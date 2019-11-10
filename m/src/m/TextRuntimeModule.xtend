package m

import org.eclipse.xtext.generator.OutputConfigurationProvider
import org.eclipse.xtext.generator.OutputConfiguration
import java.util.Set
import org.eclipse.xtext.generator.IFileSystemAccess
import m.converter.MyTerminalConverter

class TextRuntimeModule extends AbstractTextRuntimeModule 
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

class OutputFolder extends OutputConfigurationProvider
{
	override Set<OutputConfiguration> getOutputConfigurations() 
	{
		val OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Default Output - Overwrite");
		defaultOutput.setOutputDirectory(".");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);
		defaultOutput.useOutputPerSourceFolder = true;

		return newHashSet(defaultOutput);
	}
}