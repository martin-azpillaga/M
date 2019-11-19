package m.instancer

import java.util.List
import m.m.Component
import m.m.Entity
import m.m.Game
import m.m.Loop
import m.m.System
import m.mxml.Element
import m.mxml.MxmlFactory
import m.structured.Access
import m.structured.And
import m.structured.Assignment
import m.structured.AssignmentKind
import m.structured.BitwiseNegate
import m.structured.Brackets
import m.structured.Call
import m.structured.Comparison
import m.structured.Decrement
import m.structured.Divide
import m.structured.Expression
import m.structured.Increment
import m.structured.Minus
import m.structured.Not
import m.structured.Or
import m.structured.Plus
import m.structured.RelationKind
import m.structured.Times
import m.yaml.Word
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.modeler.GenericSerializer.*
import m.XMLRuntimeModule
import m.structured.Statement
import m.structured.Selection

class BlocksInstancer
{
	extension MxmlFactory factory = MxmlFactory.eINSTANCE

	var x = -150
	
	def generate(Game game, IFileSystemAccess2 fsa, String... folders)
	{
		var file = createFile
		var xmlTag = createElement => 
		[
			open='xml' 
			close='xml' 
			attributes.add(createAttribute => [name='xmlns'; value='http://www.w3.org/1999/xhtml'])
		]
		file.elements.add(xmlTag)
		
		for (entity : game.entities)
		{
			xmlTag.elements.add(entity.compile)
			x -= 300
		}
		x=150
		for (system : game.systems)
		{
			xmlTag.elements.add(system.compile)
			x+=400
		}
		
		for (folder : folders)
		{
			generate(file, new XMLRuntimeModule, fsa, folder+'main-gen.blocks')
		}
	}
	
	def compile(Entity entity)
	{
		var block = block('entity', entity.hashCode.toString, x.toString, "0")
		block.elements.add(field('name', entity.name))
		var components = statement('components')
		block.elements.add(components)
		components.elements.add(entity.components.compile(0))
		return block
	}
	
	def Element compile(List<Component> components, int index)
	{
		val current = components.get(index)
		var block = block(current.type, current.hashCode.toString)
		block.elements.add(field('name', current.name))
		
		var value = current.values
		var names = #['x','y','z','w']
		var valueValue = value('value')
		block.elements.add(valueValue)
		if (Character.isDigit(value.head.charAt(0)))
		{
			var valueBlock = block(value.componentType, value.hashCode.toString)
			valueValue.elements.add(valueBlock)
			
			for (var n = 0; n < value.size; n++)
			{
				var number = value.get(n)
				valueBlock.elements.add(field(names.get(n), number.toString))
			}
		}
		else if (value instanceof Word)
		{
			var valueBlock = block('wordvalue', value.hashCode.toString)
			valueValue.elements.add(valueBlock)
			valueBlock.elements.add(field('name', value.join(' ')))
		}
		if (components.size > index+1)
		{
			var next = createElement=>[open='next' close='next']
			next.elements.add(components.compile(index+1))
			block.elements.add(next)
		}
		return block
	}
	
	def componentType(List<String> value)
	{
		if (value.size == 1) 'real1value'
		else if (value.size == 2) 'real2value'
		else if (value.size == 3) 'real3value'
		else if (value.size == 4) 'real4value'
		else 'error'
	}
	
	def type(Component component)
	{
		var value = component.values
		if (value === null)
		{
			return 'tagcomponent'
		}
		else
		{
			return 'valuedcomponent'
		}
	}
	
