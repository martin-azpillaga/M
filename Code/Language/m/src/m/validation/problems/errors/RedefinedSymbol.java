package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import m.library.Library;
import m.validation.problems.Problem;

public class RedefinedSymbol extends Problem
{
	EObject source;
	EStructuralFeature feature;
	
	public RedefinedSymbol(EObject source, EStructuralFeature feature)
	{
		this.source = source;
		this.feature = feature;
	}
	
	@Override
	public List<Diagnostic> diagnostics(Library library, String text)
	{
		var list = new ArrayList<Diagnostic>();
		var problem = new Diagnostic(getRange(source,text), library.getProblem(this.getClass()), DiagnosticSeverity.Error, "M", "4");
		list.add(problem);
		return list;
	}
}
