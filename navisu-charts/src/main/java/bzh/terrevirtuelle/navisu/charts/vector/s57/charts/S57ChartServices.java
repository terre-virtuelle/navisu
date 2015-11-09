package bzh.terrevirtuelle.navisu.charts.vector.s57.charts;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57Controller;
import java.util.Set;
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

    public boolean isChartsOpen();

    Set<S57Controller> getS57Controllers();

}
