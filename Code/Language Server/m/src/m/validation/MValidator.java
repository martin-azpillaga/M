package m.validation;

import static m.game.GamePackage.Literals.*;

import java.util.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.game.Addition;
import m.game.And;
import m.game.Assignment;
import m.game.Brackets;
import m.game.Call;
import m.game.Cardinal;
import m.game.Cell;
import m.game.Comparison;
import m.game.Equality;
import m.game.Expression;
import m.game.Forall;
import m.game.Function;
import m.game.Game;
import m.game.ImplicitSet;
import m.game.Iteration;
import m.game.Join;
import m.game.Member;
import m.game.Multiplication;
import m.game.Not;
import m.game.Or;
import m.game.Selection;
import m.game.Statement;
import m.game.Subprocess;
import m.game.System;
import m.game.Variable;

import static m.validation.Type.*;

public class MValidator extends AbstractMValidator 
{
	GameProject project;
	ArrayList<HashSet<Expression>> groups = new ArrayList<>();
	HashMap<String,Type> libraryComponents = new HashMap<String, Type>();
	HashMap<String,Type> libraryFunctions = new HashMap<String, Type>();
	HashMap<String,Type> components = new HashMap<String, Type>();
	HashMap<String, Type> functions = new HashMap<String, Type>();
	HashMap<Expression, Type> expressions = new HashMap<Expression, Type>();
	HashSet<String> variableNames = new HashSet<String>();
	
	EObject currentObject;
	EStructuralFeature currentFeature;
	
	@Check
	public void validate(Game game)
	{
		project = new GameProject();
		
		if (libraryComponents.isEmpty())
		{
			libraryComponents.put("mass", number);
			libraryComponents.put("velocity", number3);
			libraryComponents.put("emission", number4);
			libraryComponents.put("inputValue", number);
			libraryComponents.put("timeout", truthValue);
			libraryComponents.put("position", number3);
			
			libraryFunctions.put("has", entityTypeToTruth);
			libraryFunctions.put("add", entityTypeAction);
			libraryFunctions.put("remove", entityTypeAction);
			libraryFunctions.put("create", entityAction);
			libraryFunctions.put("destroy", entityAction);
			libraryFunctions.put("random", number2ToNumber);
			libraryFunctions.put("cos", numberToNumber);
			libraryFunctions.put("sin", numberToNumber);
			libraryFunctions.put("halt", noneAction);
		}
		
		var functionNames = new HashSet<String>();
		var systemNames = new HashSet<String>();
		components.clear();
		groups.clear();
		functions.clear();
		expressions.clear();
		variableNames.clear();
		
		for (var function : libraryFunctions.keySet())
		{
			functionNames.add(function);
		}
		
		for (var system : game.getSystems())
		{
			project.systems.add(system);
			if (systemNames.contains(system.getName()))
			{
				error("Scoping error\nSystem already defined", system, SYSTEM__NAME);
			}
			else
			{
				systemNames.add(system.getName());
			}
			
			validate(system.getStatements());
			variableNames.clear();
		}
		
		
		var repeat = true;
		
		while (repeat)
		{
			repeat = false;
			
			for (var expression : expressions.keySet())
			{
				if (expression instanceof Cell)
				{
					var cell = (Cell) expression;
					var component = cell.getComponent().getName();
					
					if (!libraryComponents.containsKey(component))
					{
						var type = expressions.get(expression);

						components.put(component, type);
					}
				}
				else if (expression instanceof Variable)
				{
					var variable = (Variable) expression;
					var variableName = variable.getName();
					var type = expressions.get(expression);
					
					var block = expression.eContainer();
					while (!(block instanceof Forall))
					{
						block = block.eContainer();
					}
					var forall = (Forall) block;
					
					propagate(forall, variableName, type);
				}
			}
			
			// Groups
			for (var g = 0; g < groups.size(); g++)
			{
				var group = groups.get(g);
				for (var expression : group)
				{
					if (expressions.containsKey(expression))
					{
						repeat = true;

						var type = expressions.get(expression);
						for (var e : group)
						{
							set(e, type, e, null);
						}
						groups.remove(g);
						break;
					}
					else if (expression instanceof Cell)
					{
						var cell = (Cell) expression;
						var component = cell.getComponent().getName();
						if (components.containsKey(component))
						{
							repeat = true;
							
							var type = components.get(component);
							for (var e : group)
							{
								set(e, type, e, null);
							}
							groups.remove(g);
							break;
						}
						else if (libraryComponents.containsKey(component))
						{
							repeat = true;
							
							var type = libraryComponents.get(component);
							for (var e : group)
							{
								set(e, type, e, null);
							}
							groups.remove(g);
							break;
						}
					}
				}
			}
		}
		
		
		
		for (var group : groups)
		{
			for (var expression : group)
			{
				if (expression instanceof Cell)
				{
					error("Type error\nUndecidable type", expression, CELL__COMPONENT);
				}
				else if (expression instanceof Variable)
				{
					error("Type error\nUndecidable type", expression, VARIABLE__NAME);
				}
			}
		}
	}
	
