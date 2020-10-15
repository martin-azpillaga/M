package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.validation.Problem.Severity;

import m.library.Library;
import m.model.Game;

public class Validator
{
	SyntaxValidator parser;
	ScopeValidator scopeValidator;
	TypeValidator typeValidator;

	public Validator()
	{
		parser = new SyntaxValidator();
		scopeValidator = new ScopeValidator();
		typeValidator = new TypeValidator();
	}

	public Result validate(String file, String text)
	{
		var result = new Result(new Game(Library.ENGLISH), new HashMap<>());

		var parsed = parser.validate(text);

		var scoped = scopeValidator.validate(file, parsed.file);

		var typed = typeValidator.validate(file, parsed.file, scoped.expressionGraph);

		result.mergeProblems(file, parsed.problems);
		result.mergeProblems(scoped.problems);
		result.mergeProblems(typed.problems);

		result.game.components.putAll(typed.components);
		result.game.functions.addAll(typed.functions);

		return result;
	}

	public Result delete(String file)
	{
		var result = new Result(new Game(Library.ENGLISH), new HashMap<>());

		var scoped = scopeValidator.delete(file);

		var typed = typeValidator.delete(file);

		result.mergeProblems(scoped.problems);
		result.mergeProblems(typed.problems);

		result.game.components.putAll(typed.components);
		result.game.functions.addAll(typed.functions);

		return new Result(null,null);
	}

	public static class Result
	{
		public Game game;
		public Map<String,List<Problem>> problems;
		public boolean hasErrors;

		public Result(Game game, Map<String,List<Problem>> problems)
		{
			this.game = game;
			this.problems = problems;
		}

		public void mergeProblems(String file, List<Problem> problems)
		{
			this.problems.put(file,problems);
			checkForErrors();
		}

		public void mergeProblems(Map<String,List<Problem>> problems)
		{
			problems.forEach((f,list)->
			{
				this.problems.computeIfAbsent(f,__->new ArrayList<>()).addAll(list);
			});
			checkForErrors();
		}

		private void checkForErrors()
		{
			if (hasErrors) return;

			problems.forEach((file,list)->
			{
				for (var problem : list)
				{
					if (problem.severity == Severity.ERROR)
					{
						hasErrors = true;
					}
				}
			});
		}
	}
}
