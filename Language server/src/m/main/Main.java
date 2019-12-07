package m.main;

import org.eclipse.xtext.ide.server.*;

public class Main {

    public static void main(final String[] args) {

        final var it = new LaunchArgs();
        it.setIn(System.in);

        it.setOut(System.out);
        System.out.println("Hello World!");
    }
}