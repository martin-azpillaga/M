package m.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import m.cBlocks.File
import m.transformation.BlocksToText

class BlocksGenerator extends AbstractGenerator 
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		if (resource.contents.head instanceof File)
		{
			var blocksToText = new BlocksToText
			blocksToText.generate(resource.contents.head as File, fsa, 'Text/')
		}
	}
}
