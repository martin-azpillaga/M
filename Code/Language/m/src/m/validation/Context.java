package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.generator.Game;
import m.library.Library;
import m.m.*;
import m.types.*;
import static m.validation.BindingReason.*;

public class Context {
	
	Map<String, Value> userVariables;
	Map<String, Function> userFunctions;
	Map<String, Cell> userComponents;
	
	Inference inference;
	Stack<Map<String,Value>> stack;
	List<Problem> problems;
	Library library;
	
	public Context(List<Problem> problems, Library library) {
		this.inference = new Inference(problems);
		this.problems = problems;
		this.library = library;
		
		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();
		
		this.stack = new Stack<>();
		
		for (var component : library.components.keySet())
		{
			userVariables.put(component, null);
		}
	}
	
	public void declareVariable(Value value)
	{
		var name = value.getName();
		var set = userVariables.containsKey(name);
		var v = userVariables.get(name);
		if (v != null)
		{
			inference.group(value, userVariables.get(name), SAME_VARIABLE);
		}
		else if (set)
		{
			problems.add(new SymbolRedefinition(value, null));
		}
		else
		{
			userVariables.put(name, value);
		}
	}
	
	public void declareFunction(Function function)
	{
		var name = function.getName();
		
		if (userFunctions.containsKey(name))
		{
			problems.add(new SymbolRedefinition(function, userFunctions.get(name)));
		}
		else
		{
			userFunctions.put(name, function);
		}
	}
	
	public void declareComponent(Cell cell)
	{
		var name = cell.getComponent().getName();
		if (!library.components.containsKey(name))
		{
			userComponents.put(name, cell);
			userVariables.put(name, null);
		}
	}
	
	public void checkVariable(Value value)
	{
		var name = value.getName();
		if (!userVariables.containsKey(name))
		{
			problems.add(new UndefinedSymbol(value, null, ""));
		}
		else
		{
			inference.group(value, userVariables.get(name), SAME_VARIABLE);
		}
	}
	
	public void checkBlock(String name, Expression expression, EObject source, EStructuralFeature feature)
	{
		var block = library.blocks.get(name);
		if (block != null)
		{
			inference.set(expression, block.getType(), new TypingReason(block, 1));
		}
		else
		{
			problems.add(new UndefinedSymbol(source, feature, ""));
		}
	}
	
	public void checkFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
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
						inference.group(arguments[i], parameters.get(i), PARAMETER_ARGUMENT);
					}
					// Bind result with all the return statements
				}
				else
				{
					problems.add(new UndefinedSymbol(source, feature, ""));
				}
			}
			else
			{
				problems.add(new UndefinedSymbol(source, feature, ""));
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
						inference.set(arguments[i], parameters[i], new TypingReason(function, i+1));
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
						variables.get(typeName).add((Expression)source);
					}
					else
					{
						inference.set ((Expression)source, result, new TypingReason(function, -1));
					}
				}
				// Check for type variables
				for (var typeName : variables.entrySet())
				{
					var expressions = variables.get(typeName.getKey());
					for (var i = 1; i < expressions.size(); i++)
					{
						inference.group(expressions.get(0), expressions.get(i), POLYMORPHISM);
					}
				}
			}
			else
			{
				problems.add(new UndefinedSymbol(source, feature, ""));
			}
		}
	}
	
	public void checkComponent(Cell cell)
	{
		var name = cell.getComponent().getName();
		var component = library.components.get(name);
		if (component == null)
		{
			var userComponent = userComponents.get(name);
			
			if (userComponent != null && userComponent != cell)
			{
				inference.group(cell, userComponent, SAME_COMPONENT);
			}
			else
			{
				declareComponent(cell);
			}
		}
		else
		{
			inference.set(cell, component.getType(), new TypingReason(component, 1));
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
		return inference.infer(userComponents, userFunctions);
	}
}
