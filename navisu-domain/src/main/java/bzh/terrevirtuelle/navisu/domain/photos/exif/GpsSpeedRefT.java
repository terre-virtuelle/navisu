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
 * <p>Classe Java pour gpsSpeedRef_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="gpsSpeedRef_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Kilometers per hour"/>
 *     &lt;enumeration value="Miles per hour"/>
 *     &lt;enumeration value="Knots"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gpsSpeedRef_t")
@XmlEnum
public enum GpsSpeedRefT {

    @XmlEnumValue("Kilometers per hour")
    KILOMETERS_PER_HOUR("Kilometers per hour"),
    @XmlEnumValue("Miles per hour")
    MILES_PER_HOUR("Miles per hour"),
    @XmlEnumValue("Knots")
    KNOTS("Knots");
    private final String value;

    GpsSpeedRefT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GpsSpeedRefT fromValue(String v) {
        for (GpsSpeedRefT c: GpsSpeedRefT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
