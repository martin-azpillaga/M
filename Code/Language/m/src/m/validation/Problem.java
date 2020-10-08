package m.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class Problem
{
	public final INode node;
	public final Severity severity;
	public final String message;

	public Problem(INode node, Severity severity, String message)
	{
		this.node = node;
		this.severity = severity;
		this.message = message;
	}

	public Problem(EObject o, EStructuralFeature feature, Severity severity, String message)
	{
		this.node = NodeModelUtils.findNodesForFeature(o, feature).get(0);
		this.severity = severity;
		this.message = message;
	}

	public enum Severity
	{
		INFORMATION,WARNING,ERROR;
	}
}
