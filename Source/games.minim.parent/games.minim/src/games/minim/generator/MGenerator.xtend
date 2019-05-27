package games.minim.generator

import games.minim.m.Game
import games.minim.scoping.StandardLibrary
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

class MGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		var file = resource.URI.path
		var projectPath = file.substring(9)
		var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
		
		var deduction = StandardLibrary.deductionOf.get(projectName)
		var game = deduction.contents.get(0) as Game
		(new HybridUnity).represent(game, fsa)
	}	
}

interface Framework
{
	def void represent (Game game, IFileSystemAccess2 fileSystem)
}