package m.validation.global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.library.Library;
import m.model.Game;
import m.validation.Problem;

public class GlobalData
{
	public final Game game;
	public final Map<String,List<Problem>> problems;

	public GlobalData()
	{
		this.game = new Game(Library.ENGLISH);
		this.problems = new HashMap<>();
	}
}
