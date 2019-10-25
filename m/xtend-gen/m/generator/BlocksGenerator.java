package m.generator;

import m.cBlocks.File;
import m.transformation.BlocksToText;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class BlocksGenerator extends AbstractGenerator {
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    EObject _head = IterableExtensions.<EObject>head(resource.getContents());
    if ((_head instanceof File)) {
      BlocksToText blocksToText = new BlocksToText();
      EObject _head_1 = IterableExtensions.<EObject>head(resource.getContents());
      blocksToText.generate(((File) _head_1), fsa, "Text/");
    }
  }
}
