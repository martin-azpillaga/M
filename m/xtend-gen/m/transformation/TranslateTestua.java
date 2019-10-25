package m.transformation;

import java.util.ArrayList;
import java.util.function.Consumer;
import m.TestuaRuntimeModule;
import m.m.Access;
import m.m.And;
import m.m.Assignment;
import m.m.Bitwise;
import m.m.BitwiseNegate;
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
import m.m.MFactory;
import m.m.Minus;
import m.m.Not;
import m.m.Or;
import m.m.Plus;
import m.m.Times;
import m.m.Value;
import m.m.Vector;
import m.m.Word;
import m.transformation.GenericSerializer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class TranslateTestua {
  @Extension
  private MFactory factory = MFactory.eINSTANCE;
  
  public void translate(final Game game, final IFileSystemAccess2 fsa, final String name) {
    Game translation = this.factory.createGame();
    EList<Entity> _entities = game.getEntities();
    for (final Entity entity : _entities) {
      {
        Entity translatedEntity = this.factory.createEntity();
        translatedEntity.getName().addAll(entity.getName());
        EList<Component> _components = entity.getComponents();
        for (final Component component : _components) {
          {
            Component translatedComponent = this.factory.createComponent();
            translatedComponent.setName(this.translate(component.getName()));
            translatedEntity.getComponents().add(translatedComponent);
            Value value = component.getValue();
            if ((value instanceof Vector)) {
              Vector translatedVector = this.factory.createVector();
              translatedVector.getNumbers().addAll(((Vector)value).getNumbers());
              translatedComponent.setValue(translatedVector);
            } else {
              if ((value instanceof Word)) {
                Word translatedWord = this.factory.createWord();
                translatedWord.getValues().addAll(((Word)value).getValues());
                translatedComponent.setValue(translatedWord);
              }
            }
          }
        }
        translation.getEntities().add(translatedEntity);
      }
    }
    EList<m.m.System> _systems = game.getSystems();
    for (final m.m.System s : _systems) {
      EList<Command> _commands = s.getCommands();
      for (final Command c : _commands) {
      }
    }
    TestuaRuntimeModule _testuaRuntimeModule = new TestuaRuntimeModule();
    GenericSerializer.generate(translation, _testuaRuntimeModule, fsa, ("Testua/" + name));
  }
  
  public String translate(final String string) {
    String _switchResult = null;
    if (string != null) {
      switch (string) {
        case "position":
          _switchResult = "kokapena";
          break;
        case "rotation":
          _switchResult = "biraketa";
          break;
        case "scale":
          _switchResult = "eskala";
          break;
        case "mesh":
          _switchResult = "malla";
          break;
        case "material":
          _switchResult = "materiala";
          break;
        case "mass":
          _switchResult = "masa";
          break;
        case "radius":
          _switchResult = "erradioa";
          break;
        case "restitution":
          _switchResult = "errestituzioa";
          break;
        case "extent":
          _switchResult = "neurriak";
          break;
        case "triggerZone":
          _switchResult = "aktibazioLekua";
          break;
        case "add":
          _switchResult = "gehitu";
          break;
        case "remove":
          _switchResult = "kendu";
          break;
        case "create":
          _switchResult = "sortu";
          break;
        case "destroy":
          _switchResult = "desegin";
          break;
        case "join":
          _switchResult = "lotu";
          break;
        case "random":
          _switchResult = "sortez";
          break;
        case "has":
          _switchResult = "badauka";
          break;
        default:
          _switchResult = string;
          break;
      }
    } else {
      _switchResult = string;
    }
    return _switchResult;
  }
  
  public void translate(final Command c) {
    if ((c instanceof Loop)) {
      ArrayList<String> result = new ArrayList<String>();
      EList<String> _constraints = ((Loop)c).getConstraints();
      for (final String constraint : _constraints) {
        result.add(this.rename(constraint));
      }
      ((Loop)c).getConstraints().clear();
      ((Loop)c).getConstraints().addAll(result);
      EList<Command> _commands = ((Loop)c).getCommands();
      for (final Command comm : _commands) {
        this.translate(comm);
      }
    } else {
      if ((c instanceof Branch)) {
        this.translate(((Branch)c).getIf().getCondition());
        final Consumer<Command> _function = (Command it) -> {
          this.translate(it);
        };
        ((Branch)c).getIf().getCommands().forEach(_function);
        final Consumer<Condition> _function_1 = (Condition it) -> {
          this.translate(it.getCondition());
          final Consumer<Command> _function_2 = (Command it_1) -> {
            this.translate(it_1);
          };
          it.getCommands().forEach(_function_2);
        };
        ((Branch)c).getElseIfs().forEach(_function_1);
        final Consumer<Command> _function_2 = (Command it) -> {
          this.translate(it);
        };
        ((Branch)c).getCommands().forEach(_function_2);
      } else {
        if ((c instanceof Assignment)) {
          this.translate(((Assignment)c).getAccess());
          this.translate(((Assignment)c).getExpression());
        } else {
          if ((c instanceof Call)) {
            ((Call)c).setName(this.translate(((Call)c).getName()));
            final Consumer<Expression> _function_3 = (Expression it) -> {
              this.translate(it);
            };
            ((Call)c).getParameters().forEach(_function_3);
          }
        }
      }
    }
  }
  
  public String rename(final String string) {
    boolean _endsWith = string.endsWith("Triggered");
    if (_endsWith) {
      return string.replace("Triggered", "Eraginda");
    } else {
      boolean _endsWith_1 = string.endsWith("Range");
      if (_endsWith_1) {
        return string.replace("Range", "Rangoa");
      } else {
        boolean _endsWith_2 = string.endsWith("Vector");
        if (_endsWith_2) {
          return string.replace("Vector", "Bektorea");
        }
      }
    }
    return string;
  }
  
  public void translate(final Expression expression) {
    if ((expression instanceof And)) {
      this.translate(((And)expression).getLeft());
      this.translate(((And)expression).getRight());
    } else {
      if ((expression instanceof Or)) {
        this.translate(((Or)expression).getLeft());
        this.translate(((Or)expression).getRight());
      } else {
        if ((expression instanceof Not)) {
          this.translate(((Not)expression).getExpression());
        } else {
          if ((expression instanceof Comparison)) {
            this.translate(((Comparison)expression).getLeft());
            this.translate(((Comparison)expression).getRight());
          } else {
            if ((expression instanceof Plus)) {
              this.translate(((Plus)expression).getLeft());
              this.translate(((Plus)expression).getRight());
            } else {
              if ((expression instanceof Minus)) {
                this.translate(((Minus)expression).getLeft());
                this.translate(((Minus)expression).getRight());
              } else {
                if ((expression instanceof Times)) {
                  this.translate(((Times)expression).getLeft());
                  this.translate(((Times)expression).getRight());
                } else {
                  if ((expression instanceof Divide)) {
                    this.translate(((Divide)expression).getLeft());
                    this.translate(((Divide)expression).getRight());
                  } else {
                    if ((expression instanceof Bitwise)) {
                      this.translate(((Bitwise)expression).getLeft());
                      this.translate(((Bitwise)expression).getRight());
                    } else {
                      if ((expression instanceof BitwiseNegate)) {
                        this.translate(((BitwiseNegate)expression).getExpression());
                      } else {
                        if ((expression instanceof Increment)) {
                          this.translate(((Increment)expression).getExpression());
                        } else {
                          if ((expression instanceof Decrement)) {
                            this.translate(((Decrement)expression).getExpression());
                          } else {
                            if ((expression instanceof Access)) {
                              ArrayList<String> result = new ArrayList<String>();
                              EList<String> _names = ((Access)expression).getNames();
                              for (final String n : _names) {
                                result.add(this.translate(n));
                              }
                              ((Access)expression).getNames().clear();
                              ((Access)expression).getNames().addAll(result);
                            } else {
                              if ((expression instanceof Call)) {
                                ((Call)expression).setName(this.translate(((Call)expression).getName()));
                                final Consumer<Expression> _function = (Expression it) -> {
                                  this.translate(it);
                                };
                                ((Call)expression).getParameters().forEach(_function);
                              } else {
                                if ((expression instanceof Brackets)) {
                                  this.translate(((Brackets)expression).getExpression());
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
