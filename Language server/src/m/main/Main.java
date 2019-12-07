package m.main;

import com.google.inject.Guice;

import org.eclipse.xtext.ide.server.LaunchArgs;
import org.eclipse.xtext.ide.server.ServerLauncher;
import org.eclipse.xtext.ide.server.ServerModule;

public class Main {

    public static void main(String[] args) 
    {
        var it = new LaunchArgs();
        it.setIn(System.in);
        it.setOut(System.out);
        final ServerLauncher launcher = Guice.createInjector(new ServerModule()).
        <ServerLauncher>getInstance(ServerLauncher.class);
        launcher.start(it);
    }
}