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
 * <p>Classe Java pour flashMode_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="flashMode_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="Compulsory firing"/>
 *     &lt;enumeration value="Compulsory suppression"/>
 *     &lt;enumeration value="Auto"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "flashMode_t")
@XmlEnum
public enum FlashModeT {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("Compulsory firing")
    COMPULSORY_FIRING("Compulsory firing"),
    @XmlEnumValue("Compulsory suppression")
    COMPULSORY_SUPPRESSION("Compulsory suppression"),
    @XmlEnumValue("Auto")
    AUTO("Auto");
    private final String value;

    FlashModeT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlashModeT fromValue(String v) {
        for (FlashModeT c: FlashModeT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
