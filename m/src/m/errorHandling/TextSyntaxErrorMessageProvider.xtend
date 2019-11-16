package m.errorHandling

import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider
import org.eclipse.xtext.IGrammarAccess
import com.google.inject.Inject
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage
import org.eclipse.xtext.nodemodel.impl.RootNode
import m.m.Entity
import java.util.ArrayList
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.RuleCall

class TextSyntaxErrorMessageProvider extends SyntaxErrorMessageProvider
{
	public static val String EXPECTING_OPEN_CURLY_BRACE = "EXPECTING_OPEN_CURLY_BRACE"
	public static val String ENTITY_WRONG_NAME = "Wrong entity name"
	public static val String COMPONENT_WRONG_NAME = "Wrong component name"
	@Inject IGrammarAccess grammarAccess
	
	def message(String error)
	{
		switch error
		{
			case ENTITY_WRONG_NAME: 'The name of an entity must be an identifier'
			case COMPONENT_WRONG_NAME: 'The name of a component must be an identifier'
		}
	}

	override getSyntaxErrorMessage(IParserErrorContext context)
	{
		var grammarElement = context.currentNode.grammarElement
		if (grammarElement instanceof ParserRule)
		{
			var name = grammarElement.name
			if (name == 'Game')
			{
				
			}
			var childs = context.currentNode
			return new SyntaxErrorMessage('parser', ENTITY_WRONG_NAME)
		}
		else if (grammarElement instanceof RuleCall)
		{
			var name = grammarElement.rule.name
			return new SyntaxErrorMessage('rule', ENTITY_WRONG_NAME)
		}
		else
		{
			return super.getSyntaxErrorMessage(context)
		}
	}
	/*
		val unexpectedText = context?.recognitionException?.token?.text
		var element = context.currentNode.semanticElement
		if (context.currentNode.parent instanceof RootNode)
		{
			var error = unexpectedText.identifierError
			
			if (error != '')
			{
				return new SyntaxErrorMessage(
				'''
				«ENTITY_WRONG_NAME»
				«ENTITY_WRONG_NAME.message»
				«error»''', ENTITY_WRONG_NAME)
			}
			else
			{
				return new SyntaxErrorMessage(
				'''
				«unexpectedText» is an entity or system that hasn't been initialized. You can either 
				1. Write : after «unexpectedText» to specify a base entity, 
				2. Write { after «unexpectedText» to start the component list,
				3. Write [ after «unexpectedText» to list subentities,
				4. Write { after «unexpectedText» to describe the commands.
				''', EXPECTING_OPEN_CURLY_BRACE)
			}
		}
		else if ( element instanceof Entity)
		{
			if (element.name === null)
			{
				var fullname = context.currentNode.text
				
				return new SyntaxErrorMessage('''
				«fullname» is an entity that hasn't been initialized.
				1. Write : after «fullname» to specify a base entity, 
				2. Write { after «fullname» to start the component list,
				3. Write [ after «fullname» to list subentities,
				''', EXPECTING_OPEN_CURLY_BRACE)
			}
			else if (element.components === null)
			{
				println("component")
			}
			else
			{
				println("second component")
			}
		}
		return super.getSyntaxErrorMessage(context)
	}
	*/
	def identifierError(String text)
	{
		var error = ''
			
		if (GrammarUtil.getAllKeywords(grammarAccess.getGrammar()).contains(text))
		{
			error = 'Cannot be a reserved keyword: ' + text
		}
		else if (Character.isDigit(text.charAt(0)))
		{
			error = 'Cannot start with a digit: ' + text.charAt(0)
		}
		else
		{
			var specialCharacters = new ArrayList<String>
			for (character : text.toCharArray)
			{
				if (!Character.isJavaIdentifierPart(character))
				{
					specialCharacters.add(character.toString)
				}
			}
			if (specialCharacters.size > 0)
			{
				error = 'Cannot contain special characters: ' + specialCharacters.join(', ')
			}
		}
		return error
	}
}
