package bzh.terrevirtuelle.navisu.charts.vector.s57.charts;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @param <T>
 * @date 11/05/2014 12:49
 */
public interface S57ChartComponentServices
        extends ComponentService {

    Driver getDriver();

    boolean canOpen(String category, String file);

    void open(ProgressHandle pHandle, String... files);

    String[] getExtensions();

    void openChart(String file);

    boolean isChartsOpen();

    Set<S57Controller> getS57Controllers();

    Set<NavigationData> getS57Charts();

    List<SurfacePolylines> getCoastalLines();

    /*
    Return list of paths of S57 charts in the catalog, fromm root of ENC
    catalog = "ENC_NP5.kml" for Harbour charts
    country = "FR" for France
    version = "000" for first version default
     */
    List<Path> getFilePaths(String rootFileNames, String kmlCatalog, String country);

    /*
     Translate S7 charts in shapefiles and load these in a spatial DB
     script shell for Linux
     */
    String s57ToShapeFile(List<Path> paths);

    String s57FromCatalogToShapeFile(String rootFileNames, String kmlCatalog, String country, String epsg);

    void s57BuoyageView();
}
