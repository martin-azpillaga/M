[ ! -f lib/classes/xtext.ide.jar ] && curl https://search.maven.org/remotecontent?filepath=org/eclipse/xtext/org.eclipse.xtext.ide/2.20.0/org.eclipse.xtext.ide-2.20.0.jar -o lib/classes/xtext.ide.jar

[ ! -f lib/source/xtext.ide.jar ] && curl https://search.maven.org/remotecontent?filepath=org/eclipse/xtext/org.eclipse.xtext.ide/2.20.0/org.eclipse.xtext.ide-2.20.0-sources.jar -o lib/source/xtext.ide.jar

[ ! -f lib/classes/guice.jar ] && curl https://search.maven.org/remotecontent?filepath=com/google/inject/guice/4.2.2/guice-4.2.2.jar -o lib/classes/guice.jar

javac -d "bin" -cp "src/:lib/classes/*" src/m/*/*.java
java -cp "bin/:lib/classes/*" m.main.Main