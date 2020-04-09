package m.main;

import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.StandardLanguage;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.XtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.ecore.EMFGeneratorFragment2;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessFragment2;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;
import static org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess.*;
import static org.eclipse.xtext.xtext.generator.model.TypeReference.*;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;

public class Wire 
{
	public static void main(String[] arguments)
	{
		var rootPath = arguments.length > 0 ? arguments[0] : "..";

		var generator = new XtextGenerator();

		var configuration = new DefaultGeneratorModule();
		var project = new StandardProjectConfig();
		var code = new CodeConfig();
		
		
		var eclipsePlugin = new BundleProjectConfig();
		eclipsePlugin.setEnabled(true);
		project.setEclipsePlugin(eclipsePlugin);
		project.setCreateEclipseMetaData(true);
		
		project.setBaseName("m");
		project.setRootPath(rootPath);
		code.setEncoding("UTF-8");
		
		configuration.setProject(project);
		configuration.setCode(code);
		generator.setConfiguration(configuration);
		
		generator.addLanguage(fullLanguage("m.M","m"));
		
		generator.invoke(null);
	}
	
	static StandardLanguage fullLanguage(String name, String extensions)
	{
		var language = new StandardLanguage();
		language.setName(name);
		language.setFileExtensions(extensions);
		language.addFragment(new ContextualParserMessagesFragment());
		language.addReferencedResource("platform:/resource/m/src/m/Game.xcore");
		return language;
	}
	
	static XtextGeneratorLanguage simpleLanguage(String name, String extensions)
	{
		var language = new XtextGeneratorLanguage();
		language.setName(name);
		language.setFileExtensions(extensions);
		language.addFragment(new EMFGeneratorFragment2());
		language.addFragment(new GrammarAccessFragment2());
		language.addFragment(new XtextAntlrGeneratorFragment2());
		language.addFragment(new SerializerFragment2());
		return language;
	}
}

class ContextualParserMessagesFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		new BindingFactory()
		.addTypeToType(typeRef(ISyntaxErrorMessageProvider.class), typeRef("m.validation.ContextualParserMessages"))
		.contributeTo(getLanguage().getRuntimeGenModule());
	}
}

class FormatterFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		var grammarPath = getGrammar().getName();
		var grammar = grammarPath.substring(grammarPath.lastIndexOf('.')+1);
		new BindingFactory()
		.addTypeToType(typeRef(IFormatter2.class), typeRef("m.formatting."+grammar+"Format"))
		.contributeTo(getLanguage().getRuntimeGenModule());
	}
}

