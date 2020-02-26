package m.serializing;

import static m.csharp.Modifier.*;
import static m.csharp.Modifier.PROTECTED;
import static m.csharp.Modifier.PUBLIC;
import static m.csharp.Modifier.STATIC;
import static m.library.SimpleType.bool;
import static m.library.SimpleType.entityList;
import static m.library.SimpleType.*;
import static m.library.SimpleType.float2;
import static m.library.SimpleType.float3;
import static m.library.SimpleType.float4;
import static m.library.SimpleType.input;
import static m.library.SimpleType.tag;
import static m.serializing.CSharpHelper.*;
import static m.serializing.CSharpHelper.index;
import static m.serializing.CSharpHelper.increment;
import static m.serializing.CSharpHelper.access;
import static m.serializing.CSharpHelper.argument;
import static m.serializing.CSharpHelper.assignment;
import static m.serializing.CSharpHelper.attribute;
import static m.serializing.CSharpHelper.brackets;
import static m.serializing.CSharpHelper.clazz;
import static m.serializing.CSharpHelper.comparison;
import static m.serializing.CSharpHelper.creation;
import static m.serializing.CSharpHelper.declaration;
import static m.serializing.CSharpHelper.declarator;
import static m.serializing.CSharpHelper.defaultExpression;
import static m.serializing.CSharpHelper.equality;
import static m.serializing.CSharpHelper.field;
import static m.serializing.CSharpHelper.floatLiteral;
import static m.serializing.CSharpHelper.foreach;
import static m.serializing.CSharpHelper.function;
import static m.serializing.CSharpHelper.ifStatement;
import static m.serializing.CSharpHelper.lambda;
import static m.serializing.CSharpHelper.member;
import static m.serializing.CSharpHelper.method;
import static m.serializing.CSharpHelper.namespaceUsing;
import static m.serializing.CSharpHelper.not;
import static m.serializing.CSharpHelper.parameter;
import static m.serializing.CSharpHelper.refArgument;
import static m.serializing.CSharpHelper.refParameter;
import static m.serializing.CSharpHelper.returnStatement;
import static m.serializing.CSharpHelper.statement;
import static m.serializing.CSharpHelper.struct;
import static m.serializing.CSharpHelper.typeof;
import static m.serializing.CSharpHelper.unit;
import static m.serializing.CSharpHelper.variable;
import static m.serializing.CSharpHelper.thisParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.JSONRuntimeModule;
import m.YAMLRuntimeModule;
import m.csharp.AdditiveKind;
import m.csharp.Argument;
import m.csharp.AssignmentKind;
import m.csharp.Class;
import m.csharp.ComparisonKind;
import m.csharp.CsharpFactory;
import m.csharp.EqualityKind;
import m.csharp.Modifier;
import m.csharp.MultiplicativeKind;
import m.csharp.Parameter;
import m.json.JsonFactory;
import m.json.Member;
import m.library.Component;
import m.library.SimpleType;
import game.Addition;
import game.Archetype;
import game.Assignment;
import game.Brackets;
import game.Call;
import game.Cardinal;
import game.Cell;
import game.Comparison;
import game.Equality;
import game.ExplicitSet;
import game.Expression;
import game.Forall;
import game.Function;
import game.Game;
import game.ImplicitSet;
import game.Join;
import game.And;
import game.LogicalNot;
import game.Or;
import game.GameFactory;
import game.Multiplication;
import game.Selection;
import game.SetExpression;
import game.Statement;
import game.System;
import game.Variable;
import m.validation.MValidator;
import m.yaml.YamlFactory;
import static m.serializing.CSharpHelper.forStatement;

public class UnitySerializer
{
	CsharpFactory csharp = CsharpFactory.eINSTANCE;
	YamlFactory yaml = YamlFactory.eINSTANCE;
	JsonFactory json = JsonFactory.eINSTANCE;
	
	CSharpRuntimeModule csharpModule;
	YAMLRuntimeModule yamlModule;
	JSONRuntimeModule jsonModule;
	
