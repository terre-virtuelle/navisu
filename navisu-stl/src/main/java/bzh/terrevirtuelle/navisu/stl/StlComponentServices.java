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

    void exportBaseSTL(String outFilename, String inFilename);

    void exportRotateBaseSTL(String outFilename, String inFilename, double angle);

    void viewSTL(String filename);

}
