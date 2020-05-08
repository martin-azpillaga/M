package m.validation

import java.util.List
import static m.validation.StandardLibrary.*

class NaturalLanguage
{
	public List<Symbol> symbols
	
	public static val English = new NaturalLanguage=>
	[
		symbols = #
		[
			mass=>[name='mass'],
			velocity=>[name='velocity'],
			has=>[name='has']
		].map[it=>[reason='standard library']]
	]
}