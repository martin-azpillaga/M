package m.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import m.validation.MValidator;

public class MGenerator extends AbstractGenerator
{
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context)
	{
		var game = MValidator.game;
		if (game != null)
		{
			new Unity().generate(game,fsa);
			new Godot().generate(game,fsa);
		}
	}
}


