package m.transformation;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import m.CSRuntimeModule;
import m.YAMLRuntimeModule;
import m.cs.Access;
import m.cs.Annotation;
import m.cs.Argument;
import m.cs.Assignment;
import m.cs.AssignmentType;
import m.cs.Block;
import m.cs.Comparison;
import m.cs.CsFactory;
import m.cs.Else;
import m.cs.ElseIf;
import m.cs.Expression;
import m.cs.Field;
import m.cs.File;
import m.cs.For;
import m.cs.If;
import m.cs.InitializeVariable;
import m.cs.Lambda;
import m.cs.Member;
import m.cs.Method;
import m.cs.MethodCall;
import m.cs.RelationType;
import m.cs.Return;
import m.cs.TypeEnum;
import m.cs.TypeName;
import m.cs.Visibility;
import m.m.And;
import m.m.Brackets;
import m.m.Branch;
import m.m.Call;
import m.m.Command;
import m.m.Condition;
import m.m.Decrement;
import m.m.Divide;
import m.m.Game;
import m.m.Increment;
import m.m.Loop;
import m.m.Minus;
import m.m.Modulus;
import m.m.Not;
import m.m.Or;
import m.m.Plus;
import m.m.Times;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import m.transformation.Type;
import m.yaml.YamlFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class SystemGenerator {
  @Extension
  private CsFactory factory = CsFactory.eINSTANCE;
  
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  private YamlFactory yaml = YamlFactory.eINSTANCE;
  
  private HashMap<String, Type> types;
  
  private HashSet<String> usings;
  
  public void generate(final Game game, final HashMap<String, Type> types, final IFileSystemAccess2 fsa) {
    this.types = types;
    CSRuntimeModule csharp = new CSRuntimeModule();
    YAMLRuntimeModule yamlModule = new YAMLRuntimeModule();
    EList<m.m.System> _systems = game.getSystems();
    for (final m.m.System system : _systems) {
      {
        String _name = system.getName();
        String _plus = ("src-gen/" + _name);
        String _plus_1 = (_plus + ".done");
        fsa.generateFile(_plus_1, "");
        m.m.System s = ((m.m.System) system);
        File file = this.convert(s);
        String _name_1 = s.getName();
        String _plus_2 = ("Pure Unity/Assets/Code/Systems/" + _name_1);
        String _plus_3 = (_plus_2 + ".cs");
        GenericSerializer.generate(file, csharp, fsa, _plus_3);
        m.yaml.File meta = this.yaml.createFile();
        this.helper.add(meta.getNodes(), "fileFormatVersion", 2);
        String _name_2 = s.getName();
        this.helper.add(meta.getNodes(), "guid", this.helper.uuid((_name_2 + "system")));
        String _name_3 = s.getName();
        String _plus_4 = ("Pure Unity/Assets/Code/Systems/" + _name_3);
        String _plus_5 = (_plus_4 + ".cs.meta");
        GenericSerializer.generate(meta, yamlModule, fsa, _plus_5);
      }
    }
    File contactSystem = this.contactSystem();
    GenericSerializer.generate(contactSystem, csharp, fsa, "Pure Unity/Assets/Code/Systems/Contacts.cs");
    m.yaml.File meta = this.yaml.createFile();
    this.helper.add(meta.getNodes(), "fileFormatVersion", 2);
    this.helper.add(meta.getNodes(), "guid", this.helper.uuid(("Contacts" + "system")));
    GenericSerializer.generate(meta, yamlModule, fsa, "Pure Unity/Assets/Code/Systems/Contacts.cs.meta");
  }
  
  public File entryPoint(final List<m.m.System> systems) {
    File file = this.factory.createFile();
    this.helper.addUsing(file, "UnityEngine");
    this.helper.addUsing(file, "Unity", "Entities");
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    m.cs.Type class_ = this.factory.createType();
    member.setValue(class_);
    class_.setType(TypeEnum.CLASS);
    class_.setName(this.helper.type("Main"));
    file.getTypes().add(member);
    class_.getMembers().add(this.runMethod(systems));
    return file;
  }
  
  public Member runMethod(final List<m.m.System> systems) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PRIVATE);
    member.setStatic(true);
    Annotation runtimeAnnotation = this.factory.createAnnotation();
    runtimeAnnotation.setFunction(this.helper.type("RuntimeInitializeOnLoadMethod"));
    runtimeAnnotation.getParameters().add(this.helper.label(this.helper.label(this.helper.a(), "RuntimeInitializeLoadType"), "BeforeSceneLoad"));
    member.getAnnotations().add(runtimeAnnotation);
    member.setStatic(true);
    Method run = this.factory.createMethod();
    run.setType(this.helper.type("void"));
    run.setName("Run");
    member.setValue(run);
    Assignment worldAssignment = this.factory.createAssignment();
    worldAssignment.setVar(true);
    worldAssignment.setAccess(this.helper.label(this.helper.a(), "world"));
    worldAssignment.setAssignmentType(AssignmentType.SET);
    Access call = this.helper.call(this.helper.a(), "World", this.helper.string("world"));
    call.setNew(true);
    worldAssignment.setExpression(call);
    run.getCommands().add(worldAssignment);
    for (final m.m.System system : systems) {
      {
        Assignment create = this.factory.createAssignment();
        Access newSystem = this.helper.call(this.helper.a(), ((m.m.System) system).getName());
        newSystem.setNew(true);
        create.setAccess(this.helper.call(this.helper.label(this.helper.a(), "world"), "AddSystem", newSystem));
        run.getCommands().add(create);
      }
    }
    Assignment setWorld = this.factory.createAssignment();
    setWorld.setAccess(this.helper.label(this.helper.label(this.helper.a(), "World"), "Active"));
    setWorld.setAssignmentType(AssignmentType.SET);
    setWorld.setExpression(this.helper.label(this.helper.a(), "world"));
    run.getCommands().add(setWorld);
    return member;
  }
  
  public File convert(final m.m.System system) {
    File file = this.factory.createFile();
    HashSet<String> _hashSet = new HashSet<String>();
    this.usings = _hashSet;
    file.getTypes().add(this.systemClass(system));
    for (final String using : this.usings) {
      this.helper.addUsing(file, using.split("\\."));
    }
    return file;
  }
  
  public Member systemClass(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    m.cs.Type class_ = this.factory.createType();
    class_.setType(TypeEnum.CLASS);
    class_.setName(this.helper.type(system.getName()));
    class_.getSuperTypes().add(this.helper.type("ComponentSystem"));
    this.usings.add("Unity.Entities");
    class_.getMembers().add(this.update(system));
    member.setValue(class_);
    return member;
  }
  
  public ArrayList<Member> entityQueries(final m.m.System system) {
    ArrayList<Member> result = new ArrayList<Member>();
    List<Loop> loops = EcoreUtil2.<Loop>getAllContentsOfType(system, Loop.class);
    for (final Loop loop : loops) {
      {
        Member member = this.factory.createMember();
        member.setVisibility(Visibility.PRIVATE);
        Field field = this.factory.createField();
        field.setType(this.helper.type("EntityQuery"));
        field.setName(loop.getEntity());
        member.setValue(field);
        result.add(member);
      }
    }
    return result;
  }
  
  public Member onCreate(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PROTECTED);
    Method method = this.factory.createMethod();
    method.setOverride(true);
    method.setType(this.helper.type("void"));
    method.setName("OnCreate");
    List<Loop> loops = EcoreUtil2.<Loop>getAllContentsOfType(system, Loop.class);
    for (final Loop loop : loops) {
      method.getCommands().add(this.getQuery(system, loop));
    }
    member.setValue(method);
    return member;
  }
  
  public Assignment getQuery(final m.m.System system, final Loop loop) {
    Assignment command = this.factory.createAssignment();
    command.setAccess(this.helper.label(this.helper.a(), loop.getEntity()));
    command.setAssignmentType(AssignmentType.SET);
    Access expression = this.factory.createAccess();
    ArrayList<Expression> components = new ArrayList<Expression>();
    final Function1<m.m.Assignment, Boolean> _function = (m.m.Assignment it) -> {
      return Boolean.valueOf(((it.getAccess().getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getAccess().getNames()), loop.getEntity())));
    };
    final Function1<m.m.Assignment, String> _function_1 = (m.m.Assignment it) -> {
      return it.getAccess().getNames().get(1);
    };
    final Iterable<String> writes = IterableExtensions.<m.m.Assignment, String>map(IterableExtensions.<m.m.Assignment>filter(EcoreUtil2.<m.m.Assignment>getAllContentsOfType(loop, m.m.Assignment.class), _function), _function_1);
    final Function1<m.m.Access, Boolean> _function_2 = (m.m.Access it) -> {
      return Boolean.valueOf(((it.getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getNames()), loop.getEntity())));
    };
    final Function1<m.m.Access, Boolean> _function_3 = (m.m.Access it) -> {
      boolean _contains = IterableExtensions.<String>toList(writes).contains(it.getNames().get(1));
      return Boolean.valueOf((!_contains));
    };
    final Function1<m.m.Access, String> _function_4 = (m.m.Access it) -> {
      return it.getNames().get(1);
    };
    Iterable<String> reads = IterableExtensions.<m.m.Access, String>map(IterableExtensions.<m.m.Access>filter(IterableExtensions.<m.m.Access>filter(EcoreUtil2.<m.m.Access>getAllContentsOfType(loop, m.m.Access.class), _function_2), _function_3), _function_4);
    for (final String write : writes) {
      {
        Access writeAccess = this.helper.call(this.helper.label(this.helper.a(), "ComponentType"), "ReadWrite", this.helper.type(write));
        components.add(writeAccess);
      }
    }
    for (final String read : reads) {
      {
        Access readAccess = this.helper.call(this.helper.label(this.helper.a(), "ComponentType"), "ReadOnly", this.helper.type(read));
        components.add(readAccess);
      }
    }
    final ArrayList<Expression> _converted_components = (ArrayList<Expression>)components;
    this.helper.call(expression, "GetEntityQuery", ((Expression[])Conversions.unwrapArray(_converted_components, Expression.class)));
    command.setExpression(expression);
    return command;
  }
  
  public Member onUpdate(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PROTECTED);
    Method method = this.factory.createMethod();
    method.setOverride(true);
    method.setType(this.helper.type("JobHandle"));
    method.setName("OnUpdate");
    Argument dependencies = this.factory.createArgument();
    dependencies.setType(this.helper.type("JobHandle"));
    dependencies.setName("dependencies");
    method.getArguments().add(dependencies);
    method.getCommands().add(this.scheduleJob(system));
    member.setValue(method);
    return member;
  }
  
  public Member update(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PROTECTED);
    Method method = this.factory.createMethod();
    method.setOverride(true);
    method.setType(this.helper.type("void"));
    method.setName("OnUpdate");
    EList<Command> _commands = system.getCommands();
    for (final Command command : _commands) {
      method.getCommands().addAll(this.commands(system).getCommands());
    }
    member.setValue(method);
    return member;
  }
  
  public Return scheduleJob(final m.m.System system) {
    Return command = this.factory.createReturn();
    Access newJob = this.factory.createAccess();
    newJob.setNew(true);
    ArrayList<InitializeVariable> initializations = new ArrayList<InitializeVariable>();
    List<Loop> loops = EcoreUtil2.<Loop>getAllContentsOfType(system, Loop.class);
    for (final Loop loop : loops) {
      {
        InitializeVariable initialization = this.factory.createInitializeVariable();
        initialization.setName(loop.getEntity());
        initialization.setExpression(this.helper.call(this.helper.label(this.helper.a(), loop.getEntity()), "CreateArchetypeChunkArray", this.helper.label(this.helper.label(this.helper.a(), "Allocator"), "TempJob")));
        initializations.add(initialization);
      }
    }
    HashMap<String, Boolean> types = this.typesIn(system);
    Set<String> _keySet = types.keySet();
    for (final String type : _keySet) {
      {
        InitializeVariable sendType = this.factory.createInitializeVariable();
        sendType.setName(type);
        sendType.setExpression(this.helper.call(this.helper.a(), "GetArchetypeChunkComponentType", this.helper.type(type), this.helper.label(this.helper.a(), types.get(type).toString())));
        initializations.add(sendType);
      }
    }
    boolean _makesStructuralChanges = this.makesStructuralChanges(system);
    if (_makesStructuralChanges) {
      InitializeVariable sendBuffer = this.factory.createInitializeVariable();
      sendBuffer.setName("buffer");
      sendBuffer.setExpression(this.helper.call(this.helper.call(this.helper.call(this.helper.label(this.helper.label(this.helper.a(), "World"), "Active"), "GetExistingSystem", this.helper.type("EndSimulationEntityCommandBufferSystem")), "CreateCommandBuffer"), "ToConcurrent"));
      initializations.add(sendBuffer);
    }
    final ArrayList<InitializeVariable> _converted_initializations = (ArrayList<InitializeVariable>)initializations;
    this.helper.call(this.helper.initialize(newJob, "Job", ((InitializeVariable[])Conversions.unwrapArray(_converted_initializations, InitializeVariable.class))), "Schedule", this.helper.label(this.helper.a(), "dependencies"));
    command.setExpression(newJob);
    return command;
  }
  
  public Member job(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PRIVATE);
    m.cs.Type struct = this.factory.createType();
    struct.setType(TypeEnum.STRUCT);
    struct.setName(this.helper.type("Job"));
    struct.getSuperTypes().add(this.helper.type("IJob"));
    struct.getMembers().addAll(this.jobFields(system));
    struct.getMembers().add(this.execute(system));
    member.setValue(struct);
    return member;
  }
  
  public ArrayList<Member> jobFields(final m.m.System system) {
    ArrayList<Member> result = new ArrayList<Member>();
    List<Loop> loops = EcoreUtil2.<Loop>getAllContentsOfType(system, Loop.class);
    for (final Loop loop : loops) {
      {
        Member member = this.factory.createMember();
        member.setVisibility(Visibility.PUBLIC);
        member.getAnnotations().add(this.helper.annotation("DeallocateOnJobCompletion"));
        Field chunk = this.factory.createField();
        chunk.setType(this.helper.type(this.helper.name("NativeArray"), this.helper.name("ArchetypeChunk")));
        chunk.setName(loop.getEntity());
        member.setValue(chunk);
        result.add(member);
      }
    }
    HashMap<String, Boolean> types = this.typesIn(system);
    Set<String> _keySet = types.keySet();
    for (final String type : _keySet) {
      {
        Member member = this.factory.createMember();
        member.setVisibility(Visibility.PUBLIC);
        Boolean _get = types.get(type);
        if ((_get).booleanValue()) {
          member.getAnnotations().add(this.helper.annotation("ReadOnly"));
        }
        Field declareType = this.factory.createField();
        declareType.setType(this.helper.type(this.helper.name("ArchetypeChunkComponentType"), this.helper.name(type)));
        declareType.setName(type);
        member.setValue(declareType);
        result.add(member);
      }
    }
    boolean _makesStructuralChanges = this.makesStructuralChanges(system);
    if (_makesStructuralChanges) {
      Member member = this.factory.createMember();
      member.setVisibility(Visibility.PUBLIC);
      Field field = this.factory.createField();
      field.setType(this.helper.type(this.helper.name("EntityCommandBuffer", "Concurrent")));
      field.setName("buffer");
      member.setValue(field);
      result.add(member);
    }
    return result;
  }
  
  public boolean makesStructuralChanges(final m.m.System system) {
    final Function1<Call, String> _function = (Call it) -> {
      return it.getName();
    };
    List<String> structural = ListExtensions.<Call, String>map(EcoreUtil2.<Call>getAllContentsOfType(system, Call.class), _function);
    final Function1<String, Boolean> _function_1 = (String it) -> {
      return Boolean.valueOf(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("add", "remove", "create", "destroy")).contains(it));
    };
    return IterableExtensions.<String>exists(structural, _function_1);
  }
  
  public boolean makesStructuralChanges(final Loop loop) {
    List<Call> calls = EcoreUtil2.<Call>getAllContentsOfType(loop, Call.class);
    for (final Call call : calls) {
      if ((Objects.equal(call.getName(), "add") || Objects.equal(call.getName(), "remove"))) {
        m.m.Expression entity = call.getParameters().get(1);
        if ((entity instanceof m.m.Access)) {
          String _head = IterableExtensions.<String>head(((m.m.Access)entity).getNames());
          String _entity = loop.getEntity();
          boolean _equals = Objects.equal(_head, _entity);
          if (_equals) {
            return true;
          }
        }
      } else {
        String _name = call.getName();
        boolean _equals_1 = Objects.equal(_name, "destroy");
        if (_equals_1) {
          m.m.Expression entity_1 = call.getParameters().get(0);
          if ((entity_1 instanceof m.m.Access)) {
            String _head_1 = IterableExtensions.<String>head(((m.m.Access)entity_1).getNames());
            String _entity_1 = loop.getEntity();
            boolean _equals_2 = Objects.equal(_head_1, _entity_1);
            if (_equals_2) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  public HashMap<String, Boolean> typesIn(final m.m.System system) {
    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    final Function1<m.m.Assignment, Boolean> _function = (m.m.Assignment it) -> {
      int _size = it.getAccess().getNames().size();
      return Boolean.valueOf((_size > 1));
    };
    final Function1<m.m.Assignment, String> _function_1 = (m.m.Assignment it) -> {
      return it.getAccess().getNames().get(1);
    };
    final Iterable<String> writes = IterableExtensions.<m.m.Assignment, String>map(IterableExtensions.<m.m.Assignment>filter(EcoreUtil2.<m.m.Assignment>getAllContentsOfType(system, m.m.Assignment.class), _function), _function_1);
    final Function1<m.m.Access, Boolean> _function_2 = (m.m.Access it) -> {
      int _size = it.getNames().size();
      return Boolean.valueOf((_size > 1));
    };
    final Function1<m.m.Access, Boolean> _function_3 = (m.m.Access it) -> {
      boolean _contains = IterableExtensions.<String>toList(writes).contains(it.getNames().get(1));
      return Boolean.valueOf((!_contains));
    };
    final Function1<m.m.Access, String> _function_4 = (m.m.Access it) -> {
      return it.getNames().get(1);
    };
    Iterable<String> reads = IterableExtensions.<m.m.Access, String>map(IterableExtensions.<m.m.Access>filter(IterableExtensions.<m.m.Access>filter(EcoreUtil2.<m.m.Access>getAllContentsOfType(system, m.m.Access.class), _function_2), _function_3), _function_4);
    Set<String> _set = IterableExtensions.<String>toSet(writes);
    for (final String write : _set) {
      {
        map.put(write, Boolean.valueOf(false));
        boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("position", "rotation", "scale")).contains(write);
        if (_contains) {
          this.usings.add("Unity.Transforms");
        } else {
          boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("mass", "velocity", "acceleration", "force", "restitution", "friction", "extent", "radius")).contains(write);
          if (_contains_1) {
            this.usings.add("Unity.Physics");
          }
        }
      }
    }
    Set<String> _set_1 = IterableExtensions.<String>toSet(reads);
    for (final String read : _set_1) {
      {
        map.put(read, Boolean.valueOf(true));
        boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("position", "rotation", "scale")).contains(read);
        if (_contains) {
          this.usings.add("Unity.Transforms");
        } else {
          boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("mass", "restitution", "friction", "extent", "radius")).contains(read);
          if (_contains_1) {
            this.usings.add("Unity.Physics");
          }
        }
      }
    }
    return map;
  }
  
  public Member execute(final m.m.System system) {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("void"));
    method.setName("Execute");
    method.getCommands().addAll(this.commands(system).getCommands());
    member.setValue(method);
    return member;
  }
  
  public Block commands(final m.m.System system) {
    Block result = this.factory.createBlock();
    EList<Command> _commands = system.getCommands();
    for (final Command command : _commands) {
      this.addCommand(result, command);
    }
    return result;
  }
  
  public void addCommand(final Block list, final Command command) {
    if ((command instanceof Call)) {
      String function = ((Call)command).getName();
      boolean _equals = Objects.equal(function, "remove");
      if (_equals) {
        Assignment call = this.factory.createAssignment();
        Access access = this.helper.label(this.helper.a(), "PostUpdateCommands");
        MethodCall methodCall = this.factory.createMethodCall();
        methodCall.setName("RemoveComponent");
        m.m.Expression _get = ((Call)command).getParameters().get(0);
        methodCall.getGenerics().add(this.helper.type(IterableExtensions.<String>head(((m.m.Access) _get).getNames())));
        methodCall.getExpressions().add(this.toCS(((Call)command).getParameters().get(1)));
        access.getPortions().add(methodCall);
        call.setAccess(access);
        list.getCommands().add(call);
      } else {
        boolean _equals_1 = Objects.equal(function, "add");
        if (_equals_1) {
          Assignment call_1 = this.factory.createAssignment();
          m.m.Expression _get_1 = ((Call)command).getParameters().get(0);
          call_1.setAccess(this.helper.call(this.helper.label(this.helper.a(), "PostUpdateCommands"), "AddComponent", this.helper.type(IterableExtensions.<String>head(((m.m.Access) _get_1).getNames())), this.toCS(((Call)command).getParameters().get(1))));
          list.getCommands().add(call_1);
        } else {
          boolean _equals_2 = Objects.equal(function, "destroy");
          if (_equals_2) {
            Assignment call_2 = this.factory.createAssignment();
            call_2.setAccess(this.helper.call(this.helper.label(this.helper.a(), "PostUpdateCommands"), "DestroyEntity", this.toCS(((Call)command).getParameters().get(0))));
            list.getCommands().add(call_2);
          } else {
            boolean _equals_3 = Objects.equal(function, "create");
            if (_equals_3) {
              Assignment call_3 = this.factory.createAssignment();
              call_3.setAccess(this.helper.call(this.helper.label(this.helper.a(), "PostUpdateCommands"), "Instantiate", this.toCS(((Call)command).getParameters().get(0))));
              list.getCommands().add(call_3);
            }
          }
        }
      }
    } else {
      if ((command instanceof m.m.Assignment)) {
        int _size = ((m.m.Assignment)command).getAccess().getNames().size();
        boolean _equals_4 = (_size == 2);
        if (_equals_4) {
          Assignment created = this.factory.createAssignment();
          Access _a = this.helper.a();
          String _head = IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames());
          String _plus = (_head + "_");
          String _get_2 = ((m.m.Assignment)command).getAccess().getNames().get(1);
          String _plus_1 = (_plus + _get_2);
          created.setAccess(this.helper.label(this.helper.label(_a, _plus_1), this.field(((m.m.Assignment)command).getAccess().getNames().get(1))));
          created.setAssignmentType(this.helper.toCS(((m.m.Assignment)command).getType()));
          created.setExpression(this.toCS(((m.m.Assignment)command).getExpression()));
          list.getCommands().add(created);
        } else {
          int _size_1 = ((m.m.Assignment)command).getAccess().getNames().size();
          boolean _equals_5 = (_size_1 == 1);
          if (_equals_5) {
            Assignment declaration = this.factory.createAssignment();
            EObject container = ((m.m.Assignment)command).eContainer();
            while ((!((container instanceof Loop) || (container instanceof m.m.System)))) {
              container = EcoreUtil2.<Loop>getContainerOfType(container, Loop.class);
            }
            String _head_1 = IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames());
            String _plus_2 = (_head_1 + "@");
            int _hashCode = container.hashCode();
            String hash = (_plus_2 + Integer.valueOf(_hashCode));
            TypeName type = this.helper.type(this.helper.stringName(this.types.get(hash)));
            declaration.setType(type);
            declaration.setAccess(this.helper.label(this.helper.a(), IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames())));
            declaration.setAssignmentType(AssignmentType.SET);
            declaration.setExpression(this.helper.call(this.helper.a(), "default", this.helper.label(this.helper.a(), IterableExtensions.<String>head(type.getName().getNames()))));
            Assignment assignment = EcoreUtil2.<Assignment>getContainerOfType(list, Assignment.class);
            EObject _eContainer = assignment.eContainer();
            Block system = ((Block) _eContainer);
            system.getCommands().add(0, declaration);
            Assignment created_1 = this.factory.createAssignment();
            created_1.setAccess(this.helper.label(this.helper.a(), IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames())));
            created_1.setAssignmentType(this.helper.toCS(((m.m.Assignment)command).getType()));
            created_1.setExpression(this.toCS(((m.m.Assignment)command).getExpression()));
            list.getCommands().add(created_1);
          } else {
            Access newComponent = this.factory.createAccess();
            newComponent.setNew(true);
            this.helper.initialize(newComponent, this.componentName(IterableExtensions.<String>last(((m.m.Assignment)command).getAccess().getNames())), this.helper.initialization(this.field(IterableExtensions.<String>last(((m.m.Assignment)command).getAccess().getNames())), this.toCS(((m.m.Assignment)command).getExpression())));
            Assignment created_2 = this.factory.createAssignment();
            Access _a_1 = this.helper.a();
            String _head_2 = IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames());
            String _plus_3 = ("my_" + _head_2);
            String _plus_4 = (_plus_3 + "_");
            String _get_3 = ((m.m.Assignment)command).getAccess().getNames().get(1);
            String _plus_5 = (_plus_4 + _get_3);
            created_2.setAccess(this.helper.call(this.helper.label(this.helper.a(), "EntityManager"), "SetComponentData", this.helper.label(this.helper.label(_a_1, _plus_5), "Value"), newComponent));
            list.getCommands().add(created_2);
          }
        }
      } else {
        if ((command instanceof Loop)) {
          final Function1<m.m.Assignment, Boolean> _function = (m.m.Assignment it) -> {
            return Boolean.valueOf(((it.getAccess().getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getAccess().getNames()), ((Loop)command).getEntity())));
          };
          final Function1<m.m.Assignment, String> _function_1 = (m.m.Assignment it) -> {
            return it.getAccess().getNames().get(1);
          };
          final Iterable<String> writes = IterableExtensions.<m.m.Assignment, String>map(IterableExtensions.<m.m.Assignment>filter(EcoreUtil2.<m.m.Assignment>getAllContentsOfType(command, m.m.Assignment.class), _function), _function_1);
          final Function1<m.m.Access, Boolean> _function_2 = (m.m.Access it) -> {
            return Boolean.valueOf(((it.getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getNames()), ((Loop)command).getEntity())));
          };
          final Function1<m.m.Access, Boolean> _function_3 = (m.m.Access it) -> {
            boolean _contains = IterableExtensions.<String>toList(writes).contains(it.getNames().get(1));
            return Boolean.valueOf((!_contains));
          };
          final Function1<m.m.Access, String> _function_4 = (m.m.Access it) -> {
            return it.getNames().get(1);
          };
          Iterable<String> reads = IterableExtensions.<m.m.Access, String>map(IterableExtensions.<m.m.Access>filter(IterableExtensions.<m.m.Access>filter(EcoreUtil2.<m.m.Access>getAllContentsOfType(command, m.m.Access.class), _function_2), _function_3), _function_4);
          Lambda lambda = this.factory.createLambda();
          String entity = ((Loop)command).getEntity();
          boolean _makesStructuralChanges = this.makesStructuralChanges(((Loop)command));
          if (_makesStructuralChanges) {
            lambda.getArguments().add(this.helper.argument(this.helper.type("Entity"), entity));
          }
          Set<String> _set = IterableExtensions.<String>toSet(writes);
          for (final String w : _set) {
            boolean _isShared = this.isShared(w);
            if (_isShared) {
              lambda.getArguments().add(0, this.helper.argument(this.helper.type(this.componentName(w)), ((entity + "_") + w)));
              Assignment my = this.factory.createAssignment();
              my.setVar(true);
              my.setAccess(this.helper.label(this.helper.a(), ((("my_" + entity) + "_") + w)));
              my.setExpression(this.helper.label(this.helper.a(), ((entity + "_") + w)));
              lambda.getCommands().add(my);
            } else {
              lambda.getArguments().add(this.helper.refArgument(this.helper.type(this.componentName(w)), ((entity + "_") + w)));
              Assignment my_1 = this.factory.createAssignment();
              my_1.setVar(true);
              my_1.setAccess(this.helper.label(this.helper.a(), ((("my_" + entity) + "_") + w)));
              my_1.setExpression(this.helper.label(this.helper.a(), ((entity + "_") + w)));
              lambda.getCommands().add(my_1);
            }
          }
          Set<String> _set_1 = IterableExtensions.<String>toSet(reads);
          for (final String r : _set_1) {
            boolean _isShared_1 = this.isShared(r);
            if (_isShared_1) {
              lambda.getArguments().add(0, this.helper.argument(this.helper.type(this.componentName(r)), ((entity + "_") + r)));
              Assignment my_2 = this.factory.createAssignment();
              my_2.setVar(true);
              my_2.setAccess(this.helper.label(this.helper.a(), ((("my_" + entity) + "_") + r)));
              my_2.setExpression(this.helper.label(this.helper.a(), ((entity + "_") + r)));
              lambda.getCommands().add(my_2);
            } else {
              lambda.getArguments().add(this.helper.refArgument(this.helper.type(this.componentName(r)), ((entity + "_") + r)));
              Assignment my_3 = this.factory.createAssignment();
              my_3.setVar(true);
              my_3.setAccess(this.helper.label(this.helper.a(), ((("my_" + entity) + "_") + r)));
              my_3.setExpression(this.helper.label(this.helper.a(), ((entity + "_") + r)));
              lambda.getCommands().add(my_3);
            }
          }
          Assignment foreach = this.factory.createAssignment();
          Access access_1 = this.helper.label(this.helper.a(), "Entities");
          EList<String> _constraints = ((Loop)command).getConstraints();
          for (final String constraint : _constraints) {
            {
              MethodCall methodCall_1 = this.factory.createMethodCall();
              methodCall_1.setName("WithAll");
              methodCall_1.getGenerics().add(this.helper.type(constraint));
              access_1.getPortions().add(methodCall_1);
            }
          }
          foreach.setAccess(this.helper.call(access_1, "ForEach", lambda));
          list.getCommands().add(foreach);
          EList<Command> _commands = ((Loop)command).getCommands();
          for (final Command c : _commands) {
            this.addCommand(lambda, c);
          }
        } else {
          if ((command instanceof Branch)) {
            final If branch = this.factory.createIf();
            Condition ifCondition = ((Branch)command).getIf();
            list.getCommands().add(branch);
            branch.setExpression(this.toCS(ifCondition.getCondition()));
            final Consumer<Command> _function_5 = (Command it) -> {
              this.addCommand(branch, it);
            };
            ifCondition.getCommands().forEach(_function_5);
            EList<Condition> _elseIfs = ((Branch)command).getElseIfs();
            for (final Condition elseIf : _elseIfs) {
              {
                final ElseIf csharpElseIf = this.factory.createElseIf();
                Condition elseIfCondition = ((Condition) elseIf);
                csharpElseIf.setExpression(this.toCS(elseIfCondition.getCondition()));
                final Consumer<Command> _function_6 = (Command it) -> {
                  this.addCommand(csharpElseIf, it);
                };
                elseIfCondition.getCommands().forEach(_function_6);
              }
            }
            int _size_2 = ((Branch)command).getCommands().size();
            boolean _greaterThan = (_size_2 > 0);
            if (_greaterThan) {
              final Else csharpElse = this.factory.createElse();
              final Consumer<Command> _function_6 = (Command it) -> {
                this.addCommand(csharpElse, it);
              };
              ((Branch)command).getCommands().forEach(_function_6);
            }
          }
        }
      }
    }
  }
  
  public String componentName(final String name) {
    boolean _equals = Objects.equal(name, "velocity");
    if (_equals) {
      this.usings.add("Unity.Physics");
      return "PhysicsVelocity";
    } else {
      boolean _equals_1 = Objects.equal(name, "position");
      if (_equals_1) {
        this.usings.add("Unity.Transforms");
        return "Translation";
      } else {
        boolean _equals_2 = Objects.equal(name, "rotation");
        if (_equals_2) {
          this.usings.add("Unity.Transforms");
          return "Rotation";
        } else {
          boolean _equals_3 = Objects.equal(name, "angularVelocity");
          if (_equals_3) {
            this.usings.add("Unity.Physics");
            return "PhysicsVelocity";
          } else {
            return name;
          }
        }
      }
    }
  }
  
  public boolean isShared(final String name) {
    return Collections.<Type>unmodifiableList(CollectionLiterals.<Type>newArrayList(Type.input)).contains(this.types.get(name));
  }
  
  public String field(final String name) {
    boolean _equals = Objects.equal(name, "velocity");
    if (_equals) {
      return "Linear";
    } else {
      boolean _equals_1 = Objects.equal(name, "angularVelocity");
      if (_equals_1) {
        return "Angular";
      } else {
        return "Value";
      }
    }
  }
  
  public void addCommandOld(final List<m.cs.Command> list, final Command command) {
    if ((command instanceof Call)) {
    } else {
      if ((command instanceof m.m.Assignment)) {
        Assignment created = this.factory.createAssignment();
        Access _a = this.helper.a();
        String _head = IterableExtensions.<String>head(((m.m.Assignment)command).getAccess().getNames());
        String _plus = (_head + "_");
        String _get = ((m.m.Assignment)command).getAccess().getNames().get(1);
        String _plus_1 = (_plus + _get);
        created.setAccess(this.helper.label(_a, _plus_1));
        for (int i = 2; (i < ((m.m.Assignment)command).getAccess().getNames().size()); i++) {
          this.helper.label(created.getAccess(), ((m.m.Assignment)command).getAccess().getNames().get(i));
        }
        created.setAssignmentType(this.helper.toCS(((m.m.Assignment)command).getType()));
        created.setExpression(this.toCS(((m.m.Assignment)command).getExpression()));
        list.add(created);
      } else {
        if ((command instanceof Loop)) {
          For outer = this.factory.createFor();
          Assignment initialize_out = this.factory.createAssignment();
          initialize_out.setVar(true);
          Access _a_1 = this.helper.a();
          String _entity = ((Loop)command).getEntity();
          String _plus_2 = (_entity + "_out");
          initialize_out.setAccess(this.helper.label(_a_1, _plus_2));
          initialize_out.setExpression(this.helper.number(0));
          outer.setInitialization(initialize_out);
          Comparison condition = this.factory.createComparison();
          Access _a_2 = this.helper.a();
          String _entity_1 = ((Loop)command).getEntity();
          String _plus_3 = (_entity_1 + "_out");
          condition.setLeft(this.helper.label(_a_2, _plus_3));
          condition.setType(RelationType.UNDER);
          condition.setRight(this.helper.label(this.helper.label(this.helper.a(), ((Loop)command).getEntity()), "Length"));
          outer.setCondition(condition);
          Assignment increment = this.factory.createAssignment();
          Access _a_3 = this.helper.a();
          String _entity_2 = ((Loop)command).getEntity();
          String _plus_4 = (_entity_2 + "_out");
          increment.setAccess(this.helper.label(_a_3, _plus_4));
          increment.setIncrement(true);
          outer.setIncrement(increment);
          outer.getCommands().add(this.getChunk(((Loop)command).getEntity()));
          outer.getCommands().addAll(this.getArrays(((Loop)command)));
          For inner = this.factory.createFor();
          Assignment initialize_inner = this.factory.createAssignment();
          initialize_inner.setVar(true);
          Access _a_4 = this.helper.a();
          String _entity_3 = ((Loop)command).getEntity();
          String _plus_5 = (_entity_3 + "_in");
          initialize_inner.setAccess(this.helper.label(_a_4, _plus_5));
          initialize_inner.setExpression(this.helper.number(0));
          inner.setInitialization(initialize_inner);
          Comparison condition_inner = this.factory.createComparison();
          Access _a_5 = this.helper.a();
          String _entity_4 = ((Loop)command).getEntity();
          String _plus_6 = (_entity_4 + "_in");
          condition_inner.setLeft(this.helper.label(_a_5, _plus_6));
          condition_inner.setType(RelationType.UNDER);
          Access _a_6 = this.helper.a();
          String _entity_5 = ((Loop)command).getEntity();
          String _plus_7 = (_entity_5 + "_chunk");
          condition_inner.setRight(this.helper.label(this.helper.label(_a_6, _plus_7), "Count"));
          inner.setCondition(condition_inner);
          Assignment increment_inner = this.factory.createAssignment();
          Access _a_7 = this.helper.a();
          String _entity_6 = ((Loop)command).getEntity();
          String _plus_8 = (_entity_6 + "_in");
          increment_inner.setAccess(this.helper.label(_a_7, _plus_8));
          increment_inner.setIncrement(true);
          inner.setIncrement(increment_inner);
          outer.getCommands().add(inner);
          inner.getCommands().addAll(this.getDatas(((Loop)command)));
          EList<Command> _commands = ((Loop)command).getCommands();
          for (final Command c : _commands) {
            this.addCommand(inner, c);
          }
          list.add(outer);
        } else {
          if ((command instanceof Branch)) {
          }
        }
      }
    }
  }
  
  public Assignment getChunk(final String name) {
    Assignment assignment = this.factory.createAssignment();
    assignment.setVar(true);
    assignment.setAccess(this.helper.label(this.helper.a(), (name + "_chunk")));
    assignment.setAssignmentType(AssignmentType.SET);
    assignment.setExpression(this.helper.index(this.helper.a(), name, this.helper.label(this.helper.a(), (name + "_out"))));
    return assignment;
  }
  
  public ArrayList<m.cs.Command> getArrays(final Loop loop) {
    String group = loop.getEntity();
    ArrayList<m.cs.Command> result = new ArrayList<m.cs.Command>();
    final Function1<m.m.Assignment, Boolean> _function = (m.m.Assignment it) -> {
      return Boolean.valueOf(((it.getAccess().getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getAccess().getNames()), loop.getEntity())));
    };
    final Function1<m.m.Assignment, String> _function_1 = (m.m.Assignment it) -> {
      return it.getAccess().getNames().get(1);
    };
    final Iterable<String> writes = IterableExtensions.<m.m.Assignment, String>map(IterableExtensions.<m.m.Assignment>filter(EcoreUtil2.<m.m.Assignment>getAllContentsOfType(loop, m.m.Assignment.class), _function), _function_1);
    final Function1<m.m.Access, Boolean> _function_2 = (m.m.Access it) -> {
      return Boolean.valueOf(((it.getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getNames()), loop.getEntity())));
    };
    final Function1<m.m.Access, Boolean> _function_3 = (m.m.Access it) -> {
      boolean _contains = IterableExtensions.<String>toList(writes).contains(it.getNames().get(1));
      return Boolean.valueOf((!_contains));
    };
    final Function1<m.m.Access, String> _function_4 = (m.m.Access it) -> {
      return it.getNames().get(1);
    };
    Iterable<String> reads = IterableExtensions.<m.m.Access, String>map(IterableExtensions.<m.m.Access>filter(IterableExtensions.<m.m.Access>filter(EcoreUtil2.<m.m.Access>getAllContentsOfType(loop, m.m.Access.class), _function_2), _function_3), _function_4);
    Iterable<String> _plus = Iterables.<String>concat(writes, reads);
    for (final String type : _plus) {
      {
        Assignment assignment = this.factory.createAssignment();
        assignment.setVar(true);
        assignment.setAccess(this.helper.label(this.helper.a(), (((group + "_") + type) + "_array")));
        assignment.setAssignmentType(AssignmentType.SET);
        assignment.setExpression(this.helper.call(this.helper.label(this.helper.a(), (group + "_chunk")), "GetNativeArray", this.helper.label(this.helper.a(), type)));
        result.add(assignment);
      }
    }
    return result;
  }
  
  public ArrayList<m.cs.Command> getDatas(final Loop loop) {
    String group = loop.getEntity();
    ArrayList<m.cs.Command> result = new ArrayList<m.cs.Command>();
    final Function1<m.m.Assignment, Boolean> _function = (m.m.Assignment it) -> {
      return Boolean.valueOf(((it.getAccess().getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getAccess().getNames()), loop.getEntity())));
    };
    final Function1<m.m.Assignment, String> _function_1 = (m.m.Assignment it) -> {
      return it.getAccess().getNames().get(1);
    };
    final Iterable<String> writes = IterableExtensions.<m.m.Assignment, String>map(IterableExtensions.<m.m.Assignment>filter(EcoreUtil2.<m.m.Assignment>getAllContentsOfType(loop, m.m.Assignment.class), _function), _function_1);
    final Function1<m.m.Access, Boolean> _function_2 = (m.m.Access it) -> {
      return Boolean.valueOf(((it.getNames().size() > 1) && Objects.equal(IterableExtensions.<String>head(it.getNames()), loop.getEntity())));
    };
    final Function1<m.m.Access, Boolean> _function_3 = (m.m.Access it) -> {
      boolean _contains = IterableExtensions.<String>toList(writes).contains(it.getNames().get(1));
      return Boolean.valueOf((!_contains));
    };
    final Function1<m.m.Access, String> _function_4 = (m.m.Access it) -> {
      return it.getNames().get(1);
    };
    Iterable<String> reads = IterableExtensions.<m.m.Access, String>map(IterableExtensions.<m.m.Access>filter(IterableExtensions.<m.m.Access>filter(EcoreUtil2.<m.m.Access>getAllContentsOfType(loop, m.m.Access.class), _function_2), _function_3), _function_4);
    Iterable<String> _plus = Iterables.<String>concat(writes, reads);
    for (final String type : _plus) {
      {
        Assignment assignment = this.factory.createAssignment();
        assignment.setVar(true);
        assignment.setAccess(this.helper.label(this.helper.a(), ((group + "_") + type)));
        assignment.setAssignmentType(AssignmentType.SET);
        assignment.setExpression(this.helper.index(this.helper.a(), (((group + "_") + type) + "_array"), this.helper.label(this.helper.a(), (group + "_in"))));
        result.add(assignment);
      }
    }
    return result;
  }
  
  public Expression toCS(final m.m.Expression expression) {
    if ((expression instanceof Or)) {
      m.cs.Or created = this.factory.createOr();
      created.setLeft(this.toCS(((Or)expression).getLeft()));
      created.setRight(this.toCS(((Or)expression).getRight()));
      return created;
    } else {
      if ((expression instanceof And)) {
        m.cs.And created_1 = this.factory.createAnd();
        created_1.setLeft(this.toCS(((And)expression).getLeft()));
        created_1.setRight(this.toCS(((And)expression).getRight()));
        return created_1;
      } else {
        if ((expression instanceof Not)) {
          m.cs.And created_2 = this.factory.createAnd();
          created_2.setLeft(this.toCS(((Not)expression).getExpression()));
          return created_2;
        } else {
          if ((expression instanceof Brackets)) {
            m.cs.Brackets created_3 = this.factory.createBrackets();
            created_3.setExpression(this.toCS(((Brackets)expression).getExpression()));
            return created_3;
          } else {
            if ((expression instanceof m.m.Comparison)) {
              Comparison created_4 = this.factory.createComparison();
              created_4.setLeft(this.toCS(((m.m.Comparison)expression).getLeft()));
              created_4.setRight(this.toCS(((m.m.Comparison)expression).getRight()));
              created_4.setType(this.toCS(((m.m.Comparison)expression).getType()));
              return created_4;
            } else {
              if ((expression instanceof Plus)) {
                m.cs.Plus created_5 = this.factory.createPlus();
                created_5.setLeft(this.toCS(((Plus)expression).getLeft()));
                created_5.setRight(this.toCS(((Plus)expression).getRight()));
                return created_5;
              } else {
                if ((expression instanceof Minus)) {
                  m.cs.Minus created_6 = this.factory.createMinus();
                  created_6.setLeft(this.toCS(((Minus)expression).getLeft()));
                  created_6.setRight(this.toCS(((Minus)expression).getRight()));
                  return created_6;
                } else {
                  if ((expression instanceof Times)) {
                    m.cs.Times created_7 = this.factory.createTimes();
                    created_7.setLeft(this.toCS(((Times)expression).getLeft()));
                    created_7.setRight(this.toCS(((Times)expression).getRight()));
                    return created_7;
                  } else {
                    if ((expression instanceof Divide)) {
                      m.cs.Divide created_8 = this.factory.createDivide();
                      created_8.setLeft(this.toCS(((Divide)expression).getLeft()));
                      created_8.setRight(this.toCS(((Divide)expression).getRight()));
                      return created_8;
                    } else {
                      if ((expression instanceof Modulus)) {
                        m.cs.Modulus created_9 = this.factory.createModulus();
                        created_9.setLeft(this.toCS(((Modulus)expression).getLeft()));
                        created_9.setRight(this.toCS(((Modulus)expression).getRight()));
                        return created_9;
                      } else {
                        if ((expression instanceof Increment)) {
                          m.cs.Increment created_10 = this.factory.createIncrement();
                          created_10.setLeft(this.toCS(((Increment)expression).getExpression()));
                          return created_10;
                        } else {
                          if ((expression instanceof Decrement)) {
                            m.cs.Decrement created_11 = this.factory.createDecrement();
                            created_11.setLeft(this.toCS(((Decrement)expression).getExpression()));
                            return created_11;
                          } else {
                            if ((expression instanceof m.m.Access)) {
                              int _size = ((m.m.Access)expression).getNames().size();
                              boolean _equals = (_size == 2);
                              if (_equals) {
                                String name = ((m.m.Access)expression).getNames().get(1);
                                Access _a = this.helper.a();
                                String _head = IterableExtensions.<String>head(((m.m.Access)expression).getNames());
                                String _plus = ("my_" + _head);
                                String _plus_1 = (_plus + "_");
                                String _plus_2 = (_plus_1 + name);
                                Access access = this.helper.label(_a, _plus_2);
                                return this.helper.label(access, "Value");
                              } else {
                                int _size_1 = ((m.m.Access)expression).getNames().size();
                                boolean _equals_1 = (_size_1 == 1);
                                if (_equals_1) {
                                  return this.helper.label(this.helper.a(), IterableExtensions.<String>head(((m.m.Access)expression).getNames()));
                                } else {
                                  Access _a_1 = this.helper.a();
                                  String _head_1 = IterableExtensions.<String>head(((m.m.Access)expression).getNames());
                                  String _plus_3 = ("my_" + _head_1);
                                  String _plus_4 = (_plus_3 + "_");
                                  String _get = ((m.m.Access)expression).getNames().get(1);
                                  String _plus_5 = (_plus_4 + _get);
                                  return this.helper.call(this.helper.label(this.helper.a(), "EntityManager"), "GetComponentData", this.helper.type(IterableExtensions.<String>last(((m.m.Access)expression).getNames())), this.helper.label(this.helper.label(_a_1, _plus_5), "Value"));
                                }
                              }
                            } else {
                              if ((expression instanceof Call)) {
                                String function = ((Call)expression).getName();
                                boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("sin", "cos", "tan", "log", "sqrt")).contains(function);
                                if (_contains) {
                                  final Function1<m.m.Expression, Expression> _function = (m.m.Expression it) -> {
                                    return this.toCS(it);
                                  };
                                  return this.helper.call(this.helper.label(this.helper.a(), "math"), function, ((Expression[])Conversions.unwrapArray(ListExtensions.<m.m.Expression, Expression>map(((Call)expression).getParameters(), _function), Expression.class)));
                                } else {
                                  boolean _equals_2 = Objects.equal(function, "join");
                                  if (_equals_2) {
                                    this.usings.add("Unity.Mathematics");
                                    String newType = "float";
                                    int _size_2 = ((Call)expression).getParameters().size();
                                    boolean _equals_3 = (_size_2 == 2);
                                    if (_equals_3) {
                                      String _newType = newType;
                                      newType = (_newType + "2");
                                    } else {
                                      int _size_3 = ((Call)expression).getParameters().size();
                                      boolean _equals_4 = (_size_3 == 3);
                                      if (_equals_4) {
                                        String _newType_1 = newType;
                                        newType = (_newType_1 + "3");
                                      } else {
                                        String _newType_2 = newType;
                                        newType = (_newType_2 + "4");
                                      }
                                    }
                                    final Function1<m.m.Expression, Expression> _function_1 = (m.m.Expression it) -> {
                                      return this.toCS(it);
                                    };
                                    Access call = this.helper.call(this.helper.a(), newType, ((Expression[])Conversions.unwrapArray(ListExtensions.<m.m.Expression, Expression>map(((Call)expression).getParameters(), _function_1), Expression.class)));
                                    call.setNew(true);
                                    return call;
                                  } else {
                                    boolean _equals_5 = Objects.equal(function, "random");
                                    if (_equals_5) {
                                      this.usings.add("Unity.Mathematics");
                                      Expression _cS = this.toCS(((Call)expression).getParameters().get(0));
                                      Access e = ((Access) _cS);
                                      Expression _cS_1 = this.toCS(((Call)expression).getParameters().get(0));
                                      Access _label = this.helper.label(((Access) _cS_1), "x");
                                      Access _label_1 = this.helper.label(e, "y");
                                      return this.helper.call(this.helper.label(this.helper.label(this.helper.a(), "UnityEngine"), "Random"), "Range", new Expression[] { _label, _label_1 });
                                    } else {
                                      boolean _equals_6 = Objects.equal(function, "has");
                                      if (_equals_6) {
                                        m.m.Expression _get_1 = ((Call)expression).getParameters().get(1);
                                        Access call_1 = this.helper.call(this.helper.label(this.helper.a(), "EntityManager"), "HasComponent", this.helper.type(IterableExtensions.<String>head(((m.m.Access) _get_1).getNames())), this.toCS(((Call)expression).getParameters().get(0)));
                                        return call_1;
                                      } else {
                                        final Function1<m.m.Expression, Expression> _function_2 = (m.m.Expression it) -> {
                                          return this.toCS(it);
                                        };
                                        return this.helper.call(this.helper.a(), ((Call)expression).getName(), ((Expression[])Conversions.unwrapArray(ListExtensions.<m.m.Expression, Expression>map(((Call)expression).getParameters(), _function_2), Expression.class)));
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  public Access toCS(final m.m.Access access) {
    String name = access.getNames().get(1);
    Access _a = this.helper.a();
    String _head = IterableExtensions.<String>head(access.getNames());
    String _plus = (_head + "_");
    String _plus_1 = (_plus + name);
    Access result = this.helper.label(_a, _plus_1);
    return this.helper.label(result, "Value");
  }
  
  public RelationType toCS(final m.m.RelationType type) {
    RelationType _switchResult = null;
    if (type != null) {
      switch (type) {
        case EQUAL:
          _switchResult = RelationType.EQUAL;
          break;
        case NOTEQUAL:
          _switchResult = RelationType.NOTEQUAL;
          break;
        case OVER:
          _switchResult = RelationType.OVER;
          break;
        case OVEROREQUAL:
          _switchResult = RelationType.OVEROREQUAL;
          break;
        case UNDER:
          _switchResult = RelationType.UNDER;
          break;
        case UNDEROREQUAL:
          _switchResult = RelationType.UNDEROREQUAL;
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  public File contactSystem() {
    File system = this.factory.createFile();
    this.helper.addUsing(system, "Unity", "Entities");
    this.helper.addUsing(system, "Unity", "Jobs");
    this.helper.addUsing(system, "Unity", "Physics");
    this.helper.addUsing(system, "Unity", "Physics", "Systems");
    this.helper.addUsing(system, "Unity", "Mathematics");
    system.getTypes().add(this.contactClass());
    system.getTypes().add(this.contactA());
    system.getTypes().add(this.contactB());
    return system;
  }
  
  public Member contactClass() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    member.getAnnotations().add(this.helper.annotation("AlwaysUpdateSystem"));
    m.cs.Type class_ = this.factory.createType();
    class_.setName(this.helper.type("Contacts"));
    class_.getSuperTypes().add(this.helper.type("ComponentSystem"));
    class_.getMembers().add(this.contactUpdate());
    class_.getMembers().add(this.contactCallback());
    class_.getMembers().add(this.contactJob());
    member.setValue(class_);
    return member;
  }
  
  public Member contactUpdate() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PROTECTED);
    Method method = this.factory.createMethod();
    method.setOverride(true);
    method.setType(this.helper.type("void"));
    method.setName("OnUpdate");
    Lambda lambda = this.factory.createLambda();
    lambda.getArguments().add(this.helper.argument(this.helper.type("Entity"), "a"));
    lambda.getArguments().add(this.helper.refArgument(this.helper.type("a"), "something"));
    Assignment destroy = this.factory.createAssignment();
    destroy.setAccess(this.helper.call(this.helper.label(this.helper.a(), "PostUpdateCommands"), "DestroyEntity", this.helper.label(this.helper.a(), "a")));
    lambda.getCommands().add(destroy);
    Assignment foreach = this.factory.createAssignment();
    foreach.setAccess(this.helper.call(this.helper.label(this.helper.a(), "Entities"), "ForEach", lambda));
    method.getCommands().add(foreach);
    Assignment step = this.factory.createAssignment();
    step.setVar(true);
    step.setAccess(this.helper.label(this.helper.a(), "step"));
    step.setAssignmentType(AssignmentType.SET);
    step.setExpression(this.helper.call(this.helper.label(this.helper.label(this.helper.a(), "World"), "Active"), "GetExistingSystem", this.helper.type("StepPhysicsWorld")));
    Assignment enqueue = this.factory.createAssignment();
    enqueue.setAccess(this.helper.call(this.helper.label(this.helper.a(), "step"), "EnqueueCallback", this.helper.label(this.helper.label(this.helper.label(this.helper.a(), "SimulationCallbacks"), "Phase"), "PostCreateContacts"), this.helper.label(this.helper.a(), "callback")));
    method.getCommands().add(step);
    method.getCommands().add(enqueue);
    member.setValue(method);
    return member;
  }
  
  public Member contactCallback() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PRIVATE);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("JobHandle"));
    method.setName("callback");
    method.getArguments().add(this.helper.refArgument(this.helper.type("ISimulation"), "simulation"));
    method.getArguments().add(this.helper.refArgument(this.helper.type("PhysicsWorld"), "world"));
    method.getArguments().add(this.helper.argument(this.helper.type("JobHandle"), "dependencies"));
    Assignment getHandle = this.factory.createAssignment();
    getHandle.setVar(true);
    getHandle.setAccess(this.helper.label(this.helper.a(), "handle"));
    getHandle.setAssignmentType(AssignmentType.SET);
    Access expression = this.factory.createAccess();
    expression.setNew(true);
    Access refWorld = this.factory.createAccess();
    refWorld.setRef(true);
    this.helper.label(refWorld, "world");
    this.helper.call(this.helper.initialize(expression, "Job", this.helper.initialization("buffer", this.helper.call(this.helper.call(this.helper.call(this.helper.label(this.helper.label(this.helper.a(), "World"), "Active"), "GetExistingSystem", this.helper.type("EndSimulationEntityCommandBufferSystem")), "CreateCommandBuffer"), "ToConcurrent"))), "Schedule", this.helper.label(this.helper.a(), "simulation"), refWorld, this.helper.label(this.helper.a(), "dependencies"));
    getHandle.setExpression(expression);
    method.getCommands().add(getHandle);
    Assignment complete = this.factory.createAssignment();
    complete.setAccess(this.helper.call(this.helper.label(this.helper.a(), "handle"), "Complete"));
    method.getCommands().add(complete);
    Return returns = this.factory.createReturn();
    returns.setExpression(this.helper.label(this.helper.a(), "handle"));
    method.getCommands().add(returns);
    member.setValue(method);
    return member;
  }
  
  public Member contactJob() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PRIVATE);
    m.cs.Type struct = this.factory.createType();
    struct.setType(TypeEnum.STRUCT);
    struct.setName(this.helper.type("Job"));
    struct.getSuperTypes().add(this.helper.type("IContactsJob"));
    struct.getMembers().add(this.buffer());
    struct.getMembers().add(this.executeContacts());
    member.setValue(struct);
    return member;
  }
  
  public Member buffer() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Field field = this.factory.createField();
    field.setType(this.helper.type(this.helper.name("EntityCommandBuffer", "Concurrent")));
    field.setName("buffer");
    member.setValue(field);
    return member;
  }
  
  public Member executeContacts() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Method method = this.factory.createMethod();
    method.setType(this.helper.type("void"));
    method.setName("Execute");
    method.getArguments().add(this.helper.refArgument(this.helper.type("ModifiableContactHeader"), "header"));
    method.getArguments().add(this.helper.refArgument(this.helper.type("ModifiableContactPoint"), "contact"));
    Assignment getEntity = this.factory.createAssignment();
    getEntity.setVar(true);
    getEntity.setAccess(this.helper.label(this.helper.a(), "entity"));
    getEntity.setAssignmentType(AssignmentType.SET);
    getEntity.setExpression(this.helper.call(this.helper.label(this.helper.a(), "buffer"), "CreateEntity", this.helper.number(0)));
    Access newA = this.factory.createAccess();
    newA.setNew(true);
    this.helper.initialize(newA, "a", this.helper.initialization("Value", this.helper.label(this.helper.label(this.helper.label(this.helper.a(), "header"), "Entities"), "EntityA")));
    Access newB = this.factory.createAccess();
    newB.setNew(true);
    this.helper.initialize(newB, "b", this.helper.initialization("Value", this.helper.label(this.helper.label(this.helper.label(this.helper.a(), "header"), "Entities"), "EntityB")));
    Assignment createA = this.factory.createAssignment();
    createA.setAccess(this.helper.call(this.helper.label(this.helper.a(), "buffer"), "AddComponent", this.helper.number(0), this.helper.label(this.helper.a(), "entity"), newA));
    Assignment createB = this.factory.createAssignment();
    createB.setAccess(this.helper.call(this.helper.label(this.helper.a(), "buffer"), "AddComponent", this.helper.number(0), this.helper.label(this.helper.a(), "entity"), newB));
    method.getCommands().add(getEntity);
    method.getCommands().add(createA);
    method.getCommands().add(createB);
    member.setValue(method);
    return member;
  }
  
  public Member contactA() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    m.cs.Type struct = this.factory.createType();
    struct.setType(TypeEnum.STRUCT);
    struct.setName(this.helper.type("a"));
    struct.getSuperTypes().add(this.helper.type("IComponentData"));
    struct.getMembers().add(this.entityValue());
    member.setValue(struct);
    return member;
  }
  
  public Member contactB() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    m.cs.Type struct = this.factory.createType();
    struct.setType(TypeEnum.STRUCT);
    struct.setName(this.helper.type("b"));
    struct.getSuperTypes().add(this.helper.type("IComponentData"));
    struct.getMembers().add(this.entityValue());
    member.setValue(struct);
    return member;
  }
  
  public Member entityValue() {
    Member member = this.factory.createMember();
    member.setVisibility(Visibility.PUBLIC);
    Field field = this.factory.createField();
    field.setType(this.helper.type("Entity"));
    field.setName("Value");
    member.setValue(field);
    return member;
  }
}
