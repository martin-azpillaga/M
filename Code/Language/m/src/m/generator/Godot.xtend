package m.generator

import org.eclipse.xtext.generator.IFileSystemAccess2
import m.validation.Type
import static m.validation.Type.*
import static m.validation.ProductType.*
import m.validation.ExponentType
import m.m.Function
import java.util.HashMap
import org.eclipse.xtext.EcoreUtil2
import m.m.Assignment
import m.m.Cell
import m.m.Statement
import m.m.Block
import m.m.Value
import m.m.Delegation
import m.m.Expression
import m.m.Binary
import m.m.Unary
import m.m.Application
import static m.validation.Symbols.*
import m.validation.StandardLibrary

class Godot
{
	IFileSystemAccess2 fileSystem
	
	var queries = new HashMap<String,HashMap<String,AccessKind>>
	var library = StandardLibrary.English
	
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.fileSystem = fileSystem
		
		fileSystem.generateFile('Godot/project.godot', '')
		for (component : game.components.entrySet)
		{
			generate(component.key, component.value)
		}
		for (system : game.functions.entrySet)
		{
			var type = system.value as ExponentType
			if (type.left === null && type.right === null)
			{
				generateSystem(system.key, system.value)
			}
		}
	}
	
	def void generate(String component, Type type)
	{
		fileSystem.generateFile('''Godot/Code/Components/«component».gd''',
			'''
			extends Node
			
			«IF type != unit»export var Value: «type.name» «ENDIF»
			
			func get_class(): return "«component»"
			'''
		)
	}
	
	def void generateSystem(Function function, Type type)
	{
		var name = function.name
		
		function.collectQueries
		
		var statements = function.statements.map[code].join('\n')
		
		fileSystem.generateFile('''Godot/Code/Systems/«name».gd''',
		'''
		extends Node
		
		func _process(delta):
			var root = get_tree().get_root().get_child(0)
			«statements»
		''')
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
				for «a» in root.entities([«query.keySet.map['''"«it.component»"'''].join(",")»]):
					«FOR component : query.keySet»
					var «component»_«a» = «a»["«component.component»"];
					«ENDFOR»
					«statement.statements.join("\n",[code])»
				'''
			}
			else if (statement.name == selection.name)
			{
				var condition = code(statement.expression)
				'''
				if «condition»:
				{
					«statement.statements.join('\n',[code])»
				}
				'''
			}
			else if (statement.name == iteration.name)
			{
				'''
				while «statement.expression.code»:
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
				var «atom.code» = «statement.expression.code»
				'''
			}
			else if (atom instanceof Cell)
			{
				'''
				«atom.code» = «statement.expression.code»
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
		if (e instanceof Binary) '''«e.left.code» «e.operator» «e.right.code»'''
		else if (e instanceof Unary) '''«e.operator» «e.expression.code»'''
		else if (e instanceof Value) '''«e.name»'''
		else if (e instanceof Cell)
		{
			'''«e.component»_«e.entity».«field(e.component)»'''
		}
		else if (e instanceof Application)
		{
			if (e.name == in.name)
			{
				'''«e.arguments.get(1).code».Contains(entity_«e.arguments.get(0).code»)'''
			}
			else if (e.name == remove.name)
			{
				'''EntityManager.RemoveComponent<«e.generic»>(entity_«e.arguments.get(0).code»)'''
			}
			else if (e.name == add.name)
			{
				'''EntityManager.AddComponentData(entity_«e.arguments.get(0).code», new «e.generic»())'''
			}
			else
			{
				'''«application(e.name)»(«e.arguments.map[code].join(', ')»)'''
			}
		}
	}
	
	def private String component(String component)
	{
		var found = library.symbols.findFirst[it.name == component]
		if (found === null)
		{
			return component
		}
		else
		{
			switch found
			{
				case velocity: return "RigidBody"
				case inputValue: return "inputValue"
				case timeout: return "timeout"
				case position: return "Node2D"
				case collisions: return "Collisions"
				case numberLabel: return "number"
			}
		}
	}
	
	def private String field(String component)
	{
		var found = library.symbols.findFirst[it.name == component]
		if (found === null)
		{
			return "Value"
		}
		else
		{
			switch found
			{
				case velocity: return "linear_velocity"
				case inputValue: return "Value"
				case timeout: return "Value"
				case position: return "position"
				case collisions: return "Value"
				case numberLabel: return "Value"
			}
		}
	}
	
	def private String application(String name)
	{
		
	}
	
	def private String name(Type type)
	{
		switch type
		{
			case number: 'float'
			case number2: 'Vector2'
			case number3: 'Vector3'
			case proposition: 'bool'
			default: 'not present in name(Type) in Godot.xtend'
		}
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
}