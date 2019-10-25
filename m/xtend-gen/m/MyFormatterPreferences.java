package m;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider;
import org.eclipse.xtext.formatting2.FormatterPreferenceKeys;
import org.eclipse.xtext.formatting2.FormatterPreferenceValuesProvider;
import org.eclipse.xtext.preferences.IPreferenceValues;
import org.eclipse.xtext.preferences.IPreferenceValuesProvider;
import org.eclipse.xtext.preferences.PreferenceKey;

@SuppressWarnings("all")
public class MyFormatterPreferences extends FormatterPreferenceValuesProvider {
  @Inject
  private IWhitespaceInformationProvider whitespaceInfo;
  
  @Inject
  private IPreferenceValuesProvider valuesProvider;
  
  @Override
  public IPreferenceValues getPreferenceValues(final Resource resource) {
    final IPreferenceValues preferenceValues = this.internalGetRawPreferenceValues(resource);
    final String indent = " ";
    final String lineSep = this.whitespaceInfo.getLineSeparatorInformation(resource.getURI()).getLineSeparator();
    return new IPreferenceValues() {
      @Override
      public String getPreference(final PreferenceKey key) {
        boolean _equals = Objects.equal(key, FormatterPreferenceKeys.indentation);
        if (_equals) {
          return indent;
        }
        boolean _equals_1 = Objects.equal(key, FormatterPreferenceKeys.lineSeparator);
        if (_equals_1) {
          return lineSep;
        }
        return preferenceValues.getPreference(key);
      }
    };
  }
}
