package m.transformation;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import m.YAMLRuntimeModule;
import m.m.Component;
import m.m.Entity;
import m.m.Game;
import m.m.Value;
import m.m.Vector;
import m.m.Word;
import m.transformation.EngineComponent;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import m.transformation.Type;
import m.yaml.Document;
import m.yaml.Element;
import m.yaml.File;
import m.yaml.InlineMap;
import m.yaml.KeyValueList;
import m.yaml.Map;
import m.yaml.Node;
import m.yaml.Tag;
import m.yaml.Version;
import m.yaml.YamlFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class EntityGenerator {
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  @Extension
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  private YAMLRuntimeModule yamlRuntime = new YAMLRuntimeModule();
  
  private HashMap<String, Type> types;
  
  public void generate(final Game game, final HashMap<String, Type> types, final IFileSystemAccess2 fsa) {
    this.types = types;
    EList<Entity> _entities = game.getEntities();
    for (final Entity entity : _entities) {
      {
        File file = this.generate(entity);
        String _join = IterableExtensions.join(entity.getName(), " ");
        String _plus = ("Pure Unity/Assets/Design/Entities/" + _join);
        String _plus_1 = (_plus + ".prefab");
        GenericSerializer.generate(file, this.yamlRuntime, fsa, _plus_1);
        File meta = this.metaFile(entity);
        String _join_1 = IterableExtensions.join(entity.getName(), " ");
        String _plus_2 = ("Pure Unity/Assets/Design/Entities/" + _join_1);
        String _plus_3 = (_plus_2 + ".prefab.meta");
        GenericSerializer.generate(meta, this.yamlRuntime, fsa, _plus_3);
      }
    }
  }
  
  public File metaFile(final Entity entity) {
    File file = this.yaml.createFile();
    this.helper.add(file.getNodes(), "fileFormatVersion", 2);
    EList<String> _name = entity.getName();
    this.helper.add(file.getNodes(), "guid", this.helper.uuid((_name + "entity")));
    return file;
  }
  
  public File generate(final Entity entity) {
    ArrayList<Document> documents = this.inferComponents(entity);
    File file = this.yaml.createFile();
    file.setVersion(Version.ONE_ONE);
    file.getTags().add(Tag.UNITY);
    for (final Document document : documents) {
      file.getNodes().add(document);
    }
    return file;
  }
  
  public ArrayList<Document> inferComponents(final Entity entity) {
    ArrayList<Document> documentsToJoin = new ArrayList<Document>();
    Document go = this.yaml.createDocument();
    go.setTag("1");
    go.setId(this.helper.hash("GameObject"));
    Map gameObject = this.yaml.createMap();
    go.setValue(gameObject);
    gameObject.setKey("GameObject");
    gameObject.getBody().add(this.helper.kv("serializedVersion", this.helper.yaml(6)));
    gameObject.getBody().add(this.helper.kv("m_Name", IterableExtensions.join(entity.getName(), " ")));
    gameObject.getBody().add(this.helper.kv("m_IsActive", this.helper.yaml(1)));
    KeyValueList componentList = this.yaml.createKeyValueList();
    gameObject.getBody().add(componentList);
    componentList.setName("m_Component");
    Document t = this.yaml.createDocument();
    t.setTag("4");
    t.setId(this.helper.hash("Transform"));
    Map transform = this.yaml.createMap();
    t.setValue(transform);
    transform.setKey("Transform");
    documentsToJoin.add(go);
    documentsToJoin.add(t);
    EList<Component> _components = entity.getComponents();
    for (final Component c : _components) {
      documentsToJoin.add(this.document(c));
    }
    ArrayList<Document> documents = new ArrayList<Document>();
    for (final Document documentToJoin : documentsToJoin) {
      {
        final Function1<Document, Boolean> _function = (Document it) -> {
          String _id = it.getId();
          String _id_1 = documentToJoin.getId();
          return Boolean.valueOf(Objects.equal(_id, _id_1));
        };
        Document found = IterableExtensions.<Document>findFirst(documents, _function);
        if ((found == null)) {
          documents.add(documentToJoin);
        } else {
          found.getValue().getBody().addAll(documentToJoin.getValue().getBody());
        }
      }
    }
    for (final Document document : documents) {
      String _key = document.getValue().getKey();
      boolean _notEquals = (!Objects.equal(_key, "GameObject"));
      if (_notEquals) {
        long _parseLong = Long.parseLong(this.helper.hash("GameObject"));
        Pair<String, Long> _mappedTo = Pair.<String, Long>of("fileID", Long.valueOf(_parseLong));
        this.helper.addLongMap(document.getValue().getBody(), "m_GameObject", Collections.<String, Long>unmodifiableMap(CollectionLiterals.<String, Long>newHashMap(_mappedTo)));
        m.yaml.Long _yaml = this.helper.yaml(Long.parseLong(document.getId()));
        Pair<String, m.yaml.Long> _mappedTo_1 = Pair.<String, m.yaml.Long>of("fileID", _yaml);
        componentList.getValues().add(this.helper.kv("component", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_1))));
      }
    }
    return documents;
  }
  
  public int tag(final String name) {
    int _switchResult = (int) 0;
    if (name != null) {
      switch (name) {
        case "GameObject":
          _switchResult = 1;
          break;
        case "Transform":
          _switchResult = 4;
          break;
        case "Rigidbody2D":
          _switchResult = 50;
          break;
        case "AudioSource":
          _switchResult = 82;
          break;
        case "AudioListener":
          _switchResult = 81;
          break;
        case "Light":
          _switchResult = 108;
          break;
        case "MeshRenderer":
          _switchResult = 23;
          break;
        case "MeshFilter":
          _switchResult = 33;
          break;
        case "Camera":
          _switchResult = 20;
          break;
        case "CircleCollider2D":
          _switchResult = 58;
          break;
        case "RectTransform":
          _switchResult = 224;
          break;
        case "SpriteRenderer":
          _switchResult = 212;
          break;
        case "BoxCollider2D":
          _switchResult = 61;
          break;
        case "Canvas":
          _switchResult = 223;
          break;
        case "CanvasRenderer":
          _switchResult = 222;
          break;
        case "MonoBehaviour":
          _switchResult = 114;
          break;
        default:
          _switchResult = 114;
          break;
      }
    } else {
      _switchResult = 114;
    }
    return _switchResult;
  }
  
  public String field(final String name) {
    String _switchResult = null;
    if (name != null) {
      switch (name) {
        case "mesh":
          _switchResult = "m_Mesh";
          break;
        case "material":
          _switchResult = "m_Materials";
          break;
        case "position":
          _switchResult = "m_LocalPosition";
          break;
        case "scale":
          _switchResult = "m_LocalScale";
          break;
        case "mass":
          _switchResult = "m_Mass";
          break;
        case "damping":
          _switchResult = "m_LinearDamping";
          break;
        case "angularDamping":
          _switchResult = "m_AngularDamping";
          break;
        case "velocity":
          _switchResult = "m_InitialLinearVelocity";
          break;
        case "angularVelocity":
          _switchResult = "m_InitialAngularVelocity";
          break;
        case "gravityFactor":
          _switchResult = "m_GravityFactor";
          break;
        default:
          _switchResult = "Value";
          break;
      }
    } else {
      _switchResult = "Value";
    }
    return _switchResult;
  }
  
  public String unityId(final String name) {
    boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("GameObject", "Transform", "Rigidbody2D", "AudioSource", "AudioListener", "Light", "MeshRenderer", "MeshFilter", "Camera", "CircleCollider2D", "RectTransform", "SpriteRenderer", "BoxCollider2D", "Canvas", "CanvasRenderer")).contains(name);
    if (_contains) {
      return name;
    } else {
      return "MonoBehaviour";
    }
  }
  
  public Document document(final Component component) {
    String unityComponent = this.unityComponent(component);
    Document document = this.yaml.createDocument();
    document.setTag(Integer.valueOf(this.tag(unityComponent)).toString());
    boolean _equals = Objects.equal(unityComponent, "MonoBehaviour");
    if (_equals) {
      document.setId(this.helper.hash(component.getName()));
    } else {
      document.setId(this.helper.hash(unityComponent));
    }
    Map map = this.yaml.createMap();
    document.setValue(map);
    map.setKey(this.unityId(unityComponent));
    Value value = component.getValue();
    if ((value instanceof Vector)) {
      EList<Float> values = ((Vector)value).getNumbers();
      int _size = values.size();
      boolean _equals_1 = (_size == 1);
      if (_equals_1) {
        map.getBody().add(this.helper.kv(this.field(component.getName()), this.helper.yaml((values.get(0)).floatValue())));
      } else {
        int _size_1 = values.size();
        boolean _equals_2 = (_size_1 == 2);
        if (_equals_2) {
          Map inner = this.yaml.createMap();
          inner.setKey(this.field(component.getName()));
          inner.getBody().add(this.helper.kv("x", this.helper.yaml((values.get(0)).floatValue())));
          inner.getBody().add(this.helper.kv("y", this.helper.yaml((values.get(1)).floatValue())));
          map.getBody().add(inner);
        } else {
          int _size_2 = values.size();
          boolean _equals_3 = (_size_2 == 3);
          if (_equals_3) {
            Map inner_1 = this.yaml.createMap();
            inner_1.setKey(this.field(component.getName()));
            inner_1.getBody().add(this.helper.kv("x", this.helper.yaml((values.get(0)).floatValue())));
            inner_1.getBody().add(this.helper.kv("y", this.helper.yaml((values.get(1)).floatValue())));
            inner_1.getBody().add(this.helper.kv("z", this.helper.yaml((values.get(2)).floatValue())));
            map.getBody().add(inner_1);
          } else {
            int _size_3 = values.size();
            boolean _equals_4 = (_size_3 == 4);
            if (_equals_4) {
              Map inner_2 = this.yaml.createMap();
              inner_2.setKey(this.field(component.getName()));
              inner_2.getBody().add(this.helper.kv("x", this.helper.yaml((values.get(0)).floatValue())));
              inner_2.getBody().add(this.helper.kv("y", this.helper.yaml((values.get(1)).floatValue())));
              inner_2.getBody().add(this.helper.kv("z", this.helper.yaml((values.get(2)).floatValue())));
              inner_2.getBody().add(this.helper.kv("w", this.helper.yaml((values.get(2)).floatValue())));
              map.getBody().add(inner_2);
            }
          }
        }
      }
    } else {
      if ((value instanceof Word)) {
        EList<String> values_1 = ((Word)value).getValues();
        Type type = this.types.get(component.getName());
        boolean _equals_5 = Objects.equal(type, Type.mesh);
        if (_equals_5) {
          m.yaml.Long _yaml = this.helper.yaml(10202l);
          Pair<String, m.yaml.Long> _mappedTo = Pair.<String, m.yaml.Long>of("fileID", _yaml);
          Element _yaml_1 = this.helper.yaml("0000000000000000e000000000000000");
          Pair<String, Element> _mappedTo_1 = Pair.<String, Element>of("guid", _yaml_1);
          m.yaml.Long _yaml_2 = this.helper.yaml(0);
          Pair<String, m.yaml.Long> _mappedTo_2 = Pair.<String, m.yaml.Long>of("type", _yaml_2);
          map.getBody().add(this.helper.kv(this.field(component.getName()), Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2))));
        } else {
          boolean _equals_6 = Objects.equal(type, Type.material);
          if (_equals_6) {
            KeyValueList kvList = this.yaml.createKeyValueList();
            kvList.setName("m_Materials");
            map.getBody().add(kvList);
            InlineMap inline = this.yaml.createInlineMap();
            inline.getValues().add(this.helper.kv("fileID", this.helper.yaml(10303l)));
            inline.getValues().add(this.helper.kv("guid", this.helper.yaml("0000000000000000f000000000000000")));
            inline.getValues().add(this.helper.kv("type", this.helper.yaml(0)));
            kvList.getValues().add(inline);
          }
        }
      }
    }
    boolean _equals_7 = Objects.equal(unityComponent, "MonoBehaviour");
    if (_equals_7) {
      m.yaml.Long _yaml_3 = this.helper.yaml(11500000l);
      Pair<String, m.yaml.Long> _mappedTo_3 = Pair.<String, m.yaml.Long>of("fileID", _yaml_3);
      String _name = component.getName();
      Element _yaml_4 = this.helper.yaml(this.helper.uuid((_name + "component")));
      Pair<String, Element> _mappedTo_4 = Pair.<String, Element>of("guid", _yaml_4);
      m.yaml.Long _yaml_5 = this.helper.yaml(3);
      Pair<String, m.yaml.Long> _mappedTo_5 = Pair.<String, m.yaml.Long>of("type", _yaml_5);
      document.getValue().getBody().add(this.helper.kv("m_Script", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_3, _mappedTo_4, _mappedTo_5))));
    } else {
      boolean _equals_8 = Objects.equal(unityComponent, "PhysicsBody");
      if (_equals_8) {
        m.yaml.Long _yaml_6 = this.helper.yaml(11500000l);
        Pair<String, m.yaml.Long> _mappedTo_6 = Pair.<String, m.yaml.Long>of("fileID", _yaml_6);
        Element _yaml_7 = this.helper.yaml("ccea9ea98e38942e0b0938c27ed1903e");
        Pair<String, Element> _mappedTo_7 = Pair.<String, Element>of("guid", _yaml_7);
        m.yaml.Long _yaml_8 = this.helper.yaml(3);
        Pair<String, m.yaml.Long> _mappedTo_8 = Pair.<String, m.yaml.Long>of("type", _yaml_8);
        document.getValue().getBody().add(this.helper.kv("m_Script", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_6, _mappedTo_7, _mappedTo_8))));
      } else {
        boolean _equals_9 = Objects.equal(unityComponent, "PhysicsShape");
        if (_equals_9) {
          m.yaml.Long _yaml_9 = this.helper.yaml(11500000l);
          Pair<String, m.yaml.Long> _mappedTo_9 = Pair.<String, m.yaml.Long>of("fileID", _yaml_9);
          Element _yaml_10 = this.helper.yaml("b275e5f92732148048d7b77e264ac30e");
          Pair<String, Element> _mappedTo_10 = Pair.<String, Element>of("guid", _yaml_10);
          m.yaml.Long _yaml_11 = this.helper.yaml(3);
          Pair<String, m.yaml.Long> _mappedTo_11 = Pair.<String, m.yaml.Long>of("type", _yaml_11);
          document.getValue().getBody().add(this.helper.kv("m_Script", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_9, _mappedTo_10, _mappedTo_11))));
        }
      }
    }
    return document;
  }
  
  public String unityComponent(final Component component) {
    String _xblockexpression = null;
    {
      String name = component.getName();
      String _switchResult = null;
      if (name != null) {
        switch (name) {
          case "position":
            _switchResult = "Transform";
            break;
          case "rotation":
            _switchResult = "Transform";
            break;
          case "scale":
            _switchResult = "Transform";
            break;
          case "audioClip":
            _switchResult = "AudioSource";
            break;
          case "ears":
            _switchResult = "AudioListener";
            break;
          case "emission":
            _switchResult = "Light";
            break;
          case "intensity":
            _switchResult = "Light";
            break;
          case "material":
            _switchResult = "MeshRenderer";
            break;
          case "perspective":
            _switchResult = "Camera";
            break;
          case "range":
            _switchResult = "Light";
            break;
          case "spotAngle":
            _switchResult = "Light";
            break;
          case "mesh":
            _switchResult = "MeshFilter";
            break;
          case "text":
            _switchResult = "Text";
            break;
          case "viewAngle":
            _switchResult = "Camera";
            break;
          case "viewDistance":
            _switchResult = "Camera";
            break;
          case "clearColor":
            _switchResult = "Camera";
            break;
          case "font":
            _switchResult = "Text";
            break;
          case "pitch":
            _switchResult = "AudioSource";
            break;
          case "volume":
            _switchResult = "AudioSource";
            break;
          case "order":
            _switchResult = "SpriteRenderer";
            break;
          case "button":
            _switchResult = "Button";
            break;
          case "image":
            _switchResult = "Image";
            break;
          case "fillAmount":
            _switchResult = "Image";
            break;
          case "mass":
            _switchResult = "PhysicsBody";
            break;
          case "damping":
            _switchResult = "PhysicsBody";
            break;
          case "angularDamping":
            _switchResult = "PhysicsBody";
            break;
          case "velocity":
            _switchResult = "PhysicsBody";
            break;
          case "angularVelocity":
            _switchResult = "PhysicsBody";
            break;
          case "gravityFactor":
            _switchResult = "PhysicsBody";
            break;
          case "restitution":
            _switchResult = "PhysicsShape";
            break;
          case "friction":
            _switchResult = "PhysicsShape";
            break;
          case "radius":
            _switchResult = "PhysicsShape";
            break;
          case "extent":
            _switchResult = "PhysicsShape";
            break;
          case "triggerZone":
            _switchResult = "PhysicsShape";
            break;
          default:
            _switchResult = "MonoBehaviour";
            break;
        }
      } else {
        _switchResult = "MonoBehaviour";
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  public ArrayList<Node> properties(final Component component) {
    ArrayList<Node> result = new ArrayList<Node>();
    String name = component.getName();
    if (name != null) {
      switch (name) {
        case "position":
          Value _value = component.getValue();
          EList<Float> value = ((Vector) _value).getNumbers();
          m.yaml.Float _yaml = this.helper.yaml((value.get(0)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo = Pair.<String, m.yaml.Float>of("x", _yaml);
          m.yaml.Float _yaml_1 = this.helper.yaml((value.get(1)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo_1 = Pair.<String, m.yaml.Float>of("y", _yaml_1);
          m.yaml.Float _yaml_2 = this.helper.yaml((value.get(2)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo_2 = Pair.<String, m.yaml.Float>of("z", _yaml_2);
          result.add(this.helper.kv("m_LocalPosition", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2))));
          break;
        case "scale":
          Value _value_1 = component.getValue();
          EList<Float> value_1 = ((Vector) _value_1).getNumbers();
          m.yaml.Float _yaml_3 = this.helper.yaml((value_1.get(0)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo_3 = Pair.<String, m.yaml.Float>of("x", _yaml_3);
          m.yaml.Float _yaml_4 = this.helper.yaml((value_1.get(1)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo_4 = Pair.<String, m.yaml.Float>of("y", _yaml_4);
          m.yaml.Float _yaml_5 = this.helper.yaml((value_1.get(2)).floatValue());
          Pair<String, m.yaml.Float> _mappedTo_5 = Pair.<String, m.yaml.Float>of("z", _yaml_5);
          result.add(this.helper.kv("m_LocalScale", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_3, _mappedTo_4, _mappedTo_5))));
          break;
        case "audioClip":
          Value _value_2 = component.getValue();
          Word value_2 = ((Word) _value_2);
          m.yaml.Long _yaml_6 = this.helper.yaml(8300000);
          Pair<String, m.yaml.Long> _mappedTo_6 = Pair.<String, m.yaml.Long>of("fileID", _yaml_6);
          String _join = IterableExtensions.join(value_2.getValues(), " ");
          Element _guid = this.helper.guid((_join + "audioClip"));
          Pair<String, Element> _mappedTo_7 = Pair.<String, Element>of("guid", _guid);
          m.yaml.Long _yaml_7 = this.helper.yaml(3);
          Pair<String, m.yaml.Long> _mappedTo_8 = Pair.<String, m.yaml.Long>of("type", _yaml_7);
          result.add(this.helper.kv("m_audioClip", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_6, _mappedTo_7, _mappedTo_8))));
          break;
        case "mesh":
          Value _value_3 = component.getValue();
          Word value_3 = ((Word) _value_3);
          m.yaml.Long _yaml_8 = this.helper.yaml(10202l);
          Pair<String, m.yaml.Long> _mappedTo_9 = Pair.<String, m.yaml.Long>of("fileID", _yaml_8);
          Element _yaml_9 = this.helper.yaml("0000000000000000e000000000000000");
          Pair<String, Element> _mappedTo_10 = Pair.<String, Element>of("guid", _yaml_9);
          m.yaml.Long _yaml_10 = this.helper.yaml(0);
          Pair<String, m.yaml.Long> _mappedTo_11 = Pair.<String, m.yaml.Long>of("type", _yaml_10);
          result.add(this.helper.kv("m_Mesh", Collections.<String, Element>unmodifiableMap(CollectionLiterals.<String, Element>newHashMap(_mappedTo_9, _mappedTo_10, _mappedTo_11))));
          break;
        case "material":
          Value _value_4 = component.getValue();
          Word value_4 = ((Word) _value_4);
          KeyValueList kvList = this.yaml.createKeyValueList();
          kvList.setName("m_Materials");
          result.add(kvList);
          InlineMap inline = this.yaml.createInlineMap();
          inline.getValues().add(this.helper.kv("fileID", this.helper.yaml(10303l)));
          inline.getValues().add(this.helper.kv("guid", this.helper.yaml("0000000000000000f000000000000000")));
          inline.getValues().add(this.helper.kv("type", this.helper.yaml(0)));
          kvList.getValues().add(inline);
          break;
      }
    }
    return result;
  }
  
  public String n(final EngineComponent c) {
    String _switchResult = null;
    if (c != null) {
      switch (c) {
        case Restitution:
          _switchResult = "restitution";
          break;
        case Friction:
          _switchResult = "friction";
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
}
