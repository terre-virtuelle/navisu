package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.model.ChartS57Model;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Layer extends RenderableLayer {

    protected final static Logger LOGGUER = Logger.getLogger(ChartS57Layer.class.getName());

    protected ChartS57Model model;

    public ChartS57Layer(ChartS57Model model) {
        this.model = model;
        this.init();
    }

    private void init() {

    }
}
