package m.library;

import m.types.Type;
import static m.types.AtomicType.*;


public enum StandardBlock {
	QUERY(ENTITY),
	SELECTION(PROPOSITION),
	ITERATION(PROPOSITION)
	;
	
	Type type;
	
	StandardBlock(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
