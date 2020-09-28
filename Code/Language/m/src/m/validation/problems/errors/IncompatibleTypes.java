package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import m.library.Library;
import m.library.types.Type;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;
import m.validation.rules.ExpressionNode;

public class IncompatibleTypes implements Problem
{
	ExpressionNode node;
	
	public IncompatibleTypes(ExpressionNode node)
	{
		this.node = node;
	}
	
	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var base = library.getProblem(this.getClass());
		var list = new ArrayList<ProblemMessage>();
		
		var visited = new HashSet<ExpressionNode>();
		var stack = new Stack<ExpressionNode>();
		var current = node;
		stack.push(current);
		
		var types = new HashSet<Type>();
		var errors = new ArrayList<ExpressionNode>();
		
		while (!stack.isEmpty())
		{
			current = stack.pop();
			
			if (!visited.contains(current))
			{
				visited.add(current);
				
				for (var typing : current.typings)
				{
					if (!types.contains(typing.getType()))
					{
						base += "\n" + library.getName(typing.getType()) + " -> " + typing.getSymbol() + " : " + library.getName(typing.getSymbol().getType());
						types.add(typing.getType());
						errors.add(current);
					}
				}
				
				if (types.size() >= 2)
				{
					break;
				}
				
				for (var binding : current.bindings)
				{
					if (!visited.contains(binding.node))
					{
						stack.push(binding.node);
					}
				}
			}
		}
		
		for (var n : visited)
		{
			if (errors.contains(n))
			{
				list.add(new ProblemMessage(Severity.ERROR, base, n.expression, null));
			}
			else
			{
				list.add(new ProblemMessage(Severity.WARNING, base, n.expression, null));				
			}
		}
		
		return list;
	}
}
