/*
 * generated by Xtext
 */
package games.minim


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class XMStandaloneSetup extends XMStandaloneSetupGenerated {

	def static void doSetup() {
		new XMStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
