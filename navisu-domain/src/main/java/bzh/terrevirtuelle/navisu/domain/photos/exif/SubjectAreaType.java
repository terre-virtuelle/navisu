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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour subjectAreaType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="subjectAreaType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlns.oracle.com/ord/meta/exif}subjectLocationType">
 *       &lt;choice>
 *         &lt;element name="Diameter" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;sequence>
 *           &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *           &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subjectAreaType", propOrder = {
    "diameter",
    "width",
    "height"
})
public class SubjectAreaType
    extends SubjectLocationType
{

    @XmlElement(name = "Diameter")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger diameter;
    @XmlElement(name = "Width")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger width;
    @XmlElement(name = "Height")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger height;

    /**
     * Obtient la valeur de la propriété diameter.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDiameter() {
        return diameter;
    }

    /**
     * Définit la valeur de la propriété diameter.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDiameter(BigInteger value) {
        this.diameter = value;
    }

    /**
     * Obtient la valeur de la propriété width.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidth() {
        return width;
    }

    /**
     * Définit la valeur de la propriété width.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidth(BigInteger value) {
        this.width = value;
    }

    /**
     * Obtient la valeur de la propriété height.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeight() {
        return height;
    }

    /**
     * Définit la valeur de la propriété height.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeight(BigInteger value) {
        this.height = value;
    }

}
