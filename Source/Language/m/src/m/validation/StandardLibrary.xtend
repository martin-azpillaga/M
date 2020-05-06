package m.library

import m.model.ModelFactory

class StandardLibrary
{
	static extension ModelFactory factory = ModelFactory.eINSTANCE
	
	public static val any = createType
	public static val empty = createType
	public static val number = createType
	public static val proposition = createType
	public static val entity = createType
	public static val numeric = createType
	
	public static val declaration = createType
	public static val meshType = createType
	public static val materialType = createType
	public static val fontType = createType
	public static val textType = createType
	public static val imageType = createType
	public static val clip = createType
	
	public static val number2 = createTupleType=>[setType = number setLength=2]
	public static val number3 = createTupleType=>[type=number setLength=3]
	public static val number4 = createTupleType=>[type=number setLength=4]
	
	public static val entityList = createListType=>[type=entity]
	public static val numberList = createListType=>[type=number]
	
	public static val inputTriggered = createComponent=>[type=proposition]
	public static val inputValue = createComponent=>[type=number]
	public static val inputVector = createComponent=>[type=number2]
	
	public static val timer = createComponent=>[type=number]
	public static val elapsed = createComponent=>[type=number]
	public static val timeout = createComponent=>[type=proposition]
	
	public static val position = createComponent=>[type=number3]
	public static val rotation = createComponent=>[type=number4]
	public static val scale = createComponent=>[type=number3]
	public static val worldPosition = createComponent=>[type=number3]
	public static val worldRotation = createComponent=>[type=number4]
	public static val worldScale = createComponent=>[type=number3]
	public static val parent = createComponent=>[type=entity]
	public static val children = createComponent=>[type=entityList]
	
	public static val mesh = createComponent=>[type=meshType]
	public static val material = createComponent=>[type=materialType]
	
	public static val font = createComponent=>[type=fontType]
	public static val text = createComponent=>[type=textType]
	public static val image = createComponent=>[type=imageType]
	public static val button = createComponent=>[type=proposition]
	public static val numberLabel = createComponent=>[type=number]
	
	public static val viewAngle = createComponent=>[type=number]
	public static val viewDistance = createComponent=>[type=number]
	public static val clearColor = createComponent=>[type=number4]
	public static val perspective = createComponent=>[type=empty]
	
	public static val emission = createComponent=>[type=number4]
	public static val spotAngle = createComponent=>[type=number]
	public static val range = createComponent=>[type=number]
	public static val intensity = createComponent=>[type=number]
	public static val shadows = createComponent=>[type=proposition]
	public static val cookie = createComponent=>[type=imageType]
	
	public static val audioClip = createComponent=>[type=clip]
	public static val volume = createComponent=>[type=number]
	public static val pitch = createComponent=>[type=number]
	public static val loop = createComponent=>[type=empty]
	
	public static val distortion = createComponent=>[type=number]
	public static val echo = createComponent=>[type=number]
	public static val highPass = createComponent=>[type=number]
	public static val lowPass = createComponent=>[type=number]
	public static val reverb = createComponent=>[type=number]
	public static val chorus = createComponent=>[type=number]
	public static val doppler = createComponent=>[type=number]
	
	public static val ip = createComponent=>[type=number4]
	public static val port = createComponent=>[type=number]
	public static val networkStream = createComponent=>[type=empty]
	public static val prediction = createComponent=>[type=empty]
	public static val owner = createComponent=>[type=number]
	
	public static val mass = createComponent=>[type=number]
	public static val velocity = createComponent=>[type=number3]
	public static val acceleration = createComponent=>[type=number3]
	public static val force = createComponent=>[type=number3]
	public static val damping = createComponent=>[type=number]
	public static val angularDamping = createComponent=>[type=number]
	public static val angularVelocity = createComponent=>[type=number3]
	public static val gravityFactor = createComponent=>[type=number]
	
	public static val trigger = createComponent=>[type=empty]
	public static val extents = createComponent=>[type=number3]
	public static val radius = createComponent=>[type=number]
	public static val height = createComponent=>[type=number]
	public static val sideCount = createComponent=>[type=number]
	public static val convexHull = createComponent=>[type=empty]
	public static val geometry = createComponent=>[type=empty]
	public static val restitution = createComponent=>[type=number]
	public static val friction = createComponent=>[type=number]
	
	public static val numericNumeric = createFunctionType=>[source=numeric target=numeric]
	public static val numericNumericNumeric = createFunctionType=>[source=numeric target=numericNumeric]
	
	public static val numericNumberNumeric = createFunctionType=>[source=numeric target=createFunctionType=>[source=number target=numeric]]
	public static val numberNumber = createFunctionType=>[source=number target=number]
	public static val numberNumberNumber = createFunctionType=>[source=number target=numberNumber]
	
	public static val constant = createFunctionType=>[target=number]
	public static val entityEmpty = createFunctionType=>[source=entity target=empty]
	public static val entityProposition = createFunctionType=>[source=entity target=proposition]
	
	public static val propositionProposition = createFunctionType=>[source=proposition target=proposition]
	public static val propositionPropositionProposition = createFunctionType=>[source=proposition target=propositionProposition]
	
