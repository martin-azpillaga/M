# M

[English version](/Documentation/English/Home.md) |
[Versión en castellano](/Documentation/Castellano/Inicio.md) |
[Euskarazko bertsioa](/Documentation/Euskara/Hasiera.md)

M is a data oriented programming language for game developers.

It increases the productivity of professionals by reducing both the development and maintenance time of projects, while making the overall process more enjoyable.

M is built following the philosophy of the excellent quote:

>Everything should be as simple as it can be, but no simpler.
>
>Albert Einstain [?](https://quoteinvestigator.com/2011/05/13/einstein-simple/)

## Key features

* M is data oriented.

    M encourages data oriented design natively. A programming paradigm that has proven to be specially well suited for developing games.

    This improves the overall performance by and flexibility of the project. Learn more about data oriented design in the [Link to data oriented design summary](gitlink).

* M involves the entire team.

    M is not only for programmers. If you are an artist, a designer or a project manager, you can leverage M to better communicate your ideas across your team.

    Thanks to its visual workflow no previous experience in programming is required. You can try the [block editor online](/Visual-editor/).
    Programmers can use whatever text editor they like that supports the [language server protocol](Language-server-protocol.md).

* M is extensible.

    The outcome of a game described using M is not a executable game but rather a game project in a game engine.
    integrates with existing tools. Link to Unity.

    If there is something you would like to modify or add just modify the resulting project. For example, M includes the integration with the Unity game engine.

## Get started

You can interact with M using its visual or textual editor.

* Textual editor workflow: Head to the releases and download the [latest standalone editor](gitlink). It requires no installation, extract it and you can start playing with it.

```m
    ball has mass 10 position 1 2.
```

* Visual Editor workflow: You can try a Scratch-like block editor online for free at [the editor's page](/editor/) and play around with blocks. For example, you could try recreating the following entity using the blocks under the Entities and Components menu:

```m
    ball has mass 10 position 1 2.
```

## Become an expert

You can become an export in M within a week thanks to its documentation. Just head to [online documentation page](/documentation/en/home.md) and discover all its benefits with your own hands.

Alternatively, you can [download the pdf version](pdflink) for offline reading.

You can also get inspiration by inspecting the [example projects](examplesgitlink) hosted in this repository.

## Project status

The project M has been in active development for two years with over 3000 hours of research and development time.

 and will continue being developed.

and has over 3000 hours of research and development invested in it. although it has oly become part of github since mid 2019.

The project is still in continuous developmnet. Stay tuned about the next planned features by checking the public [kanban project](gitlink).

## Help make M even better

If you enjoy the project you can help its development in many ways.

The easiest way to contribute is by creating an issue, whether its a bug report or a feature request in this same repository. You can inspect current issues and create a new one [in the issues tab](gitlink).

If you are a developer and want to help extending M, request access to the private repository where the source code is hosted by contacting [Martin](gitlink) and follow the standard pull request workflow.
For full documentation of the language and workflow API please visit [the wiki](gitlink).

Finally, you can help this project succeed by donating any amount using GitHub's built-in sponsorship feature. Just click the sponsor heart button above. All the recaudation will be used exclusively in the development costs of the project.

## Authors and licensing

This project has been developed with love by Martin Azpillaga Aldalur, a software tool developer and mathematician. You can find his Github profile can be found at [his homepage](gitlink).

The language implementation would be impossible without the Xtext framework developed by the Eclipse foundation at [github repository](gitlink).
The Xtext framework is distributed under the Eclipse public license.

The visual editor uses the Blockly framework developed by Google at [github repository](gitlink).
The Blockly framework is distributed under the Apache 2.0 license.
