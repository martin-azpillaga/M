/**
 * generated by Xtext
 */
package m.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import m.CSRuntimeModule;
import m.CSStandaloneSetup;
import m.ide.CSIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class CSIdeSetup extends CSStandaloneSetup {
  @Override
  public Injector createInjector() {
    CSRuntimeModule _cSRuntimeModule = new CSRuntimeModule();
    CSIdeModule _cSIdeModule = new CSIdeModule();
    return Guice.createInjector(Modules2.mixin(_cSRuntimeModule, _cSIdeModule));
  }
}
