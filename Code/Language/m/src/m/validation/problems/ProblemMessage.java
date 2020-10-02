package m.validation.problems;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ProblemMessage
{
	public final EObject source;
	public final EStructuralFeature feature;
	public final String message;
	public final Severity severity;
	
	public ProblemMessage(Severity severity, String message, EObject source, EStructuralFeature feature)
	{
		this.source = source;
		this.feature = feature;
		this.severity = severity;
		this.message = message;
	}
	
	public enum Severity
	{
		INFO, WARNING, ERROR
	}
}
