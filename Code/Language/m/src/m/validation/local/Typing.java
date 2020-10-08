package m.validation.local;

import m.library.rules.TypingReason;
import m.library.symbols.Symbol;
import m.library.types.Type;

public class Typing
{
	public final Type type;
	public final TypingReason reason;
	public final Symbol symbol;

	public Typing(Type type, TypingReason reason, Symbol symbol)
	{
		this.type = type;
		this.symbol = symbol;
		this.reason = reason;
	}
}
