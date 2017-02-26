package bzh.terrevirtuelle.navisu.charts.vector.s57.charts;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface S57ChartComponentServices
        extends ComponentService {

    Driver getDriver();

    boolean canOpen(String category, String file);

    void open(ProgressHandle pHandle, String... files);

    String[] getExtensions();

    void openChart(String file);

    boolean isChartsOpen();

    Set<S57Controller> getS57Controllers();

    Set<NavigationData> getS57Charts();

    List<SurfacePolylines> getCoastalLines();
    
    S57ChartComponentController getS57ChartComponentController();
}
