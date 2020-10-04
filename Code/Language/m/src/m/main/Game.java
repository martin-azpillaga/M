package m.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.SignatureHelp;

import m.library.Library;
import m.library.types.*;
import m.m.Value;

public class Game
{
	public final Library library;
	public final Map<String, Type> components;
	public final List<UserFunction> functions;
	public final Map<Value, Type> values;

	public Game(Library library)
	{
		this.library = library;
		this.components = new HashMap<>();
		this.functions = new ArrayList<>();
		this.values = new HashMap<>();
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
}
