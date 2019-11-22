package m.formatting2

import com.sun.org.apache.xpath.internal.operations.And
import com.sun.org.apache.xpath.internal.operations.Minus
import com.sun.org.apache.xpath.internal.operations.Or
import com.sun.org.apache.xpath.internal.operations.Plus
import m.csharp.Access
import m.m.Loop
import m.modular.Assignment
import m.modular.Call
import m.modular.Selection
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class ModularFormatter extends AbstractFormatter2 
{	
	def dispatch format(Selection branch, extension IFormattableDocument document)
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
		
		branch.statements.forEach[format]
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
		
		condition.statements.forEach[format]
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
		else if (container instanceof Selection)
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
