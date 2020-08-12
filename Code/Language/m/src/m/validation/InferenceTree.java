package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.types.Type;
import m.m.Expression;
import m.validation.problems.Problem;
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.MultipleTypes;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Typing;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;

public class InferenceTree
{
	// All the typed nodes are critical nodes that must be traced up until a typed node is found
	// If the typed node has the same type -> no error
	// If the typed node has a different type -> type error
	// If there is no typed node -> no error
	
	Map<Expression, ExpressionNode> node;
	List<Problem> problems;
	List<ExpressionNode> criticalNodes;
	
	public InferenceTree(List<Problem> problems)
	{
		this.node = new HashMap<>();
		this.problems = problems;
		this.criticalNodes = new ArrayList<>();
	}
	
	public void bind(Expression a, Expression b, BindingReason reason)
	{
		var nodeA = node.get(a);
		var nodeB = node.get(b);
		
		if (nodeA == null && nodeB == null)
		{
			nodeA = checkIn(a);
			nodeB = checkIn(b);
			nodeA.binding = new Binding(nodeB, reason);
			criticalNodes.add(nodeA);
		}
		else if (nodeA == null && nodeB != null)
		{
			nodeA = checkIn(a);
			nodeA.binding = new Binding(nodeB, reason);
			criticalNodes.add(nodeA);
		}
		else if (nodeA != null && nodeB == null)
		{
			nodeB = checkIn(b);
			nodeB.binding = new Binding(nodeA, reason);
			criticalNodes.add(nodeB);
		}
		else
		{
			var rootA = nodeA;
			while (rootA.binding != null) { rootA = rootA.binding.node; }
			
			var rootB = nodeB;
			while (rootB.binding != null) { rootB = rootB.binding.node; }
			
			if (rootA != rootB)
			{
				if (rootA.typing == null)
				{
					reroot(nodeA);
					nodeA.binding = new Binding(nodeB, reason);
				}
				else
				{
					reroot(nodeB);
					nodeB.binding = new Binding(nodeA, reason);
				}
			}
		}
	}
	
	public void type(Expression a, Typing typing)
	{
		var nodeA = node.get(a);
		
		if (nodeA == null)
		{
			nodeA = checkIn(a);
			nodeA.typing = typing;
			criticalNodes.add(nodeA);
		}
		else
		{
			if (nodeA.typing == null)
			{
				nodeA.typing = typing;
				reroot(nodeA);
				criticalNodes.add(nodeA);
			}
			else if (nodeA.typing.getType() != typing.getType())
			{
				problems.add(new MultipleTypes(nodeA, typing));
			}
		}
	}
	
	public void check()
	{
		for (var criticalNode : criticalNodes)
		{
			var originalType = criticalNode.typing;
			
			var root = criticalNode;
			while (root.binding != null) {
				root = root.binding.node;
				if (root.typing != null)
				{
					break;
				}
			}
			
			var rootType = root.typing;
			
			if (originalType == null && rootType == null)
			{
				problems.add(new UndecidableType(criticalNode));
			}
			else if (originalType != null && rootType != null && originalType.getType() != rootType.getType())
			{
				problems.add(new IncompatibleTypes(criticalNode));
			}
		}
	}
	
	public Type infer(Expression a)
	{
		var rootA = node.get(a);
		
		while (rootA.typing == null)
		{
			rootA = rootA.binding.node;
		}
		
		return rootA.typing.getType();
	}
	
	private ExpressionNode checkIn(Expression e)
	{
		var expressionNode = new ExpressionNode(e);
		node.put(e, expressionNode);
		return expressionNode;
	}
	
	private void reroot(ExpressionNode node)
	{
		var current = node;
		var currentBinding = node.binding;

		while (currentBinding != null)
		{
			var tmp = currentBinding.node.binding;
			var tmpNode = currentBinding.node;
			currentBinding.node.binding = new Binding(current, current.binding.reason);
			current = tmpNode;
			currentBinding = tmp;
		}
		node.binding = null;
	}
}
