(window.webpackJsonp=window.webpackJsonp||[]).push([[29],{395:function(e,t,a){"use strict";a.r(t);var n=a(42),s=Object(n.a)({},(function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[a("h1",{attrs:{id:"source-code"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#source-code"}},[e._v("#")]),e._v(" Source code")]),e._v(" "),a("p",[e._v("Under 100 files (54). Under 10k lines of code (7k). No comments (0). The code\nfor the language server relies on the Xtext framework. It consists of two\nprojects, m and m.ide. The project m holds all the information for running the\ncompiler in headless mode. This includes the grammar specifying the lexer and\nparser rules, the validator, formatter, linker and code generators. In fact, m\nconsists of 7 languages and translations, one for each kind of supported file\ntype. A language for JSON, XML, YAML, C#, and of course M and all its variants\nin different natural languages. Google's Guice dependency injection framework\nwires every component of the language. The IDE components like code completion,\nsyntax highlighting, labels, quick fixes, outline view are in the m.ide project\nindependently from the execution platform. It's useful to have a m.ui project to\ntest the functionality in a eclipse instance with a debugger attached to it.")]),e._v(" "),a("p",[e._v("Single sourcing: If certain code's structure logically depends on another code,\nit should get automatically generated. Example: Blockly block definitions depend\non the grammars rules. Block definition files are automatically generated based\non the grammars.")]),e._v(" "),a("p",[e._v("Parser: Terminals are single character and pairwise disjoint. Don't use hidden\nterminals. Don't use until token. Don't use syntactic predicates. Don't use\nbacktracking. DataTypes combine terminals in the least information needed.\nExpressions follow Xtext guidelines for associativity and precedence. Full\nsupport for c# expressions in the base grammar. M grammar can override a rule to\nskip certain operators from the language. Unary minus operand acts on floats to\nmake them negative (otherwise they are alway positive). Numbers in components\ncan be negative because they use a different datatype rule which can include a\nminus sign (NUMBER vs FLOAT).")]),e._v(" "),a("p",[e._v("m/src")]),e._v(" "),a("p",[e._v("Generate.java: This main java file generates all the xtext infrastructure from\nthe grammar files. To generate all the project files, first execute this main\nfile. It will generate a bunch of files under m/src, m/src-gen, m.ide/src and\nm.ide/src-gen. It also generates a m.ui project to faster test the language\nserver inside an eclipse application. The easiest way to use eclipse with this\nproject is to create a xtext project with the name m first with generic ide and\neclipse plugin options selected, then download the project files to possibly\noverride the existing ones. Execute generate.java as a java application and your\neclipse project will be ready.")]),e._v(" "),a("p",[e._v("xRuntimeModule: Dependency injection wiring via Google guice modules. If the\ninjection is already wired in AbstractLanguageRuntimeModule, the injection needs\nto be overridden, if it's not it must use def. Xtext has syntax sugar def\nbindIService(){ ClassName } OutputConfiguration is an example.")]),e._v(" "),a("p",[e._v("x.xtext: Grammar definitions. All independent. Limit terminals. More data rules\nand lastly parser rules. no backtracking, no semantic helpers.")]),e._v(" "),a("p",[e._v("converter/MyTerminalConverter: Used when terminal rules return something other\nthan a EString. Defines how the conversion works in both directions.")]),e._v(" "),a("p",[e._v("errorHandling/XSyntaxErrorMessageProvider: Used when the parser throws an error\nto improve the error message considering the context.")]),e._v(" "),a("p",[e._v("xFormatter: dispatched functions to add white spaces when serializing a model.\nAAlways try to use prepend to avoid conflicts, use grammar access to access\nnon-keywords. Use literal keywords like ',' instead of grammarAccess ones for\nbetter maintainability.")]),e._v(" "),a("p",[e._v("LanguageGenerator:")]),e._v(" "),a("p",[e._v("Main.java: For headless execution. Depends on java JRE and the JAR built. Give\nit a file and it will parse it and generate it for you.")]),e._v(" "),a("p",[e._v("transformation: Each file is responsible of generating all files of a specific\nkind. First the type inference runs and now with the model and the types\ninferred the generator can generate all the artifacts. BuiltInLibrary translates\nnames of built-in components for generating the same text in other languages.\nSome are legacy code. The ones that uses TextGenerator matter.")]),e._v(" "),a("p",[e._v("No hybrid or pure distinction, use Hybrid.Rendering, Unity.Physics etc when\nthey are ready and do the rest in Hybrid, as any professional would do\nnowadays.")]),e._v(" "),a("p",[e._v("UnityGenerator UnityParser BlockGenerator BlockParser")]),e._v(" "),a("p",[e._v("xValidator: Rules to make extra assumptions true in the model. For example\nunique names.")]),e._v(" "),a("p",[e._v("Workflow.mwe2: Important. Generates all the artifacts. It defines the project\nstructure and the fragments that each language cares about.")]),e._v(" "),a("p",[e._v("ide:")]),e._v(" "),a("p",[e._v("MyContentAssist extends IdeContentProposalProvider: For platform agnostic\ndefinition of content assist proposals.")]),e._v(" "),a("p",[e._v("MyLabelProvider extends SimpleNameLabelProvider: For platform agnostic\ndefinition of label names.")]),e._v(" "),a("p",[e._v("build.gradle: Gradle script to build the language server. It needs the reference\nto the mwe workflow file and it passes it the rootPath variable. It runs the\nworkflow and generates a language server fat jar file in build folder.")]),e._v(" "),a("h2",{attrs:{id:"generating-xtext-artifacts"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#generating-xtext-artifacts"}},[e._v("#")]),e._v(" Generating Xtext artifacts")]),e._v(" "),a("p",[e._v("The first step to build the project is to generate all the xtext artifacts. The\nRunFragments.java file creates a xtext generator workflow and adds a standard\nlanguage to it for each grammar file located in the m/src/m folder.")]),e._v(" "),a("p",[e._v("The standard languages include built in fragments to generate the grammar\naccess, parser, emf model, generators, serializer etc. To all this we add a\ncustom fragment called ContextualParserMessagesFragment which wires the\nContextualParserMessages as the responsible for producing error messages when\nthe parser finds one.")]),e._v(" "),a("p",[e._v("When we run the workflow, the generated dependency injection injectors\nAbstractXRuntimeModule.java wire all the fragments together. In particular, they\nwire the ISyntaxErrorMessageProvider interface to ContextualParserMessages.")]),e._v(" "),a("h2",{attrs:{id:"using-gradle"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#using-gradle"}},[e._v("#")]),e._v(" Using gradle")]),e._v(" "),a("p",[e._v("The build.gradle file defines two gradle tasks:")]),e._v(" "),a("ul",[a("li",[e._v("runFragments: Runs the m.main.RunFragments.java file with the correct\ndependencies to generate the xtext artifacts.")]),e._v(" "),a("li",[e._v("generateLanguageServer: Packages all the generated .class files and the\ndependencies in a fat jar file.")])]),e._v(" "),a("h2",{attrs:{id:"folder-structure"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#folder-structure"}},[e._v("#")]),e._v(" Folder structure")]),e._v(" "),a("p",[e._v("The project has three root folders:")]),e._v(" "),a("ul",[a("li",[a("p",[e._v(".github: Contains all the automation tasks and their configuration files.")])]),e._v(" "),a("li",[a("p",[e._v("Code: Contains all the java files and xtext grammars used to describe the\nlanguage server and client.")])]),e._v(" "),a("li",[a("p",[e._v("Documentation: Contains all the markdown files that explain the reasoning\nbehind the code.")])])]),e._v(" "),a("h2",{attrs:{id:"validation"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#validation"}},[e._v("#")]),e._v(" Validation")]),e._v(" "),a("p",[e._v("There are three main types of checks:")]),e._v(" "),a("ul",[a("li",[a("p",[e._v("Uniqueness checks: Makes sure that there is a single element in the container\nwith that specific name. Examples: unique component, unique entity, unique\nsystem. Included checks: Unique component name in entity, Unique entity name\nin module, Unique system name in module, Unique procedure name in module,\nUnique tag name in loop tags, Unique argument name in procedure.")])]),e._v(" "),a("li",[a("p",[e._v("Existence checks: Makes sure that the specified cross reference exists.\nExamples: function call name exists, base of an entity exists. Included\nchecks: The base of an entity exists in the module, The entity accessed in a\nvariable exists in a previous assignment or loop statement.")])]),e._v(" "),a("li",[a("p",[e._v("Acyclic checks: Makes sure that the dependencies are not cyclic. That means,\nif A depends on B, B can not depend on A etc. Examples: entity A has base B,\nentity B can not have base A. Similarly for 3-cycles and n-cycles. A depends\non B, B depends on C, C can not depend on A. Included checks: The base of an\nentity is acyclic.")])]),e._v(" "),a("li",[a("p",[e._v("Typing checks: Makes sure that the variables have conformable types. Examples:\nEngine components have the correct type (Position must be a Float3 etc),\nCustom vector components have up to four entries, the left and right\nexpressions in an addition have the same type.")])])]),e._v(" "),a("h2",{attrs:{id:"parsing-error-messages"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#parsing-error-messages"}},[e._v("#")]),e._v(" Parsing error messages")]),e._v(" "),a("p",[e._v("The ContextualParserMessagesFragment deduces parser error messages from the\ngrammar definition. When there is an error, it walks the grammar structure to\nfind the feature that is missing, considering all the possible alternatives and\ncreates a one line message for each alternative.")]),e._v(" "),a("p",[e._v("For example, under the input")]),e._v(" "),a("div",{staticClass:"language-{.set .parser1 .main.m} extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("entity : ?\n")])])]),a("p",[e._v("It gives the error message")]),e._v(" "),a("div",{staticClass:"language-{.check .parser1 .error} extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("Parser error at ?\nWrite WORD to set the base of the Entity\n")])])]),a("h2",{attrs:{id:"unity-components"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#unity-components"}},[e._v("#")]),e._v(" Unity components")]),e._v(" "),a("p",[e._v("Four kind of components represent the data:")]),e._v(" "),a("ul",[a("li",[a("p",[e._v("Value components: They are IComponentData with automatically generated\nauthoring components. Example: mass.")])]),e._v(" "),a("li",[a("p",[e._v("List components: They are IBufferElementData with custom authoring component\nthat converts game object references to entities.")])]),e._v(" "),a("li",[a("p",[e._v("Reference components: They are class IComponentData with custom authoring\ncomponent that adds a componentObject to the entity.")])]),e._v(" "),a("li",[a("p",[e._v("Engine components: They are UnityEngine classes that get added to entities by\nthe EngineComponentConversion system.")])])]),e._v(" "),a("h2",{attrs:{id:"workflow"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#workflow"}},[e._v("#")]),e._v(" Workflow")]),e._v(" "),a("p",[e._v("M operates in four stages: Parsing, validating, generating and formatting.")]),e._v(" "),a("p",[e._v("The parser processes the input given by the user and checks for syntactical\nerrors. The result is an abstract syntax tree containing all the essential\ninformation of the file, all the data without white spaces.")]),e._v(" "),a("p",[e._v("The validator checks the information of the abstract tree for semantic validity.\nThe result is an Eclipse Modeling Framework model of the abstract representation\nof the interactive simulation.")]),e._v(" "),a("p",[e._v("The generating represents the model in all the requested platforms, including\ntextual representations in different languages, visual representations in\ndifferent languages and project representations in different game engines. The\nresult is a folder with all the required files for representing that simulation\nin each platform.")]),e._v(" "),a("p",[e._v("The formatter mixes white space with the generated text so that humans can\nunderstand it.")]),e._v(" "),a("h2",{attrs:{id:"for-each-view"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#for-each-view"}},[e._v("#")]),e._v(" For each view")]),e._v(" "),a("p",[e._v("M has three main views: Textual, Visual and Project. These might appear\ntranslated to different natural languages. For instance, English, Spanish and\nBasque. The total number of combinations is the product of views by\ntranslations.")]),e._v(" "),a("h2",{attrs:{id:"language-syntax"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#language-syntax"}},[e._v("#")]),e._v(" Language syntax")]),e._v(" "),a("p",[e._v('Allow not recommended constructions like single line comments, multi line\ncomments, number and string literals in systems... but warn them as deprecated.\nThis way, coders are not confused but directed in the "right" direction, and\nafter adoption, the language will enforce stronger rules.')]),e._v(" "),a("p",[e._v("M has a C-style syntax, like C++, Java or C#. It build on the same idea of\nexpression and control flows like iteration and branching blocks but differs on\nthe higher level abstractions. M does not contain any object oriented concepts\nlike classes or visibility modifiers. Neither has the ability to specify new\ndata structures, enumerations or similar in C. Instead it provides two simple\nand powerful abstractions: Entities and systems.")]),e._v(" "),a("p",[e._v("Two root level concepts in M: Entities and systems. In fact, every program\nwritten in M consists solely of entities and systems. The components of entities\nhold all the data of the program while systems hold the interactions.")]),e._v(" "),a("p",[e._v("Entities have a name and a list of components. Each component holds a single\nvalue which might be a vector or an asset. Vectors store numeric values such as\nmass, position or rotation. They can have up to four dimensions. Assets are a\nsequence of words of arbitrary length. The type inference system will decide\nwhich kind of asset - mesh, image, audio clip, font... - based on the usage of\nthe component.")]),e._v(" "),a("p",[e._v("Systems consist of a unique name and a list of sequential commands. These\ncommand can be blocks such as loops or branches that might in turn contain more\ncommands themselves.")]),e._v(" "),a("p",[e._v("Four kinds of commands. Loops iterate over entities with a certain constraints,\nbranch commands take different paths depending of a condition. Repeat commands\nrepeat their body a set number of times. Assignments assign a value to a\nvariable or a component. Call commands call a function that is either built in\nor defined by the user.")]),e._v(" "),a("p",[e._v("The expressions of the language consist of boolean and arithmetic expressions.\nArithmetic expressions can use operands like +,-,*,/,% and the bitwise\n&,|,<<,>>,^,~ to alter numerical values. The types of the operands must be\ncompatible with the operation. The bitwise operators work over single dimension\nvectors (scalars). The multiplicative operations *,/,% can take any vector as\nfirst argument but a scalar as a second argument. The additive operations + and\n- accept any two vectors as long as their dimensions are equal.")]),e._v(" "),a("p",[e._v("And that's it. You can build more complex abstractions by combining these basic\nconcepts. You can watch some of these in action in the "),a("RouterLink",{attrs:{to:"/0. Beginner guide/2. Guided examples/"}},[e._v("examples")]),e._v(" documentation.")],1),e._v(" "),a("h2",{attrs:{id:"keywords"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#keywords"}},[e._v("#")]),e._v(" Keywords")]),e._v(" "),a("p",[e._v("The grammar has some words reserved that can't be identifiers. The list of all\nidentifiers is:")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("foreach, with, if, else\n")])])]),a("p",[e._v("You can use any other word for your identifiers as long as it starts with an\nunderscore or letter followed by any amount of letters and numbers.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("Identifier: '_'? ('a'..'z'|'A'..'Z'|'0'..'9')+\n")])])]),a("p",[e._v("White space. The parser of M ignores all the white space. You can structure your\ncode as you want using white space. We recommend using the built in formatter\nfor consistency across team members and easier version controlling.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("Whitespace: '\\n'|'\\r'|' '|'\\t'\n")])])]),a("p",[e._v("Sometimes, the next token must be a number.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("Number: '-'? DIGIT* ('.' DIGIT+ ('e' '-'? DIGIT+)?)?\nDIGIT: '0'..'9'\n")])])]),a("h2",{attrs:{id:"data-oriented-design"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#data-oriented-design"}},[e._v("#")]),e._v(" Data oriented design")]),e._v(" "),a("p",[e._v("Everything is data. Introducing the data model:")]),e._v(" "),a("p",[e._v("The data matrix and the variable heap hold all the data of a frame of a game.")]),e._v(" "),a("p",[e._v("Our job as programmers is to define the laws that transform this data every\nframe.")]),e._v(" "),a("p",[e._v("The possible ways to alter the data are:")]),e._v(" "),a("ul",[a("li",[a("p",[e._v("Create a new row in the matrix (Entity creation)")])]),e._v(" "),a("li",[a("p",[e._v("Delete a row in the matrix (Entity deletion)")])]),e._v(" "),a("li",[a("p",[e._v("Activate a cell in the matrix (Component addition)")])]),e._v(" "),a("li",[a("p",[e._v("Deactivate a cell in the matrix (Component removal)")])])]),e._v(" "),a("p",[e._v("We can use the variable heap to store temporary values. The system automatically\ndeletes this values after every frame.")]),e._v(" "),a("ul",[a("li",[e._v("Create an entry in the heap (Variable declaration)")])]),e._v(" "),a("p",[e._v("Now we need a way to gather values to write to the matrix cells and the heap.")]),e._v(" "),a("ul",[a("li",[a("p",[e._v("Read a value stored in a cell (Component access)")])]),e._v(" "),a("li",[a("p",[e._v("Read a value stored in the heap (Variable access)")])])]),e._v(" "),a("p",[e._v("These are the atoms of data. We can transform the data by using transformations:")]),e._v(" "),a("ul",[a("li",[e._v("Operators: Arithmetic, Comparison, Logic, Set operators")]),e._v(" "),a("li",[e._v("Grouping: Join, Set creation, Brackets...")]),e._v(" "),a("li",[e._v("Functions: Math functions.")])]),e._v(" "),a("p",[e._v("Control Flow.")]),e._v(" "),a("p",[e._v("We may repeat this process per matching row (Forall), while a condition is true\n(Iteration) or if a condition is true (Selection).")]),e._v(" "),a("p",[e._v("And that is the whole grammar of M.")])])}),[],!1,null,null,null);t.default=s.exports}}]);