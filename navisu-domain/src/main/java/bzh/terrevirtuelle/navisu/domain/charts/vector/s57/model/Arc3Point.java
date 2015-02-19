package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;




public class Arc3Point
        extends ArcCurve {

    protected Point stpt;
    protected Point ctpt;
    protected Point enpt;

    public Arc3Point() {
    }

    public Point getStpt() {
        return this.stpt;
    }

    public int cardStpt() {
        if (this.stpt == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public Point getCtpt() {
        return this.ctpt;
    }

    public int cardCtpt() {
        if (this.ctpt == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public Point getEnpt() {
        return this.enpt;
    }

    public int cardEnpt() {
        if (this.enpt == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public Arc3Point(
            long rcid) {
    }
}
