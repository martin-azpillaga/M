package m.generator

/*
 * class Godot
{
	IFileSystemAccess2 fileSystem
	
	var queries = new HashMap<String,HashMap<String,AccessKind>>
	var library = Language.ENGLISH;
	
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.fileSystem = fileSystem
		
		//fileSystem.generateFile('Godot/project.godot', '')
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
			
			«IF type != unit»export var Value: «type.name»«ENDIF»
			
			func get_class(): return "«component»"
			func is_class(type): return type == get_class() || .is_class(type)
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
					var entity_«a» = «a»["entity"]
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
					«statement.statements.join('\n',[code])»
				'''
			}
			else if (statement.name == iteration.name)
			{
				'''
				while «statement.expression.code»:
					«statement.statements.join('\n', [code])»
				
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
		else if (statement instanceof Application)
		{
			'''«(statement as Application).codeStatement»;'''
		}
	}
	
	def private String codeStatement(Application e)
	{
		if (e.name == in.name)
		{
			'''entity_«e.arguments.get(0).code» in «e.arguments.get(1).code»;'''
		}
		else if (e.name == create.name)
		{
			var path = e.arguments.get(0).code
			'''root.get_node("Entities").add_child(load(«path»).instance());'''
		}
		else if (e.name == destroy.name)
		{
			var entity = e.arguments.get(0).code
			'''entity_«entity».queue_free();'''
		}
		else if (e.name == remove.name)
		{
			''''''
			//'''«e.generic»_«entity».queue_free()'''
		}
		else if (e.name == add.name)
		{
			''''''
			//'''entity_«entity».add_child(preload("res://Engine/«e.generic».gd").new())'''
		}
		else if (e.name == random.name)
		{
			var range = e.arguments.get(0).code
			'''rand_range(«range».x,«range».y);'''
		}
		else
		{
			'''«application(e.name)»(«e.arguments.map[code].join(', ')»);'''
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
				'''entity_«e.arguments.get(0).code» in «e.arguments.get(1).code»'''
			}
			else if (e.name == create.name)
			{
				var path = e.arguments.get(0).code
				'''root.get_node("Entities").add_child(load(«path»).instance())'''
			}
			else if (e.name == destroy.name)
			{
				var entity = e.arguments.get(0).code
				'''entity_«entity».queue_free()'''
			}
			else if (e.name == remove.name)
			{
				''''''
				//'''«e.generic»_«entity».queue_free()'''
			}
			else if (e.name == add.name)
			{
				''''''
				//'''entity_«entity».add_child(preload("res://Engine/«e.generic».gd").new())'''
			}
			else if (e.name == random.name)
			{
				var range = e.arguments.get(0).code
				'''rand_range(«range».x,«range».y)'''
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
				case position: return "Spatial"
				case collisions: return "collisions"
				case numberLabel: return "number"
			}
		}
	}
	
	def private String field(Value value)
	{
		val component = value.getName();
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
				case position: return "translation"
				case collisions: return "Value"
				case numberLabel: return "Value"
			}
		}
	}
	
	def private String application(String name)
	{
		var found = library.symbols.findFirst[it.name == name]
		if (found === null)
		{
			return "Value"
		}
		else
		{
			switch found
			{
				case random: return "rand_range"
				case cos: return "cos"
				case sin: return "sin"
				case xyz: return "Vector3"
				case in: "contains"
			}
		}
	}
	
	def private String name(Type type)
	{
		switch type
		{
			case number: 'float'
			case number2: 'Vector2'
			case number3: 'Vector3'
			case proposition: 'bool'
			case entity: 'String'
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
					cell.entity.name.setComponentAccess(cell.component.name,AccessKind.write)
				}
				else
				{
					cell.entity.name.setComponentAccess(cell.component.name,AccessKind.read)
				}
			}
			else
			{
					cell.entity.name.setComponentAccess(cell.component.name,AccessKind.read)
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
}*/