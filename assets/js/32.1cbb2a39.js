(window.webpackJsonp=window.webpackJsonp||[]).push([[32],{397:function(e,t,a){"use strict";a.r(t);var n=a(42),s=Object(n.a)({},(function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[a("h1",{attrs:{id:"continuous-deployment"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#continuous-deployment"}},[e._v("#")]),e._v(" Continuous deployment")]),e._v(" "),a("p",[e._v("Code - including documentation - is automatically transformed to deployed\nartifacts.")]),e._v(" "),a("ol",[a("li",[e._v("Develop code - including docs -")]),e._v(" "),a("li",[e._v("Lint code. Incorrect? Go to 1")]),e._v(" "),a("li",[e._v("Build code and generate artifacts.")]),e._v(" "),a("li",[e._v("Test artifacts. Incorrect? Go to 1.")]),e._v(" "),a("li",[e._v("Deploy artifacts to the web.")])]),e._v(" "),a("p",[e._v("Node versions are annoying.")]),e._v(" "),a("p",[e._v("Please not that apart from 1. a script automates all the tasks.")]),e._v(" "),a("h2",{attrs:{id:"continuous-integration"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#continuous-integration"}},[e._v("#")]),e._v(" Continuous integration")]),e._v(" "),a("p",[e._v("Code - including documentation - that satisfies the quality requirements gets\ncommitted. Thanks to git hooks implemented in python, we can run quality tools\nlike scc, markdownlint and vale and disallow commits that don't follow the\nquality standard.")]),e._v(" "),a("p",[e._v("Code checker runs gradle shadowJar, gradle clean, and scc. Doc checker runs\nmarkdownlint, markdown-link-checker and vale.")]),e._v(" "),a("p",[e._v("The outputs are:")]),e._v(" "),a("ul",[a("li",[e._v("m.jar containing all java .classes and nothing else")]),e._v(" "),a("li",[e._v("m-web.html containing all block definitions and toolboxes and a reference to\n"),a("code",[e._v("Blockly.js")])]),e._v(" "),a("li",[e._v("m.AppImage: The theia based application built with electron.")])]),e._v(" "),a("h2",{attrs:{id:"staging"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#staging"}},[e._v("#")]),e._v(" Staging")]),e._v(" "),a("h3",{attrs:{id:"testing-m-jar"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#testing-m-jar"}},[e._v("#")]),e._v(" Testing m.jar")]),e._v(" "),a("p",[e._v("A custom script checks that all the code blocks in the documentation work as\nexpected. It goes through the markdown pages and gathers code blocks. Then runs\nthe m-compiler.jar with generated files to check that the console output is as\nexpected.")]),e._v(" "),a("h3",{attrs:{id:"testing-m-html"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#testing-m-html"}},[e._v("#")]),e._v(" Testing m.html")]),e._v(" "),a("p",[e._v("A selenium script checks that all the block-based code blocks in the\ndocumentation work as expected. It goes through the markdown pages and gathers\nblock-based code blocks. Then runs selenium with m.html, inputs the code block\nand checks that there are no errors.")]),e._v(" "),a("h3",{attrs:{id:"testing-m-appimage"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#testing-m-appimage"}},[e._v("#")]),e._v(" Testing m.AppImage")]),e._v(" "),a("p",[e._v("A selenium script checks that all the text-based code blocks in the\ndocumentation work as expected. It goes through the markdown pages and gathers\ntext-based code blocks. Then runs selenium with m.AppImage electron application,\ninputs the code block and checks that there are no errors.")]),e._v(" "),a("h2",{attrs:{id:"deployment"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#deployment"}},[e._v("#")]),e._v(" Deployment")]),e._v(" "),a("p",[e._v("Upload m.jar, m.html and m.AppImage to a Github release. Host m.html using\ngithub-pages. Host browser version of m.AppImage in Glitch.")]),e._v(" "),a("h2",{attrs:{id:"publish"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#publish"}},[e._v("#")]),e._v(" Publish")]),e._v(" "),a("p",[e._v("Twurl posts a tweet with the last commit message. Right now the secrets are in\nthe .twurlrc file. Github secrets contains the tokens for accessing the twitter\napp.")]),e._v(" "),a("h2",{attrs:{id:"new-cycle"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#new-cycle"}},[e._v("#")]),e._v(" New cycle")]),e._v(" "),a("p",[e._v("Development consists of 8 steps, where each step has a manual side and an\nautomated side.")]),e._v(" "),a("p",[e._v("We can divide these 8 steps into two groups of 3 and 2 linking them giving as a\nfamiliar dev ops cycle:")]),e._v(" "),a("ul",[a("li",[e._v("Coding\n"),a("ul",[a("li",[e._v("Code / Build")]),e._v(" "),a("li",[e._v("Refactor / Lint")]),e._v(" "),a("li",[e._v("Document / Test")])])]),e._v(" "),a("li",[e._v("Coding -> Production\n"),a("ul",[a("li",[e._v("Generate artifacts / Release")])])]),e._v(" "),a("li",[e._v("Production\n"),a("ul",[a("li",[e._v("Review / Deploy")]),e._v(" "),a("li",[e._v("Write changelog / Publish")]),e._v(" "),a("li",[e._v("Write issues / Feedback")])])]),e._v(" "),a("li",[e._v("Production -> Coding\n"),a("ul",[a("li",[e._v("Create milestone / Plan")])])])]),e._v(" "),a("p",[e._v("Every step has a corresponding github action file under .github/workflows that\nruns on a specific event.")]),e._v(" "),a("p",[e._v("For example, Code is the process of creating source code whereas Build is the\nprocess of automatically compiling that code. Document is the process of\nexplaining why the developer wrote that code that way and Test is the process of\nautomatically testing that what the programmer expected and wrote in the\ndocumentation is precisely what happens when executed.")])])}),[],!1,null,null,null);t.default=s.exports}}]);