package m.transformation

import m.CSRuntimeModule
import m.YAMLRuntimeModule
import m.cs.CsFactory
import m.cs.File
import m.m.Game
import m.yaml.YamlFactory
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.cs.AssignmentType.*
import static m.cs.TypeEnum.*
import static m.cs.Visibility.*
import static m.transformation.GenericSerializer.*
import static m.transformation.Type.*
import java.util.Map
import m.cs.RelationType
import java.util.HashMap
import java.util.HashSet
import m.cs.QualifiedName

class ComponentGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	extension CsFactory factory = CsFactory.eINSTANCE
	
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
		
		generate(file, new CSRuntimeModule, fsa, 'Pure Unity/Assets/Code/Components/ExtendedText.cs')
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
		
		var castTarget = createAssignment
		castTarget.setVar = true
		castTarget.access = a.label('text')
		castTarget.assignmentType = SET
		castTarget.expression = cast
		method.commands.add(castTarget)
		
		var cast2 = createCast
		cast2.type = 'MyText'.type
		cast2.expression = a.label('EditorGUILayout').call('ObjectField',string('Asset'), a.label('text').label('asset'), a.call('typeof',a.label('ExtendedText')), a.label('false'))
		
		var castInput = createAssignment
		castInput.access = a.label('text').label('asset')
		castInput.assignmentType = SET
		castInput.expression = cast2
		method.commands.add(castInput)
		
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
		
		var command = createAssignment
		command.access = a.label('text')
		command.assignmentType = SET
		command.expression = a.label('asset').label('text')
		
		method.commands.add(command)
		
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
		var csharp = new CSRuntimeModule
		
		
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
		method.commands.add(returns)
		
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
		method.commands.add(returns)
		
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
			method.commands.add(convertAndAdd(name))
		}
		else
		{
			method.commands.add(addComponentData(name,type))
		}
		
		member.value = method
		return member
	}
	
	def addComponentData(String name, Type type)
	{
		var newComponent = createAccess
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
		toValue.commands.add(returnValue)
		
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
		toData.commands.add(returnData)
		
		member.value = toData
		return member
	}
	
	
}