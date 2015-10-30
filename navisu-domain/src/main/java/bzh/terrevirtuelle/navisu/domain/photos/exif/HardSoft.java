/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 29 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "hardSoft")
@XmlAccessorType(XmlAccessType.FIELD)
public class HardSoft {

    private String model;

    private String software;

    private String exifVersion;

    public HardSoft() {
    }

    public HardSoft(String model, String software, String exifVersion) {
        this.model = model;
        this.software = software;
        this.exifVersion = exifVersion;
    }

    /**
     * Get the value of exifVersion
     *
     * @return the value of exifVersion
     */
    public String getExifVersion() {
        return exifVersion;
    }

    /**
     * Set the value of exifVersion
     *
     * @param exifVersion new value of exifVersion
     */
    public void setExifVersion(String exifVersion) {
        this.exifVersion = exifVersion;
    }

    /**
     * Get the value of software
     *
     * @return the value of software
     */
    public String getSoftware() {
        return software;
    }

    /**
     * Set the value of software
     *
     * @param software new value of software
     */
    public void setSoftware(String software) {
        this.software = software;
    }

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the value of model
     *
     * @param model new value of model
     */
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "HardSoft{" + "model=" + model + ", software=" + software + ", exifVersion=" + exifVersion + '}';
    }

}
