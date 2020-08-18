package m.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.library.types.*;
import m.m.Function;
import m.validation.InferenceGraph;

public class Game {
	public Map<String, Type> components = new HashMap<>();
	public List<Function> systems = new ArrayList<>();
	public Library library;
	public InferenceGraph inference;
	public HashMap<Function, HashMap<String, HashMap<String, AccessKind>>> queries = new HashMap<>();
}
