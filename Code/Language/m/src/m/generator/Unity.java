package m.generator;

import static m.generator.Writer.end;
import static m.generator.Writer.foreach;
import static m.generator.Writer.iff;
import static m.generator.Writer.lines;
import static m.generator.Writer.write;
import static m.library.symbols.Block.ITERATION;
import static m.library.symbols.Block.QUERY;
import static m.library.symbols.Block.SELECTION;
import static m.library.symbols.Component.DISPLAY;
import static m.library.symbols.Function.ACTIVATE_PARAMETER;
import static m.library.symbols.Function.ADD_FORCE;
import static m.library.symbols.Function.ADD_TORQUE;
import static m.library.symbols.Function.DEACTIVATE_PARAMETER;
import static m.library.symbols.Function.IN_STATE;
import static m.library.symbols.Function.OVERLAPS;
import static m.library.symbols.Function.PAUSE;
import static m.library.symbols.Function.PLAY;
import static m.library.symbols.Function.PLAY_ONCE;
import static m.library.symbols.Function.SET_TRIGGER;
import static m.library.symbols.Function.STOP;
import static m.library.symbols.Function.UNPAUSE;
import static m.library.types.AtomicType.COLOR;
import static m.library.types.AtomicType.ENTITY;
import static m.library.types.AtomicType.ENTITY_LIST;
import static m.library.types.AtomicType.INPUT;
import static m.library.types.AtomicType.NUMBER;
import static m.library.types.AtomicType.NUMBER2;
import static m.library.types.AtomicType.NUMBER3;
import static m.library.types.AtomicType.PROPOSITION;
import static m.library.types.AtomicType.QUATERNION;
import static m.library.types.AtomicType.STRING;
import static m.library.types.AtomicType.UNIT;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.library.Library;
import m.library.symbols.Component;
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
import m.main.Game;
import m.main.UserFunction;

public class Unity
{
	Game game;
	Library library;
	IFileSystemAccess2 fileSystem;
	
	Set<String> namespaces = new HashSet<>();
	UserFunction currentFunction;
	boolean jobified;
	HashSet<String> variables;
	Stack<HashSet<String>> stack;
	HashMap<Application,String> overlapNames;
	
	final static String[] csharpReserved = new String[]
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



	public void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.game = game;
		this.library = game.getLibrary();
		this.fileSystem = fileSystem;
		this.variables = new HashSet<String>();
		this.stack = new Stack<>();
		this.overlapNames = new HashMap<Application, String>();
		
		resolvePackages();
		
		clean(Paths.get(fileSystem.getURI("").toString(), "Assets", "Code").toString().replace("file:", ""));
		
		fileSystem.generateFile
		(
			"Assets/Code/M.asmdef",
			assemblyDefinition(null)
		);

		for (var component : game.getComponents().entrySet())
		{
			fileSystem.generateFile
			(
				"Assets/Code/Components/"+unreserved(component.getKey())+".cs",
				generateComponent(component.getKey(), component.getValue())
			);
			fileSystem.generateFile
			(
				"Assets/Code/Components/Jobified/"+unreserved(component.getKey())+"Data.cs",
				generateDataComponent(component.getKey(), component.getValue())
			);
		}

		var systems = new ArrayList<UserFunction>();

		for (var function : game.getFunctions())
		{
			var type = function.getType();
			if (type.getParameters() == null && type.getReturnType() == UNIT)
			{
				systems.add(function);
				fileSystem.generateFile
				(
					"Assets/Code/Systems/"+unreserved(function.getName())+".cs",
					generateSystem(function)
				);
			}
		}

		fileSystem.generateFile
		(
			"Assets/Code/Main/SystemRunner.cs",
			systemRunner(systems)
		);

		fileSystem.generateFile
		(
			"Assets/Code/Debugging/SystemDebugger.cs",
			systemDebugger(systems)
		);

		fileSystem.generateFile
		(
			"Assets/Code/Debugging/EntityDebugger.cs",
			entityDebugger(systems)
		);

		fileSystem.generateFile
		(
			"Assets/Code/Main/ConversionSystem.cs",
			conversionSystem()
		);

		fileSystem.generateFile
		(
			"Assets/Code/Fix/FixRectTransforms.cs",
			fixRectTransforms()
		);

		fileSystem.generateFile
		(
			"Assets/Code/Fix/textData.cs",
			generateDataComponent("text", STRING)
		);

		fileSystem.generateFile
		(
			"Assets/Code/Fix/SyncPoint.cs",
			generateSyncPoint()
		);

