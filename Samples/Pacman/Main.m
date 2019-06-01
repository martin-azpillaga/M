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
invincibility
spawnTimer 20s
creation entity.fruit
spawnPosition 0 0
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
invincibilityTimer 10s.

character has 
category [character]
personality
wish
mass 1
physical
scale 5 5 
appearance sprite.pacman
invincibleAppearance sprite.invincible
invincibilityFrames 5
normalAppearance sprite.pacman
parent 0
velocity 0 0
stopVelocity 0 0
extent 1 1
speed 5
stopSensor detect [wall]
teleportFactor -1
teleportSensor detect [portal]
clashSensor detect [evil]
loseRequirement
.

portal has 
appearance sprite.portal
scale 5 20
extent 1 1 
category [portal].

label has number 0 scale 10 5.

ghost has
appearance sprite.ghost
scale 5 5
extent 1 1
category [evil]
clashSensor detect [character]
autoMove
autoSpeed 4 0
autoSpeedFactor -1
reboundSensor detect [wall]
respawnPoint 0 0
velocity 0 0.

audioEffect has
audiosource audio.wakawaka, lifeTimer 1s.

player has rotation 0 0 10,
viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.2 0.2 0.2 1.


hud has 
canvas 100 100 contains
	score_board based on label has position -40 40.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 10, scale 100 10, text 'Pacman'.
		subtitle has position 0 -20, scale 100 5, text 'Press start'.
	.
	trigger has detector gamepad.start, scene entity.playground.
	player based on player.
.

playground contains
	ruleSet based on rules.
	
	pacman based on character has position 0 -25.
	caster based on raycaster.
	user based on player.
	canvas based on hud.
	
	pinky based on ghost has position -25 25.
	bluey based on ghost has position 25 25.
	
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
	a110 based on food has position 0 45.
	a111 based on food has position 5 45.
	a112 based on food has position 10 45.
	a113 based on food has position 15 45.
	a114 based on food has position 20 45.
	a115 based on food has position 25 45.
	a116 based on food has position 30 45.
	a117 based on food has position 35 45.
	a118 based on food has position 40 45.
	a119 based on food has position 45 45.
	
	a21 based on food has position -45 40.
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
	a219 based on food has position 45 40.
	
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
	
	wall1 based on wall has position 0 -30 scale 15 5.
	wall2 based on wall has position 0 -35.
	
	foo1 based on food has position -5 -25.
	foo2 based on food has position -10 -25.
	foo3 based on food has position -10 -30.
	foo4 based on food has position -10 -35.
	foo5 based on food has position -5 -35.
	foo6 based on food has position -5 -40.
	
	power1 based on powerup has position 45 40.
	power2 based on powerup has position 45 -40.
	power3 based on powerup has position -45 -40.
	power4 based on powerup has position -45 40.
.

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
for all entity a with personality no invincibility enter clashSensor
{
	destroy (a)
}

defeat:
for all entity a with no personality enter clashSensor
{
	for all entity b with personality invincibility
	{
		a.position = a.respawnPoint
		break
	}
}

becomeInvincible:
for all entity a with invincibilityFormula enter deathSensor
{
	for all entity b with personality
	{
		add(invincibility, b)
		add(invincibilityTimer, b)
		b.appearance = b.invincibleAppearance
	}
}

adjustInvincible:
for all entity a
{
	a.invincibilityTimer = a.invincibilityFrames
}

becomeVincible:
for all entity a with timed out invincibilityTimer
{
	remove (invincibilityTimer, a)
	remove (invincibility, a)
	a.appearance = a.normalAppearance
}

lose:
for all entity a
{
	initialize amount
	for all entity b with loseRequirement
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
	for all entity b
	{
		b.number = b.number + a.worth
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

main world contains playground.