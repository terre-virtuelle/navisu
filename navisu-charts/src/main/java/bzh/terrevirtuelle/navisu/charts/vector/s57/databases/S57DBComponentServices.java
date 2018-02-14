package bzh.terrevirtuelle.navisu.charts.vector.s57.databases;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.*;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public interface S57DBComponentServices
        extends ComponentService {

    Driver getDriver();

    boolean canOpen(String category, String file);

    void open(ProgressHandle pHandle, String... files);

    List<Buoyage> getBuoyage(String database, String user, String passwd,
            Buoyage buoyage, double lat0, double lon0, double lat1, double lon1);
}
