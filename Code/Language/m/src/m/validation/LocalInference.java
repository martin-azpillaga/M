package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.m.Cell;
import m.m.Expression;
import m.main.InferenceData;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Typing;

public class LocalInference 
{
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;
	
	public LocalInference()
	{
		list = new ArrayList<>();
		map = new HashMap<>();
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
