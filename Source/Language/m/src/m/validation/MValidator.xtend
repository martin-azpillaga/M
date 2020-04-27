package m.validation

import java.util.HashMap
import java.util.HashSet
import java.util.List
import m.model.Assignment
import m.model.Binary
import m.model.Cell
import m.model.Block
import m.model.Delegation
import m.model.Expression
import m.model.File
import m.model.Function
import m.model.Priorization
import m.model.Statement
import m.model.Unary
import m.model.Value
import org.eclipse.xtext.validation.Check

import static m.model.ModelPackage.Literals.*
import static m.model.Error.*
import static m.model.GroupingReason.*
import static m.model.TypingReason.*
import static m.library.StandardLibrary.*
import m.model.FunctionType
import m.model.Type
import m.model.Group
import m.model.GroupingReason
import m.model.TypingReason
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.EcoreUtil2
import m.model.Error
import m.model.Game
import m.model.Application
import m.library.Language
import m.model.ModelFactory
import static java.lang.String.*
import java.util.ArrayList

class MValidator extends AbstractMValidator
{
	extension ModelFactory factory = ModelFactory.eINSTANCE
	
	static var files = new HashMap<URI,File>
	public static Game game
	
	var standardFunctions = new HashMap<String,FunctionType>
	var standardComponents = new HashMap<String,Type>
	var standardBlocks = new HashMap<String,Type>
	
	var userFunctions = new HashMap<String,Function>
	var userValues = new HashMap<String,Expression>
	
	Language language
	
	var expressions = new HashMap<Expression,Group>
	var components = new HashMap<String,Group>
	
	@Check
	def validate(File file)
	{
		if (file.eResource.errors.empty)
		{
			language = Language.English
			initialize(file)
			for (function : file.functions)
			{
				function.validate
			}
			solve(file)
		}
	}
	
	def private initialize(File myFile)
	{
		language.function.forEach[f,name|standardFunctions.put(name, f.type)]
		language.component.forEach[c,name|standardComponents.put(name, c.type)]
		language.block.forEach[c,name|standardBlocks.put(name, c.type)]
		
		userFunctions = new HashMap<String,Function>
		userValues = new HashMap<String,Expression>
		expressions = new HashMap<Expression,Group>
		components = new HashMap<String,Group>
		
		files.put(myFile.eResource.URI,myFile)
		
		for (file : files.values)
		{
			for (f : file.functions)
			{
				if (standardFunctions.containsKey(f.name) || userFunctions.containsKey(f.name))
				{
					error(format(language.error.get(REDEFINITION), f.name), f, FUNCTION__NAME)
				}
				else
				{
					userFunctions.put(f.name, f)
				}
			}
		}
	}

