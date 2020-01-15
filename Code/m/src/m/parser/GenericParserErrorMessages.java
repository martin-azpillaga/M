package m.parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.runtime.CommonToken;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.impl.RootNode;
import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import com.google.inject.Inject;

class Path implements Cloneable
{
	public String error;
	public ArrayList<Integer> tokens;
	public int tokenIndex;
	public ArrayList<EObject> elements;
	public int elementIndex;
	
	public boolean match()
	{
		if (elementIndex >= elements.size())
		{
			if (tokenIndex < tokens.size())
			{
				error = "Unwanted extra tokens";
				return true;
			}
		}
		
		var element = elements.get(elementIndex);
		if (element instanceof ParserRule)
		{
			var rule = (ParserRule) element;
			
			var alternatives = rule.getAlternatives();
			var name = rule.getName();
			
			if (error == "")
			{
				error = name;
			}
			else
			{
				error = name;// + " to " + error;
			}

			elements.set(elementIndex, alternatives);
			
			return match();
		}
		else if (element instanceof TerminalRule)
		{
			var terminalRule = (TerminalRule) element;
			
			var name = terminalRule.getName();
			
			var expectedType = GenericParserErrorMessages.typeOf.get("RULE_"+name);
			
			if (tokenIndex < 0 || tokenIndex >= tokens.size())
			{
				report(elementIndex);
				error = "Expected " + name + " to " + error;
				return true;
			}
			
			var realType = tokens.get(tokenIndex);
			
			if (realType == expectedType)
			{
				elementIndex++;
				tokenIndex++;
				return match();
			}
			else
			{
				report(elementIndex);
				error = "Expected " + name + " to " + error;
				return true;
			}
		}
		else if (element instanceof Keyword)
		{
			var keyword = (Keyword) element;
			
			var value = keyword.getValue();
			
			var expectedType = GenericParserErrorMessages.typeOf.get("'"+value+"'");
			
			if (tokenIndex < 0 || tokenIndex >= tokens.size())
			{
				report(elementIndex+1);
				error = "Expected " + value + " to " + error;
				return true;
			}
			
			var realType = tokens.get(tokenIndex);
			
			if (realType == expectedType)
			{
				elementIndex++;
				tokenIndex++;
				return match();
			}
			else
			{
				report(elementIndex+1);
				error = "Expected " + value + " to " + error;
				return true;
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
				return match();
			}
			else if (cardinality.equals("*") || cardinality.equals("?"))
			{
				Path path = copy();
				path.elements.set(elementIndex, rule);
				GenericParserErrorMessages.paths.add(path);
				path.match();
				
				elements.remove(elementIndex);
				return match();
			}
			
			return false;
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
				return match();
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
				return match();
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
				return match();
			}
		}
		else if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			
			var feature = assignment.getFeature();
			var operator = assignment.getOperator();
			var terminal = assignment.getTerminal();
			
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
			
			elements.set(elementIndex, terminal);
			return match();
		}
		return false;
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
		n.tokens = new ArrayList<Integer>();
		for (var t : tokens)
		{
			n.tokens.add(t);
		}
		n.tokenIndex = tokenIndex;
		n.error = error;
		return n;
	}
	
	void report(int index)
	{
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
				
			}
		}
		else if (element instanceof Group)
		{
			var group = (Group) element;
			
			var cardinality = group.getCardinality();
			var first = group.getElements().get(0);
			elements.set(index+1, first);
			report(index+1);
			if (cardinality.equals("?") || cardinality.equals("*"))
			{
				error = "optionally " + error;
			}
		}
	}
}

public class GenericParserErrorMessages extends SyntaxErrorMessageProvider
{
	
	String message;
	String error;
	boolean findFirst = true;
	ArrayList<String> processed = new ArrayList<String>();
	String alternativer = "";
	ArrayList<Integer> tokens;
	int errorIndex;
	public static HashMap<String,Integer> typeOf;
	
	public static ArrayList<Path> paths;
	
	void process(INode node)
	{
		var grammarElement = node.getGrammarElement();
		var path = new Path();
		path.elements = new ArrayList<EObject>();
		path.elements.add(grammarElement);
		path.elementIndex = 0;
		path.error = "";
		path.tokens = tokens;
		path.tokenIndex = 0;
		paths.add(path);
		path.match();
	}

	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context)
	{
		var names = context.getTokenNames();
		paths = new ArrayList<Path>();
		typeOf = new HashMap<String,Integer>();
		for (var i = 0; i < names.length; i++)
		{
			typeOf.put(names[i], i);
		}
		processed = new ArrayList<String>();
		error = "";
		message = "";
		var currentNode = context.getCurrentNode();
		
		var errorToken = context.getRecognitionException().token;
		errorIndex = errorToken.getTokenIndex();
		var input = (XtextTokenStream) context.getRecognitionException().input;
		var inputtokens = input.getTokens();
		tokens = new ArrayList<Integer>();
		for (var x : inputtokens)
		{
			var common = (CommonToken) x;
			if (common.getType() != 7)
			{
				tokens.add(common.getType());
			}
		}

		process(currentNode);

		var maximumDepth = 0;
		for (var path : paths)
		{
			if (path.elementIndex > maximumDepth)
			{
				maximumDepth = path.elementIndex;
			}
		}
		for (var path : paths)
		{
			if (path.elementIndex == maximumDepth)
			{
				error += path.error + "\n";
			}
		}
		
		return new SyntaxErrorMessage(error, "syntax error");
	}
}