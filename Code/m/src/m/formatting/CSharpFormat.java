package m.formatting;

import static m.formatting.FormatRule.angleBrackets;
import static m.formatting.FormatRule.colon;
import static m.formatting.FormatRule.comma;
import static m.formatting.FormatRule.curlyBrackets;
import static m.formatting.FormatRule.dot;
import static m.formatting.FormatRule.roundBrackets;
import static m.formatting.FormatRule.semicolon;
import static m.formatting.FormatRule.squareBrackets;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Inject;

import m.csharp.Add;
import m.csharp.AliasUsing;
import m.csharp.Attribute;
import m.csharp.AttributeSection;
import m.csharp.CompilationUnit;
import m.csharp.Constant;
import m.csharp.Constructor;
import m.csharp.Declarator;
import m.csharp.Delegate;
import m.csharp.Destructor;
import m.csharp.Enum;
import m.csharp.Event;
import m.csharp.ExternAlias;
import m.csharp.Field;
import m.csharp.Getter;
import m.csharp.Indexer;
import m.csharp.Interface;
import m.csharp.Method;
import m.csharp.NamedArgument;
import m.csharp.Namespace;
import m.csharp.NamespaceUsing;
import m.csharp.Operator;
import m.csharp.Parameter;
import m.csharp.Property;
import m.csharp.Remove;
import m.csharp.Setter;
import m.csharp.StaticConstructor;
import m.csharp.StaticUsing;
import m.csharp.Struct;
import m.csharp.TypeConstraint;
import m.csharp.TypeParameter;
import m.services.CSharpGrammarAccess;

enum FormatRule
{
	comma,
	semicolon,
	dot,
	colon,
	roundBrackets,
	squareBrackets,
	curlyBrackets,
	angleBrackets
}

public class CSharpFormat extends GenericFormatter
{
	@Inject
	CSharpGrammarAccess grammar;
	
	private void formatAll(List<? extends EObject> list)
	{
		for (var a : list)
		{
			format(a);
		}
	}
	
	protected void apply(EObject a, FormatRule... rules)
	{
		for (var rule : rules)
		{
			switch (rule)
			{
			case comma:
				for (var comma : keywords(a, ","))
				{
					prepend(comma,noSpace());
				}
				break;
			case semicolon:
				prepend(keyword(";",a),noSpace());
				break;
			case dot:
				var dot = keyword(".",a);
				prepend(dot, noSpace());
				append(dot, noSpace());
				break;
			case colon:
				prepend(keyword(":",a),noSpace());
				break;
			case roundBrackets:
				append(keyword("(",a),noSpace());
				prepend(keyword(")",a),noSpace());
				break;
			case squareBrackets:
				append(keyword("[",a),noSpace());
				prepend(keyword("]",a),noSpace());
				break;
			case curlyBrackets:
				var open = keyword("{",a);
				var close = keyword("}", a);
				prepend(open, newLine());
				prepend(close, newLine());
				indent(open, close);
			case angleBrackets:
				append(keyword("<",a),noSpace());
				prepend(keyword(">",a),noSpace());
				break;
			default:
				break;
			}
		}
	}
	protected void exceptFirst(List<? extends EObject> list)
	{
		for (var a : list)
		{
			format(a);
			if (a != list.get(0))
			{
				prepend(a,newLine());
			}
		}
	}
	protected void all(List<? extends EObject> list)
	{
		for (var a : list)
		{
			format(a);
			prepend(a,newLine());
		}
	}
	
