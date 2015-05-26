/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface DatabaseServices
        extends ComponentService {

    Connection connect(String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd);

    Connection connect(String driverName, String user, String passwd);

    EntityManager getEntityManager();

    boolean isConnect();

    Statement getStatement();

    void execute(String statement);

    ResultSet executeQuery(String query);

    PreparedStatement prepare(String statement);

    void createIndex(String indexName, String tableName, String columnName);

    void drop(String tableName);

    void close();
}
