package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.types.Type;
import m.model.File;
import m.model.UserFunction;
import m.model.ExpressionGraph;

public class TypeValidator
{
	public Result validate(String file, File model, ExpressionGraph graph)
	{
		var result = new Result();

		return result;
	}

	public static class Result
	{
		public final Map<String,Type> components = new HashMap<>();
		public final List<UserFunction> functions = new ArrayList<>();
		public final Map<String,List<Problem>> problems = new HashMap<>();
	}
}
