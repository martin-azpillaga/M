package m.formatting2

import m.json.Object
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.json.Member

class JSONFormatter extends AbstractFormatter2 
{	
	def dispatch void format(Object object, extension IFormattableDocument document) 
	{
		var open = object.regionFor.keyword('{')
		var close = object.regionFor.keyword('}')
		if (object.eContainer !== null)
		{
			open.prepend[newLine]
		}
		close.prepend[newLine]
		interior(open,close)[indent]
		object.regionFor.keywords(',').forEach[prepend[noSpace]]
		object.members.forEach[format]
	}

	def dispatch void format(Member member, extension IFormattableDocument document) 
	{
		member.prepend[newLine]
		member.regionFor.keyword(':').surround[oneSpace]
		member.value.format
	}
}
