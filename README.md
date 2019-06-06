[logo]: https://github.com/martin-azpillaga/M/blob/master/Source/games.minim.parent/games.minim.product/icons/app128.png "Logo"
[visualEntityEnglish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Visual_English.PNG
[visualSystemEnglish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Visual_System.PNG
[textualEntityEnglish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Textual_English.PNG
[textualSystemEnglish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/TextualSystem_English.PNG
[textualEntitySpanish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Textual_Spanish.PNG
[visualEntitySpanish]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Visual_Spanish.PNG
[pacman]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Pacman.PNG
[pong]: https://github.com/martin-azpillaga/M/blob/master/Documentation/Pong.PNG
[spaceInvaders]: https://github.com/martin-azpillaga/M/blob/master/Documentation/SpaceInvaders.PNG
[eclipseLogo]: https://www.macupdate.com/images/icons256/11662.png
[vscodeLogo]: https://cf.mastohost.com/v1/AUTH_91eb37814936490c95da7b85993cc2ff/fosstodon/custom_emojis/images/000/046/192/static/33ed6562f1f97c3a.png

![M logo][logo]
# The language of video games
M is a programming language built specifically for game development. It embraces the Entity Component System paradigm to facilitate the creation of flexible yet performant projects. It is built to make game development more enjoyable, by making the developers more productive and simplifying the process following the philosophy



> Everything should be as simple as it can be, but not simpler.

>-[Unknown](https://quoteinvestigator.com/2011/05/13/einstein-simple/)

### M is for everyone
M tries to involve all the technical roles in development: Artists - drawers, modelers, , programmers, designers

Textual programming | Visual programming
--- | ---
![Textual programming][textualEntityEnglish] | ![VisualProgramming][visualEntityEnglish]
![Textual system][textualSystemEnglish] | ![VisualSystem][visualSystemEnglish]
Programmers will feel at home with the C-like syntax | Artists and designers can also contribute by expressing their ideas using Scratch-like blocks
Faster to develop with | Easier to learn and use

Both approaches have the same expressive power, meaning that any program made using an approach can b emade using the other. Moreover, programs can be automatically translated from an interface to the other to approach every problem in the desired form.

### Samples

This repository includes three sample games made exclusively with the M language. They are well known arcade games, used to demonstrate the methodology behind M.

Pong | SpaceInvaders | Pacman
--- | --- | ---
![Pong][pong] | ![Space Invaders][spaceInvaders] | ![Pacman][pacman]
&copy; Atari Inc. | &copy; Taito corporation | &copy; Namco Limited
[Link to the code](https://github.com/martin-azpillaga/M/blob/master/Samples/Pong/Main.m) | [Link to the code](https://github.com/martin-azpillaga/M/blob/master/Samples/SpaceInvaders/Main.m) | [Link to the code](https://github.com/martin-azpillaga/M/blob/master/Samples/Pacman/Main.m)
### Features
___
* Natural language agnostic

The compilation process of M is independent of the natural language vocabulary which allows to easily support additional natural languages.

English | Spanish
--- | ---
![English code][textualEntityEnglish] | ![Spanish code][textualEntitySpanish]

___

* Editor agnostic

Programs in the M language can be develop in any editor that supports the LSP protocol.

Eclipse | Visual studio code
--- | ---

___

* Game engine agnostic

Similarly, the code generation process is completely orthogonal from the language parsing and processing, so new target game engines can be added like

Unity | Unreal | Godot
--- | --- | ---

### Collaboration
___
Collaboration is highly appreciated.
##### This project is in active development
Visit the [projects page](https://github.com/martin-azpillaga/M/projects) for updated information on current progress.
##### Common extension points

If you are willing to 
* add support for a new natural language
* help in the integration of the LSP server into your editor of choice or 
* write the code generation process for a new engine

Don't hesitate to contact me.
