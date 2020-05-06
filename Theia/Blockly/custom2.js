var toolbox = document.createElement('XML')
toolbox.setAttribute('xmlns','https://developers.google.com/blockly/xml')
toolbox.innerHTML =
`
<category name='Root'>
<block type='File'></block>
<block type='Function'></block>
</category>
<category name='Statement'>
<block type='Block'></block>
<block type='Assignment'></block>
<block type='Delegation'></block>
</category>
<category name='Expression'>
<block type='Value'></block>
<block type='Cell'></block>
<block type='Binary'></block>
<block type='Unary'></block>
<block type='Application'></block>
</category>
`
Blockly.inject('blocklyDiv', {toolbox: toolbox})

Blockly.Blocks['File'] =
{
	init: function()
	{
		this.appendStatementInput('functions').setCheck('Function')
		this.setInputsInline(true)
		this.setColour(100)
	},
}
Blockly.Blocks['Function'] =
{
	init: function()
	{
		this.setPreviousStatement(true, 'Function')
		this.setNextStatement(true, 'Function')
		var dummy = this.appendDummyInput('parameters')
		dummy.appendField(new Blockly.FieldTextInput('name'),'name').appendField(new Blockly.FieldNumber(0,0,null,0, function (value)
		{
			if (this.sourceBlock_)
			{
				this.sourceBlock_.update(dummy, value)
			}
		}), 'parameters')
		this.appendStatementInput('statements').setCheck(['Block','Assignment','Delegation','Statement'])
		this.appendValueInput('result').setCheck(['Value','Cell','Binary','Unary','Application','Expression'])
		this.setInputsInline(true)
		this.setColour(100)
	},
	mutationToDom: function ()
	{
		var mutation = document.createElement('mutation')
		var parameters = this.getFieldValue('parameters')
		mutation.setAttribute('parameters', parameters)
		return mutation
	},
	domToMutation: function (xml)
	{
		var parameters = xml.getAttribute('parameters')
		var input
		for (var i=0; i<this.inputList.length;i++)
		{
			if (this.inputList[i].name === 'parameters')
			{
				input = this.inputList[i]
			}
		}
		this.update(input, parameters)
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
		for (var i=0; i < value; i++)
		{
			this.appendValueInput('parameters'+i)
			if (this.inputList.length-1 != index+1)
			{
				this.moveNumberedInputBefore(this.inputList.length-1, index+1)
			}
		}
	}
}
Blockly.Blocks['Block'] =
{
	init: function()
	{
		this.setPreviousStatement(true, 'Block')
		this.setNextStatement(true, ['Block','Assignment','Delegation','Block'])
		this.appendValueInput('expression').setCheck(['Value','Cell','Binary','Unary','Application','Expression']).appendField(new Blockly.FieldTextInput('name'),'name')
		this.appendStatementInput('statements').setCheck(['Block','Assignment','Delegation','Statement'])
		this.setInputsInline(true)
		this.setColour(150)
	},
}
Blockly.Blocks['Assignment'] =
{
	init: function()
	{
		this.setPreviousStatement(true, 'Assignment')
		this.setNextStatement(true, ['Block','Assignment','Delegation','Assignment'])
		this.appendValueInput('atom').setCheck(['Value','Cell','Binary','Unary','Application','Expression'])
		this.appendValueInput('expression').setCheck(['Value','Cell','Binary','Unary','Application','Expression'])
		this.setInputsInline(true)
		this.setColour(150)
	},
}
Blockly.Blocks['Delegation'] =
{
	init: function()
	{
		this.setPreviousStatement(true, 'Delegation')
		this.setNextStatement(true, ['Block','Assignment','Delegation','Delegation'])
		this.appendValueInput('application').setCheck('Application')
		this.setInputsInline(true)
		this.setColour(150)
	},
}
Blockly.Blocks['Value'] =
{
	init: function()
	{
		this.setOutput(true, 'Value')
		this.appendDummyInput('').appendField(new Blockly.FieldTextInput('name'),'name')
		this.setInputsInline(true)
		this.setColour(200)
	},
}
Blockly.Blocks['Cell'] =
{
	init: function()
	{
		this.setOutput(true, 'Cell')
		this.appendDummyInput('').appendField(new Blockly.FieldTextInput('entity'),'entity').appendField(new Blockly.FieldTextInput('component'),'component')
		this.setInputsInline(true)
		this.setColour(200)
	},
}
Blockly.Blocks['Binary'] =
{
	init: function()
	{
		this.setOutput(true, 'Binary')
		this.appendValueInput('left').setCheck(['Value','Cell','Binary','Unary','Application','Expression'])
		this.appendValueInput('right').setCheck(['Value','Cell','Binary','Unary','Application','Expression']).appendField(new Blockly.FieldTextInput('operator'),'operator')
		this.setInputsInline(true)
		this.setColour(200)
	},
}
Blockly.Blocks['Unary'] =
{
	init: function()
	{
		this.setOutput(true, 'Unary')
		this.appendValueInput('expression').setCheck(['Value','Cell','Binary','Unary','Application','Expression']).appendField(new Blockly.FieldTextInput('operator'),'operator')
		this.setInputsInline(true)
		this.setColour(200)
	},
}
Blockly.Blocks['Application'] =
{
	init: function()
	{
		this.setOutput(true, 'Application')
		var dummy = this.appendDummyInput('arguments')
		dummy.appendField(new Blockly.FieldTextInput('name'),'name').appendField(new Blockly.FieldNumber(0,0,null,0, function (value)
		{
			if (this.sourceBlock_)
			{
				this.sourceBlock_.update(dummy, value)
			}
		}), 'arguments')
		this.setInputsInline(true)
		this.setColour(200)
	},
	mutationToDom: function ()
	{
		var mutation = document.createElement('mutation')
		var arguments = this.getFieldValue('arguments')
		mutation.setAttribute('arguments', arguments)
		return mutation
	},
	domToMutation: function (xml)
	{
		var arguments = xml.getAttribute('arguments')
		var input
		for (var i=0; i<this.inputList.length;i++)
		{
			if (this.inputList[i].name === 'arguments')
			{
				input = this.inputList[i]
			}
		}
		this.update(input, arguments)
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
		for (var i=0; i < value; i++)
		{
			this.appendValueInput('arguments'+i)
			if (this.inputList.length-1 != index+1)
			{
				this.moveNumberedInputBefore(this.inputList.length-1, index+1)
			}
		}
	}
}
