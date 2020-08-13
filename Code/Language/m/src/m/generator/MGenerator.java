package m.generator;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IFileSystemAccessExtension2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.resource.IResourceDescriptions;

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
	
	@Inject
	IResourceDescriptions descriptions;
	
	public void doGenerate(Resource resource, IFileSystemAccess2 fileSystem, IGeneratorContext context)
	{
		var uri = ((IFileSystemAccessExtension2) fileSystem).getURI("b.m");
		var rest = uri.toFileString();
		var o = uri.toString();
		var path = Paths.get(uri.path());
		var all = descriptions.getAllResourceDescriptions();
		for (var desc : all)
		{
			System.out.println(desc);
		}
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
