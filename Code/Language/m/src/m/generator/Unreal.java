package m.generator;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.model.Assignment;
import m.model.BindingBlock;
import m.model.Block;
import m.model.Delegation;
import m.model.Function;
import m.model.Game;
import m.model.Statement;
import m.model.UserFunction;

import static m.generator.IO.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

		writeFile("Systems.h",systemsHeader(game.functions));
		writeFile("Systems.cpp",systemsCpp(game.functions, game.components));

		for (var system : game.functions)
		{
			if (system.type == FunctionType.systemType)
			{
				var name = system.getName();

				writeFile("Systems/"+unreserved(name)+".h", generateSystem(system));
				writeFile("Systems/"+unreserved(name)+".cpp", generateSystem(system));
			}
		}
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

			"UCLASS( ClassGroup=\"M\", meta=(BlueprintSpawnableComponent, DisplayName=\""+unreserved(name)+"\") )",
			"class U"+unreserved(name)+"M : public UActorComponent",
			"{",
				"GENERATED_BODY()",
				"",
				"public:",
				"UPROPERTY(EditAnywhere,BlueprintReadWrite)",
				unrealType+" Value;",
			"};"
		);
	}

	private String systemsHeader(List<UserFunction> systems)
	{
		includes.clear();

		return write
		(
			"#pragma once",
			"#include \"CoreMinimal.h\"",
			"#include \"GameFramework/Pawn.h\"",
			"#include \"Systems.generated.h\"",
			"",
			"UCLASS()",
			"class ASystems : public APawn",
			"{",
				"GENERATED_BODY()",
				"",
				"public:",
				"ASystems();",
				"virtual void SetupPlayerInputComponent(class UInputComponent* PlayerInputComponent) override;",
				"virtual void Tick(float DeltaTime) override;",
				"private:",
				foreach(systems, s->"void "+s.getName()+"(float DeltaTime);"),
			"};"
		);
	}

	private String systemsCpp(List<UserFunction> systems, Map<String, Type> components)
	{
		includes.clear();
		includes.add("EngineUtils.h");

		var inputs = new HashSet<String>();
		components.forEach((name,type)->
		{
			if (type == AtomicType.INPUT)
			{
				inputs.add(name);
			}
		});

		for (var input : inputs)
		{
			includes.add("Components/"+input+".h");
		}
		var lines = lines
		(
			"ASystems::ASystems()",
			"{",
				"PrimaryActorTick.bCanEverTick = true;",
			"}",
			"",
			"void ASystems::SetupPlayerInputComponent(UInputComponent* PlayerInputComponent)",
			"{",
				"Super::SetupPlayerInputComponent(PlayerInputComponent);",
				"",
				"for (TActorIterator<AActor> It(GetWorld()); It; ++It)",
				"{",
					"AActor* Actor = *It;",
					foreach(inputs, i->lines
					(
						"auto "+i+" = Actor->FindComponentByClass<U"+i+"M>();",
						"if ("+i+")",
						"{",
							"PlayerInputComponent->BindAxis("+i+"->Value);",
						"}"
					)),
				"}",
			"}",
			"",
			"void ASystems::Tick(float DeltaTime)",
			"{",
				"Super::Tick(DeltaTime);",
				"",
				foreach(systems, s->s.getName()+"(DeltaTime);"),
			"}",
			foreach(systems, s->lines
			(
			"void ASystems::"+s.getName()+"(float DeltaTime)",
			"{",
				foreach(s.getStatements(), statement->code(statement)),
			"}"
			))
		);

		return write
		(
			"#include \"Systems.h\"",
			foreach(includes, i->"#include \""+i+"\""),
			"",
			lines
		);
	}

	private Object code(Statement statement)
	{
		if (statement instanceof BindingBlock)
		{
			var bindingBlock = (BindingBlock) statement;
			var entity = bindingBlock.getExpression().getName();
			var statements = bindingBlock.getStatements();

			return lines
			(
			"for (TActorIterator<AActor> It"+entity+"(GetWorld()); It"+entity+"; ++It"+entity+")",
			"{",
				"auto "+entity+" = *It"+entity+";",
				foreach(statements, s->code(s)),
			"}"	
			);
		}
		else if (statement instanceof Block)
		{
			return "";
		}
		else if (statement instanceof Assignment)
		{
			return "";
		}
		else if (statement instanceof Delegation)
		{
			return "";
		}
		return "";
	}

	private String generateSystem(Function system)
	{
		return "";
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
				return "FVector2D";
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
				return "FName";
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
