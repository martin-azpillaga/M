package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.library.types.*;
import m.validation.Inference;

public class Game
{
	public final Library library;
	public final Map<String, Type> components;
	public final List<UserFunction> functions;
	public final Inference inference;

	public Game(Library library, Inference inference)
	{
		this.library = library;
		this.components = new HashMap<>();
		this.functions = new ArrayList<>();
		this.inference = inference;
	}
}
