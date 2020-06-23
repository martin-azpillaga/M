package m.parser.antlr;

import m.parser.antlr.internal.InternalMParser;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.antlr.AbstractIndentationTokenSource;

public class MTokenSource extends AbstractIndentationTokenSource
{	
	static int indentation = 0;

	public MTokenSource(TokenSource delegate)
	{
		super(delegate);
	}

	@Override
	protected boolean shouldSplitTokenImpl(Token token)
	{
		if (token.getText().equals("{"))
		{
			indentation++;
		}
		else if (token.getText().equals("}"))
		{
			indentation--;
		}
		
		// True inside BlankFunction false inside Function
		return token.getType() == InternalMParser.RULE_BLANKS && indentation==0;
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
