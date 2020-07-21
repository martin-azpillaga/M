package m.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import m.m.Expression;
import m.m.Value;

public class Context {
	
	Map<String, Expression> map;
	ExpressionForest forest;
	Stack<Map<String,Expression>> stack;
	
	public Context(ExpressionForest forest) {
		this.forest = forest;
		this.map = new HashMap<>();
		this.stack = new Stack<>();
	}
	
	public void declare(Value value)
	{
		if (map.containsKey(value.getName()))
		{
			forest.group(value, map.get(value.getName()), BindingReason.sameVariable);
		}
		else
		{
			map.put(value.getName(), value);
		}
	}
	
	public void checkDeclared(Value value)
	{
		var name = value.getName();
		if (!map.containsKey(name))
		{
			// error
		}
		else
		{
			forest.group(value, map.get(name), BindingReason.sameVariable);
		}
	}
	
	public void push()
	{
		stack.push(new HashMap<>(map));
	}
	
	public void pop()
	{
		map = stack.pop();
	}
}
