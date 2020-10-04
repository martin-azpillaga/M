package m.validation.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.Diagnostic;

import m.library.types.Type;
import m.m.Function;

public class GlobalData
{
    public Map<String,Type> components;
    public List<Function> functions;
    public Map<String,List<Diagnostic>> diagnostics;

    public GlobalData()
    {
        this.components = new HashMap<>();
        this.functions = new ArrayList<>();
        this.diagnostics = new HashMap<>();
    }
}
