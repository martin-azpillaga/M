M is a new programming language, designed for game developers.

It increases the productivity of programmers and designers by complementing their workflows with a simple and flexible tool.

It's free and open source software, available -as a prototype- on all major desktop platforms.

## M complements your workflow

### Pick your editor

M is distributed as a language server.

It can be integrated in any code editor that implements the language server protocol, such as vscode or vim.

It extends the editor to support .Ⲙ files, with language features like signature helpers and code completions.

With M, all programmers can enjoy a rich development experience, right in their editor of choice.

### Pick your engine

The output of a program written in M is not an executable binary.

M generates source code files that game engines understand.

It can write readable C# classes for Unity, C++ code for Unreal and GD scripts for Godot.

The idea is to generate code with M and use the engine of choice to integrate it with the rest of the game.

### Add M to your workflow

As every good tool, M solves one problem and solves it well:

Developing performant gameplay code in a simple and maintainable manner.

No engine code, no game design, no art. M focuses on creating quality gameplay code.

When combined with powerful code editors and game engines, it enables productive game development environments.

Let me explain how.



## M is simple

The goal is to make writing and specially reading code an efficient process.

### Minimalist grammar

M has a familiar syntax, inherited from C-like languages.

But it defines a minimal set of grammar rules, specially when compared to Java or C++.

Programs are written by combining few, orthogonal building blocks.

This defines a clear path for expressing the solution to programming problems.

It keeps programmers focused on the problem rather than deciding on how to solve it, reducing the complexity.

### Implicit type inference

M has a powerful type system.

It's capable of deducing the type of all expressions efficiently.

It frees programmers from specifying types while offering helpful error messages when types don't check.

This is possible thanks to a deduction algorithm based on M's game oriented standard library.

The result is concise, clear and overall less complex code.

### Performance by default

From simple code, M generates performant gameplay code.

It stores the data in contiguous memory for cache-friendly access.

It analyzes the game logic for dependencies and outputs multithread ready jobs.

Developers can inspect the generated code and squeeze more performance by overriding specific blocks of code.

This reduces the presence of performance tricks in the code, improving readability and reducing the complexity.



## M is Flexible

### ECS architecture

M natively supports the entity component system paradigm.

It defines a clear separation between data and logic.

Data is stored in components while systems define the logic that transforms this data.

Game entities are created by mixing components together.

The result is a flexible playground for designers.

They can define entities with new behavior by combining different sets of components.

This enables the design of different games from the same codebase, without intervention of programmers.

### Data-driven behavior

M has no literal expressions.

It accepts no magic numbers nor hardcoded values.

All data is stored in components and exposed in the editor.

This way, designers can tweak all the parameters to their taste from within the game editor.


With M, programming and designing become independent jobs, providing an important boost to team productivity.



## Possible impact

This project has been extremely ambitious since its inception.

In the best case scenario, it can make an impact in the professional life of millions of game developers. Making them more productive and hopefully happier. I want to clarify that I don't envision anyone dropping C#, C++ or GDScript for M. Rather, I imagine developers using them together, as part of the interdisciplinary game development process.

In the worst case, it would go unnoticed and be forgotten. I acknowledge three main risks that could lead to this scenario: Not executing the idea properly, lack of interest and poor marketing. But, I believe in this project.


## The author

My name is Martin. I'm 28. I started programming when I was 14 and haven't stopped since.

I love learning. I graduated in Math and Software Engineering in Barcelona and then moved to Manchester for a masters in Mathematical logic. I thoroughly enjoyed my studies. Still, I think I've learned more thanks to my personal projects.

I've always had a project or two going, from small games to programs to assist in math. I have finally found my passion in developing software tools. Creating programs that allow developers, including myself, be more productive.

The idea of M came around three years ago, while I was finishing the masters. After experimenting for a while, I saw the potential the project had, but also its ambitious scope.
I rejected my PhD offer from Manchester University, moved back to my parents' home and decided to focus on developing the project.

Thanks to a government grant, a modest lifestyle and invaluable help from my parents, I have been able to keep working on it. And after three years of hard work, I'm excited to share my first prototypes with the world and apply for this grant.

When I first heard of Epic Megagrants I thought it was only for big enterprises, but I recently found some individual megagrant recipients and it filled me with motivation. Having the goal of applying for this grant has pushed me to polish the project into a presentable state and I'm thankful for that.

Receiving this grant would mean a lot to me. The financial support and the approval from an industry giant would allow me to continue working full time in the project, add missing features and dedicate time to promote the project within the game developer audience.



### Current status

M is an open source project hosted on Github.

It's in active development with over 5000 contributions this year.

A prototype is available in the form of an extension for vscode based editors.

The prototype supports the following main features:

- Editor integration: Automatic detection and synchronization of M projects
- Code validation: Syntax, scoping and type error checks
- Code generation: Debug and high performance C# code generation for Unity.
- Language services: Hover information, function signature helpers, code completions and diagnostic reports

The next big feature I'm working on is C++ code generation for Unreal. Simplified generation has been successful, now I'm getting more familiar with both C++ and Unreal before starting with high performance code generation.

### Future plan

I track the progress and pending tasks of the project using Github's issue tracker.

The planning is publicly available and classifies the tasks depending on how they add value to the project.

- Development tasks add new features
- Documentation tasks make the project easier to learn
- Maintenance tasks make the project easier to develop
- User experience tasks make it easier to use
- Social tasks make it easier to find and try out

M is highly experimental and research driven. It has a pubic experimental branch that includes ideas and results of experiments that I have tried in the past and may find their way to the main branch. Noteworthy experimental features include:

- A visual coding interface based on Blockly.js
- Custom code editor based on Theia
- Support for multiple natural languages
- Efficient incremental parsing using Tree-sitter
- Headless language client for automated testing

The ultimate goal is to keep dedicating hours to the project and steadily make a robust and complete solution that game developers can rely on.