		jobified = true;
		for (var function : game.getFunctions())
		{
			var type = function.getType();
			if (type.getParameters() == null && type.getReturnType() == UNIT)
			{
				fileSystem.generateFile
				(
					"Assets/Code/Systems/Jobified/"+unreserved(function.getName())+"Jobified.cs",
					generateJobifiedSystem(function)
				);
			}
		}
	}
	
	private void resolvePackages()
	{
		if (fileSystem.isFile("Packages/manifest.json"))
		{
			var manifest = fileSystem.readTextFile("Packages/manifest.json").toString();
			var dependencies = "\"dependencies\": {";
			if (manifest.contains(dependencies))
			{
				var index = manifest.indexOf(dependencies)+dependencies.length();
				var buffer = new StringBuffer(manifest);
				
				if (!manifest.contains("\"com.unity.entities\""))
				{
					buffer.insert(index, "\"com.unity.entities\": \"0.11.1-preview.4\",");
					fileSystem.generateFile("Packages/manifest.json", buffer.toString());
				}
				if (!manifest.contains("\"com.unity.inputsystem\""))
				{
					buffer.insert(index, "\"com.unity.inputsystem\": \"1.0.0\",");
					fileSystem.generateFile("Packages/manifest.json", buffer.toString());
				}
			}
		}
		else
		{
			fileSystem.generateFile("Packages/manifest.json", write
			(
			"",
			"{",
			"\"dependencies\":",
			"{",
				"\"com.unity.entities\": \"0.11.1-preview.4\",",
				"\"com.unity.inputsystem\": \"1.0.0\",",
				"\"com.unity.rendering.hybrid\": \"0.5.2-preview.4\",",
				"\"com.unity.physics\": \"0.4.1-preview\",",
				"\"com.unity.ugui\": \"1.0.0\",",  
				"\"com.unity.modules.ai\": \"1.0.0\",",  
				"\"com.unity.modules.androidjni\": \"1.0.0\",",  
				"\"com.unity.modules.animation\": \"1.0.0\",",  
				"\"com.unity.modules.assetbundle\": \"1.0.0\",",  
				"\"com.unity.modules.audio\": \"1.0.0\",",  
				"\"com.unity.modules.cloth\": \"1.0.0\",",  
				"\"com.unity.modules.director\": \"1.0.0\",",  
				"\"com.unity.modules.imageconversion\": \"1.0.0\",",  
				"\"com.unity.modules.imgui\": \"1.0.0\",",  
				"\"com.unity.modules.jsonserialize\": \"1.0.0\",",  
				"\"com.unity.modules.particlesystem\": \"1.0.0\",",  
				"\"com.unity.modules.physics\": \"1.0.0\",",  
				"\"com.unity.modules.physics2d\": \"1.0.0\",",  
				"\"com.unity.modules.screencapture\": \"1.0.0\",",  
				"\"com.unity.modules.terrain\": \"1.0.0\",",  
				"\"com.unity.modules.terrainphysics\": \"1.0.0\",",  
				"\"com.unity.modules.tilemap\": \"1.0.0\",",  
				"\"com.unity.modules.ui\": \"1.0.0\",",  
				"\"com.unity.modules.uielements\": \"1.0.0\",",  
				"\"com.unity.modules.umbra\": \"1.0.0\",",  
				"\"com.unity.modules.unityanalytics\": \"1.0.0\",",  
				"\"com.unity.modules.unitywebrequest\": \"1.0.0\",",  
				"\"com.unity.modules.unitywebrequestassetbundle\": \"1.0.0\",",  
				"\"com.unity.modules.unitywebrequestaudio\": \"1.0.0\",",  
				"\"com.unity.modules.unitywebrequesttexture\": \"1.0.0\",",  
				"\"com.unity.modules.unitywebrequestwww\": \"1.0.0\",",  
				"\"com.unity.modules.vehicles\": \"1.0.0\",",  
				"\"com.unity.modules.video\": \"1.0.0\",",  
				"\"com.unity.modules.vr\": \"1.0.0\",",  
				"\"com.unity.modules.wind\": \"1.0.0\",",  
				"\"com.unity.modules.xr\": \"1.0.0\"",
				"}",
			"}"
			));
		}
	}

	private String assemblyDefinition(String current)
	{
		if (current == null)
		{
			return new Gson().toJson(new AssemblyDefinition
			(
				"M",
				ImmutableList.of
				(
					"Unity.Entities",
					"Unity.Transforms",
					"Unity.Physics",
					"Unity.Mathematics",
					"Unity.Jobs",
					"Unity.Collections",
					"Unity.InputSystem",
					"Unity.Burst",
					"Unity.Entities.Hybrid"
				),
				true
			));
		}
		else
		{
			return current;
		}
	}
	
	
	
	
	
	private String generateComponent(String name, Type type)
	{
		namespaces.clear();
		namespaces.add("UnityEngine");

		var lines = lines
		(
			"namespace M",
			"{",
				"public class "+unreserved(name)+" : MonoBehaviour",
				"{",
					iff(type != UNIT),
					"public "+unity(type)+" Value;",
					end,

					iff(type == INPUT),
					"",
					"void Awake()",
					"{",
						"if (Value != null)",
						"{",
							"Value.Enable();",
						"}",
					"}",
					end,
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			"",
			lines
		);
	}

	private String generateDataComponent(String name, Type type)
	{
		namespaces.clear();
		namespaces.add("Unity.Entities");

		var classifier = valueType(type) ? "struct" : "class";

		var lines = lines
		(
			"namespace M",
			"{",
				"public "+classifier+" "+name+"Data : IComponentData",
				"{",
					iff(type == ENTITY_LIST),
					"public List<Entity> Value;",
					end,
					
					iff(type != UNIT && type != ENTITY_LIST),
					"public "+unity(type)+" Value;",
					end,
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			lines
		);
	}

	private String systemDebugger(List<UserFunction> systems)
	{
		namespaces.clear();

		var lines = lines
		(
			"using UnityEngine;",
			"using Unity.Entities;",
			"using System.Collections.Generic;",
			"using System.Linq;",
			"",
			"namespace M",
			"{",
				"public class SystemDebugger : MonoBehaviour",
				"{",
					"public bool dataOriented;",
					"public bool multithread;",
					"",
					foreach(systems, s->lines
					(
						"public bool "+s.getName()+" = true;",
						foreach(s.getQueries().keySet(), q->"[SerializeField] List<GameObject> "+s.getName()+"_"+q+" = new List<GameObject>();"),
						iff(!systems.isEmpty() && s != systems.get(systems.size()-1)),
						"[Space]",
						end
					)),
					"",
					"void Start()",
					"{",
						"if (dataOriented)",
						"{",
							"var gameObjects = GameObject.FindObjectsOfType<Transform>();",
							"foreach (var t in gameObjects)",
							"{",
								"if (t.gameObject.name != \"New Game Object\")",
								"{",
									"t.gameObject.AddComponent<ConvertToEntity>();",
								"}",
							"}",
						"}",
					"}",
					"",
					"void Update()",
					"{",
						foreach(systems, s->lines
						(
							foreach(s.getQueries().entrySet(), q->lines
							(
								s.getName()+"_"+q.getKey()+".Clear();"
							))
						)),
						"",
						"var gameObjects = GameObject.FindObjectsOfType<Transform>().Select(x=>x.gameObject).ToList();",
						"",
						"foreach (var go in gameObjects)",
						"{",
							foreach(systems, s->lines
							(
								foreach(s.getQueries().entrySet(), q->lines
								(
									"if ("+foreach(q.getValue().keySet(), c->"go.GetComponent<"+component(c)+">()", " && ")+")",
									"{",
										s.getName()+"_"+q.getKey()+".Add(go);",
									"}"
								))
							)),
						"}",
					"}",
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			lines
		);
	}

	private String entityDebugger(List<UserFunction> systems)
	{
		var lines = lines
		(
			"using UnityEngine;",
			"using UnityEditor;",
			"using System;",
			"using System.Collections.Generic;",
			"using static M.Query;",
			"",
			"namespace M",
			"{",
				"public class EntityDebugger : MonoBehaviour",
				"{",
				"}",
				"",
				"[CustomEditor(typeof(EntityDebugger))]",
				"public class EntityDebuggerInspector : Editor",
				"{",
					"string[] options = Enum.GetNames(typeof(Query));",
					"Dictionary<Query, List<Type>> types = new Dictionary<Query, List<Type>>()",
					"{",
						foreach(systems, s->lines
						(
							foreach(s.getQueries().keySet(), q->"{"+s.getName()+"_"+q+", new List<Type>{"+foreach(s.getQueries().get(q).keySet(),c->"typeof("+component(c)+")",", ")+"}},")
						)),
					"};",
					"int selected;",
					"",
					"public override void OnInspectorGUI()",
					"{",
						"var debugger = (EntityDebugger) target;",
						"var go = debugger.gameObject;",
						"",
						"EditorGUILayout.LabelField(\"Participates in\");",
						"",
						"foreach (var query in types.Keys)",
						"{",
							"var list = types[query];",
							"",
							"var missingComponent = false;",
							"var i = 0;",
							"",
							"while (!missingComponent && i < list.Count)",
							"{",
								"var type = list[i];",
								"if (go.GetComponent(type) == null)",
								"{",
									"missingComponent = true;",
								"}",
								"i++;",
							"}",
							"",
							"if (!missingComponent)",
							"{",
								"var typeNames = \"\";",
								"foreach (var type in list)",
								"{",
									"typeNames += type.Name;",
									"if (type != list[list.Count-1])",
									"{",
										"typeNames += \", \";",
									"}",
								"}",
								"EditorGUILayout.LabelField(query.ToString()+\" : \"+typeNames);",
							"}",
						"}",
						"",
						"EditorGUILayout.LabelField(\"\");",
						"",
						"selected = EditorGUILayout.Popup(\"Select\", selected, options);",
						"",
						"EditorGUILayout.LabelField(\"Missing components\");",
						"",
						"var selectedQuery = (Query) selected;",
						"var selectedList = types[selectedQuery];",
						"",
						"var missing = \"\";",
						"foreach (var type in selectedList)",
						"{",
							"if (go.GetComponent(type) == null)",
							"{",
								"missing += type.Name;",
								"if (type != selectedList[selectedList.Count-1])",
								"{",
									"missing += \", \";",
								"}",
							"}",
						"}",
						"EditorGUILayout.LabelField(missing);",
						"",
						"if (GUILayout.Button(\"Add components\"))",
						"{",
							"foreach (var type in selectedList)",
							"{",
								"if (go.GetComponent(type) == null)",
								"{",
									"go.AddComponent(type);",
								"}",
							"}",
						"}",
					"}",
				"}",
				"",
				"enum Query",
				"{",
					foreach(systems, s->foreach(s.getQueries().keySet(),q->s.getName()+"_"+q+","," ")),
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			lines
		);
	}

	private String systemRunner(List<UserFunction> systems)
	{
		return write
		(
			"using UnityEngine;",
			"using Unity.Entities;",
			"using System.Linq;",
			"",
			"namespace M",
			"{",
				"public class SystemRunner : SystemBase",
				"{",
					"public static bool multithread;",
					"public static Unity.Mathematics.Random random;",
					"",
					foreach(systems, s->s.getName()+" "+s.getName()+";"),
					"",
					"protected override void OnCreate()",
					"{",
						"random = new Unity.Mathematics.Random((uint)UnityEngine.Random.Range(1,100000));",
						foreach(systems, s->s.getName()+" = new "+s.getName()+"();"),
					"}",
					"",
					"protected override void OnUpdate()",
					"{",
						"var gameObjects = GameObject.FindObjectsOfType<Transform>().Select(x=>x.gameObject).ToList();",
						"",
						"var debuggers = GameObject.FindObjectsOfType<SystemDebugger>().ToList();",
						"",
						"if (debuggers.Count == 0)",
						"{",
							foreach(systems, s->s.getName()+".Run(gameObjects);"),
						"}",
						"else if (debuggers.Count == 1)",
						"{",
							"var debugger = debuggers[0];",
							"",
							"multithread = debugger.multithread;",
							"",
							foreach(systems, s->lines
							(
								"if (!multithread && debugger."+s.getName()+")",
								"{",
									s.getName()+".Run(gameObjects);",	
								"}"
							)),
						"}",
						"else",
						"{",
							"Debug.LogError(\"Multiple system debuggers detected. Leave one debugger at maximum\");",
							"#if UNITY_EDITOR",
							"UnityEditor.EditorApplication.isPaused = true;",
							"#endif",
						"}",
					"}",
				"}",
			"}"
		);
	}

	private String conversionSystem()
	{
		return write
		(
			"using System;",
			"using System.Collections.Generic;",
			"using UnityEngine;",
			"using Unity.Entities;",
			"using UnityEngine.UI;",
			"using Unity.Transforms;",
			"",
			"[UpdateInGroup(typeof(GameObjectAfterConversionGroup))]",
			"public class ConversionSystem : GameObjectConversionSystem",
			"{",
				"public static Dictionary<Entity, Entity> parents = new Dictionary<Entity, Entity>();",
				"",
				"protected override void OnUpdate()",
				"{",
					"Entities.ForEach((Transform root) =>",
					"{",
						"Process(root);",
					"});",
					"",
					"Entities.ForEach((RectTransform root) =>",
					"{",
						"Process(root);",
						"",
						"var canvas = root.GetComponent<Canvas>();",
						"if (canvas)",
						"{",
							"DstEntityManager.SetComponentData(GetPrimaryEntity(canvas), new Translation{Value = Vector3.zero});",
						"}",
						"else",
						"{",
							"parents.Add(GetPrimaryEntity(root), GetPrimaryEntity(root.parent));",
						"}",
					"});",
					"Entities.ForEach((Text text) =>",
					"{",
						"var entity = GetPrimaryEntity(text);",
						"",
						"DstEntityManager.AddComponentData(entity, new M.textData{Value = text.text});",
					"});",
				"}",
				"",
				"protected void Process(Component root)",
				"{",
					"var entity = GetPrimaryEntity(root);",
					"",
					foreach(game.getComponents().entrySet(), e->lines
					(
					"var "+unreserved(e.getKey())+" = root.GetComponent<"+component(e.getKey())+">();",
					"if ("+unreserved(e.getKey())+")",
					"{",
						iff(e.getValue() == ENTITY_LIST),
						"var list = new List<Entity>();",
						"foreach (var gameObject in "+unreserved(e.getKey())+".Value)",
						"{",
							"list.Add(GetPrimaryEntity(gameObject));",
						"}",
						"DstEntityManager.AddComponentData(entity, new "+component(e.getKey())+"Data{ Value = list });",
						end,
						iff(e.getValue() != ENTITY_LIST),
						"DstEntityManager.AddComponentData(entity, new "+component(e.getKey())+"Data{ Value = "+unreserved(e.getKey())+".Value });",
						end,
					"}"
					)),
					"",
					"Type[] engineTypes  = ",
					"{",
						"typeof(RectTransform),",
						"typeof(Canvas),",
						"typeof(CanvasScaler),",
						"typeof(Text),",
						"typeof(GraphicRaycaster),",
						"typeof(Camera),",
						"typeof(CanvasRenderer),",
					"};",
					"",
					"foreach (var type in engineTypes)",
					"{",
						"var component = root.GetComponent(type);",
						"if (component)",
						"{",
							"AddHybridComponent(component);",
						"}",
					"}",
				"}",
			"}"
		);
	}

	private String fixRectTransforms()
	{
		return write
		(
			"using UnityEngine;",
			"using Unity.Entities;",
			"using Unity.Collections;",
			"",
			"namespace M",
			"{",
				"public class FixRectTransforms : SystemBase",
				"{",
					"EntityQuery query;",
					"",
					"protected override void OnCreate()",
					"{",
						"query = GetEntityQuery(ComponentType.ReadWrite<RectTransform>());",
					"}",
					"",
					"protected override void OnStartRunning()",
					"{",
						"var entities = query.ToEntityArray(Allocator.Temp);",
						"",
						"foreach (var entity in entities)",
						"{",
							"if (ConversionSystem.parents.ContainsKey(entity))",
							"{",
								"EntityManager.GetComponentObject<RectTransform>(entity).SetParent(EntityManager.GetComponentObject<RectTransform>(ConversionSystem.parents[entity]));",
							"}",
						"}",
						"",
						"entities.Dispose();",
					"}",
					"",
					"protected override void OnUpdate()",
					"{",
						"",
					"}",
				"}",
			"}"
		);
	}

	private String generateSyncPoint()
	{
		return write
		(
			"using Unity.Entities;",
			"using UnityEngine.UI;",
			"",
			"namespace M",
			"{",
				"[UpdateInGroup(typeof(PresentationSystemGroup))]",
				"public class SyncPoint : SystemBase",
				"{",
					"EntityQuery query;",
					"",
					"protected override void OnCreate()",
					"{",
						"query = GetEntityQuery(ComponentType.ReadOnly<M.textData>(), ComponentType.ReadWrite<Text>());",
					"}",
					"protected override void OnUpdate()",
					"{",
						"var datas = query.ToComponentDataArray<M.textData>();",
						"var texts = query.ToComponentArray<Text>();",
						"",
						"for (var i = 0; i < datas.Length; i++)",
						"{",
							"texts[i].text = datas[i].Value;",
						"}",
					"}",
				"}",
			"}"
		);
	}

	private String generateJobifiedSystem(UserFunction function)
	{
		var constants = new HashMap<String,Type>();
		
		var values = EcoreUtil2.getAllContentsOfType(function, Value.class);
		for (var value : values)
		{
			var standard = library.getValue(value.getName());
			if (standard != null)
			{
				constants.put(value.getName(), standard.getType());
			}
		}

		var extraComponents = extraComponents(function);
		var queries = function.getQueries();

		for (var entry : extraComponents.entrySet())
		{
			var entity = entry.getKey();
			if (!queries.containsKey(entity))
			{
				queries.put(entity, new HashMap<String,Boolean>());
			}
			for (var component : entry.getValue())
			{
				queries.get(entity).put(component, true);
			}
		}

		var nativeComponents = new HashMap<String,Boolean>();
		var hybridComponents = new HashMap<String,Boolean>();

		for (var componentSet : function.getQueries().values())
		{
			for (var entry : componentSet.entrySet())
			{
				if (valueType(entry.getKey()))
				{
					if (nativeComponents.containsKey(entry.getKey()))
					{
						nativeComponents.put(entry.getKey(), nativeComponents.get(entry.getKey()) || entry.getValue());
					}
					else
					{
						nativeComponents.put(entry.getKey(), entry.getValue());
					}
				}
				else
				{
					if (hybridComponents.containsKey(entry.getKey()))
					{
						hybridComponents.put(entry.getKey(), hybridComponents.get(entry.getKey()) || entry.getValue());
					}
					else
					{
						hybridComponents.put(entry.getKey(), entry.getValue());
					}					
				}
			}
		}

		namespaces.clear();
		namespaces.add("UnityEngine");
		namespaces.add("Unity.Entities");
		namespaces.add("Unity.Jobs");
		namespaces.add("Unity.Collections");
		namespaces.add("Unity.Burst");
		namespaces.add("static Unity.Collections.Allocator");
		namespaces.add("static Unity.Entities.ComponentType");
		
		currentFunction = function;
		overlapNames = new HashMap<Application,String>();

		var overlaps = overlaps(function);

		if (overlaps.size() != 0)
		{
			namespaces.add("Unity.Mathematics");
			namespaces.add("Unity.Physics");
			namespaces.add("Unity.Physics.Systems");
		}

		var lines = lines
		(
			"namespace M",
			"{",
				"public class "+unreserved(function.getName())+"Jobified : SystemBase",
				"{",
					foreach(function.getQueries().keySet(), q->"EntityQuery "+q+";"),
					
					iff(overlaps.size() != 0),
					"",
					"BuildPhysicsWorld physics;",
					end,
					"",
					"protected override void OnCreate()",
					"{",
						foreach(function.getQueries().entrySet(), e->
						e.getKey()+" = GetEntityQuery("+foreach(e.getValue().entrySet(), c->"Read"+(c.getValue()?"Write":"Only")+"<"+jobComponent(c.getKey())+">()", ", ")+");"),
						iff(overlaps.size() != 0),
						"",
						"physics = World.DefaultGameObjectInjectionWorld.GetExistingSystem<BuildPhysicsWorld>();",
						end,
					"}",
					"",
					"protected override void OnUpdate()",
					"{",
						"var job = new RegularJob",
						"{",
							"manager = EntityManager,",
							"entityType = GetArchetypeChunkEntityType(),",
							foreach(constants.keySet(), c->c+" = "+variable(c)+","),
							foreach(function.getQueries().keySet(), q->"chunks_"+q+" = "+q+".CreateArchetypeChunkArray(TempJob),"),
							foreach(nativeComponents.entrySet(), e->e.getKey()+"Type = GetArchetypeChunkComponentType<"+jobComponent(e.getKey())+">("+e.getValue()+"),"),
							iff(overlaps.size() != 0),
							"collisionWorld = physics.PhysicsWorld.CollisionWorld,",
							end,
						"};",
						"",
						"if (SystemRunner.multithread)",
						"{",
							"job.Schedule();",
						"}",
						"else",
						"{",
							"job.Run();",
						"}",
					"}",
					"",
					"[BurstCompile]",
					"protected struct RegularJob : IJob",
					"{",
						"public EntityManager manager;",
						"public ArchetypeChunkEntityType entityType;",
						foreach(constants.entrySet(), e->"public "+unity(e.getValue())+" "+e.getKey()+";"),
						foreach(function.getQueries().keySet(), q->"[DeallocateOnJobCompletion] public NativeArray<ArchetypeChunk> chunks_"+q+";"),
						foreach(nativeComponents.entrySet(), e->(e.getValue()?"":"[ReadOnly] ")+"public ArchetypeChunkComponentType<"+jobComponent(e.getKey())+"> "+e.getKey()+"Type;"),
						iff(overlaps.size() != 0),
						"public CollisionWorld collisionWorld;",
						end,
						"",
						(overlaps.size() != 0 ? "unsafe " : "")+"public void Execute()",
						"{",
							foreach(function.getStatements(), s->code(s)),
						"}",
					"}",
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			"",
			lines
		);
	}

	private String generateSystem(UserFunction function)
	{
		namespaces.clear();
		namespaces.add("UnityEngine");
		namespaces.add("System.Collections.Generic");

		currentFunction = function;
		overlapNames = new HashMap<Application,String>();

		var lines = lines
		(
			"namespace M",
			"{",
				"public class "+unreserved(function.getName()),
				"{",
					"public void Run(List<GameObject> gameObjects)",
					"{",
						foreach(function.getStatements(), s->code(s)),
					"}",
				"}",
			"}"
		);

		return write
		(
			foreach(namespaces, n->"using "+n+";"),
			"",
			lines
		);
	}

	private ArrayList<Application> overlaps(EObject o)
	{
		var list = new ArrayList<Application>();
		for (var application : EcoreUtil2.getAllContentsOfType(o, Application.class))
		{
			if (library.getFunction(application.getName()) == OVERLAPS)
			{
				list.add(application);
			}
		}
		return list;
	}
	
	private Object code(Statement statement)
	{
		Object result = "undefined";
		var detectedNow = false;

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
				if (currentFunction.getQueries().containsKey(a))
				{
					components.addAll(currentFunction.getQueries().get(a).keySet());
				}
				if (extras.containsKey(a))
				{
					components.addAll(extras.get(a));
				}
				
				variables.add(a);

				List<Object> lines = null;

				if (jobified)
				{
					var nativeComponents = new HashSet<String>();
					var hybridComponents = new HashSet<String>();

					for (var component : currentFunction.getQueries().get(a).keySet())
					{
						if (valueType(component))
						{
							nativeComponents.add(component);
						}
						else
						{
							hybridComponents.add(component);
						}
					}

					lines = lines
					(
						"for (var c_"+a+" = 0; c_"+a+" < chunks_"+a+".Length; c_"+a+"++)",
						"{",
							"var chunk_"+a+" = chunks_"+a+"[c_"+a+"];",
							"var entityCount_"+a+" = chunk_"+a+".Count;",
							"",
							"var entityArray_"+a+" = chunk_"+a+".GetNativeArray(entityType);",
							foreach(nativeComponents, c->"var "+c+"Array_"+a+" = chunk_"+a+".GetNativeArray("+c+"Type);"),
							"",
							"for (var e_"+a+" = 0; e_"+a+" < entityCount_"+a+"; e_"+a+"++)",
							"{",
								"var entity_"+a+" = entityArray_"+a+"[e_"+a+"];",
								foreach(nativeComponents, c->"var "+c+"_"+a+" = "+c+"Array_"+a+"[e_"+a+"];"),
								foreach(hybridComponents, c->"var "+c+"_"+a+" = manager.GetComponentObject<"+jobComponent(c)+">(entity_"+a+");"),
								"",
								foreach(block.getStatements(), s->code(s)),
							"}",
						"}"
					);
				}
				else
				{
					lines = lines
					(
					"foreach (var "+a+" in gameObjects)",
					"{",
						foreach(components, c->"var "+c+"_"+a+" = "+a+".GetComponent<"+component(c)+">();"),
						"",
						iff(!components.isEmpty()),
						"if ("+foreach(components, c->c+"_"+a, " && ")+")",
						"{",
						end,
							foreach(block.getStatements(), s->code(s)),
						iff(!components.isEmpty()),
						"}",
						end,
					"}"
					);
				}

				variables = stack.pop();
				
				result = lines;
			}
			else
			{
				result = "undefined";
			}
		}
		else if (statement instanceof Block)
		{
			stack.push(new HashSet<String>(variables));
			var block = (Block) statement;
			var name = block.getName();
			
			if (library.getBlock(name) == SELECTION)
			{
				var overlaps = overlaps(block.getExpression());
				for (var overlap : overlaps)
				{
					detectedNow = true;
					overlapNames.put(overlap, "collisions_"+new Random().nextInt(1000000));
				}

				var condition = code(block.getExpression());
				
				result = lines
				(
					"if ("+condition+")",
					"{",
						foreach(block.getStatements(), s->code(s)),
					"}"
				);
			}
			else if (library.getBlock(name) == ITERATION)
			{
				var overlaps = overlaps(block.getExpression());
				for (var overlap : overlaps)
				{
					detectedNow = true;
					overlapNames.put(overlap, "collisions_"+new Random().nextInt(1000000));
				}

				var condition = code(block.getExpression());
				
				for (var overlap : overlaps)
				{
					detectedNow = true;
					overlapNames.put(overlap, "collisions_"+new Random().nextInt(1000000));
				}
				result = lines
				(
					"while ("+condition+")",
					"{",
						foreach(block.getStatements(), s->code(s)),
					"}"
				);
			}
			variables = stack.pop();			
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();
			var code = code(expression);

			var overlaps = overlaps(expression);
			for (var overlap : overlaps)
			{
				detectedNow = true;
				overlapNames.put(overlap, "collisions_"+new Random().nextInt(1000000));
			}
			
			if (atom instanceof Value)
			{
				var value = (Value) atom;
				var name = value.getName();
				if (variables.contains(name))
				{
					result = code(atom)+" = "+code+";";
				}
				else
				{
					variables.add(name);
					result = "var "+code(atom)+" = "+code+";";
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
				
				if (currentFunction.getQueries().containsKey(entity))
				{
					if (jobified)
					{
						result = lines
						(
							code(atom)+" = "+code+";",
							iff(valueType(component)),
							component+"Array_"+entity+"[e_"+entity+"] = "+component+"_"+entity+";",
							end
						);
					}
					else
					{
						result = code(atom)+" = "+code+";";
					}
				}
				else
				{
					result = entity+".GetComponent<"+component(component)+">()."+field(component)+" = "+code+";";
				}
			}
		}
		else if (statement instanceof Delegation)
		{
			var delegation = (Delegation) statement;
			var application = delegation.getApplication();

			var overlaps = overlaps(application);
			for (var overlap : overlaps)
			{
				detectedNow = true;
				overlapNames.put(overlap, "collisions_"+new Random().nextInt(1000000));
			}

			result = code(application)+";";
		}

		if (jobified && detectedNow)
		{
			result = lines
			(
				foreach(overlapNames.entrySet(), e->lines
				(
					"var "+e.getValue()+" = new NativeList<ColliderCastHit>(Allocator.Temp);",
					"collisionWorld.CastCollider(new ColliderCastInput",
					"{",
						"Collider = PhysicsCollider_"+((Value)e.getKey().getArguments().get(0)).getName()+".ColliderPtr,",
						"Orientation = quaternion.identity,",
						"Start = Translation_"+((Value)e.getKey().getArguments().get(0)).getName()+".Value,",
						"End = Translation_"+((Value)e.getKey().getArguments().get(0)).getName()+".Value,",
					"}, ref "+e.getValue()+");"
				)),
				result,
				foreach(overlapNames.values(), o->o+".Dispose();")
			);
		}

		return result;
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
			if (jobified && standard == OVERLAPS)
			{
				namespaces.add("System.Linq");
				var nativeList = overlapNames.get(e);
				return nativeList+".AsArray().Select(x => x.Entity).ToList()";
			}
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
		case ABS: namespaces.add("Unity.Mathematics"); return "math.abs("+x+")";
		case ACOS: namespaces.add("Unity.Mathematics"); return "math.acos("+x+")";
		case ADD: return "if (("+y+").GetComponent<"+simpleComponent(x)+">() == null){("+y+").AddComponent<"+simpleComponent(x)+">();"+"}";
		case ADDITION: return x+" + "+y;
		case AND: return x+" && "+y;
		case ASIN: namespaces.add("Unity.Mathematics"); return "math.asin("+x+")";
		case ASSIGNMENT: return x+" = "+y;
		case ATAN: namespaces.add("Unity.Mathematics"); return "math.atan("+x+")";
		case CEIL: namespaces.add("Unity.Mathematics"); return "math.ceil("+x+")";
		case CLAMP: namespaces.add("Unity.Mathematics"); return "math.clamp("+x+", ("+y+").x, ("+y+").y)";
		case COS: namespaces.add("Unity.Mathematics"); return "math.cos("+x+")";
		case CREATE: return "GameObject.Instantiate<GameObject>("+x+")";
		case CROSS: namespaces.add("Unity.Mathematics"); return "((Vector3)math.cross("+x+","+y+"))";
		case DESTROY: return "GameObject.Destroy("+x+")";
		case DISTANCE: namespaces.add("Unity.Mathematics"); return "math.distance("+x+", "+y+")";
		case DIVISION: return x+" / "+y;
		case DOT: namespaces.add("Unity.Mathematics"); return "math.dot("+x+", "+y+")";
		case EQUAL: return x+" == "+y;
		case EXP: namespaces.add("Unity.Mathematics"); return "math.exp("+x+")";
		case FLOOR: namespaces.add("Unity.Mathematics"); return "math.floor("+x+")";
		case FRACTIONALPART: namespaces.add("Unity.Mathematics"); return "math.frac("+x+")";
		case GREATER: return x+" > "+y;
		case GREATEROREQUAL: return x+" >= "+y;
		case HALT: return "#if UNITY_EDITOR\nUnityEditor.EditorApplication.isPlaying = false;\n#endif\nApplication.Quit()";
		case HAS: return "(("+y+").GetComponent<"+simpleComponent(x)+">() != null)";
		case IN: return jobified ? "("+y+").Contains(entity_"+x+")" : "("+y+").Contains("+x+")";
		case INEQUAL: return x+" != "+y;
		case INTEGERPART: namespaces.add("Unity.Mathematics"); return "math.trunc("+x+")";
		case INVERSE: return "(1 / ("+x+"))";
		case LERP: namespaces.add("Unity.Mathematics"); return "math.lerp("+x+", ("+y+").x, ("+y+").y)";
		case LOG: namespaces.add("Unity.Mathematics"); return "math.log("+x+")";
		case LOWER: return x+" < "+y;
		case LOWEROREQUAL: return x+" <= "+y;
		case MULTIPLICATION: return x+" * "+y;
		case NORM: namespaces.add("Unity.Mathematics"); return "math.length("+x+")";
		case NORMALIZE: namespaces.add("Unity.Mathematics"); return "((Vector3)math.normalize("+x+"))";
		case NOT: return "! ("+x+")";
		case OR: return x+" || "+y;
		case PLAY_ONCE: return "("+x+").GetComponent<AudioSource>().PlayOneShot("+y+")";
		case POW: namespaces.add("Unity.Mathematics"); return "math.pow("+x+", "+y+")";
		case PROPORTIONAL: namespaces.add("Unity.Mathematics"); return "math.remap("+x+", ("+y+").x, ("+y+").y, ("+z+").x, ("+z+").y)";
		case RANDOM: return (jobified ? "SystemRunner.random.NextFloat(" : "UnityEngine.Random.Range(")+"("+x+").x, ("+x+").y)";
		case READ_NUMBER: return "("+x+").ReadValue<float>()";
		case READ_TRIGGERED: return "("+x+").triggered";
		case READ_VECTOR: return "("+x+").ReadValue<Vector2>()";
		case RECIPROCAL: return "-("+x+")";
		case REFLECT: namespaces.add("Unity.Mathematics"); return "((Vector3)math.reflect("+x+", "+y+"))";
		case REFRACT: namespaces.add("Unity.Mathematics"); return "((Vector3)math.refract("+x+", "+y+", "+z+"))";
		case REMOVE: return "if (("+y+").GetComponent<"+simpleComponent(x)+">() != null){ GameObject.Destroy(("+y+").GetComponent<"+simpleComponent(x)+">());}";
		case ROUND: namespaces.add("Unity.Mathematics"); return "math.round("+x+")";
		case SET_COLOR: return "("+x+").SetColor("+y+", "+z+")";
		case SET_NUMBER: return "("+x+").SetFloat("+y+", "+z+")";
		case SET_TRIGGER: return "("+x+").GetComponent<Animator>().SetTrigger("+y+")";
		case SIGN: namespaces.add("Unity.Mathematics"); return "math.sign("+x+")";
		case SIN: namespaces.add("Unity.Mathematics"); return "math.sin("+x+")";
		case SIZE: return "("+x+").Count()";
		case SQRT: namespaces.add("Unity.Mathematics"); return "math.sqrt("+x+")";
		case IN_STATE: return "("+x+").GetComponent<Animator>().GetCurrentAnimatorStateInfo(0).IsName("+y+")";
		case SUBTRACTION: return "("+x+") - ("+y+")";
		case TAN: namespaces.add("Unity.Mathematics"); return "math.tan("+x+")";
		case UNLERP: namespaces.add("Unity.Mathematics"); return "math.unlerp("+x+", ("+y+").x, ("+y+").y)";
		case WRITE: return "if (Debug.isDebugBuild){ Debug.Log("+x+"); }";
		case WRITEERROR: return "if (Debug.isDebugBuild){ Debug.LogError("+x+"); }";
		case WRITE_WARNING: return "if (Debug.isDebugBuild){ Debug.LogWarning("+x+"); }";
		case SCREENSHOT: return "ScreenCapture.CaptureScreenshot((System.DateTime.Now+\".png\").Replace(\"/\", \"-\"), 1)";
		case XYZ: return "new Vector3("+x+", "+y+", "+z+")";
		case OVERLAPS:
			if (jobified)
			{
				
			}
			else
			{
				namespaces.add("System.Linq");
				namespaces.add("System");
				return "("+x+").GetComponents<Collider>().Select(x=> x is BoxCollider ? Physics.OverlapBox((x as BoxCollider).bounds.center, Vector3.Scale((x as BoxCollider).size/2,"+x+".transform.lossyScale), "+x+".transform.rotation, Int32.MaxValue, QueryTriggerInteraction.Collide): x is SphereCollider ? Physics.OverlapSphere((x as SphereCollider).bounds.center, (x as SphereCollider).radius*Mathf.Max("+x+".transform.lossyScale.x, Mathf.Max("+x+".transform.lossyScale.y, "+x+".transform.lossyScale.z)), Int32.MaxValue, QueryTriggerInteraction.Collide) : null).Aggregate(new List<Collider>(), (list, x) => {list.AddRange(x); return list;}).Select(x=>x.transform.gameObject).ToList()";
			}
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
		case DEGREES: namespaces.add("Unity.Mathematics"); return "math.degrees("+x+")";
		case MAX: namespaces.add("Unity.Mathematics"); return "math.max("+x+", "+y+")";
		case MIN: namespaces.add("Unity.Mathematics"); return "math.min("+x+", "+y+")";
		case RADIANS: namespaces.add("Unity.Mathematics"); return "math.radians("+x+")";
		case SLERP: namespaces.add("Unity.Mathematics"); return "math.slerp("+x+", "+y+", "+z+")";
		case STEP: namespaces.add("Unity.Mathematics"); return "math.step("+x+", "+y+")";
		case BREAKPOINT: return "Debug.Break()";
		case PAUSE: return "("+x+").GetComponent<AudioSource>().Pause()";
		case PLAY: return "("+x+").GetComponent<AudioSource>().Play()";
		case STOP: return "("+x+").GetComponent<AudioSource>().Stop()";
		case UNPAUSE: return x+".GetComponent<AudioSource>().UnPause()";
		case VIEWPORT_TO_WORLD: return "Camera.main.ViewportToWorldPoint("+x+")";
		case WORLD_TO_VIEWPORT: return "Camera.main.WorldToViewportPoint("+x+")";
		case SCREEN_OVERLAPS: namespaces.add("System.Linq"); return "Physics.RaycastAll(Camera.main.ScreenPointToRay("+x+")).Select(x=>x.transform.gameObject)";
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
	
	private HashMap<String, HashSet<String>> extraComponents(Function function)
	{
		var map = new HashMap<String, HashSet<String>>();
		
		for (var application : EcoreUtil2.getAllContentsOfType(function, Application.class))
		{
			var name = application.getName();
			var standard = game.getLibrary().getFunction(name);
			if (standard == SET_TRIGGER || standard == IN_STATE || standard == ACTIVATE_PARAMETER || standard == DEACTIVATE_PARAMETER )
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
			else if (jobified && standard == OVERLAPS)
			{
				var a = ((Value)application.getArguments().get(0)).getName();
				if (!map.containsKey(a))
				{
					map.put(a, new HashSet<String>());
				}
				namespaces.add("Unity.Physics");
				namespaces.add("Unity.Transforms");
				map.get(a).add("PhysicsCollider");
				map.get(a).add("Translation");


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
				namespaces.add("UnityEngine");
				return "UnityEngine.Time.time";
			case FIXED_DELTA_TIME:
				namespaces.add("UnityEngine");
				return "UnityEngine.Time.fixedDeltaTime";
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
		if (found == Component.TEXT && jobified)
		{
			return "M.textData";
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
			case FONT: namespaces.add("UnityEngine.UI"); return "Text";
			case IMAGE: namespaces.add("UnityEngine.UI"); return "RawImage";
			case IMAGE_COLOR: namespaces.add("UnityEngine.UI"); return "RawImage";
			case IMAGE_MATERIAL: namespaces.add("UnityEngine.UI"); return "RawImage";
			case SLIDER_VALUE: namespaces.add("UnityEngine.UI"); return "Slider";
			case TEXT: namespaces.add("UnityEngine.UI"); return "Text";
			case TEXTFIELD_VALUE: namespaces.add("UnityEngine.UI"); return "InputField";
			case TEXT_COLOR: namespaces.add("UnityEngine.UI"); return "Text";
			case TEXT_MATERIAL: namespaces.add("UnityEngine.UI"); return "Text";
			case TOGGLED: namespaces.add("UnityEngine.UI"); return "Toggle";
			case ANCHOR_MIN: namespaces.add("UnityEngine.UI"); return "RectTransform";
			case ANCHOR_MAX: namespaces.add("UnityEngine.UI"); return "RectTransform";
			}
		}
		return "undefined";
	}

	private String jobComponent(String name)
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
			return "PhysicsVelocity";
		}
		else if (name.equals("PhysicsCollider"))
		{
			return "PhysicsCollider";
		}
		else if (name.equals("Translation"))
		{
			namespaces.add("Unity.Transforms");
			return "Translation";
		}
		if (found == Component.TEXT && jobified)
		{
			return "M.textData";
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
			return "M."+name+"Data";
		}
		else
		{
			switch (found)
			{
			case VELOCITY: namespaces.add("Unity.Physics"); return "PhysicsVelocity";
			case POSITION: namespaces.add("Unity.Transforms"); return "Translation";
			case ANGULAR_VELOCITY: namespaces.add("Unity.Physics"); return "PhysicsVelocity";
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
			case FONT: namespaces.add("UnityEngine.UI"); return "Text";
			case IMAGE: namespaces.add("UnityEngine.UI"); return "RawImage";
			case IMAGE_COLOR: namespaces.add("UnityEngine.UI"); return "RawImage";
			case IMAGE_MATERIAL: namespaces.add("UnityEngine.UI"); return "RawImage";
			case SLIDER_VALUE: namespaces.add("UnityEngine.UI"); return "Slider";
			case TEXT: namespaces.add("UnityEngine.UI"); return "M.textData";
			case TEXTFIELD_VALUE: namespaces.add("UnityEngine.UI"); return "InputField";
			case TEXT_COLOR: namespaces.add("UnityEngine.UI"); return "Text";
			case TEXT_MATERIAL: namespaces.add("UnityEngine.UI"); return "Text";
			case TOGGLED: namespaces.add("UnityEngine.UI"); return "Toggle";
			case ANCHOR_MIN: namespaces.add("UnityEngine.UI"); return "RectTransform";
			case ANCHOR_MAX: namespaces.add("UnityEngine.UI"); return "RectTransform";
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
			case VELOCITY: return jobified ? "Linear" : "velocity";
			case POSITION: return jobified ? "Value" : "localPosition";
			case ANGULAR_VELOCITY: return "angularVelocity";
			case AUDIOCLIP: return "audioClip";
			case BACKGROUND: return "backgroundColor";
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
			case TEXT: return jobified ? "Value" : "text";
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
				if (jobified)
				{
					namespaces.add("Unity.Entities");
					return "Entity";
				}
				else
				{
					namespaces.add("UnityEngine");
					return "GameObject";
				}
			case NUMBER:
				return "float";
			case NUMBER2:
				namespaces.add("UnityEngine");
				return "Vector2";
			case NUMBER3:
				namespaces.add("UnityEngine");
				return "Vector3";
			case PROPOSITION:
				return "bool";
			case ENTITY_LIST:
				namespaces.add("UnityEngine");
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
			case COMPONENT:
				return "Error (type component shouldnt be)";
			case FONT:
				namespaces.add("UnityEngine.UI");
				return "Font";
			case TEXT:
				namespaces.add("UnityEngine.UI");
				return "Text";
			case IMAGE:
				namespaces.add("UnityEngine.UI");
				return "Image";
			case AUDIOCLIP:
				namespaces.add("UnityEngine");
				return "AudioClip";
			case QUATERNION:
				namespaces.add("UnityEngine");
				return "Quaternion";
			case TEXTURE:
				namespaces.add("UnityEngine");
				return "Texture";
			case COLLIDER:
				namespaces.add("UnityEngine");
				return "Collider";
			case RECT:	
				namespaces.add("UnityEngine");
				return "Rect";
			}
			return "Undefined";
		}
		return "Undefined";
	}

	private void clean(String root)
	{
		var components = new File(root+"/Components");
		for (var file : components.listFiles())
		{
			if (file.getName().endsWith(".cs"))
			{
				file.delete();
			}
		}
		var systems = new File(root+"/Systems");
		for (var file : systems.listFiles())
		{
			if (file.getName().endsWith(".cs"))
			{
				file.delete();
			}
		}
	}

	private boolean valueType(Type type)
	{
		return type == UNIT || type == NUMBER || type == NUMBER2
			|| type == NUMBER3 || type == COLOR || type == ENTITY ||
			type == QUATERNION || type == PROPOSITION;
	}

	private boolean valueType(String component)
	{
		if (component.equals("PhysicsCollider") || component.equals("Translation"))
		{
			return true;
		}

		var standard = library.getComponent(component);

		if (standard != null)
		{
			return valueType(standard.getType());
		}
		else
		{
			return valueType(game.getComponents().get(component));
		}
	}
}

class AssemblyDefinition
{
	String name;
	List<String> references;
	List<String> includePlatforms;
	List<String> excludePlatforms;
	boolean allowUnsafeCode;
	boolean overrideReferences;
	List<String> precompiledReferences;
	boolean autoReferenced;
	List<String> definedConstraints;
	List<String> versionDefines;
	boolean noEngineReferences;

	public AssemblyDefinition(String name, List<String> references, boolean allowUnsafeCode)
	{
		this.name = name;
		this.references = references;
		this.allowUnsafeCode = allowUnsafeCode;
	}
}