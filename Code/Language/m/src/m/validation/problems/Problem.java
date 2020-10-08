package m.validation.problems;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public abstract class Problem
{
	protected Range getRange(EObject o, String text)
	{
		var node = NodeModelUtils.getNode(o);
		return getRange(node, text);
	}

	protected Range getRange(EObject o, EStructuralFeature feature, String text)
	{
		var nodes = NodeModelUtils.findNodesForFeature(o, feature);
		var node = nodes.get(0);
		return getRange(node, text);
	}

	protected Range getRange(INode node, String text)
	{
		return new Range
		(
			new Position(node.getStartLine()-1, character(text, node.getOffset())),
			new Position(node.getEndLine()-1  , character(text, node.getOffset()))
		);
	}

	private int character(String text, int offset)
	{
		var count = 0;
		for (var i = 0; i < offset && i < text.length(); i++)
		{
			count++;
			if (text.charAt(i) == '\n')
			{
				count = 0;
			}
		}
		return count;
	}
}
