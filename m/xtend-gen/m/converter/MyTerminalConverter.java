package m.converter;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

@SuppressWarnings("all")
public class MyTerminalConverter extends DefaultTerminalConverters {
  private IValueConverter<Long> longValueConverter = new IValueConverter<Long>() {
    @Override
    public Long toValue(final String string, final INode node) throws ValueConverterException {
      return Long.valueOf(Long.parseLong(string));
    }
    
    @Override
    public String toString(final Long value) throws ValueConverterException {
      return Long.toString((value).longValue());
    }
  };
  
  @ValueConverter(rule = "LONG")
  public IValueConverter<Long> LONG() {
    return this.longValueConverter;
  }
  
  private IValueConverter<Float> floatValueConverter = new IValueConverter<Float>() {
    @Override
    public Float toValue(final String string, final INode node) throws ValueConverterException {
      return Float.valueOf(Float.parseFloat(string));
    }
    
    @Override
    public String toString(final Float value) throws ValueConverterException {
      return Float.toString((value).floatValue());
    }
  };
  
  @ValueConverter(rule = "FLOAT")
  public IValueConverter<Float> FLOAT() {
    return this.floatValueConverter;
  }
}
