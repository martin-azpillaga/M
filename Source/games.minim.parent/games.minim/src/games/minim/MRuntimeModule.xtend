package games.minim

import org.eclipse.xtext.linking.ILinkingService
import org.eclipse.xtext.linking.ILinker
import games.minim.scoping.Linking
import games.minim.scoping.Cleaning

class MRuntimeModule extends AbstractMRuntimeModule 
{
	override Class<? extends ILinkingService> bindILinkingService() 
	{
		return Linking;
	}
	
	override Class<? extends ILinker> bindILinker()
	{
		return Cleaning
	}
}