	protected void firstIf(boolean condition, List<? extends EObject> list)
	{
		for (var a : list)
		{
			format(a);
			if (a == list.get(0))
			{
				if (condition)
				{
					prepend(a, newLines(2));
				}
			}
			else
			{
				prepend(a, newLine());
			}
		}
	}
	@Override
	protected void format(Object o)
	{
		if (o instanceof CompilationUnit)
		{
			var unit = (CompilationUnit) o;
			var aliases = unit.getExternAliases();
			var attributes = unit.getGlobalAttributes();
			var usings = unit.getUsings();
			var types = unit.getTypes();
			exceptFirst(aliases);
			firstIf(aliases.isEmpty(), attributes);
			firstIf(aliases.isEmpty() && attributes.isEmpty(), usings);
			firstIf(aliases.isEmpty() && attributes.isEmpty() && usings.isEmpty(), types);
		}
		else if (o instanceof ExternAlias)
		{
			var a = (ExternAlias) o;
			apply(a, semicolon);
		}
		else if (o instanceof AttributeSection)
		{
			var a = (AttributeSection) o;
			apply(a, colon, squareBrackets);
			formatAll(a.getAttributes());
		}
		else if (o instanceof Attribute)
		{
			var a = (Attribute) o;
			apply(a, roundBrackets);
			formatAll(a.getPositionalArguments());
			formatAll(a.getNamedArguments());
		}
		else if (o instanceof NamedArgument)
		{
			var a = (NamedArgument) o;
			format(a.getExpression());
		}
		else if (o instanceof AliasUsing)
		{
			var a = (AliasUsing) o;
			apply(a, semicolon);
		}
		else if (o instanceof NamespaceUsing)
		{
			var a = (NamespaceUsing) o;
			apply(a, semicolon);
		}
		else if (o instanceof StaticUsing)
		{
			var a = (StaticUsing) o;
			apply(a, semicolon);
		}
		else if (o instanceof Namespace)
		{
			var a = (Namespace) o;
			apply(a, curlyBrackets, semicolon);
			formatAll(a.getExternAliases());
			formatAll(a.getUsings());
			formatAll(a.getMembers());
		}
		else if (o instanceof Struct)
		{
			var a = (Struct) o;
			apply(a, curlyBrackets, angleBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getMembers());
		}
		else if (o instanceof m.csharp.Class)
		{
			var a = (m.csharp.Class) o;
			apply(a, curlyBrackets, angleBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getMembers());
		}
		else if (o instanceof Enum)
		{
			var a = (Enum) o;
			apply(a, curlyBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
		}
		else if (o instanceof Interface)
		{
			var a = (Interface) o;
			apply(a, angleBrackets, curlyBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getMembers());
		}
		else if (o instanceof Delegate)
		{
			var a = (Delegate) o;
			apply(a, angleBrackets, roundBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getParameters());
			formatAll(a.getTypeConstraints());
		}
		else if (o instanceof Parameter)
		{
			var a = (Parameter) o;
			formatAll(a.getAttributes());
			format(a.getDefault());
		}
		else if (o instanceof TypeParameter)
		{
			var a = (TypeParameter) o;
			formatAll(a.getAttributes());
		}
		else if (o instanceof TypeConstraint)
		{
			var a = (TypeConstraint) o;
			apply (a, roundBrackets, comma);
		}
		else if (o instanceof Declarator)
		{
			var a = (Declarator) o;
			format(a.getValue());
		}
		else if (o instanceof Field)
		{
			var a = (Field) o;
			apply(a, semicolon, comma);
			exceptFirst(a.getAttributes());
			formatAll(a.getDeclarators());
		}
		else if (o instanceof Method)
		{
			var a = (Method) o;
			apply(a, angleBrackets, roundBrackets, curlyBrackets, comma, semicolon, dot);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getStatements());
			format(a.getExpression());
		}
		else if (o instanceof Property)
		{
			var a = (Property) o;
			apply(a, curlyBrackets, semicolon, dot);
			exceptFirst(a.getAttributes());
			format(a.getExpression());
			var getter = a.getGetter();
			var setter = a.getSetter();
			if (getter != null)
			{
				prepend(getter, newLine());
				format(getter);
			}
			if (setter != null)
			{
				prepend(setter, newLine());
				format(setter);
			}
		}
		else if (o instanceof Constant)
		{
			var a = (Constant) o;
			apply(a, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getDeclarators());
		}
		else if (o instanceof Event)
		{
			var a = (Event) o;
			apply(a, comma, semicolon, dot, curlyBrackets);
			exceptFirst(a.getAttributes());
			formatAll(a.getDeclarators());
			var add = a.getAdd();
			var remove = a.getRemove();
			if (add != null)
			{
				prepend(add, newLine());
				format(add);
			}
			if (remove != null)
			{
				prepend(remove, newLine());
				format(remove);
			}
		}
		else if (o instanceof Indexer)
		{
			var a = (Indexer) o;
			apply(a, squareBrackets, curlyBrackets, comma, semicolon, dot);
			exceptFirst(a.getAttributes());
			formatAll(a.getParameters());
			format(a.getExpression());
			var getter = a.getGetter();
			var setter = a.getSetter();
			if (getter != null)
			{
				prepend(getter, newLine());
				format(getter);
			}
			if (setter != null)
			{
				prepend(setter, newLine());
				format(setter);
			}
		}
		else if (o instanceof Operator)
		{
			var a = (Operator) o;
			apply(a, roundBrackets, curlyBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			all(a.getStatements());
			format(a.getExpression());
		}
		else if (o instanceof Constructor)
		{
			var a = (Constructor) o;
			apply(a, roundBrackets, curlyBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getParameters());
			formatAll(a.getArguments());
			all(a.getStatements());
		}
		else if (o instanceof Destructor)
		{
			var a = (Destructor) o;
			apply(a, roundBrackets, curlyBrackets, semicolon);
			exceptFirst(a.getAttributes());
			all(a.getStatements());
		}
		else if (o instanceof StaticConstructor)
		{
			var a = (StaticConstructor) o;
			apply(a, roundBrackets, curlyBrackets, semicolon);
			exceptFirst(a.getAttributes());
			all(a.getStatements());
		}
		else if (o instanceof Getter)
		{
			var a = (Getter) o;
			apply(a, curlyBrackets, semicolon);
			exceptFirst(a.getAttributes());
			all(a.getStatements());
		}
		else if (o instanceof Setter)
		{
			var a = (Setter) o;
			apply(a, curlyBrackets, semicolon);
			exceptFirst(a.getAttributes());
			all(a.getStatements());
		}
		else if (o instanceof Add)
		{
			var a = (Add) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
		}
		else if (o instanceof Remove)
		{
			var a = (Remove) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
		}
	}
}
