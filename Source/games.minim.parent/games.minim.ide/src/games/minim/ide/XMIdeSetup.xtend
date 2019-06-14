/*
 * generated by Xtext
 */
package games.minim.ide

import com.google.inject.Guice
import games.minim.XMRuntimeModule
import games.minim.XMStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class XMIdeSetup extends XMStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new XMRuntimeModule, new XMIdeModule))
	}
	
}
