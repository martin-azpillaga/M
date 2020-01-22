package m.validation;

import static m.validation.Type.*;
public enum StandardLibrary 
{
	// Transform
	position,rotation,scale,
	// Rendering 3D
	mesh,material,
	// Rendering UI
	font,text,image,button,number,
	// Physics body
	mass,velocity,acceleration,force,
	damping,angularDamping,angularVelocity,gravityFactor,
	// Physics shape
	trigger,extents,radius,height,sideCount,convexHull,geometry,
	restitution,friction,
	// Physics events
	collisions, collisionEntries, collisionExits,
	// Rendering camera
	viewAngle, viewDistance, clearColor, perspective,
	// Rendering lights
	emission, spotAngle, range, intensity, shadows, cookie,
	// Audio control
	audioClip, volume, pitch, loop,
	// Audio effects
	distortion, echo, highPass, lowPass, reverb, delay, doppler;
	
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
			case trigger: return tag;
			case extents: return float3;
			case radius: return float1;
			case viewAngle: return float1;
			case viewDistance: return float1;
			case clearColor: return float4;
			case perspective: return tag;
			case emission: return float4;
			case spotAngle: return float1;
			case range: return float1;
			case intensity: return float1;
			case shadows: return float1;
			case cookie: return Type.image;
			case audioClip: return Type.audioClip;
			case volume: return float1;
			case pitch: return float1;
			case loop: return tag;
			case distortion: return float1;
			case echo: return float1;
			case highPass: return float1;
			case lowPass: return float1;
			case reverb: return float1;
			case delay: return float1;
			case doppler: return float1;
			case angularDamping: return float1;
			case angularVelocity: return float3;
			case damping: return float1;
			case gravityFactor: return float1;
			case convexHull: return float1;
			case geometry: return Type.mesh;
			case height: return float1;
			case sideCount: return float1;
			case collisions: return entityList;
			case collisionEntries: return entityList;
			case collisionExits: return entityList;
		}
		return none;
	}
}