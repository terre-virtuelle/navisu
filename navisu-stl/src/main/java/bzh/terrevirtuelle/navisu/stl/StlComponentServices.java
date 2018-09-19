package bzh.terrevirtuelle.navisu.stl;

import gov.nasa.worldwind.render.Path;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public interface StlComponentServices
        extends ComponentService {

    String exportSTL(double latMin, double lonMin, double latScale, double lonScale,
            String kmlFilename, double verticalOffset);

    String exportBaseSTL(String stlFilename, String stlBaseFilename);

    void viewSTL(String filename);

    String toFacet(Path path,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset);
}
