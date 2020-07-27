package m.library.types;

import static m.library.types.AtomicType.*;

public class TypeVariable implements Type {
	
	public static final TypeVariable A = new TypeVariable("a");
	public static final TypeVariable A_SUB_NUMERIC = new TypeVariable("a", NUMERIC);
	
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
