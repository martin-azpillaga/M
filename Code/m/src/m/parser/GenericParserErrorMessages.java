package m.parser;

import java.util.ArrayList;
import java.util.HashMap;

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


public class GenericParserErrorMessages extends SyntaxErrorMessageProvider
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
		System.out.println(errorOffset);
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
			if (path.elementIndex > maximumDepth)
			{
				maximumDepth = path.elementIndex;
			}
		}
		var errorAt = tokens.get(maximumDepth);
		var error = "Error at " + errorAt.getText() + ". You can try to:";
		for (var path : paths)
		{
			if (path.elementIndex == maximumDepth)
			{
				error += "\n" + path.report();
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
		path.elementIndex = 0;
		path.tokens = tokens;
		path.tokenIndex = 0;
		paths.add(path);
		path.match();
	}
}

class Path
{
	public ArrayList<CommonToken> tokens;
	public int tokenIndex;
	public ArrayList<EObject> elements;
	public int elementIndex;
	public EObject last;
	
	public void match()
	{
		if (elementIndex >= elements.size())
		{
			if (tokenIndex < tokens.size())
			{
				GenericParserErrorMessages.paths.remove(this);
			}
			return;
		}
		
		var element = elements.get(elementIndex);
		if (element instanceof ParserRule)
		{
			var rule = (ParserRule) element;
			
			var alternatives = rule.getAlternatives();

			elements.set(elementIndex, alternatives);
			
			match();
		}
		else if (element instanceof TerminalRule)
		{
			var terminalRule = (TerminalRule) element;
			
			var name = terminalRule.getName();
			
			var expectedType = GenericParserErrorMessages.typeOf.get("RULE_"+name);
			
			if (tokenIndex < 0 || tokenIndex >= tokens.size())
			{
				return;
			}
			
			var realType = tokens.get(tokenIndex).getType();
			
			if (realType == expectedType)
			{
				elementIndex++;
				tokenIndex++;
				match();
			}
			else
			{
				return;
			}
		}
		else if (element instanceof Keyword)
		{
			var keyword = (Keyword) element;
			
			var value = keyword.getValue();
			
			var expectedType = GenericParserErrorMessages.typeOf.get("'"+value+"'");
			
			if (tokenIndex < 0 || tokenIndex >= tokens.size())
			{
				
				return;
			}
			
			var realType = tokens.get(tokenIndex).getType();
			
			if (realType == expectedType)
			{
				elementIndex++;
				tokenIndex++;
				match();
			}
			else
			{
				return;
			}
		}
		else if (element instanceof RuleCall)
		{
			var ruleCall = (RuleCall) element;
			
			var cardinality = ruleCall.getCardinality();
			var rule = ruleCall.getRule();
			
			if (cardinality == null || cardinality.equals("+"))
			{
				elements.set(elementIndex, rule);
				match();
			}
			else if (cardinality.equals("*") || cardinality.equals("?"))
			{
				Path path = copy();
				path.elements.set(elementIndex, rule);
				GenericParserErrorMessages.paths.add(path);
				path.match();
				
				elements.remove(elementIndex);
				match();
			}
			
			return;
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
					path.elements.set(elementIndex, e);
					GenericParserErrorMessages.paths.add(path);
					path.match();
				}
				GenericParserErrorMessages.paths.remove(this);
			}
			else if (cardinality.equals("*") || cardinality.equals("?"))
			{
				for (var e : elements)
				{
					Path path = copy();
					path.elements.set(elementIndex, e);
					GenericParserErrorMessages.paths.add(path);
					path.match();
				}
				this.elements.remove(elementIndex);
				match();
			}
		}
		else if (element instanceof Group)
		{
			var group = (Group) element;
			
			var cardinality = group.getCardinality();
			var elements = group.getElements();
			
			if (cardinality == null || cardinality.equals("+"))
			{
				this.elements.remove(elementIndex);
				for (var i = elements.size()-1; i >= 0; i--)
				{
					var e = elements.get(i);
					this.elements.add(elementIndex, e);
				}
				match();
			}
			else if (cardinality.equals("?") || cardinality.equals("*"))
			{
				Path path = copy();
				path.elements.remove(elementIndex);
				for (var i = elements.size()-1; i >= 0; i--)
				{
					var e = elements.get(i);
					path.elements.add(elementIndex, e);
				}
				GenericParserErrorMessages.paths.add(path);
				path.match();
				
				this.elements.remove(elementIndex);
				match();
			}
		}
		else if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			
			var cardinality = assignment.getCardinality();
			var terminal = assignment.getTerminal();
			
			if (cardinality == null || cardinality.equals("+"))
			{
				last = element;
				elements.set(elementIndex, terminal);
				match();
			}
			else if (cardinality.equals("*") || cardinality.equals("?"))
			{
				Path path = copy();
				path.last = element;
				path.elements.set(elementIndex, terminal);
				GenericParserErrorMessages.paths.add(path);
				path.match();
				
				elements.remove(elementIndex);
				match();
			}
			
		}
		return;
	}
	
	Path copy()
	{
		var n = new Path();
		n.elements = new ArrayList<>();
		n.elementIndex = elementIndex;
		for (var e : elements)
		{
			n.elements.add(e);
		}
		n.tokens = new ArrayList<CommonToken>();
		for (var t : tokens)
		{
			n.tokens.add(t);
		}
		n.tokenIndex = tokenIndex;
		return n;
	}
	
	String report()
	{
		var error = "";
		var element = elements.get(elementIndex);
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
			elements.set(elementIndex+1, first);
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
			if (elementIndex < elements.size()-1)
			{
				error = "Write " + value + " to " + report(elements.get(elementIndex+1),false);
			}
			else
			{
				error = "Write " + value + " to finish the list."; 
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

