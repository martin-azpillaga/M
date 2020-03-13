package m.serializing;

import m.game.*;

public class GameHelper
{
	static GameFactory game = GameFactory.eINSTANCE;
	
	public static Game game()
	{
		return game.createGame();
	}
	
	/*
	
	public static Type type(String name)
	{
		var type = game.createType();
		type.setName(name);
		return type;
	}
	
	public static Type type(String name, Type[] parameters)
	{
		var type = game.createType();
		type.setName(name);
		for (var parameter : parameters)
		{
			type.getParameters().add(parameter);
		}
		return type;
	}
	
	public static Type type(String name, Type[] parameters, boolean isValueType)
	{
		var type = game.createType();
		type.setName(name);
		for (var parameter : parameters)
		{
			type.getParameters().add(parameter);
		}
		type.setValueType(isValueType);
		return type;
	}
	
	public static Type type(String namespace, String name, Type[] parameters, boolean isValueType)
	{
		var type = game.createType();
		type.setNamespace(namespace);
		type.setName(name);
		for (var parameter : parameters)
		{
			type.getParameters().add(parameter);
		}
		type.setValueType(isValueType);
		return type;
	}
	
	public static ComponentData component(Type type, String name)
	{
		var component = game.createComponentData();
		component.setName(name);
		component.setType(type);
		return component;
	}
	
	public static Forall forall(String variable, Expression condition)
	{
		var forall = game.createForall();
		forall.setVariable(variable(variable));
		forall.setCondition(condition);
		return forall;
	}
	
	public static Variable variable(String name)
	{
		var variable = game.createVariable();
		variable.setName(name);
		return variable;
	}
	
	public static Call call(String name, Expression[] arguments)
	{
		var call = game.createCall();
		call.setName(name);
		for (var argument : arguments)
		{
			call.getArguments().add(argument);
		}
		return call;
	}
	
	public static Function function(String name, Type type)
	{
		var function = game.createFunction();
		function.setName(name);
		function.setType(type);
		return function;
	}*/
}
