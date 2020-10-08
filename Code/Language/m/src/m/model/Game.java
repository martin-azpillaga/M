package m.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.library.types.Type;

public class Game
{
	public final Library library;
	public final Map<String, Type> components;
	public final List<UserFunction> functions;
	public final Map<Value, Type> values;

	public Game(Library library)
	{
		this.library = library;
		this.components = new HashMap<>();
		this.functions = new ArrayList<>();
		this.values = new HashMap<>();
	}
}
