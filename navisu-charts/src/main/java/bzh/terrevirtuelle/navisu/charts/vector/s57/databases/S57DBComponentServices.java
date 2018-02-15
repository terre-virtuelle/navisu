package bzh.terrevirtuelle.navisu.charts.vector.s57.databases;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public interface S57DBComponentServices
        extends ComponentService {

    void on(String... files);

    boolean canOpen(String category);

    InstrumentDriver getDriver();

    List<Buoyage> getBuoyage(String database, String user, String passwd,
            Buoyage buoyage, double lat0, double lon0, double lat1, double lon1);
}
