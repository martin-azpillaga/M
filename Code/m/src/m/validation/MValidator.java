package m.validation;

import static m.validation.Type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;

import m.m.Component;
import m.m.End;
import m.m.Exists;
import m.m.Amount;
import m.m.Archetype;
import m.m.AssetComponent;
import m.m.Forall;
import m.m.MPackage;
import m.m.Game;
import m.m.System;
import m.modular.Variable;
import m.m.VectorComponent;
import m.modular.AccessExpression;
import m.modular.Action;
import m.modular.AdditiveExpression;
import m.modular.Assignment;
import m.modular.Block;
import m.modular.Comparison;
import m.modular.Equality;
import m.modular.Expression;
import m.modular.Function;
import m.modular.Iteration;
import m.modular.LogicalAnd;
import m.modular.LogicalNot;
import m.modular.LogicalOr;
import m.modular.ModularPackage;
import m.modular.MultiplicativeExpression;
import m.modular.Procedure;
import m.modular.Selection;
import m.modular.Statement;
import m.modular.UnaryMinus;
import static m.modular.ModularPackage.Literals.*;
import static m.m.MPackage.Literals.*;

@SuppressWarnings("unused")
public class MValidator extends AbstractMValidator 
{
	public static HashMap<Expression,Type> expressions;
	public static HashMap<String,Type> components;
	public static HashMap<String,Type> variables;
	public static ArrayList<ArrayList<Expression>> groups;
	
