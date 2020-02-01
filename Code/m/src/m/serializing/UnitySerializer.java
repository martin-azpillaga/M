package m.serializing;

import static m.csharp.EnumModifier.*;
import static m.csharp.ClassModifier.*;
import static m.csharp.StructModifier.*;
import static m.csharp.InterfaceModifier.*;
import static m.csharp.MethodModifier.*;
import static m.csharp.FieldModifier.*;
import static m.validation.Type.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.JSONRuntimeModule;
import m.YAMLRuntimeModule;
import m.csharp.ClassModifier;
import m.csharp.CompilationUnit;
import m.csharp.CsharpFactory;
import m.csharp.FieldModifier;
import m.csharp.MethodModifier;
import m.csharp.StructModifier;
import m.json.JsonFactory;
import m.json.Member;
import m.m.Archetype;
import m.m.Game;
import m.m.System;
import m.modular.AssignmentKind;
import m.modular.ModularFactory;
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
		
		for (var archetype : game.getArchetypes())
		{
			serialize(archetype);
		}
		
		for (var system : game.getSystems())
		{
			serialize(system);
		}
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
		members.add(dependency("com.unity.ide.rider","1.1.4"));
		members.add(dependency("com.unity.ide.vscode", "1.1.4"));
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
			struct.getModifiers().add(StructModifier.PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IComponentData");
			
			var attributeSection = csharp.createAttributeSection();
			var generate = csharp.createAttribute();
			generate.setName("GenerateAuthoringComponent");
			attributeSection.getAttributes().add(generate);
			struct.getAttributes().add(attributeSection);
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createFieldDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(FieldModifier.PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setName("Value");
			struct.getMembers().add(value);
			
			unit.getTypes().add(struct);
		}
		else if (type == tag)
		{
			var struct = csharp.createStruct();
			struct.getModifiers().add(StructModifier.PUBLIC);
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
			struct.getModifiers().add(StructModifier.PUBLIC);
			struct.setName(component);
			struct.getSuperTypes().add("IBufferElementData");
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createFieldDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(FieldModifier.PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setName("Value");
			struct.getMembers().add(value);
			
			var clazz = csharp.createClass();
			clazz.getModifiers().add(ClassModifier.PUBLIC);
			clazz.setName(component+"Authoring");
			clazz.getSuperTypes().add("MonoBehaviour");
			clazz.getSuperTypes().add("IConvertGameObjectToEntity");
			clazz.getSuperTypes().add("IDeclareReferencedPrefabs");
			
			var field = csharp.createField();
			var fieldDeclarator = csharp.createFieldDeclarator();
			field.getDeclarators().add(fieldDeclarator);
			field.getModifiers().add(FieldModifier.PUBLIC);
			field.setType("List<GameObject>");
			fieldDeclarator.setName("Value");
			clazz.getMembers().add(field);
			
			var method = csharp.createMethod();
			method.getModifiers().add(MethodModifier.PUBLIC);
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
			
			var addbuffer = csharp.createCall();
			var addAccess = modular.createAccessExpression();
			var addAccessLeft = modular.createVariable();
			var addAccessRight = csharp.createParameterizedFunction();
			var addAccessArgument = csharp.createArgument();
			var addAccessValue = modular.createVariable();
			method.getStatements().add(addbuffer);
			addbuffer.setCall(addAccess);
			addAccess.setLeft(addAccessLeft);
			addAccess.setRight(addAccessRight);
			addAccessRight.getArguments().add(addAccessArgument);
			addAccessArgument.setValue(addAccessValue);
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
			
			var declareBoard = csharp.createDeclaration();
			var newBoard = csharp.createParameterizedFunction();
			foreachV.getStatements().add(declareBoard);
			declareBoard.setExpression(newBoard);
			declareBoard.setVariable(component);
			declareBoard.setNew(true);
			declareBoard.setKind(AssignmentKind.SET);
			newBoard.setName(component);
			
			
			var declare = csharp.createMethod();
			declare.getModifiers().add(MethodModifier.PUBLIC);
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
			var action = csharp.createCall();
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
			action.setCall(access);
			foreach.getStatements().add(action);
			declare.getStatements().add(foreach);
			
			unit.getTypes().add(struct);
			unit.getTypes().add(clazz);
		}
		else
		{
			namespaces.add("UnityEngine");
			
			var clazz = csharp.createClass();
			clazz.getModifiers().add(ClassModifier.PUBLIC);
			clazz.setName(component+"Authoring");
			clazz.getSuperTypes().add("MonoBehaviour");
			
			var value = csharp.createField();
			var valueDeclarator = csharp.createFieldDeclarator();
			value.getDeclarators().add(valueDeclarator);
			value.getModifiers().add(FieldModifier.PUBLIC);
			value.setType(unityName(type));
			valueDeclarator.setName("Value");
			clazz.getMembers().add(value);
			unit.getTypes().add(clazz);
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
}
