package m.generator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.xtext.EcoreUtil2;

import m.library.Library;
import m.m.Assignment;
import m.m.Cell;
import m.m.Function;
import m.types.FunctionType;
import m.types.Type;

public class Game
{
	public Map<Function,FunctionType> functions = new HashMap<>();
	public Map<String, Type> components = new HashMap<>();
	public Library library;
}