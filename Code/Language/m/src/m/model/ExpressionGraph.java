package m.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import m.library.rules.BindingReason;
import m.model.ExpressionNode.Binding;
import m.model.ExpressionNode.Typing;

public class ExpressionGraph
{
	public final HashSet<ExpressionNode> connectedComponents;
	Map<Expression, ExpressionNode> map;
	Map<ExpressionNode, ExpressionNode> root;

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
}
