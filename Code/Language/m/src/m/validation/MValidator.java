package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.generator.Game;
import m.m.Expression;


public class MValidator extends AbstractMValidator
{
	class Group
	{
		public ArrayList<GroupEntry> entries = new ArrayList<GroupEntry>();
		public ArrayList<String> components = new ArrayList<String>();
		public Type type;
	}
	class GroupEntry
	{
		public Expression expression;
		public ArrayList<GroupingReason> groupingReasons = new ArrayList<GroupingReason>();
		public ArrayList<Type> types = new ArrayList<Type>();
	}
	class Problem
	{
		public boolean isError;
		public String message;
		public EObject o;
		public EStructuralFeature feature;
	}
	
	public static Game game;
	
	Map<String,Type> standardSymbols = new HashMap<String,Type>();
	Map<String,Type> userComponents = new HashMap<String,Type>();
	Map<String,Type> standardBlocks = new HashMap<String,Type>();
	
	/*
	var userFunctions = new HashMap<String,Function>();
	var userValues = new HashMap<String,Expression>();
	
	StandardLibrary library
	
	var expressions = new HashMap<Expression,Group>();
	var components = new HashMap<String,Group>();
	
	var problems = new ArrayList<Problem>();
	
	@Check
	def validate(File file)
	{
		if (file.eResource.errors.empty)
		{
			var map = new HashMap<StandardLibrary,ArrayList<Problem>>();
			for (lib : #[StandardLibrary.English])
			{
				problems = new ArrayList<Problem>
				library = lib
				initialize(file)
				for (function : file.functions)
				{
					function.validate
				}
				solve(file)
				map.put(library, problems)
			}
			println(map)
			var report = map.get(StandardLibrary.English)
			for (problem : report)
			{
				if (problem.isError)
				{
					error(problem.message, problem.o, problem.feature)
				}
				else
				{
					warning(problem.message, problem.o, problem.feature)
				}
			}
		}
	}
	
	def private void MError(MError err, EObject o, EStructuralFeature feature)
	{
		problems.add(new Problem=>[isError=true message=library.errors.get(err) it.o=o it.feature=feature])
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
				MError(redefinition, f, FUNCTION__NAME)
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
		var MErrorFound = false
		for (group : groups)
		{
			if (!MErrorFound)
			{
				val types = new HashSet<Type>
				for (entry : group.entries)
				{
					types.addAll(entry.types)
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
					MErrorFound = true
					for (entry : group.entries)
					{
						val MError =
						'''
						«library.errors.get(undecidable)»
						«entry.groupingReasons.join(', ')»
						'''
						problems.add(new Problem=>[message=MError o=entry.expression feature=null])
					}
				}
				else if (types.size == 1)
				{
					group.type = types.head
				}
				else
				{
					MErrorFound = true
					for (entry : group.entries)
					{
						val message =
						'''
						«library.errors.get(incompatible)»
						«entry.groupingReasons.join(', ')»
						«FOR type : entry.types»
						«library.name(type)»: Standard symbol
						«ENDFOR»
						'''
						if (entry.types.empty)
						{
							problems.add(new Problem=>[it.message=message o=entry.expression feature=null])
						}
						else
						{
							MError(incompatible, entry.expression, null)
						}
					}
				}
			}
		}
		if (!MErrorFound)
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
						set(expression,entity)
						statement.statements.validate(null)
						userValues = innerRestore
					}
					else
					{
						MError(syntax, statement, BLOCK__EXPRESSION)
					}
				}
				else if (standard !== null)
				{
					statement.expression.set(standard)
					statement.expression.validate
					statement.statements.validate(null)
				}
				else
				{
					MError(undefined, statement, BLOCK__NAME)
				}
			}
			else if (statement instanceof Application)
			{
				statement.validate
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
				MError(undefined, expression, null)
			}
		}
		else if (expression instanceof Cell)
		{
			if (userValues.containsKey(expression.entity))
			{
				userValues.get(expression.entity).set(entity)
			}
			else
			{
				MError(undefined, expression, CELL__ENTITY)
			}
			var standard = standardSymbols.get(expression.component)
			if (standard !== null)
			{
				expression.set(standard)
			}
		}
		else if (expression instanceof Binary)
		{
			inferApplication(expression.operator, #[expression.left,expression.right], expression, EXPRESSION__OPERATOR)
		}
		else if (expression instanceof Unary)
		{
			inferApplication(expression.operator, #[expression.expression], expression, EXPRESSION__OPERATOR)
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
					arguments.get(i).set(standard.parameters.get(i))
				}
				if (result !== null)
				{
					expression.set(result)
					if (standard == numericNumberNumeric)
					{
						group(arguments.get(0), expression, vectorArithmetic)
					}
					else if (standard == numericNumericNumeric)
					{
						group(arguments.get(0), arguments.get(1), vectorArithmetic)
						group(arguments.get(0), expression, vectorArithmetic)
					}
				}
			}
			else
			{
				MError(undefined, expression, feature)
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
				MError(undefined, expression, feature)
			}
		}
		else
		{
			MError(undefined, expression, feature)
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
			t = t.right as ExponentType
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
	
	def private void set(Expression expression, Type type)
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
			components.get(expression.component).entries.findFirst[it.expression==expression].types.add(type)
		}
		else if (!expressions.containsKey(expression))
		{
			group(expression,expression,null)
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(type)
		}
		else
		{
			expressions.get(expression).entries.findFirst[it.expression==expression].types.add(type)
		}
	}
	
	def private declare(Value value)
	{
		if (userValues.containsKey(value.name) || userComponents.containsKey(value.name) || standardSymbols.containsKey(value.name) || userFunctions.containsKey(value.name))
		{
			MError(redefinition, value, null)
		}
		else
		{
			userValues.put(value.name, value)
		}
	}*/
}