package m

import org.eclipse.xtext.generator.OutputConfigurationProvider

class BlocksRuntimeModule extends AbstractBlocksRuntimeModule 
{
	def Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider()
	{
		OutputFolder
	}
}
