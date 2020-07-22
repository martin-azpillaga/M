package m.generator;

import java.util.HashMap;
import java.util.Map;

import m.library.Library;
import m.m.Function;
import m.types.*;

public class Game {
	public Map<String, Type> components = new HashMap<>();
	public Map<Function, FunctionType> functions = new HashMap<>();
	public Library library;
}
