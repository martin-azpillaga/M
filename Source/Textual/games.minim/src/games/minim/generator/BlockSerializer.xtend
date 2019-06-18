package games.minim.generator

import com.google.inject.Guice
import games.minim.XMRuntimeModule
import games.minim.m.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.serializer.impl.Serializer
import org.eclipse.xtext.xbase.lib.Functions.Function0
import java.util.Stack

class BlockSerializer implements Framework
{
	int x = -100
	int id = 0
	Stack<Integer> stack
	
	def void doGenerate(Game game, IFileSystemAccess2 fsa, String name)
	{	
		x = -400
		id = 0
		var injector = Guice.createInjector(new  XMRuntimeModule());  
		var serializer = injector.getInstance(Serializer);  
		var s = serializer.serialize(game);  
		println(s)
		s = s.replaceNext()
		s = s.replaceOld('xhtml"> <block type="entity" id=""', ['''xhtml"> <block type="entity" id="" x="«x()»" y="0"'''])
		s = s.replaceOld('</block> <block type="entity" id=""', ['''</block> <block type="entity" id="" x="«x()»" y="0"'''])
		x=-400
		s = s.replaceOld('type="system" id=""', ['''type="system" id="" x="«x()»" y="500"'''])
		s = s.replaceOld('id=""',['''id="«id()»"'''])
		s = s.replace('\t', '')
		s = s.replace('\n', '')
		s = s.replace('\r', '')
		s = s.replace(' . ', '.')
		s = s.replace('>  ', '>')
		s = s.replace('> ', '>')
		s = s.replace('  <', '<')
		s = s.replace(' <', '<')
		fsa.generateFile('Blocks/'+name+'.xml',s)
		println("Generated")
	}
	
	def id()
	{
		id++
		return id
	}
	
	def x()
	{
		x+= 400
		return x
	}
	
	def replaceOld(String input, String oldPattern, Function0<String> f)
	{
	    var result = new StringBuffer();
	    //startIdx and idxOld delimit various chunks of aInput; these
	    //chunks always end where aOldPattern begins
	    var startIdx = 0;
	    var idxOld = 0;
	    while ((idxOld = input.indexOf(oldPattern, startIdx)) >= 0) 
	    {
	        //grab a part of aInput which does not include aOldPattern
	        result.append( input.substring(startIdx, idxOld) );
	        //add aNewPattern to take place of aOldPattern
	        result.append( f.apply );
	
	        //reset the startIdx to just after the current match, to see
	        //if there are any further matches
	        startIdx = idxOld + oldPattern.length();
     	}
	     //the final chunk will go to the end of aInput
	     result.append( input.substring(startIdx) );
	     return result.toString();
  	}
  	
  	def replaceNext(String input)
	{
		stack = new Stack<Integer>
	    var result = new StringBuffer();
	    //startIdx and idxOld delimit various chunks of aInput; these
	    //chunks always end where aOldPattern begins
	    var startIdx = 0
	    var openIndex = 0
	    var closeIndex = 0
	    var nextIndex = 0
	    var openStatement = "<statement"
	    var closeStatement = "</statement>";
	    var openNext = "<next>"
	    while ((closeIndex = input.indexOf(closeStatement, startIdx)) >= 0) 
	    {
	    	openIndex = input.indexOf(openStatement, startIdx)
	    	nextIndex = input.indexOf(openNext, startIdx)
	    	var min = min(openIndex,closeIndex,nextIndex)
	    	if (min === openIndex)
	    	{
	    		result.append(input.substring(startIdx, openIndex+openStatement.length))
	    		stack.push(0)
	    		startIdx = openIndex+openStatement.length
	    	}
	    	else if (min === closeIndex)
	    	{
	    		result.append( input.substring(startIdx, closeIndex) )
	    		var times = stack.pop()
	    		for (var i = 0; i < times; i++)
		        {
		        	result.append('</next></block>')
		        }
		        result.append( closeStatement )
		        startIdx = closeIndex+closeStatement.length;
	    	}
	    	else if (min === nextIndex)
	    	{
	    		result.append(input.substring(startIdx, nextIndex + openNext.length))
	    		startIdx = nextIndex + openNext.length
	    		var current = stack.pop()
	    		current++
	    		stack.push(current)
	    	}	        
     	}
	     //the final chunk will go to the end of aInput
	     result.append( input.substring(startIdx) );
	     return result.toString();
  	}
  	
	override represent(Game game, IFileSystemAccess2 fileSystem) 
	{
		var file = game.eResource.URI.path
		var projectPath = file.substring(9)
		var name = projectPath.substring(projectPath.indexOf('/')+1, projectPath.indexOf('.'))
		
		doGenerate(game, fileSystem, name)
	}
	
	def min(int a, int b, int c)
	{
		#[a,b,c].filter[it >= 0].reduce[p1, p2|Math.min(p1,p2)]
	}
}