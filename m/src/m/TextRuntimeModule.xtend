package m

import org.eclipse.xtext.generator.OutputConfigurationProvider
import org.eclipse.xtext.generator.OutputConfiguration
import java.util.Set
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.parser.antlr.ISyntaxErrorMessageProvider
import m.errorHandling.TextSyntaxErrorMessageProvider

class TextRuntimeModule extends AbstractTextRuntimeModule
{
	def Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider()
	{
		OutputFolder
	}

	def Class<? extends ISyntaxErrorMessageProvider> bindISyntaxErrorMessageProvider()
	{
		return TextSyntaxErrorMessageProvider;
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
