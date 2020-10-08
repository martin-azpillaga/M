package m.model;

import java.io.StringReader;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.IParseResult;

import m.MStandaloneSetup;

public class Parser
{
	@Inject IParser parser;

	public Parser()
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		injector.injectMembers(this);
	}

	public IParseResult parse(String text)
	{
		return parser.parse(new StringReader(text));
	}
}
