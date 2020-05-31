package m.build

import static java.nio.file.Paths.*
import static java.nio.file.Files.*
import java.util.ArrayList
import java.nio.file.Path
import java.util.List
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.File
import java.nio.file.StandardCopyOption

class Main
{
	static String project
	
	def static void main(String[] arguments)
	{
		
		var projectPath = get(arguments.get(0))
		var parent = projectPath.parent
		project = projectPath.fileName.toString
		
		
		createDirectories(get(projectPath.toString+".ide"))
		createDirectories(get(projectPath.toString+".ui"))
		
		var grammars = walk(projectPath).filter[!toString.contains("/bin/") && toString.endsWith(".xtext")].map[fileName]
		var gs = new ArrayList<String>
		for (grammar : grammars.toArray)
		{
			gs.add((grammar as Path).toString.replace(".xtext",""))
		}
		var workflow = workflow(gs)
		write(get(projectPath.toString, "src", project, "Workflow.mwe2"), workflow.toString.bytes)
		write(get(parent.toString, "build.gradle"),gradle(gs).toString.bytes)
		execute("gradle shadowJar", parent.toString)
		
		var theiaPackage = get(parent.toString, "Theia", "package.json")
		createDirectories(theiaPackage.parent)
		write(theiaPackage,applicationPackage.toString.bytes)
		
		var extensionFolder = get(parent.toString, "Theia", "m").toString
		
		var extensionPackage = get(parent.toString, "Theia", "m", "package.json")
		createDirectories(extensionPackage.parent)
		write(extensionPackage, extensionPackage().toString.bytes)
		
		write(get(extensionPackage.parent.toString,"tsconfig.json"), tsconfig.toString.bytes)
		write(get(extensionFolder,"frontend.ts"),frontend.toString.bytes)
		write(get(extensionFolder,"backend.ts"),backend.toString.bytes)
		write(get(extensionFolder,"monaco.d.ts"),monaco.toString.bytes)
		
		copy(get(parent.toString,"build","libs","Code-ls-ls.jar"),get(parent.toString,"Theia","ls.jar"),StandardCopyOption.REPLACE_EXISTING)
		
		println("Language server generated.")
		
		write(get(parent.toString,"Theia","template.html"),htmlTemplate(get(parent.toString,"..","Documentation").toString).toString.bytes)
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
						baseName = "«project»"
						rootPath = rootPath
						eclipsePlugin = { enabled = true }
						createEclipseMetaData = true
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
				java.srcDirs = ['«project»/src', '«project»/src-gen', '«project».ide/src', '«project».ide/src-gen']
				resources.srcDirs = ['«project»/src', '«project»/src-gen', '«project».ide/src', '«project».ide/src-gen']
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
		
		group = '«project»'
		version = 'ls'
		
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
			inputs.file("«project»/src/«project»/M.xtext")
			args += "«project»/src/«project»/Workflow.mwe2"
			args += "-p"
			args += "rootPath=/${projectDir}"
		}
		
		generateXtext.dependsOn(generateXtextLanguage)
		clean.dependsOn(cleanGenerateXtextLanguage)
		eclipse.classpath.plusConfigurations += [configurations.mwe2]
		
