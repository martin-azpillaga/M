<<<<<<< HEAD
<<<<<<< HEAD
# What is M

M is a data oriented programming language for developing games and interactive
experiences.

M increases productivity by reducing the development *and* maintenance time of
projects.

The following quote represents the philosophy behind the project:

> Everything should be as simple as it can be, but no simpler

Simple does not mean easy. Minimalism and the power of composition.

## Why do I care

* M is data oriented

  M encourages data oriented design by default, a programming paradigm proven to
  be specially well suited for game development.

  It provides flexibility while developing the game and performance at runtime.

* M involves all your team

  Programmers will feel at home developing in a vs-code-based text editor.

  Artists and designers might prefer the block based online visual editor.

  Tool developers can extend the headless program and develop their your own
  tools.

  The connection of these interfaces allows everyone to communicate their ideas
  better, across the entire team.

* M integrates in your workflow

  M doesn't generate executable games, but game projects that you can edit using
  a game engine.

  You can inspect the generated code, tweak design values and customize the look
  with art pieces made in your favorite content creation program.

  M does not compete with the tools you know and love. It provides you with a
  tool that will improve your development journey.

## How can I get it

M is an open source software licensed under the AGPL license.

In non legal terms, it means that you have the freedom to use it, inspect it and
make your own version, for free.

You can also redistribute it, but make sure you do it under the same license.

If you agree, you can [download] the latest version. No need for installation. You
are ready to start.

## Getting started

You can get your first game project running in five steps:

1. Download and run the editor
2. Download the code for the example project
3. Place the file in the workspace of the editor
4. Save the file
5. You can find your customizable project in the generated Unity folder

Optionally, in three more steps, you will be editing the code visually online:

1. Open the block based online editor
2. Click upload and select the `example.blocks` found in the Blocks folder
3. Try experimenting with different kinds of blocks.

If you want to understand more about how this works and develop your own
projects, check the beginner guide in the documentation.

It includes a step by step guide for creating your first project from scratch,
how to guides for common use cases and guided examples.

## Go deeper

The documentation also contains guides for users and developers.

Learn about the syntax of M, the special semantics for interactive experiences
and troubleshooting for common issues you might encounter to use it efficiently.

Make sure you fulfill the prerequisites before reading a guide and you will
become an expert in no time.

Then you may become a contributor and shape the development of the M project.

The structure and design decisions behind the source code are also written in
the documentation.

## How can I help

Contributors from all around the world make maintaining this project possible.
You don't need to be an expert to help this project succeed.

All kind actions are welcome. Here are some ideas:

* Join the conversation

  * Got a question? Open an issue.

  * Found a bug? Open an issue.

  * Missing a feature? Open an issue.

  We will reply to your suggestions.

* Become a sponsor

  You can donate using GitHub's built-in sponsorship feature. Click the sponsor
  heart button and set the amount you feel comfortable with.

Thank you.

[download]: https://github.com/martin-azpillaga/M/releases/latest/download/m-0.0.0.AppImage
=======
# M

M is a data oriented programming language for game developers.
>>>>>>> archive/gh-pages
=======
# M

[English version](/Documentation/English/Home.md) --
[Versión en castellano](/Documentation/Castellano/Inicio.md) --
[Euskarazko bertsioa](/Documentation/Euskara/Hasiera.md) 

M is a data oriented programming language for game developers.

It increases productivity by reducing the development *and* maintenance time of projects while making the overall process more enjoyable.

The philosophy behind the project can be summarized by the excellent quote:

>Everything should be as simple as it can be, but no simpler

## Key features

* **M is data oriented**

    M encourages data oriented design by default, a programming paradigm proven to be specially well suited for game development.

    This improves the overall performance and flexibility of the project.  [Learn why](/Documentation/English/Data-oriented-design.md).

* **M is not just for programmers**

     Programmers will feel at home with a C-like syntax that can be edited using any editor that implements the [language server protocol](/Documentation/English/Language-server-protocol.md).

    Artists, designers and project managers might prefer the block based [online visual editor](/Visual-editor/).

    Moreover, all these interfaces can be seamlessly [combined](/Documentation/English/Combining-interfaces.md) allowing everyone to communicate their ideas better, across the entire team.

