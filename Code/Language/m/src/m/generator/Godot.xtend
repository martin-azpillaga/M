package m.generator

import org.eclipse.xtext.generator.IFileSystemAccess2
import m.validation.Type
import static m.validation.Type.*
import static m.validation.ProductType.*

class Godot
{
	IFileSystemAccess2 fileSystem
	
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		this.fileSystem = fileSystem
		
		fileSystem.generateFile('Godot/project.godot', '')
		for (component : game.components.entrySet)
		{
			generate(component.key, component.value)
		}
	}
	
	def void generate(String component, Type type)
	{
		fileSystem.generateFile('''Godot/Code/Components/«component».gd''',
			'''
			extends Node
			
			«IF type != unit»export var Value: «type.name» «ENDIF»
			'''
		)
	}
	
	def String name(Type type)
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
}