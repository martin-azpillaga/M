/**
 * generated by Xtext 2.18.0
 */
package m;

import m.JsonStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class JsonStandaloneSetup extends JsonStandaloneSetupGenerated {
  public static void doSetup() {
    new JsonStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
