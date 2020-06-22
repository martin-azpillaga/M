package m.parser.antlr;

import m.parser.antlr.internal.InternalMParser;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.AbstractIndentationTokenSource;

public class MTokenSource extends AbstractIndentationTokenSource
{
	public MTokenSource(TokenSource delegate)
	{
		super(delegate);
	}

	@Override
	protected boolean shouldSplitTokenImpl(Token token)
	{
		return token.getType() == InternalMParser.RULE_BLANK;
	}

	@Override
	protected int getBeginTokenType()
	{
		return InternalMParser.RULE_BEGIN;
	}

	@Override
	protected int getEndTokenType()
	{
		return InternalMParser.RULE_END;
	}

}
