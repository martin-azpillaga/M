ship has 
scale 5 2 appearance sprite.ship
extent 1 1 category [ship] mask [shipProjectile]
motorAxis gamepad.leftX motorSpeed 10 0 shootButton gamepad.south
deathSensor detect [alien projectile] goodness 
gunCreation entity.goodBullet creationVelocity 0 30.

alien has
scale 10 4 appearance sprite.alien
mass 1 extent 1 1 category [alien] mask [evilProjectile]
deathSensor detect [ship space shipProjectile] hiveSensor detect [wall]
flipSensor detect [wall] gunTimer -1s gunTimerRange 3 5 gunCreation entity.evilBullet creationVelocity 0 -30 
autoSpeed 5 0 factor -1 autoSpeedFactor 1.1 
evilness hive.

bullet has
scale 1 1 appearance sprite.bullet
extent 1 1 deathSensor detect [ship alien shipProjectile evilProjectile wall space defense]
scoreSensor detect [alien] team [single] worth 1
category [genericProjectile].

goodBullet based on bullet has 
category [shipProjectile].

evilBullet based on bullet has 
category [evilProjectile].

space has
scale 100 4 appearance sprite.space
extent 1 1 category [space].

wall has
scale 4 100 appearance sprite.wall
extent 2 50 category [wall].

defense has
scale 1 1 appearance sprite.defense
extent 0.5 0.5 category [defense]
deathSensor detect [shipProjectile evilProjectile alien].

wincondition has
evilnessAmount 0, scene entity.menu.

board has 
scale 5 10,
team [single], number 0.

audioEffect has
audiosource audio.yay, lifetime 5s.

player has rotation 0 0 10,
viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.5 0.5 0.5 1.

hud has 
canvas 100 100 contains
	left_board based on board has team [blue], position -25 40.
	right_board based on board has team [red], position 25 40.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 35, scale 40 10, text 'Space invaders'.
		subtitle has position 0 10, scale 40 10, text 'Press start'.
	.
	trigger has detector gamepad.south, scene entity.playground.
	player based on player.
.

playground contains
	player based on player.
	canvas based on hud.
.


controlLatitude:
for each entity a
{
	a.velocity = a.motorSpeed * a.motorAxis tilt
}

shoot:
for each entity a with triggered shootButton
{
	creation = create(a.gunCreation)
	creation.position = join(x(a.position), y(a.position)+y(a.scale))
	creation.velocity = a.creationVelocity
}

score:
for each entity a with enter scoreSensor
{
	for each entity b
	{
		if (a.team = b.team)
		{
			b.number = b.number + a.worth
		}
	}
}

die:
for each entity a with enter deathSensor
{
	destroy(a)
}

autoMove:
for each entity a
{
	a.velocity = a.autoSpeed
}

flip:
for each entity a with enter flipSensor
{
	for each entity b
	{
		b.autoSpeed *= b.factor
	}
	break
}

approach:
for each entity a with enter hiveSensor
{
	for each entity b with hive
	{
		b.position = join(x(b.position),y(b.position)-y(b.scale))
	}
	break
}

reload:
for each entity a with timed out gunTimer
{
	a.gunTimer = random(a.gunTimerRange)
}

evilShoot:
for each entity a with timed out gunTimer
{
	creation = create(a.gunCreation)
	creation.position = join(x(a.position),y(a.position)-y(a.scale))
	creation.velocity = a.creationVelocity
}

getAngry:
for each entity a with evilness enter deathSensor
{
	for each entity b with evilness
	{
		b.autoSpeed *= b.autoSpeedFactor
	}
	break
}

win:
for each entity a
{
	initialize evils
	for each entity b with evilness
	{
		evils = evils++
	}
	if evils = a.evilnessAmount
	{
		create (a.scene)
		for each entity c
		{
			destroy (c)
		}
	}
}