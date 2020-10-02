package m.library.types;

public class TypeVariable implements Type
{	
	public static final TypeVariable A = new TypeVariable("a");
	
	public final String name;
	public final Type bound;
	
	public TypeVariable(String name)
	{
		this.name = name;
		this.bound = null;
	}
}
