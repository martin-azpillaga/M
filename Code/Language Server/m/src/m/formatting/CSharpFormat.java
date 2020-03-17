package m.formatting;

import static m.formatting.FormatRule.*;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import m.csharp.Add;
import m.csharp.AliasUsing;
import m.csharp.Argument;
import m.csharp.ArrayInitializer;
import m.csharp.Assignment;
import m.csharp.Attribute;
import m.csharp.AttributeSection;
import m.csharp.Break;
import m.csharp.Case;
import m.csharp.Catch;
import m.csharp.Checked;
import m.csharp.CompilationUnit;
import m.csharp.Constant;
import m.csharp.Constructor;
import m.csharp.Continue;
import m.csharp.Creation;
import m.csharp.Declaration;
import m.csharp.Declarator;
import m.csharp.Decrement;
import m.csharp.Delegate;
import m.csharp.Destructor;
import m.csharp.Do;
import m.csharp.Enumeration;
import m.csharp.Event;
import m.csharp.ExpressionStatement;
import m.csharp.ExternAlias;
import m.csharp.Field;
import m.csharp.For;
import m.csharp.Foreach;
import m.csharp.Getter;
import m.csharp.Goto;
import m.csharp.Increment;
import m.csharp.Index;
import m.csharp.Indexer;
import m.csharp.Interface;
import m.csharp.LabeledStatement;
import m.csharp.Lambda;
import m.csharp.Lock;
import m.csharp.MemberInitializer;
import m.csharp.Method;
import m.csharp.NamedArgument;
import m.csharp.Namespace;
import m.csharp.NamespaceUsing;
import m.csharp.NullCoalescing;
import m.csharp.Operator;
import m.csharp.Parameter;
import m.csharp.ParameterizedFunction;
import m.csharp.Property;
import m.csharp.ReferenceType;
import m.csharp.Remove;
import m.csharp.Return;
import m.csharp.Setter;
import m.csharp.StaticConstructor;
import m.csharp.StaticUsing;
import m.csharp.DataType;
import m.csharp.Switch;
import m.csharp.Ternary;
import m.csharp.Throw;
import m.csharp.Try;
import m.csharp.TypeConstraint;
import m.csharp.TypeParameter;
import m.csharp.Typeof;
import m.csharp.Unchecked;
import m.csharp.Yield;
import m.csharp.AccessExpression;
import m.csharp.AdditiveExpression;
import m.csharp.Block;
import m.csharp.Brackets;
import m.csharp.Branch;
import m.csharp.Comparison;
import m.csharp.Equality;
import m.csharp.Iteration;
import m.csharp.LogicalAnd;
import m.csharp.LogicalNot;
import m.csharp.LogicalOr;
import m.csharp.MultiplicativeExpression;
import m.csharp.Selection;

enum FormatRule
{
	comma,
	semicolon,
	dot,
	colon,
	questionMark,
	brackets,
	roundBrackets,
	squareBrackets,
	curlyBrackets,
	angleBrackets
}

