(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{385:function(e,t,a){"use strict";a.r(t);var r=a(42),s=Object(r.a)({},(function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[a("h1",{attrs:{id:"your-first-game"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#your-first-game"}},[e._v("#")]),e._v(" Your first game")]),e._v(" "),a("p",[e._v("M lets you write the logic of your game independently of the design and art of\nthe game.\nYou can write your game mechanics and then the designers will mix the mechanics\nthey like with the arts the artists provide.")]),e._v(" "),a("p",[e._v("Shoot them up. We will use an iterative approach were we keep adding features\none by one. A feature includes data and interaction, so we must edit the\nentities and systems that exist in the world to add a new feature.")]),e._v(" "),a("p",[e._v("But first, let's extract all the features we desire in the core game loop of our\ngame.")]),e._v(" "),a("h2",{attrs:{id:"feature-extraction"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#feature-extraction"}},[e._v("#")]),e._v(" Feature extraction")]),e._v(" "),a("ul",[a("li",[e._v("Control the character.")]),e._v(" "),a("li",[e._v("Create hazards over time.")]),e._v(" "),a("li",[e._v("Move hazards.")]),e._v(" "),a("li",[e._v("Destroy the character when a hazard hits it.")]),e._v(" "),a("li",[e._v("Clear the hazards when they leave the screen.")]),e._v(" "),a("li",[e._v("Shoot projectiles.")]),e._v(" "),a("li",[e._v("Destroy hazards using projectiles.")])]),e._v(" "),a("p",[e._v("Note that the words used are consciously vague, as the game won't have any art\ndirection for now. The character could be a car, a spaceship, a superhero or a\ndolphin. The hazards could be asteroids, tanks or chickens.")]),e._v(" "),a("h2",{attrs:{id:"create-a-folder"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#create-a-folder"}},[e._v("#")]),e._v(" Create a folder")]),e._v(" "),a("p",[e._v("Do it with the keyboard:")]),e._v(" "),a("ul",[a("li",[e._v("Press Control+Shift+P")]),e._v(" "),a("li",[e._v("Type new file")]),e._v(" "),a("li",[e._v("Press Enter")]),e._v(" "),a("li",[e._v("Type Hello.m")]),e._v(" "),a("li",[e._v("Press Enter")]),e._v(" "),a("li",[e._v("Type this")])]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("helloWorld(){hi}\nhi\n")])])]),a("ul",[a("li",[e._v("Press Enter")])]),e._v(" "),a("p",[e._v("Paragraph")]),e._v(" "),a("ul",[a("li",[e._v("Bullet")])]),e._v(" "),a("h2",{attrs:{id:"create-a-file"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#create-a-file"}},[e._v("#")]),e._v(" Create a file")]),e._v(" "),a("p",[e._v("We will code all the mechanics in a single file")]),e._v(" "),a("div",{staticClass:"language-{.create .first .main.text} extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("Click file\nClick new file\nType main.m\nClick ok\n")])])]),a("h2",{attrs:{id:"controlling-the-character"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#controlling-the-character"}},[e._v("#")]),e._v(" Controlling the character")]),e._v(" "),a("p",[e._v("Linear motion proportional to the input read.")]),e._v(" "),a("div",{staticClass:"language-{.append .first .main.text} extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("control\n{\n    foreach a\n    {\n        a.velocity = a.direction * a.speed\n    }\n}\n")])])])])}),[],!1,null,null,null);t.default=s.exports}}]);