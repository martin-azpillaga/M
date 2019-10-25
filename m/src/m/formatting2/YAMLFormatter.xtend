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
		//var open = map.regionFor.ruleCall(mapAccess.BEGINTerminalRuleCall_3)
		//var close = map.regionFor.ruleCall(mapAccess.ENDTerminalRuleCall_7)
		//interior(open, close)[indent]
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
	/*
	def dispatch format(Version version, extension IFormattableDocument document)
	{
		version.regionFor.keyword('.').surround[noSpace]
	}
	
	def dispatch format(Document document, extension IFormattableDocument e)
	{
		document.regionFor.assignment(documentAccess.tagAssignment_2_1).prepend[noSpace]
		document.id.prepend[noSpace]
		document.value.prepend[newLine]
		document.value.format
	}
	
	def dispatch format(Map map, extension IFormattableDocument e)
	{
		map.regionFor.keyword(':').prepend[noSpace]
		indent+='  '
		for (value : map.body)
		{
			value.prepend[space='\n'+indent]
			value.format
		}
		indent = indent.substring(0, indent.length - 2)
	}
	
	def dispatch format(List list, extension IFormattableDocument e)
	{
		list.regionFor.keyword(':').prepend[noSpace]
		list.regionFor.keywords('-').forEach[prepend[space='\n'+indent]]
		for (value : list.values)
		{
			value.format
		}
	}
	
	def dispatch format(KeyValue kv, extension IFormattableDocument e)
	{
		kv.regionFor.keyword(': ').surround[noSpace]
		kv.value.format
	}
	
	def dispatch format(Guid guid, extension IFormattableDocument e)
	{
		guid.regionFor.assignment(guidAccess.NAssignment_1).append[noSpace]
	}
	
	def dispatch format(InlineMap map, extension IFormattableDocument e)
	{
		map.regionFor.keywords(',').forEach[prepend[noSpace]]
		for (value : map.values)
		{
			value.format
		}
	}*/
}
