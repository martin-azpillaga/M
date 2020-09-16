
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

public enum Visibility { Public, Private }

public class Script
{
    string name;
    List<string> imports;
    List<Method> methods;
    List<Tuple<string, string>> fields;
    List<Property> properties;

    public Script(string name)
    {
        this.name = name;

        imports = new List<string>();
        methods = new List<Method>();
        fields = new List<Tuple<string, string>>();
        properties = new List<Property>();
    }

    public void Add(params string[] imports)
    {
        this.imports.AddRange(imports);
    }

    public void AddField(string type, string name)
    {
        fields.Add(Tuple.Create(type, name));
    }

    public void Add(params Method[] methods)
    {
        var m = methods.Where(mm => !this.methods.Contains(mm));
        this.methods.AddRange(m);
    }

    public void Add(params Property[] properties)
    {
        this.properties.AddRange(properties);
    }

    public void Save(string path)
    {
        var lines = new List<string>();

        lines.Add(string.Empty);

        foreach (var import in imports)
        {
            lines.Add($"using {import};");
        }

        lines.Add(string.Empty);

        lines.Add($"public class {name}");

        lines.Add("{");

        foreach (var field in fields)
        {
            lines.Add($"\t{field.Item1} {field.Item2};");
        }

        lines.Add("\t");

        if (properties.Count > 0)
        {
            foreach (var property in properties)
            {
                lines.Add(property.Render("\t"));
            }

            lines.Add("\t");
        }
        foreach (var method in methods)
        {
            lines.AddRange(method.Render("\t"));
        }

        lines.Add("}");

        File.WriteAllLines($"{path}/{name}.cs", lines.ToArray());
    }
}

public class Method
{
    Visibility visibility;
    string returnType;
    string name;
    List<string> body;
    List<string> decorators;

    public Method(Visibility visibility, string returnType, string name)
    {
        this.visibility = visibility;
        this.returnType = returnType;
        this.name = name;
        this.body = new List<string>();
        this.decorators = new List<string>();
    }

    public void Add(params string[] lines)
    {
        this.body.AddRange(lines);
    }

    public void AddDecorators(params string[] decorators)
    {
        this.decorators.AddRange(decorators);
    }

    public List<string> Render(string indentation)
    {
        var lines = new List<string>();

        if (decorators.Count > 0)
        {
            lines.Add($"{indentation}[{string.Join(", ", decorators)}]");
        }

        lines.Add($"{indentation}{visibility.ToString().ToLower()} {returnType} {name}()");

        lines.Add($"{indentation}{{");

        foreach (var line in body)
        {
            lines.Add($"{indentation}\t{line}");
        }

        lines.Add($"{indentation}}}");

        return lines;
    }

    public override bool Equals(object obj)
    {
        return name == ((Method)obj).name;
    }

    public override int GetHashCode()
    {
        return base.GetHashCode();
    }
}

public class Property
{
    string type;
    string name;
    public string Get { private get; set; }
    public string Set { private get; set; }

    public Property(string type, string name)
    {
        this.type = type;
        this.name = name;
    }

    public string Render(string indent)
    {
        string result = $"{indent}public virtual {type} {name} {{";

        if (Get != null)
        {
            result += $" get {{ {Get} }}";
        }

        if (Set != null)
        {
            result += $" set {{ {Set} }}";
        }

        return result + "}";
    }
}