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
	), Map.ofEntries(
		entry(STRING, "string"),
		entry(PROPOSITION, "proposition"),
		entry(NUMBER, "number"),
		entry(NUMBER2, "number2"),
		entry(NUMBER3, "number3"),
		entry(UNIT, "unit"),
		
		entry(COMPONENT, "component"),
		
		entry(COLOR, "color"),
		entry(NUMERIC, "numeric"),
		
		entry(INPUT, "input"),
		entry(AtomicType.MESH, "mesh"),
		entry(AtomicType.MATERIAL, "material"),
		entry(FONT, "font"),
		entry(TEXT, "text"),
		entry(IMAGE, "image"),
		entry(AtomicType.AUDIOCLIP, "audioClip"),
		entry(ANIMATOR, "animator"),
		
		entry(ENTITY, "entity"),
		entry(ENTITY_LIST, "entityList")
	), Map.of(
		REDEFINED, "Redefined symbol",
		UNDEFINED, "Undefined symbol"
	),
	Map.of(
		INCOMPATIBLE, "Incompatible types",
		INDETERMINATE, "Indeterminate type"
	)),
	EUSKARA(
			Map.ofEntries(
				entry("epsilon", EPSILON),
				entry("pi", PI),
				entry("e", E),
				entry("deltaDenbora", DELTA_TIME),
				entry("deltaDenboraZehaztua", FIXED_DELTA_TIME),
				entry("denbora", TIME_SINCE_START),
				entry("denboraEskala", TIME_SCALE)
			),
			Map.ofEntries(
				entry("kokapena", POSITION),
				entry("biraketa", ROTATION),
				entry("eskala", SCALE),
				entry("gurasoa", PARENT),
				entry("umeak", CHILDREN),
				
				
				entry("masa", MASS),
				entry("inertzia", INERTIA),
				entry("kinematikoa",KINEMATIC),
				
				entry("abiadura",VELOCITY),
				entry("azelerazioa", ACCELERATION),
				entry("indarra", FORCE),
				entry("abiaduraAngeluarra", ANGULAR_VELOCITY),
				entry("azelerazioAngeluarra", ANGULAR_ACCELERATION),
				entry("indarAngeluarra", ANGULAR_FORCE),
				
				entry("kolisioak", COLLISIONS),
				entry("kolisioGeruza", COLLISION_LAYER),
				entry("kolisioMaskara", COLLISION_MASK),
				entry("restituzioa", RESTITUTION),
				entry("marruskadura", FRICTION),
				entry("kolisioAdierazleak", COLLISION_EVENTS),
				entry("kolisioReakzioEza", NO_COLLISION_RESPONSE),
				
				entry("erradioa", RADIUS),
				entry("dimentsioak", EXTENTS),
				entry("mallaItxura", MESH_COLLIDER),
				entry("ahurra", CONVEX_HULL),
				
				entry("lotura", BOND),
				entry("loturaPuntua", ANCHOR),
				entry("hausteIndarra", BREAK_FORCE),
				entry("hausteIndarAngeluarra", BREAK_ANGULAR_FORCE),
				
				entry("mugiezinaX", LOCKED_POSITION_X),
				entry("mugiezinaY", LOCKED_POSITION_Y),
				entry("mugiezinaZ", LOCKED_POSITION_Z),
				entry("biraezina", LOCKED_ROTATION),
				
				
				entry("malla", Symbol.MESH),
				entry("materiala", Symbol.MATERIAL),
				
				entry("zenbakia", NUMBER_LABEL),
				
				entry("gertu", NEAR),
				entry("urruti", FAR),
				entry("ikusmenAngelua", FOV),
				entry("perspektiba", PERSPECTIVE),
				entry("atzealdea", BACKGROUND),
				entry("zerua", SKYBOX),
				entry("viewport", VIEWPORT),
				entry("renderizazioEhundura", RENDER_TEXTURE),
				
				entry("emisioa", EMISSION),
				entry("fokoAngelua", SPOT_ANGLE),
				entry("irismena", RANGE),
				entry("intentsitatea", INTENSITY),
				
				
				entry("audioKlipa", Symbol.AUDIOCLIP),
				entry("bolumena", VOLUME),
				entry("tonua", PITCH),
				entry("errepikapena", LOOP)),
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
				entry("barne", IN),
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
				entry("ausazko", RANDOM),
				
				entry("sortu", CREATE),
				entry("desegin", DESTROY),
				entry("gehitu", ADD),
				entry("kendu", REMOVE),
				entry("badu", HAS),
				
				entry("idatzi", WRITE),
				entry("idatziAkatsa", WRITEERROR),
				entry("gelditu", HALT),
				
				
				entry("ezarriZenbakia", SET_NUMBER),
				entry("ezarriKolorea", SET_COLOR),
				entry("ezarriKatea", SET_STRING),
				
				entry("ezarriKakoa", SET_TRIGGER),
				entry("egoeraIzena", STATE_NAME),
				
				entry("irakurriKakoa", READ_TRIGGERED),
				entry("irakurriZenbakia", READ_NUMBER),
				
				entry("=", ASSIGNMENT)),
			Map.ofEntries(
				entry("hartu", QUERY),
				entry("baldin", SELECTION),
				entry("bitartean", ITERATION)
			), Map.ofEntries(
					entry(STRING, "katea"),
					entry(PROPOSITION, "proposizioa"),
					entry(NUMBER, "zenbakia"),
					entry(NUMBER2, "zenbakia2"),
					entry(NUMBER3, "zenbakia3"),
					entry(UNIT, "hutsa"),
					
					entry(COMPONENT, "komponentea"),
					
					entry(COLOR, "kolorea"),
					entry(NUMERIC, "zenbagarria"),
					
					entry(INPUT, "inputa"),
					entry(AtomicType.MESH, "malla"),
					entry(AtomicType.MATERIAL, "materiala"),
					entry(FONT, "letraEstiloa"),
					entry(TEXT, "testua"),
					entry(IMAGE, "irudia"),
					entry(AtomicType.AUDIOCLIP, "audioKlipa"),
					entry(ANIMATOR, "animatzailea"),
					
					entry(ENTITY, "entitatea"),
					entry(ENTITY_LIST, "entitateLista")
			), Map.of(
				REDEFINED, "Simbolo hau dagoeneko definitua dago",
				UNDEFINED, "Simbolo honek ez du definiziorik"
			),
			Map.of(
				INCOMPATIBLE, "Izaera bateraezinak",
				INDETERMINATE, "Izaera indeterminatua"
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
