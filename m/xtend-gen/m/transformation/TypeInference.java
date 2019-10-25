package m.transformation;

import com.google.common.base.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import m.m.Access;
import m.m.And;
import m.m.Assignment;
import m.m.AssignmentType;
import m.m.Brackets;
import m.m.Branch;
import m.m.Call;
import m.m.Command;
import m.m.Comparison;
import m.m.Component;
import m.m.Condition;
import m.m.Decrement;
import m.m.Divide;
import m.m.Entity;
import m.m.Expression;
import m.m.Game;
import m.m.Increment;
import m.m.Loop;
import m.m.Minus;
import m.m.Modulus;
import m.m.Not;
import m.m.Or;
import m.m.Plus;
import m.m.Times;
import m.m.Value;
import m.m.Vector;
import m.m.Word;
import m.transformation.Type;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class TypeInference {
  private HashSet<HashSet<String>> groups = new HashSet<HashSet<String>>();
  
  private HashMap<String, Type> solved = new HashMap<String, Type>();
  
  public HashMap<String, Type> infer(final Game game) {
    EList<Entity> _entities = game.getEntities();
    for (final Entity entity : _entities) {
      EList<Component> _components = entity.getComponents();
      for (final Component component : _components) {
        this.infer(component);
      }
    }
    EList<m.m.System> _systems = game.getSystems();
    for (final m.m.System system : _systems) {
      EList<Command> _commands = system.getCommands();
      for (final Command command : _commands) {
        this.infer(command);
      }
    }
    return this.solved;
  }
  
  public void infer(final Component component) {
    String name = component.getName();
    Value value = component.getValue();
    if ((value == null)) {
      this.set(name, Type.tag);
    } else {
      if ((value instanceof Vector)) {
        EList<Float> numbers = ((Vector)value).getNumbers();
        int _size = numbers.size();
        boolean _equals = (_size == 1);
        if (_equals) {
          this.set(name, Type.float1);
        } else {
          int _size_1 = numbers.size();
          boolean _equals_1 = (_size_1 == 2);
          if (_equals_1) {
            this.set(name, Type.float2);
          } else {
            int _size_2 = numbers.size();
            boolean _equals_2 = (_size_2 == 3);
            if (_equals_2) {
              this.set(name, Type.float3);
            } else {
              int _size_3 = numbers.size();
              boolean _equals_3 = (_size_3 == 4);
              if (_equals_3) {
                this.set(name, Type.float4);
              }
            }
          }
        }
      } else {
        if ((value instanceof Word)) {
          boolean _equals_4 = Objects.equal(name, "mesh");
          if (_equals_4) {
            this.set(name, Type.mesh);
          } else {
            boolean _equals_5 = Objects.equal(name, "material");
            if (_equals_5) {
              this.set(name, Type.material);
            } else {
              boolean _equals_6 = Objects.equal(name, "audioClip");
              if (_equals_6) {
                this.set(name, Type.audioClip);
              } else {
                boolean _contains = this.inputNames().contains(IterableExtensions.join(((Word)value).getValues(), " "));
                if (_contains) {
                  this.set(name, Type.input);
                } else {
                  this.set(name, Type.gameObject);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public List<String> inputNames() {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("gamepad left Y", "gamepad left X"));
  }
  
  public void infer(final Command command) {
    if ((command instanceof Call)) {
      boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("sin", "cos", "tan", "exp", "log")).contains(((Call)command).getName());
      if (_contains) {
        this.set(((Call)command).getParameters().get(0), Type.float1);
      } else {
        boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("random")).contains(((Call)command).getName());
        if (_contains_1) {
          this.set(((Call)command).getParameters().get(0), Type.float2);
        } else {
          boolean _contains_2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("create")).contains(((Call)command).getName());
          if (_contains_2) {
            this.set(((Call)command).getParameters().get(0), Type.gameObject);
          } else {
            boolean _contains_3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("destroy")).contains(((Call)command).getName());
            if (_contains_3) {
              this.set(((Call)command).getParameters().get(0), Type.entity);
            } else {
              boolean _contains_4 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("add", "remove")).contains(((Call)command).getName());
              if (_contains_4) {
                this.set(((Call)command).getParameters().get(1), Type.entity);
              }
            }
          }
        }
      }
    } else {
      if ((command instanceof Assignment)) {
        String name = IterableExtensions.<String>last(((Assignment)command).getAccess().getNames());
        int _size = ((Assignment)command).getAccess().getNames().size();
        boolean _equals = (_size == 1);
        if (_equals) {
          EObject container = command;
          while ((!((container instanceof Loop) || (container instanceof m.m.System)))) {
            container = EcoreUtil2.<Loop>getContainerOfType(container, Loop.class);
          }
          String _head = IterableExtensions.<String>head(((Assignment)command).getAccess().getNames());
          String _plus = (_head + "@");
          int _hashCode = container.hashCode();
          String _plus_1 = (_plus + Integer.valueOf(_hashCode));
          name = _plus_1;
        }
        AssignmentType _type = ((Assignment)command).getType();
        if (_type != null) {
          switch (_type) {
            case AND:
              this.set(((Assignment)command).getExpression(), Type.float1);
              break;
            case DECREASE:
              this.group(name, ((Assignment)command).getExpression());
              break;
            case DIVIDE:
              this.set(((Assignment)command).getExpression(), Type.float1);
              break;
            case INCREASE:
              this.group(name, ((Assignment)command).getExpression());
              break;
            case MODULUS:
              this.set(((Assignment)command).getExpression(), Type.float1);
              break;
            case MULTIPLY:
              this.set(((Assignment)command).getExpression(), Type.float1);
              break;
            case OR:
              this.set(((Assignment)command).getExpression(), Type.float1);
              break;
            case SET:
              this.group(name, ((Assignment)command).getExpression());
              break;
            case SHIFT_LEFT:
              break;
            case SHIFT_RIGHT:
              break;
            case XOR:
              break;
            default:
              break;
          }
        }
      } else {
        if ((command instanceof Loop)) {
          EList<String> _constraints = ((Loop)command).getConstraints();
          for (final String constraint : _constraints) {
            this.set(constraint, Type.tag);
          }
          EList<Command> _commands = ((Loop)command).getCommands();
          for (final Command c : _commands) {
            this.infer(c);
          }
        } else {
          if ((command instanceof Branch)) {
            Condition i = ((Branch)command).getIf();
            this.infer(i.getCondition());
            final Consumer<Command> _function = (Command it) -> {
              this.infer(it);
            };
            i.getCommands().forEach(_function);
            EList<Condition> _elseIfs = ((Branch)command).getElseIfs();
            for (final Condition condition : _elseIfs) {
              {
                Condition c_1 = ((Condition) condition);
                this.infer(c_1.getCondition());
                EList<Command> _commands_1 = c_1.getCommands();
                for (final Command comm : _commands_1) {
                  this.infer(comm);
                }
              }
            }
            EList<Command> _commands_1 = ((Branch)command).getCommands();
            for (final Command comm : _commands_1) {
              this.infer(comm);
            }
          }
        }
      }
    }
  }
  
  public void group(final String component, final Expression expression) {
    boolean _containsKey = this.solved.containsKey(component);
    if (_containsKey) {
      this.set(expression, this.solved.get(component));
    } else {
      boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("position", "velocity", "acceleration", "force")).contains(component);
      if (_contains) {
        this.set(component, Type.float3);
        this.set(expression, Type.float3);
      } else {
        boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("mass", "number")).contains(component);
        if (_contains_1) {
          this.set(component, Type.float1);
          this.set(expression, Type.float1);
        } else {
          if ((expression instanceof Plus)) {
            this.group(component, ((Plus)expression).getLeft());
            this.group(component, ((Plus)expression).getRight());
          } else {
            if ((expression instanceof Minus)) {
              this.group(component, ((Minus)expression).getLeft());
              this.group(component, ((Minus)expression).getRight());
            } else {
              if ((expression instanceof Times)) {
                this.group(component, ((Times)expression).getLeft());
                this.set(((Times)expression).getRight(), Type.float1);
              } else {
                if ((expression instanceof Divide)) {
                  this.group(component, ((Divide)expression).getLeft());
                  this.set(((Divide)expression).getRight(), Type.float1);
                } else {
                  if ((expression instanceof Modulus)) {
                    this.group(component, ((Modulus)expression).getLeft());
                    this.set(((Modulus)expression).getRight(), Type.float1);
                  } else {
                    if ((expression instanceof Increment)) {
                      this.set(((Increment)expression).getExpression(), Type.float1);
                    } else {
                      if ((expression instanceof Decrement)) {
                        this.set(((Decrement)expression).getExpression(), Type.float1);
                      } else {
                        if ((expression instanceof Brackets)) {
                          this.group(component, ((Brackets)expression).getExpression());
                        } else {
                          if ((expression instanceof Access)) {
                            int _size = ((Access)expression).getNames().size();
                            boolean _greaterThan = (_size > 2);
                            if (_greaterThan) {
                              for (int i = 1; (i < (((Access)expression).getNames().size() - 1)); i++) {
                                this.set(((Access)expression).getNames().get(i), Type.entity);
                              }
                              this.group(component, IterableExtensions.<String>last(((Access)expression).getNames()));
                            } else {
                              int _size_1 = ((Access)expression).getNames().size();
                              boolean _equals = (_size_1 == 2);
                              if (_equals) {
                                this.group(component, IterableExtensions.<String>last(((Access)expression).getNames()));
                              } else {
                                EObject container = expression;
                                while ((!((container instanceof Loop) || (container instanceof m.m.System)))) {
                                  container = EcoreUtil2.<Loop>getContainerOfType(container, Loop.class);
                                }
                                String _head = IterableExtensions.<String>head(((Access)expression).getNames());
                                String _plus = (_head + "@");
                                int _hashCode = container.hashCode();
                                String name = (_plus + Integer.valueOf(_hashCode));
                                this.group(component, name);
                              }
                            }
                          } else {
                            if ((expression instanceof Call)) {
                              boolean _contains_2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("sin", "cos", "tan", "exp", "log")).contains(((Call)expression).getName());
                              if (_contains_2) {
                                this.set(component, Type.float1);
                                this.set(((Call)expression).getParameters().get(0), Type.float1);
                              } else {
                                boolean _contains_3 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("random")).contains(((Call)expression).getName());
                                if (_contains_3) {
                                  this.set(component, Type.float1);
                                  this.set(((Call)expression).getParameters().get(0), Type.float2);
                                } else {
                                  boolean _contains_4 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("join")).contains(((Call)expression).getName());
                                  if (_contains_4) {
                                    final Consumer<Expression> _function = (Expression it) -> {
                                      this.set(it, Type.float1);
                                    };
                                    ((Call)expression).getParameters().forEach(_function);
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
  
  public Object group(final String component1, final String component2) {
    Object _xifexpression = null;
    boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("velocity", "position", "force", "acceleration", "scale")).contains(component2);
    if (_contains) {
      Object _xblockexpression = null;
      {
        this.set(component1, Type.float3);
        _xblockexpression = this.set(component2, Type.float3);
      }
      _xifexpression = _xblockexpression;
    } else {
      Object _xifexpression_1 = null;
      boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("mass", "number")).contains(component2);
      if (_contains_1) {
        Object _xblockexpression_1 = null;
        {
          this.set(component1, Type.float1);
          _xblockexpression_1 = this.set(component2, Type.float1);
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        boolean _xblockexpression_2 = false;
        {
          boolean found = false;
          for (final HashSet<String> group : this.groups) {
            if ((group.contains(component1) && (!group.contains(component2)))) {
              group.add(component2);
              found = true;
            } else {
              if ((group.contains(component2) && (!group.contains(component1)))) {
                group.add(component1);
                found = true;
              }
            }
          }
          boolean _xifexpression_2 = false;
          if ((!found)) {
            boolean _xblockexpression_3 = false;
            {
              HashSet<String> newGroup = new HashSet<String>();
              newGroup.addAll(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(component1, component2)));
              _xblockexpression_3 = this.groups.add(newGroup);
            }
            _xifexpression_2 = _xblockexpression_3;
          }
          _xblockexpression_2 = _xifexpression_2;
        }
        _xifexpression_1 = Boolean.valueOf(_xblockexpression_2);
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public void infer(final Expression expression) {
    if ((expression instanceof Or)) {
      this.infer(((Or)expression).getLeft());
      this.infer(((Or)expression).getRight());
    } else {
      if ((expression instanceof And)) {
        this.infer(((And)expression).getLeft());
        this.infer(((And)expression).getRight());
      } else {
        if ((expression instanceof Not)) {
          this.infer(((Not)expression).getExpression());
        } else {
          if ((expression instanceof Comparison)) {
            this.set(((Comparison)expression).getLeft(), Type.float1);
            this.set(((Comparison)expression).getRight(), Type.float1);
          } else {
            if ((expression instanceof Plus)) {
            }
          }
        }
      }
    }
  }
  
  public void set(final Expression expression, final Type value) {
    if ((expression instanceof Plus)) {
      this.set(((Plus)expression).getLeft(), value);
      this.set(((Plus)expression).getRight(), value);
    } else {
      if ((expression instanceof Minus)) {
        this.set(((Minus)expression).getLeft(), value);
        this.set(((Minus)expression).getRight(), value);
      } else {
        if ((expression instanceof Times)) {
          this.set(((Times)expression).getLeft(), value);
          this.set(((Times)expression).getRight(), Type.float1);
        } else {
          if ((expression instanceof Divide)) {
            this.set(((Divide)expression).getLeft(), value);
            this.set(((Divide)expression).getRight(), Type.float1);
          } else {
            if ((expression instanceof Modulus)) {
              this.set(((Modulus)expression).getLeft(), value);
              this.set(((Modulus)expression).getRight(), Type.float1);
            } else {
              if ((expression instanceof Increment)) {
                this.set(((Increment)expression).getExpression(), Type.float1);
              } else {
                if ((expression instanceof Decrement)) {
                  this.set(((Decrement)expression).getExpression(), Type.float1);
                } else {
                  if ((expression instanceof Brackets)) {
                    this.set(((Brackets)expression).getExpression(), value);
                  } else {
                    if ((expression instanceof Access)) {
                      int _size = ((Access)expression).getNames().size();
                      boolean _greaterThan = (_size > 2);
                      if (_greaterThan) {
                        for (int i = 1; (i < (((Access)expression).getNames().size() - 1)); i++) {
                          this.set(((Access)expression).getNames().get(i), Type.entity);
                        }
                        this.set(IterableExtensions.<String>last(((Access)expression).getNames()), value);
                      } else {
                        int _size_1 = ((Access)expression).getNames().size();
                        boolean _equals = (_size_1 == 2);
                        if (_equals) {
                          this.set(IterableExtensions.<String>last(((Access)expression).getNames()), value);
                        } else {
                          EObject container = expression;
                          while ((!((container instanceof Loop) || (container instanceof m.m.System)))) {
                            container = EcoreUtil2.<Loop>getContainerOfType(container, Loop.class);
                          }
                          String _head = IterableExtensions.<String>head(((Access)expression).getNames());
                          String _plus = (_head + "@");
                          int _hashCode = container.hashCode();
                          String name = (_plus + Integer.valueOf(_hashCode));
                          this.set(name, value);
                        }
                      }
                    } else {
                      if ((expression instanceof Call)) {
                        boolean _contains = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("sin", "cos", "tan", "exp", "log")).contains(((Call)expression).getName());
                        if (_contains) {
                          this.set(((Call)expression).getParameters().get(0), Type.float1);
                        } else {
                          boolean _contains_1 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("random")).contains(((Call)expression).getName());
                          if (_contains_1) {
                            this.set(((Call)expression).getParameters().get(0), Type.float2);
                          } else {
                            boolean _contains_2 = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("join")).contains(((Call)expression).getName());
                            if (_contains_2) {
                              this.set(((Call)expression).getParameters().get(0), Type.float1);
                              this.set(((Call)expression).getParameters().get(1), Type.float1);
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
  
  public Object set(final String component, final Type value) {
    Object _xifexpression = null;
    boolean _containsKey = this.solved.containsKey(component);
    boolean _not = (!_containsKey);
    if (_not) {
      Type _xblockexpression = null;
      {
        for (final HashSet<String> group : this.groups) {
          boolean _contains = group.contains(component);
          if (_contains) {
            for (final String c : group) {
              this.solved.put(c, value);
            }
            this.groups.remove(group);
          }
        }
        _xblockexpression = this.solved.put(component, value);
      }
      _xifexpression = _xblockexpression;
    } else {
      String _xifexpression_1 = null;
      Type _get = this.solved.get(component);
      boolean _notEquals = (!Objects.equal(_get, value));
      if (_notEquals) {
        Type _get_1 = this.solved.get(component);
        String _plus = ((((("Type error for " + component) + " values ") + value) + " ") + _get_1);
        _xifexpression_1 = InputOutput.<String>println(_plus);
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
}
