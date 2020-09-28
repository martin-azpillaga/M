package m.generator;

public enum Engine
{
	UNITY("Unity"), UNREAL("Unreal"), GODOT("Godot");

	String name;

	Engine(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
