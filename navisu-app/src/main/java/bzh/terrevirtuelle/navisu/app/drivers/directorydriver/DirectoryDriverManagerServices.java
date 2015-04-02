package bzh.terrevirtuelle.navisu.app.drivers.directorydriver;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public interface DirectoryDriverManagerServices extends ComponentService {

    void init();

    void registerNewDriver(DirectoryDriver driver);
}
