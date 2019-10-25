package m.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import m.services.BlocksGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBlocksParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_N", "RULE_WHITE_SPACE", "'<'", "'/>'", "'>'", "'</'", "'='"
    };
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int RULE_ID=4;
    public static final int RULE_STRING=5;
    public static final int RULE_N=6;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int EOF=-1;
    public static final int RULE_WHITE_SPACE=7;
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

        public InternalBlocksParser(TokenStream input, BlocksGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "File";
       	}

       	@Override
       	protected BlocksGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleFile"
    // InternalBlocks.g:64:1: entryRuleFile returns [EObject current=null] : iv_ruleFile= ruleFile EOF ;
    public final EObject entryRuleFile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFile = null;


        try {
            // InternalBlocks.g:64:45: (iv_ruleFile= ruleFile EOF )
            // InternalBlocks.g:65:2: iv_ruleFile= ruleFile EOF
            {
             newCompositeNode(grammarAccess.getFileRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFile=ruleFile();

            state._fsp--;

             current =iv_ruleFile; 
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
    // $ANTLR end "entryRuleFile"


    // $ANTLR start "ruleFile"
    // InternalBlocks.g:71:1: ruleFile returns [EObject current=null] : ( (lv_elements_0_0= ruleElement ) ) ;
    public final EObject ruleFile() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;



        	enterRule();

        try {
            // InternalBlocks.g:77:2: ( ( (lv_elements_0_0= ruleElement ) ) )
            // InternalBlocks.g:78:2: ( (lv_elements_0_0= ruleElement ) )
            {
            // InternalBlocks.g:78:2: ( (lv_elements_0_0= ruleElement ) )
            // InternalBlocks.g:79:3: (lv_elements_0_0= ruleElement )
            {
            // InternalBlocks.g:79:3: (lv_elements_0_0= ruleElement )
            // InternalBlocks.g:80:4: lv_elements_0_0= ruleElement
            {

            				newCompositeNode(grammarAccess.getFileAccess().getElementsElementParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_elements_0_0=ruleElement();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getFileRule());
            				}
            				add(
            					current,
            					"elements",
            					lv_elements_0_0,
            					"m.Blocks.Element");
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
    // $ANTLR end "ruleFile"


    // $ANTLR start "entryRuleElement"
    // InternalBlocks.g:100:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // InternalBlocks.g:100:48: (iv_ruleElement= ruleElement EOF )
            // InternalBlocks.g:101:2: iv_ruleElement= ruleElement EOF
            {
             newCompositeNode(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
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
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // InternalBlocks.g:107:1: ruleElement returns [EObject current=null] : (otherlv_0= '<' ( (lv_open_1_0= RULE_ID ) ) ( (lv_attributes_2_0= ruleAttribute ) )* (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) ) ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_open_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token lv_close_8_0=null;
        Token otherlv_9=null;
        EObject lv_attributes_2_0 = null;

        EObject lv_elements_5_0 = null;

        AntlrDatatypeRuleToken lv_text_6_0 = null;



        	enterRule();

        try {
            // InternalBlocks.g:113:2: ( (otherlv_0= '<' ( (lv_open_1_0= RULE_ID ) ) ( (lv_attributes_2_0= ruleAttribute ) )* (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) ) ) )
            // InternalBlocks.g:114:2: (otherlv_0= '<' ( (lv_open_1_0= RULE_ID ) ) ( (lv_attributes_2_0= ruleAttribute ) )* (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) ) )
            {
            // InternalBlocks.g:114:2: (otherlv_0= '<' ( (lv_open_1_0= RULE_ID ) ) ( (lv_attributes_2_0= ruleAttribute ) )* (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) ) )
            // InternalBlocks.g:115:3: otherlv_0= '<' ( (lv_open_1_0= RULE_ID ) ) ( (lv_attributes_2_0= ruleAttribute ) )* (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) )
            {
            otherlv_0=(Token)match(input,8,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getElementAccess().getLessThanSignKeyword_0());
            		
            // InternalBlocks.g:119:3: ( (lv_open_1_0= RULE_ID ) )
            // InternalBlocks.g:120:4: (lv_open_1_0= RULE_ID )
            {
            // InternalBlocks.g:120:4: (lv_open_1_0= RULE_ID )
            // InternalBlocks.g:121:5: lv_open_1_0= RULE_ID
            {
            lv_open_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_open_1_0, grammarAccess.getElementAccess().getOpenIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"open",
            						lv_open_1_0,
            						"m.Blocks.ID");
            				

            }


            }

            // InternalBlocks.g:137:3: ( (lv_attributes_2_0= ruleAttribute ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalBlocks.g:138:4: (lv_attributes_2_0= ruleAttribute )
            	    {
            	    // InternalBlocks.g:138:4: (lv_attributes_2_0= ruleAttribute )
            	    // InternalBlocks.g:139:5: lv_attributes_2_0= ruleAttribute
            	    {

            	    					newCompositeNode(grammarAccess.getElementAccess().getAttributesAttributeParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_attributes_2_0=ruleAttribute();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getElementRule());
            	    					}
            	    					add(
            	    						current,
            	    						"attributes",
            	    						lv_attributes_2_0,
            	    						"m.Blocks.Attribute");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalBlocks.g:156:3: (otherlv_3= '/>' | (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==9) ) {
                alt4=1;
            }
            else if ( (LA4_0==10) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalBlocks.g:157:4: otherlv_3= '/>'
                    {
                    otherlv_3=(Token)match(input,9,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getElementAccess().getSolidusGreaterThanSignKeyword_3_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalBlocks.g:162:4: (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' )
                    {
                    // InternalBlocks.g:162:4: (otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>' )
                    // InternalBlocks.g:163:5: otherlv_4= '>' ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )? otherlv_7= '</' ( (lv_close_8_0= RULE_ID ) ) otherlv_9= '>'
                    {
                    otherlv_4=(Token)match(input,10,FOLLOW_5); 

                    					newLeafNode(otherlv_4, grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_0());
                    				
                    // InternalBlocks.g:167:5: ( ( (lv_elements_5_0= ruleElement ) )+ | ( (lv_text_6_0= ruleText ) ) )?
                    int alt3=3;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==8) ) {
                        alt3=1;
                    }
                    else if ( (LA3_0==RULE_ID||(LA3_0>=RULE_N && LA3_0<=RULE_WHITE_SPACE)) ) {
                        alt3=2;
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalBlocks.g:168:6: ( (lv_elements_5_0= ruleElement ) )+
                            {
                            // InternalBlocks.g:168:6: ( (lv_elements_5_0= ruleElement ) )+
                            int cnt2=0;
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==8) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // InternalBlocks.g:169:7: (lv_elements_5_0= ruleElement )
                            	    {
                            	    // InternalBlocks.g:169:7: (lv_elements_5_0= ruleElement )
                            	    // InternalBlocks.g:170:8: lv_elements_5_0= ruleElement
                            	    {

                            	    								newCompositeNode(grammarAccess.getElementAccess().getElementsElementParserRuleCall_3_1_1_0_0());
                            	    							
                            	    pushFollow(FOLLOW_6);
                            	    lv_elements_5_0=ruleElement();

                            	    state._fsp--;


                            	    								if (current==null) {
                            	    									current = createModelElementForParent(grammarAccess.getElementRule());
                            	    								}
                            	    								add(
                            	    									current,
                            	    									"elements",
                            	    									lv_elements_5_0,
                            	    									"m.Blocks.Element");
                            	    								afterParserOrEnumRuleCall();
                            	    							

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt2 >= 1 ) break loop2;
                                        EarlyExitException eee =
                                            new EarlyExitException(2, input);
                                        throw eee;
                                }
                                cnt2++;
                            } while (true);


                            }
                            break;
                        case 2 :
                            // InternalBlocks.g:188:6: ( (lv_text_6_0= ruleText ) )
                            {
                            // InternalBlocks.g:188:6: ( (lv_text_6_0= ruleText ) )
                            // InternalBlocks.g:189:7: (lv_text_6_0= ruleText )
                            {
                            // InternalBlocks.g:189:7: (lv_text_6_0= ruleText )
                            // InternalBlocks.g:190:8: lv_text_6_0= ruleText
                            {

                            								newCompositeNode(grammarAccess.getElementAccess().getTextTextParserRuleCall_3_1_1_1_0());
                            							
                            pushFollow(FOLLOW_7);
                            lv_text_6_0=ruleText();

                            state._fsp--;


                            								if (current==null) {
                            									current = createModelElementForParent(grammarAccess.getElementRule());
                            								}
                            								set(
                            									current,
                            									"text",
                            									lv_text_6_0,
                            									"m.Blocks.Text");
                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }
                            break;

                    }

                    otherlv_7=(Token)match(input,11,FOLLOW_3); 

                    					newLeafNode(otherlv_7, grammarAccess.getElementAccess().getLessThanSignSolidusKeyword_3_1_2());
                    				
                    // InternalBlocks.g:212:5: ( (lv_close_8_0= RULE_ID ) )
                    // InternalBlocks.g:213:6: (lv_close_8_0= RULE_ID )
                    {
                    // InternalBlocks.g:213:6: (lv_close_8_0= RULE_ID )
                    // InternalBlocks.g:214:7: lv_close_8_0= RULE_ID
                    {
                    lv_close_8_0=(Token)match(input,RULE_ID,FOLLOW_8); 

                    							newLeafNode(lv_close_8_0, grammarAccess.getElementAccess().getCloseIDTerminalRuleCall_3_1_3_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getElementRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"close",
                    								lv_close_8_0,
                    								"m.Blocks.ID");
                    						

                    }


                    }

                    otherlv_9=(Token)match(input,10,FOLLOW_2); 

                    					newLeafNode(otherlv_9, grammarAccess.getElementAccess().getGreaterThanSignKeyword_3_1_4());
                    				

                    }


                    }
                    break;

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
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleAttribute"
    // InternalBlocks.g:240:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalBlocks.g:240:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalBlocks.g:241:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
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
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalBlocks.g:247:1: ruleAttribute returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_value_2_0=null;


        	enterRule();

        try {
            // InternalBlocks.g:253:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) ) )
            // InternalBlocks.g:254:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) )
            {
            // InternalBlocks.g:254:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) ) )
            // InternalBlocks.g:255:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= RULE_STRING ) )
            {
            // InternalBlocks.g:255:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalBlocks.g:256:4: (lv_name_0_0= RULE_ID )
            {
            // InternalBlocks.g:256:4: (lv_name_0_0= RULE_ID )
            // InternalBlocks.g:257:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(lv_name_0_0, grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAttributeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.Blocks.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,12,FOLLOW_10); 

            			newLeafNode(otherlv_1, grammarAccess.getAttributeAccess().getEqualsSignKeyword_1());
            		
            // InternalBlocks.g:277:3: ( (lv_value_2_0= RULE_STRING ) )
            // InternalBlocks.g:278:4: (lv_value_2_0= RULE_STRING )
            {
            // InternalBlocks.g:278:4: (lv_value_2_0= RULE_STRING )
            // InternalBlocks.g:279:5: lv_value_2_0= RULE_STRING
            {
            lv_value_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_value_2_0, grammarAccess.getAttributeAccess().getValueSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAttributeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_2_0,
            						"m.Blocks.STRING");
            				

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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleText"
    // InternalBlocks.g:299:1: entryRuleText returns [String current=null] : iv_ruleText= ruleText EOF ;
    public final String entryRuleText() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleText = null;


        try {
            // InternalBlocks.g:299:44: (iv_ruleText= ruleText EOF )
            // InternalBlocks.g:300:2: iv_ruleText= ruleText EOF
            {
             newCompositeNode(grammarAccess.getTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleText=ruleText();

            state._fsp--;

             current =iv_ruleText.getText(); 
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
    // $ANTLR end "entryRuleText"


    // $ANTLR start "ruleText"
    // InternalBlocks.g:306:1: ruleText returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_N_0= RULE_N | this_WHITE_SPACE_1= RULE_WHITE_SPACE | this_ID_2= RULE_ID )+ ;
    public final AntlrDatatypeRuleToken ruleText() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_N_0=null;
        Token this_WHITE_SPACE_1=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalBlocks.g:312:2: ( (this_N_0= RULE_N | this_WHITE_SPACE_1= RULE_WHITE_SPACE | this_ID_2= RULE_ID )+ )
            // InternalBlocks.g:313:2: (this_N_0= RULE_N | this_WHITE_SPACE_1= RULE_WHITE_SPACE | this_ID_2= RULE_ID )+
            {
            // InternalBlocks.g:313:2: (this_N_0= RULE_N | this_WHITE_SPACE_1= RULE_WHITE_SPACE | this_ID_2= RULE_ID )+
            int cnt5=0;
            loop5:
            do {
                int alt5=4;
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

                }

                switch (alt5) {
            	case 1 :
            	    // InternalBlocks.g:314:3: this_N_0= RULE_N
            	    {
            	    this_N_0=(Token)match(input,RULE_N,FOLLOW_11); 

            	    			current.merge(this_N_0);
            	    		

            	    			newLeafNode(this_N_0, grammarAccess.getTextAccess().getNTerminalRuleCall_0());
            	    		

            	    }
            	    break;
            	case 2 :
            	    // InternalBlocks.g:322:3: this_WHITE_SPACE_1= RULE_WHITE_SPACE
            	    {
            	    this_WHITE_SPACE_1=(Token)match(input,RULE_WHITE_SPACE,FOLLOW_11); 

            	    			current.merge(this_WHITE_SPACE_1);
            	    		

            	    			newLeafNode(this_WHITE_SPACE_1, grammarAccess.getTextAccess().getWHITE_SPACETerminalRuleCall_1());
            	    		

            	    }
            	    break;
            	case 3 :
            	    // InternalBlocks.g:330:3: this_ID_2= RULE_ID
            	    {
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_11); 

            	    			current.merge(this_ID_2);
            	    		

            	    			newLeafNode(this_ID_2, grammarAccess.getTextAccess().getIDTerminalRuleCall_2());
            	    		

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


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
    // $ANTLR end "ruleText"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000610L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000000009D0L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000900L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000000000D2L});

}