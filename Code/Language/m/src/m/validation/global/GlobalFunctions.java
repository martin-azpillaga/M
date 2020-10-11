package m.validation.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.model.ModelPackage;
import m.model.UserFunction;
import m.validation.Problem;
import m.validation.Problem.Severity;
import m.validation.local.LocalData;

public class GlobalFunctions
{
	Map<String,List<UserFunction>> fileToFunctions;
	Map<String,Map<String,List<Problem>>> nameToProblems;

	public GlobalFunctions()
	{
		this.fileToFunctions = new HashMap<>();
		this.nameToProblems = new HashMap<>();
	}

	public void validate(GlobalData memory, String newFile, LocalData newData)
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

						var problems = getOrCreate(memory.problems, existingFile);
						var problem = new Problem(f.function, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.Problem.REDEFINED_SYMBOL));
						problems.add(problem);

						var problemsB = getOrCreate(memory.problems, newFile);
						var problemB = new Problem(newFunction, ModelPackage.Literals.FUNCTION__NAME, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.Problem.REDEFINED_SYMBOL));
						problemsB.add(problemB);

						var nameProblems = getOrCreateMap(nameToProblems, name);
						getOrCreate(nameProblems, existingFile).add(problem);
						getOrCreate(nameProblems, newFile).add(problemB);
					}
				}
			}

			var userFunction = new UserFunction(newFunction, new FunctionType(new AtomicType[]{}, AtomicType.UNIT));
			newFunctions.add(userFunction);

			if (!nameExists)
			{
				memory.game.functions.add(userFunction);
			}
		}
		fileToFunctions.put(newFile, newFunctions);
	}

	private <K,V> List<V> getOrCreate(Map<K,List<V>> map, K key)
	{
		var existing = map.get(key);
		if (existing == null)
		{
			existing = new ArrayList<V>();
			map.put(key, existing);
		}
		return existing;
	}

	private <K,A,B> Map<A,B> getOrCreateMap(Map<K,Map<A,B>> map, K key)
	{
		var existing = map.get(key);
		if (existing == null)
		{
			existing = new HashMap<A,B>();
			map.put(key, existing);
		}
		return existing;
	}
}
