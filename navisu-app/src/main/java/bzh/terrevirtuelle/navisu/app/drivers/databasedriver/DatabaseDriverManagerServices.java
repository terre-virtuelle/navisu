package bzh.terrevirtuelle.navisu.app.drivers.databasedriver;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public interface DatabaseDriverManagerServices extends ComponentService {

    void init();

    void registerNewDriver(DatabaseDriver driver);

    void connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd);

    void close(String dbName);
}
