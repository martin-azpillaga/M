package m.generator;

import com.google.inject.Inject
import com.google.inject.Provider
import java.io.ByteArrayInputStream
import java.io.IOException
import m.TextStandaloneSetup
import m.m.MPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.eclipse.xtext.validation.Issue

class Main 
{
	def static void main(String[] args) 
	{
		if (args.length == 0) 
		{
			System.err.println("Aborting: no path to EMF resource provided!");
			return;
		}
		var injector = new TextStandaloneSetup().createInjectorAndDoEMFRegistration();
		var main = injector.getInstance(Main);
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.minim.games/M")) 
		{
			EPackage.Registry.INSTANCE.put("http://www.minim.games/M", MPackage.eINSTANCE);
		}
		main.runGenerator(args.get(0));
	}

	@Inject
	Provider<ResourceSet> resourceSetProvider;

	@Inject
	IResourceValidator validator;

	def protected void runGenerator(String string) 
	{
		var set = resourceSetProvider.get();
		var resource = set.createResource(URI.createURI("dummy:/example.text"));
		var in = new ByteArrayInputStream(string.getBytes());
		try 
		{
			resource.load(in, set.getLoadOptions());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		var list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		if (!list.isEmpty()) 
		{
			for (Issue issue : list) 
			{
				System.err.println(issue);
			}
			return;
		}
		System.out.println("Code generation finished.");
	}
}
