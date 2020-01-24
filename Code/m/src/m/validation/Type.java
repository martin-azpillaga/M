package m.validation;

public enum Type
{
	tag,float1,float2,float3,float4,
	material,mesh,audioClip,font,image,
	gameObject, input, entity, text, entityList,
	bool,
	none,
	stateMachine;

	public boolean isNumeric() 
	{
		return this == float1 || this == float2 || this == float3 || this == float4;
	}
	
	public boolean isList()
	{
		return this == entityList;
	}
}