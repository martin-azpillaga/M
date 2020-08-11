package m.library;

import static java.util.Map.entry;
import static m.library.symbols.Value.*;
import static m.library.symbols.Component.*;
import static m.library.symbols.Function.*;
import static m.library.symbols.Block.*;
import static m.library.types.AtomicType.*;

import java.util.ArrayList;
import java.util.Map;

import m.library.symbols.Block;
import m.library.symbols.Component;
import m.library.symbols.Function;
import m.library.symbols.Value;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.library.types.TypeVariable;
import m.validation.problems.Problem;
import m.validation.problems.errors.*;
import m.validation.problems.warnings.*;

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
		
		
		entry("mass", MASS),
		entry("inertia", INERTIA),
		
		entry("velocity",VELOCITY),
		entry("angularVelocity", ANGULAR_VELOCITY),
		entry("restitution", RESTITUTION),
		entry("friction", FRICTION),
		entry("kinematic", KINEMATIC),
		
		entry("crossable", NO_COLLISION_RESPONSE),
		entry("radius", RADIUS),
		entry("extents", EXTENTS),
		entry("boxCenter", BOX_CENTER),
		entry("sphereCenter", SPHERE_CENTER),
		
		entry("mesh", Component.MESH),
		entry("material", Component.MATERIAL),
		entry("shadowReceiver", SHADOW_RECEIVER),
		
		entry("near", NEAR),
		entry("far", FAR),
		entry("fieldOfView", FOV),
		entry("orthographicSize", ORTHOGRAPHIC_SIZE),
		entry("background", BACKGROUND),
		entry("culling", CULLING),
		entry("viewport", VIEWPORT),
		entry("renderTexture", RENDER_TEXTURE),
		entry("display", DISPLAY),
		
		entry("emission", EMISSION),
		entry("spotAngle", SPOT_ANGLE),
		entry("range", RANGE),
		entry("intensity", INTENSITY),
		entry("bounceIntensity", INDIRECT_MULTIPLIER),
		entry("cookie", COOKIE),
		
		entry("text", Component.TEXT),
		entry("font", Component.FONT),
		entry("textColor", TEXT_COLOR),
		entry("textMaterial", TEXT_MATERIAL),
		
		entry("image", Component.IMAGE),
		entry("imageColor", IMAGE_COLOR),
		entry("imageMaterial", IMAGE_MATERIAL),
		
		entry("toggled", TOGGLED),
		entry("sliderValue", SLIDER_VALUE),
		entry("writtenText", TEXTFIELD_VALUE),
		
		entry("anchorMin", ANCHOR_MIN),
		entry("anchorMax", ANCHOR_MAX),
		
		
		entry("audioClip", Component.AUDIOCLIP),
		entry("volume", VOLUME),
		entry("pitch", PITCH),
		entry("loop", LOOP)
		),
	Map.ofEntries(
		entry("abs", ABS),
		entry("sign", SIGN),
		entry("ceil", CEIL),
		entry("floor", FLOOR),
		entry("round", ROUND),
		entry("integerPart", INTEGERPART),
		entry("fractionalPart", FRACTIONALPART),
		entry("inverse", INVERSE),
		entry("reciprocal", RECIPROCAL),

		entry("clamp", CLAMP),
		entry("lerp", LERP),
		entry("unlerp", UNLERP),
		entry("proportional", PROPORTIONAL),
		entry("slerp", SLERP),
		
		entry("min", MIN),
		entry("max", MAX),
		
		entry("degrees", DEGREES),
		entry("radians", RADIANS),
		
		entry("step", STEP),
		
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
		entry("x", X),
		entry("y", Y),
		entry("z", Z),
		entry("xyz", XYZ),
		
		entry("sin", SIN),
		entry("cos", COS),
		entry("tan", TAN),
		entry("asin", ASIN),
		entry("acos", ACOS),
		entry("atan", ATAN),
		entry("exp", EXP),
		entry("log", LOG),
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
		entry("writeWarning", WRITE_WARNING),
		entry("halt", HALT),
		entry("breakpoint", BREAKPOINT),
		entry("screenshot", SCREENSHOT),
		
		
		entry("setNumber", SET_NUMBER),
		entry("setColor", SET_COLOR),
		entry("setBool", SET_KEYWORD),
		entry("setTexture", SET_TEXTURE),
		entry("setInteger", SET_INTEGER),
		entry("getNumber", GET_NUMBER),
		entry("getColor", GET_COLOR),
		entry("getBool", GET_KEYWORD),
		entry("getTexture", GET_TEXTURE),
		entry("getInteger", GET_INTEGER),
		
		entry("setTrigger", SET_TRIGGER),
		entry("inState", IN_STATE),
		
		entry("readTriggered", READ_TRIGGERED),
		entry("readNumber", READ_NUMBER),
		entry("readVector", READ_VECTOR),
		
		entry("quaternion", TO_QUATERNION),
		entry("angles", TO_NUMBER3),
		
		entry("play", PLAY),
		entry("playOnce", PLAY_ONCE),
		entry("pause", PAUSE),
		entry("unpause", UNPAUSE),
		entry("stop", STOP),
		
		entry("over", OVER),
		entry("string", TO_STRING),
		entry("number", TO_NUMBER),
		
		entry("overlaps", OVERLAPS),
		entry("screenOverlaps", SCREEN_OVERLAPS),
		entry("addForce", ADD_FORCE),
		entry("addTorque", ADD_TORQUE),
		entry("closestPoint", CLOSEST_POINT),
		
		entry("isPositive", IS_POSITIVE),
		entry("isNegative", IS_NEGATIVE),
		entry("isZero", IS_ZERO),
		
		entry("worldToViewport", WORLD_TO_VIEWPORT),
		entry("viewportToWorld", VIEWPORT_TO_WORLD),
		
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
		
		entry(INPUT, "input"),
		entry(AtomicType.MESH, "mesh"),
		entry(AtomicType.MATERIAL, "material"),
		entry(AtomicType.FONT, "font"),
		entry(AtomicType.TEXT, "text"),
		entry(AtomicType.IMAGE, "image"),
		entry(AtomicType.AUDIOCLIP, "audioClip"),
		entry(AtomicType.ANIMATOR, "animator"),
		
		entry(ENTITY, "entity"),
		entry(ENTITY_LIST, "entityList"),
		entry(QUATERNION, "quaternion"),
		entry(TEXTURE, "texture")
	), Map.of(
		RedefinedSymbol.class, "Redefined symbol",
		UndefinedSymbol.class, "Undefined symbol",
		IncompatibleTypes.class, "Incompatible types",
		UndecidableType.class, "Undecidable type",
		UnusedValue.class, "Unused value",
		ReadOnly.class, "Cannot assign new value to read only constant"
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
				
				
				entry("masa", MASS),
				entry("inertzia", INERTIA),
				entry("abiadura",VELOCITY),
				entry("abiaduraAngeluarra", ANGULAR_VELOCITY),
				entry("restituzioa", RESTITUTION),
				entry("marruskadura", FRICTION),
				entry("kinematikoa", KINEMATIC),
				
				entry("gurutzagarria", NO_COLLISION_RESPONSE),
				entry("erradioa", RADIUS),
				entry("dimentsioak", EXTENTS),
				entry("kaixaZentrua", BOX_CENTER),
				entry("esferaZentrua", SPHERE_CENTER),
				
				
				entry("malla", Component.MESH),
				entry("materiala", Component.MATERIAL),
				entry("itzalJasotzailea", SHADOW_RECEIVER),
				
				entry("gertu", NEAR),
				entry("urruti", FAR),
				entry("ikusmenAngelua", FOV),
				entry("ikusmenTamaina", ORTHOGRAPHIC_SIZE),
				entry("irudikatu", CULLING),
				entry("atzealdea", BACKGROUND),
				entry("viewport", VIEWPORT),
				entry("renderizazioEhundura", RENDER_TEXTURE),
				entry("pantaila", DISPLAY),
				
				entry("emisioa", EMISSION),
				entry("fokoAngelua", SPOT_ANGLE),
				entry("irismena", RANGE),
				entry("intentsitatea", INTENSITY),
				entry("erreboteIntentsitatea", INDIRECT_MULTIPLIER),
				entry("gaileta", COOKIE),
				
				entry("testua", Component.TEXT),
				entry("letraEstiloa", Component.FONT),
				entry("letraKolorea", TEXT_COLOR),
				entry("letraMateriala", TEXT_MATERIAL),
				
				entry("irudia", Component.IMAGE),
				entry("irudiKolorea", IMAGE_COLOR),
				entry("irudiMateriala", IMAGE_MATERIAL),
				
				entry("sakatuta", TOGGLED),
				entry("slideBalioa", SLIDER_VALUE),
				entry("idatzitakoTestua", TEXTFIELD_VALUE),
				
				entry("euskarriaMin", ANCHOR_MIN),
				entry("euskarriaMax", ANCHOR_MAX),
				
				
				entry("audioKlipa", Component.AUDIOCLIP),
				entry("bolumena", VOLUME),
				entry("tonua", PITCH),
				entry("errepikapena", LOOP)),
			Map.ofEntries(
				entry("abs", ABS),
				entry("sign", SIGN),
				entry("ceil", CEIL),
				entry("floor", FLOOR),
				entry("round", ROUND),
				entry("integerPart", INTEGERPART),
				entry("fractionalPart", FRACTIONALPART),
				entry("inverse", INVERSE),
				entry("reciprocal", RECIPROCAL),
				
				entry("clamp", CLAMP),
				entry("lerp", LERP),
				entry("unlerp", UNLERP),
				entry("proportional", PROPORTIONAL),
				entry("slerp", SLERP),
				
				entry("min", MIN),
				entry("max", MAX),
				
				entry("graduak", DEGREES),
				entry("radianak", RADIANS),
				
				entry("step", STEP),
				
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
				entry("x", X),
				entry("y", Y),
				entry("z", Z),
				entry("xyz", XYZ),
				
				entry("sin", SIN),
				entry("cos", COS),
				entry("tan", TAN),
				entry("asin", ASIN),
				entry("acos", ACOS),
				entry("atan", ATAN),
				entry("exp", EXP),
				entry("log", LOG),
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
				entry("idatziAholkua", WRITE_WARNING),
				entry("amaitu", HALT),
				entry("gelditu", BREAKPOINT),
				entry("argazkiaAtera", SCREENSHOT),
				
				
				entry("ezarriZenbakia", SET_NUMBER),
				entry("ezarriKolorea", SET_COLOR),
				entry("ezarriOsoa", SET_INTEGER),
				entry("ezarriTexture", SET_TEXTURE),
				entry("ezarriProposizioa", SET_KEYWORD),
				entry("hartuZenbakia", GET_NUMBER),
				entry("hartuKolorea", GET_COLOR),
				entry("hartuOsoa", GET_INTEGER),
				entry("hartuTexture", GET_TEXTURE),
				entry("hartuProposizioa", GET_KEYWORD),
				
				entry("ezarriKakoa", SET_TRIGGER),
				entry("egoera", IN_STATE),
				
				entry("irakurriKakoa", READ_TRIGGERED),
				entry("irakurriZenbakia", READ_NUMBER),
				entry("irakurriBektorea", READ_VECTOR),
				
				entry("kuaternioia", TO_QUATERNION),
				entry("angeluak", TO_NUMBER3),
				
				entry("hasi", PLAY),
				entry("playOnce", PLAY_ONCE),
				entry("pause", PAUSE),
				entry("unpause", UNPAUSE),
				entry("stop", STOP),
				
				entry("saguaGainean", OVER),
				entry("zenbakia", TO_NUMBER),
				entry("katea", TO_STRING),
				
				entry("gainean", OVERLAPS),
				entry("pantailaGainean", SCREEN_OVERLAPS),
				entry("gehituIndarra", ADD_FORCE),
				entry("gehituIndarAngeluarra", ADD_TORQUE),
				entry("puntuGertukoena", CLOSEST_POINT),
				
				entry("positiboa", IS_POSITIVE),
				entry("negatiboa", IS_NEGATIVE),
				entry("zero", IS_ZERO),
				
				entry("mundutikPantailara", WORLD_TO_VIEWPORT),
				entry("pantailatikMundura", VIEWPORT_TO_WORLD),
				
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
					
					entry(INPUT, "inputa"),
					entry(AtomicType.MESH, "malla"),
					entry(AtomicType.MATERIAL, "materiala"),
					entry(AtomicType.FONT, "letraEstiloa"),
					entry(AtomicType.TEXT, "testua"),
					entry(AtomicType.IMAGE, "irudia"),
					entry(AtomicType.AUDIOCLIP, "audioKlipa"),
					entry(AtomicType.ANIMATOR, "animatzailea"),
					
					entry(ENTITY, "entitatea"),
					entry(ENTITY_LIST, "entitateLista"),
					entry(QUATERNION, "kuaternioia"),
					entry(TEXTURE, "textura")
			), Map.of(
				RedefinedSymbol.class, "Simbolo hau dagoeneko definitua dago",
				UndefinedSymbol.class, "Simbolo honek ez du definiziorik",
				IncompatibleTypes.class, "Izaera bateraezinak",
				UndecidableType.class, "Izaera ezarriezina",
				UnusedValue.class, "Erabilerarik gabeko balioa",
				ReadOnly.class, "Ezin konstante baten balioa berrezarri"
			))
	;

	Map<String, Value> variables;
	Map<String, Component> components;
	Map<String, Function> functions;
	Map<String, Block> blocks;
	
	Map<Type, String> atomicTypes;
	Map<Class<? extends Problem>, String> problems;
	
	Library(Map<String, Value> variables, Map<String, Component> components, Map<String, Function> functions, Map<String, Block> blocks, Map<Type, String> atomicTypes, Map<Class<? extends Problem>, String> problems)
	{
		this.variables = variables;
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
		this.problems = problems;
	}
	
	public Value getValue(String name) {
		return variables.get(name);
	}
	
	public Component getComponent(String name) {
		return components.get(name);
	}
	
	public Function getFunction(String name) {
		return functions.get(name);
	}
	
	public Block getBlock(String name) {
		return blocks.get(name);
	}
	
	public String getProblem(Class<? extends Problem> problem)
	{
		return problems.get(problem);
	}
	
	public String name(Type type)
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
