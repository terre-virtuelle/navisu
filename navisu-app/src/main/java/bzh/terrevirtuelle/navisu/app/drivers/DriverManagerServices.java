package bzh.terrevirtuelle.navisu.app.drivers;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public interface DriverManagerServices extends ComponentService {

    void init();

    void registerNewDriver(Driver driver);
}
