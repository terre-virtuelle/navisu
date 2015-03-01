 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.ship.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author morvan
 */
public class Ship implements Serializable, Cloneable {

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
    private String name = "";
    /**
     * Country of the ship
     *
     */
    private String country = "";

    private double latitude;
    private double longitude;
    /**
     * True heading Degrees (0-359) (511 indicates not available = default)
     */
    private double heading;
    /**
     * Course over ground in 1/10deg (0-3 599). 3 600 (E10h) = not available =
     * default; 3 601-4 095 should not be used
     */
    private double cog;
    /**
     * Speed over ground in 1/10 knot steps (0-102.2 knots) 1 023 = not
     * available, 1 022 = 102.2 knots or higher
     */
    private double sog;
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
    private double rot;
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
     * http://gpsd.berlios.de/AIVDM.html#_type_5_static_and_voyage_related_data
     * Type of ship and cargo ship 0 = not available or no ship = default 1-99 =
     * as defined below 100-199 = reserved, for regional use 200-255 = reserved,
     * for future use 50 Pilot vessel 51 Search and rescue vessels 52 Tugs 53
     * Port tenders 54 Vessels with anti-pollution facilities or equipment 55
     * Law enforcement vessels 56 Spare - for assignments to local vessels 57
     * Spare - for assignments to local vessels 58 Medical transports (as
     * defined in the 1949 Geneva Conventions and Additional Protocols) 59 Ships
     * according to RR Resolution No. 18 (Mob-83)
     */
    private int shipType;
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
    private String callSign = "";
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
    private String destination = "";
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
    }

    public Ship(int mmsi) {
        this.mmsi = mmsi;
    }

    public Ship(int mmsi, double latitude, double longitude) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Ship(int mmsi, double heading, double cog, double sog, double rot, double latitude, double longitude, int navigationalStatus) {
        this.mmsi = mmsi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.cog = cog;
        this.sog = sog;
        this.rot = rot;
        this.navigationalStatus = navigationalStatus;
    }

    public Ship(int mmsi, String name, String country,
            float width, float length, float draught,
            int shipType, int navigationalStatus, int electronicPositionDevice, String callSign) {
        this.mmsi = mmsi;
        this.name = name;
        this.country = country;
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.shipType = shipType;
        this.navigationalStatus = navigationalStatus;
        this.electronicPositionDevice = electronicPositionDevice;
        this.callSign = callSign;
    }


    public Ship(int mmsi, String name, int shipType) {
        this.mmsi = mmsi;
        this.name = name;
        this.shipType = shipType;
    }

    public Ship(int mmsi, int imo, String name,
            float heading, float cog, float sog, float rot,
            double latitude, double longitude,
            float width, float length, float draught,
            int type, int navigationalStatus, int electronicPositionDevice, String callSign,
            Calendar ETA, String destination, String country) {
        this.mmsi = mmsi;
        this.imo = imo;
        this.name = name;
        this.heading = heading;
        this.cog = cog;
        this.sog = sog;
        this.rot = rot;
        this.latitude = latitude;
        this.longitude = longitude;
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.shipType = type;
        this.navigationalStatus = navigationalStatus;
        this.electronicPositionDevice = electronicPositionDevice;
        this.callSign = callSign;
        this.country = country;
        this.ETA = ETA;
        this.destination = destination;
        this.country = country;
    }

    public int getMMSI() {
        return mmsi;
    }

    public void setMMSI(int mmsi) {
        this.mmsi = mmsi;
    }

    public int getIMO() {
        return imo;
    }

    public void setIMO(int imo) {
        this.imo = imo;
    }

    public String getName() {
        return name;
    }

    public String getShipName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShipName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public double getSog() {
        return sog;
    }

    public void setSog(double sog) {
        this.sog = sog;
    }

    public double getRot() {
        return rot;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getDraught() {
        return draught;
    }

    public void setDraught(float draught) {
        this.draught = draught;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public int getNavigationalStatus() {
        return navigationalStatus;
    }

    public void setNavigationalStatus(int navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
    }

    public int getElectronicPositionDevice() {
        return electronicPositionDevice;
    }

    public void setElectronicPositionDevice(int electronicPositionDevice) {
        this.electronicPositionDevice = electronicPositionDevice;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public Calendar getETA() {
        return ETA;
    }

    public void setETA(Calendar ETA) {
        this.ETA = ETA;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

    @Override
    public String toString() {
        return "Ship{" + "mmsi=" + mmsi + ", imo=" + imo + ", name=" + name + ", country=" + country + ", latitude=" + latitude + ", longitude=" + longitude + ", heading=" + heading + ", cog=" + cog + ", sog=" + sog + ", rot=" + rot + ", width=" + width + ", length=" + length + ", draught=" + draught + ", shipType=" + shipType + ", navigationalStatus=" + navigationalStatus + ", electronicPositionDevice=" + electronicPositionDevice + ", callSign=" + callSign + ", ETA=" + ETA + ", destination=" + destination + ", year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute=" + minute + '}';
    }

}
