/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.app.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.database.DatabaseServices;
import bzh.terrevirtuelle.navisu.database.app.TestDB;
import bzh.terrevirtuelle.navisu.database.app.TestDBServices;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 21 mai 2015
 * @author Serge Morvan
 */
public class TestDBImpl
        implements TestDB, TestDBServices, DatabaseDriver, ComponentState {

    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    private List<Ship> ships;
    private final String SHIP = "SHIP";
    private final String NAME = "TestDB";
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private final static String createTableShip = "CREATE TABLE SHIP("
            + "mmsi        INT NOT NULL PRIMARY KEY,"
            + "shipName    VARCHAR(128),"
            + "latitude    DOUBLE,"
            + "longitude   DOUBLE,"
            + "date        VARCHAR(32),"
            + "time        VARCHAR(32)"
            + ")";
    private final String insertQuery = "INSERT INTO " + SHIP + " (mmsi, shipName,latitude,longitude,date,time)"
            + " VALUES(?,?,?,?,?,?)";
    private final String retrieveQuery = "SELECT * FROM " + SHIP;

    @Override
    public Connection connect(String dbName, String passwd) {
        connection = databaseServices.connect(dbName, passwd);
        return connection;

    }

    @Override
    public void close() {
        databaseServices.close();
    }

    @Override
    public void componentInitiated() {
        ships = new ArrayList<>();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public boolean canOpen(String dbName) {
        return dbName.equalsIgnoreCase(NAME);
    }

    @Override
    public DatabaseDriver getDriver() {
        return this;
    }

    @Override
    public void onJDBC() {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get("data/saved", "savedAisShips.csv"));
            ships = lines.filter(string -> !string.contains("MMSI")).map(Ship::parseCsv).collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(TestDBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lines != null) {
            lines.close();
        }
        // System.out.println(ships);
       guiAgentServices.getJobsManager().newJob(null, (progressHandle) -> {
            createTable();
            populateTable();
            retrieveAll();
        });
    }

    /**
     * Execute a query.
     *
     * @param query The query to execute
     */
    public void executeCommand(String query) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /**
     * Create a table into the database
     */
    public void createTable() {
        try {
            System.out.println("Creating table : " + SHIP);
            executeCommand(createTableShip);
        } catch (SQLException e) {
            try {
                System.out.println(SHIP + " already exists : " + e);
                System.out.println("Drop table : " + SHIP);
                executeCommand("DROP TABLE " + SHIP);
                System.out.println("Creating table : " + SHIP);
                executeCommand(createTableShip);
            } catch (SQLException f) {
                System.out.println("Error DROP TABLE :" + f);
            }
        }
    }

    public void populateTable() {
        ships.stream().forEach((ship) -> {
            try {
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, ship.getMMSI());
                preparedStatement.setString(2, ship.getName());
                preparedStatement.setDouble(3, ship.getLatitude());
                preparedStatement.setDouble(4, ship.getLongitude());
                preparedStatement.setString(5, ship.getLocalDate().toString());
                preparedStatement.setString(6, ship.getLocalTime().toString());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TestDBImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void retrieveAll() {
        ships.clear();
        try {
            ResultSet rs = statement.executeQuery(retrieveQuery);
            while (rs.next()) {
                ships.add(new Ship(rs.getInt("mmsi"),
                        rs.getString("shipName"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        LocalDate.parse(rs.getString("date")),
                        LocalTime.parse(rs.getString("time"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(ships);
        System.out.println(ships.size());
    }
}
