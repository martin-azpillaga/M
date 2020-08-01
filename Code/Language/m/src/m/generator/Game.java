package m.generator;

import java.util.HashMap;
import java.util.Map;

import m.library.Library;
import m.library.types.*;
import m.m.Function;

public class Game {
	public Map<String, Type> components = new HashMap<>();
	public Map<Function, FunctionType> functions = new HashMap<>();
	public Library library;
	public HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries = new HashMap<>();
}
