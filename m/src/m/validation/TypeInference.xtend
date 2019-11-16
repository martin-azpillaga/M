package m.validation

import java.util.HashMap
import java.util.HashSet
import m.m.Access
import m.m.Call
import m.m.Command
import m.m.Decrement
import m.m.Divide
import m.m.Game
import m.m.Increment
import m.m.Loop
import m.m.Minus
import m.m.Modulus
import m.m.Plus
import m.m.Times
import m.m.Component
import m.m.Branch
import m.m.Condition
import m.m.Or
import m.m.And
import m.m.Not
import m.m.Comparison
import m.m.System
import static m.validation.Type.*
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.emf.ecore.EObject
import m.m.Assignment
import m.m.Brackets
import m.m.Expression
import org.eclipse.emf.common.util.EList

enum Type
{
	tag, float1,float2,float3,float4,
	sprite,material,mesh,audioClip,
	gameObject, input, entity
}

class TypeInference 
{
	var HashSet<HashSet<String>> groups = new HashSet<HashSet<String>>
	var HashMap<String,Type> solved = new HashMap<String,Type>
	
	def infer(Game game)
	{
		for (entity : game.entities)
		{
			for (component : entity.components)
			{
				component.infer
			}
		}
		for (system : game.systems)
		{
			for (command : system.commands)
			{
				infer(command)
			}
		}
		return solved
	}
	
	def void infer(Component component)
	{
		var name = component.name
		var value = component.values
		if (value.empty)
		{
			name.set(tag)
		}
		else if (Character.isDigit(value.head.charAt(0)))
		{
			if (value.size == 1)
			{
				name.set(float1)
			}
			else if (value.size == 2)
			{
				name.set(float2)
			}
			else if (value.size == 3)
			{
				name.set(float3)
			}
			else if (value.size == 4)
			{
				name.set(float4)
			}
		}
		else
		{
			if (name == 'mesh')
			{
				name.set(mesh)
			}
			else if (name == 'material')
			{
				name.set(material)
			}
			else if (name == 'audioClip')
			{
				name.set(audioClip)
			}
			else
			{
				if (inputNames.contains(value.join(' ')))
				{
					name.set(input)
				}
				else
				{
					name.set(gameObject)
				}
			}
		}
	}
	
	def inputNames()
	{
		return #['gamepad left Y', 'gamepad left X']
	}
	
