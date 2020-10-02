package m.generator;

public enum Engine
{
	UNITY("Unity"), UNREAL("Unreal"), GODOT("Godot");

	public final String name;

	Engine(String name)
	{
		this.name = name;
	}
}
