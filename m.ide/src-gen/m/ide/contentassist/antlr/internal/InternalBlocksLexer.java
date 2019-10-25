package m.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalBlocksLexer extends Lexer {
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

    public InternalBlocksLexer() {;} 
    public InternalBlocksLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalBlocksLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalBlocks.g"; }

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:11:6: ( '/>' )
            // InternalBlocks.g:11:8: '/>'
            {
            match("/>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:12:6: ( '<' )
            // InternalBlocks.g:12:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:13:7: ( '>' )
            // InternalBlocks.g:13:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:14:7: ( '</' )
            // InternalBlocks.g:14:9: '</'
            {
            match("</"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:15:7: ( '=' )
            // InternalBlocks.g:15:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "RULE_N"
    public final void mRULE_N() throws RecognitionException {
        try {
            int _type = RULE_N;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:679:8: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? )
            // InternalBlocks.g:679:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )?
            {
            // InternalBlocks.g:679:10: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalBlocks.g:679:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalBlocks.g:679:15: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalBlocks.g:679:16: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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

            // InternalBlocks.g:679:27: ( '.' ( '0' .. '9' )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalBlocks.g:679:28: '.' ( '0' .. '9' )*
                    {
                    match('.'); 
                    // InternalBlocks.g:679:32: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalBlocks.g:679:33: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_N"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:681:9: ( ( '_' | 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '-' )* )
            // InternalBlocks.g:681:11: ( '_' | 'a' .. 'z' | 'A' .. 'Z' ) ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '-' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalBlocks.g:681:35: ( '_' | 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '.' | '-' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='-' && LA5_0<='.')||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalBlocks.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_WHITE_SPACE"
    public final void mRULE_WHITE_SPACE() throws RecognitionException {
        try {
            int _type = RULE_WHITE_SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:683:18: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // InternalBlocks.g:683:20: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WHITE_SPACE"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalBlocks.g:685:13: ( ( '\"' ( options {greedy=false; } : . )* '\"' | '\\'' ( options {greedy=false; } : . )* '\\'' ) )
            // InternalBlocks.g:685:15: ( '\"' ( options {greedy=false; } : . )* '\"' | '\\'' ( options {greedy=false; } : . )* '\\'' )
            {
            // InternalBlocks.g:685:15: ( '\"' ( options {greedy=false; } : . )* '\"' | '\\'' ( options {greedy=false; } : . )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalBlocks.g:685:16: '\"' ( options {greedy=false; } : . )* '\"'
                    {
                    match('\"'); 
                    // InternalBlocks.g:685:20: ( options {greedy=false; } : . )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\"') ) {
                            alt6=2;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='\uFFFF')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalBlocks.g:685:48: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalBlocks.g:685:56: '\\'' ( options {greedy=false; } : . )* '\\''
                    {
                    match('\''); 
                    // InternalBlocks.g:685:61: ( options {greedy=false; } : . )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\'') ) {
                            alt7=2;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='\uFFFF')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalBlocks.g:685:89: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    public void mTokens() throws RecognitionException {
        // InternalBlocks.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | RULE_N | RULE_ID | RULE_WHITE_SPACE | RULE_STRING )
        int alt9=9;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // InternalBlocks.g:1:10: T__8
                {
                mT__8(); 

                }
                break;
            case 2 :
                // InternalBlocks.g:1:15: T__9
                {
                mT__9(); 

                }
                break;
            case 3 :
                // InternalBlocks.g:1:20: T__10
                {
                mT__10(); 

                }
                break;
            case 4 :
                // InternalBlocks.g:1:26: T__11
                {
                mT__11(); 

                }
                break;
            case 5 :
                // InternalBlocks.g:1:32: T__12
                {
                mT__12(); 

                }
                break;
            case 6 :
                // InternalBlocks.g:1:38: RULE_N
                {
                mRULE_N(); 

                }
                break;
            case 7 :
                // InternalBlocks.g:1:45: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 8 :
                // InternalBlocks.g:1:53: RULE_WHITE_SPACE
                {
                mRULE_WHITE_SPACE(); 

                }
                break;
            case 9 :
                // InternalBlocks.g:1:70: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\2\uffff\1\12\10\uffff";
    static final String DFA9_eofS =
        "\13\uffff";
    static final String DFA9_minS =
        "\1\11\1\uffff\1\57\10\uffff";
    static final String DFA9_maxS =
        "\1\172\1\uffff\1\57\10\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\5\1\6\1\7\1\10\1\11\1\4\1\2";
    static final String DFA9_specialS =
        "\13\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\7\2\uffff\1\7\22\uffff\1\7\1\uffff\1\10\4\uffff\1\10\5\uffff\1\5\1\uffff\1\1\12\5\2\uffff\1\2\1\4\1\3\2\uffff\32\6\4\uffff\1\6\1\uffff\32\6",
            "",
            "\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__8 | T__9 | T__10 | T__11 | T__12 | RULE_N | RULE_ID | RULE_WHITE_SPACE | RULE_STRING );";
        }
    }
 

}