package m.generator;

import static m.generator.Writer.*;
import static m.library.symbols.Block.*;
import static m.library.types.AtomicType.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.Type;
import m.m.Application;
import m.m.Assignment;
import m.m.Binary;
import m.m.BindingBlock;
import m.m.Block;
import m.m.Cell;
import m.m.Delegation;
import m.m.Expression;
import m.m.Function;
import m.m.Statement;
import m.m.Unary;
import m.m.Value;

public class ClassicUnity
{
	IFileSystemAccess2 fileSystem;
	Library library;
	Game game;
	HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries;
	
	Set<String> namespaces = new HashSet<>();
	Function currentFunction;
	
	String[] csharpReserved = new String[] {"base", "class", "struct", "if", "else", "for", "while", "foreach"};
	
	private static final String UNITY_ENGINE = "UnityEngine";
	private static final String UNITY_ENGINE_UI = "UnityEngine.UI";
	public void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.game = game;
		this.library = game.library;
		this.fileSystem = fileSystem;
		this.queries = game.queries;
		
		for (var component : game.components.entrySet())
		{
			fileSystem.generateFile("UnityClassic/Assets/Code/Components/"+simpleComponent(component.getKey())+".cs",
					generate(component.getKey(), component.getValue()));
		}
		var systems = lines("",
		"using UnityEngine;",
		"using UnityEngine.UI;",
		"using UnityEngine.InputSystem;",
		"using System.Collections.Generic;",
		"using Unity.Mathematics;",
		"using System;",
		"using System.Linq;",
		"",
		"namespace M",
		"{",
		"   public class Systems : MonoBehaviour",
		"   {",
		"      "+all(game.functions.keySet(), x->"public bool "+x.getName()+" = true;", "\n      "));		
		for (var function : game.functions.keySet())
		{
			for (var query : game.queries.get(function).keySet())
			{
				systems += lines("",
					"[Tooltip(\""+all(game.queries.get(function).get(query).keySet(), x->simpleComponent(x), ", ")+"\")]",
					"public List<GameObject> " + function.getName() + "_" + query + ";\n");
			}
		}

		systems += "void FixedUpdate()\n{\n";
		
		for (var function : game.functions.keySet())
		{
			for (var query : game.queries.get(function).keySet())
			{
				systems += function.getName() + "_" + query + " = new List<GameObject>();\n";
			}
		}
		
		for (var function : game.functions.entrySet())
		{
			var type = function.getValue();
			if (type.getParameters().length == 0 && type.getReturnType() == UNIT)
			{
				currentFunction = function.getKey();
				systems += "if ("+function.getKey().getName()+")\n{"+generate(function.getKey())+"\n}";
			}
		}
		
		systems += "}\n}\n}\n";
		
