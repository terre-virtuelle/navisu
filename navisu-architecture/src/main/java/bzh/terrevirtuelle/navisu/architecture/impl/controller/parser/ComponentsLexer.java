// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g 2017-11-05 18:39:18

package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;

import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.PrintHandler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ComponentsLexer extends Lexer {
	public static final int EOF=-1;
	public static final int COMPONENT_ITEM=4;
	public static final int CONSUMED_EVENT=5;
	public static final int EVENT_PROVIDED=6;
	public static final int IMPLEMENTATION=7;
	public static final int LETTERS=8;
	public static final int NAME=9;
	public static final int NUMBERS=10;
	public static final int POST=11;
	public static final int PRE=12;
	public static final int SEPARATOR=13;
	public static final int SERVICE_PROVIDED=14;
	public static final int STATE=15;
	public static final int SUB_COMPONENT=16;
	public static final int USED_EVENT_SUBSCRIBE=17;
	public static final int USED_SERVICES=18;

	   
	   protected String device;
	   
	   
	   /* Default handlers */
	   protected Handler handler = new PrintHandler();

	   public void setHandler(Handler handler){
	   this.handler = handler;
	   }
	   
	   protected void mismatch(IntStream input, int ttype, BitSet follow)
	   throws RecognitionException {
	        System.out.println("mismatch");
	       throw new MismatchedTokenException(ttype, input);
	    }

	    public Object recoverFromMismatchedSet(IntStream input,
	                          RecognitionException e, BitSet follow) throws RecognitionException {
	        System.out.println("recoverFromMismatchedSet");
	       throw e;
	    }
	   


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ComponentsLexer() {} 
	public ComponentsLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public ComponentsLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g"; }

	// $ANTLR start "PRE"
	public final void mPRE() throws RecognitionException {
		try {
			int _type = PRE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:5: ( '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>' ( LETTERS | NUMBERS | '\\n' )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:7: '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>' ( LETTERS | NUMBERS | '\\n' )*
			{
			match("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:64: ( LETTERS | NUMBERS | '\\n' )*
			loop1:
			while (true) {
				int alt1=4;
				switch ( input.LA(1) ) {
				case '\t':
				case ' ':
				case '!':
				case '\"':
				case '-':
				case '.':
				case '/':
				case ':':
				case '<':
				case '>':
				case '?':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					alt1=1;
					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					{
					alt1=2;
					}
					break;
				case '\n':
					{
					alt1=3;
					}
					break;
				}
				switch (alt1) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:65: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:73: NUMBERS
					{
					mNUMBERS(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:60:81: '\\n'
					{
					match('\n'); 
					}
					break;

				default :
					break loop1;
				}
			}


			handler.doIt(getText());

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRE"

	// $ANTLR start "SEPARATOR"
	public final void mSEPARATOR() throws RecognitionException {
		try {
			int _type = SEPARATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:64:11: ( ( '=' | '-' | ' ' )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:64:13: ( '=' | '-' | ' ' )* '\\n'
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:64:13: ( '=' | '-' | ' ' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==' '||LA2_0=='-'||LA2_0=='=') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
					{
					if ( input.LA(1)==' '||input.LA(1)=='-'||input.LA(1)=='=' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			match('\n'); 

			handler.doIt(getText());

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEPARATOR"

	// $ANTLR start "NAME"
	public final void mNAME() throws RecognitionException {
		try {
			int _type = NAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			CommonToken component=null;

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:68:9: ( 'COMPONENT NAME : ' (component= LETTERS | NUMBERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:68:11: 'COMPONENT NAME : ' (component= LETTERS | NUMBERS )* '\\n'
			{
			match("COMPONENT NAME : "); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:68:31: (component= LETTERS | NUMBERS )*
			loop3:
			while (true) {
				int alt3=3;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='\t'||(LA3_0 >= ' ' && LA3_0 <= '\"')||(LA3_0 >= '-' && LA3_0 <= '/')||LA3_0==':'||LA3_0=='<'||(LA3_0 >= '>' && LA3_0 <= '?')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}
				else if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=2;
				}

				switch (alt3) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:68:32: component= LETTERS
					{
					int componentStart68 = getCharIndex();
					int componentStartLine68 = getLine();
					int componentStartCharPos68 = getCharPositionInLine();
					mLETTERS(); 
					component = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, componentStart68, getCharIndex()-1);
					component.setLine(componentStartLine68);
					component.setCharPositionInLine(componentStartCharPos68);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:68:50: NUMBERS
					{
					mNUMBERS(); 

					}
					break;

				default :
					break loop3;
				}
			}

			match('\n'); 

			       // handler.doIt(getText());
			       System.out.println("COMPONENT : " + component.getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NAME"

	// $ANTLR start "IMPLEMENTATION"
	public final void mIMPLEMENTATION() throws RecognitionException {
		try {
			int _type = IMPLEMENTATION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:73:19: ( 'IMPLEMENTATION : ' ( LETTERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:73:21: 'IMPLEMENTATION : ' ( LETTERS )* '\\n'
			{
			match("IMPLEMENTATION : "); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:73:41: ( LETTERS )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0=='\t'||(LA4_0 >= ' ' && LA4_0 <= '\"')||(LA4_0 >= '-' && LA4_0 <= '/')||LA4_0==':'||LA4_0=='<'||(LA4_0 >= '>' && LA4_0 <= '?')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:73:41: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop4;
				}
			}

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("IMPLEMENTATION : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMPLEMENTATION"

	// $ANTLR start "STATE"
	public final void mSTATE() throws RecognitionException {
		try {
			int _type = STATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:78:10: ( 'STATE :' ( LETTERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:78:12: 'STATE :' ( LETTERS )* '\\n'
			{
			match("STATE :"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:78:21: ( LETTERS )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='\t'||(LA5_0 >= ' ' && LA5_0 <= '\"')||(LA5_0 >= '-' && LA5_0 <= '/')||LA5_0==':'||LA5_0=='<'||(LA5_0 >= '>' && LA5_0 <= '?')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:78:21: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop5;
				}
			}

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("STATE : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STATE"

	// $ANTLR start "SERVICE_PROVIDED"
	public final void mSERVICE_PROVIDED() throws RecognitionException {
		try {
			int _type = SERVICE_PROVIDED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:83:21: ( 'SERVICE PROVIDED:' ( LETTERS | '\\n' )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:83:23: 'SERVICE PROVIDED:' ( LETTERS | '\\n' )*
			{
			match("SERVICE PROVIDED:"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:83:43: ( LETTERS | '\\n' )*
			loop6:
			while (true) {
				int alt6=3;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='\t'||(LA6_0 >= ' ' && LA6_0 <= '\"')||(LA6_0 >= '-' && LA6_0 <= '/')||LA6_0==':'||LA6_0=='<'||(LA6_0 >= '>' && LA6_0 <= '?')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}
				else if ( (LA6_0=='\n') ) {
					alt6=2;
				}

				switch (alt6) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:83:44: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:83:53: '\\n'
					{
					match('\n'); 
					}
					break;

				default :
					break loop6;
				}
			}


			        handler.doIt(getText());
			        // System.out.println("SERVICE_PROVIDED : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SERVICE_PROVIDED"

	// $ANTLR start "EVENT_PROVIDED"
	public final void mEVENT_PROVIDED() throws RecognitionException {
		try {
			int _type = EVENT_PROVIDED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:88:19: ( 'EVENT PROVIDED:' ( LETTERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:88:21: 'EVENT PROVIDED:' ( LETTERS )* '\\n'
			{
			match("EVENT PROVIDED:"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:88:39: ( LETTERS )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0=='\t'||(LA7_0 >= ' ' && LA7_0 <= '\"')||(LA7_0 >= '-' && LA7_0 <= '/')||LA7_0==':'||LA7_0=='<'||(LA7_0 >= '>' && LA7_0 <= '?')||(LA7_0 >= 'A' && LA7_0 <= 'Z')||(LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:88:39: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop7;
				}
			}

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("EVENT_PROVIDED : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EVENT_PROVIDED"

	// $ANTLR start "USED_SERVICES"
	public final void mUSED_SERVICES() throws RecognitionException {
		try {
			int _type = USED_SERVICES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:93:17: ( 'USED SERVICES :' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:93:19: 'USED SERVICES :' LETTERS '\\n'
			{
			match("USED SERVICES :"); 

			mLETTERS(); 

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("USED_SERVICES : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USED_SERVICES"

	// $ANTLR start "CONSUMED_EVENT"
	public final void mCONSUMED_EVENT() throws RecognitionException {
		try {
			int _type = CONSUMED_EVENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:98:19: ( 'CONSUMED EVENT:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:98:21: 'CONSUMED EVENT:' LETTERS '\\n'
			{
			match("CONSUMED EVENT:"); 

			mLETTERS(); 

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("CONSUMED_EVENT : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONSUMED_EVENT"

	// $ANTLR start "USED_EVENT_SUBSCRIBE"
	public final void mUSED_EVENT_SUBSCRIBE() throws RecognitionException {
		try {
			int _type = USED_EVENT_SUBSCRIBE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:103:23: ( 'USED EVENT SUBSCRIBE:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:103:25: 'USED EVENT SUBSCRIBE:' LETTERS '\\n'
			{
			match("USED EVENT SUBSCRIBE:"); 

			mLETTERS(); 

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("USED_EVENT_SUBSCRIBE : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USED_EVENT_SUBSCRIBE"

	// $ANTLR start "SUB_COMPONENT"
	public final void mSUB_COMPONENT() throws RecognitionException {
		try {
			int _type = SUB_COMPONENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:108:18: ( 'SUB COMPONENT:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:108:20: 'SUB COMPONENT:' LETTERS '\\n'
			{
			match("SUB COMPONENT:"); 

			mLETTERS(); 

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("SUB_COMPONENT : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUB_COMPONENT"

	// $ANTLR start "COMPONENT_ITEM"
	public final void mCOMPONENT_ITEM() throws RecognitionException {
		try {
			int _type = COMPONENT_ITEM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:19: ( 'COMPONENT ITEM:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:21: 'COMPONENT ITEM:' LETTERS '\\n'
			{
			match("COMPONENT ITEM:"); 

			mLETTERS(); 

			match('\n'); 

			        handler.doIt(getText());
			        // System.out.println("COMPONENT_ITEM : " + getText());
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMPONENT_ITEM"

	// $ANTLR start "POST"
	public final void mPOST() throws RecognitionException {
		try {
			int _type = POST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:6: ( '</message>' ( LETTERS | NUMBERS | '\\n' )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:8: '</message>' ( LETTERS | NUMBERS | '\\n' )*
			{
			match("</message>"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:21: ( LETTERS | NUMBERS | '\\n' )*
			loop8:
			while (true) {
				int alt8=4;
				switch ( input.LA(1) ) {
				case '\t':
				case ' ':
				case '!':
				case '\"':
				case '-':
				case '.':
				case '/':
				case ':':
				case '<':
				case '>':
				case '?':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					alt8=1;
					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					{
					alt8=2;
					}
					break;
				case '\n':
					{
					alt8=3;
					}
					break;
				}
				switch (alt8) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:22: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:30: NUMBERS
					{
					mNUMBERS(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:118:38: '\\n'
					{
					match('\n'); 
					}
					break;

				default :
					break loop8;
				}
			}


			handler.doIt(getText());

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "POST"

	// $ANTLR start "LETTERS"
	public final void mLETTERS() throws RecognitionException {
		try {
			int _type = LETTERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:125:9: ( ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:125:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:125:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0=='\t'||(LA9_0 >= ' ' && LA9_0 <= '\"')||(LA9_0 >= '-' && LA9_0 <= '/')||LA9_0==':'||LA9_0=='<'||(LA9_0 >= '>' && LA9_0 <= '?')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
					{
					if ( input.LA(1)=='\t'||(input.LA(1) >= ' ' && input.LA(1) <= '\"')||(input.LA(1) >= '-' && input.LA(1) <= '/')||input.LA(1)==':'||input.LA(1)=='<'||(input.LA(1) >= '>' && input.LA(1) <= '?')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}


			 //System.out.println(getText());      
			  
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTERS"

	// $ANTLR start "NUMBERS"
	public final void mNUMBERS() throws RecognitionException {
		try {
			int _type = NUMBERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:129:9: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* )
			int alt13=2;
			alt13 = dfa13.predict(input);
			switch (alt13) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:130:5: ( '0' .. '9' )+
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:130:5: ( '0' .. '9' )+
					int cnt10=0;
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt10 >= 1 ) break loop10;
							EarlyExitException eee = new EarlyExitException(10, input);
							throw eee;
						}
						cnt10++;
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:132:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:132:5: ( '0' .. '9' )+
					int cnt11=0;
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt11 >= 1 ) break loop11;
							EarlyExitException eee = new EarlyExitException(11, input);
							throw eee;
						}
						cnt11++;
					}

					match('.'); 
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:132:21: ( '0' .. '9' )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop12;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBERS"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:8: ( PRE | SEPARATOR | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | LETTERS | NUMBERS )
		int alt14=15;
		alt14 = dfa14.predict(input);
		switch (alt14) {
			case 1 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:10: PRE
				{
				mPRE(); 

				}
				break;
			case 2 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:14: SEPARATOR
				{
				mSEPARATOR(); 

				}
				break;
			case 3 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:24: NAME
				{
				mNAME(); 

				}
				break;
			case 4 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:29: IMPLEMENTATION
				{
				mIMPLEMENTATION(); 

				}
				break;
			case 5 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:44: STATE
				{
				mSTATE(); 

				}
				break;
			case 6 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:50: SERVICE_PROVIDED
				{
				mSERVICE_PROVIDED(); 

				}
				break;
			case 7 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:67: EVENT_PROVIDED
				{
				mEVENT_PROVIDED(); 

				}
				break;
			case 8 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:82: USED_SERVICES
				{
				mUSED_SERVICES(); 

				}
				break;
			case 9 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:96: CONSUMED_EVENT
				{
				mCONSUMED_EVENT(); 

				}
				break;
			case 10 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:111: USED_EVENT_SUBSCRIBE
				{
				mUSED_EVENT_SUBSCRIBE(); 

				}
				break;
			case 11 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:132: SUB_COMPONENT
				{
				mSUB_COMPONENT(); 

				}
				break;
			case 12 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:146: COMPONENT_ITEM
				{
				mCOMPONENT_ITEM(); 

				}
				break;
			case 13 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:161: POST
				{
				mPOST(); 

				}
				break;
			case 14 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:166: LETTERS
				{
				mLETTERS(); 

				}
				break;
			case 15 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:1:174: NUMBERS
				{
				mNUMBERS(); 

				}
				break;

		}
	}


	protected DFA13 dfa13 = new DFA13(this);
	protected DFA14 dfa14 = new DFA14(this);
	static final String DFA13_eotS =
		"\1\uffff\1\2\2\uffff";
	static final String DFA13_eofS =
		"\4\uffff";
	static final String DFA13_minS =
		"\1\60\1\56\2\uffff";
	static final String DFA13_maxS =
		"\2\71\2\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA13_specialS =
		"\4\uffff}>";
	static final String[] DFA13_transitionS = {
			"\12\1",
			"\1\3\1\uffff\12\1",
			"",
			""
	};

	static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
	static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
	static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
	static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
	static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
	static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
	static final short[][] DFA13_transition;

	static {
		int numStates = DFA13_transitionS.length;
		DFA13_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
		}
	}

	protected class DFA13 extends DFA {

		public DFA13(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 13;
			this.eot = DFA13_eot;
			this.eof = DFA13_eof;
			this.min = DFA13_min;
			this.max = DFA13_max;
			this.accept = DFA13_accept;
			this.special = DFA13_special;
			this.transition = DFA13_transition;
		}
		@Override
		public String getDescription() {
			return "129:1: NUMBERS : ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* );";
		}
	}

	static final String DFA14_eotS =
		"\1\uffff\2\11\1\uffff\5\11\2\uffff\103\11\1\uffff\20\11\1\152\11\11\1"+
		"\152\1\uffff\35\11\1\uffff\27\11\1\uffff\1\11\1\uffff\3\11\2\uffff\1\11"+
		"\1\u00b1\1\uffff\2\11\1\uffff\1\11\1\uffff\1\u00b1\1\uffff\5\11\1\uffff";
	static final String DFA14_eofS =
		"\u00b8\uffff";
	static final String DFA14_minS =
		"\1\11\1\57\1\12\1\uffff\1\117\1\115\1\105\1\126\1\123\2\uffff\1\170\1"+
		"\155\1\115\1\120\1\101\1\122\1\102\2\105\1\155\1\145\1\120\1\123\1\114"+
		"\1\124\1\126\1\40\1\116\1\104\1\154\1\163\1\117\1\125\2\105\1\111\1\103"+
		"\1\124\2\40\1\163\1\116\2\115\1\40\1\103\1\117\1\40\1\105\1\166\1\141"+
		"\3\105\1\72\1\105\1\115\1\120\1\105\1\126\1\145\1\147\1\116\1\104\1\116"+
		"\1\11\1\40\1\120\2\122\1\105\1\162\1\145\1\124\1\40\1\124\1\11\1\uffff"+
		"\1\120\2\117\1\126\1\116\1\163\1\76\1\40\1\105\1\101\1\122\1\116\1\126"+
		"\1\111\1\124\1\151\1\11\1\111\1\126\1\124\1\117\1\105\1\111\1\103\1\40"+
		"\1\157\1\11\1\uffff\1\101\1\124\1\105\1\111\1\126\1\116\1\104\1\105\1"+
		"\123\1\156\1\115\1\105\1\116\1\117\1\111\1\124\1\105\1\123\1\125\1\75"+
		"\1\105\1\115\1\124\1\116\1\104\1\72\1\104\1\40\1\102\1\uffff\1\40\2\72"+
		"\1\40\1\105\1\11\2\72\1\123\1\72\2\11\1\72\1\104\3\11\1\103\1\40\2\11"+
		"\1\40\1\72\1\uffff\1\11\1\uffff\1\11\1\122\1\11\2\uffff\2\11\1\uffff\1"+
		"\111\1\11\1\uffff\1\11\1\uffff\1\11\1\uffff\1\102\1\105\1\72\2\11\1\uffff";
	static final String DFA14_maxS =
		"\1\172\1\77\1\75\1\uffff\1\117\1\115\1\125\1\126\1\123\2\uffff\1\170\1"+
		"\155\1\116\1\120\1\101\1\122\1\102\2\105\1\155\1\145\1\120\1\123\1\114"+
		"\1\124\1\126\1\40\1\116\1\104\1\154\1\163\1\117\1\125\2\105\1\111\1\103"+
		"\1\124\2\40\1\163\1\116\2\115\1\40\1\103\1\117\1\40\1\123\1\166\1\141"+
		"\3\105\1\72\1\105\1\115\1\120\1\105\1\126\1\145\1\147\1\116\1\104\1\116"+
		"\1\172\1\40\1\120\2\122\1\105\1\162\1\145\1\124\1\40\1\124\1\172\1\uffff"+
		"\1\120\2\117\1\126\1\116\1\163\1\76\1\40\1\105\1\101\1\122\1\116\1\126"+
		"\1\111\1\124\1\151\1\172\1\116\1\126\1\124\1\117\1\105\1\111\1\103\1\40"+
		"\1\157\1\172\1\uffff\1\101\1\124\1\105\1\111\1\126\1\116\1\104\1\105\1"+
		"\123\1\156\1\115\1\105\1\116\1\117\1\111\1\124\1\105\1\123\1\125\1\75"+
		"\1\105\1\115\1\124\1\116\1\104\1\72\1\104\1\40\1\102\1\uffff\1\40\2\72"+
		"\1\40\1\105\1\172\2\72\1\123\1\72\2\172\1\72\1\104\3\172\1\103\1\40\2"+
		"\172\1\40\1\72\1\uffff\1\172\1\uffff\1\172\1\122\1\172\2\uffff\2\172\1"+
		"\uffff\1\111\1\172\1\uffff\1\172\1\uffff\1\172\1\uffff\1\102\1\105\1\72"+
		"\2\172\1\uffff";
	static final String DFA14_acceptS =
		"\3\uffff\1\2\5\uffff\1\16\1\17\103\uffff\1\5\33\uffff\1\15\35\uffff\1"+
		"\1\27\uffff\1\13\1\uffff\1\7\3\uffff\1\14\1\11\2\uffff\1\10\2\uffff\1"+
		"\3\1\uffff\1\4\1\uffff\1\6\5\uffff\1\12";
	static final String DFA14_specialS =
		"\u00b8\uffff}>";
	static final String[] DFA14_transitionS = {
			"\1\11\1\3\25\uffff\1\2\2\11\12\uffff\1\2\2\11\12\12\1\11\1\uffff\1\1"+
			"\1\3\2\11\1\uffff\2\11\1\4\1\11\1\7\3\11\1\5\11\11\1\6\1\11\1\10\5\11"+
			"\6\uffff\32\11",
			"\1\14\17\uffff\1\13",
			"\1\3\25\uffff\1\2\14\uffff\1\2\17\uffff\1\3",
			"",
			"\1\15",
			"\1\16",
			"\1\20\16\uffff\1\17\1\21",
			"\1\22",
			"\1\23",
			"",
			"",
			"\1\24",
			"\1\25",
			"\1\26\1\27",
			"\1\30",
			"\1\31",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\35",
			"\1\36",
			"\1\37",
			"\1\40",
			"\1\41",
			"\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\74\15\uffff\1\73",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115\1\116\25\uffff\3\115\12\uffff\3\115\12\uffff\1\115\1\uffff\1"+
			"\115\1\uffff\2\115\1\uffff\32\115\6\uffff\32\115",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\115\1\116\25\uffff\3\115\12\uffff\3\115\12\uffff\1\115\1\uffff\1"+
			"\115\1\uffff\2\115\1\uffff\32\115\6\uffff\32\115",
			"",
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
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\1\151\26\uffff\3\151\12\uffff\3\151\12\uffff\1\151\1\uffff\1\151\1"+
			"\uffff\2\151\1\uffff\32\151\6\uffff\32\151",
			"\1\154\4\uffff\1\153",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\1\162",
			"\1\163",
			"\1\164",
			"\1\151\26\uffff\3\151\12\uffff\3\151\12\uffff\1\151\1\uffff\1\151\1"+
			"\uffff\2\151\1\uffff\32\151\6\uffff\32\151",
			"",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097\26\uffff\3\u0097\12\uffff\3\u0097\12\uffff\1\u0097\1\uffff"+
			"\1\u0097\1\uffff\2\u0097\1\uffff\32\u0097\6\uffff\32\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c\26\uffff\3\u009c\12\uffff\3\u009c\12\uffff\1\u009c\1\uffff"+
			"\1\u009c\1\uffff\2\u009c\1\uffff\32\u009c\6\uffff\32\u009c",
			"\1\u009d\26\uffff\3\u009d\12\uffff\3\u009d\12\uffff\1\u009d\1\uffff"+
			"\1\u009d\1\uffff\2\u009d\1\uffff\32\u009d\6\uffff\32\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u0097\1\u00a0\25\uffff\3\u0097\12\uffff\3\u0097\12\uffff\1\u0097"+
			"\1\uffff\1\u0097\1\uffff\2\u0097\1\uffff\32\u0097\6\uffff\32\u0097",
			"\1\u00a1\1\u00a2\25\uffff\3\u00a1\12\uffff\3\u00a1\12\uffff\1\u00a1"+
			"\1\uffff\1\u00a1\1\uffff\2\u00a1\1\uffff\32\u00a1\6\uffff\32\u00a1",
			"\1\u00a3\26\uffff\3\u00a3\12\uffff\3\u00a3\12\uffff\1\u00a3\1\uffff"+
			"\1\u00a3\1\uffff\2\u00a3\1\uffff\32\u00a3\6\uffff\32\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u009c\1\u00a6\25\uffff\3\u009c\12\uffff\3\u009c\12\uffff\1\u009c"+
			"\1\uffff\1\u009c\1\uffff\2\u009c\1\uffff\32\u009c\6\uffff\32\u009c",
			"\1\u009d\1\u00a7\25\uffff\3\u009d\12\uffff\3\u009d\12\uffff\1\u009d"+
			"\1\uffff\1\u009d\1\uffff\2\u009d\1\uffff\32\u009d\6\uffff\32\u009d",
			"\1\u00a8",
			"\1\u00a9",
			"",
			"\1\u00a1\1\u00a2\25\uffff\3\u00a1\12\uffff\3\u00a1\12\uffff\1\u00a1"+
			"\1\uffff\1\u00a1\1\uffff\2\u00a1\1\uffff\32\u00a1\6\uffff\32\u00a1",
			"",
			"\1\u00a3\1\u00aa\25\uffff\3\u00a3\12\uffff\3\u00a3\12\uffff\1\u00a3"+
			"\1\uffff\1\u00a3\1\uffff\2\u00a3\1\uffff\32\u00a3\6\uffff\32\u00a3",
			"\1\u00ab",
			"\1\u00ac\1\u00ad\25\uffff\3\u00ac\12\uffff\3\u00ac\12\u00ad\1\u00ac"+
			"\1\uffff\1\u00ac\1\uffff\2\u00ac\1\uffff\32\u00ac\6\uffff\32\u00ac",
			"",
			"",
			"\1\u00ae\1\u00af\25\uffff\3\u00ae\12\uffff\3\u00ae\12\uffff\1\u00ae"+
			"\1\uffff\1\u00ae\1\uffff\2\u00ae\1\uffff\32\u00ae\6\uffff\32\u00ae",
			"\1\u00b0\26\uffff\3\u00b0\12\uffff\3\u00b0\12\uffff\1\u00b0\1\uffff"+
			"\1\u00b0\1\uffff\2\u00b0\1\uffff\32\u00b0\6\uffff\32\u00b0",
			"",
			"\1\u00b2",
			"\1\u00ac\1\u00ad\25\uffff\3\u00ac\12\uffff\3\u00ac\12\u00ad\1\u00ac"+
			"\1\uffff\1\u00ac\1\uffff\2\u00ac\1\uffff\32\u00ac\6\uffff\32\u00ac",
			"",
			"\1\u00ae\1\u00af\25\uffff\3\u00ae\12\uffff\3\u00ae\12\uffff\1\u00ae"+
			"\1\uffff\1\u00ae\1\uffff\2\u00ae\1\uffff\32\u00ae\6\uffff\32\u00ae",
			"",
			"\1\u00b0\26\uffff\3\u00b0\12\uffff\3\u00b0\12\uffff\1\u00b0\1\uffff"+
			"\1\u00b0\1\uffff\2\u00b0\1\uffff\32\u00b0\6\uffff\32\u00b0",
			"",
			"\1\u00b3",
			"\1\u00b4",
			"\1\u00b5",
			"\1\u00b6\26\uffff\3\u00b6\12\uffff\3\u00b6\12\uffff\1\u00b6\1\uffff"+
			"\1\u00b6\1\uffff\2\u00b6\1\uffff\32\u00b6\6\uffff\32\u00b6",
			"\1\u00b6\1\u00b7\25\uffff\3\u00b6\12\uffff\3\u00b6\12\uffff\1\u00b6"+
			"\1\uffff\1\u00b6\1\uffff\2\u00b6\1\uffff\32\u00b6\6\uffff\32\u00b6",
			""
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

	protected class DFA14 extends DFA {

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
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( PRE | SEPARATOR | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | LETTERS | NUMBERS );";
		}
	}

}
