package m.modeler

import m.mxml.Model
import org.eclipse.xtext.generator.IFileSystemAccess2
import static extension m.modeler.GenericSerializer.*
import m.m.MFactory
import m.mxml.Element
import java.util.List
import m.m.Component
import m.mxml.Children
import m.mxml.Text
import m.TextRuntimeModule

class BlocksModeler 
{
	extension MFactory factory = MFactory.eINSTANCE
	
	def generate(Model model, IFileSystemAccess2 fsa, String... folders)
	{
		var compiled = model.compile
		for (folder : folders)
		{
			generate(compiled, new TextRuntimeModule, fsa, folder+'main.content as Text).toString.gen')
		}
	}
	
	def compile(Model model)
	{
		var game = createGame
		
		var xml = model.root
		for (element : (xml.content as Children).elements)
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
		var content = element.content as Children
		
		var name = content.elements.head
		var components = content.elements.get(1)
		if (components.content instanceof Children)
		{
			entity.components.addComponent((components.content as Children).elements.head)
		}
		
		entity.name = (name.content as Text).toString
		
		return entity
	}
	
	def void addComponent(List<Component> list, Element element)
	{
		var component = createComponent
		list.add(component)
		var first = element
		component.name = ((first.content as Children).elements.head.content as Text).toString
		if (first.attributes.head.value != 'tagcomponent')
		{
			var value = ((first.content as Children).elements.get(1).content as Children).elements.head
			var valueType = value.attributes.head.value
			if (valueType == 'real1value')
			{
				component.values.add(((value.content as Children).elements.head.content as Text).toString)
			}
			else if (valueType == 'real2value')
			{
				component.values.add(((value.content as Children).elements.head.content as Text).toString)
				component.values.add(((value.content as Children).elements.get(1).content as Text).toString)
			}
			else if (valueType == 'real3value')
			{
				component.values.add(((value.content as Children).elements.head.content as Text).toString)
				component.values.add(((value.content as Children).elements.get(1).content as Text).toString)
				component.values.add(((value.content as Children).elements.get(2).content as Text).toString)
			}
			else if (valueType == 'real4value')
			{
				component.values.add(((value.content as Children).elements.head.content as Text).toString)
				component.values.add(((value.content as Children).elements.get(1).content as Text).toString)
				component.values.add(((value.content as Children).elements.get(2).content as Text).toString)
				component.values.add(((value.content as Children).elements.get(3).content as Text).toString)
			}
			else if (valueType == 'wordvalue')
			{
				component.values.addAll(((value.content as Children).elements.head.content as Text).toString.split(' '))
			}
		}
		if ((first.content as Children).elements.size > 2)
		{
			var next = (first.content as Children).elements.get(2)
			list.addComponent((next.content as Children).elements.head)
		}
	}
	
	def compileSystem(Element element)
	{
		var system = createSystem
		
		var name = (element.content as Children).elements.head
		
		system.name = (name.content as Text).toString
		
		return system
	}
}