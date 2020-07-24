package m.validation;

import static m.validation.rules.Problem.ProblemKind.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import m.generator.Game;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.m.Cell;
import m.m.Expression;
import m.m.Function;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.Problem;
import m.validation.rules.Typing;


public class Inference {
	
	Set<ExpressionNode> criticalNodes;
	public Map<Expression, ExpressionNode> nodeOfExpression;
	Map<String, ExpressionNode> nodeOfComponent;
	public Map<EObject, ExpressionNode> nodeOf;
	List<Problem> problems;
	
	public Inference (List<Problem> problems)
	{
		this.problems = problems;
		criticalNodes = new HashSet<>();
		nodeOfExpression = new HashMap<>();
		nodeOfComponent = new HashMap<>();
	}
	
	public void type(Expression expression, Typing typing) {
		
		
		var node = find(expression);
		
		if (node == null)
		{
			node = checkIn(expression);
		}
		else
		{
			// IF the node already has a type and the given is different, throw incompatible types error
			if (node.typing != null && node.typing.getType() != typing.getType())
			{
				problems.add(new Problem(nodeOf.get(expression), INCOMPATIBLE));
				// error
			}
			reroot(node, true); // remove redundant critical nodes
			
			node.binding = null;
		}
		node.typing = typing;
		criticalNodes.add(node);
	}
	
	public void bind(Expression a, Expression b, BindingReason reason) {
		
		var nodeA = find(a);
		var nodeB = find(b);
		
		if (nodeA == null && nodeB == null)
		{
			var root = checkIn(a);
			var child = checkIn(b);
			
			child.binding = new Binding(root, reason);
			criticalNodes.add(child);
		}
		else if (nodeA == null && nodeB != null)
		{
			var newNode = checkIn(a);
			
			newNode.binding = new Binding(nodeB, reason);
		}
		else if (nodeA != null && nodeB == null)
		{
			var newNode = checkIn(b);
			newNode.binding = new Binding(nodeA, reason);
		}
		else if (nodeA == nodeB)
		{
			
		}
		else
		{
			reroot(nodeA, false);
			
			// if nodeB is already linked with nodeA do nothing (otherwise there is a cycle)
			var parent = nodeB.binding;
			var found = false;
			while (parent != null && !found)
			{
				found = parent.node == nodeA;
				parent = parent.node.binding;
			}
			if (!found)
			{
				nodeA.binding = new Binding(nodeB, reason);
			}
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
		var parent = node.binding;
		
		var nextParent = node.binding;
		
		while (parent != null) {
			// Possibly slower because of contains search but worth benchmarking
			if (removeRedundant && parent.node.typing == null && criticalNodes.contains(parent))
			{
				criticalNodes.remove(parent.node);
			}
			var parentParent = parent.node.binding;
			parent.node.binding = nextParent;
			nextParent = parent;
			parent = parentParent;
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
			}
			
			var rootType = root.typing;
			
			if (originalType == null && root.typing == null)
			{
				problems.add(new Problem(nodeOf.get(criticalNode.expression), INDETERMINATE));
			}
			else if (originalType != null && originalType != root.typing.getType())
			{
				problems.add(new Problem(nodeOf.get(criticalNode.expression), INDETERMINATE));
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
			while (root.binding != null)
			{
				root = root.binding.node;
			}
			game.components.put(component, root.typing.getType());
		}
		
		for (var userFunction : userFunctions.entrySet())
		{
			var name = userFunction.getKey();
			var function = userFunction.getValue();
			
			var parameters = new Type[function.getParameters().size()];
			var returnType = AtomicType.UNIT;
			
			var type = new FunctionType(parameters, returnType);
			
			
			game.functions.put(userFunction.getValue(), type);
		}
		
		return game;
	}
}
