package m

import java.util.ArrayList
import static m.FeatureKind.*
import java.util.List
import static java.nio.file.Paths.*
import static java.nio.file.Files.*

class Category
{
	public String name;
	public int color;
	public List<Block> blocks = new ArrayList<Block>();
}
class Block
{
	public String name;
	public List<String> topConnection = new ArrayList<String>;
	public List<String> bottomConnection = new ArrayList<String>;
	public List<String> leftConnection = new ArrayList<String>;
	public List<Input> inputs = new ArrayList<Input>();
}
		
class Input
{
	public FeatureKind kind;
	public String name;
	public List<String> acceptedTypes = new ArrayList<String>();
	public List<String> fields = new ArrayList<String>();
}
enum FeatureKind
{
	single, multiple, mutation;
}
class Blockify
{
	def static void main(String[] args)
	{
		var categories = #[new Category=>
		[
			name='Root'
			color= 200
			blocks = #
			[
				new Block=>
				[
					name = "Function"
					topConnection = #[]
					bottomConnection = #[]
					inputs = #[new Input=>
					[
						kind = mutation
						name = "parameters"
						acceptedTypes = #["Value"]
						fields = #['name']
					],new Input=>
					[
						kind = multiple
						name = "statements"
						acceptedTypes = #["Block","Assignment"]
					],new Input=>
					[
						kind = single
						name = "result"
						acceptedTypes = #["Expression"]
					]]
				]
			]
		]]
		
		write(get("../../Application/Blockly/custom2.js"), categories.print.toString.bytes);
	}
	
	private def static print(List<Category> categories)
	{
		'''
		var toolbox = document.createElement('XML')
		toolbox.setAttribute('xmlns','https://developers.google.com/blockly/xml')
		toolbox.innerHTML =
		`
		«FOR category : categories»
		<category name='«category.name»'>
		«FOR block : category.blocks»
		<block type='«block.name»'></block>
		«ENDFOR»
		</category>
		`
		«ENDFOR»
		Blockly.inject('blocklyDiv', {toolbox: toolbox})
		
		«FOR category : categories»
		«FOR block : category.blocks»
		Blockly.Blocks['«block.name»'] =
		{
			init: function()
			{
				«IF !block.topConnection.empty»
				this.setPreviousStatement(true, «block.topConnection.s»)
				«ENDIF»
				«IF !block.bottomConnection.empty»
				this.setNextStatement(true, «block.bottomConnection.s»)
				«ENDIF»
				«IF !block.leftConnection.empty»
				this.setOutput(true, «block.leftConnection.s»)
				«ENDIF»
				«FOR input : block.inputs»
				«IF input.kind == single»
				this.appendValueInput('«input.name»').setCheck(«input.acceptedTypes.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == multiple»
				this.appendStatementInput('«input.name»').setCheck(«input.acceptedTypes.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == mutation»
				var dummy = this.appendDummyInput('«input.name»')
				dummy«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR».appendField(new Blockly.FieldNumber(0,0,null,0, function (value)
				{
					this.sourceBlock_?.update(dummy, value)
				}), '«input.name»')
				«ENDIF»
				«ENDFOR»
				this.setInputsInline(true)
				this.setColour(«category.color»)
			},
			«IF block.inputs.exists[kind==mutation]»
			mutationToDom: function ()
			{
				var mutation = document.createElement('mutation')
				«FOR input : block.inputs.filter[kind==mutation]»
				var «input.name» = this.getFieldValue('«input.name»')
				mutation.setAttribute('«input.name»', «input.name»)
				«ENDFOR»
				return mutation
			},
			domToMutation: function (xml)
			{
				«FOR input : block.inputs.filter[kind==mutation]»
				var «input.name» = xml.getAttribute('«input.name»')
				var input
				for (var i=0; i<this.inputList.length;i++)
				{
					if (this.inputList[i].name === '«input.name»')
					{
						input = this.inputList[i]
					}
				}
				this.update(input, «input.name»)
				«ENDFOR»
			},
			update: function (input, value)
			{
				value = Number(value)
				var index = this.inputList.indexOf(input)
				for (var i=this.inputList.length-1; i > index; i--)
				{
					if (this.inputList[i].name.startsWith(input.name))
					{
						this.removeInput(this.inputList[i].name)
					}
				}
				«FOR input : block.inputs.filter[kind==mutation]»
				for (var i=0; i < value; i++)
				{
					this.appendValueInput('«input.name»'+i)
					this.moveNumberedInputBefore(this.inputList.length-1, index+1)
				}
				«ENDFOR»
			}
			«ENDIF»
		}
		«ENDFOR»
		«ENDFOR»
		'''
	}
	
	private static def s(List<String> list)
	{
		if (list.empty)
		{
			return null
		}
		else if (list.size == 1)
		{
			return "'"+list.head+"'"
		}
		else
		{
			return "["+list.map["'"+it+"'"].join(",")+"]"
		}
	}
}

