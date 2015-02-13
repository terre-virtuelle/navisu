/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Satellites in view
 *
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE
 * aubryc
 * @author Serge Morvan
 */
@XmlRootElement(name="GSV")
@XmlAccessorType(XmlAccessType.FIELD)
public class GSV extends NMEA {

    private int numberOfSentences;
    private int sentenceNumber;
    private int numberOfSatellitesInView;
    @XmlElementWrapper(name = "satellites")
    @XmlElement(name = "satellite")
    private List<GPSSatellite> satellites;

    /**
     * GSV - Satellites in View shows data about the satellites that the unit
     * might be able to find based on its viewing mask and almanac data. It also
     * shows current ability to track this data. Note that one GSV sentence only
     * can provide data for up to 4 satellites and thus there may need to be 3
     * sentences for the full information. It is reasonable for the GSV sentence
     * to contain more satellites than GGA might indicate since GSV may include
     * satellites that are not used as part of the solution. It is not a
     * requirment that the GSV sentences all appear in sequence. To avoid
     * overloading the data bandwidth some receivers may place the various
     * sentences in totally different samples since each sentence identifies
     * which one it is.
     *
     * @param device
     * @param sentence
     * @param numberOfSentences
     * @param sentenceNumber
     * @param numberOfSatellitesInView
     */
    public GSV(
            String device,
            String sentence,
            int numberOfSentences,
            int sentenceNumber,
            int numberOfSatellitesInView) {
        super(device, sentence);
        this.numberOfSentences = numberOfSentences;
        this.sentenceNumber = sentenceNumber;
        this.numberOfSatellitesInView = numberOfSatellitesInView;
        satellites = new ArrayList<>();
    }

    public GSV() {
    }

    public List<GPSSatellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<GPSSatellite> satellites) {
        this.satellites = satellites;
    }

    /**
     *
     * @param s
     */
    public void addSatellite(GPSSatellite s) {
        satellites.add(s);
    }

    /**
     *
     * @param pos
     * @return
     */
    public GPSSatellite getSatellite(int pos) {
        return satellites.get(pos);
    }

    /**
     *
     * @return
     */
    public int getNumberOfSatellitesInView() {
        return numberOfSatellitesInView;
    }

    /**
     *
     * @param numberOfSatellitesInView
     */
    public void setNumberOfSatellitesInView(int numberOfSatellitesInView) {
        this.numberOfSatellitesInView = numberOfSatellitesInView;
    }

    /**
     *
     * @return
     */
    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    /**
     *
     * @param numberOfSentences
     */
    public void setNumberOfSentences(int numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
    }

    /**
     *
     * @return
     */
    public int getSentenceNumber() {
        return sentenceNumber;
    }

    /**
     *
     * @param sentenceNumber
     */
    public void setSentenceNumber(int sentenceNumber) {
        this.sentenceNumber = sentenceNumber;
    }

    @Override
    public String toString() {
        return "GSV{" + "numberOfSentences=" + numberOfSentences + ", sentenceNumber=" + sentenceNumber + ", numberOfSatellitesInView=" + numberOfSatellitesInView + ", satellites=" + satellites + '}';
    }

}
