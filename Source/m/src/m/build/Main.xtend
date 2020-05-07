package m.build

import static java.nio.file.Paths.*
import static java.nio.file.Files.*
import java.util.ArrayList
import java.nio.file.Path
import java.util.List
import java.io.BufferedReader
import java.io.InputStreamReader

class Main
{
	static String project
	
	def static void main(String[] arguments)
	{
		var projectPath = get(arguments.get(0))
		var parent = projectPath.parent
		project = projectPath.fileName.toString
		
		var grammars = walk(projectPath).filter[!toString.contains("/bin/") && toString.endsWith(".xtext")].map[fileName]
		var gs = new ArrayList<String>
		for (grammar : grammars.toArray)
		{
			gs.add((grammar as Path).toString.replace(".xtext",""))
		}
		var workflow = workflow(gs)
		write(get(projectPath.toString, "src", project, "Workflow.mwe2"), workflow.toString.bytes)
		write(get(parent.toString, "build.gradle"),gradle(gs).toString.bytes)
		execute("gradle -b ../build.gradle shadowJar")
	}
	
	private static def workflow(String[] grammars)
	{
		'''
		module m.Workflow
		
		import org.eclipse.xtext.xtext.generator.*
		import org.eclipse.xtext.xtext.generator.model.project.*
		
		var rootPath = ".."
		
		Workflow
		{
			component = XtextGenerator
			{
				configuration =
				{
					project = StandardProjectConfig
					{
						baseName = "m"
						rootPath = rootPath
						genericIde = { enabled = true }
					}
					code =
					{
						encoding = "UTF-8"
						lineDelimiter = "\n"
						preferXtendStubs = true
					}
				}
				«FOR grammar : grammars»
				language = StandardLanguage
				{
					name = "«project».«grammar»"
					fileExtensions = "«grammar.toLowerCase»"
					serializer = { generateStub = false	}
					scopeProvider = { generateStub = false }
				}
				«ENDFOR»
			}
		}
		'''
	}
	
	private static def gradle(List<String> grammars)
	{
		'''
		buildscript {
			repositories {
				mavenCentral()
			}
			dependencies {
				classpath 'org.xtext:xtext-gradle-plugin:2.0.8'
			}
		}
		plugins {
			id 'com.github.johnrengelman.shadow' version '5.1.0'
		}
		
		ext.xtextVersion = '2.20.0'
		repositories {
			mavenCentral()
		}
		
		apply plugin: 'java'
		dependencies {
			compile platform("org.eclipse.xtext:xtext-dev-bom:${xtextVersion}")
		}
		
		apply plugin: 'org.xtext.xtend'
		sourceSets {
			main {
				java.srcDirs = ['m/src', 'm/src-gen', 'm.ide/src', 'm.ide/src-gen']
				resources.srcDirs = ['m/src', 'm/src-gen', 'm.ide/src', 'm.ide/src-gen']
				xtendOutputDir = 'xtend-gen'
			}
		}
		sourceSets.all {
			resources.exclude '**/*.g', '**/*.mwe2', '**/*.xtend', '**/*._trace'
		}
		jar {
			from('model') {
				into('model')
			}
			manifest {
				attributes 'Bundle-SymbolicName': project.name
			}
		}
		
		plugins.withId('war') {
			webAppDirName = "WebRoot"
		}
		apply plugin: 'eclipse'
		
		group = 'm'
		version = '1.0.0-SNAPSHOT'
		
		sourceCompatibility = '11'
		targetCompatibility = '11'
		
		configurations.all {
			exclude group: 'asm'
		}
		
		dependencies {
			compile "org.eclipse.xtext:org.eclipse.xtext:${xtextVersion}"
			compile "org.eclipse.xtext:org.eclipse.xtext.xbase:${xtextVersion}"
		}
		configurations {
			mwe2 {
				extendsFrom compile
			}
		}
		
		dependencies {
			mwe2 'org.eclipse.emf:org.eclipse.emf.mwe2.launch'
			mwe2 "org.eclipse.xtext:org.eclipse.xtext.common.types:${xtextVersion}"
			mwe2 "org.eclipse.xtext:org.eclipse.xtext.xtext.generator:${xtextVersion}"
			mwe2 'org.eclipse.xtext:xtext-antlr-generator'
		}
		
		task generateXtextLanguage(type: JavaExec) {
			main = 'org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher'
			classpath = configurations.mwe2
			args += "m/src/m/Workflow.mwe2"
			args += "-p"
			args += "rootPath=/${projectDir}"
		}
		
		generateXtext.dependsOn(generateXtextLanguage)
		clean.dependsOn(cleanGenerateXtextLanguage)
		eclipse.classpath.plusConfigurations += [configurations.mwe2]
		
		dependencies {
			//compile project(':m')
			compile "org.eclipse.xtext:org.eclipse.xtext.ide:${xtextVersion}"
			compile "org.eclipse.xtext:org.eclipse.xtext.xbase.ide:${xtextVersion}"
		}
		
		apply plugin: 'application'
		apply plugin: 'com.github.johnrengelman.shadow'
		mainClassName = "org.eclipse.xtext.ide.server.ServerLauncher"
		
		shadowJar {
			from(project.convention.getPlugin(JavaPluginConvention).sourceSets.main.output)
			configurations = [project.configurations.runtime]
			exclude('META-INF/INDEX.LIST', 'META-INF/*.SF', 'META-INF/*.DSA', 'META-INF/*.RSA','schema/*',
				'.options', '.api_description', '*.profile', '*.html', 'about.*', 'about_files/*',
				'plugin.xml', 'systembundle.properties', 'profile.list')
			classifier = 'ls'
			append('plugin.properties')
		}
		'''
	}
	
	private static def void execute(String command)
	{
		println("Executing "+command)
		var p = Runtime.runtime.exec(command)
		var String line
		var in = new BufferedReader(new InputStreamReader(p.inputStream));
		while ((line = in.readLine()) !== null)
		{
		    println(line);
		}
		var error = new BufferedReader(new InputStreamReader(p.errorStream));
		while ((line = error.readLine()) !== null)
		{
			println(line);
		}
		p.waitFor();
		println("Exited with: "+p.exitValue())
	}
}