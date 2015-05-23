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

    default Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd) {
        return null;
    }

    default Connection connect(String dbName, String passwd) {
        return null;
    }

    void close();
}
