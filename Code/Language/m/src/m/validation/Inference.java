package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.generator.Game;
import m.m.Cell;
import m.m.Expression;
import m.m.Function;
import m.types.AtomicType;
import m.types.FunctionType;
import m.types.Type;

class ExpressionNode
{
	public Expression expression;
	public ExpressionNode parent;
	public BindingReason reason;
	public Type type;
	public TypingReason typingReason;
}

class Link
{
	public Expression expression;
	public BindingReason reason;
}

public class Inference {
	
	Set<ExpressionNode> criticalNodes;
	Map<Expression, ExpressionNode> nodeOfExpression;
	Map<String, ExpressionNode> nodeOfComponent;
	List<Problem> problems;
	
	public Inference (List<Problem> problems)
	{
		this.problems = problems;
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
				problems.add(new IncompatibleTypes(expression, null, node.type, node.typingReason, type, reason, null));
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
	
	public void group(Expression a, Expression b, BindingReason reason) {
		
		var nodeA = find(a);
		var nodeB = find(b);
		
		if (nodeA == null && nodeB == null)
		{
			var root = checkIn(a);
			var child = checkIn(b);
			
			child.reason = reason;
			child.parent = root;
			criticalNodes.add(child);
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
			if (removeRedundant && parent.type == null && criticalNodes.contains(parent))
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
	
	public void check()
	{	
		for (var criticalNode : criticalNodes)
		{
			var originalType = criticalNode.type;
			
			var links = new ArrayList<Link>();
			
			var root = criticalNode;
			while (root.parent != null) {
				var link = new Link();
				link.expression = root.parent.expression;
				link.reason = root.reason;
				links.add(link);
				root = root.parent;
			}
			
			var rootType = root.type;
			
			if (originalType == null && root.type == null)
			{
				problems.add(new UndecidableType(criticalNode.expression, null, links));
			}
			else if (originalType != null && originalType != root.type)
			{
				problems.add(new IncompatibleTypes(criticalNode.expression, null, originalType, criticalNode.typingReason, rootType, root.typingReason, links));
			}
		}
	}
	
	public Game infer(Map<String,Cell> userComponents, Map<String, Function> userFunctions)
	{
		var game = new Game();
		
		for (var component : userComponents.keySet())
		{
			var node = nodeOfComponent.get(component);
			var root = node;
			while (root.parent != null)
			{
				root = root.parent;
			}
			game.components.put(component, root.type);
			System.out.println(component + " has type " + root.type + " because " + root.typingReason);
		}
		
		for (var userFunction : userFunctions.entrySet())
		{
			var name = userFunction.getKey();
			var function = userFunction.getValue();
			
			var parameters = new Type[function.getParameters().size()];
			var returnType = AtomicType.UNIT;
			
			var type = new FunctionType(parameters, returnType);
			
			System.out.println(name + " is a function of type " + parameters + " -> " + returnType);
			
			game.functions.put(userFunction.getValue(), type);
		}
		
		return game;
	}
}
