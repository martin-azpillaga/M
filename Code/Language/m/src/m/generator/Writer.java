 
package m.generator;

import java.util.Collection;
import java.util.stream.Collectors;

public class Writer 
{
	public static <T> String all(Collection<T> set, java.util.function.Function<T,String> f, String separator)
	{
		return String.join(separator, set.stream().map(f).collect(Collectors.toList()));
	}
	
	public static String lines(String indentation, String... lines)
	{
		return String.join("\n"+indentation, lines);
	}
}