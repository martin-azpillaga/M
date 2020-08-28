package m.library.types;

public class TypeVariable implements Type {
	
	public static final TypeVariable A = new TypeVariable("a");
	
	String name;
	Type bound;
	
	public TypeVariable(String name) {
		this.name = name;
	}
	
	public TypeVariable(String name, Type bound) {
		this.name = name;
		this.bound = bound;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getBound() {
		return bound;
	}
}
