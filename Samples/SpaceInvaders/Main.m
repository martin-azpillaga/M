ship has 
scale 5 2 appearance sprite.ship
extent 1 1 category [ship]
motorAxis gamepad.leftX motorSpeed 10 0 shootButton gamepad.south
deathSensor detect [alien shipProjectile] goodness 
gunCreation entity.bullet creationVelocity 0 30 gunOffset 0 1.6
velocity 0 0.

alien has
scale 9 4 appearance sprite.alien
extent 1 1 category [alien]
mass 1
deathSensor detect [ship space shipProjectile] hiveSensor detect [wall]
flipSensor detect [wall] gunCreation entity.bullet creationVelocity 0 -30 
gunOffset 0 -2.6
worth 10
evilness hive gun
relegation 0
promotion 1.

ufo has scale 5 2 appearance sprite.ufo
mass 1 extent 1 1 category [alien]
deathSensor detect [shipProjectile] scapeSensor detect [wall] worth 500.

bullet has
scale 1 1 appearance sprite.bullet
extent 1 1 deathSensor detect [alien shipProjectile evilProjectile wall space defense]
team [single]
category [shipProjectile] mass 1.

goodBullet based on bullet has 
category [shipProjectile].

evilBullet based on bullet has 
category [evilProjectile].

space has
scale 100 4 appearance sprite.space
extent 1 1 category [space].

wall has
scale 4 100 appearance sprite.wall
extent 1 1 category [wall].

defense has
scale 11 11 appearance sprite.defense
extent 1 1  trigger 1 category [defense]
deathSensor detect [shipProjectile evilProjectile alien].

hiveMind has 
requiredEntities 1
requiredGoodness 1
scene entity.menu
autoSpeed 5 0
autoSpeedRatio 1.1
flipFactor -1
gunTimer 1s
gunTimerRatio 0.9
probabilityRange 0 1
probability 0.5
ufoTimer 10s
bonus entity.ufo
bonusSpawnPoint -40 40
bonusVelocity 10 0.

board has 
scale 5 10,
team [single], number 0.

audioEffect has
audiosource audio.yay, lifetime 5s.

player has rotation 0 0 10,
viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.5 0.5 0.5 1.

hud has 
canvas 100 100 contains
	score based on board has position -40 40.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 10, scale 100 10, text 'Space_Invaders'.
		subtitle has position 0 -20, scale 100 5, text 'Press_Start'.
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
	
	alien based on alien has position 0 0 relegation 0 promotion -1.
	alienB based on alien has position -20 0 relegation 1 promotion -1.
	alienC based on alien has position -40 0 relegation 2 promotion -1.
	alienD based on alien has position 20 0 relegation 3 promotion -1.
	alienE based on alien has position 40 0 relegation 4 promotion -1.
	
	alienFather based on alien without gun has position 0 10 relegation 5 promotion 0.
	alienBFather based on alien without gun has position -20 10 relegation 6 promotion 1.
	alienCFather based on alien without gun has position -40 10 relegation 7 promotion 2.
	alienDFather based on alien without gun has position 20 10 relegation 8 promotion 3.
	alienEFather based on alien without gun has position 40 10 relegation 9 promotion 4.
	
	alienGrandpa based on alien without gun has position 0 20 relegation 10 promotion 5.
	alienBGrandpa based on alien without gun has position -20 20 relegation 11 promotion 6.
	alienCGrandpa based on alien without gun has position -40 20 relegation 12 promotion 7.
	alienDGrandpa based on alien without gun has position 20 20 relegation 13 promotion 8.
	alienEGrandpa based on alien without gun has position 40 20 relegation 14 promotion 9.
	
	
	
	hiveMind based on hiveMind.
	
	defenseA based on defense has position -33 -20.
	defenseB based on defense has position -11 -20.
	defenseC based on defense has position 11 -20.
	defenseD based on defense has position 33 -20.
	
	player based on player.
	canvas based on hud.
.