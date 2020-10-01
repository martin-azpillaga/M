package m.validation.rules;

public class Binding
{
	BindingReason reason;
	ExpressionNode node;
	
	public Binding(ExpressionNode node, BindingReason reason)
	{
		this.node = node;
		this.reason = reason;
	}

	public BindingReason getReason()
	{
		return reason;
	}

	public ExpressionNode getNode()
	{
		return node;
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
