package m.serializing;

import m.csharp.Argument;
import m.csharp.AttributeSection;
import m.csharp.BooleanLiteral;
import m.csharp.CompilationUnit;
import m.csharp.Creation;
import m.csharp.CsharpFactory;
import m.csharp.Declaration;
import m.csharp.Declarator;
import m.csharp.Default;
import m.csharp.Expression;
import m.csharp.ExpressionStatement;
import m.csharp.Field;
import m.csharp.FloatLiteral;
import m.csharp.For;
import m.csharp.Foreach;
import m.csharp.Increment;
import m.csharp.Index;
import m.csharp.Interface;
import m.csharp.Lambda;
import m.csharp.MemberInitializer;
import m.csharp.Method;
import m.csharp.Modifier;
import m.csharp.Namespace;
import m.csharp.NamespaceUsing;
import m.csharp.Parameter;
import m.csharp.ParameterizedFunction;
import m.csharp.Property;
import m.csharp.Return;
import m.csharp.Statement;
import m.csharp.StaticUsing;
import m.csharp.Struct;
import m.csharp.TypeConstraint;
import m.csharp.TypeParameter;
import m.csharp.Typeof;
import m.csharp.Using;
import m.csharp.AccessExpression;
import m.csharp.AssignmentKind;
import m.csharp.ComparisonKind;

public class CSharpHelper 
{
	static CsharpFactory csharp = CsharpFactory.eINSTANCE;
	
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
	
	public static AttributeSection attribute(String name, m.csharp.Expression[] arguments)
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
	
