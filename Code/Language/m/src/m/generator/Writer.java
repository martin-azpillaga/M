 
package m.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import static m.generator.Writer.Keyword.*;

public class Writer 
{
	enum Keyword
	{
		YES, NO, END;
	}

	public static final Keyword end = Keyword.END;

	private static String indent = "{";
	private static String dedent = "}";
	private static String indentationString = "\t";

	private static int indentation;
	private static StringBuilder builder;
	private static boolean skipping;

	public static <T> List<Object> foreach(Collection<T> collection, Function<T,Object> function)
	{
		var list = new ArrayList<Object>();
		
		if (collection == null) return list;

		for (var element : collection)
		{
			list.add(function.apply(element));
		}
		return list;
	}
	
	public static <T> String foreach(Collection<T> set, java.util.function.Function<T,String> f, String separator)
	{
		if (set == null) return "";
		
		return String.join(separator, set.stream().map(f).collect(Collectors.toList()));
	}

	public static Keyword iff(boolean condition)
	{
		return condition ? YES : NO;
	}	

	public static List<Object> lines(Object... lines)
	{
		return Arrays.asList(lines);
	}

	public static void process(Object... lines)
	{
		for (var line : lines)
		{
			if (line instanceof String)
			{
				writeLine(line);
			}
			else if (line instanceof List<?>)
			{
				var list = (List<?>) line;
				for (var element : list)
				{
					process(element);
				}
			}
			else if (line instanceof Keyword)
			{
				if (line == NO)
				{
					skipping = true;
				}
				else if (line == END)
				{
					skipping = false;
				}
			}
			else if (line != null)
			{
				writeLine(line.toString());
			}
		}
	}

	public static String write(Object... lines)
	{
		builder = new StringBuilder();
		process(lines);
		return builder.toString();
	}

	private static void writeLine(Object line)
	{
		if (skipping)
		{
			return;
		}
		else if (line instanceof String && ((String)line).startsWith(dedent))
		{
			indentation--;
		}
		
		indentation();
		builder.append(line);
		builder.append("\n");
		if (line.equals(indent))
		{
			indentation++;
		}
	}

	private static void indentation()
	{
		for (var i = 0; i < indentation; i++)
		{
			builder.append(indentationString);
		}
	}
}