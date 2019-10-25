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
import m.services.JsonGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJsonParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_FLOAT", "RULE_LONG", "RULE_WHITE_SPACE", "RULE_ID", "'true'", "'false'", "'{'", "'}'", "','", "':'", "'['", "']'"
    };
    public static final int T__9=9;
    public static final int RULE_ID=8;
    public static final int RULE_STRING=4;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int RULE_FLOAT=5;
    public static final int T__13=13;
    public static final int RULE_LONG=6;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_WHITE_SPACE=7;
    public static final int T__10=10;

    // delegates
    // delegators


        public InternalJsonParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalJsonParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalJsonParser.tokenNames; }
    public String getGrammarFileName() { return "InternalJson.g"; }


    	private JsonGrammarAccess grammarAccess;

    	public void setGrammarAccess(JsonGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleObject"
    // InternalJson.g:53:1: entryRuleObject : ruleObject EOF ;
    public final void entryRuleObject() throws RecognitionException {
        try {
            // InternalJson.g:54:1: ( ruleObject EOF )
            // InternalJson.g:55:1: ruleObject EOF
            {
             before(grammarAccess.getObjectRule()); 
            pushFollow(FOLLOW_1);
            ruleObject();

            state._fsp--;

             after(grammarAccess.getObjectRule()); 
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
    // $ANTLR end "entryRuleObject"


    // $ANTLR start "ruleObject"
    // InternalJson.g:62:1: ruleObject : ( ( rule__Object__Group__0 ) ) ;
    public final void ruleObject() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:66:2: ( ( ( rule__Object__Group__0 ) ) )
            // InternalJson.g:67:2: ( ( rule__Object__Group__0 ) )
            {
            // InternalJson.g:67:2: ( ( rule__Object__Group__0 ) )
            // InternalJson.g:68:3: ( rule__Object__Group__0 )
            {
             before(grammarAccess.getObjectAccess().getGroup()); 
            // InternalJson.g:69:3: ( rule__Object__Group__0 )
            // InternalJson.g:69:4: rule__Object__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Object__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getObjectAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleObject"


    // $ANTLR start "entryRuleField"
    // InternalJson.g:78:1: entryRuleField : ruleField EOF ;
    public final void entryRuleField() throws RecognitionException {
        try {
            // InternalJson.g:79:1: ( ruleField EOF )
            // InternalJson.g:80:1: ruleField EOF
            {
             before(grammarAccess.getFieldRule()); 
            pushFollow(FOLLOW_1);
            ruleField();

            state._fsp--;

             after(grammarAccess.getFieldRule()); 
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
    // $ANTLR end "entryRuleField"


    // $ANTLR start "ruleField"
    // InternalJson.g:87:1: ruleField : ( ( rule__Field__Group__0 ) ) ;
    public final void ruleField() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:91:2: ( ( ( rule__Field__Group__0 ) ) )
            // InternalJson.g:92:2: ( ( rule__Field__Group__0 ) )
            {
            // InternalJson.g:92:2: ( ( rule__Field__Group__0 ) )
            // InternalJson.g:93:3: ( rule__Field__Group__0 )
            {
             before(grammarAccess.getFieldAccess().getGroup()); 
            // InternalJson.g:94:3: ( rule__Field__Group__0 )
            // InternalJson.g:94:4: rule__Field__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Field__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFieldAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleField"


    // $ANTLR start "entryRuleValue"
    // InternalJson.g:103:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalJson.g:104:1: ( ruleValue EOF )
            // InternalJson.g:105:1: ruleValue EOF
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
    // InternalJson.g:112:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:116:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalJson.g:117:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalJson.g:117:2: ( ( rule__Value__Alternatives ) )
            // InternalJson.g:118:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalJson.g:119:3: ( rule__Value__Alternatives )
            // InternalJson.g:119:4: rule__Value__Alternatives
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


    // $ANTLR start "entryRuleArray"
    // InternalJson.g:128:1: entryRuleArray : ruleArray EOF ;
    public final void entryRuleArray() throws RecognitionException {
        try {
            // InternalJson.g:129:1: ( ruleArray EOF )
            // InternalJson.g:130:1: ruleArray EOF
            {
             before(grammarAccess.getArrayRule()); 
            pushFollow(FOLLOW_1);
            ruleArray();

            state._fsp--;

             after(grammarAccess.getArrayRule()); 
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
    // $ANTLR end "entryRuleArray"


    // $ANTLR start "ruleArray"
    // InternalJson.g:137:1: ruleArray : ( ( rule__Array__Group__0 ) ) ;
    public final void ruleArray() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:141:2: ( ( ( rule__Array__Group__0 ) ) )
            // InternalJson.g:142:2: ( ( rule__Array__Group__0 ) )
            {
            // InternalJson.g:142:2: ( ( rule__Array__Group__0 ) )
            // InternalJson.g:143:3: ( rule__Array__Group__0 )
            {
             before(grammarAccess.getArrayAccess().getGroup()); 
            // InternalJson.g:144:3: ( rule__Array__Group__0 )
            // InternalJson.g:144:4: rule__Array__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Array__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getArrayAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleArray"


    // $ANTLR start "entryRuleStringValue"
    // InternalJson.g:153:1: entryRuleStringValue : ruleStringValue EOF ;
    public final void entryRuleStringValue() throws RecognitionException {
        try {
            // InternalJson.g:154:1: ( ruleStringValue EOF )
            // InternalJson.g:155:1: ruleStringValue EOF
            {
             before(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            ruleStringValue();

            state._fsp--;

             after(grammarAccess.getStringValueRule()); 
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
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalJson.g:162:1: ruleStringValue : ( ( rule__StringValue__ValueAssignment ) ) ;
    public final void ruleStringValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:166:2: ( ( ( rule__StringValue__ValueAssignment ) ) )
            // InternalJson.g:167:2: ( ( rule__StringValue__ValueAssignment ) )
            {
            // InternalJson.g:167:2: ( ( rule__StringValue__ValueAssignment ) )
            // InternalJson.g:168:3: ( rule__StringValue__ValueAssignment )
            {
             before(grammarAccess.getStringValueAccess().getValueAssignment()); 
            // InternalJson.g:169:3: ( rule__StringValue__ValueAssignment )
            // InternalJson.g:169:4: rule__StringValue__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__StringValue__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getStringValueAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleFloatValue"
    // InternalJson.g:178:1: entryRuleFloatValue : ruleFloatValue EOF ;
    public final void entryRuleFloatValue() throws RecognitionException {
        try {
            // InternalJson.g:179:1: ( ruleFloatValue EOF )
            // InternalJson.g:180:1: ruleFloatValue EOF
            {
             before(grammarAccess.getFloatValueRule()); 
            pushFollow(FOLLOW_1);
            ruleFloatValue();

            state._fsp--;

             after(grammarAccess.getFloatValueRule()); 
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
    // $ANTLR end "entryRuleFloatValue"


    // $ANTLR start "ruleFloatValue"
    // InternalJson.g:187:1: ruleFloatValue : ( ( rule__FloatValue__ValueAssignment ) ) ;
    public final void ruleFloatValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:191:2: ( ( ( rule__FloatValue__ValueAssignment ) ) )
            // InternalJson.g:192:2: ( ( rule__FloatValue__ValueAssignment ) )
            {
            // InternalJson.g:192:2: ( ( rule__FloatValue__ValueAssignment ) )
            // InternalJson.g:193:3: ( rule__FloatValue__ValueAssignment )
            {
             before(grammarAccess.getFloatValueAccess().getValueAssignment()); 
            // InternalJson.g:194:3: ( rule__FloatValue__ValueAssignment )
            // InternalJson.g:194:4: rule__FloatValue__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__FloatValue__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getFloatValueAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFloatValue"


    // $ANTLR start "entryRuleLongValue"
    // InternalJson.g:203:1: entryRuleLongValue : ruleLongValue EOF ;
    public final void entryRuleLongValue() throws RecognitionException {
        try {
            // InternalJson.g:204:1: ( ruleLongValue EOF )
            // InternalJson.g:205:1: ruleLongValue EOF
            {
             before(grammarAccess.getLongValueRule()); 
            pushFollow(FOLLOW_1);
            ruleLongValue();

            state._fsp--;

             after(grammarAccess.getLongValueRule()); 
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
    // $ANTLR end "entryRuleLongValue"


    // $ANTLR start "ruleLongValue"
    // InternalJson.g:212:1: ruleLongValue : ( ( rule__LongValue__ValueAssignment ) ) ;
    public final void ruleLongValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:216:2: ( ( ( rule__LongValue__ValueAssignment ) ) )
            // InternalJson.g:217:2: ( ( rule__LongValue__ValueAssignment ) )
            {
            // InternalJson.g:217:2: ( ( rule__LongValue__ValueAssignment ) )
            // InternalJson.g:218:3: ( rule__LongValue__ValueAssignment )
            {
             before(grammarAccess.getLongValueAccess().getValueAssignment()); 
            // InternalJson.g:219:3: ( rule__LongValue__ValueAssignment )
            // InternalJson.g:219:4: rule__LongValue__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__LongValue__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLongValueAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLongValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalJson.g:228:1: entryRuleBooleanValue : ruleBooleanValue EOF ;
    public final void entryRuleBooleanValue() throws RecognitionException {
        try {
            // InternalJson.g:229:1: ( ruleBooleanValue EOF )
            // InternalJson.g:230:1: ruleBooleanValue EOF
            {
             before(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            ruleBooleanValue();

            state._fsp--;

             after(grammarAccess.getBooleanValueRule()); 
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
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalJson.g:237:1: ruleBooleanValue : ( ( rule__BooleanValue__ValueAssignment ) ) ;
    public final void ruleBooleanValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:241:2: ( ( ( rule__BooleanValue__ValueAssignment ) ) )
            // InternalJson.g:242:2: ( ( rule__BooleanValue__ValueAssignment ) )
            {
            // InternalJson.g:242:2: ( ( rule__BooleanValue__ValueAssignment ) )
            // InternalJson.g:243:3: ( rule__BooleanValue__ValueAssignment )
            {
             before(grammarAccess.getBooleanValueAccess().getValueAssignment()); 
            // InternalJson.g:244:3: ( rule__BooleanValue__ValueAssignment )
            // InternalJson.g:244:4: rule__BooleanValue__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__BooleanValue__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getBooleanValueAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "ruleBoolean"
    // InternalJson.g:253:1: ruleBoolean : ( ( rule__Boolean__Alternatives ) ) ;
    public final void ruleBoolean() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:257:1: ( ( ( rule__Boolean__Alternatives ) ) )
            // InternalJson.g:258:2: ( ( rule__Boolean__Alternatives ) )
            {
            // InternalJson.g:258:2: ( ( rule__Boolean__Alternatives ) )
            // InternalJson.g:259:3: ( rule__Boolean__Alternatives )
            {
             before(grammarAccess.getBooleanAccess().getAlternatives()); 
            // InternalJson.g:260:3: ( rule__Boolean__Alternatives )
            // InternalJson.g:260:4: rule__Boolean__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Boolean__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalJson.g:268:1: rule__Value__Alternatives : ( ( ruleObject ) | ( ruleArray ) | ( ruleStringValue ) | ( ruleFloatValue ) | ( ruleLongValue ) | ( ruleBooleanValue ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:272:1: ( ( ruleObject ) | ( ruleArray ) | ( ruleStringValue ) | ( ruleFloatValue ) | ( ruleLongValue ) | ( ruleBooleanValue ) )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt1=1;
                }
                break;
            case 15:
                {
                alt1=2;
                }
                break;
            case RULE_STRING:
                {
                alt1=3;
                }
                break;
            case RULE_FLOAT:
                {
                alt1=4;
                }
                break;
            case RULE_LONG:
                {
                alt1=5;
                }
                break;
            case 9:
            case 10:
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalJson.g:273:2: ( ruleObject )
                    {
                    // InternalJson.g:273:2: ( ruleObject )
                    // InternalJson.g:274:3: ruleObject
                    {
                     before(grammarAccess.getValueAccess().getObjectParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleObject();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getObjectParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJson.g:279:2: ( ruleArray )
                    {
                    // InternalJson.g:279:2: ( ruleArray )
                    // InternalJson.g:280:3: ruleArray
                    {
                     before(grammarAccess.getValueAccess().getArrayParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleArray();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getArrayParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJson.g:285:2: ( ruleStringValue )
                    {
                    // InternalJson.g:285:2: ( ruleStringValue )
                    // InternalJson.g:286:3: ruleStringValue
                    {
                     before(grammarAccess.getValueAccess().getStringValueParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleStringValue();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getStringValueParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalJson.g:291:2: ( ruleFloatValue )
                    {
                    // InternalJson.g:291:2: ( ruleFloatValue )
                    // InternalJson.g:292:3: ruleFloatValue
                    {
                     before(grammarAccess.getValueAccess().getFloatValueParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleFloatValue();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getFloatValueParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalJson.g:297:2: ( ruleLongValue )
                    {
                    // InternalJson.g:297:2: ( ruleLongValue )
                    // InternalJson.g:298:3: ruleLongValue
                    {
                     before(grammarAccess.getValueAccess().getLongValueParserRuleCall_4()); 
                    pushFollow(FOLLOW_2);
                    ruleLongValue();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getLongValueParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalJson.g:303:2: ( ruleBooleanValue )
                    {
                    // InternalJson.g:303:2: ( ruleBooleanValue )
                    // InternalJson.g:304:3: ruleBooleanValue
                    {
                     before(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_5()); 
                    pushFollow(FOLLOW_2);
                    ruleBooleanValue();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_5()); 

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


    // $ANTLR start "rule__Boolean__Alternatives"
    // InternalJson.g:313:1: rule__Boolean__Alternatives : ( ( ( 'true' ) ) | ( ( 'false' ) ) );
    public final void rule__Boolean__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:317:1: ( ( ( 'true' ) ) | ( ( 'false' ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==9) ) {
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
                    // InternalJson.g:318:2: ( ( 'true' ) )
                    {
                    // InternalJson.g:318:2: ( ( 'true' ) )
                    // InternalJson.g:319:3: ( 'true' )
                    {
                     before(grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0()); 
                    // InternalJson.g:320:3: ( 'true' )
                    // InternalJson.g:320:4: 'true'
                    {
                    match(input,9,FOLLOW_2); 

                    }

                     after(grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJson.g:324:2: ( ( 'false' ) )
                    {
                    // InternalJson.g:324:2: ( ( 'false' ) )
                    // InternalJson.g:325:3: ( 'false' )
                    {
                     before(grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1()); 
                    // InternalJson.g:326:3: ( 'false' )
                    // InternalJson.g:326:4: 'false'
                    {
                    match(input,10,FOLLOW_2); 

                    }

                     after(grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__Boolean__Alternatives"


    // $ANTLR start "rule__Object__Group__0"
    // InternalJson.g:334:1: rule__Object__Group__0 : rule__Object__Group__0__Impl rule__Object__Group__1 ;
    public final void rule__Object__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:338:1: ( rule__Object__Group__0__Impl rule__Object__Group__1 )
            // InternalJson.g:339:2: rule__Object__Group__0__Impl rule__Object__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Object__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Object__Group__1();

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
    // $ANTLR end "rule__Object__Group__0"


    // $ANTLR start "rule__Object__Group__0__Impl"
    // InternalJson.g:346:1: rule__Object__Group__0__Impl : ( () ) ;
    public final void rule__Object__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:350:1: ( ( () ) )
            // InternalJson.g:351:1: ( () )
            {
            // InternalJson.g:351:1: ( () )
            // InternalJson.g:352:2: ()
            {
             before(grammarAccess.getObjectAccess().getObjectAction_0()); 
            // InternalJson.g:353:2: ()
            // InternalJson.g:353:3: 
            {
            }

             after(grammarAccess.getObjectAccess().getObjectAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group__0__Impl"


    // $ANTLR start "rule__Object__Group__1"
    // InternalJson.g:361:1: rule__Object__Group__1 : rule__Object__Group__1__Impl rule__Object__Group__2 ;
    public final void rule__Object__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:365:1: ( rule__Object__Group__1__Impl rule__Object__Group__2 )
            // InternalJson.g:366:2: rule__Object__Group__1__Impl rule__Object__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Object__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Object__Group__2();

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
    // $ANTLR end "rule__Object__Group__1"


    // $ANTLR start "rule__Object__Group__1__Impl"
    // InternalJson.g:373:1: rule__Object__Group__1__Impl : ( '{' ) ;
    public final void rule__Object__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:377:1: ( ( '{' ) )
            // InternalJson.g:378:1: ( '{' )
            {
            // InternalJson.g:378:1: ( '{' )
            // InternalJson.g:379:2: '{'
            {
             before(grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group__1__Impl"


    // $ANTLR start "rule__Object__Group__2"
    // InternalJson.g:388:1: rule__Object__Group__2 : rule__Object__Group__2__Impl rule__Object__Group__3 ;
    public final void rule__Object__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:392:1: ( rule__Object__Group__2__Impl rule__Object__Group__3 )
            // InternalJson.g:393:2: rule__Object__Group__2__Impl rule__Object__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__Object__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Object__Group__3();

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
    // $ANTLR end "rule__Object__Group__2"


    // $ANTLR start "rule__Object__Group__2__Impl"
    // InternalJson.g:400:1: rule__Object__Group__2__Impl : ( ( rule__Object__Group_2__0 )? ) ;
    public final void rule__Object__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:404:1: ( ( ( rule__Object__Group_2__0 )? ) )
            // InternalJson.g:405:1: ( ( rule__Object__Group_2__0 )? )
            {
            // InternalJson.g:405:1: ( ( rule__Object__Group_2__0 )? )
            // InternalJson.g:406:2: ( rule__Object__Group_2__0 )?
            {
             before(grammarAccess.getObjectAccess().getGroup_2()); 
            // InternalJson.g:407:2: ( rule__Object__Group_2__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_STRING) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalJson.g:407:3: rule__Object__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Object__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getObjectAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group__2__Impl"


    // $ANTLR start "rule__Object__Group__3"
    // InternalJson.g:415:1: rule__Object__Group__3 : rule__Object__Group__3__Impl ;
    public final void rule__Object__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:419:1: ( rule__Object__Group__3__Impl )
            // InternalJson.g:420:2: rule__Object__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Object__Group__3__Impl();

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
    // $ANTLR end "rule__Object__Group__3"


    // $ANTLR start "rule__Object__Group__3__Impl"
    // InternalJson.g:426:1: rule__Object__Group__3__Impl : ( '}' ) ;
    public final void rule__Object__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:430:1: ( ( '}' ) )
            // InternalJson.g:431:1: ( '}' )
            {
            // InternalJson.g:431:1: ( '}' )
            // InternalJson.g:432:2: '}'
            {
             before(grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_3()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group__3__Impl"


    // $ANTLR start "rule__Object__Group_2__0"
    // InternalJson.g:442:1: rule__Object__Group_2__0 : rule__Object__Group_2__0__Impl rule__Object__Group_2__1 ;
    public final void rule__Object__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:446:1: ( rule__Object__Group_2__0__Impl rule__Object__Group_2__1 )
            // InternalJson.g:447:2: rule__Object__Group_2__0__Impl rule__Object__Group_2__1
            {
            pushFollow(FOLLOW_5);
            rule__Object__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Object__Group_2__1();

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
    // $ANTLR end "rule__Object__Group_2__0"


    // $ANTLR start "rule__Object__Group_2__0__Impl"
    // InternalJson.g:454:1: rule__Object__Group_2__0__Impl : ( ( rule__Object__FieldsAssignment_2_0 ) ) ;
    public final void rule__Object__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:458:1: ( ( ( rule__Object__FieldsAssignment_2_0 ) ) )
            // InternalJson.g:459:1: ( ( rule__Object__FieldsAssignment_2_0 ) )
            {
            // InternalJson.g:459:1: ( ( rule__Object__FieldsAssignment_2_0 ) )
            // InternalJson.g:460:2: ( rule__Object__FieldsAssignment_2_0 )
            {
             before(grammarAccess.getObjectAccess().getFieldsAssignment_2_0()); 
            // InternalJson.g:461:2: ( rule__Object__FieldsAssignment_2_0 )
            // InternalJson.g:461:3: rule__Object__FieldsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Object__FieldsAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getObjectAccess().getFieldsAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group_2__0__Impl"


    // $ANTLR start "rule__Object__Group_2__1"
    // InternalJson.g:469:1: rule__Object__Group_2__1 : rule__Object__Group_2__1__Impl ;
    public final void rule__Object__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:473:1: ( rule__Object__Group_2__1__Impl )
            // InternalJson.g:474:2: rule__Object__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Object__Group_2__1__Impl();

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
    // $ANTLR end "rule__Object__Group_2__1"


    // $ANTLR start "rule__Object__Group_2__1__Impl"
    // InternalJson.g:480:1: rule__Object__Group_2__1__Impl : ( ( rule__Object__Group_2_1__0 )* ) ;
    public final void rule__Object__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:484:1: ( ( ( rule__Object__Group_2_1__0 )* ) )
            // InternalJson.g:485:1: ( ( rule__Object__Group_2_1__0 )* )
            {
            // InternalJson.g:485:1: ( ( rule__Object__Group_2_1__0 )* )
            // InternalJson.g:486:2: ( rule__Object__Group_2_1__0 )*
            {
             before(grammarAccess.getObjectAccess().getGroup_2_1()); 
            // InternalJson.g:487:2: ( rule__Object__Group_2_1__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalJson.g:487:3: rule__Object__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Object__Group_2_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getObjectAccess().getGroup_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group_2__1__Impl"


    // $ANTLR start "rule__Object__Group_2_1__0"
    // InternalJson.g:496:1: rule__Object__Group_2_1__0 : rule__Object__Group_2_1__0__Impl rule__Object__Group_2_1__1 ;
    public final void rule__Object__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:500:1: ( rule__Object__Group_2_1__0__Impl rule__Object__Group_2_1__1 )
            // InternalJson.g:501:2: rule__Object__Group_2_1__0__Impl rule__Object__Group_2_1__1
            {
            pushFollow(FOLLOW_7);
            rule__Object__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Object__Group_2_1__1();

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
    // $ANTLR end "rule__Object__Group_2_1__0"


    // $ANTLR start "rule__Object__Group_2_1__0__Impl"
    // InternalJson.g:508:1: rule__Object__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__Object__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:512:1: ( ( ',' ) )
            // InternalJson.g:513:1: ( ',' )
            {
            // InternalJson.g:513:1: ( ',' )
            // InternalJson.g:514:2: ','
            {
             before(grammarAccess.getObjectAccess().getCommaKeyword_2_1_0()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getObjectAccess().getCommaKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group_2_1__0__Impl"


    // $ANTLR start "rule__Object__Group_2_1__1"
    // InternalJson.g:523:1: rule__Object__Group_2_1__1 : rule__Object__Group_2_1__1__Impl ;
    public final void rule__Object__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:527:1: ( rule__Object__Group_2_1__1__Impl )
            // InternalJson.g:528:2: rule__Object__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Object__Group_2_1__1__Impl();

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
    // $ANTLR end "rule__Object__Group_2_1__1"


    // $ANTLR start "rule__Object__Group_2_1__1__Impl"
    // InternalJson.g:534:1: rule__Object__Group_2_1__1__Impl : ( ( rule__Object__FieldsAssignment_2_1_1 ) ) ;
    public final void rule__Object__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:538:1: ( ( ( rule__Object__FieldsAssignment_2_1_1 ) ) )
            // InternalJson.g:539:1: ( ( rule__Object__FieldsAssignment_2_1_1 ) )
            {
            // InternalJson.g:539:1: ( ( rule__Object__FieldsAssignment_2_1_1 ) )
            // InternalJson.g:540:2: ( rule__Object__FieldsAssignment_2_1_1 )
            {
             before(grammarAccess.getObjectAccess().getFieldsAssignment_2_1_1()); 
            // InternalJson.g:541:2: ( rule__Object__FieldsAssignment_2_1_1 )
            // InternalJson.g:541:3: rule__Object__FieldsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Object__FieldsAssignment_2_1_1();

            state._fsp--;


            }

             after(grammarAccess.getObjectAccess().getFieldsAssignment_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__Group_2_1__1__Impl"


    // $ANTLR start "rule__Field__Group__0"
    // InternalJson.g:550:1: rule__Field__Group__0 : rule__Field__Group__0__Impl rule__Field__Group__1 ;
    public final void rule__Field__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:554:1: ( rule__Field__Group__0__Impl rule__Field__Group__1 )
            // InternalJson.g:555:2: rule__Field__Group__0__Impl rule__Field__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Field__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Field__Group__1();

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
    // $ANTLR end "rule__Field__Group__0"


    // $ANTLR start "rule__Field__Group__0__Impl"
    // InternalJson.g:562:1: rule__Field__Group__0__Impl : ( ( rule__Field__NameAssignment_0 ) ) ;
    public final void rule__Field__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:566:1: ( ( ( rule__Field__NameAssignment_0 ) ) )
            // InternalJson.g:567:1: ( ( rule__Field__NameAssignment_0 ) )
            {
            // InternalJson.g:567:1: ( ( rule__Field__NameAssignment_0 ) )
            // InternalJson.g:568:2: ( rule__Field__NameAssignment_0 )
            {
             before(grammarAccess.getFieldAccess().getNameAssignment_0()); 
            // InternalJson.g:569:2: ( rule__Field__NameAssignment_0 )
            // InternalJson.g:569:3: rule__Field__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Field__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getFieldAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__Group__0__Impl"


    // $ANTLR start "rule__Field__Group__1"
    // InternalJson.g:577:1: rule__Field__Group__1 : rule__Field__Group__1__Impl rule__Field__Group__2 ;
    public final void rule__Field__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:581:1: ( rule__Field__Group__1__Impl rule__Field__Group__2 )
            // InternalJson.g:582:2: rule__Field__Group__1__Impl rule__Field__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Field__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Field__Group__2();

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
    // $ANTLR end "rule__Field__Group__1"


    // $ANTLR start "rule__Field__Group__1__Impl"
    // InternalJson.g:589:1: rule__Field__Group__1__Impl : ( ':' ) ;
    public final void rule__Field__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:593:1: ( ( ':' ) )
            // InternalJson.g:594:1: ( ':' )
            {
            // InternalJson.g:594:1: ( ':' )
            // InternalJson.g:595:2: ':'
            {
             before(grammarAccess.getFieldAccess().getColonKeyword_1()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getFieldAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__Group__1__Impl"


    // $ANTLR start "rule__Field__Group__2"
    // InternalJson.g:604:1: rule__Field__Group__2 : rule__Field__Group__2__Impl ;
    public final void rule__Field__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:608:1: ( rule__Field__Group__2__Impl )
            // InternalJson.g:609:2: rule__Field__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Field__Group__2__Impl();

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
    // $ANTLR end "rule__Field__Group__2"


    // $ANTLR start "rule__Field__Group__2__Impl"
    // InternalJson.g:615:1: rule__Field__Group__2__Impl : ( ( rule__Field__ValueAssignment_2 ) ) ;
    public final void rule__Field__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:619:1: ( ( ( rule__Field__ValueAssignment_2 ) ) )
            // InternalJson.g:620:1: ( ( rule__Field__ValueAssignment_2 ) )
            {
            // InternalJson.g:620:1: ( ( rule__Field__ValueAssignment_2 ) )
            // InternalJson.g:621:2: ( rule__Field__ValueAssignment_2 )
            {
             before(grammarAccess.getFieldAccess().getValueAssignment_2()); 
            // InternalJson.g:622:2: ( rule__Field__ValueAssignment_2 )
            // InternalJson.g:622:3: rule__Field__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Field__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFieldAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__Group__2__Impl"


    // $ANTLR start "rule__Array__Group__0"
    // InternalJson.g:631:1: rule__Array__Group__0 : rule__Array__Group__0__Impl rule__Array__Group__1 ;
    public final void rule__Array__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:635:1: ( rule__Array__Group__0__Impl rule__Array__Group__1 )
            // InternalJson.g:636:2: rule__Array__Group__0__Impl rule__Array__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Array__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Array__Group__1();

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
    // $ANTLR end "rule__Array__Group__0"


    // $ANTLR start "rule__Array__Group__0__Impl"
    // InternalJson.g:643:1: rule__Array__Group__0__Impl : ( () ) ;
    public final void rule__Array__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:647:1: ( ( () ) )
            // InternalJson.g:648:1: ( () )
            {
            // InternalJson.g:648:1: ( () )
            // InternalJson.g:649:2: ()
            {
             before(grammarAccess.getArrayAccess().getArrayAction_0()); 
            // InternalJson.g:650:2: ()
            // InternalJson.g:650:3: 
            {
            }

             after(grammarAccess.getArrayAccess().getArrayAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group__0__Impl"


    // $ANTLR start "rule__Array__Group__1"
    // InternalJson.g:658:1: rule__Array__Group__1 : rule__Array__Group__1__Impl rule__Array__Group__2 ;
    public final void rule__Array__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:662:1: ( rule__Array__Group__1__Impl rule__Array__Group__2 )
            // InternalJson.g:663:2: rule__Array__Group__1__Impl rule__Array__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Array__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Array__Group__2();

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
    // $ANTLR end "rule__Array__Group__1"


    // $ANTLR start "rule__Array__Group__1__Impl"
    // InternalJson.g:670:1: rule__Array__Group__1__Impl : ( '[' ) ;
    public final void rule__Array__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:674:1: ( ( '[' ) )
            // InternalJson.g:675:1: ( '[' )
            {
            // InternalJson.g:675:1: ( '[' )
            // InternalJson.g:676:2: '['
            {
             before(grammarAccess.getArrayAccess().getLeftSquareBracketKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getArrayAccess().getLeftSquareBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group__1__Impl"


    // $ANTLR start "rule__Array__Group__2"
    // InternalJson.g:685:1: rule__Array__Group__2 : rule__Array__Group__2__Impl rule__Array__Group__3 ;
    public final void rule__Array__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:689:1: ( rule__Array__Group__2__Impl rule__Array__Group__3 )
            // InternalJson.g:690:2: rule__Array__Group__2__Impl rule__Array__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__Array__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Array__Group__3();

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
    // $ANTLR end "rule__Array__Group__2"


    // $ANTLR start "rule__Array__Group__2__Impl"
    // InternalJson.g:697:1: rule__Array__Group__2__Impl : ( ( rule__Array__Group_2__0 )? ) ;
    public final void rule__Array__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:701:1: ( ( ( rule__Array__Group_2__0 )? ) )
            // InternalJson.g:702:1: ( ( rule__Array__Group_2__0 )? )
            {
            // InternalJson.g:702:1: ( ( rule__Array__Group_2__0 )? )
            // InternalJson.g:703:2: ( rule__Array__Group_2__0 )?
            {
             before(grammarAccess.getArrayAccess().getGroup_2()); 
            // InternalJson.g:704:2: ( rule__Array__Group_2__0 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=RULE_STRING && LA5_0<=RULE_LONG)||(LA5_0>=9 && LA5_0<=11)||LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalJson.g:704:3: rule__Array__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Array__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getArrayAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group__2__Impl"


    // $ANTLR start "rule__Array__Group__3"
    // InternalJson.g:712:1: rule__Array__Group__3 : rule__Array__Group__3__Impl ;
    public final void rule__Array__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:716:1: ( rule__Array__Group__3__Impl )
            // InternalJson.g:717:2: rule__Array__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Array__Group__3__Impl();

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
    // $ANTLR end "rule__Array__Group__3"


    // $ANTLR start "rule__Array__Group__3__Impl"
    // InternalJson.g:723:1: rule__Array__Group__3__Impl : ( ']' ) ;
    public final void rule__Array__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:727:1: ( ( ']' ) )
            // InternalJson.g:728:1: ( ']' )
            {
            // InternalJson.g:728:1: ( ']' )
            // InternalJson.g:729:2: ']'
            {
             before(grammarAccess.getArrayAccess().getRightSquareBracketKeyword_3()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getArrayAccess().getRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group__3__Impl"


    // $ANTLR start "rule__Array__Group_2__0"
    // InternalJson.g:739:1: rule__Array__Group_2__0 : rule__Array__Group_2__0__Impl rule__Array__Group_2__1 ;
    public final void rule__Array__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:743:1: ( rule__Array__Group_2__0__Impl rule__Array__Group_2__1 )
            // InternalJson.g:744:2: rule__Array__Group_2__0__Impl rule__Array__Group_2__1
            {
            pushFollow(FOLLOW_5);
            rule__Array__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Array__Group_2__1();

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
    // $ANTLR end "rule__Array__Group_2__0"


    // $ANTLR start "rule__Array__Group_2__0__Impl"
    // InternalJson.g:751:1: rule__Array__Group_2__0__Impl : ( ( rule__Array__ElementsAssignment_2_0 ) ) ;
    public final void rule__Array__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:755:1: ( ( ( rule__Array__ElementsAssignment_2_0 ) ) )
            // InternalJson.g:756:1: ( ( rule__Array__ElementsAssignment_2_0 ) )
            {
            // InternalJson.g:756:1: ( ( rule__Array__ElementsAssignment_2_0 ) )
            // InternalJson.g:757:2: ( rule__Array__ElementsAssignment_2_0 )
            {
             before(grammarAccess.getArrayAccess().getElementsAssignment_2_0()); 
            // InternalJson.g:758:2: ( rule__Array__ElementsAssignment_2_0 )
            // InternalJson.g:758:3: rule__Array__ElementsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Array__ElementsAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getArrayAccess().getElementsAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group_2__0__Impl"


    // $ANTLR start "rule__Array__Group_2__1"
    // InternalJson.g:766:1: rule__Array__Group_2__1 : rule__Array__Group_2__1__Impl ;
    public final void rule__Array__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:770:1: ( rule__Array__Group_2__1__Impl )
            // InternalJson.g:771:2: rule__Array__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Array__Group_2__1__Impl();

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
    // $ANTLR end "rule__Array__Group_2__1"


    // $ANTLR start "rule__Array__Group_2__1__Impl"
    // InternalJson.g:777:1: rule__Array__Group_2__1__Impl : ( ( rule__Array__Group_2_1__0 )* ) ;
    public final void rule__Array__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:781:1: ( ( ( rule__Array__Group_2_1__0 )* ) )
            // InternalJson.g:782:1: ( ( rule__Array__Group_2_1__0 )* )
            {
            // InternalJson.g:782:1: ( ( rule__Array__Group_2_1__0 )* )
            // InternalJson.g:783:2: ( rule__Array__Group_2_1__0 )*
            {
             before(grammarAccess.getArrayAccess().getGroup_2_1()); 
            // InternalJson.g:784:2: ( rule__Array__Group_2_1__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==13) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalJson.g:784:3: rule__Array__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Array__Group_2_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getArrayAccess().getGroup_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group_2__1__Impl"


    // $ANTLR start "rule__Array__Group_2_1__0"
    // InternalJson.g:793:1: rule__Array__Group_2_1__0 : rule__Array__Group_2_1__0__Impl rule__Array__Group_2_1__1 ;
    public final void rule__Array__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:797:1: ( rule__Array__Group_2_1__0__Impl rule__Array__Group_2_1__1 )
            // InternalJson.g:798:2: rule__Array__Group_2_1__0__Impl rule__Array__Group_2_1__1
            {
            pushFollow(FOLLOW_9);
            rule__Array__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Array__Group_2_1__1();

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
    // $ANTLR end "rule__Array__Group_2_1__0"


    // $ANTLR start "rule__Array__Group_2_1__0__Impl"
    // InternalJson.g:805:1: rule__Array__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__Array__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:809:1: ( ( ',' ) )
            // InternalJson.g:810:1: ( ',' )
            {
            // InternalJson.g:810:1: ( ',' )
            // InternalJson.g:811:2: ','
            {
             before(grammarAccess.getArrayAccess().getCommaKeyword_2_1_0()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getArrayAccess().getCommaKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group_2_1__0__Impl"


    // $ANTLR start "rule__Array__Group_2_1__1"
    // InternalJson.g:820:1: rule__Array__Group_2_1__1 : rule__Array__Group_2_1__1__Impl ;
    public final void rule__Array__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:824:1: ( rule__Array__Group_2_1__1__Impl )
            // InternalJson.g:825:2: rule__Array__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Array__Group_2_1__1__Impl();

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
    // $ANTLR end "rule__Array__Group_2_1__1"


    // $ANTLR start "rule__Array__Group_2_1__1__Impl"
    // InternalJson.g:831:1: rule__Array__Group_2_1__1__Impl : ( ( rule__Array__ElementsAssignment_2_1_1 ) ) ;
    public final void rule__Array__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:835:1: ( ( ( rule__Array__ElementsAssignment_2_1_1 ) ) )
            // InternalJson.g:836:1: ( ( rule__Array__ElementsAssignment_2_1_1 ) )
            {
            // InternalJson.g:836:1: ( ( rule__Array__ElementsAssignment_2_1_1 ) )
            // InternalJson.g:837:2: ( rule__Array__ElementsAssignment_2_1_1 )
            {
             before(grammarAccess.getArrayAccess().getElementsAssignment_2_1_1()); 
            // InternalJson.g:838:2: ( rule__Array__ElementsAssignment_2_1_1 )
            // InternalJson.g:838:3: rule__Array__ElementsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Array__ElementsAssignment_2_1_1();

            state._fsp--;


            }

             after(grammarAccess.getArrayAccess().getElementsAssignment_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__Group_2_1__1__Impl"


    // $ANTLR start "rule__Object__FieldsAssignment_2_0"
    // InternalJson.g:847:1: rule__Object__FieldsAssignment_2_0 : ( ruleField ) ;
    public final void rule__Object__FieldsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:851:1: ( ( ruleField ) )
            // InternalJson.g:852:2: ( ruleField )
            {
            // InternalJson.g:852:2: ( ruleField )
            // InternalJson.g:853:3: ruleField
            {
             before(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleField();

            state._fsp--;

             after(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__FieldsAssignment_2_0"


    // $ANTLR start "rule__Object__FieldsAssignment_2_1_1"
    // InternalJson.g:862:1: rule__Object__FieldsAssignment_2_1_1 : ( ruleField ) ;
    public final void rule__Object__FieldsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:866:1: ( ( ruleField ) )
            // InternalJson.g:867:2: ( ruleField )
            {
            // InternalJson.g:867:2: ( ruleField )
            // InternalJson.g:868:3: ruleField
            {
             before(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleField();

            state._fsp--;

             after(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Object__FieldsAssignment_2_1_1"


    // $ANTLR start "rule__Field__NameAssignment_0"
    // InternalJson.g:877:1: rule__Field__NameAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Field__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:881:1: ( ( RULE_STRING ) )
            // InternalJson.g:882:2: ( RULE_STRING )
            {
            // InternalJson.g:882:2: ( RULE_STRING )
            // InternalJson.g:883:3: RULE_STRING
            {
             before(grammarAccess.getFieldAccess().getNameSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getFieldAccess().getNameSTRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__NameAssignment_0"


    // $ANTLR start "rule__Field__ValueAssignment_2"
    // InternalJson.g:892:1: rule__Field__ValueAssignment_2 : ( ruleValue ) ;
    public final void rule__Field__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:896:1: ( ( ruleValue ) )
            // InternalJson.g:897:2: ( ruleValue )
            {
            // InternalJson.g:897:2: ( ruleValue )
            // InternalJson.g:898:3: ruleValue
            {
             before(grammarAccess.getFieldAccess().getValueValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getFieldAccess().getValueValueParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Field__ValueAssignment_2"


    // $ANTLR start "rule__Array__ElementsAssignment_2_0"
    // InternalJson.g:907:1: rule__Array__ElementsAssignment_2_0 : ( ruleValue ) ;
    public final void rule__Array__ElementsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:911:1: ( ( ruleValue ) )
            // InternalJson.g:912:2: ( ruleValue )
            {
            // InternalJson.g:912:2: ( ruleValue )
            // InternalJson.g:913:3: ruleValue
            {
             before(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__ElementsAssignment_2_0"


    // $ANTLR start "rule__Array__ElementsAssignment_2_1_1"
    // InternalJson.g:922:1: rule__Array__ElementsAssignment_2_1_1 : ( ruleValue ) ;
    public final void rule__Array__ElementsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:926:1: ( ( ruleValue ) )
            // InternalJson.g:927:2: ( ruleValue )
            {
            // InternalJson.g:927:2: ( ruleValue )
            // InternalJson.g:928:3: ruleValue
            {
             before(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Array__ElementsAssignment_2_1_1"


    // $ANTLR start "rule__StringValue__ValueAssignment"
    // InternalJson.g:937:1: rule__StringValue__ValueAssignment : ( RULE_STRING ) ;
    public final void rule__StringValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:941:1: ( ( RULE_STRING ) )
            // InternalJson.g:942:2: ( RULE_STRING )
            {
            // InternalJson.g:942:2: ( RULE_STRING )
            // InternalJson.g:943:3: RULE_STRING
            {
             before(grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StringValue__ValueAssignment"


    // $ANTLR start "rule__FloatValue__ValueAssignment"
    // InternalJson.g:952:1: rule__FloatValue__ValueAssignment : ( RULE_FLOAT ) ;
    public final void rule__FloatValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:956:1: ( ( RULE_FLOAT ) )
            // InternalJson.g:957:2: ( RULE_FLOAT )
            {
            // InternalJson.g:957:2: ( RULE_FLOAT )
            // InternalJson.g:958:3: RULE_FLOAT
            {
             before(grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FloatValue__ValueAssignment"


    // $ANTLR start "rule__LongValue__ValueAssignment"
    // InternalJson.g:967:1: rule__LongValue__ValueAssignment : ( RULE_LONG ) ;
    public final void rule__LongValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:971:1: ( ( RULE_LONG ) )
            // InternalJson.g:972:2: ( RULE_LONG )
            {
            // InternalJson.g:972:2: ( RULE_LONG )
            // InternalJson.g:973:3: RULE_LONG
            {
             before(grammarAccess.getLongValueAccess().getValueLONGTerminalRuleCall_0()); 
            match(input,RULE_LONG,FOLLOW_2); 
             after(grammarAccess.getLongValueAccess().getValueLONGTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LongValue__ValueAssignment"


    // $ANTLR start "rule__BooleanValue__ValueAssignment"
    // InternalJson.g:982:1: rule__BooleanValue__ValueAssignment : ( ruleBoolean ) ;
    public final void rule__BooleanValue__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJson.g:986:1: ( ( ruleBoolean ) )
            // InternalJson.g:987:2: ( ruleBoolean )
            {
            // InternalJson.g:987:2: ( ruleBoolean )
            // InternalJson.g:988:3: ruleBoolean
            {
             before(grammarAccess.getBooleanValueAccess().getValueBooleanEnumRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getBooleanValueAccess().getValueBooleanEnumRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BooleanValue__ValueAssignment"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008E70L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000018E70L});

}