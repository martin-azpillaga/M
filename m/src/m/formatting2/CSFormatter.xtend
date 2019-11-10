package m.formatting2

import m.cs.Annotation
import m.cs.Argument
import m.cs.Assignment
import m.cs.Cast
import m.cs.Field
import m.cs.File
import m.cs.Increment
import m.cs.Member
import m.cs.Method
import m.cs.QualifiedName
import m.cs.Return
import m.cs.Type
import m.cs.TypeName
import m.cs.Using
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import m.cs.Decrement
import m.cs.Access
import m.cs.MethodCall
import m.cs.Initialization
import m.cs.Indexing
import m.cs.Lambda
import m.cs.InitializeVariable
import m.cs.If

class CSFormatter extends AbstractFormatter2 
{
	def dispatch void format(File file, extension IFormattableDocument document) 
	{
		for (using : file.usings) 
		{
			if (using !== file.usings.head)
			{
				using.prepend[newLine]
			}
			using.format
		}
		for (type : file.types) 
		{
			type.format
		}
	}

	def dispatch void format(Using using, extension IFormattableDocument document) 
	{
		using.regionFor.keyword(';').surround[noSpace]	
		using.name.format
	}
	
	def dispatch void format(QualifiedName qualifiedName, extension IFormattableDocument document)
	{
		qualifiedName.regionFor.keywords('.').forEach[surround[noSpace]]
	}
	
	def dispatch void format(Member member, extension IFormattableDocument document)
	{
		member.annotations.forEach[format]
		member.annotations.forEach[append[newLine]]
		
		var container = member.eContainer
		
		if (member.value instanceof Field)
		{
			member.prepend[newLine]
		}
		else if (container instanceof Type)
		{
			if (member == container.members.head)
			{
				member.prepend[newLine]
			}
			else
			{
				member.prepend[newLines=2]
			}
		}
		else
		{
			member.prepend[newLines=2]
		}
		member.value.format
	}
	
	def dispatch void format(Annotation annotation, extension IFormattableDocument document)
	{
		annotation.regionFor.keyword('[').append[noSpace]
		annotation.regionFor.keyword(']').prepend[noSpace]
		annotation.function.format
		annotation.parameters.forEach[format]
	}
	
	def dispatch void format(Type type, extension IFormattableDocument document)
	{
		type.regionFor.keywords(',').forEach[prepend[noSpace]]
		type.superTypes.forEach[format]
		var open = type.regionFor.keyword('{')
		var close = type.regionFor.keyword('}')
		interior(open,close)[indent]
		open.prepend[newLine]
		close.prepend[newLine]
		type.members.forEach[format]
	}
	
	def dispatch void format(TypeName typeName, extension IFormattableDocument document)
	{
		typeName.name.format
		typeName.generics.forEach[format]
		typeName.regionFor.keywords(',').forEach[prepend[noSpace]]
		typeName.regionFor.keyword('<').surround[noSpace]
		typeName.regionFor.keyword('>').prepend[noSpace]
	}
	
	def dispatch void format(Method method, extension IFormattableDocument document)
	{
		method.regionFor.keywords(',').forEach[prepend[noSpace]]
		method.arguments.head?.prepend[noSpace]
		method.regionFor.keyword('(').append[noSpace]
		method.regionFor.keyword(')').prepend[noSpace]
		var open = method.regionFor.keyword('{')
		var close = method.regionFor.keyword('}')
		interior(open,close)[indent]
		open.prepend[newLine]
		close.prepend[newLine]
		method.commands.forEach[format]
	}
	
	def dispatch void format(Argument argument, extension IFormattableDocument document)
	{

	}
	
	def dispatch void format(If branch, extension IFormattableDocument document)
	{
		branch.prepend[newLine]
		branch.expression.format
		var open = branch.regionFor.keyword('{')
		var close = branch.regionFor.keyword('}')
		interior(open,close)[indent]
		open.prepend[newLine]
		close.prepend[newLine]
		branch.commands.forEach[format]
	}
	
	def dispatch void format(Return r, extension IFormattableDocument document)
	{
		r.prepend[newLine]
		r.regionFor.keyword(';').prepend[noSpace]
		r.expression.format
	}
	
	def dispatch void format(Assignment assignment, extension IFormattableDocument document)
	{
		assignment.prepend[newLine]
		assignment.regionFor.keyword(';').prepend[noSpace]
		assignment.type?.format
		assignment.access.format
		assignment.expression.format
		assignment.regionFor.keyword('++').prepend[noSpace]
		assignment.regionFor.keyword('--').prepend[noSpace]
	}
	
	def dispatch void format(Field field, extension IFormattableDocument document)
	{
		field.type.format
		field.regionFor.keyword(';').prepend[noSpace]
	}
	
	def dispatch void format(Cast cast, extension IFormattableDocument document)
	{
		cast.regionFor.keyword('(').append[noSpace]
		cast.regionFor.keyword(')').prepend[noSpace]
		cast.expression.format
	}
	
	def dispatch void format(Lambda lambda, extension IFormattableDocument document)
	{
		lambda.regionFor.keywords(',').forEach[prepend[noSpace]]
		var open = lambda.regionFor.keyword('{')
		var close = lambda.regionFor.keyword('}')
		interior(open,close)[indent]
		open.prepend[newLine]
		close.prepend[newLine]
		lambda.commands.forEach[format]
	}
	
	def dispatch void format(Increment increment, extension IFormattableDocument document)
	{
		increment.regionFor.keyword('++').prepend[noSpace]
		increment.left.format
	}
	
	def dispatch void format(Decrement decrement, extension IFormattableDocument document)
	{
		decrement.regionFor.keyword('--').prepend[noSpace]
		decrement.left.format
	}
	
	def dispatch void format(Access access, extension IFormattableDocument document)
	{
		access.regionFor.keywords('.').forEach[surround[noSpace]]
		access.portions.forEach[format]
	}
	
	def dispatch void format(MethodCall methodCall, extension IFormattableDocument document)
	{
		methodCall.regionFor.keyword('<').surround[noSpace]
		methodCall.regionFor.keyword('>').prepend[noSpace]
		methodCall.regionFor.keyword('(').surround[noSpace]
		methodCall.regionFor.keyword(')').prepend[noSpace]
		methodCall.regionFor.keywords(',').forEach[prepend[noSpace]]
		methodCall.expressions.forEach[format]
	}
	
	def dispatch void format(Initialization initialization, extension IFormattableDocument document)
	{
		initialization.regionFor.keywords(',').forEach[prepend[noSpace]]
		initialization.initializations.forEach[format]
	}
	
	def dispatch void format(InitializeVariable initializeVariable, extension IFormattableDocument document )
	{
		initializeVariable.expression.format
	}
	
	def dispatch void format(Indexing indexing, extension IFormattableDocument document)
	{
		indexing.regionFor.keyword('[').surround[noSpace]
		indexing.regionFor.keyword(']').prepend[noSpace]
		indexing.index.format
	}
}
