package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.model.Function;
import m.validation.Problem;

public class LocalData
{
	public final List<ExpressionNode> expressionGraph;
	public final Map<String, Function> functions;
	public final List<Problem> diagnostics;

	public LocalData()
	{
		this.expressionGraph = new ArrayList<>();
		this.functions = new HashMap<>();
		this.diagnostics = new ArrayList<>();
	}

	public LocalData(List<ExpressionNode> expressionGraph, Map<String,Function> functions, List<Problem> diagnostics)
	{
		this.expressionGraph = expressionGraph;
		this.functions = functions;
		this.diagnostics = diagnostics;
	}
}