	IFileSystemAccess2 fsa;
	Class systemClass;
	boolean mainThread = true;
	
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
				serialize(component, type, false);
			}
		}
		
		for (var archetype : game.getArchetypes())
		{
			serialize(archetype);
		}
		
		for (var system : game.getSystems())
		{
			serialize(system);
		}
		
		
		conversion();
		systemGroups();
		inputSystem();
		Physics();
		timers();
		extensions();
		ui();
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
		members.add(dependency("com.unity.ui.runtime", "https://github.com/martin-azpillaga/UI.git"));
		members.add(dependency("com.unity.entities","0.6.0-preview.24"));
		members.add(dependency("com.unity.jobs","0.2.5-preview.20"));
		members.add(dependency("com.unity.collections","0.5.2-preview.8"));
		members.add(dependency("com.unity.inputsystem","1.0.0-preview.4"));
		members.add(dependency("com.unity.dots.editor", "0.3.0-preview"));
		members.add(dependency("com.unity.ui.builder", "0.10.1-preview"));
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
	
	public void serialize(String component, SimpleType type, boolean engineType)
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
			var addAccess = csharp.createAccessExpression();
			var addAccessLeft = csharp.createVariable();
			var addAccessRight = csharp.createParameterizedFunction();
			var addAccessArgument = csharp.createArgument();
			var addAccessValue = csharp.createVariable();
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
			var collectionV = csharp.createVariable();
			method.getStatements().add(foreachV);
			foreachV.setCollection(collectionV);
			foreachV.setVariable("v");
			collectionV.setName("Value");
			
			var addElementStatement = csharp.createExpressionStatement();
			var addElement = csharp.createAccessExpression();
			var addLeft = csharp.createVariable();
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
			var valueValue = csharp.createAccessExpression();
			
			creation.getMembers().add(valueMember);
			creation.setType(component);
			valueMember.setName("Value");
			valueMember.setValue(valueValue);
			
			var convertLeft = csharp.createVariable();
			var convertRight = csharp.createParameterizedFunction();
			var convertArgument = csharp.createArgument();
			var convertArgumentV = csharp.createVariable();
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
			var collection = csharp.createVariable();
			collection.setName("Value");
			foreach.setCollection(collection);
			var action = csharp.createExpressionStatement();
			var access = csharp.createAccessExpression();
			var left = csharp.createVariable();
			left.setName("referencedPrefabs");
			var right = csharp.createParameterizedFunction();
			right.setName("Add");
			var v = csharp.createVariable();
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
		else if (type == entity)
		{
			namespaces.add("UnityEngine");
			var struct = struct(new Modifier[] {PUBLIC}, "creation", new String[] {"IComponentData"});
			struct.getMembers().add(field(new Modifier[] {PUBLIC}, "Entity", declarator("Value")));
			struct.getAttributes().add(attribute("GenerateAuthoringComponent"));
			unit.getTypes().add(struct);
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
			var addObject = csharp.createAccessExpression();
			var addLeft = csharp.createVariable();
			var addRight = csharp.createParameterizedFunction();
			var entityArgument = csharp.createArgument();
			var entityArgumentValue = csharp.createVariable();
			var objectArgument = csharp.createArgument();
			var objectArgumentValue = csharp.createCreation();
			var valueMember = csharp.createMemberInitializer();
			var valueMemberValue = csharp.createVariable();
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
		if (engineType)
		{
			GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Components/"+component+"Authoring.cs");
		}
		else
		{
			GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Gameplay/Components/"+component+"Authoring.cs");
		}
	}
	
	private void serialize(Archetype archetype)
	{
		
	}
	
	private void serializeUnified(System system, boolean engine)
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
		
		systemClass = clazz;
		
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
		
		var getBuffer = access(access(variable("World"),function("GetOrCreateSystem", new String[] {"EndSimulationEntityCommandBufferSystem"})),function("CreateCommandBuffer"));
		onUpdate.getStatements().add(statement(assignment(variable("commandBuffer"),getBuffer)));
		systemClass.getMembers().add(0,field("EntityCommandBuffer", declarator("commandBuffer")));
		
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
		
		if (engine)
		{
			GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/"+system.getName()+".cs");
		}
		else
		{
			GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Gameplay/Systems/"+system.getName()+".cs");
		}
	}
	
	private void addStatementsUnified(List<Statement> statements, List<m.csharp.Statement> list, QuerySet querySet, HashSet<String> namespaces)
	{
		var tail = new ArrayList<m.csharp.Statement>();
		
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				var variable = forall.getVariable();
				var conditionExpression = forall.getCondition();
				var condition = cs(conditionExpression, querySet, namespaces);
				
				
				
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
				
				var block = lambda.getStatements();
				
				if (condition != null)
				{
					var selection = csharp.createSelection();
					var first = csharp.createBranch();
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
							
							parameter.setName(component+"_"+variable+"_");
							systemClass.getMembers().add(0, field(unityName(component, namespaces),declarator(component+"_"+variable)));
							lambda.getStatements().add(0,statement(assignment(variable(component+"_"+variable), variable(component+"_"+variable+"_"))));
														systemClass.getMembers().add(0, field(unityName(component, namespaces),declarator(component+"_"+variable)));
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
						lambda.getParameters().add(0, parameter("Entity","entity_"+variable+"_"));
						lambda.getStatements().add(0, statement(assignment(variable("entity_"+variable), variable("entity_"+variable+"_"))));
						systemClass.getMembers().add(0,field("Entity",declarator("entity_"+variable)));
					}
				}
			}
			else if (statement instanceof Selection)
			{
				var selection = (Selection) statement;
				var cs = csharp.createSelection();
				
				var csBranch = csharp.createBranch();
				cs.getBranches().add(csBranch);
				csBranch.setCondition(cs(selection.getCondition(), querySet, namespaces));
				addStatementsUnified(selection.getPositiveStatements(), csBranch.getStatements(), querySet, namespaces);
				
				list.add(cs);
			}
			else if (statement instanceof Assignment)
			{
				var assignment = (Assignment) statement;
				var variable = assignment.getAtom();
				var expression = assignment.getExpression();
				
				if (variable instanceof Variable)
				{
					var a = (Variable) variable;
					var ass = assignment(variable(a.getName()),variable("dummy"));
					var type = "";
					var cs = statement(ass);
					
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
						type = "NativeList<Entity>";
						ass.setRight(creation);
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
						
						//querySet.add(entity, "Entity", AccessKind.read);
						
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
						type = "float";
						ass.setRight(cs(expression, querySet, namespaces));
						list.add(cs);
					}
					
					systemClass.getMembers().add(0,field(type, declarator(a.getName())));
				}
				else if (variable instanceof Cell)
				{
					var access = (Cell) variable;
					var entity = access.getEntity();
					var component = access.getComponent();
					querySet.add(entity, component, AccessKind.write);
					
					var csStatement = csharp.createExpressionStatement();
					var csAssignment = csharp.createAssignment();
					var left = access(variable(access.getComponent()+"_"+access.getEntity()+"_"),variable(fieldName(access.getComponent())));
					var right = cs(expression, querySet, namespaces);
					list.add(csStatement);
					csStatement.setExpression(csAssignment);
					csAssignment.setLeft(left);
					csAssignment.setRight(right);
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
		systemClass = clazz;
		
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
					var accesses = query.get(component);
					var totalAccess = "ReadOnly";
					if (accesses.contains(AccessKind.write))
					{
						totalAccess = "ReadWrite";
					}
					
					function.getArguments().add(argument(function(totalAccess, new String[] {unityName(component, namespaces)})));
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
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Systems/"+system.getName()+".cs");
	}
	
	private void addStatements(List<Statement> statements, List<m.csharp.Statement> list, QuerySet querySet, HashSet<String> namespaces)
	{
		var tail = new ArrayList<m.csharp.Statement>();
		
		for (var statement : statements)
		{
			if (statement instanceof Forall)
			{
				var forall = (Forall) statement;
				
				var variable = forall.getVariable();
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
				var variable = assignment.getAtom();
				var expression = assignment.getExpression();
				
				if (variable instanceof Variable)
				{
					var a = (Variable) variable;
					var ass = declarator(a.getName());
					var declaration = declaration(ass);
					var type = "";
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
						type = "NativeList<Entity>";
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
						type = "float";
						ass.setValue(cs(expression, querySet, namespaces));
						list.add(cs);
					}
				}
				else if (variable instanceof Cell)
				{
					var access = (Cell) variable;
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
			else if (name.equals("create"))
			{
				var prefab = cs(e.getArguments().get(0), querySet, namespaces);
				
				return access(variable("EntityManager"),function("Instantiate", argument(prefab)));
			}
			else if (name.equals("delete"))
			{
				var prefab = cs(e.getArguments().get(0), querySet, namespaces);
				
				return access(variable("EntityManager"), function("DestroyEntity", argument(prefab)));
			}
			else if (name.equals("add"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				if (!mainThread)
				{
					//querySet.add(variable, "Entity", AccessKind.read);
					return access(variable("commandBuffer"),function("AddComponent", new String[] {component}, argument(variable("entity_"+variable))));
				}
				else
				{
					return access(variable("EntityManager"),function("AddComponent", new String[] {component}, argument(variable("entity_"+variable))));
				}
			}
			else if (name.equals("remove"))
			{
				var component = ((Variable)e.getArguments().get(0)).getName();
				var variable = ((Variable)e.getArguments().get(1)).getName();
				
				querySet.add(variable, component, AccessKind.read);
				if (!mainThread)
				{
					//querySet.add(variable, "Entity", AccessKind.read);
					return access(variable("commandBuffer"),function("RemoveComponent", new String[] {component},argument(variable("entity_"+variable))));
				}
				else
				{
					return access(variable("EntityManager"),function("RemoveComponent", new String[] {component}, argument(variable("entity_"+variable))));
				}
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
		else if (expression instanceof Cell)
		{
			var access = (Cell) expression;
			querySet.add(access.getEntity(), access.getComponent(), AccessKind.read);
			var csExpression = csharp.createAccessExpression();
			var component = csharp.createVariable();
			var field = csharp.createVariable();
			csExpression.setLeft(component);
			csExpression.setRight(field);
			component.setName(access.getComponent()+"_"+access.getEntity());
			field.setName(fieldName(access.getComponent()));
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
			
			var selection = csharp.createSelection();
		}
		else if (expression instanceof ExplicitSet)
		{
			var e = (ExplicitSet) expression;
		}
		return null;
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
			case text: return "string";
			case type: return "Type";
		}
		return "None";
	}
	
	private void conversion()
	{
		var engineComponents = new String[] {"Camera", "Light", "PanelScaler", "PanelRenderer"};
		var unit = csharp.createCompilationUnit();
		var namespaces = new String[]{"UnityEngine","Unity.Entities", "Unity.UIElements.Runtime"};
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
		var access = csharp.createAccessExpression();
		var accessLeft = csharp.createVariable();
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
		var component = csharp.createVariable();
		lambda.getStatements().add(addStatement);
		addStatement.setExpression(addFunction);
		addFunction.getArguments().add(componentArgument);
		componentArgument.setValue(component);
		addFunction.setName("AddHybridComponent");
		component.setName("component");
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/EngineComponentConversion.cs");
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
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/SystemGroups.cs");
	}
	
	private void inputSystem()
	{
		serialize("inputValue", float1, true);
		serialize("inputVector", float2, true);
		serialize("inputTriggered", bool, true);
		serialize("input", input, true);
		
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("UnityEngine.InputSystem"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "ReadInput", new String[] {"JobComponentSystem"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.csharp.Expression[] {typeof("Engine")}));
		
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		clazz.getMembers().add(onUpdate);
		
		var lambda = csharp.createLambda();
		var run = access(access(access(variable("Entities"),function("ForEach",argument(lambda))),function("WithoutBurst")), function("Run"));
		
		onUpdate.getStatements().add(statement(run));
		
		lambda.getParameters().add(parameter("input", "input"));
		lambda.getParameters().add(refParameter("inputValue", "inputValue"));
		
		lambda.getStatements().add(declaration(declarator("action", access(variable("input"), variable("Value")))));
		
		lambda.getStatements().add(statement(access(variable("action"),function("Enable"))));
		var field = access(variable("inputValue"),variable("Value"));
		m.csharp.Expression read = access(variable("action"),function("ReadValue", new String[] {"float"}));
		
		lambda.getStatements().add(statement(assignment(field,read)));
		
		var trigger = csharp.createLambda();
		var triggerRun = access(access(access(variable("Entities"),function("ForEach",argument(trigger))),function("WithoutBurst")), function("Run"));
		
		onUpdate.getStatements().add(statement(triggerRun));
		
		trigger.getParameters().add(parameter("input", "input"));
		trigger.getParameters().add(refParameter("inputTriggered", "inputTriggered"));
		
		trigger.getStatements().add(declaration(declarator("action", access(variable("input"), variable("Value")))));
		
		trigger.getStatements().add(statement(access(variable("action"),function("Enable"))));
		var triggerField = access(variable("inputTriggered"),variable("Value"));
		var triggerRead = equality(access(variable("action"),variable("phase")), access(variable("InputActionPhase"), variable("Started")));
		
		trigger.getStatements().add(statement(assignment(triggerField,triggerRead)));
		/*
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
						m.csharp.Expression read = null;
						
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
		*/
		
		
		var returnDefault = csharp.createReturn();
		var defaultValue = csharp.createDefault();
		onUpdate.getStatements().add(returnDefault);
		returnDefault.setExpression(defaultValue);
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/ReadInput.cs");
	}
	
	private void timers()
	{
		serialize("timer", float1, true);
		serialize("elapsed", float1, true);
		serialize("timeout", bool, true);
		
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "TickTimers", new String[] {"JobComponentSystem"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.csharp.Expression[] {typeof("Engine")}));
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		
		onUpdate.getStatements().add(declaration("float", declarator("deltaTime", access(variable("Time"), variable("DeltaTime")))));
		clazz.getMembers().add(onUpdate);
		/*
		for (var elapsed : MValidator.components.keySet())
		{
			if (elapsed.endsWith("Elapsed"))
			{
				var root = elapsed.substring(0, elapsed.lastIndexOf("Elapsed"));
				String total = root;
				String signal = root+"Timeout";
				
				var totalExists = MValidator.components.containsKey(total);
				var signalExists = MValidator.components.containsKey(signal);
				
				var elapsedLambda = csharp.createLambda();
				elapsedLambda.getParameters().add(refParameter(elapsed, elapsed));
				var addDelta = assignment(access(variable(elapsed), variable("Value")), AssignmentKind.INCREASE, variable("deltaTime"));
				elapsedLambda.getStatements().add(statement(addDelta));
				
				var elapsedRun = access(access(access(variable("Entities"),function("ForEach",argument(elapsedLambda))),function("WithoutBurst")), function("Run"));
				onUpdate.getStatements().add(statement(elapsedRun));

				if (totalExists && signalExists)
				{
					var signalLambda = csharp.createLambda();
					signalLambda.getParameters().add(parameter(total,total));
					signalLambda.getParameters().add(refParameter(elapsed, elapsed));
					signalLambda.getParameters().add(refParameter(signal, signal));
					var setSignal = assignment(access(variable(signal), variable("Value")), AssignmentKind.SET, comparison(access(variable(elapsed), variable("Value")), ComparisonKind.GREATER_OR_EQUAL, access(variable(total), variable("Value"))));
					signalLambda.getStatements().add(statement(setSignal));
					
					var signalRun = access(access(access(variable("Entities"),function("ForEach",argument(signalLambda))),function("WithoutBurst")), function("Run"));
					onUpdate.getStatements().add(statement(signalRun));
				}
				
				if (totalExists)
				{
					var cycleLambda = csharp.createLambda();
					cycleLambda.getParameters().add(parameter(total,total));
					cycleLambda.getParameters().add(refParameter(elapsed, elapsed));
					var cycleRun = access(access(access(variable("Entities"),function("ForEach",argument(cycleLambda))),function("WithoutBurst")), function("Run"));
					onUpdate.getStatements().add(statement(cycleRun));
					
					var selection = csharp.createSelection();
					var ifBranch = csharp.createBranch();
					selection.getBranches().add(ifBranch);
					ifBranch.setCondition(comparison(access(variable(elapsed), variable("Value")), ComparisonKind.GREATER_OR_EQUAL, access(variable(total), variable("Value"))));
					ifBranch.getStatements().add(statement(assignment(access(variable(elapsed), variable("Value")), AssignmentKind.DECREASE, access(variable(total), variable("Value")))));
					
					cycleLambda.getStatements().add(selection);

				}

				
			}
		}*/
		
		onUpdate.getStatements().add(returnStatement(defaultExpression()));
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/TickTimers.cs");
	}
	
	private void Physics()
	{
		serialize("Collisions", entityList, true);
		serialize("CollisionEntries", entityList, true);
		serialize("CollisionExits", entityList, true);
		
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
		clazz.getAttributes().add(attribute("UpdateInGroup",new m.csharp.Expression[] {typeof("Engine")}));
		clazz.getMembers().add(field("BuildPhysicsWorld", declarator("build")));
		clazz.getMembers().add(field("StepPhysicsWorld", declarator("step")));
		
		var job = struct("CollisionJob", new String[] {"ICollisionEventsJob"});
		
		
		var world = field(new Modifier[] {PUBLIC}, "PhysicsWorld", declarator("world"));
		world.getAttributes().add(attribute("ReadOnly"));
		
		job.getMembers().add(world);
		job.getMembers().add(field(new Modifier[] {PUBLIC}, "NativeList<CollisionData>", declarator("collisionData")));
		
		var execute = method(new Modifier[] {PUBLIC}, "void", "Execute", new Parameter[] {parameter("CollisionEvent", "collisionEvent")});
		job.getMembers().add(execute);
		
		var newData = creation("CollisionData", member("A", access(access(variable("collisionEvent"), variable("Entities")),variable("EntityA"))), member("B", access(access(variable("collisionEvent"), variable("Entities")),variable("EntityB"))));
		var addData = statement(access(variable("collisionData"),function("Add", argument(newData))));
		execute.getStatements().add(addData);
		
		var onCreate = method(new Modifier[] {PROTECTED, OVERRIDE}, "void", "OnCreate");
		onCreate.getStatements().add(statement(assignment(variable("build"),access(variable("World"),function("GetOrCreateSystem", new String[] {"BuildPhysicsWorld"})))));
		onCreate.getStatements().add(statement(assignment(variable("step"),access(variable("World"),function("GetOrCreateSystem", new String[] {"StepPhysicsWorld"})))));

		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		
		var clearLambda = lambda(new Parameter[] {parameter("DynamicBuffer<Collisions>", "buffer")});
		clearLambda.getStatements().add(statement(access(variable("buffer"),function("Clear"))));
		
		var clearRun = access(access(access(variable("Entities"),function("ForEach",argument(clearLambda))),function("WithoutBurst")), function("Run"));
		
		
		var lambda = lambda(new Parameter[] {refParameter("ISimulation", "simulation"), refParameter("PhysicsWorld", "world"), parameter("JobHandle", "inDeps")});
		lambda.getStatements().add(statement(clearRun));
		lambda.getStatements().add(declaration(declarator("collisionData",creation("NativeList<CollisionData>", argument(access(variable("Allocator"),variable("TempJob")))))));
		var schedule = function("Schedule", argument(variable("simulation")), refArgument(variable("world")), argument(variable("inDeps")));
		lambda.getStatements().add(declaration(declarator("job",access(creation("CollisionJob",member("collisionData", variable("collisionData")), member("world", variable("world"))),schedule))));
		
		lambda.getStatements().add(statement(access(variable("job"),function("Complete"))));

		var accessA = access(variable("EntityManager"),function("GetBuffer", new String[] {"Collisions"}, argument(access(variable("data"),variable("A")))));
		var accessB = access(variable("EntityManager"),function("GetBuffer", new String[] {"Collisions"}, argument(access(variable("data"),variable("B")))));
		var addB = access(accessA, function("Add",argument(creation("Collisions", member("Value", access(variable("data"),variable("B")))))));
		var addA = access(accessB, function("Add",argument(creation("Collisions", member("Value", access(variable("data"),variable("A")))))));
		
		var foreach = foreach("data",variable("collisionData"));
		var ifA = ifStatement(access(variable("EntityManager"),function("HasComponent", new String[] {"Collisions"}, argument(access(variable("data"),variable("A"))))));
		ifA.getBranches().get(0).getStatements().add(statement(addB));
		var ifB = ifStatement(access(variable("EntityManager"),function("HasComponent", new String[] {"Collisions"}, argument(access(variable("data"),variable("B"))))));
		ifB.getBranches().get(0).getStatements().add(statement(addA));
		
		foreach.getStatements().add(ifA);
		foreach.getStatements().add(ifB);
		lambda.getStatements().add(foreach);
		lambda.getStatements().add(statement(access(variable("collisionData"), function("Dispose"))));
		lambda.getStatements().add(returnStatement(variable("job")));
		
		var declareLambda = declaration("SimulationCallbacks.Callback", declarator("testCollisions", lambda));
		onUpdate.getStatements().add(declareLambda);
		
		var jacobians = access(access(variable("SimulationCallbacks"), variable("Phase")),variable("PostSolveJacobians"));
		var enqueue = access(variable("step"), function("EnqueueCallback", new Argument[] {argument(jacobians), argument(variable("testCollisions")), argument(variable("inputDependencies"))}));
		onUpdate.getStatements().add(statement(enqueue));
		
		onUpdate.getStatements().add(returnStatement(variable("inputDependencies")));
		
		clazz.getMembers().add(onCreate);
		clazz.getMembers().add(onUpdate);
		clazz.getMembers().add(job);
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/DetectCollisions.cs");
	}
	
	private void extensions()
	{
		var unit = unit();
		
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Collections"));
		
		var clazz = clazz(new Modifier[] {PUBLIC, STATIC}, "Extensions");
		unit.getTypes().add(clazz);
		
		var contains = method(new Modifier[] {PUBLIC, STATIC}, "bool", "Contains", new String[] {"T"}, new Parameter[] {thisParameter("DynamicBuffer<T>","buffer"), parameter("Entity", "entity")});
		contains.getTypeConstraints().add(typeStructConstraint("T", new String[] {"IEntity"}));
		
		var f = forStatement(declaration(declarator("i",floatLiteral("0"))),comparison(variable("i"), ComparisonKind.LOWER, access(variable("buffer"),variable("Length"))), increment(variable("i")));
		var ifStatement = ifStatement(equality(access(index("buffer",variable("i")),variable("Value")), variable("entity")));
		ifStatement.getBranches().get(0).getStatements().add(returnStatement(booleanLiteral("true")));
		f.getStatements().add(ifStatement);
		contains.getStatements().add(f);
		contains.getStatements().add(returnStatement(booleanLiteral("false")));
		clazz.getMembers().add(contains);
		
		var iEntity = Interface(new Modifier[] {PUBLIC}, "IEntity");
		iEntity.getMembers().add(getter("Entity", "Value"));
		
		unit.getTypes().add(iEntity);
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/Extensions.cs");
	}
	
	private void ui()
	{
		serialize("number", float1, true);
		serialize("text", text, true);
		serialize("label", text, true);
		serialize("labelClass", text, true);
		
		var unit = unit();
		unit.getUsings().add(namespaceUsing("UnityEngine"));
		unit.getUsings().add(namespaceUsing("UnityEngine.UIElements"));
		unit.getUsings().add(namespaceUsing("Unity.UIElements.Runtime"));
		unit.getUsings().add(namespaceUsing("Unity.Entities"));
		unit.getUsings().add(namespaceUsing("Unity.Jobs"));
		
		var clazz = clazz(new Modifier[] {PUBLIC}, "UI", new String[] {"JobComponentSystem"});
		clazz.getAttributes().add(attribute("UpdateInGroup", new m.csharp.Expression[] {typeof("Engine")}));
		
		unit.getTypes().add(clazz);
		
		var onUpdate = method(new Modifier[] {PROTECTED,OVERRIDE}, "JobHandle", "OnUpdate", new Parameter[] {parameter("JobHandle", "inputDependencies")});
		clazz.getMembers().add(onUpdate);
		
		var lambda = csharp.createLambda();
		var run = access(access(access(variable("Entities"),function("ForEach",argument(lambda))),function("WithoutBurst")), function("Run"));
		
		onUpdate.getStatements().add(statement(run));
		
		lambda.getParameters().add(parameter("PanelRenderer", "panel"));
		
		var inner = lambda(new Parameter[] {parameter("label", "label"),parameter("number","number")});
		var innerRun = access(access(access(variable("Entities"),function("ForEach",argument(inner))),function("WithoutBurst")), function("Run"));

		lambda.getStatements().add(statement(innerRun));
		
		var foreachLabel = lambda(new Parameter[] {parameter("Label", "l")});
		foreachLabel.getStatements().add(statement(assignment(access(variable("l"),variable("text")), access(access(variable("number"),variable("Value")),function("ToString")))));
		inner.getStatements().add(statement(access(access(access(variable("panel"),variable("visualTree")),function("Query", new String[] {"Label"}, argument(access(variable("label"),variable("Value"))))),function("ForEach", argument(foreachLabel)))));
		var returnDefault = csharp.createReturn();
		var defaultValue = csharp.createDefault();
		onUpdate.getStatements().add(returnDefault);
		returnDefault.setExpression(defaultValue);
		
		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Engine/Systems/UI.cs");
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
