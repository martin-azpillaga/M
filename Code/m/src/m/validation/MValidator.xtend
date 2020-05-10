package m.validation

import java.util.HashMap
import java.util.HashSet
import java.util.List
import m.m.Assignment
import m.m.Expression
import m.m.Cell
import m.m.Block
import m.m.Delegation
import m.m.File
import m.m.Function
import m.m.Statement
import m.m.Unary
import m.m.Value
import org.eclipse.xtext.validation.Check

import static m.m.MPackage.Literals.*
import static m.validation.Error.*
import static m.validation.GroupingReason.*
import static m.validation.TypingReason.*
import static m.validation.StandardLibrary.*
import m.m.Application
import java.util.ArrayList
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EObject
import m.m.Binary
import org.eclipse.xtext.EcoreUtil2
import m.generator.Game

class Group
{
	public var entries = new ArrayList<GroupEntry>
	public var components = new ArrayList<String>
	public Type type
}
class GroupEntry
{
	public Expression expression
	public ArrayList<GroupingReason> groupingReasons = new ArrayList<GroupingReason>
	public ArrayList<Typing> types = new ArrayList<Typing>
}
class Typing
{
	public Type type
	public TypingReason reason
}
class MValidator extends AbstractMValidator
{
	public static Game game
	
	var standardSymbols = new HashMap<String,Type>
	var userComponents = new HashMap<String,Type>
	var standardBlocks = new HashMap<String,Type>
	
	var userFunctions = new HashMap<String,Function>
	var userValues = new HashMap<String,Expression>
	
	StandardLibrary library
	
	var expressions = new HashMap<Expression,Group>
	var components = new HashMap<String,Group>
	
	@Check
	def validate(File file)
	{
		if (file.eResource.errors.empty)
		{
			library = StandardLibrary.English
			initialize(file)
			for (function : file.functions)
			{
				function.validate
			}
			solve(file)
		}
	}
	
	def private error(Error error, EObject o, EStructuralFeature feature)
	{
		error(library.errors.get(error), o, feature)
	}
	
