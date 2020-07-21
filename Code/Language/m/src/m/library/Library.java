package m.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import m.validation.IncompatibleTypes;
import m.validation.Problem;
import m.validation.TypingReason;
import m.validation.UndefinedSymbol;

import static m.library.Symbol.*;
import m.types.*;
import static m.types.AtomicType.*;

public enum Library {
	
	ENGLISH(new HashMap<>() {{
		put("velocity", VELOCITY);
		put("mass", MASS);
		put("elapsed", ELAPSED);
	}}, new HashMap<>() {{
		put("=", ASSIGNMENT);
		put("*", MULTIPLICATION);
		put("readNumber", READ_NUMBER);
		put("random", RANDOM);
		put("xyz", XYZ);
		put("cos", COS);
		put("sin", SIN);
		put("remove", REMOVE);
	}}, new HashMap<>() {{
		put("foreach", QUERY);
		put("if", SELECTION);
		put("while", ITERATION);
	}}, new HashMap<>() {{
		put(NUMBER, "number");
		put(NUMBER3, "number3");
	}}, new HashMap<>() {{
		put(IncompatibleTypes.class, "Incompatible types");
		put(UndefinedSymbol.class, "Undefined symbol");
	}}),
	EUSKARA(new HashMap<>() {{
		put("abiadura", VELOCITY);
		put("masa", MASS);
		put("igarotakoa", ELAPSED);
	}}, new HashMap<>() {{
		put("=", ASSIGNMENT);
		put("*", MULTIPLICATION);
		put("irakurri", READ_NUMBER);
		put("zorizko", RANDOM);
		put("xyz", XYZ);
		put("cos", COS);
		put("sin", SIN);
		put("kendu", REMOVE);
	}}, new HashMap<>() {{
		put("hartu", QUERY);
		put("baldin", SELECTION);
		put("bitartean", ITERATION);
	}}, new HashMap<>() {{
		put(NUMBER, "zenbakia");
		put(NUMBER3, "zenbakia3");
	}}, new HashMap<>() {{
		put(IncompatibleTypes.class, "izaera bateraezinak");
		put(UndefinedSymbol.class, "definizio gabeko zeinua");
	}}),
	;
	
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	public Map<Type, String> atomicTypes;
	public Map<Class, String> problems;
	
	Library(Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks, Map<Type,String> atomicTypes, Map<Class,String> problems)
	{
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
		this.problems = problems;
	}
	
	public String message(Problem problem)
	{
		var result = problems.get(problem.getClass());
		
		if (problem instanceof IncompatibleTypes)
		{
			var p = (IncompatibleTypes) problem;
			
			return result + "\n"+
			name(p.t1) + " -- " + name(p.t1Reason) + "\n" + 
			name(p.t2) + " -- " + name(p.t2Reason) + "\n";
		}
		else if (problem instanceof UndefinedSymbol)
		{
			return result;
		}
		return "problem message";
	}
	
	public String name(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomicType = (AtomicType) type;
			
			return atomicTypes.get(atomicType);
		}
		else if (type instanceof TypeVariable)
		{
			return ((TypeVariable) type).getName();
		}
		else if (type instanceof FunctionType)
		{
			var functionType = (FunctionType) type;
			var result = new ArrayList<String>();
			for (var p : functionType.getParameters())
			{
				result.add(name(p));
			}
			return String.join(",", result) + "->" + name(functionType.getReturnType());
		}
		else
		{
			return "unrecognized type";
		}
	}
	
	public String name(TypingReason reason)
	{
		var symbol = reason.getSymbol();
		
		var symbolName = symbol.name();
		
		for (var f : functions.entrySet())
		{
			if (f.getValue() == symbol)
			{
				symbolName = f.getKey();
			}
		}
		for (var f : components.entrySet())
		{
			if (f.getValue() == symbol)
			{
				symbolName = f.getKey();
			}
		}
		
		return symbolName + " : " + name(symbol.getType());
	}
}
