package m.serializing;

import static m.csharp.Modifier.*;
import static m.validation.Type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.JSONRuntimeModule;
import m.YAMLRuntimeModule;
import m.csharp.CompilationUnit;
import m.csharp.CsharpFactory;
import m.csharp.ParameterizedFunction;
import m.json.JsonFactory;
import m.json.Member;
import m.m.Archetype;
import m.m.Assignment;
import m.m.ComponentAccess;
import m.m.Exists;
import m.m.Forall;
import m.m.Game;
import m.m.System;
import m.modular.AdditiveExpression;
import m.modular.AssignmentKind;
import m.modular.Brackets;
import m.modular.Comparison;
import m.modular.ComparisonKind;
import m.modular.Equality;
import m.modular.Expression;
import m.modular.Function;
import m.modular.LogicalAnd;
import m.modular.LogicalNot;
import m.modular.LogicalOr;
import m.modular.ModularFactory;
import m.modular.MultiplicativeExpression;
import m.modular.Statement;
import m.modular.Variable;
import m.validation.MValidator;
import m.validation.StandardLibrary;
import m.validation.Type;
import m.yaml.Tag;
import m.yaml.Version;
import m.yaml.YamlFactory;

public class UnitySerializer
{
	ModularFactory modular = ModularFactory.eINSTANCE;
	CsharpFactory csharp = CsharpFactory.eINSTANCE;
	YamlFactory yaml = YamlFactory.eINSTANCE;
	JsonFactory json = JsonFactory.eINSTANCE;
	
	CSharpRuntimeModule csharpModule;
	YAMLRuntimeModule yamlModule;
	JSONRuntimeModule jsonModule;
	
	IFileSystemAccess2 fsa;
	
	public void serialize(Game game, IFileSystemAccess2 fsa)
	{
		csharpModule = new CSharpRuntimeModule();
		yamlModule = new YAMLRuntimeModule();
		jsonModule = new JSONRuntimeModule();
		this.fsa = fsa;
		
		packagesManifest();
		
		var components = MValidator.components;
		
		for (var component : components.keySet())
		{
			try 
			{
				StandardLibrary.valueOf(component);
			}
			catch (Exception exception)
			{
				var type = components.get(component);
				serialize(component, type);
			}
		}
		serialize("Collisions", entityList);
		serialize("CollisionEntries", entityList);
		serialize("CollisionExits", entityList);
		
		for (var archetype : game.getArchetypes())
		{
			serialize(archetype);
		}
		
		for (var system : game.getSystems())
		{
			serialize(system);
		}
		
		extras();
	}
	
	private void packagesManifest()
	{
		var file = json.createObject();
		var dependencies = json.createMember();
		dependencies.setName("dependencies");
		var list = json.createObject();
		dependencies.setValue(list);

		var members = list.getMembers();
		var modules = new String[]{"ai","androidjni","animation","assetbundle","audio","cloth","director","imageconversion","imgui","jsonserialize","particlesystem","physics","physics2d","screencapture","terrain","terrainphysics","tilemap","ui","uielements","umbra","unityanalytics","unitywebrequest","unitywebrequestassetbundle","unitywebrequestaudio","unitywebrequesttexture","unitywebrequestwww","vehicles","video","vr","wind","xr"};
		members.add(dependency("com.unity.entities","0.5.1-preview.11"));
		members.add(dependency("com.unity.jobs","0.2.4-preview.11"));
		members.add(dependency("com.unity.collections","0.5.1-preview.11"));
		members.add(dependency("com.unity.inputsystem","1.0.0-preview.4"));
		members.add(dependency("com.unity.dots.editor", "0.3.0-preview"));
		//members.add(dependency("com.unity.netcode","0.0.4-preview.0"));
		members.add(dependency("com.unity.physics","0.2.5-preview.1"));
		members.add(dependency("com.unity.rendering.hybrid","0.3.3-preview.11"));
		members.add(dependency("com.unity.test-framework","1.1.11"));
		members.add(dependency("com.unity.transport", "0.2.3-preview.0"));
		members.add(dependency("com.unity.ugui", "1.0.0"));
		members.add(dependency("com.unity.xr.legacyinputhelpers", "1.3.8"));
		members.add(dependency("com.unity.timeline", "1.2.10"));
		//members.add(dependency("com.unity.ide.rider","1.1.4"));
		//members.add(dependency("com.unity.ide.vscode", "1.1.4"));
		for (var module : modules)
		{
			members.add(dependency("com.unity.modules."+module,"1.0.0"));
		}
		file.getMembers().add(dependencies);
		
		GenericSerializer.generate(file, jsonModule, fsa, "Unity/Packages/manifest.json");
	}
	
