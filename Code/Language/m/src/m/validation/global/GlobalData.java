package m.validation.global;

import java.util.List;
import java.util.Map;

import m.model.Game;
import m.validation.Problem;

public class GlobalData
{
	public final Game game;
	public final Map<String,List<Problem>> problems;

	public GlobalData(Game game, Map<String,List<Problem>> problems)
	{
		this.game = game;
		this.problems = problems;
	}
}
