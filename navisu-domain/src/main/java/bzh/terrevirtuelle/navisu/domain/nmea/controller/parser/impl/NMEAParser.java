// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g 2015-02-20 13:41:57

package bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl;


    

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class NMEAParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AAM", "ALR", "APB", "BEC", "BOD", 
		"BWC", "BWR", "BWW", "CHECKSUM", "DBK", "DBS", "DBT", "DEV", "DEVICE", 
		"DPT", "EXPONENT", "GGA", "GLL", "GSA", "GSV", "HDG", "HDM", "HDT", "LETTERS", 
		"MSK", "MTA", "MTW", "MWD", "MWV", "NAME", "NUMBER", "PGN", "PRO", "RMB", 
		"RMC", "RSD", "RTE", "SEP", "SIGN", "SIGNED", "TIME_STAMP", "TXT", "VBW", 
		"VDM", "VHW", "VLW", "VPW", "VTG", "VWR", "VWT", "WS", "XTE", "ZDA"
	};
	public static final int EOF=-1;
	public static final int AAM=4;
	public static final int ALR=5;
	public static final int APB=6;
	public static final int BEC=7;
	public static final int BOD=8;
	public static final int BWC=9;
	public static final int BWR=10;
	public static final int BWW=11;
	public static final int CHECKSUM=12;
	public static final int DBK=13;
	public static final int DBS=14;
	public static final int DBT=15;
	public static final int DEV=16;
	public static final int DEVICE=17;
	public static final int DPT=18;
	public static final int EXPONENT=19;
	public static final int GGA=20;
	public static final int GLL=21;
	public static final int GSA=22;
	public static final int GSV=23;
	public static final int HDG=24;
	public static final int HDM=25;
	public static final int HDT=26;
	public static final int LETTERS=27;
	public static final int MSK=28;
	public static final int MTA=29;
	public static final int MTW=30;
	public static final int MWD=31;
	public static final int MWV=32;
	public static final int NAME=33;
	public static final int NUMBER=34;
	public static final int PGN=35;
	public static final int PRO=36;
	public static final int RMB=37;
	public static final int RMC=38;
	public static final int RSD=39;
	public static final int RTE=40;
	public static final int SEP=41;
	public static final int SIGN=42;
	public static final int SIGNED=43;
	public static final int TIME_STAMP=44;
	public static final int TXT=45;
	public static final int VBW=46;
	public static final int VDM=47;
	public static final int VHW=48;
	public static final int VLW=49;
	public static final int VPW=50;
	public static final int VTG=51;
	public static final int VWR=52;
	public static final int VWT=53;
	public static final int WS=54;
	public static final int XTE=55;
	public static final int ZDA=56;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public NMEAParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public NMEAParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return NMEAParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g"; }



	// $ANTLR start "entry"
	// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:277:1: entry : ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+ ;
	public final void entry() throws RecognitionException {
		try {
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:277:8: ( ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:277:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:277:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= AAM && LA1_0 <= BWR)||(LA1_0 >= DBK && LA1_0 <= DBT)||LA1_0==DPT||(LA1_0 >= GGA && LA1_0 <= HDT)||(LA1_0 >= MSK && LA1_0 <= MWV)||(LA1_0 >= PRO && LA1_0 <= RTE)||(LA1_0 >= TXT && LA1_0 <= VWT)||(LA1_0 >= XTE && LA1_0 <= ZDA)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
					{
					if ( (input.LA(1) >= AAM && input.LA(1) <= BWR)||(input.LA(1) >= DBK && input.LA(1) <= DBT)||input.LA(1)==DPT||(input.LA(1) >= GGA && input.LA(1) <= HDT)||(input.LA(1) >= MSK && input.LA(1) <= MWV)||(input.LA(1) >= PRO && input.LA(1) <= RTE)||(input.LA(1) >= TXT && input.LA(1) <= VWT)||(input.LA(1) >= XTE && input.LA(1) <= ZDA) ) {
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
		    throw e;
		   }

		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "entry"

	// Delegated rules



}
