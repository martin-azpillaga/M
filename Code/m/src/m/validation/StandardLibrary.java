package m.validation;

import static m.validation.Type.*;
public enum StandardLibrary 
{
	position,rotation,scale,
	mesh,material,
	font,text,image,button,
	mass,restitution,friction;
	
	public Type getType()
	{
		switch (this)
		{
			case button: return Asset;
			case font: return Asset;
			case friction: return Float1;
			case image: return Asset;
			case mass: return Float1;
			case material: return Asset;
			case mesh: return Asset;
			case position: return Float3;
			case restitution: return Float1;
			case rotation: return Float4;
			case scale: return Float3;
			case text: return Asset;
			default: return Tag;
		}
	}
}