package m.serializing

import java.util.HashMap
import java.util.HashSet
import java.util.Map.Entry
import m.game.Addition
import m.game.And
import m.game.Assignment
import m.game.Priorization
import m.game.Call
import m.game.Division
import m.game.Equality
import m.game.Expression
import m.game.Foreach
import m.game.Greater
import m.game.GreaterOrEqual
import m.game.Inequality
import m.game.Iteration
import m.game.Lower
import m.game.LowerOrEqual
import m.game.Multiplication
import m.game.Negation
import m.game.Or
import m.game.Selection
import m.game.Statement
import m.game.Subtraction
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.validation.StandardLibrary.*
import m.game.Variable
import m.game.Cell
import m.game.Game
import m.game.Function
import m.game.ArrayType
import m.game.Type
import m.game.Component

enum AccessKind
{
	tag, read, write
}

class UnityXtend
{
	IFileSystemAccess2 fileSystem;
	
	var queries = new HashMap<String,HashMap<String,HashSet<AccessKind>>>
	
	def void serialize(Game game, IFileSystemAccess2 fileSystem)
	{
		this.fileSystem = fileSystem;
		for (component : game.components.filter[!components.contains(it)])
		{
			serializeComponent(component)
		}
		for (function : game.functions.filter[a|!functions.exists[f|f.name==a.name]])
		{
			serializeSystem(function)
		}
	}
	
	def private String unity(Type type)
	{
		switch type
		{
			case entity: "Entity"
			case number: "float"
			case number2: "float2"
			case number3: "float3"
			case number4: "float4"
			case truthValue: "boolean"
			case world: "SubScene"
			default: "Not implemented"
		}
	}
	
	def private void serializeComponent(Component component)
	{		
		var String content
		var type = component.type
		var name = component.name
		
		if (type instanceof ArrayType)
		{
			content = 
			'''
			using Unity.Entities;
			using UnityEngine;
			
			public struct «name» : IComponentData, IContain<Entity>
			{
				public Entity Value;
			}
			
			public class «name»Authoring : MonoBehaviour, IConvertGameObjectToEntity, IDeclareReferencedPrefabs
			{
				public List<GameObject> Value;
				
				public void Convert(Entity entity, EntityManager entityManager, GameObjectConversionWorld gameObjectConversionWorld)
				{
					var buffer = ;
				}
			}
			'''
		}
		else
		{
			var classifier = #[number,number2,number3,number4].contains(type) ? "struct" : "class"
			var field = type != empty? '''public «type.unity» Value;''' : ""
			
			content =
			'''
			using Unity.Entities;
			
			[GenerateAuthoringComponent]
			public «classifier» «name» : IComponentData
			{
				«field»
			}
			'''
		}
		
		fileSystem.generateFile('''Unity/Assets/Code/Components/«name».cs''',content);
	}
	
	def private serializeSystem(Function function)
	{
		var name = function.name
		var content =
		'''
		using Unity.Entities;
		using Unity.Jobs;
		
		[UpdateInGroup(typeof(Gameplay))]
		public class «name» : SystemBase
		{
			protected override OnUpdate()
			{
				«function.statements.join('',[code])»
			}
		}
		'''
		
		fileSystem.generateFile('''Unity/Assets/Code/Systems/«name».cs''',content)
	}
	
	def private toArray(Entry<String,HashSet<AccessKind>> entry, String entity)
	{
		var component = entry.key
		var isTag = entry.value.contains(AccessKind.tag)
		
		if (isTag) return ""
		
		'''«component»s_«entity» = «entity».ToComponentDataArray(TempJob);'''
	}
	
	def private toComponent(Entry<String,HashSet<AccessKind>> entry, String entity)
	{
		var component = entry.key
		'''«component»_«entity» = «component»s_«entity»[«entity»_i];'''
	}
	
	def private String code(Statement statement)
	{
		if (statement instanceof Foreach)
		{
			val a = statement.variable.name
			var query = queries.get(a)
			
			'''
			«query?.entrySet?.join('\n',[toArray(a)])»
			for (var «a»_i = 0; «a»_i < «a».CalculateEntityCount(); «a»_i++)
			{
				«query?.entrySet?.forEach[toComponent(a)]»
				«statement.statements.join('',[code])»
			}
			'''
		}
		else if (statement instanceof Selection)
		{
			var condition = code(statement.condition)
			'''
			if («condition»)
			{
				«statement.statements.join('\n',[code])»
			}
			'''
		}
		else if (statement instanceof Iteration)
		{
			'''
			while («statement.condition.code»)
			{
				«statement.statements.join('\n', [code])»
			}
			'''
		}
		else if (statement instanceof Assignment)
		{
			'''
			«statement.atom.code» = «statement.expression.code»;
			'''
		}
		else if (statement instanceof Call)
		{
			'''
			«statement.name» («statement.arguments.join(', ', [code])»
			'''
		}
		else
		{
			'''Not implemented'''
		}
	}
	
	def private String code(Expression e)
	{
		if 		(e instanceof Or) 		'''«e.left.code» || «e.right.code»'''
		else if (e instanceof And)  	'''«e.left.code» && «e.right.code»'''
		else if (e instanceof Equality) '''«e.left.code» == «e.right.code»'''
		else if (e instanceof Inequality)'''«e.left.code» != «e.right.code»'''
		else if (e instanceof Lower) 	'''«e.left.code» < «e.right.code»'''
		else if (e instanceof LowerOrEqual) '''«e.left.code» <= «e.right.code»'''
		else if (e instanceof GreaterOrEqual) '''«e.left.code» >= «e.right.code»'''
		else if (e instanceof Greater) '''«e.left.code» > «e.right.code»'''
		else if (e instanceof Addition) '''«e.left.code» + «e.right.code»'''
		else if (e instanceof Subtraction) '''«e.left.code» - «e.right.code»'''
		else if (e instanceof Multiplication) '''«e.left.code» * «e.right.code»'''
		else if (e instanceof Division) '''«e.left.code» / «e.right.code»'''
		
		else if (e instanceof Negation) '''!«e.expression.code»'''
		else if (e instanceof Priorization) '''(«e.expression.code»)'''
		
		else if (e instanceof Variable) '''«e.name»'''
		else if (e instanceof Cell) '''«e.component»_«e.entity.name»'''
		else '''Not implemented'''
	}
}