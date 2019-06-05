main world contains menu.

directionChange has 
button gamepad.left 
direction 0 0.

raycaster has 
scale 4.5 4.5 casting extent 0.99 0.99 offset 0 0
sensor detect [wall]
parentRelation 0
velocity 0 0.

wall has
scale 5 5 appearance sprite.wall physical category [wall] extent 1 1.

food has
scale 1 1 appearance sprite.food radius 0.5 winRequirement worth 10 deathSensor detect [character].

fruit has
scale 3 3 appearance sprite.fruit radius 0.5 worth 100 deathSensor detect [character] lifeTimer 7s.

rules has
spawnTimer 20s
creation entity.fruit
spawnPosition 0 -5
winCondition 0
loseCondition 0
scene entity.menu.

powerup has
scale 3 3
appearance sprite.powerup
radius 0.5
winRequirement
worth 50
deathSensor detect [character]
invincibilityFormula
fearTimer 10s.

character has 
demoMode
demoPowerlessVelocity -5 0
demoPowerVelocity 5 0
category [character]
personality
fearAmount 0
respawnPoint 0 -25
sortingorder 2
wish
mass 1
physical
scale 4.5 4.5 
appearance sprite.pacman
parent 0
extent 1 1
speed 5
teleportFactor -1
teleportSensor detect [portal]
clashSensor detect [evil]
loseRequirement
.

portal has 
appearance sprite.portal
scale 5 5
extent 1 1 
category [portal]
physical.

label has number 0 scale 10 5 combo 0 initialCombo 0 scoreboard.

lifeImage has image sprite.life scale 3 3 life.

ghost has
demoMode
demoFearVelocity 2 0
appearance sprite.ghost
scale 4.5 4.5
sortingorder 1
extent 0.99 0.99
category [evil]
clashSensor detect [character]
autoMove
autoSpeed -5 0
autoSpeedFactor -1
reboundSensor detect [wall]
respawnPoint 0 5
velocity 0 0
teleportFactor -1
teleportSensor detect [portal]
independenceTime 10s
independenceTimeRange 2 5
independencePoint 0 15
cowardry
fearAppearance sprite.scared
fearFrames 7
worth 200
normalAppearance sprite.ghost
.

audioEffect has
audiosource audio.wakawaka, lifeTimer 1s.

player has
viewDistance 10, viewAngle 50, clearColor 0.2 0.2 0.2 1.


hud has 
canvas 100 100 contains
	score_board based on label has position -40 45.
	life_image_1 based on lifeImage has position 35 45.
	life_image_2 based on lifeImage has position 40 45.
	life_image_3 based on lifeImage has position 45 45.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 10, scale 100 10, text 'Pacman'.
		subtitle has position 0 -20, scale 100 5, text 'Play <start>'.
		options has position 0 -40 scale 100 4 text 'Options <select>'.
	.
	trigger has detector gamepad.start, scene entity.playground.
	rulesPage has detector gamepad.select, scene entity.rulesPage.
	player based on player.
.

rulesPage contains
	hud has canvas 100 100 contains
		blinkyText has position 10 45 scale 40 5 text 'Blinky'.
		pinkyText has position 10 35 scale 40 5 text 'Pinky'.
		inkyText has position 10 25 scale 40 5 text 'Inky'.
		clydeText has position 10 15 scale 40 5 text 'Clyde'.
		
		demoScore has position 30 0 scale 10 5 number 0 combo 0.
		demoText has position 40 0 scale 10 5 text 'pts'.
		
		foodText has position 10 -15 scale 40 5 text '10 pts'.
		powerupText has position 10 -25 scale 40 5 text '50 pts'.
		fruitText has position 10 -35 scale 40 5 text '100 pts'.
		copyrightText has position 0 -45 scale 40 5 text 'c 1980 MIDWAY MFG. CO.'.
	.
	
	blinky has position -30 45 scale 5 5 appearance sprite.blinky.
	pinky has position -30 35 scale 5 5 appearance sprite.pinky.
	inky has position -30 25 scale 5 5 appearance sprite.inky.
	clyde has position -30 15 scale 5 5 appearance sprite.clyde.
	
	realPowerup based on powerup has position -40 0.
	realPacman based on character has position 0 0.
	caster based on raycaster.
	realBlinky based on ghost without independenceTime has position 10 0 appearance sprite.blinky.
	realPinky based on ghost without independenceTime has position 15 0 appearance sprite.pinky.
	realInky based on ghost without independenceTime has position 20 0 appearance sprite.inky.
	realClyde based on ghost without independenceTime has position 25 0 appearance sprite.clyde.
	
	food has position -15 -15 scale 1 1 appearance sprite.food.
	powerup has position -15 -25 scale 4 4 appearance sprite.powerup.
	fruit has position -15 -35 scale 3 3 appearance sprite.fruit.
	
	titlePage has detector gamepad.south, scene entity.menu.
	player based on player.
