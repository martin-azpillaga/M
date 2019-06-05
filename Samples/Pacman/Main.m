main world contains menu.

directionChange has 
button gamepad.left 
direction 0 0.

raycaster has 
scale 5 5 casting extent 0.99 0.99 offset 0 0
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
spawnPosition 0 -15
winCondition 0
loseCondition 0
scene entity.menu.

powerup has
scale 4 4
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
demoPowerVelocity 10 0
category [character]
personality
fearAmount 0
respawnPoint 0 -25
sortingorder 2
wish
mass 1
physical
scale 5 5 
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
scale 5 20
extent 1 1 
category [portal]
physical.

label has number 0 scale 10 5 combo 0 initialCombo 0 scoreboard.

lifeImage has image sprite.life scale 5 5 life.

ghost has
demoMode
demoFearVelocity 5 0
appearance sprite.ghost
scale 5 5
sortingorder 1
extent 0.99 0.99
category [evil]
clashSensor detect [character]
autoMove
autoSpeed -5 0
autoSpeedFactor -1
reboundSensor detect [wall]
respawnPoint 0 0
velocity 0 0
teleportFactor -1
teleportSensor detect [portal]
independenceTime 10s
independenceTimeRange 2 5
independencePoint 0 15
home
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
	score_board based on label has position -40 40.
	life_image_1 based on lifeImage has position 35 45.
	life_image_2 based on lifeImage has position 40 45.
	life_image_3 based on lifeImage has position 45 45.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 10, scale 100 10, text 'Pacman'.
		subtitle has position 0 -20, scale 100 5, text 'Press start'.
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
	realPacman based on character has position 5 0.
	caster based on raycaster.
	realBlinky based on ghost without independenceTime has position 15 0 appearance sprite.blinky.
	realPinky based on ghost without independenceTime has position 20 0 appearance sprite.pinky.
	realInky based on ghost without independenceTime has position 25 0 appearance sprite.inky.
	realClyde based on ghost without independenceTime has position 30 0 appearance sprite.clyde.
	
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
	
	pinky based on ghost without demoMode has position 0 0 appearance sprite.pinky normalAppearance sprite.pinky independenceTime 2s.
	blinky based on ghost without demoMode has position 5 0 appearance sprite.blinky normalAppearance sprite.blinky independenceTime 4s.
	inky based on ghost without demoMode has position -5 0 appearance sprite.inky normalAppearance sprite.inky independenceTime 6s.
	clyde based on ghost without demoMode has position 0 5 appearance sprite.clyde normalAppearance sprite.clyde independenceTime 8s.
	
	leftWish based on directionChange has button gamepad.left direction -2 0.
	rightWish based on directionChange has button gamepad.right direction 2 0.
	upWish based on directionChange has button gamepad.up direction 0 2.
	downWish based on directionChange has button gamepad.down direction 0 -2.
	
	bottomWall based on wall has position 0 -50 scale 100 5.
	topWall based on wall has position 0 50 scale 100 5.
	leftBottomWall based on wall has position -50 -30 scale 5 40.
	leftTopWall based on wall has position -50 30 scale 5 40. 
	rightBottomWall based on wall has position 50 -30 scale 5 40.
	rightTopWall based on wall has position 50 30 scale 5 40. 
	leftPortal based on portal has position -50 0.
	rightPortal based on portal has position 50 0.
	
	a11 based on food has position -45 45.
	a12 based on food has position -40 45.
	a13 based on food has position -35 45.
	a14 based on food has position -30 45.
	a15 based on food has position -25 45.
	a16 based on food has position -20 45.
	a17 based on food has position -15 45.
	a18 based on food has position -10 45.
	a19 based on food has position -5 45.
	a0110 based on food has position 0 45.
	a0111 based on food has position 5 45.
	a0112 based on food has position 10 45.
	a0113 based on food has position 15 45.
	a0114 based on food has position 20 45.
	a0115 based on food has position 25 45.
	a0116 based on food has position 30 45.
	a0117 based on food has position 35 45.
	a0118 based on food has position 40 45.
	a0119 based on food has position 45 45.
	
	a21 based on powerup has position -45 40.
	a22 based on food has position -40 40.
	a23 based on food has position -35 40.
	a24 based on food has position -30 40.
	a25 based on food has position -25 40.
	a26 based on food has position -20 40.
	a27 based on food has position -15 40.
	a28 based on food has position -10 40.
	a29 based on food has position -5 40.
	a210 based on food has position 0 40.
	a211 based on food has position 5 40.
	a212 based on food has position 10 40.
	a213 based on food has position 15 40.
	a214 based on food has position 20 40.
	a215 based on food has position 25 40.
	a216 based on food has position 30 40.
	a217 based on food has position 35 40.
	a218 based on food has position 40 40.
	a219 based on powerup has position 45 40.
	
	a31 based on food has position -45 35.
	a32 based on food has position -40 35.
	a33 based on food has position -35 35.
	a34 based on food has position -30 35.
	a35 based on food has position -25 35.
	a36 based on food has position -20 35.
	a37 based on food has position -15 35.
	a38 based on food has position -10 35.
	a39 based on food has position -5 35.
	a310 based on food has position 0 35.
	a311 based on food has position 5 35.
	a312 based on food has position 10 35.
	a313 based on food has position 15 35.
	a314 based on food has position 20 35.
	a315 based on food has position 25 35.
	a316 based on food has position 30 35.
	a317 based on food has position 35 35.
	a318 based on food has position 40 35.
	a319 based on food has position 45 35.

	a41 based on food has position -45 30.
	a42 based on food has position -40 30.
	a43 based on food has position -35 30.
	a44 based on food has position -30 30.
	a45 based on food has position -25 30.
	a46 based on food has position -20 30.
	a47 based on food has position -15 30.
	a48 based on food has position -10 30.
	a49 based on food has position -5 30.
	a410 based on food has position 0 30.
	a411 based on food has position 5 30.
	a412 based on food has position 10 30.
	a413 based on food has position 15 30.
	a414 based on food has position 20 30.
	a415 based on food has position 25 30.
	a416 based on food has position 30 30.
	a417 based on food has position 35 30.
	a418 based on food has position 40 30.
	a419 based on food has position 45 30.
	
	a51 based on food has position -45 25.
	a52 based on food has position -40 25.
	a53 based on food has position -35 25.
	a54 based on food has position -30 25.
	a55 based on food has position -25 25.
	a56 based on food has position -20 25.
	a57 based on food has position -15 25.
	a58 based on food has position -10 25.
	a59 based on food has position -5 25.
	a510 based on food has position 0 25.
	a511 based on food has position 5 25.
	a512 based on food has position 10 25.
	a513 based on food has position 15 25.
	a514 based on food has position 20 25.
	a515 based on food has position 25 25.
	a516 based on food has position 30 25.
	a517 based on food has position 35 25.
	a518 based on food has position 40 25.
	a519 based on food has position 45 25.
	
	a61 based on food has position -45 20.
	a62 based on food has position -40 20.
	a63 based on food has position -35 20.
	a64 based on food has position -30 20.
	a65 based on food has position -25 20.
	a66 based on food has position -20 20.
	a67 based on food has position -15 20.
	a68 based on food has position -10 20.
	a69 based on food has position -5 20.
	a610 based on food has position 0 20.
	a611 based on food has position 5 20.
	a612 based on food has position 10 20.
	a613 based on food has position 15 20.
	a614 based on food has position 20 20.
	a615 based on food has position 25 20.
	a616 based on food has position 30 20.
	a617 based on food has position 35 20.
	a618 based on food has position 40 20.
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
	a82 based on food has position -40 10.
	a83 based on food has position -35 10.
	a84 based on food has position -30 10.
	a85 based on food has position -25 10.
	a86 based on food has position -20 10.
	a87 based on food has position -15 10.
	a88 based on wall has position -10 10.
	a89 based on wall has position -5 10.
	a810 based on wall has position 0 10.
	a811 based on wall has position 5 10.
	a812 based on wall has position 10 10.
	a813 based on food has position 15 10.
	a814 based on food has position 20 10.
	a815 based on food has position 25 10.
	a816 based on food has position 30 10.
	a817 based on food has position 35 10.
	a818 based on food has position 40 10.
	a819 based on food has position 45 10.

	a91 based on food has position -45 5.
	a92 based on food has position -40 5.
	a93 based on food has position -35 5.
	a94 based on food has position -30 5.
	a95 based on food has position -25 5.
	a96 based on food has position -20 5.
	a97 based on food has position -15 5.
	a98 based on wall has position -10 5.
	//a99 based on food has position -5 5.
	//a910 based on food has position 0 5.
	//a911 based on food has position 5 5.
	a912 based on wall has position 10 5.
	a913 based on food has position 15 5.
	a914 based on food has position 20 5.
	a915 based on food has position 25 5.
	a916 based on food has position 30 5.
	a917 based on food has position 35 5.
	a918 based on food has position 40 5.
	a919 based on food has position 45 5.

	a101 based on food has position -45 0.
	a102 based on food has position -40 0.
	a103 based on food has position -35 0.
	a104 based on food has position -30 0.
	a105 based on food has position -25 0.
	a106 based on food has position -20 0.
	a107 based on food has position -15 0.
	a108 based on wall has position -10 0.
	//a109 based on food has position -5 0.
	//a1010 based on food has position 0 0.
	//a1011 based on food has position 5 0.
	a1012 based on wall has position 10 0.
	a1013 based on food has position 15 0.
	a1014 based on food has position 20 0.
	a1015 based on food has position 25 0.
	a1016 based on food has position 30 0.
	a1017 based on food has position 35 0.
	a1018 based on food has position 40 0.
	a1019 based on food has position 45 0.
	
	a111 based on food has position -45 -5.
	a112 based on food has position -40 -5.
	a113 based on food has position -35 -5.
	a114 based on food has position -30 -5.
	a115 based on food has position -25 -5.
	a116 based on food has position -20 -5.
	a117 based on food has position -15 -5.
	a118 based on wall has position -10 -5.
	a119 based on wall has position -5 -5.
	a1110 based on wall has position 0 -5.
	a1111 based on wall has position 5 -5.
	a1112 based on wall has position 10 -5.
	a1113 based on food has position 15 -5.
	a1114 based on food has position 20 -5.
	a1115 based on food has position 25 -5.
	a1116 based on food has position 30 -5.
	a1117 based on food has position 35 -5.
	a1118 based on food has position 40 -5.
	a1119 based on food has position 45 -5.
	
	b11 based on food has position -45 -45.
	b12 based on food has position -40 -45.
	b13 based on food has position -35 -45.
	b14 based on food has position -30 -45.
	b15 based on food has position -25 -45.
	b16 based on food has position -20 -45.
	b17 based on food has position -15 -45.
	b18 based on food has position -10 -45.
	b19 based on food has position -5 -45.
	b0110 based on food has position 0 -45.
	b0111 based on food has position 5 -45.
	b0112 based on food has position 10 -45.
	b0113 based on food has position 15 -45.
	b0114 based on food has position 20 -45.
	b0115 based on food has position 25 -45.
	b0116 based on food has position 30 -45.
	b0117 based on food has position 35 -45.
	b0118 based on food has position 40 -45.
	b0119 based on food has position 45 -45.
	
	b21 based on powerup has position -45 -40.
	b22 based on food has position -40 -40.
	b23 based on food has position -35 -40.
	b24 based on food has position -30 -40.
	b25 based on food has position -25 -40.
	b26 based on food has position -20 -40.
	b27 based on food has position -15 -40.
	b28 based on food has position -10 -40.
	b29 based on food has position -5 -40.
	b210 based on food has position 0 -40.
	b211 based on food has position 5 -40.
	b212 based on food has position 10 -40.
	b213 based on food has position 15 -40.
	b214 based on food has position 20 -40.
	b215 based on food has position 25 -40.
	b216 based on food has position 30 -40.
	b217 based on food has position 35 -40.
	b218 based on food has position 40 -40.
	b219 based on powerup has position 45 -40.
	
	b31 based on food has position -45 -35.
	b32 based on food has position -40 -35.
	b33 based on food has position -35 -35.
	b34 based on food has position -30 -35.
	b35 based on food has position -25 -35.
	b36 based on food has position -20 -35.
	b37 based on food has position -15 -35.
	b38 based on food has position -10 -35.
	b39 based on food has position -5 -35.
	b310 based on food has position 0 -35.
	b311 based on food has position 5 -35.
	b312 based on food has position 10 -35.
	b313 based on food has position 15 -35.
	b314 based on food has position 20 -35.
	b315 based on food has position 25 -35.
	b316 based on food has position 30 -35.
	b317 based on food has position 35 -35.
	b318 based on food has position 40 -35.
	b319 based on food has position 45 -35.

	b41 based on food has position -45 -30.
	b42 based on food has position -40 -30.
	b43 based on food has position -35 -30.
	b44 based on food has position -30 -30.
	b45 based on food has position -25 -30.
	b46 based on food has position -20 -30.
	b47 based on food has position -15 -30.
	b48 based on food has position -10 -30.
	b49 based on food has position -5 -30.
	b410 based on food has position 0 -30.
	b411 based on food has position 5 -30.
	b412 based on food has position 10 -30.
	b413 based on food has position 15 -30.
	b414 based on food has position 20 -30.
	b415 based on food has position 25 -30.
	b416 based on food has position 30 -30.
	b417 based on food has position 35 -30.
	b418 based on food has position 40 -30.
	b419 based on food has position 45 -30.
	
	b51 based on food has position -45 -25.
	b52 based on food has position -40 -25.
	b53 based on food has position -35 -25.
	b54 based on food has position -30 -25.
	b55 based on food has position -25 -25.
	b56 based on food has position -20 -25.
	b57 based on food has position -15 -25.
	b58 based on food has position -10 -25.
	b59 based on food has position -5 -25.
	//b510 based on food has position 0 -25.
	b511 based on food has position 5 -25.
	b512 based on food has position 10 -25.
	b513 based on food has position 15 -25.
	b514 based on food has position 20 -25.
	b515 based on food has position 25 -25.
	b516 based on food has position 30 -25.
	b517 based on food has position 35 -25.
	b518 based on food has position 40 -25.
	b519 based on food has position 45 -25.
	
	b61 based on food has position -45 -20.
	b62 based on food has position -40 -20.
	b63 based on food has position -35 -20.
	b64 based on food has position -30 -20.
	b65 based on food has position -25 -20.
	b66 based on food has position -20 -20.
	b67 based on food has position -15 -20.
	b68 based on food has position -10 -20.
	b69 based on food has position -5 -20.
	b610 based on food has position 0 -20.
	b611 based on food has position 5 -20.
	b612 based on food has position 10 -20.
	b613 based on food has position 15 -20.
	b614 based on food has position 20 -20.
	b615 based on food has position 25 -20.
	b616 based on food has position 30 -20.
	b617 based on food has position 35 -20.
	b618 based on food has position 40 -20.
	b619 based on food has position 45 -20.
	
	b71 based on food has position -45 -15.
	b72 based on food has position -40 -15.
	b73 based on food has position -35 -15.
	b74 based on food has position -30 -15.
	b75 based on food has position -25 -15.
	b76 based on food has position -20 -15.
	b77 based on food has position -15 -15.
	b78 based on food has position -10 -15.
	b79 based on food has position -5 -15.
	b710 based on food has position 0 -15.
	b711 based on food has position 5 -15.
	b712 based on food has position 10 -15.
	b713 based on food has position 15 -15.
	b714 based on food has position 20 -15.
	b715 based on food has position 25 -15.
	b716 based on food has position 30 -15.
	b717 based on food has position 35 -15.
	b718 based on food has position 40 -15.
	b719 based on food has position 45 -15.
	
	b81 based on food has position -45 -10.
	b82 based on food has position -40 -10.
	b83 based on food has position -35 -10.
	b84 based on food has position -30 -10.
	b85 based on food has position -25 -10.
	b86 based on food has position -20 -10.
	b87 based on food has position -15 -10.
	b88 based on food has position -10 -10.
	b89 based on food has position -5 -10.
	b810 based on food has position 0 -10.
	b811 based on food has position 5 -10.
	b812 based on food has position 10 -10.
	b813 based on food has position 15 -10.
	b814 based on food has position 20 -10.
	b815 based on food has position 25 -10.
	b816 based on food has position 30 -10.
	b817 based on food has position 35 -10.
	b818 based on food has position 40 -10.
	b819 based on food has position 45 -10.
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
for all entity a with home timed out independenceTime
{
	a.position = a.independencePoint
	remove (home, a)
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
	add (home, a)
	a.independenceTime = random(a.independenceTimeRange)
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