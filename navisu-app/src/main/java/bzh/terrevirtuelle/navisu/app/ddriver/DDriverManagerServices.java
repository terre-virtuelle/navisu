package bzh.terrevirtuelle.navisu.app.ddriver;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public interface DDriverManagerServices extends ComponentService {

    void init();

    void registerNewDriver(DDriver driver);
}
