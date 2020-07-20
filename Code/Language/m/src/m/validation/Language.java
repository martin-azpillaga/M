package m.validation;

import java.util.HashMap;
import java.util.Map;

public enum Language {
	
	ENGLISH(new HashMap<>(), new HashMap<>(), new HashMap<>()),
	CASTELLANO(new HashMap<>(), new HashMap<>(), new HashMap<>()),
	EUSKARA(new HashMap<>(), new HashMap<>(), new HashMap<>())
	;
	
	Map<String, StandardComponent> components;
	Map<String, StandardFunction> functions;
	Map<String, StandardBlock> blocks;
	
	Language(Map<String, StandardComponent> components, Map<String, StandardFunction> functions, Map<String, StandardBlock> blocks)
	{
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
	}
}
