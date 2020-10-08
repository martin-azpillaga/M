package m.validation.local;

import m.library.problems.BindingReason;

public class Binding
{
	public final BindingReason reason;
	public final ExpressionNode node;

	public Binding(ExpressionNode node, BindingReason reason)
	{
		this.node = node;
		this.reason = reason;
	}
}
