//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.10.27 à 06:10:03 PM CET 
//


package bzh.terrevirtuelle.navisu.domain.photos.exif;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour primaryChromaticitiesType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="primaryChromaticitiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Color_1" type="{http://xmlns.oracle.com/ord/meta/exif}chromaticity"/>
 *         &lt;element name="Color_2" type="{http://xmlns.oracle.com/ord/meta/exif}chromaticity"/>
 *         &lt;element name="Color_3" type="{http://xmlns.oracle.com/ord/meta/exif}chromaticity"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://xmlns.oracle.com/ord/meta/exif}exifAttrs"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "primaryChromaticitiesType", propOrder = {
    "color1",
    "color2",
    "color3"
})
public class PrimaryChromaticitiesType {

    @XmlElement(name = "Color_1", required = true)
    protected Chromaticity color1;
    @XmlElement(name = "Color_2", required = true)
    protected Chromaticity color2;
    @XmlElement(name = "Color_3", required = true)
    protected Chromaticity color3;
    @XmlAttribute(name = "tag", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger tag;

    /**
     * Obtient la valeur de la propriété color1.
     * 
     * @return
     *     possible object is
     *     {@link Chromaticity }
     *     
     */
    public Chromaticity getColor1() {
        return color1;
    }

    /**
     * Définit la valeur de la propriété color1.
     * 
     * @param value
     *     allowed object is
     *     {@link Chromaticity }
     *     
     */
    public void setColor1(Chromaticity value) {
        this.color1 = value;
    }

    /**
     * Obtient la valeur de la propriété color2.
     * 
     * @return
     *     possible object is
     *     {@link Chromaticity }
     *     
     */
    public Chromaticity getColor2() {
        return color2;
    }

    /**
     * Définit la valeur de la propriété color2.
     * 
     * @param value
     *     allowed object is
     *     {@link Chromaticity }
     *     
     */
    public void setColor2(Chromaticity value) {
        this.color2 = value;
    }

    /**
     * Obtient la valeur de la propriété color3.
     * 
     * @return
     *     possible object is
     *     {@link Chromaticity }
     *     
     */
    public Chromaticity getColor3() {
        return color3;
    }

    /**
     * Définit la valeur de la propriété color3.
     * 
     * @param value
     *     allowed object is
     *     {@link Chromaticity }
     *     
     */
    public void setColor3(Chromaticity value) {
        this.color3 = value;
    }

    /**
     * Obtient la valeur de la propriété tag.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTag() {
        return tag;
    }

    /**
     * Définit la valeur de la propriété tag.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTag(BigInteger value) {
        this.tag = value;
    }

}
