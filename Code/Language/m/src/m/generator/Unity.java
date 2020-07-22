package m.generator;

import java.util.*;
import java.util.stream.Collectors;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.library.Library;
import m.m.*;
import m.types.*;

import static m.types.AtomicType.*;

enum AccessKind
{
	read, write, tag
}

public class Unity
{
	IFileSystemAccess2 fileSystem;
	Library library;
	Game game;
	
	Map<String,Map<String,Map<String,AccessKind>>> queries = new HashMap<>();
	Set<String> namespaces = new HashSet<>();
	
	public void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.game = game;
		this.library = game.library;
		this.fileSystem = fileSystem;
		
		for (var component : game.components.entrySet())
		{
			generate(component.getKey(), component.getValue());
		}
		for (var function : game.functions.entrySet())
		{
			var type = function.getValue();
			if (type.getParameters().length == 0 && type.getReturnType() == UNIT)
			{
				generate(function.getKey());
			}
		}
	}
	
	private String all(Set<String> set, java.util.function.Function<String, String> f)
	{
		return String.join("\n", set.stream().map(f).collect(Collectors.toList()));
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
			all(namespaces,x->"using "+x+";"),
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
	
	private void generate(Function function)
	{
		
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