	def private void solve(File myFile)
	{
		var groups = new HashSet<Group>(expressions.values)
		var errorFound = false
		for (group : groups)
		{
			if (!errorFound)
			{
				val types = new HashSet<Type>
				for (entry : group.entries)
				{
					types.addAll(entry.types.map[type])
				}
				if (types.contains(numeric) && #[number,number2,number3,number4].exists[types.contains(it)])
				{
					types.remove(numeric)
				}
				if (types.size == 1 && types.head == numeric)
				{
					types.remove(numeric)
				}
				if (types.size == 0)
				{
					errorFound = true
					for (entry : group.entries)
					{
						var error =
						'''
						«language.error.get(UNDECIDABLE)»
						«String.format(language.error.get(Error.GROUP),entry.groupingReasons.join(', '))»
						'''
						warning(error, entry.expression, null)
					}
				}
				else if (types.size == 1)
				{
					group.type = types.head
				}
				else
				{
					errorFound = true
					for (entry : group.entries)
					{
						var error =
						'''
						«language.error.get(INCOMPATIBLE)»
						«String.format(language.error.get(Error.GROUP),entry.groupingReasons.join(', '))»
						«FOR type : entry.types»
						«language.type.get(type.type)»: «language.typingReason.get(type.reason)»
						«ENDFOR»
						'''
						if (entry.types.empty)
						{
							warning(error, entry.expression, null)
						}
						else
						{
							error(error, entry.expression, null)
						}
					}
				}
			}
		}
		if (!errorFound)
		{
			myFile.groups.clear
			myFile.groups.addAll(components.values.toSet)
			game = createGame
			for (f : userFunctions.values)
			{
				var copy = EcoreUtil2.copy(f)
				var functionType = createFunctionType
				if (f.result !== null)
				{
					functionType.target = expressions.get(f.result).type
				}
				for (var p = f.parameters.size -1; p >= 0; p--)
				{
					val type = expressions.get(f.parameters.get(p)).type
					
					functionType = createFunctionType=>[source=type]
					functionType.target = functionType
				}
				copy.type = functionType
				game.functions.add(copy)
			}
			for (c : components.keySet)
			{
				game.components.add(createComponent=>[name=c type = components.get(c).type])
			}
		}
	}
	
	def private void validate(Function function)
	{
		userValues = new HashMap<String, Expression>
		for (parameter : function.parameters)
		{
			userValues.put(parameter.name, parameter)
			if (!expressions.containsKey(parameter))
			{
				expressions.put(parameter,createGroup=>[entries.add(createGroupEntry=>[it.expression=parameter])])
			}
		}
		function.statements.validate(function.result)
	}
	
	def private void validate(List<Statement> statements, Expression extra)
	{
		var restore = new HashMap(userValues)
		
		for (statement : statements)
		{
			if (statement instanceof Block)
			{
				var expression = statement.expression
				var standard = standardBlocks.get(statement.name)
				if (standard == declaration)
				{
					if (expression instanceof Value)
					{
						var innerRestore = new HashMap<String,Expression>(userValues)
						declare(expression)
						set(expression,entity, CONTROL_ENTITY)
						statement.statements.validate(null)
						userValues = innerRestore
					}
					else
					{
						error(language.error.get(SYNTAX), statement, BLOCK__EXPRESSION)
					}
				}
				else if (standard !== null)
				{
					statement.expression.set(standard, STANDARD_CONTROL)
					statement.expression.validate
					statement.statements.validate(null)
				}
				else
				{
					error(format(language.error.get(UNDEFINED), statement.name), statement, BLOCK__NAME)
				}
			}
			else if (statement instanceof Delegation)
			{
				statement.application.validate
			}
			else if (statement instanceof Assignment)
			{
				var atom = statement.atom
				
				group(statement.atom,statement.expression,GroupingReason.ASSIGNMENT)
				statement.expression.validate
				if (atom instanceof Value)
				{
					atom.declare
				}
				else
				{
					atom.validate
				}
			}
		}
		
		extra?.validate
		
		userValues = restore
	}
	
	def private void validate(Expression expression)
	{
		if (expression instanceof Value)
		{
			var name = expression.name
			
			if (userValues.containsKey(name))
			{
				group(expression,userValues.get(name), SAME_VARIABLE)
			}
			else
			{
				error(format(language.error.get(UNDEFINED),expression.name), expression, null)
			}
		}
		else if (expression instanceof Cell)
		{
			if (userValues.containsKey(expression.entity))
			{
				userValues.get(expression.entity).set(entity, CELL_ENTITY)
			}
			else
			{
				error(format(language.error.get(UNDEFINED),expression.entity), expression, CELL__ENTITY)
			}
			var standard = standardComponents.get(expression.component)
			if (standard !== null)
			{
				expression.set(standard, STANDARD_COMPONENT)
			}
		}
		else if (expression instanceof Binary)
		{
			var standard = standardFunctions.get(expression.operator)
			var user = userFunctions.get(expression.operator)
			if (standard !== null && standard.parameters.size == 3)
			{
				var result = standard.parameters.last
				expression.left.set(standard.parameters.get(0), STANDARD_FUNCTION)
				expression.right.set(standard.parameters.get(1), STANDARD_FUNCTION)
				if (result !== null)
				{
					expression.set(result, STANDARD_FUNCTION)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
				if (standard == numericNumericNumeric)
				{
					group(expression.left,expression.right, VECTOR_OPERATION)
					group(expression.left,expression, VECTOR_OPERATION)
				}
				else if (standard == numericNumberNumeric)
				{
					group(expression.left,expression, VECTOR_OPERATION)
				}
			}
			else if (user !== null && user.parameters.size == 2)
			{
				group(expression.left, user.parameters.get(0), PARAMETER_ARGUMENT)
				group(expression.right, user.parameters.get(1), PARAMETER_ARGUMENT)
				if (user.result !== null)
				{
					group(expression, user.result, PARAMETER_ARGUMENT)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
			}
			else
			{
				error(format(language.error.get(UNDEFINED),expression.operator), expression, BINARY__OPERATOR)
			}
			expression.left.validate
			expression.right.validate
		}
		else if (expression instanceof Unary)
		{
			var standard = standardFunctions.get(expression.operator)
			var user = userFunctions.get(expression.operator)
			if (standard !== null && standard.parameters.size == 2)
			{
				var result = standard.parameters.last
				expression.expression.set(standard.parameters.get(0), STANDARD_FUNCTION)
				if (result !== null)
				{
					expression.set(result, STANDARD_FUNCTION)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
			}
			else if (user !== null && user.parameters.size == 1)
			{
				group(expression.expression, user.parameters.get(0), PARAMETER_ARGUMENT)
				if (user.result !== null)
				{
					group(expression, user.result, PARAMETER_ARGUMENT)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
			}
			else
			{
				error(format(language.error.get(UNDEFINED),expression.operator), expression, UNARY__OPERATOR)
			}
			expression.expression.validate
		}
		else if (expression instanceof Application)
		{
			var standard = standardFunctions.get(expression.name)
			var user = userFunctions.get(expression.name)
			if (standard !== null && standard.parameters.size == expression.arguments.size-1)
			{
				var result = standard.parameters.last
				for (var i = 0; i < expression.arguments.size-1; i++)
				{
					expression.arguments.get(i).set(standard.parameters.get(i), STANDARD_FUNCTION)
				}
				if (result !== null)
				{
					expression.set(result, STANDARD_FUNCTION)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
			}
			else if (user !== null && user.parameters.size == expression.arguments.size)
			{
				for (var i = 0; i < expression.arguments.size; i++)
				{
					group(user.parameters.get(i), expression.arguments.get(i), PARAMETER_ARGUMENT)
				}
				if (user.result !== null)
				{
					group(user.result, expression, PARAMETER_ARGUMENT)
				}
				else if (!(expression.eContainer instanceof Block))
				{
					error(language.error.get(NO_RESULT), expression, null)
				}
			}
			else
			{
				error(format(language.error.get(UNDEFINED),expression.name), expression, APPLICATION__NAME)
			}
			expression.arguments.forEach[validate]
		}
		else if (expression instanceof Priorization)
		{
			expression.expression.validate
		}
	}
	
	def private parameters(FunctionType type)
	{
		var result = new ArrayList<Type>
		var t = type
		while (t.target instanceof FunctionType)
		{
			result.add(t.source)
			t = type.target as FunctionType
		}
		result.add(t.source)
		result.add(t.target)
		return result
	}
	
	def private group(Expression a, Expression b, GroupingReason reason)
	{
		var involvedGroups = new HashSet<Group>
		involvedGroups.add(expressions.get(a))
		involvedGroups.add(expressions.get(b))
		
		if (a instanceof Cell)
		{
			involvedGroups.add(components.get(a.component))
		}
		if (b instanceof Cell)
		{
			involvedGroups.add(components.get(b.component))
		}
		if (involvedGroups.contains(null))
		{
			involvedGroups.remove(null)
		}
		
		var g = createGroup
		for (group : involvedGroups)
		{
			for (entry : group.entries)
			{
				expressions.put(entry.expression,g)
			}
			for (component : group.components)
			{
				components.put(component, g)
			}
			g.entries.addAll(group.entries)
			g.components.addAll(group.components)
		}
		if (!g.entries.map[expression].contains(a))
		{
			var entry = createGroupEntry=>[expression=a]
			if (reason !== null)
			{
				entry.groupingReasons.add(reason)
			}
			g.entries.add(entry)
			expressions.put(a,g)
			if (a instanceof Cell)
			{
				components.put(a.component,g)
				g.components.add(a.component)
			}
		}
		if (!g.entries.map[expression].contains(b))
		{
			var entry = createGroupEntry=>[expression=b]
			if (reason !== null)
			{
				entry.groupingReasons.add(reason)
			}
			g.entries.add(entry)
			expressions.put(b,g)
			if (b instanceof Cell)
			{
				components.put(b.component,g)
				g.components.add(b.component)
			}
		}
	}
	
	def private void set(Expression expression, Type type, TypingReason reason)
	{
		if (expression instanceof Cell)
		{
			if (!components.containsKey(expression.component))
			{
				group(expression,expression,null)
			}
			else
			{
				group(expression,components.get(expression.component).entries.head.expression, SAME_COMPONENT)
			}
			components.get(expression.component).entries.findFirst[it.expression==expression].types.add(createTyping=>[it.type=type it.reason=reason])
		}
		else if (!expressions.containsKey(expression))
		{
			group(expression,expression,null)
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(createTyping=>[it.type=type it.reason=reason])
		}
		else
		{
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(createTyping=>[it.type=type it.reason=reason])
		}
	}
	
	def private declare(Value value)
	{
		if (userValues.containsKey(value.name))
		{
			error(format(language.error.get(REDEFINITION),value.name), value, null)
		}
		else
		{
			userValues.put(value.name, value)
		}
	}
}
