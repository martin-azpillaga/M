package m.transformation

import java.util.ArrayList
import m.YAMLRuntimeModule
import m.m.Component
import m.m.Entity
import m.m.Game
import m.m.Vector
import m.m.Word
import m.yaml.Document
import m.yaml.Node
import m.yaml.Tag
import m.yaml.Version
import m.yaml.YamlFactory
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.transformation.GenericSerializer.*
import java.util.HashMap

class EntityGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	
	var yamlRuntime = new YAMLRuntimeModule
	
	HashMap<String, Type> types
		
	def generate(Game game, HashMap<String, Type> types, IFileSystemAccess2 fsa)
	{
		this.types = types
		
		for (entity : game.entities)
		{
			var file = entity.generate
			generate(file, yamlRuntime, fsa, 'Pure Unity/Assets/Design/Entities/'+entity.name.join(' ')+'.prefab')
			
			var meta = entity.metaFile
			generate(meta, yamlRuntime, fsa, 'Pure Unity/Assets/Design/Entities/'+entity.name.join(' ')+'.prefab.meta')
		}
	}
	
	def metaFile(Entity entity)
	{
		var file = createFile
		
		file.nodes.add('fileFormatVersion', 2)
		file.nodes.add('guid', (entity.name+'entity').uuid)
		
		return file
	}
	
	def generate(Entity entity)
	{
		var documents = inferComponents(entity)
		
		var file = createFile
		
		file.version = Version.ONE_ONE
		file.tags.add(Tag.UNITY)
		
		for (document : documents)
		{
			file.nodes.add(document)
		}
		
		return file
	}
	
	def inferComponents(Entity entity)
	{		
		var documentsToJoin = new ArrayList<Document>
		
		var go = createDocument
		go.tag = "1"
		go.id = 'GameObject'.hash
		var gameObject = createMap
		go.value = gameObject
		gameObject.key = 'GameObject'
		gameObject.body.add(kv('serializedVersion', 6.yaml))
		gameObject.body.add(kv('m_Name', entity.name.join(' ')))
		gameObject.body.add(kv('m_IsActive', 1.yaml))
		var componentList = createKeyValueList
		gameObject.body.add(componentList)
		componentList.name='m_Component'
		
		var t = createDocument
		t.tag = "4"
		t.id = 'Transform'.hash
		var transform = createMap
		t.value = transform
		transform.key = 'Transform'
		
		documentsToJoin.add(go)
		documentsToJoin.add(t)
		
		
		
		
		
		
		for (c : entity.components)
		{
			documentsToJoin.add(c.document)
		}
		
		var documents = new ArrayList<Document>
		
		for (documentToJoin : documentsToJoin)
		{
			var found = documents.findFirst[it.id == documentToJoin.id]
			
			if (found === null)
			{
				documents.add(documentToJoin)
			}
			else
			{
				found.value.body.addAll(documentToJoin.value.body)
			}
		}
		
		for (document : documents)
		{
			if (document.value.key != 'GameObject')
			{
				document.value.body.addLongMap('m_GameObject', #{'fileID'->Long.parseLong('GameObject'.hash)})
				componentList.values.add(kv('component', #{'fileID'->Long.parseLong(document.id).yaml}))
			}
			
		}
		
		return documents
	}
	
	def tag(String name)
	{
		switch (name)
		{
			case 'GameObject': 1
			case 'Transform': 4
			case 'Rigidbody2D': 50
			case 'AudioSource': 82
			case 'AudioListener': 81
			case 'Light': 108
			case 'MeshRenderer': 23
			case 'MeshFilter': 33
			case 'Camera': 20
			case 'CircleCollider2D': 58
			case 'RectTransform': 224
			case 'SpriteRenderer': 212
			case 'BoxCollider2D': 61
			case 'Canvas': 223
			case 'CanvasRenderer': 222
			case 'MonoBehaviour': 114
			default: 114
		}
	}
	
	def field(String name)
	{
		switch name
		{
			case 'mesh': 'm_Mesh'
			case 'material': 'm_Materials'
			case 'position': 'm_LocalPosition'
			case 'scale': 'm_LocalScale'
			case 'mass': 'm_Mass'
			case 'damping': 'm_LinearDamping'
			case 'angularDamping': 'm_AngularDamping'
			case 'velocity': 'm_InitialLinearVelocity'
			case 'angularVelocity': 'm_InitialAngularVelocity'
			case 'gravityFactor': 'm_GravityFactor'
			default: 'Value'
		}
	}
	
	def unityId(String name)
	{
		if (#[
			'GameObject', 
			'Transform',
			'Rigidbody2D',
			'AudioSource',
			'AudioListener',
			'Light',
			'MeshRenderer',
			'MeshFilter',
			'Camera',
			'CircleCollider2D',
			'RectTransform',
			'SpriteRenderer',
			'BoxCollider2D',
			'Canvas',
			'CanvasRenderer'
			
		].contains(name))
		{
			return name
		}
		else
		{
			return 'MonoBehaviour'
		}
	}
	
	def document(Component component)
	{
		var unityComponent = component.unityComponent
		var document = createDocument
		
		document.tag = unityComponent.tag.toString
		if (unityComponent == 'MonoBehaviour')
		{
			document.id = component.name.hash
		}
		else
		{
			document.id = unityComponent.hash
		}
		
		var map = createMap
		document.value = map
		map.key = unityComponent.unityId
		
		var value = component.value
		if (value instanceof Vector)
		{
			var values = value.numbers
			if (values.size == 1)
			{
				map.body.add(kv(component.name.field, values.get(0).yaml))
			}
			else if (values.size == 2)
			{
				var inner = createMap
				inner.key = component.name.field
				inner.body.add(kv('x', values.get(0).yaml))
				inner.body.add(kv('y', values.get(1).yaml))
				map.body.add(inner)
			}
			else if (values.size == 3)
			{
				var inner = createMap
				inner.key = component.name.field
				inner.body.add(kv('x', values.get(0).yaml))
				inner.body.add(kv('y', values.get(1).yaml))
				inner.body.add(kv('z', values.get(2).yaml))
				map.body.add(inner)
			}
			else if (values.size == 4)
			{
				var inner = createMap
				inner.key = component.name.field
				inner.body.add(kv('x', values.get(0).yaml))
				inner.body.add(kv('y', values.get(1).yaml))
				inner.body.add(kv('z', values.get(2).yaml))
				inner.body.add(kv('w', values.get(2).yaml))
				map.body.add(inner)
			}
		}
		else if (value instanceof Word)
		{
			var values = value.values
			
			var type = types.get(component.name)
			
			if (type == Type.mesh)
			{
				map.body.add(kv(component.name.field, #{'fileID'->10202l.yaml, 'guid'->"0000000000000000e000000000000000".yaml, 'type'->0.yaml}))
			}
			else if (type == Type.material)
			{
				var kvList = createKeyValueList
				kvList.name = 'm_Materials'
				map.body.add(kvList)
				var inline = createInlineMap
				inline.values.add(kv('fileID',10303l.yaml))
				inline.values.add(kv('guid',"0000000000000000f000000000000000".yaml))
				inline.values.add(kv('type',0.yaml))
				kvList.values.add(inline)
			}
		}
		
		if (unityComponent == 'MonoBehaviour')
		{
			document.value.body.add(kv('m_Script', #{'fileID'->11500000l.yaml, 'guid'-> (component.name+'component').uuid.yaml, 'type'->3.yaml}))
		}
		else if (unityComponent == 'PhysicsBody')
		{
			document.value.body.add(kv('m_Script', #{'fileID'->11500000l.yaml, 'guid'-> "ccea9ea98e38942e0b0938c27ed1903e".yaml, 'type'->3.yaml}))			
		}
		else if (unityComponent == 'PhysicsShape')
		{
			document.value.body.add(kv('m_Script', #{'fileID'->11500000l.yaml, 'guid'-> "b275e5f92732148048d7b77e264ac30e".yaml, 'type'->3.yaml}))
		}
		
		return document
	}
	
	def unityComponent(Component component)
	{
		var name = component.name
		
		switch (name)
		{
			case 'position': 'Transform'
			case 'rotation': 'Transform'
			case 'scale': 'Transform'
			case 'audioClip': 'AudioSource'
			case 'ears': 'AudioListener'
			case 'emission': 'Light'
			case 'intensity': 'Light'
			case 'material': 'MeshRenderer'
			case 'perspective': 'Camera'
			case 'range': 'Light'
			case 'spotAngle': 'Light'
			case 'mesh': 'MeshFilter'
			case 'text': 'Text'
			case 'viewAngle': 'Camera'
			case 'viewDistance': 'Camera'
			case 'clearColor': 'Camera'
			case 'font': 'Text'
			case 'pitch': 'AudioSource'
			case 'volume': 'AudioSource'
			case 'order': 'SpriteRenderer'
			case 'button': 'Button'
			case 'image': 'Image'
			case 'fillAmount': 'Image'
			case 'mass': 'PhysicsBody'
			case 'damping': 'PhysicsBody'
			case 'angularDamping': 'PhysicsBody'
			case 'velocity': 'PhysicsBody'
			case 'angularVelocity': 'PhysicsBody'
			case 'gravityFactor': 'PhysicsBody'
			case 'restitution': 'PhysicsShape'
			case 'friction': 'PhysicsShape'
			case 'radius': 'PhysicsShape'
			case 'extent': 'PhysicsShape'
			case 'triggerZone': 'PhysicsShape'
			
			
			default: 'MonoBehaviour'
		}
	}
	
	def properties(Component component)
	{
		var result = new ArrayList<Node>
		
		var name = component.name
		
		switch (name)
		{
			case 'position': 
			{
				var value = (component.value as Vector).numbers
				result.add(kv('m_LocalPosition',#{'x'->value.get(0).yaml, 'y'->value.get(1).yaml, 'z'->value.get(2).yaml}))
			}
			case 'scale': 
			{
				var value = (component.value as Vector).numbers
				result.add(kv('m_LocalScale',#{'x'->value.get(0).yaml, 'y'->value.get(1).yaml, 'z'->value.get(2).yaml}))
			}
			case 'audioClip': 
			{
				var value = component.value as Word
				result.add(kv('m_audioClip',#{'fileID'->8300000.yaml, 'guid'->(value.values.join(' ')+'audioClip').guid, 'type'->3.yaml}))
			}
			case 'mesh':
			{
				var value = component.value as Word
				result.add(kv('m_Mesh', #{'fileID'->10202l.yaml, 'guid'->"0000000000000000e000000000000000".yaml, 'type'->0.yaml}))
			}
			case 'material':
			{
				var value = component.value as Word
				var kvList = createKeyValueList
				kvList.name = 'm_Materials'
				result.add(kvList)
				var inline = createInlineMap
				inline.values.add(kv('fileID',10303l.yaml))
				inline.values.add(kv('guid',"0000000000000000f000000000000000".yaml))
				inline.values.add(kv('type',0.yaml))
				kvList.values.add(inline)
			}
		}
		
		return result
	}
	
	def n(EngineComponent c)
	{
		switch (c)
		{
			case Restitution: 'restitution'
			case Friction: 'friction'
		}
	}
	
}

class UnityComponent
{
	public String name
	public int tag
	public String id
}

enum EngineComponent
{
	Restitution,Friction
}