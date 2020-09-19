package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import m.library.types.Type;
import m.m.Cell;
import m.m.Expression;
import m.main.InferenceData;
import m.validation.problems.Problem;
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Typing;

public class InferenceGraph 
{
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;
	Map<ExpressionNode, Type> inference;
	
	public InferenceGraph()
	{
		list = new ArrayList<>();
		map = new HashMap<>();
		inference = new HashMap<>();
	}
	
	public InferenceGraph(List<ExpressionNode> nodes)
	{
		this.list = nodes;
		this.inference = new HashMap<>();
	}
	
	public void bind(Expression a, Expression b, BindingReason reason)
	{
		if (a == null || b == null) return;

		var nodeA = get(a);
		var nodeB = get(b);
		
		nodeA.bindings.add(new Binding(nodeB, reason));
		nodeB.bindings.add(new Binding(nodeA, reason));
	}
	
	public void type(Expression a, Typing typing)
	{
		if (a == null) return;

		var node = get(a);
		node.typings.add(typing);
	}
	
	public List<Problem> check()
	{
		var problems = new ArrayList<Problem>();
		
		var index = 0;
		var visited = new ArrayList<ExpressionNode>();
		var stack = new Stack<ExpressionNode>();
		
		HashSet<Type> types = new HashSet<>();
		
		for (var root : list)
		{
			if (!visited.contains(root))
			{
				stack.push(root);
				types = new HashSet<>();
				index = visited.size();
			}
			else
			{
				continue;
			}
		
			while (!stack.empty())
			{
				var node = stack.pop();
				if (!visited.contains(node))
				{
					visited.add(node);
					
					for (var typing : node.typings)
					{
						types.add(typing.getType());
					}
					
					for (var binding : node.bindings)
					{
						if (!visited.contains(binding.node))
						{
							stack.push(binding.node);
						}
					}
				}
			}
			
			if (types.size() == 0)
			{
				problems.add(new UndecidableType(root));
			}
			else if (types.size() == 1)
			{
				var type = types.iterator().next();
				
				for (var i = index; i < visited.size(); i++)
				{
					inference.put(visited.get(i), type);
				}
			}
			else if (types.size() > 1)
			{
				problems.add(new IncompatibleTypes(root));
			} 
		}
		
		return problems;
	}
	
	public Type infer(Expression a)
	{
		for (var entry : inference.entrySet())
		{
			if (a == entry.getKey().expression)
			{
				return entry.getValue();
			}
		}
		return null;
	}
	
	public Type infer(ExpressionNode node)
	{
		return inference.get(node);
	}
	
	private ExpressionNode get(Expression a)
	{
		var node = map.get(a);
		if (node == null)
		{
			node = new ExpressionNode(a);
			list.add(node);
			map.put(a, node);
		}
		return node;
	}
	
	public InferenceData getInferenceData(Map<String, Cell> components)
	{
		var data = new InferenceData();
		
		data.nodes.addAll(this.list);
		
		for (var component : components.entrySet())
		{
			var node = map.get(component.getValue());
			
			data.components.put(component.getKey(), node);
		}
		
		return data;
	}
}