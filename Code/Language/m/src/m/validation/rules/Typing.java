package m.validation.rules;

import m.library.Symbol;
import m.library.types.*;

public class Typing
{
	Type type;
	Symbol symbol;
	int parameter;
	
	public Typing(Type type, Symbol symbol, int parameter) {
		this.symbol = symbol;
		this.parameter = parameter;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public int getParameter() {
		return parameter;
	}
	
	public Type getType()
	{
		return type;
	}
}