.

playground contains
	ruleSet based on rules.
	
	pacman based on character without demoMode has position 0 -25.
	caster based on raycaster.
	user based on player.
	canvas based on hud.
	
	pinky based on ghost without demoMode has position 0 5 appearance sprite.pinky normalAppearance sprite.pinky independenceTime 2s.
	blinky based on ghost without demoMode has position 5 5 appearance sprite.blinky normalAppearance sprite.blinky independenceTime 4s.
	inky based on ghost without demoMode has position -5 5 appearance sprite.inky normalAppearance sprite.inky independenceTime 6s.
	clyde based on ghost without demoMode has position 0 5 appearance sprite.clyde normalAppearance sprite.clyde independenceTime 8s.
	
	leftWish based on directionChange has button gamepad.left direction -2 0.
	rightWish based on directionChange has button gamepad.right direction 2 0.
	upWish based on directionChange has button gamepad.up direction 0 2.
	downWish based on directionChange has button gamepad.down direction 0 -2.
	
	bottomWall based on wall has position 0 -50 scale 100 5.
	topWall based on wall has position 0 40 scale 100 5.
	leftBottomWall based on wall has position -50 -30 scale 5 45.
	leftTopWall based on wall has position -50 20 scale 5 45. 
	rightBottomWall based on wall has position 50 -30 scale 5 45.
	rightTopWall based on wall has position 50 20 scale 5 45. 
	leftPortal based on portal has position -50 -5 scale 5 5.
	rightPortal based on portal has position 50 -5 scale 5 5.
	
	
	
	a31 based on food has position -45 35.
	a32 based on food has position -40 35.
	a33 based on food has position -35 35.
	a34 based on wall has position -30 35.
	a35 based on food has position -25 35.
	a36 based on food has position -20 35.
	a37 based on food has position -15 35.
	a38 based on wall has position -10 35.
	a39 based on wall has position -5 35.
	a310 based on food has position 0 35.
	a311 based on wall has position 5 35.
	a312 based on wall has position 10 35.
	a313 based on food has position 15 35.
	a314 based on food has position 20 35.
	a315 based on food has position 25 35.
	a316 based on wall has position 30 35.
	a317 based on food has position 35 35.
	a318 based on food has position 40 35.
	a319 based on food has position 45 35.

	a41 based on powerup has position -45 30.
	a42 based on wall has position -40 30.
	a43 based on food has position -35 30.
	a44 based on wall has position -30 30.
	a45 based on food has position -25 30.
	a46 based on wall has position -20 30.
	a47 based on food has position -15 30.
	a48 based on wall has position -10 30.
	a49 based on food has position -5 30.
	a410 based on food has position 0 30.
	a411 based on food has position 5 30.
	a412 based on wall has position 10 30.
	a413 based on food has position 15 30.
	a414 based on wall has position 20 30.
	a415 based on food has position 25 30.
	a416 based on wall has position 30 30.
	a417 based on food has position 35 30.
	a418 based on wall has position 40 30.
	a419 based on powerup has position 45 30.
	
	a51 based on food has position -45 25.
	a52 based on wall has position -40 25.
	a53 based on food has position -35 25.
	a54 based on food has position -30 25.
	a55 based on food has position -25 25.
	a56 based on wall has position -20 25.
	a57 based on food has position -15 25.
	a58 based on food has position -10 25.
	a59 based on food has position -5 25.
	a510 based on wall has position 0 25.
	a511 based on food has position 5 25.
	a512 based on food has position 10 25.
	a513 based on food has position 15 25.
	a514 based on wall has position 20 25.
	a515 based on food has position 25 25.
	a516 based on food has position 30 25.
	a517 based on food has position 35 25.
	a518 based on wall has position 40 25.
	a519 based on food has position 45 25.
	
	a61 based on food has position -45 20.
	a62 based on wall has position -40 20.
	a63 based on wall has position -35 20.
	a64 based on food has position -30 20.
	a65 based on wall has position -25 20.
	a66 based on wall has position -20 20.
	a67 based on wall has position -15 20.
	a68 based on food has position -10 20.
	a69 based on wall has position -5 20.
	a610 based on wall has position 0 20.
	a611 based on wall has position 5 20.
	a612 based on food has position 10 20.
	a613 based on wall has position 15 20.
	a614 based on wall has position 20 20.
	a615 based on wall has position 25 20.
	a616 based on food has position 30 20.
	a617 based on wall has position 35 20.
	a618 based on wall has position 40 20.
	a619 based on food has position 45 20.
	
	a71 based on food has position -45 15.
	a72 based on food has position -40 15.
	a73 based on food has position -35 15.
	a74 based on food has position -30 15.
	a75 based on food has position -25 15.
	a76 based on food has position -20 15.
	a77 based on food has position -15 15.
	a78 based on food has position -10 15.
	a79 based on food has position -5 15.
	a710 based on food has position 0 15.
	a711 based on food has position 5 15.
	a712 based on food has position 10 15.
	a713 based on food has position 15 15.
	a714 based on food has position 20 15.
	a715 based on food has position 25 15.
	a716 based on food has position 30 15.
	a717 based on food has position 35 15.
	a718 based on food has position 40 15.
	a719 based on food has position 45 15.
	
	a81 based on food has position -45 10.
	a82 based on wall has position -40 10.
	a83 based on wall has position -35 10.
	a84 based on food has position -30 10.
	a85 based on wall has position -25 10.
	a86 based on wall has position -20 10.
	a87 based on food has position -15 10.
	a88 based on wall has position -10 10.
	a89 based on wall has position -5 10.
	a810 based on wall has position 0 10.
	a811 based on wall has position 5 10.
	a812 based on wall has position 10 10.
	a813 based on food has position 15 10.
	a814 based on wall has position 20 10.
	a815 based on wall has position 25 10.
	a816 based on food has position 30 10.
	a817 based on wall has position 35 10.
	a818 based on wall has position 40 10.
	a819 based on food has position 45 10.

	a91 based on food has position -45 5.
	a92 based on food has position -40 5.
	a93 based on food has position -35 5.
	a94 based on food has position -30 5.
	a95 based on food has position -25 5.
	a96 based on wall has position -20 5.
	a97 based on food has position -15 5.
	a98 based on wall has position -10 5.
	//a99 based on food has position -5 5.
	//a910 based on food has position 0 5.
	//a911 based on food has position 5 5.
	a912 based on wall has position 10 5.
	a913 based on food has position 15 5.
	a914 based on wall has position 20 5.
	a915 based on food has position 25 5.
	a916 based on food has position 30 5.
	a917 based on food has position 35 5.
	a918 based on food has position 40 5.
	a919 based on food has position 45 5.

	a101 based on wall has position -45 0.
	a102 based on wall has position -40 0.
	a103 based on wall has position -35 0.
	a104 based on food has position -30 0.
	a105 based on wall has position -25 0.
	a106 based on wall has position -20 0.
	a107 based on food has position -15 0.
	a108 based on wall has position -10 0.
	a109 based on wall has position -5 0.
	a1010 based on wall has position 0 0.
	a1011 based on wall has position 5 0.
	a1012 based on wall has position 10 0.
	a1013 based on food has position 15 0.
	a1014 based on wall has position 20 0.
	a1015 based on wall has position 25 0.
	a1016 based on food has position 30 0.
	a1017 based on wall has position 35 0.
	a1018 based on wall has position 40 0.
	a1019 based on wall has position 45 0.
	
	b71 based on food has position -45 -5.
	b72 based on food has position -40 -5.
	b73 based on food has position -35 -5.
	b74 based on food has position -30 -5.
	b75 based on food has position -25 -5.
	b76 based on food has position -20 -5.
	b77 based on food has position -15 -5.
	b78 based on food has position -10 -5.
	b79 based on food has position -5 -5.
	//b710 based on food has position 0 -5.
	b711 based on food has position 5 -5.
	b712 based on food has position 10 -5.
	b713 based on food has position 15 -5.
	b714 based on food has position 20 -5.
	b715 based on food has position 25 -5.
	b716 based on food has position 30 -5.
	b717 based on food has position 35 -5.
	b718 based on food has position 40 -5.
	b719 based on food has position 45 -5.
	
	a_31 based on food has position -45 -45.
	a_32 based on food has position -40 -45.
	a_33 based on food has position -35 -45.
	a_34 based on wall has position -30 -45.
	a_45 based on food has position -25 -45.
	a_36 based on food has position -20 -45.
	a_37 based on food has position -15 -45.
	a_38 based on wall has position -10 -45.
	a_39 based on wall has position -5 -45.
	a_310 based on food has position 0 -45.
	a_311 based on wall has position 5 -45.
	a_312 based on wall has position 10 -45.
	a_313 based on food has position 15 -45.
	a_314 based on food has position 20 -45.
	a_315 based on food has position 25 -45.
	a_316 based on wall has position 30 -45.
	a_317 based on food has position 35 -45.
	a_318 based on food has position 40 -45.
	a_319 based on food has position 45 -45.

	a_41 based on powerup has position -45 -40.
	a_42 based on wall has position -40 -40.
	a_43 based on food has position -35 -40.
	a_44 based on wall has position -30 -40.
	a_45 based on food has position -25 -40.
	a_46 based on wall has position -20 -40.
	a_47 based on food has position -15 -40.
	a_48 based on wall has position -10 -40.
	a_49 based on food has position -5 -40.
	a_410 based on food has position 0 -40.
	a_411 based on food has position 5 -40.
	a_412 based on wall has position 10 -40.
	a_413 based on food has position 15 -40.
	a_414 based on wall has position 20 -40.
	a_415 based on food has position 25 -40.
	a_416 based on wall has position 30 -40.
	a_417 based on food has position 35 -40.
	a_418 based on wall has position 40 -40.
	a_419 based on powerup has position 45 -40.
	
	a_51 based on food has position -45 -35.
	a_52 based on wall has position -40 -35.
	a_53 based on food has position -35 -35.
	a_54 based on food has position -30 -35.
	a_55 based on food has position -25 -35.
	a_56 based on wall has position -20 -35.
	a_57 based on food has position -15 -35.
	a_58 based on food has position -10 -35.
	a_59 based on food has position -5 -35.
	a_510 based on wall has position 0 -35.
	a_511 based on food has position 5 -35.
	a_512 based on food has position 10 -35.
	a_513 based on food has position 15 -35.
	a_514 based on wall has position 20 -35.
	a_515 based on food has position 25 -35.
	a_516 based on food has position 30 -35.
	a_517 based on food has position 35 -35.
	a_518 based on wall has position 40 -35.
	a_519 based on food has position 45 -35.
	
	a_61 based on food has position -45 -30.
	a_62 based on wall has position -40 -30.
	a_63 based on wall has position -35 -30.
	a_64 based on food has position -30 -30.
	a_65 based on wall has position -25 -30.
	a_66 based on wall has position -20 -30.
	a_67 based on wall has position -15 -30.
	a_68 based on food has position -10 -30.
	a_69 based on wall has position -5 -30.
	a_610 based on wall has position 0 -30.
	a_611 based on wall has position 5 -30.
	a_612 based on food has position 10 -30.
	a_613 based on wall has position 15 -30.
	a_614 based on wall has position 20 -30.
	a_615 based on wall has position 25 -30.
	a_616 based on food has position 30 -30.
	a_617 based on wall has position 35 -30.
	a_618 based on wall has position 40 -30.
	a_619 based on food has position 45 -30.
	
	a_71 based on food has position -45 -25.
	a_72 based on food has position -40 -25.
	a_73 based on food has position -35 -25.
	a_74 based on food has position -30 -25.
	a_75 based on food has position -25 -25.
	a_76 based on food has position -20 -25.
	a_77 based on food has position -15 -25.
	a_78 based on food has position -10 -25.
	a_79 based on food has position -5 -25.
	a_710 based on food has position 0 -25.
	a_711 based on food has position 5 -25.
	a_712 based on food has position 10 -25.
	a_713 based on food has position 15 -25.
	a_714 based on food has position 20 -25.
	a_715 based on food has position 25 -25.
	a_716 based on food has position 30 -25.
	a_717 based on food has position 35 -25.
	a_718 based on food has position 40 -25.
	a_719 based on food has position 45 -25.
	
	a_81 based on food has position -45 -20.
	a_82 based on wall has position -40 -20.
	a_83 based on wall has position -35 -20.
	a_84 based on food has position -30 -20.
	a_85 based on wall has position -25 -20.
	a_86 based on wall has position -20 -20.
	a_87 based on food has position -15 -20.
	a_88 based on wall has position -10 -20.
	a_89 based on wall has position -5 -20.
	a_810 based on wall has position 0 -20.
	a_811 based on wall has position 5 -20.
	a_812 based on wall has position 10 -20.
	a_813 based on food has position 15 -20.
	a_814 based on wall has position 20 -20.
	a_815 based on wall has position 25 -20.
	a_816 based on food has position 30 -20.
	a_817 based on wall has position 35 -20.
	a_818 based on wall has position 40 -20.
	a_819 based on food has position 45 -20.

	a_91 based on food has position -45 -15.
	a_92 based on food has position -40 -15.
	a_93 based on food has position -35 -15.
	a_94 based on food has position -30 -15.
	a_95 based on food has position -25 -15.
	a_96 based on wall has position -20 -15.
	a_97 based on food has position -15 -15.
	a_98 based on wall has position -10 -15.
	//a99 based on food has position -5 5.
	//a910 based on food has position 0 5.
	//a911 based on food has position 5 5.
	a_912 based on wall has position 10 -15.
	a_913 based on food has position 15 -15.
	a_914 based on wall has position 20 -15.
	a_915 based on food has position 25 -15.
	a_916 based on food has position 30 -15.
	a_917 based on food has position 35 -15.
	a_918 based on food has position 40 -15.
	a_919 based on food has position 45 -15.

	a_101 based on wall has position -45 -10.
	a_102 based on wall has position -40 -10.
	a_103 based on wall has position -35 -10.
	a_104 based on food has position -30 -10.
	a_105 based on wall has position -25 -10.
	a_106 based on wall has position -20 -10.
	a_107 based on food has position -15 -10.
	a_108 based on wall has position -10 -10.
	a_109 based on wall has position -5 -10.
	a_1010 based on wall has position 0 -10.
	a_1011 based on wall has position 5 -10.
	a_1012 based on wall has position 10 -10.
	a_1013 based on food has position 15 -10.
	a_1014 based on wall has position 20 -10.
	a_1015 based on wall has position 25 -10.
	a_1016 based on food has position 30 -10.
	a_1017 based on wall has position 35 -10.
	a_1018 based on wall has position 40 -10.
	a_1019 based on wall has position 45 -10.
