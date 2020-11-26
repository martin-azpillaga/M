package m.generator;

import m.model.Configuration;
import m.model.Game;

public class Generator
{
	public void generate(Game game, Configuration configuration)
	{
		generate(game, Engine.UNITY, configuration.Unity);
		// HACK: Queries need to be restart so that one engine does not affect the next ones
		for (var function : game.functions)
		{
			function.queries = function.collectQueries();
		}
		generate(game, Engine.UNREAL, configuration.Unreal);
		// HACK: Queries need to be restart so that one engine does not affect the next ones
		for (var function : game.functions)
		{
			function.queries = function.collectQueries();
		}
		generate(game, Engine.GODOT, configuration.Godot);
	}

	private void generate(Game game, Engine engine, String path)
	{
		if (path == null || path == "")
		{
			return;
		}

		IO.setBaseFolder(path);

		switch (engine)
		{
			case UNITY: new Unity().generate(game); break;
			case UNREAL: new Unreal().generate(game); break;
			case GODOT: new Godot().generate(game); break;
		}
	}
}
