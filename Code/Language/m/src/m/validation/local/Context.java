package m.validation.local;

import static m.library.rules.BindingReason.*;
import static m.library.rules.ProblemKind.*;
import static m.library.rules.TypingReason.*;
import static m.model.ModelPackage.Literals.*;
import static m.validation.Problem.Severity.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.library.Library;
import m.library.types.*;
import m.model.*;
import m.validation.Problem;
import m.validation.local.ExpressionNode.Typing;

public class Context
{
	ExpressionGraph graph;
	List<Problem> problems;
	Library library;

	Map<String, Value> userVariables;
	Map<String, Cell> userComponents;
	Map<String, Function> userFunctions;
	Deque<Map<String,Value>> stack;

	Set<Value> accessedValues;

	public Context(Library library)
	{
		this.graph = new ExpressionGraph();
		this.problems = new ArrayList<>();
		this.library = library;

		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();

		this.stack = new ArrayDeque<>();
		this.accessedValues = new HashSet<>();
	}

	public void declareVariable(Value value)
	{
		if (value == null) return;

		var name = value.getName();

		if (library.getValue(name) != null || library.getComponent(name) != null || library.getFunction(name) != null)
		{
			problems.add(new Problem(value, VALUE__NAME, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new Problem(value, VALUE__NAME, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else
		{
			var declaration = userVariables.get(name);
			if (declaration != null)
			{
				graph.bind(value, declaration, SAME_VARIABLE);
			}
			else
			{
				userVariables.put(name, value);
			}
		}
	}

	public void declareComponent(Cell cell)
	{
		if (cell.getComponent() == null) return;

		var name = cell.getComponent().getName();

		if (library.getValue(name) != null || library.getFunction(name) != null)
		{
			problems.add(new Problem(cell, CELL__COMPONENT, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else if (userFunctions.containsKey(name))
		{
			problems.add(new Problem(cell, CELL__COMPONENT, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else if (!userComponents.containsKey(name) && library.getComponent(name) == null)
		{
			userComponents.put(name, cell);
		}
	}

	public void declareFunction(Function function)
	{
		var name = function.getName();

		if (library.getValue(name) != null || library.getComponent(name) != null || library.getFunction(name) != null)
		{
			problems.add(new Problem(function, FUNCTION__NAME, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new Problem(function, FUNCTION__NAME, ERROR, library.getProblem(REDEFINED_SYMBOL)));
		}
		else
		{
			userFunctions.put(name, function);
		}
	}

	public void accessVariable(Value value)
	{
		if (value == null) return;

		var name = value.getName();

		var standard = library.getValue(name);
		if (standard != null)
		{
			graph.type(value, new Typing(standard.getType(), STANDARD_VARIABLE, standard));
		}
		else if (userVariables.containsKey(name) && userVariables.get(name) != value)
		{
			graph.bind(value, userVariables.get(name), SAME_VARIABLE);
			accessedValues.add(userVariables.get(name));
		}
		else
		{
			problems.add(new Problem(value, VALUE__NAME, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
		}
	}

	public void accessComponent(Cell cell)
	{
		if (cell == null || cell.getComponent() == null) return;

		var name = cell.getComponent().getName();

		var standard = library.getComponent(name);
		if (standard != null)
		{
			graph.type(cell, new Typing(standard.getType(), STANDARD_COMPONENT, standard));
		}
		else
		{
			var userComponent = userComponents.get(name);

			if (userComponent != null && userComponent != cell)
			{
				graph.bind(cell, userComponent, SAME_COMPONENT);
			}
		}
	}

	public void accessBlock(String name, Expression expression, EObject source, EStructuralFeature feature)
	{
		var standard = library.getBlock(name);
		if (standard != null)
		{
			graph.type(expression, new Typing(standard.getType(), STANDARD_BLOCK, standard));
		}
		else
		{
			problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
		}
	}

	public void accessFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
	{
		var standard = library.getFunction(name);
		if (standard != null)
		{
			var type = (FunctionType) standard.getType();

			var parameterTypes = type.parameterTypes;
			var returnType = type.returnType;

			var typeVariables = new HashMap<TypeVariable, ArrayList<Expression>>();

			if (parameterTypes.length == arguments.length)
			{
				for (var i = 0; i < arguments.length; i++)
				{
					if (parameterTypes[i] instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) parameterTypes[i];
						if (!typeVariables.containsKey(typeVariable))
						{
							typeVariables.put(typeVariable, new ArrayList<>());
						}
						typeVariables.get(typeVariable).add(arguments[i]);
					}
					else
					{
						graph.type(arguments[i], new Typing(parameterTypes[i], STANDARD_FUNCTION, standard));
					}
				}
				if (source instanceof Expression)
				{
					if (returnType instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) returnType;
						if (!typeVariables.containsKey(typeVariable))
						{
							typeVariables.put(typeVariable, new ArrayList<>());
						}
						typeVariables.get(typeVariable).add(source);
					}
					else
					{
						graph.type (source, new Typing(returnType, STANDARD_FUNCTION, standard));
					}
				}
				for (var typeName : typeVariables.entrySet())
				{
					var expressions = typeVariables.get(typeName.getKey());
					for (var i = 1; i < expressions.size(); i++)
					{
						graph.bind(expressions.get(0), expressions.get(i), SAME_TYPE_VARIABLE);
					}
				}
			}
			else
			{
				problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
			}
		}
		else
		{
			var userFunction = userFunctions.get(name);
			if (userFunction != null)
			{
				var parameters = userFunction.getParameters();

				if (parameters.size() == arguments.length)
				{
					for (var i = 0; i < arguments.length; i++)
					{
						graph.bind(arguments[i], parameters.get(i), SAME_FUNCTION);
					}
				}
				else
				{
					problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
				}
			}
			else
			{
				problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
			}
		}
	}

	public void push()
	{
		stack.push(new HashMap<>(userVariables));
	}

	public void pop()
	{
		var popped = stack.pop();
		for (var entry : userVariables.entrySet())
		{
			if (!popped.containsKey(entry.getKey()) && !accessedValues.contains(entry.getValue()))
			{
				problems.add(new Problem(entry.getValue(), VALUE__NAME, WARNING, library.getProblem(UNUSED_VALUE)));
			}
		}
		userVariables = popped;
	}

	public LocalValidator.Result buildData(String text)
	{
		return graph.buildData(userFunctions, problems);
	}
}