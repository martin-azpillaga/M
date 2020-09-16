/*
 * generated by Xtext 2.12.0-SNAPSHOT
 */
package io.typefox.xtext.langserver.example.ide

import com.google.inject.Guice
import io.typefox.xtext.langserver.example.MyDslRuntimeModule
import io.typefox.xtext.langserver.example.MyDslStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class MyDslIdeSetup extends MyDslStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new MyDslRuntimeModule, new MyDslIdeModule))
	}
	
}
