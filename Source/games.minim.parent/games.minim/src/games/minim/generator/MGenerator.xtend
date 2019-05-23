package games.minim.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.emf.common.util.URI
import games.minim.m.Game
import org.eclipse.emf.common.util.BasicEList
import games.minim.m.Entity
import games.minim.scoping.Cleaning
import games.minim.m.Control

class MGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		println("generate "+resource.contents.get(0).class)
		var content = resource.contents.get(0)
		if (content instanceof games.minim.m.Game)
		{
			var file = resource.URI.path
			var projectPath = file.substring(9)
			var projectName = projectPath.substring(1,projectPath.substring(1).indexOf('/')+1)
			
			var deduction = resource.resourceSet.getResource(URI.createURI(Cleaning.deducedURI),false).contents.get(0) as Game
			deduction.entities.clear()
			deduction.title = projectName
			deduction.systems.clear
			deduction.entities.clear
			var s = new BasicEList<Control>
			var e = new BasicEList<Entity>
			var m = new BasicEList<Entity>
			for (member : content.mainEntities)
			{
				m.add(member)
			}
			for (member : content.systems)
			{
				s.add(member)
			}
			for (member : content.entities)
			{
				e.add(member)
			}
			for (sys : s)
			{
				deduction.systems.add(sys)
			}
			for (ent : e)
			{
				deduction.entities.add(ent)
			}
			for (mainEntity : m)
			{
				deduction.mainEntities.add(mainEntity)
			}
			(new HybridUnity).represent(deduction, fsa)
		}
	}	
}

interface Framework
{
	def void represent(Game game,IFileSystemAccess2 fileSystem)
}