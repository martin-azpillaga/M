package games.minim.generator

import games.minim.m.Game
import games.minim.scoping.StandardLibrary
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

class MGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var content = resource.contents.get(0)
		if (content instanceof Game)
		{
			var file = resource.URI.path
			var projectPath = file.substring(9)
			var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
			
			var deduction = resource.resourceSet.getResource(URI.createURI(StandardLibrary.deducedURI),false).contents.get(0) as Game
			deduction.title = projectName
			deduction.systems.clear
			deduction.entities.clear
			deduction.mainEntities.clear
			
			deduction.systems.addAll(content.systems)
			deduction.entities.addAll(content.entities)
			deduction.mainEntities.addAll(content.mainEntities)
			
			(new HybridUnity).represent(deduction, fsa)
		}
	}	
}

interface Framework
{
	def void represent (Game game, IFileSystemAccess2 fileSystem)
}