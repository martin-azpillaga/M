package m.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import m.services.TextGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTextParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_REAL", "RULE_WHITE_SPACE", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'>'", "'<'", "'='", "'!='", "'>='", "'<='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'{'", "'}'", "':'", "','", "'['", "']'", "'for'", "'all'", "'entity'", "'with'", "'if'", "'else'", "'('", "')'", "'.'", "'||'", "'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'~'", "'++'", "'--'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__10=10;
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int T__7=7;
    public static final int RULE_ID=4;
    public static final int RULE_REAL=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_WHITE_SPACE=6;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalTextParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTextParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTextParser.tokenNames; }
    public String getGrammarFileName() { return "InternalText.g"; }


    	private TextGrammarAccess grammarAccess;

    	public void setGrammarAccess(TextGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleGame"
    // InternalText.g:53:1: entryRuleGame : ruleGame EOF ;
    public final void entryRuleGame() throws RecognitionException {
        try {
            // InternalText.g:54:1: ( ruleGame EOF )
            // InternalText.g:55:1: ruleGame EOF
            {
             before(grammarAccess.getGameRule()); 
            pushFollow(FOLLOW_1);
            ruleGame();

            state._fsp--;

             after(grammarAccess.getGameRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGame"


    // $ANTLR start "ruleGame"
    // InternalText.g:62:1: ruleGame : ( ( rule__Game__Alternatives )* ) ;
    public final void ruleGame() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:66:2: ( ( ( rule__Game__Alternatives )* ) )
            // InternalText.g:67:2: ( ( rule__Game__Alternatives )* )
            {
            // InternalText.g:67:2: ( ( rule__Game__Alternatives )* )
            // InternalText.g:68:3: ( rule__Game__Alternatives )*
            {
             before(grammarAccess.getGameAccess().getAlternatives()); 
            // InternalText.g:69:3: ( rule__Game__Alternatives )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalText.g:69:4: rule__Game__Alternatives
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Game__Alternatives();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getGameAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGame"


    // $ANTLR start "entryRuleEntity"
    // InternalText.g:78:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // InternalText.g:79:1: ( ruleEntity EOF )
            // InternalText.g:80:1: ruleEntity EOF
            {
             before(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getEntityRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalText.g:87:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:91:2: ( ( ( rule__Entity__Group__0 ) ) )
            // InternalText.g:92:2: ( ( rule__Entity__Group__0 ) )
            {
            // InternalText.g:92:2: ( ( rule__Entity__Group__0 ) )
            // InternalText.g:93:3: ( rule__Entity__Group__0 )
            {
             before(grammarAccess.getEntityAccess().getGroup()); 
            // InternalText.g:94:3: ( rule__Entity__Group__0 )
            // InternalText.g:94:4: rule__Entity__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleComponent"
    // InternalText.g:103:1: entryRuleComponent : ruleComponent EOF ;
    public final void entryRuleComponent() throws RecognitionException {
        try {
            // InternalText.g:104:1: ( ruleComponent EOF )
            // InternalText.g:105:1: ruleComponent EOF
            {
             before(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getComponentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalText.g:112:1: ruleComponent : ( ( rule__Component__Group__0 ) ) ;
    public final void ruleComponent() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:116:2: ( ( ( rule__Component__Group__0 ) ) )
            // InternalText.g:117:2: ( ( rule__Component__Group__0 ) )
            {
            // InternalText.g:117:2: ( ( rule__Component__Group__0 ) )
            // InternalText.g:118:3: ( rule__Component__Group__0 )
            {
             before(grammarAccess.getComponentAccess().getGroup()); 
            // InternalText.g:119:3: ( rule__Component__Group__0 )
            // InternalText.g:119:4: rule__Component__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleValue"
    // InternalText.g:128:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalText.g:129:1: ( ruleValue EOF )
            // InternalText.g:130:1: ruleValue EOF
            {
             before(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getValueRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalText.g:137:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:141:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalText.g:142:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalText.g:142:2: ( ( rule__Value__Alternatives ) )
            // InternalText.g:143:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalText.g:144:3: ( rule__Value__Alternatives )
            // InternalText.g:144:4: rule__Value__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Value__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleVector"
    // InternalText.g:153:1: entryRuleVector : ruleVector EOF ;
    public final void entryRuleVector() throws RecognitionException {
        try {
            // InternalText.g:154:1: ( ruleVector EOF )
            // InternalText.g:155:1: ruleVector EOF
            {
             before(grammarAccess.getVectorRule()); 
            pushFollow(FOLLOW_1);
            ruleVector();

            state._fsp--;

             after(grammarAccess.getVectorRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVector"


    // $ANTLR start "ruleVector"
    // InternalText.g:162:1: ruleVector : ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) ) ;
    public final void ruleVector() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:166:2: ( ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) ) )
            // InternalText.g:167:2: ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) )
            {
            // InternalText.g:167:2: ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) )
            // InternalText.g:168:3: ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* )
            {
            // InternalText.g:168:3: ( ( rule__Vector__NumbersAssignment ) )
            // InternalText.g:169:4: ( rule__Vector__NumbersAssignment )
            {
             before(grammarAccess.getVectorAccess().getNumbersAssignment()); 
            // InternalText.g:170:4: ( rule__Vector__NumbersAssignment )
            // InternalText.g:170:5: rule__Vector__NumbersAssignment
            {
            pushFollow(FOLLOW_4);
            rule__Vector__NumbersAssignment();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getNumbersAssignment()); 

            }

            // InternalText.g:173:3: ( ( rule__Vector__NumbersAssignment )* )
            // InternalText.g:174:4: ( rule__Vector__NumbersAssignment )*
            {
             before(grammarAccess.getVectorAccess().getNumbersAssignment()); 
            // InternalText.g:175:4: ( rule__Vector__NumbersAssignment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_REAL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalText.g:175:5: rule__Vector__NumbersAssignment
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Vector__NumbersAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getVectorAccess().getNumbersAssignment()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVector"


    // $ANTLR start "entryRuleWord"
    // InternalText.g:185:1: entryRuleWord : ruleWord EOF ;
    public final void entryRuleWord() throws RecognitionException {
        try {
            // InternalText.g:186:1: ( ruleWord EOF )
            // InternalText.g:187:1: ruleWord EOF
            {
             before(grammarAccess.getWordRule()); 
            pushFollow(FOLLOW_1);
            ruleWord();

            state._fsp--;

             after(grammarAccess.getWordRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWord"


    // $ANTLR start "ruleWord"
    // InternalText.g:194:1: ruleWord : ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) ) ;
    public final void ruleWord() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:198:2: ( ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) ) )
            // InternalText.g:199:2: ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) )
            {
            // InternalText.g:199:2: ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) )
            // InternalText.g:200:3: ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* )
            {
            // InternalText.g:200:3: ( ( rule__Word__ValuesAssignment ) )
            // InternalText.g:201:4: ( rule__Word__ValuesAssignment )
            {
             before(grammarAccess.getWordAccess().getValuesAssignment()); 
            // InternalText.g:202:4: ( rule__Word__ValuesAssignment )
            // InternalText.g:202:5: rule__Word__ValuesAssignment
            {
            pushFollow(FOLLOW_3);
            rule__Word__ValuesAssignment();

            state._fsp--;


            }

             after(grammarAccess.getWordAccess().getValuesAssignment()); 

            }

            // InternalText.g:205:3: ( ( rule__Word__ValuesAssignment )* )
            // InternalText.g:206:4: ( rule__Word__ValuesAssignment )*
            {
             before(grammarAccess.getWordAccess().getValuesAssignment()); 
            // InternalText.g:207:4: ( rule__Word__ValuesAssignment )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalText.g:207:5: rule__Word__ValuesAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Word__ValuesAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getWordAccess().getValuesAssignment()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWord"


    // $ANTLR start "entryRuleSystem"
    // InternalText.g:217:1: entryRuleSystem : ruleSystem EOF ;
    public final void entryRuleSystem() throws RecognitionException {
        try {
            // InternalText.g:218:1: ( ruleSystem EOF )
            // InternalText.g:219:1: ruleSystem EOF
            {
             before(grammarAccess.getSystemRule()); 
            pushFollow(FOLLOW_1);
            ruleSystem();

            state._fsp--;

             after(grammarAccess.getSystemRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSystem"


    // $ANTLR start "ruleSystem"
    // InternalText.g:226:1: ruleSystem : ( ( rule__System__Group__0 ) ) ;
    public final void ruleSystem() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:230:2: ( ( ( rule__System__Group__0 ) ) )
            // InternalText.g:231:2: ( ( rule__System__Group__0 ) )
            {
            // InternalText.g:231:2: ( ( rule__System__Group__0 ) )
            // InternalText.g:232:3: ( rule__System__Group__0 )
            {
             before(grammarAccess.getSystemAccess().getGroup()); 
            // InternalText.g:233:3: ( rule__System__Group__0 )
            // InternalText.g:233:4: rule__System__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__System__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSystemAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSystem"


    // $ANTLR start "entryRuleCommand"
    // InternalText.g:242:1: entryRuleCommand : ruleCommand EOF ;
    public final void entryRuleCommand() throws RecognitionException {
        try {
            // InternalText.g:243:1: ( ruleCommand EOF )
            // InternalText.g:244:1: ruleCommand EOF
            {
             before(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getCommandRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalText.g:251:1: ruleCommand : ( ( rule__Command__Alternatives ) ) ;
    public final void ruleCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:255:2: ( ( ( rule__Command__Alternatives ) ) )
            // InternalText.g:256:2: ( ( rule__Command__Alternatives ) )
            {
            // InternalText.g:256:2: ( ( rule__Command__Alternatives ) )
            // InternalText.g:257:3: ( rule__Command__Alternatives )
            {
             before(grammarAccess.getCommandAccess().getAlternatives()); 
            // InternalText.g:258:3: ( rule__Command__Alternatives )
            // InternalText.g:258:4: rule__Command__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Command__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getCommandAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleLoop"
    // InternalText.g:267:1: entryRuleLoop : ruleLoop EOF ;
    public final void entryRuleLoop() throws RecognitionException {
        try {
            // InternalText.g:268:1: ( ruleLoop EOF )
            // InternalText.g:269:1: ruleLoop EOF
            {
             before(grammarAccess.getLoopRule()); 
            pushFollow(FOLLOW_1);
            ruleLoop();

            state._fsp--;

             after(grammarAccess.getLoopRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLoop"


    // $ANTLR start "ruleLoop"
    // InternalText.g:276:1: ruleLoop : ( ( rule__Loop__Group__0 ) ) ;
    public final void ruleLoop() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:280:2: ( ( ( rule__Loop__Group__0 ) ) )
            // InternalText.g:281:2: ( ( rule__Loop__Group__0 ) )
            {
            // InternalText.g:281:2: ( ( rule__Loop__Group__0 ) )
            // InternalText.g:282:3: ( rule__Loop__Group__0 )
            {
             before(grammarAccess.getLoopAccess().getGroup()); 
            // InternalText.g:283:3: ( rule__Loop__Group__0 )
            // InternalText.g:283:4: rule__Loop__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Loop__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLoop"


    // $ANTLR start "entryRuleBranch"
    // InternalText.g:292:1: entryRuleBranch : ruleBranch EOF ;
    public final void entryRuleBranch() throws RecognitionException {
        try {
            // InternalText.g:293:1: ( ruleBranch EOF )
            // InternalText.g:294:1: ruleBranch EOF
            {
             before(grammarAccess.getBranchRule()); 
            pushFollow(FOLLOW_1);
            ruleBranch();

            state._fsp--;

             after(grammarAccess.getBranchRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBranch"


    // $ANTLR start "ruleBranch"
    // InternalText.g:301:1: ruleBranch : ( ( rule__Branch__Group__0 ) ) ;
    public final void ruleBranch() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:305:2: ( ( ( rule__Branch__Group__0 ) ) )
            // InternalText.g:306:2: ( ( rule__Branch__Group__0 ) )
            {
            // InternalText.g:306:2: ( ( rule__Branch__Group__0 ) )
            // InternalText.g:307:3: ( rule__Branch__Group__0 )
            {
             before(grammarAccess.getBranchAccess().getGroup()); 
            // InternalText.g:308:3: ( rule__Branch__Group__0 )
            // InternalText.g:308:4: rule__Branch__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBranch"


    // $ANTLR start "entryRuleCondition"
    // InternalText.g:317:1: entryRuleCondition : ruleCondition EOF ;
    public final void entryRuleCondition() throws RecognitionException {
        try {
            // InternalText.g:318:1: ( ruleCondition EOF )
            // InternalText.g:319:1: ruleCondition EOF
            {
             before(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getConditionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalText.g:326:1: ruleCondition : ( ( rule__Condition__Group__0 ) ) ;
    public final void ruleCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:330:2: ( ( ( rule__Condition__Group__0 ) ) )
            // InternalText.g:331:2: ( ( rule__Condition__Group__0 ) )
            {
            // InternalText.g:331:2: ( ( rule__Condition__Group__0 ) )
            // InternalText.g:332:3: ( rule__Condition__Group__0 )
            {
             before(grammarAccess.getConditionAccess().getGroup()); 
            // InternalText.g:333:3: ( rule__Condition__Group__0 )
            // InternalText.g:333:4: rule__Condition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Condition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleAssignment"
    // InternalText.g:342:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalText.g:343:1: ( ruleAssignment EOF )
            // InternalText.g:344:1: ruleAssignment EOF
            {
             before(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getAssignmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalText.g:351:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:355:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalText.g:356:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalText.g:356:2: ( ( rule__Assignment__Group__0 ) )
            // InternalText.g:357:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalText.g:358:3: ( rule__Assignment__Group__0 )
            // InternalText.g:358:4: rule__Assignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleCall"
    // InternalText.g:367:1: entryRuleCall : ruleCall EOF ;
    public final void entryRuleCall() throws RecognitionException {
        try {
            // InternalText.g:368:1: ( ruleCall EOF )
            // InternalText.g:369:1: ruleCall EOF
            {
             before(grammarAccess.getCallRule()); 
            pushFollow(FOLLOW_1);
            ruleCall();

            state._fsp--;

             after(grammarAccess.getCallRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCall"


    // $ANTLR start "ruleCall"
    // InternalText.g:376:1: ruleCall : ( ( rule__Call__Group__0 ) ) ;
    public final void ruleCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:380:2: ( ( ( rule__Call__Group__0 ) ) )
            // InternalText.g:381:2: ( ( rule__Call__Group__0 ) )
            {
            // InternalText.g:381:2: ( ( rule__Call__Group__0 ) )
            // InternalText.g:382:3: ( rule__Call__Group__0 )
            {
             before(grammarAccess.getCallAccess().getGroup()); 
            // InternalText.g:383:3: ( rule__Call__Group__0 )
            // InternalText.g:383:4: rule__Call__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Call__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCall"


    // $ANTLR start "entryRuleAccess"
    // InternalText.g:392:1: entryRuleAccess : ruleAccess EOF ;
    public final void entryRuleAccess() throws RecognitionException {
        try {
            // InternalText.g:393:1: ( ruleAccess EOF )
            // InternalText.g:394:1: ruleAccess EOF
            {
             before(grammarAccess.getAccessRule()); 
            pushFollow(FOLLOW_1);
            ruleAccess();

            state._fsp--;

             after(grammarAccess.getAccessRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAccess"


    // $ANTLR start "ruleAccess"
    // InternalText.g:401:1: ruleAccess : ( ( rule__Access__Group__0 ) ) ;
    public final void ruleAccess() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:405:2: ( ( ( rule__Access__Group__0 ) ) )
            // InternalText.g:406:2: ( ( rule__Access__Group__0 ) )
            {
            // InternalText.g:406:2: ( ( rule__Access__Group__0 ) )
            // InternalText.g:407:3: ( rule__Access__Group__0 )
            {
             before(grammarAccess.getAccessAccess().getGroup()); 
            // InternalText.g:408:3: ( rule__Access__Group__0 )
            // InternalText.g:408:4: rule__Access__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Access__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAccessAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAccess"


    // $ANTLR start "entryRuleExpression"
    // InternalText.g:417:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalText.g:418:1: ( ruleExpression EOF )
            // InternalText.g:419:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalText.g:426:1: ruleExpression : ( ( rule__Expression__Group__0 ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:430:2: ( ( ( rule__Expression__Group__0 ) ) )
            // InternalText.g:431:2: ( ( rule__Expression__Group__0 ) )
            {
            // InternalText.g:431:2: ( ( rule__Expression__Group__0 ) )
            // InternalText.g:432:3: ( rule__Expression__Group__0 )
            {
             before(grammarAccess.getExpressionAccess().getGroup()); 
            // InternalText.g:433:3: ( rule__Expression__Group__0 )
            // InternalText.g:433:4: rule__Expression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Expression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAnd"
    // InternalText.g:442:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // InternalText.g:443:1: ( ruleAnd EOF )
            // InternalText.g:444:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_1);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // InternalText.g:451:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:455:2: ( ( ( rule__And__Group__0 ) ) )
            // InternalText.g:456:2: ( ( rule__And__Group__0 ) )
            {
            // InternalText.g:456:2: ( ( rule__And__Group__0 ) )
            // InternalText.g:457:3: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // InternalText.g:458:3: ( rule__And__Group__0 )
            // InternalText.g:458:4: rule__And__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__And__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRulePrimaryB"
    // InternalText.g:467:1: entryRulePrimaryB : rulePrimaryB EOF ;
    public final void entryRulePrimaryB() throws RecognitionException {
        try {
            // InternalText.g:468:1: ( rulePrimaryB EOF )
            // InternalText.g:469:1: rulePrimaryB EOF
            {
             before(grammarAccess.getPrimaryBRule()); 
            pushFollow(FOLLOW_1);
            rulePrimaryB();

            state._fsp--;

             after(grammarAccess.getPrimaryBRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimaryB"


    // $ANTLR start "rulePrimaryB"
    // InternalText.g:476:1: rulePrimaryB : ( ( rule__PrimaryB__Alternatives ) ) ;
    public final void rulePrimaryB() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:480:2: ( ( ( rule__PrimaryB__Alternatives ) ) )
            // InternalText.g:481:2: ( ( rule__PrimaryB__Alternatives ) )
            {
            // InternalText.g:481:2: ( ( rule__PrimaryB__Alternatives ) )
            // InternalText.g:482:3: ( rule__PrimaryB__Alternatives )
            {
             before(grammarAccess.getPrimaryBAccess().getAlternatives()); 
            // InternalText.g:483:3: ( rule__PrimaryB__Alternatives )
            // InternalText.g:483:4: rule__PrimaryB__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryB__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryBAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimaryB"


    // $ANTLR start "entryRuleAtomicB"
    // InternalText.g:492:1: entryRuleAtomicB : ruleAtomicB EOF ;
    public final void entryRuleAtomicB() throws RecognitionException {
        try {
            // InternalText.g:493:1: ( ruleAtomicB EOF )
            // InternalText.g:494:1: ruleAtomicB EOF
            {
             before(grammarAccess.getAtomicBRule()); 
            pushFollow(FOLLOW_1);
            ruleAtomicB();

            state._fsp--;

             after(grammarAccess.getAtomicBRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAtomicB"


    // $ANTLR start "ruleAtomicB"
    // InternalText.g:501:1: ruleAtomicB : ( ( rule__AtomicB__Group__0 ) ) ;
    public final void ruleAtomicB() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:505:2: ( ( ( rule__AtomicB__Group__0 ) ) )
            // InternalText.g:506:2: ( ( rule__AtomicB__Group__0 ) )
            {
            // InternalText.g:506:2: ( ( rule__AtomicB__Group__0 ) )
            // InternalText.g:507:3: ( rule__AtomicB__Group__0 )
            {
             before(grammarAccess.getAtomicBAccess().getGroup()); 
            // InternalText.g:508:3: ( rule__AtomicB__Group__0 )
            // InternalText.g:508:4: rule__AtomicB__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AtomicB__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAtomicBAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAtomicB"


    // $ANTLR start "entryRuleAddition"
    // InternalText.g:517:1: entryRuleAddition : ruleAddition EOF ;
    public final void entryRuleAddition() throws RecognitionException {
        try {
            // InternalText.g:518:1: ( ruleAddition EOF )
            // InternalText.g:519:1: ruleAddition EOF
            {
             before(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAdditionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalText.g:526:1: ruleAddition : ( ( rule__Addition__Group__0 ) ) ;
    public final void ruleAddition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:530:2: ( ( ( rule__Addition__Group__0 ) ) )
            // InternalText.g:531:2: ( ( rule__Addition__Group__0 ) )
            {
            // InternalText.g:531:2: ( ( rule__Addition__Group__0 ) )
            // InternalText.g:532:3: ( rule__Addition__Group__0 )
            {
             before(grammarAccess.getAdditionAccess().getGroup()); 
            // InternalText.g:533:3: ( rule__Addition__Group__0 )
            // InternalText.g:533:4: rule__Addition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalText.g:542:1: entryRuleMultiplication : ruleMultiplication EOF ;
    public final void entryRuleMultiplication() throws RecognitionException {
        try {
            // InternalText.g:543:1: ( ruleMultiplication EOF )
            // InternalText.g:544:1: ruleMultiplication EOF
            {
             before(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getMultiplicationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalText.g:551:1: ruleMultiplication : ( ( rule__Multiplication__Group__0 ) ) ;
    public final void ruleMultiplication() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:555:2: ( ( ( rule__Multiplication__Group__0 ) ) )
            // InternalText.g:556:2: ( ( rule__Multiplication__Group__0 ) )
            {
            // InternalText.g:556:2: ( ( rule__Multiplication__Group__0 ) )
            // InternalText.g:557:3: ( rule__Multiplication__Group__0 )
            {
             before(grammarAccess.getMultiplicationAccess().getGroup()); 
            // InternalText.g:558:3: ( rule__Multiplication__Group__0 )
            // InternalText.g:558:4: rule__Multiplication__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRuleBitwise"
    // InternalText.g:567:1: entryRuleBitwise : ruleBitwise EOF ;
    public final void entryRuleBitwise() throws RecognitionException {
        try {
            // InternalText.g:568:1: ( ruleBitwise EOF )
            // InternalText.g:569:1: ruleBitwise EOF
            {
             before(grammarAccess.getBitwiseRule()); 
            pushFollow(FOLLOW_1);
            ruleBitwise();

            state._fsp--;

             after(grammarAccess.getBitwiseRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBitwise"


    // $ANTLR start "ruleBitwise"
    // InternalText.g:576:1: ruleBitwise : ( ( rule__Bitwise__Group__0 ) ) ;
    public final void ruleBitwise() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:580:2: ( ( ( rule__Bitwise__Group__0 ) ) )
            // InternalText.g:581:2: ( ( rule__Bitwise__Group__0 ) )
            {
            // InternalText.g:581:2: ( ( rule__Bitwise__Group__0 ) )
            // InternalText.g:582:3: ( rule__Bitwise__Group__0 )
            {
             before(grammarAccess.getBitwiseAccess().getGroup()); 
            // InternalText.g:583:3: ( rule__Bitwise__Group__0 )
            // InternalText.g:583:4: rule__Bitwise__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBitwiseAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBitwise"


    // $ANTLR start "entryRuleUnary"
    // InternalText.g:592:1: entryRuleUnary : ruleUnary EOF ;
    public final void entryRuleUnary() throws RecognitionException {
        try {
            // InternalText.g:593:1: ( ruleUnary EOF )
            // InternalText.g:594:1: ruleUnary EOF
            {
             before(grammarAccess.getUnaryRule()); 
            pushFollow(FOLLOW_1);
            ruleUnary();

            state._fsp--;

             after(grammarAccess.getUnaryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnary"


    // $ANTLR start "ruleUnary"
    // InternalText.g:601:1: ruleUnary : ( ( rule__Unary__Alternatives ) ) ;
    public final void ruleUnary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:605:2: ( ( ( rule__Unary__Alternatives ) ) )
            // InternalText.g:606:2: ( ( rule__Unary__Alternatives ) )
            {
            // InternalText.g:606:2: ( ( rule__Unary__Alternatives ) )
            // InternalText.g:607:3: ( rule__Unary__Alternatives )
            {
             before(grammarAccess.getUnaryAccess().getAlternatives()); 
            // InternalText.g:608:3: ( rule__Unary__Alternatives )
            // InternalText.g:608:4: rule__Unary__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Unary__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getUnaryAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnary"


    // $ANTLR start "entryRulePrimary"
    // InternalText.g:617:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // InternalText.g:618:1: ( rulePrimary EOF )
            // InternalText.g:619:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_1);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalText.g:626:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:630:2: ( ( ( rule__Primary__Alternatives ) ) )
            // InternalText.g:631:2: ( ( rule__Primary__Alternatives ) )
            {
            // InternalText.g:631:2: ( ( rule__Primary__Alternatives ) )
            // InternalText.g:632:3: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // InternalText.g:633:3: ( rule__Primary__Alternatives )
            // InternalText.g:633:4: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Primary__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "ruleRelationType"
    // InternalText.g:642:1: ruleRelationType : ( ( rule__RelationType__Alternatives ) ) ;
    public final void ruleRelationType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:646:1: ( ( ( rule__RelationType__Alternatives ) ) )
            // InternalText.g:647:2: ( ( rule__RelationType__Alternatives ) )
            {
            // InternalText.g:647:2: ( ( rule__RelationType__Alternatives ) )
            // InternalText.g:648:3: ( rule__RelationType__Alternatives )
            {
             before(grammarAccess.getRelationTypeAccess().getAlternatives()); 
            // InternalText.g:649:3: ( rule__RelationType__Alternatives )
            // InternalText.g:649:4: rule__RelationType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__RelationType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRelationTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelationType"


    // $ANTLR start "ruleAssignmentType"
    // InternalText.g:658:1: ruleAssignmentType : ( ( rule__AssignmentType__Alternatives ) ) ;
    public final void ruleAssignmentType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:662:1: ( ( ( rule__AssignmentType__Alternatives ) ) )
            // InternalText.g:663:2: ( ( rule__AssignmentType__Alternatives ) )
            {
            // InternalText.g:663:2: ( ( rule__AssignmentType__Alternatives ) )
            // InternalText.g:664:3: ( rule__AssignmentType__Alternatives )
            {
             before(grammarAccess.getAssignmentTypeAccess().getAlternatives()); 
            // InternalText.g:665:3: ( rule__AssignmentType__Alternatives )
            // InternalText.g:665:4: rule__AssignmentType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__AssignmentType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssignmentType"


    // $ANTLR start "rule__Game__Alternatives"
    // InternalText.g:673:1: rule__Game__Alternatives : ( ( ( rule__Game__EntitiesAssignment_0 ) ) | ( ( rule__Game__SystemsAssignment_1 ) ) );
    public final void rule__Game__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:677:1: ( ( ( rule__Game__EntitiesAssignment_0 ) ) | ( ( rule__Game__SystemsAssignment_1 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==30) ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2==RULE_ID) ) {
                        int LA4_4 = input.LA(4);

                        if ( (LA4_4==RULE_ID||LA4_4==28) ) {
                            alt4=1;
                        }
                        else if ( (LA4_4==14||(LA4_4>=18 && LA4_4<=27)||LA4_4==40||LA4_4==42) ) {
                            alt4=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 4, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA4_2==34||LA4_2==38) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_1==RULE_ID||LA4_1==28) ) {
                    alt4=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalText.g:678:2: ( ( rule__Game__EntitiesAssignment_0 ) )
                    {
                    // InternalText.g:678:2: ( ( rule__Game__EntitiesAssignment_0 ) )
                    // InternalText.g:679:3: ( rule__Game__EntitiesAssignment_0 )
                    {
                     before(grammarAccess.getGameAccess().getEntitiesAssignment_0()); 
                    // InternalText.g:680:3: ( rule__Game__EntitiesAssignment_0 )
                    // InternalText.g:680:4: rule__Game__EntitiesAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Game__EntitiesAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGameAccess().getEntitiesAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:684:2: ( ( rule__Game__SystemsAssignment_1 ) )
                    {
                    // InternalText.g:684:2: ( ( rule__Game__SystemsAssignment_1 ) )
                    // InternalText.g:685:3: ( rule__Game__SystemsAssignment_1 )
                    {
                     before(grammarAccess.getGameAccess().getSystemsAssignment_1()); 
                    // InternalText.g:686:3: ( rule__Game__SystemsAssignment_1 )
                    // InternalText.g:686:4: rule__Game__SystemsAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Game__SystemsAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGameAccess().getSystemsAssignment_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Game__Alternatives"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalText.g:694:1: rule__Value__Alternatives : ( ( ruleVector ) | ( ruleWord ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:698:1: ( ( ruleVector ) | ( ruleWord ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_REAL) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_ID) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalText.g:699:2: ( ruleVector )
                    {
                    // InternalText.g:699:2: ( ruleVector )
                    // InternalText.g:700:3: ruleVector
                    {
                     before(grammarAccess.getValueAccess().getVectorParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleVector();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getVectorParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:705:2: ( ruleWord )
                    {
                    // InternalText.g:705:2: ( ruleWord )
                    // InternalText.g:706:3: ruleWord
                    {
                     before(grammarAccess.getValueAccess().getWordParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleWord();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getWordParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Alternatives"


    // $ANTLR start "rule__Command__Alternatives"
    // InternalText.g:715:1: rule__Command__Alternatives : ( ( ruleLoop ) | ( ruleBranch ) | ( ruleAssignment ) | ( ruleCall ) );
    public final void rule__Command__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:719:1: ( ( ruleLoop ) | ( ruleBranch ) | ( ruleAssignment ) | ( ruleCall ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt6=1;
                }
                break;
            case 38:
                {
                alt6=2;
                }
                break;
            case RULE_ID:
                {
                int LA6_3 = input.LA(2);

                if ( (LA6_3==40) ) {
                    alt6=4;
                }
                else if ( (LA6_3==14||(LA6_3>=18 && LA6_3<=27)||LA6_3==42) ) {
                    alt6=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalText.g:720:2: ( ruleLoop )
                    {
                    // InternalText.g:720:2: ( ruleLoop )
                    // InternalText.g:721:3: ruleLoop
                    {
                     before(grammarAccess.getCommandAccess().getLoopParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleLoop();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getLoopParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:726:2: ( ruleBranch )
                    {
                    // InternalText.g:726:2: ( ruleBranch )
                    // InternalText.g:727:3: ruleBranch
                    {
                     before(grammarAccess.getCommandAccess().getBranchParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleBranch();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getBranchParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:732:2: ( ruleAssignment )
                    {
                    // InternalText.g:732:2: ( ruleAssignment )
                    // InternalText.g:733:3: ruleAssignment
                    {
                     before(grammarAccess.getCommandAccess().getAssignmentParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleAssignment();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getAssignmentParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:738:2: ( ruleCall )
                    {
                    // InternalText.g:738:2: ( ruleCall )
                    // InternalText.g:739:3: ruleCall
                    {
                     before(grammarAccess.getCommandAccess().getCallParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleCall();

                    state._fsp--;

                     after(grammarAccess.getCommandAccess().getCallParserRuleCall_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Command__Alternatives"


    // $ANTLR start "rule__PrimaryB__Alternatives"
    // InternalText.g:748:1: rule__PrimaryB__Alternatives : ( ( ruleAtomicB ) | ( ( rule__PrimaryB__Group_1__0 ) ) );
    public final void rule__PrimaryB__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:752:1: ( ( ruleAtomicB ) | ( ( rule__PrimaryB__Group_1__0 ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID||LA7_0==40||LA7_0==51) ) {
                alt7=1;
            }
            else if ( (LA7_0==45) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalText.g:753:2: ( ruleAtomicB )
                    {
                    // InternalText.g:753:2: ( ruleAtomicB )
                    // InternalText.g:754:3: ruleAtomicB
                    {
                     before(grammarAccess.getPrimaryBAccess().getAtomicBParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleAtomicB();

                    state._fsp--;

                     after(grammarAccess.getPrimaryBAccess().getAtomicBParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:759:2: ( ( rule__PrimaryB__Group_1__0 ) )
                    {
                    // InternalText.g:759:2: ( ( rule__PrimaryB__Group_1__0 ) )
                    // InternalText.g:760:3: ( rule__PrimaryB__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryBAccess().getGroup_1()); 
                    // InternalText.g:761:3: ( rule__PrimaryB__Group_1__0 )
                    // InternalText.g:761:4: rule__PrimaryB__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__PrimaryB__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryBAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Alternatives"


    // $ANTLR start "rule__Addition__Alternatives_1_0"
    // InternalText.g:769:1: rule__Addition__Alternatives_1_0 : ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) );
    public final void rule__Addition__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:773:1: ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==46) ) {
                alt8=1;
            }
            else if ( (LA8_0==47) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalText.g:774:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    {
                    // InternalText.g:774:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    // InternalText.g:775:3: ( rule__Addition__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_0()); 
                    // InternalText.g:776:3: ( rule__Addition__Group_1_0_0__0 )
                    // InternalText.g:776:4: rule__Addition__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Addition__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAdditionAccess().getGroup_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:780:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    {
                    // InternalText.g:780:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    // InternalText.g:781:3: ( rule__Addition__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_1()); 
                    // InternalText.g:782:3: ( rule__Addition__Group_1_0_1__0 )
                    // InternalText.g:782:4: rule__Addition__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Addition__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAdditionAccess().getGroup_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Alternatives_1_0"


    // $ANTLR start "rule__Multiplication__Alternatives_1_0"
    // InternalText.g:790:1: rule__Multiplication__Alternatives_1_0 : ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) | ( ( rule__Multiplication__Group_1_0_2__0 ) ) );
    public final void rule__Multiplication__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:794:1: ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) | ( ( rule__Multiplication__Group_1_0_2__0 ) ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt9=1;
                }
                break;
            case 49:
                {
                alt9=2;
                }
                break;
            case 50:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalText.g:795:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    {
                    // InternalText.g:795:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    // InternalText.g:796:3: ( rule__Multiplication__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); 
                    // InternalText.g:797:3: ( rule__Multiplication__Group_1_0_0__0 )
                    // InternalText.g:797:4: rule__Multiplication__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:801:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    {
                    // InternalText.g:801:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    // InternalText.g:802:3: ( rule__Multiplication__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); 
                    // InternalText.g:803:3: ( rule__Multiplication__Group_1_0_1__0 )
                    // InternalText.g:803:4: rule__Multiplication__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:807:2: ( ( rule__Multiplication__Group_1_0_2__0 ) )
                    {
                    // InternalText.g:807:2: ( ( rule__Multiplication__Group_1_0_2__0 ) )
                    // InternalText.g:808:3: ( rule__Multiplication__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_2()); 
                    // InternalText.g:809:3: ( rule__Multiplication__Group_1_0_2__0 )
                    // InternalText.g:809:4: rule__Multiplication__Group_1_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMultiplicationAccess().getGroup_1_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Alternatives_1_0"


    // $ANTLR start "rule__Bitwise__OpAlternatives_1_1_0"
    // InternalText.g:817:1: rule__Bitwise__OpAlternatives_1_1_0 : ( ( '&' ) | ( '|' ) | ( '^' ) | ( '<<' ) | ( '>>' ) );
    public final void rule__Bitwise__OpAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:821:1: ( ( '&' ) | ( '|' ) | ( '^' ) | ( '<<' ) | ( '>>' ) )
            int alt10=5;
            switch ( input.LA(1) ) {
            case 7:
                {
                alt10=1;
                }
                break;
            case 8:
                {
                alt10=2;
                }
                break;
            case 9:
                {
                alt10=3;
                }
                break;
            case 10:
                {
                alt10=4;
                }
                break;
            case 11:
                {
                alt10=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalText.g:822:2: ( '&' )
                    {
                    // InternalText.g:822:2: ( '&' )
                    // InternalText.g:823:3: '&'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0()); 
                    match(input,7,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:828:2: ( '|' )
                    {
                    // InternalText.g:828:2: ( '|' )
                    // InternalText.g:829:3: '|'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1()); 
                    match(input,8,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:834:2: ( '^' )
                    {
                    // InternalText.g:834:2: ( '^' )
                    // InternalText.g:835:3: '^'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2()); 
                    match(input,9,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:840:2: ( '<<' )
                    {
                    // InternalText.g:840:2: ( '<<' )
                    // InternalText.g:841:3: '<<'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3()); 
                    match(input,10,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalText.g:846:2: ( '>>' )
                    {
                    // InternalText.g:846:2: ( '>>' )
                    // InternalText.g:847:3: '>>'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpGreaterThanSignGreaterThanSignKeyword_1_1_0_4()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpGreaterThanSignGreaterThanSignKeyword_1_1_0_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__OpAlternatives_1_1_0"


    // $ANTLR start "rule__Unary__Alternatives"
    // InternalText.g:856:1: rule__Unary__Alternatives : ( ( ( rule__Unary__Group_0__0 ) ) | ( ( rule__Unary__Group_1__0 ) ) );
    public final void rule__Unary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:860:1: ( ( ( rule__Unary__Group_0__0 ) ) | ( ( rule__Unary__Group_1__0 ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==51) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_ID||LA11_0==40) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalText.g:861:2: ( ( rule__Unary__Group_0__0 ) )
                    {
                    // InternalText.g:861:2: ( ( rule__Unary__Group_0__0 ) )
                    // InternalText.g:862:3: ( rule__Unary__Group_0__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_0()); 
                    // InternalText.g:863:3: ( rule__Unary__Group_0__0 )
                    // InternalText.g:863:4: rule__Unary__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Unary__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUnaryAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:867:2: ( ( rule__Unary__Group_1__0 ) )
                    {
                    // InternalText.g:867:2: ( ( rule__Unary__Group_1__0 ) )
                    // InternalText.g:868:3: ( rule__Unary__Group_1__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1()); 
                    // InternalText.g:869:3: ( rule__Unary__Group_1__0 )
                    // InternalText.g:869:4: rule__Unary__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Unary__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUnaryAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Alternatives"


    // $ANTLR start "rule__Unary__Alternatives_1_1"
    // InternalText.g:877:1: rule__Unary__Alternatives_1_1 : ( ( ( rule__Unary__Group_1_1_0__0 ) ) | ( ( rule__Unary__Group_1_1_1__0 ) ) );
    public final void rule__Unary__Alternatives_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:881:1: ( ( ( rule__Unary__Group_1_1_0__0 ) ) | ( ( rule__Unary__Group_1_1_1__0 ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==52) ) {
                alt12=1;
            }
            else if ( (LA12_0==53) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalText.g:882:2: ( ( rule__Unary__Group_1_1_0__0 ) )
                    {
                    // InternalText.g:882:2: ( ( rule__Unary__Group_1_1_0__0 ) )
                    // InternalText.g:883:3: ( rule__Unary__Group_1_1_0__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1_1_0()); 
                    // InternalText.g:884:3: ( rule__Unary__Group_1_1_0__0 )
                    // InternalText.g:884:4: rule__Unary__Group_1_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Unary__Group_1_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUnaryAccess().getGroup_1_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:888:2: ( ( rule__Unary__Group_1_1_1__0 ) )
                    {
                    // InternalText.g:888:2: ( ( rule__Unary__Group_1_1_1__0 ) )
                    // InternalText.g:889:3: ( rule__Unary__Group_1_1_1__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1_1_1()); 
                    // InternalText.g:890:3: ( rule__Unary__Group_1_1_1__0 )
                    // InternalText.g:890:4: rule__Unary__Group_1_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Unary__Group_1_1_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getUnaryAccess().getGroup_1_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Alternatives_1_1"


    // $ANTLR start "rule__Primary__Alternatives"
    // InternalText.g:898:1: rule__Primary__Alternatives : ( ( ruleAccess ) | ( ruleCall ) | ( ( rule__Primary__Group_2__0 ) ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:902:1: ( ( ruleAccess ) | ( ruleCall ) | ( ( rule__Primary__Group_2__0 ) ) )
            int alt13=3;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==EOF||LA13_1==RULE_ID||(LA13_1>=7 && LA13_1<=17)||(LA13_1>=28 && LA13_1<=29)||LA13_1==31||LA13_1==34||LA13_1==38||(LA13_1>=41 && LA13_1<=44)||(LA13_1>=46 && LA13_1<=50)||(LA13_1>=52 && LA13_1<=53)) ) {
                    alt13=1;
                }
                else if ( (LA13_1==40) ) {
                    alt13=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA13_0==40) ) {
                alt13=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalText.g:903:2: ( ruleAccess )
                    {
                    // InternalText.g:903:2: ( ruleAccess )
                    // InternalText.g:904:3: ruleAccess
                    {
                     before(grammarAccess.getPrimaryAccess().getAccessParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleAccess();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getAccessParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:909:2: ( ruleCall )
                    {
                    // InternalText.g:909:2: ( ruleCall )
                    // InternalText.g:910:3: ruleCall
                    {
                     before(grammarAccess.getPrimaryAccess().getCallParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleCall();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getCallParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:915:2: ( ( rule__Primary__Group_2__0 ) )
                    {
                    // InternalText.g:915:2: ( ( rule__Primary__Group_2__0 ) )
                    // InternalText.g:916:3: ( rule__Primary__Group_2__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_2()); 
                    // InternalText.g:917:3: ( rule__Primary__Group_2__0 )
                    // InternalText.g:917:4: rule__Primary__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Primary__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Alternatives"


    // $ANTLR start "rule__RelationType__Alternatives"
    // InternalText.g:925:1: rule__RelationType__Alternatives : ( ( ( '>' ) ) | ( ( '<' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '<=' ) ) );
    public final void rule__RelationType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:929:1: ( ( ( '>' ) ) | ( ( '<' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '<=' ) ) )
            int alt14=6;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt14=1;
                }
                break;
            case 13:
                {
                alt14=2;
                }
                break;
            case 14:
                {
                alt14=3;
                }
                break;
            case 15:
                {
                alt14=4;
                }
                break;
            case 16:
                {
                alt14=5;
                }
                break;
            case 17:
                {
                alt14=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalText.g:930:2: ( ( '>' ) )
                    {
                    // InternalText.g:930:2: ( ( '>' ) )
                    // InternalText.g:931:3: ( '>' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0()); 
                    // InternalText.g:932:3: ( '>' )
                    // InternalText.g:932:4: '>'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:936:2: ( ( '<' ) )
                    {
                    // InternalText.g:936:2: ( ( '<' ) )
                    // InternalText.g:937:3: ( '<' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1()); 
                    // InternalText.g:938:3: ( '<' )
                    // InternalText.g:938:4: '<'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:942:2: ( ( '=' ) )
                    {
                    // InternalText.g:942:2: ( ( '=' ) )
                    // InternalText.g:943:3: ( '=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2()); 
                    // InternalText.g:944:3: ( '=' )
                    // InternalText.g:944:4: '='
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:948:2: ( ( '!=' ) )
                    {
                    // InternalText.g:948:2: ( ( '!=' ) )
                    // InternalText.g:949:3: ( '!=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3()); 
                    // InternalText.g:950:3: ( '!=' )
                    // InternalText.g:950:4: '!='
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalText.g:954:2: ( ( '>=' ) )
                    {
                    // InternalText.g:954:2: ( ( '>=' ) )
                    // InternalText.g:955:3: ( '>=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4()); 
                    // InternalText.g:956:3: ( '>=' )
                    // InternalText.g:956:4: '>='
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalText.g:960:2: ( ( '<=' ) )
                    {
                    // InternalText.g:960:2: ( ( '<=' ) )
                    // InternalText.g:961:3: ( '<=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5()); 
                    // InternalText.g:962:3: ( '<=' )
                    // InternalText.g:962:4: '<='
                    {
                    match(input,17,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationType__Alternatives"


    // $ANTLR start "rule__AssignmentType__Alternatives"
    // InternalText.g:970:1: rule__AssignmentType__Alternatives : ( ( ( '=' ) ) | ( ( '+=' ) ) | ( ( '-=' ) ) | ( ( '*=' ) ) | ( ( '/=' ) ) | ( ( '%=' ) ) | ( ( '&=' ) ) | ( ( '|=' ) ) | ( ( '^=' ) ) | ( ( '<<=' ) ) | ( ( '>>=' ) ) );
    public final void rule__AssignmentType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:974:1: ( ( ( '=' ) ) | ( ( '+=' ) ) | ( ( '-=' ) ) | ( ( '*=' ) ) | ( ( '/=' ) ) | ( ( '%=' ) ) | ( ( '&=' ) ) | ( ( '|=' ) ) | ( ( '^=' ) ) | ( ( '<<=' ) ) | ( ( '>>=' ) ) )
            int alt15=11;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt15=1;
                }
                break;
            case 18:
                {
                alt15=2;
                }
                break;
            case 19:
                {
                alt15=3;
                }
                break;
            case 20:
                {
                alt15=4;
                }
                break;
            case 21:
                {
                alt15=5;
                }
                break;
            case 22:
                {
                alt15=6;
                }
                break;
            case 23:
                {
                alt15=7;
                }
                break;
            case 24:
                {
                alt15=8;
                }
                break;
            case 25:
                {
                alt15=9;
                }
                break;
            case 26:
                {
                alt15=10;
                }
                break;
            case 27:
                {
                alt15=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // InternalText.g:975:2: ( ( '=' ) )
                    {
                    // InternalText.g:975:2: ( ( '=' ) )
                    // InternalText.g:976:3: ( '=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0()); 
                    // InternalText.g:977:3: ( '=' )
                    // InternalText.g:977:4: '='
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:981:2: ( ( '+=' ) )
                    {
                    // InternalText.g:981:2: ( ( '+=' ) )
                    // InternalText.g:982:3: ( '+=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1()); 
                    // InternalText.g:983:3: ( '+=' )
                    // InternalText.g:983:4: '+='
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:987:2: ( ( '-=' ) )
                    {
                    // InternalText.g:987:2: ( ( '-=' ) )
                    // InternalText.g:988:3: ( '-=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2()); 
                    // InternalText.g:989:3: ( '-=' )
                    // InternalText.g:989:4: '-='
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:993:2: ( ( '*=' ) )
                    {
                    // InternalText.g:993:2: ( ( '*=' ) )
                    // InternalText.g:994:3: ( '*=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3()); 
                    // InternalText.g:995:3: ( '*=' )
                    // InternalText.g:995:4: '*='
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalText.g:999:2: ( ( '/=' ) )
                    {
                    // InternalText.g:999:2: ( ( '/=' ) )
                    // InternalText.g:1000:3: ( '/=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4()); 
                    // InternalText.g:1001:3: ( '/=' )
                    // InternalText.g:1001:4: '/='
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalText.g:1005:2: ( ( '%=' ) )
                    {
                    // InternalText.g:1005:2: ( ( '%=' ) )
                    // InternalText.g:1006:3: ( '%=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5()); 
                    // InternalText.g:1007:3: ( '%=' )
                    // InternalText.g:1007:4: '%='
                    {
                    match(input,22,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalText.g:1011:2: ( ( '&=' ) )
                    {
                    // InternalText.g:1011:2: ( ( '&=' ) )
                    // InternalText.g:1012:3: ( '&=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6()); 
                    // InternalText.g:1013:3: ( '&=' )
                    // InternalText.g:1013:4: '&='
                    {
                    match(input,23,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalText.g:1017:2: ( ( '|=' ) )
                    {
                    // InternalText.g:1017:2: ( ( '|=' ) )
                    // InternalText.g:1018:3: ( '|=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7()); 
                    // InternalText.g:1019:3: ( '|=' )
                    // InternalText.g:1019:4: '|='
                    {
                    match(input,24,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalText.g:1023:2: ( ( '^=' ) )
                    {
                    // InternalText.g:1023:2: ( ( '^=' ) )
                    // InternalText.g:1024:3: ( '^=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8()); 
                    // InternalText.g:1025:3: ( '^=' )
                    // InternalText.g:1025:4: '^='
                    {
                    match(input,25,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalText.g:1029:2: ( ( '<<=' ) )
                    {
                    // InternalText.g:1029:2: ( ( '<<=' ) )
                    // InternalText.g:1030:3: ( '<<=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9()); 
                    // InternalText.g:1031:3: ( '<<=' )
                    // InternalText.g:1031:4: '<<='
                    {
                    match(input,26,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalText.g:1035:2: ( ( '>>=' ) )
                    {
                    // InternalText.g:1035:2: ( ( '>>=' ) )
                    // InternalText.g:1036:3: ( '>>=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getShiftRightEnumLiteralDeclaration_10()); 
                    // InternalText.g:1037:3: ( '>>=' )
                    // InternalText.g:1037:4: '>>='
                    {
                    match(input,27,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getShiftRightEnumLiteralDeclaration_10()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AssignmentType__Alternatives"


    // $ANTLR start "rule__Entity__Group__0"
    // InternalText.g:1045:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1049:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // InternalText.g:1050:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Entity__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0"


    // $ANTLR start "rule__Entity__Group__0__Impl"
    // InternalText.g:1057:1: rule__Entity__Group__0__Impl : ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1061:1: ( ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) ) )
            // InternalText.g:1062:1: ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) )
            {
            // InternalText.g:1062:1: ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) )
            // InternalText.g:1063:2: ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* )
            {
            // InternalText.g:1063:2: ( ( rule__Entity__NameAssignment_0 ) )
            // InternalText.g:1064:3: ( rule__Entity__NameAssignment_0 )
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_0()); 
            // InternalText.g:1065:3: ( rule__Entity__NameAssignment_0 )
            // InternalText.g:1065:4: rule__Entity__NameAssignment_0
            {
            pushFollow(FOLLOW_3);
            rule__Entity__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getNameAssignment_0()); 

            }

            // InternalText.g:1068:2: ( ( rule__Entity__NameAssignment_0 )* )
            // InternalText.g:1069:3: ( rule__Entity__NameAssignment_0 )*
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_0()); 
            // InternalText.g:1070:3: ( rule__Entity__NameAssignment_0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalText.g:1070:4: rule__Entity__NameAssignment_0
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Entity__NameAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getNameAssignment_0()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0__Impl"


    // $ANTLR start "rule__Entity__Group__1"
    // InternalText.g:1079:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1083:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // InternalText.g:1084:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Entity__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1"


    // $ANTLR start "rule__Entity__Group__1__Impl"
    // InternalText.g:1091:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__Group_1__0 )? ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1095:1: ( ( ( rule__Entity__Group_1__0 )? ) )
            // InternalText.g:1096:1: ( ( rule__Entity__Group_1__0 )? )
            {
            // InternalText.g:1096:1: ( ( rule__Entity__Group_1__0 )? )
            // InternalText.g:1097:2: ( rule__Entity__Group_1__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_1()); 
            // InternalText.g:1098:2: ( rule__Entity__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==30) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalText.g:1098:3: rule__Entity__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Entity__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1__Impl"


    // $ANTLR start "rule__Entity__Group__2"
    // InternalText.g:1106:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1110:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // InternalText.g:1111:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Entity__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2"


    // $ANTLR start "rule__Entity__Group__2__Impl"
    // InternalText.g:1118:1: rule__Entity__Group__2__Impl : ( '{' ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1122:1: ( ( '{' ) )
            // InternalText.g:1123:1: ( '{' )
            {
            // InternalText.g:1123:1: ( '{' )
            // InternalText.g:1124:2: '{'
            {
             before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2__Impl"


    // $ANTLR start "rule__Entity__Group__3"
    // InternalText.g:1133:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1137:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // InternalText.g:1138:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Entity__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3"


    // $ANTLR start "rule__Entity__Group__3__Impl"
    // InternalText.g:1145:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__Group_3__0 )? ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1149:1: ( ( ( rule__Entity__Group_3__0 )? ) )
            // InternalText.g:1150:1: ( ( rule__Entity__Group_3__0 )? )
            {
            // InternalText.g:1150:1: ( ( rule__Entity__Group_3__0 )? )
            // InternalText.g:1151:2: ( rule__Entity__Group_3__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_3()); 
            // InternalText.g:1152:2: ( rule__Entity__Group_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalText.g:1152:3: rule__Entity__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Entity__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3__Impl"


    // $ANTLR start "rule__Entity__Group__4"
    // InternalText.g:1160:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1164:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // InternalText.g:1165:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__Entity__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4"


    // $ANTLR start "rule__Entity__Group__4__Impl"
    // InternalText.g:1172:1: rule__Entity__Group__4__Impl : ( '}' ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1176:1: ( ( '}' ) )
            // InternalText.g:1177:1: ( '}' )
            {
            // InternalText.g:1177:1: ( '}' )
            // InternalText.g:1178:2: '}'
            {
             before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4__Impl"


    // $ANTLR start "rule__Entity__Group__5"
    // InternalText.g:1187:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1191:1: ( rule__Entity__Group__5__Impl )
            // InternalText.g:1192:2: rule__Entity__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5"


    // $ANTLR start "rule__Entity__Group__5__Impl"
    // InternalText.g:1198:1: rule__Entity__Group__5__Impl : ( ( rule__Entity__Group_5__0 )? ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1202:1: ( ( ( rule__Entity__Group_5__0 )? ) )
            // InternalText.g:1203:1: ( ( rule__Entity__Group_5__0 )? )
            {
            // InternalText.g:1203:1: ( ( rule__Entity__Group_5__0 )? )
            // InternalText.g:1204:2: ( rule__Entity__Group_5__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_5()); 
            // InternalText.g:1205:2: ( rule__Entity__Group_5__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==32) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalText.g:1205:3: rule__Entity__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Entity__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5__Impl"


    // $ANTLR start "rule__Entity__Group_1__0"
    // InternalText.g:1214:1: rule__Entity__Group_1__0 : rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1 ;
    public final void rule__Entity__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1218:1: ( rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1 )
            // InternalText.g:1219:2: rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Entity__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_1__0"


    // $ANTLR start "rule__Entity__Group_1__0__Impl"
    // InternalText.g:1226:1: rule__Entity__Group_1__0__Impl : ( ':' ) ;
    public final void rule__Entity__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1230:1: ( ( ':' ) )
            // InternalText.g:1231:1: ( ':' )
            {
            // InternalText.g:1231:1: ( ':' )
            // InternalText.g:1232:2: ':'
            {
             before(grammarAccess.getEntityAccess().getColonKeyword_1_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getColonKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_1__0__Impl"


    // $ANTLR start "rule__Entity__Group_1__1"
    // InternalText.g:1241:1: rule__Entity__Group_1__1 : rule__Entity__Group_1__1__Impl ;
    public final void rule__Entity__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1245:1: ( rule__Entity__Group_1__1__Impl )
            // InternalText.g:1246:2: rule__Entity__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_1__1"


    // $ANTLR start "rule__Entity__Group_1__1__Impl"
    // InternalText.g:1252:1: rule__Entity__Group_1__1__Impl : ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) ) ;
    public final void rule__Entity__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1256:1: ( ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) ) )
            // InternalText.g:1257:1: ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) )
            {
            // InternalText.g:1257:1: ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) )
            // InternalText.g:1258:2: ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* )
            {
            // InternalText.g:1258:2: ( ( rule__Entity__BaseAssignment_1_1 ) )
            // InternalText.g:1259:3: ( rule__Entity__BaseAssignment_1_1 )
            {
             before(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 
            // InternalText.g:1260:3: ( rule__Entity__BaseAssignment_1_1 )
            // InternalText.g:1260:4: rule__Entity__BaseAssignment_1_1
            {
            pushFollow(FOLLOW_3);
            rule__Entity__BaseAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 

            }

            // InternalText.g:1263:2: ( ( rule__Entity__BaseAssignment_1_1 )* )
            // InternalText.g:1264:3: ( rule__Entity__BaseAssignment_1_1 )*
            {
             before(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 
            // InternalText.g:1265:3: ( rule__Entity__BaseAssignment_1_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalText.g:1265:4: rule__Entity__BaseAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Entity__BaseAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_1__1__Impl"


    // $ANTLR start "rule__Entity__Group_3__0"
    // InternalText.g:1275:1: rule__Entity__Group_3__0 : rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 ;
    public final void rule__Entity__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1279:1: ( rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 )
            // InternalText.g:1280:2: rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1
            {
            pushFollow(FOLLOW_9);
            rule__Entity__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__0"


    // $ANTLR start "rule__Entity__Group_3__0__Impl"
    // InternalText.g:1287:1: rule__Entity__Group_3__0__Impl : ( ( rule__Entity__ComponentsAssignment_3_0 ) ) ;
    public final void rule__Entity__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1291:1: ( ( ( rule__Entity__ComponentsAssignment_3_0 ) ) )
            // InternalText.g:1292:1: ( ( rule__Entity__ComponentsAssignment_3_0 ) )
            {
            // InternalText.g:1292:1: ( ( rule__Entity__ComponentsAssignment_3_0 ) )
            // InternalText.g:1293:2: ( rule__Entity__ComponentsAssignment_3_0 )
            {
             before(grammarAccess.getEntityAccess().getComponentsAssignment_3_0()); 
            // InternalText.g:1294:2: ( rule__Entity__ComponentsAssignment_3_0 )
            // InternalText.g:1294:3: rule__Entity__ComponentsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Entity__ComponentsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getComponentsAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__0__Impl"


    // $ANTLR start "rule__Entity__Group_3__1"
    // InternalText.g:1302:1: rule__Entity__Group_3__1 : rule__Entity__Group_3__1__Impl ;
    public final void rule__Entity__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1306:1: ( rule__Entity__Group_3__1__Impl )
            // InternalText.g:1307:2: rule__Entity__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__1"


    // $ANTLR start "rule__Entity__Group_3__1__Impl"
    // InternalText.g:1313:1: rule__Entity__Group_3__1__Impl : ( ( rule__Entity__Group_3_1__0 )* ) ;
    public final void rule__Entity__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1317:1: ( ( ( rule__Entity__Group_3_1__0 )* ) )
            // InternalText.g:1318:1: ( ( rule__Entity__Group_3_1__0 )* )
            {
            // InternalText.g:1318:1: ( ( rule__Entity__Group_3_1__0 )* )
            // InternalText.g:1319:2: ( rule__Entity__Group_3_1__0 )*
            {
             before(grammarAccess.getEntityAccess().getGroup_3_1()); 
            // InternalText.g:1320:2: ( rule__Entity__Group_3_1__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==31) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalText.g:1320:3: rule__Entity__Group_3_1__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__Entity__Group_3_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getGroup_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3__1__Impl"


    // $ANTLR start "rule__Entity__Group_3_1__0"
    // InternalText.g:1329:1: rule__Entity__Group_3_1__0 : rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1 ;
    public final void rule__Entity__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1333:1: ( rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1 )
            // InternalText.g:1334:2: rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Entity__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3_1__0"


    // $ANTLR start "rule__Entity__Group_3_1__0__Impl"
    // InternalText.g:1341:1: rule__Entity__Group_3_1__0__Impl : ( ',' ) ;
    public final void rule__Entity__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1345:1: ( ( ',' ) )
            // InternalText.g:1346:1: ( ',' )
            {
            // InternalText.g:1346:1: ( ',' )
            // InternalText.g:1347:2: ','
            {
             before(grammarAccess.getEntityAccess().getCommaKeyword_3_1_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getCommaKeyword_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3_1__0__Impl"


    // $ANTLR start "rule__Entity__Group_3_1__1"
    // InternalText.g:1356:1: rule__Entity__Group_3_1__1 : rule__Entity__Group_3_1__1__Impl ;
    public final void rule__Entity__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1360:1: ( rule__Entity__Group_3_1__1__Impl )
            // InternalText.g:1361:2: rule__Entity__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3_1__1"


    // $ANTLR start "rule__Entity__Group_3_1__1__Impl"
    // InternalText.g:1367:1: rule__Entity__Group_3_1__1__Impl : ( ( rule__Entity__ComponentsAssignment_3_1_1 ) ) ;
    public final void rule__Entity__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1371:1: ( ( ( rule__Entity__ComponentsAssignment_3_1_1 ) ) )
            // InternalText.g:1372:1: ( ( rule__Entity__ComponentsAssignment_3_1_1 ) )
            {
            // InternalText.g:1372:1: ( ( rule__Entity__ComponentsAssignment_3_1_1 ) )
            // InternalText.g:1373:2: ( rule__Entity__ComponentsAssignment_3_1_1 )
            {
             before(grammarAccess.getEntityAccess().getComponentsAssignment_3_1_1()); 
            // InternalText.g:1374:2: ( rule__Entity__ComponentsAssignment_3_1_1 )
            // InternalText.g:1374:3: rule__Entity__ComponentsAssignment_3_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Entity__ComponentsAssignment_3_1_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getComponentsAssignment_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_3_1__1__Impl"


    // $ANTLR start "rule__Entity__Group_5__0"
    // InternalText.g:1383:1: rule__Entity__Group_5__0 : rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1 ;
    public final void rule__Entity__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1387:1: ( rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1 )
            // InternalText.g:1388:2: rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1
            {
            pushFollow(FOLLOW_11);
            rule__Entity__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__0"


    // $ANTLR start "rule__Entity__Group_5__0__Impl"
    // InternalText.g:1395:1: rule__Entity__Group_5__0__Impl : ( '[' ) ;
    public final void rule__Entity__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1399:1: ( ( '[' ) )
            // InternalText.g:1400:1: ( '[' )
            {
            // InternalText.g:1400:1: ( '[' )
            // InternalText.g:1401:2: '['
            {
             before(grammarAccess.getEntityAccess().getLeftSquareBracketKeyword_5_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getLeftSquareBracketKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__0__Impl"


    // $ANTLR start "rule__Entity__Group_5__1"
    // InternalText.g:1410:1: rule__Entity__Group_5__1 : rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2 ;
    public final void rule__Entity__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1414:1: ( rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2 )
            // InternalText.g:1415:2: rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2
            {
            pushFollow(FOLLOW_11);
            rule__Entity__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Entity__Group_5__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__1"


    // $ANTLR start "rule__Entity__Group_5__1__Impl"
    // InternalText.g:1422:1: rule__Entity__Group_5__1__Impl : ( ( rule__Entity__EntitiesAssignment_5_1 )* ) ;
    public final void rule__Entity__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1426:1: ( ( ( rule__Entity__EntitiesAssignment_5_1 )* ) )
            // InternalText.g:1427:1: ( ( rule__Entity__EntitiesAssignment_5_1 )* )
            {
            // InternalText.g:1427:1: ( ( rule__Entity__EntitiesAssignment_5_1 )* )
            // InternalText.g:1428:2: ( rule__Entity__EntitiesAssignment_5_1 )*
            {
             before(grammarAccess.getEntityAccess().getEntitiesAssignment_5_1()); 
            // InternalText.g:1429:2: ( rule__Entity__EntitiesAssignment_5_1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalText.g:1429:3: rule__Entity__EntitiesAssignment_5_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Entity__EntitiesAssignment_5_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getEntitiesAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__1__Impl"


    // $ANTLR start "rule__Entity__Group_5__2"
    // InternalText.g:1437:1: rule__Entity__Group_5__2 : rule__Entity__Group_5__2__Impl ;
    public final void rule__Entity__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1441:1: ( rule__Entity__Group_5__2__Impl )
            // InternalText.g:1442:2: rule__Entity__Group_5__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Entity__Group_5__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__2"


    // $ANTLR start "rule__Entity__Group_5__2__Impl"
    // InternalText.g:1448:1: rule__Entity__Group_5__2__Impl : ( ']' ) ;
    public final void rule__Entity__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1452:1: ( ( ']' ) )
            // InternalText.g:1453:1: ( ']' )
            {
            // InternalText.g:1453:1: ( ']' )
            // InternalText.g:1454:2: ']'
            {
             before(grammarAccess.getEntityAccess().getRightSquareBracketKeyword_5_2()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getRightSquareBracketKeyword_5_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5__2__Impl"


    // $ANTLR start "rule__Component__Group__0"
    // InternalText.g:1464:1: rule__Component__Group__0 : rule__Component__Group__0__Impl rule__Component__Group__1 ;
    public final void rule__Component__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1468:1: ( rule__Component__Group__0__Impl rule__Component__Group__1 )
            // InternalText.g:1469:2: rule__Component__Group__0__Impl rule__Component__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Component__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__Group__0"


    // $ANTLR start "rule__Component__Group__0__Impl"
    // InternalText.g:1476:1: rule__Component__Group__0__Impl : ( ( rule__Component__NameAssignment_0 ) ) ;
    public final void rule__Component__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1480:1: ( ( ( rule__Component__NameAssignment_0 ) ) )
            // InternalText.g:1481:1: ( ( rule__Component__NameAssignment_0 ) )
            {
            // InternalText.g:1481:1: ( ( rule__Component__NameAssignment_0 ) )
            // InternalText.g:1482:2: ( rule__Component__NameAssignment_0 )
            {
             before(grammarAccess.getComponentAccess().getNameAssignment_0()); 
            // InternalText.g:1483:2: ( rule__Component__NameAssignment_0 )
            // InternalText.g:1483:3: rule__Component__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Component__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__Group__0__Impl"


    // $ANTLR start "rule__Component__Group__1"
    // InternalText.g:1491:1: rule__Component__Group__1 : rule__Component__Group__1__Impl ;
    public final void rule__Component__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1495:1: ( rule__Component__Group__1__Impl )
            // InternalText.g:1496:2: rule__Component__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__Group__1"


    // $ANTLR start "rule__Component__Group__1__Impl"
    // InternalText.g:1502:1: rule__Component__Group__1__Impl : ( ( rule__Component__ValueAssignment_1 )? ) ;
    public final void rule__Component__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1506:1: ( ( ( rule__Component__ValueAssignment_1 )? ) )
            // InternalText.g:1507:1: ( ( rule__Component__ValueAssignment_1 )? )
            {
            // InternalText.g:1507:1: ( ( rule__Component__ValueAssignment_1 )? )
            // InternalText.g:1508:2: ( rule__Component__ValueAssignment_1 )?
            {
             before(grammarAccess.getComponentAccess().getValueAssignment_1()); 
            // InternalText.g:1509:2: ( rule__Component__ValueAssignment_1 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_REAL)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalText.g:1509:3: rule__Component__ValueAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Component__ValueAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getComponentAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__Group__1__Impl"


    // $ANTLR start "rule__System__Group__0"
    // InternalText.g:1518:1: rule__System__Group__0 : rule__System__Group__0__Impl rule__System__Group__1 ;
    public final void rule__System__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1522:1: ( rule__System__Group__0__Impl rule__System__Group__1 )
            // InternalText.g:1523:2: rule__System__Group__0__Impl rule__System__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__System__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__System__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__0"


    // $ANTLR start "rule__System__Group__0__Impl"
    // InternalText.g:1530:1: rule__System__Group__0__Impl : ( ( rule__System__NameAssignment_0 ) ) ;
    public final void rule__System__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1534:1: ( ( ( rule__System__NameAssignment_0 ) ) )
            // InternalText.g:1535:1: ( ( rule__System__NameAssignment_0 ) )
            {
            // InternalText.g:1535:1: ( ( rule__System__NameAssignment_0 ) )
            // InternalText.g:1536:2: ( rule__System__NameAssignment_0 )
            {
             before(grammarAccess.getSystemAccess().getNameAssignment_0()); 
            // InternalText.g:1537:2: ( rule__System__NameAssignment_0 )
            // InternalText.g:1537:3: rule__System__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__System__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSystemAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__0__Impl"


    // $ANTLR start "rule__System__Group__1"
    // InternalText.g:1545:1: rule__System__Group__1 : rule__System__Group__1__Impl rule__System__Group__2 ;
    public final void rule__System__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1549:1: ( rule__System__Group__1__Impl rule__System__Group__2 )
            // InternalText.g:1550:2: rule__System__Group__1__Impl rule__System__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__System__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__System__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__1"


    // $ANTLR start "rule__System__Group__1__Impl"
    // InternalText.g:1557:1: rule__System__Group__1__Impl : ( ':' ) ;
    public final void rule__System__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1561:1: ( ( ':' ) )
            // InternalText.g:1562:1: ( ':' )
            {
            // InternalText.g:1562:1: ( ':' )
            // InternalText.g:1563:2: ':'
            {
             before(grammarAccess.getSystemAccess().getColonKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getSystemAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__1__Impl"


    // $ANTLR start "rule__System__Group__2"
    // InternalText.g:1572:1: rule__System__Group__2 : rule__System__Group__2__Impl ;
    public final void rule__System__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1576:1: ( rule__System__Group__2__Impl )
            // InternalText.g:1577:2: rule__System__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__System__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__2"


    // $ANTLR start "rule__System__Group__2__Impl"
    // InternalText.g:1583:1: rule__System__Group__2__Impl : ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) ) ;
    public final void rule__System__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1587:1: ( ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) ) )
            // InternalText.g:1588:1: ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) )
            {
            // InternalText.g:1588:1: ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) )
            // InternalText.g:1589:2: ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* )
            {
            // InternalText.g:1589:2: ( ( rule__System__CommandsAssignment_2 ) )
            // InternalText.g:1590:3: ( rule__System__CommandsAssignment_2 )
            {
             before(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 
            // InternalText.g:1591:3: ( rule__System__CommandsAssignment_2 )
            // InternalText.g:1591:4: rule__System__CommandsAssignment_2
            {
            pushFollow(FOLLOW_15);
            rule__System__CommandsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 

            }

            // InternalText.g:1594:2: ( ( rule__System__CommandsAssignment_2 )* )
            // InternalText.g:1595:3: ( rule__System__CommandsAssignment_2 )*
            {
             before(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 
            // InternalText.g:1596:3: ( rule__System__CommandsAssignment_2 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID) ) {
                    int LA24_2 = input.LA(2);

                    if ( (LA24_2==14||(LA24_2>=18 && LA24_2<=27)||LA24_2==40||LA24_2==42) ) {
                        alt24=1;
                    }


                }
                else if ( (LA24_0==34||LA24_0==38) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalText.g:1596:4: rule__System__CommandsAssignment_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__System__CommandsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__Group__2__Impl"


    // $ANTLR start "rule__Loop__Group__0"
    // InternalText.g:1606:1: rule__Loop__Group__0 : rule__Loop__Group__0__Impl rule__Loop__Group__1 ;
    public final void rule__Loop__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1610:1: ( rule__Loop__Group__0__Impl rule__Loop__Group__1 )
            // InternalText.g:1611:2: rule__Loop__Group__0__Impl rule__Loop__Group__1
            {
            pushFollow(FOLLOW_16);
            rule__Loop__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__0"


    // $ANTLR start "rule__Loop__Group__0__Impl"
    // InternalText.g:1618:1: rule__Loop__Group__0__Impl : ( 'for' ) ;
    public final void rule__Loop__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1622:1: ( ( 'for' ) )
            // InternalText.g:1623:1: ( 'for' )
            {
            // InternalText.g:1623:1: ( 'for' )
            // InternalText.g:1624:2: 'for'
            {
             before(grammarAccess.getLoopAccess().getForKeyword_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getForKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__0__Impl"


    // $ANTLR start "rule__Loop__Group__1"
    // InternalText.g:1633:1: rule__Loop__Group__1 : rule__Loop__Group__1__Impl rule__Loop__Group__2 ;
    public final void rule__Loop__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1637:1: ( rule__Loop__Group__1__Impl rule__Loop__Group__2 )
            // InternalText.g:1638:2: rule__Loop__Group__1__Impl rule__Loop__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__Loop__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__1"


    // $ANTLR start "rule__Loop__Group__1__Impl"
    // InternalText.g:1645:1: rule__Loop__Group__1__Impl : ( 'all' ) ;
    public final void rule__Loop__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1649:1: ( ( 'all' ) )
            // InternalText.g:1650:1: ( 'all' )
            {
            // InternalText.g:1650:1: ( 'all' )
            // InternalText.g:1651:2: 'all'
            {
             before(grammarAccess.getLoopAccess().getAllKeyword_1()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getAllKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__1__Impl"


    // $ANTLR start "rule__Loop__Group__2"
    // InternalText.g:1660:1: rule__Loop__Group__2 : rule__Loop__Group__2__Impl rule__Loop__Group__3 ;
    public final void rule__Loop__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1664:1: ( rule__Loop__Group__2__Impl rule__Loop__Group__3 )
            // InternalText.g:1665:2: rule__Loop__Group__2__Impl rule__Loop__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__Loop__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__2"


    // $ANTLR start "rule__Loop__Group__2__Impl"
    // InternalText.g:1672:1: rule__Loop__Group__2__Impl : ( 'entity' ) ;
    public final void rule__Loop__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1676:1: ( ( 'entity' ) )
            // InternalText.g:1677:1: ( 'entity' )
            {
            // InternalText.g:1677:1: ( 'entity' )
            // InternalText.g:1678:2: 'entity'
            {
             before(grammarAccess.getLoopAccess().getEntityKeyword_2()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getEntityKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__2__Impl"


    // $ANTLR start "rule__Loop__Group__3"
    // InternalText.g:1687:1: rule__Loop__Group__3 : rule__Loop__Group__3__Impl rule__Loop__Group__4 ;
    public final void rule__Loop__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1691:1: ( rule__Loop__Group__3__Impl rule__Loop__Group__4 )
            // InternalText.g:1692:2: rule__Loop__Group__3__Impl rule__Loop__Group__4
            {
            pushFollow(FOLLOW_18);
            rule__Loop__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__3"


    // $ANTLR start "rule__Loop__Group__3__Impl"
    // InternalText.g:1699:1: rule__Loop__Group__3__Impl : ( ( rule__Loop__EntityAssignment_3 ) ) ;
    public final void rule__Loop__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1703:1: ( ( ( rule__Loop__EntityAssignment_3 ) ) )
            // InternalText.g:1704:1: ( ( rule__Loop__EntityAssignment_3 ) )
            {
            // InternalText.g:1704:1: ( ( rule__Loop__EntityAssignment_3 ) )
            // InternalText.g:1705:2: ( rule__Loop__EntityAssignment_3 )
            {
             before(grammarAccess.getLoopAccess().getEntityAssignment_3()); 
            // InternalText.g:1706:2: ( rule__Loop__EntityAssignment_3 )
            // InternalText.g:1706:3: rule__Loop__EntityAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Loop__EntityAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getEntityAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__3__Impl"


    // $ANTLR start "rule__Loop__Group__4"
    // InternalText.g:1714:1: rule__Loop__Group__4 : rule__Loop__Group__4__Impl rule__Loop__Group__5 ;
    public final void rule__Loop__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1718:1: ( rule__Loop__Group__4__Impl rule__Loop__Group__5 )
            // InternalText.g:1719:2: rule__Loop__Group__4__Impl rule__Loop__Group__5
            {
            pushFollow(FOLLOW_18);
            rule__Loop__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__4"


    // $ANTLR start "rule__Loop__Group__4__Impl"
    // InternalText.g:1726:1: rule__Loop__Group__4__Impl : ( ( rule__Loop__Group_4__0 )? ) ;
    public final void rule__Loop__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1730:1: ( ( ( rule__Loop__Group_4__0 )? ) )
            // InternalText.g:1731:1: ( ( rule__Loop__Group_4__0 )? )
            {
            // InternalText.g:1731:1: ( ( rule__Loop__Group_4__0 )? )
            // InternalText.g:1732:2: ( rule__Loop__Group_4__0 )?
            {
             before(grammarAccess.getLoopAccess().getGroup_4()); 
            // InternalText.g:1733:2: ( rule__Loop__Group_4__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==37) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalText.g:1733:3: rule__Loop__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Loop__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLoopAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__4__Impl"


    // $ANTLR start "rule__Loop__Group__5"
    // InternalText.g:1741:1: rule__Loop__Group__5 : rule__Loop__Group__5__Impl rule__Loop__Group__6 ;
    public final void rule__Loop__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1745:1: ( rule__Loop__Group__5__Impl rule__Loop__Group__6 )
            // InternalText.g:1746:2: rule__Loop__Group__5__Impl rule__Loop__Group__6
            {
            pushFollow(FOLLOW_14);
            rule__Loop__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__5"


    // $ANTLR start "rule__Loop__Group__5__Impl"
    // InternalText.g:1753:1: rule__Loop__Group__5__Impl : ( '{' ) ;
    public final void rule__Loop__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1757:1: ( ( '{' ) )
            // InternalText.g:1758:1: ( '{' )
            {
            // InternalText.g:1758:1: ( '{' )
            // InternalText.g:1759:2: '{'
            {
             before(grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__5__Impl"


    // $ANTLR start "rule__Loop__Group__6"
    // InternalText.g:1768:1: rule__Loop__Group__6 : rule__Loop__Group__6__Impl rule__Loop__Group__7 ;
    public final void rule__Loop__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1772:1: ( rule__Loop__Group__6__Impl rule__Loop__Group__7 )
            // InternalText.g:1773:2: rule__Loop__Group__6__Impl rule__Loop__Group__7
            {
            pushFollow(FOLLOW_19);
            rule__Loop__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__6"


    // $ANTLR start "rule__Loop__Group__6__Impl"
    // InternalText.g:1780:1: rule__Loop__Group__6__Impl : ( ( ( rule__Loop__CommandsAssignment_6 ) ) ( ( rule__Loop__CommandsAssignment_6 )* ) ) ;
    public final void rule__Loop__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1784:1: ( ( ( ( rule__Loop__CommandsAssignment_6 ) ) ( ( rule__Loop__CommandsAssignment_6 )* ) ) )
            // InternalText.g:1785:1: ( ( ( rule__Loop__CommandsAssignment_6 ) ) ( ( rule__Loop__CommandsAssignment_6 )* ) )
            {
            // InternalText.g:1785:1: ( ( ( rule__Loop__CommandsAssignment_6 ) ) ( ( rule__Loop__CommandsAssignment_6 )* ) )
            // InternalText.g:1786:2: ( ( rule__Loop__CommandsAssignment_6 ) ) ( ( rule__Loop__CommandsAssignment_6 )* )
            {
            // InternalText.g:1786:2: ( ( rule__Loop__CommandsAssignment_6 ) )
            // InternalText.g:1787:3: ( rule__Loop__CommandsAssignment_6 )
            {
             before(grammarAccess.getLoopAccess().getCommandsAssignment_6()); 
            // InternalText.g:1788:3: ( rule__Loop__CommandsAssignment_6 )
            // InternalText.g:1788:4: rule__Loop__CommandsAssignment_6
            {
            pushFollow(FOLLOW_15);
            rule__Loop__CommandsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getCommandsAssignment_6()); 

            }

            // InternalText.g:1791:2: ( ( rule__Loop__CommandsAssignment_6 )* )
            // InternalText.g:1792:3: ( rule__Loop__CommandsAssignment_6 )*
            {
             before(grammarAccess.getLoopAccess().getCommandsAssignment_6()); 
            // InternalText.g:1793:3: ( rule__Loop__CommandsAssignment_6 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==34||LA26_0==38) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalText.g:1793:4: rule__Loop__CommandsAssignment_6
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__Loop__CommandsAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getLoopAccess().getCommandsAssignment_6()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__6__Impl"


    // $ANTLR start "rule__Loop__Group__7"
    // InternalText.g:1802:1: rule__Loop__Group__7 : rule__Loop__Group__7__Impl ;
    public final void rule__Loop__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1806:1: ( rule__Loop__Group__7__Impl )
            // InternalText.g:1807:2: rule__Loop__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Loop__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__7"


    // $ANTLR start "rule__Loop__Group__7__Impl"
    // InternalText.g:1813:1: rule__Loop__Group__7__Impl : ( '}' ) ;
    public final void rule__Loop__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1817:1: ( ( '}' ) )
            // InternalText.g:1818:1: ( '}' )
            {
            // InternalText.g:1818:1: ( '}' )
            // InternalText.g:1819:2: '}'
            {
             before(grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_7()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group__7__Impl"


    // $ANTLR start "rule__Loop__Group_4__0"
    // InternalText.g:1829:1: rule__Loop__Group_4__0 : rule__Loop__Group_4__0__Impl rule__Loop__Group_4__1 ;
    public final void rule__Loop__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1833:1: ( rule__Loop__Group_4__0__Impl rule__Loop__Group_4__1 )
            // InternalText.g:1834:2: rule__Loop__Group_4__0__Impl rule__Loop__Group_4__1
            {
            pushFollow(FOLLOW_8);
            rule__Loop__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group_4__0"


    // $ANTLR start "rule__Loop__Group_4__0__Impl"
    // InternalText.g:1841:1: rule__Loop__Group_4__0__Impl : ( 'with' ) ;
    public final void rule__Loop__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1845:1: ( ( 'with' ) )
            // InternalText.g:1846:1: ( 'with' )
            {
            // InternalText.g:1846:1: ( 'with' )
            // InternalText.g:1847:2: 'with'
            {
             before(grammarAccess.getLoopAccess().getWithKeyword_4_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getWithKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group_4__0__Impl"


    // $ANTLR start "rule__Loop__Group_4__1"
    // InternalText.g:1856:1: rule__Loop__Group_4__1 : rule__Loop__Group_4__1__Impl ;
    public final void rule__Loop__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1860:1: ( rule__Loop__Group_4__1__Impl )
            // InternalText.g:1861:2: rule__Loop__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Loop__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group_4__1"


    // $ANTLR start "rule__Loop__Group_4__1__Impl"
    // InternalText.g:1867:1: rule__Loop__Group_4__1__Impl : ( ( ( rule__Loop__ConstraintsAssignment_4_1 ) ) ( ( rule__Loop__ConstraintsAssignment_4_1 )* ) ) ;
    public final void rule__Loop__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1871:1: ( ( ( ( rule__Loop__ConstraintsAssignment_4_1 ) ) ( ( rule__Loop__ConstraintsAssignment_4_1 )* ) ) )
            // InternalText.g:1872:1: ( ( ( rule__Loop__ConstraintsAssignment_4_1 ) ) ( ( rule__Loop__ConstraintsAssignment_4_1 )* ) )
            {
            // InternalText.g:1872:1: ( ( ( rule__Loop__ConstraintsAssignment_4_1 ) ) ( ( rule__Loop__ConstraintsAssignment_4_1 )* ) )
            // InternalText.g:1873:2: ( ( rule__Loop__ConstraintsAssignment_4_1 ) ) ( ( rule__Loop__ConstraintsAssignment_4_1 )* )
            {
            // InternalText.g:1873:2: ( ( rule__Loop__ConstraintsAssignment_4_1 ) )
            // InternalText.g:1874:3: ( rule__Loop__ConstraintsAssignment_4_1 )
            {
             before(grammarAccess.getLoopAccess().getConstraintsAssignment_4_1()); 
            // InternalText.g:1875:3: ( rule__Loop__ConstraintsAssignment_4_1 )
            // InternalText.g:1875:4: rule__Loop__ConstraintsAssignment_4_1
            {
            pushFollow(FOLLOW_3);
            rule__Loop__ConstraintsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getConstraintsAssignment_4_1()); 

            }

            // InternalText.g:1878:2: ( ( rule__Loop__ConstraintsAssignment_4_1 )* )
            // InternalText.g:1879:3: ( rule__Loop__ConstraintsAssignment_4_1 )*
            {
             before(grammarAccess.getLoopAccess().getConstraintsAssignment_4_1()); 
            // InternalText.g:1880:3: ( rule__Loop__ConstraintsAssignment_4_1 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalText.g:1880:4: rule__Loop__ConstraintsAssignment_4_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Loop__ConstraintsAssignment_4_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getLoopAccess().getConstraintsAssignment_4_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__Group_4__1__Impl"


    // $ANTLR start "rule__Branch__Group__0"
    // InternalText.g:1890:1: rule__Branch__Group__0 : rule__Branch__Group__0__Impl rule__Branch__Group__1 ;
    public final void rule__Branch__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1894:1: ( rule__Branch__Group__0__Impl rule__Branch__Group__1 )
            // InternalText.g:1895:2: rule__Branch__Group__0__Impl rule__Branch__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__Branch__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__0"


    // $ANTLR start "rule__Branch__Group__0__Impl"
    // InternalText.g:1902:1: rule__Branch__Group__0__Impl : ( 'if' ) ;
    public final void rule__Branch__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1906:1: ( ( 'if' ) )
            // InternalText.g:1907:1: ( 'if' )
            {
            // InternalText.g:1907:1: ( 'if' )
            // InternalText.g:1908:2: 'if'
            {
             before(grammarAccess.getBranchAccess().getIfKeyword_0()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getIfKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__0__Impl"


    // $ANTLR start "rule__Branch__Group__1"
    // InternalText.g:1917:1: rule__Branch__Group__1 : rule__Branch__Group__1__Impl rule__Branch__Group__2 ;
    public final void rule__Branch__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1921:1: ( rule__Branch__Group__1__Impl rule__Branch__Group__2 )
            // InternalText.g:1922:2: rule__Branch__Group__1__Impl rule__Branch__Group__2
            {
            pushFollow(FOLLOW_21);
            rule__Branch__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__1"


    // $ANTLR start "rule__Branch__Group__1__Impl"
    // InternalText.g:1929:1: rule__Branch__Group__1__Impl : ( ( rule__Branch__IfAssignment_1 ) ) ;
    public final void rule__Branch__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1933:1: ( ( ( rule__Branch__IfAssignment_1 ) ) )
            // InternalText.g:1934:1: ( ( rule__Branch__IfAssignment_1 ) )
            {
            // InternalText.g:1934:1: ( ( rule__Branch__IfAssignment_1 ) )
            // InternalText.g:1935:2: ( rule__Branch__IfAssignment_1 )
            {
             before(grammarAccess.getBranchAccess().getIfAssignment_1()); 
            // InternalText.g:1936:2: ( rule__Branch__IfAssignment_1 )
            // InternalText.g:1936:3: rule__Branch__IfAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Branch__IfAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getIfAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__1__Impl"


    // $ANTLR start "rule__Branch__Group__2"
    // InternalText.g:1944:1: rule__Branch__Group__2 : rule__Branch__Group__2__Impl rule__Branch__Group__3 ;
    public final void rule__Branch__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1948:1: ( rule__Branch__Group__2__Impl rule__Branch__Group__3 )
            // InternalText.g:1949:2: rule__Branch__Group__2__Impl rule__Branch__Group__3
            {
            pushFollow(FOLLOW_21);
            rule__Branch__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__2"


    // $ANTLR start "rule__Branch__Group__2__Impl"
    // InternalText.g:1956:1: rule__Branch__Group__2__Impl : ( ( rule__Branch__Group_2__0 )* ) ;
    public final void rule__Branch__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1960:1: ( ( ( rule__Branch__Group_2__0 )* ) )
            // InternalText.g:1961:1: ( ( rule__Branch__Group_2__0 )* )
            {
            // InternalText.g:1961:1: ( ( rule__Branch__Group_2__0 )* )
            // InternalText.g:1962:2: ( rule__Branch__Group_2__0 )*
            {
             before(grammarAccess.getBranchAccess().getGroup_2()); 
            // InternalText.g:1963:2: ( rule__Branch__Group_2__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==39) ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1==38) ) {
                        alt28=1;
                    }


                }


                switch (alt28) {
            	case 1 :
            	    // InternalText.g:1963:3: rule__Branch__Group_2__0
            	    {
            	    pushFollow(FOLLOW_22);
            	    rule__Branch__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

             after(grammarAccess.getBranchAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__2__Impl"


    // $ANTLR start "rule__Branch__Group__3"
    // InternalText.g:1971:1: rule__Branch__Group__3 : rule__Branch__Group__3__Impl ;
    public final void rule__Branch__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1975:1: ( rule__Branch__Group__3__Impl )
            // InternalText.g:1976:2: rule__Branch__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__3"


    // $ANTLR start "rule__Branch__Group__3__Impl"
    // InternalText.g:1982:1: rule__Branch__Group__3__Impl : ( ( rule__Branch__Group_3__0 )? ) ;
    public final void rule__Branch__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:1986:1: ( ( ( rule__Branch__Group_3__0 )? ) )
            // InternalText.g:1987:1: ( ( rule__Branch__Group_3__0 )? )
            {
            // InternalText.g:1987:1: ( ( rule__Branch__Group_3__0 )? )
            // InternalText.g:1988:2: ( rule__Branch__Group_3__0 )?
            {
             before(grammarAccess.getBranchAccess().getGroup_3()); 
            // InternalText.g:1989:2: ( rule__Branch__Group_3__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==39) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalText.g:1989:3: rule__Branch__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Branch__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getBranchAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group__3__Impl"


    // $ANTLR start "rule__Branch__Group_2__0"
    // InternalText.g:1998:1: rule__Branch__Group_2__0 : rule__Branch__Group_2__0__Impl rule__Branch__Group_2__1 ;
    public final void rule__Branch__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2002:1: ( rule__Branch__Group_2__0__Impl rule__Branch__Group_2__1 )
            // InternalText.g:2003:2: rule__Branch__Group_2__0__Impl rule__Branch__Group_2__1
            {
            pushFollow(FOLLOW_23);
            rule__Branch__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__0"


    // $ANTLR start "rule__Branch__Group_2__0__Impl"
    // InternalText.g:2010:1: rule__Branch__Group_2__0__Impl : ( 'else' ) ;
    public final void rule__Branch__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2014:1: ( ( 'else' ) )
            // InternalText.g:2015:1: ( 'else' )
            {
            // InternalText.g:2015:1: ( 'else' )
            // InternalText.g:2016:2: 'else'
            {
             before(grammarAccess.getBranchAccess().getElseKeyword_2_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getElseKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__0__Impl"


    // $ANTLR start "rule__Branch__Group_2__1"
    // InternalText.g:2025:1: rule__Branch__Group_2__1 : rule__Branch__Group_2__1__Impl rule__Branch__Group_2__2 ;
    public final void rule__Branch__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2029:1: ( rule__Branch__Group_2__1__Impl rule__Branch__Group_2__2 )
            // InternalText.g:2030:2: rule__Branch__Group_2__1__Impl rule__Branch__Group_2__2
            {
            pushFollow(FOLLOW_20);
            rule__Branch__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__1"


    // $ANTLR start "rule__Branch__Group_2__1__Impl"
    // InternalText.g:2037:1: rule__Branch__Group_2__1__Impl : ( 'if' ) ;
    public final void rule__Branch__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2041:1: ( ( 'if' ) )
            // InternalText.g:2042:1: ( 'if' )
            {
            // InternalText.g:2042:1: ( 'if' )
            // InternalText.g:2043:2: 'if'
            {
             before(grammarAccess.getBranchAccess().getIfKeyword_2_1()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getIfKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__1__Impl"


    // $ANTLR start "rule__Branch__Group_2__2"
    // InternalText.g:2052:1: rule__Branch__Group_2__2 : rule__Branch__Group_2__2__Impl ;
    public final void rule__Branch__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2056:1: ( rule__Branch__Group_2__2__Impl )
            // InternalText.g:2057:2: rule__Branch__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__2"


    // $ANTLR start "rule__Branch__Group_2__2__Impl"
    // InternalText.g:2063:1: rule__Branch__Group_2__2__Impl : ( ( rule__Branch__ElseIfsAssignment_2_2 ) ) ;
    public final void rule__Branch__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2067:1: ( ( ( rule__Branch__ElseIfsAssignment_2_2 ) ) )
            // InternalText.g:2068:1: ( ( rule__Branch__ElseIfsAssignment_2_2 ) )
            {
            // InternalText.g:2068:1: ( ( rule__Branch__ElseIfsAssignment_2_2 ) )
            // InternalText.g:2069:2: ( rule__Branch__ElseIfsAssignment_2_2 )
            {
             before(grammarAccess.getBranchAccess().getElseIfsAssignment_2_2()); 
            // InternalText.g:2070:2: ( rule__Branch__ElseIfsAssignment_2_2 )
            // InternalText.g:2070:3: rule__Branch__ElseIfsAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__Branch__ElseIfsAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getElseIfsAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_2__2__Impl"


    // $ANTLR start "rule__Branch__Group_3__0"
    // InternalText.g:2079:1: rule__Branch__Group_3__0 : rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1 ;
    public final void rule__Branch__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2083:1: ( rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1 )
            // InternalText.g:2084:2: rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1
            {
            pushFollow(FOLLOW_24);
            rule__Branch__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__0"


    // $ANTLR start "rule__Branch__Group_3__0__Impl"
    // InternalText.g:2091:1: rule__Branch__Group_3__0__Impl : ( 'else' ) ;
    public final void rule__Branch__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2095:1: ( ( 'else' ) )
            // InternalText.g:2096:1: ( 'else' )
            {
            // InternalText.g:2096:1: ( 'else' )
            // InternalText.g:2097:2: 'else'
            {
             before(grammarAccess.getBranchAccess().getElseKeyword_3_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getElseKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__0__Impl"


    // $ANTLR start "rule__Branch__Group_3__1"
    // InternalText.g:2106:1: rule__Branch__Group_3__1 : rule__Branch__Group_3__1__Impl rule__Branch__Group_3__2 ;
    public final void rule__Branch__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2110:1: ( rule__Branch__Group_3__1__Impl rule__Branch__Group_3__2 )
            // InternalText.g:2111:2: rule__Branch__Group_3__1__Impl rule__Branch__Group_3__2
            {
            pushFollow(FOLLOW_14);
            rule__Branch__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__1"


    // $ANTLR start "rule__Branch__Group_3__1__Impl"
    // InternalText.g:2118:1: rule__Branch__Group_3__1__Impl : ( '{' ) ;
    public final void rule__Branch__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2122:1: ( ( '{' ) )
            // InternalText.g:2123:1: ( '{' )
            {
            // InternalText.g:2123:1: ( '{' )
            // InternalText.g:2124:2: '{'
            {
             before(grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_3_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__1__Impl"


    // $ANTLR start "rule__Branch__Group_3__2"
    // InternalText.g:2133:1: rule__Branch__Group_3__2 : rule__Branch__Group_3__2__Impl rule__Branch__Group_3__3 ;
    public final void rule__Branch__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2137:1: ( rule__Branch__Group_3__2__Impl rule__Branch__Group_3__3 )
            // InternalText.g:2138:2: rule__Branch__Group_3__2__Impl rule__Branch__Group_3__3
            {
            pushFollow(FOLLOW_19);
            rule__Branch__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__2"


    // $ANTLR start "rule__Branch__Group_3__2__Impl"
    // InternalText.g:2145:1: rule__Branch__Group_3__2__Impl : ( ( ( rule__Branch__CommandsAssignment_3_2 ) ) ( ( rule__Branch__CommandsAssignment_3_2 )* ) ) ;
    public final void rule__Branch__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2149:1: ( ( ( ( rule__Branch__CommandsAssignment_3_2 ) ) ( ( rule__Branch__CommandsAssignment_3_2 )* ) ) )
            // InternalText.g:2150:1: ( ( ( rule__Branch__CommandsAssignment_3_2 ) ) ( ( rule__Branch__CommandsAssignment_3_2 )* ) )
            {
            // InternalText.g:2150:1: ( ( ( rule__Branch__CommandsAssignment_3_2 ) ) ( ( rule__Branch__CommandsAssignment_3_2 )* ) )
            // InternalText.g:2151:2: ( ( rule__Branch__CommandsAssignment_3_2 ) ) ( ( rule__Branch__CommandsAssignment_3_2 )* )
            {
            // InternalText.g:2151:2: ( ( rule__Branch__CommandsAssignment_3_2 ) )
            // InternalText.g:2152:3: ( rule__Branch__CommandsAssignment_3_2 )
            {
             before(grammarAccess.getBranchAccess().getCommandsAssignment_3_2()); 
            // InternalText.g:2153:3: ( rule__Branch__CommandsAssignment_3_2 )
            // InternalText.g:2153:4: rule__Branch__CommandsAssignment_3_2
            {
            pushFollow(FOLLOW_15);
            rule__Branch__CommandsAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getCommandsAssignment_3_2()); 

            }

            // InternalText.g:2156:2: ( ( rule__Branch__CommandsAssignment_3_2 )* )
            // InternalText.g:2157:3: ( rule__Branch__CommandsAssignment_3_2 )*
            {
             before(grammarAccess.getBranchAccess().getCommandsAssignment_3_2()); 
            // InternalText.g:2158:3: ( rule__Branch__CommandsAssignment_3_2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==34||LA30_0==38) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalText.g:2158:4: rule__Branch__CommandsAssignment_3_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__Branch__CommandsAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getBranchAccess().getCommandsAssignment_3_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__2__Impl"


    // $ANTLR start "rule__Branch__Group_3__3"
    // InternalText.g:2167:1: rule__Branch__Group_3__3 : rule__Branch__Group_3__3__Impl ;
    public final void rule__Branch__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2171:1: ( rule__Branch__Group_3__3__Impl )
            // InternalText.g:2172:2: rule__Branch__Group_3__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group_3__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__3"


    // $ANTLR start "rule__Branch__Group_3__3__Impl"
    // InternalText.g:2178:1: rule__Branch__Group_3__3__Impl : ( '}' ) ;
    public final void rule__Branch__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2182:1: ( ( '}' ) )
            // InternalText.g:2183:1: ( '}' )
            {
            // InternalText.g:2183:1: ( '}' )
            // InternalText.g:2184:2: '}'
            {
             before(grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_3_3()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__Group_3__3__Impl"


    // $ANTLR start "rule__Condition__Group__0"
    // InternalText.g:2194:1: rule__Condition__Group__0 : rule__Condition__Group__0__Impl rule__Condition__Group__1 ;
    public final void rule__Condition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2198:1: ( rule__Condition__Group__0__Impl rule__Condition__Group__1 )
            // InternalText.g:2199:2: rule__Condition__Group__0__Impl rule__Condition__Group__1
            {
            pushFollow(FOLLOW_24);
            rule__Condition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Condition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__0"


    // $ANTLR start "rule__Condition__Group__0__Impl"
    // InternalText.g:2206:1: rule__Condition__Group__0__Impl : ( ( rule__Condition__ConditionAssignment_0 ) ) ;
    public final void rule__Condition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2210:1: ( ( ( rule__Condition__ConditionAssignment_0 ) ) )
            // InternalText.g:2211:1: ( ( rule__Condition__ConditionAssignment_0 ) )
            {
            // InternalText.g:2211:1: ( ( rule__Condition__ConditionAssignment_0 ) )
            // InternalText.g:2212:2: ( rule__Condition__ConditionAssignment_0 )
            {
             before(grammarAccess.getConditionAccess().getConditionAssignment_0()); 
            // InternalText.g:2213:2: ( rule__Condition__ConditionAssignment_0 )
            // InternalText.g:2213:3: rule__Condition__ConditionAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Condition__ConditionAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getConditionAccess().getConditionAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__0__Impl"


    // $ANTLR start "rule__Condition__Group__1"
    // InternalText.g:2221:1: rule__Condition__Group__1 : rule__Condition__Group__1__Impl rule__Condition__Group__2 ;
    public final void rule__Condition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2225:1: ( rule__Condition__Group__1__Impl rule__Condition__Group__2 )
            // InternalText.g:2226:2: rule__Condition__Group__1__Impl rule__Condition__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__Condition__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Condition__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__1"


    // $ANTLR start "rule__Condition__Group__1__Impl"
    // InternalText.g:2233:1: rule__Condition__Group__1__Impl : ( '{' ) ;
    public final void rule__Condition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2237:1: ( ( '{' ) )
            // InternalText.g:2238:1: ( '{' )
            {
            // InternalText.g:2238:1: ( '{' )
            // InternalText.g:2239:2: '{'
            {
             before(grammarAccess.getConditionAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getConditionAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__1__Impl"


    // $ANTLR start "rule__Condition__Group__2"
    // InternalText.g:2248:1: rule__Condition__Group__2 : rule__Condition__Group__2__Impl rule__Condition__Group__3 ;
    public final void rule__Condition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2252:1: ( rule__Condition__Group__2__Impl rule__Condition__Group__3 )
            // InternalText.g:2253:2: rule__Condition__Group__2__Impl rule__Condition__Group__3
            {
            pushFollow(FOLLOW_25);
            rule__Condition__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Condition__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__2"


    // $ANTLR start "rule__Condition__Group__2__Impl"
    // InternalText.g:2260:1: rule__Condition__Group__2__Impl : ( ( rule__Condition__CommandsAssignment_2 )* ) ;
    public final void rule__Condition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2264:1: ( ( ( rule__Condition__CommandsAssignment_2 )* ) )
            // InternalText.g:2265:1: ( ( rule__Condition__CommandsAssignment_2 )* )
            {
            // InternalText.g:2265:1: ( ( rule__Condition__CommandsAssignment_2 )* )
            // InternalText.g:2266:2: ( rule__Condition__CommandsAssignment_2 )*
            {
             before(grammarAccess.getConditionAccess().getCommandsAssignment_2()); 
            // InternalText.g:2267:2: ( rule__Condition__CommandsAssignment_2 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID||LA31_0==34||LA31_0==38) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalText.g:2267:3: rule__Condition__CommandsAssignment_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__Condition__CommandsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getConditionAccess().getCommandsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__2__Impl"


    // $ANTLR start "rule__Condition__Group__3"
    // InternalText.g:2275:1: rule__Condition__Group__3 : rule__Condition__Group__3__Impl ;
    public final void rule__Condition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2279:1: ( rule__Condition__Group__3__Impl )
            // InternalText.g:2280:2: rule__Condition__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Condition__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__3"


    // $ANTLR start "rule__Condition__Group__3__Impl"
    // InternalText.g:2286:1: rule__Condition__Group__3__Impl : ( '}' ) ;
    public final void rule__Condition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2290:1: ( ( '}' ) )
            // InternalText.g:2291:1: ( '}' )
            {
            // InternalText.g:2291:1: ( '}' )
            // InternalText.g:2292:2: '}'
            {
             before(grammarAccess.getConditionAccess().getRightCurlyBracketKeyword_3()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getConditionAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__Group__3__Impl"


    // $ANTLR start "rule__Assignment__Group__0"
    // InternalText.g:2302:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2306:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalText.g:2307:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__Assignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0"


    // $ANTLR start "rule__Assignment__Group__0__Impl"
    // InternalText.g:2314:1: rule__Assignment__Group__0__Impl : ( ( rule__Assignment__AccessAssignment_0 ) ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2318:1: ( ( ( rule__Assignment__AccessAssignment_0 ) ) )
            // InternalText.g:2319:1: ( ( rule__Assignment__AccessAssignment_0 ) )
            {
            // InternalText.g:2319:1: ( ( rule__Assignment__AccessAssignment_0 ) )
            // InternalText.g:2320:2: ( rule__Assignment__AccessAssignment_0 )
            {
             before(grammarAccess.getAssignmentAccess().getAccessAssignment_0()); 
            // InternalText.g:2321:2: ( rule__Assignment__AccessAssignment_0 )
            // InternalText.g:2321:3: rule__Assignment__AccessAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__AccessAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getAccessAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0__Impl"


    // $ANTLR start "rule__Assignment__Group__1"
    // InternalText.g:2329:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl rule__Assignment__Group__2 ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2333:1: ( rule__Assignment__Group__1__Impl rule__Assignment__Group__2 )
            // InternalText.g:2334:2: rule__Assignment__Group__1__Impl rule__Assignment__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__Assignment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1"


    // $ANTLR start "rule__Assignment__Group__1__Impl"
    // InternalText.g:2341:1: rule__Assignment__Group__1__Impl : ( ( rule__Assignment__TypeAssignment_1 ) ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2345:1: ( ( ( rule__Assignment__TypeAssignment_1 ) ) )
            // InternalText.g:2346:1: ( ( rule__Assignment__TypeAssignment_1 ) )
            {
            // InternalText.g:2346:1: ( ( rule__Assignment__TypeAssignment_1 ) )
            // InternalText.g:2347:2: ( rule__Assignment__TypeAssignment_1 )
            {
             before(grammarAccess.getAssignmentAccess().getTypeAssignment_1()); 
            // InternalText.g:2348:2: ( rule__Assignment__TypeAssignment_1 )
            // InternalText.g:2348:3: rule__Assignment__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1__Impl"


    // $ANTLR start "rule__Assignment__Group__2"
    // InternalText.g:2356:1: rule__Assignment__Group__2 : rule__Assignment__Group__2__Impl ;
    public final void rule__Assignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2360:1: ( rule__Assignment__Group__2__Impl )
            // InternalText.g:2361:2: rule__Assignment__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__2"


    // $ANTLR start "rule__Assignment__Group__2__Impl"
    // InternalText.g:2367:1: rule__Assignment__Group__2__Impl : ( ( rule__Assignment__ExpressionAssignment_2 ) ) ;
    public final void rule__Assignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2371:1: ( ( ( rule__Assignment__ExpressionAssignment_2 ) ) )
            // InternalText.g:2372:1: ( ( rule__Assignment__ExpressionAssignment_2 ) )
            {
            // InternalText.g:2372:1: ( ( rule__Assignment__ExpressionAssignment_2 ) )
            // InternalText.g:2373:2: ( rule__Assignment__ExpressionAssignment_2 )
            {
             before(grammarAccess.getAssignmentAccess().getExpressionAssignment_2()); 
            // InternalText.g:2374:2: ( rule__Assignment__ExpressionAssignment_2 )
            // InternalText.g:2374:3: rule__Assignment__ExpressionAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__ExpressionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getExpressionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__2__Impl"


    // $ANTLR start "rule__Call__Group__0"
    // InternalText.g:2383:1: rule__Call__Group__0 : rule__Call__Group__0__Impl rule__Call__Group__1 ;
    public final void rule__Call__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2387:1: ( rule__Call__Group__0__Impl rule__Call__Group__1 )
            // InternalText.g:2388:2: rule__Call__Group__0__Impl rule__Call__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__Call__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__0"


    // $ANTLR start "rule__Call__Group__0__Impl"
    // InternalText.g:2395:1: rule__Call__Group__0__Impl : ( ( rule__Call__NameAssignment_0 ) ) ;
    public final void rule__Call__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2399:1: ( ( ( rule__Call__NameAssignment_0 ) ) )
            // InternalText.g:2400:1: ( ( rule__Call__NameAssignment_0 ) )
            {
            // InternalText.g:2400:1: ( ( rule__Call__NameAssignment_0 ) )
            // InternalText.g:2401:2: ( rule__Call__NameAssignment_0 )
            {
             before(grammarAccess.getCallAccess().getNameAssignment_0()); 
            // InternalText.g:2402:2: ( rule__Call__NameAssignment_0 )
            // InternalText.g:2402:3: rule__Call__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Call__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__0__Impl"


    // $ANTLR start "rule__Call__Group__1"
    // InternalText.g:2410:1: rule__Call__Group__1 : rule__Call__Group__1__Impl rule__Call__Group__2 ;
    public final void rule__Call__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2414:1: ( rule__Call__Group__1__Impl rule__Call__Group__2 )
            // InternalText.g:2415:2: rule__Call__Group__1__Impl rule__Call__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__Call__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__1"


    // $ANTLR start "rule__Call__Group__1__Impl"
    // InternalText.g:2422:1: rule__Call__Group__1__Impl : ( '(' ) ;
    public final void rule__Call__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2426:1: ( ( '(' ) )
            // InternalText.g:2427:1: ( '(' )
            {
            // InternalText.g:2427:1: ( '(' )
            // InternalText.g:2428:2: '('
            {
             before(grammarAccess.getCallAccess().getLeftParenthesisKeyword_1()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__1__Impl"


    // $ANTLR start "rule__Call__Group__2"
    // InternalText.g:2437:1: rule__Call__Group__2 : rule__Call__Group__2__Impl rule__Call__Group__3 ;
    public final void rule__Call__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2441:1: ( rule__Call__Group__2__Impl rule__Call__Group__3 )
            // InternalText.g:2442:2: rule__Call__Group__2__Impl rule__Call__Group__3
            {
            pushFollow(FOLLOW_28);
            rule__Call__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__2"


    // $ANTLR start "rule__Call__Group__2__Impl"
    // InternalText.g:2449:1: rule__Call__Group__2__Impl : ( ( rule__Call__ParametersAssignment_2 ) ) ;
    public final void rule__Call__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2453:1: ( ( ( rule__Call__ParametersAssignment_2 ) ) )
            // InternalText.g:2454:1: ( ( rule__Call__ParametersAssignment_2 ) )
            {
            // InternalText.g:2454:1: ( ( rule__Call__ParametersAssignment_2 ) )
            // InternalText.g:2455:2: ( rule__Call__ParametersAssignment_2 )
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_2()); 
            // InternalText.g:2456:2: ( rule__Call__ParametersAssignment_2 )
            // InternalText.g:2456:3: rule__Call__ParametersAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Call__ParametersAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getParametersAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__2__Impl"


    // $ANTLR start "rule__Call__Group__3"
    // InternalText.g:2464:1: rule__Call__Group__3 : rule__Call__Group__3__Impl rule__Call__Group__4 ;
    public final void rule__Call__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2468:1: ( rule__Call__Group__3__Impl rule__Call__Group__4 )
            // InternalText.g:2469:2: rule__Call__Group__3__Impl rule__Call__Group__4
            {
            pushFollow(FOLLOW_28);
            rule__Call__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__3"


    // $ANTLR start "rule__Call__Group__3__Impl"
    // InternalText.g:2476:1: rule__Call__Group__3__Impl : ( ( rule__Call__Group_3__0 )* ) ;
    public final void rule__Call__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2480:1: ( ( ( rule__Call__Group_3__0 )* ) )
            // InternalText.g:2481:1: ( ( rule__Call__Group_3__0 )* )
            {
            // InternalText.g:2481:1: ( ( rule__Call__Group_3__0 )* )
            // InternalText.g:2482:2: ( rule__Call__Group_3__0 )*
            {
             before(grammarAccess.getCallAccess().getGroup_3()); 
            // InternalText.g:2483:2: ( rule__Call__Group_3__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==31) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalText.g:2483:3: rule__Call__Group_3__0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__Call__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getCallAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__3__Impl"


    // $ANTLR start "rule__Call__Group__4"
    // InternalText.g:2491:1: rule__Call__Group__4 : rule__Call__Group__4__Impl ;
    public final void rule__Call__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2495:1: ( rule__Call__Group__4__Impl )
            // InternalText.g:2496:2: rule__Call__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Call__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__4"


    // $ANTLR start "rule__Call__Group__4__Impl"
    // InternalText.g:2502:1: rule__Call__Group__4__Impl : ( ')' ) ;
    public final void rule__Call__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2506:1: ( ( ')' ) )
            // InternalText.g:2507:1: ( ')' )
            {
            // InternalText.g:2507:1: ( ')' )
            // InternalText.g:2508:2: ')'
            {
             before(grammarAccess.getCallAccess().getRightParenthesisKeyword_4()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group__4__Impl"


    // $ANTLR start "rule__Call__Group_3__0"
    // InternalText.g:2518:1: rule__Call__Group_3__0 : rule__Call__Group_3__0__Impl rule__Call__Group_3__1 ;
    public final void rule__Call__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2522:1: ( rule__Call__Group_3__0__Impl rule__Call__Group_3__1 )
            // InternalText.g:2523:2: rule__Call__Group_3__0__Impl rule__Call__Group_3__1
            {
            pushFollow(FOLLOW_20);
            rule__Call__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Call__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group_3__0"


    // $ANTLR start "rule__Call__Group_3__0__Impl"
    // InternalText.g:2530:1: rule__Call__Group_3__0__Impl : ( ',' ) ;
    public final void rule__Call__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2534:1: ( ( ',' ) )
            // InternalText.g:2535:1: ( ',' )
            {
            // InternalText.g:2535:1: ( ',' )
            // InternalText.g:2536:2: ','
            {
             before(grammarAccess.getCallAccess().getCommaKeyword_3_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group_3__0__Impl"


    // $ANTLR start "rule__Call__Group_3__1"
    // InternalText.g:2545:1: rule__Call__Group_3__1 : rule__Call__Group_3__1__Impl ;
    public final void rule__Call__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2549:1: ( rule__Call__Group_3__1__Impl )
            // InternalText.g:2550:2: rule__Call__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Call__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group_3__1"


    // $ANTLR start "rule__Call__Group_3__1__Impl"
    // InternalText.g:2556:1: rule__Call__Group_3__1__Impl : ( ( rule__Call__ParametersAssignment_3_1 ) ) ;
    public final void rule__Call__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2560:1: ( ( ( rule__Call__ParametersAssignment_3_1 ) ) )
            // InternalText.g:2561:1: ( ( rule__Call__ParametersAssignment_3_1 ) )
            {
            // InternalText.g:2561:1: ( ( rule__Call__ParametersAssignment_3_1 ) )
            // InternalText.g:2562:2: ( rule__Call__ParametersAssignment_3_1 )
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_3_1()); 
            // InternalText.g:2563:2: ( rule__Call__ParametersAssignment_3_1 )
            // InternalText.g:2563:3: rule__Call__ParametersAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Call__ParametersAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getCallAccess().getParametersAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__Group_3__1__Impl"


    // $ANTLR start "rule__Access__Group__0"
    // InternalText.g:2572:1: rule__Access__Group__0 : rule__Access__Group__0__Impl rule__Access__Group__1 ;
    public final void rule__Access__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2576:1: ( rule__Access__Group__0__Impl rule__Access__Group__1 )
            // InternalText.g:2577:2: rule__Access__Group__0__Impl rule__Access__Group__1
            {
            pushFollow(FOLLOW_29);
            rule__Access__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Access__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group__0"


    // $ANTLR start "rule__Access__Group__0__Impl"
    // InternalText.g:2584:1: rule__Access__Group__0__Impl : ( ( rule__Access__NamesAssignment_0 ) ) ;
    public final void rule__Access__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2588:1: ( ( ( rule__Access__NamesAssignment_0 ) ) )
            // InternalText.g:2589:1: ( ( rule__Access__NamesAssignment_0 ) )
            {
            // InternalText.g:2589:1: ( ( rule__Access__NamesAssignment_0 ) )
            // InternalText.g:2590:2: ( rule__Access__NamesAssignment_0 )
            {
             before(grammarAccess.getAccessAccess().getNamesAssignment_0()); 
            // InternalText.g:2591:2: ( rule__Access__NamesAssignment_0 )
            // InternalText.g:2591:3: rule__Access__NamesAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Access__NamesAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getAccessAccess().getNamesAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group__0__Impl"


    // $ANTLR start "rule__Access__Group__1"
    // InternalText.g:2599:1: rule__Access__Group__1 : rule__Access__Group__1__Impl ;
    public final void rule__Access__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2603:1: ( rule__Access__Group__1__Impl )
            // InternalText.g:2604:2: rule__Access__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Access__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group__1"


    // $ANTLR start "rule__Access__Group__1__Impl"
    // InternalText.g:2610:1: rule__Access__Group__1__Impl : ( ( rule__Access__Group_1__0 )* ) ;
    public final void rule__Access__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2614:1: ( ( ( rule__Access__Group_1__0 )* ) )
            // InternalText.g:2615:1: ( ( rule__Access__Group_1__0 )* )
            {
            // InternalText.g:2615:1: ( ( rule__Access__Group_1__0 )* )
            // InternalText.g:2616:2: ( rule__Access__Group_1__0 )*
            {
             before(grammarAccess.getAccessAccess().getGroup_1()); 
            // InternalText.g:2617:2: ( rule__Access__Group_1__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==42) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalText.g:2617:3: rule__Access__Group_1__0
            	    {
            	    pushFollow(FOLLOW_30);
            	    rule__Access__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getAccessAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group__1__Impl"


    // $ANTLR start "rule__Access__Group_1__0"
    // InternalText.g:2626:1: rule__Access__Group_1__0 : rule__Access__Group_1__0__Impl rule__Access__Group_1__1 ;
    public final void rule__Access__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2630:1: ( rule__Access__Group_1__0__Impl rule__Access__Group_1__1 )
            // InternalText.g:2631:2: rule__Access__Group_1__0__Impl rule__Access__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Access__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Access__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group_1__0"


    // $ANTLR start "rule__Access__Group_1__0__Impl"
    // InternalText.g:2638:1: rule__Access__Group_1__0__Impl : ( '.' ) ;
    public final void rule__Access__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2642:1: ( ( '.' ) )
            // InternalText.g:2643:1: ( '.' )
            {
            // InternalText.g:2643:1: ( '.' )
            // InternalText.g:2644:2: '.'
            {
             before(grammarAccess.getAccessAccess().getFullStopKeyword_1_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getAccessAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group_1__0__Impl"


    // $ANTLR start "rule__Access__Group_1__1"
    // InternalText.g:2653:1: rule__Access__Group_1__1 : rule__Access__Group_1__1__Impl ;
    public final void rule__Access__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2657:1: ( rule__Access__Group_1__1__Impl )
            // InternalText.g:2658:2: rule__Access__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Access__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group_1__1"


    // $ANTLR start "rule__Access__Group_1__1__Impl"
    // InternalText.g:2664:1: rule__Access__Group_1__1__Impl : ( ( rule__Access__NamesAssignment_1_1 ) ) ;
    public final void rule__Access__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2668:1: ( ( ( rule__Access__NamesAssignment_1_1 ) ) )
            // InternalText.g:2669:1: ( ( rule__Access__NamesAssignment_1_1 ) )
            {
            // InternalText.g:2669:1: ( ( rule__Access__NamesAssignment_1_1 ) )
            // InternalText.g:2670:2: ( rule__Access__NamesAssignment_1_1 )
            {
             before(grammarAccess.getAccessAccess().getNamesAssignment_1_1()); 
            // InternalText.g:2671:2: ( rule__Access__NamesAssignment_1_1 )
            // InternalText.g:2671:3: rule__Access__NamesAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Access__NamesAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAccessAccess().getNamesAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__Group_1__1__Impl"


    // $ANTLR start "rule__Expression__Group__0"
    // InternalText.g:2680:1: rule__Expression__Group__0 : rule__Expression__Group__0__Impl rule__Expression__Group__1 ;
    public final void rule__Expression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2684:1: ( rule__Expression__Group__0__Impl rule__Expression__Group__1 )
            // InternalText.g:2685:2: rule__Expression__Group__0__Impl rule__Expression__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__Expression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Expression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__0"


    // $ANTLR start "rule__Expression__Group__0__Impl"
    // InternalText.g:2692:1: rule__Expression__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Expression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2696:1: ( ( ruleAnd ) )
            // InternalText.g:2697:1: ( ruleAnd )
            {
            // InternalText.g:2697:1: ( ruleAnd )
            // InternalText.g:2698:2: ruleAnd
            {
             before(grammarAccess.getExpressionAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getAndParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__0__Impl"


    // $ANTLR start "rule__Expression__Group__1"
    // InternalText.g:2707:1: rule__Expression__Group__1 : rule__Expression__Group__1__Impl ;
    public final void rule__Expression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2711:1: ( rule__Expression__Group__1__Impl )
            // InternalText.g:2712:2: rule__Expression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Expression__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__1"


    // $ANTLR start "rule__Expression__Group__1__Impl"
    // InternalText.g:2718:1: rule__Expression__Group__1__Impl : ( ( rule__Expression__Group_1__0 )* ) ;
    public final void rule__Expression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2722:1: ( ( ( rule__Expression__Group_1__0 )* ) )
            // InternalText.g:2723:1: ( ( rule__Expression__Group_1__0 )* )
            {
            // InternalText.g:2723:1: ( ( rule__Expression__Group_1__0 )* )
            // InternalText.g:2724:2: ( rule__Expression__Group_1__0 )*
            {
             before(grammarAccess.getExpressionAccess().getGroup_1()); 
            // InternalText.g:2725:2: ( rule__Expression__Group_1__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==43) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalText.g:2725:3: rule__Expression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_32);
            	    rule__Expression__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getExpressionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group__1__Impl"


    // $ANTLR start "rule__Expression__Group_1__0"
    // InternalText.g:2734:1: rule__Expression__Group_1__0 : rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1 ;
    public final void rule__Expression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2738:1: ( rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1 )
            // InternalText.g:2739:2: rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1
            {
            pushFollow(FOLLOW_31);
            rule__Expression__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Expression__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__0"


    // $ANTLR start "rule__Expression__Group_1__0__Impl"
    // InternalText.g:2746:1: rule__Expression__Group_1__0__Impl : ( () ) ;
    public final void rule__Expression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2750:1: ( ( () ) )
            // InternalText.g:2751:1: ( () )
            {
            // InternalText.g:2751:1: ( () )
            // InternalText.g:2752:2: ()
            {
             before(grammarAccess.getExpressionAccess().getOrLeftAction_1_0()); 
            // InternalText.g:2753:2: ()
            // InternalText.g:2753:3: 
            {
            }

             after(grammarAccess.getExpressionAccess().getOrLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__0__Impl"


    // $ANTLR start "rule__Expression__Group_1__1"
    // InternalText.g:2761:1: rule__Expression__Group_1__1 : rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2 ;
    public final void rule__Expression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2765:1: ( rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2 )
            // InternalText.g:2766:2: rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Expression__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Expression__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__1"


    // $ANTLR start "rule__Expression__Group_1__1__Impl"
    // InternalText.g:2773:1: rule__Expression__Group_1__1__Impl : ( '||' ) ;
    public final void rule__Expression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2777:1: ( ( '||' ) )
            // InternalText.g:2778:1: ( '||' )
            {
            // InternalText.g:2778:1: ( '||' )
            // InternalText.g:2779:2: '||'
            {
             before(grammarAccess.getExpressionAccess().getVerticalLineVerticalLineKeyword_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getExpressionAccess().getVerticalLineVerticalLineKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__1__Impl"


    // $ANTLR start "rule__Expression__Group_1__2"
    // InternalText.g:2788:1: rule__Expression__Group_1__2 : rule__Expression__Group_1__2__Impl ;
    public final void rule__Expression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2792:1: ( rule__Expression__Group_1__2__Impl )
            // InternalText.g:2793:2: rule__Expression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Expression__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__2"


    // $ANTLR start "rule__Expression__Group_1__2__Impl"
    // InternalText.g:2799:1: rule__Expression__Group_1__2__Impl : ( ( rule__Expression__RightAssignment_1_2 ) ) ;
    public final void rule__Expression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2803:1: ( ( ( rule__Expression__RightAssignment_1_2 ) ) )
            // InternalText.g:2804:1: ( ( rule__Expression__RightAssignment_1_2 ) )
            {
            // InternalText.g:2804:1: ( ( rule__Expression__RightAssignment_1_2 ) )
            // InternalText.g:2805:2: ( rule__Expression__RightAssignment_1_2 )
            {
             before(grammarAccess.getExpressionAccess().getRightAssignment_1_2()); 
            // InternalText.g:2806:2: ( rule__Expression__RightAssignment_1_2 )
            // InternalText.g:2806:3: rule__Expression__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Expression__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getExpressionAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__Group_1__2__Impl"


    // $ANTLR start "rule__And__Group__0"
    // InternalText.g:2815:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2819:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // InternalText.g:2820:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0"


    // $ANTLR start "rule__And__Group__0__Impl"
    // InternalText.g:2827:1: rule__And__Group__0__Impl : ( rulePrimaryB ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2831:1: ( ( rulePrimaryB ) )
            // InternalText.g:2832:1: ( rulePrimaryB )
            {
            // InternalText.g:2832:1: ( rulePrimaryB )
            // InternalText.g:2833:2: rulePrimaryB
            {
             before(grammarAccess.getAndAccess().getPrimaryBParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePrimaryB();

            state._fsp--;

             after(grammarAccess.getAndAccess().getPrimaryBParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0__Impl"


    // $ANTLR start "rule__And__Group__1"
    // InternalText.g:2842:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2846:1: ( rule__And__Group__1__Impl )
            // InternalText.g:2847:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__And__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1"


    // $ANTLR start "rule__And__Group__1__Impl"
    // InternalText.g:2853:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2857:1: ( ( ( rule__And__Group_1__0 )* ) )
            // InternalText.g:2858:1: ( ( rule__And__Group_1__0 )* )
            {
            // InternalText.g:2858:1: ( ( rule__And__Group_1__0 )* )
            // InternalText.g:2859:2: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // InternalText.g:2860:2: ( rule__And__Group_1__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==44) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalText.g:2860:3: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_34);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getAndAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1__Impl"


    // $ANTLR start "rule__And__Group_1__0"
    // InternalText.g:2869:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2873:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // InternalText.g:2874:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_33);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0"


    // $ANTLR start "rule__And__Group_1__0__Impl"
    // InternalText.g:2881:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2885:1: ( ( () ) )
            // InternalText.g:2886:1: ( () )
            {
            // InternalText.g:2886:1: ( () )
            // InternalText.g:2887:2: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // InternalText.g:2888:2: ()
            // InternalText.g:2888:3: 
            {
            }

             after(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0__Impl"


    // $ANTLR start "rule__And__Group_1__1"
    // InternalText.g:2896:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2900:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // InternalText.g:2901:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1"


    // $ANTLR start "rule__And__Group_1__1__Impl"
    // InternalText.g:2908:1: rule__And__Group_1__1__Impl : ( '&&' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2912:1: ( ( '&&' ) )
            // InternalText.g:2913:1: ( '&&' )
            {
            // InternalText.g:2913:1: ( '&&' )
            // InternalText.g:2914:2: '&&'
            {
             before(grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1__Impl"


    // $ANTLR start "rule__And__Group_1__2"
    // InternalText.g:2923:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2927:1: ( rule__And__Group_1__2__Impl )
            // InternalText.g:2928:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__And__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2"


    // $ANTLR start "rule__And__Group_1__2__Impl"
    // InternalText.g:2934:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2938:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // InternalText.g:2939:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // InternalText.g:2939:1: ( ( rule__And__RightAssignment_1_2 ) )
            // InternalText.g:2940:2: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // InternalText.g:2941:2: ( rule__And__RightAssignment_1_2 )
            // InternalText.g:2941:3: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__And__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2__Impl"


    // $ANTLR start "rule__PrimaryB__Group_1__0"
    // InternalText.g:2950:1: rule__PrimaryB__Group_1__0 : rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1 ;
    public final void rule__PrimaryB__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2954:1: ( rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1 )
            // InternalText.g:2955:2: rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1
            {
            pushFollow(FOLLOW_20);
            rule__PrimaryB__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PrimaryB__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__0"


    // $ANTLR start "rule__PrimaryB__Group_1__0__Impl"
    // InternalText.g:2962:1: rule__PrimaryB__Group_1__0__Impl : ( () ) ;
    public final void rule__PrimaryB__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2966:1: ( ( () ) )
            // InternalText.g:2967:1: ( () )
            {
            // InternalText.g:2967:1: ( () )
            // InternalText.g:2968:2: ()
            {
             before(grammarAccess.getPrimaryBAccess().getNotAction_1_0()); 
            // InternalText.g:2969:2: ()
            // InternalText.g:2969:3: 
            {
            }

             after(grammarAccess.getPrimaryBAccess().getNotAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__0__Impl"


    // $ANTLR start "rule__PrimaryB__Group_1__1"
    // InternalText.g:2977:1: rule__PrimaryB__Group_1__1 : rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2 ;
    public final void rule__PrimaryB__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2981:1: ( rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2 )
            // InternalText.g:2982:2: rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2
            {
            pushFollow(FOLLOW_35);
            rule__PrimaryB__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PrimaryB__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__1"


    // $ANTLR start "rule__PrimaryB__Group_1__1__Impl"
    // InternalText.g:2989:1: rule__PrimaryB__Group_1__1__Impl : ( '!' ) ;
    public final void rule__PrimaryB__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:2993:1: ( ( '!' ) )
            // InternalText.g:2994:1: ( '!' )
            {
            // InternalText.g:2994:1: ( '!' )
            // InternalText.g:2995:2: '!'
            {
             before(grammarAccess.getPrimaryBAccess().getExclamationMarkKeyword_1_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getPrimaryBAccess().getExclamationMarkKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__1__Impl"


    // $ANTLR start "rule__PrimaryB__Group_1__2"
    // InternalText.g:3004:1: rule__PrimaryB__Group_1__2 : rule__PrimaryB__Group_1__2__Impl ;
    public final void rule__PrimaryB__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3008:1: ( rule__PrimaryB__Group_1__2__Impl )
            // InternalText.g:3009:2: rule__PrimaryB__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryB__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__2"


    // $ANTLR start "rule__PrimaryB__Group_1__2__Impl"
    // InternalText.g:3015:1: rule__PrimaryB__Group_1__2__Impl : ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) ) ;
    public final void rule__PrimaryB__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3019:1: ( ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) ) )
            // InternalText.g:3020:1: ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) )
            {
            // InternalText.g:3020:1: ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) )
            // InternalText.g:3021:2: ( rule__PrimaryB__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryBAccess().getExpressionAssignment_1_2()); 
            // InternalText.g:3022:2: ( rule__PrimaryB__ExpressionAssignment_1_2 )
            // InternalText.g:3022:3: rule__PrimaryB__ExpressionAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryB__ExpressionAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryBAccess().getExpressionAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__Group_1__2__Impl"


    // $ANTLR start "rule__AtomicB__Group__0"
    // InternalText.g:3031:1: rule__AtomicB__Group__0 : rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1 ;
    public final void rule__AtomicB__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3035:1: ( rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1 )
            // InternalText.g:3036:2: rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1
            {
            pushFollow(FOLLOW_36);
            rule__AtomicB__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AtomicB__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group__0"


    // $ANTLR start "rule__AtomicB__Group__0__Impl"
    // InternalText.g:3043:1: rule__AtomicB__Group__0__Impl : ( ruleAddition ) ;
    public final void rule__AtomicB__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3047:1: ( ( ruleAddition ) )
            // InternalText.g:3048:1: ( ruleAddition )
            {
            // InternalText.g:3048:1: ( ruleAddition )
            // InternalText.g:3049:2: ruleAddition
            {
             before(grammarAccess.getAtomicBAccess().getAdditionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAtomicBAccess().getAdditionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group__0__Impl"


    // $ANTLR start "rule__AtomicB__Group__1"
    // InternalText.g:3058:1: rule__AtomicB__Group__1 : rule__AtomicB__Group__1__Impl ;
    public final void rule__AtomicB__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3062:1: ( rule__AtomicB__Group__1__Impl )
            // InternalText.g:3063:2: rule__AtomicB__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AtomicB__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group__1"


    // $ANTLR start "rule__AtomicB__Group__1__Impl"
    // InternalText.g:3069:1: rule__AtomicB__Group__1__Impl : ( ( rule__AtomicB__Group_1__0 )? ) ;
    public final void rule__AtomicB__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3073:1: ( ( ( rule__AtomicB__Group_1__0 )? ) )
            // InternalText.g:3074:1: ( ( rule__AtomicB__Group_1__0 )? )
            {
            // InternalText.g:3074:1: ( ( rule__AtomicB__Group_1__0 )? )
            // InternalText.g:3075:2: ( rule__AtomicB__Group_1__0 )?
            {
             before(grammarAccess.getAtomicBAccess().getGroup_1()); 
            // InternalText.g:3076:2: ( rule__AtomicB__Group_1__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=12 && LA36_0<=17)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalText.g:3076:3: rule__AtomicB__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__AtomicB__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAtomicBAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group__1__Impl"


    // $ANTLR start "rule__AtomicB__Group_1__0"
    // InternalText.g:3085:1: rule__AtomicB__Group_1__0 : rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1 ;
    public final void rule__AtomicB__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3089:1: ( rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1 )
            // InternalText.g:3090:2: rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1
            {
            pushFollow(FOLLOW_36);
            rule__AtomicB__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AtomicB__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__0"


    // $ANTLR start "rule__AtomicB__Group_1__0__Impl"
    // InternalText.g:3097:1: rule__AtomicB__Group_1__0__Impl : ( () ) ;
    public final void rule__AtomicB__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3101:1: ( ( () ) )
            // InternalText.g:3102:1: ( () )
            {
            // InternalText.g:3102:1: ( () )
            // InternalText.g:3103:2: ()
            {
             before(grammarAccess.getAtomicBAccess().getComparisonLeftAction_1_0()); 
            // InternalText.g:3104:2: ()
            // InternalText.g:3104:3: 
            {
            }

             after(grammarAccess.getAtomicBAccess().getComparisonLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__0__Impl"


    // $ANTLR start "rule__AtomicB__Group_1__1"
    // InternalText.g:3112:1: rule__AtomicB__Group_1__1 : rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2 ;
    public final void rule__AtomicB__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3116:1: ( rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2 )
            // InternalText.g:3117:2: rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2
            {
            pushFollow(FOLLOW_35);
            rule__AtomicB__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AtomicB__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__1"


    // $ANTLR start "rule__AtomicB__Group_1__1__Impl"
    // InternalText.g:3124:1: rule__AtomicB__Group_1__1__Impl : ( ( rule__AtomicB__TypeAssignment_1_1 ) ) ;
    public final void rule__AtomicB__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3128:1: ( ( ( rule__AtomicB__TypeAssignment_1_1 ) ) )
            // InternalText.g:3129:1: ( ( rule__AtomicB__TypeAssignment_1_1 ) )
            {
            // InternalText.g:3129:1: ( ( rule__AtomicB__TypeAssignment_1_1 ) )
            // InternalText.g:3130:2: ( rule__AtomicB__TypeAssignment_1_1 )
            {
             before(grammarAccess.getAtomicBAccess().getTypeAssignment_1_1()); 
            // InternalText.g:3131:2: ( rule__AtomicB__TypeAssignment_1_1 )
            // InternalText.g:3131:3: rule__AtomicB__TypeAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__AtomicB__TypeAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAtomicBAccess().getTypeAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__1__Impl"


    // $ANTLR start "rule__AtomicB__Group_1__2"
    // InternalText.g:3139:1: rule__AtomicB__Group_1__2 : rule__AtomicB__Group_1__2__Impl ;
    public final void rule__AtomicB__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3143:1: ( rule__AtomicB__Group_1__2__Impl )
            // InternalText.g:3144:2: rule__AtomicB__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AtomicB__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__2"


    // $ANTLR start "rule__AtomicB__Group_1__2__Impl"
    // InternalText.g:3150:1: rule__AtomicB__Group_1__2__Impl : ( ( rule__AtomicB__RightAssignment_1_2 ) ) ;
    public final void rule__AtomicB__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3154:1: ( ( ( rule__AtomicB__RightAssignment_1_2 ) ) )
            // InternalText.g:3155:1: ( ( rule__AtomicB__RightAssignment_1_2 ) )
            {
            // InternalText.g:3155:1: ( ( rule__AtomicB__RightAssignment_1_2 ) )
            // InternalText.g:3156:2: ( rule__AtomicB__RightAssignment_1_2 )
            {
             before(grammarAccess.getAtomicBAccess().getRightAssignment_1_2()); 
            // InternalText.g:3157:2: ( rule__AtomicB__RightAssignment_1_2 )
            // InternalText.g:3157:3: rule__AtomicB__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__AtomicB__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAtomicBAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__Group_1__2__Impl"


    // $ANTLR start "rule__Addition__Group__0"
    // InternalText.g:3166:1: rule__Addition__Group__0 : rule__Addition__Group__0__Impl rule__Addition__Group__1 ;
    public final void rule__Addition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3170:1: ( rule__Addition__Group__0__Impl rule__Addition__Group__1 )
            // InternalText.g:3171:2: rule__Addition__Group__0__Impl rule__Addition__Group__1
            {
            pushFollow(FOLLOW_37);
            rule__Addition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__0"


    // $ANTLR start "rule__Addition__Group__0__Impl"
    // InternalText.g:3178:1: rule__Addition__Group__0__Impl : ( ruleMultiplication ) ;
    public final void rule__Addition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3182:1: ( ( ruleMultiplication ) )
            // InternalText.g:3183:1: ( ruleMultiplication )
            {
            // InternalText.g:3183:1: ( ruleMultiplication )
            // InternalText.g:3184:2: ruleMultiplication
            {
             before(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__0__Impl"


    // $ANTLR start "rule__Addition__Group__1"
    // InternalText.g:3193:1: rule__Addition__Group__1 : rule__Addition__Group__1__Impl ;
    public final void rule__Addition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3197:1: ( rule__Addition__Group__1__Impl )
            // InternalText.g:3198:2: rule__Addition__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__1"


    // $ANTLR start "rule__Addition__Group__1__Impl"
    // InternalText.g:3204:1: rule__Addition__Group__1__Impl : ( ( rule__Addition__Group_1__0 )* ) ;
    public final void rule__Addition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3208:1: ( ( ( rule__Addition__Group_1__0 )* ) )
            // InternalText.g:3209:1: ( ( rule__Addition__Group_1__0 )* )
            {
            // InternalText.g:3209:1: ( ( rule__Addition__Group_1__0 )* )
            // InternalText.g:3210:2: ( rule__Addition__Group_1__0 )*
            {
             before(grammarAccess.getAdditionAccess().getGroup_1()); 
            // InternalText.g:3211:2: ( rule__Addition__Group_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( ((LA37_0>=46 && LA37_0<=47)) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalText.g:3211:3: rule__Addition__Group_1__0
            	    {
            	    pushFollow(FOLLOW_38);
            	    rule__Addition__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

             after(grammarAccess.getAdditionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__1__Impl"


    // $ANTLR start "rule__Addition__Group_1__0"
    // InternalText.g:3220:1: rule__Addition__Group_1__0 : rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 ;
    public final void rule__Addition__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3224:1: ( rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 )
            // InternalText.g:3225:2: rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1
            {
            pushFollow(FOLLOW_35);
            rule__Addition__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__0"


    // $ANTLR start "rule__Addition__Group_1__0__Impl"
    // InternalText.g:3232:1: rule__Addition__Group_1__0__Impl : ( ( rule__Addition__Alternatives_1_0 ) ) ;
    public final void rule__Addition__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3236:1: ( ( ( rule__Addition__Alternatives_1_0 ) ) )
            // InternalText.g:3237:1: ( ( rule__Addition__Alternatives_1_0 ) )
            {
            // InternalText.g:3237:1: ( ( rule__Addition__Alternatives_1_0 ) )
            // InternalText.g:3238:2: ( rule__Addition__Alternatives_1_0 )
            {
             before(grammarAccess.getAdditionAccess().getAlternatives_1_0()); 
            // InternalText.g:3239:2: ( rule__Addition__Alternatives_1_0 )
            // InternalText.g:3239:3: rule__Addition__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Alternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__0__Impl"


    // $ANTLR start "rule__Addition__Group_1__1"
    // InternalText.g:3247:1: rule__Addition__Group_1__1 : rule__Addition__Group_1__1__Impl ;
    public final void rule__Addition__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3251:1: ( rule__Addition__Group_1__1__Impl )
            // InternalText.g:3252:2: rule__Addition__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__1"


    // $ANTLR start "rule__Addition__Group_1__1__Impl"
    // InternalText.g:3258:1: rule__Addition__Group_1__1__Impl : ( ( rule__Addition__RightAssignment_1_1 ) ) ;
    public final void rule__Addition__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3262:1: ( ( ( rule__Addition__RightAssignment_1_1 ) ) )
            // InternalText.g:3263:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            {
            // InternalText.g:3263:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            // InternalText.g:3264:2: ( rule__Addition__RightAssignment_1_1 )
            {
             before(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); 
            // InternalText.g:3265:2: ( rule__Addition__RightAssignment_1_1 )
            // InternalText.g:3265:3: rule__Addition__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Addition__RightAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__1__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_0__0"
    // InternalText.g:3274:1: rule__Addition__Group_1_0_0__0 : rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 ;
    public final void rule__Addition__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3278:1: ( rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 )
            // InternalText.g:3279:2: rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1
            {
            pushFollow(FOLLOW_39);
            rule__Addition__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_0__0"


    // $ANTLR start "rule__Addition__Group_1_0_0__0__Impl"
    // InternalText.g:3286:1: rule__Addition__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3290:1: ( ( () ) )
            // InternalText.g:3291:1: ( () )
            {
            // InternalText.g:3291:1: ( () )
            // InternalText.g:3292:2: ()
            {
             before(grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0()); 
            // InternalText.g:3293:2: ()
            // InternalText.g:3293:3: 
            {
            }

             after(grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_0__1"
    // InternalText.g:3301:1: rule__Addition__Group_1_0_0__1 : rule__Addition__Group_1_0_0__1__Impl ;
    public final void rule__Addition__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3305:1: ( rule__Addition__Group_1_0_0__1__Impl )
            // InternalText.g:3306:2: rule__Addition__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_0__1"


    // $ANTLR start "rule__Addition__Group_1_0_0__1__Impl"
    // InternalText.g:3312:1: rule__Addition__Group_1_0_0__1__Impl : ( '+' ) ;
    public final void rule__Addition__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3316:1: ( ( '+' ) )
            // InternalText.g:3317:1: ( '+' )
            {
            // InternalText.g:3317:1: ( '+' )
            // InternalText.g:3318:2: '+'
            {
             before(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_1__0"
    // InternalText.g:3328:1: rule__Addition__Group_1_0_1__0 : rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 ;
    public final void rule__Addition__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3332:1: ( rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 )
            // InternalText.g:3333:2: rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1
            {
            pushFollow(FOLLOW_37);
            rule__Addition__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_1__0"


    // $ANTLR start "rule__Addition__Group_1_0_1__0__Impl"
    // InternalText.g:3340:1: rule__Addition__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3344:1: ( ( () ) )
            // InternalText.g:3345:1: ( () )
            {
            // InternalText.g:3345:1: ( () )
            // InternalText.g:3346:2: ()
            {
             before(grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0()); 
            // InternalText.g:3347:2: ()
            // InternalText.g:3347:3: 
            {
            }

             after(grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_1__1"
    // InternalText.g:3355:1: rule__Addition__Group_1_0_1__1 : rule__Addition__Group_1_0_1__1__Impl ;
    public final void rule__Addition__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3359:1: ( rule__Addition__Group_1_0_1__1__Impl )
            // InternalText.g:3360:2: rule__Addition__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_1__1"


    // $ANTLR start "rule__Addition__Group_1_0_1__1__Impl"
    // InternalText.g:3366:1: rule__Addition__Group_1_0_1__1__Impl : ( '-' ) ;
    public final void rule__Addition__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3370:1: ( ( '-' ) )
            // InternalText.g:3371:1: ( '-' )
            {
            // InternalText.g:3371:1: ( '-' )
            // InternalText.g:3372:2: '-'
            {
             before(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group__0"
    // InternalText.g:3382:1: rule__Multiplication__Group__0 : rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 ;
    public final void rule__Multiplication__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3386:1: ( rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 )
            // InternalText.g:3387:2: rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1
            {
            pushFollow(FOLLOW_40);
            rule__Multiplication__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__0"


    // $ANTLR start "rule__Multiplication__Group__0__Impl"
    // InternalText.g:3394:1: rule__Multiplication__Group__0__Impl : ( ruleBitwise ) ;
    public final void rule__Multiplication__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3398:1: ( ( ruleBitwise ) )
            // InternalText.g:3399:1: ( ruleBitwise )
            {
            // InternalText.g:3399:1: ( ruleBitwise )
            // InternalText.g:3400:2: ruleBitwise
            {
             before(grammarAccess.getMultiplicationAccess().getBitwiseParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleBitwise();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getBitwiseParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__0__Impl"


    // $ANTLR start "rule__Multiplication__Group__1"
    // InternalText.g:3409:1: rule__Multiplication__Group__1 : rule__Multiplication__Group__1__Impl ;
    public final void rule__Multiplication__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3413:1: ( rule__Multiplication__Group__1__Impl )
            // InternalText.g:3414:2: rule__Multiplication__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__1"


    // $ANTLR start "rule__Multiplication__Group__1__Impl"
    // InternalText.g:3420:1: rule__Multiplication__Group__1__Impl : ( ( rule__Multiplication__Group_1__0 )* ) ;
    public final void rule__Multiplication__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3424:1: ( ( ( rule__Multiplication__Group_1__0 )* ) )
            // InternalText.g:3425:1: ( ( rule__Multiplication__Group_1__0 )* )
            {
            // InternalText.g:3425:1: ( ( rule__Multiplication__Group_1__0 )* )
            // InternalText.g:3426:2: ( rule__Multiplication__Group_1__0 )*
            {
             before(grammarAccess.getMultiplicationAccess().getGroup_1()); 
            // InternalText.g:3427:2: ( rule__Multiplication__Group_1__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=48 && LA38_0<=50)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalText.g:3427:3: rule__Multiplication__Group_1__0
            	    {
            	    pushFollow(FOLLOW_41);
            	    rule__Multiplication__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

             after(grammarAccess.getMultiplicationAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__0"
    // InternalText.g:3436:1: rule__Multiplication__Group_1__0 : rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 ;
    public final void rule__Multiplication__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3440:1: ( rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 )
            // InternalText.g:3441:2: rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1
            {
            pushFollow(FOLLOW_35);
            rule__Multiplication__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__0"


    // $ANTLR start "rule__Multiplication__Group_1__0__Impl"
    // InternalText.g:3448:1: rule__Multiplication__Group_1__0__Impl : ( ( rule__Multiplication__Alternatives_1_0 ) ) ;
    public final void rule__Multiplication__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3452:1: ( ( ( rule__Multiplication__Alternatives_1_0 ) ) )
            // InternalText.g:3453:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            {
            // InternalText.g:3453:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            // InternalText.g:3454:2: ( rule__Multiplication__Alternatives_1_0 )
            {
             before(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); 
            // InternalText.g:3455:2: ( rule__Multiplication__Alternatives_1_0 )
            // InternalText.g:3455:3: rule__Multiplication__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Alternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__1"
    // InternalText.g:3463:1: rule__Multiplication__Group_1__1 : rule__Multiplication__Group_1__1__Impl ;
    public final void rule__Multiplication__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3467:1: ( rule__Multiplication__Group_1__1__Impl )
            // InternalText.g:3468:2: rule__Multiplication__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__1"


    // $ANTLR start "rule__Multiplication__Group_1__1__Impl"
    // InternalText.g:3474:1: rule__Multiplication__Group_1__1__Impl : ( ( rule__Multiplication__RightAssignment_1_1 ) ) ;
    public final void rule__Multiplication__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3478:1: ( ( ( rule__Multiplication__RightAssignment_1_1 ) ) )
            // InternalText.g:3479:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            {
            // InternalText.g:3479:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            // InternalText.g:3480:2: ( rule__Multiplication__RightAssignment_1_1 )
            {
             before(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); 
            // InternalText.g:3481:2: ( rule__Multiplication__RightAssignment_1_1 )
            // InternalText.g:3481:3: rule__Multiplication__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__RightAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__0"
    // InternalText.g:3490:1: rule__Multiplication__Group_1_0_0__0 : rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 ;
    public final void rule__Multiplication__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3494:1: ( rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 )
            // InternalText.g:3495:2: rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1
            {
            pushFollow(FOLLOW_42);
            rule__Multiplication__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_0__0"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__0__Impl"
    // InternalText.g:3502:1: rule__Multiplication__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3506:1: ( ( () ) )
            // InternalText.g:3507:1: ( () )
            {
            // InternalText.g:3507:1: ( () )
            // InternalText.g:3508:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0()); 
            // InternalText.g:3509:2: ()
            // InternalText.g:3509:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__1"
    // InternalText.g:3517:1: rule__Multiplication__Group_1_0_0__1 : rule__Multiplication__Group_1_0_0__1__Impl ;
    public final void rule__Multiplication__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3521:1: ( rule__Multiplication__Group_1_0_0__1__Impl )
            // InternalText.g:3522:2: rule__Multiplication__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_0__1"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__1__Impl"
    // InternalText.g:3528:1: rule__Multiplication__Group_1_0_0__1__Impl : ( '*' ) ;
    public final void rule__Multiplication__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3532:1: ( ( '*' ) )
            // InternalText.g:3533:1: ( '*' )
            {
            // InternalText.g:3533:1: ( '*' )
            // InternalText.g:3534:2: '*'
            {
             before(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__0"
    // InternalText.g:3544:1: rule__Multiplication__Group_1_0_1__0 : rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 ;
    public final void rule__Multiplication__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3548:1: ( rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 )
            // InternalText.g:3549:2: rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1
            {
            pushFollow(FOLLOW_43);
            rule__Multiplication__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_1__0"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__0__Impl"
    // InternalText.g:3556:1: rule__Multiplication__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3560:1: ( ( () ) )
            // InternalText.g:3561:1: ( () )
            {
            // InternalText.g:3561:1: ( () )
            // InternalText.g:3562:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0()); 
            // InternalText.g:3563:2: ()
            // InternalText.g:3563:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__1"
    // InternalText.g:3571:1: rule__Multiplication__Group_1_0_1__1 : rule__Multiplication__Group_1_0_1__1__Impl ;
    public final void rule__Multiplication__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3575:1: ( rule__Multiplication__Group_1_0_1__1__Impl )
            // InternalText.g:3576:2: rule__Multiplication__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_1__1"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__1__Impl"
    // InternalText.g:3582:1: rule__Multiplication__Group_1_0_1__1__Impl : ( '/' ) ;
    public final void rule__Multiplication__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3586:1: ( ( '/' ) )
            // InternalText.g:3587:1: ( '/' )
            {
            // InternalText.g:3587:1: ( '/' )
            // InternalText.g:3588:2: '/'
            {
             before(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_2__0"
    // InternalText.g:3598:1: rule__Multiplication__Group_1_0_2__0 : rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1 ;
    public final void rule__Multiplication__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3602:1: ( rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1 )
            // InternalText.g:3603:2: rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1
            {
            pushFollow(FOLLOW_40);
            rule__Multiplication__Group_1_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_2__0"


    // $ANTLR start "rule__Multiplication__Group_1_0_2__0__Impl"
    // InternalText.g:3610:1: rule__Multiplication__Group_1_0_2__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3614:1: ( ( () ) )
            // InternalText.g:3615:1: ( () )
            {
            // InternalText.g:3615:1: ( () )
            // InternalText.g:3616:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0()); 
            // InternalText.g:3617:2: ()
            // InternalText.g:3617:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_2__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_2__1"
    // InternalText.g:3625:1: rule__Multiplication__Group_1_0_2__1 : rule__Multiplication__Group_1_0_2__1__Impl ;
    public final void rule__Multiplication__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3629:1: ( rule__Multiplication__Group_1_0_2__1__Impl )
            // InternalText.g:3630:2: rule__Multiplication__Group_1_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_2__1"


    // $ANTLR start "rule__Multiplication__Group_1_0_2__1__Impl"
    // InternalText.g:3636:1: rule__Multiplication__Group_1_0_2__1__Impl : ( '%' ) ;
    public final void rule__Multiplication__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3640:1: ( ( '%' ) )
            // InternalText.g:3641:1: ( '%' )
            {
            // InternalText.g:3641:1: ( '%' )
            // InternalText.g:3642:2: '%'
            {
             before(grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1()); 
            match(input,50,FOLLOW_2); 
             after(grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_2__1__Impl"


    // $ANTLR start "rule__Bitwise__Group__0"
    // InternalText.g:3652:1: rule__Bitwise__Group__0 : rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1 ;
    public final void rule__Bitwise__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3656:1: ( rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1 )
            // InternalText.g:3657:2: rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1
            {
            pushFollow(FOLLOW_44);
            rule__Bitwise__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Bitwise__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group__0"


    // $ANTLR start "rule__Bitwise__Group__0__Impl"
    // InternalText.g:3664:1: rule__Bitwise__Group__0__Impl : ( ruleUnary ) ;
    public final void rule__Bitwise__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3668:1: ( ( ruleUnary ) )
            // InternalText.g:3669:1: ( ruleUnary )
            {
            // InternalText.g:3669:1: ( ruleUnary )
            // InternalText.g:3670:2: ruleUnary
            {
             before(grammarAccess.getBitwiseAccess().getUnaryParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleUnary();

            state._fsp--;

             after(grammarAccess.getBitwiseAccess().getUnaryParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group__0__Impl"


    // $ANTLR start "rule__Bitwise__Group__1"
    // InternalText.g:3679:1: rule__Bitwise__Group__1 : rule__Bitwise__Group__1__Impl ;
    public final void rule__Bitwise__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3683:1: ( rule__Bitwise__Group__1__Impl )
            // InternalText.g:3684:2: rule__Bitwise__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group__1"


    // $ANTLR start "rule__Bitwise__Group__1__Impl"
    // InternalText.g:3690:1: rule__Bitwise__Group__1__Impl : ( ( rule__Bitwise__Group_1__0 )* ) ;
    public final void rule__Bitwise__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3694:1: ( ( ( rule__Bitwise__Group_1__0 )* ) )
            // InternalText.g:3695:1: ( ( rule__Bitwise__Group_1__0 )* )
            {
            // InternalText.g:3695:1: ( ( rule__Bitwise__Group_1__0 )* )
            // InternalText.g:3696:2: ( rule__Bitwise__Group_1__0 )*
            {
             before(grammarAccess.getBitwiseAccess().getGroup_1()); 
            // InternalText.g:3697:2: ( rule__Bitwise__Group_1__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( ((LA39_0>=7 && LA39_0<=11)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalText.g:3697:3: rule__Bitwise__Group_1__0
            	    {
            	    pushFollow(FOLLOW_45);
            	    rule__Bitwise__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

             after(grammarAccess.getBitwiseAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group__1__Impl"


    // $ANTLR start "rule__Bitwise__Group_1__0"
    // InternalText.g:3706:1: rule__Bitwise__Group_1__0 : rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1 ;
    public final void rule__Bitwise__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3710:1: ( rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1 )
            // InternalText.g:3711:2: rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1
            {
            pushFollow(FOLLOW_44);
            rule__Bitwise__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Bitwise__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__0"


    // $ANTLR start "rule__Bitwise__Group_1__0__Impl"
    // InternalText.g:3718:1: rule__Bitwise__Group_1__0__Impl : ( () ) ;
    public final void rule__Bitwise__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3722:1: ( ( () ) )
            // InternalText.g:3723:1: ( () )
            {
            // InternalText.g:3723:1: ( () )
            // InternalText.g:3724:2: ()
            {
             before(grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0()); 
            // InternalText.g:3725:2: ()
            // InternalText.g:3725:3: 
            {
            }

             after(grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__0__Impl"


    // $ANTLR start "rule__Bitwise__Group_1__1"
    // InternalText.g:3733:1: rule__Bitwise__Group_1__1 : rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2 ;
    public final void rule__Bitwise__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3737:1: ( rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2 )
            // InternalText.g:3738:2: rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2
            {
            pushFollow(FOLLOW_35);
            rule__Bitwise__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Bitwise__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__1"


    // $ANTLR start "rule__Bitwise__Group_1__1__Impl"
    // InternalText.g:3745:1: rule__Bitwise__Group_1__1__Impl : ( ( rule__Bitwise__OpAssignment_1_1 ) ) ;
    public final void rule__Bitwise__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3749:1: ( ( ( rule__Bitwise__OpAssignment_1_1 ) ) )
            // InternalText.g:3750:1: ( ( rule__Bitwise__OpAssignment_1_1 ) )
            {
            // InternalText.g:3750:1: ( ( rule__Bitwise__OpAssignment_1_1 ) )
            // InternalText.g:3751:2: ( rule__Bitwise__OpAssignment_1_1 )
            {
             before(grammarAccess.getBitwiseAccess().getOpAssignment_1_1()); 
            // InternalText.g:3752:2: ( rule__Bitwise__OpAssignment_1_1 )
            // InternalText.g:3752:3: rule__Bitwise__OpAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__OpAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getBitwiseAccess().getOpAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__1__Impl"


    // $ANTLR start "rule__Bitwise__Group_1__2"
    // InternalText.g:3760:1: rule__Bitwise__Group_1__2 : rule__Bitwise__Group_1__2__Impl ;
    public final void rule__Bitwise__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3764:1: ( rule__Bitwise__Group_1__2__Impl )
            // InternalText.g:3765:2: rule__Bitwise__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__2"


    // $ANTLR start "rule__Bitwise__Group_1__2__Impl"
    // InternalText.g:3771:1: rule__Bitwise__Group_1__2__Impl : ( ( rule__Bitwise__RightAssignment_1_2 ) ) ;
    public final void rule__Bitwise__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3775:1: ( ( ( rule__Bitwise__RightAssignment_1_2 ) ) )
            // InternalText.g:3776:1: ( ( rule__Bitwise__RightAssignment_1_2 ) )
            {
            // InternalText.g:3776:1: ( ( rule__Bitwise__RightAssignment_1_2 ) )
            // InternalText.g:3777:2: ( rule__Bitwise__RightAssignment_1_2 )
            {
             before(grammarAccess.getBitwiseAccess().getRightAssignment_1_2()); 
            // InternalText.g:3778:2: ( rule__Bitwise__RightAssignment_1_2 )
            // InternalText.g:3778:3: rule__Bitwise__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getBitwiseAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__Group_1__2__Impl"


    // $ANTLR start "rule__Unary__Group_0__0"
    // InternalText.g:3787:1: rule__Unary__Group_0__0 : rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1 ;
    public final void rule__Unary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3791:1: ( rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1 )
            // InternalText.g:3792:2: rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1
            {
            pushFollow(FOLLOW_46);
            rule__Unary__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Unary__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__0"


    // $ANTLR start "rule__Unary__Group_0__0__Impl"
    // InternalText.g:3799:1: rule__Unary__Group_0__0__Impl : ( () ) ;
    public final void rule__Unary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3803:1: ( ( () ) )
            // InternalText.g:3804:1: ( () )
            {
            // InternalText.g:3804:1: ( () )
            // InternalText.g:3805:2: ()
            {
             before(grammarAccess.getUnaryAccess().getBitwiseNegateAction_0_0()); 
            // InternalText.g:3806:2: ()
            // InternalText.g:3806:3: 
            {
            }

             after(grammarAccess.getUnaryAccess().getBitwiseNegateAction_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__0__Impl"


    // $ANTLR start "rule__Unary__Group_0__1"
    // InternalText.g:3814:1: rule__Unary__Group_0__1 : rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2 ;
    public final void rule__Unary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3818:1: ( rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2 )
            // InternalText.g:3819:2: rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2
            {
            pushFollow(FOLLOW_35);
            rule__Unary__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Unary__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__1"


    // $ANTLR start "rule__Unary__Group_0__1__Impl"
    // InternalText.g:3826:1: rule__Unary__Group_0__1__Impl : ( '~' ) ;
    public final void rule__Unary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3830:1: ( ( '~' ) )
            // InternalText.g:3831:1: ( '~' )
            {
            // InternalText.g:3831:1: ( '~' )
            // InternalText.g:3832:2: '~'
            {
             before(grammarAccess.getUnaryAccess().getTildeKeyword_0_1()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getUnaryAccess().getTildeKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__1__Impl"


    // $ANTLR start "rule__Unary__Group_0__2"
    // InternalText.g:3841:1: rule__Unary__Group_0__2 : rule__Unary__Group_0__2__Impl ;
    public final void rule__Unary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3845:1: ( rule__Unary__Group_0__2__Impl )
            // InternalText.g:3846:2: rule__Unary__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Unary__Group_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__2"


    // $ANTLR start "rule__Unary__Group_0__2__Impl"
    // InternalText.g:3852:1: rule__Unary__Group_0__2__Impl : ( ( rule__Unary__ExpressionAssignment_0_2 ) ) ;
    public final void rule__Unary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3856:1: ( ( ( rule__Unary__ExpressionAssignment_0_2 ) ) )
            // InternalText.g:3857:1: ( ( rule__Unary__ExpressionAssignment_0_2 ) )
            {
            // InternalText.g:3857:1: ( ( rule__Unary__ExpressionAssignment_0_2 ) )
            // InternalText.g:3858:2: ( rule__Unary__ExpressionAssignment_0_2 )
            {
             before(grammarAccess.getUnaryAccess().getExpressionAssignment_0_2()); 
            // InternalText.g:3859:2: ( rule__Unary__ExpressionAssignment_0_2 )
            // InternalText.g:3859:3: rule__Unary__ExpressionAssignment_0_2
            {
            pushFollow(FOLLOW_2);
            rule__Unary__ExpressionAssignment_0_2();

            state._fsp--;


            }

             after(grammarAccess.getUnaryAccess().getExpressionAssignment_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_0__2__Impl"


    // $ANTLR start "rule__Unary__Group_1__0"
    // InternalText.g:3868:1: rule__Unary__Group_1__0 : rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1 ;
    public final void rule__Unary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3872:1: ( rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1 )
            // InternalText.g:3873:2: rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1
            {
            pushFollow(FOLLOW_47);
            rule__Unary__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Unary__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1__0"


    // $ANTLR start "rule__Unary__Group_1__0__Impl"
    // InternalText.g:3880:1: rule__Unary__Group_1__0__Impl : ( rulePrimary ) ;
    public final void rule__Unary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3884:1: ( ( rulePrimary ) )
            // InternalText.g:3885:1: ( rulePrimary )
            {
            // InternalText.g:3885:1: ( rulePrimary )
            // InternalText.g:3886:2: rulePrimary
            {
             before(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1__0__Impl"


    // $ANTLR start "rule__Unary__Group_1__1"
    // InternalText.g:3895:1: rule__Unary__Group_1__1 : rule__Unary__Group_1__1__Impl ;
    public final void rule__Unary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3899:1: ( rule__Unary__Group_1__1__Impl )
            // InternalText.g:3900:2: rule__Unary__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Unary__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1__1"


    // $ANTLR start "rule__Unary__Group_1__1__Impl"
    // InternalText.g:3906:1: rule__Unary__Group_1__1__Impl : ( ( rule__Unary__Alternatives_1_1 )? ) ;
    public final void rule__Unary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3910:1: ( ( ( rule__Unary__Alternatives_1_1 )? ) )
            // InternalText.g:3911:1: ( ( rule__Unary__Alternatives_1_1 )? )
            {
            // InternalText.g:3911:1: ( ( rule__Unary__Alternatives_1_1 )? )
            // InternalText.g:3912:2: ( rule__Unary__Alternatives_1_1 )?
            {
             before(grammarAccess.getUnaryAccess().getAlternatives_1_1()); 
            // InternalText.g:3913:2: ( rule__Unary__Alternatives_1_1 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=52 && LA40_0<=53)) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalText.g:3913:3: rule__Unary__Alternatives_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Unary__Alternatives_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getUnaryAccess().getAlternatives_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1__1__Impl"


    // $ANTLR start "rule__Unary__Group_1_1_0__0"
    // InternalText.g:3922:1: rule__Unary__Group_1_1_0__0 : rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1 ;
    public final void rule__Unary__Group_1_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3926:1: ( rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1 )
            // InternalText.g:3927:2: rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1
            {
            pushFollow(FOLLOW_48);
            rule__Unary__Group_1_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Unary__Group_1_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_0__0"


    // $ANTLR start "rule__Unary__Group_1_1_0__0__Impl"
    // InternalText.g:3934:1: rule__Unary__Group_1_1_0__0__Impl : ( () ) ;
    public final void rule__Unary__Group_1_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3938:1: ( ( () ) )
            // InternalText.g:3939:1: ( () )
            {
            // InternalText.g:3939:1: ( () )
            // InternalText.g:3940:2: ()
            {
             before(grammarAccess.getUnaryAccess().getIncrementExpressionAction_1_1_0_0()); 
            // InternalText.g:3941:2: ()
            // InternalText.g:3941:3: 
            {
            }

             after(grammarAccess.getUnaryAccess().getIncrementExpressionAction_1_1_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_0__0__Impl"


    // $ANTLR start "rule__Unary__Group_1_1_0__1"
    // InternalText.g:3949:1: rule__Unary__Group_1_1_0__1 : rule__Unary__Group_1_1_0__1__Impl ;
    public final void rule__Unary__Group_1_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3953:1: ( rule__Unary__Group_1_1_0__1__Impl )
            // InternalText.g:3954:2: rule__Unary__Group_1_1_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Unary__Group_1_1_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_0__1"


    // $ANTLR start "rule__Unary__Group_1_1_0__1__Impl"
    // InternalText.g:3960:1: rule__Unary__Group_1_1_0__1__Impl : ( '++' ) ;
    public final void rule__Unary__Group_1_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3964:1: ( ( '++' ) )
            // InternalText.g:3965:1: ( '++' )
            {
            // InternalText.g:3965:1: ( '++' )
            // InternalText.g:3966:2: '++'
            {
             before(grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_1_0_1()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_0__1__Impl"


    // $ANTLR start "rule__Unary__Group_1_1_1__0"
    // InternalText.g:3976:1: rule__Unary__Group_1_1_1__0 : rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1 ;
    public final void rule__Unary__Group_1_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3980:1: ( rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1 )
            // InternalText.g:3981:2: rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1
            {
            pushFollow(FOLLOW_47);
            rule__Unary__Group_1_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Unary__Group_1_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_1__0"


    // $ANTLR start "rule__Unary__Group_1_1_1__0__Impl"
    // InternalText.g:3988:1: rule__Unary__Group_1_1_1__0__Impl : ( () ) ;
    public final void rule__Unary__Group_1_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:3992:1: ( ( () ) )
            // InternalText.g:3993:1: ( () )
            {
            // InternalText.g:3993:1: ( () )
            // InternalText.g:3994:2: ()
            {
             before(grammarAccess.getUnaryAccess().getDecrementExpressionAction_1_1_1_0()); 
            // InternalText.g:3995:2: ()
            // InternalText.g:3995:3: 
            {
            }

             after(grammarAccess.getUnaryAccess().getDecrementExpressionAction_1_1_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_1__0__Impl"


    // $ANTLR start "rule__Unary__Group_1_1_1__1"
    // InternalText.g:4003:1: rule__Unary__Group_1_1_1__1 : rule__Unary__Group_1_1_1__1__Impl ;
    public final void rule__Unary__Group_1_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4007:1: ( rule__Unary__Group_1_1_1__1__Impl )
            // InternalText.g:4008:2: rule__Unary__Group_1_1_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Unary__Group_1_1_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_1__1"


    // $ANTLR start "rule__Unary__Group_1_1_1__1__Impl"
    // InternalText.g:4014:1: rule__Unary__Group_1_1_1__1__Impl : ( '--' ) ;
    public final void rule__Unary__Group_1_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4018:1: ( ( '--' ) )
            // InternalText.g:4019:1: ( '--' )
            {
            // InternalText.g:4019:1: ( '--' )
            // InternalText.g:4020:2: '--'
            {
             before(grammarAccess.getUnaryAccess().getHyphenMinusHyphenMinusKeyword_1_1_1_1()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getUnaryAccess().getHyphenMinusHyphenMinusKeyword_1_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__Group_1_1_1__1__Impl"


    // $ANTLR start "rule__Primary__Group_2__0"
    // InternalText.g:4030:1: rule__Primary__Group_2__0 : rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1 ;
    public final void rule__Primary__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4034:1: ( rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1 )
            // InternalText.g:4035:2: rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1
            {
            pushFollow(FOLLOW_35);
            rule__Primary__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__0"


    // $ANTLR start "rule__Primary__Group_2__0__Impl"
    // InternalText.g:4042:1: rule__Primary__Group_2__0__Impl : ( () ) ;
    public final void rule__Primary__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4046:1: ( ( () ) )
            // InternalText.g:4047:1: ( () )
            {
            // InternalText.g:4047:1: ( () )
            // InternalText.g:4048:2: ()
            {
             before(grammarAccess.getPrimaryAccess().getBracketsAction_2_0()); 
            // InternalText.g:4049:2: ()
            // InternalText.g:4049:3: 
            {
            }

             after(grammarAccess.getPrimaryAccess().getBracketsAction_2_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__0__Impl"


    // $ANTLR start "rule__Primary__Group_2__1"
    // InternalText.g:4057:1: rule__Primary__Group_2__1 : rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2 ;
    public final void rule__Primary__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4061:1: ( rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2 )
            // InternalText.g:4062:2: rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2
            {
            pushFollow(FOLLOW_20);
            rule__Primary__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__1"


    // $ANTLR start "rule__Primary__Group_2__1__Impl"
    // InternalText.g:4069:1: rule__Primary__Group_2__1__Impl : ( '(' ) ;
    public final void rule__Primary__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4073:1: ( ( '(' ) )
            // InternalText.g:4074:1: ( '(' )
            {
            // InternalText.g:4074:1: ( '(' )
            // InternalText.g:4075:2: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_2_1()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__1__Impl"


    // $ANTLR start "rule__Primary__Group_2__2"
    // InternalText.g:4084:1: rule__Primary__Group_2__2 : rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3 ;
    public final void rule__Primary__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4088:1: ( rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3 )
            // InternalText.g:4089:2: rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3
            {
            pushFollow(FOLLOW_49);
            rule__Primary__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__2"


    // $ANTLR start "rule__Primary__Group_2__2__Impl"
    // InternalText.g:4096:1: rule__Primary__Group_2__2__Impl : ( ( rule__Primary__ExpressionAssignment_2_2 ) ) ;
    public final void rule__Primary__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4100:1: ( ( ( rule__Primary__ExpressionAssignment_2_2 ) ) )
            // InternalText.g:4101:1: ( ( rule__Primary__ExpressionAssignment_2_2 ) )
            {
            // InternalText.g:4101:1: ( ( rule__Primary__ExpressionAssignment_2_2 ) )
            // InternalText.g:4102:2: ( rule__Primary__ExpressionAssignment_2_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_2_2()); 
            // InternalText.g:4103:2: ( rule__Primary__ExpressionAssignment_2_2 )
            // InternalText.g:4103:3: rule__Primary__ExpressionAssignment_2_2
            {
            pushFollow(FOLLOW_2);
            rule__Primary__ExpressionAssignment_2_2();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryAccess().getExpressionAssignment_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__2__Impl"


    // $ANTLR start "rule__Primary__Group_2__3"
    // InternalText.g:4111:1: rule__Primary__Group_2__3 : rule__Primary__Group_2__3__Impl ;
    public final void rule__Primary__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4115:1: ( rule__Primary__Group_2__3__Impl )
            // InternalText.g:4116:2: rule__Primary__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Primary__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__3"


    // $ANTLR start "rule__Primary__Group_2__3__Impl"
    // InternalText.g:4122:1: rule__Primary__Group_2__3__Impl : ( ')' ) ;
    public final void rule__Primary__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4126:1: ( ( ')' ) )
            // InternalText.g:4127:1: ( ')' )
            {
            // InternalText.g:4127:1: ( ')' )
            // InternalText.g:4128:2: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_2_3()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_2__3__Impl"


    // $ANTLR start "rule__Game__EntitiesAssignment_0"
    // InternalText.g:4138:1: rule__Game__EntitiesAssignment_0 : ( ruleEntity ) ;
    public final void rule__Game__EntitiesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4142:1: ( ( ruleEntity ) )
            // InternalText.g:4143:2: ( ruleEntity )
            {
            // InternalText.g:4143:2: ( ruleEntity )
            // InternalText.g:4144:3: ruleEntity
            {
             before(grammarAccess.getGameAccess().getEntitiesEntityParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getGameAccess().getEntitiesEntityParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Game__EntitiesAssignment_0"


    // $ANTLR start "rule__Game__SystemsAssignment_1"
    // InternalText.g:4153:1: rule__Game__SystemsAssignment_1 : ( ruleSystem ) ;
    public final void rule__Game__SystemsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4157:1: ( ( ruleSystem ) )
            // InternalText.g:4158:2: ( ruleSystem )
            {
            // InternalText.g:4158:2: ( ruleSystem )
            // InternalText.g:4159:3: ruleSystem
            {
             before(grammarAccess.getGameAccess().getSystemsSystemParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleSystem();

            state._fsp--;

             after(grammarAccess.getGameAccess().getSystemsSystemParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Game__SystemsAssignment_1"


    // $ANTLR start "rule__Entity__NameAssignment_0"
    // InternalText.g:4168:1: rule__Entity__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Entity__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4172:1: ( ( RULE_ID ) )
            // InternalText.g:4173:2: ( RULE_ID )
            {
            // InternalText.g:4173:2: ( RULE_ID )
            // InternalText.g:4174:3: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__NameAssignment_0"


    // $ANTLR start "rule__Entity__BaseAssignment_1_1"
    // InternalText.g:4183:1: rule__Entity__BaseAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__Entity__BaseAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4187:1: ( ( RULE_ID ) )
            // InternalText.g:4188:2: ( RULE_ID )
            {
            // InternalText.g:4188:2: ( RULE_ID )
            // InternalText.g:4189:3: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getBaseIDTerminalRuleCall_1_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEntityAccess().getBaseIDTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__BaseAssignment_1_1"


    // $ANTLR start "rule__Entity__ComponentsAssignment_3_0"
    // InternalText.g:4198:1: rule__Entity__ComponentsAssignment_3_0 : ( ruleComponent ) ;
    public final void rule__Entity__ComponentsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4202:1: ( ( ruleComponent ) )
            // InternalText.g:4203:2: ( ruleComponent )
            {
            // InternalText.g:4203:2: ( ruleComponent )
            // InternalText.g:4204:3: ruleComponent
            {
             before(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__ComponentsAssignment_3_0"


    // $ANTLR start "rule__Entity__ComponentsAssignment_3_1_1"
    // InternalText.g:4213:1: rule__Entity__ComponentsAssignment_3_1_1 : ( ruleComponent ) ;
    public final void rule__Entity__ComponentsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4217:1: ( ( ruleComponent ) )
            // InternalText.g:4218:2: ( ruleComponent )
            {
            // InternalText.g:4218:2: ( ruleComponent )
            // InternalText.g:4219:3: ruleComponent
            {
             before(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__ComponentsAssignment_3_1_1"


    // $ANTLR start "rule__Entity__EntitiesAssignment_5_1"
    // InternalText.g:4228:1: rule__Entity__EntitiesAssignment_5_1 : ( ruleEntity ) ;
    public final void rule__Entity__EntitiesAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4232:1: ( ( ruleEntity ) )
            // InternalText.g:4233:2: ( ruleEntity )
            {
            // InternalText.g:4233:2: ( ruleEntity )
            // InternalText.g:4234:3: ruleEntity
            {
             before(grammarAccess.getEntityAccess().getEntitiesEntityParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleEntity();

            state._fsp--;

             after(grammarAccess.getEntityAccess().getEntitiesEntityParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__EntitiesAssignment_5_1"


    // $ANTLR start "rule__Component__NameAssignment_0"
    // InternalText.g:4243:1: rule__Component__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Component__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4247:1: ( ( RULE_ID ) )
            // InternalText.g:4248:2: ( RULE_ID )
            {
            // InternalText.g:4248:2: ( RULE_ID )
            // InternalText.g:4249:3: RULE_ID
            {
             before(grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__NameAssignment_0"


    // $ANTLR start "rule__Component__ValueAssignment_1"
    // InternalText.g:4258:1: rule__Component__ValueAssignment_1 : ( ruleValue ) ;
    public final void rule__Component__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4262:1: ( ( ruleValue ) )
            // InternalText.g:4263:2: ( ruleValue )
            {
            // InternalText.g:4263:2: ( ruleValue )
            // InternalText.g:4264:3: ruleValue
            {
             before(grammarAccess.getComponentAccess().getValueValueParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getComponentAccess().getValueValueParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Component__ValueAssignment_1"


    // $ANTLR start "rule__Vector__NumbersAssignment"
    // InternalText.g:4273:1: rule__Vector__NumbersAssignment : ( RULE_REAL ) ;
    public final void rule__Vector__NumbersAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4277:1: ( ( RULE_REAL ) )
            // InternalText.g:4278:2: ( RULE_REAL )
            {
            // InternalText.g:4278:2: ( RULE_REAL )
            // InternalText.g:4279:3: RULE_REAL
            {
             before(grammarAccess.getVectorAccess().getNumbersREALTerminalRuleCall_0()); 
            match(input,RULE_REAL,FOLLOW_2); 
             after(grammarAccess.getVectorAccess().getNumbersREALTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__NumbersAssignment"


    // $ANTLR start "rule__Word__ValuesAssignment"
    // InternalText.g:4288:1: rule__Word__ValuesAssignment : ( RULE_ID ) ;
    public final void rule__Word__ValuesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4292:1: ( ( RULE_ID ) )
            // InternalText.g:4293:2: ( RULE_ID )
            {
            // InternalText.g:4293:2: ( RULE_ID )
            // InternalText.g:4294:3: RULE_ID
            {
             before(grammarAccess.getWordAccess().getValuesIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getWordAccess().getValuesIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Word__ValuesAssignment"


    // $ANTLR start "rule__System__NameAssignment_0"
    // InternalText.g:4303:1: rule__System__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__System__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4307:1: ( ( RULE_ID ) )
            // InternalText.g:4308:2: ( RULE_ID )
            {
            // InternalText.g:4308:2: ( RULE_ID )
            // InternalText.g:4309:3: RULE_ID
            {
             before(grammarAccess.getSystemAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSystemAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__NameAssignment_0"


    // $ANTLR start "rule__System__CommandsAssignment_2"
    // InternalText.g:4318:1: rule__System__CommandsAssignment_2 : ( ruleCommand ) ;
    public final void rule__System__CommandsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4322:1: ( ( ruleCommand ) )
            // InternalText.g:4323:2: ( ruleCommand )
            {
            // InternalText.g:4323:2: ( ruleCommand )
            // InternalText.g:4324:3: ruleCommand
            {
             before(grammarAccess.getSystemAccess().getCommandsCommandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getSystemAccess().getCommandsCommandParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__System__CommandsAssignment_2"


    // $ANTLR start "rule__Loop__EntityAssignment_3"
    // InternalText.g:4333:1: rule__Loop__EntityAssignment_3 : ( RULE_ID ) ;
    public final void rule__Loop__EntityAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4337:1: ( ( RULE_ID ) )
            // InternalText.g:4338:2: ( RULE_ID )
            {
            // InternalText.g:4338:2: ( RULE_ID )
            // InternalText.g:4339:3: RULE_ID
            {
             before(grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__EntityAssignment_3"


    // $ANTLR start "rule__Loop__ConstraintsAssignment_4_1"
    // InternalText.g:4348:1: rule__Loop__ConstraintsAssignment_4_1 : ( RULE_ID ) ;
    public final void rule__Loop__ConstraintsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4352:1: ( ( RULE_ID ) )
            // InternalText.g:4353:2: ( RULE_ID )
            {
            // InternalText.g:4353:2: ( RULE_ID )
            // InternalText.g:4354:3: RULE_ID
            {
             before(grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_4_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__ConstraintsAssignment_4_1"


    // $ANTLR start "rule__Loop__CommandsAssignment_6"
    // InternalText.g:4363:1: rule__Loop__CommandsAssignment_6 : ( ruleCommand ) ;
    public final void rule__Loop__CommandsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4367:1: ( ( ruleCommand ) )
            // InternalText.g:4368:2: ( ruleCommand )
            {
            // InternalText.g:4368:2: ( ruleCommand )
            // InternalText.g:4369:3: ruleCommand
            {
             before(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Loop__CommandsAssignment_6"


    // $ANTLR start "rule__Branch__IfAssignment_1"
    // InternalText.g:4378:1: rule__Branch__IfAssignment_1 : ( ruleCondition ) ;
    public final void rule__Branch__IfAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4382:1: ( ( ruleCondition ) )
            // InternalText.g:4383:2: ( ruleCondition )
            {
            // InternalText.g:4383:2: ( ruleCondition )
            // InternalText.g:4384:3: ruleCondition
            {
             before(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__IfAssignment_1"


    // $ANTLR start "rule__Branch__ElseIfsAssignment_2_2"
    // InternalText.g:4393:1: rule__Branch__ElseIfsAssignment_2_2 : ( ruleCondition ) ;
    public final void rule__Branch__ElseIfsAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4397:1: ( ( ruleCondition ) )
            // InternalText.g:4398:2: ( ruleCondition )
            {
            // InternalText.g:4398:2: ( ruleCondition )
            // InternalText.g:4399:3: ruleCondition
            {
             before(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__ElseIfsAssignment_2_2"


    // $ANTLR start "rule__Branch__CommandsAssignment_3_2"
    // InternalText.g:4408:1: rule__Branch__CommandsAssignment_3_2 : ( ruleCommand ) ;
    public final void rule__Branch__CommandsAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4412:1: ( ( ruleCommand ) )
            // InternalText.g:4413:2: ( ruleCommand )
            {
            // InternalText.g:4413:2: ( ruleCommand )
            // InternalText.g:4414:3: ruleCommand
            {
             before(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Branch__CommandsAssignment_3_2"


    // $ANTLR start "rule__Condition__ConditionAssignment_0"
    // InternalText.g:4423:1: rule__Condition__ConditionAssignment_0 : ( ruleExpression ) ;
    public final void rule__Condition__ConditionAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4427:1: ( ( ruleExpression ) )
            // InternalText.g:4428:2: ( ruleExpression )
            {
            // InternalText.g:4428:2: ( ruleExpression )
            // InternalText.g:4429:3: ruleExpression
            {
             before(grammarAccess.getConditionAccess().getConditionExpressionParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getConditionExpressionParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__ConditionAssignment_0"


    // $ANTLR start "rule__Condition__CommandsAssignment_2"
    // InternalText.g:4438:1: rule__Condition__CommandsAssignment_2 : ( ruleCommand ) ;
    public final void rule__Condition__CommandsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4442:1: ( ( ruleCommand ) )
            // InternalText.g:4443:2: ( ruleCommand )
            {
            // InternalText.g:4443:2: ( ruleCommand )
            // InternalText.g:4444:3: ruleCommand
            {
             before(grammarAccess.getConditionAccess().getCommandsCommandParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getConditionAccess().getCommandsCommandParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Condition__CommandsAssignment_2"


    // $ANTLR start "rule__Assignment__AccessAssignment_0"
    // InternalText.g:4453:1: rule__Assignment__AccessAssignment_0 : ( ruleAccess ) ;
    public final void rule__Assignment__AccessAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4457:1: ( ( ruleAccess ) )
            // InternalText.g:4458:2: ( ruleAccess )
            {
            // InternalText.g:4458:2: ( ruleAccess )
            // InternalText.g:4459:3: ruleAccess
            {
             before(grammarAccess.getAssignmentAccess().getAccessAccessParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleAccess();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getAccessAccessParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__AccessAssignment_0"


    // $ANTLR start "rule__Assignment__TypeAssignment_1"
    // InternalText.g:4468:1: rule__Assignment__TypeAssignment_1 : ( ruleAssignmentType ) ;
    public final void rule__Assignment__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4472:1: ( ( ruleAssignmentType ) )
            // InternalText.g:4473:2: ( ruleAssignmentType )
            {
            // InternalText.g:4473:2: ( ruleAssignmentType )
            // InternalText.g:4474:3: ruleAssignmentType
            {
             before(grammarAccess.getAssignmentAccess().getTypeAssignmentTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignmentType();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getTypeAssignmentTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__TypeAssignment_1"


    // $ANTLR start "rule__Assignment__ExpressionAssignment_2"
    // InternalText.g:4483:1: rule__Assignment__ExpressionAssignment_2 : ( ruleExpression ) ;
    public final void rule__Assignment__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4487:1: ( ( ruleExpression ) )
            // InternalText.g:4488:2: ( ruleExpression )
            {
            // InternalText.g:4488:2: ( ruleExpression )
            // InternalText.g:4489:3: ruleExpression
            {
             before(grammarAccess.getAssignmentAccess().getExpressionExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getExpressionExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__ExpressionAssignment_2"


    // $ANTLR start "rule__Call__NameAssignment_0"
    // InternalText.g:4498:1: rule__Call__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Call__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4502:1: ( ( RULE_ID ) )
            // InternalText.g:4503:2: ( RULE_ID )
            {
            // InternalText.g:4503:2: ( RULE_ID )
            // InternalText.g:4504:3: RULE_ID
            {
             before(grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__NameAssignment_0"


    // $ANTLR start "rule__Call__ParametersAssignment_2"
    // InternalText.g:4513:1: rule__Call__ParametersAssignment_2 : ( ruleExpression ) ;
    public final void rule__Call__ParametersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4517:1: ( ( ruleExpression ) )
            // InternalText.g:4518:2: ( ruleExpression )
            {
            // InternalText.g:4518:2: ( ruleExpression )
            // InternalText.g:4519:3: ruleExpression
            {
             before(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__ParametersAssignment_2"


    // $ANTLR start "rule__Call__ParametersAssignment_3_1"
    // InternalText.g:4528:1: rule__Call__ParametersAssignment_3_1 : ( ruleExpression ) ;
    public final void rule__Call__ParametersAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4532:1: ( ( ruleExpression ) )
            // InternalText.g:4533:2: ( ruleExpression )
            {
            // InternalText.g:4533:2: ( ruleExpression )
            // InternalText.g:4534:3: ruleExpression
            {
             before(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Call__ParametersAssignment_3_1"


    // $ANTLR start "rule__Access__NamesAssignment_0"
    // InternalText.g:4543:1: rule__Access__NamesAssignment_0 : ( RULE_ID ) ;
    public final void rule__Access__NamesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4547:1: ( ( RULE_ID ) )
            // InternalText.g:4548:2: ( RULE_ID )
            {
            // InternalText.g:4548:2: ( RULE_ID )
            // InternalText.g:4549:3: RULE_ID
            {
             before(grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__NamesAssignment_0"


    // $ANTLR start "rule__Access__NamesAssignment_1_1"
    // InternalText.g:4558:1: rule__Access__NamesAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__Access__NamesAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4562:1: ( ( RULE_ID ) )
            // InternalText.g:4563:2: ( RULE_ID )
            {
            // InternalText.g:4563:2: ( RULE_ID )
            // InternalText.g:4564:3: RULE_ID
            {
             before(grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_1_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Access__NamesAssignment_1_1"


    // $ANTLR start "rule__Expression__RightAssignment_1_2"
    // InternalText.g:4573:1: rule__Expression__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Expression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4577:1: ( ( ruleAnd ) )
            // InternalText.g:4578:2: ( ruleAnd )
            {
            // InternalText.g:4578:2: ( ruleAnd )
            // InternalText.g:4579:3: ruleAnd
            {
             before(grammarAccess.getExpressionAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getRightAndParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression__RightAssignment_1_2"


    // $ANTLR start "rule__And__RightAssignment_1_2"
    // InternalText.g:4588:1: rule__And__RightAssignment_1_2 : ( rulePrimaryB ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4592:1: ( ( rulePrimaryB ) )
            // InternalText.g:4593:2: ( rulePrimaryB )
            {
            // InternalText.g:4593:2: ( rulePrimaryB )
            // InternalText.g:4594:3: rulePrimaryB
            {
             before(grammarAccess.getAndAccess().getRightPrimaryBParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            rulePrimaryB();

            state._fsp--;

             after(grammarAccess.getAndAccess().getRightPrimaryBParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__RightAssignment_1_2"


    // $ANTLR start "rule__PrimaryB__ExpressionAssignment_1_2"
    // InternalText.g:4603:1: rule__PrimaryB__ExpressionAssignment_1_2 : ( ruleAtomicB ) ;
    public final void rule__PrimaryB__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4607:1: ( ( ruleAtomicB ) )
            // InternalText.g:4608:2: ( ruleAtomicB )
            {
            // InternalText.g:4608:2: ( ruleAtomicB )
            // InternalText.g:4609:3: ruleAtomicB
            {
             before(grammarAccess.getPrimaryBAccess().getExpressionAtomicBParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAtomicB();

            state._fsp--;

             after(grammarAccess.getPrimaryBAccess().getExpressionAtomicBParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimaryB__ExpressionAssignment_1_2"


    // $ANTLR start "rule__AtomicB__TypeAssignment_1_1"
    // InternalText.g:4618:1: rule__AtomicB__TypeAssignment_1_1 : ( ruleRelationType ) ;
    public final void rule__AtomicB__TypeAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4622:1: ( ( ruleRelationType ) )
            // InternalText.g:4623:2: ( ruleRelationType )
            {
            // InternalText.g:4623:2: ( ruleRelationType )
            // InternalText.g:4624:3: ruleRelationType
            {
             before(grammarAccess.getAtomicBAccess().getTypeRelationTypeEnumRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleRelationType();

            state._fsp--;

             after(grammarAccess.getAtomicBAccess().getTypeRelationTypeEnumRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__TypeAssignment_1_1"


    // $ANTLR start "rule__AtomicB__RightAssignment_1_2"
    // InternalText.g:4633:1: rule__AtomicB__RightAssignment_1_2 : ( ruleAddition ) ;
    public final void rule__AtomicB__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4637:1: ( ( ruleAddition ) )
            // InternalText.g:4638:2: ( ruleAddition )
            {
            // InternalText.g:4638:2: ( ruleAddition )
            // InternalText.g:4639:3: ruleAddition
            {
             before(grammarAccess.getAtomicBAccess().getRightAdditionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAtomicBAccess().getRightAdditionParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AtomicB__RightAssignment_1_2"


    // $ANTLR start "rule__Addition__RightAssignment_1_1"
    // InternalText.g:4648:1: rule__Addition__RightAssignment_1_1 : ( ruleMultiplication ) ;
    public final void rule__Addition__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4652:1: ( ( ruleMultiplication ) )
            // InternalText.g:4653:2: ( ruleMultiplication )
            {
            // InternalText.g:4653:2: ( ruleMultiplication )
            // InternalText.g:4654:3: ruleMultiplication
            {
             before(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__RightAssignment_1_1"


    // $ANTLR start "rule__Multiplication__RightAssignment_1_1"
    // InternalText.g:4663:1: rule__Multiplication__RightAssignment_1_1 : ( ruleBitwise ) ;
    public final void rule__Multiplication__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4667:1: ( ( ruleBitwise ) )
            // InternalText.g:4668:2: ( ruleBitwise )
            {
            // InternalText.g:4668:2: ( ruleBitwise )
            // InternalText.g:4669:3: ruleBitwise
            {
             before(grammarAccess.getMultiplicationAccess().getRightBitwiseParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBitwise();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getRightBitwiseParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__RightAssignment_1_1"


    // $ANTLR start "rule__Bitwise__OpAssignment_1_1"
    // InternalText.g:4678:1: rule__Bitwise__OpAssignment_1_1 : ( ( rule__Bitwise__OpAlternatives_1_1_0 ) ) ;
    public final void rule__Bitwise__OpAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4682:1: ( ( ( rule__Bitwise__OpAlternatives_1_1_0 ) ) )
            // InternalText.g:4683:2: ( ( rule__Bitwise__OpAlternatives_1_1_0 ) )
            {
            // InternalText.g:4683:2: ( ( rule__Bitwise__OpAlternatives_1_1_0 ) )
            // InternalText.g:4684:3: ( rule__Bitwise__OpAlternatives_1_1_0 )
            {
             before(grammarAccess.getBitwiseAccess().getOpAlternatives_1_1_0()); 
            // InternalText.g:4685:3: ( rule__Bitwise__OpAlternatives_1_1_0 )
            // InternalText.g:4685:4: rule__Bitwise__OpAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Bitwise__OpAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getBitwiseAccess().getOpAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__OpAssignment_1_1"


    // $ANTLR start "rule__Bitwise__RightAssignment_1_2"
    // InternalText.g:4693:1: rule__Bitwise__RightAssignment_1_2 : ( ruleUnary ) ;
    public final void rule__Bitwise__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4697:1: ( ( ruleUnary ) )
            // InternalText.g:4698:2: ( ruleUnary )
            {
            // InternalText.g:4698:2: ( ruleUnary )
            // InternalText.g:4699:3: ruleUnary
            {
             before(grammarAccess.getBitwiseAccess().getRightUnaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleUnary();

            state._fsp--;

             after(grammarAccess.getBitwiseAccess().getRightUnaryParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Bitwise__RightAssignment_1_2"


    // $ANTLR start "rule__Unary__ExpressionAssignment_0_2"
    // InternalText.g:4708:1: rule__Unary__ExpressionAssignment_0_2 : ( rulePrimary ) ;
    public final void rule__Unary__ExpressionAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4712:1: ( ( rulePrimary ) )
            // InternalText.g:4713:2: ( rulePrimary )
            {
            // InternalText.g:4713:2: ( rulePrimary )
            // InternalText.g:4714:3: rulePrimary
            {
             before(grammarAccess.getUnaryAccess().getExpressionPrimaryParserRuleCall_0_2_0()); 
            pushFollow(FOLLOW_2);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getUnaryAccess().getExpressionPrimaryParserRuleCall_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary__ExpressionAssignment_0_2"


    // $ANTLR start "rule__Primary__ExpressionAssignment_2_2"
    // InternalText.g:4723:1: rule__Primary__ExpressionAssignment_2_2 : ( ruleExpression ) ;
    public final void rule__Primary__ExpressionAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalText.g:4727:1: ( ( ruleExpression ) )
            // InternalText.g:4728:2: ( ruleExpression )
            {
            // InternalText.g:4728:2: ( ruleExpression )
            // InternalText.g:4729:3: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionExpressionParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getPrimaryAccess().getExpressionExpressionParserRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__ExpressionAssignment_2_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000020000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000004400000010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000004400000012L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000002010000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0008214400000010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000004420000010L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000000FFC4000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000020080000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0008014400000010L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000000003F000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000C00000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0007000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0007000000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000F80L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000F82L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0030000000000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000020000000000L});

}