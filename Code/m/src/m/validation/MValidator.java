package m.validation;

import static m.library.SimpleType.bool;
import static m.library.SimpleType.entity;
import static m.library.SimpleType.entityList;
import static m.library.SimpleType.float1;
import static m.library.SimpleType.float2;
import static m.library.SimpleType.float3;
import static m.library.SimpleType.float4;
import static m.library.SimpleType.none;
import static m.library.SimpleType.tag;
import static m.m.MPackage.Literals.ARCHETYPE__NAME;
import static m.m.MPackage.Literals.ARCHETYPE__BASE;
import static m.m.MPackage.Literals.CELL__COMPONENT;
import static m.m.MPackage.Literals.CELL__ENTITY;
import static m.m.MPackage.Literals.FORALL__VARIABLE;
import static m.m.MPackage.Literals.FUNCTION__NAME;
import static m.m.MPackage.Literals.IMPLICIT_SET__VARIABLE;
import static m.m.MPackage.Literals.JOIN__ENTRIES;
import static m.m.MPackage.Literals.VARIABLE__NAME;
import static m.m.MPackage.Literals.SYSTEM__NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.library.Component;
import m.library.MagicName;
import m.library.SimpleType;
import m.m.AdditiveExpression;
import m.m.Archetype;
import m.m.Assignment;
import m.m.Cell;
import m.m.Comparison;
import m.m.End;
import m.m.Equality;
import m.m.ExplicitSet;
import m.m.Expression;
import m.m.Forall;
import m.m.Function;
import m.m.Game;
import m.m.ImplicitSet;
import m.m.Iteration;
import m.m.Join;
import m.m.LogicalAnd;
import m.m.LogicalNot;
import m.m.LogicalOr;
import m.m.MultiplicativeExpression;
import m.m.Selection;
import m.m.SetExpression;
import m.m.Variable;

