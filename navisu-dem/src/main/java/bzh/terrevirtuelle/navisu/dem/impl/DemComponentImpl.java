package bzh.terrevirtuelle.navisu.dem.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.dem.DemComponent;
import bzh.terrevirtuelle.navisu.dem.DemComponentServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.globes.Globe;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class DemComponentImpl
        implements DemComponent, DemComponentServices, InstrumentDriver, ComponentState {

    protected final String COMPONENT_KEY_NAME_0 = "DEM";
    protected String componentKeyName;
    protected static final Logger LOGGER = Logger.getLogger(DemComponentImpl.class.getName());
    protected WorldWindow wwd;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            componentKeyName = cmd[0];
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)) {

            }
        }
    }

    @Override
    public void off() {

    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {

    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public Point3D[][] retrieveElevations(WorldWindow wwd, Point3D[][] latLonTab, double targetResolution0) {

        double[] elevations = new double[latLonTab[0].length * latLonTab[1].length];

        ArrayList<LatLon> latLons = new ArrayList<>(latLonTab[0].length * latLonTab[1].length);
        for (int i = 0; i < latLonTab[0].length; i++) {
            for (int j = 0; j < latLonTab[1].length; j++) {
                latLons.add(new LatLon(Angle.fromDegrees(latLonTab[i][j].getLatitude()),
                        Angle.fromDegrees(latLonTab[i][j].getLongitude())));
            }
        }
        Sector sector = Sector.boundingSector(latLons);
        Globe globe = wwd.getModel().getGlobe();
        Point3D[][] result = new Point3D[latLonTab[0].length][latLonTab[1].length];
     /*
        double targetResolution = globe.getElevationModel().getBestResolution(sector);
        double actualResolution = Double.MAX_VALUE;

        while (actualResolution > targetResolution*20) {
            actualResolution = globe.getElevations(sector, latLons, targetResolution, elevations);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(actualResolution +" "+targetResolution*20);
        }

     
        for (int i = 0; i < latLonTab[0].length; i++) {
            for (int j = 0; j < latLonTab[1].length; j++) {
                result[i][j] = new Point3D(latLonTab[i][j].getLatitude(), latLonTab[i][j].getLongitude(), elevations[i + j]);
            }
        }
*/
     
     for (int i = 0; i < latLonTab[0].length; i++) {
            for (int j = 0; j < latLonTab[1].length; j++) {
                result[i][j] = new Point3D(latLonTab[i][j].getLatitude(),
                        latLonTab[i][j].getLongitude(), 
                        globe.getElevation(Angle.fromDegrees(latLonTab[i][j].getLatitude()),
                                Angle.fromDegrees(latLonTab[i][j].getLongitude())));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DemComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
        }
        return result;
    }
}