	public static val anyAny = createFunctionType=>[source=any target=any]
	public static val anyEmpty = createFunctionType=>[source=any target=empty]
	public static val emptyEmpty = createFunctionType=>[source=empty target=empty]
	
	public static val numberProposition = createFunctionType=>[source=number target=proposition]
	public static val numberNumberProposition = createFunctionType=>[source=number target=numberProposition]
	
	public static val entityListNumber = createFunctionType=>[source=entityList target=number]
	public static val entityListProposition = createFunctionType=>[source=entityList target=proposition]
	public static val entityEntityListProposition = createFunctionType=>[source=entity target=entityListProposition]
	public static val number3Number = createFunctionType=>[source=number3 target=number]
	public static val number3Number3 = createFunctionType=>[source=number3 target=number3]
	public static val number3Number3Number3 = createFunctionType=>[source=number3 target=number3Number3]
	public static val number3Number3Number = createFunctionType=>[source=number3 target=number3Number]
	public static val number2Number = createFunctionType=>[source=number2 target=number]
	public static val numberNumber2Number = createFunctionType=>[source=number target=number2Number]
	public static val number2Number2Number = createFunctionType=>[source=number2 target=number2Number]
	public static val numberNumber2Number2Number = createFunctionType=>[source=number target=number2Number2Number]
	public static val epsilon = createFunction=>[type=constant]
	public static val pi = createFunction=>[type=constant]
	public static val e = createFunction=>[type=constant]
	
	public static val abs = createFunction=>[type=numberNumber]
	public static val sign = createFunction=>[type=numberNumber]
	public static val ceil = createFunction=>[type=numberNumber]
	public static val floor = createFunction=>[type=numberNumber]
	public static val round = createFunction=>[type=numberNumber]
	public static val clamp = createFunction=>[type=numberNumber]
	public static val integerPart = createFunction=>[type=numberNumber]
	public static val fractionalPart = createFunction=>[type=numberNumber]
	public static val inverse = createFunction=>[type=numberNumber]
	public static val reciprocal = createFunction=>[type=numberNumber]
	
	public static val lerp = createFunction=>[type=numberNumber2Number]
	public static val unlerp = createFunction=>[type=numberNumber2Number]
	public static val proportional = createFunction=>[type=numberNumber2Number2Number]
	
	public static val cross = createFunction=>[type=number3Number3Number3]
	public static val dot = createFunction=>[type=number3Number3Number]
	public static val norm = createFunction=>[type=number3Number]
	public static val normalize = createFunction=>[type=number3Number3]
	public static val distance = createFunction=>[type=number3Number3Number]
	public static val reflect = createFunction=>[type=number3Number3Number3]
	public static val refract = createFunction=>[type=number3Number3Number3]
	
	public static val or = createFunction=>[type=propositionPropositionProposition]
	public static val and = createFunction=>[type=propositionProposition]
	public static val not = createFunction=>[type=propositionProposition]
	
	public static val addition = createFunction=>[type=numericNumericNumeric]
	public static val subtraction = createFunction=>[type=numericNumericNumeric]
	public static val multiplication = createFunction=>[type=numericNumberNumeric]
	public static val division = createFunction=>[type=numericNumberNumeric]
	
	public static val equal = createFunction=>[type=anyAny]
	public static val inequal = createFunction=>[type=anyAny]
	
	public static val lower = createFunction=>[type=numberNumberProposition]
	public static val lowerOrEqual = createFunction=>[type=numberNumberProposition]
	public static val greaterOrEqual = createFunction=>[type=numberNumberProposition]
	public static val greater = createFunction=>[type=numberNumberProposition]
	
	public static val size = createFunction=>[type=entityListNumber]
	public static val in = createFunction=>[type=entityEntityListProposition]
	
	public static val sin = createFunction=>[type=numberNumber]
	public static val cos = createFunction=>[type=numberNumber]
	public static val tan = createFunction=>[type=numberNumber]
	public static val asin = createFunction=>[type=numberNumber]
	public static val acos = createFunction=>[type=numberNumber]
	public static val atan = createFunction=>[type=numberNumber]
	public static val exp = createFunction=>[type=numberNumber]
	public static val log = createFunction=>[type=numberNumber]
	public static val exp10 = createFunction=>[type=numberNumber]
	public static val log10 = createFunction=>[type=numberNumber]
	public static val pow = createFunction=>[type=numberNumber]
	public static val sqrt = createFunction=>[type=numberNumber]
	public static val random = createFunction=>[type=numberNumber]
	
	public static val create = createFunction=>[type=entityEmpty]
	public static val destroy = createFunction=>[type=entityEmpty]
	public static val add = createFunction=>[type=entityEmpty]
	public static val remove = createFunction=>[type=entityEmpty]
	public static val has = createFunction=>[type=entityProposition]
	
	public static val write = createFunction=>[type=anyEmpty]
	public static val halt = createFunction=>[type=emptyEmpty]
	
	public static val selection = createBlock=>[type=proposition]
	public static val iteration = createBlock=>[type=proposition]
	public static val foreach = createBlock=>[type=declaration]
}