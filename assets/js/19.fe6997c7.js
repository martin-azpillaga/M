(window.webpackJsonp=window.webpackJsonp||[]).push([[19],{389:function(n,e,a){"use strict";a.r(e);var t=a(42),i=Object(t.a)({},(function(){var n=this.$createElement,e=this._self._c||n;return e("ContentSlotsDistributor",{attrs:{"slot-key":this.$parent.slotKey}},[e("h1",{attrs:{id:"pacman"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#pacman"}},[this._v("#")]),this._v(" Pacman")]),this._v(" "),e("div",{staticClass:"language-m extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[this._v("    main world contains menu.\n\n    directionChange has\n    button gamepad.left\n    direction 0 0.\n\n    rayCaster has\n    scale 4.5 4.5 casting extent 0.99 0.99 offset 0 0\n    sensor detect [wall]\n    parentRelation 0\n    velocity 0 0.\n\n    wall has\n    scale 5 5 appearance sprite.wall physical category [wall] extent 1 1.\n\n    food has\n    scale 1 1 appearance sprite.food radius 0.5 winRequirement worth 10\n    deathSensor detect [character].\n\n    fruit has\n    scale 3 3 appearance sprite.fruit radius 0.5 worth 100 deathSensor\n    detect [character] lifeTimer 7s.\n\n    rules has\n    spawnTimer 20s\n    creation entity.fruit\n    spawnPosition 0 -5\n    winCondition 0\n    loseCondition 0\n    scene entity.menu.\n\n    powerUp has\n    scale 3 3\n    appearance sprite.powerUp\n    radius 0.5\n    winRequirement\n    worth 50\n    deathSensor detect [character]\n    invincibilityFormula\n    fearTimer 10s.\n\n    character has\n    demoMode\n    demoPowerlessVelocity -5 0\n    demoPowerVelocity 5 0\n    category [character]\n    personality\n    fearAmount 0\n    respawnPoint 0 -25\n    sortingOrder 2\n    wish\n    mass 1\n    physical\n    scale 4.5 4.5\n    appearance sprite.pacman\n    parent 0\n    extent 1 1\n    speed 5\n    teleportFactor -1\n    teleportSensor detect [portal]\n    clashSensor detect [evil]\n    loseRequirement\n    .\n\n    portal has\n    appearance sprite.portal\n    scale 5 5\n    extent 1 1\n    category [portal]\n    physical.\n\n    label has number 0 scale 10 5 combo 0 initialCombo 0 scoreboard.\n\n    lifeImage has image sprite.life scale 3 3 life.\n\n    ghost has\n    demoMode\n    demoFearVelocity 2 0\n    appearance sprite.ghost\n    scale 4.5 4.5\n    sortingOrder 1\n    extent 0.99 0.99\n    category [evil]\n    clashSensor detect [character]\n    autoMove\n    autoSpeed -5 0\n    autoSpeedFactor -1\n    reboundSensor detect [wall]\n    respawnPoint 0 5\n    velocity 0 0\n    teleportFactor -1\n    teleportSensor detect [portal]\n    independenceTime 10s\n    independenceTimeRange 2 5\n    independencePoint 0 15\n    coward\n    fearAppearance sprite.scared\n    fearFrames 7\n    worth 200\n    normalAppearance sprite.ghost\n    .\n\n    audioEffect has\n    audioClip sound, lifeTimer 1s.\n\n    player has\n    viewDistance 10, viewAngle 50, clearColor 0.2 0.2 0.2 1.\n\n\n    hud has\n    canvas 100 100 contains\n        score_board based on label has position -40 45.\n        life_image_1 based on lifeImage has position 35 45.\n        life_image_2 based on lifeImage has position 40 45.\n        life_image_3 based on lifeImage has position 45 45.\n    .\n\n    menu contains\n        hud has canvas 100 100 contains\n            title has position 0 10, scale 100 10, text 'Pacman'.\n            subtitle has position 0 -20, scale 100 5, text 'Play_start'.\n            options has position 0 -40 scale 100 4 text 'Options_select'.\n        .\n        trigger has detector gamepad.start, scene entity.playground.\n        rulesPage has detector gamepad.select, scene entity.rulesPage.\n        player based on player.\n    .\n\n    rulesPage contains\n        hud has canvas 100 100 contains\n            blinkyText has position 10 45 scale 40 5 text 'Blinky'.\n            pinkyText has position 10 35 scale 40 5 text 'Pinky'.\n            inkyText has position 10 25 scale 40 5 text 'Inky'.\n            clydeText has position 10 15 scale 40 5 text 'Clyde'.\n\n            demoScore has position 30 0 scale 10 5 number 0 combo 0.\n            demoText has position 40 0 scale 10 5 text 'pts'.\n\n            foodText has position 10 -15 scale 40 5 text '_10_pts'.\n            powerUpText has position 10 -25 scale 40 5 text '_50_pts'.\n            fruitText has position 10 -35 scale 40 5 text '_100_pts'.\n            copyrightText has position 0 -45 scale 40 5 text\n            'c_1980_MIDWAY_MFG_CO'.\n        .\n\n        blinky has position -30 45 scale 5 5 appearance sprite.blinky.\n        pinky has position -30 35 scale 5 5 appearance sprite.pinky.\n        inky has position -30 25 scale 5 5 appearance sprite.inky.\n        clyde has position -30 15 scale 5 5 appearance sprite.clyde.\n\n        realPowerUp based on powerUp has position -40 0.\n        realPacman based on character has position 0 0.\n        caster based on rayCaster.\n        realBlinky based on ghost without independenceTime has position 10 0\n         appearance sprite.blinky.\n        realPinky based on ghost without independenceTime has position 15 0\n         appearance sprite.pinky.\n        realInky based on ghost without independenceTime has position 20 0\n         appearance sprite.inky.\n        realClyde based on ghost without independenceTime has position 25 0\n         appearance sprite.clyde.\n\n        food has position -15 -15 scale 1 1 appearance sprite.food.\n        powerUp has position -15 -25 scale 4 4 appearance sprite.powerUp.\n        fruit has position -15 -35 scale 3 3 appearance sprite.fruit.\n\n        titlePage has detector gamepad.south, scene entity.menu.\n        player based on player.\n    .\n\n    demo:\n    for all entity a with demoMode\n    {\n        initialize amount\n        for all entity b with fear\n        {\n            amount = amount++\n        }\n\n        if amount = a.fearAmount\n        {\n            a.velocity = a.demoPowerlessVelocity\n        }\n        else\n        {\n            a.velocity = a.demoPowerVelocity\n        }\n    }\n\n    demoFear:\n    for all entity c with fear demoMode\n    {\n        c.autoSpeed = c.demoFearVelocity\n    }\n\n    goIndependent:\n    for all entity a with timed out independenceTime\n    {\n        a.position = a.independencePoint\n        remove (independenceTime, a)\n    }\n\n    move:\n    for all entity a with autoMove\n    {\n        a.velocity = a.autoSpeed\n    }\n\n    rebound:\n    for all entity a with enter reboundSensor\n    {\n        a.autoSpeed *= a.autoSpeedFactor\n    }\n\n    haunt:\n    for all entity a with personality enter clashSensor\n    {\n        initialize amount\n        for all entity b with fear\n        {\n            amount = amount++\n        }\n\n        if amount = a.fearAmount\n        {\n            a.position = a.respawnPoint\n        }\n    }\n\n    loseLife:\n    for all entity a with personality enter clashSensor\n    {\n        initialize amount\n        for all entity b with fear\n        {\n            amount = amount++\n        }\n\n        if amount = a.fearAmount\n        {\n            for all entity c with life\n            {\n                destroy (c)\n                break\n            }\n        }\n    }\n\n    defeat:\n    for all entity a with fear no demoMode enter clashSensor\n    {\n        a.position = a.respawnPoint\n        time = add (independenceTime, a)\n        time := random(a.independenceTimeRange)\n    }\n\n    demoDefeat:\n    for all entity b with fear demoMode enter clashSensor\n    {\n        destroy (b)\n    }\n\n    becomeInvincible:\n    for all entity a with invincibilityFormula enter deathSensor\n    {\n        for all entity b with coward\n        {\n            add(fear, b)\n            fearTimer = add(fearTimer, b)\n            b.appearance = b.fearAppearance\n            fearTimer := b.fearFrames\n        }\n    }\n\n    becomeVincible:\n    for all entity a with timed out fearTimer\n    {\n        remove (fearTimer, a)\n        remove (fear, a)\n        a.appearance = a.normalAppearance\n    }\n\n    lose:\n    for all entity a\n    {\n        initialize amount\n        for all entity b with life\n        {\n            amount = amount++\n        }\n        if amount = a.loseCondition\n        {\n            for all entity c\n            {\n                destroy (c)\n            }\n            create (a.scene)\n        }\n    }\n    win:\n    for all entity a\n    {\n        initialize amount\n        for all entity b with winRequirement\n        {\n            amount = amount++\n        }\n        if amount = a.winCondition\n        {\n            for all entity c\n            {\n                destroy (c)\n            }\n            create (a.scene)\n        }\n    }\n    spawn:\n    for all entity a with timed out spawnTimer\n    {\n        spawn = create(a.creation)\n        spawn.position = a.spawnPosition\n    }\n\n    dieByTime:\n    for all entity a with timed out lifeTimer\n    {\n        destroy (a)\n    }\n\n    teleport:\n    for all entity a with enter teleportSensor\n    {\n        a.position = join(x(a.position) * a.teleportFactor,y(a.position))\n    }\n    die:\n    for all entity a with enter deathSensor\n    {\n        destroy (a)\n    }\n\n    score:\n    for all entity a with enter deathSensor\n    {\n        for all entity b with scoreboard\n        {\n            b.number = b.number + a.worth\n        }\n    }\n\n    performCombo:\n    for all entity a with fear enter clashSensor\n    {\n        for all entity b\n        {\n            b.combo = b.combo++\n            b.number = b.number + a.worth * b.combo\n        }\n    }\n\n    resetCombo:\n    for all entity a with timed out fearTimer\n    {\n        for all entity b\n        {\n            b.combo = b.initialCombo\n        }\n    }\n\n    redirect:\n    for all entity a with wish no enter sensor no stay sensor\n    {\n        for all entity b\n        {\n            if a.parentRelation = b.parent\n            {\n                b.velocity = a.offset * b.speed\n                remove(wish, a)\n            }\n        }\n    }\n\n    makeWish:\n    for all entity a with triggered button\n    {\n        for all entity b with casting\n        {\n            b.offset = a.direction\n            add(wish, b)\n        }\n    }\n\n    follow:\n    for all entity a\n    {\n        for all entity b\n        {\n            if a.parentRelation = b.parent\n            {\n                a.position = b.position + a.offset\n            }\n        }\n    }\n\n    start:\n    for all entity a with triggered detector\n    {\n        for all entity b\n        {\n            destroy (b)\n        }\n        create (a.scene)\n    }\n")])])]),e("p",[this._v("You can download the file that represent the [game in blocks] and load it in the\nvisual editor to visualize it.")])])}),[],!1,null,null,null);e.default=i.exports}}]);