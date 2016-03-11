                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      //
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.03 at 09:49:42 AM CET 
//
package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * wpt represents a waypoint, point of interest, or named feature on a map.
 *
 *
 * <p>
 * Java class for wptType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="wptType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ele" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="magvar" type="{http://www.topografix.com/GPX/1/1}degreesType" minOccurs="0"/>
 *         &lt;element name="geoidheight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="src" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.topografix.com/GPX/1/1}linkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sym" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fix" type="{http://www.topografix.com/GPX/1/1}fixType" minOccurs="0"/>
 *         &lt;element name="sat" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="hdop" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="vdop" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pdop" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ageofdgpsdata" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dgpsid" type="{http://www.topografix.com/GPX/1/1}dgpsStationType" minOccurs="0"/>
 *         &lt;element name="extensions" type="{http://www.topografix.com/GPX/1/1}extensionsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="lat" use="required" type="{http://www.topografix.com/GPX/1/1}latitudeType" />
 *       &lt;attribute name="lon" use="required" type="{http://www.topografix.com/GPX/1/1}longitudeType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wpt", propOrder = {
    "ele",
    "time",
    "magvar",
    "geoidheight",
    "name",
    "cmt",
    "desc",
    "src",
    "links",
    "sym",
    "type",
    "fix",
    "sat",
    "hdop",
    "vdop",
    "pdop",
    "ageofdgpsdata",
    "dgpsid",
    "course",
    "speed",
    "extensions"
})
public class Waypoint {

    protected double ele;
    @XmlSchemaType(name = "dateTime")
    protected GregorianCalendar time;
    protected double magvar;
    protected double geoidheight;
    protected String name;
    protected String cmt;
    protected String desc;
    protected String src;
    protected List<Links> links = new ArrayList<>();
    protected String sym;
    protected String type;
    protected String fix;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int sat;
    protected double hdop;
    protected double vdop;
    protected double pdop;
    protected double ageofdgpsdata;
    protected int dgpsid;
    protected Extensions extensions;
    @XmlAttribute(name = "lat", required = true)
    protected double latitude;
    @XmlAttribute(name = "lon", required = true)
    protected double longitude;
    protected float course;
    protected float speed;
    @XmlTransient
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

    /**
     * Get the value of speed
     *
     * @return the value of speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the value of speed
     *
     * @param speed new value of speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get the value of course
     *
     * @return the value of course
     */
    public float getCourse() {
        return course;
    }

    /**
     * Set the value of course
     *
     * @param course new value of course
     */
    public void setCourse(float course) {
        this.course = course;
    }

    public Waypoint() {

    }

    public Waypoint(double ele, GregorianCalendar time, double magvar, double geoidheight,
            String name, String cmt, String desc, String src, List<Links> link,
            String sym, String type, String fix, int sat, double hdop,
            double vdop, double pdop, double ageofdgpsdata, float course,float speed,
            int dgpsid, Extensions extensions, double lat, double lon) {
        this.ele = ele;
        this.time = time;
        this.magvar = magvar;
        this.geoidheight = geoidheight;
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.links = link;
        this.sym = sym;
        this.type = type;
        this.fix = fix;
        this.sat = sat;
        this.hdop = hdop;
        this.vdop = vdop;
        this.pdop = pdop;
        this.ageofdgpsdata = ageofdgpsdata;
        this.course = course;
        this.speed = speed;
        this.dgpsid = dgpsid;
        this.extensions = extensions;
        this.latitude = lat;
        this.longitude = lon;
    }

    /**
     * Gets the value of the ele property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getEle() {
        return ele;
    }

    /**
     * Sets the value of the ele property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setEle(double value) {
        this.ele = value;
    }

    /**
     * Gets the value of the time property.
     *
     * @return possible object is {@link GregorianCalendar }
     *
     */
    public GregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     * @param value allowed object is {@link GregorianCalendar }
     *
     */
    public void setTime(GregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the magvar property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getMagvar() {
        return magvar;
    }

    /**
     * Sets the value of the magvar property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setMagvar(double value) {
        this.magvar = value;
    }

    /**
     * Gets the value of the geoidheight property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getGeoidheight() {
        return geoidheight;
    }

    /**
     * Sets the value of the geoidheight property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setGeoidheight(double value) {
        this.geoidheight = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the cmt property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCmt() {
        return cmt;
    }

    /**
     * Sets the value of the cmt property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCmt(String value) {
        this.cmt = value;
    }

    /**
     * Gets the value of the desc property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the src property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the value of the src property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Gets the value of the link property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the link property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Links }
     *
     *
     */
    public List<Links> getLinkList() {
        if (links == null) {
            links = new ArrayList<Links>();
        }
        return this.links;
    }

    /**
     * Gets the value of the sym property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSym() {
        return sym;
    }

    /**
     * Sets the value of the sym property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSym(String value) {
        this.sym = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the fix property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFix() {
        return fix;
    }

    /**
     * Sets the value of the fix property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFix(String value) {
        this.fix = value;
    }

    /**
     * Gets the value of the sat property.
     *
     * @return possible object is {@link int }
     *
     */
    public int getSat() {
        return sat;
    }

    /**
     * Sets the value of the sat property.
     *
     * @param value allowed object is {@link int }
     *
     */
    public void setSat(int value) {
        this.sat = value;
    }

    /**
     * Gets the value of the hdop property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getHdop() {
        return hdop;
    }

    /**
     * Sets the value of the hdop property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setHdop(double value) {
        this.hdop = value;
    }

    /**
     * Gets the value of the vdop property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getVdop() {
        return vdop;
    }

    /**
     * Sets the value of the vdop property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setVdop(double value) {
        this.vdop = value;
    }

    /**
     * Gets the value of the pdop property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getPdop() {
        return pdop;
    }

    /**
     * Sets the value of the pdop property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setPdop(double value) {
        this.pdop = value;
    }

    /**
     * Gets the value of the ageofdgpsdata property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getAgeofdgpsdata() {
        return ageofdgpsdata;
    }

    /**
     * Sets the value of the ageofdgpsdata property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setAgeofdgpsdata(double value) {
        this.ageofdgpsdata = value;
    }

    /**
     * Gets the value of the dgpsid property.
     *
     * @return possible object is {@link int }
     *
     */
    public int getDgpsid() {
        return dgpsid;
    }

    /**
     * Sets the value of the dgpsid property.
     *
     * @param value allowed object is {@link int }
     *
     */
    public void setDgpsid(int value) {
        this.dgpsid = value;
    }

    /**
     * Gets the value of the extensions property.
     *
     * @return possible object is {@link Extensions }
     *
     */
    public Extensions getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     *
     * @param value allowed object is {@link Extensions }
     *
     */
    public void setExtensions(Extensions value) {
        this.extensions = value;
    }

    /**
     * Gets the value of the lat property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the lat property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the lon property.
     *
     * @return possible object is {@link double }
     *
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the lon property.
     *
     * @param value allowed object is {@link double }
     *
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    @Override
    public String toString() {
        return "Waypoint{"
                + "time=" + sdf.format(time.getTime())
                + (ele != 0 ? ", ele=" + ele : "")
                + (magvar != 0 ? ", magvar=" + magvar : "")
                + (geoidheight != 0 ? ", geoidheight=" + geoidheight : "")
                + (name != null ? ", name=" + name : "")
                + (cmt != null ? ", cmt=" + cmt : "")
                + (desc != null ? ", desc=" + desc : "")
                + (src != null ? ", src=" + src : "")
                + (!links.isEmpty() ? ", link=" + links : "")
                + (sym != null ? ", sym=" + sym : "")
                + (type != null ? ", type=" + type : "")
                + (fix != null ? ", fix=" + fix : "")
                + (sat != 0 ? ", sat=" + sat : "")
                + (hdop != 0 ? ", hdop=" + hdop : "")
                + (vdop != 0 ? ", vdop=" + vdop : "")
                + (pdop != 0 ? ", pdop=" + pdop : "")
                + (ageofdgpsdata != 0.0 ? ", ageofdgpsdata=" + ageofdgpsdata : "")
                + (dgpsid != 0 ? ", dgpsid=" + dgpsid : "")
                + (extensions != null ? ", extensions=" + extensions : "")
                + ", latitude=" + latitude
                + ", longitude=" + longitude
                + '}';
    }

}
