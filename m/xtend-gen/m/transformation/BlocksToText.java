package m.transformation;

import com.google.common.base.Objects;
import java.util.List;
import m.TextRuntimeModule;
import m.cBlocks.Attribute;
import m.cBlocks.Element;
import m.cBlocks.File;
import m.m.Component;
import m.m.Entity;
import m.m.Game;
import m.m.MFactory;
import m.m.Vector;
import m.m.Word;
import m.transformation.GenericSerializer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class BlocksToText {
  @Extension
  private MFactory factory = MFactory.eINSTANCE;
  
  public void generate(final File file, final IFileSystemAccess2 fsa, final String... folders) {
    Game compiled = this.compile(file);
    for (final String folder : folders) {
      TextRuntimeModule _textRuntimeModule = new TextRuntimeModule();
      GenericSerializer.generate(compiled, _textRuntimeModule, fsa, (folder + "main.text.gen"));
    }
  }
  
  public Game compile(final File file) {
    Game game = this.factory.createGame();
    Element xml = IterableExtensions.<Element>head(file.getElements());
    EList<Element> _elements = xml.getElements();
    for (final Element element : _elements) {
      {
        String type = element.getAttributes().get(0).getValue();
        boolean _equals = Objects.equal(type, "entity");
        if (_equals) {
          game.getEntities().add(this.compileEntity(element));
        } else {
          boolean _equals_1 = Objects.equal(type, "system");
          if (_equals_1) {
          }
        }
      }
    }
    return game;
  }
  
  public Entity compileEntity(final Element element) {
    Entity entity = this.factory.createEntity();
    Element name = IterableExtensions.<Element>head(element.getElements());
    Element components = element.getElements().get(1);
    int _size = components.getElements().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      this.addComponent(entity.getComponents(), IterableExtensions.<Element>head(components.getElements()));
    }
    CollectionExtensions.<String>addAll(entity.getName(), name.getText().split(" "));
    return entity;
  }
  
  public void addComponent(final List<Component> list, final Element element) {
    Component component = this.factory.createComponent();
    list.add(component);
    Element first = element;
    component.setName(IterableExtensions.<Element>head(first.getElements()).getText());
    String _value = IterableExtensions.<Attribute>head(first.getAttributes()).getValue();
    boolean _notEquals = (!Objects.equal(_value, "tagcomponent"));
    if (_notEquals) {
      Element value = IterableExtensions.<Element>head(first.getElements().get(1).getElements());
      String valueType = IterableExtensions.<Attribute>head(value.getAttributes()).getValue();
      boolean _equals = Objects.equal(valueType, "real1value");
      if (_equals) {
        Vector vector = this.factory.createVector();
        vector.getNumbers().add(Float.valueOf(Float.parseFloat(IterableExtensions.<Element>head(value.getElements()).getText())));
        component.setValue(vector);
      } else {
        boolean _equals_1 = Objects.equal(valueType, "real2value");
        if (_equals_1) {
          Vector vector_1 = this.factory.createVector();
          vector_1.getNumbers().add(Float.valueOf(Float.parseFloat(IterableExtensions.<Element>head(value.getElements()).getText())));
          vector_1.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(1).getText())));
          component.setValue(vector_1);
        } else {
          boolean _equals_2 = Objects.equal(valueType, "real3value");
          if (_equals_2) {
            Vector vector_2 = this.factory.createVector();
            vector_2.getNumbers().add(Float.valueOf(Float.parseFloat(IterableExtensions.<Element>head(value.getElements()).getText())));
            vector_2.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(1).getText())));
            vector_2.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(2).getText())));
            component.setValue(vector_2);
          } else {
            boolean _equals_3 = Objects.equal(valueType, "real4value");
            if (_equals_3) {
              Vector vector_3 = this.factory.createVector();
              vector_3.getNumbers().add(Float.valueOf(Float.parseFloat(IterableExtensions.<Element>head(value.getElements()).getText())));
              vector_3.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(1).getText())));
              vector_3.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(2).getText())));
              vector_3.getNumbers().add(Float.valueOf(Float.parseFloat(value.getElements().get(3).getText())));
              component.setValue(vector_3);
            } else {
              boolean _equals_4 = Objects.equal(valueType, "wordvalue");
              if (_equals_4) {
                Word word = this.factory.createWord();
                CollectionExtensions.<String>addAll(word.getValues(), IterableExtensions.<Element>head(value.getElements()).getText().split(" "));
                component.setValue(word);
              }
            }
          }
        }
      }
    }
    int _size = first.getElements().size();
    boolean _greaterThan = (_size > 2);
    if (_greaterThan) {
      Element next = first.getElements().get(2);
      this.addComponent(list, IterableExtensions.<Element>head(next.getElements()));
    }
  }
  
  public m.m.System compileSystem(final Element element) {
    m.m.System system = this.factory.createSystem();
    Element name = IterableExtensions.<Element>head(element.getElements());
    system.setName(name.getText());
    return system;
  }
}
