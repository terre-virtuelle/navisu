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
 * <p>Classe Java pour meteringMode_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="meteringMode_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="Average"/>
 *     &lt;enumeration value="Center Weighted Average"/>
 *     &lt;enumeration value="Spot"/>
 *     &lt;enumeration value="MultiSpot"/>
 *     &lt;enumeration value="Pattern"/>
 *     &lt;enumeration value="Partial"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "meteringMode_t")
@XmlEnum
public enum MeteringModeT {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("Average")
    AVERAGE("Average"),
    @XmlEnumValue("Center Weighted Average")
    CENTER_WEIGHTED_AVERAGE("Center Weighted Average"),
    @XmlEnumValue("Spot")
    SPOT("Spot"),
    @XmlEnumValue("MultiSpot")
    MULTI_SPOT("MultiSpot"),
    @XmlEnumValue("Pattern")
    PATTERN("Pattern"),
    @XmlEnumValue("Partial")
    PARTIAL("Partial"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    MeteringModeT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeteringModeT fromValue(String v) {
        for (MeteringModeT c: MeteringModeT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
