
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using UnityEditor;
using UnityEngine;


public class BDDEditor : EditorWindow
{
    static List<string> tokens = new List<string> { "Given", "When", "Then" };
    
    [MenuItem("Minim/Generate tests")]
    public static void GenerateBDDTests()
    {
        var fileNames = Directory.GetFiles(Application.dataPath, "*.feature", SearchOption.AllDirectories);

        foreach (var fileName in fileNames)
        {
            var content = File.ReadAllLines(fileName);

            var featureClassName = Path.GetFileNameWithoutExtension(fileName);

            var stepClassName = $"{featureClassName}Steps";

            var featureClass = new Script(featureClassName);

            featureClass.Add("System.Collections", "UnityEngine", "UnityEngine.TestTools", "NUnit.Framework");
            featureClass.AddField(stepClassName, $"steps = new {stepClassName}()");

            var stepClass = new Script(stepClassName);
            stepClass.Add("System.Collections");


            Method scenarioMethod = null;

            var previousToken = string.Empty;

            foreach (var line in content)
            {
                if (line.StartsWith("Scenario:"))
                {
                    var name = Format(line.Replace("Scenario:", ""));

                    scenarioMethod = new Method(Visibility.Public, "IEnumerator", name);
                    scenarioMethod.AddDecorators("UnityTest", "Timeout(1000)");
                }

                foreach (var token in tokens)
                {
                    if (line.StartsWith($"\t{token}") || line.StartsWith($"    {token}"))
                    {
                        scenarioMethod.Add($"steps.{Format(line)}();");

                        var step = new Method(Visibility.Public, "IEnumerator", Format(line));
                        step.Add("yield return null;");
                        stepClass.Add(step);

                        previousToken = token;
                    }
                }

                if (line.StartsWith("\tAnd") || line.StartsWith("    And"))
                {
                    var name = $"{previousToken}{Format(line.Replace("And ", ""))}";

                    scenarioMethod.Add($"steps.{name}();");

                    var step = new Method(Visibility.Public, "IEnumerator", name);
                    step.Add("yield return null;");
                    stepClass.Add(step);

                }

                if ((line == "" || line == "\n") && previousToken == "Then")
                {
                    scenarioMethod.Add("yield return null;");
                    featureClass.Add(scenarioMethod);
                    previousToken = "";
                    scenarioMethod = null;
                }
            }

            if (scenarioMethod != null)
            {
                scenarioMethod.Add("yield return null;");
                featureClass.Add(scenarioMethod);
            }
            
            var featuresFolder = Application.dataPath + "/Features/Tests";

            if (Directory.Exists(featuresFolder))
            {
                featureClass.Save(featuresFolder);
                stepClass.Save(featuresFolder);
            }
            else
            {
                featureClass.Save(Application.dataPath);
                stepClass.Save(Application.dataPath);
            }
        }
    }

    private static string Format(string s)
    {
        var result = new StringBuilder(s);

        for (int i = 0; i < s.Length; i++)
        {
            if (s[i] == ' ')
            {
                result[i + 1] = Char.ToUpper(s[i + 1]);
            }
        }

        result.Replace(" ", "");
        result.Replace("\t", "");

        return result.ToString();
    }
}