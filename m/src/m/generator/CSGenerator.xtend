package m.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import m.cs.File
import com.google.inject.Guice
import m.CSRuntimeModule
import org.eclipse.xtext.serializer.impl.Serializer
import org.eclipse.xtext.resource.SaveOptions
import m.TextRuntimeModule

class CSGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		println("CS")
		var file = resource.contents.head as File
		if (file !== null)
		{
			var textGenerator = new TextGenerator
		}
	}
}
