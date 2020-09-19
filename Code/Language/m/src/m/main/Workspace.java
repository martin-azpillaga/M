package m.main;

import java.util.HashMap;
import java.util.Map;

import m.validation.InferenceGraph;

public class Workspace
{
	public String root;
	public Map<String, InferenceData> files;
	public InferenceGraph inference;
	
	public Workspace(String root)
	{
		this.root = root;
		this.files = new HashMap<>();
	}
}
