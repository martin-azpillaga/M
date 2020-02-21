package m.library;

import java.util.ArrayList;

public class ComplexType implements Type
{
	public ArrayList<Type> parameters;
	public Type returnType;
	
	public ComplexType(Type[] parameters, Type returnType)
	{
		this.parameters = new ArrayList<Type>();
		for (var parameter : parameters)
		{
			this.parameters.add(parameter);
		}
		this.returnType = returnType;
	}
	
	public String toString()
	{
		var result = "(";
		for (var type : parameters)
		{
			result += type.toString() + ", ";
		}
		result += ")";
		
		return result; 
	}
}