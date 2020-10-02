package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import m.library.types.Type;
import m.m.Expression;
import m.validation.problems.Problem;
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.ExpressionNode;

public class Inference 
{
	List<ExpressionNode> list;
	Map<Expression, ExpressionNode> map;
	Map<ExpressionNode, Type> inference;
	
	public Inference(List<ExpressionNode> nodes)
	{
		this.list = nodes;
		this.map = new HashMap<>();
		this.inference = new HashMap<>();
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
						types.add(typing.type);
					}
					
					for (var binding : node.bindings)
					{
						if (!visited.contains(binding.getNode()))
						{
							stack.push(binding.getNode());
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

	public ExpressionNode info(Expression a)
	{
		for (var entry : inference.entrySet())
		{
			if (a == entry.getKey().expression)
			{
				return entry.getKey();
			}
		}
		return null;
	}
	
	public Type infer(ExpressionNode node)
	{
		return inference.get(node);
	}	
}