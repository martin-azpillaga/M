package m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xcore.XcoreStandaloneSetup;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGenerator;
import org.eclipse.xtext.xtext.generator.XtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.generator.GeneratorFragment2;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessFragment2;
import org.eclipse.xtext.xtext.generator.model.project.BundleProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2;
import org.eclipse.xtext.xtext.generator.validation.ValidatorFragment2;

class Main
{
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		var source = args[0];
		var projectName = args[1];
		var target = source+"/..";
		
		Files.createDirectories(Paths.get(source,projectName+".ide"));
		Files.createDirectories(Paths.get(source,projectName+".ui"));
		
		var injector = new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(XtextResourceSet.class);
		var generator = injector.getInstance(GeneratorDelegate.class);
		var encoding = new IEncodingProvider.Runtime();
		encoding.setDefaultEncoding("UTF-8");
		var registry = new IResourceServiceProvider.Registry.RegistryProvider().get();
		var fsa = new JavaIoFileSystemAccess(registry, encoding);
		fsa.setOutputPath(target+"/java");
		fsa.setWriteTrace(false);
		
		var xtextGenerator = new XtextGenerator();
		var configuration = new DefaultGeneratorModule();
		var project = new StandardProjectConfig();
		var eclipsePlugin = new BundleProjectConfig();
		var code = new CodeConfig();
		
		xtextGenerator.setConfiguration(configuration);
		configuration.setProject(project);
		configuration.setCode(code);
		project.setEclipsePlugin(eclipsePlugin);
		project.setBaseName(projectName);
		project.setRootPath(source);
		eclipsePlugin.setEnabled(true);
		code.setEncoding("UTF-8");
		code.setLineDelimiter("\n");
		code.setPreferXtendStubs(true);
		
		
		var xcores = new HashSet<Path>();
		
		Files.walk(Paths.get(source)).forEach(p->
		{
			var name = p.toString();
			
			if (!name.contains("/bin/"))
			{			
				if (name.endsWith(".xtext"))
				{
					var language = new XtextGeneratorLanguage();
					var minor = p.getFileName().toString().replace(".xtext", "");
					var languageName = projectName+"."+minor;
					language.setName(languageName);
					var extension = p.getFileName().toString().replace(".xtext", "").toLowerCase();
					language.setFileExtensions(extension);
					language.addFragment(new GrammarAccessFragment2());
					language.addFragment(new XtextAntlrGeneratorFragment2());
					xtextGenerator.addLanguage(language);
					var validator = p.getParent().toString()+"/validation/"+minor+"Validator.xtend";
					var gen = p.getParent().toString()+"/generator/"+minor+"Generator.xtend";
					if (Files.exists(Paths.get(validator)))
					{
						language.addFragment(new ValidatorFragment2());
					}
					if (Files.exists(Paths.get(gen)))
					{
						language.addFragment(new GeneratorFragment2());
					}
				}
				else if (name.endsWith(".xcore"))
				{
					xcores.add(p);
					generator.doGenerate(resourceSet.getResource(URI.createFileURI(p.toString()), true), fsa);
				}
			}
		});
		for (var l : xtextGenerator.getLanguageConfigs())
		{
			for (var x : xcores)
			{
				var name = "platform:/resource"+x.toString().replace(source, "");
				l.addReferencedResource(name);
			}
		}
		xtextGenerator.invoke(null);
		
		var subfolders = new String[] {"/"+projectName+"/src","/"+projectName+"/src-gen", "/"+projectName+".ide/src","/"+projectName+".ide/src-gen"};
		var stubs = new ArrayList<Path>();
		
		for (var subfolder : subfolders)
		{
		Files.walk(Paths.get(source+subfolder)).forEach(file->
		{
			var name = file.toString();
			
			if (!name.toString().contains(".ui") && !name.toString().contains("/bin/"))
			{
				if (name.endsWith("xtend"))
				{
					var path = Paths.get(target,"java",file.toString().replace("xtend", "java").replace(source+subfolder, ""));
					stubs.add(path);
					try {
						Files.createDirectories(path.getParent());
						var lines = new ArrayList<CharSequence>();
						var inside = false;
						for (var line : Files.readAllLines(file))
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
						Files.write(path, lines);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				else if (name.endsWith("java"))
				{
					var path = Paths.get(target,"java",file.toString().replace(source+subfolder, ""));
					try
					{
						Files.createDirectories(path.getParent());
						Files.write(path, Files.readAllBytes(file));
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		}
		// Compile java with stubs
		var names = new ArrayList<String>();
		Files.walk(Paths.get(target,"java")).forEach(f ->
		{
			if (f.toString().endsWith(".java"))
			{
				names.add(f.toString());
			}
		});
		var parameters = String.join(" ", names);
		execute("javac -d "+target+"/classes -cp "+target+"/lib/*:"+target+"/java "+parameters);
		
		
		// Delete stubs
		for (var stub : stubs)
		{
			Files.delete(stub);
			var classFile = stub.toString().replace("/java/", "/classes/").replace(".java", ".class");
			Files.delete(Paths.get(classFile));
		}
		
		// Compile xtend into java
		var command = "java -cp "+target+"/lib/*:"+target+"/classes org.eclipse.xtend.core.compiler.batch.Main -useCurrentClassLoader -d out " + source+"/m/src " + source+"/m.ide/src";
		execute(command);

		// Copy compiled xtend from out to java
		System.out.println("Copying compiled Xtend files...");
		Files.walk(Paths.get("out")).forEach(p->
		{
			if (p.toFile().isFile())
			{
				var path = p.toString().replace("out", target+"/java");
			
				try
				{
					Files.copy(p, Paths.get(path),StandardCopyOption.REPLACE_EXISTING);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		// Compile java for real
		var realNames = new ArrayList<String>();
		Files.walk(Paths.get(target,"java")).forEach(f ->
		{
			if (f.toString().endsWith(".java"))
			{
				realNames.add(f.toString());
			}
		});
		var realParameters = String.join(" ", realNames);
		execute("javac -d "+target+"/classes -cp "+target+"/lib/*:"+target+"/java "+realParameters);
		
		// Create fat jar: Extract dependency jars, remove signatures, add ISetup service for each language and set ServerLauncher as main class 
		execute("find "+target+"/lib -exec unzip -q -o -d "+target+"/classes {} ;");
		execute("rm "+target+"/classes/META-INF/ECLIPSE_.RSA "+target+"/classes/META-INF/ECLIPSE_.SF");
		System.out.println("Setting ISetup service for each language...");
		Files.write(Paths.get(target,"classes","META-INF","services","org.eclipse.xtext.ISetup"), ("m.ide.BlocksIdeSetup\nm.ide.MIdeSetup\n\n").getBytes());
		execute("jar cfe "+target+"/m.jar org.eclipse.xtext.ide.server.ServerLauncher -C "+target+"/classes/ .");
		
		System.out.println("Done");
		System.exit(0);
	}
	
	private static void execute(String command) throws IOException, InterruptedException
	{
		System.out.println("Executing "+command);
		var p = Runtime.getRuntime().exec(command);
		String line;
		var in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = in.readLine()) != null)
		{
		    System.out.println(line);
		}
		var error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while ((line = error.readLine()) != null)
		{
			System.out.println(line);
		}
		p.waitFor();
		System.out.println(p.exitValue());
	}
}