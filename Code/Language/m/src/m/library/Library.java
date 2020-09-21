package m.library;

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
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.ReadOnly;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.problems.errors.UndecidableType;
import m.validation.problems.errors.UndefinedSymbol;
import m.validation.problems.warnings.UnusedValue;

public enum Library
{
	
	ENGLISH
	(
	value -> {switch(value)
	{
		case EPSILON: return "epsilon";
		case DELTA_TIME: return "deltaTime";
		case E: return "e";
		case FIXED_DELTA_TIME: return "fixedDeltaTime";
		case PI: return "pi";
		case TIME_SCALE: return "timeScale";
		case TIME_SINCE_START: return "timeSinceStart";
	} return "";},
	component -> {switch(component)
	{
		case POSITION: return "position";
		case ROTATION: return "rotation";
		case SCALE: return "scale";
		case PARENT: return "parent";
		
		case MASS: return "mass";
		case INERTIA: return "inertia";
		
		case VELOCITY: return "velocity";
		case ANGULAR_VELOCITY: return "angularVelocity";
		case RESTITUTION: return "restitution";
		case FRICTION: return "friction";
		case KINEMATIC: return "kinematic";
		
		case NO_COLLISION_RESPONSE: return "crossable";
		case RADIUS: return "radius";
		case EXTENTS: return "extents";
		case BOX_CENTER: return "boxCenter";
		case SPHERE_CENTER: return "sphereCenter";
		
		case MESH: return "mesh";
		case MATERIAL: return "material";
		case SHADOW_RECEIVER: return "shadowReceiver";
		
		case NEAR: return "near";
		case FAR: return "far";
		case FOV: return "fieldOfView";
		case ORTHOGRAPHIC_SIZE: return "orthographicSize";
		case BACKGROUND: return "background";
		case CULLING: return "culling";
		case VIEWPORT: return "viewport";
		case RENDER_TEXTURE: return "renderTexture";
		case DISPLAY: return "display";
		
		case EMISSION: return "emission";
		case SPOT_ANGLE: return "spotAngle";
		case RANGE: return "range";
		case INTENSITY: return "intensity";
		case INDIRECT_MULTIPLIER: return "bounceIntensity";
		case COOKIE: return "cookie";
		
		case TEXT: return "text";
		case FONT: return "font";
		case TEXT_COLOR: return "textColor";
		case TEXT_MATERIAL: return "textMaterial";
		
		case IMAGE: return "image";
		case IMAGE_COLOR: return "imageColor";
		case IMAGE_MATERIAL: return "imageMaterial";
		
		case TOGGLED: return "toggled";
		case SLIDER_VALUE: return "sliderValue";
		case TEXTFIELD_VALUE: return "writtenText";
		
		case ANCHOR_MIN: return "anchorMin";
		case ANCHOR_MAX: return "anchorMax";
		
		
		case AUDIOCLIP: return "audioClip";
		case VOLUME: return "volume";
		case PITCH: return "pitch";
		case LOOP: return "loop";
	} return "";},
	function -> {switch(function)
	{
	case ABS: return "abs";
	case SIGN: return "sign";
	case CEIL: return "ceil";
	case FLOOR: return "floor";
	case ROUND: return "round";
	case INTEGERPART: return "integerPart";
	case FRACTIONALPART: return "fractionalPart";
	case INVERSE: return "inverse";
	case RECIPROCAL: return "reciprocal";

	case CLAMP: return "clamp";
	case LERP: return "lerp";
	case UNLERP: return "unlerp";
	case PROPORTIONAL: return "proportional";
	case SLERP: return "slerp";
	
	case MIN: return "min";
	case MAX: return "max";
	
	case DEGREES: return "degrees";
	case RADIANS: return "radians";
	
	case STEP: return "step";
	
	case CROSS: return "cross";
	case DOT: return "dot";
	case NORM: return "norm";
	case NORMALIZE: return "normalize";
	case DISTANCE: return "distance";
	case REFLECT: return "reflect";
	case REFRACT: return "refract";
	
	case OR: return "||";
	case AND: return "&&";
	case NOT: return "!";
	
	case ADDITION: return "+";
	case SUBTRACTION: return "-";
	case MULTIPLICATION: return "*";
	case DIVISION: return "/";
	
	case EQUAL: return "==";
	case INEQUAL: return "!=";
	
	case LOWER: return "<";
	case LOWEROREQUAL: return "<=";
	case GREATEROREQUAL: return ">=";
	case GREATER: return ">";
	
	case SIZE: return "#";
	case IN: return "@";
	case X: return "x";
	case Y: return "y";
	case Z: return "z";
	case XYZ: return "xyz";
	
	case SIN: return "sin";
	case COS: return "cos";
	case TAN: return "tan";
	case ASIN: return "asin";
	case ACOS: return "acos";
	case ATAN: return "atan";
	case EXP: return "exp";
	case LOG: return "log";
	case POW: return "pow";
	case SQRT: return "sqrt";
	case RANDOM: return "random";
	
	case CREATE: return "create";
	case DESTROY: return "destroy";
	case ADD: return "add";
	case REMOVE: return "remove";
	case HAS: return "has";
	
	case WRITE: return "write";
	case WRITEERROR: return "writeError";
	case WRITE_WARNING: return "writeWarning";
	case HALT: return "halt";
	case BREAKPOINT: return "breakpoint";
	case SCREENSHOT: return "screenshot";
	
	
	case SET_NUMBER: return "setNumber";
	case SET_COLOR: return "setColor";
	case SET_KEYWORD: return "setBool";
	case SET_TEXTURE: return "setTexture";
	case SET_INTEGER: return "setInteger";
	case GET_NUMBER: return "getNumber";
	case GET_COLOR: return "getColor";
	case GET_KEYWORD: return "getBool";
	case GET_TEXTURE: return "getTexture";
	case GET_INTEGER: return "getInteger";
	
	case SET_TRIGGER: return "setTrigger";
	case IN_STATE: return "inState";
	case ACTIVATE_PARAMETER: return "enableParameter";
	case DEACTIVATE_PARAMETER: return "disableParameter";
	case PLAY_ANIMATION: return "playAnimation";
	
	case READ_TRIGGERED: return "readTriggered";
	case READ_NUMBER: return "readNumber";
	case READ_VECTOR: return "readVector";
	
	case TO_QUATERNION: return "quaternion";
	case TO_NUMBER3: return "angles";
	
	case PLAY: return "play";
	case PLAY_ONCE: return "playOnce";
	case PAUSE: return "pause";
	case UNPAUSE: return "unpause";
	case STOP: return "stop";
	
	case OVER: return "over";
	case TO_STRING: return "string";
	case TO_NUMBER: return "number";
	
	case OVERLAPS: return "overlaps";
	case SCREEN_OVERLAPS: return "screenOverlaps";
	case ADD_FORCE: return "addForce";
	case ADD_TORQUE: return "addTorque";
	case CLOSEST_POINT: return "closestPoint";
	
	case IS_POSITIVE: return "isPositive";
	case IS_NEGATIVE: return "isNegative";
	case IS_ZERO: return "isZero";
	
	case WORLD_TO_VIEWPORT: return "worldToViewport";
	case VIEWPORT_TO_WORLD: return "viewportToWorld";
	
	case ASSIGNMENT: return "=";
	} return "";}
	,
	block -> {switch(block)
	{
	case SELECTION: return "if";
	case ITERATION: return "while";
	case QUERY: return "foreach";
	} return "";},
	type -> {switch(type)
	{
	case STRING: return "string";
	case PROPOSITION: return "proposition";
	case NUMBER: return "number";
	case NUMBER2: return "number2";
	case NUMBER3: return "number3";
	case UNIT: return "unit";
	
	case COMPONENT: return "component";
	
	case COLOR: return "color";
	
	case INPUT: return "input";
	case MESH: return "mesh";
	case MATERIAL: return "material";
	case FONT: return "font";
	case TEXT: return "text";
	case IMAGE: return "image";
	case AUDIOCLIP: return "audioClip";
	case ANIMATOR: return "animator";
	
	case ENTITY: return "entity";
	case ENTITY_LIST: return "entityList";
	case QUATERNION: return "quaternion";
	case TEXTURE: return "texture";
	case COLLIDER: return "collider";
	case RECT: return "rectangle";
	} return "";},
	problem ->
	{
		if (problem == IncompatibleTypes.class)
		{
			return "Incompatible types";
		}
		else if (problem == UndecidableType.class)
		{
			return "Undecidable type";
		}
		else if (problem == RedefinedSymbol.class)
		{
			return "Redefined symbol";
		}
		else if (problem == UndefinedSymbol.class)
		{
			return "Undefined symbol";
		}
		else if (problem == ReadOnly.class)
		{
			return "Read only value";
		}
		else if (problem == UnusedValue.class)
		{
			return "Unused value";
		}
		else
		{
			return "Undefined name for problem";
		}
	},
	function ->	{ switch(function)
	{
		case ABS: return "Absolute value of a number";
		case ACOS: return "Arc cosine of a number";
		case ACTIVATE_PARAMETER: return "Enable the parameter in the animator";
		case ADD: return "Add component to an entity";
		case ADDITION: return "Adds two numbers or vectors";
		case ADD_FORCE: return "Add force to the entity's rigidbody";
		case ADD_TORQUE: return "Add torque to the entity's rigidbody";
		case AND: return "Logical and of two propositions";
		case ASIN: return "Arc sine of a number";
		case ASSIGNMENT: return "Assigns the expression to the atom";
		case ATAN: return "Arc tangent of a number";
		case BREAKPOINT: return "Pause execution when reached this point";
		case CEIL: return "The closest integer greater or equal to the given number";
		case CLAMP: return "Clamp the number if it exceeds the low and high limits";
		case CLOSEST_POINT: return "Closest point of another collider from the entity's colliders";
		case COS: return "Cosine of the angle";
		case CREATE: return "Create a copy of the entity";
		case CROSS: return "Cross product of the two vectors";
		case DEACTIVATE_PARAMETER: return "Disable the parameter in the animator";
		case DEGREES: return "Convert from radians to degrees";
		case DESTROY: return "Destroy the entity";
		case DISTANCE: return "Distance between the two points";
		case DIVISION: return "Division of the two numbers";
		case DOT: return "Dot product of the two vectors";
		case EQUAL: return "True if both values are equal";
		case EXP: return "e raised to the number";
		case FLOOR: return "Highest integer lower or equal to the number";
		case FRACTIONALPART: return "Fractional part of the number";
		case GET_COLOR: return "Value of the property of the material of the entity";
		case GET_INTEGER: return "Value of the property of the material of the entity";
		case GET_KEYWORD: return "Value of the property of the material of the entity";
		case GET_NUMBER: return "Value of the property of the material of the entity";
		case GET_TEXTURE: return "Value of the property of the material of the entity";
		case GREATER: return "True if the first number is higher than the second";
		case GREATEROREQUAL: return "True if the first number is higher or equal to the second";
		case HALT: return "Stop the execution";
		case HAS: return "True if the entity has the component";
		case IN: return "True if the entity belongs to the entity list";
		case INEQUAL: return "True if the values are not equal";
		case INTEGERPART: return "Integer part of the number";
		case INVERSE: return "Multiplicative inverse of the number";
		case IN_STATE: return "True if the animator of the entity is in the state";
		case IS_NEGATIVE: return "True if the number is lower than zero";
		case IS_POSITIVE: return "True if the number is higher than zero";
		case IS_ZERO: return "True if the number is zero";
		case LERP: return "Linearly interpolate the number between the extents";
		case LOG: return "Natural logarithm of the number";
		case LOWER: return "True if the first number is lower than the second";
		case LOWEROREQUAL: return "True if the first number is lower or equal to the second";
		case MAX: return "Maximum of the two numbers";
		case MIN: return "Minimum of the two numbers";
		case MULTIPLICATION: return "Multiplication of the two numbers or the vector and the number";
		case NORM: return "Length of the vector";
		case NORMALIZE: return "Normalized version of the vector";
		case NOT: return "Negation of the proposition";
		case OR: return "Inclusive disjunction of the propositions";
		case OVER: return "True if the point is over the entity's colliders";
		case OVERLAPS: return "List of all the entities overlapping the entity's colliders";
		case PAUSE: return "Pause the audio";
		case PLAY: return "Play the audio";
		case PLAY_ONCE: return "Play the audio once";
		case PLAY_ANIMATION: return "Play the animation state";
		case POW: return "The first number raised to the second";
		case PROPORTIONAL: return "The proportional value of the number when shifted from the original extents to the destination extents";
		case RADIANS: return "Convert the degrees to radians";
		case RANDOM: return "A random number between the minimum and the maximum numbers";
		case READ_NUMBER: return "Numerical value of the input device";
		case READ_TRIGGERED: return "True if the input device has been triggered this frame";
		case READ_VECTOR: return "Vector value of the input device";
		case RECIPROCAL: return "Additive inverse of the number";
		case REFLECT: return "Reflection of the vector";
		case REFRACT: return "Refraction of the vector when changes to the new environment";
		case REMOVE: return "Remove the component from the entity";
		case ROUND: return "The number rounded up or down";
		case SCREENSHOT: return "Take a screenshot";
		case SCREEN_OVERLAPS: return "All the entities whose colliders are overlaping the entity's colliders";
		case SET_COLOR: return "Set the property of the material of the entity";
		case SET_INTEGER: return "Set the property of the material of the entity";
		case SET_KEYWORD: return "Set the property of the material of the entity";
		case SET_NUMBER: return "Set the property of the material of the entity";
		case SET_TEXTURE: return "Set the property of the material of the entity";
		case SET_TRIGGER: return "Set the property of the material of the entity";
		case SIGN: return "1 if the number is positive or zero, -1 otherwise";
		case SIN: return "Sine of the angle";
		case SIZE: return "Size of the list of entities";
		case SLERP: return "Spherically interpolate between the two quaternions";
		case SQRT: return "Square root of the number";
		case STEP: return "Value of the step function at the number";
		case STOP: return "Stop the audio";
		case SUBTRACTION: return "Subtraction of the two numbers or vectors";
		case TAN: return "Tangent of the angle";
		case TO_NUMBER: return "Convert the string to a number";
		case TO_NUMBER3: return "Convert the quaternion to a 3D vector";
		case TO_QUATERNION: return "Convert the 3D vector to a quaternion";
		case TO_STRING: return "Convert the number to a string";
		case UNLERP: return "Inverse of interpolating the number between the extents";
		case UNPAUSE: return "Unpause the audio";
		case VIEWPORT_TO_WORLD: return "Convert the viewport coordinates to world coordinates";
		case WORLD_TO_VIEWPORT: return "Convert world coordinates to viewport coordinates";
		case WRITE: return "Write a log message with the value";
		case WRITEERROR: return "Write an error message with the value";
		case WRITE_WARNING: return "Write a warning message with the value";
		case X: return "First entry of the vector";
		case XYZ: return "3D vector with the three numbers as entries";
		case Y: return "Second entry of the vector";
		case Z: return "Third entry of the vector";
	}
	return "";}
	),
	EUSKARA(
	value -> {switch(value)
	{
	case EPSILON: return "epsilon";
	case PI: return "pi";
	case E: return "e";
	case DELTA_TIME: return "deltaDenbora";
	case FIXED_DELTA_TIME: return "deltaDenboraZehaztua";
	case TIME_SINCE_START: return "denbora";
	case TIME_SCALE: return "denboraEskala";
	} return "";},
	component -> {switch(component)
	{
	case POSITION: return "kokapena";
	case ROTATION: return "biraketa";
	case SCALE: return "eskala";
	case PARENT: return "gurasoa";
	
	
	case MASS: return "masa";
	case INERTIA: return "inertzia";
	case VELOCITY: return "abiadura";
	case ANGULAR_VELOCITY: return "abiaduraAngeluarra";
	case RESTITUTION: return "restituzioa";
	case FRICTION: return "marruskadura";
	case KINEMATIC: return "kinematikoa";
	
	case NO_COLLISION_RESPONSE: return "gurutzagarria";
	case RADIUS: return "erradioa";
	case EXTENTS: return "dimentsioak";
	case BOX_CENTER: return "kaixaZentrua";
	case SPHERE_CENTER: return "esferaZentrua";
	
	
	case MESH: return "malla";
	case MATERIAL: return "materiala";
	case SHADOW_RECEIVER: return "itzalJasotzailea";
	
	case NEAR: return "gertu";
	case FAR: return "urruti";
	case FOV: return "ikusmenAngelua";
	case ORTHOGRAPHIC_SIZE: return "ikusmenTamaina";
	case CULLING: return "irudikatu";
	case BACKGROUND: return "atzealdea";
	case VIEWPORT: return "viewport";
	case RENDER_TEXTURE: return "renderizazioEhundura";
	case DISPLAY: return "pantaila";
	
	case EMISSION: return "emisioa";
	case SPOT_ANGLE: return "fokoAngelua";
	case RANGE: return "irismena";
	case INTENSITY: return "intentsitatea";
	case INDIRECT_MULTIPLIER: return "erreboteIntentsitatea";
	case COOKIE: return "gaileta";
	
	case TEXT: return "testua";
	case FONT: return "letraEstiloa";
	case TEXT_COLOR: return "letraKolorea";
	case TEXT_MATERIAL: return "letraMateriala";
	
	case IMAGE: return "irudia";
	case IMAGE_COLOR: return "irudiKolorea";
	case IMAGE_MATERIAL: return "irudiMateriala";
	
	case TOGGLED: return "sakatuta";
	case SLIDER_VALUE: return "slideBalioa";
	case TEXTFIELD_VALUE: return "idatzitakoTestua";
	
	case ANCHOR_MIN: return "euskarriaMin";
	case ANCHOR_MAX: return "euskarriaMax";
	
	
	case AUDIOCLIP: return "audioKlipa";
	case VOLUME: return "bolumena";
	case PITCH: return "tonua";
	case LOOP: return "errepikapena";
	} return "";},
	function -> {switch(function)
	{
	case ABS: return "abs";
	case SIGN: return "sign";
	case CEIL: return "ceil";
	case FLOOR: return "floor";
	case ROUND: return "round";
	case INTEGERPART: return "integerPart";
	case FRACTIONALPART: return "fractionalPart";
	case INVERSE: return "inverse";
	case RECIPROCAL: return "reciprocal";
	
	case CLAMP: return "clamp";
	case LERP: return "lerp";
	case UNLERP: return "unlerp";
	case PROPORTIONAL: return "proportional";
	case SLERP: return "slerp";
	
	case MIN: return "min";
	case MAX: return "max";
	
	case DEGREES: return "graduak";
	case RADIANS: return "radianak";
	
	case STEP: return "step";
	
	case CROSS: return "cross";
	case DOT: return "dot";
	case NORM: return "norm";
	case NORMALIZE: return "normalize";
	case DISTANCE: return "distance";
	case REFLECT: return "reflect";
	case REFRACT: return "refract";
	
	case OR: return "||";
	case AND: return "&&";
	case NOT: return "!";
	
	case ADDITION: return "+";
	case SUBTRACTION: return "-";
	case MULTIPLICATION: return "*";
	case DIVISION: return "/";
	
	case EQUAL: return "==";
	case INEQUAL: return "!=";
	
	case LOWER: return "<";
	case LOWEROREQUAL: return "<=";
	case GREATEROREQUAL: return ">=";
	case GREATER: return ">";
	
	case SIZE: return "#";
	case IN: return "@";
	case X: return "x";
	case Y: return "y";
	case Z: return "z";
	case XYZ: return "xyz";
	
	case SIN: return "sin";
	case COS: return "cos";
	case TAN: return "tan";
	case ASIN: return "asin";
	case ACOS: return "acos";
	case ATAN: return "atan";
	case EXP: return "exp";
	case LOG: return "log";
	case POW: return "pow";
	case SQRT: return "sqrt";
	case RANDOM: return "ausazko";
	
	case CREATE: return "sortu";
	case DESTROY: return "desegin";
	case ADD: return "gehitu";
	case REMOVE: return "kendu";
	case HAS: return "badu";
	
	case WRITE: return "idatzi";
	case WRITEERROR: return "idatziAkatsa";
	case WRITE_WARNING: return "idatziAholkua";
	case HALT: return "amaitu";
	case BREAKPOINT: return "gelditu";
	case SCREENSHOT: return "argazkiaAtera";
	
	
	case SET_NUMBER: return "ezarriZenbakia";
	case SET_COLOR: return "ezarriKolorea";
	case SET_INTEGER: return "ezarriOsoa";
	case SET_TEXTURE: return "ezarriTexture";
	case SET_KEYWORD: return "ezarriProposizioa";
	case GET_NUMBER: return "hartuZenbakia";
	case GET_COLOR: return "hartuKolorea";
	case GET_INTEGER: return "hartuOsoa";
	case GET_TEXTURE: return "hartuTexture";
	case GET_KEYWORD: return "hartuProposizioa";
	
	case SET_TRIGGER: return "ezarriKakoa";
	case IN_STATE: return "egoera";
	case ACTIVATE_PARAMETER: return "jarriParametroa";
	case DEACTIVATE_PARAMETER: return "kenduParametroa";
	case PLAY_ANIMATION: return "aldatuAnimazioa";
	
	case READ_TRIGGERED: return "irakurriKakoa";
	case READ_NUMBER: return "irakurriZenbakia";
	case READ_VECTOR: return "irakurriBektorea";
	
	case TO_QUATERNION: return "kuaternioia";
	case TO_NUMBER3: return "angeluak";
	
	case PLAY: return "hasi";
	case PLAY_ONCE: return "playOnce";
	case PAUSE: return "pause";
	case UNPAUSE: return "unpause";
	case STOP: return "stop";
	
	case OVER: return "saguaGainean";
	case TO_NUMBER: return "zenbakia";
	case TO_STRING: return "katea";
	
	case OVERLAPS: return "gainean";
	case SCREEN_OVERLAPS: return "pantailaGainean";
	case ADD_FORCE: return "gehituIndarra";
	case ADD_TORQUE: return "gehituIndarAngeluarra";
	case CLOSEST_POINT: return "puntuGertukoena";
	
	case IS_POSITIVE: return "positiboa";
	case IS_NEGATIVE: return "negatiboa";
	case IS_ZERO: return "zero";
	
	case WORLD_TO_VIEWPORT: return "mundutikPantailara";
	case VIEWPORT_TO_WORLD: return "pantailatikMundura";
	
	case ASSIGNMENT: return "=";
	} return "";},
	block -> {switch(block)
	{
	case QUERY: return "hartu";
	case SELECTION: return "baldin";
	case ITERATION: return "bitartean";
	} return "";},
	type -> {switch(type)
	{
	case STRING: return "katea";
	case PROPOSITION: return "proposizioa";
	case NUMBER: return "zenbakia";
	case NUMBER2: return "zenbakia2";
	case NUMBER3: return "zenbakia3";
	case UNIT: return "hutsa";
	
	case COMPONENT: return "komponentea";
	
	case COLOR: return "kolorea";
	
	case INPUT: return "inputa";
	case MESH: return "malla";
	case MATERIAL: return "materiala";
	case FONT: return "letraEstiloa";
	case TEXT: return "testua";
	case IMAGE: return "irudia";
	case AUDIOCLIP: return "audioKlipa";
	case ANIMATOR: return "animatzailea";
	
	case ENTITY: return "entitatea";
	case ENTITY_LIST: return "entitateLista";
	case QUATERNION: return "kuaternioia";
	case TEXTURE: return "textura";
	case COLLIDER: return "collider";
	case RECT: return "rektangulua";
	} return "";},
	problem ->
	{
		if (problem == IncompatibleTypes.class)
		{
			return "Izaera bateraezinak";
		}
		else if (problem == UndecidableType.class)
		{
			return "Izaera ezarriezina";
		}
		else if (problem == RedefinedSymbol.class)
		{
			return "Simbolo hau dagoeneko definitua dago";
		}
		else if (problem == UndefinedSymbol.class)
		{
			return "Simbolo honek ez du definiziorik";
		}
		else if (problem == ReadOnly.class)
		{
			return "Ezin konstante baten balioa berrezarri";
		}
		else if (problem == UnusedValue.class)
		{
			return "Erabilerarik gabeko balioa";
		}
		else
		{
			return "Problema honen izena ezarri gabe dago";
		}
	},
	function ->
	{
		return "";
	}
	);
	
