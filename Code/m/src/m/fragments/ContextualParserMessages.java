package m.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
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
		var totalOffset = 0;
		for (var t = 0; t < inputtokens.size(); t++)
		{
			var token = (CommonToken)inputtokens.get(t);
			
			if (totalOffset >= errorOffset)
			{
				if (token.getChannel() != Token.HIDDEN_CHANNEL)
				{
					tokens.add(token);
				}
			}
			else
			{
				totalOffset += 1+token.getStopIndex() - token.getStartIndex();
			}
		}
		var endOfFile = new CommonToken(-1);
		endOfFile.setText("END OF FILE");
		tokens.add(endOfFile);
		
		if (currentNode.getGrammarElement() instanceof Action)
		{
			currentNode = ((CompositeNode)currentNode).getFirstChild();
		}
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
				valid = true;
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
		else if (element instanceof EnumRule)
		{
			var rule = (EnumRule) element;
			
			var realText = tokens.get(index).getText();
			var alternatives = (Alternatives) rule.getAlternatives();
			var elements = alternatives.getElements();
			for (var e : elements)
			{
				var enumLiteral = (EnumLiteralDeclaration) e;
				var expectedText = enumLiteral.getLiteral().getValue();
				
				if (realText.equals(expectedText))
				{
					index++;
					match();
				}
			}
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
		else if (element instanceof Action)
		{
			elements.remove(index);
			match();
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
			this.elements.remove(index);
			ContextualParserMessages.paths.remove(this);
			if (cardinality == null)
			{
				combinations(elements, this, this, index, 1, 1);
			}
			else if (cardinality.equals("?"))
			{
				var copy = this.copy();
				ContextualParserMessages.paths.add(copy);
				copy.match();
				combinations(elements, this, this, index, 1, 1);
			}
			else if (cardinality.equals("+"))
			{
				var iterations = 0;
				
				var maxIndex = 0;
				var repeat = true;
				while (repeat)
				{
					iterations++;
					combinations(elements, this, this, index, 1, iterations);
					
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
			else if (cardinality.equals("*"))
			{
				var copy = this.copy();
				ContextualParserMessages.paths.add(copy);
				copy.ancestors.add(this);
				copy.match();
				
				var iterations = 0;

				var maxIndex = 0;
				
				for (var p : ContextualParserMessages.paths)
				{
					if ((p == copy || p.ancestors.contains(copy)) && p.index > maxIndex)
					{
						maxIndex = p.index;
					}
				}
				
				var repeat = true;
				while (repeat)
				{
					iterations++;
					combinations(elements, this, this, index, 1, iterations);
					
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
	
	void combinations(List<AbstractElement> elements, Path ancestor, Path path, int originalIndex, int iteration, int maxIteration)
	{
		for (var e : elements)
		{
			var copy = path.copy();
			copy.elements.add(originalIndex, e);
			if (iteration == maxIteration)
			{
				copy.ancestors.add(ancestor);
				ContextualParserMessages.paths.add(copy);
				copy.match();
			}
			else
			{
				combinations(elements, ancestor, copy, originalIndex, iteration+1, maxIteration);
			}
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
		// For alternatives, or even every case, prune out the bad paths
		// before creating a new iteration and only add elements to
		// the paths that are promising.
		// Receive List<List<EObject>>
		// Create an extra path for each element in the first list
		// Add an element to this path for every entry in its list.
		// Assignments call it with [[elem]]
		// Groups call it with [[a,b,c]]
		// Alternatives call it with [[a],[b],[c]]
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
				//path.last = last;
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
		if (index >= elements.size())
		{
			return "Delete this token";
		}
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
		else if (element instanceof EnumRule)
		{
			var rule = (EnumRule) element;
			var alternatives = (Alternatives) rule.getAlternatives();
			var elements = alternatives.getElements();
			
			error = "Write one of ";
			for (var e : elements)
			{
				var enumLiteral = (EnumLiteralDeclaration) e;
				error += enumLiteral.getLiteral().getValue() + " ";
			}
			error += "to specify the enum type";
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
		else if (element instanceof Group)
		{
			var group = (Group) element;
			
			var cardinality = group.getCardinality();
			var first = group.getElements().get(0);
			elements.set(index+1, first);
			//report(elementIndex+1);
			if (cardinality.equals("?") || cardinality.equals("*"))
			{
				return "optionally " + report(elements.get(index+1),false);
			}
		}
		else if (element instanceof Keyword)
		{
			return "finish";
		}
		return "Unrecognized error";
	}
}

