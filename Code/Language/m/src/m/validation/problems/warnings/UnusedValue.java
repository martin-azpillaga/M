package m.validation.problems.warnings;

import java.util.ArrayList;
import java.util.List;

import m.library.Library;
import m.m.Value;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;

public class UnusedValue implements Problem
{
	Value value;
	
	public UnusedValue(Value value)
	{
		this.value = value;
	}
	
	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var list = new ArrayList<ProblemMessage>();
		var message = library.getProblem(this.getClass());
		var problem = new ProblemMessage(Severity.WARNING, message, value, null);
		list.add(problem);
		return list;
	}
}
