package m

import org.eclipse.xtext.generator.OutputConfigurationProvider
import m.converter.MyTerminalConverter

class JsonRuntimeModule extends AbstractJsonRuntimeModule 
{
	def Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider()
	{
		OutputFolder
	}
	
	override bindIValueConverterService() 
	{
        return MyTerminalConverter
    }
}
