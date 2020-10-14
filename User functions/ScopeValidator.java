package m.validation;

import static m.library.rules.BindingReason.SAME_COMPONENT;
import static m.library.rules.BindingReason.SAME_FUNCTION;
import static m.library.rules.BindingReason.SAME_TYPE_VARIABLE;
import static m.library.rules.BindingReason.SAME_VARIABLE;
import static m.library.rules.ProblemKind.REDEFINED_SYMBOL;
import static m.library.rules.ProblemKind.UNDEFINED_SYMBOL;
import static m.library.rules.TypingReason.STANDARD_BLOCK;
import static m.library.rules.TypingReason.STANDARD_COMPONENT;
import static m.library.rules.TypingReason.STANDARD_FUNCTION;
import static m.library.rules.TypingReason.STANDARD_VARIABLE;
import static m.model.ModelPackage.Literals.APPLICATION__NAME;
import static m.model.ModelPackage.Literals.BINARY__OPERATOR;
import static m.model.ModelPackage.Literals.BINDING_BLOCK__NAME;
import static m.model.ModelPackage.Literals.BLOCK__NAME;
import static m.model.ModelPackage.Literals.CELL__COMPONENT;
import static m.model.ModelPackage.Literals.FUNCTION__NAME;
import static m.model.ModelPackage.Literals.UNARY__OPERATOR;
import static m.model.ModelPackage.Literals.VALUE__NAME;
import static m.validation.Problem.Severity.ERROR;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.TypeVariable;
import m.model.Application;
import m.model.Assignment;
import m.model.Binary;
import m.model.BindingBlock;
import m.model.Block;
import m.model.Cell;
import m.model.Delegation;
import m.model.Expression;
import m.model.ExpressionGraph;
import m.model.ExpressionNode.Typing;
import m.model.File;
import m.model.Function;
import m.model.Statement;
import m.model.Unary;
import m.model.Value;

public class ScopeValidator
{
	ExpressionGraph graph;
	List<Problem> problems;
	Library library = Library.ENGLISH;

	Map<String, Value> userVariables;
	Map<String, Cell> userComponents;
	Map<String, Function> userFunctions;
	Deque<Map<String,Value>> stack;
	List<Call> calls;

	public ScopeValidator()
	{
		this.graph = new ExpressionGraph();
		this.problems = new ArrayList<>();

		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();

		this.stack = new ArrayDeque<>();
		this.calls = new ArrayList<>();
	}

	private void reset()
	{
		this.graph = new ExpressionGraph();
		this.problems = new ArrayList<>();

		this.userVariables = new HashMap<>();
		this.userFunctions = new HashMap<>();
		this.userComponents = new HashMap<>();

		this.stack = new ArrayDeque<>();
		this.calls = new ArrayList<>();
	}

	public Result delete(String file)
	{
		return new Result();
	}

	public Result validate(String file, File model)
	{
		reset();

		var result = new Result();

		validate(model);

		checkCalls();

		result.expressionGraph = graph;

		if ( ! problems.isEmpty())
		{
			result.problems.put(file, problems);
		}

		return result;
	}

	private void validate(File file)
	{
		for (var function : file.getFunctions())
		{
			validate(function);
		}
	}

	private void validate(Function function)
	{
		push();
		for (var statement : function.getStatements())
		{
			validate(statement);
		}
		pop();
	}

	private void validate(Statement statement)
	{
		if (statement instanceof Block)
		{
			var block = (Block) statement;
			var name = block.getName();
			var expression = block.getExpression();

			accessBlock(name, expression, block, BLOCK__NAME);
			validate(expression);
			push();
			for (var s : block.getStatements())
			{
				validate(s);
			}
			pop();
		}
		else if (statement instanceof BindingBlock)
		{
			var block = (BindingBlock) statement;
			var name = block.getName();
			var value = block.getExpression();

			push();
			declareVariable(value);
			accessBindingBlock(name, value, block, BINDING_BLOCK__NAME);
			for (var s : block.getStatements())
			{
				validate(s);
			}
			pop();
		}
		else if (statement instanceof Delegation)
		{
			var delegation = (Delegation) statement;
			var application = delegation.getApplication();

			validate(application);
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();

			accessFunction("=", new Expression[] {atom, expression}, null, null);
			validate(expression);

			if (atom instanceof Value)
			{
				var value = (Value) atom;

				declareVariable(value);
			}
			else
			{
				validate(atom);
			}
		}
	}

