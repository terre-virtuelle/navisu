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
 * <p>Classe Java pour exposureProgram_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="exposureProgram_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Not defined"/>
 *     &lt;enumeration value="Manual"/>
 *     &lt;enumeration value="Normal program"/>
 *     &lt;enumeration value="Aperture priority"/>
 *     &lt;enumeration value="Shutter priority"/>
 *     &lt;enumeration value="Creative program"/>
 *     &lt;enumeration value="Action program"/>
 *     &lt;enumeration value="Portrait mode"/>
 *     &lt;enumeration value="Landscape mode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "exposureProgram_t")
@XmlEnum
public enum ExposureProgramT {

    @XmlEnumValue("Not defined")
    NOT_DEFINED("Not defined"),
    @XmlEnumValue("Manual")
    MANUAL("Manual"),
    @XmlEnumValue("Normal program")
    NORMAL_PROGRAM("Normal program"),
    @XmlEnumValue("Aperture priority")
    APERTURE_PRIORITY("Aperture priority"),
    @XmlEnumValue("Shutter priority")
    SHUTTER_PRIORITY("Shutter priority"),
    @XmlEnumValue("Creative program")
    CREATIVE_PROGRAM("Creative program"),
    @XmlEnumValue("Action program")
    ACTION_PROGRAM("Action program"),
    @XmlEnumValue("Portrait mode")
    PORTRAIT_MODE("Portrait mode"),
    @XmlEnumValue("Landscape mode")
    LANDSCAPE_MODE("Landscape mode");
    private final String value;

    ExposureProgramT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExposureProgramT fromValue(String v) {
        for (ExposureProgramT c: ExposureProgramT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
