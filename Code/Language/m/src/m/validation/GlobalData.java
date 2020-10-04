package m.validation;

import java.util.HashMap;
import java.util.Map;

import m.library.types.Type;

public class GlobalData
{
    public Map<String,Type> components;
    public boolean hasErrors;

    public GlobalData()
    {
        this.components = new HashMap<>();
        this.hasErrors = false;
    }
}
