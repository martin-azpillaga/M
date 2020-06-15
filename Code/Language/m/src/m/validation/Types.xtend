package m.validation

import static m.validation.SumType.*
import static m.validation.ProductType.*
import static m.validation.PolymorphicType.*

class Type
{
	public static val any = new Type
	public static val unit = new Type
	public static val number = new Type
	public static val proposition = new Type
	public static val entity = new Type
	
	public static val declaration = new Type
	public static val meshType = new Type
	public static val materialType = new Type
	public static val fontType = new Type
	public static val textType = new Type
	public static val imageType = new Type
	public static val clip = new Type
	public static val list = new Type
}

class ProductType extends Type
{
	public Type left
	public Type right
	
	public static val number2 = new ProductType=>[left = number right=number]
	public static val number3 = new ProductType=>[left=number2 right=number]
	public static val number4 = new ProductType=>[left=number3 right=number]
}

class SumType extends Type
{
	public Type left
	public Type right
	
	public static val numeric = new Type
}

class ExponentType extends Type
{
	public Type left
	public Type right
	
	public static val numericNumeric = new ExponentType=>[left=numeric right=numeric]
	public static val numericNumericNumeric = new ExponentType=>[left=numeric right=numericNumeric]
	
	public static val numberNumeric = new ExponentType=>[left=number right=numeric]
	public static val numericNumberNumeric = new ExponentType=>[left=numeric right=numberNumeric]
	public static val numberNumber = new ExponentType=>[left=number right=number]
	public static val numberNumberNumber = new ExponentType=>[left=number right=numberNumber]
	
	public static val constant = new ExponentType=>[right=number]
	public static val entityEmpty = new ExponentType=>[left=entity right=unit]
	public static val entityProposition = new ExponentType=>[left=entity right=proposition]
	
	public static val propositionProposition = new ExponentType=>[left=proposition right=proposition]
	public static val propositionPropositionProposition = new ExponentType=>[left=proposition right=propositionProposition]
	
	public static val anyAny = new ExponentType=>[left=any right=any]
	public static val anyEmpty = new ExponentType=>[left=any right=unit]
	public static val emptyEmpty = new ExponentType=>[left=unit right=unit]
	
	public static val numberProposition = new ExponentType=>[left=number right=proposition]
	public static val numberNumberProposition = new ExponentType=>[left=number right=numberProposition]
	public static val entityListNumber = new ExponentType=>[left=entityList right=number]
	public static val entityListProposition = new ExponentType=>[left=entityList right=proposition]
	public static val entityEntityListProposition = new ExponentType=>[left=entity right=entityListProposition]
	public static val number3Number = new ExponentType=>[left=number3 right=number]
	public static val number3Number3 = new ExponentType=>[left=number3 right=number3]
	public static val number3Number3Number3 = new ExponentType=>[left=number3 right=number3Number3]
	public static val number3Number3Number = new ExponentType=>[left=number3 right=number3Number]
	public static val number2Number = new ExponentType=>[left=number2 right=number]
	public static val numberNumber2Number = new ExponentType=>[left=number right=number2Number]
	public static val number2Number2Number = new ExponentType=>[left=number2 right=number2Number]
	public static val numberNumber2Number2Number = new ExponentType=>[left=number right=number2Number2Number]
}

class PolymorphicType extends Type
{
	public Type function
	public Type type
	
	public static val entityList = new PolymorphicType=>[function=list type=entity]
	public static val numberList = new PolymorphicType=>[function=list type=number]
}