	Map<Value, String> valueToName;
	Map<Component, String> componentToName;
	Map<Function, String> functionToName;
	Map<Block, String> blockToName;
	Map<AtomicType, String> typeToName;
	Map<Class<?>, String> problemToName;
	Map<Function, String> functionToDescription;
	
	Map<String, Value> nameToValue;
	Map<String, Component> nameToComponent;
	Map<String, Function> nameToFunction;
	Map<String, Block> nameToBlock;
	Map<String, AtomicType> nameToType;
	Map<String, Class<?>> nameToProblem;
	
	Library(java.util.function.Function<Value, String> values, java.util.function.Function<Component, String> components, java.util.function.Function<Function, String> functions, java.util.function.Function<Block,String> blocks, java.util.function.Function<AtomicType, String> atomicTypes, java.util.function.Function<Class<?>, String> problems,java.util.function.Function<Function, String> functionDescriptions)
	{
		valueToName = forward(values, Value.values());
		nameToValue = reverse(values, Value.values());
		
		componentToName = forward(components, Component.values());
		nameToComponent = reverse(components, Component.values());

		functionToName = forward(functions, Function.values());
		nameToFunction = reverse(functions, Function.values());

		blockToName = forward(blocks, Block.values());
		nameToBlock = reverse(blocks, Block.values());

		typeToName = forward(atomicTypes, AtomicType.values());
		nameToType = reverse(atomicTypes, AtomicType.values());
		
		var problemTypes = new Class[] {UndecidableType.class, IncompatibleTypes.class, UndefinedSymbol.class, RedefinedSymbol.class, ReadOnly.class, UnusedValue.class};
		
		problemToName = forward(problems, problemTypes);
		nameToProblem = reverse(problems, problemTypes);

		functionToDescription = forward(functionDescriptions, Function.values());
	}
	
