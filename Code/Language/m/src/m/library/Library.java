package m.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import m.validation.IncompatibleTypes;
import m.validation.Problem;

import static m.library.Symbol.*;
import m.types.*;
import static m.types.AtomicType.*;

public enum Library {
	
	ENGLISH(new HashMap<>() {{
		put("velocity", VELOCITY);
		put("mass", MASS);
	}}, new HashMap<>() {{
		put("=", ASSIGNMENT);
		put("*", MULTIPLICATION);
		put("readNumber", READ_NUMBER);
	}}, new HashMap<>() {{
		put("foreach", QUERY);
	}}, new HashMap<>() {{
		put(NUMBER, "number");
		put(NUMBER3, "number3");
	}}),
	CASTELLANO(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>()),
	EUSKARA(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>())
	;
	
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	public Map<Type, String> atomicTypes;
	
	Library(Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks, Map<Type,String> atomicTypes)
	{
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
	}
	
	public String message(Problem problem)
	{
		if (problem instanceof IncompatibleTypes)
		{
			var p = (IncompatibleTypes) problem;
			
			return "Incompatible types\n"+
			name(p.t1) + " because " + p.t1Reason + "\n" + 
			name(p.t2) + " because " + p.t2Reason + "\n";
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
}
