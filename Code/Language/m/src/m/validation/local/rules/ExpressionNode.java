package m.validation.local.rules;

import java.util.ArrayList;
import java.util.List;

import m.m.Expression;

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
}
