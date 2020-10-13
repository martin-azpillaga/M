package m.validation.local;

import java.util.ArrayList;
import java.util.List;

import m.library.rules.BindingReason;
import m.library.rules.TypingReason;
import m.library.symbols.Symbol;
import m.library.types.Type;
import m.model.Expression;

public class ExpressionNode
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
}
