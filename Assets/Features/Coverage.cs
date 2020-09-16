
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.TestTools;
using System.Linq;
using NUnit.Framework;

public class AA
{
    [Test]
    public void CodeCoverageSetup()
    {
        var fileNames = Directory.GetFiles($"{Application.dataPath}/Project", "*.cs", SearchOption.AllDirectories);
        foreach (var fileName in fileNames)
        {
            var file = fileName.Replace("\\", "/");

            var content = File.ReadAllLines(fileName);

            for (int i = 0; i < content.Length; i++)
            {
                var line = content[i];

                if (line.StartsWith("        ") && line.Contains(";"))
                {
                    Coverage.SetUp(file, i);

                    if (!line.Contains("Coverage"))
                    {
                        var colonIndex = line.LastIndexOf(";");
                        content[i] = $"{line.Substring(0, colonIndex + 1)}Coverage.Mark(\"{file}\",{i});{line.Substring(colonIndex + 1)}";
                    }
                }
            }

            File.WriteAllLines(fileName, content);
        }
    }
}

public class Coverage
{
    public static Dictionary<string, Dictionary<int, bool>> Marks = new Dictionary<string, Dictionary<int, bool>>();

    public static void SetUp(string file, int line)
    {
        if (!Marks.ContainsKey(file))
        {
            Marks.Add(file, new Dictionary<int, bool>());
        }

        Marks[file][line] = false;
    }
    public static void Mark(string file, int line)
    {
        Marks[file][line] = true;
    }

    public static Dictionary<int,bool> MarksIn(string file)
    {
        if (Marks.ContainsKey(file))
            return Marks[file];
        else
            return null;
    }

    public static bool Marked(string file, int line)
    {
        if (Marks.ContainsKey(file) && Marks[file].ContainsKey(line))
        {
            return Marks[file][line];
        }
        else
        {
            return false;
        }
    }
}

public class ZZ
{
    [Test]
    public void CodeCoverageReport()
    {
        var fileNames = Directory.GetFiles($"{Application.dataPath}/Project", "*.cs", SearchOption.AllDirectories);
        foreach (var fileName in fileNames)
        {
            var file = fileName.Replace("\\", "/");

            var content = File.ReadAllLines(fileName);

            for (int i = 0; i < content.Length; i++)
            {
                var line = content[i];

                if (line.Contains("Coverage"))
                {
                    var marked = Coverage.Marked(file, i);

                    content[i] = line.Substring(0, content[i].IndexOf(";") + 1);

                    if (!marked)
                    {
                        content[i] += "// Not covered by tests";
                    }
                }
            }

            File.WriteAllLines(fileName, content);

            var marks = Coverage.MarksIn(file);

            if (marks != null)
            {
                foreach (var line in marks.Keys)
                {
                    Debug.LogWarning(line + " " + marks[line]);
                }
                var executed = marks.Count(kv => kv.Value == true);

                Debug.LogWarning($"{fileName}: {executed} executed out of {marks.Count})");
            }
        }
    }
}
