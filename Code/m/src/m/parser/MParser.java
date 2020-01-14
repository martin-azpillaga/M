package m.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.MismatchedTokenException;
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
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import com.google.inject.Inject;

import m.MStandaloneSetup;

public class MParser extends SyntaxErrorMessageProvider
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
	List<Integer> tokens;
	int errorIndex;
	HashMap<String,Integer> typeOf;
	
	void process(INode node)
	{
		var grammarElement = node.getGrammarElement();
		if (grammarElement instanceof ParserRule)
		{
			processParserRule((ParserRule)grammarElement);
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
			if (common.getType() != 12)
			{
				tokens.add(common.getType());
			}
		}
		
		while (!(currentNode instanceof RootNode))
		{
			process(currentNode);
			currentNode = currentNode.getParent();
		}
		
		//printTree((RootNode) currentNode);
		
		message += "\ndone.";
		error = "Syntax error" + error;
		
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