package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.model.Game;

public class Validator
{
	Result result;

	Parser parser;
	ScopeValidator scopeValidator;
	TypeValidator typeValidator;

	public Validator()
	{
		var library = Library.ENGLISH;

		result = new Result(new Game(library), new HashMap<>());
		parser = new Parser();
		scopeValidator = new ScopeValidator(library);
		typeValidator = new TypeValidator();
	}

	public Result validate(String file, String text)
	{
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
		return result;
	}

	public static class Result
	{
		public Game game;
		public Map<String,List<Problem>> problems;

		public Result(Game game, Map<String,List<Problem>> problems)
		{
			this.game = game;
			this.problems = problems;
		}
	}
}
