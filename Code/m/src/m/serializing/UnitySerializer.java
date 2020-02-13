package m.serializing;

import static m.csharp.Modifier.OVERRIDE;
import static m.csharp.Modifier.PROTECTED;
import static m.csharp.Modifier.PUBLIC;
import static m.library.SimpleType.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.JSONRuntimeModule;
import m.YAMLRuntimeModule;
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
import m.json.JsonFactory;
import m.json.Member;
import m.m.AdditiveExpression;
import m.m.Archetype;
import m.m.Assignment;
import m.m.Brackets;
import m.m.Comparison;
import m.m.ComponentAccess;
import m.m.Equality;
import m.m.ExplicitSet;
import m.m.Expression;
import m.m.Forall;
import m.m.Function;
import m.m.Game;
import m.m.ImplicitSet;
import m.m.Join;
import m.m.LogicalAnd;
import m.m.LogicalNot;
import m.m.LogicalOr;
import m.m.MFactory;
import m.m.MultiplicativeExpression;
import m.m.Selection;
import m.m.SetExpression;
import m.m.Statement;
import m.m.System;
import m.m.Variable;
import m.modular.AccessExpression;
import m.modular.AdditiveKind;
import m.modular.AssignmentKind;
import m.modular.ComparisonKind;
import m.modular.EqualityKind;
import m.modular.ModularFactory;
import m.modular.MultiplicativeKind;
import m.validation.MValidator;
import m.library.Component;
import m.library.SimpleType;
import m.library.Type;
import m.yaml.YamlFactory;

