package m.validation;

import game.Function;
import game.Game;
import game.Type;
import static m.serializing.GameHelper.*;

public class StandardLibrary
{
	public static final String function = "Function";
	public static final String array = "DynamicBuffer";

	static Game game;
	public static Type float1;
	public static Type float2;
	public static Type float3;
	public static Type float4;
	public static Type entity;
	public static Type entityList;
	public static Type tag;
	public static Type bool;
	public static Type type;
	public static Type any;
	public static Type none;
	
	public static Function has;
	public static Function delete;
	
	public StandardLibrary()
	{
		english();
	}
	
	public static Game getGame()
	{
		english();
		return game;
	}
	
	private static void english()
	{
		tag = type("tag");
		bool = type("bool");
		float1 = type("float");
		float2 = type("float2");
		float3 = type("float3");
		float4 = type("float4");
		entity = type("entity");
		entityList = type(array, new Type[] {entity});
		type = type("Type");
		any = type("Object");
		none = type("void");
		
		game = game();
		game.getTypes().add(float1);
		game.getTypes().add(float2);
		game.getTypes().add(float3);
		game.getTypes().add(float4);
		game.getTypes().add(entity);
		game.getTypes().add(entityList);
		game.getTypes().add(bool);
		game.getTypes().add(tag);
		game.getTypes().add(type);
		game.getTypes().add(any);
		game.getTypes().add(none);
		
		has = function("has", type(function, new Type[] {type,entity,bool}));
		delete = function("delete", type(function, new Type[] {entity,none}));
		
		game.getComponents().add(component(float1, "mass"));
		
		game.getFunctions().add(has);
		game.getFunctions().add(delete);
	}
}
