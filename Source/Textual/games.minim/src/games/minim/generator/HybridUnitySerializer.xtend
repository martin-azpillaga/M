package games.minim.generator

import com.google.inject.Guice
import games.minim.HybridUnityRuntimeModule
import games.minim.gen.GenFactory
import games.minim.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.serializer.impl.Serializer

class HybridUnitySerializer implements Framework
{
  	
	override represent(Game game, IFileSystemAccess2 fileSystem) 
	{
		var injector = Guice.createInjector(new  HybridUnityRuntimeModule());  
		var serializer = injector.getInstance(Serializer);  
		
		for (component : game.real1Components)
		{
			var g = GenFactory.eINSTANCE.createFullName1
			g.name = component.name
			g.wrapper = component.name
			g.menu = component.name
			g.base = component.name
			var text = serializer.serialize(g);
			println(text)	
		}
	}
	
	def min(int a, int b, int c)
	{
		#[a,b,c].filter[it >= 0].reduce[p1, p2|Math.min(p1,p2)]
	}
}