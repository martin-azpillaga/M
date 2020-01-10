package m;

import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.StandardLanguage;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;

public class Generate 
{
	public static void main(String[] args)
	{
		var generator = new XtextGenerator();

		var configuration = new DefaultGeneratorModule();
		var project = new StandardProjectConfig();
		var eclipsePlugin = new BundleProjectConfig();
		var code = new CodeConfig();
		
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
		
		generator.invoke(null);
	}
	
	static StandardLanguage language(String name, String extensions)
	{
		var language = new StandardLanguage();
		language.setName(name);
		language.setFileExtensions(extensions);
		return language;
	}
}
