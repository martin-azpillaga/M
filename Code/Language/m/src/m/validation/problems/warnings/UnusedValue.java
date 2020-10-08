package m.validation.problems.warnings;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import m.library.Library;
import m.model.Value;
import m.validation.problems.Problem;

public class UnusedValue extends Problem
{
	Value value;

	public UnusedValue(Value value)
	{
		this.value = value;
	}

	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();

		list.add(new Diagnostic(getRange(value,text), library.getProblem(m.library.problems.Problem.UNUSED_VALUE), DiagnosticSeverity.Warning, "M", "6"));

		return list;
	}
}
