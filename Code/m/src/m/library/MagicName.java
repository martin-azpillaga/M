package m.library;

import static m.library.SimpleType.*;

public enum MagicName
{
	triggered("Triggered", bool, input),
	range("Range", float1, input),
	vector("Vector", float2, input),
	
	elapsed("Elapsed", float1, float1),
	timeout("Timeout", bool, float1),
	
	chosen("Chosen", bool, none),
	transition("Transition", bool, none);
	
	private MagicName(String ending, SimpleType type, SimpleType baseType)
	{
		this.ending = ending;
		this.type = type;
		this.baseType = baseType;
	}
	
	public String ending;
	public SimpleType type;
	public SimpleType baseType;
}
