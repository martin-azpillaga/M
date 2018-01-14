
using System;
using UnityEditor;
using UnityEngine;

public class BundleManager : EditorWindow
{
    const string path = "Project/Bundles";

    public static void Build()
    {
        BuildPipeline.BuildAssetBundles($"Assets/{path}", BuildAssetBundleOptions.None, BuildTarget.Android);
        AssetDatabase.Refresh();
    }
    
    public static void Load()
    {
        foreach (var name in AssetDatabase.GetAllAssetBundleNames())
        {
            AssetBundle.LoadFromFile($"{Application.dataPath}/{path}/{name}");
        }
    }
    
    public static void GenerateObjects()
    {
        foreach (var bundle in AssetBundle.GetAllLoadedAssetBundles())
        {
            var className = PascalCase(bundle.name);

            var resourceClass = new Script(className);

            resourceClass.Add("UnityEngine");

            resourceClass.AddField("AssetBundle", $"bundle = AssetBundle.LoadFromFile(\"Application.dataPath/{path}/{bundle.name}\")");

            foreach (var asset in bundle.LoadAllAssets<UnityEngine.Object>())
            {
                if (!asset.GetType().Equals(typeof(Texture2D)))
                {
                    var property = new Property(asset.GetType().ToString(), PascalCase(asset.name));

                    property.Get = $"return bundle.LoadAsset<" + asset.GetType() + ">(\"" + asset.name + "\");";

                    resourceClass.Add(property);
                }
            }

            Debug.LogWarning(className);
            resourceClass.Save($"{Application.dataPath}/{path}");
            bundle.Unload(true);
        }
        AssetDatabase.Refresh();
    }

    private static string PascalCase(string s)
    {
        return Char.ToUpper(s[0]) + s.Substring(1);
    }

    [MenuItem("Minim/Generate bundles")]
    public static void All()
    {
        Build(); Load(); GenerateObjects();
    }
}