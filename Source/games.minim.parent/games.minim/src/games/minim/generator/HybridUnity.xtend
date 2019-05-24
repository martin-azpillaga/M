package games.minim.generator

import games.minim.m.Access
import games.minim.m.And
import games.minim.m.ArithmeticBrackets
import games.minim.m.Audio
import games.minim.m.Axis
import games.minim.m.Bitwise
import games.minim.m.BooleanBrackets
import games.minim.m.Break
import games.minim.m.Button
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
import games.minim.m.EngineVoidType
import games.minim.m.Entity
import games.minim.m.Enumeration
import games.minim.m.Expression
import games.minim.m.Game
import games.minim.m.Immediate
import games.minim.m.Increment
import games.minim.m.Input2
import games.minim.m.Input2D
import games.minim.m.Join
import games.minim.m.Loop
import games.minim.m.LoopEnum
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
import games.minim.m.Tag
import games.minim.m.Text
import games.minim.m.Timer
import games.minim.m.Times
import games.minim.m.Trigger
import games.minim.m.Value
import games.minim.m.VariableAssignment
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
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2

import static games.minim.generator.Folder.*
import static games.minim.generator.HybridUnity.AssetType.*
import static games.minim.generator.UnityComponent.*

import static extension org.eclipse.xtext.EcoreUtil2.*

enum Folder {Assets,Code,Tests,Packages,Settings,
		Clips,Meshes,Materials,Sprites,Audios,PhysicsMaterials,
		Components,Systems,Enumerations,Prefabs,Scenes,Animators,Tools}
enum UnityComponent
{
	Transform, Rigidbody2D, Camera,
	CircleCollider2D, BoxCollider2D, 
	AudioListener, Light,
	MonoBehaviour, RectTransform,
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
		
