package m.formatting;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public abstract class FormattingHelper extends AbstractFormatter2
{
	protected IFormattableDocument document;

	@Override
	public void format(Object obj, IFormattableDocument document)
	{
		if (obj instanceof XtextResource)
		{
			this.document = document;
			_format((XtextResource)obj, document);
		}
		else
		{
			format(obj);
		}
	}
	
	protected abstract void format(Object obj);
	
	protected ISemanticRegion keyword(String key, EObject o)
	{
		return textRegionExtensions.regionFor(o).keyword(key);
	}
	
	protected List<ISemanticRegion> keywords(EObject o, String key)
	{
		return textRegionExtensions.regionFor(o).keywords(key);
	}
	
	protected void prepend(ISemanticRegion o, Procedure1<? super IHiddenRegionFormatter> f)
	{
		document.prepend(o,f);
	}
	
	protected void prepend(EObject o, Procedure1<? super IHiddenRegionFormatter> f)
	{
		document.prepend(o,f);
	}
	
	protected void append(ISemanticRegion o, Procedure1<? super IHiddenRegionFormatter> f)
	{
		document.append(o,f);
	}
	
	protected void append(EObject o, Procedure1<? super IHiddenRegionFormatter> f)
	{
		document.append(o,f);
	}
	
	protected void indent(ISemanticRegion a, ISemanticRegion b)
	{
		document.interior(a, b, indent());
	}

	protected Procedure1<? super IHiddenRegionFormatter> newLine()
	{
		return x -> x.newLine();
	}
	
	
	protected Procedure1<? super IHiddenRegionFormatter> newLines(int num)
	{
		return x -> x.setNewLines(num);
	}
	
	protected Procedure1<? super IHiddenRegionFormatter> indent()
	{
		return x -> x.indent();
	}
	
	protected Procedure1<? super IHiddenRegionFormatter> noSpace()
	{
		return x -> x.noSpace();
	}
	
	protected Procedure1<? super IHiddenRegionFormatter> oneSpace()
	{
		return x -> x.oneSpace();
	}
}
