package m.library.rules;

import static m.library.rules.Problem.Kind.*;

public enum Problem
{
	SYNTAX_ERROR(ERROR),
	UNDECIDABLE_TYPE(ERROR),
	INCOMPATIBLE_TYPES(ERROR),
	UNDEFINED_SYMBOL(ERROR),
	REDEFINED_SYMBOL(ERROR),
	READONLY_ASSIGNMENT(ERROR),
	UNUSED_VALUE(INFORMATION),
	;

	public final Kind kind;

	Problem(Kind kind)
	{
		this.kind = kind;
	}

	public enum Kind
	{
		ERROR, WARNING, INFORMATION
	}
}
