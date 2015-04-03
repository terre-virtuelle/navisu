package bzh.terrevirtuelle.navisu.app.drivers.databasedriver;

import java.sql.Connection;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public interface DatabaseDriver {

    boolean canOpen(String dbName);

    public Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd);

    void close();
}
