package m.library;

import static m.library.SimpleType.*;

public enum Component 
{
	// Input works with magic components
	 inputTriggered(bool, null, "inputTriggered", "Value", true),
	 inputValue(float1, null, "inputValue", "Value", true),
	 inputVector(float2, null, "inputVector", "Value", true),
	// Timers work with magic components
	 timer(float1, null, "timer", "Value", true),
	 elapsed(float1, null, "elapsed", "Value", true),
	 timeout(bool, null, "timeout", "Value", true),
	// Transform
	position(float3, "Unity.Transforms", "Translation", "Value", true),
	rotation(float4, "Unity.Transforms", "Rotation", "Value", true),
	scale(float3, "Unity.Transforms", "NonUniformScale", "Value", true),
	// Rendering 3D
	mesh(SimpleType.mesh, "Unity.Hybrid.Rendering", "RenderMesh", "mesh", true),
	material(SimpleType.material, "Unity.Hybrid.Rendering", "RenderMesh", "material", true),
	// Rendering UI
	font(SimpleType.font, "UnityEngine.UI", "Text", "font", false),
	text(SimpleType.text, null, "text", "text", false),
	image(SimpleType.image, "UnityEngine.UI", "Image", "source", false),
	button(tag, "UnityEngine.UI", "Button", "active", false),
	number(float1, null, "number", "Value", true),
	// Rendering camera
	viewAngle(float1, "UnityEngine", "Camera", "viewAngle", false),
	viewDistance(float1, "UnityEngine", "Camera", "farClipPlane", false),
	clearColor(float3, "UnityEngine", "Camera", "backgroundColor", false),
	perspective(tag, "UnityEngine", "Camera", "isOrthographic", false),
	// Rendering lights
	emission(float4, "UnityEngine", "Light", "color", false),
	spotAngle(float1, "UnityEngine", "Light", "spotAngle", false),
	range(float1, "UnityEngine", "Light", "range", false),
	intensity(float1, "UnityEngine", "Light", "intensity", false),
	shadows(tag, "UnityEngine", "Light", "castShadows", false),
	cookie(SimpleType.image, "UnityEngine", "Light", "cookie", false),
	// Audio control
	audioClip(SimpleType.audioClip, "UnityEngine", "AudioSource", "clip", false),
	volume(float1, "UnityEngine", "AudioSource", "volume", false),
	pitch(float1, "UnityEngine", "AudioSource", "pitch", false),
	loop(tag, "UnityEngine", "AudioSource", "loop", false),
	// Audio effects
	distortion(float1, "UnityEngine", "AudioDistortionFilter", "value", false),
	echo(float1, "UnityEngine", "AudioReverbFilter", "echo", false),
	highPass(float1, "UnityEngine", "AudioHighPassFilter", "value", false),
	lowPass(float1, "UnityEngine", "AudioLowPassFilter", "value", false),
	reverb(float1, "UnityEngine", "AudioReverbFilter", "value", false),
	chorus(float1, "UnityEngine", "AudioChorusFilter", "value", false),
	doppler(float1, "UnityEngine", "AudioSource", "dopplerEffect", false),
	// Network components
	ip(float4, null, "port", "Value", true),
	port(float1, null, "port", "Value", true),
	networkStream(tag, null, "networkStream", null, true),
	prediction(tag, null, "prediction", null, true),
	owner(float1, null, "owner", "Value", true),
	// Network synchronization happens with magic component names
	// X_Server to send to the server, X_Client to send to client
	// Physics body
	mass(float1, "Unity.Physics", "PhysicsMass", "value", true),
	velocity(float3, "Unity.Physics",  "PhysicsVelocity", "Linear", true),
	acceleration(float3, "Unity.Physics", null, null, false),
	force(float3, "Unity.Physics", null, null, false),
	damping(float1, "Unity.Physics", "PhysicsCollider", null, true),
	angularDamping(float1, "Unity.Physics", "PhysicsCollider", null, true),
	angularVelocity(float3, "Unity.Physics", "PhysicsVelocity", "Angular", true),
	gravityFactor(float1, "Unity.Physics", "GravityFactor", "Value", true),
	// Physics shape
	trigger(tag, "Unity.Physics", "PhysicsCollider", null, true),
	extents(float3, "Unity.Physics", "PhysicsCollider", null, true),
	radius(float1, "Unity.Physics", "PhysicsCollider", null, true),
	height(float1, "Unity.Physics", "PhysicsCollider", null, true),
	sideCount(float1, "Unity.Physics", "PhysicsCollider", null, true),
	convexHull(tag, "Unity.Physics", "PhysicsCollider", null, true),
	geometry(tag, "Unity.Physics", "PhysicsCollider", null, true),
	restitution(float1, "Unity.Physics", "PhysicsCollider", null, true),
	friction(float1, "Unity.Physics", "PhysicsCollider", null, true),
	// Physics events
	collisions(entityList, null, "Collisions", "Value", true),
	collisionEntries(entityList, null, "CollisionEntries", "Value", true),
	collisionExits(entityList, null, "CollisionExits", "Value", true),
	// Animation
	stateMachine(SimpleType.stateMachine, "UnityEngine", "Animator", "Controller", false),
	// Animation with magic components
	// XTransition puts the trigger on stateMachine to on/off
	// AI planner
	plan(tag, "UnityEngine.AI", "AIPlanner", null, false);
	// AI planner with magic components
	// XChosen set to true if the planner chose X this frame.
	
	public final SimpleType type;
	public final String namespace;
	public final String unityType;
	public final String unityField;
	public final boolean isValueType;
	
	Component(SimpleType type, String namespace, String unityType, String unityField, boolean isValueType)
	{
		this.type = type;
		this.namespace = namespace;
		this.unityType = unityType;
		this.unityField = unityField;
		this.isValueType = isValueType;
	}
}