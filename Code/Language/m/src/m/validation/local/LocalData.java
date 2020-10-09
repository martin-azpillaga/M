package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.model.Function;
import m.validation.Problem;

public class LocalData
{
	public final Set<ExpressionNode> expressionGraph;
	public final Map<String, Function> functions;
	public final List<Problem> problems;

	public LocalData()
	{
		this.expressionGraph = new HashSet<>();
		this.functions = new HashMap<>();
		this.problems = new ArrayList<>();
	}

	public LocalData(Set<ExpressionNode> expressionGraph, Map<String,Function> functions, List<Problem> problems)
	{
		this.expressionGraph = expressionGraph;
		this.functions = functions;
		this.problems = problems;
	}
}
