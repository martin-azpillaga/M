# What is M

M is a data oriented programming language for game developers.

It increases productivity by reducing the development *and* maintenance time of
projects.

The following quote represents the philosophy behind the project:

>Everything should be as simple as it can be, but no simpler

## Key features

Why.

* **M is data oriented**

  M encourages data oriented design by default, a programming paradigm proven to
  be specially well suited for game development.

  This improves the performance and flexibility of the project. [Learn why].

* **M involves all your team**

  Programmers will feel at home with a C-like syntax that can be edited using
  any editor that implements the [language server protocol].

  Artists, designers and project managers might prefer the block based [online
  visual editor].

  Moreover, all these [interfaces can be combined] allowing everyone to
  communicate their ideas better, across the entire team.

* **M integrates in your workflow**

  M does *not* compile the code down to an executable game. Instead, it
  generates a high quality project using the [game engine of your choice].

  Everyone can inspect the generated code, tweak values and import custom art
  pieces made in their favorite digital content creation suites.

  M does not compete with the tools you know and love. It provides you with a
  tool that will improve your development journey.

More technical [language properties].

---

## Get started

How.

There are currently four ways you can create programs in M: theia, blockly,
electron, headless.

1. Open an editor
2. Copy an example code file
3. Open the results in a game engine
4. That's the workflow
5. If you want to learn step by step go to.
6. Try it online, upload the blocks file
7. You can also develop everything online.

* **Online text editor**

1. Download the [latest standalone editor]. It requires no installation, just
   extract the folder and run the executable.

2. Click on new project, name it HelloM and select the Text, Blocks and Hybrid
   Unity interfaces.

3. Open the `HelloM/Text/main.text` file and create an entity like so:

    ball has mass 10, position 1 2 and appearance sprite.ball.

4. Save the file. Congratulations, you can find the generated project inside the
   Hybrid Unity folder.

* **Visual online editor**

1. Hit the load button and open the generated `HelloM/Blocks/main.blocks` file.

2. Try adding a component named friction with value 10 using the Numeric menu.

3. Hit the save button and override the `HelloM/Blocks/main.blocks` with the
   downloaded file.

4. Save the file. Both the Text and Hybrid Unity interfaces will synchronize to
   the new changes.

* **Standalone desktop application**

1. Download the [latest standalone editor] for Linux.

2. Follow steps in A.

## Become an expert

You can become an expert in M within a week by reading its thorough
[documentation].

If you are new to data oriented design, it is recommended that first you go
through the [key concepts] and then continue with the specific [implementation
of M].

Then learn the [language syntax] and additionally, you can get inspiration from
the [example projects] hosted in this repository.

If you want to dig deeper, the source code is available in closed beta right
now. Contact martin@minim.games to request access and a friendly onboarding
experience.

---

## Help make M even better

If you enjoy the project you can help its development by contributing, sharing
and donating.

The easiest way to contribute is by [creating an issue], both bug reports and
feature requests are welcome. Be up to date with the development by checking the
[kanban project].

If you are a developer and want to help extending M, you can request access to
the private repository where the source code is hosted by contacting the author
at martin@minim.games. The full documentation of the internal language and
workflow API will be available in [the wiki].

Finally, you can help this project succeed by donating any amount using GitHub's
built-in sponsorship feature. Just click the sponsor heart button above. All the
donations will be used exclusively in the development costs of the project.

## Authors and licensing

What can I do with it?

This project has been developed with love by [Martin Azpillaga Aldalur], and is distributed under the [Affero GNU Public License].

The language implementation would be impossible without the [Xtext framework]
developed by the Eclipse foundation. The Xtext framework is distributed under
the [Eclipse Public License 1.0].

The online and standalone editors are powered by the [Theia framework] developed
by TypeFox. Theia is licensed under the [Apache 2.0 license].

The online editor server is hosted for free thanks to the [Glitch framework]
developed by the company Glitch. Glitch is licensed under the [Apache 2.0
license].

The visual editor uses the [Blockly framework] developed by Google. The Blockly
framework is distributed under the [Apache 2.0 license].

[interfaces can be combined]: 1.%20User%20guide/0.%20Reference/4.%20Platforms.md
[game engine of your choice]: 1.%20User%20guide/0.%20Reference/1.%20Domain.md
[documentation]: 1.%20User%20guide/0.%20Reference/0.%20Grammar.md
[key concepts]: https://en.wikipedia.org/wiki/Data-oriented_design
[implementation of M]: 2.%20Developer%20guide/1.%20Implementation/README.md

[example projects]: 0.%20Beginner%20guide/2.%20Guided%20examples/README.md
[the wiki]: 2.%20Developer%20guide/0.%20Philosophy/README.md
[language syntax]: 1.%20User%20guide/0.%20Reference/0.%20Grammar.md
[language properties]: 2.%20Developer%20guide/0.%20Philosophy/2.%20Properties.md

[Learn why]: https://en.wikipedia.org/wiki/Data-oriented_design
[Mike Acton's talk]: https://www.youtube.com/watch?v=rX0ItVEVjHc

[language server protocol]:
https://en.wikipedia.org/wiki/Language_Server_Protocol
[online visual editor]:
https://martin-azpillaga.github.io/M/
[online text editor]: https://cflat.glitch.me
[latest standalone editor]:
https://github.com/martin-azpillaga/M/releases/download/2019.1/M.AppImage

[Hard challenges]: https://github.com/martin-azpillaga/M/issues
[creating an issue]: https://github.com/martin-azpillaga/M/issues
[Kanban project]: https://github.com/martin-azpillaga/M/projects/1

[Martin Azpillaga Aldalur]: https://martin-azpillaga.github.io/
[Xtext framework]: https://github.com/eclipse/xtext
[Theia framework]: https://www.theia-ide.org
[Glitch framework]: https://glitch.com/edit/#!/community
[Blockly framework]: https://github.com/google/blockly
[Apache 2.0 license]: https://www.apache.org/licenses/LICENSE-2.0
[Eclipse Public License 1.0]: https://www.eclipse.org/legal/epl-v10.html
[Affero GNU Public License]: https://www.gnu.org/licenses/agpl-3.0.en.html
