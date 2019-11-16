package m.modeler

import m.cBlocks.File
import org.eclipse.xtext.generator.IFileSystemAccess2
import m.TextRuntimeModule
import static extension m.modeler.GenericSerializer.*
import m.m.MFactory
import m.cBlocks.Element
import java.util.List
import m.m.Component

class BlocksModeler 
{
	extension MFactory factory = MFactory.eINSTANCE
	
	def generate(File file, IFileSystemAccess2 fsa, String... folders)
	{
		var compiled = file.compile
		for (folder : folders)
		{
			generate(compiled, new TextRuntimeModule, fsa, folder+'main.text.gen')
		}
	}
	
	def compile(File file)
	{
		var game = createGame
		
		var xml = file.elements.head
		for (element : xml.elements)
		{
			var type = element.attributes.get(0).value
			
			if (type == 'entity')
			{
				game.entities.add(element.compileEntity)
			}
			else if (type == 'system')
			{
				
			}
		}
		
		return game
	}
	
	def compileEntity(Element element)
	{
		var entity = createEntity
		
		var name = element.elements.head
		var components = element.elements.get(1)
		if (components.elements.size > 0)
		{
			entity.components.addComponent(components.elements.head)
		}
		
		entity.name = name.text
		
		return entity
	}
	
	def void addComponent(List<Component> list, Element element)
	{
		var component = createComponent
		list.add(component)
		var first = element
		component.name = first.elements.head.text
		if (first.attributes.head.value != 'tagcomponent')
		{
			var value = first.elements.get(1).elements.head
			var valueType = value.attributes.head.value
			if (valueType == 'real1value')
			{
				component.values.add(value.elements.head.text)
			}
			else if (valueType == 'real2value')
			{
				component.values.add(value.elements.head.text)
				component.values.add(value.elements.get(1).text)
			}
			else if (valueType == 'real3value')
			{
				component.values.add(value.elements.head.text)
				component.values.add(value.elements.get(1).text)
				component.values.add(value.elements.get(2).text)
			}
			else if (valueType == 'real4value')
			{
				component.values.add(value.elements.head.text)
				component.values.add(value.elements.get(1).text)
				component.values.add(value.elements.get(2).text)
				component.values.add(value.elements.get(3).text)
			}
			else if (valueType == 'wordvalue')
			{
				component.values.addAll(value.elements.head.text.split(' '))
			}
		}
		if (first.elements.size > 2)
		{
			var next = first.elements.get(2)
			list.addComponent(next.elements.head)
		}
	}
	
	def compileSystem(Element element)
	{
		var system = createSystem
		
		var name = element.elements.head
		
		system.name = name.text
		
		return system
	}
}