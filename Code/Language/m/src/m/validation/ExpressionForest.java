package m.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		
		var node = nodeOfExpression.get(expression);
		
		if (node == null && expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var component = cell.getComponent().getName();
			node = nodeOfComponent.get(component);
		}
		if (node == null)
		{
			node = new ExpressionNode();
			node.expression = expression;
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
		nodeOfExpression.put(expression, node);
		criticalNodes.add(node);
		if (expression instanceof Cell)
		{
			nodeOfComponent.put(((Cell) expression).getComponent().getName(), node);
		}
	}
	
	void group(Expression a, Expression b, BindingReason reason) {
		
		var nodeA = nodeOfExpression.get(a);
		if (nodeA == null && a instanceof Cell)
		{
			nodeA = nodeOfComponent.get(((Cell) a).getComponent().getName());
		}
		
		var nodeB = nodeOfExpression.get(b);
		if (nodeB == null && b instanceof Cell)
		{
			nodeB = nodeOfComponent.get(((Cell) b).getComponent().getName());
		}
		
		if (nodeA == null && nodeB == null)
		{
			var root = new ExpressionNode();
			nodeOfExpression.put(a, root);
			root.expression = a;
			var child = new ExpressionNode();
			nodeOfExpression.put(b, child);
			child.expression = b;
			child.reason = reason;
			child.parent = root;
			criticalNodes.add(root);
		}
		else if (nodeA == null && nodeB != null)
		{
			var newNode = new ExpressionNode();
			nodeOfExpression.put(a, newNode);
			newNode.expression = a;
			newNode.parent = nodeB;
			newNode.reason = reason;
		}
		else if (nodeA != null && nodeB == null)
		{
			var newNode = new ExpressionNode();
			nodeOfExpression.put(b, newNode);
			newNode.expression = b;
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
		}
	}
}