	private void propagate(Forall block, String variableName, Type type)
	{
		
	}

	private void validate(List<Statement> statements)
	{
		for (var statement : statements)
		{
			if (statement instanceof Selection)
			{
				var selection = (Selection) statement;
				
				set(selection.getCondition(), truthValue, selection, SELECTION__CONDITION);
				
				validate(selection.getCondition());
				var restore = new HashSet<String>();
				restore.addAll(variableNames);
				validate(selection.getPositiveStatements());
				variableNames.clear();
				variableNames.addAll(restore);
				validate(selection.getNegativeStatements());
				variableNames.clear();
				variableNames.addAll(restore);
			}
			else if (statement instanceof Iteration)
			{
				var iteration = (Iteration) statement;
				
				set(iteration.getCondition(), truthValue, iteration, ITERATION__CONDITION);
				
				validate(iteration.getCondition());
				var restore = new HashSet<String>();
				restore.addAll(variableNames);
				validate(iteration.getStatements());
				variableNames.clear();
				variableNames.addAll(restore);
			}
			else if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				
				set(forall.getVariable(), entity, forall.getVariable(), VARIABLE__NAME);
				set(forall.getCondition(), truthValue, forall, FORALL__CONDITION);
				
				var variableName = forall.getVariable().getName();
				var restore = new HashSet<String>();
				restore.addAll(variableNames);
				if (variableNames.contains(variableName))
				{
					error("Scoping error\nVariable already defined", forall.getVariable(), VARIABLE__NAME);
				}
				variableNames.add(variableName);
				validate(forall.getStatements());
				validate(forall.getCondition());
				variableNames.clear();
				variableNames.addAll(restore);
			}
			else if (statement instanceof Assignment)
			{
				var assignment = (Assignment) statement;
				var atom = assignment.getAtom();
				var expression = assignment.getExpression();
				
				group(new Expression[] {atom, expression});
				
				validate(expression);
				if (atom instanceof Variable)
				{
					var variable = (Variable) atom;
					var variableName = variable.getName();
					if (variableNames.contains(variableName))
					{
						error("Scoping error\nVariable already defined", variable, VARIABLE__NAME);
					}
					variableNames.add(variableName);
				}
				validate(atom);
			}
			else if (statement instanceof Subprocess)
			{
				var subprocess = (Subprocess) statement;
				
				validate(subprocess.getCall());
			}
		}
	}
	
	private void validate(Expression expression)
	{
		if (expression instanceof Variable)
		{
			var variable = (Variable) expression;
			var variableName = variable.getName();
			
			if (!variableNames.contains(variableName))
			{
				error("Scoping error\nVariable undefined", variable, VARIABLE__NAME);
			}
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			validate(cell.getEntity());
		}
		else if (expression instanceof Call)
		{
			var call = (Call) expression;
			var name = call.getName();
			
			if (!functions.containsKey(name) && !libraryFunctions.containsKey(name))
			{
				error("Scoping error\nFunction undefined", call, CALL__NAME);
				return;
			}
			Type[] parameters;
			if (functions.containsKey(name))
			{
				parameters = functions.get(name).parameters;
			}
			else
			{
				parameters = libraryFunctions.get(name).parameters;
			}
			
			if (parameters.length != call.getArguments().size() + 1)
			{
				error("Scoping error\nFunction undefined\nThere is a function with " + (parameters.length - 1) + " parameters", call, CALL__NAME);
				return;
			}
			var returnType = parameters[parameters.length-1];
			set (expression, returnType, call, CALL__NAME);
			
			for (var i = 0; i < call.getArguments().size(); i++)
			{
				var argument = call.getArguments().get(i);
				set (argument, parameters[i], call, CALL__NAME);
				if (!(parameters[i] == type && argument instanceof Variable))
				{
					validate(argument);
				}
			}
		}
		else if (expression instanceof Or)
		{
			var or = (Or) expression;
			var left = or.getLeft();
			var right = or.getRight();
			
			set(left, truthValue, or, OR__LEFT);
			set(right, truthValue, or, OR__RIGHT);
			set(expression, truthValue, or, OR__LEFT);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof And)
		{
			var and = (And) expression;
			var left = and.getLeft();
			var right = and.getRight();
			
			set(left, truthValue, and, AND__LEFT);
			set(right, truthValue, and, AND__RIGHT);
			set(expression, truthValue, and, AND__LEFT);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Equality)
		{
			var equality = (Equality) expression;
			var left = equality.getLeft();
			var right = equality.getRight();
			
			group(new Expression[] {left, right});
			set(expression, truthValue, equality, EQUALITY__KIND);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Comparison)
		{
			var comparison = (Comparison) expression;
			var left = comparison.getLeft();
			var right = comparison.getRight();
			
			set(left, number, comparison, COMPARISON__LEFT);
			set(right, number, comparison, COMPARISON__RIGHT);
			set(expression, truthValue, comparison, COMPARISON__KIND);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Addition)
		{
			var addition = (Addition) expression;
			var left = addition.getLeft();
			var right = addition.getRight();
			
			group(new Expression[] {expression, left, right});
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Multiplication)
		{
			var multiplication = (Multiplication) expression;
			var left = multiplication.getLeft();
			var right = multiplication.getRight();
			
			set (right, number, multiplication, MULTIPLICATION__RIGHT);
			group(new Expression[] {left, expression});
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Member)
		{
			var member = (Member) expression;
			var left = member.getLeft();
			var right = member.getRight();
			
			set(expression, truthValue, member, MEMBER__LEFT);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Not)
		{
			var not = (Not) expression;
			var e = not.getExpression();
			
			set(e, truthValue, not, NOT__EXPRESSION);
			set(expression, truthValue, not, NOT__EXPRESSION);
			
			validate(e);
		}
		else if (expression instanceof Cardinal)
		{
			var cardinal = (Cardinal) expression;
			var e = cardinal.getExpression();
			
			set(e, truthValue, cardinal, CARDINAL__EXPRESSION);
			set(expression, number, cardinal, CARDINAL__EXPRESSION);
			
			validate(e);
		}
		else if (expression instanceof Brackets)
		{
			var brackets = (Brackets) expression;
			var e = brackets.getExpression();
			
			validate(e);
		}
		else if (expression instanceof Join)
		{
			var join = (Join) expression;
			
			group(join.getEntries().toArray(new Expression[0]));
			for (var entry : join.getEntries())
			{
				validate(entry);
			}
		}
		else if (expression instanceof ImplicitSet)
		{
			var set = (ImplicitSet) expression;
			var variable = set.getVariable();
			var e = set.getPredicate();
			
			set(e, truthValue, set, IMPLICIT_SET__PREDICATE);
			
			var variableName = variable.getName();
			if (variableNames.contains(variableName))
			{
				error("Scoping error\nVariable already defined", variable, VARIABLE__NAME);
			}
			var restore = new HashSet<String>();
			restore.addAll(variableNames);
			variableNames.add(variableName);
			validate(e);
			variableNames.clear();
			variableNames.addAll(restore);
		}
	}
	
	private void group(Expression[] expressions)
	{
		var found = false;
		for (var group : groups)
		{
			for (var expression : expressions)
			{
				if (group.contains(expression))
				{
					found = true;
					for (var e : expressions)
					{
						if (!group.contains(e))
						{
							group.add(e);
						}
					}
				}
			}
		}
		if (!found)
		{
			var newGroup = new HashSet<Expression>();
			for (var e : expressions)
			{
				newGroup.add(e);
			}
			groups.add(newGroup);
		}
	}	
	
	private void set(Expression expression, Type type, EObject o, EStructuralFeature feature)
	{
		var currentType = expressions.get(expression);
		if (currentType != null)
		{
			if (currentType != type)
			{
				error("Type error\nIncompatible types: " + type.name + " and " + currentType.name, o, feature);
			}
		}
		else
		{
			expressions.put(expression, type);
		}
	}
	
	//private void setComponent(String name, Type type, EObject o, EStructuralFeature feature)
	//{
		/*
		var existent = components.containsKey(name);
		if (existent)
		{
			var current = components.get(name);
			if (current != type)
			{
				error("Incompatible types: "+current.getName()+" and "+type.getName(), o, feature);
			}
		}
		else
		{
			components.put(name, type);
		}*/
	//}
	/*

	@Check
	public void scope(Call call)
	{
		var name = call.getName();
		var arguments = call.getArguments();
		
		var found = false;
		
		for (var function : game.getFunctions())
		{
			if (name.equals(function.getName()))
			{
				found = true;
				
				if (name.equals(has.getName()))
				{
					setComponent(((Variable)arguments.get(0)).getName(), tag, call, CALL__NAME);
				}
				
				var parameters = function.getType().getParameters();
				if (arguments.size() + 1  != parameters.size())
				{
					error(name + " " + parameters + " not applicable to " + name + " " + arguments, call, CALL__NAME);
					break;
				}
				
				break;
			}
		}
		
		if (!found)
		{
			error("Function " + name + " undefined", call, CALL__NAME);
		}
		
	}
	
	
	
	
	
	
	@Check
	public void clear(Game _game)
	{
		game = _game;
		groups.clear();
		components.clear();
		
		var standard = getGame();
		game.getTypes().addAll(standard.getTypes());
		game.getComponents().addAll(standard.getComponents());
		game.getFunctions().addAll(standard.getFunctions());
	}
	
	private void group(Expression... expressions)
	{
		var found = false;
		for (var group : groups)
		{
			for (var expression : expressions)
			{
				if (group.contains(expression))
				{
					found = true;
					for (var e : expressions)
					{
						if (!group.contains(e))
						{
							group.add(e);
						}
					}
				}
			}
		}
		if (!found)
		{
			var list = new ArrayList<Expression>();
			for (var e : expressions)
			{
				list.add(e);
			}
			groups.add(list);
		}
	}	
	
	private void set(Expression expression, Type type, EObject o, EStructuralFeature feature)
	{
		var currentType = expression.getType();
		if (currentType != null)
		{
			if (currentType != type)
			{
				error("Incompatible types: " + expression.getType().getName() + " and " + type.getName(), o, feature);
			}
		}
		else
		{
			//expression.setType(type);
		}
	}
	
	private void setComponent(String name, Type type, EObject o, EStructuralFeature feature)
	{
		var existent = components.containsKey(name);
		if (existent)
		{
			var current = components.get(name);
			if (current != type)
			{
				error("Incompatible types: "+current.getName()+" and "+type.getName(), o, feature);
			}
		}
		else
		{
			components.put(name, type);
		}
	}
	
	
	
	@Check
	public void infer(Cell cell)
	{
		var entity = cell.getEntity();
		var component = cell.getComponent();
		
		set(entity, StandardLibrary.entity, cell, CELL__ENTITY);
		set(component, type, cell, CELL__COMPONENT);
	}	
	
	@Check
	public void infer(Selection selection)
	{
		set(selection.getCondition(), bool, selection, SELECTION__CONDITION);
	}
	
	@Check
	public void infer(Iteration iteration)
	{
		set(iteration.getCondition(), bool, iteration, ITERATION__CONDITION);
	}
	
	@Check
	public void infer(Forall forall)
	{
		var variable = forall.getVariable();
		var condition = forall.getCondition();
		
		if (condition != null)
		{
			set(condition, bool, forall, FORALL__CONDITION);
		}
		
		set(variable, entity, forall, FORALL__VARIABLE);
	}
	
	@Check
	public void infer(Assignment assignment)
	{
		var atom = assignment.getAtom();
		var expression = assignment.getExpression();
		
		group(atom,expression);
	}
	
	@Check
	public void infer(Or or)
	{
		set(or, bool, or, OR__LEFT);
		set(or.getLeft(), bool, or, OR__LEFT);
		set(or.getRight(), bool, or, AND__LEFT);
	}
	
	@Check
	public void infer(And and)
	{
		set(and, bool, and, AND__LEFT);
		set(and.getLeft(), bool, and, AND__LEFT);
		set(and.getRight(), bool, and, AND__LEFT);
	}
	
	@Check
	public void infer(LogicalNot not)
	{
		set(not, bool, not, LOGICAL_NOT__EXPRESSION);
		set(not.getExpression(), bool, not, LOGICAL_NOT__EXPRESSION);
	}
	
	@Check
	public void infer(Equality equality)
	{
		var left = equality.getLeft();
		var right = equality.getRight();
		
		group(left, right);
	}
	
	@Check
	public void infer(Addition addition)
	{
		var left = addition.getLeft();
		var right = addition.getRight();
		
		group(addition, left, right);
	}
	
	@Check
	public void infer(Multiplication multiplication)
	{
		var left = multiplication.getLeft();
		var right = multiplication.getRight();
		
		group(multiplication, left);
		set(right, float1, multiplication, MULTIPLICATION__RIGHT);
	}
	
	@Check
	public void infer(Comparison comparison)
	{
		var left = comparison.getLeft();
		var right = comparison.getRight();
		
		set(comparison, bool, comparison, COMPARISON__KIND);
		set(left, float1, comparison, COMPARISON__LEFT);
		set(right, float1, comparison, COMPARISON__RIGHT);
	}
	
	@Check
	public void infer(ImplicitSet set)
	{
		set(set.getVariable(), entity, set, IMPLICIT_SET__VARIABLE);
		set(set.getPredicate(), bool, set, IMPLICIT_SET__PREDICATE);	
		set(set, entityList, set, IMPLICIT_SET__VARIABLE);
	}
	
	@Check
	public void infer(SetExpression expression)
	{
		var left = expression.getLeft();
		var right = expression.getRight();

		set(left, entity, expression, SET_EXPRESSION__LEFT);
		set(right, entityList, expression, SET_EXPRESSION__RIGHT);
		set(expression, bool, expression, SET_EXPRESSION__LEFT);
	}
	
	@Check
	public void infer(Cardinal cardinal)
	{
		set(cardinal, float1, cardinal, CARDINAL__EXPRESSION);
		set(cardinal.getExpression(), entityList, cardinal, CARDINAL__EXPRESSION);
	}
	
	@Check
	public void infer(Join join)
	{
		var entries = join.getEntries();
		for (var entry : entries)
		{
			set(entry, float1, join, JOIN__ENTRIES);
		}
		if (entries.size() == 2)
		{
			set(join, float2, join, JOIN__ENTRIES);
		}
		else if (entries.size() == 3)
		{
			set(join, float3, join, JOIN__ENTRIES);
		}
		else if (entries.size() == 4)
		{
			set(join, float4, join, JOIN__ENTRIES);
		}
		else
		{
			error("Only 4-vectors are supported", join, JOIN__ENTRIES);
		}
	}

	
	@Check
	public void infer(Call call)
	{
		var name = call.getName();
		var arguments = call.getArguments();
		
		for (var function : game.getFunctions())
		{
			if (name.equals(function.getName()))
			{				
				var parameters = function.getType().getParameters();
				for (var i = 0; i < parameters.size(); i++)
				{
					var simpleType = parameters.get(i);
					if (simpleType != any)
					{
						set(arguments.get(i), simpleType, call, CALL__NAME);
					}
				}
				
				break;
			}
		}		
	}
	
	
	
	
	@Check
	public void check(End end)
	{
		var game = (Game) end.eContainer();
		solve();
		report(game);
	}
	
	
	public void solve()
	{
		var repeat = true;
		
		while (repeat)
		{
			repeat = false;
			
			// Groups
			for (var g = 0; g < groups.size(); g++)
			{
				var group = groups.get(g);
				for (var expression : group)
				{
					if (expression.getType() != null)
					{
						repeat = true;
						
						var type = expression.getType();
						for (var e : group)
						{
							set(e, type, e, null);
						}
						groups.remove(g);
						break;
					}
					else if (expression instanceof Cell)
					{
						var access = (Cell) expression;
						var component = access.getComponent().getName();
						if (components.containsKey(component))
						{
							repeat = true;
							
							var type = components.get(component);
							for (var e : group)
							{
								set(e, type, e, null);
							}
							groups.remove(g);
							break;
						}
					}
				}
			}
		}
	}
	
	private void report(Game game)
	{
		for (var group : groups)
		{
			for (var expression : group)
			{
				if (expression instanceof Cell)
				{
					warning("Undecidable type", expression, CELL__COMPONENT);
				}
			}
		}
	}*/
}
class GameProject
{
	public List<System> systems = new ArrayList<System>();
	public List<Function> functions = new ArrayList<Function>();
	public List<Component> components = new ArrayList<Component>();
}
class Component
{
	public Type type;
	public String name;
}
class Type
{
	public static final String array = "array";
	public static final String array2 = "array2";
	public static final String array3 = "array3";
	public static final String array4 = "array4";
	public static final String function = "Function";

