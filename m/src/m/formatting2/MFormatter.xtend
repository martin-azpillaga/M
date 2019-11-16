package m.formatting2

import m.m.Entity
import m.m.Game
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.m.Component
import com.google.inject.Inject
import m.m.Loop
import m.m.Branch
import m.m.Condition
import m.m.Assignment
import m.m.Call
import m.m.Access
import m.m.Comparison
import m.services.MGrammarAccess
import m.m.And
import m.m.Or
import m.m.Not
import m.m.Plus
import m.m.Minus
import m.m.Times
import m.m.Divide
import m.m.Bitwise
import m.m.BitwiseNegate
import m.m.Increment
import m.m.Decrement
import m.m.Brackets

class MFormatter extends AbstractFormatter2 
{
	@Inject
	extension MGrammarAccess access
	
	def dispatch void format(Game game, extension IFormattableDocument document) 
	{
		for (entity : game.entities) 
		{
			entity.append[newLines = 2]
			entity.format
		}
		for (system : game.systems) 
		{
			system.append[newLines = 2]
			system.format
		}
	}

	def dispatch void format(Entity entity, extension IFormattableDocument document) 
	{
		val open = entity.regionFor.keyword('{')
		val close = entity.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		entity.regionFor.keywords(',').forEach[prepend[noSpace].append[autowrap(8)]]
		
		for (component : entity.components) 
		{
			component.format
		}
	}
	
	def dispatch void format(Component component, extension IFormattableDocument document)
	{
		component.regionFor.assignments(componentAccess.valuesAssignment_1_0).forEach[prepend[oneSpace]]
		component.regionFor.assignments(componentAccess.valuesAssignment_1_1).forEach[prepend[oneSpace]]
	}
	
	def dispatch format(m.m.System system, extension IFormattableDocument document)
	{
		system.regionFor.keyword(':').prepend[noSpace]
		system.commands.forEach[format]
	}
	
	def dispatch format(Loop loop, extension IFormattableDocument document)
	{
		loop.prepend[newLine]
		
		val open = loop.regionFor.keyword('{')
		val close = loop.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		loop.commands.forEach[format]
	}
	
	def dispatch format(Branch branch, extension IFormattableDocument document)
	{
		branch.prepend[newLine]
		
		branch.^if.format
		branch.elseIfs.forEach[format]
		
		val open = branch.regionFor.keyword('{')
		val close = branch.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		branch.commands.forEach[format]
	}
	
	def dispatch format(Condition condition, extension IFormattableDocument document)
	{
		condition.condition.format
		val open = condition.regionFor.keyword('{')
		val close = condition.regionFor.keyword('}')
		open.surround[newLine]
		interior(open,close)[indent]
		close.prepend[newLine]
		open.prepend[newLine]
		close.prepend[newLine]
		
		condition.commands.forEach[format]
	}
	
	def dispatch format(Assignment assignment, extension IFormattableDocument document)
	{
		assignment.prepend[newLine]
		assignment.access.format
		assignment.regionFor.assignment(assignmentAccess.typeAssignment_1).surround[oneSpace]
		assignment.expression.format
	}
	
	def dispatch format(Call call, extension IFormattableDocument document)
	{
		var container = call.eContainer
		if (container instanceof Loop)
		{
			call.prepend[newLine]
		}
		else if (container instanceof Condition)
		{
			if (call != container.condition)
			{
				call.prepend[newLine]
			}
		}
		else if (container instanceof Branch)
		{
			call.prepend[newLine]
		}
		call.regionFor.keyword('(').surround[noSpace]
		call.regionFor.keyword(')').prepend[noSpace]
		
		call.regionFor.keyword(',').prepend[noSpace].append[oneSpace]
		
		call.parameters.forEach[format]
	}
	
	def dispatch format(Access access, extension IFormattableDocument document)
	{
		access.regionFor.keywords('.').forEach[surround[noSpace]]
	}
	
	def dispatch format(And and, extension IFormattableDocument document)
	{
		and.regionFor.keyword('&&').surround[oneSpace]
		and.left.format
		and.right.format
	}
	
	def dispatch format(Or or, extension IFormattableDocument document)
	{
		or.regionFor.keyword('||').surround[oneSpace]
		or.left.format
		or.right.format
	}
	
	def dispatch format(Not not, extension IFormattableDocument document)
	{
		not.regionFor.keyword('!').append[oneSpace]
		not.expression.format
	}
	
	def dispatch format(Comparison comparison, extension IFormattableDocument document)
	{
		comparison.regionFor.assignment(atomicBAccess.typeAssignment_1_1).surround[oneSpace]
		comparison.left.format
		comparison.right.format
	}
	
	def dispatch format(Plus plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('+').surround[oneSpace]
		plus.left.format
		plus.right.format
	}
	
	def dispatch format(Minus plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('-').surround[oneSpace]
		plus.left.format
		plus.right.format
	}
	
	def dispatch format(Times plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('*').surround[oneSpace]
		plus.left.format
		plus.right.format
	}
	
	def dispatch format(Divide plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('/').surround[oneSpace]
		plus.left.format
		plus.right.format
	}
	
	def dispatch format(Bitwise plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('&').surround[oneSpace]
		plus.regionFor.keyword('|').surround[oneSpace]
		plus.regionFor.keyword('^').surround[oneSpace]
		plus.regionFor.keyword('<<').surround[oneSpace]
		plus.regionFor.keyword('>>').surround[oneSpace]
		plus.left.format
		plus.right.format
	}
	
	def dispatch format(BitwiseNegate plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('~').append[oneSpace]
		plus.expression.format
	}
	
	def dispatch format(Increment plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('++').prepend[noSpace]
		plus.expression.format
	}
	
	def dispatch format(Decrement plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('~').prepend[noSpace]
		plus.expression.format
	}
	
	def dispatch format(Brackets plus, extension IFormattableDocument document)
	{
		plus.regionFor.keyword('(').append[noSpace]
		plus.regionFor.keyword(')').prepend[noSpace]
		plus.expression.format
	}
}