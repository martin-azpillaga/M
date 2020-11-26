package m.generator;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.model.Application;
import m.model.Assignment;
import m.model.Binary;
import m.model.BindingBlock;
import m.model.Block;
import m.model.Cell;
import m.model.Delegation;
import m.model.Expression;
import m.model.Function;
import m.model.Game;
import m.model.Statement;
import m.model.Unary;
import m.model.UserFunction;
import m.model.Value;

import static m.generator.IO.*;
import static m.library.symbols.Function.*;
import static m.library.symbols.Component.*;
import static m.library.types.AtomicType.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.xtext.EcoreUtil2;
public class Unreal
{
	Library library;
	Set<String> includes;
	UserFunction currentFunction;

	public void generate(Game game)
	{
		this.library = game.library;
		this.includes = new HashSet<>();

		clean(Paths.get(getBaseFolder()).toString().replace("file:", ""));

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

	private void clean(String path)
	{
		cleanCppFiles(Paths.get(path,"Components").toString());
		cleanCppFiles(Paths.get(path,"Systems").toString());
	}

	private void cleanCppFiles(String path)
	{
		var directory = new File(path);
		if (!directory.exists())
		{
			return;
		}
		for (var file : directory.listFiles())
		{
			if (file.getName().endsWith(".cpp"))
			{
				file.delete();
			}
			if (file.getName().endsWith((".h")))
			{
				file.delete();
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
			foreach(systems, s->code(s))
		);

		return write
		(
			"#include \"Systems.h\"",
			foreach(includes, i->"#include \""+i+"\""),
			"",
			lines
		);
	}

	private Object code(UserFunction s)
	{
		currentFunction = s;
		return lines
		(
			"void ASystems::"+s.getName()+"(float DeltaTime)",
			"{",
				foreach(s.getStatements(), statement->code(statement)),
			"}"
		);
	}

	private Object code(Statement statement)
	{
		if (statement instanceof BindingBlock)
		{
			var bindingBlock = (BindingBlock) statement;
			var entity = bindingBlock.getExpression().getName();
			var statements = bindingBlock.getStatements();

			var extras = extraComponents(currentFunction);
			var components = new ArrayList<String>();
			if (currentFunction.queries.containsKey(entity))
			{
				components.addAll(currentFunction.queries.get(entity).keySet());
			}
			if (extras.containsKey(entity))
			{
				components.addAll(extras.get(entity));
			}

			return lines
			(
			"for (TActorIterator<AActor> It"+entity+"(GetWorld()); It"+entity+"; ++It"+entity+")",
			"{",
				"auto "+entity+" = *It"+entity+";",
				foreach(components, c->"auto "+entity+"_"+c+" = "+entity+"->FindComponentByClass<"+component(c)+">();"),
				"",
				"if ("+foreach(components, c->entity+"_"+c, " && ")+")",
				"{",
					foreach(statements, s->code(s)),
				"}",
			"}"	
			);
		}
		else if (statement instanceof Block)
		{
			return "";
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();

			if (atom instanceof Value)
			{
				var value = (Value) atom;

				return value.getName()+" = "+code(expression);
			}
			else
			{
				var cell = (Cell) atom;
				var entity = cell.getEntity().getName();
				var component = cell.getComponent().getName();

				return setter(entity,component,expression);
			}
		}
		else if (statement instanceof Delegation)
		{
			return "";
		}
		return "";
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
			return value.getName();
		}
		else if (e instanceof Cell)
		{
			var cell = (Cell) e;
			var component = cell.getComponent().getName();
			var entity = cell.getEntity().getName();

			return entity+"_"+component+field(component);
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
		var x = "";
		var y = "";
		var z = "";

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
		case ADD: return "";
		case ADDITION: return x+" + "+y;
		case AND: return x+" && "+y;
		case ASIN: return "math.asin("+x+")";
		case ASSIGNMENT: return x+" = "+y;
		case ATAN: return "math.atan("+x+")";
		case CEIL: return "math.ceil("+x+")";
		case CLAMP: return "math.clamp("+x+", ("+y+").x, ("+y+").y)";
		case COS: return "math.cos("+x+")";
		case CREATE: return "ecb.Instantiate("+x+")GameObject.Instantiate<GameObject>("+x+")";
		case CROSS: return "((Vector3)math.cross("+x+","+y+"))";
		case DESTROY: return "ecb.DestroyEntity(entity_"+x+")GameObject.Destroy("+x+")";
		case DISTANCE: return "math.distance("+x+", "+y+")";
		case DIVISION: return x+" / "+y;
		case DOT: return "math.dot("+x+", "+y+")";
		case EQUAL: return x+" == "+y;
		case EXP: return "math.exp("+x+")";
		case FLOOR: return "math.floor("+x+")";
		case FRACTIONALPART: return "math.frac("+x+")";
		case GREATER: return x+" > "+y;
		case GREATEROREQUAL: return x+" >= "+y;
		case HALT: return "#if UNITY_EDITOR\nUnityEditor.EditorApplication.isPlaying = false;\n#endif\nApplication.Quit()";
		case HAS: return "";
		case IN: return "("+y+").Contains(entity_"+x+")("+y+").Contains("+x+")";
		case INEQUAL: return x+" != "+y;
		case INTEGERPART: return "math.trunc("+x+")";
		case INVERSE: return "(1 / ("+x+"))";
		case LERP: return "math.lerp("+x+", ("+y+").x, ("+y+").y)";
		case LOG: return "math.log("+x+")";
		case LOWER: return x+" < "+y;
		case LOWEROREQUAL: return x+" <= "+y;
		case MULTIPLICATION: return x+" * "+y;
		case NORM: return "math.length("+x+")";
		case NORMALIZE: return "((Vector3)math.normalize("+x+"))";
		case NOT: return "! ("+x+")";
		case OR: return x+" || "+y;
		case PLAY_ONCE: return "("+x+").GetComponent<AudioSource>().PlayOneShot("+y+")";
		case POW: return "math.pow("+x+", "+y+")";
		case PROPORTIONAL: return "math.remap("+x+", ("+y+").x, ("+y+").y, ("+z+").x, ("+z+").y)";
		case RANDOM: return ("SystemRunner.random.NextFloat(UnityEngine.Random.Range(")+"("+x+").x, ("+x+").y)";
		case READ_NUMBER: return "GetInputAxisValue("+x+")";
		case READ_TRIGGERED: return "("+x+").triggered";
		case READ_VECTOR: return "("+x+").ReadValue<Vector2>()";
		case RECIPROCAL: return "-("+x+")";
		case REFLECT: return "((Vector3)math.reflect("+x+", "+y+"))";
		case REFRACT: return "((Vector3)math.refract("+x+", "+y+", "+z+"))";
		case REMOVE: return "";
		case ROUND: return "math.round("+x+")";
		case SET_COLOR: return "("+x+").SetColor("+y+", "+z+")";
		case SET_NUMBER: return "("+x+").SetFloat("+y+", "+z+")";
		case SET_TRIGGER: return "("+x+").GetComponent<Animator>().SetTrigger("+y+")";
		case SIGN: return "math.sign("+x+")";
		case SIN: return "math.sin("+x+")";
		case SIZE: return "("+x+").Count()";
		case SQRT: return "math.sqrt("+x+")";
		case IN_STATE: return "("+x+").GetComponent<Animator>().GetCurrentAnimatorStateInfo(0).IsName("+y+")";
		case SUBTRACTION: return "("+x+") - ("+y+")";
		case TAN: return "math.tan("+x+")";
		case UNLERP: return "math.unlerp("+x+", ("+y+").x, ("+y+").y)";
		case WRITE: return "if (Debug.isDebugBuild){ Debug.Log("+x+"); }";
		case WRITEERROR: return "if (Debug.isDebugBuild){ Debug.LogError("+x+"); }";
		case WRITE_WARNING: return "if (Debug.isDebugBuild){ Debug.LogWarning("+x+"); }";
		case SCREENSHOT: return "ScreenCapture.CaptureScreenshot((System.DateTime.Now+\".png\").Replace(\"/\", \"-\"), 1)";
		case XYZ: return "new Vector3("+x+", "+y+", "+z+")";
		case OVERLAPS:
			return "";
		case TO_NUMBER3: return "("+x+").eulerAngles";
		case TO_QUATERNION: return "Quaternion.Euler(("+x+").x, ("+x+").y, ("+x+").z)";
		case ADD_FORCE: return "("+x+").GetComponent<Rigidbody>().AddForce("+y+")";
		case ADD_TORQUE: return "("+x+").GetComponent<Rigidbody>().AddTorque("+y+")";
		case CLOSEST_POINT: return "(("+x+").GetComponent<Collider>() != null ? "+x+".GetComponent<Collider>().ClosestPoint("+y+")" + ":" + x+".transform.position)";
		case GET_COLOR: return "("+x+").GetColor("+y+")";
		case GET_INTEGER: return "("+x+").GetInt("+y+")";
		case GET_KEYWORD: return "("+x+").IsKeywordEnabled("+y+")";
		case GET_NUMBER: return "("+x+").GetFloat("+y+")";
		case GET_TEXTURE: return "("+x+").GetTexture("+y+")";
		case SET_INTEGER: return "("+x+".SetInt("+y+", (int)("+z+"))";
		case SET_KEYWORD: return "if ("+z+"){ ("+x+").EnableKeyword("+y+"); }else{ ("+x+").DisableKeyword("+y+"); }";
		case SET_TEXTURE: return "("+x+").SetTexture("+y+", "+z+")";
		case DEGREES: return "math.degrees("+x+")";
		case MAX: return "math.max("+x+", "+y+")";
		case MIN: return "math.min("+x+", "+y+")";
		case RADIANS: return "math.radians("+x+")";
		case SLERP: return "math.slerp("+x+", "+y+", "+z+")";
		case STEP: return "math.step("+x+", "+y+")";
		case BREAKPOINT: return "Debug.Break()";
		case PAUSE: return "("+x+").GetComponent<AudioSource>().Pause()";
		case PLAY: return "("+x+").GetComponent<AudioSource>().Play()";
		case STOP: return "("+x+").GetComponent<AudioSource>().Stop()";
		case UNPAUSE: return x+".GetComponent<AudioSource>().UnPause()";
		case VIEWPORT_TO_WORLD: return "Camera.main.ViewportToWorldPoint("+x+")";
		case WORLD_TO_VIEWPORT: return "Camera.main.WorldToViewportPoint("+x+")";
		case SCREEN_OVERLAPS: return "Physics.RaycastAll(Camera.main.ScreenPointToRay("+x+")).Select(x=>x.transform.gameObject)";
		case X: return "("+x+").x";
		case Y: return "("+x+").y";
		case Z: return "("+x+").z";
		case OVER: return "(EventSystem.current.currentSelectedGameObject == "+x+")";
		case TO_NUMBER: return "float.Parse("+x+", System.Globalization.CultureInfo.InvariantCulture)";
		case TO_STRING: return "("+x+").ToString()";
		case IS_NEGATIVE: return "("+x+" < 0)";
		case IS_POSITIVE: return "("+x+" > 0)";
		case IS_ZERO: return "("+x+" == 0)";
		case ACTIVATE_PARAMETER: return "("+x+").GetComponent<Animator>().SetBool("+y+", true)";
		case DEACTIVATE_PARAMETER: return "("+x+").GetComponent<Animator>().SetBool("+y+", false)";
		case PLAY_ANIMATION: return "("+x+").GetComponent<Animator>().Play("+y+")";
		}
		return "undefined";
	}

	private String setter(String entity, String component, Expression expression)
	{
		var standard = library.getComponent(component);
		if (standard == null)
		{
			return entity+"_"+component+"->Value = "+code(expression)+";";
		}
		else
		{
			switch(standard)
			{
				case VELOCITY: return entity+"_"+component+"->SetAllPhysicsLinearVelocity("+code(expression)+", false);";
			}
			return "";
		}
	}


	private String component(String name)
	{
		var found = library.getComponent(name);

		if (found == null)
		{
			includes.add("Components/"+name+".h");
			return "U"+name+"M";
		}
		else
		{
			switch (found)
			{
			case VELOCITY: return "UPrimitiveComponent";
			case POSITION: return "Transform";
			case ANGULAR_VELOCITY: return "Rigidbody";
			case AUDIOCLIP: return "AudioSource";
			case BACKGROUND: return "Camera";
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
			case TEXT_COLOR:
			case TEXT_MATERIAL:
			case TEXT:
			case FONT: return "Text";
			case IMAGE:
			case IMAGE_COLOR:
			case IMAGE_MATERIAL: return "RawImage";
			case SLIDER_VALUE: return "Slider";
			case TEXTFIELD_VALUE: return "InputField";
			case TOGGLED: return "Toggle";
			case ANCHOR_MIN:
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
			includes.add("Components/"+name+".h");
			return "->Value";
		}
		else
		{
			switch (found)
			{
			case VELOCITY: return "->GetPhysicsLinearVelocity(\"None\")";
			case POSITION: return "Transform";
			case ANGULAR_VELOCITY: return "Rigidbody";
			case AUDIOCLIP: return "AudioSource";
			case BACKGROUND: return "Camera";
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
			case TEXT_COLOR:
			case TEXT_MATERIAL:
			case TEXT:
			case FONT: return "Text";
			case IMAGE:
			case IMAGE_COLOR:
			case IMAGE_MATERIAL: return "RawImage";
			case SLIDER_VALUE: return "Slider";
			case TEXTFIELD_VALUE: return "InputField";
			case TOGGLED: return "Toggle";
			case ANCHOR_MIN:
			case ANCHOR_MAX: return "RectTransform";
			}
		}
		return "undefined";
	}

	private HashMap<String, HashSet<String>> extraComponents(Function function)
	{
		var map = new HashMap<String, HashSet<String>>();

		for (var application : EcoreUtil2.getAllContentsOfType(function, Application.class))
		{
			var name = application.getName();
			var standard = library.getFunction(name);
			if (standard == SET_TRIGGER || standard == IN_STATE || standard == ACTIVATE_PARAMETER || standard == DEACTIVATE_PARAMETER )
			{
				var entity = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(entity))
				{
					map.put(entity, new HashSet<>());
				}
				map.get(entity).add("Animator");
			}
			else if (standard == PLAY || standard == PLAY_ONCE || standard == PAUSE || standard == UNPAUSE || standard == STOP)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<>());
				}
				map.get(a).add("AudioSource");
			}
			else if (standard == ADD_FORCE || standard == ADD_TORQUE)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<>());
				}
				map.get(a).add("Rigidbody");
			}
			else if (standard == OVERLAPS)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<>());
				}
				map.get(a).add("UPrimitiveComponent");
			}
		}

		return map;
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
