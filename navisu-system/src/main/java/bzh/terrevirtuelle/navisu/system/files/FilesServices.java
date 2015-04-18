package bzh.terrevirtuelle.navisu.system.files;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:49
 */
public interface FilesServices extends ComponentService {

    Driver getDriver();

    void openFile(String file);
}
