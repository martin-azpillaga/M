package m;

import com.google.inject.Binder;
import m.AbstractYAMLRuntimeModule;
import m.MyFormatterPreferences;
import m.OutputFolder;
import m.converter.MyTerminalConverter;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.formatting2.FormatterPreferences;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.preferences.IPreferenceValuesProvider;

@SuppressWarnings("all")
public class YAMLRuntimeModule extends AbstractYAMLRuntimeModule {
  public Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return OutputFolder.class;
  }
  
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return MyTerminalConverter.class;
  }
  
  @Override
  public void configureFormatterPreferences(final Binder binder) {
    binder.<IPreferenceValuesProvider>bind(IPreferenceValuesProvider.class).annotatedWith(FormatterPreferences.class).to(MyFormatterPreferences.class);
  }
}
