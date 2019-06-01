package games.minim.generator

import games.minim.m.Access
import games.minim.m.And
import games.minim.m.ArithmeticBrackets
import games.minim.m.AssignmentType
import games.minim.m.Audio
import games.minim.m.AxisType
import games.minim.m.Bitwise
import games.minim.m.BooleanBrackets
import games.minim.m.Break
import games.minim.m.Call
import games.minim.m.Check
import games.minim.m.Command
import games.minim.m.Comparison
import games.minim.m.ComponentAssignment
import games.minim.m.Condition
import games.minim.m.Control
import games.minim.m.Decimal
import games.minim.m.Decrement
import games.minim.m.Divide
import games.minim.m.EngineComponentType
import games.minim.m.EngineTransformationType
import games.minim.m.EngineVoidType
import games.minim.m.Entity
import games.minim.m.Enumeration
import games.minim.m.EventType
import games.minim.m.Expression
import games.minim.m.FieldType
import games.minim.m.Game
import games.minim.m.Increment
import games.minim.m.Initialization
import games.minim.m.Loop
import games.minim.m.MFactory
import games.minim.m.Minus
import games.minim.m.Modulus
import games.minim.m.Name
import games.minim.m.Not
import games.minim.m.Number
import games.minim.m.Or
import games.minim.m.Plus
import games.minim.m.Pop
import games.minim.m.Range
import games.minim.m.Real1
import games.minim.m.Real2
import games.minim.m.Real3
import games.minim.m.Real4
import games.minim.m.Reference
import games.minim.m.RelationType
import games.minim.m.Sensor
import games.minim.m.Sprite
import games.minim.m.SubrutineCall
import games.minim.m.Text
import games.minim.m.Timer
import games.minim.m.Times
import games.minim.m.Trigger
import games.minim.m.TriggerType
import games.minim.m.Value
import games.minim.m.VariableAssignment
import games.minim.m.Vector
import games.minim.m.VectorType
import games.minim.m.While
import games.minim.m.impl.NameImpl
import games.minim.scoping.EngineComponent
import games.minim.scoping.EngineTransformation
import games.minim.scoping.EngineVoid
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Random
import java.util.UUID
import javax.imageio.ImageIO
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2

import static games.minim.generator.Folder.*
import static games.minim.generator.HybridUnity.AssetType.*
import static games.minim.generator.UnityComponentType.*

import static extension org.eclipse.xtext.EcoreUtil2.*

enum Folder {Assets,Code,Tests,Packages,Settings,
		Clips,Meshes,Materials,Sprites,Audios,PhysicsMaterials,
		Components,Systems,Enumerations,Prefabs,Scenes,Animators,Tools}
enum UnityComponentType
{
	Transform, Rigidbody2D, Camera,
	CircleCollider2D, BoxCollider2D, Collider,
	AudioListener, Light,
	Text, ray, RectTransform, MonoBehaviour,
	MeshFilter, MeshRenderer, AudioSource,
	SpriteRenderer, Canvas, CanvasRenderer
}
	
class HybridUnity implements Framework
{
	Game game
	IFileSystemAccess2 fileSystem
	
	override represent(Game game, IFileSystemAccess2 fileSystem) 
	{
		this.game = game
		this.fileSystem = fileSystem
		
		assetsFolder
		version
		packages
		assemblies
		buildSettings
		projectSettings
		art
		components
		enumerations
		entities
		systems
		scenes
	}
	
	def assetsFolder()
	{
		var path = '''Hybrid «game.title»/Assets/toDelete.del'''
		fileSystem.generateFile(path, '')
		fileSystem.deleteFile(path)
	}
	
	def version()
	{
		fileSystem.generateFile('''«Settings.folder»/ProjectVersion.txt''',
		'''m_EditorVersion: 2018.4.0f1''')
	}
	
	def packages()
	{
		fileSystem.generateFile('''«Packages.folder»/manifest.json''',
		'''
		{
		  "dependencies": {
		    "com.unity.entities": "0.0.12-preview.24",
		    "com.unity.inputsystem": "0.2.1-preview",
		    "com.unity.package-manager-ui": "2.0.7",
		    "com.unity.modules.ai": "1.0.0",
		    "com.unity.modules.animation": "1.0.0",
		    "com.unity.modules.assetbundle": "1.0.0",
		    "com.unity.modules.audio": "1.0.0",
		    "com.unity.modules.cloth": "1.0.0",
		    "com.unity.modules.director": "1.0.0",
		    "com.unity.modules.imageconversion": "1.0.0",
		    "com.unity.modules.imgui": "1.0.0",
		    "com.unity.modules.jsonserialize": "1.0.0",
		    "com.unity.modules.particlesystem": "1.0.0",
		    "com.unity.modules.physics": "1.0.0",
		    "com.unity.modules.physics2d": "1.0.0",
		    "com.unity.modules.screencapture": "1.0.0",
		    "com.unity.modules.terrain": "1.0.0",
		    "com.unity.modules.terrainphysics": "1.0.0",
		    "com.unity.modules.tilemap": "1.0.0",
		    "com.unity.modules.ui": "1.0.0",
		    "com.unity.modules.uielements": "1.0.0",
		    "com.unity.modules.umbra": "1.0.0",
		    "com.unity.modules.unityanalytics": "1.0.0",
		    "com.unity.modules.unitywebrequest": "1.0.0",
		    "com.unity.modules.unitywebrequestassetbundle": "1.0.0",
		    "com.unity.modules.unitywebrequestaudio": "1.0.0",
		    "com.unity.modules.unitywebrequesttexture": "1.0.0",
		    "com.unity.modules.unitywebrequestwww": "1.0.0",
		    "com.unity.modules.vehicles": "1.0.0",
		    "com.unity.modules.video": "1.0.0",
		    "com.unity.modules.vr": "1.0.0",
		    "com.unity.modules.wind": "1.0.0",
		    "com.unity.modules.xr": "1.0.0"
		  }
		}''')
	}
	
	def assemblies()
	{
		fileSystem.generateFile('''«Code.folder»/Code.asmdef''',
		'''
		{
			"name": "Code",
			"references": 
			[
		        "Unity.Entities",
				"Unity.Entities.Hybrid",
				"Unity.Mathematics",
				"Unity.Burst",
				"Unity.InputSystem"
		    ]
		}
		'''
		)
	}

	def buildSettings()
	{
		if (game.entities.size > 0)
		{
			fileSystem.generateFile('''«Settings.folder»/EditorBuildSettings.asset''',
			'''
			%YAML 1.1
			%TAG !u! tag:unity3d.com,2011:
			--- !u!1045 &1
			EditorBuildSettings:
			  m_ObjectHideFlags: 0
			  serializedVersion: 2
			  m_Scenes:
			  - enabled: 1
			    path: Assets/Design/Scenes/Main.unity
			    guid: «'MainScene'.uuid»
			  m_configObjects: {}
			'''
			)
		}
	}
	
	def scenes()
	{
		fileSystem.generateFile('''«Scenes.folder»/Main.unity''',
		'''
		%YAML 1.1
		%TAG !u! tag:unity3d.com,2011:
		'''
		)
		fileSystem.generateFile('''«Scenes.folder»/Main.unity.meta''',
		'''
		fileFormatVersion: 2
		guid: «'MainScene'.uuid»
		'''
		)
	}
	
	def projectSettings()
	{
		fileSystem.generateFile('''«Settings.folder»/ProjectSettings.asset''',
		'''
		%YAML 1.1
		%TAG !u! tag:unity3d.com,2011:
		--- !u!129 &1
		PlayerSettings:
		  productName: «game.title»
		  enableNativePlatformBackendsForNewInputSystem: 1
		''')
		
		fileSystem.generateFile('''«Settings.folder»/Physics2DSettings.asset''',
		'''
		%YAML 1.1
		%TAG !u! tag:unity3d.com,2011:
		--- !u!19 &1
		Physics2DSettings:
		  serializedVersion: 4
		  m_Gravity: {x: 0, y: 0}
		'''
		)
		
		fileSystem.generateFile('''«Settings.folder»/DynamicsManager.asset''',
		'''
		%YAML 1.1
		%TAG !u! tag:unity3d.com,2011:
		--- !u!55 &1
		PhysicsManager:
		  serializedVersion: 10
		  m_Gravity: {x: 0, y: 0, z: 0}
		'''
		)
	}
	
	def art()
	{
		var random = new Random
		
		for (art : game.sprites)
		{
			var name = art.name
				
			var image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			image.setRGB(0,0,new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)).RGB)
			var o = new ByteArrayOutputStream
			ImageIO.write(image, 'png', o)
			
