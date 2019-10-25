package m.transformation;

import java.util.ArrayList;


public enum BuiltInLibrary
{
	position, rotation, scale;
	
	public String english()
	{
		switch (this)
		{
			case position:
			{
				return "position";
			}
			case rotation:
			{
				return "rotation";
			}
			default: return this.toString();
		}
	}
	
	public String euskara()
	{
		switch (this)
		{
			case position:
			{
				return "kokapena";
			}
			case rotation:
			{
				return "biraketa";
			}
			default: return this.toString();
		}
	}
	
	public static ArrayList<BuiltInLibrary> renderers()
	{
		ArrayList<BuiltInLibrary> result = new ArrayList<BuiltInLibrary>();
		
		result.add(position);
		
		return result;
	}
}