package m.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTestuaLexer extends Lexer {
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

    public InternalTestuaLexer() {;} 
    public InternalTestuaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalTestuaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalTestua.g"; }

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:11:6: ( ':' )
            // InternalTestua.g:11:8: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:12:6: ( '{' )
            // InternalTestua.g:12:8: '{'
            {
            match('{'); 

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
            // InternalTestua.g:13:6: ( ',' )
            // InternalTestua.g:13:8: ','
            {
            match(','); 

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
            // InternalTestua.g:14:7: ( '}' )
            // InternalTestua.g:14:9: '}'
            {
            match('}'); 

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
            // InternalTestua.g:15:7: ( '[' )
            // InternalTestua.g:15:9: '['
            {
            match('['); 

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
            // InternalTestua.g:16:7: ( ']' )
            // InternalTestua.g:16:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:17:7: ( 'entitate' )
            // InternalTestua.g:17:9: 'entitate'
            {
            match("entitate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:18:7: ( 'bakoitzarengatik' )
            // InternalTestua.g:18:9: 'bakoitzarengatik'
            {
            match("bakoitzarengatik"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:19:7: ( 'hurrengo' )
            // InternalTestua.g:19:9: 'hurrengo'
            {
            match("hurrengo"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:20:7: ( 'etiketekin' )
            // InternalTestua.g:20:9: 'etiketekin'
            {
            match("etiketekin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:21:7: ( 'baldin' )
            // InternalTestua.g:21:9: 'baldin'
            {
            match("baldin"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:22:7: ( 'eta' )
            // InternalTestua.g:22:9: 'eta'
            {
            match("eta"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:23:7: ( 'edo' )
            // InternalTestua.g:23:9: 'edo'
            {
            match("edo"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:24:7: ( 'bestela' )
            // InternalTestua.g:24:9: 'bestela'
            {
            match("bestela"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:25:7: ( '(' )
            // InternalTestua.g:25:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:26:7: ( ')' )
            // InternalTestua.g:26:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:27:7: ( '.' )
            // InternalTestua.g:27:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:28:7: ( '||' )
            // InternalTestua.g:28:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:29:7: ( '&&' )
            // InternalTestua.g:29:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:30:7: ( '!' )
            // InternalTestua.g:30:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:31:7: ( '+' )
            // InternalTestua.g:31:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:32:7: ( '-' )
            // InternalTestua.g:32:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:33:7: ( '*' )
            // InternalTestua.g:33:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:34:7: ( '/' )
            // InternalTestua.g:34:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:35:7: ( '%' )
            // InternalTestua.g:35:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:36:7: ( '&' )
            // InternalTestua.g:36:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:37:7: ( '|' )
            // InternalTestua.g:37:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:38:7: ( '^' )
            // InternalTestua.g:38:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:39:7: ( '<<' )
            // InternalTestua.g:39:9: '<<'
            {
            match("<<"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:40:7: ( '>>' )
            // InternalTestua.g:40:9: '>>'
            {
            match(">>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:41:7: ( '~' )
            // InternalTestua.g:41:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:42:7: ( '++' )
            // InternalTestua.g:42:9: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:43:7: ( '--' )
            // InternalTestua.g:43:9: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:44:7: ( '>' )
            // InternalTestua.g:44:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:45:7: ( '<' )
            // InternalTestua.g:45:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:46:7: ( '=' )
            // InternalTestua.g:46:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:47:7: ( '!=' )
            // InternalTestua.g:47:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:48:7: ( '>=' )
            // InternalTestua.g:48:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:49:7: ( '<=' )
            // InternalTestua.g:49:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:50:7: ( '+=' )
            // InternalTestua.g:50:9: '+='
            {
            match("+="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:51:7: ( '-=' )
            // InternalTestua.g:51:9: '-='
            {
            match("-="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:52:7: ( '*=' )
            // InternalTestua.g:52:9: '*='
            {
            match("*="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:53:7: ( '/=' )
            // InternalTestua.g:53:9: '/='
            {
            match("/="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:54:7: ( '%=' )
            // InternalTestua.g:54:9: '%='
            {
            match("%="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:55:7: ( '&=' )
            // InternalTestua.g:55:9: '&='
            {
            match("&="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:56:7: ( '|=' )
            // InternalTestua.g:56:9: '|='
            {
            match("|="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:57:7: ( '^=' )
            // InternalTestua.g:57:9: '^='
            {
            match("^="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:58:7: ( '<<=' )
            // InternalTestua.g:58:9: '<<='
            {
            match("<<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:59:7: ( '>>=' )
            // InternalTestua.g:59:9: '>>='
            {
            match(">>="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "RULE_WHITE_SPACE"
    public final void mRULE_WHITE_SPACE() throws RecognitionException {
        try {
            int _type = RULE_WHITE_SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:1898:18: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalTestua.g:1898:20: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalTestua.g:1898:20: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTestua.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WHITE_SPACE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:1900:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalTestua.g:1900:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalTestua.g:1900:35: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTestua.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
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

    // $ANTLR start "RULE_REAL"
    public final void mRULE_REAL() throws RecognitionException {
        try {
            int _type = RULE_REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalTestua.g:1902:11: ( ( '-' )? ( '0' .. '9' )* ( '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )? )
            // InternalTestua.g:1902:13: ( '-' )? ( '0' .. '9' )* ( '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )?
            {
            // InternalTestua.g:1902:13: ( '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalTestua.g:1902:13: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalTestua.g:1902:18: ( '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalTestua.g:1902:19: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalTestua.g:1902:30: ( '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='.') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTestua.g:1902:31: '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )?
                    {
                    match('.'); 
                    // InternalTestua.g:1902:35: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalTestua.g:1902:36: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

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

                    // InternalTestua.g:1902:47: ( 'e' ( '-' )? ( '0' .. '9' )+ )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='e') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // InternalTestua.g:1902:48: 'e' ( '-' )? ( '0' .. '9' )+
                            {
                            match('e'); 
                            // InternalTestua.g:1902:52: ( '-' )?
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0=='-') ) {
                                alt6=1;
                            }
                            switch (alt6) {
                                case 1 :
                                    // InternalTestua.g:1902:52: '-'
                                    {
                                    match('-'); 

                                    }
                                    break;

                            }

                            // InternalTestua.g:1902:57: ( '0' .. '9' )+
                            int cnt7=0;
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // InternalTestua.g:1902:58: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt7 >= 1 ) break loop7;
                                        EarlyExitException eee =
                                            new EarlyExitException(7, input);
                                        throw eee;
                                }
                                cnt7++;
                            } while (true);


                            }
                            break;

                    }


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
    // $ANTLR end "RULE_REAL"

    public void mTokens() throws RecognitionException {
        // InternalTestua.g:1:8: ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | RULE_WHITE_SPACE | RULE_ID | RULE_REAL )
        int alt10=52;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // InternalTestua.g:1:10: T__7
                {
                mT__7(); 

                }
                break;
            case 2 :
                // InternalTestua.g:1:15: T__8
                {
                mT__8(); 

                }
                break;
            case 3 :
                // InternalTestua.g:1:20: T__9
                {
                mT__9(); 

                }
                break;
            case 4 :
                // InternalTestua.g:1:25: T__10
                {
                mT__10(); 

                }
                break;
            case 5 :
                // InternalTestua.g:1:31: T__11
                {
                mT__11(); 

                }
                break;
            case 6 :
                // InternalTestua.g:1:37: T__12
                {
                mT__12(); 

                }
                break;
            case 7 :
                // InternalTestua.g:1:43: T__13
                {
                mT__13(); 

                }
                break;
            case 8 :
                // InternalTestua.g:1:49: T__14
                {
                mT__14(); 

                }
                break;
            case 9 :
                // InternalTestua.g:1:55: T__15
                {
                mT__15(); 

                }
                break;
            case 10 :
                // InternalTestua.g:1:61: T__16
                {
                mT__16(); 

                }
                break;
            case 11 :
                // InternalTestua.g:1:67: T__17
                {
                mT__17(); 

                }
                break;
            case 12 :
                // InternalTestua.g:1:73: T__18
                {
                mT__18(); 

                }
                break;
            case 13 :
                // InternalTestua.g:1:79: T__19
                {
                mT__19(); 

                }
                break;
            case 14 :
                // InternalTestua.g:1:85: T__20
                {
                mT__20(); 

                }
                break;
            case 15 :
                // InternalTestua.g:1:91: T__21
                {
                mT__21(); 

                }
                break;
            case 16 :
                // InternalTestua.g:1:97: T__22
                {
                mT__22(); 

                }
                break;
            case 17 :
                // InternalTestua.g:1:103: T__23
                {
                mT__23(); 

                }
                break;
            case 18 :
                // InternalTestua.g:1:109: T__24
                {
                mT__24(); 

                }
                break;
            case 19 :
                // InternalTestua.g:1:115: T__25
                {
                mT__25(); 

                }
                break;
            case 20 :
                // InternalTestua.g:1:121: T__26
                {
                mT__26(); 

                }
                break;
            case 21 :
                // InternalTestua.g:1:127: T__27
                {
                mT__27(); 

                }
                break;
            case 22 :
                // InternalTestua.g:1:133: T__28
                {
                mT__28(); 

                }
                break;
            case 23 :
                // InternalTestua.g:1:139: T__29
                {
                mT__29(); 

                }
                break;
            case 24 :
                // InternalTestua.g:1:145: T__30
                {
                mT__30(); 

                }
                break;
            case 25 :
                // InternalTestua.g:1:151: T__31
                {
                mT__31(); 

                }
                break;
            case 26 :
                // InternalTestua.g:1:157: T__32
                {
                mT__32(); 

                }
                break;
            case 27 :
                // InternalTestua.g:1:163: T__33
                {
                mT__33(); 

                }
                break;
            case 28 :
                // InternalTestua.g:1:169: T__34
                {
                mT__34(); 

                }
                break;
            case 29 :
                // InternalTestua.g:1:175: T__35
                {
                mT__35(); 

                }
                break;
            case 30 :
                // InternalTestua.g:1:181: T__36
                {
                mT__36(); 

                }
                break;
            case 31 :
                // InternalTestua.g:1:187: T__37
                {
                mT__37(); 

                }
                break;
            case 32 :
                // InternalTestua.g:1:193: T__38
                {
                mT__38(); 

                }
                break;
            case 33 :
                // InternalTestua.g:1:199: T__39
                {
                mT__39(); 

                }
                break;
            case 34 :
                // InternalTestua.g:1:205: T__40
                {
                mT__40(); 

                }
                break;
            case 35 :
                // InternalTestua.g:1:211: T__41
                {
                mT__41(); 

                }
                break;
            case 36 :
                // InternalTestua.g:1:217: T__42
                {
                mT__42(); 

                }
                break;
            case 37 :
                // InternalTestua.g:1:223: T__43
                {
                mT__43(); 

                }
                break;
            case 38 :
                // InternalTestua.g:1:229: T__44
                {
                mT__44(); 

                }
                break;
            case 39 :
                // InternalTestua.g:1:235: T__45
                {
                mT__45(); 

                }
                break;
            case 40 :
                // InternalTestua.g:1:241: T__46
                {
                mT__46(); 

                }
                break;
            case 41 :
                // InternalTestua.g:1:247: T__47
                {
                mT__47(); 

                }
                break;
            case 42 :
                // InternalTestua.g:1:253: T__48
                {
                mT__48(); 

                }
                break;
            case 43 :
                // InternalTestua.g:1:259: T__49
                {
                mT__49(); 

                }
                break;
            case 44 :
                // InternalTestua.g:1:265: T__50
                {
                mT__50(); 

                }
                break;
            case 45 :
                // InternalTestua.g:1:271: T__51
                {
                mT__51(); 

                }
                break;
            case 46 :
                // InternalTestua.g:1:277: T__52
                {
                mT__52(); 

                }
                break;
            case 47 :
                // InternalTestua.g:1:283: T__53
                {
                mT__53(); 

                }
                break;
            case 48 :
                // InternalTestua.g:1:289: T__54
                {
                mT__54(); 

                }
                break;
            case 49 :
                // InternalTestua.g:1:295: T__55
                {
                mT__55(); 

                }
                break;
            case 50 :
                // InternalTestua.g:1:301: RULE_WHITE_SPACE
                {
                mRULE_WHITE_SPACE(); 

                }
                break;
            case 51 :
                // InternalTestua.g:1:318: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 52 :
                // InternalTestua.g:1:326: RULE_REAL
                {
                mRULE_REAL(); 

                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\1\34\6\uffff\3\33\2\uffff\1\43\1\46\1\51\1\53\1\56\1\61\1\63\1\65\1\67\1\71\1\74\1\77\5\uffff\6\33\27\uffff\1\111\2\uffff\1\113\2\uffff\2\33\1\116\1\117\4\33\4\uffff\2\33\2\uffff\15\33\1\143\5\33\1\uffff\1\151\1\33\1\153\2\33\1\uffff\1\156\1\uffff\2\33\1\uffff\1\161\1\33\1\uffff\5\33\1\170\1\uffff";
    static final String DFA10_eofS =
        "\171\uffff";
    static final String DFA10_minS =
        "\1\11\6\uffff\1\144\1\141\1\165\2\uffff\1\60\1\75\1\46\1\75\1\53\1\55\4\75\1\74\1\75\5\uffff\1\164\1\141\1\157\1\153\1\163\1\162\27\uffff\1\75\2\uffff\1\75\2\uffff\1\151\1\153\2\60\1\157\1\144\1\164\1\162\4\uffff\1\164\1\145\2\uffff\2\151\2\145\1\141\2\164\1\156\1\154\1\156\1\164\1\145\1\172\1\60\1\141\1\147\1\145\1\153\1\141\1\uffff\1\60\1\157\1\60\1\151\1\162\1\uffff\1\60\1\uffff\1\156\1\145\1\uffff\1\60\1\156\1\uffff\1\147\1\141\1\164\1\151\1\153\1\60\1\uffff";
    static final String DFA10_maxS =
        "\1\176\6\uffff\1\164\1\145\1\165\2\uffff\1\71\1\174\11\75\1\76\5\uffff\1\164\1\151\1\157\1\154\1\163\1\162\27\uffff\1\75\2\uffff\1\75\2\uffff\1\151\1\153\2\172\1\157\1\144\1\164\1\162\4\uffff\1\164\1\145\2\uffff\2\151\2\145\1\141\2\164\1\156\1\154\1\156\1\164\1\145\2\172\1\141\1\147\1\145\1\153\1\141\1\uffff\1\172\1\157\1\172\1\151\1\162\1\uffff\1\172\1\uffff\1\156\1\145\1\uffff\1\172\1\156\1\uffff\1\147\1\141\1\164\1\151\1\153\1\172\1\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\3\uffff\1\17\1\20\14\uffff\1\37\1\44\1\62\1\63\1\64\6\uffff\1\21\1\22\1\56\1\33\1\23\1\55\1\32\1\45\1\24\1\40\1\50\1\25\1\41\1\51\1\26\1\52\1\27\1\53\1\30\1\54\1\31\1\57\1\34\1\uffff\1\47\1\43\1\uffff\1\46\1\42\10\uffff\1\60\1\35\1\61\1\36\2\uffff\1\14\1\15\23\uffff\1\13\5\uffff\1\16\1\uffff\1\7\2\uffff\1\11\2\uffff\1\12\6\uffff\1\10";
    static final String DFA10_specialS =
        "\171\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\32\2\uffff\1\32\22\uffff\1\32\1\17\3\uffff\1\24\1\16\1\uffff\1\12\1\13\1\22\1\20\1\3\1\21\1\14\1\23\12\uffff\1\1\1\uffff\1\26\1\31\1\27\2\uffff\32\33\1\5\1\uffff\1\6\1\25\1\33\1\uffff\1\33\1\10\2\33\1\7\2\33\1\11\22\33\1\2\1\15\1\4\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\37\11\uffff\1\35\5\uffff\1\36",
            "\1\40\3\uffff\1\41",
            "\1\42",
            "",
            "",
            "\12\34",
            "\1\45\76\uffff\1\44",
            "\1\47\26\uffff\1\50",
            "\1\52",
            "\1\54\21\uffff\1\55",
            "\1\57\1\34\1\uffff\12\34\3\uffff\1\60",
            "\1\62",
            "\1\64",
            "\1\66",
            "\1\70",
            "\1\72\1\73",
            "\1\76\1\75",
            "",
            "",
            "",
            "",
            "",
            "\1\100",
            "\1\102\7\uffff\1\101",
            "\1\103",
            "\1\104\1\105",
            "\1\106",
            "\1\107",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\110",
            "",
            "",
            "\1\112",
            "",
            "",
            "\1\114",
            "\1\115",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "",
            "",
            "",
            "",
            "\1\124",
            "\1\125",
            "",
            "",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\152",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\154",
            "\1\155",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "",
            "\1\157",
            "\1\160",
            "",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            "\1\162",
            "",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | RULE_WHITE_SPACE | RULE_ID | RULE_REAL );";
        }
    }
 

}