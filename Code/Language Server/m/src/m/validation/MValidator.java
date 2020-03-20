package m.validation;

import static m.game.GamePackage.Literals.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;

import m.game.Addable;
import m.game.Addition;
import m.game.And;
import m.game.Assignment;
import m.game.Brackets;
import m.game.Call;
import m.game.Cardinal;
import m.game.Cell;
import m.game.Orderable;
import m.game.Equality;
import m.game.Expression;
import m.game.Forall;
import m.game.Function;
import m.game.Game;
import m.game.ImplicitSet;
import m.game.Iteration;
import m.game.Join;
import m.game.Lower;
import m.game.Member;
import m.game.Multiplication;
import m.game.Not;
import m.game.Or;
import m.game.Selection;
import m.game.Statement;
import m.game.Subprocess;
import m.game.System;
import m.game.Variable;
import m.game.impl.GameImpl;
import m.generator.GameProject;

import static m.generator.GameProject.Type;
import static m.generator.GameProject.Type.*;

class Inference extends BasicEObjectImpl
{
	
}
public class MValidator extends AbstractMValidator
{
	ArrayList<HashSet<Expression>> groups = new ArrayList<>();
	HashMap<Expression, Type> expressions = new HashMap<Expression, Type>();
	HashMap<String,Type> components = new HashMap<String, Type>();
	HashMap<String, Type> functions = new HashMap<String, Type>();
	HashSet<String> variableNames = new HashSet<String>();
	HashSet<Expression> numerics = new HashSet<Expression>();
	
