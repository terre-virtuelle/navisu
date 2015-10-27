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
 * <p>Classe Java pour yCbCrCoefficientsType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="yCbCrCoefficientsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Coefficient_1" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeReal"/>
 *         &lt;element name="Coefficient_2" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeReal"/>
 *         &lt;element name="Coefficient_3" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeReal"/>
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
@XmlType(name = "yCbCrCoefficientsType", propOrder = {
    "coefficient1",
    "coefficient2",
    "coefficient3"
})
public class YCbCrCoefficientsType {

    @XmlElement(name = "Coefficient_1")
    protected float coefficient1;
    @XmlElement(name = "Coefficient_2")
    protected float coefficient2;
    @XmlElement(name = "Coefficient_3")
    protected float coefficient3;
    @XmlAttribute(name = "tag", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger tag;

    /**
     * Obtient la valeur de la propriété coefficient1.
     * 
     */
    public float getCoefficient1() {
        return coefficient1;
    }

    /**
     * Définit la valeur de la propriété coefficient1.
     * 
     */
    public void setCoefficient1(float value) {
        this.coefficient1 = value;
    }

    /**
     * Obtient la valeur de la propriété coefficient2.
     * 
     */
    public float getCoefficient2() {
        return coefficient2;
    }

    /**
     * Définit la valeur de la propriété coefficient2.
     * 
     */
    public void setCoefficient2(float value) {
        this.coefficient2 = value;
    }

    /**
     * Obtient la valeur de la propriété coefficient3.
     * 
     */
    public float getCoefficient3() {
        return coefficient3;
    }

    /**
     * Définit la valeur de la propriété coefficient3.
     * 
     */
    public void setCoefficient3(float value) {
        this.coefficient3 = value;
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
