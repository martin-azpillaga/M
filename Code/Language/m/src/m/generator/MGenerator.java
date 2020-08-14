package m.generator;

import static m.generator.AccessKind.READ;
import static m.generator.AccessKind.WRITE;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import com.google.inject.Inject;

import m.m.Assignment;
import m.m.BindingBlock;
import m.m.Cell;
import m.m.Function;
import m.validation.MValidator;

public class MGenerator extends AbstractGenerator
{
	@Inject
	MValidator validator;
	
	
	public void doGenerate(Resource resource, IFileSystemAccess2 fileSystem, IGeneratorContext context)
	{
		var game = validator.getGame();
		if (game != null)
		{
			for (var function : game.systems)
			{
				game.queries.put(function, collectQueries(function, game));
			}
			new ClassicUnity().generate(game, fileSystem);
		}
	}
	
	private HashMap<String, HashMap<String,AccessKind>> collectQueries(Function function, Game game)
	{
		var queries = new HashMap<String, HashMap<String,AccessKind>>();
		
		for (var binding : EcoreUtil2.getAllContentsOfType(function, BindingBlock.class))
		{
			var entity = binding.getExpression().getName();
			
			if (!queries.containsKey(entity))
			{
				queries.put(entity, new HashMap<>());
			}
		}
		
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
		if (queries.containsKey(entity))
		{
			var components = queries.get(entity);
			if (!components.containsKey(component) || components.get(component) != WRITE)
			{
				queries.get(entity).put(component,kind);
			}
		}
	}
}
