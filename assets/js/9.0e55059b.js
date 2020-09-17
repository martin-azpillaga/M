(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{375:function(e,t,o){"use strict";o.r(t);var a=o(42),n=Object(a.a)({},(function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[o("h2",{attrs:{id:"presentation"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#presentation"}},[e._v("#")]),e._v(" Presentation")]),e._v(" "),o("p",[e._v("Hi, I'm Martin.\nAnd I'm the creator of M.\nA new programming language for game developers.")]),e._v(" "),o("p",[e._v("In this video, I will go over the key features that make M unique ??")]),e._v(" "),o("p",[e._v("Keep in mind that, the project is still in active development and not all the features presented here are not fully implemented yet.")]),e._v(" "),o("p",[e._v("However, an early prototype of M is available for the public.\nAnd that's what I will be using in throughout this demo.")]),e._v(" "),o("p",[e._v("Ok, let'ss do this.")]),e._v(" "),o("h2",{attrs:{id:"m-is-a-tool"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#m-is-a-tool"}},[e._v("#")]),e._v(" M is a tool")]),e._v(" "),o("p",[e._v("One of the design goals of M is to be a tool that you can integrate in your workflow.\nIt's not aiming to replace your development stack but rather complement it.")]),e._v(" "),o("p",[e._v("Let me show you what I mean.")]),e._v(" "),o("h3",{attrs:{id:"editor-of-your-choice"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#editor-of-your-choice"}},[e._v("#")]),e._v(" Editor of your choice")]),e._v(" "),o("p",[e._v("M is a free and open source language server.\nAn independendent application that communicates with code editors using the Language Server protocol to provide rich development experience.")]),e._v(" "),o("p",[e._v("It knows the answers to code editor requests like. Could you please complete this word?\nveloc  -> velocity\nor Is this file gramatically correct ?\nfile icon -> No, error in line 10.")]),e._v(" "),o("p",[e._v("In practice, it means that you can develop a project in M using the editor of your choice.")]),e._v(" "),o("p",[e._v("In this demo, I will use Microsoft's Visual Studio Code.")]),e._v(" "),o("p",[e._v("I can search for the M language server extension in the marketplace, install it.\nAnd that's it, I'm ready to start developing!")]),e._v(" "),o("h3",{attrs:{id:"engine-of-your-choice"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#engine-of-your-choice"}},[e._v("#")]),e._v(" Engine of your choice")]),e._v(" "),o("p",[e._v("This is a simple project written in M.\nIt consists of a single .m file that holds all the code.")]),e._v(" "),o("p",[e._v("If you are a C or Java developer, you might find the syntax familiar.\nHowever, the execution semantics are quite different.")]),e._v(" "),o("p",[e._v("Maybe you've spotted that there is a syntax error in this file.\nVSCode knows about it too, I missed a closing bracket.")]),e._v(" "),o("p",[e._v("Let's fix it. Now that our project is free of errors, M has produced and output folder for us.")]),e._v(" "),o("p",[e._v("Inside, you won't find an executable file.\nInstead, M generates code that game engines can understand.")]),e._v(" "),o("p",[e._v("That's right. M is meant to be used Alongside your game engine, be it Unity, Unreal or Godot.")]),e._v(" "),o("p",[e._v("M has generated readable C# Unity classes for every component and system describe in the code.\nSimilarly, M has generated C++ files for Unreal project, and GDScript scripts if you plan to work with Godot.\nAll these files will be regenerated whenever we modify our .m source files to be updated to the latest version of our code.")]),e._v(" "),o("p",[e._v("The idea is to use M to write the code and use the game engine as the level editor for your game.")]),e._v(" "),o("p",[e._v("Let me open the Unity project. Pong demo ??")]),e._v(" "),o("h2",{attrs:{id:"properties"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#properties"}},[e._v("#")]),e._v(" Properties")]),e._v(" "),o("p",[e._v("But then, why use M in the first place? Why not develop the code in the engine's language?")]),e._v(" "),o("p",[e._v("As every good tool, M solves one problem and one problem well.\nAnd allows you to create amazing things when used In Collaboration with other great tools.\nIt is only when you combine it with other great tool that you can create something amazing.")]),e._v(" "),o("p",[e._v("So what is the problem that M is trying to solve?\nTo develop gameplay programming code in a simple, maintainable and performant way.\nCode that's easy to understand, gives the game designers maximal flexibility while designing the game yet can run in multithreaded and cache friendly way to use the resources of modern computers efficiently.")]),e._v(" "),o("h3",{attrs:{id:"m-is-simple"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#m-is-simple"}},[e._v("#")]),e._v(" M is simple")]),e._v(" "),o("p",[e._v("Which means it's easy to write and Specially easy to read.\nBeing focused on the game development domain allows M to have a concise syntax without much boilerplate.")]),e._v(" "),o("p",[e._v("Its standard library defines components that are common in games like velocity, mass or materials.\nSo you can focus on the components that are unique to your game.")]),e._v(" "),o("p",[e._v("Defining new components is easy. Use them in a system and the type inference will deduce it's type.\nNo need for class declarations or type annotations.\nM's powerful type inference will deduce all the types for you and show an error when something does not quite fit.")]),e._v(" "),o("h3",{attrs:{id:"m-is-flexible"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#m-is-flexible"}},[e._v("#")]),e._v(" M is flexible")]),e._v(" "),o("p",[e._v("M enforces data oriented design natively.")]),e._v(" "),o("p",[e._v("In particular, it follows the Entity Component System paradigm to decouple the game into independent systems describing the logic and components that hold all the data.")]),e._v(" "),o("p",[e._v("I can remove this system and I don't have to worry about affecting other parts of the program. Which makes debugging code easier.")]),e._v(" "),o("p",[e._v("More importantly, M improves the flexibility of game designers.\nM has no literal values.\nAll the data is stored in components, which means that designers can tweak all the parameters from within the game editor.")]),e._v(" "),o("p",[e._v("In fact, programming and designing become independent jobs, providing an important boost in team productivity.")]),e._v(" "),o("h3",{attrs:{id:"m-is-powerful"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#m-is-powerful"}},[e._v("#")]),e._v(" M is powerful")]),e._v(" "),o("p",[e._v("Don't get fooled by the simplicity.")]),e._v(" "),o("p",[e._v("M can generate cache friendly multithread ready code that utilizes modern hardware efficiently.")]),e._v(" "),o("p",[e._v("Take a look. Back in Unity I can toggle this switch and now all the code is running in parallel across cores.")]),e._v(" "),o("p",[e._v("Pong is a classic example that every game developer can understand, but M is capable of much more.\nYou can code 3D games with animations, advanced physics, networking, AI... covering all areas of gameplay programming.")]),e._v(" "),o("p",[e._v("And remember, if you find something missing, don't worry. You can always use the engine's language to solve it and use both\napproaches in collaboration.")]),e._v(" "),o("h2",{attrs:{id:"call-to-action"}},[o("a",{staticClass:"header-anchor",attrs:{href:"#call-to-action"}},[e._v("#")]),e._v(" Call to action")]),e._v(" "),o("p",[e._v("That's it for this video.")]),e._v(" "),o("p",[e._v("From here, I leave you with two options.\nIf you are a hands on person, head over to minim.tools/M and follow the Starter guide to get started.\nIf you are more interested in the theoretical and technical aspects, including some experimental features not mentioned in this video, I recommend you to check out the Developer Guide at minim.tools/M.")]),e._v(" "),o("p",[e._v("Feel free to contact me at martin@minim.tools.")]),e._v(" "),o("p",[e._v("Thanks for watching.")])])}),[],!1,null,null,null);t.default=n.exports}}]);