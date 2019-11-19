package m.formatting2

import com.google.inject.Inject
import m.mxml.Element
import m.mxml.Model
import m.services.XMLGrammarAccess
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.mxml.Attribute
import m.mxml.Children

class XMLFormatter extends AbstractFormatter2 
{
	
	@Inject extension XMLGrammarAccess

	def dispatch format(Model model, extension IFormattableDocument document)
	{
		model.root.format
	}
	
	def dispatch void format(Element element, extension IFormattableDocument document) 
	{
		element.regionFor.keyword('<').surround[noSpace]
		element.regionFor.keywords('>').forEach[surround[noSpace]]
		element.regionFor.keyword('/>').surround[noSpace]
		element.regionFor.keyword('</').surround[noSpace]
		element.attributes.forEach[format]
		
		var content = element.content
		if (content instanceof Children)
		{
			var open = element.regionFor.keyword(elementAccess.greaterThanSignKeyword_3_1_0)
			var close = element.regionFor.keyword(elementAccess.lessThanSignSolidusKeyword_3_1_2)
			
			interior(open,close)[indent]
			close.prepend[newLine]
			content.elements.forEach[prepend[newLine] format]
		}
	}

	def dispatch void format(Attribute attribute, extension IFormattableDocument document) 
	{
		attribute.regionFor.keyword('=').surround[noSpace]
	}
}
