package m.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.impl.RootNode;
import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;


public class ContextualParserMessages extends SyntaxErrorMessageProvider
{
	public static HashMap<String,Integer> typeOf;
	public static ArrayList<Path> paths;
	
	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context)
	{
		paths = new ArrayList<Path>();
		typeOf = new HashMap<String,Integer>();
		
		var names = context.getTokenNames();
		var currentNode = context.getCurrentNode();
		var input = (XtextTokenStream) context.getRecognitionException().input;
		var errorOffset = currentNode.getTotalOffset();
		for (var i = 0; i < names.length; i++)
		{
			typeOf.put(names[i], i);
		}

		var inputtokens = input.getTokens();
		var tokens = new ArrayList<CommonToken>();
		for (var i = errorOffset; i < inputtokens.size(); i++)
		{
			var common = (CommonToken) inputtokens.get(i);
			if (common.getChannel() != Token.HIDDEN_CHANNEL)
			{
				tokens.add(common);
			}
		}
		var endOfFile = new CommonToken(-1);
		endOfFile.setText("END OF FILE");
		tokens.add(endOfFile);
		

		process(currentNode, tokens);

		var maximumDepth = 0;
		for (var path : paths)
		{
			if (path.index > maximumDepth && path.valid)
			{
				maximumDepth = path.index;
			}
		}
		var errorAt = tokens.get(maximumDepth);
		var error = "Error at " + errorAt.getText();
		for (var path : paths)
		{
			if (path.index == maximumDepth && path.valid)
			{
				var report = path.report();
				if (!error.contains(report))
				{
					error += "\n"+report;
				}
			}
		}
		
		return new SyntaxErrorMessage(error, "syntax error");
	}
	
	void process(INode node, ArrayList<CommonToken> tokens)
	{
		var grammarElement = node.getGrammarElement();
		var path = new Path();
		path.elements = new ArrayList<EObject>();
		path.elements.add(grammarElement);
		path.index = 0;
		path.tokens = tokens;
		paths.add(path);
		path.last = null;
		path.ancestors = new ArrayList<Path>();
		path.match();
	}
}

class Path
{
	public ArrayList<CommonToken> tokens;
	public ArrayList<EObject> elements;
	public int index;
	public EObject last;
	public ArrayList<Path> ancestors;
	public boolean valid = true;
	
