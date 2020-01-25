package m.formatting;

import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import m.formatting2.MFormatter;

public class FormatterFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		if (getGrammar().getName().equals("m.JSON"))
		{
			bindingFactory.addTypeToType(TypeReference.typeRef(IFormatter2.class), TypeReference.typeRef(JSONFormat.class)).contributeTo(getLanguage().getRuntimeGenModule());
		}
	}
}