		fileSystem.generateFile("UnityClassic/Assets/Code/Systems/Systems.cs", systems);
	}
	
	
	
	private String generate(String name, Type type)
	{
		namespaces.clear();
		namespaces.add(UNITY_ENGINE);
		
		var field = type != UNIT? "public "+ unity(type) + " Value;" : "";
		var extra = "";
		if (type == INPUT)
		{
			extra = "void Start() {Value.Enable();}";
		}
		return
				lines("",
				all(namespaces,x->"using "+x+";", "\n"),
				"",
				"namespace M",
				"{",
				"    public class "+simpleComponent(name)+" : MonoBehaviour",
				"    {",
				"        "+field,
				"        "+extra,
				"    }",
				"}"
				);
	}
	
	
	
	private String generate(Function function)
	{
		namespaces.clear();
		namespaces.add(UNITY_ENGINE);
		
		var statements = all(function.getStatements(), x->code(x), "\n			");
		
		return statements;
	}
	
	private String code(Statement statement)
	{
		if (statement instanceof BindingBlock)
		{
			var block = (BindingBlock) statement;
			var name = block.getName();
			if (library.getBlock(name) == QUERY)
			{
				var a = ((Value)block.getExpression()).getName();
				var query = queries.get(currentFunction).get(a);
				
				return lines("			",
				"var transforms_"+a+" = FindObjectsOfType<Transform>();",
				"foreach (var "+a+" in transforms_"+a+")",
				"{",
				"	"+all(query.keySet(), x->"var "+x+"_"+a+" = "+a+".GetComponent<"+component(x)+">();", "\n				"),
				"	if ("+all(query.keySet(), x->x+"_"+a+" != null ", " && ")+"){",
					"",
				"   "+currentFunction.getName()+"_"+a+".Add("+a+".gameObject);",
				"	"+all(block.getStatements(), x->code(x), "\n				"),
				"   }",
				"}");
			}
			
			return "undefined";
		}
		else if (statement instanceof Block)
		{
			var block = (Block) statement;
			var name = block.getName();
			
			if (library.getBlock(name) == SELECTION)
			{
				var condition = code(block.getExpression());
				return 
					"\n"+
					"if ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			else if (library.getBlock(name) == ITERATION)
			{
				var condition = code(block.getExpression());
				return 
					"\n"+
					"while ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();
			
			if (atom instanceof Value)
			{
				return "var "+code(atom)+" = "+code(expression)+";";
			}
			else if (atom instanceof Cell)
			{
				return code(atom)+" = "+code(expression)+";";
			}
		}
		else if (statement instanceof Delegation)
		{
			return code(((Delegation) statement).getApplication())+";";
		}
		return "undefined";
	}
	
	private String code(Expression e)
	{
		if (e instanceof Binary)
		{
			var binary = (Binary) e;
			var list = new ArrayList<Expression>();
			list.add(binary.getLeft());
			list.add(binary.getRight());
			return application(library.getFunction(binary.getOperator()), list);
		}
		else if (e instanceof Unary)
		{
			var unary = (Unary) e;
			var list = new ArrayList<Expression>();
			list.add(unary.getExpression());
			return application(library.getFunction(unary.getOperator()), list);
		}
		else if (e instanceof Value)
		{
			var value = (Value) e;
			return variable(value.getName());
		}
		else if (e instanceof Cell)
		{
			var cell = (Cell) e;
			var component = cell.getComponent().getName();
			var entity = cell.getEntity().getName();
			
			return component+"_"+entity+"."+field(component);
		}
		else if (e instanceof Application)
		{
			var application = (Application) e;
			var name = application.getName();
			var args = application.getArguments();
			var standard = library.getFunction(name);
			return application(standard, args);
		}
		return "undefined";
	}
	
	private String application(m.library.symbols.Function standard, List<Expression> args)
	{
		String x = "",y = "",z = "";
		if (!args.isEmpty())
		{
			x = code(args.get(0));
		}
		if (args.size() >= 2)
		{
			y = code(args.get(1));
		}
		if (args.size() >= 3)
		{
			z = code(args.get(2));
		}
		
		switch (standard)
		{
		case ABS: return "math.abs("+x+")";
		case ACOS: return "math.acos("+x+")";
		case ADD: return "if ("+y+".GetComponent<"+x+">() == null){"+y+".gameObject.AddComponent<"+x+">();"+"}";
		case ADDITION: return x+"+"+y;
		case AND: return x+"&&"+y;
		case ASIN: return "math.asin("+x+")";
		case ASSIGNMENT: return x+"="+y;
		case ATAN: return "math.atan("+x+")";
		case CEIL: return "math.ceil("+x+")";
		case CLAMP: return "math.clamp("+x+","+y+","+z+")";
		case COS: return "math.cos("+x+")";
		case CREATE: return "Instantiate("+x+")";
		case CROSS: return "math.cross("+x+","+y+")";
		case DESTROY: return "Destroy("+x+".gameObject)";
		case DISTANCE: return "math.distance("+x+","+y+")";
		case DIVISION: return x+"/"+y;
		case DOT: return "math.dot("+x+","+y+")";
		case EQUAL: return x+"=="+y;
		case EXP: return "math.exp("+x+")";
		case EXP10: return "math.exp10("+x+")";
		case FLOOR: return "math.floor("+x+")";
		case FRACTIONALPART: return "math.frac("+x+")";
		case GREATER: return x+">"+y;
		case GREATEROREQUAL: return x+">="+y;
		case HALT: return "Application.Quit()";
		case HAS: return "("+y+".gameObject.GetComponent<"+x+">() != null)";
		case IN: return y+".Contains("+x+".gameObject)";
		case INEQUAL: return x+"!="+y;
		case INTEGERPART: return "((int) "+x+")";
		case INVERSE: return "(1/"+x+")";
		case LERP: return "math.lerp("+x+","+y+","+z+")";
		case LOG: return "math.log("+x+")";
		case LOG10: return "math.log10("+x+")";
		case LOWER: return x+"<"+y;
		case LOWEROREQUAL: return x+"<="+y;
		case MULTIPLICATION: return x+"*"+y;
		case NORM: return "math.length("+x+")";
		case NORMALIZE: return "math.normalize("+x+")";
		case NOT: return "!"+x;
		case OR: return x+"||"+y;
		case PLAY: return x+".GetComponent<AudioSource>().PlayOneShot("+y+")";
		case POW: return "math.pow("+x+","+y+")";
		case PROPORTIONAL: return "undefined";
		case RANDOM: return "UnityEngine.Random.Range("+x+".x,"+x+".y)";
		case READ_NUMBER: return x+".ReadValue<float>()";
		case READ_TRIGGERED: return x+".triggered";
		case RECIPROCAL: return "-"+x;
		case REFLECT: return "math.reflect("+x+","+y+")";
		case REFRACT: return "math.refract("+x+","+y+")";
		case REMOVE: return "Destroy("+y+".gameObject.GetComponent<"+x+">())";
		case ROUND: return "math.round("+x+")";
		case SET_COLOR: return x+".SetColor("+y+","+z+")";
		case SET_NUMBER: return x+".SetFloat("+y+","+z+")";
		case SET_TRIGGER: return x+".SetTrigger("+y+")";
		case SIGN: return "math.sign("+x+")";
		case SIN: return "math.sin("+x+")";
		case SIZE: return x+".Count()";
		case SQRT: return "math.sqrt("+x+")";
		case STATE_NAME: return "undefined";
		case SUBTRACTION: return x+"-"+y;
		case TAN: return "math.tan("+x+")";
		case UNLERP: return "math.unlerp("+x+","+y+","+z+")";
		case WRITE: return "Debug.Log("+x+")";
		case WRITEERROR: return "Debug.Error("+x+")";
		case XYZ: return "new Vector3("+x+","+y+","+z+")";
		case OVERLAPS: return x+".GetComponents<Collider>().Select(x=> x is BoxCollider ? Physics.OverlapBox("+x+".position+(x as BoxCollider).center, (x as BoxCollider).size, "+x+".rotation, Int32.MaxValue, QueryTriggerInteraction.Collide): x is SphereCollider ? Physics.OverlapSphere("+x+".position+(x as SphereCollider).center, (x as SphereCollider).radius, Int32.MaxValue, QueryTriggerInteraction.Collide) : null).Aggregate(new List<Collider>(), (list, x) => {list.AddRange(x); return list;}).Select(x=>x.transform.gameObject)";
		}
		return "undefined";
	}
	

	private String variable(String name)
	{
		var found = library.getValue(name);
		if (found != null)
		{
			switch (found)
			{
			case EPSILON:
				return "Float.Epsilon";
			case PI:
				namespaces.add("Unity.Mathematics");
				return "math.PI";
			case E:
				namespaces.add("Unity.Mathematics");
				return "math.E";
			case TIME_SINCE_START:
				namespaces.add(UNITY_ENGINE);
				return "UnityEngine.Time.time";
			case FIXED_DELTA_TIME:
				namespaces.add(UNITY_ENGINE);
				return "UnityEngine.Time.fixedDeltaTime";
			case DELTA_TIME:
				namespaces.add(UNITY_ENGINE);
				return "UnityEngine.Time.deltaTime";
			case TIME_SCALE:
				namespaces.add(UNITY_ENGINE);
				return "UnityEngine.Time.timeScale";
			}
			return "undefinedVariable";
		}
		else
		{
			return name;
		}
	}
	
	private String simpleComponent(String name)
	{
		var standard = library.getComponent(name);
		if (standard != null)
		{
			return component(name);
		}
		for (var i = 0; i < csharpReserved.length; i++)
		{
			if (csharpReserved[i].equals(name))
			{
				return "_"+name;
			}
		}
		return name;
	}
	
	private String component(String name)
	{
		var found = library.getComponent(name);
		if (found == null)
		{
			for (var i = 0; i < csharpReserved.length; i++)
			{
				if (csharpReserved[i].equals(name))
				{
					return "_"+name;
				}
			}
			return "M."+name;
		}
		else
		{
			switch (found)
			{
			case VELOCITY: return "Rigidbody";
			case TIMEOUT: return "timeout";
			case POSITION: return "Transform";
			case ACCELERATION: return "Rigidbody";
			case ANCHOR: return "ConfigurableJoint";
			case ANGULAR_ACCELERATION: return "Rigidbody";
			case ANGULAR_FORCE: return "Rigidbody";
			case ANGULAR_VELOCITY: return "Rigidbody";
			case AUDIOCLIP: return "AudioSource";
			case BACKGROUND: return "Camera";
			case BOND: return "ConfigurableJoint";
			case BREAK_ANGULAR_FORCE: return "ConfigurableJoint";
			case BREAK_FORCE: return "ConfigurableJoint";
			case CHILDREN: return "Transform";
			case COLLISION_EVENTS: return "Collider";
			case COLLISION_LAYER: return "Collider";
			case COLLISION_MASK: return "Collider";
			case CONVEX_HULL: return "Collider";
			case ELAPSED: return "Elapsed";
			case EMISSION: return "Light";
			case EXTENTS: return "BoxCollider";
			case FAR: return "Camera";
			case FORCE: return "Rigidbody";
			case FOV: return "Camera";
			case FRICTION: return "Collider";
			case INERTIA: return "Rigidbody";
			case INTENSITY: return "Light";
			case KINEMATIC: return "Rigidbody";
			case LOCKED_POSITION_X: return "Rigidbody";
			case LOCKED_POSITION_Y: return "Rigidbody";
			case LOCKED_POSITION_Z: return "Rigidbody";
			case LOCKED_ROTATION: return "Rigidbody";
			case LOOP: return "AudioSource";
			case MASS: return "Rigidbody";
			case MATERIAL: return "MeshRenderer";
			case MESH: return "MeshFilter";
			case MESH_COLLIDER: return "MeshCollider";
			case NEAR: return "Camera";
			case NO_COLLISION_RESPONSE: return "Collider";
			case PARENT: return "Transform";
			case PERSPECTIVE: return "Camera";
			case PITCH: return "AudioSource";
			case RADIUS: return "SphereCollider";
			case RANGE: return "Light";
			case RENDER_TEXTURE: return "Camera";
			case RESTITUTION: return "Collider";
			case ROTATION: return "Transform";
			case SCALE: return "Transform";
			case SKYBOX: return "Camera";
			case SPOT_ANGLE: return "Light";
			case TIMER: return "Timer";
			case VIEWPORT: return "Camera";
			case VOLUME: return "AudioSource";
			case ANIMATOR: return "Animator";
			}
		}
		return "undefined";
	}
	
	private String field(String name)
	{
		var found = library.getComponent(name);
		if (found == null)
		{
			return "Value";
		}
		else
		{
			switch (found)
			{
			case VELOCITY: return "velocity";
			case TIMEOUT: return "Value";
			case POSITION: return "position";
			case ACCELERATION: return "acceleration";
			case ANCHOR: return "anchorPoint";
			case ANGULAR_ACCELERATION: return "angularAcceleration";
			case ANGULAR_FORCE: return "torque";
			case ANGULAR_VELOCITY: return "angularVelocity";
			case AUDIOCLIP: return "audioClip";
			case BACKGROUND: return "backgroundColor";
			case BOND: return "connectedBody";
			case BREAK_ANGULAR_FORCE: return "breakTorque";
			case BREAK_FORCE: return "breakForce";
			case CHILDREN: return "GetComponent<Transform>()";
			case COLLISION_EVENTS: return "isTrigger";
			case COLLISION_LAYER: return "collisionLayer";
			case COLLISION_MASK: return "collisionMask";
			case CONVEX_HULL: return "";
			case ELAPSED: return "";
			case EMISSION: return "emission";
			case EXTENTS: return "extents";
			case FAR: return "farPlane";
			case FORCE: return "force";
			case FOV: return "fieldOfView";
			case FRICTION: return "friction";
			case INERTIA: return "inertia";
			case INTENSITY: return "intensity";
			case KINEMATIC: return "isKinematic";
			case LOCKED_POSITION_X: return "";
			case LOCKED_POSITION_Y: return "";
			case LOCKED_POSITION_Z: return "";
			case LOCKED_ROTATION: return "";
			case LOOP: return "loop";
			case MASS: return "mass";
			case MATERIAL: return "material";
			case MESH: return "mesh";
			case MESH_COLLIDER: return "";
			case NEAR: return "nearPlane";
			case NO_COLLISION_RESPONSE: return "isTrigger";
			case PARENT: return "GetParent()";
			case PERSPECTIVE: return "";
			case PITCH: return "pitch";
			case RADIUS: return "radius";
			case RANGE: return "range";
			case RENDER_TEXTURE: return "renderTexture";
			case RESTITUTION: return "restitution";
			case ROTATION: return "rotation";
			case SCALE: return "scale";
			case SKYBOX: return "";
			case SPOT_ANGLE: return "spotAngle";
			case TIMER: return "Value";
			case VIEWPORT: return "viewport";
			case VOLUME: return "volume";
			case ANIMATOR: return "GetComponent<Animator>()";
			}
		}
		return "undefined";
	}	
	
	private String unity(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomic = (AtomicType) type;
			switch (atomic)
			{
				case ENTITY:
					return "GameObject";
				case NUMBER:
					return "float";
				case NUMBER2:
					return "Vector2";
				case NUMBER3:
					return "Vector3";
				case PROPOSITION:
					return "bool";
				case ENTITY_LIST:
					namespaces.add("System.Collections.Generic");
					return "List<GameObject>";
				case INPUT:
					namespaces.add("UnityEngine.InputSystem");
					return "InputAction";
				case STRING:
					return "string";
				case UNIT:
					return "void";
				case COLOR:
					namespaces.add(UNITY_ENGINE);
					return "Color";
				case MESH:
					namespaces.add(UNITY_ENGINE);
					return "Mesh";
				case MATERIAL:
					namespaces.add(UNITY_ENGINE);
					return "Material";
				case ANIMATOR:
					namespaces.add(UNITY_ENGINE);
					return "Animator";
				case COMPONENT:
					return "Error (type component shouldnt be)";
				case FONT:
					namespaces.add("UnityEngine.UI");
					return "Font";
				case TEXT:
					namespaces.add(UNITY_ENGINE_UI);
					return "Text";
				case IMAGE:
					namespaces.add("UnityEngine.UI");
					return "Image";
				case AUDIOCLIP:
					return "AudioClip";
			}
		}
		return "Undefined";
	}	
}
