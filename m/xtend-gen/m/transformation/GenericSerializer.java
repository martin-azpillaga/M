package m.transformation;

import com.google.common.base.Objects;
import com.google.inject.Guice;
import com.google.inject.Injector;
import m.BlocksRuntimeModule;
import m.YAMLRuntimeModule;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.serializer.impl.Serializer;

@SuppressWarnings("all")
public class GenericSerializer {
  public static void generate(final EObject o, final com.google.inject.Module module, final IFileSystemAccess2 fsa, final String path) {
    XtextResource resource = new XtextResource();
    resource.getContents().add(o);
    Injector injector = Guice.createInjector(module);
    Serializer serializer = injector.<Serializer>getInstance(Serializer.class);
    SaveOptions options = SaveOptions.newBuilder().format().getOptions();
    String serialized = serializer.serialize(o, options);
    Class<? extends com.google.inject.Module> _class = module.getClass();
    boolean _equals = Objects.equal(_class, YAMLRuntimeModule.class);
    if (_equals) {
      serialized = serialized.replace("\t", "  ");
    }
    Class<? extends com.google.inject.Module> _class_1 = module.getClass();
    boolean _equals_1 = Objects.equal(_class_1, BlocksRuntimeModule.class);
    if (_equals_1) {
      serialized = serialized.replace("\t", "  ");
    }
    fsa.generateFile(path, serialized);
  }
  
  public static void add(final EObject o, final com.google.inject.Module module, final IFileSystemAccess2 fsa, final String path) {
    XtextResource resource = new XtextResource();
    resource.getContents().add(o);
    Injector injector = Guice.createInjector(module);
    Serializer serializer = injector.<Serializer>getInstance(Serializer.class);
    SaveOptions options = SaveOptions.newBuilder().format().getOptions();
    String serialized = serializer.serialize(o, options);
    CharSequence _readTextFile = fsa.readTextFile(path);
    String _plus = (_readTextFile + "\n");
    String _plus_1 = (_plus + serialized);
    fsa.generateFile(path, _plus_1);
  }
}
