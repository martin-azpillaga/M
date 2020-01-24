package m.serializing;

import java.util.HashSet;

import org.eclipse.xtext.generator.IFileSystemAccess2;

import m.CSharpRuntimeModule;
import m.csharp.CsharpFactory;
import m.csharp.NamespaceMember;
import m.csharp.NamespaceType;
import m.csharp.Struct;
import m.csharp.CompilationUnit;
import static m.csharp.Visibility.*;
import m.m.Game;
import m.modular.ModularFactory;
import m.validation.MValidator;
import m.validation.StandardLibrary;
import m.validation.Type;
import m.yaml.YamlFactory;
import static m.validation.Type.*;

public class UnitySerializer
{
	ModularFactory modular = ModularFactory.eINSTANCE;
	CsharpFactory csharp = CsharpFactory.eINSTANCE;
	YamlFactory yaml = YamlFactory.eINSTANCE;
	
	public void serialize(Game game, IFileSystemAccess2 fsa)
	{
		var csharpModule = new CSharpRuntimeModule();
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
				
				var file = serialize(component, type);
				GenericSerializer.generate(file, csharpModule, fsa, "Unity/Assets/Code/Components/"+component+".cs");
			
			}
		}
	}
	
	public CompilationUnit serialize(String component, Type type)
	{
		var model = csharp.createCompilationUnit();
		
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
		
		member.setVisibility(PUBLIC);
		member.setName(component);
		model.getTypes().add(member);
		
		
		
		
		for (var namespace : namespaces)
		{
			var using = csharp.createUsing();
			using.setName(namespace);
			model.getUsings().add(using);			
		}
		
		return model;
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
			case input: return "Unity.Input.PlayerInputAction";
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
