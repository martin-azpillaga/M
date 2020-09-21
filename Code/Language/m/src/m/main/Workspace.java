package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.generator.Game;

public class Workspace
{
	public String root;
	public Map<String, InferenceData> files;
	public Game game;
	public List<CrossReference> crossReferences;
	
	public Workspace(String root)
	{
		this.root = root;
		this.files = new HashMap<>();
		this.crossReferences = new ArrayList<CrossReference>();
	}
}
