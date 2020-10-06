package m.main;

import org.eclipse.emf.mwe2.ecore.EcoreGenerator;
import org.eclipse.emf.mwe2.runtime.workflow.Workflow;
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

public class Wire 
{
	public static void main(String[] args)
	{
		var rootPath = "..";
		if (args.length > 0)
		{
			rootPath = args[0];
		}

		var generator = new XtextGenerator();

		var configuration = new DefaultGeneratorModule();
		var project = new StandardProjectConfig();
		var eclipsePlugin = new BundleProjectConfig();
		var ide = new BundleProjectConfig();
		ide.setEnabled(true);
		var code = new CodeConfig();
		
		project.setGenericIde(ide);
		project.setEclipsePlugin(eclipsePlugin);
		project.setCreateEclipseMetaData(true);
		project.setBaseName("m");
		project.setRootPath(rootPath);
		eclipsePlugin.setEnabled(true);
		
		code.setPreferXtendStubs(false);
		code.setEncoding("UTF-8");
		
		configuration.setProject(project);
		configuration.setCode(code);
		generator.setConfiguration(configuration);
		
		generator.addLanguage(fullLanguage("m.M","m"));
		generator.addLanguage(simpleLanguage("m.CSharp","cs"));
		generator.addLanguage(simpleLanguage("m.XML","xml"));
		generator.addLanguage(simpleLanguage("m.JSON","json,project"));
		generator.addLanguage(simpleLanguage("m.YAML","yml"));
		
		generator.invoke(null);
		
		var ecore = new EcoreGenerator();
		ecore.setGenModel("platform:/resource/m/model/Game.genmodel");
		ecore.invoke(null);
	}
	
	static StandardLanguage fullLanguage(String name, String extensions)
	{
		var language = new StandardLanguage();
		language.addReferencedResource("platform:/resource/m/model/Game.genmodel");
		language.setName(name);
		language.setFileExtensions(extensions);
		language.addFragment(new FormatterFragment());
		language.addFragment(new ContextualParserMessagesFragment());
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
		language.addFragment(new FormatterFragment());
		return language;
	}
}

class ContextualParserMessagesFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		bindingFactory.addTypeToType(TypeReference.typeRef(ISyntaxErrorMessageProvider.class), TypeReference.typeRef("m.validation.ContextualParserMessages")).contributeTo(getLanguage().getRuntimeGenModule());
	}
}

class FormatterFragment extends AbstractXtextGeneratorFragment
{
	@Override
	public void generate()
	{
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		
		var grammarPath = getGrammar().getName();
		var grammar = grammarPath.substring(grammarPath.lastIndexOf('.')+1);
		
		bindingFactory.addTypeToType(TypeReference.typeRef(IFormatter2.class), TypeReference.typeRef("m.formatting."+grammar+"Format")).contributeTo(getLanguage().getRuntimeGenModule());
	}
}

