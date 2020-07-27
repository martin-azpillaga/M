package m.validation.problems;

import m.validation.rules.ExpressionNode;

public class TypingProblem implements Problem
{
	public enum TypingProblemKind
	{
		INDETERMINATE, INCOMPATIBLE
	}
	
	TypingProblemKind kind;
	ExpressionNode node;
	
	public TypingProblem(ExpressionNode node, TypingProblemKind kind)
	{
		this.node = node;
		this.kind = kind;
	}
	
	public TypingProblemKind getKind()
	{
		return kind;
	}
	
	public ExpressionNode getNode()
	{
		return node;
	}
}
