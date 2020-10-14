(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{390:function(e,a,n){"use strict";n.r(a);var t=n(42),i=Object(t.a)({},(function(){var e=this,a=e.$createElement,n=e._self._c||a;return n("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[n("h1",{attrs:{id:"responsability-view"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#responsability-view"}},[e._v("#")]),e._v(" Responsability view>")]),e._v(" "),n("p",[e._v("Public methods orchestrate the work. Explain the flow and delegate.\nPrivate methods do the actual work manipulating the data.")]),e._v(" "),n("h2",{attrs:{id:"vscode-extension"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#vscode-extension"}},[e._v("#")]),e._v(" VSCode extension")]),e._v(" "),n("ul",[n("li",[e._v("Define marketplace page's information")]),e._v(" "),n("li",[e._v("Define bracket features and snippets declaratively")]),e._v(" "),n("li",[e._v("Detect missing dependencies (Java 8+) and offer solutions")]),e._v(" "),n("li",[e._v("Detect extension activation events and start language server")]),e._v(" "),n("li",[e._v("Associate .Ⲙ and Ⲙ.json files with Ⲙ and watch for modifications")])]),e._v(" "),n("h3",{attrs:{id:"package-json"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#package-json"}},[e._v("#")]),e._v(" Package.json")]),e._v(" "),n("ul",[n("li",[e._v("Define name version publisher and license")]),e._v(" "),n("li",[e._v("Define descriptions and keywords")]),e._v(" "),n("li",[e._v("Define icon, preview label and no q&a section")]),e._v(" "),n("li",[e._v("Link to webpage, repository and issues")]),e._v(" "),n("li",[e._v("Contribute language Ⲙ for .Ⲙ files")]),e._v(" "),n("li",[e._v("Activate extension when Ⲙ files opened or workspace contains a Ⲙ or Ⲙ.json file")]),e._v(" "),n("li",[e._v("Activation starts in main.js")]),e._v(" "),n("li",[e._v("Define dependency on vscode-language client and vscode engine (required by vsce)")])]),e._v(" "),n("h3",{attrs:{id:"brackets-json"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#brackets-json"}},[e._v("#")]),e._v(" Brackets.json")]),e._v(" "),n("ul",[n("li",[e._v("Indent and dedent automatically with {}")]),e._v(" "),n("li",[e._v("Create folding markers with {}")]),e._v(" "),n("li",[e._v("Highlighting bracket pairs for {} [] and ()")]),e._v(" "),n("li",[e._v("Autoclose brackets {} [] ()")]),e._v(" "),n("li",[e._v("Surround selection with brackets {} [] ()")])]),e._v(" "),n("h3",{attrs:{id:"snippets-json"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#snippets-json"}},[e._v("#")]),e._v(" Snippets.json")]),e._v(" "),n("ul",[n("li",[e._v("Snippets for foreach, if, set and system")]),e._v(" "),n("li",[e._v("Snippets respect current indentation even if they span multiple lines")]),e._v(" "),n("li",[e._v("Snippets have default text and multiple cursor markers that the developer can jump with tab")]),e._v(" "),n("li",[e._v("Snippets description is snippet")])]),e._v(" "),n("p",[e._v("! Only in english !")]),e._v(" "),n("h3",{attrs:{id:"main-js"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#main-js"}},[e._v("#")]),e._v(" Main.js")]),e._v(" "),n("ul",[n("li",[e._v("Listen to activation and deactivation events")]),e._v(" "),n("li",[e._v("Detect java 8+ installed in path by running java -version")]),e._v(" "),n("li",[e._v("Show button to open webpage to download openjdk if necessary by using systems url opener")]),e._v(" "),n("li",[e._v("Detect debugging mode by analyzing process' environment (set in .vscode/launch.json)")]),e._v(" "),n("li",[e._v("If debugging set connection by socket 5007")]),e._v(" "),n("li",[e._v("If not debugging set connection by starting java process and using stdio")]),e._v(" "),n("li",[e._v("Start language client and server")]),e._v(" "),n("li",[e._v("Watch .Ⲙ and Ⲙ.json files and send file modified notifications to server")]),e._v(" "),n("li",[e._v("Stop client when extension gets deactivated")])]),e._v(" "),n("h2",{attrs:{id:"main-package"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#main-package"}},[e._v("#")]),e._v(" Main package")]),e._v(" "),n("ul",[n("li",[e._v("Communicate with client using LSP4J")]),e._v(" "),n("li",[e._v("Keep projects in sync with file system")]),e._v(" "),n("li",[e._v("Incrementally validate projects when files modify")]),e._v(" "),n("li",[e._v("Trigger project generation when no errors found")]),e._v(" "),n("li",[e._v("Resolve document services like hover and completions")])]),e._v(" "),n("h3",{attrs:{id:"main-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#main-java"}},[e._v("#")]),e._v(" Main.java")]),e._v(" "),n("ul",[n("li",[e._v("Detect debugging mode by reading console arguments")]),e._v(" "),n("li",[e._v("Listen for language client connection by stdio if not debugging")]),e._v(" "),n("li",[e._v("Listen for language client connection by socket 5007 if debugging")]),e._v(" "),n("li",[e._v("Listen to language client reconnection by socket 5007")])]),e._v(" "),n("h3",{attrs:{id:"server-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#server-java"}},[e._v("#")]),e._v(" Server.java")]),e._v(" "),n("p",[e._v("Connection lifetime events")]),e._v(" "),n("ul",[n("li",[e._v("Start listening for language client notifications and requests")]),e._v(" "),n("li",[e._v("Initilize request: Send server capabilities + create a project foreach folder")]),e._v(" "),n("li",[e._v("Initialized: Initialize all projects and publish created diagnostics")]),e._v(" "),n("li",[e._v("Shutdown and Exit: Exit program with status 0")])]),e._v(" "),n("p",[e._v("File and folder synchronization")]),e._v(" "),n("ul",[n("li",[e._v("Folder created: Create project and publish diagnostics")]),e._v(" "),n("li",[e._v("Folder removed: Remove project")]),e._v(" "),n("li",[e._v("Detect containing projects of file")]),e._v(" "),n("li",[e._v("File created: Read file + Signal containing projects about file modification and publish diagnostics")]),e._v(" "),n("li",[e._v("File removed: Signal containing projects about file removal and publish diagnostics")]),e._v(" "),n("li",[e._v("File modified: Signal containing projects about file modification and publish diagnostics")])]),e._v(" "),n("p",[e._v("Language services")]),e._v(" "),n("ul",[n("li",[e._v("Publish diagnostics of all projects in workspace")]),e._v(" "),n("li",[e._v("Show alert if a file is opened outside of all projects")]),e._v(" "),n("li",[e._v("Inspection services: Unwrap lsp4j parameters into file and position + ask containing projects + wrap results for lsp4j")])]),e._v(" "),n("p",[e._v("Known issues")]),e._v(" "),n("ul",[n("li",[e._v("! Deleting first project sends a shutdown request and the server exits !")])]),e._v(" "),n("h3",{attrs:{id:"project-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#project-java"}},[e._v("#")]),e._v(" Project.java")]),e._v(" "),n("ul",[n("li",[e._v("Initialize: Detect all Ⲙ files + validate changes + analyze result")]),e._v(" "),n("li",[e._v("Initialize: Detect configuration file and save parsed configuration")]),e._v(" "),n("li",[e._v("Delete file: Validate change + analyze result or clean configuration")]),e._v(" "),n("li",[e._v("Modify file: Validate change + analyze result or update configuration")]),e._v(" "),n("li",[e._v("Delegate language services to inspector")])]),e._v(" "),n("p",[e._v("Privately")]),e._v(" "),n("ul",[n("li",[e._v("Analyze result: generate project if no errors")]),e._v(" "),n("li",[e._v("Convert m.validation.problems to lsp4j.diagnostics")]),e._v(" "),n("li",[e._v("Convert nodes to text ranges")]),e._v(" "),n("li",[e._v("Convert offsets to line + character text positions")])]),e._v(" "),n("p",[e._v("Known issues")]),e._v(" "),n("ul",[n("li",[e._v("! Converting nodes to text ranges can be done better by using NodeModel better !")])]),e._v(" "),n("h2",{attrs:{id:"validation-package"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#validation-package"}},[e._v("#")]),e._v(" Validation package")]),e._v(" "),n("ul",[n("li",[e._v("! Get the nodes from expressionGraph or build the local data in expressionGraph !")])]),e._v(" "),n("h3",{attrs:{id:"validator-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#validator-java"}},[e._v("#")]),e._v(" Validator.java")]),e._v(" "),n("ul",[n("li",[e._v("Modify file: Validate syntax, scope and types")]),e._v(" "),n("li",[e._v("Delete file: Signal deletion to scope and types")]),e._v(" "),n("li",[e._v("Merge all problems in result.problems")]),e._v(" "),n("li",[e._v("Save typed functions and components in result.game")]),e._v(" "),n("li",[e._v("Detect if result has problems with error severity to signal validity of model")])]),e._v(" "),n("h3",{attrs:{id:"scopevalidator-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#scopevalidator-java"}},[e._v("#")]),e._v(" ScopeValidator.java")]),e._v(" "),n("ul",[n("li",[e._v("Clear obsolete memory")]),e._v(" "),n("li",[e._v("Walk the parsed model")])]),e._v(" "),n("p",[e._v("Variables")]),e._v(" "),n("ul",[n("li",[e._v("Declare variables: Query entity and assigning value for the first time")]),e._v(" "),n("li",[e._v("Standard variable: Redefinition error")]),e._v(" "),n("li",[e._v("Declared variable: Bind to declaration")]),e._v(" "),n("li",[e._v("Undeclared variable: Declare it")]),e._v(" "),n("li",[e._v("Access variable: In value expressions and cell entities")]),e._v(" "),n("li",[e._v("Standard variable : Type it")]),e._v(" "),n("li",[e._v("Declared variable: Bind to declaration")]),e._v(" "),n("li",[e._v("Undeclared variable: Undefined error")])]),e._v(" "),n("p",[e._v("Components")]),e._v(" "),n("ul",[n("li",[e._v("Declare component: Cells for the first time")]),e._v(" "),n("li",[e._v("Standard component: Do nothing")]),e._v(" "),n("li",[e._v("Declared component: Do nothing")]),e._v(" "),n("li",[e._v("Undeclared component: Declare it")]),e._v(" "),n("li",[e._v("Access component: Cells")]),e._v(" "),n("li",[e._v("Standard component: Type it")]),e._v(" "),n("li",[e._v("Declared component: Bind to declaration")]),e._v(" "),n("li",[e._v("! Undeclared component: Never happens !")])]),e._v(" "),n("p",[e._v("Functions")]),e._v(" "),n("ul",[n("li",[e._v("! Declare functions: Never happens !")]),e._v(" "),n("li",[e._v("Access function: In assignment, unary, binary and applications")]),e._v(" "),n("li",[e._v("Standard function with same number of arguments: Type it, bind same type variables")]),e._v(" "),n("li",[e._v("Non standard function: Undefined error in name")])]),e._v(" "),n("p",[e._v("Blocks")]),e._v(" "),n("ul",[n("li",[e._v("Standard block: Type it")]),e._v(" "),n("li",[e._v("Non standard block: Undefined error")]),e._v(" "),n("li",[e._v("Standard binding block: Type it")]),e._v(" "),n("li",[e._v("Non standard block: Undefined error")])]),e._v(" "),n("h3",{attrs:{id:"typechecker-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#typechecker-java"}},[e._v("#")]),e._v(" TypeChecker.java")]),e._v(" "),n("ul",[n("li",[e._v("Invalidate obsolete memory")]),e._v(" "),n("li",[e._v("Add undecidable and incompatible type problems")]),e._v(" "),n("li",[e._v("Add all well typed components to game")]),e._v(" "),n("li",[e._v("! Garbage is created when clusters are merged !")])]),e._v(" "),n("h3",{attrs:{id:"functionchecker-java"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#functionchecker-java"}},[e._v("#")]),e._v(" FunctionChecker.java")]),e._v(" "),n("ul",[n("li",[e._v("Detect repeated functions and report error in each file")]),e._v(" "),n("li",[e._v("Add all functions to game")])]),e._v(" "),n("h3",{attrs:{id:"local-validator-parse-2-pass-validation"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#local-validator-parse-2-pass-validation"}},[e._v("#")]),e._v(" Local validator Parse + 2 pass validation")]),e._v(" "),n("ul",[n("li",[n("p",[e._v("Parse text into model and syntax errors")])]),e._v(" "),n("li",[n("p",[e._v("Handle empty files")])]),e._v(" "),n("li",[n("p",[e._v("! Declare all functions and components beforehand !")])]),e._v(" "),n("li",[n("p",[e._v("Declare variables of assignments and bindingBlocks")])]),e._v(" "),n("li",[n("p",[e._v("Access values, components, functions and blocks.")])]),e._v(" "),n("li",[n("p",[e._v("! Assignments have special treatment as hardcoded functions !")])]),e._v(" "),n("li",[n("p",[e._v("Push and pop value stack")])]),e._v(" "),n("li",[n("p",[e._v("build resulting data in context -> expressionGraph")])]),e._v(" "),n("li",[n("p",[e._v("Add syntax errors from parsing and return")])])]),e._v(" "),n("h3",{attrs:{id:"context"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#context"}},[e._v("#")]),e._v(" Context")]),e._v(" "),n("ul",[n("li",[e._v("Create redefined, undefined and unused problems")]),e._v(" "),n("li",[e._v("Bind expressions together for same variable, same component, same function, same type variable")]),e._v(" "),n("li",[e._v("Type expressions for standard variable, standard component, standard function, standard block")])]),e._v(" "),n("h3",{attrs:{id:"expressiongraph"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#expressiongraph"}},[e._v("#")]),e._v(" ExpressionGraph")]),e._v(" "),n("ul",[n("li",[e._v("Maintain set of expression nodes. One per connected component.")]),e._v(" "),n("li",[e._v("Convert expressions to ExpressionNodes")]),e._v(" "),n("li",[e._v("Add bindings to each node of a binding")]),e._v(" "),n("li",[e._v("Add a typing to a node with a reason")]),e._v(" "),n("li",[e._v("! Add connected component when typed if it has no bindings !")]),e._v(" "),n("li",[e._v("! When binding two nodes, if both are root, remove one; if none are root add one as root !")])]),e._v(" "),n("h2",{attrs:{id:"model"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#model"}},[e._v("#")]),e._v(" Model")]),e._v(" "),n("h3",{attrs:{id:"parser"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#parser"}},[e._v("#")]),e._v(" Parser")]),e._v(" "),n("ul",[n("li",[e._v("Inject MParser generated by Xtext")]),e._v(" "),n("li",[e._v("Parse text and return results")])]),e._v(" "),n("h1",{attrs:{id:"flow"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#flow"}},[e._v("#")]),e._v(" Flow")]),e._v(" "),n("p",[e._v("Main: connect to client via socket 5007\nServer: start listening\nServer: set capabilities\nServer: Initialize projects\nProject: Find .Ⲙ files in root folder\nProject: Trigger incremental build for each")]),e._v(" "),n("p",[e._v("GlobalValidator: Validate new file content locally\nLocalValidator: Parse file text\nParser: Use injected generated MParser and parse\nLocalValidator: Declare all functions and components globally\nLocalValidator: Go through the AST model")]),e._v(" "),n("p",[e._v("LocalValidator and Context and ExpressionGraph: Interplay\nLocalValidator: Control context's defined variables stack by pushing and poping in blocks\nLocalValidator: Declare new variables if foreach expression or assignment to variable\nLocalValidator: Access variables, components, functions and blocks of context")]),e._v(" "),n("p",[e._v("3 reasons for redefined error:\nContext: Redefined error if variable is in the standard library or is a user function or component.\nContext: Redefined error if component is in the standard library or is a user function.\nContext: Redefined error if function is in the standard library or is a previous user function.")]),e._v(" "),n("p",[e._v("5 reasons for undefined errors:\nContext: Undefined error if variable is not standard nor in scope\nContext: ! Undefined error if block is not standard !\nContext: Undefined error if function parameter count does not match with standard or user function\nContext: Undefined error if function does not exist")]),e._v(" "),n("p",[e._v("Context: ! Unused value error if variable was in stack not anymore and has not been accessed !")]),e._v(" "),n("p",[e._v("ExpressionGraph: Convert expression to expressionNode")])])}),[],!1,null,null,null);a.default=i.exports}}]);