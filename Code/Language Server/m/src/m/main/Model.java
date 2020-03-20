package m.main;

import static java.lang.String.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import m.csharp.CsharpPackage;

public class Model
{	
	static StringBuilder builder = new StringBuilder();
	static int level = 0;
	
	public static void main(String[] arguments) throws IOException
	{
		model(CsharpPackage.eINSTANCE);
	}
	
	private static void model(EPackage epackage) throws IOException
	{
		var packageName = epackage.getName();
		
		line("package m.main;");
		line();
		line("import m.%s.*;", packageName);
		line("import m.%s.impl.*;", packageName);
		line();
		line("public class %sModel", camel(packageName));
		line("{");
		line("static %sFactory factory = %sFactory.eINSTANCE;",camel(packageName),camel(packageName));
		line();
		for(var e : epackage.eContents())
		{
			if (e instanceof EClass)
			{
				var eclass = (EClass) e;
				var name = eclass.getName();

				line("public static %sBuilder %s()", name, name(null,lower(name)));
				line("{");
				line("return new %sModel().new %sBuilder();", camel(packageName), name);
				line("}");
				
				var attributes = eclass.getEAllStructuralFeatures();
				
				var requiredFeatures = new ArrayList<EStructuralFeature>();
				for (var attribute : attributes)
				{
					if (attribute.getUpperBound() == 1)
					{
						requiredFeatures.add(attribute);
					}
				}
				
				if (requiredFeatures.size() > 0)
				{
					appendIndentation();
					append("public static %sBuilder %s(", name, name(null,lower(name)));
					var first = true;
					
					for (var feature : requiredFeatures)
					{
						var ftype = typeName(feature.getEType());
						var originalName = feature.getName();
						var fname = name(ftype,originalName);
						
						append("%s%s %s", first?"":", ", ftype, fname);
						first = false;
					}
					
					append(")");
					line();
					line("{");
					line("return new %sModel().new %sBuilder();", camel(packageName), name);
					line("}");
				}				
			}
		}
		for (var e : epackage.eContents())
		{
			if (e instanceof EClass)
			{
				var eclass = (EClass) e;
				var className = eclass.getName();
				
				line("public class %sBuilder extends %sImpl", className, className);
				line("{");
				for (var feature : eclass.getEAllStructuralFeatures())
				{
					var originalName = feature.getName();
					var type = typeName(feature.getEType());
					var name = name(type, originalName);
					
					var isVariable = feature.getEType().getName().equals("Variable");
					
					if (feature.getUpperBound() == 1)
					{
						line("public %sBuilder %s(%s %s)", className, name, type, name);
					}
					else
					{
						line("public %sBuilder %s(%s... %s)", className, name, type, name);
					}
					
					line("{");
					if (feature.getUpperBound() == 1)
					{
						line("set%s(%s);", camel(originalName), isVariable? "GameModel.variable("+name+")" : name);
					}
					else
					{
						line("for (var each : %s)", name);
						line("{");
						line("get%s().add(each);", camel(name), name);
						line("}");
					}
					line("return this;");
					line("}");
				}
				line("}");
			}
		}
		line("}");
		
		Files.write(Paths.get("src/m/main/"+camel(packageName)+"Model.java"),builder.toString().getBytes());
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
	
	static void append(String format, Object... contents)
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
	
	static void line(String format, Object...contents)
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
	
	static String typeName(EClassifier classifier)
	{
		var original = classifier.getName();
		if (original.equals("EString"))
		{
			return "String";
		}
		else if (original.equals("Variable"))
		{
			return "String";
		}
		else if (original.equals("EBoolean"))
		{
			return "boolean";
		}
		return original;
	}
	
	static String name(String type, String original)
	{
		if (type == "boolean")
		{
			return "is"+camel(original);
		}
		var reservedWords = new String[] {"do", "class", "default", "while", "this", "finally", "return", "try", "catch", "switch", "case", "for", "break", "throw", "goto", "continue"};
		for (var reserved : reservedWords)
		{
			if (reserved.equals(original))
			{
				return "_"+original;
			}
		}
		return original;
	}
}
