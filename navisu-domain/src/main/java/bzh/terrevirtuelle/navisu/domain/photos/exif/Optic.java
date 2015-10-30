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
@XmlType(name = "optic")
@XmlAccessorType(XmlAccessType.FIELD)
public class Optic {

    private String fNumber;

    private String apertureValue;

    private int focalLenth;

    private String lensModel;

    public Optic() {
    }

    public Optic(String fNumber, String apertureValue, int focalLenth, String lensModel) {
        this.fNumber = fNumber;
        this.apertureValue = apertureValue;
        this.focalLenth = focalLenth;
        this.lensModel = lensModel;
    }

    /**
     * Get the value of lensModel
     *
     * @return the value of lensModel
     */
    public String getLensModel() {
        return lensModel;
    }

    /**
     * Set the value of lensModel
     *
     * @param lensModel new value of lensModel
     */
    public void setLensModel(String lensModel) {
        this.lensModel = lensModel;
    }

    /**
     * Get the value of focalLenth
     *
     * @return the value of focalLenth
     */
    public int getFocalLenth() {
        return focalLenth;
    }

    /**
     * Set the value of focalLenth
     *
     * @param focalLenth new value of focalLenth
     */
    public void setFocalLenth(int focalLenth) {
        this.focalLenth = focalLenth;
    }

    /**
     * Get the value of apertureValue
     *
     * @return the value of apertureValue
     */
    public String getApertureValue() {
        return apertureValue;
    }

    /**
     * Set the value of apertureValue
     *
     * @param apertureValue new value of apertureValue
     */
    public void setApertureValue(String apertureValue) {
        this.apertureValue = apertureValue;
    }

    /**
     * Get the value of fNumber
     *
     * @return the value of fNumber
     */
    public String getfNumber() {
        return fNumber;
    }

    /**
     * Set the value of fNumber
     *
     * @param fNumber new value of fNumber
     */
    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    @Override
    public String toString() {
        return "Optic{" + "fNumber=" + fNumber + ", apertureValue=" + apertureValue + ", focalLenth=" + focalLenth + ", lensModel=" + lensModel + '}';
    }

}
