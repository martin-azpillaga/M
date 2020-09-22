package m.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;

import com.google.inject.Inject;

import m.main.Game;
import m.validation.MValidator;

public class MGenerator extends AbstractGenerator
{
	@Inject
	MValidator validator;

	@Inject
	JavaIoFileSystemAccess fileSystem;
	
	
	public void doGenerate(Resource resource, IFileSystemAccess2 fileSystem, IGeneratorContext context)
	{
		var game = validator.getGame();
		if (game != null)
		{
			new Unity().generate(game, fileSystem);
		}
	}

	public void generate(Game game, Engine engine, String path)
	{
		fileSystem.setOutputPath(path);
		switch (engine)
		{
			case Unity: new Unity().generate(game, fileSystem); break;
			case Unreal: break;
			case Godot: break;
		}
	}
}
