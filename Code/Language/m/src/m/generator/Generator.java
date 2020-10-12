package m.generator;

import java.nio.file.Path;
import java.nio.file.Paths;

import m.model.Configuration;
import m.model.Game;

public class Generator
{
	public void generate(Game game, String root, Configuration configuration)
	{
		if (configuration != null)
		{
			generate(game, Engine.UNITY, configuration.Unity, root);
			generate(game, Engine.UNREAL, configuration.Unreal, root);
			generate(game, Engine.GODOT, configuration.Godot, root);
		}
		else
		{
			for (var engine : Engine.values())
			{
				var defaultPath = Paths.get(root.toString(), engine.identifier).toString();
				generate(game, engine, defaultPath, root);
			}
		}
	}

	public void generate(Game game, Engine engine, String path, String root)
	{
		if (path == null)
		{
			return;
		}
		if (path.startsWith("./"))
		{
			path = path.replace(".", root);
		}
		IO.setBaseFolder(path);
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
			var defaultPath = Paths.get(path.toString(), engine.identifier).toString();
			generate(game, engine, defaultPath, defaultPath);
		}
	}
}
