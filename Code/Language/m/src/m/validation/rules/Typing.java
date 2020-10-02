package m.validation.rules;

import m.library.symbols.Symbol;
import m.library.types.*;

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
	
	public enum TypingReason
	{
		LIBRARY_VARIABLE, LIBRARY_COMPONENT, LIBRARY_FUNCTION, LIBRARY_BLOCK
	}
}
