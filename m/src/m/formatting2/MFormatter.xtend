package m.formatting2

import m.m.Entity
import m.m.Game
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.m.Component
import com.google.inject.Inject
import m.m.Loop
import m.services.MGrammarAccess

class MFormatter extends StructuredFormatter 
{
	@Inject
	extension MGrammarAccess access
	
	def dispatch void format(Game game, extension IFormattableDocument document) 
	{
		for (entity : game.entities) 
		{
			entity.append[newLines = 2]
			entity.format
		}
		for (system : game.systems) 
		{
			system.append[newLines = 2]
			system.format
		}
	}

	def dispatch void format(Entity entity, extension IFormattableDocument document) 
	{
		val open = entity.regionFor.keyword('{')
		val close = entity.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		entity.regionFor.keywords(',').forEach[prepend[noSpace].append[autowrap(8)]]
		
		for (component : entity.components) 
		{
			component.format
		}
	}
	
	def dispatch void format(Component component, extension IFormattableDocument document)
	{
		component.regionFor.assignments(componentAccess.valuesAssignment_1_0).forEach[prepend[oneSpace]]
		component.regionFor.assignments(componentAccess.valuesAssignment_1_1).forEach[prepend[oneSpace]]
	}
	
	def dispatch format(m.m.System system, extension IFormattableDocument document)
	{
		system.regionFor.keyword(':').prepend[noSpace]
		system.statements.forEach[format]
	}
	
	def dispatch format(Loop loop, extension IFormattableDocument document)
	{
		loop.prepend[newLine]
		
		val open = loop.regionFor.keyword('{')
		val close = loop.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		loop.statements.forEach[format]
	}
}