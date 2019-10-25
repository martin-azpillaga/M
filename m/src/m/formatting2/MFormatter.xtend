package m.formatting2

import m.m.Entity
import m.m.Game
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.m.Vector
import m.m.Component
import com.google.inject.Inject
import m.m.Word
import m.m.Loop
import m.m.Branch
import m.m.Condition
import m.m.Assignment
import m.m.Call
import m.m.Access
import m.m.Comparison
import m.services.MGrammarAccess

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
		component.value?.format
	}
	
	def dispatch format(Vector vector, extension IFormattableDocument document)
	{
		vector.regionFor.assignments(vectorAccess.numbersAssignment).forEach[prepend[oneSpace]]
	}
	
	def dispatch format(Word word, extension IFormattableDocument document)
	{
		word.regionFor.assignments(wordAccess.valuesAssignment).forEach[prepend[oneSpace]]
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
}