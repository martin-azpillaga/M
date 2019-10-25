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
import m.services.TextGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTextParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_REAL", "RULE_WHITE_SPACE", "':'", "'{'", "','", "'}'", "'['", "']'", "'for'", "'all'", "'entity'", "'with'", "'if'", "'else'", "'('", "')'", "'.'", "'||'", "'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'~'", "'++'", "'--'", "'>'", "'<'", "'='", "'!='", "'>='", "'<='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='"
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

        public InternalTextParser(TokenStream input, TextGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Game";
       	}

       	@Override
       	protected TextGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleGame"
    // InternalText.g:65:1: entryRuleGame returns [EObject current=null] : iv_ruleGame= ruleGame EOF ;
    public final EObject entryRuleGame() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGame = null;


        try {
            // InternalText.g:65:45: (iv_ruleGame= ruleGame EOF )
            // InternalText.g:66:2: iv_ruleGame= ruleGame EOF
            {
             newCompositeNode(grammarAccess.getGameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGame=ruleGame();

            state._fsp--;

             current =iv_ruleGame; 
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
    // $ANTLR end "entryRuleGame"


    // $ANTLR start "ruleGame"
    // InternalText.g:72:1: ruleGame returns [EObject current=null] : ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )* ;
    public final EObject ruleGame() throws RecognitionException {
        EObject current = null;

        EObject lv_entities_0_0 = null;

        EObject lv_systems_1_0 = null;



        	enterRule();

        try {
            // InternalText.g:78:2: ( ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )* )
            // InternalText.g:79:2: ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )*
            {
            // InternalText.g:79:2: ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==7) ) {
                        int LA1_3 = input.LA(3);

                        if ( (LA1_3==RULE_ID) ) {
                            int LA1_5 = input.LA(4);

                            if ( (LA1_5==19||LA1_5==21||LA1_5==40||(LA1_5>=44 && LA1_5<=53)) ) {
                                alt1=2;
                            }
                            else if ( (LA1_5==RULE_ID||LA1_5==8) ) {
                                alt1=1;
                            }


                        }
                        else if ( (LA1_3==13||LA1_3==17) ) {
                            alt1=2;
                        }


                    }
                    else if ( (LA1_2==RULE_ID||LA1_2==8) ) {
                        alt1=1;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // InternalText.g:80:3: ( (lv_entities_0_0= ruleEntity ) )
            	    {
            	    // InternalText.g:80:3: ( (lv_entities_0_0= ruleEntity ) )
            	    // InternalText.g:81:4: (lv_entities_0_0= ruleEntity )
            	    {
            	    // InternalText.g:81:4: (lv_entities_0_0= ruleEntity )
            	    // InternalText.g:82:5: lv_entities_0_0= ruleEntity
            	    {

            	    					newCompositeNode(grammarAccess.getGameAccess().getEntitiesEntityParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_entities_0_0=ruleEntity();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getGameRule());
            	    					}
            	    					add(
            	    						current,
            	    						"entities",
            	    						lv_entities_0_0,
            	    						"m.Text.Entity");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalText.g:100:3: ( (lv_systems_1_0= ruleSystem ) )
            	    {
            	    // InternalText.g:100:3: ( (lv_systems_1_0= ruleSystem ) )
            	    // InternalText.g:101:4: (lv_systems_1_0= ruleSystem )
            	    {
            	    // InternalText.g:101:4: (lv_systems_1_0= ruleSystem )
            	    // InternalText.g:102:5: lv_systems_1_0= ruleSystem
            	    {

            	    					newCompositeNode(grammarAccess.getGameAccess().getSystemsSystemParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_systems_1_0=ruleSystem();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getGameRule());
            	    					}
            	    					add(
            	    						current,
            	    						"systems",
            	    						lv_systems_1_0,
            	    						"m.Text.System");
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
    // $ANTLR end "ruleGame"


    // $ANTLR start "entryRuleEntity"
    // InternalText.g:123:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalText.g:123:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalText.g:124:2: iv_ruleEntity= ruleEntity EOF
            {
             newCompositeNode(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalText.g:130:1: ruleEntity returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_base_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_components_4_0 = null;

        EObject lv_components_6_0 = null;

        EObject lv_entities_9_0 = null;



        	enterRule();

        try {
            // InternalText.g:136:2: ( ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? ) )
            // InternalText.g:137:2: ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? )
            {
            // InternalText.g:137:2: ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? )
            // InternalText.g:138:3: ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )?
            {
            // InternalText.g:138:3: ( (lv_name_0_0= RULE_ID ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalText.g:139:4: (lv_name_0_0= RULE_ID )
            	    {
            	    // InternalText.g:139:4: (lv_name_0_0= RULE_ID )
            	    // InternalText.g:140:5: lv_name_0_0= RULE_ID
            	    {
            	    lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            	    					newLeafNode(lv_name_0_0, grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getEntityRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"name",
            	    						lv_name_0_0,
            	    						"m.Text.ID");
            	    				

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

            // InternalText.g:156:3: (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==7) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalText.g:157:4: otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+
                    {
                    otherlv_1=(Token)match(input,7,FOLLOW_5); 

                    				newLeafNode(otherlv_1, grammarAccess.getEntityAccess().getColonKeyword_1_0());
                    			
                    // InternalText.g:161:4: ( (lv_base_2_0= RULE_ID ) )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==RULE_ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalText.g:162:5: (lv_base_2_0= RULE_ID )
                    	    {
                    	    // InternalText.g:162:5: (lv_base_2_0= RULE_ID )
                    	    // InternalText.g:163:6: lv_base_2_0= RULE_ID
                    	    {
                    	    lv_base_2_0=(Token)match(input,RULE_ID,FOLLOW_6); 

                    	    						newLeafNode(lv_base_2_0, grammarAccess.getEntityAccess().getBaseIDTerminalRuleCall_1_1_0());
                    	    					

                    	    						if (current==null) {
                    	    							current = createModelElement(grammarAccess.getEntityRule());
                    	    						}
                    	    						addWithLastConsumed(
                    	    							current,
                    	    							"base",
                    	    							lv_base_2_0,
                    	    							"m.Text.ID");
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);


                    }
                    break;

            }

            otherlv_3=(Token)match(input,8,FOLLOW_7); 

            			newLeafNode(otherlv_3, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalText.g:184:3: ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalText.g:185:4: ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )*
                    {
                    // InternalText.g:185:4: ( (lv_components_4_0= ruleComponent ) )
                    // InternalText.g:186:5: (lv_components_4_0= ruleComponent )
                    {
                    // InternalText.g:186:5: (lv_components_4_0= ruleComponent )
                    // InternalText.g:187:6: lv_components_4_0= ruleComponent
                    {

                    						newCompositeNode(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_8);
                    lv_components_4_0=ruleComponent();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    						}
                    						add(
                    							current,
                    							"components",
                    							lv_components_4_0,
                    							"m.Text.Component");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalText.g:204:4: (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalText.g:205:5: otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) )
                    	    {
                    	    otherlv_5=(Token)match(input,9,FOLLOW_5); 

                    	    					newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalText.g:209:5: ( (lv_components_6_0= ruleComponent ) )
                    	    // InternalText.g:210:6: (lv_components_6_0= ruleComponent )
                    	    {
                    	    // InternalText.g:210:6: (lv_components_6_0= ruleComponent )
                    	    // InternalText.g:211:7: lv_components_6_0= ruleComponent
                    	    {

                    	    							newCompositeNode(grammarAccess.getEntityAccess().getComponentsComponentParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_8);
                    	    lv_components_6_0=ruleComponent();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"components",
                    	    								lv_components_6_0,
                    	    								"m.Text.Component");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_7=(Token)match(input,10,FOLLOW_9); 

            			newLeafNode(otherlv_7, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_4());
            		
            // InternalText.g:234:3: (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==11) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalText.g:235:4: otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']'
                    {
                    otherlv_8=(Token)match(input,11,FOLLOW_10); 

                    				newLeafNode(otherlv_8, grammarAccess.getEntityAccess().getLeftSquareBracketKeyword_5_0());
                    			
                    // InternalText.g:239:4: ( (lv_entities_9_0= ruleEntity ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==RULE_ID) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalText.g:240:5: (lv_entities_9_0= ruleEntity )
                    	    {
                    	    // InternalText.g:240:5: (lv_entities_9_0= ruleEntity )
                    	    // InternalText.g:241:6: lv_entities_9_0= ruleEntity
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntityAccess().getEntitiesEntityParserRuleCall_5_1_0());
                    	    					
                    	    pushFollow(FOLLOW_10);
                    	    lv_entities_9_0=ruleEntity();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entities",
                    	    							lv_entities_9_0,
                    	    							"m.Text.Entity");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,12,FOLLOW_2); 

                    				newLeafNode(otherlv_10, grammarAccess.getEntityAccess().getRightSquareBracketKeyword_5_2());
                    			

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
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleComponent"
    // InternalText.g:267:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalText.g:267:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalText.g:268:2: iv_ruleComponent= ruleComponent EOF
            {
             newCompositeNode(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent=ruleComponent();

            state._fsp--;

             current =iv_ruleComponent; 
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
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalText.g:274:1: ruleComponent returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? ) ;
    public final EObject ruleComponent() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalText.g:280:2: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? ) )
            // InternalText.g:281:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? )
            {
            // InternalText.g:281:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? )
            // InternalText.g:282:3: ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )?
            {
            // InternalText.g:282:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalText.g:283:4: (lv_name_0_0= RULE_ID )
            {
            // InternalText.g:283:4: (lv_name_0_0= RULE_ID )
            // InternalText.g:284:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_name_0_0, grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.Text.ID");
            				

            }


            }

            // InternalText.g:300:3: ( (lv_value_1_0= ruleValue ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_REAL)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalText.g:301:4: (lv_value_1_0= ruleValue )
                    {
                    // InternalText.g:301:4: (lv_value_1_0= ruleValue )
                    // InternalText.g:302:5: lv_value_1_0= ruleValue
                    {

                    					newCompositeNode(grammarAccess.getComponentAccess().getValueValueParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_value_1_0=ruleValue();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getComponentRule());
                    					}
                    					set(
                    						current,
                    						"value",
                    						lv_value_1_0,
                    						"m.Text.Value");
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
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleValue"
    // InternalText.g:323:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalText.g:323:46: (iv_ruleValue= ruleValue EOF )
            // InternalText.g:324:2: iv_ruleValue= ruleValue EOF
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
    // InternalText.g:330:1: ruleValue returns [EObject current=null] : (this_Vector_0= ruleVector | this_Word_1= ruleWord ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_Vector_0 = null;

        EObject this_Word_1 = null;



        	enterRule();

        try {
            // InternalText.g:336:2: ( (this_Vector_0= ruleVector | this_Word_1= ruleWord ) )
            // InternalText.g:337:2: (this_Vector_0= ruleVector | this_Word_1= ruleWord )
            {
            // InternalText.g:337:2: (this_Vector_0= ruleVector | this_Word_1= ruleWord )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_REAL) ) {
                alt10=1;
            }
            else if ( (LA10_0==RULE_ID) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalText.g:338:3: this_Vector_0= ruleVector
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getVectorParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Vector_0=ruleVector();

                    state._fsp--;


                    			current = this_Vector_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalText.g:347:3: this_Word_1= ruleWord
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getWordParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Word_1=ruleWord();

                    state._fsp--;


                    			current = this_Word_1;
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


    // $ANTLR start "entryRuleVector"
    // InternalText.g:359:1: entryRuleVector returns [EObject current=null] : iv_ruleVector= ruleVector EOF ;
    public final EObject entryRuleVector() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVector = null;


        try {
            // InternalText.g:359:47: (iv_ruleVector= ruleVector EOF )
            // InternalText.g:360:2: iv_ruleVector= ruleVector EOF
            {
             newCompositeNode(grammarAccess.getVectorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVector=ruleVector();

            state._fsp--;

             current =iv_ruleVector; 
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
    // $ANTLR end "entryRuleVector"


    // $ANTLR start "ruleVector"
    // InternalText.g:366:1: ruleVector returns [EObject current=null] : ( (lv_numbers_0_0= RULE_REAL ) )+ ;
    public final EObject ruleVector() throws RecognitionException {
        EObject current = null;

        Token lv_numbers_0_0=null;


        	enterRule();

        try {
            // InternalText.g:372:2: ( ( (lv_numbers_0_0= RULE_REAL ) )+ )
            // InternalText.g:373:2: ( (lv_numbers_0_0= RULE_REAL ) )+
            {
            // InternalText.g:373:2: ( (lv_numbers_0_0= RULE_REAL ) )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_REAL) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalText.g:374:3: (lv_numbers_0_0= RULE_REAL )
            	    {
            	    // InternalText.g:374:3: (lv_numbers_0_0= RULE_REAL )
            	    // InternalText.g:375:4: lv_numbers_0_0= RULE_REAL
            	    {
            	    lv_numbers_0_0=(Token)match(input,RULE_REAL,FOLLOW_12); 

            	    				newLeafNode(lv_numbers_0_0, grammarAccess.getVectorAccess().getNumbersREALTerminalRuleCall_0());
            	    			

            	    				if (current==null) {
            	    					current = createModelElement(grammarAccess.getVectorRule());
            	    				}
            	    				addWithLastConsumed(
            	    					current,
            	    					"numbers",
            	    					lv_numbers_0_0,
            	    					"m.Text.REAL");
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
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
    // $ANTLR end "ruleVector"


    // $ANTLR start "entryRuleWord"
    // InternalText.g:394:1: entryRuleWord returns [EObject current=null] : iv_ruleWord= ruleWord EOF ;
    public final EObject entryRuleWord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWord = null;


        try {
            // InternalText.g:394:45: (iv_ruleWord= ruleWord EOF )
            // InternalText.g:395:2: iv_ruleWord= ruleWord EOF
            {
             newCompositeNode(grammarAccess.getWordRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWord=ruleWord();

            state._fsp--;

             current =iv_ruleWord; 
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
    // $ANTLR end "entryRuleWord"


    // $ANTLR start "ruleWord"
    // InternalText.g:401:1: ruleWord returns [EObject current=null] : ( (lv_values_0_0= RULE_ID ) )+ ;
    public final EObject ruleWord() throws RecognitionException {
        EObject current = null;

        Token lv_values_0_0=null;


        	enterRule();

        try {
            // InternalText.g:407:2: ( ( (lv_values_0_0= RULE_ID ) )+ )
            // InternalText.g:408:2: ( (lv_values_0_0= RULE_ID ) )+
            {
            // InternalText.g:408:2: ( (lv_values_0_0= RULE_ID ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalText.g:409:3: (lv_values_0_0= RULE_ID )
            	    {
            	    // InternalText.g:409:3: (lv_values_0_0= RULE_ID )
            	    // InternalText.g:410:4: lv_values_0_0= RULE_ID
            	    {
            	    lv_values_0_0=(Token)match(input,RULE_ID,FOLLOW_3); 

            	    				newLeafNode(lv_values_0_0, grammarAccess.getWordAccess().getValuesIDTerminalRuleCall_0());
            	    			

            	    				if (current==null) {
            	    					current = createModelElement(grammarAccess.getWordRule());
            	    				}
            	    				addWithLastConsumed(
            	    					current,
            	    					"values",
            	    					lv_values_0_0,
            	    					"m.Text.ID");
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
    // $ANTLR end "ruleWord"


    // $ANTLR start "entryRuleSystem"
    // InternalText.g:429:1: entryRuleSystem returns [EObject current=null] : iv_ruleSystem= ruleSystem EOF ;
    public final EObject entryRuleSystem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSystem = null;


        try {
            // InternalText.g:429:47: (iv_ruleSystem= ruleSystem EOF )
            // InternalText.g:430:2: iv_ruleSystem= ruleSystem EOF
            {
             newCompositeNode(grammarAccess.getSystemRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSystem=ruleSystem();

            state._fsp--;

             current =iv_ruleSystem; 
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
    // $ANTLR end "entryRuleSystem"


    // $ANTLR start "ruleSystem"
    // InternalText.g:436:1: ruleSystem returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ ) ;
    public final EObject ruleSystem() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_commands_2_0 = null;



        	enterRule();

        try {
            // InternalText.g:442:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ ) )
            // InternalText.g:443:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ )
            {
            // InternalText.g:443:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ )
            // InternalText.g:444:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+
            {
            // InternalText.g:444:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalText.g:445:4: (lv_name_0_0= RULE_ID )
            {
            // InternalText.g:445:4: (lv_name_0_0= RULE_ID )
            // InternalText.g:446:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_0_0, grammarAccess.getSystemAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSystemRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.Text.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,7,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getSystemAccess().getColonKeyword_1());
            		
            // InternalText.g:466:3: ( (lv_commands_2_0= ruleCommand ) )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    int LA13_2 = input.LA(2);

                    if ( (LA13_2==19||LA13_2==21||LA13_2==40||(LA13_2>=44 && LA13_2<=53)) ) {
                        alt13=1;
                    }


                }
                else if ( (LA13_0==13||LA13_0==17) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalText.g:467:4: (lv_commands_2_0= ruleCommand )
            	    {
            	    // InternalText.g:467:4: (lv_commands_2_0= ruleCommand )
            	    // InternalText.g:468:5: lv_commands_2_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getSystemAccess().getCommandsCommandParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_commands_2_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSystemRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_2_0,
            	    						"m.Text.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
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
    // $ANTLR end "ruleSystem"


    // $ANTLR start "entryRuleCommand"
    // InternalText.g:489:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalText.g:489:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalText.g:490:2: iv_ruleCommand= ruleCommand EOF
            {
             newCompositeNode(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCommand=ruleCommand();

            state._fsp--;

             current =iv_ruleCommand; 
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
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalText.g:496:1: ruleCommand returns [EObject current=null] : (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        EObject this_Loop_0 = null;

        EObject this_Branch_1 = null;

        EObject this_Assignment_2 = null;

        EObject this_Call_3 = null;



        	enterRule();

        try {
            // InternalText.g:502:2: ( (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall ) )
            // InternalText.g:503:2: (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall )
            {
            // InternalText.g:503:2: (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall )
            int alt14=4;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt14=1;
                }
                break;
            case 17:
                {
                alt14=2;
                }
                break;
            case RULE_ID:
                {
                int LA14_3 = input.LA(2);

                if ( (LA14_3==21||LA14_3==40||(LA14_3>=44 && LA14_3<=53)) ) {
                    alt14=3;
                }
                else if ( (LA14_3==19) ) {
                    alt14=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalText.g:504:3: this_Loop_0= ruleLoop
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getLoopParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Loop_0=ruleLoop();

                    state._fsp--;


                    			current = this_Loop_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalText.g:513:3: this_Branch_1= ruleBranch
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getBranchParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Branch_1=ruleBranch();

                    state._fsp--;


                    			current = this_Branch_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalText.g:522:3: this_Assignment_2= ruleAssignment
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getAssignmentParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Assignment_2=ruleAssignment();

                    state._fsp--;


                    			current = this_Assignment_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalText.g:531:3: this_Call_3= ruleCall
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getCallParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Call_3=ruleCall();

                    state._fsp--;


                    			current = this_Call_3;
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
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleLoop"
    // InternalText.g:543:1: entryRuleLoop returns [EObject current=null] : iv_ruleLoop= ruleLoop EOF ;
    public final EObject entryRuleLoop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoop = null;


        try {
            // InternalText.g:543:45: (iv_ruleLoop= ruleLoop EOF )
            // InternalText.g:544:2: iv_ruleLoop= ruleLoop EOF
            {
             newCompositeNode(grammarAccess.getLoopRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLoop=ruleLoop();

            state._fsp--;

             current =iv_ruleLoop; 
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
    // $ANTLR end "entryRuleLoop"


    // $ANTLR start "ruleLoop"
    // InternalText.g:550:1: ruleLoop returns [EObject current=null] : (otherlv_0= 'for' otherlv_1= 'all' otherlv_2= 'entity' ( (lv_entity_3_0= RULE_ID ) ) (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )? otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' ) ;
    public final EObject ruleLoop() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_entity_3_0=null;
        Token otherlv_4=null;
        Token lv_constraints_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_commands_7_0 = null;



        	enterRule();

        try {
            // InternalText.g:556:2: ( (otherlv_0= 'for' otherlv_1= 'all' otherlv_2= 'entity' ( (lv_entity_3_0= RULE_ID ) ) (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )? otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' ) )
            // InternalText.g:557:2: (otherlv_0= 'for' otherlv_1= 'all' otherlv_2= 'entity' ( (lv_entity_3_0= RULE_ID ) ) (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )? otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )
            {
            // InternalText.g:557:2: (otherlv_0= 'for' otherlv_1= 'all' otherlv_2= 'entity' ( (lv_entity_3_0= RULE_ID ) ) (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )? otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )
            // InternalText.g:558:3: otherlv_0= 'for' otherlv_1= 'all' otherlv_2= 'entity' ( (lv_entity_3_0= RULE_ID ) ) (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )? otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getLoopAccess().getForKeyword_0());
            		
            otherlv_1=(Token)match(input,14,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getLoopAccess().getAllKeyword_1());
            		
            otherlv_2=(Token)match(input,15,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getLoopAccess().getEntityKeyword_2());
            		
            // InternalText.g:570:3: ( (lv_entity_3_0= RULE_ID ) )
            // InternalText.g:571:4: (lv_entity_3_0= RULE_ID )
            {
            // InternalText.g:571:4: (lv_entity_3_0= RULE_ID )
            // InternalText.g:572:5: lv_entity_3_0= RULE_ID
            {
            lv_entity_3_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_entity_3_0, grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoopRule());
            					}
            					setWithLastConsumed(
            						current,
            						"entity",
            						lv_entity_3_0,
            						"m.Text.ID");
            				

            }


            }

            // InternalText.g:588:3: (otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+ )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==16) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalText.g:589:4: otherlv_4= 'with' ( (lv_constraints_5_0= RULE_ID ) )+
                    {
                    otherlv_4=(Token)match(input,16,FOLLOW_5); 

                    				newLeafNode(otherlv_4, grammarAccess.getLoopAccess().getWithKeyword_4_0());
                    			
                    // InternalText.g:593:4: ( (lv_constraints_5_0= RULE_ID ) )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==RULE_ID) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalText.g:594:5: (lv_constraints_5_0= RULE_ID )
                    	    {
                    	    // InternalText.g:594:5: (lv_constraints_5_0= RULE_ID )
                    	    // InternalText.g:595:6: lv_constraints_5_0= RULE_ID
                    	    {
                    	    lv_constraints_5_0=(Token)match(input,RULE_ID,FOLLOW_6); 

                    	    						newLeafNode(lv_constraints_5_0, grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_4_1_0());
                    	    					

                    	    						if (current==null) {
                    	    							current = createModelElement(grammarAccess.getLoopRule());
                    	    						}
                    	    						addWithLastConsumed(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_5_0,
                    	    							"m.Text.ID");
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,8,FOLLOW_14); 

            			newLeafNode(otherlv_6, grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalText.g:616:3: ( (lv_commands_7_0= ruleCommand ) )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_ID||LA17_0==13||LA17_0==17) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalText.g:617:4: (lv_commands_7_0= ruleCommand )
            	    {
            	    // InternalText.g:617:4: (lv_commands_7_0= ruleCommand )
            	    // InternalText.g:618:5: lv_commands_7_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_commands_7_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getLoopRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_7_0,
            	    						"m.Text.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            otherlv_8=(Token)match(input,10,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_7());
            		

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
    // $ANTLR end "ruleLoop"


    // $ANTLR start "entryRuleBranch"
    // InternalText.g:643:1: entryRuleBranch returns [EObject current=null] : iv_ruleBranch= ruleBranch EOF ;
    public final EObject entryRuleBranch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBranch = null;


        try {
            // InternalText.g:643:47: (iv_ruleBranch= ruleBranch EOF )
            // InternalText.g:644:2: iv_ruleBranch= ruleBranch EOF
            {
             newCompositeNode(grammarAccess.getBranchRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBranch=ruleBranch();

            state._fsp--;

             current =iv_ruleBranch; 
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
    // $ANTLR end "entryRuleBranch"


    // $ANTLR start "ruleBranch"
    // InternalText.g:650:1: ruleBranch returns [EObject current=null] : (otherlv_0= 'if' ( (lv_if_1_0= ruleCondition ) ) (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? ) ;
    public final EObject ruleBranch() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_if_1_0 = null;

        EObject lv_elseIfs_4_0 = null;

        EObject lv_commands_7_0 = null;



        	enterRule();

        try {
            // InternalText.g:656:2: ( (otherlv_0= 'if' ( (lv_if_1_0= ruleCondition ) ) (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? ) )
            // InternalText.g:657:2: (otherlv_0= 'if' ( (lv_if_1_0= ruleCondition ) ) (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? )
            {
            // InternalText.g:657:2: (otherlv_0= 'if' ( (lv_if_1_0= ruleCondition ) ) (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? )
            // InternalText.g:658:3: otherlv_0= 'if' ( (lv_if_1_0= ruleCondition ) ) (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )?
            {
            otherlv_0=(Token)match(input,17,FOLLOW_20); 

            			newLeafNode(otherlv_0, grammarAccess.getBranchAccess().getIfKeyword_0());
            		
            // InternalText.g:662:3: ( (lv_if_1_0= ruleCondition ) )
            // InternalText.g:663:4: (lv_if_1_0= ruleCondition )
            {
            // InternalText.g:663:4: (lv_if_1_0= ruleCondition )
            // InternalText.g:664:5: lv_if_1_0= ruleCondition
            {

            					newCompositeNode(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_21);
            lv_if_1_0=ruleCondition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBranchRule());
            					}
            					set(
            						current,
            						"if",
            						lv_if_1_0,
            						"m.Text.Condition");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalText.g:681:3: (otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==18) ) {
                    int LA18_1 = input.LA(2);

                    if ( (LA18_1==17) ) {
                        alt18=1;
                    }


                }


                switch (alt18) {
            	case 1 :
            	    // InternalText.g:682:4: otherlv_2= 'else' otherlv_3= 'if' ( (lv_elseIfs_4_0= ruleCondition ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_22); 

            	    				newLeafNode(otherlv_2, grammarAccess.getBranchAccess().getElseKeyword_2_0());
            	    			
            	    otherlv_3=(Token)match(input,17,FOLLOW_20); 

            	    				newLeafNode(otherlv_3, grammarAccess.getBranchAccess().getIfKeyword_2_1());
            	    			
            	    // InternalText.g:690:4: ( (lv_elseIfs_4_0= ruleCondition ) )
            	    // InternalText.g:691:5: (lv_elseIfs_4_0= ruleCondition )
            	    {
            	    // InternalText.g:691:5: (lv_elseIfs_4_0= ruleCondition )
            	    // InternalText.g:692:6: lv_elseIfs_4_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_21);
            	    lv_elseIfs_4_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getBranchRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseIfs",
            	    							lv_elseIfs_4_0,
            	    							"m.Text.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // InternalText.g:710:3: (otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==18) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalText.g:711:4: otherlv_5= 'else' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_23); 

                    				newLeafNode(otherlv_5, grammarAccess.getBranchAccess().getElseKeyword_3_0());
                    			
                    otherlv_6=(Token)match(input,8,FOLLOW_14); 

                    				newLeafNode(otherlv_6, grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalText.g:719:4: ( (lv_commands_7_0= ruleCommand ) )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==RULE_ID||LA19_0==13||LA19_0==17) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalText.g:720:5: (lv_commands_7_0= ruleCommand )
                    	    {
                    	    // InternalText.g:720:5: (lv_commands_7_0= ruleCommand )
                    	    // InternalText.g:721:6: lv_commands_7_0= ruleCommand
                    	    {

                    	    						newCompositeNode(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_19);
                    	    lv_commands_7_0=ruleCommand();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getBranchRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"commands",
                    	    							lv_commands_7_0,
                    	    							"m.Text.Command");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);

                    otherlv_8=(Token)match(input,10,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_3_3());
                    			

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
    // $ANTLR end "ruleBranch"


    // $ANTLR start "entryRuleCondition"
    // InternalText.g:747:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalText.g:747:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalText.g:748:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
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
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalText.g:754:1: ruleCondition returns [EObject current=null] : ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_condition_0_0 = null;

        EObject lv_commands_2_0 = null;



        	enterRule();

        try {
            // InternalText.g:760:2: ( ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' ) )
            // InternalText.g:761:2: ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' )
            {
            // InternalText.g:761:2: ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' )
            // InternalText.g:762:3: ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}'
            {
            // InternalText.g:762:3: ( (lv_condition_0_0= ruleExpression ) )
            // InternalText.g:763:4: (lv_condition_0_0= ruleExpression )
            {
            // InternalText.g:763:4: (lv_condition_0_0= ruleExpression )
            // InternalText.g:764:5: lv_condition_0_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getConditionAccess().getConditionExpressionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_condition_0_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionRule());
            					}
            					set(
            						current,
            						"condition",
            						lv_condition_0_0,
            						"m.Text.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,8,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getConditionAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalText.g:785:3: ( (lv_commands_2_0= ruleCommand ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID||LA21_0==13||LA21_0==17) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalText.g:786:4: (lv_commands_2_0= ruleCommand )
            	    {
            	    // InternalText.g:786:4: (lv_commands_2_0= ruleCommand )
            	    // InternalText.g:787:5: lv_commands_2_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getConditionAccess().getCommandsCommandParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_commands_2_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConditionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_2_0,
            	    						"m.Text.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            otherlv_3=(Token)match(input,10,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getConditionAccess().getRightCurlyBracketKeyword_3());
            		

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
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleAssignment"
    // InternalText.g:812:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalText.g:812:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalText.g:813:2: iv_ruleAssignment= ruleAssignment EOF
            {
             newCompositeNode(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
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
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalText.g:819:1: ruleAssignment returns [EObject current=null] : ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        EObject lv_access_0_0 = null;

        Enumerator lv_type_1_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalText.g:825:2: ( ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) ) )
            // InternalText.g:826:2: ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) )
            {
            // InternalText.g:826:2: ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) )
            // InternalText.g:827:3: ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) )
            {
            // InternalText.g:827:3: ( (lv_access_0_0= ruleAccess ) )
            // InternalText.g:828:4: (lv_access_0_0= ruleAccess )
            {
            // InternalText.g:828:4: (lv_access_0_0= ruleAccess )
            // InternalText.g:829:5: lv_access_0_0= ruleAccess
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getAccessAccessParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_24);
            lv_access_0_0=ruleAccess();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"access",
            						lv_access_0_0,
            						"m.Text.Access");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalText.g:846:3: ( (lv_type_1_0= ruleAssignmentType ) )
            // InternalText.g:847:4: (lv_type_1_0= ruleAssignmentType )
            {
            // InternalText.g:847:4: (lv_type_1_0= ruleAssignmentType )
            // InternalText.g:848:5: lv_type_1_0= ruleAssignmentType
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getTypeAssignmentTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_20);
            lv_type_1_0=ruleAssignmentType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"m.Text.AssignmentType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalText.g:865:3: ( (lv_expression_2_0= ruleExpression ) )
            // InternalText.g:866:4: (lv_expression_2_0= ruleExpression )
            {
            // InternalText.g:866:4: (lv_expression_2_0= ruleExpression )
            // InternalText.g:867:5: lv_expression_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getExpressionExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_expression_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_2_0,
            						"m.Text.Expression");
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
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleCall"
    // InternalText.g:888:1: entryRuleCall returns [EObject current=null] : iv_ruleCall= ruleCall EOF ;
    public final EObject entryRuleCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCall = null;


        try {
            // InternalText.g:888:45: (iv_ruleCall= ruleCall EOF )
            // InternalText.g:889:2: iv_ruleCall= ruleCall EOF
            {
             newCompositeNode(grammarAccess.getCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCall=ruleCall();

            state._fsp--;

             current =iv_ruleCall; 
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
    // $ANTLR end "entryRuleCall"


    // $ANTLR start "ruleCall"
    // InternalText.g:895:1: ruleCall returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' ) ;
    public final EObject ruleCall() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_parameters_2_0 = null;

        EObject lv_parameters_4_0 = null;



        	enterRule();

        try {
            // InternalText.g:901:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' ) )
            // InternalText.g:902:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' )
            {
            // InternalText.g:902:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' )
            // InternalText.g:903:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')'
            {
            // InternalText.g:903:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalText.g:904:4: (lv_name_0_0= RULE_ID )
            {
            // InternalText.g:904:4: (lv_name_0_0= RULE_ID )
            // InternalText.g:905:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            					newLeafNode(lv_name_0_0, grammarAccess.getCallAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCallRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"m.Text.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_1, grammarAccess.getCallAccess().getLeftParenthesisKeyword_1());
            		
            // InternalText.g:925:3: ( (lv_parameters_2_0= ruleExpression ) )
            // InternalText.g:926:4: (lv_parameters_2_0= ruleExpression )
            {
            // InternalText.g:926:4: (lv_parameters_2_0= ruleExpression )
            // InternalText.g:927:5: lv_parameters_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_26);
            lv_parameters_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCallRule());
            					}
            					add(
            						current,
            						"parameters",
            						lv_parameters_2_0,
            						"m.Text.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalText.g:944:3: (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==9) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalText.g:945:4: otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) )
            	    {
            	    otherlv_3=(Token)match(input,9,FOLLOW_20); 

            	    				newLeafNode(otherlv_3, grammarAccess.getCallAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalText.g:949:4: ( (lv_parameters_4_0= ruleExpression ) )
            	    // InternalText.g:950:5: (lv_parameters_4_0= ruleExpression )
            	    {
            	    // InternalText.g:950:5: (lv_parameters_4_0= ruleExpression )
            	    // InternalText.g:951:6: lv_parameters_4_0= ruleExpression
            	    {

            	    						newCompositeNode(grammarAccess.getCallAccess().getParametersExpressionParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_26);
            	    lv_parameters_4_0=ruleExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getCallRule());
            	    						}
            	    						add(
            	    							current,
            	    							"parameters",
            	    							lv_parameters_4_0,
            	    							"m.Text.Expression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_5=(Token)match(input,20,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getCallAccess().getRightParenthesisKeyword_4());
            		

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
    // $ANTLR end "ruleCall"


    // $ANTLR start "entryRuleAccess"
    // InternalText.g:977:1: entryRuleAccess returns [EObject current=null] : iv_ruleAccess= ruleAccess EOF ;
    public final EObject entryRuleAccess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAccess = null;


        try {
            // InternalText.g:977:47: (iv_ruleAccess= ruleAccess EOF )
            // InternalText.g:978:2: iv_ruleAccess= ruleAccess EOF
            {
             newCompositeNode(grammarAccess.getAccessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAccess=ruleAccess();

            state._fsp--;

             current =iv_ruleAccess; 
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
    // $ANTLR end "entryRuleAccess"


    // $ANTLR start "ruleAccess"
    // InternalText.g:984:1: ruleAccess returns [EObject current=null] : ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) ;
    public final EObject ruleAccess() throws RecognitionException {
        EObject current = null;

        Token lv_names_0_0=null;
        Token otherlv_1=null;
        Token lv_names_2_0=null;


        	enterRule();

        try {
            // InternalText.g:990:2: ( ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) )
            // InternalText.g:991:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            {
            // InternalText.g:991:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            // InternalText.g:992:3: ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            {
            // InternalText.g:992:3: ( (lv_names_0_0= RULE_ID ) )
            // InternalText.g:993:4: (lv_names_0_0= RULE_ID )
            {
            // InternalText.g:993:4: (lv_names_0_0= RULE_ID )
            // InternalText.g:994:5: lv_names_0_0= RULE_ID
            {
            lv_names_0_0=(Token)match(input,RULE_ID,FOLLOW_27); 

            					newLeafNode(lv_names_0_0, grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAccessRule());
            					}
            					addWithLastConsumed(
            						current,
            						"names",
            						lv_names_0_0,
            						"m.Text.ID");
            				

            }


            }

            // InternalText.g:1010:3: (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==21) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalText.g:1011:4: otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) )
            	    {
            	    otherlv_1=(Token)match(input,21,FOLLOW_5); 

            	    				newLeafNode(otherlv_1, grammarAccess.getAccessAccess().getFullStopKeyword_1_0());
            	    			
            	    // InternalText.g:1015:4: ( (lv_names_2_0= RULE_ID ) )
            	    // InternalText.g:1016:5: (lv_names_2_0= RULE_ID )
            	    {
            	    // InternalText.g:1016:5: (lv_names_2_0= RULE_ID )
            	    // InternalText.g:1017:6: lv_names_2_0= RULE_ID
            	    {
            	    lv_names_2_0=(Token)match(input,RULE_ID,FOLLOW_27); 

            	    						newLeafNode(lv_names_2_0, grammarAccess.getAccessAccess().getNamesIDTerminalRuleCall_1_1_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getAccessRule());
            	    						}
            	    						addWithLastConsumed(
            	    							current,
            	    							"names",
            	    							lv_names_2_0,
            	    							"m.Text.ID");
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
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
    // $ANTLR end "ruleAccess"


    // $ANTLR start "entryRuleExpression"
    // InternalText.g:1038:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalText.g:1038:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalText.g:1039:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalText.g:1045:1: ruleExpression returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalText.g:1051:2: ( (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // InternalText.g:1052:2: (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // InternalText.g:1052:2: (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* )
            // InternalText.g:1053:3: this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )*
            {

            			newCompositeNode(grammarAccess.getExpressionAccess().getAndParserRuleCall_0());
            		
            pushFollow(FOLLOW_28);
            this_And_0=ruleAnd();

            state._fsp--;


            			current = this_And_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1061:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==22) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalText.g:1062:4: () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // InternalText.g:1062:4: ()
            	    // InternalText.g:1063:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getExpressionAccess().getOrLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,22,FOLLOW_20); 

            	    				newLeafNode(otherlv_2, grammarAccess.getExpressionAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalText.g:1073:4: ( (lv_right_3_0= ruleAnd ) )
            	    // InternalText.g:1074:5: (lv_right_3_0= ruleAnd )
            	    {
            	    // InternalText.g:1074:5: (lv_right_3_0= ruleAnd )
            	    // InternalText.g:1075:6: lv_right_3_0= ruleAnd
            	    {

            	    						newCompositeNode(grammarAccess.getExpressionAccess().getRightAndParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_28);
            	    lv_right_3_0=ruleAnd();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"m.Text.And");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAnd"
    // InternalText.g:1097:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // InternalText.g:1097:44: (iv_ruleAnd= ruleAnd EOF )
            // InternalText.g:1098:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
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
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // InternalText.g:1104:1: ruleAnd returns [EObject current=null] : (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PrimaryB_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalText.g:1110:2: ( (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* ) )
            // InternalText.g:1111:2: (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* )
            {
            // InternalText.g:1111:2: (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* )
            // InternalText.g:1112:3: this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndAccess().getPrimaryBParserRuleCall_0());
            		
            pushFollow(FOLLOW_29);
            this_PrimaryB_0=rulePrimaryB();

            state._fsp--;


            			current = this_PrimaryB_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1120:3: ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==23) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalText.g:1121:4: () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) )
            	    {
            	    // InternalText.g:1121:4: ()
            	    // InternalText.g:1122:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,23,FOLLOW_20); 

            	    				newLeafNode(otherlv_2, grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalText.g:1132:4: ( (lv_right_3_0= rulePrimaryB ) )
            	    // InternalText.g:1133:5: (lv_right_3_0= rulePrimaryB )
            	    {
            	    // InternalText.g:1133:5: (lv_right_3_0= rulePrimaryB )
            	    // InternalText.g:1134:6: lv_right_3_0= rulePrimaryB
            	    {

            	    						newCompositeNode(grammarAccess.getAndAccess().getRightPrimaryBParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_right_3_0=rulePrimaryB();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"m.Text.PrimaryB");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRulePrimaryB"
    // InternalText.g:1156:1: entryRulePrimaryB returns [EObject current=null] : iv_rulePrimaryB= rulePrimaryB EOF ;
    public final EObject entryRulePrimaryB() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryB = null;


        try {
            // InternalText.g:1156:49: (iv_rulePrimaryB= rulePrimaryB EOF )
            // InternalText.g:1157:2: iv_rulePrimaryB= rulePrimaryB EOF
            {
             newCompositeNode(grammarAccess.getPrimaryBRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimaryB=rulePrimaryB();

            state._fsp--;

             current =iv_rulePrimaryB; 
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
    // $ANTLR end "entryRulePrimaryB"


    // $ANTLR start "rulePrimaryB"
    // InternalText.g:1163:1: rulePrimaryB returns [EObject current=null] : (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) ) ;
    public final EObject rulePrimaryB() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AtomicB_0 = null;

        EObject lv_expression_3_0 = null;



        	enterRule();

        try {
            // InternalText.g:1169:2: ( (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) ) )
            // InternalText.g:1170:2: (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) )
            {
            // InternalText.g:1170:2: (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID||LA26_0==19||LA26_0==35) ) {
                alt26=1;
            }
            else if ( (LA26_0==24) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // InternalText.g:1171:3: this_AtomicB_0= ruleAtomicB
                    {

                    			newCompositeNode(grammarAccess.getPrimaryBAccess().getAtomicBParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_AtomicB_0=ruleAtomicB();

                    state._fsp--;


                    			current = this_AtomicB_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalText.g:1180:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) )
                    {
                    // InternalText.g:1180:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) )
                    // InternalText.g:1181:4: () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) )
                    {
                    // InternalText.g:1181:4: ()
                    // InternalText.g:1182:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryBAccess().getNotAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_2=(Token)match(input,24,FOLLOW_30); 

                    				newLeafNode(otherlv_2, grammarAccess.getPrimaryBAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalText.g:1192:4: ( (lv_expression_3_0= ruleAtomicB ) )
                    // InternalText.g:1193:5: (lv_expression_3_0= ruleAtomicB )
                    {
                    // InternalText.g:1193:5: (lv_expression_3_0= ruleAtomicB )
                    // InternalText.g:1194:6: lv_expression_3_0= ruleAtomicB
                    {

                    						newCompositeNode(grammarAccess.getPrimaryBAccess().getExpressionAtomicBParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_expression_3_0=ruleAtomicB();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPrimaryBRule());
                    						}
                    						set(
                    							current,
                    							"expression",
                    							lv_expression_3_0,
                    							"m.Text.AtomicB");
                    						afterParserOrEnumRuleCall();
                    					

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
    // $ANTLR end "rulePrimaryB"


    // $ANTLR start "entryRuleAtomicB"
    // InternalText.g:1216:1: entryRuleAtomicB returns [EObject current=null] : iv_ruleAtomicB= ruleAtomicB EOF ;
    public final EObject entryRuleAtomicB() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicB = null;


        try {
            // InternalText.g:1216:48: (iv_ruleAtomicB= ruleAtomicB EOF )
            // InternalText.g:1217:2: iv_ruleAtomicB= ruleAtomicB EOF
            {
             newCompositeNode(grammarAccess.getAtomicBRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomicB=ruleAtomicB();

            state._fsp--;

             current =iv_ruleAtomicB; 
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
    // $ANTLR end "entryRuleAtomicB"


    // $ANTLR start "ruleAtomicB"
    // InternalText.g:1223:1: ruleAtomicB returns [EObject current=null] : (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) ;
    public final EObject ruleAtomicB() throws RecognitionException {
        EObject current = null;

        EObject this_Addition_0 = null;

        Enumerator lv_type_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalText.g:1229:2: ( (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) )
            // InternalText.g:1230:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            {
            // InternalText.g:1230:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            // InternalText.g:1231:3: this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            {

            			newCompositeNode(grammarAccess.getAtomicBAccess().getAdditionParserRuleCall_0());
            		
            pushFollow(FOLLOW_31);
            this_Addition_0=ruleAddition();

            state._fsp--;


            			current = this_Addition_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1239:3: ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=38 && LA27_0<=43)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalText.g:1240:4: () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) )
                    {
                    // InternalText.g:1240:4: ()
                    // InternalText.g:1241:5: 
                    {

                    					current = forceCreateModelElementAndSet(
                    						grammarAccess.getAtomicBAccess().getComparisonLeftAction_1_0(),
                    						current);
                    				

                    }

                    // InternalText.g:1247:4: ( (lv_type_2_0= ruleRelationType ) )
                    // InternalText.g:1248:5: (lv_type_2_0= ruleRelationType )
                    {
                    // InternalText.g:1248:5: (lv_type_2_0= ruleRelationType )
                    // InternalText.g:1249:6: lv_type_2_0= ruleRelationType
                    {

                    						newCompositeNode(grammarAccess.getAtomicBAccess().getTypeRelationTypeEnumRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_type_2_0=ruleRelationType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAtomicBRule());
                    						}
                    						set(
                    							current,
                    							"type",
                    							lv_type_2_0,
                    							"m.Text.RelationType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalText.g:1266:4: ( (lv_right_3_0= ruleAddition ) )
                    // InternalText.g:1267:5: (lv_right_3_0= ruleAddition )
                    {
                    // InternalText.g:1267:5: (lv_right_3_0= ruleAddition )
                    // InternalText.g:1268:6: lv_right_3_0= ruleAddition
                    {

                    						newCompositeNode(grammarAccess.getAtomicBAccess().getRightAdditionParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=ruleAddition();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAtomicBRule());
                    						}
                    						set(
                    							current,
                    							"right",
                    							lv_right_3_0,
                    							"m.Text.Addition");
                    						afterParserOrEnumRuleCall();
                    					

                    }


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
    // $ANTLR end "ruleAtomicB"


    // $ANTLR start "entryRuleAddition"
    // InternalText.g:1290:1: entryRuleAddition returns [EObject current=null] : iv_ruleAddition= ruleAddition EOF ;
    public final EObject entryRuleAddition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddition = null;


        try {
            // InternalText.g:1290:49: (iv_ruleAddition= ruleAddition EOF )
            // InternalText.g:1291:2: iv_ruleAddition= ruleAddition EOF
            {
             newCompositeNode(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddition=ruleAddition();

            state._fsp--;

             current =iv_ruleAddition; 
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
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalText.g:1297:1: ruleAddition returns [EObject current=null] : (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) ;
    public final EObject ruleAddition() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Multiplication_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalText.g:1303:2: ( (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) )
            // InternalText.g:1304:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            {
            // InternalText.g:1304:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            // InternalText.g:1305:3: this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
            		
            pushFollow(FOLLOW_32);
            this_Multiplication_0=ruleMultiplication();

            state._fsp--;


            			current = this_Multiplication_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1313:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=25 && LA29_0<=26)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalText.g:1314:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) )
            	    {
            	    // InternalText.g:1314:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt28=2;
            	    int LA28_0 = input.LA(1);

            	    if ( (LA28_0==25) ) {
            	        alt28=1;
            	    }
            	    else if ( (LA28_0==26) ) {
            	        alt28=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 28, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt28) {
            	        case 1 :
            	            // InternalText.g:1315:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalText.g:1315:5: ( () otherlv_2= '+' )
            	            // InternalText.g:1316:6: () otherlv_2= '+'
            	            {
            	            // InternalText.g:1316:6: ()
            	            // InternalText.g:1317:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,25,FOLLOW_30); 

            	            						newLeafNode(otherlv_2, grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalText.g:1329:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalText.g:1329:5: ( () otherlv_4= '-' )
            	            // InternalText.g:1330:6: () otherlv_4= '-'
            	            {
            	            // InternalText.g:1330:6: ()
            	            // InternalText.g:1331:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,26,FOLLOW_30); 

            	            						newLeafNode(otherlv_4, grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalText.g:1343:4: ( (lv_right_5_0= ruleMultiplication ) )
            	    // InternalText.g:1344:5: (lv_right_5_0= ruleMultiplication )
            	    {
            	    // InternalText.g:1344:5: (lv_right_5_0= ruleMultiplication )
            	    // InternalText.g:1345:6: lv_right_5_0= ruleMultiplication
            	    {

            	    						newCompositeNode(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_right_5_0=ruleMultiplication();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_5_0,
            	    							"m.Text.Multiplication");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
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
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalText.g:1367:1: entryRuleMultiplication returns [EObject current=null] : iv_ruleMultiplication= ruleMultiplication EOF ;
    public final EObject entryRuleMultiplication() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplication = null;


        try {
            // InternalText.g:1367:55: (iv_ruleMultiplication= ruleMultiplication EOF )
            // InternalText.g:1368:2: iv_ruleMultiplication= ruleMultiplication EOF
            {
             newCompositeNode(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplication=ruleMultiplication();

            state._fsp--;

             current =iv_ruleMultiplication; 
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
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalText.g:1374:1: ruleMultiplication returns [EObject current=null] : (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) ;
    public final EObject ruleMultiplication() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_Bitwise_0 = null;

        EObject lv_right_7_0 = null;



        	enterRule();

        try {
            // InternalText.g:1380:2: ( (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) )
            // InternalText.g:1381:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            {
            // InternalText.g:1381:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            // InternalText.g:1382:3: this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAccess().getBitwiseParserRuleCall_0());
            		
            pushFollow(FOLLOW_33);
            this_Bitwise_0=ruleBitwise();

            state._fsp--;


            			current = this_Bitwise_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1390:3: ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=27 && LA31_0<=29)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalText.g:1391:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) )
            	    {
            	    // InternalText.g:1391:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) )
            	    int alt30=3;
            	    switch ( input.LA(1) ) {
            	    case 27:
            	        {
            	        alt30=1;
            	        }
            	        break;
            	    case 28:
            	        {
            	        alt30=2;
            	        }
            	        break;
            	    case 29:
            	        {
            	        alt30=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 30, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt30) {
            	        case 1 :
            	            // InternalText.g:1392:5: ( () otherlv_2= '*' )
            	            {
            	            // InternalText.g:1392:5: ( () otherlv_2= '*' )
            	            // InternalText.g:1393:6: () otherlv_2= '*'
            	            {
            	            // InternalText.g:1393:6: ()
            	            // InternalText.g:1394:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,27,FOLLOW_30); 

            	            						newLeafNode(otherlv_2, grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalText.g:1406:5: ( () otherlv_4= '/' )
            	            {
            	            // InternalText.g:1406:5: ( () otherlv_4= '/' )
            	            // InternalText.g:1407:6: () otherlv_4= '/'
            	            {
            	            // InternalText.g:1407:6: ()
            	            // InternalText.g:1408:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,28,FOLLOW_30); 

            	            						newLeafNode(otherlv_4, grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalText.g:1420:5: ( () otherlv_6= '%' )
            	            {
            	            // InternalText.g:1420:5: ( () otherlv_6= '%' )
            	            // InternalText.g:1421:6: () otherlv_6= '%'
            	            {
            	            // InternalText.g:1421:6: ()
            	            // InternalText.g:1422:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_6=(Token)match(input,29,FOLLOW_30); 

            	            						newLeafNode(otherlv_6, grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalText.g:1434:4: ( (lv_right_7_0= ruleBitwise ) )
            	    // InternalText.g:1435:5: (lv_right_7_0= ruleBitwise )
            	    {
            	    // InternalText.g:1435:5: (lv_right_7_0= ruleBitwise )
            	    // InternalText.g:1436:6: lv_right_7_0= ruleBitwise
            	    {

            	    						newCompositeNode(grammarAccess.getMultiplicationAccess().getRightBitwiseParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_33);
            	    lv_right_7_0=ruleBitwise();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiplicationRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_7_0,
            	    							"m.Text.Bitwise");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
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
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRuleBitwise"
    // InternalText.g:1458:1: entryRuleBitwise returns [EObject current=null] : iv_ruleBitwise= ruleBitwise EOF ;
    public final EObject entryRuleBitwise() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBitwise = null;


        try {
            // InternalText.g:1458:48: (iv_ruleBitwise= ruleBitwise EOF )
            // InternalText.g:1459:2: iv_ruleBitwise= ruleBitwise EOF
            {
             newCompositeNode(grammarAccess.getBitwiseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBitwise=ruleBitwise();

            state._fsp--;

             current =iv_ruleBitwise; 
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
    // $ANTLR end "entryRuleBitwise"


    // $ANTLR start "ruleBitwise"
    // InternalText.g:1465:1: ruleBitwise returns [EObject current=null] : (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) ;
    public final EObject ruleBitwise() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_op_2_4=null;
        Token lv_op_2_5=null;
        EObject this_Unary_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalText.g:1471:2: ( (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) )
            // InternalText.g:1472:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            {
            // InternalText.g:1472:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            // InternalText.g:1473:3: this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            {

            			newCompositeNode(grammarAccess.getBitwiseAccess().getUnaryParserRuleCall_0());
            		
            pushFollow(FOLLOW_34);
            this_Unary_0=ruleUnary();

            state._fsp--;


            			current = this_Unary_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalText.g:1481:3: ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( ((LA33_0>=30 && LA33_0<=34)) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalText.g:1482:4: () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) )
            	    {
            	    // InternalText.g:1482:4: ()
            	    // InternalText.g:1483:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalText.g:1489:4: ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) )
            	    // InternalText.g:1490:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) )
            	    {
            	    // InternalText.g:1490:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) )
            	    // InternalText.g:1491:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' )
            	    {
            	    // InternalText.g:1491:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' )
            	    int alt32=5;
            	    switch ( input.LA(1) ) {
            	    case 30:
            	        {
            	        alt32=1;
            	        }
            	        break;
            	    case 31:
            	        {
            	        alt32=2;
            	        }
            	        break;
            	    case 32:
            	        {
            	        alt32=3;
            	        }
            	        break;
            	    case 33:
            	        {
            	        alt32=4;
            	        }
            	        break;
            	    case 34:
            	        {
            	        alt32=5;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 32, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt32) {
            	        case 1 :
            	            // InternalText.g:1492:7: lv_op_2_1= '&'
            	            {
            	            lv_op_2_1=(Token)match(input,30,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalText.g:1503:7: lv_op_2_2= '|'
            	            {
            	            lv_op_2_2=(Token)match(input,31,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalText.g:1514:7: lv_op_2_3= '^'
            	            {
            	            lv_op_2_3=(Token)match(input,32,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalText.g:1525:7: lv_op_2_4= '<<'
            	            {
            	            lv_op_2_4=(Token)match(input,33,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_4, grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_4, null);
            	            						

            	            }
            	            break;
            	        case 5 :
            	            // InternalText.g:1536:7: lv_op_2_5= '>>'
            	            {
            	            lv_op_2_5=(Token)match(input,34,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_5, grammarAccess.getBitwiseAccess().getOpGreaterThanSignGreaterThanSignKeyword_1_1_0_4());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_5, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalText.g:1549:4: ( (lv_right_3_0= ruleUnary ) )
            	    // InternalText.g:1550:5: (lv_right_3_0= ruleUnary )
            	    {
            	    // InternalText.g:1550:5: (lv_right_3_0= ruleUnary )
            	    // InternalText.g:1551:6: lv_right_3_0= ruleUnary
            	    {

            	    						newCompositeNode(grammarAccess.getBitwiseAccess().getRightUnaryParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_right_3_0=ruleUnary();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getBitwiseRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"m.Text.Unary");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // $ANTLR end "ruleBitwise"


    // $ANTLR start "entryRuleUnary"
    // InternalText.g:1573:1: entryRuleUnary returns [EObject current=null] : iv_ruleUnary= ruleUnary EOF ;
    public final EObject entryRuleUnary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary = null;


        try {
            // InternalText.g:1573:46: (iv_ruleUnary= ruleUnary EOF )
            // InternalText.g:1574:2: iv_ruleUnary= ruleUnary EOF
            {
             newCompositeNode(grammarAccess.getUnaryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnary=ruleUnary();

            state._fsp--;

             current =iv_ruleUnary; 
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
    // $ANTLR end "entryRuleUnary"


    // $ANTLR start "ruleUnary"
    // InternalText.g:1580:1: ruleUnary returns [EObject current=null] : ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) ) ;
    public final EObject ruleUnary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_2_0 = null;

        EObject this_Primary_3 = null;



        	enterRule();

        try {
            // InternalText.g:1586:2: ( ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) ) )
            // InternalText.g:1587:2: ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) )
            {
            // InternalText.g:1587:2: ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==35) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_ID||LA35_0==19) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalText.g:1588:3: ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) )
                    {
                    // InternalText.g:1588:3: ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) )
                    // InternalText.g:1589:4: () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) )
                    {
                    // InternalText.g:1589:4: ()
                    // InternalText.g:1590:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getUnaryAccess().getBitwiseNegateAction_0_0(),
                    						current);
                    				

                    }

                    otherlv_1=(Token)match(input,35,FOLLOW_30); 

                    				newLeafNode(otherlv_1, grammarAccess.getUnaryAccess().getTildeKeyword_0_1());
                    			
                    // InternalText.g:1600:4: ( (lv_expression_2_0= rulePrimary ) )
                    // InternalText.g:1601:5: (lv_expression_2_0= rulePrimary )
                    {
                    // InternalText.g:1601:5: (lv_expression_2_0= rulePrimary )
                    // InternalText.g:1602:6: lv_expression_2_0= rulePrimary
                    {

                    						newCompositeNode(grammarAccess.getUnaryAccess().getExpressionPrimaryParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_expression_2_0=rulePrimary();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryRule());
                    						}
                    						set(
                    							current,
                    							"expression",
                    							lv_expression_2_0,
                    							"m.Text.Primary");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:1621:3: (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? )
                    {
                    // InternalText.g:1621:3: (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? )
                    // InternalText.g:1622:4: this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )?
                    {

                    				newCompositeNode(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_35);
                    this_Primary_3=rulePrimary();

                    state._fsp--;


                    				current = this_Primary_3;
                    				afterParserOrEnumRuleCall();
                    			
                    // InternalText.g:1630:4: ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )?
                    int alt34=3;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==36) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==37) ) {
                        alt34=2;
                    }
                    switch (alt34) {
                        case 1 :
                            // InternalText.g:1631:5: ( () otherlv_5= '++' )
                            {
                            // InternalText.g:1631:5: ( () otherlv_5= '++' )
                            // InternalText.g:1632:6: () otherlv_5= '++'
                            {
                            // InternalText.g:1632:6: ()
                            // InternalText.g:1633:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getUnaryAccess().getIncrementExpressionAction_1_1_0_0(),
                            								current);
                            						

                            }

                            otherlv_5=(Token)match(input,36,FOLLOW_2); 

                            						newLeafNode(otherlv_5, grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_1_0_1());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalText.g:1645:5: ( () otherlv_7= '--' )
                            {
                            // InternalText.g:1645:5: ( () otherlv_7= '--' )
                            // InternalText.g:1646:6: () otherlv_7= '--'
                            {
                            // InternalText.g:1646:6: ()
                            // InternalText.g:1647:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getUnaryAccess().getDecrementExpressionAction_1_1_1_0(),
                            								current);
                            						

                            }

                            otherlv_7=(Token)match(input,37,FOLLOW_2); 

                            						newLeafNode(otherlv_7, grammarAccess.getUnaryAccess().getHyphenMinusHyphenMinusKeyword_1_1_1_1());
                            					

                            }


                            }
                            break;

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
    // $ANTLR end "ruleUnary"


    // $ANTLR start "entryRulePrimary"
    // InternalText.g:1664:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalText.g:1664:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalText.g:1665:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
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
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalText.g:1671:1: rulePrimary returns [EObject current=null] : (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject this_Access_0 = null;

        EObject this_Call_1 = null;

        EObject lv_expression_4_0 = null;



        	enterRule();

        try {
            // InternalText.g:1677:2: ( (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) ) )
            // InternalText.g:1678:2: (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            {
            // InternalText.g:1678:2: (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            int alt36=3;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==EOF||LA36_1==RULE_ID||(LA36_1>=8 && LA36_1<=10)||LA36_1==13||LA36_1==17||(LA36_1>=20 && LA36_1<=23)||(LA36_1>=25 && LA36_1<=34)||(LA36_1>=36 && LA36_1<=43)) ) {
                    alt36=1;
                }
                else if ( (LA36_1==19) ) {
                    alt36=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA36_0==19) ) {
                alt36=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // InternalText.g:1679:3: this_Access_0= ruleAccess
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getAccessParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Access_0=ruleAccess();

                    state._fsp--;


                    			current = this_Access_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalText.g:1688:3: this_Call_1= ruleCall
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getCallParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Call_1=ruleCall();

                    state._fsp--;


                    			current = this_Call_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalText.g:1697:3: ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' )
                    {
                    // InternalText.g:1697:3: ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' )
                    // InternalText.g:1698:4: () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')'
                    {
                    // InternalText.g:1698:4: ()
                    // InternalText.g:1699:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryAccess().getBracketsAction_2_0(),
                    						current);
                    				

                    }

                    otherlv_3=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_3, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_2_1());
                    			
                    // InternalText.g:1709:4: ( (lv_expression_4_0= ruleExpression ) )
                    // InternalText.g:1710:5: (lv_expression_4_0= ruleExpression )
                    {
                    // InternalText.g:1710:5: (lv_expression_4_0= ruleExpression )
                    // InternalText.g:1711:6: lv_expression_4_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionExpressionParserRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_36);
                    lv_expression_4_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    						}
                    						set(
                    							current,
                    							"expression",
                    							lv_expression_4_0,
                    							"m.Text.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_5=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_2_3());
                    			

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
    // $ANTLR end "rulePrimary"


    // $ANTLR start "ruleRelationType"
    // InternalText.g:1737:1: ruleRelationType returns [Enumerator current=null] : ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) ;
    public final Enumerator ruleRelationType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalText.g:1743:2: ( ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) )
            // InternalText.g:1744:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            {
            // InternalText.g:1744:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            int alt37=6;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt37=1;
                }
                break;
            case 39:
                {
                alt37=2;
                }
                break;
            case 40:
                {
                alt37=3;
                }
                break;
            case 41:
                {
                alt37=4;
                }
                break;
            case 42:
                {
                alt37=5;
                }
                break;
            case 43:
                {
                alt37=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // InternalText.g:1745:3: (enumLiteral_0= '>' )
                    {
                    // InternalText.g:1745:3: (enumLiteral_0= '>' )
                    // InternalText.g:1746:4: enumLiteral_0= '>'
                    {
                    enumLiteral_0=(Token)match(input,38,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:1753:3: (enumLiteral_1= '<' )
                    {
                    // InternalText.g:1753:3: (enumLiteral_1= '<' )
                    // InternalText.g:1754:4: enumLiteral_1= '<'
                    {
                    enumLiteral_1=(Token)match(input,39,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:1761:3: (enumLiteral_2= '=' )
                    {
                    // InternalText.g:1761:3: (enumLiteral_2= '=' )
                    // InternalText.g:1762:4: enumLiteral_2= '='
                    {
                    enumLiteral_2=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:1769:3: (enumLiteral_3= '!=' )
                    {
                    // InternalText.g:1769:3: (enumLiteral_3= '!=' )
                    // InternalText.g:1770:4: enumLiteral_3= '!='
                    {
                    enumLiteral_3=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalText.g:1777:3: (enumLiteral_4= '>=' )
                    {
                    // InternalText.g:1777:3: (enumLiteral_4= '>=' )
                    // InternalText.g:1778:4: enumLiteral_4= '>='
                    {
                    enumLiteral_4=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalText.g:1785:3: (enumLiteral_5= '<=' )
                    {
                    // InternalText.g:1785:3: (enumLiteral_5= '<=' )
                    // InternalText.g:1786:4: enumLiteral_5= '<='
                    {
                    enumLiteral_5=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5());
                    			

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
    // $ANTLR end "ruleRelationType"


    // $ANTLR start "ruleAssignmentType"
    // InternalText.g:1796:1: ruleAssignmentType returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) ) ;
    public final Enumerator ruleAssignmentType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;


        	enterRule();

        try {
            // InternalText.g:1802:2: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) ) )
            // InternalText.g:1803:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) )
            {
            // InternalText.g:1803:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) )
            int alt38=11;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt38=1;
                }
                break;
            case 44:
                {
                alt38=2;
                }
                break;
            case 45:
                {
                alt38=3;
                }
                break;
            case 46:
                {
                alt38=4;
                }
                break;
            case 47:
                {
                alt38=5;
                }
                break;
            case 48:
                {
                alt38=6;
                }
                break;
            case 49:
                {
                alt38=7;
                }
                break;
            case 50:
                {
                alt38=8;
                }
                break;
            case 51:
                {
                alt38=9;
                }
                break;
            case 52:
                {
                alt38=10;
                }
                break;
            case 53:
                {
                alt38=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // InternalText.g:1804:3: (enumLiteral_0= '=' )
                    {
                    // InternalText.g:1804:3: (enumLiteral_0= '=' )
                    // InternalText.g:1805:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalText.g:1812:3: (enumLiteral_1= '+=' )
                    {
                    // InternalText.g:1812:3: (enumLiteral_1= '+=' )
                    // InternalText.g:1813:4: enumLiteral_1= '+='
                    {
                    enumLiteral_1=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalText.g:1820:3: (enumLiteral_2= '-=' )
                    {
                    // InternalText.g:1820:3: (enumLiteral_2= '-=' )
                    // InternalText.g:1821:4: enumLiteral_2= '-='
                    {
                    enumLiteral_2=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalText.g:1828:3: (enumLiteral_3= '*=' )
                    {
                    // InternalText.g:1828:3: (enumLiteral_3= '*=' )
                    // InternalText.g:1829:4: enumLiteral_3= '*='
                    {
                    enumLiteral_3=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalText.g:1836:3: (enumLiteral_4= '/=' )
                    {
                    // InternalText.g:1836:3: (enumLiteral_4= '/=' )
                    // InternalText.g:1837:4: enumLiteral_4= '/='
                    {
                    enumLiteral_4=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalText.g:1844:3: (enumLiteral_5= '%=' )
                    {
                    // InternalText.g:1844:3: (enumLiteral_5= '%=' )
                    // InternalText.g:1845:4: enumLiteral_5= '%='
                    {
                    enumLiteral_5=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalText.g:1852:3: (enumLiteral_6= '&=' )
                    {
                    // InternalText.g:1852:3: (enumLiteral_6= '&=' )
                    // InternalText.g:1853:4: enumLiteral_6= '&='
                    {
                    enumLiteral_6=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalText.g:1860:3: (enumLiteral_7= '|=' )
                    {
                    // InternalText.g:1860:3: (enumLiteral_7= '|=' )
                    // InternalText.g:1861:4: enumLiteral_7= '|='
                    {
                    enumLiteral_7=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalText.g:1868:3: (enumLiteral_8= '^=' )
                    {
                    // InternalText.g:1868:3: (enumLiteral_8= '^=' )
                    // InternalText.g:1869:4: enumLiteral_8= '^='
                    {
                    enumLiteral_8=(Token)match(input,51,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalText.g:1876:3: (enumLiteral_9= '<<=' )
                    {
                    // InternalText.g:1876:3: (enumLiteral_9= '<<=' )
                    // InternalText.g:1877:4: enumLiteral_9= '<<='
                    {
                    enumLiteral_9=(Token)match(input,52,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalText.g:1884:3: (enumLiteral_10= '>>=' )
                    {
                    // InternalText.g:1884:3: (enumLiteral_10= '>>=' )
                    // InternalText.g:1885:4: enumLiteral_10= '>>='
                    {
                    enumLiteral_10=(Token)match(input,53,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getShiftRightEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getAssignmentTypeAccess().getShiftRightEnumLiteralDeclaration_10());
                    			

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
    // $ANTLR end "ruleAssignmentType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000190L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000022010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000022012L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000010100L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000022410L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x00000008010A2010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x003FF10000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000100200L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00000008000A2010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x00000FC000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000006000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000038000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00000007C0000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000100000L});

}