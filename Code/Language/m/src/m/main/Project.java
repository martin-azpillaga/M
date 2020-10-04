package m.main;

import static m.m.MPackage.Literals.FUNCTION__NAME;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.lsp4j.Diagnostic;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.Generator;
import m.library.Library;
import m.m.Function;
import m.validation.Validator;
import m.validation.LocalValidator;
import m.validation.problems.errors.RedefinedSymbol;

// Generic two step project
// Local validation for each file and a global validation pass INCREMENTAL
// Stores diagnostics on each file
// Generate code if no error diagnostics
public class Project
{
    String root;

	Validator validator;
    Generator generator;
	public Game game;

    public Project(String root)
    {
		this.root = root;
		this.generator = new Generator();
		this.validator = new Validator();

        try (var walk = Files.walk(Paths.get(decode(root))))
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
        return new HashMap<>();
    }

    public void fileAdded(String uri)
    {
        if (!contains(uri)) return;

		try
		{
			var text = new String(Files.readAllBytes(Paths.get(decode(uri))));
			fileChanged(uri, text);
		}
		catch (IOException e){}
    }

    public void fileDeleted(String uri)
    {
		if (!contains(uri)) return;

        var file = decode(uri);

		validator.delete(file);
    }

    public void fileChanged(String uri, String text)
    {
        if (!contains(uri)) return;

		var modifiedFile = decode(uri);
		
		var globalData = validator.validate(modifiedFile, text);

		game = new Game(Library.ENGLISH);

		game.components.putAll(globalData.components);

		if (!globalData.hasErrors)
		{
			generateCode(game);
		}
    }

	private void generateCode(Game game)
	{		
		var path = Paths.get(root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
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
			for (var engine : Engine.values())
			{
				var defaultPath = Paths.get(decode(root), "Output", engine.identifier).toString();
				generator.generate(game, engine, defaultPath);
			}
		}
	}
	
	private String decode(String path)
	{
		var result = "";
		try
		{
			result = URLDecoder.decode(path.replace("file://", ""),"UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			result = "Unsupported encoding UTF-8";
		}

		var os = System.getProperty("os.name");

		if (os.startsWith("Win"))
		{
			result = result.substring(1);
		}

		return result;
    }
}