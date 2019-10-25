package m;

import m.AbstractBlocksRuntimeModule;
import m.OutputFolder;
import org.eclipse.xtext.generator.OutputConfigurationProvider;

@SuppressWarnings("all")
public class BlocksRuntimeModule extends AbstractBlocksRuntimeModule {
  public Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return OutputFolder.class;
  }
}
