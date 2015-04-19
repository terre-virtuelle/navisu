package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public interface InstrumentDriverManagerServices extends ComponentService {

    void init();

    void registerNewDriver(InstrumentDriver driver);

    InstrumentDriver findDriver(String category);

    void open(String category);
}
