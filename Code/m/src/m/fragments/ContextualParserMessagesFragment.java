package m.fragments;

import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import m.parser.ContextualParserMessages;

@SuppressWarnings("restriction")
public class ContextualParserMessagesFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate() 
	{
		System.out.println("Generating the parser messages...");
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		bindingFactory.addTypeToType(TypeReference.typeRef(ISyntaxErrorMessageProvider.class), TypeReference.typeRef(ContextualParserMessages.class)).contributeTo(getLanguage().getRuntimeGenModule());
	}
}

class Concat extends StringConcatenationClient
{
	@Override
	protected void appendTo(StringConcatenationClient.TargetStringConcatenation builder)
	{
		builder.append("GenericParserErrorMessages");
		builder.append(".class");
	}
}
