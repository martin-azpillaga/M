package m.transformation;

import com.google.common.base.Objects;
import java.util.Collections;
import java.util.List;
import m.BlocksRuntimeModule;
import m.cBlocks.Attribute;
import m.cBlocks.CBlocksFactory;
import m.cBlocks.Element;
import m.cBlocks.File;
import m.m.Access;
import m.m.And;
import m.m.Assignment;
import m.m.AssignmentType;
import m.m.BitwiseNegate;
import m.m.Brackets;
import m.m.Branch;
import m.m.Call;
import m.m.Command;
import m.m.Comparison;
import m.m.Component;
import m.m.Decrement;
import m.m.Divide;
import m.m.Entity;
import m.m.Expression;
import m.m.Game;
import m.m.Increment;
import m.m.Loop;
import m.m.Minus;
import m.m.Not;
import m.m.Or;
import m.m.Plus;
import m.m.RelationType;
import m.m.Times;
import m.m.Value;
import m.m.Vector;
import m.m.Word;
import m.transformation.FactoryHelper;
import m.transformation.GenericSerializer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class BlockGenerator {
  @Extension
  private CBlocksFactory factory = CBlocksFactory.eINSTANCE;
  
  @Extension
  private FactoryHelper helper = FactoryHelper.eINSTANCE();
  
  private int x = (-150);
  
  public void generate(final Game game, final IFileSystemAccess2 fsa, final String... folders) {
    File file = this.factory.createFile();
    Element _createElement = this.factory.createElement();
    final Procedure1<Element> _function = (Element it) -> {
      it.setOpen("xml");
      it.setClose("xml");
      EList<Attribute> _attributes = it.getAttributes();
      Attribute _createAttribute = this.factory.createAttribute();
      final Procedure1<Attribute> _function_1 = (Attribute it_1) -> {
        it_1.setName("xmlns");
        it_1.setValue("http://www.w3.org/1999/xhtml");
      };
      Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function_1);
      _attributes.add(_doubleArrow);
    };
    Element xmlTag = ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
    file.getElements().add(xmlTag);
    EList<Entity> _entities = game.getEntities();
    for (final Entity entity : _entities) {
      {
        xmlTag.getElements().add(this.compile(entity));
        int _x = this.x;
        this.x = (_x - 300);
      }
    }
    this.x = 150;
    EList<m.m.System> _systems = game.getSystems();
    for (final m.m.System system : _systems) {
      {
        xmlTag.getElements().add(this.compile(system));
        int _x = this.x;
        this.x = (_x + 400);
      }
    }
    for (final String folder : folders) {
      BlocksRuntimeModule _blocksRuntimeModule = new BlocksRuntimeModule();
      GenericSerializer.generate(file, _blocksRuntimeModule, fsa, (folder + "main-gen.blocks"));
    }
  }
  
  public Element compile(final Entity entity) {
    Element block = this.block("entity", Integer.valueOf(entity.hashCode()).toString(), Integer.valueOf(this.x).toString(), "0");
    block.getElements().add(this.field("name", IterableExtensions.join(entity.getName(), " ")));
    Element components = this.statement("components");
    block.getElements().add(components);
    components.getElements().add(this.compile(entity.getComponents(), 0));
    return block;
  }
  
  public Element compile(final List<Component> components, final int index) {
    final Component current = components.get(index);
    Element block = this.block(this.type(current), Integer.valueOf(current.hashCode()).toString());
    block.getElements().add(this.field("name", current.getName()));
    Value value = current.getValue();
    List<String> names = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("x", "y", "z", "w"));
    Element valueValue = this.value("value");
    block.getElements().add(valueValue);
    if ((value instanceof Vector)) {
      Element valueBlock = this.block(this.componentType(((Vector)value)), Integer.valueOf(((Vector)value).hashCode()).toString());
      valueValue.getElements().add(valueBlock);
      for (int n = 0; (n < ((Vector)value).getNumbers().size()); n++) {
        {
          Float number = ((Vector)value).getNumbers().get(n);
          valueBlock.getElements().add(this.field(names.get(n), number.toString()));
        }
      }
    } else {
      if ((value instanceof Word)) {
        Element valueBlock_1 = this.block("wordvalue", Integer.valueOf(((Word)value).hashCode()).toString());
        valueValue.getElements().add(valueBlock_1);
        valueBlock_1.getElements().add(this.field("name", IterableExtensions.join(((Word)value).getValues(), " ")));
      }
    }
    int _size = components.size();
    boolean _greaterThan = (_size > (index + 1));
    if (_greaterThan) {
      Element _createElement = this.factory.createElement();
      final Procedure1<Element> _function = (Element it) -> {
        it.setOpen("next");
        it.setClose("next");
      };
      Element next = ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
      next.getElements().add(this.compile(components, (index + 1)));
      block.getElements().add(next);
    }
    return block;
  }
  
  public String componentType(final Vector v) {
    String _xifexpression = null;
    int _size = v.getNumbers().size();
    boolean _equals = (_size == 1);
    if (_equals) {
      _xifexpression = "real1value";
    } else {
      String _xifexpression_1 = null;
      int _size_1 = v.getNumbers().size();
      boolean _equals_1 = (_size_1 == 2);
      if (_equals_1) {
        _xifexpression_1 = "real2value";
      } else {
        String _xifexpression_2 = null;
        int _size_2 = v.getNumbers().size();
        boolean _equals_2 = (_size_2 == 3);
        if (_equals_2) {
          _xifexpression_2 = "real3value";
        } else {
          String _xifexpression_3 = null;
          int _size_3 = v.getNumbers().size();
          boolean _equals_3 = (_size_3 == 4);
          if (_equals_3) {
            _xifexpression_3 = "real4value";
          } else {
            _xifexpression_3 = "error";
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public String type(final Component component) {
    Value value = component.getValue();
    if ((value == null)) {
      return "tagcomponent";
    } else {
      return "valuedcomponent";
    }
  }
  
  public Element field(final String myName, final String myText) {
    Element _createElement = this.factory.createElement();
    final Procedure1<Element> _function = (Element it) -> {
      it.setOpen("field");
      it.setClose("field");
      EList<Attribute> _attributes = it.getAttributes();
      Attribute _createAttribute = this.factory.createAttribute();
      final Procedure1<Attribute> _function_1 = (Attribute it_1) -> {
        it_1.setName("name");
        it_1.setValue(myName);
      };
      Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function_1);
      _attributes.add(_doubleArrow);
      it.setText(myText);
    };
    return ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
  }
  
  public Element block(final String myType, final String myId) {
    Element _createElement = this.factory.createElement();
    final Procedure1<Element> _function = (Element it) -> {
      it.setOpen("block");
      it.setClose("block");
      Attribute _createAttribute = this.factory.createAttribute();
      final Procedure1<Attribute> _function_1 = (Attribute it_1) -> {
        it_1.setName("type");
        it_1.setValue(myType);
      };
      Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function_1);
      Attribute _createAttribute_1 = this.factory.createAttribute();
      final Procedure1<Attribute> _function_2 = (Attribute it_1) -> {
        it_1.setName("id");
        it_1.setValue(myId);
      };
      Attribute _doubleArrow_1 = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute_1, _function_2);
      it.getAttributes().addAll(
        Collections.<Attribute>unmodifiableList(CollectionLiterals.<Attribute>newArrayList(_doubleArrow, _doubleArrow_1)));
    };
    return ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
  }
  
  public Element block(final String myType, final String myId, final String myX, final String myY) {
    Element block = this.block(myType, myId);
    EList<Attribute> _attributes = block.getAttributes();
    Attribute _createAttribute = this.factory.createAttribute();
    final Procedure1<Attribute> _function = (Attribute it) -> {
      it.setName("x");
      it.setValue(myX);
    };
    Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function);
    _attributes.add(_doubleArrow);
    EList<Attribute> _attributes_1 = block.getAttributes();
    Attribute _createAttribute_1 = this.factory.createAttribute();
    final Procedure1<Attribute> _function_1 = (Attribute it) -> {
      it.setName("y");
      it.setValue(myY);
    };
    Attribute _doubleArrow_1 = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute_1, _function_1);
    _attributes_1.add(_doubleArrow_1);
    return block;
  }
  
  public Element value(final String myName) {
    Element _createElement = this.factory.createElement();
    final Procedure1<Element> _function = (Element it) -> {
      it.setOpen("value");
      it.setClose("value");
      EList<Attribute> _attributes = it.getAttributes();
      Attribute _createAttribute = this.factory.createAttribute();
      final Procedure1<Attribute> _function_1 = (Attribute it_1) -> {
        it_1.setName("name");
        it_1.setValue(myName);
      };
      Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function_1);
      _attributes.add(_doubleArrow);
    };
    return ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
  }
  
  public Element statement(final String myName) {
    Element _createElement = this.factory.createElement();
    final Procedure1<Element> _function = (Element it) -> {
      it.setOpen("statement");
      it.setClose("statement");
      EList<Attribute> _attributes = it.getAttributes();
      Attribute _createAttribute = this.factory.createAttribute();
      final Procedure1<Attribute> _function_1 = (Attribute it_1) -> {
        it_1.setName("name");
        it_1.setValue(myName);
      };
      Attribute _doubleArrow = ObjectExtensions.<Attribute>operator_doubleArrow(_createAttribute, _function_1);
      _attributes.add(_doubleArrow);
    };
    return ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
  }
  
  public Element compile(final m.m.System system) {
    Element block = this.block("system", Integer.valueOf(system.hashCode()).toString(), Integer.valueOf(this.x).toString(), "0");
    block.getElements().add(this.field("name", system.getName()));
    Element commands = this.statement("commands");
    block.getElements().add(commands);
    commands.getElements().add(this.compileCommands(system.getCommands(), 0));
    return block;
  }
  
  public Element compileCommands(final List<Command> commands, final int index) {
    final Command current = commands.get(index);
    Element block = null;
    if ((current instanceof Loop)) {
      block = this.block("loop", Integer.valueOf(((Loop)current).hashCode()).toString());
      block.getElements().add(this.field("entity", ((Loop)current).getEntity()));
      Element comm = this.value("commands");
      comm.getElements().add(this.compileCommands(((Loop)current).getCommands(), 0));
      block.getElements().add(comm);
    } else {
      if ((current instanceof Branch)) {
        block = this.block("branch", Integer.valueOf(((Branch)current).hashCode()).toString());
        Element expression = this.value("expression");
        expression.getElements().add(this.toElement(((Branch)current).getIf().getCondition()));
        block.getElements().add(expression);
        Element comm_1 = this.value("commands");
        comm_1.getElements().add(this.compileCommands(((Branch)current).getIf().getCommands(), 0));
        block.getElements().add(comm_1);
      } else {
        if ((current instanceof Assignment)) {
          block = this.block("assignment", Integer.valueOf(((Assignment)current).hashCode()).toString());
          block.getElements().add(this.field("type", this.toBlocks(((Assignment)current).getType())));
          Element access = this.value("access");
          access.getElements().add(this.toElement(((Assignment)current).getAccess()));
          block.getElements().add(access);
          Element expression_1 = this.value("expression");
          expression_1.getElements().add(this.toElement(((Assignment)current).getExpression()));
          block.getElements().add(expression_1);
        } else {
          if ((current instanceof Call)) {
            block = this.block("converttocommand", Integer.valueOf(((Call)current).hashCode()).toString());
            Element value = this.value("value");
            block.getElements().add(value);
            value.getElements().add(this.toElement(((Expression)current)));
          }
        }
      }
    }
    int _size = commands.size();
    boolean _greaterThan = (_size > (index + 1));
    if (_greaterThan) {
      Element _createElement = this.factory.createElement();
      final Procedure1<Element> _function = (Element it) -> {
        it.setOpen("next");
        it.setClose("next");
      };
      Element next = ObjectExtensions.<Element>operator_doubleArrow(_createElement, _function);
      next.getElements().add(this.compileCommands(commands, (index + 1)));
      block.getElements().add(next);
    }
    return block;
  }
  
  public Element toElement(final Expression expression) {
    if ((expression instanceof Plus)) {
      Element block = this.block("binaryexpression", Integer.valueOf(((Plus)expression).hashCode()).toString());
      block.getElements().add(this.field("operation", "plus"));
      Element left = this.value("left");
      left.getElements().add(this.toElement(((Plus)expression).getLeft()));
      Element right = this.value("right");
      right.getElements().add(this.toElement(((Plus)expression).getRight()));
      block.getElements().add(left);
      block.getElements().add(right);
      return block;
    } else {
      if ((expression instanceof Minus)) {
        Element block_1 = this.block("binaryexpression", Integer.valueOf(((Minus)expression).hashCode()).toString());
        block_1.getElements().add(this.field("operation", "minus"));
        Element left_1 = this.value("left");
        left_1.getElements().add(this.toElement(((Minus)expression).getLeft()));
        Element right_1 = this.value("right");
        right_1.getElements().add(this.toElement(((Minus)expression).getRight()));
        block_1.getElements().add(left_1);
        block_1.getElements().add(right_1);
        return block_1;
      } else {
        if ((expression instanceof Times)) {
          Element block_2 = this.block("binaryexpression", Integer.valueOf(((Times)expression).hashCode()).toString());
          block_2.getElements().add(this.field("operation", "times"));
          Element left_2 = this.value("left");
          left_2.getElements().add(this.toElement(((Times)expression).getLeft()));
          Element right_2 = this.value("right");
          right_2.getElements().add(this.toElement(((Times)expression).getRight()));
          block_2.getElements().add(left_2);
          block_2.getElements().add(right_2);
          return block_2;
        } else {
          if ((expression instanceof Divide)) {
            Element block_3 = this.block("binaryexpression", Integer.valueOf(((Divide)expression).hashCode()).toString());
            block_3.getElements().add(this.field("operation", "divide"));
            Element left_3 = this.value("left");
            left_3.getElements().add(this.toElement(((Divide)expression).getLeft()));
            Element right_3 = this.value("right");
            right_3.getElements().add(this.toElement(((Divide)expression).getRight()));
            block_3.getElements().add(left_3);
            block_3.getElements().add(right_3);
            return block_3;
          } else {
            if ((expression instanceof And)) {
              Element block_4 = this.block("binaryexpression", Integer.valueOf(((And)expression).hashCode()).toString());
              block_4.getElements().add(this.field("operation", "and"));
              Element left_4 = this.value("left");
              left_4.getElements().add(this.toElement(((And)expression).getLeft()));
              Element right_4 = this.value("right");
              right_4.getElements().add(this.toElement(((And)expression).getRight()));
              block_4.getElements().add(left_4);
              block_4.getElements().add(right_4);
              return block_4;
            } else {
              if ((expression instanceof Or)) {
                Element block_5 = this.block("binaryexpression", Integer.valueOf(((Or)expression).hashCode()).toString());
                block_5.getElements().add(this.field("operation", "or"));
                Element left_5 = this.value("left");
                left_5.getElements().add(this.toElement(((Or)expression).getLeft()));
                Element right_5 = this.value("right");
                right_5.getElements().add(this.toElement(((Or)expression).getRight()));
                block_5.getElements().add(left_5);
                block_5.getElements().add(right_5);
                return block_5;
              } else {
                if ((expression instanceof Comparison)) {
                  Element block_6 = this.block("binaryexpression", Integer.valueOf(((Comparison)expression).hashCode()).toString());
                  block_6.getElements().add(this.field("operation", this.toBlocks(((Comparison)expression).getType())));
                  Element left_6 = this.value("left");
                  left_6.getElements().add(this.toElement(((Comparison)expression).getLeft()));
                  Element right_6 = this.value("right");
                  right_6.getElements().add(this.toElement(((Comparison)expression).getRight()));
                  block_6.getElements().add(left_6);
                  block_6.getElements().add(right_6);
                  return block_6;
                } else {
                  if ((expression instanceof Not)) {
                    Element block_7 = this.block("unaryexpression", Integer.valueOf(((Not)expression).hashCode()).toString());
                    block_7.getElements().add(this.field("operation", "not"));
                    Element v = this.value("expression");
                    v.getElements().add(this.toElement(((Not)expression).getExpression()));
                    block_7.getElements().add(v);
                    return block_7;
                  } else {
                    if ((expression instanceof BitwiseNegate)) {
                      Element block_8 = this.block("unaryexpression", Integer.valueOf(((BitwiseNegate)expression).hashCode()).toString());
                      block_8.getElements().add(this.field("operation", "not"));
                      Element v_1 = this.value("expression");
                      v_1.getElements().add(this.toElement(((BitwiseNegate)expression).getExpression()));
                      block_8.getElements().add(v_1);
                      return block_8;
                    } else {
                      if ((expression instanceof Increment)) {
                        Element block_9 = this.block("unaryexpression", Integer.valueOf(((Increment)expression).hashCode()).toString());
                        block_9.getElements().add(this.field("operation", "increment"));
                        Element v_2 = this.value("expression");
                        v_2.getElements().add(this.toElement(((Increment)expression).getExpression()));
                        block_9.getElements().add(v_2);
                        return block_9;
                      } else {
                        if ((expression instanceof Decrement)) {
                          Element block_10 = this.block("unaryexpression", Integer.valueOf(((Decrement)expression).hashCode()).toString());
                          block_10.getElements().add(this.field("operation", "decrement"));
                          Element v_3 = this.value("expression");
                          v_3.getElements().add(this.toElement(((Decrement)expression).getExpression()));
                          block_10.getElements().add(v_3);
                          return block_10;
                        } else {
                          if ((expression instanceof Brackets)) {
                            Element block_11 = this.block("brackets", Integer.valueOf(((Brackets)expression).hashCode()).toString());
                            Element v_4 = this.value("expression");
                            v_4.getElements().add(this.toElement(((Brackets)expression).getExpression()));
                            block_11.getElements().add(v_4);
                            return block_11;
                          } else {
                            if ((expression instanceof Access)) {
                              EList<String> names = ((Access)expression).getNames();
                              int _size = names.size();
                              boolean _equals = (_size == 1);
                              if (_equals) {
                                Element block_12 = this.block("access1", Integer.valueOf(((Access)expression).hashCode()).toString());
                                block_12.getElements().add(this.field("a", names.get(0)));
                                return block_12;
                              } else {
                                int _size_1 = names.size();
                                boolean _equals_1 = (_size_1 == 2);
                                if (_equals_1) {
                                  Element block_13 = this.block("access2", Integer.valueOf(((Access)expression).hashCode()).toString());
                                  block_13.getElements().add(this.field("a", names.get(0)));
                                  block_13.getElements().add(this.field("b", names.get(1)));
                                  return block_13;
                                } else {
                                  int _size_2 = names.size();
                                  boolean _equals_2 = (_size_2 == 3);
                                  if (_equals_2) {
                                    Element block_14 = this.block("access3", Integer.valueOf(((Access)expression).hashCode()).toString());
                                    block_14.getElements().add(this.field("a", names.get(0)));
                                    block_14.getElements().add(this.field("b", names.get(1)));
                                    block_14.getElements().add(this.field("c", names.get(2)));
                                    return block_14;
                                  }
                                }
                              }
                            } else {
                              if ((expression instanceof Call)) {
                                String name = ((Call)expression).getName();
                                boolean _equals_3 = Objects.equal(name, "add");
                                if (_equals_3) {
                                  Element block_15 = this.block("add", Integer.valueOf(((Call)expression).hashCode()).toString());
                                  Element component = this.value("component");
                                  component.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                  Element entity = this.value("entity");
                                  entity.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                  block_15.getElements().add(component);
                                  block_15.getElements().add(entity);
                                  return block_15;
                                } else {
                                  boolean _equals_4 = Objects.equal(name, "remove");
                                  if (_equals_4) {
                                    Element block_16 = this.block("remove", Integer.valueOf(((Call)expression).hashCode()).toString());
                                    Element component_1 = this.value("component");
                                    component_1.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                    Element entity_1 = this.value("entity");
                                    entity_1.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                    block_16.getElements().add(component_1);
                                    block_16.getElements().add(entity_1);
                                    return block_16;
                                  } else {
                                    boolean _equals_5 = Objects.equal(name, "create");
                                    if (_equals_5) {
                                      Element block_17 = this.block("create", Integer.valueOf(((Call)expression).hashCode()).toString());
                                      Element entity_2 = this.value("expression");
                                      entity_2.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                      block_17.getElements().add(entity_2);
                                      return block_17;
                                    } else {
                                      boolean _equals_6 = Objects.equal(name, "destroy");
                                      if (_equals_6) {
                                        Element block_18 = this.block("destroy", Integer.valueOf(((Call)expression).hashCode()).toString());
                                        Element entity_3 = this.value("expression");
                                        entity_3.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                        block_18.getElements().add(entity_3);
                                        return block_18;
                                      } else {
                                        boolean _equals_7 = Objects.equal(name, "join");
                                        if (_equals_7) {
                                          EList<Expression> parameters = ((Call)expression).getParameters();
                                          int _size_3 = parameters.size();
                                          boolean _equals_8 = (_size_3 == 2);
                                          if (_equals_8) {
                                            Element block_19 = this.block("join2", Integer.valueOf(((Call)expression).hashCode()).toString());
                                            Element component_2 = this.value("x");
                                            component_2.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                            Element entity_4 = this.value("y");
                                            entity_4.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                            block_19.getElements().add(component_2);
                                            block_19.getElements().add(entity_4);
                                            return block_19;
                                          } else {
                                            int _size_4 = parameters.size();
                                            boolean _equals_9 = (_size_4 == 3);
                                            if (_equals_9) {
                                              Element block_20 = this.block("join2", Integer.valueOf(((Call)expression).hashCode()).toString());
                                              Element component_3 = this.value("x");
                                              component_3.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                              Element entity_5 = this.value("y");
                                              entity_5.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                              Element z = this.value("z");
                                              z.getElements().add(this.toElement(((Call)expression).getParameters().get(2)));
                                              block_20.getElements().add(component_3);
                                              block_20.getElements().add(entity_5);
                                              block_20.getElements().add(z);
                                              return block_20;
                                            } else {
                                              int _size_5 = parameters.size();
                                              boolean _equals_10 = (_size_5 == 4);
                                              if (_equals_10) {
                                                Element block_21 = this.block("join2", Integer.valueOf(((Call)expression).hashCode()).toString());
                                                Element component_4 = this.value("x");
                                                component_4.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                                Element entity_6 = this.value("y");
                                                entity_6.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                                Element z_1 = this.value("z");
                                                z_1.getElements().add(this.toElement(((Call)expression).getParameters().get(2)));
                                                Element w = this.value("w");
                                                w.getElements().add(this.toElement(((Call)expression).getParameters().get(3)));
                                                block_21.getElements().add(component_4);
                                                block_21.getElements().add(entity_6);
                                                block_21.getElements().add(z_1);
                                                block_21.getElements().add(w);
                                                return block_21;
                                              }
                                            }
                                          }
                                        } else {
                                          boolean _equals_11 = Objects.equal(name, "random");
                                          if (_equals_11) {
                                            Element block_22 = this.block("random", Integer.valueOf(((Call)expression).hashCode()).toString());
                                            Element entity_7 = this.value("expression");
                                            entity_7.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                            block_22.getElements().add(entity_7);
                                            return block_22;
                                          } else {
                                            boolean _equals_12 = Objects.equal(name, "has");
                                            if (_equals_12) {
                                              Element block_23 = this.block("has", Integer.valueOf(((Call)expression).hashCode()).toString());
                                              Element component_5 = this.value("component");
                                              component_5.getElements().add(this.toElement(((Call)expression).getParameters().get(1)));
                                              Element entity_8 = this.value("entity");
                                              entity_8.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                              block_23.getElements().add(entity_8);
                                              block_23.getElements().add(component_5);
                                              return block_23;
                                            } else {
                                              Element block_24 = this.block("mathfunction", Integer.valueOf(((Call)expression).hashCode()).toString());
                                              Element entity_9 = this.value("expression");
                                              block_24.getElements().add(this.field("name", name));
                                              entity_9.getElements().add(this.toElement(((Call)expression).getParameters().get(0)));
                                              block_24.getElements().add(entity_9);
                                              return block_24;
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
        }
      }
    }
    return null;
  }
  
  public String toBlocks(final RelationType type) {
    String _switchResult = null;
    if (type != null) {
      switch (type) {
        case UNDER:
          _switchResult = "less";
          break;
        case UNDEROREQUAL:
          _switchResult = "lessOrEqual";
          break;
        case EQUAL:
          _switchResult = "equal";
          break;
        case NOTEQUAL:
          _switchResult = "notEqual";
          break;
        case OVEROREQUAL:
          _switchResult = "greaterOrEqual";
          break;
        case OVER:
          _switchResult = "greater";
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  public String toBlocks(final AssignmentType type) {
    String _switchResult = null;
    if (type != null) {
      switch (type) {
        case SET:
          _switchResult = "set";
          break;
        case INCREASE:
          _switchResult = "increase";
          break;
        case DECREASE:
          _switchResult = "decrease";
          break;
        case AND:
          _switchResult = "and";
          break;
        case DIVIDE:
          _switchResult = "divide";
          break;
        case MODULUS:
          _switchResult = "modulus";
          break;
        case MULTIPLY:
          _switchResult = "multiply";
          break;
        case OR:
          _switchResult = "or";
          break;
        case SHIFT_LEFT:
          _switchResult = "shiftLeft";
          break;
        case SHIFT_RIGHT:
          _switchResult = "shiftRight";
          break;
        case XOR:
          _switchResult = "xor";
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
}
