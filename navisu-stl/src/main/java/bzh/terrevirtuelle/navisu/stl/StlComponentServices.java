package bzh.terrevirtuelle.navisu.stl;

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

    void viewSTL(String filename);
}