	public static final Type type = new Type("Type",null);
	public static final Type entity = new Type("Entity",null);
	public static final Type number = new Type("number", null);
	public static final Type truthValue = new Type("truth value", null);
	public static final Type tag = new Type("tag", null);
	public static final Type none = new Type("none", null);
	public static final Type number2 = new Type(array2, new Type[] {number});
	public static final Type number3 = new Type(array3, new Type[] {number});
	public static final Type number4 = new Type(array4, new Type[] {number});
	public static final Type entityTypeAction = new Type(function, new Type[] {type, entity, none});
	public static final Type entityAction = new Type(function, new Type[] {entity, none});
	public static final Type noneAction = new Type(function, new Type[] {none});
	public static final Type numberToNumber = new Type(function, new Type[] {number, number});
	public static final Type number2ToNumber = new Type(function, new Type[] {number2, number});
	public static final Type entityTypeToTruth = new Type(function, new Type[] {type, entity, truthValue});
	
	public String name;
	public Type[] parameters;
	
	public Type(String name, Type[] parameters)
	{
		this.name = name;
		this.parameters = parameters;
	}
}

class StandardLibrary
{
	static Game game;
	public static Type float1;
	public static Type float2;
	public static Type float3;
	public static Type float4;
	public static Type entity;
	public static Type world;
	public static Type entityList;
	public static Type tag;
	public static Type bool;
	public static Type type;
	public static Type any;
	public static Type none;
	
