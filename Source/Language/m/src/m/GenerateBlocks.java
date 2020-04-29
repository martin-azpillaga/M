package m;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;

import m.model.ModelPackage;

public class GenerateBlocks
{
	static StringBuilder builder = new StringBuilder();
	static String inside = "";
	
	static HashSet<String> topdown = new HashSet<String>();
	static HashSet<String> left = new HashSet<String>();
	static HashSet<String> leftRight = new HashSet<String>();
	static LinkedHashMap<String,ArrayList<String>> categories = new LinkedHashMap<String,ArrayList<String>>();
	static HashMap<String,ArrayList<String>> renames = new HashMap<String,ArrayList<String>>();
	static String nextType = null;
	
	private static String print(ArrayList<Category> categories)
	{
		line("var toolbox = document.createElement('XML')");
		line("toolbox.setAttribute('xmlns','https://developers.google.com/blockly/xml')");
		line("toolbox.innerHTML =");
		line("`");
		for (var category : categories)
		{
			line(category.name)
		}
		line("`");
		
		return builder.toString();
	}
	
	private static void line(String content)
	{
		builder.append(content+"\n");
	}
	
	public static void main(String[] args) throws IOException
	{
		var m = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var grammar = m.getInstance(IGrammarAccess.class).getGrammar();
		
		var model = ModelPackage.eINSTANCE;

		categories.put("Root",new ArrayList<String>());
		
		for (var rule : grammar.getRules())
		{
			if (rule == grammar.getRules().get(0)) continue;
			if (rule.getAlternatives() instanceof Alternatives&&rule instanceof ParserRule)
			{
				var alternatives = (Alternatives) rule.getAlternatives();
				var names = new ArrayList<String>();
				for (var alternative : alternatives.getElements())
				{
					if (alternative instanceof RuleCall)
					{
						var name = ((RuleCall) alternative).getRule().getName();
						names.add(name);
						if (topdown.contains(rule.getName()))
						{
							topdown.add(name);
						}
						else if (left.contains(rule.getName()))
						{
							left.add(name);
						}
					}
				}
				renames.put(rule.getName(), names);
				continue;
			}
			var name = rule.getName();
			for (var classifier : model.getEClassifiers())
			{
				if (classifier.getName().equals(name))
				{
					if (classifier instanceof EClass)
					{
						var eclass = (EClass) classifier;
						categories(eclass);
						blockify(eclass);
					}
				}
			}
		}
		builder.append("var toolbox = document.createElement('XML')\n");
		builder.append("toolbox.setAttribute('xmlns','https://developers.google.com/blockly/xml')\n");
		builder.append("toolbox.innerHTML =\n`");
		for (var category : categories.keySet())
		{
			builder.append("<category name='"+category+"'>\n");
			for (var sub : categories.get(category))
			{
				builder.append("<block type='"+sub+"'></block>\n");
			}
			builder.append("</category>\n");
		}
		builder.append("`\n");
		builder.append("var workspace = Blockly.inject('blocklyDiv', {toolbox: toolbox, zoom: {controls: true, wheel: true}})\n\n");
		
		// Post processing
		var total = builder.toString();
		for (var rename : renames.keySet())
		{
			var type = buildType(renames.get(rename));
			total = total.replace("setCheck('"+rename+"'", "setCheck("+type);
		}
		Files.write(Paths.get("../../Application/Blockly/custom.js"), total.getBytes());
	}
	
	private static void categories(EClass eclass)
	{
		var superTypes = eclass.getESuperTypes();
		if (superTypes.isEmpty())
		{
			categories.get("Root").add(eclass.getName());
		}
		else if (superTypes.size() == 1)
		{
			var superType = superTypes.get(0).getName();
			if (!categories.containsKey(superType))
			{
				categories.put(superType, new ArrayList<String>());
			}
			categories.get(superType).add(eclass.getName());
		}
	}
	
	private static void connections(EClass eclass)
	{
		var name = eclass.getName();
		if (topdown.contains(name))
		{
			builder.append("this.setPreviousStatement(true,'"+name+"')\n");
			builder.append("this.setNextStatement(true,"+nextType+")\n");
		}
		else if (left.contains(name))
		{
			builder.append("this.setOutput(true, '"+name+"')\n");
			//builder.append("this.appendValueInput('next').setCheck("+nextType+")\n");
		}
	}
	
	private static void blockify(EClass eclass)
	{
		builder.append("Blockly.Blocks['"+eclass.getName()+"'] =\n");
		builder.append("{\n");
		builder.append("init: function()\n");
		builder.append("{\n");
		String extra = "";
		for (var e : eclass.getEAllStructuralFeatures())
		{
			var type = e.getEType().getName();
			if (renames.containsKey(type))
			{
				type = buildType(renames.get(type));
			}
			else
			{
				type = "'"+type+"'";
			}
			if (e instanceof EAttribute)
			{
				extra += ".appendField(new Blockly.FieldTextInput('"+e.getName()+"'), '"+e.getName()+"')";
			}
			else if (e instanceof EReference)
			{
				if (e.getName().equals("type")) continue;
				if (e.getUpperBound() == 1)
				{
					builder.append("this.appendValueInput('"+e.getName()+"').setCheck("+type+")");
					left.add(e.getEType().getName());
				}
				else
				{
					builder.append("this.appendStatementInput('"+e.getName()+"').setCheck("+type+")");
					nextType = type;
					topdown.add(e.getEType().getName());
				}
				if (extra != "")
				{
					builder.append(extra);
					extra = "";
				}
				builder.append("\n");
			}
		}
		if (extra != "")
		{
			builder.append("this.appendDummyInput()"+extra+"\n");
		}
		connections(eclass);
		builder.append("this.setInputsInline(true)\n");
		builder.append("this.setColour(260)\n");
		builder.append("}\n");
		builder.append("}\n");
	}
	
	private static String buildType(ArrayList<String> types)
	{
		var type = "";
		for (var i = 0; i < types.size(); i++)
		{
			if (i == 0)
			{
				type = "[ '" + types.get(0) +"'";
			}
			else
			{
				type += ", '" + types.get(i) + "'";
			}
		}
		type += "]";
		return type;
	}
}
