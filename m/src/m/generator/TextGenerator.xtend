package m.generator

import m.m.Game
import m.instancer.ComponentGenerator
import m.instancer.PackageGenerator
import m.instancer.SystemGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import m.instancer.ArtGenerator
import m.instancer.EntityGenerator
import java.util.HashMap
import m.m.MFactory
import m.instancer.TranslateTestua
import java.util.ArrayList
import m.m.Component
import m.TextRuntimeModule
import static extension m.modeler.GenericSerializer.*
import m.instancer.BlockGenerator
import m.validation.TypeInference
import m.validation.Type
import m.instancer.SettingsGenerator

class TextGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var startTime = System.currentTimeMillis();
		
		var typeInference = new TypeInference
		var types = new HashMap<String,Type>
		
		
		var game = MFactory.eINSTANCE.createGame
		
		for (r : resource.resourceSet.resources)
		{
			if (r.contents.head instanceof Game)
			{
				var g = r.contents.head as Game
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
					game.systems.addAll(g.systems)
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
			
			var blockGenerator = new BlockGenerator
			blockGenerator.generate(game, fsa, 'Blocks/')
						
		}
		stopTime = System.currentTimeMillis();
		runTime = stopTime - startTime;
		System.out.println("Run time: " + runTime);
	}
}
