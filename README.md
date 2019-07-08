# M

| [English version](/Documentation/English/Home.md) |
  [Versión en castellano](/Documentation/Castellano/Inicio.md) |
  [Euskarazko bertsioa](/Documentation/Euskara/Hasiera.md) |

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

* **M integrates with your workflow**

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
        ball has mass 10 position 1 2.
    ```

4. Save the file. Congratulations, you can find the generated project inside the Hybrid Unity folder -might require a refresh-.

Optionally, you might want to try the block based
[online editor](/Visual-editor/).

1. Hit the load button and open the generated `HelloM/Blocks/main.blocks` file.  

2. Try adding a component named friction with value 10 using the Numeric menu.

3. Hit the save button and override the `HelloM/Blocks/main.blocks` with the downloaded file.

4. Save the file. Both the Text and Hybrid Unity interfaces will synchronize to the new changes.

## Become an expert

You can become an expert in M within a week by reading through its documentation.

Open the
[documentation home page](/Documentation/English/Index.md)
and discover all its benefits with your own hands.

If you are new to data oriented design, it is recommended that first you go through the [key concepts](/Documentation/English/Data-oriented-design.md) and then continue with the specific interface of M.

Additionally, you can get inspiration from the
[example projects](https://github.com/martin-azpillaga/M/tree/gh-pages/Examples)
hosted in this repository.

---

## Project status

The project M has been in active development since mid 2017 with over 4000 hours of research and development as of mid 2019.

The project has gone through [several versions](https://github.com/martin-azpillaga/M/releases) and is slowly but steadily reaching a mature state of stability and feature completeness.

There are still some [hard challenges](https://github.com/martin-azpillaga/M/issues) to overcome and generally a lot of polishment to do before considering the project production ready.

The project started to be hosted in Github since mid 2019, so previous history is not present online. Since then, anyone can stay tuned about the next planned features by checking the
[kanban project](https://github.com/martin-azpillaga/M/projects/1).

## Help make M even better

If you enjoy the project you can help its development by contributing, sharing and donating.

The easiest way to contribute is by creating an issue, whether its a bug report or a feature request in this same repository. You can inspect current issues and create new ones in the
[issues tab](https://github.com/martin-azpillaga/M/issues).

If you are a developer and want to help extending M, request access to the private repository where the source code is hosted by contacting the author <martin@minim.games> and follow the standard pull request workflow.
The full documentation of the internal language and workflow API will be available in [the wiki](https://github.com/martin-azpillaga/MSource/wiki).

Finally, you can help this project succeed by donating any amount using GitHub's built-in sponsorship feature. Just click the sponsor heart button above. All the recaudation will be used exclusively in the development costs of the project.

---

## Authors and licensing

This project has been developed with love by Martin Azpillaga Aldalur, a software tool developer and mathematician. You can find his personal webpage
[here](https://martin-azpillaga.github.io/).

The language implementation would be impossible without the [Xtext framework](https://github.com/eclipse/xtext) developed by the Eclipse foundation.
The Xtext framework is distributed under the [Eclipse Public License 1.0](https://www.eclipse.org/legal/epl-v10.html).

The visual editor uses the [Blockly framework](https://github.com/google/blockly) developed by Google.
The Blockly framework is distributed under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
