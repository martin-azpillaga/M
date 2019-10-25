package m.formatting2;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.function.Consumer;
import m.services.YAMLGrammarAccess;
import m.yaml.Document;
import m.yaml.Element;
import m.yaml.File;
import m.yaml.InlineMap;
import m.yaml.KeyValue;
import m.yaml.KeyValueList;
import m.yaml.Map;
import m.yaml.Node;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class YAMLFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private YAMLGrammarAccess access;
  
  protected void _format(final File file, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(file).ruleCall(this.access.getFileAccess().getWSTerminalRuleCall_1_1()), _function);
    EList<Node> _nodes = file.getNodes();
    for (final Node node : _nodes) {
      Node _head = IterableExtensions.<Node>head(file.getNodes());
      boolean _notEquals = (!Objects.equal(node, _head));
      if (_notEquals) {
        final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
          it.newLine();
        };
        document.<Node>prepend(node, _function_1);
      } else {
        final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
          it.noSpace();
        };
        document.<Node>prepend(node, _function_2);
      }
    }
    final Consumer<Node> _function_3 = (Node it) -> {
      document.<Node>format(it);
    };
    file.getNodes().forEach(_function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(file).assignment(this.access.getFileAccess().getTagsAssignment_1_2()), _function_4);
  }
  
  protected void _format(final KeyValue keyValue, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(keyValue).keyword(":"), _function);
    Element _value = keyValue.getValue();
    if (_value!=null) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      document.<Element>surround(_value, _function_1);
    }
    Element _value_1 = keyValue.getValue();
    if (_value_1!=null) {
      document.<Element>format(_value_1);
    }
  }
  
  protected void _format(final Document document, @Extension final IFormattableDocument e) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    e.<Document>prepend(document, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(document).keyword("---"), _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(document).keyword("!u!"), _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(document).assignment(this.access.getDocumentAccess().getTagAssignment_4()), _function_3);
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(document).keyword("&"), _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(document).assignment(this.access.getDocumentAccess().getIdAssignment_7()), _function_5);
    e.<Map>format(document.getValue());
  }
  
  protected void _format(final Map map, @Extension final IFormattableDocument e) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.<Map>prepend(map, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(map).keyword(":"), _function_1);
    EList<Node> _body = map.getBody();
    for (final Node value : _body) {
      {
        final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
          it.indent();
        };
        e.<Node>surround(value, _function_2);
        e.<Node>format(value);
      }
    }
  }
  
  protected void _format(final InlineMap map, @Extension final IFormattableDocument e) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(map).keyword("{"), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    e.surround(this.textRegionExtensions.regionFor(map).keyword("}"), _function_1);
    final Consumer<ISemanticRegion> _function_2 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      e.surround(it, _function_3);
    };
    this.textRegionExtensions.regionFor(map).keywords(":").forEach(_function_2);
    final Consumer<ISemanticRegion> _function_3 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      e.surround(it, _function_4);
    };
    this.textRegionExtensions.regionFor(map).keywords(",").forEach(_function_3);
    EList<KeyValue> _values = map.getValues();
    for (final KeyValue value : _values) {
      {
        final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
          it.noSpace();
        };
        e.<KeyValue>surround(value, _function_4);
        e.<KeyValue>format(value);
      }
    }
  }
  
  protected void _format(final KeyValueList keyValueList, @Extension final IFormattableDocument document) {
    final Consumer<EObject> _function = (EObject it) -> {
      document.<EObject>format(it);
    };
    keyValueList.getValues().forEach(_function);
    final Consumer<ISemanticRegion> _function_1 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it_1) -> {
        it_1.oneSpace();
      };
      document.prepend(it, _function_2);
    };
    this.textRegionExtensions.regionFor(keyValueList).keywords("-").forEach(_function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(keyValueList).keyword(":"), _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(keyValueList).ruleCall(this.access.getKeyValueListAccess().getSPACETerminalRuleCall_4()), _function_3);
    final Consumer<ISemanticRegion> _function_4 = (ISemanticRegion it) -> {
      final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it_1) -> {
        it_1.noSpace();
      };
      document.surround(it, _function_5);
    };
    this.textRegionExtensions.regionFor(keyValueList).ruleCalls(this.access.getKeyValueListAccess().getSPACETerminalRuleCall_6_2()).forEach(_function_4);
  }
  
  public void format(final Object document, final IFormattableDocument e) {
    if (document instanceof XtextResource) {
      _format((XtextResource)document, e);
      return;
    } else if (document instanceof Document) {
      _format((Document)document, e);
      return;
    } else if (document instanceof InlineMap) {
      _format((InlineMap)document, e);
      return;
    } else if (document instanceof KeyValue) {
      _format((KeyValue)document, e);
      return;
    } else if (document instanceof KeyValueList) {
      _format((KeyValueList)document, e);
      return;
    } else if (document instanceof Map) {
      _format((Map)document, e);
      return;
    } else if (document instanceof File) {
      _format((File)document, e);
      return;
    } else if (document instanceof EObject) {
      _format((EObject)document, e);
      return;
    } else if (document == null) {
      _format((Void)null, e);
      return;
    } else if (document != null) {
      _format(document, e);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(document, e).toString());
    }
  }
}
