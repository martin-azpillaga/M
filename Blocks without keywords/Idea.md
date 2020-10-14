* Use just curly braces to mark foreach's scope

{ a.x = b.y } ~ foreach a { foreach b { a.x = b.y }}

* Use expression and curly braces to mark if's scope

a @ overlaps(b) { a.x = b.y } ~ if a @ overlaps(b) { a.x = b.y }

* This way there is no need for standard blocks, grammar checks for correctness
* No keywords in library

{ a @ overlaps(b) { a.x = b.x }} ~

foreach a
{
	foreach b
	{
		if a @ overlaps(b)
		{
			a.x = b.x
		}
	}
}
