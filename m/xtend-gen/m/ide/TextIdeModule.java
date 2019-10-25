package m.ide;

import m.ide.AbstractTextIdeModule;
import m.ide.MyContentAssist;
import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider;

@SuppressWarnings("all")
public class TextIdeModule extends AbstractTextIdeModule {
  public Class<? extends IdeContentProposalProvider> bindIdeContentProposalProvider() {
    return MyContentAssist.class;
  }
}
