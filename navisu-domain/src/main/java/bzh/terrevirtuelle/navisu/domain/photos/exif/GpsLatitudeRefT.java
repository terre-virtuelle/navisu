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
 * <p>Classe Java pour gpsLatitudeRef_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="gpsLatitudeRef_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="North latitude"/>
 *     &lt;enumeration value="South latitude"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gpsLatitudeRef_t")
@XmlEnum
public enum GpsLatitudeRefT {

    @XmlEnumValue("North latitude")
    NORTH_LATITUDE("North latitude"),
    @XmlEnumValue("South latitude")
    SOUTH_LATITUDE("South latitude");
    private final String value;

    GpsLatitudeRefT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GpsLatitudeRefT fromValue(String v) {
        for (GpsLatitudeRefT c: GpsLatitudeRefT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
