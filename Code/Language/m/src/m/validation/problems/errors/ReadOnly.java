package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import m.library.Library;
import m.model.Value;
import m.validation.problems.Problem;

public class ReadOnly extends Problem
{
	Value value;

	public ReadOnly(Value value)
	{
		this.value = value;
	}

	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();

		var range = getRange(value,text);
		var message = library.getProblem(this.getClass());

		var diagnostic = new Diagnostic(range, message, DiagnosticSeverity.Error, "M", "5");

		list.add(diagnostic);

		return list;
	}

}
