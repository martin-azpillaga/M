package m.validation;

import static m.m.MPackage.Literals.APPLICATION__NAME;
import static m.m.MPackage.Literals.BINARY__OPERATOR;
import static m.m.MPackage.Literals.BLOCK__NAME;
import static m.m.MPackage.Literals.UNARY__OPERATOR;
import static m.validation.StandardBlock.QUERY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.generator.Game;
import m.m.Application;
import m.m.Assignment;
import m.m.Binary;
import m.m.Block;
import m.m.Cell;
import m.m.Delegation;
import m.m.Expression;
import m.m.File;
import m.m.Function;
import m.m.Statement;
import m.m.Unary;
import m.m.Value;
import m.types.Type;
import m.types.TypeVariable;

enum MError
{
	redefinition, undefined, syntax, undecidable, incompatible
}

enum GroupingReason
{
	assignment, sameComponent, sameVariable, parameterArgument, resultReturn, vectorArithmetic
}

public class MValidator extends AbstractMValidator
{
	Language language;
	
	Map<String, Expression> userValues;
	Map<String, Group> groupOfComponent;
	Map<Expression, Group> groupOfExpression;
	
	class Group
	{
		public List<GroupEntry> entries = new ArrayList<GroupEntry>();
		public Type type;
		public HashMap<Expression, GroupInfo> expressions = new HashMap<>();
		
		public void addEntry(Expression expression, Type type, String reason)
		{
			if (!expressions.containsKey(expression))
			{
				expressions.put(expression, new GroupInfo());
			}
			expressions.get(expression).types.put(type, reason);
		}
		
		public void addEntry(Expression expression, String groupingReason)
		{
			if (!expressions.containsKey(expression))
			{
				expressions.put(expression, new GroupInfo());
			}
			expressions.get(expression).groupingReasons.add(groupingReason);
		}
	}
	
	class GroupInfo {
		public HashMap<Type, String> types = new HashMap<>();
		public List<String> groupingReasons = new ArrayList<>();
	}
	class GroupEntry
	{
		public Expression expression;
		public ArrayList<GroupingReason> groupingReasons = new ArrayList<GroupingReason>();
		public ArrayList<Type> types = new ArrayList<Type>();
	}
	class Problem
	{
		public boolean isError;
		public String message;
		public EObject o;
		public EStructuralFeature feature;
	}
	
	public static Game game;
	
	Map<String,Type> standardSymbols = new HashMap<String,Type>();
	Map<String,Type> userComponents = new HashMap<String,Type>();
	Map<String,Type> standardBlocks = new HashMap<String,Type>();
	
	@Check
	public void validate(File file)
	{
		if (!file.eResource().getErrors().isEmpty()) return;
		
		initialize(file);
		for (var function : file.getFunctions())
		{
			validate(function);
		}
		solve(file);
	}
	
	void initialize(File file) {
		
	}
	
	void validate(Function function) {
		for (var statement : function.getStatements()) {
			validate(statement);
		}
	}
	
	void validate(Statement statement) {
		if (statement instanceof Block) {
			var block = (Block) statement;
			var name = block.getName();
			var expression = block.getExpression();
			
			infer(name, block, BLOCK__NAME, block.getExpression());
			

			var standard = language.blocks.get(name);
			if (standard == QUERY)
			{
				if (expression instanceof Value)
				{
					var value = (Value) expression;
					var innerRestore = new HashMap<String,Expression>(userValues);
					declare(value);
					for (var s : block.getStatements())
					{
						validate(s);
					}
					userValues = innerRestore;
				}
				else
				{
					//MError(syntax, statement, BLOCK__EXPRESSION)
				}
			}
			else if (standard != null)
			{
				validate(expression);
				for (var s : block.getStatements())
				{
					validate(s);
				}
			}
			else
			{
				//MError(undefined, statement, BLOCK__NAME)
			}
		}
		else if (statement instanceof Delegation)
		{
			var delegation = (Delegation) statement;
			validate(delegation.getApplication());
		}
		else if (statement instanceof Assignment)
		{
			var assignment = (Assignment) statement;
			var atom = assignment.getAtom();
			var expression = assignment.getExpression();
			
			infer("=", assignment, null, atom, expression);
			
			validate(expression);
			
			if (atom instanceof Value)
			{
				declare((Value)atom);
			}
			else
			{
				validate(atom);
			}
		}
	}
	
