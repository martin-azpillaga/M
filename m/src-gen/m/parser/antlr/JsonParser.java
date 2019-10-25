/*
 * generated by Xtext
 */
package m.parser.antlr;

import com.google.inject.Inject;
import m.parser.antlr.internal.InternalJsonParser;
import m.services.JsonGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class JsonParser extends AbstractAntlrParser {

	@Inject
	private JsonGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WHITE_SPACE");
	}
	

	@Override
	protected InternalJsonParser createParser(XtextTokenStream stream) {
		return new InternalJsonParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Object";
	}

	public JsonGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(JsonGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