			fileSystem.generateFile('''«Sprites.folder»/«name».png''', new ByteArrayInputStream(o.toByteArray))
			fileSystem.generateFile('''«Sprites.folder»/«name».png.meta''',
			'''
			fileFormatVersion: 2
			guid: «art.name.uuid(Sprite)»
			TextureImporter:
			  serializedVersion: 9
			  textureFormat: 1
			  compressionQuality: 50
			  spriteMode: 1
			  spriteExtrude: 1
			  spriteMeshType: 1
			  spritePixelsToUnits: 1
			  textureType: 8
			  textureShape: 1
			  spriteSheet:
			    serializedVersion: 2'''
			)
		}
		for (art : game.audios)
		{
			var asset = art.name
			var path = '''«Audios.folder»/«asset».wav'''

				var hz = 44100;
				var bps = 16;
				var rescaleFactor = 0x7FFF; //to convert float to Int16
				var header = 36;
				var channels = 1;
				var format = 1;
				
				var baos = new ByteArrayOutputStream
				var outputStream = new DataOutputStream(baos)
				
				var note = random.nextInt(200) + 300
				
				var samples = newIntArrayOfSize(hz);
		
				for (var i = 0; i < samples.size; i++)
				{
					samples.set(i, (Math.sin(Math.PI * 2 * i * note / hz) * rescaleFactor ) as int)
				}
		
				outputStream.writeBytes('RIFF')
				outputStream.writeInt(Integer.reverseBytes(header + samples.size * bps / 8))
				outputStream.writeBytes("WAVE")
				outputStream.writeBytes("fmt ")
				outputStream.writeInt(Integer.reverseBytes(16))
				outputStream.writeShort(Short.reverseBytes(format as short))
				outputStream.writeShort(Short.reverseBytes(channels as short))
				outputStream.writeInt(Integer.reverseBytes(hz))
				outputStream.writeInt(Integer.reverseBytes(hz * channels * bps / 8))
				outputStream.writeShort(Short.reverseBytes((channels * bps / 8) as short))
				outputStream.writeShort(Short.reverseBytes(bps as short))
				outputStream.writeBytes("data")
				outputStream.writeInt(Integer.reverseBytes(samples.size * channels * bps / 8))
				for (var i = 0; i < samples.size; i++)
				{
					outputStream.writeShort(Short.reverseBytes(samples.get(i)as short));
				}
				
				fileSystem.generateFile(path, new ByteArrayInputStream(baos.toByteArray))
				fileSystem.generateFile(path+'.meta',
				'''
				fileFormatVersion: 2
				guid: «art.name.uuid(Audio)»
				AudioImporter:
				  serializedVersion: 6
				'''
				)
				
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	def generateMetaFile(CharSequence folder, String name, Object o)
	{
		fileSystem.generateFile('''«folder»/«name».meta''',
		'''
		fileFormatVersion: 2
		guid: «o.uuid»
		''')
	}
	
	def folder(Folder folder)
	{
		var title = '''Hybrid «game.title»'''
		var assets = '''«title»/Assets'''
		var code = '''«assets»/Code'''
		var design = '''«assets»/Design'''
		var art = '''«assets»/Art'''
		var qa = '''«assets»/Code/Quality'''
		
		switch folder
		{
			case Assets: assets
			case Code: code
			case Tests: qa
			case Packages: '''«title»/Packages'''
			case Settings: '''«title»/ProjectSettings'''
			
			case Clips: '''«art»/Clips'''
			case Meshes: '''«art»/Meshes'''
			case Materials: '''«art»/Materials'''
			case Sprites: '''«art»/Sprites'''
			case Audios: '''«art»/Audio'''
			case PhysicsMaterials: '''«design»/PhysicsMaterials'''
			
			case Components: '''«code»/Components'''
			case Systems: '''«code»/Systems'''
			case Enumerations: '''«code»/Enumerations'''
			
			case Prefabs: '''«design»/Prefabs/Resources'''
			case Scenes: '''«design»/Scenes'''
			case Animators: '''«design»/Animators'''
			case Tools: '''«design»/Tools/Editor'''
		}
	}
	
	
	def uuid(String name)
	{
		UUID.nameUUIDFromBytes(name.bytes).toString.replace('-','')
	}
	
	def hash(String s)
	{
		var result = ''
		var uuid = s.uuid
		for (var c = 0; c < 10; c++)
		{
			var character = uuid.charAt(c)
			if (character > 57)
			{
				result += (character-49) as char
			}
			else
			{
				result += character
			}
		}
		if (result.startsWith('0'))
		{
			result = result.replaceFirst('0','1')
		}
		return result
	}
	
	
	
	
	
	
	
	
	def enumerations()
	{
		for (enumeration : game.enumerations)
		{
			fileSystem.generateFile('''«Enumerations.folder»/«enumeration.name»_type.cs''',
			'''
			[System.Flags]
			public enum «enumeration.name»_type
			{
				«FOR value : enumeration.values»
				«value.name» = 0b«Math.pow(10,enumeration.values.indexOf(value)) as long»,
				«ENDFOR»
			}
			''')
			
			generateMetaFile(Enumerations.folder, enumeration.name+'_type.cs', enumeration)
		}
	}
	
	def components()
	{
		//if (true)//game.entities.exists[it.values.map[component].filter(EngineComponent).exists[it.type == EngineComponentType.RAY]])
		
		fileSystem.generateFile('''«Components.folder»/ray.cs''',
		'''
		using UnityEngine;
		
		public class ray : MonoBehaviour
		{
			public Vector2 «value»;
			
		    private void Update()
		    {
		    	Debug.DrawRay(transform.position, transform.position + new Vector3(«value».x, «value».y, 0));
		        var result = Physics2D.RaycastAll (new Vector2(transform.position.x, transform.position.y), «value», «value».magnitude);
		        foreach (var hit in result)
		        {
		            if (hit.transform.gameObject != gameObject)
		            {
		                foreach (var c in GetComponents<Interface>())
		                {
		                    c.OnTriggerEnter2D(hit.collider);
		                }
		            }
		        }
		    }
		}
		
		public interface Interface
		{
		    void OnTriggerEnter2D(Collider2D other);
		}
		'''
		)
		generateMetaFile(Components.folder, 'ray.cs', 'ray')
		
		for (component : game.sensorComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			
			public class «name» : MonoBehaviour, Interface
			{
				public category_type «value()»;
				bool now;
				
				void OnCollisionEnter2D(Collision2D other)
				{
					var other_category = other.gameObject.GetComponent<_category>();
					if (other_category != null)
					{
			            if ((other_category.Value.value & «value()») != 0)
			            {
			                var goe = gameObject.GetComponent<GameObjectEntity>();
			                goe.EntityManager.AddComponentData(goe.Entity, new «name»_enter { });
						}
					}
				}
				
				public void OnTriggerEnter2D(Collider2D other)
				{
					var other_category = other.gameObject.GetComponent<_category>();
					if (other_category != null)
					{
			            if ((other_category.Value.value & «value()») != 0)
			            {
			                var goe = gameObject.GetComponent<GameObjectEntity>();
			                if (!goe.EntityManager.HasComponent<«name»_enter>(goe.Entity))
			                {
			                	goe.EntityManager.AddComponentData(goe.Entity, new «name»_enter { });
							}
						}
					}
				}
				
				public void OnTriggerStay2D(Collider2D other)
				{
					var other_category = other.gameObject.GetComponent<_category>();
					if (other_category != null)
					{
			            if ((other_category.Value.value & «value()») != 0)
			            {
			                var goe = gameObject.GetComponent<GameObjectEntity>();
			                if (!goe.EntityManager.HasComponent<«name»_stay>(goe.Entity))
			                {
			                	goe.EntityManager.AddComponentData(goe.Entity, new «name»_stay { });
							}
						}
					}
				}
				
				public void OnTriggerExit2D(Collider2D other)
				{
					var other_category = other.gameObject.GetComponent<_category>();
					if (other_category != null)
					{
			            if ((other_category.Value.value & «value()») != 0)
			            {
			                var goe = gameObject.GetComponent<GameObjectEntity>();
			                if (!goe.EntityManager.HasComponent<«name»_exit>(goe.Entity))
			                {
			                	goe.EntityManager.AddComponentData(goe.Entity, new «name»_exit { });
							}
							if (goe.EntityManager.HasComponent<«name»_stay>(goe.Entity))
			                {
			                	goe.EntityManager.RemoveComponent<«name»_stay>(goe.Entity);
							}
						}
					}
				}
				
				void Update()
				{
					var goe = gameObject.GetComponent<GameObjectEntity>();
			        if (goe.EntityManager.HasComponent<«name»_enter>(goe.Entity))
			        {
			            if (!now)
			            {
			                now = true;
			            }
			            else
			            {
			                now = false;
			                goe.EntityManager.RemoveComponent<«name»_enter>(goe.Entity);
			            }
			        }
			        if (goe.EntityManager.HasComponent<«name»_exit>(goe.Entity))
			        {
			        	goe.EntityManager.RemoveComponent<«name»_exit>(goe.Entity);
			        }
				}
			}
			[Serializable] public struct «component.name»_enter : IComponentData {}
			[Serializable] public struct «component.name»_stay : IComponentData {}
			[Serializable] public struct «component.name»_exit : IComponentData {}'''
			)
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.referenceComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using UnityEngine;
			
			public class «name» : MonoBehaviour
			{
				public GameObject «value»;
			}'''
			)
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.timerComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			
			public class «name» : MonoBehaviour
			{
				public float «value»;
				float current;
			    Entity entity;
			    EntityManager manager;
			
			    void Start()
			    {
			        entity = GetComponent<GameObjectEntity>().Entity;
			        manager = World.Active.GetExistingManager<EntityManager>();
			    }
			    
			    void Update()
				{
			        if (manager.HasComponent<«name»_timeout>(entity))
			        {
			            manager.RemoveComponent<«name»_timeout>(entity);
			        }
			
					current += Time.deltaTime;
					if (current > «value»)
					{
						manager.AddComponentData(entity, new «name»_timeout { });
						current = current % value;
					}
				}
			}
			[Serializable] public struct «name»_timeout : IComponentData {}''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.triggerComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using UnityEngine.Experimental.Input;
			using Unity.Entities;
			
			public class «name» : MonoBehaviour
			{
				public InputAction «value()»;
				bool active;
				Entity entity;
				EntityManager manager;
				
				void OnEnable()
				{
					«value».Enable();
				}
				
				void OnDisable()
				{
					«value».Disable();
				}
				
				void Start()
				{
			        entity = GetComponent<GameObjectEntity>().Entity;
			        manager = World.Active.GetExistingManager<EntityManager>();
			
			        value.performed += c =>
			        {
			            active = c.ReadValue<float>() == 1;
			        };
					value.cancelled += c =>
					{
						active = c.ReadValue<float>() == 1;
					};
				}
				
				void Update()
				{
					AdjustComponent(entity,manager);
					active = false;
				}
			
			    void AdjustComponent(Entity entity, EntityManager manager)
			    {
			        if (active && !manager.HasComponent<«component.name»_triggered>(entity))
			        {
			            manager.AddComponentData(entity, new «component.name»_triggered { });
			        }
			        else if (!active && manager.HasComponent<«component.name»_triggered>(entity))
			        {
			            manager.RemoveComponent<«component.name»_triggered>(entity);
			        }
			    }
			}
			
			[Serializable] public struct «component.name»_triggered : IComponentData {}''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.rangeComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using UnityEngine.Experimental.Input;
			using Unity.Entities;
			using static UnityEngine.Experimental.Input.InputAction;
			
			public class «name» : MonoBehaviour
			{
				public InputAction «value()»;
				float readValue;
				Entity entity;
				EntityManager manager;
				
				void OnEnable()
				{
					«value».Enable();
				}
				
				void OnDestroy()
			    {
			        «value».performed -= Callback;
			        «value».cancelled -= Callback;
			    }
			
			    void Callback (CallbackContext c)
			    {
			        readValue = c.ReadValue<float>();
			        manager.SetComponentData(entity, new «name»_range { value = readValue });
			    }
				
				void Start()
				{
					entity = GetComponent<GameObjectEntity>().Entity;
					manager = World.Active.GetExistingManager<EntityManager>();
					manager.AddComponentData(entity, new «name»_range { value = 0 });
					
					«value».performed += Callback;
					«value».cancelled += Callback;
				}
			}
			[Serializable] public struct «component.name»_range : IComponentData { public float «value»; }''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.vectorComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using UnityEngine.Experimental.Input;
			using Unity.Entities;
			
			public class «name» : MonoBehaviour
			{
				public InputAction «value()»;
				public bool active;
				public float readValue;
				
				void OnEnable()
				{
					«value».Enable();
				}
				
				void OnDisable()
				{
					«value».Disable();
				}
				
				void Start()
				{
					«value».performed += c =>
					{
						readValue = c.ReadValue<float>();
						active = readValue == 1;
					};
					«value».cancelled += c =>
					{
						readValue = c.ReadValue<float>();
						active = readValue == 1;
					};
				}
			}
			[Serializable] public struct «component.name»_range : IComponentData { public float «value»; }''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.tagComponents)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			
			[Serializable]
			public struct «name» : IComponentData { }
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.real1Components)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			
			[Serializable]
			public struct «name» : IComponentData
			{
				public float «value»;
			}
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.real2Components)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[Serializable]
			public struct «name» : IComponentData
			{
				public float2 «value»;
			}
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.real3Components)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[Serializable]
			public struct «name» : IComponentData
			{
				public float3 «value»;
			}
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.real4Components)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[Serializable]
			public struct «name» : IComponentData
			{
				public float4 «value»;
			}
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.enumerationComponents)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/_«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[Serializable]
			public struct «name» : IComponentData
			{
				public «name»_type «value»;
			}
			
			[AddComponentMenu("Generated/«name»")]
			public class _«name» : ComponentDataProxy<«name»> { }
			''')
			generateMetaFile(Components.folder, '_'+name+'.cs', component.name)
		}
		for (component : game.spriteComponents)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[AddComponentMenu("Generated/«name»")]
			public class «name» : MonoBehaviour
			{
				public Sprite «value()»;
			}
			''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
		for (component : game.audioComponents)
		{
			var name = component.name
			
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			using Unity.Mathematics;
			
			[AddComponentMenu("Generated/«name»")]
			public class «name» : MonoBehaviour
			{
				public AudioClip «value()»;
			}
			''')
			generateMetaFile(Components.folder, name+'.cs', component.name)
		}
	}
	
	def entities()
	{
		for(entity : game.entities)
		{
			val name = entity.name
			
			var restitutionValue = entity.values.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.RESTITUTION] as Real1
			var frictionValue = entity.values.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.FRICTION] as Real1
			val physicsMaterial = name+'physicsMaterial'
			
			if (restitutionValue !== null || frictionValue !== null)
			{
				fileSystem.generateFile('''«PhysicsMaterials.folder»/«name».physicsMaterial2D''',
				'''
				%YAML 1.1
				%TAG !u! tag:unity3d.com,2011:
				--- !u!62 &6200000
				PhysicsMaterial2D:
				  m_Name: «name»
				  friction: «IF frictionValue !== null»«frictionValue.x.toCode»«ELSE»0«ENDIF»
				  bounciness: «IF restitutionValue !== null»«restitutionValue.x.toCode»«ELSE»0«ENDIF»
				'''
				)
				fileSystem.generateFile('''«PhysicsMaterials.folder»/«name».physicsMaterial2D.meta''',
				'''
				fileFormatVersion: 2
				guid: «uuid(physicsMaterial)»
				'''
				)
			}
			
			
			
			// Generate files
			
			fileSystem.generateFile('''«Prefabs.folder»/«name».prefab''',
			'''
			%YAML 1.1
			%TAG !u! tag:unity3d.com,2011:
			«entity.yaml»
			''')

			fileSystem.generateFile('''«Prefabs.folder»/«name».prefab.meta''',
			'''
			fileFormatVersion: 2
			guid: «name.uuid(Prefab)»
			''')
		}
		
		//canvas()
	}
	
	def addExceptions(Entity entity, ArrayList<Value> transformValues, HashMap<Name,List<Value>> yamlComponents)
	{
		var rigidbody = yamlComponents.keySet.findFirst[it.unityComponent == Rigidbody2D]
		if (rigidbody !== null)
		{
			if (!yamlComponents.get(rigidbody).exists[it.component.property == 'mass'])
			{
				yamlComponents.get(rigidbody).add(real1Value(EngineComponentType.BODY_TYPE, 1))
				yamlComponents.get(rigidbody).add(real1Value(EngineComponentType.SERIALIZED_VERSION, 4))
			}
			if (!transformValues.exists[it.component.property == 'localEulerAnglesHint'])
			{
				yamlComponents.get(rigidbody).add(real1Value(EngineComponentType.SERIALIZED_VERSION, 4))
				yamlComponents.get(rigidbody).add(real1Value(EngineComponentType.RIGIDBODY_CONSTRAINTS, 4))
			}
		}
		var canvasScaler = yamlComponents.keySet.filter(EngineComponent).findFirst[it.type == EngineComponentType.CANVAS_SCALER]
		if (canvasScaler !== null)
		{
			yamlComponents.get(canvasScaler).add(real1Value(EngineComponentType.UI_SCALE_MODE, 1))
			yamlComponents.get(canvasScaler).add(real1Value(EngineComponentType.SCREEN_EXPAND, 1))
			var canvas = new EngineComponent
			canvas.name = 'Canvas'
			canvas.type = EngineComponentType.CANVAS
			yamlComponents.put(canvas, new ArrayList<Value>)
			yamlComponents.get(canvas).add(real1Value(EngineComponentType.CANVAS, 0))
			yamlComponents.get(canvas).add(real1Value(EngineComponentType.SERIALIZED_VERSION, 3))
			var raycaster = new EngineComponent
			raycaster.name = 'GraphicRaycaster'
			raycaster.type = EngineComponentType.GRAPHIC_RAYCASTER
			yamlComponents.put(raycaster, #[real1Value(EngineComponentType.GRAPHIC_RAYCASTER, 0)])
			var events = new EngineComponent
			events.name = 'EventSystem'
			events.type = EngineComponentType.EVENT_SYSTEM
			yamlComponents.put(events, #[real1Value(EngineComponentType.EVENT_SYSTEM, 0)])
			var input = new EngineComponent
			input.name = 'StandaloneInputModule'
			input.type = EngineComponentType.INPUT_MODULE
			yamlComponents.put(input, #[real1Value(EngineComponentType.INPUT_MODULE, 0)])
		}
		var text = yamlComponents.keySet.filter(EngineComponent).findFirst[it.type == EngineComponentType.TEXT || it.type == EngineComponentType.NUMBER]
		if (text !== null)
		{
			var renderer = new EngineComponent
			renderer.name = 'CanvasRenderer'
			renderer.type = EngineComponentType.CANVAS_RENDERER
			yamlComponents.put(renderer, #[])
			yamlComponents.get(text).add(real1Value(EngineComponentType.FONT, 0))
			var position = transformValues.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.POSITION] as Real2
			var scale = transformValues.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.SCALE] as Real2
			transformValues.clear
			if (position !== null)
			{
				transformValues.add(real2Value(EngineComponentType.SCREEN_POSITION, position.x, position.y))
			}
			if (scale !== null)
			{
				transformValues.add(real2Value(EngineComponentType.SCREEN_SIZE, scale.x, scale.y))
			}
		}
		else
		{
			var base = entity.base
			var ui = false
			while (!ui && base !== null)
			{
				ui = base.values.exists[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.NUMBER]
				base = base.base
			}
			if (ui)
			{
				var position = transformValues.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.POSITION] as Real2
				var scale = transformValues.findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.SCALE] as Real2
				transformValues.clear
				if (position !== null)
				{
					transformValues.add(real2Value(EngineComponentType.SCREEN_POSITION, position.x, position.y))
				}
				if (scale !== null)
				{
					transformValues.add(real2Value(EngineComponentType.SCREEN_SIZE, scale.x, scale.y))
				}
			}
		}
		var camera = yamlComponents.keySet.filter(EngineComponent).findFirst[it.unityComponent == Camera]
		if (camera !== null)
		{
			var viewAngle = yamlComponents.get(camera).findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.VIEW_ANGLE] as Real1
			if (viewAngle !== null)
			{
				yamlComponents.get(camera).add(real1Value(EngineComponentType.ORTHOGRAPHIC_SIZE, Float.parseFloat(viewAngle.x.toCode)))
			}
			var perspective = yamlComponents.get(camera).map[component].filter(EngineComponent).findFirst[it.type == EngineComponentType.PERSPECTIVE]
			if (perspective === null)
			{
				yamlComponents.get(camera).add(real1Value(EngineComponentType.ORTHOGRAPHIC, 1))
			}
			yamlComponents.get(camera).add(real1Value(EngineComponentType.NEAR_CLIP, 0))
			yamlComponents.get(camera).add(real1Value(EngineComponentType.CLEAR_FLAGS, 2))
		}
		var light = yamlComponents.keySet.filter(EngineComponent).findFirst[it.unityComponent == Light]
		if (light !== null)
		{
			var spot = yamlComponents.get(light).findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.SPOT_ANGLE] as Real1
			var range = yamlComponents.get(light).findFirst[(it.component instanceof EngineComponent) && (it.component as EngineComponent).type == EngineComponentType.RANGE] as Real1
			if (spot !== null)
			{
				yamlComponents.get(light).add(real1Value(EngineComponentType.LIGHT_TYPE, 0))
			}
			else if (range !== null)
			{
				yamlComponents.get(light).add(real1Value(EngineComponentType.LIGHT_TYPE, 2))
			}
			else
			{
				yamlComponents.get(light).add(real1Value(EngineComponentType.LIGHT_TYPE, 1))
			}
		}
		var collider = yamlComponents.keySet.filter(EngineComponent).findFirst[it.unityComponent == Collider]
		var circleCollider = yamlComponents.keySet.filter(EngineComponent).findFirst[it.unityComponent == CircleCollider2D]
		var boxCollider = yamlComponents.keySet.filter(EngineComponent).findFirst[it.unityComponent == BoxCollider2D]
		if (circleCollider !== null && collider === null)
		{
			yamlComponents.get(circleCollider).add(real1Value(EngineComponentType.TRIGGER, 1))
		}
		if (boxCollider !== null && collider === null)
		{
			yamlComponents.get(boxCollider).add(real1Value(EngineComponentType.TRIGGER, 1))
		}
	}
	
	def real1Value(EngineComponentType name, float x)
	{
		var value = MFactory.eINSTANCE.createReal1
		var component = new EngineComponent
		component.name = name.toString
		component.type = name
		value.component = component
		var number = MFactory.eINSTANCE.createDecimal
		number.wholePart = x as int
		var decimals = x - (x as int)
		number.decimalPart = (decimals * 100) as int
		value.x = number
		return value
	}
	
	def real2Value(EngineComponentType name, Number x, Number y)
	{
		var value = MFactory.eINSTANCE.createReal2
		var component = new EngineComponent
		component.name = name.toString
		component.type = name
		value.component = component
		value.x = x
		value.y = y
		return value
	}
	
	def String yaml(Entity entity)
	{
		
		var transformValues = new ArrayList<Value>
		
		var yamlComponents = new HashMap<Name,List<Value>>
		for (value : entity.values)
		{
			val component = value.component
			
			if (component.unityComponent == Transform)
			{
				transformValues.add(value)
			}
			else
			{
				var Name found = null
				if (component instanceof EngineComponent)
				{
					found = yamlComponents.keySet.findFirst[(it instanceof EngineComponent) && it.unityComponent == component.unityComponent]
				}
				
				if (found === null)
				{
					yamlComponents.put(component,new ArrayList<Value>)
					found = component
				}
				yamlComponents.get(found).add(value)
			}
		}
		
		addExceptions(entity, transformValues, yamlComponents)
			
		return
		'''
		«IF entity.base === null»
		--- !u!1 &«entity.name.hash»
		GameObject:
		  serializedVersion: 6
		  m_IsActive: 1
		  m_Name: «entity.name»
		  m_Component:
		  - component: {fileID: «entity.gameObjectEntityHash»}
		  «(yamlComponents.keySet.map[it.componentHash(entity)]+#[entity.transformHash]).map['''- component: {fileID: «it»}'''].toSet.join('\n')»
		--- !u!«IF entity.ui»224«ELSE»4«ENDIF» &«entity.transformHash»
		«IF entity.ui»RectTransform«ELSE»Transform«ENDIF»:
		  m_GameObject: {fileID: «entity.name.hash»}
		  «IF entity.eContainer instanceof Entity»
		  m_Father: {fileID: «(entity.eContainer as Entity).transformHash»}
		  «ENDIF»
		  «FOR value : transformValues»
		  «value.yaml1Inner»
		  «ENDFOR»
		  «IF entity.children.size > 0»
		  m_Children:
		  «entity.children.map['''- {fileID: «it.transformHash»}'''].toSet.join('\n')»
		  «ENDIF»
		--- !u!114 &«entity.gameObjectEntityHash»
		MonoBehaviour:
		  m_GameObject: {fileID: «entity.name.hash»}
		  m_Script: {fileID: 11500000, guid: 5bf10cdea1344482e91a4f2b58506b77, type: 3}
		«FOR component : yamlComponents.keySet»
		--- !u!«component.unityComponent.code» &«component.componentHash(entity)»
		«component.unityComponent.codeName»:
		  m_GameObject: {fileID: «entity.name.hash»}
		  «FOR value : yamlComponents.get(component)»
		  «value.yaml1Inner»
		  «ENDFOR»
		  «IF component instanceof EngineComponent»
		  «IF component.unityComponent == ray»
		  m_Script: {fileID: 11500000, guid: «component.name.uuid», type: 3}
		  «ELSE»
		  m_Script: {fileID: «component.script», guid: f70555f144d8491a825f0804e09c671c, type: 3}
		  «ENDIF»
		  «ELSE»
		  m_Script: {fileID: 11500000, guid: «component.name.uuid», type: 3}
		  «ENDIF»
		«ENDFOR»
		«
		ELSE
		»
		--- !u!1001 &«entity.name.hash»
		PrefabInstance:
		  serializedVersion: 2
		  m_Modification:
		    «IF entity.eContainer instanceof Entity»
		    m_TransformParent: {fileID: «(entity.eContainer as Entity).transformHash»}
		    «ENDIF»
		    m_Modifications:
		    «IF entity.name != entity.base.name»
		    - target: {fileID: «entity.base.name.hash», guid: «entity.base.name.uuid(Prefab)», type: 3}
		      propertyPath: m_Name
		      value: «entity.name»
		    «ENDIF»
		    «FOR value : transformValues»
		    «value.yaml2(entity.base)»
		    «ENDFOR»
		    «FOR component : yamlComponents.keySet»
		    «FOR value : yamlComponents.get(component)»
		    «value.yaml2(entity.base)»
		    «ENDFOR»
		    «ENDFOR»
		    «IF entity.removed.size > 0»
		    m_RemovedComponents:
		    «FOR removed : entity.removed»
		    - {fileID: «(entity.base.name+removed.name).hash», guid: «entity.base.name.uuid(Prefab)», type: 3}
		    «ENDFOR»
		    «ENDIF»    
		  m_SourcePrefab: {fileID: 100100000, guid: «entity.base.name.uuid(Prefab)», type: 3}
		«IF entity.ui»
		«ELSE»
		--- !u!4 &«entity.transformHash» stripped
		Transform:
		  m_CorrespondingSourceObject: {fileID: «entity.base.transformHash», guid: «entity.base.name.uuid(Prefab)», type: 3}
		  m_PrefabInstance: {fileID: «entity.name.hash»}
		«ENDIF»
		«ENDIF»
		«FOR child : entity.children»
		«child.yaml»
		«ENDFOR»'''
	}
	
	def ui(Entity entity)
	{
		if (entity.values.exists[it.component instanceof EngineComponent && #[EngineComponentType.CANVAS_SCALER,EngineComponentType.TEXT,EngineComponentType.NUMBER].contains((it.component as EngineComponent).type)])
		{
			return true
		}
		return false
	}
	
	def script(Name component)
	{
		if (component instanceof EngineComponent)
		{
			if (component.type == EngineComponentType.CANVAS_SCALER)
			{
				return 1980459831
			}
			else if (component.type == EngineComponentType.EVENT_SYSTEM)
			{
				return -619905303
			}
			else if (component.type == EngineComponentType.GRAPHIC_RAYCASTER)
			{
				return 1301386320
			}
			else if (component.type == EngineComponentType.INPUT_MODULE)
			{
				return 1077351063
			}
			else if (component.type == EngineComponentType.TEXT)
			{
				return 708705254
			}
			else if (component.type == EngineComponentType.NUMBER)
			{
				return 708705254
			}
			else if (component.type == EngineComponentType.RAY)
			{
				return 'ray'.hash
			}
		}
		else
		{
			return null
		}
	}
	
	def componentHash(Name component, Entity entity)
	{
		if (component instanceof EngineComponent)
		{
			if (#[EngineComponentType.CANVAS_SCALER, EngineComponentType.GRAPHIC_RAYCASTER, EngineComponentType.INPUT_MODULE, EngineComponentType.EVENT_SYSTEM].contains(component.type))
			{
				return (entity.name+component.name.toString).hash
			}
			else if (component.unityComponent == Transform || component.unityComponent == RectTransform)
			{
				return transformHash(entity)
			}
			else
			{
				return (entity.name+component.unityComponent.toString).hash
			}
		}
		else
		{
			return (entity.name+component.name.toString).hash
		}
	}
	
	def transformHash(Entity entity)
	{
		(entity.name+Transform.toString).hash
	}
	
	def gameObjectEntityHash(Entity entity)
	{
		(entity.name+'GameObjectEntity').hash
	}
	
	def uuid(Object o)
	{
		UUID.nameUUIDFromBytes(o.toString.bytes).toString.replace('-','')
	}
	
	def yaml2(Value value, Entity entity)
	{
		var component = value.component
		var property = value.component.property
		if (value.component instanceof EngineComponent)
		{
			if (property.contains(' '))
			{
				property = property.replace(' ','')
			}
			else
			{
				property = 'm_'+property.toFirstUpper
			}
		}
		return
		'''
		«IF component instanceof EngineComponent»
		«IF value instanceof Real1»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: «value.x.toCode»
		«ELSEIF value instanceof Real2»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».x
		  value: «value.x.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».y
		  value: «value.y.toCode»
		«ELSEIF value instanceof Real3»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».x
		  value: «value.x.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».y
		  value: «value.y.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».z
		  value: «value.z.toCode»
		«ELSEIF value instanceof Real4»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».x
		  value: «value.x.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».y
		  value: «value.y.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».z
		  value: «value.z.toCode»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property».w
		  value: «value.w.toCode»
		«ELSEIF value instanceof Enumeration»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: «enumerationValue(value.values, value.component.name)»
		«ELSEIF value instanceof Reference»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: 
		  objectReference: {fileID: «value.entity.name.hash», guid: «value.entity.name.uuid(Prefab)», type: 3}
		«ELSEIF value instanceof Trigger»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Range»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Vector»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Timer»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «value.value.toCode»
		«ELSEIF value instanceof Sensor»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «enumerationValue(value.values, 'category')»
		«ELSEIF value instanceof Audio»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: 
		  objectReference: {fileID: 8300000, guid: «value.value.name.uuid(Audio)», type: 3}
		«ELSEIF value instanceof Sprite»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: 
		  objectReference: {fileID: 21300000, guid: «value.value.name.uuid(Sprite)», type: 3}
		«ELSEIF value instanceof Text»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: «value.value»
		«ENDIF»
		«ELSE»
		«IF value instanceof Real1»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()»
		  value: «value.x.toCode»
		«ELSEIF value instanceof Real2»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».x
		  value: «value.x.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».y
		  value: «value.y.toCode»
		«ELSEIF value instanceof Real3»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».x
		  value: «value.x.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».y
		  value: «value.y.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».z
		  value: «value.z.toCode»
		«ELSEIF value instanceof Real4»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».x
		  value: «value.x.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».y
		  value: «value.y.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».z
		  value: «value.z.toCode»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()».w
		  value: «value.w.toCode»
		«ELSEIF value instanceof Enumeration»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: m_SerializedData.«value()»
		  value: «enumerationValue(value.values, value.component.name)»
		«ELSEIF value instanceof Reference»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: 
		  objectReference: {fileID: «value.entity.name.hash», guid: «value.entity.name.uuid(Prefab)», type: 3}
		«ELSEIF value instanceof Trigger»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Range»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Vector»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Timer»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «value.value.toCode»
		«ELSEIF value instanceof Sensor»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «enumerationValue(value.values, 'category')»
		«ELSEIF value instanceof Audio»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: 
		  objectReference: {fileID: 8300000, guid: «value.value.name.uuid(Audio)», type: 3}
		«ELSEIF value instanceof Sprite»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: 
		  objectReference: {fileID: 21300000, guid: «value.value.name.uuid(Sprite)», type: 3}
		«ELSEIF value instanceof Text»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «property»
		  value: «value.value»
		«ENDIF»
	    «ENDIF»
		'''
	}
	
	def yaml1Inner(Value value)
	{
		var property = value.component.property
		if (property == '')
		{
			return ''''''
		}
		if (value.component instanceof EngineComponent)
		{
			if (!property.contains(' '))
			{
				property = 'm_'+property.toFirstUpper
			}
		}
		return
		'''
		«IF value.component instanceof EngineComponent»
		«IF value instanceof Real1»
		«IF (value.component instanceof EngineComponent) && ((value.component as EngineComponent).type == EngineComponentType.RESTITUTION || (value.component as EngineComponent).type == EngineComponentType.FRICTION)»
		m_Material: {fileID: 6200000, guid: «uuid((value.eContainer as Entity).name+'physicsMaterial')», type: 2}
		«ELSEIF (value.component instanceof EngineComponent) && ((value.component as EngineComponent).type == EngineComponentType.FONT)»
		m_FontData:
		  m_Font: {fileID: 10102, guid: 0000000000000000e000000000000000, type: 0}
		  m_BestFit: 1
		  m_MinSize: 1
		  m_MaxSize: 300
		  m_Alignment: 4
		«ELSE»
		«property»: «value.x.toCode»
		«ENDIF»
		«ELSEIF value instanceof Real2»
		«property»: {x: «value.x.toCode», y: «value.y.toCode»}
		«ELSEIF value instanceof Real3»
		«property»: {x: «value.x.toCode», y: «value.y.toCode», z: «value.z.toCode»}
		«ELSEIF value instanceof Real4»
		«property»: {r: «value.x.toCode», g: «value.y.toCode», b: «value.z.toCode», a: «value.w.toCode»}
		«ELSEIF value instanceof Enumeration»
		«property»: «enumerationValue(value.values, value.component.name)»
		«ELSEIF value instanceof Reference»
		«property»: {fileID: «value.entity.name.hash», guid: «value.entity.name.uuid(Prefab)», type: 3}
		«ELSEIF value instanceof Trigger»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Range»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Vector»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Timer»
		«property»: «value.value.toCode»
		«ELSEIF value instanceof Sensor»
		«property»: «enumerationValue(value.values, 'category')»
		«ELSEIF value instanceof Audio»
		«property»: {fileID: 8300000, guid: «value.value.name.uuid(Audio)», type: 3}
		«ELSEIF value instanceof Sprite»
		«property»: {fileID: 21300000, guid: «value.value.name.uuid(Sprite)», type: 3}
		m_Materials:
		  - {fileID: 10754, guid: 0000000000000000f000000000000000, type: 0}
		«ELSEIF value instanceof Text»
		«property»: «value.value»
		«ENDIF»
		«ELSE»
		«IF value instanceof Real1»
		m_SerializedData:
		  «property»: «value.x.toCode»
		«ELSEIF value instanceof Real2»
		m_SerializedData:
		  «property»:
		    x: «value.x.toCode»
		    y: «value.y.toCode»
		«ELSEIF value instanceof Real3»
		m_SerializedData:
		  «property»:
		    x: «value.x.toCode»
		    y: «value.y.toCode»
		    z: «value.z.toCode»
		«ELSEIF value instanceof Real4»
		m_SerializedData:
		  «property»:
		    x: «value.x.toCode»
		    y: «value.y.toCode»
		    z: «value.z.toCode»
		    w: «value.w.toCode»
		«ELSEIF value instanceof Enumeration»
		m_SerializedData:
		  «property»: «enumerationValue(value.values, value.component.name)»
		«ELSEIF value instanceof Reference»
		«property»: {fileID: «value.entity.name.hash», guid: «value.entity.name.uuid(Prefab)», type: 3}
		«ELSEIF value instanceof Trigger»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Range»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Vector»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Timer»
		«property»: «value.value.toCode»
		«ELSEIF value instanceof Sensor»
		«property»: «enumerationValue(value.values, 'category')»
		«ELSEIF value instanceof Audio»
		«property»: {fileID: 8300000, guid: «value.value.name.uuid(Audio)», type: 3}
		«ELSEIF value instanceof Sprite»
		«property»: {fileID: 21300000, guid: «value.value.name.uuid(Sprite)», type: 3}
		«ELSEIF value instanceof Text»
		«property»: «value.value»
		«ENDIF»
		«ENDIF»
		'''
	}
	
	def path(TriggerType type)
	{
		switch type
		{
			case DOWN: '<gamepad>/dpad/down'
			case LEFT: '<gamepad>/dpad/left'
			case RIGHT: '<gamepad>/dpad/right'
			case UP: '<gamepad>/dpad/up'
			case LEFT_TRIGGER: '<gamepad>/leftTrigger'
			case RIGHT_TRIGGER: '<gamepad>/rightTrigger'
			case EAST: '<gamepad>/buttonEast'
			case LEFT_STICK: '<gamepad>/leftStickPress'
			case NORTH: '<gamepad>/buttonNorth'
			case OPTIONAL: '<gamepad>/optional'
			case RIGHT_STICK: '<gamepad>/rightStickPress'
			case SELECT: '<gamepad>/select'
			case SOUTH: '<gamepad>/buttonSouth'
			case START: '<gamepad>/start'
			case WEST: '<gamepad>/buttonWest'
		}
	}
	
	def path(AxisType type)
	{
		switch type
		{
			case LEFT_X: '<gamepad>/leftStick/x'
			case LEFT_Y: '<gamepad>/leftStick/y'
			case RIGHT_X: '<gamepad>/rightStick/x'
			case RIGHT_Y: '<gamepad>/rightStick/y'
			case LEFT_SHOULDER: '<gamepad>/leftShoulder'
			case RIGHT_SHOULDER: '<gamepad>/rightShoulder'
		}
	}
	
	def path(VectorType type)
	{
		switch type
		{
			case MOUSE: '<Mouse>/position'
			case DPAD: '<gamepad>/dpad'
			case LEFT_STICK: '<gamepad>/leftStick'
			case RIGHT_STICK: '<gamepad>/rightStick'
		}
	}
	
	def enumerationValue(EList<Name> values, String enumName)
	{
		var enum = game.enumerations.findFirst[it.name==enumName]
		if (enum === null)
		{
			return 0
		}
		var v = 0;
		for (word : values)
		{
			v += (2 ** enum.values.indexOf(word)) as int
		}
		return v
	}
	
	enum AssetType
	{
		Sprite,Audio,Component,System,Enumeration,
		Prefab,Scene
	}
	
	def uuid(String name, AssetType type)
	{
		(name+type.toString).uuid
	}
	
	def code(UnityComponentType type)
	{
		switch type
		{
			case Rigidbody2D: 50
			case AudioSource: 82
			case AudioListener: 81
			case Light: 108
			case MeshRenderer: 23
			case MeshFilter: 33
			case Camera: 20
			case Transform: 4
			case CircleCollider2D: 58
			case RectTransform: 224
			case SpriteRenderer: 212
			case BoxCollider2D: 61
			case Text: 114
			case ray: 114
			case Canvas: 223
			case CanvasRenderer: 222
			case MonoBehaviour: 114
			case Collider: 0
		}
	}
	
	def codeName(UnityComponentType type)
	{
		switch type
		{
			case Rigidbody2D: 'Rigidbody2D'
			case AudioSource: 'AudioSource'
			case AudioListener: 'AudioListener'
			case Light: 'Light'
			case MeshRenderer: 'MeshRenderer'
			case MeshFilter: 'MeshFilter'
			case Camera: 'Camera'
			case Transform: 'Transform'
			case CircleCollider2D: 'CircleCollider2D'
			case RectTransform: 'RectTransform'
			case SpriteRenderer: 'SpriteRenderer'
			case BoxCollider2D: 'BoxCollider2D'
			case Text: 'MonoBehaviour'
			case ray: 'MonoBehaviour'
			case Canvas: 'Canvas'
			case CanvasRenderer: 'CanvasRenderer'
			case MonoBehaviour: 'MonoBehaviour'
			case Collider: 'Collider'
		}
	}
	
	def property(Name name)
	{
		if (name instanceof EngineComponent)
		{
			switch name.type
			{
				case ACCELERATION: 'acceleration'
				case AUDIO: 'm_audioClip '
				case EARS: ''
				case EMISSION: 'color'
				case EXTENT: 'size'
				case FORCE: 'force'
				case FRICTION: 'friction'
				case INTENSITY: 'intensity'
				case MASS: 'mass'
				case MATERIAL: 'source'
				case MESH: 'source'
				case PERSPECTIVE: ''
				case POSITION: 'localPosition'
				case RADIUS: 'radius'
				case RANGE: 'range'
				case RESTITUTION: 'restitution'
				case ROTATION: 'localEulerAnglesHint'
				case SCALE: 'localScale'
				case SPOT_ANGLE: 'spotAngle'
				case SPRITE: 'sprite'
				case TEXT: 'text'
				case VELOCITY: 'velocity'
				case VIEW_ANGLE: 'field of view'
				case VIEW_DISTANCE: 'far clip plane'
				case CLEAR_COLOR: 'backGroundColor'
				case NUMBER: 'text'
				case CANVAS: 'renderMode'
				case BODY_TYPE: 'bodyType'
				case SERIALIZED_VERSION: 'serializedVersion '
				case CANVAS_SCALER: 'referenceResolution'
				case EVENT_SYSTEM: ''
				case GRAPHIC_RAYCASTER: ''
				case INPUT_MODULE: ''
				case UI_SCALE_MODE: 'uiScaleMode'
				case FONT: 'font'
				case CANVAS_RENDERER: ''
				case ORTHOGRAPHIC: 'orthographic '
				case ORTHOGRAPHIC_SIZE: 'orthographic size'
				case NEAR_CLIP: 'near clip plane'
				case CLEAR_FLAGS: 'clearFlags'
				case LIGHT_TYPE: 'type'
				case SCREEN_EXPAND: 'screenMatchMode'
				case SCREEN_POSITION: 'anchoredPosition'
				case SCREEN_SIZE: 'sizeDelta'
				case DOPPLER_EFFECT: 'DopplerLevel '
				case PITCH: 'pitch'
				case VOLUME: 'volume'
				case TRIGGER: 'isTrigger'
				case RAY: '''«value()» '''.toString
				case PHYSICAL: ''
				case RIGIDBODY_CONSTRAINTS: 'constraints'
				case ZORDER: 'sortingOrder'
			}
		}
		else
		{
			value()
		}
	}
	
	def dimensions(Name name)
	{
		if (name instanceof EngineComponent)
		{
			switch name.type
			{
				case ACCELERATION: 2
				case AUDIO: 1
				case EARS: 1
				case EMISSION: 4
				case EXTENT: 2
				case FORCE: 2
				case FRICTION: 1
				case INTENSITY: 1
				case MASS: 1
				case MATERIAL: 1
				case MESH: 1
				case PERSPECTIVE: 1
				case POSITION: 2
				case RADIUS: 1
				case RANGE: 1
				case RESTITUTION: 1
				case ROTATION: -3
				case SCALE: 2
				case SPOT_ANGLE: 1
				case SPRITE: 1
				case TEXT: 1
				case VELOCITY: 2
				case VIEW_ANGLE: 1
				case VIEW_DISTANCE: 1
				case CLEAR_COLOR: 4
				case NUMBER: -1
				case CANVAS: 1
				case BODY_TYPE: 1
				case SERIALIZED_VERSION: 1
				case CANVAS_SCALER: 2
				case EVENT_SYSTEM: 0
				case GRAPHIC_RAYCASTER: 0
				case INPUT_MODULE: 0
				case UI_SCALE_MODE: 1
				case FONT: 1
				case CANVAS_RENDERER: 0
				case ORTHOGRAPHIC: 1
				case ORTHOGRAPHIC_SIZE: 1
				case NEAR_CLIP: 1
				case CLEAR_FLAGS: 1
				case LIGHT_TYPE: 1
				case SCREEN_EXPAND: 1
				case SCREEN_POSITION: 2
				case SCREEN_SIZE: 2
				case DOPPLER_EFFECT: 1
				case PITCH: 1
				case VOLUME: 1
				case TRIGGER: 1
				case PHYSICAL: 0
				case RAY: 2
				case RIGIDBODY_CONSTRAINTS: 1
				case ZORDER: 1
			}
		}
		else
		{
			// TODO: Expand the rest of the options
			1
		}
	}
	
	def unityComponent(Name name)
	{
		if (name instanceof EngineComponent)
		{
			switch name.type
			{
				case ACCELERATION: Rigidbody2D
				case AUDIO: AudioSource
				case EARS: AudioListener
				case EMISSION: Light
				case EXTENT: BoxCollider2D
				case FORCE: Rigidbody2D
				case FRICTION: Rigidbody2D
				case INTENSITY: Light
				case MASS: Rigidbody2D
				case MATERIAL: MeshRenderer
				case MESH: MeshFilter
				case PERSPECTIVE: Camera
				case POSITION: Transform
				case RADIUS: CircleCollider2D
				case RANGE: Light
				case RESTITUTION: Rigidbody2D
				case ROTATION: Transform
				case SCALE: Transform
				case SPOT_ANGLE: Light
				case SPRITE: SpriteRenderer
				case TEXT: Text
				case VELOCITY: Rigidbody2D
				case VIEW_ANGLE: Camera
				case VIEW_DISTANCE: Camera
				case CLEAR_COLOR: Camera
				case NUMBER: Text
				case CANVAS: Canvas
				case BODY_TYPE: Rigidbody2D
				case SERIALIZED_VERSION: Rigidbody2D
				case CANVAS_SCALER: Text
				case EVENT_SYSTEM: Text
				case GRAPHIC_RAYCASTER: Text
				case INPUT_MODULE: Text
				case UI_SCALE_MODE: Text
				case FONT: Text
				case CANVAS_RENDERER: CanvasRenderer
				case ORTHOGRAPHIC: Camera
				case ORTHOGRAPHIC_SIZE: Camera
				case NEAR_CLIP: Camera
				case CLEAR_FLAGS: Camera
				case LIGHT_TYPE: Light
				case SCREEN_EXPAND: Text
				case SCREEN_POSITION: RectTransform
				case SCREEN_SIZE: RectTransform
				case DOPPLER_EFFECT: AudioSource
				case PITCH: AudioSource
				case VOLUME: AudioSource
				case TRIGGER: BoxCollider2D
				case PHYSICAL: Collider
				case RAY: ray
				case RIGIDBODY_CONSTRAINTS: Rigidbody2D
				case ZORDER: SpriteRenderer
			}
		}
		else
		{
			MonoBehaviour
		}
	}
	
	def rangeEvent(Name component)
	{
		return new EventComponent(component.name + '_range')
	}
	
	def input2DEvent(Name component)
	{
		return new EventComponent(component.name + '_2D')
	}
	
	def enterEvent(Name component)
	{
		return new EventComponent(component.name + '_enter')
	}
	
	def stayEvent(Name component)
	{
		return new EventComponent(component.name + '_stay')
	}
	
	def exitEvent(Name component)
	{
		return new EventComponent(component.name + '_exit')
	}
	
	def timerEvent(Name component)
	{
		return new EventComponent(component.name + '_timeout')
	}
	
	def triggerEvent(Name component)
	{
		return new EventComponent(component.name + '_triggered')
	}
	
	def groups(Control system)
	{
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		
		for (access : EcoreUtil2.getAllContentsOfType(system, Access))
		{
			val component = access.component
			if (game.rangeComponents.exists[it.name==component.name])
			{
				if (loops.exists[it.group == access.group])
				{
					loops.findFirst[it.group == access.group].datas.add(rangeEvent(component))
				}
			}
			else if (game.vectorComponents.exists[it.name==component.name])
			{
				if (loops.exists[it.group == access.group])
				{
					loops.findFirst[it.group == access.group].datas.add(input2DEvent(component))
				}
			}
			else if (component instanceof EngineComponent)
			{
				var engineComponent = new EngineComponent
				engineComponent.name = component.unityComponent.toString
				engineComponent.type = component.type
				
				if (loops.exists[it.group == access.group])
				{
					loops.findFirst[it.group == access.group].datas.add(engineComponent)
				}
			}
			else
			{
				if (loops.exists[it.group == access.group])
				{
					loops.findFirst[it.group == access.group].datas.add(component)
				}
			}
		}
		for (set : EcoreUtil2.getAllContentsOfType(system, ComponentAssignment))
		{
			val component = set.component
			if (game.rangeComponents.exists[it.name==component.name])
			{
				if (loops.exists[it.group == set.group])
				{
					loops.findFirst[it.group == set.group].datas.add(rangeEvent(component))
				}
			}
			else if (component instanceof EngineComponent)
			{
				var engineComponent = new EngineComponent
				engineComponent.name = component.unityComponent.toString
				engineComponent.type = component.type

				if (loops.exists[it.group == set.group])
				{
					loops.findFirst[it.group == set.group].datas.add(engineComponent)
				}	
			}
			else
			{
				if (loops.exists[it.group == set.group])
				{
					loops.findFirst[it.group == set.group].datas.add(component)
				}
			}
		}
		for (routine : EcoreUtil2.getAllContentsOfType(system, SubrutineCall))
		{
			var name = routine.subrutine
			if (name instanceof EngineVoid)
			{
				if (name.type == EngineVoidType.ADD || name.type == EngineVoidType.REMOVE || name.type == EngineVoidType.DESTROY )
				{
					var container = routine.eContainer
					while (container !== system)
					{
						if (container instanceof Loop)
						{
							container.entities = true
						}
						container = container.eContainer
					}
				}
			}
		}
		
		EcoreUtil2.getAllContentsOfType(system, Loop)
	}
	
	
	
	
	
	
	
	def systems()
	{
		for (system : game.systems.filter(games.minim.m.System))
		{	
			var name = system.name
			
			var groups = system.groups
			
			fileSystem.generateFile('''«Systems.folder»/«name».cs''',
			'''
			using System.Collections.Generic;
			using Unity.Collections;
			using Unity.Entities;
			using Unity.Jobs;
			using Unity.Mathematics;
			using UnityEngine;
			using UnityEngine.UI;
			using UnityEngine.SceneManagement;
			
			[DisableAutoCreation]
			public class «name» : ComponentSystem
			{
				«FOR group : groups»
				ComponentGroup «group.group.name»;
				«ENDFOR»
			    
			    protected override void OnCreateManager()
			    {
					«FOR group : groups»
					«group.group.name» = GetComponentGroup(new ComponentType[] {«
					(group.constraints.filter[it.event==EventType.TAG && !it.negated].map['''ComponentType.Create<«it.component.name»>()''']
					+group.constraints.filter[it.event==EventType.ENTER && !it.negated].map['''ComponentType.Create<«enterEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.STAY && !it.negated].map['''ComponentType.Create<«stayEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.EXIT && !it.negated].map['''ComponentType.Create<«exitEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.TIMEOUT && !it.negated].map['''ComponentType.Create<«timerEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.TRIGGER && !it.negated].map['''ComponentType.Create<«triggerEvent(it.component).name»>()''']
					+group.datas.map['''ComponentType.Create<«it.name»>()''']
					+group.constraints.filter[it.event==EventType.TAG && it.negated].map['''ComponentType.Subtractive<«it.component.name»>()''']
					+group.constraints.filter[it.event==EventType.ENTER && it.negated].map['''ComponentType.Subtractive<«enterEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.STAY && it.negated].map['''ComponentType.Subtractive<«stayEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.EXIT && it.negated].map['''ComponentType.Subtractive<«exitEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.TIMEOUT && it.negated].map['''ComponentType.Subtractive<«timerEvent(it.component).name»>()''']
					+group.constraints.filter[it.event==EventType.TRIGGER && it.negated].map['''ComponentType.Subtractive<«triggerEvent(it.component).name»>()''']
					).join(', ')»});
					«ENDFOR»
			    }
			    
			    protected override void OnUpdate()
			    {
					«FOR command : system.commands»
					«run(command)»
					«ENDFOR»
			    }
			}'''
			)
		}
		
		fileSystem.generateFile('''«Systems.folder»/main.cs''',
		'''
		using Unity.Entities;
		using UnityEngine;
		
		public class main : MonoBehaviour
		{
		    public static Unity.Mathematics.Random random;
		
		    [RuntimeInitializeOnLoadMethod(RuntimeInitializeLoadType.BeforeSceneLoad)]
		    static void BeforeScene()
		    {
		        random = new Unity.Mathematics.Random((uint)UnityEngine.Random.Range(0, 10000));
				
		        World.DisposeAllWorlds();
		        var world = new World("world");
				«FOR system : game.systems.filter(games.minim.m.System)»
				world.CreateManager<«system.name»>();
		        «ENDFOR»
		        World.Active = world;
		        
		        «FOR entity : game.mainEntities»
		        Instantiate(Resources.Load<GameObject>("«entity.name»"));
		        «ENDFOR»
		        
		        ScriptBehaviourUpdateOrder.UpdatePlayerLoop(world);
		    }
		}
		'''
		)
	}
	
	def value(){'value'}
	
	
	def CharSequence run(Command command)
	{
		if (command instanceof Loop)
		{
			val group = command.group.name
			var customComponents = new HashSet<String>
			var unityComponents = new HashSet<String>
			
			for (component : command.datas)
			{
				if (!(component instanceof EngineComponent) && !(component instanceof EventComponent))
				{
					if (game.referenceComponents.exists[it.name==component.name]
						|| game.spriteComponents.exists[it.name==component.name]
						|| game.audioComponents.exists[it.name==component.name]
						|| game.timerComponents.exists[it.name==component.name]
						|| game.sensorComponents.exists[it.name==component.name]
						|| game.triggerComponents.exists[it.name==component.name]
						|| game.rangeComponents.exists[it.name==component.name]
						|| game.vectorComponents.exists[it.name==component.name]
					)
					{
						unityComponents.add(component.name)
					}
					else
					{
						customComponents.add(component.name)
					}
				}
				else if (component instanceof EngineComponent)
				{
					unityComponents.add(component.unityComponent.toString)
				}
				else if (component instanceof EventComponent)
				{
					customComponents.add(component.name)
				}
			}

			'''
			«FOR c : customComponents»
			var «c»_array_«group» = «group».GetComponentDataArray<«c»>();
			«ENDFOR»
			«FOR u : unityComponents»
			var «u»_array_«group» = «group».GetComponentArray<«u»>();
			«ENDFOR»
			«IF command.entities»
			var entity_array_«group» = «group».GetEntityArray();
			«ENDIF»
			
			for (var i_«group» = 0; i_«group» < «group».CalculateLength(); i_«group»++)
			{
				«FOR c : customComponents»
				var «c»_«group» = «c»_array_«group»[i_«group»];
				«ENDFOR»
				«FOR u : unityComponents»
				var «u»_«group» = «u»_array_«group»[i_«group»];
				«ENDFOR»
				«IF command.entities»
				var entity_«group» = entity_array_«group»[i_«group»];
				«ENDIF»
				«IF customComponents.size > 0 || unityComponents.size > 0 || command.entities»
				
				«ENDIF»
				«FOR comm : command.commands»
				«run(comm)»
				«ENDFOR»
			}
			'''
		}
		else if (command instanceof Check)
		{
			var condition = command.condition as Condition
			'''
			if («condition.condition.toCode(FieldType.VALUE)»)
			{
				«FOR comm : command.condition.commands»
				«run(comm)»
				«ENDFOR»
			}
			«FOR c : command.conditions»
			else if («(c as Condition).condition.toCode(FieldType.VALUE)»)
			{
				«FOR comm : c.commands»
				«run(comm)»
				«ENDFOR»
			}
			«ENDFOR»
			«IF command.commands.size > 0»
			else
			{
				«FOR comm : command.commands»
				«run(comm)»
				«ENDFOR»
			}
			«ENDIF»'''
		}
		else if (command instanceof While)
		{
			return
			'''
			while («command.condition.toCode(FieldType.VALUE)»)
			{
				«FOR comm : command.commands»
				«comm.run»
				«ENDFOR»
			}
			'''
		}
		else if (command instanceof ComponentAssignment)
		{
			var operator = command.assignment.r
			val component = command.component
			var group = command.group.name
			var componentName = component.unityComponent.toString
			if (game.referenceComponents.exists[it.name==component.name]
				|| game.spriteComponents.exists[it.name==component.name]
				|| game.audioComponents.exists[it.name==component.name]
				|| game.timerComponents.exists[it.name==component.name]
				|| game.sensorComponents.exists[it.name==component.name]
				|| game.triggerComponents.exists[it.name==component.name]
				|| game.rangeComponents.exists[it.name==component.name]
				|| game.vectorComponents.exists[it.name==component.name]
			)
			{
				componentName = component.name
			}
			var property = component.property.replace(' ','')
			'''
			«IF component.isReferenceType»
			var «property»_«group» «operator» «componentName»_«group».«property»;
			«IF component.dimensions == 1»
			«property»_«group» «operator» «command.expression.toCode(FieldType.VALUE)»;
			«ELSEIF component.dimensions == 2»
			«property»_«group».x «operator» «command.expression.toCode(FieldType.X)»;
			«property»_«group».y «operator» «command.expression.toCode(FieldType.Y)»;
			«ELSEIF component.dimensions == 3»
			«property»_«group».x «operator» «command.expression.toCode(FieldType.X)»;
			«property»_«group».y «operator» «command.expression.toCode(FieldType.Y)»;
			«property»_«group».z «operator» «command.expression.toCode(FieldType.Z)»;
			«ELSEIF component.dimensions == 4»
			«property»_«group».x «operator» «command.expression.toCode(FieldType.X)»;
			«property»_«group».y «operator» «command.expression.toCode(FieldType.Y)»;
			«property»_«group».z «operator» «command.expression.toCode(FieldType.Z)»;
			«property»_«group».w «operator» «command.expression.toCode(FieldType.W)»;
			«ELSEIF component.dimensions == -3»
			«property»_«group».z «operator» «command.expression.toCode(FieldType.VALUE)»;
			«ELSEIF component.dimensions == -1»
			«property»_«group» «operator» («command.expression.toCode(FieldType.VALUE)»).ToString();
			«ENDIF»
			
			«componentName»_«group».«property» = «property»_«group»;
			«ELSE»
			«component.name»_«group».«value» «operator» «command.expression.toCode(FieldType.VALUE)»;
			«component.name»_array_«group»[i_«group»] = «component.name»_«group»;
			«ENDIF»
			'''
		}
		else if (command instanceof VariableAssignment)
		{
			var container = command.getContainerOfType(Control)
			var declared = new HashSet<String>
			declared.addAll(container.commands.subList(0, container.commands.indexOf(command)).filter(VariableAssignment).map[variable.name])
			declared.addAll(container.commands.subList(0, container.commands.indexOf(command)).filter(Initialization).map[variable.name])
			var superContainer = container.eContainer
			while( superContainer !== null)
			{
				if (superContainer instanceof Control)
				{
					if (!(superContainer instanceof Check))
					{	
						declared.addAll(superContainer.commands.subList(0, superContainer.commands.indexOf(container)).filter(Initialization).map[variable.name])
						declared.addAll(superContainer.commands.subList(0, superContainer.commands.indexOf(container)).filter(VariableAssignment).map[variable.name])
					}
					container = superContainer
				}
				superContainer = superContainer.eContainer
			}
			var extra = ''
			var expression = command.expression
			if (expression instanceof Call)
			{
				var function = expression.function
				if (function instanceof EngineTransformation)
				{
					if (function.type == EngineTransformationType.CREATE)
					{
						var customComponents = new HashSet<String>
						var unityComponents = new HashSet<String>
						container = command.getContainerOfType(Control)
						var accesses = EcoreUtil2.getAllContentsOfType(container, ComponentAssignment).filter[it.group.name == command.variable.name]
						for (access : accesses)
						{
							val component = access.component
							if (!(component instanceof EngineComponent) && !(component instanceof EventComponent))
							{
								if (game.referenceComponents.exists[it.name==component.name]
									|| game.spriteComponents.exists[it.name==component.name]
									|| game.audioComponents.exists[it.name==component.name]
									|| game.timerComponents.exists[it.name==component.name]
									|| game.sensorComponents.exists[it.name==component.name]
									|| game.triggerComponents.exists[it.name==component.name]
									|| game.rangeComponents.exists[it.name==component.name]
									|| game.vectorComponents.exists[it.name==component.name]
								)
								{
									unityComponents.add(component.name)
								}
								else
								{
									customComponents.add(component.name)
								}
							}
							else if (component instanceof EngineComponent)
							{
								unityComponents.add(component.unityComponent.toString)
							}
							else if (component instanceof EventComponent)
							{
								customComponents.add(component.name)
							}
						}
						extra = 
						'''
						var «command.variable.name»_entity = «command.variable.name».GetComponent<GameObjectEntity>().Entity;
						«FOR c : unityComponents»
						var «c»_«command.variable.name» = EntityManager.GetComponentObject<«c»>(«command.variable.name»_entity);
						«ENDFOR»
						«FOR c : customComponents»
						var «c»_«command.variable.name» = EntityManager.GetComponentData<«c»>(«command.variable.name»_entity);
						«ENDFOR»
						'''
					}
				}
			}
			if (declared.contains(command.variable.name))
			{
				'''
				«command.variable.name» = «command.expression.toCode(FieldType.VALUE)»;
				«IF extra != ''»
				«extra»
				«ENDIF»
				'''
			}
			else
			{
				'''
				var «command.variable.name» = «command.expression.toCode(FieldType.VALUE)»;
				«IF extra != ''»
				«extra»
				«ENDIF»
				'''
			}
		}
		else if (command instanceof Break)
		{
			'''
			break;
			'''
		}
		else if (command instanceof Initialization)
		{
			'''var «command.variable.name» = 0;'''
		}
		else if (command instanceof SubrutineCall)
		{
			var name = command.subrutine
			if (name instanceof EngineVoid)
			{
				switch name.type
				{
					// TODO make added components visible in editor.
					case ADD: 
					{
						var type = command.parameters.get(0) as Pop
						var group = command.parameters.get(1) as Pop
						var referenceComponent = type.variable.isReferenceType
						return 
						'''
						if (!EntityManager.HasComponent<«type.variable.name»>(entity_«group.variable.name»))
						{
							«IF referenceComponent»
							var go = EntityManager.GetComponentObject<Transform>(entity_«group.variable.name»).gameObject;
							EntityManager.AddComponentObject(entity_b, go.AddComponent<«type.variable.name»>());
							«ELSE»
							EntityManager.AddComponentData(entity_«group.variable.name», new «type.variable.name»());
							«ENDIF»
						}
						'''
					}
					case CREATE: 
					{
						var access = command.parameters.get(0) as Access
						return
						'''
						Object.Instantiate(«access.toCode(FieldType.VALUE)»);
						'''
					}
					case DESTROY: 
					{
						var parameter = command.parameters.get(0)
						if (parameter instanceof Pop)
						{
							return
							'''
							if (EntityManager.HasComponent<Transform>(entity_«parameter.variable.name»))
							{
							    Object.Destroy(EntityManager.GetComponentObject<Transform>(entity_«parameter.variable.name»).gameObject,0.01f);
							}
							else
							{
							    Object.Destroy(EntityManager.GetComponentObject<RectTransform>(entity_«parameter.variable.name»).gameObject,0.01f);
							}
							'''
						}		
					}
					case REMOVE: 
					{
						var type = command.parameters.get(0) as Pop
						var group = command.parameters.get(1) as Pop
						return
						'''
						if (EntityManager.HasComponent<«type.variable.name»>(entity_«group.variable.name»))
						{
							PostUpdateCommands.RemoveComponent<«type.variable.name»>(entity_«group.variable.name»);
						}
						'''
					}
					
				}
			}
			else
			{
				return
				'''
				«command.subrutine.name» («command.parameters.map[toCode(FieldType.VALUE)].join(',')»); 
				'''
			}
			
		}
	}
	
	def r(AssignmentType type)
	{
		switch type
		{
			case AND: '&='
			case DECREASE: '-='
			case DIVIDE: '/='
			case INCREASE: '+='
			case MODULUS: '%='
			case MULTIPLY: '*='
			case OR: '|='
			case SET: '='			
		}
	}
	
	def r(RelationType type)
	{
		switch type
		{
			case EQUAL: '=='
			case NOTEQUAL: '!='
			case OVER: '>'
			case OVEROREQUAL: '>='
			case UNDER: '<'
			case UNDEROREQUAL: '<='
		}		
	}
	
	def r(FieldType type)
	{
		switch type
		{
			case VALUE: ''
			case X: '.x'
			case Y: '.y'
			case Z: '.z'
			case W: '.w'
		}
	}
	
	def isReferenceType(Name component)
	{
		if (component instanceof EngineComponent) return true
		
		if (game.referenceComponents.exists[it.name==component.name]
						|| game.spriteComponents.exists[it.name==component.name]
						|| game.audioComponents.exists[it.name==component.name]
						|| game.timerComponents.exists[it.name==component.name]
						|| game.sensorComponents.exists[it.name==component.name]
						|| game.triggerComponents.exists[it.name==component.name]
						|| game.rangeComponents.exists[it.name==component.name]
						|| game.vectorComponents.exists[it.name==component.name]
					)
					{
						return true
					}
		return false
	}
	
	def String toCode(Expression expression, FieldType fieldType)
	{
		var result = ''
		if (expression instanceof Access)
		{
			val component = expression.component
			var name = component.name
			var value = value()
			if (component instanceof EngineComponent)
			{
				name = component.unityComponent.name
				value = component.property	
			}
			else if (game.rangeComponents.exists[it.name == component.name])
			{
				name = rangeEvent(component).name
			}
			var group = expression.group.name
			
			result = '''«name»_«group».«value»«fieldType.r»'''
			
			if (component instanceof EngineComponent)
			{
				if (component.type == EngineComponentType.NUMBER)
				{
					result = 'float.Parse ('+result+')'
				}
			}
		}
		else if (expression instanceof Pop)
		{
			result = '''«expression.variable.name»«fieldType.r»'''
		}
		else if (expression instanceof Call)
		{
			var transformation = expression.function
			if (transformation instanceof EngineTransformation)
			{
				switch (transformation.type)
				{
					case ABS: result = '''math.abs(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case COS: result = '''math.cos(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case EXP: result = '''math.exp(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case LOG: result = '''math.log(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case SIN: result = '''math.sin(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case SQRT: result = '''math.sqrt(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case TAN: result = '''math.tan(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case RANDOM: result = '''main.random.NextFloat(«expression.parameters.get(0).toCode(fieldType)».x,«expression.parameters.get(0).toCode(fieldType)».y)«fieldType.r»'''
					case CREATE: result = '''Object.Instantiate(«expression.parameters.get(0).toCode(fieldType)»)«fieldType.r»'''
					case JOIN: 
					{
						switch fieldType
						{
							case VALUE: 
							{
								if (expression.parameters.size == 2)
								{
									return '''new float2(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)»)«fieldType.r»'''
								}
								else if (expression.parameters.size == 3)
								{
									return '''new float3(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)», «expression.parameters.get(2).toCode(fieldType)»)«fieldType.r»'''
								}
								else if (expression.parameters.size == 4)
								{
									return '''new float4(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)», «expression.parameters.get(2).toCode(fieldType)», «expression.parameters.get(3).toCode(fieldType)»)«fieldType.r»'''
								}
							}
							case X: 
							{
								return '''«expression.parameters.get(0).toCode(FieldType.VALUE)»'''
							}
							case Y: 
							{
								return '''«expression.parameters.get(1).toCode(FieldType.VALUE)»'''
							}
							case Z: 
							{
								return '''«expression.parameters.get(2).toCode(FieldType.VALUE)»'''
							}
							case W: 
							{
								return '''«expression.parameters.get(3).toCode(FieldType.VALUE)»'''
							}
							
						}
						if (expression.parameters.size == 2)
						{
							return '''new float2(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)»)«fieldType.r»'''
						}
						else if (expression.parameters.size == 3)
						{
							return '''new float3(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)», «expression.parameters.get(2).toCode(fieldType)»)«fieldType.r»'''
						}
						else if (expression.parameters.size == 4)
						{
							return '''new float4(«expression.parameters.get(0).toCode(fieldType)», «expression.parameters.get(1).toCode(fieldType)», «expression.parameters.get(2).toCode(fieldType)», «expression.parameters.get(3).toCode(fieldType)»)«fieldType.r»'''
						}
					}
					
					case X: 
					{
						return '''«expression.parameters.get(0).toCode(fieldType)».x'''
					}
					case Y: 
					{
						return '''«expression.parameters.get(0).toCode(fieldType)».y'''
					}
					case Z: 
					{
						return '''«expression.parameters.get(0).toCode(fieldType)».z'''
					}
					case W: 
					{
						return '''«expression.parameters.get(0).toCode(fieldType)».w'''
					}
				}
			}
		}
		else if (expression instanceof Plus)
		{
			result = '''«expression.left.toCode(fieldType)» + «expression.right.toCode(fieldType)»'''
		}
		else if (expression instanceof Minus)
		{
			result = '''«expression.left.toCode(fieldType)» - «expression.right.toCode(fieldType)»'''
		}
		else if (expression instanceof Times)
		{
			result = '''«expression.left.toCode(fieldType)» * «expression.right.toCode(FieldType.VALUE)»'''
		}
		else if (expression instanceof Divide)
		{
			result = '''«expression.left.toCode(fieldType)» / «expression.right.toCode(FieldType.VALUE)»'''
		}
		else if (expression instanceof Modulus)
		{
			result = '''«expression.left.toCode(fieldType)» % «expression.right.toCode(FieldType.VALUE)»'''
		}
		else if (expression instanceof Bitwise)
		{
			result = '''«expression.left.toCode(fieldType)» «expression.op» «expression.right.toCode(fieldType)»'''
		}
		else if (expression instanceof Increment)
		{
			result = '''++«expression.left.toCode(fieldType)»'''
		}
		else if (expression instanceof Decrement)
		{
			result = '''--«expression.left.toCode(fieldType)»'''
		}
		else if (expression instanceof Or)
		{
			result = '''«expression.left.toCode(fieldType)» || «expression.right.toCode(fieldType)»'''
		}
		else if (expression instanceof And)
		{
			result = '''«expression.left.toCode(fieldType)» && «expression.right.toCode(fieldType)»'''
		}
		else if (expression instanceof Not)
		{
			result = '''! («expression.expression.toCode(fieldType)»)'''
		}
		else if (expression instanceof BooleanBrackets)
		{
			result = '''(«expression.expression.toCode(fieldType)»)'''
		}
		else if (expression instanceof ArithmeticBrackets)
		{
			result = '''(«expression.expression.toCode(fieldType)»)'''
		}
		else if (expression instanceof Comparison)
		{
			result = '''«expression.left.toCode(fieldType)» «expression.type.toCode» «expression.right.toCode(fieldType)»'''
		}
		
		return result
	}
	
	def toCode(RelationType type)
	{
		switch type
		{
			case OVER: return '>'
			case UNDER: return '<'
			case OVEROREQUAL: return '>='
			case UNDEROREQUAL: return '<='
			case EQUAL: return '=='
			case NOTEQUAL: return '!='
		}
	}
	
	def toCode(Number n)
	{
		var result = ''
		if (n instanceof Decimal)
		{
			if (n.negative) 
			{
				result += '-'
			}
			result += n.wholePart
			if (n.decimalPart != 0)
			{
				result += '.' + n.decimalPart
			}
			return result
		}
	}
}

class EventComponent extends NameImpl
{
	new (String name)
	{
		this.name = name
	}
}
