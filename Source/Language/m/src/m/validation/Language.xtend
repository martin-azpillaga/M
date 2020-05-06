package m.library

import java.util.Map
import m.model.Function
import m.model.Component
import m.model.Type
import m.model.TypingReason
import m.model.GroupingReason
import m.model.Error
import static m.library.StandardLibrary.*
import static m.model.Error.*
import static m.model.GroupingReason.*
import static m.model.TypingReason.*
import m.model.Block

class Language
{
	public Map<Function,String> function
	public Map<Component,String> component
	public Map<Type,String> type
	public Map<Block,String> block
	public Map<Error,String> error
	public Map<GroupingReason,String> groupingReason
	public Map<TypingReason, String> typingReason
	
	
	public static final Language English = new Language =>
	[
		function = #
		{
			addition -> '+',
			subtraction -> '-',
			multiplication -> '*',
			division -> '/',
			lower -> '<',
			lowerOrEqual -> '<=',
			greaterOrEqual -> '>=',
			greater -> '>',
			or -> '||',
			and -> '&&',
			not -> '!',
			equal -> '==',
			inequal -> '!=',
			size -> '#',
			in -> '_in_',
			random -> 'random',
			sin -> 'sin',
			dot -> 'dot',
			halt -> 'halt',
			has -> 'has'
		}
		
		component = #
		{
			inputTriggered -> 'inputTriggered',
			inputValue -> 'inputValue',
			inputVector -> 'inputVector',
			
			timer -> 'timer',
			elapsed -> 'elapsed',
			timeout -> 'timeout',
			
			position -> 'position',
			rotation -> 'rotation',
			scale -> 'scale',
			worldPosition -> 'worldPosition',
			worldRotation -> 'worldRotation',
			worldScale -> 'worldScale',
			parent -> 'parent',
			children -> 'children',
			
			mesh -> 'mesh',
			material -> 'material',
			
			font -> 'font',
			text -> 'text',
			image -> 'image',
			button -> 'button',
			numberLabel -> 'numberLabel',
			
			viewAngle -> 'viewAngle',
			viewDistance -> 'viewDistance',
			clearColor -> 'clearColor',
			perspective -> 'perspective',
			
			emission -> 'emission',
			spotAngle -> 'spotAngle',
			range -> 'range',
			intensity -> 'intensity',
			shadows -> 'shadows',
			cookie -> 'cookie',
			
			audioClip -> 'audioClip',
			volume -> 'volume',
			pitch -> 'pitch',
			loop -> 'loop',
			
			distortion -> 'distortion',
			echo -> 'echo',
			highPass -> 'highPass',
			lowPass -> 'lowPass',
			reverb -> 'reverb',
			chorus -> 'chorus',
			doppler -> 'doppler',
			
			ip -> 'ip',
			port -> 'port',
			networkStream -> 'networkStream',
			prediction -> 'prediction',
			owner -> 'owner',
			
			mass -> 'mass',
			velocity -> 'velocity',
			acceleration -> 'acceleration',
			force -> 'force',
			damping -> 'damping',
			angularDamping -> 'angularDamping',
			angularVelocity -> 'angularVelocity',
			gravityFactor -> 'angularVelocity',
			
			trigger -> 'trigger',
			extents -> 'extents',
			radius -> 'radius',
			height -> 'height',
			sideCount -> 'sideCount',
			convexHull -> 'convexHull',
			geometry -> 'geometry',
			restitution -> 'restitution',
			friction -> 'friction'
		}
		type = #
		{
			number -> 'number',
			number2 -> 'number[2]',
			number3 -> 'number[3]',
			entity -> 'entity',
			proposition -> 'proposition',
			empty -> 'empty'
		}
		block = #
		{
			selection -> 'if',
			iteration -> 'while',
			foreach -> 'foreach'
		}
		error = #
		{
			SYNTAX -> 'syntax error',
			REDEFINITION -> '%s is already defined',
			UNDEFINED -> '%s is undefined',
			UNDECIDABLE -> 'undecidable type',
			INCOMPATIBLE -> 'incompatible types',
			GROUP -> 'Grouped because %s',
			TYPE -> 'Typing error: %s',
			NO_RESULT -> 'Function has no result'
		}
		groupingReason = #
		{
			ASSIGNMENT -> 'assigned together',
			SAME_VARIABLE -> 'same variable',
			PARAMETER_ARGUMENT -> 'argument and parameter link',
			RESULT -> 'result of function',
			SAME_COMPONENT -> 'same component'
		}
		typingReason = #
		{
			STANDARD_COMPONENT -> 'standard component',
			STANDARD_FUNCTION -> 'standard function',
			STANDARD_CONTROL -> 'standard control',
			CELL_ENTITY -> 'cell entity',
			CONTROL_ENTITY-> 'control entity'
		}
	]
	
	public static final Language Castellano = new Language =>
	[
		function = #
		{
			addition -> '+',
			multiplication -> '*',
			random -> 'aleatorio'
		}
		
		component = #
		{
			velocity -> 'velocidad',
			mass -> 'masa'
		}
		type = #
		{
			number -> 'numero',
			number3 -> 'numero[3]'
		}
		block = #
		{
			selection -> 'si',
			iteration -> 'mientras',
			foreach -> 'porcada'
		}
	]
	
	public static final Language Euskara = new Language =>
	[
		function = #
		{
			addition -> '_gehi_',
			multiplication -> '_bider_',
			random -> 'zoriz'
		}
		component = #
		{
			velocity -> 'abiadura',
			mass -> 'masa'
		}
		type = #
		{
			number -> 'zenbakia',
			number3 -> 'hiru zenbaki'
		}
		block = #
		{
			selection -> 'baldineta',
			iteration -> 'bitartean',
			foreach -> 'hartu'
		}
	]
}