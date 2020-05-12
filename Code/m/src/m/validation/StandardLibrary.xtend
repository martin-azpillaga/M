package m.validation

import java.util.List
import java.util.Map
import static m.validation.Error.*
import static m.validation.TypingReason.*

class StandardLibrary
{	
	public static val any = new Type
	public static val unit = new Type
	public static val number = new Type
	public static val proposition = new Type
	public static val entity = new Type
	public static val numeric = new Type
	
	public static val declaration = new Type
	public static val meshType = new Type
	public static val materialType = new Type
	public static val fontType = new Type
	public static val textType = new Type
	public static val imageType = new Type
	public static val clip = new Type
	public static val list = new TypeFunction
	
	public static val number2 = new ProductType=>[left = number right=number]
	public static val number3 = new ProductType=>[left=number2 right=number]
	public static val number4 = new ProductType=>[left=number3 right=number]
	
	public static val entityList = new PolymorphicType=>[function=list type=entity]
	public static val numberList = new PolymorphicType=>[function=list type=number]
	
	public static val numericNumeric = new ExponentType=>[left=numeric right=numeric]
	public static val numericNumericNumeric = new ExponentType=>[left=numeric right=numericNumeric]
	
	public static val numericNumberNumeric = new ExponentType=>[left=numeric right=new ExponentType=>[left=number right=numeric]]
	public static val numberNumber = new ExponentType=>[left=number right=number]
	public static val numberNumberNumber = new ExponentType=>[left=number right=numberNumber]
	
	public static val constant = new ExponentType=>[right=number]
	public static val entityEmpty = new ExponentType=>[left=entity right=m.validation.StandardLibrary.unit]
	public static val entityProposition = new ExponentType=>[left=entity right=proposition]
	
	public static val propositionProposition = new ExponentType=>[left=proposition right=proposition]
	public static val propositionPropositionProposition = new ExponentType=>[left=proposition right=propositionProposition]
	
	public static val anyAny = new ExponentType=>[left=any right=any]
	public static val anyEmpty = new ExponentType=>[left=any right=m.validation.StandardLibrary.unit]
	public static val emptyEmpty = new ExponentType=>[left=m.validation.StandardLibrary.unit right=m.validation.StandardLibrary.unit]
	
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
	
	
	public static val inputTriggered = new Symbol=>[type=proposition]
	public static val inputValue = new Symbol=>[type=number]
	public static val inputVector = new Symbol=>[type=number2]
	
	public static val timer = new Symbol=>[type=number]
	public static val elapsed = new Symbol=>[type=number]
	public static val timeout = new Symbol=>[type=proposition]
	
	public static val position = new Symbol=>[type=number3]
	public static val rotation = new Symbol=>[type=number4]
	public static val scale = new Symbol=>[type=number3]
	public static val worldPosition = new Symbol=>[type=number3]
	public static val worldRotation = new Symbol=>[type=number4]
	public static val worldScale = new Symbol=>[type=number3]
	public static val parent = new Symbol=>[type=entity]
	public static val children = new Symbol=>[type=entityList]
	
	public static val mesh = new Symbol=>[type=meshType]
	public static val material = new Symbol=>[type=materialType]
	
	public static val font = new Symbol=>[type=fontType]
	public static val text = new Symbol=>[type=textType]
	public static val image = new Symbol=>[type=imageType]
	public static val button = new Symbol=>[type=proposition]
	public static val numberLabel = new Symbol=>[type=number]
	
	public static val viewAngle = new Symbol=>[type=number]
	public static val viewDistance = new Symbol=>[type=number]
	public static val clearColor = new Symbol=>[type=number4]
	public static val perspective = new Symbol=>[type=m.validation.StandardLibrary.unit]
	
	public static val emission = new Symbol=>[type=number4]
	public static val spotAngle = new Symbol=>[type=number]
	public static val range = new Symbol=>[type=number]
	public static val intensity = new Symbol=>[type=number]
	public static val shadows = new Symbol=>[type=proposition]
	public static val cookie = new Symbol=>[type=imageType]
	