	def void infer(Command command)
	{
		if (command instanceof Call)
		{
			if (#['sin','cos','tan','exp','log'].contains(command.name))
			{
				set(command.parameters.get(0), float1)				
			}
			else if (#['random'].contains(command.name))
			{
				set(command.parameters.get(0), float2)
			}
			else if (#['create'].contains(command.name))
			{
				set(command.parameters.get(0), gameObject)
			}
			else if (#['destroy'].contains(command.name))
			{
				set(command.parameters.get(0), entity)
			}
			else if (#['add','remove'].contains(command.name))
			{
				set(command.parameters.get(1), entity)
			}
		}
		else if (command instanceof Assignment)
		{
			var name = command.access.names.last
			if (command.access.names.size == 1)
			{
				var EObject container = command
				while (!(container instanceof Loop || container instanceof System))
				{
					container = EcoreUtil2.getContainerOfType(container, Loop)
				}
				name = command.access.names.head + "@" + container.hashCode
			}
			switch(command.type)
			{
				case AND: 
				{
					command.expression.set(float1)
				}
				case DECREASE: 
				{
					group(name, command.expression)
				}
				case DIVIDE: 
				{
					command.expression.set(float1)
				}
				case INCREASE: 
				{
					group(name, command.expression)
				}
				case MODULUS: 
				{
					command.expression.set(float1)
				}
				case MULTIPLY: 
				{
					command.expression.set(float1)
				}
				case OR: 
				{
					command.expression.set(float1)
				}
				case SET: 
				{
					group(name, command.expression)
				}
				case SHIFT_LEFT: 
				{
					
				}
				case SHIFT_RIGHT: 
				{
					
				}
				case XOR: 
				{
					
				}
			}
		}
		else if (command instanceof Loop)
		{
			for (constraint : command.constraints)
			{
				constraint.set(tag)
			}
			for (c : command.commands)
			{
				infer(c)
			}
		}
		else if (command instanceof Branch)
		{
			var i = command.^if
			i.condition.infer
			i.commands.forEach[infer]
			
			for (condition : command.elseIfs)
			{
				var c = condition as Condition
				c.condition.infer
				for (comm : c.commands)
				{
					comm.infer
				}
			}
			for (comm : command.commands)
			{
				comm.infer
			}
		}
	}
	
	def void group(String component, Expression expression)
	{
		if (solved.containsKey(component))
		{
			expression.set(solved.get(component))
		}
		else if (#['position','velocity','acceleration','force'].contains(component))
		{
			component.set(float3)
			expression.set(float3)
		}
		else if (#['mass','number'].contains(component))
		{
			component.set(float1)
			expression.set(float1)
		}
		else
		{
			if (expression instanceof Plus)
			{
				component.group(expression.left)
				component.group(expression.right)
			}
			else if (expression instanceof Minus)
			{
				component.group(expression.left)
				component.group(expression.right)
			}
			else if (expression instanceof Times)
			{
				component.group(expression.left)
				expression.right.set(float1)
			}
			else if (expression instanceof Divide)
			{
				component.group(expression.left)
				expression.right.set(float1)
			}
			else if (expression instanceof Modulus)
			{
				component.group(expression.left)
				expression.right.set(float1)
			}
			else if (expression instanceof Increment)
			{
				expression.expression.set(float1)
			}
			else if (expression instanceof Decrement)
			{
				expression.expression.set(float1)
			}
			else if (expression instanceof Brackets)
			{
				component.group(expression.expression)
			}
			else if (expression instanceof Access)
			{
				if (expression.names.size > 2)
				{
					for (var i = 1; i < expression.names.size - 1; i++)
					{
						expression.names.get(i).set(entity)
					}
					group(component, expression.names.last)
				}
				else if (expression.names.size == 2)
				{
					group(component, expression.names.last)
				}
				else
				{
					var EObject container = expression
					while (!(container instanceof Loop || container instanceof System))
					{
						container = EcoreUtil2.getContainerOfType(container, Loop)
					}
					var name = expression.names.head + "@" + container.hashCode
					group(component, name)
				}
			}
			else if (expression instanceof Call)
			{
				if (#['sin','cos','tan','exp','log'].contains(expression.name))
				{
					component.set(float1)
					expression.parameters.get(0).set(float1)
				}
				else if (#['random'].contains(expression.name))
				{
					component.set(float1)
					expression.parameters.get(0).set(float2)
				}
				else if (#['join'].contains(expression.name))
				{
					expression.parameters.forEach[set(float1)]
				}
			}
		}
	}
	
	def group(String component1, String component2)
	{
		if (#['velocity','position','force','acceleration','scale'].contains(component2))
		{
			set(component1, float3)
			set(component2, float3)
		}
		else if (#['mass','number'].contains(component2))
		{
			set(component1, float1)
			set(component2, float1)
		}
		else
		{
			var found = false
			for (group : groups)
			{
				if (group.contains(component1) && !group.contains(component2))
				{
					group.add(component2)
					found = true
				}
				else if (group.contains(component2) && !group.contains(component1))
				{
					group.add(component1)
					found = true
				}
			}
			if (!found)
			{
				var newGroup = new HashSet<String>
				newGroup.addAll(#[component1,component2])
				groups.add(newGroup)
			}
		}
	}
	
	def void infer(Expression expression)
	{
		if (expression instanceof Or)
		{
			expression.left.infer
			expression.right.infer
		}
		else if (expression instanceof And)
		{
			expression.left.infer
			expression.right.infer
		}
		else if (expression instanceof Not)
		{
			expression.expression.infer
		}
		else if (expression instanceof Comparison)
		{
			expression.left.set(float1)
			expression.right.set(float1)
		}
		else if (expression instanceof Plus)
		{
			
		}
	}
	
	def void set(Expression expression, Type value)
	{
		if (expression instanceof Plus)
		{
			expression.left.set(value)
			expression.right.set(value)
		}
		else if (expression instanceof Minus)
		{
			expression.left.set(value)
			expression.right.set(value)
		}
		else if (expression instanceof Times)
		{
			expression.left.set(value)
			expression.right.set(float1)
		}
		else if (expression instanceof Divide)
		{
			expression.left.set(value)
			expression.right.set(float1)
		}
		else if (expression instanceof Modulus)
		{
			expression.left.set(value)
			expression.right.set(float1)
		}
		else if (expression instanceof Increment)
		{
			expression.expression.set(float1)
		}
		else if (expression instanceof Decrement)
		{
			expression.expression.set(float1)
		}
		else if (expression instanceof Brackets)
		{
			expression.expression.set(value)
		}
		else if (expression instanceof Access)
		{
			if (expression.names.size > 2)
			{
				for (var i = 1; i < expression.names.size - 1; i++)
				{
					expression.names.get(i).set(entity)
				}
				set(expression.names.last,value)
			}
			else if (expression.names.size == 2)
			{
				set(expression.names.last,value)
			}
			else
			{
				var EObject container = expression
				while (!(container instanceof Loop || container instanceof System))
				{
					container = EcoreUtil2.getContainerOfType(container, Loop)
				}
				var name = expression.names.head + "@" + container.hashCode
				set(name, value)
			}
		}
		else if (expression instanceof Call)
		{
			if (#['sin','cos','tan','exp','log'].contains(expression.name))
			{
				expression.parameters.get(0).set(float1)
			}
			else if (#['random'].contains(expression.name))
			{
				expression.parameters.get(0).set(float2)
			}
			else if (#['join'].contains(expression.name))
			{
				expression.parameters.get(0).set(float1)
				expression.parameters.get(1).set(float1)
			}
		}
	}
	
	def set(String component, Type value)
	{
		if (!solved.containsKey(component))
		{
			for (group : groups)
			{
				if (group.contains(component))
				{
					for (c : group)
					{
						solved.put(c, value)
					}
					groups.remove(group)
				}
			}
			solved.put(component,value)
		}
		else
		{
			if (solved.get(component) != value)
			{
				println("Type error for " + component + " values " + value + " " + solved.get(component))
			}
		}
	}
}