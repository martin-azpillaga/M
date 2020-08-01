package m.validation.problems;

import java.util.List;

import m.library.Library;

public interface Problem
{
	List<ProblemMessage> messages(Library library);
}