	public static val audioClip = new Symbol=>[type=clip]
	public static val volume = new Symbol=>[type=number]
	public static val pitch = new Symbol=>[type=number]
	public static val loop = new Symbol=>[type=m.validation.StandardLibrary.unit]
	
	public static val distortion = new Symbol=>[type=number]
	public static val echo = new Symbol=>[type=number]
	public static val highPass = new Symbol=>[type=number]
	public static val lowPass = new Symbol=>[type=number]
	public static val reverb = new Symbol=>[type=number]
	public static val chorus = new Symbol=>[type=number]
	public static val doppler = new Symbol=>[type=number]
	
	public static val ip = new Symbol=>[type=number4]
	public static val port = new Symbol=>[type=number]
	public static val networkStream = new Symbol=>[type=m.validation.StandardLibrary.unit]
	public static val prediction = new Symbol=>[type=m.validation.StandardLibrary.unit]
	public static val owner = new Symbol=>[type=number]
	
	public static val mass = new Symbol=>[type=number]
	public static val velocity = new Symbol=>[type=number3]
	public static val acceleration = new Symbol=>[type=number3]
	public static val force = new Symbol=>[type=number3]
	public static val damping = new Symbol=>[type=number]
	public static val angularDamping = new Symbol=>[type=number]
	public static val angularVelocity = new Symbol=>[type=number3]
	public static val gravityFactor = new Symbol=>[type=number]
	
	public static val trigger = new Symbol=>[type=m.validation.StandardLibrary.unit]
	public static val extents = new Symbol=>[type=number3]
	public static val radius = new Symbol=>[type=number]
	public static val height = new Symbol=>[type=number]
	public static val sideCount = new Symbol=>[type=number]
	public static val convexHull = new Symbol=>[type=m.validation.StandardLibrary.unit]
	public static val geometry = new Symbol=>[type=m.validation.StandardLibrary.unit]
	public static val restitution = new Symbol=>[type=number]
	public static val friction = new Symbol=>[type=number]
	
	
	public static val epsilon = new Symbol=>[type=constant]
	public static val pi = new Symbol=>[type=constant]
	public static val e = new Symbol=>[type=constant]
	
	public static val abs = new Symbol=>[type=numberNumber]
	public static val sign = new Symbol=>[type=numberNumber]
	public static val ceil = new Symbol=>[type=numberNumber]
	public static val floor = new Symbol=>[type=numberNumber]
	public static val round = new Symbol=>[type=numberNumber]
	public static val clamp = new Symbol=>[type=numberNumber]
	public static val integerPart = new Symbol=>[type=numberNumber]
	public static val fractionalPart = new Symbol=>[type=numberNumber]
	public static val inverse = new Symbol=>[type=numberNumber]
	public static val reciprocal = new Symbol=>[type=numberNumber]
	
	public static val lerp = new Symbol=>[type=numberNumber2Number]
	public static val unlerp = new Symbol=>[type=numberNumber2Number]
	public static val proportional = new Symbol=>[type=numberNumber2Number2Number]
	
	public static val cross = new Symbol=>[type=number3Number3Number3]
	public static val dot = new Symbol=>[type=number3Number3Number]
	public static val norm = new Symbol=>[type=number3Number]
	public static val normalize = new Symbol=>[type=number3Number3]
	public static val distance = new Symbol=>[type=number3Number3Number]
	public static val reflect = new Symbol=>[type=number3Number3Number3]
	public static val refract = new Symbol=>[type=number3Number3Number3]
	
	public static val or = new Symbol=>[type=propositionPropositionProposition]
	public static val and = new Symbol=>[type=propositionProposition]
	public static val not = new Symbol=>[type=propositionProposition]
	
