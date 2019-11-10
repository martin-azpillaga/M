package m.ide

import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider
import com.google.inject.Inject
import m.services.TextGrammarAccess
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.ide.labels.INameLabelProvider
import org.eclipse.xtext.ide.labels.SimpleNameLabelProvider
import org.eclipse.xtext.ide.server.WorkspaceManager

class TextIdeModule extends AbstractTextIdeModule 
{
	def Class<? extends IdeContentProposalProvider> bindIdeContentProposalProvider() 
	{
		return MyContentAssist
	}
}

class MyLabelProvider extends SimpleNameLabelProvider
{
	WorkspaceManager workspace
	
	def X()
	{
		workspace.getProjectManager("").resourceSet
	}
}

class MyContentAssist extends IdeContentProposalProvider
{
	@Inject extension TextGrammarAccess access
	
	override dispatch createProposals(RuleCall ruleCall, ContentAssistContext context, IIdeContentProposalAcceptor acceptor) 
	{
		switch ruleCall.rule 
		{
			case entityRule:
			{
				var any = proposalCreator.createProposal('any entity', context)
				acceptor.accept(any, proposalPriorities.getDefaultPriority(any))
			}
			default:
				super._createProposals(ruleCall, context, acceptor)	
		}
	}
}