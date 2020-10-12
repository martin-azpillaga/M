package m.validation.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.Library;
import m.library.types.FunctionType;
import m.model.ModelPackage;
import m.model.UserFunction;
import m.validation.Problem;
import m.validation.Problem.Severity;
import m.validation.local.LocalData;

public class FunctionChecker
{
	GlobalData memory;

	Map<String,List<UserFunction>> fileToFunctions;
	Map<String,Map<String,List<Problem>>> nameToProblems;

	public FunctionChecker(GlobalData data)
	{
		this.memory = data;
		this.fileToFunctions = new HashMap<>();
		this.nameToProblems = new HashMap<>();
	}

	public Set<String> validate(String newFile, LocalData newData)
	{
		// 1 Remove obsolete data from caches

		// Remove functions present in last frame in this file from game
		var obsoleteFunctions = fileToFunctions.get(newFile);
		if (obsoleteFunctions != null)
		{
			for (var obsoleteFunction : obsoleteFunctions)
			{
				memory.game.functions.remove(obsoleteFunction);

				// Remove problems caused because of function name: remove from memory
				var obsoleteProblems = nameToProblems.get(obsoleteFunction.getName());
				if (obsoleteProblems != null)
				{
					for (var entry : obsoleteProblems.entrySet())
					{
						var file = entry.getKey();
						var problems = entry.getValue();

						memory.problems.get(file).removeAll(problems);
					}
				}
			}
		}



		// 2 Analyze and add new data to result and cache
		fileToFunctions.remove(newFile);
		var newFunctions = new ArrayList<UserFunction>();

		for (var entry : newData.functions.entrySet())
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

						var problems = memory.problems.getOrDefault(existingFile, new ArrayList<Problem>());
						var problem = new Problem(f.function, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.Problem.REDEFINED_SYMBOL));
						problems.add(problem);

						var problemsB = memory.problems.getOrDefault(newFile, new ArrayList<Problem>());
						var problemB = new Problem(newFunction, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.Problem.REDEFINED_SYMBOL));
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
				memory.game.functions.add(userFunction);
			}
		}
		fileToFunctions.put(newFile, newFunctions);

		return new HashSet<>();
	}
}
