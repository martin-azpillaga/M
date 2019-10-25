package m

import m.converter.MyTerminalConverter

class CSRuntimeModule extends AbstractCSRuntimeModule 
{
	def bindIOutputConfigurationProvider()
	{
		OutputFolder
	}
	
	override bindIValueConverterService() 
	{
        return MyTerminalConverter
    }
}
