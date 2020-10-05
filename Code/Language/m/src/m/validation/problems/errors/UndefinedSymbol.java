package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import m.library.Library;
import m.validation.problems.Problem;

public class UndefinedSymbol extends Problem
{
	EObject source;
	EStructuralFeature feature;

	public UndefinedSymbol(EObject source, EStructuralFeature feature)
	{
		this.source = source;
		this.feature = feature;
	}

	@Override
	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();
		var message = library.getProblem(this.getClass());
		var problem = new Diagnostic(getRange(source,feature,text), message, DiagnosticSeverity.Error, "M", "3");
		list.add(problem);
		return list;
	}
}
