package games.minim.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import com.google.inject.Guice
import games.minim.XMRuntimeModule
import org.eclipse.xtext.serializer.impl.Serializer
import games.minim.m.Game
import org.eclipse.xtext.xbase.lib.Functions.Function0

class XMGenerator extends AbstractGenerator {

	int x = -100
	int id = 0
	
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{	
		x = -100
		id = 0
		var injector = Guice.createInjector(new  XMRuntimeModule());  
		var serializer = injector.getInstance(Serializer);  
		var s = serializer.serialize(resource.contents.get(0) as Game);  
		s = s.replaceNext()
		s = s.replaceOld('type="entity" id=""', ['''type="entity" id="" x="«x()»" y="0"'''])
		x=-100
		s = s.replaceOld('type="system" id=""', ['''type="system" id="" x="«x()»" y="500"'''])
		s = s.replaceOld('id=""',['''id="«id()»"'''])
		s = s.replace('> ', '>')
		s = s.replace(' <', '<')
		fsa.generateFile('generated.xml',s)
		println("Generated")
	}
	
	def id()
	{
		id++
		return id
	}
	
	def x()
	{
		x+= 100
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
	    var result = new StringBuffer();
	    //startIdx and idxOld delimit various chunks of aInput; these
	    //chunks always end where aOldPattern begins
	    var startIdx = 0;
	    var idxOld = 0;
	    var oldPattern = "<statement"
	    var endPattern = "</statement>";
	    var nextPattern = "<next>"
	    while ((idxOld = input.indexOf(oldPattern, startIdx)) >= 0) 
	    {
	    	var endIdx = input.indexOf(endPattern, startIdx)
	    	var statements = input.substring(idxOld, endIdx)
	    	
	    	var index = 0
	    	var times = 0
	    	var length = nextPattern.length();
	        while( (index = statements.indexOf(nextPattern, index)) != -1 ) {                
	                index += length; 
	                times++;
	        }
	        //grab a part of aInput which does not include aOldPattern
	        result.append( input.substring(startIdx, endIdx) );
	        
	        for (var i = 0; i < times; i++)
	        {
	        	result.append('</next></block>')
	        }
	        
	        result.append( endPattern )
	        
	        //reset the startIdx to just after the current match, to see
	        //if there are any further matches
	        startIdx = endIdx+endPattern.length;
     	}
	     //the final chunk will go to the end of aInput
	     result.append( input.substring(startIdx) );
	     return result.toString();
  	}
}
