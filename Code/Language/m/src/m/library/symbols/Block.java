package m.library.symbols;

import static m.library.types.AtomicType.ENTITY;
import static m.library.types.AtomicType.PROPOSITION;
import m.library.types.Type;

public enum Block implements Symbol
{
	QUERY(ENTITY),
	SELECTION(PROPOSITION),
	ITERATION(PROPOSITION),
	;

	Type type;

	Block (Type type)
	{
		this.type = type;
	}

	public Type getType()
	{
		return type;
	}
}