	private <A,B> HashMap<A,B> forward(java.util.function.Function<A,B> f, A[] values)
	{
		var map = new HashMap<A,B>();
		
		for (var value : values)
		{
			map.put(value, f.apply(value));
		}
		
		return map;
	}
	
	private <A,B> HashMap<B,A> reverse(java.util.function.Function<A,B> f, A[] values)
	{
		var map = new HashMap<B,A>();
		
		for (var value : values)
		{
			var mapped = f.apply(value);
			
			if (map.containsKey(mapped))
			{
				// Error
			}
			else
			{
				map.put(mapped, value);
			}
		}
		
		return map;
	}
	public Value getValue(String name) {
		return nameToValue.get(name);
	}
	
	public Component getComponent(String name) {
		return nameToComponent.get(name);
	}
	
	public Function getFunction(String name) {
		return nameToFunction.get(name);
	}
	
	public Block getBlock(String name) {
		return nameToBlock.get(name);
	}
	
	public String getProblem(Class<? extends Problem> problem)
	{
		return problemToName.get(problem);
	}

	public String getDescription(Function function)
	{
		return functionToDescription.get(function);
	}

	public String getName(Component component)
	{
		return componentToName.get(component);
	}

	public String getName(Function function)
	{
		return functionToName.get(function);
	}

	public String getName(Value value)
	{
		return valueToName.get(value);
	}
	
	public String name(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomicType = (AtomicType) type;
			
			return typeToName.get(atomicType);
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
			return String.join(" × ", result) + " -> " + name(functionType.getReturnType());
		}
		else
		{
			return "unrecognized type";
		}
	}
}
