package m.library;

import static java.util.Map.entry;
import static m.library.Symbol.*;
import static m.library.types.AtomicType.*;
import static m.library.types.TypeVariable.A;
import static m.library.types.TypeVariable.A_SUB_NUMERIC;
import static m.validation.problems.BindingProblem.BindingProblemKind.*;
import static m.validation.problems.TypingProblem.TypingProblemKind.*;

import java.util.ArrayList;
import java.util.Map;

import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.library.types.TypeVariable;
import m.validation.problems.BindingProblem;
import m.validation.problems.Problem;
import m.validation.problems.TypingProblem;
import m.validation.problems.BindingProblem.BindingProblemKind;
import m.validation.problems.TypingProblem.TypingProblemKind;

public enum Library {
	
	ENGLISH(
	Map.ofEntries(
		entry("epsilon", EPSILON),
		entry("pi", PI),
		entry("e", E),
		entry("deltaTime", DELTA_TIME),
		entry("fixedDeltaTime", FIXED_DELTA_TIME),
		entry("time", TIME_SINCE_START),
		entry("timeScale", TIME_SCALE)
	),
	Map.ofEntries(
		entry("position", POSITION),
		entry("rotation", ROTATION),
		entry("scale", SCALE),
		entry("parent", PARENT),
		entry("children", CHILDREN),
		
		
		entry("mass", MASS),
		entry("inertia", INERTIA),
		entry("kinematic",KINEMATIC),
		
		entry("velocity",VELOCITY),
		entry("acceleration", ACCELERATION),
		entry("force", FORCE),
		entry("angularVelocity", ANGULAR_VELOCITY),
		entry("angularAcceleration", ANGULAR_ACCELERATION),
		entry("angularForce", ANGULAR_FORCE),
		
		entry("collisions", COLLISIONS),
		entry("collisionLayer", COLLISION_LAYER),
		entry("collisionMask", COLLISION_MASK),
		entry("restitution", RESTITUTION),
		entry("friction", FRICTION),
		entry("collisionEvents", COLLISION_EVENTS),
		entry("noCollisionResponse", NO_COLLISION_RESPONSE),
		
		entry("radius", RADIUS),
		entry("extents", EXTENTS),
		entry("meshCollider", MESH_COLLIDER),
		entry("convexHull", CONVEX_HULL),
		
		entry("bond", BOND),
		entry("anchor", ANCHOR),
		entry("breakForce", BREAK_FORCE),
		entry("breakAngularForce", BREAK_ANGULAR_FORCE),
		
		entry("lockedPositionX", LOCKED_POSITION_X),
		entry("lockedPositionY", LOCKED_POSITION_Y),
		entry("lockedPositionZ", LOCKED_POSITION_Z),
		entry("lockedRotation", LOCKED_ROTATION),
		
		
		entry("mesh", Symbol.MESH),
		entry("material", Symbol.MATERIAL),
		
		entry("number", NUMBER_LABEL),
		
		entry("near", NEAR),
		entry("far", FAR),
		entry("fieldOfView", FOV),
		entry("perspective", PERSPECTIVE),
		entry("background", BACKGROUND),
		entry("skybox", SKYBOX),
		entry("viewport", VIEWPORT),
		entry("renderTexture", RENDER_TEXTURE),
		
		entry("emission", EMISSION),
		entry("spotAngle", SPOT_ANGLE),
		entry("range", RANGE),
		entry("intensity", INTENSITY),
		
		
		entry("audioClip", Symbol.AUDIOCLIP),
		entry("volume", VOLUME),
		entry("pitch", PITCH),
		entry("loop", LOOP)),
	Map.ofEntries(
		entry("abs", ABS),
		entry("sign", SIGN),
		entry("ceil", CEIL),
		entry("floor", FLOOR),
		entry("round", ROUND),
		entry("clamp", CLAMP),
		entry("integerPart", INTEGERPART),
		entry("fractionalPart", FRACTIONALPART),
		entry("inverse", INVERSE),
		entry("reciprocal", RECIPROCAL),
		
		entry("lerp", LERP),
		entry("unlerp", UNLERP),
		entry("proportional", PROPORTIONAL),
		
		entry("cross", CROSS),
		entry("dot", DOT),
		entry("norm", NORM),
		entry("normalize", NORMALIZE),
		entry("distance", DISTANCE),
		entry("reflect", REFLECT),
		entry("refract", REFRACT),
		
		entry("||", OR),
		entry("&&", AND),
		entry("!", NOT),
		
		entry("+", ADDITION),
		entry("-", SUBTRACTION),
		entry("*", MULTIPLICATION),
		entry("/", DIVISION),
		
		entry("==", EQUAL),
		entry("!=", INEQUAL),
		
		entry("<", LOWER),
		entry("<=", LOWEROREQUAL),
		entry(">=", GREATEROREQUAL),
		entry(">", GREATER),
		
		entry("#", SIZE),
		entry("in", IN),
		entry("xyz", XYZ),
		
		entry("sin", SIN),
		entry("cos", COS),
		entry("tan", TAN),
		entry("asin", ASIN),
		entry("acos", ACOS),
		entry("atan", ATAN),
		entry("exp", EXP),
		entry("log", LOG),
		entry("exp10", EXP10),
		entry("log10", LOG10),
		entry("pow", POW),
		entry("sqrt", SQRT),
		entry("random", RANDOM),
		
		entry("create", CREATE),
		entry("destroy", DESTROY),
		entry("add", ADD),
		entry("remove", REMOVE),
		entry("has", HAS),
		
		entry("write", WRITE),
		entry("writeError", WRITEERROR),
		entry("halt", HALT),
		
		
		entry("setNumber", SET_NUMBER),
		entry("setColor", SET_COLOR),
		entry("setString", SET_STRING),
		
		entry("setTrigger", SET_TRIGGER),
		entry("stateName", STATE_NAME),
		
		entry("readTriggered", READ_TRIGGERED),
		entry("readNumber", READ_NUMBER),
		
		entry("=", ASSIGNMENT)),
	Map.ofEntries(
		entry("foreach", QUERY),
		entry("if", SELECTION),
		entry("while", ITERATION)
	), Map.of(
		NUMBER, "number",
		NUMBER3, "number3"
	), Map.of(
		REDEFINED, "Redefined symbol",
		UNDEFINED, "Undefined symbol"
	),
	Map.of(
		INCOMPATIBLE, "Incompatible types",
		INDETERMINATE, "Indeterminate type"
	))
	;

