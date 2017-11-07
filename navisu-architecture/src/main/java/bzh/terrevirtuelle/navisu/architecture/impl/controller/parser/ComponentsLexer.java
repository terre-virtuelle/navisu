// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g 2017-11-06 20:31:58

package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;

import org.netbeans.api.visual.vmd.VMDGraphScene;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.PrintComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentView;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.util.regex.Pattern;


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

	   
	   protected ComponentView componentView;
	   protected Component component;
	   protected VMDGraphScene scene;
	   
	   /* Default handlers */
	   protected Handler handler = new PrintComponentHandler();

	   public void setHandler(Handler handler){
	   this.handler = handler;
	   }
	   public void setScene(VMDGraphScene scene){
	   this.scene = scene;
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:5: ( '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>' ( LETTERS | NUMBERS | '\\n' )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:7: '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>' ( LETTERS | NUMBERS | '\\n' )*
			{
			match("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:64: ( LETTERS | NUMBERS | '\\n' )*
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:65: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:73: NUMBERS
					{
					mNUMBERS(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:65:81: '\\n'
					{
					match('\n'); 
					}
					break;

				default :
					break loop1;
				}
			}


			//handler.doIt(getText());

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:69:11: ( ( '=' | '-' | ' ' )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:69:13: ( '=' | '-' | ' ' )* '\\n'
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:69:13: ( '=' | '-' | ' ' )*
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

			//handler.doIt(getText());


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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:74:9: ( 'COMPONENT NAME : ' ( LETTERS | NUMBERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:74:11: 'COMPONENT NAME : ' ( LETTERS | NUMBERS )* '\\n'
			{
			match("COMPONENT NAME : "); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:74:31: ( LETTERS | NUMBERS )*
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:74:32: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:74:40: NUMBERS
					{
					mNUMBERS(); 

					}
					break;

				default :
					break loop3;
				}
			}

			match('\n'); 

			        String [] name0 =getText().split(Pattern.quote("."));
			        String [] name1 = name0[name0.length-1].split(" ");
			        component = new Component(name1[0].trim());
			        String[] l = name1[name1.length-1].split(":");
			        int level = Integer.parseInt((l[l.length-1]).trim());
			        component.setLevel(level);
			        componentView = new ComponentView(scene, component);
			        
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
			CommonToken impl=null;

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:84:19: ( 'IMPLEMENTATION : ' (impl= LETTERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:84:21: 'IMPLEMENTATION : ' (impl= LETTERS )* '\\n'
			{
			match("IMPLEMENTATION : "); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:84:45: (impl= LETTERS )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0=='\t'||(LA4_0 >= ' ' && LA4_0 <= '\"')||(LA4_0 >= '-' && LA4_0 <= '/')||LA4_0==':'||LA4_0=='<'||(LA4_0 >= '>' && LA4_0 <= '?')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:84:45: impl= LETTERS
					{
					int implStart96 = getCharIndex();
					int implStartLine96 = getLine();
					int implStartCharPos96 = getCharPositionInLine();
					mLETTERS(); 
					impl = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, implStart96, getCharIndex()-1);
					impl.setLine(implStartLine96);
					impl.setCharPositionInLine(implStartCharPos96);

					}
					break;

				default :
					break loop4;
				}
			}

			match('\n'); 

			      //  handler.doIt(getText());
			        // System.out.println("IMPLEMENTATION : " + getText());
			        String[] impl0 = impl.getText().trim().split(" ");
			        String[] impl1 = impl0[impl0.length-1].split(Pattern.quote("."));
			        component.setImplementation(impl1[impl1.length-1].trim());
			        
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:92:10: ( 'STATE :' ( LETTERS )* '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:92:12: 'STATE :' ( LETTERS )* '\\n'
			{
			match("STATE :"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:92:21: ( LETTERS )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='\t'||(LA5_0 >= ' ' && LA5_0 <= '\"')||(LA5_0 >= '-' && LA5_0 <= '/')||LA5_0==':'||LA5_0=='<'||(LA5_0 >= '>' && LA5_0 <= '?')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:92:21: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop5;
				}
			}

			match('\n'); 

			         String [] name0 =getText().split(Pattern.quote(":"));
			         String [] name1 =name0[name0.length-1].trim().split(Pattern.quote("."));
			      //  System.out.println("STATE : " + getText());
			         component.setState(name1[name1.length-1].trim());
			      //  handler.doIt(component);
			        
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:21: ( 'SERVICE PROVIDED:' ( ' ' | '\\t' | '\\n' )* | ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )* )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='S') ) {
				alt10=1;
			}

			else {
				alt10=2;
			}

			switch (alt10) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:23: 'SERVICE PROVIDED:' ( ' ' | '\\t' | '\\n' )*
					{
					match("SERVICE PROVIDED:"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:43: ( ' ' | '\\t' | '\\n' )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '\t' && LA6_0 <= '\n')||LA6_0==' ') ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)==' ' ) {
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
							break loop6;
						}
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:61: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:61: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0=='\t'||LA9_0=='i') ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:62: ( '\\t' )* 'interface ' ( LETTERS )* '\\n'
							{
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:62: ( '\\t' )*
							loop7:
							while (true) {
								int alt7=2;
								int LA7_0 = input.LA(1);
								if ( (LA7_0=='\t') ) {
									alt7=1;
								}

								switch (alt7) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:62: '\\t'
									{
									match('\t'); 
									}
									break;

								default :
									break loop7;
								}
							}

							match("interface "); 

							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:81: ( LETTERS )*
							loop8:
							while (true) {
								int alt8=2;
								int LA8_0 = input.LA(1);
								if ( (LA8_0=='\t'||(LA8_0 >= ' ' && LA8_0 <= '\"')||(LA8_0 >= '-' && LA8_0 <= '/')||LA8_0==':'||LA8_0=='<'||(LA8_0 >= '>' && LA8_0 <= '?')||(LA8_0 >= 'A' && LA8_0 <= 'Z')||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
									alt8=1;
								}

								switch (alt8) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:100:81: LETTERS
									{
									mLETTERS(); 

									}
									break;

								default :
									break loop8;
								}
							}

							match('\n'); 
							}
							break;

						default :
							break loop9;
						}
					}


					        //System.out.println(getText());
					        component.addServiceProvided(getText());
					        handler.doIt(component);
					        
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
	// $ANTLR end "SERVICE_PROVIDED"

	// $ANTLR start "EVENT_PROVIDED"
	public final void mEVENT_PROVIDED() throws RecognitionException {
		try {
			int _type = EVENT_PROVIDED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:19: ( 'EVENT PROVIDED:' ( ' ' | '\\t' | '\\n' )* | ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )* )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0=='E') ) {
				alt15=1;
			}

			else {
				alt15=2;
			}

			switch (alt15) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:21: 'EVENT PROVIDED:' ( ' ' | '\\t' | '\\n' )*
					{
					match("EVENT PROVIDED:"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:39: ( ' ' | '\\t' | '\\n' )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( ((LA11_0 >= '\t' && LA11_0 <= '\n')||LA11_0==' ') ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)==' ' ) {
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
							break loop11;
						}
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:57: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:57: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0=='\t'||LA14_0=='i') ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:58: ( '\\t' )* 'interface ' ( LETTERS )* '\\n'
							{
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:58: ( '\\t' )*
							loop12:
							while (true) {
								int alt12=2;
								int LA12_0 = input.LA(1);
								if ( (LA12_0=='\t') ) {
									alt12=1;
								}

								switch (alt12) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:58: '\\t'
									{
									match('\t'); 
									}
									break;

								default :
									break loop12;
								}
							}

							match("interface "); 

							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:77: ( LETTERS )*
							loop13:
							while (true) {
								int alt13=2;
								int LA13_0 = input.LA(1);
								if ( (LA13_0=='\t'||(LA13_0 >= ' ' && LA13_0 <= '\"')||(LA13_0 >= '-' && LA13_0 <= '/')||LA13_0==':'||LA13_0=='<'||(LA13_0 >= '>' && LA13_0 <= '?')||(LA13_0 >= 'A' && LA13_0 <= 'Z')||(LA13_0 >= 'a' && LA13_0 <= 'z')) ) {
									alt13=1;
								}

								switch (alt13) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:106:77: LETTERS
									{
									mLETTERS(); 

									}
									break;

								default :
									break loop13;
								}
							}

							match('\n'); 
							}
							break;

						default :
							break loop14;
						}
					}


					       // handler.doIt(getText());
					       // System.out.println("EVENT_PROVIDED : " + getText());
					        component.addEventProvided(getText());
					       // handler.doIt(component);
					        
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
	// $ANTLR end "EVENT_PROVIDED"

	// $ANTLR start "USED_SERVICES"
	public final void mUSED_SERVICES() throws RecognitionException {
		try {
			int _type = USED_SERVICES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:17: ( 'USED SERVICES :' ( ' ' | '\\t' | '\\n' )* | ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )* )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0=='U') ) {
				alt20=1;
			}

			else {
				alt20=2;
			}

			switch (alt20) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:19: 'USED SERVICES :' ( ' ' | '\\t' | '\\n' )*
					{
					match("USED SERVICES :"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:36: ( ' ' | '\\t' | '\\n' )*
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( ((LA16_0 >= '\t' && LA16_0 <= '\n')||LA16_0==' ') ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)==' ' ) {
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
							break loop16;
						}
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:53: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:53: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					loop19:
					while (true) {
						int alt19=2;
						int LA19_0 = input.LA(1);
						if ( (LA19_0=='\t'||LA19_0=='i') ) {
							alt19=1;
						}

						switch (alt19) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:54: ( '\\t' )* 'interface ' ( LETTERS )* '\\n'
							{
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:54: ( '\\t' )*
							loop17:
							while (true) {
								int alt17=2;
								int LA17_0 = input.LA(1);
								if ( (LA17_0=='\t') ) {
									alt17=1;
								}

								switch (alt17) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:54: '\\t'
									{
									match('\t'); 
									}
									break;

								default :
									break loop17;
								}
							}

							match("interface "); 

							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:73: ( LETTERS )*
							loop18:
							while (true) {
								int alt18=2;
								int LA18_0 = input.LA(1);
								if ( (LA18_0=='\t'||(LA18_0 >= ' ' && LA18_0 <= '\"')||(LA18_0 >= '-' && LA18_0 <= '/')||LA18_0==':'||LA18_0=='<'||(LA18_0 >= '>' && LA18_0 <= '?')||(LA18_0 >= 'A' && LA18_0 <= 'Z')||(LA18_0 >= 'a' && LA18_0 <= 'z')) ) {
									alt18=1;
								}

								switch (alt18) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:113:73: LETTERS
									{
									mLETTERS(); 

									}
									break;

								default :
									break loop18;
								}
							}

							match('\n'); 
							}
							break;

						default :
							break loop19;
						}
					}


					        //handler.doIt(getText());
					      //  System.out.println("USED_SERVICES : " + getText());
					        component.addUsedService(getText());
					        handler.doIt(component);
					        
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
	// $ANTLR end "USED_SERVICES"

	// $ANTLR start "CONSUMED_EVENT"
	public final void mCONSUMED_EVENT() throws RecognitionException {
		try {
			int _type = CONSUMED_EVENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:19: ( 'CONSUMED EVENT:' ( ' ' | '\\t' | '\\n' )* | ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )* )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0=='C') ) {
				alt25=1;
			}

			else {
				alt25=2;
			}

			switch (alt25) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:21: 'CONSUMED EVENT:' ( ' ' | '\\t' | '\\n' )*
					{
					match("CONSUMED EVENT:"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:38: ( ' ' | '\\t' | '\\n' )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( ((LA21_0 >= '\t' && LA21_0 <= '\n')||LA21_0==' ') ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)==' ' ) {
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
							break loop21;
						}
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:55: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:55: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					loop24:
					while (true) {
						int alt24=2;
						int LA24_0 = input.LA(1);
						if ( (LA24_0=='\t'||LA24_0=='i') ) {
							alt24=1;
						}

						switch (alt24) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:56: ( '\\t' )* 'interface ' ( LETTERS )* '\\n'
							{
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:56: ( '\\t' )*
							loop22:
							while (true) {
								int alt22=2;
								int LA22_0 = input.LA(1);
								if ( (LA22_0=='\t') ) {
									alt22=1;
								}

								switch (alt22) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:56: '\\t'
									{
									match('\t'); 
									}
									break;

								default :
									break loop22;
								}
							}

							match("interface "); 

							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:75: ( LETTERS )*
							loop23:
							while (true) {
								int alt23=2;
								int LA23_0 = input.LA(1);
								if ( (LA23_0=='\t'||(LA23_0 >= ' ' && LA23_0 <= '\"')||(LA23_0 >= '-' && LA23_0 <= '/')||LA23_0==':'||LA23_0=='<'||(LA23_0 >= '>' && LA23_0 <= '?')||(LA23_0 >= 'A' && LA23_0 <= 'Z')||(LA23_0 >= 'a' && LA23_0 <= 'z')) ) {
									alt23=1;
								}

								switch (alt23) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:120:75: LETTERS
									{
									mLETTERS(); 

									}
									break;

								default :
									break loop23;
								}
							}

							match('\n'); 
							}
							break;

						default :
							break loop24;
						}
					}


					       // handler.doIt(component);
					      //   System.out.println("CONSUMED_EVENT : " + getText());
					        component.addConsumedEvent(getText());
					        //handler.doIt(component);
					        
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
	// $ANTLR end "CONSUMED_EVENT"

	// $ANTLR start "USED_EVENT_SUBSCRIBE"
	public final void mUSED_EVENT_SUBSCRIBE() throws RecognitionException {
		try {
			int _type = USED_EVENT_SUBSCRIBE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:22: ( 'USED EVENT SUBSCRIBE:' ( ' ' | '\\t' | '\\n' )* | ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )* )
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0=='U') ) {
				alt30=1;
			}

			else {
				alt30=2;
			}

			switch (alt30) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:24: 'USED EVENT SUBSCRIBE:' ( ' ' | '\\t' | '\\n' )*
					{
					match("USED EVENT SUBSCRIBE:"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:47: ( ' ' | '\\t' | '\\n' )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( ((LA26_0 >= '\t' && LA26_0 <= '\n')||LA26_0==' ') ) {
							alt26=1;
						}

						switch (alt26) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
							{
							if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)==' ' ) {
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
							break loop26;
						}
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:64: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:64: ( ( '\\t' )* 'interface ' ( LETTERS )* '\\n' )*
					loop29:
					while (true) {
						int alt29=2;
						int LA29_0 = input.LA(1);
						if ( (LA29_0=='\t'||LA29_0=='i') ) {
							alt29=1;
						}

						switch (alt29) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:65: ( '\\t' )* 'interface ' ( LETTERS )* '\\n'
							{
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:65: ( '\\t' )*
							loop27:
							while (true) {
								int alt27=2;
								int LA27_0 = input.LA(1);
								if ( (LA27_0=='\t') ) {
									alt27=1;
								}

								switch (alt27) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:65: '\\t'
									{
									match('\t'); 
									}
									break;

								default :
									break loop27;
								}
							}

							match("interface "); 

							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:84: ( LETTERS )*
							loop28:
							while (true) {
								int alt28=2;
								int LA28_0 = input.LA(1);
								if ( (LA28_0=='\t'||(LA28_0 >= ' ' && LA28_0 <= '\"')||(LA28_0 >= '-' && LA28_0 <= '/')||LA28_0==':'||LA28_0=='<'||(LA28_0 >= '>' && LA28_0 <= '?')||(LA28_0 >= 'A' && LA28_0 <= 'Z')||(LA28_0 >= 'a' && LA28_0 <= 'z')) ) {
									alt28=1;
								}

								switch (alt28) {
								case 1 :
									// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:127:84: LETTERS
									{
									mLETTERS(); 

									}
									break;

								default :
									break loop28;
								}
							}

							match('\n'); 
							}
							break;

						default :
							break loop29;
						}
					}


					      // handler.doIt(component);
					        // System.out.println("USED_EVENT_SUBSCRIBE : " + getText());
					       // component.
					        
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
	// $ANTLR end "USED_EVENT_SUBSCRIBE"

	// $ANTLR start "SUB_COMPONENT"
	public final void mSUB_COMPONENT() throws RecognitionException {
		try {
			int _type = SUB_COMPONENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:133:18: ( 'SUB COMPONENT:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:133:20: 'SUB COMPONENT:' LETTERS '\\n'
			{
			match("SUB COMPONENT:"); 

			mLETTERS(); 

			match('\n'); 

			      //  handler.doIt(getText());
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:138:19: ( 'COMPONENT ITEM:' LETTERS '\\n' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:138:21: 'COMPONENT ITEM:' LETTERS '\\n'
			{
			match("COMPONENT ITEM:"); 

			mLETTERS(); 

			match('\n'); 

			      //  handler.doIt(getText());
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:6: ( '</message>' ( LETTERS | NUMBERS | '\\n' )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:8: '</message>' ( LETTERS | NUMBERS | '\\n' )*
			{
			match("</message>"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:21: ( LETTERS | NUMBERS | '\\n' )*
			loop31:
			while (true) {
				int alt31=4;
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
					alt31=1;
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
					alt31=2;
					}
					break;
				case '\n':
					{
					alt31=3;
					}
					break;
				}
				switch (alt31) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:22: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:30: NUMBERS
					{
					mNUMBERS(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:143:38: '\\n'
					{
					match('\n'); 
					}
					break;

				default :
					break loop31;
				}
			}




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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:150:9: ( ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:150:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:150:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | '.' | ':' | ' ' | '<' | '!' | '?' | '>' | '/' | '\"' | '-' | '\\t' )+
			int cnt32=0;
			loop32:
			while (true) {
				int alt32=2;
				int LA32_0 = input.LA(1);
				if ( (LA32_0=='\t'||(LA32_0 >= ' ' && LA32_0 <= '\"')||(LA32_0 >= '-' && LA32_0 <= '/')||LA32_0==':'||LA32_0=='<'||(LA32_0 >= '>' && LA32_0 <= '?')||(LA32_0 >= 'A' && LA32_0 <= 'Z')||(LA32_0 >= 'a' && LA32_0 <= 'z')) ) {
					alt32=1;
				}

				switch (alt32) {
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
					if ( cnt32 >= 1 ) break loop32;
					EarlyExitException eee = new EarlyExitException(32, input);
					throw eee;
				}
				cnt32++;
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:154:9: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* )
			int alt36=2;
			alt36 = dfa36.predict(input);
			switch (alt36) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:155:5: ( '0' .. '9' )+
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:155:5: ( '0' .. '9' )+
					int cnt33=0;
					loop33:
					while (true) {
						int alt33=2;
						int LA33_0 = input.LA(1);
						if ( ((LA33_0 >= '0' && LA33_0 <= '9')) ) {
							alt33=1;
						}

						switch (alt33) {
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
							if ( cnt33 >= 1 ) break loop33;
							EarlyExitException eee = new EarlyExitException(33, input);
							throw eee;
						}
						cnt33++;
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:157:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:157:5: ( '0' .. '9' )+
					int cnt34=0;
					loop34:
					while (true) {
						int alt34=2;
						int LA34_0 = input.LA(1);
						if ( ((LA34_0 >= '0' && LA34_0 <= '9')) ) {
							alt34=1;
						}

						switch (alt34) {
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
							if ( cnt34 >= 1 ) break loop34;
							EarlyExitException eee = new EarlyExitException(34, input);
							throw eee;
						}
						cnt34++;
					}

					match('.'); 
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:157:21: ( '0' .. '9' )*
					loop35:
					while (true) {
						int alt35=2;
						int LA35_0 = input.LA(1);
						if ( ((LA35_0 >= '0' && LA35_0 <= '9')) ) {
							alt35=1;
						}

						switch (alt35) {
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
							break loop35;
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
		int alt37=15;
		alt37 = dfa37.predict(input);
		switch (alt37) {
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


	protected DFA36 dfa36 = new DFA36(this);
	protected DFA37 dfa37 = new DFA37(this);
	static final String DFA36_eotS =
		"\1\uffff\1\2\2\uffff";
	static final String DFA36_eofS =
		"\4\uffff";
	static final String DFA36_minS =
		"\1\60\1\56\2\uffff";
	static final String DFA36_maxS =
		"\2\71\2\uffff";
	static final String DFA36_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA36_specialS =
		"\4\uffff}>";
	static final String[] DFA36_transitionS = {
			"\12\1",
			"\1\3\1\uffff\12\1",
			"",
			""
	};

	static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
	static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
	static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
	static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
	static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
	static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
	static final short[][] DFA36_transition;

	static {
		int numStates = DFA36_transitionS.length;
		DFA36_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
		}
	}

	protected class DFA36 extends DFA {

		public DFA36(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 36;
			this.eot = DFA36_eot;
			this.eof = DFA36_eof;
			this.min = DFA36_min;
			this.max = DFA36_max;
			this.accept = DFA36_accept;
			this.special = DFA36_special;
			this.transition = DFA36_transition;
		}
		@Override
		public String getDescription() {
			return "154:1: NUMBERS : ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* );";
		}
	}

	static final String DFA37_eotS =
		"\1\11\2\14\1\uffff\5\14\1\uffff\2\14\2\uffff\111\14\1\uffff\22\14\1\166"+
		"\12\14\1\166\1\uffff\7\14\1\11\12\14\2\uffff\12\14\1\uffff\3\14\1\uffff"+
		"\6\14\1\uffff\5\14\1\u00b1\3\14\1\uffff\1\u00b7\1\u00b9\3\14\1\u00b1\1"+
		"\uffff\2\14\2\uffff\1\u00b7\1\uffff\1\u00b9\1\uffff\2\14\1\uffff\1\14"+
		"\1\11\1\uffff\2\14\1\uffff\1\14\1\uffff\1\11\1\uffff\1\14\1\uffff\1\14"+
		"\1\uffff\1\14\1\uffff\1\u00d0\1\uffff\1\u00d0\1\uffff";
	static final String DFA37_eofS =
		"\u00d1\uffff";
	static final String DFA37_minS =
		"\1\11\1\57\1\12\1\uffff\1\117\1\115\1\105\1\11\1\156\1\uffff\1\126\1\123"+
		"\2\uffff\1\170\1\155\1\115\1\120\1\101\1\122\1\102\1\164\2\105\1\155\1"+
		"\145\1\120\1\123\1\114\1\124\1\126\1\40\1\145\1\116\1\104\1\154\1\163"+
		"\1\117\1\125\2\105\1\111\1\103\1\162\1\124\2\40\1\163\1\116\2\115\1\40"+
		"\1\103\1\117\1\146\1\40\1\105\1\166\1\141\3\105\1\72\1\105\1\115\1\141"+
		"\1\120\1\105\1\126\1\145\1\147\1\116\1\104\1\116\1\11\1\40\1\120\1\143"+
		"\2\122\1\105\1\162\1\145\1\124\1\40\1\124\1\11\1\uffff\1\120\1\117\1\145"+
		"\1\117\1\126\1\116\1\163\1\76\1\40\1\105\1\101\1\122\1\116\1\40\1\126"+
		"\1\111\1\124\1\151\1\11\1\111\1\126\1\124\1\117\1\105\1\11\1\111\1\103"+
		"\1\40\1\157\1\11\1\uffff\1\101\1\124\1\105\1\111\1\126\1\116\2\11\1\104"+
		"\1\105\1\123\1\156\1\115\1\105\1\116\1\117\1\111\1\124\1\11\1\156\1\105"+
		"\1\123\1\125\1\75\1\105\1\115\1\124\1\116\1\104\1\72\1\164\1\104\1\40"+
		"\1\102\1\uffff\1\40\2\72\1\40\1\105\1\11\1\145\2\72\1\123\1\72\2\11\1"+
		"\72\1\104\1\11\1\162\2\11\1\103\1\40\2\11\1\uffff\1\40\1\72\1\uffff\1"+
		"\146\1\11\1\uffff\1\11\1\uffff\1\122\1\11\1\uffff\2\11\1\141\1\111\1\11"+
		"\1\uffff\1\11\1\uffff\1\11\1\143\1\102\1\145\1\105\1\40\1\72\4\11\1\uffff";
	static final String DFA37_maxS =
		"\1\172\1\77\1\75\1\uffff\1\117\1\115\1\125\1\151\1\156\1\uffff\1\126\1"+
		"\123\2\uffff\1\170\1\155\1\116\1\120\1\101\1\122\1\102\1\164\2\105\1\155"+
		"\1\145\1\120\1\123\1\114\1\124\1\126\1\40\1\145\1\116\1\104\1\154\1\163"+
		"\1\117\1\125\2\105\1\111\1\103\1\162\1\124\2\40\1\163\1\116\2\115\1\40"+
		"\1\103\1\117\1\146\1\40\1\123\1\166\1\141\3\105\1\72\1\105\1\115\1\141"+
		"\1\120\1\105\1\126\1\145\1\147\1\116\1\104\1\116\1\172\1\40\1\120\1\143"+
		"\2\122\1\105\1\162\1\145\1\124\1\40\1\124\1\172\1\uffff\1\120\1\117\1"+
		"\145\1\117\1\126\1\116\1\163\1\76\1\40\1\105\1\101\1\122\1\116\1\40\1"+
		"\126\1\111\1\124\1\151\1\172\1\116\1\126\1\124\1\117\1\105\1\172\1\111"+
		"\1\103\1\40\1\157\1\172\1\uffff\1\101\1\124\1\105\1\111\1\126\1\116\1"+
		"\172\1\151\1\104\1\105\1\123\1\156\1\115\1\105\1\116\1\117\1\111\1\124"+
		"\1\151\1\156\1\105\1\123\1\125\1\75\1\105\1\115\1\124\1\116\1\104\1\72"+
		"\1\164\1\104\1\40\1\102\1\uffff\1\40\2\72\1\40\1\105\1\172\1\145\2\72"+
		"\1\123\1\72\2\172\1\72\1\104\1\172\1\162\2\172\1\103\1\40\2\172\1\uffff"+
		"\1\40\1\72\1\uffff\1\146\1\172\1\uffff\1\172\1\uffff\1\122\1\172\1\uffff"+
		"\2\172\1\141\1\111\1\172\1\uffff\1\172\1\uffff\1\172\1\143\1\102\1\145"+
		"\1\105\1\40\1\72\4\172\1\uffff";
	static final String DFA37_acceptS =
		"\3\uffff\1\2\5\uffff\1\6\2\uffff\1\16\1\17\111\uffff\1\5\36\uffff\1\15"+
		"\42\uffff\1\1\27\uffff\1\11\2\uffff\1\13\2\uffff\1\7\1\uffff\1\10\2\uffff"+
		"\1\14\5\uffff\1\3\1\uffff\1\4\13\uffff\1\12";
	static final String DFA37_specialS =
		"\u00d1\uffff}>";
	static final String[] DFA37_transitionS = {
			"\1\7\1\3\25\uffff\1\2\2\14\12\uffff\1\2\2\14\12\15\1\14\1\uffff\1\1\1"+
			"\3\2\14\1\uffff\2\14\1\4\1\14\1\12\3\14\1\5\11\14\1\6\1\14\1\13\5\14"+
			"\6\uffff\10\14\1\10\21\14",
			"\1\17\17\uffff\1\16",
			"\1\3\25\uffff\1\2\14\uffff\1\2\17\uffff\1\3",
			"",
			"\1\20",
			"\1\21",
			"\1\23\16\uffff\1\22\1\24",
			"\1\7\137\uffff\1\10",
			"\1\25",
			"",
			"\1\26",
			"\1\27",
			"",
			"",
			"\1\30",
			"\1\31",
			"\1\32\1\33",
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
			"\1\73",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\104\15\uffff\1\103",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126\1\127\25\uffff\3\126\12\uffff\3\126\12\uffff\1\126\1\uffff\1"+
			"\126\1\uffff\2\126\1\uffff\32\126\6\uffff\32\126",
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
			"\1\126\1\127\25\uffff\3\126\12\uffff\3\126\12\uffff\1\126\1\uffff\1"+
			"\126\1\uffff\2\126\1\uffff\32\126\6\uffff\32\126",
			"",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"\1\153",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\1\162",
			"\1\163",
			"\1\164",
			"\1\165\26\uffff\3\165\12\uffff\3\165\12\uffff\1\165\1\uffff\1\165\1"+
			"\uffff\2\165\1\uffff\32\165\6\uffff\32\165",
			"\1\170\4\uffff\1\167",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\1\175\1\176\25\uffff\3\175\12\uffff\3\175\12\uffff\1\175\1\uffff\1"+
			"\175\1\uffff\2\175\1\uffff\32\175\6\uffff\32\175",
			"\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\165\26\uffff\3\165\12\uffff\3\165\12\uffff\1\165\1\uffff\1\165\1"+
			"\uffff\2\165\1\uffff\32\165\6\uffff\32\165",
			"",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\175\1\176\25\uffff\3\175\12\uffff\3\175\12\uffff\1\175\1\uffff\1"+
			"\175\1\uffff\2\175\1\uffff\32\175\6\uffff\32\175",
			"\1\u0089\137\uffff\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0089\137\uffff\1\u008a",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9\26\uffff\3\u00a9\12\uffff\3\u00a9\12\uffff\1\u00a9\1\uffff"+
			"\1\u00a9\1\uffff\2\u00a9\1\uffff\32\u00a9\6\uffff\32\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af\26\uffff\3\u00af\12\uffff\3\u00af\12\uffff\1\u00af\1\uffff"+
			"\1\u00af\1\uffff\2\u00af\1\uffff\32\u00af\6\uffff\32\u00af",
			"\1\u00b0\26\uffff\1\u00b0\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00a9\1\u00b4\25\uffff\3\u00a9\12\uffff\3\u00a9\12\uffff\1\u00a9"+
			"\1\uffff\1\u00a9\1\uffff\2\u00a9\1\uffff\32\u00a9\6\uffff\32\u00a9",
			"\1\u00b5",
			"\1\u00b6\26\uffff\1\u00b6\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00b8\26\uffff\1\u00b8\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00af\1\u00bc\25\uffff\3\u00af\12\uffff\3\u00af\12\uffff\1\u00af"+
			"\1\uffff\1\u00af\1\uffff\2\u00af\1\uffff\32\u00af\6\uffff\32\u00af",
			"\1\u00b0\26\uffff\1\u00b0\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"",
			"\1\u00bd",
			"\1\u00be",
			"",
			"\1\u00bf",
			"\1\u00b6\26\uffff\1\u00b6\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"",
			"\1\u00b8\26\uffff\1\u00b8\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"",
			"\1\u00c0",
			"\1\u00c1\1\u00c2\25\uffff\3\u00c1\12\uffff\3\u00c1\12\u00c2\1\u00c1"+
			"\1\uffff\1\u00c1\1\uffff\2\u00c1\1\uffff\32\u00c1\6\uffff\32\u00c1",
			"",
			"\1\u00c3\1\u00c4\25\uffff\3\u00c3\12\uffff\3\u00c3\12\uffff\1\u00c3"+
			"\1\uffff\1\u00c3\1\uffff\2\u00c3\1\uffff\32\u00c3\6\uffff\32\u00c3",
			"\1\u00c5\26\uffff\1\u00c5\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c1\1\u00c2\25\uffff\3\u00c1\12\uffff\3\u00c1\12\u00c2\1\u00c1"+
			"\1\uffff\1\u00c1\1\uffff\2\u00c1\1\uffff\32\u00c1\6\uffff\32\u00c1",
			"",
			"\1\u00c3\1\u00c4\25\uffff\3\u00c3\12\uffff\3\u00c3\12\uffff\1\u00c3"+
			"\1\uffff\1\u00c3\1\uffff\2\u00c3\1\uffff\32\u00c3\6\uffff\32\u00c3",
			"",
			"\1\u00c5\26\uffff\1\u00c5\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce\1\176\25\uffff\3\u00ce\12\uffff\3\u00ce\12\uffff\1\u00ce\1"+
			"\uffff\1\u00ce\1\uffff\2\u00ce\1\uffff\32\u00ce\6\uffff\32\u00ce",
			"\1\u00cf\26\uffff\1\u00cf\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			"\1\u00ce\1\176\25\uffff\3\u00ce\12\uffff\3\u00ce\12\uffff\1\u00ce\1"+
			"\uffff\1\u00ce\1\uffff\2\u00ce\1\uffff\32\u00ce\6\uffff\32\u00ce",
			"\1\u00cf\26\uffff\1\u00cf\2\14\12\uffff\3\14\12\uffff\1\14\1\uffff\1"+
			"\14\1\uffff\2\14\1\uffff\32\14\6\uffff\32\14",
			""
	};

	static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
	static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
	static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
	static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
	static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
	static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
	static final short[][] DFA37_transition;

	static {
		int numStates = DFA37_transitionS.length;
		DFA37_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
		}
	}

	protected class DFA37 extends DFA {

		public DFA37(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 37;
			this.eot = DFA37_eot;
			this.eof = DFA37_eof;
			this.min = DFA37_min;
			this.max = DFA37_max;
			this.accept = DFA37_accept;
			this.special = DFA37_special;
			this.transition = DFA37_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( PRE | SEPARATOR | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | LETTERS | NUMBERS );";
		}
	}

}
