package m.validation;

public class TypingRules {

}

enum BindingReason
{
	assignment, sameComponent, sameVariable, parameterArgument, resultReturn, vectorArithmetic
}

class TypingReason
{
	String symbol;
	int parameter;
	boolean returnType;
	
	public TypingReason(String symbol, boolean returnType) {
		this.symbol = symbol;
		this.returnType = returnType;
	}
	
	public TypingReason(String symbol, int parameter) {
		this.symbol = symbol;
		this.parameter = parameter;
	}
}