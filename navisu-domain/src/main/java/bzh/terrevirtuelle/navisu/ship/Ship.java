/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ship;

import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author morvan
 */
public class Ship implements Serializable {

    /**
     * MMSI number :1-999999999; 0 = not available = default
     */
    private int mmsi;
    /**
     * IMO number :1-999999999; 0 = not available = default
     */
    private int imo;
    /**
     * Name of the ship : Maximum 20 characters 6-bit ASCII, as defined in Table
     * 44.
     *
     * @@@@@@@@@@@@@@@@@@@@ = not available = default
     */
    private String name;
    /**
     * Country of the ship
     *
     */
    private String country;
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    /**
     * True heading Degrees (0-359) (511 indicates not available = default)
     */
    private DoubleProperty heading;
    /**
     * Course over ground in 1/10deg (0-3 599). 3 600 (E10h) = not available =
     * default; 3 601-4 095 should not be used
     */
    private DoubleProperty cog;
    /**
     * Speed over ground in 1/10 knot steps (0-102.2 knots) 1 023 = not
     * available, 1 022 = 102.2 knots or higher
     */
    private DoubleProperty sog;
    /**
     * Rate of turn 0 to +126 = turning right at up to 708 deg per min or higher
     * 0 to -126 = turning left at up to 708 deg per min or higher Values
     * between 0 and 708 deg per min coded by ROTAIS = 4.733 SQRT(ROTsensor)
     * degrees per min where ROTsensor is the Rate of Turn as input by an
     * external Rate of Turn Indicator (TI). ROTAIS is rounded to the nearest
     * integer value. +127 = turning right at more than 5 deg per 30 s (No TI
     * available) -127 = turning left at more than 5 deg per 30 s (No TI
     * available) -128 (80 hex) indicates no turn information available
     * (default). ROT data should not be derived from COG information.
     */
    private DoubleProperty rot;
    /**
     * Width of the ship
     */
    private float width;
    /**
     * Length of the ship
     */
    private float length;
    /**
     * Maximum present static draught In 1/10 m, 255 = draught 25.5 m or
     * greater, 0 = not available = default; in accordance with IMO Resolution
     * A.851
     */
    private float draught;
    /**
     * Type of ship and cargo ship 0 = not available or no ship = default 1-99 =
     * as defined below 100-199 = reserved, for regional use 200-255 = reserved,
     * for future use 50 Pilot vessel 51 Search and rescue vessels 52 Tugs 53
     * Port tenders 54 Vessels with anti-pollution facilities or equipment 55
     * Law enforcement vessels 56 Spare - for assignments to local vessels 57
     * Spare - for assignments to local vessels 58 Medical transports (as
     * defined in the 1949 Geneva Conventions and Additional Protocols) 59 Ships
     * according to RR Resolution No. 18 (Mob-83)
     */
    private int type;
    /**
     * 0 = under way using engine, 1 = at anchor, 2 = not under command, 3 =
     * restricted maneuverability, 4 = constrained by her draught, 5 = moored, 6
     * = aground, 7 = engaged in fishing, 8 = under way sailing, 9 = reserved
     * for future amendment of navigational status for ships carrying DG, HS, or
     * MP, or IMO hazard or pollutant category C, high speed craft (HSC), 10 =
     * reserved for future amendment of navigational status for ships carrying
     * dangerous goods (DG), harmful substances (HS) or marine pollutants (MP),
     * or IMO hazard or pollutant category A, wing in grand (WIG); 11-13 =
     * reserved for future use, 14 = AIS-SART (active), 15 = not defined =
     * default (also used by AIS-SART under test)
     */
    private int navigationalStatus;
    /**
     * 0 = Undefined (default); 1 = GPS, 2 = GLONASS, 3 = combined GPS/GLONASS,
     * 4 = Loran-C, 5 = Chayka, 6 = integrated navigation system, 7 = surveyed;
     * 8 = Galileo, 9-15 = not used
     */
    private int electronicPositionDevice;
    /**
     * 7 = 6 bit ASCII characters, @@@@@@@ = not available = default
     */
    private String callSign;
    /**
     * Estimated time of arrival; MMDDHHMM UTC Bits 19-16: month; 1-12; 0 = not
     * available = default Bits 15-11: day; 1-31; 0 = not available = default
     * Bits 10-6: hour; 0-23; 24 = not available = default
     */
    private Calendar ETA;
    /**
     * Maximum 20 characters using 6-bit ASCII;
     *
     * @@@@@@@@@@@@@@@@@@@@ = not available
     */
    private String destination;
    /**
     * year, month, day of ETA
     */
    private int year, month, day;
    /**
     * hour, minute of ETA
     */
    private int hour, minute;

