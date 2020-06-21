control()
{
	foreach a
	{
		a.velocity = a.baseVelocity * a.inputValue
	}
}
serve()
{
	foreach a
	{
		if a.timeout
		{
			angle = random(a.serveAngle)
			length = random(a.serveLength)
			
			a.velocity = xyz(cos(angle), sin(angle), a.z) * length
			remove[timeout](a)
		}
	}
}
respawn()
{
	foreach a
	{
		foreach b
		{
			if in(a, b.collisions)
			{
				a.position = b.respawnPoint
				a.velocity = b.respawnVelocity
				add[timeout](a)
			}
		}
	}
}
score()
{
	foreach a
	{
		foreach b
		{
			if in(a, b.collisions)
			{
				b.number = b.number + a.worth
			}
		}
	}
}
restart()
{
	foreach a
	{
		if a.number > a.maxNumber
		{
			foreach b
			{
				b.number = b.resetNumber
			}
		}
	}
}