	@Check
	public void unique(Component component)
	{
		var archetype = (Archetype) component.eContainer();
		
		var amount = 0;
		
		for (var c : archetype.getComponents())
		{
			var name = c.getName();
			if (component.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated component",COMPONENT__NAME);
		}
	}
	
	@Check
	public void unique(Archetype archetype)
	{
		var module = (Game) archetype.eContainer();
		
		var amount = 0;
		
		for (var e : module.getArchetypes())
		{
			var name = e.getName();
			if (archetype.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated entity",ARCHETYPE__NAME);
		}
	}
	
	@Check
	public void unique(m.m.System system)
	{
		var module = (Game) system.eContainer();
		
		var amount = 0;
		
		for (var s : module.getSystems())
		{
			var name = s.getName();
			if (system.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated procedure",SYSTEM__NAME);
		}
	}
	
	@Check
	public void unique(Procedure procedure)
	{
		var module = (Game) procedure.eContainer();
		
		var amount = 0;
		
		for (var s : module.getProcedures())
		{
			var name = s.getName();
			if (name.equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated system",PROCEDURE__NAME);
		}
	}
	
	@Check
	public void uniqueArguments(Procedure procedure)
	{
		var arguments = procedure.getArguments();
		
		for (var i = 0; i < arguments.size(); i++)
		{
			for (var j = i+1; j < arguments.size(); j++)
			{
				if (arguments.get(i).equals(arguments.get(j)))
				{
					error("Repeated argument "+arguments.get(i), PROCEDURE__NAME);
					return;
				}
			}
		}
	}
	
	@Check
	public void uniqueTags(Forall forall)
	{
		var tags = forall.getTags();
		
		for (var i = 0; i < tags.size(); i++)
		{
			var itag = tags.get(i);
			for (var j = i+1; j < tags.size(); j++)
			{
				if (itag.equals(tags.get(j)))
				{
					error("Repeated tag "+itag, FORALL__VARIABLE);
					return;
				}
			}
		}
	}
	
	/*
	@Check
	public void uniqueEntity(Forall forall)
	{
		var myEntity = forall.getVariable();
		var container = forall.eContainer();
		EObject current = forall;
		
		while (!(container instanceof Game))
		{
			if (container instanceof Forall)
			{
				var l = (Forall) container;
				if (l.getVariable().equals(myEntity))
				{
					error("Already exists entity " + myEntity + " in the scope", FORALL__VARIABLE);
				}
			}
			else if (container instanceof Block)
			{
				var block = (Block) container;
				var list = block.getStatements();
				for (var i = 0; i < list.size();i++)
				{
					if (list.get(i) == current)
					{
						break;
					}
					else
					{
						var statement = list.get(i);
						if (statement instanceof Assignment)
						{
							var assignment = (Assignment) statement;
							var entity = ((Variable)assignment.getVariable()).getName();
							if (entity.equals(myEntity))
							{
								error("Already exists entity " + myEntity + " in the scope", FORALL__VARIABLE);
							}
						}
					}
				}
			}
			
			current = container;
			container = container.eContainer();
		}
	}
	
	@Check
	public void scope(Variable variable)
	{
		var myEntity = variable.getName();
		var container = variable.eContainer();
		if (container instanceof FunctionCall)
		{
			var call = (FunctionCall) container;
			var function = call.getFunction();
			if (function.equals("add")||function.equals("remove"))
			{
				if (call.getParameters().get(0) == variable)
				{
					return;
				}
			}
		}
		EObject current = variable;
		
		if (container instanceof Assignment)
		{
			if (((Assignment) container).getVariable() == variable)
			{
				return;
			}
		}
		
		while (!(container instanceof Game))
		{
			if (container instanceof Block)
			{
				var block = (Block) container;
				if (container instanceof Forall)
				{
					var l = (Forall) container;
					if (l.getVariable().equals(myEntity))
					{
						return;
					}
					else if (l.getTags().contains(myEntity))
					{
						return;
					}
				}
				for (var i = 0; i < block.getStatements().size();i++)
				{
					if (block.getStatements().get(i) == current)
					{
						break;
					}
					else
					{
						var statement = block.getStatements().get(i);
						if (statement instanceof Assignment)
						{
							var assignment = (Assignment) statement;
							var assignmentVariable = assignment.getVariable();
							if (assignmentVariable instanceof Variable)
							{
								var v = (Variable) assignmentVariable;
								if (v.getName().equals(myEntity))
								{
									return;
								}
							}
						}
					}
				}
			}
			current = container;
			container = container.eContainer();
		}
		error("Entity " + myEntity + " undefined in the scope", variable, VARIABLE__NAME);
	}*/
	
	@Check
	public void existsBase(Archetype archetype)
	{
		var base = archetype.getBase();
		if (base != null)
		{
			var module = (Game) archetype.eContainer();
			for (var e : module.getArchetypes())
			{
				if (e.getName().equals(base))
				{
					return;
				}
			}
			error("The base entity is not declared in this module", ARCHETYPE__BASE);
		}
	}
	
	@Check
	public void acyclic(Archetype archetype)
	{
		var base = archetype.getBase();
		var module = (Game) archetype.eContainer();
		var entities = module.getArchetypes();
		
		var visited = new ArrayList<String>();
		visited.add(archetype.getName());
		
		while (base != null)
		{
			for (var e : entities)
			{
				if (e.getName().equals(base))
				{
					if (visited.contains(e.getName()))
					{
						error("Cyclic base entity dependency", ARCHETYPE__BASE);
						return;
					}
					else
					{
						visited.add(e.getName());
						base = e.getBase();
					}
				}
			}
			return;
		}
	}
	
	@Check
	public void type(VectorComponent vector)
	{
		if (vector.getEntries().size() > 4)
		{
			error("Vectors can have up to four entries", vector, COMPONENT__NAME);
		}
	}
	
	@Check
	public void clean(Game modul)
	{
		components = new HashMap<>();
		variables = new HashMap<>();
		expressions = new HashMap<>();
		groups = new ArrayList<>();
		for (var entry : StandardLibrary.values())
		{
			components.put(entry.toString(), entry.getType());
		}
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
	
	private void set(Component component, Type type)
	{
		var name = component.getName();
		if (components.containsKey(name))
		{
			if (components.get(name) != type)
			{
				error("Expected type " + components.get(name) + " but got " + type.toString(), component, COMPONENT__NAME);
			}
		}
		else
		{
			components.put(name, type);
		}
	}
	
	private void setComponent(String name, Type type, EObject obj, EStructuralFeature feature)
	{
		if (components.containsKey(name))
		{
			if (components.get(name) != type)
			{
				error("Expected type " + components.get(name) + " but got " + type.toString(), obj, feature);
			}
		}
		else
		{
			components.put(name, type);
		}
	}
	
	private void setVariable(String name, Type type, EObject obj, EStructuralFeature feature)
	{
		if (variables.containsKey(name))
		{
			if (variables.get(name) != type)
			{
				error("Expected type " + variables.get(name) + " but got " + type.toString(), obj, feature);
			}
		}
		else
		{
			variables.put(name, type);
		}
	}
	
	private boolean set(Variable variable, Type type)
	{
		var name = variable.getName();
		
		if (variables.containsKey(name))
		{
			if (variables.get(name) != type)
			{
				error("Expected " + variables.get(name) + ", got " + type, variable, EXPRESSION__EXPRESSION);
			}
		}
		else
		{
			variables.put(name, type);
			return true;
		}
		return false;
	}
	
	private boolean set(AccessExpression access, Type type)
	{
		var right = access.getRight();
		if (right instanceof Function)
		{
			error("Can only access components",access, EXPRESSION__EXPRESSION);
			return false;
		}
		var component = ((Variable)right).getName();
		if (components.containsKey(component))
		{
			if (components.get(component) != type)
			{
				error("Type conflict: This variable cannot be " + components.get(component) + " and " + type, access, EXPRESSION__EXPRESSION);
			}
		}
		else
		{
			components.put(component, type);
			return true;
		}
		return false;
	}
	
	private void set(Expression expression, Type type)
	{
		if (expressions.containsKey(expression))
		{
			if (expressions.get(expression) != type)
			{
				error("Expected type " + expressions.get(expression) + " but got " + type.toString(), expression, EXPRESSION__EXPRESSION);
			}
		}
		else
		{
			expressions.put(expression, type);
		}
	}
	
	
	@Check
	public void infer(VectorComponent component)
	{
		var entries = component.getEntries();
		var name = component.getName();
		if (entries.size() == 1)
		{
			set(component,float1);
		}
		else if (entries.size() == 2)
		{
			set(component, float2);
		}
		else if (entries.size() == 3)
		{
			set(component, float3);
		}
		else if (entries.size() == 4)
		{
			set(component, float4);
		}
	}
	
	@Check
	public void infer(Component component)
	{
		var feature = COMPONENT__NAME;
		var name = component.getName();
		magic(name,"Range", float1, input, component, feature);
		magic(name,"Vector", float2, input, component, feature);
		magic(name,"Trigger", bool, input, component, feature);
		
		magic(name,"Timeout", bool, float1, component, feature);
		magic(name,"Elapsed", float1, float1, component, feature);
		
		magic(name,"Chosen", bool, none, component, feature);
		
		magic(name,"Transition", bool, none, component, feature);
	}
	
	@Check
	public void infer(AccessExpression access)
	{
		var entity = ((Variable)access.getLeft()).getName();
		setVariable(entity, Type.entity, access, ACCESS_EXPRESSION__LEFT);

		var feature = EXPRESSION__EXPRESSION;
		var component = ((Variable)access.getRight()).getName();
		magic(component, "Range", float1, input, access, feature);
		magic(component,"Vector", float2, input, access, feature);
		magic(component,"Trigger", bool, input, access, feature);
		
		magic(component,"Timeout", bool, float1, access, feature);
		magic(component,"Elapsed", float1, float1, access, feature);
		
		magic(component,"Chosen", bool, none, access, feature);
		
		magic(component,"Transition", bool, none, access, feature);
	}
	
	private void magic(String component, String word, Type original, Type magic, EObject access, EStructuralFeature feature)
	{
		if (component.endsWith(word))
		{
			setComponent(component, original, access, feature);
			if (magic != none)
			{
				var magicParent = component.substring(0, component.lastIndexOf(word));
				setComponent(magicParent, magic, access, feature);
			}
		}
	}
	
	@Check
	public void infer(Forall forall)
	{
		var variable = forall.getVariable();
		var collection = forall.getCollection();
		var tags = forall.getTags();
		
		setVariable(variable, entity, forall, FORALL__VARIABLE);

		if (collection != null)
		{
			set(collection, Type.entityList);
		}
		for (var tag : tags)
		{
			setComponent(tag, Type.tag, forall, FORALL__TAGS);
		}
	}
	
	@Check
	public void infer(Exists exists)
	{
		var variable = exists.getVariable();
		var collection = exists.getCollection();
		var condition = exists.getCondition();
		
		setVariable(variable, entity, exists, EXISTS__VARIABLE);
		set(collection, entityList);
		set(condition, bool);
	}
	
	@Check
	public void infer(Amount amount)
	{
		var bound = amount.getBound();
		set(bound, float1);
	}
	
	@Check
	public void infer(Selection selection)
	{
		for (var branch : selection.getBranches())
		{
			if (branch.getCondition() != null)
			{
				set(branch.getCondition(), bool);
			}
		}
	}
	
	@Check
	public void infer(Iteration iteration)
	{
		set(iteration.getCondition(), bool);
	}
	
	@Check
	public void infer(LogicalOr or)
	{
		set(or, bool);
		set(or.getLeft(), bool);
		set(or.getRight(), bool);
	}
	
	@Check
	public void infer(LogicalAnd and)
	{
		set(and, bool);
		set(and.getLeft(), bool);
		set(and.getRight(), bool);
	}
	
	@Check
	public void infer(LogicalNot not)
	{
		set(not, bool);
		set(not.getExpression(), bool);
	}
	
	@Check
	public void infer(UnaryMinus minus)
	{
		group(minus, minus.getExpression());
	}
	
	@Check
	public void infer(Equality equality)
	{
		var left = equality.getLeft();
		var right = equality.getRight();
		
		group(left, right);
	}
	
	@Check
	public void infer(AdditiveExpression additive)
	{
		var left = additive.getLeft();
		var right = additive.getRight();
		
		group(additive, left, right);
	}
	
	@Check
	public void infer(MultiplicativeExpression multiplication)
	{
		var left = multiplication.getLeft();
		var right = multiplication.getRight();
		
		group(multiplication, left);
		set(right, float1);
	}
	
	@Check
	public void infer(Action action)
	{
		var left = action.getAccess();
		var kind = action.getKind();
		var right = action.getExpression();
		
		if (right == null) return;
		
		switch (kind)
		{
			case DECREASE:
				group(action,left,right);
				break;
			case DIVIDE:
				group(action, left);
				set(right, float1);
				break;
			case INCREASE:
				group(action,left,right);
				break;
			case MODULUS:
				group(action, left);
				set(right, float1);
				break;
			case MULTIPLY:
				group(action, left);
				set(right, float1);
				break;
			case SET:
				group(action,left,right);
				break;			
		}
	}
	
	@Check
	public void infer(Assignment assignment)
	{
		var kind = assignment.getKind();
		var left = assignment.getLeft();
		var right = assignment.getExpression();
		
		switch (kind)
		{
			case DECREASE:
				group(assignment,left,right);
				break;
			case DIVIDE:
				group(assignment, left);
				set(right, float1);
				break;
			case INCREASE:
				group(assignment,left,right);
				break;
			case MODULUS:
				group(assignment, left);
				set(right, float1);
				break;
			case MULTIPLY:
				group(assignment, left);
				set(right, float1);
				break;
			case SET:
				group(assignment,left,right);
				break;			
		}
	}
	
	@Check
	public void infer(Comparison comparison)
	{
		var left = comparison.getLeft();
		var right = comparison.getRight();
		
		set(comparison, bool);
		set(left, float1);
		set(right, float1);
	}
	
	@Check
	public void infer(Function call)
	{
		var function = call.getName();
		var parameters = call.getParameters();
		if (function.equals("random"))
		{
			if (parameters.size() != 1)
			{
				error("random takes a single float2 argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			set(call, float1);
			set(parameter0, float2);
		}
		else if (function.equals("cos")||function.equals("sin")||function.endsWith("tan")||function.endsWith("log")||function.endsWith("sqrt")||function.endsWith("exp"))
		{
			if (parameters.size() != 1)
			{
				error("math functions take a single float1 argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			set(call, float1);
			set(parameter0, float1);
		}
		else if (function.equals("create"))
		{
			if (parameters.size() != 1)
			{
				error("create takes a single entity argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			set(call, entity);
			set(parameter0, entity);
		}
		else if (function.equals("destroy"))
		{
			if (parameters.size() != 1)
			{
				error("destroy takes a single entity argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			set(call, none);
			set(parameter0, entity);
		}
		else if (function.equals("remove")||function.equals("add"))
		{
			if (parameters.size() != 2)
			{
				error("takes a type and an entity", call, FUNCTION__NAME);
				return;
			}
			var parameter1 = parameters.get(1);
			
			set(call, none);
			set(parameter1, entity);
		}
		else if (function.equals("join"))
		{
			if (parameters.size() != 2 && parameters.size() != 3 && parameters.size() != 4)
			{
				error("join takes two, three or four floats", call, FUNCTION__NAME);
				return;
			}
			var size = parameters.size();
			if (size == 2)
			{
				var parameter0 = parameters.get(0);
				var parameter1 = parameters.get(1);

				set(call, float2);
				set(parameter0, float1);
				set(parameter1, float1);
			}
			else if (size == 3)
			{
				var parameter0 = parameters.get(0);
				var parameter1 = parameters.get(1);
				var parameter2 = parameters.get(2);

				set(call, float3);
				set(parameter0, float1);
				set(parameter1, float1);
				set(parameter2, float1);
			}
			else if (size == 4)
			{
				var parameter0 = parameters.get(0);
				var parameter1 = parameters.get(1);
				var parameter2 = parameters.get(2);
				var parameter3 = parameters.get(3);

				set(call, float4);
				set(parameter0, float1);
				set(parameter1, float1);
				set(parameter2, float1);
				set(parameter3, float1);
			}
		}
		else if (function.equals("x")||function.equals("y")||function.equals("z")||function.equals("w"))
		{
			if (parameters.size() != 1)
			{
				error("component functions take a single floatn argument", call, FUNCTION__NAME);
				return;
			}
			set(call, float1);
		}
		else if (function.equals("quit"))
		{
			if (parameters.size() != 0)
			{
				error("quit takes no parameters",call, FUNCTION__NAME);
				return;
			}
		}
		else if (function.equals("has"))
		{
			if (parameters.size() != 2)
			{
				error ("has takes a type and an entity", call, FUNCTION__NAME);
				return;
			}
			set(call,bool);
			set(parameters.get(1), entity);
		}
		else
		{
			error("Function " + function + " does not exist in the standard library", call, FUNCTION__NAME);
		}
	}
	
	@Check
	public void check(End end)
	{
		solve();
		for (var group : groups)
		{
			for (var expression : group)
			{
				if (expression instanceof AccessExpression)
				{
					warning("Type undecidable", expression, EXPRESSION__EXPRESSION);
				}
			}
		}
		var modul = (Game) end.eContainer();
		for (var archetype : modul.getArchetypes())
		{
			for (var component : archetype.getComponents())
			{
				var name = component.getName();
				if (components.containsKey(name))
				{
					if (component instanceof AssetComponent && components.get(name).isNumeric())
					{
						error("Type mismatch, expected non-numeric but got " + components.get(name),component, COMPONENT__NAME);
					}
				}
				else
				{
					warning("Undecidable type", component, COMPONENT__NAME);
				}
			}
		}
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
					if (expressions.containsKey(expression))
					{
						repeat = true;
						
						var type = expressions.get(expression);
						for (var e : group)
						{
							set(e, type);
						}
						groups.remove(g);
						break;
					}
					else if (expression instanceof Variable)
					{
						var variable = (Variable) expression;
						var name = variable.getName();
						if (variables.containsKey(name))
						{
							repeat = true;
							
							var type = variables.get(name);
							for (var e : group)
							{
								set(e, type);
							}
							groups.remove(g);
							break;
						}
					}
					else if (expression instanceof AccessExpression)
					{
						var access = (AccessExpression) expression;
						var component = ((Variable)access.getRight()).getName();
						if (components.containsKey(component))
						{
							repeat = true;
							
							var type = components.get(component);
							for (var e : group)
							{
								set(e, type);
							}
							groups.remove(g);
							break;
						}
					}
				}
			}
			
			// expressions
			for (var expression : expressions.keySet())
			{
				if (expression instanceof Variable)
				{
					var variable = (Variable) expression;
					if (set(variable, expressions.get(expression)))
					{
						repeat = true;
					}
				}
				else if (expression instanceof AccessExpression)
				{
					var access = (AccessExpression) expression;
					if (set(access, expressions.get(expression)))
					{
						repeat = true;
					}
				}
			}
		}
	}
}
