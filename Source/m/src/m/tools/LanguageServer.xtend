package m.tools

import java.io.IOException
import static java.nio.file.Paths.*
import static java.nio.file.Files.*
import org.eclipse.xtext.xtext.generator.XtextGeneratorLanguage
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessFragment2
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
import org.eclipse.xtext.xtext.generator.XtextGenerator
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig
import org.eclipse.xtext.xtext.generator.CodeConfig
import org.eclipse.xtext.xtext.generator.validation.ValidatorFragment2
import org.eclipse.xtext.xtext.generator.generator.GeneratorFragment2
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig
import java.nio.file.Path
import org.eclipse.xtext.xtext.generator.ecore.EMFGeneratorFragment2
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.List
import java.nio.file.StandardCopyOption
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2

class LanguageServer
{
	static Path projectPath
	static Path target
	static String project
	
	def static void main(String[] args) throws IOException, InterruptedException
	{
		projectPath = get(args.get(0))
		target = projectPath.parent
		project = projectPath.fileName.toString
		
		var languages = generateXtext
		var stubs = prepareStubs
		compileStubs
		compileXtend(stubs)
		compileJava(languages)
	}
	
	def private static List<String> generateXtext()
	{
		val languages = new ArrayList<String>
		
		createDirectories(get(projectPath+".ide"));
		
		val xtextGenerator = new XtextGenerator=>
		[
			configuration = new DefaultGeneratorModule=>
			[
				project = new StandardProjectConfig=>
				[
					baseName = project
					rootPath = target.toString
					genericIde = new BundleProjectConfig=>
					[
						enabled = true
					]
				]
				code = new CodeConfig=>
				[
					encoding = 'UTF-8'
					lineDelimiter = '\n'
					preferXtendStubs = true
				]
			]
		]
		
		walk(projectPath).forEach
		[
			var name = it.toString
			if (!name.contains('/bin/'))
			{
				if (name.endsWith('.xtext'))
				{
					var language = new XtextGeneratorLanguage
					var minor = it.fileName.toString.replace(".xtext", "")
					language.setName(project+"."+minor);
					language.setFileExtensions(minor.toLowerCase)
					xtextGenerator.addLanguage(language);
					languages.add(minor);
					
					language.addFragment(new GrammarAccessFragment2)
					language.addFragment(new XtextAntlrGeneratorFragment2)
					language.addFragment(new EMFGeneratorFragment2)
					language.addFragment(new SerializerFragment2=>[generateStub=false])
					
					var validator = it.parent.toString+"/validation/"+minor+"Validator.xtend"
					var gen = it.parent.toString+"/generator/"+minor+"Generator.xtend"
					if (exists(get(validator)))
					{
						language.addFragment(new ValidatorFragment2)
					}
					if (exists(get(gen)))
					{
						language.addFragment(new GeneratorFragment2)
					}
				}
			}
		]
		
		xtextGenerator.invoke(null)
		
		return languages
	}
	
	def private static List<Path> prepareStubs()
	{
		var subfolders = #["/src","/src-gen", ".ide/src",".ide/src-gen"]
		val stubs = new ArrayList<Path>
		
		for (subfolder : subfolders)
		{
			walk(get(projectPath+subfolder)).forEach[prepareStub(stubs,subfolder)]
		}
		
		return stubs
	}
	
