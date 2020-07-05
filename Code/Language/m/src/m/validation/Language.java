package m.validation;

import java.util.HashMap;
import java.util.Map;

public class Language
{
	Map<String,Symbol> symbols;
	
	public Language(Map<String,Symbol> symbols)
	{
		this.symbols = symbols;
	}
	
	public T type(String component)
	{
		return symbols.get(component).type;
	}
	
	public static final Language English = new Language(Map.of("a",Symbol.acceleration));
}
