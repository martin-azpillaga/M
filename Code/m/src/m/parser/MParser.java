package m.parser;

import java.io.StringReader;
import java.util.ArrayList;

import org.antlr.runtime.CommonToken;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
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

	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage(IParserErrorContext context)
	{
		var input = (XtextTokenStream) context.getRecognitionException().input;
		var tokens = input.getTokens();
		var names = context.getTokenNames();
		for (var token : tokens)
		{
			var xtexttoken = (CommonToken) token;
			System.out.println(xtexttoken.getType() + " " + xtexttoken.getText() + names[xtexttoken.getType()]);
		}
		var grammarElement = context.getCurrentNode().getGrammarElement();
		if (true) return super.getSyntaxErrorMessage(context);
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
					message += assignment.getFeature() + " ";
					var terminal = assignment.getTerminal();
					if (terminal instanceof RuleCall)
					{
						var ruleCall = (RuleCall) terminal;
						var terminalRule = (ParserRule) ruleCall.getRule();
						var terminalAlternatives = (Group) terminalRule.getAlternatives();
						var terminalElements = terminalAlternatives.getElements();
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
				else
				{
					message += "\n not an assignment";
				}
			}
			return new SyntaxErrorMessage(message, ENTITY_WRONG_NAME);
		}
		else if (grammarElement instanceof RuleCall)
		{
			var name = ((RuleCall) grammarElement).getRule().getName();
			return new SyntaxErrorMessage("rule", ENTITY_WRONG_NAME);
		}
		else
		{
			return super.getSyntaxErrorMessage(context);
		}
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