package m.ide;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import m.services.TextGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistEntry;
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor;
import org.eclipse.xtext.ide.editor.contentassist.IdeContentProposalProvider;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class MyContentAssist extends IdeContentProposalProvider {
  @Inject
  @Extension
  private TextGrammarAccess access;
  
  @Override
  protected void _createProposals(final RuleCall ruleCall, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    AbstractRule _rule = ruleCall.getRule();
    boolean _matched = false;
    ParserRule _entityRule = this.access.getEntityRule();
    if (Objects.equal(_rule, _entityRule)) {
      _matched=true;
      ContentAssistEntry any = this.getProposalCreator().createProposal("any entity", context);
      acceptor.accept(any, this.getProposalPriorities().getDefaultPriority(any));
    }
    if (!_matched) {
      super._createProposals(ruleCall, context, acceptor);
    }
  }
  
  @Override
  public void createProposals(final AbstractElement ruleCall, final ContentAssistContext context, final IIdeContentProposalAcceptor acceptor) {
    if (ruleCall instanceof Assignment) {
      _createProposals((Assignment)ruleCall, context, acceptor);
      return;
    } else if (ruleCall instanceof CrossReference) {
      _createProposals((CrossReference)ruleCall, context, acceptor);
      return;
    } else if (ruleCall instanceof Keyword) {
      _createProposals((Keyword)ruleCall, context, acceptor);
      return;
    } else if (ruleCall instanceof RuleCall) {
      _createProposals((RuleCall)ruleCall, context, acceptor);
      return;
    } else if (ruleCall != null) {
      _createProposals(ruleCall, context, acceptor);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(ruleCall, context, acceptor).toString());
    }
  }
}
