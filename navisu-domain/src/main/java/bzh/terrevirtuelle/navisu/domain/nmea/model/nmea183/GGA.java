package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Global Positionning System Fix Data Time, Position and fix for a GPS receiver
 * @author Serge MORVAN
 */
@XmlRootElement(name="GGA")
@XmlAccessorType(XmlAccessType.FIELD)
public class GGA
        extends NMEA {

    private Calendar utc;
    private float latitude;
    private float longitude;
    private int gpsQualityIndicator;
    private int numberOfSatellitesInView;
    private float horizontalDilutionOfPrecision;
    private float antennaAltitude;
    private String unitsOfAntennaAltitude = "M";
    private float geoidAltitude;
    private String unitsOfGeoidAltitude = "M";
/**
     * GGA - essential fix data which provide 3D location and accuracy data.
     *
     * $GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
     *
     * Where: GGA Global Positioning System Fix Data 123519 Fix taken at
     * 12:35:19 UTC 
     * 4807.038,N Latitude 48 deg 07.038' N 
     * 01131.000,E Longitude 11 deg 31.000' E 
     * 1 Fix quality: 0 = invalid 1 = GPS fix (SPS) 2 = DGPS
     * fix 3 = PPS fix 4 = Real Time Kinematic 5 = Float RTK 6 = estimated (dead
     * reckoning) (2.3 feature) 7 = Manual input mode 8 = Simulation mode 08
     * Number of satellites being tracked 0.9 Horizontal dilution of position
     * 545.4,M Altitude, Meters, above mean sea level 46.9,M Height of geoid
     * (mean sea level) above WGS84 ellipsoid (empty field) time in seconds
     * since last DGPS update (empty field) 
     * DGPS station ID checksum data, always begins with *
     *
     * If the height of geoid is missing then the altitude should be suspect.
     * Some non-standard implementations report altitude with respect to the
     * ellipsoid rather than geoid altitude. Some units do not report negative
     * altitudes at all. This is the only sentence that reports altitude.
     *
     * @param device
     * @param sentence
     * @param utc
     * @param latitude
     * @param longitude
     * @param gpsQualityIndicator
     * @param numberOfSatellitesInView
     * @param horizontalDilutionOfPrecision
     * @param antennaAltitude
     * @param unitsOfAntennaAltitude
     * @param geoidAltitude
     * @param unitsOfGeoidAltitude
     */
    public GGA(
            String device,
            String sentence,
            Calendar utc,
            float latitude,
            float longitude,
            int gpsQualityIndicator,
            int numberOfSatellitesInView,
            float horizontalDilutionOfPrecision,
            float antennaAltitude,
            String unitsOfAntennaAltitude,
            float geoidAltitude,
            String unitsOfGeoidAltitude) {
        super(device, sentence);
        this.utc = utc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gpsQualityIndicator = gpsQualityIndicator;
        this.numberOfSatellitesInView = numberOfSatellitesInView;
        this.horizontalDilutionOfPrecision = horizontalDilutionOfPrecision;
        this.antennaAltitude = antennaAltitude;
        this.unitsOfAntennaAltitude = unitsOfAntennaAltitude;
        this.geoidAltitude = geoidAltitude;
        this.unitsOfGeoidAltitude = unitsOfGeoidAltitude;
    }

    /**
     * Default constructor
     */
    public GGA() {
    }
    /**
     * Get the value of utc
     *
     * @return the value of utc
     */
   public String getTime() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss"); 
        return formater.format(utc.getTime());
    }

    /**
     * Set the value of utc
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.utc = date;
    }

    public String getUnitsOfGeoidAltitude() {
        return unitsOfGeoidAltitude;
    }

    public void setUnitsOfGeoidAltitude(String unitsOfGeoidAltitude) {
        this.unitsOfGeoidAltitude = unitsOfGeoidAltitude;
    }

    

    /**
     * Returns the latitude of the ship
     * @return 
     */
    public float getLatitude() {
        return this.latitude;
    }

    /**
     * Returns the Longitude of the ship
     * @return 
     */
    public float getLongitude() {
        return this.longitude;
    }

    /**
     * Setters for the longitude of the ship
     * @param value 
     */
    public void setLongitude(float value) {
        this.longitude = value;
    }

    /**
     * Return the precision of th GPS
     * @return 
     */
    public int getGpsQualityIndicator() {
        return this.gpsQualityIndicator;
    }

    /**
     * Setters for the precision of th GPS
     * @param value 
     */
    public void setGpsQualityIndicator(int value) {
        this.gpsQualityIndicator = value;
    }

    /**
     * Returns the count of satellites in view
     * @return 
     */
    public int getNumberOfSatellitesInView() {
        return this.numberOfSatellitesInView;
    }

    /**
     * Setters for the count of satellites in view
     * @param value 
     */
    public void setNumberOfSatellitesInView(int value) {
        this.numberOfSatellitesInView = value;
    }

    /**
     * Return the horizontalDilutionOfPrecision : HDOP
     * @return 
     */
    public float getHorizontalDilutionOfPrecision() {
        return this.horizontalDilutionOfPrecision;
    }

    /**
     * Setters for the horizontalDilutionOfPrecision
     * @param value 
     */
    public void setHorizontalDilutionOfPrecision(float value) {
        this.horizontalDilutionOfPrecision = value;
    }

    /**
     * Returns the altitude of the antenna of the ship
     * @return 
     */
    public float getAntennaAltitude() {
        return this.antennaAltitude;
    }

    /**
     * Setters for the altitude of the antenna of the ship
     * @param value 
     */
    public void setAntennaAltitude(float value) {
        this.antennaAltitude = value;
    }

   
    /**
     * Returns units of antenna altitude, default : meters
     * @return 
     */
    public String getUnitsOfAntennaAltitude() {
        return this.unitsOfAntennaAltitude;
    }

    /**
     * Setters for units of antenna altitude
     * @param value 
     */
    public void setUnitsOfAntennaAltitude(String value) {
        this.unitsOfAntennaAltitude = value;
    }

    /**
     *
     * @param geoidAltitude
     */
    public void setGeoidAltitude(float geoidAltitude) {
        this.geoidAltitude = geoidAltitude;
    }

    /**
     *
     * @return
     */
    public float getGeoidAltitude() {
        return geoidAltitude;
    }

    public Calendar getUtc() {
        return utc;
    }

    public void setUtc(Calendar utc) {
        this.utc = utc;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "GGA{" + "UTC=" + formater.format(utc.getTime()) 
                + ", latitude=" + latitude 
                + ", longitude=" + longitude 
                + ", gpsQualityIndicator=" + gpsQualityIndicator 
                + ", numberOfSatellitesInView=" + numberOfSatellitesInView 
                + ", horizontalDilutionOfPrecision=" + horizontalDilutionOfPrecision 
                + ", antennaAltitude=" + antennaAltitude 
                + ", unitsOfAntennaAltitude=" + unitsOfAntennaAltitude  
                + ", geoidAltitude=" + geoidAltitude 
                + ", unitsOfGeoidAltitude=" + unitsOfGeoidAltitude
                + '}';
    }
    
}
