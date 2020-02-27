package m.library;

import static m.library.SimpleType.*;

public enum Function
{
	// Math constants
	epsilon("epsilon",float1),
	e("e", float1),
	pi("pi", float1),
	
	// Rounding, lose information
	abs("abs", float1, float1),
	sign("sign", float1, float1),
	ceil("ceil", float1, float1),
	floor("floor", float1, float1),
	round("round", float1, float1),
	clamp("clamp", float1, float2, float1),
	lerp("lerp", float2, float1, float1),
	unlerp("unlerp", float1, float2, float1),
	slerp("slerp", float4, float4, float1, float4),
	proportional("proportional", float1, float2, float2, float1),
	fractionalPart("fractionalPart", float1, float1),
	integerPart("integerPart", float1, float1),
	
	// Reduce maps
	max("max", float2, float1),
	min("min", float2, float1),
	sum("sum", float2, float1),
	product("product", float2, float1),
	shuffle("shuffle", float2, float2),
	inverse("inverse", float1, float1),
	reciprocal("reciprocal", float1, float1),
	
	
	// Vector operations
	cross("cross", float3, float3, float3),
	dot("dot", float3, float3, float1),
	length("length", float3, float1),
	normalize("normalize", float3, float3),
	distance("distance", float3, float3, float1),
	quaternionProduct("quaternionProduct", float4, float4, float4),
	reflect("reflect",float3, float3, float3),
	refract("refract", float3, float3, float1, float3),
	
	/* Not supported yet
	determinant("determinant"),
	inverse("inverse"),
	transpose(matrix)
	mul(matrix, vector),
	orthonormalizeMatrix
	hash()
	noise()
	bitwise
	*/
	
	// Optimization
	mad("mad", float1, float1, float1, float1),
	lengthSquared("lengthSquared", float3, float1),
	sincos("sincos", float1, float2),
	
	// Angles
	degrees("degrees", float1, float1),
	radians("radians", float1, float1),
	
	
	// Analytic functions
	sin("sin", float1, float1),
	cos("cos", float1, float1),
	tan("tan", float1, float1),
	sinh("sinh", float1, float1),
	cosh("cosh", float1, float1),
	tanh("tanh", float1, float1),
	
	asin("asin", float1, float1),
	acos("acos", float1, float1),
	atan("atan", float1, float1),
	atan2("atan2", float2, float1),
	exp("exp", float1, float1),
	log("log", float1, float1),
	exp10("log10", float1, float1),
	log10("exp10", float1, float1),
	
	pow("pow", float1, float1, float1),
	sqrt("sqrt", float1, float1, float1),
	
	rng("rng", float1, float1, float1),
	rng2("random", float2, float1),
	
	//ECS functions
	create("create", entity, entity),
	delete("delete", entity, none),
	add("add", SimpleType.type, entity, none),
	remove("remove", SimpleType.type, entity, none),
	has("has", SimpleType.type, entity, bool),
	enable("enable", SimpleType.type, none),
	disable("disable", SimpleType.type, none),
	
	// Collection functions
	append("append", entity, entityList, none);
	
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
	public final ComplexType type;
}