* **M integrates in your workflow**

    M does *not* compile the code down to an executable game. Instead, it generates a high quality project using the [game engine of your choice](/Documentation/English/Game-engine-support.md).

    Everyone can inspect the generated code, tweak values and import custom art pieces made in their favorite digital content creation suites.

    M does not compete with the tools you know and love. It provides you with an additional one that will, most likely, improve your development journey.

---

## Get started

The fastest way to start is by using the custom  standalone editor.

1. Download the [latest standalone editor](https://github.com/martin-azpillaga/M/releases). It requires no installation, just extract the folder and run the executable.

2. Click on new project, name it HelloM and select the Text, Blocks and Hybrid Unity interfaces.

3. Open the `HelloM/Text/main.text` file and create an entity like so:

    ```m
        ball has mass 10, position 1 2 and appearance sprite.ball.
    ```

4. Save the file. Congratulations, you can find the generated project inside the Hybrid Unity folder -might require a refresh-.

Optionally, you might want to try the block based
[online editor](/Visual-editor/).

1. Hit the load button and open the generated `HelloM/Blocks/main.blocks` file.  

2. Try adding a component named friction with value 10 using the Numeric menu.

3. Hit the save button and override the `HelloM/Blocks/main.blocks` with the downloaded file.

4. Save the file. Both the Text and Hybrid Unity interfaces will synchronize to the new changes.

## Become an expert

You can become an expert in M within a week by reading its thorough  
[documentation](/Documentation/English/Index.md).

If you are new to data oriented design, it is recommended that first you go through the 
[key concepts](/Documentation/English/Data-oriented-design.md) 
and then continue with the specific 
[implementation of M](/Documentation/English/Api.md).

Additionally, you can get inspiration from the
[example projects](https://github.com/martin-azpillaga/M/tree/gh-pages/Examples)
hosted in this repository.

---

## Project status

The project M has been in active development since mid 2017 with over 4000 hours of research and development as of mid 2019.

The project has gone through [several versions](https://github.com/martin-azpillaga/M/releases) and is slowly but steadily reaching a mature state of stability and feature completeness.

There are still some [hard challenges](https://github.com/martin-azpillaga/M/issues) to overcome and generally a lot of polishing to do before considering the project production ready.

The project started to be hosted in Github since mid 2019, so previous history is not present online. Since then, anyone can stay tuned about the next planned features by checking the
[kanban project](https://github.com/martin-azpillaga/M/projects/1).

## Help make M even better

If you enjoy the project you can help its development by contributing, sharing and donating.

The easiest way to contribute is by 
[creating an issue](https://github.com/martin-azpillaga/M/issues), both bug reports and feature requests are welcome.

If you are a developer and want to help extending M, you can request access to the private repository where the source code is hosted by contacting the author <martin@minim.games>.
The full documentation of the internal language and workflow API will be available in [the wiki](https://github.com/martin-azpillaga/MSource/wiki).

Finally, you can help this project succeed by donating any amount using GitHub's built-in sponsorship feature. Just click the sponsor heart button above. All the recaudation will be used exclusively in the development costs of the project.

---

## Authors and licensing

This project has been developed with love by [Martin Azpillaga Aldalur](https://martin-azpillaga.github.io/), a software tool developer and mathematician.

The language implementation would be impossible without the [Xtext framework](https://github.com/eclipse/xtext) developed by the Eclipse foundation.
The Xtext framework is distributed under the [Eclipse Public License 1.0](https://www.eclipse.org/legal/epl-v10.html).

The online and standalone editors are powered by the 
[Theia framework](https://www.theia-ide.org)
developed by TypeFox. Theia is licensed under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).

The visual editor uses the [Blockly framework](https://github.com/google/blockly) developed by Google.
The Blockly framework is distributed under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
>>>>>>> private/development
