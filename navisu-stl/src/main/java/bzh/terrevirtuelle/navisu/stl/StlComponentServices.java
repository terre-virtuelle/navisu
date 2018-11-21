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

    String exportBaseSTL(String stlFilename, String stlBaseFilename);

    void viewSTL(String filename);
    
}
