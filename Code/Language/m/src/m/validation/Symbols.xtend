package m.validation

import static m.validation.ExponentType.*
import static m.validation.PolymorphicType.*
import static m.validation.ProductType.*
import static m.validation.Type.*

class Symbols
{
	public String name
	public Type type
		
	public static val inputTriggered = new Symbols=>[type=proposition]
	public static val inputValue = new Symbols=>[type=number]
	public static val inputVector = new Symbols=>[type=number2]
	
	public static val timer = new Symbols=>[type=number]
	public static val elapsed = new Symbols=>[type=number]
	public static val timeout = new Symbols=>[type=proposition]
	
	public static val position = new Symbols=>[type=number3]
	public static val rotation = new Symbols=>[type=number4]
	public static val scale = new Symbols=>[type=number3]
	public static val worldPosition = new Symbols=>[type=number3]
	public static val worldRotation = new Symbols=>[type=number4]
	public static val worldScale = new Symbols=>[type=number3]
	public static val parent = new Symbols=>[type=entity]
	public static val children = new Symbols=>[type=entityList]
	
	public static val mesh = new Symbols=>[type=meshType]
	public static val material = new Symbols=>[type=materialType]
	
	public static val font = new Symbols=>[type=fontType]
	public static val text = new Symbols=>[type=textType]
	public static val image = new Symbols=>[type=imageType]
	public static val button = new Symbols=>[type=proposition]
	public static val numberLabel = new Symbols=>[type=number]
	
	public static val viewAngle = new Symbols=>[type=number]
	public static val viewDistance = new Symbols=>[type=number]
	public static val clearColor = new Symbols=>[type=number4]
	public static val perspective = new Symbols=>[type=unit]
	
	public static val emission = new Symbols=>[type=number4]
	public static val spotAngle = new Symbols=>[type=number]
	public static val range = new Symbols=>[type=number]
	public static val intensity = new Symbols=>[type=number]
	public static val shadows = new Symbols=>[type=proposition]
	public static val cookie = new Symbols=>[type=imageType]
	
	public static val audioClip = new Symbols=>[type=clip]
	public static val volume = new Symbols=>[type=number]
	public static val pitch = new Symbols=>[type=number]
	public static val loop = new Symbols=>[type=unit]
	
	public static val distortion = new Symbols=>[type=number]
	public static val echo = new Symbols=>[type=number]
	public static val highPass = new Symbols=>[type=number]
	public static val lowPass = new Symbols=>[type=number]
	public static val reverb = new Symbols=>[type=number]
	public static val chorus = new Symbols=>[type=number]
	public static val doppler = new Symbols=>[type=number]
	
	public static val ip = new Symbols=>[type=number4]
	public static val port = new Symbols=>[type=number]
	public static val networkStream = new Symbols=>[type=unit]
	public static val prediction = new Symbols=>[type=unit]
	public static val owner = new Symbols=>[type=number]
	
	public static val mass = new Symbols=>[type=number]
	public static val velocity = new Symbols=>[type=number3]
	public static val acceleration = new Symbols=>[type=number3]
	public static val force = new Symbols=>[type=number3]
	public static val damping = new Symbols=>[type=number]
	public static val angularDamping = new Symbols=>[type=number]
	public static val angularVelocity = new Symbols=>[type=number3]
	public static val gravityFactor = new Symbols=>[type=number]
	
	public static val trigger = new Symbols=>[type=unit]
	public static val extents = new Symbols=>[type=number3]
	public static val radius = new Symbols=>[type=number]
	public static val height = new Symbols=>[type=number]
	public static val sideCount = new Symbols=>[type=number]
	public static val convexHull = new Symbols=>[type=unit]
	public static val geometry = new Symbols=>[type=unit]
	public static val restitution = new Symbols=>[type=number]
	public static val friction = new Symbols=>[type=number]
	
	
	public static val epsilon = new Symbols=>[type=constant]
	public static val pi = new Symbols=>[type=constant]
	public static val e = new Symbols=>[type=constant]
	
