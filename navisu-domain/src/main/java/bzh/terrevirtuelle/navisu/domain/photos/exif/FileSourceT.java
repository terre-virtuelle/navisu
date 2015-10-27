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
 * <p>Classe Java pour fileSource_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="fileSource_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="others"/>
 *     &lt;enumeration value="scanner of transparent type"/>
 *     &lt;enumeration value="scanner of reflex type"/>
 *     &lt;enumeration value="DSC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fileSource_t")
@XmlEnum
public enum FileSourceT {

    @XmlEnumValue("others")
    OTHERS("others"),
    @XmlEnumValue("scanner of transparent type")
    SCANNER_OF_TRANSPARENT_TYPE("scanner of transparent type"),
    @XmlEnumValue("scanner of reflex type")
    SCANNER_OF_REFLEX_TYPE("scanner of reflex type"),
    DSC("DSC");
    private final String value;

    FileSourceT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FileSourceT fromValue(String v) {
        for (FileSourceT c: FileSourceT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
