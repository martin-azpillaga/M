package m.build

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import m.m.MPackage
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference

import static java.nio.file.Files.*
import static java.nio.file.Paths.*
import static m.build.FeatureKind.*

class Category
{
	public String name;
	public int color;
	public List<Block> blocks = new ArrayList<Block>();
}
class Block
{
	public EClass type
	public String name
	public List<EClass> topConnection = new ArrayList<EClass>
	public List<EClass> bottomConnection = new ArrayList<EClass>
	public List<EClass> leftConnection = new ArrayList<EClass>
	public List<Input> inputs = new ArrayList<Input>()
}
		
class Input
{
	public FeatureKind kind;
	public String name;
	public List<EClass> types = new ArrayList<EClass>();
	public List<String> fields = new ArrayList<String>();
}
enum FeatureKind
{
	single, multiple, mutation, dummy
}
class Blockify
{
	static var categories = new ArrayList<Category>
	static var left = new HashMap<EClass,Boolean>
	
	def static void main(String[] args)
	{
		categories.add(new Category=>[name="Root"])
		
		var model = MPackage.eINSTANCE;
		
		for (eclass : model.EClassifiers.filter(EClass))
		{
			if (eclass.EStructuralFeatures.filter(EReference).exists[containment])
			{
				eclass.analyze
			}
			else if (eclass.EAllSuperTypes.exists[name.equals("Expression")])
			{
				eclass.analyze
			}
		}
		
		for (var i = 0; i < categories.size; i++)
		{
			var category = categories.get(i)
			category.color = 100+50*i
			
			for (var b = 0; b < category.blocks.size; b++)
			{
				val block = category.blocks.get(b)
				var superTypes = new ArrayList<EClass>(block.type.EAllSuperTypes)
				superTypes.add(block.type)
				var subTypes = block.type.EPackage.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(block.type)].toList
				subTypes.add(block.type)
				
				if (superTypes.exists[left.containsKey(it) && left.get(it)])
				{
					block.leftConnection.addAll(block.type)
				}
				else if (superTypes.exists[left.containsKey(it)])
				{
					block.topConnection.addAll(block.type)
					if (block.type.ESuperTypes.empty)
					{
						block.bottomConnection.add(block.type)
					}
					else
					{
						val superType = block.type.ESuperTypes.head
						var superSub = model.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(superType)].toList
						superSub.add(block.type)
						block.bottomConnection.addAll(superSub)
					}
				}
				
				for (input : block.inputs)
				{
					if (input.kind == multiple)
					{
						var left = input.types.exists[left.containsKey(it) && left.get(it)]
						if (left)
						{
							input.kind = mutation
						}
					}
				}
			}
		}
		
		write(get("../../Application/Blockly/custom2.js"), categories.print.toString.bytes);
	}
	
	private def static analyze(EClass eclass)
	{
		var block = new Block=>[name=eclass.name type=eclass]
		
		var fields = new ArrayList<String>
		for (feature : eclass.EStructuralFeatures)
		{
			if (feature instanceof EAttribute)
			{
				fields.add(feature.name)
			}
			else if (feature instanceof EReference)
			{
				if (feature.containment)
				{
					val type = feature.EType as EClass
					var subTypes = eclass.EPackage.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(type)].toList
					subTypes.add(type)
					
					var Input input = new Input=>[name=feature.name]
					if (feature.upperBound == 1)
					{
						input.kind = single
						for (subType : subTypes)
						{
							left.put(subType, true)
						}
					}
					else
					{
						input.kind = multiple
						for (subType : subTypes)
						{
							if (!left.containsKey(subType))
							{
								left.put(subType, false)
							}
						}
						
					}
					input.types.addAll(subTypes)
					input.fields.addAll(fields)
					block.inputs.add(input)
					fields = new ArrayList<String>
				}
			}
		}
		
		if (!fields.empty)
		{
			var input = new Input=>[kind=dummy]
			input.fields.addAll(fields)
			block.inputs.add(input)
		}
		
		var superTypes = eclass.ESuperTypes
		if (superTypes.empty)
		{
			categories.findFirst[name=="Root"].blocks.add(block)
		}
		else if (superTypes.size == 1)
		{
			val superType = superTypes.head.name
			var category = categories.findFirst[name==superType]
			if (category === null)
			{
				category = new Category=>[name=superType]
				categories.add(category)
			}
			category.blocks.add(block)
		}
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
		«ENDFOR»
		`
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
				this.appendValueInput('«input.name»').setCheck(«input.types.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == multiple»
				this.appendStatementInput('«input.name»').setCheck(«input.types.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == mutation»
				var dummy = this.appendDummyInput('«input.name»')
				dummy«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR».appendField(new Blockly.FieldNumber(0,0,null,0, function (value)
				{
					this.sourceBlock_?.update(dummy, value)
				}), '«input.name»')
				«ELSEIF input.kind == dummy»
				this.appendDummyInput('«input.name»')«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
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
					if (this.inputList.length-1 != index+1)
					{
						this.moveNumberedInputBefore(this.inputList.length-1, index+1)
					}
				}
				«ENDFOR»
			}
			«ENDIF»
		}
		«ENDFOR»
		«ENDFOR»
		'''
	}
	
	private static def s(List<EClass> list)
	{
		if (list.empty)
		{
			return null
		}
		else if (list.size == 1)
		{
			return "'"+list.head.name+"'"
		}
		else
		{
			return "["+list.map["'"+it.name+"'"].join(",")+"]"
		}
	}
}