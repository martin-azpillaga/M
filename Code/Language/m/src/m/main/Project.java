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

import m.validation.rules.Binding;

import com.google.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.xtext.EcoreUtil2;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.Generator;
import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.m.Function;
import m.validation.Inference;
import m.validation.Validator;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Binding.BindingReason;

public class Project
{
    Validator validator;
    Generator generator;
    Game game;

    String path;
    Map<String,FileData> files;

    public Project(String path)
    {
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		this.validator = injector.getInstance(Validator.class);
		this.generator = injector.getInstance(Generator.class);
		this.path = path;
		this.files = new HashMap<>();
        initialize();
    }

    public boolean contains(String path)
    {
        return path.startsWith(this.path);
    }

    public Map<String,List<Diagnostic>> getDiagnostics()
    {
        return null;
    }

    public void fileAdded(String path)
    {
        if (!contains(path)) return;

        files.put(decode(path), null);

        try {
			var text = new String(Files.readAllBytes(Paths.get(decode(path))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void fileDeleted(String uri)
    {
		if (!contains(uri)) return;

        files.remove(decode(uri));
    }

    public String hover(String path, Position position)
    {
        /*
		var result = "";
		
		var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;
		var localData = workspace.files.get(file);

		var text = localData.text;
		var position = params.getPosition();
		var offset = offset(text, position.getLine(), position.getCharacter());
		
		var node = NodeModelUtils.findLeafNodeAtOffset(localData.rootNode, offset);
		
		if (node instanceof HiddenLeafNode)
		{
			var hover = new Hover();
			var contents = new MarkupContent("markdown", result);
			hover.setContents(contents);
			return CompletableFuture.supplyAsync(() -> hover);
		}
		var semantic = node.getSemanticElement();
		
		if (semantic instanceof Function)
		{
			var function = (Function) semantic;
			if (node.getText().equals(function.getName()))
			{
				result = "User system";
			}
		}
		else if (semantic instanceof Value)
		{
			var value = (Value) semantic;
			
			var container = value.eContainer();
			
			if (container instanceof Cell)
			{
				var cell = (Cell) container;
				if (cell.getComponent() == value)
				{
					var standard = library.getComponent(value.getName());

					if (standard != null)
					{
						var type = library.getName(standard.getType());
						result = "Standard component : " + type;
					}
					else
					{
						var type = workspace.game.components.get(value.getName());
						if (type != null)
						{
							result = "User component : " + library.getName(type);
						}
						else
						{
							result = "User component : ?";
						}
					}
				}
				else
				{
					var function = EcoreUtil2.getContainerOfType(cell, Function.class);
					UserFunction userFunction = null;
					for (var f : workspace.game.functions)
					{
						if (f.getName().equals(function.getName()))
						{
							userFunction = f;
							break;
						}
					}
					var query = userFunction.queries.get(value.getName());
					
					if (query == null)
					{
						var standard = library.getValue(value.getName());

						if (standard != null)
						{
							var type = library.getName(standard.getType());
							result = "Standard value : " + type;
						}
						else
						{
							var type = workspace.game.values.get(value);
							if (type != null)
							{
								result = "User value : " + library.getName(type);
							}
							else
							{
								result = "user value : ?";
							}
						}
					}
					else
					{
						result = "Entity query\n\n";
						var builder = new StringBuilder(result);
						for (var component : query.entrySet())
						{
							builder.append("* ");
							builder.append(component.getKey());
							builder.append("\n\n");
						}
						result = builder.toString();
					}
				}
			}
			else
			{
				var function = EcoreUtil2.getContainerOfType(value, Function.class);
				UserFunction userFunction = null;
				for (var f : workspace.game.functions)
				{
					if (f.getName().equals(function.getName()))
					{
						userFunction = f;
						break;
					}
				}
				var query = userFunction.queries.get(value.getName());
				
				if (query == null)
				{
					var standard = library.getValue(value.getName());

					if (standard != null)
					{
						var type = library.getName(standard.getType());
						result = "Standard value : " + type;
					}
					else
					{
						var type = workspace.game.values.get(value);
						if (type != null)
						{
							result = "User value : " + library.getName(type);
						}
						else
						{
							result = "user value : ?";
						}
					}
				}
				else
				{
					result = "Entity query\n\n";
					var builder = new StringBuilder(result);
					for (var component : query.entrySet())
					{
						builder.append("* ");
						builder.append(component.getKey());
						builder.append("\n\n");
					}
					result = builder.toString();
				}
			}
		}
		else if (semantic instanceof Binary)
		{
			var binary = (Binary) semantic;
			var standard = library.getFunction(binary.getOperator());
			if (standard != null)
			{
				result = "Standard operator\n\nType: " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Unary)
		{
			var unary = (Unary) semantic;
			var standard = library.getFunction(unary.getOperator());
			if (standard != null)
			{
				result = "Standard operator\n\nType: " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Application)
		{
			var application = (Application) semantic;
			var standard = library.getFunction(application.getName());
			if (standard != null)
			{
				result = "Standard function\n\nType: " + library.getName(standard.getType());
			}
		}

		var hover = new Hover();
		var contents = new MarkupContent("markdown", result);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);*/
        return "hover";
    }

    public List<CompletionItem> completions(String path, Position position)
    {
        var result = new ArrayList<CompletionItem>();
        /*
		var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;
		var localData = workspace.files.get(file);

		var text = localData.text;
		var position = params.getPosition();
		var offset = offset(text, position.getLine(), position.getCharacter());
		
		var node = NodeModelUtils.findLeafNodeAtOffset(localData.rootNode, offset-1);
		
		var semantic = node.getSemanticElement();
		var grammatic = node.getGrammarElement();
		
		if (semantic instanceof Cell || semantic instanceof Value && semantic.eContainer() instanceof Cell)
		{
			Cell cell;
			if (semantic instanceof Cell)
			{
				cell = (Cell) semantic;
			}
			else
			{
				cell = (Cell) semantic.eContainer();
			}

			for (var component : workspace.game.components.keySet())
            {
				if (component == null || cell.getComponent() != null && cell.getComponent().getName() != null && cell.getComponent().getName().equals(component))
				{
					continue;
				}
                var item = new CompletionItem(component);
				item.setDocumentation(library.getDescription(library.getComponent(component)));
				item.setDetail(library.getName(workspace.game.components.get(component)));
                item.setKind(CompletionItemKind.Class);
                result.add(item);
            }
			for (var component : Component.values())
			{
                var item = new CompletionItem(library.getName(component));
				item.setDocumentation(library.getDescription(component));
				item.setDetail(library.getName(component.getType()));
                item.setKind(CompletionItemKind.Enum);
                result.add(item);
			}
		}
		else if (grammatic instanceof TerminalRule && ((TerminalRule)grammatic).getName().equals("IDENTIFIER"))
		{			
			for (var function : m.library.symbols.Function.values())
            {
                if (function != m.library.symbols.Function.ASSIGNMENT)
                {
                    var item = new CompletionItem(library.getName(function));
                    item.setKind(CompletionItemKind.Function);
                    result.add(item);
                }
            }

            for (var value : m.library.symbols.Value.values())
            {
                var item = new CompletionItem(library.getName(value));
                item.setKind(CompletionItemKind.Value);
                result.add(item);
			}
			
			EObject statement = EcoreUtil2.getContainerOfType(semantic, Statement.class);
			if (statement == null)
			{
				statement = EcoreUtil2.getContainerOfType(semantic, Function.class);
			}
			if (statement == semantic)
			{
				if (statement instanceof BindingBlock)
				{
					var name = ((BindingBlock) statement).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}
				// identifier lost in a block
				var siblings = node.getParent().getParent().getChildren();
				for (var sibling : siblings)
				{
					if (sibling == node.getParent())
					{
						break;
					}
					else if (sibling instanceof CompositeNode && ((CompositeNode)sibling).getFirstChild() instanceof CompositeNodeWithSemanticElement)
					{
						var semanticOfSibling = ((CompositeNode)sibling).getFirstChild().getSemanticElement();
						if (semanticOfSibling instanceof Assignment)
						{
							var assignment = (Assignment) semanticOfSibling;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								result.add(item);
							}
						}
					}
				}
			}
			var container = statement.eContainer();

			while(! (container instanceof m.m.File))
			{
				if (container instanceof BindingBlock)
				{
					var name = ((BindingBlock) container).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}

				List<Statement> statements;

				if (container instanceof Block)
				{
					statements = ((Block) container).getStatements();
				}
				else if (container instanceof BindingBlock)
				{
					statements = ((BindingBlock) container).getStatements();
				}
				else if (container instanceof Function)
				{
					statements = ((Function) container).getStatements();
				}
				else
				{
					statements = new ArrayList<>();
				}

				for (var s : statements)
				{
					if (s == statement)
					{
						break;
					}
					else
					{
						if (s instanceof Assignment)
						{
							var assignment = (Assignment) s;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								item.setSortText("0"+atom.getName());
								result.add(item);
							}
						}
					}
				}

				var temp = container.eContainer();
				statement = container;
				container = temp;
			}
		}
		else if (semantic instanceof Value)
		{
			for (var function : m.library.symbols.Function.values())
            {
                if (function != m.library.symbols.Function.ASSIGNMENT)
                {
                    var item = new CompletionItem(library.getName(function));
                    item.setKind(CompletionItemKind.Function);
                    result.add(item);
                }
            }

            for (var value : m.library.symbols.Value.values())
            {
                var item = new CompletionItem(library.getName(value));
                item.setKind(CompletionItemKind.Value);
                result.add(item);
			}
			
			EObject statement = EcoreUtil2.getContainerOfType(semantic, Statement.class);
			var container = statement.eContainer();

			while(! (container instanceof m.m.File))
			{
				if (container instanceof BindingBlock)
				{
					var name = ((BindingBlock) container).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}

				List<Statement> statements;

				if (container instanceof Block)
				{
					statements = ((Block) container).getStatements();
				}
				else if (container instanceof BindingBlock)
				{
					statements = ((BindingBlock) container).getStatements();
				}
				else if (container instanceof Function)
				{
					statements = ((Function) container).getStatements();
				}
				else
				{
					statements = new ArrayList<>();
				}

				for (var s : statements)
				{
					if (s == statement)
					{
						break;
					}
					else
					{
						if (s instanceof Assignment)
						{
							var assignment = (Assignment) s;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								item.setSortText("0"+atom.getName());
								result.add(item);
							}
						}
					}
				}

				var temp = container.eContainer();
				statement = container;
				container = temp;
			}
		}
        */
		return result;
    }

    public SignatureHelp signature(String path, Position position)
    {
        var help = new SignatureHelp();
        /*
        var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;

		var offset = offset(workspace.files.get(file).text, params.getPosition().getLine(), params.getPosition().getCharacter());
		var node = NodeModelUtils.findLeafNodeAtOffset(workspace.files.get(file).rootNode, offset-1);

		var help = new SignatureHelp();
		var list = new ArrayList<SignatureInformation>();
		
		if (node != null && node.getSemanticElement() instanceof Application)
		{
			var application = (Application) node.getSemanticElement();
			var name = application.getName();
			var standard = library.getFunction(name);

			if (standard != null)
			{
				var type = (FunctionType) standard.getType();
				var parameters = new ArrayList<ParameterInformation>();
				for (var i = 0; i < type.parameterTypes.length; i++)
				{
					parameters.add(new ParameterInformation("Parameter "+i, library.getName(type.parameterTypes[i])));
				}
				var info = new SignatureInformation(name + " : " + library.getName(type), library.getDescription(standard), parameters);
				list.add(info);
				help.setSignatures(list);
				if (params.getContext().getTriggerCharacter() != null)
				{
					help.setActiveParameter(application.getArguments().size());
				}
				else
				{
					help.setActiveParameter(application.getArguments().size()-1);
				}
				return CompletableFuture.supplyAsync(()->help);
			}
		}

		
		
		help.setSignatures(list);*/
		return help;
    }

    public void fileChanged(String path, String text)
    {
        if (!contains(path)) return;

        /*
        var filePath = decode(path);
        
		var diagnostics = localInference(filePath, workspace, text);
		
		var globalDiagnostics = globalInference(workspace, filePath);

		if (globalDiagnostics.containsKey(filePath))
		{
			globalDiagnostics.get(filePath).addAll(diagnostics);
		}
		else
		{
			globalDiagnostics.put(filePath, diagnostics);
		}

		for (var entry : workspace.files.entrySet())
		{
			if (globalDiagnostics.containsKey(entry.getKey()))
			{
				client.publishDiagnostics(new PublishDiagnosticsParams("file://"+entry.getKey(), globalDiagnostics.get(entry.getKey())));
			}
			else
			{
				client.publishDiagnostics(new PublishDiagnosticsParams("file://"+entry.getKey(), new ArrayList<>()));
			}
		}*/
    }

    private void initialize()
    {
		var root = decode(path);

		try (var folder = Files.walk(Paths.get(root)))
		{
			folder.forEach(f -> 
			{
				var file = f.toString();
				if (file.endsWith(".Ⲙ"))
				{
					try
					{
						var text = new String(Files.readAllBytes(f));
						files.put(file, null);
					}
					catch (IOException e)
					{
						
					}
				}
			});
		}
		catch (IOException e)
		{

		}
    }

    /*

    private HashMap<String, List<Diagnostic>> globalInference(Workspace workspace, String modifiedFile)
	{
		var diagnostics = new HashMap<String, List<Diagnostic>>();
        
        // Delete previous crossReferences to this file

        var crossReferences = workspace.crossReferences;

		for (var i = 0; i < crossReferences.size(); i++)
		{
			var crossReference = crossReferences.get(i);
			if (crossReference.fileA.equals(modifiedFile))
			{
				crossReference.nodeB.bindings.remove(crossReference.bindingB);
				crossReferences.remove(crossReference);
			}
			else if (crossReference.fileB.equals(modifiedFile))
			{
				crossReference.nodeA.bindings.remove(crossReference.bindingA);
				crossReferences.remove(crossReference);
			}
		}
		
		
		var totalNodes = new ArrayList<ExpressionNode>();
		var totalComponents = new HashMap<String, ExpressionNode>();
		var totalFunctions = new HashMap<String, Function>();
		var definedIn = new HashMap<String,String>();
		

		for (var entry : workspace.files.entrySet())
		{
			var currentFile = entry.getKey();
			var data = entry.getValue();
			totalNodes.addAll(data.nodes);
			
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
					var file = fileOf(totalFunctions.get(function.getKey()), workspace);

					reportRedefinedFunction(totalFunctions.get(function.getKey()), workspace, file, diagnostics);
					reportRedefinedFunction(function.getValue(), workspace, currentFile, diagnostics);
				}
				else
				{
					totalFunctions.put(function.getKey(), function.getValue());
				}
			}
		}
		
		var inference = new Inference(totalNodes);
		var problems = inference.check();
		
		for (var problem : problems)
		{
			FileData data = null;
			String file;
			if (problem instanceof UndecidableType)
			{
				var undecidable = (UndecidableType) problem;
				var root = EcoreUtil2.getRoot(undecidable.node.expression, true);	
				for (var entry : workspace.files.entrySet())
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

		for (var localData : workspace.files.values())
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

		var game = new Game(Library.ENGLISH);
			
		for (var component : totalComponents.entrySet())
		{
			var type = inference.infer(component.getValue());
			game.components.put(component.getKey(), type);
		}
		
		for (var function : totalFunctions.values())
		{
			game.functions.add(new UserFunction(function, new FunctionType(null, AtomicType.UNIT)));
		}

		workspace.game = game;

		if (!hasErrors)
		{
			generateCode(game, workspace);
		}
		
		return diagnostics;
	}

	private void reportRedefinedFunction(Function function, Workspace workspace, String file, Map<String,List<Diagnostic>> diagnostics)
	{
		if (! diagnostics.containsKey(file))
		{
			diagnostics.put(file, new ArrayList<>());
		}

		var redefined = new RedefinedSymbol(function, FUNCTION__NAME);

		for (var diagnostic : redefined.diagnostics(Library.ENGLISH, workspace.files.get(file).text))
		{
			diagnostics.get(file).add(diagnostic);
		}
	}

	private String fileOf(EObject o, Workspace workspace)
	{
		for (var file : workspace.files.entrySet())
		{
			if (EcoreUtil.getRoot(o, true) == file.getValue().rootObject)
			{
				return file.getKey();
			}
		}
		return "";
	}
	
	
	
	
	private List<Diagnostic> localInference(String file, Workspace workspace, String text)
	{
		var data = validator.validate(text);

		workspace.files.put(file, data);

		var diagnostics = new ArrayList<Diagnostic>();

		for (var problem : data.problems)
		{
			diagnostics.addAll(problem.diagnostics(Library.ENGLISH, text));
		}
		
		return diagnostics;
	}

	private void generateCode(Game game, Workspace workspace)
	{		
		var path = Paths.get(workspace.root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
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
								output = output.replace(".", workspace.root);
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
				var defaultPath = Paths.get(workspace.root, "Output", engine.identifier).toString();
				generator.generate(game, engine, defaultPath);
			}
		}
	}
	
	private int offset(String text, int line, int character)
	{
		var count = 0;
		for (var i = 0; i < text.length(); i++)
		{
			if (count == line)
			{
				return i+character;
			}
			if (text.charAt(i) == '\n')
			{
				count++;
			}
		}
		return text.length();
	}*/
	
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
    /*
    private HashMap<String, List<Diagnostic>> globalInference(Workspace workspace, String modifiedFile)
	{
		var diagnostics = new HashMap<String, List<Diagnostic>>();
        
        // Delete previous crossReferences to this file

        var crossReferences = workspace.crossReferences;

		for (var i = 0; i < crossReferences.size(); i++)
		{
			var crossReference = crossReferences.get(i);
			if (crossReference.fileA.equals(modifiedFile))
			{
				crossReference.nodeB.bindings.remove(crossReference.bindingB);
				crossReferences.remove(crossReference);
			}
			else if (crossReference.fileB.equals(modifiedFile))
			{
				crossReference.nodeA.bindings.remove(crossReference.bindingA);
				crossReferences.remove(crossReference);
			}
		}
		
		
		var totalNodes = new ArrayList<ExpressionNode>();
		var totalComponents = new HashMap<String, ExpressionNode>();
		var totalFunctions = new HashMap<String, Function>();
		var definedIn = new HashMap<String,String>();
		

		for (var entry : workspace.files.entrySet())
		{
			var currentFile = entry.getKey();
			var data = entry.getValue();
			totalNodes.addAll(data.nodes);
			
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
					var file = fileOf(totalFunctions.get(function.getKey()), workspace);

					reportRedefinedFunction(totalFunctions.get(function.getKey()), workspace, file, diagnostics);
					reportRedefinedFunction(function.getValue(), workspace, currentFile, diagnostics);
				}
				else
				{
					totalFunctions.put(function.getKey(), function.getValue());
				}
			}
		}
		
		var inference = new Inference(totalNodes);
		var problems = inference.check();
		
		for (var problem : problems)
		{
			FileData data = null;
			String file;
			if (problem instanceof UndecidableType)
			{
				var undecidable = (UndecidableType) problem;
				var root = EcoreUtil2.getRoot(undecidable.node.expression, true);	
				for (var entry : workspace.files.entrySet())
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

		for (var localData : workspace.files.values())
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

		var game = new Game(Library.ENGLISH);
			
		for (var component : totalComponents.entrySet())
		{
			var type = inference.infer(component.getValue());
			game.components.put(component.getKey(), type);
		}
		
		for (var function : totalFunctions.values())
		{
			game.functions.add(new UserFunction(function, new FunctionType(null, AtomicType.UNIT)));
		}

		workspace.game = game;

		if (!hasErrors)
		{
			generateCode(game, workspace);
		}
		
		return diagnostics;
	}

	private void reportRedefinedFunction(Function function, Workspace workspace, String file, Map<String,List<Diagnostic>> diagnostics)
	{
		if (! diagnostics.containsKey(file))
		{
			diagnostics.put(file, new ArrayList<>());
		}

		var redefined = new RedefinedSymbol(function, FUNCTION__NAME);

		for (var diagnostic : redefined.diagnostics(Library.ENGLISH, workspace.files.get(file).text))
		{
			diagnostics.get(file).add(diagnostic);
		}
	}

	private String fileOf(EObject o, Workspace workspace)
	{
		for (var file : workspace.files.entrySet())
		{
			if (EcoreUtil.getRoot(o, true) == file.getValue().rootObject)
			{
				return file.getKey();
			}
		}
		return "";
	}
	
	
	
	
	private List<Diagnostic> localInference(String file, Workspace workspace, String text)
	{
		var data = validator.validate(text);

		workspace.files.put(file, data);

		var diagnostics = new ArrayList<Diagnostic>();

		for (var problem : data.problems)
		{
			diagnostics.addAll(problem.diagnostics(Library.ENGLISH, text));
		}
		
		return diagnostics;
	}

	private void generateCode(Game game, Workspace workspace)
	{		
		var path = Paths.get(workspace.root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
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
								output = output.replace(".", workspace.root);
							}
							generator.generate(game, engine, output);
						}
					}
				}
			}
			catch (IOException e)
			{
				write(e.getMessage());
			}
		}
		else
		{
			for (var engine : Engine.values())
			{
				var defaultPath = Paths.get(workspace.root, "Output", engine.identifier).toString();
				generator.generate(game, engine, defaultPath);
			}
		}
	}
	
	private int offset(String text, int line, int character)
	{
		var count = 0;
		for (var i = 0; i < text.length(); i++)
		{
			if (count == line)
			{
				return i+character;
			}
			if (text.charAt(i) == '\n')
			{
				count++;
			}
		}
		return text.length();
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
*/
}