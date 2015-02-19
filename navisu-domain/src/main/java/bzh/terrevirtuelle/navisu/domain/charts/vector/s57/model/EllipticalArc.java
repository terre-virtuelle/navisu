package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

public class EllipticalArc extends ArcCurve {

    protected Point stpt;
    protected Point ctpt;
    protected Point enpt;
    protected Point cdpm;
    protected Point cdpr;

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

    public Point getCdpm() {
        return this.cdpm;
    }

    public int cardCdpm() {
        if (this.cdpm == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public Point getCdpr() {
        return this.cdpr;
    }

    public int cardCdpr() {
        if (this.cdpr == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public EllipticalArc(long rcid) {
    }
}
