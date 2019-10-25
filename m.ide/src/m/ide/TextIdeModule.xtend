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
	/*
	override dispatch createProposals(Assignment assignment, ContentAssistContext context, IIdeContentProposalAcceptor acceptor) 
	{
		switch assignment 
		{
			case eventAccess.signalAssignment_0: {
				val scope = scopeProvider.getScope(context.currentModel, EVENT__SIGNAL)
				for (description : scope.allElements.filter[getEClass == INPUT_SIGNAL]) {
					val entry = proposalCreator.createProposal(description.name.toString, context) [
						source = description
						description = 'input signal'
					]
					acceptor.accept(entry, proposalPriorities.getCrossRefPriority(description, entry))
				}
			}
			
			case commandAccess.signalAssignment_1: {
				val scope = scopeProvider.getScope(context.currentModel, COMMAND__SIGNAL)
				for (description : scope.allElements.filter[getEClass == OUTPUT_SIGNAL]) {
					val entry = proposalCreator.createProposal(description.name.toString, context) [
						source = description
						description = 'output signal'
					]
					acceptor.accept(entry, proposalPriorities.getCrossRefPriority(description, entry))
				}
			}
			
			case eventAccess.valueAssignment_2,
			case commandAccess.newValueAssignment_3: {
				// Proposals are created by RuleCall dispatch method
			}
			
			default:
				super._createProposals(assignment, context, acceptor)
		}
	}
	
	override protected filterKeyword(Keyword keyword, ContentAssistContext context) 
	{
		switch keyword 
		{
			case stateAccess.stateKeyword_0:
				false
			default:
				super.filterKeyword(keyword, context)
		}
	}*/
}