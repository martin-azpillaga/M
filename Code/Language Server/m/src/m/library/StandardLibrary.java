package m.library;

import game.Game;
import game.GameFactory;
import game.Type;
import static m.serializing.GameHelper.*;

public class StandardLibrary
{
	GameFactory factory = GameFactory.eINSTANCE;
	public Game game;
	
	public StandardLibrary()
	{
		english();
	}
	
	private void english()
	{
		var float1 = type("float");
		var float2 = type("float2");
		var float3 = type("float3");
		var float4 = type("float4");
		var entity = type("entity");
		var entityList = type("DynamicBuffer", new Type[] {entity});
		
		game = game();
		game.getTypes().add(float1);
		game.getTypes().add(float2);
		game.getTypes().add(float3);
		game.getTypes().add(float4);
		game.getTypes().add(entity);
		game.getTypes().add(entityList);
		
		game.getComponents().add(component(float1, "mass"));
		
	}
}
