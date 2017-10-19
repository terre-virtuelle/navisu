package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver;

import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public interface InstrumentDriverManagerServices 
        extends ComponentService {

    void init();

    void registerNewDriver(InstrumentDriver driver);

    InstrumentDriver findDriver(String category);

    InstrumentDriver open(String ... file);
    
    default InstrumentDriver openFile(String category, String file){  
        return null;
    }

    default void off() {

    }
}
