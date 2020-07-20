package m.validation.types;

public class FunctionType implements Type {
	
	Type[] parameters;
	Type returnType;
	
	public FunctionType(Type[] parameters, Type returnType)
	{
		this.parameters = parameters;
		this.returnType = returnType;
	}
	
	public Type[] getParameters()
	{
		return parameters;
	}
	
	public Type getReturnType()
	{
		return returnType;
	}
}
