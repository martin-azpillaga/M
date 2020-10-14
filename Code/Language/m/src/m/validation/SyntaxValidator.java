package m.validation;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import m.MStandaloneSetup;
import m.library.Library;
import m.library.rules.ProblemKind;
import m.model.File;
import m.parser.antlr.MParser;
import m.validation.Problem.Severity;

public class SyntaxValidator
{
	@Inject MParser parser;

	public SyntaxValidator()
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		injector.injectMembers(this);
	}

	public Result validate(String text)
	{
		var parseResult = parser.parse(new StringReader(text));

		var file = (File) parseResult.getRootASTElement();

		var problems = new ArrayList<Problem>();

		for (var syntaxError : parseResult.getSyntaxErrors())
		{
			problems.add(new Problem(syntaxError, Severity.ERROR, Library.ENGLISH.getProblem(ProblemKind.SYNTAX_ERROR)));
		}

		return new Result(file, problems);
	}

	public static class Result
	{
		public final File file;
		public final List<Problem> problems;

		public Result(File file, List<Problem> problems)
		{
			this.file = file;
			this.problems = problems;
		}
	}
}
