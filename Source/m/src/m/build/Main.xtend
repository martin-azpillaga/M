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
		
		var grammars = walk(projectPath).filter[!toString.contains("/bin/") && toString.endsWith(".xtext")].map[fileName]
		var gs = new ArrayList<String>
		for (grammar : grammars.toArray)
		{
			gs.add((grammar as Path).toString.replace(".xtext",""))
		}
		var workflow = workflow(gs)
		write(get(projectPath.toString, "src", project, "Workflow.mwe2"), workflow.toString.bytes)
		write(get(parent.toString, "build.gradle"),gradle(gs).toString.bytes)
		execute("gradle shadowJar", "..")
		
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
		
		copy(get(parent.toString,"build","libs","Source-ls-ls.jar"),get(parent.toString,"Theia","ls.jar"),StandardCopyOption.REPLACE_EXISTING)
		
		println("Done.")
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
	
	private static def void execute(String command, String workingDirectory)
	{
		println("Executing "+command)
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
			    "@typefox/monaco-editor-core": "0.14.6"
		  	},
		  	"devDependencies": {
		    	"@theia/cli": "next"
		  	},
		  	"theia": {
		    	"target": "electron"
		  	},
		  	"scripts": {
		  		"java": "yarn && theia rebuild:electron && theia build --mode development",
		    	"update": "yarn && theia rebuild:electron && cd m && tsc && cd .. && yarn add ./m && theia build --mode development && theia start"
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
		import {ContainerModule, inject, injectable} from 'inversify'
		import {CommandContribution, CommandRegistry, MessageService, MenuContribution, MenuModelRegistry} from '@theia/core'
		import { AbstractViewContribution, CommonMenus, bindViewContribution, FrontendApplicationContribution, WidgetFactory, BaseWidget } from '@theia/core/lib/browser';
		import { LanguageClientContribution, BaseLanguageClientContribution } from '@theia/languages/lib/browser'
		/// <reference types='@typefox/monaco-editor-core/monaco'/>
		
		
		export default new ContainerModule(bind=>
		{
		    bind(CommandContribution).to(FirstCommand);
		    bind(MenuContribution).to(HelloWorldMenuContribution);
		
		    bindViewContribution(bind, WidgetContribution);
		    bind(FrontendApplicationContribution).toService(WidgetContribution);
		    bind(DocumentationWidget).toSelf();
		    bind(WidgetFactory).toDynamicValue(ctx => ({
		        id: DocumentationWidget.ID,
		        createWidget: () => ctx.container.get<DocumentationWidget>(DocumentationWidget)
		    })).inSingletonScope();
		
		    bind(LanguageClientContribution).to(MClient)
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
		        this.node.innerHTML = `<object type="text/html" data="../../Docs/site/index.html" style="width:100%;height:100%"/>`
		    }
		}
		
		export const DocumentationWidgetCommand =
		{
		    id: 'documentation.command',
		    label: 'Documentation'
		}
		console.log("Container ok");
		
		class WidgetContribution extends AbstractViewContribution<DocumentationWidget>
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
		        registry.registerMenuAction(CommonMenus.EDIT_FIND, {
		            commandId: DocumentationWidgetCommand.id,
		            label: DocumentationWidgetCommand.label
		        });
		        super.registerMenus(registry)
		    }
		}
		
		console.log("Widget contribution ok");
		
		
		
		export const HelloWorldCommand = {
		    id: 'HelloWorld.command',
		    label: "Say hello to me",
		    text: "Hello Martin!"
		};
		
		@injectable()
		export class FirstCommand implements CommandContribution {
		
		    constructor(
		        @inject(MessageService) private readonly messageService: MessageService,
		    ) { }
		
		    registerCommands(registry: CommandRegistry): void {
		        registry.registerCommand(HelloWorldCommand, {
		            execute: () => this.messageService.info(HelloWorldCommand.text)
		        });
		    }
		}
		
		@injectable()
		export class HelloWorldMenuContribution implements MenuContribution {
		
		    registerMenus(menus: MenuModelRegistry): void {
		        menus.registerMenuAction(CommonMenus.EDIT_FIND, {
		                commandId: HelloWorldCommand.id,
		                label: HelloWorldCommand.label
		            });
		    }
		}
		
		
		console.log("Hello command contribution ok");
		
		// register language with monaco on first load
		registerM();
		
		export function registerM() {
		    // initialize monaco
		    monaco.languages.register({
		        id: 'mserver',
		        aliases: ['mserver'],
		        extensions: ['.m'],
		        mimetypes: ['text/m']
		    })
		}
		
		console.log("monaco ok")
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
		
		console.log("I am backend")
		
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
		            const jar = path.resolve(__dirname, '../../ls.jar');
		    
		            const command = 'java';
		            const args: string[] = ['-jar',jar];
		            const serverConnection = this.createProcessStreamConnection(command, args);
		            this.forward(clientConnection, serverConnection);
		        }
		    }
		}
		
		function getPort(): number | undefined
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
}