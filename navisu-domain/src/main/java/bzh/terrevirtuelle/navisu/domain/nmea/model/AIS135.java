/**
 *
 * @author Morvan
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ais135")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS135 extends AISMessage {

    // Types 1,2,3
    // http://www.navcen.uscg.gov/index.php
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
    protected float rot;
    /**
     * Course over ground in 1/10deg (0-3 599). 3 600 (E10h) = not available =
     * default; 3 601-4 095 should not be used
     */
    protected float cog;
    /**
     * Speed over ground in 1/10 knot steps (0-102.2 knots) 1 023 = not
     * available, 1 022 = 102.2 knots or higher
     */
    protected float sog;
    /**
     * 0 = under way using engine, 1 = at anchor, 2 = not under command, 3 =
 restricted maneuverability, 4 = constrained by her draught, 5 = moored, 6
 = aground, 7 = engaged in fishing, 8 = under way sailing, 9 = reserved
 for future amendment of navigational status for ships carrying DG, HS, or
 MP, or imo hazard or pollutant category C, high speed craft (HSC), 10 =
 reserved for future amendment of navigational status for ships carrying
 dangerous goods (DG), harmful substances (HS) or marine pollutants (MP),
 or imo hazard or pollutant category A, wing in grand (WIG); 11-13 =
 reserved for future use, 14 = AIS-SART (active), 15 = not defined =
 default (also used by AIS-SART under test)
     */
    protected int navigationalStatus;
    /**
     * True heading Degrees (0-359) (511 indicates not available = default)
     */
    protected float heading;
    /**
     * Latitude in 1/10 000 min (+/-90 deg, North = positive (as per 2's
     * complement), South = negative (as 2's complement). 91deg (3412140h) = not
     * available = default)
     */
    protected float latitude;
    /**
     * Longitude in 1/10 000 min (+/-180 deg, East = positive (as per 2's
     * complement), West = negative (as per 2's complement). 181 (6791AC0h) =
     * not available = default)
     */
    protected float longitude;
    /**
     * imo number :1-999999999; 0 = not available = default
     */
    protected int imo;
    /**
     * Name of the ship : Maximum 20 characters 6-bit ASCII, as defined in Table
     * 44.
     *
     * @@@@@@@@@@@@@@@@@@@@ = not available = default
     */
    protected String shipname;
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
    protected int shipType;
    /**
     * Width of the ship
     */
    protected float width;
    /**
     * Length of the ship
     */
    protected float length;
    /**
     * Maximum present static draught In 1/10 m, 255 = draught 25.5 m or
 greater, 0 = not available = default; in accordance with imo Resolution
 A.851
     */
    protected float draught;
    /**
     * 0 =?Undefined (default); 1 = GPS, 2 = GLONASS, 3 = combined GPS/GLONASS,
     * 4 = Loran-C, 5 = Chayka, 6 = integrated navigation system, 7 = surveyed;
     * 8 = Galileo, 9-15 = not used
     */
    protected int electronicPositionDevice;
    /**
     * 7 = 6 bit ASCII characters,
     *
     * @@@@@@@ = not available = default
     */
    protected String callsign;
    /**
     * Estimated time of arrival; MMDDHHMM UTC Bits 19-16: month; 1-12; 0 = not
     * available = default Bits 15-11: day; 1-31; 0 = not available = default
     * Bits 10-6: hour; 0-23; 24 = not available = default
     */
    protected Calendar ETA;
    /**
     * Maximum 20 characters using 6-bit ASCII;
     *
     * @@@@@@@@@@@@@@@@@@@@ = not available
     */
    protected String destination;
    /**
     * year, month, day of ETA
     */
    protected int year, month, day;
    /**
     * hour, minute of ETA
     */
    protected int hour, minute;

    protected int second;

    /**
     * Get the value of second
     *
     * @return the value of second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set the value of second
     *
     * @param second new value of second
     */
    public void setSecond(int second) {
        this.second = second;
    }

    public AIS135() {
    }

    public AIS135(float rot, float cog, float sog, int navigationalStatus, float heading, float latitude, float longitude, int second, int MMSI, String device) {
        super(MMSI, device);
        this.rot = rot;
        this.cog = cog;
        this.sog = sog;
        this.navigationalStatus = navigationalStatus;
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.second = second;
    }

    public AIS135(int imo, String shipname, int shipType, float draught, String callsign, Calendar ETA, String destination, int MMSI, String device) {
        super(MMSI, device);
        this.imo = imo;
        this.shipname = shipname;
        this.shipType = shipType;
        this.draught = draught;
        this.callsign = callsign;
        this.ETA = ETA;
        this.destination = destination;
    }

    public AIS135(float rot, float cog, float sog, int navigationalStatus, float heading, float latitude, float longitude, int IMO, String name, int shipType, float width, float length, float draught, int electronicPositionDevice, String CallSign, Calendar ETA, String destination, int year, int month, int day, int hour, int minute) {
        this.rot = rot;
        this.cog = cog;
        this.sog = sog;
        this.navigationalStatus = navigationalStatus;
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imo = IMO;
        this.shipname = name;
        this.shipType = shipType;
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.electronicPositionDevice = electronicPositionDevice;
        this.callsign = CallSign;
        this.ETA = ETA;
        this.destination = destination;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public AIS135(int MMSI, String device,int imo, String shipname, int shipType, float width, float length, float draught, String callsign, Calendar ETA, String destination) {
        super(MMSI, device);
        this.imo = imo;
        this.shipname = shipname;
        this.shipType = shipType;
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.callsign = callsign;
        this.ETA = ETA;
        this.destination = destination;
    }

    

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM");
        StringBuffer sb = new StringBuffer("AISType135{MMSI=" + MMSI
                + ", IMO = " + imo
                + ", NAME = " + shipname
                + ", STATUS = " + navigationalStatus
                + ", TYPE = " + shipType
                + ", LENGTH = " + length
                + ", WIDTH = " + width
                + ", DRAUGHT = " + draught
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", HEADING = " + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", ROT = " + rot
                + ", "
                + ""
                + ", ETA = ");
        if (ETA != null) {
            Date df = ETA.getTime();
            sb.append(dateFormat.format(df));
        } else {
            sb.append("");
        }
        sb.append(", DEST = ").append(destination + "}");
        return new String(sb);
    }

    /**
     *
     * @return
     */
    public String toHTML() {

        String text = "";
        if (getShipname() != null) {
            text += getShipname() + "<br/>";
        } else {
            text += "" + "<br/>";
        }
        text += "MMSI : " + getMMSI() + "<br/>";
        text += "Type : " + getShipType() + "<br/>";
        text += "Sog : " + getSog() + "<br/>";
        if (getHeading() != 511) {
            text += "Heading : " + getHeading() + "<br/>";
        }
        return text;
    }

    /**
     *
     */
    public void display() {
        System.out.println(this.toString());
    }

    public int getElectronicPositionDevice() {
        return electronicPositionDevice;
    }

    public void setElectronicPositionDevice(int electronicPositionDevice) {
        this.electronicPositionDevice = electronicPositionDevice;
    }

    /**
     *
     * @return
     */
    public String getCallsign() {
        return callsign;
    }

    /**
     *
     * @param callsign
     */
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    /**
     *
     * @return
     */
    public Calendar getETA() {
        return ETA;
    }

    /**
     *
     * @param ETA
     */
    public void setETA(Calendar ETA) {
        this.ETA = ETA;
    }

    /**
     *
     * @return
     */
    public int getImo() {
        return imo;
    }

    /**
     *
     * @param imo
     */
    public void setImo(int Imo) {
        this.imo = Imo;
    }

    /**
     *
     * @return
     */
    public float getCog() {
        return cog;
    }

    /**
     *
     * @param cog
     */
    public void setCog(float cog) {
        this.cog = cog;
    }

    /**
     *
     * @return
     */
    public int getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     *
     * @return
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     * @return draught
     */
    public float getDraught() {
        return draught;
    }

    /**
     *
     * @param draught
     */
    public void setDraught(float draught) {
        this.draught = draught;
    }

    /**
     *
     * @return
     */
    public float getHeading() {
        return heading;
    }

    /**
     *
     * @param heading
     */
    public void setHeading(float heading) {
        this.heading = heading;
    }

    /**
     *
     * @return
     */
    public int getHour() {
        return hour;
    }

    /**
     *
     * @param hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     *
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public float getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     *
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public int getMinute() {
        return minute;
    }

    /**
     *
     * @param minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     *
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public String getShipname() {
        return shipname;
    }

    /**
     *
     * @param name
     */
    public void setShipname(String name) {
        this.shipname = name;
    }

    /**
     *
     * @return
     */
    public int getNavigationTool() {
        return electronicPositionDevice;
    }

    /**
     *
     * @param navigationTool
     */
    public void setNavigationTool(int navigationTool) {
        this.electronicPositionDevice = navigationTool;
    }

    /**
     *
     * @return
     */
    public float getRot() {
        return rot;
    }

    /**
     *
     * @param rot
     */
    public void setRot(float rot) {
        this.rot = rot;
    }

    /**
     *
     * @return
     */
    public int getShipType() {
        return shipType;
    }

    /**
     *
     * @param shipType
     */
    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    /**
     *
     * @return
     */
    public float getSog() {
        return sog;
    }

    /**
     *
     * @param sog
     */
    public void setSog(float sog) {
        this.sog = sog;
    }

    /**
     *
     * @return
     */
    public int getNavigationalStatus() {
        return navigationalStatus;
    }

    /**
     *
     * @param navigationalStatus
     */
    public void setNavigationalStatus(int navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
    }

    /**
     *
     * @return
     */
    public float getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
