package m.transformation

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.HashSet
import java.util.Random
import javax.imageio.ImageIO
import m.YAMLRuntimeModule
import m.m.Game
import m.yaml.YamlFactory
import org.eclipse.xtext.generator.IFileSystemAccess2

import static m.transformation.GenericSerializer.*
import java.io.DataOutputStream
import java.util.HashMap
import m.m.Word

class ArtGenerator 
{
	extension FactoryHelper helper = FactoryHelper.eINSTANCE
	
	extension YamlFactory yaml = YamlFactory.eINSTANCE
	
	def generate(Game game, HashMap<String, Type> types, IFileSystemAccess2 fsa, String... folders)
	{
		var meshComponents = types.keySet.filter[types.get(it) == Type.mesh].toSet
		
		var sprites = new HashSet<String>
		var audioClips = new HashSet<String>
		var meshes = new HashSet<String>
		var materials = new HashSet<String>
		var fonts = new HashSet<String>
		var texts = new HashSet<String>
		
		for (entity : game.entities)
		{
			for (component : entity.components)
			{
				if (meshComponents.contains(component.name))
				{
					meshes.add((component.value as Word).values.join(' '))
				}
			}
		}
		
		var random = new Random
		var yamlRuntime = new YAMLRuntimeModule
		
		for (mesh : meshes)
		{
			for (folder : folders)
			{
			fsa.generateFile(folder+mesh+'.obj',
			'''
			o «mesh»
			
			v -0.500000 -0.500000 0.500000
			v 0.500000 -0.500000 0.500000
			v -0.500000 0.500000 0.500000
			v 0.500000 0.500000 0.500000
			v -0.500000 0.500000 -0.500000
			v 0.500000 0.500000 -0.500000
			v -0.500000 -0.500000 -0.500000
			v 0.500000 -0.500000 -0.500000
			
			vt 0.000000 0.000000
			vt 1.000000 0.000000
			vt 0.000000 1.000000
			vt 1.000000 1.000000
			
			vn 0.000000 0.000000 1.000000
			vn 0.000000 1.000000 0.000000
			vn 0.000000 0.000000 -1.000000
			vn 0.000000 -1.000000 0.000000
			vn 1.000000 0.000000 0.000000
			vn -1.000000 0.000000 0.000000
			
			g «mesh»
			s 1
			f 1/1/1 2/2/1 3/3/1
			f 3/3/1 2/2/1 4/4/1
			s 2
			f 3/1/2 4/2/2 5/3/2
			f 5/3/2 4/2/2 6/4/2
			s 3
			f 5/4/3 6/3/3 7/2/3
			f 7/2/3 6/3/3 8/1/3
			s 4
			f 7/1/4 8/2/4 1/3/4
			f 1/3/4 8/2/4 2/4/4
			s 5
			f 2/1/5 8/2/5 4/3/5
			f 4/3/5 8/2/5 6/4/5
			s 6
			f 7/1/6 1/2/6 5/3/6
			f 5/3/6 1/2/6 3/4/6
			'''
			)
			
			var file = createFile
			file.nodes.add('fileFormatVersion',2)
			file.nodes.add('guid', (mesh+'mesh').uuid)
			
			generate(file, yamlRuntime, fsa, folder+mesh+'.obj.meta')
			}
		}
		
		for (sprite : sprites)
		{				
			var image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			image.setRGB(0,0,new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)).RGB)
			var o = new ByteArrayOutputStream
			ImageIO.write(image, 'png', o)
			
			var file = createFile
			file.nodes.add('fileFormatVersion',2)
			file.nodes.add('guid', sprite.uuid)
			
			var importer = yaml.createMap
			importer.key = "TextureImporter"
			
			importer.body.add('serializedVersion', 10)
			importer.body.add('spriteMode', 1)
			importer.body.add(kv('spritePixelsToUnits', 1.yaml))
			importer.body.add(kv('textureType', 8.yaml))
			
			file.nodes.add(importer)
			
			for (folder : folders)
			{
				fsa.generateFile(folder+sprite+'.png', new ByteArrayInputStream(o.toByteArray))		
				
				generate(file, yamlRuntime, fsa, folder+sprite+'.png.meta')
			}
		}
		
		for (audioClip : audioClips)
		{
			var hz = 44100;
			var bps = 16;
			var rescaleFactor = 0x7FFF;
			var header = 36;
			var channels = 1;
			var format = 1;
			
			var baos = new ByteArrayOutputStream
			var outputStream = new DataOutputStream(baos)
			
			var note = random.nextInt(200) + 300
			
			var samples = newIntArrayOfSize(hz);
	
			for (var i = 0; i < samples.size; i++)
			{
				samples.set(i, (Math.sin(Math.PI * 2 * i * note / hz) * rescaleFactor ) as int)
			}
	
			outputStream.writeBytes('RIFF')
			outputStream.writeInt(Integer.reverseBytes(header + samples.size * bps / 8))
			outputStream.writeBytes("WAVE")
			outputStream.writeBytes("fmt ")
			outputStream.writeInt(Integer.reverseBytes(16))
			outputStream.writeShort(Short.reverseBytes(format as short))
			outputStream.writeShort(Short.reverseBytes(channels as short))
			outputStream.writeInt(Integer.reverseBytes(hz))
			outputStream.writeInt(Integer.reverseBytes(hz * channels * bps / 8))
			outputStream.writeShort(Short.reverseBytes((channels * bps / 8) as short))
			outputStream.writeShort(Short.reverseBytes(bps as short))
			outputStream.writeBytes("data")
			outputStream.writeInt(Integer.reverseBytes(samples.size * channels * bps / 8))
			for (var i = 0; i < samples.size; i++)
			{
				outputStream.writeShort(Short.reverseBytes(samples.get(i)as short));
			}
			
			var path = 'Pure Unity/Assets/Art/AudioClips/'+audioClip+'.wav'
			fsa.generateFile(path, new ByteArrayInputStream(baos.toByteArray))
			
			var metaFile = yaml.createFile
			metaFile.nodes.add(kv('fileFormatVersion', 2.yaml))
			metaFile.nodes.add(kv('guid', guid(audioClip)))
			var importer = createMap
			importer.key = 'AudioImporter'
			importer.body.add(kv('serializedVersion', 6.yaml))
			metaFile.nodes.add(importer)
			
			for (folder : folders)
			{
				generate(metaFile, yamlRuntime, fsa, folder+audioClip+'.wav.meta')
			}
		}
		
		for (text : texts)
		{
			for (folder : folders)
			{
				fsa.generateFile(folder+text+'.txt', 'Hello')
			}
		}
	}
}