		/*
		if (game.entities.size > 0)
		{
			fileSystem.generateFile('''«Tests.folder»/Tests.asmdef''',
			'''
			{
				"name": "Tests",
			    "references": 
			    [
			        "Code",
			        "Unity.Entities",
			        "Unity.Burst",
			        "Unity.Mathematics",
			        "Unity.Entities.Tests",
			        "Unity.Entities.Hybrid",
			    ],
			    "optionalUnityReferences": [
			        "TestAssemblies"
			    ]
			}'''
			)
		}
		
		fileSystem.generateFile('''«Tools.folder»/Editor.asmdef''',
		'''
		{
			"name": "Editor",
		    "references": 
		    [
		        "Code",
		        "Unity.Entities",
		        "Unity.Burst",
		        "Unity.Mathematics",
		        "Unity.Entities.Hybrid"
		    ]
		}'''
		)*/
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
				«value.name» = 0b«Math.pow(10,enumeration.values.indexOf(value)) as int»,
				«ENDFOR»
			}
			''')
			
			generateMetaFile(Enumerations.folder, enumeration.name+'_type.cs', enumeration)
		}
	}
	
	def components()
	{
		for (component : game.sensorComponents)
		{
			var name = component.name
			fileSystem.generateFile('''«Components.folder»/«name».cs''',
			'''
			using System;
			using UnityEngine;
			using Unity.Entities;
			
			public class «name» : MonoBehaviour
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
			        if (active && !manager.HasComponent<detector_triggered>(entity))
			        {
			            manager.AddComponentData(entity, new detector_triggered { });
			        }
			        else if (!active && manager.HasComponent<detector_triggered>(entity))
			        {
			            manager.RemoveComponent<detector_triggered>(entity);
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
		for (component : game.input2DComponents)
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
		«component.unityComponent.toString»:
		  m_GameObject: {fileID: «entity.name.hash»}
		  «FOR value : yamlComponents.get(component)»
		  «value.yaml1Inner»
		  «ENDFOR»
		  «IF !(component instanceof EngineComponent)»
		  m_Script: {fileID: 11500000, guid: «component.name.uuid», type: 3}
		  «ENDIF»
		  «IF component.script !== null»
		  m_Script: {fileID: «component.script», guid: f70555f144d8491a825f0804e09c671c, type: 3}
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
		    - target: {fileID: «entity.base.name.hash», guid: «entity.base.name.uuid(Prefab)», type: 3}
		      propertyPath: m_Name
		      value: «entity.name»
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
		  value: «value.button.path»
		«ELSEIF value instanceof Range»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.axis.path»
		«ELSEIF value instanceof Input2D»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Timer»
		- target: {fileID: «value.component.componentHash(entity)», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «value.seconds.toCode»
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
		  value: «value.button.path»
		«ELSEIF value instanceof Range»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.axis.path»
		«ELSEIF value instanceof Input2D»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: value.m_SingletonActionBindings.Array.data[0].m_Path
		  value: «value.value.path»
		«ELSEIF value instanceof Timer»
		- target: {fileID: «(entity.name+value.component.name).hash», guid: «entity.name.uuid(Prefab)», type: 3}
		  propertyPath: «value()»
		  value: «value.seconds.toCode»
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
		    m_Path: «value.button.path»
		    m_Action: Value
		«ELSEIF value instanceof Range»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.axis.path»
		    m_Action: Value
		«ELSEIF value instanceof Input2D»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Timer»
		«property»: «value.seconds.toCode»
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
		    m_Path: «value.button.path»
		    m_Action: Value
		«ELSEIF value instanceof Range»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.axis.path»
		    m_Action: Value
		«ELSEIF value instanceof Input2D»
		«property»:
		  m_Name: Value
		  m_SingletonActionBindings:
		  - m_Name: 
		    m_Path: «value.value.path»
		    m_Action: Value
		«ELSEIF value instanceof Timer»
		«property»: «value.seconds.toCode»
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
	
	def path(Button button)
	{
		switch button
		{
			case DOWN: '<gamepad>/buttonSouth'
			case LEFT: '<gamepad>/buttonWest'
			case RIGHT: '<gamepad>/buttonEast'
			case UP: '<gamepad>/buttonNorth'
			case LEFT_TRIGGER: '<gamepad>/leftTrigger'
			case RIGHT_TRIGGER: '<gamepad>/rightTrigger'
			
		}
	}
	
	def path(Axis axis)
	{
		switch axis
		{
			case LEFT_X: '<gamepad>/leftStick/x'
			case LEFT_Y: '<gamepad>/leftStick/y'
			case RIGHT_X: '<gamepad>/rightStick/x'
			case RIGHT_Y: '<gamepad>/rightStick/y'			
		}
	}
	
	def path(Input2 input)
	{
		switch input
		{
			case MOUSE: '<Mouse>/position'
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
	
	def values(Value value)
	{
		if (value instanceof Tag) new BasicEList<Object>
		else if (value instanceof Real1) #[value.x]
		else if (value instanceof Real2) #[value.x,value.y]
		else if (value instanceof Real3) #[value.x,value.y,value.z]
		else if (value instanceof Real4) #[value.x,value.y,value.z,value.w]
		else if (value instanceof Reference) #[value.entity]
		else if (value instanceof Enumeration) value.values
		else if (value instanceof Timer) #[value.seconds]
		else if (value instanceof Sensor) value.values
		else if (value instanceof Trigger) #[value.button]
		else if (value instanceof Range) #[value.axis]
		else if (value instanceof Sprite) #[value.value]
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
	
	def code(UnityComponent component)
	{
		switch component
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
			case MonoBehaviour: 114
			case Canvas: 223
			case CanvasRenderer: 222
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
			}
		}
		else
		{
			value()
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
				case TEXT: MonoBehaviour
				case VELOCITY: Rigidbody2D
				case VIEW_ANGLE: Camera
				case VIEW_DISTANCE: Camera
				case CLEAR_COLOR: Camera
				case NUMBER: MonoBehaviour
				case CANVAS: Canvas
				case BODY_TYPE: Rigidbody2D
				case SERIALIZED_VERSION: Rigidbody2D
				case CANVAS_SCALER: MonoBehaviour
				case EVENT_SYSTEM: MonoBehaviour
				case GRAPHIC_RAYCASTER: MonoBehaviour
				case INPUT_MODULE: MonoBehaviour
				case UI_SCALE_MODE: MonoBehaviour
				case FONT: MonoBehaviour
				case CANVAS_RENDERER: CanvasRenderer
				case ORTHOGRAPHIC: Camera
				case ORTHOGRAPHIC_SIZE: Camera
				case NEAR_CLIP: Camera
				case CLEAR_FLAGS: Camera
				case LIGHT_TYPE: Light
				case SCREEN_EXPAND: MonoBehaviour
				case SCREEN_POSITION: RectTransform
				case SCREEN_SIZE: RectTransform
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
			else if (game.input2DComponents.exists[it.name==component.name])
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
				
				if (component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER)
				{
					engineComponent.name = 'Text'
				}
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
				if (component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER)
				{
					engineComponent.name = 'Text'
				}
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
		for (system : game.systems)
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
					(group.tags.map['''ComponentType.Create<«it.name»>()''']
					+group.enters.map['''ComponentType.Create<«enterEvent(it).name»>()''']
					+group.stays.map['''ComponentType.Create<«stayEvent(it).name»>()''']
					+group.exits.map['''ComponentType.Create<«exitEvent(it).name»>()''']
					+group.timers.map['''ComponentType.Create<«timerEvent(it).name»>()''']
					+group.triggers.map['''ComponentType.Create<«triggerEvent(it).name»>()''']
					+group.datas.map['''ComponentType.Create<«it.name»>()''']
					+group.exclusions.map['''ComponentType.Subtractive<«it.name»>()''']
					).join(', ')»});
					«ENDFOR»
					«FOR group : groups»
					RequireForUpdate(«group.group.name»);
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
				«FOR system : game.systems»
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
						|| game.input2DComponents.exists[it.name==component.name]
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
					if (component.unityComponent == MonoBehaviour)
					{
						if (component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER)
						{
							unityComponents.add('Text')
						}
					}
					else
					{
						unityComponents.add(component.unityComponent.toString)
					}
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
			if («condition.condition.toCode»)
			{
				«FOR comm : command.condition.commands»
				«run(comm)»
				«ENDFOR»
			}
			«FOR c : command.conditions»
			else if («(c as Condition).condition.toCode»)
			{
				«FOR comm : c.commands»
				«run(comm)»
				«ENDFOR»
			}
			«ENDFOR»
			«IF command.elseCommands.size > 0»
			else
			{
				«FOR comm : command.elseCommands»
				«run(comm)»
				«ENDFOR»
			}
			«ENDIF»'''
		}
		else if (command instanceof While)
		{
			return
			'''
			while («command.condition.toCode»)
			{
				«FOR comm : command.commands»
				«comm.run»
				«ENDFOR»
			}
			'''
		}
		else if (command instanceof LoopEnum)
		{
			return 
			'''
			foreach («command.component.name»_type «command.variable.name» in System.Enum.GetValues(typeof(«command.component.name»_type)))
			{
				«FOR comm : command.commands»
				«comm.run»
				«ENDFOR»
			}'''
		}
		else if (command instanceof ComponentAssignment)
		{
			var component = command.component
			var group = command.group.name
			'''
			«IF component instanceof EngineComponent»
			«IF component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER»
			var «component.property»_«group» = Text_«group».«component.property»;
			«ELSE»			
			var «component.property»_«group» = «component.unityComponent.toString»_«group».«component.property»;
			«ENDIF»
			var new_«component.property»_«group» = «command.expression.toCode»;
			«IF component.dimensions == 1»
			«component.property»_«group» = new_«component.property»_«group»;
			«ELSEIF component.dimensions == 2»
			«component.property»_«group».x = new_«component.property»_«group».x;
			«component.property»_«group».y = new_«component.property»_«group».y;
			«ELSEIF component.dimensions == 3»
			«component.property»_«group».x = new_«component.property»_«group».x;
			«component.property»_«group».y = new_«component.property»_«group».y;
			«component.property»_«group».z = new_«component.property»_«group».z;
			«ELSEIF component.dimensions == 4»
			«component.property»_«group».x = new_«component.property»_«group».x;
			«component.property»_«group».y = new_«component.property»_«group».y;
			«component.property»_«group».z = new_«component.property»_«group».z;
			«component.property»_«group».w = new_«component.property»_«group».w;
			«ELSEIF component.dimensions == -3»
			«component.property»_«group».z = new_«component.property»_«group»;
			«ELSEIF component.dimensions == -1»
			«component.property»_«group» = new_«component.property»_«group».ToString();
			«ENDIF»
			
			«IF component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER»
			Text_«group».«component.property» = «component.property»_«group»;
			«ELSE»
			«component.unityComponent.toString»_«group».«component.property» = «component.property»_«group»;
			«ENDIF»
			«ELSE»
			«component.name»_«group».«value» = «command.expression.toCode»;
			«ENDIF»
			'''
		}
		else if (command instanceof VariableAssignment)
		{
			var container = command.getContainerOfType(Control)
			var declared = new HashSet<String>
			declared.addAll(container.commands.subList(0, container.commands.indexOf(command)).filter(VariableAssignment).map[variable.name])
			var superContainer = container.eContainer
			while( superContainer !== null)
			{
				if (superContainer instanceof Control)
				{
					if (!(superContainer instanceof Check))
					{	
						declared.addAll(superContainer.commands.subList(0, superContainer.commands.indexOf(container)).filter(VariableAssignment).map[variable.name])
					}
					container = superContainer
				}
				superContainer = superContainer.eContainer
			}
			if (declared.contains(command.variable.name))
			{
				'''
				«command.variable.name» = «command.expression.toCode»;
				'''
			}
			else
			{
				'''
				var «command.variable.name» = «command.expression.toCode»;
				'''
			}
		}
		else if (command instanceof Break)
		{
			'''
			break;
			'''
		}
		else if (command instanceof SubrutineCall)
		{
			var name = command.subrutine
			if (name instanceof EngineVoid)
			{
				switch name.type
				{
					case ADD: 
					{
						var type = command.parameters.get(0) as Pop
						var group = command.parameters.get(1) as Pop
						return 
						'''
						if (!EntityManager.HasComponent<«type.variable.name»>(entity_«group.variable.name»))
						{
							PostUpdateCommands.AddComponent(entity_«group.variable.name», new «type.variable.name»());
						}
						'''
					}
					case CREATE: 
					{
						var access = command.parameters.get(0) as Access
						return
						'''
						Object.Instantiate(«access.toCode»);
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
				«command.subrutine.name» («command.parameters.map[toCode].join(',')»); 
				'''
			}
			
		}
	}
	
	def r(RelationType comparator)
	{
		switch comparator
		{
			case EQUAL: '=='
			case NOTEQUAL: '!='
			case OVER: '>'
			case OVEROREQUAL: '>='
			case UNDER: '<'
			case UNDEROREQUAL: '<='
		}		
	}
	
	def String toCode(Expression expression)
	{
		var result = ''
		if (expression instanceof Access)
		{
			val component = expression.component
			var name = component.name
			var value = value()
			if (component instanceof EngineComponent)
			{
				if (component.type == EngineComponentType.TEXT || component.type == EngineComponentType.NUMBER)
				{
					name = 'Text'
				}
				else
				{
					name = component.unityComponent.name
				}
				value = component.property	
			}
			else if (game.rangeComponents.exists[it.name == component.name])
			{
				name = rangeEvent(component).name
			}
			var group = expression.group.name
			
			result = '''«name»_«group».«value»'''
			
			if (component instanceof EngineComponent)
			{
				if (component.type == EngineComponentType.NUMBER)
				{
					result = 'float.Parse ('+result+')'
				}
			}
		}
		else if (expression instanceof Immediate)
		{
			result = '''«expression.value.toCode»f'''
		}
		else if (expression instanceof Pop)
		{
			result = '''«expression.variable.name»'''
		}
		else if (expression instanceof Call)
		{
			var transformation = expression.function
			if (transformation instanceof EngineTransformation)
			{
				switch (transformation.type)
				{
					case ABS: result = '''math.abs(«expression.parameters.get(0).toCode»)'''
					case COS: result = '''math.cos(«expression.parameters.get(0).toCode»)'''
					case EXP: result = '''math.exp(«expression.parameters.get(0).toCode»)'''
					case LOG: result = '''math.log(«expression.parameters.get(0).toCode»)'''
					case SIN: result = '''math.sin(«expression.parameters.get(0).toCode»)'''
					case SQRT: result = '''math.sqrt(«expression.parameters.get(0).toCode»)'''
					case TAN: result = '''math.tan(«expression.parameters.get(0).toCode»)'''
					case RANDOM: result = '''main.random.NextFloat(«expression.parameters.get(0).toCode».x,«expression.parameters.get(0).toCode».y)'''
				}
			}
		}
		else if (expression instanceof Join)
		{
			result = '''new float2(«expression.x.toCode», «expression.y.toCode»)'''
		}
		else if (expression instanceof Plus)
		{
			result = '''«expression.left.toCode» + «expression.right.toCode»'''
		}
		else if (expression instanceof Minus)
		{
			result = '''«expression.left.toCode» - «expression.right.toCode»'''
		}
		else if (expression instanceof Times)
		{
			result = '''«expression.left.toCode» * «expression.right.toCode»'''
		}
		else if (expression instanceof Divide)
		{
			result = '''«expression.left.toCode» / «expression.right.toCode»'''
		}
		else if (expression instanceof Modulus)
		{
			result = '''«expression.left.toCode» % «expression.right.toCode»'''
		}
		else if (expression instanceof Bitwise)
		{
			result = '''«expression.left.toCode» «expression.op» «expression.right.toCode»'''
		}
		else if (expression instanceof Increment)
		{
			result = '''«expression.left.toCode»++'''
		}
		else if (expression instanceof Decrement)
		{
			result = '''«expression.left.toCode»--'''
		}
		else if (expression instanceof Or)
		{
			result = '''«expression.left.toCode» || «expression.right.toCode»'''
		}
		else if (expression instanceof And)
		{
			result = '''«expression.left.toCode» && «expression.right.toCode»'''
		}
		else if (expression instanceof Not)
		{
			result = '''! («expression.expression.toCode»)'''
		}
		else if (expression instanceof BooleanBrackets)
		{
			result = '''(«expression.expression.toCode»)'''
		}
		else if (expression instanceof ArithmeticBrackets)
		{
			result = '''(«expression.expression.toCode»)'''
		}
		else if (expression instanceof Comparison)
		{
			result = '''«expression.left.toCode» «expression.type.toCode» «expression.right.toCode»'''
		}
		/*
		if (expression instanceof Atomic)
		{
			if (expression.field != FieldType.VALUE)
			{
				result += '.'+expression.field
			}
		}*/
		
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
