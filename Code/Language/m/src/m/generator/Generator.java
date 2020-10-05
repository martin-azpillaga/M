package m.generator;

import org.eclipse.xtext.generator.JavaIoFileSystemAccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.inject.Inject;
import m.MStandaloneSetup;

import m.model.Game;

public class Generator
{
	@Inject
	JavaIoFileSystemAccess fileSystem;

	public Generator()
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		injector.injectMembers(this);
	}

	public void generate(Game game, Engine engine, String path)
	{
		fileSystem.setOutputPath(path);
		switch (engine)
		{
			case UNITY: new Unity().generate(game, fileSystem); break;
			case UNREAL: break;
			case GODOT: break;
		}
	}

	public void generate(Game game, Path path)
	{
		for (var engine : Engine.values())
		{
			var defaultPath = Paths.get(path.toString(), "Output", engine.identifier).toString();
			generate(game, engine, defaultPath);
		}
	}
}
