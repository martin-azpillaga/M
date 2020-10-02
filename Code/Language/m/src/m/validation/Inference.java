package m.validation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
	Map<ExpressionNode, Type> result;
	
	public Inference(List<ExpressionNode> nodes)
	{
		this.list = nodes;
		this.map = new HashMap<>();
		this.result = new HashMap<>();
	}
	
	public List<Problem> check()
	{
		var problems = new ArrayList<Problem>();
		
		var index = 0;
		var visited = new ArrayList<ExpressionNode>();
		var stack = new ArrayDeque<ExpressionNode>();
		
		HashSet<Type> types;
		
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
		
			while (!stack.isEmpty())
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
						if (!visited.contains(binding.node))
						{
							stack.push(binding.node);
						}
					}
				}
			}
			
			if (types.isEmpty())
			{
				problems.add(new UndecidableType(root));
			}
			else if (types.size() == 1)
			{
				var type = types.iterator().next();
				
				for (var i = index; i < visited.size(); i++)
				{
					result.put(visited.get(i), type);
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
		for (var entry : result.entrySet())
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
		for (var entry : result.entrySet())
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
		return result.get(node);
	}	
}