package m;

import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.StandardLanguage;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.formatting.Formatter2Fragment2;
import org.eclipse.xtext.xtext.generator.generator.GeneratorFragment2;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;

import m.fragments.ContextualParserMessages;

public class Generate 
{
	public static void main(String[] args)
	{
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
		project.setRootPath("..");
		eclipsePlugin.setEnabled(true);
		
		code.setPreferXtendStubs(false);
		
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
		language.addFragment(new ContextualParserMessages());
		return language;
	}
}
