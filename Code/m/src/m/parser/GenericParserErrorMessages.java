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
				error = name + " to " + error;
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
	public static String EXPECTING_OPEN_CURLY_BRACE = "EXPECTING_OPEN_CURLY_BRACE";
	public static String ENTITY_WRONG_NAME = "Wrong entity name";
	public static String COMPONENT_WRONG_NAME = "Wrong component name";
	@Inject IGrammarAccess grammarAccess;
	
	String message(String error)
	{
		if (error == ENTITY_WRONG_NAME)
		{
			return "The name of an entity must be an identifier";
		}
		else if (error == COMPONENT_WRONG_NAME)
		{
			return "The name of a component must be an identifier";
		}
		else
		{
			return "Unknown error";
		}
	}
	
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
		if (grammarElement instanceof ParserRule)
		{
			var path = new Path();
			path.elements = new ArrayList<EObject>();
			path.elements.add(grammarElement);
			path.elementIndex = 0;
			path.error = "";
			path.tokens = tokens;
			path.tokenIndex = errorIndex;
			paths.add(path);
			path.match();
			//processParserRule((ParserRule)grammarElement);
		}
		else
		{
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
	}
	
	void processParserRule(ParserRule rule)
	{
		var alternatives = rule.getAlternatives();
		if (alternatives instanceof Keyword)
		{
			process((Keyword) alternatives);
		}
		else if (alternatives instanceof Alternatives)
		{
			alternativer = rule.getName();
			message += "" + rule.getName() + ":\n";
			if (!processed.contains(rule.getName()))
			{
				processed.add(rule.getName());
				processMultipleAlternatives((Alternatives) alternatives);
			}
		}
		else if (alternatives instanceof Group)
		{
			if (error != "")
			{
				error = rule.getName() + " to " + error;
			}
			else
			{
				error = rule.getName() + " " + error;
			}
			message += "" + rule.getName() + ":\n";
			if (!processed.contains(rule.getName()))
			{
				processed.add(rule.getName());
				processGroup((Group) alternatives);
			}
		}
		else if (alternatives instanceof Assignment)
		{
			processAssignment((Assignment) alternatives);
		}
	}
	
	void processMultipleAlternatives(Alternatives alternatives)
	{
		for (var i = 0; i < alternatives.getElements().size(); i++)
		{
			message += "Alternative " + i + "\n";
			if (error.length() > 0 && error.charAt(0) != '\n')
			{
				error = alternativer + " to " + error;
			}
			else
			{
				error = alternativer + " " + error;
			}
			var element = alternatives.getElements().get(i);
			if (element instanceof Assignment)
			{
				processAssignment((Assignment) element);
			}
			else if (element instanceof RuleCall)
			{
				processRuleCall((RuleCall) element);
			}

			
		}
	}
	
	void process(Keyword keyword)
	{
		error = "\nExpecting " + keyword.getValue() + " to introduce " + error;
		message +=keyword.getValue();
	}
	
	void processGroup(Group group)
	{
		var elements = group.getElements();
		if (group.getCardinality() != null)
		{
			message += "(";
		}
		if (findFirst)
		{
			for (var i = 0; i < elements.size(); i++)
			{
				var element = elements.get(i);
				if (errorIndex + i >= tokens.size())
				{
					if (element instanceof Assignment)
					{
						processAssignment((Assignment) element);
					}
					else if (element instanceof Group)
					{
						processGroup((Group) element);
					}
					else if (element instanceof RuleCall)
					{
						processRuleCall((RuleCall) element);
					}
					return;
				}
				var token = tokens.get(errorIndex + i);
				if (!match(element, token))
				{
					if (element instanceof Assignment)
					{
						processAssignment((Assignment) element);
					}
					else if (element instanceof Group)
					{
						processGroup((Group) element);
					}
					else if (element instanceof RuleCall)
					{
						processRuleCall((RuleCall) element);
					}
					break;
				}
			}			
		}
		else
		{
			for (var element : elements)
			
			{
				if (element instanceof Assignment)
				{
					processAssignment((Assignment) element);
				}
				else if (element instanceof Group)
				{
					processGroup((Group) element);
				}
				else if (element instanceof RuleCall)
				{
					processRuleCall((RuleCall) element);
				}			
			}
		}
		if (group.getCardinality() != null)
		{
			message += ")" + group.getCardinality();
		}
		message += "\n";
	}
	
	boolean match(AbstractElement element, int type)
	{
		if (element instanceof Assignment)
		{
			return match((Assignment) element, type);
		}
		else if (element instanceof Group)
		{
			return match(((Group) element).getElements().get(0), type);
		}
		else if (element instanceof Alternatives)
		{
			return match((Alternatives) element, type);
		}
		else if (element instanceof ParserRule)
		{
			return match((ParserRule) element, type);
		}
		else if (element instanceof RuleCall)
		{
			return match((RuleCall) element, type);
		}
		
		return false;
	}
	
	boolean match(RuleCall rule, int type)
	{
		var inner = rule.getRule();
		if (inner instanceof ParserRule)
		{
			return match((ParserRule) inner, type);
		}
		else if (inner instanceof TerminalRule)
		{
			return match((TerminalRule) inner, type);
		}
		return false;
	}
	
	boolean match(ParserRule rule, int type)
	{
		return false;
	}
	
	boolean match(TerminalRule rule, int type)
	{
		var t = typeOf.get("RULE_"+rule.getName());
		return type == t.intValue();
	}
	
	boolean match(Assignment assignment, int type)
	{
		var terminal = assignment.getTerminal();
		if (terminal instanceof RuleCall)
		{
			return match((RuleCall) terminal, type);
		}
		else if (terminal instanceof Alternatives)
		{
			return match((Alternatives) terminal, type);
		}
		return false;
	}
	
	boolean match(Group group, int type)
	{
		return false;
	}
	
	boolean match(Alternatives alternatives, int type)
	{
		return false;
	}
	
	
	void processAssignment(Assignment assignment)
	{
		var feature = assignment.getFeature();
		var operator = assignment.getOperator();
		var terminal = assignment.getTerminal();
		var cardinality = assignment.getCardinality();
		
		if (operator.equals("="))
		{
			error = "set the " + feature + " of a " + error;
		}
		else if (operator.equals("+="))
		{
			error = "add to the list of " + feature + " of " + error;
		}
		else
		{
			System.out.println(operator);
		}
		
		message += feature + " " + operator + " ";
		if (terminal instanceof RuleCall)
		{
			processRuleCall((RuleCall) terminal);
		}
		else if (terminal instanceof Alternatives)
		{
			processMultipleAlternatives((Alternatives) terminal);
		}
		
		if (cardinality != null)
		{
			message += cardinality;
		}
	}
	
	void processRuleCall(RuleCall rule)
	{
		var inner = rule.getRule();
		if (inner instanceof ParserRule)
		{
			processParserRule((ParserRule) inner);
		}
		else if (inner instanceof TerminalRule)
		{
			processTerminalRule((TerminalRule) inner);
		}
	}
	
	void processTerminalRule(TerminalRule rule)
	{
		error = "\nExpecting " + rule.getName() + " to " + error;
		message += rule.getName();
	}
	
	void printTree(RootNode root)
	{
		var iterable = root.getAsTreeIterable();
		for (var node : iterable)
		{
			var grammar = node.getGrammarElement();
			if (grammar instanceof ParserRule)
			{
				System.out.println(((ParserRule) grammar).getName());
			}
			else if (grammar instanceof RuleCall)
			{
				var rule = ((RuleCall) grammar).getRule();
				System.out.println(rule.getName());
			}
		}
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
		
		//while (!(currentNode instanceof RootNode))
		//{
			process(currentNode);
		//	currentNode = currentNode.getParent();
		//}
		
		//printTree((RootNode) currentNode);

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
		
		return new SyntaxErrorMessage(error, ENTITY_WRONG_NAME);
		
		/*
		var errorToken = context.getRecognitionException().token;
		var expectedTypeInError = ((MismatchedTokenException)context.getRecognitionException()).expecting;
		var errorIndex = errorToken.getTokenIndex();
		var input = (XtextTokenStream) context.getRecognitionException().input;
		var tokens = input.getTokens();
		var names = context.getTokenNames();
		for (var token : tokens)
		{
			var xtexttoken = (CommonToken) token;
			
			if (errorToken == token)
			{
				System.out.print("Error is here: ");
			}
			System.out.println(xtexttoken.getType() + " " + xtexttoken.getText() + names[xtexttoken.getType()]);
		}
		var grammarElement = context.getCurrentNode().getGrammarElement();
		if (grammarElement instanceof ParserRule)
		{
			var rule = (ParserRule) grammarElement;
			var alternatives = (Alternatives) rule.getAlternatives();
			var cardinality = alternatives.getCardinality();
			var elements = alternatives.getElements();
			var message = "";
			for (var element : elements)
			{
				if (element instanceof Assignment)
				{
					var assignment = (Assignment) element;
					message += assignment.getFeature() + " " + assignment.getOperator() + " ";
					var terminal = assignment.getTerminal();
					if (terminal instanceof RuleCall)
					{
						var ruleCall = (RuleCall) terminal;
						var terminalRule = (ParserRule) ruleCall.getRule();
						message += terminalRule.getName() + "\n";
						var terminalAlternatives = terminalRule.getAlternatives();
						if (terminalAlternatives instanceof Group)
						{
							var terminalGroup = (Group) terminalAlternatives;
							var terminalElements = terminalGroup.getElements();
							
							var t = terminalElements.get(0);
							if (t instanceof Assignment)
							{
								var a = (Assignment) t; 
								message += a.getFeature() + a.getOperator();
								var tt = a.getTerminal();
								if (tt instanceof RuleCall)
								{
									var r = (RuleCall) tt;
									var finalRule = r.getRule();
									if (finalRule instanceof TerminalRule)
									{
										message += finalRule.getName();
									}
								}
							}
						}
					}
				}
				else
				{
					message += "\n not an assignment";
				}
			}
			return new SyntaxErrorMessage(message, ENTITY_WRONG_NAME);
		}
		else if (grammarElement instanceof RuleCall)
		{
			var rule = ((RuleCall) grammarElement).getRule();
			var terminalAlternatives = rule.getAlternatives();
			var message = "";
			if (terminalAlternatives instanceof Group)
			{
				var terminalGroup = (Group) terminalAlternatives;
				var terminalElements = terminalGroup.getElements();
				for (var i = 0; i < tokens.size() - errorIndex; i++)
				{
					var expected = terminalElements.get(i);
					if (expected instanceof Keyword)
					{
						var expectedKeyword = (Keyword) expected;
					}
					else if (expected instanceof Assignment)
					{
						var expectedAssignment = (Assignment) expected;
						var container = expectedAssignment.eContainer();
						while (!(container instanceof ParserRule) && container != null)
						{
							container = container.eContainer();
						}
						var containerRule = (ParserRule) container;
						message += "Expected " + names[expectedTypeInError].replace("RULE_", "") + " for " + expectedAssignment.getFeature() + " of " + containerRule.getName();
					}
				}
				var t = terminalElements.get(0);
				if (t instanceof Assignment)
				{
					var a = (Assignment) t; 
					message += a.getFeature() + a.getOperator();
					var tt = a.getTerminal();
					if (tt instanceof RuleCall)
					{
						var r = (RuleCall) tt;
						var finalRule = r.getRule();
						if (finalRule instanceof TerminalRule)
						{
							message += finalRule.getName();
						}
					}
				}
			}
			return new SyntaxErrorMessage(message, ENTITY_WRONG_NAME);
		}
		else
		{
			return super.getSyntaxErrorMessage(context);
		}*/
	}
	
	String identifierError(String text)
	{
		var error = "";
			
		if (GrammarUtil.getAllKeywords(grammarAccess.getGrammar()).contains(text))
		{
			error = "Cannot be a reserved keyword: " + text;
		}
		else if (Character.isDigit(text.charAt(0)))
		{
			error = "Cannot start with a digit: " + text.charAt(0);
		}
		else
		{
			var specialCharacters = new ArrayList<String>();
			for (var character : text.toCharArray())
			{
				if (!Character.isJavaIdentifierPart(character))
				{
					specialCharacters.add(String.valueOf(character));
				}
			}
			if (specialCharacters.size() > 0)
			{
				error = "Cannot contain special characters: " + String.join(", ", specialCharacters);
			}
		}
		return error;
	}
}