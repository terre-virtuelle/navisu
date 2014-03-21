// $ANTLR 3.4 I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g 2014-03-21 11:03:06

package bzh.terrevirtuelle. navisu.nmea.controller.parser.impl;


    

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NMEAParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AAM", "ALR", "APB", "BEC", "BOD", "BWC", "BWR", "BWW", "CHECKSUM", "DBK", "DBS", "DBT", "DEVICE", "DPT", "EXPONENT", "GGA", "GLL", "GSA", "GSV", "HDG", "HDM", "HDT", "LETTERS", "MSK", "MTA", "MTW", "MWD", "MWV", "NUMBER", "PRO", "RMB", "RMC", "RSD", "RTE", "SEP", "SIGN", "TXT", "VBW", "VDM", "VHW", "VLW", "VPW", "VTG", "VWR", "VWT", "WS", "XTE", "ZDA"
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
    public static final int DEVICE=16;
    public static final int DPT=17;
    public static final int EXPONENT=18;
    public static final int GGA=19;
    public static final int GLL=20;
    public static final int GSA=21;
    public static final int GSV=22;
    public static final int HDG=23;
    public static final int HDM=24;
    public static final int HDT=25;
    public static final int LETTERS=26;
    public static final int MSK=27;
    public static final int MTA=28;
    public static final int MTW=29;
    public static final int MWD=30;
    public static final int MWV=31;
    public static final int NUMBER=32;
    public static final int PRO=33;
    public static final int RMB=34;
    public static final int RMC=35;
    public static final int RSD=36;
    public static final int RTE=37;
    public static final int SEP=38;
    public static final int SIGN=39;
    public static final int TXT=40;
    public static final int VBW=41;
    public static final int VDM=42;
    public static final int VHW=43;
    public static final int VLW=44;
    public static final int VPW=45;
    public static final int VTG=46;
    public static final int VWR=47;
    public static final int VWT=48;
    public static final int WS=49;
    public static final int XTE=50;
    public static final int ZDA=51;

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

    public String[] getTokenNames() { return NMEAParser.tokenNames; }
    public String getGrammarFileName() { return "I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g"; }

     




    // $ANTLR start "entry"
    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:202:1: entry : ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+ ;
    public final void entry() throws RecognitionException {
        try {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:202:8: ( ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+ )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:202:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:202:13: ( AAM | APB | BEC | BOD | BWC | BWR | DBS | DBT | DBK | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | RSD | VDM | TXT | ALR | PRO )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= AAM && LA1_0 <= BWR)||(LA1_0 >= DBK && LA1_0 <= DBT)||LA1_0==DPT||(LA1_0 >= GGA && LA1_0 <= HDT)||(LA1_0 >= MSK && LA1_0 <= MWV)||(LA1_0 >= PRO && LA1_0 <= RTE)||(LA1_0 >= TXT && LA1_0 <= VWT)||(LA1_0 >= XTE && LA1_0 <= ZDA)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\api\\nmea\\NMEA_2.0\\src\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entry"

    // Delegated rules


 

}