	private Member dependency(String name, String version)
	{
		var member = json.createMember();
		member.setName(name);
		var value = json.createStringValue();
		value.setValue(version);
		member.setValue(value);
		return member;
	}
	
	public void serialize(String component, Type type)
	{
		CompilationUnit unit = csharp.createCompilationUnit();
		
		var namespaces = new HashSet<String>();
		namespaces.add("Unity.Entities");
		
		if (type.isNumeric())
		{
			if (type != float1)
			{
				namespaces.add("Unity.Mathematics");
			}
			
			var struct = csharp.createStruct();
			struct.getModifiers().add(PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IComponentData");
			
			var attributeSection = csharp.createAttributeSection();
			var generate = csharp.createAttribute();
			generate.setName("GenerateAuthoringComponent");
			attributeSection.getAttributes().add(generate);
			struct.getAttributes().add(attributeSection);
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setVariable("Value");
			struct.getMembers().add(value);
			
			unit.getTypes().add(struct);
		}
		else if (type == tag)
		{
			var struct = csharp.createStruct();
			struct.getModifiers().add(PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IComponentData");
			
			var attributeSection = csharp.createAttributeSection();
			var generate = csharp.createAttribute();
			generate.setName("GenerateAuthoringComponent");
			attributeSection.getAttributes().add(generate);
			struct.getAttributes().add(attributeSection);
			
			unit.getTypes().add(struct);
		}
		else if (type.isList())
		{
			namespaces.add("UnityEngine");
			namespaces.add("System.Collections.Generic");
			
			var struct = csharp.createStruct();
			struct.getModifiers().add(PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IBufferElementData");
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setVariable("Value");
			struct.getMembers().add(value);
			
			var clazz = csharp.createClass();
			clazz.getModifiers().add(PUBLIC);
			clazz.setName(component+"Authoring");
			clazz.getSuperTypes().add("MonoBehaviour");
			clazz.getSuperTypes().add("IConvertGameObjectToEntity");
			clazz.getSuperTypes().add("IDeclareReferencedPrefabs");
			
			var field = csharp.createField();
			var fieldDeclarator = csharp.createDeclarator();
			field.getDeclarators().add(fieldDeclarator);
			field.getModifiers().add(PUBLIC);
			field.setType("List<GameObject>");
			fieldDeclarator.setVariable("Value");
			clazz.getMembers().add(field);
			
			var method = csharp.createMethod();
			method.getModifiers().add(PUBLIC);
			method.setType("void");
			method.setName("Convert");
			clazz.getMembers().add(method);
			
			var entity = csharp.createParameter();
			entity.setType("Entity");
			entity.setName("entity");
			var entityManager = csharp.createParameter();
			entityManager.setType("EntityManager");
			entityManager.setName("entityManager");
			var conversionSystem = csharp.createParameter();
			conversionSystem.setType("GameObjectConversionSystem");
			conversionSystem.setName("gameObjectConversionSystem");
			method.getParameters().add(entity);
			method.getParameters().add(entityManager);
			method.getParameters().add(conversionSystem);
			
			var addbuffer = csharp.createDeclaration();
			var declareBuffer = csharp.createDeclarator();
			var addAccess = modular.createAccessExpression();
			var addAccessLeft = modular.createVariable();
			var addAccessRight = csharp.createParameterizedFunction();
			var addAccessArgument = csharp.createArgument();
			var addAccessValue = modular.createVariable();
			method.getStatements().add(addbuffer);
			addbuffer.getDeclarators().add(declareBuffer);
			declareBuffer.setValue(addAccess);
			addAccess.setLeft(addAccessLeft);
			addAccess.setRight(addAccessRight);
			addAccessRight.getArguments().add(addAccessArgument);
			addAccessArgument.setValue(addAccessValue);
			declareBuffer.setVariable("buffer");
			addAccessLeft.setName("entityManager");
			addAccessRight.setName("AddBuffer");
			addAccessRight.getTypes().add(component);
			addAccessValue.setName("entity");
			
			var foreachV = csharp.createForeach();
			var collectionV = modular.createVariable();
			method.getStatements().add(foreachV);
			foreachV.setCollection(collectionV);
			foreachV.setVariable("v");
			collectionV.setName("Value");
			
			var addElementStatement = csharp.createExpressionStatement();
			var addElement = modular.createAccessExpression();
			var addLeft = modular.createVariable();
			var addRight = csharp.createParameterizedFunction();
			var addArgument = csharp.createArgument();
			var creation = csharp.createCreation();
			foreachV.getStatements().add(addElementStatement);
			addElementStatement.setExpression(addElement);
			addElement.setLeft(addLeft);
			addElement.setRight(addRight);
			addRight.getArguments().add(addArgument);
			addArgument.setValue(creation);
			addLeft.setName("buffer");
			addRight.setName("Add");
			
			var valueMember = csharp.createMemberInitializer();
			var valueValue = modular.createAccessExpression();
			
			creation.getMembers().add(valueMember);
			creation.setType(component);
			valueMember.setName("Value");
			valueMember.setValue(valueValue);
			
			var convertLeft = modular.createVariable();
			var convertRight = csharp.createParameterizedFunction();
			var convertArgument = csharp.createArgument();
			var convertArgumentV = modular.createVariable();
			valueValue.setLeft(convertLeft);
			valueValue.setRight(convertRight);
			convertRight.getArguments().add(convertArgument);
			convertArgument.setValue(convertArgumentV);
			convertLeft.setName("gameObjectConversionSystem");
			convertRight.setName("GetPrimaryEntity");
			convertArgumentV.setName("v");
			
			var declare = csharp.createMethod();
			declare.getModifiers().add(PUBLIC);
			declare.setType("void");
			declare.setName("DeclareReferencedPrefabs");
			clazz.getMembers().add(declare);
			
			var referencedPrefabs = csharp.createParameter();
			referencedPrefabs.setType("List<GameObject>");
			referencedPrefabs.setName("referencedPrefabs");
			declare.getParameters().add(referencedPrefabs);
			
			var foreach = csharp.createForeach();
			foreach.setVariable("v");
			var collection = modular.createVariable();
			collection.setName("Value");
			foreach.setCollection(collection);
			var action = csharp.createExpressionStatement();
			var access = modular.createAccessExpression();
			var left = modular.createVariable();
			left.setName("referencedPrefabs");
			var right = csharp.createParameterizedFunction();
			right.setName("Add");
			var v = modular.createVariable();
			v.setName("v");
			var argv = csharp.createArgument();
			argv.setValue(v);
			right.getArguments().add(argv);
			access.setLeft(left);
			access.setRight(right);
			action.setExpression(access);
			foreach.getStatements().add(action);
			declare.getStatements().add(foreach);
			
			unit.getTypes().add(struct);
			unit.getTypes().add(clazz);
		}
		else
		{
			namespaces.add("UnityEngine");
			
			var data = csharp.createClass();
			data.getModifiers().add(PUBLIC);
			data.setName(component);
			data.getSuperTypes().add("IComponentData");
			
			var dataValue = csharp.createField();
			var dataValueDeclarator = csharp.createDeclarator();
			dataValue.getModifiers().add(PUBLIC);
			dataValue.setType(unityName(type));
			dataValue.getDeclarators().add(dataValueDeclarator);
			dataValueDeclarator.setVariable("Value");
			data.getMembers().add(dataValue);
			
			var authoring = csharp.createClass();
			authoring.getModifiers().add(PUBLIC);
			authoring.setName(component+"Authoring");
			authoring.getSuperTypes().add("MonoBehaviour");
			authoring.getSuperTypes().add("IConvertGameObjectToEntity");
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setVariable("Value");
			authoring.getMembers().add(value);
			
			var method = csharp.createMethod();
			method.getModifiers().add(PUBLIC);
			method.setType("void");
			method.setName("Convert");
			authoring.getMembers().add(method);
			
			var entity = csharp.createParameter();
			entity.setType("Entity");
			entity.setName("entity");
			var entityManager = csharp.createParameter();
			entityManager.setType("EntityManager");
			entityManager.setName("entityManager");
			var conversionSystem = csharp.createParameter();
			conversionSystem.setType("GameObjectConversionSystem");
			conversionSystem.setName("gameObjectConversionSystem");
			method.getParameters().add(entity);
			method.getParameters().add(entityManager);
			method.getParameters().add(conversionSystem);
			
			var addObjectStatement = csharp.createExpressionStatement();
			var addObject = modular.createAccessExpression();
			var addLeft = modular.createVariable();
			var addRight = csharp.createParameterizedFunction();
			var entityArgument = csharp.createArgument();
			var entityArgumentValue = modular.createVariable();
			var objectArgument = csharp.createArgument();
			var objectArgumentValue = csharp.createCreation();
			var valueMember = csharp.createMemberInitializer();
			var valueMemberValue = modular.createVariable();
			method.getStatements().add(addObjectStatement);
			addObjectStatement.setExpression(addObject);
			addObject.setLeft(addLeft);
			addObject.setRight(addRight);
			addRight.getArguments().add(entityArgument);
			addRight.getArguments().add(objectArgument);
			entityArgument.setValue(entityArgumentValue);
			objectArgument.setValue(objectArgumentValue);
			objectArgumentValue.getMembers().add(valueMember);
			valueMember.setValue(valueMemberValue);
			addLeft.setName("entityManager");
			addRight.setName("AddComponentObject");
			entityArgumentValue.setName("entity");
			objectArgumentValue.setType(component);
			valueMember.setName("Value");
			valueMemberValue.setName("Value");
			
			unit.getTypes().add(data);
			unit.getTypes().add(authoring);
		}
		
		if (type == input)
		{
			namespaces.add("UnityEngine.InputSystem");
		}

		for (var namespace : namespaces)
		{
			var using = csharp.createNamespaceUsing();
			using.setNamespace(namespace);
			unit.getUsings().add(using);			
		}
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Components/"+component+"Authoring.cs");
	}
	
	private void serialize(Archetype archetype)
	{
		
	}
	
	private void serialize(System system)
	{
		var unit = csharp.createCompilationUnit();
		var namespaces = new HashSet<String>();
		var querySet = new QuerySet();

		namespaces.add("Unity.Entities");
		namespaces.add("Unity.Jobs");
		
		var clazz = csharp.createClass();
		unit.getTypes().add(clazz);
		clazz.getModifiers().add(PUBLIC);
		clazz.setName(system.getName());
		clazz.getSuperTypes().add("JobComponentSystem");
		
		var onUpdate = csharp.createMethod();
		var handleParameter = csharp.createParameter();
		clazz.getMembers().add(onUpdate);
		onUpdate.getModifiers().add(PROTECTED);
		onUpdate.getModifiers().add(OVERRIDE);
		onUpdate.setType("JobHandle");
		onUpdate.setName("OnUpdate");
		onUpdate.getParameters().add(handleParameter);
		handleParameter.setType("JobHandle");
		handleParameter.setName("inputDependencies");
		
		
		
		
		var runStatement = csharp.createExpressionStatement();
		var run = modular.createAccessExpression();
		var jobWithCode = modular.createAccessExpression();
		var job = modular.createVariable();
		var withCode = csharp.createParameterizedFunction();
		var updateArgument = csharp.createArgument();
		var lambda = csharp.createLambda();
		var runCall = csharp.createParameterizedFunction();
		onUpdate.getStatements().add(runStatement);
		runStatement.setExpression(run);
		run.setLeft(jobWithCode);
		run.setRight(runCall);
		jobWithCode.setLeft(job);
		jobWithCode.setRight(withCode);
		withCode.getArguments().add(updateArgument);
		updateArgument.setValue(lambda);
		job.setName("Job");
		withCode.setName("WithCode");
		runCall.setName("Run");
		
		addStatements(system.getStatements(), lambda.getStatements(), querySet);
		
		for (var query : querySet.queries.keySet())
		{
			var amountDeclaration = csharp.createDeclaration();
			var amountDeclarator = csharp.createDeclarator();
			var amount = modular.createAccessExpression();
			var amountLeft = modular.createVariable();
			var amountRight = csharp.createParameterizedFunction();
			onUpdate.getStatements().add(0,amountDeclaration);
			amountDeclaration.getDeclarators().add(amountDeclarator);
			amountDeclarator.setValue(amount);
			amount.setLeft(amountLeft);
			amount.setRight(amountRight);
			amountDeclarator.setVariable(query+"_amount");
			amountLeft.setName(query);
			amountRight.setName("CalculateEntityCount");
		}
		
		var returnDefault = csharp.createReturn();
		var defaultValue = csharp.createDefault();
		onUpdate.getStatements().add(returnDefault);
		returnDefault.setExpression(defaultValue);
		
		if (querySet.queries.size() > 0)
		{
			var staticUsing = csharp.createStaticUsing();
			staticUsing.setNamespace("Unity.Entities.ComponentType");
			unit.getUsings().add(staticUsing);
			
			var onCreate = csharp.createMethod();
			clazz.getMembers().add(0, onCreate);
			onCreate.getModifiers().add(PROTECTED);
			onCreate.getModifiers().add(OVERRIDE);
			onCreate.setType("void");
			onCreate.setName("OnCreate");
			
			for (var queryKey : querySet.queries.keySet())
			{
				var query = querySet.queries.get(queryKey);
				var assignmentExpression = csharp.createExpressionStatement();
				var assignment = csharp.createAssignment();
				var assignmentLeft = modular.createVariable();
				var assignmentRight = csharp.createParameterizedFunction();
				onCreate.getStatements().add(assignmentExpression);
				assignmentExpression.setExpression(assignment);
				assignment.setLeft(assignmentLeft);
				assignment.setRight(assignmentRight);
				assignmentLeft.setName(queryKey);
				assignmentRight.setName("GetEntityQuery");
				
				for (var component : query.keySet())
				{
					var accesses = query.get(component);
					var totalAccess = "ReadOnly";
					if (accesses.contains(AccessKind.write))
					{
						totalAccess = "ReadWrite";
					}
					
					var componentAccessArgument = csharp.createArgument();
					var accessType = csharp.createParameterizedFunction();
					assignmentRight.getArguments().add(componentAccessArgument);
					componentAccessArgument.setValue(accessType);
					accessType.setName(totalAccess);
					accessType.getTypes().add(unityName(component, namespaces));
				}
			}
		}
		
		for (var queryKey : querySet.queries.keySet())
		{
			var field = csharp.createField();
			var declarator = csharp.createDeclarator();
			clazz.getMembers().add(0, field);
			field.getDeclarators().add(declarator);
			field.setType("EntityQuery");
			declarator.setVariable(queryKey);
		}
		
		for (var namespace : namespaces)
		{
			var using = csharp.createNamespaceUsing();
			using.setNamespace(namespace);
			unit.getUsings().add(using);			
		}
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/"+system.getName()+".cs");
	}
	
	private void addStatements(List<Statement> statements, List<Statement> list, QuerySet querySet)
	{
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				var variable = forall.getVariable();
				var collection = forall.getCollection();
				var conditionExpression = forall.getCondition();
				cs(collection, querySet);
				cs(conditionExpression, querySet);

				
				
				var forStatement = csharp.createFor();
				var initialization = csharp.createDeclaration();
				var indexDeclarator = csharp.createDeclarator();
				var zero = csharp.createFloatLiteral();
				var condition = modular.createComparison();
				var conditionLeft = modular.createVariable();
				var conditionRight = modular.createVariable();
				var iterator = csharp.createIncrement();
				var iteratorExpression = modular.createVariable();
				forStatement.setInitialization(initialization);
				forStatement.setCondition(condition);
				forStatement.setIterator(iterator);
				initialization.getDeclarators().add(indexDeclarator);
				indexDeclarator.setValue(zero);
				condition.setLeft(conditionLeft);
				condition.setRight(conditionRight);
				iterator.setExpression(iteratorExpression);
				
				indexDeclarator.setVariable(variable+"_index");
				zero.setValue("0");
				conditionLeft.setName(variable+"_index");
				condition.setKind(ComparisonKind.LOWER);
				conditionRight.setName(variable+"_amount");
				iteratorExpression.setName(variable+"_index");
				list.add(forStatement);
				
				addStatements(forall.getStatements(), forStatement.getStatements(), querySet);
				
			}
			else if (statement instanceof Exists)
			{
				var exists = (Exists) statement;
				var variable = exists.getVariable();
				var collection = exists.getCollection();
				var conditionExpression = exists.getCondition();
				cs(conditionExpression, querySet);
				
				var forStatement = csharp.createFor();
				var initialization = csharp.createDeclaration();
				var indexDeclarator = csharp.createDeclarator();
				var zero = csharp.createFloatLiteral();
				var condition = modular.createComparison();
				var conditionLeft = modular.createVariable();
				var conditionRight = modular.createVariable();
				var iterator = csharp.createIncrement();
				var iteratorExpression = modular.createVariable();
				forStatement.setInitialization(initialization);
				forStatement.setCondition(condition);
				forStatement.setIterator(iterator);
				initialization.getDeclarators().add(indexDeclarator);
				indexDeclarator.setValue(zero);
				condition.setLeft(conditionLeft);
				condition.setRight(conditionRight);
				iterator.setExpression(iteratorExpression);
				
				indexDeclarator.setVariable(variable+"_index");
				zero.setValue("0");
				conditionLeft.setName(variable+"_index");
				condition.setKind(ComparisonKind.LOWER);
				conditionRight.setName(variable+"_amount");
				iteratorExpression.setName(variable+"_index");
				list.add(forStatement);
				
				addStatements(exists.getStatements(), forStatement.getStatements(), querySet);
				
			}
			else if (statement instanceof Assignment)
			{
				var assignment = (Assignment) statement;
				var variable = assignment.getVariable();
				var expression = assignment.getExpression();
				
				if (variable instanceof Variable)
				{
					
				}
				else if (variable instanceof ComponentAccess)
				{
					var access = (ComponentAccess) variable;
					var entity = access.getEntity();
					var component = access.getComponent();
					querySet.add(entity, component, AccessKind.write);
				}
				cs(expression, querySet);
			}
		}
	}
	
	private void cs(Expression expression, QuerySet querySet)
	{
		if (expression instanceof ComponentAccess)
		{
			var access = (ComponentAccess) expression;
			querySet.add(access.getEntity(), access.getComponent(), AccessKind.read);
		}
		else if (expression instanceof LogicalOr)
		{
			var e = (LogicalOr) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);
		}
		else if (expression instanceof LogicalAnd)
		{
			var e = (LogicalAnd) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);
		}
		else if (expression instanceof Equality)
		{
			var e = (Equality) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);	
		}
		else if (expression instanceof Comparison)
		{
			var e = (Comparison) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);
		}
		else if (expression instanceof AdditiveExpression)
		{
			var e = (AdditiveExpression) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);
		}
		else if (expression instanceof MultiplicativeExpression)
		{
			var e = (MultiplicativeExpression) expression;
			cs(e.getLeft(), querySet);
			cs(e.getRight(), querySet);
		}
		else if (expression instanceof LogicalNot)
		{
			var e = (LogicalNot) expression;
			cs(e.getExpression(), querySet);
		}
		else if (expression instanceof Brackets)
		{
			var e = (LogicalNot) expression;
			cs(e.getExpression(), querySet);
		}
		else if (expression instanceof Function)
		{
			var a = (Function) expression;
			if (a.getName().equals("has"))
			{
				var parameter0 = ((Variable)a.getArguments().get(0)).getName();
				var parameter1 = ((Variable)a.getArguments().get(1)).getName();
				querySet.add(parameter1, parameter0, AccessKind.tag);
			}
			for (var argument : a.getArguments())
			{
				cs(argument, querySet);
			}
		}
	}
	
	private String unityName(String component, HashSet<String> namespaces)
	{
		var engineComponents = StandardLibrary.values();
		for (var engineComponent : engineComponents)
		{
			if (component.equals(engineComponent.toString()))
			{
				return engineComponent.getUnityType(namespaces);
			}
		}
		return component;
	}
	
	private String uuid(String string)
	{
		return UUID.nameUUIDFromBytes(string.getBytes()).toString().replace("-","");
	}
	
	private String hash(String s)
	{
		var result = "";
		var uuid = uuid(s);
		for (var c = 0; c < 10; c++)
		{
			var character = uuid.charAt(c);
			if (character > 57)
			{
				result += ((char)character-49);
			}
			else
			{
				result += character;
			}
		}
		if (result.startsWith("0"))
		{
			result = result.replaceFirst("0","1");
		}
		return result;
	}
	
	private String unityName(Type type)
	{
		switch(type)
		{
			case audioClip: return "AudioClip";
			case bool: return "bool";
			case entity: return "Entity";
			case entityList: return "Entity";
			case float1: return "float";
			case float2: return "float2";
			case float3: return "float3";
			case float4: return "float4";
			case font: return "FontAsset";
			case gameObject: return "GameObject";
			case image: return "Texture";
			case input: return "InputAction";
			case material: return "Material";
			case mesh: return "Mesh";
			case none: return "None";
			case stateMachine: return "AnimatorController";
			case tag: return "None";
			case text: return "String";
		}
		return "None";
	}
	
	private void extras()
	{
		var engineComponents = new String[] {"Camera", "Light"};
		var unit = csharp.createCompilationUnit();
		var namespaces = new String[]{"UnityEngine","Unity.Entities"};
		for (var namespace : namespaces)
		{
			var using = csharp.createNamespaceUsing();
			unit.getUsings().add(using);
			using.setNamespace(namespace);
		}
		
		var clazz = csharp.createClass();
		unit.getTypes().add(clazz);
		clazz.getModifiers().add(PUBLIC);
		clazz.setName("EngineComponentConversion");
		clazz.getSuperTypes().add("GameObjectConversionSystem");
		
		var onUpdate = csharp.createMethod();
		clazz.getMembers().add(onUpdate);
		onUpdate.getModifiers().add(PROTECTED);
		onUpdate.getModifiers().add(OVERRIDE);
		onUpdate.setType("void");
		onUpdate.setName("OnUpdate");
		
		for (var engineComponent : engineComponents)
		{
			var statement = csharp.createExpressionStatement();
			var function = csharp.createParameterizedFunction();
			onUpdate.getStatements().add(statement);
			statement.setExpression(function);
			function.setName("HybridComponent");
			function.getTypes().add(engineComponent);
		}
		
		var hybrid = csharp.createMethod();
		var T = csharp.createTypeParameter();
		var componentConstraint = csharp.createTypeConstraint();
		clazz.getMembers().add(hybrid);
		hybrid.getTypeParameters().add(T);
		hybrid.getTypeConstraints().add(componentConstraint);
		hybrid.setType("void");
		hybrid.setName("HybridComponent");
		T.setName("T");
		componentConstraint.setType("T");
		componentConstraint.getSuperTypes().add("Component");
		
		var statement = csharp.createExpressionStatement();
		var access = modular.createAccessExpression();
		var accessLeft = modular.createVariable();
		var accessRight = csharp.createParameterizedFunction();
		var lambdaArgument = csharp.createArgument();
		var lambda = csharp.createLambda();
		var Tparameter = csharp.createParameter();
		hybrid.getStatements().add(statement);
		statement.setExpression(access);
		access.setLeft(accessLeft);
		access.setRight(accessRight);
		accessRight.getArguments().add(lambdaArgument);
		lambdaArgument.setValue(lambda);
		lambda.getParameters().add(Tparameter);
		accessLeft.setName("Entities");
		accessRight.setName("ForEach");
		Tparameter.setType("T");
		Tparameter.setName("component");
		
		var addStatement = csharp.createExpressionStatement();
		var addFunction = csharp.createParameterizedFunction();
		var componentArgument = csharp.createArgument();
		var component = modular.createVariable();
		lambda.getStatements().add(addStatement);
		addStatement.setExpression(addFunction);
		addFunction.getArguments().add(componentArgument);
		componentArgument.setValue(component);
		addFunction.setName("AddHybridComponent");
		component.setName("component");
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/Engine/EngineComponentConversion.cs");
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
}
enum AccessKind
{
	read, write, subtract, tag
}
