package m.ui;

import m.ui.AbstractTestuaUiModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;

@FinalFieldsConstructor
@SuppressWarnings("all")
public class TestuaUiModule extends AbstractTestuaUiModule {
  public TestuaUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
