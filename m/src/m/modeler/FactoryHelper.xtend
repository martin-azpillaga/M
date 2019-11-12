package m.modeler

import m.yaml.YamlFactory
import m.cs.CsFactory
import m.yaml.Element
import java.util.UUID
import m.json.JsonFactory
import m.cs.File
import m.cs.Access
import m.cs.TypeName
import m.cs.Expression
import m.cs.Initialization
import m.cs.QualifiedName
import m.m.AssignmentType
import static m.cs.AssignmentType.*
import java.util.Map
import java.util.List
import m.yaml.Node
import m.cs.InitializeVariable
import m.validation.Type

class FactoryHelper 
{
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	extension CsFactory csharp = CsFactory.eINSTANCE
	extension JsonFactory json = JsonFactory.eINSTANCE
	
	static FactoryHelper instance
	
	private new(){}
	
	def static eINSTANCE()
	{
		if (instance === null)
		{
			instance = new FactoryHelper
		}
		return instance
	}
	
	def add(List<Node> list, String key, float value)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		var floatValue = yaml.createFloat
		floatValue.value = value
		keyValue.value = floatValue
		list.add(keyValue)
	}
	
	def add(List<Node> list, String key, long value)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		var floatValue = yaml.createLong
		floatValue.value = value.toString
		keyValue.value = floatValue
		list.add(keyValue)
	}
	
	def add(List<Node> list, String key, String value)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		if (Character.isDigit(value.charAt(0)))
		{
			var guidValue = createGuid
			guidValue.value = value
			keyValue.value = guidValue
		}
		else
		{
			var wordValue = createWord
			wordValue.value = value
			keyValue.value = wordValue
		}
		list.add(keyValue)
	}
	
	def add(List<Node> list, String key, Map<String,Float> map)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		var value = createInlineMap
		for (k : map.keySet)
		{
			var entry = createKeyValue
			entry.key = k
			var entryValue = yaml.createFloat
			entryValue.value = map.get(k)
			entry.value = entryValue
			value.values.add(entry)
		}
		keyValue.value = value
		list.add(keyValue)
	}
	
	def addLongMap(List<Node> list, String key, Map<String,Long> map)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		var value = createInlineMap
		for (k : map.keySet)
		{
			var entry = createKeyValue
			entry.key = k
			var entryValue = yaml.createLong
			entryValue.value = map.get(k).toString
			entry.value = entryValue
			value.values.add(entry)
		}
		keyValue.value = value
		list.add(keyValue)
	}
	
	def kv(String key, Element value)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		keyValue.value = value
		return keyValue
	}
	
	def kv (String key, String value)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		if (Character.isDigit(value.charAt(0)))
		{
			var stringValue = createGuid
			stringValue.value = value
			keyValue.value = stringValue
		}
		else
		{
			var stringValue = createWord
			stringValue.value = value
			keyValue.value = stringValue
		}
		
		return keyValue
	}
	
	def kv (String key, Map<String, Element> map)
	{
		var keyValue = createKeyValue
		keyValue.key = key
		var inlineMap = createInlineMap
		for (k : map.keySet)
		{
			inlineMap.values.add(kv(k, map.get(k)))
		}
		keyValue.value = inlineMap
		return keyValue
	}
	
	def yaml(float n)
	{
		var number = yaml.createFloat
		number.value = n
		return number
	}
	
	def yaml(long n)
	{
		var number = yaml.createLong
		number.value = n.toString
		return number
	}
	
	def yaml(String string)
	{
		if (Character.isDigit(string.charAt(0)))
		{
			var stringValue = yaml.createGuid
			stringValue.value = string
			return stringValue
		}
		else
		{
			var stringValue = yaml.createWord
			stringValue.value = string
			return stringValue
		}
		
	}
	
	def Element guid(String string)
	{
		var uuid = uuid(string)
		if (Character.isDigit(uuid.charAt(0)))
		{
			var guid = createGuid
			guid.value = uuid
			return guid
		}
		else
		{
			var word = createWord
			word.value = uuid
			return word
		}
	}
	
	def uuid(String string)
	{
		return UUID.nameUUIDFromBytes(string.bytes).toString.replace('-','')
	}
	
	def inlineMap(Map<String,Element> map)
	{
		var inlineMap = createInlineMap
		for (key : map.keySet)
		{
			inlineMap.values.add(kv(key, map.get(key)))
		}
		return inlineMap
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
	
	def string(String name)
	{
		var expression = createString
		expression.value = name
		return expression
	}
	
	def stringName(Type type)
	{
		switch type
		{
			case float1: 'float'
			case float2: 'float2'
			case float3: 'float3'
			case float4: 'float4'
			case gameObject: 'Entity'
			case audioClip: 'AudioClip'
			case material: 'Material'
			case mesh: 'Mesh'
			case sprite: 'Sprite'
			case input: 'InputAction'
			case tag: 'tag components shouldnt have a field'
			case entity: 'Entity'
		}
	}
	
	def authoringName(Type type)
	{
		switch type
		{
			case float1: 'float'
			case float2: 'float2'
			case float3: 'float3'
			case float4: 'float4'
			case gameObject: 'GameObject'
			case audioClip: 'AudioClip'
			case material: 'Material'
			case mesh: 'Mesh'
			case sprite: 'Sprite'
			case input: 'InputAction'
			case tag: 'tag components shouldnt have a field'
			case entity: 'Entity'
		}
	}
	
	def serializableAnnotation()
	{
		var annotation = createAnnotation
		annotation.function = 'Serializable'.type
		return annotation
	}
	
	
	def addUsing(File file, String... names)
	{
		var using = createUsing
		var name = createQualifiedName
		name.names.addAll(names)
		using.name = name
		file.usings.add(using)
	}
	
	def a()
	{
		return csharp.createAccess
	}
	
	def label(Access access, String name)
	{
		var portion = createLabel
		portion.name = name
		access.portions.add(portion)
		return access
	}
	
	def call(Access access, String name, TypeName generic)
	{
		var call = createMethodCall
		call.name = name
		call.generics.add(generic)
		access.portions.add(call)
		return access
	}
	
	def call(Access access, String name, Expression... expressions)
	{
		var call = createMethodCall
		call.name = name
		call.expressions.addAll(expressions)
		access.portions.add(call)
		return access
	}
	
	def call(Access access, String name, TypeName generic, Expression... expressions)
	{
		var call = createMethodCall
		call.name = name
		call.generics.add(generic)
		call.expressions.addAll(expressions)
		access.portions.add(call)
		return access
	}
	
	def initializePortion(String name, InitializeVariable... initializations)
	{
		var portion = csharp.createInitialization
		portion.name = name
		portion.initializations.addAll(initializations)
		return portion
	}
	
	def methodPortion(String name, Expression... parameters)
	{
		var portion = createMethodCall
		portion.name = name
		portion.expressions.addAll(parameters)
		return portion
	}
	
	def methodPortion(Access access, String name, TypeName generic, Expression... parameters)
	{
		var portion = createMethodCall
		portion.name = name
		if (generic !== null)
		{
			portion.generics.add(generic)
		}
		portion.expressions.addAll(parameters)
		access.portions.add(portion)
		return access
	}
	
	def initialize(Access access, String name, InitializeVariable... initializations)
	{
		var portion = csharp.createInitialization
		portion.name = name
		portion.initializations.addAll(initializations)
		access.portions.add(portion)
		return access
	}
	
	def name(String... names)
	{
		var name = createQualifiedName
		name.names.addAll(names)
		return name
	}
	
	def type(QualifiedName... names)
	{
		var typeName = createTypeName
		typeName.name = names.head
		for (var i = 1; i < names.size; i++)
		{
			typeName.generics.add(names.get(i))	
		}
		return typeName
	}
	
	def type(String name)
	{
		var type = createTypeName
		var qualifiedName = createQualifiedName
		qualifiedName.names.add(name)
		type.name = qualifiedName
		return type
	}
	
	def argument(TypeName type, String name)
	{
		var argument = createArgument
		argument.type = type
		argument.name = name
		return argument
	}
	
	def refArgument(TypeName type, String name)
	{
		var argument = createArgument
		argument.ref = true
		argument.type = type
		argument.name = name
		return argument
	}
	
	def add(m.json.Object o, String key, String value)
	{
		var field = json.createField
		field.name = key
		var stringValue = createStringValue
		stringValue.value = value
		field.value = stringValue
		o.fields.add(field)
	}
	
	def add(m.json.Object o, String name, List<String> values)
	{
		var field = json.createField
		field.name = name
		
		var array = json.createArray
		for (value : values)
		{
			var stringValue = createStringValue
			stringValue.value = value
			array.elements.add(stringValue)
		}
		
		field.value = array
		o.fields.add(field)
	}
	
	def toCS(AssignmentType type)
	{
		switch (type)
		{
			case AND: AND
			case DECREASE: DECREASE
			case DIVIDE: DIVIDE
			case INCREASE: INCREASE
			case MODULUS: MODULUS
			case MULTIPLY: MULTIPLY
			case OR: OR
			case SET: SET
			
		}
	}
	
	def number(float number)
	{
		var literal = csharp.createFloat
		literal.value = number
		return literal
	}
	
	def number(long number)
	{
		var literal = csharp.createLong
		literal.value = number
		return literal
	}
	
	def annotation(String name)
	{
		var annotation = createAnnotation
		annotation.function = name.type
		return annotation
	}
	
	def initialization(String name, Expression expression)
	{
		var initialization = createInitializeVariable
		initialization.name = name
		initialization.expression = expression
		return initialization
	}
	
	def index(m.cs.Access access, String name, Expression index)
	{
		var indexing = createIndexing
		indexing.name = name
		indexing.index = index
		access.portions.add(indexing)
		return access
	}
}