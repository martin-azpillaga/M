package m.validation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.library.types.Type;
import m.m.Cell;
import m.m.Function;
import m.main.FileData;
import m.validation.problems.Problem;
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.Binding;
import m.validation.rules.ExpressionNode;
import m.validation.rules.Binding.BindingReason;

// Redundant maps for faster access performance
public class Clusterizer
{
    Map<String, List<Cluster>> fileToClusters;
    Map<String, Cluster> componentToCluster;
    Map<String, ExpressionNode> componentToNode;
	Map<String, String> componentToFile;
    Map<String, String> functionNameToFile;
	Map<String, Function> functionNameToFunction;
	Map<String, Map<ExpressionNode,Binding>> fileToReferences;
    
    public Clusterizer()
    {
        fileToClusters = new HashMap<>();
        componentToCluster = new HashMap<>();
		componentToNode = new HashMap<>();
		componentToFile = new HashMap<>();
        functionNameToFile = new HashMap<>();
		functionNameToFunction = new HashMap<>();
		fileToReferences = new HashMap<>();
    }

    public void clusterize(String modifiedFile, FileData data)
    {
		// Invalidate cross references
		var references = fileToReferences.get(modifiedFile);
		if (references != null)
		{
			for (var entry : references.entrySet())
			{
				var node = entry.getKey();
				var binding = entry.getValue();

				node.bindings.remove(binding);
			}
			references.clear();
		}
		// Cross references
		for (var entry : data.components.entrySet())
		{
			var component = entry.getKey();
			var modifiedNode = entry.getValue();

			var definitionNode = componentToNode.get(component);
			if (definitionNode != null)
			{
				var definitionBinding = new Binding(modifiedNode, BindingReason.SAME_COMPONENT);
				var modifiedBinding = new Binding(definitionNode, BindingReason.SAME_COMPONENT);

				definitionNode.bindings.add(definitionBinding);
				modifiedNode.bindings.add(modifiedBinding);

				var definitionFile = componentToFile.get(component);

				if (definitionFile == modifiedFile)
				{
					continue; // Correct ?
				}

				if (!fileToReferences.containsKey(definitionFile))
				{
					fileToReferences.put(definitionFile, new HashMap<>());
				}
				if (!fileToReferences.containsKey(modifiedFile))
				{
					fileToReferences.put(modifiedFile, new HashMap<>());
				}

				fileToReferences.get(definitionFile).put(definitionNode, definitionBinding);
				fileToReferences.get(modifiedFile).put(modifiedNode, modifiedBinding);
			}
		}

		var stack = new ArrayDeque<ExpressionNode>();
		var visited = new ArrayList<ExpressionNode>();
		
		HashSet<Type> types;
		
		for (var root : data.nodes)
		{
			if (visited.contains(root))
			{
				continue;
			}
			stack.push(root);
			var cluster = new Cluster();
			types = new HashSet<>();
		
			while (!stack.isEmpty())
			{
				var node = stack.pop();
				if (!visited.contains(node))
				{
					visited.add(node);
					
					for (var typing : node.typings)
					{
						types.add(typing.type);
					}
					
					for (var binding : node.bindings)
					{
						if (!visited.contains(binding.node))
						{
							stack.push(binding.node);
						}
					}
				}

				if (node.expression instanceof Cell)
				{
					var cell = (Cell) node.expression;
					if (cell.getComponent() != null)
					{
						var component = cell.getComponent().getName();
						
						cluster.components.add(component);

						// Synchronize other files to new clusters, synchronization overhead
						// Try reusing existing cluster instead of removing and adding a new one
						// if componentToCluster(component) != cluster
						if (componentToFile.containsKey(component))
						{
							var originalFile = componentToFile.get(component);
							var clusters = fileToClusters.get(originalFile);
							if (!clusters.contains(cluster))
							{
								clusters.remove(componentToCluster.get(component));
								clusters.add(cluster);
							}
						}
						componentToCluster.put(component, cluster);


						if (!componentToNode.containsKey(component))
						{
							componentToNode.put(component, node);
							componentToFile.put(component, modifiedFile);
						}

						
					}
				}
			}
			
			if (types.isEmpty())
			{
				cluster.problem = new UndecidableType(root);
			}
			else if (types.size() == 1)
			{
				cluster.type = types.iterator().next();
			}
			else if (types.size() > 1)
			{
				cluster.problem = new IncompatibleTypes(root);
			}

			// Update maps
			if (!fileToClusters.containsKey(modifiedFile))
			{
				fileToClusters.put(modifiedFile, new ArrayList<Cluster>());
			}
			fileToClusters.get(modifiedFile).add(cluster);

		}

		for (var entry : data.functions.entrySet())
		{
			var name = entry.getKey();
			var function = entry.getValue();

			functionNameToFile.put(name, modifiedFile);
			functionNameToFunction.put(name, function);
		}

		// Debugging

		var modifiedClusters = fileToClusters.get(modifiedFile);
		var stableClusters = new ArrayList<Cluster>();
		for (var entry : fileToClusters.entrySet())
		{
			var file = entry.getKey();
			var clusters = entry.getValue();

			if (file != modifiedFile)
			{
				for (var cluster : clusters)
				{
					if (!modifiedClusters.contains(cluster))
					{
						stableClusters.add(cluster);
					}
				}
			}
		}

		var allClusters = new HashSet<Cluster>();
		for (var clusters : fileToClusters.values())
		{
			allClusters.addAll(clusters);
		}

		var components = new HashMap<String,Type>();
		for (var entry : componentToCluster.entrySet())
		{
			var component = entry.getKey();
			var type = entry.getValue().type;

			if (type != null)
			{
				components.put(component, type);
			}
		}

		var problems = new HashSet<Problem>();
		for (var clusters : fileToClusters.values())
		{
			for (var cluster : clusters)
			{
				if (cluster.problem != null)
				{
					problems.add(cluster.problem);
				}
			}
		}
	}

	private void gatherNewClusters(String modifiedFile)
	{

	}
}

class Cluster
{
    public Set<String> components;
    public Type type;
	public Problem problem;
	
	public Cluster()
	{
		this.components = new HashSet<String>();
	}
}