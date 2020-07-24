package m.validation.rules;

public class Binding {
	public BindingReason reason;
	public ExpressionNode node;
	
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
