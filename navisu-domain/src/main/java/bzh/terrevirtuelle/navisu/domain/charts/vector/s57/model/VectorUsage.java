package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;

public class VectorUsage
        implements Serializable {

    protected int ornt;
    protected int usag;
    protected int topi;
    protected int mask;
    protected int pos;

    public VectorUsage() {
        super();
    }

    public VectorUsage(int ornt, int usag, int topi, int mask, int pos) {
        this.mask = mask & 0xFF;
        this.ornt = ornt & 0xFF;
        this.usag = usag & 0xFF;
        this.topi = topi & 0xFF;

    }

    public VectorUsage(int ornt, int usag, int mask, int pos) {
        this.mask = mask & 0xFF;
        this.ornt = ornt & 0xFF;
        this.usag = usag & 0xFF;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getOrnt() {
        return this.ornt;
    }

    public int getUsag() {
        return this.usag;
    }

    public int getTopi() {
        return this.topi;
    }

    public int getMask() {
        return this.mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public void setOrnt(int ornt) {
        this.ornt = ornt;
    }

    public void setTopi(int topi) {
        this.topi = topi;
    }

    public void setUsag(int usag) {
        this.usag = usag;
    }

    @Override
    public String toString() {
        return "VectorUsage{" + "ornt=" + ornt + ", usag=" + usag + ", topi=" + topi + ", mask=" + mask + ", pos=" + pos + '}';
    }

}
