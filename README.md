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

## Test

* Create and open file main.m
* Append to main.m

~~~ m
control()
{
  foreach a
  {
    a.velocity = a.baseVelocity * a.inputValue
  }
}
~~~

* Expect new files in src-gen/Unity/Assets/Code: Components/baseVelocity.cs, Systems/control.cs
* End
