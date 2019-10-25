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
import m.services.YAMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalYAMLParser extends AbstractInternalAntlrParser {
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

        public InternalYAMLParser(TokenStream input, YAMLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "File";
       	}

       	@Override
       	protected YAMLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleFile"
    // InternalYAMLParser.g:58:1: entryRuleFile returns [EObject current=null] : iv_ruleFile= ruleFile EOF ;
    public final EObject entryRuleFile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFile = null;


        try {
            // InternalYAMLParser.g:58:45: (iv_ruleFile= ruleFile EOF )
            // InternalYAMLParser.g:59:2: iv_ruleFile= ruleFile EOF
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
    // InternalYAMLParser.g:65:1: ruleFile returns [EObject current=null] : ( () ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )? ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )* ) ;
    public final EObject ruleFile() throws RecognitionException {
        EObject current = null;

        Token this_WS_2=null;
        Token this_WS_4=null;
        Token this_WS_6=null;
        Enumerator lv_version_1_0 = null;

        Enumerator lv_tags_3_0 = null;

        EObject lv_nodes_5_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:71:2: ( ( () ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )? ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )* ) )
            // InternalYAMLParser.g:72:2: ( () ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )? ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )* )
            {
            // InternalYAMLParser.g:72:2: ( () ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )? ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )* )
            // InternalYAMLParser.g:73:3: () ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )? ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )*
            {
            // InternalYAMLParser.g:73:3: ()
            // InternalYAMLParser.g:74:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFileAccess().getFileAction_0(),
            					current);
            			

            }

            // InternalYAMLParser.g:80:3: ( ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==YAML11) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalYAMLParser.g:81:4: ( (lv_version_1_0= ruleVersion ) ) this_WS_2= RULE_WS ( (lv_tags_3_0= ruleTag ) ) this_WS_4= RULE_WS
                    {
                    // InternalYAMLParser.g:81:4: ( (lv_version_1_0= ruleVersion ) )
                    // InternalYAMLParser.g:82:5: (lv_version_1_0= ruleVersion )
                    {
                    // InternalYAMLParser.g:82:5: (lv_version_1_0= ruleVersion )
                    // InternalYAMLParser.g:83:6: lv_version_1_0= ruleVersion
                    {

                    						newCompositeNode(grammarAccess.getFileAccess().getVersionVersionEnumRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_3);
                    lv_version_1_0=ruleVersion();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFileRule());
                    						}
                    						set(
                    							current,
                    							"version",
                    							lv_version_1_0,
                    							"m.YAML.Version");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    this_WS_2=(Token)match(input,RULE_WS,FOLLOW_4); 

                    				newLeafNode(this_WS_2, grammarAccess.getFileAccess().getWSTerminalRuleCall_1_1());
                    			
                    // InternalYAMLParser.g:104:4: ( (lv_tags_3_0= ruleTag ) )
                    // InternalYAMLParser.g:105:5: (lv_tags_3_0= ruleTag )
                    {
                    // InternalYAMLParser.g:105:5: (lv_tags_3_0= ruleTag )
                    // InternalYAMLParser.g:106:6: lv_tags_3_0= ruleTag
                    {

                    						newCompositeNode(grammarAccess.getFileAccess().getTagsTagEnumRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_3);
                    lv_tags_3_0=ruleTag();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFileRule());
                    						}
                    						add(
                    							current,
                    							"tags",
                    							lv_tags_3_0,
                    							"m.YAML.Tag");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    this_WS_4=(Token)match(input,RULE_WS,FOLLOW_5); 

                    				newLeafNode(this_WS_4, grammarAccess.getFileAccess().getWSTerminalRuleCall_1_3());
                    			

                    }
                    break;

            }

            // InternalYAMLParser.g:128:3: ( ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )? )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==HyphenMinusHyphenMinusHyphenMinus||LA3_0==RULE_WORD) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalYAMLParser.g:129:4: ( (lv_nodes_5_0= ruleNode ) ) (this_WS_6= RULE_WS )?
            	    {
            	    // InternalYAMLParser.g:129:4: ( (lv_nodes_5_0= ruleNode ) )
            	    // InternalYAMLParser.g:130:5: (lv_nodes_5_0= ruleNode )
            	    {
            	    // InternalYAMLParser.g:130:5: (lv_nodes_5_0= ruleNode )
            	    // InternalYAMLParser.g:131:6: lv_nodes_5_0= ruleNode
            	    {

            	    						newCompositeNode(grammarAccess.getFileAccess().getNodesNodeParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_nodes_5_0=ruleNode();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFileRule());
            	    						}
            	    						add(
            	    							current,
            	    							"nodes",
            	    							lv_nodes_5_0,
            	    							"m.YAML.Node");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalYAMLParser.g:148:4: (this_WS_6= RULE_WS )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==RULE_WS) ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // InternalYAMLParser.g:149:5: this_WS_6= RULE_WS
            	            {
            	            this_WS_6=(Token)match(input,RULE_WS,FOLLOW_5); 

            	            					newLeafNode(this_WS_6, grammarAccess.getFileAccess().getWSTerminalRuleCall_2_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


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


    // $ANTLR start "entryRuleNode"
    // InternalYAMLParser.g:159:1: entryRuleNode returns [EObject current=null] : iv_ruleNode= ruleNode EOF ;
    public final EObject entryRuleNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNode = null;


        try {
            // InternalYAMLParser.g:159:45: (iv_ruleNode= ruleNode EOF )
            // InternalYAMLParser.g:160:2: iv_ruleNode= ruleNode EOF
            {
             newCompositeNode(grammarAccess.getNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNode=ruleNode();

            state._fsp--;

             current =iv_ruleNode; 
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
    // $ANTLR end "entryRuleNode"


    // $ANTLR start "ruleNode"
    // InternalYAMLParser.g:166:1: ruleNode returns [EObject current=null] : (this_Document_0= ruleDocument | this_Value_1= ruleValue ) ;
    public final EObject ruleNode() throws RecognitionException {
        EObject current = null;

        EObject this_Document_0 = null;

        EObject this_Value_1 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:172:2: ( (this_Document_0= ruleDocument | this_Value_1= ruleValue ) )
            // InternalYAMLParser.g:173:2: (this_Document_0= ruleDocument | this_Value_1= ruleValue )
            {
            // InternalYAMLParser.g:173:2: (this_Document_0= ruleDocument | this_Value_1= ruleValue )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==HyphenMinusHyphenMinusHyphenMinus) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_WORD) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalYAMLParser.g:174:3: this_Document_0= ruleDocument
                    {

                    			newCompositeNode(grammarAccess.getNodeAccess().getDocumentParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Document_0=ruleDocument();

                    state._fsp--;


                    			current = this_Document_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:183:3: this_Value_1= ruleValue
                    {

                    			newCompositeNode(grammarAccess.getNodeAccess().getValueParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Value_1=ruleValue();

                    state._fsp--;


                    			current = this_Value_1;
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
    // $ANTLR end "ruleNode"


    // $ANTLR start "entryRuleValue"
    // InternalYAMLParser.g:195:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalYAMLParser.g:195:46: (iv_ruleValue= ruleValue EOF )
            // InternalYAMLParser.g:196:2: iv_ruleValue= ruleValue EOF
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
    // InternalYAMLParser.g:202:1: ruleValue returns [EObject current=null] : (this_Map_0= ruleMap | this_KeyValue_1= ruleKeyValue | this_KeyValueList_2= ruleKeyValueList ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_Map_0 = null;

        EObject this_KeyValue_1 = null;

        EObject this_KeyValueList_2 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:208:2: ( (this_Map_0= ruleMap | this_KeyValue_1= ruleKeyValue | this_KeyValueList_2= ruleKeyValueList ) )
            // InternalYAMLParser.g:209:2: (this_Map_0= ruleMap | this_KeyValue_1= ruleKeyValue | this_KeyValueList_2= ruleKeyValueList )
            {
            // InternalYAMLParser.g:209:2: (this_Map_0= ruleMap | this_KeyValue_1= ruleKeyValue | this_KeyValueList_2= ruleKeyValueList )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_WORD) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==Colon) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==RULE_WS) ) {
                        int LA5_3 = input.LA(4);

                        if ( (LA5_3==RULE_BEGIN) ) {
                            alt5=1;
                        }
                        else if ( (LA5_3==HyphenMinus) ) {
                            alt5=3;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 5, 3, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA5_2==RULE_SPACE) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalYAMLParser.g:210:3: this_Map_0= ruleMap
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getMapParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Map_0=ruleMap();

                    state._fsp--;


                    			current = this_Map_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:219:3: this_KeyValue_1= ruleKeyValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getKeyValueParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_KeyValue_1=ruleKeyValue();

                    state._fsp--;


                    			current = this_KeyValue_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalYAMLParser.g:228:3: this_KeyValueList_2= ruleKeyValueList
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getKeyValueListParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_KeyValueList_2=ruleKeyValueList();

                    state._fsp--;


                    			current = this_KeyValueList_2;
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


    // $ANTLR start "entryRuleDocument"
    // InternalYAMLParser.g:240:1: entryRuleDocument returns [EObject current=null] : iv_ruleDocument= ruleDocument EOF ;
    public final EObject entryRuleDocument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDocument = null;


        try {
            // InternalYAMLParser.g:240:49: (iv_ruleDocument= ruleDocument EOF )
            // InternalYAMLParser.g:241:2: iv_ruleDocument= ruleDocument EOF
            {
             newCompositeNode(grammarAccess.getDocumentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDocument=ruleDocument();

            state._fsp--;

             current =iv_ruleDocument; 
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
    // $ANTLR end "entryRuleDocument"


    // $ANTLR start "ruleDocument"
    // InternalYAMLParser.g:247:1: ruleDocument returns [EObject current=null] : ( () otherlv_1= HyphenMinusHyphenMinusHyphenMinus this_SPACE_2= RULE_SPACE otherlv_3= U ( (lv_tag_4_0= RULE_L ) ) this_SPACE_5= RULE_SPACE otherlv_6= Ampersand ( (lv_id_7_0= RULE_L ) ) this_WS_8= RULE_WS ( (lv_value_9_0= ruleMap ) ) ) ;
    public final EObject ruleDocument() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_SPACE_2=null;
        Token otherlv_3=null;
        Token lv_tag_4_0=null;
        Token this_SPACE_5=null;
        Token otherlv_6=null;
        Token lv_id_7_0=null;
        Token this_WS_8=null;
        EObject lv_value_9_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:253:2: ( ( () otherlv_1= HyphenMinusHyphenMinusHyphenMinus this_SPACE_2= RULE_SPACE otherlv_3= U ( (lv_tag_4_0= RULE_L ) ) this_SPACE_5= RULE_SPACE otherlv_6= Ampersand ( (lv_id_7_0= RULE_L ) ) this_WS_8= RULE_WS ( (lv_value_9_0= ruleMap ) ) ) )
            // InternalYAMLParser.g:254:2: ( () otherlv_1= HyphenMinusHyphenMinusHyphenMinus this_SPACE_2= RULE_SPACE otherlv_3= U ( (lv_tag_4_0= RULE_L ) ) this_SPACE_5= RULE_SPACE otherlv_6= Ampersand ( (lv_id_7_0= RULE_L ) ) this_WS_8= RULE_WS ( (lv_value_9_0= ruleMap ) ) )
            {
            // InternalYAMLParser.g:254:2: ( () otherlv_1= HyphenMinusHyphenMinusHyphenMinus this_SPACE_2= RULE_SPACE otherlv_3= U ( (lv_tag_4_0= RULE_L ) ) this_SPACE_5= RULE_SPACE otherlv_6= Ampersand ( (lv_id_7_0= RULE_L ) ) this_WS_8= RULE_WS ( (lv_value_9_0= ruleMap ) ) )
            // InternalYAMLParser.g:255:3: () otherlv_1= HyphenMinusHyphenMinusHyphenMinus this_SPACE_2= RULE_SPACE otherlv_3= U ( (lv_tag_4_0= RULE_L ) ) this_SPACE_5= RULE_SPACE otherlv_6= Ampersand ( (lv_id_7_0= RULE_L ) ) this_WS_8= RULE_WS ( (lv_value_9_0= ruleMap ) )
            {
            // InternalYAMLParser.g:255:3: ()
            // InternalYAMLParser.g:256:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDocumentAccess().getDocumentAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,HyphenMinusHyphenMinusHyphenMinus,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getDocumentAccess().getHyphenMinusHyphenMinusHyphenMinusKeyword_1());
            		
            this_SPACE_2=(Token)match(input,RULE_SPACE,FOLLOW_8); 

            			newLeafNode(this_SPACE_2, grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_2());
            		
            otherlv_3=(Token)match(input,U,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getDocumentAccess().getUKeyword_3());
            		
            // InternalYAMLParser.g:274:3: ( (lv_tag_4_0= RULE_L ) )
            // InternalYAMLParser.g:275:4: (lv_tag_4_0= RULE_L )
            {
            // InternalYAMLParser.g:275:4: (lv_tag_4_0= RULE_L )
            // InternalYAMLParser.g:276:5: lv_tag_4_0= RULE_L
            {
            lv_tag_4_0=(Token)match(input,RULE_L,FOLLOW_7); 

            					newLeafNode(lv_tag_4_0, grammarAccess.getDocumentAccess().getTagLTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDocumentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"tag",
            						lv_tag_4_0,
            						"m.YAML.L");
            				

            }


            }

            this_SPACE_5=(Token)match(input,RULE_SPACE,FOLLOW_10); 

            			newLeafNode(this_SPACE_5, grammarAccess.getDocumentAccess().getSPACETerminalRuleCall_5());
            		
            otherlv_6=(Token)match(input,Ampersand,FOLLOW_9); 

            			newLeafNode(otherlv_6, grammarAccess.getDocumentAccess().getAmpersandKeyword_6());
            		
            // InternalYAMLParser.g:300:3: ( (lv_id_7_0= RULE_L ) )
            // InternalYAMLParser.g:301:4: (lv_id_7_0= RULE_L )
            {
            // InternalYAMLParser.g:301:4: (lv_id_7_0= RULE_L )
            // InternalYAMLParser.g:302:5: lv_id_7_0= RULE_L
            {
            lv_id_7_0=(Token)match(input,RULE_L,FOLLOW_3); 

            					newLeafNode(lv_id_7_0, grammarAccess.getDocumentAccess().getIdLTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDocumentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_7_0,
            						"m.YAML.L");
            				

            }


            }

            this_WS_8=(Token)match(input,RULE_WS,FOLLOW_11); 

            			newLeafNode(this_WS_8, grammarAccess.getDocumentAccess().getWSTerminalRuleCall_8());
            		
            // InternalYAMLParser.g:322:3: ( (lv_value_9_0= ruleMap ) )
            // InternalYAMLParser.g:323:4: (lv_value_9_0= ruleMap )
            {
            // InternalYAMLParser.g:323:4: (lv_value_9_0= ruleMap )
            // InternalYAMLParser.g:324:5: lv_value_9_0= ruleMap
            {

            					newCompositeNode(grammarAccess.getDocumentAccess().getValueMapParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_9_0=ruleMap();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDocumentRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_9_0,
            						"m.YAML.Map");
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
    // $ANTLR end "ruleDocument"


    // $ANTLR start "entryRuleKeyValue"
    // InternalYAMLParser.g:345:1: entryRuleKeyValue returns [EObject current=null] : iv_ruleKeyValue= ruleKeyValue EOF ;
    public final EObject entryRuleKeyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyValue = null;


        try {
            // InternalYAMLParser.g:345:49: (iv_ruleKeyValue= ruleKeyValue EOF )
            // InternalYAMLParser.g:346:2: iv_ruleKeyValue= ruleKeyValue EOF
            {
             newCompositeNode(grammarAccess.getKeyValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyValue=ruleKeyValue();

            state._fsp--;

             current =iv_ruleKeyValue; 
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
    // $ANTLR end "entryRuleKeyValue"


    // $ANTLR start "ruleKeyValue"
    // InternalYAMLParser.g:352:1: ruleKeyValue returns [EObject current=null] : ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_SPACE_2= RULE_SPACE ( (lv_value_3_0= ruleElement ) )? ) ;
    public final EObject ruleKeyValue() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Token this_SPACE_2=null;
        EObject lv_value_3_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:358:2: ( ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_SPACE_2= RULE_SPACE ( (lv_value_3_0= ruleElement ) )? ) )
            // InternalYAMLParser.g:359:2: ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_SPACE_2= RULE_SPACE ( (lv_value_3_0= ruleElement ) )? )
            {
            // InternalYAMLParser.g:359:2: ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_SPACE_2= RULE_SPACE ( (lv_value_3_0= ruleElement ) )? )
            // InternalYAMLParser.g:360:3: ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_SPACE_2= RULE_SPACE ( (lv_value_3_0= ruleElement ) )?
            {
            // InternalYAMLParser.g:360:3: ( (lv_key_0_0= RULE_WORD ) )
            // InternalYAMLParser.g:361:4: (lv_key_0_0= RULE_WORD )
            {
            // InternalYAMLParser.g:361:4: (lv_key_0_0= RULE_WORD )
            // InternalYAMLParser.g:362:5: lv_key_0_0= RULE_WORD
            {
            lv_key_0_0=(Token)match(input,RULE_WORD,FOLLOW_12); 

            					newLeafNode(lv_key_0_0, grammarAccess.getKeyValueAccess().getKeyWORDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKeyValueRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"m.YAML.WORD");
            				

            }


            }

            otherlv_1=(Token)match(input,Colon,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getKeyValueAccess().getColonKeyword_1());
            		
            this_SPACE_2=(Token)match(input,RULE_SPACE,FOLLOW_13); 

            			newLeafNode(this_SPACE_2, grammarAccess.getKeyValueAccess().getSPACETerminalRuleCall_2());
            		
            // InternalYAMLParser.g:386:3: ( (lv_value_3_0= ruleElement ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LeftSquareBracket||LA6_0==LeftCurlyBracket||(LA6_0>=RULE_L && LA6_0<=RULE_GUID)) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_WORD) ) {
                int LA6_2 = input.LA(2);

                if ( (LA6_2==EOF||LA6_2==HyphenMinusHyphenMinusHyphenMinus||LA6_2==Comma||LA6_2==Semicolon||(LA6_2>=RightCurlyBracket && LA6_2<=RULE_WORD)||LA6_2==RULE_SPACE||LA6_2==RULE_WS||LA6_2==RULE_END) ) {
                    alt6=1;
                }
            }
            switch (alt6) {
                case 1 :
                    // InternalYAMLParser.g:387:4: (lv_value_3_0= ruleElement )
                    {
                    // InternalYAMLParser.g:387:4: (lv_value_3_0= ruleElement )
                    // InternalYAMLParser.g:388:5: lv_value_3_0= ruleElement
                    {

                    					newCompositeNode(grammarAccess.getKeyValueAccess().getValueElementParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_value_3_0=ruleElement();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getKeyValueRule());
                    					}
                    					set(
                    						current,
                    						"value",
                    						lv_value_3_0,
                    						"m.YAML.Element");
                    					afterParserOrEnumRuleCall();
                    				

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
    // $ANTLR end "ruleKeyValue"


    // $ANTLR start "entryRuleMap"
    // InternalYAMLParser.g:409:1: entryRuleMap returns [EObject current=null] : iv_ruleMap= ruleMap EOF ;
    public final EObject entryRuleMap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMap = null;


        try {
            // InternalYAMLParser.g:409:44: (iv_ruleMap= ruleMap EOF )
            // InternalYAMLParser.g:410:2: iv_ruleMap= ruleMap EOF
            {
             newCompositeNode(grammarAccess.getMapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMap=ruleMap();

            state._fsp--;

             current =iv_ruleMap; 
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
    // $ANTLR end "entryRuleMap"


    // $ANTLR start "ruleMap"
    // InternalYAMLParser.g:416:1: ruleMap returns [EObject current=null] : ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS this_BEGIN_3= RULE_BEGIN ( (lv_body_4_0= ruleValue ) ) (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )* (this_WS_7= RULE_WS )? this_END_8= RULE_END ) ;
    public final EObject ruleMap() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_1=null;
        Token this_WS_2=null;
        Token this_BEGIN_3=null;
        Token this_WS_5=null;
        Token this_WS_7=null;
        Token this_END_8=null;
        EObject lv_body_4_0 = null;

        EObject lv_body_6_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:422:2: ( ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS this_BEGIN_3= RULE_BEGIN ( (lv_body_4_0= ruleValue ) ) (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )* (this_WS_7= RULE_WS )? this_END_8= RULE_END ) )
            // InternalYAMLParser.g:423:2: ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS this_BEGIN_3= RULE_BEGIN ( (lv_body_4_0= ruleValue ) ) (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )* (this_WS_7= RULE_WS )? this_END_8= RULE_END )
            {
            // InternalYAMLParser.g:423:2: ( ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS this_BEGIN_3= RULE_BEGIN ( (lv_body_4_0= ruleValue ) ) (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )* (this_WS_7= RULE_WS )? this_END_8= RULE_END )
            // InternalYAMLParser.g:424:3: ( (lv_key_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS this_BEGIN_3= RULE_BEGIN ( (lv_body_4_0= ruleValue ) ) (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )* (this_WS_7= RULE_WS )? this_END_8= RULE_END
            {
            // InternalYAMLParser.g:424:3: ( (lv_key_0_0= RULE_WORD ) )
            // InternalYAMLParser.g:425:4: (lv_key_0_0= RULE_WORD )
            {
            // InternalYAMLParser.g:425:4: (lv_key_0_0= RULE_WORD )
            // InternalYAMLParser.g:426:5: lv_key_0_0= RULE_WORD
            {
            lv_key_0_0=(Token)match(input,RULE_WORD,FOLLOW_12); 

            					newLeafNode(lv_key_0_0, grammarAccess.getMapAccess().getKeyWORDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMapRule());
            					}
            					setWithLastConsumed(
            						current,
            						"key",
            						lv_key_0_0,
            						"m.YAML.WORD");
            				

            }


            }

            otherlv_1=(Token)match(input,Colon,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getMapAccess().getColonKeyword_1());
            		
            this_WS_2=(Token)match(input,RULE_WS,FOLLOW_14); 

            			newLeafNode(this_WS_2, grammarAccess.getMapAccess().getWSTerminalRuleCall_2());
            		
            this_BEGIN_3=(Token)match(input,RULE_BEGIN,FOLLOW_15); 

            			newLeafNode(this_BEGIN_3, grammarAccess.getMapAccess().getBEGINTerminalRuleCall_3());
            		
            // InternalYAMLParser.g:454:3: ( (lv_body_4_0= ruleValue ) )
            // InternalYAMLParser.g:455:4: (lv_body_4_0= ruleValue )
            {
            // InternalYAMLParser.g:455:4: (lv_body_4_0= ruleValue )
            // InternalYAMLParser.g:456:5: lv_body_4_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getMapAccess().getBodyValueParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_16);
            lv_body_4_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMapRule());
            					}
            					add(
            						current,
            						"body",
            						lv_body_4_0,
            						"m.YAML.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalYAMLParser.g:473:3: (this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_WS) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==RULE_WORD) ) {
                        alt7=1;
                    }


                }


                switch (alt7) {
            	case 1 :
            	    // InternalYAMLParser.g:474:4: this_WS_5= RULE_WS ( (lv_body_6_0= ruleValue ) )
            	    {
            	    this_WS_5=(Token)match(input,RULE_WS,FOLLOW_15); 

            	    				newLeafNode(this_WS_5, grammarAccess.getMapAccess().getWSTerminalRuleCall_5_0());
            	    			
            	    // InternalYAMLParser.g:478:4: ( (lv_body_6_0= ruleValue ) )
            	    // InternalYAMLParser.g:479:5: (lv_body_6_0= ruleValue )
            	    {
            	    // InternalYAMLParser.g:479:5: (lv_body_6_0= ruleValue )
            	    // InternalYAMLParser.g:480:6: lv_body_6_0= ruleValue
            	    {

            	    						newCompositeNode(grammarAccess.getMapAccess().getBodyValueParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_16);
            	    lv_body_6_0=ruleValue();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMapRule());
            	    						}
            	    						add(
            	    							current,
            	    							"body",
            	    							lv_body_6_0,
            	    							"m.YAML.Value");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalYAMLParser.g:498:3: (this_WS_7= RULE_WS )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_WS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalYAMLParser.g:499:4: this_WS_7= RULE_WS
                    {
                    this_WS_7=(Token)match(input,RULE_WS,FOLLOW_17); 

                    				newLeafNode(this_WS_7, grammarAccess.getMapAccess().getWSTerminalRuleCall_6());
                    			

                    }
                    break;

            }

            this_END_8=(Token)match(input,RULE_END,FOLLOW_2); 

            			newLeafNode(this_END_8, grammarAccess.getMapAccess().getENDTerminalRuleCall_7());
            		

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
    // $ANTLR end "ruleMap"


    // $ANTLR start "entryRuleKeyValueList"
    // InternalYAMLParser.g:512:1: entryRuleKeyValueList returns [EObject current=null] : iv_ruleKeyValueList= ruleKeyValueList EOF ;
    public final EObject entryRuleKeyValueList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyValueList = null;


        try {
            // InternalYAMLParser.g:512:53: (iv_ruleKeyValueList= ruleKeyValueList EOF )
            // InternalYAMLParser.g:513:2: iv_ruleKeyValueList= ruleKeyValueList EOF
            {
             newCompositeNode(grammarAccess.getKeyValueListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyValueList=ruleKeyValueList();

            state._fsp--;

             current =iv_ruleKeyValueList; 
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
    // $ANTLR end "entryRuleKeyValueList"


    // $ANTLR start "ruleKeyValueList"
    // InternalYAMLParser.g:519:1: ruleKeyValueList returns [EObject current=null] : ( ( (lv_name_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS otherlv_3= HyphenMinus this_SPACE_4= RULE_SPACE ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) ) (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )* ) ;
    public final EObject ruleKeyValueList() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token this_WS_2=null;
        Token otherlv_3=null;
        Token this_SPACE_4=null;
        Token this_WS_6=null;
        Token otherlv_7=null;
        Token this_SPACE_8=null;
        EObject lv_values_5_1 = null;

        EObject lv_values_5_2 = null;

        EObject lv_values_9_1 = null;

        EObject lv_values_9_2 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:525:2: ( ( ( (lv_name_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS otherlv_3= HyphenMinus this_SPACE_4= RULE_SPACE ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) ) (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )* ) )
            // InternalYAMLParser.g:526:2: ( ( (lv_name_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS otherlv_3= HyphenMinus this_SPACE_4= RULE_SPACE ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) ) (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )* )
            {
            // InternalYAMLParser.g:526:2: ( ( (lv_name_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS otherlv_3= HyphenMinus this_SPACE_4= RULE_SPACE ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) ) (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )* )
            // InternalYAMLParser.g:527:3: ( (lv_name_0_0= RULE_WORD ) ) otherlv_1= Colon this_WS_2= RULE_WS otherlv_3= HyphenMinus this_SPACE_4= RULE_SPACE ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) ) (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )*
            {
            // InternalYAMLParser.g:527:3: ( (lv_name_0_0= RULE_WORD ) )
            // InternalYAMLParser.g:528:4: (lv_name_0_0= RULE_WORD )
            {
            // InternalYAMLParser.g:528:4: (lv_name_0_0= RULE_WORD )
            // InternalYAMLParser.g:529:5: lv_name_0_0= RULE_WORD
            {
            lv_name_0_0=(Token)match(input,RULE_WORD,FOLLOW_12); 

            					newLeafNode(lv_name_0_0, grammarAccess.getKeyValueListAccess().getNameWORDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKeyValueListRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.YAML.WORD");
            				

            }


            }

            otherlv_1=(Token)match(input,Colon,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getKeyValueListAccess().getColonKeyword_1());
            		
            this_WS_2=(Token)match(input,RULE_WS,FOLLOW_18); 

            			newLeafNode(this_WS_2, grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_2());
            		
            otherlv_3=(Token)match(input,HyphenMinus,FOLLOW_7); 

            			newLeafNode(otherlv_3, grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_3());
            		
            this_SPACE_4=(Token)match(input,RULE_SPACE,FOLLOW_19); 

            			newLeafNode(this_SPACE_4, grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_4());
            		
            // InternalYAMLParser.g:561:3: ( ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) ) )
            // InternalYAMLParser.g:562:4: ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) )
            {
            // InternalYAMLParser.g:562:4: ( (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement ) )
            // InternalYAMLParser.g:563:5: (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement )
            {
            // InternalYAMLParser.g:563:5: (lv_values_5_1= ruleKeyValue | lv_values_5_2= ruleElement )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_WORD) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==EOF||LA9_1==HyphenMinusHyphenMinusHyphenMinus||LA9_1==Semicolon||LA9_1==RULE_WORD||LA9_1==RULE_WS||LA9_1==RULE_END) ) {
                    alt9=2;
                }
                else if ( (LA9_1==Colon) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA9_0==LeftSquareBracket||LA9_0==LeftCurlyBracket||(LA9_0>=RULE_L && LA9_0<=RULE_GUID)) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalYAMLParser.g:564:6: lv_values_5_1= ruleKeyValue
                    {

                    						newCompositeNode(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_5_0_0());
                    					
                    pushFollow(FOLLOW_20);
                    lv_values_5_1=ruleKeyValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKeyValueListRule());
                    						}
                    						add(
                    							current,
                    							"values",
                    							lv_values_5_1,
                    							"m.YAML.KeyValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:580:6: lv_values_5_2= ruleElement
                    {

                    						newCompositeNode(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_5_0_1());
                    					
                    pushFollow(FOLLOW_20);
                    lv_values_5_2=ruleElement();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getKeyValueListRule());
                    						}
                    						add(
                    							current,
                    							"values",
                    							lv_values_5_2,
                    							"m.YAML.Element");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            // InternalYAMLParser.g:598:3: (this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_WS) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==HyphenMinus) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // InternalYAMLParser.g:599:4: this_WS_6= RULE_WS otherlv_7= HyphenMinus this_SPACE_8= RULE_SPACE ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) )
            	    {
            	    this_WS_6=(Token)match(input,RULE_WS,FOLLOW_18); 

            	    				newLeafNode(this_WS_6, grammarAccess.getKeyValueListAccess().getWSTerminalRuleCall_6_0());
            	    			
            	    otherlv_7=(Token)match(input,HyphenMinus,FOLLOW_7); 

            	    				newLeafNode(otherlv_7, grammarAccess.getKeyValueListAccess().getHyphenMinusKeyword_6_1());
            	    			
            	    this_SPACE_8=(Token)match(input,RULE_SPACE,FOLLOW_19); 

            	    				newLeafNode(this_SPACE_8, grammarAccess.getKeyValueListAccess().getSPACETerminalRuleCall_6_2());
            	    			
            	    // InternalYAMLParser.g:611:4: ( ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) ) )
            	    // InternalYAMLParser.g:612:5: ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) )
            	    {
            	    // InternalYAMLParser.g:612:5: ( (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement ) )
            	    // InternalYAMLParser.g:613:6: (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement )
            	    {
            	    // InternalYAMLParser.g:613:6: (lv_values_9_1= ruleKeyValue | lv_values_9_2= ruleElement )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==RULE_WORD) ) {
            	        int LA10_1 = input.LA(2);

            	        if ( (LA10_1==EOF||LA10_1==HyphenMinusHyphenMinusHyphenMinus||LA10_1==Semicolon||LA10_1==RULE_WORD||LA10_1==RULE_WS||LA10_1==RULE_END) ) {
            	            alt10=2;
            	        }
            	        else if ( (LA10_1==Colon) ) {
            	            alt10=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 10, 1, input);

            	            throw nvae;
            	        }
            	    }
            	    else if ( (LA10_0==LeftSquareBracket||LA10_0==LeftCurlyBracket||(LA10_0>=RULE_L && LA10_0<=RULE_GUID)) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // InternalYAMLParser.g:614:7: lv_values_9_1= ruleKeyValue
            	            {

            	            							newCompositeNode(grammarAccess.getKeyValueListAccess().getValuesKeyValueParserRuleCall_6_3_0_0());
            	            						
            	            pushFollow(FOLLOW_20);
            	            lv_values_9_1=ruleKeyValue();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getKeyValueListRule());
            	            							}
            	            							add(
            	            								current,
            	            								"values",
            	            								lv_values_9_1,
            	            								"m.YAML.KeyValue");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalYAMLParser.g:630:7: lv_values_9_2= ruleElement
            	            {

            	            							newCompositeNode(grammarAccess.getKeyValueListAccess().getValuesElementParserRuleCall_6_3_0_1());
            	            						
            	            pushFollow(FOLLOW_20);
            	            lv_values_9_2=ruleElement();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getKeyValueListRule());
            	            							}
            	            							add(
            	            								current,
            	            								"values",
            	            								lv_values_9_2,
            	            								"m.YAML.Element");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


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
    // $ANTLR end "ruleKeyValueList"


    // $ANTLR start "entryRuleElement"
    // InternalYAMLParser.g:653:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // InternalYAMLParser.g:653:48: (iv_ruleElement= ruleElement EOF )
            // InternalYAMLParser.g:654:2: iv_ruleElement= ruleElement EOF
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
    // InternalYAMLParser.g:660:1: ruleElement returns [EObject current=null] : (this_InlineMap_0= ruleInlineMap | this_InlineList_1= ruleInlineList | this_InlineSemicolonList_2= ruleInlineSemicolonList | ( () ( (lv_value_4_0= RULE_WORD ) ) ) | ( () ( (lv_value_6_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_8_0= RULE_L ) ) ) | ( () ( (lv_value_10_0= RULE_GUID ) ) ) ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        Token lv_value_4_0=null;
        Token lv_value_6_0=null;
        Token lv_value_8_0=null;
        Token lv_value_10_0=null;
        EObject this_InlineMap_0 = null;

        EObject this_InlineList_1 = null;

        EObject this_InlineSemicolonList_2 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:666:2: ( (this_InlineMap_0= ruleInlineMap | this_InlineList_1= ruleInlineList | this_InlineSemicolonList_2= ruleInlineSemicolonList | ( () ( (lv_value_4_0= RULE_WORD ) ) ) | ( () ( (lv_value_6_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_8_0= RULE_L ) ) ) | ( () ( (lv_value_10_0= RULE_GUID ) ) ) ) )
            // InternalYAMLParser.g:667:2: (this_InlineMap_0= ruleInlineMap | this_InlineList_1= ruleInlineList | this_InlineSemicolonList_2= ruleInlineSemicolonList | ( () ( (lv_value_4_0= RULE_WORD ) ) ) | ( () ( (lv_value_6_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_8_0= RULE_L ) ) ) | ( () ( (lv_value_10_0= RULE_GUID ) ) ) )
            {
            // InternalYAMLParser.g:667:2: (this_InlineMap_0= ruleInlineMap | this_InlineList_1= ruleInlineList | this_InlineSemicolonList_2= ruleInlineSemicolonList | ( () ( (lv_value_4_0= RULE_WORD ) ) ) | ( () ( (lv_value_6_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_8_0= RULE_L ) ) ) | ( () ( (lv_value_10_0= RULE_GUID ) ) ) )
            int alt12=7;
            switch ( input.LA(1) ) {
            case LeftCurlyBracket:
                {
                alt12=1;
                }
                break;
            case LeftSquareBracket:
                {
                alt12=2;
                }
                break;
            case RULE_WORD:
                {
                int LA12_3 = input.LA(2);

                if ( (LA12_3==EOF||LA12_3==HyphenMinusHyphenMinusHyphenMinus||LA12_3==Comma||LA12_3==RightSquareBracket||(LA12_3>=RightCurlyBracket && LA12_3<=RULE_WORD)||LA12_3==RULE_SPACE||LA12_3==RULE_WS||LA12_3==RULE_END) ) {
                    alt12=4;
                }
                else if ( (LA12_3==Semicolon) ) {
                    alt12=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 3, input);

                    throw nvae;
                }
                }
                break;
            case RULE_FLOAT:
                {
                alt12=5;
                }
                break;
            case RULE_L:
                {
                alt12=6;
                }
                break;
            case RULE_GUID:
                {
                alt12=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalYAMLParser.g:668:3: this_InlineMap_0= ruleInlineMap
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getInlineMapParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineMap_0=ruleInlineMap();

                    state._fsp--;


                    			current = this_InlineMap_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalYAMLParser.g:677:3: this_InlineList_1= ruleInlineList
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getInlineListParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineList_1=ruleInlineList();

                    state._fsp--;


                    			current = this_InlineList_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalYAMLParser.g:686:3: this_InlineSemicolonList_2= ruleInlineSemicolonList
                    {

                    			newCompositeNode(grammarAccess.getElementAccess().getInlineSemicolonListParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineSemicolonList_2=ruleInlineSemicolonList();

                    state._fsp--;


                    			current = this_InlineSemicolonList_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalYAMLParser.g:695:3: ( () ( (lv_value_4_0= RULE_WORD ) ) )
                    {
                    // InternalYAMLParser.g:695:3: ( () ( (lv_value_4_0= RULE_WORD ) ) )
                    // InternalYAMLParser.g:696:4: () ( (lv_value_4_0= RULE_WORD ) )
                    {
                    // InternalYAMLParser.g:696:4: ()
                    // InternalYAMLParser.g:697:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getElementAccess().getWordAction_3_0(),
                    						current);
                    				

                    }

                    // InternalYAMLParser.g:703:4: ( (lv_value_4_0= RULE_WORD ) )
                    // InternalYAMLParser.g:704:5: (lv_value_4_0= RULE_WORD )
                    {
                    // InternalYAMLParser.g:704:5: (lv_value_4_0= RULE_WORD )
                    // InternalYAMLParser.g:705:6: lv_value_4_0= RULE_WORD
                    {
                    lv_value_4_0=(Token)match(input,RULE_WORD,FOLLOW_2); 

                    						newLeafNode(lv_value_4_0, grammarAccess.getElementAccess().getValueWORDTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElementRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_4_0,
                    							"m.YAML.WORD");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalYAMLParser.g:723:3: ( () ( (lv_value_6_0= RULE_FLOAT ) ) )
                    {
                    // InternalYAMLParser.g:723:3: ( () ( (lv_value_6_0= RULE_FLOAT ) ) )
                    // InternalYAMLParser.g:724:4: () ( (lv_value_6_0= RULE_FLOAT ) )
                    {
                    // InternalYAMLParser.g:724:4: ()
                    // InternalYAMLParser.g:725:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getElementAccess().getFloatAction_4_0(),
                    						current);
                    				

                    }

                    // InternalYAMLParser.g:731:4: ( (lv_value_6_0= RULE_FLOAT ) )
                    // InternalYAMLParser.g:732:5: (lv_value_6_0= RULE_FLOAT )
                    {
                    // InternalYAMLParser.g:732:5: (lv_value_6_0= RULE_FLOAT )
                    // InternalYAMLParser.g:733:6: lv_value_6_0= RULE_FLOAT
                    {
                    lv_value_6_0=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    						newLeafNode(lv_value_6_0, grammarAccess.getElementAccess().getValueFLOATTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElementRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_6_0,
                    							"m.YAML.FLOAT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalYAMLParser.g:751:3: ( () ( (lv_value_8_0= RULE_L ) ) )
                    {
                    // InternalYAMLParser.g:751:3: ( () ( (lv_value_8_0= RULE_L ) ) )
                    // InternalYAMLParser.g:752:4: () ( (lv_value_8_0= RULE_L ) )
                    {
                    // InternalYAMLParser.g:752:4: ()
                    // InternalYAMLParser.g:753:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getElementAccess().getLongAction_5_0(),
                    						current);
                    				

                    }

                    // InternalYAMLParser.g:759:4: ( (lv_value_8_0= RULE_L ) )
                    // InternalYAMLParser.g:760:5: (lv_value_8_0= RULE_L )
                    {
                    // InternalYAMLParser.g:760:5: (lv_value_8_0= RULE_L )
                    // InternalYAMLParser.g:761:6: lv_value_8_0= RULE_L
                    {
                    lv_value_8_0=(Token)match(input,RULE_L,FOLLOW_2); 

                    						newLeafNode(lv_value_8_0, grammarAccess.getElementAccess().getValueLTerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElementRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_8_0,
                    							"m.YAML.L");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalYAMLParser.g:779:3: ( () ( (lv_value_10_0= RULE_GUID ) ) )
                    {
                    // InternalYAMLParser.g:779:3: ( () ( (lv_value_10_0= RULE_GUID ) ) )
                    // InternalYAMLParser.g:780:4: () ( (lv_value_10_0= RULE_GUID ) )
                    {
                    // InternalYAMLParser.g:780:4: ()
                    // InternalYAMLParser.g:781:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getElementAccess().getGuidAction_6_0(),
                    						current);
                    				

                    }

                    // InternalYAMLParser.g:787:4: ( (lv_value_10_0= RULE_GUID ) )
                    // InternalYAMLParser.g:788:5: (lv_value_10_0= RULE_GUID )
                    {
                    // InternalYAMLParser.g:788:5: (lv_value_10_0= RULE_GUID )
                    // InternalYAMLParser.g:789:6: lv_value_10_0= RULE_GUID
                    {
                    lv_value_10_0=(Token)match(input,RULE_GUID,FOLLOW_2); 

                    						newLeafNode(lv_value_10_0, grammarAccess.getElementAccess().getValueGUIDTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElementRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_10_0,
                    							"m.YAML.GUID");
                    					

                    }


                    }


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
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleInlineList"
    // InternalYAMLParser.g:810:1: entryRuleInlineList returns [EObject current=null] : iv_ruleInlineList= ruleInlineList EOF ;
    public final EObject entryRuleInlineList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineList = null;


        try {
            // InternalYAMLParser.g:810:51: (iv_ruleInlineList= ruleInlineList EOF )
            // InternalYAMLParser.g:811:2: iv_ruleInlineList= ruleInlineList EOF
            {
             newCompositeNode(grammarAccess.getInlineListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineList=ruleInlineList();

            state._fsp--;

             current =iv_ruleInlineList; 
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
    // $ANTLR end "entryRuleInlineList"


    // $ANTLR start "ruleInlineList"
    // InternalYAMLParser.g:817:1: ruleInlineList returns [EObject current=null] : ( () otherlv_1= LeftSquareBracket ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )? otherlv_6= RightSquareBracket ) ;
    public final EObject ruleInlineList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token this_SPACE_4=null;
        Token otherlv_6=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_5_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:823:2: ( ( () otherlv_1= LeftSquareBracket ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )? otherlv_6= RightSquareBracket ) )
            // InternalYAMLParser.g:824:2: ( () otherlv_1= LeftSquareBracket ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )? otherlv_6= RightSquareBracket )
            {
            // InternalYAMLParser.g:824:2: ( () otherlv_1= LeftSquareBracket ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )? otherlv_6= RightSquareBracket )
            // InternalYAMLParser.g:825:3: () otherlv_1= LeftSquareBracket ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )? otherlv_6= RightSquareBracket
            {
            // InternalYAMLParser.g:825:3: ()
            // InternalYAMLParser.g:826:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineListAccess().getInlineListAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,LeftSquareBracket,FOLLOW_21); 

            			newLeafNode(otherlv_1, grammarAccess.getInlineListAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalYAMLParser.g:836:3: ( ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==LeftSquareBracket||LA14_0==LeftCurlyBracket||(LA14_0>=RULE_WORD && LA14_0<=RULE_GUID)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalYAMLParser.g:837:4: ( (lv_elements_2_0= ruleElement ) ) (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )*
                    {
                    // InternalYAMLParser.g:837:4: ( (lv_elements_2_0= ruleElement ) )
                    // InternalYAMLParser.g:838:5: (lv_elements_2_0= ruleElement )
                    {
                    // InternalYAMLParser.g:838:5: (lv_elements_2_0= ruleElement )
                    // InternalYAMLParser.g:839:6: lv_elements_2_0= ruleElement
                    {

                    						newCompositeNode(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_22);
                    lv_elements_2_0=ruleElement();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getInlineListRule());
                    						}
                    						add(
                    							current,
                    							"elements",
                    							lv_elements_2_0,
                    							"m.YAML.Element");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalYAMLParser.g:856:4: (otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==Comma) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalYAMLParser.g:857:5: otherlv_3= Comma this_SPACE_4= RULE_SPACE ( (lv_elements_5_0= ruleElement ) )
                    	    {
                    	    otherlv_3=(Token)match(input,Comma,FOLLOW_7); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getInlineListAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    this_SPACE_4=(Token)match(input,RULE_SPACE,FOLLOW_19); 

                    	    					newLeafNode(this_SPACE_4, grammarAccess.getInlineListAccess().getSPACETerminalRuleCall_2_1_1());
                    	    				
                    	    // InternalYAMLParser.g:865:5: ( (lv_elements_5_0= ruleElement ) )
                    	    // InternalYAMLParser.g:866:6: (lv_elements_5_0= ruleElement )
                    	    {
                    	    // InternalYAMLParser.g:866:6: (lv_elements_5_0= ruleElement )
                    	    // InternalYAMLParser.g:867:7: lv_elements_5_0= ruleElement
                    	    {

                    	    							newCompositeNode(grammarAccess.getInlineListAccess().getElementsElementParserRuleCall_2_1_2_0());
                    	    						
                    	    pushFollow(FOLLOW_22);
                    	    lv_elements_5_0=ruleElement();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getInlineListRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"elements",
                    	    								lv_elements_5_0,
                    	    								"m.YAML.Element");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,RightSquareBracket,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getInlineListAccess().getRightSquareBracketKeyword_3());
            		

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
    // $ANTLR end "ruleInlineList"


    // $ANTLR start "entryRuleInlineMap"
    // InternalYAMLParser.g:894:1: entryRuleInlineMap returns [EObject current=null] : iv_ruleInlineMap= ruleInlineMap EOF ;
    public final EObject entryRuleInlineMap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineMap = null;


        try {
            // InternalYAMLParser.g:894:50: (iv_ruleInlineMap= ruleInlineMap EOF )
            // InternalYAMLParser.g:895:2: iv_ruleInlineMap= ruleInlineMap EOF
            {
             newCompositeNode(grammarAccess.getInlineMapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineMap=ruleInlineMap();

            state._fsp--;

             current =iv_ruleInlineMap; 
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
    // $ANTLR end "entryRuleInlineMap"


    // $ANTLR start "ruleInlineMap"
    // InternalYAMLParser.g:901:1: ruleInlineMap returns [EObject current=null] : ( () otherlv_1= LeftCurlyBracket ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )? otherlv_8= RightCurlyBracket ) ;
    public final EObject ruleInlineMap() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_SPACE_2=null;
        Token otherlv_4=null;
        Token this_SPACE_5=null;
        Token this_SPACE_7=null;
        Token otherlv_8=null;
        EObject lv_values_3_0 = null;

        EObject lv_values_6_0 = null;



        	enterRule();

        try {
            // InternalYAMLParser.g:907:2: ( ( () otherlv_1= LeftCurlyBracket ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )? otherlv_8= RightCurlyBracket ) )
            // InternalYAMLParser.g:908:2: ( () otherlv_1= LeftCurlyBracket ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )? otherlv_8= RightCurlyBracket )
            {
            // InternalYAMLParser.g:908:2: ( () otherlv_1= LeftCurlyBracket ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )? otherlv_8= RightCurlyBracket )
            // InternalYAMLParser.g:909:3: () otherlv_1= LeftCurlyBracket ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )? otherlv_8= RightCurlyBracket
            {
            // InternalYAMLParser.g:909:3: ()
            // InternalYAMLParser.g:910:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineMapAccess().getInlineMapAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,LeftCurlyBracket,FOLLOW_23); 

            			newLeafNode(otherlv_1, grammarAccess.getInlineMapAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalYAMLParser.g:920:3: ( (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )? )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_WORD||LA18_0==RULE_SPACE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalYAMLParser.g:921:4: (this_SPACE_2= RULE_SPACE )? ( (lv_values_3_0= ruleKeyValue ) ) (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )* (this_SPACE_7= RULE_SPACE )?
                    {
                    // InternalYAMLParser.g:921:4: (this_SPACE_2= RULE_SPACE )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==RULE_SPACE) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalYAMLParser.g:922:5: this_SPACE_2= RULE_SPACE
                            {
                            this_SPACE_2=(Token)match(input,RULE_SPACE,FOLLOW_11); 

                            					newLeafNode(this_SPACE_2, grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_0());
                            				

                            }
                            break;

                    }

                    // InternalYAMLParser.g:927:4: ( (lv_values_3_0= ruleKeyValue ) )
                    // InternalYAMLParser.g:928:5: (lv_values_3_0= ruleKeyValue )
                    {
                    // InternalYAMLParser.g:928:5: (lv_values_3_0= ruleKeyValue )
                    // InternalYAMLParser.g:929:6: lv_values_3_0= ruleKeyValue
                    {

                    						newCompositeNode(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_values_3_0=ruleKeyValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getInlineMapRule());
                    						}
                    						add(
                    							current,
                    							"values",
                    							lv_values_3_0,
                    							"m.YAML.KeyValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalYAMLParser.g:946:4: (otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==Comma) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // InternalYAMLParser.g:947:5: otherlv_4= Comma this_SPACE_5= RULE_SPACE ( (lv_values_6_0= ruleKeyValue ) )
                    	    {
                    	    otherlv_4=(Token)match(input,Comma,FOLLOW_7); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getInlineMapAccess().getCommaKeyword_2_2_0());
                    	    				
                    	    this_SPACE_5=(Token)match(input,RULE_SPACE,FOLLOW_11); 

                    	    					newLeafNode(this_SPACE_5, grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_2_1());
                    	    				
                    	    // InternalYAMLParser.g:955:5: ( (lv_values_6_0= ruleKeyValue ) )
                    	    // InternalYAMLParser.g:956:6: (lv_values_6_0= ruleKeyValue )
                    	    {
                    	    // InternalYAMLParser.g:956:6: (lv_values_6_0= ruleKeyValue )
                    	    // InternalYAMLParser.g:957:7: lv_values_6_0= ruleKeyValue
                    	    {

                    	    							newCompositeNode(grammarAccess.getInlineMapAccess().getValuesKeyValueParserRuleCall_2_2_2_0());
                    	    						
                    	    pushFollow(FOLLOW_24);
                    	    lv_values_6_0=ruleKeyValue();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getInlineMapRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"values",
                    	    								lv_values_6_0,
                    	    								"m.YAML.KeyValue");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    // InternalYAMLParser.g:975:4: (this_SPACE_7= RULE_SPACE )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==RULE_SPACE) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalYAMLParser.g:976:5: this_SPACE_7= RULE_SPACE
                            {
                            this_SPACE_7=(Token)match(input,RULE_SPACE,FOLLOW_25); 

                            					newLeafNode(this_SPACE_7, grammarAccess.getInlineMapAccess().getSPACETerminalRuleCall_2_3());
                            				

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,RightCurlyBracket,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getInlineMapAccess().getRightCurlyBracketKeyword_3());
            		

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
    // $ANTLR end "ruleInlineMap"


    // $ANTLR start "entryRuleInlineSemicolonList"
    // InternalYAMLParser.g:990:1: entryRuleInlineSemicolonList returns [EObject current=null] : iv_ruleInlineSemicolonList= ruleInlineSemicolonList EOF ;
    public final EObject entryRuleInlineSemicolonList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineSemicolonList = null;


        try {
            // InternalYAMLParser.g:990:60: (iv_ruleInlineSemicolonList= ruleInlineSemicolonList EOF )
            // InternalYAMLParser.g:991:2: iv_ruleInlineSemicolonList= ruleInlineSemicolonList EOF
            {
             newCompositeNode(grammarAccess.getInlineSemicolonListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineSemicolonList=ruleInlineSemicolonList();

            state._fsp--;

             current =iv_ruleInlineSemicolonList; 
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
    // $ANTLR end "entryRuleInlineSemicolonList"


    // $ANTLR start "ruleInlineSemicolonList"
    // InternalYAMLParser.g:997:1: ruleInlineSemicolonList returns [EObject current=null] : ( () ( (lv_elements_1_0= RULE_WORD ) ) otherlv_2= Semicolon ( (lv_elements_3_0= RULE_WORD ) ) (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )* ) ;
    public final EObject ruleInlineSemicolonList() throws RecognitionException {
        EObject current = null;

        Token lv_elements_1_0=null;
        Token otherlv_2=null;
        Token lv_elements_3_0=null;
        Token otherlv_4=null;
        Token lv_elements_5_0=null;


        	enterRule();

        try {
            // InternalYAMLParser.g:1003:2: ( ( () ( (lv_elements_1_0= RULE_WORD ) ) otherlv_2= Semicolon ( (lv_elements_3_0= RULE_WORD ) ) (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )* ) )
            // InternalYAMLParser.g:1004:2: ( () ( (lv_elements_1_0= RULE_WORD ) ) otherlv_2= Semicolon ( (lv_elements_3_0= RULE_WORD ) ) (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )* )
            {
            // InternalYAMLParser.g:1004:2: ( () ( (lv_elements_1_0= RULE_WORD ) ) otherlv_2= Semicolon ( (lv_elements_3_0= RULE_WORD ) ) (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )* )
            // InternalYAMLParser.g:1005:3: () ( (lv_elements_1_0= RULE_WORD ) ) otherlv_2= Semicolon ( (lv_elements_3_0= RULE_WORD ) ) (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )*
            {
            // InternalYAMLParser.g:1005:3: ()
            // InternalYAMLParser.g:1006:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineSemicolonListAccess().getInlineSemicolonListAction_0(),
            					current);
            			

            }

            // InternalYAMLParser.g:1012:3: ( (lv_elements_1_0= RULE_WORD ) )
            // InternalYAMLParser.g:1013:4: (lv_elements_1_0= RULE_WORD )
            {
            // InternalYAMLParser.g:1013:4: (lv_elements_1_0= RULE_WORD )
            // InternalYAMLParser.g:1014:5: lv_elements_1_0= RULE_WORD
            {
            lv_elements_1_0=(Token)match(input,RULE_WORD,FOLLOW_26); 

            					newLeafNode(lv_elements_1_0, grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInlineSemicolonListRule());
            					}
            					addWithLastConsumed(
            						current,
            						"elements",
            						lv_elements_1_0,
            						"m.YAML.WORD");
            				

            }


            }

            otherlv_2=(Token)match(input,Semicolon,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_2());
            		
            // InternalYAMLParser.g:1034:3: ( (lv_elements_3_0= RULE_WORD ) )
            // InternalYAMLParser.g:1035:4: (lv_elements_3_0= RULE_WORD )
            {
            // InternalYAMLParser.g:1035:4: (lv_elements_3_0= RULE_WORD )
            // InternalYAMLParser.g:1036:5: lv_elements_3_0= RULE_WORD
            {
            lv_elements_3_0=(Token)match(input,RULE_WORD,FOLLOW_27); 

            					newLeafNode(lv_elements_3_0, grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInlineSemicolonListRule());
            					}
            					addWithLastConsumed(
            						current,
            						"elements",
            						lv_elements_3_0,
            						"m.YAML.WORD");
            				

            }


            }

            // InternalYAMLParser.g:1052:3: (otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==Semicolon) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalYAMLParser.g:1053:4: otherlv_4= Semicolon ( (lv_elements_5_0= RULE_WORD ) )
            	    {
            	    otherlv_4=(Token)match(input,Semicolon,FOLLOW_11); 

            	    				newLeafNode(otherlv_4, grammarAccess.getInlineSemicolonListAccess().getSemicolonKeyword_4_0());
            	    			
            	    // InternalYAMLParser.g:1057:4: ( (lv_elements_5_0= RULE_WORD ) )
            	    // InternalYAMLParser.g:1058:5: (lv_elements_5_0= RULE_WORD )
            	    {
            	    // InternalYAMLParser.g:1058:5: (lv_elements_5_0= RULE_WORD )
            	    // InternalYAMLParser.g:1059:6: lv_elements_5_0= RULE_WORD
            	    {
            	    lv_elements_5_0=(Token)match(input,RULE_WORD,FOLLOW_27); 

            	    						newLeafNode(lv_elements_5_0, grammarAccess.getInlineSemicolonListAccess().getElementsWORDTerminalRuleCall_4_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getInlineSemicolonListRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"elements",
            	    							lv_elements_5_0,
            	    							"m.YAML.WORD");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


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
    // $ANTLR end "ruleInlineSemicolonList"


    // $ANTLR start "ruleVersion"
    // InternalYAMLParser.g:1080:1: ruleVersion returns [Enumerator current=null] : (enumLiteral_0= YAML11 ) ;
    public final Enumerator ruleVersion() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalYAMLParser.g:1086:2: ( (enumLiteral_0= YAML11 ) )
            // InternalYAMLParser.g:1087:2: (enumLiteral_0= YAML11 )
            {
            // InternalYAMLParser.g:1087:2: (enumLiteral_0= YAML11 )
            // InternalYAMLParser.g:1088:3: enumLiteral_0= YAML11
            {
            enumLiteral_0=(Token)match(input,YAML11,FOLLOW_2); 

            			current = grammarAccess.getVersionAccess().getOneOneEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getVersionAccess().getOneOneEnumLiteralDeclaration());
            		

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
    // $ANTLR end "ruleVersion"


    // $ANTLR start "ruleTag"
    // InternalYAMLParser.g:1097:1: ruleTag returns [Enumerator current=null] : (enumLiteral_0= TAGUTagUnity3dCom2011 ) ;
    public final Enumerator ruleTag() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalYAMLParser.g:1103:2: ( (enumLiteral_0= TAGUTagUnity3dCom2011 ) )
            // InternalYAMLParser.g:1104:2: (enumLiteral_0= TAGUTagUnity3dCom2011 )
            {
            // InternalYAMLParser.g:1104:2: (enumLiteral_0= TAGUTagUnity3dCom2011 )
            // InternalYAMLParser.g:1105:3: enumLiteral_0= TAGUTagUnity3dCom2011
            {
            enumLiteral_0=(Token)match(input,TAGUTagUnity3dCom2011,FOLLOW_2); 

            			current = grammarAccess.getTagAccess().getUnityEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getTagAccess().getUnityEnumLiteralDeclaration());
            		

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
    // $ANTLR end "ruleTag"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000020082L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000820082L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x00000000001EA002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000020080L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000000001EA000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000000001EE000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000004200L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000230000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000210200L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000001002L});

}