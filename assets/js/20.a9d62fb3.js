(window.webpackJsonp=window.webpackJsonp||[]).push([[20],{391:function(n,e,a){"use strict";a.r(e);var t=a(42),o=Object(t.a)({},(function(){var n=this.$createElement,e=this._self._c||n;return e("ContentSlotsDistributor",{attrs:{"slot-key":this.$parent.slotKey}},[e("h1",{attrs:{id:"space-invaders"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#space-invaders"}},[this._v("#")]),this._v(" Space Invaders")]),this._v(" "),e("div",{staticClass:"language-m extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[this._v("    ship has\n    scale 5 2 appearance sprite.ship\n    extent 1 1 category [ship]\n    motorAxis gamepad.leftX motorSpeed 10 0 shootButton gamepad.south\n    deathSensor detect [alien shipProjectile] goodness\n    gunCreation entity.bullet creationVelocity 0 30 gunOffset 0 1.6\n    velocity 0 0.\n\n    alien has\n    scale 9 4 appearance sprite.alien\n    extent 1 1 category [alien]\n    mass 1\n    deathSensor detect [ship space shipProjectile] hiveSensor detect [wall]\n    flipSensor detect [wall] gunCreation entity.bullet creationVelocity 0 -30\n    gunOffset 0 -2.6\n    worth 10\n    evilness hive gun\n    relegation 0\n    promotion 1.\n\n    ufo has scale 5 2 appearance sprite.ufo\n    mass 1 extent 1 1 category [alien]\n    deathSensor detect [shipProjectile] scapeSensor detect [wall] worth 500.\n\n    bullet has\n    scale 1 1 appearance sprite.bullet\n    extent 1 1 deathSensor detect [alien shipProjectile evilProjectile wall\n    space defense]\n    team [single]\n    category [shipProjectile] mass 1.\n\n    goodBullet based on bullet has\n    category [shipProjectile].\n\n    evilBullet based on bullet has\n    category [evilProjectile].\n\n    space has\n    scale 100 4 appearance sprite.space\n    extent 1 1 category [space].\n\n    wall has\n    scale 4 100 appearance sprite.wall\n    extent 1 1 category [wall].\n\n    defense has\n    scale 11 11 appearance sprite.defense\n    extent 1 1  trigger 1 category [defense]\n    deathSensor detect [shipProjectile evilProjectile alien].\n\n    hiveMind has\n    requiredEntities 1\n    requiredGoodness 1\n    scene entity.menu\n    autoSpeed 5 0\n    autoSpeedRatio 1.1\n    flipFactor -1\n    gunTimer 1s\n    gunTimerRatio 0.9\n    probabilityRange 0 1\n    probability 0.5\n    ufoTimer 10s\n    bonus entity.ufo\n    bonusSpawnPoint -40 40\n    bonusVelocity 10 0.\n\n    board has\n    scale 5 10,\n    team [single], number 0.\n\n    audioEffect has\n    audioClip audio.yay, lifetime 5s.\n\n    player has rotation 0 0 10,\n    viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.5 0.5 0.5 1.\n\n    hud has\n    canvas 100 100 contains\n        score based on board has position -40 40.\n    .\n\n    menu contains\n        hud has canvas 100 100 contains\n            title has position 0 10, scale 100 10, text 'Space_Invaders'.\n            subtitle has position 0 -20, scale 100 5, text 'Press_Start'.\n        .\n        trigger has detector gamepad.start, scene entity.playground.\n        player based on player.\n    .\n\nplayground contains\n    ship based on ship has position 0 -40.\n    topSpace based on space has position 0 50.\n    bottomSpace based on space has position 0 -50.\n    leftWall based on wall has position -50 0.\n    rightWall based on wall has position 50 0.\n\n    alien based on alien has position 0 0 relegation 0 promotion -1.\n    alienB based on alien has position -20 0 relegation 1 promotion -1.\n    alienC based on alien has position -40 0 relegation 2 promotion -1.\n    alienD based on alien has position 20 0 relegation 3 promotion -1.\n    alienE based on alien has position 40 0 relegation 4 promotion -1.\n\n    alienFather based on alien without gun has position 0 10 relegation 5\n    promotion 0.\n    alienBFather based on alien without gun has position -20 10 relegation 6\n    promotion 1.\n    alienCFather based on alien without gun has position -40 10 relegation 7\n    promotion 2.\n    alienDFather based on alien without gun has position 20 10 relegation 8\n    promotion 3.\n    alienEFather based on alien without gun has position 40 10 relegation 9\n    promotion 4.\n\n    alienGrandpa based on alien without gun has position 0 20 relegation 10\n    promotion 5.\n    alienBGrandpa based on alien without gun has position -20 20 relegation 11\n     promotion 6.\n    alienCGrandpa based on alien without gun has position -40 20 relegation 12\n     promotion 7.\n    alienDGrandpa based on alien without gun has position 20 20 relegation 13\n     promotion 8.\n    alienEGrandpa based on alien without gun has position 40 20 relegation 14\n     promotion 9.\n\n    hiveMind based on hiveMind.\n\n    defenseA based on defense has position -33 -20.\n    defenseB based on defense has position -11 -20.\n    defenseC based on defense has position 11 -20.\n    defenseD based on defense has position 33 -20.\n\n    player based on player.\n    canvas based on hud.\n    .\n")])])])])}),[],!1,null,null,null);e.default=o.exports}}]);