package m.formatting2

import com.google.inject.Inject
import m.services.BlocksGrammarAccess
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.cBlocks.Element
import m.cBlocks.Attribute
import m.cBlocks.File

class BlocksFormatter extends AbstractFormatter2 
{
	
	@Inject extension BlocksGrammarAccess

	def dispatch format(File file, extension IFormattableDocument document)
	{
		for (element : file.elements)
		{
			element.format
		}
	}
	
	def dispatch void format(Element element, extension IFormattableDocument document) 
	{
		element.regionFor.keyword('<').surround[noSpace]
		element.regionFor.keywords('>').forEach[surround[noSpace]]
		element.regionFor.keyword('/>').surround[noSpace]
		element.regionFor.keyword('</').surround[noSpace]
		
		if (element.elements.size > 0)
		{
			var open = element.regionFor.keyword(elementAccess.greaterThanSignKeyword_3_1_0)
			var close = element.regionFor.keyword(elementAccess.lessThanSignSolidusKeyword_3_1_2)
			
			interior(open,close)[indent]
			close.prepend[newLine]
		}
		element.attributes.forEach[format]
		element.elements.forEach[prepend[newLine] format]
	}

	def dispatch void format(Attribute attribute, extension IFormattableDocument document) 
	{
		attribute.regionFor.keyword('=').surround[noSpace]
	}
}
