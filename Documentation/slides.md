# Presentation

Hi, I'm Martin.
And I'm the creator of M.
A new programming language for game developers.

In this video, I will give an overview of the key features of M.
For detailed information, visit the link to the documentation at the end of the video.

Keep in mind, that the project is still in active development.
Not all the features presented here are fully implemented yet.

However, an early prototype of M is available for the public.
And I will be using that throughout this demo.

Ok, let'ss do this.

## M is a tool

One of the design goals of M is to be a tool that you can integrate in your workflow.
It's not aiming to replace your development stack but rather complement it.

Let me show you what I mean.

### Editor of your choice

M is a free and open source language server.
An independendent application that communicates with code editors via the LSP.
The Language Server provides rich development experience.

It knows the answers to code editor requests like:
Could you please complete this word?
veloc  -> velocity
or Is this file gramatically correct ?
file icon -> No, error in line 10.

In practice, you can develop a project in M using the editor of your choice.

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

Let's fix it.
Now that our project is free of errors, M has produced and output folder for us.

Inside, you won't find an executable file.
Instead, M generates code that game engines can understand.

That's right.
M is meant to be used Alongside your game engine, be it Unity, Unreal or Godot.

M has generated readable C# Unity classes for every component and system.
Similarly, M has generated C++ files for Unreal project.
And GDScript scripts if you plan to work with Godot.
All these files will be regenerated whenever we modify our .m source files.

The idea is to use M to write the code and the game engine as the level editor.

Let me open the Unity project. Pong demo ??

Show the system debugger and entity debugger.

M doesn't generate executable games, but game projects that you can edit using
a game engine.

You can inspect the generated code, tweak design values and customize the look
with art pieces made in your favorite content creation program.

## Properties

But then, why use M in the first place?
Why not develop the code in the engine's language?

As every good tool, M solves one problem and one problem well.
It is only when you combine it with other great tools that you can create
something amazing.

So what is the problem that M is trying to solve?
To develop gameplay programming code in a simple, maintainable and performant way.
Code that's easy to understand, gives the game designers maximal flexibility
yet can run fast, using the resources of modern computers efficiently.

### M is simple

Which means it's easy to write and Specially easy to read.
Being focused on the game development domain allows M to have a concise syntax.

Its standard library defines components that are common in games like
velocity, mass or materials.
So you can focus on the components that are unique to your game.

Defining new components is easy.
Use them in a system and the type inference will deduce it's type.
No need for class declarations or type annotations.
M's powerful type inference will deduce all the types for you
and show an error when something does not quite fit.

### M is flexible

M enforces data oriented design natively.

In particular, it follows the Entity Component System paradigm to decouple
the game into independent systems describing the logic and components
that hold all the data.

I can remove this system and I don't have to worry about affecting other parts.
Which makes debugging code easier.

More importantly, M improves the flexibility of game designers.
M has no literal values.
All the data is stored in components,
which means that designers can tweak all the parameters from within the game editor.

In fact, programming and designing become independent jobs,
providing an important boost in team productivity.

### M is powerful

Don't get fooled by the simplicity.

M can generate cache friendly and multithread ready code.

Take a look.
I can toggle this switch and now all the code is running in parallel across cores.

Pong is a classic example that every game developer can understand,
but M is capable of much more.
You can code 3D games with animations, advanced physics, networking, AI...
covering all areas of gameplay programming.

And remember, if you find something missing, don't worry.
You can always use the engine's language to solve it and use both
approaches in collaboration.

## Call to action

That's it for this video.

From here, I leave you with two options.
If you are a hands on person, go to minim.tools/M and follow the Starter guide.
If you are more interested in the theoretical and technical aspects,
including some experimental features not mentioned in this video,
I recommend you to check out the Developer Guide at minim.tools/M.

Feel free to contact me at martin@minim.tools.

Thanks for watching.
