package m.library;

import java.util.HashMap;
import java.util.Map;

public enum Language {
	
	ENGLISH(new HashMap<>(), new HashMap<>(), new HashMap<>()),
	CASTELLANO(new HashMap<>(), new HashMap<>(), new HashMap<>()),
	EUSKARA(new HashMap<>(), new HashMap<>(), new HashMap<>())
	;
	
	public Map<String, StandardComponent> components;
	public Map<String, StandardFunction> functions;
	public Map<String, StandardBlock> blocks;
	
	Language(Map<String, StandardComponent> components, Map<String, StandardFunction> functions, Map<String, StandardBlock> blocks)
	{
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
	}
}
