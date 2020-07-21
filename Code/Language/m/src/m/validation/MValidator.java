package m.validation;

import static m.library.Symbol.QUERY;
import static m.m.MPackage.Literals.APPLICATION__NAME;
import static m.m.MPackage.Literals.BINARY__OPERATOR;
import static m.m.MPackage.Literals.BLOCK__NAME;
import static m.m.MPackage.Literals.UNARY__OPERATOR;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.Check;

import m.generator.Game;
import m.library.Language;
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
import m.types.AtomicType;
import m.types.FunctionType;
import m.types.TypeVariable;

enum MError
{
	redefinition, undefined, syntax, undecidable, incompatible
}



public class MValidator extends AbstractMValidator
{
	Language language;
	ExpressionForest expressionForest;
	Context context;
	
	class Problem
	{
		public boolean isError;
		public String message;
		public EObject o;
		public EStructuralFeature feature;
	}
	
	public static Game game;
	
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
		language = Language.ENGLISH;
		expressionForest = new ExpressionForest();
		context = new Context(expressionForest);
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
					context.push();
					context.declare(value);
					for (var s : block.getStatements())
					{
						validate(s);
					}
					context.pop();
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
				context.declare((Value)atom);
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
			
			context.checkDeclared(value);
		}
		else if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			var entity = cell.getEntity();
			var component = cell.getComponent();
			
			context.checkDeclared(entity);
			
			infer(component.getName(), cell, null, entity);
		}
		else if (expression instanceof Binary)
		{
			var binary = (Binary) expression;
			infer(binary.getOperator(), binary, BINARY__OPERATOR, binary.getLeft(), binary.getRight());
			validate(binary.getLeft());
			validate(binary.getRight());
		}
		else if (expression instanceof Unary)
		{
			var unary = (Unary) expression;
			infer(unary.getOperator(), expression, UNARY__OPERATOR, unary.getExpression());
			validate(unary.getExpression());
		}
		else if (expression instanceof Application)
		{
			var application = (Application) expression;
			infer(application.getName(), application, APPLICATION__NAME, (Expression[]) application.getArguments().toArray());

			for (var argument : application.getArguments())
			{
				validate(argument);
			}
		}
	}
	
	void infer(String name, EObject expression, EStructuralFeature feature, Expression... arguments)
	{
		var standard = language.functions.get(name);
		if (standard == null)
		{
			standard = language.components.get(name);
		}
		if (standard == null)
		{
			standard = language.blocks.get(name);
		}
		if (standard != null)
		{
			var type = standard.getType();
			if (type instanceof AtomicType) {
				if (arguments.length == 1)
				{
					if (language.blocks.containsKey(name))
					{
						expressionForest.set(arguments[0], type, new TypingReason(standard, 1));
					}
					else if (language.components.containsKey(name))
					{
						expressionForest.set((Expression)expression, type, new TypingReason(standard, true));
					}
				}
				else
				{
					// error
				}
			}
			else if (type instanceof FunctionType)
			{
				var f = (FunctionType) type;
				var parameters = f.getParameters();
				var result = f.getReturnType();
				
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
							expressionForest.set(arguments[i], parameters[i], new TypingReason(standard, i+1));
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
							expressionForest.set ((Expression)expression, result, new TypingReason(standard, true));
						}
					}
					// Check for type variables
					for (var typeName : variables.keySet())
					{
						var expressions = variables.get(typeName);
						for (var i = 1; i < expressions.size(); i++)
						{
							expressionForest.group(expressions.get(0), expressions.get(i), BindingReason.polymorphism);
						}
					}
				}
			}
			else
			{
				//MError(undefined, expression, feature)
			}
		}
		else if (expression instanceof Cell)
		{
			expressionForest.note((Cell)expression);
		}
		else
		{
			//MError(undefined, expression, feature)
		}
	}
	
	void solve(File file) {
		for (var criticalNode : expressionForest.criticalNodes)
		{
			var originalType = criticalNode.type;
			
			var root = criticalNode;
			while (root.parent != null) {
				root = root.parent;
			}
			
			var rootType = root.type;
			
			if (originalType == null && root.type == null)
			{
				System.out.println(criticalNode.expression + " is indetermined");
			}
			else if (originalType != null && originalType != root.type)
			{
				System.out.println(criticalNode.expression + " is incompatible: " + originalType + " " + rootType);
			}
		}
		for (var component : expressionForest.nodeOfComponent.keySet())
		{
			var root = expressionForest.nodeOfComponent.get(component);
			while (root.parent != null)
			{
				if (root.parent != null)
				{
					System.out.println("Linked to " + root.parent.expression + " because " + root.reason);
				}
				root = root.parent;
			}
			System.out.println(component + " has type " + root.type + " because " + root.typingReason);
		}
	}
}