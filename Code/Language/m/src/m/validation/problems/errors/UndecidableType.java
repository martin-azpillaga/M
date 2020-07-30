package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

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
		var root = node;
		while (root.binding != null)
		{
			list.add(new ProblemMessage(Severity.ERROR, message, root.expression, null));
			root = root.binding.node;
		}

		return list;
	}

}
