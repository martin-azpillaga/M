package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.xtext.nodemodel.INode;

import m.library.Library;
import m.validation.problems.Problem;

public class SyntaxError extends Problem
{
	INode node;

	public SyntaxError(INode node)
	{
		this.node = node;
	}

	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();

		var message = library.getProblem(m.library.problems.Problem.SYNTAX_ERROR);

		list.add(new Diagnostic(getRange(node, text), message, DiagnosticSeverity.Error, "M", "0"));

		return list;
	}

}
