package m.library;

import java.util.ArrayList;

public class ComplexType implements Type
{
	public ArrayList<Type> parameters;
	public Type returnType;
	
	public ComplexType(Type[] parameters, Type returnType)
	{
		for (var parameter : parameters)
		{
			this.parameters.add(parameter);
		}
		this.returnType = returnType;
	}
}