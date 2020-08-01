package m.validation.rules;

import m.library.symbols.Symbol;
import m.library.types.*;

public class Typing
{
	Type type;
	TypingReason reason;
	Symbol symbol;
	
	public Typing(Type type, TypingReason reason, Symbol symbol) {
		this.type = type;
		this.symbol = symbol;
		this.reason = reason;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public TypingReason getReason() {
		return reason;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public enum TypingReason
	{
		LIBRARY_VARIABLE, LIBRARY_COMPONENT, LIBRARY_FUNCTION, LIBRARY_BLOCK
	}
}
