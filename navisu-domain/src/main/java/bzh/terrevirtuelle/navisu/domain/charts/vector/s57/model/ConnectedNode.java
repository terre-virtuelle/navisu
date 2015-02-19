package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

public class ConnectedNode
        extends Node {

    public ConnectedNode(long rcid) {
        this.setId(rcid);
    }

    public ConnectedNode() {
 
    }

    /**
     *
     * @return
     */
    @Override
    public ConnectedNode clone() {
        ConnectedNode n;
        n = new ConnectedNode(id);
        n.setPoint(this.point);
        return n;
    }

}
