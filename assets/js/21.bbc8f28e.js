(window.webpackJsonp=window.webpackJsonp||[]).push([[21],{397:function(e,t,a){"use strict";a.r(t);var n=a(42),s=Object(n.a)({},(function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[a("h1",{attrs:{id:"pong"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#pong"}},[e._v("#")]),e._v(" Pong")]),e._v(" "),a("p",[e._v("In this document you will create a clone of the classic game of Pong by Atari.\nYou will learn the basics of rendering entities and moving them through physical\nproperties like mass and restitution. Then, you will be able to detect\ncollisions and add gameplay features accordingly.")]),e._v(" "),a("h2",{attrs:{id:"five-influential-games"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#five-influential-games"}},[e._v("#")]),e._v(" Five influential games")]),e._v(" "),a("p",[e._v("As example games we have selected some of the most influential, iconic and\nsuccessful games in their era. Small in scope but still interesting enough to\nillustrate the different aspects the language M is capable of.")]),e._v(" "),a("p",[e._v("You can start with the classic game of "),a("RouterLink",{attrs:{to:"/user-guide/guided-examples/0. Pong.html"}},[e._v("Pong")]),e._v(", developed by Atari in 1972, where\nyou will learn the basics of entity and system creation and physics\ninteractions.")],1),e._v(" "),a("p",[e._v("Next is the "),a("RouterLink",{attrs:{to:"/user-guide/guided-examples/1. Space invaders.html"}},[e._v("Space Invaders")]),e._v(" game, developed by Tomohiro Nishikado and\ndistributed by Taito in 1978. You will learn about batch creation of entities,\ncreation and destruction of entities in runtime.")],1),e._v(" "),a("p",[e._v("For the 80's era we have chosen the well known "),a("RouterLink",{attrs:{to:"/user-guide/guided-examples/2. Pacman.html"}},[e._v("Pacman")]),e._v(" and "),a("RouterLink",{attrs:{to:"/user-guide/guided-examples/3. Mario.html"}},[e._v("Mario")]),e._v(". In Pacman\nyou can learn how to use reinforcement learning AI techniques and in Mario how\nto design diverse levels.")],1),e._v(" "),a("p",[e._v("For the 90's we enter the era of 3D graphics. To showcase this ability, we have\ncreated a demo of "),a("RouterLink",{attrs:{to:"/user-guide/guided-examples/4. Crash Bandicoot.html"}},[e._v("Crash Bandicoot")]),e._v(", developed by Naughty Dog, that combines all\nthe concepts in a three dimensional game.")],1),e._v(" "),a("h2",{attrs:{id:"prerequisites"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#prerequisites"}},[e._v("#")]),e._v(" Prerequisites")]),e._v(" "),a("p",[e._v("Before starting this tutorial we recommend checking the [rendering] and\n[physics] tutorials.")]),e._v(" "),a("h2",{attrs:{id:"final-result"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#final-result"}},[e._v("#")]),e._v(" Final result")]),e._v(" "),a("p",[e._v("You can download the resulting project in each interface here:")]),e._v(" "),a("ul",[a("li",[e._v("[English text]")]),e._v(" "),a("li",[e._v("[Euskara]")]),e._v(" "),a("li",[e._v("[Blockly blocks]")]),e._v(" "),a("li",[e._v("[Unity zip]")])]),e._v(" "),a("h3",{attrs:{id:"let-s-start"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#let-s-start"}},[e._v("#")]),e._v(" Let's start")]),e._v(" "),a("p",[e._v("As discussed in [key concepts], the entities store all the data of the game.\nThis includes visual entities such as the ball but also non visual elements like\nthe game rules.")]),e._v(" "),a("p",[e._v("First, we will identify all the entities in Pong. We have the ball, the two\npaddles that the players can control, the top and bottom wall, the two goals,\nthe score counters and an extra entity for holding the game rules.")]),e._v(" "),a("p",[e._v("Some of these entities have a lot of data in common. For example, the top and\nbottom wall differ in their position, but their scale and physical properties\nare the same. Instead of creating all entities from scratch, we will define a\nprefab and create two variants of that prefab as our final walls.")]),e._v(" "),a("p",[e._v("What data do we need to define a wall? This depends on the abstraction we make.\nIn this example, we have decided that we will need a position, scale and a\nsprite. Since the position changes in the instances we will add the common\nelements:")]),e._v(" "),a("p",[e._v("We will start by defining the visual properties of the entities. Then, continue\nwith the physical ones.")]),e._v(" "),a("div",{staticClass:"language-text extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v('    "wall"\n    {\n        scale 100 2, sprite "wall"\n    }\n')])])]),a("p",[e._v("Now our walls will be visible. To interact with the walls, we will need to add\nphysical properties to them. We will add a box collider to detect collisions\nwith other physical bodies and avoid penetrations and a restitution value of 1\nto make the collision bouncy.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    wall has\n    {\n        scale 100 2, sprite wall,\n        extent 1 1, restitution 1\n    }\n")])])]),a("p",[e._v("Notice that the size of the box collider is relative to the scale of the entity.")]),e._v(" "),a("p",[e._v("Let us continue with the ball.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    ball has\n    {\n        scale 2 2, sprite ball,\n        radius 0.5, restitution 1\n    }\n")])])]),a("p",[e._v("It's like a wall, except it uses a circle collider with a radius that fits its\nscale. The ball takes part in more interactions, and as such we must add more\ndata to it.")]),e._v(" "),a("p",[e._v("Tag components are useful to mark entities that take part in certain\ninteractions. We can distinguish if a system will process an entity by looking\nwhether it contains a tag or not. In pong, we want the ball to be part of the\nserving, scoring and spawning interactions. For simplicity, we will add a tag\nfor each of those interactions.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    ball has\n    {\n        scale 2 2, sprite ball,\n        radius 0.5, restitution 1,\n        serveTag, scoreTag, spawnTag,\n        spawnPosition 0 1\n    }\n")])])]),a("p",[e._v("Ending the components with the word Tag is not necessary, it's a naming\nconvention. The particular thing about tags is that they don't carry any data,\nmaking them specially efficient to filter.")]),e._v(" "),a("p",[e._v("For the paddle, we need to include some sort of input controls to control its\nmovement. We will create a component that holds the name of the input that will\ncontrol the paddle, in this case, the left stick Y axis of the gamepad. Also,\nthe direction component will control in which direction moves this paddle, its\nmodulus being the speed.")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("paddle has\n{\n    scale 2 5, sprite paddle,\n    extent 1 1, restitution 1,\n    motion gamepad left Y, direction 0 10\n}\n")])])]),a("p",[e._v("Make sure that the code compiles and you can open the project in the game\nengine. You should see the entity prefabs under the Design/Prefabs/ folder. Next\nwe will add our first system for interaction:")]),e._v(" "),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    control\n    {\n        for all entity paddle\n        {\n            paddle.velocity = paddle.direction * paddle.motionValue\n        }\n    }\n")])])]),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("spawn\n{\n    for all entity collision with enterCollision\n    {\n        if has(collision.first, spawnTag)\n        {\n            collision.first.position = collision.first.spawnPosition\n        }\n    }\n}\n")])])]),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    serve\n    {\n        for all entity ball with serveTag\n        {\n            angle = random(ball.angleRange)\n\n            ball.velocity = join(cos(angle),sin(angle)) * random(ball.lengthRange)\n        }\n    }\n")])])]),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    score\n    {\n        for all entity collision with enterCollision\n        {\n            if has(collision.first, scoreTag)\n            {\n                for all entity board\n                {\n                    if board.team = collision.second.team\n                    {\n                        board.score += collision.second.worth\n                    }\n                }\n            }\n        }\n    }\n")])])]),a("div",{staticClass:"language-m extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("    restart\n    {\n        for all entity rules\n        {\n            for all entity board\n            {\n                if board.score >= rules.maximumScore\n                {\n                    count++\n                }\n            }\n\n            if count >= rules.minimumBoardsOverScore\n            {\n                for all entity any\n                {\n                    destroy(any)\n                }\n\n                create(rules.scene)\n            }\n        }\n    }\n")])])]),a("h3",{attrs:{id:"new-way"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#new-way"}},[e._v("#")]),e._v(" New way")]),e._v(" "),a("div",{staticClass:"language-{.set .pong .main.m} extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("control ≔\n[\n    ∀ a\n    [\n        a.velocity ≔ a.baseVelocity × a.inputValue\n    ]\n]\n\nserve ≔\n[\n    ∀ a | has[serveSignal, a]\n    [\n        α ≔ random[a.serveAngle]\n        l ≔ random[a.serveLength] + a.z\n        a.velocity ≔ [cos[α], sin[α], a.z] × l\n        remove[serveSignal, a]\n    ]\n]\n\nrespawn ≔\n[\n    ∀ a | has[respawnApplier, a]\n    [\n        ∀ b | b ∈ a.collisions\n        [\n            b.position ≔ b.respawnPosition\n            add[serveSignal, b]\n        ]\n    ]\n]\n\nscore ≔\n[\n    ∀ a | has[scoreApplier, a]\n    [\n        ∀ b | b ∈ a.collisions\n        [\n            b.number ≔ b.number + b.worth\n        ]\n    ]\n]\n\nrestart ≔\n[\n    A ≔ { z | z.number > z.maxNumber }\n    ∀ a | #A > a.requiredBoardsToReset\n    [\n        ∀ b\n        [\n            b.number ≔ b.resetNumber\n        ]\n    ]\n]\n")])])]),a("h3",{attrs:{id:"what-s-next"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#what-s-next"}},[e._v("#")]),e._v(" What's next")]),e._v(" "),a("p",[e._v("Offer little exercises or the next tutorial.")]),e._v(" "),a("h3",{attrs:{id:"have-questions"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#have-questions"}},[e._v("#")]),e._v(" Have questions")]),e._v(" "),a("p",[e._v("Post them somewhere, open an issue?")])])}),[],!1,null,null,null);t.default=s.exports}}]);