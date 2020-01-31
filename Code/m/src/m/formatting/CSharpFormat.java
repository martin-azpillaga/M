package m.formatting;

import com.google.inject.Inject;

import m.csharp.CompilationUnit;
import m.csharp.Struct;
import m.csharp.Field;
import m.csharp.Method;
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
				if (unit.getUsings().size() > 0)
				{
					prepend(type, newLines(2));
				}
				format(type);
			}
		}
		if (o instanceof Struct)
		{
			var struct = (Struct) o;
			for (var attribute : struct.getAttributes())
			{
				if (attribute != struct.getAttributes().get(0))
				{
					prepend(attribute,newLine());
				}
				append(keyword(attribute,"["), noSpace());
				prepend(keyword(attribute,"]"), noSpace());
				if (attribute == struct.getAttributes().get(struct.getAttributes().size() -1))
				{
					append(keyword(attribute,"]"), newLine());
				}					
			}
			for (var comma : keywords(struct, ","))
			{
				prepend(comma,noSpace());
			}
			prepend(keyword(struct,"{"),newLine());
			prepend(keyword(struct,"}"),newLine());
			indent(keyword(struct,"{"),keyword(struct,"}"));
			for (var member : struct.getMembers())
			{
				prepend(member, newLine());
				format(member);
			}
		}
		else if (o instanceof m.csharp.Class)
		{
			var struct = (m.csharp.Class) o;
			for (var attribute : struct.getAttributes())
			{
				if (attribute != struct.getAttributes().get(0))
				{
					prepend(attribute,newLine());
				}
				append(keyword(attribute,"["), noSpace());
				prepend(keyword(attribute,"]"), noSpace());
				if (attribute == struct.getAttributes().get(struct.getAttributes().size() -1))
				{
					append(keyword(attribute,"]"), newLine());
				}					
			}
			for (var comma : keywords(struct, ","))
			{
				prepend(comma,noSpace());
			}
			prepend(keyword(struct,"{"),newLine());
			prepend(keyword(struct,"}"),newLine());
			indent(keyword(struct,"{"),keyword(struct,"}"));
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
		else if (o instanceof Method)
		{
			var method = (Method) o;
			for (var comma : keywords(method, ","))
			{
				prepend(comma,noSpace());
			}
			prepend(keyword(method,")"), noSpace());
			prepend(keyword(method, "{"),newLine());
			prepend(keyword(method,"}"),newLine());
			indent(keyword(method,"{"),keyword(method,"}"));
			
			var parameters = method.getParameters();
			for (var parameter : parameters)
			{
				if (parameter != parameters.get(0))
				{
					prepend(parameter, oneSpace());
				}
				else
				{
					prepend(parameter, noSpace());
				}
			}
		}
	}
}
