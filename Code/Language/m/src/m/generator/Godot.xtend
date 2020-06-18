package m.generator

import org.eclipse.xtext.generator.IFileSystemAccess2

class Godot
{
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		fileSystem.generateFile('Godot/project.godot', '')
	}
}