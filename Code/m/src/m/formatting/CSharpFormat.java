package m.formatting;

import com.google.inject.Inject;

import m.csharp.Argument;
import m.csharp.Assignment;
import m.csharp.CompilationUnit;
import m.csharp.Creation;
import m.csharp.Declaration;
import m.csharp.Declarator;
import m.csharp.ExpressionStatement;
import m.csharp.Struct;
import m.modular.AccessExpression;
import m.csharp.Field;
import m.csharp.For;
import m.csharp.Foreach;
import m.csharp.Lambda;
import m.csharp.MemberInitializer;
import m.csharp.Method;
import m.csharp.ParameterizedFunction;
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
			var statements = method.getStatements();
			for (var statement : statements)
			{
				prepend(statement, newLine());
				format(statement);
			}
		}
		else if (o instanceof Foreach)
		{
			var foreach = (Foreach) o;
			append(keyword(foreach,"("), noSpace());
			prepend(keyword(foreach,")"), noSpace());
			prepend(keyword(foreach, "{"),newLine());
			prepend(keyword(foreach,"}"),newLine());
			indent(keyword(foreach,"{"),keyword(foreach,"}"));
			for (var statement : foreach.getStatements())
			{
				prepend(statement, newLine());
				format(statement);
			}
		}
		else if (o instanceof For)
		{
			var f = (For) o;
			prepend(keyword(f,";"), noSpace());
			append(keyword(f,"("), noSpace());
			prepend(keyword(f,")"), noSpace());
			prepend(keyword(f, "{"),newLine());
			prepend(keyword(f,"}"),newLine());
			indent(keyword(f,"{"),keyword(f,"}"));
			format(f.getInitialization());
			format(f.getCondition());
			format(f.getIterator());
			for (var statement : f.getStatements())
			{
				prepend(statement, newLine());
				format(statement);
			}
		}
		else if (o instanceof Declaration)
		{
			var declaration = (Declaration) o;
			for (var comma : keywords(declaration, ","))
			{
				prepend(comma,noSpace());
			}
			prepend(keyword(declaration,";"), noSpace());
			for (var declarator : declaration.getDeclarators())
			{
				format(declarator);
			}
		}
		else if (o instanceof ExpressionStatement)
		{
			var statement = (ExpressionStatement) o;
			prepend(keyword(statement,";"), noSpace());
			format(statement.getExpression());
		}
		else if (o instanceof Declarator)
		{
			var declarator = (Declarator) o;
			format(declarator.getValue());
		}
		else if (o instanceof ParameterizedFunction)
		{
			var function = (ParameterizedFunction) o;
			var types = function.getTypes();
			prepend(keyword(function,"<"), noSpace());
			append(keyword(function,"<"), noSpace());
			prepend(keyword(function,">"), noSpace());
			prepend(keyword(function,"("), noSpace());
			prepend(keyword(function,")"), noSpace());
			append(keyword(function,"("), noSpace());
			for (var comma : keywords(function, ","))
			{
				prepend(comma,noSpace());
			}
			
			var arguments = function.getArguments();
			for (var argument : arguments)
			{
				format(argument);
			}
		}
		else if (o instanceof Argument)
		{
			var argument = (Argument) o;
			format(argument.getValue());
		}
		else if (o instanceof Creation)
		{
			var creation = (Creation) o;
			for (var member : creation.getMembers())
			{
				format(member);
			}
		}
		else if (o instanceof MemberInitializer)
		{
			var member = (MemberInitializer) o;
			format(member.getValue());
		}
		else if (o instanceof Lambda)
		{
			var lambda = (Lambda) o;
			prepend(keyword(lambda,"("), noSpace());
			prepend(keyword(lambda,")"), noSpace());
			append(keyword(lambda,"("), noSpace());
			for (var comma : keywords(lambda, ","))
			{
				prepend(comma,noSpace());
			}
			
			var parameters = lambda.getParameters();
			for (var parameter : parameters)
			{
				format(parameter);
			}
			prepend(keyword(lambda, "{"),newLine());
			prepend(keyword(lambda,"}"),newLine());
			indent(keyword(lambda,"{"),keyword(lambda,"}"));
			for (var statement : lambda.getStatements())
			{
				prepend(statement, newLine());
				format(statement);
			}
		}
		else if (o instanceof AccessExpression)
		{
			var a = (AccessExpression) o;
			prepend(keyword(a, "."), noSpace());
			append(keyword(a, "."), noSpace());
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof Assignment)
		{
			var a = (Assignment) o;
			format(a.getLeft());
			format(a.getRight());
		}
	}
}
