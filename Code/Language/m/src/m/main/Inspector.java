package m.main;

import static m.library.symbols.Classification.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.ParameterInformation;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.lsp4j.SignatureHelpParams;
import org.eclipse.lsp4j.SignatureInformation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import m.library.Library;
import m.library.symbols.Component;
import m.library.symbols.Classification;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.model.Application;
import m.model.Assignment;
import m.model.Binary;
import m.model.BindingBlock;
import m.model.Block;
import m.model.Cell;
import m.model.Function;
import m.model.Game;
import m.model.Statement;
import m.model.Unary;
import m.model.UserFunction;
import m.model.Value;

public class Inspector
{
	Library library;
	Game game;
	Map<String,INode> files;

	public Inspector()
	{
		this.files = new HashMap<>();
		this.library = Library.ENGLISH;
	}

	public void update(String file, INode node)
	{
		files.put(file,node);
	}

	public void delete(String file)
	{
		files.remove(file);
	}

	public void update(Game game)
	{
		this.game = game;
	}

	public String hover(String file, Position position)
	{
		var node = nodeAt(file, position);
		var semantic = node.getSemanticElement();

		if (semantic instanceof Function)
		{
			var function = (Function) semantic;
			if (node.getText().equals(function.getName()))
			{
				return library.getDescription(Classification.USER_SYSTEM);
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
						var type = standard.getType();
						return library.getDescription(STANDARD_COMPONENT) + " : " + library.getName(type);
					}
					else
					{
						var type = game.components.get(value.getName());
						if (type != null)
						{
							return library.getDescription(USER_COMPONENT) + " : " + library.getName(type);
						}
						else
						{
							return library.getDescription(USER_COMPONENT) + " : ?";
						}
					}
				}
				else
				{
					var function = EcoreUtil2.getContainerOfType(cell, Function.class);
					UserFunction userFunction = null;
					for (var f : game.functions)
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
							return library.getDescription(STANDARD_VALUE)+ " : " + type;
						}
						else
						{
							var type = game.values.get(value);
							if (type != null)
							{
								return library.getDescription(USER_VALUE) + " : " + library.getName(type);
							}
							else
							{
								return library.getDescription(USER_VALUE) + " : ?";
							}
						}
					}
					else
					{
						var builder = new StringBuilder();
						builder.append(library.getDescription(QUERY_ENTITY)+"\n\n");
						for (var component : query.entrySet())
						{
							builder.append("* ");
							builder.append(component.getKey());
							builder.append("\n\n");
						}
						return builder.toString();
					}
				}
			}
			else
			{
				var function = EcoreUtil2.getContainerOfType(value, Function.class);
				UserFunction userFunction = null;
				for (var f : game.functions)
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
						return library.getDescription(STANDARD_VALUE) + " : " + type;
					}
					else
					{
						var type = game.values.get(value);
						if (type != null)
						{
							return library.getDescription(USER_VALUE) + " : " + library.getName(type);
						}
						else
						{
							return library.getDescription(USER_VALUE) + " : ?";
						}
					}
				}
				else
				{
					var builder = new StringBuilder();
					builder.append(library.getDescription(QUERY_ENTITY)+"\n\n");
					for (var component : query.entrySet())
					{
						builder.append("* ");
						builder.append(component.getKey());
						builder.append("\n\n");
					}
					return builder.toString();
				}
			}
		}
		else if (semantic instanceof Binary)
		{
			var binary = (Binary) semantic;
			var standard = library.getFunction(binary.getOperator());
			if (standard != null)
			{
				return library.getDescription(STANDARD_OPERATOR) + " : " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Unary)
		{
			var unary = (Unary) semantic;
			var standard = library.getFunction(unary.getOperator());
			if (standard != null)
			{
				return library.getDescription(STANDARD_OPERATOR) + " : " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Application)
		{
			var application = (Application) semantic;
			var standard = library.getFunction(application.getName());
			if (standard != null)
			{
				return library.getDescription(STANDARD_OPERATOR) + " : " + library.getName(standard.getType());
			}
		}

		return "";
	}

	private INode nodeAt(String file, Position position)
	{
		var rootNode = files.get(file);
		var offset = offset(rootNode.getText(), position.getLine(), position.getCharacter());

		var node = NodeModelUtils.findLeafNodeAtOffset(rootNode, offset > 0 ? offset-1 : 0);

		return node;
	}
	
	public List<CompletionItem> completions(String path, Position position)
	{
		return new ArrayList<CompletionItem>();
	}

	public List<CompletionItem> completions(Position position, INode rootNode)
	{
		var result = new ArrayList<CompletionItem>();

		var text = rootNode.getText();
		var offset = offset(text, position.getLine(), position.getCharacter());

		var node = NodeModelUtils.findLeafNodeAtOffset(rootNode, offset-1);

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

			for (var component : game.components.keySet())
			{
				if (component == null || cell.getComponent() != null && cell.getComponent().getName() != null && cell.getComponent().getName().equals(component))
				{
					continue;
				}
				var item = new CompletionItem(component);
				item.setDocumentation(library.getDescription(library.getComponent(component)));
				item.setDetail(library.getName(game.components.get(component)));
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

			while(! (container instanceof m.model.File))
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

			while(! (container instanceof m.model.File))
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

		return result;
	}

	public List<SignatureInformation> signatures(String path, Position position)
	{
		return new ArrayList<SignatureInformation>();
	}

	public SignatureHelp signature(INode rootNode, Position position, SignatureHelpParams params)
	{
		var help = new SignatureHelp();

		var text = rootNode.getText();
		var offset = offset(text, position.getLine(), position.getCharacter());
		var node = NodeModelUtils.findLeafNodeAtOffset(rootNode, offset-1);

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
					parameters.add(new ParameterInformation(i+"", library.getName(type.parameterTypes[i])));
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
			}
		}

		help.setSignatures(list);
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
