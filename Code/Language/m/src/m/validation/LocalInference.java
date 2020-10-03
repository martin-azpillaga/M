package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;

import m.m.Cell;
import m.m.Expression;
import m.m.Function;
import m.main.FileData;
import m.validation.problems.Problem;
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
    
    public FileData buildData(String text, INode rootNode, EObject file, Map<String, Cell> cells, Map<String,Function> functions, List<Problem> problems)
	{
        var components = new HashMap<String, ExpressionNode>();
        for (var cell : cells.entrySet())
		{
			var node = map.get(cell.getValue());
			
			components.put(cell.getKey(), node);
        }
        
        return new FileData(text, rootNode, file, list, components, functions, problems);
	}
}
