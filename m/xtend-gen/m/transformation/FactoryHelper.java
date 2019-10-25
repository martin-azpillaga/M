package m.transformation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import m.cs.Access;
import m.cs.Annotation;
import m.cs.Argument;
import m.cs.AssignmentType;
import m.cs.CsFactory;
import m.cs.Expression;
import m.cs.File;
import m.cs.Indexing;
import m.cs.Initialization;
import m.cs.InitializeVariable;
import m.cs.Label;
import m.cs.MethodCall;
import m.cs.QualifiedName;
import m.cs.TypeName;
import m.cs.Using;
import m.json.Array;
import m.json.Field;
import m.json.JsonFactory;
import m.json.StringValue;
import m.transformation.Type;
import m.yaml.Element;
import m.yaml.Guid;
import m.yaml.InlineMap;
import m.yaml.KeyValue;
import m.yaml.Node;
import m.yaml.Word;
import m.yaml.YamlFactory;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class FactoryHelper {
  @Extension
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  @Extension
  private CsFactory csharp = CsFactory.eINSTANCE;
  
  @Extension
  private JsonFactory json = JsonFactory.eINSTANCE;
  
  private static FactoryHelper instance;
  
  private FactoryHelper() {
  }
  
  public static FactoryHelper eINSTANCE() {
    if ((FactoryHelper.instance == null)) {
      FactoryHelper _factoryHelper = new FactoryHelper();
      FactoryHelper.instance = _factoryHelper;
    }
    return FactoryHelper.instance;
  }
  
  public boolean add(final List<Node> list, final String key, final float value) {
    boolean _xblockexpression = false;
    {
      KeyValue keyValue = this.yaml.createKeyValue();
      keyValue.setKey(key);
      m.yaml.Float floatValue = this.yaml.createFloat();
      floatValue.setValue(value);
      keyValue.setValue(floatValue);
      _xblockexpression = list.add(keyValue);
    }
    return _xblockexpression;
  }
  
  public boolean add(final List<Node> list, final String key, final long value) {
    boolean _xblockexpression = false;
    {
      KeyValue keyValue = this.yaml.createKeyValue();
      keyValue.setKey(key);
      m.yaml.Long floatValue = this.yaml.createLong();
      floatValue.setValue(Long.valueOf(value).toString());
      keyValue.setValue(floatValue);
      _xblockexpression = list.add(keyValue);
    }
    return _xblockexpression;
  }
  
  public boolean add(final List<Node> list, final String key, final String value) {
    boolean _xblockexpression = false;
    {
      KeyValue keyValue = this.yaml.createKeyValue();
      keyValue.setKey(key);
      boolean _isDigit = Character.isDigit(value.charAt(0));
      if (_isDigit) {
        Guid guidValue = this.yaml.createGuid();
        guidValue.setValue(value);
        keyValue.setValue(guidValue);
      } else {
        Word wordValue = this.yaml.createWord();
        wordValue.setValue(value);
        keyValue.setValue(wordValue);
      }
      _xblockexpression = list.add(keyValue);
    }
    return _xblockexpression;
  }
  
  public boolean add(final List<Node> list, final String key, final Map<String, Float> map) {
    boolean _xblockexpression = false;
    {
      KeyValue keyValue = this.yaml.createKeyValue();
      keyValue.setKey(key);
      InlineMap value = this.yaml.createInlineMap();
      Set<String> _keySet = map.keySet();
      for (final String k : _keySet) {
        {
          KeyValue entry = this.yaml.createKeyValue();
          entry.setKey(k);
          m.yaml.Float entryValue = this.yaml.createFloat();
          entryValue.setValue((map.get(k)).floatValue());
          entry.setValue(entryValue);
          value.getValues().add(entry);
        }
      }
      keyValue.setValue(value);
      _xblockexpression = list.add(keyValue);
    }
    return _xblockexpression;
  }
  
  public boolean addLongMap(final List<Node> list, final String key, final Map<String, Long> map) {
    boolean _xblockexpression = false;
    {
      KeyValue keyValue = this.yaml.createKeyValue();
      keyValue.setKey(key);
      InlineMap value = this.yaml.createInlineMap();
      Set<String> _keySet = map.keySet();
      for (final String k : _keySet) {
        {
          KeyValue entry = this.yaml.createKeyValue();
          entry.setKey(k);
          m.yaml.Long entryValue = this.yaml.createLong();
          entryValue.setValue(map.get(k).toString());
          entry.setValue(entryValue);
          value.getValues().add(entry);
        }
      }
      keyValue.setValue(value);
      _xblockexpression = list.add(keyValue);
    }
    return _xblockexpression;
  }
  
  public KeyValue kv(final String key, final Element value) {
    KeyValue keyValue = this.yaml.createKeyValue();
    keyValue.setKey(key);
    keyValue.setValue(value);
    return keyValue;
  }
  
  public KeyValue kv(final String key, final String value) {
    KeyValue keyValue = this.yaml.createKeyValue();
    keyValue.setKey(key);
    boolean _isDigit = Character.isDigit(value.charAt(0));
    if (_isDigit) {
      Guid stringValue = this.yaml.createGuid();
      stringValue.setValue(value);
      keyValue.setValue(stringValue);
    } else {
      Word stringValue_1 = this.yaml.createWord();
      stringValue_1.setValue(value);
      keyValue.setValue(stringValue_1);
    }
    return keyValue;
  }
  
  public KeyValue kv(final String key, final Map<String, Element> map) {
    KeyValue keyValue = this.yaml.createKeyValue();
    keyValue.setKey(key);
    InlineMap inlineMap = this.yaml.createInlineMap();
    Set<String> _keySet = map.keySet();
    for (final String k : _keySet) {
      inlineMap.getValues().add(this.kv(k, map.get(k)));
    }
    keyValue.setValue(inlineMap);
    return keyValue;
  }
  
  public m.yaml.Float yaml(final float n) {
    m.yaml.Float number = this.yaml.createFloat();
    number.setValue(n);
    return number;
  }
  
  public m.yaml.Long yaml(final long n) {
    m.yaml.Long number = this.yaml.createLong();
    number.setValue(Long.valueOf(n).toString());
    return number;
  }
  
  public Element yaml(final String string) {
    boolean _isDigit = Character.isDigit(string.charAt(0));
    if (_isDigit) {
      Guid stringValue = this.yaml.createGuid();
      stringValue.setValue(string);
      return stringValue;
    } else {
      Word stringValue_1 = this.yaml.createWord();
      stringValue_1.setValue(string);
      return stringValue_1;
    }
  }
  
  public Element guid(final String string) {
    String uuid = this.uuid(string);
    boolean _isDigit = Character.isDigit(uuid.charAt(0));
    if (_isDigit) {
      Guid guid = this.yaml.createGuid();
      guid.setValue(uuid);
      return guid;
    } else {
      Word word = this.yaml.createWord();
      word.setValue(uuid);
      return word;
    }
  }
  
  public String uuid(final String string) {
    return UUID.nameUUIDFromBytes(string.getBytes()).toString().replace("-", "");
  }
  
  public InlineMap inlineMap(final Map<String, Element> map) {
    InlineMap inlineMap = this.yaml.createInlineMap();
    Set<String> _keySet = map.keySet();
    for (final String key : _keySet) {
      inlineMap.getValues().add(this.kv(key, map.get(key)));
    }
    return inlineMap;
  }
  
  public String hash(final String s) {
    String result = "";
    String uuid = this.uuid(s);
    for (int c = 0; (c < 10); c++) {
      {
        char character = uuid.charAt(c);
        if ((character > 57)) {
          String _result = result;
          result = (_result + Character.valueOf(((char) (character - 49))));
        } else {
          String _result_1 = result;
          result = (_result_1 + Character.valueOf(character));
        }
      }
    }
    boolean _startsWith = result.startsWith("0");
    if (_startsWith) {
      result = result.replaceFirst("0", "1");
    }
    return result;
  }
  
  public m.cs.String string(final String name) {
    m.cs.String expression = this.csharp.createString();
    expression.setValue(name);
    return expression;
  }
  
  public String stringName(final Type type) {
    String _switchResult = null;
    if (type != null) {
      switch (type) {
        case float1:
          _switchResult = "float";
          break;
        case float2:
          _switchResult = "float2";
          break;
        case float3:
          _switchResult = "float3";
          break;
        case float4:
          _switchResult = "float4";
          break;
        case gameObject:
          _switchResult = "Entity";
          break;
        case audioClip:
          _switchResult = "AudioClip";
          break;
        case material:
          _switchResult = "Material";
          break;
        case mesh:
          _switchResult = "Mesh";
          break;
        case sprite:
          _switchResult = "Sprite";
          break;
        case input:
          _switchResult = "InputAction";
          break;
        case tag:
          _switchResult = "tag components shouldnt have a field";
          break;
        case entity:
          _switchResult = "Entity";
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  public String authoringName(final Type type) {
    String _switchResult = null;
    if (type != null) {
      switch (type) {
        case float1:
          _switchResult = "float";
          break;
        case float2:
          _switchResult = "float2";
          break;
        case float3:
          _switchResult = "float3";
          break;
        case float4:
          _switchResult = "float4";
          break;
        case gameObject:
          _switchResult = "GameObject";
          break;
        case audioClip:
          _switchResult = "AudioClip";
          break;
        case material:
          _switchResult = "Material";
          break;
        case mesh:
          _switchResult = "Mesh";
          break;
        case sprite:
          _switchResult = "Sprite";
          break;
        case input:
          _switchResult = "InputAction";
          break;
        case tag:
          _switchResult = "tag components shouldnt have a field";
          break;
        case entity:
          _switchResult = "Entity";
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  public Annotation serializableAnnotation() {
    Annotation annotation = this.csharp.createAnnotation();
    annotation.setFunction(this.type("Serializable"));
    return annotation;
  }
  
  public boolean addUsing(final File file, final String... names) {
    boolean _xblockexpression = false;
    {
      Using using = this.csharp.createUsing();
      QualifiedName name = this.csharp.createQualifiedName();
      CollectionExtensions.<String>addAll(name.getNames(), names);
      using.setName(name);
      _xblockexpression = file.getUsings().add(using);
    }
    return _xblockexpression;
  }
  
  public Access a() {
    return this.csharp.createAccess();
  }
  
  public Access label(final Access access, final String name) {
    Label portion = this.csharp.createLabel();
    portion.setName(name);
    access.getPortions().add(portion);
    return access;
  }
  
  public Access call(final Access access, final String name, final TypeName generic) {
    MethodCall call = this.csharp.createMethodCall();
    call.setName(name);
    call.getGenerics().add(generic);
    access.getPortions().add(call);
    return access;
  }
  
  public Access call(final Access access, final String name, final Expression... expressions) {
    MethodCall call = this.csharp.createMethodCall();
    call.setName(name);
    CollectionExtensions.<Expression>addAll(call.getExpressions(), expressions);
    access.getPortions().add(call);
    return access;
  }
  
  public Access call(final Access access, final String name, final TypeName generic, final Expression... expressions) {
    MethodCall call = this.csharp.createMethodCall();
    call.setName(name);
    call.getGenerics().add(generic);
    CollectionExtensions.<Expression>addAll(call.getExpressions(), expressions);
    access.getPortions().add(call);
    return access;
  }
  
  public Initialization initializePortion(final String name, final InitializeVariable... initializations) {
    Initialization portion = this.csharp.createInitialization();
    portion.setName(name);
    CollectionExtensions.<InitializeVariable>addAll(portion.getInitializations(), initializations);
    return portion;
  }
  
  public MethodCall methodPortion(final String name, final Expression... parameters) {
    MethodCall portion = this.csharp.createMethodCall();
    portion.setName(name);
    CollectionExtensions.<Expression>addAll(portion.getExpressions(), parameters);
    return portion;
  }
  
  public Access methodPortion(final Access access, final String name, final TypeName generic, final Expression... parameters) {
    MethodCall portion = this.csharp.createMethodCall();
    portion.setName(name);
    if ((generic != null)) {
      portion.getGenerics().add(generic);
    }
    CollectionExtensions.<Expression>addAll(portion.getExpressions(), parameters);
    access.getPortions().add(portion);
    return access;
  }
  
  public Access initialize(final Access access, final String name, final InitializeVariable... initializations) {
    Initialization portion = this.csharp.createInitialization();
    portion.setName(name);
    CollectionExtensions.<InitializeVariable>addAll(portion.getInitializations(), initializations);
    access.getPortions().add(portion);
    return access;
  }
  
  public QualifiedName name(final String... names) {
    QualifiedName name = this.csharp.createQualifiedName();
    CollectionExtensions.<String>addAll(name.getNames(), names);
    return name;
  }
  
  public TypeName type(final QualifiedName... names) {
    TypeName typeName = this.csharp.createTypeName();
    typeName.setName(IterableExtensions.<QualifiedName>head(((Iterable<QualifiedName>)Conversions.doWrapArray(names))));
    for (int i = 1; (i < ((List<QualifiedName>)Conversions.doWrapArray(names)).size()); i++) {
      typeName.getGenerics().add(names[i]);
    }
    return typeName;
  }
  
  public TypeName type(final String name) {
    TypeName type = this.csharp.createTypeName();
    QualifiedName qualifiedName = this.csharp.createQualifiedName();
    qualifiedName.getNames().add(name);
    type.setName(qualifiedName);
    return type;
  }
  
  public Argument argument(final TypeName type, final String name) {
    Argument argument = this.csharp.createArgument();
    argument.setType(type);
    argument.setName(name);
    return argument;
  }
  
  public Argument refArgument(final TypeName type, final String name) {
    Argument argument = this.csharp.createArgument();
    argument.setRef(true);
    argument.setType(type);
    argument.setName(name);
    return argument;
  }
  
  public boolean add(final m.json.Object o, final String key, final String value) {
    boolean _xblockexpression = false;
    {
      Field field = this.json.createField();
      field.setName(key);
      StringValue stringValue = this.json.createStringValue();
      stringValue.setValue(value);
      field.setValue(stringValue);
      _xblockexpression = o.getFields().add(field);
    }
    return _xblockexpression;
  }
  
  public boolean add(final m.json.Object o, final String name, final List<String> values) {
    boolean _xblockexpression = false;
    {
      Field field = this.json.createField();
      field.setName(name);
      Array array = this.json.createArray();
      for (final String value : values) {
        {
          StringValue stringValue = this.json.createStringValue();
          stringValue.setValue(value);
          array.getElements().add(stringValue);
        }
      }
      field.setValue(array);
      _xblockexpression = o.getFields().add(field);
    }
    return _xblockexpression;
  }
  
  public AssignmentType toCS(final m.m.AssignmentType type) {
    AssignmentType _switchResult = null;
    if (type != null) {
      switch (type) {
        case AND:
          _switchResult = AssignmentType.AND;
          break;
        case DECREASE:
          _switchResult = AssignmentType.DECREASE;
          break;
        case DIVIDE:
          _switchResult = AssignmentType.DIVIDE;
          break;
        case INCREASE:
          _switchResult = AssignmentType.INCREASE;
          break;
        case MODULUS:
          _switchResult = AssignmentType.MODULUS;
          break;
        case MULTIPLY:
          _switchResult = AssignmentType.MULTIPLY;
          break;
        case OR:
          _switchResult = AssignmentType.OR;
          break;
        case SET:
          _switchResult = AssignmentType.SET;
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  public m.cs.Float number(final float number) {
    m.cs.Float literal = this.csharp.createFloat();
    literal.setValue(number);
    return literal;
  }
  
  public m.cs.Long number(final long number) {
    m.cs.Long literal = this.csharp.createLong();
    literal.setValue(number);
    return literal;
  }
  
  public Annotation annotation(final String name) {
    Annotation annotation = this.csharp.createAnnotation();
    annotation.setFunction(this.type(name));
    return annotation;
  }
  
  public InitializeVariable initialization(final String name, final Expression expression) {
    InitializeVariable initialization = this.csharp.createInitializeVariable();
    initialization.setName(name);
    initialization.setExpression(expression);
    return initialization;
  }
  
  public Access index(final Access access, final String name, final Expression index) {
    Indexing indexing = this.csharp.createIndexing();
    indexing.setName(name);
    indexing.setIndex(index);
    access.getPortions().add(indexing);
    return access;
  }
}
