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
 * <p>Classe Java pour orientation_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="orientation_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="top left"/>
 *     &lt;enumeration value="top right"/>
 *     &lt;enumeration value="bottom right"/>
 *     &lt;enumeration value="bottom left"/>
 *     &lt;enumeration value="left top"/>
 *     &lt;enumeration value="right top"/>
 *     &lt;enumeration value="right bottom"/>
 *     &lt;enumeration value="left bottom"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "orientation_t")
@XmlEnum
public enum OrientationT {

    @XmlEnumValue("top left")
    TOP_LEFT("top left"),
    @XmlEnumValue("top right")
    TOP_RIGHT("top right"),
    @XmlEnumValue("bottom right")
    BOTTOM_RIGHT("bottom right"),
    @XmlEnumValue("bottom left")
    BOTTOM_LEFT("bottom left"),
    @XmlEnumValue("left top")
    LEFT_TOP("left top"),
    @XmlEnumValue("right top")
    RIGHT_TOP("right top"),
    @XmlEnumValue("right bottom")
    RIGHT_BOTTOM("right bottom"),
    @XmlEnumValue("left bottom")
    LEFT_BOTTOM("left bottom");
    private final String value;

    OrientationT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrientationT fromValue(String v) {
        for (OrientationT c: OrientationT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
