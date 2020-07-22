package m.generator;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.library.Library;
import m.m.*;
import m.types.*;

import static m.types.AtomicType.*;
import static m.generator.AccessKind.*;

enum AccessKind
{
	READ, WRITE, TAG
}

public class Unity
{
	IFileSystemAccess2 fileSystem;
	Library library;
	Game game;
	HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries;
	
	Set<String> namespaces = new HashSet<>();
	Function currentFunction;
	
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
		namespaces.add("Unity.Entities");
		
		var classifier = valueType(type) ? "struct" : "class";
		var field = type != UNIT? "public "+ unity(type) + " Value;" : "";
		var superInterface = type != ENTITY_LIST? "IComponentData" : "IBufferElementData, IEntity";
		
		if (type == ENTITY_LIST)
		{
			generate("Unity/Assets/Code/Components/"+name+"Authoring.cs",
			all(namespaces,x->"using "+x+";", "\n"),
			"",
			"namespace M",
			"{",
			"    public struct "+name+" : IBufferElementData, IEntity",
			"    {",
			"        public Entity Value { get; set; }",
			"    }",
			"    public class "+name+"Authoring : MonoBehaviour, IConvertGameObjectToEntity, IDeclareReferencedPrefabs",
			"    {",
			"        public List<GameObject> Value;",
			"        public void Convert (Entity entity, EntityManager entityManager, GameObjectConversionSystem gameObjectConversionSystem)",
			"        {",
			"            var buffer = entityManager.AddBuffer<"+name+">(entity);",
			"            foreach (var v in Value)",
			"            {",
			"                buffer.Add(new "+name+" { Value = gameObjectConversionSystem.GetPrimaryEntity(v) });",
			"            }",
			"        }",
			"        public void DeclareReferencedPrefabs (List<GameObject> referencedPrefabs)",
			"        {",
			"            foreach (var v in Value)",
			"            {",
			"               referencedPrefabs.Add(v);",
			"            }",
			"        }",
			"    }",
			"}"
			);
		}
		else
		{
			generate("Unity/Assets/Code/Components/"+name+".cs",
			
			"using Unity.Entities;",
			"",
			"namespace M",
			"{",
			"    [GenerateAuthoringComponent]",
			"    public "+classifier+" "+name+" : "+superInterface,
			"    {",
			"        "+field,
			"    }",
			"}"
			);
		}
	}
	
	private String getComponents(Map<String,AccessKind> components)
	{
		return all(components.entrySet(),x -> "Read"+(x.getValue()==WRITE?"Write":"Only")+"<"+x.getKey()+">", ",");
	}
	
	private void generate(Function function)
	{
		namespaces.clear();
		namespaces.add("Unity.Entities");
		namespaces.add("static Unity.Entities.ComponentType");
		namespaces.add("static Unity.Collections.Allocator");
		
		var map = queries.get(function);
		
		var queryName = map.keySet();
		
		var name = function.getName();
		
		var statements = all(function.getStatements(), x->code(x), "\n");
		
		var declareQueries = all(queryName, x -> "EntityQuery "+x+";", "\n");
		var getQueries = all(queryName, x-> x+" = GetEntityQuery("+getComponents(map.get(x))+");", "\n");
		
		generate("Unity/Assets/Code/Systems/"+name+".cs",
		
		all(namespaces,x->"using "+x+";", "\n"),
		"",
		"namespace M",
		"{",
		"	[UpdateInGroup(typeof(Gameplay))]",
		"	public class "+name+" : SystemBase",
		"	{",
		"		"+declareQueries,
		"		protected override void OnCreate()",
		"		{",
		"			"+getQueries,
		"		}",
		"",
		"		protected override void OnUpdate()",
		"		{",
		"			"+statements,
		"		}",
		"	}",
		"}"
		);
	}
	
	private String toArray(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		if (entry.getValue() == TAG)
		{
			return "";
		}
		if (isBuffer(component))
		{
			return "";
		}
		else
		{
			return "var "+component+"s_"+entity+" = "+entity+".ToComponentDataArray<"+component(component)+">(TempJob);";
		}
		
	}
	
	private String dispose(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.getKey();
		if (entry.getValue() == TAG || isBuffer(component))
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
			if (block.getName() == "foreach")
			{
				var a = ((Value)block.getExpression()).getName();
				var query = queries.get(currentFunction).get(a);
				
				return 
				"var entities_"+a+" = "+a+".ToEntityArray(TempJob);\n"+
				all(query.entrySet(), x->toArray(x,a), "\n")+"\n"+
				"\n"+
				"for (var "+a+"_i = 0; "+a+"_i < "+a+".CalculateEntityCount(); "+a+"_i++)\n"+
				"{\n"+
				"	var entity_"+a+" = entities_"+a+"["+a+"_i];\n"+
				"	"+all(query.entrySet(),x->toComponent(x,a), "\n")+"\n"+
				""+	
					all(block.getStatements(), x->code(x), "\n")+"\n"+
				"}\n"+
				"entities_"+a+".Dispose();\n"+
				all(query.entrySet(), x->dispose(x,a), "\n")+"\n";
			}
			
			return "undefined";
		}
		else if (statement instanceof Block)
		{
			var block = (Block) statement;
			
			if (block.getName() == "if")
			{
				var condition = code(block.getExpression());
				return 
					"\n"+
					"if ("+condition+")\n"+
					"{\n"+
					"	"+all(block.getStatements(),x->code(x), "\n")+"\n"+
					"}\n";
			}
			else if (block.getName() == "while")
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
				return code(atom)+" = "+code(expression)+";\n"+
				"EntityManager.SetComponentData(entity_"+cell.getEntity().getName()+", "+cell.getComponent().getName()+"_"+cell.getEntity().getName()+");";
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
		return false;
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
			return value.getName();
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
			
			if (name == "in")
			{
				return code(args.get(1))+".Contains(entity_"+code(args.get(0))+")";
			}
			else if (name == "remove")
			{
				return "EntityManager.RemoveComponent<>(entity_"+code(args.get(0))+")";
			}
			else if (name == "add")
			{
				return "EntityManager.AddComponentData(entity_"+code(args.get(0))+", new ())";
			}
			else
			{
				return application(name)+"("+all(args,x->code(x), ", ")+")";
			}
		}
		return "undefined";
	}
	
	private String component(String name)
	{
		var found = library.components.get(name);
		if (found == null)
		{
			return name;
		}
		else
		{
			switch (found)
			{
				case VELOCITY: {namespaces.add("Unity.Physics"); return "PhysicsVelocity";}
				case TIMEOUT: return "timeout";
				case POSITION: {namespaces.add("Unity.Transforms"); return "Translation";}
				case COLLISIONS: {return "Collisions";}
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
				case VELOCITY: {namespaces.add("Unity.Physics"); return "Linear";}
				case TIMEOUT: return "Value";
				case POSITION: return "Value";
				case COLLISIONS: return "Value";
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
				case RANDOM: { namespaces.add("static M.Library"); return "random";}
				case XYZ: { namespaces.add("static M.Library"); return "xyz";}
				case IN: { namespaces.add("static M.Library"); return "contains";}
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
					namespaces.add("Unity.Entities");
					return "Entity";
				case NUMBER:
					return "float";
				case NUMBER2:
					namespaces.add("Unity.Mathematics");
					return "float2";
				case NUMBER3:
					namespaces.add("Unity.Mathematics");
					return "float3";
				case PROPOSITION:
					return "bool";
				case ENTITY_LIST:
					return "Entity";
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