	public void match()
	{
		if (index >= elements.size())
		{
			if (index < tokens.size())
			{
				valid = false;
			}
			return;
		}
		
		var element = elements.get(index);
		if (element instanceof ParserRule)
		{
			var rule = (ParserRule) element;
			
			var alternatives = rule.getAlternatives();

			elements.set(index, alternatives);
			
			match();
		}
		else if (element instanceof TerminalRule)
		{
			var terminalRule = (TerminalRule) element;
			
			var name = terminalRule.getName();
			
			var expectedType = ContextualParserMessages.typeOf.get("RULE_"+name);
			
			var realType = tokens.get(index).getType();
			
			if (realType == expectedType)
			{
				index++;
				match();
			}
		}
		else if (element instanceof Keyword)
		{
			var keyword = (Keyword) element;
			
			var value = keyword.getValue();
			
			var expectedType = ContextualParserMessages.typeOf.get("'"+value+"'");
			
			var realType = tokens.get(index).getType();
			
			if (realType == expectedType)
			{
				index++;
				match();
			}
		}
		else if (element instanceof RuleCall)
		{
			var ruleCall = (RuleCall) element;
			
			var rule = ruleCall.getRule();
			
			elements.set(index, rule);
			
			match();
		}
		else if (element instanceof Alternatives)
		{
			var alternatives = (Alternatives) element;
			
			var cardinality = alternatives.getCardinality();
			var elements = alternatives.getElements();
			
			
			if (cardinality == null || cardinality.equals("+"))
			{
				for (var e : elements)
				{
					Path path = copy();
					path.elements.set(index, e);
					ContextualParserMessages.paths.add(path);
					path.match();
				}
				ContextualParserMessages.paths.remove(this);
			}
			else if (cardinality.equals("*") || cardinality.equals("?"))
			{
				this.elements.remove(index);
				var originalPath = this.copy();
				var iteration = 0;
				match();
				var repeat = true;
				while (repeat)
				{
					iteration++;
					for (var i = 0; i < iteration; i++)
					{
						for (var e : elements)
						{
							var path = originalPath.copy();
							
							path.elements.add(index, e);
							
							ContextualParserMessages.paths.add(path);
							path.ancestors.add(this);
							path.match();
						}
					}
					repeat = false;
					for (var p : ContextualParserMessages.paths)
					{
						if (p.ancestors.contains(this) && p.index > this.index)
						{
							repeat = true;
							break;
						}
					}
				}
			}
		}
		else if (element instanceof Group)
		{
			var group = (Group) element;
			
			var cardinality = group.getCardinality();
			var elements = group.getElements();
			
			var replacement = new ArrayList<EObject>();
			for (var e : elements)
			{
				replacement.add(e);
			}
			resolve(replacement,cardinality,null);
		}
		else if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			
			var cardinality = assignment.getCardinality();
			var terminal = assignment.getTerminal();
			
			var replacement = new ArrayList<EObject>();
			replacement.add(terminal);
			
			resolve(replacement, cardinality, element);
		}
	}
	
	Path copy()
	{
		var n = new Path();
		n.elements = new ArrayList<>();
		n.index = index;
		for (var e : elements)
		{
			n.elements.add(e);
		}
		n.tokens = new ArrayList<CommonToken>();
		for (var t : tokens)
		{
			n.tokens.add(t);
		}
		n.last = last;
		n.ancestors = new ArrayList<Path>();
		for (var t : ancestors)
		{
			n.ancestors.add(t);
		}
		return n;
	}
	
	void resolve(List<EObject> replacement, String cardinality, EObject last)
	{
		if (cardinality == null)
		{
			elements.remove(index);
			this.last = last;
			for (var i = replacement.size()-1; i >= 0; i--)
			{
				elements.add(index, replacement.get(i));
			}
			match();
		}
		else if (cardinality.equals("+"))
		{
			elements.remove(index);
			var originalpath = copy();
			var originalIndex = index;
			this.last = last;
			for (var i = replacement.size()-1; i >= 0; i--)
			{
				elements.add(originalIndex, replacement.get(i));
			}
			var maxIndex = this.index;
			match();
			
			var repeat = false;
			for (var p : ContextualParserMessages.paths)
			{
				if ((p == this || p.ancestors.contains(this)) && p.index > maxIndex)
				{
					maxIndex = p.index;
					repeat = true;
				}
			}
			var iteration = 1;
			
			while (repeat)
			{
				iteration++;
				var path = originalpath.copy();
				path.last = last;
				for (var i = 0; i < iteration; i++)
				{
					for (var j = replacement.size()-1; j >= 0; j--)
					{
						path.elements.add(originalIndex, replacement.get(j));
					}
				}
				ContextualParserMessages.paths.add(path);
				path.ancestors.add(this);
				path.match();

				repeat = false;
				for (var p : ContextualParserMessages.paths)
				{
					if (p.ancestors.contains(this) && p.index > maxIndex)
					{
						maxIndex = p.index;
						repeat = true;
					}
				}
			}
		}
		else if (cardinality.equals("?"))
		{
			elements.remove(index);
			var originalPath = copy();
			var originalIndex = index;
			match();
			
			var path = originalPath.copy();
			path.last = last;

			for (var j = replacement.size()-1; j >= 0; j--)
			{
				path.elements.add(originalIndex, replacement.get(j));
			}
			ContextualParserMessages.paths.add(path);
			path.ancestors.add(this);
			path.match();
		}
		else if (cardinality.equals("*"))
		{
			elements.remove(index);
			var originalpath = copy();
			var originalIndex = index;
			match();
			var maxIndex = this.index;
			var repeat = true;
			for (var p : ContextualParserMessages.paths)
			{
				if ((p==this||p.ancestors.contains(this)) && p.index > maxIndex)
				{
					maxIndex = p.index;
					repeat = true;
				}
			}
			var iteration = 0;
			
			while (repeat)
			{
				iteration++;
				var path = originalpath.copy();
				path.last = last;
				for (var i = 0; i < iteration; i++)
				{
					for (var j = replacement.size()-1; j >= 0; j--)
					{
						path.elements.add(originalIndex, replacement.get(j));
					}
				}
				ContextualParserMessages.paths.add(path);
				path.ancestors.add(this);
				path.match();

				repeat = false;
				for (var p : ContextualParserMessages.paths)
				{
					if (p.ancestors.contains(this) && p.index > maxIndex)
					{
						maxIndex = p.index;
						repeat = true;
					}
				}
			}			
		}
	}
	
	String report()
	{
		var error = "";
		var element = elements.get(index);
		if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			
			var feature = assignment.getFeature();
			var operator = assignment.getOperator();
			var container = assignment.eContainer();
			var containerName = "";
			
			while (!(container instanceof ParserRule || container instanceof RootNode))
			{
				container = container.eContainer();
			}
			
			if (container instanceof ParserRule)
			{
				containerName = ((ParserRule) container).getName();
			}
			
			if (operator.equals("="))
			{
				error = "introduce the " + feature + " of " + containerName;
			}
			else if (operator.equals("+="))
			{
				error = "introduce the list of " + feature + " of " + containerName;
			}
			else if (operator == "?=")
			{
				error = "activate the " + feature + " of " + containerName;
			}
			
			if (operator.equals("="))
			{
				error = "set the " + feature + " of " + error;
			}
			else if (operator.equals("+="))
			{
				error = "add to the list of " + feature + " of " + error;
			}
			else if (operator.equals("?="))
			{
				error = "activate the " + feature + " of " + error;
			}
		}
		else if (element instanceof Group)
		{
			var group = (Group) element;
			
			var cardinality = group.getCardinality();
			var first = group.getElements().get(0);
			elements.set(index+1, first);
			//report(elementIndex+1);
			if (cardinality.equals("?") || cardinality.equals("*"))
			{
				error = "optionally " + error;
			}
		}
		else if (element instanceof TerminalRule)
		{
			var terminal = (TerminalRule) element;
			var value = terminal.getName();
			error = "Write a " + value + " to " + report((Assignment) last, true);
		}
		else if (element instanceof Keyword)
		{
			var keyword = (Keyword) element;
			var value = keyword.getValue();
			if (index < elements.size()-1)
			{
				error = "Write " + value + " to " + report(elements.get(index+1),false);
			}
			else
			{
				error = "Write " + value + " to finish"; 
			}
		}
		return error;
	}
	
	String report(EObject element, boolean includeAction)
	{
		if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			var error = "";
			var feature = assignment.getFeature();
			var operator = assignment.getOperator();
			var container = assignment.eContainer();
			var containerName = "";
			
			while (!(container instanceof ParserRule || container instanceof RootNode))
			{
				container = container.eContainer();
			}
			
			if (container instanceof ParserRule)
			{
				containerName = "the " + ((ParserRule) container).getName();
			}
			
			if (operator.equals("="))
			{
				if (includeAction)
				{
					error = "set the " + feature + " of " + containerName;
				}
				else
				{
					error = "introduce the " + feature + " of " + containerName;
				}
			}
			else if (operator.equals("+="))
			{
				if (includeAction)
				{
					error = "add to the list of " + feature + " of " + containerName;
				}
				else
				{
					error = "introduce the list of " + feature + " of " + containerName;
				}
			}
			else if (operator == "?=")
			{
				if (includeAction)
				{
					error = "activate the " + feature + " of " + containerName;
				}
				else
				{
					error = "introduce the " + feature + " of " + containerName;
				}
			}
			return error;
		}
		return "Unrecognized error";
	}
}

