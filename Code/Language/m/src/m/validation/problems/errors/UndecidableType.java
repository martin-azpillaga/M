package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import m.library.Library;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;
import m.validation.rules.ExpressionNode;

public class UndecidableType implements Problem
{
	ExpressionNode node;
	
	public UndecidableType(ExpressionNode node)
	{
		this.node = node;
	}
	
	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var list = new ArrayList<ProblemMessage>();
		var message = library.getProblem(this.getClass());
		
		var visited = new HashSet<ExpressionNode>();
		var stack = new Stack<ExpressionNode>();
		
		stack.push(node);
		
		while (!stack.isEmpty())
		{
			var node = stack.pop();

			if (!visited.contains(node))
			{
				list.add(new ProblemMessage(Severity.ERROR, message, node.expression, null));
				visited.add(node);
				
				for (var binding : node.bindings)
				{
					if (!visited.contains(binding.node))
					{
						stack.push(binding.node);
					}
				}
			}
		}
		

		return list;
	}

}
