package m.transformation

import m.m.Game
import m.json.JsonFactory

import static m.transformation.GenericSerializer.*
import org.eclipse.xtext.generator.IFileSystemAccess2
import m.JsonRuntimeModule

class PackageGenerator 
{
	extension JsonFactory factory = JsonFactory.eINSTANCE
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	var jsonRuntime = new JsonRuntimeModule
	
	def generate(Game game, IFileSystemAccess2 fsa)
	{
		var root = createObject
		var dependencies = createField
		root.fields.add(dependencies)
		
		dependencies.name = "dependencies"
		var list = createObject
		dependencies.value = list
		
		list.add("com.unity.entities", "0.1.1-preview")
		list.add("com.unity.ide.vscode", "1.1.2")
		list.add("com.unity.inputsystem", "1.0.0-preview")
		list.add("com.unity.physics", "0.2.4-preview")
		list.add("com.unity.rendering.hybrid", "0.1.1-preview")

		var modules = #['ai','animation','assetbundle','audio','cloth','director','imageconversion','imgui','jsonserialize','particlesystem','physics','physics2d','screencapture','terrain','terrainphysics','tilemap','ui','uielements','umbra','unityanalytics','unitywebrequest','unitywebrequestassetbundle','unitywebrequestaudio','unitywebrequesttexture','unitywebrequestwww','vehicles','video','vr','wind','xr']
		for (module : modules)
		{
			list.add("com.unity.modules."+module, "1.0.0")
		}
		
		generate(root, jsonRuntime, fsa, 'Pure Unity/Packages/manifest.json')
	}
	
	def assemblies(Game game, IFileSystemAccess2 fsa)
	{
		if (game.systems.size > 0 || game.entities.size > 0)
		{
			var root = createObject
			root.add('name','Code')
			root.add('references',#['Unity.Entities','Unity.Entities.Hybrid','Unity.Mathematics','Unity.Burst','Unity.InputSystem','Unity.Physics', 'Unity.Transforms', 'Unity.Rendering.Hybrid'])
			
			generate(root, jsonRuntime, fsa, 'Pure Unity/Assets/Code/Code.asmdef')
		}
		if (game.systems.size > 0)
		{
			var root = createObject
			root.add('name','Test')
			root.add('references',#['Code', 'Unity.Entities','Unity.Entities.Hybrid','Unity.Mathematics','Unity.Burst','Unity.InputSystem','Unity.Physics', 'Unity.Transforms', 'Unity.Rendering.Hybrid'])
			
			generate(root, jsonRuntime, fsa, 'Pure Unity/Assets/Code/Tests/Test.asmdef')
		}
	}
	
}