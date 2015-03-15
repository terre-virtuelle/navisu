/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.impl;

import bzh.terrevirtuelle.navisu.database.Database;
import bzh.terrevirtuelle.navisu.database.DatabaseServices;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class DatabaseImpl
        implements Database, DatabaseServices, ComponentState {

    private Statement statement;
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Connection connect(String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd) {
        try {
            Class.forName(driverName);
            String url = protocol + hostName + ":" + port + "/" + dbName;
            connection = DriverManager.getConnection(url, userName, passwd);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Override
    public void execute(String stmt) {
        try {
            this.statement.execute(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet executeQuery(String query) {
        ResultSet r = null;
        try {
            r = this.statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public PreparedStatement prepare(String statement) {
        try {
            preparedStatement = connection.prepareStatement(statement);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStatement;
    }

    @Override
    public void createIndex(String indexName, String tableName, String columnName) {
        try {
            statement.execute("CREATE INDEX " + indexName + " ON " + tableName + " USING GIST (" + columnName + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void drop(String tableName) {
        try {
            statement.execute("DROP TABLE " + tableName);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public boolean isConnect() {
        return connection != null;
   }
}