	public Map<String, Symbol> variables;
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	
	Map<Type, String> atomicTypes;
	Map<BindingProblemKind, String> bindingProblem;
	Map<TypingProblemKind, String> typingProblem;
	
	Library(Map<String, Symbol> variables, Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks, Map<Type, String> atomicTypes, Map<BindingProblemKind, String> bindingProblem, Map<TypingProblemKind, String> typingProblem)
	{
		this.variables = variables;
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
		this.bindingProblem = bindingProblem;
		this.typingProblem = typingProblem;
	}
	
	public String message(Problem problem)
	{
		if (problem instanceof BindingProblem)
		{
			return bindingProblem.get(((BindingProblem) problem).getKind());
		}
		else if (problem instanceof TypingProblem)
		{
			var base = typingProblem.get(((TypingProblem) problem).getKind());
			var node = ((TypingProblem) problem).getNode();
			var root = node;
			
			if (((TypingProblem) problem).getKind() == INCOMPATIBLE)
			{
				while (root.binding != null)
				{
					root = root.binding.node;
				}
				base += "\n" + name(node.typing.getType()) + " - " + name(root.typing.getType());
			}
			return base;
		}
		return "error";
	}
	
	private String name(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomicType = (AtomicType) type;
			
			return atomicTypes.get(atomicType);
		}
		else if (type instanceof TypeVariable)
		{
			return ((TypeVariable) type).getName();
		}
		else if (type instanceof FunctionType)
		{
			var functionType = (FunctionType) type;
			var result = new ArrayList<String>();
			for (var p : functionType.getParameters())
			{
				result.add(name(p));
			}
			return String.join(",", result) + "->" + name(functionType.getReturnType());
		}
		else
		{
			return "unrecognized type";
		}
	}
}
