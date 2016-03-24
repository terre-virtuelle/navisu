/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 20, 2016
 *
 */
@XmlRootElement(name = "maj")
@XmlAccessorType(XmlAccessType.FIELD)
public class Update {

    @XmlElement(name = "gan")
    private String gan;
    @XmlElement(name = "bat")
    private String bat;
    @XmlElement(name = "dms")
    private String dms;
    @XmlElement(name = "anciennePub")
    private String oldPub;
    @XmlElement(name = "texte")
    private String text;

    public Update() {
    }

    public Update(String gan, String bat, String dms, String oldPub, String text) {
        this.gan = gan;
        this.bat = bat;
        this.dms = dms;
        this.oldPub = oldPub;
        this.text = text;
    }

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the value of oldPub
     *
     * @return the value of oldPub
     */
    public String getOldPub() {
        return oldPub;
    }

    /**
     * Set the value of oldPub
     *
     * @param oldPub new value of oldPub
     */
    public void setOldPub(String oldPub) {
        this.oldPub = oldPub;
    }

    /**
     * Get the value of dms
     *
     * @return the value of dms
     */
    public String getDms() {
        return dms;
    }

    /**
     * Set the value of dms
     *
     * @param dms new value of dms
     */
    public void setDms(String dms) {
        this.dms = dms;
    }

    /**
     * Get the value of bat
     *
     * @return the value of bat
     */
    public String getBat() {
        return bat;
    }

    /**
     * Set the value of bat
     *
     * @param bat new value of bat
     */
    public void setBat(String bat) {
        this.bat = bat;
    }

    /**
     * Get the value of gan
     *
     * @return the value of gan
     */
    public String getGan() {
        return gan;
    }

    /**
     * Set the value of gan
     *
     * @param gan new value of gan
     */
    public void setGan(String gan) {
        this.gan = gan;
    }

    @Override
    public String toString() {
        return "Update{" + "gan=" + gan + ", bat=" + bat + ", dms=" + dms + ", oldPub=" + oldPub + ", text=" + text + '}';
    }

}
