/**
 * generated by Xtext
 */
package m.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import m.TextRuntimeModule;
import m.TextStandaloneSetup;
import m.ide.TextIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class TextIdeSetup extends TextStandaloneSetup {
  @Override
  public Injector createInjector() {
    TextRuntimeModule _textRuntimeModule = new TextRuntimeModule();
    TextIdeModule _textIdeModule = new TextIdeModule();
    return Guice.createInjector(Modules2.mixin(_textRuntimeModule, _textIdeModule));
  }
}
