package bzh.terrevirtuelle.navisu.charts.vector.s57;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Coastline;
import java.util.Set;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface ChartS57Services
        extends ComponentService {

    Driver getDriver();

    ChartS57Layer getChartS57Layer();

    void loadFile(String path);

    Set<Coastline> getCoastlines();
}
