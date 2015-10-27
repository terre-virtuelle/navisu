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
 * <p>Classe Java pour gainControl_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="gainControl_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Low gain up"/>
 *     &lt;enumeration value="High gain up"/>
 *     &lt;enumeration value="Low gain down"/>
 *     &lt;enumeration value="High gain down"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gainControl_t")
@XmlEnum
public enum GainControlT {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Low gain up")
    LOW_GAIN_UP("Low gain up"),
    @XmlEnumValue("High gain up")
    HIGH_GAIN_UP("High gain up"),
    @XmlEnumValue("Low gain down")
    LOW_GAIN_DOWN("Low gain down"),
    @XmlEnumValue("High gain down")
    HIGH_GAIN_DOWN("High gain down");
    private final String value;

    GainControlT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GainControlT fromValue(String v) {
        for (GainControlT c: GainControlT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
