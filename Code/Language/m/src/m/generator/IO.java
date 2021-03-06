
package m.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static m.generator.IO.Keyword.YES;
import static m.generator.IO.Keyword.NO;
import static m.generator.IO.Keyword.END;

public class IO {
	enum Keyword {
		YES, NO, END;
	}

	public static final Keyword end = Keyword.END;

	private static final String INDENT = "{";
	private static final String DEDENT = "}";
	private static final String INDENTATION_STRING = "\t";

	private static int indentation;
	private static StringBuilder builder;
	private static boolean skipping;
	private static String baseFolder;

	private IO() {

	}

	public static boolean contains(String folder, String file)
	{
		return file.startsWith(folder);
	}

	public static String read(String uri)
	{
		try
		{
			var url = new URI(uri).toURL();
			var inputStream = url.openStream();
			Scanner s = new Scanner(inputStream);
			s.useDelimiter("\\A");
			String result = s.hasNext() ? s.next() : "";
			s.close();
			return result;
		} catch (Exception e) {}

		return "";
	}

	public static List<String> filesWithExtension(String extension, String folder)
	{
		try
		{
			var path = Paths.get(new URI(folder));
			return Files.walk(path)
				.filter(p->p.toString().endsWith(extension))
				.map(p->p.toUri().toString())
				.collect(Collectors.toList());
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}

	public static String concat(String a, String b)
	{
		try
		{
			return new URI(Paths.get(a,b).toString()).toString();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static boolean existsFile(String file)
	{
		try
		{
			return Files.exists(Paths.get(new URI(file)));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static String readPath(Path file)
	{
		try
		{
			return new String(Files.readAllBytes(file));
		}
		catch(IOException e)
		{
			return "";
		}
	}

	public static String getBaseFolder()
	{
		return baseFolder;
	}

	public static void setBaseFolder(String folder)
	{
		try
		{
			baseFolder = Paths.get(new URI(folder)).toString();
		}
		catch (Exception e)
		{
			baseFolder = folder;
		}
	}

	public static boolean exists(String file)
	{
		return Files.exists(Paths.get(baseFolder,file));
	}

	public static String readText(String file)
	{
		try
		{
			return new String(Files.readAllBytes(Paths.get(baseFolder, file)));
		}
		catch (IOException e)
		{
			return "";
		}
	}

	public static <T> List<Object> foreach(Collection<T> collection, Function<T, Object> function) {
		var list = new ArrayList<Object>();

		if (collection == null)
			return list;

		for (var element : collection) {
			list.add(function.apply(element));
		}
		return list;
	}

	public static <T> String foreach(Collection<T> set, java.util.function.Function<T, String> f, String separator) {
		if (set == null)
			return "";

		return String.join(separator, set.stream().map(f).collect(Collectors.toList()));
	}

	public static Keyword iff(boolean condition) {
		return condition ? YES : NO;
	}

	public static List<Object> lines(Object... lines) {
		return Arrays.asList(lines);
	}

	public static void process(Object... lines) {
		for (var line : lines) {
			if (line instanceof String) {
				writeLine(line);
			} else if (line instanceof List<?>) {
				var list = (List<?>) line;
				for (var element : list) {
					process(element);
				}
			} else if (line instanceof Keyword) {
				if (line == NO) {
					skipping = true;
				} else if (line == END) {
					skipping = false;
				}
			} else if (line != null) {
				writeLine(line.toString());
			}
		}
	}

	public static String write(Object... lines) {
		builder = new StringBuilder();
		process(lines);
		return builder.toString();
	}

	public static void writeFile(String file, Object... lines)
	{
		try
		{
			var text = write(lines(lines)).getBytes();
			var absolutePath = Paths.get(baseFolder,file);
			Files.createDirectories(absolutePath.getParent());
			Files.write(absolutePath, text);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void writeLine(Object line)
	{
		if (skipping)
		{
			return;
		}
		else if (line instanceof String && ((String)line).startsWith(DEDENT))
		{
			indentation--;
		}

		indentation();
		builder.append(line);
		builder.append("\n");
		if (line.equals(INDENT))
		{
			indentation++;
		}
	}

	private static void indentation()
	{
		for (var i = 0; i < indentation; i++)
		{
			builder.append(INDENTATION_STRING);
		}
	}
}
