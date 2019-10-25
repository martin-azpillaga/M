package m.converter

import org.eclipse.xtext.common.services.DefaultTerminalConverters
import com.google.inject.Inject
import org.eclipse.xtext.conversion.ValueConverter
import org.eclipse.xtext.conversion.IValueConverter
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.conversion.ValueConverterException

class MyTerminalConverter extends DefaultTerminalConverters 
{
	IValueConverter<Long> longValueConverter = new IValueConverter<Long>() 
	{
        override Long toValue(String string, INode node) throws ValueConverterException 
        {
            return Long.parseLong(string);
        }

        override String toString(Long value) throws ValueConverterException 
        {
            return Long.toString(value);
        }

    }

    @ValueConverter(rule = "LONG")
    def IValueConverter<Long> LONG() 
    {
        return longValueConverter;
    }
    
    
	IValueConverter<Float> floatValueConverter = new IValueConverter<Float>()
	{
		override Float toValue(String string, INode node) throws ValueConverterException 
        {
            return Float.parseFloat(string);
        }

        override String toString(Float value) throws ValueConverterException 
        {
            return Float.toString(value);
        }
	}
	
	@ValueConverter(rule = "FLOAT")
    def IValueConverter<Float> FLOAT() 
    {
        return floatValueConverter;
    }
}