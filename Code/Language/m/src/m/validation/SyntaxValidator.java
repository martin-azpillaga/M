package m.validation;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import org.eclipse.xtext.nodemodel.INode;

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

		return new Result(file, problems, parseResult.getRootNode());
	}

	public static class Result
	{
		public final File file;
		public final List<Problem> problems;
		public final INode rootNode;

		public Result(File file, List<Problem> problems, INode rootNode)
		{
			this.file = file;
			this.problems = problems;
			this.rootNode = rootNode;
		}
	}
}
