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
	Library library;
	Map<String, Type> components;
	List<UserFunction> functions;
	public Inference inference;

	public Game(Library library)
	{
		this.library = library;
		this.components = new HashMap<>();
		this.functions = new ArrayList<>();
	}

	public Library getLibrary()
	{
		return library;
	}

	public Map<String, Type> getComponents()
	{
		return components;
	}

	public List<UserFunction> getFunctions()
	{
		return functions;
	}

	public void addComponent(String name, Type type)
	{
		components.put(name, type);
	}

	public void addFunction(UserFunction function)
	{
		functions.add(function);
	}
}
