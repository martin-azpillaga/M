package m.generator;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.library.Library;
import m.library.types.*;
import m.m.*;

import static m.generator.AccessKind.*;
import static m.library.Symbol.*;
import static m.library.types.AtomicType.*;

public class ClassicUnity
{
	IFileSystemAccess2 fileSystem;
	Library library;
	Game game;
	HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries;
	
	Set<String> namespaces = new HashSet<>();
	Function currentFunction;
	
	String[] csharpReserved = new String[] {"base", "class", "struct", "if", "else", "for", "while", "foreach"};
	
	public void generate(Game game, HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries, IFileSystemAccess2 fileSystem)
	{
		this.game = game;
		this.library = game.library;
		this.fileSystem = fileSystem;
		this.queries = queries;
		
		for (var component : game.components.entrySet())
		{
			generate(component.getKey(), component.getValue());
		}
		for (var function : game.functions.entrySet())
		{
			var type = function.getValue();
			if (type.getParameters().length == 0 && type.getReturnType() == UNIT)
			{
				currentFunction = function.getKey();
				generate(function.getKey());
			}
		}
	}
	
	
	
	private void generate(String name, Type type)
	{
		namespaces.clear();
		namespaces.add("UnityEngine");
		
		var classifier = valueType(type) ? "struct" : "class";
		var field = type != UNIT? "public "+ unity(type) + " Value;" : "";
		var superInterface = type != ENTITY_LIST? "IComponentData" : "IBufferElementData, IEntity";
		
		generate("Unity/Assets/Code/Components/"+simpleComponent(name)+".cs",
				
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
	
	private String lines(String indentation, String... lines)
	{
		return String.join("\n"+indentation, lines);
	}
	
	private String getComponents(Map<String,AccessKind> components)
	{
		return all(components.entrySet(),x -> "Read"+(x.getValue()==AccessKind.WRITE?"Write":"Only")+"<"+component(x.getKey())+">()", ",");
	}
	
	private void generate(Function function)
	{
		namespaces.clear();
		namespaces.add("UnityEngine");
		
		var map = queries.get(function);
		
		var queryName = map.keySet();
		
		var name = function.getName();
		
		var statements = all(function.getStatements(), x->code(x), "\n			");
		
		var declareQueries = all(queryName, x -> "EntityQuery "+x+";", "\n		");
		var getQueries = all(queryName, x-> x+" = GetEntityQuery("+getComponents(map.get(x))+");", "\n			");
		
		generate("Unity/Assets/Code/Systems/"+name+".cs",
		
		all(namespaces,x->"using "+x+";", "\n"),
		"",
		"namespace M",
		"{",
		"	public class "+name+" : MonoBehaviour",
		"	{",
		"		void Update()",
		"		{",
		"			"+statements,
		"		}",
		"	}",
		"}"
		);
	}
	
	private String checkPresent(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		return component+"_"+entity+" == null ";
	}
	
	private String toArray(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		return "var "+component+"_"+entity+" = "+entity+".GetComponent<"+component(component)+">();";		
	}
	
	private String dispose(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		Type type;
		if (library.components.containsKey(component))
		{
			type = library.components.get(component).getType();
		}
		else
		{
			type = game.components.get(component);
		}
		if (entry.getValue() == TAG || isBuffer(component))
		{
			return "";
		}
		else if (isBuffer(component) || !valueType(type))
		{
			return "";
		}
		else
		{
			return component+"s_"+entity+".Dispose();";
		}
	}
	
	private String toComponent(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		if (entry.getValue() == TAG)
		{
			return "";
		}
		if (isBuffer(component))
		{
			return "var "+component+"_"+entity+" = EntityManager.GetBuffer<"+component(component)+">(entity_"+entity+");";
		}
		else
		{
			return "var "+component+"_"+entity+" = "+component+"s_"+entity+"["+entity+"_i];";
		}
	}
	
	private String code(Statement statement)
	{
		if (statement instanceof BindingBlock)
		{
			var block = (BindingBlock) statement;
			var name = block.getName();
			if (library.blocks.get(name) == QUERY)
			{
				var a = ((Value)block.getExpression()).getName();
				var query = queries.get(currentFunction).get(a);
				
				return lines("			",
				"var transforms_"+a+" = Object.FindObjectsOfType<Transform>();",
				"foreach (var "+a+" in transforms_"+a+")",
				"{",
				"	"+all(query.entrySet(), x->toArray(x,a), "\n				"),
				"	if ("+all(query.entrySet(), x->checkPresent(x,a), " || ")+"){ continue; }",
					"",
				"	"+all(block.getStatements(), x->code(x), "\n				"),
				"}");
			}
			
			return "undefined";
		}
		else if (statement instanceof Block)
		{
			var block = (Block) statement;
			var name = block.getName();
			
			if (library.blocks.get(name) == SELECTION)
			{
				var condition = code(block.getExpression());
				return 
					"\n"+
					"if ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			else if (library.blocks.get(name) == ITERATION)
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
				var cell = (Cell) atom;
				return code(atom)+" = "+code(expression)+";";
			}
		}
		else if (statement instanceof Delegation)
		{
			return code(((Delegation) statement).getApplication())+";";
		}
		return "undefined";
	}
	
	private boolean isBuffer(String component)
	{
		var standard = library.components.get(component);
		if (standard != null)
		{
			return standard.getType() == ENTITY_LIST;
		}
		else
		{
			return game.components.get(component) == ENTITY_LIST;
		}
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
			
			if (isBuffer(component))
			{
				return component+"_"+entity;
			}
			else
			{
				return component+"_"+entity+"."+field(component);
			}
		}
		else if (e instanceof Application)
		{
			var application = (Application) e;
			var name = application.getName();
			var args = application.getArguments();
			var standard = library.functions.get(name);
			if (standard == IN)
			{
				return code(args.get(1))+".Value.Contains("+code(args.get(0))+".gameObject)";
			}
			else if (standard == REMOVE)
			{
				return "Destroy("+code(args.get(1))+".gameObject.GetComponent<"+code(args.get(0))+">());";
			}
			else if (standard == ADD)
			{
				return code(args.get(1))+".gameObject.AddComponent<"+code(args.get(0))+">();";
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
		var found = library.variables.get(name);
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
				namespaces.add("UnityEngine");
				return "UnityEngine.Time.deltaTime";
			case FIXED_DELTA_TIME:
				namespaces.add("UnityEngine");
				return "UnityEngine.Time.time";
			case DELTA_TIME:
				namespaces.add("UnityEngine");
				return "UnityEngine.Time.deltaTime";
			case TIME_SCALE:
				namespaces.add("UnityEngine");
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
		var found = library.components.get(name);
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
			}
		}
		return "undefined";
	}
	
	private String field(String name)
	{
		var found = library.components.get(name);
		if (found == null)
		{
			return "Value";
		}
		else
		{
			switch (found)
			{
				case VELOCITY: { return "velocity";}
				case TIMEOUT: return "Value";
				case POSITION: return "position";
				case COLLISIONS: return "Value";
				case NUMBER_LABEL: return "Value";
			}
		}
		return "undefined";
	}
	
	private String application(String name)
	{
		var found = library.functions.get(name);
		if (found == null)
		{
			return "userDefinedFunction";
		}
		else
		{
			switch (found)
			{
				case COS: { namespaces.add("static Unity.Mathematics.math"); return "cos"; }
				case SIN: { namespaces.add("static Unity.Mathematics.math"); return "sin"; }
				case RANDOM: { return "M.Library.random";}
				case XYZ: { return "M.Library.xyz";}
				case IN: { return "M.Library.contains";}
				case READ_NUMBER: { return "M.Library.readNumber";}
			}
		}
		return "undefined";
	}
	
	private <T> String all(Collection<T> set, java.util.function.Function<T,String> f, String separator)
	{
		return String.join(separator, set.stream().map(f).collect(Collectors.toList()));
	}
	
	private void generate(String path, String... lines)
	{
		fileSystem.generateFile(path, String.join("\n", lines));
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
					namespaces.add("UnityEngine");
					return "Color";
				case MESH:
					namespaces.add("UnityEngine");
					return "Mesh";
				case MATERIAL:
					namespaces.add("UnityEngine");
					return "Material";
				case ANIMATOR:
					namespaces.add("UnityEngine");
					return "Animator";
			}
		}
		return "Undefined";
	}
	
	private boolean valueType(Type type)
	{
		return type == NUMBER || type == NUMBER2 || type == NUMBER3 ||
				type == ENTITY || type == ENTITY_LIST || type == PROPOSITION;
	}
	
	
}
