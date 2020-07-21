package m.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.m.Expression;
import m.types.Type;

public class IncompatibleTypes extends Problem {
	public List<Link> links;
	public Type t1;
	public Type t2;
	public TypingReason t1Reason;
	public TypingReason t2Reason;
	
	public IncompatibleTypes(EObject source, EStructuralFeature feature, Type t1, TypingReason t1Reason, Type t2, TypingReason t2Reason, List<Link> links)
	{
		this.source = source;
		this.feature = feature;
		this.severity = Severity.ERROR;
		this.links = links;
		this.t1 = t1;
		this.t2 = t2;
		this.t1Reason = t1Reason;
		this.t2Reason = t2Reason;
	}
}
