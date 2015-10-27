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
 * <p>Classe Java pour sensingMethod_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="sensingMethod_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not defined"/>
 *     &lt;enumeration value="One-chip color area"/>
 *     &lt;enumeration value="Two-chip color area"/>
 *     &lt;enumeration value="Three-chip color area"/>
 *     &lt;enumeration value="Color-sequential area"/>
 *     &lt;enumeration value="Trilinear"/>
 *     &lt;enumeration value="Color sequential linear"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sensingMethod_t")
@XmlEnum
public enum SensingMethodT {

    @XmlEnumValue("Not defined")
    NOT_DEFINED("Not defined"),
    @XmlEnumValue("One-chip color area")
    ONE_CHIP_COLOR_AREA("One-chip color area"),
    @XmlEnumValue("Two-chip color area")
    TWO_CHIP_COLOR_AREA("Two-chip color area"),
    @XmlEnumValue("Three-chip color area")
    THREE_CHIP_COLOR_AREA("Three-chip color area"),
    @XmlEnumValue("Color-sequential area")
    COLOR_SEQUENTIAL_AREA("Color-sequential area"),
    @XmlEnumValue("Trilinear")
    TRILINEAR("Trilinear"),
    @XmlEnumValue("Color sequential linear")
    COLOR_SEQUENTIAL_LINEAR("Color sequential linear");
    private final String value;

    SensingMethodT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SensingMethodT fromValue(String v) {
        for (SensingMethodT c: SensingMethodT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
