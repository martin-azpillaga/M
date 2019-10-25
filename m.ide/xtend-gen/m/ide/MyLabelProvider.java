package m.ide;

import org.eclipse.xtext.ide.labels.SimpleNameLabelProvider;
import org.eclipse.xtext.ide.server.WorkspaceManager;
import org.eclipse.xtext.resource.XtextResourceSet;

@SuppressWarnings("all")
public class MyLabelProvider extends SimpleNameLabelProvider {
  private WorkspaceManager workspace;
  
  public XtextResourceSet X() {
    return this.workspace.getProjectManager("").getResourceSet();
  }
}
