package m.validation;

public class FunctionType {
	
	AtomicType[] parameters;
	AtomicType returnType;
	
	public FunctionType(AtomicType[] parameters, AtomicType returnType)
	{
		this.parameters = parameters;
		this.returnType = returnType;
	}
	
	public AtomicType[] getParameters()
	{
		return parameters;
	}
	
	public AtomicType returnType()
	{
		return returnType;
	}
}
