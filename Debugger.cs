using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
using System;

public class Debugger : MonoBehaviour
{

}

[CustomEditor(typeof(Debugger))]
public class DebuggerInspector : Editor
{
    static string[] options = Enum.GetNames(typeof(Query));
    static Dictionary<Query, List<Type>> types = new Dictionary<Query, List<Type>>()
    {
        {Query.control_a, new List<Type>(){typeof(Rigidbody), typeof(SphereCollider)}},
        {Query.system_b, new List<Type>(){typeof(BoxCollider)}},
    };

    int selected;

    public override void OnInspectorGUI()
    {
        var debugger = (Debugger) target;
        var go = debugger.gameObject;

        EditorGUILayout.LabelField("Participates in systems:");

        foreach (var query in types.Keys)
        {
            var list = types[query];

            var missComponent = false;
            var i = 0;

            while (!missComponent && i < list.Count)
            {
                var type = list[i];
                if (go.GetComponent(type) == null)
                {
                    missComponent = true;
                }
                i++;
            }

            if (!missComponent)
            {
                var typeNames = "";
                foreach (var type in list)
                {
                    typeNames += type.Name;
                }
                EditorGUILayout.LabelField(query.ToString()+" : "+typeNames);
            }
        }

        EditorGUILayout.LabelField("");

        selected = EditorGUILayout.Popup("Select system", selected, options);

        if (GUILayout.Button("Add components"))
        {
            var query = (Query) selected;
            var list = types[query];
            
            foreach (var type in list)
            {
                if (go.GetComponent(type) == null)
                {
                    go.AddComponent(type);
                }
            }
        }
    }
}

enum Query
{
    control_a, serve_a, system_a, system_b
}