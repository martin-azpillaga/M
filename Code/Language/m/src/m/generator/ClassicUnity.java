package m.generator;

import static m.generator.Writer.*;
import static m.library.symbols.Function.*;
import static m.library.symbols.Block.*;
import static m.library.types.AtomicType.*;

import java.util.HashMap;
import java.util.HashSet;
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
		
		return
				lines("",
				all(namespaces,x->"using "+x+";", "\n"),
				"",
				"namespace M",
				"{",
				"    public class "+simpleComponent(name)+" : MonoBehaviour",
				"    {",
				"        "+field,
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
				"var transforms_"+a+" = Object.FindObjectsOfType<Transform>();",
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
			return code(binary.getLeft())+" "+binary.getOperator()+" "+code(binary.getRight());
		}
		else if (e instanceof Unary)
		{
			var unary = (Unary) e;
			return unary.getOperator()+" "+code(unary.getExpression());
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
			if (standard == IN)
			{
				return code(args.get(1))+".Contains("+code(args.get(0))+".gameObject)";
			}
			else if (standard == CREATE)
			{
				return "Instantiate("+code(args.get(0))+")";
			}
			else if (standard == DESTROY)
			{
				return "Destroy("+code(args.get(0))+")";
			}
			else if (standard == HAS)
			{
				return code(args.get(0))+".GetComponent<"+code(args.get(1))+">() != null";
			}
			else if (standard == REMOVE)
			{
				return "Destroy("+code(args.get(1))+".gameObject.GetComponent<"+code(args.get(0))+">())";
			}
			else if (standard == ADD)
			{
				return code(args.get(1))+".gameObject.AddComponent<"+code(args.get(0))+">()";
			}
			else
			{
				return application(name)+"("+all(args,x->code(x), ", ")+")";
			}
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
			case VELOCITY: { return "Rigidbody";}
			case TIMEOUT: return "timeout";
			case POSITION: { return "Transform";}
			case COLLISIONS: {return "ClassicCollisions";}
			case NUMBER_LABEL: {return "ClassicNumber";}
			case ACCELERATION:
				break;
			case ANCHOR:
				break;
			case ANGULAR_ACCELERATION:
				break;
			case ANGULAR_FORCE:
				break;
			case ANGULAR_VELOCITY:
				break;
			case AUDIOCLIP:
				break;
			case BACKGROUND:
				break;
			case BOND:
				break;
			case BREAK_ANGULAR_FORCE:
				break;
			case BREAK_FORCE:
				break;
			case CHILDREN:
				break;
			case COLLISION_EVENTS:
				break;
			case COLLISION_LAYER:
				break;
			case COLLISION_MASK:
				break;
			case CONVEX_HULL:
				break;
			case ELAPSED:
				break;
			case EMISSION:
				break;
			case EXTENTS:
				break;
			case FAR:
				break;
			case FORCE:
				break;
			case FOV:
				break;
			case FRICTION:
				break;
			case INERTIA:
				break;
			case INTENSITY:
				break;
			case KINEMATIC:
				break;
			case LOCKED_POSITION_X:
				break;
			case LOCKED_POSITION_Y:
				break;
			case LOCKED_POSITION_Z:
				break;
			case LOCKED_ROTATION:
				break;
			case LOOP:
				break;
			case MASS:
				break;
			case MATERIAL: return "MeshRenderer";
			case MESH: return "MeshFilter";
			case MESH_COLLIDER:
				break;
			case NEAR:
				break;
			case NO_COLLISION_RESPONSE:
				break;
			case PARENT:
				break;
			case PERSPECTIVE:
				break;
			case PITCH:
				break;
			case RADIUS:
				break;
			case RANGE:
				break;
			case RENDER_TEXTURE:
				break;
			case RESTITUTION:
				break;
			case ROTATION:
				break;
			case SCALE:
				break;
			case SKYBOX:
				break;
			case SPOT_ANGLE:
				break;
			case TIMER:
				break;
			case VIEWPORT:
				break;
			case VOLUME:
				break;
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
			case COLLISIONS: return "Value";
			case NUMBER_LABEL: return "Value";
			case ACCELERATION: return "acceleration";
			case ANCHOR: return "anchorPoint";
			case ANGULAR_ACCELERATION: return "angularAcceleration";
			case ANGULAR_FORCE: return "torque";
			case ANGULAR_VELOCITY: return "angularVelocity";
			case AUDIOCLIP: return "audioClip";
			case BACKGROUND: return "backgroundColor";
			case BOND: return "";
			case BREAK_ANGULAR_FORCE:
				break;
			case BREAK_FORCE:
				break;
			case CHILDREN:
				break;
			case COLLISION_EVENTS:
				break;
			case COLLISION_LAYER:
				break;
			case COLLISION_MASK:
				break;
			case CONVEX_HULL:
				break;
			case ELAPSED:
				break;
			case EMISSION:
				break;
			case EXTENTS:
				break;
			case FAR:
				break;
			case FORCE:
				break;
			case FOV:
				break;
			case FRICTION:
				break;
			case INERTIA:
				break;
			case INTENSITY:
				break;
			case KINEMATIC:
				break;
			case LOCKED_POSITION_X:
				break;
			case LOCKED_POSITION_Y:
				break;
			case LOCKED_POSITION_Z:
				break;
			case LOCKED_ROTATION:
				break;
			case LOOP:
				break;
			case MASS:
				break;
			case MATERIAL: return "material";
			case MESH: return "mesh";
			case MESH_COLLIDER:
				break;
			case NEAR:
				break;
			case NO_COLLISION_RESPONSE:
				break;
			case PARENT:
				break;
			case PERSPECTIVE:
				break;
			case PITCH:
				break;
			case RADIUS:
				break;
			case RANGE:
				break;
			case RENDER_TEXTURE:
				break;
			case RESTITUTION:
				break;
			case ROTATION:
				break;
			case SCALE:
				break;
			case SKYBOX:
				break;
			case SPOT_ANGLE:
				break;
			case TIMER:
				break;
			case VIEWPORT:
				break;
			case VOLUME:
				break;
			case ANIMATOR: return "GetComponent<Animator>()";
			default:
				break;
			}
		}
		return "undefined";
	}
	
	private String application(String name)
	{
		var found = library.getFunction(name);
		if (found == null)
		{
			return "userDefinedFunction";
		}
		else
		{
			switch (found)
			{
				case ABS: return "Mathf.Abs";
				case SIGN: return "Mathf.Sign";
				case CEIL: return "Mathf.Ceil";
				case FLOOR: return "Mathf.Floor";
				case ROUND: return "Mathf.Round";
				case CLAMP: return "Mathf.Clamp";
				case INTEGERPART: return "M.Library.integerPart";
				case FRACTIONALPART: return "M.Library.fractionalPart";
				case INVERSE: return "";
				case RECIPROCAL: return "";
				
				case LERP: return "";
				case UNLERP: return "";
				case PROPORTIONAL: return "";
				
				case CROSS: return "";
				case DOT: return "";
				case NORM: return "M.Library.norm";
				case NORMALIZE: return "";
				case DISTANCE: return "";
				case REFLECT: return "";
				case REFRACT: return "";
						
				case IN: return "M.Library.in";
				case XYZ: return "M.Library.xyz";
				
				case SIN: return "Mathf.Sin";
				case COS: return "Mathf.Cos";
				case TAN: return "Mathf.Tan";
				case ASIN: return "";
				case ACOS: return "";
				case ATAN: return "";
				case EXP: return "";
				case LOG: return "";
				case EXP10: return "";
				case LOG10: return "";
				case POW: return "";
				case SQRT: return "";
				case RANDOM: return "M.Library.random";
				
				case CREATE: return "";
				case DESTROY: return "";
				case ADD: return "";
				case REMOVE: return "";
				case HAS: return "";
				
				case WRITE: return "";
				case WRITEERROR: return "";
				case HALT: return "";
				
				
				case SET_NUMBER: return "M.Library.setFloat";
				case SET_COLOR: return "M.Library.setColor";
				
				case SET_TRIGGER: return "M.Library.setTrigger";
				case STATE_NAME: return "M.Library.stateName";
				
				case READ_TRIGGERED: return "M.Library.readTriggered";
				case READ_NUMBER: return "M.Library.readNumber";
				
				case PLAY: return "M.Library.play";
				
			case ADDITION: return "";
			case AND:
				break;
			case ASSIGNMENT:
				break;
			case DIVISION:
				break;
			case EQUAL:
				break;
			case GREATER:
				break;
			case GREATEROREQUAL:
				break;
			case INEQUAL:
				break;
			case LOWER:
				break;
			case LOWEROREQUAL:
				break;
			case MULTIPLICATION:
				break;
			case NOT:
				break;
			case OR:
				break;
			case SIZE:
				break;
			case SUBTRACTION:
				break;
			default:
				break;
				
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