	private void validate(Expression expression)
	{
		if (expression instanceof Value)
		{
			var value = (Value) expression;

			accessVariable(value);
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var entity = cell.getEntity();

			accessVariable(entity);
			declareComponent(cell);
			accessComponent(cell);
		}
		else if (expression instanceof Binary)
		{
			var binary = (Binary) expression;
			var left = binary.getLeft();
			var right = binary.getRight();
			var operator = binary.getOperator();

			accessFunction(operator, new Expression[] {left, right}, binary, BINARY__OPERATOR);
			validate(left);
			validate(right);
		}
		else if (expression instanceof Unary)
		{
			var unary = (Unary) expression;
			var e =  unary.getExpression();
			var operator = unary.getOperator();

			accessFunction(operator, new Expression[] {e}, unary, UNARY__OPERATOR);
			validate(e);
		}
		else if (expression instanceof Application)
		{
			var application = (Application) expression;
			var name = application.getName();
			var arguments = application.getArguments().toArray(new Expression[0]);

			accessFunction(name, arguments, application, APPLICATION__NAME);
			for (var argument : application.getArguments())
			{
				validate(argument);
			}
		}
	}

	private void declareVariable(Value value)
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

	private void declareComponent(Cell cell)
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

	private void declareFunction(Function function)
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

	private void accessVariable(Value value)
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
		}
		else
		{
			problems.add(new Problem(value, VALUE__NAME, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
		}
	}

	private void accessComponent(Cell cell)
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

	private void accessFunction(String name, Expression[] arguments, Expression source, EStructuralFeature feature)
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
				calls.add(new Call(name, arguments, source, feature));
			}
		}
		else
		{
			calls.add(new Call(name,arguments,source,feature));
		}
	}

	private void accessBlock(String name, Expression expression, EObject source, EStructuralFeature feature)
	{
		var standard = name.equals(library.selection) || name.equals(library.iteration);
		if (standard)
		{
			graph.type(expression, new Typing(AtomicType.PROPOSITION, STANDARD_BLOCK, null));
		}
		else
		{
			problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
		}
	}

	private void accessBindingBlock(String name, Expression expression, EObject source, EStructuralFeature feature)
	{
		var standard = name.equals(library.query);
		if (standard)
		{
			graph.type(expression, new Typing(AtomicType.ENTITY, STANDARD_BLOCK, null));
		}
		else
		{
			problems.add(new Problem(source, feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
		}
	}

	private void checkCalls()
	{
		for (var call : calls)
		{
			var userFunction = userFunctions.get(call.name);
			if (userFunction != null)
			{
				var parameters = userFunction.getParameters();

				if (parameters.size() == call.arguments.length)
				{
					for (var i = 0; i < call.arguments.length; i++)
					{
						graph.bind(call.arguments[i], parameters.get(i), SAME_FUNCTION);
					}
				}
				else
				{
					problems.add(new Problem(call.source, call.feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
				}
			}
			else
			{
				problems.add(new Problem(call.source, call.feature, ERROR, library.getProblem(UNDEFINED_SYMBOL)));
			}
		}
	}

	private void push()
	{
		stack.push(new HashMap<>(userVariables));
	}

	private void pop()
	{
		userVariables = stack.pop();
	}

	public static class Result
	{
		public ExpressionGraph expressionGraph;
		public Map<String,List<Problem>> problems;

		public Result()
		{
			this.expressionGraph = new ExpressionGraph();
			this.problems = new HashMap<>();
		}
	}
}
class Call
{
	public final String name;
	public Expression[] arguments;
	public EObject source;
	public EStructuralFeature feature;

	public Call(String name, Expression[] arguments, EObject source, EStructuralFeature feature)
	{
		this.name = name;
		this.arguments = arguments;
		this.source = source;
		this.feature = feature;
	}
}
