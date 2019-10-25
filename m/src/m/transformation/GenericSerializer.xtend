package m.transformation

import org.eclipse.emf.ecore.EObject
import com.google.inject.Module
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.resource.XtextResource
import com.google.inject.Guice
import org.eclipse.xtext.resource.SaveOptions
import org.eclipse.xtext.serializer.impl.Serializer
import m.YAMLRuntimeModule
import m.BlocksRuntimeModule

class GenericSerializer 
{
	def static void generate(EObject o, Module module, IFileSystemAccess2 fsa, String path)
	{
		var resource = new XtextResource
		resource.contents.add(o)
		
		var injector = Guice.createInjector(module)
		
		var serializer = injector.getInstance(Serializer)
		var options = SaveOptions.newBuilder.format.options
		var serialized = serializer.serialize(o, options)
		
		if (module.class == YAMLRuntimeModule)
		{
			serialized = serialized.replace('\t','  ')
		}
		if (module.class == BlocksRuntimeModule)
		{
			serialized = serialized.replace('\t','  ')
		}

		fsa.generateFile(path, serialized)
	}
	
	def static void add(EObject o, Module module, IFileSystemAccess2 fsa, String path)
	{
		var resource = new XtextResource
		resource.contents.add(o)
		
		var injector = Guice.createInjector(module)
		
		var serializer = injector.getInstance(Serializer)
		var options = SaveOptions.newBuilder.format.options
		var serialized = serializer.serialize(o, options)

		fsa.generateFile(path, fsa.readTextFile(path)+'\n'+serialized)
	}
	
}