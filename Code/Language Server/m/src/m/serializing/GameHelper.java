package m.serializing;

import game.ComponentData;
import game.Game;
import game.GameFactory;
import game.Type;

public class GameHelper
{
	static GameFactory game = GameFactory.eINSTANCE;
	
	public static Game game()
	{
		return game.createGame();
	}
	
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
	
	public static ComponentData component(Type type, String name)
	{
		var component = game.createComponentData();
		component.setName(name);
		component.setType(type);
		return component;
	}
}
