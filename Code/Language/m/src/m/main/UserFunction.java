package m.main;

import m.m.Assignment;
import m.m.BindingBlock;
import m.m.Cell;
import m.m.Function;
import m.m.impl.FunctionImpl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.xtext.EcoreUtil2;

import m.library.types.FunctionType;

public class UserFunction extends FunctionImpl
{
    public final FunctionType type;
    public final Map<String,HashMap<String,Boolean>> queries;

    public UserFunction(Function function, FunctionType type)
    {
        this.name = function.getName();
        this.parameters = function.getParameters();
        this.statements = function.getStatements();
        this.type = type;
        this.queries = collectQueries();
	}
	
    private HashMap<String,HashMap<String,Boolean>> collectQueries()
	{
		var queries = new HashMap<String,HashMap<String,Boolean>>();
		
		for (var binding : EcoreUtil2.getAllContentsOfType(this, BindingBlock.class))
		{
			var entity = binding.getExpression().getName();
			
			if (!queries.containsKey(entity))
			{
				queries.put(entity, new HashMap<>());
			}
		}
		
		for (var cell : EcoreUtil2.getAllContentsOfType(this,Cell.class))
		{
			var name = cell.getEntity().getName();
			if (cell.getComponent() == null)
			{
				continue;
			}
			
			var component = cell.getComponent().getName();
			var container = cell.eContainer();
			
			if (container instanceof Assignment)
			{
				var assignment = (Assignment) container;
				if (cell == assignment.getAtom())
				{
					setComponentAccess(name, component, true);
				}
				else
				{
					setComponentAccess(name, component, false);
				}
			}
			else
			{
				setComponentAccess(name, component, false);
			}
		}

		return queries;
	}
	
	private void setComponentAccess(String entity, String component, boolean writeAccess)
	{
		if (queries.containsKey(entity))
		{
			var components = queries.get(entity);
			if (!components.containsKey(component) || !components.get(component))
			{
				queries.get(entity).put(component, writeAccess);
			}
		}
	}
}
