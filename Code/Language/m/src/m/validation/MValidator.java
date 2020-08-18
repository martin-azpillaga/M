package m.validation;

import java.nio.file.Paths;
import java.util.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;

import m.generator.Game;
import m.library.Library;
import m.m.*;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;
import m.validation.problems.errors.ReadOnly;

import static m.m.MPackage.Literals.*;

public class MValidator extends AbstractMValidator
{
	Context context;
	Map<Library,List<Problem>> map;
	Map<Library, Context> contexts;
	public static Game game;
	Library currentLibrary;
	List<Problem> currentProblems;
	
	public Game getGame()
	{
		return game;
	}
	
	@Check
	public void validate(File file)
	{
		if (!file.eResource().getErrors().isEmpty()) return;
		
		
		map = new HashMap<>();
		contexts = new HashMap<>();
		
		for (var library : Library.values())
		{
			currentProblems = new ArrayList<Problem>();
			context = new Context(currentProblems, library);
			map.put(library, currentProblems);
			contexts.put(library, context);
			this.currentLibrary = library;
			
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
		
		var problemMessages = new ArrayList<ProblemMessage>();
		
		var hasErrors = false;
		if (list != null)
		{
			for (var problem : list)
			{
				for (var message : problem.messages(library))
				{
					problemMessages.add(message);
					if (message.severity == Severity.ERROR)
					{
						hasErrors = true;
					}
				}
			}
		}
		if (!hasErrors)
		{
			game = contexts.get(library).infer();
		}
		
		reportProblems(problemMessages);
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
				
				if (currentLibrary.getValue(value.getName()) != null)
				{
					currentProblems.add(new ReadOnly(value));
				}
				context.declareVariable(value);
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
	
	void reportProblems(List<ProblemMessage> messages)
	{
		for (var message : messages)
		{
			switch (message.severity)
			{
			case INFO:
				info(message.message, message.source, message.feature);
				break;
			case WARNING:
				warning(message.message, message.source, message.feature);
				break;
			case ERROR:
				error(message.message, message.source, message.feature);
				break;
			}
		}
	}
}