		dependencies {
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
	
	static def void execute(String command, String workingDirectory)
	{
		println("Executing "+command+ " in "+workingDirectory)
		var p = Runtime.runtime.exec(command,#[],new File(workingDirectory))
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
	
	private static def applicationPackage()
	{
		'''
		{
			"name": "«project»-theia",
			"version": "0.0.0",
			"main": "src-gen/frontend/electron-main.js",
			"private": true,
			"dependencies": {
			    "@theia/callhierarchy": "next",
			    "@theia/electron": "1.1.0",
			    "@theia/file-search": "next",
			    "@theia/git": "next",
			    "@theia/json": "next",
			    "@theia/languages": "next",
			    "@theia/markers": "next",
			    "@theia/messages": "next",
			    "@theia/mini-browser": "next",
			    "@theia/navigator": "next",
			    "@theia/outline-view": "next",
			    "@theia/plugin-ext-vscode": "next",
			    "@theia/preferences": "next",
			    "@theia/preview": "next",
			    "@theia/search-in-workspace": "next",
			    "@theia/terminal": "next",
			    "@typefox/monaco-editor-core": "0.14.6",
			    "m" : "./m"
		  	},
		  	"devDependencies": {
		    	"@theia/cli": "next",
				"puppeteer": "3.1.0",
				"mocha": "7.1.2",
				"chai-as-promised": "7.1.1"
		  	},
		  	"scripts": {
				"buildElectron": "theia rebuild:electron && theia build --app-target electron",
				"startElectron": "theia start --app-target electron",
				"buildBrowser": "theia rebuild:browser && theia build --app-target browser",
				"startBrowser": "theia start --app-target browser",
				"update-m-extension": "cd m && tsc && cd .. && yarn add ./m",
				"test": "mocha test.js",
				"testSlow": "mocha test.js slow"
			},
		  	"build":
		  	{
		  		"appId": "azpillaga.world.m-theia",
		  		"productName": "m",
		  		"linux":
		  		{
		  			"target": "AppImage",
		  			"executableName": "m"
		  		},
				"win":
				{
					"target": ["portable"]
				},
		  		"extraFiles": [ "ls.jar" ]
		  	}
		}'''
	}
	
	private static def extensionPackage()
	{
		'''
		{
		    "name": "m",
		    "version": "1.0.0",
		    "keywords": ["theia-extension"],
		    "dependencies": 
		    {
		        "@theia/core": "next",
		        "@theia/languages": "next",
		        "@typefox/monaco-editor-core": "0.14.6"
		    },
		    "devDependencies":
		    {
		        "typescript": "3.4.3"
		    },
		    "theiaExtensions":
		    [
		        {"frontend": "frontend", "backend": "backend"}
		    ]
		}
		'''
	}
	
	private static def tsconfig()
	{
		'''
		{
		    "compilerOptions": {
		        "strict": true,
		        "experimentalDecorators": true,
		        "noUnusedLocals": true,
		        "emitDecoratorMetadata": true,
		        "downlevelIteration": true,
		        "module": "commonjs",
		        "moduleResolution": "node",
		        "target": "es5",
		        "lib": [
		            "es6",
		            "dom"
		        ],
		        "sourceMap": true,
		        "skipLibCheck": true
		    }
		}
		'''
	}
	
	private static def frontend()
	{
		'''
		import {ContainerModule, injectable} from 'inversify'
		import {CommandRegistry, MenuModelRegistry} from '@theia/core'
		import { AbstractViewContribution, CommonMenus, bindViewContribution, FrontendApplicationContribution, WidgetFactory, BaseWidget } from '@theia/core/lib/browser';
		import { LanguageClientContribution, BaseLanguageClientContribution } from '@theia/languages/lib/browser'
		/// <reference types='@typefox/monaco-editor-core/monaco'/>
		
		
		export default new ContainerModule(bind=>
		{
		    bind(LanguageClientContribution).to(MClient);
		
		    bindViewContribution(bind, DocumentationWidgetContribution);
		    bind(FrontendApplicationContribution).toService(DocumentationWidgetContribution);
		    bind(DocumentationWidget).toSelf();
		    bind(WidgetFactory).toDynamicValue(ctx => ({
		        id: DocumentationWidget.ID,
		        createWidget: () => ctx.container.get<DocumentationWidget>(DocumentationWidget)
		    })).inSingletonScope();
		
		    bindViewContribution(bind, BlocksWidgetContribution);
		    bind(FrontendApplicationContribution).toService(BlocksWidgetContribution);
		    bind(BlocksWidget).toSelf();
		    bind(WidgetFactory).toDynamicValue(ctx => ({
		        id: BlocksWidget.ID,
		        createWidget: () => ctx.container.get<BlocksWidget>(BlocksWidget)
		    })).inSingletonScope();
		});
		
		@injectable()
		class MClient extends BaseLanguageClientContribution
		{
		    id='mserver';
		    name='mserver';
		
		    protected get globPatterns()
		    {
		        return ['**/*.m']
		    }
		}
		
		registerM();
		
		export function registerM()
		{
		    monaco.languages.register
		    ({
		        id: 'mserver',
		        aliases: ['mserver'],
		        extensions: ['.m'],
		        mimetypes: ['text/m']
		    })
		}
		
		
		
		@injectable()
		class DocumentationWidget extends BaseWidget
		{
		    static ID = 'documentation-widget'
		
		    constructor()
		    {
		        super();
		        this.id = DocumentationWidget.ID;
		        this.title.label = 'Documentation';
		        this.title.caption = 'Documentation for M';
		        this.title.closable = true;
		        this.node.innerHTML = `<object type="text/html" data="../Documentation/README.html" style="width:100%;height:100%"/>`
		    }
		}
		
		export const DocumentationWidgetCommand =
		{
		    id: 'documentation.command',
		    label: 'Documentation'
		}
		
		class DocumentationWidgetContribution extends AbstractViewContribution<DocumentationWidget>
		{
		    constructor()
		    {
		        super(
		        {
		            widgetId: DocumentationWidget.ID,
		            widgetName: "Documentation Widget",
		            defaultWidgetOptions: {area: 'left'},
		            toggleCommandId: 'fileNavigator:toggle',
		            toggleKeybinding: 'ctrlcmd+shift+1'
		        })
		    }
		
		    registerCommands(commands: CommandRegistry)
		    {
		        commands.registerCommand(DocumentationWidgetCommand,
		        {
		            execute: () => super.openView({activate: false, reveal: true})
		        })
		    }
		
		    registerMenus(registry: MenuModelRegistry)
		    {
		        registry.registerMenuAction(CommonMenus.VIEW, {
		            commandId: DocumentationWidgetCommand.id,
		            label: DocumentationWidgetCommand.label
		        });
		        super.registerMenus(registry)
		    }
		}
		
		
		
		@injectable()
		class BlocksWidget extends BaseWidget
		{
		    static ID = 'blocks-widget'
		
		    constructor()
		    {
		        super();
		        this.id = BlocksWidget.ID;
		        this.title.label = 'Blocks';
		        this.title.caption = 'Blocks for M';
		        this.title.closable = true;
		        this.node.innerHTML = `<object type="text/html" data="../Blockly/index.html" style="width:100%;height:100%"/>`
		    }
		}
		
		export const BlocksWidgetCommand =
		{
		    id: 'blocks.command',
		    label: 'Blocks'
		}
		
		class BlocksWidgetContribution extends AbstractViewContribution<BlocksWidget>
		{
		    constructor()
		    {
		        super(
		        {
		            widgetId: BlocksWidget.ID,
		            widgetName: "Blocks Widget",
		            defaultWidgetOptions: {area: 'left'},
		            toggleCommandId: 'fileNavigator:toggle',
		            toggleKeybinding: 'ctrlcmd+shift+1'
		        })
		    }
		
		    registerCommands(commands: CommandRegistry)
		    {
		        commands.registerCommand(BlocksWidgetCommand,
		        {
		            execute: () => super.openView({activate: false, reveal: true})
		        })
		    }
		
		    registerMenus(registry: MenuModelRegistry)
		    {
		        registry.registerMenuAction(CommonMenus.VIEW, {
		            commandId: BlocksWidgetCommand.id,
		            label: BlocksWidgetCommand.label
		        });
		        super.registerMenus(registry)
		    }
		}
		'''
	}
	
	private static def backend()
	{
		'''
		import {ContainerModule, injectable} from 'inversify'
		import {BaseLanguageServerContribution, LanguageServerContribution, IConnection} from '@theia/languages/lib/node'
		import { createSocketConnection } from 'vscode-ws-jsonrpc/lib/server'
		import * as path from 'path';
		import * as net from 'net'
		
		export default new ContainerModule(bind =>
		{
		    bind(LanguageServerContribution).to(MContribution)
		});
		
		@injectable()
		class MContribution extends BaseLanguageServerContribution
		{
		    id = 'mserver';
		    name = 'mserver';
		    
		    start(clientConnection: IConnection): void
		    {
		        console.log("Start called");
		        let socketPort = getPort();
		        if (socketPort) {
		            const socket = new net.Socket()
		            const serverConnection = createSocketConnection(socket, socket, () => {
		                socket.destroy()
		            });
		            this.forward(clientConnection, serverConnection)
		            socket.connect(socketPort)
		        } else {
		            console.log("java jarring");
		            const jar = path.resolve(__dirname, '../../../../ls.jar');
		    
		            const command = 'java';
		            const args: string[] = ['-jar',jar];
		            const serverConnection = this.createProcessStreamConnection(command, args);
		            this.forward(clientConnection, serverConnection);
		        }
		    }
		}
		
		function getPort()
		{
		    let arg = process.argv.filter(arg => arg.startsWith('--LSP_PORT='))[0]
		    if (!arg) {
		        return undefined
		    } else {
		        return Number.parseInt(arg.substring('--LSP_PORT='.length))
		    }
		}
		'''
	}
	
	private static def monaco()
	{
		'''
		/// <reference types='@typefox/monaco-editor-core/monaco'/>
		'''
	}
	
	private static def normalize(File file, String root)
	{
		var path = file.name.replace(root,"")
		for (n : #[0,1,2,3,4,5,6,7,8,9])
		{
			path = path.replace(n+". ", "")
		}
		return path
	}
	
	private static def String convert(File file, String root, String folder)
	{
		'''
		«IF !file.listFiles.empty»
		<ul>
		«FOR f : file.listFiles.map[toString].sort»
		  <li>
		    «IF new File(f).isDirectory»
		    <a href="#">«new File(f).normalize(root)»</a>
		    «convert(new File(f),root, f.replace(root,"")+"/")»
		    «ELSE»
		    <a href="«f.replace(root,"").replace(".md",".html")»">«new File(f).normalize(root)»</a>
		    «ENDIF»
		  </li>
		«ENDFOR»
		</ul>
		«ENDIF»'''
	}
	private static def htmlTemplate(String root)
	{
		'''
		<!DOCTYPE html>
		<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		  <link rel="stylesheet" href="main.css">
		  <title>M Documentation</title>
		</head>
		<body>
		  <nav>
		  	«new File(root).convert(root+"/","")»
		  </nav>
		$for(include-before)$
		$include-before$
		$endfor$
		$if(title)$
		<header id="title-block-header">
		<h1 class="title">$title$</h1>
		$if(subtitle)$
		<p class="subtitle">$subtitle$</p>
		$endif$
		$for(author)$
		<p class="author">$author$</p>
		$endfor$
		$if(date)$
		<p class="date">$date$</p>
		$endif$
		</header>
		$endif$
		$if(toc)$
		<nav id="$idprefix$TOC" role="doc-toc">
		$if(toc-title)$
		<h2 id="$idprefix$toc-title">$toc-title$</h2>
		$endif$
		$table-of-contents$
		</nav>
		$endif$
		$body$
		$for(include-after)$
		$include-after$
		$endfor$
		</body>
		</html>
		'''
	}
}

