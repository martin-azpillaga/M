package m.validation.global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.lsp4j.Diagnostic;

import m.library.types.Type;

public class GlobalData
{
    public Map<String,Type> components;
    public boolean hasErrors;
    public Map<String,List<Diagnostic>> diagnostics;

    public GlobalData()
    {
        this.components = new HashMap<>();
        this.hasErrors = false;
        this.diagnostics = new HashMap<>();
    }
}
