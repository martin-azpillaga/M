package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import m.library.Library;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Typing;

public class MultipleTypes implements Problem
{
	ExpressionNode node;
	Typing extraTyping;
	
	public MultipleTypes(ExpressionNode node, Typing extraTyping)
	{
		this.node = node;
		this.extraTyping = extraTyping;
	}

	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var list = new ArrayList<ProblemMessage>();
		
		
		var message = "Multiple types\n"+
		library.name(node.typing.getType()) + " -> " + node.typing.getSymbol() +" : " + library.name(node.typing.getSymbol().getType())+ "\n"+
		library.name(extraTyping.getType()) + " -> " + extraTyping.getSymbol() +" : " + library.name(extraTyping.getSymbol().getType());
		
		var problemMessage = new ProblemMessage(Severity.ERROR, message, node.expression, null);
		
		list.add(problemMessage);
		return list;
	}
}
