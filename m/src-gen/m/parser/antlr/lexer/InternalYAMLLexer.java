package m.parser.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalYAMLLexer extends Lexer {
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

    public InternalYAMLLexer() {;} 
    public InternalYAMLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalYAMLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalYAMLLexer.g"; }

    // $ANTLR start "TAGUTagUnity3dCom2011"
    public final void mTAGUTagUnity3dCom2011() throws RecognitionException {
        try {
            int _type = TAGUTagUnity3dCom2011;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:14:23: ( '%TAG !u! tag:unity3d.com,2011:' )
            // InternalYAMLLexer.g:14:25: '%TAG !u! tag:unity3d.com,2011:'
            {
            match("%TAG !u! tag:unity3d.com,2011:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TAGUTagUnity3dCom2011"

    // $ANTLR start "YAML11"
    public final void mYAML11() throws RecognitionException {
        try {
            int _type = YAML11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:16:8: ( '%YAML 1.1' )
            // InternalYAMLLexer.g:16:10: '%YAML 1.1'
            {
            match("%YAML 1.1"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "YAML11"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            int _type = U;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:18:3: ( '!u!' )
            // InternalYAMLLexer.g:18:5: '!u!'
            {
            match("!u!"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "HyphenMinusHyphenMinusHyphenMinus"
    public final void mHyphenMinusHyphenMinusHyphenMinus() throws RecognitionException {
        try {
            int _type = HyphenMinusHyphenMinusHyphenMinus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:20:35: ( '---' )
            // InternalYAMLLexer.g:20:37: '---'
            {
            match("---"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HyphenMinusHyphenMinusHyphenMinus"

    // $ANTLR start "Ampersand"
    public final void mAmpersand() throws RecognitionException {
        try {
            int _type = Ampersand;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:22:11: ( '&' )
            // InternalYAMLLexer.g:22:13: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Ampersand"

    // $ANTLR start "Comma"
    public final void mComma() throws RecognitionException {
        try {
            int _type = Comma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:24:7: ( ',' )
            // InternalYAMLLexer.g:24:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comma"

    // $ANTLR start "HyphenMinus"
    public final void mHyphenMinus() throws RecognitionException {
        try {
            int _type = HyphenMinus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:26:13: ( '-' )
            // InternalYAMLLexer.g:26:15: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HyphenMinus"

    // $ANTLR start "Colon"
    public final void mColon() throws RecognitionException {
        try {
            int _type = Colon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:28:7: ( ':' )
            // InternalYAMLLexer.g:28:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Colon"

    // $ANTLR start "Semicolon"
    public final void mSemicolon() throws RecognitionException {
        try {
            int _type = Semicolon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:30:11: ( ';' )
            // InternalYAMLLexer.g:30:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Semicolon"

    // $ANTLR start "LeftSquareBracket"
    public final void mLeftSquareBracket() throws RecognitionException {
        try {
            int _type = LeftSquareBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:32:19: ( '[' )
            // InternalYAMLLexer.g:32:21: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LeftSquareBracket"

    // $ANTLR start "RightSquareBracket"
    public final void mRightSquareBracket() throws RecognitionException {
        try {
            int _type = RightSquareBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:34:20: ( ']' )
            // InternalYAMLLexer.g:34:22: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RightSquareBracket"

    // $ANTLR start "LeftCurlyBracket"
    public final void mLeftCurlyBracket() throws RecognitionException {
        try {
            int _type = LeftCurlyBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:36:18: ( '{' )
            // InternalYAMLLexer.g:36:20: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LeftCurlyBracket"

    // $ANTLR start "RightCurlyBracket"
    public final void mRightCurlyBracket() throws RecognitionException {
        try {
            int _type = RightCurlyBracket;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:38:19: ( '}' )
            // InternalYAMLLexer.g:38:21: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RightCurlyBracket"

    // $ANTLR start "RULE_WORD"
    public final void mRULE_WORD() throws RecognitionException {
        try {
            int _type = RULE_WORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:40:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( ' ' | '.' | '/' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalYAMLLexer.g:40:13: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( ' ' | '.' | '/' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalYAMLLexer.g:40:37: ( ' ' | '.' | '/' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==' '||(LA1_0>='.' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalYAMLLexer.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1)>='.' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WORD"

    // $ANTLR start "RULE_L"
    public final void mRULE_L() throws RecognitionException {
        try {
            int _type = RULE_L;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:42:8: ( ( '-' )? '0' .. '9' ( '0' .. '9' )* )
            // InternalYAMLLexer.g:42:10: ( '-' )? '0' .. '9' ( '0' .. '9' )*
            {
            // InternalYAMLLexer.g:42:10: ( '-' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalYAMLLexer.g:42:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            matchRange('0','9'); 
            // InternalYAMLLexer.g:42:24: ( '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalYAMLLexer.g:42:25: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_L"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:44:12: ( ( '-' )? ( '0' .. '9' )* '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )
            // InternalYAMLLexer.g:44:14: ( '-' )? ( '0' .. '9' )* '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )?
            {
            // InternalYAMLLexer.g:44:14: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalYAMLLexer.g:44:14: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalYAMLLexer.g:44:19: ( '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalYAMLLexer.g:44:20: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match('.'); 
            // InternalYAMLLexer.g:44:35: ( '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalYAMLLexer.g:44:36: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // InternalYAMLLexer.g:44:47: ( 'e' ( '-' )? ( '0' .. '9' )+ )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='e') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalYAMLLexer.g:44:48: 'e' ( '-' )? ( '0' .. '9' )+
                    {
                    match('e'); 
                    // InternalYAMLLexer.g:44:52: ( '-' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='-') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // InternalYAMLLexer.g:44:52: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // InternalYAMLLexer.g:44:57: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalYAMLLexer.g:44:58: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
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
    // $ANTLR end "RULE_FLOAT"

    // $ANTLR start "RULE_GUID"
    public final void mRULE_GUID() throws RecognitionException {
        try {
            int _type = RULE_GUID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:46:11: ( ( '0' .. '9' )* 'a' .. 'z' ( 'a' .. 'z' | '0' .. '9' )* )
            // InternalYAMLLexer.g:46:13: ( '0' .. '9' )* 'a' .. 'z' ( 'a' .. 'z' | '0' .. '9' )*
            {
            // InternalYAMLLexer.g:46:13: ( '0' .. '9' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalYAMLLexer.g:46:14: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            matchRange('a','z'); 
            // InternalYAMLLexer.g:46:34: ( 'a' .. 'z' | '0' .. '9' )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='0' && LA11_0<='9')||(LA11_0>='a' && LA11_0<='z')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalYAMLLexer.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GUID"

    // $ANTLR start "RULE_SPACE"
    public final void mRULE_SPACE() throws RecognitionException {
        try {
            int _type = RULE_SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:48:12: ( ' ' )
            // InternalYAMLLexer.g:48:14: ' '
            {
            match(' '); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SPACE"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalYAMLLexer.g:50:9: ( RULE_NEWLINE ( RULE_SPACE )* )
            // InternalYAMLLexer.g:50:11: RULE_NEWLINE ( RULE_SPACE )*
            {
            mRULE_NEWLINE(); 
            // InternalYAMLLexer.g:50:24: ( RULE_SPACE )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalYAMLLexer.g:50:24: RULE_SPACE
            	    {
            	    mRULE_SPACE(); 

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_NEWLINE"
    public final void mRULE_NEWLINE() throws RecognitionException {
        try {
            // InternalYAMLLexer.g:52:23: ( ( '\\r' )? '\\n' )
            // InternalYAMLLexer.g:52:25: ( '\\r' )? '\\n'
            {
            // InternalYAMLLexer.g:52:25: ( '\\r' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\r') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalYAMLLexer.g:52:25: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_NEWLINE"

    // $ANTLR start "RULE_BEGIN"
    public final void mRULE_BEGIN() throws RecognitionException {
        try {
            // InternalYAMLLexer.g:54:21: ()
            // InternalYAMLLexer.g:54:23: 
            {
            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_BEGIN"

    // $ANTLR start "RULE_END"
    public final void mRULE_END() throws RecognitionException {
        try {
            // InternalYAMLLexer.g:56:19: ()
            // InternalYAMLLexer.g:56:21: 
            {
            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_END"

    public void mTokens() throws RecognitionException {
        // InternalYAMLLexer.g:1:8: ( TAGUTagUnity3dCom2011 | YAML11 | U | HyphenMinusHyphenMinusHyphenMinus | Ampersand | Comma | HyphenMinus | Colon | Semicolon | LeftSquareBracket | RightSquareBracket | LeftCurlyBracket | RightCurlyBracket | RULE_WORD | RULE_L | RULE_FLOAT | RULE_GUID | RULE_SPACE | RULE_WS )
        int alt14=19;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // InternalYAMLLexer.g:1:10: TAGUTagUnity3dCom2011
                {
                mTAGUTagUnity3dCom2011(); 

                }
                break;
            case 2 :
                // InternalYAMLLexer.g:1:32: YAML11
                {
                mYAML11(); 

                }
                break;
            case 3 :
                // InternalYAMLLexer.g:1:39: U
                {
                mU(); 

                }
                break;
            case 4 :
                // InternalYAMLLexer.g:1:41: HyphenMinusHyphenMinusHyphenMinus
                {
                mHyphenMinusHyphenMinusHyphenMinus(); 

                }
                break;
            case 5 :
                // InternalYAMLLexer.g:1:75: Ampersand
                {
                mAmpersand(); 

                }
                break;
            case 6 :
                // InternalYAMLLexer.g:1:85: Comma
                {
                mComma(); 

                }
                break;
            case 7 :
                // InternalYAMLLexer.g:1:91: HyphenMinus
                {
                mHyphenMinus(); 

                }
                break;
            case 8 :
                // InternalYAMLLexer.g:1:103: Colon
                {
                mColon(); 

                }
                break;
            case 9 :
                // InternalYAMLLexer.g:1:109: Semicolon
                {
                mSemicolon(); 

                }
                break;
            case 10 :
                // InternalYAMLLexer.g:1:119: LeftSquareBracket
                {
                mLeftSquareBracket(); 

                }
                break;
            case 11 :
                // InternalYAMLLexer.g:1:137: RightSquareBracket
                {
                mRightSquareBracket(); 

                }
                break;
            case 12 :
                // InternalYAMLLexer.g:1:156: LeftCurlyBracket
                {
                mLeftCurlyBracket(); 

                }
                break;
            case 13 :
                // InternalYAMLLexer.g:1:173: RightCurlyBracket
                {
                mRightCurlyBracket(); 

                }
                break;
            case 14 :
                // InternalYAMLLexer.g:1:191: RULE_WORD
                {
                mRULE_WORD(); 

                }
                break;
            case 15 :
                // InternalYAMLLexer.g:1:201: RULE_L
                {
                mRULE_L(); 

                }
                break;
            case 16 :
                // InternalYAMLLexer.g:1:208: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 17 :
                // InternalYAMLLexer.g:1:219: RULE_GUID
                {
                mRULE_GUID(); 

                }
                break;
            case 18 :
                // InternalYAMLLexer.g:1:229: RULE_SPACE
                {
                mRULE_SPACE(); 

                }
                break;
            case 19 :
                // InternalYAMLLexer.g:1:240: RULE_WS
                {
                mRULE_WS(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\3\uffff\1\26\10\uffff\1\17\1\31\7\uffff\1\31\1\uffff\1\17\1\31\2\uffff\1\31";
    static final String DFA14_eofS =
        "\34\uffff";
    static final String DFA14_minS =
        "\1\12\1\124\1\uffff\1\55\10\uffff\1\60\1\56\7\uffff\1\56\1\uffff\1\60\1\56\2\uffff\1\56";
    static final String DFA14_maxS =
        "\1\175\1\131\1\uffff\1\71\10\uffff\2\172\7\uffff\1\71\1\uffff\2\172\2\uffff\1\71";
    static final String DFA14_acceptS =
        "\2\uffff\1\3\1\uffff\1\5\1\6\1\10\1\11\1\12\1\13\1\14\1\15\2\uffff\1\20\1\16\1\22\1\23\1\1\1\2\1\4\1\uffff\1\7\2\uffff\1\17\1\21\1\uffff";
    static final String DFA14_specialS =
        "\34\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\21\2\uffff\1\21\22\uffff\1\20\1\2\3\uffff\1\1\1\4\5\uffff\1\5\1\3\1\16\1\uffff\12\15\1\6\1\7\5\uffff\32\17\1\10\1\uffff\1\11\1\uffff\1\17\1\uffff\32\14\1\12\1\uffff\1\13",
            "\1\22\4\uffff\1\23",
            "",
            "\1\24\1\16\1\uffff\12\25",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\27\47\uffff\32\27",
            "\1\16\1\uffff\12\30\47\uffff\32\32",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16\1\uffff\12\33",
            "",
            "\12\27\47\uffff\32\27",
            "\1\16\1\uffff\12\30\47\uffff\32\32",
            "",
            "",
            "\1\16\1\uffff\12\33"
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( TAGUTagUnity3dCom2011 | YAML11 | U | HyphenMinusHyphenMinusHyphenMinus | Ampersand | Comma | HyphenMinus | Colon | Semicolon | LeftSquareBracket | RightSquareBracket | LeftCurlyBracket | RightCurlyBracket | RULE_WORD | RULE_L | RULE_FLOAT | RULE_GUID | RULE_SPACE | RULE_WS );";
        }
    }
 

}