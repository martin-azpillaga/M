package m.library.types;

public class TypeVariable implements Type
{
	public static final TypeVariable A = new TypeVariable();

	public final Type bound;

	public TypeVariable()
	{
		this.bound = null;
	}
}
