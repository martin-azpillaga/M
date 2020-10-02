package m.validation.problems.warnings;

public class NestedIfs
{
	// Happens when inside an if there is only one statement and it is an if
	// Both ifs can be done in one using an and
	// if x { if y {} } ~~ if x && y {}
}
