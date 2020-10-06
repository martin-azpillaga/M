package m.scoping;

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
	public static Type world;
	public static Type entityList;
	public static Type tag;
	public static Type bool;
	public static Type type;
	public static Type any;
	public static Type none;
	
	public static Function has;
	public static Function create;
	public static Function delete;
	public static Function add;
	public static Function remove;
	public static Function enable;
	public static Function disable;
	public static Function load;
	public static Function unload;
	
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
		world = type("SubScene");
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
		game.getTypes().add(world);
		game.getTypes().add(entityList);
		game.getTypes().add(bool);
		game.getTypes().add(tag);
		game.getTypes().add(type);
		game.getTypes().add(any);
		game.getTypes().add(none);
		
		has = function("active", type(function, new Type[] {any,bool}));
		add = function("activate", type(function, new Type[] {any, none}));
		remove = function("deactivate", type(function, new Type[] {any, none}));
		create = function("copy", type(function, new Type[] {entity, none}));
		delete = function("delete", type(function, new Type[] {entity,none}));
		load = function("load", type(function, new Type[] {world, none}));
		unload = function("unload", type(function, new Type[] {world, none}));
		enable = function("enable", type(function, new Type[] {type, none}));
		disable = function("disable", type(function, new Type[] {type, none}));
		
		game.getComponents().add(component(float1, "mass"));
		
		game.getFunctions().add(has);
		game.getFunctions().add(add);
		game.getFunctions().add(remove);
		game.getFunctions().add(create);
		game.getFunctions().add(delete);
		game.getFunctions().add(load);
		game.getFunctions().add(unload);
		game.getFunctions().add(enable);
		game.getFunctions().add(disable);
	}
}
