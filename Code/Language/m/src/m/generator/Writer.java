 
package m.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Writer 
{
	private static int indentation;
	private static StringBuilder builder;
	private static void indentation()
	{
		for (var i = 0; i < indentation; i++)
		{
			builder.append("\t");
		}
	}
	private static boolean skipping;

	public static <T> List<Object> foreach(Collection<T> collection, Function<T,Object> function)
	{
		var list = new ArrayList<Object>();
		
		for (var element : collection)
		{
			list.add(function.apply(element));
		}
		return list;
	}

	public static String iff(boolean condition)
	{
		return condition ? yes : no;
	}
	private static final String yes = "##true##"; 
	private static final String no = "##no##";
	public static final String otherwise = "##else##";
	public static final String end = "##end##";
	public static final String indent = "{";
	public static final String dedent = "}";
	public static <T> String foreach(Collection<T> set, java.util.function.Function<T,String> f, String separator)
	{
		return String.join(separator, set.stream().map(f).collect(Collectors.toList()));
	}

	public static List<Object> lines(Object... lines)
	{
		var list = new ArrayList<Object>();

		for (var line : lines)
		{
			list.add(line);
		}

		return list;
	}

	public static String write(Object... lines)
	{
		builder = new StringBuilder();
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
					writeLine(element);
				}
			}
			else
			{
				writeLine(line.toString());
			}
		}
		return builder.toString();
	}

	private static void writeLine(Object line)
	{
		if (line == null)
		{
			return;
		}
		else if (line.equals(end))
		{
			skipping = false;
			return;
		}
		else if (skipping)
		{
			return;
		}
		else if (line.equals(yes))
		{
			return;
		}
		else if (line.equals(no))
		{
			skipping = true;
			return;
		}
		else if (line.equals(dedent))
		{
			indentation--;
		}
		
		if (line instanceof String)
		{
			indentation();
			builder.append(line);
			builder.append("\n");
			if (line.equals(indent))
			{
				indentation++;
			}
		}
		else if (line instanceof List<?>)
		{
			var list = (List<?>) line;
			for (var element : list)
			{
				writeLine(element);
			}
		}
	}
}