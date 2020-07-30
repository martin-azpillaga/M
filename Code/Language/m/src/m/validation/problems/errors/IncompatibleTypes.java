package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import m.library.Library;
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
		var root = node;
		
		while (root.binding != null)
		{
			root = root.binding.node;
		}
		base += "\n" + library.name(node.typing.getType()) + " - " + library.name(root.typing.getType());
		
		var me = new ProblemMessage(Severity.ERROR, base, node.expression, null);
		var last = new ProblemMessage(Severity.ERROR, base, root.expression, null);
		var list = new ArrayList<ProblemMessage>();
		list.add(me);
		list.add(last);
		return list;
	}

}
