package m.serializing;

import static m.csharp.Visibility.PUBLIC;
import static m.validation.Type.input;
import static m.validation.Type.tag;

import java.util.HashSet;
import java.util.UUID;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.YAMLRuntimeModule;
import m.csharp.CsharpFactory;
import m.csharp.NamespaceType;
import m.m.Archetype;
import m.m.Game;
import m.m.System;
import m.modular.ModularFactory;
import m.validation.MValidator;
import m.validation.StandardLibrary;
import m.validation.Type;
import m.yaml.YamlFactory;

public class UnitySerializer
{
	ModularFactory modular = ModularFactory.eINSTANCE;
	CsharpFactory csharp = CsharpFactory.eINSTANCE;
	YamlFactory yaml = YamlFactory.eINSTANCE;
	CSharpRuntimeModule csharpModule;
	YAMLRuntimeModule yamlModule;
	IFileSystemAccess2 fsa;
	
	public void serialize(Game game, IFileSystemAccess2 fsa)
	{
		csharpModule = new CSharpRuntimeModule();
		yamlModule = new YAMLRuntimeModule();
		this.fsa = fsa;
		
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
	
	public void serialize(String component, Type type)
	{
		var unit = csharp.createCompilationUnit();
		
		var namespaces = new HashSet<String>();
		namespaces.add("Unity.Entities");
		
		NamespaceType member;
		
		if (type.isNumeric() || type == tag || type.isList())
		{
			member = csharp.createStruct();
			member.getAnnotations().add("GenerateAuthoringComponent");
			
			if (type.isList())
			{
				member.getSuperTypes().add("IBufferElementData");
			}
			else
			{
				member.getSuperTypes().add("IComponentData");
			}
		}
		else
		{
			member = csharp.createClass();
			member.getSuperTypes().add("MonoBehaviour");
			namespaces.add("UnityEngine");
		}
		
		if (type != tag)
		{
			var valueField = csharp.createField();
			valueField.setVisibility(PUBLIC);
			valueField.setType(unityName(type));
			valueField.setName("Value");
			member.getMembers().add(valueField);
		}
		
		if (type == input)
		{
			namespaces.add("Unity.Input");
		}
		
		member.setVisibility(PUBLIC);
		member.setName(component);
		unit.getTypes().add(member);

		for (var namespace : namespaces)
		{
			var using = csharp.createUsing();
			using.setName(namespace);
			unit.getUsings().add(using);			
		}
		
		var meta = yaml.createFile();
		var fileFormat = yaml.createKeyValue();
		fileFormat.setKey("fileFormatVersion");
		var value = yaml.createFloat();
		value.setValue("2");
		fileFormat.setValue(value);
		meta.getNodes().add(fileFormat);
		
		var guid = yaml.createKeyValue();
		guid.setKey("guid");
		var word = yaml.createGuid();
		word.setValue(uuid(component+"component"));
		guid.setValue(word);
		meta.getNodes().add(guid);

		GenericSerializer.generate(meta, yamlModule, fsa, "Unity/Assets/Code/Components/"+component+".cs.meta");

		GenericSerializer.generate(unit, csharpModule, fsa, "Unity/Assets/Code/Components/"+component+".cs");
	}
	
	private void serialize(Archetype archetype)
	{
		var file = yaml.createFile();
		var guid = yaml.createKeyValue();
		guid.setKey("guid");
		var word = yaml.createGuid();
		word.setValue(uuid(archetype.getName()+"component"));
		guid.setValue(word);
		file.getNodes().add(guid);
		GenericSerializer.generate(file, yamlModule, fsa, "Unity/Assets/Design/Archetypes/"+archetype.getName()+".prefab");
		
		var meta = yaml.createFile();
		var guid2 = yaml.createKeyValue();
		guid.setKey("guid");
		var word2 = yaml.createGuid();
		word2.setValue(uuid(archetype.getName()+"component"));
		guid2.setValue(word2);
		meta.getNodes().add(guid);
		GenericSerializer.generate(meta, yamlModule, fsa, "Unity/Assets/Design/Archetypes/"+archetype.getName()+".prefab.meta");
	}
	
	private void serialize(System system)
	{
		var file = yaml.createFile();
		var guid = yaml.createKeyValue();
		guid.setKey("guid");
		var word = yaml.createGuid();
		word.setValue(uuid(system.getName()+"component"));
		guid.setValue(word);
		file.getNodes().add(guid);
		GenericSerializer.generate(file, yamlModule, fsa, "Unity/Assets/Code/Systems/"+system.getName()+".prefab");
		
		var meta = yaml.createFile();
		var guid2 = yaml.createKeyValue();
		guid.setKey("guid");
		var word2 = yaml.createGuid();
		word2.setValue(uuid(system.getName()+"component"));
		guid2.setValue(word2);
		meta.getNodes().add(guid);
		GenericSerializer.generate(meta, yamlModule, fsa, "Unity/Assets/Code/Systems/"+system.getName()+".prefab.meta");
	}
	
	private String uuid(String string)
	{
		return UUID.nameUUIDFromBytes(string.getBytes()).toString().replace("-","");
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
			case input: return "PlayerInputAction";
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
