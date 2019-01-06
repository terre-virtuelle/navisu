package bzh.terrevirtuelle.navisu.stl.databases;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import java.sql.Connection;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public interface StlDBComponentServices
        extends ComponentService {

    InstrumentDriver getDriver();

    Connection getConnnection(String database, String user, String passwd);

}
