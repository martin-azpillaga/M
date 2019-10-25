/**
 * generated by Xtext
 */
package m.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import m.JsonRuntimeModule;
import m.JsonStandaloneSetup;
import m.ide.JsonIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class JsonIdeSetup extends JsonStandaloneSetup {
  @Override
  public Injector createInjector() {
    JsonRuntimeModule _jsonRuntimeModule = new JsonRuntimeModule();
    JsonIdeModule _jsonIdeModule = new JsonIdeModule();
    return Guice.createInjector(Modules2.mixin(_jsonRuntimeModule, _jsonIdeModule));
  }
}
