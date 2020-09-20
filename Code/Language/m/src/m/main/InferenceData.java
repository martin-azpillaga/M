package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;

import m.m.Function;
import m.validation.problems.Problem;
import m.validation.rules.ExpressionNode;

public class InferenceData
{
	public List<ExpressionNode> nodes = new ArrayList<>();
	public Map<String, ExpressionNode> components = new HashMap<>();
	public Map<String, Function> functions = new HashMap<>();
	public List<Problem> problems = new ArrayList<>();
	public EObject rootObject;
	public String text;
	public INode rootNode;
}
