package m.formatting2

import com.google.inject.Inject
import m.m.Component
import m.m.Entity
import m.services.TextGrammarAccess
import org.eclipse.xtext.formatting2.IFormattableDocument

class TextFormatter extends MFormatter 
{
	@Inject
	extension TextGrammarAccess access

	override dispatch void format(Entity entity, extension IFormattableDocument document) 
	{
		val open = entity.regionFor.keyword('{')
		val close = entity.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		entity.regionFor.keywords(',').forEach[prepend[noSpace].append[autowrap(8)]]
		
		var rendering = #['position','rotation','scale','mesh','material','number']
		var physics = #['mass','restitution','friction','extents','radius', 'triggerZone']
		
		var Component last = null
		
		for (component : entity.components) 
		{
			if (last !== null)
			{
				if (rendering.contains(last.name) && !rendering.contains(component.name))
				{
					component.prepend[newLine]
				}
				else if (physics.contains(last.name) && !physics.contains(component.name))
				{
					component.prepend[newLine]
				}
				else if (last.values.size > 0 && component.values.size == 0 && !physics.contains(last.name) && !rendering.contains(last.name))
				{
					component.prepend[newLine]
				}
				else if (last.values.empty && component.values.size > 0)
				{
					component.prepend[newLine]
				}
			}
			component.format
			last = component
		}
	}
}