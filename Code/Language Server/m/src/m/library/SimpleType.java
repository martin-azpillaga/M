package m.library;

public enum SimpleType implements Type
{
	tag(true, false),
	bool(true, false),
	float1(true, false),
	float2(true, false),
	float3(true, false),
	float4(true, false),
	
	material(true, false),
	mesh(true, false),
	audioClip(false, false),
	stateMachine(false, false),
	input(false, false),
	
	font(false, false),
	image(false, false),
	text(false, false),

	entity(true, false),
	world(false, false),
	entityList(false, true),

	type(false, false),
	any(false, false),
	none(false, false);
	
	public final boolean isValueType;
	public final boolean isCollection;
	
	private SimpleType(boolean isValueType, boolean isCollection)
	{
		this.isValueType = isValueType;
		this.isCollection = isCollection;
	}
}