package m.validation;

import static m.types.AtomicType.*;

import m.types.AtomicType;

public enum StandardBlock {
	QUERY(ENTITY),
	SELECTION(PROPOSITION),
	ITERATION(PROPOSITION)
	;
	
	AtomicType type;
	
	StandardBlock(AtomicType type) {
		this.type = type;
	}
}
