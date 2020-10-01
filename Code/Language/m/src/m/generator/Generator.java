package m.generator;

import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import com.google.inject.Inject;
import m.main.Game;

public class Generator
{
	@Inject
	JavaIoFileSystemAccess fileSystem;

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
}
