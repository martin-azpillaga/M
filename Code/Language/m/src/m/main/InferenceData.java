package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.validation.problems.Problem;
import m.validation.rules.ExpressionNode;

public class InferenceData
{
	public List<ExpressionNode> nodes = new ArrayList<>();
	public Map<String, ExpressionNode> components = new HashMap<>();
	public List<Problem> problems = new ArrayList<>();
}
