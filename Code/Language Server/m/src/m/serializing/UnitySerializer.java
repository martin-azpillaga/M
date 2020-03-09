package m.serializing;

import static m.csharp.Modifier.*;
import static m.serializing.CSharpHelper.*;
import java.util.*;

import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.serializer.impl.Serializer;
import com.google.inject.Guice;

import m.csharp.AdditiveKind;
import m.csharp.ComparisonKind;
import m.csharp.CompilationUnit;
import m.csharp.EqualityKind;
import m.csharp.Modifier;
import m.csharp.MultiplicativeKind;
import m.csharp.Parameter;
import game.Addition;
import game.And;
import game.Assignment;
import game.Brackets;
import game.Call;
import game.Cardinal;
import game.Cell;
import game.Comparison;
import game.ComponentData;
import game.Equality;
import game.Function;
import game.Game;
import game.ImplicitSet;
import game.Join;
import game.LogicalNot;
import game.Multiplication;
import game.Or;
import game.Selection;
import game.SetExpression;
import game.Statement;
import game.System;
import game.Variable;
import game.Expression;
import game.Forall;
import m.CSharpRuntimeModule;
import m.library.Component;
import m.library.SimpleType;
import m.validation.MValidator;
import m.serializing.GameHelper;

public class UnitySerializer
{
	CSharpRuntimeModule csharpModule;
	IFileSystemAccess2 fsa;
	
