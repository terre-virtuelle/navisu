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
 * <p>Classe Java pour exposureMode_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="exposureMode_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Auto exposure"/>
 *     &lt;enumeration value="Manual exposure"/>
 *     &lt;enumeration value="Auto bracket"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "exposureMode_t")
@XmlEnum
public enum ExposureModeT {

    @XmlEnumValue("Auto exposure")
    AUTO_EXPOSURE("Auto exposure"),
    @XmlEnumValue("Manual exposure")
    MANUAL_EXPOSURE("Manual exposure"),
    @XmlEnumValue("Auto bracket")
    AUTO_BRACKET("Auto bracket");
    private final String value;

    ExposureModeT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExposureModeT fromValue(String v) {
        for (ExposureModeT c: ExposureModeT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
