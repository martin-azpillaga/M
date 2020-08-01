package m.validation;

import static m.validation.rules.Binding.BindingReason.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.generator.Game;
import m.library.Library;
import m.library.types.*;
import m.m.*;
import m.validation.problems.Problem;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.problems.errors.UndefinedSymbol;
import m.validation.problems.warnings.UnusedValue;
import m.validation.rules.Typing;
import static m.validation.rules.Typing.TypingReason.*;
import static m.m.MPackage.Literals.*;

public class Context {

	Stack<Map<String,Value>> stack;
	Map<String, Value> userVariables;
	Map<String, Cell> userComponents;
	Map<String, Function> userFunctions;
	Library library;
	
	Set<Value> accessedValues;
	
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
		this.accessedValues = new HashSet<Value>();
	}
	
	public void declareVariable(Value value)
	{
		var name = value.getName();
		if (library.getValue(name) != null || library.getComponent(name) != null || library.getFunction(name) != null)
		{
			problems.add(new RedefinedSymbol(value, VALUE__NAME));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new RedefinedSymbol(value, VALUE__NAME));			
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

		if (library.getValue(name) != null || library.getFunction(name) != null)
		{
			problems.add(new RedefinedSymbol(cell, CELL__COMPONENT));
		}
		else if (userFunctions.containsKey(name))
		{
			problems.add(new RedefinedSymbol(cell, CELL__COMPONENT));
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
			problems.add(new RedefinedSymbol(function, FUNCTION__NAME));
		}
		else if (userComponents.containsKey(name) || userFunctions.containsKey(name))
		{
			problems.add(new RedefinedSymbol(function, FUNCTION__NAME));
		}
		else
		{
			userFunctions.put(name, function);
		}
	}
	
	
	
	public void accessVariable(Value value)
	{
		var name = value.getName();
		
		var standard = library.getValue(name);
		if (standard != null)
		{
			inference.type(value, new Typing(standard.getType(), LIBRARY_VARIABLE, standard));
		}
		else if (userVariables.containsKey(name) && userVariables.get(name) != value)
		{
			inference.bind(value, userVariables.get(name), SAME_VARIABLE);
			accessedValues.add(userVariables.get(name));
		}
		else if (userComponents.containsKey(name))
		{
			
		}
		else
		{
			problems.add(new UndefinedSymbol(value, VALUE__NAME));
		}
	}
	
	public void accessComponent(Cell cell)
	{
		var name = cell.getComponent().getName();
		var standard = library.getComponent(name);
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
		var block = library.getBlock(name);
		if (block != null)
		{
			inference.type(expression, new Typing(block.getType(), LIBRARY_BLOCK, block));
		}
		else
		{
			problems.add(new UndefinedSymbol(source, feature));
		}
	}
	
	public void accessFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
	{
		var function = library.getFunction(name);
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
					problems.add(new UndefinedSymbol(source, feature));
				}
			}
			else
			{
				problems.add(new UndefinedSymbol(source, feature));
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
				problems.add(new UndefinedSymbol(source, feature));
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
		for (var value : userVariables.keySet())
		{
			if (!popped.containsKey(value))
			{
				if (!accessedValues.contains(userVariables.get(value)))
				{
					problems.add(new UnusedValue(userVariables.get(value)));
				}	
			}
		}
		userVariables = popped;
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