	@Check
	public void validate(Game game)
	{		
		var systemNames = new HashSet<String>();
		components.clear();
		groups.clear();
		functions.clear();
		expressions.clear();
		variableNames.clear();
		numerics.clear();
		
		components.put("mass", number);
		components.put("velocity", number3);
		components.put("friction", number);
		components.put("restitution", number);
		components.put("radius", number);
		components.put("height", number);
		components.put("extents", number3);
		components.put("mesh", tag);
		components.put("convexHull", tag);
		
		components.put("range", number);
		components.put("spotAngle", number);
		components.put("emission", number4);
		components.put("inputValue", number);
		components.put("timeout", truthValue);
		components.put("position", number3);
		components.put("rotation", number4);
		components.put("scale", number3);
		
		functions.put("has", entityTypeToTruth);
		functions.put("add", entityTypeAction);
		functions.put("remove", entityTypeAction);
		functions.put("create", entityAction);
		functions.put("destroy", entityAction);
		functions.put("enable", typeAction);
		functions.put("disable", typeAction);
		functions.put("load", worldAction);
		functions.put("unload", worldAction);
		
		functions.put("random", number2ToNumber);
		functions.put("cos", numberToNumber);
		functions.put("sin", numberToNumber);
		functions.put("halt", noneAction);
		
		for (var system : game.getSystems())
		{
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
		
		var newExpressions = new HashMap<Expression, Type>();
		newExpressions.putAll(expressions);
		
		while (repeat)
		{
			repeat = false;
			
			for (var expression : newExpressions.keySet())
			{
				if (expression instanceof Cell)
				{
					var cell = (Cell) expression;
					var component = cell.getComponent();
					
					if (!components.containsKey(component))
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
					while (!(block instanceof Forall) && !(block instanceof Selection) && !(block instanceof Iteration) && !(block instanceof System) && !(block instanceof Function))
					{
						block = block.eContainer();
					}
					
					for (var v : EcoreUtil2.getAllContentsOfType(block, Variable.class))
					{
						if (v.getName().equals(variableName))
						{
							set(v, type);
						}
					}
				}
			}
			
			newExpressions = new HashMap<Expression, Type>();
			
			for (var g = 0; g < groups.size(); g++)
			{
				var group = groups.get(g);
				var groupTypes = new ArrayList<Type>();
				for (var expression : group)
				{
					if (expressions.containsKey(expression))
					{
						groupTypes.add(expressions.get(expression));
					}
					else if (expression instanceof Cell)
					{
						var cell = (Cell) expression;
						var component = cell.getComponent();
						if (components.containsKey(component))
						{
							groupTypes.add(components.get(component));
						}
					}
				}
				for (var type : groupTypes)
				{
					for (var expression : group)
					{
						set(expression, type);
					}
				}
				if (groupTypes.size() == 1)
				{
					repeat = true;
					var groupType = groupTypes.get(0);
					for (var expression : group)
					{
						newExpressions.put(expression, groupType);
					}
				}
				if (groupTypes.size() >= 1)
				{
					groups.remove(group);
				}
			}
		}
		
		for (var expression : numerics)
		{
			var type = expressions.get(expression);
			if (type != null && !type.isNumeric())
			{
				error("Type error\nIncompatible types: numeric and " + type, expression, null);
			}
		}
		
		
		
		for (var group : groups)
		{
			for (var expression : group)
			{
				error("Type error\nUndecidable type", expression, null);
			}
		}
		
		var project = new GameProject();
		
		project.getSystems().addAll(game.getSystems());
		for (var component : components.keySet())
		{
			var type = components.get(component);
			project.components.put(component, type);
		}
		
		game.eResource().getContents().add(0, project);
	}

	private void validate(List<Statement> statements)
	{
		for (var statement : statements)
		{
			if (statement instanceof Selection)
			{
				var selection = (Selection) statement;
				
				set(selection.getCondition(), truthValue);
				
				validate(selection.getCondition());
				var restore = new HashSet<String>();
				restore.addAll(variableNames);
				validate(selection.getStatements());
				variableNames.clear();
				variableNames.addAll(restore);
			}
			else if (statement instanceof Iteration)
			{
				var iteration = (Iteration) statement;
				
				set(iteration.getCondition(), truthValue);
				
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
				
				set(forall.getVariable(), entity);
				
				var variableName = forall.getVariable().getName();
				var restore = new HashSet<String>();
				restore.addAll(variableNames);
				if (variableNames.contains(variableName))
				{
					error("Scoping error\nVariable already defined", forall.getVariable(), VARIABLE__NAME);
				}
				variableNames.add(variableName);
				validate(forall.getStatements());
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
			
			if (!functions.containsKey(name))
			{
				error("Scoping error\nFunction undefined", call, CALL__NAME);
				return;
			}
			Type functionType = functions.get(name);
			Type[] parameters = functionType.parameters;
			
			if (parameters.length != call.getArguments().size() + 1)
			{
				error("Scoping error\nFunction undefined\nDid you mean " + name + " : " + functionType.toString() + "?", call, CALL__NAME);
				return;
			}
			var returnType = parameters[parameters.length-1];
			set (expression, returnType);
			
			for (var i = 0; i < call.getArguments().size(); i++)
			{
				var argument = call.getArguments().get(i);
				set (argument, parameters[i]);
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
			
			set(left, truthValue);
			set(right, truthValue);
			set(expression, truthValue);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof And)
		{
			var and = (And) expression;
			var left = and.getLeft();
			var right = and.getRight();
			
			set(left, truthValue);
			set(right, truthValue);
			set(expression, truthValue);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Equality)
		{
			var equality = (Equality) expression;
			var left = equality.getLeft();
			var right = equality.getRight();
			
			group(new Expression[] {left, right});
			set(expression, truthValue);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Lower)
		{
			var comparison = (Lower) expression;
			var left = comparison.getLeft();
			var right = comparison.getRight();
			
			set(left, number);
			set(right, number);
			set(expression, truthValue);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Addition)
		{
			var addition = (Addition) expression;
			var left = addition.getLeft();
			var right = addition.getRight();
			
			numerics.add(left);
			numerics.add(right);
			numerics.add(expression);
			group(new Expression[] {expression, left, right});
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Multiplication)
		{
			var multiplication = (Multiplication) expression;
			var left = multiplication.getLeft();
			var right = multiplication.getRight();
			
			numerics.add(left);
			numerics.add(expression);
			set (right, number);
			group(new Expression[] {left, expression});
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Member)
		{
			var member = (Member) expression;
			var left = member.getLeft();
			var right = member.getRight();
			
			set(expression, truthValue);
			
			validate(left);
			validate(right);
		}
		else if (expression instanceof Not)
		{
			var not = (Not) expression;
			var e = not.getExpression();
			
			set(e, truthValue);
			set(expression, truthValue);
			
			validate(e);
		}
		else if (expression instanceof Cardinal)
		{
			var cardinal = (Cardinal) expression;
			var e = cardinal.getExpression();
			
			set(e, truthValue);
			set(expression, number);
			
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
			
			set(e, truthValue);
			
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
	
	private void set(Expression expression, Type type)
	{
		var currentType = expressions.get(expression);
		if (currentType != null)
		{
			if (currentType != type)
			{
				error("Type error\nIncompatible types: " + type.name + " and " + currentType.name, expression, null);
			}
		}
		else
		{
			expressions.put(expression, type);
		}
	}
}

