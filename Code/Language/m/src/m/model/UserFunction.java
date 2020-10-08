package m.model;

import m.model.impl.FunctionImpl;

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
		var result = new HashMap<String,HashMap<String,Boolean>>();

		for (var binding : EcoreUtil2.getAllContentsOfType(this, BindingBlock.class))
		{
			var entity = binding.getExpression().getName();

			if (!result.containsKey(entity))
			{
				result.put(entity, new HashMap<>());
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
				setComponentAccess(result, name, component, cell == assignment.getAtom());
			}
			else
			{
				setComponentAccess(result, name, component, false);
			}
		}

		return result;
	}

	private void setComponentAccess(HashMap<String,HashMap<String,Boolean>> result, String entity, String component, boolean writeAccess)
	{
		if (result.containsKey(entity))
		{
			var components = result.get(entity);
			if (!components.containsKey(component) || !components.get(component))
			{
				result.get(entity).put(component, writeAccess);
			}
		}
	}
}
