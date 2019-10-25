package m.formatting2;

import java.util.Arrays;
import java.util.function.Consumer;
import m.json.Field;
import m.json.StringValue;
import m.json.Value;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class JsonFormatter extends AbstractFormatter2 {
  protected void _format(final m.json.Object object, @Extension final IFormattableDocument document) {
    ISemanticRegion open = this.textRegionExtensions.regionFor(object).keyword("{");
    ISemanticRegion close = this.textRegionExtensions.regionFor(object).keyword("}");
    EObject _eContainer = object.eContainer();
    boolean _tripleNotEquals = (_eContainer != null);
    if (_tripleNotEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.newLine();
      };
      document.prepend(open, _function);
    }
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.prepend(close, _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_2);
    final Consumer<ISemanticRegion> _function_3 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      document.prepend(it, _function_4);
    };
    this.textRegionExtensions.regionFor(object).keywords(",").forEach(_function_3);
    final Consumer<Field> _function_4 = (Field it) -> {
      document.<Field>format(it);
    };
    object.getFields().forEach(_function_4);
  }
  
  protected void _format(final Field field, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.<Field>prepend(field, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(field).keyword(":"), _function_1);
    document.<Value>format(field.getValue());
  }
  
  protected void _format(final StringValue value, @Extension final IFormattableDocument document) {
  }
  
  public void format(final Object object, final IFormattableDocument document) {
    if (object instanceof XtextResource) {
      _format((XtextResource)object, document);
      return;
    } else if (object instanceof m.json.Object) {
      _format((m.json.Object)object, document);
      return;
    } else if (object instanceof StringValue) {
      _format((StringValue)object, document);
      return;
    } else if (object instanceof Field) {
      _format((Field)object, document);
      return;
    } else if (object instanceof EObject) {
      _format((EObject)object, document);
      return;
    } else if (object == null) {
      _format((Void)null, document);
      return;
    } else if (object != null) {
      _format(object, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(object, document).toString());
    }
  }
}
