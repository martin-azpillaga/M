package m.validation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.m.Cell;

import m.library.types.Type;
import m.validation.rules.ExpressionNode;

public class Validator
{
    Map<String, Set<Cluster>> fileToClusters;
    Map<String, Cluster> componentToCluster;
    LocalValidator localValidator;

    public Validator()
    {
        fileToClusters = new HashMap<>();
        componentToCluster = new HashMap<>();
        localValidator = new LocalValidator();
    }

    public GlobalData validate(String modifiedFile, String text)
    {
        var modifiedData = localValidator.validate(text);

        invalidateObsoleteMemory(modifiedFile);

        validate(modifiedData.nodes, modifiedFile);

        return collectData();
    }

    public GlobalData delete(String file)
    {
        invalidateObsoleteMemory(file);
        
        return collectData();
    }

    private List<ExpressionNode> invalidateObsoleteMemory(String modifiedFile)
    {
        var affectedNodes = new ArrayList<ExpressionNode>();

        var clusters = fileToClusters.get(modifiedFile);

        if (clusters == null)
        {
            return new ArrayList<ExpressionNode>();
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

        return affectedNodes;
    }

    private void validate(List<ExpressionNode> nodes, String modifiedFile)
    {
        var stack = new ArrayDeque<ExpressionNode>();
		var visited = new ArrayList<ExpressionNode>();
		
		for (var rootNode : nodes)
		{
			if (visited.contains(rootNode))
			{
				continue;
			}
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

                var componentFiles = cluster.componentToFiles.get(modifiedFile);
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

    private GlobalData collectData()
    {
        var data = new GlobalData();

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
                data.hasErrors = true;
            }
            else if (types.size() == 1)
            {
                data.components.put(component, types.iterator().next());
            }
            else
            {
                data.hasErrors = true;
            }
        }

        return data;
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
