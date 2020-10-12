(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{378:function(e,a,i){"use strict";i.r(a);var n=i(42),t=Object(n.a)({},(function(){var e=this,a=e.$createElement,i=e._self._c||a;return i("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[i("h1",{attrs:{id:"responsability-view"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#responsability-view"}},[e._v("#")]),e._v(" Responsability view>")]),e._v(" "),i("h2",{attrs:{id:"vscode-extension"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#vscode-extension"}},[e._v("#")]),e._v(" VSCode extension")]),e._v(" "),i("ul",[i("li",[e._v("Define marketplace page's information")]),e._v(" "),i("li",[e._v("Define bracket features and snippets declaratively")]),e._v(" "),i("li",[e._v("Detect missing dependencies (Java 8+) and offer solutions")]),e._v(" "),i("li",[e._v("Detect extension activation events and start language server")]),e._v(" "),i("li",[e._v("Associate .Ⲙ and Ⲙ.json files with Ⲙ and watch for modifications")])]),e._v(" "),i("h3",{attrs:{id:"package-json"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#package-json"}},[e._v("#")]),e._v(" Package.json")]),e._v(" "),i("ul",[i("li",[e._v("Define name version publisher and license")]),e._v(" "),i("li",[e._v("Define descriptions and keywords")]),e._v(" "),i("li",[e._v("Define icon, preview label and no q&a section")]),e._v(" "),i("li",[e._v("Link to webpage, repository and issues")]),e._v(" "),i("li",[e._v("Contribute language Ⲙ for .Ⲙ files")]),e._v(" "),i("li",[e._v("Activate extension when Ⲙ files opened or workspace contains a Ⲙ or Ⲙ.json file")]),e._v(" "),i("li",[e._v("Activation starts in main.js")]),e._v(" "),i("li",[e._v("Define dependency on vscode-language client and vscode engine (required by vsce)")])]),e._v(" "),i("h3",{attrs:{id:"brackets-json"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#brackets-json"}},[e._v("#")]),e._v(" Brackets.json")]),e._v(" "),i("ul",[i("li",[e._v("Indent and dedent automatically with {}")]),e._v(" "),i("li",[e._v("Create folding markers with {}")]),e._v(" "),i("li",[e._v("Highlighting bracket pairs for {} [] and ()")]),e._v(" "),i("li",[e._v("Autoclose brackets {} [] ()")]),e._v(" "),i("li",[e._v("Surround selection with brackets {} [] ()")])]),e._v(" "),i("h3",{attrs:{id:"snippets-json"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#snippets-json"}},[e._v("#")]),e._v(" Snippets.json")]),e._v(" "),i("ul",[i("li",[e._v("Snippets for foreach, if, set and system")]),e._v(" "),i("li",[e._v("Snippets respect current indentation even if they span multiple lines")]),e._v(" "),i("li",[e._v("Snippets have default text and multiple cursor markers that the developer can jump with tab")]),e._v(" "),i("li",[e._v("Snippets description is snippet")])]),e._v(" "),i("p",[e._v("! Only in english !")]),e._v(" "),i("h3",{attrs:{id:"main-js"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#main-js"}},[e._v("#")]),e._v(" Main.js")]),e._v(" "),i("ul",[i("li",[e._v("Listen to activation and deactivation events")]),e._v(" "),i("li",[e._v("Detect java 8+ installed in path by running java -version")]),e._v(" "),i("li",[e._v("Show button to open webpage to download openjdk if necessary by using systems url opener")]),e._v(" "),i("li",[e._v("Detect debugging mode by analyzing process' environment (set in .vscode/launch.json)")]),e._v(" "),i("li",[e._v("If debugging set connection by socket 5007")]),e._v(" "),i("li",[e._v("If not debugging set connection by starting java process and using stdio")]),e._v(" "),i("li",[e._v("Start language client and server")]),e._v(" "),i("li",[e._v("Watch .Ⲙ and Ⲙ.json files and send file modified notifications to server")]),e._v(" "),i("li",[e._v("Stop client when extension gets deactivated")])]),e._v(" "),i("h2",{attrs:{id:"main-package"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#main-package"}},[e._v("#")]),e._v(" Main package")]),e._v(" "),i("ul",[i("li",[e._v("Communicate with client using LSP4J")]),e._v(" "),i("li",[e._v("Keep projects in sync with file system")]),e._v(" "),i("li",[e._v("Incrementally validate projects when files modify")]),e._v(" "),i("li",[e._v("Trigger project generation when no errors found")]),e._v(" "),i("li",[e._v("Resolve document services like hover and completions")])]),e._v(" "),i("h3",{attrs:{id:"main-java"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#main-java"}},[e._v("#")]),e._v(" Main.java")]),e._v(" "),i("ul",[i("li",[e._v("Detect debugging mode by reading console arguments")]),e._v(" "),i("li",[e._v("Listen for language client connection by stdio if not debugging")]),e._v(" "),i("li",[e._v("Listen for language client connection by socket 5007 if debugging")]),e._v(" "),i("li",[e._v("Listen to language client reconnection by socket 5007")])]),e._v(" "),i("h3",{attrs:{id:"server-java"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#server-java"}},[e._v("#")]),e._v(" Server.java")]),e._v(" "),i("p",[e._v("Connection lifetime events")]),e._v(" "),i("ul",[i("li",[e._v("Start listening for language client notifications and requests")]),e._v(" "),i("li",[e._v("Initilize request: Send server capabilities + create a project foreach folder")]),e._v(" "),i("li",[e._v("Initialized: Initialize all projects and publish created diagnostics")]),e._v(" "),i("li",[e._v("Shutdown and Exit: Exit program with status 0")])]),e._v(" "),i("p",[e._v("File and folder synchronization")]),e._v(" "),i("ul",[i("li",[e._v("Folder created: Create project and publish diagnostics")]),e._v(" "),i("li",[e._v("Folder removed: Remove project")]),e._v(" "),i("li",[e._v("Detect containing projects of file")]),e._v(" "),i("li",[e._v("File created: Read file + Signal containing projects about file modification and publish diagnostics")]),e._v(" "),i("li",[e._v("File removed: Signal containing projects about file removal and publish diagnostics")]),e._v(" "),i("li",[e._v("File modified: Signal containing projects about file modification and publish diagnostics")])]),e._v(" "),i("p",[e._v("Language services")]),e._v(" "),i("ul",[i("li",[e._v("Publish diagnostics of all projects in workspace")]),e._v(" "),i("li",[e._v("Show alert if a file is opened outside of all projects")]),e._v(" "),i("li",[e._v("Inspection services: Unwrap lsp4j parameters into file and position + ask containing projects + wrap results for lsp4j")])]),e._v(" "),i("p",[e._v("Known issues")]),e._v(" "),i("ul",[i("li",[e._v("! Deleting first project sends a shutdown request and the server exits !")])]),e._v(" "),i("h3",{attrs:{id:"project-java"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#project-java"}},[e._v("#")]),e._v(" Project.java")]),e._v(" "),i("ul",[i("li",[e._v("Initialize: Detect all Ⲙ files + validate changes + analyze result")]),e._v(" "),i("li",[e._v("Initialize: Detect configuration file and save parsed configuration")]),e._v(" "),i("li",[e._v("Delete file: Validate change + analyze result or clean configuration")]),e._v(" "),i("li",[e._v("Modify file: Validate change + analyze result or update configuration")]),e._v(" "),i("li",[e._v("Delegate language services to inspector")])]),e._v(" "),i("p",[e._v("Privately")]),e._v(" "),i("ul",[i("li",[e._v("Analyze result: generate project if no errors + return converted diagnostics")]),e._v(" "),i("li",[e._v("Convert m.validation.problems to lsp4j.diagnostics")])]),e._v(" "),i("h2",{attrs:{id:"validation"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#validation"}},[e._v("#")]),e._v(" Validation")]),e._v(" "),i("ul",[i("li",[e._v("! Get the nodes from expressionGraph or build the local data in expressionGraph !")])]),e._v(" "),i("h3",{attrs:{id:"global-validator"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#global-validator"}},[e._v("#")]),e._v(" Global validator")]),e._v(" "),i("ul",[i("li",[e._v("On validation: validate locally")]),e._v(" "),i("li",[e._v("Save validation results in memory")]),e._v(" "),i("li",[e._v("Invalidate obsolete memory")]),e._v(" "),i("li",[e._v("Validate project globally")]),e._v(" "),i("li",[e._v("Add local problems")]),e._v(" "),i("li",[e._v("Add undecidable and incompatible type problems")]),e._v(" "),i("li",[e._v("Add all functions to game")]),e._v(" "),i("li",[e._v("Add all well typed components to game")]),e._v(" "),i("li",[e._v("Detect repeated functions and report error in each file")]),e._v(" "),i("li",[e._v("return game data")]),e._v(" "),i("li",[e._v("! Garbage is created when clusters are merged !")])]),e._v(" "),i("h3",{attrs:{id:"local-validator-parse-2-pass-validation"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#local-validator-parse-2-pass-validation"}},[e._v("#")]),e._v(" Local validator Parse + 2 pass validation")]),e._v(" "),i("ul",[i("li",[i("p",[e._v("Parse text into model and syntax errors")])]),e._v(" "),i("li",[i("p",[e._v("Handle empty files")])]),e._v(" "),i("li",[i("p",[e._v("! Declare all functions and components beforehand !")])]),e._v(" "),i("li",[i("p",[e._v("Declare variables of assignments and bindingBlocks")])]),e._v(" "),i("li",[i("p",[e._v("Access values, components, functions and blocks.")])]),e._v(" "),i("li",[i("p",[e._v("! Assignments have special treatment as hardcoded functions !")])]),e._v(" "),i("li",[i("p",[e._v("Push and pop value stack")])]),e._v(" "),i("li",[i("p",[e._v("build resulting data in context -> expressionGraph")])]),e._v(" "),i("li",[i("p",[e._v("Add syntax errors from parsing and return")])])]),e._v(" "),i("h3",{attrs:{id:"context"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#context"}},[e._v("#")]),e._v(" Context")]),e._v(" "),i("ul",[i("li",[e._v("Create redefined, undefined and unused problems")]),e._v(" "),i("li",[e._v("Bind expressions together for same variable, same component, same function, same type variable")]),e._v(" "),i("li",[e._v("Type expressions for standard variable, standard component, standard function, standard block")])]),e._v(" "),i("h3",{attrs:{id:"expressiongraph"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#expressiongraph"}},[e._v("#")]),e._v(" ExpressionGraph")]),e._v(" "),i("ul",[i("li",[e._v("Maintain set of expression nodes. One per connected component.")]),e._v(" "),i("li",[e._v("Convert expressions to ExpressionNodes")]),e._v(" "),i("li",[e._v("Add bindings to each node of a binding")]),e._v(" "),i("li",[e._v("Add a typing to a node with a reason")]),e._v(" "),i("li",[e._v("! Add connected component when typed if it has no bindings !")]),e._v(" "),i("li",[e._v("! When binding two nodes, if both are root, remove one; if none are root add one as root !")])]),e._v(" "),i("h2",{attrs:{id:"model"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#model"}},[e._v("#")]),e._v(" Model")]),e._v(" "),i("h3",{attrs:{id:"parser"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#parser"}},[e._v("#")]),e._v(" Parser")]),e._v(" "),i("ul",[i("li",[e._v("Inject MParser generated by Xtext")]),e._v(" "),i("li",[e._v("Parse text and return results")])]),e._v(" "),i("h1",{attrs:{id:"flow"}},[i("a",{staticClass:"header-anchor",attrs:{href:"#flow"}},[e._v("#")]),e._v(" Flow")]),e._v(" "),i("p",[e._v("Main: connect to client via socket 5007\nServer: start listening\nServer: set capabilities\nServer: Initialize projects\nProject: Find .Ⲙ files in root folder\nProject: Trigger incremental build for each")]),e._v(" "),i("p",[e._v("GlobalValidator: Validate new file content locally\nLocalValidator: Parse file text\nParser: Use injected generated MParser and parse\nLocalValidator: Declare all functions and components globally\nLocalValidator: Go through the AST model")]),e._v(" "),i("p",[e._v("LocalValidator and Context and ExpressionGraph: Interplay\nLocalValidator: Control context's defined variables stack by pushing and poping in blocks\nLocalValidator: Declare new variables if foreach expression or assignment to variable\nLocalValidator: Access variables, components, functions and blocks of context")]),e._v(" "),i("p",[e._v("3 reasons for redefined error:\nContext: Redefined error if variable is in the standard library or is a user function or component.\nContext: Redefined error if component is in the standard library or is a user function.\nContext: Redefined error if function is in the standard library or is a previous user function.")]),e._v(" "),i("p",[e._v("5 reasons for undefined errors:\nContext: Undefined error if variable is not standard nor in scope\nContext: ! Undefined error if block is not standard !\nContext: Undefined error if function parameter count does not match with standard or user function\nContext: Undefined error if function does not exist")]),e._v(" "),i("p",[e._v("Context: ! Unused value error if variable was in stack not anymore and has not been accessed !")]),e._v(" "),i("p",[e._v("ExpressionGraph: Convert expression to expressionNode")])])}),[],!1,null,null,null);a.default=t.exports}}]);