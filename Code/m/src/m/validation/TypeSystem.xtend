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

class TypeFunction extends Type
{
	
}

class PolymorphicType extends Type
{
	public TypeFunction function
	public Type type
}

class Symbol
{
	public String name
	public Type type
	public String reason
}

enum Error
{
	redefinition,undefined, syntax, undecidable, incompatible
}

enum GroupingReason
{
	assignment, sameComponent, sameVariable, parameterArgument, resultReturn
}

enum TypingReason
{
	standardSymbol, queryEntity, cellEntity
}