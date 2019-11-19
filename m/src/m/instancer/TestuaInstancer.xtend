package m.instancer

import java.util.ArrayList
import m.TestuaRuntimeModule
import m.structured.Access
import m.structured.And
import m.structured.Assignment
import m.structured.Bitwise
import m.structured.BitwiseNegate
import m.structured.Brackets
import m.structured.Selection
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
import m.structured.Times
import m.m.Loop
import m.m.MFactory
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.modeler.GenericSerializer.*
import m.structured.Statement
import m.m.Model

class TestuaInstancer 
{
	extension MFactory factory = MFactory.eINSTANCE
	
	def translate(Model model, IFileSystemAccess2 fsa, String name)
	{
		var translation = createModel
		
		for (entity : model.entities)
		{
			var translatedEntity = createEntity
			translatedEntity.name = entity.name
			
			for (component : entity.components)
			{
				var translatedComponent = createComponent
				translatedComponent.name = component.name.translate
				translatedEntity.components.add(translatedComponent)
				
				translatedComponent.values.addAll(component.values)
			}
			translation.entities.add(translatedEntity)
		}
		for (s : model.systems)
		{
			for (c : s.statements)
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
	
	def void translate(Statement c)
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
			for (comm : c.statements)
			{
				comm.translate
			}
		}
		else if (c instanceof Selection)
		{
			c.^if.condition.translate
			c.^if.statements.forEach[translate]
			c.elseIfs.forEach[it.condition.translate; it.statements.forEach[translate]]
			c.statements.forEach[translate]
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