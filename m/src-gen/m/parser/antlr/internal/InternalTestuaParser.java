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
import m.services.TestuaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTestuaParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_REAL", "RULE_WHITE_SPACE", "':'", "'{'", "','", "'}'", "'['", "']'", "'entitate'", "'bakoitzarengatik'", "'hurrengo'", "'etiketekin'", "'baldin'", "'eta'", "'edo'", "'bestela'", "'('", "')'", "'.'", "'||'", "'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'~'", "'++'", "'--'", "'>'", "'<'", "'='", "'!='", "'>='", "'<='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='"
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

        public InternalTestuaParser(TokenStream input, TestuaGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Game";
       	}

       	@Override
       	protected TestuaGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleGame"
    // InternalTestua.g:65:1: entryRuleGame returns [EObject current=null] : iv_ruleGame= ruleGame EOF ;
    public final EObject entryRuleGame() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGame = null;


        try {
            // InternalTestua.g:65:45: (iv_ruleGame= ruleGame EOF )
            // InternalTestua.g:66:2: iv_ruleGame= ruleGame EOF
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
    // InternalTestua.g:72:1: ruleGame returns [EObject current=null] : ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )* ;
    public final EObject ruleGame() throws RecognitionException {
        EObject current = null;

        EObject lv_entities_0_0 = null;

        EObject lv_systems_1_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:78:2: ( ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )* )
            // InternalTestua.g:79:2: ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )*
            {
            // InternalTestua.g:79:2: ( ( (lv_entities_0_0= ruleEntity ) ) | ( (lv_systems_1_0= ruleSystem ) ) )*
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

                            if ( (LA1_5==RULE_ID||LA1_5==8) ) {
                                alt1=1;
                            }
                            else if ( (LA1_5==21||LA1_5==23||LA1_5==42||(LA1_5>=46 && LA1_5<=55)) ) {
                                alt1=2;
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
            	    // InternalTestua.g:80:3: ( (lv_entities_0_0= ruleEntity ) )
            	    {
            	    // InternalTestua.g:80:3: ( (lv_entities_0_0= ruleEntity ) )
            	    // InternalTestua.g:81:4: (lv_entities_0_0= ruleEntity )
            	    {
            	    // InternalTestua.g:81:4: (lv_entities_0_0= ruleEntity )
            	    // InternalTestua.g:82:5: lv_entities_0_0= ruleEntity
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
            	    						"m.Testua.Entity");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalTestua.g:100:3: ( (lv_systems_1_0= ruleSystem ) )
            	    {
            	    // InternalTestua.g:100:3: ( (lv_systems_1_0= ruleSystem ) )
            	    // InternalTestua.g:101:4: (lv_systems_1_0= ruleSystem )
            	    {
            	    // InternalTestua.g:101:4: (lv_systems_1_0= ruleSystem )
            	    // InternalTestua.g:102:5: lv_systems_1_0= ruleSystem
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
            	    						"m.Testua.System");
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
    // InternalTestua.g:123:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalTestua.g:123:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalTestua.g:124:2: iv_ruleEntity= ruleEntity EOF
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
    // InternalTestua.g:130:1: ruleEntity returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? ) ;
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
            // InternalTestua.g:136:2: ( ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? ) )
            // InternalTestua.g:137:2: ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? )
            {
            // InternalTestua.g:137:2: ( ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )? )
            // InternalTestua.g:138:3: ( (lv_name_0_0= RULE_ID ) )+ (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )? otherlv_3= '{' ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )? otherlv_7= '}' (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )?
            {
            // InternalTestua.g:138:3: ( (lv_name_0_0= RULE_ID ) )+
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
            	    // InternalTestua.g:139:4: (lv_name_0_0= RULE_ID )
            	    {
            	    // InternalTestua.g:139:4: (lv_name_0_0= RULE_ID )
            	    // InternalTestua.g:140:5: lv_name_0_0= RULE_ID
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
            	    						"m.Testua.ID");
            	    				

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

            // InternalTestua.g:156:3: (otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+ )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==7) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalTestua.g:157:4: otherlv_1= ':' ( (lv_base_2_0= RULE_ID ) )+
                    {
                    otherlv_1=(Token)match(input,7,FOLLOW_5); 

                    				newLeafNode(otherlv_1, grammarAccess.getEntityAccess().getColonKeyword_1_0());
                    			
                    // InternalTestua.g:161:4: ( (lv_base_2_0= RULE_ID ) )+
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
                    	    // InternalTestua.g:162:5: (lv_base_2_0= RULE_ID )
                    	    {
                    	    // InternalTestua.g:162:5: (lv_base_2_0= RULE_ID )
                    	    // InternalTestua.g:163:6: lv_base_2_0= RULE_ID
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
                    	    							"m.Testua.ID");
                    	    					

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
            		
            // InternalTestua.g:184:3: ( ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalTestua.g:185:4: ( (lv_components_4_0= ruleComponent ) ) (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )*
                    {
                    // InternalTestua.g:185:4: ( (lv_components_4_0= ruleComponent ) )
                    // InternalTestua.g:186:5: (lv_components_4_0= ruleComponent )
                    {
                    // InternalTestua.g:186:5: (lv_components_4_0= ruleComponent )
                    // InternalTestua.g:187:6: lv_components_4_0= ruleComponent
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
                    							"m.Testua.Component");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalTestua.g:204:4: (otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==9) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalTestua.g:205:5: otherlv_5= ',' ( (lv_components_6_0= ruleComponent ) )
                    	    {
                    	    otherlv_5=(Token)match(input,9,FOLLOW_5); 

                    	    					newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalTestua.g:209:5: ( (lv_components_6_0= ruleComponent ) )
                    	    // InternalTestua.g:210:6: (lv_components_6_0= ruleComponent )
                    	    {
                    	    // InternalTestua.g:210:6: (lv_components_6_0= ruleComponent )
                    	    // InternalTestua.g:211:7: lv_components_6_0= ruleComponent
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
                    	    								"m.Testua.Component");
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
            		
            // InternalTestua.g:234:3: (otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==11) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalTestua.g:235:4: otherlv_8= '[' ( (lv_entities_9_0= ruleEntity ) )* otherlv_10= ']'
                    {
                    otherlv_8=(Token)match(input,11,FOLLOW_10); 

                    				newLeafNode(otherlv_8, grammarAccess.getEntityAccess().getLeftSquareBracketKeyword_5_0());
                    			
                    // InternalTestua.g:239:4: ( (lv_entities_9_0= ruleEntity ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==RULE_ID) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalTestua.g:240:5: (lv_entities_9_0= ruleEntity )
                    	    {
                    	    // InternalTestua.g:240:5: (lv_entities_9_0= ruleEntity )
                    	    // InternalTestua.g:241:6: lv_entities_9_0= ruleEntity
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
                    	    							"m.Testua.Entity");
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
    // InternalTestua.g:267:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalTestua.g:267:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalTestua.g:268:2: iv_ruleComponent= ruleComponent EOF
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
    // InternalTestua.g:274:1: ruleComponent returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? ) ;
    public final EObject ruleComponent() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:280:2: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? ) )
            // InternalTestua.g:281:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? )
            {
            // InternalTestua.g:281:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )? )
            // InternalTestua.g:282:3: ( (lv_name_0_0= RULE_ID ) ) ( (lv_value_1_0= ruleValue ) )?
            {
            // InternalTestua.g:282:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalTestua.g:283:4: (lv_name_0_0= RULE_ID )
            {
            // InternalTestua.g:283:4: (lv_name_0_0= RULE_ID )
            // InternalTestua.g:284:5: lv_name_0_0= RULE_ID
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
            						"m.Testua.ID");
            				

            }


            }

            // InternalTestua.g:300:3: ( (lv_value_1_0= ruleValue ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_REAL)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTestua.g:301:4: (lv_value_1_0= ruleValue )
                    {
                    // InternalTestua.g:301:4: (lv_value_1_0= ruleValue )
                    // InternalTestua.g:302:5: lv_value_1_0= ruleValue
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
                    						"m.Testua.Value");
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
    // InternalTestua.g:323:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalTestua.g:323:46: (iv_ruleValue= ruleValue EOF )
            // InternalTestua.g:324:2: iv_ruleValue= ruleValue EOF
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
    // InternalTestua.g:330:1: ruleValue returns [EObject current=null] : (this_Vector_0= ruleVector | this_Word_1= ruleWord ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_Vector_0 = null;

        EObject this_Word_1 = null;



        	enterRule();

        try {
            // InternalTestua.g:336:2: ( (this_Vector_0= ruleVector | this_Word_1= ruleWord ) )
            // InternalTestua.g:337:2: (this_Vector_0= ruleVector | this_Word_1= ruleWord )
            {
            // InternalTestua.g:337:2: (this_Vector_0= ruleVector | this_Word_1= ruleWord )
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
                    // InternalTestua.g:338:3: this_Vector_0= ruleVector
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
                    // InternalTestua.g:347:3: this_Word_1= ruleWord
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
    // InternalTestua.g:359:1: entryRuleVector returns [EObject current=null] : iv_ruleVector= ruleVector EOF ;
    public final EObject entryRuleVector() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVector = null;


        try {
            // InternalTestua.g:359:47: (iv_ruleVector= ruleVector EOF )
            // InternalTestua.g:360:2: iv_ruleVector= ruleVector EOF
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
    // InternalTestua.g:366:1: ruleVector returns [EObject current=null] : ( (lv_numbers_0_0= RULE_REAL ) )+ ;
    public final EObject ruleVector() throws RecognitionException {
        EObject current = null;

        Token lv_numbers_0_0=null;


        	enterRule();

        try {
            // InternalTestua.g:372:2: ( ( (lv_numbers_0_0= RULE_REAL ) )+ )
            // InternalTestua.g:373:2: ( (lv_numbers_0_0= RULE_REAL ) )+
            {
            // InternalTestua.g:373:2: ( (lv_numbers_0_0= RULE_REAL ) )+
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
            	    // InternalTestua.g:374:3: (lv_numbers_0_0= RULE_REAL )
            	    {
            	    // InternalTestua.g:374:3: (lv_numbers_0_0= RULE_REAL )
            	    // InternalTestua.g:375:4: lv_numbers_0_0= RULE_REAL
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
            	    					"m.Testua.REAL");
            	    			

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
    // InternalTestua.g:394:1: entryRuleWord returns [EObject current=null] : iv_ruleWord= ruleWord EOF ;
    public final EObject entryRuleWord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWord = null;


        try {
            // InternalTestua.g:394:45: (iv_ruleWord= ruleWord EOF )
            // InternalTestua.g:395:2: iv_ruleWord= ruleWord EOF
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
    // InternalTestua.g:401:1: ruleWord returns [EObject current=null] : ( (lv_values_0_0= RULE_ID ) )+ ;
    public final EObject ruleWord() throws RecognitionException {
        EObject current = null;

        Token lv_values_0_0=null;


        	enterRule();

        try {
            // InternalTestua.g:407:2: ( ( (lv_values_0_0= RULE_ID ) )+ )
            // InternalTestua.g:408:2: ( (lv_values_0_0= RULE_ID ) )+
            {
            // InternalTestua.g:408:2: ( (lv_values_0_0= RULE_ID ) )+
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
            	    // InternalTestua.g:409:3: (lv_values_0_0= RULE_ID )
            	    {
            	    // InternalTestua.g:409:3: (lv_values_0_0= RULE_ID )
            	    // InternalTestua.g:410:4: lv_values_0_0= RULE_ID
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
            	    					"m.Testua.ID");
            	    			

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
    // InternalTestua.g:429:1: entryRuleSystem returns [EObject current=null] : iv_ruleSystem= ruleSystem EOF ;
    public final EObject entryRuleSystem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSystem = null;


        try {
            // InternalTestua.g:429:47: (iv_ruleSystem= ruleSystem EOF )
            // InternalTestua.g:430:2: iv_ruleSystem= ruleSystem EOF
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
    // InternalTestua.g:436:1: ruleSystem returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ ) ;
    public final EObject ruleSystem() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_commands_2_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:442:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ ) )
            // InternalTestua.g:443:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ )
            {
            // InternalTestua.g:443:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+ )
            // InternalTestua.g:444:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_commands_2_0= ruleCommand ) )+
            {
            // InternalTestua.g:444:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalTestua.g:445:4: (lv_name_0_0= RULE_ID )
            {
            // InternalTestua.g:445:4: (lv_name_0_0= RULE_ID )
            // InternalTestua.g:446:5: lv_name_0_0= RULE_ID
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
            						"m.Testua.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,7,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getSystemAccess().getColonKeyword_1());
            		
            // InternalTestua.g:466:3: ( (lv_commands_2_0= ruleCommand ) )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    int LA13_2 = input.LA(2);

                    if ( (LA13_2==21||LA13_2==23||LA13_2==42||(LA13_2>=46 && LA13_2<=55)) ) {
                        alt13=1;
                    }


                }
                else if ( (LA13_0==13||LA13_0==17) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalTestua.g:467:4: (lv_commands_2_0= ruleCommand )
            	    {
            	    // InternalTestua.g:467:4: (lv_commands_2_0= ruleCommand )
            	    // InternalTestua.g:468:5: lv_commands_2_0= ruleCommand
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
            	    						"m.Testua.Command");
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
    // InternalTestua.g:489:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalTestua.g:489:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalTestua.g:490:2: iv_ruleCommand= ruleCommand EOF
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
    // InternalTestua.g:496:1: ruleCommand returns [EObject current=null] : (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        EObject this_Loop_0 = null;

        EObject this_Branch_1 = null;

        EObject this_Assignment_2 = null;

        EObject this_Call_3 = null;



        	enterRule();

        try {
            // InternalTestua.g:502:2: ( (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall ) )
            // InternalTestua.g:503:2: (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall )
            {
            // InternalTestua.g:503:2: (this_Loop_0= ruleLoop | this_Branch_1= ruleBranch | this_Assignment_2= ruleAssignment | this_Call_3= ruleCall )
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

                if ( (LA14_3==23||LA14_3==42||(LA14_3>=46 && LA14_3<=55)) ) {
                    alt14=3;
                }
                else if ( (LA14_3==21) ) {
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
                    // InternalTestua.g:504:3: this_Loop_0= ruleLoop
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
                    // InternalTestua.g:513:3: this_Branch_1= ruleBranch
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
                    // InternalTestua.g:522:3: this_Assignment_2= ruleAssignment
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
                    // InternalTestua.g:531:3: this_Call_3= ruleCall
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
    // InternalTestua.g:543:1: entryRuleLoop returns [EObject current=null] : iv_ruleLoop= ruleLoop EOF ;
    public final EObject entryRuleLoop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoop = null;


        try {
            // InternalTestua.g:543:45: (iv_ruleLoop= ruleLoop EOF )
            // InternalTestua.g:544:2: iv_ruleLoop= ruleLoop EOF
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
    // InternalTestua.g:550:1: ruleLoop returns [EObject current=null] : (otherlv_0= 'entitate' ( (lv_entity_1_0= RULE_ID ) ) otherlv_2= 'bakoitzarengatik' (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )? otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )+ otherlv_9= '}' ) ;
    public final EObject ruleLoop() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_entity_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_constraints_6_0=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_commands_8_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:556:2: ( (otherlv_0= 'entitate' ( (lv_entity_1_0= RULE_ID ) ) otherlv_2= 'bakoitzarengatik' (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )? otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )+ otherlv_9= '}' ) )
            // InternalTestua.g:557:2: (otherlv_0= 'entitate' ( (lv_entity_1_0= RULE_ID ) ) otherlv_2= 'bakoitzarengatik' (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )? otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )+ otherlv_9= '}' )
            {
            // InternalTestua.g:557:2: (otherlv_0= 'entitate' ( (lv_entity_1_0= RULE_ID ) ) otherlv_2= 'bakoitzarengatik' (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )? otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )+ otherlv_9= '}' )
            // InternalTestua.g:558:3: otherlv_0= 'entitate' ( (lv_entity_1_0= RULE_ID ) ) otherlv_2= 'bakoitzarengatik' (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )? otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )+ otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getLoopAccess().getEntitateKeyword_0());
            		
            // InternalTestua.g:562:3: ( (lv_entity_1_0= RULE_ID ) )
            // InternalTestua.g:563:4: (lv_entity_1_0= RULE_ID )
            {
            // InternalTestua.g:563:4: (lv_entity_1_0= RULE_ID )
            // InternalTestua.g:564:5: lv_entity_1_0= RULE_ID
            {
            lv_entity_1_0=(Token)match(input,RULE_ID,FOLLOW_16); 

            					newLeafNode(lv_entity_1_0, grammarAccess.getLoopAccess().getEntityIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLoopRule());
            					}
            					setWithLastConsumed(
            						current,
            						"entity",
            						lv_entity_1_0,
            						"m.Testua.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_17); 

            			newLeafNode(otherlv_2, grammarAccess.getLoopAccess().getBakoitzarengatikKeyword_2());
            		
            // InternalTestua.g:584:3: (otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+ )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==15) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalTestua.g:585:4: otherlv_3= 'hurrengo' otherlv_4= 'etiketekin' otherlv_5= ':' ( (lv_constraints_6_0= RULE_ID ) )+
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_18); 

                    				newLeafNode(otherlv_3, grammarAccess.getLoopAccess().getHurrengoKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_13); 

                    				newLeafNode(otherlv_4, grammarAccess.getLoopAccess().getEtiketekinKeyword_3_1());
                    			
                    otherlv_5=(Token)match(input,7,FOLLOW_5); 

                    				newLeafNode(otherlv_5, grammarAccess.getLoopAccess().getColonKeyword_3_2());
                    			
                    // InternalTestua.g:597:4: ( (lv_constraints_6_0= RULE_ID ) )+
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
                    	    // InternalTestua.g:598:5: (lv_constraints_6_0= RULE_ID )
                    	    {
                    	    // InternalTestua.g:598:5: (lv_constraints_6_0= RULE_ID )
                    	    // InternalTestua.g:599:6: lv_constraints_6_0= RULE_ID
                    	    {
                    	    lv_constraints_6_0=(Token)match(input,RULE_ID,FOLLOW_6); 

                    	    						newLeafNode(lv_constraints_6_0, grammarAccess.getLoopAccess().getConstraintsIDTerminalRuleCall_3_3_0());
                    	    					

                    	    						if (current==null) {
                    	    							current = createModelElement(grammarAccess.getLoopRule());
                    	    						}
                    	    						addWithLastConsumed(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_6_0,
                    	    							"m.Testua.ID");
                    	    					

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

            otherlv_7=(Token)match(input,8,FOLLOW_14); 

            			newLeafNode(otherlv_7, grammarAccess.getLoopAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalTestua.g:620:3: ( (lv_commands_8_0= ruleCommand ) )+
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
            	    // InternalTestua.g:621:4: (lv_commands_8_0= ruleCommand )
            	    {
            	    // InternalTestua.g:621:4: (lv_commands_8_0= ruleCommand )
            	    // InternalTestua.g:622:5: lv_commands_8_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getLoopAccess().getCommandsCommandParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_commands_8_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getLoopRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_8_0,
            	    						"m.Testua.Command");
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

            otherlv_9=(Token)match(input,10,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getLoopAccess().getRightCurlyBracketKeyword_6());
            		

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
    // InternalTestua.g:647:1: entryRuleBranch returns [EObject current=null] : iv_ruleBranch= ruleBranch EOF ;
    public final EObject entryRuleBranch() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBranch = null;


        try {
            // InternalTestua.g:647:47: (iv_ruleBranch= ruleBranch EOF )
            // InternalTestua.g:648:2: iv_ruleBranch= ruleBranch EOF
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
    // InternalTestua.g:654:1: ruleBranch returns [EObject current=null] : (otherlv_0= 'baldin' otherlv_1= 'eta' ( (lv_if_2_0= ruleCondition ) ) (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? ) ;
    public final EObject ruleBranch() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_if_2_0 = null;

        EObject lv_elseIfs_4_0 = null;

        EObject lv_commands_7_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:660:2: ( (otherlv_0= 'baldin' otherlv_1= 'eta' ( (lv_if_2_0= ruleCondition ) ) (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? ) )
            // InternalTestua.g:661:2: (otherlv_0= 'baldin' otherlv_1= 'eta' ( (lv_if_2_0= ruleCondition ) ) (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? )
            {
            // InternalTestua.g:661:2: (otherlv_0= 'baldin' otherlv_1= 'eta' ( (lv_if_2_0= ruleCondition ) ) (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )? )
            // InternalTestua.g:662:3: otherlv_0= 'baldin' otherlv_1= 'eta' ( (lv_if_2_0= ruleCondition ) ) (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )* (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )?
            {
            otherlv_0=(Token)match(input,17,FOLLOW_20); 

            			newLeafNode(otherlv_0, grammarAccess.getBranchAccess().getBaldinKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_21); 

            			newLeafNode(otherlv_1, grammarAccess.getBranchAccess().getEtaKeyword_1());
            		
            // InternalTestua.g:670:3: ( (lv_if_2_0= ruleCondition ) )
            // InternalTestua.g:671:4: (lv_if_2_0= ruleCondition )
            {
            // InternalTestua.g:671:4: (lv_if_2_0= ruleCondition )
            // InternalTestua.g:672:5: lv_if_2_0= ruleCondition
            {

            					newCompositeNode(grammarAccess.getBranchAccess().getIfConditionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_22);
            lv_if_2_0=ruleCondition();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBranchRule());
            					}
            					set(
            						current,
            						"if",
            						lv_if_2_0,
            						"m.Testua.Condition");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTestua.g:689:3: (otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==19) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalTestua.g:690:4: otherlv_3= 'edo' ( (lv_elseIfs_4_0= ruleCondition ) )
            	    {
            	    otherlv_3=(Token)match(input,19,FOLLOW_21); 

            	    				newLeafNode(otherlv_3, grammarAccess.getBranchAccess().getEdoKeyword_3_0());
            	    			
            	    // InternalTestua.g:694:4: ( (lv_elseIfs_4_0= ruleCondition ) )
            	    // InternalTestua.g:695:5: (lv_elseIfs_4_0= ruleCondition )
            	    {
            	    // InternalTestua.g:695:5: (lv_elseIfs_4_0= ruleCondition )
            	    // InternalTestua.g:696:6: lv_elseIfs_4_0= ruleCondition
            	    {

            	    						newCompositeNode(grammarAccess.getBranchAccess().getElseIfsConditionParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_22);
            	    lv_elseIfs_4_0=ruleCondition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getBranchRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseIfs",
            	    							lv_elseIfs_4_0,
            	    							"m.Testua.Condition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // InternalTestua.g:714:3: (otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==20) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalTestua.g:715:4: otherlv_5= 'bestela' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )+ otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,20,FOLLOW_23); 

                    				newLeafNode(otherlv_5, grammarAccess.getBranchAccess().getBestelaKeyword_4_0());
                    			
                    otherlv_6=(Token)match(input,8,FOLLOW_14); 

                    				newLeafNode(otherlv_6, grammarAccess.getBranchAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalTestua.g:723:4: ( (lv_commands_7_0= ruleCommand ) )+
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
                    	    // InternalTestua.g:724:5: (lv_commands_7_0= ruleCommand )
                    	    {
                    	    // InternalTestua.g:724:5: (lv_commands_7_0= ruleCommand )
                    	    // InternalTestua.g:725:6: lv_commands_7_0= ruleCommand
                    	    {

                    	    						newCompositeNode(grammarAccess.getBranchAccess().getCommandsCommandParserRuleCall_4_2_0());
                    	    					
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
                    	    							"m.Testua.Command");
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

                    				newLeafNode(otherlv_8, grammarAccess.getBranchAccess().getRightCurlyBracketKeyword_4_3());
                    			

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
    // InternalTestua.g:751:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalTestua.g:751:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalTestua.g:752:2: iv_ruleCondition= ruleCondition EOF
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
    // InternalTestua.g:758:1: ruleCondition returns [EObject current=null] : ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_condition_0_0 = null;

        EObject lv_commands_2_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:764:2: ( ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' ) )
            // InternalTestua.g:765:2: ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' )
            {
            // InternalTestua.g:765:2: ( ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}' )
            // InternalTestua.g:766:3: ( (lv_condition_0_0= ruleExpression ) ) otherlv_1= '{' ( (lv_commands_2_0= ruleCommand ) )* otherlv_3= '}'
            {
            // InternalTestua.g:766:3: ( (lv_condition_0_0= ruleExpression ) )
            // InternalTestua.g:767:4: (lv_condition_0_0= ruleExpression )
            {
            // InternalTestua.g:767:4: (lv_condition_0_0= ruleExpression )
            // InternalTestua.g:768:5: lv_condition_0_0= ruleExpression
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
            						"m.Testua.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,8,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getConditionAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalTestua.g:789:3: ( (lv_commands_2_0= ruleCommand ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID||LA21_0==13||LA21_0==17) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalTestua.g:790:4: (lv_commands_2_0= ruleCommand )
            	    {
            	    // InternalTestua.g:790:4: (lv_commands_2_0= ruleCommand )
            	    // InternalTestua.g:791:5: lv_commands_2_0= ruleCommand
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
            	    						"m.Testua.Command");
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
    // InternalTestua.g:816:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalTestua.g:816:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalTestua.g:817:2: iv_ruleAssignment= ruleAssignment EOF
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
    // InternalTestua.g:823:1: ruleAssignment returns [EObject current=null] : ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        EObject lv_access_0_0 = null;

        Enumerator lv_type_1_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:829:2: ( ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) ) )
            // InternalTestua.g:830:2: ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) )
            {
            // InternalTestua.g:830:2: ( ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) ) )
            // InternalTestua.g:831:3: ( (lv_access_0_0= ruleAccess ) ) ( (lv_type_1_0= ruleAssignmentType ) ) ( (lv_expression_2_0= ruleExpression ) )
            {
            // InternalTestua.g:831:3: ( (lv_access_0_0= ruleAccess ) )
            // InternalTestua.g:832:4: (lv_access_0_0= ruleAccess )
            {
            // InternalTestua.g:832:4: (lv_access_0_0= ruleAccess )
            // InternalTestua.g:833:5: lv_access_0_0= ruleAccess
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
            						"m.Testua.Access");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTestua.g:850:3: ( (lv_type_1_0= ruleAssignmentType ) )
            // InternalTestua.g:851:4: (lv_type_1_0= ruleAssignmentType )
            {
            // InternalTestua.g:851:4: (lv_type_1_0= ruleAssignmentType )
            // InternalTestua.g:852:5: lv_type_1_0= ruleAssignmentType
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getTypeAssignmentTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_21);
            lv_type_1_0=ruleAssignmentType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"m.Testua.AssignmentType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTestua.g:869:3: ( (lv_expression_2_0= ruleExpression ) )
            // InternalTestua.g:870:4: (lv_expression_2_0= ruleExpression )
            {
            // InternalTestua.g:870:4: (lv_expression_2_0= ruleExpression )
            // InternalTestua.g:871:5: lv_expression_2_0= ruleExpression
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
            						"m.Testua.Expression");
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
    // InternalTestua.g:892:1: entryRuleCall returns [EObject current=null] : iv_ruleCall= ruleCall EOF ;
    public final EObject entryRuleCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCall = null;


        try {
            // InternalTestua.g:892:45: (iv_ruleCall= ruleCall EOF )
            // InternalTestua.g:893:2: iv_ruleCall= ruleCall EOF
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
    // InternalTestua.g:899:1: ruleCall returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' ) ;
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
            // InternalTestua.g:905:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' ) )
            // InternalTestua.g:906:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' )
            {
            // InternalTestua.g:906:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')' )
            // InternalTestua.g:907:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (lv_parameters_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )* otherlv_5= ')'
            {
            // InternalTestua.g:907:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalTestua.g:908:4: (lv_name_0_0= RULE_ID )
            {
            // InternalTestua.g:908:4: (lv_name_0_0= RULE_ID )
            // InternalTestua.g:909:5: lv_name_0_0= RULE_ID
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
            						"m.Testua.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_21); 

            			newLeafNode(otherlv_1, grammarAccess.getCallAccess().getLeftParenthesisKeyword_1());
            		
            // InternalTestua.g:929:3: ( (lv_parameters_2_0= ruleExpression ) )
            // InternalTestua.g:930:4: (lv_parameters_2_0= ruleExpression )
            {
            // InternalTestua.g:930:4: (lv_parameters_2_0= ruleExpression )
            // InternalTestua.g:931:5: lv_parameters_2_0= ruleExpression
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
            						"m.Testua.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalTestua.g:948:3: (otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==9) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalTestua.g:949:4: otherlv_3= ',' ( (lv_parameters_4_0= ruleExpression ) )
            	    {
            	    otherlv_3=(Token)match(input,9,FOLLOW_21); 

            	    				newLeafNode(otherlv_3, grammarAccess.getCallAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalTestua.g:953:4: ( (lv_parameters_4_0= ruleExpression ) )
            	    // InternalTestua.g:954:5: (lv_parameters_4_0= ruleExpression )
            	    {
            	    // InternalTestua.g:954:5: (lv_parameters_4_0= ruleExpression )
            	    // InternalTestua.g:955:6: lv_parameters_4_0= ruleExpression
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
            	    							"m.Testua.Expression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_5=(Token)match(input,22,FOLLOW_2); 

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
    // InternalTestua.g:981:1: entryRuleAccess returns [EObject current=null] : iv_ruleAccess= ruleAccess EOF ;
    public final EObject entryRuleAccess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAccess = null;


        try {
            // InternalTestua.g:981:47: (iv_ruleAccess= ruleAccess EOF )
            // InternalTestua.g:982:2: iv_ruleAccess= ruleAccess EOF
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
    // InternalTestua.g:988:1: ruleAccess returns [EObject current=null] : ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) ;
    public final EObject ruleAccess() throws RecognitionException {
        EObject current = null;

        Token lv_names_0_0=null;
        Token otherlv_1=null;
        Token lv_names_2_0=null;


        	enterRule();

        try {
            // InternalTestua.g:994:2: ( ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) )
            // InternalTestua.g:995:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            {
            // InternalTestua.g:995:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            // InternalTestua.g:996:3: ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            {
            // InternalTestua.g:996:3: ( (lv_names_0_0= RULE_ID ) )
            // InternalTestua.g:997:4: (lv_names_0_0= RULE_ID )
            {
            // InternalTestua.g:997:4: (lv_names_0_0= RULE_ID )
            // InternalTestua.g:998:5: lv_names_0_0= RULE_ID
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
            						"m.Testua.ID");
            				

            }


            }

            // InternalTestua.g:1014:3: (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==23) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalTestua.g:1015:4: otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) )
            	    {
            	    otherlv_1=(Token)match(input,23,FOLLOW_5); 

            	    				newLeafNode(otherlv_1, grammarAccess.getAccessAccess().getFullStopKeyword_1_0());
            	    			
            	    // InternalTestua.g:1019:4: ( (lv_names_2_0= RULE_ID ) )
            	    // InternalTestua.g:1020:5: (lv_names_2_0= RULE_ID )
            	    {
            	    // InternalTestua.g:1020:5: (lv_names_2_0= RULE_ID )
            	    // InternalTestua.g:1021:6: lv_names_2_0= RULE_ID
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
            	    							"m.Testua.ID");
            	    					

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
    // InternalTestua.g:1042:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalTestua.g:1042:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalTestua.g:1043:2: iv_ruleExpression= ruleExpression EOF
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
    // InternalTestua.g:1049:1: ruleExpression returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1055:2: ( (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // InternalTestua.g:1056:2: (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // InternalTestua.g:1056:2: (this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )* )
            // InternalTestua.g:1057:3: this_And_0= ruleAnd ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )*
            {

            			newCompositeNode(grammarAccess.getExpressionAccess().getAndParserRuleCall_0());
            		
            pushFollow(FOLLOW_28);
            this_And_0=ruleAnd();

            state._fsp--;


            			current = this_And_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1065:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==24) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalTestua.g:1066:4: () otherlv_2= '||' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // InternalTestua.g:1066:4: ()
            	    // InternalTestua.g:1067:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getExpressionAccess().getOrLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,24,FOLLOW_21); 

            	    				newLeafNode(otherlv_2, grammarAccess.getExpressionAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalTestua.g:1077:4: ( (lv_right_3_0= ruleAnd ) )
            	    // InternalTestua.g:1078:5: (lv_right_3_0= ruleAnd )
            	    {
            	    // InternalTestua.g:1078:5: (lv_right_3_0= ruleAnd )
            	    // InternalTestua.g:1079:6: lv_right_3_0= ruleAnd
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
            	    							"m.Testua.And");
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
    // InternalTestua.g:1101:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // InternalTestua.g:1101:44: (iv_ruleAnd= ruleAnd EOF )
            // InternalTestua.g:1102:2: iv_ruleAnd= ruleAnd EOF
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
    // InternalTestua.g:1108:1: ruleAnd returns [EObject current=null] : (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PrimaryB_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1114:2: ( (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* ) )
            // InternalTestua.g:1115:2: (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* )
            {
            // InternalTestua.g:1115:2: (this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )* )
            // InternalTestua.g:1116:3: this_PrimaryB_0= rulePrimaryB ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndAccess().getPrimaryBParserRuleCall_0());
            		
            pushFollow(FOLLOW_29);
            this_PrimaryB_0=rulePrimaryB();

            state._fsp--;


            			current = this_PrimaryB_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1124:3: ( () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==25) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalTestua.g:1125:4: () otherlv_2= '&&' ( (lv_right_3_0= rulePrimaryB ) )
            	    {
            	    // InternalTestua.g:1125:4: ()
            	    // InternalTestua.g:1126:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,25,FOLLOW_21); 

            	    				newLeafNode(otherlv_2, grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalTestua.g:1136:4: ( (lv_right_3_0= rulePrimaryB ) )
            	    // InternalTestua.g:1137:5: (lv_right_3_0= rulePrimaryB )
            	    {
            	    // InternalTestua.g:1137:5: (lv_right_3_0= rulePrimaryB )
            	    // InternalTestua.g:1138:6: lv_right_3_0= rulePrimaryB
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
            	    							"m.Testua.PrimaryB");
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
    // InternalTestua.g:1160:1: entryRulePrimaryB returns [EObject current=null] : iv_rulePrimaryB= rulePrimaryB EOF ;
    public final EObject entryRulePrimaryB() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryB = null;


        try {
            // InternalTestua.g:1160:49: (iv_rulePrimaryB= rulePrimaryB EOF )
            // InternalTestua.g:1161:2: iv_rulePrimaryB= rulePrimaryB EOF
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
    // InternalTestua.g:1167:1: rulePrimaryB returns [EObject current=null] : (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) ) ;
    public final EObject rulePrimaryB() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AtomicB_0 = null;

        EObject lv_expression_3_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1173:2: ( (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) ) )
            // InternalTestua.g:1174:2: (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) )
            {
            // InternalTestua.g:1174:2: (this_AtomicB_0= ruleAtomicB | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID||LA26_0==21||LA26_0==37) ) {
                alt26=1;
            }
            else if ( (LA26_0==26) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // InternalTestua.g:1175:3: this_AtomicB_0= ruleAtomicB
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
                    // InternalTestua.g:1184:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) )
                    {
                    // InternalTestua.g:1184:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) ) )
                    // InternalTestua.g:1185:4: () otherlv_2= '!' ( (lv_expression_3_0= ruleAtomicB ) )
                    {
                    // InternalTestua.g:1185:4: ()
                    // InternalTestua.g:1186:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryBAccess().getNotAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_2=(Token)match(input,26,FOLLOW_30); 

                    				newLeafNode(otherlv_2, grammarAccess.getPrimaryBAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalTestua.g:1196:4: ( (lv_expression_3_0= ruleAtomicB ) )
                    // InternalTestua.g:1197:5: (lv_expression_3_0= ruleAtomicB )
                    {
                    // InternalTestua.g:1197:5: (lv_expression_3_0= ruleAtomicB )
                    // InternalTestua.g:1198:6: lv_expression_3_0= ruleAtomicB
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
                    							"m.Testua.AtomicB");
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
    // InternalTestua.g:1220:1: entryRuleAtomicB returns [EObject current=null] : iv_ruleAtomicB= ruleAtomicB EOF ;
    public final EObject entryRuleAtomicB() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicB = null;


        try {
            // InternalTestua.g:1220:48: (iv_ruleAtomicB= ruleAtomicB EOF )
            // InternalTestua.g:1221:2: iv_ruleAtomicB= ruleAtomicB EOF
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
    // InternalTestua.g:1227:1: ruleAtomicB returns [EObject current=null] : (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) ;
    public final EObject ruleAtomicB() throws RecognitionException {
        EObject current = null;

        EObject this_Addition_0 = null;

        Enumerator lv_type_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1233:2: ( (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) )
            // InternalTestua.g:1234:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            {
            // InternalTestua.g:1234:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            // InternalTestua.g:1235:3: this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            {

            			newCompositeNode(grammarAccess.getAtomicBAccess().getAdditionParserRuleCall_0());
            		
            pushFollow(FOLLOW_31);
            this_Addition_0=ruleAddition();

            state._fsp--;


            			current = this_Addition_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1243:3: ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=40 && LA27_0<=45)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalTestua.g:1244:4: () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) )
                    {
                    // InternalTestua.g:1244:4: ()
                    // InternalTestua.g:1245:5: 
                    {

                    					current = forceCreateModelElementAndSet(
                    						grammarAccess.getAtomicBAccess().getComparisonLeftAction_1_0(),
                    						current);
                    				

                    }

                    // InternalTestua.g:1251:4: ( (lv_type_2_0= ruleRelationType ) )
                    // InternalTestua.g:1252:5: (lv_type_2_0= ruleRelationType )
                    {
                    // InternalTestua.g:1252:5: (lv_type_2_0= ruleRelationType )
                    // InternalTestua.g:1253:6: lv_type_2_0= ruleRelationType
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
                    							"m.Testua.RelationType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalTestua.g:1270:4: ( (lv_right_3_0= ruleAddition ) )
                    // InternalTestua.g:1271:5: (lv_right_3_0= ruleAddition )
                    {
                    // InternalTestua.g:1271:5: (lv_right_3_0= ruleAddition )
                    // InternalTestua.g:1272:6: lv_right_3_0= ruleAddition
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
                    							"m.Testua.Addition");
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
    // InternalTestua.g:1294:1: entryRuleAddition returns [EObject current=null] : iv_ruleAddition= ruleAddition EOF ;
    public final EObject entryRuleAddition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddition = null;


        try {
            // InternalTestua.g:1294:49: (iv_ruleAddition= ruleAddition EOF )
            // InternalTestua.g:1295:2: iv_ruleAddition= ruleAddition EOF
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
    // InternalTestua.g:1301:1: ruleAddition returns [EObject current=null] : (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) ;
    public final EObject ruleAddition() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Multiplication_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1307:2: ( (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) )
            // InternalTestua.g:1308:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            {
            // InternalTestua.g:1308:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            // InternalTestua.g:1309:3: this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
            		
            pushFollow(FOLLOW_32);
            this_Multiplication_0=ruleMultiplication();

            state._fsp--;


            			current = this_Multiplication_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1317:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=27 && LA29_0<=28)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalTestua.g:1318:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) )
            	    {
            	    // InternalTestua.g:1318:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt28=2;
            	    int LA28_0 = input.LA(1);

            	    if ( (LA28_0==27) ) {
            	        alt28=1;
            	    }
            	    else if ( (LA28_0==28) ) {
            	        alt28=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 28, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt28) {
            	        case 1 :
            	            // InternalTestua.g:1319:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalTestua.g:1319:5: ( () otherlv_2= '+' )
            	            // InternalTestua.g:1320:6: () otherlv_2= '+'
            	            {
            	            // InternalTestua.g:1320:6: ()
            	            // InternalTestua.g:1321:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,27,FOLLOW_30); 

            	            						newLeafNode(otherlv_2, grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalTestua.g:1333:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalTestua.g:1333:5: ( () otherlv_4= '-' )
            	            // InternalTestua.g:1334:6: () otherlv_4= '-'
            	            {
            	            // InternalTestua.g:1334:6: ()
            	            // InternalTestua.g:1335:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,28,FOLLOW_30); 

            	            						newLeafNode(otherlv_4, grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalTestua.g:1347:4: ( (lv_right_5_0= ruleMultiplication ) )
            	    // InternalTestua.g:1348:5: (lv_right_5_0= ruleMultiplication )
            	    {
            	    // InternalTestua.g:1348:5: (lv_right_5_0= ruleMultiplication )
            	    // InternalTestua.g:1349:6: lv_right_5_0= ruleMultiplication
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
            	    							"m.Testua.Multiplication");
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
    // InternalTestua.g:1371:1: entryRuleMultiplication returns [EObject current=null] : iv_ruleMultiplication= ruleMultiplication EOF ;
    public final EObject entryRuleMultiplication() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplication = null;


        try {
            // InternalTestua.g:1371:55: (iv_ruleMultiplication= ruleMultiplication EOF )
            // InternalTestua.g:1372:2: iv_ruleMultiplication= ruleMultiplication EOF
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
    // InternalTestua.g:1378:1: ruleMultiplication returns [EObject current=null] : (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) ;
    public final EObject ruleMultiplication() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_Bitwise_0 = null;

        EObject lv_right_7_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1384:2: ( (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) )
            // InternalTestua.g:1385:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            {
            // InternalTestua.g:1385:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            // InternalTestua.g:1386:3: this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAccess().getBitwiseParserRuleCall_0());
            		
            pushFollow(FOLLOW_33);
            this_Bitwise_0=ruleBitwise();

            state._fsp--;


            			current = this_Bitwise_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1394:3: ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=29 && LA31_0<=31)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalTestua.g:1395:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) )
            	    {
            	    // InternalTestua.g:1395:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) )
            	    int alt30=3;
            	    switch ( input.LA(1) ) {
            	    case 29:
            	        {
            	        alt30=1;
            	        }
            	        break;
            	    case 30:
            	        {
            	        alt30=2;
            	        }
            	        break;
            	    case 31:
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
            	            // InternalTestua.g:1396:5: ( () otherlv_2= '*' )
            	            {
            	            // InternalTestua.g:1396:5: ( () otherlv_2= '*' )
            	            // InternalTestua.g:1397:6: () otherlv_2= '*'
            	            {
            	            // InternalTestua.g:1397:6: ()
            	            // InternalTestua.g:1398:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,29,FOLLOW_30); 

            	            						newLeafNode(otherlv_2, grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalTestua.g:1410:5: ( () otherlv_4= '/' )
            	            {
            	            // InternalTestua.g:1410:5: ( () otherlv_4= '/' )
            	            // InternalTestua.g:1411:6: () otherlv_4= '/'
            	            {
            	            // InternalTestua.g:1411:6: ()
            	            // InternalTestua.g:1412:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,30,FOLLOW_30); 

            	            						newLeafNode(otherlv_4, grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalTestua.g:1424:5: ( () otherlv_6= '%' )
            	            {
            	            // InternalTestua.g:1424:5: ( () otherlv_6= '%' )
            	            // InternalTestua.g:1425:6: () otherlv_6= '%'
            	            {
            	            // InternalTestua.g:1425:6: ()
            	            // InternalTestua.g:1426:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_6=(Token)match(input,31,FOLLOW_30); 

            	            						newLeafNode(otherlv_6, grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalTestua.g:1438:4: ( (lv_right_7_0= ruleBitwise ) )
            	    // InternalTestua.g:1439:5: (lv_right_7_0= ruleBitwise )
            	    {
            	    // InternalTestua.g:1439:5: (lv_right_7_0= ruleBitwise )
            	    // InternalTestua.g:1440:6: lv_right_7_0= ruleBitwise
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
            	    							"m.Testua.Bitwise");
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
    // InternalTestua.g:1462:1: entryRuleBitwise returns [EObject current=null] : iv_ruleBitwise= ruleBitwise EOF ;
    public final EObject entryRuleBitwise() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBitwise = null;


        try {
            // InternalTestua.g:1462:48: (iv_ruleBitwise= ruleBitwise EOF )
            // InternalTestua.g:1463:2: iv_ruleBitwise= ruleBitwise EOF
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
    // InternalTestua.g:1469:1: ruleBitwise returns [EObject current=null] : (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) ;
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
            // InternalTestua.g:1475:2: ( (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) )
            // InternalTestua.g:1476:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            {
            // InternalTestua.g:1476:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            // InternalTestua.g:1477:3: this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            {

            			newCompositeNode(grammarAccess.getBitwiseAccess().getUnaryParserRuleCall_0());
            		
            pushFollow(FOLLOW_34);
            this_Unary_0=ruleUnary();

            state._fsp--;


            			current = this_Unary_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalTestua.g:1485:3: ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( ((LA33_0>=32 && LA33_0<=36)) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalTestua.g:1486:4: () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) ) ( (lv_right_3_0= ruleUnary ) )
            	    {
            	    // InternalTestua.g:1486:4: ()
            	    // InternalTestua.g:1487:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalTestua.g:1493:4: ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) ) )
            	    // InternalTestua.g:1494:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) )
            	    {
            	    // InternalTestua.g:1494:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' ) )
            	    // InternalTestua.g:1495:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' )
            	    {
            	    // InternalTestua.g:1495:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '^' | lv_op_2_4= '<<' | lv_op_2_5= '>>' )
            	    int alt32=5;
            	    switch ( input.LA(1) ) {
            	    case 32:
            	        {
            	        alt32=1;
            	        }
            	        break;
            	    case 33:
            	        {
            	        alt32=2;
            	        }
            	        break;
            	    case 34:
            	        {
            	        alt32=3;
            	        }
            	        break;
            	    case 35:
            	        {
            	        alt32=4;
            	        }
            	        break;
            	    case 36:
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
            	            // InternalTestua.g:1496:7: lv_op_2_1= '&'
            	            {
            	            lv_op_2_1=(Token)match(input,32,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalTestua.g:1507:7: lv_op_2_2= '|'
            	            {
            	            lv_op_2_2=(Token)match(input,33,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalTestua.g:1518:7: lv_op_2_3= '^'
            	            {
            	            lv_op_2_3=(Token)match(input,34,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getBitwiseAccess().getOpCircumflexAccentKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalTestua.g:1529:7: lv_op_2_4= '<<'
            	            {
            	            lv_op_2_4=(Token)match(input,35,FOLLOW_30); 

            	            							newLeafNode(lv_op_2_4, grammarAccess.getBitwiseAccess().getOpLessThanSignLessThanSignKeyword_1_1_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getBitwiseRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_4, null);
            	            						

            	            }
            	            break;
            	        case 5 :
            	            // InternalTestua.g:1540:7: lv_op_2_5= '>>'
            	            {
            	            lv_op_2_5=(Token)match(input,36,FOLLOW_30); 

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

            	    // InternalTestua.g:1553:4: ( (lv_right_3_0= ruleUnary ) )
            	    // InternalTestua.g:1554:5: (lv_right_3_0= ruleUnary )
            	    {
            	    // InternalTestua.g:1554:5: (lv_right_3_0= ruleUnary )
            	    // InternalTestua.g:1555:6: lv_right_3_0= ruleUnary
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
            	    							"m.Testua.Unary");
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
    // InternalTestua.g:1577:1: entryRuleUnary returns [EObject current=null] : iv_ruleUnary= ruleUnary EOF ;
    public final EObject entryRuleUnary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary = null;


        try {
            // InternalTestua.g:1577:46: (iv_ruleUnary= ruleUnary EOF )
            // InternalTestua.g:1578:2: iv_ruleUnary= ruleUnary EOF
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
    // InternalTestua.g:1584:1: ruleUnary returns [EObject current=null] : ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) ) ;
    public final EObject ruleUnary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_2_0 = null;

        EObject this_Primary_3 = null;



        	enterRule();

        try {
            // InternalTestua.g:1590:2: ( ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) ) )
            // InternalTestua.g:1591:2: ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) )
            {
            // InternalTestua.g:1591:2: ( ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) ) | (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==37) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_ID||LA35_0==21) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalTestua.g:1592:3: ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) )
                    {
                    // InternalTestua.g:1592:3: ( () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) ) )
                    // InternalTestua.g:1593:4: () otherlv_1= '~' ( (lv_expression_2_0= rulePrimary ) )
                    {
                    // InternalTestua.g:1593:4: ()
                    // InternalTestua.g:1594:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getUnaryAccess().getBitwiseNegateAction_0_0(),
                    						current);
                    				

                    }

                    otherlv_1=(Token)match(input,37,FOLLOW_30); 

                    				newLeafNode(otherlv_1, grammarAccess.getUnaryAccess().getTildeKeyword_0_1());
                    			
                    // InternalTestua.g:1604:4: ( (lv_expression_2_0= rulePrimary ) )
                    // InternalTestua.g:1605:5: (lv_expression_2_0= rulePrimary )
                    {
                    // InternalTestua.g:1605:5: (lv_expression_2_0= rulePrimary )
                    // InternalTestua.g:1606:6: lv_expression_2_0= rulePrimary
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
                    							"m.Testua.Primary");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:1625:3: (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? )
                    {
                    // InternalTestua.g:1625:3: (this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )? )
                    // InternalTestua.g:1626:4: this_Primary_3= rulePrimary ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )?
                    {

                    				newCompositeNode(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_35);
                    this_Primary_3=rulePrimary();

                    state._fsp--;


                    				current = this_Primary_3;
                    				afterParserOrEnumRuleCall();
                    			
                    // InternalTestua.g:1634:4: ( ( () otherlv_5= '++' ) | ( () otherlv_7= '--' ) )?
                    int alt34=3;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==38) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==39) ) {
                        alt34=2;
                    }
                    switch (alt34) {
                        case 1 :
                            // InternalTestua.g:1635:5: ( () otherlv_5= '++' )
                            {
                            // InternalTestua.g:1635:5: ( () otherlv_5= '++' )
                            // InternalTestua.g:1636:6: () otherlv_5= '++'
                            {
                            // InternalTestua.g:1636:6: ()
                            // InternalTestua.g:1637:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getUnaryAccess().getIncrementExpressionAction_1_1_0_0(),
                            								current);
                            						

                            }

                            otherlv_5=(Token)match(input,38,FOLLOW_2); 

                            						newLeafNode(otherlv_5, grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_1_0_1());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalTestua.g:1649:5: ( () otherlv_7= '--' )
                            {
                            // InternalTestua.g:1649:5: ( () otherlv_7= '--' )
                            // InternalTestua.g:1650:6: () otherlv_7= '--'
                            {
                            // InternalTestua.g:1650:6: ()
                            // InternalTestua.g:1651:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getUnaryAccess().getDecrementExpressionAction_1_1_1_0(),
                            								current);
                            						

                            }

                            otherlv_7=(Token)match(input,39,FOLLOW_2); 

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
    // InternalTestua.g:1668:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalTestua.g:1668:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalTestua.g:1669:2: iv_rulePrimary= rulePrimary EOF
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
    // InternalTestua.g:1675:1: rulePrimary returns [EObject current=null] : (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject this_Access_0 = null;

        EObject this_Call_1 = null;

        EObject lv_expression_4_0 = null;



        	enterRule();

        try {
            // InternalTestua.g:1681:2: ( (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) ) )
            // InternalTestua.g:1682:2: (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            {
            // InternalTestua.g:1682:2: (this_Access_0= ruleAccess | this_Call_1= ruleCall | ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            int alt36=3;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==EOF||LA36_1==RULE_ID||(LA36_1>=8 && LA36_1<=10)||LA36_1==13||LA36_1==17||(LA36_1>=22 && LA36_1<=25)||(LA36_1>=27 && LA36_1<=36)||(LA36_1>=38 && LA36_1<=45)) ) {
                    alt36=1;
                }
                else if ( (LA36_1==21) ) {
                    alt36=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA36_0==21) ) {
                alt36=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // InternalTestua.g:1683:3: this_Access_0= ruleAccess
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
                    // InternalTestua.g:1692:3: this_Call_1= ruleCall
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
                    // InternalTestua.g:1701:3: ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' )
                    {
                    // InternalTestua.g:1701:3: ( () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' )
                    // InternalTestua.g:1702:4: () otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')'
                    {
                    // InternalTestua.g:1702:4: ()
                    // InternalTestua.g:1703:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryAccess().getBracketsAction_2_0(),
                    						current);
                    				

                    }

                    otherlv_3=(Token)match(input,21,FOLLOW_21); 

                    				newLeafNode(otherlv_3, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_2_1());
                    			
                    // InternalTestua.g:1713:4: ( (lv_expression_4_0= ruleExpression ) )
                    // InternalTestua.g:1714:5: (lv_expression_4_0= ruleExpression )
                    {
                    // InternalTestua.g:1714:5: (lv_expression_4_0= ruleExpression )
                    // InternalTestua.g:1715:6: lv_expression_4_0= ruleExpression
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
                    							"m.Testua.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_5=(Token)match(input,22,FOLLOW_2); 

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
    // InternalTestua.g:1741:1: ruleRelationType returns [Enumerator current=null] : ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) ;
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
            // InternalTestua.g:1747:2: ( ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) )
            // InternalTestua.g:1748:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            {
            // InternalTestua.g:1748:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            int alt37=6;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt37=1;
                }
                break;
            case 41:
                {
                alt37=2;
                }
                break;
            case 42:
                {
                alt37=3;
                }
                break;
            case 43:
                {
                alt37=4;
                }
                break;
            case 44:
                {
                alt37=5;
                }
                break;
            case 45:
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
                    // InternalTestua.g:1749:3: (enumLiteral_0= '>' )
                    {
                    // InternalTestua.g:1749:3: (enumLiteral_0= '>' )
                    // InternalTestua.g:1750:4: enumLiteral_0= '>'
                    {
                    enumLiteral_0=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:1757:3: (enumLiteral_1= '<' )
                    {
                    // InternalTestua.g:1757:3: (enumLiteral_1= '<' )
                    // InternalTestua.g:1758:4: enumLiteral_1= '<'
                    {
                    enumLiteral_1=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalTestua.g:1765:3: (enumLiteral_2= '=' )
                    {
                    // InternalTestua.g:1765:3: (enumLiteral_2= '=' )
                    // InternalTestua.g:1766:4: enumLiteral_2= '='
                    {
                    enumLiteral_2=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalTestua.g:1773:3: (enumLiteral_3= '!=' )
                    {
                    // InternalTestua.g:1773:3: (enumLiteral_3= '!=' )
                    // InternalTestua.g:1774:4: enumLiteral_3= '!='
                    {
                    enumLiteral_3=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalTestua.g:1781:3: (enumLiteral_4= '>=' )
                    {
                    // InternalTestua.g:1781:3: (enumLiteral_4= '>=' )
                    // InternalTestua.g:1782:4: enumLiteral_4= '>='
                    {
                    enumLiteral_4=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalTestua.g:1789:3: (enumLiteral_5= '<=' )
                    {
                    // InternalTestua.g:1789:3: (enumLiteral_5= '<=' )
                    // InternalTestua.g:1790:4: enumLiteral_5= '<='
                    {
                    enumLiteral_5=(Token)match(input,45,FOLLOW_2); 

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
    // InternalTestua.g:1800:1: ruleAssignmentType returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) ) ;
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
            // InternalTestua.g:1806:2: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) ) )
            // InternalTestua.g:1807:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) )
            {
            // InternalTestua.g:1807:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) )
            int alt38=11;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt38=1;
                }
                break;
            case 46:
                {
                alt38=2;
                }
                break;
            case 47:
                {
                alt38=3;
                }
                break;
            case 48:
                {
                alt38=4;
                }
                break;
            case 49:
                {
                alt38=5;
                }
                break;
            case 50:
                {
                alt38=6;
                }
                break;
            case 51:
                {
                alt38=7;
                }
                break;
            case 52:
                {
                alt38=8;
                }
                break;
            case 53:
                {
                alt38=9;
                }
                break;
            case 54:
                {
                alt38=10;
                }
                break;
            case 55:
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
                    // InternalTestua.g:1808:3: (enumLiteral_0= '=' )
                    {
                    // InternalTestua.g:1808:3: (enumLiteral_0= '=' )
                    // InternalTestua.g:1809:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalTestua.g:1816:3: (enumLiteral_1= '+=' )
                    {
                    // InternalTestua.g:1816:3: (enumLiteral_1= '+=' )
                    // InternalTestua.g:1817:4: enumLiteral_1= '+='
                    {
                    enumLiteral_1=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalTestua.g:1824:3: (enumLiteral_2= '-=' )
                    {
                    // InternalTestua.g:1824:3: (enumLiteral_2= '-=' )
                    // InternalTestua.g:1825:4: enumLiteral_2= '-='
                    {
                    enumLiteral_2=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalTestua.g:1832:3: (enumLiteral_3= '*=' )
                    {
                    // InternalTestua.g:1832:3: (enumLiteral_3= '*=' )
                    // InternalTestua.g:1833:4: enumLiteral_3= '*='
                    {
                    enumLiteral_3=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalTestua.g:1840:3: (enumLiteral_4= '/=' )
                    {
                    // InternalTestua.g:1840:3: (enumLiteral_4= '/=' )
                    // InternalTestua.g:1841:4: enumLiteral_4= '/='
                    {
                    enumLiteral_4=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalTestua.g:1848:3: (enumLiteral_5= '%=' )
                    {
                    // InternalTestua.g:1848:3: (enumLiteral_5= '%=' )
                    // InternalTestua.g:1849:4: enumLiteral_5= '%='
                    {
                    enumLiteral_5=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalTestua.g:1856:3: (enumLiteral_6= '&=' )
                    {
                    // InternalTestua.g:1856:3: (enumLiteral_6= '&=' )
                    // InternalTestua.g:1857:4: enumLiteral_6= '&='
                    {
                    enumLiteral_6=(Token)match(input,51,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalTestua.g:1864:3: (enumLiteral_7= '|=' )
                    {
                    // InternalTestua.g:1864:3: (enumLiteral_7= '|=' )
                    // InternalTestua.g:1865:4: enumLiteral_7= '|='
                    {
                    enumLiteral_7=(Token)match(input,52,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalTestua.g:1872:3: (enumLiteral_8= '^=' )
                    {
                    // InternalTestua.g:1872:3: (enumLiteral_8= '^=' )
                    // InternalTestua.g:1873:4: enumLiteral_8= '^='
                    {
                    enumLiteral_8=(Token)match(input,53,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getAssignmentTypeAccess().getXorEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalTestua.g:1880:3: (enumLiteral_9= '<<=' )
                    {
                    // InternalTestua.g:1880:3: (enumLiteral_9= '<<=' )
                    // InternalTestua.g:1881:4: enumLiteral_9= '<<='
                    {
                    enumLiteral_9=(Token)match(input,54,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getAssignmentTypeAccess().getShiftLeftEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalTestua.g:1888:3: (enumLiteral_10= '>>=' )
                    {
                    // InternalTestua.g:1888:3: (enumLiteral_10= '>>=' )
                    // InternalTestua.g:1889:4: enumLiteral_10= '>>='
                    {
                    enumLiteral_10=(Token)match(input,55,FOLLOW_2); 

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
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000008100L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000022410L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000002004222010L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x00FFC40000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000400200L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000002000222010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x00003F0000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00000000E0000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000001F00000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000400000L});

}