	def private initialize(File file)
	{
		library.symbols.forEach[standardSymbols.put(name, type)]
		library.blocks.forEach[standardBlocks.put(name, type)]
		
		userComponents = new HashMap<String,Type>
		var cells = EcoreUtil2.getAllContentsOfType(file, Cell)
		for (cell : cells)
		{
			if (!standardSymbols.containsKey(cell.component))
			{
				userComponents.put(cell.component, null)
			}
		}
		
		userFunctions = new HashMap<String,Function>
		userValues = new HashMap<String,Expression>
		expressions = new HashMap<Expression,Group>
		components = new HashMap<String,Group>
		
		for (f : file.functions)
		{
			if (standardSymbols.containsKey(f.name) || userFunctions.containsKey(f.name) || userComponents.containsKey(f.name))
			{
				error(redefinition, f, FUNCTION__NAME)
			}
			else
			{
				userFunctions.put(f.name, f)
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
						«library.errors.get(undecidable)»
						«entry.groupingReasons.join(', ')»
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
						«library.errors.get(incompatible)»
						«entry.groupingReasons.join(', ')»
						«FOR type : entry.types»
						«library.types.get(type.type)»: «library.typingReason.get(type.reason)»
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
			game = new Game
			for (f : userFunctions.values)
			{
				var copy = EcoreUtil2.copy(f)
				var functionType = new ExponentType
				if (f.result !== null)
				{
					functionType.right = expressions.get(f.result).type
				}
				for (var p = f.parameters.size -1; p >= 0; p--)
				{
					val type = expressions.get(f.parameters.get(p)).type
					
					functionType = new ExponentType=>[left=type]
					functionType.right = functionType
				}
				game.functions.put(copy,functionType)
			}
			for (c : userComponents.keySet)
			{
				game.components.put(c, components.get(c).type)
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
				expressions.put(parameter,new Group=>[entries.add(new GroupEntry=>[it.expression=parameter])])
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
						set(expression,entity, queryEntity)
						statement.statements.validate(null)
						userValues = innerRestore
					}
					else
					{
						error(syntax, statement, BLOCK__EXPRESSION)
					}
				}
				else if (standard !== null)
				{
					statement.expression.set(standard, standardSymbol)
					statement.expression.validate
					statement.statements.validate(null)
				}
				else
				{
					error(undefined, statement, BLOCK__NAME)
				}
			}
			else if (statement instanceof Delegation)
			{
				statement.application.validate
			}
			else if (statement instanceof Assignment)
			{
				var atom = statement.atom
				
				group(statement.atom,statement.expression,assignment)
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
				group(expression,userValues.get(name), sameVariable)
			}
			else
			{
				error(undefined, expression, null)
			}
		}
		else if (expression instanceof Cell)
		{
			if (userValues.containsKey(expression.entity))
			{
				userValues.get(expression.entity).set(entity, cellEntity)
			}
			else
			{
				error(undefined, expression, CELL__ENTITY)
			}
			var standard = standardSymbols.get(expression.component)
			if (standard !== null)
			{
				expression.set(standard, standardSymbol)
			}
		}
		else if (expression instanceof Binary)
		{
			inferApplication(expression.operator, #[expression.left,expression.right], expression, BINARY__OPERATOR)
		}
		else if (expression instanceof Unary)
		{
			inferApplication(expression.operator, #[expression.expression], expression, UNARY__OPERATOR)
		}
		else if (expression instanceof Application)
		{
			inferApplication(expression.name, expression.arguments, expression, APPLICATION__NAME)
		}
	}
	
	def private inferApplication(String name, List<Expression> arguments, Expression expression, EStructuralFeature feature)
	{
		var standard = standardSymbols.get(name)
		var user = userFunctions.get(name)
		if (standard instanceof ExponentType)
		{
			var parameters = standard.parameters
			var result = parameters.last
			
			if (parameters.size == arguments.size + 1)
			{
				for (var i = 0; i < arguments.size; i++)
				{
					arguments.get(i).set(standard.parameters.get(i), standardSymbol)
				}
				if (result !== null)
				{
					expression.set(result, standardSymbol)
				}
			}
			else
			{
				error(undefined, expression, feature)
			}
		}
		else if (user !== null)
		{
			var parameters = user.parameters
			var result = user.result
			
			if (parameters.size == arguments.size + 1)
			{
				for (var i = 0; i < arguments.size; i++)
				{
					group(parameters.get(i), arguments.get(i), parameterArgument)
				}
				if (result !== null)
				{
					group(result, expression, parameterArgument)
				}
			}
			else
			{
				error(undefined, expression, feature)
			}
		}
		else
		{
			error(undefined, expression, feature)
		}
		arguments.forEach[validate]
	}
	
	def private parameters(ExponentType type)
	{
		var result = new ArrayList<Type>
		var t = type
		while (t.right instanceof ExponentType)
		{
			result.add(t.left)
			t = type.right as ExponentType
		}
		result.add(t.left)
		result.add(t.right)
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
		
		var g = new Group
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
			var entry = new GroupEntry=>[expression=a]
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
			var entry = new GroupEntry=>[expression=b]
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
				group(expression,components.get(expression.component).entries.head.expression, sameComponent)
			}
			components.get(expression.component).entries.findFirst[it.expression==expression].types.add(new Typing=>[it.type=type it.reason=reason])
		}
		else if (!expressions.containsKey(expression))
		{
			group(expression,expression,null)
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(new Typing=>[it.type=type it.reason=reason])
		}
		else
		{
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(new Typing=>[it.type=type it.reason=reason])
		}
	}
	
	def private declare(Value value)
	{
		if (userValues.containsKey(value.name) || userComponents.containsKey(value.name) || standardSymbols.containsKey(value.name) || userFunctions.containsKey(value.name))
		{
			error(redefinition, value, null)
		}
		else
		{
			userValues.put(value.name, value)
		}
	}
}