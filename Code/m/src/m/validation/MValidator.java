package m.validation;

import static m.validation.Type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;

import m.m.Asset;
import m.m.Component;
import m.m.End;
import m.m.Entity;
import m.m.EntityVariable;
import m.m.Loop;
import m.m.MPackage;
import m.m.Modul;
import m.m.System;
import m.m.Vector;
import m.modular.Addition;
import m.modular.Assignment;
import m.modular.Block;
import m.modular.Expression;
import m.modular.FunctionCall;
import m.modular.Greater;
import m.modular.Iteration;
import m.modular.ModularPackage;
import m.modular.Multiplication;
import m.modular.Procedure;
import m.modular.Selection;
import m.modular.Variable;

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
		var entity = (Entity) component.eContainer();
		
		var amount = 0;
		
		for (var c : entity.getComponents())
		{
			var name = c.getName();
			if (component.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated component",MPackage.Literals.COMPONENT__NAME);
		}
	}
	
	@Check
	public void unique(Entity entity)
	{
		var module = (Modul) entity.eContainer();
		
		var amount = 0;
		
		for (var e : module.getEntities())
		{
			var name = e.getName();
			if (entity.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated entity",MPackage.Literals.ENTITY__NAME);
		}
	}
	
	@Check
	public void unique(m.m.System system)
	{
		var module = (Modul) system.eContainer();
		
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
			error("Repeated procedure",MPackage.Literals.SYSTEM__NAME);
		}
	}
	
	@Check
	public void unique(Procedure procedure)
	{
		var module = (Modul) procedure.eContainer();
		
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
			error("Repeated system",ModularPackage.Literals.PROCEDURE__NAME);
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
					error("Repeated argument "+arguments.get(i), ModularPackage.Literals.PROCEDURE__NAME);
					return;
				}
			}
		}
	}
	
	@Check
	public void uniqueTags(Loop loop)
	{
		var tags = loop.getTags();
		
		for (var i = 0; i < tags.size(); i++)
		{
			var itag = tags.get(i);
			for (var j = i+1; j < tags.size(); j++)
			{
				if (itag.equals(tags.get(j)))
				{
					error("Repeated tag "+itag, MPackage.Literals.LOOP__ENTITY);
					return;
				}
			}
		}
	}
	
	@Check
	public void uniqueEntity(Loop loop)
	{
		var myEntity = loop.getEntity();
		var container = loop.eContainer();
		EObject current = loop;
		
		while (!(container instanceof Modul))
		{
			if (container instanceof Block)
			{
				var block = (Block) container;
				if (container instanceof Loop)
				{
					var l = (Loop) container;
					if (l.getEntity().equals(myEntity))
					{
						error("Already exists entity " + myEntity + " in the scope", MPackage.Literals.LOOP__ENTITY);
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
							var entity = ((EntityVariable)assignment.getVariable()).getEntity();
							if (entity.equals(myEntity))
							{
								error("Already exists entity " + myEntity + " in the scope", MPackage.Literals.LOOP__ENTITY);
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
	public void scope(EntityVariable variable)
	{
		var myEntity = variable.getEntity();
		var container = variable.eContainer();
		EObject current = variable;
		
		if (container instanceof Assignment)
		{
			if (((Assignment) container).getVariable() == variable)
			{
				return;
			}
		}
		
		while (!(container instanceof Modul))
		{
			if (container instanceof Block)
			{
				var block = (Block) container;
				if (container instanceof Loop)
				{
					var l = (Loop) container;
					if (l.getEntity().equals(myEntity))
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
							var entity = ((EntityVariable)assignment.getVariable()).getEntity();
							if (entity.equals(myEntity))
							{
								return;
							}
						}
					}
				}
			}
			current = container;
			container = container.eContainer();
		}
		error("Entity " + myEntity + " undefined in the scope", MPackage.Literals.ENTITY_VARIABLE__ENTITY);
	}
	
	@Check
	public void exists(FunctionCall call)
	{
		var name = call.getFunction();
		var functions = new ArrayList<String>();
		functions.add("create");
		functions.add("destroy");
		functions.add("add");
		functions.add("remove");
		functions.add("join");
		functions.add("random");
		functions.add("sin");
		functions.add("cos");
		
		if (!functions.contains(name))
		{
			error("Function " + name + " is not defined", ModularPackage.Literals.FUNCTION_CALL__FUNCTION);
		}
	}
	
	@Check
	public void existsBase(Entity entity)
	{
		var base = entity.getBase();
		if (base != null)
		{
			var module = (Modul) entity.eContainer();
			for (var e : module.getEntities())
			{
				if (e.getName().equals(base))
				{
					return;
				}
			}
			error("The base entity is not declared in this module", MPackage.Literals.ENTITY__BASE);
		}
	}
	
	@Check
	public void acyclic(Entity entity)
	{
		var base = entity.getBase();
		var module = (Modul) entity.eContainer();
		var entities = module.getEntities();
		
		var visited = new ArrayList<String>();
		visited.add(entity.getName());
		
		while (base != null)
		{
			for (var e : entities)
			{
				if (e.getName().equals(base))
				{
					if (visited.contains(e.getName()))
					{
						error("Cyclic base entity dependency", MPackage.Literals.ENTITY__BASE);
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
	public void type(Vector vector)
	{
		if (vector.getEntries().size() > 4)
		{
			error("Vectors can have up to four entries", MPackage.Literals.VECTOR__ENTRIES,"More than four entries");
		}
	}
	
	@Check
	public void clean(Modul modul)
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
				error("Expected type " + components.get(name) + " but got " + type.toString(), component, MPackage.Literals.COMPONENT__NAME);
			}
		}
		else
		{
			components.put(name, type);
		}
	}
	
	private boolean set(EntityVariable variable, Type type)
	{
		var name = variable.getEntity();
		var component = variable.getComponent();
		
		if (component == null)
		{
			if (variables.containsKey(name))
			{
				if (variables.get(name) != type)
				{
					error("Expected " + variables.get(name) + ", got " + type, variable, ModularPackage.Literals.EXPRESSION__EXPRESSION);
				}
			}
			else
			{
				variables.put(name, type);
				return true;
			}
		}
		else
		{
			if (components.containsKey(component))
			{
				if (components.get(component) != type)
				{
					error("Type conflict: This variable cannot be " + components.get(component) + " and " + type, variable, ModularPackage.Literals.EXPRESSION__EXPRESSION);
				}
			}
			else
			{
				components.put(component, type);
				return true;
			}
		}
		return false;
	}
	
	private void set(Expression expression, Type type)
	{
		if (expressions.containsKey(expression))
		{
			if (expressions.get(expression) != type)
			{
				error("Expected type " + expressions.get(expression) + " but got " + type.toString(), expression, ModularPackage.Literals.EXPRESSION__EXPRESSION);
			}
		}
		else
		{
			expressions.put(expression, type);
		}
	}
	
	
	@Check
	public void infer(Component component)
	{
		var value = component.getValue();
		if (value instanceof Vector)
		{
			var entries = ((Vector) value).getEntries();
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
	}
	
	@Check
	public void infer(EntityVariable variable)
	{
		var component = variable.getComponent();
		if (component != null && component.endsWith("Range"))
		{
			set(variable, float1);
			var tag = component.substring(0,component.lastIndexOf("Range"));
			if (components.containsKey(tag))
			{
				if (components.get(tag) != Type.input)
				{
					error ("Expected " + components.get(tag) + ", got input", variable, MPackage.Literals.ENTITY_VARIABLE__COMPONENT);
				}
			}
			else
			{
				components.put(tag, Type.tag);
			}
		}
	}
	
	@Check
	public void infer(Loop loop)
	{
		var entity = loop.getEntity();
		var list = (EntityVariable) loop.getCollection();
		var tags = loop.getTags();
		
		if (variables.containsKey(entity))
		{
			if (variables.get(entity) != Type.entity)
			{
				error ("Expected " + variables.get(entity) + ", got entity", loop, MPackage.Literals.LOOP__ENTITY);
			}
		}
		else
		{
			variables.put(entity, Type.entity);
		}
		if (list != null)
		{
			set(list, Type.entityList);
		}
		for (var tag : tags)
		{
			if (components.containsKey(tag))
			{
				if (components.get(tag) != Type.entity)
				{
					error ("Expected " + components.get(tag) + ", got tag", loop, MPackage.Literals.LOOP__TAGS);
				}
			}
			else
			{
				components.put(tag, Type.tag);
			}
		}
	}
	
	@Check
	public void infer(Expression expression)
	{
		if (expression instanceof Assignment)
		{
			var assignment = (Assignment) expression;
			var kind = assignment.getKind();
			var left = assignment.getVariable();
			var right = assignment.getExpression();
			
			switch (kind)
			{
			case AND:
				//expressions.get(float1).add(left);
				//expressions.get(float1).add(right);
				break;
			case DECREASE:
				group(assignment,left,right);
				break;
			case DIVIDE:
				//expressions.get(float1).add(right);
				break;
			case INCREASE:
				group(assignment,left,right);
				break;
			case MODULUS:
				//expressions.get(float1).add(right);
				break;
			case MULTIPLY:
				//expressions.get(float1).add(right);
				break;
			case OR:
				//expressions.get(float1).add(left);
				//expressions.get(float1).add(right);
				break;
			case SET:
				group(assignment,left,right);
				break;
			case SHIFT_LEFT:
				//expressions.get(float1).add(left);
				//expressions.get(float1).add(right);
				break;
			case SHIFT_RIGHT:
				//expressions.get(float1).add(left);
				//expressions.get(float1).add(right);
				break;
			case XOR:
				//expressions.get(float1).add(left);
				//expressions.get(float1).add(right);
				break;			
			}
		}
		else if (expression instanceof Multiplication)
		{
			var multiplication = (Multiplication) expression;
			var left = multiplication.getLeft();
			var right = multiplication.getRight();
			group(multiplication, left);
			set(right, float1);
		}
		else if (expression instanceof FunctionCall)
		{
			var call = (FunctionCall) expression;
			var function = call.getFunction();
			if (function.equals("random"))
			{
				var parameter0 = call.getParameters().get(0);
				
				set(expression, float1);
				set(parameter0, float2);
			}
			else if (function.equals("cos")||function.equals("sin")||function.endsWith("tan"))
			{
				var parameter0 = call.getParameters().get(0);
				
				set(expression, float1);
				set(parameter0, float1);
			}
			else if (function.equals("create")||function.equals("destroy"))
			{
				var parameter0 = call.getParameters().get(0);
				
				set(parameter0, entity);
			}
			else if (function.equals("remove")||function.equals("add"))
			{
				var parameter1 = call.getParameters().get(1);
				
				set(parameter1, entity);
			}
			else if (function.equals("join"))
			{
				var size = call.getParameters().size();
				if (size == 1)
				{
					var parameter0 = call.getParameters().get(0);
					
					set(expression, float1);
					set(parameter0, float1);
				}
				else if (size == 2)
				{
					var parameter0 = call.getParameters().get(0);
					var parameter1 = call.getParameters().get(1);

					set(expression, float2);
					set(parameter0, float1);
					set(parameter1, float1);
				}
				else if (size == 3)
				{
					var parameter0 = call.getParameters().get(0);
					var parameter1 = call.getParameters().get(1);
					var parameter2 = call.getParameters().get(2);

					set(expression, float3);
					set(parameter0, float1);
					set(parameter1, float1);
					set(parameter2, float1);
				}
				else if (size == 4)
				{
					var parameter0 = call.getParameters().get(0);
					var parameter1 = call.getParameters().get(1);
					var parameter2 = call.getParameters().get(2);
					var parameter3 = call.getParameters().get(3);

					set(expression, float4);
					set(parameter0, float1);
					set(parameter1, float1);
					set(parameter2, float1);
					set(parameter3, float1);
				}
			}
		}
		else if (expression instanceof Greater)
		{
			var greater = (Greater) expression;
			var left = greater.getLeft();
			var right = greater.getRight();
			
			set(expression, bool);
			set(left, float1);
			set(right, float1);
		}
	}
	
	@Check
	public void check(End end)
	{
		solve();
		for (var key : expressions.keySet())
		{
			warning(expressions.get(key).toString(),key, ModularPackage.Literals.EXPRESSION__EXPRESSION);
		}
		var modul = (Modul) end.eContainer();
		for (var entity : modul.getEntities())
		{
			for (var component : entity.getComponents())
			{
				var name = component.getName();
				if (components.containsKey(name))
				{
					warning(components.get(name).toString(), component, MPackage.Literals.COMPONENT__NAME);
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
					else if (expression instanceof EntityVariable)
					{
						var variable = (EntityVariable) expression;
						var name = variable.getEntity();
						var component = variable.getComponent();
						if (component == null)
						{
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
						else
						{
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
			}
			
			// expressions
			for (var expression : expressions.keySet())
			{
				if (expression instanceof EntityVariable)
				{
					var variable = (EntityVariable) expression;
					if (set(variable, expressions.get(expression)))
					{
						repeat = true;
					}
				}
			}
		}
	}
	
	/*
	@Check
	public void infer(EntityVariable variable)
	{
		var component = variable.getComponent();
		if (component != null)
		{
			if (component.endsWith("Range"))
			{
				set(component.substring(0, component.indexOf("Range")), input);
				set(component, float1);
			}
			else if (component.endsWith("Vector"))
			{
				set(component.substring(0, component.indexOf("Vector")), input);
				set(component, float2);
			}
		}
	}
	
	@Check
	public void infer(Loop loop)
	{
		var entity = loop.getEntity();
		set(entity, Type.entity);
		
		var collection = (EntityVariable) loop.getCollection();
		if (collection != null)
		{
			set(collection.getEntity(), Type.entity);
			set(collection.getComponent(), Type.entityList);
		}
		
		var tags = loop.getTags();
		for (var t : tags)
		{
			set(t, Type.tag);
		}
	}
	
	@Check
	public void infer(Selection selection)
	{
		for (var branch : selection.getBranches())
		{
			var condition = branch.getCondition();
			if (condition != null)
			{
				set(condition, Type.bool);
			}
		}
	}
	
	@Check
	public void infer(Iteration iteration)
	{
		var condition = iteration.getCondition();
		set(condition, Type.bool);
	}
	
	@Check
	public void infer(FunctionCall functionCall)
	{
		var name = functionCall.getName();
		var parameters = functionCall.getParameters();
		
		var mathFunctions = new ArrayList<String>();
		mathFunctions.add("sin");
		mathFunctions.add("cos");
		mathFunctions.add("tan");
		mathFunctions.add("exp");
		mathFunctions.add("log");
		
		if (mathFunctions.contains(name))
		{
			set(parameters.get(0), float1);				
		}
		else if (name.equals("random"))
		{
			set(parameters.get(0), float2);
		}
		else if (name.equals("create"))
		{
			set(parameters.get(0), entity);
		}
		else if (name.equals("destroy"))
		{
			set(parameters.get(0), entity);
		}
		else if (name.equals("add")||name.equals("remove"))
		{
			set(parameters.get(1), entity);
		}
	}
	
	@Check
	public void infer(Assignment assignment)
	{
		var variable = (EntityVariable) assignment.getVariable();
		var name = variable.getEntity();
		if (variable.getComponent() != null)
		{
			name = variable.getComponent();
		}
		var expression = assignment.getExpression();
		switch(assignment.getKind())
		{
			case AND: 
			{
				set(name, float1);
				set(expression,float1);
				break;
			}
			case DECREASE: 
			{
				group(name, expression);
				break;
			}
			case DIVIDE: 
			{
				set(expression,float1);
				break;
			}
			case INCREASE:
			{
				group(name, expression);
				break;
			}
			case MODULUS: 
			{
				set(name, float1);
				set(expression,float1);
				break;
			}
			case MULTIPLY: 
			{
				set(expression,float1);
				break;
			}
			case OR: 
			{
				set(name, float1);
				set(expression,float1);
				break;
			}
			case SET: 
			{
				group(name, expression);
				break;
			}
			case SHIFT_LEFT: 
			{
				set(name, float1);
				set(expression,float1);
				break;
			}
			case SHIFT_RIGHT: 
			{
				set(name, float1);
				set(expression,float1);
				break;
			}
			case XOR: 
			{
				set(name,float1);
				set(expression,float1);
				break;
			}
		}
	}
	
	private void set(Expression expression, Type type)
	{
		
	}
	
	private void group(String name, Expression expression)
	{
		
	}
	
	@Check
	public void check(Addition addition)
	{
		var left = (EntityVariable) addition.getLeft();
		var right = (EntityVariable) addition.getRight();
		var leftComponent = left.getComponent();
		var rightComponent = right.getComponent();
		
		if (!types.containsKey(leftComponent)||!types.containsKey(rightComponent))
		{
			warning("components not typed", ModularPackage.Literals.ADDITION__LEFT);
		}
		var l = types.get(leftComponent);
		var r = types.get(rightComponent);
		
		if (!isNumeral(l))
		{
			error("left is not numeral", ModularPackage.Literals.ADDITION__LEFT);
		}
		
		if (l != r)
		{
			error("types dont match", ModularPackage.Literals.ADDITION__LEFT);
		}
	}
	
	@Check
	public void check(Assignment assignment)
	{
		var left = (EntityVariable) assignment.getVariable();
		var name = left.getEntity();
		if (left.getComponent() != null)
		{
			
		}
	}
	
	private boolean isNumeral(Type t)
	{
		return t == float1 || t == float2 || t == Type.float3 || t == Type.float4;
	}
	
	private void set(String name, Type type)
	{
		if (types.containsKey(name))
		{
			if (types.get(name) != type)
			{
				error("Type error, expected " + types.get(name) + " but was " + type,MPackage.Literals.COMPONENT__NAME);
			}
		}
		else
		{
			types.put(name, type);
		}
	}*/
	
}
