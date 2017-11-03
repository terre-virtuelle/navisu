// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g 2017-11-02 14:56:32

package bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl;


    

import org.antlr.runtime.*;

@SuppressWarnings("all")
public class NMEAParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AAM", "ALR", "APB", "BEC", "BOD", 
		"BWC", "BWR", "BWW", "CHECKSUM", "DBK", "DBS", "DBT", "DEV", "DEVICE", 
		"DPT", "EXPONENT", "GGA", "GLL", "GPSD_AIS", "GPSD_DEVICE", "GPSD_DEVICES", 
		"GPSD_ERROR", "GPSD_VERSION", "GPSD_WATCH", "GSA", "GSV", "HDG", "HDM", 
		"HDT", "LETTERS", "MSK", "MTA", "MTW", "MWD", "MWV", "NAME", "NUMBER", 
		"PGN", "PRO", "RMB", "RMC", "RSD", "RTE", "SEP", "SIGN", "SIGNED", "TIME_STAMP", 
		"TXT", "VBW", "VDM", "VHW", "VLW", "VPW", "VTG", "VWR", "VWT", "WS", "XTE", 
		"ZDA"
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
	public static final int GPSD_AIS=22;
	public static final int GPSD_DEVICE=23;
	public static final int GPSD_DEVICES=24;
	public static final int GPSD_ERROR=25;
	public static final int GPSD_VERSION=26;
	public static final int GPSD_WATCH=27;
	public static final int GSA=28;
	public static final int GSV=29;
	public static final int HDG=30;
	public static final int HDM=31;
	public static final int HDT=32;
	public static final int LETTERS=33;
	public static final int MSK=34;
	public static final int MTA=35;
	public static final int MTW=36;
	public static final int MWD=37;
	public static final int MWV=38;
	public static final int NAME=39;
	public static final int NUMBER=40;
	public static final int PGN=41;
	public static final int PRO=42;
	public static final int RMB=43;
	public static final int RMC=44;
	public static final int RSD=45;
	public static final int RTE=46;
	public static final int SEP=47;
	public static final int SIGN=48;
	public static final int SIGNED=49;
	public static final int TIME_STAMP=50;
	public static final int TXT=51;
	public static final int VBW=52;
	public static final int VDM=53;
	public static final int VHW=54;
	public static final int VLW=55;
	public static final int VPW=56;
	public static final int VTG=57;
	public static final int VWR=58;
	public static final int VWT=59;
	public static final int WS=60;
	public static final int XTE=61;
	public static final int ZDA=62;

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

	 
	    @Override    
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        throw new RuntimeException(hdr + ":" + msg);
	    }



	// $ANTLR start "entry"
	// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:305:1: entry : ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | GPSD_ERROR | PGN )+ ;
	public final void entry() throws RecognitionException {
		try {
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:305:8: ( ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | GPSD_ERROR | PGN )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:305:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | GPSD_ERROR | PGN )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:305:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | GPSD_ERROR | PGN )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= AAM && LA1_0 <= BWR)||(LA1_0 >= DBK && LA1_0 <= DBT)||LA1_0==DPT||(LA1_0 >= GGA && LA1_0 <= HDT)||(LA1_0 >= MSK && LA1_0 <= MWV)||LA1_0==PGN||(LA1_0 >= RMB && LA1_0 <= RTE)||(LA1_0 >= TXT && LA1_0 <= VWT)||(LA1_0 >= XTE && LA1_0 <= ZDA)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
					{
					if ( (input.LA(1) >= AAM && input.LA(1) <= BWR)||(input.LA(1) >= DBK && input.LA(1) <= DBT)||input.LA(1)==DPT||(input.LA(1) >= GGA && input.LA(1) <= HDT)||(input.LA(1) >= MSK && input.LA(1) <= MWV)||input.LA(1)==PGN||(input.LA(1) >= RMB && input.LA(1) <= RTE)||(input.LA(1) >= TXT && input.LA(1) <= VWT)||(input.LA(1) >= XTE && input.LA(1) <= ZDA) ) {
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
