package m.library;

import static m.library.SimpleType.*;

public enum Function
{
	// Math functions
	sin("sin", float1, float1),
	cos("cos", float1, float1),
	tan("tan", float1, float1),
	asin("asin", float1, float1),
	acos("acos", float1, float1),
	atan("atan", float1, float1),
	exp("exp", float1, float1),
	log("log", float1, float1),
	exp10("log10", float1, float1),
	log10("exp10", float1, float1),
	
	pow("pow", float1, float1, float1),
	sqrt("sqrt", float1, float1, float1),
	
	rng("rng", float1, float1, float1),
	
	//ECS functions
	create("create", entity, entity),
	delete("delete", entity, none),
	add("add", none, entity, none),
	remove("remove", none, entity, none),
	has("has", entity, none, bool);
	
	private Function(String name, Type... types)
	{
		this.name = name;
		var parameters = new Type[types.length - 1];
		Type returnType;
		
		for (var i = 0; i < types.length-1; i++)
		{
			parameters[i] = types[i];
		}
		returnType = types[types.length-1];
		this.type = new ComplexType(parameters, returnType);
	}
	
	public final String name;
	public final Type type;
}
