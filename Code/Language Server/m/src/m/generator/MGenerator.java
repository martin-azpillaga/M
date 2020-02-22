package m.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import game.Game;
import m.serializing.UnitySerializer;

public class MGenerator extends AbstractGenerator 
{
	@Override
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context)
	{
		if (resource.getContents().size() == 0) return;
		
		var game = (Game) resource.getContents().get(0);
		
		var unitySerializer = new UnitySerializer();
		
		unitySerializer.serialize(game, fsa);
	}
}
