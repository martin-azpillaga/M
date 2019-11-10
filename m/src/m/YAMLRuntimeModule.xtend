package m

import m.converter.MyTerminalConverter
import org.eclipse.xtext.generator.OutputConfigurationProvider
import org.eclipse.xtext.formatting2.FormatterPreferenceValuesProvider
import com.google.inject.Inject
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider
import org.eclipse.xtext.preferences.IPreferenceValuesProvider
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.preferences.IPreferenceValues
import org.eclipse.xtext.preferences.PreferenceKey
import org.eclipse.xtext.formatting2.FormatterPreferenceKeys
import com.google.inject.Binder
import org.eclipse.xtext.formatting2.FormatterPreferences

class YAMLRuntimeModule extends AbstractYAMLRuntimeModule 
{
	def Class<? extends OutputConfigurationProvider> bindIOutputConfigurationProvider()
	{
		OutputFolder
	}
	
	override bindIValueConverterService() 
	{
        return MyTerminalConverter
    }
    
    override void configureFormatterPreferences(Binder binder) {
		binder.bind(IPreferenceValuesProvider).annotatedWith(FormatterPreferences).to(MyFormatterPreferences);
	}
}

class MyFormatterPreferences extends FormatterPreferenceValuesProvider
{
	@Inject 
	private IWhitespaceInformationProvider whitespaceInfo;
	@Inject
	private IPreferenceValuesProvider valuesProvider;

	override getPreferenceValues(Resource resource) 
	{
		val preferenceValues = internalGetRawPreferenceValues(resource);
		val indent = " "
		val lineSep = whitespaceInfo.getLineSeparatorInformation(resource.getURI()).getLineSeparator();

		return new IPreferenceValues() {

			override String getPreference(PreferenceKey key) {
				if (key == FormatterPreferenceKeys.indentation) {
					return indent;
				}
				if (key == FormatterPreferenceKeys.lineSeparator) {
					return lineSep;
				}
				return preferenceValues.getPreference(key);
			}

		};
	}
}