public class CSharpFormat extends FormattingHelper
{
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
			case questionMark:
				prepend(keyword("?",a),noSpace());
				break;
			case brackets:
				prepend(keyword("(",a),noSpace());
				append(keyword("(",a),noSpace());
				prepend(keyword(")",a),noSpace());
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
				prepend(keyword("<",a), noSpace());
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
				if (!condition)
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
			apply(a, brackets);
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
		else if (o instanceof DataType)
		{
			var a = (DataType) o;
			apply(a, curlyBrackets, angleBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			if (a.getAttributes().size() > 0)
			{
				append(a.getAttributes().get(a.getAttributes().size()-1),newLine());
			}
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getMembers());
		}
		else if (o instanceof ReferenceType)
		{
			var a = (ReferenceType) o;
			apply(a, curlyBrackets, angleBrackets, comma, semicolon);
			exceptFirst(a.getAttributes());
			formatAll(a.getTypeParameters());
			formatAll(a.getTypeConstraints());
			all(a.getMembers());
		}
		else if (o instanceof Enumeration)
		{
			var a = (Enumeration) o;
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
			apply (a, brackets, comma);
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
		else if (o instanceof LabeledStatement)
		{
			var a = (LabeledStatement) o;
			apply(a, colon);
			format(a.getStatement());
		}
		else if (o instanceof Try)
		{
			var a = (Try) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
			formatAll(a.getCatches());
			all(a.getFinally());
		}
		else if (o instanceof Catch)
		{
			var a = (Catch) o;
			apply(a, curlyBrackets, roundBrackets);
			format(a.getFilter());
			all(a.getStatements());
		}
		else if (o instanceof Iteration)
		{
			var a = (Iteration) o;
			apply(a, roundBrackets, curlyBrackets);
			format(a.getCondition());
			all(a.getStatements());
		}
		else if (o instanceof Selection)
		{
			var a = (Selection) o;
			formatAll(a.getBranches());
		}
		else if (o instanceof Branch)
		{
			var a = (Branch) o;
			apply(a, curlyBrackets);
			format(a.getCondition());
			all(a.getStatements());
		}
		else if (o instanceof Switch)
		{
			var a = (Switch) o;
			apply(a, roundBrackets, curlyBrackets);
			format(a.getExpression());
			all(a.getCases());
		}
		else if (o instanceof Case)
		{
			var a = (Case) o;
			apply(a, colon);
			format(a.getCondition());
			all(a.getStatements());
		}
		else if (o instanceof For)
		{
			var a = (For) o;
			apply(a, roundBrackets, curlyBrackets, semicolon);
			format(a.getInitialization());
			format(a.getCondition());
			format(a.getIterator());
			all(a.getStatements());
		}
		else if (o instanceof Foreach)
		{
			var a = (Foreach) o;
			apply(a, roundBrackets, curlyBrackets);
			format(a.getCollection());
			all(a.getStatements());
		}
		else if (o instanceof Do)
		{
			var a = (Do) o;
			apply(a, roundBrackets, curlyBrackets);
			all(a.getStatements());
			format(a.getCondition());
		}
		else if (o instanceof Declaration)
		{
			var a = (Declaration) o;
			apply(a, semicolon);
			formatAll(a.getDeclarators());
		}
		else if (o instanceof Return)
		{
			var a = (Return) o;
			apply(a, semicolon);
			format(a.getExpression());
		}
		else if (o instanceof Yield)
		{
			var a = (Yield) o;
			apply(a, semicolon);
			format(a.getExpression());
		}
		else if (o instanceof Break)
		{
			var a = (Break) o;
			apply(a, semicolon);
		}
		else if (o instanceof Continue)
		{
			var a = (Continue) o;
			apply(a, semicolon);
		}
		else if (o instanceof Goto)
		{
			var a = (Goto) o;
			apply(a, semicolon);
			format(a.getCase());
		}
		else if (o instanceof Throw)
		{
			var a = (Throw) o;
			apply(a, semicolon);
			format(a.getExpression());
		}
		else if (o instanceof Checked)
		{
			var a = (Checked) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
		}
		else if (o instanceof Unchecked)
		{
			var a = (Unchecked) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
		}
		else if (o instanceof Lock)
		{
			var a = (Lock) o;
			apply(a, roundBrackets, curlyBrackets);
			format(a.getExpression());
			all(a.getStatements());
		}
		else if (o instanceof ExpressionStatement)
		{
			var a = (ExpressionStatement) o;
			apply(a, semicolon);
			format(a.getExpression());
		}
		else if (o instanceof Lambda)
		{
			var a = (Lambda) o;
			apply(a, roundBrackets, curlyBrackets, comma);
			formatAll(a.getParameters());
			all(a.getStatements());
		}
		else if (o instanceof ArrayInitializer)
		{
			var a = (ArrayInitializer) o;
			apply(a, comma);
			formatAll(a.getVariables());
		}
		else if (o instanceof Creation)
		{
			var a = (Creation) o;
			apply(a, brackets, comma);
			formatAll(a.getMembers());
			formatAll(a.getElements());
			formatAll(a.getArguments());
		}
		else if (o instanceof Assignment)
		{
			var a = (Assignment) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof Ternary)
		{
			var a = (Ternary) o;
			apply(a, questionMark);
			format(a.getLeft());
			format(a.getYes());
			format(a.getNo());
		}
		else if (o instanceof NullCoalescing)
		{
			var a = (NullCoalescing) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof LogicalOr)
		{
			var a = (LogicalOr) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof LogicalAnd)
		{
			var a = (LogicalAnd) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof Equality)
		{
			var a = (Equality) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof Comparison)
		{
			var a = (Comparison) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof AdditiveExpression)
		{
			var a = (AdditiveExpression) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof MultiplicativeExpression)
		{
			var a = (MultiplicativeExpression) o;
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof AccessExpression)
		{
			var a = (AccessExpression) o;
			apply(a, dot);
			format(a.getLeft());
			format(a.getRight());
		}
		else if (o instanceof Increment)
		{
			var a = (Increment) o;
			format(a.getExpression());
			prepend(keyword("++",a),noSpace());
		}
		else if (o instanceof Decrement)
		{
			var a = (Decrement) o;
			format(a.getExpression());
			prepend(keyword("++",a),noSpace());
		}
		else if (o instanceof Typeof)
		{
			var a = (Typeof) o;
			apply(a, brackets, comma);
		}
		else if (o instanceof ParameterizedFunction)
		{
			var a = (ParameterizedFunction) o;
			apply(a, brackets, angleBrackets, comma);
			formatAll(a.getArguments());
		}
		else if (o instanceof LogicalNot)
		{
			var a = (LogicalNot) o;
			append(keyword("!",a),noSpace());
			format(a.getExpression());
		}
		else if (o instanceof Brackets)
		{
			var a = (Brackets) o;
			apply(a, brackets);
			format(a.getExpression());
		}
		else if (o instanceof Argument)
		{
			var a = (Argument) o;
			format(a.getValue());
		}
		else if (o instanceof Index)
		{
			var a = (Index) o;
			apply(a, squareBrackets, comma);
			formatAll(a.getIndices());
		}
		else if (o instanceof MemberInitializer)
		{
			var a = (MemberInitializer) o;
			apply(a, squareBrackets, comma);
			format(a.getValue());
			formatAll(a.getIndices());
		}
		else if (o instanceof Block)
		{
			var a = (Block) o;
			apply(a, curlyBrackets);
			all(a.getStatements());
		}
	}
}
