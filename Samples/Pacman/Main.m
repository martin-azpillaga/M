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
scale 2 2 appearance sprite.food radius 1 category [food] worth 1 deathSensor detect [character].

character has 
category [character]
wish
mass 1
physical
scale 5 5 
appearance sprite.pacman
parent 0
velocity 0 0
stopVelocity 0 0
extent 1 1
speed 2
stopSensor detect [wall]
.

label has number 0 scale 10 5.

audioEffect has
audiosource audio.wakawaka, lifetime 1s.

player has rotation 0 0 10,
viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.5 0.5 0.5 1.


hud has 
canvas 100 100 contains
	score_board based on label has position -40 40.
.

playground contains
	pacman based on character has position 0 -25.
	caster based on raycaster.
	user based on player.
	canvas based on hud.
	
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
	
	wall1 based on wall has position 0 -30 scale 15 5.
	wall2 based on wall has position 0 -35.
	
	foo1 based on food has position -5 -25.
	foo2 based on food has position -10 -25.
	foo3 based on food has position -10 -30.
	foo4 based on food has position -10 -35.
	foo5 based on food has position -5 -35.
	foo6 based on food has position -5 -40.
.

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

main world contains playground.