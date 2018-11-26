package bzh.terrevirtuelle.navisu.geometry.objects3D.obj.simple;

/**
 *
 * @author serge
 * @date Mar 8, 2016
 *
 */
public class IndexTexcoord {

    public final int vIdx;
    public final double u;
    public final double v;

    public IndexTexcoord(int vIdx, double u, double v) {
        this.vIdx = vIdx;
        this.u = u;
        this.v = v;
    }
}
