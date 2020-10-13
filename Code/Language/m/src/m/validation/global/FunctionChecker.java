package m.validation.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.library.types.FunctionType;
import m.model.Function;
import m.model.ModelPackage;
import m.model.UserFunction;
import m.validation.Problem;
import m.validation.Problem.Severity;

public class FunctionChecker
{
	Map<String,List<UserFunction>> fileToFunctions;
	Map<String,Map<String,List<Problem>>> nameToProblems;

	public FunctionChecker()
	{
		this.fileToFunctions = new HashMap<>();
		this.nameToProblems = new HashMap<>();
	}

	public static class Result
	{
		public List<UserFunction> newFunctions;
		public List<UserFunction> invalidatedFunctions;
		public Map<String,List<Problem>> problems;
	}

	public Result validate(String newFile, Map<String,Function> newData)
	{
		var result = new Result();
		// 1 Remove obsolete data from caches

		// Remove functions present in last frame in this file from game
		var obsoleteFunctions = fileToFunctions.get(newFile);
		if (obsoleteFunctions != null)
		{
			for (var obsoleteFunction : obsoleteFunctions)
			{
				result.invalidatedFunctions.add(obsoleteFunction);
			}
		}



		// 2 Analyze and add new data to result and cache
		fileToFunctions.remove(newFile);
		var newFunctions = new ArrayList<UserFunction>();

		for (var entry : newData.entrySet())
		{
			var name = entry.getKey();
			var newFunction = entry.getValue();

			var nameExists = false;

			for (var existingEntry : fileToFunctions.entrySet())
			{
				var existingFile = existingEntry.getKey();

				for (var f : existingEntry.getValue())
				{
					if (f.getName().equals(name))
					{
						nameExists = true;

						var problems = result.problems.getOrDefault(existingFile, new ArrayList<Problem>());
						var problem = new Problem(f.function, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.REDEFINED_SYMBOL));
						problems.add(problem);

						var problemsB = result.problems.getOrDefault(newFile, new ArrayList<Problem>());
						var problemB = new Problem(newFunction, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.REDEFINED_SYMBOL));
						problemsB.add(problemB);

						var nameProblems = nameToProblems.getOrDefault(name, new HashMap<String,List<Problem>>());
						nameProblems.getOrDefault(existingFile, new ArrayList<>()).add(problem);
						nameProblems.getOrDefault(newFile, new ArrayList<>()).add(problemB);
					}
				}
			}

			var userFunction = new UserFunction(newFunction, FunctionType.systemType);
			newFunctions.add(userFunction);

			if (!nameExists)
			{
				result.newFunctions.add(userFunction);
			}
		}
		fileToFunctions.put(newFile, newFunctions);

		return result;
	}
}
