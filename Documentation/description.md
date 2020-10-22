M is a new programming language, designed for game developers.

It increases the productivity of programmers and designers by complementing their workflow(s)
with a simple and flexible tool.

It is (a) free and open source project/software, available for preview/-as a preview- on all major desktop platforms.
/
It is a free and open source project in active development.
A prototype is available for all major desktop platforms.

## M complements your workflow

It does not aim to replace existing tools, but complement them.

### Editor of your choice

M is distributed/delivered/available as a language server. (an independent process...)

(Modern) code editors can communicate with it via the language server protocol 
(to provide a rich development experience)

It can be integrated in code editors that implement the LSP* protocol (such as VSCode, Eclipse or Vim).

This means that programmers can use their editor of choice to develop in M, such as VSCode or Vim. (any uptodate code editor like vscode or vim)

### Engine of your choice

The language server detects source files written in M and generates (a configurable) output.

However, the output is not an executable program, but source code that games engines (can) understand.






### Simplicity (first)

The goal is to make writing and specially reading code simple.

It starts with a familiar syntax, it inherits the syntax from C-like languages.
 
 ### Minimal grammar

And defines a minimalist grammar.
The grammar is minimalist,  specially when compared to Java or C++.

Programs are written/composed (by) combining few orthogonal building blocks.

This defines a clear path for writing the solution to (programming) problems.
Keeps programmers focused on the problem rather than designing/deciding on how to solve it.

This is possible thanks to a game oriented standard library that includes many components and functions related to game dev and the implicit type inference.

### Type inference

It supports entity component system architecture. Instead of working with classes and methods you work with systems and components.

It also leads to code thats easier to maintain. Since everyone understands the building blocks and the boilerplate free grammar, reading a program written in M is easier.

Don't get fooled by its simplicity. We make a big effort to make the language server itself efficient and to output souce code that is performant: Multithread ready and cache friendly.

### Flexible

### Data oriented composition over inheritance

composition over inheritance lets designers create new entities with new behaviour by mixing 
M enforces data oriented design natively.

In particular, it follows the Entity Component System paradigm to decouple
the game into independent systems describing the logic and components
that hold all the data.

### No literals

More importantly, M improves the flexibility of game designers.

M has no literal values.
All the data is stored in components,
which means that designers can tweak all the parameters from within the game editor.

With M, programming and designing become independent jobs,
providing an important boost in team productivity.

### The author

My name is Martin. I'm 28. I started programming when I was 14 and haven't stopped since.

I love learning. I graduated in Maths and Software Engineering in Barcelona and then went to Manchester for a masters in Mathematical logic. I (thoroughly) enjoyed my studies (a lot). Still, I think I have learned more thanks to my personal projects.

I have always had a project going, from small games to programs to assist in math. I have finally found my passion in developing software tools. Programs that allow developers, including myself, be more productive.

After starting developing M, I decided to focus on it seriously.

I rejected a PhD offer from Manchester University, moved back to my parents' home and focus on developing my project.

Thanks to some savings, a modest lifestyle and invaluable help from my parents, I have been able to keep developing M.

And after three years of hard work I'm excited to share a prototype with the world and apply for this grant. I am a big proponent/fan of free software.  

When I first heard of Epic Megagrants I thought it was only for big enterprises, but some months ago I saw some individuals got grants too.

I will be honest. Now my savings are gone and soon I will start applying for developer jobs. I am ready to try it, but that will mean leaving M as a secondary project.

Having the goal of applying for this grant has pushed me into polishing the poject into a presentable state.

### Possible impact

This project has been extremely ambitious since its inception.

In the best case scenario, it could make an impact in the professional lives of millions of game developers. Making them more productive and hopefully enjoy their work even more.
I want to clarify that I don't envision anyone dropping C#, C++ or GD for M, but use them together as part of the interdisciplinary game development teams.

In the worst case, It would go unnoticed and be forgotten. It could happen for not eecuting the idea properly, lack of interest or poor marketing. But I believe in this project. Even in the worst case, it would have been my biggest achievement to date.

### Current status

M is a free and open source project (hosted in Github./(still) in active development).

A prototype for all major operating systems is (publicly) available for download.

A prototype exists in the form of an extension vscode based editors: Visual Studio Code and VSCodium.

### Future plan

I track the progress and pending tasks of the project using Github's issue tracker.

The planning is publicly available and classifies the tasks depending on how they add/ the value they add to the project.

- Development tasks add new features
- Documentation tasks make the project easier to learn
- Maintenance: Easier to develop
- User experience: Easier to use
- Social: Easier to find and try out

To give an important example

The ultimate goal is to keep dedicating hours to the development of the tool, on whatever becomes prioritary. And steadily make the prject a robust and complete solution that developers/programmers can rely on.

