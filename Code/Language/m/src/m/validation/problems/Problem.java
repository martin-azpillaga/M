package m.validation.problems;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import m.library.Library;

public abstract class Problem
{
	public abstract List<Diagnostic> diagnostics(Library library, String text);

	protected Range getRange(EObject o, String text)
	{
		var node = NodeModelUtils.getNode(o);
		return new Range
		(
			new Position(node.getStartLine()-1, character(text, node.getOffset())),
			new Position(node.getEndLine()-1  , character(text, node.getOffset()))
		);
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
	
	
	
	private int offset(String text, int line, int character)
	{
		var count = 0;
		for (var i = 0; i < text.length(); i++)
		{
			if (count == line)
			{
				return i+character;
			}
			if (text.charAt(i) == '\n')
			{
				count++;
			}
		}
		return text.length();
	}
	/*
	var file = fileOf(message.source, workspace);
				
				var node = NodeModelUtils.getNode(message.source);
				if (node == null)
				{
					continue;
				}
				var range = new Range(new Position(node.getStartLine()-1, character(text,node.getOffset())), new Position(node.getEndLine()-1, character(text,node.getEndOffset())));
				
				*/
}
