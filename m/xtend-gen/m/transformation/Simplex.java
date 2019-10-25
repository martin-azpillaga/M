package m.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Simplex {
  public ArrayList<ArrayList<Float>> matrix;
  
  public ArrayList<HashMap<String, Float>> inequalities = new ArrayList<HashMap<String, Float>>();
  
  public ArrayList<HashMap<String, Float>> equalities = new ArrayList<HashMap<String, Float>>();
  
  public HashMap<String, Float> objective = new HashMap<String, Float>();
  
  public HashMap<String, Float> solution = new HashMap<String, Float>();
  
  public ArrayList<String> ordered = new ArrayList<String>();
  
  public Simplex(final ArrayList<ArrayList<Float>> matrix) {
    this.matrix = matrix;
  }
  
  public Simplex() {
  }
  
  public void reduce() {
    final Function1<HashMap<String, Float>, Boolean> _function = (HashMap<String, Float> it) -> {
      int _size = it.keySet().size();
      return Boolean.valueOf((_size == 2));
    };
    HashMap<String, Float> sustitute = IterableExtensions.<HashMap<String, Float>>findFirst(this.equalities, _function);
    while ((sustitute != null)) {
      {
        this.equalities.remove(sustitute);
        final Function1<String, Boolean> _function_1 = (String it) -> {
          return Boolean.valueOf((it != "value"));
        };
        String key = IterableExtensions.<String>findFirst(sustitute.keySet(), _function_1);
        Float _get = sustitute.get("value");
        Float _get_1 = sustitute.get(key);
        float value = ((_get).floatValue() / (_get_1).floatValue());
        this.solution.put(key, Float.valueOf(value));
        for (final HashMap<String, Float> equality : this.equalities) {
          boolean _containsKey = equality.containsKey(key);
          if (_containsKey) {
            Float _get_2 = equality.get("value");
            Float _get_3 = equality.get(key);
            float _multiply = (value * (_get_3).floatValue());
            float _minus = ((_get_2).floatValue() - _multiply);
            equality.put("value", Float.valueOf(_minus));
            equality.remove(key);
          }
        }
        for (final HashMap<String, Float> inequality : this.inequalities) {
          boolean _containsKey_1 = inequality.containsKey(key);
          if (_containsKey_1) {
            Float _get_4 = inequality.get("value");
            Float _get_5 = inequality.get(key);
            float _multiply_1 = (value * (_get_5).floatValue());
            float _minus_1 = ((_get_4).floatValue() - _multiply_1);
            inequality.put("value", Float.valueOf(_minus_1));
            inequality.remove(key);
          }
        }
        boolean _containsKey_2 = this.objective.containsKey(key);
        if (_containsKey_2) {
          this.objective.remove(key);
        }
        final Function1<HashMap<String, Float>, Boolean> _function_2 = (HashMap<String, Float> it) -> {
          int _size = it.keySet().size();
          return Boolean.valueOf((_size == 2));
        };
        sustitute = IterableExtensions.<HashMap<String, Float>>findFirst(this.equalities, _function_2);
      }
    }
  }
  
  public void equalitiesToInequalities() {
    for (final HashMap<String, Float> equality : this.equalities) {
      {
        this.inequalities.add(equality);
        HashMap<String, Float> negative = new HashMap<String, Float>();
        Set<String> _keySet = equality.keySet();
        for (final String key : _keySet) {
          Float _get = equality.get(key);
          float _minus = (-(_get).floatValue());
          negative.put(key, Float.valueOf(_minus));
        }
        this.inequalities.add(negative);
      }
    }
  }
  
  public void convert() {
    HashSet<String> variables = new HashSet<String>();
    variables.addAll(this.objective.keySet());
    for (final HashMap<String, Float> inequality : this.inequalities) {
      variables.addAll(inequality.keySet());
    }
    variables.remove("value");
    int slacks = this.inequalities.size();
    ArrayList<String> _arrayList = new ArrayList<String>();
    this.ordered = _arrayList;
    this.ordered.addAll(variables);
    ArrayList<ArrayList<Float>> _arrayList_1 = new ArrayList<ArrayList<Float>>();
    this.matrix = _arrayList_1;
    ArrayList<Float> o = new ArrayList<Float>();
    for (final String v : this.ordered) {
      boolean _containsKey = this.objective.containsKey(v);
      if (_containsKey) {
        Float _get = this.objective.get(v);
        float _minus = (-(_get).floatValue());
        o.add(Float.valueOf(_minus));
      } else {
        o.add(Float.valueOf(0f));
      }
    }
    for (int i = 0; (i < slacks); i++) {
      o.add(Float.valueOf(0f));
    }
    boolean _containsKey_1 = this.objective.containsKey("value");
    if (_containsKey_1) {
      Float _get_1 = this.objective.get("value");
      float _minus_1 = (-(_get_1).floatValue());
      o.add(Float.valueOf(_minus_1));
    } else {
      o.add(Float.valueOf(0f));
    }
    this.matrix.add(o);
    for (int i = 0; (i < this.inequalities.size()); i++) {
      {
        HashMap<String, Float> inequality_1 = this.inequalities.get(i);
        ArrayList<Float> list = new ArrayList<Float>();
        for (final String v_1 : this.ordered) {
          boolean _containsKey_2 = inequality_1.containsKey(v_1);
          if (_containsKey_2) {
            list.add(inequality_1.get(v_1));
          } else {
            list.add(Float.valueOf(0f));
          }
        }
        for (int s = 0; (s < slacks); s++) {
          if ((s != i)) {
            list.add(Float.valueOf(0f));
          } else {
            list.add(Float.valueOf(1f));
          }
        }
        boolean _containsKey_3 = inequality_1.containsKey("value");
        if (_containsKey_3) {
          list.add(inequality_1.get("value"));
        } else {
          list.add(Float.valueOf(0f));
        }
        this.matrix.add(list);
      }
    }
  }
  
  public ArrayList<ArrayList<Float>> solve() {
    float minValue = 0f;
    int column = 0;
    ArrayList<Float> objectiveRow = IterableExtensions.<ArrayList<Float>>last(this.matrix);
    for (int c = 0; (c < objectiveRow.size()); c++) {
      {
        Float value = objectiveRow.get(c);
        if (((value).floatValue() < minValue)) {
          minValue = (value).floatValue();
          column = c;
        }
      }
    }
    while ((minValue < 0)) {
      {
        float minFactor = Float.MAX_VALUE;
        int row = 0;
        for (int r = 0; (r < (this.matrix.size() - 1)); r++) {
          {
            Float _last = IterableExtensions.<Float>last(this.matrix.get(r));
            Float _get = this.matrix.get(r).get(column);
            float value = ((_last).floatValue() / (_get).floatValue());
            if ((value < minFactor)) {
              minFactor = value;
              row = r;
            }
          }
        }
        ArrayList<Float> selectedRow = this.matrix.get(row);
        Float selectedCell = this.matrix.get(row).get(column);
        for (int c = 0; (c < selectedRow.size()); c++) {
          if ((c == column)) {
            selectedRow.set(c, Float.valueOf(1f));
          } else {
            Float _get = selectedRow.get(c);
            float _divide = ((_get).floatValue() / (selectedCell).floatValue());
            selectedRow.set(c, Float.valueOf(_divide));
          }
        }
        for (int r = 0; (r < this.matrix.size()); r++) {
          {
            ArrayList<Float> rowToChange = this.matrix.get(r);
            Float factor = rowToChange.get(column);
            if ((r != row)) {
              for (int c = 0; (c < rowToChange.size()); c++) {
                if ((c == column)) {
                  rowToChange.set(c, Float.valueOf(0f));
                } else {
                  Float _get = rowToChange.get(c);
                  Float _get_1 = selectedRow.get(c);
                  float _multiply = ((_get_1).floatValue() * (factor).floatValue());
                  float _minus = ((_get).floatValue() - _multiply);
                  rowToChange.set(c, Float.valueOf(_minus));
                }
              }
            }
          }
        }
        minValue = 0;
        for (int c = 0; (c < objectiveRow.size()); c++) {
          {
            Float value = objectiveRow.get(c);
            if (((value).floatValue() < minValue)) {
              minValue = (value).floatValue();
              column = c;
            }
          }
        }
      }
    }
    for (int v = 0; (v < this.ordered.size()); v++) {
      for (int r = 0; (r < this.matrix.size()); r++) {
        {
          ArrayList<Float> currentRow = this.matrix.get(r);
          Float _get = currentRow.get(v);
          boolean _equals = ((_get).floatValue() == 1f);
          if (_equals) {
            this.solution.put(this.ordered.get(v), IterableExtensions.<Float>last(currentRow));
          }
        }
      }
    }
    return this.matrix;
  }
}