	public static m.csharp.Class clazz(Modifier[] modifiers, String name)
	{
		var clazz = csharp.createClass();
		clazz.setName(name);
		for (var modifier : modifiers)
		{
			clazz.getModifiers().add(modifier);
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
	
	public static Struct struct(Modifier[] modifiers, String name, String[] superTypes)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		for (var modifier : modifiers)
		{
			struct.getModifiers().add(modifier);
		}
		for (var superType : superTypes)
		{
			struct.getSuperTypes().add(superType);
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
	
	public static Interface Interface(Modifier[] modifiers, String name)
	{
		var Interface = csharp.createInterface();
		for (var modifier : modifiers)
		{
			Interface.getModifiers().add(modifier);
		}
		Interface.setName(name);
		return Interface;
	}
	
	public static Property getter(String type, String name)
	{
		var property = csharp.createProperty();
		property.setType(type);
		property.setName(name);
		var getter = csharp.createGetter();
		getter.setEmpty(true);
		property.setGetter(getter);
		return property;
	}
	
	public static Property property(String type, String name)
	{
		var property = csharp.createProperty();
		property.setType(type);
		property.setName(name);
		var getter = csharp.createGetter();
		getter.setEmpty(true);
		var setter = csharp.createSetter();
		setter.setEmpty(true);
		property.setGetter(getter);
		property.setSetter(setter);
		return property;
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
	
	public static Method method(Modifier[] modifiers, String type, String name, String[] typeParameters, Parameter[] parameters)
	{
		var method = csharp.createMethod();
		for (var modifier : modifiers)
		{
			method.getModifiers().add(modifier);
		}
		method.setType(type);
		for (var t : typeParameters)
		{
			method.getTypeParameters().add(typeParameter(t));
		}
		method.setName(name);
		for (var parameter : parameters)
		{
			method.getParameters().add(parameter);
		}
		return method;
	}
	
	public static Method method(Modifier[] modifiers, String type, String name, String[] typeParameters)
	{
		var method = csharp.createMethod();
		for (var modifier : modifiers)
		{
			method.getModifiers().add(modifier);
		}
		method.setType(type);
		for (var t : typeParameters)
		{
			method.getTypeParameters().add(typeParameter(t));
		}
		method.setName(name);
		return method;
	}
	
	public static TypeParameter typeParameter(String name)
	{
		var typeParameter = csharp.createTypeParameter();
		typeParameter.setName(name);
		return typeParameter;
	}
	
	public static TypeConstraint typeStructConstraint(String name, String[] superTypes)
	{
		var typeConstraint = csharp.createTypeConstraint();
		typeConstraint.setStruct(true);
		typeConstraint.setType(name);
		for (var superType : superTypes)
		{
			typeConstraint.getSuperTypes().add(superType);
		}
		return typeConstraint;
	}
	
	public static TypeConstraint typeConstraint(String name, String[] superTypes)
	{
		var typeConstraint = csharp.createTypeConstraint();
		typeConstraint.setType(name);
		for (var superType : superTypes)
		{
			typeConstraint.getSuperTypes().add(superType);
		}
		return typeConstraint;
	}
	
	public static m.csharp.Selection ifStatement(m.csharp.Expression condition)
	{
		var selection = csharp.createSelection();
		var branch = csharp.createBranch();
		branch.setCondition(condition);
		selection.getBranches().add(branch);
		return selection;
	}
	
	public static For forStatement(Statement initialization, Expression condition, Expression iterator)
	{
		var forStatement = csharp.createFor();
		forStatement.setInitialization(initialization);
		forStatement.setCondition(condition);
		forStatement.setIterator(iterator);
		return forStatement;
	}
	
	public static Foreach foreach(String variable, m.csharp.Expression collection)
	{
		var foreach = csharp.createForeach();
		foreach.setVariable(variable);
		foreach.setCollection(collection);
		return foreach;
	}
	
	public static Increment increment(Expression expression)
	{
		var increment = csharp.createIncrement();
		increment.setExpression(expression);
		return increment;
	}
	
	public static Index index(String name, Expression... indices)
	{
		var index = csharp.createIndex();
		index.setName(name);
		for (var indice : indices)
		{
			index.getIndices().add(indice);
		}
		return index;
	}
	
	public static BooleanLiteral booleanLiteral(String value)
	{
		var literal = csharp.createBooleanLiteral();
		literal.setValue(value);
		return literal;
	}
	
	public static Return returnStatement(m.csharp.Expression expression)
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
	
	public static m.csharp.Variable variable(String name)
	{
		var variable = csharp.createVariable();
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
	
	public static m.csharp.LogicalNot not(m.csharp.Expression expression)
	{
		var not = csharp.createLogicalNot();
		not.setExpression(expression);
		return not;
	}
	
	public static Typeof typeof(String type)
	{
		var typeof = csharp.createTypeof();
		typeof.setType(type);
		return typeof;
	}
	
	public static m.csharp.Brackets brackets(m.csharp.Expression expression)
	{
		var brackets = csharp.createBrackets();
		brackets.setExpression(expression);
		return brackets;
	}
	
	public static AccessExpression access(m.csharp.Expression left, m.csharp.Expression right)
	{
		var access = csharp.createAccessExpression();
		access.setLeft(left);
		access.setRight(right);
		return access;
	}
	
	public static m.csharp.Equality equality(m.csharp.Expression left, m.csharp.Expression right)
	{
		var equality = csharp.createEquality();
		equality.setLeft(left);
		equality.setRight(right);
		return equality;
	}
	
	public static m.csharp.Assignment assignment(m.csharp.Expression left, m.csharp.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		return assignment;
	}
	
	public static m.csharp.Assignment assignment(m.csharp.Expression left, AssignmentKind kind, m.csharp.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		assignment.setKind(kind);
		return assignment;
	}
	
	public static ExpressionStatement statement(m.csharp.Expression expression)
	{
		var statement = csharp.createExpressionStatement();
		statement.setExpression(expression);
		return statement;
	}
	
	public static Declarator declarator(String name, m.csharp.Expression expression)
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
	
	public static Parameter thisParameter(String type, String name)
	{
		var parameter = csharp.createParameter();
		parameter.setThis(true);
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
	
	public static MemberInitializer member(String name, m.csharp.Expression value)
	{
		var memberInitializer = csharp.createMemberInitializer();
		memberInitializer.setName(name);
		memberInitializer.setValue(value);
		return memberInitializer;
	}
	
	public static Argument argument(m.csharp.Expression expression)
	{
		var argument = csharp.createArgument();
		argument.setValue(expression);
		return argument;
	}
	
	public static Argument refArgument(m.csharp.Expression expression)
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
	
	public static m.csharp.Comparison comparison(m.csharp.Expression left, ComparisonKind kind, m.csharp.Expression right)
	{
		var comparison = csharp.createComparison();
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
