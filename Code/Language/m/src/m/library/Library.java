package m.library;

import static java.util.Map.entry;
import static m.library.Symbol.*;
import static m.library.types.AtomicType.*;
import static m.validation.problems.BindingProblem.BindingProblemKind.*;
import static m.validation.problems.TypingProblem.TypingProblemKind.*;

import java.util.ArrayList;
import java.util.Map;

import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.library.types.TypeVariable;
import m.validation.problems.BindingProblem;
import m.validation.problems.Problem;
import m.validation.problems.TypingProblem;
import m.validation.problems.BindingProblem.BindingProblemKind;
import m.validation.problems.TypingProblem.TypingProblemKind;

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
		REDEFINED, "Redefined symbol",
		UNDEFINED, "Undefined symbol"
	),
	Map.of(
		INCOMPATIBLE, "Incompatible types",
		INDETERMINATE, "Indeterminate type"
	))
	;

	public Map<String, Symbol> variables;
	public Map<String, Symbol> components;
	public Map<String, Symbol> functions;
	public Map<String, Symbol> blocks;
	
	Map<Type, String> atomicTypes;
	Map<BindingProblemKind, String> bindingProblem;
	Map<TypingProblemKind, String> typingProblem;
	
	Library(Map<String, Symbol> variables, Map<String, Symbol> components, Map<String, Symbol> functions, Map<String, Symbol> blocks, Map<Type, String> atomicTypes, Map<BindingProblemKind, String> bindingProblem, Map<TypingProblemKind, String> typingProblem)
	{
		this.variables = variables;
		this.components = components;
		this.functions = functions;
		this.blocks = blocks;
		this.atomicTypes = atomicTypes;
		this.bindingProblem = bindingProblem;
		this.typingProblem = typingProblem;
	}
	
	public String message(Problem problem)
	{
		if (problem instanceof BindingProblem)
		{
			return bindingProblem.get(((BindingProblem) problem).getKind());
		}
		else if (problem instanceof TypingProblem)
		{
			var base = typingProblem.get(((TypingProblem) problem).getKind());
			var node = ((TypingProblem) problem).getNode();
			var root = node;
			while (root.binding != null)
			{
				root = root.binding.node;
			}
			base += "\n" + name(node.typing.getType()) + " - " + name(root.typing.getType());
			return base;
		}
		return "error";
	}
	
	private String name(Type type)
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
