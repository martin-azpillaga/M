package m.formatting2

import m.yaml.Document
import m.yaml.File
import m.yaml.KeyValue
import m.yaml.Map
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import com.google.inject.Inject
import m.services.YAMLGrammarAccess
import m.yaml.InlineMap
import m.yaml.KeyValueList

class YAMLFormatter extends AbstractFormatter2 
{	
	@Inject extension YAMLGrammarAccess access
	
	
	def dispatch format(File file, extension IFormattableDocument document) 
	{
		file.regionFor.ruleCall(fileAccess.WSTerminalRuleCall_1_1).surround[noSpace]
		for (node : file.nodes)
		{
			if (node != file.nodes.head)
			{
				node.prepend[newLine]
			}
			else
			{
				node.prepend[noSpace]
			}
		}
		file.nodes.forEach[format]
		file.regionFor.assignment(fileAccess.tagsAssignment_1_2).surround[noSpace]
	}
	
	def dispatch format(KeyValue keyValue, extension IFormattableDocument document)
	{
		keyValue.regionFor.keyword(':').surround[noSpace]
		keyValue.value?.surround[noSpace]
		keyValue.value?.format
	}
	
	def dispatch format(Document document, extension IFormattableDocument e)
	{
		document.prepend[newLine]
		document.regionFor.keyword('---').surround[noSpace]
		
		document.regionFor.keyword('!u!').surround[noSpace]
		document.regionFor.assignment(documentAccess.tagAssignment_4).surround[noSpace]
		document.regionFor.keyword('&').surround[noSpace]
		document.regionFor.assignment(documentAccess.idAssignment_7).surround[noSpace]

		document.value.format
	}
	
	def dispatch format(Map map, extension IFormattableDocument e)
	{
		map.prepend[noSpace]
		map.regionFor.keyword(':').surround[noSpace]
		
		for (value : map.body)
		{
			value.surround[indent]
			value.format
		}
	}
	
	def dispatch format(InlineMap map, extension IFormattableDocument e)
	{
		map.regionFor.keyword('{').surround[noSpace]
		map.regionFor.keyword('}').surround[noSpace]
		map.regionFor.keywords(':').forEach[surround[noSpace]]
		map.regionFor.keywords(',').forEach[surround[noSpace]]
		for (value : map.values)
		{
			value.surround[noSpace]
			value.format
		}
	}
	
	def dispatch format(KeyValueList keyValueList, extension IFormattableDocument document)
	{
		keyValueList.values.forEach[format]
		keyValueList.regionFor.keywords('-').forEach[prepend[oneSpace]]
		keyValueList.regionFor.keyword(':').surround[noSpace]
		keyValueList.regionFor.ruleCall(keyValueListAccess.SPACETerminalRuleCall_4).surround[noSpace]
		keyValueList.regionFor.ruleCalls(keyValueListAccess.SPACETerminalRuleCall_6_2).forEach[surround[noSpace]]
	}
}
