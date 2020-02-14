package m.serializing;

import m.csharp.Argument;
import m.csharp.AttributeSection;
import m.csharp.CompilationUnit;
import m.csharp.Creation;
import m.csharp.CsharpFactory;
import m.csharp.Declaration;
import m.csharp.Declarator;
import m.csharp.Default;
import m.csharp.ExpressionStatement;
import m.csharp.Field;
import m.csharp.FloatLiteral;
import m.csharp.Foreach;
import m.csharp.Lambda;
import m.csharp.MemberInitializer;
import m.csharp.Method;
import m.csharp.Modifier;
import m.csharp.Namespace;
import m.csharp.NamespaceUsing;
import m.csharp.Parameter;
import m.csharp.ParameterizedFunction;
import m.csharp.Return;
import m.csharp.StaticUsing;
import m.csharp.Struct;
import m.csharp.Typeof;
import m.csharp.Using;
import m.modular.AccessExpression;
import m.modular.AssignmentKind;
import m.modular.ComparisonKind;
import m.modular.ModularFactory;

public class CSharpHelper 
{
	static CsharpFactory csharp = CsharpFactory.eINSTANCE;
	static ModularFactory modular = ModularFactory.eINSTANCE;
	
	public static CompilationUnit unit ()
	{
		return csharp.createCompilationUnit();
	}
	
	public static CompilationUnit addUsing(CompilationUnit unit, Using using)
	{
		unit.getUsings().add(using);
		return unit;
	}
	
	public static NamespaceUsing namespaceUsing(String namespace)
	{
		var using = csharp.createNamespaceUsing();
		using.setNamespace(namespace);
		return using;
	}
	
	public static StaticUsing staticUsing(String namespace)
	{
		var using = csharp.createStaticUsing();
		using.setNamespace(namespace);
		return using;
	}
	
	public static Namespace namespace(String name)
	{
		var namespace = csharp.createNamespace();
		namespace.setName(name);
		return namespace;
	}
	
	public static AttributeSection attribute(String name)
	{
		var attributeSection = csharp.createAttributeSection();
		var attribute = csharp.createAttribute();
		attributeSection.getAttributes().add(attribute);
		attribute.setName(name);
		return attributeSection;
	}
	
	public static AttributeSection attribute(String name, m.modular.Expression[] arguments)
	{
		var attributeSection = csharp.createAttributeSection();
		var attribute = csharp.createAttribute();
		attributeSection.getAttributes().add(attribute);
		attribute.setName(name);
		for (var argument : arguments)
		{
			attribute.getPositionalArguments().add(argument);
		}
		return attributeSection;
	}
	
	public static m.csharp.Class clazz(Modifier[] modifiers, String name, String[] superTypes)
	{
		var clazz = csharp.createClass();
		clazz.setName(name);
		for (var modifier : modifiers)
		{
			clazz.getModifiers().add(modifier);
		}
		for (var superType : superTypes)
		{
			clazz.getSuperTypes().add(superType);
		}
		return clazz;
	}
	
