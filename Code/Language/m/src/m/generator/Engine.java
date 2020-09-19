package m.generator;

public enum Engine
{
	Unity("Unity"), Unreal("Unreal"), Godot("Godot");

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
