package games.minim.ide

import com.google.inject.Guice
import games.minim.MRuntimeModule
import games.minim.MStandaloneSetup
import org.eclipse.xtext.util.Modules2

class MIdeSetup extends MStandaloneSetup 
{
	override createInjector() 
	{
		Guice.createInjector(Modules2.mixin(new MRuntimeModule, new MIdeModule))
	}
}
