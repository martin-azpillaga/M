package m.validation;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;

import m.m.Asset;
import m.m.Component;
import m.m.Entity;
import m.m.EntityVariable;
import m.m.Loop;
import m.m.MPackage;
import m.m.Modul;
import m.m.Vector;
import m.modular.Assignment;
import m.modular.Block;
import m.modular.FunctionCall;
import m.modular.ModularPackage;
import m.modular.Procedure;
import m.modular.Variable;

public class MValidator extends AbstractMValidator 
{
	@Check
	public void unique(Component component)
	{
		var entity = (Entity) component.eContainer();
		
		var amount = 0;
		
		for (var c : entity.getComponents())
		{
			var name = c.getName();
			if (component.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated component",MPackage.Literals.COMPONENT__NAME);
		}
	}
	
	@Check
	public void unique(Entity entity)
	{
		var module = (Modul) entity.eContainer();
		
		var amount = 0;
		
		for (var e : module.getEntities())
		{
			var name = e.getName();
			if (entity.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated entity",MPackage.Literals.ENTITY__NAME);
		}
	}
	
	@Check
	public void unique(m.m.System system)
	{
		var module = (Modul) system.eContainer();
		
		var amount = 0;
		
		for (var s : module.getSystems())
		{
			var name = s.getName();
			if (system.getName().equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated procedure",MPackage.Literals.SYSTEM__NAME);
		}
	}
	
	@Check
	public void unique(Procedure procedure)
	{
		var module = (Modul) procedure.eContainer();
		
		var amount = 0;
		
		for (var s : module.getProcedures())
		{
			var name = s.getName();
			if (name.equals(name))
			{
				amount++;
			}
		}
		
		if (amount > 1)
		{
			error("Repeated system",ModularPackage.Literals.PROCEDURE__NAME);
		}
	}
	
	@Check
	public void uniqueArguments(Procedure procedure)
	{
		var arguments = procedure.getArguments();
		
		for (var i = 0; i < arguments.size(); i++)
		{
			for (var j = i+1; j < arguments.size(); j++)
			{
				if (arguments.get(i).equals(arguments.get(j)))
				{
					error("Repeated argument "+arguments.get(i), ModularPackage.Literals.PROCEDURE__NAME);
					return;
				}
			}
		}
	}
	
	@Check
	public void uniqueTags(Loop loop)
	{
		var tags = loop.getTags();
		
		for (var i = 0; i < tags.size(); i++)
		{
			var itag = tags.get(i);
			for (var j = i+1; j < tags.size(); j++)
			{
				if (itag.equals(tags.get(j)))
				{
					error("Repeated tag "+itag, MPackage.Literals.LOOP__ENTITY);
					return;
				}
			}
		}
	}
	
	@Check
	public void uniqueEntity(Loop loop)
	{
		var myEntity = loop.getEntity();
		var container = loop.eContainer();
		EObject current = loop;
		
		while (!(container instanceof Modul))
		{
			if (container instanceof Block)
			{
				var block = (Block) container;
				if (container instanceof Loop)
				{
					var l = (Loop) container;
					if (l.getEntity().equals(myEntity))
					{
						error("Already exists entity " + myEntity + " in the scope", MPackage.Literals.LOOP__ENTITY);
					}
				}
				for (var i = 0; i < block.getStatements().size();i++)
				{
					if (block.getStatements().get(i) == current)
					{
						break;
					}
					else
					{
						var statement = block.getStatements().get(i);
						if (statement instanceof Assignment)
						{
							var assignment = (Assignment) statement;
							var entity = ((EntityVariable)assignment.getVariable()).getEntity();
							if (entity.equals(myEntity))
							{
								error("Already exists entity " + myEntity + " in the scope", MPackage.Literals.LOOP__ENTITY);
							}
						}
					}
				}
			}
			current = container;
			container = container.eContainer();
		}
	}
	@Check
	public void scope(EntityVariable variable)
	{
		var myEntity = variable.getEntity();
		var container = variable.eContainer();
		EObject current = variable;
		
		if (container instanceof Assignment)
		{
			if (((Assignment) container).getVariable() == variable)
			{
				return;
			}
		}
		
		while (!(container instanceof Modul))
		{
			if (container instanceof Block)
			{
				var block = (Block) container;
				if (container instanceof Loop)
				{
					var l = (Loop) container;
					if (l.getEntity().equals(myEntity))
					{
						return;
					}
					else if (l.getTags().contains(myEntity))
					{
						return;
					}
				}
				for (var i = 0; i < block.getStatements().size();i++)
				{
					if (block.getStatements().get(i) == current)
					{
						break;
					}
					else
					{
						var statement = block.getStatements().get(i);
						if (statement instanceof Assignment)
						{
							var assignment = (Assignment) statement;
							var entity = ((EntityVariable)assignment.getVariable()).getEntity();
							if (entity.equals(myEntity))
							{
								return;
							}
						}
					}
				}
			}
			current = container;
			container = container.eContainer();
		}
		error("Entity " + myEntity + " undefined in the scope", MPackage.Literals.ENTITY_VARIABLE__ENTITY);
	}
	
	@Check
	public void exists(FunctionCall call)
	{
		var name = call.getName();
		var functions = new ArrayList<String>();
		functions.add("create");
		functions.add("destroy");
		functions.add("add");
		functions.add("remove");
		functions.add("join");
		functions.add("random");
		functions.add("sin");
		functions.add("cos");
		
		if (!functions.contains(name))
		{
			error("Function " + name + " is not defined", ModularPackage.Literals.FUNCTION_CALL__NAME);
		}
	}
	
	@Check
	public void existsBase(Entity entity)
	{
		var base = entity.getBase();
		if (base != null)
		{
			var module = (Modul) entity.eContainer();
			for (var e : module.getEntities())
			{
				if (e.getName().equals(base))
				{
					return;
				}
			}
			error("The base entity is not declared in this module", MPackage.Literals.ENTITY__BASE);
		}
	}
	
	@Check
	public void acyclic(Entity entity)
	{
		var base = entity.getBase();
		var module = (Modul) entity.eContainer();
		var entities = module.getEntities();
		
		var visited = new ArrayList<String>();
		visited.add(entity.getName());
		
		while (base != null)
		{
			for (var e : entities)
			{
				if (e.getName().equals(base))
				{
					if (visited.contains(e.getName()))
					{
						error("Cyclic base entity dependency", MPackage.Literals.ENTITY__BASE);
						return;
					}
					else
					{
						visited.add(e.getName());
						base = e.getBase();
					}
				}
			}
			return;
		}
	}
	
	@Check
	public void type(Vector vector)
	{
		if (vector.getEntries().size() > 4)
		{
			error("Vectors can have up to four entries", MPackage.Literals.VECTOR__ENTRIES,"More than four entries");
		}
	}
	
	@Check
	public void type(Component component)
	{
		var componentName = component.getName();
		var value = component.getValue();
		if (value == null) return;
		
		try
		{
			var standard = StandardLibrary.valueOf(componentName);
			var type = standard.getType();
			switch (type)
			{
			case Asset:
				if (value instanceof Vector)
				{
					error(standard + " is an engine component, only accepts assets as value",MPackage.Literals.COMPONENT__NAME);
				}
				break;
			case Float1:
				if (value instanceof Asset || ((Vector)value).getEntries().size() != 1)
				{
					error(standard + " is an engine component, only accepts vectors with one entry", MPackage.Literals.COMPONENT__NAME);
				}
				break;
			case Float2:
				if (value instanceof Asset || ((Vector)value).getEntries().size() != 2)
				{
					error(standard + " is an engine component, only accepts vectors with two entries", MPackage.Literals.COMPONENT__NAME);
				}
				break;
			case Float3:
				if (value instanceof Asset || ((Vector)value).getEntries().size() != 3)
				{
					error(standard + " is an engine component, only accepts vectors with three entries", MPackage.Literals.COMPONENT__NAME);
				}
				break;
			case Float4:
				if (value instanceof Asset || ((Vector)value).getEntries().size() != 4)
				{
					error(standard + " is an engine component, only accepts vectors with four entries", MPackage.Literals.COMPONENT__NAME);
				}
				break;
			case Tag:
				error(standard + " is a tag engine component, it does not accept values", MPackage.Literals.COMPONENT__NAME);			
			}
		}
		catch (Exception exception)
		{
			
		}
	}
	
	
}