public class UnitySerializer
{
	ModularFactory modular = ModularFactory.eINSTANCE;
	MFactory m = MFactory.eINSTANCE;
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
				Component.valueOf(component);
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
			serializeUnified(system);
		}
		
		
		extras();
		systemGroups();
		inputSystem();
		//Physics();
		timers();
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
	
	public void serialize(String component, SimpleType type)
	{
		var unit = unit();
		
		var namespaces = new HashSet<String>();
		namespaces.add("Unity.Entities");
		
		if (type == float1 || type == float2 || type == float3 || type == float4 || type == bool)
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
		else if (type.isCollection)
		{
			namespaces.add("UnityEngine");
			namespaces.add("System.Collections.Generic");
			
			var struct = csharp.createStruct();
			struct.getModifiers().add(PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IBufferElementData");
			struct.getSuperTypes().add("IEntity");
			
			var value = csharp.createProperty();
			var getter = csharp.createGetter();
			var setter = csharp.createSetter();
			value.setGetter(getter);
			value.setSetter(setter);
			getter.setEmpty(true);
			setter.setEmpty(true);
			value.getModifiers().add(PUBLIC);
			value.setType(unityName(type));
			value.setName("Value");
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
			if (namespace != null)
			{
				var using = csharp.createNamespaceUsing();
				using.setNamespace(namespace);
				unit.getUsings().add(using);			
			}
		}
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Components/"+component+"Authoring.cs");
	}
	
	private void serialize(Archetype archetype)
	{
		
	}
	
	private void serializeUnified(System system)
	{
		var unit = csharp.createCompilationUnit();
		var namespaces = new HashSet<String>();
		var querySet = new QuerySet();

		namespaces.add("Unity.Entities");
		namespaces.add("Unity.Jobs");
		
		var clazz = csharp.createClass();
		var attributeSection = csharp.createAttributeSection();
		var attribute = csharp.createAttribute();
		var typeof = csharp.createTypeof();
		clazz.getAttributes().add(attributeSection);
		attributeSection.getAttributes().add(attribute);
		attribute.setName("UpdateInGroup");
		attribute.getPositionalArguments().add(typeof);
		typeof.setType("Gameplay");
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
		
		var declareBuffer = csharp.createDeclaration();
		var commandBuffer = csharp.createDeclarator();
		var untilCreateBuffer = modular.createAccessExpression();
		var untilGetSystem = modular.createAccessExpression();
		var world = modular.createVariable();
		var getSystem = csharp.createParameterizedFunction();
		var createBuffer = csharp.createParameterizedFunction();
		onUpdate.getStatements().add(declareBuffer);
		declareBuffer.getDeclarators().add(commandBuffer);
		commandBuffer.setVariable("commandBuffer");
		commandBuffer.setValue(untilCreateBuffer);
		untilCreateBuffer.setLeft(untilGetSystem);
		untilCreateBuffer.setRight(createBuffer);
		untilGetSystem.setLeft(world);
		untilGetSystem.setRight(getSystem);
		world.setName("World");
		getSystem.setName("GetOrCreateSystem");
		getSystem.getTypes().add("EndSimulationEntityCommandBufferSystem");
		createBuffer.setName("CreateCommandBuffer");
		
		addStatementsUnified(system.getStatements(), onUpdate.getStatements(), querySet, namespaces);
		
		var returnDefault = csharp.createReturn();
		var defaultValue = csharp.createDefault();
		onUpdate.getStatements().add(returnDefault);
		returnDefault.setExpression(defaultValue);
		
		for (var namespace : namespaces)
		{
			if (namespace != null)
			{
				var using = csharp.createNamespaceUsing();
				using.setNamespace(namespace);
				unit.getUsings().add(using);
			}
		}
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/"+system.getName()+".cs");
	}
	
	private void addStatementsUnified(List<Statement> statements, List<m.modular.Statement> list, QuerySet querySet, HashSet<String> namespaces)
	{
		var tail = new ArrayList<m.modular.Statement>();
		
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				var variable = forall.getVariable();
				var conditionExpression = forall.getConditiion();
				var condition = cs(conditionExpression, querySet, namespaces);
				
				
				
				var runStatement = csharp.createExpressionStatement();
				var run = modular.createAccessExpression();
				var runCall = csharp.createParameterizedFunction();
				var withoutBurst = modular.createAccessExpression();
				var withoutBurstCall = csharp.createParameterizedFunction();
				var foreach = modular.createAccessExpression();
				var foreachCall = csharp.createParameterizedFunction();
				var lambdaArgument = csharp.createArgument();
				var lambda = csharp.createLambda();
				var entities = modular.createVariable();
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
				
				var block = lambda.getStatements();
				
				if (condition != null)
				{
					var selection = modular.createSelection();
					var first = modular.createBranch();
					selection.getBranches().add(first);
					first.setCondition(condition);
					lambda.getStatements().add(selection);
					block = first.getStatements();
				}
				
				addStatementsUnified(forall.getStatements(), block, querySet, namespaces);
				
				var myQuery = querySet.queries.get(variable);
				if (myQuery != null)
				{
					for (var component : myQuery.keySet())
					{
						if (!component.equals("Entity"))
						{
							var parameter = csharp.createParameter();
							parameter.setType(unityName(component, namespaces));
							
							parameter.setName(component+"_"+variable);
							
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
						parameter.setName("entity_"+variable);
						lambda.getParameters().add(0, parameter);
					}
				}
			}
			else if (statement instanceof Selection)
			{
				var selection = (Selection) statement;
				var cs = modular.createSelection();
				
				var csBranch = modular.createBranch();
				cs.getBranches().add(csBranch);
				csBranch.setCondition(cs(selection.getCondition(), querySet, namespaces));
				addStatementsUnified(selection.getPositiveStatements(), csBranch.getStatements(), querySet, namespaces);
				
				list.add(cs);
			}
			else if (statement instanceof Assignment)
			{
				var assignment = (Assignment) statement;
				var variable = assignment.getVariable();
				var expression = assignment.getExpression();
				
				if (variable instanceof Variable)
				{
					var a = (Variable) variable;
					var cs = csharp.createDeclaration();
					var declarator = csharp.createDeclarator();
					
					if (expression instanceof ImplicitSet)
					{
						var set = (ImplicitSet) expression;
						var predicate = set.getPredicate();
						var entity = set.getVariable();
						
						var creation = csharp.createCreation();
						var allocatorArgument = csharp.createArgument();
						var untilTempJob = modular.createAccessExpression();
						var allocator = modular.createVariable();
						var tempJob = modular.createVariable();
						creation.getArguments().add(allocatorArgument);
						allocatorArgument.setValue(untilTempJob);
						untilTempJob.setLeft(allocator);
						untilTempJob.setRight(tempJob);
						creation.setType("NativeList<Entity>");
						namespaces.add("Unity.Collections");
						cs.setType("NativeList<Entity>");
						cs.getDeclarators().add(declarator);
						declarator.setVariable(a.getName());
						declarator.setValue(creation);
						allocator.setName("Allocator");
						tempJob.setName("TempJob");
						
						list.add(cs);
						
						var runStatement = csharp.createExpressionStatement();
						var run = modular.createAccessExpression();
						var runCall = csharp.createParameterizedFunction();
						var withoutBurst = modular.createAccessExpression();
						var withoutBurstCall = csharp.createParameterizedFunction();
						var foreach = modular.createAccessExpression();
						var foreachCall = csharp.createParameterizedFunction();
						var lambdaArgument = csharp.createArgument();
						var lambda = csharp.createLambda();
						var entities = modular.createVariable();
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
						
						var selection = modular.createSelection();
						var first = modular.createBranch();
						selection.getBranches().add(first);
						first.setCondition(cs(predicate, querySet, namespaces));
						lambda.getStatements().add(selection);
						
						var addStatement = csharp.createExpressionStatement();
						var untilAdd = modular.createAccessExpression();
						var nativeList = modular.createVariable();
						var add = csharp.createParameterizedFunction();
						var argument = csharp.createArgument();
						var entityArgument = modular.createVariable();
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
						var untilDispose = modular.createAccessExpression();
						var array = modular.createVariable();
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
						cs.getDeclarators().add(declarator);
						declarator.setVariable(a.getName());
						declarator.setValue(cs(expression, querySet, namespaces));
						list.add(cs);
					}
				}
				else if (variable instanceof ComponentAccess)
				{
					var access = (ComponentAccess) variable;
					var entity = access.getEntity();
					var component = access.getComponent();
					querySet.add(entity, component, AccessKind.write);
					
					var csStatement = csharp.createExpressionStatement();
					var csAssignment = csharp.createAssignment();
					var left = cs(access, querySet, namespaces);
					var right = cs(expression, querySet, namespaces);
					list.add(csStatement);
					csStatement.setExpression(csAssignment);
					csAssignment.setLeft(left);
					csAssignment.setRight(right);
				}
			}
			else if (statement instanceof Function)
			{
				var function = (Function) statement;
				var cs = csharp.createExpressionStatement();
				cs.setExpression(cs(function, querySet, namespaces));
				list.add(cs);
			}
		}
		
		for (var t : tail)
		{
			list.add(t);
		}
	}
	
	/*
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
		
		addStatements(system.getStatements(), lambda.getStatements(), querySet, namespaces);
		
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
	
	private void addStatements(List<Statement> statements, List<Statement> list, QuerySet querySet, namespaces)
	{
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				var variable = forall.getVariable();
				var collection = forall.getCollection();
				var conditionExpression = forall.getCondition();
				cs(collection, querySet, namespaces);
				cs(conditionExpression, querySet, namespaces);

				
				
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
				
				addStatements(forall.getStatements(), forStatement.getStatements(), querySet, namespaces);
				
			}
			else if (statement instanceof Exists)
			{
				var exists = (Exists) statement;
				var variable = exists.getVariable();
				var collection = exists.getCollection();
				var conditionExpression = exists.getCondition();
				cs(conditionExpression, querySet, namespaces);
				
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
				
				addStatements(exists.getStatements(), forStatement.getStatements(), querySet, namespaces);
				
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
				cs(expression, querySet, namespaces);
			}
		}
	}*/
	
	private m.modular.Expression cs(Expression expression, QuerySet querySet, HashSet<String> namespaces)
	{
		if (expression instanceof LogicalOr)
		{
			var e = (LogicalOr) expression;
			var cs = modular.createLogicalOr();
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof LogicalAnd)
		{
			var e = (LogicalAnd) expression;
			var cs = modular.createLogicalAnd();
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Equality)
		{
			var e = (Equality) expression;
			var cs = modular.createEquality();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof Comparison)
		{
			var e = (Comparison) expression;
			var cs = modular.createComparison();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof AdditiveExpression)
		{
			var e = (AdditiveExpression) expression;
			var cs = modular.createAdditiveExpression();
			cs.setKind(cs(e.getKind()));
			cs.setLeft(cs(e.getLeft(), querySet, namespaces));
			cs.setRight(cs(e.getRight(), querySet, namespaces));
			return cs;
		}
		else if (expression instanceof MultiplicativeExpression)
		{
			var e = (MultiplicativeExpression) expression;
			var cs = modular.createMultiplicativeExpression();
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
				querySet.add(name, "Entity", AccessKind.read);
				var buffer = cs(e.getRight(), querySet, namespaces);
				
				return access(buffer, function("Contains",argument(variable("entity_"+name))));
			}
		}
		else if (expression instanceof LogicalNot)
		{
			var e = (LogicalNot) expression;
			return not(brackets(cs(e.getExpression(), querySet, namespaces)));
		}
		else if (expression instanceof Brackets)
		{
			var e = (LogicalNot) expression;
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
		else if (expression instanceof Function)
		{
			var e = (Function) expression;
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
			else if (name.equals("add"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				querySet.add(variable, "Entity", AccessKind.read);
				
				return access(variable("commandBuffer"),function("AddComponent", new String[] {component}, argument(variable("entity_"+variable))));				
				
			}
			else if (name.equals("remove"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				
				querySet.add(variable, component, AccessKind.read);
				querySet.add(variable, "Entity", AccessKind.read);
				
				return access(variable("commandBuffer"),function("RemoveComponent", new String[] {component},argument(variable("entity_"+variable))));
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
			else if (name.equals("empty"))
			{
				var set = cs(e.getArguments().get(0),querySet,namespaces);
				
				return comparison(access(set, variable("Length")), ComparisonKind.GREATER, floatLiteral("0"));
			}
		}
		else if (expression instanceof Variable)
		{
			var e = (Variable) expression;
			return variable(e.getName());
		}
		else if (expression instanceof ComponentAccess)
		{
			var access = (ComponentAccess) expression;
			querySet.add(access.getEntity(), access.getComponent(), AccessKind.read);
			var csExpression = modular.createAccessExpression();
			var component = modular.createVariable();
			var field = modular.createVariable();
			csExpression.setLeft(component);
			csExpression.setRight(field);
			component.setName(access.getComponent()+"_"+access.getEntity());
			field.setName(field(access.getComponent()));
			if (MValidator.components.get(access.getComponent()) == entityList)
			{
				return component;
			}
			else
			{
				return csExpression;
			}
		}
		else if (expression instanceof ImplicitSet)
		{
			var e = (ImplicitSet) expression;
			var variable = e.getVariable();
			var predicate = e.getPredicate();
			
			var selection = modular.createSelection();
		}
		else if (expression instanceof ExplicitSet)
		{
			var e = (ExplicitSet) expression;
		}
		return null;
	}
	
	private m.modular.ComparisonKind cs(m.m.ComparisonKind kind)
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
	
	private EqualityKind cs(m.m.EqualityKind kind)
	{
		switch(kind)
		{
		case EQUAL: return EqualityKind.EQUAL;
		case NOT_EQUAL: return EqualityKind.NOT_EQUAL;
		}
		return EqualityKind.EQUAL;
	}
	
	private AdditiveKind cs(m.m.AdditiveKind kind)
	{
		switch (kind)
		{
		case ADD: return AdditiveKind.ADD;
		case SUBTRACT: return AdditiveKind.SUBTRACT;
		default: return AdditiveKind.ADD;
		}
	}
	
	private MultiplicativeKind cs(m.m.MultiplicativeKind kind)
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
		if (MValidator.components.get(component) == entityList)
		{
			name = "DynamicBuffer<"+name+">";
		}
		return name;
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
	
	private String field(String component)
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
	
	private String unityName(SimpleType type)
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
			case image: return "Texture";
			case input: return "InputAction";
			case material: return "Material";
			case mesh: return "Mesh";
			case none: return "None";
			case stateMachine: return "AnimatorController";
			case tag: return "None";
			case text: return "String";
			case type: return "Type";
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
			if (namespace != null)
			{
				var using = csharp.createNamespaceUsing();
				unit.getUsings().add(using);
				using.setNamespace(namespace);
			}
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
	
	private void systemGroups()
	{
		var unit = csharp.createCompilationUnit();
		var using = csharp.createNamespaceUsing();
		var gameplay = csharp.createClass();
		var engine = csharp.createClass();
		unit.getUsings().add(using);
		unit.getTypes().add(gameplay);
		unit.getTypes().add(engine);
		
		using.setNamespace("Unity.Entities");
		
		gameplay.setName("Gameplay");
		gameplay.getSuperTypes().add("ComponentSystemGroup");
		engine.setName("Engine");
		engine.getSuperTypes().add("ComponentSystemGroup");
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/Engine/SystemGroups.cs");
	}
	
	private void inputSystem()
	{
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("UnityEngine.InputSystem"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "ReadInput", new String[] {"JobComponentSystem"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.modular.Expression[] {typeof("Engine")}));
		
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		clazz.getMembers().add(onUpdate);
		
		for (var component : MValidator.components.keySet())
		{
			if (MValidator.components.get(component) == input)
			{
				for (var valueComponent : MValidator.components.keySet())
				{
					if (valueComponent.equals(component+"Range")||valueComponent.equals(component+"Vector")||valueComponent.equals(component+"Triggered"))
					{
						var lambda = csharp.createLambda();
						var run = access(access(access(variable("Entities"),function("ForEach",argument(lambda))),function("WithoutBurst")), function("Run"));
						
						onUpdate.getStatements().add(statement(run));
						
						lambda.getParameters().add(parameter(component, component));
						lambda.getParameters().add(refParameter(valueComponent, valueComponent));
						
						lambda.getStatements().add(declaration(declarator("action", access(variable(component), variable("Value")))));
						
						lambda.getStatements().add(statement(access(variable("action"),function("Enable"))));
						var field = access(variable(valueComponent),variable("Value"));
						m.modular.Expression read = null;
						
						if (valueComponent.equals(component+"Range"))
						{
							read = access(variable("action"),function("ReadValue", new String[] {"float"}));
						}
						else if (valueComponent.equals(component+"Vector"))
						{
							read = access(variable("action"),function("ReadValue", new String[] {"Vector2"}));
						}
						else if (valueComponent.equals(component+"Triggered"))
						{
							read = equality(access(variable("action"),variable("phase")), access(variable("InputActionPhase"), variable("Started")));
						}
						
						lambda.getStatements().add(statement(assignment(field,read)));
					}
				}
			}
		}
		
		
		
		var returnDefault = csharp.createReturn();
		var defaultValue = csharp.createDefault();
		onUpdate.getStatements().add(returnDefault);
		returnDefault.setExpression(defaultValue);
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/Engine/Input.cs");
	}
	
	private void timers()
	{
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "TickTimers", new String[] {"JobComponentSystem"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.modular.Expression[] {typeof("Engine")}));
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		
		onUpdate.getStatements().add(declaration("float", declarator("deltaTime", access(variable("Time"), variable("DeltaTime")))));
		clazz.getMembers().add(onUpdate);
		
		for (var elapsed : MValidator.components.keySet())
		{
			if (elapsed.endsWith("Elapsed"))
			{
				var root = elapsed.substring(0, elapsed.lastIndexOf("Elapsed"));
				String total = root;
				String signal = root+"Timeout";
				
				var totalExists = MValidator.components.containsKey(total);
				var signalExists = MValidator.components.containsKey(signal);
				
				var lambda = csharp.createLambda();
				var run = access(access(access(variable("Entities"),function("ForEach",argument(lambda))),function("WithoutBurst")), function("Run"));
				
				onUpdate.getStatements().add(statement(run));

				if (totalExists)
				{
					lambda.getParameters().add(parameter(total, total));
				}
				
				lambda.getParameters().add(refParameter(elapsed, elapsed));
				
				if (signalExists)
				{
					lambda.getParameters().add(refParameter(signal, signal));
				}
				
				var addDelta = assignment(access(variable(elapsed), variable("Value")), AssignmentKind.INCREASE, variable("deltaTime"));
				lambda.getStatements().add(statement(addDelta));
				
				if (totalExists && signalExists)
				{
					var setSignal = assignment(access(variable(signal), variable("Value")), AssignmentKind.SET, comparison(access(variable(elapsed), variable("Value")), ComparisonKind.GREATER_OR_EQUAL, access(variable(total), variable("Value"))));
					lambda.getStatements().add(statement(setSignal));
				}
				
				if (totalExists)
				{
					var selection = modular.createSelection();
					var ifBranch = modular.createBranch();
					selection.getBranches().add(ifBranch);
					ifBranch.setCondition(comparison(access(variable(elapsed), variable("Value")), ComparisonKind.GREATER_OR_EQUAL, access(variable(total), variable("Value"))));
					ifBranch.getStatements().add(statement(assignment(access(variable(elapsed), variable("Value")), AssignmentKind.DECREASE, access(variable(total), variable("Value")))));
					
					lambda.getStatements().add(selection);
				}
				
				
			}
		}
		
		onUpdate.getStatements().add(returnStatement(defaultExpression()));
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/Engine/TickTimers.cs");
	}
	
	private void Physics()
	{
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("Unity.Physics"));
		unit.getUsings().add(namespaceUsing("Unity.Physics.Systems"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Collections"));
		
		var collisionData = struct(new Modifier[] {PUBLIC}, "CollisionData");
		collisionData.getMembers().add(field(new Modifier[] {PUBLIC}, "Entity", declarator("A")));
		collisionData.getMembers().add(field(new Modifier[] {PUBLIC}, "Entity", declarator("B")));
		unit.getTypes().add(collisionData);
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "DetectCollisions", new String[] {"JobComponentSystem"});
		unit.getTypes().add(clazz);
		clazz.getAttributes().add(attribute("UpdateInGroup",new m.modular.Expression[] {typeof("Engine")}));
		clazz.getMembers().add(field("BuildPhysicsWorld", declarator("build")));
		clazz.getMembers().add(field("StepPhysicsWorld", declarator("step")));
		
		var job = struct("CollisionJob", new String[] {"ICollisionEventsJob"});
		
		
		var world = field(new Modifier[] {PUBLIC}, "PhysicsWorld", declarator("world"));
		world.getAttributes().add(attribute("ReadOnly"));
		
		job.getMembers().add(world);
		job.getMembers().add(field(new Modifier[] {PUBLIC}, "NativeList<CollisionData>", declarator("collsionData")));
		
		var execute = method(new Modifier[] {PUBLIC}, "void", "Execute", new Parameter[] {parameter("CollisionEvent", "collisionEvent")});
		job.getMembers().add(execute);
		
		var newData = creation("CollisionData", member("A", access(access(variable("collisionEvent"), variable("Entities")),variable("EntityA"))), member("B", access(access(variable("collisionEvent"), variable("Entities")),variable("EntityB"))));
		var addData = statement(access(variable("collisionData"),function("Add", argument(newData))));
		execute.getStatements().add(addData);
		
		var onCreate = method(new Modifier[] {PROTECTED, OVERRIDE}, "void", "OnCreate");
		onCreate.getStatements().add(statement(assignment(variable("build"),access(variable("World"),function("GetOrCreateSystem", new String[] {"BuildPhysicsWorld"})))));
		onCreate.getStatements().add(statement(assignment(variable("step"),access(variable("World"),function("GetOrCreateSystem", new String[] {"StepPhysicsWorld"})))));

		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		onUpdate.getStatements().add(returnStatement(variable("inputDependencies")));
		
		clazz.getMembers().add(onCreate);
		clazz.getMembers().add(onUpdate);
		clazz.getMembers().add(job);
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/Engine/DetectCollisions.cs");
	}
	
	CompilationUnit unit ()
	{
		return csharp.createCompilationUnit();
	}
	
	CompilationUnit addUsing(CompilationUnit unit, Using using)
	{
		unit.getUsings().add(using);
		return unit;
	}
	
	NamespaceUsing namespaceUsing(String namespace)
	{
		var using = csharp.createNamespaceUsing();
		using.setNamespace(namespace);
		return using;
	}
	
	StaticUsing staticUsing(String namespace)
	{
		var using = csharp.createStaticUsing();
		using.setNamespace(namespace);
		return using;
	}
	
	Namespace namespace(String name)
	{
		var namespace = csharp.createNamespace();
		namespace.setName(name);
		return namespace;
	}
	
	AttributeSection attribute(String name)
	{
		var attributeSection = csharp.createAttributeSection();
		var attribute = csharp.createAttribute();
		attributeSection.getAttributes().add(attribute);
		attribute.setName(name);
		return attributeSection;
	}
	
	AttributeSection attribute(String name, m.modular.Expression[] arguments)
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
	
	m.csharp.Class clazz(Modifier[] modifiers, String name, String[] superTypes)
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
	
	Struct struct(Modifier[] modifiers, String name)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		for (var modifier : modifiers)
		{
			struct.getModifiers().add(modifier);
		}
		return struct;
	}
	
	Struct struct(String name, String[] superTypes)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		for (var superType : superTypes)
		{
			struct.getSuperTypes().add(superType);
		}
		return struct;
	}
	
	Struct struct(String name)
	{
		var struct = csharp.createStruct();
		struct.setName(name);
		return struct;
	}
	
	Field field(Modifier[] modifiers, String type, Declarator...declarators)
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
	
	Field field(String type, Declarator...declarators)
	{
		var field = csharp.createField();
		field.setType(type);
		for (var declarator : declarators)
		{
			field.getDeclarators().add(declarator);
		}
		return field;
	}
	
	Method method(Modifier[] modifiers, String type, String name)
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
	
	Method method(Modifier[] modifiers, String type, String name, Parameter[] parameters)
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
	
	Return returnStatement(m.modular.Expression expression)
	{
		var returnStatement = csharp.createReturn();
		returnStatement.setExpression(expression);
		return returnStatement;
	}
	
	Default defaultExpression()
	{
		return csharp.createDefault();
	}
	
	m.modular.Variable variable(String name)
	{
		var variable = modular.createVariable();
		variable.setName(name);
		return variable;
	}
	
	ParameterizedFunction function(String name, Argument... arguments)
	{
		var function = csharp.createParameterizedFunction();
		function.setName(name);
		for (var argument : arguments)
		{
			function.getArguments().add(argument);
		}
		return function;
	}
	
	ParameterizedFunction function(String name, String[] types, Argument... arguments)
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
	
	m.modular.LogicalNot not(m.modular.Expression expression)
	{
		var not = modular.createLogicalNot();
		not.setExpression(expression);
		return not;
	}
	
	Typeof typeof(String type)
	{
		var typeof = csharp.createTypeof();
		typeof.setType(type);
		return typeof;
	}
	
	m.modular.Brackets brackets(m.modular.Expression expression)
	{
		var brackets = modular.createBrackets();
		brackets.setExpression(expression);
		return brackets;
	}
	
	AccessExpression access(m.modular.Expression left, m.modular.Expression right)
	{
		var access = modular.createAccessExpression();
		access.setLeft(left);
		access.setRight(right);
		return access;
	}
	
	m.modular.Equality equality(m.modular.Expression left, m.modular.Expression right)
	{
		var equality = modular.createEquality();
		equality.setLeft(left);
		equality.setRight(right);
		return equality;
	}
	
	m.csharp.Assignment assignment(m.modular.Expression left, m.modular.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		return assignment;
	}
	
	m.csharp.Assignment assignment(m.modular.Expression left, AssignmentKind kind, m.modular.Expression right)
	{
		var assignment = csharp.createAssignment();
		assignment.setLeft(left);
		assignment.setRight(right);
		assignment.setKind(kind);
		return assignment;
	}
	
	ExpressionStatement statement(m.modular.Expression expression)
	{
		var statement = csharp.createExpressionStatement();
		statement.setExpression(expression);
		return statement;
	}
	
	Declarator declarator(String name, m.modular.Expression expression)
	{
		var declarator = csharp.createDeclarator();
		declarator.setVariable(name);
		declarator.setValue(expression);
		return declarator;
	}
	
	Declarator declarator(String name)
	{
		var declarator = csharp.createDeclarator();
		declarator.setVariable(name);
		return declarator;
	}
	
	Declaration declaration(String type, Declarator...declarators) 
	{
		var declaration = csharp.createDeclaration();
		declaration.setType(type);
		for (var declarator : declarators)
		{
			declaration.getDeclarators().add(declarator);
		}
		return declaration;
	}
	
	Declaration declaration(Declarator...declarators)
	{
		var declaration = csharp.createDeclaration();
		for (var declarator : declarators)
		{
			declaration.getDeclarators().add(declarator);
		}
		return declaration;
	}
	
	Parameter parameter(String type, String name)
	{
		var parameter = csharp.createParameter();
		parameter.setType(type);
		parameter.setName(name);
		return parameter;
	}
	
	Parameter refParameter(String type, String name)
	{
		var parameter = csharp.createParameter();
		parameter.setType(type);
		parameter.setName(name);
		parameter.setRef(true);
		return parameter;
	}
	
	MemberInitializer member(String name, m.modular.Expression value)
	{
		var memberInitializer = csharp.createMemberInitializer();
		memberInitializer.setName(name);
		memberInitializer.setValue(value);
		return memberInitializer;
	}
	
	Argument argument(m.modular.Expression expression)
	{
		var argument = csharp.createArgument();
		argument.setValue(expression);
		return argument;
	}
	
	FloatLiteral floatLiteral(String number)
	{
		var floatLiteral = csharp.createFloatLiteral();
		floatLiteral.setValue(number);
		return floatLiteral;
	}
	
	m.modular.Comparison comparison(m.modular.Expression left, ComparisonKind kind, m.modular.Expression right)
	{
		var comparison = modular.createComparison();
		comparison.setLeft(left);
		comparison.setRight(right);
		comparison.setKind(kind);
		
		return comparison;
	}
	
	Creation creation(String name, Argument...arguments)
	{
		var creation = csharp.createCreation();
		creation.setType(name);
		for (var argument : arguments)
		{
			creation.getArguments().add(argument);
		}
		return creation;
	}
	
	Creation creation(String name, MemberInitializer...members)
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
