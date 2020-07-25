package m.validation;

import static m.validation.problems.BindingProblem.BindingProblemKind.*;
import static m.validation.problems.TypingProblem.TypingProblemKind.*;
import static m.validation.rules.Binding.BindingReason.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.generator.Game;
import m.library.Library;
import m.library.types.*;
import m.m.*;
import m.validation.problems.BindingProblem;
import m.validation.problems.Problem;
import m.validation.rules.Typing;
import static m.validation.rules.Typing.TypingReason.*;
import static m.m.MPackage.Literals.*;

public class Context {

	Stack<Map<String,Value>> stack;
	Map<String, Value> userVariables;
	Map<String, Cell> userComponents;
	Map<String, Function> userFunctions;
	Library library;
	
	Inference inference;
	List<Problem> problems;
	
	public Context(List<Problem> problems, Library library) {
		this.inference = new Inference(problems);
		this.problems = problems;
		this.library = library;
		
		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();
		
		this.stack = new Stack<>();
	}
	
	public void declareVariable(Value value)
	{
		var name = value.getName();
		if (library.variables.containsKey(name) || library.components.containsKey(name) || library.functions.containsKey(name))
		{
			problems.add(new BindingProblem(value, VALUE__NAME, REDEFINED));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new BindingProblem(value, VALUE__NAME, REDEFINED));			
		}
		else
		{
			var v = userVariables.get(name);
			if (v != null)
			{
				inference.bind(value, userVariables.get(name), SAME_VARIABLE);				
			}
			else
			{
				userVariables.put(name, value);
			}
		}
	}
	
	public void declareComponent(Cell cell)
	{
		var name = cell.getComponent().getName();

		if (library.variables.containsKey(name) || library.functions.containsKey(name))
		{
			problems.add(new BindingProblem(cell, CELL__COMPONENT, REDEFINED));
		}
		else if (userFunctions.containsKey(name))
		{
			problems.add(new BindingProblem(cell, CELL__COMPONENT, REDEFINED));
		}
		else if (!userComponents.containsKey(name))
		{
			userComponents.put(name, cell);
		}
	}
	
	public void declareFunction(Function function)
	{
		var name = function.getName();
		
		if (library.components.containsKey(name) || library.components.containsKey(name) || library.functions.containsKey(name))
		{
			problems.add(new BindingProblem(function, FUNCTION__NAME, REDEFINED));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new BindingProblem(function, FUNCTION__NAME, REDEFINED));
		}
		else
		{
			userFunctions.put(name, function);
		}
	}
	
	
	
	public void accessVariable(Value value)
	{
		var name = value.getName();
		
		var standard = library.variables.get(name);
		if (standard != null)
		{
			inference.type(value, new Typing(standard.getType(), LIBRARY_VARIABLE, standard));
		}
		else if (userVariables.containsKey(name) && userVariables.get(name) != value)
		{
			inference.bind(value, userVariables.get(name), SAME_VARIABLE);
		}
		else
		{
			problems.add(new BindingProblem(value, VALUE__NAME, UNDEFINED));
		}
	}
	
	public void accessComponent(Cell cell)
	{
		var name = cell.getComponent().getName();
		var standard = library.components.get(name);
		if (standard != null)
		{
			inference.type(cell, new Typing(standard.getType(), LIBRARY_COMPONENT, standard));
		}
		else
		{
			var userComponent = userComponents.get(name);
			
			if (userComponent != null && userComponent != cell)
			{
				inference.bind(cell, userComponent, SAME_COMPONENT);
			}
		}
	}
	
	public void accessBlock(String name, Expression expression, EObject source, EStructuralFeature feature)
	{
		var block = library.blocks.get(name);
		if (block != null)
		{
			inference.type(expression, new Typing(block.getType(), LIBRARY_BLOCK, block));
		}
		else
		{
			problems.add(new BindingProblem(source, feature, UNDEFINED));
		}
	}
	
	public void accessFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
	{
		var function = library.functions.get(name);
		if (function == null)
		{
			var userFunction = userFunctions.get(name);
			if (userFunction != null)
			{
				var parameters = userFunction.getParameters();
				
				if (parameters.size() == arguments.length)
				{
					for (var i = 0; i < arguments.length; i++)
					{
						inference.bind(arguments[i], parameters.get(i), PARAMETER_ARGUMENT);
					}
					// Bind result with all the return statements
				}
				else
				{
					problems.add(new BindingProblem(source, feature, UNDEFINED));
				}
			}
			else
			{
				problems.add(new BindingProblem(source, feature, UNDEFINED));
			}
		}
		else
		{
			var type = function.getType();
			
			var f = (FunctionType) type;
			var parameters = f.getParameters();
			var result = f.getReturnType();
			
			var variables = new HashMap<String, ArrayList<Expression>>();
			
			if (parameters.length == arguments.length)
			{
				for (var i = 0; i < arguments.length; i++)
				{
					if (parameters[i] instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) parameters[i];
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add(arguments[i]);
					}
					else
					{
						inference.type(arguments[i], new Typing(parameters[i], LIBRARY_FUNCTION, function));
					}
				}
				if (source instanceof Expression)
				{
					if (result instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) result;
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add(source);
					}
					else
					{
						inference.type (source, new Typing(result, LIBRARY_FUNCTION, function));
					}
				}
				// Check for type variables
				for (var typeName : variables.entrySet())
				{
					var expressions = variables.get(typeName.getKey());
					for (var i = 1; i < expressions.size(); i++)
					{
						inference.bind(expressions.get(0), expressions.get(i), POLYMORPHISM);
					}
				}
			}
			else
			{
				problems.add(new BindingProblem(source, feature, UNDEFINED));
			}
		}
	}
	
	
	
	public void push()
	{
		stack.push(new HashMap<>(userVariables));
	}
	
	public void pop()
	{
		userVariables = stack.pop();
	}
	
	public void checkConsistency()
	{
		inference.check();
	}
	
	public Game infer()
	{
		var game = inference.infer(userComponents, userFunctions);
		game.library = library;
		return game;
	}
}
