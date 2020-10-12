package m.validation.global;

import java.util.ArrayList;
import java.util.HashSet;

import m.validation.local.LocalValidator;

public class Validator
{
	GlobalData data;

	LocalValidator localValidator;
	TypeChecker typeChecker;
	FunctionChecker functionChecker;

	public Validator()
	{
		localValidator = new LocalValidator();

		data = new GlobalData();
		functionChecker = new FunctionChecker(data);
		typeChecker = new TypeChecker(data);
	}

	public GlobalData modify(String file, String text)
	{
		data.modifiedFiles = new HashSet<String>();

		var localData = localValidator.validate(text);

		data.problems.put(file, localData.problems);

		data.modifiedFiles.add(file);

		var modified = functionChecker.validate(data, file, localData);

		data.modifiedFiles.addAll(modified);

		modified = typeChecker.validate(file, localData);

		data.modifiedFiles.addAll(modified);

		return data;
	}

	public GlobalData delete(String file)
	{
		return data;
	}
}
