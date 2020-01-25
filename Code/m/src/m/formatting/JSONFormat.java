package m.formatting;

import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

import m.json.Member;

public class JSONFormat extends AbstractFormatter2
{
	@Override
	public void format(Object obj, IFormattableDocument document) 
	{

		if (obj instanceof XtextResource)
		{
			_format((XtextResource) obj, document);
		}
		else if (obj instanceof m.json.Object)
		{
			var o = (m.json.Object) obj;
			var open = textRegionExtensions.regionFor(o).keyword("{");
			var close = textRegionExtensions.regionFor(o).keyword("}");
			if (o.eContainer() != null)
			{
				document.prepend(open, newLine());
			}
			document.prepend(close, newLine());
			document.interior(open, close, indent());
			var commas = textRegionExtensions.regionFor(o).keywords(",");
			for (var comma : commas)
			{
				document.prepend(comma, noSpace());
			}
			for (var member : o.getMembers())
			{
				format(member, document);
			}
		}
		else if (obj instanceof Member)
		{
			var member = (Member) obj;
			document.prepend(member, newLine());
			format(member.getValue(),document);
		}
	}
	
	private Procedure1<? super IHiddenRegionFormatter> newLine()
	{
		return x -> x.newLine();
	}
	
	private Procedure1<? super IHiddenRegionFormatter> newLines(int num)
	{
		return x -> x.setNewLines(num);
	}
	
	private Procedure1<? super IHiddenRegionFormatter> indent()
	{
		return x -> x.indent();
	}
	
	private Procedure1<? super IHiddenRegionFormatter> noSpace()
	{
		return x -> x.noSpace();
	}
}
/*
def dispatch void format(Object object, extension IFormattableDocument document) 
{
	var open = object.regionFor.keyword('{')
	var close = object.regionFor.keyword('}')
	if (object.eContainer !== null)
	{
		open.prepend[newLine]
	}
	close.prepend[newLine]
	interior(open,close)[indent]
	object.regionFor.keywords(',').forEach[prepend[noSpace]]
	object.members.forEach[format]
}

def dispatch void format(Member member, extension IFormattableDocument document) 
{
	member.prepend[newLine]
	member.regionFor.keyword(':').surround[oneSpace]
	member.value.format
}*/