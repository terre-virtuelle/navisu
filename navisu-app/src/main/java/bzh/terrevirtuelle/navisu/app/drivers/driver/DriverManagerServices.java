package bzh.terrevirtuelle.navisu.app.drivers.driver;

import javafx.stage.FileChooser;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public interface DriverManagerServices extends ComponentService {

    void init();

    default void open(FileChooser.ExtensionFilter ext) {

    }

    void open(String description, String[] ext);

   // void open(String description, String[] ext, String path);

    void registerNewDriver(Driver driver);
}
