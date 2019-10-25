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
import m.services.CSGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalCSParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_LONG", "RULE_FLOAT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'using'", "';'", "'.'", "'sealed'", "':'", "','", "'{'", "'}'", "'static'", "'['", "'('", "')'", "']'", "'<'", "'>'", "'ref'", "'override'", "'implicit'", "'operator'", "'var'", "'++'", "'--'", "'return'", "'new'", "'for'", "'if'", "'else'", "'while'", "'='", "'=>'", "'||'", "'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'~'", "'class'", "'struct'", "'public'", "'private'", "'protected'", "'internal'", "'=='", "'!='", "'>='", "'<='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
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
    public static final int RULE_WS=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_FLOAT=6;
    public static final int T__46=46;
    public static final int RULE_LONG=5;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalCSParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCSParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalCSParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCS.g"; }



     	private CSGrammarAccess grammarAccess;

        public InternalCSParser(TokenStream input, CSGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "File";
       	}

       	@Override
       	protected CSGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleFile"
    // InternalCS.g:65:1: entryRuleFile returns [EObject current=null] : iv_ruleFile= ruleFile EOF ;
    public final EObject entryRuleFile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFile = null;


        try {
            // InternalCS.g:65:45: (iv_ruleFile= ruleFile EOF )
            // InternalCS.g:66:2: iv_ruleFile= ruleFile EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFileRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFile=ruleFile();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFile; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:72:1: ruleFile returns [EObject current=null] : ( ( (lv_usings_0_0= ruleUsing ) )* ( (lv_types_1_0= ruleMember ) )* ) ;
    public final EObject ruleFile() throws RecognitionException {
        EObject current = null;

        EObject lv_usings_0_0 = null;

        EObject lv_types_1_0 = null;



        	enterRule();

        try {
            // InternalCS.g:78:2: ( ( ( (lv_usings_0_0= ruleUsing ) )* ( (lv_types_1_0= ruleMember ) )* ) )
            // InternalCS.g:79:2: ( ( (lv_usings_0_0= ruleUsing ) )* ( (lv_types_1_0= ruleMember ) )* )
            {
            // InternalCS.g:79:2: ( ( (lv_usings_0_0= ruleUsing ) )* ( (lv_types_1_0= ruleMember ) )* )
            // InternalCS.g:80:3: ( (lv_usings_0_0= ruleUsing ) )* ( (lv_types_1_0= ruleMember ) )*
            {
            // InternalCS.g:80:3: ( (lv_usings_0_0= ruleUsing ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCS.g:81:4: (lv_usings_0_0= ruleUsing )
            	    {
            	    // InternalCS.g:81:4: (lv_usings_0_0= ruleUsing )
            	    // InternalCS.g:82:5: lv_usings_0_0= ruleUsing
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getFileAccess().getUsingsUsingParserRuleCall_0_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_3);
            	    lv_usings_0_0=ruleUsing();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getFileRule());
            	      					}
            	      					add(
            	      						current,
            	      						"usings",
            	      						lv_usings_0_0,
            	      						"m.CS.Using");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalCS.g:99:3: ( (lv_types_1_0= ruleMember ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==20||(LA2_0>=54 && LA2_0<=57)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCS.g:100:4: (lv_types_1_0= ruleMember )
            	    {
            	    // InternalCS.g:100:4: (lv_types_1_0= ruleMember )
            	    // InternalCS.g:101:5: lv_types_1_0= ruleMember
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getFileAccess().getTypesMemberParserRuleCall_1_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_4);
            	    lv_types_1_0=ruleMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getFileRule());
            	      					}
            	      					add(
            	      						current,
            	      						"types",
            	      						lv_types_1_0,
            	      						"m.CS.Member");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleFile"


    // $ANTLR start "entryRuleUsing"
    // InternalCS.g:122:1: entryRuleUsing returns [EObject current=null] : iv_ruleUsing= ruleUsing EOF ;
    public final EObject entryRuleUsing() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUsing = null;


        try {
            // InternalCS.g:122:46: (iv_ruleUsing= ruleUsing EOF )
            // InternalCS.g:123:2: iv_ruleUsing= ruleUsing EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUsingRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUsing=ruleUsing();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUsing; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleUsing"


    // $ANTLR start "ruleUsing"
    // InternalCS.g:129:1: ruleUsing returns [EObject current=null] : (otherlv_0= 'using' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' ) ;
    public final EObject ruleUsing() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalCS.g:135:2: ( (otherlv_0= 'using' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' ) )
            // InternalCS.g:136:2: (otherlv_0= 'using' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' )
            {
            // InternalCS.g:136:2: (otherlv_0= 'using' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';' )
            // InternalCS.g:137:3: otherlv_0= 'using' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getUsingAccess().getUsingKeyword_0());
              		
            }
            // InternalCS.g:141:3: ( (lv_name_1_0= ruleQualifiedName ) )
            // InternalCS.g:142:4: (lv_name_1_0= ruleQualifiedName )
            {
            // InternalCS.g:142:4: (lv_name_1_0= ruleQualifiedName )
            // InternalCS.g:143:5: lv_name_1_0= ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getUsingAccess().getNameQualifiedNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_6);
            lv_name_1_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getUsingRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"m.CS.QualifiedName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getUsingAccess().getSemicolonKeyword_2());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleUsing"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalCS.g:168:1: entryRuleQualifiedName returns [EObject current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final EObject entryRuleQualifiedName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedName = null;


        try {
            // InternalCS.g:168:54: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalCS.g:169:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalCS.g:175:1: ruleQualifiedName returns [EObject current=null] : ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) ;
    public final EObject ruleQualifiedName() throws RecognitionException {
        EObject current = null;

        Token lv_names_0_0=null;
        Token otherlv_1=null;
        Token lv_names_2_0=null;


        	enterRule();

        try {
            // InternalCS.g:181:2: ( ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* ) )
            // InternalCS.g:182:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            {
            // InternalCS.g:182:2: ( ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )* )
            // InternalCS.g:183:3: ( (lv_names_0_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            {
            // InternalCS.g:183:3: ( (lv_names_0_0= RULE_ID ) )
            // InternalCS.g:184:4: (lv_names_0_0= RULE_ID )
            {
            // InternalCS.g:184:4: (lv_names_0_0= RULE_ID )
            // InternalCS.g:185:5: lv_names_0_0= RULE_ID
            {
            lv_names_0_0=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_names_0_0, grammarAccess.getQualifiedNameAccess().getNamesIDTerminalRuleCall_0_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getQualifiedNameRule());
              					}
              					addWithLastConsumed(
              						current,
              						"names",
              						lv_names_0_0,
              						"m.CS.ID");
              				
            }

            }


            }

            // InternalCS.g:201:3: (otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==13) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalCS.g:202:4: otherlv_1= '.' ( (lv_names_2_0= RULE_ID ) )
            	    {
            	    otherlv_1=(Token)match(input,13,FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	      			
            	    }
            	    // InternalCS.g:206:4: ( (lv_names_2_0= RULE_ID ) )
            	    // InternalCS.g:207:5: (lv_names_2_0= RULE_ID )
            	    {
            	    // InternalCS.g:207:5: (lv_names_2_0= RULE_ID )
            	    // InternalCS.g:208:6: lv_names_2_0= RULE_ID
            	    {
            	    lv_names_2_0=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_names_2_0, grammarAccess.getQualifiedNameAccess().getNamesIDTerminalRuleCall_1_1_0());
            	      					
            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getQualifiedNameRule());
            	      						}
            	      						addWithLastConsumed(
            	      							current,
            	      							"names",
            	      							lv_names_2_0,
            	      							"m.CS.ID");
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleType"
    // InternalCS.g:229:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalCS.g:229:45: (iv_ruleType= ruleType EOF )
            // InternalCS.g:230:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalCS.g:236:1: ruleType returns [EObject current=null] : ( ( (lv_sealed_0_0= 'sealed' ) )? ( (lv_type_1_0= ruleTypeEnum ) ) ( (lv_name_2_0= ruleTypeName ) ) (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )? otherlv_7= '{' ( (lv_members_8_0= ruleMember ) )* otherlv_9= '}' ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        Token lv_sealed_0_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Enumerator lv_type_1_0 = null;

        EObject lv_name_2_0 = null;

        EObject lv_superTypes_4_0 = null;

        EObject lv_superTypes_6_0 = null;

        EObject lv_members_8_0 = null;



        	enterRule();

        try {
            // InternalCS.g:242:2: ( ( ( (lv_sealed_0_0= 'sealed' ) )? ( (lv_type_1_0= ruleTypeEnum ) ) ( (lv_name_2_0= ruleTypeName ) ) (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )? otherlv_7= '{' ( (lv_members_8_0= ruleMember ) )* otherlv_9= '}' ) )
            // InternalCS.g:243:2: ( ( (lv_sealed_0_0= 'sealed' ) )? ( (lv_type_1_0= ruleTypeEnum ) ) ( (lv_name_2_0= ruleTypeName ) ) (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )? otherlv_7= '{' ( (lv_members_8_0= ruleMember ) )* otherlv_9= '}' )
            {
            // InternalCS.g:243:2: ( ( (lv_sealed_0_0= 'sealed' ) )? ( (lv_type_1_0= ruleTypeEnum ) ) ( (lv_name_2_0= ruleTypeName ) ) (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )? otherlv_7= '{' ( (lv_members_8_0= ruleMember ) )* otherlv_9= '}' )
            // InternalCS.g:244:3: ( (lv_sealed_0_0= 'sealed' ) )? ( (lv_type_1_0= ruleTypeEnum ) ) ( (lv_name_2_0= ruleTypeName ) ) (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )? otherlv_7= '{' ( (lv_members_8_0= ruleMember ) )* otherlv_9= '}'
            {
            // InternalCS.g:244:3: ( (lv_sealed_0_0= 'sealed' ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCS.g:245:4: (lv_sealed_0_0= 'sealed' )
                    {
                    // InternalCS.g:245:4: (lv_sealed_0_0= 'sealed' )
                    // InternalCS.g:246:5: lv_sealed_0_0= 'sealed'
                    {
                    lv_sealed_0_0=(Token)match(input,14,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_sealed_0_0, grammarAccess.getTypeAccess().getSealedSealedKeyword_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getTypeRule());
                      					}
                      					setWithLastConsumed(current, "sealed", true, "sealed");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:258:3: ( (lv_type_1_0= ruleTypeEnum ) )
            // InternalCS.g:259:4: (lv_type_1_0= ruleTypeEnum )
            {
            // InternalCS.g:259:4: (lv_type_1_0= ruleTypeEnum )
            // InternalCS.g:260:5: lv_type_1_0= ruleTypeEnum
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeAccess().getTypeTypeEnumEnumRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_type_1_0=ruleTypeEnum();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getTypeRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_1_0,
              						"m.CS.TypeEnum");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:277:3: ( (lv_name_2_0= ruleTypeName ) )
            // InternalCS.g:278:4: (lv_name_2_0= ruleTypeName )
            {
            // InternalCS.g:278:4: (lv_name_2_0= ruleTypeName )
            // InternalCS.g:279:5: lv_name_2_0= ruleTypeName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeAccess().getNameTypeNameParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_9);
            lv_name_2_0=ruleTypeName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getTypeRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"m.CS.TypeName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:296:3: (otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalCS.g:297:4: otherlv_3= ':' ( (lv_superTypes_4_0= ruleTypeName ) ) (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )*
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getTypeAccess().getColonKeyword_3_0());
                      			
                    }
                    // InternalCS.g:301:4: ( (lv_superTypes_4_0= ruleTypeName ) )
                    // InternalCS.g:302:5: (lv_superTypes_4_0= ruleTypeName )
                    {
                    // InternalCS.g:302:5: (lv_superTypes_4_0= ruleTypeName )
                    // InternalCS.g:303:6: lv_superTypes_4_0= ruleTypeName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTypeAccess().getSuperTypesTypeNameParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_10);
                    lv_superTypes_4_0=ruleTypeName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getTypeRule());
                      						}
                      						add(
                      							current,
                      							"superTypes",
                      							lv_superTypes_4_0,
                      							"m.CS.TypeName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:320:4: (otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==16) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalCS.g:321:5: otherlv_5= ',' ( (lv_superTypes_6_0= ruleTypeName ) )
                    	    {
                    	    otherlv_5=(Token)match(input,16,FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getTypeAccess().getCommaKeyword_3_2_0());
                    	      				
                    	    }
                    	    // InternalCS.g:325:5: ( (lv_superTypes_6_0= ruleTypeName ) )
                    	    // InternalCS.g:326:6: (lv_superTypes_6_0= ruleTypeName )
                    	    {
                    	    // InternalCS.g:326:6: (lv_superTypes_6_0= ruleTypeName )
                    	    // InternalCS.g:327:7: lv_superTypes_6_0= ruleTypeName
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTypeAccess().getSuperTypesTypeNameParserRuleCall_3_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_10);
                    	    lv_superTypes_6_0=ruleTypeName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getTypeRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"superTypes",
                    	      								lv_superTypes_6_0,
                    	      								"m.CS.TypeName");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

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

            otherlv_7=(Token)match(input,17,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getTypeAccess().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalCS.g:350:3: ( (lv_members_8_0= ruleMember ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==20||(LA7_0>=54 && LA7_0<=57)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalCS.g:351:4: (lv_members_8_0= ruleMember )
            	    {
            	    // InternalCS.g:351:4: (lv_members_8_0= ruleMember )
            	    // InternalCS.g:352:5: lv_members_8_0= ruleMember
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getTypeAccess().getMembersMemberParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_11);
            	    lv_members_8_0=ruleMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getTypeRule());
            	      					}
            	      					add(
            	      						current,
            	      						"members",
            	      						lv_members_8_0,
            	      						"m.CS.Member");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_9=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getTypeAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleMember"
    // InternalCS.g:377:1: entryRuleMember returns [EObject current=null] : iv_ruleMember= ruleMember EOF ;
    public final EObject entryRuleMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMember = null;


        try {
            // InternalCS.g:377:47: (iv_ruleMember= ruleMember EOF )
            // InternalCS.g:378:2: iv_ruleMember= ruleMember EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMemberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMember=ruleMember();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMember; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleMember"


    // $ANTLR start "ruleMember"
    // InternalCS.g:384:1: ruleMember returns [EObject current=null] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_visibility_1_0= ruleVisibility ) ) ( (lv_static_2_0= 'static' ) )? ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) ) ) ;
    public final EObject ruleMember() throws RecognitionException {
        EObject current = null;

        Token lv_static_2_0=null;
        EObject lv_annotations_0_0 = null;

        Enumerator lv_visibility_1_0 = null;

        EObject lv_value_3_1 = null;

        EObject lv_value_3_2 = null;

        EObject lv_value_3_3 = null;



        	enterRule();

        try {
            // InternalCS.g:390:2: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_visibility_1_0= ruleVisibility ) ) ( (lv_static_2_0= 'static' ) )? ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) ) ) )
            // InternalCS.g:391:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_visibility_1_0= ruleVisibility ) ) ( (lv_static_2_0= 'static' ) )? ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) ) )
            {
            // InternalCS.g:391:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_visibility_1_0= ruleVisibility ) ) ( (lv_static_2_0= 'static' ) )? ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) ) )
            // InternalCS.g:392:3: ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_visibility_1_0= ruleVisibility ) ) ( (lv_static_2_0= 'static' ) )? ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) )
            {
            // InternalCS.g:392:3: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==20) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalCS.g:393:4: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // InternalCS.g:393:4: (lv_annotations_0_0= ruleAnnotation )
            	    // InternalCS.g:394:5: lv_annotations_0_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getMemberAccess().getAnnotationsAnnotationParserRuleCall_0_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_12);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getMemberRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annotations",
            	      						lv_annotations_0_0,
            	      						"m.CS.Annotation");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalCS.g:411:3: ( (lv_visibility_1_0= ruleVisibility ) )
            // InternalCS.g:412:4: (lv_visibility_1_0= ruleVisibility )
            {
            // InternalCS.g:412:4: (lv_visibility_1_0= ruleVisibility )
            // InternalCS.g:413:5: lv_visibility_1_0= ruleVisibility
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getMemberAccess().getVisibilityVisibilityEnumRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_13);
            lv_visibility_1_0=ruleVisibility();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getMemberRule());
              					}
              					set(
              						current,
              						"visibility",
              						lv_visibility_1_0,
              						"m.CS.Visibility");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:430:3: ( (lv_static_2_0= 'static' ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalCS.g:431:4: (lv_static_2_0= 'static' )
                    {
                    // InternalCS.g:431:4: (lv_static_2_0= 'static' )
                    // InternalCS.g:432:5: lv_static_2_0= 'static'
                    {
                    lv_static_2_0=(Token)match(input,19,FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_static_2_0, grammarAccess.getMemberAccess().getStaticStaticKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getMemberRule());
                      					}
                      					setWithLastConsumed(current, "static", true, "static");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:444:3: ( ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) ) )
            // InternalCS.g:445:4: ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) )
            {
            // InternalCS.g:445:4: ( (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField ) )
            // InternalCS.g:446:5: (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField )
            {
            // InternalCS.g:446:5: (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField )
            int alt10=3;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalCS.g:447:6: lv_value_3_1= ruleType
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMemberAccess().getValueTypeParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_value_3_1=ruleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMemberRule());
                      						}
                      						set(
                      							current,
                      							"value",
                      							lv_value_3_1,
                      							"m.CS.Type");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 2 :
                    // InternalCS.g:463:6: lv_value_3_2= ruleMethod
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMemberAccess().getValueMethodParserRuleCall_3_0_1());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_value_3_2=ruleMethod();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMemberRule());
                      						}
                      						set(
                      							current,
                      							"value",
                      							lv_value_3_2,
                      							"m.CS.Method");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 3 :
                    // InternalCS.g:479:6: lv_value_3_3= ruleField
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMemberAccess().getValueFieldParserRuleCall_3_0_2());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_value_3_3=ruleField();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMemberRule());
                      						}
                      						set(
                      							current,
                      							"value",
                      							lv_value_3_3,
                      							"m.CS.Field");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;

            }


            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleMember"


    // $ANTLR start "entryRuleAnnotation"
    // InternalCS.g:501:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // InternalCS.g:501:51: (iv_ruleAnnotation= ruleAnnotation EOF )
            // InternalCS.g:502:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnnotationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAnnotation=ruleAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnnotation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // InternalCS.g:508:1: ruleAnnotation returns [EObject current=null] : (otherlv_0= '[' ( (lv_function_1_0= ruleTypeName ) ) (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )? otherlv_7= ']' ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_function_1_0 = null;

        EObject lv_parameters_3_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalCS.g:514:2: ( (otherlv_0= '[' ( (lv_function_1_0= ruleTypeName ) ) (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )? otherlv_7= ']' ) )
            // InternalCS.g:515:2: (otherlv_0= '[' ( (lv_function_1_0= ruleTypeName ) ) (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )? otherlv_7= ']' )
            {
            // InternalCS.g:515:2: (otherlv_0= '[' ( (lv_function_1_0= ruleTypeName ) ) (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )? otherlv_7= ']' )
            // InternalCS.g:516:3: otherlv_0= '[' ( (lv_function_1_0= ruleTypeName ) ) (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )? otherlv_7= ']'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAnnotationAccess().getLeftSquareBracketKeyword_0());
              		
            }
            // InternalCS.g:520:3: ( (lv_function_1_0= ruleTypeName ) )
            // InternalCS.g:521:4: (lv_function_1_0= ruleTypeName )
            {
            // InternalCS.g:521:4: (lv_function_1_0= ruleTypeName )
            // InternalCS.g:522:5: lv_function_1_0= ruleTypeName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAnnotationAccess().getFunctionTypeNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_14);
            lv_function_1_0=ruleTypeName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAnnotationRule());
              					}
              					set(
              						current,
              						"function",
              						lv_function_1_0,
              						"m.CS.TypeName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:539:3: (otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCS.g:540:4: otherlv_2= '(' ( (lv_parameters_3_0= ruleExpression ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )* otherlv_6= ')'
                    {
                    otherlv_2=(Token)match(input,21,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_2_0());
                      			
                    }
                    // InternalCS.g:544:4: ( (lv_parameters_3_0= ruleExpression ) )
                    // InternalCS.g:545:5: (lv_parameters_3_0= ruleExpression )
                    {
                    // InternalCS.g:545:5: (lv_parameters_3_0= ruleExpression )
                    // InternalCS.g:546:6: lv_parameters_3_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getAnnotationAccess().getParametersExpressionParserRuleCall_2_1_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
                    lv_parameters_3_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getAnnotationRule());
                      						}
                      						add(
                      							current,
                      							"parameters",
                      							lv_parameters_3_0,
                      							"m.CS.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:563:4: (otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==16) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalCS.g:564:5: otherlv_4= ',' ( (lv_parameters_5_0= ruleExpression ) )
                    	    {
                    	    otherlv_4=(Token)match(input,16,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_4, grammarAccess.getAnnotationAccess().getCommaKeyword_2_2_0());
                    	      				
                    	    }
                    	    // InternalCS.g:568:5: ( (lv_parameters_5_0= ruleExpression ) )
                    	    // InternalCS.g:569:6: (lv_parameters_5_0= ruleExpression )
                    	    {
                    	    // InternalCS.g:569:6: (lv_parameters_5_0= ruleExpression )
                    	    // InternalCS.g:570:7: lv_parameters_5_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getAnnotationAccess().getParametersExpressionParserRuleCall_2_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_16);
                    	    lv_parameters_5_0=ruleExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getAnnotationRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"parameters",
                    	      								lv_parameters_5_0,
                    	      								"m.CS.Expression");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_2_3());
                      			
                    }

                    }
                    break;

            }

            otherlv_7=(Token)match(input,23,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getAnnotationAccess().getRightSquareBracketKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleTypeName"
    // InternalCS.g:601:1: entryRuleTypeName returns [EObject current=null] : iv_ruleTypeName= ruleTypeName EOF ;
    public final EObject entryRuleTypeName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeName = null;


        try {
            // InternalCS.g:601:49: (iv_ruleTypeName= ruleTypeName EOF )
            // InternalCS.g:602:2: iv_ruleTypeName= ruleTypeName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeName=ruleTypeName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeName; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTypeName"


    // $ANTLR start "ruleTypeName"
    // InternalCS.g:608:1: ruleTypeName returns [EObject current=null] : ( ( (lv_name_0_0= ruleQualifiedName ) ) (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )? ) ;
    public final EObject ruleTypeName() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_name_0_0 = null;

        EObject lv_generics_2_0 = null;

        EObject lv_generics_4_0 = null;



        	enterRule();

        try {
            // InternalCS.g:614:2: ( ( ( (lv_name_0_0= ruleQualifiedName ) ) (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )? ) )
            // InternalCS.g:615:2: ( ( (lv_name_0_0= ruleQualifiedName ) ) (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )? )
            {
            // InternalCS.g:615:2: ( ( (lv_name_0_0= ruleQualifiedName ) ) (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )? )
            // InternalCS.g:616:3: ( (lv_name_0_0= ruleQualifiedName ) ) (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )?
            {
            // InternalCS.g:616:3: ( (lv_name_0_0= ruleQualifiedName ) )
            // InternalCS.g:617:4: (lv_name_0_0= ruleQualifiedName )
            {
            // InternalCS.g:617:4: (lv_name_0_0= ruleQualifiedName )
            // InternalCS.g:618:5: lv_name_0_0= ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeNameAccess().getNameQualifiedNameParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_18);
            lv_name_0_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getTypeNameRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_0_0,
              						"m.CS.QualifiedName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:635:3: (otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==24) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCS.g:636:4: otherlv_1= '<' ( (lv_generics_2_0= ruleQualifiedName ) ) (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )* otherlv_5= '>'
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTypeNameAccess().getLessThanSignKeyword_1_0());
                      			
                    }
                    // InternalCS.g:640:4: ( (lv_generics_2_0= ruleQualifiedName ) )
                    // InternalCS.g:641:5: (lv_generics_2_0= ruleQualifiedName )
                    {
                    // InternalCS.g:641:5: (lv_generics_2_0= ruleQualifiedName )
                    // InternalCS.g:642:6: lv_generics_2_0= ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTypeNameAccess().getGenericsQualifiedNameParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_19);
                    lv_generics_2_0=ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getTypeNameRule());
                      						}
                      						add(
                      							current,
                      							"generics",
                      							lv_generics_2_0,
                      							"m.CS.QualifiedName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:659:4: (otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==16) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalCS.g:660:5: otherlv_3= ',' ( (lv_generics_4_0= ruleQualifiedName ) )
                    	    {
                    	    otherlv_3=(Token)match(input,16,FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getTypeNameAccess().getCommaKeyword_1_2_0());
                    	      				
                    	    }
                    	    // InternalCS.g:664:5: ( (lv_generics_4_0= ruleQualifiedName ) )
                    	    // InternalCS.g:665:6: (lv_generics_4_0= ruleQualifiedName )
                    	    {
                    	    // InternalCS.g:665:6: (lv_generics_4_0= ruleQualifiedName )
                    	    // InternalCS.g:666:7: lv_generics_4_0= ruleQualifiedName
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTypeNameAccess().getGenericsQualifiedNameParserRuleCall_1_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_19);
                    	    lv_generics_4_0=ruleQualifiedName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getTypeNameRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"generics",
                    	      								lv_generics_4_0,
                    	      								"m.CS.QualifiedName");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getTypeNameAccess().getGreaterThanSignKeyword_1_3());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleTypeName"


    // $ANTLR start "entryRuleField"
    // InternalCS.g:693:1: entryRuleField returns [EObject current=null] : iv_ruleField= ruleField EOF ;
    public final EObject entryRuleField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleField = null;


        try {
            // InternalCS.g:693:46: (iv_ruleField= ruleField EOF )
            // InternalCS.g:694:2: iv_ruleField= ruleField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFieldRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleField=ruleField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleField; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:700:1: ruleField returns [EObject current=null] : ( ( (lv_type_0_0= ruleTypeName ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) ;
    public final EObject ruleField() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token otherlv_2=null;
        EObject lv_type_0_0 = null;



        	enterRule();

        try {
            // InternalCS.g:706:2: ( ( ( (lv_type_0_0= ruleTypeName ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) )
            // InternalCS.g:707:2: ( ( (lv_type_0_0= ruleTypeName ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            {
            // InternalCS.g:707:2: ( ( (lv_type_0_0= ruleTypeName ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
            // InternalCS.g:708:3: ( (lv_type_0_0= ruleTypeName ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';'
            {
            // InternalCS.g:708:3: ( (lv_type_0_0= ruleTypeName ) )
            // InternalCS.g:709:4: (lv_type_0_0= ruleTypeName )
            {
            // InternalCS.g:709:4: (lv_type_0_0= ruleTypeName )
            // InternalCS.g:710:5: lv_type_0_0= ruleTypeName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getFieldAccess().getTypeTypeNameParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_type_0_0=ruleTypeName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getFieldRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_0_0,
              						"m.CS.TypeName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:727:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalCS.g:728:4: (lv_name_1_0= RULE_ID )
            {
            // InternalCS.g:728:4: (lv_name_1_0= RULE_ID )
            // InternalCS.g:729:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_1_0, grammarAccess.getFieldAccess().getNameIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getFieldRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_1_0,
              						"m.CS.ID");
              				
            }

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getFieldAccess().getSemicolonKeyword_2());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleField"


    // $ANTLR start "entryRuleArgument"
    // InternalCS.g:753:1: entryRuleArgument returns [EObject current=null] : iv_ruleArgument= ruleArgument EOF ;
    public final EObject entryRuleArgument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArgument = null;


        try {
            // InternalCS.g:753:49: (iv_ruleArgument= ruleArgument EOF )
            // InternalCS.g:754:2: iv_ruleArgument= ruleArgument EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgumentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArgument=ruleArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArgument; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleArgument"


    // $ANTLR start "ruleArgument"
    // InternalCS.g:760:1: ruleArgument returns [EObject current=null] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_ref_1_0= 'ref' ) )? ( (lv_type_2_0= ruleTypeName ) ) ( (lv_name_3_0= RULE_ID ) ) ) ;
    public final EObject ruleArgument() throws RecognitionException {
        EObject current = null;

        Token lv_ref_1_0=null;
        Token lv_name_3_0=null;
        EObject lv_annotations_0_0 = null;

        EObject lv_type_2_0 = null;



        	enterRule();

        try {
            // InternalCS.g:766:2: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_ref_1_0= 'ref' ) )? ( (lv_type_2_0= ruleTypeName ) ) ( (lv_name_3_0= RULE_ID ) ) ) )
            // InternalCS.g:767:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_ref_1_0= 'ref' ) )? ( (lv_type_2_0= ruleTypeName ) ) ( (lv_name_3_0= RULE_ID ) ) )
            {
            // InternalCS.g:767:2: ( ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_ref_1_0= 'ref' ) )? ( (lv_type_2_0= ruleTypeName ) ) ( (lv_name_3_0= RULE_ID ) ) )
            // InternalCS.g:768:3: ( (lv_annotations_0_0= ruleAnnotation ) )* ( (lv_ref_1_0= 'ref' ) )? ( (lv_type_2_0= ruleTypeName ) ) ( (lv_name_3_0= RULE_ID ) )
            {
            // InternalCS.g:768:3: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==20) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalCS.g:769:4: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // InternalCS.g:769:4: (lv_annotations_0_0= ruleAnnotation )
            	    // InternalCS.g:770:5: lv_annotations_0_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getArgumentAccess().getAnnotationsAnnotationParserRuleCall_0_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_20);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getArgumentRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annotations",
            	      						lv_annotations_0_0,
            	      						"m.CS.Annotation");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // InternalCS.g:787:3: ( (lv_ref_1_0= 'ref' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==26) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalCS.g:788:4: (lv_ref_1_0= 'ref' )
                    {
                    // InternalCS.g:788:4: (lv_ref_1_0= 'ref' )
                    // InternalCS.g:789:5: lv_ref_1_0= 'ref'
                    {
                    lv_ref_1_0=(Token)match(input,26,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_ref_1_0, grammarAccess.getArgumentAccess().getRefRefKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getArgumentRule());
                      					}
                      					setWithLastConsumed(current, "ref", true, "ref");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:801:3: ( (lv_type_2_0= ruleTypeName ) )
            // InternalCS.g:802:4: (lv_type_2_0= ruleTypeName )
            {
            // InternalCS.g:802:4: (lv_type_2_0= ruleTypeName )
            // InternalCS.g:803:5: lv_type_2_0= ruleTypeName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getArgumentAccess().getTypeTypeNameParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_type_2_0=ruleTypeName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getArgumentRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_2_0,
              						"m.CS.TypeName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:820:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalCS.g:821:4: (lv_name_3_0= RULE_ID )
            {
            // InternalCS.g:821:4: (lv_name_3_0= RULE_ID )
            // InternalCS.g:822:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_3_0, grammarAccess.getArgumentAccess().getNameIDTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getArgumentRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_3_0,
              						"m.CS.ID");
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleArgument"


    // $ANTLR start "entryRuleMethod"
    // InternalCS.g:842:1: entryRuleMethod returns [EObject current=null] : iv_ruleMethod= ruleMethod EOF ;
    public final EObject entryRuleMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethod = null;


        try {
            // InternalCS.g:842:47: (iv_ruleMethod= ruleMethod EOF )
            // InternalCS.g:843:2: iv_ruleMethod= ruleMethod EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMethodRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMethod=ruleMethod();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMethod; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // InternalCS.g:849:1: ruleMethod returns [EObject current=null] : ( () ( (lv_override_1_0= 'override' ) )? ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )? otherlv_11= '(' ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )? otherlv_15= ')' otherlv_16= '{' ( (lv_commands_17_0= ruleCommand ) )* otherlv_18= '}' ) ;
    public final EObject ruleMethod() throws RecognitionException {
        EObject current = null;

        Token lv_override_1_0=null;
        Token lv_implicit_3_0=null;
        Token lv_operator_4_0=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_type_2_0 = null;

        EObject lv_generics_7_0 = null;

        EObject lv_generics_9_0 = null;

        EObject lv_arguments_12_0 = null;

        EObject lv_arguments_14_0 = null;

        EObject lv_commands_17_0 = null;



        	enterRule();

        try {
            // InternalCS.g:855:2: ( ( () ( (lv_override_1_0= 'override' ) )? ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )? otherlv_11= '(' ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )? otherlv_15= ')' otherlv_16= '{' ( (lv_commands_17_0= ruleCommand ) )* otherlv_18= '}' ) )
            // InternalCS.g:856:2: ( () ( (lv_override_1_0= 'override' ) )? ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )? otherlv_11= '(' ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )? otherlv_15= ')' otherlv_16= '{' ( (lv_commands_17_0= ruleCommand ) )* otherlv_18= '}' )
            {
            // InternalCS.g:856:2: ( () ( (lv_override_1_0= 'override' ) )? ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )? otherlv_11= '(' ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )? otherlv_15= ')' otherlv_16= '{' ( (lv_commands_17_0= ruleCommand ) )* otherlv_18= '}' )
            // InternalCS.g:857:3: () ( (lv_override_1_0= 'override' ) )? ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )? otherlv_11= '(' ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )? otherlv_15= ')' otherlv_16= '{' ( (lv_commands_17_0= ruleCommand ) )* otherlv_18= '}'
            {
            // InternalCS.g:857:3: ()
            // InternalCS.g:858:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getMethodAccess().getMethodAction_0(),
              					current);
              			
            }

            }

            // InternalCS.g:864:3: ( (lv_override_1_0= 'override' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCS.g:865:4: (lv_override_1_0= 'override' )
                    {
                    // InternalCS.g:865:4: (lv_override_1_0= 'override' )
                    // InternalCS.g:866:5: lv_override_1_0= 'override'
                    {
                    lv_override_1_0=(Token)match(input,27,FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_override_1_0, grammarAccess.getMethodAccess().getOverrideOverrideKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getMethodRule());
                      					}
                      					setWithLastConsumed(current, "override", true, "override");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:878:3: ( ( (lv_type_2_0= ruleTypeName ) ) | ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                alt18=1;
            }
            else if ( (LA18_0==28) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalCS.g:879:4: ( (lv_type_2_0= ruleTypeName ) )
                    {
                    // InternalCS.g:879:4: ( (lv_type_2_0= ruleTypeName ) )
                    // InternalCS.g:880:5: (lv_type_2_0= ruleTypeName )
                    {
                    // InternalCS.g:880:5: (lv_type_2_0= ruleTypeName )
                    // InternalCS.g:881:6: lv_type_2_0= ruleTypeName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMethodAccess().getTypeTypeNameParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_5);
                    lv_type_2_0=ruleTypeName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMethodRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_2_0,
                      							"m.CS.TypeName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:899:4: ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) )
                    {
                    // InternalCS.g:899:4: ( ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) ) )
                    // InternalCS.g:900:5: ( (lv_implicit_3_0= 'implicit' ) ) ( (lv_operator_4_0= 'operator' ) )
                    {
                    // InternalCS.g:900:5: ( (lv_implicit_3_0= 'implicit' ) )
                    // InternalCS.g:901:6: (lv_implicit_3_0= 'implicit' )
                    {
                    // InternalCS.g:901:6: (lv_implicit_3_0= 'implicit' )
                    // InternalCS.g:902:7: lv_implicit_3_0= 'implicit'
                    {
                    lv_implicit_3_0=(Token)match(input,28,FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(lv_implicit_3_0, grammarAccess.getMethodAccess().getImplicitImplicitKeyword_2_1_0_0());
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getMethodRule());
                      							}
                      							setWithLastConsumed(current, "implicit", true, "implicit");
                      						
                    }

                    }


                    }

                    // InternalCS.g:914:5: ( (lv_operator_4_0= 'operator' ) )
                    // InternalCS.g:915:6: (lv_operator_4_0= 'operator' )
                    {
                    // InternalCS.g:915:6: (lv_operator_4_0= 'operator' )
                    // InternalCS.g:916:7: lv_operator_4_0= 'operator'
                    {
                    lv_operator_4_0=(Token)match(input,29,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(lv_operator_4_0, grammarAccess.getMethodAccess().getOperatorOperatorKeyword_2_1_1_0());
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getMethodRule());
                      							}
                      							setWithLastConsumed(current, "operator", true, "operator");
                      						
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // InternalCS.g:930:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalCS.g:931:4: (lv_name_5_0= RULE_ID )
            {
            // InternalCS.g:931:4: (lv_name_5_0= RULE_ID )
            // InternalCS.g:932:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_23); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_5_0, grammarAccess.getMethodAccess().getNameIDTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getMethodRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_5_0,
              						"m.CS.ID");
              				
            }

            }


            }

            // InternalCS.g:948:3: (otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==24) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalCS.g:949:4: otherlv_6= '<' ( (lv_generics_7_0= ruleTypeName ) ) (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )* otherlv_10= '>'
                    {
                    otherlv_6=(Token)match(input,24,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getMethodAccess().getLessThanSignKeyword_4_0());
                      			
                    }
                    // InternalCS.g:953:4: ( (lv_generics_7_0= ruleTypeName ) )
                    // InternalCS.g:954:5: (lv_generics_7_0= ruleTypeName )
                    {
                    // InternalCS.g:954:5: (lv_generics_7_0= ruleTypeName )
                    // InternalCS.g:955:6: lv_generics_7_0= ruleTypeName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMethodAccess().getGenericsTypeNameParserRuleCall_4_1_0());
                      					
                    }
                    pushFollow(FOLLOW_19);
                    lv_generics_7_0=ruleTypeName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMethodRule());
                      						}
                      						add(
                      							current,
                      							"generics",
                      							lv_generics_7_0,
                      							"m.CS.TypeName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:972:4: (otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==16) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalCS.g:973:5: otherlv_8= ',' ( (lv_generics_9_0= ruleTypeName ) )
                    	    {
                    	    otherlv_8=(Token)match(input,16,FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_8, grammarAccess.getMethodAccess().getCommaKeyword_4_2_0());
                    	      				
                    	    }
                    	    // InternalCS.g:977:5: ( (lv_generics_9_0= ruleTypeName ) )
                    	    // InternalCS.g:978:6: (lv_generics_9_0= ruleTypeName )
                    	    {
                    	    // InternalCS.g:978:6: (lv_generics_9_0= ruleTypeName )
                    	    // InternalCS.g:979:7: lv_generics_9_0= ruleTypeName
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getMethodAccess().getGenericsTypeNameParserRuleCall_4_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_19);
                    	    lv_generics_9_0=ruleTypeName();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getMethodRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"generics",
                    	      								lv_generics_9_0,
                    	      								"m.CS.TypeName");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,25,FOLLOW_24); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_10, grammarAccess.getMethodAccess().getGreaterThanSignKeyword_4_3());
                      			
                    }

                    }
                    break;

            }

            otherlv_11=(Token)match(input,21,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_11, grammarAccess.getMethodAccess().getLeftParenthesisKeyword_5());
              		
            }
            // InternalCS.g:1006:3: ( ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )* )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_ID||LA22_0==20||LA22_0==26) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalCS.g:1007:4: ( (lv_arguments_12_0= ruleArgument ) ) (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )*
                    {
                    // InternalCS.g:1007:4: ( (lv_arguments_12_0= ruleArgument ) )
                    // InternalCS.g:1008:5: (lv_arguments_12_0= ruleArgument )
                    {
                    // InternalCS.g:1008:5: (lv_arguments_12_0= ruleArgument )
                    // InternalCS.g:1009:6: lv_arguments_12_0= ruleArgument
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMethodAccess().getArgumentsArgumentParserRuleCall_6_0_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
                    lv_arguments_12_0=ruleArgument();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getMethodRule());
                      						}
                      						add(
                      							current,
                      							"arguments",
                      							lv_arguments_12_0,
                      							"m.CS.Argument");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:1026:4: (otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==16) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalCS.g:1027:5: otherlv_13= ',' ( (lv_arguments_14_0= ruleArgument ) )
                    	    {
                    	    otherlv_13=(Token)match(input,16,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_13, grammarAccess.getMethodAccess().getCommaKeyword_6_1_0());
                    	      				
                    	    }
                    	    // InternalCS.g:1031:5: ( (lv_arguments_14_0= ruleArgument ) )
                    	    // InternalCS.g:1032:6: (lv_arguments_14_0= ruleArgument )
                    	    {
                    	    // InternalCS.g:1032:6: (lv_arguments_14_0= ruleArgument )
                    	    // InternalCS.g:1033:7: lv_arguments_14_0= ruleArgument
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getMethodAccess().getArgumentsArgumentParserRuleCall_6_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_16);
                    	    lv_arguments_14_0=ruleArgument();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getMethodRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"arguments",
                    	      								lv_arguments_14_0,
                    	      								"m.CS.Argument");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_15=(Token)match(input,22,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_15, grammarAccess.getMethodAccess().getRightParenthesisKeyword_7());
              		
            }
            otherlv_16=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_16, grammarAccess.getMethodAccess().getLeftCurlyBracketKeyword_8());
              		
            }
            // InternalCS.g:1060:3: ( (lv_commands_17_0= ruleCommand ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==RULE_ID||LA23_0==26||LA23_0==30||(LA23_0>=33 && LA23_0<=36)||LA23_0==38) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalCS.g:1061:4: (lv_commands_17_0= ruleCommand )
            	    {
            	    // InternalCS.g:1061:4: (lv_commands_17_0= ruleCommand )
            	    // InternalCS.g:1062:5: lv_commands_17_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getMethodAccess().getCommandsCommandParserRuleCall_9_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_17_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getMethodRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_17_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            otherlv_18=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_18, grammarAccess.getMethodAccess().getRightCurlyBracketKeyword_10());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleCommand"
    // InternalCS.g:1087:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalCS.g:1087:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalCS.g:1088:2: iv_ruleCommand= ruleCommand EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCommandRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCommand=ruleCommand();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCommand; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:1094:1: ruleCommand returns [EObject current=null] : (this_For_0= ruleFor | this_If_1= ruleIf | this_While_2= ruleWhile | (this_Assignment_3= ruleAssignment otherlv_4= ';' ) | (this_Return_5= ruleReturn otherlv_6= ';' ) ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_For_0 = null;

        EObject this_If_1 = null;

        EObject this_While_2 = null;

        EObject this_Assignment_3 = null;

        EObject this_Return_5 = null;



        	enterRule();

        try {
            // InternalCS.g:1100:2: ( (this_For_0= ruleFor | this_If_1= ruleIf | this_While_2= ruleWhile | (this_Assignment_3= ruleAssignment otherlv_4= ';' ) | (this_Return_5= ruleReturn otherlv_6= ';' ) ) )
            // InternalCS.g:1101:2: (this_For_0= ruleFor | this_If_1= ruleIf | this_While_2= ruleWhile | (this_Assignment_3= ruleAssignment otherlv_4= ';' ) | (this_Return_5= ruleReturn otherlv_6= ';' ) )
            {
            // InternalCS.g:1101:2: (this_For_0= ruleFor | this_If_1= ruleIf | this_While_2= ruleWhile | (this_Assignment_3= ruleAssignment otherlv_4= ';' ) | (this_Return_5= ruleReturn otherlv_6= ';' ) )
            int alt24=5;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt24=1;
                }
                break;
            case 36:
                {
                alt24=2;
                }
                break;
            case 38:
                {
                alt24=3;
                }
                break;
            case RULE_ID:
            case 26:
            case 30:
            case 34:
                {
                alt24=4;
                }
                break;
            case 33:
                {
                alt24=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // InternalCS.g:1102:3: this_For_0= ruleFor
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getCommandAccess().getForParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_For_0=ruleFor();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_For_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCS.g:1111:3: this_If_1= ruleIf
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getCommandAccess().getIfParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_If_1=ruleIf();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_If_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCS.g:1120:3: this_While_2= ruleWhile
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getCommandAccess().getWhileParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_While_2=ruleWhile();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_While_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCS.g:1129:3: (this_Assignment_3= ruleAssignment otherlv_4= ';' )
                    {
                    // InternalCS.g:1129:3: (this_Assignment_3= ruleAssignment otherlv_4= ';' )
                    // InternalCS.g:1130:4: this_Assignment_3= ruleAssignment otherlv_4= ';'
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getCommandAccess().getAssignmentParserRuleCall_3_0());
                      			
                    }
                    pushFollow(FOLLOW_6);
                    this_Assignment_3=ruleAssignment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_Assignment_3;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    otherlv_4=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getCommandAccess().getSemicolonKeyword_3_1());
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalCS.g:1144:3: (this_Return_5= ruleReturn otherlv_6= ';' )
                    {
                    // InternalCS.g:1144:3: (this_Return_5= ruleReturn otherlv_6= ';' )
                    // InternalCS.g:1145:4: this_Return_5= ruleReturn otherlv_6= ';'
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getCommandAccess().getReturnParserRuleCall_4_0());
                      			
                    }
                    pushFollow(FOLLOW_6);
                    this_Return_5=ruleReturn();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_Return_5;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    otherlv_6=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getCommandAccess().getSemicolonKeyword_4_1());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleAssignment"
    // InternalCS.g:1162:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalCS.g:1162:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalCS.g:1163:2: iv_ruleAssignment= ruleAssignment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAssignmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAssignment; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:1169:1: ruleAssignment returns [EObject current=null] : ( ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )? ( (lv_access_2_0= ruleAccess ) ) ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )? ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token lv_var_0_0=null;
        Token lv_increment_5_0=null;
        Token lv_decrement_6_0=null;
        EObject lv_type_1_0 = null;

        EObject lv_access_2_0 = null;

        Enumerator lv_assignmentType_3_0 = null;

        EObject lv_expression_4_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1175:2: ( ( ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )? ( (lv_access_2_0= ruleAccess ) ) ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )? ) )
            // InternalCS.g:1176:2: ( ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )? ( (lv_access_2_0= ruleAccess ) ) ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )? )
            {
            // InternalCS.g:1176:2: ( ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )? ( (lv_access_2_0= ruleAccess ) ) ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )? )
            // InternalCS.g:1177:3: ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )? ( (lv_access_2_0= ruleAccess ) ) ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )?
            {
            // InternalCS.g:1177:3: ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )?
            int alt25=3;
            alt25 = dfa25.predict(input);
            switch (alt25) {
                case 1 :
                    // InternalCS.g:1178:4: ( (lv_var_0_0= 'var' ) )
                    {
                    // InternalCS.g:1178:4: ( (lv_var_0_0= 'var' ) )
                    // InternalCS.g:1179:5: (lv_var_0_0= 'var' )
                    {
                    // InternalCS.g:1179:5: (lv_var_0_0= 'var' )
                    // InternalCS.g:1180:6: lv_var_0_0= 'var'
                    {
                    lv_var_0_0=(Token)match(input,30,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_var_0_0, grammarAccess.getAssignmentAccess().getVarVarKeyword_0_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAssignmentRule());
                      						}
                      						setWithLastConsumed(current, "var", true, "var");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:1193:4: ( (lv_type_1_0= ruleTypeName ) )
                    {
                    // InternalCS.g:1193:4: ( (lv_type_1_0= ruleTypeName ) )
                    // InternalCS.g:1194:5: (lv_type_1_0= ruleTypeName )
                    {
                    // InternalCS.g:1194:5: (lv_type_1_0= ruleTypeName )
                    // InternalCS.g:1195:6: lv_type_1_0= ruleTypeName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getAssignmentAccess().getTypeTypeNameParserRuleCall_0_1_0());
                      					
                    }
                    pushFollow(FOLLOW_28);
                    lv_type_1_0=ruleTypeName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getAssignmentRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_0,
                      							"m.CS.TypeName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalCS.g:1213:3: ( (lv_access_2_0= ruleAccess ) )
            // InternalCS.g:1214:4: (lv_access_2_0= ruleAccess )
            {
            // InternalCS.g:1214:4: (lv_access_2_0= ruleAccess )
            // InternalCS.g:1215:5: lv_access_2_0= ruleAccess
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAssignmentAccess().getAccessAccessParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_29);
            lv_access_2_0=ruleAccess();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAssignmentRule());
              					}
              					set(
              						current,
              						"access",
              						lv_access_2_0,
              						"m.CS.Access");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:1232:3: ( ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) ) | ( (lv_increment_5_0= '++' ) ) | ( (lv_decrement_6_0= '--' ) ) )?
            int alt26=4;
            switch ( input.LA(1) ) {
                case 39:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    {
                    alt26=1;
                    }
                    break;
                case 31:
                    {
                    alt26=2;
                    }
                    break;
                case 32:
                    {
                    alt26=3;
                    }
                    break;
            }

            switch (alt26) {
                case 1 :
                    // InternalCS.g:1233:4: ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) )
                    {
                    // InternalCS.g:1233:4: ( ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) ) )
                    // InternalCS.g:1234:5: ( (lv_assignmentType_3_0= ruleAssignmentType ) ) ( (lv_expression_4_0= ruleExpression ) )
                    {
                    // InternalCS.g:1234:5: ( (lv_assignmentType_3_0= ruleAssignmentType ) )
                    // InternalCS.g:1235:6: (lv_assignmentType_3_0= ruleAssignmentType )
                    {
                    // InternalCS.g:1235:6: (lv_assignmentType_3_0= ruleAssignmentType )
                    // InternalCS.g:1236:7: lv_assignmentType_3_0= ruleAssignmentType
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getAssignmentAccess().getAssignmentTypeAssignmentTypeEnumRuleCall_2_0_0_0());
                      						
                    }
                    pushFollow(FOLLOW_15);
                    lv_assignmentType_3_0=ruleAssignmentType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getAssignmentRule());
                      							}
                      							set(
                      								current,
                      								"assignmentType",
                      								lv_assignmentType_3_0,
                      								"m.CS.AssignmentType");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    // InternalCS.g:1253:5: ( (lv_expression_4_0= ruleExpression ) )
                    // InternalCS.g:1254:6: (lv_expression_4_0= ruleExpression )
                    {
                    // InternalCS.g:1254:6: (lv_expression_4_0= ruleExpression )
                    // InternalCS.g:1255:7: lv_expression_4_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getAssignmentAccess().getExpressionExpressionParserRuleCall_2_0_1_0());
                      						
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_4_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getAssignmentRule());
                      							}
                      							set(
                      								current,
                      								"expression",
                      								lv_expression_4_0,
                      								"m.CS.Expression");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:1274:4: ( (lv_increment_5_0= '++' ) )
                    {
                    // InternalCS.g:1274:4: ( (lv_increment_5_0= '++' ) )
                    // InternalCS.g:1275:5: (lv_increment_5_0= '++' )
                    {
                    // InternalCS.g:1275:5: (lv_increment_5_0= '++' )
                    // InternalCS.g:1276:6: lv_increment_5_0= '++'
                    {
                    lv_increment_5_0=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_increment_5_0, grammarAccess.getAssignmentAccess().getIncrementPlusSignPlusSignKeyword_2_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAssignmentRule());
                      						}
                      						setWithLastConsumed(current, "increment", true, "++");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:1289:4: ( (lv_decrement_6_0= '--' ) )
                    {
                    // InternalCS.g:1289:4: ( (lv_decrement_6_0= '--' ) )
                    // InternalCS.g:1290:5: (lv_decrement_6_0= '--' )
                    {
                    // InternalCS.g:1290:5: (lv_decrement_6_0= '--' )
                    // InternalCS.g:1291:6: lv_decrement_6_0= '--'
                    {
                    lv_decrement_6_0=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_decrement_6_0, grammarAccess.getAssignmentAccess().getDecrementHyphenMinusHyphenMinusKeyword_2_2_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAssignmentRule());
                      						}
                      						setWithLastConsumed(current, "decrement", true, "--");
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleReturn"
    // InternalCS.g:1308:1: entryRuleReturn returns [EObject current=null] : iv_ruleReturn= ruleReturn EOF ;
    public final EObject entryRuleReturn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReturn = null;


        try {
            // InternalCS.g:1308:47: (iv_ruleReturn= ruleReturn EOF )
            // InternalCS.g:1309:2: iv_ruleReturn= ruleReturn EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReturnRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleReturn=ruleReturn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReturn; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleReturn"


    // $ANTLR start "ruleReturn"
    // InternalCS.g:1315:1: ruleReturn returns [EObject current=null] : (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) ) ;
    public final EObject ruleReturn() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expression_1_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1321:2: ( (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) ) )
            // InternalCS.g:1322:2: (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) )
            {
            // InternalCS.g:1322:2: (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) )
            // InternalCS.g:1323:3: otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getReturnAccess().getReturnKeyword_0());
              		
            }
            // InternalCS.g:1327:3: ( (lv_expression_1_0= ruleExpression ) )
            // InternalCS.g:1328:4: (lv_expression_1_0= ruleExpression )
            {
            // InternalCS.g:1328:4: (lv_expression_1_0= ruleExpression )
            // InternalCS.g:1329:5: lv_expression_1_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getReturnAccess().getExpressionExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_1_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getReturnRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_1_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleReturn"


    // $ANTLR start "entryRuleAccess"
    // InternalCS.g:1350:1: entryRuleAccess returns [EObject current=null] : iv_ruleAccess= ruleAccess EOF ;
    public final EObject entryRuleAccess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAccess = null;


        try {
            // InternalCS.g:1350:47: (iv_ruleAccess= ruleAccess EOF )
            // InternalCS.g:1351:2: iv_ruleAccess= ruleAccess EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAccessRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAccess=ruleAccess();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAccess; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:1357:1: ruleAccess returns [EObject current=null] : ( ( (lv_new_0_0= 'new' ) )? ( (lv_ref_1_0= 'ref' ) )? ( (lv_portions_2_0= rulePortion ) ) (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )* ) ;
    public final EObject ruleAccess() throws RecognitionException {
        EObject current = null;

        Token lv_new_0_0=null;
        Token lv_ref_1_0=null;
        Token otherlv_3=null;
        EObject lv_portions_2_0 = null;

        EObject lv_portions_4_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1363:2: ( ( ( (lv_new_0_0= 'new' ) )? ( (lv_ref_1_0= 'ref' ) )? ( (lv_portions_2_0= rulePortion ) ) (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )* ) )
            // InternalCS.g:1364:2: ( ( (lv_new_0_0= 'new' ) )? ( (lv_ref_1_0= 'ref' ) )? ( (lv_portions_2_0= rulePortion ) ) (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )* )
            {
            // InternalCS.g:1364:2: ( ( (lv_new_0_0= 'new' ) )? ( (lv_ref_1_0= 'ref' ) )? ( (lv_portions_2_0= rulePortion ) ) (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )* )
            // InternalCS.g:1365:3: ( (lv_new_0_0= 'new' ) )? ( (lv_ref_1_0= 'ref' ) )? ( (lv_portions_2_0= rulePortion ) ) (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )*
            {
            // InternalCS.g:1365:3: ( (lv_new_0_0= 'new' ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==34) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalCS.g:1366:4: (lv_new_0_0= 'new' )
                    {
                    // InternalCS.g:1366:4: (lv_new_0_0= 'new' )
                    // InternalCS.g:1367:5: lv_new_0_0= 'new'
                    {
                    lv_new_0_0=(Token)match(input,34,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_new_0_0, grammarAccess.getAccessAccess().getNewNewKeyword_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getAccessRule());
                      					}
                      					setWithLastConsumed(current, "new", true, "new");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:1379:3: ( (lv_ref_1_0= 'ref' ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==26) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalCS.g:1380:4: (lv_ref_1_0= 'ref' )
                    {
                    // InternalCS.g:1380:4: (lv_ref_1_0= 'ref' )
                    // InternalCS.g:1381:5: lv_ref_1_0= 'ref'
                    {
                    lv_ref_1_0=(Token)match(input,26,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_ref_1_0, grammarAccess.getAccessAccess().getRefRefKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getAccessRule());
                      					}
                      					setWithLastConsumed(current, "ref", true, "ref");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCS.g:1393:3: ( (lv_portions_2_0= rulePortion ) )
            // InternalCS.g:1394:4: (lv_portions_2_0= rulePortion )
            {
            // InternalCS.g:1394:4: (lv_portions_2_0= rulePortion )
            // InternalCS.g:1395:5: lv_portions_2_0= rulePortion
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAccessAccess().getPortionsPortionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_7);
            lv_portions_2_0=rulePortion();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAccessRule());
              					}
              					add(
              						current,
              						"portions",
              						lv_portions_2_0,
              						"m.CS.Portion");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:1412:3: (otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==13) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalCS.g:1413:4: otherlv_3= '.' ( (lv_portions_4_0= rulePortion ) )
            	    {
            	    otherlv_3=(Token)match(input,13,FOLLOW_28); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getAccessAccess().getFullStopKeyword_3_0());
            	      			
            	    }
            	    // InternalCS.g:1417:4: ( (lv_portions_4_0= rulePortion ) )
            	    // InternalCS.g:1418:5: (lv_portions_4_0= rulePortion )
            	    {
            	    // InternalCS.g:1418:5: (lv_portions_4_0= rulePortion )
            	    // InternalCS.g:1419:6: lv_portions_4_0= rulePortion
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAccessAccess().getPortionsPortionParserRuleCall_3_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_7);
            	    lv_portions_4_0=rulePortion();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getAccessRule());
            	      						}
            	      						add(
            	      							current,
            	      							"portions",
            	      							lv_portions_4_0,
            	      							"m.CS.Portion");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

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

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAccess"


    // $ANTLR start "entryRulePortion"
    // InternalCS.g:1441:1: entryRulePortion returns [EObject current=null] : iv_rulePortion= rulePortion EOF ;
    public final EObject entryRulePortion() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePortion = null;


        try {
            // InternalCS.g:1441:48: (iv_rulePortion= rulePortion EOF )
            // InternalCS.g:1442:2: iv_rulePortion= rulePortion EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPortionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePortion=rulePortion();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePortion; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRulePortion"


    // $ANTLR start "rulePortion"
    // InternalCS.g:1448:1: rulePortion returns [EObject current=null] : ( ( () ( (lv_name_1_0= RULE_ID ) ) ) | ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' ) | ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' ) | ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' ) ) ;
    public final EObject rulePortion() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token lv_name_13_0=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token lv_name_20_0=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        EObject lv_generics_5_0 = null;

        EObject lv_expressions_8_0 = null;

        EObject lv_expressions_10_0 = null;

        EObject lv_initializations_15_0 = null;

        EObject lv_initializations_17_0 = null;

        EObject lv_index_22_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1454:2: ( ( ( () ( (lv_name_1_0= RULE_ID ) ) ) | ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' ) | ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' ) | ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' ) ) )
            // InternalCS.g:1455:2: ( ( () ( (lv_name_1_0= RULE_ID ) ) ) | ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' ) | ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' ) | ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' ) )
            {
            // InternalCS.g:1455:2: ( ( () ( (lv_name_1_0= RULE_ID ) ) ) | ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' ) | ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' ) | ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' ) )
            int alt35=4;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // InternalCS.g:1456:3: ( () ( (lv_name_1_0= RULE_ID ) ) )
                    {
                    // InternalCS.g:1456:3: ( () ( (lv_name_1_0= RULE_ID ) ) )
                    // InternalCS.g:1457:4: () ( (lv_name_1_0= RULE_ID ) )
                    {
                    // InternalCS.g:1457:4: ()
                    // InternalCS.g:1458:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getPortionAccess().getLabelAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:1464:4: ( (lv_name_1_0= RULE_ID ) )
                    // InternalCS.g:1465:5: (lv_name_1_0= RULE_ID )
                    {
                    // InternalCS.g:1465:5: (lv_name_1_0= RULE_ID )
                    // InternalCS.g:1466:6: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_name_1_0, grammarAccess.getPortionAccess().getNameIDTerminalRuleCall_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getPortionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"name",
                      							lv_name_1_0,
                      							"m.CS.ID");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:1484:3: ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' )
                    {
                    // InternalCS.g:1484:3: ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' )
                    // InternalCS.g:1485:4: () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')'
                    {
                    // InternalCS.g:1485:4: ()
                    // InternalCS.g:1486:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getPortionAccess().getMethodCallAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:1492:4: ( (lv_name_3_0= RULE_ID ) )
                    // InternalCS.g:1493:5: (lv_name_3_0= RULE_ID )
                    {
                    // InternalCS.g:1493:5: (lv_name_3_0= RULE_ID )
                    // InternalCS.g:1494:6: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_name_3_0, grammarAccess.getPortionAccess().getNameIDTerminalRuleCall_1_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getPortionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"name",
                      							lv_name_3_0,
                      							"m.CS.ID");
                      					
                    }

                    }


                    }

                    // InternalCS.g:1510:4: (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==24) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // InternalCS.g:1511:5: otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>'
                            {
                            otherlv_4=(Token)match(input,24,FOLLOW_5); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getPortionAccess().getLessThanSignKeyword_1_2_0());
                              				
                            }
                            // InternalCS.g:1515:5: ( (lv_generics_5_0= ruleTypeName ) )
                            // InternalCS.g:1516:6: (lv_generics_5_0= ruleTypeName )
                            {
                            // InternalCS.g:1516:6: (lv_generics_5_0= ruleTypeName )
                            // InternalCS.g:1517:7: lv_generics_5_0= ruleTypeName
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getPortionAccess().getGenericsTypeNameParserRuleCall_1_2_1_0());
                              						
                            }
                            pushFollow(FOLLOW_30);
                            lv_generics_5_0=ruleTypeName();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getPortionRule());
                              							}
                              							add(
                              								current,
                              								"generics",
                              								lv_generics_5_0,
                              								"m.CS.TypeName");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            otherlv_6=(Token)match(input,25,FOLLOW_24); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_6, grammarAccess.getPortionAccess().getGreaterThanSignKeyword_1_2_2());
                              				
                            }

                            }
                            break;

                    }

                    otherlv_7=(Token)match(input,21,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getPortionAccess().getLeftParenthesisKeyword_1_3());
                      			
                    }
                    // InternalCS.g:1543:4: ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( ((LA32_0>=RULE_ID && LA32_0<=RULE_STRING)||LA32_0==21||LA32_0==26||LA32_0==34||LA32_0==43) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // InternalCS.g:1544:5: ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )*
                            {
                            // InternalCS.g:1544:5: ( (lv_expressions_8_0= ruleExpression ) )
                            // InternalCS.g:1545:6: (lv_expressions_8_0= ruleExpression )
                            {
                            // InternalCS.g:1545:6: (lv_expressions_8_0= ruleExpression )
                            // InternalCS.g:1546:7: lv_expressions_8_0= ruleExpression
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getPortionAccess().getExpressionsExpressionParserRuleCall_1_4_0_0());
                              						
                            }
                            pushFollow(FOLLOW_16);
                            lv_expressions_8_0=ruleExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getPortionRule());
                              							}
                              							add(
                              								current,
                              								"expressions",
                              								lv_expressions_8_0,
                              								"m.CS.Expression");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            // InternalCS.g:1563:5: (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==16) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // InternalCS.g:1564:6: otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) )
                            	    {
                            	    otherlv_9=(Token)match(input,16,FOLLOW_15); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_9, grammarAccess.getPortionAccess().getCommaKeyword_1_4_1_0());
                            	      					
                            	    }
                            	    // InternalCS.g:1568:6: ( (lv_expressions_10_0= ruleExpression ) )
                            	    // InternalCS.g:1569:7: (lv_expressions_10_0= ruleExpression )
                            	    {
                            	    // InternalCS.g:1569:7: (lv_expressions_10_0= ruleExpression )
                            	    // InternalCS.g:1570:8: lv_expressions_10_0= ruleExpression
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      								newCompositeNode(grammarAccess.getPortionAccess().getExpressionsExpressionParserRuleCall_1_4_1_1_0());
                            	      							
                            	    }
                            	    pushFollow(FOLLOW_16);
                            	    lv_expressions_10_0=ruleExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      								if (current==null) {
                            	      									current = createModelElementForParent(grammarAccess.getPortionRule());
                            	      								}
                            	      								add(
                            	      									current,
                            	      									"expressions",
                            	      									lv_expressions_10_0,
                            	      									"m.CS.Expression");
                            	      								afterParserOrEnumRuleCall();
                            	      							
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop31;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_11=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_11, grammarAccess.getPortionAccess().getRightParenthesisKeyword_1_5());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:1595:3: ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' )
                    {
                    // InternalCS.g:1595:3: ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' )
                    // InternalCS.g:1596:4: () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}'
                    {
                    // InternalCS.g:1596:4: ()
                    // InternalCS.g:1597:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getPortionAccess().getInitializationAction_2_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:1603:4: ( (lv_name_13_0= RULE_ID ) )
                    // InternalCS.g:1604:5: (lv_name_13_0= RULE_ID )
                    {
                    // InternalCS.g:1604:5: (lv_name_13_0= RULE_ID )
                    // InternalCS.g:1605:6: lv_name_13_0= RULE_ID
                    {
                    lv_name_13_0=(Token)match(input,RULE_ID,FOLLOW_26); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_name_13_0, grammarAccess.getPortionAccess().getNameIDTerminalRuleCall_2_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getPortionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"name",
                      							lv_name_13_0,
                      							"m.CS.ID");
                      					
                    }

                    }


                    }

                    otherlv_14=(Token)match(input,17,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_14, grammarAccess.getPortionAccess().getLeftCurlyBracketKeyword_2_2());
                      			
                    }
                    // InternalCS.g:1625:4: ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==RULE_ID) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // InternalCS.g:1626:5: ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )*
                            {
                            // InternalCS.g:1626:5: ( (lv_initializations_15_0= ruleInitializeVariable ) )
                            // InternalCS.g:1627:6: (lv_initializations_15_0= ruleInitializeVariable )
                            {
                            // InternalCS.g:1627:6: (lv_initializations_15_0= ruleInitializeVariable )
                            // InternalCS.g:1628:7: lv_initializations_15_0= ruleInitializeVariable
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getPortionAccess().getInitializationsInitializeVariableParserRuleCall_2_3_0_0());
                              						
                            }
                            pushFollow(FOLLOW_33);
                            lv_initializations_15_0=ruleInitializeVariable();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getPortionRule());
                              							}
                              							add(
                              								current,
                              								"initializations",
                              								lv_initializations_15_0,
                              								"m.CS.InitializeVariable");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            // InternalCS.g:1645:5: (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )*
                            loop33:
                            do {
                                int alt33=2;
                                int LA33_0 = input.LA(1);

                                if ( (LA33_0==16) ) {
                                    alt33=1;
                                }


                                switch (alt33) {
                            	case 1 :
                            	    // InternalCS.g:1646:6: otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) )
                            	    {
                            	    otherlv_16=(Token)match(input,16,FOLLOW_5); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_16, grammarAccess.getPortionAccess().getCommaKeyword_2_3_1_0());
                            	      					
                            	    }
                            	    // InternalCS.g:1650:6: ( (lv_initializations_17_0= ruleInitializeVariable ) )
                            	    // InternalCS.g:1651:7: (lv_initializations_17_0= ruleInitializeVariable )
                            	    {
                            	    // InternalCS.g:1651:7: (lv_initializations_17_0= ruleInitializeVariable )
                            	    // InternalCS.g:1652:8: lv_initializations_17_0= ruleInitializeVariable
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      								newCompositeNode(grammarAccess.getPortionAccess().getInitializationsInitializeVariableParserRuleCall_2_3_1_1_0());
                            	      							
                            	    }
                            	    pushFollow(FOLLOW_33);
                            	    lv_initializations_17_0=ruleInitializeVariable();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      								if (current==null) {
                            	      									current = createModelElementForParent(grammarAccess.getPortionRule());
                            	      								}
                            	      								add(
                            	      									current,
                            	      									"initializations",
                            	      									lv_initializations_17_0,
                            	      									"m.CS.InitializeVariable");
                            	      								afterParserOrEnumRuleCall();
                            	      							
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop33;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_18=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_18, grammarAccess.getPortionAccess().getRightCurlyBracketKeyword_2_4());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCS.g:1677:3: ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' )
                    {
                    // InternalCS.g:1677:3: ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' )
                    // InternalCS.g:1678:4: () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']'
                    {
                    // InternalCS.g:1678:4: ()
                    // InternalCS.g:1679:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getPortionAccess().getIndexingAction_3_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:1685:4: ( (lv_name_20_0= RULE_ID ) )
                    // InternalCS.g:1686:5: (lv_name_20_0= RULE_ID )
                    {
                    // InternalCS.g:1686:5: (lv_name_20_0= RULE_ID )
                    // InternalCS.g:1687:6: lv_name_20_0= RULE_ID
                    {
                    lv_name_20_0=(Token)match(input,RULE_ID,FOLLOW_34); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_name_20_0, grammarAccess.getPortionAccess().getNameIDTerminalRuleCall_3_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getPortionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"name",
                      							lv_name_20_0,
                      							"m.CS.ID");
                      					
                    }

                    }


                    }

                    otherlv_21=(Token)match(input,20,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_21, grammarAccess.getPortionAccess().getLeftSquareBracketKeyword_3_2());
                      			
                    }
                    // InternalCS.g:1707:4: ( (lv_index_22_0= ruleExpression ) )
                    // InternalCS.g:1708:5: (lv_index_22_0= ruleExpression )
                    {
                    // InternalCS.g:1708:5: (lv_index_22_0= ruleExpression )
                    // InternalCS.g:1709:6: lv_index_22_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPortionAccess().getIndexExpressionParserRuleCall_3_3_0());
                      					
                    }
                    pushFollow(FOLLOW_17);
                    lv_index_22_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getPortionRule());
                      						}
                      						set(
                      							current,
                      							"index",
                      							lv_index_22_0,
                      							"m.CS.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_23=(Token)match(input,23,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_23, grammarAccess.getPortionAccess().getRightSquareBracketKeyword_3_4());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "rulePortion"


    // $ANTLR start "entryRuleFor"
    // InternalCS.g:1735:1: entryRuleFor returns [EObject current=null] : iv_ruleFor= ruleFor EOF ;
    public final EObject entryRuleFor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFor = null;


        try {
            // InternalCS.g:1735:44: (iv_ruleFor= ruleFor EOF )
            // InternalCS.g:1736:2: iv_ruleFor= ruleFor EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getForRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFor=ruleFor();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFor; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleFor"


    // $ANTLR start "ruleFor"
    // InternalCS.g:1742:1: ruleFor returns [EObject current=null] : ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initialization_3_0= ruleAssignment ) ) otherlv_4= ';' ( (lv_condition_5_0= ruleExpression ) ) otherlv_6= ';' ( (lv_increment_7_0= ruleAssignment ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_commands_10_0= ruleCommand ) )* otherlv_11= '}' ) ;
    public final EObject ruleFor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_initialization_3_0 = null;

        EObject lv_condition_5_0 = null;

        EObject lv_increment_7_0 = null;

        EObject lv_commands_10_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1748:2: ( ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initialization_3_0= ruleAssignment ) ) otherlv_4= ';' ( (lv_condition_5_0= ruleExpression ) ) otherlv_6= ';' ( (lv_increment_7_0= ruleAssignment ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_commands_10_0= ruleCommand ) )* otherlv_11= '}' ) )
            // InternalCS.g:1749:2: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initialization_3_0= ruleAssignment ) ) otherlv_4= ';' ( (lv_condition_5_0= ruleExpression ) ) otherlv_6= ';' ( (lv_increment_7_0= ruleAssignment ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_commands_10_0= ruleCommand ) )* otherlv_11= '}' )
            {
            // InternalCS.g:1749:2: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initialization_3_0= ruleAssignment ) ) otherlv_4= ';' ( (lv_condition_5_0= ruleExpression ) ) otherlv_6= ';' ( (lv_increment_7_0= ruleAssignment ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_commands_10_0= ruleCommand ) )* otherlv_11= '}' )
            // InternalCS.g:1750:3: () otherlv_1= 'for' otherlv_2= '(' ( (lv_initialization_3_0= ruleAssignment ) ) otherlv_4= ';' ( (lv_condition_5_0= ruleExpression ) ) otherlv_6= ';' ( (lv_increment_7_0= ruleAssignment ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_commands_10_0= ruleCommand ) )* otherlv_11= '}'
            {
            // InternalCS.g:1750:3: ()
            // InternalCS.g:1751:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getForAccess().getForAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,35,FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getForAccess().getForKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,21,FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getForAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCS.g:1765:3: ( (lv_initialization_3_0= ruleAssignment ) )
            // InternalCS.g:1766:4: (lv_initialization_3_0= ruleAssignment )
            {
            // InternalCS.g:1766:4: (lv_initialization_3_0= ruleAssignment )
            // InternalCS.g:1767:5: lv_initialization_3_0= ruleAssignment
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getForAccess().getInitializationAssignmentParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_6);
            lv_initialization_3_0=ruleAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getForRule());
              					}
              					set(
              						current,
              						"initialization",
              						lv_initialization_3_0,
              						"m.CS.Assignment");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getForAccess().getSemicolonKeyword_4());
              		
            }
            // InternalCS.g:1788:3: ( (lv_condition_5_0= ruleExpression ) )
            // InternalCS.g:1789:4: (lv_condition_5_0= ruleExpression )
            {
            // InternalCS.g:1789:4: (lv_condition_5_0= ruleExpression )
            // InternalCS.g:1790:5: lv_condition_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getForAccess().getConditionExpressionParserRuleCall_5_0());
              				
            }
            pushFollow(FOLLOW_6);
            lv_condition_5_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getForRule());
              					}
              					set(
              						current,
              						"condition",
              						lv_condition_5_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_6=(Token)match(input,12,FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getForAccess().getSemicolonKeyword_6());
              		
            }
            // InternalCS.g:1811:3: ( (lv_increment_7_0= ruleAssignment ) )
            // InternalCS.g:1812:4: (lv_increment_7_0= ruleAssignment )
            {
            // InternalCS.g:1812:4: (lv_increment_7_0= ruleAssignment )
            // InternalCS.g:1813:5: lv_increment_7_0= ruleAssignment
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getForAccess().getIncrementAssignmentParserRuleCall_7_0());
              				
            }
            pushFollow(FOLLOW_36);
            lv_increment_7_0=ruleAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getForRule());
              					}
              					set(
              						current,
              						"increment",
              						lv_increment_7_0,
              						"m.CS.Assignment");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_8=(Token)match(input,22,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getForAccess().getRightParenthesisKeyword_8());
              		
            }
            otherlv_9=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getForAccess().getLeftCurlyBracketKeyword_9());
              		
            }
            // InternalCS.g:1838:3: ( (lv_commands_10_0= ruleCommand ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID||LA36_0==26||LA36_0==30||(LA36_0>=33 && LA36_0<=36)||LA36_0==38) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalCS.g:1839:4: (lv_commands_10_0= ruleCommand )
            	    {
            	    // InternalCS.g:1839:4: (lv_commands_10_0= ruleCommand )
            	    // InternalCS.g:1840:5: lv_commands_10_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getForAccess().getCommandsCommandParserRuleCall_10_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_10_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getForRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_10_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            otherlv_11=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_11, grammarAccess.getForAccess().getRightCurlyBracketKeyword_11());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleFor"


    // $ANTLR start "entryRuleIf"
    // InternalCS.g:1865:1: entryRuleIf returns [EObject current=null] : iv_ruleIf= ruleIf EOF ;
    public final EObject entryRuleIf() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIf = null;


        try {
            // InternalCS.g:1865:43: (iv_ruleIf= ruleIf EOF )
            // InternalCS.g:1866:2: iv_ruleIf= ruleIf EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIf=ruleIf();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIf; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIf"


    // $ANTLR start "ruleIf"
    // InternalCS.g:1872:1: ruleIf returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_commands_6_0= ruleCommand ) )* otherlv_7= '}' ( (lv_elseIfs_8_0= ruleElseIf ) )* ( (lv_else_9_0= ruleElse ) )? ) ;
    public final EObject ruleIf() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_3_0 = null;

        EObject lv_commands_6_0 = null;

        EObject lv_elseIfs_8_0 = null;

        EObject lv_else_9_0 = null;



        	enterRule();

        try {
            // InternalCS.g:1878:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_commands_6_0= ruleCommand ) )* otherlv_7= '}' ( (lv_elseIfs_8_0= ruleElseIf ) )* ( (lv_else_9_0= ruleElse ) )? ) )
            // InternalCS.g:1879:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_commands_6_0= ruleCommand ) )* otherlv_7= '}' ( (lv_elseIfs_8_0= ruleElseIf ) )* ( (lv_else_9_0= ruleElse ) )? )
            {
            // InternalCS.g:1879:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_commands_6_0= ruleCommand ) )* otherlv_7= '}' ( (lv_elseIfs_8_0= ruleElseIf ) )* ( (lv_else_9_0= ruleElse ) )? )
            // InternalCS.g:1880:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_commands_6_0= ruleCommand ) )* otherlv_7= '}' ( (lv_elseIfs_8_0= ruleElseIf ) )* ( (lv_else_9_0= ruleElse ) )?
            {
            // InternalCS.g:1880:3: ()
            // InternalCS.g:1881:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getIfAccess().getIfAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,36,FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getIfAccess().getIfKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,21,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getIfAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCS.g:1895:3: ( (lv_expression_3_0= ruleExpression ) )
            // InternalCS.g:1896:4: (lv_expression_3_0= ruleExpression )
            {
            // InternalCS.g:1896:4: (lv_expression_3_0= ruleExpression )
            // InternalCS.g:1897:5: lv_expression_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfAccess().getExpressionExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_36);
            lv_expression_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getIfRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_3_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,22,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getIfAccess().getRightParenthesisKeyword_4());
              		
            }
            otherlv_5=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getIfAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalCS.g:1922:3: ( (lv_commands_6_0= ruleCommand ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID||LA37_0==26||LA37_0==30||(LA37_0>=33 && LA37_0<=36)||LA37_0==38) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalCS.g:1923:4: (lv_commands_6_0= ruleCommand )
            	    {
            	    // InternalCS.g:1923:4: (lv_commands_6_0= ruleCommand )
            	    // InternalCS.g:1924:5: lv_commands_6_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getIfAccess().getCommandsCommandParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_6_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getIfRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_6_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

            otherlv_7=(Token)match(input,18,FOLLOW_37); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getIfAccess().getRightCurlyBracketKeyword_7());
              		
            }
            // InternalCS.g:1945:3: ( (lv_elseIfs_8_0= ruleElseIf ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==37) ) {
                    int LA38_1 = input.LA(2);

                    if ( (LA38_1==36) ) {
                        alt38=1;
                    }


                }


                switch (alt38) {
            	case 1 :
            	    // InternalCS.g:1946:4: (lv_elseIfs_8_0= ruleElseIf )
            	    {
            	    // InternalCS.g:1946:4: (lv_elseIfs_8_0= ruleElseIf )
            	    // InternalCS.g:1947:5: lv_elseIfs_8_0= ruleElseIf
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getIfAccess().getElseIfsElseIfParserRuleCall_8_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_37);
            	    lv_elseIfs_8_0=ruleElseIf();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getIfRule());
            	      					}
            	      					add(
            	      						current,
            	      						"elseIfs",
            	      						lv_elseIfs_8_0,
            	      						"m.CS.ElseIf");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            // InternalCS.g:1964:3: ( (lv_else_9_0= ruleElse ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==37) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalCS.g:1965:4: (lv_else_9_0= ruleElse )
                    {
                    // InternalCS.g:1965:4: (lv_else_9_0= ruleElse )
                    // InternalCS.g:1966:5: lv_else_9_0= ruleElse
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getIfAccess().getElseElseParserRuleCall_9_0());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_else_9_0=ruleElse();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getIfRule());
                      					}
                      					set(
                      						current,
                      						"else",
                      						lv_else_9_0,
                      						"m.CS.Else");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleIf"


    // $ANTLR start "entryRuleElseIf"
    // InternalCS.g:1987:1: entryRuleElseIf returns [EObject current=null] : iv_ruleElseIf= ruleElseIf EOF ;
    public final EObject entryRuleElseIf() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIf = null;


        try {
            // InternalCS.g:1987:47: (iv_ruleElseIf= ruleElseIf EOF )
            // InternalCS.g:1988:2: iv_ruleElseIf= ruleElseIf EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElseIfRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleElseIf=ruleElseIf();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElseIf; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleElseIf"


    // $ANTLR start "ruleElseIf"
    // InternalCS.g:1994:1: ruleElseIf returns [EObject current=null] : ( () otherlv_1= 'else' otherlv_2= 'if' otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )* otherlv_8= '}' ) ;
    public final EObject ruleElseIf() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_expression_4_0 = null;

        EObject lv_commands_7_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2000:2: ( ( () otherlv_1= 'else' otherlv_2= 'if' otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )* otherlv_8= '}' ) )
            // InternalCS.g:2001:2: ( () otherlv_1= 'else' otherlv_2= 'if' otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )* otherlv_8= '}' )
            {
            // InternalCS.g:2001:2: ( () otherlv_1= 'else' otherlv_2= 'if' otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )* otherlv_8= '}' )
            // InternalCS.g:2002:3: () otherlv_1= 'else' otherlv_2= 'if' otherlv_3= '(' ( (lv_expression_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= '{' ( (lv_commands_7_0= ruleCommand ) )* otherlv_8= '}'
            {
            // InternalCS.g:2002:3: ()
            // InternalCS.g:2003:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getElseIfAccess().getElseIfAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,37,FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getElseIfAccess().getElseKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,36,FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getElseIfAccess().getIfKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,21,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getElseIfAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalCS.g:2021:3: ( (lv_expression_4_0= ruleExpression ) )
            // InternalCS.g:2022:4: (lv_expression_4_0= ruleExpression )
            {
            // InternalCS.g:2022:4: (lv_expression_4_0= ruleExpression )
            // InternalCS.g:2023:5: lv_expression_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getElseIfAccess().getExpressionExpressionParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_36);
            lv_expression_4_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getElseIfRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_4_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_5=(Token)match(input,22,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getElseIfAccess().getRightParenthesisKeyword_5());
              		
            }
            otherlv_6=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getElseIfAccess().getLeftCurlyBracketKeyword_6());
              		
            }
            // InternalCS.g:2048:3: ( (lv_commands_7_0= ruleCommand ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID||LA40_0==26||LA40_0==30||(LA40_0>=33 && LA40_0<=36)||LA40_0==38) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalCS.g:2049:4: (lv_commands_7_0= ruleCommand )
            	    {
            	    // InternalCS.g:2049:4: (lv_commands_7_0= ruleCommand )
            	    // InternalCS.g:2050:5: lv_commands_7_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getElseIfAccess().getCommandsCommandParserRuleCall_7_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_7_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getElseIfRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_7_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            otherlv_8=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getElseIfAccess().getRightCurlyBracketKeyword_8());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleElseIf"


    // $ANTLR start "entryRuleElse"
    // InternalCS.g:2075:1: entryRuleElse returns [EObject current=null] : iv_ruleElse= ruleElse EOF ;
    public final EObject entryRuleElse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElse = null;


        try {
            // InternalCS.g:2075:45: (iv_ruleElse= ruleElse EOF )
            // InternalCS.g:2076:2: iv_ruleElse= ruleElse EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleElse=ruleElse();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElse; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleElse"


    // $ANTLR start "ruleElse"
    // InternalCS.g:2082:1: ruleElse returns [EObject current=null] : ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_commands_3_0= ruleCommand ) )* otherlv_4= '}' ) ;
    public final EObject ruleElse() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_commands_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2088:2: ( ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_commands_3_0= ruleCommand ) )* otherlv_4= '}' ) )
            // InternalCS.g:2089:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_commands_3_0= ruleCommand ) )* otherlv_4= '}' )
            {
            // InternalCS.g:2089:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_commands_3_0= ruleCommand ) )* otherlv_4= '}' )
            // InternalCS.g:2090:3: () otherlv_1= 'else' otherlv_2= '{' ( (lv_commands_3_0= ruleCommand ) )* otherlv_4= '}'
            {
            // InternalCS.g:2090:3: ()
            // InternalCS.g:2091:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getElseAccess().getElseAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,37,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getElseAccess().getElseKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getElseAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalCS.g:2105:3: ( (lv_commands_3_0= ruleCommand ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==RULE_ID||LA41_0==26||LA41_0==30||(LA41_0>=33 && LA41_0<=36)||LA41_0==38) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalCS.g:2106:4: (lv_commands_3_0= ruleCommand )
            	    {
            	    // InternalCS.g:2106:4: (lv_commands_3_0= ruleCommand )
            	    // InternalCS.g:2107:5: lv_commands_3_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getElseAccess().getCommandsCommandParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_3_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getElseRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_3_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            otherlv_4=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getElseAccess().getRightCurlyBracketKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleElse"


    // $ANTLR start "entryRuleWhile"
    // InternalCS.g:2132:1: entryRuleWhile returns [EObject current=null] : iv_ruleWhile= ruleWhile EOF ;
    public final EObject entryRuleWhile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhile = null;


        try {
            // InternalCS.g:2132:46: (iv_ruleWhile= ruleWhile EOF )
            // InternalCS.g:2133:2: iv_ruleWhile= ruleWhile EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWhileRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleWhile=ruleWhile();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWhile; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleWhile"


    // $ANTLR start "ruleWhile"
    // InternalCS.g:2139:1: ruleWhile returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_commands_5_0= ruleCommand ) )* otherlv_6= '}' ) ;
    public final EObject ruleWhile() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_expression_2_0 = null;

        EObject lv_commands_5_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2145:2: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_commands_5_0= ruleCommand ) )* otherlv_6= '}' ) )
            // InternalCS.g:2146:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_commands_5_0= ruleCommand ) )* otherlv_6= '}' )
            {
            // InternalCS.g:2146:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_commands_5_0= ruleCommand ) )* otherlv_6= '}' )
            // InternalCS.g:2147:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_commands_5_0= ruleCommand ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,38,FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getWhileAccess().getWhileKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,21,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWhileAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalCS.g:2155:3: ( (lv_expression_2_0= ruleExpression ) )
            // InternalCS.g:2156:4: (lv_expression_2_0= ruleExpression )
            {
            // InternalCS.g:2156:4: (lv_expression_2_0= ruleExpression )
            // InternalCS.g:2157:5: lv_expression_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getWhileAccess().getExpressionExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_36);
            lv_expression_2_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getWhileRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getWhileAccess().getRightParenthesisKeyword_3());
              		
            }
            otherlv_4=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getWhileAccess().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalCS.g:2182:3: ( (lv_commands_5_0= ruleCommand ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID||LA42_0==26||LA42_0==30||(LA42_0>=33 && LA42_0<=36)||LA42_0==38) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalCS.g:2183:4: (lv_commands_5_0= ruleCommand )
            	    {
            	    // InternalCS.g:2183:4: (lv_commands_5_0= ruleCommand )
            	    // InternalCS.g:2184:5: lv_commands_5_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getWhileAccess().getCommandsCommandParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_5_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getWhileRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_5_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            otherlv_6=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getWhileAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleWhile"


    // $ANTLR start "entryRuleAtomic"
    // InternalCS.g:2209:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // InternalCS.g:2209:47: (iv_ruleAtomic= ruleAtomic EOF )
            // InternalCS.g:2210:2: iv_ruleAtomic= ruleAtomic EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAtomicRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAtomic; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAtomic"


    // $ANTLR start "ruleAtomic"
    // InternalCS.g:2216:1: ruleAtomic returns [EObject current=null] : (this_Access_0= ruleAccess | ( () ( (lv_value_2_0= RULE_LONG ) ) ) | ( () ( (lv_value_4_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_6_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        Token lv_value_4_0=null;
        Token lv_value_6_0=null;
        EObject this_Access_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2222:2: ( (this_Access_0= ruleAccess | ( () ( (lv_value_2_0= RULE_LONG ) ) ) | ( () ( (lv_value_4_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_6_0= RULE_STRING ) ) ) ) )
            // InternalCS.g:2223:2: (this_Access_0= ruleAccess | ( () ( (lv_value_2_0= RULE_LONG ) ) ) | ( () ( (lv_value_4_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_6_0= RULE_STRING ) ) ) )
            {
            // InternalCS.g:2223:2: (this_Access_0= ruleAccess | ( () ( (lv_value_2_0= RULE_LONG ) ) ) | ( () ( (lv_value_4_0= RULE_FLOAT ) ) ) | ( () ( (lv_value_6_0= RULE_STRING ) ) ) )
            int alt43=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 26:
            case 34:
                {
                alt43=1;
                }
                break;
            case RULE_LONG:
                {
                alt43=2;
                }
                break;
            case RULE_FLOAT:
                {
                alt43=3;
                }
                break;
            case RULE_STRING:
                {
                alt43=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // InternalCS.g:2224:3: this_Access_0= ruleAccess
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAtomicAccess().getAccessParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Access_0=ruleAccess();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Access_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCS.g:2233:3: ( () ( (lv_value_2_0= RULE_LONG ) ) )
                    {
                    // InternalCS.g:2233:3: ( () ( (lv_value_2_0= RULE_LONG ) ) )
                    // InternalCS.g:2234:4: () ( (lv_value_2_0= RULE_LONG ) )
                    {
                    // InternalCS.g:2234:4: ()
                    // InternalCS.g:2235:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getAtomicAccess().getLongAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:2241:4: ( (lv_value_2_0= RULE_LONG ) )
                    // InternalCS.g:2242:5: (lv_value_2_0= RULE_LONG )
                    {
                    // InternalCS.g:2242:5: (lv_value_2_0= RULE_LONG )
                    // InternalCS.g:2243:6: lv_value_2_0= RULE_LONG
                    {
                    lv_value_2_0=(Token)match(input,RULE_LONG,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_value_2_0, grammarAccess.getAtomicAccess().getValueLONGTerminalRuleCall_1_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAtomicRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"value",
                      							lv_value_2_0,
                      							"m.CS.LONG");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:2261:3: ( () ( (lv_value_4_0= RULE_FLOAT ) ) )
                    {
                    // InternalCS.g:2261:3: ( () ( (lv_value_4_0= RULE_FLOAT ) ) )
                    // InternalCS.g:2262:4: () ( (lv_value_4_0= RULE_FLOAT ) )
                    {
                    // InternalCS.g:2262:4: ()
                    // InternalCS.g:2263:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getAtomicAccess().getFloatAction_2_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:2269:4: ( (lv_value_4_0= RULE_FLOAT ) )
                    // InternalCS.g:2270:5: (lv_value_4_0= RULE_FLOAT )
                    {
                    // InternalCS.g:2270:5: (lv_value_4_0= RULE_FLOAT )
                    // InternalCS.g:2271:6: lv_value_4_0= RULE_FLOAT
                    {
                    lv_value_4_0=(Token)match(input,RULE_FLOAT,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_value_4_0, grammarAccess.getAtomicAccess().getValueFLOATTerminalRuleCall_2_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAtomicRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"value",
                      							lv_value_4_0,
                      							"m.CS.FLOAT");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalCS.g:2289:3: ( () ( (lv_value_6_0= RULE_STRING ) ) )
                    {
                    // InternalCS.g:2289:3: ( () ( (lv_value_6_0= RULE_STRING ) ) )
                    // InternalCS.g:2290:4: () ( (lv_value_6_0= RULE_STRING ) )
                    {
                    // InternalCS.g:2290:4: ()
                    // InternalCS.g:2291:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getAtomicAccess().getStringAction_3_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:2297:4: ( (lv_value_6_0= RULE_STRING ) )
                    // InternalCS.g:2298:5: (lv_value_6_0= RULE_STRING )
                    {
                    // InternalCS.g:2298:5: (lv_value_6_0= RULE_STRING )
                    // InternalCS.g:2299:6: lv_value_6_0= RULE_STRING
                    {
                    lv_value_6_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_value_6_0, grammarAccess.getAtomicAccess().getValueSTRINGTerminalRuleCall_3_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAtomicRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"value",
                      							lv_value_6_0,
                      							"m.CS.STRING");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAtomic"


    // $ANTLR start "entryRuleInitializeVariable"
    // InternalCS.g:2320:1: entryRuleInitializeVariable returns [EObject current=null] : iv_ruleInitializeVariable= ruleInitializeVariable EOF ;
    public final EObject entryRuleInitializeVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInitializeVariable = null;


        try {
            // InternalCS.g:2320:59: (iv_ruleInitializeVariable= ruleInitializeVariable EOF )
            // InternalCS.g:2321:2: iv_ruleInitializeVariable= ruleInitializeVariable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInitializeVariableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInitializeVariable=ruleInitializeVariable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInitializeVariable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleInitializeVariable"


    // $ANTLR start "ruleInitializeVariable"
    // InternalCS.g:2327:1: ruleInitializeVariable returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleExpression ) ) ) ;
    public final EObject ruleInitializeVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2333:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleExpression ) ) ) )
            // InternalCS.g:2334:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleExpression ) ) )
            {
            // InternalCS.g:2334:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleExpression ) ) )
            // InternalCS.g:2335:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleExpression ) )
            {
            // InternalCS.g:2335:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalCS.g:2336:4: (lv_name_0_0= RULE_ID )
            {
            // InternalCS.g:2336:4: (lv_name_0_0= RULE_ID )
            // InternalCS.g:2337:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_39); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_0_0, grammarAccess.getInitializeVariableAccess().getNameIDTerminalRuleCall_0_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getInitializeVariableRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_0_0,
              						"m.CS.ID");
              				
            }

            }


            }

            otherlv_1=(Token)match(input,39,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getInitializeVariableAccess().getEqualsSignKeyword_1());
              		
            }
            // InternalCS.g:2357:3: ( (lv_expression_2_0= ruleExpression ) )
            // InternalCS.g:2358:4: (lv_expression_2_0= ruleExpression )
            {
            // InternalCS.g:2358:4: (lv_expression_2_0= ruleExpression )
            // InternalCS.g:2359:5: lv_expression_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInitializeVariableAccess().getExpressionExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_2_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getInitializeVariableRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"m.CS.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleInitializeVariable"


    // $ANTLR start "entryRuleLambda"
    // InternalCS.g:2380:1: entryRuleLambda returns [EObject current=null] : iv_ruleLambda= ruleLambda EOF ;
    public final EObject entryRuleLambda() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambda = null;


        try {
            // InternalCS.g:2380:47: (iv_ruleLambda= ruleLambda EOF )
            // InternalCS.g:2381:2: iv_ruleLambda= ruleLambda EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLambdaRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLambda=ruleLambda();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLambda; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLambda"


    // $ANTLR start "ruleLambda"
    // InternalCS.g:2387:1: ruleLambda returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_arguments_2_0= ruleArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )* otherlv_5= ')' otherlv_6= '=>' otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )* otherlv_9= '}' ) ;
    public final EObject ruleLambda() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_arguments_2_0 = null;

        EObject lv_arguments_4_0 = null;

        EObject lv_commands_8_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2393:2: ( ( () otherlv_1= '(' ( (lv_arguments_2_0= ruleArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )* otherlv_5= ')' otherlv_6= '=>' otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )* otherlv_9= '}' ) )
            // InternalCS.g:2394:2: ( () otherlv_1= '(' ( (lv_arguments_2_0= ruleArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )* otherlv_5= ')' otherlv_6= '=>' otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )* otherlv_9= '}' )
            {
            // InternalCS.g:2394:2: ( () otherlv_1= '(' ( (lv_arguments_2_0= ruleArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )* otherlv_5= ')' otherlv_6= '=>' otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )* otherlv_9= '}' )
            // InternalCS.g:2395:3: () otherlv_1= '(' ( (lv_arguments_2_0= ruleArgument ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )* otherlv_5= ')' otherlv_6= '=>' otherlv_7= '{' ( (lv_commands_8_0= ruleCommand ) )* otherlv_9= '}'
            {
            // InternalCS.g:2395:3: ()
            // InternalCS.g:2396:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getLambdaAccess().getLambdaAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getLambdaAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalCS.g:2406:3: ( (lv_arguments_2_0= ruleArgument ) )
            // InternalCS.g:2407:4: (lv_arguments_2_0= ruleArgument )
            {
            // InternalCS.g:2407:4: (lv_arguments_2_0= ruleArgument )
            // InternalCS.g:2408:5: lv_arguments_2_0= ruleArgument
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLambdaAccess().getArgumentsArgumentParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_16);
            lv_arguments_2_0=ruleArgument();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getLambdaRule());
              					}
              					add(
              						current,
              						"arguments",
              						lv_arguments_2_0,
              						"m.CS.Argument");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCS.g:2425:3: (otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==16) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalCS.g:2426:4: otherlv_3= ',' ( (lv_arguments_4_0= ruleArgument ) )
            	    {
            	    otherlv_3=(Token)match(input,16,FOLLOW_20); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getLambdaAccess().getCommaKeyword_3_0());
            	      			
            	    }
            	    // InternalCS.g:2430:4: ( (lv_arguments_4_0= ruleArgument ) )
            	    // InternalCS.g:2431:5: (lv_arguments_4_0= ruleArgument )
            	    {
            	    // InternalCS.g:2431:5: (lv_arguments_4_0= ruleArgument )
            	    // InternalCS.g:2432:6: lv_arguments_4_0= ruleArgument
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getLambdaAccess().getArgumentsArgumentParserRuleCall_3_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_16);
            	    lv_arguments_4_0=ruleArgument();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getLambdaRule());
            	      						}
            	      						add(
            	      							current,
            	      							"arguments",
            	      							lv_arguments_4_0,
            	      							"m.CS.Argument");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

            otherlv_5=(Token)match(input,22,FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getLambdaAccess().getRightParenthesisKeyword_4());
              		
            }
            otherlv_6=(Token)match(input,40,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getLambdaAccess().getEqualsSignGreaterThanSignKeyword_5());
              		
            }
            otherlv_7=(Token)match(input,17,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getLambdaAccess().getLeftCurlyBracketKeyword_6());
              		
            }
            // InternalCS.g:2462:3: ( (lv_commands_8_0= ruleCommand ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==RULE_ID||LA45_0==26||LA45_0==30||(LA45_0>=33 && LA45_0<=36)||LA45_0==38) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalCS.g:2463:4: (lv_commands_8_0= ruleCommand )
            	    {
            	    // InternalCS.g:2463:4: (lv_commands_8_0= ruleCommand )
            	    // InternalCS.g:2464:5: lv_commands_8_0= ruleCommand
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getLambdaAccess().getCommandsCommandParserRuleCall_7_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_commands_8_0=ruleCommand();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getLambdaRule());
            	      					}
            	      					add(
            	      						current,
            	      						"commands",
            	      						lv_commands_8_0,
            	      						"m.CS.Command");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);

            otherlv_9=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getLambdaAccess().getRightCurlyBracketKeyword_8());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleLambda"


    // $ANTLR start "entryRuleExpression"
    // InternalCS.g:2489:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalCS.g:2489:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalCS.g:2490:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:2496:1: ruleExpression returns [EObject current=null] : ( ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) ) | this_Lambda_5= ruleLambda | this_Boolean0_6= ruleBoolean0 ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expression_4_0 = null;

        EObject this_Lambda_5 = null;

        EObject this_Boolean0_6 = null;



        	enterRule();

        try {
            // InternalCS.g:2502:2: ( ( ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) ) | this_Lambda_5= ruleLambda | this_Boolean0_6= ruleBoolean0 ) )
            // InternalCS.g:2503:2: ( ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) ) | this_Lambda_5= ruleLambda | this_Boolean0_6= ruleBoolean0 )
            {
            // InternalCS.g:2503:2: ( ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) ) | this_Lambda_5= ruleLambda | this_Boolean0_6= ruleBoolean0 )
            int alt46=3;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // InternalCS.g:2504:3: ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) )
                    {
                    // InternalCS.g:2504:3: ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) )
                    // InternalCS.g:2505:4: ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) )
                    {
                    // InternalCS.g:2522:4: ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) )
                    // InternalCS.g:2523:5: () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) )
                    {
                    // InternalCS.g:2523:5: ()
                    // InternalCS.g:2524:6: 
                    {
                    if ( state.backtracking==0 ) {

                      						current = forceCreateModelElement(
                      							grammarAccess.getExpressionAccess().getCastAction_0_0_0(),
                      							current);
                      					
                    }

                    }

                    otherlv_1=(Token)match(input,21,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_1, grammarAccess.getExpressionAccess().getLeftParenthesisKeyword_0_0_1());
                      				
                    }
                    // InternalCS.g:2534:5: ( (lv_type_2_0= ruleTypeName ) )
                    // InternalCS.g:2535:6: (lv_type_2_0= ruleTypeName )
                    {
                    // InternalCS.g:2535:6: (lv_type_2_0= ruleTypeName )
                    // InternalCS.g:2536:7: lv_type_2_0= ruleTypeName
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getExpressionAccess().getTypeTypeNameParserRuleCall_0_0_2_0());
                      						
                    }
                    pushFollow(FOLLOW_36);
                    lv_type_2_0=ruleTypeName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getExpressionRule());
                      							}
                      							set(
                      								current,
                      								"type",
                      								lv_type_2_0,
                      								"m.CS.TypeName");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,22,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_3, grammarAccess.getExpressionAccess().getRightParenthesisKeyword_0_0_3());
                      				
                    }
                    // InternalCS.g:2557:5: ( (lv_expression_4_0= ruleBoolean0 ) )
                    // InternalCS.g:2558:6: (lv_expression_4_0= ruleBoolean0 )
                    {
                    // InternalCS.g:2558:6: (lv_expression_4_0= ruleBoolean0 )
                    // InternalCS.g:2559:7: lv_expression_4_0= ruleBoolean0
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getExpressionAccess().getExpressionBoolean0ParserRuleCall_0_0_4_0());
                      						
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_4_0=ruleBoolean0();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getExpressionRule());
                      							}
                      							set(
                      								current,
                      								"expression",
                      								lv_expression_4_0,
                      								"m.CS.Boolean0");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:2579:3: this_Lambda_5= ruleLambda
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getExpressionAccess().getLambdaParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Lambda_5=ruleLambda();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Lambda_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCS.g:2588:3: this_Boolean0_6= ruleBoolean0
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getExpressionAccess().getBoolean0ParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Boolean0_6=ruleBoolean0();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Boolean0_6;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleBoolean0"
    // InternalCS.g:2600:1: entryRuleBoolean0 returns [EObject current=null] : iv_ruleBoolean0= ruleBoolean0 EOF ;
    public final EObject entryRuleBoolean0() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean0 = null;


        try {
            // InternalCS.g:2600:49: (iv_ruleBoolean0= ruleBoolean0 EOF )
            // InternalCS.g:2601:2: iv_ruleBoolean0= ruleBoolean0 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBoolean0Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBoolean0=ruleBoolean0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBoolean0; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBoolean0"


    // $ANTLR start "ruleBoolean0"
    // InternalCS.g:2607:1: ruleBoolean0 returns [EObject current=null] : (this_Boolean1_0= ruleBoolean1 ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )* ) ;
    public final EObject ruleBoolean0() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Boolean1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2613:2: ( (this_Boolean1_0= ruleBoolean1 ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )* ) )
            // InternalCS.g:2614:2: (this_Boolean1_0= ruleBoolean1 ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )* )
            {
            // InternalCS.g:2614:2: (this_Boolean1_0= ruleBoolean1 ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )* )
            // InternalCS.g:2615:3: this_Boolean1_0= ruleBoolean1 ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getBoolean0Access().getBoolean1ParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_41);
            this_Boolean1_0=ruleBoolean1();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Boolean1_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:2623:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==41) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalCS.g:2624:4: () otherlv_2= '||' ( (lv_right_3_0= ruleBoolean1 ) )
            	    {
            	    // InternalCS.g:2624:4: ()
            	    // InternalCS.g:2625:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getBoolean0Access().getOrLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    otherlv_2=(Token)match(input,41,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getBoolean0Access().getVerticalLineVerticalLineKeyword_1_1());
            	      			
            	    }
            	    // InternalCS.g:2635:4: ( (lv_right_3_0= ruleBoolean1 ) )
            	    // InternalCS.g:2636:5: (lv_right_3_0= ruleBoolean1 )
            	    {
            	    // InternalCS.g:2636:5: (lv_right_3_0= ruleBoolean1 )
            	    // InternalCS.g:2637:6: lv_right_3_0= ruleBoolean1
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getBoolean0Access().getRightBoolean1ParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_41);
            	    lv_right_3_0=ruleBoolean1();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getBoolean0Rule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"m.CS.Boolean1");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBoolean0"


    // $ANTLR start "entryRuleBoolean1"
    // InternalCS.g:2659:1: entryRuleBoolean1 returns [EObject current=null] : iv_ruleBoolean1= ruleBoolean1 EOF ;
    public final EObject entryRuleBoolean1() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean1 = null;


        try {
            // InternalCS.g:2659:49: (iv_ruleBoolean1= ruleBoolean1 EOF )
            // InternalCS.g:2660:2: iv_ruleBoolean1= ruleBoolean1 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBoolean1Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBoolean1=ruleBoolean1();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBoolean1; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBoolean1"


    // $ANTLR start "ruleBoolean1"
    // InternalCS.g:2666:1: ruleBoolean1 returns [EObject current=null] : (this_Boolean2_0= ruleBoolean2 ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )* ) ;
    public final EObject ruleBoolean1() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Boolean2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2672:2: ( (this_Boolean2_0= ruleBoolean2 ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )* ) )
            // InternalCS.g:2673:2: (this_Boolean2_0= ruleBoolean2 ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )* )
            {
            // InternalCS.g:2673:2: (this_Boolean2_0= ruleBoolean2 ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )* )
            // InternalCS.g:2674:3: this_Boolean2_0= ruleBoolean2 ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getBoolean1Access().getBoolean2ParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_42);
            this_Boolean2_0=ruleBoolean2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Boolean2_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:2682:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==42) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalCS.g:2683:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleBoolean2 ) )
            	    {
            	    // InternalCS.g:2683:4: ()
            	    // InternalCS.g:2684:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getBoolean1Access().getAndLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    otherlv_2=(Token)match(input,42,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getBoolean1Access().getAmpersandAmpersandKeyword_1_1());
            	      			
            	    }
            	    // InternalCS.g:2694:4: ( (lv_right_3_0= ruleBoolean2 ) )
            	    // InternalCS.g:2695:5: (lv_right_3_0= ruleBoolean2 )
            	    {
            	    // InternalCS.g:2695:5: (lv_right_3_0= ruleBoolean2 )
            	    // InternalCS.g:2696:6: lv_right_3_0= ruleBoolean2
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getBoolean1Access().getRightBoolean2ParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_42);
            	    lv_right_3_0=ruleBoolean2();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getBoolean1Rule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"m.CS.Boolean2");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBoolean1"


    // $ANTLR start "entryRuleBoolean2"
    // InternalCS.g:2718:1: entryRuleBoolean2 returns [EObject current=null] : iv_ruleBoolean2= ruleBoolean2 EOF ;
    public final EObject entryRuleBoolean2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean2 = null;


        try {
            // InternalCS.g:2718:49: (iv_ruleBoolean2= ruleBoolean2 EOF )
            // InternalCS.g:2719:2: iv_ruleBoolean2= ruleBoolean2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBoolean2Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBoolean2=ruleBoolean2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBoolean2; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBoolean2"


    // $ANTLR start "ruleBoolean2"
    // InternalCS.g:2725:1: ruleBoolean2 returns [EObject current=null] : (this_Boolean3_0= ruleBoolean3 | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) ) ) ;
    public final EObject ruleBoolean2() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Boolean3_0 = null;

        EObject lv_expression_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2731:2: ( (this_Boolean3_0= ruleBoolean3 | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) ) ) )
            // InternalCS.g:2732:2: (this_Boolean3_0= ruleBoolean3 | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) ) )
            {
            // InternalCS.g:2732:2: (this_Boolean3_0= ruleBoolean3 | ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( ((LA49_0>=RULE_ID && LA49_0<=RULE_STRING)||LA49_0==21||LA49_0==26||LA49_0==34) ) {
                alt49=1;
            }
            else if ( (LA49_0==43) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // InternalCS.g:2733:3: this_Boolean3_0= ruleBoolean3
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getBoolean2Access().getBoolean3ParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Boolean3_0=ruleBoolean3();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Boolean3_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCS.g:2742:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) )
                    {
                    // InternalCS.g:2742:3: ( () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) ) )
                    // InternalCS.g:2743:4: () otherlv_2= '!' ( (lv_expression_3_0= ruleBoolean3 ) )
                    {
                    // InternalCS.g:2743:4: ()
                    // InternalCS.g:2744:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getBoolean2Access().getNotAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,43,FOLLOW_43); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getBoolean2Access().getExclamationMarkKeyword_1_1());
                      			
                    }
                    // InternalCS.g:2754:4: ( (lv_expression_3_0= ruleBoolean3 ) )
                    // InternalCS.g:2755:5: (lv_expression_3_0= ruleBoolean3 )
                    {
                    // InternalCS.g:2755:5: (lv_expression_3_0= ruleBoolean3 )
                    // InternalCS.g:2756:6: lv_expression_3_0= ruleBoolean3
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getBoolean2Access().getExpressionBoolean3ParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_3_0=ruleBoolean3();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getBoolean2Rule());
                      						}
                      						set(
                      							current,
                      							"expression",
                      							lv_expression_3_0,
                      							"m.CS.Boolean3");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBoolean2"


    // $ANTLR start "entryRuleBoolean3"
    // InternalCS.g:2778:1: entryRuleBoolean3 returns [EObject current=null] : iv_ruleBoolean3= ruleBoolean3 EOF ;
    public final EObject entryRuleBoolean3() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean3 = null;


        try {
            // InternalCS.g:2778:49: (iv_ruleBoolean3= ruleBoolean3 EOF )
            // InternalCS.g:2779:2: iv_ruleBoolean3= ruleBoolean3 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBoolean3Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBoolean3=ruleBoolean3();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBoolean3; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBoolean3"


    // $ANTLR start "ruleBoolean3"
    // InternalCS.g:2785:1: ruleBoolean3 returns [EObject current=null] : this_Boolean4_0= ruleBoolean4 ;
    public final EObject ruleBoolean3() throws RecognitionException {
        EObject current = null;

        EObject this_Boolean4_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2791:2: (this_Boolean4_0= ruleBoolean4 )
            // InternalCS.g:2792:2: this_Boolean4_0= ruleBoolean4
            {
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getBoolean3Access().getBoolean4ParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_Boolean4_0=ruleBoolean4();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_Boolean4_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBoolean3"


    // $ANTLR start "entryRuleBoolean4"
    // InternalCS.g:2803:1: entryRuleBoolean4 returns [EObject current=null] : iv_ruleBoolean4= ruleBoolean4 EOF ;
    public final EObject entryRuleBoolean4() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoolean4 = null;


        try {
            // InternalCS.g:2803:49: (iv_ruleBoolean4= ruleBoolean4 EOF )
            // InternalCS.g:2804:2: iv_ruleBoolean4= ruleBoolean4 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBoolean4Rule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBoolean4=ruleBoolean4();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBoolean4; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBoolean4"


    // $ANTLR start "ruleBoolean4"
    // InternalCS.g:2810:1: ruleBoolean4 returns [EObject current=null] : (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) ;
    public final EObject ruleBoolean4() throws RecognitionException {
        EObject current = null;

        EObject this_Addition_0 = null;

        Enumerator lv_type_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2816:2: ( (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? ) )
            // InternalCS.g:2817:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            {
            // InternalCS.g:2817:2: (this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )? )
            // InternalCS.g:2818:3: this_Addition_0= ruleAddition ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getBoolean4Access().getAdditionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_44);
            this_Addition_0=ruleAddition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Addition_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:2826:3: ( () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( ((LA50_0>=24 && LA50_0<=25)||(LA50_0>=58 && LA50_0<=61)) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalCS.g:2827:4: () ( (lv_type_2_0= ruleRelationType ) ) ( (lv_right_3_0= ruleAddition ) )
                    {
                    // InternalCS.g:2827:4: ()
                    // InternalCS.g:2828:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getBoolean4Access().getComparisonLeftAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCS.g:2834:4: ( (lv_type_2_0= ruleRelationType ) )
                    // InternalCS.g:2835:5: (lv_type_2_0= ruleRelationType )
                    {
                    // InternalCS.g:2835:5: (lv_type_2_0= ruleRelationType )
                    // InternalCS.g:2836:6: lv_type_2_0= ruleRelationType
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getBoolean4Access().getTypeRelationTypeEnumRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_43);
                    lv_type_2_0=ruleRelationType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getBoolean4Rule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_2_0,
                      							"m.CS.RelationType");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCS.g:2853:4: ( (lv_right_3_0= ruleAddition ) )
                    // InternalCS.g:2854:5: (lv_right_3_0= ruleAddition )
                    {
                    // InternalCS.g:2854:5: (lv_right_3_0= ruleAddition )
                    // InternalCS.g:2855:6: lv_right_3_0= ruleAddition
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getBoolean4Access().getRightAdditionParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=ruleAddition();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getBoolean4Rule());
                      						}
                      						set(
                      							current,
                      							"right",
                      							lv_right_3_0,
                      							"m.CS.Addition");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBoolean4"


    // $ANTLR start "entryRuleAddition"
    // InternalCS.g:2877:1: entryRuleAddition returns [EObject current=null] : iv_ruleAddition= ruleAddition EOF ;
    public final EObject entryRuleAddition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddition = null;


        try {
            // InternalCS.g:2877:49: (iv_ruleAddition= ruleAddition EOF )
            // InternalCS.g:2878:2: iv_ruleAddition= ruleAddition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAddition=ruleAddition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAddition; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:2884:1: ruleAddition returns [EObject current=null] : (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) ;
    public final EObject ruleAddition() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Multiplication_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2890:2: ( (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* ) )
            // InternalCS.g:2891:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            {
            // InternalCS.g:2891:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )* )
            // InternalCS.g:2892:3: this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_45);
            this_Multiplication_0=ruleMultiplication();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Multiplication_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:2900:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( ((LA52_0>=44 && LA52_0<=45)) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalCS.g:2901:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMultiplication ) )
            	    {
            	    // InternalCS.g:2901:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt51=2;
            	    int LA51_0 = input.LA(1);

            	    if ( (LA51_0==44) ) {
            	        alt51=1;
            	    }
            	    else if ( (LA51_0==45) ) {
            	        alt51=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 51, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt51) {
            	        case 1 :
            	            // InternalCS.g:2902:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalCS.g:2902:5: ( () otherlv_2= '+' )
            	            // InternalCS.g:2903:6: () otherlv_2= '+'
            	            {
            	            // InternalCS.g:2903:6: ()
            	            // InternalCS.g:2904:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_2=(Token)match(input,44,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_2, grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1());
            	              					
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalCS.g:2916:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalCS.g:2916:5: ( () otherlv_4= '-' )
            	            // InternalCS.g:2917:6: () otherlv_4= '-'
            	            {
            	            // InternalCS.g:2917:6: ()
            	            // InternalCS.g:2918:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_4=(Token)match(input,45,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_4, grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1());
            	              					
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // InternalCS.g:2930:4: ( (lv_right_5_0= ruleMultiplication ) )
            	    // InternalCS.g:2931:5: (lv_right_5_0= ruleMultiplication )
            	    {
            	    // InternalCS.g:2931:5: (lv_right_5_0= ruleMultiplication )
            	    // InternalCS.g:2932:6: lv_right_5_0= ruleMultiplication
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_45);
            	    lv_right_5_0=ruleMultiplication();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getAdditionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_5_0,
            	      							"m.CS.Multiplication");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalCS.g:2954:1: entryRuleMultiplication returns [EObject current=null] : iv_ruleMultiplication= ruleMultiplication EOF ;
    public final EObject entryRuleMultiplication() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplication = null;


        try {
            // InternalCS.g:2954:55: (iv_ruleMultiplication= ruleMultiplication EOF )
            // InternalCS.g:2955:2: iv_ruleMultiplication= ruleMultiplication EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMultiplication=ruleMultiplication();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplication; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:2961:1: ruleMultiplication returns [EObject current=null] : (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) ;
    public final EObject ruleMultiplication() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject this_Bitwise_0 = null;

        EObject lv_right_7_0 = null;



        	enterRule();

        try {
            // InternalCS.g:2967:2: ( (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* ) )
            // InternalCS.g:2968:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            {
            // InternalCS.g:2968:2: (this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )* )
            // InternalCS.g:2969:3: this_Bitwise_0= ruleBitwise ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getMultiplicationAccess().getBitwiseParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_46);
            this_Bitwise_0=ruleBitwise();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Bitwise_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:2977:3: ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) ) )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( ((LA54_0>=46 && LA54_0<=48)) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // InternalCS.g:2978:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) ) ( (lv_right_7_0= ruleBitwise ) )
            	    {
            	    // InternalCS.g:2978:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) | ( () otherlv_6= '%' ) )
            	    int alt53=3;
            	    switch ( input.LA(1) ) {
            	    case 46:
            	        {
            	        alt53=1;
            	        }
            	        break;
            	    case 47:
            	        {
            	        alt53=2;
            	        }
            	        break;
            	    case 48:
            	        {
            	        alt53=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 53, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt53) {
            	        case 1 :
            	            // InternalCS.g:2979:5: ( () otherlv_2= '*' )
            	            {
            	            // InternalCS.g:2979:5: ( () otherlv_2= '*' )
            	            // InternalCS.g:2980:6: () otherlv_2= '*'
            	            {
            	            // InternalCS.g:2980:6: ()
            	            // InternalCS.g:2981:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getMultiplicationAccess().getTimesLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_2=(Token)match(input,46,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_2, grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1());
            	              					
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalCS.g:2993:5: ( () otherlv_4= '/' )
            	            {
            	            // InternalCS.g:2993:5: ( () otherlv_4= '/' )
            	            // InternalCS.g:2994:6: () otherlv_4= '/'
            	            {
            	            // InternalCS.g:2994:6: ()
            	            // InternalCS.g:2995:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getMultiplicationAccess().getDivideLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_4=(Token)match(input,47,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_4, grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1());
            	              					
            	            }

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalCS.g:3007:5: ( () otherlv_6= '%' )
            	            {
            	            // InternalCS.g:3007:5: ( () otherlv_6= '%' )
            	            // InternalCS.g:3008:6: () otherlv_6= '%'
            	            {
            	            // InternalCS.g:3008:6: ()
            	            // InternalCS.g:3009:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getMultiplicationAccess().getModulusLeftAction_1_0_2_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_6=(Token)match(input,48,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_6, grammarAccess.getMultiplicationAccess().getPercentSignKeyword_1_0_2_1());
            	              					
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // InternalCS.g:3021:4: ( (lv_right_7_0= ruleBitwise ) )
            	    // InternalCS.g:3022:5: (lv_right_7_0= ruleBitwise )
            	    {
            	    // InternalCS.g:3022:5: (lv_right_7_0= ruleBitwise )
            	    // InternalCS.g:3023:6: lv_right_7_0= ruleBitwise
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getMultiplicationAccess().getRightBitwiseParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_46);
            	    lv_right_7_0=ruleBitwise();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getMultiplicationRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_7_0,
            	      							"m.CS.Bitwise");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRuleBitwise"
    // InternalCS.g:3045:1: entryRuleBitwise returns [EObject current=null] : iv_ruleBitwise= ruleBitwise EOF ;
    public final EObject entryRuleBitwise() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBitwise = null;


        try {
            // InternalCS.g:3045:48: (iv_ruleBitwise= ruleBitwise EOF )
            // InternalCS.g:3046:2: iv_ruleBitwise= ruleBitwise EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBitwiseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBitwise=ruleBitwise();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBitwise; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:3052:1: ruleBitwise returns [EObject current=null] : (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) ;
    public final EObject ruleBitwise() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        EObject this_Unary_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:3058:2: ( (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* ) )
            // InternalCS.g:3059:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            {
            // InternalCS.g:3059:2: (this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )* )
            // InternalCS.g:3060:3: this_Unary_0= ruleUnary ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getBitwiseAccess().getUnaryParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_47);
            this_Unary_0=ruleUnary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Unary_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:3068:3: ( () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) ) )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=49 && LA56_0<=51)) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalCS.g:3069:4: () ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) ) ( (lv_right_3_0= ruleUnary ) )
            	    {
            	    // InternalCS.g:3069:4: ()
            	    // InternalCS.g:3070:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getBitwiseAccess().getBitwiseLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalCS.g:3076:4: ( ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) ) )
            	    // InternalCS.g:3077:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) )
            	    {
            	    // InternalCS.g:3077:5: ( (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' ) )
            	    // InternalCS.g:3078:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' )
            	    {
            	    // InternalCS.g:3078:6: (lv_op_2_1= '&' | lv_op_2_2= '|' | lv_op_2_3= '~' )
            	    int alt55=3;
            	    switch ( input.LA(1) ) {
            	    case 49:
            	        {
            	        alt55=1;
            	        }
            	        break;
            	    case 50:
            	        {
            	        alt55=2;
            	        }
            	        break;
            	    case 51:
            	        {
            	        alt55=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 55, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt55) {
            	        case 1 :
            	            // InternalCS.g:3079:7: lv_op_2_1= '&'
            	            {
            	            lv_op_2_1=(Token)match(input,49,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_1, grammarAccess.getBitwiseAccess().getOpAmpersandKeyword_1_1_0_0());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getBitwiseRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              						
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalCS.g:3090:7: lv_op_2_2= '|'
            	            {
            	            lv_op_2_2=(Token)match(input,50,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_2, grammarAccess.getBitwiseAccess().getOpVerticalLineKeyword_1_1_0_1());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getBitwiseRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              						
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // InternalCS.g:3101:7: lv_op_2_3= '~'
            	            {
            	            lv_op_2_3=(Token)match(input,51,FOLLOW_43); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_3, grammarAccess.getBitwiseAccess().getOpTildeKeyword_1_1_0_2());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getBitwiseRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	              						
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalCS.g:3114:4: ( (lv_right_3_0= ruleUnary ) )
            	    // InternalCS.g:3115:5: (lv_right_3_0= ruleUnary )
            	    {
            	    // InternalCS.g:3115:5: (lv_right_3_0= ruleUnary )
            	    // InternalCS.g:3116:6: lv_right_3_0= ruleUnary
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getBitwiseAccess().getRightUnaryParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_47);
            	    lv_right_3_0=ruleUnary();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getBitwiseRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"m.CS.Unary");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleBitwise"


    // $ANTLR start "entryRuleUnary"
    // InternalCS.g:3138:1: entryRuleUnary returns [EObject current=null] : iv_ruleUnary= ruleUnary EOF ;
    public final EObject entryRuleUnary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary = null;


        try {
            // InternalCS.g:3138:46: (iv_ruleUnary= ruleUnary EOF )
            // InternalCS.g:3139:2: iv_ruleUnary= ruleUnary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnary=ruleUnary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnary; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:3145:1: ruleUnary returns [EObject current=null] : (this_Primary_0= rulePrimary ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )? ) ;
    public final EObject ruleUnary() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Primary_0 = null;



        	enterRule();

        try {
            // InternalCS.g:3151:2: ( (this_Primary_0= rulePrimary ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )? ) )
            // InternalCS.g:3152:2: (this_Primary_0= rulePrimary ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )? )
            {
            // InternalCS.g:3152:2: (this_Primary_0= rulePrimary ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )? )
            // InternalCS.g:3153:3: this_Primary_0= rulePrimary ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getUnaryAccess().getPrimaryParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_48);
            this_Primary_0=rulePrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Primary_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCS.g:3161:3: ( ( () otherlv_2= '++' ) | ( () otherlv_4= '--' ) )?
            int alt57=3;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==31) ) {
                alt57=1;
            }
            else if ( (LA57_0==32) ) {
                alt57=2;
            }
            switch (alt57) {
                case 1 :
                    // InternalCS.g:3162:4: ( () otherlv_2= '++' )
                    {
                    // InternalCS.g:3162:4: ( () otherlv_2= '++' )
                    // InternalCS.g:3163:5: () otherlv_2= '++'
                    {
                    // InternalCS.g:3163:5: ()
                    // InternalCS.g:3164:6: 
                    {
                    if ( state.backtracking==0 ) {

                      						current = forceCreateModelElementAndSet(
                      							grammarAccess.getUnaryAccess().getIncrementLeftAction_1_0_0(),
                      							current);
                      					
                    }

                    }

                    otherlv_2=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_2, grammarAccess.getUnaryAccess().getPlusSignPlusSignKeyword_1_0_1());
                      				
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:3176:4: ( () otherlv_4= '--' )
                    {
                    // InternalCS.g:3176:4: ( () otherlv_4= '--' )
                    // InternalCS.g:3177:5: () otherlv_4= '--'
                    {
                    // InternalCS.g:3177:5: ()
                    // InternalCS.g:3178:6: 
                    {
                    if ( state.backtracking==0 ) {

                      						current = forceCreateModelElementAndSet(
                      							grammarAccess.getUnaryAccess().getDecrementLeftAction_1_1_0(),
                      							current);
                      					
                    }

                    }

                    otherlv_4=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getUnaryAccess().getHyphenMinusHyphenMinusKeyword_1_1_1());
                      				
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleUnary"


    // $ANTLR start "entryRulePrimary"
    // InternalCS.g:3194:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalCS.g:3194:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalCS.g:3195:2: iv_rulePrimary= rulePrimary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimary=rulePrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimary; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

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
    // InternalCS.g:3201:1: rulePrimary returns [EObject current=null] : (this_Atomic_0= ruleAtomic | ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Atomic_0 = null;

        EObject lv_expression_3_0 = null;



        	enterRule();

        try {
            // InternalCS.g:3207:2: ( (this_Atomic_0= ruleAtomic | ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' ) ) )
            // InternalCS.g:3208:2: (this_Atomic_0= ruleAtomic | ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' ) )
            {
            // InternalCS.g:3208:2: (this_Atomic_0= ruleAtomic | ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( ((LA58_0>=RULE_ID && LA58_0<=RULE_STRING)||LA58_0==26||LA58_0==34) ) {
                alt58=1;
            }
            else if ( (LA58_0==21) ) {
                alt58=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalCS.g:3209:3: this_Atomic_0= ruleAtomic
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getPrimaryAccess().getAtomicParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Atomic_0=ruleAtomic();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Atomic_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCS.g:3218:3: ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' )
                    {
                    // InternalCS.g:3218:3: ( () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')' )
                    // InternalCS.g:3219:4: () otherlv_2= '(' ( (lv_expression_3_0= ruleExpression ) ) otherlv_4= ')'
                    {
                    // InternalCS.g:3219:4: ()
                    // InternalCS.g:3220:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getPrimaryAccess().getBracketsAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,21,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_1_1());
                      			
                    }
                    // InternalCS.g:3230:4: ( (lv_expression_3_0= ruleExpression ) )
                    // InternalCS.g:3231:5: (lv_expression_3_0= ruleExpression )
                    {
                    // InternalCS.g:3231:5: (lv_expression_3_0= ruleExpression )
                    // InternalCS.g:3232:6: lv_expression_3_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionExpressionParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_36);
                    lv_expression_3_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getPrimaryRule());
                      						}
                      						set(
                      							current,
                      							"expression",
                      							lv_expression_3_0,
                      							"m.CS.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_1_3());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "rulePrimary"


    // $ANTLR start "ruleTypeEnum"
    // InternalCS.g:3258:1: ruleTypeEnum returns [Enumerator current=null] : ( (enumLiteral_0= 'class' ) | (enumLiteral_1= 'struct' ) ) ;
    public final Enumerator ruleTypeEnum() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalCS.g:3264:2: ( ( (enumLiteral_0= 'class' ) | (enumLiteral_1= 'struct' ) ) )
            // InternalCS.g:3265:2: ( (enumLiteral_0= 'class' ) | (enumLiteral_1= 'struct' ) )
            {
            // InternalCS.g:3265:2: ( (enumLiteral_0= 'class' ) | (enumLiteral_1= 'struct' ) )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==52) ) {
                alt59=1;
            }
            else if ( (LA59_0==53) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // InternalCS.g:3266:3: (enumLiteral_0= 'class' )
                    {
                    // InternalCS.g:3266:3: (enumLiteral_0= 'class' )
                    // InternalCS.g:3267:4: enumLiteral_0= 'class'
                    {
                    enumLiteral_0=(Token)match(input,52,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getTypeEnumAccess().getClassEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getTypeEnumAccess().getClassEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:3274:3: (enumLiteral_1= 'struct' )
                    {
                    // InternalCS.g:3274:3: (enumLiteral_1= 'struct' )
                    // InternalCS.g:3275:4: enumLiteral_1= 'struct'
                    {
                    enumLiteral_1=(Token)match(input,53,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getTypeEnumAccess().getStructEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getTypeEnumAccess().getStructEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleTypeEnum"


    // $ANTLR start "ruleVisibility"
    // InternalCS.g:3285:1: ruleVisibility returns [Enumerator current=null] : ( (enumLiteral_0= 'public' ) | (enumLiteral_1= 'private' ) | (enumLiteral_2= 'protected' ) | (enumLiteral_3= 'internal' ) ) ;
    public final Enumerator ruleVisibility() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalCS.g:3291:2: ( ( (enumLiteral_0= 'public' ) | (enumLiteral_1= 'private' ) | (enumLiteral_2= 'protected' ) | (enumLiteral_3= 'internal' ) ) )
            // InternalCS.g:3292:2: ( (enumLiteral_0= 'public' ) | (enumLiteral_1= 'private' ) | (enumLiteral_2= 'protected' ) | (enumLiteral_3= 'internal' ) )
            {
            // InternalCS.g:3292:2: ( (enumLiteral_0= 'public' ) | (enumLiteral_1= 'private' ) | (enumLiteral_2= 'protected' ) | (enumLiteral_3= 'internal' ) )
            int alt60=4;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt60=1;
                }
                break;
            case 55:
                {
                alt60=2;
                }
                break;
            case 56:
                {
                alt60=3;
                }
                break;
            case 57:
                {
                alt60=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // InternalCS.g:3293:3: (enumLiteral_0= 'public' )
                    {
                    // InternalCS.g:3293:3: (enumLiteral_0= 'public' )
                    // InternalCS.g:3294:4: enumLiteral_0= 'public'
                    {
                    enumLiteral_0=(Token)match(input,54,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:3301:3: (enumLiteral_1= 'private' )
                    {
                    // InternalCS.g:3301:3: (enumLiteral_1= 'private' )
                    // InternalCS.g:3302:4: enumLiteral_1= 'private'
                    {
                    enumLiteral_1=(Token)match(input,55,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getVisibilityAccess().getPrivateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getVisibilityAccess().getPrivateEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:3309:3: (enumLiteral_2= 'protected' )
                    {
                    // InternalCS.g:3309:3: (enumLiteral_2= 'protected' )
                    // InternalCS.g:3310:4: enumLiteral_2= 'protected'
                    {
                    enumLiteral_2=(Token)match(input,56,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getVisibilityAccess().getProtectedEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_2, grammarAccess.getVisibilityAccess().getProtectedEnumLiteralDeclaration_2());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCS.g:3317:3: (enumLiteral_3= 'internal' )
                    {
                    // InternalCS.g:3317:3: (enumLiteral_3= 'internal' )
                    // InternalCS.g:3318:4: enumLiteral_3= 'internal'
                    {
                    enumLiteral_3=(Token)match(input,57,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getVisibilityAccess().getInternalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_3, grammarAccess.getVisibilityAccess().getInternalEnumLiteralDeclaration_3());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleVisibility"


    // $ANTLR start "ruleRelationType"
    // InternalCS.g:3328:1: ruleRelationType returns [Enumerator current=null] : ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '==' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) ;
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
            // InternalCS.g:3334:2: ( ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '==' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) ) )
            // InternalCS.g:3335:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '==' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            {
            // InternalCS.g:3335:2: ( (enumLiteral_0= '>' ) | (enumLiteral_1= '<' ) | (enumLiteral_2= '==' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '<=' ) )
            int alt61=6;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt61=1;
                }
                break;
            case 24:
                {
                alt61=2;
                }
                break;
            case 58:
                {
                alt61=3;
                }
                break;
            case 59:
                {
                alt61=4;
                }
                break;
            case 60:
                {
                alt61=5;
                }
                break;
            case 61:
                {
                alt61=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // InternalCS.g:3336:3: (enumLiteral_0= '>' )
                    {
                    // InternalCS.g:3336:3: (enumLiteral_0= '>' )
                    // InternalCS.g:3337:4: enumLiteral_0= '>'
                    {
                    enumLiteral_0=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getRelationTypeAccess().getOverEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:3344:3: (enumLiteral_1= '<' )
                    {
                    // InternalCS.g:3344:3: (enumLiteral_1= '<' )
                    // InternalCS.g:3345:4: enumLiteral_1= '<'
                    {
                    enumLiteral_1=(Token)match(input,24,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getRelationTypeAccess().getUnderEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:3352:3: (enumLiteral_2= '==' )
                    {
                    // InternalCS.g:3352:3: (enumLiteral_2= '==' )
                    // InternalCS.g:3353:4: enumLiteral_2= '=='
                    {
                    enumLiteral_2=(Token)match(input,58,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_2, grammarAccess.getRelationTypeAccess().getEqualEnumLiteralDeclaration_2());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCS.g:3360:3: (enumLiteral_3= '!=' )
                    {
                    // InternalCS.g:3360:3: (enumLiteral_3= '!=' )
                    // InternalCS.g:3361:4: enumLiteral_3= '!='
                    {
                    enumLiteral_3=(Token)match(input,59,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_3, grammarAccess.getRelationTypeAccess().getNotequalEnumLiteralDeclaration_3());
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalCS.g:3368:3: (enumLiteral_4= '>=' )
                    {
                    // InternalCS.g:3368:3: (enumLiteral_4= '>=' )
                    // InternalCS.g:3369:4: enumLiteral_4= '>='
                    {
                    enumLiteral_4=(Token)match(input,60,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_4, grammarAccess.getRelationTypeAccess().getOverorequalEnumLiteralDeclaration_4());
                      			
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalCS.g:3376:3: (enumLiteral_5= '<=' )
                    {
                    // InternalCS.g:3376:3: (enumLiteral_5= '<=' )
                    // InternalCS.g:3377:4: enumLiteral_5= '<='
                    {
                    enumLiteral_5=(Token)match(input,61,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_5, grammarAccess.getRelationTypeAccess().getUnderorequalEnumLiteralDeclaration_5());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleRelationType"


    // $ANTLR start "ruleAssignmentType"
    // InternalCS.g:3387:1: ruleAssignmentType returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) ) ;
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


        	enterRule();

        try {
            // InternalCS.g:3393:2: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) ) )
            // InternalCS.g:3394:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) )
            {
            // InternalCS.g:3394:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '/=' ) | (enumLiteral_5= '%=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) )
            int alt62=8;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt62=1;
                }
                break;
            case 62:
                {
                alt62=2;
                }
                break;
            case 63:
                {
                alt62=3;
                }
                break;
            case 64:
                {
                alt62=4;
                }
                break;
            case 65:
                {
                alt62=5;
                }
                break;
            case 66:
                {
                alt62=6;
                }
                break;
            case 67:
                {
                alt62=7;
                }
                break;
            case 68:
                {
                alt62=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // InternalCS.g:3395:3: (enumLiteral_0= '=' )
                    {
                    // InternalCS.g:3395:3: (enumLiteral_0= '=' )
                    // InternalCS.g:3396:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,39,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getAssignmentTypeAccess().getSetEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCS.g:3403:3: (enumLiteral_1= '+=' )
                    {
                    // InternalCS.g:3403:3: (enumLiteral_1= '+=' )
                    // InternalCS.g:3404:4: enumLiteral_1= '+='
                    {
                    enumLiteral_1=(Token)match(input,62,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getAssignmentTypeAccess().getIncreaseEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCS.g:3411:3: (enumLiteral_2= '-=' )
                    {
                    // InternalCS.g:3411:3: (enumLiteral_2= '-=' )
                    // InternalCS.g:3412:4: enumLiteral_2= '-='
                    {
                    enumLiteral_2=(Token)match(input,63,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_2, grammarAccess.getAssignmentTypeAccess().getDecreaseEnumLiteralDeclaration_2());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCS.g:3419:3: (enumLiteral_3= '*=' )
                    {
                    // InternalCS.g:3419:3: (enumLiteral_3= '*=' )
                    // InternalCS.g:3420:4: enumLiteral_3= '*='
                    {
                    enumLiteral_3=(Token)match(input,64,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_3, grammarAccess.getAssignmentTypeAccess().getMultiplyEnumLiteralDeclaration_3());
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalCS.g:3427:3: (enumLiteral_4= '/=' )
                    {
                    // InternalCS.g:3427:3: (enumLiteral_4= '/=' )
                    // InternalCS.g:3428:4: enumLiteral_4= '/='
                    {
                    enumLiteral_4=(Token)match(input,65,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_4, grammarAccess.getAssignmentTypeAccess().getDivideEnumLiteralDeclaration_4());
                      			
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalCS.g:3435:3: (enumLiteral_5= '%=' )
                    {
                    // InternalCS.g:3435:3: (enumLiteral_5= '%=' )
                    // InternalCS.g:3436:4: enumLiteral_5= '%='
                    {
                    enumLiteral_5=(Token)match(input,66,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_5, grammarAccess.getAssignmentTypeAccess().getModulusEnumLiteralDeclaration_5());
                      			
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalCS.g:3443:3: (enumLiteral_6= '&=' )
                    {
                    // InternalCS.g:3443:3: (enumLiteral_6= '&=' )
                    // InternalCS.g:3444:4: enumLiteral_6= '&='
                    {
                    enumLiteral_6=(Token)match(input,67,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_6, grammarAccess.getAssignmentTypeAccess().getAndEnumLiteralDeclaration_6());
                      			
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalCS.g:3451:3: (enumLiteral_7= '|=' )
                    {
                    // InternalCS.g:3451:3: (enumLiteral_7= '|=' )
                    // InternalCS.g:3452:4: enumLiteral_7= '|='
                    {
                    enumLiteral_7=(Token)match(input,68,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_7, grammarAccess.getAssignmentTypeAccess().getOrEnumLiteralDeclaration_7());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

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
    // $ANTLR end "ruleAssignmentType"

    // $ANTLR start synpred1_InternalCS
    public final void synpred1_InternalCS_fragment() throws RecognitionException {   
        // InternalCS.g:2505:4: ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )
        // InternalCS.g:2505:5: ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) )
        {
        // InternalCS.g:2505:5: ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) )
        // InternalCS.g:2506:5: () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) )
        {
        // InternalCS.g:2506:5: ()
        // InternalCS.g:2507:5: 
        {
        }

        match(input,21,FOLLOW_5); if (state.failed) return ;
        // InternalCS.g:2509:5: ( ( ruleTypeName ) )
        // InternalCS.g:2510:6: ( ruleTypeName )
        {
        // InternalCS.g:2510:6: ( ruleTypeName )
        // InternalCS.g:2511:7: ruleTypeName
        {
        pushFollow(FOLLOW_36);
        ruleTypeName();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,22,FOLLOW_15); if (state.failed) return ;
        // InternalCS.g:2515:5: ( ( ruleBoolean0 ) )
        // InternalCS.g:2516:6: ( ruleBoolean0 )
        {
        // InternalCS.g:2516:6: ( ruleBoolean0 )
        // InternalCS.g:2517:7: ruleBoolean0
        {
        pushFollow(FOLLOW_2);
        ruleBoolean0();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred1_InternalCS

    // Delegated rules

    public final boolean synpred1_InternalCS() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalCS_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA25 dfa25 = new DFA25(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA46 dfa46 = new DFA46(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\4\2\uffff\3\4\1\14\1\4\1\15\1\uffff\3\4\2\15\1\4\1\15";
    static final String dfa_3s = "\1\65\2\uffff\1\30\2\4\2\30\1\31\1\uffff\3\4\2\31\1\4\1\31";
    static final String dfa_4s = "\1\uffff\1\1\1\2\6\uffff\1\3\7\uffff";
    static final String dfa_5s = "\21\uffff}>";
    static final String[] dfa_6s = {
            "\1\3\11\uffff\1\1\14\uffff\2\2\27\uffff\2\1",
            "",
            "",
            "\1\6\10\uffff\1\4\12\uffff\1\5",
            "\1\7",
            "\1\10",
            "\1\11\10\uffff\1\2\2\uffff\1\2",
            "\1\6\10\uffff\1\4\12\uffff\1\5",
            "\1\12\2\uffff\1\13\10\uffff\1\14",
            "",
            "\1\15",
            "\1\16",
            "\1\6",
            "\1\12\2\uffff\1\13\10\uffff\1\14",
            "\1\17\2\uffff\1\13\10\uffff\1\14",
            "\1\20",
            "\1\17\2\uffff\1\13\10\uffff\1\14"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "446:5: (lv_value_3_1= ruleType | lv_value_3_2= ruleMethod | lv_value_3_3= ruleField )";
        }
    }
    static final String dfa_7s = "\2\uffff\1\3\5\uffff\1\3\10\uffff";
    static final String dfa_8s = "\1\4\1\uffff\1\4\1\uffff\2\4\1\uffff\1\15\4\4\2\15\2\4\1\15";
    static final String dfa_9s = "\1\42\1\uffff\1\104\1\uffff\2\4\1\uffff\1\31\1\104\1\4\1\42\1\4\2\31\1\4\1\42\1\31";
    static final String dfa_10s = "\1\uffff\1\1\1\uffff\1\3\2\uffff\1\2\12\uffff";
    static final String[] dfa_11s = {
            "\1\2\25\uffff\1\3\3\uffff\1\1\3\uffff\1\3",
            "",
            "\1\6\7\uffff\1\3\1\5\3\uffff\1\3\2\uffff\3\3\1\uffff\1\4\1\uffff\1\6\4\uffff\2\3\1\uffff\1\6\4\uffff\1\3\26\uffff\7\3",
            "",
            "\1\7",
            "\1\10",
            "",
            "\1\11\2\uffff\1\6\7\uffff\1\3\1\12",
            "\1\6\7\uffff\1\3\1\5\3\uffff\1\3\2\uffff\3\3\1\uffff\1\13\1\uffff\1\6\4\uffff\2\3\1\uffff\1\6\4\uffff\1\3\26\uffff\7\3",
            "\1\14",
            "\1\6\20\uffff\1\3\4\uffff\1\6\7\uffff\1\6",
            "\1\15",
            "\1\11\2\uffff\1\6\7\uffff\1\3\1\12",
            "\1\16\2\uffff\1\6\7\uffff\1\3\1\17",
            "\1\20",
            "\1\6\20\uffff\1\3\4\uffff\1\6\7\uffff\1\6",
            "\1\16\2\uffff\1\6\7\uffff\1\3\1\17"
    };
    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[][] dfa_11 = unpackEncodedStringArray(dfa_11s);

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = dfa_1;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_5;
            this.transition = dfa_11;
        }
        public String getDescription() {
            return "1177:3: ( ( (lv_var_0_0= 'var' ) ) | ( (lv_type_1_0= ruleTypeName ) ) )?";
        }
    }
    static final String dfa_12s = "\24\uffff";
    static final String dfa_13s = "\1\uffff\1\2\5\uffff\1\2\2\uffff\1\2\11\uffff";
    static final String dfa_14s = "\1\4\1\14\1\uffff\1\4\3\uffff\1\14\2\4\1\14\1\15\2\4\1\25\2\15\1\4\1\25\1\15";
    static final String dfa_15s = "\1\4\1\104\1\uffff\1\42\3\uffff\1\63\2\4\1\63\1\31\2\4\3\31\1\4\2\31";
    static final String dfa_16s = "\2\uffff\1\1\1\uffff\1\3\1\4\1\2\15\uffff";
    static final String dfa_17s = "\24\uffff}>";
    static final String[] dfa_18s = {
            "\1\1",
            "\2\2\2\uffff\1\2\1\4\1\2\1\uffff\1\5\1\6\2\2\1\3\1\2\5\uffff\2\2\6\uffff\1\2\1\uffff\2\2\1\uffff\10\2\6\uffff\13\2",
            "",
            "\1\7\3\2\15\uffff\1\2\4\uffff\1\2\7\uffff\1\2",
            "",
            "",
            "",
            "\1\2\1\10\2\uffff\3\2\1\uffff\4\2\1\11\1\6\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\12",
            "\1\13",
            "\1\2\1\10\2\uffff\3\2\1\uffff\4\2\1\14\1\6\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\15\2\uffff\1\6\7\uffff\1\2\1\16",
            "\1\17",
            "\1\20",
            "\1\2\3\uffff\1\6",
            "\1\21\2\uffff\1\6\7\uffff\1\2\1\22",
            "\1\15\2\uffff\1\6\7\uffff\1\2\1\16",
            "\1\23",
            "\1\2\3\uffff\1\6",
            "\1\21\2\uffff\1\6\7\uffff\1\2\1\22"
    };

    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = dfa_12;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "1455:2: ( ( () ( (lv_name_1_0= RULE_ID ) ) ) | ( () ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= '<' ( (lv_generics_5_0= ruleTypeName ) ) otherlv_6= '>' )? otherlv_7= '(' ( ( (lv_expressions_8_0= ruleExpression ) ) (otherlv_9= ',' ( (lv_expressions_10_0= ruleExpression ) ) )* )? otherlv_11= ')' ) | ( () ( (lv_name_13_0= RULE_ID ) ) otherlv_14= '{' ( ( (lv_initializations_15_0= ruleInitializeVariable ) ) (otherlv_16= ',' ( (lv_initializations_17_0= ruleInitializeVariable ) ) )* )? otherlv_18= '}' ) | ( () ( (lv_name_20_0= RULE_ID ) ) otherlv_21= '[' ( (lv_index_22_0= ruleExpression ) ) otherlv_23= ']' ) )";
        }
    }
    static final String dfa_19s = "\56\uffff";
    static final String dfa_20s = "\11\uffff\1\2\44\uffff";
    static final String dfa_21s = "\2\4\1\uffff\2\4\1\uffff\7\4\1\15\10\uffff\1\4\1\15\7\4\3\15\1\uffff\2\15\6\4\3\15";
    static final String dfa_22s = "\2\53\1\uffff\1\4\1\75\1\uffff\1\75\1\4\1\42\1\75\1\4\1\42\1\75\1\63\10\uffff\1\75\1\63\1\42\2\4\1\26\1\42\1\4\1\25\2\63\1\31\1\uffff\2\63\1\4\1\26\1\4\1\26\1\4\1\25\1\63\1\31\1\63";
    static final String dfa_23s = "\2\uffff\1\3\2\uffff\1\2\10\uffff\10\1\14\uffff\1\1\13\uffff";
    static final String dfa_24s = "\11\uffff\1\1\21\uffff\1\2\12\uffff\1\3\1\uffff\1\0\5\uffff}>";
    static final String[] dfa_25s = {
            "\4\2\15\uffff\1\1\4\uffff\1\2\7\uffff\1\2\10\uffff\1\2",
            "\1\4\3\2\14\uffff\1\5\1\2\4\uffff\1\3\7\uffff\1\2\10\uffff\1\2",
            "",
            "\1\6",
            "\1\5\10\uffff\1\7\3\uffff\1\2\2\uffff\2\2\1\11\1\uffff\1\10\1\2\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2\6\uffff\4\2",
            "",
            "\1\5\10\uffff\1\12\3\uffff\1\2\2\uffff\3\2\1\uffff\1\13\1\2\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2\6\uffff\4\2",
            "\1\14",
            "\1\15\3\2\15\uffff\1\2\4\uffff\1\2\7\uffff\1\2",
            "\1\20\1\21\1\22\1\23\4\uffff\1\2\3\uffff\1\2\1\uffff\1\2\2\uffff\1\24\4\2\1\17\4\uffff\2\2\1\uffff\1\16\6\uffff\2\2\1\25\10\2\6\uffff\4\2",
            "\1\26",
            "\1\27\3\2\15\uffff\1\2\4\uffff\1\2\7\uffff\1\2",
            "\1\5\10\uffff\1\7\3\uffff\1\2\2\uffff\2\2\1\11\1\uffff\1\30\1\2\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2\6\uffff\4\2",
            "\1\31\2\uffff\1\32\1\2\2\uffff\3\2\1\uffff\1\2\1\33\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\5\10\uffff\1\12\3\uffff\1\2\2\uffff\3\2\1\uffff\1\34\1\2\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2\6\uffff\4\2",
            "\1\35\2\uffff\1\5\1\2\2\uffff\3\2\1\uffff\1\2\1\36\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\37\3\2\15\uffff\1\2\4\uffff\1\2\7\uffff\1\2",
            "\1\40",
            "\1\41",
            "\1\5\20\uffff\1\2\1\42",
            "\1\43\3\2\15\uffff\1\2\4\uffff\1\2\7\uffff\1\2",
            "\1\44",
            "\1\5\20\uffff\1\2",
            "\1\45\2\uffff\1\32\1\2\2\uffff\3\2\1\uffff\1\2\1\46\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\31\2\uffff\1\32\1\2\2\uffff\3\2\1\uffff\1\2\1\33\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\47\2\uffff\1\32\10\uffff\1\50",
            "",
            "\1\51\2\uffff\1\5\1\2\2\uffff\3\2\1\uffff\1\2\1\52\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\35\2\uffff\1\5\1\2\2\uffff\3\2\1\uffff\1\2\1\36\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\53",
            "\1\5\20\uffff\1\2\1\42",
            "\1\54",
            "\1\5\21\uffff\1\42",
            "\1\55",
            "\1\5\20\uffff\1\2",
            "\1\45\2\uffff\1\32\1\2\2\uffff\3\2\1\uffff\1\2\1\46\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2",
            "\1\47\2\uffff\1\32\10\uffff\1\50",
            "\1\51\2\uffff\1\5\1\2\2\uffff\3\2\1\uffff\1\2\1\52\5\uffff\2\2\10\uffff\2\2\1\uffff\10\2"
    };

    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[][] dfa_25 = unpackEncodedStringArray(dfa_25s);

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = dfa_19;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "2503:2: ( ( ( ( () '(' ( ( ruleTypeName ) ) ')' ( ( ruleBoolean0 ) ) ) )=> ( () otherlv_1= '(' ( (lv_type_2_0= ruleTypeName ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleBoolean0 ) ) ) ) | this_Lambda_5= ruleLambda | this_Boolean0_6= ruleBoolean0 )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA46_40 = input.LA(1);

                         
                        int index46_40 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA46_40==22) && (synpred1_InternalCS())) {s = 34;}

                        else if ( (LA46_40==RULE_ID) ) {s = 5;}

                         
                        input.seek(index46_40);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA46_9 = input.LA(1);

                         
                        int index46_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA46_9==EOF||LA46_9==12||LA46_9==16||LA46_9==18||(LA46_9>=22 && LA46_9<=25)||(LA46_9>=31 && LA46_9<=32)||(LA46_9>=41 && LA46_9<=42)||(LA46_9>=44 && LA46_9<=51)||(LA46_9>=58 && LA46_9<=61)) ) {s = 2;}

                        else if ( (LA46_9==34) && (synpred1_InternalCS())) {s = 14;}

                        else if ( (LA46_9==26) && (synpred1_InternalCS())) {s = 15;}

                        else if ( (LA46_9==RULE_ID) && (synpred1_InternalCS())) {s = 16;}

                        else if ( (LA46_9==RULE_LONG) && (synpred1_InternalCS())) {s = 17;}

                        else if ( (LA46_9==RULE_FLOAT) && (synpred1_InternalCS())) {s = 18;}

                        else if ( (LA46_9==RULE_STRING) && (synpred1_InternalCS())) {s = 19;}

                        else if ( (LA46_9==21) && (synpred1_InternalCS())) {s = 20;}

                        else if ( (LA46_9==43) && (synpred1_InternalCS())) {s = 21;}

                         
                        input.seek(index46_9);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA46_27 = input.LA(1);

                         
                        int index46_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA46_27==22) && (synpred1_InternalCS())) {s = 34;}

                        else if ( (LA46_27==21) ) {s = 2;}

                        else if ( (LA46_27==RULE_ID) ) {s = 5;}

                         
                        input.seek(index46_27);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA46_38 = input.LA(1);

                         
                        int index46_38 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA46_38==22) && (synpred1_InternalCS())) {s = 34;}

                        else if ( (LA46_38==RULE_ID) ) {s = 5;}

                        else if ( (LA46_38==21) ) {s = 2;}

                         
                        input.seek(index46_38);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 46, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x03C0000000100802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x03C0000000100002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0030000000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x03C0000000140000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x03C0000000100000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0030000018084010L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x00000804042000F0L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000410000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002010000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004100010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000010000010L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000001200000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000004500010L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000005E44040010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000404000010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xC000008180000002L,0x000000000000001FL});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x00000804046000F0L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000444000010L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x00000004042000F0L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x3C00000003000002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0001C00000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x000E000000000002L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000180000002L});

}