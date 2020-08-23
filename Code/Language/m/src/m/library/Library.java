package m.library;

import static m.library.symbols.Value.*;
import static m.library.symbols.Component.*;
import static m.library.symbols.Function.*;
import static m.library.symbols.Block.*;
import static m.library.types.AtomicType.*;

import java.util.ArrayList;
import java.util.HashMap;
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
import java.util.stream.*;
import com.google.common.collect.BiMap;

import com.google.common.collect.HashBiMap;

public enum Library
{
	ENG
	(
	value -> switch(value)
	{
	case EPSILON-> "epsilon";
	case DELTA_TIME -> ";";
	case E -> "d";
	case FIXED_DELTA_TIME -> "e";
	case PI -> "f";
	case TIME_SCALE -> "throw new UnsupportedOperationException";
	case TIME_SINCE_START -> "timeSinceStart";
	},
	component -> switch(component)
	{
	case POSITION -> "position";
	case ROTATION -> "rotation";
	case SCALE -> "scale";
	case PARENT -> "parent";
	
	case MASS -> "mass";
	case INERTIA -> "inertia";
	
	case VELOCITY -> "velocity";
	case ANGULAR_VELOCITY -> "angularVelocity";
	case RESTITUTION -> "restitution";
	case FRICTION -> "friction";
	case KINEMATIC -> "kinematic";
	
	case NO_COLLISION_RESPONSE -> "crossable";
	case RADIUS -> "radius";
	case EXTENTS -> "extents";
	case BOX_CENTER -> "boxCenter";
	case SPHERE_CENTER -> "sphereCenter";
	
	case MESH -> "mesh";
	case MATERIAL -> "material";
	case SHADOW_RECEIVER -> "shadowReceiver";
	
	case NEAR -> "near";
	case FAR -> "far";
	case FOV -> "fieldOfView";
	case ORTHOGRAPHIC_SIZE -> "orthographicSize";
	case BACKGROUND -> "background";
	case CULLING -> "culling";
	case VIEWPORT -> "viewport";
	case RENDER_TEXTURE -> "renderTexture";
	case DISPLAY -> "display";
	
	case EMISSION -> "emission";
	case SPOT_ANGLE -> "spotAngle";
	case RANGE -> "range";
	case INTENSITY -> "intensity";
	case INDIRECT_MULTIPLIER -> "bounceIntensity";
	case COOKIE -> "cookie";
	
	case TEXT -> "text";
	case FONT -> "font";
	case TEXT_COLOR -> "textColor";
	case TEXT_MATERIAL -> "textMaterial";
	
	case IMAGE -> "image";
	case IMAGE_COLOR -> "imageColor";
	case IMAGE_MATERIAL -> "imageMaterial";
	
	case TOGGLED -> "toggled";
	case SLIDER_VALUE -> "sliderValue";
	case TEXTFIELD_VALUE -> "writtenText";
	
	case ANCHOR_MIN -> "anchorMin";
	case ANCHOR_MAX -> "anchorMax";
	
	
	case AUDIOCLIP -> "audioClip";
	case VOLUME -> "volume";
	case PITCH -> "pitch";
	case LOOP -> "loop";
	}
	),
	ENGLISH(
	new Object[][]
	{
		{"epsilon", EPSILON},
		{"pi", PI},
		{"e", E},
		{"deltaTime", DELTA_TIME},
		{"fixedDeltaTime", FIXED_DELTA_TIME},
		{"timeSinceStart", TIME_SINCE_START},
		{"timeScale", TIME_SCALE}
	}
	,new Object[][]
	{
		{"position", POSITION},
		{"rotation", ROTATION},
		{"scale", SCALE},
		{"parent", PARENT},
		
		{"mass", MASS},
		{"inertia", INERTIA},
		
		{"velocity", VELOCITY},
		{"angularVelocity", ANGULAR_VELOCITY},
		{"restitution", RESTITUTION},
		{"friction", FRICTION},
		{"kinematic", KINEMATIC},
		
		{"crossable", NO_COLLISION_RESPONSE},
		{"radius", RADIUS},
		{"extents", EXTENTS},
		{"boxCenter", BOX_CENTER},
		{"sphereCenter", SPHERE_CENTER},
		
		{"mesh", Component.MESH},
		{"material", Component.MATERIAL},
		{"shadowReceiver", SHADOW_RECEIVER},
		
		{"near", NEAR},
		{"far", FAR},
		{"fieldOfView", FOV},
		{"orthographicSize", ORTHOGRAPHIC_SIZE},
		{"background", BACKGROUND},
		{"culling", CULLING},
		{"viewport", VIEWPORT},
		{"renderTexture", RENDER_TEXTURE},
		{"display", DISPLAY},
		
		{"emission", EMISSION},
		{"spotAngle", SPOT_ANGLE},
		{"range", RANGE},
		{"intensity", INTENSITY},
		{"bounceIntensity", INDIRECT_MULTIPLIER},
		{"cookie", COOKIE},
		
		{"text", Component.TEXT},
		{"font", Component.FONT},
		{"textColor", TEXT_COLOR},
		{"textMaterial", TEXT_MATERIAL},
		
		{"image", Component.IMAGE},
		{"imageColor", IMAGE_COLOR},
		{"imageMaterial", IMAGE_MATERIAL},
		
		{"toggled", TOGGLED},
		{"sliderValue", SLIDER_VALUE},
		{"writtenText", TEXTFIELD_VALUE},
		
		{"anchorMin", ANCHOR_MIN},
		{"anchorMax", ANCHOR_MAX},
		
		
		{"audioClip", Component.AUDIOCLIP},
		{"volume", VOLUME},
		{"pitch", PITCH},
		{"loop", LOOP},
	},
	null
	,null
	,null
	,null
	)
	/*
	ENGLISH(
			(Map<String,Value>)Stream.of(new Object[][] {{"a", PI},{"b",PI}}).collect(Collectors.toMap(data -> (String) data[0], data ->(Value) data[1])),
	Map.ofEntries(
		{"position", POSITION},
		{"rotation", ROTATION},
		{"scale", SCALE},
		{"parent", PARENT},
		
		
		{"mass", MASS},
		{"inertia", INERTIA},
		
		{"velocity", VELOCITY},
		{"angularVelocity", ANGULAR_VELOCITY},
		{"restitution", RESTITUTION},
		{"friction", FRICTION},
		{"kinematic", KINEMATIC},
		
		{"crossable", NO_COLLISION_RESPONSE},
		{"radius", RADIUS},
		{"extents", EXTENTS},
		{"boxCenter", BOX_CENTER},
		{"sphereCenter", SPHERE_CENTER},
		
		{"mesh", Component.MESH},
		{"material", Component.MATERIAL},
		{"shadowReceiver", SHADOW_RECEIVER},
		
		{"near", NEAR},
		{"far", FAR},
		{"fieldOfView", FOV},
		{"orthographicSize", ORTHOGRAPHIC_SIZE},
		{"background", BACKGROUND},
		{"culling", CULLING},
		{"viewport", VIEWPORT},
		{"renderTexture", RENDER_TEXTURE},
		{"display", DISPLAY},
		
		{"emission", EMISSION},
		{"spotAngle", SPOT_ANGLE},
		{"range", RANGE},
		{"intensity", INTENSITY},
		{"bounceIntensity", INDIRECT_MULTIPLIER},
		{"cookie", COOKIE},
		
		{"text", Component.TEXT},
		{"font", Component.FONT},
		{"textColor", TEXT_COLOR},
		{"textMaterial", TEXT_MATERIAL},
		
		{"image", Component.IMAGE},
		{"imageColor", IMAGE_COLOR},
		{"imageMaterial", IMAGE_MATERIAL},
		
		{"toggled", TOGGLED},
		{"sliderValue", SLIDER_VALUE},
		{"writtenText", TEXTFIELD_VALUE},
		
		{"anchorMin", ANCHOR_MIN},
		{"anchorMax", ANCHOR_MAX},
		
		
		{"audioClip", Component.AUDIOCLIP},
		{"volume", VOLUME},
		{"pitch", PITCH},
		{"loop", LOOP},
		),
	Map.ofEntries(
		{"abs", ABS},
		{"sign", SIGN},
		{"ceil", CEIL},
		{"floor", FLOOR},
		{"round", ROUND},
		{"integerPart", INTEGERPART},
		{"fractionalPart", FRACTIONALPART},
		{"inverse", INVERSE},
		{"reciprocal", RECIPROCAL},

		{"clamp", CLAMP},
		{"lerp", LERP},
		{"unlerp", UNLERP},
		{"proportional", PROPORTIONAL},
		{"slerp", SLERP},
		
		{"min", MIN},
		{"max", MAX},
		
		{"degrees", DEGREES},
		{"radians", RADIANS},
		
		{"step", STEP},
		
		{"cross", CROSS},
		{"dot", DOT},
		{"norm", NORM},
		{"normalize", NORMALIZE},
		{"distance", DISTANCE},
		{"reflect", REFLECT},
		{"refract", REFRACT},
		
		{"||", OR},
		{"&&", AND},
		{"!", NOT},
		
		{"+", ADDITION},
		{"-", SUBTRACTION},
		{"*", MULTIPLICATION},
		{"/", DIVISION},
		
		{"==", EQUAL},
		{"!=", INEQUAL},
		
		{"<", LOWER},
		{"<=", LOWEROREQUAL},
		{">=", GREATEROREQUAL},
		{">", GREATER},
		
		{"#", SIZE},
		{"in", IN},
		{"x", X},
		{"y", Y},
		{"z", Z},
		{"xyz", XYZ},
		
		{"sin", SIN},
		{"cos", COS},
		{"tan", TAN},
		{"asin", ASIN},
		{"acos", ACOS},
		{"atan", ATAN},
		{"exp", EXP},
		{"log", LOG},
		{"pow", POW},
		{"sqrt", SQRT},
		{"random", RANDOM},
		
		{"create", CREATE},
		{"destroy", DESTROY},
		{"add", ADD},
		{"remove", REMOVE},
		{"has", HAS},
		
		{"write", WRITE},
		{"writeError", WRITEERROR},
		{"writeWarning", WRITE_WARNING},
		{"halt", HALT},
		{"breakpoint", BREAKPOINT},
		{"screenshot", SCREENSHOT},
		
		
		{"setNumber", SET_NUMBER},
		{"setColor", SET_COLOR},
		{"setBool", SET_KEYWORD},
		{"setTexture", SET_TEXTURE},
		{"setInteger", SET_INTEGER},
		{"getNumber", GET_NUMBER},
		{"getColor", GET_COLOR},
		{"getBool", GET_KEYWORD},
		{"getTexture", GET_TEXTURE},
		{"getInteger", GET_INTEGER},
		
		{"setTrigger", SET_TRIGGER},
		{"inState", IN_STATE},
		{"enableParameter", ACTIVATE_PARAMETER},
		{"disableParameter", DEACTIVATE_PARAMETER},
		
		{"readTriggered", READ_TRIGGERED},
		{"readNumber", READ_NUMBER},
		{"readVector", READ_VECTOR},
		
		{"quaternion", TO_QUATERNION},
		{"angles", TO_NUMBER3},
		
		{"play", PLAY},
		{"playOnce", PLAY_ONCE},
		{"pause", PAUSE},
		{"unpause", UNPAUSE},
		{"stop", STOP},
		
		{"over", OVER},
		{"string", TO_STRING},
		{"number", TO_NUMBER},
		
		{"overlaps", OVERLAPS},
		{"screenOverlaps", SCREEN_OVERLAPS},
		{"addForce", ADD_FORCE},
		{"addTorque", ADD_TORQUE},
		{"closestPoint", CLOSEST_POINT},
		
		{"isPositive", IS_POSITIVE},
		{"isNegative", IS_NEGATIVE},
		{"isZero", IS_ZERO},
		
		{"worldToViewport", WORLD_TO_VIEWPORT},
		{"viewportToWorld", VIEWPORT_TO_WORLD},
		
		{"=", ASSIGNMENT)},
	Map.ofEntries(
		{"foreach", QUERY},
		{"if", SELECTION},
		{"while", ITERATION},
	), Map.ofEntries(
		{STRING, "string"},
		{PROPOSITION, "proposition"},
		{NUMBER, "number"},
		{NUMBER2, "number2"},
		{NUMBER3, "number3"},
		{UNIT, "unit"},
		
		{COMPONENT, "component"},
		
		{COLOR, "color"},
		
		{INPUT, "input"},
		{AtomicType.MESH, "mesh"},
		{AtomicType.MATERIAL, "material"},
		{AtomicType.FONT, "font"},
		{AtomicType.TEXT, "text"},
		{AtomicType.IMAGE, "image"},
		{AtomicType.AUDIOCLIP, "audioClip"},
		{AtomicType.ANIMATOR, "animator"},
		
		{ENTITY, "entity"},
		{ENTITY_LIST, "entityList"},
		{QUATERNION, "quaternion"},
		{TEXTURE, "texture"},
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
				{"epsilon", EPSILON},
				{"pi", PI},
				{"e", E},
				{"deltaDenbora", DELTA_TIME},
				{"deltaDenboraZehaztua", FIXED_DELTA_TIME},
				{"denbora", TIME_SINCE_START},
				{"denboraEskala", TIME_SCALE},
			),
			Map.ofEntries(
				{"kokapena", POSITION},
				{"biraketa", ROTATION},
				{"eskala", SCALE},
				{"gurasoa", PARENT},
				
				
				{"masa", MASS},
				{"inertzia", INERTIA},
				{"abiadura", VELOCITY},
				{"abiaduraAngeluarra", ANGULAR_VELOCITY},
				{"restituzioa", RESTITUTION},
				{"marruskadura", FRICTION},
				{"kinematikoa", KINEMATIC},
				
				{"gurutzagarria", NO_COLLISION_RESPONSE},
				{"erradioa", RADIUS},
				{"dimentsioak", EXTENTS},
				{"kaixaZentrua", BOX_CENTER},
				{"esferaZentrua", SPHERE_CENTER},
				
				
				{"malla", Component.MESH},
				{"materiala", Component.MATERIAL},
				{"itzalJasotzailea", SHADOW_RECEIVER},
				
				{"gertu", NEAR},
				{"urruti", FAR},
				{"ikusmenAngelua", FOV},
				{"ikusmenTamaina", ORTHOGRAPHIC_SIZE},
				{"irudikatu", CULLING},
				{"atzealdea", BACKGROUND},
				{"viewport", VIEWPORT},
				{"renderizazioEhundura", RENDER_TEXTURE},
				{"pantaila", DISPLAY},
				
				{"emisioa", EMISSION},
				{"fokoAngelua", SPOT_ANGLE},
				{"irismena", RANGE},
				{"intentsitatea", INTENSITY},
				{"erreboteIntentsitatea", INDIRECT_MULTIPLIER},
				{"gaileta", COOKIE},
				
				{"testua", Component.TEXT},
				{"letraEstiloa", Component.FONT},
				{"letraKolorea", TEXT_COLOR},
				{"letraMateriala", TEXT_MATERIAL},
				
				{"irudia", Component.IMAGE},
				{"irudiKolorea", IMAGE_COLOR},
				{"irudiMateriala", IMAGE_MATERIAL},
				
				{"sakatuta", TOGGLED},
				{"slideBalioa", SLIDER_VALUE},
				{"idatzitakoTestua", TEXTFIELD_VALUE},
				
				{"euskarriaMin", ANCHOR_MIN},
				{"euskarriaMax", ANCHOR_MAX},
				
				
				{"audioKlipa", Component.AUDIOCLIP},
				{"bolumena", VOLUME},
				{"tonua", PITCH},
				{"errepikapena", LOOP)},
			Map.ofEntries(
				{"abs", ABS},
				{"sign", SIGN},
				{"ceil", CEIL},
				{"floor", FLOOR},
				{"round", ROUND},
				{"integerPart", INTEGERPART},
				{"fractionalPart", FRACTIONALPART},
				{"inverse", INVERSE},
				{"reciprocal", RECIPROCAL},
				
				{"clamp", CLAMP},
				{"lerp", LERP},
				{"unlerp", UNLERP},
				{"proportional", PROPORTIONAL},
				{"slerp", SLERP},
				
				{"min", MIN},
				{"max", MAX},
				
				{"graduak", DEGREES},
				{"radianak", RADIANS},
				
				{"step", STEP},
				
				{"cross", CROSS},
				{"dot", DOT},
				{"norm", NORM},
				{"normalize", NORMALIZE},
				{"distance", DISTANCE},
				{"reflect", REFLECT},
				{"refract", REFRACT},
				
				{"||", OR},
				{"&&", AND},
				{"!", NOT},
				
				{"+", ADDITION},
				{"-", SUBTRACTION},
				{"*", MULTIPLICATION},
				{"/", DIVISION},
				
				{"==", EQUAL},
				{"!=", INEQUAL},
				
				{"<", LOWER},
				{"<=", LOWEROREQUAL},
				{">=", GREATEROREQUAL},
				{">", GREATER},
				
				{"#", SIZE},
				{"barne", IN},
				{"x", X},
				{"y", Y},
				{"z", Z},
				{"xyz", XYZ},
				
				{"sin", SIN},
				{"cos", COS},
				{"tan", TAN},
				{"asin", ASIN},
				{"acos", ACOS},
				{"atan", ATAN},
				{"exp", EXP},
				{"log", LOG},
				{"pow", POW},
				{"sqrt", SQRT},
				{"ausazko", RANDOM},
				
				{"sortu", CREATE},
				{"desegin", DESTROY},
				{"gehitu", ADD},
				{"kendu", REMOVE},
				{"badu", HAS},
				
				{"idatzi", WRITE},
				{"idatziAkatsa", WRITEERROR},
				{"idatziAholkua", WRITE_WARNING},
				{"amaitu", HALT},
				{"gelditu", BREAKPOINT},
				{"argazkiaAtera", SCREENSHOT},
				
				
				{"ezarriZenbakia", SET_NUMBER},
				{"ezarriKolorea", SET_COLOR},
				{"ezarriOsoa", SET_INTEGER},
				{"ezarriTexture", SET_TEXTURE},
				{"ezarriProposizioa", SET_KEYWORD},
				{"hartuZenbakia", GET_NUMBER},
				{"hartuKolorea", GET_COLOR},
				{"hartuOsoa", GET_INTEGER},
				{"hartuTexture", GET_TEXTURE},
				{"hartuProposizioa", GET_KEYWORD},
				
				{"ezarriKakoa", SET_TRIGGER},
				{"egoera", IN_STATE},
				{"jarriParametroa", ACTIVATE_PARAMETER},
				{"kenduParametroa", DEACTIVATE_PARAMETER},
				
				{"irakurriKakoa", READ_TRIGGERED},
				{"irakurriZenbakia", READ_NUMBER},
				{"irakurriBektorea", READ_VECTOR},
				
				{"kuaternioia", TO_QUATERNION},
				{"angeluak", TO_NUMBER3},
				
				{"hasi", PLAY},
				{"playOnce", PLAY_ONCE},
				{"pause", PAUSE},
				{"unpause", UNPAUSE},
				{"stop", STOP},
				
				{"saguaGainean", OVER},
				{"zenbakia", TO_NUMBER},
				{"katea", TO_STRING},
				
				{"gainean", OVERLAPS},
				{"pantailaGainean", SCREEN_OVERLAPS},
				{"gehituIndarra", ADD_FORCE},
				{"gehituIndarAngeluarra", ADD_TORQUE},
				{"puntuGertukoena", CLOSEST_POINT},
				
				{"positiboa", IS_POSITIVE},
				{"negatiboa", IS_NEGATIVE},
				{"zero", IS_ZERO},
				
				{"mundutikPantailara", WORLD_TO_VIEWPORT},
				{"pantailatikMundura", VIEWPORT_TO_WORLD},
				
				{"=", ASSIGNMENT)},
			Map.ofEntries(
				{"hartu", QUERY},
				{"baldin", SELECTION},
				{"bitartean", ITERATION},
			), Map.ofEntries(
					{STRING, "katea"},
					{PROPOSITION, "proposizioa"},
					{NUMBER, "zenbakia"},
					{NUMBER2, "zenbakia2"},
					{NUMBER3, "zenbakia3"},
					{UNIT, "hutsa"},
					
					{COMPONENT, "komponentea"},
					
					{COLOR, "kolorea"},
					
					{INPUT, "inputa"},
					{AtomicType.MESH, "malla"},
					{AtomicType.MATERIAL, "materiala"},
					{AtomicType.FONT, "letraEstiloa"},
					{AtomicType.TEXT, "testua"},
					{AtomicType.IMAGE, "irudia"},
					{AtomicType.AUDIOCLIP, "audioKlipa"},
					{AtomicType.ANIMATOR, "animatzailea"},
					
					{ENTITY, "entitatea"},
					{ENTITY_LIST, "entitateLista"},
					{QUATERNION, "kuaternioia"},
					{TEXTURE, "textura"},
			), Map.of(
				RedefinedSymbol.class, "Simbolo hau dagoeneko definitua dago",
				UndefinedSymbol.class, "Simbolo honek ez du definiziorik",
				IncompatibleTypes.class, "Izaera bateraezinak",
				UndecidableType.class, "Izaera ezarriezina",
				UnusedValue.class, "Erabilerarik gabeko balioa",
				ReadOnly.class, "Ezin konstante baten balioa berrezarri"
			))*/
	;

