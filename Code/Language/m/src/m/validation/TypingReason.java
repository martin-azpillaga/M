package m.validation;

import m.library.Symbol;

public class TypingReason
{
	Symbol symbol;
	int parameter;
	
	public TypingReason(Symbol symbol, int parameter) {
		this.symbol = symbol;
		this.parameter = parameter;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public int getParameter() {
		return parameter;
	}
}
