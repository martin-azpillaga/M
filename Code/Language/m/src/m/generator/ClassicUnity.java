package m.generator;

import static m.generator.Writer.*;
import static m.library.symbols.Block.*;
import static m.library.types.AtomicType.*;
import static m.library.symbols.Component.*;
import static m.library.symbols.Function.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.xtext.EcoreUtil2;
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
	HashSet<String> variables;
	Stack<HashSet<String>> stack;
	
	String[] csharpReserved = new String[]
	{
		"abstract", "as", "base", "bool",	
		"break", "byte", "case", "catch",	
		"char", "checked", "class", "const",	
		"continue", "decimal", "default", "delegate",	
		"do", "double", "else", "enum",	
		"event", "explicit", "extern", "false",	
		"finally", "fixed", "float", "for",	
		"foreach", "goto", "if", "implicit",	
		"in", "int", "interface", "internal",	
		"is", "lock", "long", "namespace",	
		"new", "null", "object", "operator",	
		"out", "override", "params", "private",	
		"protected", "public", "readonly", "ref",	
		"return", "sbyte", "sealed", "short",	
		"sizeof", "stackalloc", "static", "string",	
		"struct", "switch", "this", "throw",	
		"true", "try", "typeof", "uint",	
		"ulong", "unchecked", "unsafe", "ushort",	
		"using", "virtual", "void", "volatile",
		"while"
	};
	
	private static final String UNITY_ENGINE = "UnityEngine";
	private static final String UNITY_ENGINE_UI = "UnityEngine.UI";
	public void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.game = game;
		this.library = game.library;
		this.fileSystem = fileSystem;
		this.queries = game.queries;
		this.variables = new HashSet<String>();
		this.stack = new Stack<>();
		
		for (var component : game.components.entrySet())
		{
			fileSystem.generateFile("UnityClassic/Assets/Code/Components/"+unreserved(component.getKey())+".cs",
					generate(component.getKey(), component.getValue()));
		}
		var systems = lines("",
		"using UnityEngine;",
		"using UnityEngine.UI;",
		"using UnityEngine.EventSystems;",
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
		"      "+all(game.systems, x->"public bool "+x.getName()+" = true;", "\n      "));		
		for (var function : game.systems)
		{
			for (var query : game.queries.get(function).keySet())
			{
				var tooltip = new HashSet<String>();
				for (var component : game.queries.get(function).get(query).keySet())
				{
					tooltip.add(simpleComponent(component));
				}
				systems += lines("",
					"[Tooltip(\""+all(tooltip, x->x, ", ")+"\")]",
					"public List<GameObject> " + function.getName() + "_" + query + ";\n");
			}
		}

		systems += "void Update()\n{\n\n           var gos = FindObjectsOfType<Transform>().Select(x=>x.gameObject);\n";
		
		for (var function : game.systems)
		{
			for (var query : game.queries.get(function).keySet())
			{
				systems += function.getName() + "_" + query + " = new List<GameObject>();\n";
			}
		}
		
		for (var function : game.systems)
		{
			currentFunction = function;
			systems += "if ("+function.getName()+")\n{"+generate(function)+"\n}";
		}
		
		for (var function : game.systems)
		{
			for (var query : game.queries.get(function).keySet())
			{
				fileSystem.generateFile("UnityClassic/Assets/Code/MultiComponents/"+function.getName()+"_"+query+".cs", generateMultiComponent(function, query));
			}
		}
		
		systems += "}\n}\n}\n";
		
		fileSystem.generateFile("UnityClassic/Assets/Code/Systems/Systems.cs", systems);
	}
	
	private String typeOf(String component)
	{
		var standard = library.getComponent(component);
		if (standard != null)
		{
			return unity(standard.getType());
		}
		else
		{
			var user = game.components.get(component);
			return unity(user);
		}
	}
	
	private String generateMultiComponent(Function function, String query)
	{
		var extras = extraComponents(function);
		var normal = new ArrayList<String>();
		if (game.queries.get(function).containsKey(query))
		{
			normal.addAll(game.queries.get(function).get(query).keySet());
		}
		var extra = new ArrayList<String>();
		if (extras.containsKey(query))
		{
			extra.addAll(extras.get(query));
		}
		return lines("",
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
		"   public class "+function.getName()+"_"+query+" : MonoBehaviour",
		"   {",
		"       "+all(normal, x->"public "+typeOf(x)+" "+unreserved(x)+";", "\n       "),
		"       "+all(normal, x->simpleComponent(x)+" " + unreserved(x)+"Component;", "\n       "),
		"       void Start()",
		"       {",
		"           "+all(normal, x->unreserved(x)+"Component = GetComponent<"+simpleComponent(x)+">();\n"+"if ("+unreserved(x)+"Component == null){ "+unreserved(x)+"Component = gameObject.AddComponent<"+simpleComponent(x)+">();}", "\n           "),
		"           "+all(normal, x->unreserved(x)+"Component."+field(x)+" = "+unreserved(x)+";", "\n           "),
		"           "+all(extra, x->"if (GetComponent<"+x+">() == null) { gameObject.AddComponent<"+x+">(); }", "\n           "),
		"       }",
		"",
		"       void Update()",
		"       {",
		"           "+all(normal, x->"if ("+unreserved(x)+"Component != null){"+unreserved(x)+" = "+unreserved(x)+"Component."+field(x)+";}", "\n           "),
		"       }",
		"",
		"       void OnValidate()",
		"       {",
		"           "+all(normal, x->"if ("+unreserved(x)+"Component != null){"+unreserved(x)+"Component."+field(x)+" = "+unreserved(x)+";}", "\n           "),
		"       }",
		"   }",
		"}");
	}
	
	
	
	private String generate(String name, Type type)
	{
		namespaces.clear();
		namespaces.add(UNITY_ENGINE);
		
		var field = type != UNIT? "public "+ unity(type) + " Value;" : "";
		var extra = "";
		if (type == INPUT)
		{
			extra = "void Start() {if (Value != null) {Value.Enable();}}";
		}
		return
				lines("",
				all(namespaces,x->"using "+x+";", "\n"),
				"",
				"namespace M",
				"{",
				"    public class "+unreserved(name)+" : MonoBehaviour",
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
			stack.push(new HashSet<String>(variables));
			
			var block = (BindingBlock) statement;
			var name = block.getName();
			if (library.getBlock(name) == QUERY)
			{
				var a = block.getExpression().getName();
				var extras = extraComponents(currentFunction);
				var components = new ArrayList<String>();
				if (queries.get(currentFunction).containsKey(a))
				{
					components.addAll(queries.get(currentFunction).get(a).keySet());
				}
				if (extras.containsKey(a))
				{
					components.addAll(extras.get(a));
				}
				
				variables.add(a);
				
				var result = lines("			",
				"foreach (var "+a+" in gos)",
				"{",
				"	"+all(components, x->"var "+x+"_"+a+" = "+a+".GetComponent<"+component(x)+">();", "\n				"),
				components.isEmpty() ? "" : "	if ("+all(components, x->x+"_"+a+" != null ", " && ")+"){",
					"",
				"   "+currentFunction.getName()+"_"+a+".Add("+a+");",
				"	"+all(block.getStatements(), x->code(x), "\n				"),
				components.isEmpty() ? "" : "   }",
				"}");

				variables = stack.pop();
				
				return result;
			}
			
			
			return "undefined";
		}
		else if (statement instanceof Block)
		{
			stack.push(new HashSet<String>(variables));
			var block = (Block) statement;
			var name = block.getName();
			
			var result = "";
			if (library.getBlock(name) == SELECTION)
			{
				var condition = code(block.getExpression());
				result =
					"\n"+
					"if ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			else if (library.getBlock(name) == ITERATION)
			{
				var condition = code(block.getExpression());
				result = 
					"\n"+
					"while ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			variables = stack.pop();
			return result;
			
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();
			var code = code(expression);
			
			if (atom instanceof Value)
			{
				var value = (Value) atom;
				var name = value.getName();
				if (variables.contains(name))
				{
					return code(atom)+" = "+code+";";
				}
				else
				{
					variables.add(name);
					return "var "+code(atom)+" = "+code+";";
				}
			}
			else if (atom instanceof Cell)
			{
				var cell = (Cell) atom;
				var entity = cell.getEntity().getName();
				var component = cell.getComponent().getName();
				
				if (library.getComponent(cell.getComponent().getName()) == DISPLAY)
				{
					code = "(int)("+code+")";
				}
				
				if (game.queries.get(currentFunction).containsKey(entity))
				{
					return code(atom)+" = "+code+";";
				}
				else
				{
					return entity+".GetComponent<"+component(component)+">()."+field(component)+" = "+code+";";
				}
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
		case ADD: return "if ("+y+".GetComponent<"+simpleComponent(x)+">() == null){"+y+".AddComponent<"+simpleComponent(x)+">();"+"}";
		case ADDITION: return x+"+"+y;
		case AND: return x+"&&"+y;
		case ASIN: return "math.asin("+x+")";
		case ASSIGNMENT: return x+"="+y;
		case ATAN: return "math.atan("+x+")";
		case CEIL: return "math.ceil("+x+")";
		case CLAMP: return "math.clamp("+x+","+y+".x,"+y+".y)";
		case COS: return "math.cos("+x+")";
		case CREATE: return "Instantiate<GameObject>("+x+")";
		case CROSS: return "((Vector3)math.cross("+x+","+y+"))";
		case DESTROY: return "Destroy("+x+")";
		case DISTANCE: return "math.distance("+x+","+y+")";
		case DIVISION: return x+"/"+y;
		case DOT: return "math.dot("+x+","+y+")";
		case EQUAL: return x+"=="+y;
		case EXP: return "math.exp("+x+")";
		case FLOOR: return "math.floor("+x+")";
		case FRACTIONALPART: return "math.frac("+x+")";
		case GREATER: return x+">"+y;
		case GREATEROREQUAL: return x+">="+y;
		case HALT: return "#if UNITY_EDITOR\nUnityEditor.EditorApplication.isPlaying = false;\n#endif\nApplication.Quit()";
		case HAS: return "("+y+".GetComponent<"+simpleComponent(x)+">() != null)";
		case IN: return y+".Contains("+x+")";
		case INEQUAL: return x+"!="+y;
		case INTEGERPART: return "math.trunc("+x+")";
		case INVERSE: return "(1/"+x+")";
		case LERP: return "math.lerp("+x+","+y+".x,"+y+".y)";
		case LOG: return "math.log("+x+")";
		case LOWER: return x+"<"+y;
		case LOWEROREQUAL: return x+"<="+y;
		case MULTIPLICATION: return x+"*"+y;
		case NORM: return "math.length("+x+")";
		case NORMALIZE: return "((Vector3)math.normalize("+x+"))";
		case NOT: return "!"+x;
		case OR: return x+"||"+y;
		case PLAY_ONCE: return x+".GetComponent<AudioSource>().PlayOneShot("+y+")";
		case POW: return "math.pow("+x+","+y+")";
		case PROPORTIONAL: return "math.remap("+x+", "+y+".x, "+y+".y, "+z+".x, "+z+".y)";
		case RANDOM: return "UnityEngine.Random.Range("+x+".x,"+x+".y)";
		case READ_NUMBER: return x+".ReadValue<float>()";
		case READ_TRIGGERED: return x+".triggered";
		case READ_VECTOR: return x+".ReadValue<Vector2>()";
		case RECIPROCAL: return "-"+x;
		case REFLECT: return "((Vector3)math.reflect("+x+","+y+"))";
		case REFRACT: return "((Vector3)math.refract("+x+","+y+", "+z+"))";
		case REMOVE: return "if ("+y+".GetComponent<"+simpleComponent(x)+">() != null){ Destroy("+y+".GetComponent<"+simpleComponent(x)+">());}";
		case ROUND: return "math.round("+x+")";
		case SET_COLOR: return x+".SetColor("+y+","+z+")";
		case SET_NUMBER: return x+".SetFloat("+y+","+z+")";
		case SET_TRIGGER: return x+".GetComponent<Animator>().SetTrigger("+y+")";
		case SIGN: return "math.sign("+x+")";
		case SIN: return "math.sin("+x+")";
		case SIZE: return x+".Count()";
		case SQRT: return "math.sqrt("+x+")";
		case IN_STATE: return x+".GetComponent<Animator>().GetCurrentAnimatorStateInfo(0).IsName("+y+")";
		case SUBTRACTION: return x+"-"+y;
		case TAN: return "math.tan("+x+")";
		case UNLERP: return "math.unlerp("+x+","+y+".x,"+y+".y)";
		case WRITE: return "if (Debug.isDebugBuild){ Debug.Log("+x+"); }";
		case WRITEERROR: return "if (Debug.isDebugBuild){ Debug.LogError("+x+"); }";
		case WRITE_WARNING: return "if (Debug.isDebugBuild){ Debug.LogWarning("+x+"); }";
		case SCREENSHOT: return "ScreenCapture.CaptureScreenshot((System.DateTime.Now+\".png\").Replace(\"/\", \"-\"), 1)";
		case XYZ: return "new Vector3("+x+","+y+","+z+")";
		case OVERLAPS: return x+".GetComponentsInChildren<Collider>().Select(x=> x is BoxCollider ? Physics.OverlapBox((x as BoxCollider).bounds.center, Vector3.Scale((x as BoxCollider).size/2,"+x+".transform.lossyScale), "+x+".transform.rotation, Int32.MaxValue, QueryTriggerInteraction.Collide): x is SphereCollider ? Physics.OverlapSphere((x as SphereCollider).bounds.center, (x as SphereCollider).radius*Mathf.Max("+x+".transform.lossyScale.x, Mathf.Max("+x+".transform.lossyScale.y, "+x+".transform.lossyScale.z)), Int32.MaxValue, QueryTriggerInteraction.Collide) : null).Aggregate(new List<Collider>(), (list, x) => {list.AddRange(x); return list;}).Select(x=>x.transform.gameObject).ToList()";
		case TO_NUMBER3: return x+".eulerAngles";
		case TO_QUATERNION: return "Quaternion.Euler("+x+".x, "+x+".y, "+x+".z)";
		case ADD_FORCE: return x+".GetComponent<Rigidbody>().AddForce("+y+")";
		case ADD_TORQUE: return x+".GetComponent<Rigidbody>().AddTorque("+y+")";
		case CLOSEST_POINT: return "("+x+".GetComponent<Collider>() != null ? "+x+".GetComponent<Collider>().ClosestPoint("+y+")" + ":" + x+".transform.position)";
		case GET_COLOR: return x+".GetColor("+y+")";
		case GET_INTEGER: return x+".GetInt("+y+")";
		case GET_KEYWORD: return x+".IsKeywordEnabled("+y+")";
		case GET_NUMBER: return x+".GetFloat("+y+")";
		case GET_TEXTURE: return x+".GetTexture("+y+")";
		case SET_INTEGER: return x+".SetInt("+y+", (int)"+z+")";
		case SET_KEYWORD: return "if ("+z+"){ "+x+".EnableKeyword("+y+");}else{ "+x+".DisableKeyword("+y+");}";
		case SET_TEXTURE: return x+".SetTexture("+y+", "+z+")";
		case DEGREES: return "math.degrees("+x+")";
		case MAX: return "math.max("+x+", "+y+")";
		case MIN: return "math.min("+x+", "+y+")";
		case RADIANS: return "math.radians("+x+")";
		case SLERP: return "math.slerp("+x+", "+y+", "+z+")";
		case STEP: return "math.step("+x+", "+y+")";
		case BREAKPOINT: return "Debug.Break()";
		case PAUSE: return x+".GetComponent<AudioSource>().Pause()";
		case PLAY: return x+".GetComponent<AudioSource>().Play()";
		case STOP: return x+".GetComponent<AudioSource>().Stop()";
		case UNPAUSE: return x+".GetComponent<AudioSource>().UnPause()";
		case VIEWPORT_TO_WORLD: return "Camera.main.ViewportToWorldPoint("+x+")";
		case WORLD_TO_VIEWPORT: return "Camera.main.WorldToViewportPoint("+x+")";
		case SCREEN_OVERLAPS: return "Physics.RaycastAll(Camera.main.ScreenPointToRay("+x+")).Select(x=>x.transform.gameObject)";
		case X: return x+".x";
		case Y: return x+".y";
		case Z: return x+".z";
		case OVER: return "(EventSystem.current.currentSelectedGameObject == "+x+")";
		case TO_NUMBER: return "float.Parse("+x+", System.Globalization.CultureInfo.InvariantCulture)";
		case TO_STRING: return x+".ToString()";
		case IS_NEGATIVE: return "("+x+"< 0)";
		case IS_POSITIVE: return "("+x+"> 0)";
		case IS_ZERO: return "("+x+"== 0)";
		}
		return "undefined";
	}
	
	private HashMap<String, HashSet<String>> extraComponents(Function function)
	{
		var map = new HashMap<String, HashSet<String>>();
		
		for (var application : EcoreUtil2.getAllContentsOfType(function, Application.class))
		{
			var name = application.getName();
			var standard = game.library.getFunction(name);
			if (standard == SET_TRIGGER || standard == IN_STATE)
			{
				var entity = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(entity))
				{
					map.put(entity, new HashSet<String>());
				}
				map.get(entity).add("Animator");
			}
			else if (standard == PLAY || standard == PLAY_ONCE || standard == PAUSE || standard == UNPAUSE || standard == STOP)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<String>());
				}
				map.get(a).add("AudioSource");
			}
			else if (standard == ADD_FORCE || standard == ADD_TORQUE)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<String>());
				}
				map.get(a).add("Rigidbody");
			}
		}
		
		return map;
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
	
	private String unreserved(String name)
	{
		for (var i = 0; i < csharpReserved.length; i++)
		{
			if (csharpReserved[i].equals(name))
			{
				return "_"+name;
			}
		}
		return name;
	}
	
	private String simpleComponent(String name)
	{
		var standard = library.getComponent(name);
		if (standard != null)
		{
			return component(name);
		}
		return unreserved(name);
	}
	
	private String component(String name)
	{
		var found = library.getComponent(name);
		if (name.equals("Animator"))
		{
			return "Animator";
		}
		else if (name.equals("AudioSource"))
		{
			return "AudioSource";
		}
		else if (name.equals("Rigidbody"))
		{
			return "Rigidbody";
		}
		
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
			case ANGULAR_VELOCITY: return "Rigidbody";
			case AUDIOCLIP: return "AudioSource";
			case BACKGROUND: return "Camera";
			case ELAPSED: return "Elapsed";
			case EMISSION: return "Light";
			case EXTENTS: return "BoxCollider";
			case FAR: return "Camera";
			case FOV: return "Camera";
			case FRICTION: return "Collider";
			case INERTIA: return "Rigidbody";
			case INTENSITY: return "Light";
			case LOOP: return "AudioSource";
			case MASS: return "Rigidbody";
			case MATERIAL: return "MeshRenderer";
			case MESH: return "MeshFilter";
			case NEAR: return "Camera";
			case NO_COLLISION_RESPONSE: return "Collider";
			case PARENT: return "Transform";
			case PITCH: return "AudioSource";
			case RADIUS: return "SphereCollider";
			case RANGE: return "Light";
			case RENDER_TEXTURE: return "Camera";
			case RESTITUTION: return "Collider";
			case ROTATION: return "Transform";
			case SCALE: return "Transform";
			case SPOT_ANGLE: return "Light";
			case TIMER: return "Timer";
			case VIEWPORT: return "Camera";
			case VOLUME: return "AudioSource";
			case BOX_CENTER: return "BoxCollider";
			case SPHERE_CENTER: return "SphereCollider";
			case SHADOW_RECEIVER: return "Renderer";
			case KINEMATIC: return "Rigidbody";
			case INDIRECT_MULTIPLIER: return "Light";
			case COOKIE: return "Light";
			case DISPLAY: return "Camera";
			case CULLING: return "Camera";
			case ORTHOGRAPHIC_SIZE: return "Camera";
			case FONT: return "Text";
			case IMAGE: return "RawImage";
			case IMAGE_COLOR: return "RawImage";
			case IMAGE_MATERIAL: return "RawImage";
			case SLIDER_VALUE: return "Slider";
			case TEXT: return "Text";
			case TEXTFIELD_VALUE: return "InputField";
			case TEXT_COLOR: return "Text";
			case TEXT_MATERIAL: return "Text";
			case TOGGLED: return "Toggle";
			case ANCHOR_MIN: return "RectTransform";
			case ANCHOR_MAX: return "RectTransform";
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
			case POSITION: return "localPosition";
			case ANGULAR_VELOCITY: return "angularVelocity";
			case AUDIOCLIP: return "audioClip";
			case BACKGROUND: return "backgroundColor";
			case ELAPSED: return "";
			case EMISSION: return "color";
			case EXTENTS: return "size";
			case FAR: return "farClipPlane";
			case FOV: return "fieldOfView";
			case FRICTION: return "material.dynamicFriction";
			case INERTIA: return "inertiaTensor";
			case INTENSITY: return "intensity";
			case LOOP: return "loop";
			case MASS: return "mass";
			case MATERIAL: return "material";
			case MESH: return "mesh";
			case NEAR: return "nearClipPlane";
			case NO_COLLISION_RESPONSE: return "isTrigger";
			case PARENT: return "parent.gameObject";
			case PITCH: return "pitch";
			case RADIUS: return "radius";
			case RANGE: return "range";
			case RENDER_TEXTURE: return "renderTexture";
			case RESTITUTION: return "material.bounciness";
			case ROTATION: return "localRotation";
			case SCALE: return "localScale";
			case SPOT_ANGLE: return "spotAngle";
			case TIMER: return "Value";
			case VIEWPORT: return "rect";
			case VOLUME: return "volume";
			case BOX_CENTER: return "center";
			case SPHERE_CENTER: return "center";
			case SHADOW_RECEIVER: return "receiveShadows";
			case KINEMATIC: return "isKinematic";
			case INDIRECT_MULTIPLIER: return "bounceIntensity";
			case COOKIE: return "cookie";
			case DISPLAY: return "targetDisplay";
			case CULLING: return "cullingMask";
			case ORTHOGRAPHIC_SIZE: return "orthographicSize";
			case FONT: return "font";
			case IMAGE: return "texture";
			case IMAGE_COLOR: return "color";
			case IMAGE_MATERIAL: return "material";
			case SLIDER_VALUE: return "value";
			case TEXT: return "text";
			case TEXTFIELD_VALUE: return "text";
			case TEXT_COLOR: return "color";
			case TEXT_MATERIAL: return "material";
			case TOGGLED: return "isOn";
			case ANCHOR_MAX: return "anchorMax";
			case ANCHOR_MIN: return "anchorMin";
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
			case QUATERNION:
				return "Quaternion";
			case TEXTURE:
				return "Texture";
			case COLLIDER:
				return "Collider";
			case RECT: return "Rect";
			}
			return "Undefined";
		}
		return "Undefined";
	}	
}
