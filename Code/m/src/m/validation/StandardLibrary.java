package m.validation;

import static m.validation.Type.*;
public enum StandardLibrary 
{
	position,rotation,scale,
	mesh,material,
	font,text,image,button,number,
	mass,restitution,friction,velocity,acceleration,force;
	
	public Type getType()
	{
		switch (this)
		{
			case button: return tag;
			case font: return Type.font;
			case friction: return float1;
			case image: return Type.image;
			case mass: return float1;
			case material: return Type.material;
			case mesh: return Type.mesh;
			case position: return float3;
			case restitution: return float1;
			case rotation: return float4;
			case scale: return float3;
			case text: return Type.text;
			case velocity: return Type.float3;
			case acceleration: return Type.float3;
			case force: return Type.float3;
			case number: return Type.float1;
		}
		return tag;
	}
}