using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;
using System;
using System.IO;

using static System.Reflection.BindingFlags;

public class Inspect : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        var file = File.CreateText("UnityAPI");
        var components = typeof(Transform).Assembly.GetTypes().Where(t => t.IsSubclassOf(typeof(Component)));
        foreach (var c in components)
        {
            file.WriteLine(c.ToString());
            var properties = c.GetProperties(Public|Instance);
            foreach (var p in properties)
            {
                file.WriteLine("  {0} : {1}", p.Name, p.PropertyType);
            }
            file.WriteLine();
        }
        file.Close();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
