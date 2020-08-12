package m.validation.rules;

import java.util.ArrayList;
import java.util.List;

import m.m.Expression;

public class ExpressionNode
{
	public Expression expression;
	public Binding binding;
	public Typing typing;
	public List<Binding> bindings;
	public List<Typing> typings;
	
	public ExpressionNode(Expression expression)
	{
		this.expression = expression;
		this.bindings = new ArrayList<>();
		this.typings = new ArrayList<>();
	}
}
