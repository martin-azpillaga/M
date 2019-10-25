package m.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import m.services.JsonGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJsonParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_FLOAT", "RULE_LONG", "RULE_WHITE_SPACE", "RULE_ID", "'{'", "','", "'}'", "':'", "'['", "']'", "'true'", "'false'"
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

        public InternalJsonParser(TokenStream input, JsonGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Object";
       	}

       	@Override
       	protected JsonGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleObject"
    // InternalJson.g:65:1: entryRuleObject returns [EObject current=null] : iv_ruleObject= ruleObject EOF ;
    public final EObject entryRuleObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObject = null;


        try {
            // InternalJson.g:65:47: (iv_ruleObject= ruleObject EOF )
            // InternalJson.g:66:2: iv_ruleObject= ruleObject EOF
            {
             newCompositeNode(grammarAccess.getObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObject=ruleObject();

            state._fsp--;

             current =iv_ruleObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObject"


    // $ANTLR start "ruleObject"
    // InternalJson.g:72:1: ruleObject returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleObject() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_fields_2_0 = null;

        EObject lv_fields_4_0 = null;



        	enterRule();

        try {
            // InternalJson.g:78:2: ( ( () otherlv_1= '{' ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )? otherlv_5= '}' ) )
            // InternalJson.g:79:2: ( () otherlv_1= '{' ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )? otherlv_5= '}' )
            {
            // InternalJson.g:79:2: ( () otherlv_1= '{' ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )? otherlv_5= '}' )
            // InternalJson.g:80:3: () otherlv_1= '{' ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )? otherlv_5= '}'
            {
            // InternalJson.g:80:3: ()
            // InternalJson.g:81:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getObjectAccess().getObjectAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,9,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalJson.g:91:3: ( ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_STRING) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalJson.g:92:4: ( (lv_fields_2_0= ruleField ) ) (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )*
                    {
                    // InternalJson.g:92:4: ( (lv_fields_2_0= ruleField ) )
                    // InternalJson.g:93:5: (lv_fields_2_0= ruleField )
                    {
                    // InternalJson.g:93:5: (lv_fields_2_0= ruleField )
                    // InternalJson.g:94:6: lv_fields_2_0= ruleField
                    {

                    						newCompositeNode(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_4);
                    lv_fields_2_0=ruleField();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getObjectRule());
                    						}
                    						add(
                    							current,
                    							"fields",
                    							lv_fields_2_0,
                    							"m.Json.Field");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJson.g:111:4: (otherlv_3= ',' ( (lv_fields_4_0= ruleField ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==10) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalJson.g:112:5: otherlv_3= ',' ( (lv_fields_4_0= ruleField ) )
                    	    {
                    	    otherlv_3=(Token)match(input,10,FOLLOW_5); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getObjectAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalJson.g:116:5: ( (lv_fields_4_0= ruleField ) )
                    	    // InternalJson.g:117:6: (lv_fields_4_0= ruleField )
                    	    {
                    	    // InternalJson.g:117:6: (lv_fields_4_0= ruleField )
                    	    // InternalJson.g:118:7: lv_fields_4_0= ruleField
                    	    {

                    	    							newCompositeNode(grammarAccess.getObjectAccess().getFieldsFieldParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_4);
                    	    lv_fields_4_0=ruleField();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getObjectRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"fields",
                    	    								lv_fields_4_0,
                    	    								"m.Json.Field");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,11,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObject"


    // $ANTLR start "entryRuleField"
    // InternalJson.g:145:1: entryRuleField returns [EObject current=null] : iv_ruleField= ruleField EOF ;
    public final EObject entryRuleField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleField = null;


        try {
            // InternalJson.g:145:46: (iv_ruleField= ruleField EOF )
            // InternalJson.g:146:2: iv_ruleField= ruleField EOF
            {
             newCompositeNode(grammarAccess.getFieldRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleField=ruleField();

            state._fsp--;

             current =iv_ruleField; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleField"


    // $ANTLR start "ruleField"
    // InternalJson.g:152:1: ruleField returns [EObject current=null] : ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) ) ;
    public final EObject ruleField() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalJson.g:158:2: ( ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) ) )
            // InternalJson.g:159:2: ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) )
            {
            // InternalJson.g:159:2: ( ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) ) )
            // InternalJson.g:160:3: ( (lv_name_0_0= RULE_STRING ) ) otherlv_1= ':' ( (lv_value_2_0= ruleValue ) )
            {
            // InternalJson.g:160:3: ( (lv_name_0_0= RULE_STRING ) )
            // InternalJson.g:161:4: (lv_name_0_0= RULE_STRING )
            {
            // InternalJson.g:161:4: (lv_name_0_0= RULE_STRING )
            // InternalJson.g:162:5: lv_name_0_0= RULE_STRING
            {
            lv_name_0_0=(Token)match(input,RULE_STRING,FOLLOW_6); 

            					newLeafNode(lv_name_0_0, grammarAccess.getFieldAccess().getNameSTRINGTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFieldRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.Json.STRING");
            				

            }


            }

            otherlv_1=(Token)match(input,12,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getFieldAccess().getColonKeyword_1());
            		
            // InternalJson.g:182:3: ( (lv_value_2_0= ruleValue ) )
            // InternalJson.g:183:4: (lv_value_2_0= ruleValue )
            {
            // InternalJson.g:183:4: (lv_value_2_0= ruleValue )
            // InternalJson.g:184:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getFieldAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFieldRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"m.Json.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleField"


    // $ANTLR start "entryRuleValue"
    // InternalJson.g:205:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalJson.g:205:46: (iv_ruleValue= ruleValue EOF )
            // InternalJson.g:206:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalJson.g:212:1: ruleValue returns [EObject current=null] : (this_Object_0= ruleObject | this_Array_1= ruleArray | this_StringValue_2= ruleStringValue | this_FloatValue_3= ruleFloatValue | this_LongValue_4= ruleLongValue | this_BooleanValue_5= ruleBooleanValue ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_Object_0 = null;

        EObject this_Array_1 = null;

        EObject this_StringValue_2 = null;

        EObject this_FloatValue_3 = null;

        EObject this_LongValue_4 = null;

        EObject this_BooleanValue_5 = null;



        	enterRule();

        try {
            // InternalJson.g:218:2: ( (this_Object_0= ruleObject | this_Array_1= ruleArray | this_StringValue_2= ruleStringValue | this_FloatValue_3= ruleFloatValue | this_LongValue_4= ruleLongValue | this_BooleanValue_5= ruleBooleanValue ) )
            // InternalJson.g:219:2: (this_Object_0= ruleObject | this_Array_1= ruleArray | this_StringValue_2= ruleStringValue | this_FloatValue_3= ruleFloatValue | this_LongValue_4= ruleLongValue | this_BooleanValue_5= ruleBooleanValue )
            {
            // InternalJson.g:219:2: (this_Object_0= ruleObject | this_Array_1= ruleArray | this_StringValue_2= ruleStringValue | this_FloatValue_3= ruleFloatValue | this_LongValue_4= ruleLongValue | this_BooleanValue_5= ruleBooleanValue )
            int alt3=6;
            switch ( input.LA(1) ) {
            case 9:
                {
                alt3=1;
                }
                break;
            case 13:
                {
                alt3=2;
                }
                break;
            case RULE_STRING:
                {
                alt3=3;
                }
                break;
            case RULE_FLOAT:
                {
                alt3=4;
                }
                break;
            case RULE_LONG:
                {
                alt3=5;
                }
                break;
            case 15:
            case 16:
                {
                alt3=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalJson.g:220:3: this_Object_0= ruleObject
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getObjectParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Object_0=ruleObject();

                    state._fsp--;


                    			current = this_Object_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJson.g:229:3: this_Array_1= ruleArray
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getArrayParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Array_1=ruleArray();

                    state._fsp--;


                    			current = this_Array_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalJson.g:238:3: this_StringValue_2= ruleStringValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getStringValueParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringValue_2=ruleStringValue();

                    state._fsp--;


                    			current = this_StringValue_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalJson.g:247:3: this_FloatValue_3= ruleFloatValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getFloatValueParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_FloatValue_3=ruleFloatValue();

                    state._fsp--;


                    			current = this_FloatValue_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalJson.g:256:3: this_LongValue_4= ruleLongValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getLongValueParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_LongValue_4=ruleLongValue();

                    state._fsp--;


                    			current = this_LongValue_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalJson.g:265:3: this_BooleanValue_5= ruleBooleanValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getBooleanValueParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_BooleanValue_5=ruleBooleanValue();

                    state._fsp--;


                    			current = this_BooleanValue_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleArray"
    // InternalJson.g:277:1: entryRuleArray returns [EObject current=null] : iv_ruleArray= ruleArray EOF ;
    public final EObject entryRuleArray() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArray = null;


        try {
            // InternalJson.g:277:46: (iv_ruleArray= ruleArray EOF )
            // InternalJson.g:278:2: iv_ruleArray= ruleArray EOF
            {
             newCompositeNode(grammarAccess.getArrayRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArray=ruleArray();

            state._fsp--;

             current =iv_ruleArray; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArray"


    // $ANTLR start "ruleArray"
    // InternalJson.g:284:1: ruleArray returns [EObject current=null] : ( () otherlv_1= '[' ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )? otherlv_5= ']' ) ;
    public final EObject ruleArray() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;



        	enterRule();

        try {
            // InternalJson.g:290:2: ( ( () otherlv_1= '[' ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )? otherlv_5= ']' ) )
            // InternalJson.g:291:2: ( () otherlv_1= '[' ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )? otherlv_5= ']' )
            {
            // InternalJson.g:291:2: ( () otherlv_1= '[' ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )? otherlv_5= ']' )
            // InternalJson.g:292:3: () otherlv_1= '[' ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )? otherlv_5= ']'
            {
            // InternalJson.g:292:3: ()
            // InternalJson.g:293:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getArrayAccess().getArrayAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,13,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getArrayAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalJson.g:303:3: ( ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=RULE_STRING && LA5_0<=RULE_LONG)||LA5_0==9||LA5_0==13||(LA5_0>=15 && LA5_0<=16)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalJson.g:304:4: ( (lv_elements_2_0= ruleValue ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )*
                    {
                    // InternalJson.g:304:4: ( (lv_elements_2_0= ruleValue ) )
                    // InternalJson.g:305:5: (lv_elements_2_0= ruleValue )
                    {
                    // InternalJson.g:305:5: (lv_elements_2_0= ruleValue )
                    // InternalJson.g:306:6: lv_elements_2_0= ruleValue
                    {

                    						newCompositeNode(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_9);
                    lv_elements_2_0=ruleValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getArrayRule());
                    						}
                    						add(
                    							current,
                    							"elements",
                    							lv_elements_2_0,
                    							"m.Json.Value");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJson.g:323:4: (otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==10) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalJson.g:324:5: otherlv_3= ',' ( (lv_elements_4_0= ruleValue ) )
                    	    {
                    	    otherlv_3=(Token)match(input,10,FOLLOW_7); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getArrayAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalJson.g:328:5: ( (lv_elements_4_0= ruleValue ) )
                    	    // InternalJson.g:329:6: (lv_elements_4_0= ruleValue )
                    	    {
                    	    // InternalJson.g:329:6: (lv_elements_4_0= ruleValue )
                    	    // InternalJson.g:330:7: lv_elements_4_0= ruleValue
                    	    {

                    	    							newCompositeNode(grammarAccess.getArrayAccess().getElementsValueParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_9);
                    	    lv_elements_4_0=ruleValue();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getArrayRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"elements",
                    	    								lv_elements_4_0,
                    	    								"m.Json.Value");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getArrayAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArray"


    // $ANTLR start "entryRuleStringValue"
    // InternalJson.g:357:1: entryRuleStringValue returns [EObject current=null] : iv_ruleStringValue= ruleStringValue EOF ;
    public final EObject entryRuleStringValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringValue = null;


        try {
            // InternalJson.g:357:52: (iv_ruleStringValue= ruleStringValue EOF )
            // InternalJson.g:358:2: iv_ruleStringValue= ruleStringValue EOF
            {
             newCompositeNode(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringValue=ruleStringValue();

            state._fsp--;

             current =iv_ruleStringValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalJson.g:364:1: ruleStringValue returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalJson.g:370:2: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // InternalJson.g:371:2: ( (lv_value_0_0= RULE_STRING ) )
            {
            // InternalJson.g:371:2: ( (lv_value_0_0= RULE_STRING ) )
            // InternalJson.g:372:3: (lv_value_0_0= RULE_STRING )
            {
            // InternalJson.g:372:3: (lv_value_0_0= RULE_STRING )
            // InternalJson.g:373:4: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getStringValueRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"m.Json.STRING");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleFloatValue"
    // InternalJson.g:392:1: entryRuleFloatValue returns [EObject current=null] : iv_ruleFloatValue= ruleFloatValue EOF ;
    public final EObject entryRuleFloatValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatValue = null;


        try {
            // InternalJson.g:392:51: (iv_ruleFloatValue= ruleFloatValue EOF )
            // InternalJson.g:393:2: iv_ruleFloatValue= ruleFloatValue EOF
            {
             newCompositeNode(grammarAccess.getFloatValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloatValue=ruleFloatValue();

            state._fsp--;

             current =iv_ruleFloatValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloatValue"


    // $ANTLR start "ruleFloatValue"
    // InternalJson.g:399:1: ruleFloatValue returns [EObject current=null] : ( (lv_value_0_0= RULE_FLOAT ) ) ;
    public final EObject ruleFloatValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalJson.g:405:2: ( ( (lv_value_0_0= RULE_FLOAT ) ) )
            // InternalJson.g:406:2: ( (lv_value_0_0= RULE_FLOAT ) )
            {
            // InternalJson.g:406:2: ( (lv_value_0_0= RULE_FLOAT ) )
            // InternalJson.g:407:3: (lv_value_0_0= RULE_FLOAT )
            {
            // InternalJson.g:407:3: (lv_value_0_0= RULE_FLOAT )
            // InternalJson.g:408:4: lv_value_0_0= RULE_FLOAT
            {
            lv_value_0_0=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getFloatValueRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"m.Json.FLOAT");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloatValue"


    // $ANTLR start "entryRuleLongValue"
    // InternalJson.g:427:1: entryRuleLongValue returns [EObject current=null] : iv_ruleLongValue= ruleLongValue EOF ;
    public final EObject entryRuleLongValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLongValue = null;


        try {
            // InternalJson.g:427:50: (iv_ruleLongValue= ruleLongValue EOF )
            // InternalJson.g:428:2: iv_ruleLongValue= ruleLongValue EOF
            {
             newCompositeNode(grammarAccess.getLongValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLongValue=ruleLongValue();

            state._fsp--;

             current =iv_ruleLongValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLongValue"


    // $ANTLR start "ruleLongValue"
    // InternalJson.g:434:1: ruleLongValue returns [EObject current=null] : ( (lv_value_0_0= RULE_LONG ) ) ;
    public final EObject ruleLongValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalJson.g:440:2: ( ( (lv_value_0_0= RULE_LONG ) ) )
            // InternalJson.g:441:2: ( (lv_value_0_0= RULE_LONG ) )
            {
            // InternalJson.g:441:2: ( (lv_value_0_0= RULE_LONG ) )
            // InternalJson.g:442:3: (lv_value_0_0= RULE_LONG )
            {
            // InternalJson.g:442:3: (lv_value_0_0= RULE_LONG )
            // InternalJson.g:443:4: lv_value_0_0= RULE_LONG
            {
            lv_value_0_0=(Token)match(input,RULE_LONG,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getLongValueAccess().getValueLONGTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getLongValueRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"m.Json.LONG");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLongValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalJson.g:462:1: entryRuleBooleanValue returns [EObject current=null] : iv_ruleBooleanValue= ruleBooleanValue EOF ;
    public final EObject entryRuleBooleanValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanValue = null;


        try {
            // InternalJson.g:462:53: (iv_ruleBooleanValue= ruleBooleanValue EOF )
            // InternalJson.g:463:2: iv_ruleBooleanValue= ruleBooleanValue EOF
            {
             newCompositeNode(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanValue=ruleBooleanValue();

            state._fsp--;

             current =iv_ruleBooleanValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalJson.g:469:1: ruleBooleanValue returns [EObject current=null] : ( (lv_value_0_0= ruleBoolean ) ) ;
    public final EObject ruleBooleanValue() throws RecognitionException {
        EObject current = null;

        Enumerator lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalJson.g:475:2: ( ( (lv_value_0_0= ruleBoolean ) ) )
            // InternalJson.g:476:2: ( (lv_value_0_0= ruleBoolean ) )
            {
            // InternalJson.g:476:2: ( (lv_value_0_0= ruleBoolean ) )
            // InternalJson.g:477:3: (lv_value_0_0= ruleBoolean )
            {
            // InternalJson.g:477:3: (lv_value_0_0= ruleBoolean )
            // InternalJson.g:478:4: lv_value_0_0= ruleBoolean
            {

            				newCompositeNode(grammarAccess.getBooleanValueAccess().getValueBooleanEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleBoolean();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getBooleanValueRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"m.Json.Boolean");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "ruleBoolean"
    // InternalJson.g:498:1: ruleBoolean returns [Enumerator current=null] : ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) ;
    public final Enumerator ruleBoolean() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalJson.g:504:2: ( ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) )
            // InternalJson.g:505:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            {
            // InternalJson.g:505:2: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            else if ( (LA6_0==16) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalJson.g:506:3: (enumLiteral_0= 'true' )
                    {
                    // InternalJson.g:506:3: (enumLiteral_0= 'true' )
                    // InternalJson.g:507:4: enumLiteral_0= 'true'
                    {
                    enumLiteral_0=(Token)match(input,15,FOLLOW_2); 

                    				current = grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getBooleanAccess().getTRUEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalJson.g:514:3: (enumLiteral_1= 'false' )
                    {
                    // InternalJson.g:514:3: (enumLiteral_1= 'false' )
                    // InternalJson.g:515:4: enumLiteral_1= 'false'
                    {
                    enumLiteral_1=(Token)match(input,16,FOLLOW_2); 

                    				current = grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getBooleanAccess().getFALSEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoolean"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000C00L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000001A270L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000000001E270L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000004400L});

}