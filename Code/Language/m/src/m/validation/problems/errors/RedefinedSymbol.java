package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.library.Library;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;

public class RedefinedSymbol implements Problem
{
	public EObject source;
	public EStructuralFeature feature;
	
	public RedefinedSymbol(EObject source, EStructuralFeature feature)
	{
		this.source = source;
		this.feature = feature;
	}
	
	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var list = new ArrayList<ProblemMessage>();
		var problem = new ProblemMessage(Severity.ERROR, library.getProblem(this.getClass()), source, feature);
		list.add(problem);
		return list;
	}
}
