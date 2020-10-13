package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.model.Game;
import m.validation.global.FunctionChecker;
import m.validation.global.TypeChecker;
import m.validation.local.LocalValidator;

public class Validator
{
	LocalValidator localValidator;
	TypeChecker typeChecker;
	FunctionChecker functionChecker;
	Result result;

	public Validator()
	{
		localValidator = new LocalValidator();
		functionChecker = new FunctionChecker();
		typeChecker = new TypeChecker();
		result = new Result();
	}

	public Result validate(String file, String text)
	{
		result.problems.clear();

		var localResult = localValidator.validate(text);

		var functions = functionChecker.validate(file, localResult);

		var types = typeChecker.validate(file, localResult);



		result.problems.put(file, localResult.problems);
		functions.problems.forEach((f, problems)->
		{
			result.problems.getOrDefault(f, new ArrayList<Problem>()).addAll(problems);
		});
		types.problems.forEach((f, problems)->
		{
			result.problems.getOrDefault(f, new ArrayList<Problem>()).addAll(problems);
		});


		for (var component : types.invalidatedComponents)
		{
			result.game.components.remove(component);
		}
		result.game.components.putAll(types.newComponents);

		for (var function : functions.invalidatedFunctions)
		{
			result.game.functions.remove(function);
		}
		result.game.functions.addAll(functions.newFunctions);

		return result;
	}

	public Result delete(String file)
	{
		return result;
	}

	public class Result
	{
		public Game game;
		public Map<String,List<Problem>> problems;
	}
}
