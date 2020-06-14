package m.validation

class Type{}

class ProductType extends Type
{
	public Type left
	public Type right
}

class SumType extends Type
{
	public Type left
	public Type right
}

class ExponentType extends Type
{
	public Type left
	public Type right
}

class PolymorphicType extends Type
{
	public Type function
	public Type type
}

enum MError
{
	redefinition, undefined, syntax, undecidable, incompatible
}

enum GroupingReason
{
	assignment, sameComponent, sameVariable, parameterArgument, resultReturn
}

enum TypingReason
{
	standardSymbol, queryEntity, cellEntity
}
