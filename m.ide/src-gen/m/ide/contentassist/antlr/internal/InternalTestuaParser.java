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
import m.services.TestuaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTestuaParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_REAL", "RULE_WHITE_SPACE", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'>'", "'<'", "'='", "'!='", "'>='", "'<='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'{'", "'}'", "':'", "','", "'['", "']'", "'entitate'", "'bakoitzarengatik'", "'hurrengo'", "'etiketekin'", "'baldin'", "'eta'", "'edo'", "'bestela'", "'('", "')'", "'.'", "'||'", "'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'~'", "'++'", "'--'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__10=10;
    public static final int T__54=54;
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


        public InternalTestuaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTestuaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTestuaParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTestua.g"; }


    	private TestuaGrammarAccess grammarAccess;

    	public void setGrammarAccess(TestuaGrammarAccess grammarAccess) {
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
    // InternalTestua.g:53:1: entryRuleGame : ruleGame EOF ;
    public final void entryRuleGame() throws RecognitionException {
        try {
            // InternalTestua.g:54:1: ( ruleGame EOF )
            // InternalTestua.g:55:1: ruleGame EOF
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
    // InternalTestua.g:62:1: ruleGame : ( ( rule__Game__Alternatives )* ) ;
    public final void ruleGame() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:66:2: ( ( ( rule__Game__Alternatives )* ) )
            // InternalTestua.g:67:2: ( ( rule__Game__Alternatives )* )
            {
            // InternalTestua.g:67:2: ( ( rule__Game__Alternatives )* )
            // InternalTestua.g:68:3: ( rule__Game__Alternatives )*
            {
             before(grammarAccess.getGameAccess().getAlternatives()); 
            // InternalTestua.g:69:3: ( rule__Game__Alternatives )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTestua.g:69:4: rule__Game__Alternatives
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
    // InternalTestua.g:78:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // InternalTestua.g:79:1: ( ruleEntity EOF )
            // InternalTestua.g:80:1: ruleEntity EOF
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
    // InternalTestua.g:87:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:91:2: ( ( ( rule__Entity__Group__0 ) ) )
            // InternalTestua.g:92:2: ( ( rule__Entity__Group__0 ) )
            {
            // InternalTestua.g:92:2: ( ( rule__Entity__Group__0 ) )
            // InternalTestua.g:93:3: ( rule__Entity__Group__0 )
            {
             before(grammarAccess.getEntityAccess().getGroup()); 
            // InternalTestua.g:94:3: ( rule__Entity__Group__0 )
            // InternalTestua.g:94:4: rule__Entity__Group__0
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
    // InternalTestua.g:103:1: entryRuleComponent : ruleComponent EOF ;
    public final void entryRuleComponent() throws RecognitionException {
        try {
            // InternalTestua.g:104:1: ( ruleComponent EOF )
            // InternalTestua.g:105:1: ruleComponent EOF
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
    // InternalTestua.g:112:1: ruleComponent : ( ( rule__Component__Group__0 ) ) ;
    public final void ruleComponent() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:116:2: ( ( ( rule__Component__Group__0 ) ) )
            // InternalTestua.g:117:2: ( ( rule__Component__Group__0 ) )
            {
            // InternalTestua.g:117:2: ( ( rule__Component__Group__0 ) )
            // InternalTestua.g:118:3: ( rule__Component__Group__0 )
            {
             before(grammarAccess.getComponentAccess().getGroup()); 
            // InternalTestua.g:119:3: ( rule__Component__Group__0 )
            // InternalTestua.g:119:4: rule__Component__Group__0
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
    // InternalTestua.g:128:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalTestua.g:129:1: ( ruleValue EOF )
            // InternalTestua.g:130:1: ruleValue EOF
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
    // InternalTestua.g:137:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:141:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalTestua.g:142:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalTestua.g:142:2: ( ( rule__Value__Alternatives ) )
            // InternalTestua.g:143:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalTestua.g:144:3: ( rule__Value__Alternatives )
            // InternalTestua.g:144:4: rule__Value__Alternatives
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
    // InternalTestua.g:153:1: entryRuleVector : ruleVector EOF ;
    public final void entryRuleVector() throws RecognitionException {
        try {
            // InternalTestua.g:154:1: ( ruleVector EOF )
            // InternalTestua.g:155:1: ruleVector EOF
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
    // InternalTestua.g:162:1: ruleVector : ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) ) ;
    public final void ruleVector() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:166:2: ( ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) ) )
            // InternalTestua.g:167:2: ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) )
            {
            // InternalTestua.g:167:2: ( ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* ) )
            // InternalTestua.g:168:3: ( ( rule__Vector__NumbersAssignment ) ) ( ( rule__Vector__NumbersAssignment )* )
            {
            // InternalTestua.g:168:3: ( ( rule__Vector__NumbersAssignment ) )
            // InternalTestua.g:169:4: ( rule__Vector__NumbersAssignment )
            {
             before(grammarAccess.getVectorAccess().getNumbersAssignment()); 
            // InternalTestua.g:170:4: ( rule__Vector__NumbersAssignment )
            // InternalTestua.g:170:5: rule__Vector__NumbersAssignment
            {
            pushFollow(FOLLOW_4);
            rule__Vector__NumbersAssignment();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getNumbersAssignment()); 

            }

            // InternalTestua.g:173:3: ( ( rule__Vector__NumbersAssignment )* )
            // InternalTestua.g:174:4: ( rule__Vector__NumbersAssignment )*
            {
             before(grammarAccess.getVectorAccess().getNumbersAssignment()); 
            // InternalTestua.g:175:4: ( rule__Vector__NumbersAssignment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_REAL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTestua.g:175:5: rule__Vector__NumbersAssignment
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
    // InternalTestua.g:185:1: entryRuleWord : ruleWord EOF ;
    public final void entryRuleWord() throws RecognitionException {
        try {
            // InternalTestua.g:186:1: ( ruleWord EOF )
            // InternalTestua.g:187:1: ruleWord EOF
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
    // InternalTestua.g:194:1: ruleWord : ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) ) ;
    public final void ruleWord() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:198:2: ( ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) ) )
            // InternalTestua.g:199:2: ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) )
            {
            // InternalTestua.g:199:2: ( ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* ) )
            // InternalTestua.g:200:3: ( ( rule__Word__ValuesAssignment ) ) ( ( rule__Word__ValuesAssignment )* )
            {
            // InternalTestua.g:200:3: ( ( rule__Word__ValuesAssignment ) )
            // InternalTestua.g:201:4: ( rule__Word__ValuesAssignment )
            {
             before(grammarAccess.getWordAccess().getValuesAssignment()); 
            // InternalTestua.g:202:4: ( rule__Word__ValuesAssignment )
            // InternalTestua.g:202:5: rule__Word__ValuesAssignment
            {
            pushFollow(FOLLOW_3);
            rule__Word__ValuesAssignment();

            state._fsp--;


            }

             after(grammarAccess.getWordAccess().getValuesAssignment()); 

            }

            // InternalTestua.g:205:3: ( ( rule__Word__ValuesAssignment )* )
            // InternalTestua.g:206:4: ( rule__Word__ValuesAssignment )*
            {
             before(grammarAccess.getWordAccess().getValuesAssignment()); 
            // InternalTestua.g:207:4: ( rule__Word__ValuesAssignment )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalTestua.g:207:5: rule__Word__ValuesAssignment
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
    // InternalTestua.g:217:1: entryRuleSystem : ruleSystem EOF ;
    public final void entryRuleSystem() throws RecognitionException {
        try {
            // InternalTestua.g:218:1: ( ruleSystem EOF )
            // InternalTestua.g:219:1: ruleSystem EOF
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
    // InternalTestua.g:226:1: ruleSystem : ( ( rule__System__Group__0 ) ) ;
    public final void ruleSystem() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:230:2: ( ( ( rule__System__Group__0 ) ) )
            // InternalTestua.g:231:2: ( ( rule__System__Group__0 ) )
            {
            // InternalTestua.g:231:2: ( ( rule__System__Group__0 ) )
            // InternalTestua.g:232:3: ( rule__System__Group__0 )
            {
             before(grammarAccess.getSystemAccess().getGroup()); 
            // InternalTestua.g:233:3: ( rule__System__Group__0 )
            // InternalTestua.g:233:4: rule__System__Group__0
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
    // InternalTestua.g:242:1: entryRuleCommand : ruleCommand EOF ;
    public final void entryRuleCommand() throws RecognitionException {
        try {
            // InternalTestua.g:243:1: ( ruleCommand EOF )
            // InternalTestua.g:244:1: ruleCommand EOF
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
    // InternalTestua.g:251:1: ruleCommand : ( ( rule__Command__Alternatives ) ) ;
    public final void ruleCommand() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:255:2: ( ( ( rule__Command__Alternatives ) ) )
            // InternalTestua.g:256:2: ( ( rule__Command__Alternatives ) )
            {
            // InternalTestua.g:256:2: ( ( rule__Command__Alternatives ) )
            // InternalTestua.g:257:3: ( rule__Command__Alternatives )
            {
             before(grammarAccess.getCommandAccess().getAlternatives()); 
            // InternalTestua.g:258:3: ( rule__Command__Alternatives )
            // InternalTestua.g:258:4: rule__Command__Alternatives
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
    // InternalTestua.g:267:1: entryRuleLoop : ruleLoop EOF ;
    public final void entryRuleLoop() throws RecognitionException {
        try {
            // InternalTestua.g:268:1: ( ruleLoop EOF )
            // InternalTestua.g:269:1: ruleLoop EOF
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
    // InternalTestua.g:276:1: ruleLoop : ( ( rule__Loop__Group__0 ) ) ;
    public final void ruleLoop() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:280:2: ( ( ( rule__Loop__Group__0 ) ) )
            // InternalTestua.g:281:2: ( ( rule__Loop__Group__0 ) )
            {
            // InternalTestua.g:281:2: ( ( rule__Loop__Group__0 ) )
            // InternalTestua.g:282:3: ( rule__Loop__Group__0 )
            {
             before(grammarAccess.getLoopAccess().getGroup()); 
            // InternalTestua.g:283:3: ( rule__Loop__Group__0 )
            // InternalTestua.g:283:4: rule__Loop__Group__0
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
    // InternalTestua.g:292:1: entryRuleBranch : ruleBranch EOF ;
    public final void entryRuleBranch() throws RecognitionException {
        try {
            // InternalTestua.g:293:1: ( ruleBranch EOF )
            // InternalTestua.g:294:1: ruleBranch EOF
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
    // InternalTestua.g:301:1: ruleBranch : ( ( rule__Branch__Group__0 ) ) ;
    public final void ruleBranch() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:305:2: ( ( ( rule__Branch__Group__0 ) ) )
            // InternalTestua.g:306:2: ( ( rule__Branch__Group__0 ) )
            {
            // InternalTestua.g:306:2: ( ( rule__Branch__Group__0 ) )
            // InternalTestua.g:307:3: ( rule__Branch__Group__0 )
            {
             before(grammarAccess.getBranchAccess().getGroup()); 
            // InternalTestua.g:308:3: ( rule__Branch__Group__0 )
            // InternalTestua.g:308:4: rule__Branch__Group__0
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
    // InternalTestua.g:317:1: entryRuleCondition : ruleCondition EOF ;
    public final void entryRuleCondition() throws RecognitionException {
        try {
            // InternalTestua.g:318:1: ( ruleCondition EOF )
            // InternalTestua.g:319:1: ruleCondition EOF
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
    // InternalTestua.g:326:1: ruleCondition : ( ( rule__Condition__Group__0 ) ) ;
    public final void ruleCondition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:330:2: ( ( ( rule__Condition__Group__0 ) ) )
            // InternalTestua.g:331:2: ( ( rule__Condition__Group__0 ) )
            {
            // InternalTestua.g:331:2: ( ( rule__Condition__Group__0 ) )
            // InternalTestua.g:332:3: ( rule__Condition__Group__0 )
            {
             before(grammarAccess.getConditionAccess().getGroup()); 
            // InternalTestua.g:333:3: ( rule__Condition__Group__0 )
            // InternalTestua.g:333:4: rule__Condition__Group__0
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
    // InternalTestua.g:342:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalTestua.g:343:1: ( ruleAssignment EOF )
            // InternalTestua.g:344:1: ruleAssignment EOF
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
    // InternalTestua.g:351:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:355:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalTestua.g:356:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalTestua.g:356:2: ( ( rule__Assignment__Group__0 ) )
            // InternalTestua.g:357:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalTestua.g:358:3: ( rule__Assignment__Group__0 )
            // InternalTestua.g:358:4: rule__Assignment__Group__0
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
    // InternalTestua.g:367:1: entryRuleCall : ruleCall EOF ;
    public final void entryRuleCall() throws RecognitionException {
        try {
            // InternalTestua.g:368:1: ( ruleCall EOF )
            // InternalTestua.g:369:1: ruleCall EOF
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
    // InternalTestua.g:376:1: ruleCall : ( ( rule__Call__Group__0 ) ) ;
    public final void ruleCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:380:2: ( ( ( rule__Call__Group__0 ) ) )
            // InternalTestua.g:381:2: ( ( rule__Call__Group__0 ) )
            {
            // InternalTestua.g:381:2: ( ( rule__Call__Group__0 ) )
            // InternalTestua.g:382:3: ( rule__Call__Group__0 )
            {
             before(grammarAccess.getCallAccess().getGroup()); 
            // InternalTestua.g:383:3: ( rule__Call__Group__0 )
            // InternalTestua.g:383:4: rule__Call__Group__0
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
    // InternalTestua.g:392:1: entryRuleAccess : ruleAccess EOF ;
    public final void entryRuleAccess() throws RecognitionException {
        try {
            // InternalTestua.g:393:1: ( ruleAccess EOF )
            // InternalTestua.g:394:1: ruleAccess EOF
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
    // InternalTestua.g:401:1: ruleAccess : ( ( rule__Access__Group__0 ) ) ;
    public final void ruleAccess() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:405:2: ( ( ( rule__Access__Group__0 ) ) )
            // InternalTestua.g:406:2: ( ( rule__Access__Group__0 ) )
            {
            // InternalTestua.g:406:2: ( ( rule__Access__Group__0 ) )
            // InternalTestua.g:407:3: ( rule__Access__Group__0 )
            {
             before(grammarAccess.getAccessAccess().getGroup()); 
            // InternalTestua.g:408:3: ( rule__Access__Group__0 )
            // InternalTestua.g:408:4: rule__Access__Group__0
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
    // InternalTestua.g:417:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalTestua.g:418:1: ( ruleExpression EOF )
            // InternalTestua.g:419:1: ruleExpression EOF
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
    // InternalTestua.g:426:1: ruleExpression : ( ( rule__Expression__Group__0 ) ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:430:2: ( ( ( rule__Expression__Group__0 ) ) )
            // InternalTestua.g:431:2: ( ( rule__Expression__Group__0 ) )
            {
            // InternalTestua.g:431:2: ( ( rule__Expression__Group__0 ) )
            // InternalTestua.g:432:3: ( rule__Expression__Group__0 )
            {
             before(grammarAccess.getExpressionAccess().getGroup()); 
            // InternalTestua.g:433:3: ( rule__Expression__Group__0 )
            // InternalTestua.g:433:4: rule__Expression__Group__0
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
    // InternalTestua.g:442:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // InternalTestua.g:443:1: ( ruleAnd EOF )
            // InternalTestua.g:444:1: ruleAnd EOF
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
    // InternalTestua.g:451:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:455:2: ( ( ( rule__And__Group__0 ) ) )
            // InternalTestua.g:456:2: ( ( rule__And__Group__0 ) )
            {
            // InternalTestua.g:456:2: ( ( rule__And__Group__0 ) )
            // InternalTestua.g:457:3: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // InternalTestua.g:458:3: ( rule__And__Group__0 )
            // InternalTestua.g:458:4: rule__And__Group__0
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
    // InternalTestua.g:467:1: entryRulePrimaryB : rulePrimaryB EOF ;
    public final void entryRulePrimaryB() throws RecognitionException {
        try {
            // InternalTestua.g:468:1: ( rulePrimaryB EOF )
            // InternalTestua.g:469:1: rulePrimaryB EOF
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
    // InternalTestua.g:476:1: rulePrimaryB : ( ( rule__PrimaryB__Alternatives ) ) ;
    public final void rulePrimaryB() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:480:2: ( ( ( rule__PrimaryB__Alternatives ) ) )
            // InternalTestua.g:481:2: ( ( rule__PrimaryB__Alternatives ) )
            {
            // InternalTestua.g:481:2: ( ( rule__PrimaryB__Alternatives ) )
            // InternalTestua.g:482:3: ( rule__PrimaryB__Alternatives )
            {
             before(grammarAccess.getPrimaryBAccess().getAlternatives()); 
            // InternalTestua.g:483:3: ( rule__PrimaryB__Alternatives )
            // InternalTestua.g:483:4: rule__PrimaryB__Alternatives
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
    // InternalTestua.g:492:1: entryRuleAtomicB : ruleAtomicB EOF ;
    public final void entryRuleAtomicB() throws RecognitionException {
        try {
            // InternalTestua.g:493:1: ( ruleAtomicB EOF )
            // InternalTestua.g:494:1: ruleAtomicB EOF
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
    // InternalTestua.g:501:1: ruleAtomicB : ( ( rule__AtomicB__Group__0 ) ) ;
    public final void ruleAtomicB() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:505:2: ( ( ( rule__AtomicB__Group__0 ) ) )
            // InternalTestua.g:506:2: ( ( rule__AtomicB__Group__0 ) )
            {
            // InternalTestua.g:506:2: ( ( rule__AtomicB__Group__0 ) )
            // InternalTestua.g:507:3: ( rule__AtomicB__Group__0 )
            {
             before(grammarAccess.getAtomicBAccess().getGroup()); 
            // InternalTestua.g:508:3: ( rule__AtomicB__Group__0 )
            // InternalTestua.g:508:4: rule__AtomicB__Group__0
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
    // InternalTestua.g:517:1: entryRuleAddition : ruleAddition EOF ;
    public final void entryRuleAddition() throws RecognitionException {
        try {
            // InternalTestua.g:518:1: ( ruleAddition EOF )
            // InternalTestua.g:519:1: ruleAddition EOF
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
    // InternalTestua.g:526:1: ruleAddition : ( ( rule__Addition__Group__0 ) ) ;
    public final void ruleAddition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:530:2: ( ( ( rule__Addition__Group__0 ) ) )
            // InternalTestua.g:531:2: ( ( rule__Addition__Group__0 ) )
            {
            // InternalTestua.g:531:2: ( ( rule__Addition__Group__0 ) )
            // InternalTestua.g:532:3: ( rule__Addition__Group__0 )
            {
             before(grammarAccess.getAdditionAccess().getGroup()); 
            // InternalTestua.g:533:3: ( rule__Addition__Group__0 )
            // InternalTestua.g:533:4: rule__Addition__Group__0
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
    // InternalTestua.g:542:1: entryRuleMultiplication : ruleMultiplication EOF ;
    public final void entryRuleMultiplication() throws RecognitionException {
        try {
            // InternalTestua.g:543:1: ( ruleMultiplication EOF )
            // InternalTestua.g:544:1: ruleMultiplication EOF
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
    // InternalTestua.g:551:1: ruleMultiplication : ( ( rule__Multiplication__Group__0 ) ) ;
    public final void ruleMultiplication() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:555:2: ( ( ( rule__Multiplication__Group__0 ) ) )
            // InternalTestua.g:556:2: ( ( rule__Multiplication__Group__0 ) )
            {
            // InternalTestua.g:556:2: ( ( rule__Multiplication__Group__0 ) )
            // InternalTestua.g:557:3: ( rule__Multiplication__Group__0 )
            {
             before(grammarAccess.getMultiplicationAccess().getGroup()); 
            // InternalTestua.g:558:3: ( rule__Multiplication__Group__0 )
            // InternalTestua.g:558:4: rule__Multiplication__Group__0
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
    // InternalTestua.g:567:1: entryRuleBitwise : ruleBitwise EOF ;
    public final void entryRuleBitwise() throws RecognitionException {
        try {
            // InternalTestua.g:568:1: ( ruleBitwise EOF )
            // InternalTestua.g:569:1: ruleBitwise EOF
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
    // InternalTestua.g:576:1: ruleBitwise : ( ( rule__Bitwise__Group__0 ) ) ;
    public final void ruleBitwise() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:580:2: ( ( ( rule__Bitwise__Group__0 ) ) )
            // InternalTestua.g:581:2: ( ( rule__Bitwise__Group__0 ) )
            {
            // InternalTestua.g:581:2: ( ( rule__Bitwise__Group__0 ) )
            // InternalTestua.g:582:3: ( rule__Bitwise__Group__0 )
            {
             before(grammarAccess.getBitwiseAccess().getGroup()); 
            // InternalTestua.g:583:3: ( rule__Bitwise__Group__0 )
            // InternalTestua.g:583:4: rule__Bitwise__Group__0
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
    // InternalTestua.g:592:1: entryRuleUnary : ruleUnary EOF ;
    public final void entryRuleUnary() throws RecognitionException {
        try {
            // InternalTestua.g:593:1: ( ruleUnary EOF )
            // InternalTestua.g:594:1: ruleUnary EOF
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
    // InternalTestua.g:601:1: ruleUnary : ( ( rule__Unary__Alternatives ) ) ;
    public final void ruleUnary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:605:2: ( ( ( rule__Unary__Alternatives ) ) )
            // InternalTestua.g:606:2: ( ( rule__Unary__Alternatives ) )
            {
            // InternalTestua.g:606:2: ( ( rule__Unary__Alternatives ) )
            // InternalTestua.g:607:3: ( rule__Unary__Alternatives )
            {
             before(grammarAccess.getUnaryAccess().getAlternatives()); 
            // InternalTestua.g:608:3: ( rule__Unary__Alternatives )
            // InternalTestua.g:608:4: rule__Unary__Alternatives
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
    // InternalTestua.g:617:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // InternalTestua.g:618:1: ( rulePrimary EOF )
            // InternalTestua.g:619:1: rulePrimary EOF
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
    // InternalTestua.g:626:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:630:2: ( ( ( rule__Primary__Alternatives ) ) )
            // InternalTestua.g:631:2: ( ( rule__Primary__Alternatives ) )
            {
            // InternalTestua.g:631:2: ( ( rule__Primary__Alternatives ) )
            // InternalTestua.g:632:3: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // InternalTestua.g:633:3: ( rule__Primary__Alternatives )
            // InternalTestua.g:633:4: rule__Primary__Alternatives
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
    // InternalTestua.g:642:1: ruleRelationType : ( ( rule__RelationType__Alternatives ) ) ;
    public final void ruleRelationType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:646:1: ( ( ( rule__RelationType__Alternatives ) ) )
            // InternalTestua.g:647:2: ( ( rule__RelationType__Alternatives ) )
            {
            // InternalTestua.g:647:2: ( ( rule__RelationType__Alternatives ) )
            // InternalTestua.g:648:3: ( rule__RelationType__Alternatives )
            {
             before(grammarAccess.getRelationTypeAccess().getAlternatives()); 
            // InternalTestua.g:649:3: ( rule__RelationType__Alternatives )
            // InternalTestua.g:649:4: rule__RelationType__Alternatives
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
    // InternalTestua.g:658:1: ruleAssignmentType : ( ( rule__AssignmentType__Alternatives ) ) ;
    public final void ruleAssignmentType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:662:1: ( ( ( rule__AssignmentType__Alternatives ) ) )
            // InternalTestua.g:663:2: ( ( rule__AssignmentType__Alternatives ) )
            {
            // InternalTestua.g:663:2: ( ( rule__AssignmentType__Alternatives ) )
            // InternalTestua.g:664:3: ( rule__AssignmentType__Alternatives )
            {
             before(grammarAccess.getAssignmentTypeAccess().getAlternatives()); 
            // InternalTestua.g:665:3: ( rule__AssignmentType__Alternatives )
            // InternalTestua.g:665:4: rule__AssignmentType__Alternatives
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
    // InternalTestua.g:673:1: rule__Game__Alternatives : ( ( ( rule__Game__EntitiesAssignment_0 ) ) | ( ( rule__Game__SystemsAssignment_1 ) ) );
    public final void rule__Game__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:677:1: ( ( ( rule__Game__EntitiesAssignment_0 ) ) | ( ( rule__Game__SystemsAssignment_1 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==30) ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2==34||LA4_2==38) ) {
                        alt4=2;
                    }
                    else if ( (LA4_2==RULE_ID) ) {
                        int LA4_5 = input.LA(4);

                        if ( (LA4_5==RULE_ID||LA4_5==28) ) {
                            alt4=1;
                        }
                        else if ( (LA4_5==14||(LA4_5>=18 && LA4_5<=27)||LA4_5==42||LA4_5==44) ) {
                            alt4=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 5, input);

                            throw nvae;
                        }
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
                    // InternalTestua.g:678:2: ( ( rule__Game__EntitiesAssignment_0 ) )
                    {
                    // InternalTestua.g:678:2: ( ( rule__Game__EntitiesAssignment_0 ) )
                    // InternalTestua.g:679:3: ( rule__Game__EntitiesAssignment_0 )
                    {
                     before(grammarAccess.getGameAccess().getEntitiesAssignment_0()); 
                    // InternalTestua.g:680:3: ( rule__Game__EntitiesAssignment_0 )
                    // InternalTestua.g:680:4: rule__Game__EntitiesAssignment_0
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
                    // InternalTestua.g:684:2: ( ( rule__Game__SystemsAssignment_1 ) )
                    {
                    // InternalTestua.g:684:2: ( ( rule__Game__SystemsAssignment_1 ) )
                    // InternalTestua.g:685:3: ( rule__Game__SystemsAssignment_1 )
                    {
                     before(grammarAccess.getGameAccess().getSystemsAssignment_1()); 
                    // InternalTestua.g:686:3: ( rule__Game__SystemsAssignment_1 )
                    // InternalTestua.g:686:4: rule__Game__SystemsAssignment_1
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
    // InternalTestua.g:694:1: rule__Value__Alternatives : ( ( ruleVector ) | ( ruleWord ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:698:1: ( ( ruleVector ) | ( ruleWord ) )
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
                    // InternalTestua.g:699:2: ( ruleVector )
                    {
                    // InternalTestua.g:699:2: ( ruleVector )
                    // InternalTestua.g:700:3: ruleVector
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
                    // InternalTestua.g:705:2: ( ruleWord )
                    {
                    // InternalTestua.g:705:2: ( ruleWord )
                    // InternalTestua.g:706:3: ruleWord
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
    // InternalTestua.g:715:1: rule__Command__Alternatives : ( ( ruleLoop ) | ( ruleBranch ) | ( ruleAssignment ) | ( ruleCall ) );
    public final void rule__Command__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:719:1: ( ( ruleLoop ) | ( ruleBranch ) | ( ruleAssignment ) | ( ruleCall ) )
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

                if ( (LA6_3==14||(LA6_3>=18 && LA6_3<=27)||LA6_3==44) ) {
                    alt6=3;
                }
                else if ( (LA6_3==42) ) {
                    alt6=4;
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
                    // InternalTestua.g:720:2: ( ruleLoop )
                    {
                    // InternalTestua.g:720:2: ( ruleLoop )
                    // InternalTestua.g:721:3: ruleLoop
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
                    // InternalTestua.g:726:2: ( ruleBranch )
                    {
                    // InternalTestua.g:726:2: ( ruleBranch )
                    // InternalTestua.g:727:3: ruleBranch
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
                    // InternalTestua.g:732:2: ( ruleAssignment )
                    {
                    // InternalTestua.g:732:2: ( ruleAssignment )
                    // InternalTestua.g:733:3: ruleAssignment
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
                    // InternalTestua.g:738:2: ( ruleCall )
                    {
                    // InternalTestua.g:738:2: ( ruleCall )
                    // InternalTestua.g:739:3: ruleCall
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
    // InternalTestua.g:748:1: rule__PrimaryB__Alternatives : ( ( ruleAtomicB ) | ( ( rule__PrimaryB__Group_1__0 ) ) );
    public final void rule__PrimaryB__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:752:1: ( ( ruleAtomicB ) | ( ( rule__PrimaryB__Group_1__0 ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID||LA7_0==42||LA7_0==53) ) {
                alt7=1;
            }
            else if ( (LA7_0==47) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalTestua.g:753:2: ( ruleAtomicB )
                    {
                    // InternalTestua.g:753:2: ( ruleAtomicB )
                    // InternalTestua.g:754:3: ruleAtomicB
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
                    // InternalTestua.g:759:2: ( ( rule__PrimaryB__Group_1__0 ) )
                    {
                    // InternalTestua.g:759:2: ( ( rule__PrimaryB__Group_1__0 ) )
                    // InternalTestua.g:760:3: ( rule__PrimaryB__Group_1__0 )
                    {
                     before(grammarAccess.getPrimaryBAccess().getGroup_1()); 
                    // InternalTestua.g:761:3: ( rule__PrimaryB__Group_1__0 )
                    // InternalTestua.g:761:4: rule__PrimaryB__Group_1__0
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
    // InternalTestua.g:769:1: rule__Addition__Alternatives_1_0 : ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) );
    public final void rule__Addition__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:773:1: ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==48) ) {
                alt8=1;
            }
            else if ( (LA8_0==49) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalTestua.g:774:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    {
                    // InternalTestua.g:774:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    // InternalTestua.g:775:3: ( rule__Addition__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_0()); 
                    // InternalTestua.g:776:3: ( rule__Addition__Group_1_0_0__0 )
                    // InternalTestua.g:776:4: rule__Addition__Group_1_0_0__0
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
                    // InternalTestua.g:780:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    {
                    // InternalTestua.g:780:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    // InternalTestua.g:781:3: ( rule__Addition__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_1()); 
                    // InternalTestua.g:782:3: ( rule__Addition__Group_1_0_1__0 )
                    // InternalTestua.g:782:4: rule__Addition__Group_1_0_1__0
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
    // InternalTestua.g:790:1: rule__Multiplication__Alternatives_1_0 : ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) | ( ( rule__Multiplication__Group_1_0_2__0 ) ) );
    public final void rule__Multiplication__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:794:1: ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) | ( ( rule__Multiplication__Group_1_0_2__0 ) ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt9=1;
                }
                break;
            case 51:
                {
                alt9=2;
                }
                break;
            case 52:
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
                    // InternalTestua.g:795:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    {
                    // InternalTestua.g:795:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    // InternalTestua.g:796:3: ( rule__Multiplication__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); 
                    // InternalTestua.g:797:3: ( rule__Multiplication__Group_1_0_0__0 )
                    // InternalTestua.g:797:4: rule__Multiplication__Group_1_0_0__0
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
                    // InternalTestua.g:801:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    {
                    // InternalTestua.g:801:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    // InternalTestua.g:802:3: ( rule__Multiplication__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); 
                    // InternalTestua.g:803:3: ( rule__Multiplication__Group_1_0_1__0 )
                    // InternalTestua.g:803:4: rule__Multiplication__Group_1_0_1__0
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
                    // InternalTestua.g:807:2: ( ( rule__Multiplication__Group_1_0_2__0 ) )
                    {
                    // InternalTestua.g:807:2: ( ( rule__Multiplication__Group_1_0_2__0 ) )
                    // InternalTestua.g:808:3: ( rule__Multiplication__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_2()); 
                    // InternalTestua.g:809:3: ( rule__Multiplication__Group_1_0_2__0 )
                    // InternalTestua.g:809:4: rule__Multiplication__Group_1_0_2__0
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
    // InternalTestua.g:817:1: rule__Bitwise__OpAlternatives_1_1_0 : ( ( '&' ) | ( '|' ) | ( '^' ) | ( '<<' ) | ( '>>' ) );
    public final void rule__Bitwise__OpAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:821:1: ( ( '&' ) | ( '|' ) | ( '^' ) | ( '<<' ) | ( '>>' ) )
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
                    // InternalTestua.g:822:2: ( '&' )
                    {
                    // InternalTestua.g:822:2: ( '&' )
                    // InternalTestua.g:823:3: '&'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0()); 
                    match(input,7,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:828:2: ( '|' )
                    {
                    // InternalTestua.g:828:2: ( '|' )
                    // InternalTestua.g:829:3: '|'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1()); 
                    match(input,8,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalTestua.g:834:2: ( '^' )
                    {
                    // InternalTestua.g:834:2: ( '^' )
                    // InternalTestua.g:835:3: '^'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2()); 
                    match(input,9,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalTestua.g:840:2: ( '<<' )
                    {
                    // InternalTestua.g:840:2: ( '<<' )
                    // InternalTestua.g:841:3: '<<'
                    {
                     before(grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3()); 
                    match(input,10,FOLLOW_2); 
                     after(grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalTestua.g:846:2: ( '>>' )
                    {
                    // InternalTestua.g:846:2: ( '>>' )
                    // InternalTestua.g:847:3: '>>'
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
    // InternalTestua.g:856:1: rule__Unary__Alternatives : ( ( ( rule__Unary__Group_0__0 ) ) | ( ( rule__Unary__Group_1__0 ) ) );
    public final void rule__Unary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:860:1: ( ( ( rule__Unary__Group_0__0 ) ) | ( ( rule__Unary__Group_1__0 ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==53) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_ID||LA11_0==42) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalTestua.g:861:2: ( ( rule__Unary__Group_0__0 ) )
                    {
                    // InternalTestua.g:861:2: ( ( rule__Unary__Group_0__0 ) )
                    // InternalTestua.g:862:3: ( rule__Unary__Group_0__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_0()); 
                    // InternalTestua.g:863:3: ( rule__Unary__Group_0__0 )
                    // InternalTestua.g:863:4: rule__Unary__Group_0__0
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
                    // InternalTestua.g:867:2: ( ( rule__Unary__Group_1__0 ) )
                    {
                    // InternalTestua.g:867:2: ( ( rule__Unary__Group_1__0 ) )
                    // InternalTestua.g:868:3: ( rule__Unary__Group_1__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1()); 
                    // InternalTestua.g:869:3: ( rule__Unary__Group_1__0 )
                    // InternalTestua.g:869:4: rule__Unary__Group_1__0
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
    // InternalTestua.g:877:1: rule__Unary__Alternatives_1_1 : ( ( ( rule__Unary__Group_1_1_0__0 ) ) | ( ( rule__Unary__Group_1_1_1__0 ) ) );
    public final void rule__Unary__Alternatives_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:881:1: ( ( ( rule__Unary__Group_1_1_0__0 ) ) | ( ( rule__Unary__Group_1_1_1__0 ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==54) ) {
                alt12=1;
            }
            else if ( (LA12_0==55) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalTestua.g:882:2: ( ( rule__Unary__Group_1_1_0__0 ) )
                    {
                    // InternalTestua.g:882:2: ( ( rule__Unary__Group_1_1_0__0 ) )
                    // InternalTestua.g:883:3: ( rule__Unary__Group_1_1_0__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1_1_0()); 
                    // InternalTestua.g:884:3: ( rule__Unary__Group_1_1_0__0 )
                    // InternalTestua.g:884:4: rule__Unary__Group_1_1_0__0
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
                    // InternalTestua.g:888:2: ( ( rule__Unary__Group_1_1_1__0 ) )
                    {
                    // InternalTestua.g:888:2: ( ( rule__Unary__Group_1_1_1__0 ) )
                    // InternalTestua.g:889:3: ( rule__Unary__Group_1_1_1__0 )
                    {
                     before(grammarAccess.getUnaryAccess().getGroup_1_1_1()); 
                    // InternalTestua.g:890:3: ( rule__Unary__Group_1_1_1__0 )
                    // InternalTestua.g:890:4: rule__Unary__Group_1_1_1__0
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
    // InternalTestua.g:898:1: rule__Primary__Alternatives : ( ( ruleAccess ) | ( ruleCall ) | ( ( rule__Primary__Group_2__0 ) ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:902:1: ( ( ruleAccess ) | ( ruleCall ) | ( ( rule__Primary__Group_2__0 ) ) )
            int alt13=3;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==EOF||LA13_1==RULE_ID||(LA13_1>=7 && LA13_1<=17)||(LA13_1>=28 && LA13_1<=29)||LA13_1==31||LA13_1==34||LA13_1==38||(LA13_1>=43 && LA13_1<=46)||(LA13_1>=48 && LA13_1<=52)||(LA13_1>=54 && LA13_1<=55)) ) {
                    alt13=1;
                }
                else if ( (LA13_1==42) ) {
                    alt13=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA13_0==42) ) {
                alt13=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalTestua.g:903:2: ( ruleAccess )
                    {
                    // InternalTestua.g:903:2: ( ruleAccess )
                    // InternalTestua.g:904:3: ruleAccess
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
                    // InternalTestua.g:909:2: ( ruleCall )
                    {
                    // InternalTestua.g:909:2: ( ruleCall )
                    // InternalTestua.g:910:3: ruleCall
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
                    // InternalTestua.g:915:2: ( ( rule__Primary__Group_2__0 ) )
                    {
                    // InternalTestua.g:915:2: ( ( rule__Primary__Group_2__0 ) )
                    // InternalTestua.g:916:3: ( rule__Primary__Group_2__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_2()); 
                    // InternalTestua.g:917:3: ( rule__Primary__Group_2__0 )
                    // InternalTestua.g:917:4: rule__Primary__Group_2__0
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
    // InternalTestua.g:925:1: rule__RelationType__Alternatives : ( ( ( '>' ) ) | ( ( '<' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '<=' ) ) );
    public final void rule__RelationType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:929:1: ( ( ( '>' ) ) | ( ( '<' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '<=' ) ) )
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
                    // InternalTestua.g:930:2: ( ( '>' ) )
                    {
                    // InternalTestua.g:930:2: ( ( '>' ) )
                    // InternalTestua.g:931:3: ( '>' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0()); 
                    // InternalTestua.g:932:3: ( '>' )
                    // InternalTestua.g:932:4: '>'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:936:2: ( ( '<' ) )
                    {
                    // InternalTestua.g:936:2: ( ( '<' ) )
                    // InternalTestua.g:937:3: ( '<' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1()); 
                    // InternalTestua.g:938:3: ( '<' )
                    // InternalTestua.g:938:4: '<'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalTestua.g:942:2: ( ( '=' ) )
                    {
                    // InternalTestua.g:942:2: ( ( '=' ) )
                    // InternalTestua.g:943:3: ( '=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2()); 
                    // InternalTestua.g:944:3: ( '=' )
                    // InternalTestua.g:944:4: '='
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalTestua.g:948:2: ( ( '!=' ) )
                    {
                    // InternalTestua.g:948:2: ( ( '!=' ) )
                    // InternalTestua.g:949:3: ( '!=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3()); 
                    // InternalTestua.g:950:3: ( '!=' )
                    // InternalTestua.g:950:4: '!='
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalTestua.g:954:2: ( ( '>=' ) )
                    {
                    // InternalTestua.g:954:2: ( ( '>=' ) )
                    // InternalTestua.g:955:3: ( '>=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4()); 
                    // InternalTestua.g:956:3: ( '>=' )
                    // InternalTestua.g:956:4: '>='
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalTestua.g:960:2: ( ( '<=' ) )
                    {
                    // InternalTestua.g:960:2: ( ( '<=' ) )
                    // InternalTestua.g:961:3: ( '<=' )
                    {
                     before(grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5()); 
                    // InternalTestua.g:962:3: ( '<=' )
                    // InternalTestua.g:962:4: '<='
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
    // InternalTestua.g:970:1: rule__AssignmentType__Alternatives : ( ( ( '=' ) ) | ( ( '+=' ) ) | ( ( '-=' ) ) | ( ( '*=' ) ) | ( ( '/=' ) ) | ( ( '%=' ) ) | ( ( '&=' ) ) | ( ( '|=' ) ) | ( ( '^=' ) ) | ( ( '<<=' ) ) | ( ( '>>=' ) ) );
    public final void rule__AssignmentType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:974:1: ( ( ( '=' ) ) | ( ( '+=' ) ) | ( ( '-=' ) ) | ( ( '*=' ) ) | ( ( '/=' ) ) | ( ( '%=' ) ) | ( ( '&=' ) ) | ( ( '|=' ) ) | ( ( '^=' ) ) | ( ( '<<=' ) ) | ( ( '>>=' ) ) )
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
                    // InternalTestua.g:975:2: ( ( '=' ) )
                    {
                    // InternalTestua.g:975:2: ( ( '=' ) )
                    // InternalTestua.g:976:3: ( '=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0()); 
                    // InternalTestua.g:977:3: ( '=' )
                    // InternalTestua.g:977:4: '='
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:981:2: ( ( '+=' ) )
                    {
                    // InternalTestua.g:981:2: ( ( '+=' ) )
                    // InternalTestua.g:982:3: ( '+=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1()); 
                    // InternalTestua.g:983:3: ( '+=' )
                    // InternalTestua.g:983:4: '+='
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalTestua.g:987:2: ( ( '-=' ) )
                    {
                    // InternalTestua.g:987:2: ( ( '-=' ) )
                    // InternalTestua.g:988:3: ( '-=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2()); 
                    // InternalTestua.g:989:3: ( '-=' )
                    // InternalTestua.g:989:4: '-='
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalTestua.g:993:2: ( ( '*=' ) )
                    {
                    // InternalTestua.g:993:2: ( ( '*=' ) )
                    // InternalTestua.g:994:3: ( '*=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3()); 
                    // InternalTestua.g:995:3: ( '*=' )
                    // InternalTestua.g:995:4: '*='
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalTestua.g:999:2: ( ( '/=' ) )
                    {
                    // InternalTestua.g:999:2: ( ( '/=' ) )
                    // InternalTestua.g:1000:3: ( '/=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4()); 
                    // InternalTestua.g:1001:3: ( '/=' )
                    // InternalTestua.g:1001:4: '/='
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalTestua.g:1005:2: ( ( '%=' ) )
                    {
                    // InternalTestua.g:1005:2: ( ( '%=' ) )
                    // InternalTestua.g:1006:3: ( '%=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5()); 
                    // InternalTestua.g:1007:3: ( '%=' )
                    // InternalTestua.g:1007:4: '%='
                    {
                    match(input,22,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalTestua.g:1011:2: ( ( '&=' ) )
                    {
                    // InternalTestua.g:1011:2: ( ( '&=' ) )
                    // InternalTestua.g:1012:3: ( '&=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6()); 
                    // InternalTestua.g:1013:3: ( '&=' )
                    // InternalTestua.g:1013:4: '&='
                    {
                    match(input,23,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalTestua.g:1017:2: ( ( '|=' ) )
                    {
                    // InternalTestua.g:1017:2: ( ( '|=' ) )
                    // InternalTestua.g:1018:3: ( '|=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7()); 
                    // InternalTestua.g:1019:3: ( '|=' )
                    // InternalTestua.g:1019:4: '|='
                    {
                    match(input,24,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalTestua.g:1023:2: ( ( '^=' ) )
                    {
                    // InternalTestua.g:1023:2: ( ( '^=' ) )
                    // InternalTestua.g:1024:3: ( '^=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8()); 
                    // InternalTestua.g:1025:3: ( '^=' )
                    // InternalTestua.g:1025:4: '^='
                    {
                    match(input,25,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalTestua.g:1029:2: ( ( '<<=' ) )
                    {
                    // InternalTestua.g:1029:2: ( ( '<<=' ) )
                    // InternalTestua.g:1030:3: ( '<<=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9()); 
                    // InternalTestua.g:1031:3: ( '<<=' )
                    // InternalTestua.g:1031:4: '<<='
                    {
                    match(input,26,FOLLOW_2); 

                    }

                     after(grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalTestua.g:1035:2: ( ( '>>=' ) )
                    {
                    // InternalTestua.g:1035:2: ( ( '>>=' ) )
                    // InternalTestua.g:1036:3: ( '>>=' )
                    {
                     before(grammarAccess.getAssignmentTypeAccess().getShiftRightEnumLiteralDeclaration_10()); 
                    // InternalTestua.g:1037:3: ( '>>=' )
                    // InternalTestua.g:1037:4: '>>='
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
    // InternalTestua.g:1045:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1049:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // InternalTestua.g:1050:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
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
    // InternalTestua.g:1057:1: rule__Entity__Group__0__Impl : ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1061:1: ( ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) ) )
            // InternalTestua.g:1062:1: ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) )
            {
            // InternalTestua.g:1062:1: ( ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* ) )
            // InternalTestua.g:1063:2: ( ( rule__Entity__NameAssignment_0 ) ) ( ( rule__Entity__NameAssignment_0 )* )
            {
            // InternalTestua.g:1063:2: ( ( rule__Entity__NameAssignment_0 ) )
            // InternalTestua.g:1064:3: ( rule__Entity__NameAssignment_0 )
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_0()); 
            // InternalTestua.g:1065:3: ( rule__Entity__NameAssignment_0 )
            // InternalTestua.g:1065:4: rule__Entity__NameAssignment_0
            {
            pushFollow(FOLLOW_3);
            rule__Entity__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getNameAssignment_0()); 

            }

            // InternalTestua.g:1068:2: ( ( rule__Entity__NameAssignment_0 )* )
            // InternalTestua.g:1069:3: ( rule__Entity__NameAssignment_0 )*
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_0()); 
            // InternalTestua.g:1070:3: ( rule__Entity__NameAssignment_0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalTestua.g:1070:4: rule__Entity__NameAssignment_0
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
    // InternalTestua.g:1079:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1083:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // InternalTestua.g:1084:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
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
    // InternalTestua.g:1091:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__Group_1__0 )? ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1095:1: ( ( ( rule__Entity__Group_1__0 )? ) )
            // InternalTestua.g:1096:1: ( ( rule__Entity__Group_1__0 )? )
            {
            // InternalTestua.g:1096:1: ( ( rule__Entity__Group_1__0 )? )
            // InternalTestua.g:1097:2: ( rule__Entity__Group_1__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_1()); 
            // InternalTestua.g:1098:2: ( rule__Entity__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==30) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalTestua.g:1098:3: rule__Entity__Group_1__0
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
    // InternalTestua.g:1106:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1110:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // InternalTestua.g:1111:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
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
    // InternalTestua.g:1118:1: rule__Entity__Group__2__Impl : ( '{' ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1122:1: ( ( '{' ) )
            // InternalTestua.g:1123:1: ( '{' )
            {
            // InternalTestua.g:1123:1: ( '{' )
            // InternalTestua.g:1124:2: '{'
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
    // InternalTestua.g:1133:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1137:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // InternalTestua.g:1138:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
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
    // InternalTestua.g:1145:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__Group_3__0 )? ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1149:1: ( ( ( rule__Entity__Group_3__0 )? ) )
            // InternalTestua.g:1150:1: ( ( rule__Entity__Group_3__0 )? )
            {
            // InternalTestua.g:1150:1: ( ( rule__Entity__Group_3__0 )? )
            // InternalTestua.g:1151:2: ( rule__Entity__Group_3__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_3()); 
            // InternalTestua.g:1152:2: ( rule__Entity__Group_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalTestua.g:1152:3: rule__Entity__Group_3__0
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
    // InternalTestua.g:1160:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1164:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // InternalTestua.g:1165:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
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
    // InternalTestua.g:1172:1: rule__Entity__Group__4__Impl : ( '}' ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1176:1: ( ( '}' ) )
            // InternalTestua.g:1177:1: ( '}' )
            {
            // InternalTestua.g:1177:1: ( '}' )
            // InternalTestua.g:1178:2: '}'
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
    // InternalTestua.g:1187:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1191:1: ( rule__Entity__Group__5__Impl )
            // InternalTestua.g:1192:2: rule__Entity__Group__5__Impl
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
    // InternalTestua.g:1198:1: rule__Entity__Group__5__Impl : ( ( rule__Entity__Group_5__0 )? ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1202:1: ( ( ( rule__Entity__Group_5__0 )? ) )
            // InternalTestua.g:1203:1: ( ( rule__Entity__Group_5__0 )? )
            {
            // InternalTestua.g:1203:1: ( ( rule__Entity__Group_5__0 )? )
            // InternalTestua.g:1204:2: ( rule__Entity__Group_5__0 )?
            {
             before(grammarAccess.getEntityAccess().getGroup_5()); 
            // InternalTestua.g:1205:2: ( rule__Entity__Group_5__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==32) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalTestua.g:1205:3: rule__Entity__Group_5__0
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
    // InternalTestua.g:1214:1: rule__Entity__Group_1__0 : rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1 ;
    public final void rule__Entity__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1218:1: ( rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1 )
            // InternalTestua.g:1219:2: rule__Entity__Group_1__0__Impl rule__Entity__Group_1__1
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
    // InternalTestua.g:1226:1: rule__Entity__Group_1__0__Impl : ( ':' ) ;
    public final void rule__Entity__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1230:1: ( ( ':' ) )
            // InternalTestua.g:1231:1: ( ':' )
            {
            // InternalTestua.g:1231:1: ( ':' )
            // InternalTestua.g:1232:2: ':'
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
    // InternalTestua.g:1241:1: rule__Entity__Group_1__1 : rule__Entity__Group_1__1__Impl ;
    public final void rule__Entity__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1245:1: ( rule__Entity__Group_1__1__Impl )
            // InternalTestua.g:1246:2: rule__Entity__Group_1__1__Impl
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
    // InternalTestua.g:1252:1: rule__Entity__Group_1__1__Impl : ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) ) ;
    public final void rule__Entity__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1256:1: ( ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) ) )
            // InternalTestua.g:1257:1: ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) )
            {
            // InternalTestua.g:1257:1: ( ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* ) )
            // InternalTestua.g:1258:2: ( ( rule__Entity__BaseAssignment_1_1 ) ) ( ( rule__Entity__BaseAssignment_1_1 )* )
            {
            // InternalTestua.g:1258:2: ( ( rule__Entity__BaseAssignment_1_1 ) )
            // InternalTestua.g:1259:3: ( rule__Entity__BaseAssignment_1_1 )
            {
             before(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 
            // InternalTestua.g:1260:3: ( rule__Entity__BaseAssignment_1_1 )
            // InternalTestua.g:1260:4: rule__Entity__BaseAssignment_1_1
            {
            pushFollow(FOLLOW_3);
            rule__Entity__BaseAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 

            }

            // InternalTestua.g:1263:2: ( ( rule__Entity__BaseAssignment_1_1 )* )
            // InternalTestua.g:1264:3: ( rule__Entity__BaseAssignment_1_1 )*
            {
             before(grammarAccess.getEntityAccess().getBaseAssignment_1_1()); 
            // InternalTestua.g:1265:3: ( rule__Entity__BaseAssignment_1_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalTestua.g:1265:4: rule__Entity__BaseAssignment_1_1
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
    // InternalTestua.g:1275:1: rule__Entity__Group_3__0 : rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 ;
    public final void rule__Entity__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1279:1: ( rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1 )
            // InternalTestua.g:1280:2: rule__Entity__Group_3__0__Impl rule__Entity__Group_3__1
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
    // InternalTestua.g:1287:1: rule__Entity__Group_3__0__Impl : ( ( rule__Entity__ComponentsAssignment_3_0 ) ) ;
    public final void rule__Entity__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1291:1: ( ( ( rule__Entity__ComponentsAssignment_3_0 ) ) )
            // InternalTestua.g:1292:1: ( ( rule__Entity__ComponentsAssignment_3_0 ) )
            {
            // InternalTestua.g:1292:1: ( ( rule__Entity__ComponentsAssignment_3_0 ) )
            // InternalTestua.g:1293:2: ( rule__Entity__ComponentsAssignment_3_0 )
            {
             before(grammarAccess.getEntityAccess().getComponentsAssignment_3_0()); 
            // InternalTestua.g:1294:2: ( rule__Entity__ComponentsAssignment_3_0 )
            // InternalTestua.g:1294:3: rule__Entity__ComponentsAssignment_3_0
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
    // InternalTestua.g:1302:1: rule__Entity__Group_3__1 : rule__Entity__Group_3__1__Impl ;
    public final void rule__Entity__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1306:1: ( rule__Entity__Group_3__1__Impl )
            // InternalTestua.g:1307:2: rule__Entity__Group_3__1__Impl
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
    // InternalTestua.g:1313:1: rule__Entity__Group_3__1__Impl : ( ( rule__Entity__Group_3_1__0 )* ) ;
    public final void rule__Entity__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1317:1: ( ( ( rule__Entity__Group_3_1__0 )* ) )
            // InternalTestua.g:1318:1: ( ( rule__Entity__Group_3_1__0 )* )
            {
            // InternalTestua.g:1318:1: ( ( rule__Entity__Group_3_1__0 )* )
            // InternalTestua.g:1319:2: ( rule__Entity__Group_3_1__0 )*
            {
             before(grammarAccess.getEntityAccess().getGroup_3_1()); 
            // InternalTestua.g:1320:2: ( rule__Entity__Group_3_1__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==31) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalTestua.g:1320:3: rule__Entity__Group_3_1__0
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
    // InternalTestua.g:1329:1: rule__Entity__Group_3_1__0 : rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1 ;
    public final void rule__Entity__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1333:1: ( rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1 )
            // InternalTestua.g:1334:2: rule__Entity__Group_3_1__0__Impl rule__Entity__Group_3_1__1
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
    // InternalTestua.g:1341:1: rule__Entity__Group_3_1__0__Impl : ( ',' ) ;
    public final void rule__Entity__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1345:1: ( ( ',' ) )
            // InternalTestua.g:1346:1: ( ',' )
            {
            // InternalTestua.g:1346:1: ( ',' )
            // InternalTestua.g:1347:2: ','
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
    // InternalTestua.g:1356:1: rule__Entity__Group_3_1__1 : rule__Entity__Group_3_1__1__Impl ;
    public final void rule__Entity__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1360:1: ( rule__Entity__Group_3_1__1__Impl )
            // InternalTestua.g:1361:2: rule__Entity__Group_3_1__1__Impl
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
    // InternalTestua.g:1367:1: rule__Entity__Group_3_1__1__Impl : ( ( rule__Entity__ComponentsAssignment_3_1_1 ) ) ;
    public final void rule__Entity__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1371:1: ( ( ( rule__Entity__ComponentsAssignment_3_1_1 ) ) )
            // InternalTestua.g:1372:1: ( ( rule__Entity__ComponentsAssignment_3_1_1 ) )
            {
            // InternalTestua.g:1372:1: ( ( rule__Entity__ComponentsAssignment_3_1_1 ) )
            // InternalTestua.g:1373:2: ( rule__Entity__ComponentsAssignment_3_1_1 )
            {
             before(grammarAccess.getEntityAccess().getComponentsAssignment_3_1_1()); 
            // InternalTestua.g:1374:2: ( rule__Entity__ComponentsAssignment_3_1_1 )
            // InternalTestua.g:1374:3: rule__Entity__ComponentsAssignment_3_1_1
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
    // InternalTestua.g:1383:1: rule__Entity__Group_5__0 : rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1 ;
    public final void rule__Entity__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1387:1: ( rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1 )
            // InternalTestua.g:1388:2: rule__Entity__Group_5__0__Impl rule__Entity__Group_5__1
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
    // InternalTestua.g:1395:1: rule__Entity__Group_5__0__Impl : ( '[' ) ;
    public final void rule__Entity__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1399:1: ( ( '[' ) )
            // InternalTestua.g:1400:1: ( '[' )
            {
            // InternalTestua.g:1400:1: ( '[' )
            // InternalTestua.g:1401:2: '['
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
    // InternalTestua.g:1410:1: rule__Entity__Group_5__1 : rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2 ;
    public final void rule__Entity__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1414:1: ( rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2 )
            // InternalTestua.g:1415:2: rule__Entity__Group_5__1__Impl rule__Entity__Group_5__2
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
    // InternalTestua.g:1422:1: rule__Entity__Group_5__1__Impl : ( ( rule__Entity__EntitiesAssignment_5_1 )* ) ;
    public final void rule__Entity__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1426:1: ( ( ( rule__Entity__EntitiesAssignment_5_1 )* ) )
            // InternalTestua.g:1427:1: ( ( rule__Entity__EntitiesAssignment_5_1 )* )
            {
            // InternalTestua.g:1427:1: ( ( rule__Entity__EntitiesAssignment_5_1 )* )
            // InternalTestua.g:1428:2: ( rule__Entity__EntitiesAssignment_5_1 )*
            {
             before(grammarAccess.getEntityAccess().getEntitiesAssignment_5_1()); 
            // InternalTestua.g:1429:2: ( rule__Entity__EntitiesAssignment_5_1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalTestua.g:1429:3: rule__Entity__EntitiesAssignment_5_1
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
    // InternalTestua.g:1437:1: rule__Entity__Group_5__2 : rule__Entity__Group_5__2__Impl ;
    public final void rule__Entity__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1441:1: ( rule__Entity__Group_5__2__Impl )
            // InternalTestua.g:1442:2: rule__Entity__Group_5__2__Impl
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
    // InternalTestua.g:1448:1: rule__Entity__Group_5__2__Impl : ( ']' ) ;
    public final void rule__Entity__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1452:1: ( ( ']' ) )
            // InternalTestua.g:1453:1: ( ']' )
            {
            // InternalTestua.g:1453:1: ( ']' )
            // InternalTestua.g:1454:2: ']'
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
    // InternalTestua.g:1464:1: rule__Component__Group__0 : rule__Component__Group__0__Impl rule__Component__Group__1 ;
    public final void rule__Component__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1468:1: ( rule__Component__Group__0__Impl rule__Component__Group__1 )
            // InternalTestua.g:1469:2: rule__Component__Group__0__Impl rule__Component__Group__1
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
    // InternalTestua.g:1476:1: rule__Component__Group__0__Impl : ( ( rule__Component__NameAssignment_0 ) ) ;
    public final void rule__Component__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1480:1: ( ( ( rule__Component__NameAssignment_0 ) ) )
            // InternalTestua.g:1481:1: ( ( rule__Component__NameAssignment_0 ) )
            {
            // InternalTestua.g:1481:1: ( ( rule__Component__NameAssignment_0 ) )
            // InternalTestua.g:1482:2: ( rule__Component__NameAssignment_0 )
            {
             before(grammarAccess.getComponentAccess().getNameAssignment_0()); 
            // InternalTestua.g:1483:2: ( rule__Component__NameAssignment_0 )
            // InternalTestua.g:1483:3: rule__Component__NameAssignment_0
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
    // InternalTestua.g:1491:1: rule__Component__Group__1 : rule__Component__Group__1__Impl ;
    public final void rule__Component__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1495:1: ( rule__Component__Group__1__Impl )
            // InternalTestua.g:1496:2: rule__Component__Group__1__Impl
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
    // InternalTestua.g:1502:1: rule__Component__Group__1__Impl : ( ( rule__Component__ValueAssignment_1 )? ) ;
    public final void rule__Component__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1506:1: ( ( ( rule__Component__ValueAssignment_1 )? ) )
            // InternalTestua.g:1507:1: ( ( rule__Component__ValueAssignment_1 )? )
            {
            // InternalTestua.g:1507:1: ( ( rule__Component__ValueAssignment_1 )? )
            // InternalTestua.g:1508:2: ( rule__Component__ValueAssignment_1 )?
            {
             before(grammarAccess.getComponentAccess().getValueAssignment_1()); 
            // InternalTestua.g:1509:2: ( rule__Component__ValueAssignment_1 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_REAL)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalTestua.g:1509:3: rule__Component__ValueAssignment_1
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
    // InternalTestua.g:1518:1: rule__System__Group__0 : rule__System__Group__0__Impl rule__System__Group__1 ;
    public final void rule__System__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1522:1: ( rule__System__Group__0__Impl rule__System__Group__1 )
            // InternalTestua.g:1523:2: rule__System__Group__0__Impl rule__System__Group__1
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
    // InternalTestua.g:1530:1: rule__System__Group__0__Impl : ( ( rule__System__NameAssignment_0 ) ) ;
    public final void rule__System__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1534:1: ( ( ( rule__System__NameAssignment_0 ) ) )
            // InternalTestua.g:1535:1: ( ( rule__System__NameAssignment_0 ) )
            {
            // InternalTestua.g:1535:1: ( ( rule__System__NameAssignment_0 ) )
            // InternalTestua.g:1536:2: ( rule__System__NameAssignment_0 )
            {
             before(grammarAccess.getSystemAccess().getNameAssignment_0()); 
            // InternalTestua.g:1537:2: ( rule__System__NameAssignment_0 )
            // InternalTestua.g:1537:3: rule__System__NameAssignment_0
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
    // InternalTestua.g:1545:1: rule__System__Group__1 : rule__System__Group__1__Impl rule__System__Group__2 ;
    public final void rule__System__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1549:1: ( rule__System__Group__1__Impl rule__System__Group__2 )
            // InternalTestua.g:1550:2: rule__System__Group__1__Impl rule__System__Group__2
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
    // InternalTestua.g:1557:1: rule__System__Group__1__Impl : ( ':' ) ;
    public final void rule__System__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1561:1: ( ( ':' ) )
            // InternalTestua.g:1562:1: ( ':' )
            {
            // InternalTestua.g:1562:1: ( ':' )
            // InternalTestua.g:1563:2: ':'
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
    // InternalTestua.g:1572:1: rule__System__Group__2 : rule__System__Group__2__Impl ;
    public final void rule__System__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1576:1: ( rule__System__Group__2__Impl )
            // InternalTestua.g:1577:2: rule__System__Group__2__Impl
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
    // InternalTestua.g:1583:1: rule__System__Group__2__Impl : ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) ) ;
    public final void rule__System__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1587:1: ( ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) ) )
            // InternalTestua.g:1588:1: ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) )
            {
            // InternalTestua.g:1588:1: ( ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* ) )
            // InternalTestua.g:1589:2: ( ( rule__System__CommandsAssignment_2 ) ) ( ( rule__System__CommandsAssignment_2 )* )
            {
            // InternalTestua.g:1589:2: ( ( rule__System__CommandsAssignment_2 ) )
            // InternalTestua.g:1590:3: ( rule__System__CommandsAssignment_2 )
            {
             before(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 
            // InternalTestua.g:1591:3: ( rule__System__CommandsAssignment_2 )
            // InternalTestua.g:1591:4: rule__System__CommandsAssignment_2
            {
            pushFollow(FOLLOW_15);
            rule__System__CommandsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 

            }

            // InternalTestua.g:1594:2: ( ( rule__System__CommandsAssignment_2 )* )
            // InternalTestua.g:1595:3: ( rule__System__CommandsAssignment_2 )*
            {
             before(grammarAccess.getSystemAccess().getCommandsAssignment_2()); 
            // InternalTestua.g:1596:3: ( rule__System__CommandsAssignment_2 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID) ) {
                    int LA24_2 = input.LA(2);

                    if ( (LA24_2==14||(LA24_2>=18 && LA24_2<=27)||LA24_2==42||LA24_2==44) ) {
                        alt24=1;
                    }


                }
                else if ( (LA24_0==34||LA24_0==38) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalTestua.g:1596:4: rule__System__CommandsAssignment_2
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
    // InternalTestua.g:1606:1: rule__Loop__Group__0 : rule__Loop__Group__0__Impl rule__Loop__Group__1 ;
    public final void rule__Loop__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1610:1: ( rule__Loop__Group__0__Impl rule__Loop__Group__1 )
            // InternalTestua.g:1611:2: rule__Loop__Group__0__Impl rule__Loop__Group__1
            {
            pushFollow(FOLLOW_8);
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
    // InternalTestua.g:1618:1: rule__Loop__Group__0__Impl : ( 'entitate' ) ;
    public final void rule__Loop__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1622:1: ( ( 'entitate' ) )
            // InternalTestua.g:1623:1: ( 'entitate' )
            {
            // InternalTestua.g:1623:1: ( 'entitate' )
            // InternalTestua.g:1624:2: 'entitate'
            {
             before(grammarAccess.getLoopAccess().getEntitateKeyword_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getEntitateKeyword_0()); 

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
    // InternalTestua.g:1633:1: rule__Loop__Group__1 : rule__Loop__Group__1__Impl rule__Loop__Group__2 ;
    public final void rule__Loop__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1637:1: ( rule__Loop__Group__1__Impl rule__Loop__Group__2 )
            // InternalTestua.g:1638:2: rule__Loop__Group__1__Impl rule__Loop__Group__2
            {
            pushFollow(FOLLOW_16);
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
    // InternalTestua.g:1645:1: rule__Loop__Group__1__Impl : ( ( rule__Loop__EntityAssignment_1 ) ) ;
    public final void rule__Loop__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1649:1: ( ( ( rule__Loop__EntityAssignment_1 ) ) )
            // InternalTestua.g:1650:1: ( ( rule__Loop__EntityAssignment_1 ) )
            {
            // InternalTestua.g:1650:1: ( ( rule__Loop__EntityAssignment_1 ) )
            // InternalTestua.g:1651:2: ( rule__Loop__EntityAssignment_1 )
            {
             before(grammarAccess.getLoopAccess().getEntityAssignment_1()); 
            // InternalTestua.g:1652:2: ( rule__Loop__EntityAssignment_1 )
            // InternalTestua.g:1652:3: rule__Loop__EntityAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Loop__EntityAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getEntityAssignment_1()); 

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
    // InternalTestua.g:1660:1: rule__Loop__Group__2 : rule__Loop__Group__2__Impl rule__Loop__Group__3 ;
    public final void rule__Loop__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1664:1: ( rule__Loop__Group__2__Impl rule__Loop__Group__3 )
            // InternalTestua.g:1665:2: rule__Loop__Group__2__Impl rule__Loop__Group__3
            {
            pushFollow(FOLLOW_17);
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
    // InternalTestua.g:1672:1: rule__Loop__Group__2__Impl : ( 'bakoitzarengatik' ) ;
    public final void rule__Loop__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1676:1: ( ( 'bakoitzarengatik' ) )
            // InternalTestua.g:1677:1: ( 'bakoitzarengatik' )
            {
            // InternalTestua.g:1677:1: ( 'bakoitzarengatik' )
            // InternalTestua.g:1678:2: 'bakoitzarengatik'
            {
             before(grammarAccess.getLoopAccess().getBakoitzarengatikKeyword_2()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getBakoitzarengatikKeyword_2()); 

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
    // InternalTestua.g:1687:1: rule__Loop__Group__3 : rule__Loop__Group__3__Impl rule__Loop__Group__4 ;
    public final void rule__Loop__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1691:1: ( rule__Loop__Group__3__Impl rule__Loop__Group__4 )
            // InternalTestua.g:1692:2: rule__Loop__Group__3__Impl rule__Loop__Group__4
            {
            pushFollow(FOLLOW_17);
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
    // InternalTestua.g:1699:1: rule__Loop__Group__3__Impl : ( ( rule__Loop__Group_3__0 )? ) ;
    public final void rule__Loop__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1703:1: ( ( ( rule__Loop__Group_3__0 )? ) )
            // InternalTestua.g:1704:1: ( ( rule__Loop__Group_3__0 )? )
            {
            // InternalTestua.g:1704:1: ( ( rule__Loop__Group_3__0 )? )
            // InternalTestua.g:1705:2: ( rule__Loop__Group_3__0 )?
            {
             before(grammarAccess.getLoopAccess().getGroup_3()); 
            // InternalTestua.g:1706:2: ( rule__Loop__Group_3__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==36) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalTestua.g:1706:3: rule__Loop__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Loop__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLoopAccess().getGroup_3()); 

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
    // InternalTestua.g:1714:1: rule__Loop__Group__4 : rule__Loop__Group__4__Impl rule__Loop__Group__5 ;
    public final void rule__Loop__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1718:1: ( rule__Loop__Group__4__Impl rule__Loop__Group__5 )
            // InternalTestua.g:1719:2: rule__Loop__Group__4__Impl rule__Loop__Group__5
            {
            pushFollow(FOLLOW_14);
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
    // InternalTestua.g:1726:1: rule__Loop__Group__4__Impl : ( '{' ) ;
    public final void rule__Loop__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1730:1: ( ( '{' ) )
            // InternalTestua.g:1731:1: ( '{' )
            {
            // InternalTestua.g:1731:1: ( '{' )
            // InternalTestua.g:1732:2: '{'
            {
             before(grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_4()); 

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
    // InternalTestua.g:1741:1: rule__Loop__Group__5 : rule__Loop__Group__5__Impl rule__Loop__Group__6 ;
    public final void rule__Loop__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1745:1: ( rule__Loop__Group__5__Impl rule__Loop__Group__6 )
            // InternalTestua.g:1746:2: rule__Loop__Group__5__Impl rule__Loop__Group__6
            {
            pushFollow(FOLLOW_18);
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
    // InternalTestua.g:1753:1: rule__Loop__Group__5__Impl : ( ( ( rule__Loop__CommandsAssignment_5 ) ) ( ( rule__Loop__CommandsAssignment_5 )* ) ) ;
    public final void rule__Loop__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1757:1: ( ( ( ( rule__Loop__CommandsAssignment_5 ) ) ( ( rule__Loop__CommandsAssignment_5 )* ) ) )
            // InternalTestua.g:1758:1: ( ( ( rule__Loop__CommandsAssignment_5 ) ) ( ( rule__Loop__CommandsAssignment_5 )* ) )
            {
            // InternalTestua.g:1758:1: ( ( ( rule__Loop__CommandsAssignment_5 ) ) ( ( rule__Loop__CommandsAssignment_5 )* ) )
            // InternalTestua.g:1759:2: ( ( rule__Loop__CommandsAssignment_5 ) ) ( ( rule__Loop__CommandsAssignment_5 )* )
            {
            // InternalTestua.g:1759:2: ( ( rule__Loop__CommandsAssignment_5 ) )
            // InternalTestua.g:1760:3: ( rule__Loop__CommandsAssignment_5 )
            {
             before(grammarAccess.getLoopAccess().getCommandsAssignment_5()); 
            // InternalTestua.g:1761:3: ( rule__Loop__CommandsAssignment_5 )
            // InternalTestua.g:1761:4: rule__Loop__CommandsAssignment_5
            {
            pushFollow(FOLLOW_15);
            rule__Loop__CommandsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getCommandsAssignment_5()); 

            }

            // InternalTestua.g:1764:2: ( ( rule__Loop__CommandsAssignment_5 )* )
            // InternalTestua.g:1765:3: ( rule__Loop__CommandsAssignment_5 )*
            {
             before(grammarAccess.getLoopAccess().getCommandsAssignment_5()); 
            // InternalTestua.g:1766:3: ( rule__Loop__CommandsAssignment_5 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==34||LA26_0==38) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalTestua.g:1766:4: rule__Loop__CommandsAssignment_5
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__Loop__CommandsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getLoopAccess().getCommandsAssignment_5()); 

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
    // $ANTLR end "rule__Loop__Group__5__Impl"


    // $ANTLR start "rule__Loop__Group__6"
    // InternalTestua.g:1775:1: rule__Loop__Group__6 : rule__Loop__Group__6__Impl ;
    public final void rule__Loop__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1779:1: ( rule__Loop__Group__6__Impl )
            // InternalTestua.g:1780:2: rule__Loop__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Loop__Group__6__Impl();

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
    // InternalTestua.g:1786:1: rule__Loop__Group__6__Impl : ( '}' ) ;
    public final void rule__Loop__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1790:1: ( ( '}' ) )
            // InternalTestua.g:1791:1: ( '}' )
            {
            // InternalTestua.g:1791:1: ( '}' )
            // InternalTestua.g:1792:2: '}'
            {
             before(grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_6()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_6()); 

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


    // $ANTLR start "rule__Loop__Group_3__0"
    // InternalTestua.g:1802:1: rule__Loop__Group_3__0 : rule__Loop__Group_3__0__Impl rule__Loop__Group_3__1 ;
    public final void rule__Loop__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1806:1: ( rule__Loop__Group_3__0__Impl rule__Loop__Group_3__1 )
            // InternalTestua.g:1807:2: rule__Loop__Group_3__0__Impl rule__Loop__Group_3__1
            {
            pushFollow(FOLLOW_19);
            rule__Loop__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group_3__1();

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
    // $ANTLR end "rule__Loop__Group_3__0"


    // $ANTLR start "rule__Loop__Group_3__0__Impl"
    // InternalTestua.g:1814:1: rule__Loop__Group_3__0__Impl : ( 'hurrengo' ) ;
    public final void rule__Loop__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1818:1: ( ( 'hurrengo' ) )
            // InternalTestua.g:1819:1: ( 'hurrengo' )
            {
            // InternalTestua.g:1819:1: ( 'hurrengo' )
            // InternalTestua.g:1820:2: 'hurrengo'
            {
             before(grammarAccess.getLoopAccess().getHurrengoKeyword_3_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getHurrengoKeyword_3_0()); 

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
    // $ANTLR end "rule__Loop__Group_3__0__Impl"


    // $ANTLR start "rule__Loop__Group_3__1"
    // InternalTestua.g:1829:1: rule__Loop__Group_3__1 : rule__Loop__Group_3__1__Impl rule__Loop__Group_3__2 ;
    public final void rule__Loop__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1833:1: ( rule__Loop__Group_3__1__Impl rule__Loop__Group_3__2 )
            // InternalTestua.g:1834:2: rule__Loop__Group_3__1__Impl rule__Loop__Group_3__2
            {
            pushFollow(FOLLOW_13);
            rule__Loop__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group_3__2();

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
    // $ANTLR end "rule__Loop__Group_3__1"


    // $ANTLR start "rule__Loop__Group_3__1__Impl"
    // InternalTestua.g:1841:1: rule__Loop__Group_3__1__Impl : ( 'etiketekin' ) ;
    public final void rule__Loop__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1845:1: ( ( 'etiketekin' ) )
            // InternalTestua.g:1846:1: ( 'etiketekin' )
            {
            // InternalTestua.g:1846:1: ( 'etiketekin' )
            // InternalTestua.g:1847:2: 'etiketekin'
            {
             before(grammarAccess.getLoopAccess().getEtiketekinKeyword_3_1()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getEtiketekinKeyword_3_1()); 

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
    // $ANTLR end "rule__Loop__Group_3__1__Impl"


    // $ANTLR start "rule__Loop__Group_3__2"
    // InternalTestua.g:1856:1: rule__Loop__Group_3__2 : rule__Loop__Group_3__2__Impl rule__Loop__Group_3__3 ;
    public final void rule__Loop__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1860:1: ( rule__Loop__Group_3__2__Impl rule__Loop__Group_3__3 )
            // InternalTestua.g:1861:2: rule__Loop__Group_3__2__Impl rule__Loop__Group_3__3
            {
            pushFollow(FOLLOW_8);
            rule__Loop__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Loop__Group_3__3();

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
    // $ANTLR end "rule__Loop__Group_3__2"


    // $ANTLR start "rule__Loop__Group_3__2__Impl"
    // InternalTestua.g:1868:1: rule__Loop__Group_3__2__Impl : ( ':' ) ;
    public final void rule__Loop__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1872:1: ( ( ':' ) )
            // InternalTestua.g:1873:1: ( ':' )
            {
            // InternalTestua.g:1873:1: ( ':' )
            // InternalTestua.g:1874:2: ':'
            {
             before(grammarAccess.getLoopAccess().getColonKeyword_3_2()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getColonKeyword_3_2()); 

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
    // $ANTLR end "rule__Loop__Group_3__2__Impl"


    // $ANTLR start "rule__Loop__Group_3__3"
    // InternalTestua.g:1883:1: rule__Loop__Group_3__3 : rule__Loop__Group_3__3__Impl ;
    public final void rule__Loop__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1887:1: ( rule__Loop__Group_3__3__Impl )
            // InternalTestua.g:1888:2: rule__Loop__Group_3__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Loop__Group_3__3__Impl();

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
    // $ANTLR end "rule__Loop__Group_3__3"


    // $ANTLR start "rule__Loop__Group_3__3__Impl"
    // InternalTestua.g:1894:1: rule__Loop__Group_3__3__Impl : ( ( ( rule__Loop__ConstraintsAssignment_3_3 ) ) ( ( rule__Loop__ConstraintsAssignment_3_3 )* ) ) ;
    public final void rule__Loop__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1898:1: ( ( ( ( rule__Loop__ConstraintsAssignment_3_3 ) ) ( ( rule__Loop__ConstraintsAssignment_3_3 )* ) ) )
            // InternalTestua.g:1899:1: ( ( ( rule__Loop__ConstraintsAssignment_3_3 ) ) ( ( rule__Loop__ConstraintsAssignment_3_3 )* ) )
            {
            // InternalTestua.g:1899:1: ( ( ( rule__Loop__ConstraintsAssignment_3_3 ) ) ( ( rule__Loop__ConstraintsAssignment_3_3 )* ) )
            // InternalTestua.g:1900:2: ( ( rule__Loop__ConstraintsAssignment_3_3 ) ) ( ( rule__Loop__ConstraintsAssignment_3_3 )* )
            {
            // InternalTestua.g:1900:2: ( ( rule__Loop__ConstraintsAssignment_3_3 ) )
            // InternalTestua.g:1901:3: ( rule__Loop__ConstraintsAssignment_3_3 )
            {
             before(grammarAccess.getLoopAccess().getConstraintsAssignment_3_3()); 
            // InternalTestua.g:1902:3: ( rule__Loop__ConstraintsAssignment_3_3 )
            // InternalTestua.g:1902:4: rule__Loop__ConstraintsAssignment_3_3
            {
            pushFollow(FOLLOW_3);
            rule__Loop__ConstraintsAssignment_3_3();

            state._fsp--;


            }

             after(grammarAccess.getLoopAccess().getConstraintsAssignment_3_3()); 

            }

            // InternalTestua.g:1905:2: ( ( rule__Loop__ConstraintsAssignment_3_3 )* )
            // InternalTestua.g:1906:3: ( rule__Loop__ConstraintsAssignment_3_3 )*
            {
             before(grammarAccess.getLoopAccess().getConstraintsAssignment_3_3()); 
            // InternalTestua.g:1907:3: ( rule__Loop__ConstraintsAssignment_3_3 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalTestua.g:1907:4: rule__Loop__ConstraintsAssignment_3_3
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Loop__ConstraintsAssignment_3_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getLoopAccess().getConstraintsAssignment_3_3()); 

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
    // $ANTLR end "rule__Loop__Group_3__3__Impl"


    // $ANTLR start "rule__Branch__Group__0"
    // InternalTestua.g:1917:1: rule__Branch__Group__0 : rule__Branch__Group__0__Impl rule__Branch__Group__1 ;
    public final void rule__Branch__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1921:1: ( rule__Branch__Group__0__Impl rule__Branch__Group__1 )
            // InternalTestua.g:1922:2: rule__Branch__Group__0__Impl rule__Branch__Group__1
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
    // InternalTestua.g:1929:1: rule__Branch__Group__0__Impl : ( 'baldin' ) ;
    public final void rule__Branch__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1933:1: ( ( 'baldin' ) )
            // InternalTestua.g:1934:1: ( 'baldin' )
            {
            // InternalTestua.g:1934:1: ( 'baldin' )
            // InternalTestua.g:1935:2: 'baldin'
            {
             before(grammarAccess.getBranchAccess().getBaldinKeyword_0()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getBaldinKeyword_0()); 

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
    // InternalTestua.g:1944:1: rule__Branch__Group__1 : rule__Branch__Group__1__Impl rule__Branch__Group__2 ;
    public final void rule__Branch__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1948:1: ( rule__Branch__Group__1__Impl rule__Branch__Group__2 )
            // InternalTestua.g:1949:2: rule__Branch__Group__1__Impl rule__Branch__Group__2
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
    // InternalTestua.g:1956:1: rule__Branch__Group__1__Impl : ( 'eta' ) ;
    public final void rule__Branch__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1960:1: ( ( 'eta' ) )
            // InternalTestua.g:1961:1: ( 'eta' )
            {
            // InternalTestua.g:1961:1: ( 'eta' )
            // InternalTestua.g:1962:2: 'eta'
            {
             before(grammarAccess.getBranchAccess().getEtaKeyword_1()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getEtaKeyword_1()); 

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
    // InternalTestua.g:1971:1: rule__Branch__Group__2 : rule__Branch__Group__2__Impl rule__Branch__Group__3 ;
    public final void rule__Branch__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1975:1: ( rule__Branch__Group__2__Impl rule__Branch__Group__3 )
            // InternalTestua.g:1976:2: rule__Branch__Group__2__Impl rule__Branch__Group__3
            {
            pushFollow(FOLLOW_22);
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
    // InternalTestua.g:1983:1: rule__Branch__Group__2__Impl : ( ( rule__Branch__IfAssignment_2 ) ) ;
    public final void rule__Branch__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:1987:1: ( ( ( rule__Branch__IfAssignment_2 ) ) )
            // InternalTestua.g:1988:1: ( ( rule__Branch__IfAssignment_2 ) )
            {
            // InternalTestua.g:1988:1: ( ( rule__Branch__IfAssignment_2 ) )
            // InternalTestua.g:1989:2: ( rule__Branch__IfAssignment_2 )
            {
             before(grammarAccess.getBranchAccess().getIfAssignment_2()); 
            // InternalTestua.g:1990:2: ( rule__Branch__IfAssignment_2 )
            // InternalTestua.g:1990:3: rule__Branch__IfAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Branch__IfAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getIfAssignment_2()); 

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
    // InternalTestua.g:1998:1: rule__Branch__Group__3 : rule__Branch__Group__3__Impl rule__Branch__Group__4 ;
    public final void rule__Branch__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2002:1: ( rule__Branch__Group__3__Impl rule__Branch__Group__4 )
            // InternalTestua.g:2003:2: rule__Branch__Group__3__Impl rule__Branch__Group__4
            {
            pushFollow(FOLLOW_22);
            rule__Branch__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group__4();

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
    // InternalTestua.g:2010:1: rule__Branch__Group__3__Impl : ( ( rule__Branch__Group_3__0 )* ) ;
    public final void rule__Branch__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2014:1: ( ( ( rule__Branch__Group_3__0 )* ) )
            // InternalTestua.g:2015:1: ( ( rule__Branch__Group_3__0 )* )
            {
            // InternalTestua.g:2015:1: ( ( rule__Branch__Group_3__0 )* )
            // InternalTestua.g:2016:2: ( rule__Branch__Group_3__0 )*
            {
             before(grammarAccess.getBranchAccess().getGroup_3()); 
            // InternalTestua.g:2017:2: ( rule__Branch__Group_3__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==40) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalTestua.g:2017:3: rule__Branch__Group_3__0
            	    {
            	    pushFollow(FOLLOW_23);
            	    rule__Branch__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

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


    // $ANTLR start "rule__Branch__Group__4"
    // InternalTestua.g:2025:1: rule__Branch__Group__4 : rule__Branch__Group__4__Impl ;
    public final void rule__Branch__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2029:1: ( rule__Branch__Group__4__Impl )
            // InternalTestua.g:2030:2: rule__Branch__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group__4__Impl();

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
    // $ANTLR end "rule__Branch__Group__4"


    // $ANTLR start "rule__Branch__Group__4__Impl"
    // InternalTestua.g:2036:1: rule__Branch__Group__4__Impl : ( ( rule__Branch__Group_4__0 )? ) ;
    public final void rule__Branch__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2040:1: ( ( ( rule__Branch__Group_4__0 )? ) )
            // InternalTestua.g:2041:1: ( ( rule__Branch__Group_4__0 )? )
            {
            // InternalTestua.g:2041:1: ( ( rule__Branch__Group_4__0 )? )
            // InternalTestua.g:2042:2: ( rule__Branch__Group_4__0 )?
            {
             before(grammarAccess.getBranchAccess().getGroup_4()); 
            // InternalTestua.g:2043:2: ( rule__Branch__Group_4__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==41) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalTestua.g:2043:3: rule__Branch__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Branch__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getBranchAccess().getGroup_4()); 

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
    // $ANTLR end "rule__Branch__Group__4__Impl"


    // $ANTLR start "rule__Branch__Group_3__0"
    // InternalTestua.g:2052:1: rule__Branch__Group_3__0 : rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1 ;
    public final void rule__Branch__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2056:1: ( rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1 )
            // InternalTestua.g:2057:2: rule__Branch__Group_3__0__Impl rule__Branch__Group_3__1
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2064:1: rule__Branch__Group_3__0__Impl : ( 'edo' ) ;
    public final void rule__Branch__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2068:1: ( ( 'edo' ) )
            // InternalTestua.g:2069:1: ( 'edo' )
            {
            // InternalTestua.g:2069:1: ( 'edo' )
            // InternalTestua.g:2070:2: 'edo'
            {
             before(grammarAccess.getBranchAccess().getEdoKeyword_3_0()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getEdoKeyword_3_0()); 

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
    // InternalTestua.g:2079:1: rule__Branch__Group_3__1 : rule__Branch__Group_3__1__Impl ;
    public final void rule__Branch__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2083:1: ( rule__Branch__Group_3__1__Impl )
            // InternalTestua.g:2084:2: rule__Branch__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group_3__1__Impl();

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
    // InternalTestua.g:2090:1: rule__Branch__Group_3__1__Impl : ( ( rule__Branch__ElseIfsAssignment_3_1 ) ) ;
    public final void rule__Branch__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2094:1: ( ( ( rule__Branch__ElseIfsAssignment_3_1 ) ) )
            // InternalTestua.g:2095:1: ( ( rule__Branch__ElseIfsAssignment_3_1 ) )
            {
            // InternalTestua.g:2095:1: ( ( rule__Branch__ElseIfsAssignment_3_1 ) )
            // InternalTestua.g:2096:2: ( rule__Branch__ElseIfsAssignment_3_1 )
            {
             before(grammarAccess.getBranchAccess().getElseIfsAssignment_3_1()); 
            // InternalTestua.g:2097:2: ( rule__Branch__ElseIfsAssignment_3_1 )
            // InternalTestua.g:2097:3: rule__Branch__ElseIfsAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Branch__ElseIfsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getElseIfsAssignment_3_1()); 

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


    // $ANTLR start "rule__Branch__Group_4__0"
    // InternalTestua.g:2106:1: rule__Branch__Group_4__0 : rule__Branch__Group_4__0__Impl rule__Branch__Group_4__1 ;
    public final void rule__Branch__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2110:1: ( rule__Branch__Group_4__0__Impl rule__Branch__Group_4__1 )
            // InternalTestua.g:2111:2: rule__Branch__Group_4__0__Impl rule__Branch__Group_4__1
            {
            pushFollow(FOLLOW_24);
            rule__Branch__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_4__1();

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
    // $ANTLR end "rule__Branch__Group_4__0"


    // $ANTLR start "rule__Branch__Group_4__0__Impl"
    // InternalTestua.g:2118:1: rule__Branch__Group_4__0__Impl : ( 'bestela' ) ;
    public final void rule__Branch__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2122:1: ( ( 'bestela' ) )
            // InternalTestua.g:2123:1: ( 'bestela' )
            {
            // InternalTestua.g:2123:1: ( 'bestela' )
            // InternalTestua.g:2124:2: 'bestela'
            {
             before(grammarAccess.getBranchAccess().getBestelaKeyword_4_0()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getBestelaKeyword_4_0()); 

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
    // $ANTLR end "rule__Branch__Group_4__0__Impl"


    // $ANTLR start "rule__Branch__Group_4__1"
    // InternalTestua.g:2133:1: rule__Branch__Group_4__1 : rule__Branch__Group_4__1__Impl rule__Branch__Group_4__2 ;
    public final void rule__Branch__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2137:1: ( rule__Branch__Group_4__1__Impl rule__Branch__Group_4__2 )
            // InternalTestua.g:2138:2: rule__Branch__Group_4__1__Impl rule__Branch__Group_4__2
            {
            pushFollow(FOLLOW_14);
            rule__Branch__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_4__2();

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
    // $ANTLR end "rule__Branch__Group_4__1"


    // $ANTLR start "rule__Branch__Group_4__1__Impl"
    // InternalTestua.g:2145:1: rule__Branch__Group_4__1__Impl : ( '{' ) ;
    public final void rule__Branch__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2149:1: ( ( '{' ) )
            // InternalTestua.g:2150:1: ( '{' )
            {
            // InternalTestua.g:2150:1: ( '{' )
            // InternalTestua.g:2151:2: '{'
            {
             before(grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_4_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_4_1()); 

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
    // $ANTLR end "rule__Branch__Group_4__1__Impl"


    // $ANTLR start "rule__Branch__Group_4__2"
    // InternalTestua.g:2160:1: rule__Branch__Group_4__2 : rule__Branch__Group_4__2__Impl rule__Branch__Group_4__3 ;
    public final void rule__Branch__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2164:1: ( rule__Branch__Group_4__2__Impl rule__Branch__Group_4__3 )
            // InternalTestua.g:2165:2: rule__Branch__Group_4__2__Impl rule__Branch__Group_4__3
            {
            pushFollow(FOLLOW_18);
            rule__Branch__Group_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Branch__Group_4__3();

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
    // $ANTLR end "rule__Branch__Group_4__2"


    // $ANTLR start "rule__Branch__Group_4__2__Impl"
    // InternalTestua.g:2172:1: rule__Branch__Group_4__2__Impl : ( ( ( rule__Branch__CommandsAssignment_4_2 ) ) ( ( rule__Branch__CommandsAssignment_4_2 )* ) ) ;
    public final void rule__Branch__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2176:1: ( ( ( ( rule__Branch__CommandsAssignment_4_2 ) ) ( ( rule__Branch__CommandsAssignment_4_2 )* ) ) )
            // InternalTestua.g:2177:1: ( ( ( rule__Branch__CommandsAssignment_4_2 ) ) ( ( rule__Branch__CommandsAssignment_4_2 )* ) )
            {
            // InternalTestua.g:2177:1: ( ( ( rule__Branch__CommandsAssignment_4_2 ) ) ( ( rule__Branch__CommandsAssignment_4_2 )* ) )
            // InternalTestua.g:2178:2: ( ( rule__Branch__CommandsAssignment_4_2 ) ) ( ( rule__Branch__CommandsAssignment_4_2 )* )
            {
            // InternalTestua.g:2178:2: ( ( rule__Branch__CommandsAssignment_4_2 ) )
            // InternalTestua.g:2179:3: ( rule__Branch__CommandsAssignment_4_2 )
            {
             before(grammarAccess.getBranchAccess().getCommandsAssignment_4_2()); 
            // InternalTestua.g:2180:3: ( rule__Branch__CommandsAssignment_4_2 )
            // InternalTestua.g:2180:4: rule__Branch__CommandsAssignment_4_2
            {
            pushFollow(FOLLOW_15);
            rule__Branch__CommandsAssignment_4_2();

            state._fsp--;


            }

             after(grammarAccess.getBranchAccess().getCommandsAssignment_4_2()); 

            }

            // InternalTestua.g:2183:2: ( ( rule__Branch__CommandsAssignment_4_2 )* )
            // InternalTestua.g:2184:3: ( rule__Branch__CommandsAssignment_4_2 )*
            {
             before(grammarAccess.getBranchAccess().getCommandsAssignment_4_2()); 
            // InternalTestua.g:2185:3: ( rule__Branch__CommandsAssignment_4_2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==34||LA30_0==38) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalTestua.g:2185:4: rule__Branch__CommandsAssignment_4_2
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__Branch__CommandsAssignment_4_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getBranchAccess().getCommandsAssignment_4_2()); 

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
    // $ANTLR end "rule__Branch__Group_4__2__Impl"


    // $ANTLR start "rule__Branch__Group_4__3"
    // InternalTestua.g:2194:1: rule__Branch__Group_4__3 : rule__Branch__Group_4__3__Impl ;
    public final void rule__Branch__Group_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2198:1: ( rule__Branch__Group_4__3__Impl )
            // InternalTestua.g:2199:2: rule__Branch__Group_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Branch__Group_4__3__Impl();

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
    // $ANTLR end "rule__Branch__Group_4__3"


    // $ANTLR start "rule__Branch__Group_4__3__Impl"
    // InternalTestua.g:2205:1: rule__Branch__Group_4__3__Impl : ( '}' ) ;
    public final void rule__Branch__Group_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2209:1: ( ( '}' ) )
            // InternalTestua.g:2210:1: ( '}' )
            {
            // InternalTestua.g:2210:1: ( '}' )
            // InternalTestua.g:2211:2: '}'
            {
             before(grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_4_3()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_4_3()); 

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
    // $ANTLR end "rule__Branch__Group_4__3__Impl"


    // $ANTLR start "rule__Condition__Group__0"
    // InternalTestua.g:2221:1: rule__Condition__Group__0 : rule__Condition__Group__0__Impl rule__Condition__Group__1 ;
    public final void rule__Condition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2225:1: ( rule__Condition__Group__0__Impl rule__Condition__Group__1 )
            // InternalTestua.g:2226:2: rule__Condition__Group__0__Impl rule__Condition__Group__1
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
    // InternalTestua.g:2233:1: rule__Condition__Group__0__Impl : ( ( rule__Condition__ConditionAssignment_0 ) ) ;
    public final void rule__Condition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2237:1: ( ( ( rule__Condition__ConditionAssignment_0 ) ) )
            // InternalTestua.g:2238:1: ( ( rule__Condition__ConditionAssignment_0 ) )
            {
            // InternalTestua.g:2238:1: ( ( rule__Condition__ConditionAssignment_0 ) )
            // InternalTestua.g:2239:2: ( rule__Condition__ConditionAssignment_0 )
            {
             before(grammarAccess.getConditionAccess().getConditionAssignment_0()); 
            // InternalTestua.g:2240:2: ( rule__Condition__ConditionAssignment_0 )
            // InternalTestua.g:2240:3: rule__Condition__ConditionAssignment_0
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
    // InternalTestua.g:2248:1: rule__Condition__Group__1 : rule__Condition__Group__1__Impl rule__Condition__Group__2 ;
    public final void rule__Condition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2252:1: ( rule__Condition__Group__1__Impl rule__Condition__Group__2 )
            // InternalTestua.g:2253:2: rule__Condition__Group__1__Impl rule__Condition__Group__2
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
    // InternalTestua.g:2260:1: rule__Condition__Group__1__Impl : ( '{' ) ;
    public final void rule__Condition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2264:1: ( ( '{' ) )
            // InternalTestua.g:2265:1: ( '{' )
            {
            // InternalTestua.g:2265:1: ( '{' )
            // InternalTestua.g:2266:2: '{'
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
    // InternalTestua.g:2275:1: rule__Condition__Group__2 : rule__Condition__Group__2__Impl rule__Condition__Group__3 ;
    public final void rule__Condition__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2279:1: ( rule__Condition__Group__2__Impl rule__Condition__Group__3 )
            // InternalTestua.g:2280:2: rule__Condition__Group__2__Impl rule__Condition__Group__3
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
    // InternalTestua.g:2287:1: rule__Condition__Group__2__Impl : ( ( rule__Condition__CommandsAssignment_2 )* ) ;
    public final void rule__Condition__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2291:1: ( ( ( rule__Condition__CommandsAssignment_2 )* ) )
            // InternalTestua.g:2292:1: ( ( rule__Condition__CommandsAssignment_2 )* )
            {
            // InternalTestua.g:2292:1: ( ( rule__Condition__CommandsAssignment_2 )* )
            // InternalTestua.g:2293:2: ( rule__Condition__CommandsAssignment_2 )*
            {
             before(grammarAccess.getConditionAccess().getCommandsAssignment_2()); 
            // InternalTestua.g:2294:2: ( rule__Condition__CommandsAssignment_2 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID||LA31_0==34||LA31_0==38) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalTestua.g:2294:3: rule__Condition__CommandsAssignment_2
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
    // InternalTestua.g:2302:1: rule__Condition__Group__3 : rule__Condition__Group__3__Impl ;
    public final void rule__Condition__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2306:1: ( rule__Condition__Group__3__Impl )
            // InternalTestua.g:2307:2: rule__Condition__Group__3__Impl
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
    // InternalTestua.g:2313:1: rule__Condition__Group__3__Impl : ( '}' ) ;
    public final void rule__Condition__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2317:1: ( ( '}' ) )
            // InternalTestua.g:2318:1: ( '}' )
            {
            // InternalTestua.g:2318:1: ( '}' )
            // InternalTestua.g:2319:2: '}'
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
    // InternalTestua.g:2329:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2333:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalTestua.g:2334:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
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
    // InternalTestua.g:2341:1: rule__Assignment__Group__0__Impl : ( ( rule__Assignment__AccessAssignment_0 ) ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2345:1: ( ( ( rule__Assignment__AccessAssignment_0 ) ) )
            // InternalTestua.g:2346:1: ( ( rule__Assignment__AccessAssignment_0 ) )
            {
            // InternalTestua.g:2346:1: ( ( rule__Assignment__AccessAssignment_0 ) )
            // InternalTestua.g:2347:2: ( rule__Assignment__AccessAssignment_0 )
            {
             before(grammarAccess.getAssignmentAccess().getAccessAssignment_0()); 
            // InternalTestua.g:2348:2: ( rule__Assignment__AccessAssignment_0 )
            // InternalTestua.g:2348:3: rule__Assignment__AccessAssignment_0
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
    // InternalTestua.g:2356:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl rule__Assignment__Group__2 ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2360:1: ( rule__Assignment__Group__1__Impl rule__Assignment__Group__2 )
            // InternalTestua.g:2361:2: rule__Assignment__Group__1__Impl rule__Assignment__Group__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2368:1: rule__Assignment__Group__1__Impl : ( ( rule__Assignment__TypeAssignment_1 ) ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2372:1: ( ( ( rule__Assignment__TypeAssignment_1 ) ) )
            // InternalTestua.g:2373:1: ( ( rule__Assignment__TypeAssignment_1 ) )
            {
            // InternalTestua.g:2373:1: ( ( rule__Assignment__TypeAssignment_1 ) )
            // InternalTestua.g:2374:2: ( rule__Assignment__TypeAssignment_1 )
            {
             before(grammarAccess.getAssignmentAccess().getTypeAssignment_1()); 
            // InternalTestua.g:2375:2: ( rule__Assignment__TypeAssignment_1 )
            // InternalTestua.g:2375:3: rule__Assignment__TypeAssignment_1
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
    // InternalTestua.g:2383:1: rule__Assignment__Group__2 : rule__Assignment__Group__2__Impl ;
    public final void rule__Assignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2387:1: ( rule__Assignment__Group__2__Impl )
            // InternalTestua.g:2388:2: rule__Assignment__Group__2__Impl
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
    // InternalTestua.g:2394:1: rule__Assignment__Group__2__Impl : ( ( rule__Assignment__ExpressionAssignment_2 ) ) ;
    public final void rule__Assignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2398:1: ( ( ( rule__Assignment__ExpressionAssignment_2 ) ) )
            // InternalTestua.g:2399:1: ( ( rule__Assignment__ExpressionAssignment_2 ) )
            {
            // InternalTestua.g:2399:1: ( ( rule__Assignment__ExpressionAssignment_2 ) )
            // InternalTestua.g:2400:2: ( rule__Assignment__ExpressionAssignment_2 )
            {
             before(grammarAccess.getAssignmentAccess().getExpressionAssignment_2()); 
            // InternalTestua.g:2401:2: ( rule__Assignment__ExpressionAssignment_2 )
            // InternalTestua.g:2401:3: rule__Assignment__ExpressionAssignment_2
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
    // InternalTestua.g:2410:1: rule__Call__Group__0 : rule__Call__Group__0__Impl rule__Call__Group__1 ;
    public final void rule__Call__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2414:1: ( rule__Call__Group__0__Impl rule__Call__Group__1 )
            // InternalTestua.g:2415:2: rule__Call__Group__0__Impl rule__Call__Group__1
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
    // InternalTestua.g:2422:1: rule__Call__Group__0__Impl : ( ( rule__Call__NameAssignment_0 ) ) ;
    public final void rule__Call__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2426:1: ( ( ( rule__Call__NameAssignment_0 ) ) )
            // InternalTestua.g:2427:1: ( ( rule__Call__NameAssignment_0 ) )
            {
            // InternalTestua.g:2427:1: ( ( rule__Call__NameAssignment_0 ) )
            // InternalTestua.g:2428:2: ( rule__Call__NameAssignment_0 )
            {
             before(grammarAccess.getCallAccess().getNameAssignment_0()); 
            // InternalTestua.g:2429:2: ( rule__Call__NameAssignment_0 )
            // InternalTestua.g:2429:3: rule__Call__NameAssignment_0
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
    // InternalTestua.g:2437:1: rule__Call__Group__1 : rule__Call__Group__1__Impl rule__Call__Group__2 ;
    public final void rule__Call__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2441:1: ( rule__Call__Group__1__Impl rule__Call__Group__2 )
            // InternalTestua.g:2442:2: rule__Call__Group__1__Impl rule__Call__Group__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2449:1: rule__Call__Group__1__Impl : ( '(' ) ;
    public final void rule__Call__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2453:1: ( ( '(' ) )
            // InternalTestua.g:2454:1: ( '(' )
            {
            // InternalTestua.g:2454:1: ( '(' )
            // InternalTestua.g:2455:2: '('
            {
             before(grammarAccess.getCallAccess().getLeftParenthesisKeyword_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalTestua.g:2464:1: rule__Call__Group__2 : rule__Call__Group__2__Impl rule__Call__Group__3 ;
    public final void rule__Call__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2468:1: ( rule__Call__Group__2__Impl rule__Call__Group__3 )
            // InternalTestua.g:2469:2: rule__Call__Group__2__Impl rule__Call__Group__3
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
    // InternalTestua.g:2476:1: rule__Call__Group__2__Impl : ( ( rule__Call__ParametersAssignment_2 ) ) ;
    public final void rule__Call__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2480:1: ( ( ( rule__Call__ParametersAssignment_2 ) ) )
            // InternalTestua.g:2481:1: ( ( rule__Call__ParametersAssignment_2 ) )
            {
            // InternalTestua.g:2481:1: ( ( rule__Call__ParametersAssignment_2 ) )
            // InternalTestua.g:2482:2: ( rule__Call__ParametersAssignment_2 )
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_2()); 
            // InternalTestua.g:2483:2: ( rule__Call__ParametersAssignment_2 )
            // InternalTestua.g:2483:3: rule__Call__ParametersAssignment_2
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
    // InternalTestua.g:2491:1: rule__Call__Group__3 : rule__Call__Group__3__Impl rule__Call__Group__4 ;
    public final void rule__Call__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2495:1: ( rule__Call__Group__3__Impl rule__Call__Group__4 )
            // InternalTestua.g:2496:2: rule__Call__Group__3__Impl rule__Call__Group__4
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
    // InternalTestua.g:2503:1: rule__Call__Group__3__Impl : ( ( rule__Call__Group_3__0 )* ) ;
    public final void rule__Call__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2507:1: ( ( ( rule__Call__Group_3__0 )* ) )
            // InternalTestua.g:2508:1: ( ( rule__Call__Group_3__0 )* )
            {
            // InternalTestua.g:2508:1: ( ( rule__Call__Group_3__0 )* )
            // InternalTestua.g:2509:2: ( rule__Call__Group_3__0 )*
            {
             before(grammarAccess.getCallAccess().getGroup_3()); 
            // InternalTestua.g:2510:2: ( rule__Call__Group_3__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==31) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalTestua.g:2510:3: rule__Call__Group_3__0
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
    // InternalTestua.g:2518:1: rule__Call__Group__4 : rule__Call__Group__4__Impl ;
    public final void rule__Call__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2522:1: ( rule__Call__Group__4__Impl )
            // InternalTestua.g:2523:2: rule__Call__Group__4__Impl
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
    // InternalTestua.g:2529:1: rule__Call__Group__4__Impl : ( ')' ) ;
    public final void rule__Call__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2533:1: ( ( ')' ) )
            // InternalTestua.g:2534:1: ( ')' )
            {
            // InternalTestua.g:2534:1: ( ')' )
            // InternalTestua.g:2535:2: ')'
            {
             before(grammarAccess.getCallAccess().getRightParenthesisKeyword_4()); 
            match(input,43,FOLLOW_2); 
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
    // InternalTestua.g:2545:1: rule__Call__Group_3__0 : rule__Call__Group_3__0__Impl rule__Call__Group_3__1 ;
    public final void rule__Call__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2549:1: ( rule__Call__Group_3__0__Impl rule__Call__Group_3__1 )
            // InternalTestua.g:2550:2: rule__Call__Group_3__0__Impl rule__Call__Group_3__1
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2557:1: rule__Call__Group_3__0__Impl : ( ',' ) ;
    public final void rule__Call__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2561:1: ( ( ',' ) )
            // InternalTestua.g:2562:1: ( ',' )
            {
            // InternalTestua.g:2562:1: ( ',' )
            // InternalTestua.g:2563:2: ','
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
    // InternalTestua.g:2572:1: rule__Call__Group_3__1 : rule__Call__Group_3__1__Impl ;
    public final void rule__Call__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2576:1: ( rule__Call__Group_3__1__Impl )
            // InternalTestua.g:2577:2: rule__Call__Group_3__1__Impl
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
    // InternalTestua.g:2583:1: rule__Call__Group_3__1__Impl : ( ( rule__Call__ParametersAssignment_3_1 ) ) ;
    public final void rule__Call__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2587:1: ( ( ( rule__Call__ParametersAssignment_3_1 ) ) )
            // InternalTestua.g:2588:1: ( ( rule__Call__ParametersAssignment_3_1 ) )
            {
            // InternalTestua.g:2588:1: ( ( rule__Call__ParametersAssignment_3_1 ) )
            // InternalTestua.g:2589:2: ( rule__Call__ParametersAssignment_3_1 )
            {
             before(grammarAccess.getCallAccess().getParametersAssignment_3_1()); 
            // InternalTestua.g:2590:2: ( rule__Call__ParametersAssignment_3_1 )
            // InternalTestua.g:2590:3: rule__Call__ParametersAssignment_3_1
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
    // InternalTestua.g:2599:1: rule__Access__Group__0 : rule__Access__Group__0__Impl rule__Access__Group__1 ;
    public final void rule__Access__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2603:1: ( rule__Access__Group__0__Impl rule__Access__Group__1 )
            // InternalTestua.g:2604:2: rule__Access__Group__0__Impl rule__Access__Group__1
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
    // InternalTestua.g:2611:1: rule__Access__Group__0__Impl : ( ( rule__Access__NamesAssignment_0 ) ) ;
    public final void rule__Access__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2615:1: ( ( ( rule__Access__NamesAssignment_0 ) ) )
            // InternalTestua.g:2616:1: ( ( rule__Access__NamesAssignment_0 ) )
            {
            // InternalTestua.g:2616:1: ( ( rule__Access__NamesAssignment_0 ) )
            // InternalTestua.g:2617:2: ( rule__Access__NamesAssignment_0 )
            {
             before(grammarAccess.getAccessAccess().getNamesAssignment_0()); 
            // InternalTestua.g:2618:2: ( rule__Access__NamesAssignment_0 )
            // InternalTestua.g:2618:3: rule__Access__NamesAssignment_0
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
    // InternalTestua.g:2626:1: rule__Access__Group__1 : rule__Access__Group__1__Impl ;
    public final void rule__Access__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2630:1: ( rule__Access__Group__1__Impl )
            // InternalTestua.g:2631:2: rule__Access__Group__1__Impl
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
    // InternalTestua.g:2637:1: rule__Access__Group__1__Impl : ( ( rule__Access__Group_1__0 )* ) ;
    public final void rule__Access__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2641:1: ( ( ( rule__Access__Group_1__0 )* ) )
            // InternalTestua.g:2642:1: ( ( rule__Access__Group_1__0 )* )
            {
            // InternalTestua.g:2642:1: ( ( rule__Access__Group_1__0 )* )
            // InternalTestua.g:2643:2: ( rule__Access__Group_1__0 )*
            {
             before(grammarAccess.getAccessAccess().getGroup_1()); 
            // InternalTestua.g:2644:2: ( rule__Access__Group_1__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==44) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalTestua.g:2644:3: rule__Access__Group_1__0
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
    // InternalTestua.g:2653:1: rule__Access__Group_1__0 : rule__Access__Group_1__0__Impl rule__Access__Group_1__1 ;
    public final void rule__Access__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2657:1: ( rule__Access__Group_1__0__Impl rule__Access__Group_1__1 )
            // InternalTestua.g:2658:2: rule__Access__Group_1__0__Impl rule__Access__Group_1__1
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
    // InternalTestua.g:2665:1: rule__Access__Group_1__0__Impl : ( '.' ) ;
    public final void rule__Access__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2669:1: ( ( '.' ) )
            // InternalTestua.g:2670:1: ( '.' )
            {
            // InternalTestua.g:2670:1: ( '.' )
            // InternalTestua.g:2671:2: '.'
            {
             before(grammarAccess.getAccessAccess().getFullStopKeyword_1_0()); 
            match(input,44,FOLLOW_2); 
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
    // InternalTestua.g:2680:1: rule__Access__Group_1__1 : rule__Access__Group_1__1__Impl ;
    public final void rule__Access__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2684:1: ( rule__Access__Group_1__1__Impl )
            // InternalTestua.g:2685:2: rule__Access__Group_1__1__Impl
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
    // InternalTestua.g:2691:1: rule__Access__Group_1__1__Impl : ( ( rule__Access__NamesAssignment_1_1 ) ) ;
    public final void rule__Access__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2695:1: ( ( ( rule__Access__NamesAssignment_1_1 ) ) )
            // InternalTestua.g:2696:1: ( ( rule__Access__NamesAssignment_1_1 ) )
            {
            // InternalTestua.g:2696:1: ( ( rule__Access__NamesAssignment_1_1 ) )
            // InternalTestua.g:2697:2: ( rule__Access__NamesAssignment_1_1 )
            {
             before(grammarAccess.getAccessAccess().getNamesAssignment_1_1()); 
            // InternalTestua.g:2698:2: ( rule__Access__NamesAssignment_1_1 )
            // InternalTestua.g:2698:3: rule__Access__NamesAssignment_1_1
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
    // InternalTestua.g:2707:1: rule__Expression__Group__0 : rule__Expression__Group__0__Impl rule__Expression__Group__1 ;
    public final void rule__Expression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2711:1: ( rule__Expression__Group__0__Impl rule__Expression__Group__1 )
            // InternalTestua.g:2712:2: rule__Expression__Group__0__Impl rule__Expression__Group__1
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
    // InternalTestua.g:2719:1: rule__Expression__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Expression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2723:1: ( ( ruleAnd ) )
            // InternalTestua.g:2724:1: ( ruleAnd )
            {
            // InternalTestua.g:2724:1: ( ruleAnd )
            // InternalTestua.g:2725:2: ruleAnd
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
    // InternalTestua.g:2734:1: rule__Expression__Group__1 : rule__Expression__Group__1__Impl ;
    public final void rule__Expression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2738:1: ( rule__Expression__Group__1__Impl )
            // InternalTestua.g:2739:2: rule__Expression__Group__1__Impl
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
    // InternalTestua.g:2745:1: rule__Expression__Group__1__Impl : ( ( rule__Expression__Group_1__0 )* ) ;
    public final void rule__Expression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2749:1: ( ( ( rule__Expression__Group_1__0 )* ) )
            // InternalTestua.g:2750:1: ( ( rule__Expression__Group_1__0 )* )
            {
            // InternalTestua.g:2750:1: ( ( rule__Expression__Group_1__0 )* )
            // InternalTestua.g:2751:2: ( rule__Expression__Group_1__0 )*
            {
             before(grammarAccess.getExpressionAccess().getGroup_1()); 
            // InternalTestua.g:2752:2: ( rule__Expression__Group_1__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==45) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalTestua.g:2752:3: rule__Expression__Group_1__0
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
    // InternalTestua.g:2761:1: rule__Expression__Group_1__0 : rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1 ;
    public final void rule__Expression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2765:1: ( rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1 )
            // InternalTestua.g:2766:2: rule__Expression__Group_1__0__Impl rule__Expression__Group_1__1
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
    // InternalTestua.g:2773:1: rule__Expression__Group_1__0__Impl : ( () ) ;
    public final void rule__Expression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2777:1: ( ( () ) )
            // InternalTestua.g:2778:1: ( () )
            {
            // InternalTestua.g:2778:1: ( () )
            // InternalTestua.g:2779:2: ()
            {
             before(grammarAccess.getExpressionAccess().getOrLeftAction_1_0()); 
            // InternalTestua.g:2780:2: ()
            // InternalTestua.g:2780:3: 
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
    // InternalTestua.g:2788:1: rule__Expression__Group_1__1 : rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2 ;
    public final void rule__Expression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2792:1: ( rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2 )
            // InternalTestua.g:2793:2: rule__Expression__Group_1__1__Impl rule__Expression__Group_1__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2800:1: rule__Expression__Group_1__1__Impl : ( '||' ) ;
    public final void rule__Expression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2804:1: ( ( '||' ) )
            // InternalTestua.g:2805:1: ( '||' )
            {
            // InternalTestua.g:2805:1: ( '||' )
            // InternalTestua.g:2806:2: '||'
            {
             before(grammarAccess.getExpressionAccess().getVerticalLineVerticalLineKeyword_1_1()); 
            match(input,45,FOLLOW_2); 
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
    // InternalTestua.g:2815:1: rule__Expression__Group_1__2 : rule__Expression__Group_1__2__Impl ;
    public final void rule__Expression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2819:1: ( rule__Expression__Group_1__2__Impl )
            // InternalTestua.g:2820:2: rule__Expression__Group_1__2__Impl
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
    // InternalTestua.g:2826:1: rule__Expression__Group_1__2__Impl : ( ( rule__Expression__RightAssignment_1_2 ) ) ;
    public final void rule__Expression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2830:1: ( ( ( rule__Expression__RightAssignment_1_2 ) ) )
            // InternalTestua.g:2831:1: ( ( rule__Expression__RightAssignment_1_2 ) )
            {
            // InternalTestua.g:2831:1: ( ( rule__Expression__RightAssignment_1_2 ) )
            // InternalTestua.g:2832:2: ( rule__Expression__RightAssignment_1_2 )
            {
             before(grammarAccess.getExpressionAccess().getRightAssignment_1_2()); 
            // InternalTestua.g:2833:2: ( rule__Expression__RightAssignment_1_2 )
            // InternalTestua.g:2833:3: rule__Expression__RightAssignment_1_2
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
    // InternalTestua.g:2842:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2846:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // InternalTestua.g:2847:2: rule__And__Group__0__Impl rule__And__Group__1
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
    // InternalTestua.g:2854:1: rule__And__Group__0__Impl : ( rulePrimaryB ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2858:1: ( ( rulePrimaryB ) )
            // InternalTestua.g:2859:1: ( rulePrimaryB )
            {
            // InternalTestua.g:2859:1: ( rulePrimaryB )
            // InternalTestua.g:2860:2: rulePrimaryB
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
    // InternalTestua.g:2869:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2873:1: ( rule__And__Group__1__Impl )
            // InternalTestua.g:2874:2: rule__And__Group__1__Impl
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
    // InternalTestua.g:2880:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2884:1: ( ( ( rule__And__Group_1__0 )* ) )
            // InternalTestua.g:2885:1: ( ( rule__And__Group_1__0 )* )
            {
            // InternalTestua.g:2885:1: ( ( rule__And__Group_1__0 )* )
            // InternalTestua.g:2886:2: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // InternalTestua.g:2887:2: ( rule__And__Group_1__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==46) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalTestua.g:2887:3: rule__And__Group_1__0
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
    // InternalTestua.g:2896:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2900:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // InternalTestua.g:2901:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
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
    // InternalTestua.g:2908:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2912:1: ( ( () ) )
            // InternalTestua.g:2913:1: ( () )
            {
            // InternalTestua.g:2913:1: ( () )
            // InternalTestua.g:2914:2: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // InternalTestua.g:2915:2: ()
            // InternalTestua.g:2915:3: 
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
    // InternalTestua.g:2923:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2927:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // InternalTestua.g:2928:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2935:1: rule__And__Group_1__1__Impl : ( '&&' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2939:1: ( ( '&&' ) )
            // InternalTestua.g:2940:1: ( '&&' )
            {
            // InternalTestua.g:2940:1: ( '&&' )
            // InternalTestua.g:2941:2: '&&'
            {
             before(grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1()); 
            match(input,46,FOLLOW_2); 
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
    // InternalTestua.g:2950:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2954:1: ( rule__And__Group_1__2__Impl )
            // InternalTestua.g:2955:2: rule__And__Group_1__2__Impl
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
    // InternalTestua.g:2961:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2965:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // InternalTestua.g:2966:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // InternalTestua.g:2966:1: ( ( rule__And__RightAssignment_1_2 ) )
            // InternalTestua.g:2967:2: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // InternalTestua.g:2968:2: ( rule__And__RightAssignment_1_2 )
            // InternalTestua.g:2968:3: rule__And__RightAssignment_1_2
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
    // InternalTestua.g:2977:1: rule__PrimaryB__Group_1__0 : rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1 ;
    public final void rule__PrimaryB__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2981:1: ( rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1 )
            // InternalTestua.g:2982:2: rule__PrimaryB__Group_1__0__Impl rule__PrimaryB__Group_1__1
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:2989:1: rule__PrimaryB__Group_1__0__Impl : ( () ) ;
    public final void rule__PrimaryB__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:2993:1: ( ( () ) )
            // InternalTestua.g:2994:1: ( () )
            {
            // InternalTestua.g:2994:1: ( () )
            // InternalTestua.g:2995:2: ()
            {
             before(grammarAccess.getPrimaryBAccess().getNotAction_1_0()); 
            // InternalTestua.g:2996:2: ()
            // InternalTestua.g:2996:3: 
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
    // InternalTestua.g:3004:1: rule__PrimaryB__Group_1__1 : rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2 ;
    public final void rule__PrimaryB__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3008:1: ( rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2 )
            // InternalTestua.g:3009:2: rule__PrimaryB__Group_1__1__Impl rule__PrimaryB__Group_1__2
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
    // InternalTestua.g:3016:1: rule__PrimaryB__Group_1__1__Impl : ( '!' ) ;
    public final void rule__PrimaryB__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3020:1: ( ( '!' ) )
            // InternalTestua.g:3021:1: ( '!' )
            {
            // InternalTestua.g:3021:1: ( '!' )
            // InternalTestua.g:3022:2: '!'
            {
             before(grammarAccess.getPrimaryBAccess().getExclamationMarkKeyword_1_1()); 
            match(input,47,FOLLOW_2); 
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
    // InternalTestua.g:3031:1: rule__PrimaryB__Group_1__2 : rule__PrimaryB__Group_1__2__Impl ;
    public final void rule__PrimaryB__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3035:1: ( rule__PrimaryB__Group_1__2__Impl )
            // InternalTestua.g:3036:2: rule__PrimaryB__Group_1__2__Impl
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
    // InternalTestua.g:3042:1: rule__PrimaryB__Group_1__2__Impl : ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) ) ;
    public final void rule__PrimaryB__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3046:1: ( ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) ) )
            // InternalTestua.g:3047:1: ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) )
            {
            // InternalTestua.g:3047:1: ( ( rule__PrimaryB__ExpressionAssignment_1_2 ) )
            // InternalTestua.g:3048:2: ( rule__PrimaryB__ExpressionAssignment_1_2 )
            {
             before(grammarAccess.getPrimaryBAccess().getExpressionAssignment_1_2()); 
            // InternalTestua.g:3049:2: ( rule__PrimaryB__ExpressionAssignment_1_2 )
            // InternalTestua.g:3049:3: rule__PrimaryB__ExpressionAssignment_1_2
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
    // InternalTestua.g:3058:1: rule__AtomicB__Group__0 : rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1 ;
    public final void rule__AtomicB__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3062:1: ( rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1 )
            // InternalTestua.g:3063:2: rule__AtomicB__Group__0__Impl rule__AtomicB__Group__1
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
    // InternalTestua.g:3070:1: rule__AtomicB__Group__0__Impl : ( ruleAddition ) ;
    public final void rule__AtomicB__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3074:1: ( ( ruleAddition ) )
            // InternalTestua.g:3075:1: ( ruleAddition )
            {
            // InternalTestua.g:3075:1: ( ruleAddition )
            // InternalTestua.g:3076:2: ruleAddition
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
    // InternalTestua.g:3085:1: rule__AtomicB__Group__1 : rule__AtomicB__Group__1__Impl ;
    public final void rule__AtomicB__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3089:1: ( rule__AtomicB__Group__1__Impl )
            // InternalTestua.g:3090:2: rule__AtomicB__Group__1__Impl
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
    // InternalTestua.g:3096:1: rule__AtomicB__Group__1__Impl : ( ( rule__AtomicB__Group_1__0 )? ) ;
    public final void rule__AtomicB__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3100:1: ( ( ( rule__AtomicB__Group_1__0 )? ) )
            // InternalTestua.g:3101:1: ( ( rule__AtomicB__Group_1__0 )? )
            {
            // InternalTestua.g:3101:1: ( ( rule__AtomicB__Group_1__0 )? )
            // InternalTestua.g:3102:2: ( rule__AtomicB__Group_1__0 )?
            {
             before(grammarAccess.getAtomicBAccess().getGroup_1()); 
            // InternalTestua.g:3103:2: ( rule__AtomicB__Group_1__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=12 && LA36_0<=17)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalTestua.g:3103:3: rule__AtomicB__Group_1__0
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
    // InternalTestua.g:3112:1: rule__AtomicB__Group_1__0 : rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1 ;
    public final void rule__AtomicB__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3116:1: ( rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1 )
            // InternalTestua.g:3117:2: rule__AtomicB__Group_1__0__Impl rule__AtomicB__Group_1__1
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
    // InternalTestua.g:3124:1: rule__AtomicB__Group_1__0__Impl : ( () ) ;
    public final void rule__AtomicB__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3128:1: ( ( () ) )
            // InternalTestua.g:3129:1: ( () )
            {
            // InternalTestua.g:3129:1: ( () )
            // InternalTestua.g:3130:2: ()
            {
             before(grammarAccess.getAtomicBAccess().getComparisonLeftAction_1_0()); 
            // InternalTestua.g:3131:2: ()
            // InternalTestua.g:3131:3: 
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
    // InternalTestua.g:3139:1: rule__AtomicB__Group_1__1 : rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2 ;
    public final void rule__AtomicB__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3143:1: ( rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2 )
            // InternalTestua.g:3144:2: rule__AtomicB__Group_1__1__Impl rule__AtomicB__Group_1__2
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
    // InternalTestua.g:3151:1: rule__AtomicB__Group_1__1__Impl : ( ( rule__AtomicB__TypeAssignment_1_1 ) ) ;
    public final void rule__AtomicB__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3155:1: ( ( ( rule__AtomicB__TypeAssignment_1_1 ) ) )
            // InternalTestua.g:3156:1: ( ( rule__AtomicB__TypeAssignment_1_1 ) )
            {
            // InternalTestua.g:3156:1: ( ( rule__AtomicB__TypeAssignment_1_1 ) )
            // InternalTestua.g:3157:2: ( rule__AtomicB__TypeAssignment_1_1 )
            {
             before(grammarAccess.getAtomicBAccess().getTypeAssignment_1_1()); 
            // InternalTestua.g:3158:2: ( rule__AtomicB__TypeAssignment_1_1 )
            // InternalTestua.g:3158:3: rule__AtomicB__TypeAssignment_1_1
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
    // InternalTestua.g:3166:1: rule__AtomicB__Group_1__2 : rule__AtomicB__Group_1__2__Impl ;
    public final void rule__AtomicB__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3170:1: ( rule__AtomicB__Group_1__2__Impl )
            // InternalTestua.g:3171:2: rule__AtomicB__Group_1__2__Impl
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
    // InternalTestua.g:3177:1: rule__AtomicB__Group_1__2__Impl : ( ( rule__AtomicB__RightAssignment_1_2 ) ) ;
    public final void rule__AtomicB__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3181:1: ( ( ( rule__AtomicB__RightAssignment_1_2 ) ) )
            // InternalTestua.g:3182:1: ( ( rule__AtomicB__RightAssignment_1_2 ) )
            {
            // InternalTestua.g:3182:1: ( ( rule__AtomicB__RightAssignment_1_2 ) )
            // InternalTestua.g:3183:2: ( rule__AtomicB__RightAssignment_1_2 )
            {
             before(grammarAccess.getAtomicBAccess().getRightAssignment_1_2()); 
            // InternalTestua.g:3184:2: ( rule__AtomicB__RightAssignment_1_2 )
            // InternalTestua.g:3184:3: rule__AtomicB__RightAssignment_1_2
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
    // InternalTestua.g:3193:1: rule__Addition__Group__0 : rule__Addition__Group__0__Impl rule__Addition__Group__1 ;
    public final void rule__Addition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3197:1: ( rule__Addition__Group__0__Impl rule__Addition__Group__1 )
            // InternalTestua.g:3198:2: rule__Addition__Group__0__Impl rule__Addition__Group__1
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
    // InternalTestua.g:3205:1: rule__Addition__Group__0__Impl : ( ruleMultiplication ) ;
    public final void rule__Addition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3209:1: ( ( ruleMultiplication ) )
            // InternalTestua.g:3210:1: ( ruleMultiplication )
            {
            // InternalTestua.g:3210:1: ( ruleMultiplication )
            // InternalTestua.g:3211:2: ruleMultiplication
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
    // InternalTestua.g:3220:1: rule__Addition__Group__1 : rule__Addition__Group__1__Impl ;
    public final void rule__Addition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3224:1: ( rule__Addition__Group__1__Impl )
            // InternalTestua.g:3225:2: rule__Addition__Group__1__Impl
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
    // InternalTestua.g:3231:1: rule__Addition__Group__1__Impl : ( ( rule__Addition__Group_1__0 )* ) ;
    public final void rule__Addition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3235:1: ( ( ( rule__Addition__Group_1__0 )* ) )
            // InternalTestua.g:3236:1: ( ( rule__Addition__Group_1__0 )* )
            {
            // InternalTestua.g:3236:1: ( ( rule__Addition__Group_1__0 )* )
            // InternalTestua.g:3237:2: ( rule__Addition__Group_1__0 )*
            {
             before(grammarAccess.getAdditionAccess().getGroup_1()); 
            // InternalTestua.g:3238:2: ( rule__Addition__Group_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( ((LA37_0>=48 && LA37_0<=49)) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalTestua.g:3238:3: rule__Addition__Group_1__0
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
    // InternalTestua.g:3247:1: rule__Addition__Group_1__0 : rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 ;
    public final void rule__Addition__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3251:1: ( rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 )
            // InternalTestua.g:3252:2: rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1
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
    // InternalTestua.g:3259:1: rule__Addition__Group_1__0__Impl : ( ( rule__Addition__Alternatives_1_0 ) ) ;
    public final void rule__Addition__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3263:1: ( ( ( rule__Addition__Alternatives_1_0 ) ) )
            // InternalTestua.g:3264:1: ( ( rule__Addition__Alternatives_1_0 ) )
            {
            // InternalTestua.g:3264:1: ( ( rule__Addition__Alternatives_1_0 ) )
            // InternalTestua.g:3265:2: ( rule__Addition__Alternatives_1_0 )
            {
             before(grammarAccess.getAdditionAccess().getAlternatives_1_0()); 
            // InternalTestua.g:3266:2: ( rule__Addition__Alternatives_1_0 )
            // InternalTestua.g:3266:3: rule__Addition__Alternatives_1_0
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
    // InternalTestua.g:3274:1: rule__Addition__Group_1__1 : rule__Addition__Group_1__1__Impl ;
    public final void rule__Addition__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3278:1: ( rule__Addition__Group_1__1__Impl )
            // InternalTestua.g:3279:2: rule__Addition__Group_1__1__Impl
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
    // InternalTestua.g:3285:1: rule__Addition__Group_1__1__Impl : ( ( rule__Addition__RightAssignment_1_1 ) ) ;
    public final void rule__Addition__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3289:1: ( ( ( rule__Addition__RightAssignment_1_1 ) ) )
            // InternalTestua.g:3290:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            {
            // InternalTestua.g:3290:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            // InternalTestua.g:3291:2: ( rule__Addition__RightAssignment_1_1 )
            {
             before(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); 
            // InternalTestua.g:3292:2: ( rule__Addition__RightAssignment_1_1 )
            // InternalTestua.g:3292:3: rule__Addition__RightAssignment_1_1
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
    // InternalTestua.g:3301:1: rule__Addition__Group_1_0_0__0 : rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 ;
    public final void rule__Addition__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3305:1: ( rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 )
            // InternalTestua.g:3306:2: rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1
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
    // InternalTestua.g:3313:1: rule__Addition__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3317:1: ( ( () ) )
            // InternalTestua.g:3318:1: ( () )
            {
            // InternalTestua.g:3318:1: ( () )
            // InternalTestua.g:3319:2: ()
            {
             before(grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0()); 
            // InternalTestua.g:3320:2: ()
            // InternalTestua.g:3320:3: 
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
    // InternalTestua.g:3328:1: rule__Addition__Group_1_0_0__1 : rule__Addition__Group_1_0_0__1__Impl ;
    public final void rule__Addition__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3332:1: ( rule__Addition__Group_1_0_0__1__Impl )
            // InternalTestua.g:3333:2: rule__Addition__Group_1_0_0__1__Impl
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
    // InternalTestua.g:3339:1: rule__Addition__Group_1_0_0__1__Impl : ( '+' ) ;
    public final void rule__Addition__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3343:1: ( ( '+' ) )
            // InternalTestua.g:3344:1: ( '+' )
            {
            // InternalTestua.g:3344:1: ( '+' )
            // InternalTestua.g:3345:2: '+'
            {
             before(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); 
            match(input,48,FOLLOW_2); 
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
    // InternalTestua.g:3355:1: rule__Addition__Group_1_0_1__0 : rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 ;
    public final void rule__Addition__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3359:1: ( rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 )
            // InternalTestua.g:3360:2: rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1
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
    // InternalTestua.g:3367:1: rule__Addition__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3371:1: ( ( () ) )
            // InternalTestua.g:3372:1: ( () )
            {
            // InternalTestua.g:3372:1: ( () )
            // InternalTestua.g:3373:2: ()
            {
             before(grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0()); 
            // InternalTestua.g:3374:2: ()
            // InternalTestua.g:3374:3: 
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
    // InternalTestua.g:3382:1: rule__Addition__Group_1_0_1__1 : rule__Addition__Group_1_0_1__1__Impl ;
    public final void rule__Addition__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3386:1: ( rule__Addition__Group_1_0_1__1__Impl )
            // InternalTestua.g:3387:2: rule__Addition__Group_1_0_1__1__Impl
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
    // InternalTestua.g:3393:1: rule__Addition__Group_1_0_1__1__Impl : ( '-' ) ;
    public final void rule__Addition__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3397:1: ( ( '-' ) )
            // InternalTestua.g:3398:1: ( '-' )
            {
            // InternalTestua.g:3398:1: ( '-' )
            // InternalTestua.g:3399:2: '-'
            {
             before(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); 
            match(input,49,FOLLOW_2); 
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
    // InternalTestua.g:3409:1: rule__Multiplication__Group__0 : rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 ;
    public final void rule__Multiplication__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3413:1: ( rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 )
            // InternalTestua.g:3414:2: rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1
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
    // InternalTestua.g:3421:1: rule__Multiplication__Group__0__Impl : ( ruleBitwise ) ;
    public final void rule__Multiplication__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3425:1: ( ( ruleBitwise ) )
            // InternalTestua.g:3426:1: ( ruleBitwise )
            {
            // InternalTestua.g:3426:1: ( ruleBitwise )
            // InternalTestua.g:3427:2: ruleBitwise
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
    // InternalTestua.g:3436:1: rule__Multiplication__Group__1 : rule__Multiplication__Group__1__Impl ;
    public final void rule__Multiplication__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3440:1: ( rule__Multiplication__Group__1__Impl )
            // InternalTestua.g:3441:2: rule__Multiplication__Group__1__Impl
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
    // InternalTestua.g:3447:1: rule__Multiplication__Group__1__Impl : ( ( rule__Multiplication__Group_1__0 )* ) ;
    public final void rule__Multiplication__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3451:1: ( ( ( rule__Multiplication__Group_1__0 )* ) )
            // InternalTestua.g:3452:1: ( ( rule__Multiplication__Group_1__0 )* )
            {
            // InternalTestua.g:3452:1: ( ( rule__Multiplication__Group_1__0 )* )
            // InternalTestua.g:3453:2: ( rule__Multiplication__Group_1__0 )*
            {
             before(grammarAccess.getMultiplicationAccess().getGroup_1()); 
            // InternalTestua.g:3454:2: ( rule__Multiplication__Group_1__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=50 && LA38_0<=52)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalTestua.g:3454:3: rule__Multiplication__Group_1__0
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
    // InternalTestua.g:3463:1: rule__Multiplication__Group_1__0 : rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 ;
    public final void rule__Multiplication__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3467:1: ( rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 )
            // InternalTestua.g:3468:2: rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1
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
    // InternalTestua.g:3475:1: rule__Multiplication__Group_1__0__Impl : ( ( rule__Multiplication__Alternatives_1_0 ) ) ;
    public final void rule__Multiplication__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3479:1: ( ( ( rule__Multiplication__Alternatives_1_0 ) ) )
            // InternalTestua.g:3480:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            {
            // InternalTestua.g:3480:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            // InternalTestua.g:3481:2: ( rule__Multiplication__Alternatives_1_0 )
            {
             before(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); 
            // InternalTestua.g:3482:2: ( rule__Multiplication__Alternatives_1_0 )
            // InternalTestua.g:3482:3: rule__Multiplication__Alternatives_1_0
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
    // InternalTestua.g:3490:1: rule__Multiplication__Group_1__1 : rule__Multiplication__Group_1__1__Impl ;
    public final void rule__Multiplication__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3494:1: ( rule__Multiplication__Group_1__1__Impl )
            // InternalTestua.g:3495:2: rule__Multiplication__Group_1__1__Impl
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
    // InternalTestua.g:3501:1: rule__Multiplication__Group_1__1__Impl : ( ( rule__Multiplication__RightAssignment_1_1 ) ) ;
    public final void rule__Multiplication__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3505:1: ( ( ( rule__Multiplication__RightAssignment_1_1 ) ) )
            // InternalTestua.g:3506:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            {
            // InternalTestua.g:3506:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            // InternalTestua.g:3507:2: ( rule__Multiplication__RightAssignment_1_1 )
            {
             before(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); 
            // InternalTestua.g:3508:2: ( rule__Multiplication__RightAssignment_1_1 )
            // InternalTestua.g:3508:3: rule__Multiplication__RightAssignment_1_1
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
    // InternalTestua.g:3517:1: rule__Multiplication__Group_1_0_0__0 : rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 ;
    public final void rule__Multiplication__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3521:1: ( rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 )
            // InternalTestua.g:3522:2: rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1
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
    // InternalTestua.g:3529:1: rule__Multiplication__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3533:1: ( ( () ) )
            // InternalTestua.g:3534:1: ( () )
            {
            // InternalTestua.g:3534:1: ( () )
            // InternalTestua.g:3535:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0()); 
            // InternalTestua.g:3536:2: ()
            // InternalTestua.g:3536:3: 
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
    // InternalTestua.g:3544:1: rule__Multiplication__Group_1_0_0__1 : rule__Multiplication__Group_1_0_0__1__Impl ;
    public final void rule__Multiplication__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3548:1: ( rule__Multiplication__Group_1_0_0__1__Impl )
            // InternalTestua.g:3549:2: rule__Multiplication__Group_1_0_0__1__Impl
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
    // InternalTestua.g:3555:1: rule__Multiplication__Group_1_0_0__1__Impl : ( '*' ) ;
    public final void rule__Multiplication__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3559:1: ( ( '*' ) )
            // InternalTestua.g:3560:1: ( '*' )
            {
            // InternalTestua.g:3560:1: ( '*' )
            // InternalTestua.g:3561:2: '*'
            {
             before(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); 
            match(input,50,FOLLOW_2); 
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
    // InternalTestua.g:3571:1: rule__Multiplication__Group_1_0_1__0 : rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 ;
    public final void rule__Multiplication__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3575:1: ( rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 )
            // InternalTestua.g:3576:2: rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1
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
    // InternalTestua.g:3583:1: rule__Multiplication__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3587:1: ( ( () ) )
            // InternalTestua.g:3588:1: ( () )
            {
            // InternalTestua.g:3588:1: ( () )
            // InternalTestua.g:3589:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0()); 
            // InternalTestua.g:3590:2: ()
            // InternalTestua.g:3590:3: 
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
    // InternalTestua.g:3598:1: rule__Multiplication__Group_1_0_1__1 : rule__Multiplication__Group_1_0_1__1__Impl ;
    public final void rule__Multiplication__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3602:1: ( rule__Multiplication__Group_1_0_1__1__Impl )
            // InternalTestua.g:3603:2: rule__Multiplication__Group_1_0_1__1__Impl
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
    // InternalTestua.g:3609:1: rule__Multiplication__Group_1_0_1__1__Impl : ( '/' ) ;
    public final void rule__Multiplication__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3613:1: ( ( '/' ) )
            // InternalTestua.g:3614:1: ( '/' )
            {
            // InternalTestua.g:3614:1: ( '/' )
            // InternalTestua.g:3615:2: '/'
            {
             before(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); 
            match(input,51,FOLLOW_2); 
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
    // InternalTestua.g:3625:1: rule__Multiplication__Group_1_0_2__0 : rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1 ;
    public final void rule__Multiplication__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3629:1: ( rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1 )
            // InternalTestua.g:3630:2: rule__Multiplication__Group_1_0_2__0__Impl rule__Multiplication__Group_1_0_2__1
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
    // InternalTestua.g:3637:1: rule__Multiplication__Group_1_0_2__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3641:1: ( ( () ) )
            // InternalTestua.g:3642:1: ( () )
            {
            // InternalTestua.g:3642:1: ( () )
            // InternalTestua.g:3643:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0()); 
            // InternalTestua.g:3644:2: ()
            // InternalTestua.g:3644:3: 
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
    // InternalTestua.g:3652:1: rule__Multiplication__Group_1_0_2__1 : rule__Multiplication__Group_1_0_2__1__Impl ;
    public final void rule__Multiplication__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3656:1: ( rule__Multiplication__Group_1_0_2__1__Impl )
            // InternalTestua.g:3657:2: rule__Multiplication__Group_1_0_2__1__Impl
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
    // InternalTestua.g:3663:1: rule__Multiplication__Group_1_0_2__1__Impl : ( '%' ) ;
    public final void rule__Multiplication__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3667:1: ( ( '%' ) )
            // InternalTestua.g:3668:1: ( '%' )
            {
            // InternalTestua.g:3668:1: ( '%' )
            // InternalTestua.g:3669:2: '%'
            {
             before(grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1()); 
            match(input,52,FOLLOW_2); 
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
    // InternalTestua.g:3679:1: rule__Bitwise__Group__0 : rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1 ;
    public final void rule__Bitwise__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3683:1: ( rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1 )
            // InternalTestua.g:3684:2: rule__Bitwise__Group__0__Impl rule__Bitwise__Group__1
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
    // InternalTestua.g:3691:1: rule__Bitwise__Group__0__Impl : ( ruleUnary ) ;
    public final void rule__Bitwise__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3695:1: ( ( ruleUnary ) )
            // InternalTestua.g:3696:1: ( ruleUnary )
            {
            // InternalTestua.g:3696:1: ( ruleUnary )
            // InternalTestua.g:3697:2: ruleUnary
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
    // InternalTestua.g:3706:1: rule__Bitwise__Group__1 : rule__Bitwise__Group__1__Impl ;
    public final void rule__Bitwise__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3710:1: ( rule__Bitwise__Group__1__Impl )
            // InternalTestua.g:3711:2: rule__Bitwise__Group__1__Impl
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
    // InternalTestua.g:3717:1: rule__Bitwise__Group__1__Impl : ( ( rule__Bitwise__Group_1__0 )* ) ;
    public final void rule__Bitwise__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3721:1: ( ( ( rule__Bitwise__Group_1__0 )* ) )
            // InternalTestua.g:3722:1: ( ( rule__Bitwise__Group_1__0 )* )
            {
            // InternalTestua.g:3722:1: ( ( rule__Bitwise__Group_1__0 )* )
            // InternalTestua.g:3723:2: ( rule__Bitwise__Group_1__0 )*
            {
             before(grammarAccess.getBitwiseAccess().getGroup_1()); 
            // InternalTestua.g:3724:2: ( rule__Bitwise__Group_1__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( ((LA39_0>=7 && LA39_0<=11)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalTestua.g:3724:3: rule__Bitwise__Group_1__0
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
    // InternalTestua.g:3733:1: rule__Bitwise__Group_1__0 : rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1 ;
    public final void rule__Bitwise__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3737:1: ( rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1 )
            // InternalTestua.g:3738:2: rule__Bitwise__Group_1__0__Impl rule__Bitwise__Group_1__1
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
    // InternalTestua.g:3745:1: rule__Bitwise__Group_1__0__Impl : ( () ) ;
    public final void rule__Bitwise__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3749:1: ( ( () ) )
            // InternalTestua.g:3750:1: ( () )
            {
            // InternalTestua.g:3750:1: ( () )
            // InternalTestua.g:3751:2: ()
            {
             before(grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0()); 
            // InternalTestua.g:3752:2: ()
            // InternalTestua.g:3752:3: 
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
    // InternalTestua.g:3760:1: rule__Bitwise__Group_1__1 : rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2 ;
    public final void rule__Bitwise__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3764:1: ( rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2 )
            // InternalTestua.g:3765:2: rule__Bitwise__Group_1__1__Impl rule__Bitwise__Group_1__2
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
    // InternalTestua.g:3772:1: rule__Bitwise__Group_1__1__Impl : ( ( rule__Bitwise__OpAssignment_1_1 ) ) ;
    public final void rule__Bitwise__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3776:1: ( ( ( rule__Bitwise__OpAssignment_1_1 ) ) )
            // InternalTestua.g:3777:1: ( ( rule__Bitwise__OpAssignment_1_1 ) )
            {
            // InternalTestua.g:3777:1: ( ( rule__Bitwise__OpAssignment_1_1 ) )
            // InternalTestua.g:3778:2: ( rule__Bitwise__OpAssignment_1_1 )
            {
             before(grammarAccess.getBitwiseAccess().getOpAssignment_1_1()); 
            // InternalTestua.g:3779:2: ( rule__Bitwise__OpAssignment_1_1 )
            // InternalTestua.g:3779:3: rule__Bitwise__OpAssignment_1_1
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
    // InternalTestua.g:3787:1: rule__Bitwise__Group_1__2 : rule__Bitwise__Group_1__2__Impl ;
    public final void rule__Bitwise__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3791:1: ( rule__Bitwise__Group_1__2__Impl )
            // InternalTestua.g:3792:2: rule__Bitwise__Group_1__2__Impl
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
    // InternalTestua.g:3798:1: rule__Bitwise__Group_1__2__Impl : ( ( rule__Bitwise__RightAssignment_1_2 ) ) ;
    public final void rule__Bitwise__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3802:1: ( ( ( rule__Bitwise__RightAssignment_1_2 ) ) )
            // InternalTestua.g:3803:1: ( ( rule__Bitwise__RightAssignment_1_2 ) )
            {
            // InternalTestua.g:3803:1: ( ( rule__Bitwise__RightAssignment_1_2 ) )
            // InternalTestua.g:3804:2: ( rule__Bitwise__RightAssignment_1_2 )
            {
             before(grammarAccess.getBitwiseAccess().getRightAssignment_1_2()); 
            // InternalTestua.g:3805:2: ( rule__Bitwise__RightAssignment_1_2 )
            // InternalTestua.g:3805:3: rule__Bitwise__RightAssignment_1_2
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
    // InternalTestua.g:3814:1: rule__Unary__Group_0__0 : rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1 ;
    public final void rule__Unary__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3818:1: ( rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1 )
            // InternalTestua.g:3819:2: rule__Unary__Group_0__0__Impl rule__Unary__Group_0__1
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
    // InternalTestua.g:3826:1: rule__Unary__Group_0__0__Impl : ( () ) ;
    public final void rule__Unary__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3830:1: ( ( () ) )
            // InternalTestua.g:3831:1: ( () )
            {
            // InternalTestua.g:3831:1: ( () )
            // InternalTestua.g:3832:2: ()
            {
             before(grammarAccess.getUnaryAccess().getBitwiseNegateAction_0_0()); 
            // InternalTestua.g:3833:2: ()
            // InternalTestua.g:3833:3: 
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
    // InternalTestua.g:3841:1: rule__Unary__Group_0__1 : rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2 ;
    public final void rule__Unary__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3845:1: ( rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2 )
            // InternalTestua.g:3846:2: rule__Unary__Group_0__1__Impl rule__Unary__Group_0__2
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
    // InternalTestua.g:3853:1: rule__Unary__Group_0__1__Impl : ( '~' ) ;
    public final void rule__Unary__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3857:1: ( ( '~' ) )
            // InternalTestua.g:3858:1: ( '~' )
            {
            // InternalTestua.g:3858:1: ( '~' )
            // InternalTestua.g:3859:2: '~'
            {
             before(grammarAccess.getUnaryAccess().getTildeKeyword_0_1()); 
            match(input,53,FOLLOW_2); 
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
    // InternalTestua.g:3868:1: rule__Unary__Group_0__2 : rule__Unary__Group_0__2__Impl ;
    public final void rule__Unary__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3872:1: ( rule__Unary__Group_0__2__Impl )
            // InternalTestua.g:3873:2: rule__Unary__Group_0__2__Impl
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
    // InternalTestua.g:3879:1: rule__Unary__Group_0__2__Impl : ( ( rule__Unary__ExpressionAssignment_0_2 ) ) ;
    public final void rule__Unary__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3883:1: ( ( ( rule__Unary__ExpressionAssignment_0_2 ) ) )
            // InternalTestua.g:3884:1: ( ( rule__Unary__ExpressionAssignment_0_2 ) )
            {
            // InternalTestua.g:3884:1: ( ( rule__Unary__ExpressionAssignment_0_2 ) )
            // InternalTestua.g:3885:2: ( rule__Unary__ExpressionAssignment_0_2 )
            {
             before(grammarAccess.getUnaryAccess().getExpressionAssignment_0_2()); 
            // InternalTestua.g:3886:2: ( rule__Unary__ExpressionAssignment_0_2 )
            // InternalTestua.g:3886:3: rule__Unary__ExpressionAssignment_0_2
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
    // InternalTestua.g:3895:1: rule__Unary__Group_1__0 : rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1 ;
    public final void rule__Unary__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3899:1: ( rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1 )
            // InternalTestua.g:3900:2: rule__Unary__Group_1__0__Impl rule__Unary__Group_1__1
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
    // InternalTestua.g:3907:1: rule__Unary__Group_1__0__Impl : ( rulePrimary ) ;
    public final void rule__Unary__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3911:1: ( ( rulePrimary ) )
            // InternalTestua.g:3912:1: ( rulePrimary )
            {
            // InternalTestua.g:3912:1: ( rulePrimary )
            // InternalTestua.g:3913:2: rulePrimary
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
    // InternalTestua.g:3922:1: rule__Unary__Group_1__1 : rule__Unary__Group_1__1__Impl ;
    public final void rule__Unary__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3926:1: ( rule__Unary__Group_1__1__Impl )
            // InternalTestua.g:3927:2: rule__Unary__Group_1__1__Impl
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
    // InternalTestua.g:3933:1: rule__Unary__Group_1__1__Impl : ( ( rule__Unary__Alternatives_1_1 )? ) ;
    public final void rule__Unary__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3937:1: ( ( ( rule__Unary__Alternatives_1_1 )? ) )
            // InternalTestua.g:3938:1: ( ( rule__Unary__Alternatives_1_1 )? )
            {
            // InternalTestua.g:3938:1: ( ( rule__Unary__Alternatives_1_1 )? )
            // InternalTestua.g:3939:2: ( rule__Unary__Alternatives_1_1 )?
            {
             before(grammarAccess.getUnaryAccess().getAlternatives_1_1()); 
            // InternalTestua.g:3940:2: ( rule__Unary__Alternatives_1_1 )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=54 && LA40_0<=55)) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalTestua.g:3940:3: rule__Unary__Alternatives_1_1
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
    // InternalTestua.g:3949:1: rule__Unary__Group_1_1_0__0 : rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1 ;
    public final void rule__Unary__Group_1_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3953:1: ( rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1 )
            // InternalTestua.g:3954:2: rule__Unary__Group_1_1_0__0__Impl rule__Unary__Group_1_1_0__1
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
    // InternalTestua.g:3961:1: rule__Unary__Group_1_1_0__0__Impl : ( () ) ;
    public final void rule__Unary__Group_1_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3965:1: ( ( () ) )
            // InternalTestua.g:3966:1: ( () )
            {
            // InternalTestua.g:3966:1: ( () )
            // InternalTestua.g:3967:2: ()
            {
             before(grammarAccess.getUnaryAccess().getIncrementExpressionAction_1_1_0_0()); 
            // InternalTestua.g:3968:2: ()
            // InternalTestua.g:3968:3: 
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
    // InternalTestua.g:3976:1: rule__Unary__Group_1_1_0__1 : rule__Unary__Group_1_1_0__1__Impl ;
    public final void rule__Unary__Group_1_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3980:1: ( rule__Unary__Group_1_1_0__1__Impl )
            // InternalTestua.g:3981:2: rule__Unary__Group_1_1_0__1__Impl
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
    // InternalTestua.g:3987:1: rule__Unary__Group_1_1_0__1__Impl : ( '++' ) ;
    public final void rule__Unary__Group_1_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:3991:1: ( ( '++' ) )
            // InternalTestua.g:3992:1: ( '++' )
            {
            // InternalTestua.g:3992:1: ( '++' )
            // InternalTestua.g:3993:2: '++'
            {
             before(grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_1_0_1()); 
            match(input,54,FOLLOW_2); 
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
    // InternalTestua.g:4003:1: rule__Unary__Group_1_1_1__0 : rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1 ;
    public final void rule__Unary__Group_1_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4007:1: ( rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1 )
            // InternalTestua.g:4008:2: rule__Unary__Group_1_1_1__0__Impl rule__Unary__Group_1_1_1__1
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
    // InternalTestua.g:4015:1: rule__Unary__Group_1_1_1__0__Impl : ( () ) ;
    public final void rule__Unary__Group_1_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4019:1: ( ( () ) )
            // InternalTestua.g:4020:1: ( () )
            {
            // InternalTestua.g:4020:1: ( () )
            // InternalTestua.g:4021:2: ()
            {
             before(grammarAccess.getUnaryAccess().getDecrementExpressionAction_1_1_1_0()); 
            // InternalTestua.g:4022:2: ()
            // InternalTestua.g:4022:3: 
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
    // InternalTestua.g:4030:1: rule__Unary__Group_1_1_1__1 : rule__Unary__Group_1_1_1__1__Impl ;
    public final void rule__Unary__Group_1_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4034:1: ( rule__Unary__Group_1_1_1__1__Impl )
            // InternalTestua.g:4035:2: rule__Unary__Group_1_1_1__1__Impl
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
    // InternalTestua.g:4041:1: rule__Unary__Group_1_1_1__1__Impl : ( '--' ) ;
    public final void rule__Unary__Group_1_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4045:1: ( ( '--' ) )
            // InternalTestua.g:4046:1: ( '--' )
            {
            // InternalTestua.g:4046:1: ( '--' )
            // InternalTestua.g:4047:2: '--'
            {
             before(grammarAccess.getUnaryAccess().getHyphenMinusHyphenMinusKeyword_1_1_1_1()); 
            match(input,55,FOLLOW_2); 
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
    // InternalTestua.g:4057:1: rule__Primary__Group_2__0 : rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1 ;
    public final void rule__Primary__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4061:1: ( rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1 )
            // InternalTestua.g:4062:2: rule__Primary__Group_2__0__Impl rule__Primary__Group_2__1
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
    // InternalTestua.g:4069:1: rule__Primary__Group_2__0__Impl : ( () ) ;
    public final void rule__Primary__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4073:1: ( ( () ) )
            // InternalTestua.g:4074:1: ( () )
            {
            // InternalTestua.g:4074:1: ( () )
            // InternalTestua.g:4075:2: ()
            {
             before(grammarAccess.getPrimaryAccess().getBracketsAction_2_0()); 
            // InternalTestua.g:4076:2: ()
            // InternalTestua.g:4076:3: 
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
    // InternalTestua.g:4084:1: rule__Primary__Group_2__1 : rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2 ;
    public final void rule__Primary__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4088:1: ( rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2 )
            // InternalTestua.g:4089:2: rule__Primary__Group_2__1__Impl rule__Primary__Group_2__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalTestua.g:4096:1: rule__Primary__Group_2__1__Impl : ( '(' ) ;
    public final void rule__Primary__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4100:1: ( ( '(' ) )
            // InternalTestua.g:4101:1: ( '(' )
            {
            // InternalTestua.g:4101:1: ( '(' )
            // InternalTestua.g:4102:2: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_2_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalTestua.g:4111:1: rule__Primary__Group_2__2 : rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3 ;
    public final void rule__Primary__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4115:1: ( rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3 )
            // InternalTestua.g:4116:2: rule__Primary__Group_2__2__Impl rule__Primary__Group_2__3
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
    // InternalTestua.g:4123:1: rule__Primary__Group_2__2__Impl : ( ( rule__Primary__ExpressionAssignment_2_2 ) ) ;
    public final void rule__Primary__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4127:1: ( ( ( rule__Primary__ExpressionAssignment_2_2 ) ) )
            // InternalTestua.g:4128:1: ( ( rule__Primary__ExpressionAssignment_2_2 ) )
            {
            // InternalTestua.g:4128:1: ( ( rule__Primary__ExpressionAssignment_2_2 ) )
            // InternalTestua.g:4129:2: ( rule__Primary__ExpressionAssignment_2_2 )
            {
             before(grammarAccess.getPrimaryAccess().getExpressionAssignment_2_2()); 
            // InternalTestua.g:4130:2: ( rule__Primary__ExpressionAssignment_2_2 )
            // InternalTestua.g:4130:3: rule__Primary__ExpressionAssignment_2_2
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
    // InternalTestua.g:4138:1: rule__Primary__Group_2__3 : rule__Primary__Group_2__3__Impl ;
    public final void rule__Primary__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4142:1: ( rule__Primary__Group_2__3__Impl )
            // InternalTestua.g:4143:2: rule__Primary__Group_2__3__Impl
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
    // InternalTestua.g:4149:1: rule__Primary__Group_2__3__Impl : ( ')' ) ;
    public final void rule__Primary__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4153:1: ( ( ')' ) )
            // InternalTestua.g:4154:1: ( ')' )
            {
            // InternalTestua.g:4154:1: ( ')' )
            // InternalTestua.g:4155:2: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_2_3()); 
            match(input,43,FOLLOW_2); 
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
    // InternalTestua.g:4165:1: rule__Game__EntitiesAssignment_0 : ( ruleEntity ) ;
    public final void rule__Game__EntitiesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4169:1: ( ( ruleEntity ) )
            // InternalTestua.g:4170:2: ( ruleEntity )
            {
            // InternalTestua.g:4170:2: ( ruleEntity )
            // InternalTestua.g:4171:3: ruleEntity
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
    // InternalTestua.g:4180:1: rule__Game__SystemsAssignment_1 : ( ruleSystem ) ;
    public final void rule__Game__SystemsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4184:1: ( ( ruleSystem ) )
            // InternalTestua.g:4185:2: ( ruleSystem )
            {
            // InternalTestua.g:4185:2: ( ruleSystem )
            // InternalTestua.g:4186:3: ruleSystem
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
    // InternalTestua.g:4195:1: rule__Entity__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Entity__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4199:1: ( ( RULE_ID ) )
            // InternalTestua.g:4200:2: ( RULE_ID )
            {
            // InternalTestua.g:4200:2: ( RULE_ID )
            // InternalTestua.g:4201:3: RULE_ID
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
    // InternalTestua.g:4210:1: rule__Entity__BaseAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__Entity__BaseAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4214:1: ( ( RULE_ID ) )
            // InternalTestua.g:4215:2: ( RULE_ID )
            {
            // InternalTestua.g:4215:2: ( RULE_ID )
            // InternalTestua.g:4216:3: RULE_ID
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
    // InternalTestua.g:4225:1: rule__Entity__ComponentsAssignment_3_0 : ( ruleComponent ) ;
    public final void rule__Entity__ComponentsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4229:1: ( ( ruleComponent ) )
            // InternalTestua.g:4230:2: ( ruleComponent )
            {
            // InternalTestua.g:4230:2: ( ruleComponent )
            // InternalTestua.g:4231:3: ruleComponent
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
    // InternalTestua.g:4240:1: rule__Entity__ComponentsAssignment_3_1_1 : ( ruleComponent ) ;
    public final void rule__Entity__ComponentsAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4244:1: ( ( ruleComponent ) )
            // InternalTestua.g:4245:2: ( ruleComponent )
            {
            // InternalTestua.g:4245:2: ( ruleComponent )
            // InternalTestua.g:4246:3: ruleComponent
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
    // InternalTestua.g:4255:1: rule__Entity__EntitiesAssignment_5_1 : ( ruleEntity ) ;
    public final void rule__Entity__EntitiesAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4259:1: ( ( ruleEntity ) )
            // InternalTestua.g:4260:2: ( ruleEntity )
            {
            // InternalTestua.g:4260:2: ( ruleEntity )
            // InternalTestua.g:4261:3: ruleEntity
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
    // InternalTestua.g:4270:1: rule__Component__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Component__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4274:1: ( ( RULE_ID ) )
            // InternalTestua.g:4275:2: ( RULE_ID )
            {
            // InternalTestua.g:4275:2: ( RULE_ID )
            // InternalTestua.g:4276:3: RULE_ID
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
    // InternalTestua.g:4285:1: rule__Component__ValueAssignment_1 : ( ruleValue ) ;
    public final void rule__Component__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4289:1: ( ( ruleValue ) )
            // InternalTestua.g:4290:2: ( ruleValue )
            {
            // InternalTestua.g:4290:2: ( ruleValue )
            // InternalTestua.g:4291:3: ruleValue
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
    // InternalTestua.g:4300:1: rule__Vector__NumbersAssignment : ( RULE_REAL ) ;
    public final void rule__Vector__NumbersAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4304:1: ( ( RULE_REAL ) )
            // InternalTestua.g:4305:2: ( RULE_REAL )
            {
            // InternalTestua.g:4305:2: ( RULE_REAL )
            // InternalTestua.g:4306:3: RULE_REAL
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
    // InternalTestua.g:4315:1: rule__Word__ValuesAssignment : ( RULE_ID ) ;
    public final void rule__Word__ValuesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4319:1: ( ( RULE_ID ) )
            // InternalTestua.g:4320:2: ( RULE_ID )
            {
            // InternalTestua.g:4320:2: ( RULE_ID )
            // InternalTestua.g:4321:3: RULE_ID
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
    // InternalTestua.g:4330:1: rule__System__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__System__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4334:1: ( ( RULE_ID ) )
            // InternalTestua.g:4335:2: ( RULE_ID )
            {
            // InternalTestua.g:4335:2: ( RULE_ID )
            // InternalTestua.g:4336:3: RULE_ID
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
    // InternalTestua.g:4345:1: rule__System__CommandsAssignment_2 : ( ruleCommand ) ;
    public final void rule__System__CommandsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4349:1: ( ( ruleCommand ) )
            // InternalTestua.g:4350:2: ( ruleCommand )
            {
            // InternalTestua.g:4350:2: ( ruleCommand )
            // InternalTestua.g:4351:3: ruleCommand
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


    // $ANTLR start "rule__Loop__EntityAssignment_1"
    // InternalTestua.g:4360:1: rule__Loop__EntityAssignment_1 : ( RULE_ID ) ;
    public final void rule__Loop__EntityAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4364:1: ( ( RULE_ID ) )
            // InternalTestua.g:4365:2: ( RULE_ID )
            {
            // InternalTestua.g:4365:2: ( RULE_ID )
            // InternalTestua.g:4366:3: RULE_ID
            {
             before(grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Loop__EntityAssignment_1"


    // $ANTLR start "rule__Loop__ConstraintsAssignment_3_3"
    // InternalTestua.g:4375:1: rule__Loop__ConstraintsAssignment_3_3 : ( RULE_ID ) ;
    public final void rule__Loop__ConstraintsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4379:1: ( ( RULE_ID ) )
            // InternalTestua.g:4380:2: ( RULE_ID )
            {
            // InternalTestua.g:4380:2: ( RULE_ID )
            // InternalTestua.g:4381:3: RULE_ID
            {
             before(grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_3_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_3_3_0()); 

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
    // $ANTLR end "rule__Loop__ConstraintsAssignment_3_3"


    // $ANTLR start "rule__Loop__CommandsAssignment_5"
    // InternalTestua.g:4390:1: rule__Loop__CommandsAssignment_5 : ( ruleCommand ) ;
    public final void rule__Loop__CommandsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4394:1: ( ( ruleCommand ) )
            // InternalTestua.g:4395:2: ( ruleCommand )
            {
            // InternalTestua.g:4395:2: ( ruleCommand )
            // InternalTestua.g:4396:3: ruleCommand
            {
             before(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Loop__CommandsAssignment_5"


    // $ANTLR start "rule__Branch__IfAssignment_2"
    // InternalTestua.g:4405:1: rule__Branch__IfAssignment_2 : ( ruleCondition ) ;
    public final void rule__Branch__IfAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4409:1: ( ( ruleCondition ) )
            // InternalTestua.g:4410:2: ( ruleCondition )
            {
            // InternalTestua.g:4410:2: ( ruleCondition )
            // InternalTestua.g:4411:3: ruleCondition
            {
             before(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Branch__IfAssignment_2"


    // $ANTLR start "rule__Branch__ElseIfsAssignment_3_1"
    // InternalTestua.g:4420:1: rule__Branch__ElseIfsAssignment_3_1 : ( ruleCondition ) ;
    public final void rule__Branch__ElseIfsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4424:1: ( ( ruleCondition ) )
            // InternalTestua.g:4425:2: ( ruleCondition )
            {
            // InternalTestua.g:4425:2: ( ruleCondition )
            // InternalTestua.g:4426:3: ruleCondition
            {
             before(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCondition();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__Branch__ElseIfsAssignment_3_1"


    // $ANTLR start "rule__Branch__CommandsAssignment_4_2"
    // InternalTestua.g:4435:1: rule__Branch__CommandsAssignment_4_2 : ( ruleCommand ) ;
    public final void rule__Branch__CommandsAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4439:1: ( ( ruleCommand ) )
            // InternalTestua.g:4440:2: ( ruleCommand )
            {
            // InternalTestua.g:4440:2: ( ruleCommand )
            // InternalTestua.g:4441:3: ruleCommand
            {
             before(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCommand();

            state._fsp--;

             after(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_4_2_0()); 

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
    // $ANTLR end "rule__Branch__CommandsAssignment_4_2"


    // $ANTLR start "rule__Condition__ConditionAssignment_0"
    // InternalTestua.g:4450:1: rule__Condition__ConditionAssignment_0 : ( ruleExpression ) ;
    public final void rule__Condition__ConditionAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4454:1: ( ( ruleExpression ) )
            // InternalTestua.g:4455:2: ( ruleExpression )
            {
            // InternalTestua.g:4455:2: ( ruleExpression )
            // InternalTestua.g:4456:3: ruleExpression
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
    // InternalTestua.g:4465:1: rule__Condition__CommandsAssignment_2 : ( ruleCommand ) ;
    public final void rule__Condition__CommandsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4469:1: ( ( ruleCommand ) )
            // InternalTestua.g:4470:2: ( ruleCommand )
            {
            // InternalTestua.g:4470:2: ( ruleCommand )
            // InternalTestua.g:4471:3: ruleCommand
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
    // InternalTestua.g:4480:1: rule__Assignment__AccessAssignment_0 : ( ruleAccess ) ;
    public final void rule__Assignment__AccessAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4484:1: ( ( ruleAccess ) )
            // InternalTestua.g:4485:2: ( ruleAccess )
            {
            // InternalTestua.g:4485:2: ( ruleAccess )
            // InternalTestua.g:4486:3: ruleAccess
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
    // InternalTestua.g:4495:1: rule__Assignment__TypeAssignment_1 : ( ruleAssignmentType ) ;
    public final void rule__Assignment__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4499:1: ( ( ruleAssignmentType ) )
            // InternalTestua.g:4500:2: ( ruleAssignmentType )
            {
            // InternalTestua.g:4500:2: ( ruleAssignmentType )
            // InternalTestua.g:4501:3: ruleAssignmentType
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
    // InternalTestua.g:4510:1: rule__Assignment__ExpressionAssignment_2 : ( ruleExpression ) ;
    public final void rule__Assignment__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4514:1: ( ( ruleExpression ) )
            // InternalTestua.g:4515:2: ( ruleExpression )
            {
            // InternalTestua.g:4515:2: ( ruleExpression )
            // InternalTestua.g:4516:3: ruleExpression
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
    // InternalTestua.g:4525:1: rule__Call__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Call__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4529:1: ( ( RULE_ID ) )
            // InternalTestua.g:4530:2: ( RULE_ID )
            {
            // InternalTestua.g:4530:2: ( RULE_ID )
            // InternalTestua.g:4531:3: RULE_ID
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
    // InternalTestua.g:4540:1: rule__Call__ParametersAssignment_2 : ( ruleExpression ) ;
    public final void rule__Call__ParametersAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4544:1: ( ( ruleExpression ) )
            // InternalTestua.g:4545:2: ( ruleExpression )
            {
            // InternalTestua.g:4545:2: ( ruleExpression )
            // InternalTestua.g:4546:3: ruleExpression
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
    // InternalTestua.g:4555:1: rule__Call__ParametersAssignment_3_1 : ( ruleExpression ) ;
    public final void rule__Call__ParametersAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4559:1: ( ( ruleExpression ) )
            // InternalTestua.g:4560:2: ( ruleExpression )
            {
            // InternalTestua.g:4560:2: ( ruleExpression )
            // InternalTestua.g:4561:3: ruleExpression
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
    // InternalTestua.g:4570:1: rule__Access__NamesAssignment_0 : ( RULE_ID ) ;
    public final void rule__Access__NamesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4574:1: ( ( RULE_ID ) )
            // InternalTestua.g:4575:2: ( RULE_ID )
            {
            // InternalTestua.g:4575:2: ( RULE_ID )
            // InternalTestua.g:4576:3: RULE_ID
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
    // InternalTestua.g:4585:1: rule__Access__NamesAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__Access__NamesAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4589:1: ( ( RULE_ID ) )
            // InternalTestua.g:4590:2: ( RULE_ID )
            {
            // InternalTestua.g:4590:2: ( RULE_ID )
            // InternalTestua.g:4591:3: RULE_ID
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
    // InternalTestua.g:4600:1: rule__Expression__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Expression__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4604:1: ( ( ruleAnd ) )
            // InternalTestua.g:4605:2: ( ruleAnd )
            {
            // InternalTestua.g:4605:2: ( ruleAnd )
            // InternalTestua.g:4606:3: ruleAnd
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
    // InternalTestua.g:4615:1: rule__And__RightAssignment_1_2 : ( rulePrimaryB ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4619:1: ( ( rulePrimaryB ) )
            // InternalTestua.g:4620:2: ( rulePrimaryB )
            {
            // InternalTestua.g:4620:2: ( rulePrimaryB )
            // InternalTestua.g:4621:3: rulePrimaryB
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
    // InternalTestua.g:4630:1: rule__PrimaryB__ExpressionAssignment_1_2 : ( ruleAtomicB ) ;
    public final void rule__PrimaryB__ExpressionAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4634:1: ( ( ruleAtomicB ) )
            // InternalTestua.g:4635:2: ( ruleAtomicB )
            {
            // InternalTestua.g:4635:2: ( ruleAtomicB )
            // InternalTestua.g:4636:3: ruleAtomicB
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
    // InternalTestua.g:4645:1: rule__AtomicB__TypeAssignment_1_1 : ( ruleRelationType ) ;
    public final void rule__AtomicB__TypeAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4649:1: ( ( ruleRelationType ) )
            // InternalTestua.g:4650:2: ( ruleRelationType )
            {
            // InternalTestua.g:4650:2: ( ruleRelationType )
            // InternalTestua.g:4651:3: ruleRelationType
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
    // InternalTestua.g:4660:1: rule__AtomicB__RightAssignment_1_2 : ( ruleAddition ) ;
    public final void rule__AtomicB__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4664:1: ( ( ruleAddition ) )
            // InternalTestua.g:4665:2: ( ruleAddition )
            {
            // InternalTestua.g:4665:2: ( ruleAddition )
            // InternalTestua.g:4666:3: ruleAddition
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
    // InternalTestua.g:4675:1: rule__Addition__RightAssignment_1_1 : ( ruleMultiplication ) ;
    public final void rule__Addition__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4679:1: ( ( ruleMultiplication ) )
            // InternalTestua.g:4680:2: ( ruleMultiplication )
            {
            // InternalTestua.g:4680:2: ( ruleMultiplication )
            // InternalTestua.g:4681:3: ruleMultiplication
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
    // InternalTestua.g:4690:1: rule__Multiplication__RightAssignment_1_1 : ( ruleBitwise ) ;
    public final void rule__Multiplication__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4694:1: ( ( ruleBitwise ) )
            // InternalTestua.g:4695:2: ( ruleBitwise )
            {
            // InternalTestua.g:4695:2: ( ruleBitwise )
            // InternalTestua.g:4696:3: ruleBitwise
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
    // InternalTestua.g:4705:1: rule__Bitwise__OpAssignment_1_1 : ( ( rule__Bitwise__OpAlternatives_1_1_0 ) ) ;
    public final void rule__Bitwise__OpAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4709:1: ( ( ( rule__Bitwise__OpAlternatives_1_1_0 ) ) )
            // InternalTestua.g:4710:2: ( ( rule__Bitwise__OpAlternatives_1_1_0 ) )
            {
            // InternalTestua.g:4710:2: ( ( rule__Bitwise__OpAlternatives_1_1_0 ) )
            // InternalTestua.g:4711:3: ( rule__Bitwise__OpAlternatives_1_1_0 )
            {
             before(grammarAccess.getBitwiseAccess().getOpAlternatives_1_1_0()); 
            // InternalTestua.g:4712:3: ( rule__Bitwise__OpAlternatives_1_1_0 )
            // InternalTestua.g:4712:4: rule__Bitwise__OpAlternatives_1_1_0
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
    // InternalTestua.g:4720:1: rule__Bitwise__RightAssignment_1_2 : ( ruleUnary ) ;
    public final void rule__Bitwise__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4724:1: ( ( ruleUnary ) )
            // InternalTestua.g:4725:2: ( ruleUnary )
            {
            // InternalTestua.g:4725:2: ( ruleUnary )
            // InternalTestua.g:4726:3: ruleUnary
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
    // InternalTestua.g:4735:1: rule__Unary__ExpressionAssignment_0_2 : ( rulePrimary ) ;
    public final void rule__Unary__ExpressionAssignment_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4739:1: ( ( rulePrimary ) )
            // InternalTestua.g:4740:2: ( rulePrimary )
            {
            // InternalTestua.g:4740:2: ( rulePrimary )
            // InternalTestua.g:4741:3: rulePrimary
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
    // InternalTestua.g:4750:1: rule__Primary__ExpressionAssignment_2_2 : ( ruleExpression ) ;
    public final void rule__Primary__ExpressionAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTestua.g:4754:1: ( ( ruleExpression ) )
            // InternalTestua.g:4755:2: ( ruleExpression )
            {
            // InternalTestua.g:4755:2: ( ruleExpression )
            // InternalTestua.g:4756:3: ruleExpression
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
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000001010000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0020844400000010L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000030000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000004420000010L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000000FFC4000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000080080000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0020044400000010L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000000003F000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x001C000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x001C000000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000F80L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000F82L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x00C0000000000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000080000000000L});

}