package m.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import java.util.function.Consumer;
import m.cBlocks.Attribute;
import m.cBlocks.Element;
import m.cBlocks.File;
import m.services.BlocksGrammarAccess;
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
public class BlocksFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private BlocksGrammarAccess _blocksGrammarAccess;
  
  protected void _format(final File file, @Extension final IFormattableDocument document) {
    EList<Element> _elements = file.getElements();
    for (final Element element : _elements) {
      document.<Element>format(element);
    }
  }
  
  protected void _format(final Element element, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(element).keyword("<"), _function);
    final Consumer<ISemanticRegion> _function_1 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      document.surround(it, _function_2);
    };
    this.textRegionExtensions.regionFor(element).keywords(">").forEach(_function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(element).keyword("/>"), _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(element).keyword("</"), _function_3);
    int _size = element.getElements().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      ISemanticRegion open = this.textRegionExtensions.regionFor(element).keyword(this._blocksGrammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_0());
      ISemanticRegion close = this.textRegionExtensions.regionFor(element).keyword(this._blocksGrammarAccess.getElementAccess().getLessThanSignSolidusKeyword_3_1_2());
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
        it.indent();
      };
      document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_4);
      final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
        it.newLine();
      };
      document.prepend(close, _function_5);
    }
    final Consumer<Attribute> _function_6 = (Attribute it) -> {
      document.<Attribute>format(it);
    };
    element.getAttributes().forEach(_function_6);
    final Consumer<Element> _function_7 = (Element it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_8 = (IHiddenRegionFormatter it_1) -> {
        it_1.newLine();
      };
      document.<Element>prepend(it, _function_8);
      document.<Element>format(it);
    };
    element.getElements().forEach(_function_7);
  }
  
  protected void _format(final Attribute attribute, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(attribute).keyword("="), _function);
  }
  
  public void format(final Object attribute, final IFormattableDocument document) {
    if (attribute instanceof XtextResource) {
      _format((XtextResource)attribute, document);
      return;
    } else if (attribute instanceof Attribute) {
      _format((Attribute)attribute, document);
      return;
    } else if (attribute instanceof Element) {
      _format((Element)attribute, document);
      return;
    } else if (attribute instanceof File) {
      _format((File)attribute, document);
      return;
    } else if (attribute instanceof EObject) {
      _format((EObject)attribute, document);
      return;
    } else if (attribute == null) {
      _format((Void)null, document);
      return;
    } else if (attribute != null) {
      _format(attribute, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(attribute, document).toString());
    }
  }
}
