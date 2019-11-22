package m.generator

import java.util.ArrayList
import java.util.HashMap
import m.instancer.ArtGenerator
import m.instancer.BlocksInstancer
import m.instancer.ComponentGenerator
import m.instancer.EntityGenerator
import m.instancer.PackageGenerator
import m.instancer.SettingsGenerator
import m.instancer.SystemGenerator
import m.m.Component
import m.m.MFactory
import m.validation.Type
import m.validation.TypeInference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

class TextGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var startTime = System.currentTimeMillis();
		
		var typeInference = new TypeInference
		var types = new HashMap<String,Type>
		
		
		var game = MFactory.eINSTANCE.createModule
		
		for (r : resource.resourceSet.resources)
		{
			if (r.contents.head instanceof m.m.Module)
			{
				var g = r.contents.head as m.m.Module
				types = typeInference.infer(g)
				if (r.contents.size > 1)
				{
					var renderingN = #['position','rotation','scale','mesh','material','number']
					var physicsN = #['mass','restitution','friction','extents','radius', 'triggerZone']
		
					for (entity : g.entities)
					{
						var rendering = new ArrayList<Component>
						var physics = new ArrayList<Component>
						var tags = new ArrayList<Component>
						var rest = new ArrayList<Component>
					
						for (component : entity.components)
						{
							if (renderingN.contains(component.name))
							{
								rendering.add(component)
							}
							else if (physicsN.contains(component.name))
							{
								physics.add(component)
							}
							else if (component.value === null)
							{
								tags.add(component)
							}
							else
							{
								rest.add(component)
							}
						}
						entity.components.clear
						entity.components.addAll(rendering+physics+tags+rest)
					}
					
					game.entities.addAll(g.entities)
					game.procedures.addAll(g.procedures)
				}
			}
		}
		var stopTime = System.currentTimeMillis();
		var runTime = stopTime - startTime;
		System.out.println("Prepare time: " + runTime);
		
		if (types.keySet.size > 0)
		{
			
			var componentGenerator = new ComponentGenerator
			componentGenerator.generate(types, fsa)
			componentGenerator.metaFiles(types, fsa)
			
			var jsonGenerator = new PackageGenerator
			jsonGenerator.generate(game, fsa)
			jsonGenerator.assemblies(game, fsa)
			
			var artGenerator = new ArtGenerator
			artGenerator.generate(game, types, fsa, 'Pure Unity/Assets/Art/Meshes/', 'Hybrid Unity/Assets/Art/Meshes/')
			
			var entityGenerator = new EntityGenerator
			entityGenerator.generate(game, types, fsa)
			
			
			var settingsGenerator = new SettingsGenerator
			settingsGenerator.generate(game, fsa)
			
			var systemGenerator = new SystemGenerator
			systemGenerator.generate(game, types, fsa)
			
			var BlocksInstancer = new BlocksInstancer
			BlocksInstancer.generate(game, fsa, 'Blocks/')
						
		}
		stopTime = System.currentTimeMillis();
		runTime = stopTime - startTime;
		System.out.println("Run time: " + runTime);
	}
}
