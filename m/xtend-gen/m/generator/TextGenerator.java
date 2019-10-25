package m.generator;

import java.util.HashMap;
import m.TextRuntimeModule;
import m.m.Game;
import m.transformation.ArtGenerator;
import m.transformation.BlockGenerator;
import m.transformation.ComponentGenerator;
import m.transformation.EntityGenerator;
import m.transformation.GenericSerializer;
import m.transformation.PackageGenerator;
import m.transformation.SettingsGenerator;
import m.transformation.SystemGenerator;
import m.transformation.TranslateTestua;
import m.transformation.Type;
import m.transformation.TypeInference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class TextGenerator extends AbstractGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    long startTime = System.currentTimeMillis();
    TypeInference typeInference = new TypeInference();
    HashMap<String, Type> types = new HashMap<String, Type>();
    EObject _head = IterableExtensions.<EObject>head(resource.getContents());
    Game game = ((Game) _head);
    types = typeInference.infer(game);
    int _size = game.getEntities().size();
    String _plus = ("src-gen/" + Integer.valueOf(_size));
    String _plus_1 = (_plus + " entities.done");
    fsa.generateFile(_plus_1, "");
    int _size_1 = types.keySet().size();
    boolean _greaterThan = (_size_1 > 0);
    if (_greaterThan) {
      ComponentGenerator componentGenerator = new ComponentGenerator();
      componentGenerator.generate(types, fsa);
      componentGenerator.metaFiles(types, fsa);
      PackageGenerator jsonGenerator = new PackageGenerator();
      jsonGenerator.generate(game, fsa);
      jsonGenerator.assemblies(game, fsa);
      ArtGenerator artGenerator = new ArtGenerator();
      artGenerator.generate(game, types, fsa, "Pure Unity/Assets/Art/Meshes/", "Hybrid Unity/Assets/Art/Meshes/");
      EntityGenerator entityGenerator = new EntityGenerator();
      entityGenerator.generate(game, types, fsa);
      SettingsGenerator settingsGenerator = new SettingsGenerator();
      settingsGenerator.generate(game, fsa);
      SystemGenerator systemGenerator = new SystemGenerator();
      systemGenerator.generate(game, types, fsa);
      TranslateTestua translateTestua = new TranslateTestua();
      translateTestua.translate(game, fsa, resource.getURI().lastSegment().replace(".text", ".testua"));
      BlockGenerator blockGenerator = new BlockGenerator();
      blockGenerator.generate(game, fsa, "Blocks/");
      TextRuntimeModule _textRuntimeModule = new TextRuntimeModule();
      String _lastSegment = resource.getURI().lastSegment();
      String _plus_2 = ("Text/" + _lastSegment);
      GenericSerializer.generate(game, _textRuntimeModule, fsa, _plus_2);
    }
  }
}
