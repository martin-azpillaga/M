package m.transformation;

import java.util.Collections;
import java.util.List;
import m.JsonRuntimeModule;
import m.json.Field;
import m.json.JsonFactory;
import m.m.Game;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class PackageGenerator {
  @Extension
  private JsonFactory factory = JsonFactory.eINSTANCE;
  
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  private JsonRuntimeModule jsonRuntime = new JsonRuntimeModule();
  
  public void generate(final Game game, final IFileSystemAccess2 fsa) {
    m.json.Object root = this.factory.createObject();
    Field dependencies = this.factory.createField();
    root.getFields().add(dependencies);
    dependencies.setName("dependencies");
    m.json.Object list = this.factory.createObject();
    dependencies.setValue(list);
    this.helper.add(list, "com.unity.entities", "0.1.1-preview");
    this.helper.add(list, "com.unity.ide.vscode", "1.1.2");
    this.helper.add(list, "com.unity.inputsystem", "1.0.0-preview");
    this.helper.add(list, "com.unity.physics", "0.2.4-preview");
    this.helper.add(list, "com.unity.rendering.hybrid", "0.1.1-preview");
    List<String> modules = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("ai", "animation", "assetbundle", "audio", "cloth", "director", "imageconversion", "imgui", "jsonserialize", "particlesystem", "physics", "physics2d", "screencapture", "terrain", "terrainphysics", "tilemap", "ui", "uielements", "umbra", "unityanalytics", "unitywebrequest", "unitywebrequestassetbundle", "unitywebrequestaudio", "unitywebrequesttexture", "unitywebrequestwww", "vehicles", "video", "vr", "wind", "xr"));
    for (final String module : modules) {
      this.helper.add(list, ("com.unity.modules." + module), "1.0.0");
    }
    GenericSerializer.generate(root, this.jsonRuntime, fsa, "Pure Unity/Packages/manifest.json");
  }
  
  public void assemblies(final Game game, final IFileSystemAccess2 fsa) {
    if (((game.getSystems().size() > 0) || (game.getEntities().size() > 0))) {
      m.json.Object root = this.factory.createObject();
      this.helper.add(root, "name", "Code");
      this.helper.add(root, "references", Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("Unity.Entities", "Unity.Entities.Hybrid", "Unity.Mathematics", "Unity.Burst", "Unity.InputSystem", "Unity.Physics", "Unity.Transforms", "Unity.Rendering.Hybrid")));
      GenericSerializer.generate(root, this.jsonRuntime, fsa, "Pure Unity/Assets/Code/Code.asmdef");
    }
    int _size = game.getSystems().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      m.json.Object root_1 = this.factory.createObject();
      this.helper.add(root_1, "name", "Test");
      this.helper.add(root_1, "references", Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("Code", "Unity.Entities", "Unity.Entities.Hybrid", "Unity.Mathematics", "Unity.Burst", "Unity.InputSystem", "Unity.Physics", "Unity.Transforms", "Unity.Rendering.Hybrid")));
      GenericSerializer.generate(root_1, this.jsonRuntime, fsa, "Pure Unity/Assets/Code/Tests/Test.asmdef");
    }
  }
}
