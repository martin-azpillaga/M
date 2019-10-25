package m.formatting2

import m.json.Field
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.json.StringValue

class JsonFormatter extends AbstractFormatter2 
{
	def dispatch void format(m.json.Object object, extension IFormattableDocument document) 
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
		object.fields.forEach[format]
	}

	def dispatch void format(Field field, extension IFormattableDocument document) 
	{
		field.prepend[newLine]
		field.regionFor.keyword(':').surround[oneSpace]
		field.value.format
	}
	
	def dispatch void format(StringValue value, extension IFormattableDocument document)
	{
		
	}
}
