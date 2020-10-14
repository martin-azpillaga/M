package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.model.Game;
import m.validation.global.FunctionChecker;
import m.validation.global.TypeChecker;
import m.validation.local.LocalValidator;

public class Validator
{
	Parser parser;
	ScopeValidator scopeValidator;
	TypeValidator typeValidator;

	public Validator()
	{
		parser = new Parser();
		scopeValidator = new ScopeValidator();
		typeValidator = new TypeValidator();
	}

	public Result validate(String file, String text)
	{
		var result = new Result();

		var parsed = parser.validate(text);

		var scoped = scopeValidator.validate(file, parsed.file);

		var typed = typeValidator.validate(file, parsed.file, scoped.expressionGraph);

		if (!parsed.problems.isEmpty())
		{
			result.problems.put(file, parsed.problems);
		}
		scoped.problems.forEach((f,problems)-> result.problems.getOrDefault(f, new ArrayList<>()).addAll(problems));
		typed .problems.forEach((f,problems)-> result.problems.getOrDefault(f, new ArrayList<>()).addAll(problems));

		result.game.components.putAll(typed.components);
		result.game.functions.addAll(typed.functions);

		return result;
	}

	public Result delete(String file)
	{
		return new Result();
	}

	public static class Result
	{
		public Game game;
		public Map<String,List<Problem>> problems;

		public Result()
		{
			game = new Game(Library.ENGLISH);
			problems = new HashMap<>();
		}
	}
}
