package m.generator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import com.google.inject.Inject;

import m.m.Assignment;
import m.m.Cell;
import m.m.Function;
import m.validation.MValidator;
import static m.generator.AccessKind.*;

public class MGenerator extends AbstractGenerator
{
	@Inject
	MValidator validator;
	
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context)
	{
		var game = validator.getGame();
		if (game != null)
		{
			var queries = new HashMap<Function, HashMap<String, HashMap<String, AccessKind>>>();
			for (var function : game.functions.keySet())
			{
				queries.put(function, collectQueries(function));
			}
			new Unity().generate(game, queries, fsa);
		}
	}
	
	private HashMap<String, HashMap<String,AccessKind>> collectQueries(Function function)
	{
		var queries = new HashMap<String, HashMap<String,AccessKind>>();
		
		for (var cell : EcoreUtil2.getAllContentsOfType(function,Cell.class))
		{
			var name = cell.getEntity().getName();
			var component = cell.getComponent().getName();
			var container = cell.eContainer();
			
			if (container instanceof Assignment)
			{
				var assignment = (Assignment) container;
				if (cell == assignment.getAtom())
				{
					setComponentAccess(queries, name, component, WRITE);
				}
				else
				{
					setComponentAccess(queries, name, component, READ);
				}
			}
			else
			{
				setComponentAccess(queries, name, component, READ);
			}
		}
		
		return queries;
	}
	
	private void setComponentAccess(Map<String, HashMap<String,AccessKind>> queries, String entity, String component, AccessKind kind)
	{
		if (!queries.containsKey(entity))
		{
			queries.put(entity, new HashMap<>());
		}
		var components = queries.get(entity);
		if (!components.containsKey(component) || components.get(component) != WRITE)
		{
			queries.get(entity).put(component,kind);
		}
	}
}
