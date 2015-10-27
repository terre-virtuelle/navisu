//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.10.27 à 06:10:03 PM CET 
//


package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour lightSource_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="lightSource_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="Daylight"/>
 *     &lt;enumeration value="Fluorescent"/>
 *     &lt;enumeration value="Tungsten"/>
 *     &lt;enumeration value="Flash"/>
 *     &lt;enumeration value="Fine weather"/>
 *     &lt;enumeration value="Cloudy weather"/>
 *     &lt;enumeration value="Shade"/>
 *     &lt;enumeration value="Daylight fluorescent"/>
 *     &lt;enumeration value="Day white fluorescent"/>
 *     &lt;enumeration value="Cool white fluorescent"/>
 *     &lt;enumeration value="Standard light A"/>
 *     &lt;enumeration value="Standard light B"/>
 *     &lt;enumeration value="Standard light C"/>
 *     &lt;enumeration value="D55"/>
 *     &lt;enumeration value="D65"/>
 *     &lt;enumeration value="D75"/>
 *     &lt;enumeration value="D50"/>
 *     &lt;enumeration value="ISO studio tungsten"/>
 *     &lt;enumeration value="other light source"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "lightSource_t")
@XmlEnum
public enum LightSourceT {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("Daylight")
    DAYLIGHT("Daylight"),
    @XmlEnumValue("Fluorescent")
    FLUORESCENT("Fluorescent"),
    @XmlEnumValue("Tungsten")
    TUNGSTEN("Tungsten"),
    @XmlEnumValue("Flash")
    FLASH("Flash"),
    @XmlEnumValue("Fine weather")
    FINE_WEATHER("Fine weather"),
    @XmlEnumValue("Cloudy weather")
    CLOUDY_WEATHER("Cloudy weather"),
    @XmlEnumValue("Shade")
    SHADE("Shade"),
    @XmlEnumValue("Daylight fluorescent")
    DAYLIGHT_FLUORESCENT("Daylight fluorescent"),
    @XmlEnumValue("Day white fluorescent")
    DAY_WHITE_FLUORESCENT("Day white fluorescent"),
    @XmlEnumValue("Cool white fluorescent")
    COOL_WHITE_FLUORESCENT("Cool white fluorescent"),
    @XmlEnumValue("Standard light A")
    STANDARD_LIGHT_A("Standard light A"),
    @XmlEnumValue("Standard light B")
    STANDARD_LIGHT_B("Standard light B"),
    @XmlEnumValue("Standard light C")
    STANDARD_LIGHT_C("Standard light C"),
    @XmlEnumValue("D55")
    D_55("D55"),
    @XmlEnumValue("D65")
    D_65("D65"),
    @XmlEnumValue("D75")
    D_75("D75"),
    @XmlEnumValue("D50")
    D_50("D50"),
    @XmlEnumValue("ISO studio tungsten")
    ISO_STUDIO_TUNGSTEN("ISO studio tungsten"),
    @XmlEnumValue("other light source")
    OTHER_LIGHT_SOURCE("other light source");
    private final String value;

    LightSourceT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LightSourceT fromValue(String v) {
        for (LightSourceT c: LightSourceT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
