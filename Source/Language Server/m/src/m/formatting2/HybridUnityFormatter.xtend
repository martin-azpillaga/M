/*
 * generated by Xtext
 */
package m.formatting2

import com.google.inject.Inject
import m.m.Game
import m.services.HybridUnityGrammarAccess
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class HybridUnityFormatter extends AbstractFormatter2 {
	
	@Inject extension HybridUnityGrammarAccess

	def dispatch void format(Game game, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (name : game.real1Components) {
			name.format
		}
	}
	
	// TODO: implement for 
}
