/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.relational;

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

    /**
     * Connection connection = databaseServices.connect(s57NP5DB,
       "localhost", "jdbc:postgresql://","5432","org.postgresql.Driver","admin","admin");
     * 
     * @param dbName
     * @param hostName
     * @param protocol
     * @param port
     * @param driverName
     * @param userName
     * @param passwd
     * @return
     */
    Connection connect(String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd);

    /**
     *
     * @param hostName
     * @param protocol
     * @param port
     * @param driverName
     * @param userName
     * @param passwd
     * @return
     */
    Connection connect(String hostName, String protocol, String port, String driverName, String userName, String passwd);

    /**
     *
     * @param dbName
     * @param user
     * @param passwd
     * @return
     */
    Connection connectDerby(String dbName, String user, String passwd);

    /**
     *
     * @return
     */
    EntityManager getEntityManager();

    /**
     *
     * @return
     */
    boolean isConnect();

    /**
     *
     * @return
     */
    Statement getStatement();

    /**
     *
     * @param statement
     */
    void execute(String statement);

    /**
     *
     * @param query
     * @return
     */
    ResultSet executeQuery(String query);

    /**
     *
     * @param statement
     * @return
     */
    PreparedStatement prepare(String statement);

    /**
     *
     * @param indexName
     * @param tableName
     * @param columnName
     */
    void createIndex(String indexName, String tableName, String columnName);

    /**
     *
     * @param tableName
     */
    void drop(String tableName);

    /**
     *
     */
    void close();

    /**
     *
     * @param shpDir
     * @param epsg
     * @return
     */
    String shapeFileToSql(String shpDir, String epsg);

    /**
     *
     * @param databaseName
     * @param user
     * @param passwd
     * @param dir
     * @param cmd
     */
    void sqlToSpatialDB(String databaseName, String user, String passwd, String dir, String cmd);
}
