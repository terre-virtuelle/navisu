/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.shom.ShomSailingDirectionsParser;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Mar 21, 2016
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShomSailingDirections
        extends SailingDirections {

    
    private String filename;

    public ShomSailingDirections() {
    }

    public ShomSailingDirections(String filename) {
        this.filename = filename;
        sailingDirectionsParser = new ShomSailingDirectionsParser(filename);
    }

    /**
     * Get the value of filename
     *
     * @return the value of filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the value of filename
     *
     * @param filename new value of filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    

}
