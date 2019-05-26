ship has 
scale 5 2 appearance sprite.ship
extent 1 1 category [ship]
motorAxis gamepad.leftX motorSpeed 10 0 shootButton gamepad.south
deathSensor detect [alien projectile] goodness 
gunCreation entity.bullet creationVelocity 0 30 gunOffset 0 1.5
velocity 0 0.

alien has
scale 10 4 appearance sprite.alien
mass 1 extent 1 1 category [alien]
deathSensor detect [ship space shipProjectile] hiveSensor detect [wall]
flipSensor detect [wall] gunCreation entity.bullet creationVelocity 0 -30 
gunOffset 0 -2.5
evilness hive.

bullet has
scale 1 1 appearance sprite.bullet
extent 1 1 deathSensor detect [alien shipProjectile evilProjectile wall space defense]
scoreSensor detect [alien] team [single] worth 1
category [genericProjectile] mass 1.

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

hiveMind has 
requiredEntities 1
scene entity.menu
autoSpeed 5 0
autoSpeedRatio 1.1
flipFactor -1
gunTimer 10s
gunTimerRatio 0.9.

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
	trigger has detector gamepad.start, scene entity.playground.
	player based on player.
.

playground contains
	ship based on ship has position 0 -40.
	topSpace based on space has position 0 50.
	bottomSpace based on space has position 0 -50.
	leftWall based on wall has position -50 0.
	rightWall based on wall has position 50 0.
	
	alien based on alien has position 0 0.
	
	hiveMind based on hiveMind.
	
	player based on player.
	canvas based on hud.
.

start:
for each entity a with triggered detector
{
	for each entity b 
	{
		destroy (b)
	}
	create (a.scene)
}

control:
for each entity a
{
	a.velocity = a.motorSpeed * a.motorAxis tilt
}

shoot:
for each entity a with triggered shootButton
{
	creation = create(a.gunCreation)
	creation.position = a.position + a.gunOffset
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
		b.autoSpeed *= b.flipFactor
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

evilShoot:
for each entity a with timed out gunTimer
{
	creation = create(a.gunCreation)
	creation.position = a.position + a.gunOffset
	creation.velocity = a.creationVelocity
}

getAngry:
for each entity a with evilness enter deathSensor
{
	for each entity b with evilness
	{
		b.autoSpeed *= b.autoSpeedRatio
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
	if evils = a.requiredEntities
	{
		create (a.scene)
		for each entity c
		{
			destroy (c)
		}
	}
}

main world contains menu.