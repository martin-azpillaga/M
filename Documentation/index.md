---
home: true
heroImage: https://avatars2.githubusercontent.com/u/6147446?s=400&u=c325f5bfc187e889f05cc872681ad1cb1ba6b3b5&v=4
heroText: Project M
tagline: A data-driven programming language for game developers
actionText: Get Started →
actionLink: /0.%20Starter%20guide/0.%20Set%20up.html
features:
- title: Simple
  details: A carefully crafted minimalist programming language with a built-in game programming standard library and implicit type inference.
- title: Flexible
  details: Use any editor with the language server protocol support like VS Code, and see the results in different engines. Unreal, Unity or Godot.
- title: Performant
  details: The code written in M is optimizable by default, runs in parallel across cores and can run in native code speed sometimes.
footer: AGPL Licensed | Copyright © 2017-present Martin Azpillaga Aldalur
---

<h1> Presentation video </h1>
<div style="width:100%;height:0;padding-bottom:56.25%;position:relative">
<iframe src="https://www.youtube.com/embed/_EBhQk6f7Ow" frameborder="0" allowfullscreen style="position: absolute;width:100%; height:100%"></iframe>
</div>

## Presentation

Hi, I'm Martin.
And I'm the creator of M.
A new programming language for game developers.

In this video, I will go over the key features that make M unique ??

Keep in mind that, the project is still in active development and not all the features presented here are not fully implemented yet.

However, an early prototype of M is available for the public.
And that's what I will be using in throughout this demo.

Ok, let'ss do this.

## M is a tool

One of the design goals of M is to be a tool that you can integrate in your workflow.
It's not aiming to replace your development stack but rather complement it.

Let me show you what I mean.

### Editor of your choice

M is a free and open source language server.
An independendent application that communicates with code editors using the Language Server protocol to provide rich development experience.

It knows the answers to code editor requests like. Could you please complete this word?
veloc  -> velocity
or Is this file gramatically correct ?
file icon -> No, error in line 10.

In practice, it means that you can develop a project in M using the editor of your choice.

In this demo, I will use Microsoft's Visual Studio Code.

I can search for the M language server extension in the marketplace, install it.
And that's it, I'm ready to start developing!

### Engine of your choice

This is a simple project written in M.
It consists of a single .m file that holds all the code.

If you are a C or Java developer, you might find the syntax familiar.
However, the execution semantics are quite different.

Maybe you've spotted that there is a syntax error in this file.
VSCode knows about it too, I missed a closing bracket.

Let's fix it. Now that our project is free of errors, M has produced and output folder for us.

Inside, you won't find an executable file.
Instead, M generates code that game engines can understand.

That's right. M is meant to be used Alongside your game engine, be it Unity, Unreal or Godot.

M has generated readable C# Unity classes for every component and system describe in the code.
Similarly, M has generated C++ files for Unreal project, and GDScript scripts if you plan to work with Godot.
All these files will be regenerated whenever we modify our .m source files to be updated to the latest version of our code.

The idea is to use M to write the code and use the game engine as the level editor for your game.

Let me open the Unity project. Pong demo ??

## Properties

But then, why use M in the first place? Why not develop the code in the engine's language?

As every good tool, M solves one problem and one problem well.
And allows you to create amazing things when used In Collaboration with other great tools.
It is only when you combine it with other great tool that you can create something amazing.

So what is the problem that M is trying to solve?
To develop gameplay programming code in a simple, maintainable and performant way.
Code that's easy to understand, gives the game designers maximal flexibility while designing the game yet can run in multithreaded and cache friendly way to use the resources of modern computers efficiently.

### M is simple

Which means it's easy to write and Specially easy to read.
Being focused on the game development domain allows M to have a concise syntax without much boilerplate.

Its standard library defines components that are common in games like velocity, mass or materials.
So you can focus on the components that are unique to your game.

Defining new components is easy. Use them in a system and the type inference will deduce it's type.
No need for class declarations or type annotations.
M's powerful type inference will deduce all the types for you and show an error when something does not quite fit.

### M is flexible

M enforces data oriented design natively.

In particular, it follows the Entity Component System paradigm to decouple the game into independent systems describing the logic and components that hold all the data.

I can remove this system and I don't have to worry about affecting other parts of the program. Which makes debugging code easier.

More importantly, M improves the flexibility of game designers.
M has no literal values.
All the data is stored in components, which means that designers can tweak all the parameters from within the game editor.

In fact, programming and designing become independent jobs, providing an important boost in team productivity.

### M is powerful

Don't get fooled by the simplicity.

M can generate cache friendly multithread ready code that utilizes modern hardware efficiently.

Take a look. Back in Unity I can toggle this switch and now all the code is running in parallel across cores.

Pong is a classic example that every game developer can understand, but M is capable of much more.
You can code 3D games with animations, advanced physics, networking, AI... covering all areas of gameplay programming.

And remember, if you find something missing, don't worry. You can always use the engine's language to solve it and use both
approaches in collaboration.

## Call to action

That's it for this video.

From here, I leave you with two options.
If you are a hands on person, head over to minim.tools/M and follow the Starter guide to get started.
If you are more interested in the theoretical and technical aspects, including some experimental features not mentioned in this video, I recommend you to check out the Developer Guide at minim.tools/M.

Feel free to contact me at martin@minim.tools.

Thanks for watching.
