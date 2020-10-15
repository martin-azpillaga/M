package m.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class Configuration
{
	String root;
	public String Unity;
	public String Unreal;
	public String Godot;

	Gson gson;

	public Configuration(String root)
	{
		this.Unity = parsePath("./Output/Unity", root);
		this.Unreal = parsePath("./Output/Unreal", root);
		this.Godot = parsePath("./Output/Godot", root);
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
