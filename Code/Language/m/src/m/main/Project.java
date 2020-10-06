package m.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.Diagnostic;

import m.generator.Engine;
import m.generator.Generator;
import m.library.Library;
import m.model.Game;
import m.validation.global.GlobalData;
import m.validation.global.GlobalValidator;

public class Project
{
	String root;

	GlobalValidator validator;
	Generator generator;
	public Game game;
	Map<String,List<Diagnostic>> diagnostics;

	public Project(String root)
	{
		this.root = root;
		this.generator = new Generator();
		this.validator = new GlobalValidator();
		this.diagnostics = new HashMap<>();

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

	public boolean contains(String path)
	{
		return path.startsWith(this.root);
	}

	public Map<String,List<Diagnostic>> getDiagnostics()
	{
		return diagnostics;
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

	private void check(GlobalData globalData)
	{
		diagnostics = globalData.diagnostics;

		game = new Game(Library.ENGLISH);

		game.components.putAll(globalData.components);

		if (globalData.diagnostics.isEmpty())
		{
			generateCode(game);
		}
	}

	private void generateCode(Game game)
	{
		var path = Paths.get(root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "Ⲙ.json");

		if (new File(path.toString()).exists())
		{
			try
			{
				var configuration = Files.readAllLines(path);
				for (var line : configuration)
				{
					for (var engine : Engine.values())
					{
						if (line.startsWith(engine.identifier))
						{
							var output = line.substring((engine.identifier+": ").length());
							if (output.startsWith("./"))
							{
								output = output.replace(".", root);
							}
							generator.generate(game, engine, output);
						}
					}
				}
			}
			catch (IOException e)
			{

			}
		}
		else
		{
			generator.generate(game, Paths.get(root));
		}
	}
}
