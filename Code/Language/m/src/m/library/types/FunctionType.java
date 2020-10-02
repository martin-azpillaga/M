package m.library.types;

public class FunctionType implements Type
{
	public final Type[] parameterTypes;
	public final Type returnType;
	
	public FunctionType(Type[] parameters, Type returnType)
	{
		this.parameterTypes = parameters;
		this.returnType = returnType;
	}
}
