package games.minim

import org.eclipse.xtext.linking.ILinkingService
import org.eclipse.xtext.linking.ILinker
import games.minim.scoping.TypeInference
import games.minim.scoping.StandardLibrary

class MRuntimeModule extends AbstractMRuntimeModule 
{
	override Class<? extends ILinkingService> bindILinkingService() 
	{
		return TypeInference;
	}
	
	override Class<? extends ILinker> bindILinker()
	{
		return StandardLibrary
	}
}