    /**
     * Creates a new instance of Ship
     */
    public Ship() {
        this.latitude = new SimpleDoubleProperty(0);
        this.longitude = new SimpleDoubleProperty(0);
        this.heading = new SimpleDoubleProperty(0);
        this.cog = new  SimpleDoubleProperty(0);
        this.sog = new  SimpleDoubleProperty(0);
    }

    /**
     * Creates a new instance of Ship
     *
     * @param mmsi
     */
    public Ship(int mmsi) {
        this.mmsi = mmsi;
    }
    
    public Ship(int mmsi, int imo, String name,
            float heading, float cog, float sog, float rot,
            float latitude, float longitude,
            float width, float length, float draught,
            int type, int navigationalStatus, int electronicPositionDevice, String callSign,
            Calendar ETA, String destination) {
        this.mmsi = mmsi;
        this.imo = imo;
        this.name = name;
        this.heading = new SimpleDoubleProperty(heading);
        this.cog = new SimpleDoubleProperty(cog);
        this.sog = new SimpleDoubleProperty(sog);
        this.rot = new SimpleDoubleProperty(rot);
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.type = type;
        this.navigationalStatus = navigationalStatus;
        this.electronicPositionDevice = electronicPositionDevice;
        this.callSign = callSign;
        this.ETA = ETA;
        this.destination = destination;
    }
    
    @Override
    public String toString() {
        String s = "[" + "MMSI : " + mmsi
                + ", IMO" + imo
                + ", name : " + name
                + ", country : " + country
                + ", lat : " + getLatitude()
                + ", lon : " + getLongitude()
                + ", heading : " + heading
                + ", cog : " + cog
                + ", sog : " + sog
                + ", rot : " + rot
                + ", width : " + width
                + ", length : " + length
                + ", draught : " + draught
                + ", type : " + type
                + ", navigationalStatus : " + navigationalStatus
                + ", callSign : " + callSign
                + ", destination : " + destination
                + ", ETA : " + ETA
                + "]";
        return s;
    }
    
    public int getMonth() {
        return month;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public DoubleProperty latitudeProperty() {
        return this.latitude;
    }

    /**
     *
     * @return
     */
    public double getLatitude() {
        return this.latitudeProperty().get();
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitudeProperty().set(latitude);
    }
    
    public DoubleProperty longitudeProperty() {
        return this.longitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitudeProperty().get();
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        
        this.longitudeProperty().set(longitude);
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Calendar getETA() {
        return ETA;
    }
    
    public void setETA(Calendar ETA) {
        this.ETA = ETA;
    }
    
    public int getDay() {
        return day;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    
    public int getHour() {
        return hour;
    }
    
    public void setHour(int hour) {
        this.hour = hour;
    }
    
    public int getMinute() {
        return minute;
    }
    
    public void setMinute(int minute) {
        this.minute = minute;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getCallSign() {
        return callSign;
    }
    
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }
    
    public DoubleProperty cogProperty() {
        return cog;
    }
    
    public double getCog() {
        return cogProperty().get();
    }
    
    public void setCog(float cog) {
        this.cogProperty().set(cog);
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public float getDraught() {
        return draught;
    }
    
    public void setDraught(float draught) {
        this.draught = draught;
    }
    
    public int getElectronicPositionDevice() {
        return electronicPositionDevice;
    }
    
    public void setElectronicPositionDevice(int electronicPositionDevice) {
        this.electronicPositionDevice = electronicPositionDevice;
    }
    
    public DoubleProperty headingProperty() {
        return this.heading;
    }
    
    public double getHeading() {
        return headingProperty().get();
    }
    
    public void setHeading(float heading) {
        this.headingProperty().set(heading);
    }
    
    public int getImo() {
        return imo;
    }
    
    public void setImo(int imo) {
        this.imo = imo;
    }
    
    public float getLength() {
        return length;
    }
    
    public void setLength(float length) {
        this.length = length;
    }
    
    public int getMmsi() {
        return mmsi;
    }
    
    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNavigationalStatus() {
        return navigationalStatus;
    }
    
    public void setNavigationalStatus(int navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
    }

    public DoubleProperty rotProperty() {
        return rot;
    }

    public double getRot() {
        return rotProperty().get();
    }
    
    public void setRot(float rot) {
        this.rotProperty().set(rot);
    }
    
    public DoubleProperty sogProperty() {
        return sog;
    }
    
    public double getSog() {
        return sogProperty().get();
    }
    
    public void setSog(float sog) {
        this.sogProperty().set(sog);
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public float getWidth() {
        return width;
    }
    
    public void setWidth(float width) {
        this.width = width;
    }
    
}
