package m.validation.global;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.Library;
import m.model.Game;
import m.validation.Problem;

public class GlobalData
{
	public final Game game;
	public final Map<String,List<Problem>> problems;
	public Set<String> modifiedFiles;

	public GlobalData()
	{
		this.game = new Game(Library.ENGLISH);
		this.problems = new HashMap<>();
		this.modifiedFiles = new HashSet<>();
	}
}
