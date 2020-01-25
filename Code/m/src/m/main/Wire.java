package m.main;

import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.StandardLanguage;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;

import m.formatting.FormatterFragment;
import m.parsing.ContextualParserMessagesFragment;

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
		
		generator.addLanguage(language("m.Modular","modular"));
		generator.addLanguage(language("m.M","m"));
		generator.addLanguage(language("m.CSharp","cs"));
		generator.addLanguage(language("m.Javascript","js"));
		generator.addLanguage(language("m.XML","xml"));
		generator.addLanguage(language("m.JSON","json"));
		generator.addLanguage(language("m.YAML","yml"));
		generator.addLanguage(language("m.Text", "text"));
		generator.addLanguage(language("m.Testua", "testua"));
		
		generator.invoke(null);
	}
	
	static StandardLanguage language(String name, String extensions)
	{
		var language = new StandardLanguage();
		language.setName(name);
		language.setFileExtensions(extensions);
		language.addFragment(new FormatterFragment());
		//language.addFragment(new ContextualParserMessagesFragment());
		return language;
	}
}
