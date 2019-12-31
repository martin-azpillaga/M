import org.eclipse.emf.mwe2.runtime.workflow.Workflow;
import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;

public class Main
{
    public static void main(String[] args) 
    {
        var generator = new XtextGenerator();
        var configuration = new DefaultGeneratorModule();
        generator.setConfiguration(configuration);

        var code = new CodeConfig();
        var project = new StandardProjectConfig();
        configuration.setProject(project);
        project.setCodeConfig(code);

        project.setBaseName("Code");
        project.setRootPath("..");
        var ide = new BundleProjectConfig();
        ide.setEnabled(true);
        ide.setName("Code");
        project.setGenericIde(ide);

        code.setEncoding("UTF-8");
        code.setLineDelimiter("\n");
        System.out.println("Hello World");
        generator.invoke(null);
    }
}