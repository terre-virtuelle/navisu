package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

public class DataSetGeographicReference extends S57Object {

    public int comf = 1;
    public int somf;
    protected String coun;

    /**
     * Ratio de la carte (ne fait pas partie de l'objet S57 d'origine)
     */
    public double ratio;

    /**
     * Limites de la carte (ne fait pas partie de l'objet S57 d'origine)
     */
    protected String bounds;

    public String getBounds() {
        return this.bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public int getComf() {
        return this.comf;
    }

    public int getSomf() {
        return this.somf;
    }

    public String getCoun() {
        return this.coun;
    }

    public DataSetGeographicReference decodDSPM(byte[] fieldValue) {
        try {
            //COMF: Octets de 16 a 19
            comf = (fieldValue[19] & 0xFF);
            comf = comf * 256 + (fieldValue[18] & 0xFF);
            comf = comf * 256 + (fieldValue[17] & 0xFF);
            comf = comf * 256 + (fieldValue[16] & 0xFF);

            //SOMF: Octets de 20 a 23
            somf = (fieldValue[23] & 0xFF);
            somf = somf * 256 + (fieldValue[22] & 0xFF);
            somf = somf * 256 + (fieldValue[21] & 0xFF);
            somf = somf * 256 + (fieldValue[20] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public DataSetGeographicReference() {
        dataSet = true;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setComf(int comf) {
        this.comf = comf;
    }

    public void setCoun(String coun) {
        this.coun = coun;
    }

    public void setSomf(int somf) {
        this.somf = somf;
    }

    @Override
    public String toString() {
        return "DataSetGeographicReference{" + "comf=" + comf + ", somf=" + somf + ", coun=" + coun + ", ratio=" + ratio + ", bounds=" + bounds + '}';
    }

}
