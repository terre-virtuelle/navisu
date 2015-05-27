// $ANTLR 3.5.1 /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g 2015-05-27 14:13:00

package bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;

import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBS;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DPT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GLL;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MSK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RSD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VBW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VHW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VLW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VPW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.XTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.ZDA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GPSSatellite;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS08;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS18;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS24;

import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128267;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;  
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.impl.PrintHandler; 
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.AISParser;
 


 
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
    

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
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
	   
	   protected AIS01 ais01 = null;
	   protected AIS02 ais02 = null;
	   protected AIS03 ais03 = null;
	   protected AIS04 ais04 = null;
	   protected AIS05 ais05 = null;
	   protected AIS08 ais08 = null;
	   protected AIS18 ais18 = null;
	   protected AIS24 ais24 = null;
	   
	   protected PGN130306 pgn130306 = null;
	   protected PGN128267 pgn128267 = null;
	   
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
	   protected AISParser aisParser;

	   /* NmeaHandler is injected by the server 
	    The NMEA object is add at the sentences before fire event
	    @Override
	    public <T extends NMEA> void doIt(T t) {
	        sentences.add(t);
	    }
	   */
	    public void setAISParser(AISParser aisParser){
	      this.aisParser = aisParser;
	    }
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
	    
	    private Calendar timestampFactory(String timestamp){
	    String tmp = timestamp.replace("\"","");
	      String[] date = tmp.split("-");

	      int year = new Integer(date[0]);
	      int month =  new Integer(date[1]);
	      int day =  new Integer(date[2]);
	      
	      String[] time =  date[3].split(":");

	      int hour =  new Integer(time[0]);
	      int minute =  new Integer(time[1]);
	      
	      String[] sec = time[2].split(".");
	      int second =  new Integer(sec[0]);
	      
	      return new GregorianCalendar(year, month, day, hour, minute, second);
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
	@Override public String getGrammarFileName() { return "/home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g"; }

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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:291:6: ( '$' device= DEVICE 'AAM' SEP (arrivalCircleEntered= LETTERS )* SEP (perpendicularPassed= LETTERS )* SEP (circleRadius= NUMBER )* SEP ( LETTERS )* SEP (wayPointID= LETTERS |wayPointID= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:291:9: '$' device= DEVICE 'AAM' SEP (arrivalCircleEntered= LETTERS )* SEP (perpendicularPassed= LETTERS )* SEP (circleRadius= NUMBER )* SEP ( LETTERS )* SEP (wayPointID= LETTERS |wayPointID= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart29 = getCharIndex();
			int deviceStartLine29 = getLine();
			int deviceStartCharPos29 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart29, getCharIndex()-1);
			device.setLine(deviceStartLine29);
			device.setCharPositionInLine(deviceStartCharPos29);

			match("AAM"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:292:6: (arrivalCircleEntered= LETTERS )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==' '||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:292:7: arrivalCircleEntered= LETTERS
					{
					int arrivalCircleEnteredStart45 = getCharIndex();
					int arrivalCircleEnteredStartLine45 = getLine();
					int arrivalCircleEnteredStartCharPos45 = getCharPositionInLine();
					mLETTERS(); 
					arrivalCircleEntered = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, arrivalCircleEnteredStart45, getCharIndex()-1);
					arrivalCircleEntered.setLine(arrivalCircleEnteredStartLine45);
					arrivalCircleEntered.setCharPositionInLine(arrivalCircleEnteredStartCharPos45);

					}
					break;

				default :
					break loop1;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:293:13: (perpendicularPassed= LETTERS )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==' '||(LA2_0 >= 'A' && LA2_0 <= 'Z')||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:293:14: perpendicularPassed= LETTERS
					{
					int perpendicularPassedStart68 = getCharIndex();
					int perpendicularPassedStartLine68 = getLine();
					int perpendicularPassedStartCharPos68 = getCharPositionInLine();
					mLETTERS(); 
					perpendicularPassed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, perpendicularPassedStart68, getCharIndex()-1);
					perpendicularPassed.setLine(perpendicularPassedStartLine68);
					perpendicularPassed.setCharPositionInLine(perpendicularPassedStartCharPos68);

					}
					break;

				default :
					break loop2;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:294:13: (circleRadius= NUMBER )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='.'||(LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:294:14: circleRadius= NUMBER
					{
					int circleRadiusStart91 = getCharIndex();
					int circleRadiusStartLine91 = getLine();
					int circleRadiusStartCharPos91 = getCharPositionInLine();
					mNUMBER(); 
					circleRadius = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, circleRadiusStart91, getCharIndex()-1);
					circleRadius.setLine(circleRadiusStartLine91);
					circleRadius.setCharPositionInLine(circleRadiusStartCharPos91);

					}
					break;

				default :
					break loop3;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:295:13: ( LETTERS )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==' '||(LA4_0 >= 'A' && LA4_0 <= 'Z')||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:295:14: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop4;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:296:13: (wayPointID= LETTERS |wayPointID= NUMBER )*
			loop5:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:296:14: wayPointID= LETTERS
					{
					int wayPointIDStart133 = getCharIndex();
					int wayPointIDStartLine133 = getLine();
					int wayPointIDStartCharPos133 = getCharPositionInLine();
					mLETTERS(); 
					wayPointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wayPointIDStart133, getCharIndex()-1);
					wayPointID.setLine(wayPointIDStartLine133);
					wayPointID.setCharPositionInLine(wayPointIDStartCharPos133);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:296:37: wayPointID= NUMBER
					{
					int wayPointIDStart141 = getCharIndex();
					int wayPointIDStartLine141 = getLine();
					int wayPointIDStartCharPos141 = getCharPositionInLine();
					mNUMBER(); 
					wayPointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wayPointIDStart141, getCharIndex()-1);
					wayPointID.setLine(wayPointIDStartLine141);
					wayPointID.setCharPositionInLine(wayPointIDStartCharPos141);

					}
					break;

				default :
					break loop5;
				}
			}

			int checksumStart159 = getCharIndex();
			int checksumStartLine159 = getLine();
			int checksumStartCharPos159 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart159, getCharIndex()-1);
			checksum.setLine(checksumStartLine159);
			checksum.setCharPositionInLine(checksumStartCharPos159);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:318:5: ( '$' device= DEVICE 'APB' SEP (status0= LETTERS )* SEP (status1= LETTERS )* SEP (crossTrackErrorMagnitude= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (crossTrackUnits= LETTERS )* SEP (status2= LETTERS )* SEP (status3= LETTERS )* SEP (bearingOriginToDestination= NUMBER )* SEP (bearingOriginToDestinationType= LETTERS )* SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (bearingPresentPositionToDestination= NUMBER )* SEP (bearingPresentPositionToDestinationType= LETTERS )* SEP (headingToSteerToDestination= NUMBER )* SEP (headingToSteerToDestinationType= LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:318:7: '$' device= DEVICE 'APB' SEP (status0= LETTERS )* SEP (status1= LETTERS )* SEP (crossTrackErrorMagnitude= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (crossTrackUnits= LETTERS )* SEP (status2= LETTERS )* SEP (status3= LETTERS )* SEP (bearingOriginToDestination= NUMBER )* SEP (bearingOriginToDestinationType= LETTERS )* SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (bearingPresentPositionToDestination= NUMBER )* SEP (bearingPresentPositionToDestinationType= LETTERS )* SEP (headingToSteerToDestination= NUMBER )* SEP (headingToSteerToDestinationType= LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart175 = getCharIndex();
			int deviceStartLine175 = getLine();
			int deviceStartCharPos175 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart175, getCharIndex()-1);
			device.setLine(deviceStartLine175);
			device.setCharPositionInLine(deviceStartCharPos175);

			match("APB"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:319:15: (status0= LETTERS )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==' '||(LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:319:16: status0= LETTERS
					{
					int status0Start200 = getCharIndex();
					int status0StartLine200 = getLine();
					int status0StartCharPos200 = getCharPositionInLine();
					mLETTERS(); 
					status0 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status0Start200, getCharIndex()-1);
					status0.setLine(status0StartLine200);
					status0.setCharPositionInLine(status0StartCharPos200);

					}
					break;

				default :
					break loop6;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:320:15: (status1= LETTERS )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==' '||(LA7_0 >= 'A' && LA7_0 <= 'Z')||(LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:320:16: status1= LETTERS
					{
					int status1Start225 = getCharIndex();
					int status1StartLine225 = getLine();
					int status1StartCharPos225 = getCharPositionInLine();
					mLETTERS(); 
					status1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status1Start225, getCharIndex()-1);
					status1.setLine(status1StartLine225);
					status1.setCharPositionInLine(status1StartCharPos225);

					}
					break;

				default :
					break loop7;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:321:15: (crossTrackErrorMagnitude= NUMBER )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='.'||(LA8_0 >= '0' && LA8_0 <= '9')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:321:16: crossTrackErrorMagnitude= NUMBER
					{
					int crossTrackErrorMagnitudeStart250 = getCharIndex();
					int crossTrackErrorMagnitudeStartLine250 = getLine();
					int crossTrackErrorMagnitudeStartCharPos250 = getCharPositionInLine();
					mNUMBER(); 
					crossTrackErrorMagnitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorMagnitudeStart250, getCharIndex()-1);
					crossTrackErrorMagnitude.setLine(crossTrackErrorMagnitudeStartLine250);
					crossTrackErrorMagnitude.setCharPositionInLine(crossTrackErrorMagnitudeStartCharPos250);

					}
					break;

				default :
					break loop8;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:322:15: (directionToSteer= LETTERS )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==' '||(LA9_0 >= 'A' && LA9_0 <= 'Z')||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:322:16: directionToSteer= LETTERS
					{
					int directionToSteerStart275 = getCharIndex();
					int directionToSteerStartLine275 = getLine();
					int directionToSteerStartCharPos275 = getCharPositionInLine();
					mLETTERS(); 
					directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart275, getCharIndex()-1);
					directionToSteer.setLine(directionToSteerStartLine275);
					directionToSteer.setCharPositionInLine(directionToSteerStartCharPos275);

					}
					break;

				default :
					break loop9;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:323:15: (crossTrackUnits= LETTERS )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==' '||(LA10_0 >= 'A' && LA10_0 <= 'Z')||(LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:323:16: crossTrackUnits= LETTERS
					{
					int crossTrackUnitsStart300 = getCharIndex();
					int crossTrackUnitsStartLine300 = getLine();
					int crossTrackUnitsStartCharPos300 = getCharPositionInLine();
					mLETTERS(); 
					crossTrackUnits = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackUnitsStart300, getCharIndex()-1);
					crossTrackUnits.setLine(crossTrackUnitsStartLine300);
					crossTrackUnits.setCharPositionInLine(crossTrackUnitsStartCharPos300);

					}
					break;

				default :
					break loop10;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:324:15: (status2= LETTERS )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==' '||(LA11_0 >= 'A' && LA11_0 <= 'Z')||(LA11_0 >= 'a' && LA11_0 <= 'z')) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:324:16: status2= LETTERS
					{
					int status2Start325 = getCharIndex();
					int status2StartLine325 = getLine();
					int status2StartCharPos325 = getCharPositionInLine();
					mLETTERS(); 
					status2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status2Start325, getCharIndex()-1);
					status2.setLine(status2StartLine325);
					status2.setCharPositionInLine(status2StartCharPos325);

					}
					break;

				default :
					break loop11;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:325:15: (status3= LETTERS )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==' '||(LA12_0 >= 'A' && LA12_0 <= 'Z')||(LA12_0 >= 'a' && LA12_0 <= 'z')) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:325:16: status3= LETTERS
					{
					int status3Start350 = getCharIndex();
					int status3StartLine350 = getLine();
					int status3StartCharPos350 = getCharPositionInLine();
					mLETTERS(); 
					status3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, status3Start350, getCharIndex()-1);
					status3.setLine(status3StartLine350);
					status3.setCharPositionInLine(status3StartCharPos350);

					}
					break;

				default :
					break loop12;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:326:15: (bearingOriginToDestination= NUMBER )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0=='.'||(LA13_0 >= '0' && LA13_0 <= '9')) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:326:16: bearingOriginToDestination= NUMBER
					{
					int bearingOriginToDestinationStart375 = getCharIndex();
					int bearingOriginToDestinationStartLine375 = getLine();
					int bearingOriginToDestinationStartCharPos375 = getCharPositionInLine();
					mNUMBER(); 
					bearingOriginToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingOriginToDestinationStart375, getCharIndex()-1);
					bearingOriginToDestination.setLine(bearingOriginToDestinationStartLine375);
					bearingOriginToDestination.setCharPositionInLine(bearingOriginToDestinationStartCharPos375);

					}
					break;

				default :
					break loop13;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:327:15: (bearingOriginToDestinationType= LETTERS )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==' '||(LA14_0 >= 'A' && LA14_0 <= 'Z')||(LA14_0 >= 'a' && LA14_0 <= 'z')) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:327:16: bearingOriginToDestinationType= LETTERS
					{
					int bearingOriginToDestinationTypeStart398 = getCharIndex();
					int bearingOriginToDestinationTypeStartLine398 = getLine();
					int bearingOriginToDestinationTypeStartCharPos398 = getCharPositionInLine();
					mLETTERS(); 
					bearingOriginToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingOriginToDestinationTypeStart398, getCharIndex()-1);
					bearingOriginToDestinationType.setLine(bearingOriginToDestinationTypeStartLine398);
					bearingOriginToDestinationType.setCharPositionInLine(bearingOriginToDestinationTypeStartCharPos398);

					}
					break;

				default :
					break loop14;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:328:15: (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )*
			loop15:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:328:16: destinationWaypointID= LETTERS
					{
					int destinationWaypointIDStart423 = getCharIndex();
					int destinationWaypointIDStartLine423 = getLine();
					int destinationWaypointIDStartCharPos423 = getCharPositionInLine();
					mLETTERS(); 
					destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart423, getCharIndex()-1);
					destinationWaypointID.setLine(destinationWaypointIDStartLine423);
					destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos423);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:328:50: destinationWaypointID= NUMBER
					{
					int destinationWaypointIDStart431 = getCharIndex();
					int destinationWaypointIDStartLine431 = getLine();
					int destinationWaypointIDStartCharPos431 = getCharPositionInLine();
					mNUMBER(); 
					destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart431, getCharIndex()-1);
					destinationWaypointID.setLine(destinationWaypointIDStartLine431);
					destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos431);

					}
					break;

				default :
					break loop15;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:329:15: (bearingPresentPositionToDestination= NUMBER )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0=='.'||(LA16_0 >= '0' && LA16_0 <= '9')) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:329:16: bearingPresentPositionToDestination= NUMBER
					{
					int bearingPresentPositionToDestinationStart456 = getCharIndex();
					int bearingPresentPositionToDestinationStartLine456 = getLine();
					int bearingPresentPositionToDestinationStartCharPos456 = getCharPositionInLine();
					mNUMBER(); 
					bearingPresentPositionToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingPresentPositionToDestinationStart456, getCharIndex()-1);
					bearingPresentPositionToDestination.setLine(bearingPresentPositionToDestinationStartLine456);
					bearingPresentPositionToDestination.setCharPositionInLine(bearingPresentPositionToDestinationStartCharPos456);

					}
					break;

				default :
					break loop16;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:330:15: (bearingPresentPositionToDestinationType= LETTERS )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==' '||(LA17_0 >= 'A' && LA17_0 <= 'Z')||(LA17_0 >= 'a' && LA17_0 <= 'z')) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:330:16: bearingPresentPositionToDestinationType= LETTERS
					{
					int bearingPresentPositionToDestinationTypeStart479 = getCharIndex();
					int bearingPresentPositionToDestinationTypeStartLine479 = getLine();
					int bearingPresentPositionToDestinationTypeStartCharPos479 = getCharPositionInLine();
					mLETTERS(); 
					bearingPresentPositionToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingPresentPositionToDestinationTypeStart479, getCharIndex()-1);
					bearingPresentPositionToDestinationType.setLine(bearingPresentPositionToDestinationTypeStartLine479);
					bearingPresentPositionToDestinationType.setCharPositionInLine(bearingPresentPositionToDestinationTypeStartCharPos479);

					}
					break;

				default :
					break loop17;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:331:15: (headingToSteerToDestination= NUMBER )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0=='.'||(LA18_0 >= '0' && LA18_0 <= '9')) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:331:16: headingToSteerToDestination= NUMBER
					{
					int headingToSteerToDestinationStart504 = getCharIndex();
					int headingToSteerToDestinationStartLine504 = getLine();
					int headingToSteerToDestinationStartCharPos504 = getCharPositionInLine();
					mNUMBER(); 
					headingToSteerToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingToSteerToDestinationStart504, getCharIndex()-1);
					headingToSteerToDestination.setLine(headingToSteerToDestinationStartLine504);
					headingToSteerToDestination.setCharPositionInLine(headingToSteerToDestinationStartCharPos504);

					}
					break;

				default :
					break loop18;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:332:15: (headingToSteerToDestinationType= LETTERS )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==' '||(LA19_0 >= 'A' && LA19_0 <= 'Z')||(LA19_0 >= 'a' && LA19_0 <= 'z')) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:332:16: headingToSteerToDestinationType= LETTERS
					{
					int headingToSteerToDestinationTypeStart529 = getCharIndex();
					int headingToSteerToDestinationTypeStartLine529 = getLine();
					int headingToSteerToDestinationTypeStartCharPos529 = getCharPositionInLine();
					mLETTERS(); 
					headingToSteerToDestinationType = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingToSteerToDestinationTypeStart529, getCharIndex()-1);
					headingToSteerToDestinationType.setLine(headingToSteerToDestinationTypeStartLine529);
					headingToSteerToDestinationType.setCharPositionInLine(headingToSteerToDestinationTypeStartCharPos529);

					}
					break;

				default :
					break loop19;
				}
			}

			int checksumStart549 = getCharIndex();
			int checksumStartLine549 = getLine();
			int checksumStartCharPos549 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart549, getCharIndex()-1);
			checksum.setLine(checksumStartLine549);
			checksum.setCharPositionInLine(checksumStartCharPos549);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:358:7: ( '$' device= DEVICE 'BEC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:358:9: '$' device= DEVICE 'BEC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart569 = getCharIndex();
			int deviceStartLine569 = getLine();
			int deviceStartCharPos569 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart569, getCharIndex()-1);
			device.setLine(deviceStartLine569);
			device.setCharPositionInLine(deviceStartCharPos569);

			match("BEC"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:359:7: ( ' ' )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0==' ') ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:359:7: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop20;
				}
			}

			int utcStart599 = getCharIndex();
			int utcStartLine599 = getLine();
			int utcStartCharPos599 = getCharPositionInLine();
			mNUMBER(); 
			utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart599, getCharIndex()-1);
			utc.setLine(utcStartLine599);
			utc.setCharPositionInLine(utcStartCharPos599);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:360:25: ( SEP )+
			int cnt21=0;
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( (LA21_0==',') ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(21, input);
					throw eee;
				}
				cnt21++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:361:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0=='.'||(LA22_0 >= '0' && LA22_0 <= '9')) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:361:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
					{
					int latitudeStart619 = getCharIndex();
					int latitudeStartLine619 = getLine();
					int latitudeStartCharPos619 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart619, getCharIndex()-1);
					latitude.setLine(latitudeStartLine619);
					latitude.setCharPositionInLine(latitudeStartCharPos619);

					mSEP(); 

					int nsStart627 = getCharIndex();
					int nsStartLine627 = getLine();
					int nsStartCharPos627 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart627, getCharIndex()-1);
					ns.setLine(nsStartLine627);
					ns.setCharPositionInLine(nsStartCharPos627);

					mSEP(); 

					int longitudeStart633 = getCharIndex();
					int longitudeStartLine633 = getLine();
					int longitudeStartCharPos633 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart633, getCharIndex()-1);
					longitude.setLine(longitudeStartLine633);
					longitude.setCharPositionInLine(longitudeStartCharPos633);

					mSEP(); 

					int ewStart639 = getCharIndex();
					int ewStartLine639 = getLine();
					int ewStartCharPos639 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart639, getCharIndex()-1);
					ew.setLine(ewStartLine639);
					ew.setCharPositionInLine(ewStartCharPos639);

					mSEP(); 

					int bearingDegreesTrueStart645 = getCharIndex();
					int bearingDegreesTrueStartLine645 = getLine();
					int bearingDegreesTrueStartCharPos645 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart645, getCharIndex()-1);
					bearingDegreesTrue.setLine(bearingDegreesTrueStartLine645);
					bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos645);

					mSEP(); 

					}
					break;

				default :
					break loop22;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:362:22: ( SEP )+
			int cnt23=0;
			loop23:
			while (true) {
				int alt23=2;
				int LA23_0 = input.LA(1);
				if ( (LA23_0==',') ) {
					alt23=1;
				}

				switch (alt23) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(23, input);
					throw eee;
				}
				cnt23++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:363:13: (bearingDegreesMagnetic= NUMBER SEP )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0=='.'||(LA24_0 >= '0' && LA24_0 <= '9')) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:363:14: bearingDegreesMagnetic= NUMBER SEP
					{
					int bearingDegreesMagneticStart684 = getCharIndex();
					int bearingDegreesMagneticStartLine684 = getLine();
					int bearingDegreesMagneticStartCharPos684 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart684, getCharIndex()-1);
					bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine684);
					bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos684);

					mSEP(); 

					}
					break;

				default :
					break loop24;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:364:22: ( SEP )+
			int cnt25=0;
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==',') ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(25, input);
					throw eee;
				}
				cnt25++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:365:13: (distanceToWayPoint= NUMBER SEP )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0=='.'||(LA26_0 >= '0' && LA26_0 <= '9')) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:365:14: distanceToWayPoint= NUMBER SEP
					{
					int distanceToWayPointStart724 = getCharIndex();
					int distanceToWayPointStartLine724 = getLine();
					int distanceToWayPointStartCharPos724 = getCharPositionInLine();
					mNUMBER(); 
					distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart724, getCharIndex()-1);
					distanceToWayPoint.setLine(distanceToWayPointStartLine724);
					distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos724);

					mSEP(); 

					}
					break;

				default :
					break loop26;
				}
			}

			int unitsOfDistanceToWayPointStart745 = getCharIndex();
			int unitsOfDistanceToWayPointStartLine745 = getLine();
			int unitsOfDistanceToWayPointStartCharPos745 = getCharPositionInLine();
			mLETTERS(); 
			unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart745, getCharIndex()-1);
			unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine745);
			unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos745);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:366:48: ( SEP )+
			int cnt27=0;
			loop27:
			while (true) {
				int alt27=2;
				int LA27_0 = input.LA(1);
				if ( (LA27_0==',') ) {
					alt27=1;
				}

				switch (alt27) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(27, input);
					throw eee;
				}
				cnt27++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:367:13: (waypointID= LETTERS |waypointID= NUMBER )*
			loop28:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:367:14: waypointID= LETTERS
					{
					int waypointIDStart765 = getCharIndex();
					int waypointIDStartLine765 = getLine();
					int waypointIDStartCharPos765 = getCharPositionInLine();
					mLETTERS(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart765, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine765);
					waypointID.setCharPositionInLine(waypointIDStartCharPos765);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:367:35: waypointID= NUMBER
					{
					int waypointIDStart771 = getCharIndex();
					int waypointIDStartLine771 = getLine();
					int waypointIDStartCharPos771 = getCharPositionInLine();
					mNUMBER(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart771, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine771);
					waypointID.setCharPositionInLine(waypointIDStartCharPos771);

					}
					break;

				default :
					break loop28;
				}
			}

			int checksumStart790 = getCharIndex();
			int checksumStartLine790 = getLine();
			int checksumStartCharPos790 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart790, getCharIndex()-1);
			checksum.setLine(checksumStartLine790);
			checksum.setCharPositionInLine(checksumStartCharPos790);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:390:7: ( '$' device= DEVICE 'BOD' SEP (bearingDegreesTrue= NUMBER )* ( SEP )+ LETTERS SEP (bearingDegreesMagnetic= NUMBER )* ( SEP )+ LETTERS SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (originWaypointID= LETTERS |originWaypointID= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:390:9: '$' device= DEVICE 'BOD' SEP (bearingDegreesTrue= NUMBER )* ( SEP )+ LETTERS SEP (bearingDegreesMagnetic= NUMBER )* ( SEP )+ LETTERS SEP (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )* SEP (originWaypointID= LETTERS |originWaypointID= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart819 = getCharIndex();
			int deviceStartLine819 = getLine();
			int deviceStartCharPos819 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart819, getCharIndex()-1);
			device.setLine(deviceStartLine819);
			device.setCharPositionInLine(deviceStartCharPos819);

			match("BOD"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:391:6: (bearingDegreesTrue= NUMBER )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0=='.'||(LA29_0 >= '0' && LA29_0 <= '9')) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:391:7: bearingDegreesTrue= NUMBER
					{
					int bearingDegreesTrueStart833 = getCharIndex();
					int bearingDegreesTrueStartLine833 = getLine();
					int bearingDegreesTrueStartCharPos833 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart833, getCharIndex()-1);
					bearingDegreesTrue.setLine(bearingDegreesTrueStartLine833);
					bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos833);

					}
					break;

				default :
					break loop29;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:391:35: ( SEP )+
			int cnt30=0;
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==',') ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(30, input);
					throw eee;
				}
				cnt30++;
			}

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:393:6: (bearingDegreesMagnetic= NUMBER )*
			loop31:
			while (true) {
				int alt31=2;
				int LA31_0 = input.LA(1);
				if ( (LA31_0=='.'||(LA31_0 >= '0' && LA31_0 <= '9')) ) {
					alt31=1;
				}

				switch (alt31) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:393:7: bearingDegreesMagnetic= NUMBER
					{
					int bearingDegreesMagneticStart857 = getCharIndex();
					int bearingDegreesMagneticStartLine857 = getLine();
					int bearingDegreesMagneticStartCharPos857 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart857, getCharIndex()-1);
					bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine857);
					bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos857);

					}
					break;

				default :
					break loop31;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:393:39: ( SEP )+
			int cnt32=0;
			loop32:
			while (true) {
				int alt32=2;
				int LA32_0 = input.LA(1);
				if ( (LA32_0==',') ) {
					alt32=1;
				}

				switch (alt32) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(32, input);
					throw eee;
				}
				cnt32++;
			}

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:395:6: (destinationWaypointID= LETTERS |destinationWaypointID= NUMBER )*
			loop33:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:395:7: destinationWaypointID= LETTERS
					{
					int destinationWaypointIDStart881 = getCharIndex();
					int destinationWaypointIDStartLine881 = getLine();
					int destinationWaypointIDStartCharPos881 = getCharPositionInLine();
					mLETTERS(); 
					destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart881, getCharIndex()-1);
					destinationWaypointID.setLine(destinationWaypointIDStartLine881);
					destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos881);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:395:39: destinationWaypointID= NUMBER
					{
					int destinationWaypointIDStart887 = getCharIndex();
					int destinationWaypointIDStartLine887 = getLine();
					int destinationWaypointIDStartCharPos887 = getCharPositionInLine();
					mNUMBER(); 
					destinationWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointIDStart887, getCharIndex()-1);
					destinationWaypointID.setLine(destinationWaypointIDStartLine887);
					destinationWaypointID.setCharPositionInLine(destinationWaypointIDStartCharPos887);

					}
					break;

				default :
					break loop33;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:396:6: (originWaypointID= LETTERS |originWaypointID= NUMBER )*
			loop34:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:396:7: originWaypointID= LETTERS
					{
					int originWaypointIDStart902 = getCharIndex();
					int originWaypointIDStartLine902 = getLine();
					int originWaypointIDStartCharPos902 = getCharPositionInLine();
					mLETTERS(); 
					originWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, originWaypointIDStart902, getCharIndex()-1);
					originWaypointID.setLine(originWaypointIDStartLine902);
					originWaypointID.setCharPositionInLine(originWaypointIDStartCharPos902);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:396:34: originWaypointID= NUMBER
					{
					int originWaypointIDStart908 = getCharIndex();
					int originWaypointIDStartLine908 = getLine();
					int originWaypointIDStartCharPos908 = getCharPositionInLine();
					mNUMBER(); 
					originWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, originWaypointIDStart908, getCharIndex()-1);
					originWaypointID.setLine(originWaypointIDStartLine908);
					originWaypointID.setCharPositionInLine(originWaypointIDStartCharPos908);

					}
					break;

				default :
					break loop34;
				}
			}

			int checksumStart919 = getCharIndex();
			int checksumStartLine919 = getLine();
			int checksumStartCharPos919 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart919, getCharIndex()-1);
			checksum.setLine(checksumStartLine919);
			checksum.setCharPositionInLine(checksumStartCharPos919);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:409:6: ( '$' device= DEVICE 'BWC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:409:9: '$' device= DEVICE 'BWC' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart941 = getCharIndex();
			int deviceStartLine941 = getLine();
			int deviceStartCharPos941 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart941, getCharIndex()-1);
			device.setLine(deviceStartLine941);
			device.setCharPositionInLine(deviceStartCharPos941);

			match("BWC"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:410:14: ( ' ' )*
			loop35:
			while (true) {
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==' ') ) {
					alt35=1;
				}

				switch (alt35) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:410:14: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop35;
				}
			}

			int utcStart978 = getCharIndex();
			int utcStartLine978 = getLine();
			int utcStartCharPos978 = getCharPositionInLine();
			mNUMBER(); 
			utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart978, getCharIndex()-1);
			utc.setLine(utcStartLine978);
			utc.setCharPositionInLine(utcStartCharPos978);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:411:25: ( SEP )+
			int cnt36=0;
			loop36:
			while (true) {
				int alt36=2;
				int LA36_0 = input.LA(1);
				if ( (LA36_0==',') ) {
					alt36=1;
				}

				switch (alt36) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(36, input);
					throw eee;
				}
				cnt36++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:412:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
			loop37:
			while (true) {
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0=='.'||(LA37_0 >= '0' && LA37_0 <= '9')) ) {
					alt37=1;
				}

				switch (alt37) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:412:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
					{
					int latitudeStart998 = getCharIndex();
					int latitudeStartLine998 = getLine();
					int latitudeStartCharPos998 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart998, getCharIndex()-1);
					latitude.setLine(latitudeStartLine998);
					latitude.setCharPositionInLine(latitudeStartCharPos998);

					mSEP(); 

					int nsStart1006 = getCharIndex();
					int nsStartLine1006 = getLine();
					int nsStartCharPos1006 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1006, getCharIndex()-1);
					ns.setLine(nsStartLine1006);
					ns.setCharPositionInLine(nsStartCharPos1006);

					mSEP(); 

					int longitudeStart1012 = getCharIndex();
					int longitudeStartLine1012 = getLine();
					int longitudeStartCharPos1012 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1012, getCharIndex()-1);
					longitude.setLine(longitudeStartLine1012);
					longitude.setCharPositionInLine(longitudeStartCharPos1012);

					mSEP(); 

					int ewStart1018 = getCharIndex();
					int ewStartLine1018 = getLine();
					int ewStartCharPos1018 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1018, getCharIndex()-1);
					ew.setLine(ewStartLine1018);
					ew.setCharPositionInLine(ewStartCharPos1018);

					mSEP(); 

					int bearingDegreesTrueStart1024 = getCharIndex();
					int bearingDegreesTrueStartLine1024 = getLine();
					int bearingDegreesTrueStartCharPos1024 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1024, getCharIndex()-1);
					bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1024);
					bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1024);

					mSEP(); 

					}
					break;

				default :
					break loop37;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:413:22: ( SEP )+
			int cnt38=0;
			loop38:
			while (true) {
				int alt38=2;
				int LA38_0 = input.LA(1);
				if ( (LA38_0==',') ) {
					alt38=1;
				}

				switch (alt38) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(38, input);
					throw eee;
				}
				cnt38++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:414:13: (bearingDegreesMagnetic= NUMBER SEP )*
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0=='.'||(LA39_0 >= '0' && LA39_0 <= '9')) ) {
					alt39=1;
				}

				switch (alt39) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:414:14: bearingDegreesMagnetic= NUMBER SEP
					{
					int bearingDegreesMagneticStart1063 = getCharIndex();
					int bearingDegreesMagneticStartLine1063 = getLine();
					int bearingDegreesMagneticStartCharPos1063 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1063, getCharIndex()-1);
					bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1063);
					bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1063);

					mSEP(); 

					}
					break;

				default :
					break loop39;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:415:22: ( SEP )+
			int cnt40=0;
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( (LA40_0==',') ) {
					alt40=1;
				}

				switch (alt40) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(40, input);
					throw eee;
				}
				cnt40++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:416:13: (distanceToWayPoint= NUMBER SEP )*
			loop41:
			while (true) {
				int alt41=2;
				int LA41_0 = input.LA(1);
				if ( (LA41_0=='.'||(LA41_0 >= '0' && LA41_0 <= '9')) ) {
					alt41=1;
				}

				switch (alt41) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:416:14: distanceToWayPoint= NUMBER SEP
					{
					int distanceToWayPointStart1103 = getCharIndex();
					int distanceToWayPointStartLine1103 = getLine();
					int distanceToWayPointStartCharPos1103 = getCharPositionInLine();
					mNUMBER(); 
					distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart1103, getCharIndex()-1);
					distanceToWayPoint.setLine(distanceToWayPointStartLine1103);
					distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos1103);

					mSEP(); 

					}
					break;

				default :
					break loop41;
				}
			}

			int unitsOfDistanceToWayPointStart1124 = getCharIndex();
			int unitsOfDistanceToWayPointStartLine1124 = getLine();
			int unitsOfDistanceToWayPointStartCharPos1124 = getCharPositionInLine();
			mLETTERS(); 
			unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart1124, getCharIndex()-1);
			unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine1124);
			unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos1124);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:417:48: ( SEP )+
			int cnt42=0;
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==',') ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(42, input);
					throw eee;
				}
				cnt42++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:418:13: (waypointID= LETTERS |waypointID= NUMBER )*
			loop43:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:418:14: waypointID= LETTERS
					{
					int waypointIDStart1144 = getCharIndex();
					int waypointIDStartLine1144 = getLine();
					int waypointIDStartCharPos1144 = getCharPositionInLine();
					mLETTERS(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1144, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine1144);
					waypointID.setCharPositionInLine(waypointIDStartCharPos1144);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:418:35: waypointID= NUMBER
					{
					int waypointIDStart1150 = getCharIndex();
					int waypointIDStartLine1150 = getLine();
					int waypointIDStartCharPos1150 = getCharPositionInLine();
					mNUMBER(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1150, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine1150);
					waypointID.setCharPositionInLine(waypointIDStartCharPos1150);

					}
					break;

				default :
					break loop43;
				}
			}

			int checksumStart1169 = getCharIndex();
			int checksumStartLine1169 = getLine();
			int checksumStartCharPos1169 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1169, getCharIndex()-1);
			checksum.setLine(checksumStartLine1169);
			checksum.setCharPositionInLine(checksumStartCharPos1169);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:443:6: ( '$' device= DEVICE 'BWR' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:443:10: '$' device= DEVICE 'BWR' SEP ( ' ' )* utc= NUMBER ( SEP )+ (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )* LETTERS ( SEP )+ (bearingDegreesMagnetic= NUMBER SEP )* LETTERS ( SEP )+ (distanceToWayPoint= NUMBER SEP )* unitsOfDistanceToWayPoint= LETTERS ( SEP )+ (waypointID= LETTERS |waypointID= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart1203 = getCharIndex();
			int deviceStartLine1203 = getLine();
			int deviceStartCharPos1203 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1203, getCharIndex()-1);
			device.setLine(deviceStartLine1203);
			device.setCharPositionInLine(deviceStartCharPos1203);

			match("BWR"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:444:7: ( ' ' )*
			loop44:
			while (true) {
				int alt44=2;
				int LA44_0 = input.LA(1);
				if ( (LA44_0==' ') ) {
					alt44=1;
				}

				switch (alt44) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:444:7: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop44;
				}
			}

			int utcStart1234 = getCharIndex();
			int utcStartLine1234 = getLine();
			int utcStartCharPos1234 = getCharPositionInLine();
			mNUMBER(); 
			utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart1234, getCharIndex()-1);
			utc.setLine(utcStartLine1234);
			utc.setCharPositionInLine(utcStartCharPos1234);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:445:25: ( SEP )+
			int cnt45=0;
			loop45:
			while (true) {
				int alt45=2;
				int LA45_0 = input.LA(1);
				if ( (LA45_0==',') ) {
					alt45=1;
				}

				switch (alt45) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(45, input);
					throw eee;
				}
				cnt45++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:446:13: (latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP )*
			loop46:
			while (true) {
				int alt46=2;
				int LA46_0 = input.LA(1);
				if ( (LA46_0=='.'||(LA46_0 >= '0' && LA46_0 <= '9')) ) {
					alt46=1;
				}

				switch (alt46) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:446:14: latitude= NUMBER SEP ns= LETTERS SEP longitude= NUMBER SEP ew= LETTERS SEP bearingDegreesTrue= NUMBER SEP
					{
					int latitudeStart1254 = getCharIndex();
					int latitudeStartLine1254 = getLine();
					int latitudeStartCharPos1254 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart1254, getCharIndex()-1);
					latitude.setLine(latitudeStartLine1254);
					latitude.setCharPositionInLine(latitudeStartCharPos1254);

					mSEP(); 

					int nsStart1262 = getCharIndex();
					int nsStartLine1262 = getLine();
					int nsStartCharPos1262 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1262, getCharIndex()-1);
					ns.setLine(nsStartLine1262);
					ns.setCharPositionInLine(nsStartCharPos1262);

					mSEP(); 

					int longitudeStart1268 = getCharIndex();
					int longitudeStartLine1268 = getLine();
					int longitudeStartCharPos1268 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1268, getCharIndex()-1);
					longitude.setLine(longitudeStartLine1268);
					longitude.setCharPositionInLine(longitudeStartCharPos1268);

					mSEP(); 

					int ewStart1274 = getCharIndex();
					int ewStartLine1274 = getLine();
					int ewStartCharPos1274 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1274, getCharIndex()-1);
					ew.setLine(ewStartLine1274);
					ew.setCharPositionInLine(ewStartCharPos1274);

					mSEP(); 

					int bearingDegreesTrueStart1280 = getCharIndex();
					int bearingDegreesTrueStartLine1280 = getLine();
					int bearingDegreesTrueStartCharPos1280 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1280, getCharIndex()-1);
					bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1280);
					bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1280);

					mSEP(); 

					}
					break;

				default :
					break loop46;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:447:22: ( SEP )+
			int cnt47=0;
			loop47:
			while (true) {
				int alt47=2;
				int LA47_0 = input.LA(1);
				if ( (LA47_0==',') ) {
					alt47=1;
				}

				switch (alt47) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(47, input);
					throw eee;
				}
				cnt47++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:448:13: (bearingDegreesMagnetic= NUMBER SEP )*
			loop48:
			while (true) {
				int alt48=2;
				int LA48_0 = input.LA(1);
				if ( (LA48_0=='.'||(LA48_0 >= '0' && LA48_0 <= '9')) ) {
					alt48=1;
				}

				switch (alt48) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:448:14: bearingDegreesMagnetic= NUMBER SEP
					{
					int bearingDegreesMagneticStart1319 = getCharIndex();
					int bearingDegreesMagneticStartLine1319 = getLine();
					int bearingDegreesMagneticStartCharPos1319 = getCharPositionInLine();
					mNUMBER(); 
					bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1319, getCharIndex()-1);
					bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1319);
					bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1319);

					mSEP(); 

					}
					break;

				default :
					break loop48;
				}
			}

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:449:22: ( SEP )+
			int cnt49=0;
			loop49:
			while (true) {
				int alt49=2;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==',') ) {
					alt49=1;
				}

				switch (alt49) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(49, input);
					throw eee;
				}
				cnt49++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:450:13: (distanceToWayPoint= NUMBER SEP )*
			loop50:
			while (true) {
				int alt50=2;
				int LA50_0 = input.LA(1);
				if ( (LA50_0=='.'||(LA50_0 >= '0' && LA50_0 <= '9')) ) {
					alt50=1;
				}

				switch (alt50) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:450:14: distanceToWayPoint= NUMBER SEP
					{
					int distanceToWayPointStart1359 = getCharIndex();
					int distanceToWayPointStartLine1359 = getLine();
					int distanceToWayPointStartCharPos1359 = getCharPositionInLine();
					mNUMBER(); 
					distanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, distanceToWayPointStart1359, getCharIndex()-1);
					distanceToWayPoint.setLine(distanceToWayPointStartLine1359);
					distanceToWayPoint.setCharPositionInLine(distanceToWayPointStartCharPos1359);

					mSEP(); 

					}
					break;

				default :
					break loop50;
				}
			}

			int unitsOfDistanceToWayPointStart1380 = getCharIndex();
			int unitsOfDistanceToWayPointStartLine1380 = getLine();
			int unitsOfDistanceToWayPointStartCharPos1380 = getCharPositionInLine();
			mLETTERS(); 
			unitsOfDistanceToWayPoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfDistanceToWayPointStart1380, getCharIndex()-1);
			unitsOfDistanceToWayPoint.setLine(unitsOfDistanceToWayPointStartLine1380);
			unitsOfDistanceToWayPoint.setCharPositionInLine(unitsOfDistanceToWayPointStartCharPos1380);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:451:48: ( SEP )+
			int cnt51=0;
			loop51:
			while (true) {
				int alt51=2;
				int LA51_0 = input.LA(1);
				if ( (LA51_0==',') ) {
					alt51=1;
				}

				switch (alt51) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(51, input);
					throw eee;
				}
				cnt51++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:452:13: (waypointID= LETTERS |waypointID= NUMBER )*
			loop52:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:452:14: waypointID= LETTERS
					{
					int waypointIDStart1400 = getCharIndex();
					int waypointIDStartLine1400 = getLine();
					int waypointIDStartCharPos1400 = getCharPositionInLine();
					mLETTERS(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1400, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine1400);
					waypointID.setCharPositionInLine(waypointIDStartCharPos1400);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:452:35: waypointID= NUMBER
					{
					int waypointIDStart1406 = getCharIndex();
					int waypointIDStartLine1406 = getLine();
					int waypointIDStartCharPos1406 = getCharPositionInLine();
					mNUMBER(); 
					waypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waypointIDStart1406, getCharIndex()-1);
					waypointID.setLine(waypointIDStartLine1406);
					waypointID.setCharPositionInLine(waypointIDStartCharPos1406);

					}
					break;

				default :
					break loop52;
				}
			}

			int checksumStart1425 = getCharIndex();
			int checksumStartLine1425 = getLine();
			int checksumStartCharPos1425 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1425, getCharIndex()-1);
			checksum.setLine(checksumStartLine1425);
			checksum.setCharPositionInLine(checksumStartCharPos1425);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:499:6: ( '$' device= DEVICE 'BWW' SEP bearingDegreesTrue= NUMBER SEP LETTERS SEP bearingDegreesMagnetic= NUMBER SEP LETTERS SEP (toWaypointID= LETTERS |toWaypointID= NUMBER ) SEP (fromWaypointID= LETTERS |fromWaypointID= NUMBER ) SEP checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:499:8: '$' device= DEVICE 'BWW' SEP bearingDegreesTrue= NUMBER SEP LETTERS SEP bearingDegreesMagnetic= NUMBER SEP LETTERS SEP (toWaypointID= LETTERS |toWaypointID= NUMBER ) SEP (fromWaypointID= LETTERS |fromWaypointID= NUMBER ) SEP checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart1461 = getCharIndex();
			int deviceStartLine1461 = getLine();
			int deviceStartCharPos1461 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1461, getCharIndex()-1);
			device.setLine(deviceStartLine1461);
			device.setCharPositionInLine(deviceStartCharPos1461);

			match("BWW"); 

			mSEP(); 

			int bearingDegreesTrueStart1475 = getCharIndex();
			int bearingDegreesTrueStartLine1475 = getLine();
			int bearingDegreesTrueStartCharPos1475 = getCharPositionInLine();
			mNUMBER(); 
			bearingDegreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesTrueStart1475, getCharIndex()-1);
			bearingDegreesTrue.setLine(bearingDegreesTrueStartLine1475);
			bearingDegreesTrue.setCharPositionInLine(bearingDegreesTrueStartCharPos1475);

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			int bearingDegreesMagneticStart1493 = getCharIndex();
			int bearingDegreesMagneticStartLine1493 = getLine();
			int bearingDegreesMagneticStartCharPos1493 = getCharPositionInLine();
			mNUMBER(); 
			bearingDegreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingDegreesMagneticStart1493, getCharIndex()-1);
			bearingDegreesMagnetic.setLine(bearingDegreesMagneticStartLine1493);
			bearingDegreesMagnetic.setCharPositionInLine(bearingDegreesMagneticStartCharPos1493);

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:504:4: (toWaypointID= LETTERS |toWaypointID= NUMBER )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:504:5: toWaypointID= LETTERS
					{
					int toWaypointIDStart1510 = getCharIndex();
					int toWaypointIDStartLine1510 = getLine();
					int toWaypointIDStartCharPos1510 = getCharPositionInLine();
					mLETTERS(); 
					toWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIDStart1510, getCharIndex()-1);
					toWaypointID.setLine(toWaypointIDStartLine1510);
					toWaypointID.setCharPositionInLine(toWaypointIDStartCharPos1510);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:504:28: toWaypointID= NUMBER
					{
					int toWaypointIDStart1516 = getCharIndex();
					int toWaypointIDStartLine1516 = getLine();
					int toWaypointIDStartCharPos1516 = getCharPositionInLine();
					mNUMBER(); 
					toWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIDStart1516, getCharIndex()-1);
					toWaypointID.setLine(toWaypointIDStartLine1516);
					toWaypointID.setCharPositionInLine(toWaypointIDStartCharPos1516);

					}
					break;

			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:505:4: (fromWaypointID= LETTERS |fromWaypointID= NUMBER )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:505:5: fromWaypointID= LETTERS
					{
					int fromWaypointIDStart1527 = getCharIndex();
					int fromWaypointIDStartLine1527 = getLine();
					int fromWaypointIDStartCharPos1527 = getCharPositionInLine();
					mLETTERS(); 
					fromWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIDStart1527, getCharIndex()-1);
					fromWaypointID.setLine(fromWaypointIDStartLine1527);
					fromWaypointID.setCharPositionInLine(fromWaypointIDStartCharPos1527);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:505:30: fromWaypointID= NUMBER
					{
					int fromWaypointIDStart1533 = getCharIndex();
					int fromWaypointIDStartLine1533 = getLine();
					int fromWaypointIDStartCharPos1533 = getCharPositionInLine();
					mNUMBER(); 
					fromWaypointID = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIDStart1533, getCharIndex()-1);
					fromWaypointID.setLine(fromWaypointIDStartLine1533);
					fromWaypointID.setCharPositionInLine(fromWaypointIDStartCharPos1533);

					}
					break;

			}

			mSEP(); 

			int checksumStart1543 = getCharIndex();
			int checksumStartLine1543 = getLine();
			int checksumStartCharPos1543 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1543, getCharIndex()-1);
			checksum.setLine(checksumStartLine1543);
			checksum.setCharPositionInLine(checksumStartCharPos1543);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:508:5: ( '$' device= DEVICE 'DBT' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:508:7: '$' device= DEVICE 'DBT' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
			{
			match('$'); 
			int deviceStart1561 = getCharIndex();
			int deviceStartLine1561 = getLine();
			int deviceStartCharPos1561 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1561, getCharIndex()-1);
			device.setLine(deviceStartLine1561);
			device.setCharPositionInLine(deviceStartCharPos1561);

			match("DBT"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:509:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:509:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:509:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:509:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:509:10: depthInFeet= NUMBER SEP LETTERS SEP
					{
					int depthInFeetStart1576 = getCharIndex();
					int depthInFeetStartLine1576 = getLine();
					int depthInFeetStartCharPos1576 = getCharPositionInLine();
					mNUMBER(); 
					depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1576, getCharIndex()-1);
					depthInFeet.setLine(depthInFeetStartLine1576);
					depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1576);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:510:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:510:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:510:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:510:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:510:10: depthInMeters= NUMBER SEP LETTERS SEP
					{
					int depthInMetersStart1595 = getCharIndex();
					int depthInMetersStartLine1595 = getLine();
					int depthInMetersStartCharPos1595 = getCharPositionInLine();
					mNUMBER(); 
					depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1595, getCharIndex()-1);
					depthInMeters.setLine(depthInMetersStartLine1595);
					depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1595);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:511:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:511:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:511:9: (depthInFathoms= NUMBER SEP LETTERS )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:511:9: (depthInFathoms= NUMBER SEP LETTERS )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:511:10: depthInFathoms= NUMBER SEP LETTERS
					{
					int depthInFathomsStart1614 = getCharIndex();
					int depthInFathomsStartLine1614 = getLine();
					int depthInFathomsStartCharPos1614 = getCharPositionInLine();
					mNUMBER(); 
					depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1614, getCharIndex()-1);
					depthInFathoms.setLine(depthInFathomsStartLine1614);
					depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1614);

					mSEP(); 

					mLETTERS(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:512:11: (checksum= CHECKSUM )*
			loop58:
			while (true) {
				int alt58=2;
				int LA58_0 = input.LA(1);
				if ( (LA58_0=='*') ) {
					alt58=1;
				}

				switch (alt58) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:512:11: checksum= CHECKSUM
					{
					int checksumStart1626 = getCharIndex();
					int checksumStartLine1626 = getLine();
					int checksumStartCharPos1626 = getCharPositionInLine();
					mCHECKSUM(); 
					checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1626, getCharIndex()-1);
					checksum.setLine(checksumStartLine1626);
					checksum.setCharPositionInLine(checksumStartCharPos1626);

					}
					break;

				default :
					break loop58;
				}
			}


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:524:5: ( '$' device= DEVICE 'DBK' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:524:6: '$' device= DEVICE 'DBK' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
			{
			match('$'); 
			int deviceStart1644 = getCharIndex();
			int deviceStartLine1644 = getLine();
			int deviceStartCharPos1644 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1644, getCharIndex()-1);
			device.setLine(deviceStartLine1644);
			device.setCharPositionInLine(deviceStartCharPos1644);

			match("DBK"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:525:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:525:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:525:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:525:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:525:10: depthInFeet= NUMBER SEP LETTERS SEP
					{
					int depthInFeetStart1659 = getCharIndex();
					int depthInFeetStartLine1659 = getLine();
					int depthInFeetStartCharPos1659 = getCharPositionInLine();
					mNUMBER(); 
					depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1659, getCharIndex()-1);
					depthInFeet.setLine(depthInFeetStartLine1659);
					depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1659);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:526:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:526:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:526:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:526:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:526:10: depthInMeters= NUMBER SEP LETTERS SEP
					{
					int depthInMetersStart1678 = getCharIndex();
					int depthInMetersStartLine1678 = getLine();
					int depthInMetersStartCharPos1678 = getCharPositionInLine();
					mNUMBER(); 
					depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1678, getCharIndex()-1);
					depthInMeters.setLine(depthInMetersStartLine1678);
					depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1678);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:527:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:527:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:527:9: (depthInFathoms= NUMBER SEP LETTERS )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:527:9: (depthInFathoms= NUMBER SEP LETTERS )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:527:10: depthInFathoms= NUMBER SEP LETTERS
					{
					int depthInFathomsStart1697 = getCharIndex();
					int depthInFathomsStartLine1697 = getLine();
					int depthInFathomsStartCharPos1697 = getCharPositionInLine();
					mNUMBER(); 
					depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1697, getCharIndex()-1);
					depthInFathoms.setLine(depthInFathomsStartLine1697);
					depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1697);

					mSEP(); 

					mLETTERS(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:528:11: (checksum= CHECKSUM )*
			loop62:
			while (true) {
				int alt62=2;
				int LA62_0 = input.LA(1);
				if ( (LA62_0=='*') ) {
					alt62=1;
				}

				switch (alt62) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:528:11: checksum= CHECKSUM
					{
					int checksumStart1709 = getCharIndex();
					int checksumStartLine1709 = getLine();
					int checksumStartCharPos1709 = getCharPositionInLine();
					mCHECKSUM(); 
					checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1709, getCharIndex()-1);
					checksum.setLine(checksumStartLine1709);
					checksum.setCharPositionInLine(checksumStartCharPos1709);

					}
					break;

				default :
					break loop62;
				}
			}


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:540:5: ( '$' device= DEVICE 'DBS' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:540:6: '$' device= DEVICE 'DBS' SEP ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) ) ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) ) (checksum= CHECKSUM )*
			{
			match('$'); 
			int deviceStart1727 = getCharIndex();
			int deviceStartLine1727 = getLine();
			int deviceStartCharPos1727 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1727, getCharIndex()-1);
			device.setLine(deviceStartLine1727);
			device.setCharPositionInLine(deviceStartCharPos1727);

			match("DBS"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:541:2: ( SEP | (depthInFeet= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:541:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:541:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:541:9: (depthInFeet= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:541:10: depthInFeet= NUMBER SEP LETTERS SEP
					{
					int depthInFeetStart1742 = getCharIndex();
					int depthInFeetStartLine1742 = getLine();
					int depthInFeetStartCharPos1742 = getCharPositionInLine();
					mNUMBER(); 
					depthInFeet = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFeetStart1742, getCharIndex()-1);
					depthInFeet.setLine(depthInFeetStartLine1742);
					depthInFeet.setCharPositionInLine(depthInFeetStartCharPos1742);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:542:2: ( SEP | (depthInMeters= NUMBER SEP LETTERS SEP ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:542:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:542:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:542:9: (depthInMeters= NUMBER SEP LETTERS SEP )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:542:10: depthInMeters= NUMBER SEP LETTERS SEP
					{
					int depthInMetersStart1761 = getCharIndex();
					int depthInMetersStartLine1761 = getLine();
					int depthInMetersStartCharPos1761 = getCharPositionInLine();
					mNUMBER(); 
					depthInMeters = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInMetersStart1761, getCharIndex()-1);
					depthInMeters.setLine(depthInMetersStartLine1761);
					depthInMeters.setCharPositionInLine(depthInMetersStartCharPos1761);

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:543:2: ( SEP | (depthInFathoms= NUMBER SEP LETTERS ) )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:543:3: SEP
					{
					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:543:9: (depthInFathoms= NUMBER SEP LETTERS )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:543:9: (depthInFathoms= NUMBER SEP LETTERS )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:543:10: depthInFathoms= NUMBER SEP LETTERS
					{
					int depthInFathomsStart1780 = getCharIndex();
					int depthInFathomsStartLine1780 = getLine();
					int depthInFathomsStartCharPos1780 = getCharPositionInLine();
					mNUMBER(); 
					depthInFathoms = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthInFathomsStart1780, getCharIndex()-1);
					depthInFathoms.setLine(depthInFathomsStartLine1780);
					depthInFathoms.setCharPositionInLine(depthInFathomsStartCharPos1780);

					mSEP(); 

					mLETTERS(); 

					}

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:544:11: (checksum= CHECKSUM )*
			loop66:
			while (true) {
				int alt66=2;
				int LA66_0 = input.LA(1);
				if ( (LA66_0=='*') ) {
					alt66=1;
				}

				switch (alt66) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:544:11: checksum= CHECKSUM
					{
					int checksumStart1792 = getCharIndex();
					int checksumStartLine1792 = getLine();
					int checksumStartCharPos1792 = getCharPositionInLine();
					mCHECKSUM(); 
					checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1792, getCharIndex()-1);
					checksum.setLine(checksumStartLine1792);
					checksum.setCharPositionInLine(checksumStartCharPos1792);

					}
					break;

				default :
					break loop66;
				}
			}


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:556:5: ( '$' device= DEVICE 'DPT' SEP depth= NUMBER SEP (offset= NUMBER SEP |offset= NUMBER ) (checksum= CHECKSUM )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:556:6: '$' device= DEVICE 'DPT' SEP depth= NUMBER SEP (offset= NUMBER SEP |offset= NUMBER ) (checksum= CHECKSUM )*
			{
			match('$'); 
			int deviceStart1811 = getCharIndex();
			int deviceStartLine1811 = getLine();
			int deviceStartCharPos1811 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1811, getCharIndex()-1);
			device.setLine(deviceStartLine1811);
			device.setCharPositionInLine(deviceStartCharPos1811);

			match("DPT"); 

			mSEP(); 

			int depthStart1820 = getCharIndex();
			int depthStartLine1820 = getLine();
			int depthStartCharPos1820 = getCharPositionInLine();
			mNUMBER(); 
			depth = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, depthStart1820, getCharIndex()-1);
			depth.setLine(depthStartLine1820);
			depth.setCharPositionInLine(depthStartCharPos1820);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:558:9: (offset= NUMBER SEP |offset= NUMBER )
			int alt67=2;
			alt67 = dfa67.predict(input);
			switch (alt67) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:558:10: offset= NUMBER SEP
					{
					int offsetStart1835 = getCharIndex();
					int offsetStartLine1835 = getLine();
					int offsetStartCharPos1835 = getCharPositionInLine();
					mNUMBER(); 
					offset = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, offsetStart1835, getCharIndex()-1);
					offset.setLine(offsetStartLine1835);
					offset.setCharPositionInLine(offsetStartCharPos1835);

					mSEP(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:558:30: offset= NUMBER
					{
					int offsetStart1843 = getCharIndex();
					int offsetStartLine1843 = getLine();
					int offsetStartCharPos1843 = getCharPositionInLine();
					mNUMBER(); 
					offset = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, offsetStart1843, getCharIndex()-1);
					offset.setLine(offsetStartLine1843);
					offset.setCharPositionInLine(offsetStartCharPos1843);

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:559:18: (checksum= CHECKSUM )*
			loop68:
			while (true) {
				int alt68=2;
				int LA68_0 = input.LA(1);
				if ( (LA68_0=='*') ) {
					alt68=1;
				}

				switch (alt68) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:559:18: checksum= CHECKSUM
					{
					int checksumStart1858 = getCharIndex();
					int checksumStartLine1858 = getLine();
					int checksumStartCharPos1858 = getCharPositionInLine();
					mCHECKSUM(); 
					checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart1858, getCharIndex()-1);
					checksum.setLine(checksumStartLine1858);
					checksum.setCharPositionInLine(checksumStartCharPos1858);

					}
					break;

				default :
					break loop68;
				}
			}


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:589:5: ( '$' device= DEVICE 'GGA' SEP (utc= NUMBER )* SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( ' ' )* (gpsQualityIndicator= NUMBER )* SEP (numberOfSatellitesInView= NUMBER )* SEP (horizontalDilutionOfPrecision= NUMBER )* SEP ( SIGN )* (antennaAltitude= NUMBER )* SEP unitsOfAntennaAltitude= LETTERS SEP ( SIGN )* (geoidAltitude= NUMBER )* SEP (unitsOfGeoidAltitude= LETTERS )* ( SEP )+ ( NUMBER SEP )* ( LETTERS | NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:589:12: '$' device= DEVICE 'GGA' SEP (utc= NUMBER )* SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( ' ' )* (gpsQualityIndicator= NUMBER )* SEP (numberOfSatellitesInView= NUMBER )* SEP (horizontalDilutionOfPrecision= NUMBER )* SEP ( SIGN )* (antennaAltitude= NUMBER )* SEP unitsOfAntennaAltitude= LETTERS SEP ( SIGN )* (geoidAltitude= NUMBER )* SEP (unitsOfGeoidAltitude= LETTERS )* ( SEP )+ ( NUMBER SEP )* ( LETTERS | NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart1883 = getCharIndex();
			int deviceStartLine1883 = getLine();
			int deviceStartCharPos1883 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart1883, getCharIndex()-1);
			device.setLine(deviceStartLine1883);
			device.setCharPositionInLine(deviceStartCharPos1883);

			match("GGA"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:590:17: (utc= NUMBER )*
			loop69:
			while (true) {
				int alt69=2;
				int LA69_0 = input.LA(1);
				if ( (LA69_0=='.'||(LA69_0 >= '0' && LA69_0 <= '9')) ) {
					alt69=1;
				}

				switch (alt69) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:590:17: utc= NUMBER
					{
					int utcStart1904 = getCharIndex();
					int utcStartLine1904 = getLine();
					int utcStartCharPos1904 = getCharPositionInLine();
					mNUMBER(); 
					utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart1904, getCharIndex()-1);
					utc.setLine(utcStartLine1904);
					utc.setCharPositionInLine(utcStartCharPos1904);

					}
					break;

				default :
					break loop69;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:591:22: (latitude= NUMBER )*
			loop70:
			while (true) {
				int alt70=2;
				int LA70_0 = input.LA(1);
				if ( (LA70_0=='.'||(LA70_0 >= '0' && LA70_0 <= '9')) ) {
					alt70=1;
				}

				switch (alt70) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:591:22: latitude= NUMBER
					{
					int latitudeStart1924 = getCharIndex();
					int latitudeStartLine1924 = getLine();
					int latitudeStartCharPos1924 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart1924, getCharIndex()-1);
					latitude.setLine(latitudeStartLine1924);
					latitude.setCharPositionInLine(latitudeStartCharPos1924);

					}
					break;

				default :
					break loop70;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:592:16: (ns= LETTERS )*
			loop71:
			while (true) {
				int alt71=2;
				int LA71_0 = input.LA(1);
				if ( (LA71_0==' '||(LA71_0 >= 'A' && LA71_0 <= 'Z')||(LA71_0 >= 'a' && LA71_0 <= 'z')) ) {
					alt71=1;
				}

				switch (alt71) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:592:16: ns= LETTERS
					{
					int nsStart1944 = getCharIndex();
					int nsStartLine1944 = getLine();
					int nsStartCharPos1944 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart1944, getCharIndex()-1);
					ns.setLine(nsStartLine1944);
					ns.setCharPositionInLine(nsStartCharPos1944);

					}
					break;

				default :
					break loop71;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:593:23: (longitude= NUMBER )*
			loop72:
			while (true) {
				int alt72=2;
				int LA72_0 = input.LA(1);
				if ( (LA72_0=='.'||(LA72_0 >= '0' && LA72_0 <= '9')) ) {
					alt72=1;
				}

				switch (alt72) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:593:23: longitude= NUMBER
					{
					int longitudeStart1964 = getCharIndex();
					int longitudeStartLine1964 = getLine();
					int longitudeStartCharPos1964 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart1964, getCharIndex()-1);
					longitude.setLine(longitudeStartLine1964);
					longitude.setCharPositionInLine(longitudeStartCharPos1964);

					}
					break;

				default :
					break loop72;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:594:16: (ew= LETTERS )*
			loop73:
			while (true) {
				int alt73=2;
				int LA73_0 = input.LA(1);
				if ( (LA73_0==' '||(LA73_0 >= 'A' && LA73_0 <= 'Z')||(LA73_0 >= 'a' && LA73_0 <= 'z')) ) {
					alt73=1;
				}

				switch (alt73) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:594:16: ew= LETTERS
					{
					int ewStart1984 = getCharIndex();
					int ewStartLine1984 = getLine();
					int ewStartCharPos1984 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart1984, getCharIndex()-1);
					ew.setLine(ewStartLine1984);
					ew.setCharPositionInLine(ewStartCharPos1984);

					}
					break;

				default :
					break loop73;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:596:14: ( ' ' )*
			loop74:
			while (true) {
				int alt74=2;
				int LA74_0 = input.LA(1);
				if ( (LA74_0==' ') ) {
					alt74=1;
				}

				switch (alt74) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:596:14: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop74;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:597:33: (gpsQualityIndicator= NUMBER )*
			loop75:
			while (true) {
				int alt75=2;
				int LA75_0 = input.LA(1);
				if ( (LA75_0=='.'||(LA75_0 >= '0' && LA75_0 <= '9')) ) {
					alt75=1;
				}

				switch (alt75) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:597:33: gpsQualityIndicator= NUMBER
					{
					int gpsQualityIndicatorStart2034 = getCharIndex();
					int gpsQualityIndicatorStartLine2034 = getLine();
					int gpsQualityIndicatorStartCharPos2034 = getCharPositionInLine();
					mNUMBER(); 
					gpsQualityIndicator = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, gpsQualityIndicatorStart2034, getCharIndex()-1);
					gpsQualityIndicator.setLine(gpsQualityIndicatorStartLine2034);
					gpsQualityIndicator.setCharPositionInLine(gpsQualityIndicatorStartCharPos2034);

					}
					break;

				default :
					break loop75;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:598:38: (numberOfSatellitesInView= NUMBER )*
			loop76:
			while (true) {
				int alt76=2;
				int LA76_0 = input.LA(1);
				if ( (LA76_0=='.'||(LA76_0 >= '0' && LA76_0 <= '9')) ) {
					alt76=1;
				}

				switch (alt76) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:598:38: numberOfSatellitesInView= NUMBER
					{
					int numberOfSatellitesInViewStart2054 = getCharIndex();
					int numberOfSatellitesInViewStartLine2054 = getLine();
					int numberOfSatellitesInViewStartCharPos2054 = getCharPositionInLine();
					mNUMBER(); 
					numberOfSatellitesInView = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, numberOfSatellitesInViewStart2054, getCharIndex()-1);
					numberOfSatellitesInView.setLine(numberOfSatellitesInViewStartLine2054);
					numberOfSatellitesInView.setCharPositionInLine(numberOfSatellitesInViewStartCharPos2054);

					}
					break;

				default :
					break loop76;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:599:43: (horizontalDilutionOfPrecision= NUMBER )*
			loop77:
			while (true) {
				int alt77=2;
				int LA77_0 = input.LA(1);
				if ( (LA77_0=='.'||(LA77_0 >= '0' && LA77_0 <= '9')) ) {
					alt77=1;
				}

				switch (alt77) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:599:43: horizontalDilutionOfPrecision= NUMBER
					{
					int horizontalDilutionOfPrecisionStart2074 = getCharIndex();
					int horizontalDilutionOfPrecisionStartLine2074 = getLine();
					int horizontalDilutionOfPrecisionStartCharPos2074 = getCharPositionInLine();
					mNUMBER(); 
					horizontalDilutionOfPrecision = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, horizontalDilutionOfPrecisionStart2074, getCharIndex()-1);
					horizontalDilutionOfPrecision.setLine(horizontalDilutionOfPrecisionStartLine2074);
					horizontalDilutionOfPrecision.setCharPositionInLine(horizontalDilutionOfPrecisionStartCharPos2074);

					}
					break;

				default :
					break loop77;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:601:14: ( SIGN )*
			loop78:
			while (true) {
				int alt78=2;
				int LA78_0 = input.LA(1);
				if ( (LA78_0=='+'||LA78_0=='-') ) {
					alt78=1;
				}

				switch (alt78) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:602:14: (antennaAltitude= NUMBER )*
			loop79:
			while (true) {
				int alt79=2;
				int LA79_0 = input.LA(1);
				if ( (LA79_0=='.'||(LA79_0 >= '0' && LA79_0 <= '9')) ) {
					alt79=1;
				}

				switch (alt79) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:602:15: antennaAltitude= NUMBER
					{
					int antennaAltitudeStart2125 = getCharIndex();
					int antennaAltitudeStartLine2125 = getLine();
					int antennaAltitudeStartCharPos2125 = getCharPositionInLine();
					mNUMBER(); 
					antennaAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, antennaAltitudeStart2125, getCharIndex()-1);
					antennaAltitude.setLine(antennaAltitudeStartLine2125);
					antennaAltitude.setCharPositionInLine(antennaAltitudeStartCharPos2125);

					}
					break;

				default :
					break loop79;
				}
			}

			mSEP(); 

			int unitsOfAntennaAltitudeStart2146 = getCharIndex();
			int unitsOfAntennaAltitudeStartLine2146 = getLine();
			int unitsOfAntennaAltitudeStartCharPos2146 = getCharPositionInLine();
			mLETTERS(); 
			unitsOfAntennaAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfAntennaAltitudeStart2146, getCharIndex()-1);
			unitsOfAntennaAltitude.setLine(unitsOfAntennaAltitudeStartLine2146);
			unitsOfAntennaAltitude.setCharPositionInLine(unitsOfAntennaAltitudeStartCharPos2146);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:604:14: ( SIGN )*
			loop80:
			while (true) {
				int alt80=2;
				int LA80_0 = input.LA(1);
				if ( (LA80_0=='+'||LA80_0=='-') ) {
					alt80=1;
				}

				switch (alt80) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:605:14: (geoidAltitude= NUMBER )*
			loop81:
			while (true) {
				int alt81=2;
				int LA81_0 = input.LA(1);
				if ( (LA81_0=='.'||(LA81_0 >= '0' && LA81_0 <= '9')) ) {
					alt81=1;
				}

				switch (alt81) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:605:15: geoidAltitude= NUMBER
					{
					int geoidAltitudeStart2183 = getCharIndex();
					int geoidAltitudeStartLine2183 = getLine();
					int geoidAltitudeStartCharPos2183 = getCharPositionInLine();
					mNUMBER(); 
					geoidAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, geoidAltitudeStart2183, getCharIndex()-1);
					geoidAltitude.setLine(geoidAltitudeStartLine2183);
					geoidAltitude.setCharPositionInLine(geoidAltitudeStartCharPos2183);

					}
					break;

				default :
					break loop81;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:606:14: (unitsOfGeoidAltitude= LETTERS )*
			loop82:
			while (true) {
				int alt82=2;
				int LA82_0 = input.LA(1);
				if ( (LA82_0==' '||(LA82_0 >= 'A' && LA82_0 <= 'Z')||(LA82_0 >= 'a' && LA82_0 <= 'z')) ) {
					alt82=1;
				}

				switch (alt82) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:606:15: unitsOfGeoidAltitude= LETTERS
					{
					int unitsOfGeoidAltitudeStart2206 = getCharIndex();
					int unitsOfGeoidAltitudeStartLine2206 = getLine();
					int unitsOfGeoidAltitudeStartCharPos2206 = getCharPositionInLine();
					mLETTERS(); 
					unitsOfGeoidAltitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitsOfGeoidAltitudeStart2206, getCharIndex()-1);
					unitsOfGeoidAltitude.setLine(unitsOfGeoidAltitudeStartLine2206);
					unitsOfGeoidAltitude.setCharPositionInLine(unitsOfGeoidAltitudeStartCharPos2206);

					}
					break;

				default :
					break loop82;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:606:46: ( SEP )+
			int cnt83=0;
			loop83:
			while (true) {
				int alt83=2;
				int LA83_0 = input.LA(1);
				if ( (LA83_0==',') ) {
					alt83=1;
				}

				switch (alt83) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(83, input);
					throw eee;
				}
				cnt83++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:608:14: ( NUMBER SEP )*
			loop84:
			while (true) {
				int alt84=2;
				alt84 = dfa84.predict(input);
				switch (alt84) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:608:16: NUMBER SEP
					{
					mNUMBER(); 

					mSEP(); 

					}
					break;

				default :
					break loop84;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:609:14: ( LETTERS | NUMBER )*
			loop85:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:609:16: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:609:26: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop85;
				}
			}

			int checksumStart2299 = getCharIndex();
			int checksumStartLine2299 = getLine();
			int checksumStartCharPos2299 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2299, getCharIndex()-1);
			checksum.setLine(checksumStartLine2299);
			checksum.setCharPositionInLine(checksumStartCharPos2299);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:647:5: ( '$' device= DEVICE 'GLL' SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP (utc= NUMBER )* SEP status= LETTERS ( SEP )* ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:647:7: '$' device= DEVICE 'GLL' SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP (utc= NUMBER )* SEP status= LETTERS ( SEP )* ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart2332 = getCharIndex();
			int deviceStartLine2332 = getLine();
			int deviceStartCharPos2332 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart2332, getCharIndex()-1);
			device.setLine(deviceStartLine2332);
			device.setCharPositionInLine(deviceStartCharPos2332);

			match("GLL"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:648:12: (latitude= NUMBER )*
			loop86:
			while (true) {
				int alt86=2;
				int LA86_0 = input.LA(1);
				if ( (LA86_0=='.'||(LA86_0 >= '0' && LA86_0 <= '9')) ) {
					alt86=1;
				}

				switch (alt86) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:648:12: latitude= NUMBER
					{
					int latitudeStart2343 = getCharIndex();
					int latitudeStartLine2343 = getLine();
					int latitudeStartCharPos2343 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart2343, getCharIndex()-1);
					latitude.setLine(latitudeStartLine2343);
					latitude.setCharPositionInLine(latitudeStartCharPos2343);

					}
					break;

				default :
					break loop86;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:649:17: (ns= LETTERS )*
			loop87:
			while (true) {
				int alt87=2;
				int LA87_0 = input.LA(1);
				if ( (LA87_0==' '||(LA87_0 >= 'A' && LA87_0 <= 'Z')||(LA87_0 >= 'a' && LA87_0 <= 'z')) ) {
					alt87=1;
				}

				switch (alt87) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:649:17: ns= LETTERS
					{
					int nsStart2364 = getCharIndex();
					int nsStartLine2364 = getLine();
					int nsStartCharPos2364 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart2364, getCharIndex()-1);
					ns.setLine(nsStartLine2364);
					ns.setCharPositionInLine(nsStartCharPos2364);

					}
					break;

				default :
					break loop87;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:650:24: (longitude= NUMBER )*
			loop88:
			while (true) {
				int alt88=2;
				int LA88_0 = input.LA(1);
				if ( (LA88_0=='.'||(LA88_0 >= '0' && LA88_0 <= '9')) ) {
					alt88=1;
				}

				switch (alt88) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:650:24: longitude= NUMBER
					{
					int longitudeStart2385 = getCharIndex();
					int longitudeStartLine2385 = getLine();
					int longitudeStartCharPos2385 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart2385, getCharIndex()-1);
					longitude.setLine(longitudeStartLine2385);
					longitude.setCharPositionInLine(longitudeStartCharPos2385);

					}
					break;

				default :
					break loop88;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:651:17: (ew= LETTERS )*
			loop89:
			while (true) {
				int alt89=2;
				int LA89_0 = input.LA(1);
				if ( (LA89_0==' '||(LA89_0 >= 'A' && LA89_0 <= 'Z')||(LA89_0 >= 'a' && LA89_0 <= 'z')) ) {
					alt89=1;
				}

				switch (alt89) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:651:17: ew= LETTERS
					{
					int ewStart2406 = getCharIndex();
					int ewStartLine2406 = getLine();
					int ewStartCharPos2406 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart2406, getCharIndex()-1);
					ew.setLine(ewStartLine2406);
					ew.setCharPositionInLine(ewStartCharPos2406);

					}
					break;

				default :
					break loop89;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:652:18: (utc= NUMBER )*
			loop90:
			while (true) {
				int alt90=2;
				int LA90_0 = input.LA(1);
				if ( (LA90_0=='.'||(LA90_0 >= '0' && LA90_0 <= '9')) ) {
					alt90=1;
				}

				switch (alt90) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:652:18: utc= NUMBER
					{
					int utcStart2427 = getCharIndex();
					int utcStartLine2427 = getLine();
					int utcStartCharPos2427 = getCharPositionInLine();
					mNUMBER(); 
					utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart2427, getCharIndex()-1);
					utc.setLine(utcStartLine2427);
					utc.setCharPositionInLine(utcStartCharPos2427);

					}
					break;

				default :
					break loop90;
				}
			}

			mSEP(); 

			int statusStart2448 = getCharIndex();
			int statusStartLine2448 = getLine();
			int statusStartCharPos2448 = getCharPositionInLine();
			mLETTERS(); 
			status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart2448, getCharIndex()-1);
			status.setLine(statusStartLine2448);
			status.setCharPositionInLine(statusStartCharPos2448);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:653:30: ( SEP )*
			loop91:
			while (true) {
				int alt91=2;
				int LA91_0 = input.LA(1);
				if ( (LA91_0==',') ) {
					alt91=1;
				}

				switch (alt91) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:654:15: ( LETTERS )*
			loop92:
			while (true) {
				int alt92=2;
				int LA92_0 = input.LA(1);
				if ( (LA92_0==' '||(LA92_0 >= 'A' && LA92_0 <= 'Z')||(LA92_0 >= 'a' && LA92_0 <= 'z')) ) {
					alt92=1;
				}

				switch (alt92) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:654:15: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop92;
				}
			}

			int checksumStart2487 = getCharIndex();
			int checksumStartLine2487 = getLine();
			int checksumStartCharPos2487 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2487, getCharIndex()-1);
			checksum.setLine(checksumStartLine2487);
			checksum.setCharPositionInLine(checksumStartCharPos2487);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:674:6: ( '$' device= DEVICE 'GSA' SEP autoOrManualSelection= LETTERS SEP ( ' ' )* dimensionFix= NUMBER SEP ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )* SEP ( ( ' ' )* PDOP= NUMBER )* SEP ( ( ' ' )* HDOP= NUMBER )* SEP ( ( ' ' )* VDOP= NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:674:11: '$' device= DEVICE 'GSA' SEP autoOrManualSelection= LETTERS SEP ( ' ' )* dimensionFix= NUMBER SEP ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )* SEP ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )* SEP ( ( ' ' )* PDOP= NUMBER )* SEP ( ( ' ' )* HDOP= NUMBER )* SEP ( ( ' ' )* VDOP= NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart2522 = getCharIndex();
			int deviceStartLine2522 = getLine();
			int deviceStartCharPos2522 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart2522, getCharIndex()-1);
			device.setLine(deviceStartLine2522);
			device.setCharPositionInLine(deviceStartCharPos2522);

			match("GSA"); 

			mSEP(); 

			int autoOrManualSelectionStart2545 = getCharIndex();
			int autoOrManualSelectionStartLine2545 = getLine();
			int autoOrManualSelectionStartCharPos2545 = getCharPositionInLine();
			mLETTERS(); 
			autoOrManualSelection = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, autoOrManualSelectionStart2545, getCharIndex()-1);
			autoOrManualSelection.setLine(autoOrManualSelectionStartLine2545);
			autoOrManualSelection.setCharPositionInLine(autoOrManualSelectionStartCharPos2545);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:676:14: ( ' ' )*
			loop93:
			while (true) {
				int alt93=2;
				int LA93_0 = input.LA(1);
				if ( (LA93_0==' ') ) {
					alt93=1;
				}

				switch (alt93) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:676:14: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop93;
				}
			}

			int dimensionFixStart2569 = getCharIndex();
			int dimensionFixStartLine2569 = getLine();
			int dimensionFixStartCharPos2569 = getCharPositionInLine();
			mNUMBER(); 
			dimensionFix = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dimensionFixStart2569, getCharIndex()-1);
			dimensionFix.setLine(dimensionFixStartLine2569);
			dimensionFix.setCharPositionInLine(dimensionFixStartCharPos2569);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:677:14: ( ( ' ' )* PRNOfSatellitesUsed1= NUMBER )*
			loop95:
			while (true) {
				int alt95=2;
				int LA95_0 = input.LA(1);
				if ( (LA95_0==' '||LA95_0=='.'||(LA95_0 >= '0' && LA95_0 <= '9')) ) {
					alt95=1;
				}

				switch (alt95) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:677:15: ( ' ' )* PRNOfSatellitesUsed1= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:677:15: ( ' ' )*
					loop94:
					while (true) {
						int alt94=2;
						int LA94_0 = input.LA(1);
						if ( (LA94_0==' ') ) {
							alt94=1;
						}

						switch (alt94) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:677:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop94;
						}
					}

					int PRNOfSatellitesUsed1Start2594 = getCharIndex();
					int PRNOfSatellitesUsed1StartLine2594 = getLine();
					int PRNOfSatellitesUsed1StartCharPos2594 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed1Start2594, getCharIndex()-1);
					PRNOfSatellitesUsed1.setLine(PRNOfSatellitesUsed1StartLine2594);
					PRNOfSatellitesUsed1.setCharPositionInLine(PRNOfSatellitesUsed1StartCharPos2594);

					}
					break;

				default :
					break loop95;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:678:14: ( ( ' ' )* PRNOfSatellitesUsed2= NUMBER )*
			loop97:
			while (true) {
				int alt97=2;
				int LA97_0 = input.LA(1);
				if ( (LA97_0==' '||LA97_0=='.'||(LA97_0 >= '0' && LA97_0 <= '9')) ) {
					alt97=1;
				}

				switch (alt97) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:678:15: ( ' ' )* PRNOfSatellitesUsed2= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:678:15: ( ' ' )*
					loop96:
					while (true) {
						int alt96=2;
						int LA96_0 = input.LA(1);
						if ( (LA96_0==' ') ) {
							alt96=1;
						}

						switch (alt96) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:678:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop96;
						}
					}

					int PRNOfSatellitesUsed2Start2621 = getCharIndex();
					int PRNOfSatellitesUsed2StartLine2621 = getLine();
					int PRNOfSatellitesUsed2StartCharPos2621 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed2Start2621, getCharIndex()-1);
					PRNOfSatellitesUsed2.setLine(PRNOfSatellitesUsed2StartLine2621);
					PRNOfSatellitesUsed2.setCharPositionInLine(PRNOfSatellitesUsed2StartCharPos2621);

					}
					break;

				default :
					break loop97;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:679:14: ( ( ' ' )* PRNOfSatellitesUsed3= NUMBER )*
			loop99:
			while (true) {
				int alt99=2;
				int LA99_0 = input.LA(1);
				if ( (LA99_0==' '||LA99_0=='.'||(LA99_0 >= '0' && LA99_0 <= '9')) ) {
					alt99=1;
				}

				switch (alt99) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:679:15: ( ' ' )* PRNOfSatellitesUsed3= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:679:15: ( ' ' )*
					loop98:
					while (true) {
						int alt98=2;
						int LA98_0 = input.LA(1);
						if ( (LA98_0==' ') ) {
							alt98=1;
						}

						switch (alt98) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:679:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop98;
						}
					}

					int PRNOfSatellitesUsed3Start2648 = getCharIndex();
					int PRNOfSatellitesUsed3StartLine2648 = getLine();
					int PRNOfSatellitesUsed3StartCharPos2648 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed3Start2648, getCharIndex()-1);
					PRNOfSatellitesUsed3.setLine(PRNOfSatellitesUsed3StartLine2648);
					PRNOfSatellitesUsed3.setCharPositionInLine(PRNOfSatellitesUsed3StartCharPos2648);

					}
					break;

				default :
					break loop99;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:680:14: ( ( ' ' )* PRNOfSatellitesUsed4= NUMBER )*
			loop101:
			while (true) {
				int alt101=2;
				int LA101_0 = input.LA(1);
				if ( (LA101_0==' '||LA101_0=='.'||(LA101_0 >= '0' && LA101_0 <= '9')) ) {
					alt101=1;
				}

				switch (alt101) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:680:15: ( ' ' )* PRNOfSatellitesUsed4= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:680:15: ( ' ' )*
					loop100:
					while (true) {
						int alt100=2;
						int LA100_0 = input.LA(1);
						if ( (LA100_0==' ') ) {
							alt100=1;
						}

						switch (alt100) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:680:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop100;
						}
					}

					int PRNOfSatellitesUsed4Start2675 = getCharIndex();
					int PRNOfSatellitesUsed4StartLine2675 = getLine();
					int PRNOfSatellitesUsed4StartCharPos2675 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed4 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed4Start2675, getCharIndex()-1);
					PRNOfSatellitesUsed4.setLine(PRNOfSatellitesUsed4StartLine2675);
					PRNOfSatellitesUsed4.setCharPositionInLine(PRNOfSatellitesUsed4StartCharPos2675);

					}
					break;

				default :
					break loop101;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:681:14: ( ( ' ' )* PRNOfSatellitesUsed5= NUMBER )*
			loop103:
			while (true) {
				int alt103=2;
				int LA103_0 = input.LA(1);
				if ( (LA103_0==' '||LA103_0=='.'||(LA103_0 >= '0' && LA103_0 <= '9')) ) {
					alt103=1;
				}

				switch (alt103) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:681:15: ( ' ' )* PRNOfSatellitesUsed5= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:681:15: ( ' ' )*
					loop102:
					while (true) {
						int alt102=2;
						int LA102_0 = input.LA(1);
						if ( (LA102_0==' ') ) {
							alt102=1;
						}

						switch (alt102) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:681:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop102;
						}
					}

					int PRNOfSatellitesUsed5Start2702 = getCharIndex();
					int PRNOfSatellitesUsed5StartLine2702 = getLine();
					int PRNOfSatellitesUsed5StartCharPos2702 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed5 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed5Start2702, getCharIndex()-1);
					PRNOfSatellitesUsed5.setLine(PRNOfSatellitesUsed5StartLine2702);
					PRNOfSatellitesUsed5.setCharPositionInLine(PRNOfSatellitesUsed5StartCharPos2702);

					}
					break;

				default :
					break loop103;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:682:14: ( ( ' ' )* PRNOfSatellitesUsed6= NUMBER )*
			loop105:
			while (true) {
				int alt105=2;
				int LA105_0 = input.LA(1);
				if ( (LA105_0==' '||LA105_0=='.'||(LA105_0 >= '0' && LA105_0 <= '9')) ) {
					alt105=1;
				}

				switch (alt105) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:682:15: ( ' ' )* PRNOfSatellitesUsed6= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:682:15: ( ' ' )*
					loop104:
					while (true) {
						int alt104=2;
						int LA104_0 = input.LA(1);
						if ( (LA104_0==' ') ) {
							alt104=1;
						}

						switch (alt104) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:682:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop104;
						}
					}

					int PRNOfSatellitesUsed6Start2729 = getCharIndex();
					int PRNOfSatellitesUsed6StartLine2729 = getLine();
					int PRNOfSatellitesUsed6StartCharPos2729 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed6 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed6Start2729, getCharIndex()-1);
					PRNOfSatellitesUsed6.setLine(PRNOfSatellitesUsed6StartLine2729);
					PRNOfSatellitesUsed6.setCharPositionInLine(PRNOfSatellitesUsed6StartCharPos2729);

					}
					break;

				default :
					break loop105;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:683:14: ( ( ' ' )* PRNOfSatellitesUsed7= NUMBER )*
			loop107:
			while (true) {
				int alt107=2;
				int LA107_0 = input.LA(1);
				if ( (LA107_0==' '||LA107_0=='.'||(LA107_0 >= '0' && LA107_0 <= '9')) ) {
					alt107=1;
				}

				switch (alt107) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:683:15: ( ' ' )* PRNOfSatellitesUsed7= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:683:15: ( ' ' )*
					loop106:
					while (true) {
						int alt106=2;
						int LA106_0 = input.LA(1);
						if ( (LA106_0==' ') ) {
							alt106=1;
						}

						switch (alt106) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:683:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop106;
						}
					}

					int PRNOfSatellitesUsed7Start2756 = getCharIndex();
					int PRNOfSatellitesUsed7StartLine2756 = getLine();
					int PRNOfSatellitesUsed7StartCharPos2756 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed7 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed7Start2756, getCharIndex()-1);
					PRNOfSatellitesUsed7.setLine(PRNOfSatellitesUsed7StartLine2756);
					PRNOfSatellitesUsed7.setCharPositionInLine(PRNOfSatellitesUsed7StartCharPos2756);

					}
					break;

				default :
					break loop107;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:684:14: ( ( ' ' )* PRNOfSatellitesUsed8= NUMBER )*
			loop109:
			while (true) {
				int alt109=2;
				int LA109_0 = input.LA(1);
				if ( (LA109_0==' '||LA109_0=='.'||(LA109_0 >= '0' && LA109_0 <= '9')) ) {
					alt109=1;
				}

				switch (alt109) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:684:15: ( ' ' )* PRNOfSatellitesUsed8= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:684:15: ( ' ' )*
					loop108:
					while (true) {
						int alt108=2;
						int LA108_0 = input.LA(1);
						if ( (LA108_0==' ') ) {
							alt108=1;
						}

						switch (alt108) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:684:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop108;
						}
					}

					int PRNOfSatellitesUsed8Start2783 = getCharIndex();
					int PRNOfSatellitesUsed8StartLine2783 = getLine();
					int PRNOfSatellitesUsed8StartCharPos2783 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed8 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed8Start2783, getCharIndex()-1);
					PRNOfSatellitesUsed8.setLine(PRNOfSatellitesUsed8StartLine2783);
					PRNOfSatellitesUsed8.setCharPositionInLine(PRNOfSatellitesUsed8StartCharPos2783);

					}
					break;

				default :
					break loop109;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:685:14: ( ( ' ' )* PRNOfSatellitesUsed9= NUMBER )*
			loop111:
			while (true) {
				int alt111=2;
				int LA111_0 = input.LA(1);
				if ( (LA111_0==' '||LA111_0=='.'||(LA111_0 >= '0' && LA111_0 <= '9')) ) {
					alt111=1;
				}

				switch (alt111) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:685:15: ( ' ' )* PRNOfSatellitesUsed9= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:685:15: ( ' ' )*
					loop110:
					while (true) {
						int alt110=2;
						int LA110_0 = input.LA(1);
						if ( (LA110_0==' ') ) {
							alt110=1;
						}

						switch (alt110) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:685:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop110;
						}
					}

					int PRNOfSatellitesUsed9Start2810 = getCharIndex();
					int PRNOfSatellitesUsed9StartLine2810 = getLine();
					int PRNOfSatellitesUsed9StartCharPos2810 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed9 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed9Start2810, getCharIndex()-1);
					PRNOfSatellitesUsed9.setLine(PRNOfSatellitesUsed9StartLine2810);
					PRNOfSatellitesUsed9.setCharPositionInLine(PRNOfSatellitesUsed9StartCharPos2810);

					}
					break;

				default :
					break loop111;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:686:14: ( ( ' ' )* PRNOfSatellitesUsed10= NUMBER )*
			loop113:
			while (true) {
				int alt113=2;
				int LA113_0 = input.LA(1);
				if ( (LA113_0==' '||LA113_0=='.'||(LA113_0 >= '0' && LA113_0 <= '9')) ) {
					alt113=1;
				}

				switch (alt113) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:686:15: ( ' ' )* PRNOfSatellitesUsed10= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:686:15: ( ' ' )*
					loop112:
					while (true) {
						int alt112=2;
						int LA112_0 = input.LA(1);
						if ( (LA112_0==' ') ) {
							alt112=1;
						}

						switch (alt112) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:686:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop112;
						}
					}

					int PRNOfSatellitesUsed10Start2837 = getCharIndex();
					int PRNOfSatellitesUsed10StartLine2837 = getLine();
					int PRNOfSatellitesUsed10StartCharPos2837 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed10 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed10Start2837, getCharIndex()-1);
					PRNOfSatellitesUsed10.setLine(PRNOfSatellitesUsed10StartLine2837);
					PRNOfSatellitesUsed10.setCharPositionInLine(PRNOfSatellitesUsed10StartCharPos2837);

					}
					break;

				default :
					break loop113;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:687:14: ( ( ' ' )* PRNOfSatellitesUsed11= NUMBER )*
			loop115:
			while (true) {
				int alt115=2;
				int LA115_0 = input.LA(1);
				if ( (LA115_0==' '||LA115_0=='.'||(LA115_0 >= '0' && LA115_0 <= '9')) ) {
					alt115=1;
				}

				switch (alt115) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:687:15: ( ' ' )* PRNOfSatellitesUsed11= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:687:15: ( ' ' )*
					loop114:
					while (true) {
						int alt114=2;
						int LA114_0 = input.LA(1);
						if ( (LA114_0==' ') ) {
							alt114=1;
						}

						switch (alt114) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:687:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop114;
						}
					}

					int PRNOfSatellitesUsed11Start2864 = getCharIndex();
					int PRNOfSatellitesUsed11StartLine2864 = getLine();
					int PRNOfSatellitesUsed11StartCharPos2864 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed11 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed11Start2864, getCharIndex()-1);
					PRNOfSatellitesUsed11.setLine(PRNOfSatellitesUsed11StartLine2864);
					PRNOfSatellitesUsed11.setCharPositionInLine(PRNOfSatellitesUsed11StartCharPos2864);

					}
					break;

				default :
					break loop115;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:688:14: ( ( ' ' )* PRNOfSatellitesUsed12= NUMBER )*
			loop117:
			while (true) {
				int alt117=2;
				int LA117_0 = input.LA(1);
				if ( (LA117_0==' '||LA117_0=='.'||(LA117_0 >= '0' && LA117_0 <= '9')) ) {
					alt117=1;
				}

				switch (alt117) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:688:15: ( ' ' )* PRNOfSatellitesUsed12= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:688:15: ( ' ' )*
					loop116:
					while (true) {
						int alt116=2;
						int LA116_0 = input.LA(1);
						if ( (LA116_0==' ') ) {
							alt116=1;
						}

						switch (alt116) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:688:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop116;
						}
					}

					int PRNOfSatellitesUsed12Start2891 = getCharIndex();
					int PRNOfSatellitesUsed12StartLine2891 = getLine();
					int PRNOfSatellitesUsed12StartCharPos2891 = getCharPositionInLine();
					mNUMBER(); 
					PRNOfSatellitesUsed12 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PRNOfSatellitesUsed12Start2891, getCharIndex()-1);
					PRNOfSatellitesUsed12.setLine(PRNOfSatellitesUsed12StartLine2891);
					PRNOfSatellitesUsed12.setCharPositionInLine(PRNOfSatellitesUsed12StartCharPos2891);

					}
					break;

				default :
					break loop117;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:689:14: ( ( ' ' )* PDOP= NUMBER )*
			loop119:
			while (true) {
				int alt119=2;
				int LA119_0 = input.LA(1);
				if ( (LA119_0==' '||LA119_0=='.'||(LA119_0 >= '0' && LA119_0 <= '9')) ) {
					alt119=1;
				}

				switch (alt119) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:689:15: ( ' ' )* PDOP= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:689:15: ( ' ' )*
					loop118:
					while (true) {
						int alt118=2;
						int LA118_0 = input.LA(1);
						if ( (LA118_0==' ') ) {
							alt118=1;
						}

						switch (alt118) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:689:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop118;
						}
					}

					int PDOPStart2918 = getCharIndex();
					int PDOPStartLine2918 = getLine();
					int PDOPStartCharPos2918 = getCharPositionInLine();
					mNUMBER(); 
					PDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, PDOPStart2918, getCharIndex()-1);
					PDOP.setLine(PDOPStartLine2918);
					PDOP.setCharPositionInLine(PDOPStartCharPos2918);

					}
					break;

				default :
					break loop119;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:690:14: ( ( ' ' )* HDOP= NUMBER )*
			loop121:
			while (true) {
				int alt121=2;
				int LA121_0 = input.LA(1);
				if ( (LA121_0==' '||LA121_0=='.'||(LA121_0 >= '0' && LA121_0 <= '9')) ) {
					alt121=1;
				}

				switch (alt121) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:690:15: ( ' ' )* HDOP= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:690:15: ( ' ' )*
					loop120:
					while (true) {
						int alt120=2;
						int LA120_0 = input.LA(1);
						if ( (LA120_0==' ') ) {
							alt120=1;
						}

						switch (alt120) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:690:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop120;
						}
					}

					int HDOPStart2945 = getCharIndex();
					int HDOPStartLine2945 = getLine();
					int HDOPStartCharPos2945 = getCharPositionInLine();
					mNUMBER(); 
					HDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, HDOPStart2945, getCharIndex()-1);
					HDOP.setLine(HDOPStartLine2945);
					HDOP.setCharPositionInLine(HDOPStartCharPos2945);

					}
					break;

				default :
					break loop121;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:691:14: ( ( ' ' )* VDOP= NUMBER )*
			loop123:
			while (true) {
				int alt123=2;
				int LA123_0 = input.LA(1);
				if ( (LA123_0==' '||LA123_0=='.'||(LA123_0 >= '0' && LA123_0 <= '9')) ) {
					alt123=1;
				}

				switch (alt123) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:691:15: ( ' ' )* VDOP= NUMBER
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:691:15: ( ' ' )*
					loop122:
					while (true) {
						int alt122=2;
						int LA122_0 = input.LA(1);
						if ( (LA122_0==' ') ) {
							alt122=1;
						}

						switch (alt122) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:691:15: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop122;
						}
					}

					int VDOPStart2972 = getCharIndex();
					int VDOPStartLine2972 = getLine();
					int VDOPStartCharPos2972 = getCharPositionInLine();
					mNUMBER(); 
					VDOP = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, VDOPStart2972, getCharIndex()-1);
					VDOP.setLine(VDOPStartLine2972);
					VDOP.setCharPositionInLine(VDOPStartCharPos2972);

					}
					break;

				default :
					break loop123;
				}
			}

			int checksumStart2991 = getCharIndex();
			int checksumStartLine2991 = getLine();
			int checksumStartCharPos2991 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart2991, getCharIndex()-1);
			checksum.setLine(checksumStartLine2991);
			checksum.setCharPositionInLine(checksumStartCharPos2991);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:787:5: ( '$' device= DEVICE 'GSV' ( NUMBER | SEP )+ checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:787:10: '$' device= DEVICE 'GSV' ( NUMBER | SEP )+ checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3021 = getCharIndex();
			int deviceStartLine3021 = getLine();
			int deviceStartCharPos3021 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3021, getCharIndex()-1);
			device.setLine(deviceStartLine3021);
			device.setCharPositionInLine(deviceStartCharPos3021);

			match("GSV"); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:788:3: ( NUMBER | SEP )+
			int cnt124=0;
			loop124:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:788:4: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:788:13: SEP
					{
					mSEP(); 

					}
					break;

				default :
					if ( cnt124 >= 1 ) break loop124;
					EarlyExitException eee = new EarlyExitException(124, input);
					throw eee;
				}
				cnt124++;
			}

			int checksumStart3042 = getCharIndex();
			int checksumStartLine3042 = getLine();
			int checksumStartCharPos3042 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3042, getCharIndex()-1);
			checksum.setLine(checksumStartLine3042);
			checksum.setCharPositionInLine(checksumStartCharPos3042);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:825:7: ( '$' device= DEVICE 'HDG' SEP heading= NUMBER ( SEP )+ (dev= NUMBER SEP we= LETTERS SEP )* var= NUMBER SEP ew= LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:825:9: '$' device= DEVICE 'HDG' SEP heading= NUMBER ( SEP )+ (dev= NUMBER SEP we= LETTERS SEP )* var= NUMBER SEP ew= LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3082 = getCharIndex();
			int deviceStartLine3082 = getLine();
			int deviceStartCharPos3082 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3082, getCharIndex()-1);
			device.setLine(deviceStartLine3082);
			device.setCharPositionInLine(deviceStartCharPos3082);

			match("HDG"); 

			mSEP(); 

			int headingStart3096 = getCharIndex();
			int headingStartLine3096 = getLine();
			int headingStartCharPos3096 = getCharPositionInLine();
			mNUMBER(); 
			heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3096, getCharIndex()-1);
			heading.setLine(headingStartLine3096);
			heading.setCharPositionInLine(headingStartCharPos3096);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:826:22: ( SEP )+
			int cnt125=0;
			loop125:
			while (true) {
				int alt125=2;
				int LA125_0 = input.LA(1);
				if ( (LA125_0==',') ) {
					alt125=1;
				}

				switch (alt125) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(125, input);
					throw eee;
				}
				cnt125++;
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:827:5: (dev= NUMBER SEP we= LETTERS SEP )*
			loop126:
			while (true) {
				int alt126=2;
				alt126 = dfa126.predict(input);
				switch (alt126) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:827:6: dev= NUMBER SEP we= LETTERS SEP
					{
					int devStart3110 = getCharIndex();
					int devStartLine3110 = getLine();
					int devStartCharPos3110 = getCharPositionInLine();
					mNUMBER(); 
					dev = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, devStart3110, getCharIndex()-1);
					dev.setLine(devStartLine3110);
					dev.setCharPositionInLine(devStartCharPos3110);

					mSEP(); 

					int weStart3118 = getCharIndex();
					int weStartLine3118 = getLine();
					int weStartCharPos3118 = getCharPositionInLine();
					mLETTERS(); 
					we = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, weStart3118, getCharIndex()-1);
					we.setLine(weStartLine3118);
					we.setCharPositionInLine(weStartCharPos3118);

					mSEP(); 

					}
					break;

				default :
					break loop126;
				}
			}

			int varStart3132 = getCharIndex();
			int varStartLine3132 = getLine();
			int varStartCharPos3132 = getCharPositionInLine();
			mNUMBER(); 
			var = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, varStart3132, getCharIndex()-1);
			var.setLine(varStartLine3132);
			var.setCharPositionInLine(varStartCharPos3132);

			mSEP(); 

			int ewStart3144 = getCharIndex();
			int ewStartLine3144 = getLine();
			int ewStartCharPos3144 = getCharPositionInLine();
			mLETTERS(); 
			ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart3144, getCharIndex()-1);
			ew.setLine(ewStartLine3144);
			ew.setCharPositionInLine(ewStartCharPos3144);

			int checksumStart3152 = getCharIndex();
			int checksumStartLine3152 = getLine();
			int checksumStartCharPos3152 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3152, getCharIndex()-1);
			checksum.setLine(checksumStartLine3152);
			checksum.setCharPositionInLine(checksumStartCharPos3152);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:840:8: ( '$' device= DEVICE 'HDM' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:840:11: '$' device= DEVICE 'HDM' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3176 = getCharIndex();
			int deviceStartLine3176 = getLine();
			int deviceStartCharPos3176 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3176, getCharIndex()-1);
			device.setLine(deviceStartLine3176);
			device.setCharPositionInLine(deviceStartCharPos3176);

			match("HDM"); 

			mSEP(); 

			int headingStart3200 = getCharIndex();
			int headingStartLine3200 = getLine();
			int headingStartCharPos3200 = getCharPositionInLine();
			mNUMBER(); 
			heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3200, getCharIndex()-1);
			heading.setLine(headingStartLine3200);
			heading.setCharPositionInLine(headingStartCharPos3200);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:842:15: ( LETTERS )*
			loop127:
			while (true) {
				int alt127=2;
				int LA127_0 = input.LA(1);
				if ( (LA127_0==' '||(LA127_0 >= 'A' && LA127_0 <= 'Z')||(LA127_0 >= 'a' && LA127_0 <= 'z')) ) {
					alt127=1;
				}

				switch (alt127) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:842:17: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop127;
				}
			}

			int checksumStart3237 = getCharIndex();
			int checksumStartLine3237 = getLine();
			int checksumStartCharPos3237 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3237, getCharIndex()-1);
			checksum.setLine(checksumStartLine3237);
			checksum.setCharPositionInLine(checksumStartCharPos3237);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:856:7: ( '$' device= DEVICE 'HDT' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:856:8: '$' device= DEVICE 'HDT' SEP heading= NUMBER SEP ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3258 = getCharIndex();
			int deviceStartLine3258 = getLine();
			int deviceStartCharPos3258 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3258, getCharIndex()-1);
			device.setLine(deviceStartLine3258);
			device.setCharPositionInLine(deviceStartCharPos3258);

			match("HDT"); 

			mSEP(); 

			int headingStart3282 = getCharIndex();
			int headingStartLine3282 = getLine();
			int headingStartCharPos3282 = getCharPositionInLine();
			mNUMBER(); 
			heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart3282, getCharIndex()-1);
			heading.setLine(headingStartLine3282);
			heading.setCharPositionInLine(headingStartCharPos3282);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:858:15: ( LETTERS )*
			loop128:
			while (true) {
				int alt128=2;
				int LA128_0 = input.LA(1);
				if ( (LA128_0==' '||(LA128_0 >= 'A' && LA128_0 <= 'Z')||(LA128_0 >= 'a' && LA128_0 <= 'z')) ) {
					alt128=1;
				}

				switch (alt128) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:858:16: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop128;
				}
			}

			int checksumStart3322 = getCharIndex();
			int checksumStartLine3322 = getLine();
			int checksumStartCharPos3322 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3322, getCharIndex()-1);
			checksum.setLine(checksumStartLine3322);
			checksum.setCharPositionInLine(checksumStartCharPos3322);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:865:6: ( '$' device= DEVICE 'MSK' SEP frequencyToUse= NUMBER SEP frequencyMode= LETTERS SEP beaconBitRate= NUMBER SEP bitRateMode= LETTERS SEP (frequencyForMSS= NUMBER )* ( SEP )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:865:8: '$' device= DEVICE 'MSK' SEP frequencyToUse= NUMBER SEP frequencyMode= LETTERS SEP beaconBitRate= NUMBER SEP bitRateMode= LETTERS SEP (frequencyForMSS= NUMBER )* ( SEP )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3343 = getCharIndex();
			int deviceStartLine3343 = getLine();
			int deviceStartCharPos3343 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3343, getCharIndex()-1);
			device.setLine(deviceStartLine3343);
			device.setCharPositionInLine(deviceStartCharPos3343);

			match("MSK"); 

			mSEP(); 

			int frequencyToUseStart3358 = getCharIndex();
			int frequencyToUseStartLine3358 = getLine();
			int frequencyToUseStartCharPos3358 = getCharPositionInLine();
			mNUMBER(); 
			frequencyToUse = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyToUseStart3358, getCharIndex()-1);
			frequencyToUse.setLine(frequencyToUseStartLine3358);
			frequencyToUse.setCharPositionInLine(frequencyToUseStartCharPos3358);

			mSEP(); 

			int frequencyModeStart3378 = getCharIndex();
			int frequencyModeStartLine3378 = getLine();
			int frequencyModeStartCharPos3378 = getCharPositionInLine();
			mLETTERS(); 
			frequencyMode = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyModeStart3378, getCharIndex()-1);
			frequencyMode.setLine(frequencyModeStartLine3378);
			frequencyMode.setCharPositionInLine(frequencyModeStartCharPos3378);

			mSEP(); 

			int beaconBitRateStart3398 = getCharIndex();
			int beaconBitRateStartLine3398 = getLine();
			int beaconBitRateStartCharPos3398 = getCharPositionInLine();
			mNUMBER(); 
			beaconBitRate = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, beaconBitRateStart3398, getCharIndex()-1);
			beaconBitRate.setLine(beaconBitRateStartLine3398);
			beaconBitRate.setCharPositionInLine(beaconBitRateStartCharPos3398);

			mSEP(); 

			int bitRateModeStart3418 = getCharIndex();
			int bitRateModeStartLine3418 = getLine();
			int bitRateModeStartCharPos3418 = getCharPositionInLine();
			mLETTERS(); 
			bitRateMode = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bitRateModeStart3418, getCharIndex()-1);
			bitRateMode.setLine(bitRateModeStartLine3418);
			bitRateMode.setCharPositionInLine(bitRateModeStartCharPos3418);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:870:29: (frequencyForMSS= NUMBER )*
			loop129:
			while (true) {
				int alt129=2;
				int LA129_0 = input.LA(1);
				if ( (LA129_0=='.'||(LA129_0 >= '0' && LA129_0 <= '9')) ) {
					alt129=1;
				}

				switch (alt129) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:870:29: frequencyForMSS= NUMBER
					{
					int frequencyForMSSStart3438 = getCharIndex();
					int frequencyForMSSStartLine3438 = getLine();
					int frequencyForMSSStartCharPos3438 = getCharPositionInLine();
					mNUMBER(); 
					frequencyForMSS = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, frequencyForMSSStart3438, getCharIndex()-1);
					frequencyForMSS.setLine(frequencyForMSSStartLine3438);
					frequencyForMSS.setCharPositionInLine(frequencyForMSSStartCharPos3438);

					}
					break;

				default :
					break loop129;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:870:39: ( SEP )*
			loop130:
			while (true) {
				int alt130=2;
				int LA130_0 = input.LA(1);
				if ( (LA130_0==',') ) {
					alt130=1;
				}

				switch (alt130) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			int checksumStart3458 = getCharIndex();
			int checksumStartLine3458 = getLine();
			int checksumStartCharPos3458 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3458, getCharIndex()-1);
			checksum.setLine(checksumStartLine3458);
			checksum.setCharPositionInLine(checksumStartCharPos3458);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:888:5: ( '$' device= DEVICE 'MTA' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:888:7: '$' device= DEVICE 'MTA' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3476 = getCharIndex();
			int deviceStartLine3476 = getLine();
			int deviceStartCharPos3476 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3476, getCharIndex()-1);
			device.setLine(deviceStartLine3476);
			device.setCharPositionInLine(deviceStartCharPos3476);

			match("MTA"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:889:4: (temperature= NUMBER )*
			loop131:
			while (true) {
				int alt131=2;
				int LA131_0 = input.LA(1);
				if ( (LA131_0=='.'||(LA131_0 >= '0' && LA131_0 <= '9')) ) {
					alt131=1;
				}

				switch (alt131) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:889:5: temperature= NUMBER
					{
					int temperatureStart3490 = getCharIndex();
					int temperatureStartLine3490 = getLine();
					int temperatureStartCharPos3490 = getCharPositionInLine();
					mNUMBER(); 
					temperature = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, temperatureStart3490, getCharIndex()-1);
					temperature.setLine(temperatureStartLine3490);
					temperature.setCharPositionInLine(temperatureStartCharPos3490);

					}
					break;

				default :
					break loop131;
				}
			}

			mSEP(); 

			int unitStart3510 = getCharIndex();
			int unitStartLine3510 = getLine();
			int unitStartCharPos3510 = getCharPositionInLine();
			mLETTERS(); 
			unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3510, getCharIndex()-1);
			unit.setLine(unitStartLine3510);
			unit.setCharPositionInLine(unitStartCharPos3510);

			int checksumStart3525 = getCharIndex();
			int checksumStartLine3525 = getLine();
			int checksumStartCharPos3525 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3525, getCharIndex()-1);
			checksum.setLine(checksumStartLine3525);
			checksum.setCharPositionInLine(checksumStartCharPos3525);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:905:6: ( '$' device= DEVICE 'MTW' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:905:8: '$' device= DEVICE 'MTW' SEP (temperature= NUMBER )* SEP unit= LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3544 = getCharIndex();
			int deviceStartLine3544 = getLine();
			int deviceStartCharPos3544 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3544, getCharIndex()-1);
			device.setLine(deviceStartLine3544);
			device.setCharPositionInLine(deviceStartCharPos3544);

			match("MTW"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:906:4: (temperature= NUMBER )*
			loop132:
			while (true) {
				int alt132=2;
				int LA132_0 = input.LA(1);
				if ( (LA132_0=='.'||(LA132_0 >= '0' && LA132_0 <= '9')) ) {
					alt132=1;
				}

				switch (alt132) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:906:5: temperature= NUMBER
					{
					int temperatureStart3558 = getCharIndex();
					int temperatureStartLine3558 = getLine();
					int temperatureStartCharPos3558 = getCharPositionInLine();
					mNUMBER(); 
					temperature = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, temperatureStart3558, getCharIndex()-1);
					temperature.setLine(temperatureStartLine3558);
					temperature.setCharPositionInLine(temperatureStartCharPos3558);

					}
					break;

				default :
					break loop132;
				}
			}

			mSEP(); 

			int unitStart3578 = getCharIndex();
			int unitStartLine3578 = getLine();
			int unitStartCharPos3578 = getCharPositionInLine();
			mLETTERS(); 
			unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3578, getCharIndex()-1);
			unit.setLine(unitStartLine3578);
			unit.setCharPositionInLine(unitStartCharPos3578);

			int checksumStart3593 = getCharIndex();
			int checksumStartLine3593 = getLine();
			int checksumStartCharPos3593 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3593, getCharIndex()-1);
			checksum.setLine(checksumStartLine3593);
			checksum.setCharPositionInLine(checksumStartCharPos3593);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:917:7: ( '$' device= DEVICE 'MWD' SEP (windDirectionTrue= NUMBER )* SEP LETTERS SEP (windDirectionMagnetic= NUMBER )* SEP LETTERS SEP (windSpeed= NUMBER )* SEP LETTERS SEP NUMBER SEP LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:917:9: '$' device= DEVICE 'MWD' SEP (windDirectionTrue= NUMBER )* SEP LETTERS SEP (windDirectionMagnetic= NUMBER )* SEP LETTERS SEP (windSpeed= NUMBER )* SEP LETTERS SEP NUMBER SEP LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3616 = getCharIndex();
			int deviceStartLine3616 = getLine();
			int deviceStartCharPos3616 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3616, getCharIndex()-1);
			device.setLine(deviceStartLine3616);
			device.setCharPositionInLine(deviceStartCharPos3616);

			match("MWD"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:918:22: (windDirectionTrue= NUMBER )*
			loop133:
			while (true) {
				int alt133=2;
				int LA133_0 = input.LA(1);
				if ( (LA133_0=='.'||(LA133_0 >= '0' && LA133_0 <= '9')) ) {
					alt133=1;
				}

				switch (alt133) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:918:22: windDirectionTrue= NUMBER
					{
					int windDirectionTrueStart3629 = getCharIndex();
					int windDirectionTrueStartLine3629 = getLine();
					int windDirectionTrueStartCharPos3629 = getCharPositionInLine();
					mNUMBER(); 
					windDirectionTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionTrueStart3629, getCharIndex()-1);
					windDirectionTrue.setLine(windDirectionTrueStartLine3629);
					windDirectionTrue.setCharPositionInLine(windDirectionTrueStartCharPos3629);

					}
					break;

				default :
					break loop133;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:920:36: (windDirectionMagnetic= NUMBER )*
			loop134:
			while (true) {
				int alt134=2;
				int LA134_0 = input.LA(1);
				if ( (LA134_0=='.'||(LA134_0 >= '0' && LA134_0 <= '9')) ) {
					alt134=1;
				}

				switch (alt134) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:920:36: windDirectionMagnetic= NUMBER
					{
					int windDirectionMagneticStart3668 = getCharIndex();
					int windDirectionMagneticStartLine3668 = getLine();
					int windDirectionMagneticStartCharPos3668 = getCharPositionInLine();
					mNUMBER(); 
					windDirectionMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionMagneticStart3668, getCharIndex()-1);
					windDirectionMagnetic.setLine(windDirectionMagneticStartLine3668);
					windDirectionMagnetic.setCharPositionInLine(windDirectionMagneticStartCharPos3668);

					}
					break;

				default :
					break loop134;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:922:13: (windSpeed= NUMBER )*
			loop135:
			while (true) {
				int alt135=2;
				int LA135_0 = input.LA(1);
				if ( (LA135_0=='.'||(LA135_0 >= '0' && LA135_0 <= '9')) ) {
					alt135=1;
				}

				switch (alt135) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:922:13: windSpeed= NUMBER
					{
					int windSpeedStart3696 = getCharIndex();
					int windSpeedStartLine3696 = getLine();
					int windSpeedStartCharPos3696 = getCharPositionInLine();
					mNUMBER(); 
					windSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windSpeedStart3696, getCharIndex()-1);
					windSpeed.setLine(windSpeedStartLine3696);
					windSpeed.setCharPositionInLine(windSpeedStartCharPos3696);

					}
					break;

				default :
					break loop135;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			mNUMBER(); 

			mSEP(); 

			mLETTERS(); 

			int checksumStart3763 = getCharIndex();
			int checksumStartLine3763 = getLine();
			int checksumStartCharPos3763 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3763, getCharIndex()-1);
			checksum.setLine(checksumStartLine3763);
			checksum.setCharPositionInLine(checksumStartCharPos3763);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:936:6: ( '$' device= DEVICE 'MWV' SEP windAngle= NUMBER SEP reference= LETTERS SEP windSpeed= NUMBER SEP unit= LETTERS SEP status= LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:936:8: '$' device= DEVICE 'MWV' SEP windAngle= NUMBER SEP reference= LETTERS SEP windSpeed= NUMBER SEP unit= LETTERS SEP status= LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3787 = getCharIndex();
			int deviceStartLine3787 = getLine();
			int deviceStartCharPos3787 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3787, getCharIndex()-1);
			device.setLine(deviceStartLine3787);
			device.setCharPositionInLine(deviceStartCharPos3787);

			match("MWV"); 

			mSEP(); 

			int windAngleStart3800 = getCharIndex();
			int windAngleStartLine3800 = getLine();
			int windAngleStartCharPos3800 = getCharPositionInLine();
			mNUMBER(); 
			windAngle = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windAngleStart3800, getCharIndex()-1);
			windAngle.setLine(windAngleStartLine3800);
			windAngle.setCharPositionInLine(windAngleStartCharPos3800);

			mSEP(); 

			int referenceStart3821 = getCharIndex();
			int referenceStartLine3821 = getLine();
			int referenceStartCharPos3821 = getCharPositionInLine();
			mLETTERS(); 
			reference = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, referenceStart3821, getCharIndex()-1);
			reference.setLine(referenceStartLine3821);
			reference.setCharPositionInLine(referenceStartCharPos3821);

			mSEP(); 

			int windSpeedStart3842 = getCharIndex();
			int windSpeedStartLine3842 = getLine();
			int windSpeedStartCharPos3842 = getCharPositionInLine();
			mNUMBER(); 
			windSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windSpeedStart3842, getCharIndex()-1);
			windSpeed.setLine(windSpeedStartLine3842);
			windSpeed.setCharPositionInLine(windSpeedStartCharPos3842);

			mSEP(); 

			int unitStart3863 = getCharIndex();
			int unitStartLine3863 = getLine();
			int unitStartCharPos3863 = getCharPositionInLine();
			mLETTERS(); 
			unit = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, unitStart3863, getCharIndex()-1);
			unit.setLine(unitStartLine3863);
			unit.setCharPositionInLine(unitStartCharPos3863);

			mSEP(); 

			int statusStart3884 = getCharIndex();
			int statusStartLine3884 = getLine();
			int statusStartCharPos3884 = getCharPositionInLine();
			mLETTERS(); 
			status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart3884, getCharIndex()-1);
			status.setLine(statusStartLine3884);
			status.setCharPositionInLine(statusStartCharPos3884);

			int checksumStart3901 = getCharIndex();
			int checksumStartLine3901 = getLine();
			int checksumStartCharPos3901 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart3901, getCharIndex()-1);
			checksum.setLine(checksumStartLine3901);
			checksum.setCharPositionInLine(checksumStartCharPos3901);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:960:8: ( '$' device= DEVICE 'RMB' SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (fromWaypointId= LETTERS |fromWaypointId= NUMBER )* SEP (toWaypointId= LETTERS |toWaypointId= NUMBER )* SEP (destinationWaypointLatitude= NUMBER )* SEP (ns= LETTERS )* SEP (destinationWaypointLongitude= NUMBER )* SEP (ew= LETTERS )* SEP (rangeToDestination= NUMBER )* SEP (bearingToDestination= NUMBER )* SEP (destinationClosingVelocity= NUMBER )* SEP ( LETTERS SEP )* (arrivalStatus= LETTERS | '\\u0000' )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:960:10: '$' device= DEVICE 'RMB' SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP (directionToSteer= LETTERS )* SEP (fromWaypointId= LETTERS |fromWaypointId= NUMBER )* SEP (toWaypointId= LETTERS |toWaypointId= NUMBER )* SEP (destinationWaypointLatitude= NUMBER )* SEP (ns= LETTERS )* SEP (destinationWaypointLongitude= NUMBER )* SEP (ew= LETTERS )* SEP (rangeToDestination= NUMBER )* SEP (bearingToDestination= NUMBER )* SEP (destinationClosingVelocity= NUMBER )* SEP ( LETTERS SEP )* (arrivalStatus= LETTERS | '\\u0000' )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart3934 = getCharIndex();
			int deviceStartLine3934 = getLine();
			int deviceStartCharPos3934 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart3934, getCharIndex()-1);
			device.setLine(deviceStartLine3934);
			device.setCharPositionInLine(deviceStartCharPos3934);

			match("RMB"); 

			mSEP(); 

			int statusStart3955 = getCharIndex();
			int statusStartLine3955 = getLine();
			int statusStartCharPos3955 = getCharPositionInLine();
			mLETTERS(); 
			status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart3955, getCharIndex()-1);
			status.setLine(statusStartLine3955);
			status.setCharPositionInLine(statusStartCharPos3955);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:962:11: (crossTrackError= NUMBER )*
			loop136:
			while (true) {
				int alt136=2;
				int LA136_0 = input.LA(1);
				if ( (LA136_0=='.'||(LA136_0 >= '0' && LA136_0 <= '9')) ) {
					alt136=1;
				}

				switch (alt136) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:962:12: crossTrackError= NUMBER
					{
					int crossTrackErrorStart3977 = getCharIndex();
					int crossTrackErrorStartLine3977 = getLine();
					int crossTrackErrorStartCharPos3977 = getCharPositionInLine();
					mNUMBER(); 
					crossTrackError = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorStart3977, getCharIndex()-1);
					crossTrackError.setLine(crossTrackErrorStartLine3977);
					crossTrackError.setCharPositionInLine(crossTrackErrorStartCharPos3977);

					}
					break;

				default :
					break loop136;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:963:11: (directionToSteer= LETTERS )*
			loop137:
			while (true) {
				int alt137=2;
				int LA137_0 = input.LA(1);
				if ( (LA137_0==' '||(LA137_0 >= 'A' && LA137_0 <= 'Z')||(LA137_0 >= 'a' && LA137_0 <= 'z')) ) {
					alt137=1;
				}

				switch (alt137) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:963:12: directionToSteer= LETTERS
					{
					int directionToSteerStart3998 = getCharIndex();
					int directionToSteerStartLine3998 = getLine();
					int directionToSteerStartCharPos3998 = getCharPositionInLine();
					mLETTERS(); 
					directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart3998, getCharIndex()-1);
					directionToSteer.setLine(directionToSteerStartLine3998);
					directionToSteer.setCharPositionInLine(directionToSteerStartCharPos3998);

					}
					break;

				default :
					break loop137;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:964:11: (fromWaypointId= LETTERS |fromWaypointId= NUMBER )*
			loop138:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:964:12: fromWaypointId= LETTERS
					{
					int fromWaypointIdStart4019 = getCharIndex();
					int fromWaypointIdStartLine4019 = getLine();
					int fromWaypointIdStartCharPos4019 = getCharPositionInLine();
					mLETTERS(); 
					fromWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIdStart4019, getCharIndex()-1);
					fromWaypointId.setLine(fromWaypointIdStartLine4019);
					fromWaypointId.setCharPositionInLine(fromWaypointIdStartCharPos4019);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:964:38: fromWaypointId= NUMBER
					{
					int fromWaypointIdStart4026 = getCharIndex();
					int fromWaypointIdStartLine4026 = getLine();
					int fromWaypointIdStartCharPos4026 = getCharPositionInLine();
					mNUMBER(); 
					fromWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fromWaypointIdStart4026, getCharIndex()-1);
					fromWaypointId.setLine(fromWaypointIdStartLine4026);
					fromWaypointId.setCharPositionInLine(fromWaypointIdStartCharPos4026);

					}
					break;

				default :
					break loop138;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:965:11: (toWaypointId= LETTERS |toWaypointId= NUMBER )*
			loop139:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:965:12: toWaypointId= LETTERS
					{
					int toWaypointIdStart4049 = getCharIndex();
					int toWaypointIdStartLine4049 = getLine();
					int toWaypointIdStartCharPos4049 = getCharPositionInLine();
					mLETTERS(); 
					toWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIdStart4049, getCharIndex()-1);
					toWaypointId.setLine(toWaypointIdStartLine4049);
					toWaypointId.setCharPositionInLine(toWaypointIdStartCharPos4049);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:965:38: toWaypointId= NUMBER
					{
					int toWaypointIdStart4057 = getCharIndex();
					int toWaypointIdStartLine4057 = getLine();
					int toWaypointIdStartCharPos4057 = getCharPositionInLine();
					mNUMBER(); 
					toWaypointId = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, toWaypointIdStart4057, getCharIndex()-1);
					toWaypointId.setLine(toWaypointIdStartLine4057);
					toWaypointId.setCharPositionInLine(toWaypointIdStartCharPos4057);

					}
					break;

				default :
					break loop139;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:966:11: (destinationWaypointLatitude= NUMBER )*
			loop140:
			while (true) {
				int alt140=2;
				int LA140_0 = input.LA(1);
				if ( (LA140_0=='.'||(LA140_0 >= '0' && LA140_0 <= '9')) ) {
					alt140=1;
				}

				switch (alt140) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:966:12: destinationWaypointLatitude= NUMBER
					{
					int destinationWaypointLatitudeStart4079 = getCharIndex();
					int destinationWaypointLatitudeStartLine4079 = getLine();
					int destinationWaypointLatitudeStartCharPos4079 = getCharPositionInLine();
					mNUMBER(); 
					destinationWaypointLatitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointLatitudeStart4079, getCharIndex()-1);
					destinationWaypointLatitude.setLine(destinationWaypointLatitudeStartLine4079);
					destinationWaypointLatitude.setCharPositionInLine(destinationWaypointLatitudeStartCharPos4079);

					}
					break;

				default :
					break loop140;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:966:56: (ns= LETTERS )*
			loop141:
			while (true) {
				int alt141=2;
				int LA141_0 = input.LA(1);
				if ( (LA141_0==' '||(LA141_0 >= 'A' && LA141_0 <= 'Z')||(LA141_0 >= 'a' && LA141_0 <= 'z')) ) {
					alt141=1;
				}

				switch (alt141) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:966:57: ns= LETTERS
					{
					int nsStart4090 = getCharIndex();
					int nsStartLine4090 = getLine();
					int nsStartCharPos4090 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart4090, getCharIndex()-1);
					ns.setLine(nsStartLine4090);
					ns.setCharPositionInLine(nsStartCharPos4090);

					}
					break;

				default :
					break loop141;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:967:11: (destinationWaypointLongitude= NUMBER )*
			loop142:
			while (true) {
				int alt142=2;
				int LA142_0 = input.LA(1);
				if ( (LA142_0=='.'||(LA142_0 >= '0' && LA142_0 <= '9')) ) {
					alt142=1;
				}

				switch (alt142) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:967:12: destinationWaypointLongitude= NUMBER
					{
					int destinationWaypointLongitudeStart4113 = getCharIndex();
					int destinationWaypointLongitudeStartLine4113 = getLine();
					int destinationWaypointLongitudeStartCharPos4113 = getCharPositionInLine();
					mNUMBER(); 
					destinationWaypointLongitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationWaypointLongitudeStart4113, getCharIndex()-1);
					destinationWaypointLongitude.setLine(destinationWaypointLongitudeStartLine4113);
					destinationWaypointLongitude.setCharPositionInLine(destinationWaypointLongitudeStartCharPos4113);

					}
					break;

				default :
					break loop142;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:967:58: (ew= LETTERS )*
			loop143:
			while (true) {
				int alt143=2;
				int LA143_0 = input.LA(1);
				if ( (LA143_0==' '||(LA143_0 >= 'A' && LA143_0 <= 'Z')||(LA143_0 >= 'a' && LA143_0 <= 'z')) ) {
					alt143=1;
				}

				switch (alt143) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:967:59: ew= LETTERS
					{
					int ewStart4125 = getCharIndex();
					int ewStartLine4125 = getLine();
					int ewStartCharPos4125 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart4125, getCharIndex()-1);
					ew.setLine(ewStartLine4125);
					ew.setCharPositionInLine(ewStartCharPos4125);

					}
					break;

				default :
					break loop143;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:968:11: (rangeToDestination= NUMBER )*
			loop144:
			while (true) {
				int alt144=2;
				int LA144_0 = input.LA(1);
				if ( (LA144_0=='.'||(LA144_0 >= '0' && LA144_0 <= '9')) ) {
					alt144=1;
				}

				switch (alt144) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:968:12: rangeToDestination= NUMBER
					{
					int rangeToDestinationStart4147 = getCharIndex();
					int rangeToDestinationStartLine4147 = getLine();
					int rangeToDestinationStartCharPos4147 = getCharPositionInLine();
					mNUMBER(); 
					rangeToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, rangeToDestinationStart4147, getCharIndex()-1);
					rangeToDestination.setLine(rangeToDestinationStartLine4147);
					rangeToDestination.setCharPositionInLine(rangeToDestinationStartCharPos4147);

					}
					break;

				default :
					break loop144;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:969:11: (bearingToDestination= NUMBER )*
			loop145:
			while (true) {
				int alt145=2;
				int LA145_0 = input.LA(1);
				if ( (LA145_0=='.'||(LA145_0 >= '0' && LA145_0 <= '9')) ) {
					alt145=1;
				}

				switch (alt145) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:969:12: bearingToDestination= NUMBER
					{
					int bearingToDestinationStart4169 = getCharIndex();
					int bearingToDestinationStartLine4169 = getLine();
					int bearingToDestinationStartCharPos4169 = getCharPositionInLine();
					mNUMBER(); 
					bearingToDestination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bearingToDestinationStart4169, getCharIndex()-1);
					bearingToDestination.setLine(bearingToDestinationStartLine4169);
					bearingToDestination.setCharPositionInLine(bearingToDestinationStartCharPos4169);

					}
					break;

				default :
					break loop145;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:970:11: (destinationClosingVelocity= NUMBER )*
			loop146:
			while (true) {
				int alt146=2;
				int LA146_0 = input.LA(1);
				if ( (LA146_0=='.'||(LA146_0 >= '0' && LA146_0 <= '9')) ) {
					alt146=1;
				}

				switch (alt146) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:970:12: destinationClosingVelocity= NUMBER
					{
					int destinationClosingVelocityStart4191 = getCharIndex();
					int destinationClosingVelocityStartLine4191 = getLine();
					int destinationClosingVelocityStartCharPos4191 = getCharPositionInLine();
					mNUMBER(); 
					destinationClosingVelocity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationClosingVelocityStart4191, getCharIndex()-1);
					destinationClosingVelocity.setLine(destinationClosingVelocityStartLine4191);
					destinationClosingVelocity.setCharPositionInLine(destinationClosingVelocityStartCharPos4191);

					}
					break;

				default :
					break loop146;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:971:11: ( LETTERS SEP )*
			loop147:
			while (true) {
				int alt147=2;
				alt147 = dfa147.predict(input);
				switch (alt147) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:971:12: LETTERS SEP
					{
					mLETTERS(); 

					mSEP(); 

					}
					break;

				default :
					break loop147;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:972:11: (arrivalStatus= LETTERS | '\\u0000' )*
			loop148:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:972:12: arrivalStatus= LETTERS
					{
					int arrivalStatusStart4244 = getCharIndex();
					int arrivalStatusStartLine4244 = getLine();
					int arrivalStatusStartCharPos4244 = getCharPositionInLine();
					mLETTERS(); 
					arrivalStatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, arrivalStatusStart4244, getCharIndex()-1);
					arrivalStatus.setLine(arrivalStatusStartLine4244);
					arrivalStatus.setCharPositionInLine(arrivalStatusStartCharPos4244);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:972:38: '\\u0000'
					{
					match('\u0000'); 
					}
					break;

				default :
					break loop148;
				}
			}

			int checksumStart4265 = getCharIndex();
			int checksumStartLine4265 = getLine();
			int checksumStartCharPos4265 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4265, getCharIndex()-1);
			checksum.setLine(checksumStartLine4265);
			checksum.setCharPositionInLine(checksumStartCharPos4265);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1023:6: ( '$' device= DEVICE 'RMC' SEP (utc= NUMBER )* SEP status= LETTERS SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( SIGN )* (sog= NUMBER )* SEP (track= NUMBER )* SEP (yymmdd= NUMBER )* SEP (magneticVariation= NUMBER )* SEP (nsew= LETTERS )* ( SEP LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1023:8: '$' device= DEVICE 'RMC' SEP (utc= NUMBER )* SEP status= LETTERS SEP (latitude= NUMBER )* SEP (ns= LETTERS )* SEP (longitude= NUMBER )* SEP (ew= LETTERS )* SEP ( SIGN )* (sog= NUMBER )* SEP (track= NUMBER )* SEP (yymmdd= NUMBER )* SEP (magneticVariation= NUMBER )* SEP (nsew= LETTERS )* ( SEP LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4297 = getCharIndex();
			int deviceStartLine4297 = getLine();
			int deviceStartCharPos4297 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4297, getCharIndex()-1);
			device.setLine(deviceStartLine4297);
			device.setCharPositionInLine(deviceStartCharPos4297);

			match("RMC"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1024:16: (utc= NUMBER )*
			loop149:
			while (true) {
				int alt149=2;
				int LA149_0 = input.LA(1);
				if ( (LA149_0=='.'||(LA149_0 >= '0' && LA149_0 <= '9')) ) {
					alt149=1;
				}

				switch (alt149) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1024:16: utc= NUMBER
					{
					int utcStart4318 = getCharIndex();
					int utcStartLine4318 = getLine();
					int utcStartCharPos4318 = getCharPositionInLine();
					mNUMBER(); 
					utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart4318, getCharIndex()-1);
					utc.setLine(utcStartLine4318);
					utc.setCharPositionInLine(utcStartCharPos4318);

					}
					break;

				default :
					break loop149;
				}
			}

			mSEP(); 

			int statusStart4338 = getCharIndex();
			int statusStartLine4338 = getLine();
			int statusStartCharPos4338 = getCharPositionInLine();
			mLETTERS(); 
			status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart4338, getCharIndex()-1);
			status.setLine(statusStartLine4338);
			status.setCharPositionInLine(statusStartCharPos4338);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:20: (latitude= NUMBER )*
			loop150:
			while (true) {
				int alt150=2;
				int LA150_0 = input.LA(1);
				if ( (LA150_0=='.'||(LA150_0 >= '0' && LA150_0 <= '9')) ) {
					alt150=1;
				}

				switch (alt150) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:20: latitude= NUMBER
					{
					int latitudeStart4355 = getCharIndex();
					int latitudeStartLine4355 = getLine();
					int latitudeStartCharPos4355 = getCharPositionInLine();
					mNUMBER(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart4355, getCharIndex()-1);
					latitude.setLine(latitudeStartLine4355);
					latitude.setCharPositionInLine(latitudeStartCharPos4355);

					}
					break;

				default :
					break loop150;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:36: (ns= LETTERS )*
			loop151:
			while (true) {
				int alt151=2;
				int LA151_0 = input.LA(1);
				if ( (LA151_0==' '||(LA151_0 >= 'A' && LA151_0 <= 'Z')||(LA151_0 >= 'a' && LA151_0 <= 'z')) ) {
					alt151=1;
				}

				switch (alt151) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:36: ns= LETTERS
					{
					int nsStart4364 = getCharIndex();
					int nsStartLine4364 = getLine();
					int nsStartCharPos4364 = getCharPositionInLine();
					mLETTERS(); 
					ns = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsStart4364, getCharIndex()-1);
					ns.setLine(nsStartLine4364);
					ns.setCharPositionInLine(nsStartCharPos4364);

					}
					break;

				default :
					break loop151;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:60: (longitude= NUMBER )*
			loop152:
			while (true) {
				int alt152=2;
				int LA152_0 = input.LA(1);
				if ( (LA152_0=='.'||(LA152_0 >= '0' && LA152_0 <= '9')) ) {
					alt152=1;
				}

				switch (alt152) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:60: longitude= NUMBER
					{
					int longitudeStart4371 = getCharIndex();
					int longitudeStartLine4371 = getLine();
					int longitudeStartCharPos4371 = getCharPositionInLine();
					mNUMBER(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart4371, getCharIndex()-1);
					longitude.setLine(longitudeStartLine4371);
					longitude.setCharPositionInLine(longitudeStartCharPos4371);

					}
					break;

				default :
					break loop152;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:75: (ew= LETTERS )*
			loop153:
			while (true) {
				int alt153=2;
				int LA153_0 = input.LA(1);
				if ( (LA153_0==' '||(LA153_0 >= 'A' && LA153_0 <= 'Z')||(LA153_0 >= 'a' && LA153_0 <= 'z')) ) {
					alt153=1;
				}

				switch (alt153) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1026:75: ew= LETTERS
					{
					int ewStart4378 = getCharIndex();
					int ewStartLine4378 = getLine();
					int ewStartCharPos4378 = getCharPositionInLine();
					mLETTERS(); 
					ew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ewStart4378, getCharIndex()-1);
					ew.setLine(ewStartLine4378);
					ew.setCharPositionInLine(ewStartCharPos4378);

					}
					break;

				default :
					break loop153;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1027:12: ( SIGN )*
			loop154:
			while (true) {
				int alt154=2;
				int LA154_0 = input.LA(1);
				if ( (LA154_0=='+'||LA154_0=='-') ) {
					alt154=1;
				}

				switch (alt154) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1027:18: (sog= NUMBER )*
			loop155:
			while (true) {
				int alt155=2;
				int LA155_0 = input.LA(1);
				if ( (LA155_0=='.'||(LA155_0 >= '0' && LA155_0 <= '9')) ) {
					alt155=1;
				}

				switch (alt155) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1027:19: sog= NUMBER
					{
					int sogStart4402 = getCharIndex();
					int sogStartLine4402 = getLine();
					int sogStartCharPos4402 = getCharPositionInLine();
					mNUMBER(); 
					sog = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sogStart4402, getCharIndex()-1);
					sog.setLine(sogStartLine4402);
					sog.setCharPositionInLine(sogStartCharPos4402);

					}
					break;

				default :
					break loop155;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1028:12: (track= NUMBER )*
			loop156:
			while (true) {
				int alt156=2;
				int LA156_0 = input.LA(1);
				if ( (LA156_0=='.'||(LA156_0 >= '0' && LA156_0 <= '9')) ) {
					alt156=1;
				}

				switch (alt156) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1028:13: track= NUMBER
					{
					int trackStart4424 = getCharIndex();
					int trackStartLine4424 = getLine();
					int trackStartCharPos4424 = getCharPositionInLine();
					mNUMBER(); 
					track = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, trackStart4424, getCharIndex()-1);
					track.setLine(trackStartLine4424);
					track.setCharPositionInLine(trackStartCharPos4424);

					}
					break;

				default :
					break loop156;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1029:13: (yymmdd= NUMBER )*
			loop157:
			while (true) {
				int alt157=2;
				int LA157_0 = input.LA(1);
				if ( (LA157_0=='.'||(LA157_0 >= '0' && LA157_0 <= '9')) ) {
					alt157=1;
				}

				switch (alt157) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1029:14: yymmdd= NUMBER
					{
					int yymmddStart4447 = getCharIndex();
					int yymmddStartLine4447 = getLine();
					int yymmddStartCharPos4447 = getCharPositionInLine();
					mNUMBER(); 
					yymmdd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, yymmddStart4447, getCharIndex()-1);
					yymmdd.setLine(yymmddStartLine4447);
					yymmdd.setCharPositionInLine(yymmddStartCharPos4447);

					}
					break;

				default :
					break loop157;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1030:31: (magneticVariation= NUMBER )*
			loop158:
			while (true) {
				int alt158=2;
				int LA158_0 = input.LA(1);
				if ( (LA158_0=='.'||(LA158_0 >= '0' && LA158_0 <= '9')) ) {
					alt158=1;
				}

				switch (alt158) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1030:31: magneticVariation= NUMBER
					{
					int magneticVariationStart4469 = getCharIndex();
					int magneticVariationStartLine4469 = getLine();
					int magneticVariationStartCharPos4469 = getCharPositionInLine();
					mNUMBER(); 
					magneticVariation = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, magneticVariationStart4469, getCharIndex()-1);
					magneticVariation.setLine(magneticVariationStartLine4469);
					magneticVariation.setCharPositionInLine(magneticVariationStartCharPos4469);

					}
					break;

				default :
					break loop158;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1031:18: (nsew= LETTERS )*
			loop159:
			while (true) {
				int alt159=2;
				int LA159_0 = input.LA(1);
				if ( (LA159_0==' '||(LA159_0 >= 'A' && LA159_0 <= 'Z')||(LA159_0 >= 'a' && LA159_0 <= 'z')) ) {
					alt159=1;
				}

				switch (alt159) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1031:18: nsew= LETTERS
					{
					int nsewStart4491 = getCharIndex();
					int nsewStartLine4491 = getLine();
					int nsewStartCharPos4491 = getCharPositionInLine();
					mLETTERS(); 
					nsew = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nsewStart4491, getCharIndex()-1);
					nsew.setLine(nsewStartLine4491);
					nsew.setCharPositionInLine(nsewStartCharPos4491);

					}
					break;

				default :
					break loop159;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1032:13: ( SEP LETTERS )*
			loop160:
			while (true) {
				int alt160=2;
				int LA160_0 = input.LA(1);
				if ( (LA160_0==',') ) {
					alt160=1;
				}

				switch (alt160) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1032:14: SEP LETTERS
					{
					mSEP(); 

					mLETTERS(); 

					}
					break;

				default :
					break loop160;
				}
			}

			int checksumStart4527 = getCharIndex();
			int checksumStartLine4527 = getLine();
			int checksumStartCharPos4527 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4527, getCharIndex()-1);
			checksum.setLine(checksumStartLine4527);
			checksum.setCharPositionInLine(checksumStartCharPos4527);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1048:6: ( '$' device= DEVICE 'RSD' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1048:9: '$' device= DEVICE 'RSD' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4556 = getCharIndex();
			int deviceStartLine4556 = getLine();
			int deviceStartCharPos4556 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4556, getCharIndex()-1);
			device.setLine(deviceStartLine4556);
			device.setCharPositionInLine(deviceStartCharPos4556);

			match("RSD"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1049:3: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
			loop161:
			while (true) {
				int alt161=2;
				alt161 = dfa161.predict(input);
				switch (alt161) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			int checksumStart4586 = getCharIndex();
			int checksumStartLine4586 = getLine();
			int checksumStartCharPos4586 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4586, getCharIndex()-1);
			checksum.setLine(checksumStartLine4586);
			checksum.setCharPositionInLine(checksumStartCharPos4586);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1062:5: ( '$' device= DEVICE 'RTE' SEP totalNumberOfsentence= NUMBER SEP sentenceNumber= NUMBER SEP type= LETTERS SEP ( LETTERS | NUMBER | '-' | '_' | SEP )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1062:7: '$' device= DEVICE 'RTE' SEP totalNumberOfsentence= NUMBER SEP sentenceNumber= NUMBER SEP type= LETTERS SEP ( LETTERS | NUMBER | '-' | '_' | SEP )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4605 = getCharIndex();
			int deviceStartLine4605 = getLine();
			int deviceStartCharPos4605 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4605, getCharIndex()-1);
			device.setLine(deviceStartLine4605);
			device.setCharPositionInLine(deviceStartCharPos4605);

			match("RTE"); 

			mSEP(); 

			int totalNumberOfsentenceStart4616 = getCharIndex();
			int totalNumberOfsentenceStartLine4616 = getLine();
			int totalNumberOfsentenceStartCharPos4616 = getCharPositionInLine();
			mNUMBER(); 
			totalNumberOfsentence = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, totalNumberOfsentenceStart4616, getCharIndex()-1);
			totalNumberOfsentence.setLine(totalNumberOfsentenceStartLine4616);
			totalNumberOfsentence.setCharPositionInLine(totalNumberOfsentenceStartCharPos4616);

			mSEP(); 

			int sentenceNumberStart4625 = getCharIndex();
			int sentenceNumberStartLine4625 = getLine();
			int sentenceNumberStartCharPos4625 = getCharPositionInLine();
			mNUMBER(); 
			sentenceNumber = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sentenceNumberStart4625, getCharIndex()-1);
			sentenceNumber.setLine(sentenceNumberStartLine4625);
			sentenceNumber.setCharPositionInLine(sentenceNumberStartCharPos4625);

			mSEP(); 

			int typeStart4634 = getCharIndex();
			int typeStartLine4634 = getLine();
			int typeStartCharPos4634 = getCharPositionInLine();
			mLETTERS(); 
			type = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, typeStart4634, getCharIndex()-1);
			type.setLine(typeStartLine4634);
			type.setCharPositionInLine(typeStartCharPos4634);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:2: ( LETTERS | NUMBER | '-' | '_' | SEP )*
			loop162:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:3: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:13: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:22: '-'
					{
					match('-'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:28: '_'
					{
					match('_'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1066:34: SEP
					{
					mSEP(); 

					}
					break;

				default :
					break loop162;
				}
			}

			int checksumStart4664 = getCharIndex();
			int checksumStartLine4664 = getLine();
			int checksumStartCharPos4664 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4664, getCharIndex()-1);
			checksum.setLine(checksumStartLine4664);
			checksum.setCharPositionInLine(checksumStartCharPos4664);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1081:6: ( '$' device= DEVICE 'VBW' SEP ( ' ' )* ( SIGN )* longitudinalWaterSpeed= NUMBER SEP ( ' ' )* ( SIGN )* transverseWaterSpeed= NUMBER SEP wstatus= LETTERS ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1081:8: '$' device= DEVICE 'VBW' SEP ( ' ' )* ( SIGN )* longitudinalWaterSpeed= NUMBER SEP ( ' ' )* ( SIGN )* transverseWaterSpeed= NUMBER SEP wstatus= LETTERS ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4683 = getCharIndex();
			int deviceStartLine4683 = getLine();
			int deviceStartCharPos4683 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4683, getCharIndex()-1);
			device.setLine(deviceStartLine4683);
			device.setCharPositionInLine(deviceStartCharPos4683);

			match("VBW"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1082:2: ( ' ' )*
			loop163:
			while (true) {
				int alt163=2;
				int LA163_0 = input.LA(1);
				if ( (LA163_0==' ') ) {
					alt163=1;
				}

				switch (alt163) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1082:2: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop163;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1082:7: ( SIGN )*
			loop164:
			while (true) {
				int alt164=2;
				int LA164_0 = input.LA(1);
				if ( (LA164_0=='+'||LA164_0=='-') ) {
					alt164=1;
				}

				switch (alt164) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			int longitudinalWaterSpeedStart4700 = getCharIndex();
			int longitudinalWaterSpeedStartLine4700 = getLine();
			int longitudinalWaterSpeedStartCharPos4700 = getCharPositionInLine();
			mNUMBER(); 
			longitudinalWaterSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudinalWaterSpeedStart4700, getCharIndex()-1);
			longitudinalWaterSpeed.setLine(longitudinalWaterSpeedStartLine4700);
			longitudinalWaterSpeed.setCharPositionInLine(longitudinalWaterSpeedStartCharPos4700);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1083:2: ( ' ' )*
			loop165:
			while (true) {
				int alt165=2;
				int LA165_0 = input.LA(1);
				if ( (LA165_0==' ') ) {
					alt165=1;
				}

				switch (alt165) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1083:2: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop165;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1083:7: ( SIGN )*
			loop166:
			while (true) {
				int alt166=2;
				int LA166_0 = input.LA(1);
				if ( (LA166_0=='+'||LA166_0=='-') ) {
					alt166=1;
				}

				switch (alt166) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			}

			int transverseWaterSpeedStart4715 = getCharIndex();
			int transverseWaterSpeedStartLine4715 = getLine();
			int transverseWaterSpeedStartCharPos4715 = getCharPositionInLine();
			mNUMBER(); 
			transverseWaterSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, transverseWaterSpeedStart4715, getCharIndex()-1);
			transverseWaterSpeed.setLine(transverseWaterSpeedStartLine4715);
			transverseWaterSpeed.setCharPositionInLine(transverseWaterSpeedStartCharPos4715);

			mSEP(); 

			int wstatusStart4724 = getCharIndex();
			int wstatusStartLine4724 = getLine();
			int wstatusStartCharPos4724 = getCharPositionInLine();
			mLETTERS(); 
			wstatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wstatusStart4724, getCharIndex()-1);
			wstatus.setLine(wstatusStartLine4724);
			wstatus.setCharPositionInLine(wstatusStartCharPos4724);

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:2: ( SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS )*
			loop173:
			while (true) {
				int alt173=2;
				int LA173_0 = input.LA(1);
				if ( (LA173_0==',') ) {
					alt173=1;
				}

				switch (alt173) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:3: SEP ( ' ' )* ( SIGN )* (longitudinalGroundSpeed= NUMBER )* SEP ( ' ' )* ( SIGN )* (transverseGroundSpeed= NUMBER )* SEP gstatus= LETTERS
					{
					mSEP(); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:7: ( ' ' )*
					loop167:
					while (true) {
						int alt167=2;
						int LA167_0 = input.LA(1);
						if ( (LA167_0==' ') ) {
							alt167=1;
						}

						switch (alt167) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:7: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop167;
						}
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:12: ( SIGN )*
					loop168:
					while (true) {
						int alt168=2;
						int LA168_0 = input.LA(1);
						if ( (LA168_0=='+'||LA168_0=='-') ) {
							alt168=1;
						}

						switch (alt168) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:18: (longitudinalGroundSpeed= NUMBER )*
					loop169:
					while (true) {
						int alt169=2;
						int LA169_0 = input.LA(1);
						if ( (LA169_0=='.'||(LA169_0 >= '0' && LA169_0 <= '9')) ) {
							alt169=1;
						}

						switch (alt169) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1085:19: longitudinalGroundSpeed= NUMBER
							{
							int longitudinalGroundSpeedStart4741 = getCharIndex();
							int longitudinalGroundSpeedStartLine4741 = getLine();
							int longitudinalGroundSpeedStartCharPos4741 = getCharPositionInLine();
							mNUMBER(); 
							longitudinalGroundSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudinalGroundSpeedStart4741, getCharIndex()-1);
							longitudinalGroundSpeed.setLine(longitudinalGroundSpeedStartLine4741);
							longitudinalGroundSpeed.setCharPositionInLine(longitudinalGroundSpeedStartCharPos4741);

							}
							break;

						default :
							break loop169;
						}
					}

					mSEP(); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1086:2: ( ' ' )*
					loop170:
					while (true) {
						int alt170=2;
						int LA170_0 = input.LA(1);
						if ( (LA170_0==' ') ) {
							alt170=1;
						}

						switch (alt170) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1086:2: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop170;
						}
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1086:7: ( SIGN )*
					loop171:
					while (true) {
						int alt171=2;
						int LA171_0 = input.LA(1);
						if ( (LA171_0=='+'||LA171_0=='-') ) {
							alt171=1;
						}

						switch (alt171) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1086:13: (transverseGroundSpeed= NUMBER )*
					loop172:
					while (true) {
						int alt172=2;
						int LA172_0 = input.LA(1);
						if ( (LA172_0=='.'||(LA172_0 >= '0' && LA172_0 <= '9')) ) {
							alt172=1;
						}

						switch (alt172) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1086:14: transverseGroundSpeed= NUMBER
							{
							int transverseGroundSpeedStart4759 = getCharIndex();
							int transverseGroundSpeedStartLine4759 = getLine();
							int transverseGroundSpeedStartCharPos4759 = getCharPositionInLine();
							mNUMBER(); 
							transverseGroundSpeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, transverseGroundSpeedStart4759, getCharIndex()-1);
							transverseGroundSpeed.setLine(transverseGroundSpeedStartLine4759);
							transverseGroundSpeed.setCharPositionInLine(transverseGroundSpeedStartCharPos4759);

							}
							break;

						default :
							break loop172;
						}
					}

					mSEP(); 

					int gstatusStart4770 = getCharIndex();
					int gstatusStartLine4770 = getLine();
					int gstatusStartCharPos4770 = getCharPositionInLine();
					mLETTERS(); 
					gstatus = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, gstatusStart4770, getCharIndex()-1);
					gstatus.setLine(gstatusStartLine4770);
					gstatus.setCharPositionInLine(gstatusStartCharPos4770);

					}
					break;

				default :
					break loop173;
				}
			}

			int checksumStart4780 = getCharIndex();
			int checksumStartLine4780 = getLine();
			int checksumStartCharPos4780 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4780, getCharIndex()-1);
			checksum.setLine(checksumStartLine4780);
			checksum.setCharPositionInLine(checksumStartCharPos4780);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1103:5: ( '$' device= DEVICE 'VLW' SEP ( ' ' )* (dataTotalWaterDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripWaterDistance= NUMBER )* SEP LETTERS ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1103:7: '$' device= DEVICE 'VLW' SEP ( ' ' )* (dataTotalWaterDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripWaterDistance= NUMBER )* SEP LETTERS ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4798 = getCharIndex();
			int deviceStartLine4798 = getLine();
			int deviceStartCharPos4798 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4798, getCharIndex()-1);
			device.setLine(deviceStartLine4798);
			device.setCharPositionInLine(deviceStartCharPos4798);

			match("VLW"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1104:6: ( ' ' )*
			loop174:
			while (true) {
				int alt174=2;
				int LA174_0 = input.LA(1);
				if ( (LA174_0==' ') ) {
					alt174=1;
				}

				switch (alt174) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1104:6: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop174;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1104:11: (dataTotalWaterDistance= NUMBER )*
			loop175:
			while (true) {
				int alt175=2;
				int LA175_0 = input.LA(1);
				if ( (LA175_0=='.'||(LA175_0 >= '0' && LA175_0 <= '9')) ) {
					alt175=1;
				}

				switch (alt175) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1104:12: dataTotalWaterDistance= NUMBER
					{
					int dataTotalWaterDistanceStart4817 = getCharIndex();
					int dataTotalWaterDistanceStartLine4817 = getLine();
					int dataTotalWaterDistanceStartCharPos4817 = getCharPositionInLine();
					mNUMBER(); 
					dataTotalWaterDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTotalWaterDistanceStart4817, getCharIndex()-1);
					dataTotalWaterDistance.setLine(dataTotalWaterDistanceStartLine4817);
					dataTotalWaterDistance.setCharPositionInLine(dataTotalWaterDistanceStartCharPos4817);

					}
					break;

				default :
					break loop175;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1106:6: ( ' ' )*
			loop176:
			while (true) {
				int alt176=2;
				int LA176_0 = input.LA(1);
				if ( (LA176_0==' ') ) {
					alt176=1;
				}

				switch (alt176) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1106:6: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop176;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1106:11: (dataTripWaterDistance= NUMBER )*
			loop177:
			while (true) {
				int alt177=2;
				int LA177_0 = input.LA(1);
				if ( (LA177_0=='.'||(LA177_0 >= '0' && LA177_0 <= '9')) ) {
					alt177=1;
				}

				switch (alt177) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1106:12: dataTripWaterDistance= NUMBER
					{
					int dataTripWaterDistanceStart4845 = getCharIndex();
					int dataTripWaterDistanceStartLine4845 = getLine();
					int dataTripWaterDistanceStartCharPos4845 = getCharPositionInLine();
					mNUMBER(); 
					dataTripWaterDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTripWaterDistanceStart4845, getCharIndex()-1);
					dataTripWaterDistance.setLine(dataTripWaterDistanceStartLine4845);
					dataTripWaterDistance.setCharPositionInLine(dataTripWaterDistanceStartCharPos4845);

					}
					break;

				default :
					break loop177;
				}
			}

			mSEP(); 

			mLETTERS(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:6: ( SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS )*
			loop182:
			while (true) {
				int alt182=2;
				int LA182_0 = input.LA(1);
				if ( (LA182_0==',') ) {
					alt182=1;
				}

				switch (alt182) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:7: SEP ( ' ' )* (dataTotalGroundDistance= NUMBER )* SEP LETTERS SEP ( ' ' )* (dataTripGroundDistance= NUMBER )* SEP LETTERS
					{
					mSEP(); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:11: ( ' ' )*
					loop178:
					while (true) {
						int alt178=2;
						int LA178_0 = input.LA(1);
						if ( (LA178_0==' ') ) {
							alt178=1;
						}

						switch (alt178) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:11: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop178;
						}
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:16: (dataTotalGroundDistance= NUMBER )*
					loop179:
					while (true) {
						int alt179=2;
						int LA179_0 = input.LA(1);
						if ( (LA179_0=='.'||(LA179_0 >= '0' && LA179_0 <= '9')) ) {
							alt179=1;
						}

						switch (alt179) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1108:17: dataTotalGroundDistance= NUMBER
							{
							int dataTotalGroundDistanceStart4875 = getCharIndex();
							int dataTotalGroundDistanceStartLine4875 = getLine();
							int dataTotalGroundDistanceStartCharPos4875 = getCharPositionInLine();
							mNUMBER(); 
							dataTotalGroundDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTotalGroundDistanceStart4875, getCharIndex()-1);
							dataTotalGroundDistance.setLine(dataTotalGroundDistanceStartLine4875);
							dataTotalGroundDistance.setCharPositionInLine(dataTotalGroundDistanceStartCharPos4875);

							}
							break;

						default :
							break loop179;
						}
					}

					mSEP(); 

					mLETTERS(); 

					mSEP(); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1110:6: ( ' ' )*
					loop180:
					while (true) {
						int alt180=2;
						int LA180_0 = input.LA(1);
						if ( (LA180_0==' ') ) {
							alt180=1;
						}

						switch (alt180) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1110:6: ' '
							{
							match(' '); 
							}
							break;

						default :
							break loop180;
						}
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1110:11: (dataTripGroundDistance= NUMBER )*
					loop181:
					while (true) {
						int alt181=2;
						int LA181_0 = input.LA(1);
						if ( (LA181_0=='.'||(LA181_0 >= '0' && LA181_0 <= '9')) ) {
							alt181=1;
						}

						switch (alt181) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1110:12: dataTripGroundDistance= NUMBER
							{
							int dataTripGroundDistanceStart4903 = getCharIndex();
							int dataTripGroundDistanceStartLine4903 = getLine();
							int dataTripGroundDistanceStartCharPos4903 = getCharPositionInLine();
							mNUMBER(); 
							dataTripGroundDistance = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dataTripGroundDistanceStart4903, getCharIndex()-1);
							dataTripGroundDistance.setLine(dataTripGroundDistanceStartLine4903);
							dataTripGroundDistance.setCharPositionInLine(dataTripGroundDistanceStartCharPos4903);

							}
							break;

						default :
							break loop181;
						}
					}

					mSEP(); 

					mLETTERS(); 

					}
					break;

				default :
					break loop182;
				}
			}

			int checksumStart4932 = getCharIndex();
			int checksumStartLine4932 = getLine();
			int checksumStartCharPos4932 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart4932, getCharIndex()-1);
			checksum.setLine(checksumStartLine4932);
			checksum.setCharPositionInLine(checksumStartCharPos4932);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1131:5: ( '$' device= DEVICE 'VHW' SEP ( ' ' )* (degreesTrue= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (degreesMagnetic= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKnots= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKilometers= NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1131:8: '$' device= DEVICE 'VHW' SEP ( ' ' )* (degreesTrue= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (degreesMagnetic= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKnots= NUMBER )* SEP ( LETTERS )* SEP ( ' ' )* (speedInKilometers= NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart4955 = getCharIndex();
			int deviceStartLine4955 = getLine();
			int deviceStartCharPos4955 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart4955, getCharIndex()-1);
			device.setLine(deviceStartLine4955);
			device.setCharPositionInLine(deviceStartCharPos4955);

			match("VHW"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1132:6: ( ' ' )*
			loop183:
			while (true) {
				int alt183=2;
				int LA183_0 = input.LA(1);
				if ( (LA183_0==' ') ) {
					alt183=1;
				}

				switch (alt183) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1132:6: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop183;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1132:11: (degreesTrue= NUMBER )*
			loop184:
			while (true) {
				int alt184=2;
				int LA184_0 = input.LA(1);
				if ( (LA184_0=='.'||(LA184_0 >= '0' && LA184_0 <= '9')) ) {
					alt184=1;
				}

				switch (alt184) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1132:12: degreesTrue= NUMBER
					{
					int degreesTrueStart4974 = getCharIndex();
					int degreesTrueStartLine4974 = getLine();
					int degreesTrueStartCharPos4974 = getCharPositionInLine();
					mNUMBER(); 
					degreesTrue = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, degreesTrueStart4974, getCharIndex()-1);
					degreesTrue.setLine(degreesTrueStartLine4974);
					degreesTrue.setCharPositionInLine(degreesTrueStartCharPos4974);

					}
					break;

				default :
					break loop184;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1133:13: ( LETTERS )*
			loop185:
			while (true) {
				int alt185=2;
				int LA185_0 = input.LA(1);
				if ( (LA185_0==' '||(LA185_0 >= 'A' && LA185_0 <= 'Z')||(LA185_0 >= 'a' && LA185_0 <= 'z')) ) {
					alt185=1;
				}

				switch (alt185) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1133:13: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop185;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1134:13: ( ' ' )*
			loop186:
			while (true) {
				int alt186=2;
				int LA186_0 = input.LA(1);
				if ( (LA186_0==' ') ) {
					alt186=1;
				}

				switch (alt186) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1134:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop186;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1134:18: (degreesMagnetic= NUMBER )*
			loop187:
			while (true) {
				int alt187=2;
				int LA187_0 = input.LA(1);
				if ( (LA187_0=='.'||(LA187_0 >= '0' && LA187_0 <= '9')) ) {
					alt187=1;
				}

				switch (alt187) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1134:19: degreesMagnetic= NUMBER
					{
					int degreesMagneticStart5017 = getCharIndex();
					int degreesMagneticStartLine5017 = getLine();
					int degreesMagneticStartCharPos5017 = getCharPositionInLine();
					mNUMBER(); 
					degreesMagnetic = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, degreesMagneticStart5017, getCharIndex()-1);
					degreesMagnetic.setLine(degreesMagneticStartLine5017);
					degreesMagnetic.setCharPositionInLine(degreesMagneticStartCharPos5017);

					}
					break;

				default :
					break loop187;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1135:13: ( LETTERS )*
			loop188:
			while (true) {
				int alt188=2;
				int LA188_0 = input.LA(1);
				if ( (LA188_0==' '||(LA188_0 >= 'A' && LA188_0 <= 'Z')||(LA188_0 >= 'a' && LA188_0 <= 'z')) ) {
					alt188=1;
				}

				switch (alt188) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1135:13: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop188;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1136:13: ( ' ' )*
			loop189:
			while (true) {
				int alt189=2;
				int LA189_0 = input.LA(1);
				if ( (LA189_0==' ') ) {
					alt189=1;
				}

				switch (alt189) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1136:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop189;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1136:18: (speedInKnots= NUMBER )*
			loop190:
			while (true) {
				int alt190=2;
				int LA190_0 = input.LA(1);
				if ( (LA190_0=='.'||(LA190_0 >= '0' && LA190_0 <= '9')) ) {
					alt190=1;
				}

				switch (alt190) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1136:19: speedInKnots= NUMBER
					{
					int speedInKnotsStart5060 = getCharIndex();
					int speedInKnotsStartLine5060 = getLine();
					int speedInKnotsStartCharPos5060 = getCharPositionInLine();
					mNUMBER(); 
					speedInKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKnotsStart5060, getCharIndex()-1);
					speedInKnots.setLine(speedInKnotsStartLine5060);
					speedInKnots.setCharPositionInLine(speedInKnotsStartCharPos5060);

					}
					break;

				default :
					break loop190;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1137:13: ( LETTERS )*
			loop191:
			while (true) {
				int alt191=2;
				int LA191_0 = input.LA(1);
				if ( (LA191_0==' '||(LA191_0 >= 'A' && LA191_0 <= 'Z')||(LA191_0 >= 'a' && LA191_0 <= 'z')) ) {
					alt191=1;
				}

				switch (alt191) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1137:13: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop191;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1138:13: ( ' ' )*
			loop192:
			while (true) {
				int alt192=2;
				int LA192_0 = input.LA(1);
				if ( (LA192_0==' ') ) {
					alt192=1;
				}

				switch (alt192) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1138:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop192;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1138:18: (speedInKilometers= NUMBER )*
			loop193:
			while (true) {
				int alt193=2;
				int LA193_0 = input.LA(1);
				if ( (LA193_0=='.'||(LA193_0 >= '0' && LA193_0 <= '9')) ) {
					alt193=1;
				}

				switch (alt193) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1138:19: speedInKilometers= NUMBER
					{
					int speedInKilometersStart5103 = getCharIndex();
					int speedInKilometersStartLine5103 = getLine();
					int speedInKilometersStartCharPos5103 = getCharPositionInLine();
					mNUMBER(); 
					speedInKilometers = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKilometersStart5103, getCharIndex()-1);
					speedInKilometers.setLine(speedInKilometersStartLine5103);
					speedInKilometers.setCharPositionInLine(speedInKilometersStartCharPos5103);

					}
					break;

				default :
					break loop193;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1139:13: ( LETTERS )*
			loop194:
			while (true) {
				int alt194=2;
				int LA194_0 = input.LA(1);
				if ( (LA194_0==' '||(LA194_0 >= 'A' && LA194_0 <= 'Z')||(LA194_0 >= 'a' && LA194_0 <= 'z')) ) {
					alt194=1;
				}

				switch (alt194) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1139:13: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop194;
				}
			}

			int checksumStart5138 = getCharIndex();
			int checksumStartLine5138 = getLine();
			int checksumStartCharPos5138 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5138, getCharIndex()-1);
			checksum.setLine(checksumStartLine5138);
			checksum.setCharPositionInLine(checksumStartCharPos5138);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1157:6: ( '$' device= DEVICE 'VPW' SEP ( ' ' )* (speed= NUMBER |speed= SIGN )* SEP LETTERS SEP ( ' ' )* ( NUMBER | '-' )* SEP ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1157:9: '$' device= DEVICE 'VPW' SEP ( ' ' )* (speed= NUMBER |speed= SIGN )* SEP LETTERS SEP ( ' ' )* ( NUMBER | '-' )* SEP ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5167 = getCharIndex();
			int deviceStartLine5167 = getLine();
			int deviceStartCharPos5167 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5167, getCharIndex()-1);
			device.setLine(deviceStartLine5167);
			device.setCharPositionInLine(deviceStartCharPos5167);

			match("VPW"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1158:2: ( ' ' )*
			loop195:
			while (true) {
				int alt195=2;
				int LA195_0 = input.LA(1);
				if ( (LA195_0==' ') ) {
					alt195=1;
				}

				switch (alt195) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1158:2: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop195;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1158:7: (speed= NUMBER |speed= SIGN )*
			loop196:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1158:8: speed= NUMBER
					{
					int speedStart5182 = getCharIndex();
					int speedStartLine5182 = getLine();
					int speedStartCharPos5182 = getCharPositionInLine();
					mNUMBER(); 
					speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart5182, getCharIndex()-1);
					speed.setLine(speedStartLine5182);
					speed.setCharPositionInLine(speedStartCharPos5182);

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1158:25: speed= SIGN
					{
					int speedStart5190 = getCharIndex();
					int speedStartLine5190 = getLine();
					int speedStartCharPos5190 = getCharPositionInLine();
					mSIGN(); 
					speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart5190, getCharIndex()-1);
					speed.setLine(speedStartLine5190);
					speed.setCharPositionInLine(speedStartCharPos5190);

					}
					break;

				default :
					break loop196;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1160:3: ( ' ' )*
			loop197:
			while (true) {
				int alt197=2;
				int LA197_0 = input.LA(1);
				if ( (LA197_0==' ') ) {
					alt197=1;
				}

				switch (alt197) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1160:3: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop197;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1160:8: ( NUMBER | '-' )*
			loop198:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1160:9: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1160:18: '-'
					{
					match('-'); 
					}
					break;

				default :
					break loop198;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1161:10: ( LETTERS )*
			loop199:
			while (true) {
				int alt199=2;
				int LA199_0 = input.LA(1);
				if ( (LA199_0==' '||(LA199_0 >= 'A' && LA199_0 <= 'Z')||(LA199_0 >= 'a' && LA199_0 <= 'z')) ) {
					alt199=1;
				}

				switch (alt199) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1161:10: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop199;
				}
			}

			int checksumStart5241 = getCharIndex();
			int checksumStartLine5241 = getLine();
			int checksumStartCharPos5241 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5241, getCharIndex()-1);
			checksum.setLine(checksumStartLine5241);
			checksum.setCharPositionInLine(checksumStartCharPos5241);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1173:6: ( '$' device= DEVICE 'VTG' SEP ( ' ' )* (trueTrackMadeGoodDegrees= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (magneticTrackMadeGood= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKnots= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKph= NUMBER )* SEP ( LETTERS SEP )* ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1173:9: '$' device= DEVICE 'VTG' SEP ( ' ' )* (trueTrackMadeGoodDegrees= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (magneticTrackMadeGood= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKnots= NUMBER )* SEP ( LETTERS ) SEP ( ' ' )* (groundSpeedKph= NUMBER )* SEP ( LETTERS SEP )* ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5261 = getCharIndex();
			int deviceStartLine5261 = getLine();
			int deviceStartCharPos5261 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5261, getCharIndex()-1);
			device.setLine(deviceStartLine5261);
			device.setCharPositionInLine(deviceStartCharPos5261);

			match("VTG"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1174:13: ( ' ' )*
			loop200:
			while (true) {
				int alt200=2;
				int LA200_0 = input.LA(1);
				if ( (LA200_0==' ') ) {
					alt200=1;
				}

				switch (alt200) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1174:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop200;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1174:18: (trueTrackMadeGoodDegrees= NUMBER )*
			loop201:
			while (true) {
				int alt201=2;
				int LA201_0 = input.LA(1);
				if ( (LA201_0=='.'||(LA201_0 >= '0' && LA201_0 <= '9')) ) {
					alt201=1;
				}

				switch (alt201) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1174:19: trueTrackMadeGoodDegrees= NUMBER
					{
					int trueTrackMadeGoodDegreesStart5287 = getCharIndex();
					int trueTrackMadeGoodDegreesStartLine5287 = getLine();
					int trueTrackMadeGoodDegreesStartCharPos5287 = getCharPositionInLine();
					mNUMBER(); 
					trueTrackMadeGoodDegrees = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, trueTrackMadeGoodDegreesStart5287, getCharIndex()-1);
					trueTrackMadeGoodDegrees.setLine(trueTrackMadeGoodDegreesStartLine5287);
					trueTrackMadeGoodDegrees.setCharPositionInLine(trueTrackMadeGoodDegreesStartCharPos5287);

					}
					break;

				default :
					break loop201;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1175:13: ( LETTERS )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1175:14: LETTERS
			{
			mLETTERS(); 

			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1176:13: ( ' ' )*
			loop202:
			while (true) {
				int alt202=2;
				int LA202_0 = input.LA(1);
				if ( (LA202_0==' ') ) {
					alt202=1;
				}

				switch (alt202) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1176:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop202;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1176:18: (magneticTrackMadeGood= NUMBER )*
			loop203:
			while (true) {
				int alt203=2;
				int LA203_0 = input.LA(1);
				if ( (LA203_0=='.'||(LA203_0 >= '0' && LA203_0 <= '9')) ) {
					alt203=1;
				}

				switch (alt203) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1176:19: magneticTrackMadeGood= NUMBER
					{
					int magneticTrackMadeGoodStart5331 = getCharIndex();
					int magneticTrackMadeGoodStartLine5331 = getLine();
					int magneticTrackMadeGoodStartCharPos5331 = getCharPositionInLine();
					mNUMBER(); 
					magneticTrackMadeGood = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, magneticTrackMadeGoodStart5331, getCharIndex()-1);
					magneticTrackMadeGood.setLine(magneticTrackMadeGoodStartLine5331);
					magneticTrackMadeGood.setCharPositionInLine(magneticTrackMadeGoodStartCharPos5331);

					}
					break;

				default :
					break loop203;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1177:13: ( LETTERS )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1177:14: LETTERS
			{
			mLETTERS(); 

			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1178:13: ( ' ' )*
			loop204:
			while (true) {
				int alt204=2;
				int LA204_0 = input.LA(1);
				if ( (LA204_0==' ') ) {
					alt204=1;
				}

				switch (alt204) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1178:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop204;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1178:18: (groundSpeedKnots= NUMBER )*
			loop205:
			while (true) {
				int alt205=2;
				int LA205_0 = input.LA(1);
				if ( (LA205_0=='.'||(LA205_0 >= '0' && LA205_0 <= '9')) ) {
					alt205=1;
				}

				switch (alt205) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1178:19: groundSpeedKnots= NUMBER
					{
					int groundSpeedKnotsStart5375 = getCharIndex();
					int groundSpeedKnotsStartLine5375 = getLine();
					int groundSpeedKnotsStartCharPos5375 = getCharPositionInLine();
					mNUMBER(); 
					groundSpeedKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, groundSpeedKnotsStart5375, getCharIndex()-1);
					groundSpeedKnots.setLine(groundSpeedKnotsStartLine5375);
					groundSpeedKnots.setCharPositionInLine(groundSpeedKnotsStartCharPos5375);

					}
					break;

				default :
					break loop205;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1179:13: ( LETTERS )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1179:14: LETTERS
			{
			mLETTERS(); 

			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1180:13: ( ' ' )*
			loop206:
			while (true) {
				int alt206=2;
				int LA206_0 = input.LA(1);
				if ( (LA206_0==' ') ) {
					alt206=1;
				}

				switch (alt206) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1180:13: ' '
					{
					match(' '); 
					}
					break;

				default :
					break loop206;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1180:18: (groundSpeedKph= NUMBER )*
			loop207:
			while (true) {
				int alt207=2;
				int LA207_0 = input.LA(1);
				if ( (LA207_0=='.'||(LA207_0 >= '0' && LA207_0 <= '9')) ) {
					alt207=1;
				}

				switch (alt207) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1180:19: groundSpeedKph= NUMBER
					{
					int groundSpeedKphStart5419 = getCharIndex();
					int groundSpeedKphStartLine5419 = getLine();
					int groundSpeedKphStartCharPos5419 = getCharPositionInLine();
					mNUMBER(); 
					groundSpeedKph = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, groundSpeedKphStart5419, getCharIndex()-1);
					groundSpeedKph.setLine(groundSpeedKphStartLine5419);
					groundSpeedKph.setCharPositionInLine(groundSpeedKphStartCharPos5419);

					}
					break;

				default :
					break loop207;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1181:13: ( LETTERS SEP )*
			loop208:
			while (true) {
				int alt208=2;
				alt208 = dfa208.predict(input);
				switch (alt208) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1181:14: LETTERS SEP
					{
					mLETTERS(); 

					mSEP(); 

					}
					break;

				default :
					break loop208;
				}
			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1182:13: ( LETTERS )*
			loop209:
			while (true) {
				int alt209=2;
				int LA209_0 = input.LA(1);
				if ( (LA209_0==' '||(LA209_0 >= 'A' && LA209_0 <= 'Z')||(LA209_0 >= 'a' && LA209_0 <= 'z')) ) {
					alt209=1;
				}

				switch (alt209) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1182:13: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop209;
				}
			}

			int checksumStart5473 = getCharIndex();
			int checksumStartLine5473 = getLine();
			int checksumStartCharPos5473 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5473, getCharIndex()-1);
			checksum.setLine(checksumStartLine5473);
			checksum.setCharPositionInLine(checksumStartCharPos5473);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1199:6: ( '$' device= DEVICE 'VWR' SEP (windDirectionMagnitude= NUMBER )* SEP windDirectionOfBow= LETTERS SEP (speedInKnots= NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1199:8: '$' device= DEVICE 'VWR' SEP (windDirectionMagnitude= NUMBER )* SEP windDirectionOfBow= LETTERS SEP (speedInKnots= NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP LETTERS SEP ( NUMBER )* SEP ( LETTERS )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5503 = getCharIndex();
			int deviceStartLine5503 = getLine();
			int deviceStartCharPos5503 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5503, getCharIndex()-1);
			device.setLine(deviceStartLine5503);
			device.setCharPositionInLine(deviceStartCharPos5503);

			match("VWR"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1200:27: (windDirectionMagnitude= NUMBER )*
			loop210:
			while (true) {
				int alt210=2;
				int LA210_0 = input.LA(1);
				if ( (LA210_0=='.'||(LA210_0 >= '0' && LA210_0 <= '9')) ) {
					alt210=1;
				}

				switch (alt210) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1200:27: windDirectionMagnitude= NUMBER
					{
					int windDirectionMagnitudeStart5516 = getCharIndex();
					int windDirectionMagnitudeStartLine5516 = getLine();
					int windDirectionMagnitudeStartCharPos5516 = getCharPositionInLine();
					mNUMBER(); 
					windDirectionMagnitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionMagnitudeStart5516, getCharIndex()-1);
					windDirectionMagnitude.setLine(windDirectionMagnitudeStartLine5516);
					windDirectionMagnitude.setCharPositionInLine(windDirectionMagnitudeStartCharPos5516);

					}
					break;

				default :
					break loop210;
				}
			}

			mSEP(); 

			int windDirectionOfBowStart5528 = getCharIndex();
			int windDirectionOfBowStartLine5528 = getLine();
			int windDirectionOfBowStartCharPos5528 = getCharPositionInLine();
			mLETTERS(); 
			windDirectionOfBow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, windDirectionOfBowStart5528, getCharIndex()-1);
			windDirectionOfBow.setLine(windDirectionOfBowStartLine5528);
			windDirectionOfBow.setCharPositionInLine(windDirectionOfBowStartCharPos5528);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1202:16: (speedInKnots= NUMBER )*
			loop211:
			while (true) {
				int alt211=2;
				int LA211_0 = input.LA(1);
				if ( (LA211_0=='.'||(LA211_0 >= '0' && LA211_0 <= '9')) ) {
					alt211=1;
				}

				switch (alt211) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1202:16: speedInKnots= NUMBER
					{
					int speedInKnotsStart5538 = getCharIndex();
					int speedInKnotsStartLine5538 = getLine();
					int speedInKnotsStartCharPos5538 = getCharPositionInLine();
					mNUMBER(); 
					speedInKnots = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedInKnotsStart5538, getCharIndex()-1);
					speedInKnots.setLine(speedInKnotsStartLine5538);
					speedInKnots.setCharPositionInLine(speedInKnotsStartCharPos5538);

					}
					break;

				default :
					break loop211;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1204:3: ( NUMBER )*
			loop212:
			while (true) {
				int alt212=2;
				int LA212_0 = input.LA(1);
				if ( (LA212_0=='.'||(LA212_0 >= '0' && LA212_0 <= '9')) ) {
					alt212=1;
				}

				switch (alt212) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1204:3: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop212;
				}
			}

			mSEP(); 

			mLETTERS(); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1206:3: ( NUMBER )*
			loop213:
			while (true) {
				int alt213=2;
				int LA213_0 = input.LA(1);
				if ( (LA213_0=='.'||(LA213_0 >= '0' && LA213_0 <= '9')) ) {
					alt213=1;
				}

				switch (alt213) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1206:3: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop213;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1207:3: ( LETTERS )*
			loop214:
			while (true) {
				int alt214=2;
				int LA214_0 = input.LA(1);
				if ( (LA214_0==' '||(LA214_0 >= 'A' && LA214_0 <= 'Z')||(LA214_0 >= 'a' && LA214_0 <= 'z')) ) {
					alt214=1;
				}

				switch (alt214) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1207:3: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop214;
				}
			}

			int checksumStart5579 = getCharIndex();
			int checksumStartLine5579 = getLine();
			int checksumStartCharPos5579 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5579, getCharIndex()-1);
			checksum.setLine(checksumStartLine5579);
			checksum.setCharPositionInLine(checksumStartCharPos5579);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1224:5: ( '$' device= DEVICE 'VWT' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1224:7: '$' device= DEVICE 'VWT' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5602 = getCharIndex();
			int deviceStartLine5602 = getLine();
			int deviceStartCharPos5602 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5602, getCharIndex()-1);
			device.setLine(deviceStartLine5602);
			device.setCharPositionInLine(deviceStartCharPos5602);

			match("VWT"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1225:2: ( '\\u0021' .. '\\u007F' )+
			int cnt215=0;
			loop215:
			while (true) {
				int alt215=2;
				alt215 = dfa215.predict(input);
				switch (alt215) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(215, input);
					throw eee;
				}
				cnt215++;
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1226:5: ( NUMBER )*
			loop216:
			while (true) {
				int alt216=2;
				int LA216_0 = input.LA(1);
				if ( (LA216_0=='.'||(LA216_0 >= '0' && LA216_0 <= '9')) ) {
					alt216=1;
				}

				switch (alt216) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1226:5: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop216;
				}
			}

			int checksumStart5632 = getCharIndex();
			int checksumStartLine5632 = getLine();
			int checksumStartCharPos5632 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5632, getCharIndex()-1);
			checksum.setLine(checksumStartLine5632);
			checksum.setCharPositionInLine(checksumStartCharPos5632);


				
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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1264:6: ( '$' device= DEVICE 'XTE' SEP generalWarning= LETTERS SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP directionToSteer= LETTERS SEP LETTERS checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1264:8: '$' device= DEVICE 'XTE' SEP generalWarning= LETTERS SEP status= LETTERS SEP (crossTrackError= NUMBER )* SEP directionToSteer= LETTERS SEP LETTERS checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5651 = getCharIndex();
			int deviceStartLine5651 = getLine();
			int deviceStartCharPos5651 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5651, getCharIndex()-1);
			device.setLine(deviceStartLine5651);
			device.setCharPositionInLine(deviceStartCharPos5651);

			match("XTE"); 

			mSEP(); 

			int generalWarningStart5660 = getCharIndex();
			int generalWarningStartLine5660 = getLine();
			int generalWarningStartCharPos5660 = getCharPositionInLine();
			mLETTERS(); 
			generalWarning = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, generalWarningStart5660, getCharIndex()-1);
			generalWarning.setLine(generalWarningStartLine5660);
			generalWarning.setCharPositionInLine(generalWarningStartCharPos5660);

			mSEP(); 

			int statusStart5667 = getCharIndex();
			int statusStartLine5667 = getLine();
			int statusStartCharPos5667 = getCharPositionInLine();
			mLETTERS(); 
			status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart5667, getCharIndex()-1);
			status.setLine(statusStartLine5667);
			status.setCharPositionInLine(statusStartCharPos5667);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1267:17: (crossTrackError= NUMBER )*
			loop217:
			while (true) {
				int alt217=2;
				int LA217_0 = input.LA(1);
				if ( (LA217_0=='.'||(LA217_0 >= '0' && LA217_0 <= '9')) ) {
					alt217=1;
				}

				switch (alt217) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1267:17: crossTrackError= NUMBER
					{
					int crossTrackErrorStart5674 = getCharIndex();
					int crossTrackErrorStartLine5674 = getLine();
					int crossTrackErrorStartCharPos5674 = getCharPositionInLine();
					mNUMBER(); 
					crossTrackError = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, crossTrackErrorStart5674, getCharIndex()-1);
					crossTrackError.setLine(crossTrackErrorStartLine5674);
					crossTrackError.setCharPositionInLine(crossTrackErrorStartCharPos5674);

					}
					break;

				default :
					break loop217;
				}
			}

			mSEP(); 

			int directionToSteerStart5682 = getCharIndex();
			int directionToSteerStartLine5682 = getLine();
			int directionToSteerStartCharPos5682 = getCharPositionInLine();
			mLETTERS(); 
			directionToSteer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, directionToSteerStart5682, getCharIndex()-1);
			directionToSteer.setLine(directionToSteerStartLine5682);
			directionToSteer.setCharPositionInLine(directionToSteerStartCharPos5682);

			mSEP(); 

			mLETTERS(); 

			int checksumStart5692 = getCharIndex();
			int checksumStartLine5692 = getLine();
			int checksumStartCharPos5692 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5692, getCharIndex()-1);
			checksum.setLine(checksumStartLine5692);
			checksum.setCharPositionInLine(checksumStartCharPos5692);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1267:6: ( '$' device= DEVICE 'ZDA' SEP utc= NUMBER SEP dd= NUMBER SEP mm= NUMBER SEP yy= NUMBER SEP ( NUMBER )* SEP ( NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1267:9: '$' device= DEVICE 'ZDA' SEP utc= NUMBER SEP dd= NUMBER SEP mm= NUMBER SEP yy= NUMBER SEP ( NUMBER )* SEP ( NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5715 = getCharIndex();
			int deviceStartLine5715 = getLine();
			int deviceStartCharPos5715 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5715, getCharIndex()-1);
			device.setLine(deviceStartLine5715);
			device.setCharPositionInLine(deviceStartCharPos5715);

			match("ZDA"); 

			mSEP(); 

			int utcStart5727 = getCharIndex();
			int utcStartLine5727 = getLine();
			int utcStartCharPos5727 = getCharPositionInLine();
			mNUMBER(); 
			utc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, utcStart5727, getCharIndex()-1);
			utc.setLine(utcStartLine5727);
			utc.setCharPositionInLine(utcStartCharPos5727);

			mSEP(); 

			int ddStart5737 = getCharIndex();
			int ddStartLine5737 = getLine();
			int ddStartCharPos5737 = getCharPositionInLine();
			mNUMBER(); 
			dd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ddStart5737, getCharIndex()-1);
			dd.setLine(ddStartLine5737);
			dd.setCharPositionInLine(ddStartCharPos5737);

			mSEP(); 

			int mmStart5747 = getCharIndex();
			int mmStartLine5747 = getLine();
			int mmStartCharPos5747 = getCharPositionInLine();
			mNUMBER(); 
			mm = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mmStart5747, getCharIndex()-1);
			mm.setLine(mmStartLine5747);
			mm.setCharPositionInLine(mmStartCharPos5747);

			mSEP(); 

			int yyStart5757 = getCharIndex();
			int yyStartLine5757 = getLine();
			int yyStartCharPos5757 = getCharPositionInLine();
			mNUMBER(); 
			yy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, yyStart5757, getCharIndex()-1);
			yy.setLine(yyStartLine5757);
			yy.setCharPositionInLine(yyStartCharPos5757);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1272:3: ( NUMBER )*
			loop218:
			while (true) {
				int alt218=2;
				int LA218_0 = input.LA(1);
				if ( (LA218_0=='.'||(LA218_0 >= '0' && LA218_0 <= '9')) ) {
					alt218=1;
				}

				switch (alt218) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1272:3: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop218;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1273:3: ( NUMBER )*
			loop219:
			while (true) {
				int alt219=2;
				int LA219_0 = input.LA(1);
				if ( (LA219_0=='.'||(LA219_0 >= '0' && LA219_0 <= '9')) ) {
					alt219=1;
				}

				switch (alt219) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1273:3: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop219;
				}
			}

			int checksumStart5778 = getCharIndex();
			int checksumStartLine5778 = getLine();
			int checksumStartCharPos5778 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5778, getCharIndex()-1);
			checksum.setLine(checksumStartLine5778);
			checksum.setCharPositionInLine(checksumStartCharPos5778);


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1289:5: ( '$' device= DEVICE 'ALR' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1289:7: '$' device= DEVICE 'ALR' SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
			{
			match('$'); 
			int deviceStart5796 = getCharIndex();
			int deviceStartLine5796 = getLine();
			int deviceStartCharPos5796 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5796, getCharIndex()-1);
			device.setLine(deviceStartLine5796);
			device.setCharPositionInLine(deviceStartCharPos5796);

			match("ALR"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1290:2: ( '\\u0021' .. '\\u007F' )+
			int cnt220=0;
			loop220:
			while (true) {
				int alt220=2;
				alt220 = dfa220.predict(input);
				switch (alt220) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(220, input);
					throw eee;
				}
				cnt220++;
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1291:5: ( NUMBER )*
			loop221:
			while (true) {
				int alt221=2;
				int LA221_0 = input.LA(1);
				if ( (LA221_0=='.'||(LA221_0 >= '0' && LA221_0 <= '9')) ) {
					alt221=1;
				}

				switch (alt221) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1291:5: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop221;
				}
			}

			int checksumStart5826 = getCharIndex();
			int checksumStartLine5826 = getLine();
			int checksumStartCharPos5826 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5826, getCharIndex()-1);
			checksum.setLine(checksumStartLine5826);
			checksum.setCharPositionInLine(checksumStartCharPos5826);


				
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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1294:6: ( '!' device= DEVICE 'VDM' SEP ( NUMBER )* SEP ( NUMBER )* SEP ( NUMBER )* SEP ( LETTERS )* SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1294:8: '!' device= DEVICE 'VDM' SEP ( NUMBER )* SEP ( NUMBER )* SEP ( NUMBER )* SEP ( LETTERS )* SEP ( '\\u0021' .. '\\u007F' )+ SEP ( NUMBER )* checksum= CHECKSUM
			{
			match('!'); 
			int deviceStart5843 = getCharIndex();
			int deviceStartLine5843 = getLine();
			int deviceStartCharPos5843 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart5843, getCharIndex()-1);
			device.setLine(deviceStartLine5843);
			device.setCharPositionInLine(deviceStartCharPos5843);

			match("VDM"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1295:4: ( NUMBER )*
			loop222:
			while (true) {
				int alt222=2;
				int LA222_0 = input.LA(1);
				if ( (LA222_0=='.'||(LA222_0 >= '0' && LA222_0 <= '9')) ) {
					alt222=1;
				}

				switch (alt222) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1295:4: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop222;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1296:4: ( NUMBER )*
			loop223:
			while (true) {
				int alt223=2;
				int LA223_0 = input.LA(1);
				if ( (LA223_0=='.'||(LA223_0 >= '0' && LA223_0 <= '9')) ) {
					alt223=1;
				}

				switch (alt223) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1296:4: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop223;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1297:4: ( NUMBER )*
			loop224:
			while (true) {
				int alt224=2;
				int LA224_0 = input.LA(1);
				if ( (LA224_0=='.'||(LA224_0 >= '0' && LA224_0 <= '9')) ) {
					alt224=1;
				}

				switch (alt224) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1297:4: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop224;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1298:4: ( LETTERS )*
			loop225:
			while (true) {
				int alt225=2;
				int LA225_0 = input.LA(1);
				if ( (LA225_0==' '||(LA225_0 >= 'A' && LA225_0 <= 'Z')||(LA225_0 >= 'a' && LA225_0 <= 'z')) ) {
					alt225=1;
				}

				switch (alt225) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1298:4: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop225;
				}
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1299:4: ( '\\u0021' .. '\\u007F' )+
			int cnt226=0;
			loop226:
			while (true) {
				int alt226=2;
				alt226 = dfa226.predict(input);
				switch (alt226) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(226, input);
					throw eee;
				}
				cnt226++;
			}

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1300:5: ( NUMBER )*
			loop227:
			while (true) {
				int alt227=2;
				int LA227_0 = input.LA(1);
				if ( (LA227_0=='.'||(LA227_0 >= '0' && LA227_0 <= '9')) ) {
					alt227=1;
				}

				switch (alt227) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1300:5: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop227;
				}
			}

			int checksumStart5907 = getCharIndex();
			int checksumStartLine5907 = getLine();
			int checksumStartCharPos5907 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart5907, getCharIndex()-1);
			checksum.setLine(checksumStartLine5907);
			checksum.setCharPositionInLine(checksumStartCharPos5907);


				aisParser.parse(getText().trim());
				
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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1315:10: ( '{\"class\":\"AIS\"' SEP '\"device\":' dev= DEV SEP '\"type\":' type= NUMBER SEP '\"repeat\":' repeat= NUMBER SEP '\"mmsi\":' mmsi= NUMBER SEP '\"scaled\":' scaled= LETTERS SEP ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER '}' | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER ) ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1315:12: '{\"class\":\"AIS\"' SEP '\"device\":' dev= DEV SEP '\"type\":' type= NUMBER SEP '\"repeat\":' repeat= NUMBER SEP '\"mmsi\":' mmsi= NUMBER SEP '\"scaled\":' scaled= LETTERS SEP ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER '}' | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER ) ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )*
			{
			match("{\"class\":\"AIS\""); 

			mSEP(); 

			match("\"device\":"); 

			int devStart5937 = getCharIndex();
			int devStartLine5937 = getLine();
			int devStartCharPos5937 = getCharPositionInLine();
			mDEV(); 
			dev = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, devStart5937, getCharIndex()-1);
			dev.setLine(devStartLine5937);
			dev.setCharPositionInLine(devStartCharPos5937);

			mSEP(); 

			match("\"type\":"); 

			int typeStart5953 = getCharIndex();
			int typeStartLine5953 = getLine();
			int typeStartCharPos5953 = getCharPositionInLine();
			mNUMBER(); 
			type = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, typeStart5953, getCharIndex()-1);
			type.setLine(typeStartLine5953);
			type.setCharPositionInLine(typeStartCharPos5953);

			mSEP(); 

			match("\"repeat\":"); 

			int repeatStart5968 = getCharIndex();
			int repeatStartLine5968 = getLine();
			int repeatStartCharPos5968 = getCharPositionInLine();
			mNUMBER(); 
			repeat = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, repeatStart5968, getCharIndex()-1);
			repeat.setLine(repeatStartLine5968);
			repeat.setCharPositionInLine(repeatStartCharPos5968);

			mSEP(); 

			match("\"mmsi\":"); 

			int mmsiStart5983 = getCharIndex();
			int mmsiStartLine5983 = getLine();
			int mmsiStartCharPos5983 = getCharPositionInLine();
			mNUMBER(); 
			mmsi = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mmsiStart5983, getCharIndex()-1);
			mmsi.setLine(mmsiStartLine5983);
			mmsi.setCharPositionInLine(mmsiStartCharPos5983);

			mSEP(); 

			match("\"scaled\":"); 

			int scaledStart5996 = getCharIndex();
			int scaledStartLine5996 = getLine();
			int scaledStartCharPos5996 = getCharPositionInLine();
			mLETTERS(); 
			scaled = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, scaledStart5996, getCharIndex()-1);
			scaled.setLine(scaledStartLine5996);
			scaled.setCharPositionInLine(scaledStartCharPos5996);

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1322:6: ( '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER '}' | '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER | '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER | '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER | '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER | '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER )
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
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 230, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 230, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 230, 0, input);
				throw nvae;
			}

			switch (alt230) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1322:7: '\"status\":' status= NUMBER SEP '\"turn\":' turn= SIGNED SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"maneuver\":' maneuvrer= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER '}'
					{
					match("\"status\":"); 

					int statusStart6016 = getCharIndex();
					int statusStartLine6016 = getLine();
					int statusStartCharPos6016 = getCharPositionInLine();
					mNUMBER(); 
					status = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, statusStart6016, getCharIndex()-1);
					status.setLine(statusStartLine6016);
					status.setCharPositionInLine(statusStartCharPos6016);

					mSEP(); 

					match("\"turn\":"); 

					int turnStart6031 = getCharIndex();
					int turnStartLine6031 = getLine();
					int turnStartCharPos6031 = getCharPositionInLine();
					mSIGNED(); 
					turn = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, turnStart6031, getCharIndex()-1);
					turn.setLine(turnStartLine6031);
					turn.setCharPositionInLine(turnStartCharPos6031);

					mSEP(); 

					match("\"speed\":"); 

					int speedStart6046 = getCharIndex();
					int speedStartLine6046 = getLine();
					int speedStartCharPos6046 = getCharPositionInLine();
					mNUMBER(); 
					speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart6046, getCharIndex()-1);
					speed.setLine(speedStartLine6046);
					speed.setCharPositionInLine(speedStartCharPos6046);

					mSEP(); 

					match("\"accuracy\":"); 

					int accuracyStart6061 = getCharIndex();
					int accuracyStartLine6061 = getLine();
					int accuracyStartCharPos6061 = getCharPositionInLine();
					mLETTERS(); 
					accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6061, getCharIndex()-1);
					accuracy.setLine(accuracyStartLine6061);
					accuracy.setCharPositionInLine(accuracyStartCharPos6061);

					mSEP(); 

					match("\"lon\":"); 

					int longitudeStart6075 = getCharIndex();
					int longitudeStartLine6075 = getLine();
					int longitudeStartCharPos6075 = getCharPositionInLine();
					mSIGNED(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6075, getCharIndex()-1);
					longitude.setLine(longitudeStartLine6075);
					longitude.setCharPositionInLine(longitudeStartCharPos6075);

					mSEP(); 

					match("\"lat\":"); 

					int latitudeStart6090 = getCharIndex();
					int latitudeStartLine6090 = getLine();
					int latitudeStartCharPos6090 = getCharPositionInLine();
					mSIGNED(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6090, getCharIndex()-1);
					latitude.setLine(latitudeStartLine6090);
					latitude.setCharPositionInLine(latitudeStartCharPos6090);

					mSEP(); 

					match("\"course\":"); 

					int courseStart6105 = getCharIndex();
					int courseStartLine6105 = getLine();
					int courseStartCharPos6105 = getCharPositionInLine();
					mNUMBER(); 
					course = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, courseStart6105, getCharIndex()-1);
					course.setLine(courseStartLine6105);
					course.setCharPositionInLine(courseStartCharPos6105);

					mSEP(); 

					match("\"heading\":"); 

					int headingStart6119 = getCharIndex();
					int headingStartLine6119 = getLine();
					int headingStartCharPos6119 = getCharPositionInLine();
					mNUMBER(); 
					heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart6119, getCharIndex()-1);
					heading.setLine(headingStartLine6119);
					heading.setCharPositionInLine(headingStartCharPos6119);

					mSEP(); 

					match("\"second\":"); 

					int secondStart6133 = getCharIndex();
					int secondStartLine6133 = getLine();
					int secondStartCharPos6133 = getCharPositionInLine();
					mNUMBER(); 
					second = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, secondStart6133, getCharIndex()-1);
					second.setLine(secondStartLine6133);
					second.setCharPositionInLine(secondStartCharPos6133);

					mSEP(); 

					match("\"maneuver\":"); 

					int maneuvrerStart6148 = getCharIndex();
					int maneuvrerStartLine6148 = getLine();
					int maneuvrerStartCharPos6148 = getCharPositionInLine();
					mNUMBER(); 
					maneuvrer = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, maneuvrerStart6148, getCharIndex()-1);
					maneuvrer.setLine(maneuvrerStartLine6148);
					maneuvrer.setCharPositionInLine(maneuvrerStartCharPos6148);

					mSEP(); 

					match("\"raim\":"); 

					int raimStart6163 = getCharIndex();
					int raimStartLine6163 = getLine();
					int raimStartCharPos6163 = getCharPositionInLine();
					mLETTERS(); 
					raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart6163, getCharIndex()-1);
					raim.setLine(raimStartLine6163);
					raim.setCharPositionInLine(raimStartCharPos6163);

					mSEP(); 

					match("\"radio\":"); 

					int radioStart6178 = getCharIndex();
					int radioStartLine6178 = getLine();
					int radioStartCharPos6178 = getCharPositionInLine();
					mNUMBER(); 
					radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart6178, getCharIndex()-1);
					radio.setLine(radioStartLine6178);
					radio.setCharPositionInLine(radioStartCharPos6178);

					match('}'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1337:6: '\"timestamp\":' timestamp= TIME_STAMP SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"epfd\":' epfd= NUMBER SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER
					{
					match("\"timestamp\":"); 

					int timestampStart6212 = getCharIndex();
					int timestampStartLine6212 = getLine();
					int timestampStartCharPos6212 = getCharPositionInLine();
					mTIME_STAMP(); 
					timestamp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, timestampStart6212, getCharIndex()-1);
					timestamp.setLine(timestampStartLine6212);
					timestamp.setCharPositionInLine(timestampStartCharPos6212);

					mSEP(); 

					match("\"accuracy\":"); 

					int accuracyStart6226 = getCharIndex();
					int accuracyStartLine6226 = getLine();
					int accuracyStartCharPos6226 = getCharPositionInLine();
					mLETTERS(); 
					accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6226, getCharIndex()-1);
					accuracy.setLine(accuracyStartLine6226);
					accuracy.setCharPositionInLine(accuracyStartCharPos6226);

					mSEP(); 

					match("\"lon\":"); 

					int longitudeStart6240 = getCharIndex();
					int longitudeStartLine6240 = getLine();
					int longitudeStartCharPos6240 = getCharPositionInLine();
					mSIGNED(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6240, getCharIndex()-1);
					longitude.setLine(longitudeStartLine6240);
					longitude.setCharPositionInLine(longitudeStartCharPos6240);

					mSEP(); 

					match("\"lat\":"); 

					int latitudeStart6254 = getCharIndex();
					int latitudeStartLine6254 = getLine();
					int latitudeStartCharPos6254 = getCharPositionInLine();
					mSIGNED(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6254, getCharIndex()-1);
					latitude.setLine(latitudeStartLine6254);
					latitude.setCharPositionInLine(latitudeStartCharPos6254);

					mSEP(); 

					match("\"epfd\":"); 

					int epfdStart6267 = getCharIndex();
					int epfdStartLine6267 = getLine();
					int epfdStartCharPos6267 = getCharPositionInLine();
					mNUMBER(); 
					epfd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, epfdStart6267, getCharIndex()-1);
					epfd.setLine(epfdStartLine6267);
					epfd.setCharPositionInLine(epfdStartCharPos6267);

					mSEP(); 

					match("\"raim\":"); 

					int raimStart6281 = getCharIndex();
					int raimStartLine6281 = getLine();
					int raimStartCharPos6281 = getCharPositionInLine();
					mLETTERS(); 
					raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart6281, getCharIndex()-1);
					raim.setLine(raimStartLine6281);
					raim.setCharPositionInLine(raimStartCharPos6281);

					mSEP(); 

					match("\"radio\":"); 

					int radioStart6295 = getCharIndex();
					int radioStartLine6295 = getLine();
					int radioStartCharPos6295 = getCharPositionInLine();
					mNUMBER(); 
					radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart6295, getCharIndex()-1);
					radio.setLine(radioStartLine6295);
					radio.setCharPositionInLine(radioStartCharPos6295);

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1346:6: '\"imo\":' imo= NUMBER SEP '\"ais_version\":' ais_version= NUMBER SEP '\"callsign\":' callsign= NAME SEP '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER SEP '\"epfd\":' epfd= NUMBER SEP '\"eta\":' eta= TIME_STAMP SEP '\"draught\":' draught= NUMBER SEP '\"destination\":' destination= NAME SEP '\"dte\":' dte= NUMBER
					{
					match("\"imo\":"); 

					int imoStart6319 = getCharIndex();
					int imoStartLine6319 = getLine();
					int imoStartCharPos6319 = getCharPositionInLine();
					mNUMBER(); 
					imo = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, imoStart6319, getCharIndex()-1);
					imo.setLine(imoStartLine6319);
					imo.setCharPositionInLine(imoStartCharPos6319);

					mSEP(); 

					match("\"ais_version\":"); 

					int ais_versionStart6332 = getCharIndex();
					int ais_versionStartLine6332 = getLine();
					int ais_versionStartCharPos6332 = getCharPositionInLine();
					mNUMBER(); 
					ais_version = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, ais_versionStart6332, getCharIndex()-1);
					ais_version.setLine(ais_versionStartLine6332);
					ais_version.setCharPositionInLine(ais_versionStartCharPos6332);

					mSEP(); 

					match("\"callsign\":"); 

					int callsignStart6346 = getCharIndex();
					int callsignStartLine6346 = getLine();
					int callsignStartCharPos6346 = getCharPositionInLine();
					mNAME(); 
					callsign = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, callsignStart6346, getCharIndex()-1);
					callsign.setLine(callsignStartLine6346);
					callsign.setCharPositionInLine(callsignStartCharPos6346);

					mSEP(); 

					match("\"shipname\":"); 

					int shipnameStart6360 = getCharIndex();
					int shipnameStartLine6360 = getLine();
					int shipnameStartCharPos6360 = getCharPositionInLine();
					mNAME(); 
					shipname = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shipnameStart6360, getCharIndex()-1);
					shipname.setLine(shipnameStartLine6360);
					shipname.setCharPositionInLine(shipnameStartCharPos6360);

					mSEP(); 

					match("\"shiptype\":"); 

					int shiptypeStart6373 = getCharIndex();
					int shiptypeStartLine6373 = getLine();
					int shiptypeStartCharPos6373 = getCharPositionInLine();
					mNUMBER(); 
					shiptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shiptypeStart6373, getCharIndex()-1);
					shiptype.setLine(shiptypeStartLine6373);
					shiptype.setCharPositionInLine(shiptypeStartCharPos6373);

					mSEP(); 

					match("\"to_bow\":"); 

					int to_bowStart6386 = getCharIndex();
					int to_bowStartLine6386 = getLine();
					int to_bowStartCharPos6386 = getCharPositionInLine();
					mNUMBER(); 
					to_bow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_bowStart6386, getCharIndex()-1);
					to_bow.setLine(to_bowStartLine6386);
					to_bow.setCharPositionInLine(to_bowStartCharPos6386);

					mSEP(); 

					match("\"to_stern\":"); 

					int to_sternStart6399 = getCharIndex();
					int to_sternStartLine6399 = getLine();
					int to_sternStartCharPos6399 = getCharPositionInLine();
					mNUMBER(); 
					to_stern = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_sternStart6399, getCharIndex()-1);
					to_stern.setLine(to_sternStartLine6399);
					to_stern.setCharPositionInLine(to_sternStartCharPos6399);

					mSEP(); 

					match("\"to_port\":"); 

					int to_portStart6412 = getCharIndex();
					int to_portStartLine6412 = getLine();
					int to_portStartCharPos6412 = getCharPositionInLine();
					mNUMBER(); 
					to_port = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_portStart6412, getCharIndex()-1);
					to_port.setLine(to_portStartLine6412);
					to_port.setCharPositionInLine(to_portStartCharPos6412);

					mSEP(); 

					match("\"to_starboard\":"); 

					int to_starboardStart6425 = getCharIndex();
					int to_starboardStartLine6425 = getLine();
					int to_starboardStartCharPos6425 = getCharPositionInLine();
					mNUMBER(); 
					to_starboard = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_starboardStart6425, getCharIndex()-1);
					to_starboard.setLine(to_starboardStartLine6425);
					to_starboard.setCharPositionInLine(to_starboardStartCharPos6425);

					mSEP(); 

					match("\"epfd\":"); 

					int epfdStart6438 = getCharIndex();
					int epfdStartLine6438 = getLine();
					int epfdStartCharPos6438 = getCharPositionInLine();
					mNUMBER(); 
					epfd = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, epfdStart6438, getCharIndex()-1);
					epfd.setLine(epfdStartLine6438);
					epfd.setCharPositionInLine(epfdStartCharPos6438);

					mSEP(); 

					match("\"eta\":"); 

					int etaStart6451 = getCharIndex();
					int etaStartLine6451 = getLine();
					int etaStartCharPos6451 = getCharPositionInLine();
					mTIME_STAMP(); 
					eta = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, etaStart6451, getCharIndex()-1);
					eta.setLine(etaStartLine6451);
					eta.setCharPositionInLine(etaStartCharPos6451);

					mSEP(); 

					match("\"draught\":"); 

					int draughtStart6464 = getCharIndex();
					int draughtStartLine6464 = getLine();
					int draughtStartCharPos6464 = getCharPositionInLine();
					mNUMBER(); 
					draught = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, draughtStart6464, getCharIndex()-1);
					draught.setLine(draughtStartLine6464);
					draught.setCharPositionInLine(draughtStartCharPos6464);

					mSEP(); 

					match("\"destination\":"); 

					int destinationStart6478 = getCharIndex();
					int destinationStartLine6478 = getLine();
					int destinationStartCharPos6478 = getCharPositionInLine();
					mNAME(); 
					destination = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, destinationStart6478, getCharIndex()-1);
					destination.setLine(destinationStartLine6478);
					destination.setCharPositionInLine(destinationStartCharPos6478);

					mSEP(); 

					match("\"dte\":"); 

					int dteStart6493 = getCharIndex();
					int dteStartLine6493 = getLine();
					int dteStartCharPos6493 = getCharPositionInLine();
					mNUMBER(); 
					dte = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dteStart6493, getCharIndex()-1);
					dte.setLine(dteStartLine6493);
					dte.setCharPositionInLine(dteStartCharPos6493);

					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1362:6: '\"seqno\":' NUMBER SEP '\"dest_mmsi\":' NUMBER SEP '\"retransmit\":' LETTERS SEP '\"dac\":' NUMBER SEP '\"fid\":' NUMBER SEP '\"off_pos\":' LETTERS SEP '\"alarm\":' LETTERS SEP '\"stat_ext\":' NUMBER SEP '\"ana_int\":' NUMBER SEP '\"ana_ext1\":' NUMBER SEP '\"ana_ext2\":' NUMBER SEP '\"racon\":' NUMBER SEP '\"light\":' NUMBER
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1377:6: '\"dac\":' dac= NUMBER SEP '\"fid\":' fid= NUMBER SEP '\"lat\":' latitude= SIGNED SEP '\"lon\":' longitude= SIGNED SEP '\"accuracy\":' accuracy= LETTERS SEP '\"timestamp\":' timestamp= TIME_STAMP SEP '\"wspeed\":' wspeed= NUMBER SEP '\"wgust\":' wgust= NUMBER SEP '\"wdir\":' wdir= NUMBER SEP '\"wgustdir\":' wgustdir= NUMBER SEP '\"humidity\":' humidity= NUMBER SEP '\"airtemp\":' airtemp= SIGNED SEP '\"dewpoint\":' dewpoint= SIGNED SEP '\"pressure\":' pressure= NUMBER SEP '\"pressuretend\":' pressuretend= NUMBER SEP '\"visgreater\":' visgreater= LETTERS SEP '\"visibility\":' visibility= NUMBER SEP '\"waterlevel\":' waterlevel= SIGNED SEP '\"leveltrend\":' leveltrend= NUMBER SEP '\"cspeed\":' cspeed= NUMBER SEP '\"cdir\":' cdir= NUMBER SEP '\"cspeed2\":' cspeed2= NUMBER SEP '\"cdir2\":' cdir2= NUMBER SEP '\"cdepth2\":' cdepth2= NUMBER SEP '\"cspeed3\":' cspeed3= NUMBER SEP '\"cdir3\":' cdir3= NUMBER SEP '\"cdepth3\":' (cdepth3= NUMBER | '\\n' ) SEP '\"waveheight\":' waveheight= NUMBER SEP '\"waveperiod\":' waveperiod= NUMBER SEP '\"wavedir\":' wavedir= NUMBER SEP '\"swellheight\":' swellheight= NUMBER SEP '\"swellperiod\":' swellperiod= NUMBER SEP '\"swelldir\":' swelldir= NUMBER SEP '\"seastate\":' seastate= NUMBER SEP '\"watertemp\":' watertemp= SIGNED SEP '\"preciptype\":' preciptype= NUMBER SEP '\"salinity\":' salinity= NUMBER SEP '\"ice\":' ice= NUMBER
					{
					match("\"dac\":"); 

					int dacStart6681 = getCharIndex();
					int dacStartLine6681 = getLine();
					int dacStartCharPos6681 = getCharPositionInLine();
					mNUMBER(); 
					dac = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dacStart6681, getCharIndex()-1);
					dac.setLine(dacStartLine6681);
					dac.setCharPositionInLine(dacStartCharPos6681);

					mSEP(); 

					match("\"fid\":"); 

					int fidStart6695 = getCharIndex();
					int fidStartLine6695 = getLine();
					int fidStartCharPos6695 = getCharPositionInLine();
					mNUMBER(); 
					fid = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fidStart6695, getCharIndex()-1);
					fid.setLine(fidStartLine6695);
					fid.setCharPositionInLine(fidStartCharPos6695);

					mSEP(); 

					match("\"lat\":"); 

					int latitudeStart6709 = getCharIndex();
					int latitudeStartLine6709 = getLine();
					int latitudeStartCharPos6709 = getCharPositionInLine();
					mSIGNED(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart6709, getCharIndex()-1);
					latitude.setLine(latitudeStartLine6709);
					latitude.setCharPositionInLine(latitudeStartCharPos6709);

					mSEP(); 

					match("\"lon\":"); 

					int longitudeStart6723 = getCharIndex();
					int longitudeStartLine6723 = getLine();
					int longitudeStartCharPos6723 = getCharPositionInLine();
					mSIGNED(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart6723, getCharIndex()-1);
					longitude.setLine(longitudeStartLine6723);
					longitude.setCharPositionInLine(longitudeStartCharPos6723);

					mSEP(); 

					match("\"accuracy\":"); 

					int accuracyStart6737 = getCharIndex();
					int accuracyStartLine6737 = getLine();
					int accuracyStartCharPos6737 = getCharPositionInLine();
					mLETTERS(); 
					accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart6737, getCharIndex()-1);
					accuracy.setLine(accuracyStartLine6737);
					accuracy.setCharPositionInLine(accuracyStartCharPos6737);

					mSEP(); 

					match("\"timestamp\":"); 

					int timestampStart6750 = getCharIndex();
					int timestampStartLine6750 = getLine();
					int timestampStartCharPos6750 = getCharPositionInLine();
					mTIME_STAMP(); 
					timestamp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, timestampStart6750, getCharIndex()-1);
					timestamp.setLine(timestampStartLine6750);
					timestamp.setCharPositionInLine(timestampStartCharPos6750);

					mSEP(); 

					match("\"wspeed\":"); 

					int wspeedStart6764 = getCharIndex();
					int wspeedStartLine6764 = getLine();
					int wspeedStartCharPos6764 = getCharPositionInLine();
					mNUMBER(); 
					wspeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wspeedStart6764, getCharIndex()-1);
					wspeed.setLine(wspeedStartLine6764);
					wspeed.setCharPositionInLine(wspeedStartCharPos6764);

					mSEP(); 

					match("\"wgust\":"); 

					int wgustStart6778 = getCharIndex();
					int wgustStartLine6778 = getLine();
					int wgustStartCharPos6778 = getCharPositionInLine();
					mNUMBER(); 
					wgust = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wgustStart6778, getCharIndex()-1);
					wgust.setLine(wgustStartLine6778);
					wgust.setCharPositionInLine(wgustStartCharPos6778);

					mSEP(); 

					match("\"wdir\":"); 

					int wdirStart6792 = getCharIndex();
					int wdirStartLine6792 = getLine();
					int wdirStartCharPos6792 = getCharPositionInLine();
					mNUMBER(); 
					wdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wdirStart6792, getCharIndex()-1);
					wdir.setLine(wdirStartLine6792);
					wdir.setCharPositionInLine(wdirStartCharPos6792);

					mSEP(); 

					match("\"wgustdir\":"); 

					int wgustdirStart6806 = getCharIndex();
					int wgustdirStartLine6806 = getLine();
					int wgustdirStartCharPos6806 = getCharPositionInLine();
					mNUMBER(); 
					wgustdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wgustdirStart6806, getCharIndex()-1);
					wgustdir.setLine(wgustdirStartLine6806);
					wgustdir.setCharPositionInLine(wgustdirStartCharPos6806);

					mSEP(); 

					match("\"humidity\":"); 

					int humidityStart6820 = getCharIndex();
					int humidityStartLine6820 = getLine();
					int humidityStartCharPos6820 = getCharPositionInLine();
					mNUMBER(); 
					humidity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, humidityStart6820, getCharIndex()-1);
					humidity.setLine(humidityStartLine6820);
					humidity.setCharPositionInLine(humidityStartCharPos6820);

					mSEP(); 

					match("\"airtemp\":"); 

					int airtempStart6834 = getCharIndex();
					int airtempStartLine6834 = getLine();
					int airtempStartCharPos6834 = getCharPositionInLine();
					mSIGNED(); 
					airtemp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, airtempStart6834, getCharIndex()-1);
					airtemp.setLine(airtempStartLine6834);
					airtemp.setCharPositionInLine(airtempStartCharPos6834);

					mSEP(); 

					match("\"dewpoint\":"); 

					int dewpointStart6847 = getCharIndex();
					int dewpointStartLine6847 = getLine();
					int dewpointStartCharPos6847 = getCharPositionInLine();
					mSIGNED(); 
					dewpoint = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dewpointStart6847, getCharIndex()-1);
					dewpoint.setLine(dewpointStartLine6847);
					dewpoint.setCharPositionInLine(dewpointStartCharPos6847);

					mSEP(); 

					match("\"pressure\":"); 

					int pressureStart6860 = getCharIndex();
					int pressureStartLine6860 = getLine();
					int pressureStartCharPos6860 = getCharPositionInLine();
					mNUMBER(); 
					pressure = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, pressureStart6860, getCharIndex()-1);
					pressure.setLine(pressureStartLine6860);
					pressure.setCharPositionInLine(pressureStartCharPos6860);

					mSEP(); 

					match("\"pressuretend\":"); 

					int pressuretendStart6874 = getCharIndex();
					int pressuretendStartLine6874 = getLine();
					int pressuretendStartCharPos6874 = getCharPositionInLine();
					mNUMBER(); 
					pressuretend = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, pressuretendStart6874, getCharIndex()-1);
					pressuretend.setLine(pressuretendStartLine6874);
					pressuretend.setCharPositionInLine(pressuretendStartCharPos6874);

					mSEP(); 

					match("\"visgreater\":"); 

					int visgreaterStart6888 = getCharIndex();
					int visgreaterStartLine6888 = getLine();
					int visgreaterStartCharPos6888 = getCharPositionInLine();
					mLETTERS(); 
					visgreater = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, visgreaterStart6888, getCharIndex()-1);
					visgreater.setLine(visgreaterStartLine6888);
					visgreater.setCharPositionInLine(visgreaterStartCharPos6888);

					mSEP(); 

					match("\"visibility\":"); 

					int visibilityStart6901 = getCharIndex();
					int visibilityStartLine6901 = getLine();
					int visibilityStartCharPos6901 = getCharPositionInLine();
					mNUMBER(); 
					visibility = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, visibilityStart6901, getCharIndex()-1);
					visibility.setLine(visibilityStartLine6901);
					visibility.setCharPositionInLine(visibilityStartCharPos6901);

					mSEP(); 

					match("\"waterlevel\":"); 

					int waterlevelStart6915 = getCharIndex();
					int waterlevelStartLine6915 = getLine();
					int waterlevelStartCharPos6915 = getCharPositionInLine();
					mSIGNED(); 
					waterlevel = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waterlevelStart6915, getCharIndex()-1);
					waterlevel.setLine(waterlevelStartLine6915);
					waterlevel.setCharPositionInLine(waterlevelStartCharPos6915);

					mSEP(); 

					match("\"leveltrend\":"); 

					int leveltrendStart6928 = getCharIndex();
					int leveltrendStartLine6928 = getLine();
					int leveltrendStartCharPos6928 = getCharPositionInLine();
					mNUMBER(); 
					leveltrend = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, leveltrendStart6928, getCharIndex()-1);
					leveltrend.setLine(leveltrendStartLine6928);
					leveltrend.setCharPositionInLine(leveltrendStartCharPos6928);

					mSEP(); 

					match("\"cspeed\":"); 

					int cspeedStart6942 = getCharIndex();
					int cspeedStartLine6942 = getLine();
					int cspeedStartCharPos6942 = getCharPositionInLine();
					mNUMBER(); 
					cspeed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeedStart6942, getCharIndex()-1);
					cspeed.setLine(cspeedStartLine6942);
					cspeed.setCharPositionInLine(cspeedStartCharPos6942);

					mSEP(); 

					match("\"cdir\":"); 

					int cdirStart6956 = getCharIndex();
					int cdirStartLine6956 = getLine();
					int cdirStartCharPos6956 = getCharPositionInLine();
					mNUMBER(); 
					cdir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdirStart6956, getCharIndex()-1);
					cdir.setLine(cdirStartLine6956);
					cdir.setCharPositionInLine(cdirStartCharPos6956);

					mSEP(); 

					match("\"cspeed2\":"); 

					int cspeed2Start6970 = getCharIndex();
					int cspeed2StartLine6970 = getLine();
					int cspeed2StartCharPos6970 = getCharPositionInLine();
					mNUMBER(); 
					cspeed2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeed2Start6970, getCharIndex()-1);
					cspeed2.setLine(cspeed2StartLine6970);
					cspeed2.setCharPositionInLine(cspeed2StartCharPos6970);

					mSEP(); 

					match("\"cdir2\":"); 

					int cdir2Start6984 = getCharIndex();
					int cdir2StartLine6984 = getLine();
					int cdir2StartCharPos6984 = getCharPositionInLine();
					mNUMBER(); 
					cdir2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdir2Start6984, getCharIndex()-1);
					cdir2.setLine(cdir2StartLine6984);
					cdir2.setCharPositionInLine(cdir2StartCharPos6984);

					mSEP(); 

					match("\"cdepth2\":"); 

					int cdepth2Start6998 = getCharIndex();
					int cdepth2StartLine6998 = getLine();
					int cdepth2StartCharPos6998 = getCharPositionInLine();
					mNUMBER(); 
					cdepth2 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdepth2Start6998, getCharIndex()-1);
					cdepth2.setLine(cdepth2StartLine6998);
					cdepth2.setCharPositionInLine(cdepth2StartCharPos6998);

					mSEP(); 

					match("\"cspeed3\":"); 

					int cspeed3Start7012 = getCharIndex();
					int cspeed3StartLine7012 = getLine();
					int cspeed3StartCharPos7012 = getCharPositionInLine();
					mNUMBER(); 
					cspeed3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cspeed3Start7012, getCharIndex()-1);
					cspeed3.setLine(cspeed3StartLine7012);
					cspeed3.setCharPositionInLine(cspeed3StartCharPos7012);

					mSEP(); 

					match("\"cdir3\":"); 

					int cdir3Start7026 = getCharIndex();
					int cdir3StartLine7026 = getLine();
					int cdir3StartCharPos7026 = getCharPositionInLine();
					mNUMBER(); 
					cdir3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdir3Start7026, getCharIndex()-1);
					cdir3.setLine(cdir3StartLine7026);
					cdir3.setCharPositionInLine(cdir3StartCharPos7026);

					mSEP(); 

					match("\"cdepth3\":"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1403:19: (cdepth3= NUMBER | '\\n' )
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
						throw nvae;
					}

					switch (alt228) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1403:20: cdepth3= NUMBER
							{
							int cdepth3Start7043 = getCharIndex();
							int cdepth3StartLine7043 = getLine();
							int cdepth3StartCharPos7043 = getCharPositionInLine();
							mNUMBER(); 
							cdepth3 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, cdepth3Start7043, getCharIndex()-1);
							cdepth3.setLine(cdepth3StartLine7043);
							cdepth3.setCharPositionInLine(cdepth3StartCharPos7043);

							}
							break;
						case 2 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1403:36: '\\n'
							{
							match('\n'); 
							}
							break;

					}

					mSEP(); 

					match("\"waveheight\":"); 

					int waveheightStart7062 = getCharIndex();
					int waveheightStartLine7062 = getLine();
					int waveheightStartCharPos7062 = getCharPositionInLine();
					mNUMBER(); 
					waveheight = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waveheightStart7062, getCharIndex()-1);
					waveheight.setLine(waveheightStartLine7062);
					waveheight.setCharPositionInLine(waveheightStartCharPos7062);

					mSEP(); 

					match("\"waveperiod\":"); 

					int waveperiodStart7076 = getCharIndex();
					int waveperiodStartLine7076 = getLine();
					int waveperiodStartCharPos7076 = getCharPositionInLine();
					mNUMBER(); 
					waveperiod = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, waveperiodStart7076, getCharIndex()-1);
					waveperiod.setLine(waveperiodStartLine7076);
					waveperiod.setCharPositionInLine(waveperiodStartCharPos7076);

					mSEP(); 

					match("\"wavedir\":"); 

					int wavedirStart7090 = getCharIndex();
					int wavedirStartLine7090 = getLine();
					int wavedirStartCharPos7090 = getCharPositionInLine();
					mNUMBER(); 
					wavedir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, wavedirStart7090, getCharIndex()-1);
					wavedir.setLine(wavedirStartLine7090);
					wavedir.setCharPositionInLine(wavedirStartCharPos7090);

					mSEP(); 

					match("\"swellheight\":"); 

					int swellheightStart7104 = getCharIndex();
					int swellheightStartLine7104 = getLine();
					int swellheightStartCharPos7104 = getCharPositionInLine();
					mNUMBER(); 
					swellheight = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swellheightStart7104, getCharIndex()-1);
					swellheight.setLine(swellheightStartLine7104);
					swellheight.setCharPositionInLine(swellheightStartCharPos7104);

					mSEP(); 

					match("\"swellperiod\":"); 

					int swellperiodStart7118 = getCharIndex();
					int swellperiodStartLine7118 = getLine();
					int swellperiodStartCharPos7118 = getCharPositionInLine();
					mNUMBER(); 
					swellperiod = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swellperiodStart7118, getCharIndex()-1);
					swellperiod.setLine(swellperiodStartLine7118);
					swellperiod.setCharPositionInLine(swellperiodStartCharPos7118);

					mSEP(); 

					match("\"swelldir\":"); 

					int swelldirStart7132 = getCharIndex();
					int swelldirStartLine7132 = getLine();
					int swelldirStartCharPos7132 = getCharPositionInLine();
					mNUMBER(); 
					swelldir = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, swelldirStart7132, getCharIndex()-1);
					swelldir.setLine(swelldirStartLine7132);
					swelldir.setCharPositionInLine(swelldirStartCharPos7132);

					mSEP(); 

					match("\"seastate\":"); 

					int seastateStart7146 = getCharIndex();
					int seastateStartLine7146 = getLine();
					int seastateStartCharPos7146 = getCharPositionInLine();
					mNUMBER(); 
					seastate = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, seastateStart7146, getCharIndex()-1);
					seastate.setLine(seastateStartLine7146);
					seastate.setCharPositionInLine(seastateStartCharPos7146);

					mSEP(); 

					match("\"watertemp\":"); 

					int watertempStart7160 = getCharIndex();
					int watertempStartLine7160 = getLine();
					int watertempStartCharPos7160 = getCharPositionInLine();
					mSIGNED(); 
					watertemp = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, watertempStart7160, getCharIndex()-1);
					watertemp.setLine(watertempStartLine7160);
					watertemp.setCharPositionInLine(watertempStartCharPos7160);

					mSEP(); 

					match("\"preciptype\":"); 

					int preciptypeStart7173 = getCharIndex();
					int preciptypeStartLine7173 = getLine();
					int preciptypeStartCharPos7173 = getCharPositionInLine();
					mNUMBER(); 
					preciptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, preciptypeStart7173, getCharIndex()-1);
					preciptype.setLine(preciptypeStartLine7173);
					preciptype.setCharPositionInLine(preciptypeStartCharPos7173);

					mSEP(); 

					match("\"salinity\":"); 

					int salinityStart7187 = getCharIndex();
					int salinityStartLine7187 = getLine();
					int salinityStartCharPos7187 = getCharPositionInLine();
					mNUMBER(); 
					salinity = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, salinityStart7187, getCharIndex()-1);
					salinity.setLine(salinityStartLine7187);
					salinity.setCharPositionInLine(salinityStartCharPos7187);

					mSEP(); 

					match("\"ice\":"); 

					int iceStart7201 = getCharIndex();
					int iceStartLine7201 = getLine();
					int iceStartCharPos7201 = getCharPositionInLine();
					mNUMBER(); 
					ice = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, iceStart7201, getCharIndex()-1);
					ice.setLine(iceStartLine7201);
					ice.setCharPositionInLine(iceStartCharPos7201);

					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1417:6: '\"reserved\":' (reserved= NUMBER )* SEP '\"speed\":' speed= NUMBER SEP '\"accuracy\":' accuracy= LETTERS SEP '\"lon\":' longitude= SIGNED SEP '\"lat\":' latitude= SIGNED SEP '\"course\":' course= NUMBER SEP '\"heading\":' heading= NUMBER SEP '\"second\":' second= NUMBER SEP '\"regional\":' regional= NUMBER SEP '\"cs\":' cs= LETTERS SEP '\"display\":' display= LETTERS SEP '\"dsc\":' dsc= LETTERS SEP '\"band\":' band= LETTERS SEP '\"msg22\":' msg22= LETTERS SEP '\"raim\":' raim= LETTERS SEP '\"radio\":' radio= NUMBER
					{
					match("\"reserved\":"); 

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1417:28: (reserved= NUMBER )*
					loop229:
					while (true) {
						int alt229=2;
						int LA229_0 = input.LA(1);
						if ( (LA229_0=='.'||(LA229_0 >= '0' && LA229_0 <= '9')) ) {
							alt229=1;
						}

						switch (alt229) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1417:28: reserved= NUMBER
							{
							int reservedStart7226 = getCharIndex();
							int reservedStartLine7226 = getLine();
							int reservedStartCharPos7226 = getCharPositionInLine();
							mNUMBER(); 
							reserved = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, reservedStart7226, getCharIndex()-1);
							reserved.setLine(reservedStartLine7226);
							reserved.setCharPositionInLine(reservedStartCharPos7226);

							}
							break;

						default :
							break loop229;
						}
					}

					mSEP(); 

					match("\"speed\":"); 

					int speedStart7240 = getCharIndex();
					int speedStartLine7240 = getLine();
					int speedStartCharPos7240 = getCharPositionInLine();
					mNUMBER(); 
					speed = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, speedStart7240, getCharIndex()-1);
					speed.setLine(speedStartLine7240);
					speed.setCharPositionInLine(speedStartCharPos7240);

					mSEP(); 

					match("\"accuracy\":"); 

					int accuracyStart7254 = getCharIndex();
					int accuracyStartLine7254 = getLine();
					int accuracyStartCharPos7254 = getCharPositionInLine();
					mLETTERS(); 
					accuracy = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, accuracyStart7254, getCharIndex()-1);
					accuracy.setLine(accuracyStartLine7254);
					accuracy.setCharPositionInLine(accuracyStartCharPos7254);

					mSEP(); 

					match("\"lon\":"); 

					int longitudeStart7267 = getCharIndex();
					int longitudeStartLine7267 = getLine();
					int longitudeStartCharPos7267 = getCharPositionInLine();
					mSIGNED(); 
					longitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, longitudeStart7267, getCharIndex()-1);
					longitude.setLine(longitudeStartLine7267);
					longitude.setCharPositionInLine(longitudeStartCharPos7267);

					mSEP(); 

					match("\"lat\":"); 

					int latitudeStart7281 = getCharIndex();
					int latitudeStartLine7281 = getLine();
					int latitudeStartCharPos7281 = getCharPositionInLine();
					mSIGNED(); 
					latitude = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, latitudeStart7281, getCharIndex()-1);
					latitude.setLine(latitudeStartLine7281);
					latitude.setCharPositionInLine(latitudeStartCharPos7281);

					mSEP(); 

					match("\"course\":"); 

					int courseStart7295 = getCharIndex();
					int courseStartLine7295 = getLine();
					int courseStartCharPos7295 = getCharPositionInLine();
					mNUMBER(); 
					course = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, courseStart7295, getCharIndex()-1);
					course.setLine(courseStartLine7295);
					course.setCharPositionInLine(courseStartCharPos7295);

					mSEP(); 

					match("\"heading\":"); 

					int headingStart7308 = getCharIndex();
					int headingStartLine7308 = getLine();
					int headingStartCharPos7308 = getCharPositionInLine();
					mNUMBER(); 
					heading = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, headingStart7308, getCharIndex()-1);
					heading.setLine(headingStartLine7308);
					heading.setCharPositionInLine(headingStartCharPos7308);

					mSEP(); 

					match("\"second\":"); 

					int secondStart7321 = getCharIndex();
					int secondStartLine7321 = getLine();
					int secondStartCharPos7321 = getCharPositionInLine();
					mNUMBER(); 
					second = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, secondStart7321, getCharIndex()-1);
					second.setLine(secondStartLine7321);
					second.setCharPositionInLine(secondStartCharPos7321);

					mSEP(); 

					match("\"regional\":"); 

					int regionalStart7336 = getCharIndex();
					int regionalStartLine7336 = getLine();
					int regionalStartCharPos7336 = getCharPositionInLine();
					mNUMBER(); 
					regional = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, regionalStart7336, getCharIndex()-1);
					regional.setLine(regionalStartLine7336);
					regional.setCharPositionInLine(regionalStartCharPos7336);

					mSEP(); 

					match("\"cs\":"); 

					int csStart7350 = getCharIndex();
					int csStartLine7350 = getLine();
					int csStartCharPos7350 = getCharPositionInLine();
					mLETTERS(); 
					cs = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, csStart7350, getCharIndex()-1);
					cs.setLine(csStartLine7350);
					cs.setCharPositionInLine(csStartCharPos7350);

					mSEP(); 

					match("\"display\":"); 

					int displayStart7364 = getCharIndex();
					int displayStartLine7364 = getLine();
					int displayStartCharPos7364 = getCharPositionInLine();
					mLETTERS(); 
					display = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, displayStart7364, getCharIndex()-1);
					display.setLine(displayStartLine7364);
					display.setCharPositionInLine(displayStartCharPos7364);

					mSEP(); 

					match("\"dsc\":"); 

					int dscStart7377 = getCharIndex();
					int dscStartLine7377 = getLine();
					int dscStartCharPos7377 = getCharPositionInLine();
					mLETTERS(); 
					dsc = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, dscStart7377, getCharIndex()-1);
					dsc.setLine(dscStartLine7377);
					dsc.setCharPositionInLine(dscStartCharPos7377);

					mSEP(); 

					match("\"band\":"); 

					int bandStart7391 = getCharIndex();
					int bandStartLine7391 = getLine();
					int bandStartCharPos7391 = getCharPositionInLine();
					mLETTERS(); 
					band = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, bandStart7391, getCharIndex()-1);
					band.setLine(bandStartLine7391);
					band.setCharPositionInLine(bandStartCharPos7391);

					mSEP(); 

					match("\"msg22\":"); 

					int msg22Start7404 = getCharIndex();
					int msg22StartLine7404 = getLine();
					int msg22StartCharPos7404 = getCharPositionInLine();
					mLETTERS(); 
					msg22 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, msg22Start7404, getCharIndex()-1);
					msg22.setLine(msg22StartLine7404);
					msg22.setCharPositionInLine(msg22StartCharPos7404);

					mSEP(); 

					match("\"raim\":"); 

					int raimStart7417 = getCharIndex();
					int raimStartLine7417 = getLine();
					int raimStartCharPos7417 = getCharPositionInLine();
					mLETTERS(); 
					raim = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, raimStart7417, getCharIndex()-1);
					raim.setLine(raimStartLine7417);
					raim.setCharPositionInLine(raimStartCharPos7417);

					mSEP(); 

					match("\"radio\":"); 

					int radioStart7431 = getCharIndex();
					int radioStartLine7431 = getLine();
					int radioStartCharPos7431 = getCharPositionInLine();
					mNUMBER(); 
					radio = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, radioStart7431, getCharIndex()-1);
					radio.setLine(radioStartLine7431);
					radio.setCharPositionInLine(radioStartCharPos7431);

					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1435:6: '\"shipname\":' shipname= NAME SEP '\"shiptype\":' shiptype= NUMBER SEP '\"vendorid\":' vendorid= NAME SEP '\"callsign\":' callsign= NAME SEP '\"to_bow\":' to_bow= NUMBER SEP '\"to_stern\":' to_stern= NUMBER SEP '\"to_port\":' to_port= NUMBER SEP '\"to_starboard\":' to_starboard= NUMBER
					{
					match("\"shipname\":"); 

					int shipnameStart7455 = getCharIndex();
					int shipnameStartLine7455 = getLine();
					int shipnameStartCharPos7455 = getCharPositionInLine();
					mNAME(); 
					shipname = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shipnameStart7455, getCharIndex()-1);
					shipname.setLine(shipnameStartLine7455);
					shipname.setCharPositionInLine(shipnameStartCharPos7455);

					mSEP(); 

					match("\"shiptype\":"); 

					int shiptypeStart7469 = getCharIndex();
					int shiptypeStartLine7469 = getLine();
					int shiptypeStartCharPos7469 = getCharPositionInLine();
					mNUMBER(); 
					shiptype = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, shiptypeStart7469, getCharIndex()-1);
					shiptype.setLine(shiptypeStartLine7469);
					shiptype.setCharPositionInLine(shiptypeStartCharPos7469);

					mSEP(); 

					match("\"vendorid\":"); 

					int vendoridStart7482 = getCharIndex();
					int vendoridStartLine7482 = getLine();
					int vendoridStartCharPos7482 = getCharPositionInLine();
					mNAME(); 
					vendorid = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, vendoridStart7482, getCharIndex()-1);
					vendorid.setLine(vendoridStartLine7482);
					vendorid.setCharPositionInLine(vendoridStartCharPos7482);

					mSEP(); 

					match("\"callsign\":"); 

					int callsignStart7496 = getCharIndex();
					int callsignStartLine7496 = getLine();
					int callsignStartCharPos7496 = getCharPositionInLine();
					mNAME(); 
					callsign = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, callsignStart7496, getCharIndex()-1);
					callsign.setLine(callsignStartLine7496);
					callsign.setCharPositionInLine(callsignStartCharPos7496);

					mSEP(); 

					match("\"to_bow\":"); 

					int to_bowStart7510 = getCharIndex();
					int to_bowStartLine7510 = getLine();
					int to_bowStartCharPos7510 = getCharPositionInLine();
					mNUMBER(); 
					to_bow = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_bowStart7510, getCharIndex()-1);
					to_bow.setLine(to_bowStartLine7510);
					to_bow.setCharPositionInLine(to_bowStartCharPos7510);

					mSEP(); 

					match("\"to_stern\":"); 

					int to_sternStart7523 = getCharIndex();
					int to_sternStartLine7523 = getLine();
					int to_sternStartCharPos7523 = getCharPositionInLine();
					mNUMBER(); 
					to_stern = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_sternStart7523, getCharIndex()-1);
					to_stern.setLine(to_sternStartLine7523);
					to_stern.setCharPositionInLine(to_sternStartCharPos7523);

					mSEP(); 

					match("\"to_port\":"); 

					int to_portStart7536 = getCharIndex();
					int to_portStartLine7536 = getLine();
					int to_portStartCharPos7536 = getCharPositionInLine();
					mNUMBER(); 
					to_port = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_portStart7536, getCharIndex()-1);
					to_port.setLine(to_portStartLine7536);
					to_port.setCharPositionInLine(to_portStartCharPos7536);

					mSEP(); 

					match("\"to_starboard\":"); 

					int to_starboardStart7549 = getCharIndex();
					int to_starboardStartLine7549 = getLine();
					int to_starboardStartCharPos7549 = getCharPositionInLine();
					mNUMBER(); 
					to_starboard = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, to_starboardStart7549, getCharIndex()-1);
					to_starboard.setLine(to_starboardStartLine7549);
					to_starboard.setCharPositionInLine(to_starboardStartCharPos7549);

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:10: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | NUMBER | LETTERS | SIGN )*
			loop231:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:11: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:17: '['
					{
					match('['); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:23: ']'
					{
					match(']'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:29: ':'
					{
					match(':'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:35: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:42: '}'
					{
					match('}'); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:48: '_'
					{
					match('_'); 
					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:54: '#'
					{
					match('#'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:60: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:69: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1444:79: SIGN
					{
					mSIGN(); 

					}
					break;

				default :
					break loop231;
				}
			}


			        //System.out.println(getText());
			    	switch(type.getText()){
				case "1" :
				  if(dev != null && mmsi != null && status != null && turn != null 
				     && speed != null && longitude != null && latitude != null && course != null && heading != null && second != null){
			            ais01 = new AIS01();
			                        ais01.setRateOfTurn(new Integer(turn.getText()));
			                        ais01.setCourseOverGround((int)((new Float(course.getText()))/10.0f));
			                        ais01.setSpeedOverGround((int)((new Float(speed.getText()))/10.0f));
			                        ais01.setNavigationalStatus(new Integer(status.getText()));
			                        ais01.setTrueHeading(new Integer(heading.getText()));
			                        ais01.setLatitude(degConvert(new Float(latitude.getText())));
			                        ais01.setLongitude(degConvert(new Float(longitude.getText())));
			                        ais01.setMmsi(new Integer(mmsi.getText()));
			                        ais01.setDevice(dev.getText()); 
			                        ais01.setSpecialManoeuvreIndicator(new Integer(maneuvrer.getText()));
			                        ais01.setRaimFlag(Boolean.getBoolean(raim.getText())); 
			                        ais01.setPositionAccuracy(Boolean.getBoolean(accuracy.getText()));
			             // System.out.println(ais01);                                    
			            handler.doIt(ais01);
			           
			        }
			           break;
			           
			        case "2" :
			         if(dev != null && mmsi != null && status != null && turn != null 
				     && speed != null && longitude != null && latitude != null && course != null && heading != null && second != null){
			            ais02 = new AIS02();
			                        ais02.setRateOfTurn(new Integer(turn.getText()));
			                        ais02.setCourseOverGround((int)((new Float(course.getText()))/10.0f));
			                        ais02.setSpeedOverGround((int)((new Float(speed.getText()))/10.0f));
			                        ais02.setNavigationalStatus(new Integer(status.getText()));
			                        ais02.setTrueHeading(new Integer(heading.getText()));
			                        ais02.setLatitude(degConvert(new Float(latitude.getText())));
			                        ais02.setLongitude(degConvert(new Float(longitude.getText())));
			                        ais02.setMmsi(new Integer(mmsi.getText()));
			                        ais02.setDevice(dev.getText());
			                        ais02.setSpecialManoeuvreIndicator(new Integer(maneuvrer.getText()));
			                        ais02.setRaimFlag(Boolean.getBoolean(raim.getText())); 
			                        ais02.setPositionAccuracy(Boolean.getBoolean(accuracy.getText()));
			            handler.doIt(ais02);
				  }
			           break;
				case "3" :
			         if(dev != null && mmsi != null && status != null && turn != null 
				     && speed != null && longitude != null && latitude != null && course != null && heading != null && second != null){
			            ais03 = new AIS03();
			                        ais03.setRateOfTurn(new Integer(turn.getText()));
			                        ais03.setCourseOverGround((int)((new Float(course.getText()))/10.0f));
			                        ais03.setSpeedOverGround((int)((new Float(speed.getText()))/10.0f));
			                        ais03.setNavigationalStatus(new Integer(status.getText()));
			                        ais03.setTrueHeading(new Integer(heading.getText()));
			                        ais03.setLatitude(degConvert(new Float(latitude.getText())));
			                        ais03.setLongitude(degConvert(new Float(longitude.getText())));
			                        ais03.setMmsi(new Integer(mmsi.getText()));
			                        ais03.setDevice(dev.getText());
			                        ais03.setSpecialManoeuvreIndicator(new Integer(maneuvrer.getText()));
			                        ais03.setRaimFlag(Boolean.getBoolean(raim.getText())); 
			                        ais03.setPositionAccuracy(Boolean.getBoolean(accuracy.getText()));
			            handler.doIt(ais03);
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
				  /*
				    ais04 = new AIS04(new Integer(mmsi.getText()), device,
				                         new GregorianCalendar(year, month, day, hours, minutes, seconds),
				                         degConvert(new Float(latitude.getText())), degConvert(new Float(longitude.getText()))
				                        );  
				                                         
				    
				    handler.doIt(ais04);
				    */
			         // System.out.println("ais4");
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
				  
				  AIS05 ais05=new AIS05();
				  ais05.setMmsi(new Integer(mmsi.getText()));
			          ais05.setCallSign(callsign.getText());
			          ais05.setDestination(destination.getText());
			          ais05.setImoNumber(new Integer(imo.getText()));
			          ais05.setName(shipname.getText());
			          ais05.setShipType(new Integer(shiptype.getText()));
			          handler.doIt(ais05);
			        //  System.out.println("ais05");
				  }
				  
				   break;
				case "8" :
				  // System.out.println("ais08");
				   break;
				case "18":
			          if(dev != null && mmsi != null && speed != null && longitude != null && latitude != null &&
			             course != null && heading != null && second != null){
			          /*    
			           ais18 = new AIS18(new Integer(mmsi.getText()), dev.getText(),
			                   new Float(speed.getText()), new Float(course.getText()), new Float(heading.getText()), 
			                   degConvert(new Float(latitude.getText())), degConvert(new Float(longitude.getText())),
			                   new Integer(second.getText()));
			                   
			            
			            handler.doIt(ais18);
			           */ 
			          // System.out.println("ais18"); 
			                                 
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1588:9: ( '{' '\"class\":\"DEVICE\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:6: '{' '\"class\":\"DEVICE\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			{
			match('{'); 
			match("\"class\":\"DEVICE\""); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:28: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			loop232:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:29: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:35: '['
					{
					match('['); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:41: ']'
					{
					match(']'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:47: ':'
					{
					match(':'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:53: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:60: '}'
					{
					match('}'); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:66: '_'
					{
					match('_'); 
					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:72: '#'
					{
					match('#'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:79: SEP
					{
					mSEP(); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:85: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:94: SIGN
					{
					mSIGN(); 

					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1589:101: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop232;
				}
			}


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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1595:9: ( '{' '\"class\":\"DEVICES\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:6: '{' '\"class\":\"DEVICES\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			{
			match('{'); 
			match("\"class\":\"DEVICES\""); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:29: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			loop233:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:30: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:36: '['
					{
					match('['); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:42: ']'
					{
					match(']'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:48: ':'
					{
					match(':'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:54: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:61: '}'
					{
					match('}'); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:67: '_'
					{
					match('_'); 
					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:73: '#'
					{
					match('#'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:80: SEP
					{
					mSEP(); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:86: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:95: SIGN
					{
					mSIGN(); 

					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1596:102: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop233;
				}
			}


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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1602:6: ( '{' '\"class\":\"VERSION\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:6: '{' '\"class\":\"VERSION\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			{
			match('{'); 
			match("\"class\":\"VERSION\""); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:29: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			loop234:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:30: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:36: '['
					{
					match('['); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:42: ']'
					{
					match(']'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:48: ':'
					{
					match(':'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:54: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:61: '}'
					{
					match('}'); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:67: '_'
					{
					match('_'); 
					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:73: '#'
					{
					match('#'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:80: SEP
					{
					mSEP(); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:86: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:95: SIGN
					{
					mSIGN(); 

					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1603:102: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop234;
				}
			}


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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1609:6: ( '{' '\"class\":\"WATCH\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )* )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:6: '{' '\"class\":\"WATCH\"' ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			{
			match('{'); 
			match("\"class\":\"WATCH\""); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:27: ( '\"' | '[' | ']' | ':' | '/' | '}' | '_' | '#' | SEP | NUMBER | SIGN | LETTERS )*
			loop235:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:28: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:34: '['
					{
					match('['); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:40: ']'
					{
					match(']'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:46: ':'
					{
					match(':'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:52: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:59: '}'
					{
					match('}'); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:65: '_'
					{
					match('_'); 
					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:71: '#'
					{
					match('#'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:78: SEP
					{
					mSEP(); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:84: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:93: SIGN
					{
					mSIGN(); 

					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1610:100: LETTERS
					{
					mLETTERS(); 

					}
					break;

				default :
					break loop235;
				}
			}


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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1673:5: ( ( '$' ) device= DEVICE 'TXT' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1673:7: ( '$' ) device= DEVICE 'TXT' SEP ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1673:7: ( '$' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1673:8: '$'
			{
			match('$'); 
			}

			int deviceStart7982 = getCharIndex();
			int deviceStartLine7982 = getLine();
			int deviceStartCharPos7982 = getCharPositionInLine();
			mDEVICE(); 
			device = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, deviceStart7982, getCharIndex()-1);
			device.setLine(deviceStartLine7982);
			device.setCharPositionInLine(deviceStartCharPos7982);

			match("TXT"); 

			mSEP(); 

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1674:2: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
			loop236:
			while (true) {
				int alt236=2;
				alt236 = dfa236.predict(input);
				switch (alt236) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					break loop236;
				}
			}

			int checksumStart8009 = getCharIndex();
			int checksumStartLine8009 = getLine();
			int checksumStartCharPos8009 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart8009, getCharIndex()-1);
			checksum.setLine(checksumStartLine8009);
			checksum.setCharPositionInLine(checksumStartCharPos8009);


				
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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:5: ( ( '$PR' | '$PG' | '$PS' ) ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:7: ( '$PR' | '$PG' | '$PS' ) ( '\\u0021' .. '\\u007F' | SEP | ' ' )* checksum= CHECKSUM
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:7: ( '$PR' | '$PG' | '$PS' )
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
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 237, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 237, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 237, 0, input);
				throw nvae;
			}

			switch (alt237) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:8: '$PR'
					{
					match("$PR"); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:15: '$PG'
					{
					match("$PG"); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:22: '$PS'
					{
					match("$PS"); 

					}
					break;

			}

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1686:29: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*
			loop238:
			while (true) {
				int alt238=2;
				alt238 = dfa238.predict(input);
				switch (alt238) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					break loop238;
				}
			}

			int checksumStart8055 = getCharIndex();
			int checksumStartLine8055 = getLine();
			int checksumStartCharPos8055 = getCharPositionInLine();
			mCHECKSUM(); 
			checksum = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, checksumStart8055, getCharIndex()-1);
			checksum.setLine(checksumStartLine8055);
			checksum.setCharPositionInLine(checksumStartCharPos8055);


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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1690:9: ( ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' ) )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:4: ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' )
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:4: ( 'GP' | 'II' | 'AG' | 'AI' | 'AP' | 'CC' | 'CD' | 'CS' | 'CT' | 'CV' | 'CX' | 'DF' | 'EC' | 'EP' | 'ER' | 'HC' | 'HE' | 'HN' | 'IN' | 'RA' | 'SD' | 'SM' | 'SN' | 'SS' | 'TI' | 'TR' | 'VD' | 'DM' | 'VW' | 'WI' | 'YX' | 'ZA' | 'ZC' | 'ZQ' | 'ZV' )
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 6, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 7, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 9, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 10, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 11, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 239, 14, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:5: 'GP'
					{
					match("GP"); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:10: 'II'
					{
					match("II"); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:15: 'AG'
					{
					match("AG"); 

					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:20: 'AI'
					{
					match("AI"); 

					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:25: 'AP'
					{
					match("AP"); 

					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:30: 'CC'
					{
					match("CC"); 

					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:35: 'CD'
					{
					match("CD"); 

					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:40: 'CS'
					{
					match("CS"); 

					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:45: 'CT'
					{
					match("CT"); 

					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:50: 'CV'
					{
					match("CV"); 

					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:55: 'CX'
					{
					match("CX"); 

					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:60: 'DF'
					{
					match("DF"); 

					}
					break;
				case 13 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:65: 'EC'
					{
					match("EC"); 

					}
					break;
				case 14 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:70: 'EP'
					{
					match("EP"); 

					}
					break;
				case 15 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:75: 'ER'
					{
					match("ER"); 

					}
					break;
				case 16 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:80: 'HC'
					{
					match("HC"); 

					}
					break;
				case 17 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:85: 'HE'
					{
					match("HE"); 

					}
					break;
				case 18 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:90: 'HN'
					{
					match("HN"); 

					}
					break;
				case 19 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:95: 'IN'
					{
					match("IN"); 

					}
					break;
				case 20 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:100: 'RA'
					{
					match("RA"); 

					}
					break;
				case 21 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:105: 'SD'
					{
					match("SD"); 

					}
					break;
				case 22 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:110: 'SM'
					{
					match("SM"); 

					}
					break;
				case 23 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:115: 'SN'
					{
					match("SN"); 

					}
					break;
				case 24 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:120: 'SS'
					{
					match("SS"); 

					}
					break;
				case 25 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:125: 'TI'
					{
					match("TI"); 

					}
					break;
				case 26 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:130: 'TR'
					{
					match("TR"); 

					}
					break;
				case 27 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:135: 'VD'
					{
					match("VD"); 

					}
					break;
				case 28 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:140: 'DM'
					{
					match("DM"); 

					}
					break;
				case 29 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:145: 'VW'
					{
					match("VW"); 

					}
					break;
				case 30 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:150: 'WI'
					{
					match("WI"); 

					}
					break;
				case 31 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:155: 'YX'
					{
					match("YX"); 

					}
					break;
				case 32 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:160: 'ZA'
					{
					match("ZA"); 

					}
					break;
				case 33 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:165: 'ZC'
					{
					match("ZC"); 

					}
					break;
				case 34 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:170: 'ZQ'
					{
					match("ZQ"); 

					}
					break;
				case 35 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1691:175: 'ZV'
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1696:9: ( '\"' ( LETTERS | '/' | ':' | '#' | NUMBER )* '\"' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:2: '\"' ( LETTERS | '/' | ':' | '#' | NUMBER )* '\"'
			{
			match('\"'); 
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:5: ( LETTERS | '/' | ':' | '#' | NUMBER )*
			loop240:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:7: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:16: '/'
					{
					match('/'); 
					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:22: ':'
					{
					match(':'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:28: '#'
					{
					match('#'); 
					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1697:34: NUMBER
					{
					mNUMBER(); 

					}
					break;

				default :
					break loop240;
				}
			}

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1701:5: ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )* ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
			int alt248=4;
			alt248 = dfa248.predict(input);
			switch (alt248) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1702:5: ( '0' .. '9' )+
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1702:5: ( '0' .. '9' )+
					int cnt241=0;
					loop241:
					while (true) {
						int alt241=2;
						int LA241_0 = input.LA(1);
						if ( ((LA241_0 >= '0' && LA241_0 <= '9')) ) {
							alt241=1;
						}

						switch (alt241) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
							EarlyExitException eee = new EarlyExitException(241, input);
							throw eee;
						}
						cnt241++;
					}

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1704:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1704:5: ( '0' .. '9' )+
					int cnt242=0;
					loop242:
					while (true) {
						int alt242=2;
						int LA242_0 = input.LA(1);
						if ( ((LA242_0 >= '0' && LA242_0 <= '9')) ) {
							alt242=1;
						}

						switch (alt242) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
							EarlyExitException eee = new EarlyExitException(242, input);
							throw eee;
						}
						cnt242++;
					}

					match('.'); 
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1704:21: ( '0' .. '9' )*
					loop243:
					while (true) {
						int alt243=2;
						int LA243_0 = input.LA(1);
						if ( ((LA243_0 >= '0' && LA243_0 <= '9')) ) {
							alt243=1;
						}

						switch (alt243) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1704:33: ( EXPONENT )?
					int alt244=2;
					int LA244_0 = input.LA(1);
					if ( (LA244_0=='E'||LA244_0=='e') ) {
						alt244=1;
					}
					switch (alt244) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1704:33: EXPONENT
							{
							mEXPONENT(); 

							}
							break;

					}

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1705:9: '.' ( '0' .. '9' )* ( EXPONENT )?
					{
					match('.'); 
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1705:13: ( '0' .. '9' )*
					loop245:
					while (true) {
						int alt245=2;
						int LA245_0 = input.LA(1);
						if ( ((LA245_0 >= '0' && LA245_0 <= '9')) ) {
							alt245=1;
						}

						switch (alt245) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					}

					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1705:25: ( EXPONENT )?
					int alt246=2;
					int LA246_0 = input.LA(1);
					if ( (LA246_0=='E'||LA246_0=='e') ) {
						alt246=1;
					}
					switch (alt246) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1705:25: EXPONENT
							{
							mEXPONENT(); 

							}
							break;

					}

					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1706:9: ( '0' .. '9' )+ EXPONENT
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1706:9: ( '0' .. '9' )+
					int cnt247=0;
					loop247:
					while (true) {
						int alt247=2;
						int LA247_0 = input.LA(1);
						if ( ((LA247_0 >= '0' && LA247_0 <= '9')) ) {
							alt247=1;
						}

						switch (alt247) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
							EarlyExitException eee = new EarlyExitException(247, input);
							throw eee;
						}
						cnt247++;
					}

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1709:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1709:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1716:5: ( ( ',' ) )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1716:7: ( ',' )
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1720:6: ( ( '+' | '-' ) )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1723:8: ( ( SIGN )? NUMBER )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1724:5: ( SIGN )? NUMBER
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1724:5: ( SIGN )?
			int alt249=2;
			int LA249_0 = input.LA(1);
			if ( (LA249_0=='+'||LA249_0=='-') ) {
				alt249=1;
			}
			switch (alt249) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1728:2: ( '\"' ( LETTERS | NUMBER | ':' | SIGN )+ '\"' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:2: '\"' ( LETTERS | NUMBER | ':' | SIGN )+ '\"'
			{
			match('\"'); 
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:6: ( LETTERS | NUMBER | ':' | SIGN )+
			int cnt250=0;
			loop250:
			while (true) {
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:7: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:17: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:27: ':'
					{
					match(':'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1729:33: SIGN
					{
					mSIGN(); 

					}
					break;

				default :
					if ( cnt250 >= 1 ) break loop250;
					EarlyExitException eee = new EarlyExitException(250, input);
					throw eee;
				}
				cnt250++;
			}

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:10: ( ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) ) )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:12: ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) )
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:12: ( ( '*' ( '0' .. '9' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) ) | ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) ) | ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) ) )
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
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 252, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
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
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 252, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 252, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 252, 0, input);
				throw nvae;
			}

			switch (alt252) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:13: ( '*' ( '0' .. '9' ) ( '0' .. '9' ) )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:13: ( '*' ( '0' .. '9' ) ( '0' .. '9' ) )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1731:14: '*' ( '0' .. '9' ) ( '0' .. '9' )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1732:13: ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1732:13: ( '*' ( 'A' .. 'F' ) ( '0' .. '9' ) )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1732:14: '*' ( 'A' .. 'F' ) ( '0' .. '9' )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1733:13: ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1733:13: ( '*' ( 'A' .. 'F' ) ( 'A' .. 'F' ) )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1733:14: '*' ( 'A' .. 'F' ) ( 'A' .. 'F' )
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
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1734:13: ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) )
					{
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1734:13: ( '*' ( '0' .. '9' )+ ( 'A' .. 'F' ) )
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1734:14: '*' ( '0' .. '9' )+ ( 'A' .. 'F' )
					{
					match('*'); 
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1734:17: ( '0' .. '9' )+
					int cnt251=0;
					loop251:
					while (true) {
						int alt251=2;
						int LA251_0 = input.LA(1);
						if ( ((LA251_0 >= '0' && LA251_0 <= '9')) ) {
							alt251=1;
						}

						switch (alt251) {
						case 1 :
							// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
							EarlyExitException eee = new EarlyExitException(251, input);
							throw eee;
						}
						cnt251++;
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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1737:3: ( '\"' ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' | '#' | ']' | '[' | '\\\\' | '=' | '\\?' | '(' | ')' | '&' )* '\"' )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:3: '\"' ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' | '#' | ']' | '[' | '\\\\' | '=' | '\\?' | '(' | ')' | '&' )* '\"'
			{
			match('\"'); 
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:7: ( LETTERS | NUMBER | ':' | SIGN | '/' | '\\'' | SEP | '%' | '!' | '#' | ']' | '[' | '\\\\' | '=' | '\\?' | '(' | ')' | '&' )*
			loop253:
			while (true) {
				int alt253=19;
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
				case '#':
					{
					alt253=10;
					}
					break;
				case ']':
					{
					alt253=11;
					}
					break;
				case '[':
					{
					alt253=12;
					}
					break;
				case '\\':
					{
					alt253=13;
					}
					break;
				case '=':
					{
					alt253=14;
					}
					break;
				case '?':
					{
					alt253=15;
					}
					break;
				case '(':
					{
					alt253=16;
					}
					break;
				case ')':
					{
					alt253=17;
					}
					break;
				case '&':
					{
					alt253=18;
					}
					break;
				}
				switch (alt253) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:8: LETTERS
					{
					mLETTERS(); 

					}
					break;
				case 2 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:18: NUMBER
					{
					mNUMBER(); 

					}
					break;
				case 3 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:28: ':'
					{
					match(':'); 
					}
					break;
				case 4 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:34: SIGN
					{
					mSIGN(); 

					}
					break;
				case 5 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:41: '/'
					{
					match('/'); 
					}
					break;
				case 6 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1738:47: '\\''
					{
					match('\''); 
					}
					break;
				case 7 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:5: SEP
					{
					mSEP(); 

					}
					break;
				case 8 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:11: '%'
					{
					match('%'); 
					}
					break;
				case 9 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:17: '!'
					{
					match('!'); 
					}
					break;
				case 10 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:23: '#'
					{
					match('#'); 
					}
					break;
				case 11 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:29: ']'
					{
					match(']'); 
					}
					break;
				case 12 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:35: '['
					{
					match('['); 
					}
					break;
				case 13 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:41: '\\\\'
					{
					match('\\'); 
					}
					break;
				case 14 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:48: '='
					{
					match('='); 
					}
					break;
				case 15 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1739:54: '\\?'
					{
					match('?'); 
					}
					break;
				case 16 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1740:5: '('
					{
					match('('); 
					}
					break;
				case 17 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1740:11: ')'
					{
					match(')'); 
					}
					break;
				case 18 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1740:17: '&'
					{
					match('&'); 
					}
					break;

				default :
					break loop253;
				}
			}

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1742:9: ( ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1742:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+
			{
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1742:11: ( ( 'A' .. 'Z' ) | ( 'a' .. 'z' ) | ' ' )+
			int cnt254=0;
			loop254:
			while (true) {
				int alt254=2;
				int LA254_0 = input.LA(1);
				if ( (LA254_0==' '||(LA254_0 >= 'A' && LA254_0 <= 'Z')||(LA254_0 >= 'a' && LA254_0 <= 'z')) ) {
					alt254=1;
				}

				switch (alt254) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(254, input);
					throw eee;
				}
				cnt254++;
			}

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
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1748:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1748:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1748:22: ( '+' | '-' )?
			int alt255=2;
			int LA255_0 = input.LA(1);
			if ( (LA255_0=='+'||LA255_0=='-') ) {
				alt255=1;
			}
			switch (alt255) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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

			// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1748:33: ( '0' .. '9' )+
			int cnt256=0;
			loop256:
			while (true) {
				int alt256=2;
				int LA256_0 = input.LA(1);
				if ( ((LA256_0 >= '0' && LA256_0 <= '9')) ) {
					alt256=1;
				}

				switch (alt256) {
				case 1 :
					// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:
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
					EarlyExitException eee = new EarlyExitException(256, input);
					throw eee;
				}
				cnt256++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPONENT"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:8: ( AAM | APB | BEC | BOD | BWC | BWR | BWW | DBT | DBK | DBS | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RSD | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | ALR | VDM | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | TXT | PRO | DEVICE | DEV | NUMBER | WS | SEP | SIGN | SIGNED | TIME_STAMP | CHECKSUM | NAME | LETTERS )
		int alt257=56;
		alt257 = dfa257.predict(input);
		switch (alt257) {
			case 1 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:10: AAM
				{
				mAAM(); 

				}
				break;
			case 2 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:14: APB
				{
				mAPB(); 

				}
				break;
			case 3 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:18: BEC
				{
				mBEC(); 

				}
				break;
			case 4 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:22: BOD
				{
				mBOD(); 

				}
				break;
			case 5 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:26: BWC
				{
				mBWC(); 

				}
				break;
			case 6 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:30: BWR
				{
				mBWR(); 

				}
				break;
			case 7 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:34: BWW
				{
				mBWW(); 

				}
				break;
			case 8 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:38: DBT
				{
				mDBT(); 

				}
				break;
			case 9 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:42: DBK
				{
				mDBK(); 

				}
				break;
			case 10 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:46: DBS
				{
				mDBS(); 

				}
				break;
			case 11 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:50: DPT
				{
				mDPT(); 

				}
				break;
			case 12 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:54: GGA
				{
				mGGA(); 

				}
				break;
			case 13 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:58: GLL
				{
				mGLL(); 

				}
				break;
			case 14 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:62: GSA
				{
				mGSA(); 

				}
				break;
			case 15 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:66: GSV
				{
				mGSV(); 

				}
				break;
			case 16 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:70: HDG
				{
				mHDG(); 

				}
				break;
			case 17 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:74: HDM
				{
				mHDM(); 

				}
				break;
			case 18 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:78: HDT
				{
				mHDT(); 

				}
				break;
			case 19 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:82: MSK
				{
				mMSK(); 

				}
				break;
			case 20 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:86: MTA
				{
				mMTA(); 

				}
				break;
			case 21 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:90: MTW
				{
				mMTW(); 

				}
				break;
			case 22 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:94: MWD
				{
				mMWD(); 

				}
				break;
			case 23 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:98: MWV
				{
				mMWV(); 

				}
				break;
			case 24 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:102: RMB
				{
				mRMB(); 

				}
				break;
			case 25 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:106: RMC
				{
				mRMC(); 

				}
				break;
			case 26 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:110: RSD
				{
				mRSD(); 

				}
				break;
			case 27 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:114: RTE
				{
				mRTE(); 

				}
				break;
			case 28 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:118: VBW
				{
				mVBW(); 

				}
				break;
			case 29 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:122: VLW
				{
				mVLW(); 

				}
				break;
			case 30 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:126: VHW
				{
				mVHW(); 

				}
				break;
			case 31 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:130: VPW
				{
				mVPW(); 

				}
				break;
			case 32 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:134: VTG
				{
				mVTG(); 

				}
				break;
			case 33 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:138: VWR
				{
				mVWR(); 

				}
				break;
			case 34 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:142: VWT
				{
				mVWT(); 

				}
				break;
			case 35 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:146: XTE
				{
				mXTE(); 

				}
				break;
			case 36 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:150: ZDA
				{
				mZDA(); 

				}
				break;
			case 37 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:154: ALR
				{
				mALR(); 

				}
				break;
			case 38 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:158: VDM
				{
				mVDM(); 

				}
				break;
			case 39 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:162: GPSD_AIS
				{
				mGPSD_AIS(); 

				}
				break;
			case 40 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:171: GPSD_DEVICE
				{
				mGPSD_DEVICE(); 

				}
				break;
			case 41 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:183: GPSD_DEVICES
				{
				mGPSD_DEVICES(); 

				}
				break;
			case 42 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:196: GPSD_VERSION
				{
				mGPSD_VERSION(); 

				}
				break;
			case 43 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:209: GPSD_WATCH
				{
				mGPSD_WATCH(); 

				}
				break;
			case 44 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:220: TXT
				{
				mTXT(); 

				}
				break;
			case 45 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:224: PRO
				{
				mPRO(); 

				}
				break;
			case 46 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:228: DEVICE
				{
				mDEVICE(); 

				}
				break;
			case 47 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:235: DEV
				{
				mDEV(); 

				}
				break;
			case 48 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:239: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 49 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:246: WS
				{
				mWS(); 

				}
				break;
			case 50 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:249: SEP
				{
				mSEP(); 

				}
				break;
			case 51 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:253: SIGN
				{
				mSIGN(); 

				}
				break;
			case 52 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:258: SIGNED
				{
				mSIGNED(); 

				}
				break;
			case 53 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:265: TIME_STAMP
				{
				mTIME_STAMP(); 

				}
				break;
			case 54 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:276: CHECKSUM
				{
				mCHECKSUM(); 

				}
				break;
			case 55 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:285: NAME
				{
				mNAME(); 

				}
				break;
			case 56 :
				// /home/serge/Data/developement/ProjetNaVisu/navisu/navisu-domain/src/main/java/bzh/terrevirtuelle/navisu/domain/nmea/controller/parser/impl/NMEA.g:1:290: LETTERS
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
		"\1\uffff\2\3\2\uffff\1\3\1\uffff\1\3\1\uffff\1\3\2\uffff\1\3\1\uffff\1"+
		"\3\1\uffff\1\3";
	static final String DFA67_eofS =
		"\21\uffff";
	static final String DFA67_minS =
		"\1\56\2\54\2\uffff\1\54\1\53\1\54\1\53\1\54\1\53\1\60\1\54\1\60\1\54\1"+
		"\60\1\54";
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

	protected class DFA67 extends DFA {

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
		@Override
		public String getDescription() {
			return "558:9: (offset= NUMBER SEP |offset= NUMBER )";
		}
	}

	static final String DFA84_eotS =
		"\34\uffff";
	static final String DFA84_eofS =
		"\34\uffff";
	static final String DFA84_minS =
		"\1\40\1\uffff\4\40\1\uffff\3\40\1\60\3\40\1\60\3\40\1\60\1\40\1\60\2\40"+
		"\1\60\4\40";
	static final String DFA84_maxS =
		"\1\172\1\uffff\4\172\1\uffff\3\172\1\71\3\172\1\71\3\172\1\71\1\172\1"+
		"\71\2\172\1\71\4\172";
	static final String DFA84_acceptS =
		"\1\uffff\1\2\4\uffff\1\1\25\uffff";
	static final String DFA84_specialS =
		"\34\uffff}>";
	static final String[] DFA84_transitionS = {
			"\1\1\11\uffff\1\1\3\uffff\1\3\1\uffff\12\2\7\uffff\32\1\6\uffff\32\1",
			"",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\5\1\uffff\12\2\7\uffff\4\1\1"+
			"\4\25\1\6\uffff\4\1\1\4\25\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\7\7\uffff\4\1\1"+
			"\10\25\1\6\uffff\4\1\1\10\25\1",
			"\1\1\11\uffff\1\1\1\12\1\uffff\1\12\1\1\1\uffff\12\11\7\uffff\32\1\6"+
			"\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\13\7\uffff\4\1"+
			"\1\14\25\1\6\uffff\4\1\1\14\25\1",
			"",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\7\7\uffff\4\1\1"+
			"\15\25\1\6\uffff\4\1\1\15\25\1",
			"\1\1\11\uffff\1\1\1\16\1\uffff\1\16\1\1\1\uffff\12\17\7\uffff\32\1\6"+
			"\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\11\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\12\20",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\13\7\uffff\4\1"+
			"\1\21\25\1\6\uffff\4\1\1\21\25\1",
			"\1\1\11\uffff\1\1\1\22\1\uffff\1\22\1\1\1\uffff\12\23\7\uffff\32\1\6"+
			"\uffff\32\1",
			"\1\1\11\uffff\1\1\1\24\1\uffff\1\24\1\1\1\uffff\12\25\7\uffff\32\1\6"+
			"\uffff\32\1",
			"\12\26",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\17\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\11\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\27\1\uffff\1\27\1\1\1\uffff\12\30\7\uffff\32\1\6"+
			"\uffff\32\1",
			"\12\31",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\23\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\12\32",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\25\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\17\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\12\33",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\30\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\23\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\25\7\uffff\32\1"+
			"\6\uffff\32\1",
			"\1\1\11\uffff\1\1\1\uffff\1\6\1\uffff\1\1\1\uffff\12\30\7\uffff\32\1"+
			"\6\uffff\32\1"
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

	protected class DFA84 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 608:14: ( NUMBER SEP )*";
		}
	}

	static final String DFA126_eotS =
		"\23\uffff";
	static final String DFA126_eofS =
		"\23\uffff";
	static final String DFA126_minS =
		"\1\56\2\54\1\40\1\54\1\53\1\54\1\53\1\40\1\54\1\53\1\60\1\54\1\60\1\54"+
		"\2\uffff\1\60\1\54";
	static final String DFA126_maxS =
		"\1\71\2\145\1\172\1\145\1\71\1\145\1\71\1\172\1\145\5\71\2\uffff\2\71";
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
			"\1\10\11\uffff\1\17\1\uffff\1\20\24\uffff\32\10\6\uffff\32\10",
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

	protected class DFA126 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 827:5: (dev= NUMBER SEP we= LETTERS SEP )*";
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
			"\1\2\37\uffff\1\1\11\uffff\1\2\1\uffff\1\3\24\uffff\32\1\6\uffff\32"+
			"\1",
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

	protected class DFA147 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 971:11: ( LETTERS SEP )*";
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

	protected class DFA161 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 1049:3: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
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

	protected class DFA208 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 1181:13: ( LETTERS SEP )*";
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

	protected class DFA215 extends DFA {

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
		@Override
		public String getDescription() {
			return "()+ loopback of 1225:2: ( '\\u0021' .. '\\u007F' )+";
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

	protected class DFA220 extends DFA {

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
		@Override
		public String getDescription() {
			return "()+ loopback of 1290:2: ( '\\u0021' .. '\\u007F' )+";
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

	protected class DFA226 extends DFA {

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
		@Override
		public String getDescription() {
			return "()+ loopback of 1299:4: ( '\\u0021' .. '\\u007F' )+";
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

	protected class DFA236 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 1674:2: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
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

	protected class DFA238 extends DFA {

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
		@Override
		public String getDescription() {
			return "()* loopback of 1686:29: ( '\\u0021' .. '\\u007F' | SEP | ' ' )*";
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

	protected class DFA248 extends DFA {

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
		@Override
		public String getDescription() {
			return "1700:1: NUMBER : ( ( '0' .. '9' )+ | ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )* ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
		}
	}

	static final String DFA257_eotS =
		"\4\uffff\16\32\1\uffff\2\127\1\31\1\uffff\1\134\23\uffff\43\u0082\12\uffff"+
		"\1\127\1\uffff\1\127\67\uffff\1\127\2\uffff\1\127\1\uffff\1\127\35\uffff"+
		"\1\127\u0081\uffff";
	static final String DFA257_eofS =
		"\u0137\uffff";
	static final String DFA257_minS =
		"\1\11\1\101\1\uffff\1\42\1\120\1\111\1\107\1\103\1\106\2\103\1\101\1\104"+
		"\1\111\1\104\1\111\1\130\1\101\1\40\1\56\1\60\1\40\1\uffff\1\56\4\uffff"+
		"\1\120\1\111\1\107\1\103\1\106\2\103\1\101\1\104\1\111\1\104\1\111\1\130"+
		"\1\101\1\143\51\40\1\uffff\1\40\2\uffff\1\60\1\53\1\60\1\53\2\uffff\43"+
		"\101\1\154\2\uffff\10\40\2\uffff\4\40\1\60\1\53\4\60\1\101\1\105\1\102"+
		"\1\107\1\104\1\123\1\115\1\102\3\uffff\1\141\13\40\1\uffff\4\40\2\60\5"+
		"\uffff\1\103\1\113\3\uffff\1\101\1\107\1\uffff\1\101\1\104\1\102\7\uffff"+
		"\1\122\1\163\25\40\23\uffff\1\163\22\40\1\42\14\40\1\72\7\40\1\42\6\40"+
		"\1\101\4\40\1\uffff\1\105\2\uffff\1\40\1\126\1\111\1\103\1\105\1\42\2"+
		"\uffff";
	static final String DFA257_maxS =
		"\1\173\1\132\1\uffff\1\42\1\120\1\116\1\120\1\130\1\115\1\122\1\116\1"+
		"\101\1\123\1\122\1\127\1\111\1\130\1\126\1\172\2\145\1\172\1\uffff\1\71"+
		"\4\uffff\1\120\1\116\1\120\1\130\1\115\1\122\1\116\1\101\1\123\1\122\1"+
		"\127\1\111\1\130\1\126\1\143\51\172\1\uffff\1\172\2\uffff\1\145\1\71\1"+
		"\145\1\71\2\uffff\43\132\1\154\2\uffff\10\172\2\uffff\4\172\1\145\5\71"+
		"\1\120\1\127\1\120\1\123\1\104\1\127\1\124\1\127\3\uffff\1\141\13\172"+
		"\1\uffff\4\172\2\71\5\uffff\1\127\1\124\3\uffff\1\126\1\124\1\uffff\1"+
		"\127\1\126\1\103\7\uffff\1\124\1\163\25\172\23\uffff\1\163\22\172\1\42"+
		"\14\172\1\72\7\172\1\42\6\172\1\127\4\172\1\uffff\1\105\2\uffff\1\172"+
		"\1\126\1\111\1\103\1\105\1\123\2\uffff";
	static final String DFA257_acceptS =
		"\2\uffff\1\46\23\uffff\1\62\1\uffff\1\66\1\61\1\70\1\55\70\uffff\1\57"+
		"\1\uffff\1\67\1\60\4\uffff\1\63\1\64\44\uffff\1\56\1\57\10\uffff\1\57"+
		"\1\65\22\uffff\1\43\1\44\1\54\14\uffff\1\65\6\uffff\1\1\1\2\1\45\1\3\1"+
		"\4\2\uffff\1\13\1\14\1\15\2\uffff\1\23\3\uffff\1\32\1\33\1\34\1\35\1\36"+
		"\1\37\1\40\27\uffff\1\5\1\6\1\7\1\10\1\11\1\12\1\16\1\17\1\20\1\21\1\22"+
		"\1\24\1\25\1\26\1\27\1\30\1\31\1\41\1\42\64\uffff\1\47\1\uffff\1\52\1"+
		"\53\6\uffff\1\50\1\51";
	static final String DFA257_specialS =
		"\u0137\uffff}>";
	static final String[] DFA257_transitionS = {
			"\2\31\2\uffff\1\31\22\uffff\1\25\1\2\1\22\1\uffff\1\1\5\uffff\1\30\1"+
			"\27\1\26\1\27\1\24\1\uffff\12\23\7\uffff\1\6\1\32\1\7\1\10\1\11\1\32"+
			"\1\4\1\12\1\5\10\32\1\13\1\14\1\15\1\32\1\16\1\17\1\32\1\20\1\21\6\uffff"+
			"\32\32\1\3",
			"\1\36\1\uffff\1\37\1\40\1\41\1\uffff\1\34\1\42\1\35\6\uffff\1\33\1\uffff"+
			"\1\43\1\44\1\45\1\uffff\1\46\1\47\1\uffff\1\50\1\51",
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
			"\1\116\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1\123"+
			"\1\117\12\122\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116\3\126"+
			"\3\uffff\32\116",
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
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\123\1\117\12\122\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116\3"+
			"\126\3\uffff\32\116",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0086\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32\u0084\3\126"+
			"\3\uffff\32\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\123\1\117\12\122\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116\3"+
			"\126\3\uffff\32\116",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0086\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32\u0084\3\126"+
			"\3\uffff\32\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\122\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116\1"+
			"\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\123\1\117\12\u008a\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116\1"+
			"\u008b\25\116\3\126\3\uffff\4\116\1\u008b\25\116",
			"",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u008f\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"",
			"",
			"\12\u0092\13\uffff\1\u0093\37\uffff\1\u0093",
			"\1\u0094\1\uffff\1\u0094\2\uffff\12\u0095",
			"\12\132\13\uffff\1\133\37\uffff\1\133",
			"\1\u0096\1\uffff\1\u0096\2\uffff\12\u0097",
			"",
			"",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u0098\1\u0099\1\uffff\1\u009a\2\uffff\1\u009b\1\u009c\4\uffff\1\u009d"+
			"\4\uffff\1\u009e\1\uffff\1\u00a2\1\uffff\1\u009f\1\uffff\1\u00a0\1\uffff"+
			"\1\u00a1",
			"\1\u00a3",
			"",
			"",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0086\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32\u0084\3\126"+
			"\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0086\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32\u0084\3\126"+
			"\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u0086\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u00a6\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a7"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a7\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u00a9\1\126\1\u00a9"+
			"\1\123\1\117\12\u00a8\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\123\1\117\12\u00aa\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116\1"+
			"\u00ab\25\116\3\126\3\uffff\4\116\1\u00ab\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u008a\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ac\25\116\3\126\3\uffff\4\116\1\u00ac\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u00ad\1\126\1\u00ad"+
			"\1\123\1\117\12\u00ae\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"",
			"",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u008f\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u008f\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00b2\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b3\25\u008e\3\126\3\uffff\4\u008e\1\u00b3\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u008f\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\12\u0092\13\uffff\1\u0093\37\uffff\1\u0093",
			"\1\u00b4\1\uffff\1\u00b4\2\uffff\12\u00b5",
			"\12\u0095",
			"\12\u0095",
			"\12\u0097",
			"\12\u0097",
			"\1\u00b6\12\uffff\1\u00b8\3\uffff\1\u00b7",
			"\1\u00b9\11\uffff\1\u00ba\7\uffff\1\u00bb",
			"\1\u00bc\15\uffff\1\u00bd",
			"\1\u00be\4\uffff\1\u00bf\6\uffff\1\u00c0",
			"\1\u00c1",
			"\1\u00c2\1\u00c3\2\uffff\1\u00c4",
			"\1\u00c5\5\uffff\1\u00c6\1\u00c7",
			"\1\u00c8\5\uffff\1\u00ca\3\uffff\1\u00c9\3\uffff\1\u00cb\3\uffff\1\u00cc"+
			"\2\uffff\1\u00cd",
			"",
			"",
			"",
			"\1\u00ce",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u00d0\1\126\1\u00d0"+
			"\1\u0087\1\117\12\u00cf\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u00d1\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00d2"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00d2\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00a6\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00d3"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00d3\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u00d4\1\126\1\u00d4"+
			"\1\u0087\1\117\12\u00d5\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u00a8\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00d6\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u00aa\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00d7\25\116\3\126\3\uffff\4\116\1\u00d7\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u00d8\1\126\1\u00d8"+
			"\1\123\1\117\12\u00d9\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u00da\1\126\1\u00da"+
			"\1\123\1\117\12\u00db\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00dc\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u00ae\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\u00de\1\126\1\u00de"+
			"\1\u0090\1\126\12\u00dd\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00df\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00e0\25\u008e\3\126\3\uffff\4\u008e\1\u00e0\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u00b2\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00e1\25\u008e\3\126\3\uffff\4\u008e\1\u00e1\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\u00e2\1\126\1\u00e2"+
			"\1\u0090\1\126\12\u00e3\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\12\u00b5",
			"\12\u00b5",
			"",
			"",
			"",
			"",
			"",
			"\1\u00e4\16\uffff\1\u00e5\4\uffff\1\u00e6",
			"\1\u00e8\7\uffff\1\u00e9\1\u00e7",
			"",
			"",
			"",
			"\1\u00ea\24\uffff\1\u00eb",
			"\1\u00ec\5\uffff\1\u00ed\6\uffff\1\u00ee",
			"",
			"\1\u00ef\25\uffff\1\u00f0",
			"\1\u00f1\21\uffff\1\u00f2",
			"\1\u00f3\1\u00f4",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00f5\1\uffff\1\u00f6",
			"\1\u00f7",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00cf\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u00f8\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00d1\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00f9"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00f9\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u00fa\1\126\1\u00fa"+
			"\1\u0087\1\117\12\u00fb\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u00fc\1\126\1\u00fc"+
			"\1\u0087\1\117\12\u00fd\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u00fe\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00d5\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u00a8\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u0101\1\126\1\u0101"+
			"\1\123\1\117\12\u0102\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0103\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u00d9\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0104\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u00db\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u00ae\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u00dd\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00dd\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u00df\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u0105\25\u008e\3\126\3\uffff\4\u008e\1\u0105\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\u0106\1\126\1\u0106"+
			"\1\u0090\1\126\12\u0107\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\u0108\1\126\1\u0108"+
			"\1\u0090\1\126\12\u0109\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u00e3\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u00e3\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
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
			"\1\u010a",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u00cf\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u010d\1\126\1\u010d"+
			"\1\u0087\1\117\12\u010e\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u010f\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00fb\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u0110\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u00fd\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u00d5\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u00de\1\126\1\u00de"+
			"\1\123\1\117\12\u0111\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\123\1\117\12\u0112\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116\1"+
			"\u0113\25\116\3\126\3\uffff\4\116\1\u0113\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0114\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u0102\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u00d9\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u00db\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\u0115\1\126\1\u0115"+
			"\1\u0090\1\126\12\u0116\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0107\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u0107\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0109\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u0109\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
			"\1\u0117",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0118\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32\u0084\3\126"+
			"\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u0087\1\117"+
			"\12\u0119\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u011a"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u011a\25\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u011b\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u010e\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u00fb\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u00fd\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u0111\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u0112\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u011c\25\116\3\126\3\uffff\4\116\1\u011c\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u011d\1\126\1\u011d"+
			"\1\123\1\117\12\u011e\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u0102\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0116\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u00b1\1\126\12\u0116\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\4"+
			"\u008e\1\u00b0\25\u008e\3\126\3\uffff\4\u008e\1\u00b0\25\u008e",
			"\1\u011f",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u0118\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u0119\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u0120"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u0120\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u0121\1\126\1\u0121"+
			"\1\u0087\1\117\12\u0122\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u010e\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\u0123\1\126\1\u0123"+
			"\1\123\1\117\12\u0124\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\32\116"+
			"\3\126\3\uffff\32\116",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u0125\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u011e\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\u0126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\1\u0127\1\126\1\u0127"+
			"\1\u0087\1\117\12\u0128\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u0084\3\126\3\uffff\32\u0084",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u0129\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u0122\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u008e\1\126\1\u008d\1\126\1\uffff\5\126\1\uffff\1\125\1\126\1\125"+
			"\1\u0090\1\126\12\u012a\1\u0091\2\uffff\1\126\1\uffff\1\126\1\uffff\32"+
			"\u008e\3\126\3\uffff\32\u008e",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0089\1\117\12\u0124\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u0088\25\116\3\126\3\uffff\4\116\1\u0088\25\116",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u011e\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"\1\u012b\2\uffff\1\u012c\21\uffff\1\u012d\1\u012e",
			"\4\126\1\uffff\5\126\1\uffff\5\126\12\u012f\1\126\2\uffff\1\126\1\uffff"+
			"\1\126\1\uffff\35\126\3\uffff\32\126",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u00a5\1\117"+
			"\12\u0128\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u00a4"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u00a4\25\u0084",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u0122\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\116\1\126\1\u0083\1\121\1\uffff\5\126\1\uffff\1\125\1\126\1\125\1"+
			"\u0100\1\117\12\u0124\1\120\2\uffff\1\126\1\uffff\1\126\1\uffff\4\116"+
			"\1\u00ff\25\116\3\126\3\uffff\4\116\1\u00ff\25\116",
			"",
			"\1\u0130",
			"",
			"",
			"\1\u0084\1\126\1\124\1\121\1\uffff\5\126\1\uffff\3\126\1\u010c\1\117"+
			"\12\u0128\1\u0085\2\uffff\1\126\1\uffff\1\126\1\uffff\4\u0084\1\u010b"+
			"\25\u0084\3\126\3\uffff\4\u0084\1\u010b\25\u0084",
			"\1\u0131",
			"\1\u0132",
			"\1\u0133",
			"\1\u0134",
			"\1\u0135\60\uffff\1\u0136",
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

	protected class DFA257 extends DFA {

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
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( AAM | APB | BEC | BOD | BWC | BWR | BWW | DBT | DBK | DBS | DPT | GGA | GLL | GSA | GSV | HDG | HDM | HDT | MSK | MTA | MTW | MWD | MWV | RMB | RMC | RSD | RTE | VBW | VLW | VHW | VPW | VTG | VWR | VWT | XTE | ZDA | ALR | VDM | GPSD_AIS | GPSD_DEVICE | GPSD_DEVICES | GPSD_VERSION | GPSD_WATCH | TXT | PRO | DEVICE | DEV | NUMBER | WS | SEP | SIGN | SIGNED | TIME_STAMP | CHECKSUM | NAME | LETTERS );";
		}
	}

}
