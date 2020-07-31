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
		var list = new ArrayList<ProblemMessage>();
		
		while (root.binding != null)
		{
			list.add(new ProblemMessage(Severity.WARNING, base, root.expression, null));
			root = root.binding.node;
		}
		base += "\n" + library.name(node.typing.getType()) + " - " + library.name(node.typing.getSymbol().getType()) + "\n" +
		library.name(root.typing.getType()) + " - " + library.name(root.typing.getSymbol().getType());
		
		var me = new ProblemMessage(Severity.ERROR, base, node.expression, null);
		var last = new ProblemMessage(Severity.ERROR, base, root.expression, null);
		list.add(me);
		list.add(last);
		return list;
	}
}
