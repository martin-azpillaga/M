package m.library;

import static java.util.Map.entry;
import static m.library.Symbol.*;
import static m.library.types.AtomicType.*;
import static m.validation.rules.Problem.ProblemKind.*;
import java.util.ArrayList;
import java.util.Map;

import m.validation.rules.Problem.ProblemKind;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.library.types.TypeVariable;
import m.validation.rules.Typing;

public enum Library {
	
	ENGLISH(
	Map.ofEntries(
		entry("pi", PI),
		entry("e", E)
	),
	Map.ofEntries(
		entry("velocity", VELOCITY),
		entry("mass", MASS),
		entry("elapsed", ELAPSED)),
	Map.ofEntries(
		entry("=", ASSIGNMENT),
		entry("*", MULTIPLICATION),
		entry("+", ADDITION),
		entry("<=", LOWEROREQUAL),
		entry("readNumber", READ_NUMBER),
		entry("random", RANDOM),
		entry("xyz", XYZ),
		entry("cos", COS),
		entry("sin", SIN),
		entry("remove", REMOVE)),
	Map.ofEntries(
		entry("foreach", QUERY),
		entry("if", SELECTION),
		entry("while", ITERATION)
	), Map.of(
		NUMBER, "number",
		NUMBER3, "number3"
	), Map.of(
		INCOMPATIBLE, "Incompatible types",
		UNDEFINED, "Undefined symbol",
		REDEFINED, "Redefined symbol",
		INDETERMINATE, "Indeterminate type"
	))
	;

	public Map<String, Symbol> variables;
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	
	Map<Type, String> atomicTypes;
	Map<ProblemKind, String> problemKind;
	
	Library(Map<String, Symbol> variables, Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks, Map<Type, String> atomicTypes, Map<ProblemKind, String> problemKind)
	{
		this.variables = variables;
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
		this.problemKind = problemKind;
	}
	
	public String message(ProblemKind kind)
	{
		return problemKind.get(kind);
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