.

demo:
for all entity a with demoMode
{
	initialize amount
	for all entity b with fear
	{
		amount = amount++
	}
	
	if amount = a.fearAmount
	{
		a.velocity = a.demoPowerlessVelocity
	}
	else
	{
		a.velocity = a.demoPowerVelocity
	}
}

demoFear:
for all entity c with fear demoMode
{
	c.autoSpeed = c.demoFearVelocity
}

goIndependent:
for all entity a with timed out independenceTime
{
	a.position = a.independencePoint
	remove (independenceTime, a)
}

move:
for all entity a with autoMove
{
	a.velocity = a.autoSpeed
}

rebound:
for all entity a with enter reboundSensor
{
	a.autoSpeed *= a.autoSpeedFactor
}

haunt:
for all entity a with personality enter clashSensor
{
	initialize amount
	for all entity b with fear
	{
		amount = amount++
	}
	
	if amount = a.fearAmount
	{
		a.position = a.respawnPoint
	}
}

loseLife:
for all entity a with personality enter clashSensor
{
	initialize amount
	for all entity b with fear
	{
		amount = amount++
	}
	
	if amount = a.fearAmount
	{
		for all entity c with life
		{
			destroy (c)
			break
		}
	}
}

defeat:
for all entity a with fear no demoMode enter clashSensor
{
	a.position = a.respawnPoint
	time = add (independenceTime, a)
	time := random(a.independenceTimeRange)
}

