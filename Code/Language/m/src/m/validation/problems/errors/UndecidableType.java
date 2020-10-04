package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import java.util.ArrayDeque;

import m.library.Library;
import m.validation.problems.Problem;
import m.validation.local.rules.ExpressionNode;

public class UndecidableType extends Problem
{
	public final ExpressionNode node;
	
	public UndecidableType(ExpressionNode node)
	{
		this.node = node;
	}
	
	@Override
	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();
		var message = library.getProblem(getClass());
		
		var visited = new HashSet<ExpressionNode>();
		var stack = new ArrayDeque<ExpressionNode>();
		
		stack.push(node);
		
		while (!stack.isEmpty())
		{
			var n = stack.pop();

			if (!visited.contains(n))
			{
				list.add(new Diagnostic(getRange(n.expression,text), message, DiagnosticSeverity.Error, "M", "2"));
				visited.add(n);
				
				for (var binding : n.bindings)
				{
					if (!visited.contains(binding.node))
					{
						stack.push(binding.node);
					}
				}
			}
		}
		return list;
	}
}
