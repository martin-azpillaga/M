package m.main;

import java.util.HashMap;
import java.util.Map;

import m.generator.Game;

public class Workspace
{
	public String root;
	public Map<String, InferenceData> files;
	public Game game;
	
	public Workspace(String root)
	{
		this.root = root;
		this.files = new HashMap<>();
	}
}
