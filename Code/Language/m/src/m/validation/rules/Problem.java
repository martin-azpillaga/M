package m.validation.rules;

import m.validation.ExpressionNode;

public class Problem
{
	public ExpressionNode node;
	public ProblemKind kind;
	
	public Problem(ExpressionNode node, ProblemKind kind)
	{
		this.node = node;
		this.kind = kind;
	}
	
	public enum ProblemKind
	{
		UNDEFINED, REDEFINED, INDETERMINATE, INCOMPATIBLE
	}
}