	public void serialize(Game game, IFileSystemAccess2 fsa)
	{
		csharpModule = new CSharpRuntimeModule();
		this.fsa = fsa;
		
		for (var component : game.getComponents())
		{
			//serialize(component);
		}
		
		for (var system : game.getSystems())
		{
			//serialize(system);
		}
	}
	/*
	private void serialize(ComponentData component)
	{
		var name = component.getName();
		var type = component.getType();
		
		var unit = unit();
		
		var namespaces = new HashSet<String>();
		namespaces.add("Unity.Entities");
		namespaces.add(type.getNamespace());
		
		if (type.isValueType())
		{
			if (type.getName().equals("DynamicBuffer"))
			{
				if (type.getParameters().size() == 1 && type.getParameters().get(0).getName().equals("Entity"))
				{
					namespaces.add("UnityEngine");
					
					var struct = struct(new Modifier[] {PUBLIC}, name, new String[] {"IBufferElementData", "IContain<"+type+">"});
					struct.getMembers().add(property("Entity", "Value"));
					
					var clazz = clazz(new Modifier[] {PUBLIC}, name+"Authoring", new String[] {"MonoBehaviour", "IConvertGameObjectToEntity", "IDeclareReferencedPrefabs"});
					clazz.getMembers().add(field(new Modifier[] {PUBLIC}, "List<GameObject", declarator("Value")));
					
					var convert = method(new Modifier[] {PUBLIC}, "void", "Convert",new Parameter[] {parameter("Entity", "entity"), parameter("EntityManager", "entityManager"), parameter("GameObjectConversionSystem", "gameObjectConversionSystem")});
					clazz.getMembers().add(convert);
					
					convert.getStatements().add(declaration(declarator("buffer", access(variable("entityManager"), function("AddBuffer", new String[] {name}, argument(variable("entity")))))));
					var foreach = foreach("v", variable("Value"));
					var converted = access(variable("gameObjectConversionSystem"),function("GetPrimaryEntity", argument(variable("v"))));
					foreach.getStatements().add(statement(access(variable("buffer"),function("Add", argument(creation(name, member("Value", converted)))))));
					convert.getStatements().add(foreach);
					
					var declare = method(new Modifier[] {PUBLIC}, "void", "DeclareReferencedPrefabs", new Parameter[] {parameter("List<GameObject>", "prefabs")});
					var foreach2 = foreach("v", variable("Value"));
					foreach2.getStatements().add(statement(access(variable("prefabs"),function("Add", argument(variable("v"))))));
					declare.getStatements().add(foreach2);
					
					unit.getTypes().add(struct);
					unit.getTypes().add(clazz);
				}
			}
			else
			{
				var struct = struct(new Modifier[] {PUBLIC}, name, new String[] {"IComponentData"});
				struct.getAttributes().add(attribute("GenerateAuthoringComponent"));
				struct.getMembers().add(field(new Modifier[] {PUBLIC}, type.getName(), declarator("Value")));
				unit.getTypes().add(struct);
			}
		}
		else
		{			
			var clazz = clazz(new Modifier[] {PUBLIC}, name, new String[] {"IComponentData"});
			clazz.getMembers().add(field(new Modifier[] {PUBLIC}, name, declarator("Value")));
			clazz.getAttributes().add(attribute("GenerateAuthoringComponent"));
			unit.getTypes().add(clazz);
		}

		for (var namespace : namespaces)
		{
			if (namespace != null)
			{
				unit.getUsings().add(namespaceUsing(namespace));			
			}
		}
		generate(unit, "Code/Components/"+name+"Authoring.cs");
	}
	
	private void serialize(System system)
	{
		var unit = unit();
		var namespaces = new HashSet<String>();
		var querySet = new QuerySet();
		
		unit.getUsings().add(staticUsing("Unity.Collections.Allocator"));

		namespaces.add("Unity.Entities");
		namespaces.add("Unity.Jobs");
		
		var clazz = clazz(new Modifier[] {PUBLIC}, system.getName(), new String[] {"SystemBase"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.csharp.Expression[] {typeof("Gameplay")}));
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED, OVERRIDE}, "void", "OnUpdate");
		clazz.getMembers().add(onUpdate);
		
		addStatements(system.getStatements(), onUpdate.getStatements(), querySet, namespaces);
		
		if (querySet.queries.size() > 0)
		{
			unit.getUsings().add(staticUsing("Unity.Entities.ComponentType"));
			
			var onCreate = method(new Modifier[] {PROTECTED, OVERRIDE}, "void", "OnCreate");
			clazz.getMembers().add(0,onCreate);
			
			for (var queryKey : querySet.queries.keySet())
			{
				clazz.getMembers().add(0,field("EntityQuery", declarator(queryKey)));
				
				var query = querySet.queries.get(queryKey);
				
				var function = function("GetEntityQuery");
				var get = statement(assignment(variable(queryKey),function));
				onCreate.getStatements().add(get);
				
				for (var component : query.keySet())
				{
					if (component != "Entity")
					{
						var accesses = query.get(component);
						var totalAccess = "ReadOnly";
						if (accesses.contains(AccessKind.write))
						{
							totalAccess = "ReadWrite";
						}
						
						function.getArguments().add(argument(function(totalAccess, new String[] {unityName(component, namespaces)})));
					}
				}
				if (query.isEmpty() || (query.size() == 1 && query.containsKey("Entity")))
				{
					function.getArguments().add(argument(creation("ComponentType[]")));
				}
			}
		}
		
		for (var namespace : namespaces)
		{
			if (namespace != null)
			{
				unit.getUsings().add(namespaceUsing(namespace));
			}
		}
		
		generate(unit, "Code/Systems/"+system.getName()+".cs");
	}
	
	private void addStatements(List<Statement> statements, List<m.csharp.Statement> list, QuerySet querySet, HashSet<String> namespaces)
	{
		var tail = new ArrayList<m.csharp.Statement>();
		
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				
				var variable = forall.getVariable().getName();
				var conditionExpression = forall.getCondition();
				
				var loop = forStatement(declaration(declarator("i_"+variable, floatLiteral("0"))),comparison(variable("i_"+variable), ComparisonKind.LOWER, access(variable(variable),function("CalculateEntityCount"))),increment(variable("i_"+variable)));
				list.add(loop);
				
				var block = loop.getStatements();
				
				if (conditionExpression != null)
				{
					if (!(conditionExpression instanceof Function && ((Function)conditionExpression).getName().equals("has")))
					{
						var selection = csharp.createSelection();
						var first = csharp.createBranch();
						selection.getBranches().add(first);
						first.setCondition(cs(conditionExpression,querySet,namespaces));
						loop.getStatements().add(selection);
						block = first.getStatements();
					}
					else
					{
						// Hack for executing access inference
						cs(conditionExpression, querySet, namespaces);
					}
				}
				
				addStatements(forall.getStatements(), block, querySet, namespaces);
				
				var myQuery = querySet.queries.get(variable);
				if (myQuery != null)
				{
					for (var component : myQuery.keySet())
					{
						if (!myQuery.get(component).contains(AccessKind.tag))
						{
							var type = MValidator.components.get(component);
							if (type.isValueType)
							{
								list.add(0,declaration(declarator(component+"s_"+variable, access(variable(variable),function("ToComponentDataArray", new String[] {unityName(component, namespaces)}, argument(variable("TempJob")))))));
								loop.getStatements().add(0,declaration(declarator(component+"_"+variable, index(component+"s_"+variable, variable("i_"+variable)))));
								list.add(statement(access(variable(component+"s_"+variable),function("Dispose"))));
							}
							else if (type.isCollection)
							{
								loop.getStatements().add(0, declaration(declarator(component+"_"+variable,access(variable("EntityManager"),function("GetBuffer", new String[] {unityName(component, namespaces)}, argument(variable("entity_"+variable)))))));
							}
							else
							{
								list.add(0,declaration(declarator(component+"s_"+variable, access(variable(variable),function("ToComponentDataArray", new String[] {unityName(component, namespaces)})))));
								loop.getStatements().add(0,declaration(declarator(component+"_"+variable, index(component+"s_"+variable, variable("i_"+variable)))));
							}
							
						}
					}
					list.add(0,declaration(declarator("entities_"+variable, access(variable(variable),function("ToEntityArray", argument(variable("TempJob")))))));
					loop.getStatements().add(0,declaration(declarator("entity_"+variable, index("entities_"+variable, variable("i_"+variable)))));
					list.add(statement(access(variable("entities_"+variable),function("Dispose"))));
				}
			}
			else if (statement instanceof Selection)
			{
				var selection = (Selection) statement;
				var cs = csharp.createSelection();
				
				var csBranch = csharp.createBranch();
				cs.getBranches().add(csBranch);
				csBranch.setCondition(cs(selection.getCondition(), querySet, namespaces));
				addStatements(selection.getPositiveStatements(), csBranch.getStatements(), querySet, namespaces);
				
				list.add(cs);
			}
			else if (statement instanceof Assignment)
			{
				var assignment = (Assignment) statement;
				var atom = assignment.getAtom();
				var expression = assignment.getExpression();
				
				if (atom instanceof Variable)
				{
					var a = (Variable) atom;
					var ass = declarator(a.getName());
					var declaration = declaration(ass);
					var cs = declaration;
					
					if (expression instanceof ImplicitSet)
					{
						var set = (ImplicitSet) expression;
						var predicate = set.getPredicate();
						var entity = set.getVariable();
						
						var creation = csharp.createCreation();
						var allocatorArgument = csharp.createArgument();
						var untilTempJob = csharp.createAccessExpression();
						var allocator = csharp.createVariable();
						var tempJob = csharp.createVariable();
						creation.getArguments().add(allocatorArgument);
						allocatorArgument.setValue(untilTempJob);
						untilTempJob.setLeft(allocator);
						untilTempJob.setRight(tempJob);
						creation.setType("NativeList<Entity>");
						namespaces.add("Unity.Collections");
						ass.setValue(creation);
						allocator.setName("Allocator");
						tempJob.setName("TempJob");
						
						list.add(cs);
						
						var runStatement = csharp.createExpressionStatement();
						var run = csharp.createAccessExpression();
						var runCall = csharp.createParameterizedFunction();
						var withoutBurst = csharp.createAccessExpression();
						var withoutBurstCall = csharp.createParameterizedFunction();
						var foreach = csharp.createAccessExpression();
						var foreachCall = csharp.createParameterizedFunction();
						var lambdaArgument = csharp.createArgument();
						var lambda = csharp.createLambda();
						var entities = csharp.createVariable();
						list.add(runStatement);
						runStatement.setExpression(run);
						run.setLeft(withoutBurst);
						run.setRight(runCall);
						withoutBurst.setLeft(foreach);
						withoutBurst.setRight(withoutBurstCall);
						foreach.setLeft(entities);
						foreach.setRight(foreachCall);
						foreachCall.getArguments().add(lambdaArgument);
						lambdaArgument.setValue(lambda);
						entities.setName("Entities");
						foreachCall.setName("ForEach");
						withoutBurstCall.setName("WithoutBurst");
						runCall.setName("Run");
						
						var selection = csharp.createSelection();
						var first = csharp.createBranch();
						selection.getBranches().add(first);
						first.setCondition(cs(predicate, querySet, namespaces));
						lambda.getStatements().add(selection);
						
						var addStatement = csharp.createExpressionStatement();
						var untilAdd = csharp.createAccessExpression();
						var nativeList = csharp.createVariable();
						var add = csharp.createParameterizedFunction();
						var argument = csharp.createArgument();
						var entityArgument = csharp.createVariable();
						addStatement.setExpression(untilAdd);
						first.getStatements().add(addStatement);
						untilAdd.setLeft(nativeList);
						untilAdd.setRight(add);
						nativeList.setName(a.getName());
						add.setName("Add");
						add.getArguments().add(argument);
						argument.setValue(entityArgument);
						entityArgument.setName("entity_"+entity);
						
						querySet.add(entity, "Entity", AccessKind.read);
						
						var myQuery = querySet.queries.get(entity);
						if (myQuery != null)
						{
							for (var component : myQuery.keySet())
							{
								if (!component.equals("Entity"))
								{
									var parameter = csharp.createParameter();
									parameter.setType(unityName(component, namespaces));
									
									parameter.setName(component+"_"+entity);
									
									if (myQuery.get(component).contains(AccessKind.write) && isValueType(component))
									{
										parameter.setRef(true);
										lambda.getParameters().add(lambda.getParameters().size(), parameter);
									}
									else
									{
										lambda.getParameters().add(0, parameter);
									}
								}
							}
							if (myQuery.containsKey("Entity"))
							{
								var parameter = csharp.createParameter();
								parameter.setType("Entity");
								parameter.setName("entity_"+entity);
								lambda.getParameters().add(0, parameter);
							}
						}
						
						var disposeStatement = csharp.createExpressionStatement();
						var untilDispose = csharp.createAccessExpression();
						var array = csharp.createVariable();
						var dispose = csharp.createParameterizedFunction();
						disposeStatement.setExpression(untilDispose);
						untilDispose.setLeft(array);
						untilDispose.setRight(dispose);
						array.setName(a.getName());
						dispose.setName("Dispose");
						tail.add(disposeStatement);
					}
					else
					{
						ass.setValue(cs(expression, querySet, namespaces));
						list.add(cs);
					}
				}
				else if (atom instanceof Cell)
				{
					var access = (Cell) atom;
					var entity = access.getEntity();
					var component = access.getComponent();
					querySet.add(entity, component, AccessKind.write);
					
					var csStatement = csharp.createExpressionStatement();
					var csAssignment = csharp.createAssignment();
					var left = access(variable(access.getComponent()+"_"+access.getEntity()),variable(fieldName(access.getComponent())));
					var right = cs(expression, querySet, namespaces);
					list.add(csStatement);
					csStatement.setExpression(csAssignment);
					csAssignment.setLeft(left);
					csAssignment.setRight(right);
					if (MValidator.components.get(component).isValueType)
					{
						list.add(statement(access(variable("EntityManager"),function("SetComponentData", argument(variable("entity_"+entity)), argument(variable(component+"_"+entity))))));
					}
				}
			}
			else if (statement instanceof Call)
			{
				var call = (Call) statement;
				var cs = csharp.createExpressionStatement();
				cs.setExpression(cs(call.getFunction(), querySet, namespaces));
				list.add(cs);
			}
		}
		
		for (var t : tail)
		{
			list.add(t);
		}
	}
	
	private m.csharp.Expression cs(Expression expression, QuerySet querySet, HashSet<String> namespaces)
	{
		if (expression instanceof Or)
		{
			var e = (Or) expression;
			var cs = csharp.createLogicalOr();
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof And)
		{
			var e = (And) expression;
			var cs = csharp.createLogicalAnd();
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Equality)
		{
			var e = (Equality) expression;
			var cs = csharp.createEquality();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Comparison)
		{
			var e = (Comparison) expression;
			var cs = csharp.createComparison();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Addition)
		{
			var e = (Addition) expression;
			var cs = csharp.createAdditiveExpression();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Multiplication)
		{
			var e = (Multiplication) expression;
			var cs = csharp.createMultiplicativeExpression();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof SetExpression)
		{
			var e = (SetExpression) expression;
			var kind = e.getKind();
			switch(kind)
			{
			case MEMBERSHIP:
				var name = ((Variable)e.getLeft()).getName();
				//querySet.add(name, "Entity", AccessKind.read);
				var buffer = cs(e.getRight(), querySet, namespaces);
				
				return access(buffer, function("Contains",argument(variable("entity_"+name))));
			case DIFFERENCE:
				break;
			case INTERSECTION:
				break;
			case UNION:
				break;
			default:
				break;
			}
		}
		else if (expression instanceof LogicalNot)
		{
			var e = (LogicalNot) expression;
			return not(brackets(cs(e.getExpression(), querySet, namespaces)));
		}
		else if (expression instanceof Brackets)
		{
			var e = (Brackets) expression;
			return brackets(cs(e.getExpression(),querySet, namespaces));
		}
		else if (expression instanceof Join)
		{
			var e = (Join) expression;
			namespaces.add("Unity.Mathematics");
			var argument0 = cs(e.getEntries().get(0), querySet, namespaces);
			var argument1 = cs(e.getEntries().get(1), querySet, namespaces);
			var argument2 = cs(e.getEntries().get(2), querySet, namespaces);
			
			return creation("float3",argument(argument0),argument(argument1), argument(argument2));
		}
		else if (expression instanceof Cardinal)
		{
			var e = (Cardinal) expression;
			return access(cs(e.getExpression(), querySet, namespaces), variable("Length"));
		}
		else if (expression instanceof Call)
		{
			var e = (Call) expression;
			var name = e.getName();
			
			if (name.equals("has"))
			{
				var parameter0 = ((Variable)e.getArguments().get(0)).getName();
				var parameter1 = ((Variable)e.getArguments().get(1)).getName();
				querySet.add(parameter1, parameter0, AccessKind.tag);
				var cs = csharp.createBooleanLiteral();
				cs.setValue("true");
				return cs;
			}
			else if (name.equals("sin")||name.equals("cos")||name.equals("tan")||name.equals("exp")||name.equals("log"))
			{
				namespaces.add("Unity.Mathematics");
				return access(variable("math"),function(e.getName(),argument(cs(e.getArguments().get(0),querySet,namespaces))));
			}
			else if (name.equals("random"))
			{
				var library = access(variable("UnityEngine"),variable("Random"));
				var x = argument(access(cs(e.getArguments().get(0), querySet, namespaces),variable("x")));
				var y = argument(access(cs(e.getArguments().get(0), querySet, namespaces),variable("y")));
				return access(library,function("Range", x, y));
			}
			else if (name.equals("create"))
			{
				var prefab = cs(e.getArguments().get(0), querySet, namespaces);
				
				return access(variable("EntityManager"),function("Instantiate", argument(prefab)));
			}
			else if (name.equals("delete"))
			{
				var entityArgument = e.getArguments().get(0);
				var prefab = cs(entityArgument, querySet, namespaces);
				if (entityArgument instanceof Variable)
				{
					prefab = variable("entity_" + ((Variable)entityArgument).getName());
				}
				var variable = ((Variable)e.getArguments().get(0)).getName();
				querySet.addEmpty(variable);
				return access(variable("EntityManager"), function("DestroyEntityAndChildren", argument(prefab)));
			}
			else if (name.equals("add"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				
				querySet.addEmpty(variable);
				return access(variable("EntityManager"),function("AddComponent", new String[] {component}, argument(variable("entity_"+variable))));
			}
			else if (name.equals("remove"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				
				querySet.add(variable, component, AccessKind.read);
				
				return access(variable("EntityManager"),function("RemoveComponent", new String[] {component}, argument(variable("entity_"+variable))));
			}
			else if (name.equals("x"))
			{
				return access(cs(e.getArguments().get(0), querySet, namespaces), variable("x"));
			}
			else if (name.equals("y"))
			{
				return access(cs(e.getArguments().get(0), querySet, namespaces), variable("y"));
			}
			else if (name.equals("z"))
			{
				return access(cs(e.getArguments().get(0), querySet, namespaces), variable("z"));
			}
			else if (name.equals("w"))
			{
				return access(cs(e.getArguments().get(0), querySet, namespaces), variable("w"));
			}
			else if (name.equals("write"))
			{
				namespaces.add("UnityEngine");
				return access(variable("Debug"),function("Log", argument(cs(e.getArguments().get(0), querySet, namespaces))));
			}
			else if (name.equals("halt"))
			{
				namespaces.add("UnityEditor");
				return assignment(access(variable("EditorApplication"),variable("isPlaying")),booleanLiteral("false"));
			}
			else if (name.equals("load"))
			{
				var world = cs(e.getArguments().get(0), querySet, namespaces);
				namespaces.add("Unity.Scenes");
				return access(access(variable("World"),function("GetOrCreateSystem", new String[] {"SceneSystem"})),function("LoadSceneAsync",argument(access(world,variable("SceneGUID")))));
			}
			else if (name.equals("unload"))
			{
				var world = cs(e.getArguments().get(0), querySet, namespaces);
				namespaces.add("Unity.Scenes");
				return access(access(variable("World"),function("GetOrCreateSystem", new String[] {"SceneSystem"})),function("UnloadScene",argument(access(world,variable("SceneGUID")))));
			}
			else if (name.equals("enable"))
			{
				var system = ((Variable)e.getArguments().get(0)).getName();
				return assignment(access(access(variable("World"),function("GetOrCreateSystem", new String[] {system})),variable("Enabled")),booleanLiteral("true"));
			}
			else if (name.equals("disable"))
			{
				var system = ((Variable)e.getArguments().get(0)).getName();
				return assignment(access(access(variable("World"),function("GetOrCreateSystem", new String[] {system})),variable("Enabled")),booleanLiteral("false"));
			}
		}
		else if (expression instanceof Variable)
		{
			var e = (Variable) expression;
			return variable(e.getName());
		}
		else if (expression instanceof Cell)
		{
			var access = (Cell) expression;
			querySet.add(access.getEntity().getName(), access.getComponent().getName(), AccessKind.read);
			var csExpression = csharp.createAccessExpression();
			var component = csharp.createVariable();
			var field = csharp.createVariable();
			csExpression.setLeft(component);
			csExpression.setRight(field);
			component.setName(access.getComponent()+"_"+access.getEntity());
			field.setName(fieldName(access.getComponent().getName()));
			
			return csExpression;
		}
		else if (expression instanceof ImplicitSet)
		{
			var e = (ImplicitSet) expression;
			var variable = e.getVariable();
			var predicate = e.getPredicate();
			
			var selection = csharp.createSelection();
		}
		return null;
	}
	
	private void generate(CompilationUnit unit, String path)
	{
		var resource = new XtextResource();
		resource.getContents().add(unit);
		
		var injector = Guice.createInjector(csharpModule);
		
		var serializer = injector.getInstance(Serializer.class);
		var options = SaveOptions.newBuilder().format().getOptions();
		var serialized = serializer.serialize(unit, options);

		fsa.generateFile("Unity/Assets/"+path, serialized);
	}
	
	private m.csharp.ComparisonKind cs(game.ComparisonKind kind)
	{
		switch (kind)
		{
		case GREATER: return ComparisonKind.GREATER;
		case GREATER_OR_EQUAL: return ComparisonKind.GREATER_OR_EQUAL;
		case LOWER: return ComparisonKind.LOWER;
		case LOWER_OR_EQUAL: return ComparisonKind.LOWER_OR_EQUAL;
		}
		return ComparisonKind.GREATER;
	}
	
	private EqualityKind cs(game.EqualityKind kind)
	{
		switch(kind)
		{
		case EQUAL: return EqualityKind.EQUAL;
		case NOT_EQUAL: return EqualityKind.NOT_EQUAL;
		}
		return EqualityKind.EQUAL;
	}
	
	private AdditiveKind cs(game.AdditiveKind kind)
	{
		switch (kind)
		{
		case ADD: return AdditiveKind.ADD;
		case SUBTRACT: return AdditiveKind.SUBTRACT;
		default: return AdditiveKind.ADD;
		}
	}
	
	private MultiplicativeKind cs(game.MultiplicativeKind kind)
	{
		switch (kind)
		{
		case MULTIPLY: return MultiplicativeKind.MULTIPLY;
		case DIVIDE: return MultiplicativeKind.DIVIDE;
		default: return MultiplicativeKind.DIVIDE;
		}
	}
	
	private boolean isValueType(String component)
	{
		var engineComponents = Component.values();
		for (var engineComponent : engineComponents)
		{
			if (component.equals(engineComponent.toString()))
			{
				return engineComponent.isValueType;
			}
		}
		return MValidator.components.get(component).isValueType;
	}
	
	private String unityName(String component, HashSet<String> namespaces)
	{
		var engineComponents = Component.values();
		var name = component;
		for (var engineComponent : engineComponents)
		{
			if (component.equals(engineComponent.toString()))
			{
				namespaces.add(engineComponent.namespace);
				name = engineComponent.unityType;
			}
		}
		return name;
	}
	
	private String fieldName(String component)
	{
		var engineComponents = Component.values();
		for (var engineComponent : engineComponents)
		{
			if (component.equals(engineComponent.toString()))
			{
				return engineComponent.unityField;
			}
		}
		return "Value";
	}
}

class QuerySet
{
	public HashMap<String,HashMap<String, HashSet<AccessKind>>> queries;
	
	public QuerySet()
	{
		queries = new HashMap<String,HashMap<String, HashSet<AccessKind>>>();
	}
	
	public void add(String entity, String component, AccessKind kind)
	{
		if (!queries.containsKey(entity))
		{
			queries.put(entity, new HashMap<String,HashSet<AccessKind>>());
		}
		var query = queries.get(entity);
		
		if (!query.containsKey(component))
		{
			query.put(component, new HashSet<AccessKind>());
		}
		query.get(component).add(kind);
	}
	public void addEmpty(String entity)
	{
		if (!queries.containsKey(entity))
		{
			queries.put(entity, new HashMap<String,HashSet<AccessKind>>());
		}
	}*/
}

enum AccessKind
{
	read, write, subtract, tag
}
