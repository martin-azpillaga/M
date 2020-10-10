package m.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import m.generator.Generator;
import m.validation.global.GlobalData;
import m.validation.global.GlobalValidator;

public class Project
{
	String root;

	GlobalValidator validator;
	Generator generator;
	public final Inspector inspector;

	Consumer<Map<String,ArrayList<Diagnostic>>> problemsChanged;

	public Project(String root)
	{
		this.root = root;
		this.generator = new Generator();
		this.validator = new GlobalValidator();
		this.inspector = new Inspector();

		try (var walk = Files.walk(Paths.get(root)))
		{
			walk.forEach(f ->
			{
				var file = f.toString();
				if (file.endsWith(".Ⲙ"))
				{
					try
					{
						var text = new String(Files.readAllBytes(f));
						validator.validate(file,text);
					}
					catch (IOException e)
					{

					}
				}
			});
		}
		catch (IOException e){}
	}

	public void setPublisher(Consumer<Map<String,ArrayList<Diagnostic>>> onProblemsChanged)
	{
		this.problemsChanged = onProblemsChanged;
	}

	public boolean contains(String path)
	{
		return path.startsWith(this.root);
	}

	public Map<String,List<Diagnostic>> getDiagnostics()
	{
		return new HashMap<String, List<Diagnostic>>();
	}

	public void fileAdded(String file)
	{
		if (!contains(file)) return;

		try
		{
			var text = new String(Files.readAllBytes(Paths.get(file)));
			fileChanged(file, text);
		}
		catch (IOException e){}
	}

	public void fileDeleted(String file)
	{
		if (!contains(file)) return;

		var globalData = validator.delete(file);

		check(globalData);
	}

	public void fileChanged(String modifiedFile, String text)
	{
		if (!contains(modifiedFile)) return;

		var globalData = validator.validate(modifiedFile, text);

		check(globalData);
	}

	public String hover(String file, Position position)
	{
		return inspector.hover(file, position);
	}

	public List<CompletionItem> completions(String file, Position position)
	{
		return inspector.completions(file, position);
	}

	public SignatureHelp signature(String file, Position position)
	{
		return inspector.signature(file, position);
	}



	private void check(GlobalData globalData)
	{
		var problemMap = globalData.problems;
		var diagnosticMap = new HashMap<String, ArrayList<Diagnostic>>();

		for (var entry : problemMap.entrySet())
		{
			var file = entry.getKey();
			var problems = entry.getValue();

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
		}

		if (problemsChanged != null)
		{
			problemsChanged.accept(diagnosticMap);
		}

		var game = globalData.game;

		if (globalData.problems.isEmpty())
		{
			var path = Paths.get(root, "Ⲙ.json");

			if (new File(path.toString()).exists())
			{
				try
				{
					var configuration = new String(Files.readAllBytes(path));
					generator.generate(game, root, configuration);
				}
				catch (IOException e){}
			}
			else
			{
				generator.generate(game, Paths.get(root));
			}
		}
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
