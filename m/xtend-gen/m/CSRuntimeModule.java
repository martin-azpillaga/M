package m;

import m.AbstractCSRuntimeModule;
import m.OutputFolder;
import m.converter.MyTerminalConverter;
import org.eclipse.xtext.conversion.IValueConverterService;

@SuppressWarnings("all")
public class CSRuntimeModule extends AbstractCSRuntimeModule {
  public Class<OutputFolder> bindIOutputConfigurationProvider() {
    return OutputFolder.class;
  }
  
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return MyTerminalConverter.class;
  }
}
