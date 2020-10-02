package m.generator;

public enum Engine
{
	UNITY("Unity"), UNREAL("Unreal"), GODOT("Godot");

	public final String identifier;

	Engine(String name)
	{
		this.identifier = name;
	}
}
