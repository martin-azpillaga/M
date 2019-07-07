package m

import org.eclipse.xtext.linking.ILinkingService
import m.scoping.TypeInference
import org.eclipse.xtext.linking.ILinker
import m.scoping.StandardLibrary
import org.eclipse.xtext.generator.IGenerator2
import m.generator.MGenerator

class HybridUnityRuntimeModule extends AbstractHybridUnityRuntimeModule 
{
	override Class<? extends ILinkingService> bindILinkingService() 
	{
		return TypeInference;
	}
	
	override Class<? extends ILinker> bindILinker()
	{
		return StandardLibrary
	}
	
	def Class<? extends IGenerator2> bindIGenerator2()
	{
		return MGenerator
	}
}
