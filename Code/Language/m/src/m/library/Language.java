package m.library;

import java.util.HashMap;
import java.util.Map;
import static m.library.Symbol.*;

public enum Language {
	
	ENGLISH(new HashMap<>() {{
		put("velocity", VELOCITY);
	}}, new HashMap<>() {{
		put("=", ASSIGNMENT);
		put("*", MULTIPLICATION);
		put("readNumber", READ_NUMBER);
	}}, new HashMap<>() {{
		put("foreach", QUERY);
	}}),
	CASTELLANO(new HashMap<>(), new HashMap<>(), new HashMap<>()),
	EUSKARA(new HashMap<>(), new HashMap<>(), new HashMap<>())
	;
	
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	
	Language(Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks)
	{
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
	}
}
