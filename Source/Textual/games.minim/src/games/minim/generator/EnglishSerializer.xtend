package games.minim.generator

import games.minim.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import com.google.inject.Guice
import games.minim.MRuntimeModule
import org.eclipse.xtext.serializer.impl.Serializer

class EnglishSerializer implements Framework
{
	
	override represent(Game game, IFileSystemAccess2 fileSystem) 
	{
		var file = game.eResource.URI.path
		var projectPath = file.substring(9)
		var name = projectPath.substring(projectPath.indexOf('/')+1, projectPath.indexOf('.'))
		
		var injector = Guice.createInjector(new  MRuntimeModule());  
		var serializer = injector.getInstance(Serializer);  
		var s = serializer.serialize(game);  
		fileSystem.generateFile('Textual/English_'+name+'.m', s)
	}	
}