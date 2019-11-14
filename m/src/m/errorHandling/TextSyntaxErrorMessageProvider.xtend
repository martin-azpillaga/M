package m.errorHandling

import org.eclipse.xtext.parser.antlr.SyntaxErrorMessageProvider
import org.eclipse.xtext.IGrammarAccess
import com.google.inject.Inject
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage
import org.eclipse.xtext.nodemodel.impl.RootNode
import m.m.Entity

class TextSyntaxErrorMessageProvider extends SyntaxErrorMessageProvider
{
	public static val String USED_RESERVED_KEYWORD = "USED_RESERVED_KEYWORD"
	public static val String EXPECTING_OPEN_CURLY_BRACE = "EXPECTING_OPEN_CURLY_BRACE"
	@Inject IGrammarAccess grammarAccess

	override getSyntaxErrorMessage(IParserErrorContext context)
	{
		val unexpectedText = context?.recognitionException?.token?.text
		if (context.currentNode.parent instanceof RootNode)
		{
			if (GrammarUtil::getAllKeywords(grammarAccess.getGrammar()).contains(unexpectedText))
			{
				return new SyntaxErrorMessage('''«unexpectedText» is a keyword, please rename it.''', USED_RESERVED_KEYWORD)
			}
			else
			{
				return new SyntaxErrorMessage('''
				«unexpectedText» is an entity or system that hasn't been initialized. You can either 
				1. Write : after «unexpectedText» to specify a base entity, 
				2. Write { after «unexpectedText» to start the component list,
				3. Write [ after «unexpectedText» to list subentities,
				4. Write { after «unexpectedText» to describe the commands.
				''', EXPECTING_OPEN_CURLY_BRACE)
			}
		}
		else if (context.currentNode.semanticElement instanceof Entity)
		{
			var fullname = context.currentNode.text
			
			return new SyntaxErrorMessage('''
			«fullname» is an entity that hasn't been initialized.
			1. Write : after «fullname» to specify a base entity, 
			2. Write { after «fullname» to start the component list,
			3. Write [ after «fullname» to list subentities,
			''', EXPECTING_OPEN_CURLY_BRACE)
		}
		super.getSyntaxErrorMessage(context)
	}
}
