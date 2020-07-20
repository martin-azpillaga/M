package m.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import m.library.Language;
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

public class ExpressionForest {
	
	Set<ExpressionNode> criticalNodes;
	Map<Expression, ExpressionNode> nodeOfExpression;
	Map<String, ExpressionNode> nodeOfComponent;
	
	public ExpressionForest ()
	{
		criticalNodes = new HashSet<>();
		nodeOfExpression = new HashMap<>();
		nodeOfComponent = new HashMap<>();
	}
	
	public void set(Expression expression, Type type, TypingReason reason) {
		
		// IF the node already has a type and the given is different, throw incompatible types error
		
		var node = find(expression);
		
		if (node == null)
		{
			node = checkIn(expression);
		}
		else
		{
			var root = node;
			var nextParent = root.parent;
			while (root.parent != null) {
				var tmp = root.parent;
				root.parent = nextParent;
				nextParent = tmp;
				root = root.parent;
			}
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
			var rootA = nodeA;
			var nextParent = nodeA;
			while (rootA.parent != null) {
				var tmp = rootA.parent;
				rootA.parent = nextParent;
				nextParent = tmp;
				rootA = rootA.parent;
			}
			
			nodeA.parent = nodeB;
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
}
