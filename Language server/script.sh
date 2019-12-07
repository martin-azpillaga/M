[ ! -f lib/xtext.ide.jar ] &&
curl https://search.maven.org/remotecontent?filepath=org/eclipse/xtext/org.eclipse.xtext.ide/2.20.0/org.eclipse.xtext.ide-2.20.0.jar -o lib/xtext.ide.jar

javac -cp "src/:lib/*" src/m/*/*.java
java -cp "src/:lib/*" m.main.Main