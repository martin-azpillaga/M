package m.formatting;

import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

import m.json.Member;

public class JSONFormat extends GenericFormatter
{
	@Override
	public void format(Object obj) 
	{
		if (obj instanceof m.json.Object)
		{
			var o = (m.json.Object) obj;
			var open = keyword(o,"{");
			var close = keyword(o,"}");
			if (o.eContainer() != null)
			{
				prepend(open, newLine());
			}
			prepend(close, newLine());
			indent(open, close);

			for (var comma : keywords(o,","))
			{
				prepend(comma, noSpace());
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
}