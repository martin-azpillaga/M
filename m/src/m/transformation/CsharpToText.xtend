/*
package m.transformation

import m.csharp.File
import m.csharp.System
import m.text.TextFactory
import m.csharp.Command
import m.text.Control
import m.csharp.Break
import m.csharp.Initialization
import m.csharp.SubrutineCall
import m.csharp.ArithmeticExpression
import m.csharp.Plus
import m.csharp.Minus
import m.csharp.Times
import m.csharp.Divide
import m.csharp.Exponentiation
import m.csharp.Modulus
import m.csharp.Increment
import m.csharp.Decrement
import m.csharp.ArithmeticBrackets
import m.csharp.Access
import m.csharp.Call
import m.csharp.Pop
import m.csharp.BooleanExpression
import m.csharp.Or
import m.csharp.And
import m.csharp.Not
import m.csharp.BooleanBrackets
import m.csharp.Comparison
import m.text.RelationType
import m.csharp.BooleanVariable
import m.csharp.ComponentAssignment
import m.text.AssignmentType
import m.csharp.VariableAssignment
import m.csharp.CreatedAssignment
import m.csharp.Loop
import m.csharp.Check
import m.csharp.Condition
import m.csharp.While
import org.eclipse.xtext.EcoreUtil2

class CsharpToText
{
	TextFactory text = TextFactory.eINSTANCE
	
	def transform(File file)
	{
		if (file instanceof System)
		{
			var system = text.createSystem
			system.name = file.name
			
			for (command : file.commands)
			{
				add(command,system)
			}
			return system
		}
		return null
	}
	
	def void add(Command command, Control control)
	{
		if (command instanceof Break)
		{
			var break = text.createBreak
			control.commands.add(break)
		}
		else if (command instanceof Initialization)
		{
			var initialization = text.createInitialization
			initialization.variable = command.name
			control.commands.add(initialization)
		}
		else if (command instanceof SubrutineCall)
		{
			var subrutine = text.createSubrutineCall
			subrutine.subrutine = command.subrutine
			subrutine.parameters.addAll(command.parameters.map[toText])
		}
		else if (command instanceof ComponentAssignment)
		{
			var assignment = text.createComponentAssignment
			assignment.group = command.group
			assignment.component = command.component
			assignment.assignment = AssignmentType.SET
			assignment.expression = command.expression.toText
			control.commands.add(assignment)
		}
		else if (command instanceof VariableAssignment)
		{
			var variable = text.createVariableAssignment
			variable.variable = command.variable
			variable.assignment = AssignmentType.SET
			variable.expression = command.expression.toText
			control.commands.add(variable)
		}
		else if (command instanceof CreatedAssignment)
		{
			var created = text.createCreatedAssignment
			created.variable = command.variable
			created.assignment = AssignmentType.SET
			created.expression = command.expression.toText
			control.commands.add(created)
		}
		else if (command instanceof Check)
		{
			var branch = text.createCheck
			branch.condition = (command.condition as Condition).toText
			branch.conditions.addAll(command.conditions.map[(it as Condition).toText])
			for (c : command.commands)
			{
				add(c,branch)
			}
			control.commands.add(branch)
		}
		else if (command instanceof While)
		{
			var created = text.createWhile
			created.condition = command.condition.toText
			for (c : command.commands)
			{
				add(c,created)
			}
			control.commands.add(created)
		}
		else if (command instanceof Loop)
		{
			var system = EcoreUtil2.getContainerOfType(command, System)
			var getter = system.queryGetters.findFirst[it.name == command.chunksName]
			
			var types = getter.components.map[type]
			val sent = system.sendTypes.map[type]
			
			var tags = types.filter[!sent.contains(it)]
			
			var loop = text.createLoop
			loop.group = command.chunkName.substring(0,command.chunkName.indexOf('_'))
			for (tag : tags)
			{
				var constraint = text.createConstraint
				constraint.component = tag
				loop.constraints.add(constraint)
			}
			for (c : command.commands)
			{
				add(c, loop)
			}
			control.commands.add(loop)
		}
	}
	
	def toText(Condition condition)
	{
		var created = text.createCondition
		created.condition = condition.condition.toText
		for (c : condition.commands)
		{
			add(c, created)
		}
		return created
	}
	
	def m.text.BooleanExpression toText(BooleanExpression expression)
	{
		if (expression instanceof Or)
		{
			var or = text.createOr
			or.left = expression.left.toText
			or.right = expression.right.toText
			return or
		}
		else if (expression instanceof And)
		{
			var and = text.createAnd
			and.left = expression.left.toText
			and.right = expression.right.toText
			return and
		}
		else if (expression instanceof Not)
		{
			var not = text.createNot
			not.expression = expression.expression.toText
			return not
		}
		else if (expression instanceof BooleanBrackets)
		{
			var brackets = text.createBooleanBrackets
			brackets.expression = expression.expression.toText
			return brackets
		}
		else if (expression instanceof Comparison)
		{
			var comparison = text.createComparison
			comparison.left = expression.left.toText
			comparison.type = RelationType.EQUAL
			comparison.right = expression.right.toText
			return comparison
		}
		else if (expression instanceof BooleanVariable)
		{
			var variable = text.createBooleanVariable
			variable.variable = expression.variable
			return variable
		}
	}
	
	def m.text.ArithmeticExpression toText(ArithmeticExpression expression)
	{
		if (expression instanceof Plus)
		{
			var plus = text.createPlus
			plus.left = expression.left.toText
			plus.right = expression.right.toText
			return plus
		}
		else if (expression instanceof Minus)
		{
			var minus = text.createMinus
			minus.left = expression.left.toText
			minus.right = expression.right.toText
			return minus
		}
		else if (expression instanceof Times)
		{
			var times = text.createTimes
			times.left = expression.left.toText
			times.right = expression.right.toText
			return times
		}
		else if (expression instanceof Divide)
		{
			var divide = text.createDivide
			divide.left = expression.left.toText
			divide.right = expression.right.toText
			return divide
		}
		else if (expression instanceof Exponentiation)
		{
			var exp = text.createExponentiation
			exp.left = expression.left.toText
			exp.right = expression.right.toText
			return exp
		}
		else if (expression instanceof Modulus)
		{
			var modulus = text.createModulus
			modulus.left = expression.left.toText
			modulus.right = expression.right.toText
			return modulus
		}
		else if (expression instanceof Increment)
		{
			var increment = text.createIncrement
			increment.left = expression.left.toText
			return increment
		}
		else if (expression instanceof Decrement)
		{
			var decrement = text.createDecrement
			decrement.left = expression.left.toText
			return decrement
		}
		else if (expression instanceof ArithmeticBrackets)
		{
			var brackets = text.createArithmeticBrackets
			brackets.expression = expression.toText
			return brackets
		}
		else if (expression instanceof Access)
		{
			var access = text.createAccess
			access.group = expression.group
			access.component = expression.component
			access.range = expression.range
			access.vector = expression.vector
			return access
		}
		else if (expression instanceof Call)
		{
			var call = text.createCall
			call.function = expression.function
			call.parameters.addAll(expression.parameters.map[toText])
			return call
		}
		else if (expression instanceof Pop)
		{
			var pop = text.createPop
			pop.variable = expression.variable
			return pop
		}
	}
}
* */
