package m.fragments;

import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

@SuppressWarnings("restriction")
public class ContextualParserMessagesFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		bindingFactory.addTypeToType(TypeReference.typeRef(ISyntaxErrorMessageProvider.class), TypeReference.typeRef(ContextualParserMessages.class)).contributeTo(getLanguage().getRuntimeGenModule());
	}
}
