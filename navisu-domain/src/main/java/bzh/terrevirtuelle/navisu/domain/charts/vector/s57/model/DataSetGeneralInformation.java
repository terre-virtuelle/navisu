package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

public class DataSetGeneralInformation extends S57Object {

    protected String dstr;

    public String getDstr() {
        return this.dstr;
    }

    public void decodDSID(String fieldValue) {
    }

    public void decodDSSI(String fieldValue) {
    }

    @Override
    public String toString() {
        return "DataSetGeneralInformation{" + "dstr=" + dstr + '}';
    }

}
