package m.generator;

import java.nio.file.Path;
import java.nio.file.Paths;

import m.model.Game;

public class Generator
{
	public void generate(Game game, Engine engine, String path)
	{
		Writer.setBaseFolder(path);
		switch (engine)
		{
			case UNITY: new Unity().generate(game); break;
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
