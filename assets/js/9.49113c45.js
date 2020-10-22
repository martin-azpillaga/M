(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{381:function(e,t,n){"use strict";n.r(t);var a=n(42),i=Object(a.a)({},(function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[n("h1",{attrs:{id:"design-decisions"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#design-decisions"}},[e._v("#")]),e._v(" Design decisions")]),e._v(" "),n("h2",{attrs:{id:"motivation-why-does-m-exist"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#motivation-why-does-m-exist"}},[e._v("#")]),e._v(" Motivation. Why does M exist")]),e._v(" "),n("p",[e._v("Problem I am trying to solve: I know how to say it, I have said it, I know\nsomeone else can say it.")]),e._v(" "),n("p",[e._v("Why my problem is important to solve: Who does it benefit? How much is it worth\nsolving?")]),e._v(" "),n("p",[e._v("Plan B: What other solution do I have for my problem? Is plan B implemented?")]),e._v(" "),n("p",[e._v("Steps: I can articulate the steps to solve my problem I can point risks and\nunknowns in those steps Examples: I have used the project to solve actual\nproblems Completion: I know what means to my project being complete Statistics:\nI know how to falsify my hypothesis. Test with users my project and disprove my\nassumptions.")]),e._v(" "),n("p",[e._v("Efficiency: I know how long it takes to run the project. I know the minimal\nspecs for my project to run I know the dependencies of my project")]),e._v(" "),n("p",[e._v("Profile the generation I know how to optimize the generation")]),e._v(" "),n("p",[e._v("Use case: I know the most common use cases of the project I know how programs\nwritten in my project look lke in real life I know the acceptable range of\nprograms that programmers can write in my project I know what happens when\nsomeone goes out of that range Watch users use the project What is the part that\nthey require most time for? I know to debug when something fails, even without\nsource code I can apply data oriented: I know what data I operate on I know how\nI act with cache This project gives back to the community.")]),e._v(" "),n("h2",{attrs:{id:"properties"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#properties"}},[e._v("#")]),e._v(" Properties")]),e._v(" "),n("p",[e._v("M is a DSL Compiled External C-like concrete syntax textual Scratch like visual\nsyntax Abstract syntax is a EMF model Strongly typed with implicit types Type\ninferred and validated No null reference, no type conversion error, no infinite\nloop Execution semantics: Game engine projects with assets Useful for non\nprogrammers Turing complete Efficient compilation Efficient generated code,\nnative multi threaded Clean: Native support for DOD Full IDE support:")]),e._v(" "),n("ul",[n("li",[e._v("Code completion")]),e._v(" "),n("li",[e._v("Code generation")]),e._v(" "),n("li",[e._v("Syntax Highlight")]),e._v(" "),n("li",[e._v("Error markers")]),e._v(" "),n("li",[e._v("Quick fixes")]),e._v(" "),n("li",[e._v("Formatting")]),e._v(" "),n("li",[e._v("Refactoring")]),e._v(" "),n("li",[e._v("Labels")]),e._v(" "),n("li",[e._v("Outline view")]),e._v(" "),n("li",[e._v("Find uses")]),e._v(" "),n("li",[e._v("Find definition")]),e._v(" "),n("li",[e._v("Debugging")])]),e._v(" "),n("p",[e._v("Small syntax Concise User defined abstractions: Files as libraries, functions\nfor reusability, prefabs possibly nested Fast and volatile development of\nlanguage No collections, all entities Big language scope: Most games M improves:")]),e._v(" "),n("ul",[n("li",[e._v("Productivity: Less code to write and read")]),e._v(" "),n("li",[e._v("Quality: No runtime errors, halts, multi threaded")]),e._v(" "),n("li",[e._v("Communication and thinking: Familiar domain concepts, personalized views")]),e._v(" "),n("li",[e._v("Platform support: Any platform with JVM any platform with Unity support")])]),e._v(" "),n("h2",{attrs:{id:"lexer"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#lexer"}},[e._v("#")]),e._v(" Lexer")]),e._v(" "),n("p",[e._v("The lexer is derived from the Unicode 13 standard.\nIt accepts all character code points in the\nBase Multilangual Plane: U+0000 to U+FFFF.")]),e._v(" "),n("p",[e._v("It classifies the characters in character classes\ngiven by the unicode consortium and then merges together\nin the major character groups:")]),e._v(" "),n("ul",[n("li",[e._v("Letter")]),e._v(" "),n("li",[e._v("Number")]),e._v(" "),n("li",[e._v("Punctuation")]),e._v(" "),n("li",[e._v("Symbol")]),e._v(" "),n("li",[e._v("Blank")]),e._v(" "),n("li",[e._v("Control")])]),e._v(" "),n("p",[e._v("For the case of M, three terminals are interesting:")]),e._v(" "),n("ul",[n("li",[e._v("Identifiers are a nonempty string of letters")]),e._v(" "),n("li",[e._v("Operators are a nonempty string of symbols")]),e._v(" "),n("li",[e._v("Blanks are a nonempty string of blanks")]),e._v(" "),n("li",[e._v("All the rest are unrecognized characters")])]),e._v(" "),n("h2",{attrs:{id:"syntax"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#syntax"}},[e._v("#")]),e._v(" Syntax")]),e._v(" "),n("p",[e._v("To distinguish parser rules from each other, some keywords\nare necessary. In M, the keywords are taken from the punctuations\nclass.\nThis is because it is not involved in the interesting terminals and\nkeeps the syntax natural language agnostic.")]),e._v(" "),n("p",[e._v("The syntax of M is inspired in the family of C-like\nprogramming languages.")]),e._v(" "),n("p",[e._v("The keywords in M are:")]),e._v(" "),n("ul",[n("li",[e._v("Brackets for grouping: (), [], {}")]),e._v(" "),n("li",[e._v("Commas for indicating sequences: ,")]),e._v(" "),n("li",[e._v("Equality for assignment: =")]),e._v(" "),n("li",[e._v("Dot for cell access: .")])]),e._v(" "),n("h2",{attrs:{id:"parser"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#parser"}},[e._v("#")]),e._v(" Parser")]),e._v(" "),n("p",[e._v("The parser of M is inspired in maths. In particular,\nminimal Turing complete languages with infix operator support\nand a special expression in cells from ECS paradigms.")]),e._v(" "),n("p",[e._v("A program is a list of functions.\nA function has a name, a list of parameters and a list of statements.\nA statement is either a block statement, an assignment or a delegation.\nA block statement has a name, a expression and a list of statements.\nAn assignment assigns an expression to a value or a cell.\nA delegation delegates work to another function and receives the output.\nAn expression can be a binary operator, a unary operator,\na function application, a value or a cell.")]),e._v(" "),n("h2",{attrs:{id:"type-system"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#type-system"}},[e._v("#")]),e._v(" Type system")]),e._v(" "),n("p",[e._v("System F with bounds, possible bounded type variables.")]),e._v(" "),n("p",[e._v("Is a lambda calculus where the set of base types is {Bool,Number,String}\nand there are no constants.\nThe only definable types are the base types and function types t -> t'\nwhere t and t' have been previously defined.\nIt is implicitly typed but fully inferred.")]),e._v(" "),n("p",[e._v("The system is inspired in the theory of algebraic types.\nM supports")]),e._v(" "),n("ul",[n("li",[e._v("sum types: a + b: either type a or b")]),e._v(" "),n("li",[e._v("product types: a x b: a tuple of (type a, type b)")]),e._v(" "),n("li",[e._v("exponent types: a -> b: a function from type a to b")]),e._v(" "),n("li",[e._v("polymorphic types: f<a>: functions on types, for example List<Number>.")])]),e._v(" "),n("p",[e._v("Expressions get typed for two reasons:")]),e._v(" "),n("ul",[n("li",[e._v("Symbol typed in standard library")]),e._v(" "),n("li",[e._v("Entity of a cell")])]),e._v(" "),n("p",[e._v("Typing errors occur when either:")]),e._v(" "),n("ul",[n("li",[e._v("Not enough information to infer the type of an expression")]),e._v(" "),n("li",[e._v("The inferred information is not coherent or compatible")])]),e._v(" "),n("h2",{attrs:{id:"type-inference"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#type-inference"}},[e._v("#")]),e._v(" Type inference")]),e._v(" "),n("p",[e._v("Hindley-Milner style fully implicitly typed inference.\nSimilar to Algorithm W.")]),e._v(" "),n("h2",{attrs:{id:"standard-library"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#standard-library"}},[e._v("#")]),e._v(" Standard library")]),e._v(" "),n("p",[e._v("The standard library defines the types, symbols and\nerrors messages that are available to all files written in M.\nSymbols are\nnames with an associated predefined type. For example,\nThe symbol mass will always have the\ntype number. However, its name will depend on the specific\ninstance of standard library that is in use. In fact, two\ninstances of the standard library are provided, one using\nEnglish as the natural language and the other using Euskara.")]),e._v(" "),n("p",[e._v("The collection of symbols is inspired in game development,\nmostly in the standard components of the Unity game engine.\nCommonly used properties and functions are included. In\nparticular, from the seven main areas of game development:")]),e._v(" "),n("ul",[n("li",[e._v("Rendering")]),e._v(" "),n("li",[e._v("Audio")]),e._v(" "),n("li",[e._v("Networking")]),e._v(" "),n("li",[e._v("Input")]),e._v(" "),n("li",[e._v("Physics")]),e._v(" "),n("li",[e._v("AI")]),e._v(" "),n("li",[e._v("Animation")])]),e._v(" "),n("p",[e._v("Functions are inspired in math libraries and the matrix model\nof the ECS paradigm (CRUD over the matrix):")]),e._v(" "),n("ul",[n("li",[e._v("All the data is stored in a conceptual matrix")]),e._v(" "),n("li",[e._v("The rows represent entities and the columns components")]),e._v(" "),n("li",[e._v("All the simulation in the game happens by modifying the matrix")]),e._v(" "),n("li",[e._v("By removing and adding rows we create and destroy entities")]),e._v(" "),n("li",[e._v("Columns can not be altered at runtime (no dynamic components)")]),e._v(" "),n("li",[e._v("By setting or nullifying cells we add and remove components")]),e._v(" "),n("li",[e._v("By querying for column sets we iterate over entities with\nspecific sets of components")]),e._v(" "),n("li",[e._v("By looking wheter a row has a column active we determine whether\nan entity has a component or not")])]),e._v(" "),n("p",[e._v("It also provides messages to form the error messages. Five kinds\nof errors can happen in M:")]),e._v(" "),n("ul",[n("li",[e._v("Syntax error: Didn't follow the grammar rules")]),e._v(" "),n("li",[e._v("Symbol undefined: Used a symbol that has not been defined")]),e._v(" "),n("li",[e._v("Symbol redefined: Defined a symbol that was already defined")]),e._v(" "),n("li",[e._v("Undecidable type: Not enough information to infer type of an\nexpression")]),e._v(" "),n("li",[e._v("Incompatible types: Inferred multiple types for an expression\nthat are not compatible between them.\nFor example, number and numeric or sum types are compatible.")])]),e._v(" "),n("h2",{attrs:{id:"validation-algorithm"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#validation-algorithm"}},[e._v("#")]),e._v(" Validation algorithm")]),e._v(" "),n("p",[e._v("Custom inference algorithm determines the types of all expressions\ninvolved in a program written in M and reports errors if the\nprocess goes badly.")]),e._v(" "),n("h2",{attrs:{id:"the-ide-project"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#the-ide-project"}},[e._v("#")]),e._v(" The IDE project")]),e._v(" "),n("p",[e._v("To showcase the capabilities of the language server M, a custom\nIDE is built using Theia. It is a node application with a extension.\nThe theia blocks are defined in the root package.json and an\nextension called m is included with backend and frontend contributions\nfor the IDE.")]),e._v(" "),n("h2",{attrs:{id:"the-syntax"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#the-syntax"}},[e._v("#")]),e._v(" The syntax")]),e._v(" "),n("p",[e._v("The goal is to maximize the familiarity of programmers.\nAccording to the Stack Overflow 2020 interview, the top\nused and loved programming languages use curly braces or\nindentation to group statements in blocks.\nM can support both styles independently so that programmers\ncan feel at home and adapt the syntax to their needs.")]),e._v(" "),n("h2",{attrs:{id:"debug-and-production-modes-in-the-generator"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#debug-and-production-modes-in-the-generator"}},[e._v("#")]),e._v(" Debug and production modes in the generator")]),e._v(" "),n("p",[e._v('By default, code is generated in "debug" mode. It is aimed\nat providing maximal flexibility while developing the game.\nWhen required, a production build can be made to maximize\nthe efficiency of the generated code, assuming properties\nof the worlds of the project.')]),e._v(" "),n("h2",{attrs:{id:"parser-and-syntax"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#parser-and-syntax"}},[e._v("#")]),e._v(" Parser and syntax")]),e._v(" "),n("p",[e._v("Based on structured+procedural+modular programming.\nAll code blocks are enclosed by curly braces or indentation.\nPrograms are divided in files that each contain functions\nthat can call each other.\nThe return statement is a delegation to the return function\nwhich can have a different name in each natural language.")]),e._v(" "),n("p",[e._v("A program is divided in files.\nA file is a collection of functions.\nA function has a name, a list of parameters and a block of\nstatements.")]),e._v(" "),n("p",[e._v("A statement is a controlled block of statements or\nassignment or an application.")]),e._v(" "),n("p",[e._v("Assignment is an statement not an operator or expression in M.")]),e._v(" "),n("p",[e._v("M is a expression oriented language.")]),e._v(" "),n("h2",{attrs:{id:"licensing"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#licensing"}},[e._v("#")]),e._v(" Licensing")]),e._v(" "),n("p",[e._v("The project is licensed under AGPL v3 to enforce\nthat it will always remain open sourced.")]),e._v(" "),n("p",[e._v("The NOTICE.md file in the root folder contains the attribution\nto the open source frameworks that get bundled when the desktop\napplications are built.")]),e._v(" "),n("p",[e._v("It has two parts: First explains that the source code in the\nrepository is licensed under AGPLv3 and Martin Azpillaga Aldalur\nis the copyright holder.")]),e._v(" "),n("p",[e._v("Second, it lists the bundled frameworks with their author names\nand a link to the open source initiave webpage of the\ncorresponding license as well as a link to the source code repository.")]),e._v(" "),n("h2",{attrs:{id:"classification-what-is-m-technically"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#classification-what-is-m-technically"}},[e._v("#")]),e._v(" Classification. What is M technically")]),e._v(" "),n("p",[e._v("M is a Imperative Structured programming Procedural programming Modular\nprogramming Data oriented programming DSL Compiled External C-like concrete\nsyntax textual Scratch like visual syntax Abstract syntax is a EMF model\nStrongly typed with implicit types Type inferred and validated No null\nreference, no type conversion error, no infinite loop Execution semantics: Game\nengine projects with assets Useful for non programmers Turing complete Efficient\ncompilation Efficient generated code, native multi threaded Clean: Native\nsupport for DOD Full IDE support:")]),e._v(" "),n("ul",[n("li",[e._v("Code completion")]),e._v(" "),n("li",[e._v("Code generation")]),e._v(" "),n("li",[e._v("Syntax Highlight")]),e._v(" "),n("li",[e._v("Error markers")]),e._v(" "),n("li",[e._v("Quick fixes")]),e._v(" "),n("li",[e._v("Formatting")]),e._v(" "),n("li",[e._v("Refactoring")]),e._v(" "),n("li",[e._v("Labels")]),e._v(" "),n("li",[e._v("Outline view")]),e._v(" "),n("li",[e._v("Find uses")]),e._v(" "),n("li",[e._v("Find definition")]),e._v(" "),n("li",[e._v("Debugging")])]),e._v(" "),n("p",[e._v("Small syntax Concise User defined abstractions: Files as libraries, functions\nfor reusability, prefabs possibly nested Fast and volatile development of\nlanguage No collections, all entities Big language scope: Most games M improves:")]),e._v(" "),n("ul",[n("li",[e._v("Productivity: Less code to write and read")]),e._v(" "),n("li",[e._v("Quality: No runtime errors, halts, multi threaded")]),e._v(" "),n("li",[e._v("Communication and thinking: Familiar domain concepts, different interfaces")]),e._v(" "),n("li",[e._v("Platform support: Any platform with JVM any platform with Unity support")])]),e._v(" "),n("h2",{attrs:{id:"turing-complete"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#turing-complete"}},[e._v("#")]),e._v(" Turing complete")]),e._v(" "),n("p",[e._v("M is turing complete. Indeed, given a Turing machine with a table of rules, one\ncan simulate it in M as follows:")]),e._v(" "),n("p",[e._v("The tape is a list of entities, each having previous, next and value. Previous\nand next refer to the previous and next entity cells and value stores the value\nof the cell.")]),e._v(" "),n("p",[e._v("Create an entity global with state, haltState and initialCell. State determines\nthe internal state of the Turing machine and the haltState is the state where\nthe computation stops. Place the head of the machine in the entity referenced by\ninitialCell.")]),e._v(" "),n("p",[e._v("Create an entity entry with components state, value, writeState, writeValue,\ncontinueRight. The combination of state and value determine when to use this\ninstruction. In that case, replace the global state with writeState, replace the\ncell value with writeValue and proceed right if continueRight else proceed left.")]),e._v(" "),n("p",[e._v("The algorithm follows by storing the currentCell in a variable of type Entity:")]),e._v(" "),n("div",{staticClass:"language-m extra-class"},[n("pre",{pre:!0,attrs:{class:"language-text"}},[n("code",[e._v("currentCell = global.initialCell\n\nwhile global.state != global.haltState\n{\n    for each a with a = currentCell\n    {\n        for each entry\n        {\n            if global.state = entry.state and a.value = entry.value\n            {\n                a.value = entry.writeValue\n                global.state = entry.writeState\n\n                if (entry.continueRight)\n                {\n                    currentCell = a.next\n                }\n                else\n                {\n                    currentCell = a.previous\n                }\n            }\n        }\n    }\n}\n")])])])])}),[],!1,null,null,null);t.default=i.exports}}]);