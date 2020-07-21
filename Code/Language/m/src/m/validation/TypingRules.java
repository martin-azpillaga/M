package m.validation;

import m.library.Symbol;

enum BindingReason
{
	SAME_COMPONENT,
	SAME_VARIABLE,
	PARAMETER_ARGUMENT,
	RESULT_RETURN,
	POLYMORPHISM
}

class TypingReason
{
	Symbol symbol;
	int parameter;
	boolean returnType;
	
	public TypingReason(Symbol symbol, boolean returnType) {
		this.symbol = symbol;
		this.returnType = returnType;
	}
	
	public TypingReason(Symbol symbol, int parameter) {
		this.symbol = symbol;
		this.parameter = parameter;
	}
	
	@Override
	public String toString()
	{
		if (returnType)
		{
			return "return value of " + symbol;
		}
		else
		{
			return "parameter " + parameter + " of " + symbol;
		}
	}
}