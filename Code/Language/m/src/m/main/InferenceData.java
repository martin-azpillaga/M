package m.main;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;

import m.m.Function;
import m.validation.problems.Problem;
import m.validation.rules.ExpressionNode;

public class InferenceData
{
	public final String text;
	public final INode rootNode;
	public final EObject rootObject;
	public final List<ExpressionNode> nodes;
	public final Map<String, ExpressionNode> components;
	public final Map<String, Function> functions;
	public final List<Problem> problems;

	public InferenceData(String text, INode rootNode, EObject rootObject, List<ExpressionNode> nodes, Map<String, ExpressionNode> components, Map<String, Function> functions, List<Problem> problems)
	{
		this.text = text;
		this.rootNode = rootNode;
		this.rootObject = rootObject;
		this.nodes = nodes;
		this.components = components;
		this.functions = functions;
		this.problems = problems;
	}
}
