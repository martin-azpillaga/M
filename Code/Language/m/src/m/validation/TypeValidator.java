package m.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.Library;
import m.library.rules.ProblemKind;
import m.library.types.FunctionType;
import m.library.types.Type;
import m.model.Cell;
import m.model.ExpressionNode;
import m.model.File;
import m.model.UserFunction;
import m.model.ExpressionNode.Typing;
import m.validation.Problem.Severity;

public class TypeValidator
{
	Result result;

	Map<String, Set<Cluster>> fileToClusters;
	Map<String, Cluster> componentToCluster;

	Map<String, List<UserFunction>> fileToFunctions;

	public TypeValidator()
	{
		this.result = new Result();

		this.fileToClusters = new HashMap<>();
		this.componentToCluster = new HashMap<>();
		this.fileToFunctions = new HashMap<>();
	}

	public Result validate(String file, File model, Set<ExpressionNode> connectedComponents)
	{
		invalidateObsoleteMemory(file);
		validate(connectedComponents, file);
		checkTypes();
		gatherFunctions(file,model);
		return result;
	}

	public Result delete(String file)
	{
		invalidateObsoleteMemory(file);
		checkTypes();
		gatherFunctions(file, null);
		return result;
	}

	private void invalidateObsoleteMemory(String modifiedFile)
	{
		fileToFunctions.remove(modifiedFile);

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
				cluster.componentToFiles.forEach((component,files)->
				{
					files.remove(modifiedFile);

					if (files.isEmpty())
					{
						componentToCluster.remove(component);
					}
				});

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

				cluster.componentToFiles.computeIfAbsent(component,__->new HashSet<>()).add(modifiedFile);

				var definitionCluster = componentToCluster.get(component);
				if (definitionCluster != null && definitionCluster != cluster)
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
			cluster.fileToTypes.computeIfAbsent(modifiedFile,__->new HashSet<>()).add(typing);
		}
		return cluster;
	}

	private Cluster merge(Cluster cluster, Cluster definitionCluster)
	{
		cluster.fileToTypes.forEach((file, types)->
		{
			var definitionClusterTypes = definitionCluster.fileToTypes.get(file);
			if (definitionClusterTypes == null)
			{
				definitionCluster.fileToTypes.put(file, types);
			}
			else
			{
				definitionClusterTypes.addAll(types);
			}
		});

		cluster.fileToNodes.forEach((file,entryNode)->
		{
			definitionCluster.fileToNodes.put(file, entryNode);
		});

		cluster.componentToFiles.forEach((entryComponent, files)->
		{
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
		});

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

		var library = Library.ENGLISH;

		for (var cluster : allClusters)
		{
			var message = "";
			var types = new HashSet<Type>();

			for (var typingSet : cluster.fileToTypes.values())
			{
				for (var typing : typingSet)
				{
					message += "\n"+library.getName(typing.symbol)+" : "+library.getName(typing.type);
					types.add(typing.type);
				}
			}

			if (types.isEmpty())
			{
				cluster.fileToNodes.forEach((file,rootNode)->
				{
					var kind = Library.ENGLISH.getProblem(ProblemKind.UNDECIDABLE_TYPE);

					for (var node : rootNode)
					{
						result.problems.computeIfAbsent(file,__->new ArrayList<>()).add(new Problem(node.expression, Severity.ERROR, kind));
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
					var kind = Library.ENGLISH.getProblem(ProblemKind.INCOMPATIBLE_TYPES);

					for (var node : rootNode)
					{
						result.problems.computeIfAbsent(file,__->new ArrayList<>()).add(new Problem(node.expression, Severity.ERROR, kind));
					}
				});
			}
		}
	}

	private void gatherFunctions(String file, File model)
	{
		var userFunctions = new ArrayList<UserFunction>();

		if (model != null)
		{
			for (var function : model.getFunctions())
			{
				userFunctions.add(new UserFunction(function, FunctionType.systemType));
			}
		}
		fileToFunctions.put(file, userFunctions);

		for (var functionList : fileToFunctions.values())
		{
			for (var function : functionList)
			{
				result.functions.add(function);
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
	public Map<String, Set<Typing>> fileToTypes;
	public Map<String, ExpressionNode> fileToNodes;
	public Map<String, Set<String>> componentToFiles;

	public Cluster()
	{
		fileToTypes = new HashMap<>();
		fileToNodes = new HashMap<>();
		componentToFiles = new HashMap<>();
	}
}
