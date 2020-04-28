package m;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;

import com.google.inject.Guice;

public class GenerateBlocks
{
	static StringBuilder builder = new StringBuilder();
	static String inside = "";
	
	static ArrayList<String> covered;
	
	public static void main(String[] args)
	{
		var m = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var grammar = m.getInstance(IGrammarAccess.class).getGrammar();
		for (var rule : grammar.getRules())
		{
			if (rule instanceof ParserRule)
			{
				if (rule == grammar.getRules().get(0))
				{
					continue;
				}
				if (rule.getAlternatives() instanceof Alternatives)
				{
					continue;
				}
				var name = rule.getName().toLowerCase();
				
				builder.append("Blockly.Blocks['"+name+"'] =\n{\n");
				builder.append("init: function()\n{\n");

				covered = new ArrayList<String>();
				analyze(rule.getAlternatives());
				
				builder.append("this.setColour(200);\n");
				builder.append("}\n}\n\n");
			}
		}
		System.out.println(builder.toString());
	}
	
	private static void analyze(AbstractElement element)
	{
		if (element instanceof Assignment)
		{
			var assignment = (Assignment) element;
			var cardinality = assignment.getCardinality();
			var feature = assignment.getFeature();
			var terminal = assignment.getTerminal();
			var operator = assignment.getOperator().toString();
			var type = "";
			if (terminal instanceof RuleCall)
			{
				type = ((RuleCall) terminal).getRule().getName().toLowerCase();
			}
			
			if (operator.equals("="))
			{
				if (terminal instanceof RuleCall)
				{
					if (((RuleCall) terminal).getRule() instanceof TerminalRule)
					{
						inside = ".appendField(new Blockly.FieldTextInput('"+feature+"'), '"+feature+"')";
					}
					else
					{
						builder.append("this.appendValueInput('"+feature+"').setCheck('"+type+"');\n");
					}
				}
			}
			else if (operator.equals("+="))
			{
				if (!covered.contains(feature))
				{
					covered.add(feature);
					if (terminal instanceof RuleCall)
					{
						if (((RuleCall) terminal).getRule() instanceof ParserRule)
						{
							builder.append("this.appendStatementInput('"+feature+"').setCheck('"+type+"')");
							if (inside != null)
							{
								builder.append(inside);
								inside = null;
							}
							builder.append(";\n");
						}
					}
				}
			}
		}
		else if (element instanceof Group)
		{
			for (var e : ((Group) element).getElements())
			{
				analyze(e);
			}
		}
	}
}
