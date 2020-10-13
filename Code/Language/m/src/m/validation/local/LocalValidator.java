package m.validation.local;

import static m.library.rules.ProblemKind.*;
import static m.model.ModelPackage.Literals.APPLICATION__NAME;
import static m.model.ModelPackage.Literals.BINARY__OPERATOR;
import static m.model.ModelPackage.Literals.BINDING_BLOCK__NAME;
import static m.model.ModelPackage.Literals.BLOCK__NAME;
import static m.model.ModelPackage.Literals.UNARY__OPERATOR;

import org.eclipse.xtext.EcoreUtil2;

import m.library.Library;
import m.model.Application;
import m.model.Assignment;
import m.model.Binary;
import m.model.BindingBlock;
import m.model.Block;
import m.model.Cell;
import m.model.Delegation;
import m.model.Expression;
import m.model.File;
import m.model.Function;
import m.model.Parser;
import m.model.Statement;
import m.model.Unary;
import m.model.Value;
import m.validation.Problem;
import m.validation.Problem.Severity;

public class LocalValidator
{
	Parser parser;
	Context context;

	public LocalValidator()
	{
		parser = new Parser();
	}

	public LocalData validate(String text)
	{
		var parseResult = parser.parse(text);
		var file = (File) parseResult.getRootASTElement();

		LocalData result;

		if (file != null)
		{
			context = new Context(Library.ENGLISH);
			validate(file);
			result = context.buildData(text);
		}
		else
		{
			result = new LocalData();
		}

		for (var problem : parseResult.getSyntaxErrors())
		{
			result.problems.add(new Problem(problem, Severity.ERROR, Library.ENGLISH.getProblem(SYNTAX_ERROR)));
		}

		return result;
	}

	void validate(File file)
	{
		for (var function : file.getFunctions())
		{
			context.declareFunction(function);
		}
		for (var cell : EcoreUtil2.getAllContentsOfType(file, Cell.class))
		{
			context.declareComponent(cell);
		}

		for (var function : file.getFunctions())
		{
			validate(function);
		}
	}

	private void validate(Function function)
	{
		context.push();
		for (var statement : function.getStatements())
		{
			validate(statement);
		}
		context.pop();
	}

	private void validate(Statement statement)
	{
		if (statement instanceof Block)
		{
			var block = (Block) statement;
			var name = block.getName();
			var expression = block.getExpression();

			context.accessBlock(name, expression, block, BLOCK__NAME);
			validate(expression);
			context.push();
			for (var s : block.getStatements())
			{
				validate(s);
			}
			context.pop();
		}
		else if (statement instanceof BindingBlock)
		{
			var block = (BindingBlock) statement;
			var name = block.getName();
			var value = block.getExpression();

			context.push();
			context.declareVariable(value);
			context.accessBlock(name, value, block, BINDING_BLOCK__NAME);
			for (var s : block.getStatements())
			{
				validate(s);
			}
			context.pop();
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

			context.accessFunction("=", new Expression[] {atom, expression}, null, null);
			validate(expression);

			if (atom instanceof Value)
			{
				var value = (Value) atom;

				context.declareVariable(value);
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

			context.accessVariable(value);
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var entity = cell.getEntity();

			context.accessVariable(entity);
			context.accessComponent(cell);
		}
		else if (expression instanceof Binary)
		{
			var binary = (Binary) expression;
			var left = binary.getLeft();
			var right = binary.getRight();
			var operator = binary.getOperator();

			context.accessFunction(operator, new Expression[] {left, right}, binary, BINARY__OPERATOR);
			validate(left);
			validate(right);
		}
		else if (expression instanceof Unary)
		{
			var unary = (Unary) expression;
			var e =  unary.getExpression();
			var operator = unary.getOperator();

			context.accessFunction(operator, new Expression[] {e}, unary, UNARY__OPERATOR);
			validate(e);
		}
		else if (expression instanceof Application)
		{
			var application = (Application) expression;
			var name = application.getName();
			var arguments = application.getArguments().toArray(new Expression[0]);

			context.accessFunction(name, arguments, application, APPLICATION__NAME);
			for (var argument : application.getArguments())
			{
				validate(argument);
			}
		}
	}
}
