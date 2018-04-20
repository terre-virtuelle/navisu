package bzh.terrevirtuelle.navisu.charts.vector.s57.databases;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.sql.Connection;
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

    Connection getConnnection(String database, String user, String passwd);

    List<? extends Geo> getS57Objects(Geo t, 
            double latMin, double lonMin, double latMax, double lonMax);

    List<List<? extends Geo>> getS57Objects(List<? extends Geo> t, 
            double latMin, double lonMin, double latMax, double lonMax);
}
