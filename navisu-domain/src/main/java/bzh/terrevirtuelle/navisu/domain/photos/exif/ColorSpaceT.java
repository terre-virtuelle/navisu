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
 * <p>Classe Java pour colorSpace_t.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="colorSpace_t">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="sRGB"/>
 *     &lt;enumeration value="Uncalibrated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "colorSpace_t")
@XmlEnum
public enum ColorSpaceT {

    @XmlEnumValue("sRGB")
    S_RGB("sRGB"),
    @XmlEnumValue("Uncalibrated")
    UNCALIBRATED("Uncalibrated");
    private final String value;

    ColorSpaceT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ColorSpaceT fromValue(String v) {
        for (ColorSpaceT c: ColorSpaceT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
