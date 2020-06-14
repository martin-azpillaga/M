package m.validation

import java.util.List
import java.util.Map
import static m.validation.MError.*
import static m.validation.TypingReason.*

class Symbol
{
	public String name
	public Type type
}

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
	public static val list = new Type
	
	public static val number2 = new ProductType=>[left = number right=number]
	public static val number3 = new ProductType=>[left=number2 right=number]
	public static val number4 = new ProductType=>[left=number3 right=number]
	
	public static val entityList = new PolymorphicType=>[function=list type=entity]
	public static val numberList = new PolymorphicType=>[function=list type=number]
	
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
	public static val perspective = new Symbol=>[type=unit]
	
	public static val emission = new Symbol=>[type=number4]
	public static val spotAngle = new Symbol=>[type=number]
	public static val range = new Symbol=>[type=number]
	public static val intensity = new Symbol=>[type=number]
	public static val shadows = new Symbol=>[type=proposition]
	public static val cookie = new Symbol=>[type=imageType]
	
	public static val audioClip = new Symbol=>[type=clip]
	public static val volume = new Symbol=>[type=number]
	public static val pitch = new Symbol=>[type=number]
	public static val loop = new Symbol=>[type=unit]
	
	public static val distortion = new Symbol=>[type=number]
	public static val echo = new Symbol=>[type=number]
	public static val highPass = new Symbol=>[type=number]
	public static val lowPass = new Symbol=>[type=number]
	public static val reverb = new Symbol=>[type=number]
	public static val chorus = new Symbol=>[type=number]
	public static val doppler = new Symbol=>[type=number]
	
	public static val ip = new Symbol=>[type=number4]
	public static val port = new Symbol=>[type=number]
	public static val networkStream = new Symbol=>[type=unit]
	public static val prediction = new Symbol=>[type=unit]
	public static val owner = new Symbol=>[type=number]
	
	public static val mass = new Symbol=>[type=number]
	public static val velocity = new Symbol=>[type=number3]
	public static val acceleration = new Symbol=>[type=number3]
	public static val force = new Symbol=>[type=number3]
	public static val damping = new Symbol=>[type=number]
	public static val angularDamping = new Symbol=>[type=number]
	public static val angularVelocity = new Symbol=>[type=number3]
	public static val gravityFactor = new Symbol=>[type=number]
	
	public static val trigger = new Symbol=>[type=unit]
	public static val extents = new Symbol=>[type=number3]
	public static val radius = new Symbol=>[type=number]
	public static val height = new Symbol=>[type=number]
	public static val sideCount = new Symbol=>[type=number]
	public static val convexHull = new Symbol=>[type=unit]
	public static val geometry = new Symbol=>[type=unit]
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
	public static val writeError = new Symbol=>[type=anyEmpty]
	public static val halt = new Symbol=>[type=emptyEmpty]
	
	public static val selection = new Symbol=>[type=proposition]
	public static val iteration = new Symbol=>[type=proposition]
	public static val query = new Symbol=>[type=declaration]
	
	public List<Symbol> symbols
	public List<Symbol> blocks
	public Map<MError,String> errors
	public Map<Type,String> types
	public Map<TypingReason,String> typingReason
	
	public static val English = new StandardLibrary=>
	[
		symbols = #
		[
			inputTriggered=>[name="inputTriggered"],
            inputValue=>[name="inputValue"],
            inputVector=>[name="inputVector"],
	
            timer=>[name="timer"],
            elapsed=>[name="elapsed"],
            timeout=>[name="timeout"],
	
            position=>[name="position"],
            rotation=>[name="rotation"],
            scale=>[name="scale"],
            worldPosition=>[name="worldPosition"],
            worldRotation=>[name="worldRotation"],
            worldScale=>[name="worldScale"],
            parent=>[name="parent"],
            children=>[name="children"],
	
            mesh=>[name="mesh"],
            material=>[name="material"],
	
            font=>[name="font"],
            text=>[name="text"],
            image=>[name="image"],
            button=>[name="button"],
            numberLabel=>[name="numberLabel"],
	
            viewAngle=>[name="viewAngle"],
            viewDistance=>[name="viewDistance"],
            clearColor=>[name="clearColor"],
            perspective=>[name="perspective"],
	
            emission=>[name="emission"],
            spotAngle=>[name="spotAngle"],
            range=>[name="range"],
            intensity=>[name="intensity"],
            shadows=>[name="shadows"],
            cookie=>[name="cookie"],
	
            audioClip=>[name="audioClip"],
            volume=>[name="volume"],
            pitch=>[name="pitch"],
            loop=>[name="loop"],
	
            distortion=>[name="distortion"],
            echo=>[name="echo"],
            highPass=>[name="highPass"],
            lowPass=>[name="lowPass"],
            reverb=>[name="reverb"],
            chorus=>[name="chorus"],
            doppler=>[name="doppler"],
	
            ip=>[name="ip"],
            port=>[name="port"],
            networkStream=>[name="networkStream"],
            prediction=>[name="prediction"],
            owner=>[name="owner"],
	
            mass=>[name="mass"],
            velocity=>[name="velocity"],
            acceleration=>[name="acceleration"],
            force=>[name="force"],
            damping=>[name="damping"],
            angularDamping=>[name="angularDamping"],
            angularVelocity=>[name="angularVelocity"],
            gravityFactor=>[name="gravityFactor"],
	
            trigger=>[name="trigger"],
            extents=>[name="extents"],
            radius=>[name="radius"],
            height=>[name="height"],
            sideCount=>[name="sideCount"],
            convexHull=>[name="convexHull"],
            geometry=>[name="geometry"],
            restitution=>[name="restitution"],
            friction=>[name="friction"],
	
	
            epsilon=>[name="epsilon"],
            pi=>[name="pi"],
            e=>[name="e"],
	
            abs=>[name="abs"],
            sign=>[name="sign"],
            ceil=>[name="ceil"],
            floor=>[name="floor"],
            round=>[name="round"],
            clamp=>[name="clamp"],
            integerPart=>[name="integerPart"],
            fractionalPart=>[name="fractionalPart"],
            inverse=>[name="inverse"],
            reciprocal=>[name="reciprocal"],
	
            lerp=>[name="lerp"],
            unlerp=>[name="unlerp"],
            proportional=>[name="proportional"],
	
            cross=>[name="cross"],
            dot=>[name="dot"],
            norm=>[name="norm"],
            normalize=>[name="normalize"],
            distance=>[name="distance"],
            reflect=>[name="reflect"],
            refract=>[name="refract"],
	
            or=>[name="or"],
            and=>[name="and"],
            not=>[name="not"],
	
            addition=>[name="addition"],
            subtraction=>[name="subtraction"],
            multiplication=>[name="multiplication"],
            division=>[name="division"],
	
            equal=>[name="equal"],
            inequal=>[name="inequal"],
	
            lower=>[name="lower"],
            lowerOrEqual=>[name="lowerOrEqual"],
            greaterOrEqual=>[name="greaterOrEqual"],
            greater=>[name="greater"],
	
            size=>[name="size"],
            in=>[name="in"],
	
            sin=>[name="sin"],
            cos=>[name="cos"],
            tan=>[name="tan"],
            asin=>[name="asin"],
            acos=>[name="acos"],
            atan=>[name="atan"],
            exp=>[name="exp"],
            log=>[name="log"],
            exp10=>[name="exp10"],
            log10=>[name="log10"],
            pow=>[name="pow"],
            sqrt=>[name="sqrt"],
            random=>[name="random"],
	
            create=>[name="create"],
            destroy=>[name="destroy"],
            add=>[name="add"],
            remove=>[name="remove"],
            has=>[name="has"],
	
            write=>[name="write"],
            writeError=>[name="writeError"],
            halt=>[name="halt"]
		]
		
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
			syntax->'Syntax error',
			undecidable -> 'Undecidable type',
			incompatible -> 'Incompatible types'
		}
		types = #
		{
			numeric -> 'numeric',
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
