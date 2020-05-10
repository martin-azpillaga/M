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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EReference
import java.util.HashMap
import m.m.MPackage
import static m.build.FeatureKind.*

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
		write(get(parent.toString, "Theia", "electron-builder.yml"), electronBuilder.toString.bytes)
		
		copy(get(parent.toString,"build","libs","Source-ls-ls.jar"),get(parent.toString,"Theia","ls.jar"),StandardCopyOption.REPLACE_EXISTING)
		
		println("Done.")
		
		blocks()
		
		println("Blocks too.")
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
			    "@typefox/monaco-editor-core": "0.14.6",
			    "m" : "./m"
		  	},
		  	"devDependencies": {
		    	"@theia/cli": "next"
		  	},
		  	"theia": {
		    	"target": "electron"
		  	},
		  	"scripts":
		  	{
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
		        this.node.innerHTML = `<object type="text/html" data="../../Docs/site/index.html" style="width:100%;height:100%"/>`
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
		        registry.registerMenuAction(CommonMenus.EDIT_FIND, {
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
		        this.node.innerHTML = `<object type="text/html" data="../../Blockly/index.html" style="width:100%;height:100%"/>`
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
		            const jar = path.resolve(__dirname, '../../ls.jar');
		    
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
	
	private static def electronBuilder()
	 {
	 	'''
	 	asar: false
	 	'''
	 }
	 
	 
	 
	 
	 
	 
	 
	static var categories = new ArrayList<Category>
	static var left = new HashMap<EClass,Boolean>
	
	private def static void blocks()
	{
		categories.add(new Category=>[name="Root"])
		
		var model = MPackage.eINSTANCE;
		
		for (eclass : model.EClassifiers.filter(EClass))
		{
			if (eclass.EStructuralFeatures.filter(EReference).exists[containment])
			{
				eclass.analyze
			}
			else if (eclass.EAllSuperTypes.exists[name.equals("Expression")])
			{
				eclass.analyze
			}
		}
		
		for (var i = 0; i < categories.size; i++)
		{
			var category = categories.get(i)
			category.color = 100+50*i
			
			for (var b = 0; b < category.blocks.size; b++)
			{
				val block = category.blocks.get(b)
				var superTypes = new ArrayList<EClass>(block.type.EAllSuperTypes)
				superTypes.add(block.type)
				var subTypes = block.type.EPackage.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(block.type)].toList
				subTypes.add(block.type)
				
				if (superTypes.exists[left.containsKey(it) && left.get(it)])
				{
					block.leftConnection.addAll(block.type)
				}
				else if (superTypes.exists[left.containsKey(it)])
				{
					block.topConnection.addAll(block.type)
					if (block.type.ESuperTypes.empty)
					{
						block.bottomConnection.add(block.type)
					}
					else
					{
						val superType = block.type.ESuperTypes.head
						var superSub = model.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(superType)].toList
						superSub.add(block.type)
						block.bottomConnection.addAll(superSub)
					}
				}
				
				for (input : block.inputs)
				{
					if (input.kind == multiple)
					{
						var left = input.types.exists[left.containsKey(it) && left.get(it)]
						if (left)
						{
							input.kind = mutation
						}
					}
				}
			}
		}
		var destination = get("../Theia/Blockly/custom2.js")
		createDirectories(destination.parent)
		write(destination, categories.print.toString.bytes);
		write(get(destination.parent.toString, "index.html"), index.toString.bytes)
		Main.execute("wget https://raw.githubusercontent.com/google/blockly/master/blockly_compressed.js --output-document ../Theia/Blockly/blockly.js", ".")
	}
	
	private def static analyze(EClass eclass)
	{
		var block = new Block=>[name=eclass.name type=eclass]
		
		var fields = new ArrayList<String>
		for (feature : eclass.EStructuralFeatures)
		{
			if (feature instanceof EAttribute)
			{
				fields.add(feature.name)
			}
			else if (feature instanceof EReference)
			{
				if (feature.containment)
				{
					val type = feature.EType as EClass
					var subTypes = eclass.EPackage.eAllContents.filter(EClass).filter[it.EAllSuperTypes.contains(type)].toList
					subTypes.add(type)
					
					var Input input = new Input=>[name=feature.name]
					if (feature.upperBound == 1)
					{
						input.kind = single
						for (subType : subTypes)
						{
							left.put(subType, true)
						}
					}
					else
					{
						input.kind = multiple
						for (subType : subTypes)
						{
							if (!left.containsKey(subType))
							{
								left.put(subType, false)
							}
						}
						
					}
					input.types.addAll(subTypes)
					input.fields.addAll(fields)
					block.inputs.add(input)
					fields = new ArrayList<String>
				}
			}
		}
		
		if (!fields.empty)
		{
			var input = new Input=>[kind=dummy]
			input.fields.addAll(fields)
			block.inputs.add(input)
		}
		
		var superTypes = eclass.ESuperTypes
		if (superTypes.empty)
		{
			categories.findFirst[name=="Root"].blocks.add(block)
		}
		else if (superTypes.size == 1)
		{
			val superType = superTypes.head.name
			var category = categories.findFirst[name==superType]
			if (category === null)
			{
				category = new Category=>[name=superType]
				categories.add(category)
			}
			category.blocks.add(block)
		}
	}
	
	private def static print(List<Category> categories)
	{
		'''
		var toolbox = document.createElement('XML')
		toolbox.setAttribute('xmlns','https://developers.google.com/blockly/xml')
		toolbox.innerHTML =
		`
		«FOR category : categories»
		<category name='«category.name»'>
		«FOR block : category.blocks»
		<block type='«block.name»'></block>
		«ENDFOR»
		</category>
		«ENDFOR»
		`
		Blockly.inject('blocklyDiv', {toolbox: toolbox})
		
		«FOR category : categories»
		«FOR block : category.blocks»
		Blockly.Blocks['«block.name»'] =
		{
			init: function()
			{
				«IF !block.topConnection.empty»
				this.setPreviousStatement(true, «block.topConnection.s»)
				«ENDIF»
				«IF !block.bottomConnection.empty»
				this.setNextStatement(true, «block.bottomConnection.s»)
				«ENDIF»
				«IF !block.leftConnection.empty»
				this.setOutput(true, «block.leftConnection.s»)
				«ENDIF»
				«FOR input : block.inputs»
				«IF input.kind == single»
				this.appendValueInput('«input.name»').setCheck(«input.types.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == multiple»
				this.appendStatementInput('«input.name»').setCheck(«input.types.s»)«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ELSEIF input.kind == mutation»
				var dummy = this.appendDummyInput('«input.name»')
				dummy«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR».appendField(new Blockly.FieldNumber(0,0,null,0, function (value)
				{
					this.sourceBlock_?.update(dummy, value)
				}), '«input.name»')
				«ELSEIF input.kind == dummy»
				this.appendDummyInput('«input.name»')«FOR field : input.fields».appendField(new Blockly.FieldTextInput('«field»'),'«field»')«ENDFOR»
				«ENDIF»
				«ENDFOR»
				this.setInputsInline(true)
				this.setColour(«category.color»)
			},
			«IF block.inputs.exists[kind==mutation]»
			mutationToDom: function ()
			{
				var mutation = document.createElement('mutation')
				«FOR input : block.inputs.filter[kind==mutation]»
				var «input.name» = this.getFieldValue('«input.name»')
				mutation.setAttribute('«input.name»', «input.name»)
				«ENDFOR»
				return mutation
			},
			domToMutation: function (xml)
			{
				«FOR input : block.inputs.filter[kind==mutation]»
				var «input.name» = xml.getAttribute('«input.name»')
				var input
				for (var i=0; i<this.inputList.length;i++)
				{
					if (this.inputList[i].name === '«input.name»')
					{
						input = this.inputList[i]
					}
				}
				this.update(input, «input.name»)
				«ENDFOR»
			},
			update: function (input, value)
			{
				value = Number(value)
				var index = this.inputList.indexOf(input)
				for (var i=this.inputList.length-1; i > index; i--)
				{
					if (this.inputList[i].name.startsWith(input.name))
					{
						this.removeInput(this.inputList[i].name)
					}
				}
				«FOR input : block.inputs.filter[kind==mutation]»
				for (var i=0; i < value; i++)
				{
					this.appendValueInput('«input.name»'+i)
					if (this.inputList.length-1 != index+1)
					{
						this.moveNumberedInputBefore(this.inputList.length-1, index+1)
					}
				}
				«ENDFOR»
			}
			«ENDIF»
		}
		«ENDFOR»
		«ENDFOR»
		'''
	}
	
	private static def index()
	{
		'''
		<div id="blocklyDiv"></div>
		<script src="blockly.js"></script>
		<script src="custom2.js"></script>
		'''
	}
	
	private static def s(List<EClass> list)
	{
		if (list.empty)
		{
			return null
		}
		else if (list.size == 1)
		{
			return "'"+list.head.name+"'"
		}
		else
		{
			return "["+list.map["'"+it.name+"'"].join(",")+"]"
		}
	}
}


class Category
{
	public String name;
	public int color;
	public List<Block> blocks = new ArrayList<Block>();
}
class Block
{
	public EClass type
	public String name
	public List<EClass> topConnection = new ArrayList<EClass>
	public List<EClass> bottomConnection = new ArrayList<EClass>
	public List<EClass> leftConnection = new ArrayList<EClass>
	public List<Input> inputs = new ArrayList<Input>()
}
		
class Input
{
	public FeatureKind kind;
	public String name;
	public List<EClass> types = new ArrayList<EClass>();
	public List<String> fields = new ArrayList<String>();
}
enum FeatureKind
{
	single, multiple, mutation, dummy
}