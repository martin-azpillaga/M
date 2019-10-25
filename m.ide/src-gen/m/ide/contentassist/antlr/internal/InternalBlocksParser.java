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
import m.services.BlocksGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBlocksParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_N", "RULE_WHITE_SPACE", "RULE_ID", "RULE_STRING", "'/>'", "'<'", "'>'", "'</'", "'='"
    };
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int RULE_ID=6;
    public static final int RULE_STRING=7;
    public static final int RULE_N=4;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int EOF=-1;
    public static final int RULE_WHITE_SPACE=5;
    public static final int T__10=10;

    // delegates
    // delegators


        public InternalBlocksParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBlocksParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBlocksParser.tokenNames; }
    public String getGrammarFileName() { return "InternalBlocks.g"; }


    	private BlocksGrammarAccess grammarAccess;

    	public void setGrammarAccess(BlocksGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleFile"
    // InternalBlocks.g:53:1: entryRuleFile : ruleFile EOF ;
    public final void entryRuleFile() throws RecognitionException {
        try {
            // InternalBlocks.g:54:1: ( ruleFile EOF )
            // InternalBlocks.g:55:1: ruleFile EOF
            {
             before(grammarAccess.getFileRule()); 
            pushFollow(FOLLOW_1);
            ruleFile();

            state._fsp--;

             after(grammarAccess.getFileRule()); 
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
    // $ANTLR end "entryRuleFile"


    // $ANTLR start "ruleFile"
    // InternalBlocks.g:62:1: ruleFile : ( ( rule__File__ElementsAssignment ) ) ;
    public final void ruleFile() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:66:2: ( ( ( rule__File__ElementsAssignment ) ) )
            // InternalBlocks.g:67:2: ( ( rule__File__ElementsAssignment ) )
            {
            // InternalBlocks.g:67:2: ( ( rule__File__ElementsAssignment ) )
            // InternalBlocks.g:68:3: ( rule__File__ElementsAssignment )
            {
             before(grammarAccess.getFileAccess().getElementsAssignment()); 
            // InternalBlocks.g:69:3: ( rule__File__ElementsAssignment )
            // InternalBlocks.g:69:4: rule__File__ElementsAssignment
            {
            pushFollow(FOLLOW_2);
            rule__File__ElementsAssignment();

            state._fsp--;


            }

             after(grammarAccess.getFileAccess().getElementsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFile"


    // $ANTLR start "entryRuleElement"
    // InternalBlocks.g:78:1: entryRuleElement : ruleElement EOF ;
    public final void entryRuleElement() throws RecognitionException {
        try {
            // InternalBlocks.g:79:1: ( ruleElement EOF )
            // InternalBlocks.g:80:1: ruleElement EOF
            {
             before(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_1);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getElementRule()); 
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
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // InternalBlocks.g:87:1: ruleElement : ( ( rule__Element__Group__0 ) ) ;
    public final void ruleElement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:91:2: ( ( ( rule__Element__Group__0 ) ) )
            // InternalBlocks.g:92:2: ( ( rule__Element__Group__0 ) )
            {
            // InternalBlocks.g:92:2: ( ( rule__Element__Group__0 ) )
            // InternalBlocks.g:93:3: ( rule__Element__Group__0 )
            {
             before(grammarAccess.getElementAccess().getGroup()); 
            // InternalBlocks.g:94:3: ( rule__Element__Group__0 )
            // InternalBlocks.g:94:4: rule__Element__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleAttribute"
    // InternalBlocks.g:103:1: entryRuleAttribute : ruleAttribute EOF ;
    public final void entryRuleAttribute() throws RecognitionException {
        try {
            // InternalBlocks.g:104:1: ( ruleAttribute EOF )
            // InternalBlocks.g:105:1: ruleAttribute EOF
            {
             before(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_1);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getAttributeRule()); 
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
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalBlocks.g:112:1: ruleAttribute : ( ( rule__Attribute__Group__0 ) ) ;
    public final void ruleAttribute() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:116:2: ( ( ( rule__Attribute__Group__0 ) ) )
            // InternalBlocks.g:117:2: ( ( rule__Attribute__Group__0 ) )
            {
            // InternalBlocks.g:117:2: ( ( rule__Attribute__Group__0 ) )
            // InternalBlocks.g:118:3: ( rule__Attribute__Group__0 )
            {
             before(grammarAccess.getAttributeAccess().getGroup()); 
            // InternalBlocks.g:119:3: ( rule__Attribute__Group__0 )
            // InternalBlocks.g:119:4: rule__Attribute__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleText"
    // InternalBlocks.g:128:1: entryRuleText : ruleText EOF ;
    public final void entryRuleText() throws RecognitionException {
        try {
            // InternalBlocks.g:129:1: ( ruleText EOF )
            // InternalBlocks.g:130:1: ruleText EOF
            {
             before(grammarAccess.getTextRule()); 
            pushFollow(FOLLOW_1);
            ruleText();

            state._fsp--;

             after(grammarAccess.getTextRule()); 
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
    // $ANTLR end "entryRuleText"


    // $ANTLR start "ruleText"
    // InternalBlocks.g:137:1: ruleText : ( ( ( rule__Text__Alternatives ) ) ( ( rule__Text__Alternatives )* ) ) ;
    public final void ruleText() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:141:2: ( ( ( ( rule__Text__Alternatives ) ) ( ( rule__Text__Alternatives )* ) ) )
            // InternalBlocks.g:142:2: ( ( ( rule__Text__Alternatives ) ) ( ( rule__Text__Alternatives )* ) )
            {
            // InternalBlocks.g:142:2: ( ( ( rule__Text__Alternatives ) ) ( ( rule__Text__Alternatives )* ) )
            // InternalBlocks.g:143:3: ( ( rule__Text__Alternatives ) ) ( ( rule__Text__Alternatives )* )
            {
            // InternalBlocks.g:143:3: ( ( rule__Text__Alternatives ) )
            // InternalBlocks.g:144:4: ( rule__Text__Alternatives )
            {
             before(grammarAccess.getTextAccess().getAlternatives()); 
            // InternalBlocks.g:145:4: ( rule__Text__Alternatives )
            // InternalBlocks.g:145:5: rule__Text__Alternatives
            {
            pushFollow(FOLLOW_3);
            rule__Text__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTextAccess().getAlternatives()); 

            }

            // InternalBlocks.g:148:3: ( ( rule__Text__Alternatives )* )
            // InternalBlocks.g:149:4: ( rule__Text__Alternatives )*
            {
             before(grammarAccess.getTextAccess().getAlternatives()); 
            // InternalBlocks.g:150:4: ( rule__Text__Alternatives )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_N && LA1_0<=RULE_ID)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalBlocks.g:150:5: rule__Text__Alternatives
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Text__Alternatives();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getTextAccess().getAlternatives()); 

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
    // $ANTLR end "ruleText"


    // $ANTLR start "rule__Element__Alternatives_3"
    // InternalBlocks.g:159:1: rule__Element__Alternatives_3 : ( ( '/>' ) | ( ( rule__Element__Group_3_1__0 ) ) );
    public final void rule__Element__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:163:1: ( ( '/>' ) | ( ( rule__Element__Group_3_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==8) ) {
                alt2=1;
            }
            else if ( (LA2_0==10) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalBlocks.g:164:2: ( '/>' )
                    {
                    // InternalBlocks.g:164:2: ( '/>' )
                    // InternalBlocks.g:165:3: '/>'
                    {
                     before(grammarAccess.getElementAccess().getSolidusGreaterThanSignKeyword_3_0()); 
                    match(input,8,FOLLOW_2); 
                     after(grammarAccess.getElementAccess().getSolidusGreaterThanSignKeyword_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalBlocks.g:170:2: ( ( rule__Element__Group_3_1__0 ) )
                    {
                    // InternalBlocks.g:170:2: ( ( rule__Element__Group_3_1__0 ) )
                    // InternalBlocks.g:171:3: ( rule__Element__Group_3_1__0 )
                    {
                     before(grammarAccess.getElementAccess().getGroup_3_1()); 
                    // InternalBlocks.g:172:3: ( rule__Element__Group_3_1__0 )
                    // InternalBlocks.g:172:4: rule__Element__Group_3_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Group_3_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__Element__Alternatives_3"


    // $ANTLR start "rule__Element__Alternatives_3_1_1"
    // InternalBlocks.g:180:1: rule__Element__Alternatives_3_1_1 : ( ( ( ( rule__Element__ElementsAssignment_3_1_1_0 ) ) ( ( rule__Element__ElementsAssignment_3_1_1_0 )* ) ) | ( ( rule__Element__TextAssignment_3_1_1_1 ) ) );
    public final void rule__Element__Alternatives_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:184:1: ( ( ( ( rule__Element__ElementsAssignment_3_1_1_0 ) ) ( ( rule__Element__ElementsAssignment_3_1_1_0 )* ) ) | ( ( rule__Element__TextAssignment_3_1_1_1 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==9) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=RULE_N && LA4_0<=RULE_ID)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalBlocks.g:185:2: ( ( ( rule__Element__ElementsAssignment_3_1_1_0 ) ) ( ( rule__Element__ElementsAssignment_3_1_1_0 )* ) )
                    {
                    // InternalBlocks.g:185:2: ( ( ( rule__Element__ElementsAssignment_3_1_1_0 ) ) ( ( rule__Element__ElementsAssignment_3_1_1_0 )* ) )
                    // InternalBlocks.g:186:3: ( ( rule__Element__ElementsAssignment_3_1_1_0 ) ) ( ( rule__Element__ElementsAssignment_3_1_1_0 )* )
                    {
                    // InternalBlocks.g:186:3: ( ( rule__Element__ElementsAssignment_3_1_1_0 ) )
                    // InternalBlocks.g:187:4: ( rule__Element__ElementsAssignment_3_1_1_0 )
                    {
                     before(grammarAccess.getElementAccess().getElementsAssignment_3_1_1_0()); 
                    // InternalBlocks.g:188:4: ( rule__Element__ElementsAssignment_3_1_1_0 )
                    // InternalBlocks.g:188:5: rule__Element__ElementsAssignment_3_1_1_0
                    {
                    pushFollow(FOLLOW_4);
                    rule__Element__ElementsAssignment_3_1_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getElementsAssignment_3_1_1_0()); 

                    }

                    // InternalBlocks.g:191:3: ( ( rule__Element__ElementsAssignment_3_1_1_0 )* )
                    // InternalBlocks.g:192:4: ( rule__Element__ElementsAssignment_3_1_1_0 )*
                    {
                     before(grammarAccess.getElementAccess().getElementsAssignment_3_1_1_0()); 
                    // InternalBlocks.g:193:4: ( rule__Element__ElementsAssignment_3_1_1_0 )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==9) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalBlocks.g:193:5: rule__Element__ElementsAssignment_3_1_1_0
                    	    {
                    	    pushFollow(FOLLOW_4);
                    	    rule__Element__ElementsAssignment_3_1_1_0();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                     after(grammarAccess.getElementAccess().getElementsAssignment_3_1_1_0()); 

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalBlocks.g:198:2: ( ( rule__Element__TextAssignment_3_1_1_1 ) )
                    {
                    // InternalBlocks.g:198:2: ( ( rule__Element__TextAssignment_3_1_1_1 ) )
                    // InternalBlocks.g:199:3: ( rule__Element__TextAssignment_3_1_1_1 )
                    {
                     before(grammarAccess.getElementAccess().getTextAssignment_3_1_1_1()); 
                    // InternalBlocks.g:200:3: ( rule__Element__TextAssignment_3_1_1_1 )
                    // InternalBlocks.g:200:4: rule__Element__TextAssignment_3_1_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__TextAssignment_3_1_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getTextAssignment_3_1_1_1()); 

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
    // $ANTLR end "rule__Element__Alternatives_3_1_1"


    // $ANTLR start "rule__Text__Alternatives"
    // InternalBlocks.g:208:1: rule__Text__Alternatives : ( ( RULE_N ) | ( RULE_WHITE_SPACE ) | ( RULE_ID ) );
    public final void rule__Text__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:212:1: ( ( RULE_N ) | ( RULE_WHITE_SPACE ) | ( RULE_ID ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case RULE_N:
                {
                alt5=1;
                }
                break;
            case RULE_WHITE_SPACE:
                {
                alt5=2;
                }
                break;
            case RULE_ID:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalBlocks.g:213:2: ( RULE_N )
                    {
                    // InternalBlocks.g:213:2: ( RULE_N )
                    // InternalBlocks.g:214:3: RULE_N
                    {
                     before(grammarAccess.getTextAccess().getNTerminalRuleCall_0()); 
                    match(input,RULE_N,FOLLOW_2); 
                     after(grammarAccess.getTextAccess().getNTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalBlocks.g:219:2: ( RULE_WHITE_SPACE )
                    {
                    // InternalBlocks.g:219:2: ( RULE_WHITE_SPACE )
                    // InternalBlocks.g:220:3: RULE_WHITE_SPACE
                    {
                     before(grammarAccess.getTextAccess().getWHITE_SPACETerminalRuleCall_1()); 
                    match(input,RULE_WHITE_SPACE,FOLLOW_2); 
                     after(grammarAccess.getTextAccess().getWHITE_SPACETerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalBlocks.g:225:2: ( RULE_ID )
                    {
                    // InternalBlocks.g:225:2: ( RULE_ID )
                    // InternalBlocks.g:226:3: RULE_ID
                    {
                     before(grammarAccess.getTextAccess().getIDTerminalRuleCall_2()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getTextAccess().getIDTerminalRuleCall_2()); 

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
    // $ANTLR end "rule__Text__Alternatives"


    // $ANTLR start "rule__Element__Group__0"
    // InternalBlocks.g:235:1: rule__Element__Group__0 : rule__Element__Group__0__Impl rule__Element__Group__1 ;
    public final void rule__Element__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:239:1: ( rule__Element__Group__0__Impl rule__Element__Group__1 )
            // InternalBlocks.g:240:2: rule__Element__Group__0__Impl rule__Element__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Element__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group__1();

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
    // $ANTLR end "rule__Element__Group__0"


    // $ANTLR start "rule__Element__Group__0__Impl"
    // InternalBlocks.g:247:1: rule__Element__Group__0__Impl : ( '<' ) ;
    public final void rule__Element__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:251:1: ( ( '<' ) )
            // InternalBlocks.g:252:1: ( '<' )
            {
            // InternalBlocks.g:252:1: ( '<' )
            // InternalBlocks.g:253:2: '<'
            {
             before(grammarAccess.getElementAccess().getLessThanSignKeyword_0()); 
            match(input,9,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getLessThanSignKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__0__Impl"


    // $ANTLR start "rule__Element__Group__1"
    // InternalBlocks.g:262:1: rule__Element__Group__1 : rule__Element__Group__1__Impl rule__Element__Group__2 ;
    public final void rule__Element__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:266:1: ( rule__Element__Group__1__Impl rule__Element__Group__2 )
            // InternalBlocks.g:267:2: rule__Element__Group__1__Impl rule__Element__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Element__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group__2();

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
    // $ANTLR end "rule__Element__Group__1"


    // $ANTLR start "rule__Element__Group__1__Impl"
    // InternalBlocks.g:274:1: rule__Element__Group__1__Impl : ( ( rule__Element__OpenAssignment_1 ) ) ;
    public final void rule__Element__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:278:1: ( ( ( rule__Element__OpenAssignment_1 ) ) )
            // InternalBlocks.g:279:1: ( ( rule__Element__OpenAssignment_1 ) )
            {
            // InternalBlocks.g:279:1: ( ( rule__Element__OpenAssignment_1 ) )
            // InternalBlocks.g:280:2: ( rule__Element__OpenAssignment_1 )
            {
             before(grammarAccess.getElementAccess().getOpenAssignment_1()); 
            // InternalBlocks.g:281:2: ( rule__Element__OpenAssignment_1 )
            // InternalBlocks.g:281:3: rule__Element__OpenAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Element__OpenAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getOpenAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__1__Impl"


    // $ANTLR start "rule__Element__Group__2"
    // InternalBlocks.g:289:1: rule__Element__Group__2 : rule__Element__Group__2__Impl rule__Element__Group__3 ;
    public final void rule__Element__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:293:1: ( rule__Element__Group__2__Impl rule__Element__Group__3 )
            // InternalBlocks.g:294:2: rule__Element__Group__2__Impl rule__Element__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Element__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group__3();

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
    // $ANTLR end "rule__Element__Group__2"


    // $ANTLR start "rule__Element__Group__2__Impl"
    // InternalBlocks.g:301:1: rule__Element__Group__2__Impl : ( ( rule__Element__AttributesAssignment_2 )* ) ;
    public final void rule__Element__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:305:1: ( ( ( rule__Element__AttributesAssignment_2 )* ) )
            // InternalBlocks.g:306:1: ( ( rule__Element__AttributesAssignment_2 )* )
            {
            // InternalBlocks.g:306:1: ( ( rule__Element__AttributesAssignment_2 )* )
            // InternalBlocks.g:307:2: ( rule__Element__AttributesAssignment_2 )*
            {
             before(grammarAccess.getElementAccess().getAttributesAssignment_2()); 
            // InternalBlocks.g:308:2: ( rule__Element__AttributesAssignment_2 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_ID) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalBlocks.g:308:3: rule__Element__AttributesAssignment_2
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Element__AttributesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getElementAccess().getAttributesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__2__Impl"


    // $ANTLR start "rule__Element__Group__3"
    // InternalBlocks.g:316:1: rule__Element__Group__3 : rule__Element__Group__3__Impl ;
    public final void rule__Element__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:320:1: ( rule__Element__Group__3__Impl )
            // InternalBlocks.g:321:2: rule__Element__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group__3__Impl();

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
    // $ANTLR end "rule__Element__Group__3"


    // $ANTLR start "rule__Element__Group__3__Impl"
    // InternalBlocks.g:327:1: rule__Element__Group__3__Impl : ( ( rule__Element__Alternatives_3 ) ) ;
    public final void rule__Element__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:331:1: ( ( ( rule__Element__Alternatives_3 ) ) )
            // InternalBlocks.g:332:1: ( ( rule__Element__Alternatives_3 ) )
            {
            // InternalBlocks.g:332:1: ( ( rule__Element__Alternatives_3 ) )
            // InternalBlocks.g:333:2: ( rule__Element__Alternatives_3 )
            {
             before(grammarAccess.getElementAccess().getAlternatives_3()); 
            // InternalBlocks.g:334:2: ( rule__Element__Alternatives_3 )
            // InternalBlocks.g:334:3: rule__Element__Alternatives_3
            {
            pushFollow(FOLLOW_2);
            rule__Element__Alternatives_3();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getAlternatives_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group__3__Impl"


    // $ANTLR start "rule__Element__Group_3_1__0"
    // InternalBlocks.g:343:1: rule__Element__Group_3_1__0 : rule__Element__Group_3_1__0__Impl rule__Element__Group_3_1__1 ;
    public final void rule__Element__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:347:1: ( rule__Element__Group_3_1__0__Impl rule__Element__Group_3_1__1 )
            // InternalBlocks.g:348:2: rule__Element__Group_3_1__0__Impl rule__Element__Group_3_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Element__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_3_1__1();

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
    // $ANTLR end "rule__Element__Group_3_1__0"


    // $ANTLR start "rule__Element__Group_3_1__0__Impl"
    // InternalBlocks.g:355:1: rule__Element__Group_3_1__0__Impl : ( '>' ) ;
    public final void rule__Element__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:359:1: ( ( '>' ) )
            // InternalBlocks.g:360:1: ( '>' )
            {
            // InternalBlocks.g:360:1: ( '>' )
            // InternalBlocks.g:361:2: '>'
            {
             before(grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_0()); 
            match(input,10,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3_1__0__Impl"


    // $ANTLR start "rule__Element__Group_3_1__1"
    // InternalBlocks.g:370:1: rule__Element__Group_3_1__1 : rule__Element__Group_3_1__1__Impl rule__Element__Group_3_1__2 ;
    public final void rule__Element__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:374:1: ( rule__Element__Group_3_1__1__Impl rule__Element__Group_3_1__2 )
            // InternalBlocks.g:375:2: rule__Element__Group_3_1__1__Impl rule__Element__Group_3_1__2
            {
            pushFollow(FOLLOW_8);
            rule__Element__Group_3_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_3_1__2();

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
    // $ANTLR end "rule__Element__Group_3_1__1"


    // $ANTLR start "rule__Element__Group_3_1__1__Impl"
    // InternalBlocks.g:382:1: rule__Element__Group_3_1__1__Impl : ( ( rule__Element__Alternatives_3_1_1 )? ) ;
    public final void rule__Element__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:386:1: ( ( ( rule__Element__Alternatives_3_1_1 )? ) )
            // InternalBlocks.g:387:1: ( ( rule__Element__Alternatives_3_1_1 )? )
            {
            // InternalBlocks.g:387:1: ( ( rule__Element__Alternatives_3_1_1 )? )
            // InternalBlocks.g:388:2: ( rule__Element__Alternatives_3_1_1 )?
            {
             before(grammarAccess.getElementAccess().getAlternatives_3_1_1()); 
            // InternalBlocks.g:389:2: ( rule__Element__Alternatives_3_1_1 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=RULE_N && LA7_0<=RULE_ID)||LA7_0==9) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalBlocks.g:389:3: rule__Element__Alternatives_3_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Alternatives_3_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElementAccess().getAlternatives_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3_1__1__Impl"


    // $ANTLR start "rule__Element__Group_3_1__2"
    // InternalBlocks.g:397:1: rule__Element__Group_3_1__2 : rule__Element__Group_3_1__2__Impl rule__Element__Group_3_1__3 ;
    public final void rule__Element__Group_3_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:401:1: ( rule__Element__Group_3_1__2__Impl rule__Element__Group_3_1__3 )
            // InternalBlocks.g:402:2: rule__Element__Group_3_1__2__Impl rule__Element__Group_3_1__3
            {
            pushFollow(FOLLOW_5);
            rule__Element__Group_3_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_3_1__3();

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
    // $ANTLR end "rule__Element__Group_3_1__2"


    // $ANTLR start "rule__Element__Group_3_1__2__Impl"
    // InternalBlocks.g:409:1: rule__Element__Group_3_1__2__Impl : ( '</' ) ;
    public final void rule__Element__Group_3_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:413:1: ( ( '</' ) )
            // InternalBlocks.g:414:1: ( '</' )
            {
            // InternalBlocks.g:414:1: ( '</' )
            // InternalBlocks.g:415:2: '</'
            {
             before(grammarAccess.getElementAccess().getLessThanSignSolidusKeyword_3_1_2()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getLessThanSignSolidusKeyword_3_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3_1__2__Impl"


    // $ANTLR start "rule__Element__Group_3_1__3"
    // InternalBlocks.g:424:1: rule__Element__Group_3_1__3 : rule__Element__Group_3_1__3__Impl rule__Element__Group_3_1__4 ;
    public final void rule__Element__Group_3_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:428:1: ( rule__Element__Group_3_1__3__Impl rule__Element__Group_3_1__4 )
            // InternalBlocks.g:429:2: rule__Element__Group_3_1__3__Impl rule__Element__Group_3_1__4
            {
            pushFollow(FOLLOW_9);
            rule__Element__Group_3_1__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_3_1__4();

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
    // $ANTLR end "rule__Element__Group_3_1__3"


    // $ANTLR start "rule__Element__Group_3_1__3__Impl"
    // InternalBlocks.g:436:1: rule__Element__Group_3_1__3__Impl : ( ( rule__Element__CloseAssignment_3_1_3 ) ) ;
    public final void rule__Element__Group_3_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:440:1: ( ( ( rule__Element__CloseAssignment_3_1_3 ) ) )
            // InternalBlocks.g:441:1: ( ( rule__Element__CloseAssignment_3_1_3 ) )
            {
            // InternalBlocks.g:441:1: ( ( rule__Element__CloseAssignment_3_1_3 ) )
            // InternalBlocks.g:442:2: ( rule__Element__CloseAssignment_3_1_3 )
            {
             before(grammarAccess.getElementAccess().getCloseAssignment_3_1_3()); 
            // InternalBlocks.g:443:2: ( rule__Element__CloseAssignment_3_1_3 )
            // InternalBlocks.g:443:3: rule__Element__CloseAssignment_3_1_3
            {
            pushFollow(FOLLOW_2);
            rule__Element__CloseAssignment_3_1_3();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getCloseAssignment_3_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3_1__3__Impl"


    // $ANTLR start "rule__Element__Group_3_1__4"
    // InternalBlocks.g:451:1: rule__Element__Group_3_1__4 : rule__Element__Group_3_1__4__Impl ;
    public final void rule__Element__Group_3_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:455:1: ( rule__Element__Group_3_1__4__Impl )
            // InternalBlocks.g:456:2: rule__Element__Group_3_1__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group_3_1__4__Impl();

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
    // $ANTLR end "rule__Element__Group_3_1__4"


    // $ANTLR start "rule__Element__Group_3_1__4__Impl"
    // InternalBlocks.g:462:1: rule__Element__Group_3_1__4__Impl : ( '>' ) ;
    public final void rule__Element__Group_3_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:466:1: ( ( '>' ) )
            // InternalBlocks.g:467:1: ( '>' )
            {
            // InternalBlocks.g:467:1: ( '>' )
            // InternalBlocks.g:468:2: '>'
            {
             before(grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_4()); 
            match(input,10,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3_1__4__Impl"


    // $ANTLR start "rule__Attribute__Group__0"
    // InternalBlocks.g:478:1: rule__Attribute__Group__0 : rule__Attribute__Group__0__Impl rule__Attribute__Group__1 ;
    public final void rule__Attribute__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:482:1: ( rule__Attribute__Group__0__Impl rule__Attribute__Group__1 )
            // InternalBlocks.g:483:2: rule__Attribute__Group__0__Impl rule__Attribute__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Attribute__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__1();

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
    // $ANTLR end "rule__Attribute__Group__0"


    // $ANTLR start "rule__Attribute__Group__0__Impl"
    // InternalBlocks.g:490:1: rule__Attribute__Group__0__Impl : ( ( rule__Attribute__NameAssignment_0 ) ) ;
    public final void rule__Attribute__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:494:1: ( ( ( rule__Attribute__NameAssignment_0 ) ) )
            // InternalBlocks.g:495:1: ( ( rule__Attribute__NameAssignment_0 ) )
            {
            // InternalBlocks.g:495:1: ( ( rule__Attribute__NameAssignment_0 ) )
            // InternalBlocks.g:496:2: ( rule__Attribute__NameAssignment_0 )
            {
             before(grammarAccess.getAttributeAccess().getNameAssignment_0()); 
            // InternalBlocks.g:497:2: ( rule__Attribute__NameAssignment_0 )
            // InternalBlocks.g:497:3: rule__Attribute__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__0__Impl"


    // $ANTLR start "rule__Attribute__Group__1"
    // InternalBlocks.g:505:1: rule__Attribute__Group__1 : rule__Attribute__Group__1__Impl rule__Attribute__Group__2 ;
    public final void rule__Attribute__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:509:1: ( rule__Attribute__Group__1__Impl rule__Attribute__Group__2 )
            // InternalBlocks.g:510:2: rule__Attribute__Group__1__Impl rule__Attribute__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Attribute__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Attribute__Group__2();

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
    // $ANTLR end "rule__Attribute__Group__1"


    // $ANTLR start "rule__Attribute__Group__1__Impl"
    // InternalBlocks.g:517:1: rule__Attribute__Group__1__Impl : ( '=' ) ;
    public final void rule__Attribute__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:521:1: ( ( '=' ) )
            // InternalBlocks.g:522:1: ( '=' )
            {
            // InternalBlocks.g:522:1: ( '=' )
            // InternalBlocks.g:523:2: '='
            {
             before(grammarAccess.getAttributeAccess().getEqualsSignKeyword_1()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__1__Impl"


    // $ANTLR start "rule__Attribute__Group__2"
    // InternalBlocks.g:532:1: rule__Attribute__Group__2 : rule__Attribute__Group__2__Impl ;
    public final void rule__Attribute__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:536:1: ( rule__Attribute__Group__2__Impl )
            // InternalBlocks.g:537:2: rule__Attribute__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__Group__2__Impl();

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
    // $ANTLR end "rule__Attribute__Group__2"


    // $ANTLR start "rule__Attribute__Group__2__Impl"
    // InternalBlocks.g:543:1: rule__Attribute__Group__2__Impl : ( ( rule__Attribute__ValueAssignment_2 ) ) ;
    public final void rule__Attribute__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:547:1: ( ( ( rule__Attribute__ValueAssignment_2 ) ) )
            // InternalBlocks.g:548:1: ( ( rule__Attribute__ValueAssignment_2 ) )
            {
            // InternalBlocks.g:548:1: ( ( rule__Attribute__ValueAssignment_2 ) )
            // InternalBlocks.g:549:2: ( rule__Attribute__ValueAssignment_2 )
            {
             before(grammarAccess.getAttributeAccess().getValueAssignment_2()); 
            // InternalBlocks.g:550:2: ( rule__Attribute__ValueAssignment_2 )
            // InternalBlocks.g:550:3: rule__Attribute__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Attribute__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAttributeAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__Group__2__Impl"


    // $ANTLR start "rule__File__ElementsAssignment"
    // InternalBlocks.g:559:1: rule__File__ElementsAssignment : ( ruleElement ) ;
    public final void rule__File__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:563:1: ( ( ruleElement ) )
            // InternalBlocks.g:564:2: ( ruleElement )
            {
            // InternalBlocks.g:564:2: ( ruleElement )
            // InternalBlocks.g:565:3: ruleElement
            {
             before(grammarAccess.getFileAccess().getElementsElementParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getFileAccess().getElementsElementParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__ElementsAssignment"


    // $ANTLR start "rule__Element__OpenAssignment_1"
    // InternalBlocks.g:574:1: rule__Element__OpenAssignment_1 : ( RULE_ID ) ;
    public final void rule__Element__OpenAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:578:1: ( ( RULE_ID ) )
            // InternalBlocks.g:579:2: ( RULE_ID )
            {
            // InternalBlocks.g:579:2: ( RULE_ID )
            // InternalBlocks.g:580:3: RULE_ID
            {
             before(grammarAccess.getElementAccess().getOpenIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getOpenIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__OpenAssignment_1"


    // $ANTLR start "rule__Element__AttributesAssignment_2"
    // InternalBlocks.g:589:1: rule__Element__AttributesAssignment_2 : ( ruleAttribute ) ;
    public final void rule__Element__AttributesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:593:1: ( ( ruleAttribute ) )
            // InternalBlocks.g:594:2: ( ruleAttribute )
            {
            // InternalBlocks.g:594:2: ( ruleAttribute )
            // InternalBlocks.g:595:3: ruleAttribute
            {
             before(grammarAccess.getElementAccess().getAttributesAttributeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAttribute();

            state._fsp--;

             after(grammarAccess.getElementAccess().getAttributesAttributeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__AttributesAssignment_2"


    // $ANTLR start "rule__Element__ElementsAssignment_3_1_1_0"
    // InternalBlocks.g:604:1: rule__Element__ElementsAssignment_3_1_1_0 : ( ruleElement ) ;
    public final void rule__Element__ElementsAssignment_3_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:608:1: ( ( ruleElement ) )
            // InternalBlocks.g:609:2: ( ruleElement )
            {
            // InternalBlocks.g:609:2: ( ruleElement )
            // InternalBlocks.g:610:3: ruleElement
            {
             before(grammarAccess.getElementAccess().getElementsElementParserRuleCall_3_1_1_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getElementAccess().getElementsElementParserRuleCall_3_1_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ElementsAssignment_3_1_1_0"


    // $ANTLR start "rule__Element__TextAssignment_3_1_1_1"
    // InternalBlocks.g:619:1: rule__Element__TextAssignment_3_1_1_1 : ( ruleText ) ;
    public final void rule__Element__TextAssignment_3_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:623:1: ( ( ruleText ) )
            // InternalBlocks.g:624:2: ( ruleText )
            {
            // InternalBlocks.g:624:2: ( ruleText )
            // InternalBlocks.g:625:3: ruleText
            {
             before(grammarAccess.getElementAccess().getTextTextParserRuleCall_3_1_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleText();

            state._fsp--;

             after(grammarAccess.getElementAccess().getTextTextParserRuleCall_3_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__TextAssignment_3_1_1_1"


    // $ANTLR start "rule__Element__CloseAssignment_3_1_3"
    // InternalBlocks.g:634:1: rule__Element__CloseAssignment_3_1_3 : ( RULE_ID ) ;
    public final void rule__Element__CloseAssignment_3_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:638:1: ( ( RULE_ID ) )
            // InternalBlocks.g:639:2: ( RULE_ID )
            {
            // InternalBlocks.g:639:2: ( RULE_ID )
            // InternalBlocks.g:640:3: RULE_ID
            {
             before(grammarAccess.getElementAccess().getCloseIDTerminalRuleCall_3_1_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getCloseIDTerminalRuleCall_3_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__CloseAssignment_3_1_3"


    // $ANTLR start "rule__Attribute__NameAssignment_0"
    // InternalBlocks.g:649:1: rule__Attribute__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Attribute__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:653:1: ( ( RULE_ID ) )
            // InternalBlocks.g:654:2: ( RULE_ID )
            {
            // InternalBlocks.g:654:2: ( RULE_ID )
            // InternalBlocks.g:655:3: RULE_ID
            {
             before(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__NameAssignment_0"


    // $ANTLR start "rule__Attribute__ValueAssignment_2"
    // InternalBlocks.g:664:1: rule__Attribute__ValueAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Attribute__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalBlocks.g:668:1: ( ( RULE_STRING ) )
            // InternalBlocks.g:669:2: ( RULE_STRING )
            {
            // InternalBlocks.g:669:2: ( RULE_STRING )
            // InternalBlocks.g:670:3: RULE_STRING
            {
             before(grammarAccess.getAttributeAccess().getValueSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAttributeAccess().getValueSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Attribute__ValueAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000072L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000540L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000A70L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000080L});

}