package m;

import java.util.Set;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class OutputFolder extends OutputConfigurationProvider {
  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    final OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
    defaultOutput.setDescription("Default Output - Overwrite");
    defaultOutput.setOutputDirectory(".");
    defaultOutput.setOverrideExistingResources(true);
    defaultOutput.setCreateOutputDirectory(true);
    defaultOutput.setCleanUpDerivedResources(true);
    defaultOutput.setSetDerivedProperty(true);
    defaultOutput.setUseOutputPerSourceFolder(true);
    return CollectionLiterals.<OutputConfiguration>newHashSet(defaultOutput);
  }
}
