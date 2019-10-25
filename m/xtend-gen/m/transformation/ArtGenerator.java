package m.transformation;

import com.google.common.base.Objects;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import m.YAMLRuntimeModule;
import m.m.Component;
import m.m.Entity;
import m.m.Game;
import m.m.Value;
import m.m.Word;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import m.transformation.Type;
import m.yaml.File;
import m.yaml.Map;
import m.yaml.YamlFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ArtGenerator {
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  @Extension
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  public void generate(final Game game, final HashMap<String, Type> types, final IFileSystemAccess2 fsa, final String... folders) {
    try {
      final Function1<String, Boolean> _function = (String it) -> {
        Type _get = types.get(it);
        return Boolean.valueOf(Objects.equal(_get, Type.mesh));
      };
      Set<String> meshComponents = IterableExtensions.<String>toSet(IterableExtensions.<String>filter(types.keySet(), _function));
      HashSet<String> sprites = new HashSet<String>();
      HashSet<String> audioClips = new HashSet<String>();
      HashSet<String> meshes = new HashSet<String>();
      HashSet<String> texts = new HashSet<String>();
      EList<Entity> _entities = game.getEntities();
      for (final Entity entity : _entities) {
        EList<Component> _components = entity.getComponents();
        for (final Component component : _components) {
          boolean _contains = meshComponents.contains(component.getName());
          if (_contains) {
            Value _value = component.getValue();
            meshes.add(IterableExtensions.join(((Word) _value).getValues(), " "));
          }
        }
      }
      Random random = new Random();
      YAMLRuntimeModule yamlRuntime = new YAMLRuntimeModule();
      for (final String mesh : meshes) {
        for (final String folder : folders) {
          {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("o ");
            _builder.append(mesh);
            _builder.newLineIfNotEmpty();
            _builder.newLine();
            _builder.append("v -0.500000 -0.500000 0.500000");
            _builder.newLine();
            _builder.append("v 0.500000 -0.500000 0.500000");
            _builder.newLine();
            _builder.append("v -0.500000 0.500000 0.500000");
            _builder.newLine();
            _builder.append("v 0.500000 0.500000 0.500000");
            _builder.newLine();
            _builder.append("v -0.500000 0.500000 -0.500000");
            _builder.newLine();
            _builder.append("v 0.500000 0.500000 -0.500000");
            _builder.newLine();
            _builder.append("v -0.500000 -0.500000 -0.500000");
            _builder.newLine();
            _builder.append("v 0.500000 -0.500000 -0.500000");
            _builder.newLine();
            _builder.newLine();
            _builder.append("vt 0.000000 0.000000");
            _builder.newLine();
            _builder.append("vt 1.000000 0.000000");
            _builder.newLine();
            _builder.append("vt 0.000000 1.000000");
            _builder.newLine();
            _builder.append("vt 1.000000 1.000000");
            _builder.newLine();
            _builder.newLine();
            _builder.append("vn 0.000000 0.000000 1.000000");
            _builder.newLine();
            _builder.append("vn 0.000000 1.000000 0.000000");
            _builder.newLine();
            _builder.append("vn 0.000000 0.000000 -1.000000");
            _builder.newLine();
            _builder.append("vn 0.000000 -1.000000 0.000000");
            _builder.newLine();
            _builder.append("vn 1.000000 0.000000 0.000000");
            _builder.newLine();
            _builder.append("vn -1.000000 0.000000 0.000000");
            _builder.newLine();
            _builder.newLine();
            _builder.append("g ");
            _builder.append(mesh);
            _builder.newLineIfNotEmpty();
            _builder.append("s 1");
            _builder.newLine();
            _builder.append("f 1/1/1 2/2/1 3/3/1");
            _builder.newLine();
            _builder.append("f 3/3/1 2/2/1 4/4/1");
            _builder.newLine();
            _builder.append("s 2");
            _builder.newLine();
            _builder.append("f 3/1/2 4/2/2 5/3/2");
            _builder.newLine();
            _builder.append("f 5/3/2 4/2/2 6/4/2");
            _builder.newLine();
            _builder.append("s 3");
            _builder.newLine();
            _builder.append("f 5/4/3 6/3/3 7/2/3");
            _builder.newLine();
            _builder.append("f 7/2/3 6/3/3 8/1/3");
            _builder.newLine();
            _builder.append("s 4");
            _builder.newLine();
            _builder.append("f 7/1/4 8/2/4 1/3/4");
            _builder.newLine();
            _builder.append("f 1/3/4 8/2/4 2/4/4");
            _builder.newLine();
            _builder.append("s 5");
            _builder.newLine();
            _builder.append("f 2/1/5 8/2/5 4/3/5");
            _builder.newLine();
            _builder.append("f 4/3/5 8/2/5 6/4/5");
            _builder.newLine();
            _builder.append("s 6");
            _builder.newLine();
            _builder.append("f 7/1/6 1/2/6 5/3/6");
            _builder.newLine();
            _builder.append("f 5/3/6 1/2/6 3/4/6");
            _builder.newLine();
            fsa.generateFile(((folder + mesh) + ".obj"), _builder);
            File file = this.yaml.createFile();
            this.helper.add(file.getNodes(), "fileFormatVersion", 2);
            this.helper.add(file.getNodes(), "guid", this.helper.uuid((mesh + "mesh")));
            GenericSerializer.generate(file, yamlRuntime, fsa, ((folder + mesh) + ".obj.meta"));
          }
        }
      }
      for (final String sprite : sprites) {
        {
          BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
          int _nextInt = random.nextInt(256);
          int _nextInt_1 = random.nextInt(256);
          int _nextInt_2 = random.nextInt(256);
          image.setRGB(0, 0, new Color(_nextInt, _nextInt_1, _nextInt_2).getRGB());
          ByteArrayOutputStream o = new ByteArrayOutputStream();
          ImageIO.write(image, "png", o);
          File file = this.yaml.createFile();
          this.helper.add(file.getNodes(), "fileFormatVersion", 2);
          this.helper.add(file.getNodes(), "guid", this.helper.uuid(sprite));
          Map importer = this.yaml.createMap();
          importer.setKey("TextureImporter");
          this.helper.add(importer.getBody(), "serializedVersion", 10);
          this.helper.add(importer.getBody(), "spriteMode", 1);
          importer.getBody().add(this.helper.kv("spritePixelsToUnits", this.helper.yaml(1)));
          importer.getBody().add(this.helper.kv("textureType", this.helper.yaml(8)));
          file.getNodes().add(importer);
          for (final String folder_1 : folders) {
            {
              byte[] _byteArray = o.toByteArray();
              ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_byteArray);
              fsa.generateFile(((folder_1 + sprite) + ".png"), _byteArrayInputStream);
              GenericSerializer.generate(file, yamlRuntime, fsa, ((folder_1 + sprite) + ".png.meta"));
            }
          }
        }
      }
      for (final String audioClip : audioClips) {
        {
          int hz = 44100;
          int bps = 16;
          int rescaleFactor = 0x7FFF;
          int header = 36;
          int channels = 1;
          int format = 1;
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          DataOutputStream outputStream = new DataOutputStream(baos);
          int _nextInt = random.nextInt(200);
          int note = (_nextInt + 300);
          int[] samples = new int[hz];
          for (int i = 0; (i < ((List<Integer>)Conversions.doWrapArray(samples)).size()); i++) {
            double _sin = Math.sin(((((Math.PI * 2) * i) * note) / hz));
            double _multiply = (_sin * rescaleFactor);
            samples[i] = ((int) _multiply);
          }
          outputStream.writeBytes("RIFF");
          final int[] _converted_samples = (int[])samples;
          int _size = ((List<Integer>)Conversions.doWrapArray(_converted_samples)).size();
          int _multiply = (_size * bps);
          int _divide = (_multiply / 8);
          int _plus = (header + _divide);
          outputStream.writeInt(Integer.reverseBytes(_plus));
          outputStream.writeBytes("WAVE");
          outputStream.writeBytes("fmt ");
          outputStream.writeInt(Integer.reverseBytes(16));
          outputStream.writeShort(Short.reverseBytes(((short) format)));
          outputStream.writeShort(Short.reverseBytes(((short) channels)));
          outputStream.writeInt(Integer.reverseBytes(hz));
          outputStream.writeInt(Integer.reverseBytes((((hz * channels) * bps) / 8)));
          outputStream.writeShort(Short.reverseBytes(((short) ((channels * bps) / 8))));
          outputStream.writeShort(Short.reverseBytes(((short) bps)));
          outputStream.writeBytes("data");
          final int[] _converted_samples_1 = (int[])samples;
          int _size_1 = ((List<Integer>)Conversions.doWrapArray(_converted_samples_1)).size();
          int _multiply_1 = (_size_1 * channels);
          int _multiply_2 = (_multiply_1 * bps);
          int _divide_1 = (_multiply_2 / 8);
          outputStream.writeInt(Integer.reverseBytes(_divide_1));
          for (int i = 0; (i < ((List<Integer>)Conversions.doWrapArray(samples)).size()); i++) {
            int _get = samples[i];
            outputStream.writeShort(Short.reverseBytes(((short) _get)));
          }
          String path = (("Pure Unity/Assets/Art/AudioClips/" + audioClip) + ".wav");
          byte[] _byteArray = baos.toByteArray();
          ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_byteArray);
          fsa.generateFile(path, _byteArrayInputStream);
          File metaFile = this.yaml.createFile();
          metaFile.getNodes().add(this.helper.kv("fileFormatVersion", this.helper.yaml(2)));
          metaFile.getNodes().add(this.helper.kv("guid", this.helper.guid(audioClip)));
          Map importer = this.yaml.createMap();
          importer.setKey("AudioImporter");
          importer.getBody().add(this.helper.kv("serializedVersion", this.helper.yaml(6)));
          metaFile.getNodes().add(importer);
          for (final String folder_1 : folders) {
            GenericSerializer.generate(metaFile, yamlRuntime, fsa, ((folder_1 + audioClip) + ".wav.meta"));
          }
        }
      }
      for (final String text : texts) {
        for (final String folder_1 : folders) {
          fsa.generateFile(((folder_1 + text) + ".txt"), "Hello");
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
