package m.validation.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.Diagnostic;
import m.m.Function;
import m.validation.local.rules.ExpressionNode;

public class LocalData
{
	public final List<ExpressionNode> nodes;
	public final Map<String, Function> functions;
	public final List<Diagnostic> diagnostics;

	public LocalData()
	{
		this.nodes = new ArrayList<>();
		this.functions = new HashMap<>();
		this.diagnostics = new ArrayList<>();
	}

	public LocalData(List<ExpressionNode> nodes, Map<String,Function> functions, List<Diagnostic> diagnostics)
	{
		this.nodes = nodes;
		this.functions = functions;
		this.diagnostics = diagnostics;
	}
}
