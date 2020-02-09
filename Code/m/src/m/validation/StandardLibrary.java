package m.validation;

import static m.validation.Type.*;

import java.util.HashSet;
public enum StandardLibrary 
{
	// Input works with magic components
	// XTrigger, XRange and XVector
	// Timers work with magic components
	// XTotal, XElapsed and XTimeout
	// Transform
	position,rotation,scale,
	// Rendering 3D
	mesh,material,
	// Rendering UI
	font,text,image,button,number,
	// Rendering camera
	viewAngle, viewDistance, clearColor, perspective,
	// Rendering lights
	emission, spotAngle, range, intensity, shadows, cookie,
	// Audio control
	audioClip, volume, pitch, loop,
	// Audio effects
	distortion, echo, highPass, lowPass, reverb, chorus, doppler,
	// Network components
	ip,port,networkStream,prediction,owner,
	// Network synchronization happens with magic component names
	// X_Server to send to the server, X_Client to send to client
	// Physics body
	mass,velocity,acceleration,force,
	damping,angularDamping,angularVelocity,gravityFactor,
	// Physics shape
	trigger,extents,radius,height,sideCount,convexHull,geometry,
	restitution,friction,
	// Physics events
	collisions, collisionEntries, collisionExits,
	// Animation
	stateMachine,
	// Animation with magic components
	// XTransition puts the trigger on stateMachine to on/off
	// AI planner
	plan;
	// AI planner with magic components
	// XChosen set to true if the planner chose X this frame.
	
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
			case chorus: return float1;
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
			case ip: return float4;
			case port: return float1;
			case networkStream: return tag;
			case owner: return float1;
			case prediction: return tag;
			case plan: return tag;
			case stateMachine: return Type.stateMachine;
		}
		return none;
	}
	public String getUnityType(HashSet<String> namespaces)
	{
		switch (this)
		{
		case acceleration: return "noUnityName";
		case angularDamping: namespaces.add("Unity.Physics"); return "PhysicsDamping";
		case angularVelocity: namespaces.add("Unity.Physics"); return "PhysicsVelocity";
		case audioClip: namespaces.add("UnityEngine"); return "AudioSource";
		case button: namespaces.add("UnityEngine.UI"); return "Button";
		case clearColor: namespaces.add("UnityEngine"); return "Camera";
		case collisionEntries: return "CollisionEntries";
		case collisionExits: return "CollisionExits";
		case collisions: return "Collisions";
		case convexHull: namespaces.add("Unity.Physics"); return "PhysicsCollider";
		case cookie: namespaces.add("UnityEngine"); return "Light";
		case damping: namespaces.add("Unity.Physics"); return "PhysicsDamping";
		case chorus: namespaces.add("UnityEngine"); return "AudioChorusFilter";
		case distortion:
			namespaces.add("UnityEngine");
			return "AudioDistortionFilter";
		case doppler:
			namespaces.add("UnityEngine");
			return "AudioSource";
		case echo:
			namespaces.add("UnityEngine");
			return "AudioEchoFilter";
		case emission:
			namespaces.add("UnityEngine");
			return "Light";
		case extents:
			namespaces.add("Unity.Physics");
			return "PhysicsShape";
		case font:
			namespaces.add("UnityEngine");
			return "Font";
		case force: return "noUnityName";
		case friction:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case geometry:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case gravityFactor:
			namespaces.add("Unity.Physics");
			return "PhysicsGravityFactor";
		case height:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case highPass:
			namespaces.add("UnityEngine");
			return "AudioHighPassFilter";
		case image:
			namespaces.add("UnityEngine.UI");
			return "Image";
		case intensity:
			namespaces.add("UnityEngine");
			return "Light";
		case ip:
			return "noUnityName";
		case loop:
			namespaces.add("UnityEngine");
			return "AudioSource";
		case lowPass:
			namespaces.add("UnityEngine");
			return "AudioLowpassFilter";
		case mass:
			namespaces.add("Unity.Physics");
			return "PhysicsMass";
		case material:
			namespaces.add("Unity.Hybrid.Rendering");
			return "RenderMesh";
		case mesh:
			namespaces.add("Unity.Hybrid.Rendering");
			return "RenderMesh";
		case networkStream:
			return "noUnityName";
		case number:
			namespaces.add("UnityEngine.UI");
			return "Text";
		case owner:
			return "noUnityName";
		case perspective:
			namespaces.add("UnityEngine");
			return "Camera";
		case pitch:
			namespaces.add("UnityEngine");
			return "AudioSource";
		case plan:
			return "noUnityName";
		case port:
			return "noUnityName";
		case position:
			namespaces.add("Unity.Transforms");
			return "Translation";
		case prediction:
			return "noUnityName";
		case radius:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case range:
			namespaces.add("UnityEngine");
			return "Light";
		case restitution:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case reverb:
			namespaces.add("UnityEngine");
			return "AudioReverbFilter";
		case rotation:
			namespaces.add("Unity.Transforms");
			return "Rotation";
		case scale:
			namespaces.add("Unity.Transforms");
			return "NonUniformScale";
		case shadows:
			namespaces.add("Unity.Hybrid.Rendering");
			return "RenderMesh";
		case sideCount:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case spotAngle:
			namespaces.add("UnityEngine");
			return "Light";
		case stateMachine:
			return "noUnityName";
		case text:
			namespaces.add("UnityEngine");
			return "Text";
		case trigger:
			namespaces.add("Unity.Physics");
			return "PhysicsCollider";
		case velocity:
			namespaces.add("Unity.Physics");
			return "PhysicsVelocity";
		case viewAngle:
			namespaces.add("UnityEngine");
			return "Camera";
		case viewDistance:
			namespaces.add("UnityEngine");
			return "Camera";
		case volume:
			namespaces.add("UnityEngine");
			return "AudioSource";
		default:
			return "noUnityName";
		
		}
	}
	
	public boolean isValueType()
	{
		switch(this)
		{
		case acceleration: return true;

		case angularDamping: return true;

		case angularVelocity: return true;

		case audioClip: return false;

		case button: return false;

		case chorus: return false;

		case clearColor: return false;

		case collisionEntries: return true;

		case collisionExits: return true;

		case collisions: return true;

		case convexHull: return true;

		case cookie: return false;

		case damping: return true;

		case distortion: return false;

		case doppler: return false;

		case echo: return false;

		case emission: return false;

		case extents: return true;

		case font: return false;

		case force: return true;

		case friction: return true;

		case geometry: return true;

		case gravityFactor: return true;

		case height: return true;

		case highPass: return false;

		case image: return false;

		case intensity: return false;

		case ip: return false;

		case loop: return false;

		case lowPass: return false;

		case mass: return true;

		case material: return true;

		case mesh: return true;

		case networkStream: return false;

		case number: return false;

		case owner: return false;

		case perspective:

		case pitch: return false;

		case plan: return false;

		case port: return false;

		case position: return true;

		case prediction: return false;

		case radius: return true;

		case range: return false;

		case restitution: return true;

		case reverb: return false;

		case rotation: return true;

		case scale: return true;

		case shadows: return false;

		case sideCount: return true;

		case spotAngle: return false;

		case stateMachine: return false;

		case text: return false;

		case trigger: return true;

		case velocity: return true;

		case viewAngle: return false;

		case viewDistance: return false;

		case volume: return false;

		default: return false;
		}
	}
}