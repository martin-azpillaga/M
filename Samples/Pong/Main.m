ball has
position 0 0, scale 2 2, appearance sprite.ball,
mass 1, radius 0.5, restitution 1, category [balls],
speed_up_sensor detect [paddles], respawn_sensor detect [goals],
servable_tag, respawn_point 0 0, angle -1 1, length 10 20.

paddle has
position 0 0, scale 4 10, appearance sprite.paddle, category paddles,
extent 1 1, restitution 1, velocity 0 0,
motion gamepad.leftY, direction 0 10.

goal has
position 0 0, scale 2 100, appearance sprite.goal,
extent 1 1, restitution 1, category [goals],
score_sensor detect [balls], team [red], worth 1, effect entity.audioEffect.

wall has
position 0 0, scale 100 2, appearance sprite.wall,
extent 1 1, restitution 1.

board has maximum 1, position 0 0, scale 5 10,
team [red], scene entity.menu, number 0.

audioEffect has
audiosource audio.yay, lifetime 5s.

player has rotation 0 0 10,
viewDistance 1000, viewAngle 50, position 0 0, clearColor 0.5 0.5 0.5 1.

sun has
position 0 0, emission 1 1 1 1, intensity 1.

spot has 
position 0 0, emission 1 1 1 1, range 5, intensity 2, spotAngle 20.

lamp has 
emission 1 1 1 1, range 2.

hud has 
canvas 100 100 contains
	left_board based on board has team [blue], position -25 40.
	right_board based on board has team [red], position 25 40.
.

playground contains
	ball based on ball.
	left_paddle  based on paddle has position -45 0, motion gamepad.leftY, team [red].
	right_paddle based on paddle has position  45 0, motion gamepad.rightY, team [blue].
	left_goal based on goal has position -50 0, team [red].
	right_goal based on goal has position 50 0, team [blue].
	top_wall based on wall has position 0 50.
	bottom_wall based on wall has position 0 -50.
	player based on player.
	canvas based on hud.
.

menu contains
	hud has canvas 100 100 contains
		title has position 0 35, scale 40 10, text 'Pong'.
		subtitle has position 0 10, scale 40 10, text 'Press start'.
	.
	trigger has detector gamepad.south, scene entity.playground.
	player based on player.
.

start:
for each entity a with triggered detector
{
	for each entity b
	{
		destroy(b)
	}
	create(a.scene)
}

move:
for each entity a
{
	a.velocity = a.direction * a.motion tilt
}

respawn:
for each entity a with enter respawn_sensor
{
	a.position = a.respawn_point
	add(servable_tag, a)
}

serve:
for each entity a with servable_tag
{
	angle = random(a.angle)
	a.velocity = <cos(angle),sin(angle)> * random(a.length)
	remove(servable_tag, a)
}

score:
for each entity a with enter score_sensor
{
	for each entity b
	{
		if a.team = b.team
		{
			b.number = b.number + a.worth
		}
	}
}

restart:
for each entity a
{
	if a.number > a.maximum
	{
		for each entity b
		{
			destroy(b)
		}
		create(a.scene)
		break
	}
}

sound:
for each entity a with enter score_sensor
{
	create(a.effect)
}

destroyLifetime:
for each entity a with timed out lifetime
{
	destroy(a)
}

main world contains menu.