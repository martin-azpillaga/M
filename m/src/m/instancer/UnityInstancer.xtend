package m.instancer

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Random
import javax.imageio.ImageIO
import m.YAMLRuntimeModule
import m.csharp.Block
import m.csharp.File
import m.csharp.InitializeVariable
import m.csharp.Member
import m.csharp.QualifiedName
import m.structured.Access
import m.structured.And
import m.structured.Assignment
import m.structured.Brackets
import m.structured.Selection
import m.structured.Call
import m.structured.Comparison
import m.structured.Condition
import m.structured.Decrement
import m.structured.Divide
import m.structured.Expression
import m.structured.Increment
import m.structured.Minus
import m.structured.Modulus
import m.structured.Not
import m.structured.Or
import m.structured.Plus
import m.structured.RelationType
import m.structured.Times
import m.json.JsonFactory
import m.m.Component
import m.m.Entity
import m.m.Game
import m.m.Loop
import m.m.System
import m.modeler.FactoryHelper
import m.validation.Type
import m.yaml.Document
import m.yaml.Node
import m.yaml.Tag
import m.yaml.Version
import m.yaml.YamlFactory
import org.eclipse.emf.common.command.Command
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.modeler.GenericSerializer.*
import static m.validation.Type.*
import static m.csharp.Visibility.*
import static m.structured.AssignmentType.*
import static m.csharp.TypeEnum.*
import m.csharp.CsharpFactory
import m.CSharpRuntimeModule
import m.structured.StructuredFactory

class ArtGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	
	def generate(Game game, HashMap<String, Type> types, IFileSystemAccess2 fsa, String... folders)
	{
		var meshComponents = types.keySet.filter[types.get(it) == Type.mesh].toSet
		
		var sprites = new HashSet<String>
		var audioClips = new HashSet<String>
		var meshes = new HashSet<String>
		var materials = new HashSet<String>
		var fonts = new HashSet<String>
		var texts = new HashSet<String>
		
		for (entity : game.entities)
		{
			for (component : entity.components)
			{
				if (meshComponents.contains(component.name))
				{
					meshes.add(component.values.join(' '))
				}
			}
		}
		
		var random = new Random
		var yamlRuntime = new YAMLRuntimeModule
		
		for (mesh : meshes)
		{
			for (folder : folders)
			{
			fsa.generateFile(folder+mesh+'.obj',
			'''
			o «mesh»
			
			v -0.500000 -0.500000 0.500000
			v 0.500000 -0.500000 0.500000
			v -0.500000 0.500000 0.500000
			v 0.500000 0.500000 0.500000
			v -0.500000 0.500000 -0.500000
			v 0.500000 0.500000 -0.500000
			v -0.500000 -0.500000 -0.500000
			v 0.500000 -0.500000 -0.500000
			
			vt 0.000000 0.000000
			vt 1.000000 0.000000
			vt 0.000000 1.000000
			vt 1.000000 1.000000
			
			vn 0.000000 0.000000 1.000000
			vn 0.000000 1.000000 0.000000
			vn 0.000000 0.000000 -1.000000
			vn 0.000000 -1.000000 0.000000
			vn 1.000000 0.000000 0.000000
			vn -1.000000 0.000000 0.000000
			
			g «mesh»
			s 1
			f 1/1/1 2/2/1 3/3/1
			f 3/3/1 2/2/1 4/4/1
			s 2
			f 3/1/2 4/2/2 5/3/2
			f 5/3/2 4/2/2 6/4/2
			s 3
			f 5/4/3 6/3/3 7/2/3
			f 7/2/3 6/3/3 8/1/3
			s 4
			f 7/1/4 8/2/4 1/3/4
			f 1/3/4 8/2/4 2/4/4
			s 5
			f 2/1/5 8/2/5 4/3/5
			f 4/3/5 8/2/5 6/4/5
			s 6
			f 7/1/6 1/2/6 5/3/6
			f 5/3/6 1/2/6 3/4/6
			'''
			)
			
			var file = createFile
			file.nodes.add('fileFormatVersion',2)
			file.nodes.add('guid', (mesh+'mesh').uuid)
			
			generate(file, yamlRuntime, fsa, folder+mesh+'.obj.meta')
			}
		}
		
		for (sprite : sprites)
		{				
			var image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			image.setRGB(0,0,new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)).RGB)
			var o = new ByteArrayOutputStream
			ImageIO.write(image, 'png', o)
			
			var file = createFile
			file.nodes.add('fileFormatVersion',2)
			file.nodes.add('guid', sprite.uuid)
			
			var importer = yaml.createMap
			importer.key = "TextureImporter"
			
			importer.body.add('serializedVersion', 10)
			importer.body.add('spriteMode', 1)
			importer.body.add(kv('spritePixelsToUnits', 1.yaml))
			importer.body.add(kv('textureType', 8.yaml))
			
			file.nodes.add(importer)
			
			for (folder : folders)
			{
				fsa.generateFile(folder+sprite+'.png', new ByteArrayInputStream(o.toByteArray))		
				
				generate(file, yamlRuntime, fsa, folder+sprite+'.png.meta')
			}
		}
		
		for (audioClip : audioClips)
		{
			var hz = 44100;
			var bps = 16;
			var rescaleFactor = 0x7FFF;
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
			
			var path = 'Pure Unity/Assets/Art/AudioClips/'+audioClip+'.wav'
			fsa.generateFile(path, new ByteArrayInputStream(baos.toByteArray))
			
			var metaFile = yaml.createFile
			metaFile.nodes.add(kv('fileFormatVersion', 2.yaml))
			metaFile.nodes.add(kv('guid', guid(audioClip)))
			var importer = createMap
			importer.key = 'AudioImporter'
			importer.body.add(kv('serializedVersion', 6.yaml))
			metaFile.nodes.add(importer)
			
			for (folder : folders)
			{
				generate(metaFile, yamlRuntime, fsa, folder+audioClip+'.wav.meta')
			}
		}
		
		for (text : texts)
		{
			for (folder : folders)
			{
				fsa.generateFile(folder+text+'.txt', 'Hello')
			}
		}
	}
}




class ComponentGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	extension CsharpFactory factory = CsharpFactory.eINSTANCE
	extension StructuredFactory structured = StructuredFactory.eINSTANCE
	
	YamlFactory yaml = YamlFactory.eINSTANCE
	var usings = new HashSet<QualifiedName>
	
	def extensions(Game game, IFileSystemAccess2 fsa)
	{

	}
	
	def extendedText(IFileSystemAccess2 fsa)
	{
		var file = createFile
		file.addUsing('Unity')
		file.addUsing('UnityEngine')
		file.addUsing('UnityEngine','UI')
		file.addUsing('UnityEditor')
		
		var component = createMember
		component.visibility = PUBLIC
		
		var extendedText = createType
		extendedText.name = 'ExtendedText'.type
		extendedText.superTypes.add('Text'.type)
		
		extendedText.members.add(textAssetField)
		extendedText.members.add(updateText)
		
		component.value = extendedText
		file.types.add(component)
		
		var editor = createMember
		editor.visibility = PUBLIC
		var annotation = createAnnotation
		annotation.function = 'CustomEditor'.type
		annotation.parameters.add(a.call('typeof', a.label('ExtendedText')))
		editor.annotations.add(annotation)
		
		var textEditor = createType
		textEditor.name = 'ExtendedTextEditor'.type
		textEditor.superTypes.add('Editor'.type)
		
		textEditor.members.add(inspectorGUI)
		
		editor.value = textEditor
		file.types.add(editor)
		
		generate(file, new CSharpRuntimeModule, fsa, 'Pure Unity/Assets/Code/Components/ExtendedText.cs')
	}
	
	def inspectorGUI()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.override = true
		method.name = 'OnInspectorGUI'
		
		var cast = createCast
		cast.type = 'MyText'.type
		cast.expression = a.label('target')
		
		var castTarget = createCAssignment
		castTarget.setVar = true
		castTarget.access = a.label('text')
		castTarget.assignmentType = SET
		castTarget.expression = cast
		method.statements.add(castTarget)
		
		var cast2 = createCast
		cast2.type = 'MyText'.type
		cast2.expression = a.label('EditorGUILayout').call('ObjectField',string('Asset'), a.label('text').label('asset'), a.call('typeof',a.label('ExtendedText')), a.label('false'))
		
		var castInput = createCAssignment
		castInput.access = a.label('text').label('asset')
		castInput.assignmentType = SET
		castInput.expression = cast2
		method.statements.add(castInput)
		
		member.value = method
		return member
	}
	
	def textAssetField()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = 'TextAsset'.type
		field.name = 'asset'
		
		member.value = field
		return member
	}
	
	def updateText()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Update'
		
		var command = createCAssignment
		command.access = a.label('text')
		command.assignmentType = SET
		command.expression = a.label('asset').label('text')
		
		method.statements.add(command)
		
		member.value = method
		return member
	}
	
	def metaFiles(HashMap<String, Type> types, IFileSystemAccess2 fsa)
	{		
		var yamlModule = new YAMLRuntimeModule
		
		for (component : types.keySet.filter[valid])
		{
			var file = yaml.createFile
			file.nodes.add('fileFormatVersion', 2)
			file.nodes.add('guid', (component+'component').uuid)

			generate(file, yamlModule, fsa, 'Pure Unity/Assets/Code/Components/'+component.authoringComponentName+'.cs.meta')
		}
	}
	
	def valid(String name)
	{
		var builtIn = #['a','b','position','rotation','scale','mesh', 'material', 'audioClip',
			'triggerZone', 'extent', 'radius', 'velocity', 'acceleration', 'force',
			'mass','eyes','emission','color','backgroundColor',
			'restitution','friction']
			
		return !builtIn.contains(name) && !name.contains('@')
	}
	
	def generate(Map<String,Type> components, IFileSystemAccess2 fsa)
	{
		var csharp = new CSharpRuntimeModule
		
		
		for (component : components.keySet.filter[valid])
		{
			var file = component.convert(components.get(component))
			generate(file, csharp, fsa, 'Pure Unity/Assets/Code/Components/'+component.authoringComponentName+'.cs')
		}
	}
	
	def convert(String name, Type type)
	{
		var file = createFile
		
		usings = new HashSet<QualifiedName>
		
		file.types.add(componentDataStruct(name, type))
		file.types.add(proxyClass(name, type))
		
		var real = new HashSet<String>
		for (using : usings)
		{
			real.add(using.names.join('.'))
		}
		
		for (r : real)
		{
			var split = r.split('\\.')
			file.addUsing(split)			
		}
		
		return file
	}
	
	def isValueType(Type type)
	{
		return #[tag, float1, float2, float3, float4, entity, gameObject].contains(type)
	}
	
	def addUsings(File file, Type type)
	{
		file.addUsing('UnityEngine')
		file.addUsing('Unity','Entities')
		
		if (#[float2, float3, float4].contains(type))
		{
			file.addUsing('Unity','Mathematics')
		}
		else if (#[input].contains(type))
		{
			file.addUsing('UnityEngine','InputSystem')
			file.addUsing('System')
		}
	}
	
	def componentDataStruct(String name, Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var struct = createType
		struct.type = STRUCT
		struct.name = name.type
		
		if (type !== tag)
		{
			struct.members.add(valueField(type))
			struct.members.add(toValue(name, type))
			struct.members.add(toData(name, type))
		}
		
		if (type.isValueType)
		{
			struct.superTypes.add('IComponentData'.type)
		}
		else
		{
			struct.superTypes.add('ISharedComponentData'.type)
			struct.superTypes.add(type(name('IEquatable'),name(name)))
			struct.members.add(equals(name))
			struct.members.add(getHashCode(name))
			usings.add(name('System'))
		}
		
		usings.add(name('Unity','Entities'))
		
		
		
		member.value = struct
		
		return member
	}
	
	def equals(String name)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'bool'.type
		method.name = 'Equals'
		method.arguments.add(argument(name.type, 'other'))
		var comparison = createComparison
		comparison.left = a.label('other').label('Value')
		comparison.right = a.label('this').label('Value')
		comparison.type = RelationType.EQUAL
		var returns = createReturn
		returns.expression = comparison
		method.statements.add(returns)
		
		member.value = method
		return member
	}
	
	def getHashCode(String name)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'int'.type
		method.name = 'GetHashCode'
		method.override = true
		var returns = createReturn
		returns.expression = a.label('Value').call('GetHashCode')
		method.statements.add(returns)
		
		member.value = method
		return member
	}
	
	def authoringComponentName(String componentName)
	{
		return componentName + 'Authoring'
	}
	
	def proxyClass(String name, Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var class = createType
		class.type = CLASS
		class.name = name.authoringComponentName.type
		class.superTypes.add('MonoBehaviour'.type)
		class.superTypes.add('IConvertGameObjectToEntity'.type)
		member.value = class
		if (type != tag)
		{
			class.members.add(serializedField(name, type))
		}
		class.members.add(convertMethod(name, type))
		
		usings.add(name('UnityEngine'))
		usings.add(name('Unity','Entities'))
		
		return member
	}
	
	def serializedField(String name, Type type)
	{
		var member = createMember
		member.annotations.add('SerializeField'.annotation)
		member.visibility = PRIVATE
		
		usings.add(name('UnityEngine'))
		
		var field = createField
		field.type = type.authoringName.type
		field.name = 'Value'
		
		member.value = field
		
		return member
	}
	
	def convertMethod(String name, Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Convert'
		method.arguments.addAll(#[argument('Entity'.type, 'entity'), argument('EntityManager'.type, 'manager'), argument('GameObjectConversionSystem'.type, 'conversionSystem')])
		
		usings.add(name('UnityEngine'))
		
		if (type == gameObject)
		{
			method.statements.add(convertAndAdd(name))
		}
		else
		{
			method.statements.add(addComponentData(name,type))
		}
		
		member.value = method
		return member
	}
	
	def addComponentData(String name, Type type)
	{
		var newComponent = createCAccess
		newComponent.setNew = true
		
		if (type != tag)
		{
			newComponent.initialize(name, initialization('Value', a.label('Value')))
		}
		else
		{
			newComponent.initialize(name)
		}
		var method = 'AddComponentData'
		if (!type.isValueType)
		{
			method = 'AddSharedComponentData'
		}
		var access = a.label('manager').call(method, a.label('entity'), newComponent)
		
		var assignment = createAssignment
		assignment.access = access
		
		return assignment
	}
	
	def convertAndAdd(String name)
	{
		var newComponent = createAccess
		newComponent.setNew = true
		newComponent.initialize(name, initialization('Value', a.label('GameObjectConversionUtility').call('ConvertGameObjectHierarchy', a.label('Value'), a.label('World').label('Active'))))
		var method = 'AddComponentData'
		var access = a.label('manager').call(method, a.label('entity'), newComponent)
		
		var assignment = createAssignment
		assignment.access = access
		
		return assignment
	}
	
	def rangeClass(String name)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var class = createType
		class.type = CLASS
		class.name = ('_'+name).type
		class.superTypes.add('MonoBehaviour'.type)
		member.value = class
		
		class.members.add(valueField(input))
		
		return member
	}
	
	def valueField(Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = type.stringName.type
		field.name = 'Value'
		
		if (#[float2,float3,float4].contains(type))
		{
			usings.add(name('Unity','Mathematics'))
		}
		else if (#[input].contains(type))
		{
			usings.add(name('UnityEngine','InputSystem'))
		}
		
		member.value = field
		return member
	}
	
	def toValue(String name, Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		member.static = true
		
		var toValue = createMethod
		toValue.implicit = true
		toValue.operator = true
		toValue.name = type.stringName
		
		var dataArgument = createArgument
		dataArgument.type = type(name(name))
		dataArgument.name = 'data'
		toValue.arguments.add(dataArgument)
		
		var returnValue = createReturn
		returnValue.expression = a.label('data').label('Value')
		toValue.statements.add(returnValue)
		
		member.value = toValue
		return member
	}
	
	def toData(String name, Type type)
	{
		var member = createMember
		member.visibility = PUBLIC
		member.static = true
		
		var toData = createMethod
		toData.implicit = true
		toData.operator = true
		toData.name = name
		
		var dataArgument = createArgument
		dataArgument.type = type(name(type.stringName))
		dataArgument.name = 'Value'
		toData.arguments.add(dataArgument)
		
		var returnData = createReturn
		var accessData = createAccess
		accessData.setNew = true
		accessData.initialize(name, initialization('Value', a.label('Value')))
		returnData.expression = accessData
		toData.statements.add(returnData)
		
		member.value = toData
		return member
	}
	
	
}


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
			generate(file, yamlRuntime, fsa, 'Pure Unity/Assets/Design/Entities/'+entity.name+'.prefab')
			
			var meta = entity.metaFile
			generate(meta, yamlRuntime, fsa, 'Pure Unity/Assets/Design/Entities/'+entity.name+'.prefab.meta')
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
		gameObject.body.add(kv('m_Name', entity.name))
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
		
		var values = component.values
		if (Character.isDigit(values.head.charAt(0)))
		{
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
		else
		{			
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
				var value = component.values
				result.add(kv('m_LocalPosition',#{'x'->value.get(0).yaml, 'y'->value.get(1).yaml, 'z'->value.get(2).yaml}))
			}
			case 'scale': 
			{
				var value = component.values
				result.add(kv('m_LocalScale',#{'x'->value.get(0).yaml, 'y'->value.get(1).yaml, 'z'->value.get(2).yaml}))
			}
			case 'audioClip': 
			{
				var value = component.values
				result.add(kv('m_audioClip',#{'fileID'->8300000.yaml, 'guid'->(value.join(' ')+'audioClip').guid, 'type'->3.yaml}))
			}
			case 'mesh':
			{
				result.add(kv('m_Mesh', #{'fileID'->10202l.yaml, 'guid'->"0000000000000000e000000000000000".yaml, 'type'->0.yaml}))
			}
			case 'material':
			{
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


class PackageGenerator 
{
	extension JsonFactory factory = JsonFactory.eINSTANCE
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	var jsonRuntime = new JsonRuntimeModule
	
	def generate(Game game, IFileSystemAccess2 fsa)
	{
		var root = createObject
		var dependencies = createField
		root.fields.add(dependencies)
		
		dependencies.name = "dependencies"
		var list = createObject
		dependencies.value = list
		
		list.add("com.unity.entities", "0.1.1-preview")
		list.add("com.unity.ide.vscode", "1.1.2")
		list.add("com.unity.inputsystem", "1.0.0-preview")
		list.add("com.unity.physics", "0.2.4-preview")
		list.add("com.unity.rendering.hybrid", "0.1.1-preview")

		var modules = #['ai','animation','assetbundle','audio','cloth','director','imageconversion','imgui','jsonserialize','particlesystem','physics','physics2d','screencapture','terrain','terrainphysics','tilemap','ui','uielements','umbra','unityanalytics','unitywebrequest','unitywebrequestassetbundle','unitywebrequestaudio','unitywebrequesttexture','unitywebrequestwww','vehicles','video','vr','wind','xr']
		for (module : modules)
		{
			list.add("com.unity.modules."+module, "1.0.0")
		}
		
		generate(root, jsonRuntime, fsa, 'Pure Unity/Packages/manifest.json')
	}
	
	def assemblies(Game game, IFileSystemAccess2 fsa)
	{
		if (game.systems.size > 0 || game.entities.size > 0)
		{
			var root = createObject
			root.add('name','Code')
			root.add('references',#['Unity.Entities','Unity.Entities.Hybrid','Unity.Mathematics','Unity.Burst','Unity.InputSystem','Unity.Physics', 'Unity.Transforms', 'Unity.Rendering.Hybrid'])
			
			generate(root, jsonRuntime, fsa, 'Pure Unity/Assets/Code/Code.asmdef')
		}
		if (game.systems.size > 0)
		{
			var root = createObject
			root.add('name','Test')
			root.add('references',#['Code', 'Unity.Entities','Unity.Entities.Hybrid','Unity.Mathematics','Unity.Burst','Unity.InputSystem','Unity.Physics', 'Unity.Transforms', 'Unity.Rendering.Hybrid'])
			
			generate(root, jsonRuntime, fsa, 'Pure Unity/Assets/Code/Tests/Test.asmdef')
		}
	}
	
}

class SettingsGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	var yamlRuntime = new YAMLRuntimeModule
	
	def generate(Game game, IFileSystemAccess2 fsa)
	{
		twoDPhysics(fsa)
		threeDPhysics(fsa)
	}
	
	def twoDPhysics(IFileSystemAccess2 fsa)
	{
		var twoD = createFile
		twoD.version = Version.ONE_ONE
		twoD.tags.add(Tag.UNITY)
		
		var document = createDocument
		document.tag = "19"
		document.id = "1"
		
		var map = createMap
		map.key = 'Physics2DSettings'
		map.body.add('serializedVersion', 4)
		map.body.add('m_Gravity', #{'x'->0f, 'y'->0f})
		
		document.value = map
		twoD.nodes.add(document)
		
		generate(twoD, yamlRuntime, fsa, 'Pure Unity/ProjectSettings/Physics2DSettings.asset')
	}
	
	def threeDPhysics(IFileSystemAccess2 fsa)
	{
		var file = createFile
		file.version = Version.ONE_ONE
		file.tags.add(Tag.UNITY)
		
		var document = createDocument
		document.tag = "55"
		document.id = "1"
		
		var map = createMap
		map.key = 'PhysicsManager'
		map.body.add('serializedVersion', 10)
		map.body.addLongMap('m_Gravity', #{'x'->0l, 'y'->0l, 'z'->0l})
		
		document.value = map
		file.nodes.add(document)
		
		generate(file, yamlRuntime, fsa, 'Pure Unity/ProjectSettings/DynamicsManager.asset')
	}
}

class SystemGenerator 
{
	extension CsharpFactory factory = CsharpFactory.eINSTANCE
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	YamlFactory yaml = YamlFactory.eINSTANCE
	
	HashMap<String,Type> types
	HashSet<String> usings
	
	def generate(Game game, HashMap<String,Type> types, IFileSystemAccess2 fsa)
	{
		this.types = types
		
		var csharp = new CSharpRuntimeModule
		var yamlModule = new YAMLRuntimeModule
		
		for (system : game.systems)
		{
			fsa.generateFile('src-gen/'+system.name+'.done','')
			var s = system as System
			var file = s.convert
			generate(file, csharp, fsa, 'Pure Unity/Assets/Code/Systems/'+s.name+'.cs')
			
			var meta = yaml.createFile
			meta.nodes.add('fileFormatVersion', 2)
			meta.nodes.add('guid', (s.name+'system').uuid)
			
			generate(meta, yamlModule, fsa, 'Pure Unity/Assets/Code/Systems/'+s.name+'.cs.meta')
		}
		
		var contactSystem = contactSystem
		generate(contactSystem, csharp, fsa, 'Pure Unity/Assets/Code/Systems/Contacts.cs')
		var meta = yaml.createFile
		meta.nodes.add('fileFormatVersion', 2)
		meta.nodes.add('guid', ('Contacts'+'system').uuid)
		
		generate(meta, yamlModule, fsa, 'Pure Unity/Assets/Code/Systems/Contacts.cs.meta')
	}
	
	def entryPoint(List<System> systems)
	{
		var file = createFile
		
		file.addUsing('UnityEngine')
		file.addUsing('Unity','Entities')
		
		var member = createMember
		member.visibility = PUBLIC
		var class = createType
		member.value = class
		class.type = CLASS
		class.name = 'Main'.type
		file.types.add(member)
		
		class.members.add(runMethod(systems))
		
		return file
	}
	
	def runMethod(List<System> systems)
	{
		var member = createMember
		member.visibility = PRIVATE
		member.static = true
		
		var runtimeAnnotation = createAnnotation
		runtimeAnnotation.function = 'RuntimeInitializeOnLoadMethod'.type
		runtimeAnnotation.parameters.add(a.label('RuntimeInitializeLoadType').label('BeforeSceneLoad'))
		member.annotations.add(runtimeAnnotation)
		member.static = true
		
		var run = createMethod
		run.type = 'void'.type
		run.name = 'Run'
		member.value=run
		
		var worldAssignment = createAssignment
		worldAssignment.setVar = true
		worldAssignment.access = a.label('world')
		worldAssignment.assignmentType = SET
		var call = a.call('World', string('world'))
		call.setNew = true
		worldAssignment.expression = call
		
		run.statements.add(worldAssignment)
		
		for (system : systems)
		{
			var create = createAssignment
			var newSystem = a.call((system as System).name)
			newSystem.setNew = true
			create.access = a.label('world').call('AddSystem', newSystem)
			
			run.statements.add(create)
		}

		var setWorld = createAssignment
		setWorld.access = a.label('World').label('Active')
		setWorld.assignmentType = SET
		setWorld.expression = a.label('world')
				
		run.statements.add(setWorld)
		
		return member
	}
	
	def convert(System system)
	{
		var file = createFile
		
		usings = new HashSet<String>
		
		file.types.add(systemClass(system))
		
		for (using : usings)
		{
			file.addUsing(using.split('\\.'))
		}
		return file
	}
	
	def systemClass(System system)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var class = createType
		class.type = CLASS
		class.name = system.name.type
		class.superTypes.add('ComponentSystem'.type)
		
		usings.add('Unity.Entities')
		
		class.members.add(update(system))
		
		member.value = class
		return member
	}
	
	def entityQueries(System system)
	{
		var result = new ArrayList<Member>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var member = createMember
			member.visibility = PRIVATE
			
			var field = createField
			field.type = 'EntityQuery'.type
			field.name = loop.entity
			
			member.value = field
			result.add(member)
		}
		
		return result
	}
	
	def onCreate(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnCreate'
		
		var loops = EcoreUtil2.getAllContentsOfType(system,Loop)
		for (loop : loops)
		{
			method.statements.add(getQuery(system, loop))
		}
		
		member.value = method
		return member
	}
	
	def getQuery(System system, Loop loop)
	{
		var command = createAssignment
		command.access = a.label(loop.entity)
		command.assignmentType = SET
		
		var expression = createAccess
		
		var components = new ArrayList<Expression>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (write : writes)
		{
			var writeAccess = a.label('ComponentType').call('ReadWrite',write.type)
			components.add(writeAccess)
		}
		for (read : reads)
		{
			var readAccess = a.label('ComponentType').call('ReadOnly',read.type)
			components.add(readAccess)
		}
		expression.call('GetEntityQuery', components)
		
		command.expression = expression
		
		return command
	}
	
	def onUpdate(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'JobHandle'.type
		method.name = 'OnUpdate'
		
		var dependencies = createArgument
		dependencies.type = 'JobHandle'.type
		dependencies.name = 'dependencies'
		method.arguments.add(dependencies)
		
		method.statements.add(scheduleJob(system))
		
		member.value = method
		return member
	}
	
	def update(System system)
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnUpdate'
		
		for (command : system.statements)
		{
			method.statements.addAll(statements(system).statements)
		}
		
		member.value = method
		return member
	}
	
	def scheduleJob(System system)
	{
		var command = createReturn
		
		var newJob = createAccess
		newJob.setNew = true
		
		var initializations = new ArrayList<InitializeVariable>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var initialization = createInitializeVariable
			initialization.name = loop.entity
			initialization.expression = a.label(loop.entity).call('CreateArchetypeChunkArray', a.label('Allocator').label('TempJob'))
			
			initializations.add(initialization)
		}
		
		var types = typesIn(system)
		for (type : types.keySet)
		{
			var sendType = createInitializeVariable
			sendType.name = type
			sendType.expression = a.call('GetArchetypeChunkComponentType', type.type, a.label((types.get(type)).toString))
			initializations.add(sendType)
		}
		
		if (system.makesStructuralChanges)
		{
			var sendBuffer = createInitializeVariable
			sendBuffer.name = 'buffer'
			sendBuffer.expression = a.label('World').label('Active').call('GetExistingSystem','EndSimulationEntityCommandBufferSystem'.type).call('CreateCommandBuffer').call('ToConcurrent')
			initializations.add(sendBuffer)
		}
		newJob.initialize('Job', initializations).call('Schedule',a.label('dependencies'))
		
		
		
		command.expression = newJob
		
		return command
	}
	
	def job(System system)
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'Job'.type
		struct.superTypes.add('IJob'.type)
		struct.members.addAll(jobFields(system))
		struct.members.add(execute(system))
		
		member.value = struct
		
		return member
	}
	
	def jobFields(System system)
	{
		var result = new ArrayList<Member>
		
		var loops = EcoreUtil2.getAllContentsOfType(system, Loop)
		for (loop : loops)
		{
			var member = createMember
			member.visibility = PUBLIC
			member.annotations.add(annotation('DeallocateOnJobCompletion'))
			
			var chunk = createField
			chunk.type = type(name('NativeArray'),name('ArchetypeChunk'))
			chunk.name = loop.entity
			member.value = chunk
			
			result.add(member)	
		}
		
		var types = typesIn(system)
		for (type : types.keySet)
		{
			var member = createMember
			member.visibility = PUBLIC
			if (types.get(type))
			{
				member.annotations.add(annotation('ReadOnly'))
			}
			
			var declareType = createField
			declareType.type = type(name('ArchetypeChunkComponentType'), name(type))
			declareType.name = type
			member.value = declareType
			
			result.add(member)
		}
		
		if (system.makesStructuralChanges)
		{
			var member = createMember
			member.visibility = PUBLIC
			
			var field = createField
			field.type = type(name('EntityCommandBuffer','Concurrent'))
			field.name = 'buffer'
			member.value = field
			
			result.add(member)
		}
		
		return result
	}
	
	def makesStructuralChanges(System system)
	{
		var structural = EcoreUtil2.getAllContentsOfType(system, Call).map[name]
		
		return (structural).exists[#['add','remove','create','destroy'].contains(it)]
	}
	
	def makesStructuralChanges(Loop loop)
	{
		var calls = EcoreUtil2.getAllContentsOfType(loop, Call)
		
		for (call : calls)
		{
			if (call.name == 'add' || call.name=='remove')
			{
				var entity = call.parameters.get(1)
				if (entity instanceof Access)
				{
					if (entity.names.head == loop.entity)
					{
						return true
					}
				}
			}
			else if (call.name == 'destroy')
			{
				var entity = call.parameters.get(0)
				if (entity instanceof Access)
				{
					if (entity.names.head == loop.entity)
					{
						return true
					}
				}
			}
		}	
		return false
	}
	
	def typesIn(System system)
	{
		var map = new HashMap<String,Boolean>
		
		val writes = EcoreUtil2.getAllContentsOfType(system, Assignment).filter[it.access.names.size > 1].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(system, Access).filter[it.names.size > 1].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (write : writes.toSet)
		{
			map.put(write, false)
			if (#['position','rotation','scale'].contains(write))
			{
				usings.add('Unity.Transforms')
			}
			else if (#['mass','velocity','acceleration','force','restitution','friction','extent','radius'].contains(write))
			{
				usings.add('Unity.Physics')
			}
		}
		for (read : reads.toSet)
		{
			map.put(read, true)
			if (#['position','rotation','scale'].contains(read))
			{
				usings.add('Unity.Transforms')
			}
			else if (#['mass','restitution','friction','extent','radius'].contains(read))
			{
				usings.add('Unity.Physics')
			}
		}
		
		
		return map
	}
	
	def execute(System system)
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Execute'
		
		method.statements.addAll(statements(system).statements)
		
		member.value = method
		return member
	}
	
	def statements(System system)
	{
		var result = createBlock
		
		for (command : system.statements)
		{
			result.addCommand(command)
		}
		return result
	}
	
	def void addCommand(Block list, m.m.Command command)
	{
		if (command instanceof Call)
		{
			var function = command.name
			if (function == 'remove')
			{
				var call = createAssignment
				var access = a.label('PostUpdatestatements')
				var methodCall = createMethodCall
				methodCall.name = 'RemoveComponent'
				methodCall.generics.add((command.parameters.get(0) as Access).names.head.type)
				methodCall.expressions.add(command.parameters.get(1))
				access.portions.add(methodCall)
				call.access = access
				list.statements.add(call)
			}
			else if (function == 'add')
			{
				var call = createAssignment
				call.access = a.label('PostUpdatestatements').call('AddComponent', (command.parameters.get(0) as Access).names.head.type, command.parameters.get(1))
				list.statements.add(call)
			}
			else if (function == 'destroy')
			{
				var call = createAssignment
				call.access = a.label('PostUpdatestatements').call('DestroyEntity', command.parameters.get(0))
				list.statements.add(call)
			}
			else if (function == 'create')
			{
				var call = createAssignment
				call.access = a.label('PostUpdatestatements').call('Instantiate', command.parameters.get(0))
				list.statements.add(call)
			}
		}
		else if (command instanceof Assignment)
		{
			if (command.access.names.size == 2)
			{
				var created = createAssignment
				created.access = a.label(command.access.names.head+'_'+command.access.names.get(1)).label(field(command.access.names.get(1)))
				created.assignmentType = command.type
				created.expression = command.expression
				list.statements.add(created)
			}
			else if (command.access.names.size == 1)
			{
				var declaration = createAssignment
				var container = command.eContainer
				while (!(container instanceof Loop || container instanceof System))
				{
					container = EcoreUtil2.getContainerOfType(container, Loop)
				}
				var hash = command.access.names.head+'@'+container.hashCode
				var type = types.get(hash).stringName.type
				declaration.type = type
				declaration.access = a.label(command.access.names.head)
				declaration.assignmentType = SET
				declaration.expression = a.call('default', a.label(type.name.names.head))
				
				var assignment = EcoreUtil2.getContainerOfType(list, m.cs.Assignment)
				var system = assignment.eContainer as Block
				system.statements.add(0,declaration)
				
				
				var created = createAssignment
				created.access = a.label(command.access.names.head)
				created.assignmentType = command.type
				created.expression = command.expression
				list.statements.add(created)
			}
			else
			{
				var newComponent = createAccess
				newComponent.setNew = true
				newComponent.initialize(command.access.names.last.componentName, initialization(command.access.names.last.field, command.expression))
				var created = createAssignment
				created.access = a.label('EntityManager').call('SetComponentData', a.label('my_'+command.access.names.head+'_'+command.access.names.get(1)).label('Value'), newComponent)
				list.statements.add(created)
			}
		}
		else if (command instanceof Loop)
		{
			val writes = EcoreUtil2.getAllContentsOfType(command, Assignment).filter[it.access.names.size > 1 && it.access.names.head == command.entity].map[it.access.names.get(1)]
			var reads = EcoreUtil2.getAllContentsOfType(command, Access).filter[it.names.size > 1 && it.names.head == command.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
			var lambda = createLambda
			var entity = command.entity
			if (command.makesStructuralChanges)
			{
				lambda.arguments.add(argument('Entity'.type, entity))
			}
			for (w : writes.toSet)
			{
				if (w.isShared)
				{
					lambda.arguments.add(0, argument(w.componentName.type, entity+'_'+w))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+w)
					my.expression = a.label(entity+'_'+w)
					lambda.statements.add(my)
				}
				else
				{
					lambda.arguments.add(refArgument(w.componentName.type, entity+'_'+w))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+w)
					my.expression = a.label(entity+'_'+w)
					lambda.statements.add(my)
				}
			}
			for (r : reads.toSet)
			{
				if (r.isShared)
				{
					lambda.arguments.add(0, argument(r.componentName.type, entity+'_'+r))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+r)
					my.expression = a.label(entity+'_'+r)
					lambda.statements.add(my)
				}
				else
				{
					lambda.arguments.add(refArgument(r.componentName.type, entity+'_'+r))
					var my = createAssignment
					my.setVar = true
					my.access = a.label('my_'+entity+'_'+r)
					my.expression = a.label(entity+'_'+r)
					lambda.statements.add(my)
				}
			}
			var foreach = createAssignment
			var access = a.label('Entities')
			
			for (constraint : command.constraints)
			{
				var methodCall = createMethodCall
				methodCall.name = 'WithAll'
				methodCall.generics.add(constraint.type)
				access.portions.add(methodCall)
			}
			foreach.access = access.call('ForEach',lambda)
			list.statements.add(foreach)
			
			for (c : command.statements)
			{
				lambda.addCommand(c)
			}
			
		}
		else if (command instanceof Selection)
		{
			val Selection = createIf
			var ifCondition = command.^if
			list.statements.add(Selection)
			
			Selection.expression = ifCondition.condition
			ifCondition.statements.forEach[Selection.addCommand(it)]
			
			
			for (elseIf : command.elseIfs)
			{
				val csharpElseIf = createElseIf
				var elseIfCondition = elseIf as Condition
				csharpElseIf.expression = elseIfCondition.condition
				elseIfCondition.statements.forEach[csharpElseIf.addCommand(it)]
			}
			
			if (command.statements.size > 0)
			{
				val csharpElse = createElse
				command.statements.forEach[csharpElse.addCommand(it)]
			}
		}
	}
	
	def componentName(String name)
	{
		if (name == 'velocity')
		{
			usings.add('Unity.Physics')
			return 'PhysicsVelocity'
		}
		else if (name == 'position')
		{
			usings.add('Unity.Transforms')
			return 'Translation'
		}
		else if (name == 'rotation')
		{
			usings.add('Unity.Transforms')
			return 'Rotation'
		}
		else if (name == 'angularVelocity')
		{
			usings.add('Unity.Physics')
			return 'PhysicsVelocity'
		}
		else
		{
			return name
		}
	}
	
	def isShared(String name)
	{
		return #[Type.input].contains(types.get(name))
	}
	
	def field(String name)
	{
		if (name == 'velocity')
		{
			return 'Linear'
		}
		else if (name == 'angularVelocity')
		{
			return 'Angular'
		}
		else
		{
			return 'Value'
		}
	}
	
	def void addCommandOld(List<Command> list, m.m.Command command)
	{
		if (command instanceof Call)
		{
			
		}
		else if (command instanceof Assignment)
		{
			var created = createAssignment
			created.access = a.label(command.access.names.head+'_'+command.access.names.get(1))
			for (var i = 2; i < command.access.names.size; i++)
			{
				created.access.label(command.access.names.get(i))
			}
			created.assignmentType = command.type
			created.expression = command.expression
			list.add(created)
		}
		else if (command instanceof Loop)
		{
			var outer = createFor
			var initialize_out = createAssignment
			initialize_out.setVar = true
			initialize_out.access = a.label(command.entity+'_out')
			initialize_out.expression = number(0)
			outer.initialization = initialize_out
			
			var condition = createComparison
			condition.left = a.label(command.entity+'_out')
			condition.type = RelationType.UNDER
			condition.right = a.label(command.entity).label('Length')
			outer.condition = condition
			
			var increment = createAssignment
			increment.access = a.label(command.entity+'_out')
			increment.increment = true
			outer.increment = increment
			
			outer.statements.add(getChunk(command.entity))
			outer.statements.addAll(getArrays(command))
			
			var inner = createFor
			var initialize_inner = createAssignment
			initialize_inner.setVar = true
			initialize_inner.access = a.label(command.entity+'_in')
			initialize_inner.expression = number(0)
			inner.initialization = initialize_inner
			
			var condition_inner = createComparison
			condition_inner.left = a.label(command.entity+'_in')
			condition_inner.type = RelationType.UNDER
			condition_inner.right = a.label(command.entity+'_chunk').label('Count')
			inner.condition = condition_inner
			
			var increment_inner = createAssignment
			increment_inner.access = a.label(command.entity+'_in')
			increment_inner.increment = true
			inner.increment = increment_inner
			
			
			outer.statements.add(inner)
			inner.statements.addAll(getDatas(command))
			for (c : command.statements)
			{
				inner.addCommand(c)
			}
			
			list.add(outer)
		}
		else if (command instanceof Selection)
		{
			
		}
	}
	
	def getChunk(String name)
	{
		var assignment = createAssignment
		assignment.setVar = true
		assignment.access = a.label(name+'_chunk')
		assignment.assignmentType = SET
		assignment.expression = a.index(name, a.label(name+'_out'))
		return assignment
	}
	
	def getArrays(Loop loop)
	{
		var group = loop.entity
		
		var result = new ArrayList<Command>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (type : writes+reads)
		{
			var assignment = createAssignment
			
			assignment.setVar = true
			assignment.access = a.label(group+'_'+type+'_array')
			assignment.assignmentType = SET
			assignment.expression = a.label(group+'_chunk').call('GetNativeArray', a.label(type))
			
			result.add(assignment)
		}
		
		
		return result
	}
	
	def getDatas(Loop loop)
	{
		var group = loop.entity
		
		var result = new ArrayList<Command>
		
		val writes = EcoreUtil2.getAllContentsOfType(loop, Assignment).filter[it.access.names.size > 1 && it.access.names.head == loop.entity].map[it.access.names.get(1)]
		var reads = EcoreUtil2.getAllContentsOfType(loop, Access).filter[it.names.size > 1 && it.names.head == loop.entity].filter[!writes.toList.contains(it.names.get(1))].map[it.names.get(1)]
		
		for (type : writes+reads)
		{
			var assignment = createAssignment
			
			assignment.setVar = true
			assignment.access = a.label(group+'_'+type)
			assignment.assignmentType = SET
			assignment.expression = a.index(group+'_'+type+'_array',a.label(group+'_in'))
			
			result.add(assignment)
		}
		
		return result
	}
	
	def contactSystem()
	{
		var system = createFile
		
		system.addUsing('Unity','Entities')
		system.addUsing('Unity', 'Jobs')
		system.addUsing('Unity', 'Physics')
		system.addUsing('Unity', 'Physics', 'Systems')
		system.addUsing('Unity', 'Mathematics')
		
		system.types.add(contactClass)
		system.types.add(contactA)
		system.types.add(contactB)
		
		return system
	}
	
	def contactClass()
	{
		var member = createMember
		member.visibility = PUBLIC
		member.annotations.add(annotation('AlwaysUpdateSystem'))
		
		var class = createType
		class.name = 'Contacts'.type
		class.superTypes.add('ComponentSystem'.type)
		
		class.members.add(contactUpdate())
		class.members.add(contactCallback())
		class.members.add(contactJob())
		
		member.value = class
		return member
	}
	
	def contactUpdate()
	{
		var member = createMember
		member.visibility = PROTECTED
		
		var method = createMethod
		method.override = true
		method.type = 'void'.type
		method.name = 'OnUpdate'
		
		var lambda = createLambda
		lambda.arguments.add(argument('Entity'.type, 'a'))
		lambda.arguments.add(refArgument('a'.type, 'something'))
		var destroy = createAssignment
		destroy.access = a.label('PostUpdatestatements').call('DestroyEntity',a.label('a'))
		lambda.statements.add(destroy)
		
		var foreach = createAssignment
		foreach.access = a.label('Entities').call('ForEach', lambda)
		
		method.statements.add(foreach)
		
		
		var step = createAssignment
		step.setVar = true
		step.access = a.label('step')
		step.assignmentType = SET
		step.expression = a.label('World').label('Active').call('GetExistingSystem','StepPhysicsWorld'.type)
		
		var enqueue = createAssignment
		enqueue.access = a.label('step').call('EnqueueCallback', a.label('SimulationCallbacks').label('Phase').label('PostCreateContacts'), a.label('callback'))
		
		method.statements.add(step)
		method.statements.add(enqueue)
		
		member.value = method
		return member
	}
	
	def contactCallback()
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var method = createMethod
		method.type = 'JobHandle'.type
		method.name = 'callback'
		method.arguments.add(refArgument('ISimulation'.type, 'simulation'))
		method.arguments.add(refArgument('PhysicsWorld'.type, 'world'))
		method.arguments.add(argument('JobHandle'.type, 'dependencies'))
		
		
		
		var getHandle = createAssignment
		getHandle.setVar = true
		getHandle.access = a.label('handle')
		getHandle.assignmentType = SET
		var expression = createAccess
		expression.setNew = true
		var refWorld = createAccess
		refWorld.ref = true
		refWorld.label('world')
		expression.initialize('Job', initialization('buffer', a.label('World').label('Active').call('GetExistingSystem', 'EndSimulationEntityCommandBufferSystem'.type).call('CreateCommandBuffer').call('ToConcurrent'))).call('Schedule', a.label('simulation'), refWorld, a.label('dependencies'))
		getHandle.expression = expression
		
		method.statements.add(getHandle)
		
		var complete = createAssignment
		complete.access = a.label('handle').call('Complete')
		
		method.statements.add(complete)
		
		var returns = createReturn
		returns.expression = a.label('handle')
		
		method.statements.add(returns)
		
		member.value = method
		return member
	}
	
	def contactJob()
	{
		var member = createMember
		member.visibility = PRIVATE
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'Job'.type
		struct.superTypes.add('IContactsJob'.type)
		
		struct.members.add(buffer)
		struct.members.add(executeContacts)
		
		member.value = struct
		return member
	}
	
	def buffer()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = type(name('EntityCommandBuffer', 'Concurrent'))
		field.name = 'buffer'
		
		member.value = field
		return member
	}
	
	def executeContacts()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var method = createMethod
		method.type = 'void'.type
		method.name = 'Execute'
		method.arguments.add(refArgument('ModifiableContactHeader'.type, 'header'))
		method.arguments.add(refArgument('ModifiableContactPoint'.type, 'contact'))
		
		var getEntity = createAssignment
		getEntity.setVar = true
		getEntity.access = a.label('entity')
		getEntity.assignmentType = SET
		getEntity.expression = a.label('buffer').call('CreateEntity', 0.number)
		var newA = createAccess
		newA.setNew = true
		newA.initialize('a', initialization('Value', a.label('header').label('Entities').label('EntityA')))
		var newB = createAccess
		newB.setNew = true
		newB.initialize('b', initialization('Value', a.label('header').label('Entities').label('EntityB')))
		var createA = createAssignment
		createA.access = a.label('buffer').call('AddComponent', number(0), a.label('entity'), newA)
		var createB = createAssignment
		createB.access = a.label('buffer').call('AddComponent', number(0), a.label('entity'), newB)
		
		method.statements.add(getEntity)
		method.statements.add(createA)
		method.statements.add(createB)
		
		member.value = method
		return member
	}
	
	def contactA()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'a'.type
		struct.superTypes.add('IComponentData'.type)
		
		struct.members.add(entityValue)
		
		member.value = struct
		return member
	}
	
	def contactB()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var struct = createType
		struct.type = STRUCT
		struct.name = 'b'.type
		struct.superTypes.add('IComponentData'.type)
		
		struct.members.add(entityValue)
		
		member.value = struct
		return member
	}
	
	def entityValue()
	{
		var member = createMember
		member.visibility = PUBLIC
		
		var field = createField
		field.type = 'Entity'.type
		field.name = 'Value'
		member.value = field
		return member
	}
}