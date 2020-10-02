package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workspace
{
	Game game;
	public final String root;
	public final Map<String, InferenceData> files;
	public final List<CrossReference> crossReferences;
	
	public Workspace(String root)
	{
		this.root = root;
		this.files = new HashMap<>();
		this.crossReferences = new ArrayList<>();
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}
}
