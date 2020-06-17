package m.validation

import java.util.List
import java.util.Map

import static m.validation.Symbols.*
import static m.validation.Type.*
import static m.validation.SumType.*
import static m.validation.MError.*

class StandardLibrary
{
	public List<Symbols> symbols
	public List<Symbols> blocks
	public Map<MError,String> errors
	public Map<Type,String> types
	
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
            numberLabel=>[name="number"],
	
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
            
            collisions=>[name="collisions"],
	
	
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
	
            or=>[name="||"],
            and=>[name="&&"],
            not=>[name="!"],
	
            addition=>[name="+"],
            subtraction=>[name="-"],
            multiplication=>[name="*"],
            division=>[name="/"],
	
            equal=>[name="="],
            inequal=>[name="!="],
	
            lower=>[name="<"],
            lowerOrEqual=>[name="<="],
            greaterOrEqual=>[name=">="],
            greater=>[name=">"],
	
            size=>[name="#"],
            in=>[name="in"],
            xyz=>[name="xyz"],
	
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