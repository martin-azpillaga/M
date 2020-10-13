package m.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.model.Game;
import m.validation.global.FunctionChecker;
import m.validation.global.TypeChecker;
import m.validation.local.LocalValidator;

public class Validator
{
	LocalValidator localValidator;
	TypeChecker typeChecker;
	FunctionChecker functionChecker;

	public Validator()
	{
		localValidator = new LocalValidator();
		functionChecker = new FunctionChecker();
		typeChecker = new TypeChecker();
	}

	public Result modify(String file, String text)
	{
		var result = new Result();

		result.modifiedFiles = new HashSet<>();

		var localData = localValidator.validate(text);

		result.problems.put(file, localData.problems);

		result.modifiedFiles.add(file);

		var modified = functionChecker.validate(file, localData);

		result.modifiedFiles.addAll(modified.keySet());

		modified = typeChecker.validate(file, localData);

		result.modifiedFiles.addAll(modified.keySet());

		return new Result();
	}

	public Result delete(String file)
	{
		return new Result();
	}

	public class Result
	{
		public Game game;
		public Map<String,List<Problem>> problems;
		public Set<String> modifiedFiles;
	}
}