	void validate(Expression expression) {
		if (expression instanceof Value)
		{
			var value = (Value) expression;
			
			checkDeclared(value);
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var entity = cell.getEntity();
			var component = cell.getComponent();
			
			checkDeclared(entity);
			
			infer(component.getName(), cell, null, entity, component);
		}
		else if (expression instanceof Binary)
		{
			var binary = (Binary) expression;
			infer(binary.getOperator(), binary, BINARY__OPERATOR, binary.getLeft(), binary.getRight());
		}
		else if (expression instanceof Unary)
		{
			var unary = (Unary) expression;
			infer(unary.getOperator(), expression, UNARY__OPERATOR, unary.getExpression());
		}
		else if (expression instanceof Application)
		{
			var application = (Application) expression;
			infer(application.getName(), application, APPLICATION__NAME, (Expression[]) application.getArguments().toArray());
		}
	}
	
	void infer(String name, EObject expression, EStructuralFeature feature, Expression... arguments)
	{
		var standard = language.functions.get(name);
		if (standard != null)
		{
			var parameters = standard.type.getParameters();
			var result = standard.type.getReturnType();
			
			var variables = new HashMap<String, ArrayList<Expression>>();
			
			if (parameters.length == arguments.length)
			{
				for (var i = 0; i < arguments.length; i++)
				{
					if (parameters[i] instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) parameters[i];
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add(arguments[i]);
					}
					else
					{
						set(arguments[i], parameters[i], "argument " + i + " of standard symbol " + name);
					}
				}
				if (expression instanceof Expression)
				{
					if (result instanceof TypeVariable)
					{
						var typeVariable = (TypeVariable) result;
						var typeName = typeVariable.getName();
						if (!variables.containsKey(typeName))
						{
							variables.put(typeName, new ArrayList<>());
						}
						variables.get(typeName).add((Expression)expression);
					}
					else
					{
						set ((Expression)expression, result, "result of standard symbol " + name);
					}
				}
				// Check for type variables
				for (var typeName : variables.keySet())
				{
					var expressions = variables.get(typeName);
					for (var i = 1; i < expressions.size(); i++)
					{
						group(expressions.get(0), expressions.get(i), GroupingReason.assignment);
					}
				}
			}
			else
			{
				//MError(undefined, expression, feature)
			}
		}
		else
		{
			//MError(undefined, expression, feature)
		}
		for (var argument : arguments)
		{
			validate(argument);
		}
	}
	
	void solve(File file) {
		
	}
	
	void set(Expression expression, Type type, String reason) {
		if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var component = cell.getComponent().getName();
			
			var group = groupOfComponent.get(component);
			
			if (group == null)
			{
				group = new Group();
				groupOfComponent.put(component, group);
				groupOfExpression.put(expression, group);
			}
			group.addEntry(expression, type, reason);
		}
		else
		{
			var group = groupOfExpression.get(expression);
			
			if (group == null)
			{
				group = new Group();
				groupOfExpression.put(expression, group);
			}
			
			group.addEntry(expression, type, reason);
		}
	}
	
	void group(Expression a, Expression b, GroupingReason reason) {
		var involvedGroups = new HashSet<Group>();
		involvedGroups.add(groupOfExpression.get(a));
		involvedGroups.add(groupOfExpression.get(b));
		
		if (a instanceof Cell)
		{
			involvedGroups.add(groupOfComponent.get(((Cell) a).getComponent().getName()));
		}
		if (b instanceof Cell)
		{
			involvedGroups.add(groupOfComponent.get(((Cell) b).getComponent().getName()));
		}
		if (involvedGroups.contains(null))
		{
			involvedGroups.remove(null);
		}
		
		var extra = new Group();
		extra.addEntry(a, reason.toString());
		extra.addEntry(b, reason.toString());
		involvedGroups.add(extra);
		
		var g = new Group();
		for (var group : involvedGroups)
		{
			for (var expression : group.expressions.keySet())
			{
				groupOfExpression.put(expression,g);
				if (expression instanceof Cell)
				{
					groupOfComponent.put(((Cell) expression).getComponent().getName(), g);
				}
			}
			g.expressions.putAll(group.expressions);
			g.expressions.putAll(group.expressions);
		}
	}
	
	void declare(Value value)
	{
		if (userValues.containsKey(value.getName()) || userComponents.containsKey(value.getName()) || standardSymbols.containsKey(value.getName()))
		{
			
		}
		else
		{
			userValues.put(value.getName(), value);
		}
	}
	
	void checkDeclared(Value value)
	{
		var name = value.getName();
		if (!userValues.containsKey(name))
		{
			// error
		}
		else
		{
			group(value, userValues.get(name), GroupingReason.sameVariable);
		}
	}
}