	public static Function has;
	public static Function create;
	public static Function delete;
	public static Function add;
	public static Function remove;
	public static Function enable;
	public static Function disable;
	public static Function load;
	public static Function unload;
	
	public StandardLibrary()
	{
		english();
	}
	
	public static Game getGame()
	{
		english();
		return game;
	}
	
	private static void english()
	{
		/*
		tag = type("tag");
		bool = type("bool");
		float1 = type("float");
		float2 = type("float2");
		float3 = type("float3");
		float4 = type("float4");
		entity = type("entity");
		world = type("SubScene");
		entityList = type(array, new Type[] {entity});
		type = type("Type");
		any = type("Object");
		none = type("void");
		
		game = game();
		game.getTypes().add(float1);
		game.getTypes().add(float2);
		game.getTypes().add(float3);
		game.getTypes().add(float4);
		game.getTypes().add(entity);
		game.getTypes().add(world);
		game.getTypes().add(entityList);
		game.getTypes().add(bool);
		game.getTypes().add(tag);
		game.getTypes().add(type);
		game.getTypes().add(any);
		game.getTypes().add(none);
		
		has = function("active", type(function, new Type[] {any,bool}));
		add = function("activate", type(function, new Type[] {any, none}));
		remove = function("deactivate", type(function, new Type[] {any, none}));
		create = function("copy", type(function, new Type[] {entity, none}));
		delete = function("delete", type(function, new Type[] {entity,none}));
		load = function("load", type(function, new Type[] {world, none}));
		unload = function("unload", type(function, new Type[] {world, none}));
		enable = function("enable", type(function, new Type[] {type, none}));
		disable = function("disable", type(function, new Type[] {type, none}));
		
		game.getComponents().add(component(float1, "mass"));
		
		game.getFunctions().add(has);
		game.getFunctions().add(add);
		game.getFunctions().add(remove);
		game.getFunctions().add(create);
		game.getFunctions().add(delete);
		game.getFunctions().add(load);
		game.getFunctions().add(unload);
		game.getFunctions().add(enable);
		game.getFunctions().add(disable);*/
	}
}
