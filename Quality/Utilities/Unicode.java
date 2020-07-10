package m.build;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Unicode
{
	public static void main(String[] arguments) throws IOException, InterruptedException
	{
		var result = "";
		
		var categories = new HashMap<String, ArrayList<String>>();
		
		if (!Files.exists(Paths.get("DerivedGeneralCategory.txt")))
		{
			Runtime.getRuntime().exec("wget https://www.unicode.org/Public/UCD/latest/ucd/extracted/DerivedGeneralCategory.txt").waitFor();
		}
		
		var lines = Files.readAllLines(Paths.get("DerivedGeneralCategory.txt"));
		for (var line : lines)
		{
			if (line.length() > 1 && !line.startsWith("#"))
			{
				var range = line.split(";")[0].replace(" ", "");
				var category = line.split(";")[1].split("#")[0].replace(" ", "");
				
				if (!categories.containsKey(category))
				{
					categories.put(category, new ArrayList<String>());
				}
				
				var rangeSplit = range.split("\\.\\.");
				if (rangeSplit.length == 0)
				{
					System.out.println(range);
					System.out.println(rangeSplit);
				}
				if (rangeSplit.length == 1)
				{
					if (range.length() == 4)
					{
						categories.get(category).add("'\\u"+range+"'");
					}
				}
				else
				{
					if (range.length() == 10)
					{
						categories.get(category).add("'\\u"+rangeSplit[0]+"'..'\\u"+rangeSplit[1]+"'");
					}
				}
			}
		}
		
		for (var category : categories.keySet())
		{
			result += "terminal fragment " + category.toUpperCase() + ": ";
			result += String.join("|", categories.get(category));
			result += ";\n";
		}
		
		var m = Files.readAllLines(Paths.get("../M.xtext"));
		if (!m.get(m.size()-2).startsWith("terminal fragment"))
		{
			result = String.join("\n", m) + "\n" + result;
			Files.write(Paths.get("../M.xtext"), result.getBytes());
		}
	}
}
