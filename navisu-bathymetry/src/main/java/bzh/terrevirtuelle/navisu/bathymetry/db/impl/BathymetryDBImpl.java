/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDB;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller.BathymetryDBController;
import bzh.terrevirtuelle.navisu.database.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.postgis.PGgeometry;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBImpl
        implements BathymetryDB, BathymetryDBServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(BathymetryDBImpl.class.getName());
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    DatabaseServices databaseServices;

    private String dataFileName;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private List<Point2D> points;

    @Override
    public void componentInitiated() {
        BathymetryDBController bathymetryDBController = BathymetryDBController.getInstance();
        bathymetryDBController.setBathymetryDB(this);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Statement connect(String dbName, String hostName, String protocol, String port,
            String driverName, String userName, String passwd,
            String dataFileName) {
        this.dataFileName = dataFileName;
        this.statement = databaseServices.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        return statement;
    }

    @Override
    public void open() {
        //retrieveAll();//Attention > 10 000 000 pts a afficher
        retrieve(2.11195, 51.4362);
    }

    @Override
    public void create() {
    }

    public final void read() {
        try {
            points = Files.lines(new File(dataFileName).toPath())
                    .map(line -> line.trim())
                    .map(line -> line.split(" "))
                    .map(tab -> new Point2D(Float.parseFloat(tab[0]),
                                    Float.parseFloat(tab[1]),
                                    Float.parseFloat(tab[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public final void insert() {
        points.stream().forEach((p) -> {
            try {
                preparedStatement.setDouble(1, p.getLon());
                preparedStatement.setDouble(2, p.getLat());
                preparedStatement.setDouble(3, p.getElevation());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    public final void createIndex() {
        try {
            statement.execute("CREATE INDEX bathyIndex ON bathy USING GIST (coord)");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public final double retrieve(double lon, double lat) {
        PGgeometry geom;
        ResultSet r;
        double result = Double.MAX_VALUE;
        try {
            System.out.println(Double.toString(lon) + "           " + Double.toString(lat));
            r = statement.executeQuery(
                    "SELECT coord,elevation "
                    + "FROM bathy "
                    + "ORDER BY coord <-> ST_SetSRID("
                    + "ST_MakePoint(" + Double.toString(lon) + ", " + Double.toString(lat) + ")"
                    + ", 4326) "
                    + "LIMIT 1");
            while (r.next()) {
                geom = (PGgeometry) r.getObject(1);
                System.out.print(geom.getGeometry().getFirstPoint().getX() + "  ");
                System.out.print(geom.getGeometry().getFirstPoint().getY() + "  ");
                System.out.println(r.getObject(2));
                result = r.getDouble(2);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public final void retrieveAll() {
        guiAgentServices.getJobsManager().newJob("retrieveAll", (progressHandle) -> {
            PGgeometry geom;
            try {
                ResultSet r = statement.executeQuery("SELECT ST_AsText(coord) AS gid, coord, elevation FROM bathy");
                while (r.next()) {
                    geom = (PGgeometry) r.getObject(2);
                    System.out.print(geom.getGeometry().getFirstPoint().getX() + " ");
                    System.out.print("  " + geom.getGeometry().getFirstPoint().getY() + " ");
                    System.out.println(r.getDouble(3));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void close() {
        databaseServices.close();
    }
}