public class MValidator extends AbstractMValidator 
{
	public static HashMap<Expression,SimpleType> expressions  = new HashMap<>();
	public static HashMap<String,SimpleType> components  = new HashMap<>();
	public static HashMap<String,SimpleType> variables  = new HashMap<>();
	public static ArrayList<ArrayList<Expression>> groups  = new ArrayList<>();

	
	@Check
	public void unique(Archetype archetype)
	{
		var game = (Game) archetype.eContainer();
		
		var amount = 0;
		
		for (var e : game.getArchetypes())
		{
			var name = e.getName();
			if (archetype.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated archetype", archetype, ARCHETYPE__NAME);
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
			error("Repeated system", system, SYSTEM__NAME);
		}
	}
	
	@Check
	public void existsBase(Archetype archetype)
	{
		var base = archetype.getBase();
		if (base == null) return;
		
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
	public void uniqueComponent(Archetype archetype)
	{
		var game = (Game) archetype.eContainer();
		
		var set = new HashSet<String>();
		var current = archetype;
		while (current != null)
		{
			for (var component : current.getComponents())
			{
				if (!set.contains(component))
				{
					set.add(component);
				}
				else
				{
					error("Repeated component: "+component, ARCHETYPE__NAME);
				}
			}
			var next = current.getBase();
			current = null;
			for (var a : game.getArchetypes())
			{
				if (a.getName().equals(next))
				{
					current = a;
					break;
				}
			}
			
		}
	}
	
	
	
	
	
	
	@Check
	public void clear(Game modul)
	{
		components.clear();
		variables.clear();
		expressions.clear();
		groups.clear();
		
		for (var entry : Component.values())
		{
			components.put(entry.toString(), entry.type);
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

	private boolean setComponent(String name, SimpleType type, EObject obj, EStructuralFeature feature)
	{
		var isNew = !components.containsKey(name);
		if (isNew)
		{
			components.put(name, type);			
		}
		else
		{
			if (components.get(name) != type)
			{
				error("Expected type " + components.get(name) + " but got " + type.toString(), obj, feature);
			}
		}
		return isNew;
	}
	
	private boolean setVariable(String name, SimpleType type, EObject obj, EStructuralFeature feature)
	{
		var isNew = !variables.containsKey(name);
		if (isNew)
		{
			variables.put(name, type);
		}
		else
		{
			if (variables.get(name) != type)
			{
				error("Expected type " + variables.get(name) + " but got " + type.toString(), obj, feature);
			}
		}
		return isNew;
	}
	
	private boolean setExpression(Expression expression, SimpleType type)
	{
		var isNew = !expressions.containsKey(expression);
		if (isNew)
		{
			expressions.put(expression, type);			
		}
		else
		{
			if (expressions.get(expression) != type)
			{
				error("Expected type " + expressions.get(expression) + " but got " + type.toString(), expression, null);
			}
		}
		return isNew;
	}
	
	
	
	
	
	
	
	@Check
	public void magicNames(Archetype archetype)
	{
		for (var component : archetype.getComponents())
		{
			magic(component, archetype, ARCHETYPE__NAME);
		}		
	}
	
	@Check
	public void infer(Cell cell)
	{
		var entity = cell.getEntity();
		var component = cell.getComponent();
		
		setVariable(entity, SimpleType.entity, cell, CELL__ENTITY);
		magic(component, cell, CELL__COMPONENT);
	}
	
	private void magic(String component, EObject o, EStructuralFeature feature)
	{
		for (var magicName : MagicName.values())
		{
			if (component.endsWith(magicName.ending))
			{
				setComponent(component, magicName.type, o, feature);
			}
		}
	}
	
	
	
	
	@Check
	public void infer(Selection selection)
	{
		setExpression(selection.getCondition(), bool);
	}
	
	@Check
	public void infer(Iteration iteration)
	{
		setExpression(iteration.getCondition(), bool);
	}
	
	@Check
	public void infer(Forall forall)
	{
		var variable = forall.getVariable();
		
		setVariable(variable, entity, forall, FORALL__VARIABLE);
	}
	
	@Check
	public void infer(ImplicitSet set)
	{
		setVariable(set.getVariable(), entity, set, IMPLICIT_SET__VARIABLE);
		setExpression(set.getPredicate(), bool);	
		setExpression(set, entityList);
	}
	
	@Check
	public void infer(ExplicitSet set)
	{
		for (var element : set.getElements())
		{
			setExpression(element, entity);
		}
		setExpression(set, entityList);
	}
	
	@Check
	public void infer(SetExpression expression)
	{
		var left = expression.getLeft();
		var right = expression.getRight();
		var kind = expression.getKind();
		
		switch(kind)
		{
		case MEMBERSHIP:
			setExpression(left, entity);
			setExpression(right, entityList);
			setExpression(expression, bool);
			break;
		case UNION:
			setExpression(left, entityList);
			setExpression(right, entityList);
			setExpression(expression, entityList);
			break;
		case DIFFERENCE:
			setExpression(left, entityList);
			setExpression(right, entityList);
			setExpression(expression, entityList);
			break;
		case INTERSECTION:
			setExpression(left, entityList);
			setExpression(right, entityList);
			setExpression(expression, entityList);
			break;
		default:
			break;
		}
	}
	
	
	
	@Check
	public void infer(LogicalOr or)
	{
		setExpression(or, bool);
		setExpression(or.getLeft(), bool);
		setExpression(or.getRight(), bool);
	}
	
	@Check
	public void infer(LogicalAnd and)
	{
		setExpression(and, bool);
		setExpression(and.getLeft(), bool);
		setExpression(and.getRight(), bool);
	}
	
	@Check
	public void infer(LogicalNot not)
	{
		setExpression(not, bool);
		setExpression(not.getExpression(), bool);
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
		setExpression(right, float1);
	}
	
	@Check
	public void infer(Join join)
	{
		var entries = join.getEntries();
		for (var entry : entries)
		{
			setExpression(entry, float1);
		}
		if (entries.size() == 2)
		{
			setExpression(join, float2);
		}
		else if (entries.size() == 3)
		{
			setExpression(join, float3);
		}
		else if (entries.size() == 4)
		{
			setExpression(join, float4);
		}
		else
		{
			error("Only 4-vectors are supported", join, JOIN__ENTRIES);
		}
	}
	
	@Check
	public void infer(Assignment assignment)
	{
		var left = assignment.getAtom();
		var right = assignment.getExpression();
		group(left,right);
	}
	
	@Check
	public void infer(Comparison comparison)
	{
		var left = comparison.getLeft();
		var right = comparison.getRight();
		
		setExpression(comparison, bool);
		setExpression(left, float1);
		setExpression(right, float1);
	}
	
	@Check
	public void infer(Function call)
	{
		var function = call.getName();
		var parameters = call.getArguments();
		if (function.equals("random"))
		{
			if (parameters.size() != 1)
			{
				error("random takes a single float2 argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			setExpression(call, float1);
			setExpression(parameter0, float2);
		}
		else if (function.equals("cos")||function.equals("sin")||function.endsWith("tan")||function.endsWith("log")||function.endsWith("sqrt")||function.endsWith("exp"))
		{
			if (parameters.size() != 1)
			{
				error("math functions take a single float1 argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			setExpression(call, float1);
			setExpression(parameter0, float1);
		}
		else if (function.equals("create"))
		{
			if (parameters.size() != 1)
			{
				error("create takes a single entity argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			setExpression(call, entity);
			setExpression(parameter0, entity);
		}
		else if (function.equals("destroy"))
		{
			if (parameters.size() != 1)
			{
				error("destroy takes a single entity argument", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = parameters.get(0);
			
			setExpression(call, none);
			setExpression(parameter0, entity);
		}
		else if (function.equals("remove")||function.equals("add"))
		{
			if (parameters.size() != 2)
			{
				error("takes a type and an entity", call, FUNCTION__NAME);
				return;
			}
			var parameter1 = parameters.get(1);
			
			setExpression(call, none);
			setExpression(parameter1, entity);
		}
		else if (function.equals("has"))
		{
			if (parameters.size() != 2)
			{
				error("takes a type and an entity", call, FUNCTION__NAME);
				return;
			}
			var parameter0 = (Variable) parameters.get(0);
			var parameter1 = parameters.get(1);
			
			setExpression(call, bool);
			setComponent(parameter0.getName(), tag, call, FUNCTION__NAME);
			setExpression(parameter1, entity);
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

				setExpression(call, float2);
				setExpression(parameter0, float1);
				setExpression(parameter1, float1);
			}
			else if (size == 3)
			{
				var parameter0 = parameters.get(0);
				var parameter1 = parameters.get(1);
				var parameter2 = parameters.get(2);

				setExpression(call, float3);
				setExpression(parameter0, float1);
				setExpression(parameter1, float1);
				setExpression(parameter2, float1);
			}
			else if (size == 4)
			{
				var parameter0 = parameters.get(0);
				var parameter1 = parameters.get(1);
				var parameter2 = parameters.get(2);
				var parameter3 = parameters.get(3);

				setExpression(call, float4);
				setExpression(parameter0, float1);
				setExpression(parameter1, float1);
				setExpression(parameter2, float1);
				setExpression(parameter3, float1);
			}
		}
		else if (function.equals("x")||function.equals("y")||function.equals("z")||function.equals("w"))
		{
			if (parameters.size() != 1)
			{
				error("component functions take a single floatn argument", call, FUNCTION__NAME);
				return;
			}
			setExpression(call, float1);
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
			setExpression(call,bool);
			setExpression(parameters.get(1), entity);
		}
		else if (function.equals("empty"))
		{
			if (parameters.size() != 1)
			{
				error ("empty takes a list argument", call, FUNCTION__NAME);
			}
			setExpression(call, bool);
			setExpression(parameters.get(0), entityList);
		}
		else if (function.equals("addTo"))
		{
			
		}
		else if (function.equals("removeFrom"))
		{
			
		}
		else
		{
			error("Function " + function + " does not exist in the standard library", call, FUNCTION__NAME);
		}
	}
	
	
	@Check
	public void check(End end)
	{
		var game = (Game) end.eContainer();
		report(game);
	}
	
	
	private void report(Game game)
	{
		solve();
		for (var group : groups)
		{
			for (var expression : group)
			{
				if (expression instanceof Cell)
				{
					warning("Type undecidable", expression, CELL__COMPONENT);
				}
			}
		}
		for (var archetype : game.getArchetypes())
		{
			for (var component : archetype.getComponents())
			{				
				if (!components.containsKey(component))
				{
					warning("Undecidable type: " + component, archetype, ARCHETYPE__NAME);
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
							setExpression(e, type);
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
								setExpression(e, type);
							}
							groups.remove(g);
							break;
						}
					}
					else if (expression instanceof Cell)
					{
						var access = (Cell) expression;
						var component = access.getComponent();
						if (components.containsKey(component))
						{
							repeat = true;
							
							var type = components.get(component);
							for (var e : group)
							{
								setExpression(e, type);
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
					if (setVariable(variable.getName(), expressions.get(expression), variable, VARIABLE__NAME))
					{
						repeat = true;
					}
				}
				else if (expression instanceof Cell)
				{
					var cell = (Cell) expression;
					if (setComponent(cell.getComponent(), expressions.get(expression), cell, CELL__COMPONENT))
					{
						repeat = true;
					}
				}
			}
		}
	}
}
