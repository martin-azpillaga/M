package m.generator;

import m.library.Library;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.model.Function;
import m.model.Game;
import static m.generator.IO.*;

public class Godot
{
	Library library;

	public void generate(Game game)
	{
		this.library = game.library;

		game.components.entrySet().removeIf(e-> library.getComponent(e.getKey()) != null);
		game.components.forEach((name, type)->
		{
			writeFile("Components/"+unreserved(name)+".gd",
			generateComponent(name,type));
		});

		for (var system : game.functions)
		{
			if (system.type == FunctionType.systemType)
			{
				var name = system.getName();

				writeFile("Systems/"+unreserved(name)+".gd", generateSystem(system));
			}
		}
	}

	private String generateComponent(String name, Type type)
	{
		return write
		(
			"extends Node",
			"",
			"export var Value : "+godot(type),
			"",
			"func get_class(): return \""+unreserved(name)+"\"",
			"func is_class(type): return type == \""+unreserved(name)+"\" || .is_class(type)"
		);
	}

	private String generateSystem(Function system)
	{
		return "";
	}

	private String unreserved(String name)
	{
		return name;
	}

	private String godot(Type type)
	{
		if (type instanceof AtomicType)
		{
			var atomic = (AtomicType) type;
			switch (atomic)
			{
			case ENTITY:
				return "NodePath";
			case NUMBER:
				return "float";
			case NUMBER2:
				return "Vector2";
			case NUMBER3:
				return "Vector3";
			case PROPOSITION:
				return "bool";
			case ENTITY_LIST:
				return "Array";
			case INPUT:
				return "String";
			case STRING:
				return "String";
			case COLOR:
				return "Color";
			case MESH:
				return "MeshInstance";
			case MATERIAL:
				return "Material";
			}
			return "Undefined";
		}
		return "Undefined";
	}
}
