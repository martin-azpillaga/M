package m.model;

import com.google.gson.Gson;

public class Configuration
{
	public String Unity;
	public String Unreal;
	public String Godot;

	Gson gson;

	public Configuration()
	{
		this.Unity = "./Output/Unity";
		this.Unreal = "./Output/Unreal";
		this.Godot = "./Output/Godot";
		this.gson = new Gson();
	}

	public void parse(String json, String root)
	{
		var configuration = gson.fromJson(json, getClass());
		this.Unity = parsePath(configuration.Unity, root);
		this.Unreal = parsePath(configuration.Unreal, root);
		this.Godot = parsePath(configuration.Godot, root);
	}

	private String parsePath(String path, String root)
	{
		if (path == null)
		{
			return null;
		}
		if (path.startsWith("."))
		{
			path = path.replace(".", root);
		}
		return path;
	}
}
