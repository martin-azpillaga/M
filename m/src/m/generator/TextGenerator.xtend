package m.generator

import m.m.Game
import m.transformation.ComponentGenerator
import m.transformation.PackageGenerator
import m.transformation.SystemGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import m.transformation.ArtGenerator
import m.transformation.EntityGenerator
import m.transformation.SettingsGenerator
import m.transformation.TypeInference
import java.util.HashMap
import m.transformation.Type
import m.m.MFactory
import m.transformation.TranslateTestua
import java.util.ArrayList
import m.m.Component
import m.TextRuntimeModule
import static extension m.transformation.GenericSerializer.*
import m.transformation.BuiltInLibrary
import org.eclipse.emf.common.util.URI
import m.transformation.BlockGenerator

class TextGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var startTime = System.currentTimeMillis();
		
		var typeInference = new TypeInference
		var types = new HashMap<String,Type>
		var game = resource.contents.head as Game
		types = typeInference.infer(game)
		
		fsa.generateFile('src-gen/'+game.entities.size+ ' entities.done','')
		
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
						var euskal = #[BuiltInLibrary.position, BuiltInLibrary.rotation].map[euskara]
						var euska = BuiltInLibrary.renderers.map[euskara]
						
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
			
			var translateTestua = new TranslateTestua
			translateTestua.translate(game, fsa, resource.URI.lastSegment.replace('.text','.testua'))
			
			var blockGenerator = new BlockGenerator
			blockGenerator.generate(game, fsa, 'Blocks/')
						
			generate(game, new TextRuntimeModule, fsa, 'Text/'+resource.URI.lastSegment)
		}
		stopTime = System.currentTimeMillis();
		runTime = stopTime - startTime;
		System.out.println("Run time: " + runTime);
	}
}
