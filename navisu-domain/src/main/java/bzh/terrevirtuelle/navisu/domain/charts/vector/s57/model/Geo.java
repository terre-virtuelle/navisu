package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Geo
        extends Feature
        implements Serializable {

    @XmlTransient
    protected List<Carto> cartos;
    @XmlTransient
    protected List<Geo> geos;
    @XmlTransient
    protected String information;
    @XmlTransient
    protected String informationInNationalLanguage;
    @XmlTransient
    protected String textualDescriptionInNationalLanguage;
    @XmlTransient
    protected String textualDescription;
    @XmlTransient
    protected String scaleMinimum;
    @XmlTransient
    protected String sourceDate;
    @XmlTransient
    protected String sourceIndication;
    @XmlTransient
    protected String geom;

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public Geo(long id) {
        super(id);
    }

    @SuppressWarnings("unchecked")
    public Geo() {
        this.geos = new ArrayList();
        this.cartos = new ArrayList();
    }

    public Carto getCartos(int i) {
        return this.cartos.get(i);
    }

    public int cardCartos() {
        return this.cartos.size();
    }

    public Geo getGeos(int i) {
        return this.geos.get(i);
    }

    public int cardGeos() {
        return this.geos.size();
    }

    public String getInformation() {
        return this.information;
    }

    public String getInformationInNationalLanguage() {
        return this.informationInNationalLanguage;
    }

    public String getTextualDescriptionInNationalLanguage() {
        return this.textualDescriptionInNationalLanguage;
    }

    public String getTextualDescription() {
        return this.textualDescription;
    }

    public String getScaleMinimum() {
        return this.scaleMinimum;
    }

    public String getSourceDate() {
        return this.sourceDate;
    }

    public String getSourceIndication() {
        return this.sourceIndication;
    }

    public void setCartos(List<Carto> cartos) {
        this.cartos = cartos;
    }

    public void setGeos(List<Geo> geos) {
        this.geos = geos;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setInformationInNationalLanguage(
            String informationInNationalLanguage) {
        this.informationInNationalLanguage = informationInNationalLanguage;
    }

    public void setScaleMinimum(String scaleMinimum) {
        this.scaleMinimum = scaleMinimum;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public void setSourceIndication(String sourceIndication) {
        this.sourceIndication = sourceIndication;
    }

    public void setTextualDescription(String textualDescription) {
        this.textualDescription = textualDescription;
    }

    public void setTextualDescriptionInNationalLanguage(
            String textualDescriptionInNationalLanguage) {
        this.textualDescriptionInNationalLanguage = textualDescriptionInNationalLanguage;
    }

    @Override
    public String toString() {
        return super.toString() + "  Geo{" + "cartos=" + cartos + ", geos=" + geos
                + ", information=" + information
                + ", informationInNationalLanguage=" + informationInNationalLanguage
                + ", textualDescriptionInNationalLanguage=" + textualDescriptionInNationalLanguage
                + ", textualDescription=" + textualDescription
                + ", scaleMinimum=" + scaleMinimum
                + ", sourceDate=" + sourceDate
                + ", sourceIndication=" + sourceIndication + '}' + '\n';
    }

}
