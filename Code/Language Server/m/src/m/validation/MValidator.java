package m.validation;

import static game.GamePackage.Literals.*;
import java.util.*;
import java.util.HashMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;
import game.*;
import static m.validation.StandardLibrary.*;

public class MValidator extends AbstractMValidator 
{
	Game game;
	ArrayList<ArrayList<Expression>> groups = new ArrayList<>();
	HashMap<String,Type> components = new HashMap<String, Type>();
	
	@Check
	public void unique(game.System system)
	{
		var game = (Game) system.eContainer();
		
		var amount = 0;
		
		for (var s : game.getSystems())
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
			expression.setType(type);
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
		
		if (!found)
		{
			error("Function " + name + " undefined", call, CALL__NAME);
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
	}
}
