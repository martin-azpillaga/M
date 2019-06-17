package games.minim.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import com.google.inject.Guice
import games.minim.XMRuntimeModule
import org.eclipse.xtext.serializer.impl.Serializer
import games.minim.m.Game
import org.eclipse.core.resources.ResourcesPlugin
import games.minim.scoping.StandardLibrary
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IFile
import org.eclipse.emf.common.util.URI
import games.minim.MRuntimeModule

class XMGenerator extends AbstractGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var injector = Guice.createInjector(new  MRuntimeModule());  
		var serializer = injector.getInstance(Serializer);  
		//var s = serializer.serialize(resource.contents.get(0) as Game); 
		//fsa.generateFile('generated.game', s);
		
		var file = resource.URI.path
		var projectPath = file.substring(9)
		var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
		var project = ResourcesPlugin.workspace.root.getProject(projectName)
		if (StandardLibrary.deductionOf === null) return;
		var deduction = StandardLibrary.deductionOf.get(projectName)
		if (deduction === null) return;
		var game = deduction.contents.get(0) as Game
		game.entities.clear
		game.systems.clear
		game.mainEntities.clear
		for (member : project.members)
		{
			if (member instanceof IFolder)
			{
				for (m : member.members)
				{
					//analyze(m,model)
				}
			}
			else if (member instanceof IFile)
			{
				if(member.name.endsWith('.xm'))
				{
					val memberUri = URI.createURI('platform:/resource'+member.fullPath)
					
					var otherContents = resource.resourceSet.getResource(memberUri,true).contents
					if (otherContents.size > 0)
					{
						var other = otherContents.get(0) as Game
						game.entities.addAll(other.entities)
						game.systems.addAll(other.systems)
						game.mainEntities.addAll(other.mainEntities)
					}
				}
			}
		}
		(new HybridUnity).represent(game, fsa)
		println("Generated")
	}
}
