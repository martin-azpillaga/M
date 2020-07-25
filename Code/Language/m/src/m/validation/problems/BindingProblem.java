package m.validation.problems;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class BindingProblem implements Problem
{
	public enum BindingProblemKind
	{
		UNDEFINED, REDEFINED
	}
	
	BindingProblemKind kind;
	EObject source;
	EStructuralFeature feature;
	
	public BindingProblem(EObject source, EStructuralFeature feature, BindingProblemKind kind)
	{
		this.source = source;
		this.feature = feature;
		this.kind = kind;
	}
	
	public BindingProblemKind getKind()
	{
		return kind;
	}
	
	public EObject getSource()
	{
		return source;
	}
	
	public EStructuralFeature getFeature()
	{
		return feature;
	}
}
