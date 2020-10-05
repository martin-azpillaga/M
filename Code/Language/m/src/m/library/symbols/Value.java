package m.library.symbols;

import static m.library.types.AtomicType.NUMBER;

import m.library.types.Type;

public enum Value implements Symbol
{
	EPSILON(NUMBER),
	PI(NUMBER),
	E(NUMBER),

	DELTA_TIME(NUMBER),
	FIXED_DELTA_TIME(NUMBER),
	TIME_SINCE_START(NUMBER),
	TIME_SCALE(NUMBER),
	;

	Type type;

	Value (Type type)
	{
		this.type = type;
	}

	public Type getType()
	{
		return type;
	}
}
