package m.validation.local.rules;

public class Binding
{
	public final BindingReason reason;
	public final ExpressionNode node;

	public Binding(ExpressionNode node, BindingReason reason)
	{
		this.node = node;
		this.reason = reason;
	}

	public enum BindingReason
	{
		SAME_COMPONENT,
		SAME_VARIABLE,
		PARAMETER_ARGUMENT,
		RESULT_RETURN,
		POLYMORPHISM
	}
}
