package m.ide.contentassist.antlr.internal;
import java.util.Map;
import java.util.HashMap;

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
import m.services.YAMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalYAMLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "TAGUTagUnity3dCom2011", "YAML11", "U", "HyphenMinusHyphenMinusHyphenMinus", "Ampersand", "Comma", "HyphenMinus", "Colon", "Semicolon", "LeftSquareBracket", "RightSquareBracket", "LeftCurlyBracket", "RightCurlyBracket", "RULE_WORD", "RULE_L", "RULE_FLOAT", "RULE_GUID", "RULE_SPACE", "RULE_NEWLINE", "RULE_WS", "RULE_BEGIN", "RULE_END"
    };
    public static final int RULE_END=25;
    public static final int RULE_BEGIN=24;
    public static final int RULE_NEWLINE=22;
    public static final int RULE_WORD=17;
    public static final int RULE_GUID=20;
    public static final int YAML11=5;
    public static final int Comma=9;
    public static final int HyphenMinus=10;
    public static final int U=6;
    public static final int TAGUTagUnity3dCom2011=4;
    public static final int Ampersand=8;
    public static final int RULE_SPACE=21;
    public static final int Colon=11;
    public static final int RightCurlyBracket=16;
    public static final int EOF=-1;
    public static final int RightSquareBracket=14;
    public static final int RULE_WS=23;
    public static final int LeftCurlyBracket=15;
    public static final int HyphenMinusHyphenMinusHyphenMinus=7;
    public static final int Semicolon=12;
    public static final int RULE_L=18;
    public static final int RULE_FLOAT=19;
    public static final int LeftSquareBracket=13;

    // delegates
    // delegators


        public InternalYAMLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalYAMLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalYAMLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalYAMLParser.g"; }


    	private YAMLGrammarAccess grammarAccess;
    	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
    	
    	{
    		tokenNameToValue.put("Ampersand", "'&'");
    		tokenNameToValue.put("Comma", "','");
    		tokenNameToValue.put("HyphenMinus", "'-'");
    		tokenNameToValue.put("Colon", "':'");
    		tokenNameToValue.put("Semicolon", "';'");
    		tokenNameToValue.put("LeftSquareBracket", "'['");
    		tokenNameToValue.put("RightSquareBracket", "']'");
    		tokenNameToValue.put("LeftCurlyBracket", "'{'");
    		tokenNameToValue.put("RightCurlyBracket", "'}'");
    		tokenNameToValue.put("U", "'!u!'");
    		tokenNameToValue.put("HyphenMinusHyphenMinusHyphenMinus", "'---'");
    		tokenNameToValue.put("YAML11", "'%YAML 1.1'");
    		tokenNameToValue.put("TAGUTagUnity3dCom2011", "'%TAG !u! tag:unity3d.com,2011:'");
    	}

    	public void setGrammarAccess(YAMLGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		String result = tokenNameToValue.get(tokenName);
    		if (result == null)
    			result = tokenName;
    		return result;
    	}



    // $ANTLR start "entryRuleFile"
    // InternalYAMLParser.g:68:1: entryRuleFile : ruleFile EOF ;
    public final void entryRuleFile() throws RecognitionException {
        try {
            // InternalYAMLParser.g:69:1: ( ruleFile EOF )
            // InternalYAMLParser.g:70:1: ruleFile EOF
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
    // InternalYAMLParser.g:77:1: ruleFile : ( ( rule__File__Group__0 ) ) ;
    public final void ruleFile() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:81:2: ( ( ( rule__File__Group__0 ) ) )
            // InternalYAMLParser.g:82:2: ( ( rule__File__Group__0 ) )
            {
            // InternalYAMLParser.g:82:2: ( ( rule__File__Group__0 ) )
            // InternalYAMLParser.g:83:3: ( rule__File__Group__0 )
            {
             before(grammarAccess.getFileAccess().getGroup()); 
            // InternalYAMLParser.g:84:3: ( rule__File__Group__0 )
            // InternalYAMLParser.g:84:4: rule__File__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__File__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFileAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleNode"
    // InternalYAMLParser.g:93:1: entryRuleNode : ruleNode EOF ;
    public final void entryRuleNode() throws RecognitionException {
        try {
            // InternalYAMLParser.g:94:1: ( ruleNode EOF )
            // InternalYAMLParser.g:95:1: ruleNode EOF
            {
             before(grammarAccess.getNodeRule()); 
            pushFollow(FOLLOW_1);
            ruleNode();

            state._fsp--;

             after(grammarAccess.getNodeRule()); 
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
    // $ANTLR end "entryRuleNode"


    // $ANTLR start "ruleNode"
    // InternalYAMLParser.g:102:1: ruleNode : ( ( rule__Node__Alternatives ) ) ;
    public final void ruleNode() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:106:2: ( ( ( rule__Node__Alternatives ) ) )
            // InternalYAMLParser.g:107:2: ( ( rule__Node__Alternatives ) )
            {
            // InternalYAMLParser.g:107:2: ( ( rule__Node__Alternatives ) )
            // InternalYAMLParser.g:108:3: ( rule__Node__Alternatives )
            {
             before(grammarAccess.getNodeAccess().getAlternatives()); 
            // InternalYAMLParser.g:109:3: ( rule__Node__Alternatives )
            // InternalYAMLParser.g:109:4: rule__Node__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Node__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNodeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNode"


    // $ANTLR start "entryRuleValue"
    // InternalYAMLParser.g:118:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalYAMLParser.g:119:1: ( ruleValue EOF )
            // InternalYAMLParser.g:120:1: ruleValue EOF
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
    // InternalYAMLParser.g:127:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:131:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalYAMLParser.g:132:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalYAMLParser.g:132:2: ( ( rule__Value__Alternatives ) )
            // InternalYAMLParser.g:133:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalYAMLParser.g:134:3: ( rule__Value__Alternatives )
            // InternalYAMLParser.g:134:4: rule__Value__Alternatives
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


    // $ANTLR start "entryRuleDocument"
    // InternalYAMLParser.g:143:1: entryRuleDocument : ruleDocument EOF ;
    public final void entryRuleDocument() throws RecognitionException {
        try {
            // InternalYAMLParser.g:144:1: ( ruleDocument EOF )
            // InternalYAMLParser.g:145:1: ruleDocument EOF
            {
             before(grammarAccess.getDocumentRule()); 
            pushFollow(FOLLOW_1);
            ruleDocument();

            state._fsp--;

             after(grammarAccess.getDocumentRule()); 
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
    // $ANTLR end "entryRuleDocument"


    // $ANTLR start "ruleDocument"
    // InternalYAMLParser.g:152:1: ruleDocument : ( ( rule__Document__Group__0 ) ) ;
    public final void ruleDocument() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:156:2: ( ( ( rule__Document__Group__0 ) ) )
            // InternalYAMLParser.g:157:2: ( ( rule__Document__Group__0 ) )
            {
            // InternalYAMLParser.g:157:2: ( ( rule__Document__Group__0 ) )
            // InternalYAMLParser.g:158:3: ( rule__Document__Group__0 )
            {
             before(grammarAccess.getDocumentAccess().getGroup()); 
            // InternalYAMLParser.g:159:3: ( rule__Document__Group__0 )
            // InternalYAMLParser.g:159:4: rule__Document__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Document__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDocumentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDocument"


    // $ANTLR start "entryRuleKeyValue"
    // InternalYAMLParser.g:168:1: entryRuleKeyValue : ruleKeyValue EOF ;
    public final void entryRuleKeyValue() throws RecognitionException {
        try {
            // InternalYAMLParser.g:169:1: ( ruleKeyValue EOF )
            // InternalYAMLParser.g:170:1: ruleKeyValue EOF
            {
             before(grammarAccess.getKeyValueRule()); 
            pushFollow(FOLLOW_1);
            ruleKeyValue();

            state._fsp--;

             after(grammarAccess.getKeyValueRule()); 
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
    // $ANTLR end "entryRuleKeyValue"


    // $ANTLR start "ruleKeyValue"
    // InternalYAMLParser.g:177:1: ruleKeyValue : ( ( rule__KeyValue__Group__0 ) ) ;
    public final void ruleKeyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:181:2: ( ( ( rule__KeyValue__Group__0 ) ) )
            // InternalYAMLParser.g:182:2: ( ( rule__KeyValue__Group__0 ) )
            {
            // InternalYAMLParser.g:182:2: ( ( rule__KeyValue__Group__0 ) )
            // InternalYAMLParser.g:183:3: ( rule__KeyValue__Group__0 )
            {
             before(grammarAccess.getKeyValueAccess().getGroup()); 
            // InternalYAMLParser.g:184:3: ( rule__KeyValue__Group__0 )
            // InternalYAMLParser.g:184:4: rule__KeyValue__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValue__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeyValue"


    // $ANTLR start "entryRuleMap"
    // InternalYAMLParser.g:193:1: entryRuleMap : ruleMap EOF ;
    public final void entryRuleMap() throws RecognitionException {
        try {
            // InternalYAMLParser.g:194:1: ( ruleMap EOF )
            // InternalYAMLParser.g:195:1: ruleMap EOF
            {
             before(grammarAccess.getMapRule()); 
            pushFollow(FOLLOW_1);
            ruleMap();

            state._fsp--;

             after(grammarAccess.getMapRule()); 
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
    // $ANTLR end "entryRuleMap"


    // $ANTLR start "ruleMap"
    // InternalYAMLParser.g:202:1: ruleMap : ( ( rule__Map__Group__0 ) ) ;
    public final void ruleMap() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:206:2: ( ( ( rule__Map__Group__0 ) ) )
            // InternalYAMLParser.g:207:2: ( ( rule__Map__Group__0 ) )
            {
            // InternalYAMLParser.g:207:2: ( ( rule__Map__Group__0 ) )
            // InternalYAMLParser.g:208:3: ( rule__Map__Group__0 )
            {
             before(grammarAccess.getMapAccess().getGroup()); 
            // InternalYAMLParser.g:209:3: ( rule__Map__Group__0 )
            // InternalYAMLParser.g:209:4: rule__Map__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Map__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMapAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMap"


    // $ANTLR start "entryRuleKeyValueList"
    // InternalYAMLParser.g:218:1: entryRuleKeyValueList : ruleKeyValueList EOF ;
    public final void entryRuleKeyValueList() throws RecognitionException {
        try {
            // InternalYAMLParser.g:219:1: ( ruleKeyValueList EOF )
            // InternalYAMLParser.g:220:1: ruleKeyValueList EOF
            {
             before(grammarAccess.getKeyValueListRule()); 
            pushFollow(FOLLOW_1);
            ruleKeyValueList();

            state._fsp--;

             after(grammarAccess.getKeyValueListRule()); 
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
    // $ANTLR end "entryRuleKeyValueList"


    // $ANTLR start "ruleKeyValueList"
    // InternalYAMLParser.g:227:1: ruleKeyValueList : ( ( rule__KeyValueList__Group__0 ) ) ;
    public final void ruleKeyValueList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:231:2: ( ( ( rule__KeyValueList__Group__0 ) ) )
            // InternalYAMLParser.g:232:2: ( ( rule__KeyValueList__Group__0 ) )
            {
            // InternalYAMLParser.g:232:2: ( ( rule__KeyValueList__Group__0 ) )
            // InternalYAMLParser.g:233:3: ( rule__KeyValueList__Group__0 )
            {
             before(grammarAccess.getKeyValueListAccess().getGroup()); 
            // InternalYAMLParser.g:234:3: ( rule__KeyValueList__Group__0 )
            // InternalYAMLParser.g:234:4: rule__KeyValueList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKeyValueList"


    // $ANTLR start "entryRuleElement"
    // InternalYAMLParser.g:243:1: entryRuleElement : ruleElement EOF ;
    public final void entryRuleElement() throws RecognitionException {
        try {
            // InternalYAMLParser.g:244:1: ( ruleElement EOF )
            // InternalYAMLParser.g:245:1: ruleElement EOF
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
    // InternalYAMLParser.g:252:1: ruleElement : ( ( rule__Element__Alternatives ) ) ;
    public final void ruleElement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:256:2: ( ( ( rule__Element__Alternatives ) ) )
            // InternalYAMLParser.g:257:2: ( ( rule__Element__Alternatives ) )
            {
            // InternalYAMLParser.g:257:2: ( ( rule__Element__Alternatives ) )
            // InternalYAMLParser.g:258:3: ( rule__Element__Alternatives )
            {
             before(grammarAccess.getElementAccess().getAlternatives()); 
            // InternalYAMLParser.g:259:3: ( rule__Element__Alternatives )
            // InternalYAMLParser.g:259:4: rule__Element__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Element__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getAlternatives()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleInlineList"
    // InternalYAMLParser.g:268:1: entryRuleInlineList : ruleInlineList EOF ;
    public final void entryRuleInlineList() throws RecognitionException {
        try {
            // InternalYAMLParser.g:269:1: ( ruleInlineList EOF )
            // InternalYAMLParser.g:270:1: ruleInlineList EOF
            {
             before(grammarAccess.getInlineListRule()); 
            pushFollow(FOLLOW_1);
            ruleInlineList();

            state._fsp--;

             after(grammarAccess.getInlineListRule()); 
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
    // $ANTLR end "entryRuleInlineList"


    // $ANTLR start "ruleInlineList"
    // InternalYAMLParser.g:277:1: ruleInlineList : ( ( rule__InlineList__Group__0 ) ) ;
    public final void ruleInlineList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:281:2: ( ( ( rule__InlineList__Group__0 ) ) )
            // InternalYAMLParser.g:282:2: ( ( rule__InlineList__Group__0 ) )
            {
            // InternalYAMLParser.g:282:2: ( ( rule__InlineList__Group__0 ) )
            // InternalYAMLParser.g:283:3: ( rule__InlineList__Group__0 )
            {
             before(grammarAccess.getInlineListAccess().getGroup()); 
            // InternalYAMLParser.g:284:3: ( rule__InlineList__Group__0 )
            // InternalYAMLParser.g:284:4: rule__InlineList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInlineListAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInlineList"


    // $ANTLR start "entryRuleInlineMap"
    // InternalYAMLParser.g:293:1: entryRuleInlineMap : ruleInlineMap EOF ;
    public final void entryRuleInlineMap() throws RecognitionException {
        try {
            // InternalYAMLParser.g:294:1: ( ruleInlineMap EOF )
            // InternalYAMLParser.g:295:1: ruleInlineMap EOF
            {
             before(grammarAccess.getInlineMapRule()); 
            pushFollow(FOLLOW_1);
            ruleInlineMap();

            state._fsp--;

             after(grammarAccess.getInlineMapRule()); 
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
    // $ANTLR end "entryRuleInlineMap"


    // $ANTLR start "ruleInlineMap"
    // InternalYAMLParser.g:302:1: ruleInlineMap : ( ( rule__InlineMap__Group__0 ) ) ;
    public final void ruleInlineMap() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:306:2: ( ( ( rule__InlineMap__Group__0 ) ) )
            // InternalYAMLParser.g:307:2: ( ( rule__InlineMap__Group__0 ) )
            {
            // InternalYAMLParser.g:307:2: ( ( rule__InlineMap__Group__0 ) )
            // InternalYAMLParser.g:308:3: ( rule__InlineMap__Group__0 )
            {
             before(grammarAccess.getInlineMapAccess().getGroup()); 
            // InternalYAMLParser.g:309:3: ( rule__InlineMap__Group__0 )
            // InternalYAMLParser.g:309:4: rule__InlineMap__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInlineMapAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInlineMap"


    // $ANTLR start "entryRuleInlineSemicolonList"
    // InternalYAMLParser.g:318:1: entryRuleInlineSemicolonList : ruleInlineSemicolonList EOF ;
    public final void entryRuleInlineSemicolonList() throws RecognitionException {
        try {
            // InternalYAMLParser.g:319:1: ( ruleInlineSemicolonList EOF )
            // InternalYAMLParser.g:320:1: ruleInlineSemicolonList EOF
            {
             before(grammarAccess.getInlineSemicolonListRule()); 
            pushFollow(FOLLOW_1);
            ruleInlineSemicolonList();

            state._fsp--;

             after(grammarAccess.getInlineSemicolonListRule()); 
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
    // $ANTLR end "entryRuleInlineSemicolonList"


    // $ANTLR start "ruleInlineSemicolonList"
    // InternalYAMLParser.g:327:1: ruleInlineSemicolonList : ( ( rule__InlineSemicolonList__Group__0 ) ) ;
    public final void ruleInlineSemicolonList() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:331:2: ( ( ( rule__InlineSemicolonList__Group__0 ) ) )
            // InternalYAMLParser.g:332:2: ( ( rule__InlineSemicolonList__Group__0 ) )
            {
            // InternalYAMLParser.g:332:2: ( ( rule__InlineSemicolonList__Group__0 ) )
            // InternalYAMLParser.g:333:3: ( rule__InlineSemicolonList__Group__0 )
            {
             before(grammarAccess.getInlineSemicolonListAccess().getGroup()); 
            // InternalYAMLParser.g:334:3: ( rule__InlineSemicolonList__Group__0 )
            // InternalYAMLParser.g:334:4: rule__InlineSemicolonList__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInlineSemicolonListAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInlineSemicolonList"


    // $ANTLR start "ruleVersion"
    // InternalYAMLParser.g:343:1: ruleVersion : ( ( YAML11 ) ) ;
    public final void ruleVersion() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:347:1: ( ( ( YAML11 ) ) )
            // InternalYAMLParser.g:348:2: ( ( YAML11 ) )
            {
            // InternalYAMLParser.g:348:2: ( ( YAML11 ) )
            // InternalYAMLParser.g:349:3: ( YAML11 )
            {
             before(grammarAccess.getVersionAccess().getOneOneEnumLiteralDeclaration()); 
            // InternalYAMLParser.g:350:3: ( YAML11 )
            // InternalYAMLParser.g:350:4: YAML11
            {
            match(input,YAML11,FOLLOW_2); 

            }

             after(grammarAccess.getVersionAccess().getOneOneEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVersion"


    // $ANTLR start "ruleTag"
    // InternalYAMLParser.g:359:1: ruleTag : ( ( TAGUTagUnity3dCom2011 ) ) ;
    public final void ruleTag() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:363:1: ( ( ( TAGUTagUnity3dCom2011 ) ) )
            // InternalYAMLParser.g:364:2: ( ( TAGUTagUnity3dCom2011 ) )
            {
            // InternalYAMLParser.g:364:2: ( ( TAGUTagUnity3dCom2011 ) )
            // InternalYAMLParser.g:365:3: ( TAGUTagUnity3dCom2011 )
            {
             before(grammarAccess.getTagAccess().getUnityEnumLiteralDeclaration()); 
            // InternalYAMLParser.g:366:3: ( TAGUTagUnity3dCom2011 )
            // InternalYAMLParser.g:366:4: TAGUTagUnity3dCom2011
            {
            match(input,TAGUTagUnity3dCom2011,FOLLOW_2); 

            }

             after(grammarAccess.getTagAccess().getUnityEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTag"


    // $ANTLR start "rule__Node__Alternatives"
    // InternalYAMLParser.g:374:1: rule__Node__Alternatives : ( ( ruleDocument ) | ( ruleValue ) );
    public final void rule__Node__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:378:1: ( ( ruleDocument ) | ( ruleValue ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==HyphenMinusHyphenMinusHyphenMinus) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_WORD) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalYAMLParser.g:379:2: ( ruleDocument )
                    {
                    // InternalYAMLParser.g:379:2: ( ruleDocument )
                    // InternalYAMLParser.g:380:3: ruleDocument
                    {
                     before(grammarAccess.getNodeAccess().getDocumentParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleDocument();

                    state._fsp--;

                     after(grammarAccess.getNodeAccess().getDocumentParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:385:2: ( ruleValue )
                    {
                    // InternalYAMLParser.g:385:2: ( ruleValue )
                    // InternalYAMLParser.g:386:3: ruleValue
                    {
                     before(grammarAccess.getNodeAccess().getValueParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleValue();

                    state._fsp--;

                     after(grammarAccess.getNodeAccess().getValueParserRuleCall_1()); 

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
    // $ANTLR end "rule__Node__Alternatives"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalYAMLParser.g:395:1: rule__Value__Alternatives : ( ( ruleMap ) | ( ruleKeyValue ) | ( ruleKeyValueList ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:399:1: ( ( ruleMap ) | ( ruleKeyValue ) | ( ruleKeyValueList ) )
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_WORD) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==Colon) ) {
                    int LA2_2 = input.LA(3);

                    if ( (LA2_2==RULE_WS) ) {
                        int LA2_3 = input.LA(4);

                        if ( (LA2_3==HyphenMinus) ) {
                            alt2=3;
                        }
                        else if ( (LA2_3==RULE_BEGIN) ) {
                            alt2=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 2, 3, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA2_2==RULE_SPACE) ) {
                        alt2=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalYAMLParser.g:400:2: ( ruleMap )
                    {
                    // InternalYAMLParser.g:400:2: ( ruleMap )
                    // InternalYAMLParser.g:401:3: ruleMap
                    {
                     before(grammarAccess.getValueAccess().getMapParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleMap();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getMapParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:406:2: ( ruleKeyValue )
                    {
                    // InternalYAMLParser.g:406:2: ( ruleKeyValue )
                    // InternalYAMLParser.g:407:3: ruleKeyValue
                    {
                     before(grammarAccess.getValueAccess().getKeyValueParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleKeyValue();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getKeyValueParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalYAMLParser.g:412:2: ( ruleKeyValueList )
                    {
                    // InternalYAMLParser.g:412:2: ( ruleKeyValueList )
                    // InternalYAMLParser.g:413:3: ruleKeyValueList
                    {
                     before(grammarAccess.getValueAccess().getKeyValueListParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleKeyValueList();

                    state._fsp--;

                     after(grammarAccess.getValueAccess().getKeyValueListParserRuleCall_2()); 

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


    // $ANTLR start "rule__KeyValueList__ValuesAlternatives_5_0"
    // InternalYAMLParser.g:422:1: rule__KeyValueList__ValuesAlternatives_5_0 : ( ( ruleKeyValue ) | ( ruleElement ) );
    public final void rule__KeyValueList__ValuesAlternatives_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:426:1: ( ( ruleKeyValue ) | ( ruleElement ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_WORD) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==Colon) ) {
                    alt3=1;
                }
                else if ( (LA3_1==EOF||LA3_1==HyphenMinusHyphenMinusHyphenMinus||LA3_1==Semicolon||LA3_1==RULE_WORD||LA3_1==RULE_WS||LA3_1==RULE_END) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA3_0==LeftSquareBracket||LA3_0==LeftCurlyBracket||(LA3_0>=RULE_L && LA3_0<=RULE_GUID)) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalYAMLParser.g:427:2: ( ruleKeyValue )
                    {
                    // InternalYAMLParser.g:427:2: ( ruleKeyValue )
                    // InternalYAMLParser.g:428:3: ruleKeyValue
                    {
                     before(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_5_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleKeyValue();

                    state._fsp--;

                     after(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_5_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:433:2: ( ruleElement )
                    {
                    // InternalYAMLParser.g:433:2: ( ruleElement )
                    // InternalYAMLParser.g:434:3: ruleElement
                    {
                     before(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_5_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleElement();

                    state._fsp--;

                     after(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_5_0_1()); 

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
    // $ANTLR end "rule__KeyValueList__ValuesAlternatives_5_0"


    // $ANTLR start "rule__KeyValueList__ValuesAlternatives_6_3_0"
    // InternalYAMLParser.g:443:1: rule__KeyValueList__ValuesAlternatives_6_3_0 : ( ( ruleKeyValue ) | ( ruleElement ) );
    public final void rule__KeyValueList__ValuesAlternatives_6_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:447:1: ( ( ruleKeyValue ) | ( ruleElement ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_WORD) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==Colon) ) {
                    alt4=1;
                }
                else if ( (LA4_1==EOF||LA4_1==HyphenMinusHyphenMinusHyphenMinus||LA4_1==Semicolon||LA4_1==RULE_WORD||LA4_1==RULE_WS||LA4_1==RULE_END) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA4_0==LeftSquareBracket||LA4_0==LeftCurlyBracket||(LA4_0>=RULE_L && LA4_0<=RULE_GUID)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalYAMLParser.g:448:2: ( ruleKeyValue )
                    {
                    // InternalYAMLParser.g:448:2: ( ruleKeyValue )
                    // InternalYAMLParser.g:449:3: ruleKeyValue
                    {
                     before(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_6_3_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleKeyValue();

                    state._fsp--;

                     after(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_6_3_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:454:2: ( ruleElement )
                    {
                    // InternalYAMLParser.g:454:2: ( ruleElement )
                    // InternalYAMLParser.g:455:3: ruleElement
                    {
                     before(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_6_3_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleElement();

                    state._fsp--;

                     after(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_6_3_0_1()); 

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
    // $ANTLR end "rule__KeyValueList__ValuesAlternatives_6_3_0"


    // $ANTLR start "rule__Element__Alternatives"
    // InternalYAMLParser.g:464:1: rule__Element__Alternatives : ( ( ruleInlineMap ) | ( ruleInlineList ) | ( ruleInlineSemicolonList ) | ( ( rule__Element__Group_3__0 ) ) | ( ( rule__Element__Group_4__0 ) ) | ( ( rule__Element__Group_5__0 ) ) | ( ( rule__Element__Group_6__0 ) ) );
    public final void rule__Element__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:468:1: ( ( ruleInlineMap ) | ( ruleInlineList ) | ( ruleInlineSemicolonList ) | ( ( rule__Element__Group_3__0 ) ) | ( ( rule__Element__Group_4__0 ) ) | ( ( rule__Element__Group_5__0 ) ) | ( ( rule__Element__Group_6__0 ) ) )
            int alt5=7;
            switch ( input.LA(1) ) {
            case LeftCurlyBracket:
                {
                alt5=1;
                }
                break;
            case LeftSquareBracket:
                {
                alt5=2;
                }
                break;
            case RULE_WORD:
                {
                int LA5_3 = input.LA(2);

                if ( (LA5_3==EOF||LA5_3==HyphenMinusHyphenMinusHyphenMinus||LA5_3==Comma||LA5_3==RightSquareBracket||(LA5_3>=RightCurlyBracket && LA5_3<=RULE_WORD)||LA5_3==RULE_SPACE||LA5_3==RULE_WS||LA5_3==RULE_END) ) {
                    alt5=4;
                }
                else if ( (LA5_3==Semicolon) ) {
                    alt5=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 3, input);

                    throw nvae;
                }
                }
                break;
            case RULE_FLOAT:
                {
                alt5=5;
                }
                break;
            case RULE_L:
                {
                alt5=6;
                }
                break;
            case RULE_GUID:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalYAMLParser.g:469:2: ( ruleInlineMap )
                    {
                    // InternalYAMLParser.g:469:2: ( ruleInlineMap )
                    // InternalYAMLParser.g:470:3: ruleInlineMap
                    {
                     before(grammarAccess.getElementAccess().getInlineMapParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleInlineMap();

                    state._fsp--;

                     after(grammarAccess.getElementAccess().getInlineMapParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:475:2: ( ruleInlineList )
                    {
                    // InternalYAMLParser.g:475:2: ( ruleInlineList )
                    // InternalYAMLParser.g:476:3: ruleInlineList
                    {
                     before(grammarAccess.getElementAccess().getInlineListParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleInlineList();

                    state._fsp--;

                     after(grammarAccess.getElementAccess().getInlineListParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalYAMLParser.g:481:2: ( ruleInlineSemicolonList )
                    {
                    // InternalYAMLParser.g:481:2: ( ruleInlineSemicolonList )
                    // InternalYAMLParser.g:482:3: ruleInlineSemicolonList
                    {
                     before(grammarAccess.getElementAccess().getInlineSemicolonListParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleInlineSemicolonList();

                    state._fsp--;

                     after(grammarAccess.getElementAccess().getInlineSemicolonListParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalYAMLParser.g:487:2: ( ( rule__Element__Group_3__0 ) )
                    {
                    // InternalYAMLParser.g:487:2: ( ( rule__Element__Group_3__0 ) )
                    // InternalYAMLParser.g:488:3: ( rule__Element__Group_3__0 )
                    {
                     before(grammarAccess.getElementAccess().getGroup_3()); 
                    // InternalYAMLParser.g:489:3: ( rule__Element__Group_3__0 )
                    // InternalYAMLParser.g:489:4: rule__Element__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalYAMLParser.g:493:2: ( ( rule__Element__Group_4__0 ) )
                    {
                    // InternalYAMLParser.g:493:2: ( ( rule__Element__Group_4__0 ) )
                    // InternalYAMLParser.g:494:3: ( rule__Element__Group_4__0 )
                    {
                     before(grammarAccess.getElementAccess().getGroup_4()); 
                    // InternalYAMLParser.g:495:3: ( rule__Element__Group_4__0 )
                    // InternalYAMLParser.g:495:4: rule__Element__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getGroup_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalYAMLParser.g:499:2: ( ( rule__Element__Group_5__0 ) )
                    {
                    // InternalYAMLParser.g:499:2: ( ( rule__Element__Group_5__0 ) )
                    // InternalYAMLParser.g:500:3: ( rule__Element__Group_5__0 )
                    {
                     before(grammarAccess.getElementAccess().getGroup_5()); 
                    // InternalYAMLParser.g:501:3: ( rule__Element__Group_5__0 )
                    // InternalYAMLParser.g:501:4: rule__Element__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getGroup_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalYAMLParser.g:505:2: ( ( rule__Element__Group_6__0 ) )
                    {
                    // InternalYAMLParser.g:505:2: ( ( rule__Element__Group_6__0 ) )
                    // InternalYAMLParser.g:506:3: ( rule__Element__Group_6__0 )
                    {
                     before(grammarAccess.getElementAccess().getGroup_6()); 
                    // InternalYAMLParser.g:507:3: ( rule__Element__Group_6__0 )
                    // InternalYAMLParser.g:507:4: rule__Element__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Element__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElementAccess().getGroup_6()); 

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
    // $ANTLR end "rule__Element__Alternatives"


    // $ANTLR start "rule__File__Group__0"
    // InternalYAMLParser.g:515:1: rule__File__Group__0 : rule__File__Group__0__Impl rule__File__Group__1 ;
    public final void rule__File__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:519:1: ( rule__File__Group__0__Impl rule__File__Group__1 )
            // InternalYAMLParser.g:520:2: rule__File__Group__0__Impl rule__File__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__File__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__0"


    // $ANTLR start "rule__File__Group__0__Impl"
    // InternalYAMLParser.g:527:1: rule__File__Group__0__Impl : ( () ) ;
    public final void rule__File__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:531:1: ( ( () ) )
            // InternalYAMLParser.g:532:1: ( () )
            {
            // InternalYAMLParser.g:532:1: ( () )
            // InternalYAMLParser.g:533:2: ()
            {
             before(grammarAccess.getFileAccess().getFileAction_0()); 
            // InternalYAMLParser.g:534:2: ()
            // InternalYAMLParser.g:534:3: 
            {
            }

             after(grammarAccess.getFileAccess().getFileAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__0__Impl"


    // $ANTLR start "rule__File__Group__1"
    // InternalYAMLParser.g:542:1: rule__File__Group__1 : rule__File__Group__1__Impl rule__File__Group__2 ;
    public final void rule__File__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:546:1: ( rule__File__Group__1__Impl rule__File__Group__2 )
            // InternalYAMLParser.g:547:2: rule__File__Group__1__Impl rule__File__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__File__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__1"


    // $ANTLR start "rule__File__Group__1__Impl"
    // InternalYAMLParser.g:554:1: rule__File__Group__1__Impl : ( ( rule__File__Group_1__0 )? ) ;
    public final void rule__File__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:558:1: ( ( ( rule__File__Group_1__0 )? ) )
            // InternalYAMLParser.g:559:1: ( ( rule__File__Group_1__0 )? )
            {
            // InternalYAMLParser.g:559:1: ( ( rule__File__Group_1__0 )? )
            // InternalYAMLParser.g:560:2: ( rule__File__Group_1__0 )?
            {
             before(grammarAccess.getFileAccess().getGroup_1()); 
            // InternalYAMLParser.g:561:2: ( rule__File__Group_1__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==YAML11) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalYAMLParser.g:561:3: rule__File__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__File__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFileAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__1__Impl"


    // $ANTLR start "rule__File__Group__2"
    // InternalYAMLParser.g:569:1: rule__File__Group__2 : rule__File__Group__2__Impl ;
    public final void rule__File__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:573:1: ( rule__File__Group__2__Impl )
            // InternalYAMLParser.g:574:2: rule__File__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__File__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__2"


    // $ANTLR start "rule__File__Group__2__Impl"
    // InternalYAMLParser.g:580:1: rule__File__Group__2__Impl : ( ( rule__File__Group_2__0 )* ) ;
    public final void rule__File__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:584:1: ( ( ( rule__File__Group_2__0 )* ) )
            // InternalYAMLParser.g:585:1: ( ( rule__File__Group_2__0 )* )
            {
            // InternalYAMLParser.g:585:1: ( ( rule__File__Group_2__0 )* )
            // InternalYAMLParser.g:586:2: ( rule__File__Group_2__0 )*
            {
             before(grammarAccess.getFileAccess().getGroup_2()); 
            // InternalYAMLParser.g:587:2: ( rule__File__Group_2__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==HyphenMinusHyphenMinusHyphenMinus||LA7_0==RULE_WORD) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalYAMLParser.g:587:3: rule__File__Group_2__0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__File__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getFileAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group__2__Impl"


    // $ANTLR start "rule__File__Group_1__0"
    // InternalYAMLParser.g:596:1: rule__File__Group_1__0 : rule__File__Group_1__0__Impl rule__File__Group_1__1 ;
    public final void rule__File__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:600:1: ( rule__File__Group_1__0__Impl rule__File__Group_1__1 )
            // InternalYAMLParser.g:601:2: rule__File__Group_1__0__Impl rule__File__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__File__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__0"


    // $ANTLR start "rule__File__Group_1__0__Impl"
    // InternalYAMLParser.g:608:1: rule__File__Group_1__0__Impl : ( ( rule__File__VersionAssignment_1_0 ) ) ;
    public final void rule__File__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:612:1: ( ( ( rule__File__VersionAssignment_1_0 ) ) )
            // InternalYAMLParser.g:613:1: ( ( rule__File__VersionAssignment_1_0 ) )
            {
            // InternalYAMLParser.g:613:1: ( ( rule__File__VersionAssignment_1_0 ) )
            // InternalYAMLParser.g:614:2: ( rule__File__VersionAssignment_1_0 )
            {
             before(grammarAccess.getFileAccess().getVersionAssignment_1_0()); 
            // InternalYAMLParser.g:615:2: ( rule__File__VersionAssignment_1_0 )
            // InternalYAMLParser.g:615:3: rule__File__VersionAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__File__VersionAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getFileAccess().getVersionAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__0__Impl"


    // $ANTLR start "rule__File__Group_1__1"
    // InternalYAMLParser.g:623:1: rule__File__Group_1__1 : rule__File__Group_1__1__Impl rule__File__Group_1__2 ;
    public final void rule__File__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:627:1: ( rule__File__Group_1__1__Impl rule__File__Group_1__2 )
            // InternalYAMLParser.g:628:2: rule__File__Group_1__1__Impl rule__File__Group_1__2
            {
            pushFollow(FOLLOW_6);
            rule__File__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__1"


    // $ANTLR start "rule__File__Group_1__1__Impl"
    // InternalYAMLParser.g:635:1: rule__File__Group_1__1__Impl : ( RULE_WS ) ;
    public final void rule__File__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:639:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:640:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:640:1: ( RULE_WS )
            // InternalYAMLParser.g:641:2: RULE_WS
            {
             before(grammarAccess.getFileAccess().getWSTerminalRuleCall_1_1()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getFileAccess().getWSTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__1__Impl"


    // $ANTLR start "rule__File__Group_1__2"
    // InternalYAMLParser.g:650:1: rule__File__Group_1__2 : rule__File__Group_1__2__Impl rule__File__Group_1__3 ;
    public final void rule__File__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:654:1: ( rule__File__Group_1__2__Impl rule__File__Group_1__3 )
            // InternalYAMLParser.g:655:2: rule__File__Group_1__2__Impl rule__File__Group_1__3
            {
            pushFollow(FOLLOW_5);
            rule__File__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__2"


    // $ANTLR start "rule__File__Group_1__2__Impl"
    // InternalYAMLParser.g:662:1: rule__File__Group_1__2__Impl : ( ( rule__File__TagsAssignment_1_2 ) ) ;
    public final void rule__File__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:666:1: ( ( ( rule__File__TagsAssignment_1_2 ) ) )
            // InternalYAMLParser.g:667:1: ( ( rule__File__TagsAssignment_1_2 ) )
            {
            // InternalYAMLParser.g:667:1: ( ( rule__File__TagsAssignment_1_2 ) )
            // InternalYAMLParser.g:668:2: ( rule__File__TagsAssignment_1_2 )
            {
             before(grammarAccess.getFileAccess().getTagsAssignment_1_2()); 
            // InternalYAMLParser.g:669:2: ( rule__File__TagsAssignment_1_2 )
            // InternalYAMLParser.g:669:3: rule__File__TagsAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__File__TagsAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getFileAccess().getTagsAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__2__Impl"


    // $ANTLR start "rule__File__Group_1__3"
    // InternalYAMLParser.g:677:1: rule__File__Group_1__3 : rule__File__Group_1__3__Impl ;
    public final void rule__File__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:681:1: ( rule__File__Group_1__3__Impl )
            // InternalYAMLParser.g:682:2: rule__File__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__File__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__3"


    // $ANTLR start "rule__File__Group_1__3__Impl"
    // InternalYAMLParser.g:688:1: rule__File__Group_1__3__Impl : ( RULE_WS ) ;
    public final void rule__File__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:692:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:693:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:693:1: ( RULE_WS )
            // InternalYAMLParser.g:694:2: RULE_WS
            {
             before(grammarAccess.getFileAccess().getWSTerminalRuleCall_1_3()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getFileAccess().getWSTerminalRuleCall_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_1__3__Impl"


    // $ANTLR start "rule__File__Group_2__0"
    // InternalYAMLParser.g:704:1: rule__File__Group_2__0 : rule__File__Group_2__0__Impl rule__File__Group_2__1 ;
    public final void rule__File__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:708:1: ( rule__File__Group_2__0__Impl rule__File__Group_2__1 )
            // InternalYAMLParser.g:709:2: rule__File__Group_2__0__Impl rule__File__Group_2__1
            {
            pushFollow(FOLLOW_5);
            rule__File__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__File__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_2__0"


    // $ANTLR start "rule__File__Group_2__0__Impl"
    // InternalYAMLParser.g:716:1: rule__File__Group_2__0__Impl : ( ( rule__File__NodesAssignment_2_0 ) ) ;
    public final void rule__File__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:720:1: ( ( ( rule__File__NodesAssignment_2_0 ) ) )
            // InternalYAMLParser.g:721:1: ( ( rule__File__NodesAssignment_2_0 ) )
            {
            // InternalYAMLParser.g:721:1: ( ( rule__File__NodesAssignment_2_0 ) )
            // InternalYAMLParser.g:722:2: ( rule__File__NodesAssignment_2_0 )
            {
             before(grammarAccess.getFileAccess().getNodesAssignment_2_0()); 
            // InternalYAMLParser.g:723:2: ( rule__File__NodesAssignment_2_0 )
            // InternalYAMLParser.g:723:3: rule__File__NodesAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__File__NodesAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getFileAccess().getNodesAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_2__0__Impl"


    // $ANTLR start "rule__File__Group_2__1"
    // InternalYAMLParser.g:731:1: rule__File__Group_2__1 : rule__File__Group_2__1__Impl ;
    public final void rule__File__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:735:1: ( rule__File__Group_2__1__Impl )
            // InternalYAMLParser.g:736:2: rule__File__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__File__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_2__1"


    // $ANTLR start "rule__File__Group_2__1__Impl"
    // InternalYAMLParser.g:742:1: rule__File__Group_2__1__Impl : ( ( RULE_WS )? ) ;
    public final void rule__File__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:746:1: ( ( ( RULE_WS )? ) )
            // InternalYAMLParser.g:747:1: ( ( RULE_WS )? )
            {
            // InternalYAMLParser.g:747:1: ( ( RULE_WS )? )
            // InternalYAMLParser.g:748:2: ( RULE_WS )?
            {
             before(grammarAccess.getFileAccess().getWSTerminalRuleCall_2_1()); 
            // InternalYAMLParser.g:749:2: ( RULE_WS )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_WS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalYAMLParser.g:749:3: RULE_WS
                    {
                    match(input,RULE_WS,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getFileAccess().getWSTerminalRuleCall_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__Group_2__1__Impl"


    // $ANTLR start "rule__Document__Group__0"
    // InternalYAMLParser.g:758:1: rule__Document__Group__0 : rule__Document__Group__0__Impl rule__Document__Group__1 ;
    public final void rule__Document__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:762:1: ( rule__Document__Group__0__Impl rule__Document__Group__1 )
            // InternalYAMLParser.g:763:2: rule__Document__Group__0__Impl rule__Document__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__Document__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__0"


    // $ANTLR start "rule__Document__Group__0__Impl"
    // InternalYAMLParser.g:770:1: rule__Document__Group__0__Impl : ( () ) ;
    public final void rule__Document__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:774:1: ( ( () ) )
            // InternalYAMLParser.g:775:1: ( () )
            {
            // InternalYAMLParser.g:775:1: ( () )
            // InternalYAMLParser.g:776:2: ()
            {
             before(grammarAccess.getDocumentAccess().getDocumentAction_0()); 
            // InternalYAMLParser.g:777:2: ()
            // InternalYAMLParser.g:777:3: 
            {
            }

             after(grammarAccess.getDocumentAccess().getDocumentAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__0__Impl"


    // $ANTLR start "rule__Document__Group__1"
    // InternalYAMLParser.g:785:1: rule__Document__Group__1 : rule__Document__Group__1__Impl rule__Document__Group__2 ;
    public final void rule__Document__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:789:1: ( rule__Document__Group__1__Impl rule__Document__Group__2 )
            // InternalYAMLParser.g:790:2: rule__Document__Group__1__Impl rule__Document__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__Document__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__1"


    // $ANTLR start "rule__Document__Group__1__Impl"
    // InternalYAMLParser.g:797:1: rule__Document__Group__1__Impl : ( HyphenMinusHyphenMinusHyphenMinus ) ;
    public final void rule__Document__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:801:1: ( ( HyphenMinusHyphenMinusHyphenMinus ) )
            // InternalYAMLParser.g:802:1: ( HyphenMinusHyphenMinusHyphenMinus )
            {
            // InternalYAMLParser.g:802:1: ( HyphenMinusHyphenMinusHyphenMinus )
            // InternalYAMLParser.g:803:2: HyphenMinusHyphenMinusHyphenMinus
            {
             before(grammarAccess.getDocumentAccess().getHyphenMinusHyphenMinusHyphenMinusKeyword_1()); 
            match(input,HyphenMinusHyphenMinusHyphenMinus,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getHyphenMinusHyphenMinusHyphenMinusKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__1__Impl"


    // $ANTLR start "rule__Document__Group__2"
    // InternalYAMLParser.g:812:1: rule__Document__Group__2 : rule__Document__Group__2__Impl rule__Document__Group__3 ;
    public final void rule__Document__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:816:1: ( rule__Document__Group__2__Impl rule__Document__Group__3 )
            // InternalYAMLParser.g:817:2: rule__Document__Group__2__Impl rule__Document__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__Document__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__2"


    // $ANTLR start "rule__Document__Group__2__Impl"
    // InternalYAMLParser.g:824:1: rule__Document__Group__2__Impl : ( RULE_SPACE ) ;
    public final void rule__Document__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:828:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:829:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:829:1: ( RULE_SPACE )
            // InternalYAMLParser.g:830:2: RULE_SPACE
            {
             before(grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_2()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__2__Impl"


    // $ANTLR start "rule__Document__Group__3"
    // InternalYAMLParser.g:839:1: rule__Document__Group__3 : rule__Document__Group__3__Impl rule__Document__Group__4 ;
    public final void rule__Document__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:843:1: ( rule__Document__Group__3__Impl rule__Document__Group__4 )
            // InternalYAMLParser.g:844:2: rule__Document__Group__3__Impl rule__Document__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__Document__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__3"


    // $ANTLR start "rule__Document__Group__3__Impl"
    // InternalYAMLParser.g:851:1: rule__Document__Group__3__Impl : ( U ) ;
    public final void rule__Document__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:855:1: ( ( U ) )
            // InternalYAMLParser.g:856:1: ( U )
            {
            // InternalYAMLParser.g:856:1: ( U )
            // InternalYAMLParser.g:857:2: U
            {
             before(grammarAccess.getDocumentAccess().getUKeyword_3()); 
            match(input,U,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getUKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__3__Impl"


    // $ANTLR start "rule__Document__Group__4"
    // InternalYAMLParser.g:866:1: rule__Document__Group__4 : rule__Document__Group__4__Impl rule__Document__Group__5 ;
    public final void rule__Document__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:870:1: ( rule__Document__Group__4__Impl rule__Document__Group__5 )
            // InternalYAMLParser.g:871:2: rule__Document__Group__4__Impl rule__Document__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__Document__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__4"


    // $ANTLR start "rule__Document__Group__4__Impl"
    // InternalYAMLParser.g:878:1: rule__Document__Group__4__Impl : ( ( rule__Document__TagAssignment_4 ) ) ;
    public final void rule__Document__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:882:1: ( ( ( rule__Document__TagAssignment_4 ) ) )
            // InternalYAMLParser.g:883:1: ( ( rule__Document__TagAssignment_4 ) )
            {
            // InternalYAMLParser.g:883:1: ( ( rule__Document__TagAssignment_4 ) )
            // InternalYAMLParser.g:884:2: ( rule__Document__TagAssignment_4 )
            {
             before(grammarAccess.getDocumentAccess().getTagAssignment_4()); 
            // InternalYAMLParser.g:885:2: ( rule__Document__TagAssignment_4 )
            // InternalYAMLParser.g:885:3: rule__Document__TagAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Document__TagAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getDocumentAccess().getTagAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__4__Impl"


    // $ANTLR start "rule__Document__Group__5"
    // InternalYAMLParser.g:893:1: rule__Document__Group__5 : rule__Document__Group__5__Impl rule__Document__Group__6 ;
    public final void rule__Document__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:897:1: ( rule__Document__Group__5__Impl rule__Document__Group__6 )
            // InternalYAMLParser.g:898:2: rule__Document__Group__5__Impl rule__Document__Group__6
            {
            pushFollow(FOLLOW_11);
            rule__Document__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__5"


    // $ANTLR start "rule__Document__Group__5__Impl"
    // InternalYAMLParser.g:905:1: rule__Document__Group__5__Impl : ( RULE_SPACE ) ;
    public final void rule__Document__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:909:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:910:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:910:1: ( RULE_SPACE )
            // InternalYAMLParser.g:911:2: RULE_SPACE
            {
             before(grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_5()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__5__Impl"


    // $ANTLR start "rule__Document__Group__6"
    // InternalYAMLParser.g:920:1: rule__Document__Group__6 : rule__Document__Group__6__Impl rule__Document__Group__7 ;
    public final void rule__Document__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:924:1: ( rule__Document__Group__6__Impl rule__Document__Group__7 )
            // InternalYAMLParser.g:925:2: rule__Document__Group__6__Impl rule__Document__Group__7
            {
            pushFollow(FOLLOW_10);
            rule__Document__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__6"


    // $ANTLR start "rule__Document__Group__6__Impl"
    // InternalYAMLParser.g:932:1: rule__Document__Group__6__Impl : ( Ampersand ) ;
    public final void rule__Document__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:936:1: ( ( Ampersand ) )
            // InternalYAMLParser.g:937:1: ( Ampersand )
            {
            // InternalYAMLParser.g:937:1: ( Ampersand )
            // InternalYAMLParser.g:938:2: Ampersand
            {
             before(grammarAccess.getDocumentAccess().getAmpersandKeyword_6()); 
            match(input,Ampersand,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getAmpersandKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__6__Impl"


    // $ANTLR start "rule__Document__Group__7"
    // InternalYAMLParser.g:947:1: rule__Document__Group__7 : rule__Document__Group__7__Impl rule__Document__Group__8 ;
    public final void rule__Document__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:951:1: ( rule__Document__Group__7__Impl rule__Document__Group__8 )
            // InternalYAMLParser.g:952:2: rule__Document__Group__7__Impl rule__Document__Group__8
            {
            pushFollow(FOLLOW_5);
            rule__Document__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__7"


    // $ANTLR start "rule__Document__Group__7__Impl"
    // InternalYAMLParser.g:959:1: rule__Document__Group__7__Impl : ( ( rule__Document__IdAssignment_7 ) ) ;
    public final void rule__Document__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:963:1: ( ( ( rule__Document__IdAssignment_7 ) ) )
            // InternalYAMLParser.g:964:1: ( ( rule__Document__IdAssignment_7 ) )
            {
            // InternalYAMLParser.g:964:1: ( ( rule__Document__IdAssignment_7 ) )
            // InternalYAMLParser.g:965:2: ( rule__Document__IdAssignment_7 )
            {
             before(grammarAccess.getDocumentAccess().getIdAssignment_7()); 
            // InternalYAMLParser.g:966:2: ( rule__Document__IdAssignment_7 )
            // InternalYAMLParser.g:966:3: rule__Document__IdAssignment_7
            {
            pushFollow(FOLLOW_2);
            rule__Document__IdAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getDocumentAccess().getIdAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__7__Impl"


    // $ANTLR start "rule__Document__Group__8"
    // InternalYAMLParser.g:974:1: rule__Document__Group__8 : rule__Document__Group__8__Impl rule__Document__Group__9 ;
    public final void rule__Document__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:978:1: ( rule__Document__Group__8__Impl rule__Document__Group__9 )
            // InternalYAMLParser.g:979:2: rule__Document__Group__8__Impl rule__Document__Group__9
            {
            pushFollow(FOLLOW_12);
            rule__Document__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Document__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__8"


    // $ANTLR start "rule__Document__Group__8__Impl"
    // InternalYAMLParser.g:986:1: rule__Document__Group__8__Impl : ( RULE_WS ) ;
    public final void rule__Document__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:990:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:991:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:991:1: ( RULE_WS )
            // InternalYAMLParser.g:992:2: RULE_WS
            {
             before(grammarAccess.getDocumentAccess().getWSTerminalRuleCall_8()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getWSTerminalRuleCall_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__8__Impl"


    // $ANTLR start "rule__Document__Group__9"
    // InternalYAMLParser.g:1001:1: rule__Document__Group__9 : rule__Document__Group__9__Impl ;
    public final void rule__Document__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1005:1: ( rule__Document__Group__9__Impl )
            // InternalYAMLParser.g:1006:2: rule__Document__Group__9__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Document__Group__9__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__9"


    // $ANTLR start "rule__Document__Group__9__Impl"
    // InternalYAMLParser.g:1012:1: rule__Document__Group__9__Impl : ( ( rule__Document__ValueAssignment_9 ) ) ;
    public final void rule__Document__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1016:1: ( ( ( rule__Document__ValueAssignment_9 ) ) )
            // InternalYAMLParser.g:1017:1: ( ( rule__Document__ValueAssignment_9 ) )
            {
            // InternalYAMLParser.g:1017:1: ( ( rule__Document__ValueAssignment_9 ) )
            // InternalYAMLParser.g:1018:2: ( rule__Document__ValueAssignment_9 )
            {
             before(grammarAccess.getDocumentAccess().getValueAssignment_9()); 
            // InternalYAMLParser.g:1019:2: ( rule__Document__ValueAssignment_9 )
            // InternalYAMLParser.g:1019:3: rule__Document__ValueAssignment_9
            {
            pushFollow(FOLLOW_2);
            rule__Document__ValueAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getDocumentAccess().getValueAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__Group__9__Impl"


    // $ANTLR start "rule__KeyValue__Group__0"
    // InternalYAMLParser.g:1028:1: rule__KeyValue__Group__0 : rule__KeyValue__Group__0__Impl rule__KeyValue__Group__1 ;
    public final void rule__KeyValue__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1032:1: ( rule__KeyValue__Group__0__Impl rule__KeyValue__Group__1 )
            // InternalYAMLParser.g:1033:2: rule__KeyValue__Group__0__Impl rule__KeyValue__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__KeyValue__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValue__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__0"


    // $ANTLR start "rule__KeyValue__Group__0__Impl"
    // InternalYAMLParser.g:1040:1: rule__KeyValue__Group__0__Impl : ( ( rule__KeyValue__KeyAssignment_0 ) ) ;
    public final void rule__KeyValue__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1044:1: ( ( ( rule__KeyValue__KeyAssignment_0 ) ) )
            // InternalYAMLParser.g:1045:1: ( ( rule__KeyValue__KeyAssignment_0 ) )
            {
            // InternalYAMLParser.g:1045:1: ( ( rule__KeyValue__KeyAssignment_0 ) )
            // InternalYAMLParser.g:1046:2: ( rule__KeyValue__KeyAssignment_0 )
            {
             before(grammarAccess.getKeyValueAccess().getKeyAssignment_0()); 
            // InternalYAMLParser.g:1047:2: ( rule__KeyValue__KeyAssignment_0 )
            // InternalYAMLParser.g:1047:3: rule__KeyValue__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValue__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__0__Impl"


    // $ANTLR start "rule__KeyValue__Group__1"
    // InternalYAMLParser.g:1055:1: rule__KeyValue__Group__1 : rule__KeyValue__Group__1__Impl rule__KeyValue__Group__2 ;
    public final void rule__KeyValue__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1059:1: ( rule__KeyValue__Group__1__Impl rule__KeyValue__Group__2 )
            // InternalYAMLParser.g:1060:2: rule__KeyValue__Group__1__Impl rule__KeyValue__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__KeyValue__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValue__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__1"


    // $ANTLR start "rule__KeyValue__Group__1__Impl"
    // InternalYAMLParser.g:1067:1: rule__KeyValue__Group__1__Impl : ( Colon ) ;
    public final void rule__KeyValue__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1071:1: ( ( Colon ) )
            // InternalYAMLParser.g:1072:1: ( Colon )
            {
            // InternalYAMLParser.g:1072:1: ( Colon )
            // InternalYAMLParser.g:1073:2: Colon
            {
             before(grammarAccess.getKeyValueAccess().getColonKeyword_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getKeyValueAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__1__Impl"


    // $ANTLR start "rule__KeyValue__Group__2"
    // InternalYAMLParser.g:1082:1: rule__KeyValue__Group__2 : rule__KeyValue__Group__2__Impl rule__KeyValue__Group__3 ;
    public final void rule__KeyValue__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1086:1: ( rule__KeyValue__Group__2__Impl rule__KeyValue__Group__3 )
            // InternalYAMLParser.g:1087:2: rule__KeyValue__Group__2__Impl rule__KeyValue__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__KeyValue__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValue__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__2"


    // $ANTLR start "rule__KeyValue__Group__2__Impl"
    // InternalYAMLParser.g:1094:1: rule__KeyValue__Group__2__Impl : ( RULE_SPACE ) ;
    public final void rule__KeyValue__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1098:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:1099:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:1099:1: ( RULE_SPACE )
            // InternalYAMLParser.g:1100:2: RULE_SPACE
            {
             before(grammarAccess.getKeyValueAccess().getSPACETerminalRuleCall_2()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getKeyValueAccess().getSPACETerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__2__Impl"


    // $ANTLR start "rule__KeyValue__Group__3"
    // InternalYAMLParser.g:1109:1: rule__KeyValue__Group__3 : rule__KeyValue__Group__3__Impl ;
    public final void rule__KeyValue__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1113:1: ( rule__KeyValue__Group__3__Impl )
            // InternalYAMLParser.g:1114:2: rule__KeyValue__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__KeyValue__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__3"


    // $ANTLR start "rule__KeyValue__Group__3__Impl"
    // InternalYAMLParser.g:1120:1: rule__KeyValue__Group__3__Impl : ( ( rule__KeyValue__ValueAssignment_3 )? ) ;
    public final void rule__KeyValue__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1124:1: ( ( ( rule__KeyValue__ValueAssignment_3 )? ) )
            // InternalYAMLParser.g:1125:1: ( ( rule__KeyValue__ValueAssignment_3 )? )
            {
            // InternalYAMLParser.g:1125:1: ( ( rule__KeyValue__ValueAssignment_3 )? )
            // InternalYAMLParser.g:1126:2: ( rule__KeyValue__ValueAssignment_3 )?
            {
             before(grammarAccess.getKeyValueAccess().getValueAssignment_3()); 
            // InternalYAMLParser.g:1127:2: ( rule__KeyValue__ValueAssignment_3 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==LeftSquareBracket||LA9_0==LeftCurlyBracket||(LA9_0>=RULE_L && LA9_0<=RULE_GUID)) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_WORD) ) {
                int LA9_2 = input.LA(2);

                if ( (LA9_2==EOF||LA9_2==HyphenMinusHyphenMinusHyphenMinus||LA9_2==Comma||LA9_2==Semicolon||(LA9_2>=RightCurlyBracket && LA9_2<=RULE_WORD)||LA9_2==RULE_SPACE||LA9_2==RULE_WS||LA9_2==RULE_END) ) {
                    alt9=1;
                }
            }
            switch (alt9) {
                case 1 :
                    // InternalYAMLParser.g:1127:3: rule__KeyValue__ValueAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__KeyValue__ValueAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKeyValueAccess().getValueAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__Group__3__Impl"


    // $ANTLR start "rule__Map__Group__0"
    // InternalYAMLParser.g:1136:1: rule__Map__Group__0 : rule__Map__Group__0__Impl rule__Map__Group__1 ;
    public final void rule__Map__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1140:1: ( rule__Map__Group__0__Impl rule__Map__Group__1 )
            // InternalYAMLParser.g:1141:2: rule__Map__Group__0__Impl rule__Map__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__Map__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__0"


    // $ANTLR start "rule__Map__Group__0__Impl"
    // InternalYAMLParser.g:1148:1: rule__Map__Group__0__Impl : ( ( rule__Map__KeyAssignment_0 ) ) ;
    public final void rule__Map__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1152:1: ( ( ( rule__Map__KeyAssignment_0 ) ) )
            // InternalYAMLParser.g:1153:1: ( ( rule__Map__KeyAssignment_0 ) )
            {
            // InternalYAMLParser.g:1153:1: ( ( rule__Map__KeyAssignment_0 ) )
            // InternalYAMLParser.g:1154:2: ( rule__Map__KeyAssignment_0 )
            {
             before(grammarAccess.getMapAccess().getKeyAssignment_0()); 
            // InternalYAMLParser.g:1155:2: ( rule__Map__KeyAssignment_0 )
            // InternalYAMLParser.g:1155:3: rule__Map__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Map__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMapAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__0__Impl"


    // $ANTLR start "rule__Map__Group__1"
    // InternalYAMLParser.g:1163:1: rule__Map__Group__1 : rule__Map__Group__1__Impl rule__Map__Group__2 ;
    public final void rule__Map__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1167:1: ( rule__Map__Group__1__Impl rule__Map__Group__2 )
            // InternalYAMLParser.g:1168:2: rule__Map__Group__1__Impl rule__Map__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Map__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__1"


    // $ANTLR start "rule__Map__Group__1__Impl"
    // InternalYAMLParser.g:1175:1: rule__Map__Group__1__Impl : ( Colon ) ;
    public final void rule__Map__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1179:1: ( ( Colon ) )
            // InternalYAMLParser.g:1180:1: ( Colon )
            {
            // InternalYAMLParser.g:1180:1: ( Colon )
            // InternalYAMLParser.g:1181:2: Colon
            {
             before(grammarAccess.getMapAccess().getColonKeyword_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__1__Impl"


    // $ANTLR start "rule__Map__Group__2"
    // InternalYAMLParser.g:1190:1: rule__Map__Group__2 : rule__Map__Group__2__Impl rule__Map__Group__3 ;
    public final void rule__Map__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1194:1: ( rule__Map__Group__2__Impl rule__Map__Group__3 )
            // InternalYAMLParser.g:1195:2: rule__Map__Group__2__Impl rule__Map__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__Map__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__2"


    // $ANTLR start "rule__Map__Group__2__Impl"
    // InternalYAMLParser.g:1202:1: rule__Map__Group__2__Impl : ( RULE_WS ) ;
    public final void rule__Map__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1206:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:1207:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:1207:1: ( RULE_WS )
            // InternalYAMLParser.g:1208:2: RULE_WS
            {
             before(grammarAccess.getMapAccess().getWSTerminalRuleCall_2()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getWSTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__2__Impl"


    // $ANTLR start "rule__Map__Group__3"
    // InternalYAMLParser.g:1217:1: rule__Map__Group__3 : rule__Map__Group__3__Impl rule__Map__Group__4 ;
    public final void rule__Map__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1221:1: ( rule__Map__Group__3__Impl rule__Map__Group__4 )
            // InternalYAMLParser.g:1222:2: rule__Map__Group__3__Impl rule__Map__Group__4
            {
            pushFollow(FOLLOW_16);
            rule__Map__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__3"


    // $ANTLR start "rule__Map__Group__3__Impl"
    // InternalYAMLParser.g:1229:1: rule__Map__Group__3__Impl : ( RULE_BEGIN ) ;
    public final void rule__Map__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1233:1: ( ( RULE_BEGIN ) )
            // InternalYAMLParser.g:1234:1: ( RULE_BEGIN )
            {
            // InternalYAMLParser.g:1234:1: ( RULE_BEGIN )
            // InternalYAMLParser.g:1235:2: RULE_BEGIN
            {
             before(grammarAccess.getMapAccess().getBEGINTerminalRuleCall_3()); 
            match(input,RULE_BEGIN,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getBEGINTerminalRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__3__Impl"


    // $ANTLR start "rule__Map__Group__4"
    // InternalYAMLParser.g:1244:1: rule__Map__Group__4 : rule__Map__Group__4__Impl rule__Map__Group__5 ;
    public final void rule__Map__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1248:1: ( rule__Map__Group__4__Impl rule__Map__Group__5 )
            // InternalYAMLParser.g:1249:2: rule__Map__Group__4__Impl rule__Map__Group__5
            {
            pushFollow(FOLLOW_17);
            rule__Map__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__4"


    // $ANTLR start "rule__Map__Group__4__Impl"
    // InternalYAMLParser.g:1256:1: rule__Map__Group__4__Impl : ( ( rule__Map__BodyAssignment_4 ) ) ;
    public final void rule__Map__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1260:1: ( ( ( rule__Map__BodyAssignment_4 ) ) )
            // InternalYAMLParser.g:1261:1: ( ( rule__Map__BodyAssignment_4 ) )
            {
            // InternalYAMLParser.g:1261:1: ( ( rule__Map__BodyAssignment_4 ) )
            // InternalYAMLParser.g:1262:2: ( rule__Map__BodyAssignment_4 )
            {
             before(grammarAccess.getMapAccess().getBodyAssignment_4()); 
            // InternalYAMLParser.g:1263:2: ( rule__Map__BodyAssignment_4 )
            // InternalYAMLParser.g:1263:3: rule__Map__BodyAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Map__BodyAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getMapAccess().getBodyAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__4__Impl"


    // $ANTLR start "rule__Map__Group__5"
    // InternalYAMLParser.g:1271:1: rule__Map__Group__5 : rule__Map__Group__5__Impl rule__Map__Group__6 ;
    public final void rule__Map__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1275:1: ( rule__Map__Group__5__Impl rule__Map__Group__6 )
            // InternalYAMLParser.g:1276:2: rule__Map__Group__5__Impl rule__Map__Group__6
            {
            pushFollow(FOLLOW_17);
            rule__Map__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__5"


    // $ANTLR start "rule__Map__Group__5__Impl"
    // InternalYAMLParser.g:1283:1: rule__Map__Group__5__Impl : ( ( rule__Map__Group_5__0 )* ) ;
    public final void rule__Map__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1287:1: ( ( ( rule__Map__Group_5__0 )* ) )
            // InternalYAMLParser.g:1288:1: ( ( rule__Map__Group_5__0 )* )
            {
            // InternalYAMLParser.g:1288:1: ( ( rule__Map__Group_5__0 )* )
            // InternalYAMLParser.g:1289:2: ( rule__Map__Group_5__0 )*
            {
             before(grammarAccess.getMapAccess().getGroup_5()); 
            // InternalYAMLParser.g:1290:2: ( rule__Map__Group_5__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_WS) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==RULE_WORD) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // InternalYAMLParser.g:1290:3: rule__Map__Group_5__0
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__Map__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getMapAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__5__Impl"


    // $ANTLR start "rule__Map__Group__6"
    // InternalYAMLParser.g:1298:1: rule__Map__Group__6 : rule__Map__Group__6__Impl rule__Map__Group__7 ;
    public final void rule__Map__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1302:1: ( rule__Map__Group__6__Impl rule__Map__Group__7 )
            // InternalYAMLParser.g:1303:2: rule__Map__Group__6__Impl rule__Map__Group__7
            {
            pushFollow(FOLLOW_17);
            rule__Map__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__6"


    // $ANTLR start "rule__Map__Group__6__Impl"
    // InternalYAMLParser.g:1310:1: rule__Map__Group__6__Impl : ( ( RULE_WS )? ) ;
    public final void rule__Map__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1314:1: ( ( ( RULE_WS )? ) )
            // InternalYAMLParser.g:1315:1: ( ( RULE_WS )? )
            {
            // InternalYAMLParser.g:1315:1: ( ( RULE_WS )? )
            // InternalYAMLParser.g:1316:2: ( RULE_WS )?
            {
             before(grammarAccess.getMapAccess().getWSTerminalRuleCall_6()); 
            // InternalYAMLParser.g:1317:2: ( RULE_WS )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_WS) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalYAMLParser.g:1317:3: RULE_WS
                    {
                    match(input,RULE_WS,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getMapAccess().getWSTerminalRuleCall_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__6__Impl"


    // $ANTLR start "rule__Map__Group__7"
    // InternalYAMLParser.g:1325:1: rule__Map__Group__7 : rule__Map__Group__7__Impl ;
    public final void rule__Map__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1329:1: ( rule__Map__Group__7__Impl )
            // InternalYAMLParser.g:1330:2: rule__Map__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Map__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__7"


    // $ANTLR start "rule__Map__Group__7__Impl"
    // InternalYAMLParser.g:1336:1: rule__Map__Group__7__Impl : ( RULE_END ) ;
    public final void rule__Map__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1340:1: ( ( RULE_END ) )
            // InternalYAMLParser.g:1341:1: ( RULE_END )
            {
            // InternalYAMLParser.g:1341:1: ( RULE_END )
            // InternalYAMLParser.g:1342:2: RULE_END
            {
             before(grammarAccess.getMapAccess().getENDTerminalRuleCall_7()); 
            match(input,RULE_END,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getENDTerminalRuleCall_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group__7__Impl"


    // $ANTLR start "rule__Map__Group_5__0"
    // InternalYAMLParser.g:1352:1: rule__Map__Group_5__0 : rule__Map__Group_5__0__Impl rule__Map__Group_5__1 ;
    public final void rule__Map__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1356:1: ( rule__Map__Group_5__0__Impl rule__Map__Group_5__1 )
            // InternalYAMLParser.g:1357:2: rule__Map__Group_5__0__Impl rule__Map__Group_5__1
            {
            pushFollow(FOLLOW_16);
            rule__Map__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Map__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group_5__0"


    // $ANTLR start "rule__Map__Group_5__0__Impl"
    // InternalYAMLParser.g:1364:1: rule__Map__Group_5__0__Impl : ( RULE_WS ) ;
    public final void rule__Map__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1368:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:1369:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:1369:1: ( RULE_WS )
            // InternalYAMLParser.g:1370:2: RULE_WS
            {
             before(grammarAccess.getMapAccess().getWSTerminalRuleCall_5_0()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getWSTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group_5__0__Impl"


    // $ANTLR start "rule__Map__Group_5__1"
    // InternalYAMLParser.g:1379:1: rule__Map__Group_5__1 : rule__Map__Group_5__1__Impl ;
    public final void rule__Map__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1383:1: ( rule__Map__Group_5__1__Impl )
            // InternalYAMLParser.g:1384:2: rule__Map__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Map__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group_5__1"


    // $ANTLR start "rule__Map__Group_5__1__Impl"
    // InternalYAMLParser.g:1390:1: rule__Map__Group_5__1__Impl : ( ( rule__Map__BodyAssignment_5_1 ) ) ;
    public final void rule__Map__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1394:1: ( ( ( rule__Map__BodyAssignment_5_1 ) ) )
            // InternalYAMLParser.g:1395:1: ( ( rule__Map__BodyAssignment_5_1 ) )
            {
            // InternalYAMLParser.g:1395:1: ( ( rule__Map__BodyAssignment_5_1 ) )
            // InternalYAMLParser.g:1396:2: ( rule__Map__BodyAssignment_5_1 )
            {
             before(grammarAccess.getMapAccess().getBodyAssignment_5_1()); 
            // InternalYAMLParser.g:1397:2: ( rule__Map__BodyAssignment_5_1 )
            // InternalYAMLParser.g:1397:3: rule__Map__BodyAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Map__BodyAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getMapAccess().getBodyAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__Group_5__1__Impl"


    // $ANTLR start "rule__KeyValueList__Group__0"
    // InternalYAMLParser.g:1406:1: rule__KeyValueList__Group__0 : rule__KeyValueList__Group__0__Impl rule__KeyValueList__Group__1 ;
    public final void rule__KeyValueList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1410:1: ( rule__KeyValueList__Group__0__Impl rule__KeyValueList__Group__1 )
            // InternalYAMLParser.g:1411:2: rule__KeyValueList__Group__0__Impl rule__KeyValueList__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__KeyValueList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__0"


    // $ANTLR start "rule__KeyValueList__Group__0__Impl"
    // InternalYAMLParser.g:1418:1: rule__KeyValueList__Group__0__Impl : ( ( rule__KeyValueList__NameAssignment_0 ) ) ;
    public final void rule__KeyValueList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1422:1: ( ( ( rule__KeyValueList__NameAssignment_0 ) ) )
            // InternalYAMLParser.g:1423:1: ( ( rule__KeyValueList__NameAssignment_0 ) )
            {
            // InternalYAMLParser.g:1423:1: ( ( rule__KeyValueList__NameAssignment_0 ) )
            // InternalYAMLParser.g:1424:2: ( rule__KeyValueList__NameAssignment_0 )
            {
             before(grammarAccess.getKeyValueListAccess().getNameAssignment_0()); 
            // InternalYAMLParser.g:1425:2: ( rule__KeyValueList__NameAssignment_0 )
            // InternalYAMLParser.g:1425:3: rule__KeyValueList__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__0__Impl"


    // $ANTLR start "rule__KeyValueList__Group__1"
    // InternalYAMLParser.g:1433:1: rule__KeyValueList__Group__1 : rule__KeyValueList__Group__1__Impl rule__KeyValueList__Group__2 ;
    public final void rule__KeyValueList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1437:1: ( rule__KeyValueList__Group__1__Impl rule__KeyValueList__Group__2 )
            // InternalYAMLParser.g:1438:2: rule__KeyValueList__Group__1__Impl rule__KeyValueList__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__KeyValueList__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__1"


    // $ANTLR start "rule__KeyValueList__Group__1__Impl"
    // InternalYAMLParser.g:1445:1: rule__KeyValueList__Group__1__Impl : ( Colon ) ;
    public final void rule__KeyValueList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1449:1: ( ( Colon ) )
            // InternalYAMLParser.g:1450:1: ( Colon )
            {
            // InternalYAMLParser.g:1450:1: ( Colon )
            // InternalYAMLParser.g:1451:2: Colon
            {
             before(grammarAccess.getKeyValueListAccess().getColonKeyword_1()); 
            match(input,Colon,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__1__Impl"


    // $ANTLR start "rule__KeyValueList__Group__2"
    // InternalYAMLParser.g:1460:1: rule__KeyValueList__Group__2 : rule__KeyValueList__Group__2__Impl rule__KeyValueList__Group__3 ;
    public final void rule__KeyValueList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1464:1: ( rule__KeyValueList__Group__2__Impl rule__KeyValueList__Group__3 )
            // InternalYAMLParser.g:1465:2: rule__KeyValueList__Group__2__Impl rule__KeyValueList__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__KeyValueList__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__2"


    // $ANTLR start "rule__KeyValueList__Group__2__Impl"
    // InternalYAMLParser.g:1472:1: rule__KeyValueList__Group__2__Impl : ( RULE_WS ) ;
    public final void rule__KeyValueList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1476:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:1477:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:1477:1: ( RULE_WS )
            // InternalYAMLParser.g:1478:2: RULE_WS
            {
             before(grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_2()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__2__Impl"


    // $ANTLR start "rule__KeyValueList__Group__3"
    // InternalYAMLParser.g:1487:1: rule__KeyValueList__Group__3 : rule__KeyValueList__Group__3__Impl rule__KeyValueList__Group__4 ;
    public final void rule__KeyValueList__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1491:1: ( rule__KeyValueList__Group__3__Impl rule__KeyValueList__Group__4 )
            // InternalYAMLParser.g:1492:2: rule__KeyValueList__Group__3__Impl rule__KeyValueList__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__KeyValueList__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__3"


    // $ANTLR start "rule__KeyValueList__Group__3__Impl"
    // InternalYAMLParser.g:1499:1: rule__KeyValueList__Group__3__Impl : ( HyphenMinus ) ;
    public final void rule__KeyValueList__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1503:1: ( ( HyphenMinus ) )
            // InternalYAMLParser.g:1504:1: ( HyphenMinus )
            {
            // InternalYAMLParser.g:1504:1: ( HyphenMinus )
            // InternalYAMLParser.g:1505:2: HyphenMinus
            {
             before(grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_3()); 
            match(input,HyphenMinus,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__3__Impl"


    // $ANTLR start "rule__KeyValueList__Group__4"
    // InternalYAMLParser.g:1514:1: rule__KeyValueList__Group__4 : rule__KeyValueList__Group__4__Impl rule__KeyValueList__Group__5 ;
    public final void rule__KeyValueList__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1518:1: ( rule__KeyValueList__Group__4__Impl rule__KeyValueList__Group__5 )
            // InternalYAMLParser.g:1519:2: rule__KeyValueList__Group__4__Impl rule__KeyValueList__Group__5
            {
            pushFollow(FOLLOW_14);
            rule__KeyValueList__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__4"


    // $ANTLR start "rule__KeyValueList__Group__4__Impl"
    // InternalYAMLParser.g:1526:1: rule__KeyValueList__Group__4__Impl : ( RULE_SPACE ) ;
    public final void rule__KeyValueList__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1530:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:1531:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:1531:1: ( RULE_SPACE )
            // InternalYAMLParser.g:1532:2: RULE_SPACE
            {
             before(grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_4()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__4__Impl"


    // $ANTLR start "rule__KeyValueList__Group__5"
    // InternalYAMLParser.g:1541:1: rule__KeyValueList__Group__5 : rule__KeyValueList__Group__5__Impl rule__KeyValueList__Group__6 ;
    public final void rule__KeyValueList__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1545:1: ( rule__KeyValueList__Group__5__Impl rule__KeyValueList__Group__6 )
            // InternalYAMLParser.g:1546:2: rule__KeyValueList__Group__5__Impl rule__KeyValueList__Group__6
            {
            pushFollow(FOLLOW_5);
            rule__KeyValueList__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__5"


    // $ANTLR start "rule__KeyValueList__Group__5__Impl"
    // InternalYAMLParser.g:1553:1: rule__KeyValueList__Group__5__Impl : ( ( rule__KeyValueList__ValuesAssignment_5 ) ) ;
    public final void rule__KeyValueList__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1557:1: ( ( ( rule__KeyValueList__ValuesAssignment_5 ) ) )
            // InternalYAMLParser.g:1558:1: ( ( rule__KeyValueList__ValuesAssignment_5 ) )
            {
            // InternalYAMLParser.g:1558:1: ( ( rule__KeyValueList__ValuesAssignment_5 ) )
            // InternalYAMLParser.g:1559:2: ( rule__KeyValueList__ValuesAssignment_5 )
            {
             before(grammarAccess.getKeyValueListAccess().getValuesAssignment_5()); 
            // InternalYAMLParser.g:1560:2: ( rule__KeyValueList__ValuesAssignment_5 )
            // InternalYAMLParser.g:1560:3: rule__KeyValueList__ValuesAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__ValuesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getValuesAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__5__Impl"


    // $ANTLR start "rule__KeyValueList__Group__6"
    // InternalYAMLParser.g:1568:1: rule__KeyValueList__Group__6 : rule__KeyValueList__Group__6__Impl ;
    public final void rule__KeyValueList__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1572:1: ( rule__KeyValueList__Group__6__Impl )
            // InternalYAMLParser.g:1573:2: rule__KeyValueList__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__6"


    // $ANTLR start "rule__KeyValueList__Group__6__Impl"
    // InternalYAMLParser.g:1579:1: rule__KeyValueList__Group__6__Impl : ( ( rule__KeyValueList__Group_6__0 )* ) ;
    public final void rule__KeyValueList__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1583:1: ( ( ( rule__KeyValueList__Group_6__0 )* ) )
            // InternalYAMLParser.g:1584:1: ( ( rule__KeyValueList__Group_6__0 )* )
            {
            // InternalYAMLParser.g:1584:1: ( ( rule__KeyValueList__Group_6__0 )* )
            // InternalYAMLParser.g:1585:2: ( rule__KeyValueList__Group_6__0 )*
            {
             before(grammarAccess.getKeyValueListAccess().getGroup_6()); 
            // InternalYAMLParser.g:1586:2: ( rule__KeyValueList__Group_6__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_WS) ) {
                    int LA12_2 = input.LA(2);

                    if ( (LA12_2==HyphenMinus) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // InternalYAMLParser.g:1586:3: rule__KeyValueList__Group_6__0
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__KeyValueList__Group_6__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getKeyValueListAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group__6__Impl"


    // $ANTLR start "rule__KeyValueList__Group_6__0"
    // InternalYAMLParser.g:1595:1: rule__KeyValueList__Group_6__0 : rule__KeyValueList__Group_6__0__Impl rule__KeyValueList__Group_6__1 ;
    public final void rule__KeyValueList__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1599:1: ( rule__KeyValueList__Group_6__0__Impl rule__KeyValueList__Group_6__1 )
            // InternalYAMLParser.g:1600:2: rule__KeyValueList__Group_6__0__Impl rule__KeyValueList__Group_6__1
            {
            pushFollow(FOLLOW_19);
            rule__KeyValueList__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__0"


    // $ANTLR start "rule__KeyValueList__Group_6__0__Impl"
    // InternalYAMLParser.g:1607:1: rule__KeyValueList__Group_6__0__Impl : ( RULE_WS ) ;
    public final void rule__KeyValueList__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1611:1: ( ( RULE_WS ) )
            // InternalYAMLParser.g:1612:1: ( RULE_WS )
            {
            // InternalYAMLParser.g:1612:1: ( RULE_WS )
            // InternalYAMLParser.g:1613:2: RULE_WS
            {
             before(grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_6_0()); 
            match(input,RULE_WS,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__0__Impl"


    // $ANTLR start "rule__KeyValueList__Group_6__1"
    // InternalYAMLParser.g:1622:1: rule__KeyValueList__Group_6__1 : rule__KeyValueList__Group_6__1__Impl rule__KeyValueList__Group_6__2 ;
    public final void rule__KeyValueList__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1626:1: ( rule__KeyValueList__Group_6__1__Impl rule__KeyValueList__Group_6__2 )
            // InternalYAMLParser.g:1627:2: rule__KeyValueList__Group_6__1__Impl rule__KeyValueList__Group_6__2
            {
            pushFollow(FOLLOW_8);
            rule__KeyValueList__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group_6__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__1"


    // $ANTLR start "rule__KeyValueList__Group_6__1__Impl"
    // InternalYAMLParser.g:1634:1: rule__KeyValueList__Group_6__1__Impl : ( HyphenMinus ) ;
    public final void rule__KeyValueList__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1638:1: ( ( HyphenMinus ) )
            // InternalYAMLParser.g:1639:1: ( HyphenMinus )
            {
            // InternalYAMLParser.g:1639:1: ( HyphenMinus )
            // InternalYAMLParser.g:1640:2: HyphenMinus
            {
             before(grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_6_1()); 
            match(input,HyphenMinus,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__1__Impl"


    // $ANTLR start "rule__KeyValueList__Group_6__2"
    // InternalYAMLParser.g:1649:1: rule__KeyValueList__Group_6__2 : rule__KeyValueList__Group_6__2__Impl rule__KeyValueList__Group_6__3 ;
    public final void rule__KeyValueList__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1653:1: ( rule__KeyValueList__Group_6__2__Impl rule__KeyValueList__Group_6__3 )
            // InternalYAMLParser.g:1654:2: rule__KeyValueList__Group_6__2__Impl rule__KeyValueList__Group_6__3
            {
            pushFollow(FOLLOW_14);
            rule__KeyValueList__Group_6__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group_6__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__2"


    // $ANTLR start "rule__KeyValueList__Group_6__2__Impl"
    // InternalYAMLParser.g:1661:1: rule__KeyValueList__Group_6__2__Impl : ( RULE_SPACE ) ;
    public final void rule__KeyValueList__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1665:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:1666:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:1666:1: ( RULE_SPACE )
            // InternalYAMLParser.g:1667:2: RULE_SPACE
            {
             before(grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_6_2()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__2__Impl"


    // $ANTLR start "rule__KeyValueList__Group_6__3"
    // InternalYAMLParser.g:1676:1: rule__KeyValueList__Group_6__3 : rule__KeyValueList__Group_6__3__Impl ;
    public final void rule__KeyValueList__Group_6__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1680:1: ( rule__KeyValueList__Group_6__3__Impl )
            // InternalYAMLParser.g:1681:2: rule__KeyValueList__Group_6__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__Group_6__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__3"


    // $ANTLR start "rule__KeyValueList__Group_6__3__Impl"
    // InternalYAMLParser.g:1687:1: rule__KeyValueList__Group_6__3__Impl : ( ( rule__KeyValueList__ValuesAssignment_6_3 ) ) ;
    public final void rule__KeyValueList__Group_6__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1691:1: ( ( ( rule__KeyValueList__ValuesAssignment_6_3 ) ) )
            // InternalYAMLParser.g:1692:1: ( ( rule__KeyValueList__ValuesAssignment_6_3 ) )
            {
            // InternalYAMLParser.g:1692:1: ( ( rule__KeyValueList__ValuesAssignment_6_3 ) )
            // InternalYAMLParser.g:1693:2: ( rule__KeyValueList__ValuesAssignment_6_3 )
            {
             before(grammarAccess.getKeyValueListAccess().getValuesAssignment_6_3()); 
            // InternalYAMLParser.g:1694:2: ( rule__KeyValueList__ValuesAssignment_6_3 )
            // InternalYAMLParser.g:1694:3: rule__KeyValueList__ValuesAssignment_6_3
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__ValuesAssignment_6_3();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getValuesAssignment_6_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__Group_6__3__Impl"


    // $ANTLR start "rule__Element__Group_3__0"
    // InternalYAMLParser.g:1703:1: rule__Element__Group_3__0 : rule__Element__Group_3__0__Impl rule__Element__Group_3__1 ;
    public final void rule__Element__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1707:1: ( rule__Element__Group_3__0__Impl rule__Element__Group_3__1 )
            // InternalYAMLParser.g:1708:2: rule__Element__Group_3__0__Impl rule__Element__Group_3__1
            {
            pushFollow(FOLLOW_12);
            rule__Element__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3__0"


    // $ANTLR start "rule__Element__Group_3__0__Impl"
    // InternalYAMLParser.g:1715:1: rule__Element__Group_3__0__Impl : ( () ) ;
    public final void rule__Element__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1719:1: ( ( () ) )
            // InternalYAMLParser.g:1720:1: ( () )
            {
            // InternalYAMLParser.g:1720:1: ( () )
            // InternalYAMLParser.g:1721:2: ()
            {
             before(grammarAccess.getElementAccess().getWordAction_3_0()); 
            // InternalYAMLParser.g:1722:2: ()
            // InternalYAMLParser.g:1722:3: 
            {
            }

             after(grammarAccess.getElementAccess().getWordAction_3_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3__0__Impl"


    // $ANTLR start "rule__Element__Group_3__1"
    // InternalYAMLParser.g:1730:1: rule__Element__Group_3__1 : rule__Element__Group_3__1__Impl ;
    public final void rule__Element__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1734:1: ( rule__Element__Group_3__1__Impl )
            // InternalYAMLParser.g:1735:2: rule__Element__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3__1"


    // $ANTLR start "rule__Element__Group_3__1__Impl"
    // InternalYAMLParser.g:1741:1: rule__Element__Group_3__1__Impl : ( ( rule__Element__ValueAssignment_3_1 ) ) ;
    public final void rule__Element__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1745:1: ( ( ( rule__Element__ValueAssignment_3_1 ) ) )
            // InternalYAMLParser.g:1746:1: ( ( rule__Element__ValueAssignment_3_1 ) )
            {
            // InternalYAMLParser.g:1746:1: ( ( rule__Element__ValueAssignment_3_1 ) )
            // InternalYAMLParser.g:1747:2: ( rule__Element__ValueAssignment_3_1 )
            {
             before(grammarAccess.getElementAccess().getValueAssignment_3_1()); 
            // InternalYAMLParser.g:1748:2: ( rule__Element__ValueAssignment_3_1 )
            // InternalYAMLParser.g:1748:3: rule__Element__ValueAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Element__ValueAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getValueAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_3__1__Impl"


    // $ANTLR start "rule__Element__Group_4__0"
    // InternalYAMLParser.g:1757:1: rule__Element__Group_4__0 : rule__Element__Group_4__0__Impl rule__Element__Group_4__1 ;
    public final void rule__Element__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1761:1: ( rule__Element__Group_4__0__Impl rule__Element__Group_4__1 )
            // InternalYAMLParser.g:1762:2: rule__Element__Group_4__0__Impl rule__Element__Group_4__1
            {
            pushFollow(FOLLOW_20);
            rule__Element__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__0"


    // $ANTLR start "rule__Element__Group_4__0__Impl"
    // InternalYAMLParser.g:1769:1: rule__Element__Group_4__0__Impl : ( () ) ;
    public final void rule__Element__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1773:1: ( ( () ) )
            // InternalYAMLParser.g:1774:1: ( () )
            {
            // InternalYAMLParser.g:1774:1: ( () )
            // InternalYAMLParser.g:1775:2: ()
            {
             before(grammarAccess.getElementAccess().getFloatAction_4_0()); 
            // InternalYAMLParser.g:1776:2: ()
            // InternalYAMLParser.g:1776:3: 
            {
            }

             after(grammarAccess.getElementAccess().getFloatAction_4_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__0__Impl"


    // $ANTLR start "rule__Element__Group_4__1"
    // InternalYAMLParser.g:1784:1: rule__Element__Group_4__1 : rule__Element__Group_4__1__Impl ;
    public final void rule__Element__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1788:1: ( rule__Element__Group_4__1__Impl )
            // InternalYAMLParser.g:1789:2: rule__Element__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__1"


    // $ANTLR start "rule__Element__Group_4__1__Impl"
    // InternalYAMLParser.g:1795:1: rule__Element__Group_4__1__Impl : ( ( rule__Element__ValueAssignment_4_1 ) ) ;
    public final void rule__Element__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1799:1: ( ( ( rule__Element__ValueAssignment_4_1 ) ) )
            // InternalYAMLParser.g:1800:1: ( ( rule__Element__ValueAssignment_4_1 ) )
            {
            // InternalYAMLParser.g:1800:1: ( ( rule__Element__ValueAssignment_4_1 ) )
            // InternalYAMLParser.g:1801:2: ( rule__Element__ValueAssignment_4_1 )
            {
             before(grammarAccess.getElementAccess().getValueAssignment_4_1()); 
            // InternalYAMLParser.g:1802:2: ( rule__Element__ValueAssignment_4_1 )
            // InternalYAMLParser.g:1802:3: rule__Element__ValueAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Element__ValueAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getValueAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_4__1__Impl"


    // $ANTLR start "rule__Element__Group_5__0"
    // InternalYAMLParser.g:1811:1: rule__Element__Group_5__0 : rule__Element__Group_5__0__Impl rule__Element__Group_5__1 ;
    public final void rule__Element__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1815:1: ( rule__Element__Group_5__0__Impl rule__Element__Group_5__1 )
            // InternalYAMLParser.g:1816:2: rule__Element__Group_5__0__Impl rule__Element__Group_5__1
            {
            pushFollow(FOLLOW_10);
            rule__Element__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_5__0"


    // $ANTLR start "rule__Element__Group_5__0__Impl"
    // InternalYAMLParser.g:1823:1: rule__Element__Group_5__0__Impl : ( () ) ;
    public final void rule__Element__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1827:1: ( ( () ) )
            // InternalYAMLParser.g:1828:1: ( () )
            {
            // InternalYAMLParser.g:1828:1: ( () )
            // InternalYAMLParser.g:1829:2: ()
            {
             before(grammarAccess.getElementAccess().getLongAction_5_0()); 
            // InternalYAMLParser.g:1830:2: ()
            // InternalYAMLParser.g:1830:3: 
            {
            }

             after(grammarAccess.getElementAccess().getLongAction_5_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_5__0__Impl"


    // $ANTLR start "rule__Element__Group_5__1"
    // InternalYAMLParser.g:1838:1: rule__Element__Group_5__1 : rule__Element__Group_5__1__Impl ;
    public final void rule__Element__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1842:1: ( rule__Element__Group_5__1__Impl )
            // InternalYAMLParser.g:1843:2: rule__Element__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_5__1"


    // $ANTLR start "rule__Element__Group_5__1__Impl"
    // InternalYAMLParser.g:1849:1: rule__Element__Group_5__1__Impl : ( ( rule__Element__ValueAssignment_5_1 ) ) ;
    public final void rule__Element__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1853:1: ( ( ( rule__Element__ValueAssignment_5_1 ) ) )
            // InternalYAMLParser.g:1854:1: ( ( rule__Element__ValueAssignment_5_1 ) )
            {
            // InternalYAMLParser.g:1854:1: ( ( rule__Element__ValueAssignment_5_1 ) )
            // InternalYAMLParser.g:1855:2: ( rule__Element__ValueAssignment_5_1 )
            {
             before(grammarAccess.getElementAccess().getValueAssignment_5_1()); 
            // InternalYAMLParser.g:1856:2: ( rule__Element__ValueAssignment_5_1 )
            // InternalYAMLParser.g:1856:3: rule__Element__ValueAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__Element__ValueAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getValueAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_5__1__Impl"


    // $ANTLR start "rule__Element__Group_6__0"
    // InternalYAMLParser.g:1865:1: rule__Element__Group_6__0 : rule__Element__Group_6__0__Impl rule__Element__Group_6__1 ;
    public final void rule__Element__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1869:1: ( rule__Element__Group_6__0__Impl rule__Element__Group_6__1 )
            // InternalYAMLParser.g:1870:2: rule__Element__Group_6__0__Impl rule__Element__Group_6__1
            {
            pushFollow(FOLLOW_14);
            rule__Element__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Element__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_6__0"


    // $ANTLR start "rule__Element__Group_6__0__Impl"
    // InternalYAMLParser.g:1877:1: rule__Element__Group_6__0__Impl : ( () ) ;
    public final void rule__Element__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1881:1: ( ( () ) )
            // InternalYAMLParser.g:1882:1: ( () )
            {
            // InternalYAMLParser.g:1882:1: ( () )
            // InternalYAMLParser.g:1883:2: ()
            {
             before(grammarAccess.getElementAccess().getGuidAction_6_0()); 
            // InternalYAMLParser.g:1884:2: ()
            // InternalYAMLParser.g:1884:3: 
            {
            }

             after(grammarAccess.getElementAccess().getGuidAction_6_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_6__0__Impl"


    // $ANTLR start "rule__Element__Group_6__1"
    // InternalYAMLParser.g:1892:1: rule__Element__Group_6__1 : rule__Element__Group_6__1__Impl ;
    public final void rule__Element__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1896:1: ( rule__Element__Group_6__1__Impl )
            // InternalYAMLParser.g:1897:2: rule__Element__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Element__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_6__1"


    // $ANTLR start "rule__Element__Group_6__1__Impl"
    // InternalYAMLParser.g:1903:1: rule__Element__Group_6__1__Impl : ( ( rule__Element__ValueAssignment_6_1 ) ) ;
    public final void rule__Element__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1907:1: ( ( ( rule__Element__ValueAssignment_6_1 ) ) )
            // InternalYAMLParser.g:1908:1: ( ( rule__Element__ValueAssignment_6_1 ) )
            {
            // InternalYAMLParser.g:1908:1: ( ( rule__Element__ValueAssignment_6_1 ) )
            // InternalYAMLParser.g:1909:2: ( rule__Element__ValueAssignment_6_1 )
            {
             before(grammarAccess.getElementAccess().getValueAssignment_6_1()); 
            // InternalYAMLParser.g:1910:2: ( rule__Element__ValueAssignment_6_1 )
            // InternalYAMLParser.g:1910:3: rule__Element__ValueAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__Element__ValueAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getElementAccess().getValueAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__Group_6__1__Impl"


    // $ANTLR start "rule__InlineList__Group__0"
    // InternalYAMLParser.g:1919:1: rule__InlineList__Group__0 : rule__InlineList__Group__0__Impl rule__InlineList__Group__1 ;
    public final void rule__InlineList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1923:1: ( rule__InlineList__Group__0__Impl rule__InlineList__Group__1 )
            // InternalYAMLParser.g:1924:2: rule__InlineList__Group__0__Impl rule__InlineList__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__InlineList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__0"


    // $ANTLR start "rule__InlineList__Group__0__Impl"
    // InternalYAMLParser.g:1931:1: rule__InlineList__Group__0__Impl : ( () ) ;
    public final void rule__InlineList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1935:1: ( ( () ) )
            // InternalYAMLParser.g:1936:1: ( () )
            {
            // InternalYAMLParser.g:1936:1: ( () )
            // InternalYAMLParser.g:1937:2: ()
            {
             before(grammarAccess.getInlineListAccess().getInlineListAction_0()); 
            // InternalYAMLParser.g:1938:2: ()
            // InternalYAMLParser.g:1938:3: 
            {
            }

             after(grammarAccess.getInlineListAccess().getInlineListAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__0__Impl"


    // $ANTLR start "rule__InlineList__Group__1"
    // InternalYAMLParser.g:1946:1: rule__InlineList__Group__1 : rule__InlineList__Group__1__Impl rule__InlineList__Group__2 ;
    public final void rule__InlineList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1950:1: ( rule__InlineList__Group__1__Impl rule__InlineList__Group__2 )
            // InternalYAMLParser.g:1951:2: rule__InlineList__Group__1__Impl rule__InlineList__Group__2
            {
            pushFollow(FOLLOW_22);
            rule__InlineList__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__1"


    // $ANTLR start "rule__InlineList__Group__1__Impl"
    // InternalYAMLParser.g:1958:1: rule__InlineList__Group__1__Impl : ( LeftSquareBracket ) ;
    public final void rule__InlineList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1962:1: ( ( LeftSquareBracket ) )
            // InternalYAMLParser.g:1963:1: ( LeftSquareBracket )
            {
            // InternalYAMLParser.g:1963:1: ( LeftSquareBracket )
            // InternalYAMLParser.g:1964:2: LeftSquareBracket
            {
             before(grammarAccess.getInlineListAccess().getLeftSquareBracketKeyword_1()); 
            match(input,LeftSquareBracket,FOLLOW_2); 
             after(grammarAccess.getInlineListAccess().getLeftSquareBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__1__Impl"


    // $ANTLR start "rule__InlineList__Group__2"
    // InternalYAMLParser.g:1973:1: rule__InlineList__Group__2 : rule__InlineList__Group__2__Impl rule__InlineList__Group__3 ;
    public final void rule__InlineList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1977:1: ( rule__InlineList__Group__2__Impl rule__InlineList__Group__3 )
            // InternalYAMLParser.g:1978:2: rule__InlineList__Group__2__Impl rule__InlineList__Group__3
            {
            pushFollow(FOLLOW_22);
            rule__InlineList__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__2"


    // $ANTLR start "rule__InlineList__Group__2__Impl"
    // InternalYAMLParser.g:1985:1: rule__InlineList__Group__2__Impl : ( ( rule__InlineList__Group_2__0 )? ) ;
    public final void rule__InlineList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:1989:1: ( ( ( rule__InlineList__Group_2__0 )? ) )
            // InternalYAMLParser.g:1990:1: ( ( rule__InlineList__Group_2__0 )? )
            {
            // InternalYAMLParser.g:1990:1: ( ( rule__InlineList__Group_2__0 )? )
            // InternalYAMLParser.g:1991:2: ( rule__InlineList__Group_2__0 )?
            {
             before(grammarAccess.getInlineListAccess().getGroup_2()); 
            // InternalYAMLParser.g:1992:2: ( rule__InlineList__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LeftSquareBracket||LA13_0==LeftCurlyBracket||(LA13_0>=RULE_WORD && LA13_0<=RULE_GUID)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalYAMLParser.g:1992:3: rule__InlineList__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InlineList__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInlineListAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__2__Impl"


    // $ANTLR start "rule__InlineList__Group__3"
    // InternalYAMLParser.g:2000:1: rule__InlineList__Group__3 : rule__InlineList__Group__3__Impl ;
    public final void rule__InlineList__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2004:1: ( rule__InlineList__Group__3__Impl )
            // InternalYAMLParser.g:2005:2: rule__InlineList__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__3"


    // $ANTLR start "rule__InlineList__Group__3__Impl"
    // InternalYAMLParser.g:2011:1: rule__InlineList__Group__3__Impl : ( RightSquareBracket ) ;
    public final void rule__InlineList__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2015:1: ( ( RightSquareBracket ) )
            // InternalYAMLParser.g:2016:1: ( RightSquareBracket )
            {
            // InternalYAMLParser.g:2016:1: ( RightSquareBracket )
            // InternalYAMLParser.g:2017:2: RightSquareBracket
            {
             before(grammarAccess.getInlineListAccess().getRightSquareBracketKeyword_3()); 
            match(input,RightSquareBracket,FOLLOW_2); 
             after(grammarAccess.getInlineListAccess().getRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group__3__Impl"


    // $ANTLR start "rule__InlineList__Group_2__0"
    // InternalYAMLParser.g:2027:1: rule__InlineList__Group_2__0 : rule__InlineList__Group_2__0__Impl rule__InlineList__Group_2__1 ;
    public final void rule__InlineList__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2031:1: ( rule__InlineList__Group_2__0__Impl rule__InlineList__Group_2__1 )
            // InternalYAMLParser.g:2032:2: rule__InlineList__Group_2__0__Impl rule__InlineList__Group_2__1
            {
            pushFollow(FOLLOW_23);
            rule__InlineList__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2__0"


    // $ANTLR start "rule__InlineList__Group_2__0__Impl"
    // InternalYAMLParser.g:2039:1: rule__InlineList__Group_2__0__Impl : ( ( rule__InlineList__ElementsAssignment_2_0 ) ) ;
    public final void rule__InlineList__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2043:1: ( ( ( rule__InlineList__ElementsAssignment_2_0 ) ) )
            // InternalYAMLParser.g:2044:1: ( ( rule__InlineList__ElementsAssignment_2_0 ) )
            {
            // InternalYAMLParser.g:2044:1: ( ( rule__InlineList__ElementsAssignment_2_0 ) )
            // InternalYAMLParser.g:2045:2: ( rule__InlineList__ElementsAssignment_2_0 )
            {
             before(grammarAccess.getInlineListAccess().getElementsAssignment_2_0()); 
            // InternalYAMLParser.g:2046:2: ( rule__InlineList__ElementsAssignment_2_0 )
            // InternalYAMLParser.g:2046:3: rule__InlineList__ElementsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__ElementsAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getInlineListAccess().getElementsAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2__0__Impl"


    // $ANTLR start "rule__InlineList__Group_2__1"
    // InternalYAMLParser.g:2054:1: rule__InlineList__Group_2__1 : rule__InlineList__Group_2__1__Impl ;
    public final void rule__InlineList__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2058:1: ( rule__InlineList__Group_2__1__Impl )
            // InternalYAMLParser.g:2059:2: rule__InlineList__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2__1"


    // $ANTLR start "rule__InlineList__Group_2__1__Impl"
    // InternalYAMLParser.g:2065:1: rule__InlineList__Group_2__1__Impl : ( ( rule__InlineList__Group_2_1__0 )* ) ;
    public final void rule__InlineList__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2069:1: ( ( ( rule__InlineList__Group_2_1__0 )* ) )
            // InternalYAMLParser.g:2070:1: ( ( rule__InlineList__Group_2_1__0 )* )
            {
            // InternalYAMLParser.g:2070:1: ( ( rule__InlineList__Group_2_1__0 )* )
            // InternalYAMLParser.g:2071:2: ( rule__InlineList__Group_2_1__0 )*
            {
             before(grammarAccess.getInlineListAccess().getGroup_2_1()); 
            // InternalYAMLParser.g:2072:2: ( rule__InlineList__Group_2_1__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Comma) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalYAMLParser.g:2072:3: rule__InlineList__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__InlineList__Group_2_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getInlineListAccess().getGroup_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2__1__Impl"


    // $ANTLR start "rule__InlineList__Group_2_1__0"
    // InternalYAMLParser.g:2081:1: rule__InlineList__Group_2_1__0 : rule__InlineList__Group_2_1__0__Impl rule__InlineList__Group_2_1__1 ;
    public final void rule__InlineList__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2085:1: ( rule__InlineList__Group_2_1__0__Impl rule__InlineList__Group_2_1__1 )
            // InternalYAMLParser.g:2086:2: rule__InlineList__Group_2_1__0__Impl rule__InlineList__Group_2_1__1
            {
            pushFollow(FOLLOW_8);
            rule__InlineList__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group_2_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__0"


    // $ANTLR start "rule__InlineList__Group_2_1__0__Impl"
    // InternalYAMLParser.g:2093:1: rule__InlineList__Group_2_1__0__Impl : ( Comma ) ;
    public final void rule__InlineList__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2097:1: ( ( Comma ) )
            // InternalYAMLParser.g:2098:1: ( Comma )
            {
            // InternalYAMLParser.g:2098:1: ( Comma )
            // InternalYAMLParser.g:2099:2: Comma
            {
             before(grammarAccess.getInlineListAccess().getCommaKeyword_2_1_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getInlineListAccess().getCommaKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__0__Impl"


    // $ANTLR start "rule__InlineList__Group_2_1__1"
    // InternalYAMLParser.g:2108:1: rule__InlineList__Group_2_1__1 : rule__InlineList__Group_2_1__1__Impl rule__InlineList__Group_2_1__2 ;
    public final void rule__InlineList__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2112:1: ( rule__InlineList__Group_2_1__1__Impl rule__InlineList__Group_2_1__2 )
            // InternalYAMLParser.g:2113:2: rule__InlineList__Group_2_1__1__Impl rule__InlineList__Group_2_1__2
            {
            pushFollow(FOLLOW_14);
            rule__InlineList__Group_2_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineList__Group_2_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__1"


    // $ANTLR start "rule__InlineList__Group_2_1__1__Impl"
    // InternalYAMLParser.g:2120:1: rule__InlineList__Group_2_1__1__Impl : ( RULE_SPACE ) ;
    public final void rule__InlineList__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2124:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:2125:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:2125:1: ( RULE_SPACE )
            // InternalYAMLParser.g:2126:2: RULE_SPACE
            {
             before(grammarAccess.getInlineListAccess().getSPACETerminalRuleCall_2_1_1()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getInlineListAccess().getSPACETerminalRuleCall_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__1__Impl"


    // $ANTLR start "rule__InlineList__Group_2_1__2"
    // InternalYAMLParser.g:2135:1: rule__InlineList__Group_2_1__2 : rule__InlineList__Group_2_1__2__Impl ;
    public final void rule__InlineList__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2139:1: ( rule__InlineList__Group_2_1__2__Impl )
            // InternalYAMLParser.g:2140:2: rule__InlineList__Group_2_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__Group_2_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__2"


    // $ANTLR start "rule__InlineList__Group_2_1__2__Impl"
    // InternalYAMLParser.g:2146:1: rule__InlineList__Group_2_1__2__Impl : ( ( rule__InlineList__ElementsAssignment_2_1_2 ) ) ;
    public final void rule__InlineList__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2150:1: ( ( ( rule__InlineList__ElementsAssignment_2_1_2 ) ) )
            // InternalYAMLParser.g:2151:1: ( ( rule__InlineList__ElementsAssignment_2_1_2 ) )
            {
            // InternalYAMLParser.g:2151:1: ( ( rule__InlineList__ElementsAssignment_2_1_2 ) )
            // InternalYAMLParser.g:2152:2: ( rule__InlineList__ElementsAssignment_2_1_2 )
            {
             before(grammarAccess.getInlineListAccess().getElementsAssignment_2_1_2()); 
            // InternalYAMLParser.g:2153:2: ( rule__InlineList__ElementsAssignment_2_1_2 )
            // InternalYAMLParser.g:2153:3: rule__InlineList__ElementsAssignment_2_1_2
            {
            pushFollow(FOLLOW_2);
            rule__InlineList__ElementsAssignment_2_1_2();

            state._fsp--;


            }

             after(grammarAccess.getInlineListAccess().getElementsAssignment_2_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__Group_2_1__2__Impl"


    // $ANTLR start "rule__InlineMap__Group__0"
    // InternalYAMLParser.g:2162:1: rule__InlineMap__Group__0 : rule__InlineMap__Group__0__Impl rule__InlineMap__Group__1 ;
    public final void rule__InlineMap__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2166:1: ( rule__InlineMap__Group__0__Impl rule__InlineMap__Group__1 )
            // InternalYAMLParser.g:2167:2: rule__InlineMap__Group__0__Impl rule__InlineMap__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__InlineMap__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__0"


    // $ANTLR start "rule__InlineMap__Group__0__Impl"
    // InternalYAMLParser.g:2174:1: rule__InlineMap__Group__0__Impl : ( () ) ;
    public final void rule__InlineMap__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2178:1: ( ( () ) )
            // InternalYAMLParser.g:2179:1: ( () )
            {
            // InternalYAMLParser.g:2179:1: ( () )
            // InternalYAMLParser.g:2180:2: ()
            {
             before(grammarAccess.getInlineMapAccess().getInlineMapAction_0()); 
            // InternalYAMLParser.g:2181:2: ()
            // InternalYAMLParser.g:2181:3: 
            {
            }

             after(grammarAccess.getInlineMapAccess().getInlineMapAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__0__Impl"


    // $ANTLR start "rule__InlineMap__Group__1"
    // InternalYAMLParser.g:2189:1: rule__InlineMap__Group__1 : rule__InlineMap__Group__1__Impl rule__InlineMap__Group__2 ;
    public final void rule__InlineMap__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2193:1: ( rule__InlineMap__Group__1__Impl rule__InlineMap__Group__2 )
            // InternalYAMLParser.g:2194:2: rule__InlineMap__Group__1__Impl rule__InlineMap__Group__2
            {
            pushFollow(FOLLOW_26);
            rule__InlineMap__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__1"


    // $ANTLR start "rule__InlineMap__Group__1__Impl"
    // InternalYAMLParser.g:2201:1: rule__InlineMap__Group__1__Impl : ( LeftCurlyBracket ) ;
    public final void rule__InlineMap__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2205:1: ( ( LeftCurlyBracket ) )
            // InternalYAMLParser.g:2206:1: ( LeftCurlyBracket )
            {
            // InternalYAMLParser.g:2206:1: ( LeftCurlyBracket )
            // InternalYAMLParser.g:2207:2: LeftCurlyBracket
            {
             before(grammarAccess.getInlineMapAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,LeftCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getInlineMapAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__1__Impl"


    // $ANTLR start "rule__InlineMap__Group__2"
    // InternalYAMLParser.g:2216:1: rule__InlineMap__Group__2 : rule__InlineMap__Group__2__Impl rule__InlineMap__Group__3 ;
    public final void rule__InlineMap__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2220:1: ( rule__InlineMap__Group__2__Impl rule__InlineMap__Group__3 )
            // InternalYAMLParser.g:2221:2: rule__InlineMap__Group__2__Impl rule__InlineMap__Group__3
            {
            pushFollow(FOLLOW_26);
            rule__InlineMap__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__2"


    // $ANTLR start "rule__InlineMap__Group__2__Impl"
    // InternalYAMLParser.g:2228:1: rule__InlineMap__Group__2__Impl : ( ( rule__InlineMap__Group_2__0 )? ) ;
    public final void rule__InlineMap__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2232:1: ( ( ( rule__InlineMap__Group_2__0 )? ) )
            // InternalYAMLParser.g:2233:1: ( ( rule__InlineMap__Group_2__0 )? )
            {
            // InternalYAMLParser.g:2233:1: ( ( rule__InlineMap__Group_2__0 )? )
            // InternalYAMLParser.g:2234:2: ( rule__InlineMap__Group_2__0 )?
            {
             before(grammarAccess.getInlineMapAccess().getGroup_2()); 
            // InternalYAMLParser.g:2235:2: ( rule__InlineMap__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_WORD||LA15_0==RULE_SPACE) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalYAMLParser.g:2235:3: rule__InlineMap__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__InlineMap__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInlineMapAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__2__Impl"


    // $ANTLR start "rule__InlineMap__Group__3"
    // InternalYAMLParser.g:2243:1: rule__InlineMap__Group__3 : rule__InlineMap__Group__3__Impl ;
    public final void rule__InlineMap__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2247:1: ( rule__InlineMap__Group__3__Impl )
            // InternalYAMLParser.g:2248:2: rule__InlineMap__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__3"


    // $ANTLR start "rule__InlineMap__Group__3__Impl"
    // InternalYAMLParser.g:2254:1: rule__InlineMap__Group__3__Impl : ( RightCurlyBracket ) ;
    public final void rule__InlineMap__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2258:1: ( ( RightCurlyBracket ) )
            // InternalYAMLParser.g:2259:1: ( RightCurlyBracket )
            {
            // InternalYAMLParser.g:2259:1: ( RightCurlyBracket )
            // InternalYAMLParser.g:2260:2: RightCurlyBracket
            {
             before(grammarAccess.getInlineMapAccess().getRightCurlyBracketKeyword_3()); 
            match(input,RightCurlyBracket,FOLLOW_2); 
             after(grammarAccess.getInlineMapAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group__3__Impl"


    // $ANTLR start "rule__InlineMap__Group_2__0"
    // InternalYAMLParser.g:2270:1: rule__InlineMap__Group_2__0 : rule__InlineMap__Group_2__0__Impl rule__InlineMap__Group_2__1 ;
    public final void rule__InlineMap__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2274:1: ( rule__InlineMap__Group_2__0__Impl rule__InlineMap__Group_2__1 )
            // InternalYAMLParser.g:2275:2: rule__InlineMap__Group_2__0__Impl rule__InlineMap__Group_2__1
            {
            pushFollow(FOLLOW_27);
            rule__InlineMap__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__0"


    // $ANTLR start "rule__InlineMap__Group_2__0__Impl"
    // InternalYAMLParser.g:2282:1: rule__InlineMap__Group_2__0__Impl : ( ( RULE_SPACE )? ) ;
    public final void rule__InlineMap__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2286:1: ( ( ( RULE_SPACE )? ) )
            // InternalYAMLParser.g:2287:1: ( ( RULE_SPACE )? )
            {
            // InternalYAMLParser.g:2287:1: ( ( RULE_SPACE )? )
            // InternalYAMLParser.g:2288:2: ( RULE_SPACE )?
            {
             before(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_0()); 
            // InternalYAMLParser.g:2289:2: ( RULE_SPACE )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_SPACE) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalYAMLParser.g:2289:3: RULE_SPACE
                    {
                    match(input,RULE_SPACE,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__0__Impl"


    // $ANTLR start "rule__InlineMap__Group_2__1"
    // InternalYAMLParser.g:2297:1: rule__InlineMap__Group_2__1 : rule__InlineMap__Group_2__1__Impl rule__InlineMap__Group_2__2 ;
    public final void rule__InlineMap__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2301:1: ( rule__InlineMap__Group_2__1__Impl rule__InlineMap__Group_2__2 )
            // InternalYAMLParser.g:2302:2: rule__InlineMap__Group_2__1__Impl rule__InlineMap__Group_2__2
            {
            pushFollow(FOLLOW_28);
            rule__InlineMap__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__1"


    // $ANTLR start "rule__InlineMap__Group_2__1__Impl"
    // InternalYAMLParser.g:2309:1: rule__InlineMap__Group_2__1__Impl : ( ( rule__InlineMap__ValuesAssignment_2_1 ) ) ;
    public final void rule__InlineMap__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2313:1: ( ( ( rule__InlineMap__ValuesAssignment_2_1 ) ) )
            // InternalYAMLParser.g:2314:1: ( ( rule__InlineMap__ValuesAssignment_2_1 ) )
            {
            // InternalYAMLParser.g:2314:1: ( ( rule__InlineMap__ValuesAssignment_2_1 ) )
            // InternalYAMLParser.g:2315:2: ( rule__InlineMap__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getInlineMapAccess().getValuesAssignment_2_1()); 
            // InternalYAMLParser.g:2316:2: ( rule__InlineMap__ValuesAssignment_2_1 )
            // InternalYAMLParser.g:2316:3: rule__InlineMap__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getInlineMapAccess().getValuesAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__1__Impl"


    // $ANTLR start "rule__InlineMap__Group_2__2"
    // InternalYAMLParser.g:2324:1: rule__InlineMap__Group_2__2 : rule__InlineMap__Group_2__2__Impl rule__InlineMap__Group_2__3 ;
    public final void rule__InlineMap__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2328:1: ( rule__InlineMap__Group_2__2__Impl rule__InlineMap__Group_2__3 )
            // InternalYAMLParser.g:2329:2: rule__InlineMap__Group_2__2__Impl rule__InlineMap__Group_2__3
            {
            pushFollow(FOLLOW_28);
            rule__InlineMap__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__2"


    // $ANTLR start "rule__InlineMap__Group_2__2__Impl"
    // InternalYAMLParser.g:2336:1: rule__InlineMap__Group_2__2__Impl : ( ( rule__InlineMap__Group_2_2__0 )* ) ;
    public final void rule__InlineMap__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2340:1: ( ( ( rule__InlineMap__Group_2_2__0 )* ) )
            // InternalYAMLParser.g:2341:1: ( ( rule__InlineMap__Group_2_2__0 )* )
            {
            // InternalYAMLParser.g:2341:1: ( ( rule__InlineMap__Group_2_2__0 )* )
            // InternalYAMLParser.g:2342:2: ( rule__InlineMap__Group_2_2__0 )*
            {
             before(grammarAccess.getInlineMapAccess().getGroup_2_2()); 
            // InternalYAMLParser.g:2343:2: ( rule__InlineMap__Group_2_2__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==Comma) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalYAMLParser.g:2343:3: rule__InlineMap__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__InlineMap__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getInlineMapAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__2__Impl"


    // $ANTLR start "rule__InlineMap__Group_2__3"
    // InternalYAMLParser.g:2351:1: rule__InlineMap__Group_2__3 : rule__InlineMap__Group_2__3__Impl ;
    public final void rule__InlineMap__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2355:1: ( rule__InlineMap__Group_2__3__Impl )
            // InternalYAMLParser.g:2356:2: rule__InlineMap__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__3"


    // $ANTLR start "rule__InlineMap__Group_2__3__Impl"
    // InternalYAMLParser.g:2362:1: rule__InlineMap__Group_2__3__Impl : ( ( RULE_SPACE )? ) ;
    public final void rule__InlineMap__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2366:1: ( ( ( RULE_SPACE )? ) )
            // InternalYAMLParser.g:2367:1: ( ( RULE_SPACE )? )
            {
            // InternalYAMLParser.g:2367:1: ( ( RULE_SPACE )? )
            // InternalYAMLParser.g:2368:2: ( RULE_SPACE )?
            {
             before(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_3()); 
            // InternalYAMLParser.g:2369:2: ( RULE_SPACE )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_SPACE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalYAMLParser.g:2369:3: RULE_SPACE
                    {
                    match(input,RULE_SPACE,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2__3__Impl"


    // $ANTLR start "rule__InlineMap__Group_2_2__0"
    // InternalYAMLParser.g:2378:1: rule__InlineMap__Group_2_2__0 : rule__InlineMap__Group_2_2__0__Impl rule__InlineMap__Group_2_2__1 ;
    public final void rule__InlineMap__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2382:1: ( rule__InlineMap__Group_2_2__0__Impl rule__InlineMap__Group_2_2__1 )
            // InternalYAMLParser.g:2383:2: rule__InlineMap__Group_2_2__0__Impl rule__InlineMap__Group_2_2__1
            {
            pushFollow(FOLLOW_8);
            rule__InlineMap__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__0"


    // $ANTLR start "rule__InlineMap__Group_2_2__0__Impl"
    // InternalYAMLParser.g:2390:1: rule__InlineMap__Group_2_2__0__Impl : ( Comma ) ;
    public final void rule__InlineMap__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2394:1: ( ( Comma ) )
            // InternalYAMLParser.g:2395:1: ( Comma )
            {
            // InternalYAMLParser.g:2395:1: ( Comma )
            // InternalYAMLParser.g:2396:2: Comma
            {
             before(grammarAccess.getInlineMapAccess().getCommaKeyword_2_2_0()); 
            match(input,Comma,FOLLOW_2); 
             after(grammarAccess.getInlineMapAccess().getCommaKeyword_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__0__Impl"


    // $ANTLR start "rule__InlineMap__Group_2_2__1"
    // InternalYAMLParser.g:2405:1: rule__InlineMap__Group_2_2__1 : rule__InlineMap__Group_2_2__1__Impl rule__InlineMap__Group_2_2__2 ;
    public final void rule__InlineMap__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2409:1: ( rule__InlineMap__Group_2_2__1__Impl rule__InlineMap__Group_2_2__2 )
            // InternalYAMLParser.g:2410:2: rule__InlineMap__Group_2_2__1__Impl rule__InlineMap__Group_2_2__2
            {
            pushFollow(FOLLOW_12);
            rule__InlineMap__Group_2_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__1"


    // $ANTLR start "rule__InlineMap__Group_2_2__1__Impl"
    // InternalYAMLParser.g:2417:1: rule__InlineMap__Group_2_2__1__Impl : ( RULE_SPACE ) ;
    public final void rule__InlineMap__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2421:1: ( ( RULE_SPACE ) )
            // InternalYAMLParser.g:2422:1: ( RULE_SPACE )
            {
            // InternalYAMLParser.g:2422:1: ( RULE_SPACE )
            // InternalYAMLParser.g:2423:2: RULE_SPACE
            {
             before(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_2_1()); 
            match(input,RULE_SPACE,FOLLOW_2); 
             after(grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__1__Impl"


    // $ANTLR start "rule__InlineMap__Group_2_2__2"
    // InternalYAMLParser.g:2432:1: rule__InlineMap__Group_2_2__2 : rule__InlineMap__Group_2_2__2__Impl ;
    public final void rule__InlineMap__Group_2_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2436:1: ( rule__InlineMap__Group_2_2__2__Impl )
            // InternalYAMLParser.g:2437:2: rule__InlineMap__Group_2_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__Group_2_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__2"


    // $ANTLR start "rule__InlineMap__Group_2_2__2__Impl"
    // InternalYAMLParser.g:2443:1: rule__InlineMap__Group_2_2__2__Impl : ( ( rule__InlineMap__ValuesAssignment_2_2_2 ) ) ;
    public final void rule__InlineMap__Group_2_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2447:1: ( ( ( rule__InlineMap__ValuesAssignment_2_2_2 ) ) )
            // InternalYAMLParser.g:2448:1: ( ( rule__InlineMap__ValuesAssignment_2_2_2 ) )
            {
            // InternalYAMLParser.g:2448:1: ( ( rule__InlineMap__ValuesAssignment_2_2_2 ) )
            // InternalYAMLParser.g:2449:2: ( rule__InlineMap__ValuesAssignment_2_2_2 )
            {
             before(grammarAccess.getInlineMapAccess().getValuesAssignment_2_2_2()); 
            // InternalYAMLParser.g:2450:2: ( rule__InlineMap__ValuesAssignment_2_2_2 )
            // InternalYAMLParser.g:2450:3: rule__InlineMap__ValuesAssignment_2_2_2
            {
            pushFollow(FOLLOW_2);
            rule__InlineMap__ValuesAssignment_2_2_2();

            state._fsp--;


            }

             after(grammarAccess.getInlineMapAccess().getValuesAssignment_2_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__Group_2_2__2__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group__0"
    // InternalYAMLParser.g:2459:1: rule__InlineSemicolonList__Group__0 : rule__InlineSemicolonList__Group__0__Impl rule__InlineSemicolonList__Group__1 ;
    public final void rule__InlineSemicolonList__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2463:1: ( rule__InlineSemicolonList__Group__0__Impl rule__InlineSemicolonList__Group__1 )
            // InternalYAMLParser.g:2464:2: rule__InlineSemicolonList__Group__0__Impl rule__InlineSemicolonList__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__InlineSemicolonList__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__0"


    // $ANTLR start "rule__InlineSemicolonList__Group__0__Impl"
    // InternalYAMLParser.g:2471:1: rule__InlineSemicolonList__Group__0__Impl : ( () ) ;
    public final void rule__InlineSemicolonList__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2475:1: ( ( () ) )
            // InternalYAMLParser.g:2476:1: ( () )
            {
            // InternalYAMLParser.g:2476:1: ( () )
            // InternalYAMLParser.g:2477:2: ()
            {
             before(grammarAccess.getInlineSemicolonListAccess().getInlineSemicolonListAction_0()); 
            // InternalYAMLParser.g:2478:2: ()
            // InternalYAMLParser.g:2478:3: 
            {
            }

             after(grammarAccess.getInlineSemicolonListAccess().getInlineSemicolonListAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__0__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group__1"
    // InternalYAMLParser.g:2486:1: rule__InlineSemicolonList__Group__1 : rule__InlineSemicolonList__Group__1__Impl rule__InlineSemicolonList__Group__2 ;
    public final void rule__InlineSemicolonList__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2490:1: ( rule__InlineSemicolonList__Group__1__Impl rule__InlineSemicolonList__Group__2 )
            // InternalYAMLParser.g:2491:2: rule__InlineSemicolonList__Group__1__Impl rule__InlineSemicolonList__Group__2
            {
            pushFollow(FOLLOW_29);
            rule__InlineSemicolonList__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__1"


    // $ANTLR start "rule__InlineSemicolonList__Group__1__Impl"
    // InternalYAMLParser.g:2498:1: rule__InlineSemicolonList__Group__1__Impl : ( ( rule__InlineSemicolonList__ElementsAssignment_1 ) ) ;
    public final void rule__InlineSemicolonList__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2502:1: ( ( ( rule__InlineSemicolonList__ElementsAssignment_1 ) ) )
            // InternalYAMLParser.g:2503:1: ( ( rule__InlineSemicolonList__ElementsAssignment_1 ) )
            {
            // InternalYAMLParser.g:2503:1: ( ( rule__InlineSemicolonList__ElementsAssignment_1 ) )
            // InternalYAMLParser.g:2504:2: ( rule__InlineSemicolonList__ElementsAssignment_1 )
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_1()); 
            // InternalYAMLParser.g:2505:2: ( rule__InlineSemicolonList__ElementsAssignment_1 )
            // InternalYAMLParser.g:2505:3: rule__InlineSemicolonList__ElementsAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__ElementsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__1__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group__2"
    // InternalYAMLParser.g:2513:1: rule__InlineSemicolonList__Group__2 : rule__InlineSemicolonList__Group__2__Impl rule__InlineSemicolonList__Group__3 ;
    public final void rule__InlineSemicolonList__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2517:1: ( rule__InlineSemicolonList__Group__2__Impl rule__InlineSemicolonList__Group__3 )
            // InternalYAMLParser.g:2518:2: rule__InlineSemicolonList__Group__2__Impl rule__InlineSemicolonList__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__InlineSemicolonList__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__2"


    // $ANTLR start "rule__InlineSemicolonList__Group__2__Impl"
    // InternalYAMLParser.g:2525:1: rule__InlineSemicolonList__Group__2__Impl : ( Semicolon ) ;
    public final void rule__InlineSemicolonList__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2529:1: ( ( Semicolon ) )
            // InternalYAMLParser.g:2530:1: ( Semicolon )
            {
            // InternalYAMLParser.g:2530:1: ( Semicolon )
            // InternalYAMLParser.g:2531:2: Semicolon
            {
             before(grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_2()); 
            match(input,Semicolon,FOLLOW_2); 
             after(grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__2__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group__3"
    // InternalYAMLParser.g:2540:1: rule__InlineSemicolonList__Group__3 : rule__InlineSemicolonList__Group__3__Impl rule__InlineSemicolonList__Group__4 ;
    public final void rule__InlineSemicolonList__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2544:1: ( rule__InlineSemicolonList__Group__3__Impl rule__InlineSemicolonList__Group__4 )
            // InternalYAMLParser.g:2545:2: rule__InlineSemicolonList__Group__3__Impl rule__InlineSemicolonList__Group__4
            {
            pushFollow(FOLLOW_29);
            rule__InlineSemicolonList__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__3"


    // $ANTLR start "rule__InlineSemicolonList__Group__3__Impl"
    // InternalYAMLParser.g:2552:1: rule__InlineSemicolonList__Group__3__Impl : ( ( rule__InlineSemicolonList__ElementsAssignment_3 ) ) ;
    public final void rule__InlineSemicolonList__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2556:1: ( ( ( rule__InlineSemicolonList__ElementsAssignment_3 ) ) )
            // InternalYAMLParser.g:2557:1: ( ( rule__InlineSemicolonList__ElementsAssignment_3 ) )
            {
            // InternalYAMLParser.g:2557:1: ( ( rule__InlineSemicolonList__ElementsAssignment_3 ) )
            // InternalYAMLParser.g:2558:2: ( rule__InlineSemicolonList__ElementsAssignment_3 )
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_3()); 
            // InternalYAMLParser.g:2559:2: ( rule__InlineSemicolonList__ElementsAssignment_3 )
            // InternalYAMLParser.g:2559:3: rule__InlineSemicolonList__ElementsAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__ElementsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__3__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group__4"
    // InternalYAMLParser.g:2567:1: rule__InlineSemicolonList__Group__4 : rule__InlineSemicolonList__Group__4__Impl ;
    public final void rule__InlineSemicolonList__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2571:1: ( rule__InlineSemicolonList__Group__4__Impl )
            // InternalYAMLParser.g:2572:2: rule__InlineSemicolonList__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__4"


    // $ANTLR start "rule__InlineSemicolonList__Group__4__Impl"
    // InternalYAMLParser.g:2578:1: rule__InlineSemicolonList__Group__4__Impl : ( ( rule__InlineSemicolonList__Group_4__0 )* ) ;
    public final void rule__InlineSemicolonList__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2582:1: ( ( ( rule__InlineSemicolonList__Group_4__0 )* ) )
            // InternalYAMLParser.g:2583:1: ( ( rule__InlineSemicolonList__Group_4__0 )* )
            {
            // InternalYAMLParser.g:2583:1: ( ( rule__InlineSemicolonList__Group_4__0 )* )
            // InternalYAMLParser.g:2584:2: ( rule__InlineSemicolonList__Group_4__0 )*
            {
             before(grammarAccess.getInlineSemicolonListAccess().getGroup_4()); 
            // InternalYAMLParser.g:2585:2: ( rule__InlineSemicolonList__Group_4__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Semicolon) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalYAMLParser.g:2585:3: rule__InlineSemicolonList__Group_4__0
            	    {
            	    pushFollow(FOLLOW_30);
            	    rule__InlineSemicolonList__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getInlineSemicolonListAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group__4__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group_4__0"
    // InternalYAMLParser.g:2594:1: rule__InlineSemicolonList__Group_4__0 : rule__InlineSemicolonList__Group_4__0__Impl rule__InlineSemicolonList__Group_4__1 ;
    public final void rule__InlineSemicolonList__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2598:1: ( rule__InlineSemicolonList__Group_4__0__Impl rule__InlineSemicolonList__Group_4__1 )
            // InternalYAMLParser.g:2599:2: rule__InlineSemicolonList__Group_4__0__Impl rule__InlineSemicolonList__Group_4__1
            {
            pushFollow(FOLLOW_12);
            rule__InlineSemicolonList__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group_4__0"


    // $ANTLR start "rule__InlineSemicolonList__Group_4__0__Impl"
    // InternalYAMLParser.g:2606:1: rule__InlineSemicolonList__Group_4__0__Impl : ( Semicolon ) ;
    public final void rule__InlineSemicolonList__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2610:1: ( ( Semicolon ) )
            // InternalYAMLParser.g:2611:1: ( Semicolon )
            {
            // InternalYAMLParser.g:2611:1: ( Semicolon )
            // InternalYAMLParser.g:2612:2: Semicolon
            {
             before(grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_4_0()); 
            match(input,Semicolon,FOLLOW_2); 
             after(grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group_4__0__Impl"


    // $ANTLR start "rule__InlineSemicolonList__Group_4__1"
    // InternalYAMLParser.g:2621:1: rule__InlineSemicolonList__Group_4__1 : rule__InlineSemicolonList__Group_4__1__Impl ;
    public final void rule__InlineSemicolonList__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2625:1: ( rule__InlineSemicolonList__Group_4__1__Impl )
            // InternalYAMLParser.g:2626:2: rule__InlineSemicolonList__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group_4__1"


    // $ANTLR start "rule__InlineSemicolonList__Group_4__1__Impl"
    // InternalYAMLParser.g:2632:1: rule__InlineSemicolonList__Group_4__1__Impl : ( ( rule__InlineSemicolonList__ElementsAssignment_4_1 ) ) ;
    public final void rule__InlineSemicolonList__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2636:1: ( ( ( rule__InlineSemicolonList__ElementsAssignment_4_1 ) ) )
            // InternalYAMLParser.g:2637:1: ( ( rule__InlineSemicolonList__ElementsAssignment_4_1 ) )
            {
            // InternalYAMLParser.g:2637:1: ( ( rule__InlineSemicolonList__ElementsAssignment_4_1 ) )
            // InternalYAMLParser.g:2638:2: ( rule__InlineSemicolonList__ElementsAssignment_4_1 )
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_4_1()); 
            // InternalYAMLParser.g:2639:2: ( rule__InlineSemicolonList__ElementsAssignment_4_1 )
            // InternalYAMLParser.g:2639:3: rule__InlineSemicolonList__ElementsAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__InlineSemicolonList__ElementsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getInlineSemicolonListAccess().getElementsAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__Group_4__1__Impl"


    // $ANTLR start "rule__File__VersionAssignment_1_0"
    // InternalYAMLParser.g:2648:1: rule__File__VersionAssignment_1_0 : ( ruleVersion ) ;
    public final void rule__File__VersionAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2652:1: ( ( ruleVersion ) )
            // InternalYAMLParser.g:2653:2: ( ruleVersion )
            {
            // InternalYAMLParser.g:2653:2: ( ruleVersion )
            // InternalYAMLParser.g:2654:3: ruleVersion
            {
             before(grammarAccess.getFileAccess().getVersionVersionEnumRuleCall_1_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVersion();

            state._fsp--;

             after(grammarAccess.getFileAccess().getVersionVersionEnumRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__VersionAssignment_1_0"


    // $ANTLR start "rule__File__TagsAssignment_1_2"
    // InternalYAMLParser.g:2663:1: rule__File__TagsAssignment_1_2 : ( ruleTag ) ;
    public final void rule__File__TagsAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2667:1: ( ( ruleTag ) )
            // InternalYAMLParser.g:2668:2: ( ruleTag )
            {
            // InternalYAMLParser.g:2668:2: ( ruleTag )
            // InternalYAMLParser.g:2669:3: ruleTag
            {
             before(grammarAccess.getFileAccess().getTagsTagEnumRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleTag();

            state._fsp--;

             after(grammarAccess.getFileAccess().getTagsTagEnumRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__TagsAssignment_1_2"


    // $ANTLR start "rule__File__NodesAssignment_2_0"
    // InternalYAMLParser.g:2678:1: rule__File__NodesAssignment_2_0 : ( ruleNode ) ;
    public final void rule__File__NodesAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2682:1: ( ( ruleNode ) )
            // InternalYAMLParser.g:2683:2: ( ruleNode )
            {
            // InternalYAMLParser.g:2683:2: ( ruleNode )
            // InternalYAMLParser.g:2684:3: ruleNode
            {
             before(grammarAccess.getFileAccess().getNodesNodeParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleNode();

            state._fsp--;

             after(grammarAccess.getFileAccess().getNodesNodeParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__File__NodesAssignment_2_0"


    // $ANTLR start "rule__Document__TagAssignment_4"
    // InternalYAMLParser.g:2693:1: rule__Document__TagAssignment_4 : ( RULE_L ) ;
    public final void rule__Document__TagAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2697:1: ( ( RULE_L ) )
            // InternalYAMLParser.g:2698:2: ( RULE_L )
            {
            // InternalYAMLParser.g:2698:2: ( RULE_L )
            // InternalYAMLParser.g:2699:3: RULE_L
            {
             before(grammarAccess.getDocumentAccess().getTagLTerminalRuleCall_4_0()); 
            match(input,RULE_L,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getTagLTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__TagAssignment_4"


    // $ANTLR start "rule__Document__IdAssignment_7"
    // InternalYAMLParser.g:2708:1: rule__Document__IdAssignment_7 : ( RULE_L ) ;
    public final void rule__Document__IdAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2712:1: ( ( RULE_L ) )
            // InternalYAMLParser.g:2713:2: ( RULE_L )
            {
            // InternalYAMLParser.g:2713:2: ( RULE_L )
            // InternalYAMLParser.g:2714:3: RULE_L
            {
             before(grammarAccess.getDocumentAccess().getIdLTerminalRuleCall_7_0()); 
            match(input,RULE_L,FOLLOW_2); 
             after(grammarAccess.getDocumentAccess().getIdLTerminalRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__IdAssignment_7"


    // $ANTLR start "rule__Document__ValueAssignment_9"
    // InternalYAMLParser.g:2723:1: rule__Document__ValueAssignment_9 : ( ruleMap ) ;
    public final void rule__Document__ValueAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2727:1: ( ( ruleMap ) )
            // InternalYAMLParser.g:2728:2: ( ruleMap )
            {
            // InternalYAMLParser.g:2728:2: ( ruleMap )
            // InternalYAMLParser.g:2729:3: ruleMap
            {
             before(grammarAccess.getDocumentAccess().getValueMapParserRuleCall_9_0()); 
            pushFollow(FOLLOW_2);
            ruleMap();

            state._fsp--;

             after(grammarAccess.getDocumentAccess().getValueMapParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Document__ValueAssignment_9"


    // $ANTLR start "rule__KeyValue__KeyAssignment_0"
    // InternalYAMLParser.g:2738:1: rule__KeyValue__KeyAssignment_0 : ( RULE_WORD ) ;
    public final void rule__KeyValue__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2742:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2743:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2743:2: ( RULE_WORD )
            // InternalYAMLParser.g:2744:3: RULE_WORD
            {
             before(grammarAccess.getKeyValueAccess().getKeyWORDTerminalRuleCall_0_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getKeyValueAccess().getKeyWORDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__KeyAssignment_0"


    // $ANTLR start "rule__KeyValue__ValueAssignment_3"
    // InternalYAMLParser.g:2753:1: rule__KeyValue__ValueAssignment_3 : ( ruleElement ) ;
    public final void rule__KeyValue__ValueAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2757:1: ( ( ruleElement ) )
            // InternalYAMLParser.g:2758:2: ( ruleElement )
            {
            // InternalYAMLParser.g:2758:2: ( ruleElement )
            // InternalYAMLParser.g:2759:3: ruleElement
            {
             before(grammarAccess.getKeyValueAccess().getValueElementParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getKeyValueAccess().getValueElementParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValue__ValueAssignment_3"


    // $ANTLR start "rule__Map__KeyAssignment_0"
    // InternalYAMLParser.g:2768:1: rule__Map__KeyAssignment_0 : ( RULE_WORD ) ;
    public final void rule__Map__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2772:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2773:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2773:2: ( RULE_WORD )
            // InternalYAMLParser.g:2774:3: RULE_WORD
            {
             before(grammarAccess.getMapAccess().getKeyWORDTerminalRuleCall_0_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getMapAccess().getKeyWORDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__KeyAssignment_0"


    // $ANTLR start "rule__Map__BodyAssignment_4"
    // InternalYAMLParser.g:2783:1: rule__Map__BodyAssignment_4 : ( ruleValue ) ;
    public final void rule__Map__BodyAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2787:1: ( ( ruleValue ) )
            // InternalYAMLParser.g:2788:2: ( ruleValue )
            {
            // InternalYAMLParser.g:2788:2: ( ruleValue )
            // InternalYAMLParser.g:2789:3: ruleValue
            {
             before(grammarAccess.getMapAccess().getBodyValueParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getMapAccess().getBodyValueParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__BodyAssignment_4"


    // $ANTLR start "rule__Map__BodyAssignment_5_1"
    // InternalYAMLParser.g:2798:1: rule__Map__BodyAssignment_5_1 : ( ruleValue ) ;
    public final void rule__Map__BodyAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2802:1: ( ( ruleValue ) )
            // InternalYAMLParser.g:2803:2: ( ruleValue )
            {
            // InternalYAMLParser.g:2803:2: ( ruleValue )
            // InternalYAMLParser.g:2804:3: ruleValue
            {
             before(grammarAccess.getMapAccess().getBodyValueParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getMapAccess().getBodyValueParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Map__BodyAssignment_5_1"


    // $ANTLR start "rule__KeyValueList__NameAssignment_0"
    // InternalYAMLParser.g:2813:1: rule__KeyValueList__NameAssignment_0 : ( RULE_WORD ) ;
    public final void rule__KeyValueList__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2817:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2818:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2818:2: ( RULE_WORD )
            // InternalYAMLParser.g:2819:3: RULE_WORD
            {
             before(grammarAccess.getKeyValueListAccess().getNameWORDTerminalRuleCall_0_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getKeyValueListAccess().getNameWORDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__NameAssignment_0"


    // $ANTLR start "rule__KeyValueList__ValuesAssignment_5"
    // InternalYAMLParser.g:2828:1: rule__KeyValueList__ValuesAssignment_5 : ( ( rule__KeyValueList__ValuesAlternatives_5_0 ) ) ;
    public final void rule__KeyValueList__ValuesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2832:1: ( ( ( rule__KeyValueList__ValuesAlternatives_5_0 ) ) )
            // InternalYAMLParser.g:2833:2: ( ( rule__KeyValueList__ValuesAlternatives_5_0 ) )
            {
            // InternalYAMLParser.g:2833:2: ( ( rule__KeyValueList__ValuesAlternatives_5_0 ) )
            // InternalYAMLParser.g:2834:3: ( rule__KeyValueList__ValuesAlternatives_5_0 )
            {
             before(grammarAccess.getKeyValueListAccess().getValuesAlternatives_5_0()); 
            // InternalYAMLParser.g:2835:3: ( rule__KeyValueList__ValuesAlternatives_5_0 )
            // InternalYAMLParser.g:2835:4: rule__KeyValueList__ValuesAlternatives_5_0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__ValuesAlternatives_5_0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getValuesAlternatives_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__ValuesAssignment_5"


    // $ANTLR start "rule__KeyValueList__ValuesAssignment_6_3"
    // InternalYAMLParser.g:2843:1: rule__KeyValueList__ValuesAssignment_6_3 : ( ( rule__KeyValueList__ValuesAlternatives_6_3_0 ) ) ;
    public final void rule__KeyValueList__ValuesAssignment_6_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2847:1: ( ( ( rule__KeyValueList__ValuesAlternatives_6_3_0 ) ) )
            // InternalYAMLParser.g:2848:2: ( ( rule__KeyValueList__ValuesAlternatives_6_3_0 ) )
            {
            // InternalYAMLParser.g:2848:2: ( ( rule__KeyValueList__ValuesAlternatives_6_3_0 ) )
            // InternalYAMLParser.g:2849:3: ( rule__KeyValueList__ValuesAlternatives_6_3_0 )
            {
             before(grammarAccess.getKeyValueListAccess().getValuesAlternatives_6_3_0()); 
            // InternalYAMLParser.g:2850:3: ( rule__KeyValueList__ValuesAlternatives_6_3_0 )
            // InternalYAMLParser.g:2850:4: rule__KeyValueList__ValuesAlternatives_6_3_0
            {
            pushFollow(FOLLOW_2);
            rule__KeyValueList__ValuesAlternatives_6_3_0();

            state._fsp--;


            }

             after(grammarAccess.getKeyValueListAccess().getValuesAlternatives_6_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyValueList__ValuesAssignment_6_3"


    // $ANTLR start "rule__Element__ValueAssignment_3_1"
    // InternalYAMLParser.g:2858:1: rule__Element__ValueAssignment_3_1 : ( RULE_WORD ) ;
    public final void rule__Element__ValueAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2862:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2863:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2863:2: ( RULE_WORD )
            // InternalYAMLParser.g:2864:3: RULE_WORD
            {
             before(grammarAccess.getElementAccess().getValueWORDTerminalRuleCall_3_1_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getValueWORDTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ValueAssignment_3_1"


    // $ANTLR start "rule__Element__ValueAssignment_4_1"
    // InternalYAMLParser.g:2873:1: rule__Element__ValueAssignment_4_1 : ( RULE_FLOAT ) ;
    public final void rule__Element__ValueAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2877:1: ( ( RULE_FLOAT ) )
            // InternalYAMLParser.g:2878:2: ( RULE_FLOAT )
            {
            // InternalYAMLParser.g:2878:2: ( RULE_FLOAT )
            // InternalYAMLParser.g:2879:3: RULE_FLOAT
            {
             before(grammarAccess.getElementAccess().getValueFLOATTerminalRuleCall_4_1_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getValueFLOATTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ValueAssignment_4_1"


    // $ANTLR start "rule__Element__ValueAssignment_5_1"
    // InternalYAMLParser.g:2888:1: rule__Element__ValueAssignment_5_1 : ( RULE_L ) ;
    public final void rule__Element__ValueAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2892:1: ( ( RULE_L ) )
            // InternalYAMLParser.g:2893:2: ( RULE_L )
            {
            // InternalYAMLParser.g:2893:2: ( RULE_L )
            // InternalYAMLParser.g:2894:3: RULE_L
            {
             before(grammarAccess.getElementAccess().getValueLTerminalRuleCall_5_1_0()); 
            match(input,RULE_L,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getValueLTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ValueAssignment_5_1"


    // $ANTLR start "rule__Element__ValueAssignment_6_1"
    // InternalYAMLParser.g:2903:1: rule__Element__ValueAssignment_6_1 : ( RULE_GUID ) ;
    public final void rule__Element__ValueAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2907:1: ( ( RULE_GUID ) )
            // InternalYAMLParser.g:2908:2: ( RULE_GUID )
            {
            // InternalYAMLParser.g:2908:2: ( RULE_GUID )
            // InternalYAMLParser.g:2909:3: RULE_GUID
            {
             before(grammarAccess.getElementAccess().getValueGUIDTerminalRuleCall_6_1_0()); 
            match(input,RULE_GUID,FOLLOW_2); 
             after(grammarAccess.getElementAccess().getValueGUIDTerminalRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Element__ValueAssignment_6_1"


    // $ANTLR start "rule__InlineList__ElementsAssignment_2_0"
    // InternalYAMLParser.g:2918:1: rule__InlineList__ElementsAssignment_2_0 : ( ruleElement ) ;
    public final void rule__InlineList__ElementsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2922:1: ( ( ruleElement ) )
            // InternalYAMLParser.g:2923:2: ( ruleElement )
            {
            // InternalYAMLParser.g:2923:2: ( ruleElement )
            // InternalYAMLParser.g:2924:3: ruleElement
            {
             before(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__ElementsAssignment_2_0"


    // $ANTLR start "rule__InlineList__ElementsAssignment_2_1_2"
    // InternalYAMLParser.g:2933:1: rule__InlineList__ElementsAssignment_2_1_2 : ( ruleElement ) ;
    public final void rule__InlineList__ElementsAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2937:1: ( ( ruleElement ) )
            // InternalYAMLParser.g:2938:2: ( ruleElement )
            {
            // InternalYAMLParser.g:2938:2: ( ruleElement )
            // InternalYAMLParser.g:2939:3: ruleElement
            {
             before(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElement();

            state._fsp--;

             after(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineList__ElementsAssignment_2_1_2"


    // $ANTLR start "rule__InlineMap__ValuesAssignment_2_1"
    // InternalYAMLParser.g:2948:1: rule__InlineMap__ValuesAssignment_2_1 : ( ruleKeyValue ) ;
    public final void rule__InlineMap__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2952:1: ( ( ruleKeyValue ) )
            // InternalYAMLParser.g:2953:2: ( ruleKeyValue )
            {
            // InternalYAMLParser.g:2953:2: ( ruleKeyValue )
            // InternalYAMLParser.g:2954:3: ruleKeyValue
            {
             before(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleKeyValue();

            state._fsp--;

             after(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__ValuesAssignment_2_1"


    // $ANTLR start "rule__InlineMap__ValuesAssignment_2_2_2"
    // InternalYAMLParser.g:2963:1: rule__InlineMap__ValuesAssignment_2_2_2 : ( ruleKeyValue ) ;
    public final void rule__InlineMap__ValuesAssignment_2_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2967:1: ( ( ruleKeyValue ) )
            // InternalYAMLParser.g:2968:2: ( ruleKeyValue )
            {
            // InternalYAMLParser.g:2968:2: ( ruleKeyValue )
            // InternalYAMLParser.g:2969:3: ruleKeyValue
            {
             before(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleKeyValue();

            state._fsp--;

             after(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineMap__ValuesAssignment_2_2_2"


    // $ANTLR start "rule__InlineSemicolonList__ElementsAssignment_1"
    // InternalYAMLParser.g:2978:1: rule__InlineSemicolonList__ElementsAssignment_1 : ( RULE_WORD ) ;
    public final void rule__InlineSemicolonList__ElementsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2982:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2983:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2983:2: ( RULE_WORD )
            // InternalYAMLParser.g:2984:3: RULE_WORD
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_1_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__ElementsAssignment_1"


    // $ANTLR start "rule__InlineSemicolonList__ElementsAssignment_3"
    // InternalYAMLParser.g:2993:1: rule__InlineSemicolonList__ElementsAssignment_3 : ( RULE_WORD ) ;
    public final void rule__InlineSemicolonList__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:2997:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:2998:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:2998:2: ( RULE_WORD )
            // InternalYAMLParser.g:2999:3: RULE_WORD
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_3_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__ElementsAssignment_3"


    // $ANTLR start "rule__InlineSemicolonList__ElementsAssignment_4_1"
    // InternalYAMLParser.g:3008:1: rule__InlineSemicolonList__ElementsAssignment_4_1 : ( RULE_WORD ) ;
    public final void rule__InlineSemicolonList__ElementsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalYAMLParser.g:3012:1: ( ( RULE_WORD ) )
            // InternalYAMLParser.g:3013:2: ( RULE_WORD )
            {
            // InternalYAMLParser.g:3013:2: ( RULE_WORD )
            // InternalYAMLParser.g:3014:3: RULE_WORD
            {
             before(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_4_1_0()); 
            match(input,RULE_WORD,FOLLOW_2); 
             after(grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__InlineSemicolonList__ElementsAssignment_4_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000000200A0L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000020082L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000001EA000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020080L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00000000001EE000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000230000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000220000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000200200L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000001002L});

}