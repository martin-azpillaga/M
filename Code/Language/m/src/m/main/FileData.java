package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;

import m.m.Function;
import m.m.MFactory;
import m.validation.problems.Problem;
import m.validation.rules.ExpressionNode;

public class FileData
{
	public final String text;
	public final INode rootNode;
	public final EObject rootObject;
	public final List<ExpressionNode> nodes;
	public final Map<String, ExpressionNode> components;
	public final Map<String, Function> functions;
	public final List<Problem> problems;
	public final List<CrossReference> crossReferences;

	public FileData(String text, INode rootNode, EObject rootObject, List<ExpressionNode> nodes, Map<String, ExpressionNode> components, Map<String, Function> functions, List<Problem> problems)
	{
		this.text = text;
		this.rootNode = rootNode;
		this.rootObject = rootObject;
		this.nodes = nodes;
		this.components = components;
		this.functions = functions;
		this.problems = problems;
		this.crossReferences = new ArrayList<>();
	}

	public FileData(INode rootNode)
	{
		this.text = "";
		this.rootNode = rootNode;
		this.rootObject = MFactory.eINSTANCE.createFile();
		this.nodes = new ArrayList<>();
		this.components = new HashMap<>();
		this.functions = new HashMap<>();
		this.problems = new ArrayList<>();
		this.crossReferences = new ArrayList<>();
	}
}
