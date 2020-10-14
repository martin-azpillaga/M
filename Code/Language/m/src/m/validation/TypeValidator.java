package m.validation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.Library;
import m.library.rules.ProblemKind;
import m.library.types.Type;
import m.model.Cell;
import m.model.ExpressionGraph;
import m.model.ExpressionNode;
import m.model.File;
import m.model.UserFunction;
import m.validation.Problem.Severity;

public class TypeValidator
{
	Result result;

	Map<String, Set<Cluster>> fileToClusters;
	Map<String, Cluster> componentToCluster;

	public TypeValidator()
	{
		this.result = new Result();

		this.fileToClusters = new HashMap<>();
		this.componentToCluster = new HashMap<>();
	}

	public Result validate(String file, File model, ExpressionGraph graph)
	{
		invalidateObsoleteMemory(file);
		validate(graph.connectedComponents, file);
		checkTypes();

		return result;
	}

	public Result delete(String file)
	{
		invalidateObsoleteMemory(file);
		checkTypes();
		return result;
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
		for (var rootNode : connectedComponents)
		{
			var cluster = new Cluster();
			cluster.fileToNodes.put(modifiedFile, rootNode);

			for (var node : rootNode)
			{
				cluster = validate(node, cluster, modifiedFile);
			}

			fileToClusters.computeIfAbsent(modifiedFile,__->new HashSet<>()).add(cluster);
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

	private void checkTypes()
	{
		result.components.clear();
		result.functions.clear();
		result.problems.clear();

		var allClusters = new HashSet<Cluster>();

		for (var clusterSet : fileToClusters.values())
		{
			allClusters.addAll(clusterSet);
		}

		for (var cluster : allClusters)
		{
			var types = new HashSet<Type>();

			for (var typeSet : cluster.fileToTypes.values())
			{
				types.addAll(typeSet);
			}

			if (types.isEmpty())
			{
				cluster.fileToNodes.forEach((file,rootNode)->
				{
					for (var node : rootNode)
					{
						result.problems.computeIfAbsent(file, k->new ArrayList<>()).add(new Problem(node.expression, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.UNDECIDABLE_TYPE)));
					}
				});
			}
			else if (types.size() == 1)
			{
				for (var component : cluster.componentToFiles.keySet())
				{
					result.components.put(component, types.iterator().next());
				}
			}
			else
			{
				cluster.fileToNodes.forEach((file,rootNode)->
				{
					for (var node : rootNode)
					{
						result.problems.computeIfAbsent(file, k->new ArrayList<>()).add(new Problem(node.expression, Severity.ERROR, Library.ENGLISH.getProblem(m.library.rules.ProblemKind.INCOMPATIBLE_TYPES)));
					}
				});
			}
		}
	}

	public static class Result
	{
		public final Map<String,Type> components = new HashMap<>();
		public final List<UserFunction> functions = new ArrayList<>();
		public final Map<String,List<Problem>> problems = new HashMap<>();
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
