package m.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import m.library.rules.BindingReason;
import m.library.rules.TypingReason;
import m.library.symbols.Symbol;
import m.library.types.Type;

public class ExpressionNode implements Iterable<ExpressionNode>
{
	public final Expression expression;
	public final List<Binding> bindings;
	public final List<Typing> typings;

	public ExpressionNode(Expression expression)
	{
		this.expression = expression;
		this.bindings = new ArrayList<>();
		this.typings = new ArrayList<>();
	}

	public Iterator<ExpressionNode> iterator()
	{
		return new NodeIterator(this);
	}

	public static class Typing
	{
		public final Type type;
		public final TypingReason reason;
		public final Symbol symbol;

		public Typing(Type type, TypingReason reason, Symbol symbol)
		{
			this.type = type;
			this.symbol = symbol;
			this.reason = reason;
		}
	}

	public static class Binding
	{
		public final BindingReason reason;
		public final ExpressionNode node;

		public Binding(ExpressionNode node, BindingReason reason)
		{
			this.node = node;
			this.reason = reason;
		}
	}

	public static class NodeIterator implements Iterator<ExpressionNode>
	{
		Deque<ExpressionNode> stack;
		Set<ExpressionNode> visited;

		public NodeIterator(ExpressionNode root)
		{
			this.stack = new ArrayDeque<ExpressionNode>();
			this.stack.push(root);
			this.visited = new HashSet<>();
		}

		public boolean hasNext()
		{
			return ! stack.isEmpty();
		}

		public ExpressionNode next()
		{
			var current = stack.pop();

			visited.add(current);

			for (var binding : current.bindings)
			{
				var node = binding.node;

				if (!visited.contains(node))
				{
					stack.push(node);
				}
			}

			return current;
		}
	}
}
