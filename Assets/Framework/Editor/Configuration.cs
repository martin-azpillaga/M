
using UnityEditor;
using UnityEngine;

public class Configuration : ScriptableObject
{
    [Space, Header("Behaviour tests")]
    public string testsPath;
    public int timeOutMilliseconds;
    [Space, Header("Scriptable Objects")]
    public string settingsPath;
    [Space, Header("Asset bundles")]
    public string bundlesPath;
}

public class ConfigurationEditor : EditorWindow
{
    Configuration configuration;

    [MenuItem("Minim/Framework configuration")]
    public static void CreateConfiguration()
    {
        Create(CreateInstance<Configuration>());
    }

    private static void Create(Object asset)
    {
        var index = asset.GetType().ToString().LastIndexOf(".") + 1;
        var name = asset.GetType().ToString().Substring(index);
        var path = $"Assets/{name}.asset";

        AssetDatabase.CreateAsset(asset, path);
        AssetDatabase.SaveAssets();
        AssetDatabase.Refresh();
        EditorUtility.FocusProjectWindow();
        Selection.activeObject = asset;
    }
}