	BiMap<String, Value> variables;
	BiMap<String, Component> components;
	BiMap<String, Function> functions;
	BiMap<String, Block> blocks;
	
	BiMap<String, Type> atomicTypes;
	BiMap<String, Class<? extends Problem>> problems;
	
	Library(Object[][] variables, Object[][] components, Object[][] functions, Object[][] blocks, Object[][] atomicTypes, Object[][] problems)
	{
		this.variables = HashBiMap.create(Stream.of(variables).collect(Collectors.toMap(x->(String)x[0], x->(Value)x[1])));
		this.components = HashBiMap.create(Stream.of(components).collect(Collectors.toMap(x->(String)x[0], x->(Component)x[1])));
		this.functions = HashBiMap.create(Stream.of(functions).collect(Collectors.toMap(x->(String)x[0], x->(Function)x[1])));
		this.blocks = HashBiMap.create(Stream.of(blocks).collect(Collectors.toMap(x->(String)x[0], x->(Block)x[1])));
		this.atomicTypes = HashBiMap.create(Stream.of(atomicTypes).collect(Collectors.toMap(x->(String)x[0], x->(AtomicType)x[1])));
		this.problems = HashBiMap.create(Stream.of(problems).collect(Collectors.toMap(x->(String)x[0], x->(Class<? extends Problem>)x[1])));
	}
	
	Map<String, Value> valueInverse;
	
	Library(java.util.function.Function<Value, String> f, java.util.function.Function<Component, String> ff)
	{
		valueInverse = new HashMap<>();
		for (var v : Value.values())
		{
			var mapped = f.apply(v);
			
			if (valueInverse.containsKey(mapped))
			{
				System.err.println("Value already present: "+mapped);
			}
			else
			{
				valueInverse.put(mapped, v);
			}
		}
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
		return problems.inverse().get(problem);
	}
	
	public String name(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomicType = (AtomicType) type;
			
			return atomicTypes.inverse().get(atomicType);
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
