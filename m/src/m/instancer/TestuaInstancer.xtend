package m.instancer


import static extension m.modeler.GenericSerializer.*
import m.TestuaRuntimeModule
import m.m.Loop
import m.m.Branch
import m.m.Assignment
import m.m.Call
import java.util.ArrayList
import m.m.Command
import m.m.Expression
import m.m.And
import m.m.Or
import m.m.Not
import m.m.Comparison
import m.m.Plus
import m.m.Minus
import m.m.Times
import m.m.Divide
import m.m.Bitwise
import m.m.BitwiseNegate
import m.m.Increment
import m.m.Decrement
import m.m.Brackets
import m.m.Access
import m.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import m.m.MFactory
import m.m.Component
import m.m.Vector
import m.m.Word

class TranslateTestua 
{
	extension MFactory factory = MFactory.eINSTANCE
	
	def translate(Game game, IFileSystemAccess2 fsa, String name)
	{
		var translation = createGame
		
		for (entity : game.entities)
		{
			var translatedEntity = createEntity
			translatedEntity.name.addAll(entity.name)
			
			for (component : entity.components)
			{
				var translatedComponent = createComponent
				translatedComponent.name = component.name.translate
				translatedEntity.components.add(translatedComponent)
				
				var value = component.value
				if (value instanceof Vector)
				{
					var translatedVector = createVector
					translatedVector.numbers.addAll(value.numbers)
					translatedComponent.value = translatedVector
				}
				else if (value instanceof Word)
				{
					var translatedWord = createWord
					translatedWord.values.addAll(value.values)
					translatedComponent.value = translatedWord
				}
			}
			translation.entities.add(translatedEntity)
		}
		for (s : game.systems)
		{
			for (c : s.commands)
			{
				c.translate
			}
		}
		generate(translation, new TestuaRuntimeModule, fsa, 'Testua/'+name)
	}
	def translate(String string)
	{
		switch string
		{
			case 'position': 'kokapena'
			case 'rotation': 'biraketa'
			case 'scale': 'eskala'
			case 'mesh': 'malla'
			case 'material': 'materiala'
			case 'mass': 'masa'
			case 'radius': 'erradioa'
			case 'restitution': 'errestituzioa'
			case 'extent': 'neurriak'
			case 'triggerZone': 'aktibazioLekua'
			
			
			case 'add': 'gehitu'
			case 'remove': 'kendu'
			case 'create': 'sortu'
			case 'destroy': 'desegin'
			case 'join': 'lotu'
			case 'random': 'sortez'
			case 'has': 'badauka'
			
			default: string
		}
	}
	
	def void translate(Command c)
	{
		if (c instanceof Loop)
		{
			var result = new ArrayList<String>
			for (constraint : c.constraints)
			{
				result.add(constraint.rename)
			}
			c.constraints.clear
			c.constraints.addAll(result)
			for (comm : c.commands)
			{
				comm.translate
			}
		}
		else if (c instanceof Branch)
		{
			c.^if.condition.translate
			c.^if.commands.forEach[translate]
			c.elseIfs.forEach[it.condition.translate; it.commands.forEach[translate]]
			c.commands.forEach[translate]
		}
		else if (c instanceof Assignment)
		{
			c.access.translate
			c.expression.translate
		}
		else if (c instanceof Call)
		{
			c.name = c.name.translate
			c.parameters.forEach[translate]
		}
	}
	
	def rename(String string)
	{
		if (string.endsWith('Triggered'))
		{
			return string.replace('Triggered', 'Eraginda')
		}
		else if (string.endsWith('Range'))
		{
			return string.replace('Range', 'Rangoa')
		}
		else if (string.endsWith('Vector'))
		{
			return string.replace('Vector', 'Bektorea')
		}
		return string
	}
	
	def void translate(Expression expression)
	{
		if (expression instanceof And)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Or)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Not)
		{
			expression.expression.translate
		}
		else if (expression instanceof Comparison)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Plus)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Minus)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Times)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Divide)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof Bitwise)
		{
			expression.left.translate
			expression.right.translate
		}
		else if (expression instanceof BitwiseNegate)
		{
			expression.expression.translate
		}
		else if (expression instanceof Increment)
		{
			expression.expression.translate
		}
		else if (expression instanceof Decrement)
		{
			expression.expression.translate
		}
		else if (expression instanceof Access)
		{
			var result = new ArrayList<String>
			for ( n : expression.names )
			{
				result.add(n.translate)
			}
			expression.names.clear
			expression.names.addAll(result)
		}
		else if (expression instanceof Call)
		{
			expression.name = expression.name.translate
			expression.parameters.forEach[translate]
		}
		else if (expression instanceof Brackets)
		{
			expression.expression.translate
		}
	}
	
}