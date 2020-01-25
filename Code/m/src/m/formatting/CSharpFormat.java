package m.formatting;

import com.google.inject.Inject;

import m.csharp.CompilationUnit;
import m.csharp.Struct;
import m.csharp.Field;
import m.services.CSharpGrammarAccess;

public class CSharpFormat extends GenericFormatter
{
	@Inject
	CSharpGrammarAccess grammar;
	
	@Override
	protected void format(Object o)
	{
		if (o instanceof CompilationUnit)
		{
			var unit = (CompilationUnit) o;
			for (var using : unit.getUsings())
			{
				if (using != unit.getUsings().get(0))
				{
					prepend(using,newLine());
				}
				prepend(keyword(using,";"), noSpace());
			}
			for (var type : unit.getTypes())
			{
				prepend(type, newLines(2));
				for (var annotation : type.getAnnotations())
				{
					if (annotation != type.getAnnotations().get(0))
					{
						prepend(annotation,newLine());
					}
					append(keyword(annotation,"["), noSpace());
					prepend(keyword(annotation,"]"), noSpace());
					if (annotation == type.getAnnotations().get(type.getAnnotations().size() -1))
					{
						append(keyword(annotation,"]"), newLine());
					}					
				}
				prepend(keyword(type,"{"),newLine());
				prepend(keyword(type,"}"),newLine());
				indent(keyword(type,"{"),keyword(type,"}"));
				format(type);
			}
		}
		else if (o instanceof m.csharp.Class)
		{
			var clazz = (m.csharp.Class) o;
			for (var member : clazz.getMembers())
			{
				prepend(member, newLine());
				format(member);
			}
		}
		else if (o instanceof Struct)
		{
			var struct = (Struct) o;
			for (var member : struct.getMembers())
			{
				prepend(member, newLine());
				format(member);
			}
		}
		else if (o instanceof Field)
		{
			var field = (Field) o;
			prepend(keyword(field,";"), noSpace());
		}
	}

}
