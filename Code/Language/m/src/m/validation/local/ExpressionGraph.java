package m.validation.local;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import m.library.rules.BindingReason;
import m.model.Expression;
import m.model.Function;
import m.validation.Problem;
import m.validation.local.ExpressionNode.Binding;
import m.validation.local.ExpressionNode.Typing;

public class ExpressionGraph
{
	HashSet<ExpressionNode> connectedComponents;
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;

	public ExpressionGraph()
	{
		connectedComponents = new HashSet<>();
		map = new HashMap<>();
	}

	public void bind(Expression a, Expression b, BindingReason reason)
	{
		if (a == null || b == null) return;

		var nodeA = get(a);
		var nodeB = get(b);

		nodeA.bindings.add(new Binding(nodeB, reason));
		nodeB.bindings.add(new Binding(nodeA, reason));

		if (connectedComponents.contains(nodeA))
		{
			if (connectedComponents.contains(nodeB))
			{
				connectedComponents.remove(nodeB);
			}
		}
		else
		{
			if (!connectedComponents.contains(nodeB))
			{
				connectedComponents.add(nodeB);
			}
		}
	}

	public void type(Expression a, Typing typing)
	{
		if (a == null) return;

		var node = get(a);
		node.typings.add(typing);

		if (node.bindings.isEmpty())
		{
			connectedComponents.add(node);
		}
	}

	private ExpressionNode get(Expression a)
	{
		var node = map.get(a);
		if (node == null)
		{
			node = new ExpressionNode(a);
			map.put(a, node);
		}
		return node;
	}

	public LocalValidator.Result buildData(Map<String,Function> functions, List<Problem> problems)
	{
		return new LocalValidator.Result(connectedComponents, functions, problems);
	}
}