package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;
import java.util.*;



public class Geo
        extends Feature
        implements Serializable {

    protected List<Carto> cartos;
    protected List<Geo> geos;
    protected String information;
    protected String informationInNationalLanguage;
    protected String textualDescriptionInNationalLanguage;
    protected String textualDescription;
    protected String scaleMinimum;
    protected String sourceDate;
    protected String sourceIndication;

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
        return  this.geos.get(i);
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
