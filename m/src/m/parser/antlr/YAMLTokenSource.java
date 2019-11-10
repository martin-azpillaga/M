package m.parser.antlr;

import m.parser.antlr.internal.InternalYAMLParser;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.AbstractIndentationTokenSource;

public class YAMLTokenSource extends AbstractIndentationTokenSource {

	public YAMLTokenSource(TokenSource delegate) {
		super(delegate);
	}

	@Override
	protected boolean shouldSplitTokenImpl(Token token) 
	{
		return token.getType() == InternalYAMLParser.RULE_WS;
	}

	@Override
	protected int getBeginTokenType() 
	{
		return InternalYAMLParser.RULE_BEGIN;
	}

	@Override
	protected int getEndTokenType() 
	{
		return InternalYAMLParser.RULE_END;
	}

}