demoDefeat:
for all entity b with fear demoMode enter clashSensor
{
	destroy (b)
}

becomeInvincible:
for all entity a with invincibilityFormula enter deathSensor
{
	for all entity b with cowardry
	{
		add(fear, b)
		fearTimer = add(fearTimer, b)
		b.appearance = b.fearAppearance
		fearTimer := b.fearFrames
	}
}

becomeVincible:
for all entity a with timed out fearTimer
{
	remove (fearTimer, a)
	remove (fear, a)
	a.appearance = a.normalAppearance
}

lose:
for all entity a
{
	initialize amount
	for all entity b with life
	{
		amount = amount++
	}
	if amount = a.loseCondition
	{
		for all entity c
		{
			destroy (c)
		}
		create (a.scene)
	}
}
win:
for all entity a
{
	initialize amount
	for all entity b with winRequirement
	{
		amount = amount++
	}
	if amount = a.winCondition
	{
		for all entity c
		{
			destroy (c)
		}
		create (a.scene)
	}
}
spawn:
for all entity a with timed out spawnTimer
{
	spawn = create(a.creation)
	spawn.position = a.spawnPosition
}

dieByTime:
for all entity a with timed out lifeTimer
{
	destroy (a)
}

teleport:
for all entity a with enter teleportSensor
{
	a.position = join(x(a.position) * a.teleportFactor,y(a.position))
}
die:
for all entity a with enter deathSensor
{
	destroy (a)
}

score:
for all entity a with enter deathSensor
{
	for all entity b with scoreboard
	{
		b.number = b.number + a.worth
	}
}

performCombo:
for all entity a with fear enter clashSensor
{
	for all entity b
	{
		b.combo = b.combo++
		b.number = b.number + a.worth * b.combo
	}
}

resetCombo:
for all entity a with timed out fearTimer
{
	for all entity b
	{
		b.combo = b.initialCombo
	}
}

redirect:
for all entity a with wish no enter sensor no stay sensor
{
	for all entity b
	{
		if a.parentRelation = b.parent
		{
			b.velocity = a.offset * b.speed
			remove(wish, a)
		}
	}
}

makeWish:
for all entity a with triggered button
{
	for all entity b with casting
	{
		b.offset = a.direction
		add(wish, b)
	}
}

follow:
for all entity a
{
	for all entity b
	{
		if a.parentRelation = b.parent
		{
			a.position = b.position + a.offset
		}
	}
}

start:
for all entity a with triggered detector
{
	for all entity b
	{
		destroy (b)
	}
	create (a.scene)
}