	def private static void prepareStub(Path path, List<Path> stubs, String subfolder) throws IOException
	{
		var name = path.toString
			
		if (!name.contains("/bin/"))
		{
			if (name.endsWith("xtend"))
			{
				var destination = get(target.toString,"java",path.toString.replace("xtend", "java").replace(projectPath+subfolder, ""));
				stubs.add(destination);
				
				createDirectories(destination.parent)
				var lines = new ArrayList<CharSequence>
				for (line : readAllLines(path))
				{
					if (line.startsWith("package "))
					{
						lines.add(line+(line.endsWith(";")?"":";"));
					}
					else if (line.startsWith("import "))
					{
						lines.add(line+(line.endsWith(";")?"":";"));
					}
					else if (line.startsWith("class "))
					{
						lines.add("public " + line);
						if (!line.endsWith("{"))
						{
							lines.add("{");
						}
					}
					else if (line.startsWith("\toverride createInjector()"))
					{
						lines.add(line.replace("override ", "public com.google.inject.Injector "));
						if (!line.endsWith("{"))
						{
							lines.add("{");
						}
						lines.add("return null;}");
					}
					else if (line.startsWith("\toverride "))
					{
						lines.add(line.replace("override ", "public "));
						if (!line.endsWith("{"))
						{
							lines.add("{");
						}
						lines.add("}");
					}
				}
				lines.add("}");
				write(destination, lines);
				
			}
			else if (name.endsWith("java"))
			{
				var destination = get(target.toString,"java",path.toString.replace(projectPath+subfolder, ""));
			
				createDirectories(destination.parent)
				write(destination, readAllBytes(path));	
			}
		}
	}
	
	def private static void compileStubs()
	{
		val names = new ArrayList<String>
		walk(get(target.toString,"java")).forEach
		[
			if (it.toString.endsWith(".java"))
			{
				names.add(it.toString)
			}
		]
		var parameters = String.join(" ", names);
		execute("javac -d "+target+"/classes -cp "+target+"/lib/*:"+target+"/java "+parameters);
	}
	
	def private static void compileXtend(List<Path> stubs)
	{
		for (stub : stubs)
		{
			var classFile = get(stub.toString.replace("/java/", "/classes/").replace(".java", ".class"))
			delete(stub)
			delete(classFile)
		}
		
		var command = "java -cp "+target+"/lib/*:"+target+"/classes org.eclipse.xtend.core.compiler.batch.Main -useCurrentClassLoader -d out " + projectPath+"/src " + projectPath+".ide/src";
		execute(command);
		
		println("Copying compiled Xtend files...");
		walk(get(projectPath+"/out")).forEach[p|
		{
			if (p.toFile.isFile)
			{
				var path = p.toString.replace("out", "../java");
			
				copy(p, get(path),StandardCopyOption.REPLACE_EXISTING);			
			}
		}]
	}
	
	def private static void compileJava(List<String> languages)
	{
		// Compile java for real
		val realNames = new ArrayList<String>
		walk(get(target.toString,"java")).forEach[f|
		{
			if (f.toString.endsWith(".java"))
			{
				realNames.add(f.toString());
			}
		}]
		var realParameters = String.join(" ", realNames);
		execute("javac -d "+target+"/classes -cp "+target+"/lib/*:"+target+"/java "+realParameters);
		
		// Create fat jar: Extract dependency jars, remove signatures, add ISetup service for each language and set ServerLauncher as main class 
		execute("find "+target+"/lib -exec unzip -q -o -d "+target+"/classes {} ;");
		
		execute("rm "+target+"/classes/META-INF/ECLIPSE_.RSA "+target+"/classes/META-INF/ECLIPSE_.SF");
		
		println("Setting ISetup service for each language...");
		write(get(target.toString,"classes","META-INF","services","org.eclipse.xtext.ISetup"), languages.map[project+".ide."+it+"IdeSetup\n"].join.bytes)
		execute("jar cfe "+target+"/m.jar org.eclipse.xtext.ide.server.ServerLauncher -C "+target+"/classes/ .");
		
		println("Done");
	}
	
	def private static void execute(String command) throws IOException, InterruptedException
	{
		println("Executing "+command)
		var p = Runtime.runtime.exec(command)
		
		var String line
		var in = new BufferedReader(new InputStreamReader(p.inputStream))
		while ((line = in.readLine) !== null)
		{
		    println(line)
		}
		var error = new BufferedReader(new InputStreamReader(p.errorStream));
		while ((line = error.readLine()) !== null)
		{
			println(line)
		}
		p.waitFor
		println("Exited with: " + p.exitValue)	
	}
}