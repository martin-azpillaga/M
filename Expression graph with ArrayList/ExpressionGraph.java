package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.rules.BindingReason;
import m.model.Expression;
import m.model.Function;
import m.validation.Problem;

public class ExpressionGraph
{
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;

	public ExpressionGraph()
	{
		list = new ArrayList<>();
		map = new HashMap<>();
	}

	public void bind(Expression a, Expression b, BindingReason reason)
	{
		if (a == null || b == null) return;

		var nodeA = get(a, true);
		var nodeB = get(b, false);

		nodeA.bindings.add(new Binding(nodeB, reason));
		nodeB.bindings.add(new Binding(nodeA, reason));
	}

	public void type(Expression a, Typing typing)
	{
		if (a == null) return;

		var node = get(a, true);
		node.typings.add(typing);
	}

	private ExpressionNode get(Expression a, boolean addToList)
	{
		var node = map.get(a);
		if (node == null)
		{
			node = new ExpressionNode(a);
			map.put(a, node);
			if (addToList)
			{
				list.add(node);
			}
		}
		return node;
	}

	public LocalData buildData(Map<String,Function> functions, List<Problem> problems)
	{
		return new LocalData(list, functions, problems);
	}
}
