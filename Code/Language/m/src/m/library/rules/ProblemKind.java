package m.library.rules;

import static m.library.rules.ProblemKind.Severity.*;

public enum ProblemKind
{
	SYNTAX_ERROR(ERROR),
	UNDECIDABLE_TYPE(ERROR),
	INCOMPATIBLE_TYPES(ERROR),
	UNDEFINED_SYMBOL(ERROR),
	REDEFINED_SYMBOL(ERROR),
	READONLY_ASSIGNMENT(ERROR),
	UNUSED_VALUE(INFORMATION),
	;

	public final Severity severity;

	ProblemKind(Severity severity)
	{
		this.severity = severity;
	}

	public enum Severity
	{
		ERROR, WARNING, INFORMATION
	}
}
