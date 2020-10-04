package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.Diagnostic;

import m.m.Expression;
import m.m.Function;
import m.validation.local.rules.Binding;
import m.validation.local.rules.Binding.BindingReason;
import m.validation.local.rules.ExpressionNode;
import m.validation.local.rules.Typing;

public class ExpressionGraph 
{
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;
	
	public ExpressionGraph()
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
    
    public LocalData buildData(Map<String,Function> functions, List<Diagnostic> problems)
	{
		return new LocalData(list, functions, problems);
	}
}
