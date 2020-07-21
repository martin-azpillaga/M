package m.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import m.generator.Game;
import m.m.Cell;
import m.m.Expression;
import m.types.Type;

class ExpressionNode
{
	public Expression expression;
	public ExpressionNode parent;
	public BindingReason reason;
	public Type type;
	public TypingReason typingReason;
}

public class Inference {
	
	Set<ExpressionNode> criticalNodes;
	Map<Expression, ExpressionNode> nodeOfExpression;
	Map<String, ExpressionNode> nodeOfComponent;
	
	public Inference ()
	{
		criticalNodes = new HashSet<>();
		nodeOfExpression = new HashMap<>();
		nodeOfComponent = new HashMap<>();
	}
	
	public void set(Expression expression, Type type, TypingReason reason) {
		
		
		var node = find(expression);
		
		if (node == null)
		{
			node = checkIn(expression);
		}
		else
		{
			// IF the node already has a type and the given is different, throw incompatible types error
			if (node.type != null && node.type != type)
			{
				// error
			}
			reroot(node, true); // remove redundant critical nodes
			
			node.parent = null;
			node.reason = null;
		}
		node.type = type;
		node.typingReason = reason;
		criticalNodes.add(node);
	}
	
	void group(Expression a, Expression b, BindingReason reason) {
		
		var nodeA = find(a);
		var nodeB = find(b);
		
		if (nodeA == null && nodeB == null)
		{
			var root = checkIn(a);
			var child = checkIn(b);
			
			child.reason = reason;
			child.parent = root;
			criticalNodes.add(root);
		}
		else if (nodeA == null && nodeB != null)
		{
			var newNode = checkIn(a);
			
			newNode.parent = nodeB;
			newNode.reason = reason;
		}
		else if (nodeA != null && nodeB == null)
		{
			var newNode = checkIn(b);
			
			newNode.parent = nodeA;
			newNode.reason = reason;
		}
		else
		{
			reroot(nodeA, false);
			
			nodeA.parent = nodeB;
			nodeA.reason = reason;
		}
	}
	
	public void note(Expression expression)
	{
		var node = find(expression);
		if (node == null)
		{
			node = checkIn(expression);
			criticalNodes.add(node);
		}
	}
	
	private ExpressionNode find(Expression expression)
	{
		var node = nodeOfExpression.get(expression);
		if (node == null && expression instanceof Cell)
		{
			node = nodeOfComponent.get(((Cell) expression).getComponent().getName());
		}
		return node;
	}
	
	private ExpressionNode checkIn(Expression expression)
	{
		var node = new ExpressionNode();
		node.expression = expression;
		nodeOfExpression.put(expression, node);
		if (expression instanceof Cell)
		{
			var component = ((Cell)expression).getComponent().getName();
			nodeOfComponent.put(component, node);
		}
		return node;
	}
	
	private void reroot(ExpressionNode node, boolean removeRedundant)
	{
		var parent = node.parent;
		
		var nextParent = node;
		var nextReason = node.reason;
		
		while (parent != null) {
			// Possibly slower because of contains search but worth benchmarking
			if (removeRedundant && criticalNodes.contains(parent))
			{
				criticalNodes.remove(parent);
			}
			var parentParent = parent.parent;
			var parentReason = parent.reason;
			parent.parent = nextParent;
			parent.reason = nextReason;
			nextParent = parent;
			nextReason = parentReason;
			parent = parentParent;
		}
	}
	
	public Game infer() {
		var game = new Game();
		for (var criticalNode : criticalNodes)
		{
			var originalType = criticalNode.type;
			
			var root = criticalNode;
			while (root.parent != null) {
				root = root.parent;
			}
			
			var rootType = root.type;
			
			if (originalType == null && root.type == null)
			{
				System.out.println(criticalNode.expression + " is indetermined");
			}
			else if (originalType != null && originalType != root.type)
			{
				System.out.println(criticalNode.expression + " is incompatible: " + originalType + " " + rootType);
			}
		}
		for (var component : nodeOfComponent.keySet())
		{
			var root = nodeOfComponent.get(component);
			while (root.parent != null)
			{
				if (root.parent != null)
				{
					System.out.println("Linked to " + root.parent.expression + " because " + root.reason);
				}
				root = root.parent;
			}
			game.components.put(component, root.type);
			System.out.println(component + " has type " + root.type + " because " + root.typingReason);
		}
		
		return game;
	}
}
