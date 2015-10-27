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
 * <p>Classe Java pour flashReturn_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="flashReturn_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="No strobe return function"/>
 *     &lt;enumeration value="Strobe return not detected"/>
 *     &lt;enumeration value="Strobe return detected"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "flashReturn_t")
@XmlEnum
public enum FlashReturnT {

    @XmlEnumValue("No strobe return function")
    NO_STROBE_RETURN_FUNCTION("No strobe return function"),
    @XmlEnumValue("Strobe return not detected")
    STROBE_RETURN_NOT_DETECTED("Strobe return not detected"),
    @XmlEnumValue("Strobe return detected")
    STROBE_RETURN_DETECTED("Strobe return detected");
    private final String value;

    FlashReturnT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlashReturnT fromValue(String v) {
        for (FlashReturnT c: FlashReturnT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
