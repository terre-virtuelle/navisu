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
 * <p>Classe Java pour flashType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="flashType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fired" type="{http://xmlns.oracle.com/ord/meta/exif}yesNo_t"/>
 *         &lt;element name="Return" type="{http://xmlns.oracle.com/ord/meta/exif}flashReturn_t" minOccurs="0"/>
 *         &lt;element name="Mode" type="{http://xmlns.oracle.com/ord/meta/exif}flashMode_t" minOccurs="0"/>
 *         &lt;element name="Function" type="{http://xmlns.oracle.com/ord/meta/exif}yesNo_t" minOccurs="0"/>
 *         &lt;element name="RedEyeReduction" type="{http://xmlns.oracle.com/ord/meta/exif}yesNo_t" minOccurs="0"/>
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
@XmlType(name = "flashType", propOrder = {
    "fired",
    "_return",
    "mode",
    "function",
    "redEyeReduction"
})
public class FlashType {

    @XmlElement(name = "Fired", required = true)
    @XmlSchemaType(name = "string")
    protected YesNoT fired;
    @XmlElement(name = "Return")
    @XmlSchemaType(name = "string")
    protected FlashReturnT _return;
    @XmlElement(name = "Mode")
    @XmlSchemaType(name = "string")
    protected FlashModeT mode;
    @XmlElement(name = "Function")
    @XmlSchemaType(name = "string")
    protected YesNoT function;
    @XmlElement(name = "RedEyeReduction")
    @XmlSchemaType(name = "string")
    protected YesNoT redEyeReduction;
    @XmlAttribute(name = "tag", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger tag;

    /**
     * Obtient la valeur de la propriété fired.
     * 
     * @return
     *     possible object is
     *     {@link YesNoT }
     *     
     */
    public YesNoT getFired() {
        return fired;
    }

    /**
     * Définit la valeur de la propriété fired.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoT }
     *     
     */
    public void setFired(YesNoT value) {
        this.fired = value;
    }

    /**
     * Obtient la valeur de la propriété return.
     * 
     * @return
     *     possible object is
     *     {@link FlashReturnT }
     *     
     */
    public FlashReturnT getReturn() {
        return _return;
    }

    /**
     * Définit la valeur de la propriété return.
     * 
     * @param value
     *     allowed object is
     *     {@link FlashReturnT }
     *     
     */
    public void setReturn(FlashReturnT value) {
        this._return = value;
    }

    /**
     * Obtient la valeur de la propriété mode.
     * 
     * @return
     *     possible object is
     *     {@link FlashModeT }
     *     
     */
    public FlashModeT getMode() {
        return mode;
    }

    /**
     * Définit la valeur de la propriété mode.
     * 
     * @param value
     *     allowed object is
     *     {@link FlashModeT }
     *     
     */
    public void setMode(FlashModeT value) {
        this.mode = value;
    }

    /**
     * Obtient la valeur de la propriété function.
     * 
     * @return
     *     possible object is
     *     {@link YesNoT }
     *     
     */
    public YesNoT getFunction() {
        return function;
    }

    /**
     * Définit la valeur de la propriété function.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoT }
     *     
     */
    public void setFunction(YesNoT value) {
        this.function = value;
    }

    /**
     * Obtient la valeur de la propriété redEyeReduction.
     * 
     * @return
     *     possible object is
     *     {@link YesNoT }
     *     
     */
    public YesNoT getRedEyeReduction() {
        return redEyeReduction;
    }

    /**
     * Définit la valeur de la propriété redEyeReduction.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoT }
     *     
     */
    public void setRedEyeReduction(YesNoT value) {
        this.redEyeReduction = value;
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