	public static val abs = new Symbols=>[type=numberNumber]
	public static val sign = new Symbols=>[type=numberNumber]
	public static val ceil = new Symbols=>[type=numberNumber]
	public static val floor = new Symbols=>[type=numberNumber]
	public static val round = new Symbols=>[type=numberNumber]
	public static val clamp = new Symbols=>[type=numberNumber]
	public static val integerPart = new Symbols=>[type=numberNumber]
	public static val fractionalPart = new Symbols=>[type=numberNumber]
	public static val inverse = new Symbols=>[type=numberNumber]
	public static val reciprocal = new Symbols=>[type=numberNumber]
	
	public static val lerp = new Symbols=>[type=numberNumber2Number]
	public static val unlerp = new Symbols=>[type=numberNumber2Number]
	public static val proportional = new Symbols=>[type=numberNumber2Number2Number]
	
	public static val cross = new Symbols=>[type=number3Number3Number3]
	public static val dot = new Symbols=>[type=number3Number3Number]
	public static val norm = new Symbols=>[type=number3Number]
	public static val normalize = new Symbols=>[type=number3Number3]
	public static val distance = new Symbols=>[type=number3Number3Number]
	public static val reflect = new Symbols=>[type=number3Number3Number3]
	public static val refract = new Symbols=>[type=number3Number3Number3]
	
	public static val or = new Symbols=>[type=propositionPropositionProposition]
	public static val and = new Symbols=>[type=propositionProposition]
	public static val not = new Symbols=>[type=propositionProposition]
	
	public static val addition = new Symbols=>[type=numericNumericNumeric]
	public static val subtraction = new Symbols=>[type=numericNumericNumeric]
	public static val multiplication = new Symbols=>[type=numericNumberNumeric]
	public static val division = new Symbols=>[type=numericNumberNumeric]
	
	public static val equal = new Symbols=>[type=anyAny]
	public static val inequal = new Symbols=>[type=anyAny]
	
	public static val lower = new Symbols=>[type=numberNumberProposition]
	public static val lowerOrEqual = new Symbols=>[type=numberNumberProposition]
	public static val greaterOrEqual = new Symbols=>[type=numberNumberProposition]
	public static val greater = new Symbols=>[type=numberNumberProposition]
	
	public static val size = new Symbols=>[type=entityListNumber]
	public static val in = new Symbols=>[type=entityEntityListProposition]
	
	public static val sin = new Symbols=>[type=numberNumber]
	public static val cos = new Symbols=>[type=numberNumber]
	public static val tan = new Symbols=>[type=numberNumber]
	public static val asin = new Symbols=>[type=numberNumber]
	public static val acos = new Symbols=>[type=numberNumber]
	public static val atan = new Symbols=>[type=numberNumber]
	public static val exp = new Symbols=>[type=numberNumber]
	public static val log = new Symbols=>[type=numberNumber]
	public static val exp10 = new Symbols=>[type=numberNumber]
	public static val log10 = new Symbols=>[type=numberNumber]
	public static val pow = new Symbols=>[type=numberNumber]
	public static val sqrt = new Symbols=>[type=numberNumber]
	public static val random = new Symbols=>[type=numberNumber]
	
	public static val create = new Symbols=>[type=entityEmpty]
	public static val destroy = new Symbols=>[type=entityEmpty]
	public static val add = new Symbols=>[type=entityEmpty]
	public static val remove = new Symbols=>[type=entityEmpty]
	public static val has = new Symbols=>[type=entityProposition]
	
	public static val write = new Symbols=>[type=anyEmpty]
	public static val writeError = new Symbols=>[type=anyEmpty]
	public static val halt = new Symbols=>[type=emptyEmpty]
	
	public static val selection = new Symbols=>[type=proposition]
	public static val iteration = new Symbols=>[type=proposition]
	public static val query = new Symbols=>[type=declaration]
}
