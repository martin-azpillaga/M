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

import m.library.Library;
import m.library.types.*;
import m.m.*;
import m.main.InferenceData;
import m.validation.problems.Problem;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.problems.errors.UndefinedSymbol;
import m.validation.problems.warnings.UnusedValue;
import m.validation.rules.Typing;
import static m.validation.rules.Typing.TypingReason.*;
import static m.m.MPackage.Literals.*;

public class Context
{
	LocalInference inference;
	List<Problem> problems;
	Library library;
	
	Map<String, Value> userVariables;
	Map<String, Cell> userComponents;
	Map<String, Function> userFunctions;
	Stack<Map<String,Value>> stack;
	
	Set<Value> accessedValues;
	
	public Context(Library library)
	{
		this.inference = new LocalInference();
		this.problems = new ArrayList<Problem>();
		this.library = library;
		
		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();
		
		this.stack = new Stack<>();
		this.accessedValues = new HashSet<Value>();
	}
	
	public void declareVariable(Value value)
	{
		if (value == null) return;

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
			var declaration = userVariables.get(name);
			if (declaration != null)
			{
				inference.bind(value, declaration, SAME_VARIABLE);				
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
		if (value == null) return;
		
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
		else
		{
			problems.add(new UndefinedSymbol(value, VALUE__NAME));
		}
	}
	
	public void accessComponent(Cell cell)
	{
		if (cell == null || cell.getComponent() == null) return;

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
		var standard = library.getBlock(name);
		if (standard != null)
		{
			inference.type(expression, new Typing(standard.getType(), LIBRARY_BLOCK, standard));
		}
		else
		{
			problems.add(new UndefinedSymbol(source, feature));
		}
	}
	
	public void accessFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
	{
		var standard = library.getFunction(name);
		if (standard != null)
		{
			var type = (FunctionType) standard.getType();
			
			var parameterTypes = type.getParameters();
			var returnType = type.getReturnType();
			
			var variables = new HashMap<String, ArrayList<Expression>>();
			
			if (parameterTypes.length == arguments.length)
			{
				for (var i = 0; i < arguments.length; i++)
				{
					if (parameterTypes[i] instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) parameterTypes[i];
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add(arguments[i]);
					}
					else
					{
						inference.type(arguments[i], new Typing(parameterTypes[i], LIBRARY_FUNCTION, standard));
					}
				}
				if (source instanceof Expression)
				{
					if (returnType instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) returnType;
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add(source);
					}
					else
					{
						inference.type (source, new Typing(returnType, LIBRARY_FUNCTION, standard));
					}
				}
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
						inference.bind(arguments[i], parameters.get(i), PARAMETER_ARGUMENT);
					}
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
	


	public InferenceData getInferenceData()
	{
		var result = inference.getInferenceData(userComponents);
		result.functions.putAll(userFunctions);
		result.problems = problems;
		return result;
	}
}
