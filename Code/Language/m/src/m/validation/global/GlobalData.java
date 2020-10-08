package m.validation.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.types.Type;
import m.model.Function;
import m.validation.problems.Problem;

public class GlobalData
{
	public Map<String,Type> components;
	public List<Function> functions;
	public Map<String,List<Problem>> diagnostics;

	public GlobalData()
	{
		this.components = new HashMap<>();
		this.functions = new ArrayList<>();
		this.diagnostics = new HashMap<>();
	}
}
