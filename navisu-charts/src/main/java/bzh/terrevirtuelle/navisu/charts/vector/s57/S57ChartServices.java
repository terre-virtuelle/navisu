package bzh.terrevirtuelle.navisu.charts.vector.s57;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface S57ChartServices
        extends ComponentService {

    Driver getDriver();

    void openChart(String file);

    /*
     void addCoastlines();
     void removeCoastlines();
     void addDepthAreas();
     void addDepthContours();
     */
}
