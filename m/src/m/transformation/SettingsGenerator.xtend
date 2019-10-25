package m.transformation

import m.yaml.YamlFactory
import m.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import m.YAMLRuntimeModule
import static m.transformation.GenericSerializer.*
import m.yaml.Version
import m.yaml.Tag

class SettingsGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	var yamlRuntime = new YAMLRuntimeModule
	
	def generate(Game game, IFileSystemAccess2 fsa)
	{
		twoDPhysics(fsa)
		threeDPhysics(fsa)
	}
	
	def twoDPhysics(IFileSystemAccess2 fsa)
	{
		var twoD = createFile
		twoD.version = Version.ONE_ONE
		twoD.tags.add(Tag.UNITY)
		
		var document = createDocument
		document.tag = "19"
		document.id = "1"
		
		var map = createMap
		map.key = 'Physics2DSettings'
		map.body.add('serializedVersion', 4)
		map.body.add('m_Gravity', #{'x'->0f, 'y'->0f})
		
		document.value = map
		twoD.nodes.add(document)
		
		generate(twoD, yamlRuntime, fsa, 'Pure Unity/ProjectSettings/Physics2DSettings.asset')
	}
	
	def threeDPhysics(IFileSystemAccess2 fsa)
	{
		var file = createFile
		file.version = Version.ONE_ONE
		file.tags.add(Tag.UNITY)
		
		var document = createDocument
		document.tag = "55"
		document.id = "1"
		
		var map = createMap
		map.key = 'PhysicsManager'
		map.body.add('serializedVersion', 10)
		map.body.addLongMap('m_Gravity', #{'x'->0l, 'y'->0l, 'z'->0l})
		
		document.value = map
		file.nodes.add(document)
		
		generate(file, yamlRuntime, fsa, 'Pure Unity/ProjectSettings/DynamicsManager.asset')
	}
}