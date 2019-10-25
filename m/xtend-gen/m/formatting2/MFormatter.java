package m.formatting2;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.function.Consumer;
import m.m.Access;
import m.m.Assignment;
import m.m.Branch;
import m.m.Call;
import m.m.Command;
import m.m.Component;
import m.m.Condition;
import m.m.Entity;
import m.m.Expression;
import m.m.Game;
import m.m.Loop;
import m.m.Value;
import m.m.Vector;
import m.m.Word;
import m.services.MGrammarAccess;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class MFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private MGrammarAccess access;
  
  protected void _format(final Game game, @Extension final IFormattableDocument document) {
    EList<Entity> _entities = game.getEntities();
    for (final Entity entity : _entities) {
      {
        final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
          it.setNewLines(2);
        };
        document.<Entity>append(entity, _function);
        document.<Entity>format(entity);
      }
    }
    EList<m.m.System> _systems = game.getSystems();
    for (final m.m.System system : _systems) {
      {
        final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
          it.setNewLines(2);
        };
        document.<m.m.System>append(system, _function);
        document.<m.m.System>format(system);
      }
    }
  }
  
  protected void _format(final Entity entity, @Extension final IFormattableDocument document) {
    final ISemanticRegion open = this.textRegionExtensions.regionFor(entity).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(entity).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.surround(open, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(open, _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_4);
    final Consumer<ISemanticRegion> _function_5 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_7 = (IHiddenRegionFormatter it_1) -> {
        it_1.autowrap(8);
      };
      document.append(document.prepend(it, _function_6), _function_7);
    };
    this.textRegionExtensions.regionFor(entity).keywords(",").forEach(_function_5);
    EList<Component> _components = entity.getComponents();
    for (final Component component : _components) {
      document.<Component>format(component);
    }
  }
  
  protected void _format(final Component component, @Extension final IFormattableDocument document) {
    Value _value = component.getValue();
    if (_value!=null) {
      document.<Value>format(_value);
    }
  }
  
  protected void _format(final Vector vector, @Extension final IFormattableDocument document) {
    final Consumer<ISemanticRegion> _function = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it_1) -> {
        it_1.oneSpace();
      };
      document.prepend(it, _function_1);
    };
    this.textRegionExtensions.regionFor(vector).assignments(this.access.getVectorAccess().getNumbersAssignment()).forEach(_function);
  }
  
  protected void _format(final Word word, @Extension final IFormattableDocument document) {
    final Consumer<ISemanticRegion> _function = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it_1) -> {
        it_1.oneSpace();
      };
      document.prepend(it, _function_1);
    };
    this.textRegionExtensions.regionFor(word).assignments(this.access.getWordAccess().getValuesAssignment()).forEach(_function);
  }
  
  protected void _format(final m.m.System system, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.prepend(this.textRegionExtensions.regionFor(system).keyword(":"), _function);
    final Consumer<Command> _function_1 = (Command it) -> {
      document.<Command>format(it);
    };
    system.getCommands().forEach(_function_1);
  }
  
  protected void _format(final Loop loop, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.<Loop>prepend(loop, _function);
    final ISemanticRegion open = this.textRegionExtensions.regionFor(loop).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(loop).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.surround(open, _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(open, _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_5);
    final Consumer<Command> _function_6 = (Command it) -> {
      document.<Command>format(it);
    };
    loop.getCommands().forEach(_function_6);
  }
  
  protected void _format(final Branch branch, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.<Branch>prepend(branch, _function);
    document.<Condition>format(branch.getIf());
    final Consumer<Condition> _function_1 = (Condition it) -> {
      document.<Condition>format(it);
    };
    branch.getElseIfs().forEach(_function_1);
    final ISemanticRegion open = this.textRegionExtensions.regionFor(branch).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(branch).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.surround(open, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(open, _function_5);
    final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_6);
    final Consumer<Command> _function_7 = (Command it) -> {
      document.<Command>format(it);
    };
    branch.getCommands().forEach(_function_7);
  }
  
  protected void _format(final Condition condition, @Extension final IFormattableDocument document) {
    document.<Expression>format(condition.getCondition());
    final ISemanticRegion open = this.textRegionExtensions.regionFor(condition).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(condition).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.surround(open, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(open, _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_4);
    final Consumer<Command> _function_5 = (Command it) -> {
      document.<Command>format(it);
    };
    condition.getCommands().forEach(_function_5);
  }
  
  protected void _format(final Assignment assignment, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.<Assignment>prepend(assignment, _function);
    document.<Access>format(assignment.getAccess());
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(assignment).assignment(this.access.getAssignmentAccess().getTypeAssignment_1()), _function_1);
    document.<Expression>format(assignment.getExpression());
  }
  
  protected void _format(final Call call, @Extension final IFormattableDocument document) {
    EObject container = call.eContainer();
    if ((container instanceof Loop)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.newLine();
      };
      document.<Call>prepend(call, _function);
    } else {
      if ((container instanceof Condition)) {
        Expression _condition = ((Condition)container).getCondition();
        boolean _notEquals = (!Objects.equal(call, _condition));
        if (_notEquals) {
          final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
            it.newLine();
          };
          document.<Call>prepend(call, _function_1);
        }
      } else {
        if ((container instanceof Branch)) {
          final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
            it.newLine();
          };
          document.<Call>prepend(call, _function_2);
        }
      }
    }
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(call).keyword("("), _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.prepend(this.textRegionExtensions.regionFor(call).keyword(")"), _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.append(document.prepend(this.textRegionExtensions.regionFor(call).keyword(","), _function_5), _function_6);
    final Consumer<Expression> _function_7 = (Expression it) -> {
      document.<Expression>format(it);
    };
    call.getParameters().forEach(_function_7);
  }
  
  protected void _format(final Access access, @Extension final IFormattableDocument document) {
    final Consumer<ISemanticRegion> _function = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      document.surround(it, _function_1);
    };
    this.textRegionExtensions.regionFor(access).keywords(".").forEach(_function);
  }
  
  public void format(final Object access, final IFormattableDocument document) {
    if (access instanceof XtextResource) {
      _format((XtextResource)access, document);
      return;
    } else if (access instanceof Access) {
      _format((Access)access, document);
      return;
    } else if (access instanceof Assignment) {
      _format((Assignment)access, document);
      return;
    } else if (access instanceof Branch) {
      _format((Branch)access, document);
      return;
    } else if (access instanceof Call) {
      _format((Call)access, document);
      return;
    } else if (access instanceof Loop) {
      _format((Loop)access, document);
      return;
    } else if (access instanceof Vector) {
      _format((Vector)access, document);
      return;
    } else if (access instanceof Word) {
      _format((Word)access, document);
      return;
    } else if (access instanceof Component) {
      _format((Component)access, document);
      return;
    } else if (access instanceof Condition) {
      _format((Condition)access, document);
      return;
    } else if (access instanceof Entity) {
      _format((Entity)access, document);
      return;
    } else if (access instanceof Game) {
      _format((Game)access, document);
      return;
    } else if (access instanceof m.m.System) {
      _format((m.m.System)access, document);
      return;
    } else if (access instanceof EObject) {
      _format((EObject)access, document);
      return;
    } else if (access == null) {
      _format((Void)null, document);
      return;
    } else if (access != null) {
      _format(access, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(access, document).toString());
    }
  }
}
