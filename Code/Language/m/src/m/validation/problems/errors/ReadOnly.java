package m.validation.problems.errors;

import java.util.ArrayList;
import java.util.List;

import m.library.Library;
import m.m.Value;
import m.validation.problems.Problem;
import m.validation.problems.ProblemMessage;
import m.validation.problems.ProblemMessage.Severity;

public class ReadOnly implements Problem
{
	Value value;
	
	public ReadOnly(Value value)
	{
		this.value = value;
	}
	
	@Override
	public List<ProblemMessage> messages(Library library)
	{
		var list = new ArrayList<ProblemMessage>();
		var message = library.getProblem(this.getClass());
		list.add(new ProblemMessage(Severity.ERROR, message, value, null));
		return list;
	}

}
