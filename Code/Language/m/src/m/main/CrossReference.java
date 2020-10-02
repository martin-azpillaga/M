package m.main;

import m.validation.rules.Binding;
import m.validation.rules.ExpressionNode;

public class CrossReference
{
	public final String fileA;
	public final ExpressionNode nodeA;
	public final Binding bindingA;
	
	public final String fileB;
	public final ExpressionNode nodeB;
	public final Binding bindingB;

	public CrossReference(String fileA, ExpressionNode nodeA, Binding bindingA, String fileB, ExpressionNode nodeB, Binding bindingB)
	{
		this.fileA = fileA;
		this.nodeA = nodeA;
		this.bindingA = bindingA;
		this.fileB = fileB;
		this.nodeB = nodeB;
		this.bindingB = bindingB;
	}
}
