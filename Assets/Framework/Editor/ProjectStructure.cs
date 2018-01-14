using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEditor;
using UnityEngine;

class ProjectStructure : EditorWindow 
{
	[MenuItem("Minim/Create Project Folders")]
	public static void CreateProjectFolders()
	{
		Directory.CreateDirectory($"{Application.dataPath}/Features");
		Directory.CreateDirectory($"{Application.dataPath}/Features/Tests");
		
		Directory.CreateDirectory($"{Application.dataPath}/Scripts");

		File.Create($"{Application.dataPath}/Features/Assembly.asmdef");
	}
}
