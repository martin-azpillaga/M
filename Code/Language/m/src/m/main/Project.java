package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SignatureInformation;
import org.eclipse.xtext.nodemodel.INode;

import m.generator.Generator;
import m.generator.IO;
import m.validation.Problem;
import m.validation.Validator;
import m.model.Configuration;

public class Project
{
	String root;

	Validator validator;
	Generator generator;
	Inspector inspector;
	Configuration configuration;

	public Project(String root)
	{
		this.root = root;
		this.generator = new Generator();
		this.validator = new Validator();
		this.inspector = new Inspector();
		this.configuration = new Configuration();
	}

	public Map<String,List<Diagnostic>> initialize()
	{
		Validator.Result data = null;

		for (var file : IO.filesWithExtension("Ⲙ", root))
		{
			var text = IO.read(file);
			data = validator.modify(file,text);
		}

		var configurationFile = IO.concat(root, "Ⲙ.json");
		if (IO.existsFile(configurationFile))
		{
			var json = IO.read(configurationFile);
			configuration.parse(json, root);
		}

		return check(data);
	}

	public Map<String,List<Diagnostic>> delete(String file)
	{
		if (file.endsWith(".json"))
		{
			configuration = new Configuration();
			return new HashMap<>();
		}
		else
		{
			var globalData = validator.delete(file);

			return check(globalData);
		}
	}

	public Map<String,List<Diagnostic>> modify(String file, String text)
	{
		if (file.endsWith(".json"))
		{
			configuration.parse(text,root);
			return new HashMap<>();
		}
		else
		{
			var globalData = validator.modify(file, text);

			return check(globalData);
		}
	}

	public String hover(String file, Position position)
	{
		return inspector.hover(file, position);
	}

	public List<CompletionItem> completions(String file, Position position)
	{
		return inspector.completions(file, position);
	}

	public List<SignatureInformation> signatures(String file, Position position)
	{
		return inspector.signatures(file, position);
	}



	private Map<String,List<Diagnostic>> check(Validator.Result globalData)
	{
		if (globalData == null)
		{
			return new HashMap<>();
		}

		var game = globalData.game;

		var diagnosticMap = convert(globalData.problems, globalData.modifiedFiles);

		var hasErrors = false;
		for (var diagnosticList : diagnosticMap.values())
		{
			for (var diagnostic : diagnosticList)
			{
				if (diagnostic.getSeverity() == DiagnosticSeverity.Error)
				{
					hasErrors = true;
				}
			}
		}

		if (!hasErrors)
		{
			generator.generate(game, configuration);
		}

		return diagnosticMap;
	}

	private Map<String,List<Diagnostic>> convert(Map<String,List<Problem>> problemMap, Set<String> filter)
	{
		var diagnosticMap = new HashMap<String, List<Diagnostic>>();

		if (problemMap == null)
		{
			return diagnosticMap;
		}

		problemMap.forEach((file, problems) ->
		{
			if (!filter.contains(file))
			{
				return;
			}

			var diagnostics = new ArrayList<Diagnostic>();
			for (var problem : problems)
			{
				DiagnosticSeverity severity;
				switch(problem.severity)
				{
					case INFORMATION: severity = DiagnosticSeverity.Information; break;
					case WARNING: severity = DiagnosticSeverity.Warning; break;
					case ERROR: severity = DiagnosticSeverity.Error; break;
					default: severity = DiagnosticSeverity.Error; break;
				}
				var range = getRange(problem.node);
				var diagnostic = new Diagnostic(range, problem.message);
				diagnostic.setSeverity(severity);
				diagnostics.add(diagnostic);
			}

			diagnosticMap.put(file, diagnostics);
		});

		return diagnosticMap;
	}

	private Range getRange(INode node)
	{
		var text = node.getRootNode().getText();
		return new Range
		(
			new Position(node.getStartLine()-1, character(text, node.getOffset())),
			new Position(node.getEndLine()-1  , character(text, node.getOffset()+node.getLength()))
		);
	}

	private int character(String text, int offset)
	{
		var count = 0;
		for (var i = 0; i < offset && i < text.length(); i++)
		{
			count++;
			if (text.charAt(i) == '\n')
			{
				count = 0;
			}
		}
		return count;
	}
}
