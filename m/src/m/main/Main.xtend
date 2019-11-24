
package m.main

import com.google.inject.Inject
import com.google.inject.Provider
import m.TextStandaloneSetup
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.eclipse.emf.ecore.EPackage
import m.m.MPackage
import m.expressions.ExpressionsPackage
import m.modular.ModularPackage

class Main 
{
	def static main(String[] args) 
	{
		val injector = new TextStandaloneSetup().createInjectorAndDoEMFRegistration
		val main = injector.getInstance(Main)
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.minim.games/expressions")) 
		{
			EPackage.Registry.INSTANCE.put("http://www.minim.games/expressions", ExpressionsPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.minim.games/modular")) 
		{
			EPackage.Registry.INSTANCE.put("http://www.minim.games/modular", ModularPackage.eINSTANCE);
		}
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.minim.games/M")) 
		{
			EPackage.Registry.INSTANCE.put("http://www.minim.games/M", MPackage.eINSTANCE);
		}
		println ('Running')

		return;
		//main.runGenerator(args.get(0))
	}

	@Inject Provider<ResourceSet> resourceSetProvider

	@Inject IResourceValidator validator

	def protected runGenerator(String string) 
	{
		val set = resourceSetProvider.get
		val resource = set.getResource(URI.createFileURI(string), true)
		
		val issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl)
		if (!issues.empty) {
			issues.forEach[System.err.println(it)]
			return
		}
		else
		{
			System.out.println("Validation correct")
		}
	}
}
