package m.validation;

import static m.library.SimpleType.bool;
import static m.library.SimpleType.entity;
import static m.library.SimpleType.entityList;
import static m.library.SimpleType.float1;
import static m.library.SimpleType.float2;
import static m.library.SimpleType.float3;
import static m.library.SimpleType.float4;
import static m.m.MPackage.Literals.ARCHETYPE__BASE;
import static m.m.MPackage.Literals.ARCHETYPE__NAME;
import static m.m.MPackage.Literals.CARDINAL__EXPRESSION;
import static m.m.MPackage.Literals.CELL__COMPONENT;
import static m.m.MPackage.Literals.CELL__ENTITY;
import static m.m.MPackage.Literals.COMPARISON__KIND;
import static m.m.MPackage.Literals.COMPARISON__LEFT;
import static m.m.MPackage.Literals.COMPARISON__RIGHT;
import static m.m.MPackage.Literals.COMPONENT__NAME;
import static m.m.MPackage.Literals.EXPLICIT_SET__ELEMENTS;
import static m.m.MPackage.Literals.FORALL__CONDITION;
import static m.m.MPackage.Literals.FORALL__VARIABLE;
import static m.m.MPackage.Literals.FUNCTION__NAME;
import static m.m.MPackage.Literals.IMPLICIT_SET__PREDICATE;
import static m.m.MPackage.Literals.IMPLICIT_SET__VARIABLE;
import static m.m.MPackage.Literals.ITERATION__CONDITION;
import static m.m.MPackage.Literals.JOIN__ENTRIES;
import static m.m.MPackage.Literals.LOGICAL_AND__LEFT;
import static m.m.MPackage.Literals.LOGICAL_NOT__EXPRESSION;
import static m.m.MPackage.Literals.LOGICAL_OR__LEFT;
import static m.m.MPackage.Literals.MULTIPLICATIVE_EXPRESSION__RIGHT;
import static m.m.MPackage.Literals.SELECTION__CONDITION;
import static m.m.MPackage.Literals.SET_EXPRESSION__KIND;
import static m.m.MPackage.Literals.SET_EXPRESSION__LEFT;
import static m.m.MPackage.Literals.SET_EXPRESSION__RIGHT;
import static m.m.MPackage.Literals.SYSTEM__NAME;
import static m.m.MPackage.Literals.VARIABLE__NAME;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.library.Component;
import m.library.MagicName;
import m.library.SimpleType;
import m.m.AdditiveExpression;
import m.m.Archetype;
import m.m.Assignment;
import m.m.Cardinal;
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
	public void unique(m.m.Component component)
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
				error("Expected type " + expressions.get(expression) + " but got " + type.toString(), o, feature);
			}
		}
		return isNew;
	}
	
	
	
	
	
	
	
	@Check
	public void infer(m.m.Component component)
	{
		magicNames(component.getName(), component, COMPONENT__NAME);
	}
	
	@Check
	public void infer(Cell cell)
	{
		var entity = cell.getEntity();
		var component = cell.getComponent();
		
		setVariable(entity, SimpleType.entity, cell, CELL__ENTITY);
		magicNames(component, cell, CELL__COMPONENT);
	}
	
	private void magicNames(String component, EObject o, EStructuralFeature feature)
	{
		for (var magicName : MagicName.values())
		{
			if (component.endsWith(magicName.ending))
			{
				setComponent(component, magicName.type, o, feature);
				
				if (magicName.baseType != SimpleType.none)
				{
					var base = component.substring(0, component.lastIndexOf(magicName.ending));
					setComponent(base, magicName.baseType, o, feature);
				}
				break;
			}
		}
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
	public void infer(LogicalOr or)
	{
		setExpression(or, bool, or, LOGICAL_OR__LEFT);
		setExpression(or.getLeft(), bool, or, LOGICAL_OR__LEFT);
		setExpression(or.getRight(), bool, or, LOGICAL_AND__LEFT);
	}
	
	@Check
	public void infer(LogicalAnd and)
	{
		setExpression(and, bool, and, LOGICAL_AND__LEFT);
		setExpression(and.getLeft(), bool, and, LOGICAL_AND__LEFT);
		setExpression(and.getRight(), bool, and, LOGICAL_AND__LEFT);
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
		setExpression(right, float1, multiplication, MULTIPLICATIVE_EXPRESSION__RIGHT);
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
					setExpression(arguments.get(i), simpleType, call, FUNCTION__NAME);
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
