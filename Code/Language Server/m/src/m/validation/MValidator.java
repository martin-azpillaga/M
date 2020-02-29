package m.validation;

import static m.library.SimpleType.*;
import static game.GamePackage.Literals.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.library.Component;
import m.library.SimpleType;
import game.Addition;
import game.Archetype;
import game.Assignment;
import game.Brackets;
import game.Cardinal;
import game.Cell;
import game.Comparison;
import game.End;
import game.Equality;
import game.ExplicitSet;
import game.Expression;
import game.Forall;
import game.Function;
import game.Game;
import game.ImplicitSet;
import game.Iteration;
import game.Join;
import game.And;
import game.LogicalNot;
import game.Or;
import game.Multiplication;
import game.Selection;
import game.SetExpression;
import game.Variable;

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
	public void unique(game.Component component)
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
			error("Repeated component", component, COMPONENT__NAME);
		}
	}
	
	@Check
	public void unique(game.System system)
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
		error("Base entity "+ base + "undefined", ARCHETYPE__BASE);
	}
	
	@Check
	public void acyclicBase(Archetype archetype)
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
						error("Cyclic archetype dependency", ARCHETYPE__BASE);
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
				error("Incompatible types: " + components.get(name) + " and " + type.toString(), obj, feature);
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
				error("Incompatible types: " + variables.get(name) + " and " + type.toString(), obj, feature);
			}
		}
		return isNew;
	}
	
	private boolean setExpression(Expression expression, SimpleType type, EObject o, EStructuralFeature feature)
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
				error("Incompatible types: " + expressions.get(expression) + " and " + type.toString(), o, feature);
			}
		}
		return isNew;
	}
	
	
	
	
	
	
	
	
	
	@Check
	public void infer(Cell cell)
	{
		var entity = cell.getEntity();
		
		setVariable(entity, SimpleType.entity, cell, CELL__ENTITY);
	}	
	
	@Check
	public void infer(Selection selection)
	{
		setExpression(selection.getCondition(), bool, selection, SELECTION__CONDITION);
	}
	
	@Check
	public void infer(Iteration iteration)
	{
		setExpression(iteration.getCondition(), bool, iteration, ITERATION__CONDITION);
	}
	
	@Check
	public void infer(Forall forall)
	{
		var variable = forall.getVariable();
		var condition = forall.getCondition();
		
		if (condition != null)
		{
			setExpression(condition, bool, forall, FORALL__CONDITION);
		}
		
		setVariable(variable, entity, forall, FORALL__VARIABLE);
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
		setExpression(or, bool, or, OR__LEFT);
		setExpression(or.getLeft(), bool, or, OR__LEFT);
		setExpression(or.getRight(), bool, or, AND__LEFT);
	}
	
	@Check
	public void infer(And and)
	{
		setExpression(and, bool, and, AND__LEFT);
		setExpression(and.getLeft(), bool, and, AND__LEFT);
		setExpression(and.getRight(), bool, and, AND__LEFT);
	}
	
	@Check
	public void infer(LogicalNot not)
	{
		setExpression(not, bool, not, LOGICAL_NOT__EXPRESSION);
		setExpression(not.getExpression(), bool, not, LOGICAL_NOT__EXPRESSION);
	}
	
	@Check
	public void infer(Equality equality)
	{
		var left = equality.getLeft();
		var right = equality.getRight();
		
		group(left, right);
	}
	
	
	
	
	@Check
	public void infer(Addition additive)
	{
		var left = additive.getLeft();
		var right = additive.getRight();
		
		group(additive, left, right);
	}
	
	@Check
	public void infer(Multiplication multiplication)
	{
		var left = multiplication.getLeft();
		var right = multiplication.getRight();
		
		group(multiplication, left);
		setExpression(right, float1, multiplication, MULTIPLICATION__RIGHT);
	}
	
	@Check
	public void infer(Comparison comparison)
	{
		var left = comparison.getLeft();
		var right = comparison.getRight();
		
		setExpression(comparison, bool, comparison, COMPARISON__KIND);
		setExpression(left, float1, comparison, COMPARISON__LEFT);
		setExpression(right, float1, comparison, COMPARISON__RIGHT);
	}
	
	
	
	
	@Check
	public void infer(ImplicitSet set)
	{
		setVariable(set.getVariable(), entity, set, IMPLICIT_SET__VARIABLE);
		setExpression(set.getPredicate(), bool, set, IMPLICIT_SET__PREDICATE);	
		setExpression(set, entityList, set, IMPLICIT_SET__VARIABLE);
	}
	
	@Check
	public void infer(ExplicitSet set)
	{
		for (var element : set.getElements())
		{
			setExpression(element, entity, set, EXPLICIT_SET__ELEMENTS);
		}
		setExpression(set, entityList, set, EXPLICIT_SET__ELEMENTS);
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
			setExpression(left, entity, expression, SET_EXPRESSION__LEFT);
			setExpression(right, entityList, expression, SET_EXPRESSION__RIGHT);
			setExpression(expression, bool, expression, SET_EXPRESSION__KIND);
			break;
		case UNION:
			setExpression(left, entityList, expression, SET_EXPRESSION__LEFT);
			setExpression(right, entityList, expression, SET_EXPRESSION__RIGHT);
			setExpression(expression, entityList, expression, SET_EXPRESSION__KIND);
			break;
		case DIFFERENCE:
			setExpression(left, entityList, expression, SET_EXPRESSION__LEFT);
			setExpression(right, entityList, expression, SET_EXPRESSION__RIGHT);
			setExpression(expression, entityList, expression, SET_EXPRESSION__KIND);
			break;
		case INTERSECTION:
			setExpression(left, entityList, expression, SET_EXPRESSION__LEFT);
			setExpression(right, entityList, expression, SET_EXPRESSION__RIGHT);
			setExpression(expression, entityList, expression, SET_EXPRESSION__KIND);
			break;
		default:
			break;
		}
	}
	
	@Check
	public void infer(Cardinal cardinal)
	{
		setExpression(cardinal, float1, cardinal, CARDINAL__EXPRESSION);
		setExpression(cardinal.getExpression(), entityList, cardinal, CARDINAL__EXPRESSION);
	}
	
	@Check
	public void infer(Join join)
	{
		var entries = join.getEntries();
		for (var entry : entries)
		{
			setExpression(entry, float1, join, JOIN__ENTRIES);
		}
		if (entries.size() == 2)
		{
			setExpression(join, float2, join, JOIN__ENTRIES);
		}
		else if (entries.size() == 3)
		{
			setExpression(join, float3, join, JOIN__ENTRIES);
		}
		else if (entries.size() == 4)
		{
			setExpression(join, float4, join, JOIN__ENTRIES);
		}
		else
		{
			error("Only 4-vectors are supported", join, JOIN__ENTRIES);
		}
	}
	
	
	
	
	
	@Check
	public void infer(Function call)
	{
		var function = call.getName();
		var arguments = call.getArguments();
		
		var found = false;
		
		for (var engineFunction : m.library.Function.values())
		{
			if (function.equals(engineFunction.name))
			{
				found = true;
				
				if (function.equals(m.library.Function.has.name))
				{
					setComponent(((Variable)arguments.get(0)).getName(), SimpleType.tag, call, FUNCTION__NAME);
				}
				
				var parameters = engineFunction.type.parameters;
				if (arguments.size() != parameters.size())
				{
					error(function + " takes " + engineFunction.type, call, FUNCTION__NAME);
					break;
				}
				for (var i = 0; i < engineFunction.type.parameters.size(); i++)
				{
					var simpleType = (SimpleType) parameters.get(i);
					if (simpleType != any)
					{
						setExpression(arguments.get(i), simpleType, call, FUNCTION__NAME);
					}
				}
				
				break;
			}
		}
		
		if (!found)
		{
			error("Function " + function + " undefined", call, FUNCTION__NAME);
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
		var newExpressions = new ArrayList<Expression>();
		for (var expression : expressions.keySet())
		{
			newExpressions.add(expression);
		}
		
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
							setExpression(e, type, e, null);
							newExpressions.add(e);
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
								setExpression(e, type, e, null);
								newExpressions.add(e);
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
								setExpression(e, type, e, null);
								newExpressions.add(e);
							}
							groups.remove(g);
							break;
						}
					}
				}
			}
			
			// expressions
			for (var expression : newExpressions)
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
				else if (expression instanceof Brackets)
				{
					var brackets = (Brackets) expression;
					if (setExpression(brackets.getExpression(),expressions.get(brackets),brackets,BRACKETS__EXPRESSION))
					{
						repeat = true;
					}
				}
			}

			
			newExpressions.clear();
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
		for (var archetype : game.getArchetypes())
		{
			for (var component : archetype.getComponents())
			{				
				if (!components.containsKey(component.getName()))
				{
					warning("Undecidable type", component, COMPONENT__NAME);
				}
			}
		}
	}
}