	public static val addition = new Symbol=>[type=numericNumericNumeric]
	public static val subtraction = new Symbol=>[type=numericNumericNumeric]
	public static val multiplication = new Symbol=>[type=numericNumberNumeric]
	public static val division = new Symbol=>[type=numericNumberNumeric]
	
	public static val equal = new Symbol=>[type=anyAny]
	public static val inequal = new Symbol=>[type=anyAny]
	
	public static val lower = new Symbol=>[type=numberNumberProposition]
	public static val lowerOrEqual = new Symbol=>[type=numberNumberProposition]
	public static val greaterOrEqual = new Symbol=>[type=numberNumberProposition]
	public static val greater = new Symbol=>[type=numberNumberProposition]
	
	public static val size = new Symbol=>[type=entityListNumber]
	public static val in = new Symbol=>[type=entityEntityListProposition]
	
	public static val sin = new Symbol=>[type=numberNumber]
	public static val cos = new Symbol=>[type=numberNumber]
	public static val tan = new Symbol=>[type=numberNumber]
	public static val asin = new Symbol=>[type=numberNumber]
	public static val acos = new Symbol=>[type=numberNumber]
	public static val atan = new Symbol=>[type=numberNumber]
	public static val exp = new Symbol=>[type=numberNumber]
	public static val log = new Symbol=>[type=numberNumber]
	public static val exp10 = new Symbol=>[type=numberNumber]
	public static val log10 = new Symbol=>[type=numberNumber]
	public static val pow = new Symbol=>[type=numberNumber]
	public static val sqrt = new Symbol=>[type=numberNumber]
	public static val random = new Symbol=>[type=numberNumber]
	
	public static val create = new Symbol=>[type=entityEmpty]
	public static val destroy = new Symbol=>[type=entityEmpty]
	public static val add = new Symbol=>[type=entityEmpty]
	public static val remove = new Symbol=>[type=entityEmpty]
	public static val has = new Symbol=>[type=entityProposition]
	
	public static val write = new Symbol=>[type=anyEmpty]
	public static val halt = new Symbol=>[type=emptyEmpty]
	
	public static val selection = new Symbol=>[type=proposition]
	public static val iteration = new Symbol=>[type=proposition]
	public static val query = new Symbol=>[type=declaration]
	
	public List<Symbol> symbols
	public List<Symbol> blocks
	public Map<Error,String> errors
	public Map<Type,String> types
	public Map<TypingReason,String> typingReason
	
	public static val English = new StandardLibrary=>
	[
		symbols = #
		[
			mass=>[name='mass'],
			velocity=>[name='velocity'],
			has=>[name='has'],
			multiplication=>[name='*']
		].map[it=>[reason='standard library']]
		
		blocks = #
		[
			selection=>[name='if'],
			iteration=>[name='while'],
			query=>[name='foreach']
		]
		errors = #
		{
			redefinition->'Symbol already defined',
			undefined->'Symbol undefined',
			syntax->'Syntax error'
		}
		types = #
		{
			number -> 'number',
			proposition -> 'proposition',
			any -> 'any',
			unit -> 'unit',
			entity -> 'entity',
			meshType -> 'mesh',
			materialType -> 'material',
			fontType -> 'font',
			textType -> 'text',
			imageType -> 'image',
			clip -> 'audio clip',
			list -> 'list'
		}
		typingReason = #
		{
			standardSymbol -> 'standard symbol',
			queryEntity -> 'entity variable of foreach',
			cellEntity -> 'entity variable of cell'
		}
	]
	
	def String name(Type type)
	{
		if (types.containsKey(type))
		{
			return types.get(type)
		}
		else if (type instanceof ProductType)
		{
			return '''«type.left.name», «type.right.name»'''
		}
		else if (type instanceof SumType)
		{
			return '''«type.left.name» | «type.right.name»'''
		}
		else if (type instanceof ExponentType)
		{
			return '''«type.left.name» -> «type.right.name»'''
		}
		else if (type instanceof PolymorphicType)
		{
			return '''«type.function.name»<«type.type.name»>'''
		}
	}
}
