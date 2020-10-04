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
import org.eclipse.lsp4j.DiagnosticSeverity;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.Generator;
import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.m.Function;
import m.validation.Clusterizer;
import m.validation.Inference;
import m.validation.Validator;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.ExpressionNode;

// Generic two step project
// Local validation for each file and a global validation pass INCREMENTAL
// Stores diagnostics on each file
// Generate code if no error diagnostics
public class Project
{
    String root;
	Map<String,FileData> files;
	List<CrossReference> crossReferences;

    Validator validator;
    Generator generator;
	public Game game;
	Clusterizer clusterizer;

    public Project(String path)
    {
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		this.validator = injector.getInstance(Validator.class);
		this.generator = injector.getInstance(Generator.class);
		this.root = path;
		this.clusterizer = new Clusterizer();

		this.files = new HashMap<>();
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
						var data = validator.validate(text);
						files.put(file, data);
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

        var filePath = decode(uri);

		files.remove(filePath);
		
		globalInference(filePath);
    }

    public void fileChanged(String uri, String text)
    {
        if (!contains(uri)) return;

        var filePath = decode(uri);

		files.put(filePath,validator.validate(text));
		
		globalInference(filePath);
    }


    private void globalInference(String modifiedFile)
	{
		var modifiedData = files.get(modifiedFile);

		clusterizer.clusterize(modifiedFile, modifiedData);

		for (var crossReference : modifiedData.crossReferences)
		{
			crossReference.nodeB.bindings.remove(crossReference.bindingB);
		}

		modifiedData.crossReferences.clear();


		var diagnostics = new HashMap<String, List<Diagnostic>>();
        
        // Delete previous crossReferences to this file
		
		// Incremental build
		// Only compare other files to the modified one for creating cross references
		var totalComponents = new HashMap<String, ExpressionNode>();
		var totalFunctions = new HashMap<String, Function>();
		var definedIn = new HashMap<String,String>();
		

		for (var entry : files.entrySet())
		{
			var currentFile = entry.getKey();
			var data = entry.getValue();
			
			for (var component : data.components.keySet())
			{
				var existing = totalComponents.get(component);
				if (existing == null)
				{
					definedIn.put(component, currentFile);
					totalComponents.put(component, data.components.get(component));
				}
				else
				{
					var bindingA = new Binding(existing, BindingReason.SAME_COMPONENT);
					var bindingB = new Binding(data.components.get(component), BindingReason.SAME_COMPONENT);
					var crossReference = new CrossReference(currentFile, data.components.get(component), bindingA, definedIn.get(component), existing, bindingB);
					
					crossReferences.add(crossReference);
					
					data.components.get(component).bindings.add(bindingA);
					existing.bindings.add(bindingB);
				}
			}
			
			for (var function : data.functions.entrySet())
			{
				if (totalFunctions.containsKey(function.getKey()))
				{
					var file = fileOf(totalFunctions.get(function.getKey()));

					reportRedefinedFunction(totalFunctions.get(function.getKey()), file, diagnostics);
					reportRedefinedFunction(function.getValue(), currentFile, diagnostics);
				}
				else
				{
					totalFunctions.put(function.getKey(), function.getValue());
				}
			}
		}
		
		// Here
		var inference = new Inference(files.get(modifiedFile).nodes);
		var problems = inference.check();
		
		for (var problem : problems)
		{
			FileData data = null;
			String file;
			if (problem instanceof UndecidableType)
			{
				var undecidable = (UndecidableType) problem;
				var root = EcoreUtil.getRoot(undecidable.node.expression, true);	
				for (var entry : files.entrySet())
				{
					if (entry.getValue().rootObject == root)
					{
						file = entry.getKey();
						data = entry.getValue();
						break;
					}
				}

				
			}
			for (var d : problem.diagnostics(Library.ENGLISH, data.text))
			{
				//diagnostics.put(file, d);
			}
		}
		
		// Check for global and local errors
		var hasErrors = false;
		
		for (var list : diagnostics.values())
		{
			for (var i = 0; i < list.size() && !hasErrors; i++)
			{
				if (list.get(i).getSeverity() == DiagnosticSeverity.Error)
				{
					hasErrors = true;
				}
			}
		}

		for (var localData : files.values())
		{
			for (var problem : localData.problems)
			{
				for (var diagnostic : problem.diagnostics(Library.ENGLISH, localData.text))
				{
					if (diagnostic.getSeverity() == DiagnosticSeverity.Error)
					{
						hasErrors = true;
					}
				}
			}
		}

		game = new Game(Library.ENGLISH);
			
		for (var component : totalComponents.entrySet())
		{
			var type = inference.infer(component.getValue());
			game.components.put(component.getKey(), type);
		}
		
		for (var function : totalFunctions.values())
		{
			game.functions.add(new UserFunction(function, new FunctionType(null, AtomicType.UNIT)));
		}

		this.game = game;

		if (!hasErrors)
		{
			generateCode(game);
		}
	}

	private void reportRedefinedFunction(Function function, String file, Map<String,List<Diagnostic>> diagnostics)
	{
		if (! diagnostics.containsKey(file))
		{
			diagnostics.put(file, new ArrayList<>());
		}

		var redefined = new RedefinedSymbol(function, FUNCTION__NAME);

		for (var diagnostic : redefined.diagnostics(Library.ENGLISH, files.get(file).text))
		{
			diagnostics.get(file).add(diagnostic);
		}
	}

	private String fileOf(EObject o)
	{
		for (var file : files.entrySet())
		{
			if (EcoreUtil.getRoot(o, true) == file.getValue().rootObject)
			{
				return file.getKey();
			}
		}
		return "";
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