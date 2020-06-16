package m.generator

import java.util.HashMap
import java.util.Map.Entry
import m.m.Application
import m.m.Assignment
import m.m.Binary
import m.m.Block
import m.m.Cell
import m.m.Delegation
import m.m.Expression
import m.m.Function
import m.m.Statement
import m.validation.Type
import m.m.Unary
import m.m.Value
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2

import java.util.HashSet
import m.validation.ExponentType
import static m.validation.Symbols.*
import static m.validation.Type.*
import static m.validation.ProductType.*
import static m.validation.PolymorphicType.*

enum AccessKind
{
	read, write, tag
}

class Unity
{
	IFileSystemAccess2 fileSystem;
	
	var queries = new HashMap<String,HashMap<String,AccessKind>>
	var namespaces = new HashSet<String>
	
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{		
		this.fileSystem = fileSystem;
		
		for (component : game.components.entrySet)
		{
			generate(component.key, component.value)
		}
		for (function : game.functions.entrySet)
		{
			var type = function.value as ExponentType
			if (type.left === null && type.right === null)
			{
				generate(function.key)
			}
		}
	}
	
	

	def private generate(String name, Type type)
	{
		namespaces.clear
		namespaces.add('Unity.Entities')
		
		var classifier = type.valueType ? "struct" : "class"
		var field = type != unit? '''public «type.unity» Value;''' : ""
		
		fileSystem.generateFile('''Unity/Assets/Code/Components/«name».cs''',
		
		'''
		«['''using «it»;'''].foreach(namespaces)»
		
		namespace M
		{
			[GenerateAuthoringComponent]
			public «classifier» «name» : IComponentData
			{
				«field»
			}
		}
		'''
		)
	}
	
	def private generate(Function function)
	{
		namespaces.clear
		namespaces.addAll(#['Unity.Entities','Unity.Jobs'])
		
		function.collectQueries
		
		var queryName = queries.keySet
		
		var name = function.name
		
		fileSystem.generateFile('''Unity/Assets/Code/Systems/«name».cs''',
		
		'''
		using static Unity.Entities.ComponentType;
		using static Unity.Collections.Allocator;
		«['''using «it»;'''].foreach(namespaces)»
		
		namespace M
		{
			[UpdateInGroup(typeof(Gameplay))]
			public class «name» : SystemBase
			{
				«['''EntityQuery «it»;'''].foreach(queryName)»
				protected override void OnCreate()
				{
					«['''«it» = GetEntityQuery(«queries.get(it).entrySet.map[access].join(', ')»)'''].foreach(queryName)»;
				}
				
				protected override void OnUpdate()
				{
					«function.statements.map[code].n»
				}
			}
		}
		'''
		)
	}
	
	def private foreach ((Object)=>CharSequence f, Iterable<?> iterable)
	{
		iterable.map(f).n
	}
	
	def private access(Entry<String,AccessKind> entry)
	{
		var write = entry.value==AccessKind.write?'Write':'Only'
		
		'''Read«write»<«entry.key»>()'''
	}
	
	def private toArray(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.key
		var isTag = entry.value == AccessKind.tag
		
		if (isTag) return "";
		
		'''var «component»s_«entity» = GetArchetypeChunkComponentType<«component»>(«entry.value==AccessKind.read»)'''
	}
	
	def private dispose(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.key
		var isTag = entry.value == AccessKind.tag
		
		if (isTag) return "";
		
		'''«component»s_«entity».Dispose();'''
	}
	
	def private toComponent(Entry<String,AccessKind> entry, String entity)
	{
		var component = entry.key
		'''var «component»_«entity» = «component»s_«entity»[«entity»_i];'''
	}
	
	def private String code(Statement statement)
	{
		if (statement instanceof Block)
		{
			if (statement.name == query.name)
			{
				val a = (statement.expression as Value).name
				var query = queries.get(a)
				
				'''
				var chunks_«a» = «a».CreateArchetypeChunkArray(TempJob);
				var entities_«a» = GetArchetypeChunkEntityType();
				«query.entrySet.join('\n',[toArray(a)])»
				
				for (var «a»_i = 0; «a»_i < «a».CalculateEntityCount(); «a»_i++)
				{
					var entity_«a» = entities_«a»[«a»_i];
					«query.entrySet.map[toComponent(a)].n»
					
					«statement.statements.join('',[code])»
				}
				entities_«a».Dispose();
				«query.entrySet.join('\n',[dispose(a)])»
				'''
			}
			else if (statement.name == selection.name)
			{
				var condition = code(statement.expression)
				'''
				if («condition»)
				{
					«statement.statements.join('\n',[code])»
				}
				'''
			}
			else if (statement.name == iteration.name)
			{
				'''
				while («statement.expression.code»)
				{
					«statement.statements.join('\n', [code])»
				}
				'''
			}
			
		}
		else if (statement instanceof Assignment)
		{
			var atom = statement.atom
			
			if (atom instanceof Value)
			{
				'''
				var «atom.code» = «statement.expression.code»;
				'''
			}
			else if (atom instanceof Cell)
			{
				'''
				«atom.code» = «statement.expression.code»;
				EntityManager.SetComponentData(entity_«atom.entity», «atom.component»_«atom.entity»);
				'''
			}
		}
		else if (statement instanceof Delegation)
		{
			'''«statement.application.code»;'''
		}
	}
	
	def private String code(Expression e)
	{
		if (e instanceof Binary) '''(«e.left.code» «e.operator» «e.right.code»)'''
		else if (e instanceof Unary) '''(«e.operator» «e.expression.code»)'''
		else if (e instanceof Value) '''«e.name»'''
		else if (e instanceof Cell) '''«e.component»_«e.entity».Value'''
		else if (e instanceof Application) '''«e.name»(«e.arguments.map[code].join(', ')»)'''
	}
	
	def private String unity(Type type)
	{
		
		switch type
		{
			case entity:
			{
				namespaces.add("Unity.Entities")
				return "Entity"
			}
			case number: "float"
			case number2: "float2"
			case number3:
			{
				namespaces.add("Unity.Mathematics")
				"float3"	
			}
			case number4: "float4"
			case proposition: "bool"
			case entityList: "SubScene"
		}
	}
	
	def private setComponentAccess(String entity, String component, AccessKind kind)
	{
		if (!queries.containsKey(entity))
		{
			queries.put(entity, new HashMap<String,AccessKind>)
		}
		var components = queries.get(entity)
		if (!components.containsKey(component) || components.get(component) != AccessKind.write)
		{
			queries.get(entity).put(component,kind)
		}
	}
	
	def private valueType(Type type)
	{
		#[number,number2,number3,number4,entity,entityList,proposition].contains(type)
	}
	
	def private n(Iterable<?> iterable)
	{
		iterable.join('\n')
	}
	
	def private collectQueries(Function function)
	{
		queries = new HashMap<String,HashMap<String,AccessKind>>
		
		for (cell : EcoreUtil2.getAllContentsOfType(function,Cell))
		{
			var container = cell.eContainer
			if (container instanceof Assignment)
			{
				if (cell == container.atom)
				{
					cell.entity.setComponentAccess(cell.component,AccessKind.write)
				}
				else
				{
					cell.entity.setComponentAccess(cell.component,AccessKind.read)
				}
			}
			else
			{
					cell.entity.setComponentAccess(cell.component,AccessKind.read)
			}
		}
	}
}