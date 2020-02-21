package m.formatting;

import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class FormatterFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		
		var grammar = getGrammar().getName();
		if (grammar.equals("m.JSON"))
		{
			bindingFactory.addTypeToType(TypeReference.typeRef(IFormatter2.class), TypeReference.typeRef(JSONFormat.class)).contributeTo(getLanguage().getRuntimeGenModule());
		}
		else if (grammar.equals("m.CSharp"))
		{
			bindingFactory.addTypeToType(TypeReference.typeRef(IFormatter2.class), TypeReference.typeRef(CSharpFormat.class)).contributeTo(getLanguage().getRuntimeGenModule());
		}
		else if (grammar.equals("m.YAML"))
		{
			bindingFactory.addTypeToType(TypeReference.typeRef(IFormatter2.class), TypeReference.typeRef(YAMLFormat.class)).contributeTo(getLanguage().getRuntimeGenModule());
		}
	}
}