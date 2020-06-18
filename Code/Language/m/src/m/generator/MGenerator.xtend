package m.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import m.validation.MValidator
import java.util.HashMap
import m.m.Function
import m.validation.Type

class MGenerator extends AbstractGenerator
{
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context)
	{
		var game = MValidator.game
		if (game !== null)
		{
			new Unity().generate(game,fsa)
			new Godot().generate(game,fsa)
		}
	}
}

class Game
{
	public var functions = new HashMap<Function,Type>
	public var components = new HashMap<String,Type>
}
