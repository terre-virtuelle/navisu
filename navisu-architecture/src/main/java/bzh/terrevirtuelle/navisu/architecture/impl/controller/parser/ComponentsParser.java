// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g 2017-11-08 20:26:30

package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ComponentsParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CLASS", "COMPONENT_ITEM", "CONSUMED_EVENT", 
		"EVENT_PROVIDED", "IMPL", "IMPLEMENTATION", "INTERFACE", "LETTERS", "NAME", 
		"NUMBERS", "POST", "PRE", "SEPARATOR", "SERVICE_PROVIDED", "STATE", "SUB_COMPONENT", 
		"USED_EVENT_SUBSCRIBE", "USED_SERVICES", "WS"
	};
	public static final int EOF=-1;
	public static final int CLASS=4;
	public static final int COMPONENT_ITEM=5;
	public static final int CONSUMED_EVENT=6;
	public static final int EVENT_PROVIDED=7;
	public static final int IMPL=8;
	public static final int IMPLEMENTATION=9;
	public static final int INTERFACE=10;
	public static final int LETTERS=11;
	public static final int NAME=12;
	public static final int NUMBERS=13;
	public static final int POST=14;
	public static final int PRE=15;
	public static final int SEPARATOR=16;
	public static final int SERVICE_PROVIDED=17;
	public static final int STATE=18;
	public static final int SUB_COMPONENT=19;
	public static final int USED_EVENT_SUBSCRIBE=20;
	public static final int USED_SERVICES=21;
	public static final int WS=22;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public ComponentsParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public ComponentsParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return ComponentsParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g"; }

	 
	    @Override    
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        throw new RuntimeException(hdr + ":" + msg);
	    }



	// $ANTLR start "entry"
	// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:55:1: entry : ( PRE | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | SEPARATOR )+ ;
	public final void entry() throws RecognitionException {
		try {
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:55:8: ( ( PRE | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | SEPARATOR )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:55:13: ( PRE | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | SEPARATOR )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:55:13: ( PRE | NAME | IMPLEMENTATION | STATE | SERVICE_PROVIDED | EVENT_PROVIDED | USED_SERVICES | CONSUMED_EVENT | USED_EVENT_SUBSCRIBE | SUB_COMPONENT | COMPONENT_ITEM | POST | SEPARATOR )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= COMPONENT_ITEM && LA1_0 <= EVENT_PROVIDED)||LA1_0==IMPLEMENTATION||LA1_0==NAME||(LA1_0 >= POST && LA1_0 <= USED_SERVICES)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-architecture/src/main/java/bzh/terrevirtuelle/navisu/architecture/impl/controller/parser/Components.g:
					{
					if ( (input.LA(1) >= COMPONENT_ITEM && input.LA(1) <= EVENT_PROVIDED)||input.LA(1)==IMPLEMENTATION||input.LA(1)==NAME||(input.LA(1) >= POST && input.LA(1) <= USED_SERVICES) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

		}

		  catch (RecognitionException e) {
		  //System.out.println("eeeeee"+e.toString());
		    throw e;
		   }

		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "entry"

	// Delegated rules



}
