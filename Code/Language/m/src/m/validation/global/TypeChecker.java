package m.validation.global;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.Library;
import m.library.types.Type;
import m.model.Cell;
import m.model.Game;
import m.validation.Problem;
import m.validation.Problem.Severity;
import m.validation.local.ExpressionNode;

public class TypeChecker
{
	Map<String, Set<Cluster>> fileToClusters;
	Map<String, Cluster> componentToCluster; // acceleration structure


	public TypeChecker()
	{
		this.fileToClusters = new HashMap<>();
		this.componentToCluster = new HashMap<>();
	}

	public Result validate(String file, Set<ExpressionNode> localData)
	{
		return new Result();
	}

	private void invalidateObsoleteMemory(String modifiedFile)
	{
		var affectedNodes = new ArrayList<ExpressionNode>();

		var clusters = fileToClusters.get(modifiedFile);

		if (clusters == null)
		{
			return;
		}

		for (var cluster : clusters)
		{
			cluster.fileToTypes.remove(modifiedFile);
			cluster.fileToNodes.remove(modifiedFile);

			if (cluster.fileToNodes.isEmpty())
			{
				for (var component : cluster.componentToFiles.keySet())
				{
					componentToCluster.remove(component);
				}
			}
			else
			{
				for (var entry : cluster.componentToFiles.entrySet())
				{
					var component = entry.getKey();
					var files = entry.getValue();

					files.remove(modifiedFile);

					if (files.isEmpty())
					{
						componentToCluster.remove(component);
					}
				}

				for (var node : cluster.fileToNodes.values())
				{
					affectedNodes.add(node);
				}
			}
		}

		fileToClusters.remove(modifiedFile);
	}

	private void validate(Set<ExpressionNode> connectedComponents, String modifiedFile)
	{
		var stack = new ArrayDeque<ExpressionNode>();
		var visited = new HashSet<ExpressionNode>();

		for (var rootNode : connectedComponents)
		{
			stack.push(rootNode);
			var cluster = new Cluster();
			cluster.fileToNodes.put(modifiedFile, rootNode);

			while (!stack.isEmpty())
			{
				var node = stack.pop();

				visited.add(node);

				cluster = validate(node, cluster, modifiedFile);

				for (var binding : node.bindings)
				{
					var boundNode = binding.node;
					if (!visited.contains(boundNode))
					{
						stack.push(boundNode);
					}
				}
			}

			var clusters = fileToClusters.get(modifiedFile);
			if (clusters == null)
			{
				clusters = new HashSet<Cluster>();
				fileToClusters.put(modifiedFile, clusters);
			}
			clusters.add(cluster);
		}
	}

	private Cluster validate(ExpressionNode node, Cluster cluster, String modifiedFile)
	{
		var expression = node.expression;
		if (expression instanceof Cell)
		{
			var cell = (Cell) expression;
			if (cell.getComponent() != null)
			{
				var component = cell.getComponent().getName();

				var componentFiles = cluster.componentToFiles.get(component);
				if (componentFiles == null)
				{
					componentFiles = new HashSet<String>();
					cluster.componentToFiles.put(component, componentFiles);
				}
				componentFiles.add(modifiedFile);


				var definitionCluster = componentToCluster.get(component);
				if (definitionCluster != null)
				{
					cluster = merge(cluster, definitionCluster);
				}
				else
				{
					componentToCluster.put(component, cluster);
				}
			}
		}
		for (var typing : node.typings)
		{
			var types = cluster.fileToTypes.get(modifiedFile);
			if (types == null)
			{
				types = new HashSet<Type>();

				cluster.fileToTypes.put(modifiedFile, types);
			}
			types.add(typing.type);
		}
		return cluster;
	}

	private Cluster merge(Cluster cluster, Cluster definitionCluster)
	{
		for (var entry : cluster.fileToTypes.entrySet())
		{
			var file = entry.getKey();
			var types = entry.getValue();

			var definitionClusterTypes = definitionCluster.fileToTypes.get(file);
			if (definitionClusterTypes == null)
			{
				definitionCluster.fileToTypes.put(file, types);
			}
			else
			{
				definitionClusterTypes.addAll(types);
			}
		}
		for (var entry : cluster.fileToNodes.entrySet())
		{
			var file = entry.getKey();
			var entryNode = entry.getValue();

			definitionCluster.fileToNodes.put(file, entryNode);
		}
		for (var entry : cluster.componentToFiles.entrySet())
		{
			var entryComponent = entry.getKey();
			var files = entry.getValue();

			componentToCluster.put(entryComponent, definitionCluster);

			var definitionFiles = definitionCluster.componentToFiles.get(entryComponent);
			if (definitionFiles == null)
			{
				definitionCluster.componentToFiles.put(entryComponent, files);
			}
			else
			{
				definitionFiles.addAll(files);
			}
		}
		return definitionCluster;
	}

	private Map<String,List<Problem>> checkTypes()
	{
		var problems = new HashMap<String,List<Problem>>();

		var game = new Game(Library.ENGLISH);

		for (var entry : componentToCluster.entrySet())
		{
			var component = entry.getKey();
			var cluster = entry.getValue();

			var types = new HashSet<Type>();

			for (var typeSet : cluster.fileToTypes.values())
			{
				types.addAll(typeSet);
			}

			if (types.isEmpty())
			{
				for (var nodeEntry : cluster.fileToNodes.entrySet())
				{
					var file = nodeEntry.getKey();
					var rootNode = nodeEntry.getValue();

					var stack = new ArrayDeque<ExpressionNode>();
					var visited = new HashSet<ExpressionNode>();

					stack.push(rootNode);

					while (!stack.isEmpty())
					{
						var node = stack.pop();

						visited.add(node);

						if (!problems.containsKey(file))
						{
							problems.put(file, new ArrayList<Problem>());
						}
						problems.get(file).add(new Problem(node.expression, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.UNDECIDABLE_TYPE)));

						for (var binding : node.bindings)
						{
							var boundNode = binding.node;
							if (!visited.contains(boundNode))
							{
								stack.push(boundNode);
							}
						}
					}
				}
			}
			else if (types.size() == 1)
			{
				game.components.put(component, types.iterator().next());
			}
			else
			{
				for (var nodeEntry : cluster.fileToNodes.entrySet())
				{
					var file = nodeEntry.getKey();
					var node = nodeEntry.getValue();

					if (!problems.containsKey(file))
					{
						problems.put(file, new ArrayList<Problem>());
					}
					problems.get(file).add(new Problem(node.expression, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.INCOMPATIBLE_TYPES)));
				}
			}
		}

		//var data = new GlobalData(game, problems);

		return problems;
	}

	public static class Result
	{
		public final Map<String,Type> newComponents;
		public final Set<String> invalidatedComponents;
		public final Map<String,List<Problem>> problems;

		public Result()
		{
			newComponents = new HashMap<>();
			invalidatedComponents = new HashSet<>();
			problems = new HashMap<>();
		}
	}
}

class Cluster
{
	public Map<String, Set<Type>> fileToTypes;
	public Map<String, ExpressionNode> fileToNodes;
	public Map<String, Set<String>> componentToFiles;

	public Cluster()
	{
		fileToTypes = new HashMap<>();
		fileToNodes = new HashMap<>();
		componentToFiles = new HashMap<>();
	}
}
