package m.generator;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import m.model.Game;

public class Generator
{
	public void generate(Game game, String root, String configurationData)
	{
		var gson = new Gson();
		var configuration = gson.fromJson(configurationData, Configuration.class);

		generate(game, Engine.UNITY, configuration.Unity);
		generate(game, Engine.UNREAL, configuration.Unreal);
		generate(game, Engine.GODOT, configuration.Godot);
	}

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
			var defaultPath = Paths.get(path.toString(), engine.identifier).toString();
			generate(game, engine, defaultPath);
		}
	}

	private void generate(Game game, Engine engine, String root, String path)
	{
		if (path == null) return;

		if (path.startsWith("."))
		{
			path = path.replace(".", root);
		}
		generate(game, engine, path);
	}
}

class Configuration
{
	public String Unity;
	public String Unreal;
	public String Godot;
}
