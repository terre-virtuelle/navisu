/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.media.images;

import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 * @date Oct 9, 2019
 */
public interface ImageServices
        extends ComponentService {

    void displayShelf( String directoryName);
}
