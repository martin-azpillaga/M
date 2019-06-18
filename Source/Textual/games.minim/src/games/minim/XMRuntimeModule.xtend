package games.minim

import games.minim.scoping.StandardLibrary
import games.minim.scoping.TypeInference
import org.eclipse.xtext.generator.IGenerator2
import org.eclipse.xtext.linking.ILinker
import org.eclipse.xtext.linking.ILinkingService
import org.eclipse.xtext.scoping.IScopeProvider
import games.minim.generator.MGenerator
import games.minim.scoping.MScopeProvider

class XMRuntimeModule extends AbstractXMRuntimeModule 
{
	override Class<? extends ILinkingService> bindILinkingService() 
	{
		return TypeInference;
	}
	
	override Class<? extends ILinker> bindILinker()
	{
		return StandardLibrary
	}
	
	override Class<? extends IScopeProvider> bindIScopeProvider() 
	{
		return MScopeProvider;
	}
	
	def Class<? extends IGenerator2> bindIGenerator2()
	{
		return MGenerator
	}
}
