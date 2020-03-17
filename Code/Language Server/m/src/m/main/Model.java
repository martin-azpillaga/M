package m.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import static java.lang.String.*;

import m.game.GamePackage;

public class Model
{	
	static StringBuilder builder = new StringBuilder();
	static int level = 0;
	
	public static void main(String[] arguments) throws IOException
	{
		line("package m.main;");
		line();
		line("import java.util.List;");
		line("import m.game.GameFactory;");
		for (var e : GamePackage.eINSTANCE.eContents())
		{
			if (e instanceof EClassifier)
			{
				var eclass = (EClassifier) e;

				line("import m.game.%s;", eclass.getName());
			}
		}
		line();
		line("public class GameModel");
		line("{");
		line("static GameFactory factory = GameFactory.eINSTANCE;");
		line();
		for(var e : GamePackage.eINSTANCE.eContents())
		{
			if (e instanceof EClass)
			{
				var eclass = (EClass) e;

				constructor(eclass);
			}
		}
		line("}");
		
		Files.write(Paths.get("src/m/main/GameModel.java"),builder.toString().getBytes());
	}
	
	static void constructor(EClass eclass)
	{
		var name = eclass.getName();
		
		var features = eclass.getEAllStructuralFeatures();
		var size = features.size();
		
		var featureSet = ImmutableSet.copyOf(features);
		
		for (var i = 0; i <= size; i++)
		{
			var combinations = Sets.combinations(featureSet, i);
			
			for (var set : combinations)
			{
				appendIndentation();
				append("public static %s %s(", name, lower(name));
				
				var first = true;
				
				for (var feature : set)
				{
					var fname = feature.getName();
					var ftype = name(feature.getEType());
					
					if (!first)
					{
						builder.append(", ");
					}
					first = false;
					
					if (feature.getUpperBound() == 1)
					{
						builder.append(ftype);
					}
					else
					{
						append("%s[]", ftype);
					}
					append(" %s", fname);
				}
				builder.append(")");
				line();
				line("{");
				line("var result = factory.create%s();", name);
				for (var feature : set)
				{
					var fname = feature.getName();
					if (feature.getUpperBound() == 1)
					{
						line("result.set%s(%s);", camel(fname), fname);
					}
					else
					{
						line("for (var each : %s)", fname);
						line("{");
						line("result.get%s().add(each);", camel(fname));
						line("}");
					}
				}
				line("return result;");
				line("}");
			}
		}
	}
	
	static String camel(String word)
	{
		var builder = new StringBuilder();
		builder.append(Character.toTitleCase(word.charAt(0)));
		builder.append(word.substring(1));
		return builder.toString();
	}
	
	static String lower(String word)
	{
		var builder = new StringBuilder();
		builder.append(Character.toLowerCase(word.charAt(0)));
		builder.append(word.substring(1));
		return builder.toString();
	}
	
	static void append(String format, String... contents)
	{
		var content = format(format, contents);
		if (content.equals("}"))
		{
			level--;
		}
		builder.append(content);
		if (content.equals("{"))
		{
			level++;
		}
	}
	
	static void line(String format, String...contents)
	{
		var content = format(format, contents);
		if (content.equals("}"))
		{
			level--;
		}
		appendIndentation();
		builder.append(content + System.lineSeparator());
		if (content.equals("{"))
		{
			level++;
		}
	}
	
	static void line()
	{
		builder.append(System.lineSeparator());
	}
	
	static void appendIndentation()
	{
		for (var i = 0; i < level; i++)
		{
			builder.append("\t");
		}
	}
	
	static String name(EClassifier classifier)
	{
		var original = classifier.getName();
		if (original.equals("EString"))
		{
			return "String";
		}
		return original;
	}
}
