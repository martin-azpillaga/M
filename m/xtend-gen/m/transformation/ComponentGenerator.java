package m.transformation;

import com.google.common.base.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import m.CSRuntimeModule;
import m.YAMLRuntimeModule;
import m.cs.Access;
import m.cs.Annotation;
import m.cs.Argument;
import m.cs.Assignment;
import m.cs.AssignmentType;
import m.cs.Cast;
import m.cs.Comparison;
import m.cs.CsFactory;
import m.cs.Field;
import m.cs.File;
import m.cs.Member;
import m.cs.Method;
import m.cs.QualifiedName;
import m.cs.RelationType;
import m.cs.Return;
import m.cs.Type;
import m.cs.TypeEnum;
import m.cs.Visibility;
import m.m.Game;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import m.yaml.YamlFactory;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ComponentGenerator {
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  @Extension
  private CsFactory factory = CsFactory.eINSTANCE;
  
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  private HashSet<QualifiedName> usings = new HashSet<QualifiedName>();
  
  public Object extensions(final Game game, final IFileSystemAccess2 fsa) {
    return null;
  }
  
  public void extendedText(final IFileSystemAccess2 fsa) {
    File file = this.factory.createFile();
    this.helper.addUsing(file, "Unity");
    this.helper.addUsing(file, "UnityEngine");
    this.helper.addUsing(file, "UnityEngine", "UI");
    this.helper.addUsing(file, "UnityEditor");
    Member component = this.factory.createMember();
    component.setVisibility(Visibility.PUBLIC);
    Type extendedText = this.factory.createType();
    extendedText.setName(this.helper.type("ExtendedText"));
    extendedText.getSuperTypes().add(this.helper.type("Text"));
    extendedText.getMembers().add(this.textAssetField());
    extendedText.getMembers().add(this.updateText());
    component.setValue(extendedText);
    file.getTypes().add(component);
    Member editor = this.factory.createMember();
    editor.setVisibility(Visibility.PUBLIC);
    Annotation annotation = this.factory.createAnnotation();
    annotation.setFunction(this.helper.type("CustomEditor"));
    annotation.getParameters().add(this.helper.call(this.helper.a(), "typeof", this.helper.label(this.helper.a(), "ExtendedText")));
    editor.getAnnotations().add(annotation);
    Type textEditor = this.factory.createType();
    textEditor.setName(this.helper.type("ExtendedTextEditor"));
    textEditor.getSuperTypes().add(this.helper.type("Editor"));
    textEditor.getMembers().add(this.inspectorGUI());
    editor.setValue(textEditor);
    file.getTypes().add(editor);
    CSRuntimeModule _cSRuntimeModule = new CSRuntimeModule();
    GenericSerializer.generate(file, _cSRuntimeModule, fsa, "Pure Unity/Assets/Code/Components/ExtendedText.cs");
  }
  
  public Member inspectorGUI() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setOverride(true);
    method.setName("OnInspectorGUI");
    Cast cast = this.factory.createCast();
    cast.setType(this.helper.type("MyText"));
    cast.setExpression(this.helper.label(this.helper.a(), "target"));
    Assignment castTarget = this.factory.createAssignment();
    castTarget.setVar(true);
    castTarget.setAccess(this.helper.label(this.helper.a(), "text"));
    castTarget.setAssignmentType(AssignmentType.SET);
    castTarget.setExpression(cast);
    method.getCommands().add(castTarget);
    Cast cast2 = this.factory.createCast();
    cast2.setType(this.helper.type("MyText"));
    cast2.setExpression(this.helper.call(this.helper.label(this.helper.a(), "EditorGUILayout"), "ObjectField", this.helper.string("Asset"), this.helper.label(this.helper.label(this.helper.a(), "text"), "asset"), this.helper.call(this.helper.a(), "typeof", this.helper.label(this.helper.a(), "ExtendedText")), this.helper.label(this.helper.a(), "false")));
    Assignment castInput = this.factory.createAssignment();
    castInput.setAccess(this.helper.label(this.helper.label(this.helper.a(), "text"), "asset"));
    castInput.setAssignmentType(AssignmentType.SET);
    castInput.setExpression(cast2);
    method.getCommands().add(castInput);
    member.setValue(method);
    return member;
  }
  
  public Member textAssetField() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Field field = this.factory.createField();
    field.setType(this.helper.type("TextAsset"));
    field.setName("asset");
    member.setValue(field);
    return member;
  }
  
  public Member updateText() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("void"));
    method.setName("Update");
    Assignment command = this.factory.createAssignment();
    command.setAccess(this.helper.label(this.helper.a(), "text"));
    command.setAssignmentType(AssignmentType.SET);
    command.setExpression(this.helper.label(this.helper.label(this.helper.a(), "asset"), "text"));
    method.getCommands().add(command);
    member.setValue(method);
    return member;
  }
  
  public void metaFiles(final HashMap<String, m.transformation.Type> types, final IFileSystemAccess2 fsa) {
    YAMLRuntimeModule yamlModule = new YAMLRuntimeModule();
    final Function1<String, Boolean> _function = (String it) -> {
      return Boolean.valueOf(this.valid(it));
    };
    Iterable<String> _filter = IterableExtensions.<String>filter(types.keySet(), _function);
    for (final String component : _filter) {
      {
        m.yaml.File file = this.yaml.createFile();
        this.helper.add(file.getNodes(), "fileFormatVersion", 2);
        this.helper.add(file.getNodes(), "guid", this.helper.uuid((component + "component")));
        String _authoringComponentName = this.authoringComponentName(component);
        String _plus = ("Pure Unity/Assets/Code/Components/" + _authoringComponentName);
        String _plus_1 = (_plus + ".cs.meta");
        GenericSerializer.generate(file, yamlModule, fsa, _plus_1);
      }
    }
  }
  
  public boolean valid(final String name) {
    List<String> builtIn = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("a", "b", "position", "rotation", "scale", "mesh", "material", "audioClip", "triggerZone", "extent", "radius", "velocity", "acceleration", "force", "mass", "eyes", "emission", "color", "backgroundColor", "restitution", "friction"));
    return ((!builtIn.contains(name)) && (!name.contains("@")));
  }
  
  public void generate(final Map<String, m.transformation.Type> components, final IFileSystemAccess2 fsa) {
    CSRuntimeModule csharp = new CSRuntimeModule();
    final Function1<String, Boolean> _function = (String it) -> {
      return Boolean.valueOf(this.valid(it));
    };
    Iterable<String> _filter = IterableExtensions.<String>filter(components.keySet(), _function);
    for (final String component : _filter) {
      {
        File file = this.convert(component, components.get(component));
        String _authoringComponentName = this.authoringComponentName(component);
        String _plus = ("Pure Unity/Assets/Code/Components/" + _authoringComponentName);
        String _plus_1 = (_plus + ".cs");
        GenericSerializer.generate(file, csharp, fsa, _plus_1);
      }
    }
  }
  
  public File convert(final String name, final m.transformation.Type type) {
    File file = this.factory.createFile();
    HashSet<QualifiedName> _hashSet = new HashSet<QualifiedName>();
    this.usings = _hashSet;
    file.getTypes().add(this.componentDataStruct(name, type));
    file.getTypes().add(this.proxyClass(name, type));
    HashSet<String> real = new HashSet<String>();
    for (final QualifiedName using : this.usings) {
      real.add(IterableExtensions.join(using.getNames(), "."));
    }
    for (final String r : real) {
      {
        String[] split = r.split("\\.");
        this.helper.addUsing(file, split);
      }
    }
    return file;
  }
  
  public boolean isValueType(final m.transformation.Type type) {
    return Collections.<m.transformation.Type>unmodifiableList(CollectionLiterals.<m.transformation.Type>newArrayList(m.transformation.Type.tag, m.transformation.Type.float1, m.transformation.Type.float2, m.transformation.Type.float3, m.transformation.Type.float4, m.transformation.Type.entity, m.transformation.Type.gameObject)).contains(type);
  }
  
  public boolean addUsings(final File file, final m.transformation.Type type) {
    boolean _xblockexpression = false;
    {
      this.helper.addUsing(file, "UnityEngine");
      this.helper.addUsing(file, "Unity", "Entities");
      boolean _xifexpression = false;
      boolean _contains = Collections.<m.transformation.Type>unmodifiableList(CollectionLiterals.<m.transformation.Type>newArrayList(m.transformation.Type.float2, m.transformation.Type.float3, m.transformation.Type.float4)).contains(type);
      if (_contains) {
        _xifexpression = this.helper.addUsing(file, "Unity", "Mathematics");
      } else {
        boolean _xifexpression_1 = false;
        boolean _contains_1 = Collections.<m.transformation.Type>unmodifiableList(CollectionLiterals.<m.transformation.Type>newArrayList(m.transformation.Type.input)).contains(type);
        if (_contains_1) {
          boolean _xblockexpression_1 = false;
          {
            this.helper.addUsing(file, "UnityEngine", "InputSystem");
            _xblockexpression_1 = this.helper.addUsing(file, "System");
          }
          _xifexpression_1 = _xblockexpression_1;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public Member componentDataStruct(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Type struct = this.factory.createType();
    struct.setType(TypeEnum.STRUCT);
    struct.setName(this.helper.type(name));
    if ((type != m.transformation.Type.tag)) {
      struct.getMembers().add(this.valueField(type));
      struct.getMembers().add(this.toValue(name, type));
      struct.getMembers().add(this.toData(name, type));
    }
    boolean _isValueType = this.isValueType(type);
    if (_isValueType) {
      struct.getSuperTypes().add(this.helper.type("IComponentData"));
    } else {
      struct.getSuperTypes().add(this.helper.type("ISharedComponentData"));
      struct.getSuperTypes().add(this.helper.type(this.helper.name("IEquatable"), this.helper.name(name)));
      struct.getMembers().add(this.equals(name));
      struct.getMembers().add(this.getHashCode(name));
      this.usings.add(this.helper.name("System"));
    }
    this.usings.add(this.helper.name("Unity", "Entities"));
    member.setValue(struct);
    return member;
  }
  
  public Member equals(final String name) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("bool"));
    method.setName("Equals");
    method.getArguments().add(this.helper.argument(this.helper.type(name), "other"));
    Comparison comparison = this.factory.createComparison();
    comparison.setLeft(this.helper.label(this.helper.label(this.helper.a(), "other"), "Value"));
    comparison.setRight(this.helper.label(this.helper.label(this.helper.a(), "this"), "Value"));
    comparison.setType(RelationType.EQUAL);
    Return returns = this.factory.createReturn();
    returns.setExpression(comparison);
    method.getCommands().add(returns);
    member.setValue(method);
    return member;
  }
  
  public Member getHashCode(final String name) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("int"));
    method.setName("GetHashCode");
    method.setOverride(true);
    Return returns = this.factory.createReturn();
    returns.setExpression(this.helper.call(this.helper.label(this.helper.a(), "Value"), "GetHashCode"));
    method.getCommands().add(returns);
    member.setValue(method);
    return member;
  }
  
  public String authoringComponentName(final String componentName) {
    return (componentName + "Authoring");
  }
  
  public Member proxyClass(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Type class_ = this.factory.createType();
    class_.setType(TypeEnum.CLASS);
    class_.setName(this.helper.type(this.authoringComponentName(name)));
    class_.getSuperTypes().add(this.helper.type("MonoBehaviour"));
    class_.getSuperTypes().add(this.helper.type("IConvertGameObjectToEntity"));
    member.setValue(class_);
    boolean _notEquals = (!Objects.equal(type, m.transformation.Type.tag));
    if (_notEquals) {
      class_.getMembers().add(this.serializedField(name, type));
    }
    class_.getMembers().add(this.convertMethod(name, type));
    this.usings.add(this.helper.name("UnityEngine"));
    this.usings.add(this.helper.name("Unity", "Entities"));
    return member;
  }
  
  public Member serializedField(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.getAnnotations().add(this.helper.annotation("SerializeField"));
    member.setVisibility(Visibility.PRIVATE);
    this.usings.add(this.helper.name("UnityEngine"));
    Field field = this.factory.createField();
    field.setType(this.helper.type(this.helper.authoringName(type)));
    field.setName("Value");
    member.setValue(field);
    return member;
  }
  
  public Member convertMethod(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("void"));
    method.setName("Convert");
    Argument _argument = this.helper.argument(this.helper.type("Entity"), "entity");
    Argument _argument_1 = this.helper.argument(this.helper.type("EntityManager"), "manager");
    Argument _argument_2 = this.helper.argument(this.helper.type("GameObjectConversionSystem"), "conversionSystem");
    method.getArguments().addAll(Collections.<Argument>unmodifiableList(CollectionLiterals.<Argument>newArrayList(_argument, _argument_1, _argument_2)));
    this.usings.add(this.helper.name("UnityEngine"));
    boolean _equals = Objects.equal(type, m.transformation.Type.gameObject);
    if (_equals) {
      method.getCommands().add(this.convertAndAdd(name));
    } else {
      method.getCommands().add(this.addComponentData(name, type));
    }
    member.setValue(method);
    return member;
  }
  
  public Assignment addComponentData(final String name, final m.transformation.Type type) {
    Access newComponent = this.factory.createAccess();
    newComponent.setNew(true);
    boolean _notEquals = (!Objects.equal(type, m.transformation.Type.tag));
    if (_notEquals) {
      this.helper.initialize(newComponent, name, this.helper.initialization("Value", this.helper.label(this.helper.a(), "Value")));
    } else {
      this.helper.initialize(newComponent, name);
    }
    String method = "AddComponentData";
    boolean _isValueType = this.isValueType(type);
    boolean _not = (!_isValueType);
    if (_not) {
      method = "AddSharedComponentData";
    }
    Access access = this.helper.call(this.helper.label(this.helper.a(), "manager"), method, this.helper.label(this.helper.a(), "entity"), newComponent);
    Assignment assignment = this.factory.createAssignment();
    assignment.setAccess(access);
    return assignment;
  }
  
  public Assignment convertAndAdd(final String name) {
    Access newComponent = this.factory.createAccess();
    newComponent.setNew(true);
    this.helper.initialize(newComponent, name, this.helper.initialization("Value", this.helper.call(this.helper.label(this.helper.a(), "GameObjectConversionUtility"), "ConvertGameObjectHierarchy", this.helper.label(this.helper.a(), "Value"), this.helper.label(this.helper.label(this.helper.a(), "World"), "Active"))));
    String method = "AddComponentData";
    Access access = this.helper.call(this.helper.label(this.helper.a(), "manager"), method, this.helper.label(this.helper.a(), "entity"), newComponent);
    Assignment assignment = this.factory.createAssignment();
    assignment.setAccess(access);
    return assignment;
  }
  
  public Member rangeClass(final String name) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Type class_ = this.factory.createType();
    class_.setType(TypeEnum.CLASS);
    class_.setName(this.helper.type(("_" + name)));
    class_.getSuperTypes().add(this.helper.type("MonoBehaviour"));
    member.setValue(class_);
    class_.getMembers().add(this.valueField(m.transformation.Type.input));
    return member;
  }
  
  public Member valueField(final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Field field = this.factory.createField();
    field.setType(this.helper.type(this.helper.stringName(type)));
    field.setName("Value");
    boolean _contains = Collections.<m.transformation.Type>unmodifiableList(CollectionLiterals.<m.transformation.Type>newArrayList(m.transformation.Type.float2, m.transformation.Type.float3, m.transformation.Type.float4)).contains(type);
    if (_contains) {
      this.usings.add(this.helper.name("Unity", "Mathematics"));
    } else {
      boolean _contains_1 = Collections.<m.transformation.Type>unmodifiableList(CollectionLiterals.<m.transformation.Type>newArrayList(m.transformation.Type.input)).contains(type);
      if (_contains_1) {
        this.usings.add(this.helper.name("UnityEngine", "InputSystem"));
      }
    }
    member.setValue(field);
    return member;
  }
  
  public Member toValue(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    member.setStatic(true);
    Method toValue = this.factory.createMethod();
    toValue.setImplicit(true);
    toValue.setOperator(true);
    toValue.setName(this.helper.stringName(type));
    Argument dataArgument = this.factory.createArgument();
    dataArgument.setType(this.helper.type(this.helper.name(name)));
    dataArgument.setName("data");
    toValue.getArguments().add(dataArgument);
    Return returnValue = this.factory.createReturn();
    returnValue.setExpression(this.helper.label(this.helper.label(this.helper.a(), "data"), "Value"));
    toValue.getCommands().add(returnValue);
    member.setValue(toValue);
    return member;
  }
  
  public Member toData(final String name, final m.transformation.Type type) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    member.setStatic(true);
    Method toData = this.factory.createMethod();
    toData.setImplicit(true);
    toData.setOperator(true);
    toData.setName(name);
    Argument dataArgument = this.factory.createArgument();
    dataArgument.setType(this.helper.type(this.helper.name(this.helper.stringName(type))));
    dataArgument.setName("Value");
    toData.getArguments().add(dataArgument);
    Return returnData = this.factory.createReturn();
    Access accessData = this.factory.createAccess();
    accessData.setNew(true);
    this.helper.initialize(accessData, name, this.helper.initialization("Value", this.helper.label(this.helper.a(), "Value")));
    returnData.setExpression(accessData);
    toData.getCommands().add(returnData);
    member.setValue(toData);
    return member;
  }
}
