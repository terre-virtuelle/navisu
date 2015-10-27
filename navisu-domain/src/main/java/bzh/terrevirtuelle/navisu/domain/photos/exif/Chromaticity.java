//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.10.27 à 06:10:03 PM CET 
//


package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour chromaticity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="chromaticity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeReal"/>
 *         &lt;element name="Y" type="{http://xmlns.oracle.com/ord/meta/exif}nonNegativeReal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chromaticity", propOrder = {
    "x",
    "y"
})
@XmlSeeAlso({
    WhitePointType.class
})
public class Chromaticity {

    @XmlElement(name = "X")
    protected float x;
    @XmlElement(name = "Y")
    protected float y;

    /**
     * Obtient la valeur de la propriété x.
     * 
     */
    public float getX() {
        return x;
    }

    /**
     * Définit la valeur de la propriété x.
     * 
     */
    public void setX(float value) {
        this.x = value;
    }

    /**
     * Obtient la valeur de la propriété y.
     * 
     */
    public float getY() {
        return y;
    }

    /**
     * Définit la valeur de la propriété y.
     * 
     */
    public void setY(float value) {
        this.y = value;
    }

}
