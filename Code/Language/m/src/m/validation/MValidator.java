package m.validation;


import java.util.*;

import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;

import m.generator.Game;
import m.library.Library;
import m.m.*;
import m.validation.problems.BindingProblem;
import m.validation.problems.Problem;
import m.validation.problems.TypingProblem;

import static m.m.MPackage.Literals.*;


public class MValidator extends AbstractMValidator
{
	Context context;
	Map<Library,List<Problem>> map;
	Map<Library, Context> contexts;
	Game game;
	
	public Game getGame()
	{
		return game;
	}
	
	@Check
	public void validate(File file)
	{
		if (!file.eResource().getErrors().isEmpty()) return;
		
		map = new EnumMap<>(Library.class);
		contexts = new EnumMap<>(Library.class);
		
		for (var library : Library.values())
		{
			var problems = new ArrayList<Problem>();
			context = new Context(problems, library);
			map.put(library, problems);
			contexts.put(library, context);
			
			for (var function : file.getFunctions())
			{
				context.declareFunction(function);
			}
			// Can become a single pass if done with delayed checking
			for (var cell : EcoreUtil2.getAllContentsOfType(file, Cell.class))
			{
				context.declareComponent(cell);
			}
			for (var function : file.getFunctions())
			{
				validate(function);
			}
			
			context.checkConsistency();
		}
		
		var minProblems = Integer.MAX_VALUE;
		List<Problem> list = null;
		Library library = null;
		
		for (var entry : map.entrySet())
		{
			if (entry.getValue().size() < minProblems)
			{
				minProblems = entry.getValue().size();
				list = entry.getValue();
				library = entry.getKey();
			}
		}
		if (list != null && list.isEmpty())
		{
			game = contexts.get(library).infer();
		}
		else
		{
			reportProblems(list, library);
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
			context.accessBlock(name, value, block, BLOCK__NAME);
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
	
	void reportProblems(List<Problem> problems, Library library)
	{
		for (var problem : problems)
		{
			var message = library.message(problem);
			
			if (problem instanceof BindingProblem)
			{
				var bindingProblem = (BindingProblem) problem;
				var source = bindingProblem.getSource();
				var feature = bindingProblem.getFeature();
				
				error(message, source, feature);
			}
			else if (problem instanceof TypingProblem)
			{
				var typingProblem = (TypingProblem) problem;
				var node = typingProblem.getNode();
				error(message, node.expression, null);
				while (node.binding != null)
				{
					node = node.binding.node;
					error(message, node.expression, null);
				}
			}
		}
	}
}