	public static Struct struct(Modifier[] modifiers, String name)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		for (var modifier : modifiers)
		{
			struct.getModifiers().add(modifier);
		}
		return struct;
	}
	
	public static Struct struct(String name, String[] superTypes)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		for (var superType : superTypes)
		{
			struct.getSuperTypes().add(superType);
		}
		return struct;
	}
	
	public static Struct struct(String name)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		return struct;
	}
	
	public static Field field(Modifier[] modifiers, String type, Declarator...declarators)
	{
		var field = csharp.createField();
		for (var modifier : modifiers)
		{
			field.getModifiers().add(modifier);
		}
		field.setType(type);
		for (var declarator : declarators)
		{
			field.getDeclarators().add(declarator);
		}
		return field;
	}
	
	public static Field field(String type, Declarator...declarators)
	{
		var field = csharp.createField();
		field.setType(type);
		for (var declarator : declarators)
		{
			field.getDeclarators().add(declarator);
		}
		return field;
	}
	
	public static Method method(Modifier[] modifiers, String type, String name)
	{
		var method = csharp.createMethod();
		for (var modifier : modifiers)
		{
			method.getModifiers().add(modifier);
		}
		method.setType(type);
		method.setName(name);
		return method;
	}
	
	public static Method method(Modifier[] modifiers, String type, String name, Parameter[] parameters)
	{
		var method = csharp.createMethod();
		for (var modifier : modifiers)
		{
			method.getModifiers().add(modifier);
		}
		method.setType(type);
		method.setName(name);
		for (var parameter : parameters)
		{
			method.getParameters().add(parameter);
		}
		return method;
	}
	
	public static m.modular.Selection ifStatement(m.modular.Expression condition)
	{
		var selection = modular.createSelection();
		var branch = modular.createBranch();
		branch.setCondition(condition);
		selection.getBranches().add(branch);
		return selection;
	}
	
	public static Foreach foreach(String variable, m.modular.Expression collection)
	{
		var foreach = csharp.createForeach();
		foreach.setVariable(variable);
		foreach.setCollection(collection);
		return foreach;
	}
	
	public static Return returnStatement(m.modular.Expression expression)
	{
		var returnStatement = csharp.createReturn();
		returnStatement.setExpression(expression);
		return returnStatement;
	}
	
	public static Lambda lambda(Parameter[] parameters)
	{
		var lambda = csharp.createLambda();
		for (var parameter : parameters)
		{
			lambda.getParameters().add(parameter);
		}
		return lambda;
	}
	
	public static Default defaultExpression()
	{
		return csharp.createDefault();
	}
	
	public static m.modular.Variable variable(String name)
	{
		var variable = modular.createVariable();
		variable.setName(name);
		return variable;
	}
	
	public static ParameterizedFunction function(String name, Argument... arguments)
	{
		var function = csharp.createParameterizedFunction();
		function.setName(name);
		for (var argument : arguments)
		{
			function.getArguments().add(argument);
		}
		return function;
	}
	
	public static ParameterizedFunction function(String name, String[] types, Argument... arguments)
	{
		var function = csharp.createParameterizedFunction();
		function.setName(name);
		for (var type : types)
		{
			function.getTypes().add(type);
		}
		for (var argument : arguments)
		{
			function.getArguments().add(argument);
		}
		return function;
	}
	
	public static m.modular.LogicalNot not(m.modular.Expression expression)
	{
		var not = modular.createLogicalNot();
		not.setExpression(expression);
		return not;
	}
	
	public static Typeof typeof(String type)
	{
		var typeof = csharp.createTypeof();
		typeof.setType(type);
		return typeof;
	}
	
	public static m.modular.Brackets brackets(m.modular.Expression expression)
	{
		var brackets = modular.createBrackets();
		brackets.setExpression(expression);
		return brackets;
	}
	
	public static AccessExpression access(m.modular.Expression left, m.modular.Expression right)
	{
		var access = modular.createAccessExpression();
		access.setLeft(left);
		access.setRight(right);
		return access;
	}
	
	public static m.modular.Equality equality(m.modular.Expression left, m.modular.Expression right)
	{
		var equality = modular.createEquality();
		equality.setLeft(left);
		equality.setRight(right);
		return equality;
	}
	
	public static m.csharp.Assignment assignment(m.modular.Expression left, m.modular.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		return assignment;
	}
	
	public static m.csharp.Assignment assignment(m.modular.Expression left, AssignmentKind kind, m.modular.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		assignment.setKind(kind);
		return assignment;
	}
	
	public static ExpressionStatement statement(m.modular.Expression expression)
	{
		var statement = csharp.createExpressionStatement();
		statement.setExpression(expression);
		return statement;
	}
	
	public static Declarator declarator(String name, m.modular.Expression expression)
	{
		var declarator = csharp.createDeclarator();
		declarator.setVariable(name);
		declarator.setValue(expression);
		return declarator;
	}
	
	public static Declarator declarator(String name)
	{
		var declarator = csharp.createDeclarator();
		declarator.setVariable(name);
		return declarator;
	}
	
	public static Declaration declaration(String type, Declarator...declarators) 
	{
		var declaration = csharp.createDeclaration();
		declaration.setType(type);
		for (var declarator : declarators)
		{
			declaration.getDeclarators().add(declarator);
		}
		return declaration;
	}
	
	public static Declaration declaration(Declarator...declarators)
	{
		var declaration = csharp.createDeclaration();
		for (var declarator : declarators)
		{
			declaration.getDeclarators().add(declarator);
		}
		return declaration;
	}
	
	public static Parameter parameter(String type, String name)
	{
		var parameter = csharp.createParameter();
		parameter.setType(type);
		parameter.setName(name);
		return parameter;
	}
	
	public static Parameter refParameter(String type, String name)
	{
		var parameter = csharp.createParameter();
		parameter.setType(type);
		parameter.setName(name);
		parameter.setRef(true);
		return parameter;
	}
	
	public static MemberInitializer member(String name, m.modular.Expression value)
	{
		var memberInitializer = csharp.createMemberInitializer();
		memberInitializer.setName(name);
		memberInitializer.setValue(value);
		return memberInitializer;
	}
	
	public static Argument argument(m.modular.Expression expression)
	{
		var argument = csharp.createArgument();
		argument.setValue(expression);
		return argument;
	}
	
	public static Argument refArgument(m.modular.Expression expression)
	{
		var argument = csharp.createArgument();
		argument.setRef(true);
		argument.setValue(expression);
		return argument;
	}
	
	public static FloatLiteral floatLiteral(String number)
	{
		var floatLiteral = csharp.createFloatLiteral();
		floatLiteral.setValue(number);
		return floatLiteral;
	}
	
	public static m.modular.Comparison comparison(m.modular.Expression left, ComparisonKind kind, m.modular.Expression right)
	{
		var comparison = modular.createComparison();
		comparison.setLeft(left);
		comparison.setRight(right);
		comparison.setKind(kind);
		
		return comparison;
	}
	
	public static Creation creation(String name)
	{
		var creation = csharp.createCreation();
		creation.setType(name);
		return creation;
	}
	
	public static Creation creation(String name, Argument...arguments)
	{
		var creation = csharp.createCreation();
		creation.setType(name);
		for (var argument : arguments)
		{
			creation.getArguments().add(argument);
		}
		return creation;
	}
	
	public static Creation creation(String name, MemberInitializer...members)
	{
		var creation = csharp.createCreation();
		creation.setType(name);
		for (var member : members)
		{
			creation.getMembers().add(member);
		}
		return creation;
	}

}
