package m.formatting2

import com.google.inject.Inject
import java.lang.reflect.Method
import m.csharp.Model
import m.csharp.Using
import m.services.CSharpGrammarAccess
import m.validation.Type
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.xtext.generator.parser.antlr.splitting.simpleExpressions.MethodCall

class CSharpFormatter extends StructuredFormatter 
{
	
	@Inject extension CSharpGrammarAccess

	def dispatch void format(Model model, extension IFormattableDocument document) 
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
		method.statements.forEach[format]
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
		branch.statements.forEach[format]
	}
	
	def dispatch void format(Return r, extension IFormattableDocument document)
	{
		r.prepend[newLine]
		r.regionFor.keyword(';').prepend[noSpace]
		r.expression.format
	}
	
	def dispatch void format(CAssignment assignment, extension IFormattableDocument document)
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
		lambda.statements.forEach[format]
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
