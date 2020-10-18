package m.generator;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.Type;
import m.model.Game;
import static m.generator.IO.*;

import java.util.HashSet;
import java.util.Set;
public class Unreal
{
	Library library;
	Set<String> includes;

	public void generate(Game game)
	{
		this.library = game.library;
		this.includes = new HashSet<>();

		game.components.entrySet().removeIf(e-> library.getComponent(e.getKey()) != null);
		game.components.forEach((name, type)->
		{
			writeFile("Components/"+unreserved(name)+".h",
			generateComponent(name,type));

			writeFile("Components/"+unreserved(name)+".cpp", write("#include \""+unreserved(name)+".h\""));
		});
	}

	private String generateComponent(String name, Type type)
	{
		includes.clear();

		var unrealType  = unreal(type);

		return write
		(
			"#pragma once",
			"",
			"#include \"CoreMinimal.h\"",
			"#include \"Components/ActorComponent.h\"",
			foreach(includes, i-> "#include \""+i+"\""),
			"#include \""+unreserved(name)+".generated.h\"",

			"UCLASS( ClassGroup=(Custom), meta=(BlueprintSpawnableComponent) )",
			"class MYPROJECT_API U"+unreserved(name)+" : public UActorComponent",
			"{",
				"GENERATED_BODY()",
				"",
				"public:",
				"UPROPERTY(EditAnywhere,BlueprintReadWrite)",
				unrealType+" Value;",
			"};"
		);
	}

	private String unreserved(String name)
	{
		return name;
	}

	private String unreal(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomic = (AtomicType) type;
			switch (atomic)
			{
			case ENTITY:
				includes.add("GameFramework/Actor.h");
				return "AActor*";
			case NUMBER:
				return "float";
			case NUMBER2:
				includes.add("Math/Vector2D.h");
				return "FVector2";
			case NUMBER3:
				includes.add("Math/Vector.h");
				return "FVector";
			case PROPOSITION:
				return "bool";
			case ENTITY_LIST:
				includes.add("Containers/Array.h");
				includes.add("GameFramework/Actor.h");
				return "TArray<AActor*>";
			case INPUT:
				return "FString";
			case STRING:
				return "FText";
			case COLOR:
				return "FColor";
			case MESH:
				return "UStaticMesh*";
			case MATERIAL:
				includes.add("Materials/MaterialInstance.h");
				return "UMaterialInstance*";
			}
			return "Undefined";
		}
		return "Undefined";
	}
}
