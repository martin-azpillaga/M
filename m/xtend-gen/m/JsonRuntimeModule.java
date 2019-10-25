package m;

import m.AbstractJsonRuntimeModule;
import m.OutputFolder;
import m.converter.MyTerminalConverter;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.generator.OutputConfigurationProvider;

@SuppressWarnings("all")
public class JsonRuntimeModule extends AbstractJsonRuntimeModule {
  public Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return OutputFolder.class;
  }
  
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return MyTerminalConverter.class;
  }
}