	def field(String myName, String myText)
	{
		return createElement =>
		[
			open = 'field'
			close = 'field'
			attributes.add(createAttribute => [name='name' value=myName])
			text = myText
		]
	}
	def block(String myType, String myId)
	{
		return createElement =>
		[
			open = 'block'
			close = 'block'
			attributes.addAll
			(#[
				createAttribute => [name='type' value=myType],
				createAttribute => [name='id' value=myId]
			])
		]
	}
	def block(String myType, String myId, String myX, String myY)
	{
		var block = block(myType, myId)
		block.attributes.add(createAttribute=>[name='x' value=myX])
		block.attributes.add(createAttribute=>[name='y' value=myY])
		return block
	}
	def value(String myName)
	{
		return createElement =>
		[
			open = 'value'
			close = 'value'
			attributes.add(createAttribute => [name='name' value=myName])
		]
	}
	
	def statement(String myName)
	{
		return createElement =>
		[
			open = 'statement'
			close = 'statement'
			attributes.add(createAttribute=>[name='name' value=myName])
		]
	}
	
	def compile(System system)
	{
		var block = block('system', system.hashCode.toString, x.toString, "0")
		block.elements.add(field('name', system.name))
		var commands = statement('commands')
		block.elements.add(commands)
		commands.elements.add(system.statements.compileCommands(0))
		return block
	}
	
	def Element compileCommands(List<Statement> commands, int index)
	{
		val current = commands.get(index)
		var Element block
		if (current instanceof Loop)
		{
			block = block('loop', current.hashCode.toString)
			block.elements.add(field('entity', current.entity))
			var comm = value('commands')
			comm.elements.add(current.statements.compileCommands(0))
			block.elements.add(comm)
		}
		else if (current instanceof Selection)
		{
			block = block('branch', current.hashCode.toString)
			var expression = value('expression')
			expression.elements.add(current.^if.condition.toElement)
			block.elements.add(expression)
			var comm = value('commands')
			comm.elements.add(current.^if.statements.compileCommands(0))
			block.elements.add(comm)
		}
		else if (current instanceof Assignment)
		{
			block = block('assignment', current.hashCode.toString)
			block.elements.add(field('type', current.type.toBlocks))
			var access = value('access')
			access.elements.add(current.access.toElement)
			block.elements.add(access)
			var expression = value('expression')
			expression.elements.add(current.expression.toElement)
			block.elements.add(expression)
		}
		else if (current instanceof Call)
		{
			block = block('converttocommand',current.hashCode.toString)
			var value = value('value')
			block.elements.add(value)
			value.elements.add(current.toElement)
		}
		if (commands.size > index+1)
		{
			var next = createElement=>[open='next' close='next']
			next.elements.add(commands.compileCommands(index+1))
			block.elements.add(next)
		}
		return block
	}
	
	def Element toElement(Expression expression)
	{
		if (expression instanceof Plus)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'plus'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Minus)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'minus'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Times)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'times'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Divide)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'divide'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof And)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'and'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Or)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'or'))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Comparison)
		{
			var block = block('binaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', expression.type.toBlocks))
			var left = value('left')
			left.elements.add(expression.left.toElement)
			var right = value('right')
			right.elements.add(expression.right.toElement)
			
			block.elements.add(left)
			block.elements.add(right)
			return block
		}
		else if (expression instanceof Not)
		{
			var block = block('unaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'not'))
			var v = value('expression')
			v.elements.add(expression.expression.toElement)
			block.elements.add(v)
			return block
		}
		else if (expression instanceof BitwiseNegate)
		{
			var block = block('unaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'not'))
			var v = value('expression')
			v.elements.add(expression.expression.toElement)
			block.elements.add(v)
			return block
		}
		else if (expression instanceof Increment)
		{
			var block = block('unaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'increment'))
			var v = value('expression')
			v.elements.add(expression.expression.toElement)
			block.elements.add(v)
			return block
		}
		else if (expression instanceof Decrement)
		{
			var block = block('unaryexpression', expression.hashCode.toString)
			block.elements.add(field('operation', 'decrement'))
			var v = value('expression')
			v.elements.add(expression.expression.toElement)
			block.elements.add(v)
			return block
		}
		else if (expression instanceof Brackets)
		{
			var block = block('brackets', expression.hashCode.toString)
			var v = value('expression')
			v.elements.add(expression.expression.toElement)
			block.elements.add(v)
			return block
		}
		else if (expression instanceof Access)
		{
			var names = expression.names
			if (names.size == 1)
			{
				var block = block('access1', expression.hashCode.toString)
				block.elements.add(field('a',names.get(0)))
				return block
			}
			else if (names.size == 2)
			{
				var block = block('access2', expression.hashCode.toString)
				block.elements.add(field('a',names.get(0)))
				block.elements.add(field('b',names.get(1)))
				return block
			}
			else if (names.size == 3)
			{
				var block = block('access3', expression.hashCode.toString)
				block.elements.add(field('a',names.get(0)))
				block.elements.add(field('b',names.get(1)))
				block.elements.add(field('c',names.get(2)))
				return block
			}
		}
		else if (expression instanceof Call)
		{
			var name = expression.name
			if (name == 'add')
			{
				var block = block('add', expression.hashCode.toString)
				var component = value('component')
				component.elements.add(expression.parameters.get(0).toElement)
				var entity = value('entity')
				entity.elements.add(expression.parameters.get(1).toElement)
				block.elements.add(component)
				block.elements.add(entity)
				return block
			}
			else if (name == 'remove')
			{
				var block = block('remove', expression.hashCode.toString)
				var component = value('component')
				component.elements.add(expression.parameters.get(0).toElement)
				var entity = value('entity')
				entity.elements.add(expression.parameters.get(1).toElement)
				block.elements.add(component)
				block.elements.add(entity)
				return block
			}
			else if (name == 'create')
			{
				var block = block('create', expression.hashCode.toString)
				var entity = value('expression')
				entity.elements.add(expression.parameters.get(0).toElement)
				block.elements.add(entity)
				return block
			}
			else if (name == 'destroy')
			{
				var block = block('destroy', expression.hashCode.toString)
				var entity = value('expression')
				entity.elements.add(expression.parameters.get(0).toElement)
				block.elements.add(entity)
				return block
			}
			else if (name == 'join')
			{
				var parameters = expression.parameters
				if (parameters.size == 2)
				{
					var block = block('join2', expression.hashCode.toString)
					var component = value('x')
					component.elements.add(expression.parameters.get(0).toElement)
					var entity = value('y')
					entity.elements.add(expression.parameters.get(1).toElement)
					block.elements.add(component)
					block.elements.add(entity)
					return block
				}
				else if (parameters.size == 3)
				{
					var block = block('join2', expression.hashCode.toString)
					var component = value('x')
					component.elements.add(expression.parameters.get(0).toElement)
					var entity = value('y')
					entity.elements.add(expression.parameters.get(1).toElement)
					var z = value('z')
					z.elements.add(expression.parameters.get(2).toElement)
					block.elements.add(component)
					block.elements.add(entity)
					block.elements.add(z)
					return block
				}
				else if (parameters.size == 4)
				{
					var block = block('join2', expression.hashCode.toString)
					var component = value('x')
					component.elements.add(expression.parameters.get(0).toElement)
					var entity = value('y')
					entity.elements.add(expression.parameters.get(1).toElement)
					var z = value('z')
					z.elements.add(expression.parameters.get(2).toElement)
					var w = value('w')
					w.elements.add(expression.parameters.get(3).toElement)
					block.elements.add(component)
					block.elements.add(entity)
					block.elements.add(z)
					block.elements.add(w)
					return block
				}
			}
			else if (name == 'random')
			{
				var block = block('random', expression.hashCode.toString)
				var entity = value('expression')
				entity.elements.add(expression.parameters.get(0).toElement)
				block.elements.add(entity)
				return block
			}
			else if (name == 'has')
			{
				var block = block('has', expression.hashCode.toString)
				var component = value('component')
				component.elements.add(expression.parameters.get(1).toElement)
				var entity = value('entity')
				entity.elements.add(expression.parameters.get(0).toElement)
				block.elements.add(entity)
				block.elements.add(component)
				
				return block
			}
			else
			{
				var block = block('mathfunction', expression.hashCode.toString)
				var entity = value('expression')
				block.elements.add(field('name', name))
				entity.elements.add(expression.parameters.get(0).toElement)
				block.elements.add(entity)
				return block
			}
		}
	}
	
	def toBlocks(RelationKind type)
	{
		switch type
		{
			case RelationKind.UNDER: 'less'
			case RelationKind.UNDEROREQUAL: 'lessOrEqual'
			case RelationKind.EQUAL: 'equal'
			case RelationKind.NOTEQUAL: 'notEqual'
			case RelationKind.OVEROREQUAL: 'greaterOrEqual'
			case RelationKind.OVER: 'greater'
		}
	}
	
	def toBlocks(AssignmentKind type)
	{
		switch type
		{
			case SET: 'set'
			case INCREASE: 'increase'
			case DECREASE: 'decrease'
			case AND: 'and'
			case DIVIDE: 'divide'
			case MODULUS: 'modulus'
			case MULTIPLY: 'multiply'
			case OR: 'or'
			case SHIFT_LEFT: 'shiftLeft'
			case SHIFT_RIGHT: 'shiftRight'
			case XOR: 'xor'
		}
	}
}