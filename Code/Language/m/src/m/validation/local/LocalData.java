package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.model.Function;
import m.validation.problems.Problem;

public class LocalData
{
	public final List<ExpressionNode> nodes;
	public final Map<String, Function> functions;
	public final List<Problem> diagnostics;

	public LocalData()
	{
		this.nodes = new ArrayList<>();
		this.functions = new HashMap<>();
		this.diagnostics = new ArrayList<>();
	}

	public LocalData(List<ExpressionNode> nodes, Map<String,Function> functions, List<Problem> diagnostics)
	{
		this.nodes = nodes;
		this.functions = functions;
		this.diagnostics = diagnostics;
	}
}
