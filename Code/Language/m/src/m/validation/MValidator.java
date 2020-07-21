package m.validation;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.validation.Check;

import m.generator.Game;
import m.library.Library;
import m.m.*;

import static m.m.MPackage.Literals.*;

public class MValidator extends AbstractMValidator
{
	Library library;
	Context context;
	List<Problem> problems;
	Game game;
	
	public Game getGame()
	{
		return game;
	}
	
	@Check
	public void validate(File file)
	{
		if (!file.eResource().getErrors().isEmpty()) return;

		library = Library.ENGLISH;
		problems = new ArrayList<Problem>();
		context = new Context(problems, library);
		
		for (var function : file.getFunctions())
		{
			context.declareFunction(function);
		}
		for (var function : file.getFunctions())
		{
			validate(function);
		}
		
		context.checkConsistency();
		if (problems.isEmpty())
		{
			game = context.infer();
		}
		else
		{
			reportProblems();
		}
	}
	
	void validate(Function function) {
		context.push();
		for (var statement : function.getStatements()) {
			validate(statement);
		}
		context.pop();
	}
	
	void validate(Statement statement) {
		if (statement instanceof Block) {
			var block = (Block) statement;
			var name = block.getName();
			var expression = block.getExpression();
			
			context.checkBlock(name, expression, block, BLOCK__NAME);
			validate(expression);
			for (var s : block.getStatements())
			{
				validate(s);
			}
		}
		else if (statement instanceof BindingBlock)
		{
			var block = (BindingBlock) statement;
			var name = block.getName();
			var value = block.getExpression();
			
			context.push();
			context.declareVariable(value);
			context.checkBlock(name, value, block, BLOCK__NAME);
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
			
			context.checkFunction("=", new Expression[] {atom, expression}, null, ASSIGNMENT__ATOM);
			validate(expression);
			
			if (atom instanceof Value)
			{
				context.declareVariable((Value)atom);
			}
			else
			{
				validate(atom);
			}
		}
	}
	
	void validate(Expression expression) {
		if (expression instanceof Value)
		{
			var value = (Value) expression;
			
			context.checkVariable(value);
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var entity = cell.getEntity();
			
			context.declareComponent(cell);
			context.checkVariable(entity);
			context.checkComponent(cell);
		}
		else if (expression instanceof Binary)
		{
			var binary = (Binary) expression;
			var left = binary.getLeft();
			var right = binary.getRight();
			var operator = binary.getOperator();
			
			context.checkFunction(operator, new Expression[] {left, right}, binary, BINARY__OPERATOR);
			validate(left);
			validate(right);
		}
		else if (expression instanceof Unary)
		{
			var unary = (Unary) expression;
			var e =  unary.getExpression();
			var operator = unary.getOperator();
			
			context.checkFunction(operator, new Expression[] {e}, unary, UNARY__OPERATOR);
			validate(e);
		}
		else if (expression instanceof Application)
		{
			var application = (Application) expression;
			var name = application.getName();
			var arguments = application.getArguments().toArray(new Expression[0]);
			
			context.checkFunction(name, arguments, application, APPLICATION__NAME);

			for (var argument : application.getArguments())
			{
				validate(argument);
			}
		}
	}
	
	void reportProblems()
	{
		for (var problem : problems)
		{
			switch (problem.severity)
			{
			case INFO:
				info(library.message(problem), problem.source, problem.feature);
				break;
			case WARNING:
				warning(library.message(problem), problem.source, problem.feature);
				break;
			case ERROR:
				if (problem instanceof IncompatibleTypes)
				{
					var p = (IncompatibleTypes) problem;
					var message = library.message(problem);
					error(message, problem.source, problem.feature);
					for (var link : p.links)
					{
						if (link == p.links.get(p.links.size()-1))
						{
							error(message, link.expression, null);
						}
						else
						{
							warning(message, link.expression, null);
						}
					}
				}
				else
				{
					error(library.message(problem), problem.source, problem.feature);
				}
				break;
			}
		}
	}
}