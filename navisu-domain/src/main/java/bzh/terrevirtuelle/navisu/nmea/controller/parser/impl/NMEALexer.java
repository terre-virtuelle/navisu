// $ANTLR 3.4 I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g 2014-02-03 15:51:52

package bzh.terrevirtuelle.navisu.nmea.controller.parser.impl;

import bzh.terrevirtuelle.navisu.nmea.model.NMEA;

import bzh.terrevirtuelle.navisu.nmea.model.AAM;
import bzh.terrevirtuelle.navisu.nmea.model.APB;
import bzh.terrevirtuelle.navisu.nmea.model.BEC;
import bzh.terrevirtuelle.navisu.nmea.model.BOD;
import bzh.terrevirtuelle.navisu.nmea.model.BWC;
import bzh.terrevirtuelle.navisu.nmea.model.BWR;
import bzh.terrevirtuelle.navisu.nmea.model.BWW;
import bzh.terrevirtuelle.navisu.nmea.model.DBT;
import bzh.terrevirtuelle.navisu.nmea.model.DBK;
import bzh.terrevirtuelle.navisu.nmea.model.DBS;
import bzh.terrevirtuelle.navisu.nmea.model.DPT;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.GLL;
import bzh.terrevirtuelle.navisu.nmea.model.GSA;
import bzh.terrevirtuelle.navisu.nmea.model.GSV;
import bzh.terrevirtuelle.navisu.nmea.model.HDG;
import bzh.terrevirtuelle.navisu.nmea.model.HDM;
import bzh.terrevirtuelle.navisu.nmea.model.HDT;
import bzh.terrevirtuelle.navisu.nmea.model.MTA;
import bzh.terrevirtuelle.navisu.nmea.model.MTW;
import bzh.terrevirtuelle.navisu.nmea.model.MWD;
import bzh.terrevirtuelle.navisu.nmea.model.MWV;
import bzh.terrevirtuelle.navisu.nmea.model.MSK;
import bzh.terrevirtuelle.navisu.nmea.model.RMB;
import bzh.terrevirtuelle.navisu.nmea.model.RMC;
import bzh.terrevirtuelle.navisu.nmea.model.RSD;
import bzh.terrevirtuelle.navisu.nmea.model.RTE;
import bzh.terrevirtuelle.navisu.nmea.model.VBW;
import bzh.terrevirtuelle.navisu.nmea.model.VHW;
import bzh.terrevirtuelle.navisu.nmea.model.VLW;
import bzh.terrevirtuelle.navisu.nmea.model.VPW;
import bzh.terrevirtuelle.navisu.nmea.model.VTG;
import bzh.terrevirtuelle.navisu.nmea.model.VWR;
import bzh.terrevirtuelle.navisu.nmea.model.VWT;
import bzh.terrevirtuelle.navisu.nmea.model.XTE;
import bzh.terrevirtuelle.navisu.nmea.model.ZDA;
import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.nmea.model.AIS5;
import bzh.terrevirtuelle.navisu.nmea.model.AIS8;
import bzh.terrevirtuelle.navisu.nmea.model.AIS18;
import bzh.terrevirtuelle.navisu.nmea.model.AIS24;


import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;  
import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.impl.PrintHandler; 
import bzh.terrevirtuelle.navisu.nmea.ais.controller.parser.impl.AISParser;
 
import bzh.terrevirtuelle.navisu.nmea.model.GPSSatellite;

 
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
    

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class NMEALexer extends Lexer {
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
    public static final int GPSD_VERSION=25;
    public static final int GPSD_WATCH=26;
    public static final int GSA=27;
    public static final int GSV=28;
    public static final int HDG=29;
    public static final int HDM=30;
    public static final int HDT=31;
    public static final int LETTERS=32;
    public static final int MSK=33;
    public static final int MTA=34;
    public static final int MTW=35;
    public static final int MWD=36;
    public static final int MWV=37;
    public static final int NAME=38;
    public static final int NUMBER=39;
    public static final int PRO=40;
    public static final int RMB=41;
    public static final int RMC=42;
    public static final int RSD=43;
    public static final int RTE=44;
    public static final int SEP=45;
    public static final int SIGN=46;
    public static final int SIGNED=47;
    public static final int TIME_STAMP=48;
    public static final int TXT=49;
    public static final int VBW=50;
    public static final int VDM=51;
    public static final int VHW=52;
    public static final int VLW=53;
    public static final int VPW=54;
    public static final int VTG=55;
    public static final int VWR=56;
    public static final int VWT=57;
    public static final int WS=58;
    public static final int XTE=59;
    public static final int ZDA=60;

       protected NMEA nmea = null;
       
       protected AAM aam = null;
       protected APB apb = null;
       protected BEC bec = null;
       protected BOD bod = null;
       protected BWC bwc = null;
       protected BWR bwr = null;
       protected BWW bww = null;
       protected DBS dbs = null;
       protected DBT dbt = null;
       protected DBK dbk = null;
       protected DPT dpt = null;
       protected GGA gga = null;
       protected GLL gll = null;
       protected GSA gsa = null;
       protected GSV gsv = null;
       protected HDG hdg = null;
       protected HDM hdm = null;
       protected HDT hdt = null;
       protected MTA mta = null;
       protected MTW mtw = null;
       protected MWD mwd = null;
       protected MWV mwv = null;
       protected MSK msk = null;
       protected RMB rmb = null;
       protected RMC rmc = null;
       protected RSD rsd = null;
       protected RTE rte = null;
       protected VBW vbw = null;
       protected VLW vlw = null;
       protected VHW vhw = null;
       protected VPW vpw = null;
       protected VTG vtg = null;
       protected VWR vwr = null;
       protected VWT vwt = null;
       protected XTE xte = null;
       protected ZDA zda = null;
       
       protected AIS1 ais1 = null;
       protected AIS3 ais3 = null;
       protected AIS4 ais4 = null;
       protected AIS5 ais5 = null;
       protected AIS8 ais8 = null;
       protected AIS18 ais18 = null;
       protected AIS24 ais24 = null;
       
       protected int year;
       protected int month;
       protected int day;  
       protected int hours;
       protected int minutes;
       protected int seconds;
       protected int hours2;
       protected int minutes2;
       protected int seconds2;
       protected float lat;
       protected float lon;
       protected float deviation;
       protected float variation;
       protected float sog;
       protected String uoaa;
       protected float ga;
       protected String uoga;
       protected String northOrSouth;
       protected String eastOrWest;
       protected float bdt;
       protected float bdm;
       protected float dtwp;
       protected String uodtwp;
       protected String unitsOfDistanceToWayPoint;
       protected String wid;
       protected String sId; 
       protected Calendar date;  
       protected GPSSatellite s1;
       protected GPSSatellite s2;
       protected GPSSatellite s3;
       protected GPSSatellite s4;
       protected String device;
       /* Default handlers */
       protected Handler handler = new PrintHandler();
       protected Handler aisHandler = new PrintHandler();
       protected AISParser aisParser = new AISParser(aisHandler);
       
       
       public void setHandler(Handler handler){
       this.handler = handler;
       }
       
       public void setAISHandler(Handler handler){
       this.aisHandler = handler;
       aisParser.setHandler(handler);
       }
       
       private float latConvert(float latitude, String ns){
            int latitudeInt = (int) latitude / 100;
            float latitudeMin = latitude - (latitudeInt * 100);
            float latDeg = latitudeInt + (latitudeMin / 60);
            if (ns.contains("S")){
                       latDeg *= -1;
            } 
            return latDeg;
       }
       private float lonConvert(float longitude, String ew){
            int longitudeInt = (int) longitude / 100;
            float longitudeMin = longitude - (longitudeInt * 100);
            float lonDeg = longitudeInt + (longitudeMin / 60);   
            if (ew.contains("W")){
                lonDeg *= -1;
            }
            return lonDeg;
       }	
       
       private float degConvert(float latlon){
            float latlonMin = latlon / 10000;
            float latlonDeg = latlonMin / 60;
            
            return latlonDeg;
       }
       private float ewConvert(float var, String ew){
               if (ew.contains("W")){
                var *= -1;
            }
            return var;
       }
       private Calendar dateFactory(String utc){
         int hours = new Integer(utc.substring(0,2)).intValue();
         int minutes = new Integer(utc.substring(2,4)).intValue();
         int seconds = new Integer(utc.substring(4,6)).intValue();
         Calendar  date = new GregorianCalendar();
         date.set(Calendar.HOUR_OF_DAY, hours);
         date.set(Calendar.MINUTE, minutes);
         date.set(Calendar.SECOND, seconds);
         return date;
       }
       private Calendar dateFactory(String yymmdd, String utc){
         
         Calendar  date = dateFactory(utc);
         if(yymmdd.length() != 0){
           int dd = new Integer(yymmdd.substring(0,2)).intValue();
           int mm = new Integer(yymmdd.substring(2,4)).intValue();
           int yy = new Integer(yymmdd.substring(4,6)).intValue();
           date.set(Calendar.YEAR, yy);
           date.set(Calendar.MONTH, mm-1);
           date.set(Calendar.DAY_OF_MONTH, dd);
         }
         return date;
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

    public NMEALexer() {} 
    public NMEALexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public NMEALexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g"; }

    // $ANTLR start "AAM"
    public final void mAAM() throws RecognitionException {
        try {
            int _type = AAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken arrivalCircleEntered=null;
            CommonToken perpendicularPassed=null;
            CommonToken circleRadius=null;
            CommonToken wayPointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:278:6: ( '$' device= DEVICE 'AAM' SEP (arrivalCircleEntered= LETTERS )* SEP (perpendicularPassed= LETTERS )* SEP (circleRadius= NUMBER )* SEP ( LETTERS )* SEP (wayPointID= LETTERS |wayPointID= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:278:9: '$' device= DEVICE 'AAM' SEP (arrivalCircleEntered= LETTERS )* SEP (perpendicularPassed= LETTERS )* SEP (circleRadius= NUMBER )* SEP ( LETTERS )* SEP (wayPointID= LETTERS |wayPointID= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart34 = getCharIndex();
            int deviceStartLine34 = getLine();
            int deviceStartCharPos34 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart34, getCharIndex()-1);
            device.setLine(deviceStartLine34);
            device.setCharPositionInLine(deviceStartCharPos34);


            match("AAM"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:279:6: (arrivalCircleEntered= LETTERS )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==' '||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:279:7: arrivalCircleEntered= LETTERS
            	    {
            	    int arrivalCircleEnteredStart50 = getCharIndex();
            	    int arrivalCircleEnteredStartLine50 = getLine();
            	    int arrivalCircleEnteredStartCharPos50 = getCharPositionInLine();
            	    mLETTERS(); 
            	    arrivalCircleEntered = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, arrivalCircleEnteredStart50, getCharIndex()-1);
            	    arrivalCircleEntered.setLine(arrivalCircleEnteredStartLine50);
            	    arrivalCircleEntered.setCharPositionInLine(arrivalCircleEnteredStartCharPos50);


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:280:13: (perpendicularPassed= LETTERS )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==' '||(LA2_0 >= 'A' && LA2_0 <= 'Z')||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:280:14: perpendicularPassed= LETTERS
            	    {
            	    int perpendicularPassedStart73 = getCharIndex();
            	    int perpendicularPassedStartLine73 = getLine();
            	    int perpendicularPassedStartCharPos73 = getCharPositionInLine();
            	    mLETTERS(); 
            	    perpendicularPassed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, perpendicularPassedStart73, getCharIndex()-1);
            	    perpendicularPassed.setLine(perpendicularPassedStartLine73);
            	    perpendicularPassed.setCharPositionInLine(perpendicularPassedStartCharPos73);


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:281:13: (circleRadius= NUMBER )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='.'||(LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:281:14: circleRadius= NUMBER
            	    {
            	    int circleRadiusStart96 = getCharIndex();
            	    int circleRadiusStartLine96 = getLine();
            	    int circleRadiusStartCharPos96 = getCharPositionInLine();
            	    mNUMBER(); 
            	    circleRadius = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, circleRadiusStart96, getCharIndex()-1);
            	    circleRadius.setLine(circleRadiusStartLine96);
            	    circleRadius.setCharPositionInLine(circleRadiusStartCharPos96);


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:282:13: ( LETTERS )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==' '||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:282:14: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:283:13: (wayPointID= LETTERS |wayPointID= NUMBER )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==' '||(LA5_0 >= 'A' && LA5_0 <= 'Z')||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
                    alt5=1;
                }
                else if ( (LA5_0=='.'||(LA5_0 >= '0' && LA5_0 <= '9')) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:283:14: wayPointID= LETTERS
            	    {
            	    int wayPointIDStart138 = getCharIndex();
            	    int wayPointIDStartLine138 = getLine();
            	    int wayPointIDStartCharPos138 = getCharPositionInLine();
            	    mLETTERS(); 
            	    wayPointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wayPointIDStart138, getCharIndex()-1);
            	    wayPointID.setLine(wayPointIDStartLine138);
            	    wayPointID.setCharPositionInLine(wayPointIDStartCharPos138);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:283:37: wayPointID= NUMBER
            	    {
            	    int wayPointIDStart146 = getCharIndex();
            	    int wayPointIDStartLine146 = getLine();
            	    int wayPointIDStartCharPos146 = getCharPositionInLine();
            	    mNUMBER(); 
            	    wayPointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wayPointIDStart146, getCharIndex()-1);
            	    wayPointID.setLine(wayPointIDStartLine146);
            	    wayPointID.setCharPositionInLine(wayPointIDStartCharPos146);


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            int checksumStart164 = getCharIndex();
            int checksumStartLine164 = getLine();
            int checksumStartCharPos164 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart164, getCharIndex()-1);
            checksum.setLine(checksumStartLine164);
            checksum.setCharPositionInLine(checksumStartCharPos164);



            	aam = new AAM(device.getText(), getText(),
            	arrivalCircleEntered != null ? arrivalCircleEntered.getText() : "", 
            	perpendicularPassed != null ? perpendicularPassed.getText() : "",
            	circleRadius != null ?  new Float(circleRadius.getText()).floatValue(): 0.0f,
            	wayPointID != null ? wayPointID.getText() : "");
            	
            	handler.doIt(aam);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AAM"

    // $ANTLR start "APB"
    public final void mAPB() throws RecognitionException {
        try {
            int _type = APB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken status0=null;
            CommonToken status1=null;
            CommonToken crossTrackErrorMagnitude=null;
            CommonToken directionToSteer=null;
            CommonToken crossTrackUnits=null;
            CommonToken status2=null;
            CommonToken status3=null;
            CommonToken bearingOriginToDestination=null;
            CommonToken bearingOriginToDestinationType=null;
            CommonToken destinationWaypointID=null;
            CommonToken bearingPresentPositionToDestination=null;
            CommonToken bearingPresentPositionToDestinationType=null;
            CommonToken headingToSteerToDestination=null;
            CommonToken headingToSteerToDestinationType=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:305:5: ( '$' device= DEVICE 'APB' SEP (status0= LETTERS )* SEP (status1= LETTERS )* SEP (crossTrackErrorMagnitude= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (crossTrackUnits= LETTERS )* SEP (status2= LETTERS )* SEP (status3= LETTERS )* SEP (bearingOriginToDestination= NUMBER )* SEP (bearingOriginToDestinationType= LETTERS )* SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (bearingPresentPositionToDestination= NUMBER )* SEP (bearingPresentPositionToDestinationType= LETTERS )* SEP (headingToSteerToDestination= NUMBER )* SEP (headingToSteerToDestinationType= LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:305:7: '$' device= DEVICE 'APB' SEP (status0= LETTERS )* SEP (status1= LETTERS )* SEP (crossTrackErrorMagnitude= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (crossTrackUnits= LETTERS )* SEP (status2= LETTERS )* SEP (status3= LETTERS )* SEP (bearingOriginToDestination= NUMBER )* SEP (bearingOriginToDestinationType= LETTERS )* SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (bearingPresentPositionToDestination= NUMBER )* SEP (bearingPresentPositionToDestinationType= LETTERS )* SEP (headingToSteerToDestination= NUMBER )* SEP (headingToSteerToDestinationType= LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart180 = getCharIndex();
            int deviceStartLine180 = getLine();
            int deviceStartCharPos180 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart180, getCharIndex()-1);
            device.setLine(deviceStartLine180);
            device.setCharPositionInLine(deviceStartCharPos180);


            match("APB"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:306:15: (status0= LETTERS )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==' '||(LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:306:16: status0= LETTERS
            	    {
            	    int status0Start205 = getCharIndex();
            	    int status0StartLine205 = getLine();
            	    int status0StartCharPos205 = getCharPositionInLine();
            	    mLETTERS(); 
            	    status0 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status0Start205, getCharIndex()-1);
            	    status0.setLine(status0StartLine205);
            	    status0.setCharPositionInLine(status0StartCharPos205);


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:307:15: (status1= LETTERS )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==' '||(LA7_0 >= 'A' && LA7_0 <= 'Z')||(LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:307:16: status1= LETTERS
            	    {
            	    int status1Start230 = getCharIndex();
            	    int status1StartLine230 = getLine();
            	    int status1StartCharPos230 = getCharPositionInLine();
            	    mLETTERS(); 
            	    status1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status1Start230, getCharIndex()-1);
            	    status1.setLine(status1StartLine230);
            	    status1.setCharPositionInLine(status1StartCharPos230);


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:308:15: (crossTrackErrorMagnitude= NUMBER )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='.'||(LA8_0 >= '0' && LA8_0 <= '9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:308:16: crossTrackErrorMagnitude= NUMBER
            	    {
            	    int crossTrackErrorMagnitudeStart255 = getCharIndex();
            	    int crossTrackErrorMagnitudeStartLine255 = getLine();
            	    int crossTrackErrorMagnitudeStartCharPos255 = getCharPositionInLine();
            	    mNUMBER(); 
            	    crossTrackErrorMagnitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorMagnitudeStart255, getCharIndex()-1);
            	    crossTrackErrorMagnitude.setLine(crossTrackErrorMagnitudeStartLine255);
            	    crossTrackErrorMagnitude.setCharPositionInLine(crossTrackErrorMagnitudeStartCharPos255);


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:309:15: (directionToSteer= LETTERS )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==' '||(LA9_0 >= 'A' && LA9_0 <= 'Z')||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:309:16: directionToSteer= LETTERS
            	    {
            	    int directionToSteerStart280 = getCharIndex();
            	    int directionToSteerStartLine280 = getLine();
            	    int directionToSteerStartCharPos280 = getCharPositionInLine();
            	    mLETTERS(); 
            	    directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart280, getCharIndex()-1);
            	    directionToSteer.setLine(directionToSteerStartLine280);
            	    directionToSteer.setCharPositionInLine(directionToSteerStartCharPos280);


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:310:15: (crossTrackUnits= LETTERS )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==' '||(LA10_0 >= 'A' && LA10_0 <= 'Z')||(LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:310:16: crossTrackUnits= LETTERS
            	    {
            	    int crossTrackUnitsStart305 = getCharIndex();
            	    int crossTrackUnitsStartLine305 = getLine();
            	    int crossTrackUnitsStartCharPos305 = getCharPositionInLine();
            	    mLETTERS(); 
            	    crossTrackUnits = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackUnitsStart305, getCharIndex()-1);
            	    crossTrackUnits.setLine(crossTrackUnitsStartLine305);
            	    crossTrackUnits.setCharPositionInLine(crossTrackUnitsStartCharPos305);


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:311:15: (status2= LETTERS )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==' '||(LA11_0 >= 'A' && LA11_0 <= 'Z')||(LA11_0 >= 'a' && LA11_0 <= 'z')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:311:16: status2= LETTERS
            	    {
            	    int status2Start330 = getCharIndex();
            	    int status2StartLine330 = getLine();
            	    int status2StartCharPos330 = getCharPositionInLine();
            	    mLETTERS(); 
            	    status2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status2Start330, getCharIndex()-1);
            	    status2.setLine(status2StartLine330);
            	    status2.setCharPositionInLine(status2StartCharPos330);


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:312:15: (status3= LETTERS )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==' '||(LA12_0 >= 'A' && LA12_0 <= 'Z')||(LA12_0 >= 'a' && LA12_0 <= 'z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:312:16: status3= LETTERS
            	    {
            	    int status3Start355 = getCharIndex();
            	    int status3StartLine355 = getLine();
            	    int status3StartCharPos355 = getCharPositionInLine();
            	    mLETTERS(); 
            	    status3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status3Start355, getCharIndex()-1);
            	    status3.setLine(status3StartLine355);
            	    status3.setCharPositionInLine(status3StartCharPos355);


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:313:15: (bearingOriginToDestination= NUMBER )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='.'||(LA13_0 >= '0' && LA13_0 <= '9')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:313:16: bearingOriginToDestination= NUMBER
            	    {
            	    int bearingOriginToDestinationStart380 = getCharIndex();
            	    int bearingOriginToDestinationStartLine380 = getLine();
            	    int bearingOriginToDestinationStartCharPos380 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingOriginToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingOriginToDestinationStart380, getCharIndex()-1);
            	    bearingOriginToDestination.setLine(bearingOriginToDestinationStartLine380);
            	    bearingOriginToDestination.setCharPositionInLine(bearingOriginToDestinationStartCharPos380);


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:314:15: (bearingOriginToDestinationType= LETTERS )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==' '||(LA14_0 >= 'A' && LA14_0 <= 'Z')||(LA14_0 >= 'a' && LA14_0 <= 'z')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:314:16: bearingOriginToDestinationType= LETTERS
            	    {
            	    int bearingOriginToDestinationTypeStart403 = getCharIndex();
            	    int bearingOriginToDestinationTypeStartLine403 = getLine();
            	    int bearingOriginToDestinationTypeStartCharPos403 = getCharPositionInLine();
            	    mLETTERS(); 
            	    bearingOriginToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingOriginToDestinationTypeStart403, getCharIndex()-1);
            	    bearingOriginToDestinationType.setLine(bearingOriginToDestinationTypeStartLine403);
            	    bearingOriginToDestinationType.setCharPositionInLine(bearingOriginToDestinationTypeStartCharPos403);


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:315:15: (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==' '||(LA15_0 >= 'A' && LA15_0 <= 'Z')||(LA15_0 >= 'a' && LA15_0 <= 'z')) ) {
                    alt15=1;
                }
                else if ( (LA15_0=='.'||(LA15_0 >= '0' && LA15_0 <= '9')) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:315:16: destinationWaypointID= LETTERS
            	    {
            	    int destinationWaypointIDStart428 = getCharIndex();
            	    int destinationWaypointIDStartLine428 = getLine();
            	    int destinationWaypointIDStartCharPos428 = getCharPositionInLine();
            	    mLETTERS(); 
            	    destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart428, getCharIndex()-1);
            	    destinationWaypointID.setLine(destinationWaypointIDStartLine428);
            	    destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos428);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:315:50: destinationWaypointID= NUMBER
            	    {
            	    int destinationWaypointIDStart436 = getCharIndex();
            	    int destinationWaypointIDStartLine436 = getLine();
            	    int destinationWaypointIDStartCharPos436 = getCharPositionInLine();
            	    mNUMBER(); 
            	    destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart436, getCharIndex()-1);
            	    destinationWaypointID.setLine(destinationWaypointIDStartLine436);
            	    destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos436);


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:316:15: (bearingPresentPositionToDestination= NUMBER )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='.'||(LA16_0 >= '0' && LA16_0 <= '9')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:316:16: bearingPresentPositionToDestination= NUMBER
            	    {
            	    int bearingPresentPositionToDestinationStart461 = getCharIndex();
            	    int bearingPresentPositionToDestinationStartLine461 = getLine();
            	    int bearingPresentPositionToDestinationStartCharPos461 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingPresentPositionToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingPresentPositionToDestinationStart461, getCharIndex()-1);
            	    bearingPresentPositionToDestination.setLine(bearingPresentPositionToDestinationStartLine461);
            	    bearingPresentPositionToDestination.setCharPositionInLine(bearingPresentPositionToDestinationStartCharPos461);


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:317:15: (bearingPresentPositionToDestinationType= LETTERS )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==' '||(LA17_0 >= 'A' && LA17_0 <= 'Z')||(LA17_0 >= 'a' && LA17_0 <= 'z')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:317:16: bearingPresentPositionToDestinationType= LETTERS
            	    {
            	    int bearingPresentPositionToDestinationTypeStart484 = getCharIndex();
            	    int bearingPresentPositionToDestinationTypeStartLine484 = getLine();
            	    int bearingPresentPositionToDestinationTypeStartCharPos484 = getCharPositionInLine();
            	    mLETTERS(); 
            	    bearingPresentPositionToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingPresentPositionToDestinationTypeStart484, getCharIndex()-1);
            	    bearingPresentPositionToDestinationType.setLine(bearingPresentPositionToDestinationTypeStartLine484);
            	    bearingPresentPositionToDestinationType.setCharPositionInLine(bearingPresentPositionToDestinationTypeStartCharPos484);


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:318:15: (headingToSteerToDestination= NUMBER )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='.'||(LA18_0 >= '0' && LA18_0 <= '9')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:318:16: headingToSteerToDestination= NUMBER
            	    {
            	    int headingToSteerToDestinationStart509 = getCharIndex();
            	    int headingToSteerToDestinationStartLine509 = getLine();
            	    int headingToSteerToDestinationStartCharPos509 = getCharPositionInLine();
            	    mNUMBER(); 
            	    headingToSteerToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingToSteerToDestinationStart509, getCharIndex()-1);
            	    headingToSteerToDestination.setLine(headingToSteerToDestinationStartLine509);
            	    headingToSteerToDestination.setCharPositionInLine(headingToSteerToDestinationStartCharPos509);


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:319:15: (headingToSteerToDestinationType= LETTERS )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==' '||(LA19_0 >= 'A' && LA19_0 <= 'Z')||(LA19_0 >= 'a' && LA19_0 <= 'z')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:319:16: headingToSteerToDestinationType= LETTERS
            	    {
            	    int headingToSteerToDestinationTypeStart534 = getCharIndex();
            	    int headingToSteerToDestinationTypeStartLine534 = getLine();
            	    int headingToSteerToDestinationTypeStartCharPos534 = getCharPositionInLine();
            	    mLETTERS(); 
            	    headingToSteerToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingToSteerToDestinationTypeStart534, getCharIndex()-1);
            	    headingToSteerToDestinationType.setLine(headingToSteerToDestinationTypeStartLine534);
            	    headingToSteerToDestinationType.setCharPositionInLine(headingToSteerToDestinationTypeStartCharPos534);


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            int checksumStart554 = getCharIndex();
            int checksumStartLine554 = getLine();
            int checksumStartCharPos554 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart554, getCharIndex()-1);
            checksum.setLine(checksumStartLine554);
            checksum.setCharPositionInLine(checksumStartCharPos554);



            	apb = new APB(device.getText(), getText(),
            	status0 != null ? status0.getText() : "",
            	status1 != null ? status1.getText() : "",
            	crossTrackErrorMagnitude != null ? new Float(crossTrackErrorMagnitude.getText()).floatValue(): 0.0f,
            	directionToSteer != null ? directionToSteer.getText() : "",
            	crossTrackUnits != null ? crossTrackUnits.getText() : "",
            	status2 != null ? status2.getText() : "",
            	status3 != null ? status3.getText() : "",
            	bearingOriginToDestination != null ? (new Integer(bearingOriginToDestination.getText())).intValue() : 0,
            	bearingOriginToDestinationType != null ? bearingOriginToDestinationType.getText() : "",
            	destinationWaypointID != null ? destinationWaypointID.getText() : "",
            	bearingPresentPositionToDestination != null ? (new Integer(bearingPresentPositionToDestination.getText())).intValue() : 0,
            	bearingPresentPositionToDestinationType != null ? bearingPresentPositionToDestinationType.getText() : "",
            	headingToSteerToDestination != null ? (new Integer(headingToSteerToDestination.getText())).intValue() : 0,
            	headingToSteerToDestinationType != null ? headingToSteerToDestinationType.getText() : "");
            	
            	handler.doIt(apb);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "APB"

    // $ANTLR start "BEC"
    public final void mBEC() throws RecognitionException {
        try {
            int _type = BEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken bearingDegreesTrue=null;
            CommonToken bearingDegreesMagnetic=null;
            CommonToken distanceToWayPoint=null;
            CommonToken unitsOfDistanceToWayPoint=null;
            CommonToken waypointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:345:7: ( '$' device= DEVICE 'BEC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:345:9: '$' device= DEVICE 'BEC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart574 = getCharIndex();
            int deviceStartLine574 = getLine();
            int deviceStartCharPos574 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart574, getCharIndex()-1);
            device.setLine(deviceStartLine574);
            device.setCharPositionInLine(deviceStartCharPos574);


            match("BEC"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:346:7: ( ' ' )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==' ') ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:346:7: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            int utcStart604 = getCharIndex();
            int utcStartLine604 = getLine();
            int utcStartCharPos604 = getCharPositionInLine();
            mNUMBER(); 
            utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart604, getCharIndex()-1);
            utc.setLine(utcStartLine604);
            utc.setCharPositionInLine(utcStartCharPos604);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:347:25: ( SEP )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==',') ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:348:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='.'||(LA22_0 >= '0' && LA22_0 <= '9')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:348:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
            	    {
            	    int latitudeStart624 = getCharIndex();
            	    int latitudeStartLine624 = getLine();
            	    int latitudeStartCharPos624 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart624, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine624);
            	    latitude.setCharPositionInLine(latitudeStartCharPos624);


            	    mSEP(); 


            	    int nsStart632 = getCharIndex();
            	    int nsStartLine632 = getLine();
            	    int nsStartCharPos632 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart632, getCharIndex()-1);
            	    ns.setLine(nsStartLine632);
            	    ns.setCharPositionInLine(nsStartCharPos632);


            	    mSEP(); 


            	    int longitudeStart638 = getCharIndex();
            	    int longitudeStartLine638 = getLine();
            	    int longitudeStartCharPos638 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart638, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine638);
            	    longitude.setCharPositionInLine(longitudeStartCharPos638);


            	    mSEP(); 


            	    int ewStart644 = getCharIndex();
            	    int ewStartLine644 = getLine();
            	    int ewStartCharPos644 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart644, getCharIndex()-1);
            	    ew.setLine(ewStartLine644);
            	    ew.setCharPositionInLine(ewStartCharPos644);


            	    mSEP(); 


            	    int bearingDegreesTrueStart650 = getCharIndex();
            	    int bearingDegreesTrueStartLine650 = getLine();
            	    int bearingDegreesTrueStartCharPos650 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart650, getCharIndex()-1);
            	    bearingDegreesTrue.setLine(bearingDegreesTrueStartLine650);
            	    bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos650);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:349:22: ( SEP )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==',') ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:350:13: (bearingDegreesMagnetic= NUMBER SEP )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='.'||(LA24_0 >= '0' && LA24_0 <= '9')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:350:14: bearingDegreesMagnetic= NUMBER SEP
            	    {
            	    int bearingDegreesMagneticStart689 = getCharIndex();
            	    int bearingDegreesMagneticStartLine689 = getLine();
            	    int bearingDegreesMagneticStartCharPos689 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart689, getCharIndex()-1);
            	    bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine689);
            	    bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos689);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:351:22: ( SEP )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==',') ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:352:13: (distanceToWayPoint= NUMBER SEP )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='.'||(LA26_0 >= '0' && LA26_0 <= '9')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:352:14: distanceToWayPoint= NUMBER SEP
            	    {
            	    int distanceToWayPointStart729 = getCharIndex();
            	    int distanceToWayPointStartLine729 = getLine();
            	    int distanceToWayPointStartCharPos729 = getCharPositionInLine();
            	    mNUMBER(); 
            	    distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart729, getCharIndex()-1);
            	    distanceToWayPoint.setLine(distanceToWayPointStartLine729);
            	    distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos729);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            int unitsOfDistanceToWayPointStart750 = getCharIndex();
            int unitsOfDistanceToWayPointStartLine750 = getLine();
            int unitsOfDistanceToWayPointStartCharPos750 = getCharPositionInLine();
            mLETTERS(); 
            unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart750, getCharIndex()-1);
            unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine750);
            unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos750);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:353:48: ( SEP )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==',') ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:354:13: (waypointID= LETTERS |waypointID= NUMBER )*
            loop28:
            do {
                int alt28=3;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==' '||(LA28_0 >= 'A' && LA28_0 <= 'Z')||(LA28_0 >= 'a' && LA28_0 <= 'z')) ) {
                    alt28=1;
                }
                else if ( (LA28_0=='.'||(LA28_0 >= '0' && LA28_0 <= '9')) ) {
                    alt28=2;
                }


                switch (alt28) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:354:14: waypointID= LETTERS
            	    {
            	    int waypointIDStart770 = getCharIndex();
            	    int waypointIDStartLine770 = getLine();
            	    int waypointIDStartCharPos770 = getCharPositionInLine();
            	    mLETTERS(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart770, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine770);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos770);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:354:35: waypointID= NUMBER
            	    {
            	    int waypointIDStart776 = getCharIndex();
            	    int waypointIDStartLine776 = getLine();
            	    int waypointIDStartCharPos776 = getCharPositionInLine();
            	    mNUMBER(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart776, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine776);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos776);


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            int checksumStart795 = getCharIndex();
            int checksumStartLine795 = getLine();
            int checksumStartCharPos795 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart795, getCharIndex()-1);
            checksum.setLine(checksumStartLine795);
            checksum.setCharPositionInLine(checksumStartCharPos795);



                    date = dateFactory(utc.getText()); 
                    
                    lat = latConvert(latitude != null ? (new Float(latitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
                    lon = lonConvert(longitude != null ? (new Float(longitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
                        
                    bdt =  bearingDegreesTrue != null ? new Float(bearingDegreesTrue.getText()).floatValue() : 0.0f;
                    bdm = bearingDegreesMagnetic != null ?  new Float(bearingDegreesMagnetic.getText()).floatValue() : 0.0f;
                    dtwp = distanceToWayPoint != null ? new Float(distanceToWayPoint.getText()).floatValue() : 0.0f;
                    wid= waypointID != null ? waypointID.getText() : "";
                    uodtwp = unitsOfDistanceToWayPoint.getText() ;
                    bec = new BEC(device.getText(), getText(), 
                    date, 
                    lat, lon,
                    bdt, bdm, 
                    dtwp, uodtwp, 
                    wid);
                    
                    handler.doIt(bwc);
                   

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEC"

    // $ANTLR start "BOD"
    public final void mBOD() throws RecognitionException {
        try {
            int _type = BOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken bearingDegreesTrue=null;
            CommonToken bearingDegreesMagnetic=null;
            CommonToken destinationWaypointID=null;
            CommonToken originWaypointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:377:7: ( '$' device= DEVICE 'BOD' SEP (bearingDegreesTrue= NUMBER )* ( SEP )+ LETTERS SEP (bearingDegreesMagnetic= NUMBER )* ( SEP )+ LETTERS SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (originWaypointID= LETTERS |originWaypointID= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:377:9: '$' device= DEVICE 'BOD' SEP (bearingDegreesTrue= NUMBER )* ( SEP )+ LETTERS SEP (bearingDegreesMagnetic= NUMBER )* ( SEP )+ LETTERS SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (originWaypointID= LETTERS |originWaypointID= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart824 = getCharIndex();
            int deviceStartLine824 = getLine();
            int deviceStartCharPos824 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart824, getCharIndex()-1);
            device.setLine(deviceStartLine824);
            device.setCharPositionInLine(deviceStartCharPos824);


            match("BOD"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:378:6: (bearingDegreesTrue= NUMBER )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0=='.'||(LA29_0 >= '0' && LA29_0 <= '9')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:378:7: bearingDegreesTrue= NUMBER
            	    {
            	    int bearingDegreesTrueStart838 = getCharIndex();
            	    int bearingDegreesTrueStartLine838 = getLine();
            	    int bearingDegreesTrueStartCharPos838 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart838, getCharIndex()-1);
            	    bearingDegreesTrue.setLine(bearingDegreesTrueStartLine838);
            	    bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos838);


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:378:35: ( SEP )+
            int cnt30=0;
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==',') ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt30 >= 1 ) break loop30;
                        EarlyExitException eee =
                            new EarlyExitException(30, input);
                        throw eee;
                }
                cnt30++;
            } while (true);


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:380:6: (bearingDegreesMagnetic= NUMBER )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0=='.'||(LA31_0 >= '0' && LA31_0 <= '9')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:380:7: bearingDegreesMagnetic= NUMBER
            	    {
            	    int bearingDegreesMagneticStart862 = getCharIndex();
            	    int bearingDegreesMagneticStartLine862 = getLine();
            	    int bearingDegreesMagneticStartCharPos862 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart862, getCharIndex()-1);
            	    bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine862);
            	    bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos862);


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:380:39: ( SEP )+
            int cnt32=0;
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==',') ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
                        EarlyExitException eee =
                            new EarlyExitException(32, input);
                        throw eee;
                }
                cnt32++;
            } while (true);


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:382:6: (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )*
            loop33:
            do {
                int alt33=3;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==' '||(LA33_0 >= 'A' && LA33_0 <= 'Z')||(LA33_0 >= 'a' && LA33_0 <= 'z')) ) {
                    alt33=1;
                }
                else if ( (LA33_0=='.'||(LA33_0 >= '0' && LA33_0 <= '9')) ) {
                    alt33=2;
                }


                switch (alt33) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:382:7: destinationWaypointID= LETTERS
            	    {
            	    int destinationWaypointIDStart886 = getCharIndex();
            	    int destinationWaypointIDStartLine886 = getLine();
            	    int destinationWaypointIDStartCharPos886 = getCharPositionInLine();
            	    mLETTERS(); 
            	    destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart886, getCharIndex()-1);
            	    destinationWaypointID.setLine(destinationWaypointIDStartLine886);
            	    destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos886);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:382:39: destinationWaypointID= NUMBER
            	    {
            	    int destinationWaypointIDStart892 = getCharIndex();
            	    int destinationWaypointIDStartLine892 = getLine();
            	    int destinationWaypointIDStartCharPos892 = getCharPositionInLine();
            	    mNUMBER(); 
            	    destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart892, getCharIndex()-1);
            	    destinationWaypointID.setLine(destinationWaypointIDStartLine892);
            	    destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos892);


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:383:6: (originWaypointID= LETTERS |originWaypointID= NUMBER )*
            loop34:
            do {
                int alt34=3;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==' '||(LA34_0 >= 'A' && LA34_0 <= 'Z')||(LA34_0 >= 'a' && LA34_0 <= 'z')) ) {
                    alt34=1;
                }
                else if ( (LA34_0=='.'||(LA34_0 >= '0' && LA34_0 <= '9')) ) {
                    alt34=2;
                }


                switch (alt34) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:383:7: originWaypointID= LETTERS
            	    {
            	    int originWaypointIDStart907 = getCharIndex();
            	    int originWaypointIDStartLine907 = getLine();
            	    int originWaypointIDStartCharPos907 = getCharPositionInLine();
            	    mLETTERS(); 
            	    originWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, originWaypointIDStart907, getCharIndex()-1);
            	    originWaypointID.setLine(originWaypointIDStartLine907);
            	    originWaypointID.setCharPositionInLine(originWaypointIDStartCharPos907);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:383:34: originWaypointID= NUMBER
            	    {
            	    int originWaypointIDStart913 = getCharIndex();
            	    int originWaypointIDStartLine913 = getLine();
            	    int originWaypointIDStartCharPos913 = getCharPositionInLine();
            	    mNUMBER(); 
            	    originWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, originWaypointIDStart913, getCharIndex()-1);
            	    originWaypointID.setLine(originWaypointIDStartLine913);
            	    originWaypointID.setCharPositionInLine(originWaypointIDStartCharPos913);


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            int checksumStart924 = getCharIndex();
            int checksumStartLine924 = getLine();
            int checksumStartCharPos924 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart924, getCharIndex()-1);
            checksum.setLine(checksumStartLine924);
            checksum.setCharPositionInLine(checksumStartCharPos924);



             		bod = new BOD(device.getText(), getText(),
             		bearingDegreesTrue != null ? new Float(bearingDegreesTrue.getText()).floatValue() : 0.0f,
             		bearingDegreesMagnetic != null ? new Float(bearingDegreesMagnetic.getText()).floatValue() : 0.0f,
             		destinationWaypointID != null ? destinationWaypointID.getText() : "",
             		originWaypointID != null ? originWaypointID.getText() : "");
             
             		 handler.doIt(bod);
             

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOD"

    // $ANTLR start "BWC"
    public final void mBWC() throws RecognitionException {
        try {
            int _type = BWC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken bearingDegreesTrue=null;
            CommonToken bearingDegreesMagnetic=null;
            CommonToken distanceToWayPoint=null;
            CommonToken unitsOfDistanceToWayPoint=null;
            CommonToken waypointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:396:6: ( '$' device= DEVICE 'BWC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:396:9: '$' device= DEVICE 'BWC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart946 = getCharIndex();
            int deviceStartLine946 = getLine();
            int deviceStartCharPos946 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart946, getCharIndex()-1);
            device.setLine(deviceStartLine946);
            device.setCharPositionInLine(deviceStartCharPos946);


            match("BWC"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:397:14: ( ' ' )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==' ') ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:397:14: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            int utcStart983 = getCharIndex();
            int utcStartLine983 = getLine();
            int utcStartCharPos983 = getCharPositionInLine();
            mNUMBER(); 
            utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart983, getCharIndex()-1);
            utc.setLine(utcStartLine983);
            utc.setCharPositionInLine(utcStartCharPos983);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:398:25: ( SEP )+
            int cnt36=0;
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==',') ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt36 >= 1 ) break loop36;
                        EarlyExitException eee =
                            new EarlyExitException(36, input);
                        throw eee;
                }
                cnt36++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:399:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0=='.'||(LA37_0 >= '0' && LA37_0 <= '9')) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:399:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
            	    {
            	    int latitudeStart1003 = getCharIndex();
            	    int latitudeStartLine1003 = getLine();
            	    int latitudeStartCharPos1003 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart1003, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine1003);
            	    latitude.setCharPositionInLine(latitudeStartCharPos1003);


            	    mSEP(); 


            	    int nsStart1011 = getCharIndex();
            	    int nsStartLine1011 = getLine();
            	    int nsStartCharPos1011 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1011, getCharIndex()-1);
            	    ns.setLine(nsStartLine1011);
            	    ns.setCharPositionInLine(nsStartCharPos1011);


            	    mSEP(); 


            	    int longitudeStart1017 = getCharIndex();
            	    int longitudeStartLine1017 = getLine();
            	    int longitudeStartCharPos1017 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1017, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine1017);
            	    longitude.setCharPositionInLine(longitudeStartCharPos1017);


            	    mSEP(); 


            	    int ewStart1023 = getCharIndex();
            	    int ewStartLine1023 = getLine();
            	    int ewStartCharPos1023 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1023, getCharIndex()-1);
            	    ew.setLine(ewStartLine1023);
            	    ew.setCharPositionInLine(ewStartCharPos1023);


            	    mSEP(); 


            	    int bearingDegreesTrueStart1029 = getCharIndex();
            	    int bearingDegreesTrueStartLine1029 = getLine();
            	    int bearingDegreesTrueStartCharPos1029 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1029, getCharIndex()-1);
            	    bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1029);
            	    bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1029);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:400:22: ( SEP )+
            int cnt38=0;
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==',') ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt38 >= 1 ) break loop38;
                        EarlyExitException eee =
                            new EarlyExitException(38, input);
                        throw eee;
                }
                cnt38++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:401:13: (bearingDegreesMagnetic= NUMBER SEP )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0=='.'||(LA39_0 >= '0' && LA39_0 <= '9')) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:401:14: bearingDegreesMagnetic= NUMBER SEP
            	    {
            	    int bearingDegreesMagneticStart1068 = getCharIndex();
            	    int bearingDegreesMagneticStartLine1068 = getLine();
            	    int bearingDegreesMagneticStartCharPos1068 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1068, getCharIndex()-1);
            	    bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1068);
            	    bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1068);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:402:22: ( SEP )+
            int cnt40=0;
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==',') ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt40 >= 1 ) break loop40;
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:403:13: (distanceToWayPoint= NUMBER SEP )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0=='.'||(LA41_0 >= '0' && LA41_0 <= '9')) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:403:14: distanceToWayPoint= NUMBER SEP
            	    {
            	    int distanceToWayPointStart1108 = getCharIndex();
            	    int distanceToWayPointStartLine1108 = getLine();
            	    int distanceToWayPointStartCharPos1108 = getCharPositionInLine();
            	    mNUMBER(); 
            	    distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart1108, getCharIndex()-1);
            	    distanceToWayPoint.setLine(distanceToWayPointStartLine1108);
            	    distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos1108);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            int unitsOfDistanceToWayPointStart1129 = getCharIndex();
            int unitsOfDistanceToWayPointStartLine1129 = getLine();
            int unitsOfDistanceToWayPointStartCharPos1129 = getCharPositionInLine();
            mLETTERS(); 
            unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart1129, getCharIndex()-1);
            unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine1129);
            unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos1129);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:404:48: ( SEP )+
            int cnt42=0;
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==',') ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt42 >= 1 ) break loop42;
                        EarlyExitException eee =
                            new EarlyExitException(42, input);
                        throw eee;
                }
                cnt42++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:405:13: (waypointID= LETTERS |waypointID= NUMBER )*
            loop43:
            do {
                int alt43=3;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==' '||(LA43_0 >= 'A' && LA43_0 <= 'Z')||(LA43_0 >= 'a' && LA43_0 <= 'z')) ) {
                    alt43=1;
                }
                else if ( (LA43_0=='.'||(LA43_0 >= '0' && LA43_0 <= '9')) ) {
                    alt43=2;
                }


                switch (alt43) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:405:14: waypointID= LETTERS
            	    {
            	    int waypointIDStart1149 = getCharIndex();
            	    int waypointIDStartLine1149 = getLine();
            	    int waypointIDStartCharPos1149 = getCharPositionInLine();
            	    mLETTERS(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1149, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine1149);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos1149);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:405:35: waypointID= NUMBER
            	    {
            	    int waypointIDStart1155 = getCharIndex();
            	    int waypointIDStartLine1155 = getLine();
            	    int waypointIDStartCharPos1155 = getCharPositionInLine();
            	    mNUMBER(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1155, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine1155);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos1155);


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            int checksumStart1174 = getCharIndex();
            int checksumStartLine1174 = getLine();
            int checksumStartCharPos1174 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1174, getCharIndex()-1);
            checksum.setLine(checksumStartLine1174);
            checksum.setCharPositionInLine(checksumStartCharPos1174);



                    date = dateFactory(utc.getText()); 
                    
                    lat = latConvert(latitude != null ? (new Float(latitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
                    lon = lonConvert(longitude != null ? (new Float(longitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
                        
                    bdt =  bearingDegreesTrue != null ? new Float(bearingDegreesTrue.getText()).floatValue() : 0.0f;
                    bdm = bearingDegreesMagnetic != null ?  new Float(bearingDegreesMagnetic.getText()).floatValue() : 0.0f;
                    dtwp = distanceToWayPoint != null ? new Float(distanceToWayPoint.getText()).floatValue() : 0.0f;
                    wid= waypointID != null ? waypointID.getText() : "";
                    uodtwp = unitsOfDistanceToWayPoint.getText() ;
                    bwc = new BWC(device.getText(), getText(), 
                    date, 
                    lat, lon, 
                    bdt, bdm, 
                    dtwp, uodtwp, 
                    wid);
                    
                    handler.doIt(bwc);
                   

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BWC"

    // $ANTLR start "BWR"
    public final void mBWR() throws RecognitionException {
        try {
            int _type = BWR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken bearingDegreesTrue=null;
            CommonToken bearingDegreesMagnetic=null;
            CommonToken distanceToWayPoint=null;
            CommonToken unitsOfDistanceToWayPoint=null;
            CommonToken waypointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:430:6: ( '$' device= DEVICE 'BWR' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:430:10: '$' device= DEVICE 'BWR' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart1208 = getCharIndex();
            int deviceStartLine1208 = getLine();
            int deviceStartCharPos1208 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1208, getCharIndex()-1);
            device.setLine(deviceStartLine1208);
            device.setCharPositionInLine(deviceStartCharPos1208);


            match("BWR"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:431:7: ( ' ' )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==' ') ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:431:7: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            int utcStart1239 = getCharIndex();
            int utcStartLine1239 = getLine();
            int utcStartCharPos1239 = getCharPositionInLine();
            mNUMBER(); 
            utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart1239, getCharIndex()-1);
            utc.setLine(utcStartLine1239);
            utc.setCharPositionInLine(utcStartCharPos1239);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:432:25: ( SEP )+
            int cnt45=0;
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==',') ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt45 >= 1 ) break loop45;
                        EarlyExitException eee =
                            new EarlyExitException(45, input);
                        throw eee;
                }
                cnt45++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:433:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0=='.'||(LA46_0 >= '0' && LA46_0 <= '9')) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:433:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
            	    {
            	    int latitudeStart1259 = getCharIndex();
            	    int latitudeStartLine1259 = getLine();
            	    int latitudeStartCharPos1259 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart1259, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine1259);
            	    latitude.setCharPositionInLine(latitudeStartCharPos1259);


            	    mSEP(); 


            	    int nsStart1267 = getCharIndex();
            	    int nsStartLine1267 = getLine();
            	    int nsStartCharPos1267 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1267, getCharIndex()-1);
            	    ns.setLine(nsStartLine1267);
            	    ns.setCharPositionInLine(nsStartCharPos1267);


            	    mSEP(); 


            	    int longitudeStart1273 = getCharIndex();
            	    int longitudeStartLine1273 = getLine();
            	    int longitudeStartCharPos1273 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1273, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine1273);
            	    longitude.setCharPositionInLine(longitudeStartCharPos1273);


            	    mSEP(); 


            	    int ewStart1279 = getCharIndex();
            	    int ewStartLine1279 = getLine();
            	    int ewStartCharPos1279 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1279, getCharIndex()-1);
            	    ew.setLine(ewStartLine1279);
            	    ew.setCharPositionInLine(ewStartCharPos1279);


            	    mSEP(); 


            	    int bearingDegreesTrueStart1285 = getCharIndex();
            	    int bearingDegreesTrueStartLine1285 = getLine();
            	    int bearingDegreesTrueStartCharPos1285 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1285, getCharIndex()-1);
            	    bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1285);
            	    bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1285);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:434:22: ( SEP )+
            int cnt47=0;
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==',') ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt47 >= 1 ) break loop47;
                        EarlyExitException eee =
                            new EarlyExitException(47, input);
                        throw eee;
                }
                cnt47++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:435:13: (bearingDegreesMagnetic= NUMBER SEP )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0=='.'||(LA48_0 >= '0' && LA48_0 <= '9')) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:435:14: bearingDegreesMagnetic= NUMBER SEP
            	    {
            	    int bearingDegreesMagneticStart1324 = getCharIndex();
            	    int bearingDegreesMagneticStartLine1324 = getLine();
            	    int bearingDegreesMagneticStartCharPos1324 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1324, getCharIndex()-1);
            	    bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1324);
            	    bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1324);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:436:22: ( SEP )+
            int cnt49=0;
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==',') ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt49 >= 1 ) break loop49;
                        EarlyExitException eee =
                            new EarlyExitException(49, input);
                        throw eee;
                }
                cnt49++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:437:13: (distanceToWayPoint= NUMBER SEP )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0=='.'||(LA50_0 >= '0' && LA50_0 <= '9')) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:437:14: distanceToWayPoint= NUMBER SEP
            	    {
            	    int distanceToWayPointStart1364 = getCharIndex();
            	    int distanceToWayPointStartLine1364 = getLine();
            	    int distanceToWayPointStartCharPos1364 = getCharPositionInLine();
            	    mNUMBER(); 
            	    distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart1364, getCharIndex()-1);
            	    distanceToWayPoint.setLine(distanceToWayPointStartLine1364);
            	    distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos1364);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            int unitsOfDistanceToWayPointStart1385 = getCharIndex();
            int unitsOfDistanceToWayPointStartLine1385 = getLine();
            int unitsOfDistanceToWayPointStartCharPos1385 = getCharPositionInLine();
            mLETTERS(); 
            unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart1385, getCharIndex()-1);
            unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine1385);
            unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos1385);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:438:48: ( SEP )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==',') ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt51 >= 1 ) break loop51;
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:439:13: (waypointID= LETTERS |waypointID= NUMBER )*
            loop52:
            do {
                int alt52=3;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==' '||(LA52_0 >= 'A' && LA52_0 <= 'Z')||(LA52_0 >= 'a' && LA52_0 <= 'z')) ) {
                    alt52=1;
                }
                else if ( (LA52_0=='.'||(LA52_0 >= '0' && LA52_0 <= '9')) ) {
                    alt52=2;
                }


                switch (alt52) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:439:14: waypointID= LETTERS
            	    {
            	    int waypointIDStart1405 = getCharIndex();
            	    int waypointIDStartLine1405 = getLine();
            	    int waypointIDStartCharPos1405 = getCharPositionInLine();
            	    mLETTERS(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1405, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine1405);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos1405);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:439:35: waypointID= NUMBER
            	    {
            	    int waypointIDStart1411 = getCharIndex();
            	    int waypointIDStartLine1411 = getLine();
            	    int waypointIDStartCharPos1411 = getCharPositionInLine();
            	    mNUMBER(); 
            	    waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1411, getCharIndex()-1);
            	    waypointID.setLine(waypointIDStartLine1411);
            	    waypointID.setCharPositionInLine(waypointIDStartCharPos1411);


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            int checksumStart1430 = getCharIndex();
            int checksumStartLine1430 = getLine();
            int checksumStartCharPos1430 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1430, getCharIndex()-1);
            checksum.setLine(checksumStartLine1430);
            checksum.setCharPositionInLine(checksumStartCharPos1430);



                    date = dateFactory(utc.getText()); 
                    
                    lat = latConvert(latitude != null ? (new Float(latitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
                    lon = lonConvert(longitude != null ? (new Float(longitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
                        
                    bdt =  bearingDegreesTrue != null ? new Float(bearingDegreesTrue.getText()).floatValue() : 0.0f;
                    bdm = bearingDegreesMagnetic != null ?  new Float(bearingDegreesMagnetic.getText()).floatValue() : 0.0f;
                    dtwp = distanceToWayPoint != null ? new Float(distanceToWayPoint.getText()).floatValue() : 0.0f;
                    wid= waypointID != null ? waypointID.getText() : "";
                    uodtwp = unitsOfDistanceToWayPoint.getText() ;
                    bwr = new BWR(device.getText(), getText(),
                    date, 
                    lat, lon, 
                    bdt, bdm, 
                    dtwp, uodtwp, 
                    wid);
                    
                    handler.doIt(bwr);
                   

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BWR"

    // $ANTLR start "BWW"
    public final void mBWW() throws RecognitionException {
        try {
            int _type = BWW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken bearingDegreesTrue=null;
            CommonToken bearingDegreesMagnetic=null;
            CommonToken toWaypointID=null;
            CommonToken fromWaypointID=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:486:6: ( '$' device= DEVICE 'BWW' SEP bearingDegreesTrue= NUMBER SEP LETTERS SEP bearingDegreesMagnetic= NUMBER SEP LETTERS SEP (toWaypointID= LETTERS |toWaypointID= NUMBER ) SEP (fromWaypointID= LETTERS |fromWaypointID= NUMBER ) SEP checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:486:8: '$' device= DEVICE 'BWW' SEP bearingDegreesTrue= NUMBER SEP LETTERS SEP bearingDegreesMagnetic= NUMBER SEP LETTERS SEP (toWaypointID= LETTERS |toWaypointID= NUMBER ) SEP (fromWaypointID= LETTERS |fromWaypointID= NUMBER ) SEP checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart1466 = getCharIndex();
            int deviceStartLine1466 = getLine();
            int deviceStartCharPos1466 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1466, getCharIndex()-1);
            device.setLine(deviceStartLine1466);
            device.setCharPositionInLine(deviceStartCharPos1466);


            match("BWW"); 



            mSEP(); 


            int bearingDegreesTrueStart1480 = getCharIndex();
            int bearingDegreesTrueStartLine1480 = getLine();
            int bearingDegreesTrueStartCharPos1480 = getCharPositionInLine();
            mNUMBER(); 
            bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1480, getCharIndex()-1);
            bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1480);
            bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1480);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            int bearingDegreesMagneticStart1498 = getCharIndex();
            int bearingDegreesMagneticStartLine1498 = getLine();
            int bearingDegreesMagneticStartCharPos1498 = getCharPositionInLine();
            mNUMBER(); 
            bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1498, getCharIndex()-1);
            bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1498);
            bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1498);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:491:4: (toWaypointID= LETTERS |toWaypointID= NUMBER )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==' '||(LA53_0 >= 'A' && LA53_0 <= 'Z')||(LA53_0 >= 'a' && LA53_0 <= 'z')) ) {
                alt53=1;
            }
            else if ( (LA53_0=='.'||(LA53_0 >= '0' && LA53_0 <= '9')) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }
            switch (alt53) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:491:5: toWaypointID= LETTERS
                    {
                    int toWaypointIDStart1515 = getCharIndex();
                    int toWaypointIDStartLine1515 = getLine();
                    int toWaypointIDStartCharPos1515 = getCharPositionInLine();
                    mLETTERS(); 
                    toWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIDStart1515, getCharIndex()-1);
                    toWaypointID.setLine(toWaypointIDStartLine1515);
                    toWaypointID.setCharPositionInLine(toWaypointIDStartCharPos1515);


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:491:28: toWaypointID= NUMBER
                    {
                    int toWaypointIDStart1521 = getCharIndex();
                    int toWaypointIDStartLine1521 = getLine();
                    int toWaypointIDStartCharPos1521 = getCharPositionInLine();
                    mNUMBER(); 
                    toWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIDStart1521, getCharIndex()-1);
                    toWaypointID.setLine(toWaypointIDStartLine1521);
                    toWaypointID.setCharPositionInLine(toWaypointIDStartCharPos1521);


                    }
                    break;

            }


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:492:4: (fromWaypointID= LETTERS |fromWaypointID= NUMBER )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==' '||(LA54_0 >= 'A' && LA54_0 <= 'Z')||(LA54_0 >= 'a' && LA54_0 <= 'z')) ) {
                alt54=1;
            }
            else if ( (LA54_0=='.'||(LA54_0 >= '0' && LA54_0 <= '9')) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }
            switch (alt54) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:492:5: fromWaypointID= LETTERS
                    {
                    int fromWaypointIDStart1532 = getCharIndex();
                    int fromWaypointIDStartLine1532 = getLine();
                    int fromWaypointIDStartCharPos1532 = getCharPositionInLine();
                    mLETTERS(); 
                    fromWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIDStart1532, getCharIndex()-1);
                    fromWaypointID.setLine(fromWaypointIDStartLine1532);
                    fromWaypointID.setCharPositionInLine(fromWaypointIDStartCharPos1532);


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:492:30: fromWaypointID= NUMBER
                    {
                    int fromWaypointIDStart1538 = getCharIndex();
                    int fromWaypointIDStartLine1538 = getLine();
                    int fromWaypointIDStartCharPos1538 = getCharPositionInLine();
                    mNUMBER(); 
                    fromWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIDStart1538, getCharIndex()-1);
                    fromWaypointID.setLine(fromWaypointIDStartLine1538);
                    fromWaypointID.setCharPositionInLine(fromWaypointIDStartCharPos1538);


                    }
                    break;

            }


            mSEP(); 


            int checksumStart1548 = getCharIndex();
            int checksumStartLine1548 = getLine();
            int checksumStartCharPos1548 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1548, getCharIndex()-1);
            checksum.setLine(checksumStartLine1548);
            checksum.setCharPositionInLine(checksumStartCharPos1548);



            	bww = new BWW(device.getText(), getText(),
            	bearingDegreesTrue != null ? (new Float(bearingDegreesTrue.getText())).floatValue() : 0.0f,
            	bearingDegreesMagnetic != null ? (new Float(bearingDegreesMagnetic.getText())).floatValue() : 0.0f,
            	toWaypointID != null ? toWaypointID.getText() : "",
            	fromWaypointID != null ? fromWaypointID.getText() : "");
            	
            	
            	handler.doIt(bww);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BWW"

    // $ANTLR start "DBT"
    public final void mDBT() throws RecognitionException {
        try {
            int _type = DBT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken depthInFeet=null;
            CommonToken depthInMeters=null;
            CommonToken depthInFathoms=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:495:5: ( '$' device= DEVICE 'DBT' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:495:7: '$' device= DEVICE 'DBT' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
            {
            match('$'); 

            int deviceStart1566 = getCharIndex();
            int deviceStartLine1566 = getLine();
            int deviceStartCharPos1566 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1566, getCharIndex()-1);
            device.setLine(deviceStartLine1566);
            device.setCharPositionInLine(deviceStartCharPos1566);


            match("DBT"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:496:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==',') ) {
                alt55=1;
            }
            else if ( (LA55_0=='.'||(LA55_0 >= '0' && LA55_0 <= '9')) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:496:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:496:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:496:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:496:10: depthInFeet= NUMBER SEP LETTERS SEP
                    {
                    int depthInFeetStart1581 = getCharIndex();
                    int depthInFeetStartLine1581 = getLine();
                    int depthInFeetStartCharPos1581 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1581, getCharIndex()-1);
                    depthInFeet.setLine(depthInFeetStartLine1581);
                    depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1581);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:497:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==',') ) {
                alt56=1;
            }
            else if ( (LA56_0=='.'||(LA56_0 >= '0' && LA56_0 <= '9')) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:497:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:497:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:497:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:497:10: depthInMeters= NUMBER SEP LETTERS SEP
                    {
                    int depthInMetersStart1600 = getCharIndex();
                    int depthInMetersStartLine1600 = getLine();
                    int depthInMetersStartCharPos1600 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1600, getCharIndex()-1);
                    depthInMeters.setLine(depthInMetersStartLine1600);
                    depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1600);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:498:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==',') ) {
                alt57=1;
            }
            else if ( (LA57_0=='.'||(LA57_0 >= '0' && LA57_0 <= '9')) ) {
                alt57=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }
            switch (alt57) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:498:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:498:9: (depthInFathoms= NUMBER SEP LETTERS )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:498:9: (depthInFathoms= NUMBER SEP LETTERS )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:498:10: depthInFathoms= NUMBER SEP LETTERS
                    {
                    int depthInFathomsStart1619 = getCharIndex();
                    int depthInFathomsStartLine1619 = getLine();
                    int depthInFathomsStartCharPos1619 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1619, getCharIndex()-1);
                    depthInFathoms.setLine(depthInFathomsStartLine1619);
                    depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1619);


                    mSEP(); 


                    mLETTERS(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:499:11: (checksum= CHECKSUM )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0=='*') ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:499:11: checksum= CHECKSUM
            	    {
            	    int checksumStart1631 = getCharIndex();
            	    int checksumStartLine1631 = getLine();
            	    int checksumStartCharPos1631 = getCharPositionInLine();
            	    mCHECKSUM(); 
            	    checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1631, getCharIndex()-1);
            	    checksum.setLine(checksumStartLine1631);
            	    checksum.setCharPositionInLine(checksumStartCharPos1631);


            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);



            	dbt = new DBT(device.getText(), getText(),
            	depthInFeet != null ? (new Float(depthInFeet.getText())).floatValue() : 0.0f,
            	depthInMeters !=null ? (new Float(depthInMeters.getText())).floatValue() : 0.0f,
            	depthInFathoms !=null ? (new Float(depthInFathoms.getText())).floatValue() : 0.0f);
            	
            	handler.doIt(dbt);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DBT"

    // $ANTLR start "DBK"
    public final void mDBK() throws RecognitionException {
        try {
            int _type = DBK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken depthInFeet=null;
            CommonToken depthInMeters=null;
            CommonToken depthInFathoms=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:511:5: ( '$' device= DEVICE 'DBK' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:511:6: '$' device= DEVICE 'DBK' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
            {
            match('$'); 

            int deviceStart1649 = getCharIndex();
            int deviceStartLine1649 = getLine();
            int deviceStartCharPos1649 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1649, getCharIndex()-1);
            device.setLine(deviceStartLine1649);
            device.setCharPositionInLine(deviceStartCharPos1649);


            match("DBK"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:512:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==',') ) {
                alt59=1;
            }
            else if ( (LA59_0=='.'||(LA59_0 >= '0' && LA59_0 <= '9')) ) {
                alt59=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;

            }
            switch (alt59) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:512:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:512:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:512:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:512:10: depthInFeet= NUMBER SEP LETTERS SEP
                    {
                    int depthInFeetStart1664 = getCharIndex();
                    int depthInFeetStartLine1664 = getLine();
                    int depthInFeetStartCharPos1664 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1664, getCharIndex()-1);
                    depthInFeet.setLine(depthInFeetStartLine1664);
                    depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1664);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:513:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==',') ) {
                alt60=1;
            }
            else if ( (LA60_0=='.'||(LA60_0 >= '0' && LA60_0 <= '9')) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;

            }
            switch (alt60) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:513:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:513:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:513:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:513:10: depthInMeters= NUMBER SEP LETTERS SEP
                    {
                    int depthInMetersStart1683 = getCharIndex();
                    int depthInMetersStartLine1683 = getLine();
                    int depthInMetersStartCharPos1683 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1683, getCharIndex()-1);
                    depthInMeters.setLine(depthInMetersStartLine1683);
                    depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1683);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:514:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==',') ) {
                alt61=1;
            }
            else if ( (LA61_0=='.'||(LA61_0 >= '0' && LA61_0 <= '9')) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:514:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:514:9: (depthInFathoms= NUMBER SEP LETTERS )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:514:9: (depthInFathoms= NUMBER SEP LETTERS )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:514:10: depthInFathoms= NUMBER SEP LETTERS
                    {
                    int depthInFathomsStart1702 = getCharIndex();
                    int depthInFathomsStartLine1702 = getLine();
                    int depthInFathomsStartCharPos1702 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1702, getCharIndex()-1);
                    depthInFathoms.setLine(depthInFathomsStartLine1702);
                    depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1702);


                    mSEP(); 


                    mLETTERS(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:515:11: (checksum= CHECKSUM )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0=='*') ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:515:11: checksum= CHECKSUM
            	    {
            	    int checksumStart1714 = getCharIndex();
            	    int checksumStartLine1714 = getLine();
            	    int checksumStartCharPos1714 = getCharPositionInLine();
            	    mCHECKSUM(); 
            	    checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1714, getCharIndex()-1);
            	    checksum.setLine(checksumStartLine1714);
            	    checksum.setCharPositionInLine(checksumStartCharPos1714);


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);



            	dbk = new DBK(device.getText(), getText(),
            	depthInFeet != null ? (new Float(depthInFeet.getText())).floatValue() : 0.0f,
            	depthInMeters !=null ? (new Float(depthInMeters.getText())).floatValue() : 0.0f,
            	depthInFathoms !=null ? (new Float(depthInFathoms.getText())).floatValue() : 0.0f);
            	
            	handler.doIt(dbk);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DBK"

    // $ANTLR start "DBS"
    public final void mDBS() throws RecognitionException {
        try {
            int _type = DBS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken depthInFeet=null;
            CommonToken depthInMeters=null;
            CommonToken depthInFathoms=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:527:5: ( '$' device= DEVICE 'DBS' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:527:6: '$' device= DEVICE 'DBS' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
            {
            match('$'); 

            int deviceStart1732 = getCharIndex();
            int deviceStartLine1732 = getLine();
            int deviceStartCharPos1732 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1732, getCharIndex()-1);
            device.setLine(deviceStartLine1732);
            device.setCharPositionInLine(deviceStartCharPos1732);


            match("DBS"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:528:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==',') ) {
                alt63=1;
            }
            else if ( (LA63_0=='.'||(LA63_0 >= '0' && LA63_0 <= '9')) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:528:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:528:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:528:9: (depthInFeet= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:528:10: depthInFeet= NUMBER SEP LETTERS SEP
                    {
                    int depthInFeetStart1747 = getCharIndex();
                    int depthInFeetStartLine1747 = getLine();
                    int depthInFeetStartCharPos1747 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1747, getCharIndex()-1);
                    depthInFeet.setLine(depthInFeetStartLine1747);
                    depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1747);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:529:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==',') ) {
                alt64=1;
            }
            else if ( (LA64_0=='.'||(LA64_0 >= '0' && LA64_0 <= '9')) ) {
                alt64=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;

            }
            switch (alt64) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:529:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:529:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:529:9: (depthInMeters= NUMBER SEP LETTERS SEP )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:529:10: depthInMeters= NUMBER SEP LETTERS SEP
                    {
                    int depthInMetersStart1766 = getCharIndex();
                    int depthInMetersStartLine1766 = getLine();
                    int depthInMetersStartCharPos1766 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1766, getCharIndex()-1);
                    depthInMeters.setLine(depthInMetersStartLine1766);
                    depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1766);


                    mSEP(); 


                    mLETTERS(); 


                    mSEP(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:530:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==',') ) {
                alt65=1;
            }
            else if ( (LA65_0=='.'||(LA65_0 >= '0' && LA65_0 <= '9')) ) {
                alt65=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:530:3: SEP
                    {
                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:530:9: (depthInFathoms= NUMBER SEP LETTERS )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:530:9: (depthInFathoms= NUMBER SEP LETTERS )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:530:10: depthInFathoms= NUMBER SEP LETTERS
                    {
                    int depthInFathomsStart1785 = getCharIndex();
                    int depthInFathomsStartLine1785 = getLine();
                    int depthInFathomsStartCharPos1785 = getCharPositionInLine();
                    mNUMBER(); 
                    depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1785, getCharIndex()-1);
                    depthInFathoms.setLine(depthInFathomsStartLine1785);
                    depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1785);


                    mSEP(); 


                    mLETTERS(); 


                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:531:11: (checksum= CHECKSUM )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0=='*') ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:531:11: checksum= CHECKSUM
            	    {
            	    int checksumStart1797 = getCharIndex();
            	    int checksumStartLine1797 = getLine();
            	    int checksumStartCharPos1797 = getCharPositionInLine();
            	    mCHECKSUM(); 
            	    checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1797, getCharIndex()-1);
            	    checksum.setLine(checksumStartLine1797);
            	    checksum.setCharPositionInLine(checksumStartCharPos1797);


            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);



            	dbs = new DBS(device.getText(), getText(),
            	depthInFeet != null ? (new Float(depthInFeet.getText())).floatValue() : 0.0f,
            	depthInMeters !=null ? (new Float(depthInMeters.getText())).floatValue() : 0.0f,
            	depthInFathoms !=null ? (new Float(depthInFathoms.getText())).floatValue() : 0.0f);
            	
            	handler.doIt(dbs);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DBS"

    // $ANTLR start "DPT"
    public final void mDPT() throws RecognitionException {
        try {
            int _type = DPT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken depth=null;
            CommonToken offset=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:543:5: ( '$' device= DEVICE 'DPT' SEP depth= NUMBER SEP (offset= NUMBER SEP |offset= NUMBER ) (checksum= CHECKSUM )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:543:6: '$' device= DEVICE 'DPT' SEP depth= NUMBER SEP (offset= NUMBER SEP |offset= NUMBER ) (checksum= CHECKSUM )*
            {
            match('$'); 

            int deviceStart1816 = getCharIndex();
            int deviceStartLine1816 = getLine();
            int deviceStartCharPos1816 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1816, getCharIndex()-1);
            device.setLine(deviceStartLine1816);
            device.setCharPositionInLine(deviceStartCharPos1816);


            match("DPT"); 



            mSEP(); 


            int depthStart1825 = getCharIndex();
            int depthStartLine1825 = getLine();
            int depthStartCharPos1825 = getCharPositionInLine();
            mNUMBER(); 
            depth = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthStart1825, getCharIndex()-1);
            depth.setLine(depthStartLine1825);
            depth.setCharPositionInLine(depthStartCharPos1825);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:545:9: (offset= NUMBER SEP |offset= NUMBER )
            int alt67=2;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:545:10: offset= NUMBER SEP
                    {
                    int offsetStart1840 = getCharIndex();
                    int offsetStartLine1840 = getLine();
                    int offsetStartCharPos1840 = getCharPositionInLine();
                    mNUMBER(); 
                    offset = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, offsetStart1840, getCharIndex()-1);
                    offset.setLine(offsetStartLine1840);
                    offset.setCharPositionInLine(offsetStartCharPos1840);


                    mSEP(); 


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:545:30: offset= NUMBER
                    {
                    int offsetStart1848 = getCharIndex();
                    int offsetStartLine1848 = getLine();
                    int offsetStartCharPos1848 = getCharPositionInLine();
                    mNUMBER(); 
                    offset = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, offsetStart1848, getCharIndex()-1);
                    offset.setLine(offsetStartLine1848);
                    offset.setCharPositionInLine(offsetStartCharPos1848);


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:546:18: (checksum= CHECKSUM )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0=='*') ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:546:18: checksum= CHECKSUM
            	    {
            	    int checksumStart1863 = getCharIndex();
            	    int checksumStartLine1863 = getLine();
            	    int checksumStartCharPos1863 = getCharPositionInLine();
            	    mCHECKSUM(); 
            	    checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1863, getCharIndex()-1);
            	    checksum.setLine(checksumStartLine1863);
            	    checksum.setCharPositionInLine(checksumStartCharPos1863);


            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);



            	dpt = new DPT(device.getText(), getText(),
            	depth != null ? (new Float(depth.getText())).floatValue() : 0.0f,
            	offset != null ? (new Float(offset.getText())).floatValue() : 0.0f);
            	
            	handler.doIt(dpt);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DPT"

    // $ANTLR start "GGA"
    public final void mGGA() throws RecognitionException {
        try {
            int _type = GGA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken gpsQualityIndicator=null;
            CommonToken numberOfSatellitesInView=null;
            CommonToken horizontalDilutionOfPrecision=null;
            CommonToken antennaAltitude=null;
            CommonToken unitsOfAntennaAltitude=null;
            CommonToken geoidAltitude=null;
            CommonToken unitsOfGeoidAltitude=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:576:5: ( '$' device= DEVICE 'GGA' SEP (utc= NUMBER )* SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( ' ' )* (gpsQualityIndicator= NUMBER )* SEP (numberOfSatellitesInView= NUMBER )* SEP (horizontalDilutionOfPrecision= NUMBER )* SEP ( SIGN )* (antennaAltitude= NUMBER )* SEP unitsOfAntennaAltitude= LETTERS SEP ( SIGN )* (geoidAltitude= NUMBER )* SEP (unitsOfGeoidAltitude= LETTERS )* ( SEP )+ ( NUMBER SEP )* ( LETTERS | NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:576:12: '$' device= DEVICE 'GGA' SEP (utc= NUMBER )* SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( ' ' )* (gpsQualityIndicator= NUMBER )* SEP (numberOfSatellitesInView= NUMBER )* SEP (horizontalDilutionOfPrecision= NUMBER )* SEP ( SIGN )* (antennaAltitude= NUMBER )* SEP unitsOfAntennaAltitude= LETTERS SEP ( SIGN )* (geoidAltitude= NUMBER )* SEP (unitsOfGeoidAltitude= LETTERS )* ( SEP )+ ( NUMBER SEP )* ( LETTERS | NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart1888 = getCharIndex();
            int deviceStartLine1888 = getLine();
            int deviceStartCharPos1888 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1888, getCharIndex()-1);
            device.setLine(deviceStartLine1888);
            device.setCharPositionInLine(deviceStartCharPos1888);


            match("GGA"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:577:17: (utc= NUMBER )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0=='.'||(LA69_0 >= '0' && LA69_0 <= '9')) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:577:17: utc= NUMBER
            	    {
            	    int utcStart1909 = getCharIndex();
            	    int utcStartLine1909 = getLine();
            	    int utcStartCharPos1909 = getCharPositionInLine();
            	    mNUMBER(); 
            	    utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart1909, getCharIndex()-1);
            	    utc.setLine(utcStartLine1909);
            	    utc.setCharPositionInLine(utcStartCharPos1909);


            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:578:22: (latitude= NUMBER )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0=='.'||(LA70_0 >= '0' && LA70_0 <= '9')) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:578:22: latitude= NUMBER
            	    {
            	    int latitudeStart1929 = getCharIndex();
            	    int latitudeStartLine1929 = getLine();
            	    int latitudeStartCharPos1929 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart1929, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine1929);
            	    latitude.setCharPositionInLine(latitudeStartCharPos1929);


            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:579:16: (ns= LETTERS )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==' '||(LA71_0 >= 'A' && LA71_0 <= 'Z')||(LA71_0 >= 'a' && LA71_0 <= 'z')) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:579:16: ns= LETTERS
            	    {
            	    int nsStart1949 = getCharIndex();
            	    int nsStartLine1949 = getLine();
            	    int nsStartCharPos1949 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1949, getCharIndex()-1);
            	    ns.setLine(nsStartLine1949);
            	    ns.setCharPositionInLine(nsStartCharPos1949);


            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:580:23: (longitude= NUMBER )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0=='.'||(LA72_0 >= '0' && LA72_0 <= '9')) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:580:23: longitude= NUMBER
            	    {
            	    int longitudeStart1969 = getCharIndex();
            	    int longitudeStartLine1969 = getLine();
            	    int longitudeStartCharPos1969 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1969, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine1969);
            	    longitude.setCharPositionInLine(longitudeStartCharPos1969);


            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:581:16: (ew= LETTERS )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==' '||(LA73_0 >= 'A' && LA73_0 <= 'Z')||(LA73_0 >= 'a' && LA73_0 <= 'z')) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:581:16: ew= LETTERS
            	    {
            	    int ewStart1989 = getCharIndex();
            	    int ewStartLine1989 = getLine();
            	    int ewStartCharPos1989 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1989, getCharIndex()-1);
            	    ew.setLine(ewStartLine1989);
            	    ew.setCharPositionInLine(ewStartCharPos1989);


            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:583:14: ( ' ' )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==' ') ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:583:14: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:584:33: (gpsQualityIndicator= NUMBER )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0=='.'||(LA75_0 >= '0' && LA75_0 <= '9')) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:584:33: gpsQualityIndicator= NUMBER
            	    {
            	    int gpsQualityIndicatorStart2039 = getCharIndex();
            	    int gpsQualityIndicatorStartLine2039 = getLine();
            	    int gpsQualityIndicatorStartCharPos2039 = getCharPositionInLine();
            	    mNUMBER(); 
            	    gpsQualityIndicator = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, gpsQualityIndicatorStart2039, getCharIndex()-1);
            	    gpsQualityIndicator.setLine(gpsQualityIndicatorStartLine2039);
            	    gpsQualityIndicator.setCharPositionInLine(gpsQualityIndicatorStartCharPos2039);


            	    }
            	    break;

            	default :
            	    break loop75;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:585:38: (numberOfSatellitesInView= NUMBER )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0=='.'||(LA76_0 >= '0' && LA76_0 <= '9')) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:585:38: numberOfSatellitesInView= NUMBER
            	    {
            	    int numberOfSatellitesInViewStart2059 = getCharIndex();
            	    int numberOfSatellitesInViewStartLine2059 = getLine();
            	    int numberOfSatellitesInViewStartCharPos2059 = getCharPositionInLine();
            	    mNUMBER(); 
            	    numberOfSatellitesInView = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, numberOfSatellitesInViewStart2059, getCharIndex()-1);
            	    numberOfSatellitesInView.setLine(numberOfSatellitesInViewStartLine2059);
            	    numberOfSatellitesInView.setCharPositionInLine(numberOfSatellitesInViewStartCharPos2059);


            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:586:43: (horizontalDilutionOfPrecision= NUMBER )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0=='.'||(LA77_0 >= '0' && LA77_0 <= '9')) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:586:43: horizontalDilutionOfPrecision= NUMBER
            	    {
            	    int horizontalDilutionOfPrecisionStart2079 = getCharIndex();
            	    int horizontalDilutionOfPrecisionStartLine2079 = getLine();
            	    int horizontalDilutionOfPrecisionStartCharPos2079 = getCharPositionInLine();
            	    mNUMBER(); 
            	    horizontalDilutionOfPrecision = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, horizontalDilutionOfPrecisionStart2079, getCharIndex()-1);
            	    horizontalDilutionOfPrecision.setLine(horizontalDilutionOfPrecisionStartLine2079);
            	    horizontalDilutionOfPrecision.setCharPositionInLine(horizontalDilutionOfPrecisionStartCharPos2079);


            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:588:14: ( SIGN )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0=='+'||LA78_0=='-') ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    break loop78;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:589:14: (antennaAltitude= NUMBER )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0=='.'||(LA79_0 >= '0' && LA79_0 <= '9')) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:589:15: antennaAltitude= NUMBER
            	    {
            	    int antennaAltitudeStart2130 = getCharIndex();
            	    int antennaAltitudeStartLine2130 = getLine();
            	    int antennaAltitudeStartCharPos2130 = getCharPositionInLine();
            	    mNUMBER(); 
            	    antennaAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, antennaAltitudeStart2130, getCharIndex()-1);
            	    antennaAltitude.setLine(antennaAltitudeStartLine2130);
            	    antennaAltitude.setCharPositionInLine(antennaAltitudeStartCharPos2130);


            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);


            mSEP(); 


            int unitsOfAntennaAltitudeStart2151 = getCharIndex();
            int unitsOfAntennaAltitudeStartLine2151 = getLine();
            int unitsOfAntennaAltitudeStartCharPos2151 = getCharPositionInLine();
            mLETTERS(); 
            unitsOfAntennaAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfAntennaAltitudeStart2151, getCharIndex()-1);
            unitsOfAntennaAltitude.setLine(unitsOfAntennaAltitudeStartLine2151);
            unitsOfAntennaAltitude.setCharPositionInLine(unitsOfAntennaAltitudeStartCharPos2151);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:591:14: ( SIGN )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0=='+'||LA80_0=='-') ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    break loop80;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:592:14: (geoidAltitude= NUMBER )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0=='.'||(LA81_0 >= '0' && LA81_0 <= '9')) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:592:15: geoidAltitude= NUMBER
            	    {
            	    int geoidAltitudeStart2188 = getCharIndex();
            	    int geoidAltitudeStartLine2188 = getLine();
            	    int geoidAltitudeStartCharPos2188 = getCharPositionInLine();
            	    mNUMBER(); 
            	    geoidAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, geoidAltitudeStart2188, getCharIndex()-1);
            	    geoidAltitude.setLine(geoidAltitudeStartLine2188);
            	    geoidAltitude.setCharPositionInLine(geoidAltitudeStartCharPos2188);


            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:593:14: (unitsOfGeoidAltitude= LETTERS )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==' '||(LA82_0 >= 'A' && LA82_0 <= 'Z')||(LA82_0 >= 'a' && LA82_0 <= 'z')) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:593:15: unitsOfGeoidAltitude= LETTERS
            	    {
            	    int unitsOfGeoidAltitudeStart2211 = getCharIndex();
            	    int unitsOfGeoidAltitudeStartLine2211 = getLine();
            	    int unitsOfGeoidAltitudeStartCharPos2211 = getCharPositionInLine();
            	    mLETTERS(); 
            	    unitsOfGeoidAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfGeoidAltitudeStart2211, getCharIndex()-1);
            	    unitsOfGeoidAltitude.setLine(unitsOfGeoidAltitudeStartLine2211);
            	    unitsOfGeoidAltitude.setCharPositionInLine(unitsOfGeoidAltitudeStartCharPos2211);


            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:593:46: ( SEP )+
            int cnt83=0;
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( (LA83_0==',') ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt83 >= 1 ) break loop83;
                        EarlyExitException eee =
                            new EarlyExitException(83, input);
                        throw eee;
                }
                cnt83++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:595:14: ( NUMBER SEP )*
            loop84:
            do {
                int alt84=2;
                alt84 = dfa84.predict(input);
                switch (alt84) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:595:16: NUMBER SEP
            	    {
            	    mNUMBER(); 


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:596:14: ( LETTERS | NUMBER )*
            loop85:
            do {
                int alt85=3;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==' '||(LA85_0 >= 'A' && LA85_0 <= 'Z')||(LA85_0 >= 'a' && LA85_0 <= 'z')) ) {
                    alt85=1;
                }
                else if ( (LA85_0=='.'||(LA85_0 >= '0' && LA85_0 <= '9')) ) {
                    alt85=2;
                }


                switch (alt85) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:596:16: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:596:26: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);


            int checksumStart2304 = getCharIndex();
            int checksumStartLine2304 = getLine();
            int checksumStartCharPos2304 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2304, getCharIndex()-1);
            checksum.setLine(checksumStartLine2304);
            checksum.setCharPositionInLine(checksumStartCharPos2304);



                  if(utc != null){
                    date = dateFactory(utc.getText());
                   }else {
                   date = dateFactory("000000");
                   }
                    uoaa = unitsOfAntennaAltitude !=null ? unitsOfAntennaAltitude.getText() :  " ";
                    ga = geoidAltitude != null ? (new Float(geoidAltitude.getText())).floatValue() : 0.0f;
                    uoga = unitsOfGeoidAltitude != null ? unitsOfGeoidAltitude.getText() : " ";
                    if(latitude != null){
                    lat = latConvert((new Float(latitude.getText())).floatValue(), ns.getText());
                       }else {
                         lat = 0.0f;
                       }
                    if(longitude != null){
                        lon = lonConvert((new Float(longitude.getText())).floatValue(), ew.getText());
                    } else {
                    lon = 0.0f;
                    }
                    gga = new GGA(device.getText(), getText(),
                    		     date,
                                         lat, 
                                         lon,
                                         gpsQualityIndicator != null ? (new Integer(gpsQualityIndicator.getText())).intValue() : 0,
                                         numberOfSatellitesInView != null ? (new Integer(numberOfSatellitesInView.getText())).intValue() : 0,
                                         horizontalDilutionOfPrecision != null ? (new Float(horizontalDilutionOfPrecision.getText())).floatValue() : 0.0f,
                                         antennaAltitude != null ? (new Float(antennaAltitude.getText())).floatValue() : 0.0f,
                                         uoaa,
                                         ga,
                                         uoga);
                         
                   handler.doIt(gga);
                  

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GGA"

    // $ANTLR start "GLL"
    public final void mGLL() throws RecognitionException {
        try {
            int _type = GLL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken utc=null;
            CommonToken status=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:634:5: ( '$' device= DEVICE 'GLL' SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP (utc= NUMBER )* SEP status= LETTERS ( SEP )* ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:634:7: '$' device= DEVICE 'GLL' SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP (utc= NUMBER )* SEP status= LETTERS ( SEP )* ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart2337 = getCharIndex();
            int deviceStartLine2337 = getLine();
            int deviceStartCharPos2337 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart2337, getCharIndex()-1);
            device.setLine(deviceStartLine2337);
            device.setCharPositionInLine(deviceStartCharPos2337);


            match("GLL"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:635:12: (latitude= NUMBER )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0=='.'||(LA86_0 >= '0' && LA86_0 <= '9')) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:635:12: latitude= NUMBER
            	    {
            	    int latitudeStart2348 = getCharIndex();
            	    int latitudeStartLine2348 = getLine();
            	    int latitudeStartCharPos2348 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart2348, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine2348);
            	    latitude.setCharPositionInLine(latitudeStartCharPos2348);


            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:636:17: (ns= LETTERS )*
            loop87:
            do {
                int alt87=2;
                int LA87_0 = input.LA(1);

                if ( (LA87_0==' '||(LA87_0 >= 'A' && LA87_0 <= 'Z')||(LA87_0 >= 'a' && LA87_0 <= 'z')) ) {
                    alt87=1;
                }


                switch (alt87) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:636:17: ns= LETTERS
            	    {
            	    int nsStart2369 = getCharIndex();
            	    int nsStartLine2369 = getLine();
            	    int nsStartCharPos2369 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart2369, getCharIndex()-1);
            	    ns.setLine(nsStartLine2369);
            	    ns.setCharPositionInLine(nsStartCharPos2369);


            	    }
            	    break;

            	default :
            	    break loop87;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:637:24: (longitude= NUMBER )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0=='.'||(LA88_0 >= '0' && LA88_0 <= '9')) ) {
                    alt88=1;
                }


                switch (alt88) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:637:24: longitude= NUMBER
            	    {
            	    int longitudeStart2390 = getCharIndex();
            	    int longitudeStartLine2390 = getLine();
            	    int longitudeStartCharPos2390 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart2390, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine2390);
            	    longitude.setCharPositionInLine(longitudeStartCharPos2390);


            	    }
            	    break;

            	default :
            	    break loop88;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:638:17: (ew= LETTERS )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==' '||(LA89_0 >= 'A' && LA89_0 <= 'Z')||(LA89_0 >= 'a' && LA89_0 <= 'z')) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:638:17: ew= LETTERS
            	    {
            	    int ewStart2411 = getCharIndex();
            	    int ewStartLine2411 = getLine();
            	    int ewStartCharPos2411 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart2411, getCharIndex()-1);
            	    ew.setLine(ewStartLine2411);
            	    ew.setCharPositionInLine(ewStartCharPos2411);


            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:639:18: (utc= NUMBER )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0=='.'||(LA90_0 >= '0' && LA90_0 <= '9')) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:639:18: utc= NUMBER
            	    {
            	    int utcStart2432 = getCharIndex();
            	    int utcStartLine2432 = getLine();
            	    int utcStartCharPos2432 = getCharPositionInLine();
            	    mNUMBER(); 
            	    utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart2432, getCharIndex()-1);
            	    utc.setLine(utcStartLine2432);
            	    utc.setCharPositionInLine(utcStartCharPos2432);


            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);


            mSEP(); 


            int statusStart2453 = getCharIndex();
            int statusStartLine2453 = getLine();
            int statusStartCharPos2453 = getCharPositionInLine();
            mLETTERS(); 
            status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart2453, getCharIndex()-1);
            status.setLine(statusStartLine2453);
            status.setCharPositionInLine(statusStartCharPos2453);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:640:30: ( SEP )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==',') ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    break loop91;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:641:15: ( LETTERS )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==' '||(LA92_0 >= 'A' && LA92_0 <= 'Z')||(LA92_0 >= 'a' && LA92_0 <= 'z')) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:641:15: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);


            int checksumStart2492 = getCharIndex();
            int checksumStartLine2492 = getLine();
            int checksumStartCharPos2492 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2492, getCharIndex()-1);
            checksum.setLine(checksumStartLine2492);
            checksum.setCharPositionInLine(checksumStartCharPos2492);



            	//String origin, float latitude, float longitude, Calendar utc, String status
            	 if(utc != null)
            	   date = dateFactory(utc.getText());
            	 else
            	   date = dateFactory("000000");
            	 lat = latConvert(latitude != null ? (new Float(latitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
                     lon = lonConvert(longitude != null ? (new Float(longitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
            	 gll = new GLL(device.getText(), getText(),
            	 lat, lon, 
            	 date, 
            	 status.getText());
            	 
            	 handler.doIt(gll); 
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GLL"

    // $ANTLR start "GSA"
    public final void mGSA() throws RecognitionException {
        try {
            int _type = GSA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken autoOrManualSelection=null;
            CommonToken dimensionFix=null;
            CommonToken PRNOfSatellitesUsed1=null;
            CommonToken PRNOfSatellitesUsed2=null;
            CommonToken PRNOfSatellitesUsed3=null;
            CommonToken PRNOfSatellitesUsed4=null;
            CommonToken PRNOfSatellitesUsed5=null;
            CommonToken PRNOfSatellitesUsed6=null;
            CommonToken PRNOfSatellitesUsed7=null;
            CommonToken PRNOfSatellitesUsed8=null;
            CommonToken PRNOfSatellitesUsed9=null;
            CommonToken PRNOfSatellitesUsed10=null;
            CommonToken PRNOfSatellitesUsed11=null;
            CommonToken PRNOfSatellitesUsed12=null;
            CommonToken PDOP=null;
            CommonToken HDOP=null;
            CommonToken VDOP=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:661:6: ( '$' device= DEVICE 'GSA' SEP autoOrManualSelection= LETTERS SEP ( ' ' )* dimensionFix= NUMBER SEP ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )* SEP ( ( ' ' )* PDOP= NUMBER )* SEP ( ( ' ' )* HDOP= NUMBER )* SEP ( ( ' ' )* VDOP= NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:661:11: '$' device= DEVICE 'GSA' SEP autoOrManualSelection= LETTERS SEP ( ' ' )* dimensionFix= NUMBER SEP ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )* SEP ( ( ' ' )* PDOP= NUMBER )* SEP ( ( ' ' )* HDOP= NUMBER )* SEP ( ( ' ' )* VDOP= NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart2527 = getCharIndex();
            int deviceStartLine2527 = getLine();
            int deviceStartCharPos2527 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart2527, getCharIndex()-1);
            device.setLine(deviceStartLine2527);
            device.setCharPositionInLine(deviceStartCharPos2527);


            match("GSA"); 



            mSEP(); 


            int autoOrManualSelectionStart2550 = getCharIndex();
            int autoOrManualSelectionStartLine2550 = getLine();
            int autoOrManualSelectionStartCharPos2550 = getCharPositionInLine();
            mLETTERS(); 
            autoOrManualSelection = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, autoOrManualSelectionStart2550, getCharIndex()-1);
            autoOrManualSelection.setLine(autoOrManualSelectionStartLine2550);
            autoOrManualSelection.setCharPositionInLine(autoOrManualSelectionStartCharPos2550);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:663:14: ( ' ' )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==' ') ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:663:14: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop93;
                }
            } while (true);


            int dimensionFixStart2574 = getCharIndex();
            int dimensionFixStartLine2574 = getLine();
            int dimensionFixStartCharPos2574 = getCharPositionInLine();
            mNUMBER(); 
            dimensionFix = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dimensionFixStart2574, getCharIndex()-1);
            dimensionFix.setLine(dimensionFixStartLine2574);
            dimensionFix.setCharPositionInLine(dimensionFixStartCharPos2574);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:664:14: ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==' '||LA95_0=='.'||(LA95_0 >= '0' && LA95_0 <= '9')) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:664:15: ( ' ' )* PRNOfSatellitesUsed1= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:664:15: ( ' ' )*
            	    loop94:
            	    do {
            	        int alt94=2;
            	        int LA94_0 = input.LA(1);

            	        if ( (LA94_0==' ') ) {
            	            alt94=1;
            	        }


            	        switch (alt94) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:664:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop94;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed1Start2599 = getCharIndex();
            	    int PRNOfSatellitesUsed1StartLine2599 = getLine();
            	    int PRNOfSatellitesUsed1StartCharPos2599 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed1Start2599, getCharIndex()-1);
            	    PRNOfSatellitesUsed1.setLine(PRNOfSatellitesUsed1StartLine2599);
            	    PRNOfSatellitesUsed1.setCharPositionInLine(PRNOfSatellitesUsed1StartCharPos2599);


            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:665:14: ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==' '||LA97_0=='.'||(LA97_0 >= '0' && LA97_0 <= '9')) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:665:15: ( ' ' )* PRNOfSatellitesUsed2= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:665:15: ( ' ' )*
            	    loop96:
            	    do {
            	        int alt96=2;
            	        int LA96_0 = input.LA(1);

            	        if ( (LA96_0==' ') ) {
            	            alt96=1;
            	        }


            	        switch (alt96) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:665:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop96;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed2Start2626 = getCharIndex();
            	    int PRNOfSatellitesUsed2StartLine2626 = getLine();
            	    int PRNOfSatellitesUsed2StartCharPos2626 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed2Start2626, getCharIndex()-1);
            	    PRNOfSatellitesUsed2.setLine(PRNOfSatellitesUsed2StartLine2626);
            	    PRNOfSatellitesUsed2.setCharPositionInLine(PRNOfSatellitesUsed2StartCharPos2626);


            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:666:14: ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==' '||LA99_0=='.'||(LA99_0 >= '0' && LA99_0 <= '9')) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:666:15: ( ' ' )* PRNOfSatellitesUsed3= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:666:15: ( ' ' )*
            	    loop98:
            	    do {
            	        int alt98=2;
            	        int LA98_0 = input.LA(1);

            	        if ( (LA98_0==' ') ) {
            	            alt98=1;
            	        }


            	        switch (alt98) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:666:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop98;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed3Start2653 = getCharIndex();
            	    int PRNOfSatellitesUsed3StartLine2653 = getLine();
            	    int PRNOfSatellitesUsed3StartCharPos2653 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed3Start2653, getCharIndex()-1);
            	    PRNOfSatellitesUsed3.setLine(PRNOfSatellitesUsed3StartLine2653);
            	    PRNOfSatellitesUsed3.setCharPositionInLine(PRNOfSatellitesUsed3StartCharPos2653);


            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:667:14: ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )*
            loop101:
            do {
                int alt101=2;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==' '||LA101_0=='.'||(LA101_0 >= '0' && LA101_0 <= '9')) ) {
                    alt101=1;
                }


                switch (alt101) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:667:15: ( ' ' )* PRNOfSatellitesUsed4= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:667:15: ( ' ' )*
            	    loop100:
            	    do {
            	        int alt100=2;
            	        int LA100_0 = input.LA(1);

            	        if ( (LA100_0==' ') ) {
            	            alt100=1;
            	        }


            	        switch (alt100) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:667:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop100;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed4Start2680 = getCharIndex();
            	    int PRNOfSatellitesUsed4StartLine2680 = getLine();
            	    int PRNOfSatellitesUsed4StartCharPos2680 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed4 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed4Start2680, getCharIndex()-1);
            	    PRNOfSatellitesUsed4.setLine(PRNOfSatellitesUsed4StartLine2680);
            	    PRNOfSatellitesUsed4.setCharPositionInLine(PRNOfSatellitesUsed4StartCharPos2680);


            	    }
            	    break;

            	default :
            	    break loop101;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:668:14: ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==' '||LA103_0=='.'||(LA103_0 >= '0' && LA103_0 <= '9')) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:668:15: ( ' ' )* PRNOfSatellitesUsed5= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:668:15: ( ' ' )*
            	    loop102:
            	    do {
            	        int alt102=2;
            	        int LA102_0 = input.LA(1);

            	        if ( (LA102_0==' ') ) {
            	            alt102=1;
            	        }


            	        switch (alt102) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:668:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop102;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed5Start2707 = getCharIndex();
            	    int PRNOfSatellitesUsed5StartLine2707 = getLine();
            	    int PRNOfSatellitesUsed5StartCharPos2707 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed5 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed5Start2707, getCharIndex()-1);
            	    PRNOfSatellitesUsed5.setLine(PRNOfSatellitesUsed5StartLine2707);
            	    PRNOfSatellitesUsed5.setCharPositionInLine(PRNOfSatellitesUsed5StartCharPos2707);


            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:669:14: ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )*
            loop105:
            do {
                int alt105=2;
                int LA105_0 = input.LA(1);

                if ( (LA105_0==' '||LA105_0=='.'||(LA105_0 >= '0' && LA105_0 <= '9')) ) {
                    alt105=1;
                }


                switch (alt105) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:669:15: ( ' ' )* PRNOfSatellitesUsed6= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:669:15: ( ' ' )*
            	    loop104:
            	    do {
            	        int alt104=2;
            	        int LA104_0 = input.LA(1);

            	        if ( (LA104_0==' ') ) {
            	            alt104=1;
            	        }


            	        switch (alt104) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:669:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop104;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed6Start2734 = getCharIndex();
            	    int PRNOfSatellitesUsed6StartLine2734 = getLine();
            	    int PRNOfSatellitesUsed6StartCharPos2734 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed6 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed6Start2734, getCharIndex()-1);
            	    PRNOfSatellitesUsed6.setLine(PRNOfSatellitesUsed6StartLine2734);
            	    PRNOfSatellitesUsed6.setCharPositionInLine(PRNOfSatellitesUsed6StartCharPos2734);


            	    }
            	    break;

            	default :
            	    break loop105;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:670:14: ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )*
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==' '||LA107_0=='.'||(LA107_0 >= '0' && LA107_0 <= '9')) ) {
                    alt107=1;
                }


                switch (alt107) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:670:15: ( ' ' )* PRNOfSatellitesUsed7= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:670:15: ( ' ' )*
            	    loop106:
            	    do {
            	        int alt106=2;
            	        int LA106_0 = input.LA(1);

            	        if ( (LA106_0==' ') ) {
            	            alt106=1;
            	        }


            	        switch (alt106) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:670:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop106;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed7Start2761 = getCharIndex();
            	    int PRNOfSatellitesUsed7StartLine2761 = getLine();
            	    int PRNOfSatellitesUsed7StartCharPos2761 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed7 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed7Start2761, getCharIndex()-1);
            	    PRNOfSatellitesUsed7.setLine(PRNOfSatellitesUsed7StartLine2761);
            	    PRNOfSatellitesUsed7.setCharPositionInLine(PRNOfSatellitesUsed7StartCharPos2761);


            	    }
            	    break;

            	default :
            	    break loop107;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:671:14: ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==' '||LA109_0=='.'||(LA109_0 >= '0' && LA109_0 <= '9')) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:671:15: ( ' ' )* PRNOfSatellitesUsed8= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:671:15: ( ' ' )*
            	    loop108:
            	    do {
            	        int alt108=2;
            	        int LA108_0 = input.LA(1);

            	        if ( (LA108_0==' ') ) {
            	            alt108=1;
            	        }


            	        switch (alt108) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:671:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop108;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed8Start2788 = getCharIndex();
            	    int PRNOfSatellitesUsed8StartLine2788 = getLine();
            	    int PRNOfSatellitesUsed8StartCharPos2788 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed8 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed8Start2788, getCharIndex()-1);
            	    PRNOfSatellitesUsed8.setLine(PRNOfSatellitesUsed8StartLine2788);
            	    PRNOfSatellitesUsed8.setCharPositionInLine(PRNOfSatellitesUsed8StartCharPos2788);


            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:672:14: ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==' '||LA111_0=='.'||(LA111_0 >= '0' && LA111_0 <= '9')) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:672:15: ( ' ' )* PRNOfSatellitesUsed9= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:672:15: ( ' ' )*
            	    loop110:
            	    do {
            	        int alt110=2;
            	        int LA110_0 = input.LA(1);

            	        if ( (LA110_0==' ') ) {
            	            alt110=1;
            	        }


            	        switch (alt110) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:672:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop110;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed9Start2815 = getCharIndex();
            	    int PRNOfSatellitesUsed9StartLine2815 = getLine();
            	    int PRNOfSatellitesUsed9StartCharPos2815 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed9 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed9Start2815, getCharIndex()-1);
            	    PRNOfSatellitesUsed9.setLine(PRNOfSatellitesUsed9StartLine2815);
            	    PRNOfSatellitesUsed9.setCharPositionInLine(PRNOfSatellitesUsed9StartCharPos2815);


            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:673:14: ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )*
            loop113:
            do {
                int alt113=2;
                int LA113_0 = input.LA(1);

                if ( (LA113_0==' '||LA113_0=='.'||(LA113_0 >= '0' && LA113_0 <= '9')) ) {
                    alt113=1;
                }


                switch (alt113) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:673:15: ( ' ' )* PRNOfSatellitesUsed10= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:673:15: ( ' ' )*
            	    loop112:
            	    do {
            	        int alt112=2;
            	        int LA112_0 = input.LA(1);

            	        if ( (LA112_0==' ') ) {
            	            alt112=1;
            	        }


            	        switch (alt112) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:673:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop112;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed10Start2842 = getCharIndex();
            	    int PRNOfSatellitesUsed10StartLine2842 = getLine();
            	    int PRNOfSatellitesUsed10StartCharPos2842 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed10 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed10Start2842, getCharIndex()-1);
            	    PRNOfSatellitesUsed10.setLine(PRNOfSatellitesUsed10StartLine2842);
            	    PRNOfSatellitesUsed10.setCharPositionInLine(PRNOfSatellitesUsed10StartCharPos2842);


            	    }
            	    break;

            	default :
            	    break loop113;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:674:14: ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )*
            loop115:
            do {
                int alt115=2;
                int LA115_0 = input.LA(1);

                if ( (LA115_0==' '||LA115_0=='.'||(LA115_0 >= '0' && LA115_0 <= '9')) ) {
                    alt115=1;
                }


                switch (alt115) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:674:15: ( ' ' )* PRNOfSatellitesUsed11= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:674:15: ( ' ' )*
            	    loop114:
            	    do {
            	        int alt114=2;
            	        int LA114_0 = input.LA(1);

            	        if ( (LA114_0==' ') ) {
            	            alt114=1;
            	        }


            	        switch (alt114) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:674:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop114;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed11Start2869 = getCharIndex();
            	    int PRNOfSatellitesUsed11StartLine2869 = getLine();
            	    int PRNOfSatellitesUsed11StartCharPos2869 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed11 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed11Start2869, getCharIndex()-1);
            	    PRNOfSatellitesUsed11.setLine(PRNOfSatellitesUsed11StartLine2869);
            	    PRNOfSatellitesUsed11.setCharPositionInLine(PRNOfSatellitesUsed11StartCharPos2869);


            	    }
            	    break;

            	default :
            	    break loop115;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:675:14: ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )*
            loop117:
            do {
                int alt117=2;
                int LA117_0 = input.LA(1);

                if ( (LA117_0==' '||LA117_0=='.'||(LA117_0 >= '0' && LA117_0 <= '9')) ) {
                    alt117=1;
                }


                switch (alt117) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:675:15: ( ' ' )* PRNOfSatellitesUsed12= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:675:15: ( ' ' )*
            	    loop116:
            	    do {
            	        int alt116=2;
            	        int LA116_0 = input.LA(1);

            	        if ( (LA116_0==' ') ) {
            	            alt116=1;
            	        }


            	        switch (alt116) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:675:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop116;
            	        }
            	    } while (true);


            	    int PRNOfSatellitesUsed12Start2896 = getCharIndex();
            	    int PRNOfSatellitesUsed12StartLine2896 = getLine();
            	    int PRNOfSatellitesUsed12StartCharPos2896 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PRNOfSatellitesUsed12 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed12Start2896, getCharIndex()-1);
            	    PRNOfSatellitesUsed12.setLine(PRNOfSatellitesUsed12StartLine2896);
            	    PRNOfSatellitesUsed12.setCharPositionInLine(PRNOfSatellitesUsed12StartCharPos2896);


            	    }
            	    break;

            	default :
            	    break loop117;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:676:14: ( ( ' ' )* PDOP= NUMBER )*
            loop119:
            do {
                int alt119=2;
                int LA119_0 = input.LA(1);

                if ( (LA119_0==' '||LA119_0=='.'||(LA119_0 >= '0' && LA119_0 <= '9')) ) {
                    alt119=1;
                }


                switch (alt119) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:676:15: ( ' ' )* PDOP= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:676:15: ( ' ' )*
            	    loop118:
            	    do {
            	        int alt118=2;
            	        int LA118_0 = input.LA(1);

            	        if ( (LA118_0==' ') ) {
            	            alt118=1;
            	        }


            	        switch (alt118) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:676:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop118;
            	        }
            	    } while (true);


            	    int PDOPStart2923 = getCharIndex();
            	    int PDOPStartLine2923 = getLine();
            	    int PDOPStartCharPos2923 = getCharPositionInLine();
            	    mNUMBER(); 
            	    PDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PDOPStart2923, getCharIndex()-1);
            	    PDOP.setLine(PDOPStartLine2923);
            	    PDOP.setCharPositionInLine(PDOPStartCharPos2923);


            	    }
            	    break;

            	default :
            	    break loop119;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:677:14: ( ( ' ' )* HDOP= NUMBER )*
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==' '||LA121_0=='.'||(LA121_0 >= '0' && LA121_0 <= '9')) ) {
                    alt121=1;
                }


                switch (alt121) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:677:15: ( ' ' )* HDOP= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:677:15: ( ' ' )*
            	    loop120:
            	    do {
            	        int alt120=2;
            	        int LA120_0 = input.LA(1);

            	        if ( (LA120_0==' ') ) {
            	            alt120=1;
            	        }


            	        switch (alt120) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:677:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop120;
            	        }
            	    } while (true);


            	    int HDOPStart2950 = getCharIndex();
            	    int HDOPStartLine2950 = getLine();
            	    int HDOPStartCharPos2950 = getCharPositionInLine();
            	    mNUMBER(); 
            	    HDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, HDOPStart2950, getCharIndex()-1);
            	    HDOP.setLine(HDOPStartLine2950);
            	    HDOP.setCharPositionInLine(HDOPStartCharPos2950);


            	    }
            	    break;

            	default :
            	    break loop121;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:678:14: ( ( ' ' )* VDOP= NUMBER )*
            loop123:
            do {
                int alt123=2;
                int LA123_0 = input.LA(1);

                if ( (LA123_0==' '||LA123_0=='.'||(LA123_0 >= '0' && LA123_0 <= '9')) ) {
                    alt123=1;
                }


                switch (alt123) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:678:15: ( ' ' )* VDOP= NUMBER
            	    {
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:678:15: ( ' ' )*
            	    loop122:
            	    do {
            	        int alt122=2;
            	        int LA122_0 = input.LA(1);

            	        if ( (LA122_0==' ') ) {
            	            alt122=1;
            	        }


            	        switch (alt122) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:678:15: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop122;
            	        }
            	    } while (true);


            	    int VDOPStart2977 = getCharIndex();
            	    int VDOPStartLine2977 = getLine();
            	    int VDOPStartCharPos2977 = getCharPositionInLine();
            	    mNUMBER(); 
            	    VDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, VDOPStart2977, getCharIndex()-1);
            	    VDOP.setLine(VDOPStartLine2977);
            	    VDOP.setCharPositionInLine(VDOPStartCharPos2977);


            	    }
            	    break;

            	default :
            	    break loop123;
                }
            } while (true);


            int checksumStart2996 = getCharIndex();
            int checksumStartLine2996 = getLine();
            int checksumStartCharPos2996 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2996, getCharIndex()-1);
            checksum.setLine(checksumStartLine2996);
            checksum.setCharPositionInLine(checksumStartCharPos2996);



                    gsa = new GSA (device.getText(), getText(),
                    		     autoOrManualSelection.getText(), 
                                         (new Integer(dimensionFix.getText())).intValue(),
                                         PDOP != null ? (new Float(PDOP.getText())).floatValue() : 0.0f,
            			     HDOP != null ? (new Float(HDOP.getText())).floatValue() : 0.0f,
            			     VDOP != null ? (new Float(VDOP.getText())).floatValue() : 0.0f);
            			     
                    if(PRNOfSatellitesUsed1 != null) { 
                       sId =PRNOfSatellitesUsed1.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                    if(PRNOfSatellitesUsed2 != null) { 
                       sId =PRNOfSatellitesUsed2.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed3 != null) { 
                       sId =PRNOfSatellitesUsed3.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed4 != null) { 
                       sId =PRNOfSatellitesUsed4.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed5 != null) { 
                       sId =PRNOfSatellitesUsed5.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed6 != null) { 
                       sId =PRNOfSatellitesUsed6.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed7 != null) { 
                       sId =PRNOfSatellitesUsed7.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed8 != null) { 
                       sId =PRNOfSatellitesUsed8.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed9 != null) { 
                       sId =PRNOfSatellitesUsed9.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed10 != null) { 
                       sId =PRNOfSatellitesUsed10.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed11 != null) { 
                       sId =PRNOfSatellitesUsed11.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                     if(PRNOfSatellitesUsed12 != null) { 
                       sId =PRNOfSatellitesUsed12.getText();  
                       if(!sId.equals("00"))
                          gsa.addPRNOfSatelliteUsed(new Integer(sId).intValue());
                     }
                    
                   handler.doIt(gsa);
                    

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GSA"

    // $ANTLR start "GSV"
    public final void mGSV() throws RecognitionException {
        try {
            int _type = GSV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:774:5: ( '$' device= DEVICE 'GSV' ( NUMBER | SEP )+ checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:774:10: '$' device= DEVICE 'GSV' ( NUMBER | SEP )+ checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3026 = getCharIndex();
            int deviceStartLine3026 = getLine();
            int deviceStartCharPos3026 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3026, getCharIndex()-1);
            device.setLine(deviceStartLine3026);
            device.setCharPositionInLine(deviceStartCharPos3026);


            match("GSV"); 



            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:775:3: ( NUMBER | SEP )+
            int cnt124=0;
            loop124:
            do {
                int alt124=3;
                int LA124_0 = input.LA(1);

                if ( (LA124_0=='.'||(LA124_0 >= '0' && LA124_0 <= '9')) ) {
                    alt124=1;
                }
                else if ( (LA124_0==',') ) {
                    alt124=2;
                }


                switch (alt124) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:775:4: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:775:13: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    if ( cnt124 >= 1 ) break loop124;
                        EarlyExitException eee =
                            new EarlyExitException(124, input);
                        throw eee;
                }
                cnt124++;
            } while (true);


            int checksumStart3047 = getCharIndex();
            int checksumStartLine3047 = getLine();
            int checksumStartCharPos3047 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3047, getCharIndex()-1);
            checksum.setLine(checksumStartLine3047);
            checksum.setCharPositionInLine(checksumStartCharPos3047);



                        String sentence = getText();
                        String [] tab = sentence.split("\\*");
                        sentence = tab[0];
                     
                       
                        String[] ss = sentence.split(",");
                             
                        String numberOfSentences = ss[1];
                        String sentenceNumber = ss[2];
                        String numberOfSatellitesInView = ss[3];
                        
                        gsv = new GSV (device.getText(), getText(),
                            numberOfSentences != null ? new Integer(numberOfSentences).intValue() : 0,
                            sentenceNumber != null ? new Integer(sentenceNumber).intValue() : 0,
                            numberOfSatellitesInView != null ? new Integer(numberOfSatellitesInView).intValue() :0
                        );
                        
                        String satellitePRNNumber = null;
                        String elevationDegrees = null;
                        String azimuthDegrees = null;
                        String snr = null;
                       
                        GPSSatellite s;
                        int l = ss.length / 4;  
                        l *= 4;
                        for(int i = 4; i < l ; i+=4){
                           satellitePRNNumber = ss[i];
                           elevationDegrees = ss[i+1];
                           azimuthDegrees = ss[i+2];
                           snr = ss[i+3];
                           if(snr != null && !snr.equals("") && !snr.equals(" ")){
                              s = new GPSSatellite(
                              satellitePRNNumber != null ? new Integer(satellitePRNNumber).intValue() : 0,
                              elevationDegrees != null ?  new Integer(elevationDegrees).intValue() : 0,
                              azimuthDegrees != null ? new Integer(azimuthDegrees).intValue() : 0,
                              new Integer(snr).intValue()
                              );
                            gsv.addSatellite(s);
                           }
                        }
                       handler.doIt(gsv);
                      

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GSV"

    // $ANTLR start "HDG"
    public final void mHDG() throws RecognitionException {
        try {
            int _type = HDG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken heading=null;
            CommonToken dev=null;
            CommonToken we=null;
            CommonToken var=null;
            CommonToken ew=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:810:7: ( '$' device= DEVICE 'HDG' SEP heading= NUMBER ( SEP )+ (dev= NUMBER SEP we= LETTERS SEP )* var= NUMBER SEP ew= LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:810:9: '$' device= DEVICE 'HDG' SEP heading= NUMBER ( SEP )+ (dev= NUMBER SEP we= LETTERS SEP )* var= NUMBER SEP ew= LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3087 = getCharIndex();
            int deviceStartLine3087 = getLine();
            int deviceStartCharPos3087 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3087, getCharIndex()-1);
            device.setLine(deviceStartLine3087);
            device.setCharPositionInLine(deviceStartCharPos3087);


            match("HDG"); 



            mSEP(); 


            int headingStart3101 = getCharIndex();
            int headingStartLine3101 = getLine();
            int headingStartCharPos3101 = getCharPositionInLine();
            mNUMBER(); 
            heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3101, getCharIndex()-1);
            heading.setLine(headingStartLine3101);
            heading.setCharPositionInLine(headingStartCharPos3101);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:811:22: ( SEP )+
            int cnt125=0;
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==',') ) {
                    alt125=1;
                }


                switch (alt125) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    if ( cnt125 >= 1 ) break loop125;
                        EarlyExitException eee =
                            new EarlyExitException(125, input);
                        throw eee;
                }
                cnt125++;
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:812:5: (dev= NUMBER SEP we= LETTERS SEP )*
            loop126:
            do {
                int alt126=2;
                alt126 = dfa126.predict(input);
                switch (alt126) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:812:6: dev= NUMBER SEP we= LETTERS SEP
            	    {
            	    int devStart3115 = getCharIndex();
            	    int devStartLine3115 = getLine();
            	    int devStartCharPos3115 = getCharPositionInLine();
            	    mNUMBER(); 
            	    dev = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, devStart3115, getCharIndex()-1);
            	    dev.setLine(devStartLine3115);
            	    dev.setCharPositionInLine(devStartCharPos3115);


            	    mSEP(); 


            	    int weStart3123 = getCharIndex();
            	    int weStartLine3123 = getLine();
            	    int weStartCharPos3123 = getCharPositionInLine();
            	    mLETTERS(); 
            	    we = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, weStart3123, getCharIndex()-1);
            	    we.setLine(weStartLine3123);
            	    we.setCharPositionInLine(weStartCharPos3123);


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop126;
                }
            } while (true);


            int varStart3137 = getCharIndex();
            int varStartLine3137 = getLine();
            int varStartCharPos3137 = getCharPositionInLine();
            mNUMBER(); 
            var = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, varStart3137, getCharIndex()-1);
            var.setLine(varStartLine3137);
            var.setCharPositionInLine(varStartCharPos3137);


            mSEP(); 


            int ewStart3149 = getCharIndex();
            int ewStartLine3149 = getLine();
            int ewStartCharPos3149 = getCharPositionInLine();
            mLETTERS(); 
            ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart3149, getCharIndex()-1);
            ew.setLine(ewStartLine3149);
            ew.setCharPositionInLine(ewStartCharPos3149);


            int checksumStart3157 = getCharIndex();
            int checksumStartLine3157 = getLine();
            int checksumStartCharPos3157 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3157, getCharIndex()-1);
            checksum.setLine(checksumStartLine3157);
            checksum.setCharPositionInLine(checksumStartCharPos3157);



              deviation = ewConvert(dev != null ? (new Float(dev.getText())).floatValue() : 0.0f, we != null ? we.getText() : "");
              variation = ewConvert(var != null ? (new Float(var.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
              hdg = new HDG(device.getText(), getText(),
             		 heading != null ?  new Float(heading.getText()).floatValue(): 0.0f,
             		 deviation, variation);
              	handler.doIt(hdg);
              

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HDG"

    // $ANTLR start "HDM"
    public final void mHDM() throws RecognitionException {
        try {
            int _type = HDM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken heading=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:826:8: ( '$' device= DEVICE 'HDM' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:826:11: '$' device= DEVICE 'HDM' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3181 = getCharIndex();
            int deviceStartLine3181 = getLine();
            int deviceStartCharPos3181 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3181, getCharIndex()-1);
            device.setLine(deviceStartLine3181);
            device.setCharPositionInLine(deviceStartCharPos3181);


            match("HDM"); 



            mSEP(); 


            int headingStart3205 = getCharIndex();
            int headingStartLine3205 = getLine();
            int headingStartCharPos3205 = getCharPositionInLine();
            mNUMBER(); 
            heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3205, getCharIndex()-1);
            heading.setLine(headingStartLine3205);
            heading.setCharPositionInLine(headingStartCharPos3205);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:828:15: ( LETTERS )*
            loop127:
            do {
                int alt127=2;
                int LA127_0 = input.LA(1);

                if ( (LA127_0==' '||(LA127_0 >= 'A' && LA127_0 <= 'Z')||(LA127_0 >= 'a' && LA127_0 <= 'z')) ) {
                    alt127=1;
                }


                switch (alt127) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:828:17: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop127;
                }
            } while (true);


            int checksumStart3242 = getCharIndex();
            int checksumStartLine3242 = getLine();
            int checksumStartCharPos3242 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3242, getCharIndex()-1);
            checksum.setLine(checksumStartLine3242);
            checksum.setCharPositionInLine(checksumStartCharPos3242);



              hdm = new HDM(device.getText(), getText(),
             		 heading != null ?  new Float(heading.getText()).floatValue(): 0.0f);
             		 
                		handler.doIt(hdm);
              

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HDM"

    // $ANTLR start "HDT"
    public final void mHDT() throws RecognitionException {
        try {
            int _type = HDT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken heading=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:842:7: ( '$' device= DEVICE 'HDT' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:842:8: '$' device= DEVICE 'HDT' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3263 = getCharIndex();
            int deviceStartLine3263 = getLine();
            int deviceStartCharPos3263 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3263, getCharIndex()-1);
            device.setLine(deviceStartLine3263);
            device.setCharPositionInLine(deviceStartCharPos3263);


            match("HDT"); 



            mSEP(); 


            int headingStart3287 = getCharIndex();
            int headingStartLine3287 = getLine();
            int headingStartCharPos3287 = getCharPositionInLine();
            mNUMBER(); 
            heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3287, getCharIndex()-1);
            heading.setLine(headingStartLine3287);
            heading.setCharPositionInLine(headingStartCharPos3287);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:844:15: ( LETTERS )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==' '||(LA128_0 >= 'A' && LA128_0 <= 'Z')||(LA128_0 >= 'a' && LA128_0 <= 'z')) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:844:16: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop128;
                }
            } while (true);


            int checksumStart3327 = getCharIndex();
            int checksumStartLine3327 = getLine();
            int checksumStartCharPos3327 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3327, getCharIndex()-1);
            checksum.setLine(checksumStartLine3327);
            checksum.setCharPositionInLine(checksumStartCharPos3327);



               hdt = new HDT(device.getText(), getText(),
             		 heading != null ?  new Float(heading.getText()).floatValue(): 0.0f);
                		handler.doIt(hdt);
              

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HDT"

    // $ANTLR start "MSK"
    public final void mMSK() throws RecognitionException {
        try {
            int _type = MSK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken frequencyToUse=null;
            CommonToken frequencyMode=null;
            CommonToken beaconBitRate=null;
            CommonToken bitRateMode=null;
            CommonToken frequencyForMSS=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:851:6: ( '$' device= DEVICE 'MSK' SEP frequencyToUse= NUMBER SEP frequencyMode= LETTERS SEP beaconBitRate= NUMBER SEP bitRateMode= LETTERS SEP (frequencyForMSS= NUMBER )* ( SEP )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:851:8: '$' device= DEVICE 'MSK' SEP frequencyToUse= NUMBER SEP frequencyMode= LETTERS SEP beaconBitRate= NUMBER SEP bitRateMode= LETTERS SEP (frequencyForMSS= NUMBER )* ( SEP )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3348 = getCharIndex();
            int deviceStartLine3348 = getLine();
            int deviceStartCharPos3348 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3348, getCharIndex()-1);
            device.setLine(deviceStartLine3348);
            device.setCharPositionInLine(deviceStartCharPos3348);


            match("MSK"); 



            mSEP(); 


            int frequencyToUseStart3363 = getCharIndex();
            int frequencyToUseStartLine3363 = getLine();
            int frequencyToUseStartCharPos3363 = getCharPositionInLine();
            mNUMBER(); 
            frequencyToUse = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyToUseStart3363, getCharIndex()-1);
            frequencyToUse.setLine(frequencyToUseStartLine3363);
            frequencyToUse.setCharPositionInLine(frequencyToUseStartCharPos3363);


            mSEP(); 


            int frequencyModeStart3383 = getCharIndex();
            int frequencyModeStartLine3383 = getLine();
            int frequencyModeStartCharPos3383 = getCharPositionInLine();
            mLETTERS(); 
            frequencyMode = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyModeStart3383, getCharIndex()-1);
            frequencyMode.setLine(frequencyModeStartLine3383);
            frequencyMode.setCharPositionInLine(frequencyModeStartCharPos3383);


            mSEP(); 


            int beaconBitRateStart3403 = getCharIndex();
            int beaconBitRateStartLine3403 = getLine();
            int beaconBitRateStartCharPos3403 = getCharPositionInLine();
            mNUMBER(); 
            beaconBitRate = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, beaconBitRateStart3403, getCharIndex()-1);
            beaconBitRate.setLine(beaconBitRateStartLine3403);
            beaconBitRate.setCharPositionInLine(beaconBitRateStartCharPos3403);


            mSEP(); 


            int bitRateModeStart3423 = getCharIndex();
            int bitRateModeStartLine3423 = getLine();
            int bitRateModeStartCharPos3423 = getCharPositionInLine();
            mLETTERS(); 
            bitRateMode = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bitRateModeStart3423, getCharIndex()-1);
            bitRateMode.setLine(bitRateModeStartLine3423);
            bitRateMode.setCharPositionInLine(bitRateModeStartCharPos3423);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:856:29: (frequencyForMSS= NUMBER )*
            loop129:
            do {
                int alt129=2;
                int LA129_0 = input.LA(1);

                if ( (LA129_0=='.'||(LA129_0 >= '0' && LA129_0 <= '9')) ) {
                    alt129=1;
                }


                switch (alt129) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:856:29: frequencyForMSS= NUMBER
            	    {
            	    int frequencyForMSSStart3443 = getCharIndex();
            	    int frequencyForMSSStartLine3443 = getLine();
            	    int frequencyForMSSStartCharPos3443 = getCharPositionInLine();
            	    mNUMBER(); 
            	    frequencyForMSS = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyForMSSStart3443, getCharIndex()-1);
            	    frequencyForMSS.setLine(frequencyForMSSStartLine3443);
            	    frequencyForMSS.setCharPositionInLine(frequencyForMSSStartCharPos3443);


            	    }
            	    break;

            	default :
            	    break loop129;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:856:39: ( SEP )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( (LA130_0==',') ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==',' ) {
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
            	    break loop130;
                }
            } while (true);


            int checksumStart3463 = getCharIndex();
            int checksumStartLine3463 = getLine();
            int checksumStartCharPos3463 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3463, getCharIndex()-1);
            checksum.setLine(checksumStartLine3463);
            checksum.setCharPositionInLine(checksumStartCharPos3463);



                        msk = new MSK(device.getText(), getText(),
                        frequencyToUse != null ?  new Float(frequencyToUse.getText()).floatValue(): 0.0f,
                        frequencyMode != null ? frequencyMode.getText() : "",
                        beaconBitRate != null ? (new Integer(beaconBitRate.getText())).intValue() : 0,
                        bitRateMode != null ? bitRateMode.getText() : "",
                        frequencyForMSS != null ? (new Integer(frequencyForMSS.getText())).intValue() : 0);
                        
            	handler.doIt(msk);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MSK"

    // $ANTLR start "MTA"
    public final void mMTA() throws RecognitionException {
        try {
            int _type = MTA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken temperature=null;
            CommonToken unit=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:874:5: ( '$' device= DEVICE 'MTA' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:874:7: '$' device= DEVICE 'MTA' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3481 = getCharIndex();
            int deviceStartLine3481 = getLine();
            int deviceStartCharPos3481 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3481, getCharIndex()-1);
            device.setLine(deviceStartLine3481);
            device.setCharPositionInLine(deviceStartCharPos3481);


            match("MTA"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:875:4: (temperature= NUMBER )*
            loop131:
            do {
                int alt131=2;
                int LA131_0 = input.LA(1);

                if ( (LA131_0=='.'||(LA131_0 >= '0' && LA131_0 <= '9')) ) {
                    alt131=1;
                }


                switch (alt131) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:875:5: temperature= NUMBER
            	    {
            	    int temperatureStart3495 = getCharIndex();
            	    int temperatureStartLine3495 = getLine();
            	    int temperatureStartCharPos3495 = getCharPositionInLine();
            	    mNUMBER(); 
            	    temperature = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, temperatureStart3495, getCharIndex()-1);
            	    temperature.setLine(temperatureStartLine3495);
            	    temperature.setCharPositionInLine(temperatureStartCharPos3495);


            	    }
            	    break;

            	default :
            	    break loop131;
                }
            } while (true);


            mSEP(); 


            int unitStart3515 = getCharIndex();
            int unitStartLine3515 = getLine();
            int unitStartCharPos3515 = getCharPositionInLine();
            mLETTERS(); 
            unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3515, getCharIndex()-1);
            unit.setLine(unitStartLine3515);
            unit.setCharPositionInLine(unitStartCharPos3515);


            int checksumStart3530 = getCharIndex();
            int checksumStartLine3530 = getLine();
            int checksumStartCharPos3530 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3530, getCharIndex()-1);
            checksum.setLine(checksumStartLine3530);
            checksum.setCharPositionInLine(checksumStartCharPos3530);



            	 mta = new MTA(device.getText(), getText(),
            	 temperature != null ?  new Float(temperature.getText()).floatValue(): -276.00f,
            	 unit != null ? unit.getText() : "");
            	 
            	 handler.doIt(mta);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MTA"

    // $ANTLR start "MTW"
    public final void mMTW() throws RecognitionException {
        try {
            int _type = MTW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken temperature=null;
            CommonToken unit=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:891:6: ( '$' device= DEVICE 'MTW' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:891:8: '$' device= DEVICE 'MTW' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3549 = getCharIndex();
            int deviceStartLine3549 = getLine();
            int deviceStartCharPos3549 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3549, getCharIndex()-1);
            device.setLine(deviceStartLine3549);
            device.setCharPositionInLine(deviceStartCharPos3549);


            match("MTW"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:892:4: (temperature= NUMBER )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0=='.'||(LA132_0 >= '0' && LA132_0 <= '9')) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:892:5: temperature= NUMBER
            	    {
            	    int temperatureStart3563 = getCharIndex();
            	    int temperatureStartLine3563 = getLine();
            	    int temperatureStartCharPos3563 = getCharPositionInLine();
            	    mNUMBER(); 
            	    temperature = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, temperatureStart3563, getCharIndex()-1);
            	    temperature.setLine(temperatureStartLine3563);
            	    temperature.setCharPositionInLine(temperatureStartCharPos3563);


            	    }
            	    break;

            	default :
            	    break loop132;
                }
            } while (true);


            mSEP(); 


            int unitStart3583 = getCharIndex();
            int unitStartLine3583 = getLine();
            int unitStartCharPos3583 = getCharPositionInLine();
            mLETTERS(); 
            unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3583, getCharIndex()-1);
            unit.setLine(unitStartLine3583);
            unit.setCharPositionInLine(unitStartCharPos3583);


            int checksumStart3598 = getCharIndex();
            int checksumStartLine3598 = getLine();
            int checksumStartCharPos3598 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3598, getCharIndex()-1);
            checksum.setLine(checksumStartLine3598);
            checksum.setCharPositionInLine(checksumStartCharPos3598);



            	 mtw = new MTW(device.getText(), getText(),
            	 temperature != null ?  new Float(temperature.getText()).floatValue(): -276.00f,
            	 unit != null ? unit.getText() : "");
            	 
            	 handler.doIt(mtw);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MTW"

    // $ANTLR start "MWD"
    public final void mMWD() throws RecognitionException {
        try {
            int _type = MWD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken windDirectionTrue=null;
            CommonToken windDirectionMagnetic=null;
            CommonToken windSpeed=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:903:7: ( '$' device= DEVICE 'MWD' SEP (windDirectionTrue= NUMBER )* SEP LETTERS SEP (windDirectionMagnetic= NUMBER )* SEP LETTERS SEP (windSpeed= NUMBER )* SEP LETTERS SEP NUMBER SEP LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:903:9: '$' device= DEVICE 'MWD' SEP (windDirectionTrue= NUMBER )* SEP LETTERS SEP (windDirectionMagnetic= NUMBER )* SEP LETTERS SEP (windSpeed= NUMBER )* SEP LETTERS SEP NUMBER SEP LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3621 = getCharIndex();
            int deviceStartLine3621 = getLine();
            int deviceStartCharPos3621 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3621, getCharIndex()-1);
            device.setLine(deviceStartLine3621);
            device.setCharPositionInLine(deviceStartCharPos3621);


            match("MWD"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:904:22: (windDirectionTrue= NUMBER )*
            loop133:
            do {
                int alt133=2;
                int LA133_0 = input.LA(1);

                if ( (LA133_0=='.'||(LA133_0 >= '0' && LA133_0 <= '9')) ) {
                    alt133=1;
                }


                switch (alt133) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:904:22: windDirectionTrue= NUMBER
            	    {
            	    int windDirectionTrueStart3634 = getCharIndex();
            	    int windDirectionTrueStartLine3634 = getLine();
            	    int windDirectionTrueStartCharPos3634 = getCharPositionInLine();
            	    mNUMBER(); 
            	    windDirectionTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionTrueStart3634, getCharIndex()-1);
            	    windDirectionTrue.setLine(windDirectionTrueStartLine3634);
            	    windDirectionTrue.setCharPositionInLine(windDirectionTrueStartCharPos3634);


            	    }
            	    break;

            	default :
            	    break loop133;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:906:36: (windDirectionMagnetic= NUMBER )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0=='.'||(LA134_0 >= '0' && LA134_0 <= '9')) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:906:36: windDirectionMagnetic= NUMBER
            	    {
            	    int windDirectionMagneticStart3673 = getCharIndex();
            	    int windDirectionMagneticStartLine3673 = getLine();
            	    int windDirectionMagneticStartCharPos3673 = getCharPositionInLine();
            	    mNUMBER(); 
            	    windDirectionMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionMagneticStart3673, getCharIndex()-1);
            	    windDirectionMagnetic.setLine(windDirectionMagneticStartLine3673);
            	    windDirectionMagnetic.setCharPositionInLine(windDirectionMagneticStartCharPos3673);


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:908:13: (windSpeed= NUMBER )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0=='.'||(LA135_0 >= '0' && LA135_0 <= '9')) ) {
                    alt135=1;
                }


                switch (alt135) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:908:13: windSpeed= NUMBER
            	    {
            	    int windSpeedStart3701 = getCharIndex();
            	    int windSpeedStartLine3701 = getLine();
            	    int windSpeedStartCharPos3701 = getCharPositionInLine();
            	    mNUMBER(); 
            	    windSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windSpeedStart3701, getCharIndex()-1);
            	    windSpeed.setLine(windSpeedStartLine3701);
            	    windSpeed.setCharPositionInLine(windSpeedStartCharPos3701);


            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            mNUMBER(); 


            mSEP(); 


            mLETTERS(); 


            int checksumStart3768 = getCharIndex();
            int checksumStartLine3768 = getLine();
            int checksumStartCharPos3768 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3768, getCharIndex()-1);
            checksum.setLine(checksumStartLine3768);
            checksum.setCharPositionInLine(checksumStartCharPos3768);



             		mwd = new MWD(device.getText(), getText(),
             		windDirectionTrue != null ?  new Float(windDirectionTrue.getText()).floatValue(): 0.0f,
             		windDirectionMagnetic != null ?  new Float(windDirectionMagnetic.getText()).floatValue(): 0.0f,
             		windSpeed != null ?  new Float(windSpeed.getText()).floatValue(): 0.0f);
             		handler.doIt(mwd);
             		

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MWD"

    // $ANTLR start "MWV"
    public final void mMWV() throws RecognitionException {
        try {
            int _type = MWV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken windAngle=null;
            CommonToken reference=null;
            CommonToken windSpeed=null;
            CommonToken unit=null;
            CommonToken status=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:922:6: ( '$' device= DEVICE 'MWV' SEP windAngle= NUMBER SEP reference= LETTERS SEP windSpeed= NUMBER SEP unit= LETTERS SEP status= LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:922:8: '$' device= DEVICE 'MWV' SEP windAngle= NUMBER SEP reference= LETTERS SEP windSpeed= NUMBER SEP unit= LETTERS SEP status= LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3792 = getCharIndex();
            int deviceStartLine3792 = getLine();
            int deviceStartCharPos3792 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3792, getCharIndex()-1);
            device.setLine(deviceStartLine3792);
            device.setCharPositionInLine(deviceStartCharPos3792);


            match("MWV"); 



            mSEP(); 


            int windAngleStart3805 = getCharIndex();
            int windAngleStartLine3805 = getLine();
            int windAngleStartCharPos3805 = getCharPositionInLine();
            mNUMBER(); 
            windAngle = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windAngleStart3805, getCharIndex()-1);
            windAngle.setLine(windAngleStartLine3805);
            windAngle.setCharPositionInLine(windAngleStartCharPos3805);


            mSEP(); 


            int referenceStart3826 = getCharIndex();
            int referenceStartLine3826 = getLine();
            int referenceStartCharPos3826 = getCharPositionInLine();
            mLETTERS(); 
            reference = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, referenceStart3826, getCharIndex()-1);
            reference.setLine(referenceStartLine3826);
            reference.setCharPositionInLine(referenceStartCharPos3826);


            mSEP(); 


            int windSpeedStart3847 = getCharIndex();
            int windSpeedStartLine3847 = getLine();
            int windSpeedStartCharPos3847 = getCharPositionInLine();
            mNUMBER(); 
            windSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windSpeedStart3847, getCharIndex()-1);
            windSpeed.setLine(windSpeedStartLine3847);
            windSpeed.setCharPositionInLine(windSpeedStartCharPos3847);


            mSEP(); 


            int unitStart3868 = getCharIndex();
            int unitStartLine3868 = getLine();
            int unitStartCharPos3868 = getCharPositionInLine();
            mLETTERS(); 
            unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3868, getCharIndex()-1);
            unit.setLine(unitStartLine3868);
            unit.setCharPositionInLine(unitStartCharPos3868);


            mSEP(); 


            int statusStart3889 = getCharIndex();
            int statusStartLine3889 = getLine();
            int statusStartCharPos3889 = getCharPositionInLine();
            mLETTERS(); 
            status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart3889, getCharIndex()-1);
            status.setLine(statusStartLine3889);
            status.setCharPositionInLine(statusStartCharPos3889);


            int checksumStart3906 = getCharIndex();
            int checksumStartLine3906 = getLine();
            int checksumStartCharPos3906 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3906, getCharIndex()-1);
            checksum.setLine(checksumStartLine3906);
            checksum.setCharPositionInLine(checksumStartCharPos3906);



            	mwv = new MWV(device.getText(), getText(),
             		windAngle != null ?  new Float(windAngle.getText()).floatValue(): 0.0f,
             		reference != null ? reference.getText() : "",
             		windSpeed != null ?  new Float(windSpeed.getText()).floatValue(): 0.0f,
             		unit != null ? unit.getText() : "",
             		status != null ? status.getText() : "");
             		
             	 handler.doIt(mwv);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MWV"

    // $ANTLR start "RMB"
    public final void mRMB() throws RecognitionException {
        try {
            int _type = RMB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken status=null;
            CommonToken crossTrackError=null;
            CommonToken directionToSteer=null;
            CommonToken fromWaypointId=null;
            CommonToken toWaypointId=null;
            CommonToken destinationWaypointLatitude=null;
            CommonToken ns=null;
            CommonToken destinationWaypointLongitude=null;
            CommonToken ew=null;
            CommonToken rangeToDestination=null;
            CommonToken bearingToDestination=null;
            CommonToken destinationClosingVelocity=null;
            CommonToken arrivalStatus=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:946:8: ( '$' device= DEVICE 'RMB' SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (fromWaypointId= LETTERS |fromWaypointId= NUMBER )* SEP (toWaypointId= LETTERS |toWaypointId= NUMBER )* SEP (destinationWaypointLatitude= NUMBER )* SEP (ns= LETTERS )* SEP (destinationWaypointLongitude= NUMBER )* SEP (ew= LETTERS )* SEP (rangeToDestination= NUMBER )* SEP (bearingToDestination= NUMBER )* SEP (destinationClosingVelocity= NUMBER )* SEP ( LETTERS SEP )* (arrivalStatus= LETTERS | '\\u0000' )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:946:10: '$' device= DEVICE 'RMB' SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (fromWaypointId= LETTERS |fromWaypointId= NUMBER )* SEP (toWaypointId= LETTERS |toWaypointId= NUMBER )* SEP (destinationWaypointLatitude= NUMBER )* SEP (ns= LETTERS )* SEP (destinationWaypointLongitude= NUMBER )* SEP (ew= LETTERS )* SEP (rangeToDestination= NUMBER )* SEP (bearingToDestination= NUMBER )* SEP (destinationClosingVelocity= NUMBER )* SEP ( LETTERS SEP )* (arrivalStatus= LETTERS | '\\u0000' )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart3939 = getCharIndex();
            int deviceStartLine3939 = getLine();
            int deviceStartCharPos3939 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3939, getCharIndex()-1);
            device.setLine(deviceStartLine3939);
            device.setCharPositionInLine(deviceStartCharPos3939);


            match("RMB"); 



            mSEP(); 


            int statusStart3960 = getCharIndex();
            int statusStartLine3960 = getLine();
            int statusStartCharPos3960 = getCharPositionInLine();
            mLETTERS(); 
            status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart3960, getCharIndex()-1);
            status.setLine(statusStartLine3960);
            status.setCharPositionInLine(statusStartCharPos3960);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:948:11: (crossTrackError= NUMBER )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0=='.'||(LA136_0 >= '0' && LA136_0 <= '9')) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:948:12: crossTrackError= NUMBER
            	    {
            	    int crossTrackErrorStart3982 = getCharIndex();
            	    int crossTrackErrorStartLine3982 = getLine();
            	    int crossTrackErrorStartCharPos3982 = getCharPositionInLine();
            	    mNUMBER(); 
            	    crossTrackError = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorStart3982, getCharIndex()-1);
            	    crossTrackError.setLine(crossTrackErrorStartLine3982);
            	    crossTrackError.setCharPositionInLine(crossTrackErrorStartCharPos3982);


            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:949:11: (directionToSteer= LETTERS )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==' '||(LA137_0 >= 'A' && LA137_0 <= 'Z')||(LA137_0 >= 'a' && LA137_0 <= 'z')) ) {
                    alt137=1;
                }


                switch (alt137) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:949:12: directionToSteer= LETTERS
            	    {
            	    int directionToSteerStart4003 = getCharIndex();
            	    int directionToSteerStartLine4003 = getLine();
            	    int directionToSteerStartCharPos4003 = getCharPositionInLine();
            	    mLETTERS(); 
            	    directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart4003, getCharIndex()-1);
            	    directionToSteer.setLine(directionToSteerStartLine4003);
            	    directionToSteer.setCharPositionInLine(directionToSteerStartCharPos4003);


            	    }
            	    break;

            	default :
            	    break loop137;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:950:11: (fromWaypointId= LETTERS |fromWaypointId= NUMBER )*
            loop138:
            do {
                int alt138=3;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==' '||(LA138_0 >= 'A' && LA138_0 <= 'Z')||(LA138_0 >= 'a' && LA138_0 <= 'z')) ) {
                    alt138=1;
                }
                else if ( (LA138_0=='.'||(LA138_0 >= '0' && LA138_0 <= '9')) ) {
                    alt138=2;
                }


                switch (alt138) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:950:12: fromWaypointId= LETTERS
            	    {
            	    int fromWaypointIdStart4024 = getCharIndex();
            	    int fromWaypointIdStartLine4024 = getLine();
            	    int fromWaypointIdStartCharPos4024 = getCharPositionInLine();
            	    mLETTERS(); 
            	    fromWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIdStart4024, getCharIndex()-1);
            	    fromWaypointId.setLine(fromWaypointIdStartLine4024);
            	    fromWaypointId.setCharPositionInLine(fromWaypointIdStartCharPos4024);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:950:38: fromWaypointId= NUMBER
            	    {
            	    int fromWaypointIdStart4031 = getCharIndex();
            	    int fromWaypointIdStartLine4031 = getLine();
            	    int fromWaypointIdStartCharPos4031 = getCharPositionInLine();
            	    mNUMBER(); 
            	    fromWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIdStart4031, getCharIndex()-1);
            	    fromWaypointId.setLine(fromWaypointIdStartLine4031);
            	    fromWaypointId.setCharPositionInLine(fromWaypointIdStartCharPos4031);


            	    }
            	    break;

            	default :
            	    break loop138;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:951:11: (toWaypointId= LETTERS |toWaypointId= NUMBER )*
            loop139:
            do {
                int alt139=3;
                int LA139_0 = input.LA(1);

                if ( (LA139_0==' '||(LA139_0 >= 'A' && LA139_0 <= 'Z')||(LA139_0 >= 'a' && LA139_0 <= 'z')) ) {
                    alt139=1;
                }
                else if ( (LA139_0=='.'||(LA139_0 >= '0' && LA139_0 <= '9')) ) {
                    alt139=2;
                }


                switch (alt139) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:951:12: toWaypointId= LETTERS
            	    {
            	    int toWaypointIdStart4054 = getCharIndex();
            	    int toWaypointIdStartLine4054 = getLine();
            	    int toWaypointIdStartCharPos4054 = getCharPositionInLine();
            	    mLETTERS(); 
            	    toWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIdStart4054, getCharIndex()-1);
            	    toWaypointId.setLine(toWaypointIdStartLine4054);
            	    toWaypointId.setCharPositionInLine(toWaypointIdStartCharPos4054);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:951:38: toWaypointId= NUMBER
            	    {
            	    int toWaypointIdStart4062 = getCharIndex();
            	    int toWaypointIdStartLine4062 = getLine();
            	    int toWaypointIdStartCharPos4062 = getCharPositionInLine();
            	    mNUMBER(); 
            	    toWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIdStart4062, getCharIndex()-1);
            	    toWaypointId.setLine(toWaypointIdStartLine4062);
            	    toWaypointId.setCharPositionInLine(toWaypointIdStartCharPos4062);


            	    }
            	    break;

            	default :
            	    break loop139;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:952:11: (destinationWaypointLatitude= NUMBER )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0=='.'||(LA140_0 >= '0' && LA140_0 <= '9')) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:952:12: destinationWaypointLatitude= NUMBER
            	    {
            	    int destinationWaypointLatitudeStart4084 = getCharIndex();
            	    int destinationWaypointLatitudeStartLine4084 = getLine();
            	    int destinationWaypointLatitudeStartCharPos4084 = getCharPositionInLine();
            	    mNUMBER(); 
            	    destinationWaypointLatitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointLatitudeStart4084, getCharIndex()-1);
            	    destinationWaypointLatitude.setLine(destinationWaypointLatitudeStartLine4084);
            	    destinationWaypointLatitude.setCharPositionInLine(destinationWaypointLatitudeStartCharPos4084);


            	    }
            	    break;

            	default :
            	    break loop140;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:952:56: (ns= LETTERS )*
            loop141:
            do {
                int alt141=2;
                int LA141_0 = input.LA(1);

                if ( (LA141_0==' '||(LA141_0 >= 'A' && LA141_0 <= 'Z')||(LA141_0 >= 'a' && LA141_0 <= 'z')) ) {
                    alt141=1;
                }


                switch (alt141) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:952:57: ns= LETTERS
            	    {
            	    int nsStart4095 = getCharIndex();
            	    int nsStartLine4095 = getLine();
            	    int nsStartCharPos4095 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart4095, getCharIndex()-1);
            	    ns.setLine(nsStartLine4095);
            	    ns.setCharPositionInLine(nsStartCharPos4095);


            	    }
            	    break;

            	default :
            	    break loop141;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:953:11: (destinationWaypointLongitude= NUMBER )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0=='.'||(LA142_0 >= '0' && LA142_0 <= '9')) ) {
                    alt142=1;
                }


                switch (alt142) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:953:12: destinationWaypointLongitude= NUMBER
            	    {
            	    int destinationWaypointLongitudeStart4118 = getCharIndex();
            	    int destinationWaypointLongitudeStartLine4118 = getLine();
            	    int destinationWaypointLongitudeStartCharPos4118 = getCharPositionInLine();
            	    mNUMBER(); 
            	    destinationWaypointLongitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointLongitudeStart4118, getCharIndex()-1);
            	    destinationWaypointLongitude.setLine(destinationWaypointLongitudeStartLine4118);
            	    destinationWaypointLongitude.setCharPositionInLine(destinationWaypointLongitudeStartCharPos4118);


            	    }
            	    break;

            	default :
            	    break loop142;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:953:58: (ew= LETTERS )*
            loop143:
            do {
                int alt143=2;
                int LA143_0 = input.LA(1);

                if ( (LA143_0==' '||(LA143_0 >= 'A' && LA143_0 <= 'Z')||(LA143_0 >= 'a' && LA143_0 <= 'z')) ) {
                    alt143=1;
                }


                switch (alt143) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:953:59: ew= LETTERS
            	    {
            	    int ewStart4130 = getCharIndex();
            	    int ewStartLine4130 = getLine();
            	    int ewStartCharPos4130 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart4130, getCharIndex()-1);
            	    ew.setLine(ewStartLine4130);
            	    ew.setCharPositionInLine(ewStartCharPos4130);


            	    }
            	    break;

            	default :
            	    break loop143;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:954:11: (rangeToDestination= NUMBER )*
            loop144:
            do {
                int alt144=2;
                int LA144_0 = input.LA(1);

                if ( (LA144_0=='.'||(LA144_0 >= '0' && LA144_0 <= '9')) ) {
                    alt144=1;
                }


                switch (alt144) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:954:12: rangeToDestination= NUMBER
            	    {
            	    int rangeToDestinationStart4152 = getCharIndex();
            	    int rangeToDestinationStartLine4152 = getLine();
            	    int rangeToDestinationStartCharPos4152 = getCharPositionInLine();
            	    mNUMBER(); 
            	    rangeToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, rangeToDestinationStart4152, getCharIndex()-1);
            	    rangeToDestination.setLine(rangeToDestinationStartLine4152);
            	    rangeToDestination.setCharPositionInLine(rangeToDestinationStartCharPos4152);


            	    }
            	    break;

            	default :
            	    break loop144;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:955:11: (bearingToDestination= NUMBER )*
            loop145:
            do {
                int alt145=2;
                int LA145_0 = input.LA(1);

                if ( (LA145_0=='.'||(LA145_0 >= '0' && LA145_0 <= '9')) ) {
                    alt145=1;
                }


                switch (alt145) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:955:12: bearingToDestination= NUMBER
            	    {
            	    int bearingToDestinationStart4174 = getCharIndex();
            	    int bearingToDestinationStartLine4174 = getLine();
            	    int bearingToDestinationStartCharPos4174 = getCharPositionInLine();
            	    mNUMBER(); 
            	    bearingToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingToDestinationStart4174, getCharIndex()-1);
            	    bearingToDestination.setLine(bearingToDestinationStartLine4174);
            	    bearingToDestination.setCharPositionInLine(bearingToDestinationStartCharPos4174);


            	    }
            	    break;

            	default :
            	    break loop145;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:956:11: (destinationClosingVelocity= NUMBER )*
            loop146:
            do {
                int alt146=2;
                int LA146_0 = input.LA(1);

                if ( (LA146_0=='.'||(LA146_0 >= '0' && LA146_0 <= '9')) ) {
                    alt146=1;
                }


                switch (alt146) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:956:12: destinationClosingVelocity= NUMBER
            	    {
            	    int destinationClosingVelocityStart4196 = getCharIndex();
            	    int destinationClosingVelocityStartLine4196 = getLine();
            	    int destinationClosingVelocityStartCharPos4196 = getCharPositionInLine();
            	    mNUMBER(); 
            	    destinationClosingVelocity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationClosingVelocityStart4196, getCharIndex()-1);
            	    destinationClosingVelocity.setLine(destinationClosingVelocityStartLine4196);
            	    destinationClosingVelocity.setCharPositionInLine(destinationClosingVelocityStartCharPos4196);


            	    }
            	    break;

            	default :
            	    break loop146;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:957:11: ( LETTERS SEP )*
            loop147:
            do {
                int alt147=2;
                alt147 = dfa147.predict(input);
                switch (alt147) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:957:12: LETTERS SEP
            	    {
            	    mLETTERS(); 


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop147;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:958:11: (arrivalStatus= LETTERS | '\\u0000' )*
            loop148:
            do {
                int alt148=3;
                int LA148_0 = input.LA(1);

                if ( (LA148_0==' '||(LA148_0 >= 'A' && LA148_0 <= 'Z')||(LA148_0 >= 'a' && LA148_0 <= 'z')) ) {
                    alt148=1;
                }
                else if ( (LA148_0=='\u0000') ) {
                    alt148=2;
                }


                switch (alt148) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:958:12: arrivalStatus= LETTERS
            	    {
            	    int arrivalStatusStart4249 = getCharIndex();
            	    int arrivalStatusStartLine4249 = getLine();
            	    int arrivalStatusStartCharPos4249 = getCharPositionInLine();
            	    mLETTERS(); 
            	    arrivalStatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, arrivalStatusStart4249, getCharIndex()-1);
            	    arrivalStatus.setLine(arrivalStatusStartLine4249);
            	    arrivalStatus.setCharPositionInLine(arrivalStatusStartCharPos4249);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:958:38: '\\u0000'
            	    {
            	    match('\u0000'); 

            	    }
            	    break;

            	default :
            	    break loop148;
                }
            } while (true);


            int checksumStart4270 = getCharIndex();
            int checksumStartLine4270 = getLine();
            int checksumStartCharPos4270 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4270, getCharIndex()-1);
            checksum.setLine(checksumStartLine4270);
            checksum.setCharPositionInLine(checksumStartCharPos4270);



               lat = latConvert(destinationWaypointLatitude != null ? (new Float(destinationWaypointLatitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
               lon = lonConvert(destinationWaypointLongitude != null ? (new Float(destinationWaypointLongitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
               rmb = new RMB(device.getText(), getText(),
              		 status.getText(),
              		 crossTrackError != null ? new Float(crossTrackError.getText()).floatValue() : 0.0f,
              		 directionToSteer != null ? directionToSteer.getText() : "",
              		 fromWaypointId != null ? fromWaypointId.getText() : "",
              		 toWaypointId != null ? toWaypointId.getText() : "",
              		 lat,
              		 lon,
              		 rangeToDestination != null ? new Float(rangeToDestination.getText()).floatValue() : 0.0f,
              		 bearingToDestination != null ? new Float(bearingToDestination.getText()).floatValue() : 0.0f,
              		 destinationClosingVelocity != null ? new Float(destinationClosingVelocity.getText()).floatValue() : 0.0f,
              		 //arrivalStatus != null ? arrivalStatus.getText() : "");
            	         "");
                handler.doIt(rmb);
              

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RMB"

    // $ANTLR start "RMC"
    public final void mRMC() throws RecognitionException {
        try {
            int _type = RMC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken status=null;
            CommonToken latitude=null;
            CommonToken ns=null;
            CommonToken longitude=null;
            CommonToken ew=null;
            CommonToken sog=null;
            CommonToken track=null;
            CommonToken yymmdd=null;
            CommonToken magneticVariation=null;
            CommonToken nsew=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1009:6: ( '$' device= DEVICE 'RMC' SEP (utc= NUMBER )* SEP status= LETTERS SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( SIGN )* (sog= NUMBER )* SEP (track= NUMBER )* SEP (yymmdd= NUMBER )* SEP (magneticVariation= NUMBER )* SEP (nsew= LETTERS )* ( SEP LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1009:8: '$' device= DEVICE 'RMC' SEP (utc= NUMBER )* SEP status= LETTERS SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( SIGN )* (sog= NUMBER )* SEP (track= NUMBER )* SEP (yymmdd= NUMBER )* SEP (magneticVariation= NUMBER )* SEP (nsew= LETTERS )* ( SEP LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4302 = getCharIndex();
            int deviceStartLine4302 = getLine();
            int deviceStartCharPos4302 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4302, getCharIndex()-1);
            device.setLine(deviceStartLine4302);
            device.setCharPositionInLine(deviceStartCharPos4302);


            match("RMC"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1010:16: (utc= NUMBER )*
            loop149:
            do {
                int alt149=2;
                int LA149_0 = input.LA(1);

                if ( (LA149_0=='.'||(LA149_0 >= '0' && LA149_0 <= '9')) ) {
                    alt149=1;
                }


                switch (alt149) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1010:16: utc= NUMBER
            	    {
            	    int utcStart4323 = getCharIndex();
            	    int utcStartLine4323 = getLine();
            	    int utcStartCharPos4323 = getCharPositionInLine();
            	    mNUMBER(); 
            	    utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart4323, getCharIndex()-1);
            	    utc.setLine(utcStartLine4323);
            	    utc.setCharPositionInLine(utcStartCharPos4323);


            	    }
            	    break;

            	default :
            	    break loop149;
                }
            } while (true);


            mSEP(); 


            int statusStart4343 = getCharIndex();
            int statusStartLine4343 = getLine();
            int statusStartCharPos4343 = getCharPositionInLine();
            mLETTERS(); 
            status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart4343, getCharIndex()-1);
            status.setLine(statusStartLine4343);
            status.setCharPositionInLine(statusStartCharPos4343);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:20: (latitude= NUMBER )*
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0=='.'||(LA150_0 >= '0' && LA150_0 <= '9')) ) {
                    alt150=1;
                }


                switch (alt150) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:20: latitude= NUMBER
            	    {
            	    int latitudeStart4360 = getCharIndex();
            	    int latitudeStartLine4360 = getLine();
            	    int latitudeStartCharPos4360 = getCharPositionInLine();
            	    mNUMBER(); 
            	    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart4360, getCharIndex()-1);
            	    latitude.setLine(latitudeStartLine4360);
            	    latitude.setCharPositionInLine(latitudeStartCharPos4360);


            	    }
            	    break;

            	default :
            	    break loop150;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:36: (ns= LETTERS )*
            loop151:
            do {
                int alt151=2;
                int LA151_0 = input.LA(1);

                if ( (LA151_0==' '||(LA151_0 >= 'A' && LA151_0 <= 'Z')||(LA151_0 >= 'a' && LA151_0 <= 'z')) ) {
                    alt151=1;
                }


                switch (alt151) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:36: ns= LETTERS
            	    {
            	    int nsStart4369 = getCharIndex();
            	    int nsStartLine4369 = getLine();
            	    int nsStartCharPos4369 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart4369, getCharIndex()-1);
            	    ns.setLine(nsStartLine4369);
            	    ns.setCharPositionInLine(nsStartCharPos4369);


            	    }
            	    break;

            	default :
            	    break loop151;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:60: (longitude= NUMBER )*
            loop152:
            do {
                int alt152=2;
                int LA152_0 = input.LA(1);

                if ( (LA152_0=='.'||(LA152_0 >= '0' && LA152_0 <= '9')) ) {
                    alt152=1;
                }


                switch (alt152) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:60: longitude= NUMBER
            	    {
            	    int longitudeStart4376 = getCharIndex();
            	    int longitudeStartLine4376 = getLine();
            	    int longitudeStartCharPos4376 = getCharPositionInLine();
            	    mNUMBER(); 
            	    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart4376, getCharIndex()-1);
            	    longitude.setLine(longitudeStartLine4376);
            	    longitude.setCharPositionInLine(longitudeStartCharPos4376);


            	    }
            	    break;

            	default :
            	    break loop152;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:75: (ew= LETTERS )*
            loop153:
            do {
                int alt153=2;
                int LA153_0 = input.LA(1);

                if ( (LA153_0==' '||(LA153_0 >= 'A' && LA153_0 <= 'Z')||(LA153_0 >= 'a' && LA153_0 <= 'z')) ) {
                    alt153=1;
                }


                switch (alt153) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1012:75: ew= LETTERS
            	    {
            	    int ewStart4383 = getCharIndex();
            	    int ewStartLine4383 = getLine();
            	    int ewStartCharPos4383 = getCharPositionInLine();
            	    mLETTERS(); 
            	    ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart4383, getCharIndex()-1);
            	    ew.setLine(ewStartLine4383);
            	    ew.setCharPositionInLine(ewStartCharPos4383);


            	    }
            	    break;

            	default :
            	    break loop153;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1013:12: ( SIGN )*
            loop154:
            do {
                int alt154=2;
                int LA154_0 = input.LA(1);

                if ( (LA154_0=='+'||LA154_0=='-') ) {
                    alt154=1;
                }


                switch (alt154) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    break loop154;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1013:18: (sog= NUMBER )*
            loop155:
            do {
                int alt155=2;
                int LA155_0 = input.LA(1);

                if ( (LA155_0=='.'||(LA155_0 >= '0' && LA155_0 <= '9')) ) {
                    alt155=1;
                }


                switch (alt155) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1013:19: sog= NUMBER
            	    {
            	    int sogStart4407 = getCharIndex();
            	    int sogStartLine4407 = getLine();
            	    int sogStartCharPos4407 = getCharPositionInLine();
            	    mNUMBER(); 
            	    sog = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sogStart4407, getCharIndex()-1);
            	    sog.setLine(sogStartLine4407);
            	    sog.setCharPositionInLine(sogStartCharPos4407);


            	    }
            	    break;

            	default :
            	    break loop155;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1014:12: (track= NUMBER )*
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( (LA156_0=='.'||(LA156_0 >= '0' && LA156_0 <= '9')) ) {
                    alt156=1;
                }


                switch (alt156) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1014:13: track= NUMBER
            	    {
            	    int trackStart4429 = getCharIndex();
            	    int trackStartLine4429 = getLine();
            	    int trackStartCharPos4429 = getCharPositionInLine();
            	    mNUMBER(); 
            	    track = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, trackStart4429, getCharIndex()-1);
            	    track.setLine(trackStartLine4429);
            	    track.setCharPositionInLine(trackStartCharPos4429);


            	    }
            	    break;

            	default :
            	    break loop156;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1015:13: (yymmdd= NUMBER )*
            loop157:
            do {
                int alt157=2;
                int LA157_0 = input.LA(1);

                if ( (LA157_0=='.'||(LA157_0 >= '0' && LA157_0 <= '9')) ) {
                    alt157=1;
                }


                switch (alt157) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1015:14: yymmdd= NUMBER
            	    {
            	    int yymmddStart4452 = getCharIndex();
            	    int yymmddStartLine4452 = getLine();
            	    int yymmddStartCharPos4452 = getCharPositionInLine();
            	    mNUMBER(); 
            	    yymmdd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, yymmddStart4452, getCharIndex()-1);
            	    yymmdd.setLine(yymmddStartLine4452);
            	    yymmdd.setCharPositionInLine(yymmddStartCharPos4452);


            	    }
            	    break;

            	default :
            	    break loop157;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1016:31: (magneticVariation= NUMBER )*
            loop158:
            do {
                int alt158=2;
                int LA158_0 = input.LA(1);

                if ( (LA158_0=='.'||(LA158_0 >= '0' && LA158_0 <= '9')) ) {
                    alt158=1;
                }


                switch (alt158) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1016:31: magneticVariation= NUMBER
            	    {
            	    int magneticVariationStart4474 = getCharIndex();
            	    int magneticVariationStartLine4474 = getLine();
            	    int magneticVariationStartCharPos4474 = getCharPositionInLine();
            	    mNUMBER(); 
            	    magneticVariation = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, magneticVariationStart4474, getCharIndex()-1);
            	    magneticVariation.setLine(magneticVariationStartLine4474);
            	    magneticVariation.setCharPositionInLine(magneticVariationStartCharPos4474);


            	    }
            	    break;

            	default :
            	    break loop158;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1017:18: (nsew= LETTERS )*
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==' '||(LA159_0 >= 'A' && LA159_0 <= 'Z')||(LA159_0 >= 'a' && LA159_0 <= 'z')) ) {
                    alt159=1;
                }


                switch (alt159) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1017:18: nsew= LETTERS
            	    {
            	    int nsewStart4496 = getCharIndex();
            	    int nsewStartLine4496 = getLine();
            	    int nsewStartCharPos4496 = getCharPositionInLine();
            	    mLETTERS(); 
            	    nsew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsewStart4496, getCharIndex()-1);
            	    nsew.setLine(nsewStartLine4496);
            	    nsew.setCharPositionInLine(nsewStartCharPos4496);


            	    }
            	    break;

            	default :
            	    break loop159;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1018:13: ( SEP LETTERS )*
            loop160:
            do {
                int alt160=2;
                int LA160_0 = input.LA(1);

                if ( (LA160_0==',') ) {
                    alt160=1;
                }


                switch (alt160) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1018:14: SEP LETTERS
            	    {
            	    mSEP(); 


            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop160;
                }
            } while (true);


            int checksumStart4532 = getCharIndex();
            int checksumStartLine4532 = getLine();
            int checksumStartCharPos4532 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4532, getCharIndex()-1);
            checksum.setLine(checksumStartLine4532);
            checksum.setCharPositionInLine(checksumStartCharPos4532);



                        lat = latConvert(latitude != null ? (new Float(latitude.getText())).floatValue() : 0.0f, ns != null ? ns.getText() : "");
                        lon = lonConvert(longitude != null ? (new Float(longitude.getText())).floatValue() : 0.0f, ew != null ? ew.getText() : "");
                        if(utc != null)
            	      date = dateFactory(utc.getText());
            	    else
            	      date = dateFactory("000000");
                        String sentence = getText();
                        
                        if(sentence.contains("\n")){
                             String [] tab = sentence.split("\n");
                             sentence = tab[0];
                        }
                        rmc = new RMC( device.getText(), sentence,
                        		  date,
                        		  status.getText(),
                                      lat, 
                                      lon, 
                                      sog != null ? new Float(sog.getText()).floatValue() : 0.0f,
                                      track != null ? new Float(track.getText()).floatValue() : 0.0f,
                                      magneticVariation != null ? new Float(magneticVariation.getText()).floatValue() : 0.0f,
                                      //nsew != null ? nsew.getText() : ""
                                      ""
                                      );
                      
                       handler.doIt(rmc);
                        

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RMC"

    // $ANTLR start "RSD"
    public final void mRSD() throws RecognitionException {
        try {
            int _type = RSD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1034:6: ( '$' device= DEVICE 'RSD' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1034:9: '$' device= DEVICE 'RSD' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4561 = getCharIndex();
            int deviceStartLine4561 = getLine();
            int deviceStartCharPos4561 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4561, getCharIndex()-1);
            device.setLine(deviceStartLine4561);
            device.setCharPositionInLine(deviceStartCharPos4561);


            match("RSD"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1035:3: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
            loop161:
            do {
                int alt161=2;
                alt161 = dfa161.predict(input);
                switch (alt161) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= ' ' && input.LA(1) <= '\u007F') ) {
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
            	    break loop161;
                }
            } while (true);


            int checksumStart4591 = getCharIndex();
            int checksumStartLine4591 = getLine();
            int checksumStartCharPos4591 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4591, getCharIndex()-1);
            checksum.setLine(checksumStartLine4591);
            checksum.setCharPositionInLine(checksumStartCharPos4591);



            	//System.out.println(getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RSD"

    // $ANTLR start "RTE"
    public final void mRTE() throws RecognitionException {
        try {
            int _type = RTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken totalNumberOfsentence=null;
            CommonToken sentenceNumber=null;
            CommonToken type=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1048:5: ( '$' device= DEVICE 'RTE' SEP totalNumberOfsentence= NUMBER SEP sentenceNumber= NUMBER SEP type= LETTERS SEP ( LETTERS | NUMBER | '-' | '_' | SEP )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1048:7: '$' device= DEVICE 'RTE' SEP totalNumberOfsentence= NUMBER SEP sentenceNumber= NUMBER SEP type= LETTERS SEP ( LETTERS | NUMBER | '-' | '_' | SEP )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4610 = getCharIndex();
            int deviceStartLine4610 = getLine();
            int deviceStartCharPos4610 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4610, getCharIndex()-1);
            device.setLine(deviceStartLine4610);
            device.setCharPositionInLine(deviceStartCharPos4610);


            match("RTE"); 



            mSEP(); 


            int totalNumberOfsentenceStart4621 = getCharIndex();
            int totalNumberOfsentenceStartLine4621 = getLine();
            int totalNumberOfsentenceStartCharPos4621 = getCharPositionInLine();
            mNUMBER(); 
            totalNumberOfsentence = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, totalNumberOfsentenceStart4621, getCharIndex()-1);
            totalNumberOfsentence.setLine(totalNumberOfsentenceStartLine4621);
            totalNumberOfsentence.setCharPositionInLine(totalNumberOfsentenceStartCharPos4621);


            mSEP(); 


            int sentenceNumberStart4630 = getCharIndex();
            int sentenceNumberStartLine4630 = getLine();
            int sentenceNumberStartCharPos4630 = getCharPositionInLine();
            mNUMBER(); 
            sentenceNumber = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sentenceNumberStart4630, getCharIndex()-1);
            sentenceNumber.setLine(sentenceNumberStartLine4630);
            sentenceNumber.setCharPositionInLine(sentenceNumberStartCharPos4630);


            mSEP(); 


            int typeStart4639 = getCharIndex();
            int typeStartLine4639 = getLine();
            int typeStartCharPos4639 = getCharPositionInLine();
            mLETTERS(); 
            type = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, typeStart4639, getCharIndex()-1);
            type.setLine(typeStartLine4639);
            type.setCharPositionInLine(typeStartCharPos4639);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:2: ( LETTERS | NUMBER | '-' | '_' | SEP )*
            loop162:
            do {
                int alt162=6;
                switch ( input.LA(1) ) {
                case ' ':
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
                    alt162=1;
                    }
                    break;
                case '.':
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
                    alt162=2;
                    }
                    break;
                case '-':
                    {
                    alt162=3;
                    }
                    break;
                case '_':
                    {
                    alt162=4;
                    }
                    break;
                case ',':
                    {
                    alt162=5;
                    }
                    break;

                }

                switch (alt162) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:3: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:13: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:22: '-'
            	    {
            	    match('-'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:28: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1052:34: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop162;
                }
            } while (true);


            int checksumStart4669 = getCharIndex();
            int checksumStartLine4669 = getLine();
            int checksumStartCharPos4669 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4669, getCharIndex()-1);
            checksum.setLine(checksumStartLine4669);
            checksum.setCharPositionInLine(checksumStartCharPos4669);



                    String[] route = getText().split(",");
                    List<String> waypoints = new ArrayList<>();
                    for(int i = 4; i < route.length ; i++){
                         waypoints.add(route[i]);
                     }
                         rte = new RTE(device.getText(), getText(),
                         new Integer(route[1]).intValue(),
                         new Integer(route[2]).intValue(),
                         route[3],
                         waypoints);
                        // System.out.println(rte); 
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RTE"

    // $ANTLR start "VBW"
    public final void mVBW() throws RecognitionException {
        try {
            int _type = VBW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken longitudinalWaterSpeed=null;
            CommonToken transverseWaterSpeed=null;
            CommonToken wstatus=null;
            CommonToken longitudinalGroundSpeed=null;
            CommonToken transverseGroundSpeed=null;
            CommonToken gstatus=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1067:6: ( '$' device= DEVICE 'VBW' SEP ( ' ' )* ( SIGN )* longitudinalWaterSpeed= NUMBER SEP ( ' ' )* ( SIGN )* transverseWaterSpeed= NUMBER SEP wstatus= LETTERS ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1067:8: '$' device= DEVICE 'VBW' SEP ( ' ' )* ( SIGN )* longitudinalWaterSpeed= NUMBER SEP ( ' ' )* ( SIGN )* transverseWaterSpeed= NUMBER SEP wstatus= LETTERS ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4688 = getCharIndex();
            int deviceStartLine4688 = getLine();
            int deviceStartCharPos4688 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4688, getCharIndex()-1);
            device.setLine(deviceStartLine4688);
            device.setCharPositionInLine(deviceStartCharPos4688);


            match("VBW"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1068:2: ( ' ' )*
            loop163:
            do {
                int alt163=2;
                int LA163_0 = input.LA(1);

                if ( (LA163_0==' ') ) {
                    alt163=1;
                }


                switch (alt163) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1068:2: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop163;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1068:7: ( SIGN )*
            loop164:
            do {
                int alt164=2;
                int LA164_0 = input.LA(1);

                if ( (LA164_0=='+'||LA164_0=='-') ) {
                    alt164=1;
                }


                switch (alt164) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    break loop164;
                }
            } while (true);


            int longitudinalWaterSpeedStart4705 = getCharIndex();
            int longitudinalWaterSpeedStartLine4705 = getLine();
            int longitudinalWaterSpeedStartCharPos4705 = getCharPositionInLine();
            mNUMBER(); 
            longitudinalWaterSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudinalWaterSpeedStart4705, getCharIndex()-1);
            longitudinalWaterSpeed.setLine(longitudinalWaterSpeedStartLine4705);
            longitudinalWaterSpeed.setCharPositionInLine(longitudinalWaterSpeedStartCharPos4705);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1069:2: ( ' ' )*
            loop165:
            do {
                int alt165=2;
                int LA165_0 = input.LA(1);

                if ( (LA165_0==' ') ) {
                    alt165=1;
                }


                switch (alt165) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1069:2: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop165;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1069:7: ( SIGN )*
            loop166:
            do {
                int alt166=2;
                int LA166_0 = input.LA(1);

                if ( (LA166_0=='+'||LA166_0=='-') ) {
                    alt166=1;
                }


                switch (alt166) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    break loop166;
                }
            } while (true);


            int transverseWaterSpeedStart4720 = getCharIndex();
            int transverseWaterSpeedStartLine4720 = getLine();
            int transverseWaterSpeedStartCharPos4720 = getCharPositionInLine();
            mNUMBER(); 
            transverseWaterSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, transverseWaterSpeedStart4720, getCharIndex()-1);
            transverseWaterSpeed.setLine(transverseWaterSpeedStartLine4720);
            transverseWaterSpeed.setCharPositionInLine(transverseWaterSpeedStartCharPos4720);


            mSEP(); 


            int wstatusStart4729 = getCharIndex();
            int wstatusStartLine4729 = getLine();
            int wstatusStartCharPos4729 = getCharPositionInLine();
            mLETTERS(); 
            wstatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wstatusStart4729, getCharIndex()-1);
            wstatus.setLine(wstatusStartLine4729);
            wstatus.setCharPositionInLine(wstatusStartCharPos4729);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:2: ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )*
            loop173:
            do {
                int alt173=2;
                int LA173_0 = input.LA(1);

                if ( (LA173_0==',') ) {
                    alt173=1;
                }


                switch (alt173) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:3: SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS
            	    {
            	    mSEP(); 


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:7: ( ' ' )*
            	    loop167:
            	    do {
            	        int alt167=2;
            	        int LA167_0 = input.LA(1);

            	        if ( (LA167_0==' ') ) {
            	            alt167=1;
            	        }


            	        switch (alt167) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:7: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop167;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:12: ( SIGN )*
            	    loop168:
            	    do {
            	        int alt168=2;
            	        int LA168_0 = input.LA(1);

            	        if ( (LA168_0=='+'||LA168_0=='-') ) {
            	            alt168=1;
            	        }


            	        switch (alt168) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    	    {
            	    	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    	    break loop168;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:18: (longitudinalGroundSpeed= NUMBER )*
            	    loop169:
            	    do {
            	        int alt169=2;
            	        int LA169_0 = input.LA(1);

            	        if ( (LA169_0=='.'||(LA169_0 >= '0' && LA169_0 <= '9')) ) {
            	            alt169=1;
            	        }


            	        switch (alt169) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1071:19: longitudinalGroundSpeed= NUMBER
            	    	    {
            	    	    int longitudinalGroundSpeedStart4746 = getCharIndex();
            	    	    int longitudinalGroundSpeedStartLine4746 = getLine();
            	    	    int longitudinalGroundSpeedStartCharPos4746 = getCharPositionInLine();
            	    	    mNUMBER(); 
            	    	    longitudinalGroundSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudinalGroundSpeedStart4746, getCharIndex()-1);
            	    	    longitudinalGroundSpeed.setLine(longitudinalGroundSpeedStartLine4746);
            	    	    longitudinalGroundSpeed.setCharPositionInLine(longitudinalGroundSpeedStartCharPos4746);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop169;
            	        }
            	    } while (true);


            	    mSEP(); 


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1072:2: ( ' ' )*
            	    loop170:
            	    do {
            	        int alt170=2;
            	        int LA170_0 = input.LA(1);

            	        if ( (LA170_0==' ') ) {
            	            alt170=1;
            	        }


            	        switch (alt170) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1072:2: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop170;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1072:7: ( SIGN )*
            	    loop171:
            	    do {
            	        int alt171=2;
            	        int LA171_0 = input.LA(1);

            	        if ( (LA171_0=='+'||LA171_0=='-') ) {
            	            alt171=1;
            	        }


            	        switch (alt171) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    	    {
            	    	    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
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
            	    	    break loop171;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1072:13: (transverseGroundSpeed= NUMBER )*
            	    loop172:
            	    do {
            	        int alt172=2;
            	        int LA172_0 = input.LA(1);

            	        if ( (LA172_0=='.'||(LA172_0 >= '0' && LA172_0 <= '9')) ) {
            	            alt172=1;
            	        }


            	        switch (alt172) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1072:14: transverseGroundSpeed= NUMBER
            	    	    {
            	    	    int transverseGroundSpeedStart4764 = getCharIndex();
            	    	    int transverseGroundSpeedStartLine4764 = getLine();
            	    	    int transverseGroundSpeedStartCharPos4764 = getCharPositionInLine();
            	    	    mNUMBER(); 
            	    	    transverseGroundSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, transverseGroundSpeedStart4764, getCharIndex()-1);
            	    	    transverseGroundSpeed.setLine(transverseGroundSpeedStartLine4764);
            	    	    transverseGroundSpeed.setCharPositionInLine(transverseGroundSpeedStartCharPos4764);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop172;
            	        }
            	    } while (true);


            	    mSEP(); 


            	    int gstatusStart4775 = getCharIndex();
            	    int gstatusStartLine4775 = getLine();
            	    int gstatusStartCharPos4775 = getCharPositionInLine();
            	    mLETTERS(); 
            	    gstatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, gstatusStart4775, getCharIndex()-1);
            	    gstatus.setLine(gstatusStartLine4775);
            	    gstatus.setCharPositionInLine(gstatusStartCharPos4775);


            	    }
            	    break;

            	default :
            	    break loop173;
                }
            } while (true);


            int checksumStart4785 = getCharIndex();
            int checksumStartLine4785 = getLine();
            int checksumStartCharPos4785 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4785, getCharIndex()-1);
            checksum.setLine(checksumStartLine4785);
            checksum.setCharPositionInLine(checksumStartCharPos4785);



            	vbw = new VBW (device.getText(), getText(),
                            longitudinalWaterSpeed != null ? (new Float(longitudinalWaterSpeed.getText())).floatValue() : 0.0f,
                            transverseWaterSpeed != null ? (new Float(transverseWaterSpeed.getText())).floatValue() : 0.0f,
                            wstatus != null ? wstatus.getText() : "",
                            longitudinalGroundSpeed != null ? (new Float(longitudinalGroundSpeed.getText())).floatValue() : 0.0f,
                            transverseGroundSpeed != null ? (new Float(transverseGroundSpeed.getText())).floatValue() : 0.0f,
                            gstatus != null ? gstatus.getText() : "");
                        handler.doIt(vbw);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VBW"

    // $ANTLR start "VLW"
    public final void mVLW() throws RecognitionException {
        try {
            int _type = VLW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken dataTotalWaterDistance=null;
            CommonToken dataTripWaterDistance=null;
            CommonToken dataTotalGroundDistance=null;
            CommonToken dataTripGroundDistance=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1089:5: ( '$' device= DEVICE 'VLW' SEP ( ' ' )* (dataTotalWaterDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripWaterDistance= NUMBER )* SEP LETTERS ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1089:7: '$' device= DEVICE 'VLW' SEP ( ' ' )* (dataTotalWaterDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripWaterDistance= NUMBER )* SEP LETTERS ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4803 = getCharIndex();
            int deviceStartLine4803 = getLine();
            int deviceStartCharPos4803 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4803, getCharIndex()-1);
            device.setLine(deviceStartLine4803);
            device.setCharPositionInLine(deviceStartCharPos4803);


            match("VLW"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1090:6: ( ' ' )*
            loop174:
            do {
                int alt174=2;
                int LA174_0 = input.LA(1);

                if ( (LA174_0==' ') ) {
                    alt174=1;
                }


                switch (alt174) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1090:6: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop174;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1090:11: (dataTotalWaterDistance= NUMBER )*
            loop175:
            do {
                int alt175=2;
                int LA175_0 = input.LA(1);

                if ( (LA175_0=='.'||(LA175_0 >= '0' && LA175_0 <= '9')) ) {
                    alt175=1;
                }


                switch (alt175) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1090:12: dataTotalWaterDistance= NUMBER
            	    {
            	    int dataTotalWaterDistanceStart4822 = getCharIndex();
            	    int dataTotalWaterDistanceStartLine4822 = getLine();
            	    int dataTotalWaterDistanceStartCharPos4822 = getCharPositionInLine();
            	    mNUMBER(); 
            	    dataTotalWaterDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTotalWaterDistanceStart4822, getCharIndex()-1);
            	    dataTotalWaterDistance.setLine(dataTotalWaterDistanceStartLine4822);
            	    dataTotalWaterDistance.setCharPositionInLine(dataTotalWaterDistanceStartCharPos4822);


            	    }
            	    break;

            	default :
            	    break loop175;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1092:6: ( ' ' )*
            loop176:
            do {
                int alt176=2;
                int LA176_0 = input.LA(1);

                if ( (LA176_0==' ') ) {
                    alt176=1;
                }


                switch (alt176) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1092:6: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop176;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1092:11: (dataTripWaterDistance= NUMBER )*
            loop177:
            do {
                int alt177=2;
                int LA177_0 = input.LA(1);

                if ( (LA177_0=='.'||(LA177_0 >= '0' && LA177_0 <= '9')) ) {
                    alt177=1;
                }


                switch (alt177) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1092:12: dataTripWaterDistance= NUMBER
            	    {
            	    int dataTripWaterDistanceStart4850 = getCharIndex();
            	    int dataTripWaterDistanceStartLine4850 = getLine();
            	    int dataTripWaterDistanceStartCharPos4850 = getCharPositionInLine();
            	    mNUMBER(); 
            	    dataTripWaterDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTripWaterDistanceStart4850, getCharIndex()-1);
            	    dataTripWaterDistance.setLine(dataTripWaterDistanceStartLine4850);
            	    dataTripWaterDistance.setCharPositionInLine(dataTripWaterDistanceStartCharPos4850);


            	    }
            	    break;

            	default :
            	    break loop177;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:6: ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )*
            loop182:
            do {
                int alt182=2;
                int LA182_0 = input.LA(1);

                if ( (LA182_0==',') ) {
                    alt182=1;
                }


                switch (alt182) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:7: SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS
            	    {
            	    mSEP(); 


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:11: ( ' ' )*
            	    loop178:
            	    do {
            	        int alt178=2;
            	        int LA178_0 = input.LA(1);

            	        if ( (LA178_0==' ') ) {
            	            alt178=1;
            	        }


            	        switch (alt178) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:11: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop178;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:16: (dataTotalGroundDistance= NUMBER )*
            	    loop179:
            	    do {
            	        int alt179=2;
            	        int LA179_0 = input.LA(1);

            	        if ( (LA179_0=='.'||(LA179_0 >= '0' && LA179_0 <= '9')) ) {
            	            alt179=1;
            	        }


            	        switch (alt179) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1094:17: dataTotalGroundDistance= NUMBER
            	    	    {
            	    	    int dataTotalGroundDistanceStart4880 = getCharIndex();
            	    	    int dataTotalGroundDistanceStartLine4880 = getLine();
            	    	    int dataTotalGroundDistanceStartCharPos4880 = getCharPositionInLine();
            	    	    mNUMBER(); 
            	    	    dataTotalGroundDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTotalGroundDistanceStart4880, getCharIndex()-1);
            	    	    dataTotalGroundDistance.setLine(dataTotalGroundDistanceStartLine4880);
            	    	    dataTotalGroundDistance.setCharPositionInLine(dataTotalGroundDistanceStartCharPos4880);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop179;
            	        }
            	    } while (true);


            	    mSEP(); 


            	    mLETTERS(); 


            	    mSEP(); 


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1096:6: ( ' ' )*
            	    loop180:
            	    do {
            	        int alt180=2;
            	        int LA180_0 = input.LA(1);

            	        if ( (LA180_0==' ') ) {
            	            alt180=1;
            	        }


            	        switch (alt180) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1096:6: ' '
            	    	    {
            	    	    match(' '); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop180;
            	        }
            	    } while (true);


            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1096:11: (dataTripGroundDistance= NUMBER )*
            	    loop181:
            	    do {
            	        int alt181=2;
            	        int LA181_0 = input.LA(1);

            	        if ( (LA181_0=='.'||(LA181_0 >= '0' && LA181_0 <= '9')) ) {
            	            alt181=1;
            	        }


            	        switch (alt181) {
            	    	case 1 :
            	    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1096:12: dataTripGroundDistance= NUMBER
            	    	    {
            	    	    int dataTripGroundDistanceStart4908 = getCharIndex();
            	    	    int dataTripGroundDistanceStartLine4908 = getLine();
            	    	    int dataTripGroundDistanceStartCharPos4908 = getCharPositionInLine();
            	    	    mNUMBER(); 
            	    	    dataTripGroundDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTripGroundDistanceStart4908, getCharIndex()-1);
            	    	    dataTripGroundDistance.setLine(dataTripGroundDistanceStartLine4908);
            	    	    dataTripGroundDistance.setCharPositionInLine(dataTripGroundDistanceStartCharPos4908);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop181;
            	        }
            	    } while (true);


            	    mSEP(); 


            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop182;
                }
            } while (true);


            int checksumStart4937 = getCharIndex();
            int checksumStartLine4937 = getLine();
            int checksumStartCharPos4937 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4937, getCharIndex()-1);
            checksum.setLine(checksumStartLine4937);
            checksum.setCharPositionInLine(checksumStartCharPos4937);



            	      vlw = new VLW (device.getText(), getText(),
                            dataTotalWaterDistance != null ? (new Float(dataTotalWaterDistance.getText())).floatValue() : 0.0f,
                            dataTripWaterDistance != null ? (new Float(dataTripWaterDistance.getText())).floatValue() : 0.0f,
                            dataTotalGroundDistance != null ? (new Float(dataTotalGroundDistance.getText())).floatValue() : 0.0f,
                            dataTripGroundDistance != null ? (new Float(dataTripGroundDistance.getText())).floatValue() : 0.0f);
                        handler.doIt(vlw);
            	    

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VLW"

    // $ANTLR start "VHW"
    public final void mVHW() throws RecognitionException {
        try {
            int _type = VHW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken degreesTrue=null;
            CommonToken degreesMagnetic=null;
            CommonToken speedInKnots=null;
            CommonToken speedInKilometers=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1117:5: ( '$' device= DEVICE 'VHW' SEP ( ' ' )* (degreesTrue= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (degreesMagnetic= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKnots= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKilometers= NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1117:8: '$' device= DEVICE 'VHW' SEP ( ' ' )* (degreesTrue= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (degreesMagnetic= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKnots= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKilometers= NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart4960 = getCharIndex();
            int deviceStartLine4960 = getLine();
            int deviceStartCharPos4960 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4960, getCharIndex()-1);
            device.setLine(deviceStartLine4960);
            device.setCharPositionInLine(deviceStartCharPos4960);


            match("VHW"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1118:6: ( ' ' )*
            loop183:
            do {
                int alt183=2;
                int LA183_0 = input.LA(1);

                if ( (LA183_0==' ') ) {
                    alt183=1;
                }


                switch (alt183) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1118:6: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop183;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1118:11: (degreesTrue= NUMBER )*
            loop184:
            do {
                int alt184=2;
                int LA184_0 = input.LA(1);

                if ( (LA184_0=='.'||(LA184_0 >= '0' && LA184_0 <= '9')) ) {
                    alt184=1;
                }


                switch (alt184) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1118:12: degreesTrue= NUMBER
            	    {
            	    int degreesTrueStart4979 = getCharIndex();
            	    int degreesTrueStartLine4979 = getLine();
            	    int degreesTrueStartCharPos4979 = getCharPositionInLine();
            	    mNUMBER(); 
            	    degreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, degreesTrueStart4979, getCharIndex()-1);
            	    degreesTrue.setLine(degreesTrueStartLine4979);
            	    degreesTrue.setCharPositionInLine(degreesTrueStartCharPos4979);


            	    }
            	    break;

            	default :
            	    break loop184;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1119:13: ( LETTERS )*
            loop185:
            do {
                int alt185=2;
                int LA185_0 = input.LA(1);

                if ( (LA185_0==' '||(LA185_0 >= 'A' && LA185_0 <= 'Z')||(LA185_0 >= 'a' && LA185_0 <= 'z')) ) {
                    alt185=1;
                }


                switch (alt185) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1119:13: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop185;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1120:13: ( ' ' )*
            loop186:
            do {
                int alt186=2;
                int LA186_0 = input.LA(1);

                if ( (LA186_0==' ') ) {
                    alt186=1;
                }


                switch (alt186) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1120:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop186;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1120:18: (degreesMagnetic= NUMBER )*
            loop187:
            do {
                int alt187=2;
                int LA187_0 = input.LA(1);

                if ( (LA187_0=='.'||(LA187_0 >= '0' && LA187_0 <= '9')) ) {
                    alt187=1;
                }


                switch (alt187) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1120:19: degreesMagnetic= NUMBER
            	    {
            	    int degreesMagneticStart5022 = getCharIndex();
            	    int degreesMagneticStartLine5022 = getLine();
            	    int degreesMagneticStartCharPos5022 = getCharPositionInLine();
            	    mNUMBER(); 
            	    degreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, degreesMagneticStart5022, getCharIndex()-1);
            	    degreesMagnetic.setLine(degreesMagneticStartLine5022);
            	    degreesMagnetic.setCharPositionInLine(degreesMagneticStartCharPos5022);


            	    }
            	    break;

            	default :
            	    break loop187;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1121:13: ( LETTERS )*
            loop188:
            do {
                int alt188=2;
                int LA188_0 = input.LA(1);

                if ( (LA188_0==' '||(LA188_0 >= 'A' && LA188_0 <= 'Z')||(LA188_0 >= 'a' && LA188_0 <= 'z')) ) {
                    alt188=1;
                }


                switch (alt188) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1121:13: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop188;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1122:13: ( ' ' )*
            loop189:
            do {
                int alt189=2;
                int LA189_0 = input.LA(1);

                if ( (LA189_0==' ') ) {
                    alt189=1;
                }


                switch (alt189) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1122:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop189;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1122:18: (speedInKnots= NUMBER )*
            loop190:
            do {
                int alt190=2;
                int LA190_0 = input.LA(1);

                if ( (LA190_0=='.'||(LA190_0 >= '0' && LA190_0 <= '9')) ) {
                    alt190=1;
                }


                switch (alt190) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1122:19: speedInKnots= NUMBER
            	    {
            	    int speedInKnotsStart5065 = getCharIndex();
            	    int speedInKnotsStartLine5065 = getLine();
            	    int speedInKnotsStartCharPos5065 = getCharPositionInLine();
            	    mNUMBER(); 
            	    speedInKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKnotsStart5065, getCharIndex()-1);
            	    speedInKnots.setLine(speedInKnotsStartLine5065);
            	    speedInKnots.setCharPositionInLine(speedInKnotsStartCharPos5065);


            	    }
            	    break;

            	default :
            	    break loop190;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1123:13: ( LETTERS )*
            loop191:
            do {
                int alt191=2;
                int LA191_0 = input.LA(1);

                if ( (LA191_0==' '||(LA191_0 >= 'A' && LA191_0 <= 'Z')||(LA191_0 >= 'a' && LA191_0 <= 'z')) ) {
                    alt191=1;
                }


                switch (alt191) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1123:13: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop191;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1124:13: ( ' ' )*
            loop192:
            do {
                int alt192=2;
                int LA192_0 = input.LA(1);

                if ( (LA192_0==' ') ) {
                    alt192=1;
                }


                switch (alt192) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1124:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop192;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1124:18: (speedInKilometers= NUMBER )*
            loop193:
            do {
                int alt193=2;
                int LA193_0 = input.LA(1);

                if ( (LA193_0=='.'||(LA193_0 >= '0' && LA193_0 <= '9')) ) {
                    alt193=1;
                }


                switch (alt193) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1124:19: speedInKilometers= NUMBER
            	    {
            	    int speedInKilometersStart5108 = getCharIndex();
            	    int speedInKilometersStartLine5108 = getLine();
            	    int speedInKilometersStartCharPos5108 = getCharPositionInLine();
            	    mNUMBER(); 
            	    speedInKilometers = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKilometersStart5108, getCharIndex()-1);
            	    speedInKilometers.setLine(speedInKilometersStartLine5108);
            	    speedInKilometers.setCharPositionInLine(speedInKilometersStartCharPos5108);


            	    }
            	    break;

            	default :
            	    break loop193;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1125:13: ( LETTERS )*
            loop194:
            do {
                int alt194=2;
                int LA194_0 = input.LA(1);

                if ( (LA194_0==' '||(LA194_0 >= 'A' && LA194_0 <= 'Z')||(LA194_0 >= 'a' && LA194_0 <= 'z')) ) {
                    alt194=1;
                }


                switch (alt194) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1125:13: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop194;
                }
            } while (true);


            int checksumStart5143 = getCharIndex();
            int checksumStartLine5143 = getLine();
            int checksumStartCharPos5143 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5143, getCharIndex()-1);
            checksum.setLine(checksumStartLine5143);
            checksum.setCharPositionInLine(checksumStartCharPos5143);



                        vhw = new VHW (device.getText(), getText(),
                            degreesTrue != null ? (new Float(degreesTrue.getText())).floatValue() : 0.0f,
                            degreesMagnetic != null ? (new Float(degreesMagnetic.getText())).floatValue() : 0.0f,
                            speedInKnots != null ? (new Float(speedInKnots.getText())).floatValue() : 0.0f,
                            speedInKilometers != null ? (new Float(speedInKilometers.getText())).floatValue() : 0.0f);
                        handler.doIt(vhw);
                        

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VHW"

    // $ANTLR start "VPW"
    public final void mVPW() throws RecognitionException {
        try {
            int _type = VPW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken speed=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1143:6: ( '$' device= DEVICE 'VPW' SEP ( ' ' )* (speed= NUMBER |speed= SIGN )* SEP LETTERS SEP ( ' ' )* ( NUMBER | '-' )* SEP ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1143:9: '$' device= DEVICE 'VPW' SEP ( ' ' )* (speed= NUMBER |speed= SIGN )* SEP LETTERS SEP ( ' ' )* ( NUMBER | '-' )* SEP ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5172 = getCharIndex();
            int deviceStartLine5172 = getLine();
            int deviceStartCharPos5172 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5172, getCharIndex()-1);
            device.setLine(deviceStartLine5172);
            device.setCharPositionInLine(deviceStartCharPos5172);


            match("VPW"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1144:2: ( ' ' )*
            loop195:
            do {
                int alt195=2;
                int LA195_0 = input.LA(1);

                if ( (LA195_0==' ') ) {
                    alt195=1;
                }


                switch (alt195) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1144:2: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop195;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1144:7: (speed= NUMBER |speed= SIGN )*
            loop196:
            do {
                int alt196=3;
                int LA196_0 = input.LA(1);

                if ( (LA196_0=='.'||(LA196_0 >= '0' && LA196_0 <= '9')) ) {
                    alt196=1;
                }
                else if ( (LA196_0=='+'||LA196_0=='-') ) {
                    alt196=2;
                }


                switch (alt196) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1144:8: speed= NUMBER
            	    {
            	    int speedStart5187 = getCharIndex();
            	    int speedStartLine5187 = getLine();
            	    int speedStartCharPos5187 = getCharPositionInLine();
            	    mNUMBER(); 
            	    speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart5187, getCharIndex()-1);
            	    speed.setLine(speedStartLine5187);
            	    speed.setCharPositionInLine(speedStartCharPos5187);


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1144:25: speed= SIGN
            	    {
            	    int speedStart5195 = getCharIndex();
            	    int speedStartLine5195 = getLine();
            	    int speedStartCharPos5195 = getCharPositionInLine();
            	    mSIGN(); 
            	    speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart5195, getCharIndex()-1);
            	    speed.setLine(speedStartLine5195);
            	    speed.setCharPositionInLine(speedStartCharPos5195);


            	    }
            	    break;

            	default :
            	    break loop196;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1146:3: ( ' ' )*
            loop197:
            do {
                int alt197=2;
                int LA197_0 = input.LA(1);

                if ( (LA197_0==' ') ) {
                    alt197=1;
                }


                switch (alt197) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1146:3: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop197;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1146:8: ( NUMBER | '-' )*
            loop198:
            do {
                int alt198=3;
                int LA198_0 = input.LA(1);

                if ( (LA198_0=='.'||(LA198_0 >= '0' && LA198_0 <= '9')) ) {
                    alt198=1;
                }
                else if ( (LA198_0=='-') ) {
                    alt198=2;
                }


                switch (alt198) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1146:9: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1146:18: '-'
            	    {
            	    match('-'); 

            	    }
            	    break;

            	default :
            	    break loop198;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1147:10: ( LETTERS )*
            loop199:
            do {
                int alt199=2;
                int LA199_0 = input.LA(1);

                if ( (LA199_0==' '||(LA199_0 >= 'A' && LA199_0 <= 'Z')||(LA199_0 >= 'a' && LA199_0 <= 'z')) ) {
                    alt199=1;
                }


                switch (alt199) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1147:10: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop199;
                }
            } while (true);


            int checksumStart5246 = getCharIndex();
            int checksumStartLine5246 = getLine();
            int checksumStartCharPos5246 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5246, getCharIndex()-1);
            checksum.setLine(checksumStartLine5246);
            checksum.setCharPositionInLine(checksumStartCharPos5246);



            	 vpw = new VPW (device.getText(), getText(),
            	  speed != null ? (new Float(speed.getText())).floatValue() : 0.0f);
            	  
            	   handler.doIt(vpw);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VPW"

    // $ANTLR start "VTG"
    public final void mVTG() throws RecognitionException {
        try {
            int _type = VTG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken trueTrackMadeGoodDegrees=null;
            CommonToken magneticTrackMadeGood=null;
            CommonToken groundSpeedKnots=null;
            CommonToken groundSpeedKph=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1159:6: ( '$' device= DEVICE 'VTG' SEP ( ' ' )* (trueTrackMadeGoodDegrees= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (magneticTrackMadeGood= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKnots= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKph= NUMBER )* SEP ( LETTERS SEP )* ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1159:9: '$' device= DEVICE 'VTG' SEP ( ' ' )* (trueTrackMadeGoodDegrees= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (magneticTrackMadeGood= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKnots= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKph= NUMBER )* SEP ( LETTERS SEP )* ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5266 = getCharIndex();
            int deviceStartLine5266 = getLine();
            int deviceStartCharPos5266 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5266, getCharIndex()-1);
            device.setLine(deviceStartLine5266);
            device.setCharPositionInLine(deviceStartCharPos5266);


            match("VTG"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1160:13: ( ' ' )*
            loop200:
            do {
                int alt200=2;
                int LA200_0 = input.LA(1);

                if ( (LA200_0==' ') ) {
                    alt200=1;
                }


                switch (alt200) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1160:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop200;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1160:18: (trueTrackMadeGoodDegrees= NUMBER )*
            loop201:
            do {
                int alt201=2;
                int LA201_0 = input.LA(1);

                if ( (LA201_0=='.'||(LA201_0 >= '0' && LA201_0 <= '9')) ) {
                    alt201=1;
                }


                switch (alt201) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1160:19: trueTrackMadeGoodDegrees= NUMBER
            	    {
            	    int trueTrackMadeGoodDegreesStart5292 = getCharIndex();
            	    int trueTrackMadeGoodDegreesStartLine5292 = getLine();
            	    int trueTrackMadeGoodDegreesStartCharPos5292 = getCharPositionInLine();
            	    mNUMBER(); 
            	    trueTrackMadeGoodDegrees = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, trueTrackMadeGoodDegreesStart5292, getCharIndex()-1);
            	    trueTrackMadeGoodDegrees.setLine(trueTrackMadeGoodDegreesStartLine5292);
            	    trueTrackMadeGoodDegrees.setCharPositionInLine(trueTrackMadeGoodDegreesStartCharPos5292);


            	    }
            	    break;

            	default :
            	    break loop201;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1161:13: ( LETTERS )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1161:14: LETTERS
            {
            mLETTERS(); 


            }


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1162:13: ( ' ' )*
            loop202:
            do {
                int alt202=2;
                int LA202_0 = input.LA(1);

                if ( (LA202_0==' ') ) {
                    alt202=1;
                }


                switch (alt202) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1162:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop202;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1162:18: (magneticTrackMadeGood= NUMBER )*
            loop203:
            do {
                int alt203=2;
                int LA203_0 = input.LA(1);

                if ( (LA203_0=='.'||(LA203_0 >= '0' && LA203_0 <= '9')) ) {
                    alt203=1;
                }


                switch (alt203) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1162:19: magneticTrackMadeGood= NUMBER
            	    {
            	    int magneticTrackMadeGoodStart5336 = getCharIndex();
            	    int magneticTrackMadeGoodStartLine5336 = getLine();
            	    int magneticTrackMadeGoodStartCharPos5336 = getCharPositionInLine();
            	    mNUMBER(); 
            	    magneticTrackMadeGood = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, magneticTrackMadeGoodStart5336, getCharIndex()-1);
            	    magneticTrackMadeGood.setLine(magneticTrackMadeGoodStartLine5336);
            	    magneticTrackMadeGood.setCharPositionInLine(magneticTrackMadeGoodStartCharPos5336);


            	    }
            	    break;

            	default :
            	    break loop203;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1163:13: ( LETTERS )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1163:14: LETTERS
            {
            mLETTERS(); 


            }


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1164:13: ( ' ' )*
            loop204:
            do {
                int alt204=2;
                int LA204_0 = input.LA(1);

                if ( (LA204_0==' ') ) {
                    alt204=1;
                }


                switch (alt204) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1164:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop204;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1164:18: (groundSpeedKnots= NUMBER )*
            loop205:
            do {
                int alt205=2;
                int LA205_0 = input.LA(1);

                if ( (LA205_0=='.'||(LA205_0 >= '0' && LA205_0 <= '9')) ) {
                    alt205=1;
                }


                switch (alt205) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1164:19: groundSpeedKnots= NUMBER
            	    {
            	    int groundSpeedKnotsStart5380 = getCharIndex();
            	    int groundSpeedKnotsStartLine5380 = getLine();
            	    int groundSpeedKnotsStartCharPos5380 = getCharPositionInLine();
            	    mNUMBER(); 
            	    groundSpeedKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, groundSpeedKnotsStart5380, getCharIndex()-1);
            	    groundSpeedKnots.setLine(groundSpeedKnotsStartLine5380);
            	    groundSpeedKnots.setCharPositionInLine(groundSpeedKnotsStartCharPos5380);


            	    }
            	    break;

            	default :
            	    break loop205;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1165:13: ( LETTERS )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1165:14: LETTERS
            {
            mLETTERS(); 


            }


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1166:13: ( ' ' )*
            loop206:
            do {
                int alt206=2;
                int LA206_0 = input.LA(1);

                if ( (LA206_0==' ') ) {
                    alt206=1;
                }


                switch (alt206) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1166:13: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;

            	default :
            	    break loop206;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1166:18: (groundSpeedKph= NUMBER )*
            loop207:
            do {
                int alt207=2;
                int LA207_0 = input.LA(1);

                if ( (LA207_0=='.'||(LA207_0 >= '0' && LA207_0 <= '9')) ) {
                    alt207=1;
                }


                switch (alt207) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1166:19: groundSpeedKph= NUMBER
            	    {
            	    int groundSpeedKphStart5424 = getCharIndex();
            	    int groundSpeedKphStartLine5424 = getLine();
            	    int groundSpeedKphStartCharPos5424 = getCharPositionInLine();
            	    mNUMBER(); 
            	    groundSpeedKph = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, groundSpeedKphStart5424, getCharIndex()-1);
            	    groundSpeedKph.setLine(groundSpeedKphStartLine5424);
            	    groundSpeedKph.setCharPositionInLine(groundSpeedKphStartCharPos5424);


            	    }
            	    break;

            	default :
            	    break loop207;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1167:13: ( LETTERS SEP )*
            loop208:
            do {
                int alt208=2;
                alt208 = dfa208.predict(input);
                switch (alt208) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1167:14: LETTERS SEP
            	    {
            	    mLETTERS(); 


            	    mSEP(); 


            	    }
            	    break;

            	default :
            	    break loop208;
                }
            } while (true);


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1168:13: ( LETTERS )*
            loop209:
            do {
                int alt209=2;
                int LA209_0 = input.LA(1);

                if ( (LA209_0==' '||(LA209_0 >= 'A' && LA209_0 <= 'Z')||(LA209_0 >= 'a' && LA209_0 <= 'z')) ) {
                    alt209=1;
                }


                switch (alt209) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1168:13: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop209;
                }
            } while (true);


            int checksumStart5478 = getCharIndex();
            int checksumStartLine5478 = getLine();
            int checksumStartCharPos5478 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5478, getCharIndex()-1);
            checksum.setLine(checksumStartLine5478);
            checksum.setCharPositionInLine(checksumStartCharPos5478);



                        vtg = new VTG (device.getText(), getText(),
                            trueTrackMadeGoodDegrees != null ? (new Float(trueTrackMadeGoodDegrees.getText())).floatValue() : 0.0f,
                            magneticTrackMadeGood != null ? (new Float(magneticTrackMadeGood.getText())).floatValue() : 0.0f,
                            groundSpeedKnots != null ? (new Float(groundSpeedKnots.getText())).floatValue() : 0.0f);
                            
                       handler.doIt(vtg);
                        

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VTG"

    // $ANTLR start "VWR"
    public final void mVWR() throws RecognitionException {
        try {
            int _type = VWR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken windDirectionMagnitude=null;
            CommonToken windDirectionOfBow=null;
            CommonToken speedInKnots=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1185:6: ( '$' device= DEVICE 'VWR' SEP (windDirectionMagnitude= NUMBER )* SEP windDirectionOfBow= LETTERS SEP (speedInKnots= NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1185:8: '$' device= DEVICE 'VWR' SEP (windDirectionMagnitude= NUMBER )* SEP windDirectionOfBow= LETTERS SEP (speedInKnots= NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5508 = getCharIndex();
            int deviceStartLine5508 = getLine();
            int deviceStartCharPos5508 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5508, getCharIndex()-1);
            device.setLine(deviceStartLine5508);
            device.setCharPositionInLine(deviceStartCharPos5508);


            match("VWR"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1186:27: (windDirectionMagnitude= NUMBER )*
            loop210:
            do {
                int alt210=2;
                int LA210_0 = input.LA(1);

                if ( (LA210_0=='.'||(LA210_0 >= '0' && LA210_0 <= '9')) ) {
                    alt210=1;
                }


                switch (alt210) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1186:27: windDirectionMagnitude= NUMBER
            	    {
            	    int windDirectionMagnitudeStart5521 = getCharIndex();
            	    int windDirectionMagnitudeStartLine5521 = getLine();
            	    int windDirectionMagnitudeStartCharPos5521 = getCharPositionInLine();
            	    mNUMBER(); 
            	    windDirectionMagnitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionMagnitudeStart5521, getCharIndex()-1);
            	    windDirectionMagnitude.setLine(windDirectionMagnitudeStartLine5521);
            	    windDirectionMagnitude.setCharPositionInLine(windDirectionMagnitudeStartCharPos5521);


            	    }
            	    break;

            	default :
            	    break loop210;
                }
            } while (true);


            mSEP(); 


            int windDirectionOfBowStart5533 = getCharIndex();
            int windDirectionOfBowStartLine5533 = getLine();
            int windDirectionOfBowStartCharPos5533 = getCharPositionInLine();
            mLETTERS(); 
            windDirectionOfBow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionOfBowStart5533, getCharIndex()-1);
            windDirectionOfBow.setLine(windDirectionOfBowStartLine5533);
            windDirectionOfBow.setCharPositionInLine(windDirectionOfBowStartCharPos5533);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1188:16: (speedInKnots= NUMBER )*
            loop211:
            do {
                int alt211=2;
                int LA211_0 = input.LA(1);

                if ( (LA211_0=='.'||(LA211_0 >= '0' && LA211_0 <= '9')) ) {
                    alt211=1;
                }


                switch (alt211) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1188:16: speedInKnots= NUMBER
            	    {
            	    int speedInKnotsStart5543 = getCharIndex();
            	    int speedInKnotsStartLine5543 = getLine();
            	    int speedInKnotsStartCharPos5543 = getCharPositionInLine();
            	    mNUMBER(); 
            	    speedInKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKnotsStart5543, getCharIndex()-1);
            	    speedInKnots.setLine(speedInKnotsStartLine5543);
            	    speedInKnots.setCharPositionInLine(speedInKnotsStartCharPos5543);


            	    }
            	    break;

            	default :
            	    break loop211;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1190:3: ( NUMBER )*
            loop212:
            do {
                int alt212=2;
                int LA212_0 = input.LA(1);

                if ( (LA212_0=='.'||(LA212_0 >= '0' && LA212_0 <= '9')) ) {
                    alt212=1;
                }


                switch (alt212) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1190:3: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop212;
                }
            } while (true);


            mSEP(); 


            mLETTERS(); 


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1192:3: ( NUMBER )*
            loop213:
            do {
                int alt213=2;
                int LA213_0 = input.LA(1);

                if ( (LA213_0=='.'||(LA213_0 >= '0' && LA213_0 <= '9')) ) {
                    alt213=1;
                }


                switch (alt213) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1192:3: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop213;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1193:3: ( LETTERS )*
            loop214:
            do {
                int alt214=2;
                int LA214_0 = input.LA(1);

                if ( (LA214_0==' '||(LA214_0 >= 'A' && LA214_0 <= 'Z')||(LA214_0 >= 'a' && LA214_0 <= 'z')) ) {
                    alt214=1;
                }


                switch (alt214) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1193:3: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop214;
                }
            } while (true);


            int checksumStart5584 = getCharIndex();
            int checksumStartLine5584 = getLine();
            int checksumStartCharPos5584 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5584, getCharIndex()-1);
            checksum.setLine(checksumStartLine5584);
            checksum.setCharPositionInLine(checksumStartCharPos5584);



            		vwr = new VWR(device.getText(), getText(),
            			windDirectionMagnitude != null ? (new Integer(windDirectionMagnitude.getText())).intValue() : 0,
            			windDirectionOfBow != null ? windDirectionOfBow.getText() : "",
            			speedInKnots != null ? (new Float(speedInKnots.getText())).floatValue() : 0.0f);
            			 handler.doIt(vwr);
            		

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VWR"

    // $ANTLR start "VWT"
    public final void mVWT() throws RecognitionException {
        try {
            int _type = VWT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1210:5: ( '$' device= DEVICE 'VWT' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1210:7: '$' device= DEVICE 'VWT' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5607 = getCharIndex();
            int deviceStartLine5607 = getLine();
            int deviceStartCharPos5607 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5607, getCharIndex()-1);
            device.setLine(deviceStartLine5607);
            device.setCharPositionInLine(deviceStartCharPos5607);


            match("VWT"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1211:2: ( '\\u0021' .. '\\u007F' )+
            int cnt215=0;
            loop215:
            do {
                int alt215=2;
                alt215 = dfa215.predict(input);
                switch (alt215) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= '!' && input.LA(1) <= '\u007F') ) {
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
            	    if ( cnt215 >= 1 ) break loop215;
                        EarlyExitException eee =
                            new EarlyExitException(215, input);
                        throw eee;
                }
                cnt215++;
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1212:5: ( NUMBER )*
            loop216:
            do {
                int alt216=2;
                int LA216_0 = input.LA(1);

                if ( (LA216_0=='.'||(LA216_0 >= '0' && LA216_0 <= '9')) ) {
                    alt216=1;
                }


                switch (alt216) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1212:5: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop216;
                }
            } while (true);


            int checksumStart5637 = getCharIndex();
            int checksumStartLine5637 = getLine();
            int checksumStartCharPos5637 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5637, getCharIndex()-1);
            checksum.setLine(checksumStartLine5637);
            checksum.setCharPositionInLine(checksumStartCharPos5637);



            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VWT"

    // $ANTLR start "XTE"
    public final void mXTE() throws RecognitionException {
        try {
            int _type = XTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken generalWarning=null;
            CommonToken status=null;
            CommonToken crossTrackError=null;
            CommonToken directionToSteer=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1250:6: ( '$' device= DEVICE 'XTE' SEP generalWarning= LETTERS SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP directionToSteer= LETTERS SEP LETTERS checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1250:8: '$' device= DEVICE 'XTE' SEP generalWarning= LETTERS SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP directionToSteer= LETTERS SEP LETTERS checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5656 = getCharIndex();
            int deviceStartLine5656 = getLine();
            int deviceStartCharPos5656 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5656, getCharIndex()-1);
            device.setLine(deviceStartLine5656);
            device.setCharPositionInLine(deviceStartCharPos5656);


            match("XTE"); 



            mSEP(); 


            int generalWarningStart5665 = getCharIndex();
            int generalWarningStartLine5665 = getLine();
            int generalWarningStartCharPos5665 = getCharPositionInLine();
            mLETTERS(); 
            generalWarning = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, generalWarningStart5665, getCharIndex()-1);
            generalWarning.setLine(generalWarningStartLine5665);
            generalWarning.setCharPositionInLine(generalWarningStartCharPos5665);


            mSEP(); 


            int statusStart5672 = getCharIndex();
            int statusStartLine5672 = getLine();
            int statusStartCharPos5672 = getCharPositionInLine();
            mLETTERS(); 
            status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart5672, getCharIndex()-1);
            status.setLine(statusStartLine5672);
            status.setCharPositionInLine(statusStartCharPos5672);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1253:17: (crossTrackError= NUMBER )*
            loop217:
            do {
                int alt217=2;
                int LA217_0 = input.LA(1);

                if ( (LA217_0=='.'||(LA217_0 >= '0' && LA217_0 <= '9')) ) {
                    alt217=1;
                }


                switch (alt217) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1253:17: crossTrackError= NUMBER
            	    {
            	    int crossTrackErrorStart5679 = getCharIndex();
            	    int crossTrackErrorStartLine5679 = getLine();
            	    int crossTrackErrorStartCharPos5679 = getCharPositionInLine();
            	    mNUMBER(); 
            	    crossTrackError = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorStart5679, getCharIndex()-1);
            	    crossTrackError.setLine(crossTrackErrorStartLine5679);
            	    crossTrackError.setCharPositionInLine(crossTrackErrorStartCharPos5679);


            	    }
            	    break;

            	default :
            	    break loop217;
                }
            } while (true);


            mSEP(); 


            int directionToSteerStart5687 = getCharIndex();
            int directionToSteerStartLine5687 = getLine();
            int directionToSteerStartCharPos5687 = getCharPositionInLine();
            mLETTERS(); 
            directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart5687, getCharIndex()-1);
            directionToSteer.setLine(directionToSteerStartLine5687);
            directionToSteer.setCharPositionInLine(directionToSteerStartCharPos5687);


            mSEP(); 


            mLETTERS(); 


            int checksumStart5697 = getCharIndex();
            int checksumStartLine5697 = getLine();
            int checksumStartCharPos5697 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5697, getCharIndex()-1);
            checksum.setLine(checksumStartLine5697);
            checksum.setCharPositionInLine(checksumStartCharPos5697);



            	xte = new XTE(device.getText(), getText(),
            	generalWarning.getText(),
            	status.getText(),
            	crossTrackError!= null ? (new Float(crossTrackError.getText())).floatValue() : 0.0f,
            	directionToSteer.getText());
            	handler.doIt(xte);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XTE"

    // $ANTLR start "ZDA"
    public final void mZDA() throws RecognitionException {
        try {
            int _type = ZDA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken utc=null;
            CommonToken dd=null;
            CommonToken mm=null;
            CommonToken yy=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1253:6: ( '$' device= DEVICE 'ZDA' SEP utc= NUMBER SEP dd= NUMBER SEP mm= NUMBER SEP yy= NUMBER SEP ( NUMBER )* SEP ( NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1253:9: '$' device= DEVICE 'ZDA' SEP utc= NUMBER SEP dd= NUMBER SEP mm= NUMBER SEP yy= NUMBER SEP ( NUMBER )* SEP ( NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5720 = getCharIndex();
            int deviceStartLine5720 = getLine();
            int deviceStartCharPos5720 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5720, getCharIndex()-1);
            device.setLine(deviceStartLine5720);
            device.setCharPositionInLine(deviceStartCharPos5720);


            match("ZDA"); 



            mSEP(); 


            int utcStart5732 = getCharIndex();
            int utcStartLine5732 = getLine();
            int utcStartCharPos5732 = getCharPositionInLine();
            mNUMBER(); 
            utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart5732, getCharIndex()-1);
            utc.setLine(utcStartLine5732);
            utc.setCharPositionInLine(utcStartCharPos5732);


            mSEP(); 


            int ddStart5742 = getCharIndex();
            int ddStartLine5742 = getLine();
            int ddStartCharPos5742 = getCharPositionInLine();
            mNUMBER(); 
            dd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ddStart5742, getCharIndex()-1);
            dd.setLine(ddStartLine5742);
            dd.setCharPositionInLine(ddStartCharPos5742);


            mSEP(); 


            int mmStart5752 = getCharIndex();
            int mmStartLine5752 = getLine();
            int mmStartCharPos5752 = getCharPositionInLine();
            mNUMBER(); 
            mm = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mmStart5752, getCharIndex()-1);
            mm.setLine(mmStartLine5752);
            mm.setCharPositionInLine(mmStartCharPos5752);


            mSEP(); 


            int yyStart5762 = getCharIndex();
            int yyStartLine5762 = getLine();
            int yyStartCharPos5762 = getCharPositionInLine();
            mNUMBER(); 
            yy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, yyStart5762, getCharIndex()-1);
            yy.setLine(yyStartLine5762);
            yy.setCharPositionInLine(yyStartCharPos5762);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1258:3: ( NUMBER )*
            loop218:
            do {
                int alt218=2;
                int LA218_0 = input.LA(1);

                if ( (LA218_0=='.'||(LA218_0 >= '0' && LA218_0 <= '9')) ) {
                    alt218=1;
                }


                switch (alt218) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1258:3: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop218;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1259:3: ( NUMBER )*
            loop219:
            do {
                int alt219=2;
                int LA219_0 = input.LA(1);

                if ( (LA219_0=='.'||(LA219_0 >= '0' && LA219_0 <= '9')) ) {
                    alt219=1;
                }


                switch (alt219) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1259:3: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop219;
                }
            } while (true);


            int checksumStart5783 = getCharIndex();
            int checksumStartLine5783 = getLine();
            int checksumStartCharPos5783 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5783, getCharIndex()-1);
            checksum.setLine(checksumStartLine5783);
            checksum.setCharPositionInLine(checksumStartCharPos5783);



            	 date = dateFactory(utc.getText());
            	 date.set(Calendar.YEAR, (new Integer(yy.getText()).intValue()));
                     date.set(Calendar.MONTH, (new Integer(mm.getText()).intValue() - 1));
                     date.set(Calendar.DAY_OF_MONTH, (new Integer(dd.getText()).intValue()));
                     
                     zda = new ZDA(device.getText(), getText(),
                     date);
                     handler.doIt(zda);
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ZDA"

    // $ANTLR start "ALR"
    public final void mALR() throws RecognitionException {
        try {
            int _type = ALR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1275:5: ( '$' device= DEVICE 'ALR' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1275:7: '$' device= DEVICE 'ALR' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
            {
            match('$'); 

            int deviceStart5801 = getCharIndex();
            int deviceStartLine5801 = getLine();
            int deviceStartCharPos5801 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5801, getCharIndex()-1);
            device.setLine(deviceStartLine5801);
            device.setCharPositionInLine(deviceStartCharPos5801);


            match("ALR"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1276:2: ( '\\u0021' .. '\\u007F' )+
            int cnt220=0;
            loop220:
            do {
                int alt220=2;
                alt220 = dfa220.predict(input);
                switch (alt220) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= '!' && input.LA(1) <= '\u007F') ) {
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
            	    if ( cnt220 >= 1 ) break loop220;
                        EarlyExitException eee =
                            new EarlyExitException(220, input);
                        throw eee;
                }
                cnt220++;
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1277:5: ( NUMBER )*
            loop221:
            do {
                int alt221=2;
                int LA221_0 = input.LA(1);

                if ( (LA221_0=='.'||(LA221_0 >= '0' && LA221_0 <= '9')) ) {
                    alt221=1;
                }


                switch (alt221) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1277:5: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop221;
                }
            } while (true);


            int checksumStart5831 = getCharIndex();
            int checksumStartLine5831 = getLine();
            int checksumStartCharPos5831 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5831, getCharIndex()-1);
            checksum.setLine(checksumStartLine5831);
            checksum.setCharPositionInLine(checksumStartCharPos5831);



            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALR"

    // $ANTLR start "VDM"
    public final void mVDM() throws RecognitionException {
        try {
            int _type = VDM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1280:6: ( '!' device= DEVICE 'VDM' SEP ( NUMBER )* SEP ( NUMBER )* SEP ( NUMBER )* SEP ( LETTERS )* SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1280:8: '!' device= DEVICE 'VDM' SEP ( NUMBER )* SEP ( NUMBER )* SEP ( NUMBER )* SEP ( LETTERS )* SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
            {
            match('!'); 

            int deviceStart5848 = getCharIndex();
            int deviceStartLine5848 = getLine();
            int deviceStartCharPos5848 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5848, getCharIndex()-1);
            device.setLine(deviceStartLine5848);
            device.setCharPositionInLine(deviceStartCharPos5848);


            match("VDM"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1281:4: ( NUMBER )*
            loop222:
            do {
                int alt222=2;
                int LA222_0 = input.LA(1);

                if ( (LA222_0=='.'||(LA222_0 >= '0' && LA222_0 <= '9')) ) {
                    alt222=1;
                }


                switch (alt222) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1281:4: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop222;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1282:4: ( NUMBER )*
            loop223:
            do {
                int alt223=2;
                int LA223_0 = input.LA(1);

                if ( (LA223_0=='.'||(LA223_0 >= '0' && LA223_0 <= '9')) ) {
                    alt223=1;
                }


                switch (alt223) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1282:4: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop223;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1283:4: ( NUMBER )*
            loop224:
            do {
                int alt224=2;
                int LA224_0 = input.LA(1);

                if ( (LA224_0=='.'||(LA224_0 >= '0' && LA224_0 <= '9')) ) {
                    alt224=1;
                }


                switch (alt224) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1283:4: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop224;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1284:4: ( LETTERS )*
            loop225:
            do {
                int alt225=2;
                int LA225_0 = input.LA(1);

                if ( (LA225_0==' '||(LA225_0 >= 'A' && LA225_0 <= 'Z')||(LA225_0 >= 'a' && LA225_0 <= 'z')) ) {
                    alt225=1;
                }


                switch (alt225) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1284:4: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop225;
                }
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1285:4: ( '\\u0021' .. '\\u007F' )+
            int cnt226=0;
            loop226:
            do {
                int alt226=2;
                alt226 = dfa226.predict(input);
                switch (alt226) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= '!' && input.LA(1) <= '\u007F') ) {
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
            	    if ( cnt226 >= 1 ) break loop226;
                        EarlyExitException eee =
                            new EarlyExitException(226, input);
                        throw eee;
                }
                cnt226++;
            } while (true);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1286:5: ( NUMBER )*
            loop227:
            do {
                int alt227=2;
                int LA227_0 = input.LA(1);

                if ( (LA227_0=='.'||(LA227_0 >= '0' && LA227_0 <= '9')) ) {
                    alt227=1;
                }


                switch (alt227) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1286:5: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop227;
                }
            } while (true);


            int checksumStart5912 = getCharIndex();
            int checksumStartLine5912 = getLine();
            int checksumStartCharPos5912 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5912, getCharIndex()-1);
            checksum.setLine(checksumStartLine5912);
            checksum.setCharPositionInLine(checksumStartCharPos5912);



            	aisParser.parse(getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VDM"

    // $ANTLR start "GPSD_AIS"
    public final void mGPSD_AIS() throws RecognitionException {
        try {
            int _type = GPSD_AIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken dev=null;
            CommonToken type=null;
            CommonToken repeat=null;
            CommonToken mmsi=null;
            CommonToken scaled=null;
            CommonToken status=null;
            CommonToken turn=null;
            CommonToken speed=null;
            CommonToken accuracy=null;
            CommonToken longitude=null;
            CommonToken latitude=null;
            CommonToken course=null;
            CommonToken heading=null;
            CommonToken second=null;
            CommonToken maneuvrer=null;
            CommonToken raim=null;
            CommonToken radio=null;
            CommonToken timestamp=null;
            CommonToken epfd=null;
            CommonToken imo=null;
            CommonToken ais_version=null;
            CommonToken callsign=null;
            CommonToken shipname=null;
            CommonToken shiptype=null;
            CommonToken to_bow=null;
            CommonToken to_stern=null;
            CommonToken to_port=null;
            CommonToken to_starboard=null;
            CommonToken eta=null;
            CommonToken draught=null;
            CommonToken destination=null;
            CommonToken dte=null;
            CommonToken dac=null;
            CommonToken fid=null;
            CommonToken wspeed=null;
            CommonToken wgust=null;
            CommonToken wdir=null;
            CommonToken wgustdir=null;
            CommonToken humidity=null;
            CommonToken airtemp=null;
            CommonToken dewpoint=null;
            CommonToken pressure=null;
            CommonToken pressuretend=null;
            CommonToken visgreater=null;
            CommonToken visibility=null;
            CommonToken waterlevel=null;
            CommonToken leveltrend=null;
            CommonToken cspeed=null;
            CommonToken cdir=null;
            CommonToken cspeed2=null;
            CommonToken cdir2=null;
            CommonToken cdepth2=null;
            CommonToken cspeed3=null;
            CommonToken cdir3=null;
            CommonToken cdepth3=null;
            CommonToken waveheight=null;
            CommonToken waveperiod=null;
            CommonToken wavedir=null;
            CommonToken swellheight=null;
            CommonToken swellperiod=null;
            CommonToken swelldir=null;
            CommonToken seastate=null;
            CommonToken watertemp=null;
            CommonToken preciptype=null;
            CommonToken salinity=null;
            CommonToken ice=null;
            CommonToken reserved=null;
            CommonToken regional=null;
            CommonToken cs=null;
            CommonToken display=null;
            CommonToken dsc=null;
            CommonToken band=null;
            CommonToken msg22=null;
            CommonToken vendorid=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1296:6: ( '{\"class\":\"AIS\"' SEP '\"device\":' dev= DEV SEP '\"type\":' type= NUMBER SEP '\"repeat\":' repeat= NUMBER SEP '\"mmsi\":' mmsi= NUMBER SEP '\"scaled\":' scaled= LETTERS SEP ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER ) ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1297:6: '{\"class\":\"AIS\"' SEP '\"device\":' dev= DEV SEP '\"type\":' type= NUMBER SEP '\"repeat\":' repeat= NUMBER SEP '\"mmsi\":' mmsi= NUMBER SEP '\"scaled\":' scaled= LETTERS SEP ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER ) ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )*
            {
            match("{\"class\":\"AIS\""); 



            mSEP(); 


            match("\"device\":"); 



            int devStart5954 = getCharIndex();
            int devStartLine5954 = getLine();
            int devStartCharPos5954 = getCharPositionInLine();
            mDEV(); 
            dev = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, devStart5954, getCharIndex()-1);
            dev.setLine(devStartLine5954);
            dev.setCharPositionInLine(devStartCharPos5954);


            mSEP(); 


            match("\"type\":"); 



            int typeStart5970 = getCharIndex();
            int typeStartLine5970 = getLine();
            int typeStartCharPos5970 = getCharPositionInLine();
            mNUMBER(); 
            type = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, typeStart5970, getCharIndex()-1);
            type.setLine(typeStartLine5970);
            type.setCharPositionInLine(typeStartCharPos5970);


            mSEP(); 


            match("\"repeat\":"); 



            int repeatStart5985 = getCharIndex();
            int repeatStartLine5985 = getLine();
            int repeatStartCharPos5985 = getCharPositionInLine();
            mNUMBER(); 
            repeat = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, repeatStart5985, getCharIndex()-1);
            repeat.setLine(repeatStartLine5985);
            repeat.setCharPositionInLine(repeatStartCharPos5985);


            mSEP(); 


            match("\"mmsi\":"); 



            int mmsiStart6000 = getCharIndex();
            int mmsiStartLine6000 = getLine();
            int mmsiStartCharPos6000 = getCharPositionInLine();
            mNUMBER(); 
            mmsi = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mmsiStart6000, getCharIndex()-1);
            mmsi.setLine(mmsiStartLine6000);
            mmsi.setCharPositionInLine(mmsiStartCharPos6000);


            mSEP(); 


            match("\"scaled\":"); 



            int scaledStart6013 = getCharIndex();
            int scaledStartLine6013 = getLine();
            int scaledStartCharPos6013 = getCharPositionInLine();
            mLETTERS(); 
            scaled = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, scaledStart6013, getCharIndex()-1);
            scaled.setLine(scaledStartLine6013);
            scaled.setCharPositionInLine(scaledStartCharPos6013);


            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1304:6: ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER )
            int alt230=7;
            int LA230_0 = input.LA(1);

            if ( (LA230_0=='\"') ) {
                switch ( input.LA(2) ) {
                case 's':
                    {
                    switch ( input.LA(3) ) {
                    case 't':
                        {
                        alt230=1;
                        }
                        break;
                    case 'e':
                        {
                        alt230=4;
                        }
                        break;
                    case 'h':
                        {
                        alt230=7;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 230, 2, input);

                        throw nvae;

                    }

                    }
                    break;
                case 't':
                    {
                    alt230=2;
                    }
                    break;
                case 'i':
                    {
                    alt230=3;
                    }
                    break;
                case 'd':
                    {
                    alt230=5;
                    }
                    break;
                case 'r':
                    {
                    alt230=6;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 230, 1, input);

                   // throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 230, 0, input);

              //  throw nvae;

            }
            switch (alt230) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1304:7: '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER
                    {
                    match("\"status\":"); 



                    int statusStart6033 = getCharIndex();
                    int statusStartLine6033 = getLine();
                    int statusStartCharPos6033 = getCharPositionInLine();
                    mNUMBER(); 
                    status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart6033, getCharIndex()-1);
                    status.setLine(statusStartLine6033);
                    status.setCharPositionInLine(statusStartCharPos6033);


                    mSEP(); 


                    match("\"turn\":"); 



                    int turnStart6048 = getCharIndex();
                    int turnStartLine6048 = getLine();
                    int turnStartCharPos6048 = getCharPositionInLine();
                    mSIGNED(); 
                    turn = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, turnStart6048, getCharIndex()-1);
                    turn.setLine(turnStartLine6048);
                    turn.setCharPositionInLine(turnStartCharPos6048);


                    mSEP(); 


                    match("\"speed\":"); 



                    int speedStart6063 = getCharIndex();
                    int speedStartLine6063 = getLine();
                    int speedStartCharPos6063 = getCharPositionInLine();
                    mNUMBER(); 
                    speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart6063, getCharIndex()-1);
                    speed.setLine(speedStartLine6063);
                    speed.setCharPositionInLine(speedStartCharPos6063);


                    mSEP(); 


                    match("\"accuracy\":"); 



                    int accuracyStart6078 = getCharIndex();
                    int accuracyStartLine6078 = getLine();
                    int accuracyStartCharPos6078 = getCharPositionInLine();
                    mLETTERS(); 
                    accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6078, getCharIndex()-1);
                    accuracy.setLine(accuracyStartLine6078);
                    accuracy.setCharPositionInLine(accuracyStartCharPos6078);


                    mSEP(); 


                    match("\"lon\":"); 



                    int longitudeStart6092 = getCharIndex();
                    int longitudeStartLine6092 = getLine();
                    int longitudeStartCharPos6092 = getCharPositionInLine();
                    mSIGNED(); 
                    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6092, getCharIndex()-1);
                    longitude.setLine(longitudeStartLine6092);
                    longitude.setCharPositionInLine(longitudeStartCharPos6092);


                    mSEP(); 


                    match("\"lat\":"); 



                    int latitudeStart6107 = getCharIndex();
                    int latitudeStartLine6107 = getLine();
                    int latitudeStartCharPos6107 = getCharPositionInLine();
                    mSIGNED(); 
                    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6107, getCharIndex()-1);
                    latitude.setLine(latitudeStartLine6107);
                    latitude.setCharPositionInLine(latitudeStartCharPos6107);


                    mSEP(); 


                    match("\"course\":"); 



                    int courseStart6122 = getCharIndex();
                    int courseStartLine6122 = getLine();
                    int courseStartCharPos6122 = getCharPositionInLine();
                    mNUMBER(); 
                    course = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, courseStart6122, getCharIndex()-1);
                    course.setLine(courseStartLine6122);
                    course.setCharPositionInLine(courseStartCharPos6122);


                    mSEP(); 


                    match("\"heading\":"); 



                    int headingStart6136 = getCharIndex();
                    int headingStartLine6136 = getLine();
                    int headingStartCharPos6136 = getCharPositionInLine();
                    mNUMBER(); 
                    heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart6136, getCharIndex()-1);
                    heading.setLine(headingStartLine6136);
                    heading.setCharPositionInLine(headingStartCharPos6136);


                    mSEP(); 


                    match("\"second\":"); 



                    int secondStart6150 = getCharIndex();
                    int secondStartLine6150 = getLine();
                    int secondStartCharPos6150 = getCharPositionInLine();
                    mNUMBER(); 
                    second = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, secondStart6150, getCharIndex()-1);
                    second.setLine(secondStartLine6150);
                    second.setCharPositionInLine(secondStartCharPos6150);


                    mSEP(); 


                    match("\"maneuver\":"); 



                    int maneuvrerStart6165 = getCharIndex();
                    int maneuvrerStartLine6165 = getLine();
                    int maneuvrerStartCharPos6165 = getCharPositionInLine();
                    mNUMBER(); 
                    maneuvrer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, maneuvrerStart6165, getCharIndex()-1);
                    maneuvrer.setLine(maneuvrerStartLine6165);
                    maneuvrer.setCharPositionInLine(maneuvrerStartCharPos6165);


                    mSEP(); 


                    match("\"raim\":"); 



                    int raimStart6180 = getCharIndex();
                    int raimStartLine6180 = getLine();
                    int raimStartCharPos6180 = getCharPositionInLine();
                    mLETTERS(); 
                    raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart6180, getCharIndex()-1);
                    raim.setLine(raimStartLine6180);
                    raim.setCharPositionInLine(raimStartCharPos6180);


                    mSEP(); 


                    match("\"radio\":"); 



                    int radioStart6195 = getCharIndex();
                    int radioStartLine6195 = getLine();
                    int radioStartCharPos6195 = getCharPositionInLine();
                    mNUMBER(); 
                    radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart6195, getCharIndex()-1);
                    radio.setLine(radioStartLine6195);
                    radio.setCharPositionInLine(radioStartCharPos6195);


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1318:6: '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER
                    {
                    match("\"timestamp\":"); 



                    int timestampStart6219 = getCharIndex();
                    int timestampStartLine6219 = getLine();
                    int timestampStartCharPos6219 = getCharPositionInLine();
                    mTIME_STAMP(); 
                    timestamp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, timestampStart6219, getCharIndex()-1);
                    timestamp.setLine(timestampStartLine6219);
                    timestamp.setCharPositionInLine(timestampStartCharPos6219);


                    mSEP(); 


                    match("\"accuracy\":"); 



                    int accuracyStart6233 = getCharIndex();
                    int accuracyStartLine6233 = getLine();
                    int accuracyStartCharPos6233 = getCharPositionInLine();
                    mLETTERS(); 
                    accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6233, getCharIndex()-1);
                    accuracy.setLine(accuracyStartLine6233);
                    accuracy.setCharPositionInLine(accuracyStartCharPos6233);


                    mSEP(); 


                    match("\"lon\":"); 



                    int longitudeStart6247 = getCharIndex();
                    int longitudeStartLine6247 = getLine();
                    int longitudeStartCharPos6247 = getCharPositionInLine();
                    mSIGNED(); 
                    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6247, getCharIndex()-1);
                    longitude.setLine(longitudeStartLine6247);
                    longitude.setCharPositionInLine(longitudeStartCharPos6247);


                    mSEP(); 


                    match("\"lat\":"); 



                    int latitudeStart6261 = getCharIndex();
                    int latitudeStartLine6261 = getLine();
                    int latitudeStartCharPos6261 = getCharPositionInLine();
                    mSIGNED(); 
                    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6261, getCharIndex()-1);
                    latitude.setLine(latitudeStartLine6261);
                    latitude.setCharPositionInLine(latitudeStartCharPos6261);


                    mSEP(); 


                    match("\"epfd\":"); 



                    int epfdStart6274 = getCharIndex();
                    int epfdStartLine6274 = getLine();
                    int epfdStartCharPos6274 = getCharPositionInLine();
                    mNUMBER(); 
                    epfd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, epfdStart6274, getCharIndex()-1);
                    epfd.setLine(epfdStartLine6274);
                    epfd.setCharPositionInLine(epfdStartCharPos6274);


                    mSEP(); 


                    match("\"raim\":"); 



                    int raimStart6288 = getCharIndex();
                    int raimStartLine6288 = getLine();
                    int raimStartCharPos6288 = getCharPositionInLine();
                    mLETTERS(); 
                    raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart6288, getCharIndex()-1);
                    raim.setLine(raimStartLine6288);
                    raim.setCharPositionInLine(raimStartCharPos6288);


                    mSEP(); 


                    match("\"radio\":"); 



                    int radioStart6302 = getCharIndex();
                    int radioStartLine6302 = getLine();
                    int radioStartCharPos6302 = getCharPositionInLine();
                    mNUMBER(); 
                    radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart6302, getCharIndex()-1);
                    radio.setLine(radioStartLine6302);
                    radio.setCharPositionInLine(radioStartCharPos6302);


                    }
                    break;
                case 3 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1327:6: '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER
                    {
                    match("\"imo\":"); 



                    int imoStart6326 = getCharIndex();
                    int imoStartLine6326 = getLine();
                    int imoStartCharPos6326 = getCharPositionInLine();
                    mNUMBER(); 
                    imo = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, imoStart6326, getCharIndex()-1);
                    imo.setLine(imoStartLine6326);
                    imo.setCharPositionInLine(imoStartCharPos6326);


                    mSEP(); 


                    match("\"ais_version\":"); 



                    int ais_versionStart6339 = getCharIndex();
                    int ais_versionStartLine6339 = getLine();
                    int ais_versionStartCharPos6339 = getCharPositionInLine();
                    mNUMBER(); 
                    ais_version = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ais_versionStart6339, getCharIndex()-1);
                    ais_version.setLine(ais_versionStartLine6339);
                    ais_version.setCharPositionInLine(ais_versionStartCharPos6339);


                    mSEP(); 


                    match("\"callsign\":"); 



                    int callsignStart6353 = getCharIndex();
                    int callsignStartLine6353 = getLine();
                    int callsignStartCharPos6353 = getCharPositionInLine();
                    mNAME(); 
                    callsign = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, callsignStart6353, getCharIndex()-1);
                    callsign.setLine(callsignStartLine6353);
                    callsign.setCharPositionInLine(callsignStartCharPos6353);


                    mSEP(); 


                    match("\"shipname\":"); 



                    int shipnameStart6367 = getCharIndex();
                    int shipnameStartLine6367 = getLine();
                    int shipnameStartCharPos6367 = getCharPositionInLine();
                    mNAME(); 
                    shipname = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shipnameStart6367, getCharIndex()-1);
                    shipname.setLine(shipnameStartLine6367);
                    shipname.setCharPositionInLine(shipnameStartCharPos6367);


                    mSEP(); 


                    match("\"shiptype\":"); 



                    int shiptypeStart6380 = getCharIndex();
                    int shiptypeStartLine6380 = getLine();
                    int shiptypeStartCharPos6380 = getCharPositionInLine();
                    mNUMBER(); 
                    shiptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shiptypeStart6380, getCharIndex()-1);
                    shiptype.setLine(shiptypeStartLine6380);
                    shiptype.setCharPositionInLine(shiptypeStartCharPos6380);


                    mSEP(); 


                    match("\"to_bow\":"); 



                    int to_bowStart6393 = getCharIndex();
                    int to_bowStartLine6393 = getLine();
                    int to_bowStartCharPos6393 = getCharPositionInLine();
                    mNUMBER(); 
                    to_bow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_bowStart6393, getCharIndex()-1);
                    to_bow.setLine(to_bowStartLine6393);
                    to_bow.setCharPositionInLine(to_bowStartCharPos6393);


                    mSEP(); 


                    match("\"to_stern\":"); 



                    int to_sternStart6406 = getCharIndex();
                    int to_sternStartLine6406 = getLine();
                    int to_sternStartCharPos6406 = getCharPositionInLine();
                    mNUMBER(); 
                    to_stern = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_sternStart6406, getCharIndex()-1);
                    to_stern.setLine(to_sternStartLine6406);
                    to_stern.setCharPositionInLine(to_sternStartCharPos6406);


                    mSEP(); 


                    match("\"to_port\":"); 



                    int to_portStart6419 = getCharIndex();
                    int to_portStartLine6419 = getLine();
                    int to_portStartCharPos6419 = getCharPositionInLine();
                    mNUMBER(); 
                    to_port = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_portStart6419, getCharIndex()-1);
                    to_port.setLine(to_portStartLine6419);
                    to_port.setCharPositionInLine(to_portStartCharPos6419);


                    mSEP(); 


                    match("\"to_starboard\":"); 



                    int to_starboardStart6432 = getCharIndex();
                    int to_starboardStartLine6432 = getLine();
                    int to_starboardStartCharPos6432 = getCharPositionInLine();
                    mNUMBER(); 
                    to_starboard = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_starboardStart6432, getCharIndex()-1);
                    to_starboard.setLine(to_starboardStartLine6432);
                    to_starboard.setCharPositionInLine(to_starboardStartCharPos6432);


                    mSEP(); 


                    match("\"epfd\":"); 



                    int epfdStart6445 = getCharIndex();
                    int epfdStartLine6445 = getLine();
                    int epfdStartCharPos6445 = getCharPositionInLine();
                    mNUMBER(); 
                    epfd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, epfdStart6445, getCharIndex()-1);
                    epfd.setLine(epfdStartLine6445);
                    epfd.setCharPositionInLine(epfdStartCharPos6445);


                    mSEP(); 


                    match("\"eta\":"); 



                    int etaStart6458 = getCharIndex();
                    int etaStartLine6458 = getLine();
                    int etaStartCharPos6458 = getCharPositionInLine();
                    mTIME_STAMP(); 
                    eta = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, etaStart6458, getCharIndex()-1);
                    eta.setLine(etaStartLine6458);
                    eta.setCharPositionInLine(etaStartCharPos6458);


                    mSEP(); 


                    match("\"draught\":"); 



                    int draughtStart6471 = getCharIndex();
                    int draughtStartLine6471 = getLine();
                    int draughtStartCharPos6471 = getCharPositionInLine();
                    mNUMBER(); 
                    draught = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, draughtStart6471, getCharIndex()-1);
                    draught.setLine(draughtStartLine6471);
                    draught.setCharPositionInLine(draughtStartCharPos6471);


                    mSEP(); 


                    match("\"destination\":"); 



                    int destinationStart6485 = getCharIndex();
                    int destinationStartLine6485 = getLine();
                    int destinationStartCharPos6485 = getCharPositionInLine();
                    mNAME(); 
                    destination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationStart6485, getCharIndex()-1);
                    destination.setLine(destinationStartLine6485);
                    destination.setCharPositionInLine(destinationStartCharPos6485);


                    mSEP(); 


                    match("\"dte\":"); 



                    int dteStart6500 = getCharIndex();
                    int dteStartLine6500 = getLine();
                    int dteStartCharPos6500 = getCharPositionInLine();
                    mNUMBER(); 
                    dte = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dteStart6500, getCharIndex()-1);
                    dte.setLine(dteStartLine6500);
                    dte.setCharPositionInLine(dteStartCharPos6500);


                    }
                    break;
                case 4 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1343:6: '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER
                    {
                    match("\"seqno\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"dest_mmsi\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"retransmit\":"); 



                    mLETTERS(); 


                    mSEP(); 


                    match("\"dac\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"fid\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"off_pos\":"); 



                    mLETTERS(); 


                    mSEP(); 


                    match("\"alarm\":"); 



                    mLETTERS(); 


                    mSEP(); 


                    match("\"stat_ext\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"ana_int\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"ana_ext1\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"ana_ext2\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"racon\":"); 



                    mNUMBER(); 


                    mSEP(); 


                    match("\"light\":"); 



                    mNUMBER(); 


                    }
                    break;
                case 5 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1358:6: '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER
                    {
                    match("\"dac\":"); 



                    int dacStart6688 = getCharIndex();
                    int dacStartLine6688 = getLine();
                    int dacStartCharPos6688 = getCharPositionInLine();
                    mNUMBER(); 
                    dac = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dacStart6688, getCharIndex()-1);
                    dac.setLine(dacStartLine6688);
                    dac.setCharPositionInLine(dacStartCharPos6688);


                    mSEP(); 


                    match("\"fid\":"); 



                    int fidStart6702 = getCharIndex();
                    int fidStartLine6702 = getLine();
                    int fidStartCharPos6702 = getCharPositionInLine();
                    mNUMBER(); 
                    fid = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fidStart6702, getCharIndex()-1);
                    fid.setLine(fidStartLine6702);
                    fid.setCharPositionInLine(fidStartCharPos6702);


                    mSEP(); 


                    match("\"lat\":"); 



                    int latitudeStart6716 = getCharIndex();
                    int latitudeStartLine6716 = getLine();
                    int latitudeStartCharPos6716 = getCharPositionInLine();
                    mSIGNED(); 
                    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6716, getCharIndex()-1);
                    latitude.setLine(latitudeStartLine6716);
                    latitude.setCharPositionInLine(latitudeStartCharPos6716);


                    mSEP(); 


                    match("\"lon\":"); 



                    int longitudeStart6730 = getCharIndex();
                    int longitudeStartLine6730 = getLine();
                    int longitudeStartCharPos6730 = getCharPositionInLine();
                    mSIGNED(); 
                    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6730, getCharIndex()-1);
                    longitude.setLine(longitudeStartLine6730);
                    longitude.setCharPositionInLine(longitudeStartCharPos6730);


                    mSEP(); 


                    match("\"accuracy\":"); 



                    int accuracyStart6744 = getCharIndex();
                    int accuracyStartLine6744 = getLine();
                    int accuracyStartCharPos6744 = getCharPositionInLine();
                    mLETTERS(); 
                    accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6744, getCharIndex()-1);
                    accuracy.setLine(accuracyStartLine6744);
                    accuracy.setCharPositionInLine(accuracyStartCharPos6744);


                    mSEP(); 


                    match("\"timestamp\":"); 



                    int timestampStart6757 = getCharIndex();
                    int timestampStartLine6757 = getLine();
                    int timestampStartCharPos6757 = getCharPositionInLine();
                    mTIME_STAMP(); 
                    timestamp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, timestampStart6757, getCharIndex()-1);
                    timestamp.setLine(timestampStartLine6757);
                    timestamp.setCharPositionInLine(timestampStartCharPos6757);


                    mSEP(); 


                    match("\"wspeed\":"); 



                    int wspeedStart6771 = getCharIndex();
                    int wspeedStartLine6771 = getLine();
                    int wspeedStartCharPos6771 = getCharPositionInLine();
                    mNUMBER(); 
                    wspeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wspeedStart6771, getCharIndex()-1);
                    wspeed.setLine(wspeedStartLine6771);
                    wspeed.setCharPositionInLine(wspeedStartCharPos6771);


                    mSEP(); 


                    match("\"wgust\":"); 



                    int wgustStart6785 = getCharIndex();
                    int wgustStartLine6785 = getLine();
                    int wgustStartCharPos6785 = getCharPositionInLine();
                    mNUMBER(); 
                    wgust = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wgustStart6785, getCharIndex()-1);
                    wgust.setLine(wgustStartLine6785);
                    wgust.setCharPositionInLine(wgustStartCharPos6785);


                    mSEP(); 


                    match("\"wdir\":"); 



                    int wdirStart6799 = getCharIndex();
                    int wdirStartLine6799 = getLine();
                    int wdirStartCharPos6799 = getCharPositionInLine();
                    mNUMBER(); 
                    wdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wdirStart6799, getCharIndex()-1);
                    wdir.setLine(wdirStartLine6799);
                    wdir.setCharPositionInLine(wdirStartCharPos6799);


                    mSEP(); 


                    match("\"wgustdir\":"); 



                    int wgustdirStart6813 = getCharIndex();
                    int wgustdirStartLine6813 = getLine();
                    int wgustdirStartCharPos6813 = getCharPositionInLine();
                    mNUMBER(); 
                    wgustdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wgustdirStart6813, getCharIndex()-1);
                    wgustdir.setLine(wgustdirStartLine6813);
                    wgustdir.setCharPositionInLine(wgustdirStartCharPos6813);


                    mSEP(); 


                    match("\"humidity\":"); 



                    int humidityStart6827 = getCharIndex();
                    int humidityStartLine6827 = getLine();
                    int humidityStartCharPos6827 = getCharPositionInLine();
                    mNUMBER(); 
                    humidity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, humidityStart6827, getCharIndex()-1);
                    humidity.setLine(humidityStartLine6827);
                    humidity.setCharPositionInLine(humidityStartCharPos6827);


                    mSEP(); 


                    match("\"airtemp\":"); 



                    int airtempStart6841 = getCharIndex();
                    int airtempStartLine6841 = getLine();
                    int airtempStartCharPos6841 = getCharPositionInLine();
                    mSIGNED(); 
                    airtemp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, airtempStart6841, getCharIndex()-1);
                    airtemp.setLine(airtempStartLine6841);
                    airtemp.setCharPositionInLine(airtempStartCharPos6841);


                    mSEP(); 


                    match("\"dewpoint\":"); 



                    int dewpointStart6854 = getCharIndex();
                    int dewpointStartLine6854 = getLine();
                    int dewpointStartCharPos6854 = getCharPositionInLine();
                    mSIGNED(); 
                    dewpoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dewpointStart6854, getCharIndex()-1);
                    dewpoint.setLine(dewpointStartLine6854);
                    dewpoint.setCharPositionInLine(dewpointStartCharPos6854);


                    mSEP(); 


                    match("\"pressure\":"); 



                    int pressureStart6867 = getCharIndex();
                    int pressureStartLine6867 = getLine();
                    int pressureStartCharPos6867 = getCharPositionInLine();
                    mNUMBER(); 
                    pressure = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, pressureStart6867, getCharIndex()-1);
                    pressure.setLine(pressureStartLine6867);
                    pressure.setCharPositionInLine(pressureStartCharPos6867);


                    mSEP(); 


                    match("\"pressuretend\":"); 



                    int pressuretendStart6881 = getCharIndex();
                    int pressuretendStartLine6881 = getLine();
                    int pressuretendStartCharPos6881 = getCharPositionInLine();
                    mNUMBER(); 
                    pressuretend = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, pressuretendStart6881, getCharIndex()-1);
                    pressuretend.setLine(pressuretendStartLine6881);
                    pressuretend.setCharPositionInLine(pressuretendStartCharPos6881);


                    mSEP(); 


                    match("\"visgreater\":"); 



                    int visgreaterStart6895 = getCharIndex();
                    int visgreaterStartLine6895 = getLine();
                    int visgreaterStartCharPos6895 = getCharPositionInLine();
                    mLETTERS(); 
                    visgreater = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, visgreaterStart6895, getCharIndex()-1);
                    visgreater.setLine(visgreaterStartLine6895);
                    visgreater.setCharPositionInLine(visgreaterStartCharPos6895);


                    mSEP(); 


                    match("\"visibility\":"); 



                    int visibilityStart6908 = getCharIndex();
                    int visibilityStartLine6908 = getLine();
                    int visibilityStartCharPos6908 = getCharPositionInLine();
                    mNUMBER(); 
                    visibility = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, visibilityStart6908, getCharIndex()-1);
                    visibility.setLine(visibilityStartLine6908);
                    visibility.setCharPositionInLine(visibilityStartCharPos6908);


                    mSEP(); 


                    match("\"waterlevel\":"); 



                    int waterlevelStart6922 = getCharIndex();
                    int waterlevelStartLine6922 = getLine();
                    int waterlevelStartCharPos6922 = getCharPositionInLine();
                    mSIGNED(); 
                    waterlevel = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waterlevelStart6922, getCharIndex()-1);
                    waterlevel.setLine(waterlevelStartLine6922);
                    waterlevel.setCharPositionInLine(waterlevelStartCharPos6922);


                    mSEP(); 


                    match("\"leveltrend\":"); 



                    int leveltrendStart6935 = getCharIndex();
                    int leveltrendStartLine6935 = getLine();
                    int leveltrendStartCharPos6935 = getCharPositionInLine();
                    mNUMBER(); 
                    leveltrend = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, leveltrendStart6935, getCharIndex()-1);
                    leveltrend.setLine(leveltrendStartLine6935);
                    leveltrend.setCharPositionInLine(leveltrendStartCharPos6935);


                    mSEP(); 


                    match("\"cspeed\":"); 



                    int cspeedStart6949 = getCharIndex();
                    int cspeedStartLine6949 = getLine();
                    int cspeedStartCharPos6949 = getCharPositionInLine();
                    mNUMBER(); 
                    cspeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeedStart6949, getCharIndex()-1);
                    cspeed.setLine(cspeedStartLine6949);
                    cspeed.setCharPositionInLine(cspeedStartCharPos6949);


                    mSEP(); 


                    match("\"cdir\":"); 



                    int cdirStart6963 = getCharIndex();
                    int cdirStartLine6963 = getLine();
                    int cdirStartCharPos6963 = getCharPositionInLine();
                    mNUMBER(); 
                    cdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdirStart6963, getCharIndex()-1);
                    cdir.setLine(cdirStartLine6963);
                    cdir.setCharPositionInLine(cdirStartCharPos6963);


                    mSEP(); 


                    match("\"cspeed2\":"); 



                    int cspeed2Start6977 = getCharIndex();
                    int cspeed2StartLine6977 = getLine();
                    int cspeed2StartCharPos6977 = getCharPositionInLine();
                    mNUMBER(); 
                    cspeed2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeed2Start6977, getCharIndex()-1);
                    cspeed2.setLine(cspeed2StartLine6977);
                    cspeed2.setCharPositionInLine(cspeed2StartCharPos6977);


                    mSEP(); 


                    match("\"cdir2\":"); 



                    int cdir2Start6991 = getCharIndex();
                    int cdir2StartLine6991 = getLine();
                    int cdir2StartCharPos6991 = getCharPositionInLine();
                    mNUMBER(); 
                    cdir2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdir2Start6991, getCharIndex()-1);
                    cdir2.setLine(cdir2StartLine6991);
                    cdir2.setCharPositionInLine(cdir2StartCharPos6991);


                    mSEP(); 


                    match("\"cdepth2\":"); 



                    int cdepth2Start7005 = getCharIndex();
                    int cdepth2StartLine7005 = getLine();
                    int cdepth2StartCharPos7005 = getCharPositionInLine();
                    mNUMBER(); 
                    cdepth2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdepth2Start7005, getCharIndex()-1);
                    cdepth2.setLine(cdepth2StartLine7005);
                    cdepth2.setCharPositionInLine(cdepth2StartCharPos7005);


                    mSEP(); 


                    match("\"cspeed3\":"); 



                    int cspeed3Start7019 = getCharIndex();
                    int cspeed3StartLine7019 = getLine();
                    int cspeed3StartCharPos7019 = getCharPositionInLine();
                    mNUMBER(); 
                    cspeed3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeed3Start7019, getCharIndex()-1);
                    cspeed3.setLine(cspeed3StartLine7019);
                    cspeed3.setCharPositionInLine(cspeed3StartCharPos7019);


                    mSEP(); 


                    match("\"cdir3\":"); 



                    int cdir3Start7033 = getCharIndex();
                    int cdir3StartLine7033 = getLine();
                    int cdir3StartCharPos7033 = getCharPositionInLine();
                    mNUMBER(); 
                    cdir3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdir3Start7033, getCharIndex()-1);
                    cdir3.setLine(cdir3StartLine7033);
                    cdir3.setCharPositionInLine(cdir3StartCharPos7033);


                    mSEP(); 


                    match("\"cdepth3\":"); 



                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1384:19: (cdepth3= NUMBER | '\\n' )
                    int alt228=2;
                    int LA228_0 = input.LA(1);

                    if ( (LA228_0=='.'||(LA228_0 >= '0' && LA228_0 <= '9')) ) {
                        alt228=1;
                    }
                    else if ( (LA228_0=='\n') ) {
                        alt228=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 228, 0, input);

                      //  throw nvae;

                    }
                    switch (alt228) {
                        case 1 :
                            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1384:20: cdepth3= NUMBER
                            {
                            int cdepth3Start7050 = getCharIndex();
                            int cdepth3StartLine7050 = getLine();
                            int cdepth3StartCharPos7050 = getCharPositionInLine();
                            mNUMBER(); 
                            cdepth3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdepth3Start7050, getCharIndex()-1);
                            cdepth3.setLine(cdepth3StartLine7050);
                            cdepth3.setCharPositionInLine(cdepth3StartCharPos7050);


                            }
                            break;
                        case 2 :
                            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1384:36: '\\n'
                            {
                            match('\n'); 

                            }
                            break;

                    }


                    mSEP(); 


                    match("\"waveheight\":"); 



                    int waveheightStart7069 = getCharIndex();
                    int waveheightStartLine7069 = getLine();
                    int waveheightStartCharPos7069 = getCharPositionInLine();
                    mNUMBER(); 
                    waveheight = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waveheightStart7069, getCharIndex()-1);
                    waveheight.setLine(waveheightStartLine7069);
                    waveheight.setCharPositionInLine(waveheightStartCharPos7069);


                    mSEP(); 


                    match("\"waveperiod\":"); 



                    int waveperiodStart7083 = getCharIndex();
                    int waveperiodStartLine7083 = getLine();
                    int waveperiodStartCharPos7083 = getCharPositionInLine();
                    mNUMBER(); 
                    waveperiod = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waveperiodStart7083, getCharIndex()-1);
                    waveperiod.setLine(waveperiodStartLine7083);
                    waveperiod.setCharPositionInLine(waveperiodStartCharPos7083);


                    mSEP(); 


                    match("\"wavedir\":"); 



                    int wavedirStart7097 = getCharIndex();
                    int wavedirStartLine7097 = getLine();
                    int wavedirStartCharPos7097 = getCharPositionInLine();
                    mNUMBER(); 
                    wavedir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wavedirStart7097, getCharIndex()-1);
                    wavedir.setLine(wavedirStartLine7097);
                    wavedir.setCharPositionInLine(wavedirStartCharPos7097);


                    mSEP(); 


                    match("\"swellheight\":"); 



                    int swellheightStart7111 = getCharIndex();
                    int swellheightStartLine7111 = getLine();
                    int swellheightStartCharPos7111 = getCharPositionInLine();
                    mNUMBER(); 
                    swellheight = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swellheightStart7111, getCharIndex()-1);
                    swellheight.setLine(swellheightStartLine7111);
                    swellheight.setCharPositionInLine(swellheightStartCharPos7111);


                    mSEP(); 


                    match("\"swellperiod\":"); 



                    int swellperiodStart7125 = getCharIndex();
                    int swellperiodStartLine7125 = getLine();
                    int swellperiodStartCharPos7125 = getCharPositionInLine();
                    mNUMBER(); 
                    swellperiod = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swellperiodStart7125, getCharIndex()-1);
                    swellperiod.setLine(swellperiodStartLine7125);
                    swellperiod.setCharPositionInLine(swellperiodStartCharPos7125);


                    mSEP(); 


                    match("\"swelldir\":"); 



                    int swelldirStart7139 = getCharIndex();
                    int swelldirStartLine7139 = getLine();
                    int swelldirStartCharPos7139 = getCharPositionInLine();
                    mNUMBER(); 
                    swelldir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swelldirStart7139, getCharIndex()-1);
                    swelldir.setLine(swelldirStartLine7139);
                    swelldir.setCharPositionInLine(swelldirStartCharPos7139);


                    mSEP(); 


                    match("\"seastate\":"); 



                    int seastateStart7153 = getCharIndex();
                    int seastateStartLine7153 = getLine();
                    int seastateStartCharPos7153 = getCharPositionInLine();
                    mNUMBER(); 
                    seastate = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, seastateStart7153, getCharIndex()-1);
                    seastate.setLine(seastateStartLine7153);
                    seastate.setCharPositionInLine(seastateStartCharPos7153);


                    mSEP(); 


                    match("\"watertemp\":"); 



                    int watertempStart7167 = getCharIndex();
                    int watertempStartLine7167 = getLine();
                    int watertempStartCharPos7167 = getCharPositionInLine();
                    mSIGNED(); 
                    watertemp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, watertempStart7167, getCharIndex()-1);
                    watertemp.setLine(watertempStartLine7167);
                    watertemp.setCharPositionInLine(watertempStartCharPos7167);


                    mSEP(); 


                    match("\"preciptype\":"); 



                    int preciptypeStart7180 = getCharIndex();
                    int preciptypeStartLine7180 = getLine();
                    int preciptypeStartCharPos7180 = getCharPositionInLine();
                    mNUMBER(); 
                    preciptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, preciptypeStart7180, getCharIndex()-1);
                    preciptype.setLine(preciptypeStartLine7180);
                    preciptype.setCharPositionInLine(preciptypeStartCharPos7180);


                    mSEP(); 


                    match("\"salinity\":"); 



                    int salinityStart7194 = getCharIndex();
                    int salinityStartLine7194 = getLine();
                    int salinityStartCharPos7194 = getCharPositionInLine();
                    mNUMBER(); 
                    salinity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, salinityStart7194, getCharIndex()-1);
                    salinity.setLine(salinityStartLine7194);
                    salinity.setCharPositionInLine(salinityStartCharPos7194);


                    mSEP(); 


                    match("\"ice\":"); 



                    int iceStart7208 = getCharIndex();
                    int iceStartLine7208 = getLine();
                    int iceStartCharPos7208 = getCharPositionInLine();
                    mNUMBER(); 
                    ice = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, iceStart7208, getCharIndex()-1);
                    ice.setLine(iceStartLine7208);
                    ice.setCharPositionInLine(iceStartCharPos7208);


                    }
                    break;
                case 6 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1398:6: '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER
                    {
                    match("\"reserved\":"); 



                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1398:28: (reserved= NUMBER )*
                    loop229:
                    do {
                        int alt229=2;
                        int LA229_0 = input.LA(1);

                        if ( (LA229_0=='.'||(LA229_0 >= '0' && LA229_0 <= '9')) ) {
                            alt229=1;
                        }


                        switch (alt229) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1398:28: reserved= NUMBER
                    	    {
                    	    int reservedStart7233 = getCharIndex();
                    	    int reservedStartLine7233 = getLine();
                    	    int reservedStartCharPos7233 = getCharPositionInLine();
                    	    mNUMBER(); 
                    	    reserved = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, reservedStart7233, getCharIndex()-1);
                    	    reserved.setLine(reservedStartLine7233);
                    	    reserved.setCharPositionInLine(reservedStartCharPos7233);


                    	    }
                    	    break;

                    	default :
                    	    break loop229;
                        }
                    } while (true);


                    mSEP(); 


                    match("\"speed\":"); 



                    int speedStart7247 = getCharIndex();
                    int speedStartLine7247 = getLine();
                    int speedStartCharPos7247 = getCharPositionInLine();
                    mNUMBER(); 
                    speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart7247, getCharIndex()-1);
                    speed.setLine(speedStartLine7247);
                    speed.setCharPositionInLine(speedStartCharPos7247);


                    mSEP(); 


                    match("\"accuracy\":"); 



                    int accuracyStart7261 = getCharIndex();
                    int accuracyStartLine7261 = getLine();
                    int accuracyStartCharPos7261 = getCharPositionInLine();
                    mLETTERS(); 
                    accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart7261, getCharIndex()-1);
                    accuracy.setLine(accuracyStartLine7261);
                    accuracy.setCharPositionInLine(accuracyStartCharPos7261);


                    mSEP(); 


                    match("\"lon\":"); 



                    int longitudeStart7274 = getCharIndex();
                    int longitudeStartLine7274 = getLine();
                    int longitudeStartCharPos7274 = getCharPositionInLine();
                    mSIGNED(); 
                    longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart7274, getCharIndex()-1);
                    longitude.setLine(longitudeStartLine7274);
                    longitude.setCharPositionInLine(longitudeStartCharPos7274);


                    mSEP(); 


                    match("\"lat\":"); 



                    int latitudeStart7288 = getCharIndex();
                    int latitudeStartLine7288 = getLine();
                    int latitudeStartCharPos7288 = getCharPositionInLine();
                    mSIGNED(); 
                    latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart7288, getCharIndex()-1);
                    latitude.setLine(latitudeStartLine7288);
                    latitude.setCharPositionInLine(latitudeStartCharPos7288);


                    mSEP(); 


                    match("\"course\":"); 



                    int courseStart7302 = getCharIndex();
                    int courseStartLine7302 = getLine();
                    int courseStartCharPos7302 = getCharPositionInLine();
                    mNUMBER(); 
                    course = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, courseStart7302, getCharIndex()-1);
                    course.setLine(courseStartLine7302);
                    course.setCharPositionInLine(courseStartCharPos7302);


                    mSEP(); 


                    match("\"heading\":"); 



                    int headingStart7315 = getCharIndex();
                    int headingStartLine7315 = getLine();
                    int headingStartCharPos7315 = getCharPositionInLine();
                    mNUMBER(); 
                    heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart7315, getCharIndex()-1);
                    heading.setLine(headingStartLine7315);
                    heading.setCharPositionInLine(headingStartCharPos7315);


                    mSEP(); 


                    match("\"second\":"); 



                    int secondStart7328 = getCharIndex();
                    int secondStartLine7328 = getLine();
                    int secondStartCharPos7328 = getCharPositionInLine();
                    mNUMBER(); 
                    second = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, secondStart7328, getCharIndex()-1);
                    second.setLine(secondStartLine7328);
                    second.setCharPositionInLine(secondStartCharPos7328);


                    mSEP(); 


                    match("\"regional\":"); 



                    int regionalStart7343 = getCharIndex();
                    int regionalStartLine7343 = getLine();
                    int regionalStartCharPos7343 = getCharPositionInLine();
                    mNUMBER(); 
                    regional = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, regionalStart7343, getCharIndex()-1);
                    regional.setLine(regionalStartLine7343);
                    regional.setCharPositionInLine(regionalStartCharPos7343);


                    mSEP(); 


                    match("\"cs\":"); 



                    int csStart7357 = getCharIndex();
                    int csStartLine7357 = getLine();
                    int csStartCharPos7357 = getCharPositionInLine();
                    mLETTERS(); 
                    cs = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, csStart7357, getCharIndex()-1);
                    cs.setLine(csStartLine7357);
                    cs.setCharPositionInLine(csStartCharPos7357);


                    mSEP(); 


                    match("\"display\":"); 



                    int displayStart7371 = getCharIndex();
                    int displayStartLine7371 = getLine();
                    int displayStartCharPos7371 = getCharPositionInLine();
                    mLETTERS(); 
                    display = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, displayStart7371, getCharIndex()-1);
                    display.setLine(displayStartLine7371);
                    display.setCharPositionInLine(displayStartCharPos7371);


                    mSEP(); 


                    match("\"dsc\":"); 



                    int dscStart7384 = getCharIndex();
                    int dscStartLine7384 = getLine();
                    int dscStartCharPos7384 = getCharPositionInLine();
                    mLETTERS(); 
                    dsc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dscStart7384, getCharIndex()-1);
                    dsc.setLine(dscStartLine7384);
                    dsc.setCharPositionInLine(dscStartCharPos7384);


                    mSEP(); 


                    match("\"band\":"); 



                    int bandStart7398 = getCharIndex();
                    int bandStartLine7398 = getLine();
                    int bandStartCharPos7398 = getCharPositionInLine();
                    mLETTERS(); 
                    band = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bandStart7398, getCharIndex()-1);
                    band.setLine(bandStartLine7398);
                    band.setCharPositionInLine(bandStartCharPos7398);


                    mSEP(); 


                    match("\"msg22\":"); 



                    int msg22Start7411 = getCharIndex();
                    int msg22StartLine7411 = getLine();
                    int msg22StartCharPos7411 = getCharPositionInLine();
                    mLETTERS(); 
                    msg22 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, msg22Start7411, getCharIndex()-1);
                    msg22.setLine(msg22StartLine7411);
                    msg22.setCharPositionInLine(msg22StartCharPos7411);


                    mSEP(); 


                    match("\"raim\":"); 



                    int raimStart7424 = getCharIndex();
                    int raimStartLine7424 = getLine();
                    int raimStartCharPos7424 = getCharPositionInLine();
                    mLETTERS(); 
                    raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart7424, getCharIndex()-1);
                    raim.setLine(raimStartLine7424);
                    raim.setCharPositionInLine(raimStartCharPos7424);


                    mSEP(); 


                    match("\"radio\":"); 



                    int radioStart7438 = getCharIndex();
                    int radioStartLine7438 = getLine();
                    int radioStartCharPos7438 = getCharPositionInLine();
                    mNUMBER(); 
                    radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart7438, getCharIndex()-1);
                    radio.setLine(radioStartLine7438);
                    radio.setCharPositionInLine(radioStartCharPos7438);


                    }
                    break;
                case 7 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1416:6: '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER
                    {
                    match("\"shipname\":"); 



                    int shipnameStart7462 = getCharIndex();
                    int shipnameStartLine7462 = getLine();
                    int shipnameStartCharPos7462 = getCharPositionInLine();
                    mNAME(); 
                    shipname = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shipnameStart7462, getCharIndex()-1);
                    shipname.setLine(shipnameStartLine7462);
                    shipname.setCharPositionInLine(shipnameStartCharPos7462);


                    mSEP(); 


                    match("\"shiptype\":"); 



                    int shiptypeStart7476 = getCharIndex();
                    int shiptypeStartLine7476 = getLine();
                    int shiptypeStartCharPos7476 = getCharPositionInLine();
                    mNUMBER(); 
                    shiptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shiptypeStart7476, getCharIndex()-1);
                    shiptype.setLine(shiptypeStartLine7476);
                    shiptype.setCharPositionInLine(shiptypeStartCharPos7476);


                    mSEP(); 


                    match("\"vendorid\":"); 



                    int vendoridStart7489 = getCharIndex();
                    int vendoridStartLine7489 = getLine();
                    int vendoridStartCharPos7489 = getCharPositionInLine();
                    mNAME(); 
                    vendorid = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, vendoridStart7489, getCharIndex()-1);
                    vendorid.setLine(vendoridStartLine7489);
                    vendorid.setCharPositionInLine(vendoridStartCharPos7489);


                    mSEP(); 


                    match("\"callsign\":"); 



                    int callsignStart7503 = getCharIndex();
                    int callsignStartLine7503 = getLine();
                    int callsignStartCharPos7503 = getCharPositionInLine();
                    mNAME(); 
                    callsign = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, callsignStart7503, getCharIndex()-1);
                    callsign.setLine(callsignStartLine7503);
                    callsign.setCharPositionInLine(callsignStartCharPos7503);


                    mSEP(); 


                    match("\"to_bow\":"); 



                    int to_bowStart7517 = getCharIndex();
                    int to_bowStartLine7517 = getLine();
                    int to_bowStartCharPos7517 = getCharPositionInLine();
                    mNUMBER(); 
                    to_bow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_bowStart7517, getCharIndex()-1);
                    to_bow.setLine(to_bowStartLine7517);
                    to_bow.setCharPositionInLine(to_bowStartCharPos7517);


                    mSEP(); 


                    match("\"to_stern\":"); 



                    int to_sternStart7530 = getCharIndex();
                    int to_sternStartLine7530 = getLine();
                    int to_sternStartCharPos7530 = getCharPositionInLine();
                    mNUMBER(); 
                    to_stern = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_sternStart7530, getCharIndex()-1);
                    to_stern.setLine(to_sternStartLine7530);
                    to_stern.setCharPositionInLine(to_sternStartCharPos7530);


                    mSEP(); 


                    match("\"to_port\":"); 



                    int to_portStart7543 = getCharIndex();
                    int to_portStartLine7543 = getLine();
                    int to_portStartCharPos7543 = getCharPositionInLine();
                    mNUMBER(); 
                    to_port = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_portStart7543, getCharIndex()-1);
                    to_port.setLine(to_portStartLine7543);
                    to_port.setCharPositionInLine(to_portStartCharPos7543);


                    mSEP(); 


                    match("\"to_starboard\":"); 



                    int to_starboardStart7556 = getCharIndex();
                    int to_starboardStartLine7556 = getLine();
                    int to_starboardStartCharPos7556 = getCharPositionInLine();
                    mNUMBER(); 
                    to_starboard = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_starboardStart7556, getCharIndex()-1);
                    to_starboard.setLine(to_starboardStartLine7556);
                    to_starboard.setCharPositionInLine(to_starboardStartCharPos7556);


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:10: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )*
            loop231:
            do {
                int alt231=12;
                switch ( input.LA(1) ) {
                case '\"':
                    {
                    alt231=1;
                    }
                    break;
                case '[':
                    {
                    alt231=2;
                    }
                    break;
                case ']':
                    {
                    alt231=3;
                    }
                    break;
                case ':':
                    {
                    alt231=4;
                    }
                    break;
                case '/':
                    {
                    alt231=5;
                    }
                    break;
                case '}':
                    {
                    alt231=6;
                    }
                    break;
                case '_':
                    {
                    alt231=7;
                    }
                    break;
                case '#':
                    {
                    alt231=8;
                    }
                    break;
                case '.':
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
                    alt231=9;
                    }
                    break;
                case ' ':
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
                    alt231=10;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt231=11;
                    }
                    break;

                }

                switch (alt231) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:11: '\"'
            	    {
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:17: '['
            	    {
            	    match('['); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:23: ']'
            	    {
            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:29: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:35: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:42: '}'
            	    {
            	    match('}'); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:48: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:54: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:60: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 10 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:69: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 11 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1426:79: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;

            	default :
            	    break loop231;
                }
            } while (true);



                	switch(type.getText()){
            	case "1" :
            	case "3" :
            	  if(dev != null && mmsi != null && status != null && turn != null 
            	     && speed != null && longitude != null && latitude != null && course != null && heading != null && second != null){
            	     
            	     ais1 = new AIS1(new Float(turn.getText()), new Float(course.getText()), new Float(speed.getText()),
            	                         new Integer(status.getText()), new Float(heading.getText()), 
            	                         degConvert(new Float(latitude.getText())), degConvert(new Float(longitude.getText())),
            	                         new Integer(second.getText()), new Integer(mmsi.getText()), dev.getText()); 
            	  //System.out.println(ais1);
            	   aisHandler.doIt(ais1);
            	  }
                       break;
            	case "4" :
            	  if(dev != null && mmsi != null && timestamp != null && longitude != null && latitude != null){
            	  
            	    String date = timestamp.getText();
            	    String [] tmp0 = date.split("\"");
            	    String [] tmp1 = tmp0[1].split("T");
            	    
            	    String [] tmp2 = tmp1[0].split("-");
            	    year = new Integer(tmp2[0]);
            	    month = new Integer(tmp2[1]);
            	    day = new Integer(tmp2[2]);
            	    String [] tmp3 = tmp1[1].split(":");
            	    hours = new Integer(tmp3[0]);
            	    minutes  = new Integer(tmp3[1]);
            	    seconds = new Integer(tmp3[2].substring(0, 2));
            	  
            	    ais4 = new AIS4(new Integer(mmsi.getText()), device,
            	                         new GregorianCalendar(year, month, day, hours, minutes, seconds),
            	                         degConvert(new Float(latitude.getText())), degConvert(new Float(longitude.getText()))
            	                        );  
            	                                         
            	    //System.out.println(ais4);
            	    aisHandler.doIt(ais4);
            	 }
            	   break;
            	case "5" :
            	  if(dev != null && mmsi != null && imo != null && callsign != null && shipname != null &&
            	  to_bow != null && to_port != null && to_starboard != null && eta != null && draught != null && destination != null){

            	  String etaTmp = eta.getText();
            	  String [] tmp0 = etaTmp.split("\"");
            	  String [] tmp1 = tmp0[1].split("T");
            	  String [] tmp2 = tmp1[0].split("-");
            	  month = new Integer(tmp2[0]);
            	  day = new Integer(tmp2[1]);
            	  String [] tmp3 = tmp1[1].split(":");
            	  hours = new Integer(tmp3[0]);
            	  minutes  = new Integer(tmp3[1].substring(0, 2));

            	  date = new GregorianCalendar();
            	  date.set(Calendar.MONTH, month);
            	  date.set(Calendar.DATE, day);
            	  date.set(Calendar.HOUR, hours);
            	  date.set(Calendar.MINUTE, minutes);
            	  
            	  
            	  ais5 = new AIS5(new Integer(mmsi.getText()), device, 
            	                        new Integer(imo.getText()), shipname.getText(), new Integer(shiptype.getText()),
            	                        new Integer(to_starboard.getText())*2, new Integer(to_bow.getText())+ new Integer(to_stern.getText()),
            	                        new Integer(draught.getText()), callsign.getText(), date, destination.getText());
            	  aisHandler.doIt(ais5);
            	  }
            	  
            	   break;
            	case "8" :
            	   System.out.println("ais8");
            	   break;
            	case "18":
                      if(dev != null && mmsi != null && speed != null && longitude != null && latitude != null &&
                         course != null && heading != null && second != null){
                          
                       ais18 = new AIS18(new Integer(mmsi.getText()), dev.getText(),
                               new Float(speed.getText()), new Float(course.getText()), new Float(heading.getText()), 
                               degConvert(new Float(latitude.getText())), degConvert(new Float(longitude.getText())),
                               new Integer(second.getText()));
                               
                        //System.out.println(ais18); 
                        aisHandler.doIt(ais18);                       
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
    // $ANTLR end "GPSD_AIS"

    // $ANTLR start "GPSD_DEVICE"
    public final void mGPSD_DEVICE() throws RecognitionException {
        try {
            int _type = GPSD_DEVICE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1517:9: ( '{\"class\":\"DEVICE\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:6: '{\"class\":\"DEVICE\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            {
            match("{\"class\":\"DEVICE\""); 



            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:26: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            loop232:
            do {
                int alt232=13;
                switch ( input.LA(1) ) {
                case '\"':
                    {
                    alt232=1;
                    }
                    break;
                case '[':
                    {
                    alt232=2;
                    }
                    break;
                case ']':
                    {
                    alt232=3;
                    }
                    break;
                case ':':
                    {
                    alt232=4;
                    }
                    break;
                case '/':
                    {
                    alt232=5;
                    }
                    break;
                case '}':
                    {
                    alt232=6;
                    }
                    break;
                case '_':
                    {
                    alt232=7;
                    }
                    break;
                case '#':
                    {
                    alt232=8;
                    }
                    break;
                case ',':
                    {
                    alt232=9;
                    }
                    break;
                case '.':
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
                    alt232=10;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt232=11;
                    }
                    break;
                case ' ':
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
                    alt232=12;
                    }
                    break;

                }

                switch (alt232) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:27: '\"'
            	    {
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:33: '['
            	    {
            	    match('['); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:39: ']'
            	    {
            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:45: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:51: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:58: '}'
            	    {
            	    match('}'); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:64: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:70: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:77: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;
            	case 10 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:83: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 11 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:92: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;
            	case 12 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1518:99: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop232;
                }
            } while (true);



            	 //System.out.println("GPSD DEVICE sentence : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GPSD_DEVICE"

    // $ANTLR start "GPSD_DEVICES"
    public final void mGPSD_DEVICES() throws RecognitionException {
        try {
            int _type = GPSD_DEVICES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1524:9: ( '{\"class\":\"DEVICES\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:6: '{\"class\":\"DEVICES\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            {
            match("{\"class\":\"DEVICES\""); 



            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:27: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            loop233:
            do {
                int alt233=13;
                switch ( input.LA(1) ) {
                case '\"':
                    {
                    alt233=1;
                    }
                    break;
                case '[':
                    {
                    alt233=2;
                    }
                    break;
                case ']':
                    {
                    alt233=3;
                    }
                    break;
                case ':':
                    {
                    alt233=4;
                    }
                    break;
                case '/':
                    {
                    alt233=5;
                    }
                    break;
                case '}':
                    {
                    alt233=6;
                    }
                    break;
                case '_':
                    {
                    alt233=7;
                    }
                    break;
                case '#':
                    {
                    alt233=8;
                    }
                    break;
                case ',':
                    {
                    alt233=9;
                    }
                    break;
                case '.':
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
                    alt233=10;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt233=11;
                    }
                    break;
                case ' ':
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
                    alt233=12;
                    }
                    break;

                }

                switch (alt233) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:28: '\"'
            	    {
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:34: '['
            	    {
            	    match('['); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:40: ']'
            	    {
            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:46: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:52: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:59: '}'
            	    {
            	    match('}'); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:65: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:71: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:78: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;
            	case 10 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:84: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 11 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:93: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;
            	case 12 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1525:100: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop233;
                }
            } while (true);



            	//System.out.println("GPSD DEVICES sentence : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GPSD_DEVICES"

    // $ANTLR start "GPSD_VERSION"
    public final void mGPSD_VERSION() throws RecognitionException {
        try {
            int _type = GPSD_VERSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1531:6: ( '{\"class\":\"VERSION\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:6: '{\"class\":\"VERSION\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            {
            match("{\"class\":\"VERSION\""); 



            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:27: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            loop234:
            do {
                int alt234=13;
                switch ( input.LA(1) ) {
                case '\"':
                    {
                    alt234=1;
                    }
                    break;
                case '[':
                    {
                    alt234=2;
                    }
                    break;
                case ']':
                    {
                    alt234=3;
                    }
                    break;
                case ':':
                    {
                    alt234=4;
                    }
                    break;
                case '/':
                    {
                    alt234=5;
                    }
                    break;
                case '}':
                    {
                    alt234=6;
                    }
                    break;
                case '_':
                    {
                    alt234=7;
                    }
                    break;
                case '#':
                    {
                    alt234=8;
                    }
                    break;
                case ',':
                    {
                    alt234=9;
                    }
                    break;
                case '.':
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
                    alt234=10;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt234=11;
                    }
                    break;
                case ' ':
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
                    alt234=12;
                    }
                    break;

                }

                switch (alt234) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:28: '\"'
            	    {
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:34: '['
            	    {
            	    match('['); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:40: ']'
            	    {
            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:46: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:52: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:59: '}'
            	    {
            	    match('}'); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:65: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:71: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:78: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;
            	case 10 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:84: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 11 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:93: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;
            	case 12 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1532:100: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop234;
                }
            } while (true);



            	//System.out.println("GPSD VERSION sentence : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GPSD_VERSION"

    // $ANTLR start "GPSD_WATCH"
    public final void mGPSD_WATCH() throws RecognitionException {
        try {
            int _type = GPSD_WATCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1538:6: ( '{\"class\":\"WATCH\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:6: '{\"class\":\"WATCH\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            {
            match("{\"class\":\"WATCH\""); 



            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:25: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
            loop235:
            do {
                int alt235=13;
                switch ( input.LA(1) ) {
                case '\"':
                    {
                    alt235=1;
                    }
                    break;
                case '[':
                    {
                    alt235=2;
                    }
                    break;
                case ']':
                    {
                    alt235=3;
                    }
                    break;
                case ':':
                    {
                    alt235=4;
                    }
                    break;
                case '/':
                    {
                    alt235=5;
                    }
                    break;
                case '}':
                    {
                    alt235=6;
                    }
                    break;
                case '_':
                    {
                    alt235=7;
                    }
                    break;
                case '#':
                    {
                    alt235=8;
                    }
                    break;
                case ',':
                    {
                    alt235=9;
                    }
                    break;
                case '.':
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
                    alt235=10;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt235=11;
                    }
                    break;
                case ' ':
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
                    alt235=12;
                    }
                    break;

                }

                switch (alt235) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:26: '\"'
            	    {
            	    match('\"'); 

            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:32: '['
            	    {
            	    match('['); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:38: ']'
            	    {
            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:44: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:50: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:57: '}'
            	    {
            	    match('}'); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:63: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:69: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:76: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;
            	case 10 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:82: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 11 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:91: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;
            	case 12 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1539:98: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;

            	default :
            	    break loop235;
                }
            } while (true);



            	//System.out.println("GPSD WATCH sentence : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GPSD_WATCH"

    // $ANTLR start "TXT"
    public final void mTXT() throws RecognitionException {
        try {
            int _type = TXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken device=null;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1546:5: ( ( '$' ) device= DEVICE 'TXT' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1546:7: ( '$' ) device= DEVICE 'TXT' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1546:7: ( '$' )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1546:8: '$'
            {
            match('$'); 

            }


            int deviceStart7997 = getCharIndex();
            int deviceStartLine7997 = getLine();
            int deviceStartCharPos7997 = getCharPositionInLine();
            mDEVICE(); 
            device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart7997, getCharIndex()-1);
            device.setLine(deviceStartLine7997);
            device.setCharPositionInLine(deviceStartCharPos7997);


            match("TXT"); 



            mSEP(); 


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1547:2: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
            loop236:
            do {
                int alt236=2;
                alt236 = dfa236.predict(input);
                switch (alt236) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= ' ' && input.LA(1) <= '\u007F') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	      //  throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop236;
                }
            } while (true);


            int checksumStart8024 = getCharIndex();
            int checksumStartLine8024 = getLine();
            int checksumStartCharPos8024 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart8024, getCharIndex()-1);
            checksum.setLine(checksumStartLine8024);
            checksum.setCharPositionInLine(checksumStartCharPos8024);



            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TXT"

    // $ANTLR start "PRO"
    public final void mPRO() throws RecognitionException {
        try {
            int _type = PRO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken checksum=null;

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:5: ( ( '$PR' | '$PG' | '$PS' ) ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:7: ( '$PR' | '$PG' | '$PS' ) ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:7: ( '$PR' | '$PG' | '$PS' )
            int alt237=3;
            int LA237_0 = input.LA(1);

            if ( (LA237_0=='$') ) {
                int LA237_1 = input.LA(2);

                if ( (LA237_1=='P') ) {
                    switch ( input.LA(3) ) {
                    case 'R':
                        {
                        alt237=1;
                        }
                        break;
                    case 'G':
                        {
                        alt237=2;
                        }
                        break;
                    case 'S':
                        {
                        alt237=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 237, 2, input);

                       // throw nvae;

                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 237, 1, input);

                  //  throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 237, 0, input);

               // throw nvae;

            }
            switch (alt237) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:8: '$PR'
                    {
                    match("$PR"); 



                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:15: '$PG'
                    {
                    match("$PG"); 



                    }
                    break;
                case 3 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:22: '$PS'
                    {
                    match("$PS"); 



                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1559:29: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
            loop238:
            do {
                int alt238=2;
                alt238 = dfa238.predict(input);
                switch (alt238) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( (input.LA(1) >= ' ' && input.LA(1) <= '\u007F') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	      //  throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop238;
                }
            } while (true);


            int checksumStart8070 = getCharIndex();
            int checksumStartLine8070 = getLine();
            int checksumStartCharPos8070 = getCharPositionInLine();
            mCHECKSUM(); 
            checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart8070, getCharIndex()-1);
            checksum.setLine(checksumStartLine8070);
            checksum.setCharPositionInLine(checksumStartCharPos8070);



            	//System.out.println("Proprietary sentence : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PRO"

    // $ANTLR start "DEVICE"
    public final void mDEVICE() throws RecognitionException {
        try {
            int _type = DEVICE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1563:9: ( ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' ) )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:4: ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' )
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:4: ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' )
            int alt239=35;
            switch ( input.LA(1) ) {
            case 'G':
                {
                alt239=1;
                }
                break;
            case 'I':
                {
                int LA239_2 = input.LA(2);

                if ( (LA239_2=='I') ) {
                    alt239=2;
                }
                else if ( (LA239_2=='N') ) {
                    alt239=19;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 2, input);

                   // throw nvae;

                }
                }
                break;
            case 'A':
                {
                switch ( input.LA(2) ) {
                case 'G':
                    {
                    alt239=3;
                    }
                    break;
                case 'I':
                    {
                    alt239=4;
                    }
                    break;
                case 'P':
                    {
                    alt239=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 3, input);

                   // throw nvae;

                }

                }
                break;
            case 'C':
                {
                switch ( input.LA(2) ) {
                case 'C':
                    {
                    alt239=6;
                    }
                    break;
                case 'D':
                    {
                    alt239=7;
                    }
                    break;
                case 'S':
                    {
                    alt239=8;
                    }
                    break;
                case 'T':
                    {
                    alt239=9;
                    }
                    break;
                case 'V':
                    {
                    alt239=10;
                    }
                    break;
                case 'X':
                    {
                    alt239=11;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 4, input);

                 //   throw nvae;

                }

                }
                break;
            case 'D':
                {
                int LA239_5 = input.LA(2);

                if ( (LA239_5=='F') ) {
                    alt239=12;
                }
                else if ( (LA239_5=='M') ) {
                    alt239=28;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 5, input);

                  //  throw nvae;

                }
                }
                break;
            case 'E':
                {
                switch ( input.LA(2) ) {
                case 'C':
                    {
                    alt239=13;
                    }
                    break;
                case 'P':
                    {
                    alt239=14;
                    }
                    break;
                case 'R':
                    {
                    alt239=15;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 6, input);

                  //  throw nvae;

                }

                }
                break;
            case 'H':
                {
                switch ( input.LA(2) ) {
                case 'C':
                    {
                    alt239=16;
                    }
                    break;
                case 'E':
                    {
                    alt239=17;
                    }
                    break;
                case 'N':
                    {
                    alt239=18;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 7, input);

                   // throw nvae;

                }

                }
                break;
            case 'R':
                {
                alt239=20;
                }
                break;
            case 'S':
                {
                switch ( input.LA(2) ) {
                case 'D':
                    {
                    alt239=21;
                    }
                    break;
                case 'M':
                    {
                    alt239=22;
                    }
                    break;
                case 'N':
                    {
                    alt239=23;
                    }
                    break;
                case 'S':
                    {
                    alt239=24;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 9, input);

                  //  throw nvae;

                }

                }
                break;
            case 'T':
                {
                int LA239_10 = input.LA(2);

                if ( (LA239_10=='I') ) {
                    alt239=25;
                }
                else if ( (LA239_10=='R') ) {
                    alt239=26;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 10, input);

                  //  throw nvae;

                }
                }
                break;
            case 'V':
                {
                int LA239_11 = input.LA(2);

                if ( (LA239_11=='D') ) {
                    alt239=27;
                }
                else if ( (LA239_11=='W') ) {
                    alt239=29;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 11, input);

                 //   throw nvae;

                }
                }
                break;
            case 'W':
                {
                alt239=30;
                }
                break;
            case 'Y':
                {
                alt239=31;
                }
                break;
            case 'Z':
                {
                switch ( input.LA(2) ) {
                case 'A':
                    {
                    alt239=32;
                    }
                    break;
                case 'C':
                    {
                    alt239=33;
                    }
                    break;
                case 'Q':
                    {
                    alt239=34;
                    }
                    break;
                case 'V':
                    {
                    alt239=35;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 239, 14, input);

                    throw nvae;

                }

                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 239, 0, input);

                throw nvae;

            }

            switch (alt239) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:5: 'GP'
                    {
                    match("GP"); 



                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:10: 'II'
                    {
                    match("II"); 



                    }
                    break;
                case 3 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:15: 'AG'
                    {
                    match("AG"); 



                    }
                    break;
                case 4 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:20: 'AI'
                    {
                    match("AI"); 



                    }
                    break;
                case 5 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:25: 'AP'
                    {
                    match("AP"); 



                    }
                    break;
                case 6 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:30: 'CC'
                    {
                    match("CC"); 



                    }
                    break;
                case 7 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:35: 'CD'
                    {
                    match("CD"); 



                    }
                    break;
                case 8 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:40: 'CS'
                    {
                    match("CS"); 



                    }
                    break;
                case 9 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:45: 'CT'
                    {
                    match("CT"); 



                    }
                    break;
                case 10 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:50: 'CV'
                    {
                    match("CV"); 



                    }
                    break;
                case 11 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:55: 'CX'
                    {
                    match("CX"); 



                    }
                    break;
                case 12 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:60: 'DF'
                    {
                    match("DF"); 



                    }
                    break;
                case 13 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:65: 'EC'
                    {
                    match("EC"); 



                    }
                    break;
                case 14 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:70: 'EP'
                    {
                    match("EP"); 



                    }
                    break;
                case 15 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:75: 'ER'
                    {
                    match("ER"); 



                    }
                    break;
                case 16 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:80: 'HC'
                    {
                    match("HC"); 



                    }
                    break;
                case 17 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:85: 'HE'
                    {
                    match("HE"); 



                    }
                    break;
                case 18 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:90: 'HN'
                    {
                    match("HN"); 



                    }
                    break;
                case 19 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:95: 'IN'
                    {
                    match("IN"); 



                    }
                    break;
                case 20 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:100: 'RA'
                    {
                    match("RA"); 



                    }
                    break;
                case 21 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:105: 'SD'
                    {
                    match("SD"); 



                    }
                    break;
                case 22 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:110: 'SM'
                    {
                    match("SM"); 



                    }
                    break;
                case 23 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:115: 'SN'
                    {
                    match("SN"); 



                    }
                    break;
                case 24 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:120: 'SS'
                    {
                    match("SS"); 



                    }
                    break;
                case 25 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:125: 'TI'
                    {
                    match("TI"); 



                    }
                    break;
                case 26 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:130: 'TR'
                    {
                    match("TR"); 



                    }
                    break;
                case 27 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:135: 'VD'
                    {
                    match("VD"); 



                    }
                    break;
                case 28 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:140: 'DM'
                    {
                    match("DM"); 



                    }
                    break;
                case 29 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:145: 'VW'
                    {
                    match("VW"); 



                    }
                    break;
                case 30 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:150: 'WI'
                    {
                    match("WI"); 



                    }
                    break;
                case 31 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:155: 'YX'
                    {
                    match("YX"); 



                    }
                    break;
                case 32 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:160: 'ZA'
                    {
                    match("ZA"); 



                    }
                    break;
                case 33 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:165: 'ZC'
                    {
                    match("ZC"); 



                    }
                    break;
                case 34 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:170: 'ZQ'
                    {
                    match("ZQ"); 



                    }
                    break;
                case 35 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1564:175: 'ZV'
                    {
                    match("ZV"); 



                    }
                    break;

            }



            	// System.out.println("Device : " + getText());
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEVICE"

    // $ANTLR start "DEV"
    public final void mDEV() throws RecognitionException {
        try {
            int _type = DEV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1569:9: ( '\"' ( LETTERS | '/' | ':' | '#' | NUMBER )* '\"' )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:2: '\"' ( LETTERS | '/' | ':' | '#' | NUMBER )* '\"'
            {
            match('\"'); 

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:5: ( LETTERS | '/' | ':' | '#' | NUMBER )*
            loop240:
            do {
                int alt240=6;
                switch ( input.LA(1) ) {
                case ' ':
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
                    alt240=1;
                    }
                    break;
                case '/':
                    {
                    alt240=2;
                    }
                    break;
                case ':':
                    {
                    alt240=3;
                    }
                    break;
                case '#':
                    {
                    alt240=4;
                    }
                    break;
                case '.':
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
                    alt240=5;
                    }
                    break;

                }

                switch (alt240) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:7: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:16: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:22: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:28: '#'
            	    {
            	    match('#'); 

            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1570:34: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;

            	default :
            	    break loop240;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEV"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1574:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )* ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt248=4;
            alt248 = dfa248.predict(input);
            switch (alt248) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1575:5: ( '0' .. '9' )+
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1575:5: ( '0' .. '9' )+
                    int cnt241=0;
                    loop241:
                    do {
                        int alt241=2;
                        int LA241_0 = input.LA(1);

                        if ( ((LA241_0 >= '0' && LA241_0 <= '9')) ) {
                            alt241=1;
                        }


                        switch (alt241) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    if ( cnt241 >= 1 ) break loop241;
                                EarlyExitException eee =
                                    new EarlyExitException(241, input);
                                throw eee;
                        }
                        cnt241++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1577:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1577:5: ( '0' .. '9' )+
                    int cnt242=0;
                    loop242:
                    do {
                        int alt242=2;
                        int LA242_0 = input.LA(1);

                        if ( ((LA242_0 >= '0' && LA242_0 <= '9')) ) {
                            alt242=1;
                        }


                        switch (alt242) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    if ( cnt242 >= 1 ) break loop242;
                                EarlyExitException eee =
                                    new EarlyExitException(242, input);
                                throw eee;
                        }
                        cnt242++;
                    } while (true);


                    match('.'); 

                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1577:21: ( '0' .. '9' )*
                    loop243:
                    do {
                        int alt243=2;
                        int LA243_0 = input.LA(1);

                        if ( ((LA243_0 >= '0' && LA243_0 <= '9')) ) {
                            alt243=1;
                        }


                        switch (alt243) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    break loop243;
                        }
                    } while (true);


                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1577:33: ( EXPONENT )?
                    int alt244=2;
                    int LA244_0 = input.LA(1);

                    if ( (LA244_0=='E'||LA244_0=='e') ) {
                        alt244=1;
                    }
                    switch (alt244) {
                        case 1 :
                            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1577:33: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1578:9: '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    match('.'); 

                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1578:13: ( '0' .. '9' )*
                    loop245:
                    do {
                        int alt245=2;
                        int LA245_0 = input.LA(1);

                        if ( ((LA245_0 >= '0' && LA245_0 <= '9')) ) {
                            alt245=1;
                        }


                        switch (alt245) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    break loop245;
                        }
                    } while (true);


                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1578:25: ( EXPONENT )?
                    int alt246=2;
                    int LA246_0 = input.LA(1);

                    if ( (LA246_0=='E'||LA246_0=='e') ) {
                        alt246=1;
                    }
                    switch (alt246) {
                        case 1 :
                            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1578:25: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1579:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1579:9: ( '0' .. '9' )+
                    int cnt247=0;
                    loop247:
                    do {
                        int alt247=2;
                        int LA247_0 = input.LA(1);

                        if ( ((LA247_0 >= '0' && LA247_0 <= '9')) ) {
                            alt247=1;
                        }


                        switch (alt247) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    if ( cnt247 >= 1 ) break loop247;
                                EarlyExitException eee =
                                    new EarlyExitException(247, input);
                                throw eee;
                        }
                        cnt247++;
                    } while (true);


                    mEXPONENT(); 


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
    // $ANTLR end "NUMBER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1582:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1582:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "SEP"
    public final void mSEP() throws RecognitionException {
        try {
            int _type = SEP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1589:5: ( ( ',' ) )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1589:7: ( ',' )
            {
            if ( input.LA(1)==',' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEP"

    // $ANTLR start "SIGN"
    public final void mSIGN() throws RecognitionException {
        try {
            int _type = SIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1593:6: ( ( '+' | '-' ) )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGN"

    // $ANTLR start "SIGNED"
    public final void mSIGNED() throws RecognitionException {
        try {
            int _type = SIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1596:8: ( ( SIGN )? NUMBER )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1597:5: ( SIGN )? NUMBER
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1597:5: ( SIGN )?
            int alt249=2;
            int LA249_0 = input.LA(1);

            if ( (LA249_0=='+'||LA249_0=='-') ) {
                alt249=1;
            }
            switch (alt249) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            mNUMBER(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGNED"

    // $ANTLR start "TIME_STAMP"
    public final void mTIME_STAMP() throws RecognitionException {
        try {
            int _type = TIME_STAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1601:2: ( '\"' ( LETTERS | NUMBER | ':' | SIGN )+ '\"' )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:2: '\"' ( LETTERS | NUMBER | ':' | SIGN )+ '\"'
            {
            match('\"'); 

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:6: ( LETTERS | NUMBER | ':' | SIGN )+
            int cnt250=0;
            loop250:
            do {
                int alt250=5;
                switch ( input.LA(1) ) {
                case ' ':
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
                    alt250=1;
                    }
                    break;
                case '.':
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
                    alt250=2;
                    }
                    break;
                case ':':
                    {
                    alt250=3;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt250=4;
                    }
                    break;

                }

                switch (alt250) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:7: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:17: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:27: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1602:33: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;

            	default :
            	    if ( cnt250 >= 1 ) break loop250;
                        EarlyExitException eee =
                            new EarlyExitException(250, input);
                        throw eee;
                }
                cnt250++;
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TIME_STAMP"

    // $ANTLR start "CHECKSUM"
    public final void mCHECKSUM() throws RecognitionException {
        try {
            int _type = CHECKSUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:10: ( ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) ) )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:12: ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) )
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:12: ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) )
            int alt252=4;
            int LA252_0 = input.LA(1);

            if ( (LA252_0=='*') ) {
                int LA252_1 = input.LA(2);

                if ( ((LA252_1 >= '0' && LA252_1 <= '9')) ) {
                    int LA252_2 = input.LA(3);

                    if ( ((LA252_2 >= '0' && LA252_2 <= '9')) ) {
                        int LA252_4 = input.LA(4);

                        if ( ((LA252_4 >= '0' && LA252_4 <= '9')||(LA252_4 >= 'A' && LA252_4 <= 'F')) ) {
                            alt252=4;
                        }
                        else {
                            alt252=1;
                        }
                    }
                    else if ( ((LA252_2 >= 'A' && LA252_2 <= 'F')) ) {
                        alt252=4;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 252, 2, input);

                        throw nvae;

                    }
                }
                else if ( ((LA252_1 >= 'A' && LA252_1 <= 'F')) ) {
                    int LA252_3 = input.LA(3);

                    if ( ((LA252_3 >= '0' && LA252_3 <= '9')) ) {
                        alt252=2;
                    }
                    else if ( ((LA252_3 >= 'A' && LA252_3 <= 'F')) ) {
                        alt252=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 252, 3, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 252, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 252, 0, input);

                throw nvae;

            }
            switch (alt252) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:13: ( '*' ( '0' .. '9' ) ( '0' .. '9' ) )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:13: ( '*' ( '0' .. '9' ) ( '0' .. '9' ) )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1604:14: '*' ( '0' .. '9' ) ( '0' .. '9' )
                    {
                    match('*'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }


                    }
                    break;
                case 2 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1605:13: ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1605:13: ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1605:14: '*' ( 'A' .. 'F' ) ( '0' .. '9' )
                    {
                    match('*'); 

                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }


                    }
                    break;
                case 3 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1606:13: ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1606:13: ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1606:14: '*' ( 'A' .. 'F' ) ( 'A' .. 'F' )
                    {
                    match('*'); 

                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }


                    }
                    break;
                case 4 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1607:13: ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) )
                    {
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1607:13: ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) )
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1607:14: '*' ( '0' .. '9' )+ ( 'A' .. 'F' )
                    {
                    match('*'); 

                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1607:17: ( '0' .. '9' )+
                    int cnt251=0;
                    loop251:
                    do {
                        int alt251=2;
                        int LA251_0 = input.LA(1);

                        if ( ((LA251_0 >= '0' && LA251_0 <= '9')) ) {
                            alt251=1;
                        }


                        switch (alt251) {
                    	case 1 :
                    	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
                    	    if ( cnt251 >= 1 ) break loop251;
                                EarlyExitException eee =
                                    new EarlyExitException(251, input);
                                throw eee;
                        }
                        cnt251++;
                    } while (true);


                    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHECKSUM"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1610:3: ( '\"' ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' )* '\"' )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:3: '\"' ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' )* '\"'
            {
            match('\"'); 

            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:7: ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' )*
            loop253:
            do {
                int alt253=10;
                switch ( input.LA(1) ) {
                case ' ':
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
                    alt253=1;
                    }
                    break;
                case '.':
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
                    alt253=2;
                    }
                    break;
                case ':':
                    {
                    alt253=3;
                    }
                    break;
                case '+':
                case '-':
                    {
                    alt253=4;
                    }
                    break;
                case '/':
                    {
                    alt253=5;
                    }
                    break;
                case '\'':
                    {
                    alt253=6;
                    }
                    break;
                case ',':
                    {
                    alt253=7;
                    }
                    break;
                case '%':
                    {
                    alt253=8;
                    }
                    break;
                case '!':
                    {
                    alt253=9;
                    }
                    break;

                }

                switch (alt253) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:8: LETTERS
            	    {
            	    mLETTERS(); 


            	    }
            	    break;
            	case 2 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:18: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 3 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:28: ':'
            	    {
            	    match(':'); 

            	    }
            	    break;
            	case 4 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:34: SIGN
            	    {
            	    mSIGN(); 


            	    }
            	    break;
            	case 5 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:41: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;
            	case 6 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:47: '\\''
            	    {
            	    match('\''); 

            	    }
            	    break;
            	case 7 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:54: SEP
            	    {
            	    mSEP(); 


            	    }
            	    break;
            	case 8 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:60: '%'
            	    {
            	    match('%'); 

            	    }
            	    break;
            	case 9 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1611:66: '!'
            	    {
            	    match('!'); 

            	    }
            	    break;

            	default :
            	    break loop253;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "LETTERS"
    public final void mLETTERS() throws RecognitionException {
        try {
            int _type = LETTERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1613:9: ( ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+ )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1613:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+
            {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1613:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+
            int cnt254=0;
            loop254:
            do {
                int alt254=2;
                int LA254_0 = input.LA(1);

                if ( (LA254_0==' '||(LA254_0 >= 'A' && LA254_0 <= 'Z')||(LA254_0 >= 'a' && LA254_0 <= 'z')) ) {
                    alt254=1;
                }


                switch (alt254) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    if ( cnt254 >= 1 ) break loop254;
                        EarlyExitException eee =
                            new EarlyExitException(254, input);
                        throw eee;
                }
                cnt254++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTERS"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1619:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1619:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1619:22: ( '+' | '-' )?
            int alt255=2;
            int LA255_0 = input.LA(1);

            if ( (LA255_0=='+'||LA255_0=='-') ) {
                alt255=1;
            }
            switch (alt255) {
                case 1 :
                    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1619:33: ( '0' .. '9' )+
            int cnt256=0;
            loop256:
            do {
                int alt256=2;
                int LA256_0 = input.LA(1);

                if ( ((LA256_0 >= '0' && LA256_0 <= '9')) ) {
                    alt256=1;
                }


                switch (alt256) {
            	case 1 :
            	    // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:
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
            	    if ( cnt256 >= 1 ) break loop256;
                        EarlyExitException eee =
                            new EarlyExitException(256, input);
                        throw eee;
                }
                cnt256++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    public void mTokens() throws RecognitionException {
        // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:8: ( AAM | APB | BEC | BOD | BWC | BWR | BWW | DBT | DBK | DBS | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RSD | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | ALR | VDM | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | TXT | PRO | DEVICE | DEV | NUMBER | WS | SEP | SIGN | SIGNED | TIME_STAMP | CHECKSUM | NAME | LETTERS )
        int alt257=56;
        alt257 = dfa257.predict(input);
        switch (alt257) {
            case 1 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:10: AAM
                {
                mAAM(); 


                }
                break;
            case 2 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:14: APB
                {
                mAPB(); 


                }
                break;
            case 3 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:18: BEC
                {
                mBEC(); 


                }
                break;
            case 4 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:22: BOD
                {
                mBOD(); 


                }
                break;
            case 5 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:26: BWC
                {
                mBWC(); 


                }
                break;
            case 6 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:30: BWR
                {
                mBWR(); 


                }
                break;
            case 7 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:34: BWW
                {
                mBWW(); 


                }
                break;
            case 8 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:38: DBT
                {
                mDBT(); 


                }
                break;
            case 9 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:42: DBK
                {
                mDBK(); 


                }
                break;
            case 10 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:46: DBS
                {
                mDBS(); 


                }
                break;
            case 11 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:50: DPT
                {
                mDPT(); 


                }
                break;
            case 12 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:54: GGA
                {
                mGGA(); 


                }
                break;
            case 13 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:58: GLL
                {
                mGLL(); 


                }
                break;
            case 14 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:62: GSA
                {
                mGSA(); 


                }
                break;
            case 15 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:66: GSV
                {
                mGSV(); 


                }
                break;
            case 16 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:70: HDG
                {
                mHDG(); 


                }
                break;
            case 17 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:74: HDM
                {
                mHDM(); 


                }
                break;
            case 18 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:78: HDT
                {
                mHDT(); 


                }
                break;
            case 19 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:82: MSK
                {
                mMSK(); 


                }
                break;
            case 20 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:86: MTA
                {
                mMTA(); 


                }
                break;
            case 21 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:90: MTW
                {
                mMTW(); 


                }
                break;
            case 22 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:94: MWD
                {
                mMWD(); 


                }
                break;
            case 23 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:98: MWV
                {
                mMWV(); 


                }
                break;
            case 24 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:102: RMB
                {
                mRMB(); 


                }
                break;
            case 25 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:106: RMC
                {
                mRMC(); 


                }
                break;
            case 26 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:110: RSD
                {
                mRSD(); 


                }
                break;
            case 27 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:114: RTE
                {
                mRTE(); 


                }
                break;
            case 28 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:118: VBW
                {
                mVBW(); 


                }
                break;
            case 29 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:122: VLW
                {
                mVLW(); 


                }
                break;
            case 30 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:126: VHW
                {
                mVHW(); 


                }
                break;
            case 31 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:130: VPW
                {
                mVPW(); 


                }
                break;
            case 32 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:134: VTG
                {
                mVTG(); 


                }
                break;
            case 33 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:138: VWR
                {
                mVWR(); 


                }
                break;
            case 34 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:142: VWT
                {
                mVWT(); 


                }
                break;
            case 35 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:146: XTE
                {
                mXTE(); 


                }
                break;
            case 36 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:150: ZDA
                {
                mZDA(); 


                }
                break;
            case 37 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:154: ALR
                {
                mALR(); 


                }
                break;
            case 38 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:158: VDM
                {
                mVDM(); 


                }
                break;
            case 39 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:162: GPSD_AIS
                {
                mGPSD_AIS(); 


                }
                break;
            case 40 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:171: GPSD_DEVICE
                {
                mGPSD_DEVICE(); 


                }
                break;
            case 41 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:183: GPSD_DEVICES
                {
                mGPSD_DEVICES(); 


                }
                break;
            case 42 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:196: GPSD_VERSION
                {
                mGPSD_VERSION(); 


                }
                break;
            case 43 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:209: GPSD_WATCH
                {
                mGPSD_WATCH(); 


                }
                break;
            case 44 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:220: TXT
                {
                mTXT(); 


                }
                break;
            case 45 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:224: PRO
                {
                mPRO(); 


                }
                break;
            case 46 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:228: DEVICE
                {
                mDEVICE(); 


                }
                break;
            case 47 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:235: DEV
                {
                mDEV(); 


                }
                break;
            case 48 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:239: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 49 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:246: WS
                {
                mWS(); 


                }
                break;
            case 50 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:249: SEP
                {
                mSEP(); 


                }
                break;
            case 51 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:253: SIGN
                {
                mSIGN(); 


                }
                break;
            case 52 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:258: SIGNED
                {
                mSIGNED(); 


                }
                break;
            case 53 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:265: TIME_STAMP
                {
                mTIME_STAMP(); 


                }
                break;
            case 54 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:276: CHECKSUM
                {
                mCHECKSUM(); 


                }
                break;
            case 55 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:285: NAME
                {
                mNAME(); 


                }
                break;
            case 56 :
                // I:\\developpement\\projetNaVisu\\navisu\\trunk\\navisu\\navisu-domain\\src\\main\\java\\bzh\\terrevirtuelle\\navisu\\nmea\\controller\\parser\\impl\\NMEA.g:1:290: LETTERS
                {
                mLETTERS(); 


                }
                break;

        }

    }


    protected DFA67 dfa67 = new DFA67(this);
    protected DFA84 dfa84 = new DFA84(this);
    protected DFA126 dfa126 = new DFA126(this);
    protected DFA147 dfa147 = new DFA147(this);
    protected DFA161 dfa161 = new DFA161(this);
    protected DFA208 dfa208 = new DFA208(this);
    protected DFA215 dfa215 = new DFA215(this);
    protected DFA220 dfa220 = new DFA220(this);
    protected DFA226 dfa226 = new DFA226(this);
    protected DFA236 dfa236 = new DFA236(this);
    protected DFA238 dfa238 = new DFA238(this);
    protected DFA248 dfa248 = new DFA248(this);
    protected DFA257 dfa257 = new DFA257(this);
    static final String DFA67_eotS =
        "\1\uffff\2\3\2\uffff\1\3\1\uffff\1\3\1\uffff\1\3\2\uffff\1\3\1\uffff"+
        "\1\3\1\uffff\1\3";
    static final String DFA67_eofS =
        "\21\uffff";
    static final String DFA67_minS =
        "\1\56\2\54\2\uffff\1\54\1\53\1\54\1\53\1\54\1\53\1\60\1\54\1\60"+
        "\1\54\1\60\1\54";
    static final String DFA67_maxS =
        "\1\71\2\145\2\uffff\1\145\1\71\1\145\1\71\1\145\7\71";
    static final String DFA67_acceptS =
        "\3\uffff\1\2\1\1\14\uffff";
    static final String DFA67_specialS =
        "\21\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\1\5\1\uffff\12\1\13\uffff\1\6\37\uffff\1\6",
            "\1\4\3\uffff\12\7\13\uffff\1\10\37\uffff\1\10",
            "",
            "",
            "\1\4\3\uffff\12\11\13\uffff\1\12\37\uffff\1\12",
            "\1\13\1\uffff\1\13\2\uffff\12\14",
            "\1\4\3\uffff\12\7\13\uffff\1\10\37\uffff\1\10",
            "\1\15\1\uffff\1\15\2\uffff\12\16",
            "\1\4\3\uffff\12\11\13\uffff\1\12\37\uffff\1\12",
            "\1\17\1\uffff\1\17\2\uffff\12\20",
            "\12\14",
            "\1\4\3\uffff\12\14",
            "\12\16",
            "\1\4\3\uffff\12\16",
            "\12\20",
            "\1\4\3\uffff\12\20"
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "545:9: (offset= NUMBER SEP |offset= NUMBER )";
        }
    }
    static final String DFA84_eotS =
        "\34\uffff";
    static final String DFA84_eofS =
        "\34\uffff";
    static final String DFA84_minS =
        "\1\40\1\uffff\4\40\1\uffff\3\40\1\60\3\40\1\60\3\40\1\60\1\40\1"+
        "\60\2\40\1\60\4\40";
    static final String DFA84_maxS =
        "\1\172\1\uffff\4\172\1\uffff\3\172\1\71\3\172\1\71\3\172\1\71\1"+
        "\172\1\71\2\172\1\71\4\172";
    static final String DFA84_acceptS =
        "\1\uffff\1\2\4\uffff\1\1\25\uffff";
    static final String DFA84_specialS =
        "\34\uffff}>";
    static final String[] DFA84_transitionS = {
            "\1\1\11\uffff\1\1\3\uffff\1\3\1\uffff\12\2\7\uffff\32\1\6\uffff"+
            "\32\1",
            "",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\5\1\uffff\12\2\7\uffff"+
            "\4\1\1\4\25\1\6\uffff\4\1\1\4\25\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\7\7\uffff"+
            "\4\1\1\10\25\1\6\uffff\4\1\1\10\25\1",
            "\1\1\11\uffff\1\1\1\12\1\uffff\1\12\1\1\1\uffff\12\11\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\13\7\uffff"+
            "\4\1\1\14\25\1\6\uffff\4\1\1\14\25\1",
            "",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\7\7\uffff"+
            "\4\1\1\15\25\1\6\uffff\4\1\1\15\25\1",
            "\1\1\11\uffff\1\1\1\16\1\uffff\1\16\1\1\1\uffff\12\17\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\11\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\12\20",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\13\7\uffff"+
            "\4\1\1\21\25\1\6\uffff\4\1\1\21\25\1",
            "\1\1\11\uffff\1\1\1\22\1\uffff\1\22\1\1\1\uffff\12\23\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\24\1\uffff\1\24\1\1\1\uffff\12\25\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\12\26",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\17\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\11\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\27\1\uffff\1\27\1\1\1\uffff\12\30\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\12\31",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\23\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\12\32",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\25\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\17\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\12\33",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\30\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\23\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\25\7\uffff"+
            "\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\30\7\uffff"+
            "\32\1\6\uffff\32\1"
    };

    static final short[] DFA84_eot = DFA.unpackEncodedString(DFA84_eotS);
    static final short[] DFA84_eof = DFA.unpackEncodedString(DFA84_eofS);
    static final char[] DFA84_min = DFA.unpackEncodedStringToUnsignedChars(DFA84_minS);
    static final char[] DFA84_max = DFA.unpackEncodedStringToUnsignedChars(DFA84_maxS);
    static final short[] DFA84_accept = DFA.unpackEncodedString(DFA84_acceptS);
    static final short[] DFA84_special = DFA.unpackEncodedString(DFA84_specialS);
    static final short[][] DFA84_transition;

    static {
        int numStates = DFA84_transitionS.length;
        DFA84_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA84_transition[i] = DFA.unpackEncodedString(DFA84_transitionS[i]);
        }
    }

    class DFA84 extends DFA {

        public DFA84(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 84;
            this.eot = DFA84_eot;
            this.eof = DFA84_eof;
            this.min = DFA84_min;
            this.max = DFA84_max;
            this.accept = DFA84_accept;
            this.special = DFA84_special;
            this.transition = DFA84_transition;
        }
        public String getDescription() {
            return "()* loopback of 595:14: ( NUMBER SEP )*";
        }
    }
    static final String DFA126_eotS =
        "\23\uffff";
    static final String DFA126_eofS =
        "\23\uffff";
    static final String DFA126_minS =
        "\1\56\2\54\1\40\1\54\1\53\1\54\1\53\1\40\1\54\1\53\1\60\1\54\1\60"+
        "\1\54\2\uffff\1\60\1\54";
    static final String DFA126_maxS =
        "\1\71\2\145\1\172\1\145\1\71\1\145\1\71\1\172\1\145\5\71\2\uffff"+
        "\2\71";
    static final String DFA126_acceptS =
        "\17\uffff\1\2\1\1\2\uffff";
    static final String DFA126_specialS =
        "\23\uffff}>";
    static final String[] DFA126_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\1\4\1\uffff\12\1\13\uffff\1\5\37\uffff\1\5",
            "\1\3\3\uffff\12\6\13\uffff\1\7\37\uffff\1\7",
            "\1\10\40\uffff\32\10\6\uffff\32\10",
            "\1\3\3\uffff\12\11\13\uffff\1\12\37\uffff\1\12",
            "\1\13\1\uffff\1\13\2\uffff\12\14",
            "\1\3\3\uffff\12\6\13\uffff\1\7\37\uffff\1\7",
            "\1\15\1\uffff\1\15\2\uffff\12\16",
            "\1\10\11\uffff\1\17\1\uffff\1\20\24\uffff\32\10\6\uffff\32"+
            "\10",
            "\1\3\3\uffff\12\11\13\uffff\1\12\37\uffff\1\12",
            "\1\21\1\uffff\1\21\2\uffff\12\22",
            "\12\14",
            "\1\3\3\uffff\12\14",
            "\12\16",
            "\1\3\3\uffff\12\16",
            "",
            "",
            "\12\22",
            "\1\3\3\uffff\12\22"
    };

    static final short[] DFA126_eot = DFA.unpackEncodedString(DFA126_eotS);
    static final short[] DFA126_eof = DFA.unpackEncodedString(DFA126_eofS);
    static final char[] DFA126_min = DFA.unpackEncodedStringToUnsignedChars(DFA126_minS);
    static final char[] DFA126_max = DFA.unpackEncodedStringToUnsignedChars(DFA126_maxS);
    static final short[] DFA126_accept = DFA.unpackEncodedString(DFA126_acceptS);
    static final short[] DFA126_special = DFA.unpackEncodedString(DFA126_specialS);
    static final short[][] DFA126_transition;

    static {
        int numStates = DFA126_transitionS.length;
        DFA126_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA126_transition[i] = DFA.unpackEncodedString(DFA126_transitionS[i]);
        }
    }

    class DFA126 extends DFA {

        public DFA126(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 126;
            this.eot = DFA126_eot;
            this.eof = DFA126_eof;
            this.min = DFA126_min;
            this.max = DFA126_max;
            this.accept = DFA126_accept;
            this.special = DFA126_special;
            this.transition = DFA126_transition;
        }
        public String getDescription() {
            return "()* loopback of 812:5: (dev= NUMBER SEP we= LETTERS SEP )*";
        }
    }
    static final String DFA147_eotS =
        "\4\uffff";
    static final String DFA147_eofS =
        "\4\uffff";
    static final String DFA147_minS =
        "\2\0\2\uffff";
    static final String DFA147_maxS =
        "\2\172\2\uffff";
    static final String DFA147_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA147_specialS =
        "\4\uffff}>";
    static final String[] DFA147_transitionS = {
            "\1\2\37\uffff\1\1\11\uffff\1\2\26\uffff\32\1\6\uffff\32\1",
            "\1\2\37\uffff\1\1\11\uffff\1\2\1\uffff\1\3\24\uffff\32\1\6"+
            "\uffff\32\1",
            "",
            ""
    };

    static final short[] DFA147_eot = DFA.unpackEncodedString(DFA147_eotS);
    static final short[] DFA147_eof = DFA.unpackEncodedString(DFA147_eofS);
    static final char[] DFA147_min = DFA.unpackEncodedStringToUnsignedChars(DFA147_minS);
    static final char[] DFA147_max = DFA.unpackEncodedStringToUnsignedChars(DFA147_maxS);
    static final short[] DFA147_accept = DFA.unpackEncodedString(DFA147_acceptS);
    static final short[] DFA147_special = DFA.unpackEncodedString(DFA147_specialS);
    static final short[][] DFA147_transition;

    static {
        int numStates = DFA147_transitionS.length;
        DFA147_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA147_transition[i] = DFA.unpackEncodedString(DFA147_transitionS[i]);
        }
    }

    class DFA147 extends DFA {

        public DFA147(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 147;
            this.eot = DFA147_eot;
            this.eof = DFA147_eof;
            this.min = DFA147_min;
            this.max = DFA147_max;
            this.accept = DFA147_accept;
            this.special = DFA147_special;
            this.transition = DFA147_transition;
        }
        public String getDescription() {
            return "()* loopback of 957:11: ( LETTERS SEP )*";
        }
    }
    static final String DFA161_eotS =
        "\5\uffff\4\11\2\uffff";
    static final String DFA161_eofS =
        "\13\uffff";
    static final String DFA161_minS =
        "\2\40\1\uffff\6\40\1\uffff\1\40";
    static final String DFA161_maxS =
        "\2\177\1\uffff\6\177\1\uffff\1\177";
    static final String DFA161_acceptS =
        "\2\uffff\1\1\6\uffff\1\2\1\uffff";
    static final String DFA161_specialS =
        "\13\uffff}>";
    static final String[] DFA161_transitionS = {
            "\12\2\1\1\125\2",
            "\20\2\12\3\7\2\6\4\71\2",
            "",
            "\20\2\12\5\7\2\6\6\71\2",
            "\20\2\12\7\7\2\6\10\71\2",
            "\20\2\12\12\7\2\6\6\71\2",
            "\140\2",
            "\140\2",
            "\140\2",
            "",
            "\20\2\12\12\7\2\6\6\71\2"
    };

    static final short[] DFA161_eot = DFA.unpackEncodedString(DFA161_eotS);
    static final short[] DFA161_eof = DFA.unpackEncodedString(DFA161_eofS);
    static final char[] DFA161_min = DFA.unpackEncodedStringToUnsignedChars(DFA161_minS);
    static final char[] DFA161_max = DFA.unpackEncodedStringToUnsignedChars(DFA161_maxS);
    static final short[] DFA161_accept = DFA.unpackEncodedString(DFA161_acceptS);
    static final short[] DFA161_special = DFA.unpackEncodedString(DFA161_specialS);
    static final short[][] DFA161_transition;

    static {
        int numStates = DFA161_transitionS.length;
        DFA161_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA161_transition[i] = DFA.unpackEncodedString(DFA161_transitionS[i]);
        }
    }

    class DFA161 extends DFA {

        public DFA161(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 161;
            this.eot = DFA161_eot;
            this.eof = DFA161_eof;
            this.min = DFA161_min;
            this.max = DFA161_max;
            this.accept = DFA161_accept;
            this.special = DFA161_special;
            this.transition = DFA161_transition;
        }
        public String getDescription() {
            return "()* loopback of 1035:3: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
        }
    }
    static final String DFA208_eotS =
        "\4\uffff";
    static final String DFA208_eofS =
        "\4\uffff";
    static final String DFA208_minS =
        "\2\40\2\uffff";
    static final String DFA208_maxS =
        "\2\172\2\uffff";
    static final String DFA208_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA208_specialS =
        "\4\uffff}>";
    static final String[] DFA208_transitionS = {
            "\1\1\11\uffff\1\2\26\uffff\32\1\6\uffff\32\1",
            "\1\1\11\uffff\1\2\1\uffff\1\3\24\uffff\32\1\6\uffff\32\1",
            "",
            ""
    };

    static final short[] DFA208_eot = DFA.unpackEncodedString(DFA208_eotS);
    static final short[] DFA208_eof = DFA.unpackEncodedString(DFA208_eofS);
    static final char[] DFA208_min = DFA.unpackEncodedStringToUnsignedChars(DFA208_minS);
    static final char[] DFA208_max = DFA.unpackEncodedStringToUnsignedChars(DFA208_maxS);
    static final short[] DFA208_accept = DFA.unpackEncodedString(DFA208_acceptS);
    static final short[] DFA208_special = DFA.unpackEncodedString(DFA208_specialS);
    static final short[][] DFA208_transition;

    static {
        int numStates = DFA208_transitionS.length;
        DFA208_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA208_transition[i] = DFA.unpackEncodedString(DFA208_transitionS[i]);
        }
    }

    class DFA208 extends DFA {

        public DFA208(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 208;
            this.eot = DFA208_eot;
            this.eof = DFA208_eof;
            this.min = DFA208_min;
            this.max = DFA208_max;
            this.accept = DFA208_accept;
            this.special = DFA208_special;
            this.transition = DFA208_transition;
        }
        public String getDescription() {
            return "()* loopback of 1167:13: ( LETTERS SEP )*";
        }
    }
    static final String DFA215_eotS =
        "\23\uffff\4\36\16\uffff";
    static final String DFA215_eofS =
        "\45\uffff";
    static final String DFA215_minS =
        "\2\41\1\uffff\33\41\1\uffff\6\41";
    static final String DFA215_maxS =
        "\2\177\1\uffff\33\177\1\uffff\6\177";
    static final String DFA215_acceptS =
        "\2\uffff\1\1\33\uffff\1\2\6\uffff";
    static final String DFA215_specialS =
        "\45\uffff}>";
    static final String[] DFA215_transitionS = {
            "\13\2\1\1\123\2",
            "\11\2\1\5\3\2\1\4\1\2\12\3\106\2",
            "",
            "\11\2\1\5\3\2\1\6\1\2\12\3\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\4\1\2\12\10\13\2\1\11\37\2\1\11\32\2",
            "\17\2\12\12\7\2\6\13\71\2",
            "\11\2\1\5\3\2\1\4\1\2\12\14\13\2\1\15\37\2\1\15\32\2",
            "\12\2\1\16\1\2\1\16\2\2\12\17\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\10\13\2\1\20\37\2\1\20\32\2",
            "\12\2\1\21\1\2\1\21\2\2\12\22\106\2",
            "\17\2\12\23\7\2\6\24\71\2",
            "\17\2\12\25\7\2\6\26\71\2",
            "\11\2\1\5\3\2\1\6\1\2\12\14\13\2\1\27\37\2\1\27\32\2",
            "\12\2\1\30\1\2\1\30\2\2\12\31\106\2",
            "\17\2\12\17\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\32\106\2",
            "\12\2\1\33\1\2\1\33\2\2\12\34\106\2",
            "\17\2\12\22\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\35\106\2",
            "\17\2\12\37\7\2\6\24\71\2",
            "\137\2",
            "\137\2",
            "\137\2",
            "\12\2\1\40\1\2\1\40\2\2\12\41\106\2",
            "\17\2\12\31\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\42\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\32\13\2\1\7\37\2\1\7\32\2",
            "\17\2\12\34\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\43\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\35\13\2\1\7\37\2\1\7\32\2",
            "",
            "\17\2\12\37\7\2\6\24\71\2",
            "\17\2\12\41\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\44\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\42\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\43\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\44\13\2\1\7\37\2\1\7\32\2"
    };

    static final short[] DFA215_eot = DFA.unpackEncodedString(DFA215_eotS);
    static final short[] DFA215_eof = DFA.unpackEncodedString(DFA215_eofS);
    static final char[] DFA215_min = DFA.unpackEncodedStringToUnsignedChars(DFA215_minS);
    static final char[] DFA215_max = DFA.unpackEncodedStringToUnsignedChars(DFA215_maxS);
    static final short[] DFA215_accept = DFA.unpackEncodedString(DFA215_acceptS);
    static final short[] DFA215_special = DFA.unpackEncodedString(DFA215_specialS);
    static final short[][] DFA215_transition;

    static {
        int numStates = DFA215_transitionS.length;
        DFA215_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA215_transition[i] = DFA.unpackEncodedString(DFA215_transitionS[i]);
        }
    }

    class DFA215 extends DFA {

        public DFA215(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 215;
            this.eot = DFA215_eot;
            this.eof = DFA215_eof;
            this.min = DFA215_min;
            this.max = DFA215_max;
            this.accept = DFA215_accept;
            this.special = DFA215_special;
            this.transition = DFA215_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1211:2: ( '\\u0021' .. '\\u007F' )+";
        }
    }
    static final String DFA220_eotS =
        "\23\uffff\4\36\16\uffff";
    static final String DFA220_eofS =
        "\45\uffff";
    static final String DFA220_minS =
        "\2\41\1\uffff\33\41\1\uffff\6\41";
    static final String DFA220_maxS =
        "\2\177\1\uffff\33\177\1\uffff\6\177";
    static final String DFA220_acceptS =
        "\2\uffff\1\1\33\uffff\1\2\6\uffff";
    static final String DFA220_specialS =
        "\45\uffff}>";
    static final String[] DFA220_transitionS = {
            "\13\2\1\1\123\2",
            "\11\2\1\5\3\2\1\4\1\2\12\3\106\2",
            "",
            "\11\2\1\5\3\2\1\6\1\2\12\3\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\4\1\2\12\10\13\2\1\11\37\2\1\11\32\2",
            "\17\2\12\12\7\2\6\13\71\2",
            "\11\2\1\5\3\2\1\4\1\2\12\14\13\2\1\15\37\2\1\15\32\2",
            "\12\2\1\16\1\2\1\16\2\2\12\17\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\10\13\2\1\20\37\2\1\20\32\2",
            "\12\2\1\21\1\2\1\21\2\2\12\22\106\2",
            "\17\2\12\23\7\2\6\24\71\2",
            "\17\2\12\25\7\2\6\26\71\2",
            "\11\2\1\5\3\2\1\6\1\2\12\14\13\2\1\27\37\2\1\27\32\2",
            "\12\2\1\30\1\2\1\30\2\2\12\31\106\2",
            "\17\2\12\17\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\32\106\2",
            "\12\2\1\33\1\2\1\33\2\2\12\34\106\2",
            "\17\2\12\22\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\35\106\2",
            "\17\2\12\37\7\2\6\24\71\2",
            "\137\2",
            "\137\2",
            "\137\2",
            "\12\2\1\40\1\2\1\40\2\2\12\41\106\2",
            "\17\2\12\31\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\42\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\32\13\2\1\7\37\2\1\7\32\2",
            "\17\2\12\34\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\43\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\35\13\2\1\7\37\2\1\7\32\2",
            "",
            "\17\2\12\37\7\2\6\24\71\2",
            "\17\2\12\41\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\44\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\42\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\43\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\44\13\2\1\7\37\2\1\7\32\2"
    };

    static final short[] DFA220_eot = DFA.unpackEncodedString(DFA220_eotS);
    static final short[] DFA220_eof = DFA.unpackEncodedString(DFA220_eofS);
    static final char[] DFA220_min = DFA.unpackEncodedStringToUnsignedChars(DFA220_minS);
    static final char[] DFA220_max = DFA.unpackEncodedStringToUnsignedChars(DFA220_maxS);
    static final short[] DFA220_accept = DFA.unpackEncodedString(DFA220_acceptS);
    static final short[] DFA220_special = DFA.unpackEncodedString(DFA220_specialS);
    static final short[][] DFA220_transition;

    static {
        int numStates = DFA220_transitionS.length;
        DFA220_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA220_transition[i] = DFA.unpackEncodedString(DFA220_transitionS[i]);
        }
    }

    class DFA220 extends DFA {

        public DFA220(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 220;
            this.eot = DFA220_eot;
            this.eof = DFA220_eof;
            this.min = DFA220_min;
            this.max = DFA220_max;
            this.accept = DFA220_accept;
            this.special = DFA220_special;
            this.transition = DFA220_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1276:2: ( '\\u0021' .. '\\u007F' )+";
        }
    }
    static final String DFA226_eotS =
        "\23\uffff\4\36\16\uffff";
    static final String DFA226_eofS =
        "\45\uffff";
    static final String DFA226_minS =
        "\2\41\1\uffff\33\41\1\uffff\6\41";
    static final String DFA226_maxS =
        "\2\177\1\uffff\33\177\1\uffff\6\177";
    static final String DFA226_acceptS =
        "\2\uffff\1\1\33\uffff\1\2\6\uffff";
    static final String DFA226_specialS =
        "\45\uffff}>";
    static final String[] DFA226_transitionS = {
            "\13\2\1\1\123\2",
            "\11\2\1\5\3\2\1\4\1\2\12\3\106\2",
            "",
            "\11\2\1\5\3\2\1\6\1\2\12\3\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\4\1\2\12\10\13\2\1\11\37\2\1\11\32\2",
            "\17\2\12\12\7\2\6\13\71\2",
            "\11\2\1\5\3\2\1\4\1\2\12\14\13\2\1\15\37\2\1\15\32\2",
            "\12\2\1\16\1\2\1\16\2\2\12\17\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\10\13\2\1\20\37\2\1\20\32\2",
            "\12\2\1\21\1\2\1\21\2\2\12\22\106\2",
            "\17\2\12\23\7\2\6\24\71\2",
            "\17\2\12\25\7\2\6\26\71\2",
            "\11\2\1\5\3\2\1\6\1\2\12\14\13\2\1\27\37\2\1\27\32\2",
            "\12\2\1\30\1\2\1\30\2\2\12\31\106\2",
            "\17\2\12\17\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\32\106\2",
            "\12\2\1\33\1\2\1\33\2\2\12\34\106\2",
            "\17\2\12\22\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\35\106\2",
            "\17\2\12\37\7\2\6\24\71\2",
            "\137\2",
            "\137\2",
            "\137\2",
            "\12\2\1\40\1\2\1\40\2\2\12\41\106\2",
            "\17\2\12\31\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\42\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\32\13\2\1\7\37\2\1\7\32\2",
            "\17\2\12\34\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\43\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\35\13\2\1\7\37\2\1\7\32\2",
            "",
            "\17\2\12\37\7\2\6\24\71\2",
            "\17\2\12\41\106\2",
            "\11\2\1\5\3\2\1\4\1\2\12\44\106\2",
            "\11\2\1\5\3\2\1\6\1\2\12\42\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\43\13\2\1\7\37\2\1\7\32\2",
            "\11\2\1\5\3\2\1\6\1\2\12\44\13\2\1\7\37\2\1\7\32\2"
    };

    static final short[] DFA226_eot = DFA.unpackEncodedString(DFA226_eotS);
    static final short[] DFA226_eof = DFA.unpackEncodedString(DFA226_eofS);
    static final char[] DFA226_min = DFA.unpackEncodedStringToUnsignedChars(DFA226_minS);
    static final char[] DFA226_max = DFA.unpackEncodedStringToUnsignedChars(DFA226_maxS);
    static final short[] DFA226_accept = DFA.unpackEncodedString(DFA226_acceptS);
    static final short[] DFA226_special = DFA.unpackEncodedString(DFA226_specialS);
    static final short[][] DFA226_transition;

    static {
        int numStates = DFA226_transitionS.length;
        DFA226_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA226_transition[i] = DFA.unpackEncodedString(DFA226_transitionS[i]);
        }
    }

    class DFA226 extends DFA {

        public DFA226(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 226;
            this.eot = DFA226_eot;
            this.eof = DFA226_eof;
            this.min = DFA226_min;
            this.max = DFA226_max;
            this.accept = DFA226_accept;
            this.special = DFA226_special;
            this.transition = DFA226_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1285:4: ( '\\u0021' .. '\\u007F' )+";
        }
    }
    static final String DFA236_eotS =
        "\5\uffff\4\11\2\uffff";
    static final String DFA236_eofS =
        "\13\uffff";
    static final String DFA236_minS =
        "\2\40\1\uffff\6\40\1\uffff\1\40";
    static final String DFA236_maxS =
        "\2\177\1\uffff\6\177\1\uffff\1\177";
    static final String DFA236_acceptS =
        "\2\uffff\1\1\6\uffff\1\2\1\uffff";
    static final String DFA236_specialS =
        "\13\uffff}>";
    static final String[] DFA236_transitionS = {
            "\12\2\1\1\125\2",
            "\20\2\12\3\7\2\6\4\71\2",
            "",
            "\20\2\12\5\7\2\6\6\71\2",
            "\20\2\12\7\7\2\6\10\71\2",
            "\20\2\12\12\7\2\6\6\71\2",
            "\140\2",
            "\140\2",
            "\140\2",
            "",
            "\20\2\12\12\7\2\6\6\71\2"
    };

    static final short[] DFA236_eot = DFA.unpackEncodedString(DFA236_eotS);
    static final short[] DFA236_eof = DFA.unpackEncodedString(DFA236_eofS);
    static final char[] DFA236_min = DFA.unpackEncodedStringToUnsignedChars(DFA236_minS);
    static final char[] DFA236_max = DFA.unpackEncodedStringToUnsignedChars(DFA236_maxS);
    static final short[] DFA236_accept = DFA.unpackEncodedString(DFA236_acceptS);
    static final short[] DFA236_special = DFA.unpackEncodedString(DFA236_specialS);
    static final short[][] DFA236_transition;

    static {
        int numStates = DFA236_transitionS.length;
        DFA236_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA236_transition[i] = DFA.unpackEncodedString(DFA236_transitionS[i]);
        }
    }

    class DFA236 extends DFA {

        public DFA236(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 236;
            this.eot = DFA236_eot;
            this.eof = DFA236_eof;
            this.min = DFA236_min;
            this.max = DFA236_max;
            this.accept = DFA236_accept;
            this.special = DFA236_special;
            this.transition = DFA236_transition;
        }
        public String getDescription() {
            return "()* loopback of 1547:2: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
        }
    }
    static final String DFA238_eotS =
        "\5\uffff\4\11\2\uffff";
    static final String DFA238_eofS =
        "\13\uffff";
    static final String DFA238_minS =
        "\2\40\1\uffff\6\40\1\uffff\1\40";
    static final String DFA238_maxS =
        "\2\177\1\uffff\6\177\1\uffff\1\177";
    static final String DFA238_acceptS =
        "\2\uffff\1\1\6\uffff\1\2\1\uffff";
    static final String DFA238_specialS =
        "\13\uffff}>";
    static final String[] DFA238_transitionS = {
            "\12\2\1\1\125\2",
            "\20\2\12\3\7\2\6\4\71\2",
            "",
            "\20\2\12\5\7\2\6\6\71\2",
            "\20\2\12\7\7\2\6\10\71\2",
            "\20\2\12\12\7\2\6\6\71\2",
            "\140\2",
            "\140\2",
            "\140\2",
            "",
            "\20\2\12\12\7\2\6\6\71\2"
    };

    static final short[] DFA238_eot = DFA.unpackEncodedString(DFA238_eotS);
    static final short[] DFA238_eof = DFA.unpackEncodedString(DFA238_eofS);
    static final char[] DFA238_min = DFA.unpackEncodedStringToUnsignedChars(DFA238_minS);
    static final char[] DFA238_max = DFA.unpackEncodedStringToUnsignedChars(DFA238_maxS);
    static final short[] DFA238_accept = DFA.unpackEncodedString(DFA238_acceptS);
    static final short[] DFA238_special = DFA.unpackEncodedString(DFA238_specialS);
    static final short[][] DFA238_transition;

    static {
        int numStates = DFA238_transitionS.length;
        DFA238_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA238_transition[i] = DFA.unpackEncodedString(DFA238_transitionS[i]);
        }
    }

    class DFA238 extends DFA {

        public DFA238(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 238;
            this.eot = DFA238_eot;
            this.eof = DFA238_eof;
            this.min = DFA238_min;
            this.max = DFA238_max;
            this.accept = DFA238_accept;
            this.special = DFA238_special;
            this.transition = DFA238_transition;
        }
        public String getDescription() {
            return "()* loopback of 1559:29: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
        }
    }
    static final String DFA248_eotS =
        "\1\uffff\1\3\4\uffff";
    static final String DFA248_eofS =
        "\6\uffff";
    static final String DFA248_minS =
        "\2\56\4\uffff";
    static final String DFA248_maxS =
        "\1\71\1\145\4\uffff";
    static final String DFA248_acceptS =
        "\2\uffff\1\3\1\1\1\2\1\4";
    static final String DFA248_specialS =
        "\6\uffff}>";
    static final String[] DFA248_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1\13\uffff\1\5\37\uffff\1\5",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA248_eot = DFA.unpackEncodedString(DFA248_eotS);
    static final short[] DFA248_eof = DFA.unpackEncodedString(DFA248_eofS);
    static final char[] DFA248_min = DFA.unpackEncodedStringToUnsignedChars(DFA248_minS);
    static final char[] DFA248_max = DFA.unpackEncodedStringToUnsignedChars(DFA248_maxS);
    static final short[] DFA248_accept = DFA.unpackEncodedString(DFA248_acceptS);
    static final short[] DFA248_special = DFA.unpackEncodedString(DFA248_specialS);
    static final short[][] DFA248_transition;

    static {
        int numStates = DFA248_transitionS.length;
        DFA248_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA248_transition[i] = DFA.unpackEncodedString(DFA248_transitionS[i]);
        }
    }

    class DFA248 extends DFA {

        public DFA248(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 248;
            this.eot = DFA248_eot;
            this.eof = DFA248_eof;
            this.min = DFA248_min;
            this.max = DFA248_max;
            this.accept = DFA248_accept;
            this.special = DFA248_special;
            this.transition = DFA248_transition;
        }
        public String getDescription() {
            return "1573:1: NUMBER : ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )* ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA257_eotS =
        "\4\uffff\16\32\1\uffff\2\127\1\31\1\uffff\1\134\23\uffff\43\u0082"+
        "\12\uffff\1\127\1\uffff\1\127\66\uffff\1\127\2\uffff\1\127\1\uffff"+
        "\1\127\35\uffff\1\127\u0081\uffff";
    static final String DFA257_eofS =
        "\u0136\uffff";
    static final String DFA257_minS =
        "\1\11\1\101\1\uffff\1\42\1\120\1\111\1\107\1\103\1\106\2\103\1\101"+
        "\1\104\1\111\1\104\1\111\1\130\1\101\1\40\1\56\1\60\1\40\1\uffff"+
        "\1\56\4\uffff\1\120\1\111\1\107\1\103\1\106\2\103\1\101\1\104\1"+
        "\111\1\104\1\111\1\130\1\101\1\143\46\40\1\uffff\2\40\1\uffff\1"+
        "\40\2\uffff\1\60\1\53\1\60\1\53\2\uffff\43\101\1\154\2\uffff\10"+
        "\40\1\uffff\4\40\1\60\1\53\4\60\1\101\1\105\1\102\1\107\1\104\1"+
        "\123\1\115\1\102\3\uffff\1\141\13\40\1\uffff\4\40\2\60\5\uffff\1"+
        "\103\1\113\3\uffff\1\101\1\107\1\uffff\1\101\1\104\1\102\7\uffff"+
        "\1\122\1\163\25\40\23\uffff\1\163\22\40\1\42\14\40\1\72\7\40\1\42"+
        "\6\40\1\101\4\40\1\uffff\1\105\2\uffff\1\40\1\126\1\111\1\103\1"+
        "\105\1\42\2\uffff";
    static final String DFA257_maxS =
        "\1\173\1\132\1\uffff\1\42\1\120\1\116\1\120\1\130\1\115\1\122\1"+
        "\116\1\101\1\123\1\122\1\127\1\111\1\130\1\126\1\172\2\145\1\172"+
        "\1\uffff\1\71\4\uffff\1\120\1\116\1\120\1\130\1\115\1\122\1\116"+
        "\1\101\1\123\1\122\1\127\1\111\1\130\1\126\1\143\46\172\1\uffff"+
        "\2\172\1\uffff\1\172\2\uffff\1\145\1\71\1\145\1\71\2\uffff\43\132"+
        "\1\154\2\uffff\10\172\1\uffff\4\172\1\145\5\71\1\120\1\127\1\120"+
        "\1\123\1\104\1\127\1\124\1\127\3\uffff\1\141\13\172\1\uffff\4\172"+
        "\2\71\5\uffff\1\127\1\124\3\uffff\1\126\1\124\1\uffff\1\127\1\126"+
        "\1\103\7\uffff\1\124\1\163\25\172\23\uffff\1\163\22\172\1\42\14"+
        "\172\1\72\7\172\1\42\6\172\1\127\4\172\1\uffff\1\105\2\uffff\1\172"+
        "\1\126\1\111\1\103\1\105\1\123\2\uffff";
    static final String DFA257_acceptS =
        "\2\uffff\1\46\23\uffff\1\62\1\uffff\1\66\1\61\1\70\1\55\65\uffff"+
        "\1\57\2\uffff\1\57\1\uffff\1\67\1\60\4\uffff\1\63\1\64\44\uffff"+
        "\1\56\1\57\10\uffff\1\65\22\uffff\1\43\1\44\1\54\14\uffff\1\65\6"+
        "\uffff\1\1\1\2\1\45\1\3\1\4\2\uffff\1\13\1\14\1\15\2\uffff\1\23"+
        "\3\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\27\uffff\1\5\1\6\1\7"+
        "\1\10\1\11\1\12\1\16\1\17\1\20\1\21\1\22\1\24\1\25\1\26\1\27\1\30"+
        "\1\31\1\41\1\42\64\uffff\1\47\1\uffff\1\52\1\53\6\uffff\1\50\1\51";
    static final String DFA257_specialS =
        "\u0136\uffff}>";
    static final String[] DFA257_transitionS = {
            "\2\31\2\uffff\1\31\22\uffff\1\25\1\2\1\22\1\uffff\1\1\5\uffff"+
            "\1\30\1\27\1\26\1\27\1\24\1\uffff\12\23\7\uffff\1\6\1\32\1\7"+
            "\1\10\1\11\1\32\1\4\1\12\1\5\10\32\1\13\1\14\1\15\1\32\1\16"+
            "\1\17\1\32\1\20\1\21\6\uffff\32\32\1\3",
            "\1\36\1\uffff\1\37\1\40\1\41\1\uffff\1\34\1\42\1\35\6\uffff"+
            "\1\33\1\uffff\1\43\1\44\1\45\1\uffff\1\46\1\47\1\uffff\1\50"+
            "\1\51",
            "",
            "\1\52",
            "\1\53",
            "\1\54\4\uffff\1\55",
            "\1\56\1\uffff\1\57\6\uffff\1\60",
            "\1\61\1\62\16\uffff\1\63\1\64\1\uffff\1\65\1\uffff\1\66",
            "\1\67\6\uffff\1\70",
            "\1\71\14\uffff\1\72\1\uffff\1\73",
            "\1\74\1\uffff\1\75\10\uffff\1\76",
            "\1\77",
            "\1\100\10\uffff\1\101\1\102\4\uffff\1\103",
            "\1\104\10\uffff\1\105",
            "\1\106\22\uffff\1\107",
            "\1\110",
            "\1\111",
            "\1\112\1\uffff\1\113\15\uffff\1\114\4\uffff\1\115",
            "\1\116\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\122\1\120\6\uffff\32\116\6"+
            "\uffff\32\116",
            "\1\130\1\uffff\12\23\13\uffff\1\131\37\uffff\1\131",
            "\12\132\13\uffff\1\133\37\uffff\1\133",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "",
            "\1\135\1\uffff\12\135",
            "",
            "",
            "",
            "",
            "\1\136",
            "\1\137\4\uffff\1\140",
            "\1\141\1\uffff\1\142\6\uffff\1\143",
            "\1\144\1\145\16\uffff\1\146\1\147\1\uffff\1\150\1\uffff\1\151",
            "\1\152\6\uffff\1\153",
            "\1\154\14\uffff\1\155\1\uffff\1\156",
            "\1\157\1\uffff\1\160\10\uffff\1\161",
            "\1\162",
            "\1\163\10\uffff\1\164\1\165\4\uffff\1\166",
            "\1\167\10\uffff\1\170",
            "\1\171\22\uffff\1\172",
            "\1\173",
            "\1\174",
            "\1\175\1\uffff\1\176\15\uffff\1\177\4\uffff\1\u0080",
            "\1\u0081",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\32\40\uffff\32\32\6\uffff\32\32",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\122\1\120\6\uffff\32\116\6"+
            "\uffff\32\116",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u0086\1\u0085\6\uffff\32\u0084\6\uffff"+
            "\32\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\122\1\120\6\uffff\32\116\6"+
            "\uffff\32\116",
            "",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\122\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\u008a\1\120\6\uffff\4\116"+
            "\1\u008b\25\116\6\uffff\4\116\1\u008b\25\116",
            "",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u008e\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "",
            "",
            "\12\u0091\13\uffff\1\u0092\37\uffff\1\u0092",
            "\1\u0093\1\uffff\1\u0093\2\uffff\12\u0094",
            "\12\132\13\uffff\1\133\37\uffff\1\133",
            "\1\u0095\1\uffff\1\u0095\2\uffff\12\u0096",
            "",
            "",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u0097\1\u0098\1\uffff\1\u0099\2\uffff\1\u009a\1\u009b\4"+
            "\uffff\1\u009c\4\uffff\1\u009d\1\uffff\1\u00a1\1\uffff\1\u009e"+
            "\1\uffff\1\u009f\1\uffff\1\u00a0",
            "\1\u00a2",
            "",
            "",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u0086\1\u0085\6\uffff\32\u0084\6\uffff"+
            "\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u0086\1\u0085\6\uffff\32\u0084\6\uffff"+
            "\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u0086\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u00a5\1\u0085\6\uffff\4\u0084\1\u00a6"+
            "\25\u0084\6\uffff\4\u0084\1\u00a6\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00a8\1\126\1\u00a8\1\123\1\117\12\u00a7\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\u00a9\1\120\6\uffff\4\116"+
            "\1\u00aa\25\116\6\uffff\4\116\1\u00aa\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u008a\1\120\6\uffff\4\116"+
            "\1\u00ab\25\116\6\uffff\4\116\1\u00ab\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00ac\1\126\1\u00ac\1\123\1\117\12\u00ad\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u008e\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u008e\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00b1\1\u0090\6\uffff\4"+
            "\u008d\1\u00b2\25\u008d\6\uffff\4\u008d\1\u00b2\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u008e\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\12\u0091\13\uffff\1\u0092\37\uffff\1\u0092",
            "\1\u00b3\1\uffff\1\u00b3\2\uffff\12\u00b4",
            "\12\u0094",
            "\12\u0094",
            "\12\u0096",
            "\12\u0096",
            "\1\u00b5\12\uffff\1\u00b7\3\uffff\1\u00b6",
            "\1\u00b8\11\uffff\1\u00b9\7\uffff\1\u00ba",
            "\1\u00bb\15\uffff\1\u00bc",
            "\1\u00bd\4\uffff\1\u00be\6\uffff\1\u00bf",
            "\1\u00c0",
            "\1\u00c1\1\u00c2\2\uffff\1\u00c3",
            "\1\u00c4\5\uffff\1\u00c5\1\u00c6",
            "\1\u00c7\5\uffff\1\u00c9\3\uffff\1\u00c8\3\uffff\1\u00ca\3"+
            "\uffff\1\u00cb\2\uffff\1\u00cc",
            "",
            "",
            "",
            "\1\u00cd",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00cf\1\126\1\u00cf\1\u0087\1\117\12\u00ce\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u00d0\1\u0085\6\uffff\4\u0084\1\u00d1"+
            "\25\u0084\6\uffff\4\u0084\1\u00d1\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00a5\1\u0085\6\uffff\4\u0084\1\u00d2"+
            "\25\u0084\6\uffff\4\u0084\1\u00d2\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00d3\1\126\1\u00d3\1\u0087\1\117\12\u00d4\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u00a7\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00d5\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u00a9\1\120\6\uffff\4\116"+
            "\1\u00d6\25\116\6\uffff\4\116\1\u00d6\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00d7\1\126\1\u00d7\1\123\1\117\12\u00d8\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00d9\1\126\1\u00d9\1\123\1\117\12\u00da\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00db\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u00ad\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00dd\1\126\1\u00dd\1\u008f\1\126\12\u00dc\1\u0090\6\uffff"+
            "\32\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00de\1\u0090\6\uffff\4"+
            "\u008d\1\u00df\25\u008d\6\uffff\4\u008d\1\u00df\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u00b1\1\u0090\6\uffff\4"+
            "\u008d\1\u00e0\25\u008d\6\uffff\4\u008d\1\u00e0\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00e1\1\126\1\u00e1\1\u008f\1\126\12\u00e2\1\u0090\6\uffff"+
            "\32\u008d\6\uffff\32\u008d",
            "\12\u00b4",
            "\12\u00b4",
            "",
            "",
            "",
            "",
            "",
            "\1\u00e3\16\uffff\1\u00e4\4\uffff\1\u00e5",
            "\1\u00e7\7\uffff\1\u00e8\1\u00e6",
            "",
            "",
            "",
            "\1\u00e9\24\uffff\1\u00ea",
            "\1\u00eb\5\uffff\1\u00ec\6\uffff\1\u00ed",
            "",
            "\1\u00ee\25\uffff\1\u00ef",
            "\1\u00f0\21\uffff\1\u00f1",
            "\1\u00f2\1\u00f3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00f4\1\uffff\1\u00f5",
            "\1\u00f6",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00ce\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u00f7\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00d0\1\u0085\6\uffff\4\u0084\1\u00f8"+
            "\25\u0084\6\uffff\4\u0084\1\u00f8\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00f9\1\126\1\u00f9\1\u0087\1\117\12\u00fa\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00fb\1\126\1\u00fb\1\u0087\1\117\12\u00fc\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u00fd\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00d4\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u00a7\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0100\1\126\1\u0100\1\123\1\117\12\u0101\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0102\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u00d8\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0103\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u00da\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u00ad\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u00dc\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00dc\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u00de\1\u0090\6\uffff\4"+
            "\u008d\1\u0104\25\u008d\6\uffff\4\u008d\1\u0104\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0105\1\126\1\u0105\1\u008f\1\126\12\u0106\1\u0090\6\uffff"+
            "\32\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0107\1\126\1\u0107\1\u008f\1\126\12\u0108\1\u0090\6\uffff"+
            "\32\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u00e2\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u00e2\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
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
            "\1\u0109",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u00ce\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u010c\1\126\1\u010c\1\u0087\1\117\12\u010d\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u010e\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00fa\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u010f\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u00fc\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u00d4\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u00dd\1\126\1\u00dd\1\123\1\117\12\u0110\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\123\1\117\12\u0111\1\120\6\uffff\4\116"+
            "\1\u0112\25\116\6\uffff\4\116\1\u0112\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0113\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u0101\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u00d8\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u00da\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0114\1\126\1\u0114\1\u008f\1\126\12\u0115\1\u0090\6\uffff"+
            "\32\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0106\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u0106\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0108\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u0108\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
            "\1\u0116",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u0117\1\u0085\6\uffff\32\u0084\6\uffff"+
            "\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u0087\1\117\12\u0118\1\u0085\6\uffff\4\u0084\1\u0119"+
            "\25\u0084\6\uffff\4\u0084\1\u0119\25\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u011a\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u010d\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u00fa\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u00fc\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u0110\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u0111\1\120\6\uffff\4\116"+
            "\1\u011b\25\116\6\uffff\4\116\1\u011b\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u011c\1\126\1\u011c\1\123\1\117\12\u011d\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u0101\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0115\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00b0\1\126\12\u0115\1\u0090\6\uffff\4"+
            "\u008d\1\u00af\25\u008d\6\uffff\4\u008d\1\u00af\25\u008d",
            "\1\u011e",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u0117\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u0118\1\u0085\6\uffff\4\u0084\1\u011f"+
            "\25\u0084\6\uffff\4\u0084\1\u011f\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0120\1\126\1\u0120\1\u0087\1\117\12\u0121\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u010d\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0122\1\126\1\u0122\1\123\1\117\12\u0123\1\120\6\uffff\32"+
            "\116\6\uffff\32\116",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0124\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u011d\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\u0125",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\u0126\1\126\1\u0126\1\u0087\1\117\12\u0127\1\u0085\6\uffff"+
            "\32\u0084\6\uffff\32\u0084",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u0128\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u0121\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u008d\1\126\1\u008c\2\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u008f\1\126\12\u0129\1\u0090\6\uffff\32"+
            "\u008d\6\uffff\32\u008d",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u0089\1\117\12\u0123\1\120\6\uffff\4\116"+
            "\1\u0088\25\116\6\uffff\4\116\1\u0088\25\116",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u011d\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "\1\u012a\2\uffff\1\u012b\21\uffff\1\u012c\1\u012d",
            "\3\126\2\uffff\1\126\1\uffff\1\126\3\uffff\5\126\12\u012e\1"+
            "\126\6\uffff\32\126\6\uffff\32\126",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u00a4\1\117\12\u0127\1\u0085\6\uffff\4\u0084\1\u00a3"+
            "\25\u0084\6\uffff\4\u0084\1\u00a3\25\u0084",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u0121\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\116\1\126\1\u0083\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\1\125\1\126\1\125\1\u00ff\1\117\12\u0123\1\120\6\uffff\4\116"+
            "\1\u00fe\25\116\6\uffff\4\116\1\u00fe\25\116",
            "",
            "\1\u012f",
            "",
            "",
            "\1\u0084\1\126\1\124\1\121\1\uffff\1\126\1\uffff\1\126\3\uffff"+
            "\3\126\1\u010b\1\117\12\u0127\1\u0085\6\uffff\4\u0084\1\u010a"+
            "\25\u0084\6\uffff\4\u0084\1\u010a\25\u0084",
            "\1\u0130",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134\60\uffff\1\u0135",
            "",
            ""
    };

    static final short[] DFA257_eot = DFA.unpackEncodedString(DFA257_eotS);
    static final short[] DFA257_eof = DFA.unpackEncodedString(DFA257_eofS);
    static final char[] DFA257_min = DFA.unpackEncodedStringToUnsignedChars(DFA257_minS);
    static final char[] DFA257_max = DFA.unpackEncodedStringToUnsignedChars(DFA257_maxS);
    static final short[] DFA257_accept = DFA.unpackEncodedString(DFA257_acceptS);
    static final short[] DFA257_special = DFA.unpackEncodedString(DFA257_specialS);
    static final short[][] DFA257_transition;

    static {
        int numStates = DFA257_transitionS.length;
        DFA257_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA257_transition[i] = DFA.unpackEncodedString(DFA257_transitionS[i]);
        }
    }

    class DFA257 extends DFA {

        public DFA257(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 257;
            this.eot = DFA257_eot;
            this.eof = DFA257_eof;
            this.min = DFA257_min;
            this.max = DFA257_max;
            this.accept = DFA257_accept;
            this.special = DFA257_special;
            this.transition = DFA257_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AAM | APB | BEC | BOD | BWC | BWR | BWW | DBT | DBK | DBS | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RSD | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | ALR | VDM | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | TXT | PRO | DEVICE | DEV | NUMBER | WS | SEP | SIGN | SIGNED | TIME_STAMP | CHECKSUM | NAME | LETTERS );";
        }
    }
 

}