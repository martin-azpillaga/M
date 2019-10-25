package m.transformation;

import java.util.Collections;
import m.YAMLRuntimeModule;
import m.m.Game;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import m.yaml.Document;
import m.yaml.File;
import m.yaml.Map;
import m.yaml.Tag;
import m.yaml.Version;
import m.yaml.YamlFactory;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class SettingsGenerator {
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  @Extension
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  private YAMLRuntimeModule yamlRuntime = new YAMLRuntimeModule();
  
  public void generate(final Game game, final IFileSystemAccess2 fsa) {
    this.twoDPhysics(fsa);
    this.threeDPhysics(fsa);
  }
  
  public void twoDPhysics(final IFileSystemAccess2 fsa) {
    File twoD = this.yaml.createFile();
    twoD.setVersion(Version.ONE_ONE);
    twoD.getTags().add(Tag.UNITY);
    Document document = this.yaml.createDocument();
    document.setTag("19");
    document.setId("1");
    Map map = this.yaml.createMap();
    map.setKey("Physics2DSettings");
    this.helper.add(map.getBody(), "serializedVersion", 4);
    Pair<String, Float> _mappedTo = Pair.<String, Float>of("x", Float.valueOf(0f));
    Pair<String, Float> _mappedTo_1 = Pair.<String, Float>of("y", Float.valueOf(0f));
    this.helper.add(map.getBody(), "m_Gravity", Collections.<String, Float>unmodifiableMap(CollectionLiterals.<String, Float>newHashMap(_mappedTo, _mappedTo_1)));
    document.setValue(map);
    twoD.getNodes().add(document);
    GenericSerializer.generate(twoD, this.yamlRuntime, fsa, "Pure Unity/ProjectSettings/Physics2DSettings.asset");
  }
  
  public void threeDPhysics(final IFileSystemAccess2 fsa) {
    File file = this.yaml.createFile();
    file.setVersion(Version.ONE_ONE);
    file.getTags().add(Tag.UNITY);
    Document document = this.yaml.createDocument();
    document.setTag("55");
    document.setId("1");
    Map map = this.yaml.createMap();
    map.setKey("PhysicsManager");
    this.helper.add(map.getBody(), "serializedVersion", 10);
    Pair<String, Long> _mappedTo = Pair.<String, Long>of("x", Long.valueOf(0l));
    Pair<String, Long> _mappedTo_1 = Pair.<String, Long>of("y", Long.valueOf(0l));
    Pair<String, Long> _mappedTo_2 = Pair.<String, Long>of("z", Long.valueOf(0l));
    this.helper.addLongMap(map.getBody(), "m_Gravity", Collections.<String, Long>unmodifiableMap(CollectionLiterals.<String, Long>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2)));
    document.setValue(map);
    file.getNodes().add(document);
    GenericSerializer.generate(file, this.yamlRuntime, fsa, "Pure Unity/ProjectSettings/DynamicsManager.asset");
  }
}
